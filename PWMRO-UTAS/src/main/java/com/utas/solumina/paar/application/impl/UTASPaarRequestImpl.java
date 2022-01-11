package com.utas.solumina.paar.application.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.event.SoluminaEventPublisher;
import com.ibaset.solumina.domain.dao.DAO;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.domain.SecurityGroupDef;
import com.ibaset.solumina.sffnd.domain.User;
import com.ibaset.solumina.sffnd.domain.WorkLocDef;
import com.utas.solumina.common.UtasConstants;
import com.utas.solumina.paar.application.IUTASPaarRequest;
import com.utas.solumina.paar.dao.PaarRequestDao;
import com.utas.solumina.paar.domain.AuthPermissionSet;
import com.utas.solumina.paar.domain.Permission;
import com.utas.solumina.paar.domain.SolPaarExternal;
import com.utas.solumina.paar.event.UTASPaarReplyExternalEvent;

public class UTASPaarRequestImpl implements IUTASPaarRequest {

	@Reference
	SoluminaEventPublisher soluminaEventPublisher;
	@Reference
	IEvent event;
	@Reference
	PaarRequestDao paarRequestDao;
	@Reference("hibernateDAO")
    private DAO dao;
	@Reference
	IMessage message;
	
	@Override
	public void sendPaarRequestApproval(String requestId, String calledFrom) 
	{
		String requestStatus = paarRequestDao.selectExternalPaarRequestStatus(requestId, calledFrom);
		
		if(StringUtils.equals(requestStatus, SoluminaConstants.COMPLETE_UPPERCASE))
		{
			message.raiseError("HAMSI_1941FC6A354B4D8BE053D20F0A0A9D1C",requestId);
		}
		
		paarRequestDao.updateExternalPaarRequestStatus(requestId, calledFrom);
		
		// Build Message Object
		SolPaarExternal solPaarExternal = buildSolPaarExternal(requestId, calledFrom);
		
		// Check for at least one positive Permission Status in the Approval
		if(StringUtils.equals(calledFrom, UtasConstants.EXECUTION_PROPER_CASE))
		{
			checkOrderHasSecurityGroup(solPaarExternal);
		}
		else
		{
		    checkPlanHasSecurityGroup(solPaarExternal);
		}

		// Build Event Object
		UTASPaarReplyExternalEvent paarReplyExternalEvent = new UTASPaarReplyExternalEvent(this);

		// Set event object
		paarReplyExternalEvent.addSolPaar(solPaarExternal);

		// Fire Event
		soluminaEventPublisher.publishEvent(paarReplyExternalEvent);						
		
		// Refresh Tool
		event.refreshTool();

	}
	
    @SuppressWarnings("rawtypes")
    private void checkPlanHasSecurityGroup(SolPaarExternal solPaarExternal) 
    {
        // So as not to remove all security from Plan, check that there is at least one positively approved permission
        boolean permitPermissionExists = paarRequestDao.planReqHasOnePositivePerm(solPaarExternal.getRequestId());

        if(!permitPermissionExists)
        {
            message.raiseError("HAMSI_19F82CFBEBDE5BA7E053D20F0A0AC277", "Plan No: " + solPaarExternal.getPlanNo());
        }       
    }	
		
