/*
* This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017-2020.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    CommunicationImplPW.java
* 
*  Created: 2017-09-27
* 
*  Author:  c079222
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-09-27 	D.Miron		    SMRO_EXPT_201   Initial Release 
* 2017-11-05    D.Miron         SMRO_APP_203    Updated for email notifications.
* 2020-09-20    S.Edgerly       SMRO_QA_301     Defect 1771 - Error Acknowledging Task/Discrepancy Communication
* 2020-10-08	S.Edgerly		SMRO_QA_301		Defect 1788 - Task Communication Reply Button
*/
package com.pw.solumina.sffnd.application.impl;

import static com.ibaset.common.FrameworkConstants.DATE_MASK_NAME;
import static com.ibaset.common.FrameworkConstants.IN_QUEUE;
import static com.ibaset.common.SoluminaConstants.ACTIVE_STATUS;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.StringUtils.equalsIgnoreCase;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.util.SoluminaUtils;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.ICommunication;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.application.IParameters;
import com.ibaset.solumina.sffnd.application.IParse;
import com.ibaset.solumina.sffnd.application.IWorkFlow;
import com.ibaset.solumina.sffnd.application.IWrapUtils;
import com.ibaset.solumina.sffnd.dao.ICommunicationDao;
import com.ibaset.solumina.sffnd.dao.IStandardOperationDao;
import com.ibaset.solumina.sffnd.dao.ITaskDao;
import com.ibaset.solumina.sffnd.application.IQueue;
import com.ibaset.solumina.sfpl.dao.IBomDao;
import com.ibaset.solumina.sfpl.application.IPlan;
import com.ibaset.solumina.sfpl.dao.IPlanDao;
import com.ibaset.solumina.sfqa.application.IDiscrepancy;
import com.ibaset.solumina.sfwid.application.IOrder;
import com.pw.solumina.sffnd.dao.CommunicationDaoPW;
import com.pw.solumina.sfpl.application.MailPW;
import com.pw.solumina.sfpl.application.PlanPW;
import com.pw.solumina.sfpl.dao.PlanDaoPW;
import com.pw.solumina.sffnd.application.ICommunicationPW;

import com.pw.common.dao.CommonDaoPW;
import com.pw.solumina.sffnd.dao.TaskDaoPW;

public abstract class CommunicationImplPW extends ImplementationOf<ICommunication> implements ICommunication, ICommunicationPW
{

	@Reference private IMessage message;
	@Reference private IParse parse;
    @Reference private ITaskDao taskDao;
    @Reference private ICommunicationDao communicationDao;
    @Reference private com.ibaset.solumina.sfcore.application.IUser privilege;
    @Reference private IParameters parameters;
    @Reference private IWrapUtils wrapUtils;
    @Reference private IEvent eventUdv;
    @Reference private IPlan iPlan;
    @Reference private IWorkFlow iWorkFlow;
    @Reference private IEvent event;
    @Reference private IOrder order;
    @Reference private PlanPW planPW;
    @Reference private PlanDaoPW planDaoPW;
    @Reference private CommunicationDaoPW communicationDaoPW;
    @Reference private CommonDaoPW commonDaoPW = null;
	@Reference private MailPW mailPW = null;
	@Reference private IPlanDao planDao = null;
	@Reference private IDiscrepancy discrepancy = null;
	@Reference private IBomDao bomDao = null;
	@Reference private IQueue queue = null;
    @Reference private TaskDaoPW taskDaoPW = null;

