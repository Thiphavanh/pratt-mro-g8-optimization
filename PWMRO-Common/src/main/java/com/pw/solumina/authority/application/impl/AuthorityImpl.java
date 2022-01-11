/*
 *  This unpublished work, first created in 2019 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2019-2021.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    AuthorityImpl.java
 * 
 *  Created: April 2019
 * 
 *  Author:  Marcel LeClerc (iBaset)
 * 
 *  Revision History
 * 
 *  Date        Who          	Description
 *  ----------	--------------	-----------------------------------------------------------
 *  04-02-2019  D.Miron         Initial Release PWU-116 Task Authority
 *  03-18-2020  D.Sibrian		PWU-116F Update
 *  03-19-2020  S.Edgerly		SMRO_CV_301 - Defect 1383; Engine Manuals and Subject Authorities functionality added
 *  04-14-2021  J DeNinno       Defect 1890 - Add sequence number for primary key
*/
package com.pw.solumina.authority.application.impl;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.OutputParameter;
import com.ibaset.common.Reference;
import com.ibaset.common.util.OutputParameterConverter;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sfor.application.ISfplPlan;
import com.ibaset.solumina.sfpl.application.IOrder;
import com.ibaset.solumina.sfpl.application.IPlan;
import com.ibaset.solumina.sfwid.application.ICall;
import com.pw.solumina.authority.application.IAuthority;
import com.pw.solumina.authority.application.dao.IAuthorityDao;


public class AuthorityImpl implements IAuthority {
	
	@Reference IAuthorityDao authorityDao;
	@Reference IMessage message;
	@Reference IEvent event;
	@Reference ISfplPlan sfplPlan;
	@Reference IPlan plan;
	@Reference ICall oobCall;
	
	public void insertManualProvider(String manualProvider)
	{
		if (authorityDao.selectProviderCount(manualProvider) > 0 )				
			{message.raiseError("MFI_50400","Manual Provider already Exists ");}
		else
		{
			authorityDao.insertProvider(manualProvider);
		}

	}
	
	public void updateManualProvider(String manualProvider, String obsoleteRecordFlag)
	{
			authorityDao.updateProvider(manualProvider,obsoleteRecordFlag);
			int index = -1,i;
	}
	
	public void deleteManualProvider(String manualProvider)
	{
		if (authorityDao.selectProviderUsedCount(manualProvider) > 0 )				
			{message.showMessage("MFI_50400"," Provider used in a Manual, Marked Record Obsolete Instead ");
			authorityDao.updateProvider(manualProvider,"Y");
			}
		else
		{
			authorityDao.deleteProvider(manualProvider);
		}
	}
	
	public void insertManualType(String manualType)
	{
		if (authorityDao.selectManualTypeCount(manualType) > 0 )				
			{message.raiseError("MFI_50400","Manual Type already Exists ");}
		else
		{
			authorityDao.insertManualType(manualType);
		}
	}
	
	
	public void updateManualType(String manualType, String obsoleteRecordFlag)
	{
			authorityDao.updateManualType(manualType,obsoleteRecordFlag);
	}


	public void deleteManual(String manualNo, String manualRev)
	{
		
	
		if (authorityDao.selectManualUsedCount(manualNo, manualRev) > 0 )				
			{message.showMessage("MFI_50400"," Manual used in a Plan, Marked Record Obsolete Instead ");
			authorityDao.updateManual(manualNo,manualRev,"Y");
			}
		else
		{
			authorityDao.deleteManual(manualNo, manualRev);
		}
		}
	
