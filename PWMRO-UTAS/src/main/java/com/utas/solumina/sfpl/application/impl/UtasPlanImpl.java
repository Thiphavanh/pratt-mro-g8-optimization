package com.utas.solumina.sfpl.application.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.event.SoluminaEventPublisher;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.security.SQLSecurityManager;
import com.ibaset.common.util.SoluminaUtils;
import com.ibaset.solumina.data.DataMigrationService;
import com.ibaset.solumina.sfcore.application.ILicense;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.application.ISecurityGroup;
import com.ibaset.solumina.sffnd.dao.ISecurityGroupDao;
import com.ibaset.solumina.sfpl.application.IPlan;
import com.ibaset.solumina.sfpl.dao.IPlanDao;
import com.utas.solumina.common.UtasConstants;
import com.utas.solumina.paar.dao.PaarRequestDao;
import com.utas.solumina.paar.domain.SolPaar;
import com.utas.solumina.paar.event.PaarRequestEvent;
import com.utas.solumina.paar.integration.PaarRequestApplication;
import com.utas.solumina.sfpl.application.IUtasPlan;
import com.utas.solumina.sfpl.dao.IUtasPlanDao;



public class UtasPlanImpl implements IUtasPlan
{
	@Reference
    private IPlan plan;

	@Reference
	private IUtasPlanDao utasPlanDao;
	@Reference
	IEvent event;
	@Reference
	IPlanDao planDao;
	@Reference
	IMessage message;
	@Reference
	SoluminaEventPublisher soluminaEventPublisher;
	
	@Reference
	private DataMigrationService dataMigrationService; //UTAS-435
	
	@Reference
	private ISecurityGroupDao fndSecurityGroupDao; //UTAS-435
	
	@Reference
	private PaarRequestApplication paarRequestApplication;
	
	@Reference
	private PaarRequestDao paarRequestDao;
	
	@Reference
	private ISecurityGroup fndSecurityGroup;
	
	@Reference
	private ILicense license;
	
