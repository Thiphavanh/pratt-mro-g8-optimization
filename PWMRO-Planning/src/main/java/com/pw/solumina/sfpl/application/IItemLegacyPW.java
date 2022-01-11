package com.pw.solumina.sfpl.application;
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
*  File:    IItemLegacyPW.java
* 
*  Created: 2018-04-05
* 
*  Author:  B. Preston
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2018-04-05	B. Preston		PW_SMRO_EXPT_203 Defect 365 Initial release.
*/

public interface IItemLegacyPW {

	public void preInsert(String itemNumber, String itemRev, String itemType, String calledFrom, String workLoc); // Defect 365
}
