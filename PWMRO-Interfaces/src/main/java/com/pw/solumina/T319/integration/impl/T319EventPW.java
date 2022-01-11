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
*  File:    T319EventPW.java
* 
*  Created: 2019-06-05
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2019-06-04 	Fred Ettefagh       Initial Release for PW_SMRO_TR_319
* 2019-06-10    Fred Ettefagh	    added order work loc to event class
* 2019-09-12    Fred Ettefagh       SMRO_TR_319, added code for SCR-001 
* 2020-08-18    D.Miron             SMRO_TR_319 Defect 1757 - Added setters/getters for planItemNo.
*/
package com.pw.solumina.T319.integration.impl;

import com.ibaset.common.event.SoluminaApplicationEvent;

public class T319EventPW extends SoluminaApplicationEvent{

	private static final long serialVersionUID = 1641222765037029574L;
	
	
	String workOrderNumber;
	String plantCode;
	Number orderQty;
	String planNo;
	int   numberOfSerialNo;
	String[] serialNoArray  = new String[numberOfSerialNo]; 

	int   numberOfTaskGroups;
	String[] taskGroupArray = new String[numberOfTaskGroups];
	String workLocation;
	String sapOrderType;
	String sapOrderTypeIndicator;
	String planItemNo;
	
	public String getSapOrderType() {
		return sapOrderType;
	}

	public void setSapOrderType(String sapOrderType) {
		this.sapOrderType = sapOrderType;
	}

	public String getSapOrderTypeIndicator() {
		return sapOrderTypeIndicator;
	}

	public void setSapOrderTypeIndicator(String sapOrderTypeIndicator) {
		this.sapOrderTypeIndicator = sapOrderTypeIndicator;
	}
	
	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public T319EventPW(Object source) {
		super(source);
	}
	
	public String getWorkOrderNumber() {
		return workOrderNumber;
	}

	public void setWorkOrderNumber(String workOrderNumber) {
		this.workOrderNumber = workOrderNumber;
	}

	public String getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}

	public Number getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(Number orderQty) {
		this.orderQty = orderQty;
	}

	public int getNumberOfSerialNo() {
		return numberOfSerialNo;
	}

	public void setNumberOfSerialNo(int numberOfSerialNo) {
		this.numberOfSerialNo = numberOfSerialNo;
	}

	public String[] getSerialNoArray() {
		return serialNoArray;
	}

	public void setSerialNoArray(String[] serialNoArray) {
		this.serialNoArray = serialNoArray;
	}

	public int getNumberOfTaskGroups() {
		return numberOfTaskGroups;
	}

	public void setNumberOfTaskGroups(int numberOfTaskGroups) {
		this.numberOfTaskGroups = numberOfTaskGroups;
	}

	public String[] getTaskGroupArray() {
		return taskGroupArray;
	}

	public void setTaskGroupArray(String[] taskGroupArray) {
		this.taskGroupArray = taskGroupArray;
	}

	public String getPlanNo() {
		return planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	public String getPlanItemNo() {
		return planItemNo;
	}

	public void setPlanItemNo(String planItemNo) {
		this.planItemNo = planItemNo;
	}
	
}
