/*
* This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    PwmroiOrderSubjectAuthority.java
* 
*  Created: 2020-03-18
* 
*  Author:  X005703
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2020-03-18 	D.Sibrian		 Initial PWU-116F creation
*/

package com.pw.solumina.authority.domain;

import java.util.Date;

public class PwmroiOrderSubjectAuthority implements java.io.Serializable {
	private String planPartNo;
	private String orderId;
	private Number subjectNo;
	private Number subjectRev;
	private String subjectDesc;
	private String authorityType;
	private String authorityDetail;
	private String manualNo;
	private String manualRev;
	private String manualProvider;
	private String manualType;
	private String manualSelection;
	private String sbPartNo;
	private String authorityRev;
	private Date authorityRevDate;
	private String updtUserId;
	private Date timeStamp;
	private String authority;
	private String arcAuthority;
	
	public String getPlanPartNo() {
		return planPartNo;
	}
	public void setPlanPartNo(String planPartNo) {
		this.planPartNo = planPartNo;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Number getSubjectNo() {
		return subjectNo;
	}
	public void setSubjectNo(Number subjectNo) {
		this.subjectNo = subjectNo;
	}
	public Number getSubjectRev() {
		return subjectRev;
	}
	public void setSubjectRev(Number subjectRev) {
		this.subjectRev = subjectRev;
	}
	public String getSubjectDesc() {
		return subjectDesc;
	}
	public void setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
	}
	public String getAuthorityType() {
		return authorityType;
	}
	public void setAuthorityType(String authorityType) {
		this.authorityType = authorityType;
	}
	public String getAuthorityDetail() {
		return authorityDetail;
	}
	public void setAuthorityDetail(String authorityDetail) {
		this.authorityDetail = authorityDetail;
	}
	public String getManualNo() {
		return manualNo;
	}
	public void setManualNo(String manualNo) {
		this.manualNo = manualNo;
	}
	public String getManualRev() {
		return manualRev;
	}
	public void setManualRev(String manualRev) {
		this.manualRev = manualRev;
	}
	public String getManualProvider() {
		return manualProvider;
	}
	public void setManualProvider(String manualProvider) {
		this.manualProvider = manualProvider;
	}
	public String getManualType() {
		return manualType;
	}
	public void setManualType(String manualType) {
		this.manualType = manualType;
	}
	public String getManualSelection() {
		return manualSelection;
	}
	public void setManualSelection(String manualSelection) {
		this.manualSelection = manualSelection;
	}
	public String getSbPartNo() {
		return sbPartNo;
	}
	public void setSbPartNo(String sbPartNo) {
		this.sbPartNo = sbPartNo;
	}
	public String getAuthorityRev() {
		return authorityRev;
	}
	public void setAuthorityRev(String authorityRev) {
		this.authorityRev = authorityRev;
	}
	public Date getAuthorityRevDate() {
		return authorityRevDate;
	}
	public void setAuthorityRevDate(Date authorityRevDate) {
		this.authorityRevDate = authorityRevDate;
	}
	public String getUpdtUserId() {
		return updtUserId;
	}
	public void setUpdtUserId(String updtUserId) {
		this.updtUserId = updtUserId;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getArcAuthority() {
		return arcAuthority;
	}
	public void setArcAuthority(String arcAuthority) {
		this.arcAuthority = arcAuthority;
	}

}
