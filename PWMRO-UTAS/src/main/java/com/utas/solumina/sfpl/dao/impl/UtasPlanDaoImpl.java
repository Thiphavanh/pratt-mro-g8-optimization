package com.utas.solumina.sfpl.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;
import com.utas.solumina.common.UtasConstants;
import com.utas.solumina.sfpl.dao.IUtasPlanDao;

public class UtasPlanDaoImpl implements IUtasPlanDao
{
    
	@Reference
	JdbcDaoSupport jdbcDao;

    public String getEmbeddedObjectId (String objectId) {
        StringBuilder sql = new StringBuilder("select to_char(substr(text_data,instr(text_data,'@OBJECT_ID=')+11,40)) as object_id") 
                                      .append("  from sfcore_mm_object a ")
                                      .append(" where object_id =?"); 
        ParameterHolder params = new ParameterHolder();
        params.addParameter(objectId);
        return jdbcDao.queryForString(sql.toString(),params);
    }
    
    public Map selectMMObjectHint (String objectId) {
        StringBuilder sql = new StringBuilder("SELECT classification, dir, dir_version, object_desc, SECURITY_GROUP ")
                                      .append("  FROM utasgi_fnd_dir_obj_v ")
                                      .append(" WHERE object_id = ? ");
        
        ParameterHolder params = new ParameterHolder();
        params.addParameter(objectId);
        return jdbcDao.queryForMap(sql.toString(),params);
    }	
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see com.utas.solumina.sfpl.application.dao.IUtasPlanDao#selectSecurityGroupForAssignedWorkLocation(java.lang.String)
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
	/*
	 * (non-Javadoc)
	 * @see com.utas.solumina.sfpl.application.dao.IUtasPlanDao#selectDefaultSecurityGroup(java.lang.String, java.lang.String)
	 */
	public String selectDefaultSecurityGroup(String workLocation)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT LISTAGG(X.SECURITY_GROUP, ',') WITHIN GROUP(ORDER BY X.SECURITY_GROUP) AS SG ")
												   .append("   FROM (SELECT DISTINCT SECURITY_GROUP ")
												   .append("           FROM UTASGI_USER_SEC_GRP_XREF ")
												   .append("                  WHERE HR_LOC = ?) X ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(workLocation);

		return jdbcDao.queryForString(selectSql.toString(), params);    	
	}
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see com.utas.solumina.sfpl.application.dao.IUtasPlanDao#insertPaarDesc(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Number, java.lang.Number, java.lang.Number, java.util.Date, java.lang.String)
	 */
	public void insertPaarDesc(String requestId,
							   String requestStatus,
							   String requestUser,
							   String requestReason,
							   String planId,
							   Number planVer,
							   Number planRev,
							   Number planAlt,
							   Date replyReqDate,
							   String priorRequestId,
							   String guidSecurityGroup)
	{
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO UTASGI_PLG_PAAR_DESC( ")
												   .append(" REQUEST_ID,            ")  
												   .append(" REQUEST_STATUS,        ")
												   .append(" REQUEST_USERID,        ")
												   .append(" REQUEST_CREATION_DATE, ")
												   .append(" REQUEST_REASON,        ")
												   .append(" PLAN_ID,               ")
												   .append(" PLAN_VERSION,          ")
												   .append(" PLAN_REVISION,         ")
												   .append(" PLAN_ALTERATIONS,      ")
												   .append(" REPLY_REQUIRED_DATE,   ")
												   .append(" PRIOR_REQUEST_ID,      ")												   
												   .append(" REQUEST_SECURITY_GROUP, ")												   
												   .append(" UPDT_USERID,           ")
												   .append(" TIME_STAMP,            ")
												   .append(" LAST_ACTION            ")
												   .append(" ) VALUES( ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append(jdbcDao.getTimestampFunction()+ " , ")
												   .append("    ?, ")
												   .append("    ?, ")
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
		params.addParameter(requestStatus);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(requestReason);
		params.addParameter(planId);
		params.addParameter(planVer);
		params.addParameter(planRev);
		params.addParameter(planAlt);
		params.addParameter(replyReqDate);
		params.addParameter(priorRequestId);
		params.addParameter(guidSecurityGroup);		
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(SoluminaConstants.INSERTED);
		
		jdbcDao.insert(insertSql.toString(), params);
	}
	
	
	public Map selectLastPlanRequest(String planId)
	{
	    Map resultMap = new HashMap();
	    
	    StringBuffer selectSql = new StringBuffer().append("SELECT X.REQUEST_ID, X.REQUEST_STATUS ")
                                                   .append("  FROM (SELECT REQUEST_ID, REQUEST_STATUS ")
                                                   .append("          FROM UTASGI_PLG_PAAR_DESC ")
                                                   .append("         WHERE PLAN_ID = ? ")
                                                   .append("         ORDER BY  ")                                                         
                                                   .append("               CASE REQUEST_STATUS ")
                                                   .append("                    WHEN 'IN REVIEW' THEN 1")
                                                   .append("                    WHEN 'COMPLETE' THEN 2")
                                                   .append("                    ELSE 3 END,")
                                                   .append("                   TIME_STAMP DESC) X ")
                                                   .append(" WHERE ROWNUM = 1");

	       ParameterHolder params = new ParameterHolder();
	       params.addParameter(planId);
	       
	       try
	       {
	           resultMap = jdbcDao.queryForMap(selectSql.toString(), params);
	       }
	       catch (IncorrectResultSizeDataAccessException e)
	        {
	            // do nothing, we'll just return an empty map
	        }   
	        
	        return resultMap;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see com.utas.solumina.sfpl.application.dao.IUtasPlanDao#selectExistingRequestIdAsPerStatus(java.lang.String, java.lang.Number, java.lang.Number, java.lang.Number, java.lang.String)
	 */
	public String selectExistingRequestIdAsPerStatus(String planId, 
										     		 Number planVer,
										     		 Number planRev,
										     		 Number planAlt,
										     		 String requestStatus)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT X.REQUEST_ID                             ")
												   .append("   FROM (SELECT REQUEST_ID                       ")
												   .append("           FROM UTASGI_PLG_PAAR_DESC             ")
												   .append("          WHERE PLAN_ID = ?                      ")
												   .append("            AND REQUEST_STATUS = ?               ")
												   .append("          ORDER BY REQUEST_CREATION_DATE DESC) X ")
												   .append("  WHERE ROWNUM = 1                               ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(requestStatus);
		
		return jdbcDao.queryForString(selectSql.toString(), params);    	
	}
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see com.utas.solumina.sfpl.application.dao.IUtasPlanDao#insertSuggestedSecurityGroupProfleFromPastCancelledProfile(java.lang.String, java.lang.String)
	 */
	public void insertSuggestedSecurityGroupProfleFromPastCancelledProfile(String currentRequestId,
																		   String priorRequestId)
	{
		StringBuffer insertSql = new StringBuffer().append(" INSERT INTO UTASGI_PLG_PAAR_PMN_SGHT  ")
												   .append("   (REQUEST_ID,                        ")
												   .append("    SGHT_ID,                           ")
												   .append("    PMN_ACTION,                        ")
												   .append("    PMN_TYPE,                          ")
												   .append("    PMN_TYPE_ENTITY,                   ")
												   .append("    PMN_EFF_FROM_DATE,                 ")
												   .append("    PMN_EFF_THRU_DATE,                 ")
												   .append("    AUTH_ID,                           ")
												   .append("    UPDT_USERID,                       ")
												   .append("    TIME_STAMP,                        ")
												   .append("    LAST_ACTION)                       ")
												   .append("   SELECT ?,                           ")
												   .append("          SFDB_GUID,                   ")
												   .append("          PMN_ACTION,                  ")
												   .append("          PMN_TYPE,                    ")
												   .append("          PMN_TYPE_ENTITY,             ")
												   .append("          PMN_EFF_FROM_DATE,           ")
												   .append("          PMN_EFF_THRU_DATE,           ")
												   .append("          AUTH_ID,                     ")
												   .append("          ?,                           ")
												   .append( jdbcDao.getTimestampFunction() +   " , ")
												   .append("          ?                            ")
												   .append("     FROM UTASGI_PLG_PAAR_PMN_SGHT     ")
												   .append("    WHERE REQUEST_ID = ?               ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(currentRequestId);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(SoluminaConstants.INSERTED);
		params.addParameter(priorRequestId);
		
		jdbcDao.insert(insertSql.toString(), params);
	}
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see com.utas.solumina.sfpl.application.dao.IUtasPlanDao#insertSuggestedSecurityGroupProfleFromPastAuthoredProfile(java.lang.String, java.lang.String)
	 */
	public void insertSuggestedSecurityGroupProfleFromPastAuthoredProfile(String currentRequestId,
																		   String priorRequestId)
	{
		StringBuffer insertSql = new StringBuffer().append(" INSERT INTO UTASGI_PLG_PAAR_PMN_SGHT  ")
												   .append("   (REQUEST_ID,                        ")
												   .append("    SGHT_ID,                           ")
												   .append("    PMN_ACTION,                        ")
												   .append("    PMN_TYPE,                          ")
												   .append("    PMN_TYPE_ENTITY,                   ")
												   .append("    PMN_EFF_FROM_DATE,                 ")
												   .append("    PMN_EFF_THRU_DATE,                 ")
												   .append("    AUTH_ID,                           ")
												   .append("    UPDT_USERID,                       ")
												   .append("    TIME_STAMP,                        ")
												   .append("    LAST_ACTION)                       ")
												   .append("   SELECT ?,                           ")
												   .append("          SFDB_GUID,                   ")
												   .append("          'PERSISTED',                 ")
												   .append("          PMN_TYPE,                    ")
												   .append("          PMN_TYPE_ENTITY,             ")
												   .append("          PMN_EFF_FROM_DATE,           ")
												   .append("          PMN_EFF_THRU_DATE,           ")
												   .append("          AUTH_ID,                     ")
												   .append("          ?,                           ")
												   .append( jdbcDao.getTimestampFunction() +   " , ")
												   .append("          ?                            ")
												   .append("     FROM UTASGI_PLG_PAAR_PMN_AUTH     ")
												   .append("    WHERE REQUEST_ID = ?               ")
												   .append("      AND PMN_ACTION = 'PERMIT'        ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(currentRequestId);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(SoluminaConstants.INSERTED);
		params.addParameter(priorRequestId);

		jdbcDao.insert(insertSql.toString(), params);
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see com.utas.solumina.sfpl.application.dao.IUtasPlanDao#selectPlanNumber(java.lang.String, java.lang.Number)
	 */
	public String selectPlanNumber(String planId, Number planUpdateNumber) 
	{

        StringBuffer selectSql = new StringBuffer().append("SELECT PLAN_NO ")
                                                   .append("  FROM SFPL_PLAN_DESC ")
                                                   .append(" WHERE PLAN_ID = ? ")
                                                   .append("   AND PLAN_UPDT_NO = ? ");

        ParameterHolder parameters = new ParameterHolder();
        parameters.addParameter(planId);
        parameters.addParameter(planUpdateNumber);
        
        return jdbcDao.queryForString(selectSql.toString(), parameters);
    
	}
	
	
	public void deleteAllSecurityForPlan(String planNumber)
	{
	    // Delete the plan security group records
	    deleteWorkPlanSecurityGroup(planNumber);

	    //Update security group field to be null for all plans of Plan No	    
	    StringBuffer sqlText = new StringBuffer().append("update sfpl_plan_desc set security_group = null where plan_no = ? ");

	    ParameterHolder parameters = new ParameterHolder();
        parameters.addParameter(planNumber);
        
        jdbcDao.update(sqlText.toString(), parameters);
	}
	   
	   
	@Override
	/*
	 * (non-Javadoc)
	 * @see com.utas.solumina.sfpl.application.dao.IUtasPlanDao#deleteWorkPlanSecurityGroup(java.lang.String)
	 */
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
	/*
	 * (non-Javadoc)
	 * @see com.utas.solumina.sfpl.application.dao.IUtasPlanDao#selectDBTimeStamp()
	 */
	public Date selectDBTimeStamp()
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT ")
												   .append(jdbcDao.getSchemaPrefix() + "SFDB_SYSDATE()" )
												   .append(jdbcDao.getDualTable());

		return (Date) jdbcDao.queryForObject(selectSql.toString(), Date.class);
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see com.utas.solumina.sfpl.application.dao.IUtasPlanDao#updatePAARRequestStatus(java.lang.String, java.lang.Number, java.lang.Number, java.lang.Number, java.lang.String)
	 */
	public void updatePAARRequestStatus(String planId,
									   Number planVer,
									   Number planRev, 
									   Number planAlt, 
									   String requestStatus) 
	{
		StringBuffer update = new StringBuffer().append(" UPDATE UTASGI_PLG_PAAR_DESC ")
											    .append("    SET REQUEST_STATUS = ? ")
											    .append("  WHERE PLAN_ID = ? ")
											    .append("    AND PLAN_VERSION = ? ")
											    .append("    AND PLAN_REVISION = ? ")
											    .append("    AND PLAN_ALTERATIONS = ? ")
											    .append("    AND REQUEST_STATUS = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(UtasConstants.CANCELLED);
		params.addParameter(planId);
		params.addParameter(planVer);
		params.addParameter(planRev);
		params.addParameter(planAlt);
		params.addParameter(requestStatus);
		
		jdbcDao.update(update.toString(), params);
	}
    
    public void cancelAnyInReviewPlgRequests(String planId) 
     {
         StringBuffer update = new StringBuffer().append(" UPDATE UTASGI_PLG_PAAR_DESC ")
                                                 .append("    SET REQUEST_STATUS = ?, ")
                                                 .append("        UPDT_USERID = ?,")
                                                 .append("        TIME_STAMP = "+ jdbcDao.getTimestampFunction() +",")
                                                 .append("        LAST_ACTION = ?")
                                                 .append("  WHERE PLAN_ID = ? ")
                                                 .append("    AND REQUEST_STATUS = ? ");

         ParameterHolder params = new ParameterHolder();
         params.addParameter(UtasConstants.CANCELLED);
         params.addParameter(ContextUtil.getUsername());
         params.addParameter(SoluminaConstants.UPDATED);
         params.addParameter(planId);
         params.addParameter(SoluminaConstants.IN_REVIEW);
         
         jdbcDao.update(update.toString(), params);
     }	
	
    
    @Override
    public void insertProfile(String pmnType, 
			  				  String pmnTypeEntity, 
			  				  String pmnAction, //UTAS-511
			  				  Date effFromDate, 
			  				  Date effThruDate, 
			  				  String requestId, 
			  				  String updateUserId)
    {
    	StringBuffer insertSql = new StringBuffer().append(" INSERT INTO UTASGI_PLG_PAAR_PMN_SGHT ( ")
												   .append("    REQUEST_ID, ")
												   .append("    SGHT_ID, ")
												   .append("    PMN_ACTION, ")
												   .append("    PMN_TYPE, ")
												   .append("    PMN_TYPE_ENTITY, ")
												   .append("    PMN_EFF_FROM_DATE, ")
												   .append("    PMN_EFF_THRU_DATE, ")
												   .append("    UPDT_USERID, ")
												   .append("    LAST_ACTION, ")
												   .append("    TIME_STAMP ) ")
												   .append(" VALUES ( ")
												   .append("    ?, ")
												   .append("    " + jdbcDao.getSchemaPrefix()+"SFDB_GUID(), ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    " + jdbcDao.getTimestampFunction() + " )");

    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(requestId);
    	params.addParameter(pmnAction);//UTAS-511
    	params.addParameter(pmnType);
    	params.addParameter(pmnTypeEntity);
    	params.addParameter(effFromDate);
    	params.addParameter(effThruDate);
    	params.addParameter(updateUserId);
    	params.addParameter(SoluminaConstants.INSERTED);
    	jdbcDao.insert(insertSql.toString(), params); 
    }
    
    public void deleteSuggestedPermissionProfileRecord(String sghtId, String requestId)
    {
    	StringBuffer delete = new StringBuffer().append("DELETE ")
    											.append("FROM ")
    											.append("	UTASGI_PLG_PAAR_PMN_SGHT ")
    											.append("WHERE ")
    											.append("	SGHT_ID = ? ")
    											.append("AND ")
    											.append("	REQUEST_ID = ? ");
    	
    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(sghtId);
    	params.addParameter(requestId);
    	
    	jdbcDao.delete(delete.toString(), params);
    }
	
    public void updateSuggestedPermissionProfileRecordToDeleted(String sghtId, String requestId, String pmnAction)
    {
    	StringBuffer update = new StringBuffer().append("UPDATE ")
    											.append("	UTASGI_PLG_PAAR_PMN_SGHT ")
    											.append("SET ")
												.append("	PMN_ACTION = ?, ")
												.append("	UPDT_USERID = ?, ")
												.append("	LAST_ACTION = ?, ")
												.append("	TIME_STAMP = " + jdbcDao.getTimestampFunction())
												.append("WHERE ")
												.append("	SGHT_ID = ? ")
												.append("AND ")
												.append("	REQUEST_ID = ? ");

    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(pmnAction);
    	params.addParameter(ContextUtil.getUsername());
    	params.addParameter(SoluminaConstants.UPDATED);
    	params.addParameter(sghtId);
    	params.addParameter(requestId);

    	jdbcDao.update(update.toString(), params);
    }
    
    public void updatePAARReviewHeader(String requestId, 
									   String requestReason,
									   Date requestRequiredDate)
    {
    	StringBuffer update = new StringBuffer().append("UPDATE ")
												.append("	UTASGI_PLG_PAAR_DESC ")
												.append("SET ")
												.append("	REQUEST_REASON = ?, ")
												.append("	REPLY_REQUIRED_DATE = ?, ")
												.append("	UPDT_USERID = ?, ")
												.append("	LAST_ACTION = ?, ")
												.append("	TIME_STAMP = " + jdbcDao.getTimestampFunction())
												.append("WHERE ")
												.append("	REQUEST_ID = ? ");

    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(requestReason);
    	params.addParameter(requestRequiredDate);
    	params.addParameter(ContextUtil.getUsername());
    	params.addParameter(SoluminaConstants.UPDATED);
    	params.addParameter(requestId);

    	jdbcDao.update(update.toString(), params);
    }
    
    public void updateProfile(String requestId, String sghtId, Date effFromDate, Date effThruDate)
    {
    	StringBuffer update = new StringBuffer().append("UPDATE ")
												.append("	UTASGI_PLG_PAAR_PMN_SGHT ")
												.append("SET ")
												.append("	PMN_EFF_FROM_DATE = ?, ")
												.append("	PMN_EFF_THRU_DATE = ?, ")
												.append("	UPDT_USERID = ?, ")
												.append("	LAST_ACTION = ?, ")
												.append("	TIME_STAMP = " + jdbcDao.getTimestampFunction())
												.append("WHERE ")
												.append("	REQUEST_ID = ? ")
												.append("AND ")
												.append("	SGHT_ID = ? ");

    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(effFromDate);
    	params.addParameter(effThruDate);
    	params.addParameter(ContextUtil.getUsername());
    	params.addParameter(SoluminaConstants.UPDATED);
    	params.addParameter(requestId);
    	params.addParameter(sghtId);

    	jdbcDao.update(update.toString(), params);
    }
    
    @SuppressWarnings("rawtypes")
	public Map selectDeletedRecordExists(String requestId, String pmnType, String pmnTypeEntity)
    {
    	StringBuffer select = new StringBuffer().append("SELECT SGHT_ID ")
												.append("FROM 	UTASGI_PLG_PAAR_PMN_SGHT ")
												.append("WHERE 	REQUEST_ID = ? ")
												.append("AND 	PMN_TYPE = ? ")
												.append("AND 	PMN_TYPE_ENTITY = ? ")
												.append("AND	PMN_ACTION = ? ");

    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(requestId);
    	params.addParameter(pmnType);
    	params.addParameter(pmnTypeEntity);
    	params.addParameter(SoluminaConstants.DELETED);

    	List list = jdbcDao.queryForList(select.toString(), params);
    	if(list != null && list.size() > 0)
    	{
    		return (Map) list.get(0);
    	}
    	return null;
    }
    
    public void updateExpirationDate(String requestId, 
									 String sghtId, 
									 Date effFromDate, 
									 Date effThruDate, 
									 String pmnAction)
    {
    	StringBuffer update = new StringBuffer().append("UPDATE ")
												.append("	UTASGI_PLG_PAAR_PMN_SGHT ")
												.append("SET ")
												.append("	PMN_EFF_FROM_DATE = ?, ")
												.append("	PMN_EFF_THRU_DATE = ?, ")
												.append("	PMN_ACTION = ?, ")
												.append("	UPDT_USERID = ?, ")
												.append("	LAST_ACTION = ?, ")
												.append("	TIME_STAMP = " + jdbcDao.getTimestampFunction())
												.append("WHERE ")
												.append("	REQUEST_ID = ? ")
												.append("AND ")
												.append("	SGHT_ID = ? ");

    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(effFromDate);
    	params.addParameter(effThruDate);
    	params.addParameter(pmnAction);
    	params.addParameter(ContextUtil.getUsername());
    	params.addParameter(SoluminaConstants.UPDATED);
    	params.addParameter(requestId);
    	params.addParameter(sghtId);

    	jdbcDao.update(update.toString(), params);
    }
    
    @Override
    public Map selectPlanInfoByPlanNumber(String planNumber, 
    									  Number planUpdateNumber) 
	{
        StringBuffer selectSql = new StringBuffer().append(" SELECT A.PLAN_ID, ")
        										   .append("  A.PLAN_VERSION, ")
        										   .append("  A.PLAN_REVISION, ")
        										   .append("  A.PLAN_ALTERATIONS, ")
        										   .append("  A.PLAN_UPDT_NO ")
                                                   .append("  FROM SFPL_PLAN_V A ")
                                                   .append(" WHERE A.PLAN_NO = ? ")
                                                   .append("   AND A.PLAN_UPDT_NO = ? ");

        ParameterHolder parameters = new ParameterHolder();
        parameters.addParameter(planNumber);
        parameters.addParameter(planUpdateNumber);
        
        return jdbcDao.queryForMap(selectSql.toString(), parameters);
    
	}

	@Override
	public void insertApprovalProfile(String pmnType, 
									  String pmnTypeEntity,
									  String pmnStatus, 
									  Date effFromDate,
									  Date effThruDate,
									  String requestId, 
									  String updateUserId) 
	{
		StringBuffer insertSql = new StringBuffer().append(" INSERT INTO UTASGE_PLG_PAAR_PMN_AUTH ( ")
												   .append("    REQUEST_ID, ")
												   .append("    AUTH_ID, ")
												   .append("    PMN_ACTION, ")
												   .append("    PMN_STATUS, ")
												   .append("    PMN_TYPE, ")
												   .append("    PMN_TYPE_ENTITY, ")
												   .append("    PMN_EFF_FROM_DATE, ")
												   .append("    PMN_EFF_THRU_DATE, ")
												   .append("    UPDT_USERID, ")
												   .append("    LAST_ACTION, ")
												   .append("    TIME_STAMP ) ")
												   .append(" VALUES ( ")
												   .append("    ?, ")
												   .append("    " + jdbcDao.getSchemaPrefix()+"SFDB_GUID(), ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    " + jdbcDao.getTimestampFunction() + " )");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(requestId);
		params.addParameter(UtasConstants.ADDED);
		params.addParameter(pmnStatus);
		params.addParameter(pmnType);
		params.addParameter(pmnTypeEntity);
		params.addParameter(effFromDate);
		params.addParameter(effThruDate);
		params.addParameter(updateUserId);
		params.addParameter(SoluminaConstants.INSERTED);
		jdbcDao.insert(insertSql.toString(), params); 
}

	@Override
	public Map selectDeletedApprovalRecordExists(String requestId,
											     String pmnType, 
											     String pmnTypeEntity) 
	{
		StringBuffer select = new StringBuffer().append("SELECT AUTH_ID ")
											    .append("FROM 	UTASGE_PLG_PAAR_PMN_AUTH ")
											    .append("WHERE 	REQUEST_ID = ? ")
											    .append("AND 	PMN_TYPE = ? ")
											    .append("AND 	PMN_TYPE_ENTITY = ? ")
											    .append("AND	PMN_ACTION = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(requestId);
		params.addParameter(pmnType);
		params.addParameter(pmnTypeEntity);
		params.addParameter(SoluminaConstants.DELETED);

		List list = jdbcDao.queryForList(select.toString(), params);
		if(list != null && list.size() > 0)
		{
			return (Map) list.get(0);
		}
		return null;
	}

	@Override
	public void updateApprovalProfileExpirationDate(String requestId,
													String authId, 
													String pmnStatus,
													Date effFromDate,
													Date effThruDate, 
													String pmnAction) 
	{	
    	StringBuffer update = new StringBuffer().append("UPDATE ")
												.append("	UTASGE_PLG_PAAR_PMN_AUTH ")
												.append("SET ")
												.append("	PMN_EFF_FROM_DATE = ?, ")
												.append("	PMN_EFF_THRU_DATE = ?, ")
												.append("	PMN_STATUS = ?, ")
												.append("	PMN_ACTION = ?, ")
												.append("	UPDT_USERID = ?, ")
												.append("	LAST_ACTION = ?, ")
												.append("	TIME_STAMP = " + jdbcDao.getTimestampFunction())
												.append("WHERE ")
												.append("	REQUEST_ID = ? ")
												.append("AND ")
												.append("	AUTH_ID = ? ");

    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(effFromDate);
    	params.addParameter(effThruDate);
    	params.addParameter(pmnStatus);
    	params.addParameter(SoluminaConstants.UPDATED);
    	params.addParameter(ContextUtil.getUsername());
    	params.addParameter(SoluminaConstants.UPDATED);
    	params.addParameter(requestId);
    	params.addParameter(authId);

    	jdbcDao.update(update.toString(), params);
	}

	@Override
	public void updateApprovalProfile(String requestId, 
									  String authId,
									  String pmnStatus, 
									  Date effFromDate,
									  Date effThruDate) 
	{
		StringBuffer update = new StringBuffer().append("UPDATE ")
												.append("	UTASGE_PLG_PAAR_PMN_AUTH ")
												.append("SET ")
												.append("	PMN_EFF_FROM_DATE = ?, ")
												.append("	PMN_EFF_THRU_DATE = ?, ")
												.append("	PMN_STATUS = ?, ")
												.append("	UPDT_USERID = ?, ")
												.append("	LAST_ACTION = ?, ")
												.append("	TIME_STAMP = " + jdbcDao.getTimestampFunction())
												.append("WHERE ")
												.append("	REQUEST_ID = ? ")
												.append("AND ")
												.append("	AUTH_ID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(effFromDate);
		params.addParameter(effThruDate);
		params.addParameter(pmnStatus);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(SoluminaConstants.UPDATED);
		params.addParameter(requestId);
		params.addParameter(authId);

		jdbcDao.update(update.toString(), params);
	}

	@Override
	public void deleteApprovalPermissionProfileRecord(String authId,
													  String requestId) 
	{
		StringBuffer delete = new StringBuffer().append("DELETE ")
												.append("FROM ")
												.append("	UTASGE_PLG_PAAR_PMN_AUTH ")
												.append("WHERE ")
												.append("	AUTH_ID = ? ")
												.append("AND ")
												.append("	REQUEST_ID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(authId);
		params.addParameter(requestId);

		jdbcDao.delete(delete.toString(), params);
	}

	@Override
	/*
	 * UTAS-496
	 * (non-Javadoc)
	 * @see com.utas.solumina.sfpl.application.dao.IUtasPlanDao#insertOrdSecGrpApprovalProfile(java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date, java.lang.String, java.lang.String)
	 */
	public void insertOrdSecGrpApprovalProfile(String pmnType,
											   String pmnTypeEntity,
											   String pmnStatus, 
											   Date effFromDate,
											   Date effThruDate, 
											   String requestId, 
											   String updateUserId) 
	{

		StringBuffer insertSql = new StringBuffer().append(" INSERT INTO UTASGE_WID_PAAR_PMN_AUTH ( ")
												   .append("    REQUEST_ID, ")
												   .append("    AUTH_ID, ")
												   .append("    PMN_ACTION, ")
												   .append("    PMN_STATUS, ")
												   .append("    PMN_TYPE, ")
												   .append("    PMN_TYPE_ENTITY, ")
												   .append("    PMN_EFF_FROM_DATE, ")
												   .append("    PMN_EFF_THRU_DATE, ")
												   .append("    UPDT_USERID, ")
												   .append("    LAST_ACTION, ")
												   .append("    TIME_STAMP ) ")
												   .append(" VALUES ( ")
												   .append("    ?, ")
												   .append("    " + jdbcDao.getSchemaPrefix()+"SFDB_GUID(), ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    ?, ")
												   .append("    " + jdbcDao.getTimestampFunction() + " )");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(requestId);
		params.addParameter(UtasConstants.ADDED);
		params.addParameter(pmnStatus);
		params.addParameter(pmnType);
		params.addParameter(pmnTypeEntity);
		params.addParameter(effFromDate);
		params.addParameter(effThruDate);
		params.addParameter(updateUserId);
		params.addParameter(SoluminaConstants.INSERTED);
		jdbcDao.insert(insertSql.toString(), params); 

	}

	@Override
	/*
	 * UTAS-496
	 * (non-Javadoc)
	 * @see com.utas.solumina.sfpl.application.dao.IUtasPlanDao#updateOrdSecGrpApprovalProfileExpirationDate(java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date, java.lang.String)
	 */
	public void updateOrdSecGrpApprovalProfileExpirationDate(String requestId,
															 String authId, 
															 String pmnStatus, 
															 Date effFromDate,
															 Date effThruDate, 
															 String pmnAction) 
	{
		
    	StringBuffer update = new StringBuffer().append("UPDATE ")
												.append("	UTASGE_WID_PAAR_PMN_AUTH ")
												.append("SET ")
												.append("	PMN_EFF_FROM_DATE = ?, ")
												.append("	PMN_EFF_THRU_DATE = ?, ")
												.append("	PMN_STATUS = ?, ")
												.append("	PMN_ACTION = ?, ")
												.append("	UPDT_USERID = ?, ")
												.append("	LAST_ACTION = ?, ")
												.append("	TIME_STAMP = " + jdbcDao.getTimestampFunction())
												.append("WHERE ")
												.append("	REQUEST_ID = ? ")
												.append("AND ")
												.append("	AUTH_ID = ? ");

    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(effFromDate);
    	params.addParameter(effThruDate);
    	params.addParameter(pmnStatus);
    	params.addParameter(SoluminaConstants.UPDATED);
    	params.addParameter(ContextUtil.getUsername());
    	params.addParameter(SoluminaConstants.UPDATED);
    	params.addParameter(requestId);
    	params.addParameter(authId);

    	jdbcDao.update(update.toString(), params);
	
	}

	@Override
	/*
	 * UTAS-496
	 * (non-Javadoc)
	 * @see com.utas.solumina.sfpl.application.dao.IUtasPlanDao#updateOrdSecGrpApprovalProfile(java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date)
	 */
	public void updateOrdSecGrpApprovalProfile(String requestId,
											   String authId,
											   String pmnStatus, 
											   Date effFromDate, 
											   Date effThruDate) 
	{
		StringBuffer update = new StringBuffer().append("UPDATE ")
												.append("	UTASGE_WID_PAAR_PMN_AUTH ")
												.append("SET ")
												.append("	PMN_EFF_FROM_DATE = ?, ")
												.append("	PMN_EFF_THRU_DATE = ?, ")
												.append("	PMN_STATUS = ?, ")
												.append("	UPDT_USERID = ?, ")
												.append("	LAST_ACTION = ?, ")
												.append("	TIME_STAMP = " + jdbcDao.getTimestampFunction())
												.append("WHERE ")
												.append("	REQUEST_ID = ? ")
												.append("AND ")
												.append("	AUTH_ID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(effFromDate);
		params.addParameter(effThruDate);
		params.addParameter(pmnStatus);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(SoluminaConstants.UPDATED);
		params.addParameter(requestId);
		params.addParameter(authId);

		jdbcDao.update(update.toString(), params);

	}

	@Override
	/*
	 * UTAS-496
	 * (non-Javadoc)
	 * @see com.utas.solumina.sfpl.application.dao.IUtasPlanDao#deleteOrdSecGrpApprovalPermissionProfileRecord(java.lang.String, java.lang.String)
	 */
	public void deleteOrdSecGrpApprovalPermissionProfileRecord(String authId,
															   String requestId) 
	{
		StringBuffer delete = new StringBuffer().append("DELETE ")
												.append("FROM ")
												.append("	UTASGE_WID_PAAR_PMN_AUTH ")
												.append("WHERE ")
												.append("	AUTH_ID = ? ")
												.append("AND ")
												.append("	REQUEST_ID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(authId);
		params.addParameter(requestId);

		jdbcDao.delete(delete.toString(), params);
	}

	@Override
	public List selectNonRestrictedSecGrp(String planId, Number planUpdtNo)
	{
        StringBuffer selectSql = new StringBuffer().append(" SELECT A.SECURITY_GROUP ")
                                                   .append("  FROM SFPL_PLAN_DESC_SEC_GRP A, SFFND_SECURITY_GROUP_DEF B ")
                                                   .append("WHERE A.SECURITY_GROUP = B.SECURITY_GROUP ")
                                                   .append("  AND A.PLAN_ID = ? ")
                                                   .append(" AND A.PLAN_UPDT_NO = ?")
                                                   .append("  AND NVL(B.UCF_SEC_GRP_FLAG1,?) != ? ");

        ParameterHolder params = new ParameterHolder();
        params.addParameter(planId);
        params.addParameter(planUpdtNo);
        params.addParameter(SoluminaConstants.N);
        params.addParameter(SoluminaConstants.R);

        return jdbcDao.queryForList(selectSql.toString(), params);
    }
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see com.utas.solumina.sfpl.application.dao.IUtasPlanDao#selectPlanNumber(java.lang.String, java.lang.Number)
	 */
	public String selectPlanWorkLocation(String planId, Number planUpdateNumber) 
	{
        StringBuffer selectSql = new StringBuffer().append("SELECT PLND_WORK_LOC ")
                                                   .append("  FROM SFPL_PLAN_DESC ")
                                                   .append(" WHERE PLAN_ID = ? ")
                                                   .append("   AND PLAN_UPDT_NO = ? ");

        ParameterHolder parameters = new ParameterHolder();
        parameters.addParameter(planId);
        parameters.addParameter(planUpdateNumber);
        
        return jdbcDao.queryForString(selectSql.toString(), parameters);
	}

	@Override
	public String selectRestrictedSecurityGroup(String planId,
												Number planUpdateNumber)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT A.SECURITY_GROUP ")
									               .append("   FROM SFFND_SECURITY_GROUP_DEF A, ")
									               .append("   		TABLE(SFFND_PARSE_FCN((SELECT SECURITY_GROUP FROM SFPL_PLAN_DESC WHERE PLAN_ID = ? AND PLAN_UPDT_NO = ?),',')) B ")
									               .append("  WHERE A.SECURITY_GROUP = B.COLUMN_VALUE ")
									               .append("    AND NVL(A.UCF_SEC_GRP_FLAG1,' ') = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdateNumber);
		params.addParameter(SoluminaConstants.R);

		List list = jdbcDao.queryForList(selectSql.toString(), params);
		if(list != null && list.size() > 0)
		{
			Map map = (Map) list.get(0);
			return (String) map.get("SECURITY_GROUP");
		}
		return null;
	}
	
	@Override
	public void updatePlanDescSecurityGroup(String planId, 
											Number planUpdateNumber, 
											String securityGroup)
	{
		StringBuffer update = new StringBuffer().append("UPDATE ")
												.append("	SFPL_PLAN_DESC ")
												.append("SET ")
												.append("	SECURITY_GROUP = ?, ")
												.append("	UPDT_USERID = ?, ")
												.append("	LAST_ACTION = ?, ")
												.append("	TIME_STAMP = " + jdbcDao.getTimestampFunction())
												.append("WHERE ")
												.append("	PLAN_ID = ? ")
												.append("AND ")
												.append("	PLAN_UPDT_NO = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(securityGroup);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(SoluminaConstants.UPDATED);
		params.addParameter(planId);
		params.addParameter(planUpdateNumber);

		jdbcDao.update(update.toString(), params);
	}
	
	@Override
	public boolean selectRestrictedSecurityGroupExistsInOtherRevision(String planNumber,
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
	
	/*
	 * (non-Javadoc)
	 * @see com.utas.solumina.sfpl.application.dao.IUtasPlanDao#deletePlanDescReferenceSecurityGroup(java.lang.String, java.lang.String)
	 */
	public void deletePlanDescReferenceSecurityGroup(String planNumber,
			 										 String securityGroup)
	{
		StringBuffer delete = new StringBuffer().append("DELETE ")
												.append("FROM ")
												.append("	SFPL_PLAN_DESC_SEC_GRP ")
												.append("WHERE ")
												.append("	PLAN_NO = ? ")
												.append("AND ")
												.append("	SECURITY_GROUP = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(planNumber);
		params.addParameter(securityGroup);

		jdbcDao.delete(delete.toString(), params);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.utas.solumina.sfpl.application.dao.IUtasPlanDao#selectRestrictedSecurityGroupUserList(java.lang.String)
	 */
	public List selectRestrictedSecurityGroupUserList(String securityGroup)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT USERID, ")
												   .append("	   EFFECTIVE_START_DATE, ")
												   .append("	   EFFECTIVE_END_DATE ")
									               .append("  FROM SFFND_USER_SEC_GRP ")
									               .append(" WHERE SECURITY_GROUP = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(securityGroup);

		return jdbcDao.queryForList(selectSql.toString(), parameters);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.utas.solumina.sfpl.application.dao.IUtasPlanDao#selectSecurityGroupListAssignedForUser(java.lang.String)
	 */
	public List selectSecurityGroupListAssignedForUser(String userId)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT SECURITY_GROUP ")
												   .append("  FROM SFFND_USER_SEC_GRP ")
									               .append(" WHERE USERID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(userId);

		return jdbcDao.queryForList(selectSql.toString(), parameters);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.utas.solumina.sfpl.application.dao.IUtasPlanDao#updateUserProfile(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date)
	 */
	public void updateUserProfile(String requestId,
								  String pmnType,
								  String pmnTypeEntity, 
								  String pmnAction,
								  Date effFromDate, 
								  Date effThruDate) 
	{
		StringBuffer update = new StringBuffer().append("UPDATE ")
												.append("	UTASGI_PLG_PAAR_PMN_SGHT ")
												.append("SET ")
												.append("	PMN_EFF_FROM_DATE = ?, ")
												.append("	PMN_EFF_THRU_DATE = ?, ")
												.append("	PMN_ACTION = ?, ")
												.append("	UPDT_USERID = ?, ")
												.append("	LAST_ACTION = ?, ")
												.append("	TIME_STAMP = " + jdbcDao.getTimestampFunction())
												.append("WHERE ")
												.append("	REQUEST_ID = ? ")
												.append("AND ")
												.append("	PMN_TYPE = ? ")
												.append("AND ")
												.append("	PMN_TYPE_ENTITY = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(effFromDate);
		params.addParameter(effThruDate);
		params.addParameter(pmnAction);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(SoluminaConstants.UPDATED);
		params.addParameter(requestId);
		params.addParameter(pmnType);
		params.addParameter(pmnTypeEntity);

		jdbcDao.update(update.toString(), params);

	}
	
}
