/*
* This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017, 2019.  All rights reserved.  Information contained 
*  herein is proprietary and confidential and improper use or disclosure 
*  may result in civil and penal sanctions.
*
*  File:    OrderMroRepairSplitPW.java
* 
*  Created: 2019-06-05
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2019-06-05 	Fred Ettefagh   Initial Release for PW_SMRO_TR_319
* 2019-08-12 	Fred Ettefagh   added code to rename and cancel serial No for split/replace transactions 
* 2019-08-21    Fred Ettefagh   change interface code to add outboundMessageId, to look for correct return message
* 2019-08-21    Fred Ettefagh   changed method startRepairOrderSplit, values for  calledFrom values are REPAIR_WO_SPLIT, REPAIR_REWORK_CREATE_REWORK, REPAIR_REWORK_CREATE_REPLACE
* 2019-08-29    Fred Ettefagh   changed method startRepairOrderSplit, to undo update SN to "-CAN"
* 2019-09-12    Fred Ettefagh   SMRO_TR_319, added code for SCR-001 
* 2019-09-20    Bahram J.       changed error handling 
* 2019-11-20    Fred Ettefagh   SMRO_TR_319, Removed check for order qty, and added parentOrderWorkLoc to argsHashMap
* 2019-12-16    Fred Ettefagh   SMRO_TR_319, changed split code to get part no, part chg from new plan instead of parent order no
* 2019-12-11	BPolak			added note text for defect 1416
* 2020-02-17    B. Corson       Defect 1436 - Comment out code that tries to get partChg and partNo. PartChg should now come in and partNo should not change to subject PartNo.
* 2020-06-01    Fred Ettefagh   SMRO_TR_319, Defect 1611, changed split logic for parent order , if serial no has not been worked on delete it, otherwise rename serial no by appending "-split" then cancel it
* 2020-06-04    Fred Ettefagh   SMRO_TR_319, Defect 1611, fixed Dao call to get serial no data and added error message if no serial data is found  
* 2020-06-10    Fred Ettefagh   SMRO_TR_319, Defect 1611, added code to update SFWID_ORDER_DESC.ORDER_STOP_QTY to 0 after a split and update sfwid_order_desc.SPLIT_FROM_ORDER_ID and sfwid_order_desc.SPLIT_FLAG
* 2020-06-22    Fred Ettefagh   SMRO_TR_319, Defect 1611, added code to update SFWID_ORDER_DESC.SPLIT_FROM_ORDER_ID for both new order and parent order  
* 2020-07-14    John DeNinno	defect 1392 Add Sub Order Type
* 2020-08-18    D.Miron         SMRO_TR_319 Defect 1757 - Set planItemNo and sapOrderType.
* 2020-10-06    Fred Ettefagh   Defect 1772, added code to update custom table SFWID_BUYOFF_HIST_PW column serial no after order split 
*/
package com.ibaset.solumina.sfwid.application.impl;

import static com.ibaset.common.SoluminaConstants.SEMI_COLON;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.ibaset.common.Reference;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.application.IParse;
import com.ibaset.solumina.sfwid.application.IOperation;
import com.ibaset.solumina.sfwid.dao.IOrderDao;
import com.ibaset.solumina.sfwid.dao.impl.AsWorkedItemDaoImpl;
import com.ibaset.solumina.sfwid.dao.impl.OrderMroRepairSplitDao;
import com.pw.common.dao.CommonDaoPW;
import com.pw.common.utils.CommonUtilsPW;
import com.pw.common.utils.SplitUtilsPW;
import com.pw.solumina.T319.integration.impl.WorkOrderSplitInterface;
import com.pw.solumina.sfpl.dao.impl.RepairOrderCreateDaoPW;

public class OrderMroRepairSplitPW {
	
	
	@Reference CommonDaoPW commonDaoPW = null;
	@Reference CommonUtilsPW commonUtilsPW = null;
	@Reference IParse parse = null;
	@Reference IMessage message = null;
    @Reference OrderMroRepairSplitDao orderMroRepairSplitDao  = null;
    @Reference IEvent event = null;
    @Reference SplitUtilsPW splitUtilsPW = null;
    @Reference WorkOrderSplitInterface workOrderSplitInterface = null;	
    @Reference AsWorkedItemDaoImpl AsWorkedItemDaoImpl = null;
    @Reference IOrderDao iOrderDao= null;
    @Reference IOperation operation = null;
    @Reference RepairOrderCreateDaoPW repairOrdeCreateDao = null;
    
