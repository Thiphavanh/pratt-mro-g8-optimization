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
 *  File:    OrderImplSfwidPW.java
 * 
 *  Created: 2017-08-30
 * 
 *  Author:  Rusty Cannon
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2017-08-30		R. Cannon		Initial Release SMRO_EXPT_205 - assembleText method created
 *  2018-04-05		Fred Ettefagh   Defect 577
 *  2018-04-06      Fred Ettefagh   Defect 44
 *  2018_05_07      Fred Ettefagh   Defect 577 changed method updateOverHaulOrderOperConfirmData to skip conf_no update/check if oper_no was added in WO alt and is not found in the plan
 *  2018-05-17      Fred Ettefagh   defect 760 check/update oper conf no for sub order and Supplemental Order 
 *  2018-05-29      Fred Ettefagh   defect 813 Changed code for multi order release to commit for each order release 
 *  2018-06-05      S.Niu   		defect 468 Create Sub Orders Priv and Supplemental Orders Priv
 *  2018-08-14		Fred Ettefagh	Defect 939 Multiple Order release - change to commit after each order complete
 *  2018-10-01      F. Ettefagh     Defect 980 Cannot Create Suborder due to supNet subNet and conf Numbers
 *  2018-10-19		Bob Preston		SMRO_USR_203, Defect 468 - Don't check for ORDER_RELEASE priv. for Supp. and Sub Orders. 
 *  2019-07_03      Fred Ettefagh   PW_SMRO_WOE_310, added method copyOrder and moved class to a new package
 *  2019-07_18      Fred Ettefagh   Defect 1327, rename file from OrderImplPW to OrderImplSfwidPW to fix upgrade issues
 *  2019-08-19		T. Phan		    SMRO_eWORK_202 Defect 1364 - Added confirmation number to released orders
 *  2019-09-19		Fred Ettefagh   removed code check in by T. Phan for defect 1364
 *  2020-02-27      Fred Ettefagh   SMRO_TR_308, Defect 1474, added code to save t308 data when user starts operation alteration 
 *  2021-03-15      Brendan Polak   1848, added serial_no to selectOperDatColInfo
 */
package com.ibaset.solumina.sfwid.application.impl;

import static com.ibaset.common.DbColumnNameConstants.DAT_COL_UOM;
import static com.ibaset.common.DbColumnNameConstants.LOWER_LIMIT;
import static com.ibaset.common.DbColumnNameConstants.TARGET_VALUE;
import static com.ibaset.common.DbColumnNameConstants.UPPER_LIMIT;
import static com.ibaset.common.SoluminaConstants.ATTENTION;
import static com.ibaset.common.SoluminaConstants.ORDER;
import static com.ibaset.common.SoluminaConstants.Y;
import static com.ibaset.common.util.SoluminaUtils.parseDateGeneralToLocaleSpecific;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang.StringUtils;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.solumina.IValidator;//Defect 468
import com.ibaset.solumina.sfbis.dao.ServiceDefinitionDao;
import com.ibaset.solumina.sfcore.application.ILicense;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sfcore.application.IUser;
import com.ibaset.solumina.sffnd.application.IAutoNumberGenerator;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.application.IHistoryControl;
import com.ibaset.solumina.sffnd.application.IParse;
import com.ibaset.solumina.sffnd.application.IWrapUtils;
import com.ibaset.solumina.sfor.application.IWidSubject;
import com.ibaset.solumina.sfpl.application.IBom;
import com.ibaset.solumina.sfpl.application.IStep;
import com.ibaset.solumina.sfpl.dao.IBomDao;
import com.ibaset.solumina.sfpl.dao.ICDCVariableDao;
import com.ibaset.solumina.sfwid.application.IInheritSecurityGroup;
import com.ibaset.solumina.sfwid.application.IOrder;
import com.ibaset.solumina.sfwid.application.IOrderDetails;
import com.ibaset.solumina.sfwid.application.IWorkPackage;
import com.ibaset.solumina.sfwid.dao.ICallDao;
import com.ibaset.solumina.sfwid.dao.IOrderDao;
import com.pw.common.dao.CommonDaoPW;
import com.pw.common.utils.CommonUtilsPW;
import com.pw.solumina.sfor.application.SfwidUndoPW;
import com.pw.solumina.sfwid.dao.OrderDaoPW;
import com.pw.solumina.sfwid.dao.WorkOrderDaoPW;
import com.pw.solumina.sfwid.impl.OrderOperConfirmationCheck;

