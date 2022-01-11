package com.pw.solumina.sfpl.dao;
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
*  File:    IItemDaoPW.java
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
* 2018-05-02	B. Preston		PW_SMRO_EXPT_203 Defect 365 Return int in updateSecurity.
* 2018-08-14	B. Preston		PW_SMRO_EXPT_203 Defect 933 Added getWorkLocation().
* 2019-02-28    T. Phan         SCR SMRO_PLG_207-001 Added repairUpdate.
* 2019-03-01    X002174         Switched Security Group record from a field to a tab – Defect 1135
*/

public interface IItemDaoPW {
	public Number getItemNumber1(String itemId);

	public int updateSecurity(String itemNo, String workLocation); // Defect 365. Defect 1135. Changed parameters.
	
	public int repairUpdate(String itemNo, String itemRev); // SCR SMRO_PLG_207-001
		
	public String getWorkLocation(String partNo, String partChg);  // Defect 933.
}