	@SuppressWarnings({ "rawtypes"})
	public String startRepairOrderSplit(String parentOrderNo,
			                            String serialNumberList,
			                            String newOrderNo,
			                            String noteText,
			                            String partNo, 
			                            String partChg,
			                            String planNo,
			                            String planId,
			                            Number planVer,
			                            Number planRev,
			                            Number planAlt,
			                            String calledFrom) throws Exception{
		
		String  newOrderId = null;
		
		System.out.println("startRepairOrderSplit partNo = " + partNo);
		System.out.println("startRepairOrderSplit partChg = " + partChg);
		System.out.println("startRepairOrderSplit planId = " + planId);
		System.out.println("startRepairOrderSplit planVer = " + planVer);
		System.out.println("startRepairOrderSplit planRev = " + planRev);
		System.out.println("startRepairOrderSplit planAlt = " + planAlt);

        // Begin Defect 1436
        /*
		Map sfplPlanVMap = commonDaoPW.sfplPlanVSelect(planId, planVer, planRev, planAlt);
		if (sfplPlanVMap == null){
			message.raiseError("MFI_20","sfplPlanVMap is null");
		}
		partNo = (String)sfplPlanVMap.get("PART_NO");
		partChg  =(String)sfplPlanVMap.get("PART_CHG");
        */
        // End Defect 1436
		
		System.out.println("startRepairOrderSplit partNo = " + partNo);
		System.out.println("startRepairOrderSplit partChg = " + partChg);

		validateInputParams(serialNumberList, newOrderNo, partNo, partChg, planNo, planId, planVer, planRev, planAlt);		
		
		//commonUtilsPW.disableConnectionHistory();
		
		String todaysDate = commonDaoPW.selectDBTimeStamp("MM/DD/YYYY");
		String userName = ContextUtil.getUser().getUsername();
		
		List newOrderNoList = commonDaoPW.selectsfwidOrderDescList(newOrderNo);
		if (newOrderNoList!=null && !newOrderNoList.isEmpty()){
			message.raiseError("MFI_20","Error new order No " + newOrderNo + " exits in table sfwid_order_Desc table" );
		}
		
		String[] serialNumberArray  = parse.parse(serialNumberList,SEMI_COLON,true);
		Number newOrderQty = serialNumberArray.length;

		List parentOrderNoList = commonDaoPW.selectsfwidOrderDescList(parentOrderNo);
		if (parentOrderNoList==null || parentOrderNoList.isEmpty()){
			message.raiseError("MFI_20","Error getting order No " + parentOrderNo + " from sfwid_order_Desc table" );
		}
		
		Map sfwidOrderDescParentMap = (Map)parentOrderNoList.get(0);
		String parentSalesOrderNumber =  (String) sfwidOrderDescParentMap.get("UCF_ORDER_VCH11"); 
		String parentInterfaceTablePK =  (String) sfwidOrderDescParentMap.get("UCF_ORDER_VCH15");
		String parentOrderId =  (String) sfwidOrderDescParentMap.get("ORDER_ID");
		Number parentOrderQty = (Number) sfwidOrderDescParentMap.get("ORDER_QTY");
		Number parentOrderScrapQty = (Number) sfwidOrderDescParentMap.get("ORDER_SCRAP_QTY");
		Number parentOrderCompleteQty = (Number) sfwidOrderDescParentMap.get("ORDER_COMPLETE_QTY");
		Number parentOrderStopQty = (Number) sfwidOrderDescParentMap.get("ORDER_STOP_QTY");
		String parentOrderType =  (String) sfwidOrderDescParentMap.get("ORDER_TYPE");
		String parentPlanNo =  (String) sfwidOrderDescParentMap.get("PLAN_NO");
		String parentOrderWorkLoc = (String) sfwidOrderDescParentMap.get("WORK_LOC");	
		String parentPartNo = (String) sfwidOrderDescParentMap.get("PART_NO");
			
		List  sfwidLotDescList = commonDaoPW.selectSfwidLotDescList(parentOrderId);
		Map sfwidLotDescMap = (Map)sfwidLotDescList.get(0);
		String lotNo = (String)sfwidLotDescMap.get("LOT_NO");
		
		String  asWorkedSNStrings = orderMroRepairSplitDao.checkSfwidAsWorkedItem(partNo, lotNo, serialNumberArray);
		if (StringUtils.isNotBlank(asWorkedSNStrings)){
			message.raiseError("MFI_20","Cannot use serial no " +  asWorkedSNStrings  + " due to stopped status " );
		}
			
		
		List<Map> sfwidSerialDescList = orderMroRepairSplitDao.selectSfwidSerialDescList(parentOrderId, serialNumberList);
		if (sfwidSerialDescList== null || sfwidSerialDescList.isEmpty()){
			message.raiseError("MFI_20","Error getting Serial No Data" );
		}
		checkSerialNoPriorToSplit(parentOrderId,sfwidSerialDescList);
			
		newOrderNo = null;
		// Defect 1757 - Added planItemNo
        String planItemNo = commonDaoPW.getPlanItemNo(planNo);  // Defect 1757
        
		newOrderNo = workOrderSplitInterface.requestSMOrderNo(parentOrderId, parentOrderNo, planItemNo, serialNumberArray,parentOrderWorkLoc,calledFrom);
		
		if (StringUtils.isBlank(newOrderNo)){
			message.raiseError("MFI_20","Error getting new order No" );
		}
		
		HashMap<String, Object> argsHashMap = new HashMap<String, Object>();
		argsHashMap.put("newOrderNo", newOrderNo);
		argsHashMap.put("newOrderQty",newOrderQty);
		argsHashMap.put("parentSalesOrderNumber", parentSalesOrderNumber);
		argsHashMap.put("parentInterfaceTablePK", parentInterfaceTablePK);
		argsHashMap.put("partNo", partNo);
		argsHashMap.put("partChg",partChg);
		argsHashMap.put("planNo", planNo);
		argsHashMap.put("planId", planId);
		argsHashMap.put("planVer",planVer);
		argsHashMap.put("planRev", planRev);
		argsHashMap.put("planAlt", planAlt);
		argsHashMap.put("orderType", parentOrderType);
		argsHashMap.put("effectivityType", "DATE");
		argsHashMap.put("scheduledStartDate",todaysDate); 
		argsHashMap.put("userName",userName);
		argsHashMap.put("parentOrderWorkLoc",parentOrderWorkLoc);
		argsHashMap.put("calledFrom",calledFrom); //defect 1392
		
		String parentLotNo = commonUtilsPW.getOrderLotNo(parentOrderId);
		newOrderId = splitUtilsPW.createNewRepairOrder(argsHashMap);
			
		if (StringUtils.equalsIgnoreCase("REPAIR_WO_SPLIT", calledFrom)  ||
		    StringUtils.equalsIgnoreCase("REPAIR_REWORK_CREATE_REWORK", calledFrom) ||
		    StringUtils.equalsIgnoreCase("REPAIR_REWORK_CREATE_REPLACE", calledFrom)){
			
				argsHashMap.clear();
				argsHashMap.put("parentOrderId", parentOrderId);
				argsHashMap.put("newOrderId",newOrderId); 
				argsHashMap.put("serialNumberList",serialNumberList);
				argsHashMap.put("newLotNumber","N/A");
				
					argsHashMap.clear();
					
					argsHashMap.put("parentOrderId", parentOrderId);
					argsHashMap.put("parentLotNo",parentLotNo);
					
					for (Map sfwidSerialDescMap : sfwidSerialDescList) {
						
						String parnetSerialId= (String) sfwidSerialDescMap.get("SERIAL_ID");
						String parnetLotId= (String) sfwidSerialDescMap.get("LOT_ID");
						String parnetSerialStatus = (String) sfwidSerialDescMap.get("SERIAL_STATUS");
						String parnetSerialNo = (String) sfwidSerialDescMap.get("SERIAL_NO");
						
						if (StringUtils.equalsIgnoreCase(parnetSerialStatus, "IN PROCESS") || StringUtils.equalsIgnoreCase(parnetSerialStatus, "ACTIVE")){
							String renamedSerialno =  renameSerialNumber(parentOrderId,parnetSerialId,parnetSerialNo,parentPartNo);
							cancelSerialNumber(parentOrderId,renamedSerialno,parentLotNo,calledFrom);
						}else{
							splitUtilsPW.deleteSerial(parentOrderId, parnetSerialId, null/*calledFrom*/);
						}
					}
		}
			
		// assign serial number to new order
		argsHashMap.clear();
		argsHashMap.put("newOrderId", newOrderId);
		argsHashMap.put("serialNumberList", serialNumberList);
		argsHashMap.put("partNo", partNo);
		argsHashMap.put("partChg",partChg);
		splitUtilsPW.insertSerialPre(argsHashMap);
		
		if (StringUtils.equalsIgnoreCase("REPAIR_WO_SPLIT", calledFrom)  ||
			StringUtils.equalsIgnoreCase("REPAIR_REWORK_CREATE_REWORK", calledFrom)){
				int newParentOrderQty = parentOrderQty.intValue() -  serialNumberArray.length;
				orderMroRepairSplitDao.updateSfwidOrderDescQty(parentOrderId, newParentOrderQty,0);
				orderMroRepairSplitDao.updateSfwidLotDescQty(parentOrderId, newParentOrderQty);
		}
		
		orderMroRepairSplitDao.updateOrderSplitColumns (parentOrderId,newOrderId,"Y");
		
		
		//1416 record note text
		commonDaoPW.updatePwustSfwidOrderDescNoteText(newOrderId, noteText);
		
		//defect 1392
		if (StringUtils.equals(calledFrom, "REWORK")) {
			commonDaoPW.updatePwSubOrderType(newOrderId,"REWORK");
		}
		
		// Defect 1757
		Map sapTypeMap = workOrderSplitInterface.getWorkOrderSAPOrderTypeAndIndicator(calledFrom, parentOrderId);
		String sapOrderType = (String) sapTypeMap.get("SAP_ORDER_TYPE");
		repairOrdeCreateDao.updatePwustSfwidOrderDesc(newOrderId, null, sapOrderType, null);
		
		event.refreshTool();
		return newOrderId;
	}