public abstract class OrderImplSfwidPW extends ImplementationOf<IOrder> implements IOrder
{
	@Reference	IWidSubject widSubjectImpl;
	@Reference	private WorkOrderDaoPW workOrderDaoPW;
	@Reference	private IMessage message = null;
	@Reference	private IUser user = null;
	@Reference	private IOrderDao orderDao = null;
	@Reference	private IBomDao mbomDao = null;
	@Reference	private IBom bom = null;
    @Reference	private OrderOperConfirmationCheck orderOperConfirmationCheck;
	@Reference	private IValidator validator = null; //Defect 468
	@Reference  private CommonUtilsPW commonUtilsPW;
    @Reference  private SfwidUndoPW sfwidUndoPW = null; // Defect 468
    @Reference	IHistoryControl historyControl = null;
    @Reference  com.ibaset.solumina.sfpl.dao.IOrderDao sfplOrderDao = null;
	@Reference  IInheritSecurityGroup inheritSecurityGroup;
	@Reference  IWorkPackage workPackage = null;
	@Reference  IOrderDetails orderDetails = null;
	@Reference  ICallDao callDao = null;
	@Reference  ILicense license = null;
	@Reference  ServiceDefinitionDao serviceDefinitionDao = null;
	@Reference  IAutoNumberGenerator orderNumberGenerator = null;
	@Reference  IWrapUtils wrapUtils = null;
	@Reference  IEvent event = null;
	@Reference  CommonDaoPW commonDaoPW = null;
	@Reference  IParse parse = null;
	@Reference  OrderDaoPW orderDaoPW = null;
	@Reference  ICDCVariableDao cdcVariableDao = null;
	@Reference  IStep step = null;

	
	String errorMsg ="The data used to create the Orders does not match the incoming Sales Order's information.  "+System.lineSeparator() 
                    +"The Orders must be deleted and recreated to match the Sales Order's information before Orders can be released.";
	
	
	
	
	 public void startAlteration(String orderId,
							    String alterationReason,
							    String changeAuthorizationType,
							    String alterationNo,
							    String calledFrom,
							    String ucfOrderAlterationVch1,
							    String ucfOrderAlterationVch2,
							    String ucfOrderAlterationVch3,
							    String ucfOrderAlterationVch4,
							    String ucfOrderAlterationVch5,
							    String ucfOrderAlterationVch6,
							    String ucfOrderAlterationVch7,
							    String ucfOrderAlterationVch8,
							    String ucfOrderAlterationVch9,
							    String ucfOrderAlterationVch10,
							    String ucfOrderAlterationVch11,
							    String ucfOrderAlterationVch12,
							    String ucfOrderAlterationVch13,
							    String ucfOrderAlterationVch14,
							    String ucfOrderAlterationVch15,
							    Number ucfOrderAlterationNum1,
							    Number ucfOrderAlterationNum2,
							    Number ucfOrderAlterationNum3,
							    Number ucfOrderAlterationNum4,
							    Number ucfOrderAlterationNum5,
							    String ucfOrderAlterationFlag1,
							    String ucfOrderAlterationFlag2,
							    String ucfOrderAlterationFlag3,
							    String ucfOrderAlterationFlag4,
							    String ucfOrderAlterationFlag5,
							    Date ucfOrderAlterationDate1,
							    Date ucfOrderAlterationDate2,
							    Date ucfOrderAlterationDate3,
							    Date ucfOrderAlterationDate4,
							    Date ucfOrderAlterationDate5,
							    String alterationType,
							    String operationList,
							    Number operationKey,
							    String discrepancyId,
							    Number discrepancyLineNumber,
							    String objectId, 
							    String workFlow,
							    String ucfOrderAlterationVch2551,
							    String ucfOrderAlterationVch2552,
							    String ucfOrderAlterationVch2553,
							    String ucfOrderAlterationVch40001,
							    String ucfOrderAlterationVch40002,
							    String releasePackageId, // FND-24168
								String associateChange){ // FND-25795
	 
		 	Super.startAlteration(orderId, 
		 			              alterationReason, 
		 			              changeAuthorizationType, 
		 			              alterationNo, 
		 			              calledFrom, 
		 			              ucfOrderAlterationVch1, 
		 			              ucfOrderAlterationVch2, 
		 			              ucfOrderAlterationVch3, 
		 			              ucfOrderAlterationVch4, 
		 			              ucfOrderAlterationVch5, 
		 			              ucfOrderAlterationVch6, 
		 			              ucfOrderAlterationVch7, 
		 			              ucfOrderAlterationVch8, 
		 			              ucfOrderAlterationVch9, 
		 			              ucfOrderAlterationVch10, 
		 			              ucfOrderAlterationVch11, 
		 			              ucfOrderAlterationVch12, 
		 			              ucfOrderAlterationVch13, 
		 			              ucfOrderAlterationVch14, 
		 			              ucfOrderAlterationVch15, 
		 			              ucfOrderAlterationNum1, 
		 			              ucfOrderAlterationNum2,
		 			              ucfOrderAlterationNum3, 
		 			              ucfOrderAlterationNum4, 
		 			              ucfOrderAlterationNum5, 
		 			              ucfOrderAlterationFlag1, 
		 			              ucfOrderAlterationFlag2, 
		 			              ucfOrderAlterationFlag3, 
		 			              ucfOrderAlterationFlag4, 
		 			              ucfOrderAlterationFlag5, 
		 			              ucfOrderAlterationDate1, 
		 			              ucfOrderAlterationDate2, 
		 			              ucfOrderAlterationDate3,
		 			              ucfOrderAlterationDate4, 
		 			              ucfOrderAlterationDate5, 
		 			              alterationType, 
		 			              operationList, 
		 			              operationKey, 
		 			              discrepancyId,
		 			              discrepancyLineNumber,
		 			              objectId, 
		 			              workFlow, 
		 			              ucfOrderAlterationVch2551, 
		 			              ucfOrderAlterationVch2552,
		 			              ucfOrderAlterationVch2553, 
		 			              ucfOrderAlterationVch40001, 
		 			              ucfOrderAlterationVch40002, 
		 			              releasePackageId, 
		 			              associateChange);
		 	
		 	
		 	Map pwustSfwidOrderDescMap = workOrderDaoPW.selectPwustSfwidOrderDesc(orderId);
	    	if (null==pwustSfwidOrderDescMap){
	    		message.raiseError("MFI_20","Bad Order. Missing data in PWUST_SFWID_ORDER_DESC table");
	    	}
	    	
	   		String pwOrderType = (String) pwustSfwidOrderDescMap.get("PW_ORDER_TYPE");
	   		String orderStatus = (String) pwustSfwidOrderDescMap.get("ORDER_STATUS");
	   		String orderHoldStatus = (String) pwustSfwidOrderDescMap.get("ORDER_HOLD_STATUS");
	   		
		 	 if (StringUtils.equalsIgnoreCase(pwOrderType, "REPAIR") &&
		        !StringUtils.equalsIgnoreCase(orderStatus, "PENDING") &&
		        !StringUtils.equalsIgnoreCase(orderHoldStatus, "OPER STOP")){
		         	
		         	List sfwidHoldsList =commonDaoPW.selectSfwidHoldsInfo(orderId, "WORK SCOPE HOLD", "OPEN");
		         	if (sfwidHoldsList ==null){
		             	commonUtilsPW.deleteRepairOrderAltT308Data(orderId);
		     			commonUtilsPW.saveRepairOrderAltT308Data(orderId);
		         	}else if (sfwidHoldsList.isEmpty()){
		             	commonUtilsPW.deleteRepairOrderAltT308Data(orderId);
		     			commonUtilsPW.saveRepairOrderAltT308Data(orderId);
		         	}else if (sfwidHoldsList.size()==0){
		             	commonUtilsPW.deleteRepairOrderAltT308Data(orderId);
		     			commonUtilsPW.saveRepairOrderAltT308Data(orderId);
		         	}
		         }		 	
		 	
	 }
	
	
	 public String copyOrder(String parentOrderId,
				             String orderNumber,
				             Number splitQuantity,
				             String owpId,
				             String calledFrom){
		 
		 String newOrderId = null;
		 newOrderId = Super.copyOrder(parentOrderId, orderNumber, splitQuantity, owpId, calledFrom);			 
		 commonUtilsPW.insertIntoPwustSfwidOrderDesc( orderNumber,
										              null, //pwOrderNo,
													  newOrderId,
													  null, //superNetPW, 
													  null, //subNetPW, 
													  null, //workScopePW, 
													  null, //customerPW, 
													  null, //salesOrder, 
													  null, //engineModel,
													  null);//orderType);
		 return newOrderId;
	 }

