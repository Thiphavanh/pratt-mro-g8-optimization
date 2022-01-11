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
 *  File:    OrderMROOverhaulPW.java
 *  
 *  PW_SMRO_eWORK_202-Order-Creation
 * 
 *  Created: 2017.08.09
 * 
 *  Author:  Fred Ettefagh
 * 
 *  Revision History
 * 
 *  Date             Who            Description
 *  -----------    ------------     ---------------------------------------------------
 *  2018-03-08       Fred Ettefagh  
 *  2018-03-26       Fred Ettefagh defect 412  change how overhaul overs are created via input matrix
 *  2018-03-269      Fred Ettefagh defect 386  long order no
 *  2018-05-04       Fred Ettefagh defect 754  Input Matrix not including expected task groups - All Customers
 *  2018-05-07       Fred Ettefagh defect 754  fix null pointer exception for input matrix order create
 *  2018-05-10       Fred Ettefagh defect 765  added trace code to track OOB error Scheduled Start Date is required and must be within the effectivity range
 *  2018-05-22       Fred Ettefagh defect N/A  Changed order create to commit after each call to OOB order create method
 *  2018-05-24       Fred Ettefagh defect 811  Changed input matrix order create to use global temporary table PWUST_TEMP_ORDER_CREATE_DATA 
 *  2018-06-01       Fred Ettefagh defect N/A  changed date mask from "MM/DD/YYYY HH:MM:SS" to "MM/DD/YYYY"
 *  2018-06-07       T. Phan       defect 728  Removed wrapper statement "... Created Created" to "... Created"
 *  2018-06-18	    Bob Preston	  defect 820  SMRO_eWORK_202 -- Commented out update.
 *  2018-06-21	    Bob Preston	  defect 820  SMRO_eWORK_202 -- Uncommented update.
 *  2018-07-09       Fred Ettefagh Changed code for defect 820, update tasks based on each IM data row
 *  2018-07-18       T. Phan       defect 885  Added Customer Field to Create Order UDV
 *  2018-10-29		Bob Preston   defect 468  SMRO_USR_203 -- Added check for PW_OH_PARENT_ORDER_CREATE priv.
 *  2019-02-26       R.Thorpe	  defect 1006 SMRO_eWORK_202 - added code to insert to PWUST_TEMP_ORDER_CREATE_DATA in  createMroOverhaulOrders
 *  2019-04-19       Fred Ettefagh defect 1104, start and complete WO ALT when updating tasks after WO create.
 *  2019-05-03       Fred Ettefagh added event.refreshTool() to method buildImOrders()
 *  2021-01-29       John DeNinno Defect 1844 display only one popup with all the work orders
 */

package com.pw.solumina.sfpl.application.impl;

import static com.ibaset.common.SoluminaConstants.MRO_PART;
import static com.ibaset.common.SoluminaConstants.MRO_UPGRADE;
import static com.ibaset.common.util.SoluminaUtils.stringEquals;
import static org.apache.commons.lang.StringUtils.contains;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.solumina.IValidator;
import com.ibaset.common.util.SoluminaUtils;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.application.IParameters;
import com.ibaset.solumina.sffnd.application.IWrapUtils;
import com.ibaset.solumina.sfpl.application.IOrder;
import com.ibaset.solumina.sfpl.dao.IOrderDao;
import com.ibaset.solumina.sfpl.dao.IPlanDao;
import com.pw.solumina.sfpl.dao.impl.LogOrderCreateErrors;
import com.pw.solumina.sfpl.dao.impl.OrderCreateDaoPW;
import com.pw.common.dao.CommonDaoPW;


public class OrderMROOverhaulPW{

	@Reference
	private DataFieldMaxValueIncrementer orderStringSequence = null;

	@Reference
	private IWrapUtils wrapUtils = null;
	@Reference
	private IEvent event = null;

	@Reference
	IOrder orderImpl =null;

	@Reference
	private IMessage message = null;

	@Reference
	private OrderCreateDaoPW orderCreateDaoPW = null;
	@Reference
	private IPlanDao planDao = null;
	@Reference
	private IOrderDao orderDao = null;
	@Reference
	private buildMROOrderNumber buildMROOrderNumber = null;
	@Reference
	private IParameters parameters = null;
	@Reference
	private CallOrderCreate callOrderCreate = null;

	@Reference
	private UpdateOverHaulTaskStatus updateOverHaulTaskStatus = null;

	@Reference
	private LogOrderCreateErrors logOrderCreateErrors;

	@Reference private IValidator validator = null; // Defect 468.

	@Reference
	private CommonDaoPW commonDaoPW;

	class PlanDataSelected{


		String partNo  = null;
		String partChg = null;
		String planId  = null;
		Number planVer = null;
		Number planRev = null;
		Number planAlt = null;
		Number planUpdtNo = null;
		String scheduledStartDate =null;
		String scheduledEndDate = null;

		public Number getPlanUpdtNo() {
			return planUpdtNo;
		}
		public void setPlanUpdtNo(Number planUpdtNo) {
			this.planUpdtNo = planUpdtNo;
		}
		public String getScheduledStartDate() {
			return scheduledStartDate;
		}
		public void setScheduledStartDate(String scheduledStartDate) {
			this.scheduledStartDate = scheduledStartDate;
		}

