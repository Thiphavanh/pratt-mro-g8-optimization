/*
*  This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017,2020,2021.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    PlanImplPW.java
* 
*  Created: 2017-08-24
* 
*  Author:  xcs4331
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-08-24 	xcs4331		    Initial Release XXXXXXX
* 2017-10-13    D.Miron         SRMO_EXPT_201 - Added method returnToPriorPlanningTask.
* 2017-09-25    R.Thorpe        SMRO_PLG_205  - Defect 91 Add method insertCustomTaskGroupInformationForCopy, insertCustomTaskGroupInformationForRevision
* 2017-11-07    R.Thorpe        Defect 181    - change calls to Insert Custom Task group information on Plan Revision methods in insertCustomTaskGroupInformationForRevision 
* 2017-11-13    D.Miron         Defect 183    - Added methods setDefaultSecurity and assignSecurityGrpToPlan.  Updated insertPlan.
* 2017-12-06    N.Gnassounou    SMRO_PLG_205  - Defect 199 -  Update Task Group error message when selecting Complete Task   
* 2018-01-02    R.Thorpe        SMRO_PLG_205  - Defect 186 - SCR-004
* 2018-02-27	B. Preston		Defect 323    - Fix exceptions
* 2018-03-27	R.Cannon		SMRO_CV_202	  -	Moved code within insertPlan to insertPlanAndReturnKeyInfo. insertPlan changed to call insertPlanAndReturnKeyInfo
* 2018-03-28	R.Cannon		SMRO_CV_202	  - Removed insertPlan method
* 2018-06-18    Sam Niu         SMRO_TR_203   - defect 805 - Alteration Completion check for copied operations  
* 2018-10-16    R.Thorpe	    R2 Upgrade    - Defect 998 - extended OOB method (startPlanRevision) changed with R2
* 2018-10-16    R.Thorpe        SMRO_APP_301  - Added checks for REPAIR vs. OVERHAUL to insertCustomTaskGroupInformationForCopy, insertCustomTaskGroupInformationForRevision
* 2018-10-22    Fred Ettefagh   defect 977    - Copying plans uses 1 item for tasks and another for the plans
* 2018-11-02	R.Thorpe        SMRO_PLG_205  - Defect 1020 Plan copy does not copy over workscopes, superior and sub networks.
* 2018-12-17    Fred Ettefagh   Defect 1044,   fix repair order create
* 2019-04-26	B. Preston		Defect 1201   - Engine Type is optional when copying a plan for Repair.
* 2019-05-21	B. Preston		Defect 1207   - Check if source Engine Type is null during copy.
* 2019-08-30	German Rodriguez defect 1317  - Security groups do not populate properly when copying between sites
* 2019-10-17    Fred Ettefagh   SMRO_PLG_205  - Defect 1097  - for overhaul plans only, update all task parts with plan task parts
* 2020-03-23    John DeNinno	defect 1485   - task group was disappearing
* 2020-09-01	Scott Edgerly	Defect 1756: Auto-fill location, department, and center on ops when copying plans
* 2020-09-21	Scott Edgerly	Defect 1785: Java Error Creating Standard Operation
* 2020-09-23	Scott Edgelry 	Defect 1786: Java Error when creating Repair or Overhaul Process Plans
* 2020-10-28    David Miron     Defect 1807 - Moved code added for Defects 1756,1785,1786 from insertPlan to insertPlanAndReturnKeyInfo
* 2020-10-30    David Miron     Defect 1807 - Added call to deleteAllWorkPlanSecurityGroups
* 2021-03-18    John DeNinno	Defect 1870 - commented out the hard stop if there was no operation assigned to a task group
* 2021-05-03    John DeNinno	Defect 1902 - copy plan Task Authority tab information was missing
* 2021-05-11    John DeNinni    Defect 1914 . remove plan authority values when the plan revision is deleted
* 2021-05-13    John DeNinno    Defect 1902 . removed code by commenting
* 2021-06-09    John DeNinno    Defect 1870 - added popup to warn user about using commas in the notes
*/

package com.pw.solumina.sfpl.application.impl;

import static com.ibaset.common.DbColumnNameConstants.PLAN_ALTERATIONS;
import static com.ibaset.common.DbColumnNameConstants.PLAN_ID;
import static com.ibaset.common.DbColumnNameConstants.PLAN_REVISION;
import static com.ibaset.common.DbColumnNameConstants.PLAN_VERSION;
import static com.ibaset.common.DbColumnNameConstants.SECURITY_GROUP;
import static com.ibaset.common.FrameworkConstants.NO;
import static com.ibaset.common.SoluminaConstants.MRO_PART;
import static com.ibaset.common.SoluminaConstants.MRO_UPGRADE;
import static com.ibaset.common.SoluminaConstants.PROCESS_PLAN_TEXT;
import static com.ibaset.common.util.SoluminaUtils.stringEquals;




import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.OutputParameter;
import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.dao.IPlanObjectDao;
import com.ibaset.solumina.sffnd.dao.ISecurityGroupDao;
import com.ibaset.solumina.sfor.dao.ISfplPlanDao;
import com.ibaset.solumina.sfpl.application.IPlan;
import com.ibaset.solumina.sfpl.application.impl.PlanImpl;
import com.ibaset.solumina.sfpl.dao.IPlanDao;
import com.ibaset.solumina.sfpl.dao.impl.PlanDaoImpl;
import com.pw.solumina.authority.application.dao.IAuthorityDao;
import com.pw.solumina.authority.application.impl.AuthorityImpl;
import com.pw.solumina.migration.application.MigrationVariablesPW;
import com.pw.solumina.migration.application.SoluminaMigrationDaoPW;
import com.pw.solumina.sffnd.application.CommunicationPW;
import com.pw.solumina.sfor.dao.TaskGroupsDaoPW;
import com.pw.solumina.sfpl.dao.PlanDaoPW;
import com.utas.solumina.sfpl.application.IUtasPlan;
import com.utas.solumina.sfpl.dao.IUtasPlanDao;
import com.pw.solumina.authority.application.dao.impl.AuthorityDaoImpl;

public abstract class PlanImplPW extends ImplementationOf<com.ibaset.solumina.sfpl.application.IPlan> implements IPlan{

	@Reference
	protected PlanDaoImplPW planDaoImplPW = null;
	@Reference
	protected PlanDaoImpl planDaoImpl = null;
	@Reference
	protected IMessage message = null;
	@Reference 
	TaskGroupsDaoPW taskGroupsDaoPW = null;
	@Reference IPlanDao planDao = null;
	@Reference 
	private PlanDaoPW planDaoPW = null;
	
	@Reference 
	private CommunicationPW commPW = null;
	@Reference
	private ISecurityGroupDao fndSecurityGroupDao; 
	@Reference
	private IUtasPlanDao utasPlanDao;
	@Reference
	private IUtasPlan utasPlan;
	@Reference
	private IPlanObjectDao planObjectDao = null; 
	@Reference
	private ISfplPlanDao sfplPlanDao = null;
	
	@Reference
	private SoluminaMigrationDaoPW soluminaMigrationDaoPW;
	