	// defect 980
    @SuppressWarnings("rawtypes")
	public void releaseOrder(String orderId, String sendLtaFlag){
    	
    	String pwOrderType = commonUtilsPW.getOrderTypePW(orderId);
    	String salesOrder = null;
    	
    	if ("OVERHAUL".equalsIgnoreCase(pwOrderType) || 
    	    "SUB ORDER".equalsIgnoreCase(pwOrderType) || 
    		"SUPPLEMENTAL".equalsIgnoreCase(pwOrderType)){
    		
	    	if (!user.hasPrivilege("PWUST_LONOTE_BYPASS")){
	
			       		if ("SUB ORDER".equalsIgnoreCase(pwOrderType)) {
			    			validator.checkUserPrivilege("PWUST_SUB_ORDER_REL"); //Defect 468
			    		}
			        	
			    		if ("SUPPLEMENTAL".equalsIgnoreCase(pwOrderType)) {
			    			validator.checkUserPrivilege("PWUST_SUPPLEM_ORDER_REL"); //Defect 468
			    		}
		
			    		Map pwustSfwidOrderDescMap = workOrderDaoPW.selectPwustSfwidOrderDesc(orderId);
			        	if (null==pwustSfwidOrderDescMap){
			        		message.raiseError("MFI_20","Bad Order. Missing data in PWUST_SFWID_ORDER_DESC table");
			        	}
			
			    		//String pwOrderType = (String) pwustSfwidOrderDescMap.get("PW_ORDER_TYPE");	
			        	salesOrder = (String) pwustSfwidOrderDescMap.get("SALES_ORDER");
			        	
			        	String engineModel = (String) pwustSfwidOrderDescMap.get("ENGINE_MODEL");
			        	String engineType = (String) pwustSfwidOrderDescMap.get("PROGRAM");
			        	String workLoc = (String) pwustSfwidOrderDescMap.get("WORK_LOC");
			        	String planId = (String) pwustSfwidOrderDescMap.get("PLAN_ID");
			        	Number planUpdtNo= (Number) pwustSfwidOrderDescMap.get("PLAN_UPDT_NO");
		    			
		        		updateOperConfirmationData(orderId,pwOrderType,salesOrder,engineType,engineModel,workLoc,planId,planUpdtNo);	
		        		checkOrderOperConfNumber(orderId, salesOrder, engineType, engineModel, workLoc);
	    		}
   		}
    	
    	// Begin defect 468
    	if ("SUPPLEMENTAL".equalsIgnoreCase(pwOrderType) ||
    		"SUB ORDER".equalsIgnoreCase(pwOrderType)) {
    			sfwidUndoPW.releaseOrderNoCheckPriv(orderId, sendLtaFlag);
    	} else {
    			Super.releaseOrder(orderId, sendLtaFlag);
    	}
    	// End defect 468
    	
    	
    }
	
