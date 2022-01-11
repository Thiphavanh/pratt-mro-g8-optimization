package com.utas.solumina.sffnd.dao.impl;

import java.util.List;
import java.util.Map;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;
import com.utas.solumina.sffnd.dao.IUtasSecurityGroupDao;

public class UtasSecurityGroupDaoImplExt implements IUtasSecurityGroupDao
{
	@Reference
	JdbcDaoSupport jdbcDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	public List selectPlanSecurityGroup(String planId, Number planUpdateNumber)
	{
		StringBuffer selectSql = new StringBuffer();
        ParameterHolder parameters = new ParameterHolder();

        selectSql.append(" SELECT ")
                 .append("		SECURITY_GROUP ")
                 .append(" FROM ")
                 .append("		SFPL_PLAN_DESC_SEC_GRP ")
        		 .append(" WHERE PLAN_ID= ? ")
        		 .append(" AND   PLAN_UPDT_NO= ? ");

        parameters.addParameter(planId);
        parameters.addParameter(planUpdateNumber);

        return jdbcDao.queryForList(selectSql.toString(), parameters);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List selectOrderSecurityGroup(String orderNo)
	{
		StringBuffer selectSql = new StringBuffer();
        ParameterHolder parameters = new ParameterHolder();

        selectSql.append(" SELECT ")
                 .append("		SECURITY_GROUP ")
                 .append(" FROM ")
                 .append("		SFWID_ORDER_DESC_SEC_GRP ")
        		 .append(" WHERE ORDER_NO = ? ");

        parameters.addParameter(orderNo);

        return jdbcDao.queryForList(selectSql.toString(), parameters);
	}

	@Override
	public boolean securityGroupExistsInAnyRevision(String planNo, String securityGroup)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT ? ")
												   .append("FROM DUAL ")
												   .append("WHERE '" + securityGroup + "' IN ")
												   .append("	(SELECT COLUMN_VALUE ")
												   .append("	   FROM TABLE(SFFND_PARSE_FCN((SELECT ")
												   .append("			 LISTAGG(SECURITY_GROUP, ',') WITHIN GROUP(ORDER BY SECURITY_GROUP) ")
												   .append("				FROM SFPL_PLAN_DESC WHERE PLAN_NO = ?) , ','))) ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(SoluminaConstants.X);
		params.addParameter(planNo);
		
		return jdbcDao.queryForList(selectSql.toString(), params).size() > 0;
	}
	
	// UTAS-484
	@Override
	public boolean isUserLastSecurityGroup(String planNo, Number planUpdtNo, String userId)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT ? ")
												   .append("FROM SFFND_USER_SEC_GRP A, (SELECT COLUMN_VALUE AS PLAN_SECURITY_GROUP ")
												   .append("                              FROM TABLE(SFFND_PARSE_FCN((SELECT LISTAGG(SECURITY_GROUP, ',') WITHIN GROUP(ORDER BY SECURITY_GROUP)")
												   .append("                                                           FROM SFPL_PLAN_DESC WHERE PLAN_NO = ? AND PLAN_UPDT_NO = ?) , ','))) B ")
												   .append("WHERE A.USERID = ? ")
												   .append("  AND A.SECURITY_GROUP = B.PLAN_SECURITY_GROUP ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(SoluminaConstants.X);
		params.addParameter(planNo);
		params.addParameter(planUpdtNo);
		params.addParameter(userId);

		return jdbcDao.queryForList(selectSql.toString(), params).size() > 0;
	}
	
	public String selectOrderDescSecurityGroup(String orderNo)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT ")
												   .append(" SECURITY_GROUP ")
												   .append("FROM SFWID_ORDER_DESC ")
												   .append("WHERE ORDER_NO = ?  ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderNo);

		return jdbcDao.queryForString(selectSql.toString(), parameters);
	}
	
	public int deleteWorkPlanSecurityGroup(String planNumber)
	{
		StringBuffer deleteSql = new StringBuffer().append("DELETE FROM ")
												   .append("    SFPL_PLAN_DESC_SEC_GRP ")
												   .append("WHERE ")
												   .append("    PLAN_NO = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(planNumber);
		
		return jdbcDao.delete(deleteSql.toString(), parameters);
	}

	@Override
	public Map getPlanInfo(String planNumber) {
		StringBuffer selectSql = new StringBuffer().append("SELECT ")
				   .append(" PLAN_ID, PLAN_UPDT_NO")
				   .append("FROM SFPL__DESC ")
				   .append("WHERE PLAN_NO = ?  ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(planNumber);
		
		return jdbcDao.queryForMap(selectSql.toString(), parameters);
	}
	
	public String getPlanId(String planNumber) {
		StringBuffer selectSql = new StringBuffer().append("SELECT DISTINCT ")
				   .append(" PLAN_ID ")
				   .append("FROM SFPL__DESC ")
				   .append("WHERE PLAN_NO = ?  ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(planNumber);
		
		return jdbcDao.queryForString(selectSql.toString(), parameters);
	}
}