	@Reference
	private AuthorityImpl authorityImpl;
	@Reference AuthorityDaoImpl authorityDaoImpl;
	
	


	
//begin defect 1914
	@Override
	public void planDelete(String planId, Number planVersion, Number planRevision, Number planAlterations,
			String refresh, String planNumber, String calledFrom) {
		
		Super.planDelete(planId, planVersion, planRevision, planAlterations, refresh, planNumber, calledFrom);
		
		authorityDaoImpl.deletePlanTaskAuthByRevision(planId,planRevision); //defect 1914
		
	} //end defect 1914

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map insertPlanAndReturnKeyInfo(String partNumber,
				           				  String partChange,
				           				  String planType,
				           				  String itemType,
				           				  String itemSubtype,
				           				  String program,
				           				  String project,
				           				  String plannedWorkLocation,
				           				  Number planVersion,
				           				  Number planRevision,
				           				  Number planAlterations,
				           				  String planTitle,
				           				  Number plannedOrderQuantity,
				           				  String plannedCustomerId,
				           				  String model,
				           				  String engineeringPartNumber,
				           				  String engineeringPartChange,
				           				  String engineeringGroup,
				           				  String taskId,
				           				  String lowestNavigationLevel,
				           				  String changeDocumentType,
				           				  String pwpId, 
				           				  String sourcePartNumber, 
				           				  String sourcePartChange,
				           				  String sourcePlanNumber,
				           				  String sourceItemType,
				           				  String sourceItemSubtype,
				           				  String sourceProgram,
				           				  Number sourcePlanVersion,
				           				  Number sourcePlanRevision,
				           				  Number sourcePlanAlterations,
				           				  String copyComponentPartsFlag,
				           				  String calledFrom,
				           				  String ucfPlanVch1,
				           				  String ucfPlanVch2,
				           				  String ucfPlanVch3,
				           				  String ucfPlanVch4,
				           				  String ucfPlanVch5,
				           				  String ucfPlanVch6,
				           				  String ucfPlanVch7,
				           				  String ucfPlanVch8,
				           				  String ucfPlanVch9,
				           				  String ucfPlanVch10,
				           				  String ucfPlanVch11,
				           				  String ucfPlanVch12,
				           				  String ucfPlanVch13,
				           				  String ucfPlanVch14,
				           				  String ucfPlanVch15,
				           				  Number ucfPlanNum1,
				           				  Number ucfPlanNum2,
				           				  Number ucfPlanNum3,
				           				  Number ucfPlanNum4,
				           				  Number ucfPlanNum5,
				           				  String ucfPlanFlag1,
				           				  String ucfPlanFlag2,
				           				  String ucfPlanFlag3,
				           				  String ucfPlanFlag4,
				           				  String ucfPlanFlag5,
				           				  Date ucfPlanDate1,
				           				  Date ucfPlanDate2,
				           				  Date ucfPlanDate3,
				           				  Date ucfPlanDate4,
				           				  Date ucfPlanDate5,
				           				  String ucfPlanVch2551,
				           				  String ucfPlanVch2552,
				           				  String ucfPlanVch2553,
				           				  String ucfPlanVch40001,
				           				  String ucfPlanVch40002,
				           				  String fromPlanType,
				           				  String displaySequence,
				           				  String documentType,
				           				  String mfgBomRev,
				           				  String bomNumber,
				           				  String workFlow, 
				           				  String finalStores, 
				           				  String inspectionPlanId, 
				           				  String bomId,
				           				  String changeRequestId,
				           				  String plannedActionId, 
				           				  String explicitBomLink,
				           				  String ppvRequiredFlag,
				           				  String ppvType,
				           				  Number ppvQuantity, 
				           				  String associateChange,
				           				  String orderUOM,
				           				  String calledFromExternalSystem,
				           				  String workOrderLotNumberFlag,
				           				  String workOrderSerialNumberFlag){
    	
    	displaySequence = "Execution Order";

    	Map newPlanKey = Super.insertPlanAndReturnKeyInfo(partNumber, partChange, planType, itemType, itemSubtype, program, project, plannedWorkLocation, planVersion, planRevision, planAlterations, planTitle, 
    													  plannedOrderQuantity, plannedCustomerId, model, engineeringPartNumber, engineeringPartChange, engineeringGroup, taskId, lowestNavigationLevel, 
    													  changeDocumentType, pwpId, sourcePartNumber, sourcePartChange, sourcePlanNumber, sourceItemType, sourceItemSubtype, sourceProgram, sourcePlanVersion, 
    													  sourcePlanRevision, sourcePlanAlterations, copyComponentPartsFlag, calledFrom, ucfPlanVch1, ucfPlanVch2, ucfPlanVch3, ucfPlanVch4, ucfPlanVch5, 
    													  ucfPlanVch6, ucfPlanVch7, ucfPlanVch8, ucfPlanVch9, ucfPlanVch10, ucfPlanVch11, ucfPlanVch12, ucfPlanVch13, ucfPlanVch14, ucfPlanVch15, ucfPlanNum1, 
    													  ucfPlanNum2, ucfPlanNum3, ucfPlanNum4, ucfPlanNum5, ucfPlanFlag1, ucfPlanFlag2, ucfPlanFlag3, ucfPlanFlag4, ucfPlanFlag5, ucfPlanDate1, ucfPlanDate2, 
    													  ucfPlanDate3, ucfPlanDate4, ucfPlanDate5, ucfPlanVch2551, ucfPlanVch2552, ucfPlanVch2553, ucfPlanVch40001, ucfPlanVch40002, fromPlanType, displaySequence, 
    													  documentType, mfgBomRev, bomNumber, workFlow, finalStores, inspectionPlanId, bomId, changeRequestId, plannedActionId, explicitBomLink, ppvRequiredFlag, 
    													  ppvType, ppvQuantity, associateChange, orderUOM, calledFromExternalSystem, workOrderLotNumberFlag, workOrderSerialNumberFlag);
    	
    	
    	String newPlanId =  (String) newPlanKey.get("PLAN_ID");
    	Number newPlanVersion = (Number) newPlanKey.get("PLAN_VERSION");
    	Number newPlanRevision = (Number) newPlanKey.get("PLAN_REVISION");
    	Number newPlanAlterations = (Number) newPlanKey.get("PLAN_ALTERATIONS");
    	
       	
        Number newPlanUpdtNo = (Number) planDao.selectPlanUpdateNumberFromPlan(newPlanId, newPlanVersion, newPlanRevision, newPlanAlterations);
    	// get newEngineType from plan
    	String newEngineType = (String) planDaoImplPW.selectPlanEngineType(newPlanId, newPlanVersion, newPlanRevision, newPlanAlterations);      // SMRO_PLG_205 SCR-004
    	
    	if(!StringUtils.isEmpty(sourcePartNumber) && sourcePartNumber != null)
    	{
    		// Defect 91 Insert Custom Task Group Information when copying a plan
    		Map sourceMap = planDaoImplPW.selectSourcePlanMap(sourcePlanNumber, sourcePlanVersion, sourcePlanRevision, sourcePlanAlterations);

    		String sourcePlanId =(String) sourceMap.get("PLAN_ID");
    		Number sourcePlanUpdtNo = (Number) sourceMap.get("PLAN_UPDT_NO");
    		String sourceEngineType = (String) sourceMap.get("PROGRAM");   // SMRO_PLG_205 SCR-004
    		
    		String changeEngineTypeFlag = "N";     // SMRO_PLG_205 SCR-004
    		//check if PROGRAM aka enginetype from source plan and new are the same or not
    		if ((newEngineType == null) || (sourceEngineType == null) || !newEngineType.contentEquals(sourceEngineType)) // SMRO_PLG_205 SCR-004, defect 1201, 1207
    		{
    			changeEngineTypeFlag = "Y";
    			insertCustomTaskGroupInformationForCopy(sourcePlanId, sourcePlanVersion, sourcePlanRevision, sourcePlanUpdtNo, newPlanId, newPlanUpdtNo, changeEngineTypeFlag, newEngineType);
    		}
    		else
    		{
    			insertCustomTaskGroupInformationForCopy(sourcePlanId, sourcePlanVersion, sourcePlanRevision, sourcePlanUpdtNo, newPlanId, newPlanUpdtNo, changeEngineTypeFlag, sourceEngineType);
    		}
    		
    		List<String> engineModelList = planDaoImplPW.selectPwustPlanEngineModel(sourcePlanId, sourcePlanVersion, sourcePlanRevision, sourcePlanAlterations);
    		for (String engineModel : engineModelList) {
    			planDaoImplPW.insertIntoPwustPlanEngineModel(newPlanId, newPlanUpdtNo, engineModel);
    		}
    		
    		// defect 977 start
    		if (StringUtils.equalsIgnoreCase(planType, "OVERHAUL")){

    			// FE defect 1097
    			// overhaul plans only
    			// task part number must be the same an plan part number
    			Map sfplItemDescMasterAllMap = planDaoImplPW.selectSfplItemDescMasterAll(partNumber, partChange);
    			planDaoImplPW.updateOverHaulPlanSubjectItemID(newPlanId, 
    					                                      newPlanUpdtNo, 
    					                                      (String)sfplItemDescMasterAllMap.get("PART_NO"), 
    					                                      (String)sfplItemDescMasterAllMap.get("PART_CHG"), 
    					                                      (String)sfplItemDescMasterAllMap.get("PART_TITLE"), 
    					                                      (String)sfplItemDescMasterAllMap.get("ITEM_ID"));
    		}
    		// defect 977 end
    		
			//defect 1902 add task authorities begin
    		// Defect 91 Insert Custom Task Group Information when copying a plan  
			
			List operList = soluminaMigrationDaoPW.querySubjectAuthorities(sourcePlanId, sourcePlanRevision);
			ListIterator operIter = operList.listIterator();
			

			while(operIter.hasNext()) {
				Map operInfo = (Map) operIter.next();
				Number exe_order = (Number) operInfo.get("EXE_ORDER");
				
				//String planId = new_plan_id;
				//Number planRevision = MigrationVariablesPW.NEW_PLAN_REV;
				Number subjectNo = (Number) operInfo.get("SUBJECT_NO");
				Number subjectRev = MigrationVariablesPW.NEW_PLAN_REV;
				String authorityType = (String) operInfo.get("AUTHORITY_TYPE");
				String authorityDetail = (String) operInfo.get("AUTHORITY_DETAIL");
				String manualNo = (String) operInfo.get("MANUAL_NO");
				String manualRev = (String) operInfo.get("MANUAL_REV");
				String manualProvider = (String) operInfo.get("MANUAL_PROVIDER");
				String manualType = (String) operInfo.get("MANUAL_TYPE");
				String manualSection = (String) operInfo.get("MANUAL_SECTION");
				String sbPartNo = (String) operInfo.get("SB_PART_NO");
				String authorityRev = (String) operInfo.get("AUTHORITY_REV");
				Date authorityRevDate = (Date) operInfo.get("AUTHORITY_REV_DATE");

				String subjectDesc =(String) operInfo.get("SUBJECT_DESCRIPTION");
				
				try{
				authorityImpl.insertPlanTaskAuth(newPlanId, newPlanRevision, subjectNo.toString(), subjectRev.toString(), subjectDesc, authorityType,
												authorityDetail, manualNo, manualRev, manualProvider, 
												manualType, manualSection, sbPartNo, authorityRev, 
												authorityRevDate);
				}
				catch(Exception e){
					StringBuffer errMessage = new StringBuffer();
					errMessage.append(e.getMessage());
					errMessage.append("\nTask Group Number: " + subjectNo);
					errMessage.append("\n  Task Rev Number: " + subjectRev);
					errMessage.append("\n           Manual: " + manualNo);
					errMessage.append("\n   Authority Type: " + authorityType);
					errMessage.append("\n  Authority Notes: " + authorityDetail);
					errMessage.append("\nSee TOOL_NO for Manual Name/Number");
					soluminaMigrationDaoPW.insertErrorLogG8("TASK_AUTH_INSERT", MigrationVariablesPW.WARN, newPlanId,
							MigrationVariablesPW.PLAN_UPDT_NO, "", MigrationVariablesPW.NA_ERR,
							manualNo, 0, errMessage.toString(), MigrationVariablesPW.SFMFG);
				}
			}

			//defect 1902 add task authorities end
    		
    	}
    
    	// Defect 1807
    	if("UDV_CREATE".equals(calledFrom) && sourcePartNumber!=null){//defect 1785, 1786
    		String toPlanId = newPlanKey.get("PLAN_ID").toString();
	    	
    		
	    	Map toWorkLocationData = (Map) planDaoPW.getWorkLocationData(plannedWorkLocation);
	    	//need to test, disable if statement to check calledFrom parameter.
	    	if(!toWorkLocationData.isEmpty()){
	    		String toWorkLocationId = (String) toWorkLocationData.get("LOCATION_ID");
		    	//source plan data
		    	//planDao.selectPlanId(sourcePartNumber, sourceItemType, sourceItemSubtype, sourceProgram);
		    	String sourcePlanId = planDao.selectPlanIdByPlanNumber(sourcePartNumber, sourcePlanNumber, documentType, fromPlanType);
		    	
		    	//get source plan work location
		    	//defect 1786
				Map sourcePlanWorkLocationData = planDaoPW.selectPlanWorkLocationData(sourcePlanId,sourcePlanVersion, sourcePlanRevision, sourcePlanAlterations);
				if(!sourcePlanWorkLocationData.isEmpty()){ //if user does not enter a plan to copy from
					String sourceWorkLocation = (String)sourcePlanWorkLocationData.get("WORK_LOC");
					//String sourceWorkLocation = planDaoPW.selectPlanWorkLocation(sourcePlanId, sourcePlanNumber);
					if(toWorkLocationId != null && !sourceWorkLocation.equals(plannedWorkLocation)){
						updateCopiedPlanOperationsWorkLocation(toPlanId, toWorkLocationId);
					}
				}
	    	}
    	}
    	// End Defect 1807

    	// Defect 183
        if(StringUtils.equals(ucfPlanFlag4,SoluminaConstants.Y))
        {
            // This is a restricted plan – PAAR review
           String requestId = utasPlan.requestPaarReviewForNewPlan(newPlanId, newPlanVersion, newPlanRevision, newPlanAlterations, newPlanUpdtNo);
        }
        else
        {        
        	planDaoImplPW.deleteAllWorkPlanSecurityGroups(newPlanId, newPlanUpdtNo);  // Defect 1807
    		
    		planDaoImplPW.updateSecurityGrpToNull(newPlanId);//Defect 1317
        	
            // Plan only needs Default Security
            setDefaultSecurity(newPlanId, newPlanUpdtNo, partNumber, plannedWorkLocation);
        }
        return newPlanKey;

    } //end insertPlanAndReturnKeyInfo()

	
	public void updatePlan(String planId,
				            String partNo,
				            String itemType,
				            String itemSubtype,
				            String partChg,
				            String planType,
				            String program,
				            Number planVersion,
				            Number planRevision,
				            Number planAlterations,
				            String lowNavLvl,
				            String plndWorkLoc,
				            String planTitle,
				            Number plndOrderQty,
				            String plndCustId,
				            String model,
				            String mfgIndexNo,
				            String plgGroup,
				            String engPartNo,
				            String engPartChg,
				            String engGroup,
				            String project,
				            String plndMaxOrderQty,
				            String plndMinOrderQty,
				            String initialStores,
				            String finalStores,
				            String mfgBomChg,
				            String unitType,
				            String planNumber,
				            String condition,
				            String ucfPlanVch1,
				            String ucfPlanVch2,
				            String ucfPlanFlag1,
				            String ucfPlanVch3,
				            String ucfPlanVch4,
				            String ucfPlanVch5,
				            String ucfPlanVch6,
				            String ucfPlanVch7,
				            String ucfPlanVch8,
				            String ucfPlanFlag2,
				            Number ucfPlanNum1,
				            Number ucfPlanNum2,
				            String serialFlag,
				            String lotFlag,
				            String continueP,
				            String changeAuthority,
				            String changeComments,
				            String planChangeLevel,
				            String engineType,
				            String securityGroup,
				            String ltaSendFlag,
				            String ucfPlanVch9,
				            String ucfPlanVch10,
				            String ucfPlanVch11,
				            String ucfPlanVch12,
				            String ucfPlanVch13,
				            String ucfPlanVch14,
				            String ucfPlanVch15,
				            Number ucfPlanNum3,
				            Number ucfPlanNum4,
				            Number ucfPlanNum5,
				            Date ucfPlanDate1,
				            Date ucfPlanDate2,
				            Date ucfPlanDate3,
				            Date ucfPlanDate4,
				            Date ucfPlanDate5,
				            String ucfPlanFlag3,
				            String ucfPlanFlag4,
				            String ucfPlanFlag5,
				            String ucfPlanVch2551,
				            String ucfPlanVch2552,
				            String ucfPlanVch2553,
				            String ucfPlanVch40001,
				            String ucfPlanVch40002,
				            String uidEntryName,
				            String uidItemFlag,
				            String bomNumber,
				            String displaySequence,
				            String operationOverlapFlag,
				            String inspectionPlanId,
				            String bomId,
				            String declaredLanguage,
				            String explicitBomLink,
				            String orderUom,
				            String updtBatchOperConfirm, 
				            String ppvRequiredFlag, 
				            String ppvType, 
				            Number ppvQuantity, 
				            String commodityJurisdiction, 
				            String commodityClassification,
				            String toWorkPackage){
		
		displaySequence= "Execution Order";
		
		Super.updatePlan(planId, partNo, itemType, itemSubtype, partChg, planType, program, planVersion, planRevision, planAlterations, lowNavLvl, plndWorkLoc, planTitle, 
				         plndOrderQty, plndCustId, model, mfgIndexNo, plgGroup, engPartNo, engPartChg, engGroup, project, plndMaxOrderQty, plndMinOrderQty, initialStores, 
				         finalStores, mfgBomChg, unitType, planNumber, condition, ucfPlanVch1, ucfPlanVch2, ucfPlanFlag1, ucfPlanVch3, ucfPlanVch4, ucfPlanVch5, ucfPlanVch6, 
				         ucfPlanVch7, ucfPlanVch8, ucfPlanFlag2, ucfPlanNum1, ucfPlanNum2, serialFlag, lotFlag, continueP, changeAuthority, changeComments, planChangeLevel, 
				         engineType, securityGroup, ltaSendFlag, ucfPlanVch9, ucfPlanVch10, ucfPlanVch11, ucfPlanVch12, ucfPlanVch13, ucfPlanVch14, ucfPlanVch15, ucfPlanNum3, 
				         ucfPlanNum4, ucfPlanNum5, ucfPlanDate1, ucfPlanDate2, ucfPlanDate3, ucfPlanDate4, ucfPlanDate5, ucfPlanFlag3, ucfPlanFlag4, ucfPlanFlag5, ucfPlanVch2551, 
				         ucfPlanVch2552, ucfPlanVch2553, ucfPlanVch40001, ucfPlanVch40002, uidEntryName, uidItemFlag, bomNumber, displaySequence, operationOverlapFlag, inspectionPlanId,
				         bomId, declaredLanguage, explicitBomLink, orderUom, updtBatchOperConfirm, ppvRequiredFlag, ppvType, ppvQuantity, commodityJurisdiction, commodityClassification, 
				         toWorkPackage);
    }
	
//  RTT changed due to R2 upgrade OOB method changed added planLocationId, program
	public void startPlanRevision(String planId,
			                      Number planVersion,
			                      OutputParameter planRevisionOutput,
			                      Number planAlterations,
			                      String workPackageId,
			                      String notes,
			                      String calledFrom,
			                      String partChange,
			                      String mfgBomChg,
			                      boolean externalPlanCheck,
			                      String planNumber,
			                      String planLocationId,
			                      String program,
			                      String bomNumber,
			                      String partNumber,
			                      String bomId,
			                      String explicitBomLink,//FND-25583
			                      String associateChange,
			                      String workFlow)
	{
		//defect 1870		
		if (notes.contains(",")) {
			
			message.raiseError("MFI_20","Comma's ',' not allowed in Notes. Please remove any commas from notes");

		}
		Number planRevision = null;
		if (planRevisionOutput != null && planRevisionOutput.getValue() != null) {
			planRevision = new Integer(planRevisionOutput.getValue().toString());
		}

		List<String> engineModelList = planDaoImplPW.selectPwustPlanEngineModel(planId, planVersion, planRevision, planAlterations);
		
		// RTT changed due to R2 upgrade OOB method changed - added planLocationId, program
		Super.startPlanRevision(planId, planVersion, planRevisionOutput, planAlterations, workPackageId, 
				                "",//notes, 
				                calledFrom, partChange, mfgBomChg, externalPlanCheck, planNumber, planLocationId, program,
				                bomNumber, partNumber, bomId, explicitBomLink, associateChange, workFlow);

		HashMap hashMap    = (HashMap)planRevisionOutput.getValue();
		Number  newPlanRev = (Number) hashMap.get("PLAN_REVISION");
	
	    dummyUpdatePlanHeaderAfterNewPlanRev(planId, planVersion, newPlanRev, planAlterations, workPackageId);
	    Number planUpdateNumberAfterUpdate = planDaoImpl.selectPlanRevisionPlanUpdateNumber(planId, planVersion, newPlanRev, planAlterations);
				
		String dateString = planDaoImplPW.selectDBTimeStamp();
		String userSTring = ContextUtil.getUsername();
		notes = userSTring + " " + dateString  +":  "+notes;
		int numOfRowsUpdated = planDaoImplPW.updateSfplPlanDesc(planId, planUpdateNumberAfterUpdate, notes);
	
		// Defect 91
		insertCustomTaskGroupInformationForRevision(planId, planVersion, newPlanRev, planUpdateNumberAfterUpdate);
		//		
		for (String engineModel : engineModelList) {
			planDaoImplPW.insertIntoPwustPlanEngineModel(planId, planUpdateNumberAfterUpdate, engineModel);
		}
	}