		public String getScheduledEndDate() {
			return scheduledEndDate;
		}
		public void setScheduledEndDate(String scheduledEndDate) {
			this.scheduledEndDate = scheduledEndDate;
		}
		public String getPartNo() {
			return partNo;
		}
		public void setPartNo(String partNo) {
			this.partNo = partNo;
		}
		public String getPartChg() {
			return partChg;
		}
		public void setPartChg(String partChg) {
			this.partChg = partChg;
		}
		public String getPlanId() {
			return planId;
		}
		public void setPlanId(String planId) {
			this.planId = planId;
		}
		public Number getPlanVer() {
			return planVer;
		}
		public void setPlanVer(Number planVer) {
			this.planVer = planVer;
		}
		public Number getPlanRev() {
			return planRev;
		}
		public void setPlanRev(Number planRev) {
			this.planRev = planRev;
		}
		public Number getPlanAlt() {
			return planAlt;
		}
		public void setPlanAlt(Number planAlt) {
			this.planAlt = planAlt;
		}
	}

	class UserEnteredData {
		String customer= null;// Defect 885
		String salesOrderNumber = null;
		String engineType= null;
		String engineModel= null;
		String sapWorkLoc= null; 
		String workScope= null;
		String superiorNet= null;
		String subNet= null;
		String partNoPassedIn= null;
		String plannedOrderFlag= null;

		public String getSalesOrderNumber() {
			return salesOrderNumber;
		}
		public void setSalesOrderNumber(String salesOrderNumber) {
			this.salesOrderNumber = salesOrderNumber;
		}
		public String getEngineType() {
			return engineType;
		}
		public void setEngineType(String engineType) {
			this.engineType = engineType;
		}
		public String getEngineModel() {
			return engineModel;
		}
		public void setEngineModel(String engineModel) {
			this.engineModel = engineModel;
		}
		public String getSapWorkLoc() {
			return sapWorkLoc;
		}
		public void setSapWorkLoc(String sapWorkLoc) {
			this.sapWorkLoc = sapWorkLoc;
		}
		public String getWorkScope() {
			return workScope;
		}
		public void setWorkScope(String workScope) {
			this.workScope = workScope;
		}
		public String getSuperiorNet() {
			return superiorNet;
		}
		public void setSuperiorNet(String superiorNet) {
			this.superiorNet = superiorNet;
		}
		public String getSubNet() {
			return subNet;
		}
		public void setSubNet(String subNet) {
			this.subNet = subNet;
		}
		public String getCustomer() {
			return customer;
		}
		public void setCustomer(String customer) {
			this.customer = customer;
		}
		public String getPartNoPassedIn() {
			return partNoPassedIn;
		}
		public void setPartNoPassedIn(String partNoPassedIn) {
			this.partNoPassedIn = partNoPassedIn;
		}
		public String getPlannedOrderFlag() {
			return plannedOrderFlag;
		}
		public void setPlannedOrderFlag(String plannedOrderFlag) {
			this.plannedOrderFlag = plannedOrderFlag;
		}
	}


	public List selectWorkOrderOverhaulDispatch (){

		return orderCreateDaoPW.selectWorkOrderOverhaulDispatch();
	}

	public List selectWorkOrderOverhaulTab (String salesOrder){

		return orderCreateDaoPW.selectWorkOrderOverhaulTab(salesOrder);
	}

	// defect 1104
	public void buildImOrders() throws Exception{

		// final call from input matrix
		String msg = "No plan Data found ";

		List imPlanlist = orderCreateDaoPW.selectTempOrderCreateDataDistinct();
		if (imPlanlist!=null && !imPlanlist.isEmpty()){

			Iterator iterator = imPlanlist.iterator();

			Map saleOrderMap = (Map) orderCreateDaoPW.selectTempOrderCreateDataSaleOrder();
			String salesOrderNumber = (String) saleOrderMap.get("SALES_ORDER");
			String engineModel = (String) saleOrderMap.get("ENGINE_MODEL");
			String engineType = (String) saleOrderMap.get("ENGINE_TYPE");
			String sapWorkLoc = (String) saleOrderMap.get("SAPWORKLOC");
			String plannedOrderFlag = (String) saleOrderMap.get("PLANNEDORDERFLAG");

			UserEnteredData userEnteredData = new UserEnteredData();
			userEnteredData.setSalesOrderNumber(salesOrderNumber);
			userEnteredData.setEngineType(engineType);
			userEnteredData.setEngineModel(engineModel);
			userEnteredData.setSapWorkLoc(sapWorkLoc);
			userEnteredData.setPlannedOrderFlag(plannedOrderFlag);

			while (iterator.hasNext()){

				String 	newpPOrderNo = null;
				Map map = (Map) iterator.next();

				String planId  = (String)  map.get("PLAN_ID");
				Number planVer = (Number)  map.get("PLAN_VERSION");
				Number planRev = (Number)  map.get("PLAN_REVISION");
				Number planAlt = (Number)  map.get("PLAN_ALTERATIONS");
				Number planUpdtNo = (Number) map.get("PLAN_UPDT_NO");
				String partNo = (String) map.get("PART_NO");

				PlanDataSelected planDataSelected =  new PlanDataSelected();
				planDataSelected.setPartNo(partNo);
				planDataSelected.setPartChg("N/A");
				planDataSelected.setPlanId(planId);
				planDataSelected.setPlanVer(planVer);
				planDataSelected.setPlanRev(planRev);
				planDataSelected.setPlanAlt(planAlt);

				planDataSelected.setPlanUpdtNo(planUpdtNo);


				newpPOrderNo =  createOverhaulOrder(userEnteredData,planDataSelected,"INPUT MATRIX");

				if (StringUtils.contains(newpPOrderNo, "Error")){
					if (StringUtils.isBlank(msg)){
						msg = "Error " + newpPOrderNo ;
					}else{
						msg = msg + System.lineSeparator() +"Error " + newpPOrderNo ;
					}					
				}else{
					String displayMsg = "Work Order " +newpPOrderNo + " Created "; 
					if (StringUtils.isBlank(msg)){
						msg = displayMsg;
					}else{
						msg = msg + System.lineSeparator() + displayMsg;
					}
				}
			}
		}
		message.showMessage("MFI_20", msg);
		event.refreshTool();
	}


