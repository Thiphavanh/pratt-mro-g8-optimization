/*
*  This unpublished work, first created in 2018 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2018.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    PaarRequestApplicationImpl.java
* 
*  Created: 2018-11-15
* 
*  Author:  David Miron
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	-------------------------------------------------------------------
* 2018-08-24 	D.Miron		    Added PWU-124 Update from iBaset - Francis Graham
*/

package com.utas.solumina.paar.integration.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.solumina.domain.dao.DAO;
import com.ibaset.solumina.exception.SoluminaApplicationException;
import com.ibaset.solumina.sffnd.application.ISecurityGroup;
import com.ibaset.solumina.sffnd.dao.ISecurityGroupDao;
import com.ibaset.solumina.sffnd.dao.IUserDao;
import com.ibaset.solumina.sffnd.domain.SecurityGroupDef;
import com.ibaset.solumina.sffnd.domain.User;
import com.ibaset.solumina.sffnd.domain.WorkLocDef;
import com.ibaset.solumina.sfpl.dao.IPlanDao;
import com.ibaset.solumina.sfpl.domain.PlanDesc;
import com.ibaset.solumina.sfpl.domain.PlanDescId;
import com.ibaset.solumina.sfpl.domain.PlanRevId;
import com.ibaset.solumina.sfwid.domain.OrderDesc;
import com.utas.solumina.common.UtasConstants;
import com.utas.solumina.paar.dao.PaarRequestDao;
import com.utas.solumina.paar.domain.Permission;
import com.utas.solumina.paar.domain.PermissionDesc;
import com.utas.solumina.paar.domain.SghtPermissionSet;
import com.utas.solumina.paar.domain.SolPaar;
import com.utas.solumina.paar.domain.SolPaarWO;
import com.utas.solumina.paar.integration.PaarRequestApplication;
import com.utas.solumina.sffnd.dao.IUtasSecurityGroupDao;

public class PaarRequestApplicationImpl implements PaarRequestApplication 
{
	private PaarRequestDao paarRequestDao;
	
	@Reference("hibernateDAO")
    private DAO dao;
	
	@Reference
	private ISecurityGroup fndSecurityGroup; //UTAS-465
	
	@Reference
	ISecurityGroupDao ibtSecurityGroupDao;
	
	@Reference
	IUserDao fndUserDao;
	
	@Reference
	IPlanDao planDao;
	
	@Reference
	IUtasSecurityGroupDao utasSecurityGroupDao;
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public PermissionDesc selectPermissionDesc(String requestId) 
	{
		Map requestInfo = paarRequestDao.selectRequestInfo(requestId);
		
		if(requestInfo != null && requestInfo.size() > 0)
		{
			String planId = (String) requestInfo.get("PLAN_ID");
			Number planVersion = (Number) requestInfo.get("PLAN_VERSION");
			Number planRevision = (Number) requestInfo.get("PLAN_REVISION");
			Number planAlterations = (Number) requestInfo.get("PLAN_ALTERATIONS");
			Number planUpdtNo = (Number) requestInfo.get("PLAN_UPDT_NO");
			
			String requestUserId = (String) requestInfo.get("REQUEST_USERID");
			String requestReason = (String) requestInfo.get("REQUEST_REASON");		
			Date replyRequiredDate = (Date) requestInfo.get("REPLY_REQUIRED_DATE");
			Date requestCreationDate = (Date) requestInfo.get("REQUEST_CREATION_DATE");
			String priorRequestId = (String) requestInfo.get("PRIOR_REQUEST_ID");
			String requestStatus = (String) requestInfo.get("REQUEST_STATUS");
			
			// Create Plan Rev Object
			PlanRevId planRevId = new PlanRevId(planId, 
												new BigDecimal(planVersion.intValue()), 
												new BigDecimal(planRevision.intValue()), 
												new BigDecimal(planAlterations.intValue()));
			
			// Create PlanDescId object
	        PlanDescId planDescId = new PlanDescId(planId, new BigDecimal(planUpdtNo.intValue()));

	        PlanDesc planDesc = (PlanDesc) dao.get(PlanDesc.class, planDescId);
	
			// Create Permission Object
			PermissionDesc permissionDesc = new PermissionDesc(requestId, 
															   requestUserId,
															   requestReason,
															   requestCreationDate,
															   replyRequiredDate,
															   priorRequestId,
															   planRevId,
															   planDesc,
															   requestStatus);		
			return permissionDesc;
		}
		else
		{
			PermissionDesc permissionDesc = new PermissionDesc();
			permissionDesc.setRequestId(requestId);
			return permissionDesc;
		}
	}
	