	public void deleteManualType(String manualType)
	{
		
	
		if (authorityDao.selectManualTypeUsedCount(manualType) > 0 )				
			{message.showMessage("MFI_50400"," Manual Type used in a Manual, Marked Record Obsolete Instead ");
			authorityDao.updateManualType(manualType,"Y");
			}
		else
		{
			authorityDao.deleteManualType(manualType);
		}
		}
	public void deleteAuthorityType(String authorityType)
	{
		if (authorityDao.selectAuthorityTypeUsedCount(authorityType) > 0 )				
			{message.showMessage("MFI_50400"," Authority Type used in a Manual, Marked Record Obsolete Instead ");
			authorityDao.updateAuthorityType(authorityType,"Y");
			}
		else
		{
			authorityDao.deleteAuthorityType(authorityType);
		}
	}	
	public void updateAuthorityType(String authorityType, String authRevRule, String authDateRule, String sbPartNo, String obsoleteRecordFlag)
	{
		authorityDao.updateAuthorityType(authorityType, authRevRule, authDateRule, sbPartNo, obsoleteRecordFlag);
	}
	
	public void insertAuthorityType(String authorityType, String authRevRule, String authDateRule, String sbPartNo, String obsoleteRecordFlag)
	{
		authorityDao.insertAuthorityType(authorityType, authRevRule, authDateRule, sbPartNo, obsoleteRecordFlag);
	}
	public void insertManual(String manualNo, String manualRev, String manualTransmittalNo,String manualRemarks, 
						     String manualProvider, String manualTitle, Date manualReleaseDate, String manualType,
						     String engineSeries,String obsoleteRecordFlag)
	{
				authorityDao.insertManual(manualNo, manualRev, manualTransmittalNo, manualRemarks, manualProvider, manualTitle, manualReleaseDate, manualType, engineSeries,"N");
	}
	public void updateManual(String manualNo, String manualRev, String manualTransmittalNo,String manualRemarks, 
		     String manualProvider, String manualTitle, Date manualReleaseDate, String manualType,
		     String engineSeries,String obsoleteRecordFlag)
{
		authorityDao.updateManual(manualNo, manualRev, manualTransmittalNo, manualRemarks, manualProvider, manualTitle, manualReleaseDate, manualType, engineSeries,"N");
}
	public void insertPlanTaskAuth(String planId,Number planRevision, String subjectNo, String subjectRev, String subjectDesc, String authorityType,
			                      String authorityDetail, String manualNo, String manualRev, String manualProvider, 
			                      String manualType, String manualSection, String sbPartNo, String authorityRev,
			                      Date authorityRevDate)
	{
				
		Map authMap = authorityDao.selectAuthReq(authorityType);
		String authRevRule = (String) authMap.get("AUTHORITY_REV_RULE");
		String authDateRule = (String) authMap.get("AUTHORITY_DATE_RULE");
		String sbPartRule   = (String) authMap.get("SB_PART_NO_RULE");
		
		verifyAuthorityTypeFields(authRevRule,  authorityRev,  authDateRule, 
				                   authorityRevDate,  sbPartRule,  sbPartNo);
		try {
		authorityDao.insertPlanTaskAuth( planId, planRevision,  subjectNo,  subjectRev, subjectDesc, authorityType,
                 authorityDetail,  manualNo,  manualRev,  manualProvider, 
                 manualType,  manualSection,  sbPartNo,  authorityRev,
                 authorityRevDate);
		}
		catch (Exception e)
		{
			message.raiseError("MFI_50400", "Cannot Duplicate Manual in a Task Group");
		}
	}	
		public void updatePlanTaskAuth(String planId,Number planRevision, String subjectNo, String subjectRev, String authorityType,
                String authorityDetail, String manualNo, String manualRev, String manualProvider, 
                String manualType, String manualSection, String sbPartNo, String authorityRev,
                Date authorityRevDate, Number seqNo)	 //defect 1890
{ 
			Map authMap = authorityDao.selectAuthReq(authorityType);
			String authRevRule = (String) authMap.get("AUTHORITY_REV_RULE");
			String authDateRule = (String) authMap.get("AUTHORITY_DATE_RULE");
			String sbPartRule   = (String) authMap.get("SB_PART_NO_RULE");
			verifyAuthorityTypeFields(authRevRule,  authorityRev,  authDateRule, 
					                   authorityRevDate,  sbPartRule,  sbPartNo);
			authorityDao.updatePlanTaskAuth( planId, planRevision,  subjectNo,  subjectRev,  authorityType,
			authorityDetail,  manualNo,  manualRev,  manualProvider, 
			manualType,  manualSection,  sbPartNo,  authorityRev,
			authorityRevDate,seqNo); //defect 1890

	}

