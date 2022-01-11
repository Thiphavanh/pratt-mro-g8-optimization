
/*
 *  This unpublished work, first created in 2018 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2018.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    WidSubjectImplPW.java
 * 
 *  Created: 2018-05-01
 * 
 *  Author:  Bob Preston
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *	2018-05-01		B. Preston		Initial Release, SMRO_WOE_211, Defect 51.
 *  2018-08-14      R. Thorpe       SMRO_WOE_202 - Defect 850 
 *  2019-01-31      R. Thorpe       Removed System.out lines from defect 850
 *  2019-03-01 		B. Preston		SMRO_USR_203 - Defect 1005.
 *  2019-03-03      D.Miron         G8R2SP4 Upgrade - Added 7th argument to method call for includeExcludeWidSubject.
 *  2019-04-12 		B. Preston		SMRO_USR_203 - Defect 1005 -- Added priv check.
 *  2019-06-05      Fred Ettefagh   added method includeExcludeMultipleWidSubject to include/exclude sibling orders 
 *  2019-07-31      J. DeNinno      WE308 - add code to insert multiple task history
 *  2019-08-02      R. Thorpe       TR308 -added code to send T308 in authorizeAllWorkScopeOperations when order is REPAIR
*   2019-09-20      Bahram J.       SMRO_TR_308, changed xml to use synWorkOrder instead of syncRecord *
*   2019-09-20      Bahram J        SMRO_TR_308, added role to bypass t308 interface
*   2019-09-25      Fred Ettefagh   Removed call to T308 intereface    
*   2019-10-30      Fred Ettefagh   SMRO_TR_301, defecet 1395, changed code for multi orders task include/exclude, based on background order create    
*   2019-11-20      Fred Ettefagh   SMRO_TR_301, fixed bug for for multi orders task include/exclude, based on background order create
 *  2019-09-16      T. Phan         Defect 979 - Checks for other non-accepted statuses so the operation cannot be Excluded
 *  2020-02-06      B. Corson       Defect 979 - Fix check for statuses.
 *  2020-02-13      Fred Ettefagh   SMRO_TR_308, Defect 1474, changed code to send t308 message after certain changes for WO Alt and task include exclude
 *  2020-02-24      Fred Ettefagh   SMRO_TR_308, Defect 1474, added debug info 
 *  2020-02-24      Fred Ettefagh   SMRO_TR_308, Defect 1474, changed code to send t308 message after certain changes for WO Alt and task include exclude
 *  2020-02-26      Fred Ettefagh   SMRO_TR_308, Defect 1474, changed code to send t308 message after certain changes for WO Alt and task include exclude
 *  2020-04-01		G. Rodriguez	Defect 1597 - Unexpected java error occurs when excluding task groups.
 */

package com.pw.solumina.sfor.application.impl;

import static com.ibaset.common.FrameworkConstants.YES;
import static com.ibaset.common.SoluminaConstants.COMMA;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.StringUtils.defaultIfEmpty;
import static org.apache.commons.lang.StringUtils.isEmpty;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.solumina.IValidator;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sfor.application.IParse;
import com.ibaset.solumina.sfor.application.IWidSubject;
import com.ibaset.solumina.sfwid.application.IHold;
import com.ibaset.solumina.sfwid.dao.IHoldDao;
import com.pw.common.dao.CommonDaoPW;
import com.pw.common.utils.CommonUtilsPW;
import com.pw.solumina.T308.integration.impl.RepairOrderT308Interface;
import com.pw.solumina.sfor.application.IWidSubjectPW;
import com.pw.solumina.sfor.dao.AuditSubjectDaoPW;
import com.pw.solumina.sfwid.dao.WorkOrderDaoPW;

public abstract class WidSubjectImplPW extends ImplementationOf<IWidSubject> implements IWidSubject, IWidSubjectPW {
	protected final Log logger = LogFactory.getLog(WidSubjectImplPW.class);
	