    private void dummyUpdatePlanHeaderAfterNewPlanRev(String planId, Number planVersion, Number newPlanRev,	Number planAlterations, String workPackageId) {
		
		Map sfplPlanRevMap = planDaoImplPW.selectSfplPlanRevMap(planId, planVersion, newPlanRev, planAlterations);
		
		Number planUpdateNumber = (Number) sfplPlanRevMap.get("PLAN_UPDT_NO");
		String inspPlanId =   (String)sfplPlanRevMap.get("INSP_PLAN_ID");
		String prvReqFlag = (String)sfplPlanRevMap.get("PPV_REQ_FLAG");
		String ppvType =  (String)sfplPlanRevMap.get("PPV_TYPE");
		Number ppvQty =   (Number)sfplPlanRevMap.get("PPV_QTY");
			
		Map sfplPlanDescMap = planDaoImplPW.selectSfplPlanDescMap(planId,planUpdateNumber);
		
		String partNo = (String) sfplPlanDescMap.get("PART_NO");
		String itemType = (String)sfplPlanDescMap.get("ITEM_TYPE");
		String itemSubtype =  (String)sfplPlanDescMap.get("ITEM_SUBTYPE");
		String partChg =(String)sfplPlanDescMap.get("PART_CHG");
		String planType = (String)sfplPlanDescMap.get("PLAN_TYPE");
		String program = (String)sfplPlanDescMap.get("PROGRAM");
		
		String plndLocationId = (String)sfplPlanDescMap.get("PLND_LOCATION_ID");
			
		String workLoc = null;
		if (!StringUtils.isBlank(plndLocationId)) {
			Map workLocMap= planDaoImplPW.selectSffndWorkLocMap(plndLocationId);
			workLoc = (String) workLocMap.get("WORK_LOC");
		}
		
		String planTitle=  (String) sfplPlanDescMap.get("PLAN_TITLE");
		Number planOrderQty = (Number) sfplPlanDescMap.get("PLND_ORDER_QTY");
		String planCustID = (String) sfplPlanDescMap.get("PLND_CUST_ID");
		String model = (String) sfplPlanDescMap.get("MODEL");
		String mfgIndexNo = (String) sfplPlanDescMap.get("MFG_INDEX_NO");
		String plgGroup = (String)	sfplPlanDescMap.get("PLG_GROUP");
		String engPartNo = (String) sfplPlanDescMap.get("ENG_PART_NO");
		String engPartChg = (String) sfplPlanDescMap.get("ENG_PART_CHG");
		String engGroup = (String)	sfplPlanDescMap.get("ENG_GROUP");
		String project = (String) 	sfplPlanDescMap.get("PROJECT");
		String plndMaxOrderQty = (String) sfplPlanDescMap.get("PLND_MAX_ORDER_QTY");
		String plndMinOrderQty= (String) sfplPlanDescMap.get("PLND_MIN_ORDER_QTY");
		String initialStores = (String) sfplPlanDescMap.get("INITIAL_STORES");
		String finalStores = (String) 	sfplPlanDescMap.get("FINAL_STORES");
		String mfgBomRev = (String) sfplPlanDescMap.get("MFG_BOM_CHG");
		String unitType = (String) sfplPlanDescMap.get("UNIT_TYPE");
		String planNo = (String) sfplPlanDescMap.get("PLAN_NO");
		String condition = (String) sfplPlanDescMap.get("CONDITION");
		
		String ucfplanVch1 = (String) sfplPlanDescMap.get("UCF_PLAN_VCH1");
		String ucfplanVch2 = (String) sfplPlanDescMap.get("UCF_PLAN_VCH2");
		String ucfplanFlag1 = (String) sfplPlanDescMap.get("UCF_PLAN_FLAG1");
		String ucfplanVch3 = (String) sfplPlanDescMap.get("UCF_PLAN_VCH3");
		String ucfplanVch4 = (String) sfplPlanDescMap.get("UCF_PLAN_VCH4");
		String ucfplanVch5 = (String) sfplPlanDescMap.get("UCF_PLAN_VCH5");
		String ucfplanVch6 = (String) sfplPlanDescMap.get("UCF_PLAN_VCH6");
		String ucfplanVch7 = (String) sfplPlanDescMap.get("UCF_PLAN_VCH7");
		String ucfplanVch8 = (String) sfplPlanDescMap.get("UCF_PLAN_VCH8");
		String ucfplanFlag2 = (String) sfplPlanDescMap.get("UCF_PLAN_FLAG2");
		Number ucfPlanNumb1 = (Number) sfplPlanDescMap.get("UCF_PLAN_NUM1");
		Number ucfPlanNumb2 = (Number) sfplPlanDescMap.get("UCF_PLAN_NUM2");
		
		String serialFlag = (String) sfplPlanDescMap.get("SERIAL_FLAG");
		String lotFlag = (String) sfplPlanDescMap.get("LOT_FLAG");
        String securityGroup = (String) sfplPlanDescMap.get("SECURITY_GROUP");
        String ltaSendFlag = (String) sfplPlanDescMap.get("LTA_SEND_FLAG");
        
		String ucfplanVch9 = (String) sfplPlanDescMap.get("UCF_PLAN_VCH9");
		String ucfplanVch10 = (String) sfplPlanDescMap.get("UCF_PLAN_VCH10");
		String ucfplanVch11 = (String) sfplPlanDescMap.get("UCF_PLAN_VCH11");
		String ucfplanVch12 = (String) sfplPlanDescMap.get("UCF_PLAN_VCH12");
		String ucfplanVch13 = (String) sfplPlanDescMap.get("UCF_PLAN_VCH13");
		String ucfplanVch14 = (String) sfplPlanDescMap.get("UCF_PLAN_VCH14");
		String ucfplanVch15 = (String) sfplPlanDescMap.get("UCF_PLAN_VCH15");
		Number ucfPlanNumb3 = (Number) sfplPlanDescMap.get("UCF_PLAN_NUM3");
		Number ucfPlanNumb4 = (Number) sfplPlanDescMap.get("UCF_PLAN_NUM4");
		Number ucfPlanNumb5 = (Number) sfplPlanDescMap.get("UCF_PLAN_NUM5");
		Date ucfPlanDate1 = (Date) sfplPlanDescMap.get("UCF_PLAN_DATE1");
		Date ucfPlanDate2 = (Date) sfplPlanDescMap.get("UCF_PLAN_DATE2");
		Date ucfPlanDate3 = (Date) sfplPlanDescMap.get("UCF_PLAN_DATE3");
		Date ucfPlanDate4 = (Date) sfplPlanDescMap.get("UCF_PLAN_DATE4");
		Date ucfPlanDate5 = (Date) sfplPlanDescMap.get("UCF_PLAN_DATE5");
		String ucfplanFlag3 = (String) sfplPlanDescMap.get("UCF_PLAN_FLAG3");
		String ucfplanFlag4 = (String) sfplPlanDescMap.get("UCF_PLAN_FLAG4");
		String ucfplanFlag5 = (String) sfplPlanDescMap.get("UCF_PLAN_FLAG5");
		String ucfplanVch2551 = (String) sfplPlanDescMap.get("UCF_PLAN_VCH255_1");
		String ucfplanVch2552 = (String) sfplPlanDescMap.get("UCF_PLAN_VCH255_2");
		String ucfplanVch2553 = (String) sfplPlanDescMap.get("UCF_PLAN_VCH255_3");
		String ucfplanVch40001 = (String) sfplPlanDescMap.get("UCF_PLAN_VCH4000_1");
		String ucfplanVch40002 = (String) sfplPlanDescMap.get("UCF_PLAN_VCH4000_2");			
		
		String uidEntryName = (String) sfplPlanDescMap.get("UID_ENTRY_NAME");
		String uidItemFlag = (String) sfplPlanDescMap.get("UID_ITEM_FLAG");
		String bomNo = (String) sfplPlanDescMap.get("BOM_NO");
		String displaySequence = (String) sfplPlanDescMap.get("DISPLAY_SEQUENCE");
		String operationOverLapFlag = (String) sfplPlanDescMap.get("OPERATION_OVERLAP_FLAG");
		String bomId = (String) sfplPlanDescMap.get("BOM_ID");
		String languageCode = (String) sfplPlanDescMap.get("DECLARED_LANGUAGE_CODE");
		String orderUom = (String) sfplPlanDescMap.get("ORDER_UOM");
		String commodityJursdiction = (String) sfplPlanDescMap.get("COMMODITY_JURISDICTION");
		String commodityClassfication = (String) sfplPlanDescMap.get("COMMODITY_CLASSIFICATION");			
		
		Super.updatePlan(planId,
				        partNo,//:PART_NO,
				        itemType,//:ITEM_TYPE,
				        itemSubtype, // :ITEM_SUBTYPE,
				        partChg, //:PART_CHG,
				        planType, //:PLAN_TYPE,
				        program, //:PROGRAM,
				        planVersion, //:PLAN_VERSION,
				        newPlanRev, //:PLAN_REVISION,
				        planAlterations, // :PLAN_ALTERATIONS,
				        "STEP", //":LOW_NAV_LVL,
				        workLoc , //:PLND_WORK_LOC,
				        planTitle,//:PLAN_TITLE,
				        planOrderQty,//:PLND_ORDER_QTY,
				        planCustID,//:PLND_CUST_ID,
				        model,//:MODEL,
				        mfgIndexNo,//:MFG_INDEX_NO,
				        plgGroup,//:PLG_GROUP,
				        engPartNo,//:ENG_PART_NO,
				        engPartChg,//:ENG_PART_CHG,
				        engGroup,//:ENG_GROUP,
				        project,//:PROJECT,
				        plndMaxOrderQty,//:PLND_MAX_ORDER_QTY,
				        plndMinOrderQty,//:PLND_MIN_ORDER_QTY,
				        initialStores,//:INITIAL_STORES,
				        finalStores,//:FINAL_STORES,
				        mfgBomRev,//:MFG_BOM_REV,
				        unitType,//:UNIT_TYPE,
				        planNo,//:PLAN_NO,
				        condition,//:CONDITION,
				        ucfplanVch1,//:UCF_PLAN_VCH1,
				        ucfplanVch2,//:UCF_PLAN_VCH2,
				        ucfplanFlag1, //:UCF_PLAN_FLAG1,
						ucfplanVch3,//:UCF_PLAN_VCH3,
						ucfplanVch4,//:UCF_PLAN_VCH4,
						ucfplanVch5,//:UCF_PLAN_VCH5,
						ucfplanVch6,//:UCF_PLAN_VCH6,
						ucfplanVch7,//:UCF_PLAN_VCH7,
						ucfplanVch8,//:UCF_PLAN_VCH8,
						ucfplanFlag2, //:UCF_PLAN_FLAG2,
						ucfPlanNumb1,//:UCF_PLAN_NUM1,
						ucfPlanNumb2, //:UCF_PLAN_NUM2,
						serialFlag, //:SERIAL_FLAG,
				        lotFlag, //:LOT_FLAG,
				        null,// :CONTINUE,
				        null,//NULL,
				        null,//NULL,
				        null,//:PLAN_CHANGE_LEVEL,
				        null,//:ENGINE_TYPE,
				        securityGroup, //:SECURITY_GROUP,
				        ltaSendFlag,//:LTA_SEND_FLAG,
				        ucfplanVch9,//:UCF_PLAN_VCH9,
				        ucfplanVch10,//:UCF_PLAN_VCH10,
				        ucfplanVch11,//:UCF_PLAN_VCH11,
				        ucfplanVch12,//:UCF_PLAN_VCH12,
				        ucfplanVch13,//:UCF_PLAN_VCH13,
				        ucfplanVch14,//:UCF_PLAN_VCH14,
				        ucfplanVch15,//:UCF_PLAN_VCH15,
				        ucfPlanNumb3, //:UCF_PLAN_NUM3,
				        ucfPlanNumb4,//:UCF_PLAN_NUM4,
				        ucfPlanNumb5, //:UCF_PLAN_NUM5,
				        ucfPlanDate1, //:UCF_PLAN_DATE1,
				        ucfPlanDate2,//:UCF_PLAN_DATE2,
				        ucfPlanDate3,//:UCF_PLAN_DATE3,
				        ucfPlanDate4, //:UCF_PLAN_DATE4,
				        ucfPlanDate5, //:UCF_PLAN_DATE5,
				        ucfplanFlag3, //:UCF_PLAN_FLAG3,
				        ucfplanFlag4, //:UCF_PLAN_FLAG4,
				        ucfplanFlag5, //:UCF_PLAN_FLAG5,
				        ucfplanVch2551, //:UCF_PLAN_VCH255_1,
				        ucfplanVch2552, //:UCF_PLAN_VCH255_2,
				        ucfplanVch2553, //:UCF_PLAN_VCH255_3,
				        ucfplanVch40001, //:CHANGE_LEVEL_NOTE,
				        ucfplanVch40002, //:UCF_PLAN_VCH4000_2,
				        uidEntryName,//:UID_ENTRY_NAME,
				        uidItemFlag,//:UID_ITEM_FLAG,
				        bomNo,//:BOM_NO,
				        displaySequence,//:DISPLAY_SEQUENCE,
				        operationOverLapFlag,//:OPERATION_OVERLAP_FLAG,
				        inspPlanId,//:INSP_PLAN_ID,
				        bomId,//:BOM_ID,
				        languageCode,//:LANGUAGE_CODE,
				        null,
				        orderUom,//:ORDER_UOM,
				        "N",
				        prvReqFlag,//:PPV_REQ_FLAG,
				        ppvType, //:PPV_TYPE,
				        ppvQty,//:PPV_QTY,
				        commodityJursdiction,//:COMMODITY_JURISDICTION,
				        commodityClassfication,//:COMMODITY_CLASSIFICATION,
				        workPackageId);//:TO_PWP_ID
		
	}
	
