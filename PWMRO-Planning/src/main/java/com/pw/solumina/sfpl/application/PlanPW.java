/*
* This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    PlanPW.java
* 
*  Created: 2017-08-03
* 
*  Author:  c079222
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-08-03 	c079222		    Initial Release XXXXXXX
* 2017-09-01    c293011	        SMRO_PLG_205 - Added Checks for Task Groups
* 2018-05-01    x002174			SMRO_APP_201 - Defect 270. Display error message if no approver selected
* 2018-03-29    xcs5279         Defect 528 - Create an error when all operations do not have work dept/centers
* 2018-04-20    x002174         Defect 680 - Create an error when standard operations do not have work dept/centers
* 2018-09-26    c293011         SMRO_APP_301 - Added checks for REPAIR vs. OVERHAUL
* 2018-11-02	R.Thorpe        SMRO_PLG_205  - Defect 1020 Plan copy does not copy over workscopes, superior and sub networks.
* 2018-11-15    D.Miron         Defect 220 - Uncommented method sendPaarExternalApproval.
* 2018-12-17	B. Preston		SMRO_PLG_301:  Added getEngineModelTabList().
* 2018-01-07	B. Preston		SMRO_PLG_305:  Changes for warningMsgCheck() based on functional spec.
*/

package com.pw.solumina.sfpl.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.application.IWrapUtils;
import com.ibaset.solumina.sfpl.application.IPlan;
import com.ibaset.solumina.sfpl.dao.IPlanDao;

import com.utas.solumina.sfpl.application.IUtasPlan;
import com.utas.solumina.paar.application.IUTASPaarRequest;
import com.utas.solumina.paar.dao.PaarRequestDao;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang.StringUtils;

import com.pw.common.dao.CommonDaoPW;
import com.pw.common.utils.CommonUtilsPW;
import com.pw.solumina.sfor.dao.TaskGroupsDaoPW;
import com.pw.solumina.sfpl.application.impl.PlanDaoImplPW;
import com.pw.solumina.sfpl.dao.PlanDaoPW;

/**
 * @author c079222
 *
 */
public class PlanPW {

	@Reference 
	private IPlan plan = null;
	
	@Reference 
	private PlanDaoPW planDaoPW = null;
	
	@Reference 
	private IPlanDao planDao = null;
	
	@Reference 
	private CommonUtilsPW commonUtils = null;
	
	@Reference 
	private CommonDaoPW commonDaoPW = null;
	
	@Reference  
	private IMessage message = null;
	
	@Reference 
	private TaskGroupsDaoPW taskGroupsDaoPW = null;

	@Reference
	IUtasPlan utasPlan;
	@Reference
	IUTASPaarRequest utasPaarRequest;
	@Reference
	PaarRequestDao paarRequestDao;

	@Reference
	private IEvent event;

	@Reference
	IWrapUtils wrapUtils;
	
	@Reference
	PlanDaoImplPW planDaoImplPW = null;
	
