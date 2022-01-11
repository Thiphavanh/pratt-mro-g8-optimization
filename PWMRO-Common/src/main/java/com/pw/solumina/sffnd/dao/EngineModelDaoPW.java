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
*  File:    EngineModelDaoPW.java
* 
*  Created: 2018-09-07
* 
*  Author:  c293011
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2018-09-07 	R. Thorpe		Initial Release Defect 962
*/

package com.pw.solumina.sffnd.dao;

import java.util.List;

import com.ibaset.common.Reference;

import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;

public class EngineModelDaoPW {

	@Reference
	private JdbcDaoSupport jdbcDao;
	
	public void insertModelType(String model, 
			                    String modelDescription,
			                    String program)
	{

		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_ENGINE_TYPE_MODEL_DEF ")
				                                   .append("(ENGINE_TYPE, MODEL, UPDT_USERID, TIME_STAMP, ACTION, OBSOLETE_RECORD_FLAG, PROGRAM_MODEL_DESC)")
												   .append(" VALUES")
												   .append(" (? , ?, ?, SYSDATE, 'INSERTED', 'N', ? )");
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(program);
		parameters.addParameter(model);
		parameters.addParameter(ContextUtil.getUsername());
		parameters.addParameter(modelDescription);
		
		jdbcDao.insert(insertSql.toString(), parameters);

		return;   	
	}
	
	public int updateModelType(String model,
			                   String modelDescription,
			                   String program,
			                   String origProgram,
			                   String obsoleteRecordFlag)
	{
		int rowCount = 0;
	
		StringBuffer updateSql = new StringBuffer().append("UPDATE PWUST_ENGINE_TYPE_MODEL_DEF")
												   .append("   SET UPDT_USERID = ?,")
												   .append("       TIME_STAMP = SYSDATE,")
												   .append("       ACTION = 'UPDATE',")
												   .append("       PROGRAM_MODEL_DESC = ?,")
												   .append("       ENGINE_TYPE = ?,")
												   .append("       OBSOLETE_RECORD_FLAG = ?")
												   .append(" WHERE MODEL = ?")
												   .append("   AND ENGINE_TYPE = ?");

		ParameterHolder parameters = new ParameterHolder();
		
		parameters.addParameter(ContextUtil.getUsername());
		parameters.addParameter(modelDescription);
		parameters.addParameter(program);
		parameters.addParameter(obsoleteRecordFlag);
		parameters.addParameter(model);
		parameters.addParameter(origProgram);

		rowCount = jdbcDao.update(updateSql.toString(), parameters); 

		return rowCount;
	}
	
	public int obsoleteModelType(String model,
			                     String program)
	{
		int rowCount = 0;

		StringBuffer updateSql = new StringBuffer().append("UPDATE PWUST_ENGINE_TYPE_MODEL_DEF")
				                                   .append("   SET UPDT_USERID = ?,")
				                                   .append("       TIME_STAMP = SYSDATE,")
				                                   .append("       ACTION = 'UPDATE',")
				                                   .append("       OBSOLETE_RECORD_FLAG = 'Y'")
				                                   .append(" WHERE MODEL = ?")
				                                   .append("   AND ENGINE_TYPE = ?");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(ContextUtil.getUsername());
		parameters.addParameter(model);
		parameters.addParameter(program);
		
		rowCount = jdbcDao.update(updateSql.toString(), parameters); 
		
		return rowCount;
	}
	
	public boolean doesModelTypeExist(String model, 
                                      String program)
	{
		boolean exists = false;

		StringBuffer selectSql = new StringBuffer().append("SELECT 'X'")
				                                   .append("  FROM PWUST_ENGINE_TYPE_MODEL_DEF")
												   .append(" WHERE MODEL = ?")
												   .append("   AND ENGINE_TYPE = ?");
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(model);
		parameters.addParameter(program);
		
		List returnList = jdbcDao.queryForList(selectSql.toString(), parameters);
		
		if(returnList.size() > 0)
		{
			exists = true;
		}
		return exists;	
	}

	public boolean isModelReferenced(String model, String program)
	{
		boolean referenced = false;
		StringBuffer selectSql = new StringBuffer().append("SELECT 'X'")
                                                   .append("  FROM PWUST_SFWID_ORDER_DESC")
                                                   .append(" WHERE ENGINE_MODEL = ?");
				
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(model);

		List returnList = jdbcDao.queryForList(selectSql.toString(), parameters);

		if(returnList.size() > 0)
		{
			referenced = true;
		}	
		
		StringBuffer selectSql3 = new StringBuffer().append("SELECT 'X'")
				                                    .append("  FROM PWUST_PLAN_ENGINE_MODEL")
				                                    .append(" WHERE ENGINE_MODEL = ?");

		ParameterHolder parameters3 = new ParameterHolder();
		parameters3.addParameter(model);

		List returnList3 = jdbcDao.queryForList(selectSql3.toString(), parameters3);

		if(returnList3.size() > 0)
		{
			referenced = true;
		}	
		
		StringBuffer selectSql1 = new StringBuffer().append("SELECT 'X'")
				                                    .append("  FROM SFPL_PLAN_DESC")
				                                    .append(" WHERE MODEL = ?");

		ParameterHolder parameters1 = new ParameterHolder();
		parameters1.addParameter(model);

		List returnList1 = jdbcDao.queryForList(selectSql1.toString(), parameters1);

		if(returnList1.size() > 0)
		{
			referenced = true;
		}


		StringBuffer selectSql2 = new StringBuffer().append("SELECT 'X'")
				                                    .append("  FROM SFWID_ORDER_DESC")
				                                    .append(" WHERE MODEL = ?");

		ParameterHolder parameters2 = new ParameterHolder();
		parameters2.addParameter(model);

		List returnList2 = jdbcDao.queryForList(selectSql2.toString(), parameters);

		if(returnList2.size() > 0)
		{
			referenced = true;
		} 
		
		return referenced;	
	}
 

	public int  deleteModelType(String model, String program)
	{
 
		StringBuffer updateSql = new StringBuffer().append("DELETE PWUST_ENGINE_TYPE_MODEL_DEF")
												   .append(" WHERE ENGINE_TYPE = ?")
												   .append("   AND MODEL = ?");
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(program);
		parameters.addParameter(model);

		int rowCount = jdbcDao.update(updateSql.toString(), parameters);

		return rowCount;   	
	}
}

