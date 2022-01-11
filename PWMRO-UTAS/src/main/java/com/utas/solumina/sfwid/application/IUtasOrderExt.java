package com.utas.solumina.sfwid.application;

import java.util.Date;

public interface IUtasOrderExt 
{
	public String requestPaarReview(String orderId, 
									String calledFrom);
	
	public void updatePAARReviewHeader(String requestId, 
									   String requestReason,
									   Date replyRequiredDate);
	
	public void deleteSuggestedPermissionProfile(String requestId, String sghtId, String pmnAction);
	
	public void insertProfile(String requestId, 
								  String pmnTypeEntity, 
								  Date effFromDate, 
								  Date effThruDate,
								  String pmnType);
	
	public void updateProfile(String requestId, 
							  String sghtId, 
							  Date pmnEffFromDate, 
							  Date pmnEffThruDate);
}