	public void cancelSerialNumber(String parentOrderId, String serialno, String parentLotNo,String calledFrom) {
		
		HashMap<String, Object> argsHashMap = new HashMap<String, Object>();
		argsHashMap.clear();
		
		argsHashMap.put("parentOrderId",parentOrderId);
		argsHashMap.put("serialNumberList",serialno);
		argsHashMap.put("parentLotNo",parentLotNo);
		splitUtilsPW.stopMultipleOperation(argsHashMap);
		if (StringUtils.equalsIgnoreCase("REPAIR_WO_SPLIT", calledFrom)  ||
			StringUtils.equalsIgnoreCase("REPAIR_REWORK_CREATE_REWORK", calledFrom)){
			orderMroRepairSplitDao.updateSfwidSerialDescUCF(parentOrderId, serialno, "Cancel via repair order split");
		}
	}


	public String renameSerialNumber(String parentOrderId, String parnetSerialId, String parnetSerialNo, String parentPartNo) {
		
		String prefix = null;
		
		List list =orderMroRepairSplitDao.selectSfwidSerialDescPartNo(parnetSerialNo+"-SPLIT%", parentPartNo);
		if (list==null || list.isEmpty()){
			prefix = "SPLIT1"; 
		}else{
			Map map = (Map)list.get(0);
			String existingSerialNo = (String)map.get("SERIAL_NO");
			String existingSuffix = StringUtils.substringAfterLast(existingSerialNo, "-");
			existingSuffix = StringUtils.substring(existingSuffix,"SPLIT".length());
			int existingSuffixInt= Integer.parseInt(existingSuffix) ;
			existingSuffixInt++;
			prefix = "SPLIT" + String.valueOf(existingSuffixInt);
		}
		
		String changedSerialNo = parnetSerialNo + "-" + prefix;
		
		HashMap<String, Object> argsHashMap = new HashMap<String, Object>();
		argsHashMap.clear();
		argsHashMap.put("parentOrderId", parentOrderId);
		argsHashMap.put("oldSerialId",parnetSerialId);
		argsHashMap.put("newSerialNumber",changedSerialNo);
		splitUtilsPW.updateSerial(argsHashMap);
		String userName = ContextUtil.getUser().getUsername();
		commonDaoPW.updateSfwidBuyoffHistPW(parentOrderId, parnetSerialNo, changedSerialNo, userName);
		return changedSerialNo;

	}


