/*
 *  This unpublished work, first created in 2017 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2017.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    ReleaseOrderAndCommitPW.java
 * 
 *  Created: 2018-05-29
 * 
 *  Author:  Fred Ettefagh
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2018-08-14		Fred Ettefagh	Initial Release, defect 939 Multiple Order release - change to commit after each order complete
 *  2019-05-03      Fred Ettefagh   changed  all methods for repair order release  
 *  2019-06-05      F. Ettefagh     changed code for Repair shop order split 
 *  2019-08-01      R. Thorpe       Modified releaseOrder to check for parentOrderId in case of SUB ORDER
 *                                  Added call to T308 in method releaseRepairOrder
 *  2019-09-20      Bahram J.       SMRO_TR_308, changed xml to use synWorkOrder instead of syncRecord
 *  2019-09-20      Bahram J        SMRO_TR_308, added role to bypass t308 interface
 *  2019-09-25      F. Ettefagh     SMRO_TR_308, renamed orderReleaseInterface to  RepairOrderT308Interface    
 *  2019-11-20      F. Ettefagh     SMRO_TR_308, added code to release multi repair orders and send t308
 *  2019-11-22      F. Ettefagh     SMRO_TR_308, fixed null pointer exception when releasing repair orders 
 *  2020-01-28      John DeNinno    Defect 953 fix released by
 *  2020-02-13      Fred Ettefagh   SMRO_TR_308, Defect 1474, changed code to send t308 message after certain changes for WO Alt and task include exclude
 *  2020-02-24      Fred Ettefagh   SMRO_TR_308, Defect 1474, removed comments and added debug info
 *  2020-01-28      John DeNinno    Defect 953 fix released by - added to match overhaul
 */
package com.pw.solumina.sfwid.application;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import com.ibaset.common.Reference;
import com.ibaset.common.client.SoluminaServiceLocator;
import com.ibaset.common.concurrent.AsynchronousTransaction;
import com.ibaset.common.concurrent.AsynchronousTransactionCallback;
import com.ibaset.common.util.ClassUtils;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sfwid.application.IOrder;
import com.pw.common.dao.CommonDaoPW;
import com.pw.common.utils.CommonUtilsPW;
import com.pw.solumina.sfpl.dao.impl.OrderCreateDaoPW;
import com.pw.solumina.sfwid.dao.WorkOrderDaoPW;
import com.pw.solumina.T308.integration.impl.RepairOrderT308Interface;
import com.pw.solumina.sfwid.dao.OrderDaoPW;

public class ReleaseOrderAndCommitPW {
	
	@Reference private IOrder oobWfwidOrder;
	@Reference private WorkOrderDaoPW workOrderDaoPW;
	@Reference private IEvent event = null;
	@Reference private CommonUtilsPW commonUtilsPW;
	@Reference private OrderCreateDaoPW orderCreateDaoPW = null;
	@Reference private RepairOrderT308Interface repairOrderT308Interface;
	@Reference private CommonDaoPW commonDaoPW;
	@Reference private IMessage message = null;
        @Reference private OrderDaoPW orderDaoPW;
	 
	 public void releaseOrder(String orderId, String sendLtaFlag, String calledFrom){
		 
		 if ((sendLtaFlag == null) || sendLtaFlag.isEmpty())
			{
			 sendLtaFlag = "N";
			}
		 
		 if (StringUtils.equalsIgnoreCase(calledFrom, "UDV")){
		    	
			    String pwOrderType = commonUtilsPW.getOrderTypePW(orderId);
			    
 		    	if (StringUtils.equalsIgnoreCase(pwOrderType, "SUB ORDER")){
 		    		String parentOrderId = commonDaoPW.getParentOrderId(orderId);
 		    		pwOrderType = commonUtilsPW.getOrderTypePW(parentOrderId);
 		    	}
 		    	
 		    	if (StringUtils.equalsIgnoreCase(pwOrderType, "REPAIR")){
		    		releaseRepairOrder(orderId,sendLtaFlag);    
		    	}else{     
		    		oobReleaseOrder(orderId, sendLtaFlag);
                    orderDaoPW.updateReleasedBy(orderId);
			    	event.refreshTool();
		    	}
		 }
	 }
	 
 
	public void submitRepairOrderReleaseJob(String orderId, String sendLtaFlag,String orderNo) {

			try{
				Class<?> backJob = this.getClass();
				Method method = ClassUtils.findMethod(backJob, "oobReleaseOrder", 2); 
				
				Object[] args = {orderId,sendLtaFlag};
				
				AsynchronousTransactionCallback c = new  AsynchronousTransactionCallback ( "PWUST Repair Release Order",
																						   backJob,
																						   method.getName(),
																						   args,
																						   orderNo,
																						   orderId,
																						   null,
																						   null,
																						   null);
				
				AsynchronousTransaction t = SoluminaServiceLocator.getAsynchronousTransactionService().execute(c);
				t.setIgnoreUIEvents(true);
				
			}catch(Exception e){
				throw new RuntimeException(e);
			}
	}
	 
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void releaseRepairOrder(String orderId, String sendLtaFlag) 
	{
		// interface with SAP T308
		boolean repairMultiOrderReleaseFlag = false;
    	String sapIDocNumber = null;

  		Map sfwidOrderDescMap = orderCreateDaoPW.selectOrderDesc(orderId);
  		sapIDocNumber = (String) sfwidOrderDescMap.get("UCF_ORDER_VCH15");
		BigDecimal numberOfBackGroundOrders = (BigDecimal) sfwidOrderDescMap.get("UCF_ORDER_NUM5");
		System.out.println("in ReleaseOrderAndCommitPW.releaseRepairOrder orderId = " + orderId + " iDocNumber = " + sapIDocNumber + " numberOfBackGroundOrders = " + numberOfBackGroundOrders );
    	if (StringUtils.isNotBlank(sapIDocNumber) && numberOfBackGroundOrders !=null && numberOfBackGroundOrders.intValue()>1){
    			repairMultiOrderReleaseFlag = true;
    	}
		
		if (repairMultiOrderReleaseFlag){
			List<Map> repairOrderList = commonDaoPW.selectsfwidOrderDescMutilOrdersList(sapIDocNumber);
			if (repairOrderList.size()!=numberOfBackGroundOrders.intValue()){
				message.raiseError("MFI_20","Cannot release order,all background orders have not been created yet");
			}else{
				for (Map sfwidOrderDescListMap : repairOrderList) {
					String orderIdMulti = (String) sfwidOrderDescListMap.get("ORDER_ID");
					if (commonUtilsPW.sendRepairT308()){
						repairOrderT308Interface.sendOrderT308Msg(orderIdMulti,"releaseRepairOrder");
					}
					oobReleaseOrder(orderIdMulti, sendLtaFlag);
				}
			}
		}else{
			if (commonUtilsPW.sendRepairT308()){
				repairOrderT308Interface.sendOrderT308Msg(orderId,"releaseRepairOrder");
			}
			oobReleaseOrder(orderId, sendLtaFlag);
			event.refreshTool();
		}
	}
	
	public void oobReleaseOrder(String orderId, String sendLtaFlag){
		
		oobWfwidOrder.releaseOrder(orderId, sendLtaFlag);
		orderDaoPW.updateReleasedBy(orderId); //defect 953
		
	}
	
}