 // defect 980
    @SuppressWarnings("rawtypes")
	public void releaseOrderAndRefreshTool(String orderId, String sendLtaFlag){
    	
    	String pwOrderType = commonUtilsPW.getOrderTypePW(orderId);
    	String salesOrder = null;

    	if ("OVERHAUL".equalsIgnoreCase(pwOrderType) || 
            "SUB ORDER".equalsIgnoreCase(pwOrderType) || 
        	"SUPPLEMENTAL".equalsIgnoreCase(pwOrderType)){
    		
    			if (!user.hasPrivilege("PWUST_LONOTE_BYPASS")){
			       		if ("SUB ORDER".equalsIgnoreCase(pwOrderType)) {
			    			validator.checkUserPrivilege("PWUST_SUB_ORDER_REL"); //Defect 468
			    		}
			        	
			    		if ("SUPPLEMENTAL".equalsIgnoreCase(pwOrderType)) {
			    			validator.checkUserPrivilege("PWUST_SUPPLEM_ORDER_REL"); //Defect 468
			    		}
			    		
			    		Map pwustSfwidOrderDescMap = workOrderDaoPW.selectPwustSfwidOrderDesc(orderId);
			        	if (null==pwustSfwidOrderDescMap){
			        		message.raiseError("MFI_20","Bad Order. Missing data in PWUST_SFWID_ORDER_DESC table");
			        	}
			
			    		//String pwOrderType = (String) pwustSfwidOrderDescMap.get("PW_ORDER_TYPE");	
			        	salesOrder = (String) pwustSfwidOrderDescMap.get("SALES_ORDER");
			        	String engineModel = (String) pwustSfwidOrderDescMap.get("ENGINE_MODEL");
			        	String engineType = (String) pwustSfwidOrderDescMap.get("PROGRAM");
			        	String workLoc = (String) pwustSfwidOrderDescMap.get("WORK_LOC");
			        	String planId = (String) pwustSfwidOrderDescMap.get("PLAN_ID");
			        	Number planUpdtNo= (Number) pwustSfwidOrderDescMap.get("PLAN_UPDT_NO");
			        	
		        		updateOperConfirmationData(orderId,pwOrderType,salesOrder,engineType,engineModel,workLoc,planId,planUpdtNo);	
		        		checkOrderOperConfNumber(orderId, salesOrder, engineType, engineModel, workLoc);
    			}
        }
    	
		this.Super.releaseOrderAndRefreshTool(orderId, sendLtaFlag);
		
    }