    public void createCommunication(String taskId,
			String documentType,
			String queueType,
			String toQueues,
			String priority,
			String replyRequirement,
			String subject,
			String messageInput,
			String scheduleStart,
			String scheduleEnd,
			String ucfCommunicationVch1,
			String ucfCommunicationVch2,
			String ucfCommunicationVch3,
			String ucfCommunicationVch4,
			String ucfCommunicationVch5,
			String ucfCommunicationVch6,
			String ucfCommunicationVch7,
			String ucfCommunicationVch8,
			String ucfCommunicationVch9,
			String ucfCommunicationVch10,
			String ucfCommunicationVch11,
			String ucfCommunicationVch12,
			String ucfCommunicationVch13,
			String ucfCommunicationVch14,
			String ucfCommunicationVch15,
			Number ucfCommunicationNumber1,
			Number ucfCommunicationNumber2,
			Number ucfCommunicationNumber3,
			Number ucfCommunicationNumber4,
			Number ucfCommunicationNumber5,
			String ucfCommunicationFlag1,
			String ucfCommunicationFlag2,
			String ucfCommunicationFlag3,
			String ucfCommunicationFlag4,
			String ucfCommunicationFlag5,
			Date ucfCommunicationDate1,
			Date ucfCommunicationDate2,
			Date ucfCommunicationDate3,
			Date ucfCommunicationDate4,
			Date ucfCommunicationDate5,
			String ucfCommunicationVch2551,
			String ucfCommunicationVch2552,
			String ucfCommunicationVch2553,
			String ucfCommunicationVch40001,
			String ucfCommunicationVch40002)
	{
		
		/*
		 * UCF's used in comm_Create
		 * 
		 *		UCF_COMM_DATE1	DATE		Approval Date
		 *		UCF_COMM_FLAG1	VARCHAR2	Auto generatedFlag ('Y' indicates it's a pratt communication)
		 *		UCF_COMM_FLAG2	VARCHAR2	Auto approval Flag
		 *		UCF_COMM_VCH1	VARCHAR2	Approver Name
		 *		UCF_COMM_VCH2	VARCHAR2	Submitter
		 *		UCF_COMM_VCH3	VARCHAR2	Rejection Reason 
		 *		UCF_COMM_VCH4	VARCHAR2	Reject Reason Typos
		 *		UCF_COMM_VCH5	VARCHAR2	Reject Reason Std Work
		 *		UCF_COMM_VCH6	VARCHAR2	Reject Reason Procedural
		 *		UCF_COMM_VCH7	VARCHAR2	Reject Reason Process
		 */
			
		if(ucfCommunicationFlag1 != "Y"){

			this.Super.createCommunication(taskId, documentType, queueType, toQueues, priority, replyRequirement, subject, messageInput, 
					                      scheduleStart, scheduleEnd, ucfCommunicationVch1, ucfCommunicationVch2, ucfCommunicationVch3, 
					                      ucfCommunicationVch4, ucfCommunicationVch5, ucfCommunicationVch6, ucfCommunicationVch7, 
					                      ucfCommunicationVch8, ucfCommunicationVch9, ucfCommunicationVch10, ucfCommunicationVch11, 
					                      ucfCommunicationVch12, ucfCommunicationVch13, ucfCommunicationVch14, ucfCommunicationVch15, 
					                      ucfCommunicationNumber1, ucfCommunicationNumber2, ucfCommunicationNumber3, ucfCommunicationNumber4, 
					                      ucfCommunicationNumber5, ucfCommunicationFlag1, ucfCommunicationFlag2, ucfCommunicationFlag3, 
					                      ucfCommunicationFlag4, ucfCommunicationFlag5, ucfCommunicationDate1, ucfCommunicationDate2, 
					                      ucfCommunicationDate3, ucfCommunicationDate4, ucfCommunicationDate5, ucfCommunicationVch2551,
					          			  ucfCommunicationVch2552,ucfCommunicationVch2553,ucfCommunicationVch40001,ucfCommunicationVch40002);			 
		}
		else
		{
	        String newCommunicationId = null;
	        String[] localToQueues = null;
	        Number schedule = new Integer(0);
	        Date scheduleStartDate = getScheduleDate(scheduleStart);
	        Date scheduleEndDate = getScheduleDate(scheduleEnd);
	        String fullName = null;

	        fullName = commonDaoPW.getUserFullName(ContextUtil.getUsername());
	        
	        validateTaskStatusForCommunication(taskId);

	        if ((scheduleStartDate != null) && (scheduleEndDate != null))
	        {
	            schedule = scheduleDateCheck(scheduleStartDate, scheduleEndDate);
	        }

	        verifyRevStatusAndUserPrivForQueueType(queueType);

	        newCommunicationId = communicationDao.selectNextSequenceId();

	        priority = presetPriority(priority);

	        String messageInputText = insertTextObjectAndReturnInputText(messageInput);  // GE-4372
	        
	        // The Order Id, Operation Key and Step Key are removed.
	        communicationDao.insertCommunication(newCommunicationId,
                    taskId,
                    documentType,
                    queueType,
                    subject,
                    priority,
                    messageInputText,  // GE-4372
                    ucfCommunicationVch1,
                    fullName, /*ucfCommunicationVch2*/
                    ucfCommunicationVch3,
                    ucfCommunicationVch4,
                    ucfCommunicationVch5,
                    ucfCommunicationVch6,
                    ucfCommunicationVch7,
                    ucfCommunicationVch8,
                    ucfCommunicationVch9,
                    ucfCommunicationVch10,
                    ucfCommunicationVch11,
                    ucfCommunicationVch12,
                    ucfCommunicationVch13,
                    ucfCommunicationVch14,
                    ucfCommunicationVch15,
                    ucfCommunicationNumber1,
                    ucfCommunicationNumber2,
                    ucfCommunicationNumber3,
                    ucfCommunicationNumber4,
                    ucfCommunicationNumber5,
                    ucfCommunicationFlag1,
                    ucfCommunicationFlag2,
                    ucfCommunicationFlag3,
                    ucfCommunicationFlag4,
                    ucfCommunicationFlag5,
                    ucfCommunicationDate1,
                    ucfCommunicationDate2,
                    ucfCommunicationDate3,
                    ucfCommunicationDate4,
                    ucfCommunicationDate5,
                    ucfCommunicationVch2551,   //FND-22919
                    ucfCommunicationVch2552,
                    ucfCommunicationVch2553,
                    ucfCommunicationVch40001,
                    ucfCommunicationVch40002);

	        if (!StringUtils.isEmpty(toQueues)) 
	        {
	            localToQueues = parse.parse(toQueues,
	                                        SoluminaConstants.SEMI_COLON,
	                                        true);

	            for (int i = 0; i < localToQueues.length; i++)
	            {
	                communicationDao.insertCommunicationRoute(newCommunicationId,
	                                                          localToQueues[i],
	                                                          replyRequirement,
	                                                          scheduleStartDate,
	                                                          scheduleEndDate,
	                                                          schedule);
	            }
	        }
	        
	        this.Super.sendCommunication(newCommunicationId);
	    }
	}


