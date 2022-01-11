package com.utas.solumina.paar.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.utas.solumina.paar.domain.Permission;
import com.utas.solumina.paar.domain.SolPaarExternal;

public interface PaarRequestDao 
{
	public Map selectRequestInfo(String requestId);
	
	public boolean selectPlgPermissionExists(String sghtId, String requestId, String type, String typeEntity);
	
	public boolean selectWidPermissionExists(String sghtId, String requestId, String type, String typeEntity);
	
	public void insertAuthPermission(String tableName,
									 String requestId,
									 String sghtId,
									 String pmnType,
									 String pmnTypeEntity,
									 String pmnAction,
									 Date effFromDate, Date effThruDate);
	
	public List selectSuggestedPermissionList(String requestId);
	
    public void updateRequestStatus(String requestId, String requestStatus, String authorizationId);
    
    /**
     * UTAS-465
     * 
     * @param requestId
     * @return
     */
    public List selectAuthorizedProfileForRequestId(String requestId);
    
    /**
     * UTAS-465
     * 
     * @param planId
     * @param planVersion
     * @param planRevision
     * @param planAlterations
     * @return
     */
    public Map getGUIDGenSecGroup(String requestId);
    
    /**
     * UTAS-465
     * 
     * @param workLocation
     * @return
     */
    public List selectSecurityGroupForAssignedWorkLocation(String workLocation);
    
    public boolean selectSecurityGroupExists(String securityGroup);
    
    public boolean selectWorkLocationExists(String workLocation);
    
    //UTAS-479
    public void insertExternalPlanPAARDesc(SolPaarExternal solPaarExternal);

    //UTAS-479
    public void insertExternalPlanPAARPermissionAuth(String requestId, Permission permission);

    //UTAS-479
	public List selectPaarExternalPermissionList(String requestId, String calledFrom);

    //UTAS-479
	public Map selectPlanPaarRequestExternalInfo(String requestId);

    //UTAS-479
	public void updateExternalPaarRequestStatus(String requestId, String calledFrom);

	//UTAS-479
	public String selectExternalPaarRequestStatus(String requestId, String calledFrom);
	
	public void insertExternalOrderPAARDesc(SolPaarExternal solPaarExternal);
	
	public void insertExternalOrderPAARPermissionAuth(String requestId, Permission permission);
	
	public Map selectOrderPaarRequestExternalInfo(String requestId);
	
	//UTAS-493
	public Map selectPaarWORequestInfo(String requestId);

	//UTAS-493
	public void updatePaarWORequestStatus(String requestId, String requestStatus, String authorizationId);

	//UTAS-493
	public List selectPaarWOPermissionList(String requestId);

	//UTAS-493
	public List selectWOAuthorizedProfileForRequestId(String requestId);
	
	public boolean selectAtLeastOneSecurityGroupExists(String orderNo);
	
	public boolean orderReqHasOnePositivePerm(String requestId);
	
	public boolean planReqHasOnePositivePerm(String requestId);
	
	public boolean selectSecGrpStillInUse(String securityGroup);
	
	public void deleteAllUsersFromSecGrp(String securityGroup);
	
	public void removeSecGrpFromPlanDesc(String planId,
                                         Number planUpdtNo,                                          
                                         String securityGroup);
	
	public boolean selectSecGrpUsedInOtherPlanVerRev(String planNumber,
	                                                 String securityGroup,
	                                                 String planId, 
	                                                 Number planVersion,
	                                                 Number planRevision,
	                                                 Number planAlterations);

    public void deletePlanDescSecurityGroup(String planNumber,
                                            String securityGroup);
    
    public boolean doesUserSecGrpHaveUser(String securityGroup);
    
    public boolean selectSecGrpExistsInOtherVerRev(String planNumber,
                                                   String securityGroup,
                                                   String planId, 
                                                   Number planVersion,
                                                   Number planRevision,
                                                   Number planAlterations);
    
    public String selectWorkLocId(String workLoc);
    
    public void removeAllSecGrpFromOrderDesc(String orderId);
    
    public void deleteUnusedOrderDescSecGrps(String orderNo);
    
    public void cancelExternalPriorRequestPlg(String priorRequestId);
    
    public void cancelExternalPriorRequestWid(String priorRequestId);
}