    // defect 980
	public void updateOperConfirmationData(String orderId,String pwOrderType,String salesOrder,String  engineType,String engineModel,String workLoc,String planId,Number planUpdtNo) {
		
    		
    		if ("OVERHAUL".equalsIgnoreCase(pwOrderType)){
    			orderOperConfirmationCheck.updateConfNumberForOverHaulOrders(orderId, salesOrder, engineType, engineModel, workLoc, planId, planUpdtNo);
    		}
    		if ("SUB ORDER".equalsIgnoreCase(pwOrderType)){
    			orderOperConfirmationCheck.updateConfNumberForSubOrders(orderId, salesOrder, engineType, engineModel, workLoc);
    		}
    		
    		if ("SUPPLEMENTAL".equalsIgnoreCase(pwOrderType)){
    			orderOperConfirmationCheck.updateConfNumberForSupplementalOrder( orderId,  salesOrder,  engineType,engineModel, workLoc, planId,  planUpdtNo);
    		}
	}
    
	// defect 980
	public void checkOrderOperConfNumber(String orderId,String salesOrder, String engineType, String engineModel, String workLoc){
		
		String operListWithOutConfNumber = orderOperConfirmationCheck.checkOrderOperationConfirmationData(orderId);
		
		if (StringUtils.isNotBlank(operListWithOutConfNumber)){
    		message.raiseError("MFI_20",errorMsg + System.lineSeparator() +
					                    "sales order = " + salesOrder + System.lineSeparator() +
					                    "engine Type = " + engineType + System.lineSeparator() +
					                    "engine Model = " + engineModel + System.lineSeparator() +
					                    "workLoc = " + workLoc + System.lineSeparator() +  System.lineSeparator() +
					                    operListWithOutConfNumber);
			
		}
	}
	
	
    public void updateOrderWorkFlowPre( String taskId,
							            String toTaskType,
							            String orderAlterStatus,
							            String toStatus,
							            String notes,
							            Number blockEdit,
							            String orderId,
							            String calledFrom,
							            String alterationId,
							            String calledFrmDialog )
    {
    	this.Super.updateOrderWorkFlowPre(taskId, 
						    			toTaskType, 
						    			orderAlterStatus, 
						    			toStatus, 
						    			notes, 
						    			blockEdit, 
						    			orderId, 
						    			calledFrom, 
						    			alterationId, 
						    			calledFrmDialog);
    	
    	if (StringUtils.equals(orderAlterStatus, "ORDER_AUTHORING")) {
            widSubjectImpl.authorizeAllWorkScopeOperations(orderId, notes);
    	}
    	
    }
    
    
    // defect 44
    @SuppressWarnings("rawtypes")
	public List getOrderPartInfoForTab(String orderId,
    		                           String groupJobNo,
			                           String whereClause){
    	Number operationKey = null;
    	if(isNotEmpty(groupJobNo))
    	{
    		List list = orderDao.selectGroupJobSerials(groupJobNo);
    		if(list !=null && list.size() > 0)
    		{
    			Map row = (Map) list.get(0);
    			orderId = (String) row.get("ORDER_ID");
    			operationKey = (Number) row.get("OPER_KEY");
    		}
    	}
    	String userId = ContextUtil.getUsername(); 
    	List partInfo = workOrderDaoPW.selectOrderPartInfoForTab(orderId, userId, operationKey, whereClause);    	
    	return getOrderPartInfoForTab(orderId,partInfo);
    }
    