	public void movePlannedOrder(String salesOrderNumber,
			String engineType,
			String engineModel,
			String sapWorkLoc,
			String workScope,
			String superiorNet,
			String subNet,
			String customer,
			String partNoPassedIn,
			String planId,
			Number planVer,
			Number planRev,
			Number planAlt,
			Number planUpdtNo,
			String partNo,
			String plannedOrderKeyList) throws Exception{

		// begin defect 1844
		String msg = null; 
		String[] plannedOrderKeyData = plannedOrderKeyList.split(";"); // defect 1844
		for (String plannedOrderKey: plannedOrderKeyData) {     //defect 1844

			List planInfo =  commonDaoPW.selectPlanInfoUsingPrimaryKey(plannedOrderKey);

			Iterator iterator = planInfo.iterator();

			

			if (iterator.hasNext()){

				Map map = (Map) iterator.next();
				partNo  = (String)  map.get("PART_NO");
				String partChg = (String)  map.get("PART_CHG");
				planId  = (String)  map.get("PLAN_ID");
				planVer = (Number)  map.get("PLAN_VERSION");
				planRev = (Number)  map.get("PLAN_REVISION");
				planAlt = (Number)  map.get("PLAN_ALTERATIONS");
				salesOrderNumber = (String) map.get("SALES_ORDER");
				engineType = (String) map.get("ENGINE_TYPE");
				engineModel = (String) map.get("ENGINE_MODEL");
				sapWorkLoc = (String) map.get("SAPWORKLOC");
				workScope = (String) map.get("WORK_SCOPE");
				superiorNet = (String) map.get("SUPERIORNET");
				subNet = (String) map.get("SUBNET");
				customer = (String) map.get("CUSTOMER");
				//partNoPassedIn
				planUpdtNo = (Number)  map.get("PLAN_UPDT_NO");
                //end first section of defect 1844


				PlanDataSelected planDataSelected =  new PlanDataSelected();
				planDataSelected.setPartNo(partNo);
				planDataSelected.setPartChg("N/A");
				planDataSelected.setPlanId(planId);
				planDataSelected.setPlanVer(planVer);
				planDataSelected.setPlanRev(planRev);
				planDataSelected.setPlanAlt(planAlt);

				planDataSelected.setPlanUpdtNo(planUpdtNo);

				UserEnteredData userEnteredData = new UserEnteredData();
				userEnteredData.setSalesOrderNumber(salesOrderNumber);
				userEnteredData.setEngineType(engineType);
				userEnteredData.setEngineModel(engineModel);
				userEnteredData.setSapWorkLoc(sapWorkLoc);
				userEnteredData.setWorkScope(workScope);	
				userEnteredData.setCustomer(customer);	
				userEnteredData.setSuperiorNet(superiorNet);	
				userEnteredData.setSubNet(subNet);	
				userEnteredData.setPartNoPassedIn(partNoPassedIn);

				String pwOrderNo = createOverhaulOrder(userEnteredData,planDataSelected,"MOVE_PLANNED_ORDER");
				//begin defect 1844
				if (StringUtils.contains(pwOrderNo, "Error")){
					if (StringUtils.isBlank(msg)){
						msg = "Error " + pwOrderNo ;
					}else{
						msg = msg + System.lineSeparator() +"Error " + pwOrderNo ;
					}		
				}else{
					orderCreateDaoPW.deleteFromPwustPlannedOrders(plannedOrderKey);
					String displayMsg = "Work Order " +pwOrderNo + " Created "; 
					if (StringUtils.isBlank(msg)){
						msg = displayMsg;
					}else{
						msg = msg + System.lineSeparator() + displayMsg;
					}
				}
			}

			//end defect 1844
		} //end for loop
		message.showMessage("MFI_20", msg);
		event.refreshTool();
	}

