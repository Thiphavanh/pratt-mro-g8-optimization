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
*  File:    CommunicationPW.java
* 
*  Created: 2017-09-27
* 
*  Author:  c079222
* 
*  Revision History
* 
*  Date      	Who             Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-09-27 	c079222		    SMRO_EXPT_201 - Initial Release 
*/
package com.pw.solumina.sffnd.application;

import java.util.ListIterator;
import java.util.Map;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.application.ICommunication;
import com.pw.solumina.export_control.CallXClassWebServicePW;
import com.pw.solumina.export_control.XClassItemInfoBean;
import com.pw.solumina.export_control.XClassWebServiceCallResult;
import com.pw.solumina.sffnd.dao.CommunicationDaoPW;
import com.pw.solumina.sfpl.dao.PlanDaoPW;
import com.pw.solumina.sfpl.application.ClassifyPlanPW;
import com.pw.solumina.sfpl.application.PlanPW;
import java.util.List;

public class CommunicationPW {
	
	@Reference
	private IEvent event = null;
	@Reference
	private IMessage message = null;
	@Reference
	private ICommunication communication = null;
	
	@Reference
	private CommunicationDaoPW communicationDaoPW = null;
	@Reference
	private CallXClassWebServicePW callXClass = null;
	@Reference
	private PlanDaoPW planDaoPW = null;
	@Reference
	private PlanPW planPW = null;
	@Reference 
	private ClassifyPlanPW classifyPlanPW = null;

	private String ucfPlanVch255_2;
	private String planID;
	private Number planUpdtNo;
	private String workLoc;
	private String planNumber;
	private String itemID;
	private String category;
	private String type;	
	private String rtcFlag;
	private String jurisdiction;
	private String classification;
	private String sme = "N";
	private String stringSME;
	private String stringRecNum;
	private int recordNumber;
	private int planRevision;
	private String planRev;
	
	public void approve(String communicationId, String userId)
	{
		rtcFlag = communicationDaoPW.getRTCFlag(communicationId);
		String toQueue = communicationDaoPW.getToQueue(communicationId);
		if(toQueue.contains("EXPT"))
		{
			// Obtain plan info required for the call to xClass
			getPlanInformation(communicationId, userId);
			
			XClassWebServiceCallResult xClassInfoBean = callXClass.getJurisdictionClassification(itemID, category, type, planRev, "", "");
			
			int rtcReturnCode = xClassInfoBean.getReturnCode();
						
			String rtcErrorMsg = xClassInfoBean.getReturnMsg();
						
			if (!(rtcReturnCode == 0))
			{
				message.raiseError("MFI_20","Contact the Help Desk. " + rtcErrorMsg);
			}
			
			// A return code of 0 is returned even if the JC fields have not been set
			// Obtain the JC fields from the bean; if they are empty then a JC has not yet been created
            List jcBeanList = xClassInfoBean.getItemInfoBean();
			if (!(jcBeanList.isEmpty()))
	    	{
	    		ListIterator jcBeanItr = jcBeanList.listIterator();
	    									    		
	    		
	    		while (jcBeanItr.hasNext())  // only expecting one List result set back
	    		{
	    			XClassItemInfoBean row = (XClassItemInfoBean)jcBeanItr.next();
	    			jurisdiction = row.getJurisdiction();
	    			classification = row.getClassification();
	    			stringSME = row.getIsSme();
	    			stringRecNum = row.getRecnumber();
	    		    			
	    		}
	    	}
			
			// if the JC data is not in the returned bean, there is no JC, there is an open RTC; just check jurisdiction
			if (jurisdiction.equals(""))
			{
				message.raiseError("MFI_20","Warning: there is an open '“Request to Classify”' in xClass for this Plan. This must" +
	        			"  be completed before the Plan can be Export approved. If the" +
	        			"  approval is Rejected or Reject button is clicked to take the Plan back to Authoring," +
	        			"  the record in xClass will be cancelled.");
			}
						    	
			if(stringSME.equals("TRUE"))
			{
			    sme = "Y"; 
			}
			else
			{
				sme = "N"; 
			}
			
			recordNumber = Integer.parseInt(stringRecNum);
			
			// Put classification in single quotes in case any part of it contains a paren, which gets lost when passed to a udv otherwise
			classification = "\'" + classification + "\'";
			
			String rtcFlag = communicationDaoPW.getRTCFlag(communicationId);
			
			StringBuffer params = new StringBuffer(500);
	        params.append("COMM_ID=" +  communicationId )
	           .append(",USER_ID=" +  userId )
	           .append(",PLAN_ID=" +  planID )
	           .append(",PLAN_NO=" +  planNumber )
	           .append(",PLAN_UPDT_NO=" +  planUpdtNo )
	           .append(",JURISD="  +  jurisdiction )
	           .append(",CLASSIF=" +  classification )
	           .append(",SME="     +  sme )
	           .append(",REC_NO="  +  recordNumber )
	           .append(",RTC="  +  rtcFlag );
	        
			event.showUdv("PWUST_5AB1AB68AEF9C98FE05387971F0ABAD8",    // script udv with atcapp value
					SoluminaConstants.INSERT,
	                "Warning",
	                null,
	                params.toString());
		} 
		else // toQueue does not contain 'EXPT', just run the normal approval process bypassing any EXPT logic
		{
			//CommunicationImpl commImpl = new CommunicationImpl();
			communication.preAcknowledgeCommunication(communicationId, userId);	
		}
	}
	
