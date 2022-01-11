package com.utas.solumina.sfwid.application.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;
import com.utas.solumina.common.UtasConstants;
import com.utas.solumina.sfwid.application.dao.IUtasOrderDao;

public class UtasOrderDaoImpl implements IUtasOrderDao 
{
	
	@Reference
	JdbcDaoSupport jdbcDao;

	@Override
	public Date selectDBTimeStamp() 
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT ")
				   .append(jdbcDao.getSchemaPrefix() + "SFDB_SYSDATE()" )
				   .append(jdbcDao.getDualTable());

		return (Date) jdbcDao.queryForObject(selectSql.toString(), Date.class);
	}

	@Override
	public int deleteWorkOrderSecurityGroup(String orderNumber) 
	{
		StringBuffer deleteSql = new StringBuffer().append("DELETE FROM ")
				   								   .append("    SFWID_ORDER_DESC_SEC_GRP ")
				   								   .append("WHERE ")
				   								   .append("    ORDER_NO = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderNumber);

		return jdbcDao.delete(deleteSql.toString(), parameters);
	}

	@Override
	public void updatePAARRequestStatus(String orderId, String requestStatus) 
	{
		StringBuffer update = new StringBuffer().append("UPDATE UTASGI_WID_PAAR_DESC ")
			    								.append("   SET REQUEST_STATUS = ?, ")			    								
			    								.append("       UPDT_USERID = ?, ")
                                                .append("       LAST_ACTION = ?, ")
                                                .append("       TIME_STAMP = " + jdbcDao.getTimestampFunction())
			    								.append(" WHERE ORDER_ID = ? ")
			    								.append("   AND REQUEST_STATUS = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(UtasConstants.CANCELLED);		
		params.addParameter(ContextUtil.getUsername());
        params.addParameter(SoluminaConstants.UPDATED);
		params.addParameter(orderId);
		params.addParameter(requestStatus);

		jdbcDao.update(update.toString(), params);
	}

	@Override
	public String selectExistingRequestIdAsPerStatus(String orderId,
													 String requestStatus)
	{
	    String result = StringUtils.EMPTY;
	    
		StringBuffer selectSql = new StringBuffer().append(" SELECT X.REQUEST_ID                   ")
				   								   .append("   FROM (SELECT REQUEST_ID             ")
				   								   .append("           FROM UTASGI_WID_PAAR_DESC   ")
				   								   .append("          WHERE ORDER_ID = ?           ")
				   								   .append("            AND REQUEST_STATUS = ?     ")
				   								   .append("          ORDER BY TIME_STAMP DESC) X  ")
				   								   .append("  WHERE ROWNUM = 1                     ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(requestStatus);
		
		try
		{
		
		    result = jdbcDao.queryForString(selectSql.toString(), params);
		
		}
		catch (EmptyResultDataAccessException e) 
        {
             // There isn't an existing request. That's OK - return empty string.
        }
		
		return result;
	}

	@Override
	public void insertPaarDesc(String requestId, 
							   String requestStatus,
							   String requestUser, 
							   String requestReason, 
							   String orderId,
							   Date replyReqDate, 
							   String priorRequestId) 
	{
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO UTASGI_WID_PAAR_DESC( ")
				   								   .append(" REQUEST_ID,            ")  
				   								   .append(" REQUEST_STATUS,        ")
				   								   .append(" REQUEST_USERID,        ")
				   								   .append(" REQUEST_CREATION_DATE, ")
				   								   .append(" REQUEST_REASON,        ")
				   								   .append(" ORDER_ID,              ")
				   								   .append(" REPLY_REQUIRED_DATE,   ")
				   								   .append(" PRIOR_REQUEST_ID,      ")
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
				   								   .append(jdbcDao.getTimestampFunction()+ " , ")
				   								   .append("    ?) ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(requestId);
		params.addParameter(requestStatus);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(requestReason);
		params.addParameter(orderId);
		params.addParameter(replyReqDate);
		params.addParameter(priorRequestId);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(SoluminaConstants.INSERTED);

		jdbcDao.insert(insertSql.toString(), params);
	}

	@Override
	public void insertSuggestedSecurityGroupProfleFromPastAuthoredProfile(String currentRequestId, 
																		  String priorRequestId) 
	{
		StringBuffer insertSql = new StringBuffer().append(" INSERT INTO UTASGI_WID_PAAR_PMN_SGHT  ")
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
				   								   .append("     FROM UTASGI_WID_PAAR_PMN_AUTH     ")
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
	public void insertSuggestedSecurityGroupProfleFromPastCancelledProfile(String currentRequestId, 
																		   String priorRequestId) 
	{
		StringBuffer insertSql = new StringBuffer().append(" INSERT INTO UTASGI_WID_PAAR_PMN_SGHT  ")
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
				   								   .append("     FROM UTASGI_WID_PAAR_PMN_SGHT     ")
				   								   .append("    WHERE REQUEST_ID = ?               ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(currentRequestId);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(SoluminaConstants.INSERTED);
		params.addParameter(priorRequestId);

		jdbcDao.insert(insertSql.toString(), params);
		
	}
	
    public void insertSuggestedSecurityGroupProfleFromOrder(String orderNo, 
                                                            String currentRequestId) 
    {
        StringBuffer insertSql = new StringBuffer().append(" INSERT INTO UTASGI_WID_PAAR_PMN_SGHT  ")
                                                   .append("   (REQUEST_ID,                        ")
                                                   .append("    SGHT_ID,                           ")
                                                   .append("    PMN_ACTION,                        ")
                                                   .append("    PMN_TYPE,                          ")
                                                   .append("    PMN_TYPE_ENTITY,                   ")
                                                   .append("    UPDT_USERID,                       ")
                                                   .append("    TIME_STAMP,                        ")
                                                   .append("    LAST_ACTION)                       ")
                                                   .append("   SELECT ?,                           ")
                                                   .append("          SFDB_GUID,                   ")
                                                   .append("          'PERSISTED',                 ")
                                                   .append("          'SECURITY GROUP',            ")
                                                   .append("          SECURITY_GROUP,              ")
                                                   .append("          ?,                           ")
                                                   .append( jdbcDao.getTimestampFunction() +   " , ")
                                                   .append("          ?                            ")
                                                   .append("     FROM SFWID_ORDER_DESC_SEC_GRP     ")
                                                   .append("    WHERE ORDER_NO = ?                 ");

        ParameterHolder params = new ParameterHolder();
        params.addParameter(currentRequestId);
        params.addParameter(ContextUtil.getUsername());
        params.addParameter(SoluminaConstants.INSERTED);
        params.addParameter(orderNo);
        
        jdbcDao.insert(insertSql.toString(), params);
    }	
	
	public void updatePAARReviewHeader(String requestId, 
			   						   String requestReason,
			   						   Date requestRequiredDate)
	{
		StringBuffer update = new StringBuffer().append("UPDATE ")
												.append("	UTASGI_WID_PAAR_DESC ")
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
	
	public void deleteSuggestedPermissionProfileRecord(String sghtId, String requestId)
    {
    	StringBuffer delete = new StringBuffer().append("DELETE ")
    											.append("FROM ")
    											.append("	UTASGI_WID_PAAR_PMN_SGHT ")
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
    											.append("	UTASGI_WID_PAAR_PMN_SGHT ")
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
	
	public void insertProfile(String pmnType, 
			  				  String pmnTypeEntity, 
			  				  String action, 
			  				  Date effFromDate, 
			  				  Date effThruDate, 
			  				  String requestId, 
			  				  String updateUserId)
	{
		StringBuffer insertSql = new StringBuffer().append(" INSERT INTO UTASGI_WID_PAAR_PMN_SGHT ( ")
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
		params.addParameter(action);
		params.addParameter(pmnType);
		params.addParameter(pmnTypeEntity);
		params.addParameter(effFromDate);
		params.addParameter(effThruDate);
		params.addParameter(updateUserId);
		params.addParameter(SoluminaConstants.INSERTED);
		jdbcDao.insert(insertSql.toString(), params); 
	}
	
	public Map selectDeletedRecordExists(String requestId, String pmnType, String pmnTypeEntity)
    {
    	StringBuffer select = new StringBuffer().append("SELECT SGHT_ID ")
												.append("FROM 	UTASGI_WID_PAAR_PMN_SGHT ")
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
												.append("	UTASGI_WID_PAAR_PMN_SGHT ")
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
		params.addParameter(SoluminaConstants.UPDATED);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(SoluminaConstants.UPDATED);
		params.addParameter(requestId);
		params.addParameter(sghtId);

		jdbcDao.update(update.toString(), params);
	}
	
	public void updateProfile(String requestId, String sghtId, Date effFromDate, Date effThruDate)
    {
    	StringBuffer update = new StringBuffer().append("UPDATE ")
												.append("	UTASGI_WID_PAAR_PMN_SGHT ")
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

	@Override
	public List selectWorkLocSecGrpOfOrder(String orderId)
	{

		StringBuffer select = new StringBuffer().append(" SELECT DISTINCT A.SECURITY_GROUP ")
		  	    								.append("   FROM UTASGI_USER_SEC_GRP_XREF A, SFWID_ORDER_DESC C ")
		  	    								.append("  WHERE  A.HR_LOC = C.ASGND_WORK_LOC ")		  	    								
		  	    								.append("   AND C.ORDER_ID = ? ");												

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);

		return jdbcDao.queryForList(select.toString(), params);
	}
}
