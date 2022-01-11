/*
 * This unpublished work, first created in 2019 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2019.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    IM309DaoPW.java
 * 
 *  Created: 2019-08-23
 * 
 *  Author:  Brendan Polak 
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2019-08-23		B.Polak 		Initial Release SMRO_M309 - Tooling Serial Interface
 *  								
 */

package com.pw.solumina.interfaces.dao;

import java.util.List;

public interface IM309DaoPW {
	
	@SuppressWarnings("rawtypes")
	public List getM309ColumnLengths();
	public String getSecurityGroupForItem(String item_id);
	public String getSecurityGroupForPlant(String plant);
	public String getItemId(String part_no, String part_chg);
	public String getPlantForItem(String Item_Id);
	public boolean itemDescSecGrpContainsTool(String part_no);
	public void insertIntoItemDescSecGroup(String part_no, String security_Group);
	public void updateSecurityGroupForItem(String item_id, String newSecurityGroup);
	public boolean itemProgramDetailContainsTool(String item_id, String location_id);
	public void insertIntoItemProgramDetail(String item_id, String location_id);
	public String getLocationIDforPlant(String plant);

}
