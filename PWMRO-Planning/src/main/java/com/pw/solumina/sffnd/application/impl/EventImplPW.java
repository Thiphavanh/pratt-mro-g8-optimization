/*
*  This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    OrderMROOverhaulPW.java
*  
*  PW_SMRO_eWORK_202-Order-Creation
* 
*  Created: 2017.08.09
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date             Who            Description
*  -----------    ------------     ---------------------------------------------------
*  2018-03-08       Fred Ettefagh  
*  2018-03-26       Fred Ettefagag defect 412  change how overhaul overs are created via input matrix
*  2018-03-26       Fred Ettefagag defect 412  change how overhaul overs are created via input matrix
*  2019-08-20     D.Miron          SMRO_WOE_304 Sub Orders - Added check for Order Type = REPAIR
*/
package com.pw.solumina.sffnd.application.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sfwid.dao.IOrderDao;

public abstract class EventImplPW extends ImplementationOf<com.ibaset.solumina.sffnd.application.IEvent> implements IEvent{
	
	@Reference private IOrderDao orderDao = null;
	
	public void launchMROManufacturing(String orderId){
		String lunchMroOverHaulFlag = (String)ContextUtil.getUser().getContext().get("LAUNCH_ORDER");
		
		Map orderDescription = orderDao.selectOrderDescription(orderId);
		String orderType = (String)orderDescription.get("ORDER_TYPE");

		if (StringUtils.equalsIgnoreCase(lunchMroOverHaulFlag, "TRUE") || 
				"REPAIR".equals(orderType.toUpperCase())){
			Super.launchMROManufacturing(orderId);
		}
    }
}