	public void createMroOverhaulOrders(String salesOrderNumber,
			String engineType,
			String engineModel,
			String sapWorkLoc,
			String workScopeString,
			String superiorNetString,
			String subNetString,
			String customerString,
			String partNoPassedIn,
			String plannedOrderFlag) throws Exception{

		// called from input matrix

		validator.checkUserPrivilege("PW_OH_PARENT_ORDER_CREATE"); // Defect 468.


		//String msg = "No plans found";

		pwOrderCheck(salesOrderNumber, engineType, engineModel, sapWorkLoc);

		UserEnteredData userEnteredData = null;
		userEnteredData = new UserEnteredData();
		userEnteredData.setSalesOrderNumber(salesOrderNumber);
		userEnteredData.setEngineType(engineType);
		userEnteredData.setEngineModel(engineModel);
		userEnteredData.setSapWorkLoc(sapWorkLoc);


		if (StringUtils.isNotBlank(workScopeString) && StringUtils.contains(workScopeString, ",")){
			//CAN , Cannibalize Engine (Module and Engine)
			String[] workScopeSplit = StringUtils.split(workScopeString, ",");
			if (workScopeSplit!=null && workScopeSplit.length >=1){
				userEnteredData.setWorkScope(workScopeSplit[0].trim());
				//String workScopeDesc = workScopeSplit[1].trim();
			}
		}else{
			userEnteredData.setWorkScope(workScopeString);	
		}

		if (StringUtils.isNotBlank(customerString) && StringUtils.contains(customerString, ",")){
			// 0000000002,AGES GROUP
			String[] customerSplit = StringUtils.split(customerString, ",");
			if (customerSplit!=null && customerSplit.length>=1){

				userEnteredData.setCustomer(customerSplit[0].trim());       
				//String customerDesc = customerSplit[1].trim();
			}

		}else{
			userEnteredData.setCustomer(customerString);	
		}

		if (StringUtils.isNotBlank(superiorNetString) && StringUtils.contains(superiorNetString, ",")){
			// 0120  , Front Bearing (1-2-3) Compartment
			String[] superiorNetSplit = StringUtils.split(superiorNetString, ",");
			if (superiorNetSplit!=null && superiorNetSplit.length>=1){
				userEnteredData.setSuperiorNet(superiorNetSplit[0].trim());
				//String superNetDesc = superiorNetSplit[1].trim();
			}
		}else{
			userEnteredData.setSuperiorNet(superiorNetString);	
		}

		if (StringUtils.isNotBlank(subNetString) && StringUtils.contains(subNetString, ",")){
			// 0070  , Assembly
			String[] subNetSplit = StringUtils.split(subNetString, ",");
			if (subNetSplit!=null && subNetSplit.length >=1){
				userEnteredData.setSubNet(subNetSplit[0].trim());
				//String subNetDesc = subNetSplit[1].trim();
			}
		}else{
			userEnteredData.setSubNet(subNetString);	
		}

		userEnteredData.setPartNoPassedIn(partNoPassedIn);
		userEnteredData.setPlannedOrderFlag(plannedOrderFlag);

		List planList = getPlansListForOrderCreate(engineType, 
				sapWorkLoc, 
				engineModel, 
				partNoPassedIn,
				userEnteredData);

		if (planList!=null && !planList.isEmpty()){

			Iterator iterator = planList.iterator();

			//msg = "plan found";

			while (iterator.hasNext()){

				Map map = (Map) iterator.next();
				String partNo  = (String)  map.get("PART_NO");
				String partChg = (String)  map.get("PART_CHG");
				String planId  = (String)  map.get("PLAN_ID");
				Number planVer = (Number)  map.get("PLAN_VERSION");
				Number planRev = (Number)  map.get("PLAN_REVISION");
				Number planAlt = (Number)  map.get("PLAN_ALTERATIONS");

				Map planUpdtNoMap = orderCreateDaoPW.selectPlanUpdtNo(planId,planVer,planRev,planAlt);
				Number planUpdtNo = (Number) planUpdtNoMap.get("PLAN_UPDT_NO");

				String nextImTableKey = orderCreateDaoPW.getNextImTableKey();
				orderCreateDaoPW.insertTempOrderData(nextImTableKey, 
						salesOrderNumber, 
						engineType, 
						engineModel, 
						sapWorkLoc, 
						userEnteredData.getWorkScope(), 
						partNoPassedIn, 
						userEnteredData.getSuperiorNet(), 
						userEnteredData.getSubNet(), 
						userEnteredData.getCustomer(),
						userEnteredData.getPlannedOrderFlag(),
						planId,
						planVer, 
						planRev, 
						planAlt, 
						planUpdtNo, 
						partNo);
			}
		}
	}

	public  void pwOrderCheck(String salesOrderNumber, String engineType, String engineModel, String sapWorkLoc) {

		if (StringUtils.isBlank(salesOrderNumber)){
			message.raiseError("MFI_20","salesOrderNumber cannot be blank");
		}
		if (StringUtils.isBlank(engineType)){
			message.raiseError("MFI_20","engineType cannot be blank");
		}
		if (StringUtils.isBlank(engineModel)){
			message.raiseError("MFI_20","engineModel cannot be blank");
		}
		if (StringUtils.isBlank(sapWorkLoc)){
			message.raiseError("MFI_20","sapWorkLoc cannot be blank");
		}


		if (salesOrderNumber.length()>20){
			message.raiseError("MFI_20","The Sales Order entered is too long. Please limit the Sales Order to 20 characters.");
		}
	}


	protected List getPlansListForOrderCreate(String engineType,
			String sapWorkLoc, 
			String engineModel, 
			String partNoPassedIn,
			UserEnteredData userEnteredData) {

		List newPlanList = new ArrayList();

		List planList = orderCreateDaoPW.selectPlansForWOCreate(engineType, 
				sapWorkLoc, 
				engineModel, 
				userEnteredData.getWorkScope(), 
				partNoPassedIn, 
				null, //startDate, 
				null, // endDate, 
				userEnteredData.getSuperiorNet(), 
				userEnteredData.getSubNet(), 
				userEnteredData.getCustomer());

		if (planList!=null && !planList.isEmpty()){

			Iterator iterator = planList.iterator();

			while (iterator.hasNext()){

				Map map = (Map) iterator.next();

				String partNo  = (String)  map.get("PART_NO");
				String partChg = (String)  map.get("PART_CHG");
				String planId  = (String)  map.get("PLAN_ID");
				Number planVer = (Number)  map.get("PLAN_VERSION");
				Number planRev = (Number)  map.get("PLAN_REVISION");
				Number planAlt = (Number)  map.get("PLAN_ALTERATIONS");


				boolean taskGroupAndPartsExits  =checkTaskGroupAndPartsExits(planId,planVer,planRev,planAlt,partNo,partChg);
				if (taskGroupAndPartsExits){
					newPlanList.add(map);
				}
			}
		}

		return newPlanList;
	}

