/*
*  This unpublished work, first created in 2015 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2015.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    OrderDetailsImplPW.java
* 
*  Created: January 14 2020
* 
*  Author: J DeNinno
* 
*  Revision History
* 
*  Date      	Who             DER/SCR     	Description
*  ----------	---------------	---------------	----------------------------------------------------------------------------------------
*  1/14/2020    John DeNinno	defect 953      update with released by
*  7/28/2021    John DeNinno    defect 1940     release date and release by should only appear when order released
*/

package com.pw.solumina.sfwid.application.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;

import com.ibaset.common.solumina.exception.SoluminaException;
import com.ibaset.solumina.sfwid.application.IOrderDetails;
import com.pw.solumina.sfwid.dao.OrderDaoPW;
import com.pw.solumina.sfwid.dao.OrderDaoPW;

public abstract class OrderDetailsImplPW extends ImplementationOf<IOrderDetails> implements IOrderDetails
{
	@Reference
	public OrderDaoPW orderDaoPW;

	@Override
	public void updateOrderStatus(String orderId, String orderStatus, String statusChangeReason) {

		
		Super.updateOrderStatus(orderId, orderStatus, statusChangeReason);
		
		System.out.println("Order status is - " + orderStatus);
		
		//defect 953 
		if (!orderDaoPW.ReleasedByDateExists(orderId) && StringUtils.equals(orderStatus,"PENDING"))  //defect 1940
		{
		  orderDaoPW.updateReleasedBy(orderId);
		}
		
	}
	
	
}
