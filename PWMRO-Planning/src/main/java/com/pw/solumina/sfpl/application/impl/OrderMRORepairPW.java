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
*  File:    OrderMRORepairPW.java
*  
*  PW_SMRO_WOE_310_Order_Creation
* 
*  Created: 2019.04.12
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date             Who            Description
*  -----------    ------------     ---------------------------------------------------
*  2019-04-19       Fred Ettefagh  
*  2019-05-03       Fred Ettefagh  added more code for repair order create 
*  2019-06-05       Fred Ettefagh  changed code based on Dave M code for interface T301
*  2019-10-10       Fred Ettefagh  removed   customer and customerOrderNumber from call to create repair orders 
*  2019-10-23       Fred Ettefagh  PW_SMRO_WOE_310, added code to update work order ASGND_LOCATION_ID wtih plans work loc after new order is created.
*  2019-10-24       Fred Ettefagh  PW_SMRO_WOE_310, added code to skipp oob show message when a PW order is created.
*  2019-10-30       Fred Ettefagh  PW_SMRO_WOE_310, defect 1395  changed multi order create code, so that first order is create and them background job is started for rest of orders  
*  2019-11-18       Fred Ettefagh  PW_SMRO_WOE_310, set sfwid_order_desc.UCF_ORDER_VCH15 to iDocNumber.  this is needed for repair order split interface
*  2019-11-20       Fred Ettefagh  PW_SMRO_TR_319, moved method updateSfwidOrderDescWorkLoc(newOrderId,workLocationId) to CommonDaoPW class
*  2019-12-03       Fred Ettefagh  PW_SMRO_WOE_310, changed repair order create method to get part_no and part_chg from sfpl_plan_v
*  2019-12-12       Fred Ettefagh  PW_SMRO_WOE_310, Changed repair muilt order create method to check for duplicate order no
*  2019-12-16       Fred Ettefagh  moved dao sfplPlanVSelect from OrderMroRepairSplitDao to CommonDaoPW 
*  2020-01-17       Fred Ettefagh  PW_SMRO_WOE_310, defect 1454/1457, when creating MRO orders, part_no/part_chg passed to OOB code to be inserted to sfwid_order_desc.part_no must be in one of the task part numbers of the selected plan 
*  2020-01-27		Brendan Polak  defect 1406 - added outgoing part no to create repair order 
*  2020-02-24       Fred Ettefagh  SMRO_TR_319, Defect 1526 changed code to save SAP_ORDER_TYPE in ext table pwust_sfwid_order_desc. 
*  2020-04-13		Brendan Polak  defect 1624 - adding work_loc to createRepairOrderSelUDV to account for defect 1564
*  2020-05-06		Brendan Polak  defect 1584 - adding lot_code to createRepairOrderSelUDV
*  2020-05-29       Fred Ettefagh  SMRO_WOE_302 - Defect 1584, updated and fixed update to table PWUST_SFWID_ORDER_DESC after repair order create to update OUTGOING_PART_NO, SAP_ORDER_TYPE and SAP_LOT_CODE
*  2020-05-29       John DeNinno   SMRO_WOE_302 - Defect 1584, pass " " as Lot No to avoid the out of the box N/A error. Will still populate later with N/A
*  2021-04-28       David Miron    SMRO_WOE_301 - Defect 1880, check for "No Plan" passed in to createRepairOrder. 
*/
package com.pw.solumina.sfpl.application.impl;

import static com.ibaset.common.SoluminaConstants.INSERT;
import static com.ibaset.common.SoluminaConstants.SEMI_COLON;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

import com.ibaset.common.Reference;
import com.ibaset.common.client.SoluminaServiceLocator;
import com.ibaset.common.concurrent.AsynchronousTransaction;
import com.ibaset.common.concurrent.AsynchronousTransactionCallback;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.util.ClassUtils;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.application.IParse;
import com.ibaset.solumina.sffnd.application.IWrapUtils;
import com.ibaset.solumina.sfpl.application.IOrder;
import com.pw.common.dao.CommonDaoPW;
import com.pw.common.utils.CommonUtilsPW;
import com.pw.solumina.sfpl.dao.impl.LogOrderCreateErrors;
import com.pw.solumina.sfpl.dao.impl.OrderCreateDaoPW;
import com.pw.solumina.sfpl.dao.impl.RepairOrderCreateDaoPW;

public class OrderMRORepairPW {
	
