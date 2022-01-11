package com.pw.solumina.sffnd.dao;

import com.ibaset.common.OutputParameter;
import com.ibaset.common.security.context.ContextUtil;

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
*  File:    IStandardOperationDaoPW.java
* 
*  Created: 2018-03-15
* 
*  Author:  X002475
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	------------------------------------------------------------
* 2018-03-15	B. Preston		Defect 50 		Initial release -- Limit standard operations by work location. 
* 2020-02-07    G.Rodriguez     Defect 1354 - Copy standard Operations does not bring over security groups
* 2020-08-03    D.Miron         Defect 1578 - Added methods insertDataInSfcoreMmObjectGrp and selectDefaultSecurityGroup
* 2021-12-03    S.Edgerly      Defect 1354 - Copy standard Operations now also inserts records into SFCORE_MM_OBJECT_SEC_GRP post copy
*/

// Begin defect 50.
public interface IStandardOperationDaoPW {

    public void insertWorkLocation(String tag, String workLocation);
    public String getLocationId(String workLocation);
	public void insertLocationId(String locId, String planId); 
	public String selectStdOperPlan(String standardOperationTag);//Defect 1354
	public String selectObjectIdFromSFFND_STDOPER(String newPlanId);//Defect 1354
	public String selectWorkLocation(String standardOperationObjectId);//Defect 1354
	public String selectSecurityGroup(String standardOperationObjectId);//Defect 1354
	public void updateSecGrpWorkLoc(String newObjectId, String cacheWorkLoc, String cacheSecurityGroup);//Defect 1354
	public void insertStandardOperationSecurityGroup(String newObjectId,  ///Defect 1354 - 2021-12-03 
													 String standardOperationType, 
													 String standardOperationTag, 
													 Integer standardOperationRevision, 
													 String securityGroup, 
													 String username);
	
	public void insertDataInSfcoreMmObjectGrp(String standardOperationType, 
                                              String standardOperationTag, 
                                              String planRevision, 
                                              String securityGroup); // Defect 1578
	public String selectDefaultSecurityGroup(String workLocationId); // Defect 1578
            
            
	
}
// End defect 50.
