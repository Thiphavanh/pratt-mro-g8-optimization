package com.utas.solumina.paar.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;
import com.utas.solumina.common.UtasConstants;
import com.utas.solumina.paar.dao.PaarRequestDao;
import com.utas.solumina.paar.domain.Permission;
import com.utas.solumina.paar.domain.SolPaarExternal;

public class PaarRequestDaoImpl implements PaarRequestDao 
{
	@Reference
	JdbcDaoSupport jdbcDao;	
	
	@SuppressWarnings("rawtypes")
	@Override
	public Map selectRequestInfo(String requestId)
	{
		Map map = new HashMap();
		StringBuffer selectSql = new StringBuffer().append(" SELECT A.REQUEST_STATUS, ")
												   .append("  		A.REQUEST_USERID, ")
												   .append("  		A.REQUEST_CREATION_DATE, ")
												   .append("  		A.REQUEST_REASON, ")
												   .append("  		A.PLAN_ID, ")
												   .append("  		A.PLAN_VERSION, ")
												   .append("  		A.PLAN_REVISION, ")
												   .append("  		A.PLAN_ALTERATIONS, ")
												   .append("  		A.REPLY_REQUIRED_DATE, ")
												   .append("  		A.PRIOR_REQUEST_ID, ")
												   .append("        A.REQUEST_SECURITY_GROUP, ")
												   .append("        B.PLAN_NO, ")
												   .append("        B.PLAN_UPDT_NO ")												   												   
												   .append("  FROM  SFPL_PLAN_V B, UTASGI_PLG_PAAR_DESC A ")
                                                   .append(" WHERE  B.PLAN_ID = A.PLAN_ID ")
                                                   .append("   AND  B.PLAN_VERSION = A.PLAN_VERSION ")
                                                   .append("   AND  B.PLAN_REVISION = A.PLAN_REVISION ")
                                                   .append("   AND  B.PLAN_ALTERATIONS = A.PLAN_ALTERATIONS ")
                                                   .append("   AND  A.REQUEST_ID = ? ");												   												   

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(requestId);

		List list = jdbcDao.queryForList(selectSql.toString(), parameters);
		if(list != null && list.size() > 0)
		{
			map = (Map) list.get(0);
		}
		return map;		
	}
	
	@Override
	public boolean selectPlgPermissionExists(String sghtId, String requestId, String type, String typeEntity)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT ? ")
												   .append("  FROM  DUAL ")
												   .append(" WHERE  EXISTS ( SELECT ? ")
												   .append("				   FROM UTASGI_PLG_PAAR_PMN_SGHT ")
												   .append("				  WHERE SGHT_ID = ? ")
												   .append("				    AND REQUEST_ID = ? ")
												   .append("				    AND PMN_TYPE = ? ")
												   .append("				    AND PMN_TYPE_ENTITY = ? ) ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(SoluminaConstants.X);
		parameters.addParameter(SoluminaConstants.X);
		parameters.addParameter(sghtId);
		parameters.addParameter(requestId);
		parameters.addParameter(type);
		parameters.addParameter(typeEntity);

		return jdbcDao.queryForList(selectSql.toString(), parameters).size() > 0;
	}
	
    @Override
    public boolean selectWidPermissionExists(String sghtId, String requestId, String type, String typeEntity)
    {
        StringBuffer selectSql = new StringBuffer().append(" SELECT ? ")
                                                   .append("  FROM  DUAL ")
                                                   .append(" WHERE  EXISTS ( SELECT ? ")
                                                   .append("                   FROM UTASGI_WID_PAAR_PMN_SGHT ")
                                                   .append("                  WHERE SGHT_ID = ? ")
                                                   .append("                    AND REQUEST_ID = ? ")
                                                   .append("                    AND PMN_TYPE = ? ")
                                                   .append("                    AND PMN_TYPE_ENTITY = ? ) ");

        ParameterHolder parameters = new ParameterHolder();
        parameters.addParameter(SoluminaConstants.X);
        parameters.addParameter(SoluminaConstants.X);
        parameters.addParameter(sghtId);
        parameters.addParameter(requestId);
        parameters.addParameter(type);
        parameters.addParameter(typeEntity);

        return jdbcDao.queryForList(selectSql.toString(), parameters).size() > 0;
    }	
	