	/** 
	 * SAEP_APP_01 - Aug 11, 2017
	 * 
	 * @author c079222 - David Miron
	 *
	 * @param taskId
	 * @param toTaskType
	 * @param toQueueType
	 * @param toStatus
	 * @param notes
	 * @param blockEdit
	 * @param orderId
	 * @param calledFrom
	 * @param documentType
	 * @param slideModifyFlag
	 * @param planId
	 * @param planVersion
	 * @param planRevision
	 * @param planAlterations
	 * @param planRevisionStatus
	 * @param notificationMatrix
	 */
	@SuppressWarnings("static-access")
	public void warningMsgCheck(String taskId,
			                    String toTaskType,
			                    String toQueueType,
			                    String toStatus,
			                    String notes,
			                    Number blockEdit,
			                    String orderId,
			                    String calledFrom,
			                    String documentType, 
			                    Number slideModifyFlag,
			                    String planId,
			                    Number planVersion,
			                    Number planRevision,
			                    Number planAlterations,
			                    String planRevisionStatus,
			                    String notificationMatrix)
	{
		// Begin SMRO_PLG_305
		String chgNotes = commonUtils.writeChgHist(notes, null);
		
		if (chgNotes.length() > 4000) 
		{ 
			chgNotes = chgNotes.substring(0, 4000);
			message.showMessage("MFI_20","Change Notes truncated to 4000 characters");
		} 
		// End SMRO_PLG_305
		
		// get plantype here 
		String planType =  planDaoPW.getPlanType(planId);
		if ("OVERHAUL".equals(planType))  //Defect 1020
		{
			/*//Defect 528
			String operations = planDaoPW.getOperationsMissingWorkCenterOrWorkDept(planId, planVersion, planRevision, planAlterations);
			if (!StringUtils.isBlank(operations))
			{ 
				message.raiseError("MFI_20",
						"Some operations in the plan are missing a work department or center. Please update the headers of the following operations:"
								+ operations);
			} // End Defect 528
*/
			List<String> engineModelList = planDaoImplPW.selectPwustPlanEngineModel(planId, planVersion, planRevision, planAlterations);

			if (engineModelList == null || engineModelList.isEmpty())
			{ 
				message.raiseError("MFI_20","This Plan cannot be put into the Approval Cycle.  The following Data is Missing:\nThe plan does not have an engine model");
			}

			//Begin SMRO_PLG_205
			String taskGroupErrorMsg = "This Plan cannot be put into the Approval Cycle.  The following Data is Missing:";
			List taskInformation = taskGroupsDaoPW.selectTaskGroupsForPlan(planId, planVersion, planRevision, planAlterations);

			ListIterator task = taskInformation.listIterator();

			while (task.hasNext())
			{
				Map collection = (Map) task.next();

				Number subjectNumber = (Number) collection.get("SUBJECT_NO");
				Number subjectRev    = (Number) collection.get("SUBJECT_REV");
				Number planUpdtNo    = (Number) collection.get("PLAN_UPDT_NO");
				String engineType    = (String) collection.get("PROGRAM");

				boolean superiorNetworkExists = taskGroupsDaoPW.doesSuperiorNetworkExist(planId, planUpdtNo, subjectNumber, subjectRev, engineType);
				if (superiorNetworkExists)
				{
					String superiorNetwork = taskGroupsDaoPW.selectSuperiorNetwork(planId, planUpdtNo, subjectNumber, subjectRev, engineType);
					boolean subNetworkExists = taskGroupsDaoPW.doesSubNetworkExist(planId, planUpdtNo, subjectNumber, subjectRev, engineType, superiorNetwork);
					if (!subNetworkExists)
					{
						taskGroupErrorMsg = taskGroupErrorMsg + "\n"+"Task Group "+subjectNumber+" is missing a Sub Network";
					}   
				}
				else
				{
					taskGroupErrorMsg = taskGroupErrorMsg + "\n"+"Task Group "+subjectNumber+" is missing Superior and Sub Networks";
				}
			}

			if (taskGroupErrorMsg.length() > 80) 
			{ 
				message.raiseError("MFI_20",taskGroupErrorMsg);
			}
			//End SMRO_PLG_205
		}// end if plantype OH
		//Applies to both Overhaul and Repair
		
		//Defect 528
//		String operations = planDaoPW.getOperationsMissingWorkCenterOrWorkDept(planId, planVersion, planRevision, planAlterations);
//		if (!StringUtils.isBlank(operations))
//		{ 
//			message.raiseError("MFI_20",
//					"Some operations in the plan are missing a work department or center. Please update the headers of the following operations:"
//							+ operations);
//		} // End Defect 528
//		
//		String chgNotes = commonUtils.writeChgHist(notes, null);
//
//		if (chgNotes.length() > 4000) 
//		{ 
//			chgNotes = chgNotes.substring(0, 4000);
//			message.showMessage("MFI_20","Change Notes truncated to 4000 characters");
//		}

		if (notificationMatrix != null) 
		{
			if (notificationMatrix.length() > 255) 
			{ 
				message.raiseError("MFI_20","Too many approvers selected. Max limit is 30 approvers.");
			}
		}
		//start Defect 270
		else if (planRevision.equals(1))
		{
			message.raiseError("MFI_20", "Please enter an approver in the Approver Notification Selection box.");
		}
		//End Defect 270

		Number planUpdtNo = planDao.selectPlanUpdateNumberFromPlan(planId, 
				planVersion, 
				planRevision, 
				planAlterations);

		String workLoc = planDaoPW.getPlantForPlan(planId, planUpdtNo); 
		if(workLoc == null)
		{
			message.raiseError("MFI_20","Plan is missing required work location. ");
		}

		if ((planRevision.intValue() == 1) && (notificationMatrix != null)) // SMRO_PLG_301 
		{
			String delims = "[;]";
			String hasExptPriv = null;

			String[] tokens = notificationMatrix.split(delims);
			String exptPrivilege = workLoc + "_PW_EXPT_APPROVER";

			for (int i = 0; i < tokens.length; i++)
			{
				hasExptPriv = commonDaoPW.selectUserPriv(tokens[i], exptPrivilege);
				if ("Y".equals(hasExptPriv)) break;
			}

			if ("N".equals(hasExptPriv))
			{
				message.raiseError("MFI_20","At least one Export Approver must be selected for Revision 1");
			}
		}

		planDaoPW.updateChangeNotes(planId, planUpdtNo, chgNotes, notificationMatrix);

		if (notes.length() > 255) 
		{ 
			notes = notes.substring(0,255);
		}

		plan.warningMsgCheck(taskId, 
				toTaskType, 
				toQueueType, 
				toStatus, 
				notes, 
				blockEdit, 
				orderId, 
				calledFrom, 
				documentType, 
				slideModifyFlag, 
				planId, 
				planVersion, 
				planRevision, 
				planAlterations, 
				planRevisionStatus);
	}
	
