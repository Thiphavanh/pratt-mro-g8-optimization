package com.utas.solumina.paar.domain;

import java.util.Date;

public class SolPaarExternal 
{	
	private AuthPermissionSet authPermissionSet;
	private String requestId;
	private String requestStatus;
	private String requestUserId;
	private Date reqCreationDate;
	private String requestReason;
	private String planNo;
	private Number planVersion;
	private Number planRevision;
	private Number planAlteration;
	private String planType;
	private String planTitle;
	private String bomNo;
	private String bomRev;
	private String facilityId;
	private String facilityDesc;
	private String partNo;
	private String partRev;
	private String partTitle;
	private String partType;
	private String partSubType;
	private Date replyRequiredDate;
	private String priorRequestId;
	private String requestComponentId;
	private String orderNo;
	private String orderStatus;
	private String orderTitle;
	private Date actualStartDate;
	private Date actualEndDate;
	private Date schedStartDate;
	private Date schedEndDate;
	private String approvingUserId;

	public SolPaarExternal() 
	{
		
	}
	
	public SolPaarExternal(
			String requestId, String requestStatus, String reuqestUserId,
			Date reqCreationDate, String requestReason, String planNo,
			Number planVersion, Number planRevision, Number planAlteration,
			String planType, String planTitle, String bomNo, String bomRev,
			String facilityId, String facilityDesc, String partNo,
			String partRev, String partTitle, String partType,
			String partSubType, Date replyRequiredDate, String priorRequestId,
			AuthPermissionSet authPermissionSet, String requestComponentId,
			String orderNo, String orderStatus, String orderTitle,
			Date actualStartDate, Date actualEndDate, Date schedStartDate, Date schedEndDate) {
		
		this.authPermissionSet = authPermissionSet;
		this.requestId = requestId;
		this.requestStatus = requestStatus;
		this.requestUserId = reuqestUserId;
		this.reqCreationDate = reqCreationDate;
		this.requestReason = requestReason;
		this.planNo = planNo;
		this.planVersion = planVersion;
		this.planRevision = planRevision;
		this.planAlteration = planAlteration;
		this.planType = planType;
		this.planTitle = planTitle;
		this.bomNo = bomNo;
		this.bomRev = bomRev;
		this.facilityId = facilityId;
		this.facilityDesc = facilityDesc;
		this.partNo = partNo;
		this.partRev = partRev;
		this.partTitle = partTitle;
		this.partType = partType;
		this.partSubType = partSubType;
		this.replyRequiredDate = replyRequiredDate;
		this.priorRequestId = priorRequestId;
		this.requestComponentId = requestComponentId;
		this.orderNo = orderNo;
		this.orderStatus = orderStatus;
		this.orderTitle = orderTitle;
		this.actualStartDate = actualStartDate;
		this.actualEndDate = actualEndDate;
		this.schedStartDate = schedStartDate;
		this.schedEndDate = schedEndDate;
	}

	public AuthPermissionSet getAuthPermissionSet() 
	{
		return authPermissionSet;
	}
	public void setAuthPermissionSet(AuthPermissionSet authPermissionSet) {
		this.authPermissionSet = authPermissionSet;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}	
	public Date getReqCreationDate() {
		return reqCreationDate;
	}
	public void setReqCreationDate(Date reqCreationDate) {
		this.reqCreationDate = reqCreationDate;
	}
	public String getRequestReason() {
		return requestReason;
	}
	public void setRequestReason(String requestReason) {
		this.requestReason = requestReason;
	}
	public String getPlanNo() {
		return planNo;
	}
	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}
	public Number getPlanVersion() {
		return planVersion;
	}
	public void setPlanVersion(Number planVersion) {
		this.planVersion = planVersion;
	}
	public Number getPlanRevision() {
		return planRevision;
	}
	public void setPlanRevision(Number planRevision) {
		this.planRevision = planRevision;
	}
	public Number getPlanAlteration() {
		return planAlteration;
	}
	public void setPlanAlteration(Number planAlteration) {
		this.planAlteration = planAlteration;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	public String getPlanTitle() {
		return planTitle;
	}
	public void setPlanTitle(String planTitle) {
		this.planTitle = planTitle;
	}
	public String getBomNo() {
		return bomNo;
	}
	public void setBomNo(String bomNo) {
		this.bomNo = bomNo;
	}
	public String getBomRev() {
		return bomRev;
	}
	public void setBomRev(String bomRev) {
		this.bomRev = bomRev;
	}
	public String getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}
	public String getFacilityDesc() {
		return facilityDesc;
	}
	public void setFacilityDesc(String facilityDesc) {
		this.facilityDesc = facilityDesc;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getPartRev() {
		return partRev;
	}
	public void setPartRev(String partRev) {
		this.partRev = partRev;
	}
	public String getPartTitle() {
		return partTitle;
	}
	public void setPartTitle(String partTitle) {
		this.partTitle = partTitle;
	}
	public String getPartType() {
		return partType;
	}
	public void setPartType(String partType) {
		this.partType = partType;
	}
	public String getPartSubType() {
		return partSubType;
	}
	public void setPartSubType(String partSubType) {
		this.partSubType = partSubType;
	}
	public Date getReplyRequiredDate() {
		return replyRequiredDate;
	}
	public void setReplyRequiredDate(Date replyRequiredDate) {
		this.replyRequiredDate = replyRequiredDate;
	}
	public String getPriorRequestId() {
		return priorRequestId;
	}
	public void setPriorRequestId(String priorRequestId) 
	{
		this.priorRequestId = priorRequestId;
	}

	public String getRequestUserId() {
		return requestUserId;
	}

	public void setRequestUserId(String requestUserId) {
		this.requestUserId = requestUserId;
	}

	public String getRequestComponentId() {
		return requestComponentId;
	}

	public void setRequestComponentId(String requestComponentId) {
		this.requestComponentId = requestComponentId;
	}
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderTitle() {
		return orderTitle;
	}

	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public Date getSchedStartDate() {
		return schedStartDate;
	}

	public void setSchedStartDate(Date schedStartDate) {
		this.schedStartDate = schedStartDate;
	}

	public Date getSchedEndDate() {
		return schedEndDate;
	}

	public void setSchedEndDate(Date schedEndDate) {
		this.schedEndDate = schedEndDate;
	}

	public String getApprovingUserId() {
		return approvingUserId;
	}

	public void setApprovingUserId(String approvingUserId) {
		this.approvingUserId = approvingUserId;
	}
	
}