	public void insertCustomTaskGroupInformationForRevision(String planId, 
			                                                Number planVersion, 
			                                                Number newPlanRev, 
			                                                Number newPlanUpdtNo)
	{
		//get plan type - Added for SMRO_APP_301-REPAIR
		String planType =  planDaoPW.getPlanType(planId);
	
		//get a list of subjects and revisions in previous planRevision or copied from planRevision
		String userName = ContextUtil.getUsername();
		Number previousPlanRev = newPlanRev.intValue() - 1;
		
		List taskInformation = taskGroupsDaoPW.selectTaskGroupsForPlan(planId, planVersion, previousPlanRev, 0);
		ListIterator task = taskInformation.listIterator();

		while (task.hasNext())
		{
			Map collection = (Map) task.next();

			Number subjectNumber = (Number) collection.get("SUBJECT_NO");

			//Insert Custom Task group information on Plan Revision		
			////defect 1485 planDaoImplPW.insertSubjectExtension(subjectNumber, userName, planId, newPlanUpdtNo); //Defect 181 
			planDaoImplPW.insertTaskGroupExtension(planId, newPlanUpdtNo); //defect 1485
			planDaoImplPW.insertCustomer(subjectNumber, userName, planId, newPlanUpdtNo); //Defect 181 
			planDaoImplPW.insertCustomerException(subjectNumber, userName, planId, newPlanUpdtNo);//Defect 181 
			
			if ("OVERHAUL".equals(planType))
			{
			planDaoImplPW.insertSuperiorNetwork(subjectNumber, userName, planId, newPlanUpdtNo);//Defect 181 
			planDaoImplPW.insertSubNetwork(subjectNumber, userName, planId, newPlanUpdtNo);//Defect 181 
			planDaoImplPW.insertWorkscope(subjectNumber, userName, planId, newPlanUpdtNo);//Defect 181 
			}
		}
	}
	
