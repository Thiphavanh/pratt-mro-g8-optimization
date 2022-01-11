package com.utas.solumina.sfpl.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface IUtasPlanDao 
{
    /**
     * 
     * @param objectId
     * @return
     */
    public String getEmbeddedObjectId (String objectId);
    
    /**
     * 
     * @param objectId
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Map selectMMObjectHint (String objectId);
    
    /**
     * UTAS-210
     * 
     * @param workLocation
     * @return
     */
    public List selectSecurityGroupForAssignedWorkLocation(String workLocation);
    
    /**
     * UTAS-210
     * @param workLocation
     * 
     * @return
     */
    public String selectDefaultSecurityGroup(String workLocation);
       
    /**
     * UTAS-210
     * 
     * @param requestId
     * @param requestStatus
     * @param requestUser
     * @param requestReason
     * @param planId
     * @param planVer
     * @param planRev
     * @param planAlt
     * @param replyReqDate
     * @param priorRequestId
     */
    public void insertPaarDesc(String requestId,
    						   String requestStatus,
    						   String requestUser,
    						   String requestReason,
    						   String planId,
    						   Number planVer,
    						   Number planRev,
    						   Number planAlt,
    						   Date replyReqDate,
    						   String priorRequestId,
    						   String guidSecurityGroup);
    
    
    public Map selectLastPlanRequest(String planId);
    
    /**
     * UTAS-210
     * 
     * @param planId
     * @param planVer
     * @param planRev
     * @param planAlt
     * @param requestStatus
     * @return
     */
    public String selectExistingRequestIdAsPerStatus(String planId, 
    		 										 Number planVer,
    		 										 Number planRev,
    		 										 Number planAlt,
    		 										 String requestStatus);
    
    /**
     * UTAS-210
     * 
     * @param currentRequestId
     * @param priorRequestId
     */
    public void insertSuggestedSecurityGroupProfleFromPastCancelledProfile(String currentRequestId,
			   															   String priorRequestId);
    /**
     * UTAS-210
     * 
     * @param currentRequestId
     * @param priorRequestId
     */
    public void insertSuggestedSecurityGroupProfleFromPastAuthoredProfile(String currentRequestId,
    																	  String priorRequestId);
    
    /**
     * UTAS-210
     * 
     * @param planId
     * @param planUpdateNumber
     * @return
     */
    public String selectPlanNumber(String planId,
			 					   Number planUpdateNumber);
    
    /**
     * UTAS-210
     * 
     * @param planNumber
     * @return
     */
    public int deleteWorkPlanSecurityGroup(String planNumber);
    
    /**
     * UTAS-210
     * 
     * @return
     */
    public Date selectDBTimeStamp();
    
    /**
     * UTAS-210
     * 
     * @param planId
     * @param planVer
     * @param planRev
     * @param planAlt
     * @param requestStatus
     */
    public void updatePAARRequestStatus(String planId, 
			 						   	Number planVer,
			 						   	Number planRev,
			 						   	Number planAlt,
			 						   	String requestStatus);
    
    public void cancelAnyInReviewPlgRequests(String planId);
    
    /**
     * UTAS-450
     * 
     * @param pmnType
     * @param pmnTypeEntity
     * @param pmnAction TODO
     * @param effFromDate
     * @param effThruDate
     * @param requestId
     * @param updateUserId
     */
    public void insertProfile(String pmnType, 
    						  String pmnTypeEntity, 
    						  String pmnAction, 
    						  Date effFromDate, 
    						  Date effThruDate, 
    						  String requestId,
    						  String updateUserId);
    
    /**
     * UTAS-450
     * @param sghtId
     * @param requestId
     */
    public void deleteSuggestedPermissionProfileRecord(String sghtId, String requestId);
    
    /**
     * UTAS-450
     * @param sghtId
     * @param requestId
     * @param pmnAction
     */
    public void updateSuggestedPermissionProfileRecordToDeleted(String sghtId, String requestId, String pmnAction);
    
    /**
     * UTAS-450
     * @param requestId
     * @param requestReason
     * @param requestRequiredDate
     */
    public void updatePAARReviewHeader(String requestId, 
    								   String requestReason,
    								   Date requestRequiredDate);
    
    /**
     * UTAS-450
     * @param requestId
     * @param sghtId
     * @param effFromDate
     * @param effThruDate
     */
    public void updateProfile(String requestId, String sghtId, Date effFromDate, Date effThruDate);
    
    /**
     * UTAS-450
     * @param requestId
     * @param pmnType
     * @param pmnTypeEntity
     * @return TODO
     */
    public Map selectDeletedRecordExists(String requestId, String pmnType, String pmnTypeEntity);
    
    /**
     * UTAS-450
     * @param requestId
     * @param sghtId
     * @param effFromDate
     * @param effThruDate
     * @param pmnAction
     */
    public void updateExpirationDate(String requestId, 
    								 String sghtId, 
    								 Date effFromDate, 
    								 Date effThruDate, 
    								 String pmnAction);
    
    /**
     * UTAS-435
     * 
     * @param planNumber
     * @param planUpdateNumber
     * @return
     */
    public Map selectPlanInfoByPlanNumber(String planNumber, 
			  							  Number planUpdateNumber);
    
    /**
     * UTAS-478
     * 
     * @param pmnType
     * @param pmnTypeEntity
     * @param pmnStatus
     * @param effFromDate
     * @param effThruDate
     * @param requestId
     * @param updateUserId
     */
    public void insertApprovalProfile(String pmnType, 
			  						  String pmnTypeEntity, 
			  						  String pmnStatus, 
			  						  Date effFromDate, 
			  						  Date effThruDate, 
			  						  String requestId, 
			  						  String updateUserId);

    /**
     * UTAS-478
     * 
     * @param requestId
     * @param pmnType
     * @param pmnTypeEntity
     * @return
     */
    public Map selectDeletedApprovalRecordExists(String requestId, String pmnType, String pmnTypeEntity);
    
    
    /**
     * UTAS-478
     * 
     * @param requestId
     * @param authId
     * @param pmnStatus
     * @param effFromDate
     * @param effThruDate
     * @param pmnAction
     */
    public void updateApprovalProfileExpirationDate(String requestId, 
			 										String authId, 
			 										String pmnStatus, 
			 										Date effFromDate, 
			 										Date effThruDate, 
			 										String pmnAction);
    
    /**
     * UTAS-478
     * 
     * @param requestId
     * @param authId
     * @param pmnStatus
     * @param effFromDate
     * @param effThruDate
     */
    public void updateApprovalProfile(String requestId, 
    								  String authId, 
    								  String pmnStatus, 
    								  Date effFromDate, 
    								  Date effThruDate);
    
    /**
     * UTAS-478
     * 
     * @param authId
     * @param requestId
     */
    public void deleteApprovalPermissionProfileRecord(String authId, 
    												  String requestId);
    
    /**
     * UTAS-496
     * 
     * @param pmnType
     * @param pmnTypeEntity
     * @param pmnStatus
     * @param effFromDate
     * @param effThruDate
     * @param requestId
     * @param updateUserId
     */
    public void insertOrdSecGrpApprovalProfile(String pmnType, 
			  								   String pmnTypeEntity, 
			  								   String pmnStatus, 
			  								   Date effFromDate, 
			  								   Date effThruDate, 
			  								   String requestId, 
			  								   String updateUserId);
    
    /**
     * UTAS-496
     * 
     * @param requestId
     * @param authId
     * @param pmnStatus
     * @param effFromDate
     * @param effThruDate
     * @param pmnAction
     */
    public void updateOrdSecGrpApprovalProfileExpirationDate(String requestId, 
															 String authId, 
															 String pmnStatus, 
															 Date effFromDate, 
															 Date effThruDate, 
															 String pmnAction);
    
    /**
     * UTAS-496
     * 
     * @param requestId
     * @param authId
     * @param pmnStatus
     * @param effFromDate
     * @param effThruDate
     */
    public void updateOrdSecGrpApprovalProfile(String requestId, 
			  								   String authId, 
			  								   String pmnStatus, 
			  								   Date effFromDate, 
			  								   Date effThruDate);
    
    /**
     * UTAS-496
     * 
     * @param authId
     * @param requestId
     */
    public void deleteOrdSecGrpApprovalPermissionProfileRecord(String authId, 
			  												   String requestId);
    
    /**
     * UTAS-511
     * @param planNumber
     * @return
     */
    public List selectNonRestrictedSecGrp(String planId, Number planUpdtNo);

	//UTAS-545
	public String selectPlanWorkLocation(String planId, Number planUpdateNumber);
    
    /**
     * 
     * @param planId
     * @param planUpdateNumber
     * @return
     */
    public String selectRestrictedSecurityGroup(String planId,
    												   Number planUpdateNumber);
    
    /**
     * 
     * @param planId
     * @param planUpdateNumber
     * @param securityGroup
     */
    public void updatePlanDescSecurityGroup(String planId, 
    										Number planUpdateNumber, 
    										String securityGroup);
    
    /**
     * 
     * @param planId
     * @param planUpdateNumber
     * @param planVersion
     * @param securityGroup
     * @return
     */
    public boolean selectRestrictedSecurityGroupExistsInOtherRevision(String planNumber,
                                                                      String securityGroup,
                                                                      String planId, 
                                                                      Number planVersion,
                                                                      Number planRevision,
                                                                      Number planAlterations);
    
    /**
     * 
     * @param planNumber
     * @param securityGroup
     */
    public void deletePlanDescReferenceSecurityGroup(String planNumber,
    												 String securityGroup);
    
    /**
     * 
     * @param securityGroup
     * @return
     */
    public List selectRestrictedSecurityGroupUserList(String securityGroup);
    
    /**
     * 
     * @param userId
     * @return
     */
    public List selectSecurityGroupListAssignedForUser(String userId);
    
    /**
     * 
     * @param requestId
     * @param pmnType
     * @param pmnTypeEntity
     * @param pmnAction
     * @param effFromDate
     * @param effThruDate
     */
    public void updateUserProfile(String requestId,
								  String pmnType,
								  String pmnTypeEntity, 
								  String pmnAction,
								  Date effFromDate, 
								  Date effThruDate);
    
    public void deleteAllSecurityForPlan(String planNumber);
    
}
