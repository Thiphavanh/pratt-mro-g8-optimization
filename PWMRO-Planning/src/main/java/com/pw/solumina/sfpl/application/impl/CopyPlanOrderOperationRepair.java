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
 *  File:    CopyPlanOrderOperationRepair.java
 * 
 *  Created: 2018-12-21
 * 
 *  Author:  Fred Ettefagh
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-------------------------------------------------------------------
 *  2018-05-14		Fred Ettefagh	Initial Release SMRO_eWORK_202 - Defect 1044, create repair orders
 *                                  
 */
package com.pw.solumina.sfpl.application.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.solumina.sfpl.application.IPlan;


public class CopyPlanOrderOperationRepair {

	@Reference
	public IPlan plan;
	
	
	@SuppressWarnings("rawtypes")
	public List copyPlanOrderOperationRepair(String targetOperationNumber,
									         String incrementBy,
									         String fromPlanId,
									         Number fromPlanVersion,
									         Number fromPlanRevision,
									         Number fromPlanAlterations,
									         String operationNumberList,
									         Number sequenceNumber,
									         String orderType){

		ArrayList returnArrayList = (ArrayList) plan.copyPlanOrderOperation(targetOperationNumber, 
																                incrementBy, 
																                fromPlanId,
																                fromPlanVersion, 
																                fromPlanRevision, 
																                fromPlanAlterations, 
																                operationNumberList, 
																                sequenceNumber);

		if (StringUtils.equalsIgnoreCase(orderType, "REPAIR")){
			
			for (int i = 0; i < returnArrayList.size(); i++) {
					Map retMap = (Map) returnArrayList.get(i);
					retMap.remove("UCF_ORDER_OPER_VCH2");
					retMap.remove("UCF_ORDER_OPER_VCH3");
			}
		}
		
		return returnArrayList;
	}
}