	//SMRO_PLG_205 SCR-004
	public void insertCustomTaskGroupInformationForCopy(String sourcePlanId, 
			Number sourcePlanVersion, 
			Number sourcePlanRev, 
			Number sourcePlanUpdtNo, 
			String newPlanId, 
			Number newPlanUpdtNo, 
			String changeEngineTypeFlag, 
			String engineType)  //SMRO_PLG_205 SCR-004
	{

		//get plan type - Added for SMRO_APP_301-REPAIR
		String planType =  planDaoPW.getPlanType(sourcePlanId);

		//get a list of subjects and revisions in sourcePlan
		String userName = ContextUtil.getUsername();
		String workLoc =  planDaoImplPW.selectPlanWorkLocation(newPlanId, newPlanUpdtNo);

		List taskInformation = taskGroupsDaoPW.selectTaskGroupsForPlan(sourcePlanId, sourcePlanVersion, sourcePlanRev, 0);
		ListIterator task = taskInformation.listIterator();

		while (task.hasNext())
		{
			Map taskList = (Map) task.next();

			Number subjectNumber = (Number) taskList.get("SUBJECT_NO");
			Number subjectRevision = (Number) taskList.get("SUBJECT_REV");

			//Insert Custom Task group information on Plan Copy
			planDaoImplPW.insertSubjectExtension(subjectNumber, subjectRevision, userName, sourcePlanId, sourcePlanUpdtNo, newPlanId, newPlanUpdtNo);
			planDaoImplPW.insertCustomer(subjectNumber, subjectRevision, userName, sourcePlanId, sourcePlanUpdtNo, newPlanId, newPlanUpdtNo);
			planDaoImplPW.insertCustomerException(subjectNumber, subjectRevision, userName, sourcePlanId, sourcePlanUpdtNo, newPlanId, newPlanUpdtNo);

			if ("OVERHAUL".equals(planType))  // Defect 1020
			{
				if ("Y".contentEquals(changeEngineTypeFlag)) // SMRO_PLG_205 SCR-004
				{
					Map taskNetworkMap = planDaoImplPW.selectNetworkForTask(sourcePlanId, sourcePlanUpdtNo, subjectNumber, subjectRevision);

					String superiorNetwork = (String) taskNetworkMap.get("SUPERIOR_NETWORK_ACT");
					String subNetwork      = (String) taskNetworkMap.get("SUB_NETWORK_ACT");

					// does the superior and sub network exist for the new plan engine type?
					if (taskGroupsDaoPW.doesNetworkExist(engineType, superiorNetwork, subNetwork))
					{
						planDaoImplPW.insertSuperiorNetwork(subjectNumber, subjectRevision, userName, sourcePlanId, sourcePlanUpdtNo, newPlanId, newPlanUpdtNo, engineType);  
						planDaoImplPW.insertSubNetwork(subjectNumber, subjectRevision, userName, sourcePlanId, sourcePlanUpdtNo, newPlanId, newPlanUpdtNo, engineType); 
					}

					//is there a workscope for the current task
					List taskWorkscope = planDaoImplPW.selectWorkscopeForTask(sourcePlanId, sourcePlanUpdtNo, subjectNumber, subjectRevision);
					ListIterator workscope = taskWorkscope.listIterator();

					while (workscope.hasNext())
					{
						Map workscopeList = (Map) workscope.next();
						String currentWorkscope = (String) workscopeList.get("WORKSCOPE");

						if(taskGroupsDaoPW.doesWorkscopeExist(engineType, currentWorkscope, workLoc))
						{
							planDaoImplPW.insertWorkscope(subjectNumber, subjectRevision, userName, sourcePlanId, sourcePlanUpdtNo, newPlanId, newPlanUpdtNo, currentWorkscope);// SMRO_PLG_205 SCR-004
						}	
					}
				}
				else
				{
					//copy the workscope, superior, and sub networks
					planDaoImplPW.insertWorkscope(subjectNumber, subjectRevision, userName, sourcePlanId, sourcePlanUpdtNo, newPlanId, newPlanUpdtNo);// SMRO_PLG_205 SCR-004
					planDaoImplPW.insertSuperiorNetwork(subjectNumber, subjectRevision, userName, sourcePlanId, sourcePlanUpdtNo, newPlanId, newPlanUpdtNo, engineType);   // SMRO_PLG_205 SCR-004
					planDaoImplPW.insertSubNetwork(subjectNumber, subjectRevision, userName, sourcePlanId, sourcePlanUpdtNo, newPlanId, newPlanUpdtNo, engineType);  // SMRO_PLG_205 SCR-004
				}
			} // Added for SMRO_APP_301-REPAIR
		}
	}
	