	@Reference AuditSubjectDaoPW auditSubjectDaoPW;
	@Reference IParse parse = null;
	@Reference IMessage message = null;
    @Reference IEvent event;
    @Reference IHold hold = null;
    @Reference IHoldDao holdDao = null;
    @Reference IValidator validator = null; // Defect 1005.
    @Reference  WorkOrderDaoPW workOrderDaoPW;
	@Reference  CommonDaoPW commonDaoPW = null;
	@Reference  CommonUtilsPW commonUtilsPW;
	@Reference  RepairOrderT308Interface repairOrderT308Interface;
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void includeExcludeMultipleWidSubject(String orderId,
									             String subjectNumberList,
									             String includeExclude,
									             String notes,
									             String discrepancyId,
									             Number discrepancyLineNumber){

		boolean multipleRepairOrderTaskUpate = false;
		
		Map pwustSfwidOrderDescMap = workOrderDaoPW.selectPwustSfwidOrderDesc(orderId);
	   	if (null==pwustSfwidOrderDescMap){
	   		message.raiseError("MFI_20","Bad Order. Missing data in PWUST_SFWID_ORDER_DESC table");
	   	}
	   	String pwOrderType = (String) pwustSfwidOrderDescMap.get("PW_ORDER_TYPE");
		String orderStatus = (String) pwustSfwidOrderDescMap.get("ORDER_STATUS");
		String orderAltStatus = (String) pwustSfwidOrderDescMap.get("ALT_STATUS");
		
		String sapIDocNumber = (String) pwustSfwidOrderDescMap.get("UCF_ORDER_VCH15");
		BigDecimal numberOfBackGroundOrders = (BigDecimal) pwustSfwidOrderDescMap.get("UCF_ORDER_NUM5");
		List<Map> repairOrderList = commonDaoPW.selectsfwidOrderDescMutilOrdersList(sapIDocNumber);

			if (StringUtils.equalsIgnoreCase(pwOrderType, "REPAIR")){
				
				multipleRepairOrderTaskUpate = repairMultiOrderCheck(orderId, 
						                                             sapIDocNumber, 
						                                             numberOfBackGroundOrders, 
						                                             repairOrderList);
				
				repairOrderSaveT308Data(orderId,orderStatus,orderAltStatus);
			}
	
		calloobToIncludeExcludeMultipleWidSubject(orderId, 
				                               subjectNumberList, 
				                               includeExclude, 
				                               notes, 
				                               discrepancyId, 
				                               discrepancyLineNumber);

		if (multipleRepairOrderTaskUpate){
				repairOrderUpdateMultiTask(repairOrderList,subjectNumberList, includeExclude, notes, discrepancyId,discrepancyLineNumber);
  		}
	 }

	public void calloobToIncludeExcludeMultipleWidSubject(String orderId, 
			                                              String subjectNumberList,
			                                              String includeExclude, 
			                                              String notes, 
			                                              String discrepancyId, 
			                                              Number discrepancyLineNumber) {

		Super.includeExcludeMultipleWidSubject(orderId, 
 							                   subjectNumberList, 
								               includeExclude, 
								               notes, 
								               discrepancyId, 
								               discrepancyLineNumber);


		/*WE308 7/31/19 JPD*/
		auditSubjectDaoPW.insertIncludeExcludeMultiWidSubject(orderId, subjectNumberList, includeExclude, notes, discrepancyId,discrepancyLineNumber);
		
	}