		public void verifyAuthorityTypeFields(String authRevRule, String authorityRev, String authDateRule, 
											  Date authorityRevDate, String sbPartRule, String sbPartNo)
		{
			
		   String errMessage = " ";
		  
		   authorityRev = (authorityRev  == null ? "" : authorityRev);
		   if (StringUtils.equals(authRevRule, "REQD")	 && authorityRev.isEmpty() )
		   {
			   errMessage = errMessage + "Authority Rev Required ";
		   }
		   if (StringUtils.equals(authRevRule, "N/A")	 && (authorityRev != null && !authorityRev.isEmpty() &&
				   !StringUtils.equals("", authorityRev)))
		   {
				errMessage = errMessage + "Authority Rev must be Empty";
		   }
		   if (StringUtils.equals(authDateRule, "REQD")	 && authorityRevDate == null )
		   {
			   errMessage = errMessage + "Authority Rev Date Required ";
		   }
		   if (StringUtils.equals(authDateRule, "N/A")	 && authorityRevDate != null )
		   {
				errMessage = errMessage + "Authority Rev Date must be Empty";
		   }
		   sbPartNo = (sbPartNo  == null ? "" : sbPartNo);
		   if (StringUtils.equals(sbPartRule, "REQD")	 && sbPartNo.isEmpty() )
		   {
			   errMessage = errMessage + "SB Part No Required ";
		   }
		   if (StringUtils.equals(sbPartRule, "N/A")	 && !sbPartNo.isEmpty() )
		   {
				errMessage = errMessage + "SB Part No must be Empty";
		   }
		   if (!StringUtils.equals(errMessage, " "))
		   {
			   message.raiseError("MFI_50400",errMessage);
		   }
		}	
		public void deletePlanTaskAuth(String planId,Number planRevision, String subjectNo, String subjectRev, String manualNo,Number seqNo) //defect 1890
		{
			authorityDao.deletePlanTaskAuth(planId, planRevision, subjectNo, subjectRev, manualNo, seqNo); //defect 1890
		}

		public void insertOrderTaskAuth(String orderId, String subjectNo, String subjectRev, String authorityType,
                String authorityDetail, String manualNo, String manualRev, String manualProvider, 
                String manualType, String manualSection, String sbPartNo, String authorityRev,
                Date authorityRevDate,String authority, String arcAuthority)		
{

			Map authMap = authorityDao.selectAuthReq(authorityType);
			String authRevRule = (String) authMap.get("AUTHORITY_REV_RULE");
			String authDateRule = (String) authMap.get("AUTHORITY_DATE_RULE");
			String sbPartRule   = (String) authMap.get("SB_PART_NO_RULE");
			verifyAuthorityTypeFields(authRevRule,  authorityRev,  authDateRule, 
			                 authorityRevDate,  sbPartRule,  sbPartNo);
			
		    try {
				authorityDao.insertOrderTaskAuth( orderId,  subjectNo,  subjectRev,  authorityType,
				authorityDetail,  manualNo,  manualRev,  manualProvider, 
				manualType,  manualSection,  sbPartNo,  authorityRev,
				authorityRevDate, authority, arcAuthority);
		    	}
		    catch (Exception e)
		    {
		    	message.raiseError("MFI_50400","Record already exists for order/task group");
		    }
}
		