    /** 
     * SMRO_EXPT_201 - Oct 13, 2017
     * 
     * @author c079222 - David Miron
     */
	public void returnToPriorPlanningTask(String taskId, 
			                              String planId,
			                              Number planVersion, 
			                              Number planRevision, 
			                              Number planAlterations,
			                              String oldQueueType, 
			                              String newQueueType, 
			                              String notes) 
	{
		List l = (List) planDao.selectPlanInformation(planId, planVersion, planRevision, planAlterations);
		Map m = (Map) l.get(0);
		Number planUpdtNo = (Number) m.get("PLAN_UPDT_NO");

		commPW.cancelEntry(planId, planVersion, planRevision, planAlterations, notes, "EXPT_ACCEPTANCE", planUpdtNo);

		this.Super.returnToPriorPlanningTask(taskId, 
				                             planId, 
				                             planVersion, 
				                             planRevision, 
				                             planAlterations, 
				                             oldQueueType, 
				                             newQueueType, 
				                             notes);
	}

	// Defect 183
	private void setDefaultSecurity(String planId, 
			                        Number planUpdateNumber, 
			                        String partNumber, 
			                        String plannedWorkLocation)
	{
		String planNumber = utasPlanDao.selectPlanNumber(planId, planUpdateNumber);

		// Select Security Groups for Assigned Work Location
		List workLocationSecGroupList = utasPlanDao.selectSecurityGroupForAssignedWorkLocation(plannedWorkLocation);
		Iterator workLocationSecGrpIt = workLocationSecGroupList.listIterator();

		while(workLocationSecGrpIt.hasNext())
		{
			Map workLocationSecGrpMap = (Map) workLocationSecGrpIt.next();
			String workLocationSecGrpStr = (String)workLocationSecGrpMap.get("SECURITY_GROUP");

			assignSecurityGrpToPlan(planId, planUpdateNumber, workLocationSecGrpStr);
		}
	}	