	public void repairOrderSaveT308Data(String orderId, String orderStatus, String orderAltStatus) {
		
		
		System.out.println("WidSubjectImplPW.repairOrderSaveT308Data");
		
		System.out.println("in  WidSubjectImplPW.repairOrderSaveT308Data orderStatus = " + orderStatus);
		System.out.println("in WidSubjectImplPW.repairOrderSaveT308Data orderAltStatus = " + orderAltStatus);
		if (StringUtils.equalsIgnoreCase(orderStatus, "IN QUEUE") || StringUtils.equalsIgnoreCase(orderStatus, "ACTIVE")) {
			
			// if order is not in alt mode and an there are no open work scope holds that are open then
			// need to save order data for Possible  sync with sap via t308
			if (StringUtils.isBlank(orderAltStatus)){
				
				List sfwidHoldsList = commonDaoPW.selectSfwidHoldInfoV(orderId, "OPEN", "WORK SCOPE HOLD");
				
				if (sfwidHoldsList==null || sfwidHoldsList.isEmpty()){
					
					System.out.println("in WidSubjectImplPW.repairOrderSaveT308Data sfwidHoldsList.size() = " + sfwidHoldsList.size());
					commonUtilsPW.deleteRepairOrderAltT308Data(orderId);
					commonUtilsPW.saveRepairOrderAltT308Data(orderId);
				}
			}
		}
		
	}

	public void repairOrderUpdateMultiTask(List<Map> repairOrderList,
										   String subjectNumberList,
								           String includeExclude,
								           String notes,
								           String discrepancyId,
								           Number discrepancyLineNumber) {
		
		if (repairOrderList!=null && !repairOrderList.isEmpty()){
			
				for (Map orderMap : repairOrderList) {
					
					String nextOrderId = (String)orderMap.get("ORDER_ID");
					String nextOrderStatus = (String)orderMap.get("ORDER_STATUS");
					if (StringUtils.equalsIgnoreCase(nextOrderStatus, "PENDING")){
						
						calloobToIncludeExcludeMultipleWidSubject( nextOrderId, 
									                               subjectNumberList, 
									                               includeExclude, 
									                               notes, 
									                               discrepancyId, 
									                               discrepancyLineNumber);
						
					}
				}
		}
		
	}

	public boolean repairMultiOrderCheck(String orderId, String sapIDocNumber, BigDecimal numberOfBackGroundOrders, List<Map> repairOrderList) {
		
		boolean multipleRepairOrderTaskUpate = false;
		
		if (numberOfBackGroundOrders != null){//Defect 1597
			
			if (StringUtils.isNotBlank(sapIDocNumber) && numberOfBackGroundOrders != null){//Defect 1597
  					
  					multipleRepairOrderTaskUpate = true;
  						
  					int totalNumberOfMultiOrders =numberOfBackGroundOrders.intValue();
  					System.out.println("in WidSubjectImplPW.repairMultiOrderCheck, repairOrderCheck = " + totalNumberOfMultiOrders);
  					System.out.println("in WidSubjectImplPW.repairMultiOrderCheck, repairOrderCheck repairOrderList.size() = " + repairOrderList.size());
  					if (totalNumberOfMultiOrders!=0){
  						if (totalNumberOfMultiOrders!=repairOrderList.size()){
  							message.raiseError("MFI_20","Cannot include or exclude tasks, all background orders have not been created yet");
  						}
 					}
  					
  			}
		}
		return  multipleRepairOrderTaskUpate;
		
	}

	public void includeExcludeWidSubject(String orderId, Number subjectNumber, String includeExclude, String notes,	String discrepancyId, Number discrepancyLineNo) {

		Super.includeExcludeWidSubject( orderId, 
						                subjectNumber, 
						                includeExclude, 
						                notes, 
						                discrepancyId, 
						                discrepancyLineNo,
						                false); // G8R2SP4	
		
		auditSubjectDaoPW.insertIncludeExcludeWidSubject(orderId, subjectNumber, includeExclude, notes, discrepancyId,discrepancyLineNo);
	}