		public void updateOrderTaskAuth(String orderId, String subjectNo, String subjectRev, String authorityType,
                String authorityDetail, String manualNo, String manualRev, String manualProvider, 
                String manualType, String manualSection, String sbPartNo, String authorityRev,
                Date authorityRevDate,String authority, String arcAuthority,Number seqNo)		 // defect 1890
{

			Map authMap = authorityDao.selectAuthReq(authorityType);
			String authRevRule = (String) authMap.get("AUTHORITY_REV_RULE");
			String authDateRule = (String) authMap.get("AUTHORITY_DATE_RULE");
			String sbPartRule   = (String) authMap.get("SB_PART_NO_RULE");
			verifyAuthorityTypeFields(authRevRule,  authorityRev,  authDateRule, 
			                 authorityRevDate,  sbPartRule,  sbPartNo);
			
			authorityDao.updateOrderTaskAuth( orderId,  subjectNo,  subjectRev,  authorityType,
			authorityDetail,  manualNo,  manualRev,  manualProvider, 
			manualType,  manualSection,  sbPartNo,  authorityRev,
			authorityRevDate, authority, arcAuthority,seqNo); //defect 1890
}
		public void deleteOrderTaskAuth(String orderId, String subjectNo, String subjectRev, String manualNo, Number seqNo) //defect 1890
		{ 
			authorityDao.deleteOrderTaskAuth(orderId,subjectNo, subjectRev,manualNo, seqNo);  //defect 1890
		}
		public void updateSubjectTaskGroup (String planId,Number planVersion,Number planRevision,Number planAlterations ,
				Number subjectNo ,String title , String isDefaultT,
				String ucfPlanSubjVch1,
				String ucfPlanSubjVch2,
				String ucfPlanSubjVch3,
				String ucfPlanSubjVch4,
				String ucfPlanSubjVch5,
				String ucfPlanSubjVch6,
				String ucfPlanSubjVch7,
				String ucfPlanSubjVch8,
				String ucfPlanSubjVch9,
				String ucfPlanSubjVch10,
				String ucfPlanSubjVch11,
				String ucfPlanSubjVch12,
				String ucfPlanSubjVch13,
				String ucfPlanSubjVch14,
				String ucfPlanSubjVch15,
				Number ucfPlanSubjNum1,
				Number ucfPlanSubjNum2,
				Number ucfPlanSubjNum3,
				Number ucfPlanSubjNum4,
				Number ucfPlanSubjNum5,
				String  ucfPlanSubjFlag1,
				String  ucfPlanSubjFlag2,
				String  ucfPlanSubjFlag3,
				String  ucfPlanSubjFlag4,
				String  ucfPlanSubjFlag5,
				Date  ucfPlanSubjDate1,
				Date  ucfPlanSubjDate2,
				Date  ucfPlanSubjDate3,
				Date  ucfPlanSubjDate4,
				Date  ucfPlanSubjDate5,
				String authority,
				Number planUpdtNo)
		{
			sfplPlan.updateSubjectTaskGroup(planId, planVersion, planRevision, planAlterations ,
					 subjectNo , title ,  isDefaultT,
					 ucfPlanSubjVch1,
					 ucfPlanSubjVch2,
					 ucfPlanSubjVch3,
					 ucfPlanSubjVch4,
					 ucfPlanSubjVch5,
					 ucfPlanSubjVch6,
					 ucfPlanSubjVch7,
					 ucfPlanSubjVch8,
					 ucfPlanSubjVch9,
					 ucfPlanSubjVch10,
					 ucfPlanSubjVch11,
					 ucfPlanSubjVch12,
					 ucfPlanSubjVch13,
					 ucfPlanSubjVch14,
					 ucfPlanSubjVch15,
					 ucfPlanSubjNum1,
					 ucfPlanSubjNum2,
					 ucfPlanSubjNum3,
					 ucfPlanSubjNum4,
					 ucfPlanSubjNum5,
					 ucfPlanSubjFlag1,
					 ucfPlanSubjFlag2,
					 ucfPlanSubjFlag3,
					 ucfPlanSubjFlag4,
					 ucfPlanSubjFlag5,
					 ucfPlanSubjDate1,
					 ucfPlanSubjDate2,
					 ucfPlanSubjDate3,
					 ucfPlanSubjDate4,
					 ucfPlanSubjDate5);
						
			authorityDao.updateSforTaskAuth(planId,planUpdtNo, subjectNo, authority);
		}
		public void insertSubjectTaskGroup (String planId,Number planVersion,Number planRevision,Number planAlterations ,
				Number subjectNo ,String title , String isDefaultT,
				String ucfPlanSubjVch1,
				String ucfPlanSubjVch2,
				String ucfPlanSubjVch3,
				String ucfPlanSubjVch4,
				String ucfPlanSubjVch5,
				String ucfPlanSubjVch6,
				String ucfPlanSubjVch7,
				String ucfPlanSubjVch8,
				String ucfPlanSubjVch9,
				String ucfPlanSubjVch10,
				String ucfPlanSubjVch11,
				String ucfPlanSubjVch12,
				String ucfPlanSubjVch13,
				String ucfPlanSubjVch14,
				String ucfPlanSubjVch15,
				Number ucfPlanSubjNum1,
				Number ucfPlanSubjNum2,
				Number ucfPlanSubjNum3,
				Number ucfPlanSubjNum4,
				Number ucfPlanSubjNum5,
				String  ucfPlanSubjFlag1,
				String  ucfPlanSubjFlag2,
				String  ucfPlanSubjFlag3,
				String  ucfPlanSubjFlag4,
				String  ucfPlanSubjFlag5,
				Date  ucfPlanSubjDate1,
				Date  ucfPlanSubjDate2,
				Date  ucfPlanSubjDate3,
				Date  ucfPlanSubjDate4,
				Date  ucfPlanSubjDate5,
				String authority,
				Number planUpdtNo)
		{
			sfplPlan.insertSubjectTaskGroup(planId, planVersion, planRevision, planAlterations ,
					 subjectNo , title ,  isDefaultT,
					 ucfPlanSubjVch1,
					 ucfPlanSubjVch2,
					 ucfPlanSubjVch3,
					 ucfPlanSubjVch4,
					 ucfPlanSubjVch5,
					 ucfPlanSubjVch6,
					 ucfPlanSubjVch7,
					 ucfPlanSubjVch8,
					 ucfPlanSubjVch9,
					 ucfPlanSubjVch10,
					 ucfPlanSubjVch11,
					 ucfPlanSubjVch12,
					 ucfPlanSubjVch13,
					 ucfPlanSubjVch14,
					 ucfPlanSubjVch15,
					 ucfPlanSubjNum1,
					 ucfPlanSubjNum2,
					 ucfPlanSubjNum3,
					 ucfPlanSubjNum4,
					 ucfPlanSubjNum5,
					 ucfPlanSubjFlag1,
					 ucfPlanSubjFlag2,
					 ucfPlanSubjFlag3,
					 ucfPlanSubjFlag4,
					 ucfPlanSubjFlag5,
					 ucfPlanSubjDate1,
					 ucfPlanSubjDate2,
					 ucfPlanSubjDate3,
					 ucfPlanSubjDate4,
					 ucfPlanSubjDate5);
			
			authorityDao.updateSforTaskAuth(planId,planUpdtNo, subjectNo, authority);		
		}

