/*
* This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    StandardTextImplPW.java
* 
*  Created: 2017-11-20
* 
*  Author:  XCS4071
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	------------------------------------------------------------
* 2017-11-20 	N.Gnassounou	 Initial Release -Defect 244 : Do not allow apostrophe in STANDARDTEXTTAG  field 
* 2018-03-16	B. Preston		Defect 50		Limit standard text by work location.
* 2018-05-17	B. Preston		Defect 761		Copy security group for new revision. 
*/

package com.pw.solumina.sffnd.application.impl;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IStandardText;
import com.ibaset.solumina.sffnd.dao.IStandardTextDao;
import com.pw.solumina.sffnd.application.IStandardTextPW;
import com.pw.solumina.sffnd.dao.IStandardTextDaoPW;

public abstract class StandardTextImplPW extends ImplementationOf<IStandardText>
		implements IStandardText, IStandardTextPW {

	@Reference
	protected IMessage message = null;

	@Reference
	private IStandardTextDao standardTextDao = null;

	@Reference
	private IStandardTextDaoPW standardTextDaoPW = null;

	@Reference
	private IStandardTextPW standardTextPW = null;

	public void insertStandardText(String standardTextTag, String description, String blockType, String releasePackage,
			String calledFrom, String standardTextType, String workFlow, String commodityJurisdiction,
			String commodityClassification, String associateChange) {
		int apostosphe = standardTextTag.indexOf("'");
		if (apostosphe > 0 || apostosphe == 0) {
			message.raiseError("MFI_20", "Special characters such as Apostrophe (') is not allowed in tag field");
		}

		Super.insertStandardText(standardTextTag, description, blockType, releasePackage, calledFrom, standardTextType,
				workFlow, commodityJurisdiction, commodityClassification, associateChange);
	}

	// Begin defect 50.
	public void insertStandardText(String standardTextTag, String description, String blockType, String releasePackage,
			String calledFrom, String standardTextType, String workFlow, String commodityJurisdiction,
			String commodityClassification, String associateChange, String workLocation) {

		// Call original method.
		insertStandardText(standardTextTag, description, blockType, releasePackage, calledFrom, standardTextType,
				workFlow, commodityJurisdiction, commodityClassification, associateChange);

		// Insert work location.
		standardTextDaoPW.insertWorkLocation(standardTextTag, workLocation);
	}
	// End defect 50.

	// Begin defect 761.
	public void createStandardTextRevision(String standardTextTag, String description, String blockType,
			String releasePackage, String oldObjectId, String calledFrom, String changeRequestId,
			String plannedActionId, String standardTextType, String workFlow, String associateChange) {
		
		Super.createStandardTextRevision(standardTextTag, description, blockType,
				releasePackage, oldObjectId, calledFrom, changeRequestId,
				plannedActionId, standardTextType, workFlow, associateChange);
		
		standardTextDaoPW.updateSecurityGroup(oldObjectId, standardTextTag);
			
	}
	// End defect 761
}
