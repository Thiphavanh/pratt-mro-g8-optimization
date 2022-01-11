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
*  File:    WorkFlowImplPW.java
* 
*  Created: 2017-08-18
* 
*  Author:  c079222
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-08-18    D.Miron		    Initial Release
* 2017-10-13    D.Miron         SMRO_EXPT_201   Various updates.
* 2017-11-05    D.Miron         SMRO_APP_203    Updated for email notifications. Added method selectTaskSubmitter.
* 2017-11-05    D.Miron         Defect 219      Fixed Java Error when clicking OK on Complete Task UDV for Standard Text
* 2018-06-27    D.Miron         PW_SMRO_APP_201 Defect 861: Moved code here to check Work Dept/Center on Std Op release.
*/
package com.pw.solumina.sffnd.application.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.ICommunication;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.application.IWorkFlow;
import com.ibaset.solumina.sffnd.dao.ITaskDao;
import com.ibaset.solumina.sffnd.dao.IWorkFlowDao;
import com.ibaset.solumina.sfpl.dao.IPlanNotificationDao;
import com.ibaset.solumina.sfwid.application.IHold;

import com.pw.solumina.sffnd.application.CommunicationPW;
import com.pw.solumina.sffnd.dao.TaskDaoPW;
import com.pw.solumina.sffnd.dao.WorkFlowDaoPW;
import com.pw.solumina.sfpl.application.ClassifyPlanPW;
import com.pw.solumina.sfpl.application.MailPW;
import com.pw.solumina.sfpl.dao.PlanDaoPW;
import com.pw.solumina.sfwid.dao.IHoldDaoPW;
import com.pw.solumina.sfwid.dao.IHoldDaoPW;

public abstract class WorkFlowImplPW extends ImplementationOf<IWorkFlow> implements IWorkFlow
{
	
	@Reference 
	private TaskDaoPW taskDaoPW = null;
	
	@Reference 
	private IMessage message = null;
	
	@Reference 
	private ClassifyPlanPW classifyPlanPW = null;
	
	@Reference 
	private MailPW mailPW = null;
	
	@Reference 
	private PlanDaoPW planDaoPW = null;
	
	@Reference	
	private IPlanNotificationDao planNotificationDao = null;
	
	@Reference	
	private IEvent event = null;
	
	@Reference	
	private ICommunication iComm = null;
	
	@Reference 
	private CommunicationPW commPW = null;
	
	@Reference
    private ITaskDao taskDao = null;

	@Reference
    private IWorkFlowDao workFlowDao = null;

    @Reference
    private IHoldDaoPW holdDaoPW = null;
	
	@Reference
	private IHold hold = null;
	
	@Reference
	private WorkFlowDaoPW workFlowDaoPW;

	private String planId           = null;
	private Number planUpdtNo      = null;
	private Number planVersion     = null;
	private Number planRevision    = null;
	private Number planAlterations = null;
	private Map planMap;
	private String notificationMatrix = null;
	private String changeLevel = null;

