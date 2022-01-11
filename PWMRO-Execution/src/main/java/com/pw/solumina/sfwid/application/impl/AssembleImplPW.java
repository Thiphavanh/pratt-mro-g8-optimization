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
 *  File:    AssembleImplPW.java
 * 
 *  Created: 2017-08-30
 * 
 *  Author:  Rusty Cannon
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2017-08-30		R. Cannon		Initial Release SMRO_EXPT_205 - assembleText method created
 */

package com.pw.solumina.sfwid.application.impl;

import java.util.Map;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.solumina.sfwid.application.IAssemble;
import com.pw.solumina.sfpl.dao.AssembleTextDaoPW;
import com.pw.solumina.sfwid.dao.OrderAssembleTextDaoPW;

public abstract class AssembleImplPW extends ImplementationOf<IAssemble> implements IAssemble
{
	@Reference private AssembleTextDaoPW planAssembleTxtDaoPW;
	@Reference private OrderAssembleTextDaoPW orderAssembleTxtDaoPW ;

	@SuppressWarnings("rawtypes")
	public String assembleText(String orderLevel,
							   String orderId,
							   String operationNumber,
							   Number operationKey,
							   String stepNumber,
							   Number stepKey,
							   String editMode,
							   String blockType,
							   String currentNavLevelFieldName,
							   String calledFrom,
                               String completeAltId,
                               String groupJobNo)
    {
		String orderText = this.Super.assembleText(orderLevel,
												   orderId,
												   operationNumber,
												   operationKey,
												   stepNumber,
												   stepKey,
												   editMode,
												   blockType,
												   currentNavLevelFieldName,
												   calledFrom,
												   completeAltId,
												   groupJobNo);

		if  (orderAssembleTxtDaoPW.isOrderClassified(orderId))
		{
			//Get the export control information for the plan that the order was created from
	    	Map orderMap = orderAssembleTxtDaoPW.selectExportControlInfoForOrder(orderId);
	    	
	    	String orderNo        = (String) orderMap.get("ORDER_NO");
	    	String jurisdiction	  = (String) orderMap.get("COMMODITY_JURISDICTION");
	    	String classification = (String) orderMap.get("COMMODITY_CLASSIFICATION");
	    	String sme			  = (String) orderMap.get("UCF_PLAN_FLAG1");
	    	String workLoc        = (String) orderMap.get("WORK_LOC");

	    	String application = "G8MROW";
	    	String busUnit = "PWA";
	    	String authLicense = null;
	    	String docId = workLoc + "," + orderNo;		
				
	    	planAssembleTxtDaoPW.insertExportAuditLog(application, busUnit, jurisdiction, classification, sme, authLicense, docId);
		}

    	return orderText;

    } //end assembleText()
	
} //end AssembleImplPW Class
