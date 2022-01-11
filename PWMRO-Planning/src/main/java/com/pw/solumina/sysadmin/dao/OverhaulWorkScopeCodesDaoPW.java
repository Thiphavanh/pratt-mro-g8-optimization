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
 *  File:    OverhaulWorkScopeCodesDaoPW.java
 * 
 *  Created: 2017-08-03
 * 
 *  Author:  c293011
 * 
 *  Revision History
 * 
 *  Date      	Who                	Description
 *  ----------	---------------	---------------	---------------------------------------------------
 * 2017-08-03 	c293011		    Initial Release SMRO_PLG_205
 */

package com.pw.solumina.sysadmin.dao;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;

public class OverhaulWorkScopeCodesDaoPW {

	@Reference private ExtensionDaoSupport eds;
	
	public int insertOverhaulWorkScopeCodes(String workScope, String workScopeTitle, String engineType, String workLoc)
	{
	    //// Explain Plan cost = 1
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_WORKSCOPE_DEF")
				                                   .append(" (WORKSCOPE,")
				                                   .append("  WORKSCOPE_TITLE,")
				                                   .append("  ENGINE_TYPE,")
				                                   .append("  WORK_LOC,")
				                                   .append("  UPDT_USERID)")						                         
				                                   .append("  VALUES (")
				                                   .append("  ?,")
				                                   .append("  ?,")
				                                   .append("  ?,")
				                                   .append("  ?,")
				                                   .append("  ? )");


		ParameterHolder params = new ParameterHolder();
		params.addParameter(workScope);
		params.addParameter(workScopeTitle);
		params.addParameter(engineType);	
		params.addParameter(workLoc);
		params.addParameter(ContextUtil.getUsername());

		System.out.println();
		int rowCount = eds.update(insertSql.toString(), params);

		return rowCount;
	}

	public void deleteOverhaulWorkScopeCodes(String workScope, String engineType, String workLoc)
	{
		//// Explain Plan cost = 0
		StringBuffer deleteSql = new StringBuffer().append("DELETE FROM PWUST_WORKSCOPE_DEF")
				                                   .append(" WHERE WORKSCOPE = ?")
				                                   .append("   AND ENGINE_TYPE = ?")
				                                   .append("   AND WORK_LOC = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(workScope);
		params.addParameter(engineType);
		params.addParameter(workLoc);
		
		eds.delete(deleteSql.toString(), params);
	}
	
	public int updateOverhaulWorkScopeCodes(String workScope, String workScopeTitle, String engineType, String workLoc, String obsoleteRecordFlag)
	{
	    //// Explain Plan cost = 0
		StringBuffer updateSql = new StringBuffer().append("UPDATE PWUST_WORKSCOPE_DEF")
				                                   .append("   SET WORKSCOPE_TITLE = ?,")
				                                   .append("       ENGINE_TYPE = ?,")
				                                   .append("       WORK_LOC = ?,")
				                                   .append("       OBSOLETE_RECORD_FLAG = ?,")
				                                   .append("       UPDT_USERID = ?,")
				                                   .append("       TIME_STAMP = SYSDATE,")
				                                   .append("       ACTION = ?")
				                                   .append(" WHERE WORKSCOPE = ? ")
				                                   .append("   AND ENGINE_TYPE = ?")
				                                   .append("   AND WORK_LOC = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(workScopeTitle);
		params.addParameter(engineType);
		params.addParameter(workLoc);
		params.addParameter(obsoleteRecordFlag);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(SoluminaConstants.UPDATED);
		params.addParameter(workScope);
		params.addParameter(engineType);
		params.addParameter(workLoc);

		int rowCount = eds.update(updateSql.toString(), params);

		return rowCount;
	}

	public boolean checkIfWorkScopeExists(String workScope, String engineType, String workLoc)
	{
		
		StringBuffer resultSql = new StringBuffer().append("SELECT COUNT(*)")
												   .append("  FROM PWUST_WORKSCOPE_DEF")
											       .append(" WHERE WORKSCOPE = ?")
											       .append("   AND ENGINE_TYPE = ?")
											       .append("   AND WORK_LOC = ?");
				
		ParameterHolder params = new ParameterHolder();
		params.addParameter(workScope);
		params.addParameter(engineType);
		params.addParameter(workLoc);

		int count = eds.queryForInt(resultSql.toString(), params);

		boolean workScopeExists;

		if (count > 0)
		{
			workScopeExists = true;
		}
		else
		{
			workScopeExists = false;
		}

		return workScopeExists;
	}
	
	public boolean checkIfEngineTypeExists(String engineType)
	{
		
		StringBuffer resultSql = new StringBuffer().append("SELECT COUNT(*)")
												   .append("  FROM SFFND_PROGRAM_DEF")
											       .append(" WHERE PROGRAM = ?");
				
		ParameterHolder params = new ParameterHolder();
		params.addParameter(engineType);

		int count = eds.queryForInt(resultSql.toString(), params);

		boolean engineTypeExists;

		if (count > 0)
		{
			engineTypeExists = true;
		}
		else
		{
			engineTypeExists = false;
		}

		return engineTypeExists;
	}

	public boolean checkIfWorkLocExists(String workLoc)
	{
		
		StringBuffer resultSql = new StringBuffer().append("SELECT COUNT(*)")
												   .append("  FROM SFFND_WORK_LOC_DEF")
											       .append(" WHERE WORK_LOC = ?");
				
		ParameterHolder params = new ParameterHolder();
		params.addParameter(workLoc);

		int count = eds.queryForInt(resultSql.toString(), params);

		boolean workLocExists;

		if (count > 0)
		{
			workLocExists = true;
		}
		else
		{
			workLocExists = false;
		}

		return workLocExists;
	}
}
