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
*  File:    PlanXClassPW.java
* 
*  Created: 2017-09-27
* 
*  Author:  c079222
* 
*  Revision History
* 
*  Date      	Who             Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-09-27 	D.Miron		    SMO_EXPT_201    Initial Release 
* 2017-11-05    D.Miron         SMRO_APP_203    Updated for email notifications.  Fixed trimBaerClockId substring.
* 2019-07-01    T. Phan         SMRO_EXPT_201   Defect 875. Fixed duplicate xClass error
*/
package com.pw.solumina.sfpl.application;

import java.util.HashMap;
import java.util.Map;

import com.ibaset.common.Reference;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sfpl.dao.IPlanDao;
import com.pw.common.dao.CommonDaoPW;
import com.pw.common.utils.CommonUtilsPW;
import com.pw.common.utils.StringUtil;
import com.pw.solumina.export_control.CallXClassWebServicePW;
import com.pw.solumina.export_control.XClassWebServiceCallResult;
import com.pw.solumina.sffnd.dao.CommunicationDaoPW;
import com.pw.solumina.sfpl.dao.PlanDaoPW;

public class ClassifyPlanPW {
	
	@Reference	
	private CommunicationDaoPW commDaoPW = null;

	@Reference 
	private CommonUtilsPW commonUtils = null;

	@Reference 
	private CommonDaoPW commonDaoPW = null;

	@Reference  
	private IMessage message = null;

	@Reference 
	private PlanDaoPW planDaoPW = null;

	@Reference 
	private CallXClassWebServicePW callXClass = null;


	public void classifyPlan(String planId, 
			Number planVersion, 
			Number planRevision, 
			Number planAlterations, 
			Number planUpdtNo, 
			String notificationMatrix)
	{

		String rtcFlag = "N";	

		if(!planDaoPW.userHasExportBypass(planId, planVersion, planRevision, planAlterations))	
		{				    
			Map rtcMap = requestToClassify(planId, planUpdtNo, notificationMatrix, planRevision);
			Number returnCode = (Number)rtcMap.get("CODE");
			int rtcReturnCode = returnCode.intValue();
			String rtcErrMsg = (String)rtcMap.get("MESSAGE");

			if (rtcReturnCode == 0)
			{
				rtcFlag = "Y";
				message.showMessage("MFI_20","A '“Request to Classify”' has been created in xClass and your local JCL2 has been notified." +
						"  Warning: The request must be completed in xClass before the Plan can be JCL2 approved prior to release.");
			}
			else
			{
				// A non-zero return code means we are possibly sending a work location, plan number and plan rev that eClass already has for an RTC.
				// In that case have to cancel the rec no eClass has and then resend the RTC again with the same work location, plan number and plan rev that failed.
				// eClass will send the rec no that has to be cancelled in the rtc error message body.
				if (rtcErrMsg.contains("Duplicate record"))
				{
					int colonPosition = rtcErrMsg.lastIndexOf(":");
					if (colonPosition == -1)
					{
						message.raiseError("MFI_20","xClass failed to return a record number during Complete Task on plan rev 1.  Contact the Help Desk. ");
					}
					// the rec number will be preceded by ': " (colon followed by a space) so add 2 to get the positioning of the first digit of the rec no
					String recNoToCancel = rtcErrMsg.substring(colonPosition + 2);
					int dupRecNo = Integer.parseInt(recNoToCancel);
					
					//Defect 875. Added BAER ID so duplicate records will always be erased.
					String userId;
					try {
						userId = notificationMatrix.substring(0,notificationMatrix.indexOf(";"));
						userId = userId.trim();
					}
					catch (IndexOutOfBoundsException e) {
						userId = notificationMatrix.trim() ; 
					}
					XClassWebServiceCallResult xClassInfoBean = callXClass.requestToCancel(dupRecNo, userId, "Duplicate Record Encountered In Error: New Request");
					int cancelReturnCode = xClassInfoBean.getReturnCode();

					String cancelErrorMsg = xClassInfoBean.getReturnMsg();

					if (!(cancelReturnCode == 0))
					{
						message.raiseError("MFI_20","Contact the Help Desk. " + cancelErrorMsg);
					}

					// second rtc call goes here
					rtcMap = requestToClassify(planId, planUpdtNo, notificationMatrix, planRevision);
					returnCode = (Number)rtcMap.get("CODE");
					rtcReturnCode = returnCode.intValue();
					rtcErrMsg = (String)rtcMap.get("MESSAGE");
					if (rtcReturnCode == 0)
					{
						rtcFlag = "Y";
						message.showMessage("MFI_20","A '“Request to Classify”' has been created in xClass and your local JCL2 has been notified." +
								"  Warning: The request must be completed in xClass before the Plan can be JCL2 approved prior to release.");
					}
					else
					{
						message.raiseError("MFI_20","Contact the Help Desk. " + rtcErrMsg);
					}
				}
				else
				{
					message.raiseError("MFI_20","Contact the Help Desk. " + rtcErrMsg);
				}
			}
		}
		return;
	}