	@SuppressWarnings("rawtypes")
	private void checkOrderHasSecurityGroup(SolPaarExternal solPaarExternal) 
	{
	    // So as not to remove all security from Order, check that there is at least one positively approved permission
	    boolean permitPermissionExists = paarRequestDao.orderReqHasOnePositivePerm(solPaarExternal.getRequestId());

	    if(!permitPermissionExists)
	    {
	        message.raiseError("HAMSI_19F82CFBEBDE5BA7E053D20F0A0AC277", "Order No: "+ solPaarExternal.getOrderNo());
	    }		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SolPaarExternal buildSolPaarExternal(String requestId, String calledFrom) 
	{
		Map requestInfo = new HashMap();
		if(StringUtils.equals(calledFrom, UtasConstants.PLANNING_PROPER_CASE))
		{
			requestInfo = paarRequestDao.selectPlanPaarRequestExternalInfo(requestId);
		}
		else if(StringUtils.equals(calledFrom, UtasConstants.EXECUTION_PROPER_CASE))
		{
			requestInfo = paarRequestDao.selectOrderPaarRequestExternalInfo(requestId);
		}
		
		// Get Suggested Permission List
		List<Map> suggestedPermissionList = paarRequestDao.selectPaarExternalPermissionList(requestId, calledFrom);
		
		// Create Permission List
		List<Permission> xmlSuggestedPermissionList = new ArrayList<Permission>(); 
		
		// Build Permission Object
		for(Map suggestedPermission : suggestedPermissionList)
		{
			String sghtId = (String) suggestedPermission.get("SGHT_ID");
			String pmnAction = (String) suggestedPermission.get("PMN_ACTION");
			String pmnType = (String) suggestedPermission.get("PMN_TYPE");
			String pmnStatus = (String) suggestedPermission.get("PMN_STATUS");
			
			String pmnTypeEntity = (String) suggestedPermission.get("PMN_TYPE_ENTITY");
			Date pmnEffFromDate = (Date) suggestedPermission.get("PMN_EFF_FROM_DATE");
			Date pmnEffThruDate = (Date) suggestedPermission.get("PMN_EFF_THRU_DATE");
			String authId = (String) suggestedPermission.get("AUTH_ID");
			
			Permission permission = new Permission();
			permission.setAuthId(authId);
			permission.setSghtId(sghtId);
			permission.setPermissionStatus(pmnStatus);
			permission.setType(pmnType);
			permission.setTypeEntity(pmnTypeEntity);
			permission.setAction(pmnAction);
			permission.setStartDate(pmnEffFromDate);
			permission.setEndDate(pmnEffThruDate);
			
			if(StringUtils.equalsIgnoreCase(pmnType, SoluminaConstants.USER)
					&& StringUtils.equals(calledFrom, UtasConstants.PLANNING_PROPER_CASE))
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
				
		String requestUserId = (String) requestInfo.get("REQUEST_USERID");
		String requestReason = (String) requestInfo.get("REQUEST_REASON");		
		Date replyRequiredDate = (Date) requestInfo.get("REPLY_REQUIRED_DATE");
		Date requestCreationDate = (Date) requestInfo.get("REQUEST_CREATION_DATE");
		String priorRequestId = (String) requestInfo.get("PRIOR_REQUEST_ID");
		String requestStatus = (String) requestInfo.get("REQUEST_STATUS");
		
		// Create SolPaarExternal
		SolPaarExternal solPaarExternal = new SolPaarExternal();
		
		solPaarExternal.setRequestId(requestId);
		solPaarExternal.setRequestUserId(requestUserId);
		solPaarExternal.setRequestReason(requestReason);
		solPaarExternal.setReplyRequiredDate(replyRequiredDate);
		solPaarExternal.setReqCreationDate(requestCreationDate);
		solPaarExternal.setPriorRequestId(priorRequestId);
		solPaarExternal.setRequestStatus(requestStatus);
		solPaarExternal.setRequestComponentId(calledFrom);
		
		if(StringUtils.equals(calledFrom, UtasConstants.PLANNING_PROPER_CASE))
		{
			String planNo = (String) requestInfo.get("PLAN_NO");
			Number planVersion = (Number) requestInfo.get("PLAN_VERSION");
			Number planRevision = (Number) requestInfo.get("PLAN_REVISION");
			Number planAlterations = (Number) requestInfo.get("PLAN_ALTERATIONS");
			
			solPaarExternal.setPlanNo(planNo);
			solPaarExternal.setPlanRevision(planRevision);
			solPaarExternal.setPlanVersion(planVersion);
			solPaarExternal.setPlanAlteration(planAlterations);
		}
		else if(StringUtils.equals(calledFrom, UtasConstants.EXECUTION_PROPER_CASE))
		{
			String orderNo = (String) requestInfo.get("ORDER_NO");
			String orderStatus = (String) requestInfo.get("ORDER_STATUS");
			String orderTitle = (String) requestInfo.get("ORDER_TITLE");
			Date actualStartDate = (Date) requestInfo.get("ACTUAL_START_DATE");
			Date actualEndDate = (Date) requestInfo.get("ACTUAL_END_DATE");
			Date schedStartDate = (Date) requestInfo.get("SCHED_START_DATE");
			Date schedEndDate = (Date) requestInfo.get("SCHED_END_DATE");
			
			solPaarExternal.setOrderNo(orderNo);
			solPaarExternal.setOrderStatus(orderStatus);
			solPaarExternal.setOrderTitle(orderTitle);
			solPaarExternal.setActualStartDate(actualStartDate);
			solPaarExternal.setActualEndDate(actualEndDate);
			solPaarExternal.setSchedStartDate(schedStartDate);
			solPaarExternal.setSchedEndDate(schedEndDate);
		}
		
		// Create Permission Object
		AuthPermissionSet authPermissionSet = new AuthPermissionSet();
		authPermissionSet.setSghtPermissionList(xmlSuggestedPermissionList);		

		solPaarExternal.setAuthPermissionSet(authPermissionSet);
		
		return solPaarExternal;
	}

}