	/* (non-Javadoc)
	 * @see com.pw.solumina.sfor.application.impl.IWidSubjectPW#includeExcludeMultOper(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Number, java.lang.String)
	 */
	@Override
	public void includeExcludeMultOp(String orderId, String operationKeyList, String includeExclude, String calledFrom, String notes, String discrepancyId, Number discrepancyLineNumber, String stepKey){
		
		
		System.out.println("WidSubjectImplPW.includeExcludeMultOp ");
		boolean multipleRepairOrderOperUpdate = false;
		
		Map pwustSfwidOrderDescMap = workOrderDaoPW.selectPwustSfwidOrderDesc(orderId);
	   	if (null==pwustSfwidOrderDescMap){
	   		message.raiseError("MFI_20","Bad Order. Missing data in PWUST_SFWID_ORDER_DESC table");
	   	}
	   	String pwOrderType = (String) pwustSfwidOrderDescMap.get("PW_ORDER_TYPE");
		String orderStatus = (String) pwustSfwidOrderDescMap.get("ORDER_STATUS");
		String orderAltStatus = (String) pwustSfwidOrderDescMap.get("ALT_STATUS");
		
		String sapIDocNumber = (String) pwustSfwidOrderDescMap.get("UCF_ORDER_VCH15");
		BigDecimal numberOfBackGroundOrders = (BigDecimal) pwustSfwidOrderDescMap.get("UCF_ORDER_NUM5");
		List<Map> repairOrderList = commonDaoPW.selectsfwidOrderDescMutilOrdersList(sapIDocNumber);

		System.out.println("WidSubjectImplPW.includeExcludeMultOp pwOrderType= " + pwOrderType);
		if (StringUtils.equalsIgnoreCase(pwOrderType, "REPAIR")){
			multipleRepairOrderOperUpdate = repairMultiOrderCheck(orderId, sapIDocNumber, numberOfBackGroundOrders, repairOrderList);
			System.out.println("in WidSubjectImplPW.includeExcludeMultOp, multipleRepairOrderOperUpdate= " + multipleRepairOrderOperUpdate);
			repairOrderSaveT308Data(orderId, orderStatus, orderAltStatus);
		}
		
		
		//Begin Defect 850
		if (StringUtils.isNotEmpty(operationKeyList)){
			
			// Gets the Operation Numbers in an array.
			String[] opNumPartial = parse.parse(operationKeyList, COMMA, YES);
			for (String i : opNumPartial){
				
				boolean isPartialed = auditSubjectDaoPW.checkForPartialBuyoffOnOperation(orderId, NumberUtils.createNumber(i));
				if (isPartialed){
					message.raiseError("MFI_20", "The Operation has a PARTIAL Buyoff and cannot be Excluded" );
				}
			
				//Defect 979
				boolean hasOpenOrders = auditSubjectDaoPW.checkOpenOrders(orderId,NumberUtils.createNumber(i));
				if (hasOpenOrders){
					message.raiseError("MFI_20", "The Operation has NA/ND/Training buyoff completed on it and cannot be excluded" );
				}
			}
			//End Defect 979
		}
		//End Defect 850

		oobCallToIncludeExcludeMultipleOperation(orderId, 
				                                 operationKeyList, 
				                                 includeExclude, 
				                                 calledFrom, 
				                                 notes, 
				                                 discrepancyId,	
				                                 discrepancyLineNumber, 
				                                 stepKey);
		
		if (multipleRepairOrderOperUpdate){
			
			repairOrderUpdateOper(repairOrderList, 
					              operationKeyList, 
					              includeExclude, 
					              calledFrom, 
					              notes, 
					              discrepancyId, 
					              discrepancyLineNumber, 
					              stepKey);   
		}
		
	}