	public Map requestToClassify(String planId, Number planUpdtNo, String notificationMatrix, Number planRevision)
	{
		String workLoc = null;
		String planNumber = null;
		String itemID = null;
		String nomenclature = null;
		String category = null;
		String type = null;
		String planRev = null;
		String partNo = null;
		String planTitle = null;
		String businessUnit = null;
		String functionalArea = null;

		String trimBaerClockId = null;

		// call xClass to request an RTC be created; return code result other than 0 indicates an RTC already exists, 0 indicates the new RTC was created

		Map planMap = planDaoPW.getPlanInfoForRTC(planId, planUpdtNo);

		if ((planMap == null) || (planMap.isEmpty()))
		{	message.raiseError("MFI_20", "Request Plan not found");
		}

		planNumber     = (String)planMap.get("PLAN_NO");
		partNo         = (String)planMap.get("PART_NO");
		planTitle      = (String)planMap.get("PLAN_TITLE");
		businessUnit   = (String)planMap.get("BUSINESS_UNIT");
		functionalArea = (String)planMap.get("FUNCTIONAL_AREA");	
		workLoc        = (String)planMap.get("WORK_LOC");	

		planRev      = String.valueOf(planRevision);		
		itemID       = workLoc + ',' + planNumber;
		category     = "Technology/Tech Data";
		type         = "Process Plan";

		nomenclature = "Part: " + partNo + "\r\n" +
				"Plan Title: " + planTitle + "\r\n";

		try {
			trimBaerClockId = notificationMatrix.substring(0,notificationMatrix.indexOf(";"));
			trimBaerClockId = trimBaerClockId.trim();
		}
		catch (IndexOutOfBoundsException e) {
			trimBaerClockId = notificationMatrix.trim() ; 
		}

		XClassWebServiceCallResult xClassInfoBean = callXClass.requestToClassify(itemID, 
				category, 
				type, 
				planRev, 
				nomenclature, 
				trimBaerClockId,
				businessUnit,
				functionalArea);
		int rtcReturnCode = xClassInfoBean.getReturnCode();
		String rtcErrorMsg = xClassInfoBean.getReturnMsg();

		Map rtcMap = new HashMap();
		rtcMap.put("CODE", rtcReturnCode);
		rtcMap.put("MESSAGE", rtcErrorMsg);

		return rtcMap;
	}	

	public void updatePlanJCInfo(String planId, 
			Number planVersion, 
			Number planRevision, 
			Number planAlterations,
			Number planUpdtNo)
	{
		if(!planDaoPW.userHasExportBypass(planId, planVersion, planRevision, planAlterations))	
		{
			Map exptCommMap1 = commDaoPW.getExptComm(planId, planVersion, planRevision, planAlterations);
			if (!((exptCommMap1 == null) || (exptCommMap1.isEmpty())))
			{
				message.raiseError("MFI_20", "An open Export Approval Communication has been detected. Please resolve this Communication before completing the Plan.");
			}

			String rtcFlagComm;

			Map planMap = planDaoPW.getPlanInfoForRTC(planId, planUpdtNo);
			rtcFlagComm = commDaoPW.getRTCFlagForPlanExptTask(planId, planUpdtNo); 

			if ((planMap == null) || (planMap.isEmpty()))
			{	message.raiseError("MFI_20", "Request Plan not found during Release");
			}

			if (rtcFlagComm.equals("Y"))  // regardless of plan rev   
			{
				updatePlanHeader(planId, planUpdtNo);
			}
			else
			{
				fastPath(planId, planUpdtNo, planRevision, planMap);
			}
		}
	}

	private void updatePlanHeader(String planId, Number planUpdtNo)
	{
		// get the JC information from the comm record

		Map commMap = commDaoPW.getInfoForPlanExptTask(planId, planUpdtNo);  

		// in this case the UEA check is not looking for RTC flag of Y, we are already on an RTC flag of Y
		/* Removing UEA check in release process per Glenn on 05-20-13
			boolean ueaExists = commDaoPW.openUEAExists(communicationId, "N");
			if (ueaExists)
			{
				ibtMessage.raiseError("MFI_20","Error: This plan has a new J&C record in eClass (for this revision) which is complete." +
		       			"  The Plan/Operation that started this new J&C has not been released." +
		       			"  This must be resolved before this Plan/Operation can be released. Contact the help desk and reference this error message. ");
			}
		 */

		Number sme = (Number)commMap.get("UCF_COMM_NUM2");
		int intSME = sme.intValue();
		
		String smeStr = null;
		if(intSME == 1 ) 
		{
			smeStr = "Y";
		}
		
		Number recNo = (Number)commMap.get("UCF_COMM_NUM3");
		int intRecNo = recNo.intValue();

		String jurisdiction  = (String) commMap.get("UCF_COMM_VCH8");
		String classification  = (String) commMap.get("UCF_COMM_VCH9");

		// update the JC information into the plan header (SFPL_PLAN_DESC)
		planDaoPW.updateJCInfo(planId, planUpdtNo, jurisdiction,
				classification, smeStr, intRecNo);
	}