	/** 
	 * SAEP_APP_01 - Aug 14, 2017
	 * 
	 * @author c079222 - David Miron
	 *
	 * @param planId
	 * @param planUpdtNo
	 * @param instanceMatrix
	 * @param whereClause
	 * @return
	 */
	public List individualApprovers(String planId, Number planUpdtNo, String instanceMatrix, String whereClause)  
	{
		List list = null; 
		String workLoc;
		StringBuffer sqlORClause = new StringBuffer();
		String delims = "[;]";
		String[] tokens = instanceMatrix.split(delims);

		workLoc = planDaoPW.getPlantForPlan(planId, planUpdtNo);   // Should always find a workLoc.

		if(workLoc == null){
			workLoc = "CONN";  // Assign a default location if none found
		}

		for (int i = 0; i < tokens.length; i++){
			sqlORClause.append("'"+workLoc + "_PW_" +tokens[i]+"_APPROVER'");
			if(i != tokens.length - 1){
				sqlORClause.append(", ");
			}
		}

		list = planDaoPW.getApprovalUsers(workLoc, sqlORClause.toString(), whereClause);  // Add whereClause to dao method call

		// If list of approval users is empty, create and return Map with null in fields in order to display empty ListBuilder
		if(list.size()==0 || list==null)
		{
			Map emptyMap = new ListOrderedMap();

			emptyMap.put("PRIV", null);
			emptyMap.put("FULL_NAME", null);
			emptyMap.put("USERID", null);
			emptyMap.put("NOTIFICATION_MATRIX", null);
			emptyMap.put("SECURITY_GROUP", null);
			list.add(emptyMap);
		}

		return list;
	}

	public void requestPaarReview(String planId, Number planVersion, Number planRevision, Number planAlterations, Number planUpdtNo) {

		if (planRevision.intValue() == 1) {
			utasPlan.requestPaarReview(planId, planVersion, planRevision, planAlterations, planUpdtNo, "BUTTON");
		} else {
			String messageText = "Warning: Multiple revisions exist for this Plan.\n\n"
					+ "If any of these revisions are not covered by the license being assigned, "
					+ "please create a new plan, and assign the appropriate license to it.";

			StringBuffer parameters = new StringBuffer();
			parameters.append("PLAN_ID=").append(planId).append(",PLAN_VERSION=").append(planVersion)
					.append(",PLAN_REVISION=").append(planRevision).append(",PLAN_ALTERATIONS=").append(planAlterations)
					.append(",PLAN_UPDT_NO=").append(planUpdtNo).append(",MESSAGE_TEXT=")
					.append(wrapUtils.wrapValue(messageText));

			String udvCaption = "Paar Warning";

			event.showUdv("PWUST_5AEE015ACEDA9CDDE05387971F0A6755", SoluminaConstants.UPDATE, udvCaption,
					StringUtils.EMPTY, parameters.toString());
		}
	}

