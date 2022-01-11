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
*  File:    LienDaoImplPW.java
* 
*  Created: 2019-03-06
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2019-03-06 	XCS4331		    Initial Release XXXXXXX
*/


package com.ibaset.solumina.sfwid.dao.impl;

import java.util.List;
import java.util.Map;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.solumina.sfwid.dao.ILienDao;

public abstract class LienDaoImplPW extends ImplementationOf<LienDaoImpl> implements ILienDao {

	// defect 1028
    public String selectNoneControlOrderHoldId(String holdType,
											   String orderId,
											   String discId,
											   Number discLineNo){
    	
    	// Fred E.
    	// 3-6-2019
    	// over write OOB Dao method so that if more than one is is selected, a null hold_id is passed back.
    	// this is to fix OOB bug caused by calling code 
    	
    	String holdId = null;
    	
    	StringBuilder select = new StringBuilder().append(" SELECT HOLD_ID ")
												  .append("   FROM SFWID_HOLDS A ")
												  .append("  WHERE A.HOLD_TYPE = ? ")
												  .append("    AND A.ORDER_ID = ? ")
												  .append("    AND A.HOLD_REF2 = ? ")
												  .append("    AND A.HOLD_REF3 = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(holdType);
		parameters.addParameter(orderId);
		parameters.addParameter(discId);
		parameters.addParameter(discLineNo);

		List list = Super.queryForList(select.toString(), parameters);
		if (list!=null && !list.isEmpty()){
			Map map = (Map)list.get(0);
			holdId = (String)map.get("HOLD_ID");
		}
		return holdId;		
    }
}