	@Override
	public void savePermissionList(String tableName, String requestId, List<Permission> permissionList)
	{
		if(permissionList != null && permissionList.size() > 0)
		{
			for(Permission permission : permissionList)
			{
				String sghtId = permission.getSghtId();
				String type = permission.getType();
				String typeEntity = permission.getTypeEntity();
				Date effFromDate = permission.getStartDate();
				Date effThruDate = permission.getEndDate();
				String action = permission.getAction();
				String effect = permission.getEffect();
				
				// Check if SGHT_ID exists in matching SGHT table (PLG or WID)
				boolean permissionIdExists = false;
				if (StringUtils.equalsIgnoreCase("UTASGI_PLG_PAAR_PMN_AUTH", tableName))
				{
				    permissionIdExists = paarRequestDao.selectPlgPermissionExists(sghtId, requestId, type, typeEntity);
				}
				else if (StringUtils.equalsIgnoreCase("UTASGI_WID_PAAR_PMN_AUTH", tableName))
				{
				    permissionIdExists = paarRequestDao.selectWidPermissionExists(sghtId, requestId, type, typeEntity);
				}
												
				if(!permissionIdExists)
				{
					sghtId = StringUtils.EMPTY;
				}
				
				// Check corresponding values exists in Base Table
				if(StringUtils.equalsIgnoreCase(type, SoluminaConstants.USER)
						&& !fndUserDao.isSoluminaUser(typeEntity))
				{
					throw new SoluminaApplicationException("User: " + typeEntity + " does not exists in Database.");
				}
				else if(StringUtils.equalsIgnoreCase(type, UtasConstants.LOCATION)
						&& !paarRequestDao.selectWorkLocationExists(typeEntity))
				{
					throw new SoluminaApplicationException("Work Location: " + typeEntity + " does not exists in Database.");
				}
				else if(StringUtils.equalsIgnoreCase(type, "Security Group")
						&& !paarRequestDao.selectSecurityGroupExists(typeEntity))
				{
					throw new SoluminaApplicationException("Security Group: " + typeEntity + " does not exists in Database.");					
				}
								
				// If the permission request action is DELETED, we need to twist the value of the effect (two negatives make a positive).
				String pmAction = effect;
				if (StringUtils.equalsIgnoreCase("DELETED", action))
				{
				    if (StringUtils.equalsIgnoreCase("PERMIT", effect))
				    {
				        pmAction = "DENY";
				    }
				    else
				    {
				        pmAction = "PERMIT";
				    }
				}
							
				paarRequestDao.insertAuthPermission(tableName, 
													requestId, 
													sghtId, 
													type, 
													typeEntity, 
													pmAction, 
													effFromDate, 
													effThruDate);
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public SolPaar buildSolPaar(String requestId) 
	{
		Map requestInfo = paarRequestDao.selectRequestInfo(requestId);
		
		// Get Suggested Permission List
		List<Map> suggestedPermissionList = paarRequestDao.selectSuggestedPermissionList(requestId);
		
		// Create Permission List
		List<Permission> xmlSuggestedPermissionList = new ArrayList<Permission>(); 
		
		// Build Permission Object
		for(Map suggestedPermission : suggestedPermissionList)
		{
			String sghtId = (String) suggestedPermission.get("SGHT_ID");
			String pmnAction = (String) suggestedPermission.get("PMN_ACTION");
			String pmnType = (String) suggestedPermission.get("PMN_TYPE");
			String pmnTypeEntity = (String) suggestedPermission.get("PMN_TYPE_ENTITY");
			Date pmnEffFromDate = (Date) suggestedPermission.get("PMN_EFF_FROM_DATE");
			Date pmnEffThruDate = (Date) suggestedPermission.get("PMN_EFF_THRU_DATE");
			String authId = (String) suggestedPermission.get("AUTH_ID");
			
			Permission permission = new Permission(sghtId, authId, pmnAction, "Permit", pmnType, pmnTypeEntity, pmnEffFromDate, pmnEffThruDate);
			if(StringUtils.equalsIgnoreCase(pmnType, SoluminaConstants.USER))
			{
				// Get User Object
				User user = (User) dao.get(User.class, pmnTypeEntity);
				permission.setUser(user);
			}
			else if(StringUtils.equalsIgnoreCase(pmnType, UtasConstants.LOCATION))
			{
				// Get Location Object
			    String locId = paarRequestDao.selectWorkLocId(pmnTypeEntity);
				WorkLocDef workLoc = (WorkLocDef) dao.get(WorkLocDef.class, locId);
				permission.setWorkloc(workLoc);
			}
			else if(StringUtils.equalsIgnoreCase(pmnType, "Security Group"))
			{
				// Get Security Group Object
				SecurityGroupDef securityGroup = (SecurityGroupDef) dao.get(SecurityGroupDef.class, pmnTypeEntity);
				permission.setSecurityGroup(securityGroup);
			}
			
			xmlSuggestedPermissionList.add(permission);
		}
		
		// Get Request Info
		String planId = (String) requestInfo.get("PLAN_ID");
		Number planVersion = (Number) requestInfo.get("PLAN_VERSION");
		Number planRevision = (Number) requestInfo.get("PLAN_REVISION");
		Number planAlterations = (Number) requestInfo.get("PLAN_ALTERATIONS");
		Number planUpdtNo = (Number) requestInfo.get("PLAN_UPDT_NO");
		
		String requestUserId = (String) requestInfo.get("REQUEST_USERID");
		String requestReason = (String) requestInfo.get("REQUEST_REASON");		
		Date replyRequiredDate = (Date) requestInfo.get("REPLY_REQUIRED_DATE");
		Date requestCreationDate = (Date) requestInfo.get("REQUEST_CREATION_DATE");
		String priorRequestId = (String) requestInfo.get("PRIOR_REQUEST_ID");
		String requestStatus = (String) requestInfo.get("REQUEST_STATUS");

		// Create Plan Rev Object
		PlanRevId planRevId = new PlanRevId(planId, 
											new BigDecimal(planVersion.intValue()), 
											new BigDecimal(planRevision.intValue()), 
											new BigDecimal(planAlterations.intValue()));
		
		// Create PlanDescId object
		PlanDescId planDescId = new PlanDescId(planId, new BigDecimal(planUpdtNo.intValue()));

		PlanDesc planDesc = (PlanDesc) dao.get(PlanDesc.class, planDescId);
		
		// Create SolPaar
		SolPaar solPaar = new SolPaar();

		// Create Permission Object
		PermissionDesc permissionDesc = new PermissionDesc(requestId, 
														   requestUserId,
														   requestReason,
														   requestCreationDate,
														   replyRequiredDate,
														   priorRequestId,
														   planRevId,
														   planDesc,
														   requestStatus);

		solPaar.setPermissionDesc(permissionDesc);

		SghtPermissionSet sghtPermissionSet = new SghtPermissionSet();
		sghtPermissionSet.setSghtPermissionList(xmlSuggestedPermissionList);
		solPaar.setSghtPermissionSet(sghtPermissionSet);
		return solPaar;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	/*
	 * (non-Javadoc)
	 * @see com.utas.solumina.paar.integration.PaarRequestApplication#authorizedSecGrpToPlanAndUser(java.lang.String)
	 */
	public void authorizedSecGrpToPlanAndUser(String requestId)
	{
		Map requestInfo = paarRequestDao.selectRequestInfo(requestId);

		//Get Request Info
		String planId = (String) requestInfo.get("PLAN_ID");
		Number planVersion = (Number) requestInfo.get("PLAN_VERSION");
		Number planRevision = (Number) requestInfo.get("PLAN_REVISION");
		Number planAlterations = (Number) requestInfo.get("PLAN_ALTERATIONS");		
		String requestSecurityGroup = (String) requestInfo.get("REQUEST_SECURITY_GROUP");
		String planNumber = (String) requestInfo.get("PLAN_NO");
		Number planUpdtNo = (Number) requestInfo.get("PLAN_UPDT_NO");        
		ContextUtil.getUser().getContext().set("PLAN_UPDT_NO", planUpdtNo);
				
		List<Map> authorizedProfileList = paarRequestDao.selectAuthorizedProfileForRequestId(requestId);

		// Loop For every Profile
		for(Map authorizedProfileMap : authorizedProfileList)
		{		    
			String pmnType = (String) authorizedProfileMap.get("PMN_TYPE");
			String pmnAction = (String) authorizedProfileMap.get("PMN_ACTION");						
			String pmnTypeEntity = (String) authorizedProfileMap.get("PMN_TYPE_ENTITY");
			Date pmnEffFromDate = (Date) authorizedProfileMap.get("PMN_EFF_FROM_DATE");
			Date pmnEffThruDate = (Date) authorizedProfileMap.get("PMN_EFF_THRU_DATE");
			
			if(StringUtils.equals(pmnType, SoluminaConstants.USER))
			{
			    if (StringUtils.equalsIgnoreCase("PERMIT", pmnAction))
			    {
			        assignSecurityGroupToUser(planId, 
			                                  planVersion,
			                                  planRevision,
			                                  planAlterations,
			                                  pmnTypeEntity,
			                                  pmnEffFromDate,
			                                  pmnEffThruDate, 
			                                  requestSecurityGroup);
			    }
			    else if (StringUtils.equalsIgnoreCase("DENY", pmnAction))
			    {			        
			        removeSecurityGroupFromUser(requestSecurityGroup, pmnTypeEntity);
			    }
			}			
			else if (StringUtils.equals(pmnType, UtasConstants.LOCATION)) 
			{
			    if (StringUtils.equalsIgnoreCase("PERMIT", pmnAction))
                {
			        assgnWorkLocationSecurityToPlan(planId,planUpdtNo, pmnTypeEntity);
                }
                else if (StringUtils.equalsIgnoreCase("DENY", pmnAction))
                {
                    removeWorkLocationSecurityFromPlan(planId, 
                                                       planVersion,
                                                       planRevision,
                                                       planAlterations,
                                                       planNumber,
                                                       planUpdtNo,
                                                       pmnTypeEntity);
                }               
			}
			else if(StringUtils.equals(pmnType, "Security Group".toUpperCase()))
            {
			    if (StringUtils.equalsIgnoreCase("PERMIT", pmnAction))
                {
			        assignSecurityGrpToPlan(planId, planUpdtNo,pmnTypeEntity);
                }
                else if (StringUtils.equalsIgnoreCase("DENY", pmnAction))
                {
                    removeSecurityGrpFromPlan(planId, 
                                              planVersion,
                                              planRevision,
                                              planAlterations,
                                              planNumber,
                                              planUpdtNo,
                                              pmnTypeEntity);
                }               
            }
		}
		
		// Call method to assign or delete the Request Security Group.		
		processPlanRequestSecurityGroup(planId,
		                                planVersion,
		                                planRevision,
		                                planAlterations, 
		                                planNumber,
		                                planUpdtNo,
		                                requestSecurityGroup);
			
		// Update Request status to COMPLETE
		paarRequestDao.updateRequestStatus(requestId, SoluminaConstants.COMPLETE.toUpperCase(), null);
	}

	//UTAS-465
	private void assignSecurityGroupToUser(String planId,
										  Number planVersion,
										  Number planRevision,
										  Number planAlterations,
										  String userId,
										  Date pmnEffFromDate,
										  Date pmnEffThruDate, 
										  String securityGroup)
	{
		try
		{
			//Assign To User
			fndSecurityGroup.insertUserSecurityGroup(userId, 
												     securityGroup,
												     pmnEffFromDate,
												     pmnEffThruDate);
		}
		catch(Exception e)
		{
			if(StringUtils.contains(e.getMessage(), "MFI_20380"))
			{
			    //The user is already in the User Security Group - just update the effectivity dates.
			    fndSecurityGroup.updateUserSecurityGroup(userId, securityGroup, pmnEffFromDate, pmnEffThruDate);  			    
			}
			else
			{			 
			    throw new SoluminaApplicationException(e.getMessage());
			}
		}
	}

	//UTAS-465
	@SuppressWarnings("rawtypes")
	private void assgnWorkLocationSecurityToPlan(String planId,
												 Number planUpdtNo,
												String plannedWorkLocation)
	{
		// Select Security Groups for Assigned Work Location
		List workLocationSecGroupList = paarRequestDao.selectSecurityGroupForAssignedWorkLocation(plannedWorkLocation);

		Iterator workLocationSecGrpIt = workLocationSecGroupList.listIterator();

		while(workLocationSecGrpIt.hasNext())
		{
			Map workLocationSecGrpMap = (Map) workLocationSecGrpIt.next();
			String workLocationSecGrpStr = (String)workLocationSecGrpMap.get("SECURITY_GROUP");

			//Insert Security Group to Plan
			assignSecurityGrpToPlan(planId,planUpdtNo, workLocationSecGrpStr);
		}
	}

	//UTAS-465
	private void assignSecurityGrpToPlan(String planId, 
										 Number planUpdtno,
	                                     String securityGroup) 
	{
	    try
	    {
	        ibtSecurityGroupDao.insertWorkPlanSecurityGroup( planId, planUpdtno,securityGroup, ContextUtil.getUsername());
	    }
	    catch(DataIntegrityViolationException exception)
	    {
	        // Record already exists - do nothing.
	    }
	    
	    ibtSecurityGroupDao.updateSecurityGroupInPlan(planId,planUpdtno, securityGroup, SoluminaConstants.INSERT);
	}
	
		
	private void removeSecurityGroupFromUser(String securityGroup,
	                                         String userId)
	{
	    // Remove the user from the user security group
	    fndSecurityGroup.deleteUserSecurityGroup(userId, securityGroup);	        	                
	}
	
		
	private void processPlanRequestSecurityGroup(String planId,
	                                             Number planVersion,
	                                             Number planRevision,
	                                             Number planAlterations, 
	                                             String planNumber,
	                                             Number planUpdtNo,
	                                             String securityGroup)
    {	    
	    // If the user security group is now empty, then remove from plan and delete it.
        boolean userSecGrpHasUser = paarRequestDao.doesUserSecGrpHaveUser(securityGroup);
        
        if (!userSecGrpHasUser)
        {
            removeSecurityGrpFromPlan(planId,
                                      planVersion,
                                      planRevision,
                                      planAlterations, 
                                      planNumber,
                                      planUpdtNo,                                          
                                      securityGroup);
            
            // If the Security Group is not being used elsewhere, delete it.
            boolean isSecGrpStillInUse = paarRequestDao.selectSecGrpStillInUse(securityGroup);
            
            if (!isSecGrpStillInUse)
            {
                ibtSecurityGroupDao.deleteSecurityGroup(securityGroup);
            }
            
        } 
        else
        {
            // Sec Group has users, we need to assign this Sec Group to all plans with same Plan No.
            assignSecurityGrpToPlan(planId,planUpdtNo, securityGroup);                       
        }	    	    
    }
	
	private void removeWorkLocationSecurityFromPlan(String planId,
	                                                Number planVersion,
	                                                Number planRevision,
	                                                Number planAlterations, 
	                                                String planNumber,
	                                                Number planUpdtNo,                                          
	                                                String workLocation)
	{
	    
	    // Select Security Groups for Assigned Work Location
        List workLocationSecGroupList = paarRequestDao.selectSecurityGroupForAssignedWorkLocation(workLocation);
        Iterator workLocationSecGrpIt = workLocationSecGroupList.listIterator();

        while(workLocationSecGrpIt.hasNext())
        {
            Map workLocationSecGrpMap = (Map) workLocationSecGrpIt.next();
            String workLocSecGrp = (String)workLocationSecGrpMap.get("SECURITY_GROUP");

            //Remove Security Group from Plan
            removeSecurityGrpFromPlan(planId,
                                      planVersion,
                                      planRevision,
                                      planAlterations, 
                                      planNumber,
                                      planUpdtNo,                                          
                                      workLocSecGrp);
        }
	    
	}
	
	private void removeSecurityGrpFromPlan(String planId,
	                                       Number planVersion,
                                           Number planRevision,
                                           Number planAlterations, 
                                           String planNumber,
                                           Number planUpdtNo,                                          
                                           String securityGroup)
	{
	    ibtSecurityGroupDao.deleteWorkPlanSecurityGroup(planId,planUpdtNo, securityGroup);
	    	    
	    ibtSecurityGroupDao.updateSecurityGroupInPlan(planId,planUpdtNo, securityGroup, SoluminaConstants.DELETE);
	}
	
	
	@Override
	public SolPaarWO selectPaarWORequestInfo(String requestId)
	{
		SolPaarWO solPaarWO = new SolPaarWO();
						
		Map requestInfo = paarRequestDao.selectPaarWORequestInfo(requestId);
		if(requestInfo!=null && requestInfo.size()>0)
		{
			// Get Request Info

			/*String requestUserId = (String) requestInfo.get("REQUEST_USERID");
			String requestReason = (String) requestInfo.get("REQUEST_REASON");		
			Date replyRequiredDate = (Date) requestInfo.get("REPLY_REQUIRED_DATE");
			Date requestCreationDate = (Date) requestInfo.get("REQUEST_CREATION_DATE");
			String priorRequestId = (String) requestInfo.get("PRIOR_REQUEST_ID");
			String requestStatus = (String) requestInfo.get("REQUEST_STATUS");

			String orderId = (String) requestInfo.get("ORDER_ID");

			OrderDesc orderDesc = orderId ==null ? null : (OrderDesc)dao.get(OrderDesc.class, orderId);

			// Create SolPaarWO

			solPaarWO.setRequestId(requestId);
			solPaarWO.setRequestUserId(requestUserId);
			solPaarWO.setRequestReason(requestReason);
			solPaarWO.setReplyRequiredDate(replyRequiredDate);
			solPaarWO.setRequestCreationDate(requestCreationDate);
			solPaarWO.setPriorRequestId(priorRequestId);
			solPaarWO.setRequestStatus(requestStatus);
			solPaarWO.setOrderDesc(orderDesc);*/
			
			fetchPaarWORequestInfo(requestInfo, solPaarWO);
		}
		else
		{
			solPaarWO.setRequestId(requestId);
		}
		
		return solPaarWO;
	}
	
	@Override
	public SolPaarWO buildSolPaarWO(String requestId)
	{
		SolPaarWO solPaarWO = null;
		if(StringUtils.isNotEmpty(requestId))
		{
			solPaarWO = new SolPaarWO();
			solPaarWO.setRequestId(requestId);
			
			Map solPaarWORequestInfoMap = paarRequestDao.selectPaarWORequestInfo(requestId);
			
			fetchPaarWORequestInfo(solPaarWORequestInfoMap, solPaarWO);
			
			List<Map> paarWOPermissionList = paarRequestDao.selectPaarWOPermissionList(requestId);
			
			fetchPaarWORequestPermissionInfo(paarWOPermissionList, solPaarWO);
		}
		return solPaarWO;
	}
	private void fetchPaarWORequestInfo(Map solPaarWORequestInfoMap, SolPaarWO solPaarWO)
	{
		if(solPaarWORequestInfoMap!=null && solPaarWORequestInfoMap.size()>0)
		{
			
			String requestUserId = (String) solPaarWORequestInfoMap.get("REQUEST_USERID");
			String requestReason = (String) solPaarWORequestInfoMap.get("REQUEST_REASON");		
			Date replyRequiredDate = (Date) solPaarWORequestInfoMap.get("REPLY_REQUIRED_DATE");
			Date requestCreationDate = (Date) solPaarWORequestInfoMap.get("REQUEST_CREATION_DATE");
			String priorRequestId = (String) solPaarWORequestInfoMap.get("PRIOR_REQUEST_ID");
			String requestStatus = (String) solPaarWORequestInfoMap.get("REQUEST_STATUS");

			String orderId = (String) solPaarWORequestInfoMap.get("ORDER_ID");
			
			OrderDesc orderDesc = orderId ==null ? null : (OrderDesc)dao.get(OrderDesc.class, orderId);
			
			solPaarWO.setRequestUserId(requestUserId);
			solPaarWO.setRequestReason(requestReason);
			solPaarWO.setReplyRequiredDate(replyRequiredDate);
			solPaarWO.setRequestCreationDate(requestCreationDate);
			solPaarWO.setPriorRequestId(priorRequestId);
			solPaarWO.setRequestStatus(requestStatus);
			solPaarWO.setOrderDesc(orderDesc);
		}		
	}
	
	private void fetchPaarWORequestPermissionInfo(List<Map> solPaarWORequestPaermissionList, SolPaarWO solPaarWO)
	{
		if(solPaarWORequestPaermissionList!=null && solPaarWORequestPaermissionList.size()>0)
		{
			// Create Permission List
			List<Permission> suggestedPermissionList = new ArrayList<Permission>(); 
			
			for(Map paarWOPermission : solPaarWORequestPaermissionList)
			{
				String sghtId = (String) paarWOPermission.get("SGHT_ID");
				String pmnAction = (String) paarWOPermission.get("PMN_ACTION");
				String pmnType = (String) paarWOPermission.get("PMN_TYPE");
				String pmnTypeEntity = (String) paarWOPermission.get("PMN_TYPE_ENTITY");
				Date pmnEffFromDate = (Date) paarWOPermission.get("PMN_EFF_FROM_DATE");
				Date pmnEffThruDate = (Date) paarWOPermission.get("PMN_EFF_THRU_DATE");
				String authId = (String) paarWOPermission.get("AUTH_ID");
				
				Permission permission = new Permission();
				
				permission.setAuthId(authId);
				permission.setSghtId(sghtId);
				permission.setAction(pmnAction);
				permission.setType(pmnType);
				permission.setTypeEntity(pmnTypeEntity);
				permission.setStartDate(pmnEffFromDate);
				permission.setEndDate(pmnEffThruDate);
				
				if(StringUtils.equalsIgnoreCase(pmnType, SoluminaConstants.USER))
				{
					// Get User Object
					User user = (User) dao.get(User.class, pmnTypeEntity);
					permission.setUser(user);
				}
				else if(StringUtils.equalsIgnoreCase(pmnType, UtasConstants.LOCATION))
				{
					// Get Location Object
				    String locId = paarRequestDao.selectWorkLocId(pmnTypeEntity);
					WorkLocDef workLoc = (WorkLocDef) dao.get(WorkLocDef.class, locId);
					permission.setWorkloc(workLoc);
				}
				else if(StringUtils.equalsIgnoreCase(pmnType, "Security Group"))
				{
					// Get Security Group Object
					SecurityGroupDef securityGroup = (SecurityGroupDef) dao.get(SecurityGroupDef.class, pmnTypeEntity);
					permission.setSecurityGroup(securityGroup);
				}
				
				suggestedPermissionList.add(permission);											
			}
			SghtPermissionSet sghtPermissionSet = new SghtPermissionSet();
			sghtPermissionSet.setSghtPermissionList(suggestedPermissionList);
			solPaarWO.setSghtPermissionSet(sghtPermissionSet);
		}
		
	}
	
	@Override
	public void authorizedSecGrpToOrder(String requestId)
	{
		Map paarWORequestInfo = paarRequestDao.selectPaarWORequestInfo(requestId);
		
		String orderId = (String)paarWORequestInfo.get("ORDER_ID");
		
		OrderDesc orderDesc = orderId ==null ? null : (OrderDesc)dao.get(OrderDesc.class, orderId);

		String orderNo = StringUtils.EMPTY;
		if(orderDesc!=null)
		{
			orderNo = orderDesc.getOrderNo();
		}
		
		List<Map> paarWORequestPermissionList = paarRequestDao.selectWOAuthorizedProfileForRequestId(requestId);
		
		if (paarWORequestPermissionList.size() > 0)
		{
		    // Set the security group string on OrderDesc to null
		    paarRequestDao.removeAllSecGrpFromOrderDesc(orderId);
		    
		    for(Map paarWOPermission :paarWORequestPermissionList)
		    {
		        String pmnType = (String) paarWOPermission.get("PMN_TYPE");
		        String pmnTypeEntity = (String) paarWOPermission.get("PMN_TYPE_ENTITY");

		        if(StringUtils.equals(pmnType, "Security Group".toUpperCase()))
		        {
		            assignSecurityGrpToOrder(orderNo, pmnTypeEntity);
		        }
		        else if (StringUtils.equals(pmnType, UtasConstants.LOCATION)) 
		        {
		            assgnWorkLocationSecurityToOrder(orderNo, pmnTypeEntity);
		        }
		    }		    		    
		    
		}

		// Now remove any unused security groups from sfwid_order_desc_sec_grp table.
		cleanupOrderDescSecGrp(orderNo);
		
		// Update Request status to COMPLETE
		paarRequestDao.updatePaarWORequestStatus(requestId, SoluminaConstants.COMPLETE.toUpperCase(), null);
		
	}
	
	//UTAS-493
	public void assignSecurityGrpToOrder(String orderNo, 
										 String securityGroup) 
	{
	    // Get the user name
        String userName = ContextUtil.getUsername();
	    
        // Insert the Work Order Security Group.
	    try
	    {	        
	        ibtSecurityGroupDao.insertOrderSecurityGroup(orderNo, securityGroup, userName);
	    }
	    catch (DataIntegrityViolationException dive)
	    {
	        // do nothing - as this just means the record already exists.
	    }
	    catch (Exception e)
	    {
	        throw new SoluminaApplicationException(e.getMessage());	        
	    }
	    
	    // Now add to the security group string on Order Desc
	    ibtSecurityGroupDao.updateSecurityGroupInWorkOrder(orderNo, securityGroup, SoluminaConstants.INSERT);

        // Now set for holds and liens
	    ibtSecurityGroupDao.updateSecurityGroupInWOHolds(orderNo, securityGroup, SoluminaConstants.INSERT);        
	    
	}
	
	//UTAS-493
	public void assgnWorkLocationSecurityToOrder(String orderNo, 
												 String orderPlannedWorkLocation) 
	{
		// Select Security Groups for Assigned Work Location
		List workLocationSecGroupList = paarRequestDao.selectSecurityGroupForAssignedWorkLocation(orderPlannedWorkLocation);

		Iterator workLocationSecGrpIt = workLocationSecGroupList.listIterator();

		while(workLocationSecGrpIt.hasNext())
		{
			Map workLocationSecGrpMap = (Map) workLocationSecGrpIt.next();
			String workLocationSecGrpStr = (String)workLocationSecGrpMap.get("SECURITY_GROUP");

			//Insert Security Group to Plan
			assignSecurityGrpToOrder(orderNo, workLocationSecGrpStr);
		}
	}
	
	private void cleanupOrderDescSecGrp(String orderNo)
	{
	    // Remove any records in Order Desc Sec grp table not referenced in by Order Desc SecGrp record.
	    paarRequestDao.deleteUnusedOrderDescSecGrps(orderNo);
	}
	
	public PaarRequestDao getPaarRequestDao() 
	{
		return paarRequestDao;
	}

	public void setPaarRequestDao(PaarRequestDao paarRequestDao) 
	{
		this.paarRequestDao = paarRequestDao;
	}
}
