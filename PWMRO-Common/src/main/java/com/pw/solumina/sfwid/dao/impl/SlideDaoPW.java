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
*  File:    SlideDaoPW.java
* 
*  Created: 2021-05-28
* 
*  Author:  X007084
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2021-05-28 		X007084		    Initial Release XXXXXXX
*/

package com.pw.solumina.sfwid.dao.impl;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;

public class SlideDaoPW {

	@Reference  private ExtensionDaoSupport eds;
	
public void insertSlideMMObject(String objectId, String pwTag,String pwRev,String securityGroup){
		
		StringBuffer insertSql = new StringBuffer().append(" INSERT INTO pwust_sfcore_mm_object( PW_OBJECT_ID, " +
		                                                                                             " PW_OBJECT_TAG, " + 
				                                                                                     " PW_OBJECT_TYPE, " + 
				                                                                                     " PW_OBJECT_REV, " + 
				                                                                                     " SECURITY_GROUP, " +
		                                                                                             " UPDT_USERID, " +
		                                                                                             " TIME_STAMP ) " +
																		" VALUES ( ? , ? , ? , ?, ?, SYSDATE ) "); //
	

		String user = ContextUtil.getUsername();
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(objectId);
		parameters.addParameter(pwTag);
		parameters.addParameter("ILLUSTRATION");
		parameters.addParameter(securityGroup);
		parameters.addParameter(user);

		
		eds.insert(insertSql.toString(), parameters);
		
	}

}