	public void createMroOverhaulOrders(String salesOrderNumber,
			String engineType,
			String engineModel,
			String sapWorkLoc, 
			String workScope,
			String partNoPassedIn,
			String customer, // Defect 885
			String plannedOrderFlag) throws Exception{


		// called from input UDV

		String msg = null;

		plannedOrderFlag= (String)ContextUtil.getUser().getContext().get("NEW_PLANNED_ORDER_FLAG");
		ContextUtil.getUser().getContext().set("NEW_PLANNED_ORDER_FLAG","");

		UserEnteredData userEnteredData = null;

		pwOrderCheck(salesOrderNumber, engineType, engineModel, sapWorkLoc);

		//String todaysDate = orderCreateDaoPW.selectDBTimeStamp("MM/DD/YYYY");

		userEnteredData = new UserEnteredData();
		userEnteredData.setSalesOrderNumber(salesOrderNumber);
		userEnteredData.setEngineType(engineType);
		userEnteredData.setEngineModel(engineModel);
		userEnteredData.setSapWorkLoc(sapWorkLoc);
		userEnteredData.setWorkScope(workScope);
		userEnteredData.setCustomer(customer);   // Defect 885
		userEnteredData.setPartNoPassedIn(partNoPassedIn);
		userEnteredData.setPlannedOrderFlag(plannedOrderFlag);

		List planList = getPlansListForOrderCreate(engineType, 
				sapWorkLoc, 
				engineModel, 
				partNoPassedIn,
				userEnteredData);		

		if (planList!=null && !planList.isEmpty()){
			/*	Backing out for now		//Begin Defect 1006
			Iterator iterator = planList.iterator();

			//msg = "plan found";

			while (iterator.hasNext()){

				Map map = (Map) iterator.next();
				String partNo  = (String)  map.get("PART_NO");
				String partChg = (String)  map.get("PART_CHG");
				String planId  = (String)  map.get("PLAN_ID");
				Number planVer = (Number)  map.get("PLAN_VERSION");
				Number planRev = (Number)  map.get("PLAN_REVISION");
				Number planAlt = (Number)  map.get("PLAN_ALTERATIONS");

				Map planUpdtNoMap = orderCreateDaoPW.selectPlanUpdtNo(planId,planVer,planRev,planAlt);
				Number planUpdtNo = (Number) planUpdtNoMap.get("PLAN_UPDT_NO");

				String nextImTableKey = orderCreateDaoPW.getNextImTableKey();
				orderCreateDaoPW.insertTempOrderData(nextImTableKey, 
						salesOrderNumber, 
						engineType, 
						engineModel, 
						sapWorkLoc, 
						userEnteredData.getWorkScope(), 
						partNoPassedIn, 
						userEnteredData.getSuperiorNet(), 
						userEnteredData.getSubNet(), 
						userEnteredData.getCustomer(),
						userEnteredData.getPlannedOrderFlag(),
						planId,
						planVer, 
						planRev, 
						planAlt, 
						planUpdtNo, 
						partNo);
			}
				//End Defect 1006
			 */
			msg = createOrvalHaulOrdersInputUDV(planList,userEnteredData,"INPUT UDV");
			//      createOverhaulOrder          (UserEnteredData userEnteredData,PlanDataSelected planDataSelected, String calledFrom) throws Exception {	    	    
		}else{
			msg = "No plans found";
		}

		message.showMessage("MFI_20", msg);
		event.refreshTool();
	}



	private String  createOrvalHaulOrdersInputUDV(List planList, UserEnteredData userEnteredData, String calledFrom) throws Exception {

		String orderNo=null;
		String msg = null;
		PlanDataSelected planDataSelected = null;

		Iterator iterator = planList.iterator();

		while (iterator.hasNext()){

			Map map = (Map) iterator.next();

			String partNo  = (String)  map.get("PART_NO");
			String partChg = (String)  map.get("PART_CHG");
			String planId  = (String)  map.get("PLAN_ID");
			Number planVer = (Number)  map.get("PLAN_VERSION");
			Number planRev = (Number)  map.get("PLAN_REVISION");
			Number planAlt = (Number)  map.get("PLAN_ALTERATIONS");


			Map planUpdtNoMap = orderCreateDaoPW.selectPlanUpdtNo(planId,planVer,planRev,planAlt);
			Number planUpdtNo = (Number) planUpdtNoMap.get("PLAN_UPDT_NO");


			planDataSelected =  new PlanDataSelected();
			planDataSelected.setPartNo(partNo);
			planDataSelected.setPartChg(partChg);
			planDataSelected.setPlanId(planId);
			planDataSelected.setPlanVer(planVer);
			planDataSelected.setPlanRev(planRev);
			planDataSelected.setPlanAlt(planAlt);

			planDataSelected.setPlanUpdtNo(planUpdtNo);

			orderNo = createOverhaulOrder(userEnteredData,planDataSelected,calledFrom);
			String displayMsg = "Work Order " +orderNo + " Created "; 
			if (StringUtils.isBlank(msg)){
				msg = displayMsg;
			}else{
				msg = msg + System.lineSeparator() + displayMsg ;
			}

		}
		return 	msg;
	}