	@Reference 	private CallOrderCreate callOrderCreate = null;
	@Reference	private IMessage message = null;
	@Reference	private RepairOrderCreateDaoPW repairOrderCreateDaoPW = null;
	@Reference	private OrderCreateDaoPW orderCreateDaoPW = null;
	@Reference private IOrder orderImpl =null;
	@Reference private IEvent event = null;
	@Reference private IWrapUtils wrapUtils = null;
	@Reference DataFieldMaxValueIncrementer soluminaSequence = null;
	@Reference LogOrderCreateErrors logOrderCreateErrors = null;
	@Reference CommonUtilsPW commonUtilsPW = null;
	@Reference CommonDaoPW commonDaoPW = null;
	@Reference IParse parse = null;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void createRepairOrderSelUDV(String  iDocNumber){
		
		// called from Sql lib to display Repair order create UDV. 
		// Must set smOrderNo and order qty for single or Multiple repair order create 
		
		List<Map> list = repairOrderCreateDaoPW.pwustIntRepairWoDataSelect(iDocNumber);
		
		String sapUserName = null;
		String smOrder = null;
		String itemNo = null;
		String itemRev = "N/A";
		int totalQuantity = 0;
		String salesOrder = null;
		String customerNumber = null;
		String customerName = null;
		String planType = "REPAIR";
		//1406 added outgoing material
		String outgoing_material = null;
		String sapOrderType = null;
		//1624 added work location
		String work_loc = null;
		//1584 added lot code
		String lot_code = null;

		
		if (list!=null && !list.isEmpty()){
			for (Map intOrderDataMap : list) {
				
				sapUserName = (String)intOrderDataMap.get("SAP_UPDT_USERID");
				smOrder = (String)intOrderDataMap.get("SM_ORDER");
				itemNo = (String)intOrderDataMap.get("INCOMING_MATERIAL");
				Number quantity =(Number)intOrderDataMap.get("QUANTITY");
				totalQuantity = totalQuantity + quantity.intValue();
				salesOrder = (String)intOrderDataMap.get("SALES_ORDER");
				customerNumber = (String)intOrderDataMap.get("CUSTOMER_NUMBER");
				customerName = (String)intOrderDataMap.get("CUSTOMER_NAME");
				outgoing_material = (String)intOrderDataMap.get("OUTGOING_MATERIAL");
				sapOrderType = (String)intOrderDataMap.get("ORDER_TYPE");
				work_loc = (String)intOrderDataMap.get("PLANT");
				lot_code = (String)intOrderDataMap.get("LOT_CODE");
			}
			
			if (list.size() >1){
				smOrder="Multiple";
			}
		}
		

		
		StringBuffer parameters = new StringBuffer();
		parameters.append("IDOC_NUMBER=").append(wrapUtils.wrapValue(iDocNumber))
  				  .append(",SAP_UPDT_USERID=").append(wrapUtils.wrapValue(sapUserName))
				  .append(",SM_ORDER=").append(wrapUtils.wrapValue(smOrder))
				  .append(",ITEM_NO=").append(wrapUtils.wrapValue(itemNo))
				  .append(",ITEM_REV= ").append(wrapUtils.wrapValue(itemRev))
				  .append(",QUANTITY=").append(wrapUtils.wrapValue(totalQuantity))
				  .append(",PLAN_TYPE=").append(wrapUtils.wrapValue(planType))
				  .append(",SALES_ORDER=").append(wrapUtils.wrapValue(salesOrder))
				  .append(",CUSTOMER_NUMBER=").append(wrapUtils.wrapValue(customerNumber))
				  .append(",CUSTOMER_NAME=").append(wrapUtils.wrapValue(customerName))
				  .append(",OUTGOING_NO=").append(wrapUtils.wrapValue(outgoing_material))
				  .append(",SAP_ORDER_TYPE=").append(wrapUtils.wrapValue(sapOrderType) )
				  .append(",WORK_LOC=").append(wrapUtils.wrapValue(work_loc) )
				  .append(",SAP_LOT_CODE=").append(wrapUtils.wrapValue(lot_code) );

		String hideColumns = null;
		
		event.showUdv("PWUST_84022A8CD747EF4BE05387971F0A50FF",INSERT, "Create Repair Work Order",hideColumns,parameters.toString());
	}
	
