package com.pw.solumina.sffnd.application;

import com.ibaset.common.OutputParameter;

/*
* This unpublished work, first created in 2018 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2018.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    IStandardOperationPW.java
* 
*  Created: 2018-03-15
* 
*  Author:  X002475
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	------------------------------------------------------------
* 2018-03-15	B. Preston		Defect 50		Initial release -- Limit standard operations by work location.
* 2020-02-07    G.Rodriguez     Defect 1354 - Copy standard Operations does not bring over security groups
*/

public interface IStandardOperationPW {

	// Begin defect 50.
	public void createStandardOperation(String tag, String description, String releasePackage,
			String standardOperationType, String workFlow, String commodityJurisdiction, String commodityClassification,
			String associateChange, String workLocation, String planId);
	// End defect 50.
	
	//Begin defect 1354
	public void copyStandardOperationPlan2(String standardOperationObjectId,
            String standardOperationTag,
            String standardOperationDescription,
            String folder,
            String standardOperationType,
            String fromPartNumber,
            String fromPartChange,
            OutputParameter planId,
            OutputParameter planVersion,
            OutputParameter planRevision,
            OutputParameter planAlterations,
            OutputParameter invokeToolMode,
            String releasePackage,
            String workFlow,
            String commodityJurisdiction,
            String commodityClassification, 
            String associateChange,
            String securityGroup, 
            String userId,
            Number stdOperKey);
	//End defect 1354
	
}
