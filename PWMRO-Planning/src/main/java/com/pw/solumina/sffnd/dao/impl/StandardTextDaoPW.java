package com.pw.solumina.sffnd.dao.impl;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.solumina.sffnd.dao.IStandardTextDao;

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
*  File:    StandardTextDaoPW.java
* 
*  Created: 2018-03-15
* 
*  Author:  X002475
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	------------------------------------------------------------
* 2018-03-15	B. Preston		Defect 50		Initial release -- Limit standard text by work location. 
* 2018-05-17	B. Preston		Defect 761		Copy security group for new revision. 
* 2018-06-05	B. Preston		Defect 826		Commented out updateSecurityGroup.
*/

import com.pw.solumina.sffnd.dao.IStandardTextDaoPW;

public abstract class StandardTextDaoPW extends ImplementationOf<IStandardTextDao> implements IStandardTextDao, IStandardTextDaoPW {
	
	@Reference
	private IStandardTextDaoPW standardTextDaoPW = null;

	@Reference 
	private ExtensionDaoSupport eds = null;
	
	// Begin defect 50.
	@Override
	public void insertWorkLocation(String tag, String workLocation) {
		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFCORE_MM_OBJECT ")
				.append("    SET UCF_MMOBJ_VCH1 = ?, ")
				.append("		 SECURITY_GROUP = (SELECT SECURITY_GROUP FROM UTASGI_USER_SEC_GRP_XREF WHERE HR_LOC = ?)")
				.append("  WHERE OBJECT_TAG = ? AND OBJECT_TYPE = 'STDTEXT'");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(workLocation);
		params.addParameter(workLocation);
		params.addParameter(tag);

		eds.update(updateSql.toString(), params);
	}
	// End defect 50.
	
	// Begin defect 761.
	@Override
	public void updateSecurityGroup(String oldObjectId, String tag) {
//		StringBuffer updateSql = new StringBuffer().append("UPDATE SFCORE_MM_OBJECT ")
//				.append("    set security_group = ")
//				.append("      (select security_group from sfcore_mm_object where object_id = ? and object_type = 'STDTEXT') ")
//				.append("where object_id <> ? ")
//				.append("and object_type = 'STDTEXT' ")
//				.append("and object_tag = ? ");
//
//		ParameterHolder params = new ParameterHolder();
//		params.addParameter(oldObjectId);
//		params.addParameter(oldObjectId);
//		params.addParameter(tag);
//
//		eds.update(updateSql.toString(), params);
	}
	// End defect 761.
}