	//1406 - added outgoing part
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void createRepairOrder(String  iDocNumber,
				                  String  sapUserName,
				                  String  smOrderNo,
				                  String  partNo,
				                  String  partChg,
				                  Number  orderQty,
				                  String  salesOrderNumber,
				                  String  customerNo,
				                  String  customerName,
				                  String  planNo,
				                  String  planId,
				                  Number  planVer,
				                  Number  planRev,
				                  Number  planAlt,
				                  String  planType,
				                  String  effectivityType,
				                  String  outgoingPartNo,
				                  String  sapOrderType,
				                  String  sapLotCode){
		
		// called from sql lib to create one repair orders or  Multiple Repair orders
		
		// Defect 1880 
		if ("No Plan".equals(planNo)) {
			return;
		}
		
		if (StringUtils.isBlank(iDocNumber)){
			message.raiseError("MFI_20","interfaceTablePK cannot be null");
		}
		if (StringUtils.isBlank(smOrderNo)){
			message.raiseError("MFI_20","smOrderNo cannot be null");
		}
		if (StringUtils.isBlank(planId)){
			message.raiseError("MFI_20","planId cannot be null");
		}
		if (planVer==null){
			message.raiseError("MFI_20","planVer cannot be null");
		}
		if (planRev==null){
			message.raiseError("MFI_20","planRev cannot be null");	
		}
		if (planAlt==null){
			message.raiseError("MFI_20","planAlt cannot be null");	
		}
		if (orderQty==null || orderQty.intValue()==0 ){
			message.raiseError("MFI_20","orderQty cannot be null");
		}
		
		// 1/17/2020
		// Fred E defect 1454 and 1457
		// when creating MRO orders, part no/part chg pased to oob code to insert into sfwid_order_desc.part_no must in one of the task group parts
		/*
		Map sfplPlanVMap = commonDaoPW.sfplPlanVSelect(planId, planVer, planRev, planAlt);
		if (sfplPlanVMap == null){
			message.raiseError("MFI_20","sfplPlanVMap is null");
		}
		
		partNo = (String)sfplPlanVMap.get("PART_NO");
		partChg  =(String)sfplPlanVMap.get("PART_CHG");
		*/
		
		Map itemMasterMap = repairOrderCreateDaoPW.sfplItemDescMasterAllSelect(partNo, partChg);
		if (itemMasterMap == null){
				message.raiseError("MFI_20","material = " + partNo + " not found in solumina item master table");
		}
		
		String todaysDate = orderCreateDaoPW.selectDBTimeStamp("MM/DD/YYYY");
		String userName = ContextUtil.getUser().getUsername();
		
		HashMap<String, Object> argsHashMap = new HashMap<String, Object>();
		argsHashMap.put("smOrderNo", smOrderNo);
		argsHashMap.put("sapUserName",sapUserName);
		argsHashMap.put("partNo", partNo);
		argsHashMap.put("partChg", partChg);
		argsHashMap.put("salesOrderNumber",salesOrderNumber); 
		argsHashMap.put("customerNo",customerNo);
		argsHashMap.put("customerName", customerName);
		argsHashMap.put("planNo", planNo);
		argsHashMap.put("planId", planId);
		argsHashMap.put("planVer", planVer);
		argsHashMap.put("planRev", planRev);
		argsHashMap.put("planAlt", planAlt);
		argsHashMap.put("todaysDate", todaysDate);
		argsHashMap.put("userName",userName);
		argsHashMap.put("iDocNumber",iDocNumber);
		argsHashMap.put("orderQty",orderQty);
		argsHashMap.put("outgoingPartNo", outgoingPartNo);
		argsHashMap.put("sapOrderType", sapOrderType);
		argsHashMap.put("sapLotCode", sapLotCode);
		
		if ("Multiple".equalsIgnoreCase(smOrderNo)){
			argsHashMap.put("calledFrom", "Multiple Repair Order Create");
			createMultipleRepairOrders(argsHashMap);
		}else{
			argsHashMap.put("calledFrom", "Single Repair Order Create");
			createSingleRepairOrder(argsHashMap);
		}
	}