	@Override
	public void workFlowUpdate(String taskId,
			String toTaskType,
			String toQueueType,
			String toStatus,
			String notes,
			Number blockEdit,
			String orderId,
			String calledFrom,
			String documentType, 
			Number slideModifyFlag)
	{
		if (StringUtils.equals(documentType, SoluminaConstants.PROCESS_PLAN_TEXT))
		{
			// Defect 219 Moved select inside if
			planMap = taskDaoPW.selectPlanningTaskInformation(taskId);
			planId             = (String) planMap.get("PLAN_ID");
			planVersion        = (Number) planMap.get("PLAN_VERSION");
			planRevision       = (Number) planMap.get("PLAN_REVISION");
			planAlterations    = (Number) planMap.get("PLAN_ALTERATIONS");
			planUpdtNo         = (Number) planMap.get("PLAN_UPDT_NO");
			notificationMatrix = (String) planMap.get("NOTIFICATION_MATRIX"); 

			if (toQueueType.equals("EXPT_ACCEPTANCE"))
			{
				if (StringUtils.equals(toStatus, "ACCEPT")) 
				{
					classifyPlanPW.updatePlanJCInfo(planId, planVersion, planRevision, planAlterations, planUpdtNo);
				}
				else if (StringUtils.equals(toStatus, "REJECT"))
				{
					// Cancel entry from xClass when Reject clicked at plan level
					commPW.cancelEntry(planId, planVersion, planRevision, planAlterations, notes, toQueueType, planUpdtNo);		
				}
			}

			// If reject, get original submitter to work flow.
			String submitter = null;
			if (StringUtils.equals(toStatus, "REJECT"))
			{
				submitter = taskDaoPW.selectTaskSubmitter(planId, planVersion, planRevision, planAlterations);
			}

			Super.workFlowUpdate(taskId,
					toTaskType,
					toQueueType,
					toStatus,
					notes,
					blockEdit,
					orderId,
					calledFrom,
					documentType,  
					slideModifyFlag);

			// TDODO:  Add approval notification email and export logic to following IF

			if (toQueueType.equals("PLG_AUTHORING") 
					&& StringUtils.equals(toStatus, "COMPLETE"))  
			{
				if( planRevision.intValue() == 1) 
				{
					classifyPlanPW.classifyPlan(planId, planVersion, planRevision, planAlterations, planUpdtNo, notificationMatrix);

				}

				//Send email to users requested
				if(notificationMatrix != null){
					mailPW.sendEmail(planId, planUpdtNo, notificationMatrix, toQueueType, changeLevel, taskId);
				}
			}

			// Create communication when for ACCEPT at QA_APPROVAL

			if ((toQueueType.equals("QA_APPROVAL"))
					&& StringUtils.equals(toStatus, "ACCEPT")
					&& !planDaoPW.userHasExportBypass(planId, planVersion, planRevision, planAlterations))	
			{
				this.createCommunication(toQueueType, documentType);

				event.refreshPrePlanningInstruction(planId,
						1,
						planRevision,
						0);
			}

			// If Reject was selected... 
			if (StringUtils.equals(toStatus, "REJECT"))
			{
				if (toQueueType.equals("ME_APPROVAL") || 
						toQueueType.equals("QA_APPROVAL") || 
						toQueueType.equals("EXPT_ACCEPTANCE"))
				{ 
					///Send emails to anyone who had previously approved the plan queues...
					List priorPlanApprovalsList = planDaoPW.selectAllPriorPlanApprovals(planId, planVersion, planRevision, planAlterations);

					//Send email to plan submitter
					if(!priorPlanApprovalsList.contains(submitter)) 
					{
						Map map = new HashMap();
						map.put("UPDT_USERID", submitter);
						priorPlanApprovalsList.add(map);	
					}

					for (int i=0; i<priorPlanApprovalsList.size(); i++)
					{
						Map map = (Map) priorPlanApprovalsList.get(i);
						String userid = (String) map.get("UPDT_USERID");

						mailPW.sendRejectEmail(userid, taskId, null, notes, "WorkFlowImplPW");
					}			
				}
			}
		}
		else if (StringUtils.equals(documentType, SoluminaConstants.DISCREPANCY_PROPER_CASE))
		{
			Map taskInformationRow = new HashMap();
			try
			{
				taskInformationRow = taskDao.selectTaskInformation(taskId);
			}
			catch (EmptyResultDataAccessException erdae)
			{

			}

			if (taskInformationRow == null || taskInformationRow.isEmpty())
			{
				// Invalid Task Status Change ( %V1 )
				message.raiseError("INVALID_TASK_STATUS_CHANGE", toStatus);
			}

			String fromTaskType = (String) taskInformationRow.get("TASK_TYPE");
			String fromQueueType = (String) taskInformationRow.get("QUEUE_TYPE");
			String fromTaskStatus = (String) taskInformationRow.get("STATUS");
			String orderNo = StringUtils.EMPTY;

			String endingTaskFlag = selectEndingTaskFlag(fromTaskType,
					fromQueueType,
					fromTaskStatus,
					toTaskType,
					toQueueType,
					toStatus);

			String discrepancyId = "";
			Number discrepancyLineNo = 1;

			if (StringUtils.equals(endingTaskFlag, SoluminaConstants.YES))
			{
				// Get DI from Task Id
				Map discrepancyIdLineNo = null;
				try
				{
					discrepancyIdLineNo = taskDao.selectDispositionTask(taskId);
				}
				catch (EmptyResultDataAccessException erdae)
				{
					
				}


				if (discrepancyIdLineNo != null) 
				{
					discrepancyId = (String) discrepancyIdLineNo.get("DISC_ID");
					discrepancyLineNo = (Number) discrepancyIdLineNo.get("DISC_LINE_NO");
				}
				
				

				Super.workFlowUpdate(taskId,
						toTaskType,
						toQueueType,
						toStatus,
						notes,
						blockEdit,
						orderId,
						calledFrom,
						documentType,  
						slideModifyFlag);

				//get the OrderId  Defect 274
				try {
					orderId = holdDaoPW.selectOrderId(discrepancyId, discrepancyLineNo);
				} catch (Exception e) {
					orderId = "";
				}
				

				//check to see if there is any holds on the order.  If there is, close the hold(s)
				List holdList = holdDaoPW.getHoldList(orderId, discrepancyId, discrepancyLineNo.toString(), "ORDER STOP", "DISCREPANCY LIEN");
				if (holdList!=null && !holdList.isEmpty()){

					Iterator iterator = holdList.iterator();
					while (iterator.hasNext()){
						Map map = (Map)iterator.next();

						String holdId = (String) map.get("HOLD_ID");
						String holdorderId = (String) map.get("ORDER_ID");
						String holdoperNo = (String) map.get("OPER_NO");

						hold.closeHold(holdorderId,
								holdoperNo,
								holdId,
								StringUtils.EMPTY);
					}
				}

			} else {
				Super.workFlowUpdate(taskId,
						toTaskType,
						toQueueType,
						toStatus,
						notes,
						blockEdit,
						orderId,
						calledFrom,
						documentType,  
						slideModifyFlag);


			}

		}
		// Defect 861
		else if (StringUtils.equals(documentType, SoluminaConstants.STANDARD_OPERATION_TEXT))
		{
			if (toQueueType.equals("STDOPER_AUTHORING") 
					&& StringUtils.equals(toStatus, "COMPLETE"))  
			{
				boolean dept = workFlowDaoPW.getWorkIds(taskId, "x.PLND_DEPARTMENT_ID");
				boolean cent = workFlowDaoPW.getWorkIds(taskId, "x.PLND_CENTER_ID");
				
				if (dept && cent) {
					Super.workFlowUpdate(taskId,
							toTaskType,
							toQueueType,
							toStatus,
							notes,
							blockEdit,
							orderId,
							calledFrom,
							documentType, 
							slideModifyFlag);
				}
				else {
					message.raiseError("MFI_20", "The standard operation is missing a work department or center. \n"
							+ "Please update the header of the standard operation");
				}
			}
		}
		// End Defect 861
		else {
			Super.workFlowUpdate(taskId,
					toTaskType,
					toQueueType,
					toStatus,
					notes,
					blockEdit,
					orderId,
					calledFrom,
					documentType,  
					slideModifyFlag);


		}

	}
		