	public void getPlanInformation(String communicationId, String userId)
	{
		// Come up with the dao calls necessary to obtain the planID and planUpdtNo from the communication id
		Map planMap1 = communicationDaoPW.getPlanInfoForCommunicationId(communicationId);  
		 
		if ((planMap1 == null) || (planMap1.isEmpty()))
		{	message.raiseError("MFI_20", "Request Plan not found for Communciation Id during Approval");
		}
		
		planID = (String)planMap1.get("PLAN_ID");
		planUpdtNo = (Number)planMap1.get("PLAN_UPDT_NO");
		
		Map map = planDaoPW.getPlanInfoForUpdateNo(planID, planUpdtNo);
		String itemSubtype = (String) map.get("ITEM_SUBTYPE");
				
		planRevision = planDaoPW.selectPlanRevision(planID, planUpdtNo);
				
		if (!(rtcFlag.equals("Y")))
		{
			// set plan revision down by 1 to send to xClass as need to match what xClass has at this point
			planRevision--;
					
		}
					
		planRev = String.valueOf(planRevision);
		workLoc = planDaoPW.getPlantForPlan(planID, planUpdtNo);
		Map planMap2 = planDaoPW.getPlanInfoForRTC(planID, planUpdtNo);
		if ((planMap2 == null) || (planMap2.isEmpty()))
		{	message.raiseError("MFI_20", "Request Plan not found for Plan Id and Plan Updt No during Approval");
		}
		planNumber = (String)planMap2.get("PLAN_NO");
		itemID = workLoc + ',' + planNumber;
		category = "Technology/Tech Data";
		type = "Process Plan";
								
	}
	
	public void getPlanInformation()
	{
		workLoc = planDaoPW.getPlantForPlan(planID, planUpdtNo);
		Map planMap2 = planDaoPW.getPlanInfoForRTC(planID, planUpdtNo);
		if ((planMap2 == null) || (planMap2.isEmpty()))
		{	message.raiseError("MFI_20", "Request Plan not found for Plan Id and Plan Updt No during Approval");
		}
		planNumber = (String)planMap2.get("PLAN_NO");
		itemID = workLoc + ',' + planNumber;
		category = "Technology/Tech Data";
		type = "Process Plan";
								
	}
	
	public void setJCInfo(String communicationId, String userId, String jurisdiction, String classification,
			String sme, int recordNo)
	{
		
		int intSME = 0;
		
		if (!(sme == null))
		{
			if(sme.equals("Y"))
			{
				intSME = 1;
			}
		}
		
		// leave intSME = 0 when sme is null
		communicationDaoPW.updateJCInfo(communicationId, jurisdiction, classification, intSME, recordNo, "EXPT_ST_1");
			
	}
	

