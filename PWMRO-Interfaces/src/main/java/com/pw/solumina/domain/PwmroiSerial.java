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
*  File:    PwmroiSerial.java
* 
*  Created: 2020-01-24
* 
*  Author:  X005703
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	------------------------------------------------------------------
* 2020-01-24 	D.Sibrian		Initial creation for update of T314 Interface messaging
* 2021-10-29    J DeNinno        Defect 2010 T314 message change
*/

package com.pw.solumina.domain;

import java.util.List;

import com.pw.solumina.authority.domain.PwmroiOrderSubjectAuthority;

public class PwmroiSerial {

	 private List<PwmroiOrderSubjectAuthority> pwmroiOrderSubjectAuthorityList;
	 private List<PwmroiDefect> pwmroiDefect;
	 private String orderId;
	 private String salesOrderNo;
	 private String orderNo;
	 private Number operKey;
	 private String userId;
	 private String date;
	 private String time;
	 private String condition;
	 private String[] engineManual;
	 private String[] engineManualRevNo; //defect 2010
	 private String[] serialNo;
	 private String[] serialId;
	 private Number quantity;
	 private String transType;
	 private String revesalSapId;
	 private String groupCat;
	 private String groupCode;
	 private String causeCat;
	 private String causeCode;
	 private String nonRepairType;
	 private String exchPt;
	 private String zcomSalesOrder;
	 private String zcomSaleOrderText;
	 
	public List<PwmroiOrderSubjectAuthority> getPwmroiOrderSubjectAuthorityList() {
		return pwmroiOrderSubjectAuthorityList;
	}
	public void setPwmroiOrderSubjectAuthorityList(List<PwmroiOrderSubjectAuthority> pwmroiOrderSubjectAuthorityList) {
		this.pwmroiOrderSubjectAuthorityList = pwmroiOrderSubjectAuthorityList;
	}
	public List<PwmroiDefect> getPwmroiDefect() {
		return pwmroiDefect;
	}
	public void setPwmroiDefect(List<PwmroiDefect> pwmroiDefect) {
		this.pwmroiDefect = pwmroiDefect;
	}	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getSalesOrderNo() {
		return salesOrderNo;
	}
	public void setSalesOrderNo(String salesOrderNo) {
		this.salesOrderNo = salesOrderNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}	
	public Number getOperKey() {
		return operKey;
	}
	public void setOperKey(Number operKey) {
		this.operKey = operKey;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String[] getEngineManual() {
		return engineManual;
	}
	public void setEngineManual(String[] engineManual) {
		this.engineManual = engineManual;
	}
	//begin defect 2010
	public String[] getEngineManualRevNo() {
		return engineManualRevNo;
	}
	public void setEngineManualRevNo(String[] engineManualRevNo) {
		this.engineManualRevNo = engineManualRevNo;
	}
	//End defect 2010
	public String[] getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String[] serialNo) {
		this.serialNo = serialNo;
	}
	
	public String[] getSerialId() {
		return serialId;
	}
	public void setSerialId(String[] serialId) {
		this.serialId = serialId;
	}
	public Number getQuantity() {
		return quantity;
	}
	public void setQuantity(Number quantity) {
		this.quantity = quantity;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getRevesalSapId() {
		return revesalSapId;
	}
	public void setRevesalSapId(String revesalSapId) {
		this.revesalSapId = revesalSapId;
	}
	public String getGroupCat() {
		return groupCat;
	}
	public void setGroupCat(String groupCat) {
		this.groupCat = groupCat;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getCauseCat() {
		return causeCat;
	}
	public void setCauseCat(String causeCat) {
		this.causeCat = causeCat;
	}
	public String getCauseCode() {
		return causeCode;
	}
	public void setCauseCode(String causeCode) {
		this.causeCode = causeCode;
	}
	public String getNonRepairType() {
		return nonRepairType;
	}
	public void setNonRepairType(String nonRepairType) {
		this.nonRepairType = nonRepairType;
	}
	public String getExchPt() {
		return exchPt;
	}
	public void setExchPt(String exchPt) {
		this.exchPt = exchPt;
	}
	public String getZcomSalesOrder() {
		return zcomSalesOrder;
	}
	public void setZcomSalesOrder(String zcomSalesOrder) {
		this.zcomSalesOrder = zcomSalesOrder;
	}
	public String getZcomSaleOrderText() {
		return zcomSaleOrderText;
	}
	public void setZcomSaleOrderText(String zcomSaleOrderText) {
		this.zcomSaleOrderText = zcomSaleOrderText;
	}	
	 
}
