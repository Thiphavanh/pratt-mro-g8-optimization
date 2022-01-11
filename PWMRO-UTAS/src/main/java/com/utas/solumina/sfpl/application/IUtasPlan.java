package com.utas.solumina.sfpl.application;

import java.util.Date;

public interface IUtasPlan 
{

	public void firePaarReviewEvent(String requestId);
	
	public void insertProfile(String requestId, 
			  				  String pmnTypeEntity, 
			  				  Date effFromDate, 
			  				  Date effThruDate,
			  				  String pmnType);
	
	public void deleteSuggestedPermissionProfile(String sghtId, 
												 String requestId, 
												 String pmnAction);
	
	public void updateProfile(String requestId, 
							  String sghtId, 
							  Date pmnEffFromDate, 
							  Date pmnEffThruDate);
	
	public void updatePAARReviewHeader(String requestId,
									   String requestReason,
									   Date replyRequiredDate);
	
	//UTAS-435
	public void importPlan(String pwpId, 
			   			   String fileName, 
			   			   String restricted);
	
	//UTAS-210
	public String requestPaarReview(String planId, 
		    						Number planVersion, 
		    						Number planRevision,
		    						Number planAlteration,
		    						Number planUpdateNumber, 
		    						String calledFrom);
	
    public String requestPaarReviewForNewPlan(String planId, 
                                              Number planVersion, 
                                              Number planRevision,
                                              Number planAlteration,
                                              Number planUpdateNumber);
	
	//UTAS-478
	public void insertApprovalProfile(String requestId, 
			  						  String pmnTypeEntity, 
			  						  String pmnStatus, 
			  						  Date effFromDate,
			  						  Date effThruDate,
			  						  String pmnType);
	
	//UTAS-478
	public void updateApprovalProfile(String requestId, 
			  						  String authId, 
			  						  String pmnStatus,
			  						  Date pmnEffFromDate, 
			  						  Date pmnEffThruDate);
	
	//UTAS-478
	public void deleteApprovalPermissionProfile(String requestId, 
			 									String authId);
	
	//UTAS-496
	public void insertOrdSecGrpApprovalProfile(String requestId, 
									  		   String pmnTypeEntity, 
									  		   String pmnStatus, 
									  		   Date effFromDate,
									  		   Date effThruDate,
									  		   String pmnType);

	//UTAS-496
	public void updateOrdSecGrpApprovalProfile(String requestId, 
											   String authId, 
											   String pmnStatus,
											   Date pmnEffFromDate, 
											   Date pmnEffThruDate);

	//UTAS-496
	public void deleteOrdSecGrpApprovalPermissionProfile(String requestId, 
														 String authId);
	
	public void checkUserSecurityForPlanWorkLoc(String plannedWorkLoc);
	
}