	private void fastPath(String planId, Number planUpdtNo, Number planRevision, Map planMap)
	{	
		String partNo;
		String planTitle;
		String mfgBomChg;
		String approverId = null;
		String nomenclature = null;

		// plan rev is greater than 1 since RTC Flag is "N"   
		// get the JC information from the comm record
		Map commMap = commDaoPW.getInfoForPlanExptTask(planId, planUpdtNo);

		// first check for any UEAs with RTC 'Y'
		String communicationId = (String) commMap.get("COMM_ID");

		Number recNo   = (Number)commMap.get("UCF_COMM_NUM3");
		int intRecNo   = recNo.intValue();
		approverId     = (String)commMap.get("UCF_COMM_VCH1");
		String certTag = (String) commMap.get("UCF_COMM_VCH10");

		String revisionReason = (String) planMap.get("UCF_PLAN_VCH4000_2");

		String planRevFastPath = String.valueOf(planRevision);

		// Get all the fields required to format nomenclature for the fastpath request 
		partNo    = (String)planMap.get("PART_NO");
		planTitle = (String)planMap.get("PLAN_TITLE");

		nomenclature = "Part: " + partNo + "\r\n" +
				"Plan Title: " + planTitle + "\r\n";

		XClassWebServiceCallResult xClassInfoBeanFP = 
				callXClass.requestFastPath(intRecNo, "", "", "", planRevFastPath, nomenclature, "", approverId, "", revisionReason, certTag);

		int fpReturnCode = xClassInfoBeanFP.getReturnCode();
		// rec no is returned in the fastpath return message when return code is 0
		String fastPathRecNo = xClassInfoBeanFP.getReturnMsg();
		if (fpReturnCode == 0)
		{
			intRecNo = Integer.parseInt(fastPathRecNo);
			planDaoPW.updateRecNoFromFastPath(planId, planUpdtNo, intRecNo);
			commDaoPW.updateRecNoFromFastPath(communicationId, intRecNo);
		}
		else
		{
			// Begin SEPL_SUM_08_I-002
			// Return message that contains "Duplicate Revision" means we are sending a record number and rev that xClass already has.
			// In that case have to cancel the rec no xClass has and then resend the fastPath again with the same rec no that failed
			// xClass will send the rec no that has to be cancelled in the message body that we placed into fastPathRecNo
			if (fastPathRecNo.contains("Duplicate revision"))	
			{
				int colonPosition = fastPathRecNo.lastIndexOf(":");
				if (colonPosition == -1)
				{
					message.raiseError("MFI_20","xClass failed to return a record number on plan/oper release.  Contact the Help Desk. ");
				}
				// the rec number will be preceded by ': " (colon followed by a space) so add 2 to get the positioning of the first digit of the rec no
				String recNoToCancel = fastPathRecNo.substring(colonPosition + 2);
				int dupRecNo = Integer.parseInt(recNoToCancel);
				String userId = ContextUtil.getUsername();
				xClassInfoBeanFP = callXClass.requestToCancel(dupRecNo, userId, "Duplicate Record Encountered In Error: Fastpath");
				int cancelReturnCode = xClassInfoBeanFP.getReturnCode();

				String cancelErrorMsg = xClassInfoBeanFP.getReturnMsg();

				if (!(cancelReturnCode == 0))
				{
					message.raiseError("MFI_20","xClass Error: Contact the Help Desk. " + cancelErrorMsg);
				}

				// invoke second fastpath here
				xClassInfoBeanFP = 
						callXClass.requestFastPath(intRecNo, "", "", "", planRevFastPath, nomenclature, "", approverId, "", revisionReason, certTag);
				fpReturnCode = xClassInfoBeanFP.getReturnCode();
				// rec no is returned in the fastpath return message when return code is 0
				fastPathRecNo = xClassInfoBeanFP.getReturnMsg();
				if (fpReturnCode == 0)
				{
					intRecNo = Integer.parseInt(fastPathRecNo);
					planDaoPW.updateRecNoFromFastPath(planId, planUpdtNo, intRecNo);
					commDaoPW.updateRecNoFromFastPath(communicationId, intRecNo);
				}
				else
				{
					// fastPathRecNo contains the error message when an error condition occurred
					message.raiseError("MFI_20","xClass Error: Contact the Help Desk. " + fastPathRecNo);
				}

			}
			else
			{
				// End SEPL_SUM_08_I-002	
				// fastPathRecNo contains the error message when an error condition occurred
				message.raiseError("MFI_20","xClass Error: Contact the Help Desk. " + fastPathRecNo);
			}	
		}
	}
}
