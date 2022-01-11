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
*  File:    IStandardTextDaoPW.java
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
* 2018-05-17	B. Preston		Defect 761		Copy security group for new revision. 
*/

package com.pw.solumina.sffnd.dao;


public interface IStandardTextDaoPW  {

	// Begin defect 50.
	public void insertWorkLocation(String tag, String workLocation);
	// End defect 50.
	
	public void updateSecurityGroup(String oldObjectId, String tag); // Defect 761.
}