	public void replaceRTC(String communicationId, String userId)
	{
		// reset plan id information to be sure we are dealing with the correct plan after coming thru the warning udvs		
		getPlanInformation(communicationId, userId);
		planRevision = planDaoPW.selectPlanRevision(planID,  planUpdtNo);
		
		// Begin SEPL_SUM_08 I - SCR-001
		// If the Export Approver rejected the JC during the approval of Plan Revision 1, the pending JC in xClass must be cancelled
		// before a new RTC is created
		if (planRevision == 1)  // if rejecting the JC when approving an Operation, the plan could be complete or released at Rev 1
		{
			cancelPlanRev1();
		}
		// End SEPL_SUM_08 I - SCR-001
						
		// call xClass to replace the existing RTC with this new one
		
		Map rtcMap = classifyPlanPW.requestToClassify(planID, planUpdtNo, userId, planRevision);  // Defect #1182, requestToClassify requires a BAER id
				
		Number returnCode = (Number)rtcMap.get("CODE");
		int rtcReturnCode = returnCode.intValue();
		String rtcErrorMsg = (String) rtcMap.get("MESSAGE");
						
		if (rtcReturnCode == 0)
		{
			communicationDaoPW.updateCommRTCFlag(communicationId, "Y");
			
			message.showMessage("MFI_20","A '“Request to Classify”' has been created in xClass and your local JCL2 has been notified." +
        			"  Warning: The request must be completed in xClass before the Plan can be JCL2 approved prior to release.");
		}
		else
		{			
			message.raiseError("MFI_20","Contact the Help Desk. " + rtcErrorMsg);
		}			
	}
		
	public void cancelEntry(String planId, Number planVersion, Number planRev, Number planAlteration, String messageInput, String toQueueType, Number planUpdtNumber)
	{
		String exptCommID;
		String exptRTCFlag;
		
		planID = planId;
		planUpdtNo = planUpdtNumber;
		
		if (planRev.intValue() == 1)
		{
			// assume rtc flag is Y, no need to check for its value on comm record when queue is not EXPT_ACCEPTANCE
						
			if ((toQueueType.equals("EXPT_ACCEPTANCE")))
			{
				// At plan rev 1 we need to change the rtc flag to C when at EXPT_ACCEPTANCE
								
				Map exptCommMap1 = communicationDaoPW.getExptComm(planId, planVersion, planRev, planAlteration);
				
				if (!((exptCommMap1 == null) || (exptCommMap1.isEmpty())))
				{
					// we have an EXPT communication IN PROCESS
					exptCommID = (String)exptCommMap1.get("COMM_ID");
													
					cancel(exptCommID, messageInput);
					communicationDaoPW.updateCommRTCFlag(exptCommID, "C");
				}
			}
			else
			{
				cancelWhenNoCommRecord(messageInput);
			}	
		}
		else
		{
			// plan rev is greater than 1
			// only run the cancel if we are at the EXPT_ACCEPTANCE queue
			if ((toQueueType.equals("EXPT_ACCEPTANCE")))
			{
				// First use the plan info to see if an EXPT COMM in IN PROCESS status exists;
				// if so will use that to send xClass the Cancel but only if rtc flag is Y
				Map exptCommMap1 = communicationDaoPW.getExptComm(planId, planVersion, planRev, planAlteration);
				
				if (!((exptCommMap1 == null) || (exptCommMap1.isEmpty())))
				{
					// we have an EXPT communication IN PROCESS
					exptCommID = (String)exptCommMap1.get("COMM_ID");
					exptRTCFlag = (String)exptCommMap1.get("UCF_COMM_FLAG3");
								
					if (exptRTCFlag.equals("Y"))
					{
						cancel(exptCommID, messageInput);													
				    }
					
					// Whatever the value of the rtc flag, it must be set to 'C' when Solumina returns back to PLG_AUTHORING, which occurs whether or not
					// there is a record to cancel in xClass.
					communicationDaoPW.updateCommRTCFlag(exptCommID, "C");
				}
				else
				{
					// we DO NOT have an EXPT communication IN PROCESS, so get the one COMPLETE communication that has not been cancelled  
											
					Map completedCommMap = communicationDaoPW.getExptCompletedComm(planId, planVersion, planRev, planAlteration);
					if (!((completedCommMap == null) || (completedCommMap.isEmpty())))
					{
						// we have an EXPT communication that is COMPLETE 
						// Check what the RTC flag value is; only run the cancel if it is Y
						exptRTCFlag = (String)completedCommMap.get("UCF_COMM_FLAG3");
						exptCommID = (String)completedCommMap.get("COMM_ID");
										
						if (exptRTCFlag.equals("Y"))
						{
							cancel(exptCommID, messageInput);
						}
						
						// Whatever the value of the rtc flag, it must be set to 'C' when Solumina returns back to PLG_AUTHORING, which occurs whether or not
						// there is a record to cancel in xClass.
						communicationDaoPW.updateCommRTCFlag(exptCommID, "C");  
					}					
				}  // end else
				
				//update ucf_comm_flag5 to 'C' on all expt comms on a cancel event
				communicationDaoPW.updateExptCompletedCommsOnCancel(planId, planVersion, planRevision, planAlteration);
			}		
		}	
	}
	