    // defect 44
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List getOrderPartInfoForTab(String orderId, List partInfo){

    		List returnList = new ArrayList();

    		Map allBomComponents = null;
    		int cntr = 0;

    		ListIterator it = partInfo.listIterator();
    		while (it.hasNext()){
    			Map map = (Map) it.next();
    			if(cntr==0){
    				allBomComponents = mbomDao.getAllBomComponentsForYield((String) map.get("BOM_ID"));
    				cntr++;
    			}
    			if(Y.equals((String)map.get("COMP_PART_OBSOLETE_FLAG"))){
    				map.put("PART_STATUS_DISPLAY", ATTENTION);
    				map.put("MSG", message.buildMessage(EMPTY, "MFI_EBEEC224841F4DF681FB1F484687C9CB"));
    			}
    			else{ 
    				if(isNotEmpty((String) map.get("BOM_COMP_ID"))){
						
						bom.addYieldMessage( map,
								             allBomComponents,
								             ORDER,
								             null,
								             null,
								             null,
								             null,
								             null,
								             orderId);
						if(isNotEmpty((String)map.get("MSG"))){
							map.put( "MSG", message.buildMessage(EMPTY, (String)map.get("MSG")));
						}
    				}else{
    					map.put("PART_STATUS_DISPLAY", EMPTY);
    					map.put("MSG", EMPTY);
    				}
    			}
    			map.put( "ORDER_ID", orderId );
    			returnList.add(map);
    		}
    		if (returnList.isEmpty()){
    			returnList.add(getPartRecordMetaDataTab());
    		}
    		return returnList;
    }
    