	@SuppressWarnings({ "rawtypes" })
	public void createSingleRepairOrder(HashMap<String, Object> argsHashMap) {
		
		//message.showMessage("call solumina repair order create code");
		
		Map sfwidOrderDescMap = repairOrderCreateDaoPW.sfwidOrderDescSelect((String)argsHashMap.get("smOrderNo"));
		
		if (sfwidOrderDescMap !=null){
			message.raiseError("MFI_20","Order No = " + (String)argsHashMap.get("smOrderNo") + " exists in solumina table");
		}
	    String serialNumbers = getIntSerialNumbers((String)argsHashMap.get("iDocNumber"), (String)argsHashMap.get("smOrderNo"));
		argsHashMap.put("serialNumbers", serialNumbers);
		argsHashMap.put("calledFrom", "Single Repair Order Create");
		
		String  orderId =  oobCreateRepairOrder(argsHashMap);
		
		event.refreshTool();
		//event.launchManufacturing( orderId, EDIT, true );
	}



	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createMultipleRepairOrders(HashMap dataHashMap) {
		
    	//message.showMessage("call solumina repair order create code");
    	
		String iDocNumber = (String) dataHashMap.get("iDocNumber");
		String backGroundJobOrderNoString = null;
		List<Map> list = repairOrderCreateDaoPW.pwustIntRepairWoDataSelect(iDocNumber);
		if (list!= null && !list.isEmpty()){
			
			System.out.print("Repair order create, createMultipleRepairOrders number of orders to create is "+ list.size());
			boolean firstOrder = true;
			
			for (Map intOrderDataMap : list) {
				String smOrderNo = (String) intOrderDataMap.get("SM_ORDER");
				Map sfwidOrderDescMap = repairOrderCreateDaoPW.sfwidOrderDescSelect(smOrderNo);
				if (sfwidOrderDescMap!=null){
					message.raiseError("MFI_20","Order No = " + smOrderNo + " exists in solumina table");
				}
			}
			
			for (Map intOrderDataMap : list) {
				
				Number orderQty = (Number) intOrderDataMap.get("QUANTITY");
				String smOrderNo = (String) intOrderDataMap.get("SM_ORDER");
					
				String serialNumbers = getIntSerialNumbers(iDocNumber, smOrderNo);
				dataHashMap.put("serialNumbers", serialNumbers);
				dataHashMap.put("orderQty",orderQty);
				dataHashMap.put("smOrderNo",smOrderNo);
				dataHashMap.put("numberOFOrders", list.size());
				
				if (firstOrder){
						firstOrder = false;
						oobCreateRepairOrder(dataHashMap);
				}else{
						
						if (StringUtils.isNotBlank(backGroundJobOrderNoString)){
							backGroundJobOrderNoString = smOrderNo;
						}else{
							backGroundJobOrderNoString = backGroundJobOrderNoString + System.lineSeparator() + smOrderNo;
						}
						
						System.out.print("Repair order create background job submited, " +  System.lineSeparator() +
								        " backGroundJobOrderNoString = " + backGroundJobOrderNoString +  System.lineSeparator() +
									    " iDocNumber = " + iDocNumber + System.lineSeparator() +
		                                " smOrderNo = "+ smOrderNo + System.lineSeparator() +
		                                " orderQty = " + orderQty +  System.lineSeparator() +
		                                " serialNumbers = " + serialNumbers + System.lineSeparator() +
		                                " dataHashMap.get(planNo) = "+ dataHashMap.get("planNo") + System.lineSeparator() +
		                                " dataHashMap.get(planId) = "+ dataHashMap.get("planId") + System.lineSeparator() +
		                                " dataHashMap.get(planVer) = "+ dataHashMap.get("planVer")+ System.lineSeparator() +
		                                " dataHashMap.get(planRev) = "+ dataHashMap.get("planRev") + System.lineSeparator() +
		                                " dataHashMap.get(planAlt) = "+ dataHashMap.get("planAlt") ); 
						
						submitRepaorOrderCreateJob(dataHashMap);	
				}
			}
			
			int numberOfbackGroundJobs = list.size() - 1;
			message.showMessage("MFI_20","Started background jobs to create repair orders " + System.lineSeparator() + backGroundJobOrderNoString );
			event.refreshTool();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void submitRepaorOrderCreateJob(HashMap dataHashMap) {

		try{
			Class<?> backJob = this.getClass();
			Method method = ClassUtils.findMethod(backJob, "oobCreateRepairOrder", 1); 
			
			Object[] args = {dataHashMap};
			
			String orderQtyString = null;
			Number orderQty = (Number)dataHashMap.get("orderQty");
			if (orderQty!=null){
				orderQtyString = orderQty.toString();
			}
			
			AsynchronousTransactionCallback c = new  AsynchronousTransactionCallback ( "PWUST Repair Order Create",
																					   backJob,
																					   method.getName(),
																					   args,
																					   (String)dataHashMap.get("iDocNumber"),
																					   (String)dataHashMap.get("planNo"),
																					   orderQtyString,
																					   null,
																					   null);
			
			AsynchronousTransaction t = SoluminaServiceLocator.getAsynchronousTransactionService().execute(c);
			t.setIgnoreUIEvents(true);
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	public String oobCreateRepairOrder(HashMap dataHashMap) {
		
		    commonUtilsPW.disableConnectionHistory();
		    
			String newOrderId =null;
		
			String smOrderNo = (String) dataHashMap.get("smOrderNo");
			String sapUserName = (String) dataHashMap.get("sapUserName");
			String partNo = (String) dataHashMap.get("partNo");
			String partChg = (String) dataHashMap.get("partChg");
			Number orderQty = (Number)dataHashMap.get("orderQty");
			String serialNumbers = (String)dataHashMap.get("serialNumbers");
			String salesOrderNumber = (String) dataHashMap.get("salesOrderNumber"); 
			String customerNo = (String) dataHashMap.get("customerNo");
			String customerName = (String) dataHashMap.get("customerName");
			String planId = (String)dataHashMap.get("planId");
			Number planVer = (Number) dataHashMap.get("planVer");
			Number planRev = (Number) dataHashMap.get("planRev");
			Number planAlt = (Number) dataHashMap.get("planAlt");
			String todaysDate = (String) dataHashMap.get("todaysDate");
			String userName = (String) dataHashMap.get("userName");
			String iDocNumber = (String) dataHashMap.get("iDocNumber");
			String calledFrom = (String) dataHashMap.get("calledFrom");
			String outgoingPartNo = (String) dataHashMap.get("outgoingPartNo");
			String sapOrderType = (String) dataHashMap.get("sapOrderType");
			String sapLotCode= (String) dataHashMap.get("sapLotCode");
			
			Number numberOfOrders = 0;
			if (StringUtils.equalsIgnoreCase(calledFrom, "Multiple Repair Order Create")){
				numberOfOrders = (Number) dataHashMap.get("numberOFOrders");
			}
			
			
			Map sfplPlanVMap = orderCreateDaoPW.selectSfplPlanV(planId, planVer, planRev, planAlt);
			String workLocationId = (String) sfplPlanVMap.get("PLND_LOCATION_ID");
			
			try{
				        ContextUtil.getUser().getContext().set("LAUNCH_ORDER", "FALSE");
						String dateTimeFomat = "MM/DD/YYYY HH:MM:SS";
				        String sysDateTimie = orderCreateDaoPW.selectDBTimeStamp(dateTimeFomat);
						orderImpl.createWorkOrder(partNo, 
				                                  partChg, 
				                                  planId, 
				                                  smOrderNo,
				                                  orderQty, 
									              todaysDate,  //scheduledStartDate,  
									              null,        // scheduledEndDate, 
									              null,        //effectivityType, 
									              null,        //effectivityUnit, 
									              null,        //notes, 
									              null,        //model, 
									              "REPAIR",    //orderType, 
									              null,        //customer, 
									              null,        //customerDescription, 
									              null,        //customerOrderNumber, 
									              null,        //contract, 
									              workLocationId,        //workLocation, 
									              null,        //orderReview, 
									              null,        //owpId, 
									              sapUserName,//ucfOrderVch1, 
									              null,    //ucfOrderVch2, 
									              null,    //ucfOrderVch3, 
									              null,    //ucfOrderVch4, 
									              null,    //ucfOrderVch5, 
									              null,    //ucfOrderVch6, 
									              sysDateTimie,//ucfOrderVch7, 
									              null,    //ucfOrderVch8, 
									              null,    //ucfOrderVch9, 
									              null,    //ucfOrderVch10, 
									              salesOrderNumber, //ucfOrderVch11
									              salesOrderNumber, //ucfOrderVch12,
									              null,//ucfOrderVch13, 
									              null,//ucfOrderVch14, 
									              iDocNumber, //ucfOrderVch15, 
									              null,//ucfOrderNum1, 
									              null,//ucfOrderNum2, 
									              null,//ucfOrderNumber3,
									              null,//ucfOrderNumber4, 
									              numberOfOrders,//ucfOrderNumber5, 
									              null,//ucfOrderDate1, 
									              null,//ucfOrderDate2, 
									              null,//ucfOrderDate3, 
									              null,//ucfOrderDate4, 
									              null,//ucfOrderDate5, 
									              null,//ucfOrderFlag1, 
									              null,//ucfOrderFlag2, 
									              null,//ucfOrderFlag3, 
									              null,//ucfOrderFlag4, 
									              null,//ucfOrderFlag5, 
									              null,//ucfOrderVch2551, 
									              null,//ucfOrderVch2552, 
									              null,//ucfOrderVch2553, 
									              null,//ucfOrderVch40001, 
									              null,//ucfOrderVch40002, 
									              null,//replacementAction, 
									              null,//asWorkedBomId, 
									              null,//relatedOrderId, 
									              null,//selectedField, 
									              null,//groupOfSerialList,
									              null,//orderNumberForGroupOfSerial, 
									              null,//calledFrom, 
									              serialNumbers,//serialNumbersPassed, 
									              " ", //defect 1584 "N/A",//lotNumberPassed, 
									              "N/A",//unitNumberPassed,
									              planVer,//planVersionPassed, 
									              planRev,//planRevisionPassed, 
									              planAlt,//planAlterationsPassed, 
									              null,//hiddenValues,
									              null,//parentOrderId, 
									              null,//startingItemNo,
									              null,//startingItemRev,
									              null,//configNmae, 
									              null,//changeRequestId,
									              null,//plannedActionId, 
									              null,//releasePackageId,
									              null,//discrepancyId, 
									              null,//discrepancyLineNumber,
									              planVer, // Number planVersionSelected,
									              planRev, // Number planRevisionSelected,  // GE-4863
									              planAlt);
						
						if (StringUtils.equalsIgnoreCase(calledFrom, "Multiple Repair Order Create") ||
							StringUtils.equalsIgnoreCase(calledFrom, "Single Repair Order Create")){
							
								Map map = orderCreateDaoPW.selectOrderId(smOrderNo);
								newOrderId = (String)map.get("ORDER_ID");
								repairOrderCreateDaoPW.updatePwustIntRepairWoDataProcesFlagToSuccess(iDocNumber, smOrderNo,newOrderId);
								updateNewOrderWorkLoc(newOrderId, planId, planVer, planRev, planAlt);
								// 1406 add outgoing part no 
								// 1526 update pwust_sfwid_order_desc with SAP_ORDER_TYPE 
								repairOrderCreateDaoPW.updatePwustSfwidOrderDesc(newOrderId,outgoingPartNo,sapOrderType,sapLotCode);
								
						}
						

						
			}catch (Exception e) {
				String errMsg = e.getMessage();
				
				if (StringUtils.isNotBlank(errMsg) && errMsg.length() >2000){
					errMsg = errMsg.substring(1, 1999);
				}
				
				logOrderCreateErrors.insertIntoErrorTable(errMsg,
						                                  smOrderNo,
							                              partNo,
							                              planId,
							                              planRev,
							                              planRev,
							                              planAlt,
							                              null,//planUpdtNo,
							                              salesOrderNumber,
							                              null,//engineType,
							                              null,//engineModel,
							                              null,//workLocation,
							                              null,//workScope,
							                              null,//superiorNet,
							                              null,//subNet,
							                              iDocNumber,//customer,
							                              calledFrom);
				
				commonUtilsPW.enableConnectionHistory();
				
				throw e ;
			}finally{
				ContextUtil.getUser().getContext().set("LAUNCH_ORDER", null);
			}
			
			commonUtilsPW.enableConnectionHistory();
			
			return newOrderId; 	
			
			
	}

	public void updateNewOrderWorkLoc(String newOrderId, String planId, Number planVer, Number planRev,Number planAlt) {
		
		String workLocationId = null;
		Map planVmap =orderCreateDaoPW.selectSfplPlanV(planId, planVer, planRev, planAlt);
		workLocationId = (String) planVmap.get("PLND_LOCATION_ID");
		int i = commonDaoPW.updateSfwidOrderDescWorkLoc(newOrderId,workLocationId);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getIntSerialNumbers(String interfaceTablePK, String smOrderNo) {
		
		String serialNumbers = null;
		List<Map> serialNoList = repairOrderCreateDaoPW.pwustIntRepairWoSerialNoSelect(interfaceTablePK, smOrderNo);
		if (serialNoList!=null && !serialNoList.isEmpty()){
			
			for (Map intOrderSerialDataMap : serialNoList) {
				if (serialNumbers==null){
					serialNumbers = (String)intOrderSerialDataMap.get("SERIAL_NUMBER");
				}else{
					serialNumbers = serialNumbers + SEMI_COLON + (String)intOrderSerialDataMap.get("SERIAL_NUMBER");	
				}
			}
		}
		return serialNumbers;
	}
}