	public void cancel(String communicationId, String messageInput)
	{
		XClassWebServiceCallResult xClassInfoBean;
		
		String commStatus;
		int intRecNo = 0;
		int rtcReturnCode = 0;
		String rtcErrorMsg;
		int cancelReturnCode = 0;
		String cancelErrorMsg;
		// Need to set this flag so getPlanInformation has what it needs to determine the plan rev to send to xClass
		rtcFlag = "Y";
		
		// logic for sending the cancel to xclass goes here
		String userId = ContextUtil.getUsername();
		Map commMap = communicationDaoPW.getCommStatus(communicationId);
		if ((commMap == null) || (commMap.isEmpty()))
		{	message.raiseError("MFI_20", "Communication record not found while rejecting from within Approvals");
		}
		
		commStatus = (String)commMap.get("COMM_STATUS");
		
		if (commStatus.equals("COMPLETE"))
		{
			Number recNo = (Number)commMap.get("UCF_COMM_NUM3");
			intRecNo = recNo.intValue();
			xClassInfoBean = callXClass.requestToCancel(intRecNo, userId, messageInput);
			cancelReturnCode = xClassInfoBean.getReturnCode();
			cancelErrorMsg = xClassInfoBean.getReturnMsg();
			
			if (!(cancelReturnCode == 0))
			{
				message.raiseError("MFI_20","Contact the Help Desk. " + cancelErrorMsg);
			}
		}
		else
		{
			// will have to get JC info from xClass to provide the rec no
			getPlanInformation(communicationId, userId);
			// do not pass nomenclature and baer clock id
			xClassInfoBean = callXClass.getJurisdictionClassification(itemID, category, type, planRev, "", "");
			
			rtcReturnCode = xClassInfoBean.getReturnCode();
			
			rtcErrorMsg = xClassInfoBean.getReturnMsg();
			
			if (!(rtcReturnCode == 0))
			{
				message.raiseError("MFI_20","Contact the Help Desk. " + rtcErrorMsg);
			}
					
			// A return code of 0 is returned even if the JC fields have not been set
			// Obtain the JC fields from the bean; if they are empty then a JC has not yet been created
	        List jcBeanList = xClassInfoBean.getItemInfoBean();
			if (!(jcBeanList.isEmpty()))
	    	{
	    		ListIterator jcBeanItr = jcBeanList.listIterator();
	    									    		    		
	    		while (jcBeanItr.hasNext())  // only expecting one List result set back
	    		{
	    			XClassItemInfoBean row = (XClassItemInfoBean)jcBeanItr.next();
	    			stringRecNum = row.getRecnumber();
	    		}    			    		
	    	}
			
			// if the JC data is not in the returned bean, there is no JC; just check rec num
			if (stringRecNum.equals(""))
			{
				// do not call xClass cancel, just fall thru to the rest of the reject processing
			}
			else
			{	
				intRecNo = Integer.parseInt(stringRecNum);
				xClassInfoBean = callXClass.requestToCancel(intRecNo, userId, messageInput);
				cancelReturnCode = xClassInfoBean.getReturnCode();
				
				cancelErrorMsg = xClassInfoBean.getReturnMsg();
				
				if (!(cancelReturnCode == 0))
				{
					message.raiseError("MFI_20","Contact the Help Desk. " + cancelErrorMsg);
				}				
			}						
		}	
	}  // end cancel method
	
	
	public void cancelWhenNoCommRecord(String messageInput)
	{
		XClassWebServiceCallResult xClassInfoBean;
		
		int intRecNo = 0;
		int rtcReturnCode = 0;
		String rtcErrorMsg;
		int cancelReturnCode = 0;
		String cancelErrorMsg;
		
		String userId = ContextUtil.getUsername();
		
		// Need to set this flag so getPlanInformation has what it needs to determine the plan rev to send to xClass
		rtcFlag = "Y";
		
		// will have to get JC info from xClass to provide the rec no
		getPlanInformation();
		
		// We have to be at plan rev 1 to be in this method
		xClassInfoBean = callXClass.getJurisdictionClassification(itemID, category, type, "1" /*planRev*/, "", "");
		
		rtcReturnCode = xClassInfoBean.getReturnCode();
		
		rtcErrorMsg = xClassInfoBean.getReturnMsg();
		
		if (!(rtcReturnCode == 0))
		{
			message.raiseError("MFI_20","Contact the Help Desk. " + rtcErrorMsg);
		}
				
		// A return code of 0 is returned even if the JC fields have not been set
		// Obtain the JC fields from the bean; if they are empty then a JC has not yet been created
        List jcBeanList = xClassInfoBean.getItemInfoBean();
		if (!(jcBeanList.isEmpty()))
    	{
    		ListIterator jcBeanItr = jcBeanList.listIterator();
    									    		
    		while (jcBeanItr.hasNext())  // only expecting one List result set back
    		{
    			XClassItemInfoBean row = (XClassItemInfoBean)jcBeanItr.next();
    			stringRecNum = row.getRecnumber();		
    		} 		
    	}
		
		// if the JC data is not in the returned bean, there is no JC; just check rec num
		if (stringRecNum.equals(""))
		{
			// do not call xClass cancel, just fall thru to the rest of the reject processing
		}
		else
		{
			intRecNo = Integer.parseInt(stringRecNum);
			xClassInfoBean = callXClass.requestToCancel(intRecNo, userId, messageInput);
			cancelReturnCode = xClassInfoBean.getReturnCode();
			
			cancelErrorMsg = xClassInfoBean.getReturnMsg();
			
			if (!(cancelReturnCode == 0))
			{
				message.raiseError("MFI_20","Contact the Help Desk. " + cancelErrorMsg);
			}				
		}	
	}
	