	// Defect 220
	public void sendPaarExternalApproval(String requestId, String calledFrom)
	{
		
		Map requestInfo = paarRequestDao.selectPlanPaarRequestExternalInfo(requestId);
		Number planRevision = (Number) requestInfo.get("PLAN_REVISION");
		
		if(planRevision.intValue() == 1)
		{
			utasPaarRequest.sendPaarRequestApproval(requestId, calledFrom);
		}
		else
		{
			String messageText = "Warning: Multiple revisions exist for this plan.\n\n" + 
					             "Ensure the License/Security Group being approved applies to all revisions.";
			
			StringBuffer parameters = new StringBuffer();
			parameters.append("REQUEST_ID=").append(requestId)
									   	 .append(",@Tag=").append(calledFrom)
									   	 .append(",MESSAGE_TEXT=").append(wrapUtils.wrapValue(messageText));

			String udvCaption = "Paar Warning";

			event.showUdv("PWUST_5B66B7FB0E8AB0E5E05387971F0AA5CD", 
					SoluminaConstants.UPDATE,
					udvCaption,
					StringUtils.EMPTY,
					parameters.toString());
		}
	}

	//Begin Defect 680
	public void updatePlanWorkFlowPre(String planId,
			  Number planVersion,
			  Number planRevision,
			  Number planAlterations,
			  String planRevisionStatus,
			  String calledFrmDialog,
			  String configTool,
			  String calledFromConfirmMsg,
			  String deptId,
			  String centId,
			  String tag)
	{
		if (deptId == null || centId == null){
			message.raiseError("MFI_20", "The standard operation is missing a work department or center. "
					+ "Please update the header of the standard operation");
		}
		else {
			plan.updatePlanWorkFlowPre(planId,
					  planVersion,
					  planRevision,
					  planAlterations,
					  planRevisionStatus,
					  calledFrmDialog,
					  configTool,
					  calledFromConfirmMsg,
					  tag);
		}
	}
	//End Defect 680
	
	// Begin SMRO_PLG_301
	public List getEngineModelTabList(String planId,
			Number planVersion, 
			Number planRevison,
			String planAlterations,
                  String program)
    {
    	
    	List retList = new ArrayList();

    	List engineModelList = planDaoPW.selectEngineModelTabList(planId, planVersion, planRevison, planAlterations, program);

    	if(engineModelList.size() > 0)
    	{
    		for (int i=0;i< engineModelList.size();i++) 
    		{
    			Map map = (Map) engineModelList.get(i);

    			String engineModel = (String) map.get("ENGINE_MODEL");   
    			String description = (String) map.get("PROGRAM_MODEL_DESC");
    			String updtUserid = (String) map.get("UPDT_USERID");   
    			Date   timestamp = (Date) map.get("TIME_STAMP"); 

    			Map retMap = new HashMap();
    			retMap.put("ENGINE_MODEL", engineModel);  //Defect 879
    			retMap.put("PROGRAM_MODEL_DESC", description);
    			retMap.put("UPDT_USERID", updtUserid);
    			retMap.put("TIME_STAMP", timestamp); //signoffDate);
    			retList.add(retMap);
    		}
    	}
    	else
    	{
    		Map retMap = new HashMap();
    		retMap.put("ENGINE_MODEL",null);  //Defect 879
    		retMap.put("PROGRAM_MODEL_DESC",null);
    		retMap.put("UPDT_USERID", null);
    		retMap.put("TIME_STAMP", null);
    		retList.add(retMap);
    	}
    	return retList;
    }
	// End SMRO_PLG_301
}

