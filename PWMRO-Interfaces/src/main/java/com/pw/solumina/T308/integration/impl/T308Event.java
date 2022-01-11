/*
* This unpublished work, first created in 2019 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2019.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    T308EventPW.java
* 
*  Created: 2019-08-01
* 
*  Author:  Raeann Thorpe
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2019-08-01 	Raeann Thorpe   Initial Release for PW_SMRO_TR_308
* 2019-09-20    Bahram J.       SMRO_TR_308, changed xml to use synWorkOrder instead of syncRecord
*/
package com.pw.solumina.T308.integration.impl;

import com.ibaset.common.event.SoluminaApplicationEvent;

public class T308Event extends SoluminaApplicationEvent
{

private static final long serialVersionUID = 1641222765037029574L;
	
public T308Event(Object source) 
{
	super(source);
}
	//Header Info
	String workOrderNumber;
	String orderId;
	String plantCode;
	Number orderQty;
	String planNo;
	String workLocation;
	String orderPartNo;
	
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getWorkOrderNumber() 
	{
		return workOrderNumber;
	}

	public void setWorkOrderNumber(String workOrderNumber) 
	{
		this.workOrderNumber = workOrderNumber;
	}

	public String getPlanNo() 
	{
		return planNo;
	}

	public void setPlanNo(String planNo) 
	{
		this.planNo = planNo;
	}

	public String getOrderPartNo() 
	{
		return orderPartNo;
	}

	public void setOrderPartNo(String orderPartNo) 
	{
		this.orderPartNo = orderPartNo;
	}
	public String getWorkLocation()
	{
		return workLocation;
	}

	public void setWorkLocation(String workLocation) 
	{
		this.workLocation = workLocation;
	}

	public Number getOrderQty() 
	{
		return orderQty;
	}

	public void setOrderQty(Number orderQty) 
	{
		this.orderQty = orderQty;
	}
	
}

	
