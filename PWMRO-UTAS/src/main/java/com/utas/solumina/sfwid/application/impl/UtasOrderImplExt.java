package com.utas.solumina.sfwid.application.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.security.SQLSecurityManager;
import com.ibaset.common.util.SoluminaUtils;
import com.ibaset.solumina.exception.SoluminaApplicationException;
import com.ibaset.solumina.sfcore.application.ILicense;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.application.ISecurityGroup;
import com.ibaset.solumina.sffnd.dao.ISecurityGroupDao;
import com.ibaset.solumina.sfwid.dao.IOrderDao;
import com.utas.solumina.common.UtasConstants;
import com.utas.solumina.sfwid.application.IUtasOrderExt;
import com.utas.solumina.sfwid.application.dao.IUtasOrderDao;

public class UtasOrderImplExt implements IUtasOrderExt 
{
	
	@Reference
	private IUtasOrderDao utasOrderDao;
	
	@Reference
	private ISecurityGroupDao fndSecurityGroupDao;
	
	@Reference
	private ILicense license;
	
	@Reference
	private IOrderDao fndOrderDao;
	
	@Reference
	private DataFieldMaxValueIncrementer soluminaSequence;
	
	@Reference
	private ISecurityGroup fndSecurityGroup;
	
	@Reference
	private IMessage message;
	
	@Reference
	IEvent event;
	
	
	public String requestPaarReview(String orderId, 
									String calledFrom)
	{		 
		String requestId = createPaarRequest(orderId, calledFrom);
		
		// If called from Security Group tab, we need to call RefreshTool to make PAAR tab appear. There's no other way.
		if (StringUtils.equalsIgnoreCase("SECURITY_GROUP_TAB", calledFrom))
		{
		    event.refreshTool();
		}
	
		return requestId;
	}

	public String createPaarRequest(String orderId, 
									String calledFrom)
	{
		String requestId = soluminaSequence.nextStringValue();
		String requestStatus = SoluminaConstants.OPEN_STATUS;
		String requestUser = ContextUtil.getUsername();
		String priorRequestId = StringUtils.EMPTY;

		// Query for last prior In Review PAAR request		
		priorRequestId = utasOrderDao.selectExistingRequestIdAsPerStatus(orderId, SoluminaConstants.IN_REVIEW);
		if (StringUtils.isEmpty(priorRequestId))
		{
		    priorRequestId = utasOrderDao.selectExistingRequestIdAsPerStatus(orderId, SoluminaConstants.COMPLETE_UPPERCASE);
		}
				
		// Cancel any in-process requests
        utasOrderDao.updatePAARRequestStatus(orderId, SoluminaConstants.IN_REVIEW);
		
		// Create the PAAR Request
		utasOrderDao.insertPaarDesc(requestId, requestStatus, requestUser, null, orderId, null, priorRequestId);
		
		// Create Sought Profile - Copy all security groups currently assigned to the Work Order.
		String orderNumber = fndOrderDao.selectOrderNumberByOrderId(orderId);
		utasOrderDao.insertSuggestedSecurityGroupProfleFromOrder(orderNumber, requestId);

		return requestId;
	}
	
	public void updatePAARReviewHeader(String requestId, 
			   						   String requestReason,
			   						   Date replyRequiredDate) 
	{
		// Update Header for Request Id
		utasOrderDao.updatePAARReviewHeader(requestId, 
											requestReason, 
											replyRequiredDate);
	}
	
	public void deleteSuggestedPermissionProfile(String requestId, String sghtId, String pmnAction)
	{
	    if (StringUtils.equalsIgnoreCase("ADDED", pmnAction))
	    {
	        // Delete Record
	        utasOrderDao.deleteSuggestedPermissionProfileRecord(sghtId, requestId);
	    }
	    else
	    {
	        // Update Record with Deleted Action
	        utasOrderDao.updateSuggestedPermissionProfileRecordToDeleted(sghtId, requestId, SoluminaConstants.DELETED);
	    }
	}
	
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

			// Insert User Profile
			utasOrderDao.insertProfile(pmnType, 
										pmnTypeEntity, 
										UtasConstants.ADDED, 
										effFromDate, 
										effThruDate, 
										requestId, updateUserId);
		}
		catch(DataIntegrityViolationException daie)
		{
			Map map = utasOrderDao.selectDeletedRecordExists(requestId, pmnType, pmnTypeEntity);
			if(map != null && map.size() > 0)
			{
				String sghtId = (String) map.get("SGHT_ID");
				utasOrderDao.updateExpirationDate(requestId, sghtId, effFromDate, effThruDate, SoluminaConstants.UPDATED);
			}
			else
			{
				message.raiseError("MFI_20", "Record already exists");
			}
		}
	}
	
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

		utasOrderDao.updateProfile(requestId, 
								   sghtId, 
								   pmnEffFromDate, 
								   pmnEffThruDate);
	}
}
