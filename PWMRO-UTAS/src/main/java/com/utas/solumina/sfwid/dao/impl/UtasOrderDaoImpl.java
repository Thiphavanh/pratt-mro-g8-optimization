package com.utas.solumina.sfwid.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;
import com.utas.solumina.common.UtasConstants;
import com.utas.solumina.sfwid.dao.IUtasOrderDao;

public class UtasOrderDaoImpl implements IUtasOrderDao
{

	@Reference
	private JdbcDaoSupport dao;
	

    public boolean selectOpenPAARRequestExists(String planId, 
											   Number planVersion,
											   Number planRevision,
											   Number planAlterations)
    {
    	StringBuffer select = new StringBuffer().append("SELECT ? ")
    											.append("FROM   DUAL ")
    											.append("WHERE  EXISTS ( SELECT ? ")
    											.append("				   FROM UTASGI_PLG_PAAR_DESC ")
    											.append("				  WHERE PLAN_ID = ? ")
    											.append("					AND PLAN_VERSION = ? ")
    											.append("					AND PLAN_REVISION = ? ")
    											.append("					AND PLAN_ALTERATIONS = ? ")
    											.append("					AND REQUEST_STATUS NOT IN (?, ?)) ");
    	
    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(SoluminaConstants.X);
    	params.addParameter(SoluminaConstants.X);
    	params.addParameter(planId);
    	params.addParameter(planVersion);
    	params.addParameter(planRevision);
    	params.addParameter(planAlterations);
    	params.addParameter(SoluminaConstants.COMPLETE_UPPERCASE);
    	params.addParameter(UtasConstants.CANCELLED);
    	
    	return dao.queryForList(select.toString(), params).size() > 0;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.utas.solumina.sfwid.dao.IUtasOrderDao#isWorkPlanHasSecurityGroup(java.lang.String, java.lang.Number, java.lang.Number, java.lang.Number)
     */
    public boolean isWorkPlanHasSecurityGroup(String planId, 
			   								   Number planVersion,
			   								   Number planRevision,
			   								   Number planAlterations)
    {
    	StringBuffer select = new StringBuffer().append(" SELECT ? ")
    											.append("	FROM SFPL_PLAN_DESC A, SFPL_PLAN_REV B ")
    											.append(" WHERE A.PLAN_ID = B.PLAN_ID     ")
    											.append("	AND A.PLAN_UPDT_NO = B.PLAN_UPDT_NO ")
    											.append("	AND B.PLAN_ID = ? ")
    											.append("	AND B.PLAN_VERSION = ? ")
    											.append("	AND B.PLAN_REVISION = ? ")
    											.append("	AND B.PLAN_ALTERATIONS = ? ")
    											.append("	AND A.SECURITY_GROUP IS NOT NULL ");

    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(SoluminaConstants.X);
    	params.addParameter(planId);
    	params.addParameter(planVersion);
    	params.addParameter(planRevision);
    	params.addParameter(planAlterations);
    	
    	return dao.queryForList(select.toString(), params).size() > 0;
    }
    
    public List selectSecurityGroupBasedOnWorkLocation(String workLocation)
    {

    	StringBuffer select = new StringBuffer().append(" SELECT A.SECURITY_GROUP ")
    											.append("	FROM UTASGI_USER_SEC_GRP_XREF A, SFFND_USER_SEC_GRP B ")
    											.append(" WHERE A.SECURITY_GROUP = B.SECURITY_GROUP     ")
    											.append("	AND A.HR_LOC = ? ")
    											.append("	AND B.USERID = ? ");

    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(workLocation);
    	params.addParameter(ContextUtil.getUsername());
    	
    	return dao.queryForList(select.toString(), params);
    }
    
}
