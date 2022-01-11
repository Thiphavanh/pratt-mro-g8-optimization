package com.utas.solumina.sfpl.dao.impl;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;
import com.utas.solumina.sfpl.dao.IUtasOperationDao;

public class UtasOperationDaoImpl implements IUtasOperationDao
{
	@Reference
	private JdbcDaoSupport jdbcDao;
		
	@Override
	public String selectStandardOperationSecurityGroup(String stdOperObjectId)
	{
		StringBuilder sql = new StringBuilder("select security_group ")
							          .append("  from sfcore_mm_object ")
							          .append(" where object_id = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(stdOperObjectId);

		return jdbcDao.queryForString(sql.toString(),params);
	}
	
}