	@Override
	public void insertAuthPermission(String tableName,
									 String requestId,
									 String sghtId,
									 String pmnType,
									 String pmnTypeEntity,
									 String pmnAction,
									 Date effFromDate,
									 Date effThruDate)
	{
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO "+tableName+"( ")
												   .append(" REQUEST_ID, ")  
												   .append(" AUTH_ID, ")
												   .append(" PMN_ACTION, ")
												   .append(" PMN_TYPE, ")
												   .append(" PMN_TYPE_ENTITY, ")
												   .append(" PMN_EFF_FROM_DATE, ")
												   .append(" PMN_EFF_THRU_DATE, ")
												   .append(" SGHT_ID, ")
												   .append(" UPDT_USERID, ")
												   .append(" TIME_STAMP, ")
												   .append(" LAST_ACTION ")
												   .append(" ) VALUES( ")
												   .append("    ?, ")
												   .append("    " + jdbcDao.getSchemaPrefix() + "SFDB_GUID(), ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append(jdbcDao.getTimestampFunction()+ " , ")
												   .append("    ?) ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(requestId);
		params.addParameter(pmnAction);
		params.addParameter(pmnType);
		params.addParameter(pmnTypeEntity);
		params.addParameter(effFromDate);
		params.addParameter(effThruDate);
		params.addParameter(sghtId);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(SoluminaConstants.INSERTED);

		jdbcDao.insert(insertSql.toString(), params);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
    public List selectSuggestedPermissionList(String requestId) 
    {
    	StringBuffer selectSql = new StringBuffer().append(" SELECT SGHT_ID, ")
												   .append("  		PMN_ACTION, ")
												   .append("  		PMN_TYPE, ")
												   .append("  		PMN_TYPE_ENTITY, ")
												   .append("  		PMN_EFF_FROM_DATE, ")
												   .append("  		PMN_EFF_THRU_DATE, ")
												   .append("  		AUTH_ID ")
												   .append("  FROM  UTASGI_PLG_PAAR_PMN_SGHT ")
												   .append(" WHERE  REQUEST_ID = ? ");
//												   .append("   AND  PMN_ACTION != ? ");

    	ParameterHolder parameters = new ParameterHolder();
    	parameters.addParameter(requestId);
//    	parameters.addParameter(SoluminaConstants.DELETED);

    	return jdbcDao.queryForList(selectSql.toString(), parameters);
    }
    
    @Override
    public void updateRequestStatus(String requestId, String requestStatus, String authorizationId) 
    {
    	StringBuffer updateSql = new StringBuffer().append(" UPDATE UTASGI_PLG_PAAR_DESC ")    											      											   
    											   .append("    SET REQUEST_STATUS = ? ");
    	if(!StringUtils.isEmpty(authorizationId))
    	{
    		updateSql.append("  , APPROVING_USER_ID = ? ");
    	}
    	updateSql.append("  WHERE REQUEST_ID = ? ");

    	ParameterHolder params = new ParameterHolder();    	
    	params.addParameter(requestStatus);
    	if(!StringUtils.isEmpty(authorizationId))
    	{
    		params.addParameter(authorizationId);
    	}
    	params.addParameter(requestId);
    	jdbcDao.update(updateSql.toString(), params);  
    }
    
    @Override
    /*
     * (non-Javadoc)
     * @see com.utas.solumina.paar.dao.PaarRequestDao#selectAuthorizedProfileForRequestId(java.lang.String)
     */
	public List selectAuthorizedProfileForRequestId(String requestId) 
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT AUTH_ID, ")
												   .append("  		PMN_ACTION, ")
												   .append("  		PMN_TYPE, ")
												   .append("  		PMN_TYPE_ENTITY, ")
												   .append("  		PMN_EFF_FROM_DATE, ")
												   .append("  		PMN_EFF_THRU_DATE, ")
												   .append("  		SGHT_ID ")
												   .append("  FROM  UTASGI_PLG_PAAR_PMN_AUTH ")
												   .append(" WHERE  REQUEST_ID = ? ")
												   .append(" ORDER BY DECODE(PMN_TYPE,'USER',1,'LOCATION',2,'SECURITY GROUP',3,4) ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(requestId);

		return jdbcDao.queryForList(selectSql.toString(), parameters);
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see com.utas.solumina.paar.dao.PaarRequestDao#getGUIDGenSecGroup(java.lang.String)
	 */
	public Map getGUIDGenSecGroup(String requestId) 
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT A.REQUEST_SECURITY_GROUP, ")
												   .append("  		B.PLAN_NO ")
												   .append("  FROM  SFPL_PLAN_V B, UTASGI_PLG_PAAR_DESC A ")
												   .append(" WHERE  B.PLAN_ID = A.PLAN_ID ")
												   .append("   AND  B.PLAN_VERSION = A.PLAN_VERSION ")
												   .append("   AND  B.PLAN_REVISION = A.PLAN_REVISION ")
												   .append("   AND  B.PLAN_ALTERATIONS = A.PLAN_ALTERATIONS ")
                                                   .append("   AND  A.REQUEST_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(requestId);
		
		return jdbcDao.queryForMap(selectSql.toString(), parameters);
	}
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see com.utas.solumina.paar.dao.PaarRequestDao#selectSecurityGroupForAssignedWorkLocation(java.lang.String)
	 */
	public List selectSecurityGroupForAssignedWorkLocation(String workLocation) 
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT DISTINCT SECURITY_GROUP ")
												   .append("     FROM UTASGI_USER_SEC_GRP_XREF ")
												   .append("   WHERE HR_LOC = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(workLocation);
		
		return jdbcDao.queryForList(selectSql.toString(), params);    	
	}
	
	@Override
	public boolean selectSecurityGroupExists(String securityGroup)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT ? ")
												   .append("  FROM  DUAL ")
												   .append(" WHERE  EXISTS ( SELECT ? ")
												   .append("				   FROM SFFND_SECURITY_GROUP_DEF ")
												   .append("				  WHERE SECURITY_GROUP = ? )");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(SoluminaConstants.X);
		parameters.addParameter(SoluminaConstants.X);
		parameters.addParameter(securityGroup);

		return jdbcDao.queryForList(selectSql.toString(), parameters).size() > 0;
	}
	
	@Override
	public boolean selectWorkLocationExists(String workLocation) 
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT ? ")
												   .append("  FROM  DUAL ")
												   .append(" WHERE  EXISTS ( SELECT ? ")
												   .append("				   FROM SFFND_WORK_LOC_DEF ")
												   .append("				  WHERE WORK_LOC = ? )");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(SoluminaConstants.X);
		parameters.addParameter(SoluminaConstants.X);
		parameters.addParameter(workLocation);

		return jdbcDao.queryForList(selectSql.toString(), parameters).size() > 0;
	}

	@Override
	public void insertExternalPlanPAARDesc(SolPaarExternal solPaarExternal) 
	{
		StringBuilder insertSql = new StringBuilder().append(" INSERT INTO UTASGE_PLG_PAAR_DESC  ")
													 .append(" (REQUEST_ID            ,          ")
													 .append("   REQUEST_STATUS        ,         ")
													 .append("   REQUEST_USERID        ,         ")
													 .append("   REQUEST_CREATION_DATE ,         ")
													 .append("   REQUEST_REASON        ,         ")
													 .append("   PLAN_NO               ,         ")
													 .append("   PLAN_VERSION          ,         ")
													 .append("   PLAN_REVISION         ,         ")
													 .append("   PLAN_ALTERATIONS      ,         ")
													 .append("   PLAN_TYPE             ,         ")
													 .append("   PLAN_TITLE            ,         ")
													 .append("   BOM_NO                ,         ")
													 .append("   BOM_REV               ,         ")
													 .append("   FACILITY_ID           ,         ")
													 .append("   FACILITY_DESC         ,         ")
													 .append("   ITEM_NO               ,         ")
													 .append("   ITEM_REV              ,         ")
													 .append("   ITEM_TITLE            ,         ")
													 .append("   ITEM_TYPE             ,         ")
													 .append("   ITEM_SUBTYPE          ,         ")
													 .append("   REPLY_REQUIRED_DATE   ,         ")
													 .append("   PRIOR_REQUEST_ID      ,         ")
													 .append("   UPDT_USERID           ,         ")
													 .append("   TIME_STAMP            ,         ")
													 .append("   LAST_ACTION ,                   ")
													 .append("   APPROVING_USER_ID )                   ")
													 .append("   VALUES (                          ") 
													  .append(" ?, ")  
													 .append("   ?        ,         ")
													 .append("   ?        ,         ")
													 .append("   ? ,         ")
													 .append("   ?        ,         ")                    
													 .append("   ?,                              ")
													 .append("   ?          ,         ")
													 .append("   ?         ,         ")
													 .append("   ?      ,         ")
													 .append("   ?,                              ")
													 .append("   ?,                              ")
													 .append("   ?,                              ")
													 .append("   ?,                              ")
													 .append("   ?,                              ")
													 .append("   ?,                              ")
													 .append("   ?,                              ")
													 .append("   ?,                              ")
													 .append("   ?,                              ")
													 .append("   ?,                              ")
													 .append("   ?,                              ")
													 .append("   ?   ,         ")
													 .append("   ?      ,         ")
													 .append("   ?,                              ")
													 .append(jdbcDao.getTimestampFunction()+" ,  ")                         
													 .append("   ?						,	")
													 .append("   ?						)	");			
		
		ParameterHolder params = new ParameterHolder();		
		params.addParameter(solPaarExternal.getRequestId());
		params.addParameter(solPaarExternal.getRequestStatus());		
		params.addParameter(solPaarExternal.getRequestUserId());
		params.addParameter(solPaarExternal.getReqCreationDate());
		params.addParameter(solPaarExternal.getRequestReason());
		params.addParameter(solPaarExternal.getPlanNo());
		params.addParameter(solPaarExternal.getPlanVersion());
		params.addParameter(solPaarExternal.getPlanRevision());
		params.addParameter(solPaarExternal.getPlanAlteration());
		params.addParameter(solPaarExternal.getPlanType());
		params.addParameter(solPaarExternal.getPlanTitle());
		params.addParameter(solPaarExternal.getBomNo());
		params.addParameter(solPaarExternal.getBomRev());
		params.addParameter(solPaarExternal.getFacilityId());
		params.addParameter(solPaarExternal.getFacilityDesc());
		params.addParameter(solPaarExternal.getPartNo());
		params.addParameter(solPaarExternal.getPartRev());
		params.addParameter(solPaarExternal.getPartTitle());
		params.addParameter(solPaarExternal.getPartType());
		params.addParameter(solPaarExternal.getPartSubType());
		params.addParameter(solPaarExternal.getReplyRequiredDate());
		params.addParameter(solPaarExternal.getPriorRequestId());		
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(SoluminaConstants.INSERTED);
		params.addParameter(solPaarExternal.getApprovingUserId());
		
		jdbcDao.insert(insertSql.toString(), params);
				
	}

	@Override
	public void insertExternalPlanPAARPermissionAuth(String requestId, Permission permission) 
	{
		StringBuilder insertSql = new StringBuilder().append(" INSERT INTO UTASGE_PLG_PAAR_PMN_AUTH ")
													 .append("  (REQUEST_ID        ,                ")
													 .append("   AUTH_ID           ,                ")
													 .append("   PMN_ACTION        ,                ")
													 .append("   PMN_STATUS        ,                ")
													 .append("   PMN_TYPE          ,                ")
													 .append("   PMN_TYPE_ENTITY   ,                ")
													 .append("   PMN_EFF_FROM_DATE ,                ")
													 .append("   PMN_EFF_THRU_DATE ,                ")
													 .append("   SGHT_ID           ,                ")
													 .append("   UPDT_USERID       ,                ")
													 .append("   TIME_STAMP        ,                ")
													 .append("   LAST_ACTION       )                ")
													 .append("   VALUES(                             ")
													 .append("  ? ,                                ")
													 .append( jdbcDao.getSchemaPrefix() + "SFDB_GUID(), ")
													 .append("   ? ,                                ")
													 .append("   ? ,                                ")
													 .append("   ? ,                                ")
													 .append("   ? ,                                ")
													 .append("   ? ,                                ")
													 .append("   ? ,                                ")
													 .append("   ? ,                                ")
													 .append("   ? ,                                ")
													 .append( jdbcDao.getTimestampFunction()+", ")
													 .append("   ? )                                ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(requestId);
		params.addParameter(permission.getAction()==null?StringUtils.EMPTY:permission.getAction());
		params.addParameter(permission.getPermissionStatus());
		params.addParameter(permission.getType()==null?StringUtils.EMPTY:permission.getType());
		params.addParameter(permission.getTypeEntity()==null?StringUtils.EMPTY:permission.getTypeEntity());
		params.addParameter(permission.getStartDate());
		params.addParameter(permission.getEndDate());
		params.addParameter(permission.getSghtId()==null?StringUtils.EMPTY:permission.getSghtId());
		params.addParameter(ContextUtil.getUsername());		
		params.addParameter(SoluminaConstants.INSERTED);
		
		jdbcDao.insert(insertSql.toString(), params);
	}
	
	@Override
    public List selectPaarExternalPermissionList(String requestId, String calledFrom) 
    {
    	StringBuffer selectSql = new StringBuffer().append(" SELECT  ")
    											   .append(" AUTH_ID           , ")
    											   .append(" PMN_ACTION        , ")
    											   .append(" PMN_STATUS        , ")
    											   .append(" PMN_TYPE          , ")
    											   .append(" PMN_TYPE_ENTITY   , ")
    											   .append(" PMN_EFF_FROM_DATE , ")
    											   .append(" PMN_EFF_THRU_DATE , ")
    											   .append(" SGHT_ID            ");
    	if(StringUtils.equals(calledFrom, UtasConstants.PLANNING_PROPER_CASE))
    	{
    		selectSql.append("  FROM  UTASGE_PLG_PAAR_PMN_AUTH ");
    	}
    	else if(StringUtils.equals(calledFrom, UtasConstants.EXECUTION_PROPER_CASE))
    	{
    		selectSql.append("  FROM  UTASGE_WID_PAAR_PMN_AUTH ");
    	}
		selectSql.append(" WHERE  REQUEST_ID = ? ");												   

    	ParameterHolder parameters = new ParameterHolder();
    	parameters.addParameter(requestId);    	

    	return jdbcDao.queryForList(selectSql.toString(), parameters);
    }
	
	@Override
	public Map selectPlanPaarRequestExternalInfo(String requestId)
	{
		Map map = new HashMap();
		StringBuffer selectSql = new StringBuffer().append(" SELECT  ")
												   .append(" REQUEST_STATUS        ,")
												   .append(" REQUEST_USERID        ,")
												   .append(" REQUEST_CREATION_DATE ,")
												   .append(" REQUEST_REASON        ,")
												   .append(" PLAN_NO               ,")
												   .append(" PLAN_VERSION          ,")
												   .append(" PLAN_REVISION         ,")
												   .append(" PLAN_ALTERATIONS      ,")
												   .append(" PLAN_TYPE             ,")
												   .append(" PLAN_TITLE            ,")
												   .append(" BOM_NO                ,")
												   .append(" BOM_REV               ,")
												   .append(" FACILITY_ID           ,")
												   .append(" FACILITY_DESC         ,")
												   .append(" ITEM_NO               ,")
												   .append(" ITEM_REV              ,")
												   .append(" ITEM_TITLE            ,")
												   .append(" ITEM_TYPE             ,")
												   .append(" ITEM_SUBTYPE          ,")
												   .append(" REPLY_REQUIRED_DATE   ,")
												   .append(" PRIOR_REQUEST_ID      ")
												   .append("  FROM  UTASGE_PLG_PAAR_DESC ")
												   .append(" WHERE  REQUEST_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(requestId);

		List list = jdbcDao.queryForList(selectSql.toString(), parameters);
		if(list != null && list.size() > 0)
		{
			map = (Map) list.get(0);
		}
		return map;		
	}
	
	@Override
	public void updateExternalPaarRequestStatus(String requestId, String calledFrom)
	{
		StringBuffer updateSql = new StringBuffer().append(" UPDATE  ");
		if(StringUtils.equals(calledFrom, UtasConstants.PLANNING_PROPER_CASE))
		{
			updateSql.append(" UTASGE_PLG_PAAR_DESC        ");
		}
		else if(StringUtils.equals(calledFrom, UtasConstants.EXECUTION_PROPER_CASE))
		{
			updateSql.append(" UTASGE_WID_PAAR_DESC        ");
		}
		updateSql.append(" SET         ")
				 .append(" REQUEST_STATUS = ?, ")				 
				 .append(" APPROVING_USER_ID = ? ")		//UTAS-538		 
				 .append(" WHERE  REQUEST_ID = ? ");
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(SoluminaConstants.COMPLETE_UPPERCASE);
		parameters.addParameter(ContextUtil.getUsername());
		parameters.addParameter(requestId);
		
		jdbcDao.update(updateSql.toString(), parameters);
	}
	
	@Override
	public String selectExternalPaarRequestStatus(String requestId, String calledFrom)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT  REQUEST_STATUS ");
		if(StringUtils.equals(calledFrom, UtasConstants.PLANNING_PROPER_CASE))
		{
			selectSql.append(" FROM UTASGE_PLG_PAAR_DESC        ");
		}
		else if(StringUtils.equals(calledFrom, UtasConstants.EXECUTION_PROPER_CASE))
		{
			selectSql.append(" FROM UTASGE_WID_PAAR_DESC        ");
		}
		selectSql.append(" WHERE  REQUEST_ID = ? ");												   
		
		ParameterHolder parameters = new ParameterHolder();		
		parameters.addParameter(requestId);
		
		return jdbcDao.queryForString(selectSql.toString(), parameters);		
	}

	@Override
	public Map selectPaarWORequestInfo(String requestId) 
	{
		Map map = new HashMap();
		StringBuffer selectSql = new StringBuffer().append(" SELECT REQUEST_STATUS, ")
												   .append("  		REQUEST_USERID, ")
												   .append("  		REQUEST_CREATION_DATE, ")
												   .append("  		REQUEST_REASON, ")
												   .append("  		ORDER_ID, ")												   
												   .append("  		REPLY_REQUIRED_DATE, ")
												   .append("  		PRIOR_REQUEST_ID ")
												   .append("  FROM  UTASGI_WID_PAAR_DESC ")
												   .append(" WHERE  REQUEST_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(requestId);

		List list = jdbcDao.queryForList(selectSql.toString(), parameters);
		if(list != null && list.size() > 0)
		{
			map = (Map) list.get(0);
		}
		return map;	
		
	}
	
	@Override
    public void updatePaarWORequestStatus(String requestId, String requestStatus, String authorizationId) 
    {
    	StringBuffer updateSql = new StringBuffer().append("UPDATE UTASGI_WID_PAAR_DESC ")
    	                                           .append("   SET REQUEST_STATUS = ?, ")									   
    	                                           .append("       UPDT_USERID = ?, ")
    	                                           .append("       LAST_ACTION = ?, ")
    	                                           .append("       TIME_STAMP = " + jdbcDao.getTimestampFunction());
    											   
    	if(!StringUtils.isEmpty(authorizationId))
    	{
    	    updateSql.append("  , APPROVING_USER_ID = ? ");	
    	}

    	updateSql.append("  WHERE REQUEST_ID = ? ");

    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(requestStatus);
    	params.addParameter(ContextUtil.getUsername());
        params.addParameter(SoluminaConstants.UPDATED);
    	
    	if(!StringUtils.isEmpty(authorizationId))
		{
    		params.addParameter(authorizationId);	
		}
    	
    	params.addParameter(requestId);
    	jdbcDao.update(updateSql.toString(), params);  
    	
    }
	
	// UTAS-497
	@Override
	public void insertExternalOrderPAARDesc(SolPaarExternal solPaarExternal) 
	{
		StringBuilder insertSql = new StringBuilder().append(" INSERT INTO UTASGE_WID_PAAR_DESC  ")
													 .append(" (REQUEST_ID             ,         ")
													 .append("   REQUEST_STATUS        ,         ")
													 .append("   REQUEST_USERID        ,         ")
													 .append("   REQUEST_CREATION_DATE ,         ")
													 .append("   REQUEST_REASON        ,         ")
													 .append("   ORDER_NO              ,         ")
													 .append("   ORDER_STATUS          ,         ")
													 .append("   ORDER_TITLE           ,         ")
													 .append("   BOM_NO                ,         ")
													 .append("   BOM_REV               ,         ")
													 .append("   FACILITY_ID           ,         ")
													 .append("   FACILITY_DESC         ,         ")
													 .append("   ITEM_NO               ,         ")
													 .append("   ITEM_REV              ,         ")
													 .append("   ITEM_TITLE            ,         ")
													 .append("   ITEM_TYPE             ,         ")
													 .append("   ITEM_SUBTYPE          ,         ")
													 .append("   ACTUAL_START_DATE     ,         ")
													 .append("   ACTUAL_END_DATE       ,         ")
													 .append("   SCHED_START_DATE      ,         ")
													 .append("   SCHED_END_DATE        ,         ")
													 .append("   REPLY_REQUIRED_DATE   ,         ")
													 .append("   PRIOR_REQUEST_ID      ,         ")
													 .append("   UPDT_USERID           ,         ")
													 .append("   TIME_STAMP            ,         ")
													 .append("   LAST_ACTION ,                   ")
													 .append("   APPROVING_USER_ID )                   ")
													 .append("   VALUES (                        ") 
													  .append("  ?, ")  
													 .append("   ?        ,         ")
													 .append("   ?        ,         ")
													 .append("   ? ,         ")
													 .append("   ?        ,         ")                    
													 .append("   ?,                              ")
													 .append("   ?          ,         ")
													 .append("   ?         ,         ")
													 .append("   ?      ,         ")
													 .append("   ?,                              ")
													 .append("   ?,                              ")
													 .append("   ?,                              ")
													 .append("   ?,                              ")
													 .append("   ?,                              ")
													 .append("   ?,                              ")
													 .append("   ?,                              ")
													 .append("   ?,                              ")
													 .append("   ?,                              ")
													 .append("   ?,                              ")
													 .append("   ?,                              ")
													 .append("   ?,                              ")
													 .append("   ?   ,         ")
													 .append("   ?      ,         ")
													 .append("   ?,                              ")
													 .append(jdbcDao.getTimestampFunction()+" ,  ")                         
													 .append("   ?						,	")
													 .append("   ?						)	");
		
		ParameterHolder params = new ParameterHolder();		
		params.addParameter(solPaarExternal.getRequestId());
		params.addParameter(solPaarExternal.getRequestStatus());		
		params.addParameter(solPaarExternal.getRequestUserId());
		params.addParameter(solPaarExternal.getReqCreationDate());
		params.addParameter(solPaarExternal.getRequestReason());
		params.addParameter(solPaarExternal.getOrderNo());
		params.addParameter(solPaarExternal.getOrderStatus());
		params.addParameter(solPaarExternal.getOrderTitle());
		params.addParameter(solPaarExternal.getBomNo());
		params.addParameter(solPaarExternal.getBomRev());
		params.addParameter(solPaarExternal.getFacilityId());
		params.addParameter(solPaarExternal.getFacilityDesc());
		params.addParameter(solPaarExternal.getPartNo());
		params.addParameter(solPaarExternal.getPartRev());
		params.addParameter(solPaarExternal.getPartTitle());
		params.addParameter(solPaarExternal.getPartType());
		params.addParameter(solPaarExternal.getPartSubType());
		params.addParameter(solPaarExternal.getActualStartDate());
		params.addParameter(solPaarExternal.getActualEndDate());
		params.addParameter(solPaarExternal.getSchedStartDate());
		params.addParameter(solPaarExternal.getSchedEndDate());
		params.addParameter(solPaarExternal.getReplyRequiredDate());
		params.addParameter(solPaarExternal.getPriorRequestId());		
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(SoluminaConstants.INSERTED);
		params.addParameter(solPaarExternal.getApprovingUserId());
		
		jdbcDao.insert(insertSql.toString(), params);
	}

	@Override
	public List selectPaarWOPermissionList(String requestId)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT SGHT_ID, ")
												   .append("  		PMN_ACTION, ")
												   .append("  		PMN_TYPE, ")
												   .append("  		PMN_TYPE_ENTITY, ")
												   .append("  		PMN_EFF_FROM_DATE, ")
												   .append("  		PMN_EFF_THRU_DATE, ")
												   .append("  		AUTH_ID ")
												   .append("  FROM  UTASGI_WID_PAAR_PMN_SGHT ")
												   .append(" WHERE  REQUEST_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(requestId);

		return jdbcDao.queryForList(selectSql.toString(), parameters);		
	}
	
	@Override
	public List selectWOAuthorizedProfileForRequestId(String requestId) 
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT AUTH_ID, ")
												   .append("  		PMN_ACTION, ")
												   .append("  		PMN_TYPE, ")
												   .append("  		PMN_TYPE_ENTITY, ")
												   .append("  		PMN_EFF_FROM_DATE, ")
												   .append("  		PMN_EFF_THRU_DATE, ")
												   .append("  		SGHT_ID ")
												   .append("  FROM  UTASGI_WID_PAAR_PMN_AUTH ")
												   .append(" WHERE  REQUEST_ID = ? ")
												   .append("   AND  PMN_ACTION = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(requestId);
		parameters.addParameter(UtasConstants.PERMIT);

		return jdbcDao.queryForList(selectSql.toString(), parameters);
	}
	
	// UTAS-497
	@Override
	public void insertExternalOrderPAARPermissionAuth(String requestId, Permission permission) 
	{
		StringBuilder insertSql = new StringBuilder().append(" INSERT INTO UTASGE_WID_PAAR_PMN_AUTH ")
													 .append("  (REQUEST_ID        ,                ")
													 .append("   AUTH_ID           ,                ")
													 .append("   PMN_ACTION        ,                ")
													 .append("   PMN_STATUS        ,                ")
													 .append("   PMN_TYPE          ,                ")
													 .append("   PMN_TYPE_ENTITY   ,                ")
													 .append("   PMN_EFF_FROM_DATE ,                ")
													 .append("   PMN_EFF_THRU_DATE ,                ")
													 .append("   SGHT_ID           ,                ")
													 .append("   UPDT_USERID       ,                ")
													 .append("   TIME_STAMP        ,                ")
													 .append("   LAST_ACTION       )                ")
													 .append("   VALUES(                             ")
													 .append("  ? ,                                ")
													 .append( jdbcDao.getSchemaPrefix() + "SFDB_GUID(), ")
													 .append("   ? ,                                ")
													 .append("   ? ,                                ")
													 .append("   ? ,                                ")
													 .append("   ? ,                                ")
													 .append("   ? ,                                ")
													 .append("   ? ,                                ")
													 .append("   ? ,                                ")
													 .append("   ? ,                                ")
													 .append( jdbcDao.getTimestampFunction()+", ")
													 .append("   ? )                                ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(requestId);
		params.addParameter(permission.getAction()==null?StringUtils.EMPTY:permission.getAction());
		params.addParameter(permission.getPermissionStatus());
		params.addParameter(permission.getType()==null?StringUtils.EMPTY:permission.getType());
		params.addParameter(permission.getTypeEntity()==null?StringUtils.EMPTY:permission.getTypeEntity());
		params.addParameter(permission.getStartDate());
		params.addParameter(permission.getEndDate());
		params.addParameter(permission.getSghtId()==null?StringUtils.EMPTY:permission.getSghtId());
		params.addParameter(ContextUtil.getUsername());		
		params.addParameter(SoluminaConstants.INSERTED);
		
		jdbcDao.insert(insertSql.toString(), params);
	}
	
	// UTAS-497
	@SuppressWarnings("rawtypes")
	@Override
	public Map selectOrderPaarRequestExternalInfo(String requestId)
	{
		Map map = new HashMap();
		StringBuffer selectSql = new StringBuffer().append(" SELECT  ")
												   .append(" REQUEST_STATUS        ,")
												   .append(" REQUEST_USERID        ,")
												   .append(" REQUEST_CREATION_DATE ,")
												   .append(" REQUEST_REASON        ,")
												   .append(" ORDER_NO              ,")
												   .append(" ORDER_STATUS		   ,")
												   .append(" ORDER_TITLE           ,")
												   .append(" BOM_NO                ,")
												   .append(" BOM_REV               ,")
												   .append(" FACILITY_ID           ,")
												   .append(" FACILITY_DESC         ,")
												   .append(" ITEM_NO               ,")
												   .append(" ITEM_REV              ,")
												   .append(" ITEM_TITLE            ,")
												   .append(" ITEM_TYPE             ,")
												   .append(" ITEM_SUBTYPE          ,")
												   .append(" ACTUAL_START_DATE     ,")
												   .append(" ACTUAL_END_DATE       ,")
												   .append(" SCHED_START_DATE      ,")
												   .append(" SCHED_END_DATE        ,")
												   .append(" REPLY_REQUIRED_DATE   ,")
												   .append(" PRIOR_REQUEST_ID      ")
												   .append("  FROM  UTASGE_WID_PAAR_DESC ")
												   .append(" WHERE  REQUEST_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(requestId);

		List list = jdbcDao.queryForList(selectSql.toString(), parameters);
		if(list != null && list.size() > 0)
		{
			map = (Map) list.get(0);
		}
		return map;		
	}
	
	@Override
	public boolean selectAtLeastOneSecurityGroupExists(String orderNo)
	{
		StringBuffer select = new StringBuffer().append("SELECT ? ")
												.append("FROM DUAL ")
												.append("WHERE EXISTS (SELECT ? ")
												.append("				 FROM SFFND_SECURITY_GROUP_DEF A, SFWID_ORDER_DESC_SEC_GRP B ")
												.append("				WHERE A.SECURITY_GROUP = B.SECURITY_GROUP ")
												.append("				  AND NVL(A.UCF_SEC_GRP_FLAG1, ' ') <> ? ")
												.append("				  AND B.ORDER_NO = ? ) ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(SoluminaConstants.X);
		params.addParameter(SoluminaConstants.X);
		params.addParameter(SoluminaConstants.R);
		params.addParameter(orderNo);
		
		return jdbcDao.queryForList(select.toString(), params).size() > 0;												
	}
	
	@Override
	public boolean orderReqHasOnePositivePerm(String requestId) 
	{
		StringBuffer select = new StringBuffer();
		
		select.append("select 'X' ")
              .append("from utasge_wid_paar_pmn_auth ")
              .append("where request_id = ? ")
              .append("and ((pmn_action = 'DELETED' and pmn_status = 'Deny') or (pmn_action != 'DELETED' and pmn_status = 'Permit')) ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(requestId);

		return jdbcDao.queryForList(select.toString(), params).size() > 0;
	}
	
    public boolean planReqHasOnePositivePerm(String requestId) 
    {
        StringBuffer select = new StringBuffer();
        
        select.append("select 'X' ")
              .append("from utasge_plg_paar_pmn_auth ")
              .append("where request_id = ? ")
              .append("and ((pmn_action = 'DELETED' and pmn_status = 'Deny') or (pmn_action != 'DELETED' and pmn_status = 'Permit')) ");

        ParameterHolder params = new ParameterHolder();
        params.addParameter(requestId);

        return jdbcDao.queryForList(select.toString(), params).size() > 0;
    }	
	
	
	
	public boolean selectSecGrpStillInUse(String securityGroup)
	{
	    StringBuffer sqlText = new StringBuffer();
	    
	    sqlText.append("select 'Y' from sfcore_mm_object_sec_grp where security_group = ? ");
	    sqlText.append("union select 'Y' from sfpl_item_desc_sec_grp where security_group = ? ");
	    sqlText.append("union select 'Y' from sfpl_mfg_bom_rev_sec_grp where security_group = ? ");
	    sqlText.append("union select 'Y' from sfpl_plan_desc_sec_grp where security_group = ? ");
	    sqlText.append("union select 'Y' from sfqa_disc_item_sec_grp where security_group = ? ");
	    sqlText.append("union select 'Y' from sfwid_order_desc_sec_grp where security_group = ? ");
	    sqlText.append("union select 'Y' from utasgi_user_sec_grp_xref where security_group = ? ");

	    ParameterHolder params = new ParameterHolder();
        params.addParameter(securityGroup);
        params.addParameter(securityGroup);
        params.addParameter(securityGroup);
        params.addParameter(securityGroup);
        params.addParameter(securityGroup);
        params.addParameter(securityGroup);
        params.addParameter(securityGroup);
        
        return jdbcDao.queryForList(sqlText.toString(), params).size() > 0;
	    
	}
	
	public void deleteAllUsersFromSecGrp(String securityGroup)
	{
	    StringBuffer sqlText = new StringBuffer();
	    
	    sqlText.append("delete from sffnd_user_sec_grp where security_group = ? ");
	    
	    ParameterHolder params = new ParameterHolder();
	    params.addParameter(securityGroup);
	    
	    jdbcDao.delete(sqlText.toString(), params);
	    
	}
	
	public void removeSecGrpFromPlanDesc(String planId,
	                                     Number planUpdtNo,                                          
                                         String securityGroup)
	{
	    StringBuffer sqlText = new StringBuffer();
	    
	    sqlText.append("update sfpl_plan_desc ");
	    sqlText.append("set security_group = sfmfg.sffnd_security_group_delete(?, security_group) ");
	    sqlText.append("where plan_id = ? and plan_updt_no = ? ");

	    ParameterHolder params = new ParameterHolder();
        params.addParameter(securityGroup);
        params.addParameter(planId);
        params.addParameter(planUpdtNo);
	    
	    jdbcDao.update(sqlText.toString(), params);
	    
	}

	public boolean selectSecGrpUsedInOtherPlanVerRev(String planNumber,
	                                                 String securityGroup,
	                                                 String planId, 
	                                                 Number planVersion,
	                                                 Number planRevision,
	                                                 Number planAlterations) 
	{ 
	    StringBuffer selectSql = new StringBuffer();

	    selectSql.append("select 'Y' ");
	    selectSql.append("from utasgi_plg_plan_sec_grp_v ");
	    selectSql.append("where plan_no = ? ");
	    selectSql.append("and security_group = ? ");
	    selectSql.append("and ((plan_id = ? and plan_version != ?) or (plan_id = ? and plan_version = ? and plan_revision != ?)) ");

	    ParameterHolder params = new ParameterHolder();
	    params.addParameter(planNumber);
	    params.addParameter(securityGroup);
	    params.addParameter(planId);
	    params.addParameter(planVersion);
	    params.addParameter(planId);
	    params.addParameter(planVersion);
	    params.addParameter(planRevision);        

	    return jdbcDao.queryForList(selectSql.toString(), params).size() > 0;
	}
	
	   public void deletePlanDescSecurityGroup(String planNumber,
	                                           String securityGroup)
	    {
	        StringBuffer delete = new StringBuffer().append("delete from sfpl_plan_desc_sec_grp where plan_no = ? and security_group = ? ");

	        ParameterHolder params = new ParameterHolder();
	        params.addParameter(planNumber);
	        params.addParameter(securityGroup);

	        jdbcDao.delete(delete.toString(), params);
	    }
	   
   
	   public boolean doesUserSecGrpHaveUser(String securityGroup)
	   {
	       StringBuffer sqlText = new StringBuffer();

	       sqlText.append("select 'Y' from sffnd_user_sec_grp where security_group = ?");

	       ParameterHolder params = new ParameterHolder();
	       params.addParameter(securityGroup);

	       return jdbcDao.queryForList(sqlText.toString(), params).size() > 0;
	       
	   }
	   
	    public boolean selectSecGrpExistsInOtherVerRev(String planNumber,
	                                                   String securityGroup,
	                                                   String planId, 
	                                                   Number planVersion,
	                                                   Number planRevision,
	                                                   Number planAlterations) 
	    { 
	        StringBuffer selectSql = new StringBuffer();
	        
	        selectSql.append("select 'Y' ");
	        selectSql.append("from utasgi_plg_plan_sec_grp_v ");
	        selectSql.append("where plan_no = ? ");
	        selectSql.append("and security_group = ? ");
	        selectSql.append("and ((plan_id = ? and plan_version != ?) or (plan_id = ? and plan_version = ? and plan_revision != ?)) ");
	        
	        ParameterHolder params = new ParameterHolder();
	        params.addParameter(planNumber);
	        params.addParameter(securityGroup);
	        params.addParameter(planId);
	        params.addParameter(planVersion);
	        params.addParameter(planId);
	        params.addParameter(planVersion);
	        params.addParameter(planRevision);        
	        
	        return jdbcDao.queryForList(selectSql.toString(), params).size() > 0;
	    }
	    
	    public void removeAllSecGrpFromOrderDesc(String orderId)
	    {
	        StringBuffer sqlText = new StringBuffer();

	        sqlText.append("update sfwid_order_desc set security_group = null where order_id = ? ");

	        ParameterHolder params = new ParameterHolder();
	        params.addParameter(orderId);

	        jdbcDao.update(sqlText.toString(), params);

	    }   
	    
	    public void deleteUnusedOrderDescSecGrps(String orderNo)
	    {
	        StringBuffer sqlText = new StringBuffer();
	        
	        sqlText.append("delete from sfwid_order_desc_sec_grp y ");
	        sqlText.append(" where y.order_no = ? ");
	        sqlText.append(" and not exists ");
	        sqlText.append("    (select 'N' ");
	        sqlText.append("       from sfwid_order_desc b, table(sffnd_parse_fcn(b.security_group,',')) c ");
	        sqlText.append("      where b.order_no = ? ");
	        sqlText.append("        and c.column_value = y.security_group)");
	        
	        ParameterHolder params = new ParameterHolder();
            params.addParameter(orderNo);
            params.addParameter(orderNo);
	        
            jdbcDao.delete(sqlText.toString(), params);

	    }
        
	    public String selectWorkLocId(String workLoc)
	    {
	    	StringBuffer selectSql = new StringBuffer();
	    	selectSql.append("select location_id ");
	        selectSql.append("from SFFND_WORK_LOC_DEF ");
	        selectSql.append("where work_loc = ? ");
	        selectSql.append("and rownum=1 ");
	        
	        ParameterHolder params = new ParameterHolder();
	        params.addParameter(workLoc);     
	        
	        return jdbcDao.queryForString(selectSql.toString(), params);
	    }
	    
	    public void cancelExternalPriorRequestPlg(String priorRequestId)
	    {
	        StringBuffer sqlText = new StringBuffer();

	        sqlText.append("update utasge_plg_paar_desc set request_status = 'CANCELLED', updt_userid = ?, time_stamp = "+ jdbcDao.getTimestampFunction() +", last_action = ?  ")
	               .append(" where request_id = ? and request_status = 'OPEN'");

	        ParameterHolder params = new ParameterHolder();
	        params.addParameter(ContextUtil.getUsername());
	        params.addParameter(SoluminaConstants.UPDATED);
	        params.addParameter(priorRequestId);

	        jdbcDao.update(sqlText.toString(), params);            
	    }
	    
	    public void cancelExternalPriorRequestWid(String priorRequestId)
	    {
	        StringBuffer sqlText = new StringBuffer();

	        sqlText.append("update utasge_wid_paar_desc set request_status = 'CANCELLED', updt_userid = ?, time_stamp = "+ jdbcDao.getTimestampFunction() +", last_action = ?  ")
	               .append(" where request_id = ? and request_status = 'OPEN'");

	        ParameterHolder params = new ParameterHolder();
	        params.addParameter(ContextUtil.getUsername());
	        params.addParameter(SoluminaConstants.UPDATED);
	        params.addParameter(priorRequestId);

	        jdbcDao.update(sqlText.toString(), params);            
	    }

}