	public void cancelPlanRev1()
	{
		XClassWebServiceCallResult xClassInfoBean;
		int rtcReturnCode = 0;
		String rtcErrorMsg;
		int intRecNo = 0;
		int cancelReturnCode = 0;
		String cancelErrorMsg;
		String userId = ContextUtil.getUsername();
		
		xClassInfoBean = callXClass.getJurisdictionClassification(itemID, category, type, planRev, "", "");
		
		rtcReturnCode = xClassInfoBean.getReturnCode();
		
		rtcErrorMsg = xClassInfoBean.getReturnMsg();
		
		if (!(rtcReturnCode == 0))
		{
			message.raiseError("MFI_20","Contact the Help Desk. " + rtcErrorMsg);
		}
				
		// A return code of 0 is returned even if the JC fields have not been set
		// Obtain the JC fields from the bean; if they are empty then a JC has not yet been created
        List jcBeanList = xClassInfoBean.getItemInfoBean();
		if (!(jcBeanList.isEmpty()))
    	{
    		ListIterator jcBeanItr = jcBeanList.listIterator();							    		
    		
    		while (jcBeanItr.hasNext())  // only expecting one List result set back
    		{
    			XClassItemInfoBean row = (XClassItemInfoBean)jcBeanItr.next();
    			stringRecNum = row.getRecnumber();   			
    		}	    		
    	}
		
		// if the JC data is not in the returned bean, there is no JC; just check rec num
		if (stringRecNum.equals(""))
		{
			// do not call xClass cancel, just fall thru to the rest of the reject processing
		}
		else
		{
			intRecNo = Integer.parseInt(stringRecNum);
			xClassInfoBean = callXClass.requestToCancel(intRecNo, userId, "J&C rejected by Export Approver");
			cancelReturnCode = xClassInfoBean.getReturnCode();
			
			cancelErrorMsg = xClassInfoBean.getReturnMsg();
			
			if (!(cancelReturnCode == 0))
			{
				message.raiseError("MFI_20","Contact the Help Desk. " + cancelErrorMsg);
			}		
		}
					
	}  // end cancelPlanRev1 method
	// End SEPL_SUM_08 I - SCR-001
}  // end class

