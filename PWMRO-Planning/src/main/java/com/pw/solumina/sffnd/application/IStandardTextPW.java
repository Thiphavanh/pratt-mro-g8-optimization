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
*  File:    IStandardTextPW.java
* 
*  Created: 2018-03-16
* 
*  Author:  X002465
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	------------------------------------------------------------
* 2018-03-16	B. Preston		Defect 50		Initial release -- Limit standard text by work location.
*/

package com.pw.solumina.sffnd.application;

public interface IStandardTextPW {

	// Begin defect 50.
	public void insertStandardText(String standardTextTag, String description, String blockType, String releasePackage,
			String calledFrom, String standardTextType, String workFlow, String commodityJurisdiction,
			String commodityClassification, String associateChange, String workLocation);
	// End defect 50.
	
}