	protected String  createOverhaulOrder(UserEnteredData userEnteredData,PlanDataSelected planDataSelected, String calledFrom) throws Exception {

		String msg = null;
		String pwOrderNumber = null;

		String todaysDate = orderCreateDaoPW.selectDBTimeStamp("MM/DD/YYYY");
		String userName = ContextUtil.getUser().getUsername();

		if (StringUtils.equalsIgnoreCase(userEnteredData.getPlannedOrderFlag(), "Y")){

			savePlannedOrderData(userEnteredData,planDataSelected);
			msg =  "Planned Order " + userEnteredData.getSalesOrderNumber() + "-" +  planDataSelected.getPartNo() + "-XX";// +" Created"; (Defect 728)
			return msg;

		}else{

			pwOrderNumber =buildMROOrderNumber.buildOverhaulOrderNumber(userEnteredData.getSalesOrderNumber(),planDataSelected.getPartNo());

			Number orderQuantity=1;
			String effectivityType="DATE";
			String orderType="";

			List planEffDateList = orderCreateDaoPW.selectSfplPlannEff(planDataSelected.getPlanId(),
					planDataSelected.getPlanVer(),
					planDataSelected.getPlanRev(),
					planDataSelected.getPlanAlt(),
					"DATE");

			if (planEffDateList==null || planEffDateList.isEmpty() ){  // [{EFF_FROM=02/14/2018}, {EFF_FROM=02/13/2018}]
				msg =   "Error no plan Date eff found ";
			}else{

				msg= null;

				ContextUtil.getUser().getContext().set("LAUNCH_ORDER", "FALSE");
				ContextUtil.getUser().getContext().set("PW_ORDER_NO", pwOrderNumber);
				ContextUtil.getUser().getContext().set("ENGINE_MODEL", userEnteredData.getEngineModel());

				String orderNoAutoGenerated  = orderStringSequence.nextStringValue();

				List imPlanList1 = orderCreateDaoPW.selectTempOrderCreateDataPlans(planDataSelected.getPlanId(),
						planDataSelected.getPlanVer(), 
						planDataSelected.getPlanRev(),
						planDataSelected.getPlanAlt(),
						planDataSelected.getPlanUpdtNo());

				callOrderCreate.callOOBCreateOrder(userEnteredData.getSalesOrderNumber(), 
						userEnteredData.getEngineType(),
						userEnteredData.getEngineModel(),
						userEnteredData.getSapWorkLoc(),
						userEnteredData.getWorkScope(), 
						userEnteredData.getSuperiorNet(), 
						userEnteredData.getSubNet(), 
						userEnteredData.getCustomer(), 
						planDataSelected.getPlanId(),
						planDataSelected.getPlanVer(),
						planDataSelected.getPlanRev(),
						planDataSelected.getPlanAlt(), 
						planDataSelected.getPlanUpdtNo(), 
						planDataSelected.getPartNo(), 
						planDataSelected.getPartChg(), 
						todaysDate, 
						userName, 
						orderQuantity, 
						effectivityType, 
						orderType, 
						todaysDate,//scheduledStartDate,
						orderNoAutoGenerated, 
						pwOrderNumber, 
						calledFrom,
						imPlanList1);
			}
		}		
		return pwOrderNumber;
	}





	protected void savePlannedOrderData(UserEnteredData userEnteredData, PlanDataSelected planDataSelected) {


		orderCreateDaoPW.insertInfoPwustPlannedOrders(userEnteredData.getSalesOrderNumber(), // salesOrder, 
				userEnteredData.getEngineType(),       //engineType, 
				userEnteredData.getEngineModel(),      //engineModel, 
				userEnteredData.getSapWorkLoc(),       // sapWorkLoc, 
				userEnteredData.getWorkScope(),        // workScope, 
				userEnteredData.getPartNoPassedIn(),   //partNoSel, 
				userEnteredData.getSuperiorNet(),      //supNetworkAct, 
				userEnteredData.getSubNet(),           // subNetworkAct, 
				userEnteredData.getCustomer(),         // customer, 
				planDataSelected.getPlanId(),          // planId, 
				planDataSelected.getPlanVer(),         // planVer, 
				planDataSelected.getPlanRev(),         // planRev, 
				planDataSelected.getPlanAlt(),         // planAlt, 
				planDataSelected.getPlanUpdtNo(),     //  planUpdtNo);
				planDataSelected.getPartNo());

	}


	protected boolean checkTaskGroupAndPartsExits(String planId,
			Number planVer,
			Number planRev,
			Number planAlt,
			String partNo,
			String partChg) {

		// check out of box bus rules
		boolean returnValue=true;

		String planInstructionType = planDao.selectPlanInstructionsType(planId, planVer,planRev,planAlt);


		if (stringEquals(planInstructionType, MRO_PART)){
			boolean taskGroupPartExists = planDao.selectPlanPartExistsInAnySubject(planId,planVer,planRev,planAlt,partNo,partChg);
			if (!taskGroupPartExists){
				// Item No %V1 Item Rev %V2 does not exist in
				// any
				// task group of Plan Version %V3 and Plan
				// Revision %V4
				//message.raiseError("MFI_7E2C146ADB325650E04400144FA7B7D2",itemNo,itemRev,planVersion[0].toString(),	planRevision[0].toString());
				returnValue=false;
			}

		}else if (stringEquals(planInstructionType, MRO_UPGRADE)){
			boolean taskGroupPartExists = planDao.selectPlanPartExistsInAnySubject(planId,planVer,planRev,planAlt,partNo,partChg);
			if (!taskGroupPartExists){
				// Item No %V1 Item Rev %V2 does not exist in
				// any
				// task group of Plan Version %V3 and Plan
				// Revision %V4
				//message.raiseError("MFI_7E2C146ADB325650E04400144FA7B7D2",startingItemNo,startingItemRev,planVersion[0].toString(),	planRevision[0].toString());
				returnValue=false;
			}
		}

		return returnValue;
	}