	public void replyCommunication(String communicationId,
            String toQueue,
            String fromQueue,
            String priority,
            String replyRequirement,
            String subject,
            String messageInput,
            String scheduledStart,
            String scheduledEnd,
            String ucfCommunicationVch1,
            String ucfCommunicationVch2,
            String ucfCommunicationVch3,
            String ucfCommunicationVch4,
            String ucfCommunicationVch5,
            String ucfCommunicationVch6,
            String ucfCommunicationVch7,
            String ucfCommunicationVch8,
            String ucfCommunicationVch9,
            String ucfCommunicationVch10,
            String ucfCommunicationVch11,
            String ucfCommunicationVch12, 
            String ucfCommunicationVch13,
            String ucfCommunicationVch14,
            String ucfCommunicationVch15,
            Number ucfCommunicationNumber1,
            Number ucfCommunicationNumber2,
            Number ucfCommunicationNumber3,
            Number ucfCommunicationNumber4,
            Number ucfCommunicationNumber5,
            String ucfCommunicationFlag1,
            String ucfCommunicationFlag2,
            String ucfCommunicationFlag3,
            String ucfCommunicationFlag4,
            String ucfCommunicationFlag5,
            Date ucfCommunicationDate1,
            Date ucfCommunicationDate2,
            Date ucfCommunicationDate3,
            Date ucfCommunicationDate4,
            Date ucfCommunicationDate5,
			String ucfCommunicationVch2551,
			String ucfCommunicationVch2552,
			String ucfCommunicationVch2553,
			String ucfCommunicationVch40001,
			String ucfCommunicationVch40002)
	{
		/*
		UCF_COMM_DATE1	DATE		Approval Date
		UCF_COMM_FLAG1	VARCHAR2	Auto generatedFlag
		UCF_COMM_FLAG2	VARCHAR2	Auto approval Flag
		UCF_COMM_VCH1	VARCHAR2	Approver Name
		UCF_COMM_VCH2	VARCHAR2	Submitter
		UCF_COMM_VCH3	VARCHAR2	Rejection Reason 
		UCF_COMM_VCH4	VARCHAR2	Reject Reason Typos
		UCF_COMM_VCH5	VARCHAR2	Reject Reason Std Work
		UCF_COMM_VCH6	VARCHAR2	Reject Reason Procedural
		UCF_COMM_VCH7	VARCHAR2	Reject Reason Process
		*/
/*		if(ucfCommunicationVch3 != null){
			if(ucfCommunicationVch3.contains("Minor")){ucfCommunicationVch4 = "Typos";}
			if(ucfCommunicationVch3.contains("StdWork")){ucfCommunicationVch5 = "Work Instructions do not comply with PFPS";}
			if(ucfCommunicationVch3.contains("Procedural")){ucfCommunicationVch6 = "Work Instructions violate procedural requirements";}
			if(ucfCommunicationVch3.contains("Process")){ucfCommunicationVch7 = "Issues with Tooling, Gaging, Process Steps";}
		}*/

		java.util.Date date= new java.util.Date();
		
		//subject = "Rejected"; //defect 1788
		this.Super.replyCommunication(communicationId, toQueue, fromQueue, priority, 
				replyRequirement, subject, messageInput, scheduledStart, scheduledEnd, 
				ContextUtil.getUsername(), ucfCommunicationVch2, ucfCommunicationVch3, 
				ucfCommunicationVch4, ucfCommunicationVch5, ucfCommunicationVch6, 
				ucfCommunicationVch7, ucfCommunicationVch8, ucfCommunicationVch9, 
				ucfCommunicationVch10, ucfCommunicationVch11, ucfCommunicationVch12, 
				ucfCommunicationVch13, ucfCommunicationVch14, ucfCommunicationVch15, 
				ucfCommunicationNumber1, ucfCommunicationNumber2, ucfCommunicationNumber3, 
				ucfCommunicationNumber4, ucfCommunicationNumber5, ucfCommunicationFlag1, 
				ucfCommunicationFlag2, ucfCommunicationFlag3, ucfCommunicationFlag4, 
				ucfCommunicationFlag5, new Timestamp(date.getTime()), ucfCommunicationDate2, 
				ucfCommunicationDate3, ucfCommunicationDate4, ucfCommunicationDate5,
				ucfCommunicationVch2551, ucfCommunicationVch2552, ucfCommunicationVch2553,
				ucfCommunicationVch40001, ucfCommunicationVch40002);
		
		Map commMap = communicationDao.selectCommunicationInformation(communicationId);
		String documentType = (String) commMap.get("DOC_TYPE");
		
		if(!StringUtils.equals(documentType,"Discrepancy")){//defect 1788
			//get plan information

	        Map planMap = communicationDaoPW.getPlanInfoFromCommID(communicationId);
	        
	        String planID;
	        Number planVersion;
	        Number planRevision;
	        Number planAlterations;
	        String taskID;
	        
	        planID = (String) planMap.get("PLAN_ID");
	        planVersion = (Number) planMap.get("PLAN_VERSION");
	        planRevision = (Number) planMap.get("PLAN_REVISION");
	        planAlterations = (Number) planMap.get("PLAN_ALTERATIONS");
	        taskID = (String) planMap.get("TASK_ID");
	            
	        //Send emails to anyone who had previously approved steps or approved the plan queues...
			List useridsAndQueuesForPriorPlanApprovalsList = planDaoPW.selectAllPriorPlanApprovals(planID, planVersion, planRevision, planAlterations);
			
	        //Send email to plan submitter
	        String submitter = taskDaoPW.selectTaskSubmitter(planID, planVersion, planRevision, planAlterations);
			if(!useridsAndQueuesForPriorPlanApprovalsList.contains(submitter)) 
			{
				Map map = new HashMap();
				map.put("UPDT_USERID", submitter);
				useridsAndQueuesForPriorPlanApprovalsList.add(map);
			}
			
			for (int i=0; i<useridsAndQueuesForPriorPlanApprovalsList.size(); i++)
			{
					Map map = (Map) useridsAndQueuesForPriorPlanApprovalsList.get(i);
					String userid = (String) map.get("UPDT_USERID");
					mailPW.sendRejectEmail(userid, taskID, ucfCommunicationVch3, messageInput, "CommunicationImplPW");		
			}
			
	        // refreshing document
		
			String oldQueueType = EMPTY;
	
			try
			{
				Map currentTast = (Map) bomDao.selectCurrentPlanTaskInfo(planID,
						                                                 planVersion,
						                                                 planRevision,
						                                                 planAlterations);
				oldQueueType = (String) currentTast.get("QUEUE_TYPE");
			}
			catch (EmptyResultDataAccessException erdae)
			{   
	
			}
			iPlan.returnToPriorPlanningTask(taskID,
					                        planID,
					                        planVersion,
					                        planRevision,
					                        planAlterations,
					                        oldQueueType,
					                        "PLG_AUTHORING",
					                        messageInput);
	
			event.refreshPrePlanningInstruction(planID, 1, planRevision, 0);
		}
	}


