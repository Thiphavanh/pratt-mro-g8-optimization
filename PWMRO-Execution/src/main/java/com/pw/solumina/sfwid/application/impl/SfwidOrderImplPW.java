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
*  File:    SfwidOrderImplPW.java
*  
*  PW_SMRO_WOE_310_Order_Creation
* 
*  Created: 2019.04.12
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date             Who            Description
*  -----------    ------------     ---------------------------------------------------------------------
*  2019-05-03     Fred Ettefagh    PW_SMRO_WOE_310, if deleting a repair WO, then update repair interface table flat to N
*  2019-10-24     Fred Ettefagh    PW_SMRO_WOE_310, removed event.refreshTool(); 
*/
package com.pw.solumina.sfwid.application.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sfwid.application.ISfwidOrder;
import com.pw.common.dao.CommonDaoPW;

public abstract class SfwidOrderImplPW extends ImplementationOf<ISfwidOrder> implements ISfwidOrder{

	@Reference public CommonDaoPW commonDaoPW;
	@Reference private IEvent event = null;
	
	@SuppressWarnings("rawtypes")
	public void deleteOrder(String orderId, String refresh){

		String orderType = null;
		
		List sfwidOrderDesclist = commonDaoPW.selectSfwidOrderDesc(orderId);
		if (sfwidOrderDesclist!=null && !sfwidOrderDesclist.isEmpty()){
			Map sfwidOrderDescMap = (Map)sfwidOrderDesclist.get(0);
			orderType = (String)sfwidOrderDescMap.get("ORDER_TYPE");
		}
	
		Super.deleteOrder(orderId, refresh);
			
		if (StringUtils.equalsIgnoreCase(orderType, "REPAIR")){
			commonDaoPW.updatePwustIntRepairWoData(orderId, "N");
		}
	}
}