	public void createMroOverhaulOrdersSelectUDV(String salesOrderNumber,
			String engineType,
			String engineModel,
			String workLocation, 
			String workScope,
			String customer,
			String partNo,
			String plannedOrderFlag,
			String inputMatrixFlag){


		validator.checkUserPrivilege("PW_OH_PARENT_ORDER_CREATE"); // Defect 468.

		StringBuilder params = new StringBuilder();

		String udvId=null;
		String UDVcaption=null;
		String hideColumns = null;
		String sqlTrans = null;

		if (StringUtils.equalsIgnoreCase(inputMatrixFlag, "N")){
			udvId = "PWUST_5DE0C822DB68FB50E05387971F0A4114";
			UDVcaption = "Create Overhaul Orders";    
			sqlTrans = 	SoluminaConstants.INSERT;

			params.append("NEW_SALES_ORDER=" + wrapUtils.wrapValue(salesOrderNumber));
			params.append(",NEW_ENGINE_TYPE=" + wrapUtils.wrapValue(engineType));
			params.append(",NEW_ENGINE_MODEL=" + wrapUtils.wrapValue(engineModel));
			params.append(",NEW_SAP_WORK_LOC=" + wrapUtils.wrapValue(workLocation));
			params.append(",NEW_WORK_SCOPE=" + wrapUtils.wrapValue(workScope));
			params.append(",NEW_CUSTOMER=" + wrapUtils.wrapValue(customer));
			params.append(",NEW_PART_NO=" + wrapUtils.wrapValue(partNo));
			params.append(",NEW_PLANNED_ORDER_FLAG =" + wrapUtils.wrapValue(plannedOrderFlag));

			ContextUtil.getUser().getContext().set("NEW_PLANNED_ORDER_FLAG", plannedOrderFlag);

			//ContextUtil.putContextStore(key, value);

		}else if (StringUtils.equalsIgnoreCase(inputMatrixFlag, "Y")){

			udvId =  "PWUST_5DE17AF441ABFB35E05387971F0A59E2";
			UDVcaption = "Create Overhaul Orders Input Matrix ";
			sqlTrans = 	SoluminaConstants.TRANSACTION_INPUT_MATRIX;


			params.append("NEW_SALES_ORDER=" + wrapUtils.wrapValue(salesOrderNumber));
			params.append(",NEW_ENGINE_TYPE=" + wrapUtils.wrapValue(engineType));
			params.append(",NEW_ENGINE_MODEL=" + wrapUtils.wrapValue(engineModel));
			params.append(",NEW_SAP_WORK_LOC=" + wrapUtils.wrapValue(workLocation));

			//params.append(",NEW_SUP_NET_ACT= 'DUMMY' "  );
			//params.append(",NEW_SUB_NET_ACT= 'DUMMY' "  );
			//params.append(",NEW_WORK_SCOPE=" + wrapUtils.wrapValue(workScope));


			params.append(",@SqlSourceName=@Auxparams");

			ContextUtil.getUser().getContext().set("NEW_SALES_ORDER", salesOrderNumber);
			ContextUtil.getUser().getContext().set("NEW_ENGINE_TYPE", engineType);
			ContextUtil.getUser().getContext().set("NEW_ENGINE_MODEL", engineModel);
			ContextUtil.getUser().getContext().set("NEW_SAP_WORK_LOC", workLocation);

			hideColumns = "ORDER_ID";

			/*
    		hideColumns = "PLAN_TITLE,"
    				    + "ALT_NO,"
    				    + "ORDER_STATUS,"
    				    + "SUB_NETWORK_ACTIVITY_DESC,"
    				    + "ALT_STATUS,"
    				    + "ORDER_HOLD_STATUS,"
    				    + "ASASGND_WORK_LOC,"
    				    + "CREATE_DATE,"
    				    + "CREATE_BY,"
    				    + "RELEASE_DATE,"
    				    + "CREATE_BY_1,"
    				    + "ORDER_TYPE,"
    				    + "PLAN_NO,"
    				    + "PART_NO,"
    				    + "PARENT_ORDER_ID,"
    				    + "ALTERED_BY,"
    				    + "REASON_FOR_CHANGE,"
    				    + "SECURITY_GROUP";*/

		}

		// Insert a row in SFCORE_SESSION_EVENT_QUEUE
		event.showUdv(udvId,
				sqlTrans,//"INSERT", //sqlTrans,
				UDVcaption, //captionHead + ORDER_CREATE,
				hideColumns,
				params.toString());

	}