	// Defect 183
	private void assignSecurityGrpToPlan(String planId, 
			                             Number planUpdtNo,
			                             String securityGroup) 
	{
		try
		{
			fndSecurityGroupDao.insertWorkPlanSecurityGroup( planId, planUpdtNo, securityGroup, ContextUtil.getUsername());
		}
		catch(DataIntegrityViolationException exception)
		{
			// Record already exists - do nothing.
		}
		
		fndSecurityGroupDao.updateSecurityGroupInPlan(planId, planUpdtNo, securityGroup, SoluminaConstants.INSERT);
	}
	
	//Defect 199
	public void checkComplete(String planId,
			                  Number planVersion,
			                  Number planRevision,
			                  Number planAlterations)
	{
		// Gets the Plan Description.
		Map planDescriptionAndPlanRevisionInformation = planObjectDao.selectPlan(planId,
				                                                                 planVersion,
				                                                                 planRevision,
				                                                                 planAlterations);

		// Gets the Plan Update Number and Plan Type.
		Number planUpdateNumber = (Number) planDescriptionAndPlanRevisionInformation.get("PLAN_UPDT_NO");
		String documentType = (String) planDescriptionAndPlanRevisionInformation.get("DOC_TYPE");
		String planType = (String) planDescriptionAndPlanRevisionInformation.get("PLAN_TYPE");
		String workFlow = (String) planDescriptionAndPlanRevisionInformation.get("WORK_FLOW");

		// Checks whether Subject exists in the Work Plan or not.
		boolean planSubjectExists = sfplPlanDao.selectPlanSubjectExists(planId,
				planUpdateNumber,
				NO);

		// Raises an error when Subject does not exist.
		if (!planSubjectExists)
		{
			message.raiseError("SFOR_10");
		}

		// Raises an error when there is not at least one default task.
		if (planSubjectExists)
		{
			boolean isDefault = sfplPlanDao.selectPlanSubjectIsDefault(planId,
					                                                   planUpdateNumber,
					                                                   NO);

			if (!isDefault)
			{
				// Plan should have atleast one default task.
				message.raiseError("MFI_20", "Plan does not have at least one Task Group set to Auto Include. Set at least one Task Group to Auto Include"); //Defect 199
			}

		}
/* defect 1870
		// Checks whether Subjects missing Operation assignment or not.
		planSubjectExists = sfplPlanDao.selectPlanSubjectExistsForMissingOperation(planId,
				                                                                   planUpdateNumber,
				                                                                   NO);

		// Raises an error when Subject missing Operation assignment.
		
		if (planSubjectExists)
		{
			message.raiseError("SFOR_11"); 
		}
end defect 1870 */ 
		
		// Gets the Plan Type Description.
		Map planTypeInformation = planDao.selectInstructionTypeFromPlanType(documentType, 
				                                                            planType);

		// Gets the Instructions Type and Plan Purpose.
		String instructionsType = (String) planTypeInformation.get("INSTRUCTIONS_TYPE");

		if (stringEquals(instructionsType,MRO_PART)
				|| stringEquals(instructionsType,MRO_UPGRADE))
		{
			// Checks whether Subject missing Part Number assignment or not.
			planSubjectExists = sfplPlanDao.selectPlanSubjectExistsForMissingPart(planId,
					                                                              planUpdateNumber,
					                                                              NO);

			// Raises an error when Subject missing Part Number assignment.
			if (planSubjectExists)
			{
				message.raiseError("SFOR_12");
			}

			// FND-12710 01/07/2010
			// Checks whether there is at least one task group included for each
			// task group part or not.
			boolean defaultPlanSubjectExists = sfplPlanDao.selectDefaultPlanSubjectExistsForPart(planId,
					                                                                             planUpdateNumber);

			// For each task group part, there should be at least one task
			// included
			if (defaultPlanSubjectExists)
			{
				message.raiseError("MFI_7C905B0B8D9A4BC4E04400144FA7B7D2");
			}
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public void planCreateRun(String partNumber,
            String itemType,
            String itemSubtype,
            String partChange,
            String program,
            Number planVersion,
            Number planRevision,
            Number planAlterations,
            String toPartNumber,
            String toItemType,
            String toItemSubtype,
            String toPartChange,
            String toProgram,
            String toWorkLocation,
            Number toPlanVersion,
            String toPlanTitle,
            String toPlanType,
            String toChangeDocumentationId,
            String lowestNavigationLevel,
            String copyComponentParts,
            String notes,
            String planNumber)
    {
        String planId = planDao.selectPlanIdByPlanNumber(partNumber, 
				   planNumber, 
				   PROCESS_PLAN_TEXT, 
				   toPlanType);
		List<String> engineModelList = planDaoImplPW.selectPwustPlanEngineModel(planId, planVersion, planRevision, planAlterations);

		Super.planCreateRun(partNumber, itemType, itemSubtype, partChange, program, planVersion, planRevision, planAlterations, toPartNumber, toItemType, toItemSubtype, toPartChange, toProgram, toWorkLocation, toPlanVersion, toPlanTitle, toPlanType, toChangeDocumentationId, lowestNavigationLevel, copyComponentParts, notes, planNumber);

		
		
		// Begin Defect 323.
		Map toMap = planDaoImplPW.selectSourcePlanIdMap(toPartNumber, toPlanVersion, new Integer(1), new Integer(0));
		// End Defect 323.
		String toPlanId =(String) toMap.get("PLAN_ID");
        Number toPlanUpdtNo = (Number) planDao.selectPlanUpdateNumberFromPlan(toPlanId, toPlanVersion, new Integer(1), new Integer(0));
		for (String engineModel : engineModelList) {
			// Begin Defect 323.
			Map cntMap = planDaoImplPW.selectPwustPlanEngineModelCount(toPlanId, toPlanUpdtNo, engineModel);
			String cnt = cntMap.get("CNT").toString();
			if ("0".equals(cnt)) {
				// End Defect 323.
				planDaoImplPW.insertIntoPwustPlanEngineModel(toPlanId, toPlanUpdtNo, engineModel);
			}
		}
		//Defect 1756
		//get destinations work locaation's ID
		//need to update this
		Map toWorkLocationData = (Map) planDaoPW.getWorkLocationData(toWorkLocation);
		if(!toWorkLocationData.isEmpty()){//Defect 1785
    		String toWorkLocationId = (String) toWorkLocationData.get("LOCATION_ID");
			//String planWorkLocation = planDaoPW.selectPlanWorkLocation(planId, planVersion, planRevision, planAlterations);
			Map sourcePlanWorkLocationData = planDaoPW.selectPlanWorkLocationData(planId, planVersion, planRevision, planAlterations);
			if(!sourcePlanWorkLocationData.isEmpty()){ //if user does not enter a plan to copy from
				String planWorkLocation = (String)sourcePlanWorkLocationData.get("WORK_LOC");
				if(toWorkLocationId != null && !planWorkLocation.equals(toWorkLocation)){
					updateCopiedPlanOperationsWorkLocation(toPlanId, toWorkLocationId);
					//If workcenter exists, update every operation
				}
			}
		}
	/*	
		//defect 1902 add task authorities begin		
		List operList = soluminaMigrationDaoPW.querySubjectAuthorities(planId, planRevision);
		ListIterator operIter = operList.listIterator();
		while(operIter.hasNext()) {
			Map operInfo = (Map) operIter.next();
			Number exe_order = (Number) operInfo.get("EXE_ORDER");			
			Number subjectNo = (Number) operInfo.get("SUBJECT_NO");
			Number subjectRev = MigrationVariablesPW.NEW_PLAN_REV;
			String authorityType = (String) operInfo.get("AUTHORITY_TYPE");
			String authorityDetail = (String) operInfo.get("AUTHORITY_DETAIL");
			String manualNo = (String) operInfo.get("MANUAL_NO");
			String manualRev = (String) operInfo.get("MANUAL_REV");
			String manualProvider = (String) operInfo.get("MANUAL_PROVIDER");
			String manualType = (String) operInfo.get("MANUAL_TYPE");
			String manualSection = (String) operInfo.get("MANUAL_SECTION");
			String sbPartNo = (String) operInfo.get("SB_PART_NO");
			String authorityRev = (String) operInfo.get("AUTHORITY_REV");
			Date authorityRevDate = (Date) operInfo.get("AUTHORITY_REV_DATE");
			
			
			String subjectDesc =(String) operInfo.get("SUBJECT_DESCRIPTION");
			
			try{
			authorityImpl.insertPlanTaskAuth(toPlanId, toPlanVersion, subjectNo.toString(), subjectRev.toString(), subjectDesc, authorityType,
											authorityDetail, manualNo, manualRev, manualProvider, 
											manualType, manualSection, sbPartNo, authorityRev, 
											authorityRevDate);
			}
			catch(Exception e){
				StringBuffer errMessage = new StringBuffer();
				errMessage.append(e.getMessage());
				errMessage.append("\nTask Group Number: " + subjectNo);
				errMessage.append("\n  Task Rev Number: " + subjectRev);
				errMessage.append("\n           Manual: " + manualNo);
				errMessage.append("\n   Authority Type: " + authorityType);
				errMessage.append("\n  Authority Notes: " + authorityDetail);
				errMessage.append("\nSee TOOL_NO for Manual Name/Number");
				soluminaMigrationDaoPW.insertErrorLogG8("TASK_AUTH_INSERT", MigrationVariablesPW.WARN, toPlanId,
						MigrationVariablesPW.PLAN_UPDT_NO, "", MigrationVariablesPW.NA_ERR,
						manualNo, 0, errMessage.toString(), MigrationVariablesPW.SFMFG);
			}
		}
*/
		//defect 1902 add task authorities end
		
    }

    //Defect 805
    public List copyPlanOrderOperation(String targetOperationNumber,
            String incrementBy,
            String fromPlanId,
            Number fromPlanVersion,
            Number fromPlanRevision,
            Number fromPlanAlterations,
            String operationNumberList,
			Number sequenceNumber){
    	
		ArrayList returnArrayList = (ArrayList) Super.copyPlanOrderOperation(targetOperationNumber, 
				                                                             incrementBy, 
				                                                             fromPlanId,
				                                                             fromPlanVersion, 
				                                                             fromPlanRevision, 
				                                                             fromPlanAlterations, 
				                                                             operationNumberList, 
				                                                             sequenceNumber);
		
		for (int i = 0; i < returnArrayList.size(); i++) {
			Map retMap = (Map) returnArrayList.get(i);

			retMap.put("UCF_ORDER_OPER_VCH2", null);
			retMap.put("UCF_ORDER_OPER_VCH3", null);
		}
		return returnArrayList;
    }
    
    private void updateCopiedPlanOperationsWorkLocation(String planId, String workLocationId){
		for(Map<String, Object> row : planDaoPW.getCopiedPlanOperations(planId)){
			BigDecimal operKey = (BigDecimal)row.get("OPER_KEY");
			String workCenter = (String)row.get("WORK_CNTR");
			//check if work center exists in new work location
			Map<String, String> record = planDaoPW.getWorkCenterKey(workLocationId, workCenter);
			if(record != null){
				//update operation
				planDaoPW.updateOperationWorkLocation(planId, record.get("LOCATION_ID"), record.get("DEPARTMENT_ID"), record.get("CENTER_ID"), operKey);
			}
		}
    }
}
