package com.pw.solumina.sfwid.application.impl;
/*
 *  This unpublished work, first created in 2019 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2019.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    AuthorityDao.java
 * 
 *  Created: April 2019
 * 
 *  Author:  Marcel LeClerc (iBaset)
 * 
 *  Revision History
 * 
 *  Date        Who          	Description
 *  ----------	--------------	-----------------------------------------------------------
 *  04-02-2019  D.Miron         Initial Release PWU-116 Task Authority
 *  09-13-2019  D.Miron         Updated for PWU-116C release
*/

import com.ibaset.solumina.sfwid.application.IUnitWorkHistory;
import com.ibaset.common.ImplementationOf;

public abstract class PWMROIUnitWorkHistoryImpl extends ImplementationOf<IUnitWorkHistory> implements IUnitWorkHistory
{
//	private static final String BUYOFF_EMBBED_UDV_OOB = "MFI_1002728";
	private static final String BUYOFF_EMBBED_UDV_OOB = "MF1_1000529";
	private static final String BUYOFF_EMBBED_UDV_CFG = "PWMROI_52A97F49227B4F0590615D4C75930075";
	
	public String OrderOperationTextGet(String orderId,
            							Number operKey,
            							Number stepKey,
            							String textType,
            							boolean operationTextFlag,
            							boolean ackMaterialFlag)
	{
		
		String orderOperationText = Super.OrderOperationTextGet(orderId,
										   						operKey,
										   						stepKey,
										   						textType,
										   						operationTextFlag,
										   						ackMaterialFlag);
		
		return orderOperationText.replace(BUYOFF_EMBBED_UDV_OOB + "(@CONTROL=", BUYOFF_EMBBED_UDV_CFG + "(@CONTROL=");
	}
}