	public void preAcknowledgeCommunication(String communicationId,
            String userid)
	{		
		Map commMap = communicationDao.selectCommunicationInformation(communicationId);
		
		String taskId = (String) commMap.get("TASK_ID");
		String documentType = (String) commMap.get("DOC_TYPE");
		
		String agency = communicationDaoPW.getCommApprover(communicationId);
		
		if(!documentType.equals("Process Order"))
		{
			this.Super.preAcknowledgeCommunication(communicationId, userid);
			communicationDaoPW.updateApprovalStatus(communicationId, userid);
			
			// Defect 1639 ends.
			
			if(!documentType.equals("Discrepancy")){//defect 1771
				Map map = taskDao.selectPlanningTaskInformation(taskId);
				String planId  = (String)  map.get("PLAN_ID");
				Number planRevision = (Number) map.get("PLAN_REVISION");
	
				event.refreshPrePlanningInstruction(planId,
							1,
							planRevision,
							0);
			}
		}
/*		else{ //DocumentType does equal 'Process Order'
			
		    String orderId = (String) commMap.get("ORDER_ID");
			String plant = orderDaoPW.selectOrderWorkLoc(orderId);	
			String priv = plant+"_PW_"+agency+"_APPROVER";
			
			if(privilege.hasPrivilege(priv)){
				this.Super.preAcknowledgeCommunication(communicationId, userid);
				communicationDaoPW.updateApprovalStatus(communicationId, userid);
			}else{
				message.raiseError("MFI_20","You do not have the appropriate priv to approve this communication");
			}
			
			boolean communicationHoldExists = communicationDao.selectIsHoldOnCommunication(taskId,null);

			if (!communicationHoldExists)
			{
				Map taskMap = taskDao.selectAlterationAndOrderIds(taskId);

				final String toTaskType = "ALTERATION";
				final String orderAlterStatus = "ORDER_AUTHORING2";
				final String toStatus = "COMPLETE";
				final String notes = null;
				final Number blockEdit = 0;
				final String calledFrom = null;
				final String alterationId = (String) taskMap.get("ALT_ID");
				final String calledFrmDialog = "N";
				
				order.updateOrderWorkFlowPre(taskId, toTaskType, orderAlterStatus, toStatus,
						notes, blockEdit, orderId, calledFrom, alterationId, calledFrmDialog );
				
				event.refreshTool();
			}
		}*/
	}