	protected void traceCreateOrderCheckForDateEffectivity(String planId,
			String orderNumber, 
			String scheduledStartDate,
			String effectivityType,
			PlanDataSelected planDataSelected,
			UserEnteredData userEnteredData){

		String errMsg = " planId =" +planId + 
				" orderNumber = " + orderNumber + 
				" scheduledStartDate = " + scheduledStartDate +
				" effectivityType = " + effectivityType;

		Date localScheduledStartDate = null;
		String partNumber = null;
		if ((scheduledStartDate == null)|| (contains(scheduledStartDate, "/ /")))
		{
			// 	Message Text: Schedule Start Date cannot be blank
			//message.raiseError("MFI_55497");
			logOrderCreateErrors.insertIntoErrorTable("MFI_55497  Schedule Start Date cannot be blank error, "+ errMsg,
					orderNumber,//pwOrderNumber,
					planDataSelected.getPartNo(),//planDataSelected.getPartNo(),
					planId,//planDataSelected.getPlanId(),
					planDataSelected.getPlanVer(),//
					planDataSelected.getPlanRev(),// planRevision,
					planDataSelected.getPlanAlt(),// planAlterations,
					planDataSelected.getPlanUpdtNo(),
					userEnteredData.getSalesOrderNumber(),
					userEnteredData.getEngineType(),
					userEnteredData.getEngineModel(),
					userEnteredData.getSapWorkLoc(),
					userEnteredData.getWorkScope(),
					userEnteredData.getSuperiorNet(),
					userEnteredData.getSubNet(),
					userEnteredData.getCustomer(),
					"OOB plan eff check");//calledFrom);

			return ;
		}else{

			try{
				// Message Text: Schedule Start Date cannot be blank
				localScheduledStartDate = SoluminaUtils.parseDate(scheduledStartDate);

			}catch (ParseException e){
				//message.raiseError("MFI_50848", parameters.getClientSessionParameter(DATE_MASK_NAME));
				logOrderCreateErrors.insertIntoErrorTable("MFI_50848  ParseException " + errMsg,
						orderNumber,//pwOrderNumber,
						planDataSelected.getPartNo(),//planDataSelected.getPartNo(),
						planId,//planDataSelected.getPlanId(),
						planDataSelected.getPlanVer(),//
						planDataSelected.getPlanRev(),// planRevision,
						planDataSelected.getPlanAlt(),// planAlterations,
						planDataSelected.getPlanUpdtNo(),
						userEnteredData.getSalesOrderNumber(),
						userEnteredData.getEngineType(),
						userEnteredData.getEngineModel(),
						userEnteredData.getSapWorkLoc(),
						userEnteredData.getWorkScope(),
						userEnteredData.getSuperiorNet(),
						userEnteredData.getSubNet(),
						userEnteredData.getCustomer(),
						"OOB plan eff check");//calledFrom);
						return ;
			}
		}

		//Map<String, Object> planInformation = orderDao.selectPlanEffectivityInformation(planId,effectivityType,	localScheduledStartDate);
		Map<String, Object> planInformation = null;
		try{
			planInformation = orderCreateDaoPW.selectPlanEffectivityInformation(planId,effectivityType,	localScheduledStartDate);
		}catch (Exception e) {
			String errMsg1 = e.getMessage();
			logOrderCreateErrors.insertIntoErrorTable("orderCreateDaoPW.selectPlanEffectivityInformation(planId,effectivityType,localScheduledStartDate) errMsg" + errMsg + errMsg1,
					orderNumber,//pwOrderNumber,
					planDataSelected.getPartNo(),//planDataSelected.getPartNo(),
					planId,//planDataSelected.getPlanId(),
					planDataSelected.getPlanVer(),//
					planDataSelected.getPlanRev(),// planRevision,
					planDataSelected.getPlanAlt(),// planAlterations,
					planDataSelected.getPlanUpdtNo(),
					userEnteredData.getSalesOrderNumber(),
					userEnteredData.getEngineType(),
					userEnteredData.getEngineModel(),
					userEnteredData.getSapWorkLoc(),
					userEnteredData.getWorkScope(),
					userEnteredData.getSuperiorNet(),
					userEnteredData.getSubNet(),
					userEnteredData.getCustomer(),
					"OOB plan eff check");//calledFrom);


			return ;
		}

		if (planInformation.size() <= 0){
			// Message Text: Schedule Start Date is required and must be within the effectivity range.
			//message.raiseError("MFI_52561");
			logOrderCreateErrors.insertIntoErrorTable("MFI_52561  Schedule Start Date is required and must be within the effectivity range " + errMsg,
					orderNumber,//pwOrderNumber,
					planDataSelected.getPartNo(),//planDataSelected.getPartNo(),
					planId,//planDataSelected.getPlanId(),
					planDataSelected.getPlanVer(),//
					planDataSelected.getPlanRev(),// planRevision,
					planDataSelected.getPlanAlt(),// planAlterations,
					planDataSelected.getPlanUpdtNo(),
					userEnteredData.getSalesOrderNumber(),
					userEnteredData.getEngineType(),
					userEnteredData.getEngineModel(),
					userEnteredData.getSapWorkLoc(),
					userEnteredData.getWorkScope(),
					userEnteredData.getSuperiorNet(),
					userEnteredData.getSubNet(),
					userEnteredData.getCustomer(),
					"OOB plan eff check");//calledFrom);
					return ;

		}

		//planVersion[0] = (Number) planInformation.get("PLAN_VERSION");
		//planRevision[0] = (Number) planInformation.get("PLAN_REVISION");
		//planAlterations[0] = (Number) planInformation.get("PLAN_ALTERATIONS");

		try{
			Map uniquePart = planDao.selectUniquePartNumber(planId,planDataSelected.getPlanVer(),planDataSelected.getPlanRev(),planDataSelected.getPlanAlt());

			partNumber = (String) uniquePart.get("PART_NO");

		}
		catch (EmptyResultDataAccessException erdae)
		{

		}
		int lotCount = orderDao.selectLotDescriptionCount(orderNumber, partNumber);

		if (lotCount > 0)
		{
			// Message Text: Order No cannot be equal to any existing Lot No for that same Part.
			//message.raiseError("MFI_20642");
			logOrderCreateErrors.insertIntoErrorTable("MFI_20642  Schedule Start Date is required and must be within the effectivity range " + errMsg,
					orderNumber,//pwOrderNumber,
					planDataSelected.getPartNo(),//planDataSelected.getPartNo(),
					planId,//planDataSelected.getPlanId(),
					planDataSelected.getPlanVer(),//
					planDataSelected.getPlanRev(),// planRevision,
					planDataSelected.getPlanAlt(),// planAlterations,
					planDataSelected.getPlanUpdtNo(),
					userEnteredData.getSalesOrderNumber(),
					userEnteredData.getEngineType(),
					userEnteredData.getEngineModel(),
					userEnteredData.getSapWorkLoc(),
					userEnteredData.getWorkScope(),
					userEnteredData.getSuperiorNet(),
					userEnteredData.getSubNet(),
					userEnteredData.getCustomer(),
					"OOB plan eff check");//calledFrom);
					return ;
		}	
	}

}

