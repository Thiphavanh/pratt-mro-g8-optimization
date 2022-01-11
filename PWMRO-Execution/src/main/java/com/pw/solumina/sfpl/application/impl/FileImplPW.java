/*
 * This unpublished work, first created in 2017 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2021.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    FileImplPW.java
 * 
 *  Created: 2021-05-28
 * 
 *  Author:  X007084
 * 
 *  Revision History
 * 
 *  Date      	Who                	Description
 *  ----------	---------------	---------------	---------------------------------------------------
 * 2021-05-28 		X007084		    Initial Release defect 1906
 */

package com.pw.solumina.sfpl.application.impl;
import com.ibaset.solumina.sffnd.application.ISecurityGroup;
import com.ibaset.solumina.sfpl.application.IFile;
import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;

public class FileImplPW {

	@Reference
	IFile ifile;
	
	@Reference
	ISecurityGroup iSecurityGroup;
	
	 public void insertFileMaster(String objectId,
             String newObjectId,
             String generatingSystem,
             String obsoleteRecordFlag,
             String storeInDatabase,
             String insertFlag,
             String objectTag,
             String classification,
             String releasePackageId,
             String changeRequestId,
             String plannedActionId,
             String commodityJurisdiction,
             String commodityClassification, 
             String associateChange,  
             String callFrom, 
             String workFlow,
             String securityGroup) 	{
		 
		    System.out.println("insertFileMaster - objectId - " + newObjectId);
		    System.out.println("insertFileMaster - securityGroup - " + securityGroup);
		    System.out.println("insertFileMaster - callFrom - " + callFrom);

		 
		    ifile.insertFileMaster(objectId, newObjectId, generatingSystem, obsoleteRecordFlag, storeInDatabase, insertFlag, objectTag, classification, releasePackageId, changeRequestId, plannedActionId, commodityJurisdiction, commodityClassification, associateChange, SoluminaConstants.FILE, workFlow);		 

		    iSecurityGroup.insertMultiMediaSecurityGroup(newObjectId, securityGroup, SoluminaConstants.FILE);
			 

	 }
	
	
}