		public void updatePlanManualRev(String planId, Number planRevision,String subjectNo, String subjectRev,
				                        String manualNo, String manualRev, String updatePlanFlag,String revStatus,
				                        OutputParameter outPlanRevision,Number seqNo) //defect 1890
		{
	      if (StringUtils.equals("Y", updatePlanFlag))		
	    	
	      {  Number plgauthcount = authorityDao.selectPlanAuthCount(planId);
	      
			if (StringUtils.equals("PLG_AUTHORING", revStatus) )
			{
				  authorityDao.updateAuthManRev(planId, planRevision, subjectNo, subjectRev, manualNo, manualRev,seqNo); //defect 1890
			}
			else
			{
				if (StringUtils.equals("PLAN COMPLETE", revStatus) || StringUtils.equals("PLAN RELEASED", revStatus))
				{
					if (plgauthcount.intValue() == 0 )
					{	
						String pwpId = authorityDao.selectPWP(planId, planRevision);
						Map planMap = authorityDao.selectPlanInfo(planId, planRevision);					
						String notes = "New Manual Revision " + manualRev;
						String partChg = (String) planMap.get("PART_CHG");
						String bomChg = (String) planMap.get("MFG_BOM_CHG");
						String planNo = (String) planMap.get("PLAN_NO");					
						String workLoc = (String) planMap.get("PLND_WORK_LOC");
						String program = (String) planMap.get("PROGRAM");
						String bomNo = (String) planMap.get("BOM_NO");
						String partNo = (String) planMap.get("PART_NO");
						String bomId = (String) planMap.get("BOM_ID");
						String workFlow = (String) planMap.get("WORK_FLOW");												
						plan.startPlanRevision(planId, 1, outPlanRevision, 0, pwpId, notes, "UDV", partChg,bomChg, false,
								               planNo,workLoc, program, bomNo, partNo,bomId, null, null, workFlow);
						
						Number newPlanRevision = planRevision.intValue() + 1;
						authorityDao.updateAuthManRev(planId, newPlanRevision, subjectNo, subjectRev, manualNo, manualRev,seqNo); //defect 1890
						event.closeTool();				 
						event.launchDispatch("Process Planning", "Manuals",null,null);
					}
					else 
					{
						Number newPlanRev = planRevision.intValue() + 1;						
					    authorityDao.updateAuthManRev(planId, newPlanRev, subjectNo, subjectRev, manualNo, manualRev,seqNo);
					}
				}
				else
				{
					String docType="Process Plan";
					String status = "REJECT";
					Map taskInfoMap = authorityDao.selectPlanTaskInfo(planId, planRevision);
					String taskId = (String) taskInfoMap.get("TASK_ID");
					String taskType = (String) taskInfoMap.get("TASK_TYPE");
					String queueType = (String) taskInfoMap.get("QUEUE_TYPE");
					String notes = (String) taskInfoMap.get("NOTES ")  + "NEW MANUAL REV " + manualRev;
					
					plan.warningMsgCheck(taskId,taskType,queueType,status,notes,null,null,null,docType,null,
							             planId,1,planRevision,0,revStatus);
					
					authorityDao.updateAuthManRev(planId, planRevision, subjectNo, subjectRev, manualNo, manualRev,seqNo);   
					event.closeTool();				 
					event.launchDispatch("Process Planning", "Manuals",null,null);
               }
			}
	      }	
		}
		public void updateOrderManualRev(String orderId, Number subjectNo, Number subjectRev, 
                						 String manualNo,String manualRev, String updateOrderFlag,
                						 String orderStatus, Number seqNo) //defect 1890
		{
		   if (StringUtils.equals(updateOrderFlag, "Y"))
		   {   Number ordauthcount = authorityDao.selectOrderAuthCount(orderId);
			   if (StringUtils.contains(orderStatus, "AUTHORING"))
			   {
				   authorityDao.updateOrderManRev(orderId, subjectNo, subjectRev, manualNo, manualRev,seqNo); //defect 1890
			   }
			   else
			   {   if (ordauthcount.intValue() == 0)
			      {
				   String altReason = "Update to Engine Manual " + manualNo + " " + manualRev;
				   String altNo = authorityDao.selectGUID();
				   Number operKey = authorityDao.selectMinOperKey(orderId, subjectNo, subjectRev);
				   String workFlow = authorityDao.selectOrderWorkFlow(orderId);
				   oobCall.preStartAlteration(orderId,altReason,null,altNo,null,null,null,null,null,null,null,null,null,null,null,
                                              null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
                                              null,null,null,"ORDER",operKey,null,null,null,workFlow,null,null,null,null,null,null,null);

				   authorityDao.updateOrderManRev(orderId, subjectNo, subjectRev, manualNo, manualRev, seqNo); //defect 1890
				   event.closeTool();				 
				   event.launchDispatch("Process Planning", "Manuals",null,null);
			      }
			   else
			     {
				   authorityDao.updateOrderManRev(orderId, subjectNo, subjectRev, manualNo, manualRev, seqNo); //defect 1890
			     } 
			   }
		   
		   }
		}
  }