	public void oobCallToIncludeExcludeMultipleOperation( String orderId, 
			                                              String operationKeyList, 
			                                              String includeExclude,
		                     	                          String calledFrom, 
		                     	                          String notes, 
		                     	                          String discrepancyId, 
		                     	                          Number discrepancyLineNumber, 
		                     	                          String stepKey) {
		
		Super.includeExcludeMultipleOperation(orderId, operationKeyList, includeExclude, calledFrom, notes,	discrepancyId, discrepancyLineNumber);

		// If Operation Key List is not empty.
		if (StringUtils.isNotEmpty(operationKeyList)) {
			
			// Gets the Operation Numbers in an array.
			String[] operationNumber = parse.parse(operationKeyList, COMMA, YES);
			for (String i : operationNumber) {
				auditSubjectDaoPW.insertIncludeExcludeOperation(orderId, NumberUtils.createNumber(i),includeExclude, notes, discrepancyId, discrepancyLineNumber, stepKey);
			}
		}
	}
	
	
	public void repairOrderUpdateOper(List<Map> repairOrderList,
			                          String operationKeyList, 
			                          String includeExclude, 
			                          String calledFrom, 
			                          String notes, 
			                          String discrepancyId, 
			                          Number discrepancyLineNumber, 
			                          String stepKey) {

		if (repairOrderList!=null && !repairOrderList.isEmpty()){

			for (Map orderMap : repairOrderList) {

				String nextOrderId = (String)orderMap.get("ORDER_ID");
				String nextOrderStatus = (String)orderMap.get("ORDER_STATUS");
				if (StringUtils.equalsIgnoreCase(nextOrderStatus, "PENDING")){

					oobCallToIncludeExcludeMultipleOperation(nextOrderId, 
							                                 operationKeyList, 
							                                 includeExclude, 
							                                 calledFrom, 
							                                 notes,
							                                 discrepancyId, 
							                                 discrepancyLineNumber, 
							                                 stepKey);
					
				}
			}

		}
	}
	
	
    // Begin defect 1005.
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ibaset.solumina.sfor.application.ISfwidUndo#authorizeAllWorkScopeOperations(java.lang.
     * String, java.lang.String)
     */
	   public void authorizeAllWorkScopeOperations(String orderId, String notes){
		   
	        List pwSfwidOrderDescList =commonDaoPW.selectPWOrderDataByOrderId(orderId);
    	    if (pwSfwidOrderDescList!=null && !pwSfwidOrderDescList.isEmpty()){
    	    	
    	    	 Map pwOrderDataMap = (Map) pwSfwidOrderDescList.iterator().next();
    	    	 String pwOrderType = (String) pwOrderDataMap.get("PW_ORDER_TYPE");
    	    	 
    	    	 if (StringUtils.equalsIgnoreCase(pwOrderType, "REPAIR")){
    	    		 
    	    		String orderStatus = (String)pwOrderDataMap.get("ORDER_STATUS");
    	    		String altStatus = (String)pwOrderDataMap.get("ALT_STATUS");
    	    		if (StringUtils.isBlank(altStatus)){
    	    			
    	 	    		if (StringUtils.isBlank(altStatus)){
    	 	    			if (StringUtils.equals(orderStatus, "IN QUEUE") || StringUtils.equals(orderStatus, "ACTIVE")){

    	 	    				List sfwidHoldsList =commonDaoPW.selectSfwidHoldsInfo(orderId, "WORK SCOPE HOLD", "OPEN");
    	 	    				if (sfwidHoldsList!=null && !sfwidHoldsList.isEmpty()){
    	 	    					// interface with SAP T308
    	 	    					if (commonUtilsPW.sendRepairT308()){
    	 	    						repairOrderT308Interface.sendOrderT308Msg(orderId,"closeWorkScopeHold");
    	 	    					}
    	 	    				}
    	 	    			}
    	 		    	}
    	 			}
				}
   	    	 }
    	    
    	    String userName = ContextUtil.getUsername();
    	    
	        try{
	        	
	            // if there is an open hold for work scope
	            Map holdInfo = holdDao.selectOrderWorkScopeHold(orderId);
	            String holdId = (String) holdInfo.get("HOLD_ID");
	            
	            // check priv
	            validator.checkUserPrivilege("WORK_SCOPE_AUTHORIZATION");
	            
	            StringBuffer returnNotes = new StringBuffer();
	            returnNotes.append("Approved Scope Changes").append(" - ").append(userName);
	            if (!isEmpty(notes)){
	            	
	                returnNotes.append(" - ").append(defaultIfEmpty(notes, EMPTY));
	            }
            	hold.closeHold(orderId, null, holdId, returnNotes.toString());
            	
	          event.refreshTool(); 
	        }
	        // if there is no hold
	        catch (EmptyResultDataAccessException erdae)
	        {}
	    }
	    // End defect 1005.
}