	/** 
	 * SMRO_EXPT_201 - Oct 13, 2017
	 * 
	 * @author c079222 - David Miron
	 *
	 * @param toQueueType
	 * @param documentType
	 */
	public void createCommunication(String toQueueType,  String documentType)
	{
		String planTaskId = planNotificationDao.selectTaskId(planId, 
				1, 
				planRevision, 
				0);

		String queueType = toQueueType;
		String plant = null;
		plant = planDaoPW.getPlantForPlan(planId, planUpdtNo);	///Should always find a workLoc (plant).

		String toQueues = plant+"_PW_EXPT_APPROVER";
		String priority = null;
		String subject = "The following document is awaiting your EXPT approval";
		String messageInput = "The following document is awaiting your approval.";

		String rtcFlag = "N";
		//	int planRevision = planDaoPW.selectPlanRevision(planId,  planUpdtNo);

		if (planRevision.intValue() == 1)
		{
			rtcFlag = "Y";
		}

		iComm.createCommunication(planTaskId,
				documentType, queueType, toQueues, priority, "ACKNOWLEDGE",
				subject, messageInput, null, null, null, null, null, null, null,
				null,null,null,null,null,null,null,null,/*trimBaerClockId*/null,/*tokens[j]*/"EXPT",null,null,null,
				null,null,"Y","N",rtcFlag, changeLevel,null,null,null,null,null,null,
				null,null,null,null,null);
	}
	
	
	public String selectEndingTaskFlag(String fromTaskType,
			String fromQueueType,
			String fromTaskStatus,
			String toTaskType,
			String toQueueType,
			String toStatus)
	{
		String endingTaskFlag = null;
		try
		{
			endingTaskFlag = workFlowDao.selectEndingTaskFlag(fromTaskType,
					fromQueueType,
					fromTaskStatus,
					toTaskType,
					toQueueType,
					toStatus); // FND-17666
		}
		catch (IncorrectResultSizeDataAccessException noDataFound)
		{
			// Workflow setup is missing. Please contact your System
			// Administrator.
			message.raiseError("MFI_4513557D0DC1235DE0440003BA041A64");
		}
		return endingTaskFlag;
	}


}