    // defect 44
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getPartRecordMetaDataTab()
    {
    	Map map = new HashMap();
    	
    	map.put("OPER_NO", null);
    	map.put("OPER_KEY", null);
    	map.put("REF_DES", null);
    	map.put("REF_DES_PREF_RANK", null);
    	map.put("STEP_KEYPART_NO", null);
    	map.put("ITEM_TYPE", null);
    	map.put("ITEM_SUBTYPE", null);
    	map.put("PART_LINE_NO", null);
    	map.put("PART_NO", null);
    	map.put("PART_CHG", null);
    	map.put("PART_TITLE", null);
    	map.put("PART_DAT_COL_ID", null);
    	map.put("PLND_ITEM_ID", null);
    	map.put("PLND_ITEM_QTY", null);
    	map.put("PART_TITLEUOM", null);
    	map.put("SERIAL_FLAG", null);
    	map.put("LOT_FLAG", null);
    	map.put("EXP_FLAG", null);
    	map.put("SPOOL_FLAG", null);
    	map.put("FIND_NO", null);
    	map.put("BOM_LINE_NO", null);
    	map.put("ITEM_NOTES", null);
    	map.put("PART_ACTION", null);
    	map.put("OPT_DC1_FLAG", null);
    	map.put("OPT_DC2_FLAG", null);
    	map.put("OPT_DC3_FLAG", null);
    	map.put("OPT_DC4_FLAG", null);
    	map.put("UCF_PLAN_ITEM_VCH1", null);
    	map.put("UCF_PLAN_ITEM_VCH2", null);
    	map.put("UCF_PLAN_ITEM_VCH3", null);
    	map.put("UCF_PLAN_ITEM_VCH4", null);
    	map.put("UCF_PLAN_ITEM_VCH5", null);
    	map.put("UCF_PLAN_ITEM_FLAG1", null);
    	map.put("UCF_PLAN_ITEM_FLAG2", null);
    	map.put("UCF_PLAN_ITEM_NUM1", null);
    	map.put("UCF_PLAN_ITEM_NUM2", null);
    	map.put("OVER_CONSUMPTION_FLAG", null);
    	map.put("ORIENTATION_FLAG", null);
    	map.put("CROSS_ORDER_FLAG", null);
    	map.put("ITEM_CATEGORY", null);
    	map.put("STORE_LOC", null);
    	map.put("UNLOADING_POINT", null);
    	map.put("STEP_NO", null);
    	map.put("STEP_NO_ORDERBY", null);
    	map.put("STANDARD_PART_FLAG", null);
    	map.put("OPTIONAL_FLAG", null);
    	map.put("UPDT_USERID", null);
    	map.put("TIME_STAMP", null);
    	map.put("UCF_PLAN_ITEM_VCH6", null);
    	map.put("UCF_PLAN_ITEM_VCH7", null);
    	map.put("UCF_PLAN_ITEM_VCH8", null);
    	map.put("UCF_PLAN_ITEM_VCH9", null);
    	map.put("UCF_PLAN_ITEM_VCH10", null);
    	map.put("UCF_PLAN_ITEM_VCH11", null);
    	map.put("UCF_PLAN_ITEM_VCH12", null);
    	map.put("UCF_PLAN_ITEM_VCH13", null);
    	map.put("UCF_PLAN_ITEM_VCH14", null);
    	map.put("UCF_PLAN_ITEM_VCH15", null);
    	map.put("UCF_PLAN_ITEM_NUM3", null);
    	map.put("UCF_PLAN_ITEM_NUM4", null);
    	map.put("UCF_PLAN_ITEM_NUM5", null);
    	map.put("UCF_PLAN_ITEM_DATE1", null);
    	map.put("UCF_PLAN_ITEM_DATE2", null);
    	map.put("UCF_PLAN_ITEM_DATE3", null);
    	map.put("UCF_PLAN_ITEM_DATE4", null);
    	map.put("UCF_PLAN_ITEM_DATE5", null);
    	map.put("UCF_PLAN_ITEM_FLAG3", null);
    	map.put("UCF_PLAN_ITEM_FLAG4", null);
    	map.put("UCF_PLAN_ITEM_FLAG5", null);
    	map.put("UCF_PLAN_ITEM_VCH255_1", null);
    	map.put("UCF_PLAN_ITEM_VCH255_2", null);
    	map.put("UCF_PLAN_ITEM_VCH255_3", null);
    	map.put("UCF_PLAN_ITEM_VCH4000_1", null);
    	map.put("UCF_PLAN_ITEM_VCH4000_2", null);
    	map.put("EXTERNAL_PLM_NO", null);
    	map.put("EXTERNAL_ERP_NO", null);
    	map.put("IMAGE", null);
    	map.put("SLIDE_ID", null);
    	map.put("SLIDE_EMBEDDED_REF_ID", null);
    	map.put("REMOVE_ACTION", null);
    	map.put("UTILIZATION_RULE", null);
    	map.put("TRACKABLE_FLAG", null);
    	map.put("UID_ITEM_FLAG", null);
    	map.put("UID_ENTRY_NAME", null);
    	map.put("CLOCK_SYMBOL", null);
    	map.put("DISPLAY_LINE_NO", null);
    	map.put("SECURITY_GROUP", null);
    	map.put("PHANTOM_KIT_PART_NO", null);
    	map.put("PART_STATUS_DISPLAY", null);
    	map.put("MSG", null);
    	map.put("AUTHOR_FROM", null);
    	return map;	
    }

