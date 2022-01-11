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
 *  File:    MailPW.java
 * 
 *  Created: 2017-09-27
 * 
 *  Author:  c079222
 * 
 *  Revision History
 * 
 *  Date      	Who             Description
 *  ----------	---------------	---------------	---------------------------------------------------
 * 2017-09-27   D.Miron		    SMRO_EXPT_201 Initial Release 
 * 2017-11-05   D.Miron         SMRO_APP_203  Updated for email notifications.
 */
package com.pw.solumina.sfpl.application;

import java.util.Map;

import com.ibaset.common.Reference;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.solumina.alert.mail.MailService;
import com.pw.common.dao.CommonDaoPW;
import com.pw.solumina.sffnd.dao.CommunicationDaoPW;
import com.pw.solumina.sfpl.dao.PlanDaoPW;

public class MailPW {

	@Reference 
	private CommonDaoPW commonDaoPW = null;

	@Reference	
	private CommunicationDaoPW commDao = null;

	@Reference 
	private PlanDaoPW planDaoPW = null;

	@Reference	
	private MailService mailService = null;

	public void sendEmail(String planId, Number planUpdtNo, String notificationMatrix, 
			              String queueType, String changeLevel, String taskId)
	{  
		Map planMap = null;
		
		String planNo = null;
		String partNo = null;
		String planTitle = null;
		String chgHist = null;
		String userid = null;
		String to = null;
		
		Number planVersion = null;
		Number planRevision = null;
		Number planAlterations = null;

		planMap = planDaoPW.getEmailFromTask(taskId);

		planId = (String) planMap.get("PLAN_ID");
		planUpdtNo = (Number) planMap.get("PLAN_UPDT_NO");
		planVersion = (Number) planMap.get("PLAN_VERSION");
		planRevision = (Number) planMap.get("PLAN_REVISION");
		planAlterations = (Number) planMap.get("PLAN_ALTERATIONS");

		if(queueType.equals("PLG_AUTHORING"))
		{
			planMap = planDaoPW.getPlanInfoForEmail(planId, planVersion, planRevision, planAlterations);

			planNo = (String) planMap.get("PLAN_NO");
			partNo = (String) planMap.get("PART_NO");
			planTitle = (String) planMap.get("PLAN_TITLE");
			chgHist = (String) planMap.get("UCF_PLAN_VCH4000_1"); 
		}

/*		String changeLevelDecode = null;    
		if(changeLevel.equals("M")){changeLevelDecode = "MAJOR_RELEASE";}
		if(changeLevel.equals("N")){changeLevelDecode = "MINOR_RELEASE";}
		if(changeLevel.equals("I")){changeLevelDecode = "INITIAL_RELEASE";}*/

/*		String user = ContextUtil.getUsername();
		String fullName = commonDaoPW.getUserFullName(user);*/

		StringBuffer eSubject = new StringBuffer().append("Plan " + planNo + " " + partNo + " is awaiting approval");
		StringBuffer text = new StringBuffer();

		text.append("Plan " + planNo + " " + partNo + " is ready for review and approval\n")
		
		.append("\n")
		.append("   Plan No: "+ planNo+"\n")
		.append("   Item No: "+ partNo+"\n")
		.append("   Plan Title: "+ planTitle+"\n")
		.append("   Plan Rev: "+ planRevision+"\n\n")
		.append("Chg Level Notes:\n")
		.append("---------------------------\n")
		.append(chgHist);

		String delims = "[;]";
		String[] notificationTokens = notificationMatrix.split(delims);
		
		for (int i = 0; i < notificationTokens.length; i++)
		{	
			userid = notificationTokens[i];

			to = commonDaoPW.getUserEmail(userid);

			if(!to.equals("NONE"))
			{
				mailService.sendEmail(to, eSubject.toString(), text.toString());
			}
		}
	}

	public void sendRejectEmail(String userid, String taskId, String ucfCommunicationVch3, String messageInput, String sentFrom) 
	{	
		StringBuffer text   = new StringBuffer();
		String to           = "";

		String planId       = null;
		Number planRevision = null;
		Number planVersion;
		Number planAlterations;

		String partNo       = null;
		String planNo       = null;
		String planTitle    = null;

		Map planMap  = planDaoPW.getEmailFromTask(taskId);
		planId       = (String) planMap.get("PLAN_ID");
		planVersion  = (Number) planMap.get("PLAN_VERSION");
		planRevision = (Number) planMap.get("PLAN_REVISION");
		planAlterations = (Number) planMap.get("PLAN_ALTERATIONS");

		planMap =  planDaoPW.getPlanInfoForEmail(planId, planVersion, planRevision, planAlterations);

		planNo = (String) planMap.get("PLAN_NO");
		partNo = (String) planMap.get("PART_NO");
		planTitle = (String) planMap.get("PLAN_TITLE");

		to = commonDaoPW.getUserEmail(userid);

		//Rejector ID
		String rejectorID = null;   
		rejectorID = (ContextUtil.getUsername());
		
/*		//Rejector Name
		String fullName = null;
		fullName = commonDaoPW.getUserFullName(rejectorID);

		if (sentFrom.equals("CommunicationImplPW"))
		{
			text.append(fullName + " has rejected your export approval request for Process Plan " + planNo + " Rev "+planRevision+"\n");
			text.append("\n");
			text.append("Reject Reason(s): " + ucfCommunicationVch3+ "\n");
			text.append("\n");
		}
		else
		{
			text.append(fullName + " has rejected Process Plan " + planNo + " Rev "+planRevision+"\n");
			text.append("\n");
		}*/
		
		StringBuffer eSubject = new StringBuffer().append("Plan " + planNo + " " + partNo + " has been returned for revisions");

		text.append("Plan " + planNo + " " + partNo + " has been returned for revisions/modifications\n")

	    .append("\n\n")
		.append("   Plan No: "+ planNo+"\n")
		.append("   Item No: "+ partNo+"\n")
		.append("   Plan Title: "+ planTitle+"\n")
		.append("   Plan Rev: "+ planRevision+"\n")
		.append("   Approver ID: "+ rejectorID+"\n");
		
		if(ucfCommunicationVch3 != null) {
			text.append("   Reject Reason: "+ ucfCommunicationVch3+"\n");
		}
		
		text.append("\n")
		.append("Reject Explanation:\n")
		.append("------------------------------\n")
		.append(messageInput);

		if(!to.equals("NONE")){
			mailService.sendEmail(to, eSubject.toString(), text.toString());
		}
	}
}