	public void replyCommunicationPre(String communicationId,
            String ucfCommunicationVch1,
            String ucfCommunicationVch2,
            String ucfCommunicationVch3,
            String ucfCommunicationVch4,
            String ucfCommunicationVch5,
            String ucfCommunicationVch6,
            String ucfCommunicationVch7,
            String ucfCommunicationVch8,
            String ucfCommunicationVch9,
            String ucfCommunicationVch10,
            String ucfCommunicationVch11,
            String ucfCommunicationVch12,
            String ucfCommunicationVch13,
            String ucfCommunicationVch14,
            String ucfCommunicationVch15,
            Number ucfCommunicationNumber1,
            Number ucfCommunicationNumber2,
            Number ucfCommunicationNumber3,
            Number ucfCommunicationNumber4,
            Number ucfCommunicationNumber5,
            String ucfCommunicationFlag1,
            String ucfCommunicationFlag2,
            String ucfCommunicationFlag3,
            String ucfCommunicationFlag4,
            String ucfCommunicationFlag5,
            Date ucfCommunicationDate1,
            Date ucfCommunicationDate2,
            Date ucfCommunicationDate3,
            Date ucfCommunicationDate4,
            Date ucfCommunicationDate5,
			String ucfCommunicationVch2551,
			String ucfCommunicationVch2552,
			String ucfCommunicationVch2553,
			String ucfCommunicationVch40001,
			String ucfCommunicationVch40002,
            String calledFrom){
	
		
		if(!ucfCommunicationFlag1.equals("Y") && !calledFrom.equals("REJECT")){
			this.Super.replyCommunicationPre(communicationId, ucfCommunicationVch1, ucfCommunicationVch2, ucfCommunicationVch3, ucfCommunicationVch4, ucfCommunicationVch5, ucfCommunicationVch6, ucfCommunicationVch7, ucfCommunicationVch8, ucfCommunicationVch9, ucfCommunicationVch10, ucfCommunicationVch11, ucfCommunicationVch12, ucfCommunicationVch13, ucfCommunicationVch14, ucfCommunicationVch15, ucfCommunicationNumber1, ucfCommunicationNumber2, ucfCommunicationNumber3, ucfCommunicationNumber4, ucfCommunicationNumber5, ucfCommunicationFlag1, ucfCommunicationFlag2, ucfCommunicationFlag3, ucfCommunicationFlag4, ucfCommunicationFlag5, ucfCommunicationDate1, ucfCommunicationDate2, ucfCommunicationDate3, ucfCommunicationDate4, ucfCommunicationDate5);
		}
		else{
 			String subject = null;
	        String queueType = null;
	        SimpleDateFormat dateFormatter = new SimpleDateFormat(parameters.getClientSessionParameter(SoluminaConstants.DATE_TIME_MASK_NAME));
	        Map toQueue = communicationDao.selectCommunicationInformation(communicationId);
	        queueType = (String) toQueue.get("FROM_QUEUE");
	        subject = (String) toQueue.get("SUBJECT");

	        /** * Start Ankur ** */
	        // 04/28/2006 Pratibha Agrawal #FND-4435
	        // Added UCF fields.
	        // Creates the Parameters to pass in UDV.
	        StringBuffer parameters = new StringBuffer();

	        communicationId = StringUtils.defaultIfEmpty(communicationId,
	                                                     StringUtils.EMPTY);
	        subject = StringUtils.defaultIfEmpty(subject, StringUtils.EMPTY);
	        queueType = StringUtils.defaultIfEmpty(queueType, StringUtils.EMPTY);
	        ucfCommunicationVch1 = StringUtils.defaultIfEmpty(ucfCommunicationVch1,
	                                                          StringUtils.EMPTY);
	        ucfCommunicationVch2 = StringUtils.defaultIfEmpty(ucfCommunicationVch2,
	                                                          StringUtils.EMPTY);
	        ucfCommunicationVch3 = StringUtils.defaultIfEmpty(ucfCommunicationVch3,
	                                                          StringUtils.EMPTY);
	        ucfCommunicationVch4 = StringUtils.defaultIfEmpty(ucfCommunicationVch4,
	                                                          StringUtils.EMPTY);
	        ucfCommunicationVch5 = StringUtils.defaultIfEmpty(ucfCommunicationVch5,
	                                                          StringUtils.EMPTY);
	        ucfCommunicationVch6 = StringUtils.defaultIfEmpty(ucfCommunicationVch6,
	                                                          StringUtils.EMPTY);
	        ucfCommunicationVch7 = StringUtils.defaultIfEmpty(ucfCommunicationVch7,
	                                                          StringUtils.EMPTY);
	        ucfCommunicationVch8 = StringUtils.defaultIfEmpty(ucfCommunicationVch8,
	                                                          StringUtils.EMPTY);
	        ucfCommunicationVch9 = StringUtils.defaultIfEmpty(ucfCommunicationVch9,
	                                                          StringUtils.EMPTY);
	        ucfCommunicationVch10 = StringUtils.defaultIfEmpty(ucfCommunicationVch10,
	                                                           StringUtils.EMPTY);
	        ucfCommunicationVch11 = StringUtils.defaultIfEmpty(ucfCommunicationVch11,
	                                                           StringUtils.EMPTY);
	        ucfCommunicationVch12 = StringUtils.defaultIfEmpty(ucfCommunicationVch12,
	                                                           StringUtils.EMPTY);
	        ucfCommunicationVch13 = StringUtils.defaultIfEmpty(ucfCommunicationVch13,
	                                                           StringUtils.EMPTY);
	        ucfCommunicationVch14 = StringUtils.defaultIfEmpty(ucfCommunicationVch14,
	                                                           StringUtils.EMPTY);
	        ucfCommunicationVch15 = StringUtils.defaultIfEmpty(ucfCommunicationVch15,
	                                                           StringUtils.EMPTY);
	        ucfCommunicationFlag1 = StringUtils.defaultIfEmpty(ucfCommunicationFlag1,
	                                                           StringUtils.EMPTY);
	        ucfCommunicationFlag2 = StringUtils.defaultIfEmpty(ucfCommunicationFlag2,
	                                                           StringUtils.EMPTY);
	        ucfCommunicationFlag3 = StringUtils.defaultIfEmpty(ucfCommunicationFlag3,
	                                                           StringUtils.EMPTY);
	        ucfCommunicationFlag4 = StringUtils.defaultIfEmpty(ucfCommunicationFlag4,
	                                                           StringUtils.EMPTY);
	        ucfCommunicationFlag5 = StringUtils.defaultIfEmpty(ucfCommunicationFlag5,
	                                                           StringUtils.EMPTY);

	        // Appends parameters.
	        parameters.append("COMM_ID=")
	                  .append(communicationId)
	                  .append(",TO_QUEUE=")
	                  .append(wrapUtils.wrapValue(queueType))
	                  .append(",SUBJECT=")
	                  .append(wrapUtils.wrapValue("RE: " + subject))
	                  .append(",UCF_COMM_VCH1=")
	                  .append(ucfCommunicationVch1)
	                  .append(",UCF_COMM_VCH2=")
	                  .append(ucfCommunicationVch2)
	                  .append(",UCF_COMM_VCH3=")
	                  .append(ucfCommunicationVch3)
	                  .append(",UCF_COMM_VCH4=")
	                  .append(ucfCommunicationVch4)
	                  .append(",UCF_COMM_VCH5=")
	                  .append(ucfCommunicationVch5)
	                  .append(",UCF_COMM_VCH6=")
	                  .append(ucfCommunicationVch6)
	                  .append(",UCF_COMM_VCH7=")
	                  .append(ucfCommunicationVch7)
	                  .append(",UCF_COMM_VCH8=")
	                  .append(ucfCommunicationVch8)
	                  .append(",UCF_COMM_VCH9=")
	                  .append(ucfCommunicationVch9)
	                  .append(",UCF_COMM_VCH10=")
	                  .append(ucfCommunicationVch10)
	                  .append(",UCF_COMM_VCH11=")
	                  .append(ucfCommunicationVch11)
	                  .append(",UCF_COMM_VCH12=")
	                  .append(ucfCommunicationVch12)
	                  .append(",UCF_COMM_VCH13=")
	                  .append(ucfCommunicationVch13)
	                  .append(",UCF_COMM_VCH14=")
	                  .append(ucfCommunicationVch14)
	                  .append(",UCF_COMM_VCH15=")
	                  .append(ucfCommunicationVch15)
	                  .append(",UCF_COMM_NUM1=");

	        if (ucfCommunicationNumber1 != null)
	        {
	            parameters.append(ucfCommunicationNumber1);
	        }
	        parameters.append(",UCF_COMM_NUM2=");

	        if (ucfCommunicationNumber2 != null)
	        {
	            parameters.append(ucfCommunicationNumber2);
	        }
	        parameters.append(",UCF_COMM_NUM3=");

	        if (ucfCommunicationNumber3 != null)
	        {
	            parameters.append(ucfCommunicationNumber3);
	        }
	        parameters.append(",UCF_COMM_NUM4=");

	        if (ucfCommunicationNumber4 != null)
	        {
	            parameters.append(ucfCommunicationNumber4);
	        }
	        parameters.append(",UCF_COMM_NUM5=");

	        if (ucfCommunicationNumber5 != null)
	        {
	            parameters.append(ucfCommunicationNumber5);
	        }

	        parameters.append(",UCF_COMM_FLAG1=")
	                  .append(ucfCommunicationFlag1)
	                  .append(",UCF_COMM_FLAG2=")
	                  .append(ucfCommunicationFlag2)
	                  .append(",UCF_COMM_FLAG3=")
	                  .append(ucfCommunicationFlag3)
	                  .append(",UCF_COMM_FLAG4=")
	                  .append(ucfCommunicationFlag4)
	                  .append(",UCF_COMM_FLAG5=")
	                  .append(ucfCommunicationFlag5)
	                  .append(",UCF_COMM_DATE1=");

	        if (ucfCommunicationDate1 != null)
	        {
	            String tempUcfCommunicationDate1 = dateFormatter.format(ucfCommunicationDate1);
	            wrapUtils.wrapValue(tempUcfCommunicationDate1);
	            parameters.append(tempUcfCommunicationDate1);

	        }

	        parameters.append(",UCF_COMM_DATE2=");

	        if (ucfCommunicationDate2 != null)
	        {
	            String tempUcfCommunicationDate2 = dateFormatter.format(ucfCommunicationDate2);
	            wrapUtils.wrapValue(tempUcfCommunicationDate2);
	            parameters.append(tempUcfCommunicationDate2);
	        }

	        parameters.append(",UCF_COMM_DATE3=");

	        if (ucfCommunicationDate3 != null)
	        {
	            String tempUcfCommunicationDate3 = dateFormatter.format(ucfCommunicationDate3);
	            wrapUtils.wrapValue(tempUcfCommunicationDate3);
	            parameters.append(tempUcfCommunicationDate3);
	        }

	        parameters.append(",UCF_COMM_DATE4=");

	        if (ucfCommunicationDate4 != null)
	        {
	            String tempUcfCommunicationDate4 = dateFormatter.format(ucfCommunicationDate4);
	            wrapUtils.wrapValue(tempUcfCommunicationDate4);
	            parameters.append(tempUcfCommunicationDate4);
	        }

	        parameters.append(",UCF_COMM_DATE5=");

	        if (ucfCommunicationDate5 != null)
	        {
	            String tempUcfCommunicationDate5 = dateFormatter.format(ucfCommunicationDate5);
	            wrapUtils.wrapValue(tempUcfCommunicationDate5);
	            parameters.append(tempUcfCommunicationDate5);
	        }
	        
	        parameters.append(",UCF_COMM_VCH255_1=")
			  		  .append(ucfCommunicationVch2551)
			  		  .append(",UCF_COMM_VCH255_2=")
			  		  .append(ucfCommunicationVch2552)
			  		  .append(",UCF_COMM_VCH255_3=")
			  		  .append(ucfCommunicationVch2553)
			  		  .append(",UCF_COMM_VCH4000_1=")
			  		  .append(ucfCommunicationVch40001)
			  		  .append(",UCF_COMM_VCH4000_2=")
			  		  .append(ucfCommunicationVch40002);
        
	        String fromQueueType = communicationDaoPW.commReplySel(communicationId);
 
	        parameters.append(",QUEUE_TYPE=");
	        parameters.append(fromQueueType);
	        
	        
	        // Shows the UDV.
	        //Launches the rejection UDV
	        eventUdv.showUdv("PWUST_5A9D866D82CE3FE5E05387971F0ADE00",  // atcapp udv id
	                         StringUtils.upperCase(SoluminaConstants.INSERT),
	                         "Rejecting Approvals",
	                         "",
	                         parameters.toString());
		}
		
	}

