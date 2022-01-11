package com.utas.solumina.sfwid.application.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IUtasOrderDao 
{
        public Date selectDBTimeStamp();
        
        public int deleteWorkOrderSecurityGroup(String orderNumber);
        
        public void updatePAARRequestStatus(String orderId,
                                            String requestStatus);
        
        public String selectExistingRequestIdAsPerStatus(String orderId,
                                                         String requestStatus);
        
        public void insertPaarDesc(String requestId,
                                   String requestStatus,
                                   String requestUser,
                                   String requestReason,
                                   String orderId,
                                   Date replyReqDate,
                                   String priorRequestId);
        
        public void insertSuggestedSecurityGroupProfleFromPastAuthoredProfile(String currentRequestId,
                                                                              String priorRequestId);
        
        public void insertSuggestedSecurityGroupProfleFromPastCancelledProfile(String currentRequestId,
                                                                               String priorRequestId);
        
        public void insertSuggestedSecurityGroupProfleFromOrder(String orderNo, 
                                                                String currentRequestId);
        
        public void updatePAARReviewHeader(String requestId, 
                                           String requestReason,
                                           Date requestRequiredDate);
        
        public void deleteSuggestedPermissionProfileRecord(String sghtId, String requestId);
        
        public void updateSuggestedPermissionProfileRecordToDeleted(String sghtId, String requestId, String pmnAction);
        
        public void insertProfile(String pmnType, 
                                  String pmnTypeEntity, 
                                  String action, 
                                  Date effFromDate, 
                                  Date effThruDate, 
                                  String requestId, 
                                  String updateUserId);
        
        public Map selectDeletedRecordExists(String requestId, String pmnType, String pmnTypeEntity);
        
        public void updateExpirationDate(String requestId, 
                                         String sghtId, 
                                         Date effFromDate, 
                                         Date effThruDate, 
                                         String pmnAction);
        
        public void updateProfile(String requestId, String sghtId, Date effFromDate, Date effThruDate);

        /**
         * UTAS-511
         * @param orderId
         * @return
         */
        public List selectWorkLocSecGrpOfOrder(String orderId);
}