    public void deleteOrder(String orderId, String refresh)
    {
		Map pwustSfwidOrderDescMap = workOrderDaoPW.selectPwustSfwidOrderDesc(orderId);
    	if (null==pwustSfwidOrderDescMap){
    		message.raiseError("MFI_20","Bad Order. Missing data in PWUST_SFWID_ORDER_DESC table");
    	}
    	
   		String pwOrderType = (String) pwustSfwidOrderDescMap.get("PW_ORDER_TYPE");	

   		if ("SUB ORDER".equalsIgnoreCase(pwOrderType)) {
			validator.checkUserPrivilege("PWUST_SUB_ORDER_DEL"); //Defect 468
		}
    	
		if ("SUPPLEMENTAL".equalsIgnoreCase(pwOrderType)) {
			validator.checkUserPrivilege("PWUST_SUPPLEM_ORDER_DEL"); //Defect 468
		}

    	this.Super.deleteOrder(orderId, refresh);
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectOperDatColInfo(String orderControlType, String orderId, Number operKey, Number stepKey,
			String referenceId) {
		List returnList = orderDaoPW.selectOperDatColInfo(orderControlType, orderId, operKey, stepKey, referenceId);
		Iterator i = returnList.listIterator();

		while (i.hasNext()) {
			Map mapList = (Map) i.next();
			String upperLimit = (String) mapList.get(UPPER_LIMIT);
			String lowerLimit = (String) mapList.get(LOWER_LIMIT);
			String targetValue = (String) mapList.get(TARGET_VALUE);
			String datColUom = (String) mapList.get(DAT_COL_UOM); // FND-24182

			String format = cdcVariableDao.selectUomFormat(datColUom);

			targetValue = parseDateGeneralToLocaleSpecific(format, targetValue);
			mapList.put(TARGET_VALUE, targetValue);
			lowerLimit = parseDateGeneralToLocaleSpecific(format, lowerLimit);
			mapList.put(LOWER_LIMIT, lowerLimit);
			upperLimit = parseDateGeneralToLocaleSpecific(format, upperLimit);
			mapList.put(UPPER_LIMIT, upperLimit);

			step.parseDataCollectionValue(mapList, upperLimit, lowerLimit, targetValue, null, null, format); // GE-3723

		}
		if (returnList.size() == 0) {
			Map emptyMap = new ListOrderedMap();
			emptyMap.put("ORDER_ID", null);
			emptyMap.put("OPER_NO", null);
			emptyMap.put("STEP_NO", null);
			emptyMap.put("OPER_KEY", null);
			emptyMap.put("STEP_KEY", null);
			emptyMap.put("STD_DATCOL_ID", null); // FND-24182
			emptyMap.put("DAT_COL_TYPE", null);
			emptyMap.put("DAT_COL_CERT", null);
			emptyMap.put("DAT_COL_UOM", null);
			emptyMap.put("DAT_COL_TITLE", null);
			emptyMap.put("UPPER_LIMIT", null);
			emptyMap.put("LOWER_LIMIT", null);
			emptyMap.put("TARGET_VALUE", null);
			emptyMap.put("ALT_ID", null);
			emptyMap.put("ALT_COUNT", null);
			emptyMap.put("REF_ID", null); 
			emptyMap.put("SERIAL_NO", null);
			emptyMap.put("DISPLAY_LINE_NO", null);
			emptyMap.put("ORIENTATION_FLAG", null);
			emptyMap.put("CROSS_ORDER_FLAG", null);
			emptyMap.put("OPTIONAL_FLAG", null);
			emptyMap.put("VALUE", null);
			emptyMap.put("COMMENTS", null);
			emptyMap.put("UPDT_USERID", null);
			emptyMap.put("TIME_STAMP", null);
			emptyMap.put("IMAGE", null);

			returnList.add(emptyMap);
		}
		return returnList;
	}

}