	@Reference
	private DataFieldMaxValueIncrementer soluminaSequence;
	
	
	@Override
	public void firePaarReviewEvent(String requestId) 
	{
		// Build Message Object
		SolPaar solPaar = paarRequestApplication.buildSolPaar(requestId);
		
		// Build Event Object
		PaarRequestEvent paarRequestEvent = new PaarRequestEvent(this);
		
		// Set event object
		paarRequestEvent.addSolPaar(solPaar);
		
		// Fire Event
		soluminaEventPublisher.publishEvent(paarRequestEvent);
		
		// Update Request Status to Review
		paarRequestDao.updateRequestStatus(requestId, SoluminaConstants.IN_REVIEW.toUpperCase(), null);
		
		// Refresh Tool
		event.refreshTool();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void insertProfile(String requestId, 
							  String pmnTypeEntity, 
							  Date effFromDate, 
							  Date effThruDate,
							  String pmnType)
	{
		String updateUserId = ContextUtil.getUsername();
		
		try
		{
			// Check if Eff Thru Date is greater than Eff From Date
			if((effFromDate != null && effThruDate != null)
					&& effThruDate.compareTo(effFromDate) < 0 )
			{
				// Raise Error that Thru Date is less than From Date
				message.raiseError("MFI_20", "Effective Thru Date can not be less than Effective From Date");
			}
			
			if(StringUtils.equals(pmnType, StringUtils.upperCase("Security Group")) || StringUtils.equals(pmnType, StringUtils.upperCase(UtasConstants.LOCATION)))
			{
				effFromDate = null;
				effThruDate = null;
			}
			
			// Insert User Profile
			utasPlanDao.insertProfile(pmnType, 
									  pmnTypeEntity, 
									  UtasConstants.ADDED, 
									  effFromDate, 
									  effThruDate, 
									  requestId, 
									  updateUserId);
		}
		catch(DataIntegrityViolationException daie)
		{
			Map map = utasPlanDao.selectDeletedRecordExists(requestId, pmnType, pmnTypeEntity);
			if(map != null && map.size() > 0)
			{
				String sghtId = (String) map.get("SGHT_ID");
				
				// Location or Security Group don't have editable fields, so return these back to PERSISTED
				if (StringUtils.equalsIgnoreCase("LOCATION", pmnType) || StringUtils.equalsIgnoreCase("SECURITY GROUP", pmnType))
				{
				    utasPlanDao.updateExpirationDate(requestId, sghtId, effFromDate, effThruDate, "PERSISTED");
				}
				else if (StringUtils.equalsIgnoreCase("USER", pmnType))
				{
				    // We are not setting the Permission Action to Updated when calling update so to be consistent will set this to PERSISTED,
				    // even though the dates may have changed.
				    utasPlanDao.updateExpirationDate(requestId, sghtId, effFromDate, effThruDate, "PERSISTED");			
				}
			}
			else
			{
				message.raiseError("MFI_20", "Record already exists");
			}
		}
	}
	
	@Override
	public void deleteSuggestedPermissionProfile(String sghtId, String requestId, String pmnAction)
	{
		if(StringUtils.equalsIgnoreCase("ADDED", pmnAction))
		{
		 // Delete Record
            utasPlanDao.deleteSuggestedPermissionProfileRecord(sghtId, requestId);
		}
		else
		{
		    // Update Record with Deleted Action
		    utasPlanDao.updateSuggestedPermissionProfileRecordToDeleted(sghtId, requestId, SoluminaConstants.DELETED);
		}
	}
	
	@Override
	public void updateProfile(String requestId, 
							  String sghtId, 
							  Date pmnEffFromDate, 
							  Date pmnEffThruDate)
	{
		if((pmnEffFromDate != null && pmnEffThruDate != null)
				&& pmnEffThruDate.compareTo(pmnEffFromDate) < 0 )
		{
			// Raise Error that Thru Date is less than From Date
			message.raiseError("MFI_20", "Effective Thru Date can not be less than Effective From Date");
		}
		
		utasPlanDao.updateProfile(requestId, 
								  sghtId, 
								  pmnEffFromDate, 
								  pmnEffThruDate);
	}
	
	@Override
	public void updatePAARReviewHeader(String requestId, 
									   String requestReason,
									   Date replyRequiredDate) 
	{
		// Update Header for Request Id
		utasPlanDao.updatePAARReviewHeader(requestId, 
										   requestReason, 
										   replyRequiredDate);
	}
	
	//UTAS-435
	public void importPlan(String pwpId, 
						   String fileName, 
						   String restricted) 
	{
		//import plan
		dataMigrationService.importPlan(pwpId, fileName);
		String planNumber = (String)ContextUtil.getUser().getContext().get("IMPORT_PLAN_NO");
		String plannedWorkLocation = (String)ContextUtil.getUser().getContext().get("IMPORT_PLAN_PLND_WORK_LOC");
		Number planUpdateNumber = 1;  //It will be always one while plan creating
		String planId = null;
		if(planNumber != null)
		{
			if(StringUtils.equals(restricted, SoluminaConstants.Y))
			{
				Map planInfo = utasPlanDao.selectPlanInfoByPlanNumber(planNumber, planUpdateNumber);

				planId = (String)planInfo.get("PLAN_ID");
				Number planVersion = (Number)planInfo.get("PLAN_VERSION");
				Number planRevision = (Number)planInfo.get("PLAN_REVISION");
				Number planAlt = (Number)planInfo.get("PLAN_ALTERATIONS");

				requestPaarReviewForNewPlan(planId, planVersion, planRevision, planAlt, planUpdateNumber);

				event.showUdv("HAMSI_178391EEEB086EF6E053D20F0A0AD469",
							   null,
							   "Import Restricted Plan Display Message",
							   null,
							   null);


			}
			else
			{
				// Select Security Groups for Assigned Work Location
				List workLocationSecGroupList = utasPlanDao.selectSecurityGroupForAssignedWorkLocation(plannedWorkLocation);
				Iterator workLocationSecGrpIt = workLocationSecGroupList.listIterator();
				
				while(workLocationSecGrpIt.hasNext())
				{
					Map workLocationSecGrpMap = (Map) workLocationSecGrpIt.next();
					String workLocationSecGrpStr = (String)workLocationSecGrpMap.get("SECURITY_GROUP");

					assignSecurityGrpToPlan(planId, planUpdateNumber,workLocationSecGrpStr);

				}
			}
		}
		ContextUtil.getUser().getContext().set("IMPORT_PLAN_NO", "");
		ContextUtil.getUser().getContext().set("IMPORT_PLAN_PLND_WORK_LOC", "");
	}

	//UTAS-210
	public String requestPaarReview(String planId, 
								    Number planVersion, 
								    Number planRevision,
								    Number planAlteration,
								    Number planUpdateNumber, 
								    String calledFrom)
	{
        // Get Plan Number
        String planNumber = utasPlanDao.selectPlanNumber(planId, planUpdateNumber);
        
	    // Check if any Restricted Security Group Exists in this Plan
		String restrictedSecurityGroup = utasPlanDao.selectRestrictedSecurityGroup(planId, planUpdateNumber);
		List users = new ArrayList();		
		
		if(StringUtils.isNotEmpty(restrictedSecurityGroup))
		{
		    // Remove the Security Group from Plan
		    fndSecurityGroupDao.deleteWorkPlanSecurityGroup(planId,planUpdateNumber,restrictedSecurityGroup);

		    fndSecurityGroupDao.updateSecurityGroupInPlan(planId,planUpdateNumber, restrictedSecurityGroup, SoluminaConstants.DELETE);		    
				
		    // Get list of all users assigned to this Restricted Security Group
		    users = utasPlanDao.selectRestrictedSecurityGroupUserList(restrictedSecurityGroup);

		    // If the Security Group is not being used elsewhere, delete the Users from the Security Group and then Security Group itself.
		    boolean isSecGrpStillInUse = paarRequestDao.selectSecGrpStillInUse(restrictedSecurityGroup);

		    if (!isSecGrpStillInUse)
		    {
		        paarRequestDao.deleteAllUsersFromSecGrp(restrictedSecurityGroup);
		        fndSecurityGroupDao.deleteSecurityGroup(restrictedSecurityGroup);
		    }
		
		}
		
		// Now look to see if the plan has a prior request - and if so, get the last one.
		String priorRequestId = StringUtils.EMPTY;
		String priorRequestStatus = StringUtils.EMPTY;
		
		Map priorPlanReqMap = utasPlanDao.selectLastPlanRequest(planId);
		
		if (priorPlanReqMap != null && priorPlanReqMap.size() > 0)
		{
		    // There was a prior request
		    priorRequestId = (String) priorPlanReqMap.get("REQUEST_ID");
		    priorRequestStatus = (String) priorPlanReqMap.get("REQUEST_STATUS");	
		    		    
		    if (StringUtils.equalsIgnoreCase("CANCELLED", priorRequestStatus))
		    {
		     // We don't want to send the prior request if it was Cancelled
		        priorRequestId = StringUtils.EMPTY;
		    }
		    else if (StringUtils.equalsIgnoreCase("IN REVIEW", priorRequestStatus))
		    {
		        // Need to cancel this request in Solumina
		        utasPlanDao.cancelAnyInReviewPlgRequests(planId);
		    }
		}
       
		// Create the new restricted security group and the PAAR request.
		String guidSecurityGroup = setRestrictedSecurityForExistingPlan(planId, planUpdateNumber);

		String requestId = createPaarRequest(planId, planVersion, planRevision, planAlteration, priorRequestId, guidSecurityGroup);

		// Now create the Sought Permission records from the current Plan security.       
		String updateUserId = ContextUtil.getUsername();
		
		//Get All non restricted security group of plan and insert it to Sought Permissions with a permission action of PERSISTED.
		List<Map> nonRestrictedSecGrpList = utasPlanDao.selectNonRestrictedSecGrp(planId, planUpdateNumber);
		for(Map nonResSecGrpMap : nonRestrictedSecGrpList)
		{
		    String securityGroup = (String)nonResSecGrpMap.get("SECURITY_GROUP");
		    try
		    {
		        utasPlanDao.insertProfile("Security Group".toUpperCase(), 
		                                  securityGroup, 
		                                  UtasConstants.PERSISTED, 
		                                  null, 
		                                  null, 
		                                  requestId, 
		                                  updateUserId);
		    }
		    catch (DataIntegrityViolationException ex) 
		    {
		        //Do Nothing.
		    }
		}
		       
        // Now add in users from the prior restricted security group
        if(users != null && !users.isEmpty())
        {
            for(int i = 0; i < users.size(); i++)
            {
                Map user = (Map) users.get(i);
                String userId = (String) user.get("USERID");
                Date effStartDate = (Date) user.get("EFFECTIVE_START_DATE");
                Date effEndDate = (Date) user.get("EFFECTIVE_END_DATE");

                if(!StringUtils.equals(userId, ContextUtil.getUsername()))
                {					
                    fndSecurityGroupDao.insertUserSecurityGroup(userId,
                                                                guidSecurityGroup,
                                                                ContextUtil.getUsername(),
                                                                effStartDate,
                                                                effEndDate);

                    try
                    {
                        // Add Existing User Profiles with PERSISTED action
                        utasPlanDao.insertProfile(SoluminaConstants.USER, 
                                                  userId, 
                                                  UtasConstants.PERSISTED, 
                                                  effStartDate, 
                                                  effEndDate, 
                                                  requestId, 
                                                  ContextUtil.getUsername());
                    }
                    catch(DataIntegrityViolationException dive)
                    {
                        utasPlanDao.updateUserProfile(requestId, 
                                                      SoluminaConstants.USER, 
                                                      userId, 
                                                      UtasConstants.PERSISTED, 
                                                      effStartDate, 
                                                      effEndDate);
                    }
                }
            }
        }
		
		// Now add the current user - who is the requesting user.
		try
		{
		    utasPlanDao.insertProfile(SoluminaConstants.USER, 
		                              ContextUtil.getUsername(), 
		                              UtasConstants.PERSISTED, 
		                              utasPlanDao.selectDBTimeStamp(), 
		                              SoluminaUtils.parseDate("01/01/9999"), 
		                              requestId, 
		                              ContextUtil.getUsername());
		}
		catch(Exception e)
		{
		    // This is here to catch any parse date issues.
		}

		SQLSecurityManager.getInstance().refreshSecurityPolicy();

		return requestId;
	}

	@Override
	public void insertApprovalProfile(String requestId, 
									  String pmnTypeEntity,
									  String pmnStatus, 
									  Date effFromDate, 
									  Date effThruDate, 
									  String pmnType) 
	{	
		String updateUserId = ContextUtil.getUsername();
		
		try
		{
			// Check if Eff Thru Date is greater than Eff From Date
			if((effFromDate != null && effThruDate != null)
					&& effThruDate.compareTo(effFromDate) < 0 )
			{
				// Raise Error that Thru Date is less than From Date
				message.raiseError("MFI_20", "Effective Thru Date can not be less than Effective From Date");
			}
			
			// Insert User Profile
			utasPlanDao.insertApprovalProfile(pmnType, 
											  pmnTypeEntity,
											  pmnStatus,
											  effFromDate, 
											  effThruDate, 
											  requestId, 
											  updateUserId);
		}
		catch(DataIntegrityViolationException daie)
		{
			Map map = utasPlanDao.selectDeletedApprovalRecordExists(requestId, pmnType, pmnTypeEntity);
			if(map != null && map.size() > 0)
			{
				String authId = (String) map.get("AUTH_ID");
				utasPlanDao.updateApprovalProfileExpirationDate(requestId, authId, pmnStatus, effFromDate, effThruDate, SoluminaConstants.UPDATED);				
			}
			else
			{
				message.raiseError("MFI_20", "Record already exists");
			}
		}
	}

	@Override
	public void updateApprovalProfile(String requestId, 
									  String authId,
									  String pmnStatus, 
									  Date pmnEffFromDate, 
									  Date pmnEffThruDate) 
	{
		if((pmnEffFromDate != null && pmnEffThruDate != null)
				&& pmnEffThruDate.compareTo(pmnEffFromDate) < 0 )
		{
			// Raise Error that Thru Date is less than From Date
			message.raiseError("MFI_20", "Effective Thru Date can not be less than Effective From Date");
		}
		
		utasPlanDao.updateApprovalProfile(requestId, 
								  		  authId, 
								  		  pmnStatus,
								  		  pmnEffFromDate, 
								  		  pmnEffThruDate);
	}

	@Override
	public void deleteApprovalPermissionProfile(String requestId, 
												String authId) 
	{
		utasPlanDao.deleteApprovalPermissionProfileRecord(authId, 
				   										  requestId);
	}

	@Override
	/*
	 * UTAS-496
	 * (non-Javadoc)
	 * @see com.utas.solumina.sfpl.application.IUtasPlan#insertOrdSecGrpApprovalProfile(java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date, java.lang.String)
	 */
	public void insertOrdSecGrpApprovalProfile(String requestId,
											   String pmnTypeEntity,
											   String pmnStatus,
											   Date effFromDate,
											   Date effThruDate, 
											   String pmnType) 
	{
		String updateUserId = ContextUtil.getUsername();
		
		try
		{
			// Check if Eff Thru Date is greater than Eff From Date
			if((effFromDate != null && effThruDate != null)
					&& effThruDate.compareTo(effFromDate) < 0 )
			{
				// Raise Error that Thru Date is less than From Date
				message.raiseError("MFI_20", "Effective Thru Date can not be less than Effective From Date");
			}
			
			// Insert User Profile
			utasPlanDao.insertOrdSecGrpApprovalProfile(pmnType, 
											  pmnTypeEntity,
											  pmnStatus,
											  effFromDate, 
											  effThruDate, 
											  requestId, 
											  updateUserId);
		}
		catch(DataIntegrityViolationException daie)
		{
			Map map = utasPlanDao.selectDeletedApprovalRecordExists(requestId, pmnType, pmnTypeEntity);
			if(map != null && map.size() > 0)
			{
				String authId = (String) map.get("AUTH_ID");
				utasPlanDao.updateOrdSecGrpApprovalProfileExpirationDate(requestId, authId, pmnStatus, effFromDate, effThruDate, SoluminaConstants.UPDATED);				
			}
			else
			{
				message.raiseError("MFI_20", "Record already exists");
			}
		}
	
	}

	@Override
	/*
	 * UTAS-496
	 * (non-Javadoc)
	 * @see com.utas.solumina.sfpl.application.IUtasPlan#updateOrdSecGrpApprovalProfile(java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date)
	 */
	public void updateOrdSecGrpApprovalProfile(String requestId,
											   String authId,
											   String pmnStatus,
											   Date pmnEffFromDate,
											   Date pmnEffThruDate) 
	{
		if((pmnEffFromDate != null && pmnEffThruDate != null)
				&& pmnEffThruDate.compareTo(pmnEffFromDate) < 0 )
		{
			// Raise Error that Thru Date is less than From Date
			message.raiseError("MFI_20", "Effective Thru Date can not be less than Effective From Date");
		}
		
		utasPlanDao.updateOrdSecGrpApprovalProfile(requestId, 
								  		  		   authId, 
								  		  		   pmnStatus,
								  		  		   pmnEffFromDate, 
								  		  		   pmnEffThruDate);
	}

	@Override
	/*
	 * UTAS-496
	 * (non-Javadoc)
	 * @see com.utas.solumina.sfpl.application.IUtasPlan#deleteOrdSecGrpApprovalPermissionProfile(java.lang.String, java.lang.String)
	 */
	public void deleteOrdSecGrpApprovalPermissionProfile(String requestId,
														 String authId)
	{
		utasPlanDao.deleteOrdSecGrpApprovalPermissionProfileRecord(authId, 
				   										  		   requestId);
	}
	
    //UTAS-210
    public String createPaarRequest(String planId, 
                                    Number planVer,
                                    Number planRev, 
                                    Number planAlt,
                                    String priorRequestId,
                                    String guidSecurityGroup)
    {
        // Insert into UTASGI_PLG_PAAR_DESC table – status OPEN
        String requestId = soluminaSequence.nextStringValue();
        String requestStatus = SoluminaConstants.OPEN_STATUS;
        String requestUser = ContextUtil.getUsername();

        utasPlanDao.insertPaarDesc(requestId, requestStatus, requestUser, null, planId, planVer, planRev, planAlt, null, priorRequestId, guidSecurityGroup);
                
        event.refreshTool();
        return requestId;
    }
    
    //UTAS-210
    public String requestPaarReviewForNewPlan(String planId, 
                                              Number planVersion, 
                                              Number planRevision,
                                              Number planAlteration,
                                              Number planUpdateNumber)
    {
        String priorRequestId = StringUtils.EMPTY;
        
        String guidSecurityGroup = setRestrictedSecurityForNewPlan(planId, planUpdateNumber);  
        String requestId = createPaarRequest(planId, planVersion, planRevision, planAlteration, priorRequestId, guidSecurityGroup);
        
        // Now create the Sought Permission Profile using assigned Plan Security Groups and Requesting User Id
        String updateUserId = ContextUtil.getUsername();
        String planNumber = utasPlanDao.selectPlanNumber(planId, planUpdateNumber);
        List<Map> nonRestrictedSecGrpList = utasPlanDao.selectNonRestrictedSecGrp(planId, planUpdateNumber);
            
        for(Map nonResSecGrpMap : nonRestrictedSecGrpList)
        {
            String securityGroup = (String)nonResSecGrpMap.get("SECURITY_GROUP");
            try
            {
                utasPlanDao.insertProfile("Security Group".toUpperCase(), 
                                          securityGroup, 
                                          UtasConstants.PERSISTED, 
                                          null, 
                                          null, 
                                          requestId, 
                                          updateUserId);
            }
            catch (DataIntegrityViolationException ex) 
            {
                //Do Nothing.
            }
        }
            
        // Now add the current user - who is the requesting user.
        try
        {
            utasPlanDao.insertProfile(SoluminaConstants.USER, 
                                      ContextUtil.getUsername(), 
                                      UtasConstants.PERSISTED, 
                                      utasPlanDao.selectDBTimeStamp(), 
                                      SoluminaUtils.parseDate("01/01/9999"), 
                                      requestId, 
                                      ContextUtil.getUsername());
        }
        catch(Exception e)
        {
            // This is here to catch any parse date issues.
        }

        return requestId;
    }   
    
private String setRestrictedSecurityForNewPlan(String planId, 
            								    Number planUpdateNumber)
{
// Create new security group.
String securityGroup = license.getLicensePrefix() + SoluminaConstants.UNDERSCORE + soluminaSequence.nextStringValue();

// Insert new security group into system.
fndSecurityGroup.insertSecurityGroup(securityGroup, null, null, null, null, null, null,  null, null, null, null, null, null, null,
      null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,  
      SoluminaConstants.R, null,  null, null, null);

// Assign Security group to user.
Date startDate = new Date();
Date effectiveEndDate = new Date();
try 
{
startDate = utasPlanDao.selectDBTimeStamp();
effectiveEndDate = SoluminaUtils.parseDate("01/01/9999");
} 
catch (ParseException e1) 
{
e1.printStackTrace();
}

fndSecurityGroupDao.insertUserSecurityGroup(ContextUtil.getUsername(), securityGroup, ContextUtil.getUsername(), startDate, effectiveEndDate);

SQLSecurityManager.getInstance().refreshSecurityPolicy();


// Delete any plan security that Plan Create or Plan Import may have set.
String planNumber = utasPlanDao.selectPlanNumber(planId, planUpdateNumber);        
utasPlanDao.deleteAllSecurityForPlan(planNumber);

// Now add the Restricted security group to the plan               
assignSecurityGrpToPlan(planId, planUpdateNumber, securityGroup);

return securityGroup;
}

    
private String setRestrictedSecurityForExistingPlan(String planId, 
                                                    Number planUpdateNumber)
    {
        // Create new security group.
        String securityGroup = soluminaSequence.nextStringValue();

        // Insert new security group into system.
        fndSecurityGroup.insertSecurityGroup(securityGroup, 
        									null, null, null, null, null, null,  null, null, null, null, 
        									null, null, null,
        									null, null, null, null, null, null,
        									null, null, null, null, null, null, null, 
        									null, null, null,  
                                            SoluminaConstants.R, null,  null, null, null);
          
        // Assign Security group to user.
        Date startDate = new Date();
        Date effectiveEndDate = new Date();
        try 
        {
            startDate = utasPlanDao.selectDBTimeStamp();
            effectiveEndDate = SoluminaUtils.parseDate("01/01/9999");
        } 
        catch (ParseException e1) 
        {
            e1.printStackTrace();
        }
          
        fndSecurityGroupDao.insertUserSecurityGroup(ContextUtil.getUsername(), securityGroup, ContextUtil.getUsername(), startDate, effectiveEndDate);
          
        SQLSecurityManager.getInstance().refreshSecurityPolicy();
                              
        // Now add the security group to the plan
        String planNumber = utasPlanDao.selectPlanNumber(planId, planUpdateNumber);       
        assignSecurityGrpToPlan(planId, planUpdateNumber, securityGroup);

        return securityGroup;
    }
    
    public void checkUserSecurityForPlanWorkLoc(String plannedWorkLoc)
    {
    	// check that the user has access to at least one security group associated to the work loc
    	checkUserWorkLoc(plannedWorkLoc, ContextUtil.getUsername());
    	
    	event.showUdv("PWUE_BC6174F1E8C70048E0430A1E10150048",
    				    SoluminaConstants.INSERT,
					   "Create Plan",
					    null,
					   "PWP_NOTE="+plannedWorkLoc);
    	
    }
    
    private void checkUserWorkLoc (String plannedWorkLoc, String userId)
    {
    	
    	// Select Security Groups for Assigned Work Location
		List workLocationSecGroupList = utasPlanDao.selectSecurityGroupForAssignedWorkLocation(plannedWorkLoc);
		if (workLocationSecGroupList.size() <= 0)
		{
			//Can't create a plan. Work Loc: %V1 doesn't have any default security assigned to it.
			message.raiseError("PWUE_3816B02E05ED480EE053D40F0A0AADDA", plannedWorkLoc);
		
		}
		// Select Security Groups Assigned for User
		List userAssignedSecGroupList = utasPlanDao.selectSecurityGroupListAssignedForUser(userId);
		if (userAssignedSecGroupList.size() <= 0)
		{
			//Can't create a plan. You don't have any security assigned to you.
			message.raiseError("PWUE_3819BE40E8FC56BEE053D40F0A0AD093");
		}
		
		
		Iterator workLocationSecGrpIt = workLocationSecGroupList.listIterator();
		Iterator userAssignedSecGroupIt = userAssignedSecGroupList.listIterator();
			
		//workLocationSecGrp loop
		boolean result = false;
		mainLoop:
		while(workLocationSecGrpIt.hasNext())
		{
				Map workLocationSecGrpMap = (Map) workLocationSecGrpIt.next();
				String workLocationSecGrpStr = (String)workLocationSecGrpMap.get("SECURITY_GROUP");
					
				//userAssignedSecGroup loop
				while(userAssignedSecGroupIt.hasNext())
				{
					Map userAssignedSecGroupMap = (Map) userAssignedSecGroupIt.next();
					String userAssignedSecGroupStr = (String)userAssignedSecGroupMap.get("SECURITY_GROUP");
						
					//verify user has workLocationSecGrp
					if(StringUtils.equals(userAssignedSecGroupStr, workLocationSecGrpStr))
					{
							result = true;
							break mainLoop;
					}
				}
		}
		
		if(result == false)
		{
			
			// You do not have security group access to create a plan for this Work Loc: %V1
			message.raiseError("PWUE_3819BE40E90656BEE053D40F0A0AD093", plannedWorkLoc);
		}
		
    }
    
    private void assignSecurityGrpToPlan(String planId,
    									 Number planUpdateNumber,
            							 String securityGroup) 
{
try
{
fndSecurityGroupDao.insertWorkPlanSecurityGroup(planId, planUpdateNumber, securityGroup, ContextUtil.getUsername());
}
catch(DataIntegrityViolationException exception)
{
// Record already exists - do nothing.
}

fndSecurityGroupDao.updateSecurityGroupInPlan(planId, planUpdateNumber, securityGroup, SoluminaConstants.INSERT);
}	

   
}