	/**
	 * @param date
	 * @return
	 */
    protected Date getScheduleDate(String date)
    {
    	Date scheduleDate = null;
    	if (date != null)
        {
            try
            {
                scheduleDate = SoluminaUtils.parseDate(date); // FND-18291
            }
            catch (ParseException e1)
            {
                message.raiseError("MFI_50848", parameters.getClientSessionParameter(DATE_MASK_NAME));
            }
        }
    	return scheduleDate;
    }
    
	/**
	 * @param taskId
	 */
	protected void validateTaskStatusForCommunication(String taskId) 
	{
		// Validate that the FROM_QUEUE (task) is in an active/open status.
        try
        {
            // Gets the Task Status.
            Map map = taskDao.selectTaskInformation(taskId);
            String taskStatus = (String) map.get("STATUS");

            // Communications can only be generated when Queue Type Task Status
            // is 'IN QUEUE' or 'ACTIVE'.
            if (!(equalsIgnoreCase(taskStatus, IN_QUEUE) || equalsIgnoreCase(taskStatus, ACTIVE_STATUS)))
            {
                message.raiseError("MFI_1A270426568947D5E0440003BA560E35");
            }
        }
        catch (EmptyResultDataAccessException exception)
        {
        }
	}
	
	/**
	 * @param queueType
	 */
	protected void verifyRevStatusAndUserPrivForQueueType(String queueType) 
	{
		// verify REV_STATUS exists as a QUEUE_TYPE and the current user has the
        // privilege
		if (!communicationDao.selectVerifyStatusIsAQueue(queueType))
        {
            message.raiseError("MFI_67162"); // Message Text: Cannot
            // initiate communication
            // from this rev_status
        }

        if (!privilege.hasPrivilege(queueType))
        {
            message.raiseError("MFI_67163"); // Message Text: You do
            // not have the proper privilege to perform this function
        }
	}
	
    private String presetPriority(String priority)
    {
        String value = "3";

        if (equalsIgnoreCase("High", priority))
        {
            value = "1";
        }

        else if (equalsIgnoreCase("Medium", priority))
        {
            value = "2";
        }

        else if (equalsIgnoreCase("Low", priority))
        {
            value = "3";
        }

        return value;
    }
    
    /**
     * @param messageInput
     * @return
     */
    protected String insertTextObjectAndReturnInputText(String messageInput)
    {
    	String objectId = planDao.selectNewObjectId();
        String refId = objectId;
        
    	String messageInputText = "<UT=\"Embed(TextObject(@SQL_ID=@Inline,OBJECT_ID="
        		+ objectId + 
        		",REF_ID=" + refId + "))\">";
    	
    	String messageInputRtfText = discrepancy.makeRTFText(messageInput);
    	String language = parameters.getClientSessionParameter("@LanguageExt");
    	
    	order.insertTextObject(objectId, language, messageInputRtfText, messageInput);
    	
    	return messageInputText;
    }
}

