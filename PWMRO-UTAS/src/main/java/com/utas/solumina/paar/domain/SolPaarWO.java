package com.utas.solumina.paar.domain;

import java.util.Date;

import com.ibaset.solumina.sfwid.domain.OrderDesc;

public class SolPaarWO 
{
	private String requestId;
	private String requestStatus;
	private String requestUserId;
	private Date requestCreationDate;
	private String requestReason;
	private String orderId;
	private Date replyRequiredDate;
	private String priorRequestId;
	private SghtPermissionSet sghtPermissionSet;
	private OrderDesc orderDesc;
	
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
	public String getRequestUserId() {
		return requestUserId;
	}
	public void setRequestUserId(String requestUserId) {
		this.requestUserId = requestUserId;
	}
	public Date getRequestCreationDate() {
		return requestCreationDate;
	}
	public void setRequestCreationDate(Date requestCreationDate) {
		this.requestCreationDate = requestCreationDate;
	}
	public String getRequestReason() {
		return requestReason;
	}
	public void setRequestReason(String requestReason) {
		this.requestReason = requestReason;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	public void setPriorRequestId(String priorRequestId) {
		this.priorRequestId = priorRequestId;
	}
	public SghtPermissionSet getSghtPermissionSet() {
		return sghtPermissionSet;
	}
	public void setSghtPermissionSet(SghtPermissionSet sghtPermissionSet) {
		this.sghtPermissionSet = sghtPermissionSet;
	}
	public OrderDesc getOrderDesc() {
		return orderDesc;
	}
	public void setOrderDesc(OrderDesc orderDesc) {
		this.orderDesc = orderDesc;
	}
	

}