	public void checkSerialNoPriorToSplit(String parentOrderId, List<Map> sfwidSerialDescList) {

		if (sfwidSerialDescList!=null && !sfwidSerialDescList.isEmpty()){
			for (Map sfwidSerialDescMap : sfwidSerialDescList) {
				
				String serialHoldStatus = (String)sfwidSerialDescMap.get("SERIAL_HOLD_STATUS");
				String serialNo = (String)sfwidSerialDescMap.get("SERIAL_NO");
				if (StringUtils.isNotBlank(serialHoldStatus)){
					message.raiseError("MFI_20"," Cannot split serial no " + serialNo + " due to serial hold status of  "  +serialHoldStatus );
				}
			}
		}

	}


	public void validateInputParams(String serialNumberList, String newOrderNo, String partNo, String partChg,String planNo, String planId, Number planVer, Number planRev, Number planAlt) {
		

		if (StringUtils.isBlank(serialNumberList)){
			message.raiseError("MFI_20","Error Serial No cannot be null" );
		}
		if (StringUtils.isBlank(planId)){
			message.raiseError("MFI_20","Error planId cannot be null" );
		}
		if (planVer ==null){
			message.raiseError("MFI_20","Error planVer cannot be null" );
		}
		if (planRev ==null){
			message.raiseError("MFI_20","Error planRev cannot be null" );
		}
		if (planAlt ==null){
			message.raiseError("MFI_20","Error planAlt cannot be null" );
		}
		if (StringUtils.isBlank(partNo)){
			message.raiseError("MFI_20","Error partNo cannot be null" );
		}
		if (StringUtils.isBlank(partChg)){
			message.raiseError("MFI_20","Error partChg cannot be null" );
		}
		if (StringUtils.isBlank(planNo)){
			message.raiseError("MFI_20","Error planNo cannot be null" );
		}
	}

}
