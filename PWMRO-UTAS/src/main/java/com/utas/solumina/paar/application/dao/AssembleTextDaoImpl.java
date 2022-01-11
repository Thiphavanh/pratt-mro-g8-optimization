package com.utas.solumina.paar.application.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.sql.ParameterHolder;

public class AssembleTextDaoImpl
{
    @Reference
    ExtensionDaoSupport dao;

    public Map getPaarReqHdr(String requestId)
    {
        Map result = new HashMap();
        StringBuffer sqlText = new StringBuffer();
        
        sqlText.append("select request_id, request_status, request_userid, request_creation_date, ")
               .append(" plan_id, plan_version, plan_revision, plan_alterations, ")
               .append(" reply_required_date, prior_request_id ")
               .append(" from utasgi_plg_paar_desc ")
               .append(" where request_id = ? ");
        
        ParameterHolder params = new ParameterHolder();
        params.addParameter(requestId);
        
        try
        {
            result = dao.queryForMap(sqlText.toString(), params);
        }
        catch (IncorrectResultSizeDataAccessException e)
        {
            // do nothing, we'll just return an empty map
        }   
        
        return result;
    }
    
    public Map getWIDPaarReqHdr(String requestId)
    {
        Map result = new HashMap();
        StringBuffer sqlText = new StringBuffer();
        
        sqlText.append("select request_id, request_status, request_userid, request_creation_date, ")
               .append(" order_id, ")
               .append(" reply_required_date, prior_request_id ")
               .append(" from utasgi_wid_paar_desc ")
               .append(" where request_id = ? ");
        
        ParameterHolder params = new ParameterHolder();
        params.addParameter(requestId);
        
        try
        {
            result = dao.queryForMap(sqlText.toString(), params);
        }
        catch (IncorrectResultSizeDataAccessException e)
        {
            // do nothing, we'll just return an empty map
        }   
        
        return result;
    }
    
    public Map getPaarReqHdrForApproval(String requestId)
    {
        Map result = new HashMap();
        StringBuffer sqlText = new StringBuffer();
        
        sqlText.append("select request_id, request_status, request_userid, request_creation_date, ")
               .append(" plan_no, plan_version, plan_revision, plan_alterations, ")
               .append(" reply_required_date, prior_request_id ")
               .append(" from utasge_plg_paar_desc ")
               .append(" where request_id = ? ");
        
        ParameterHolder params = new ParameterHolder();
        params.addParameter(requestId);
        
        try
        {
            result = dao.queryForMap(sqlText.toString(), params);
        }
        catch (IncorrectResultSizeDataAccessException e)
        {
            // do nothing, we'll just return an empty map
        }   
        
        return result;
    }
    
    //UTAS-496
    public Map getPaarOrderSecGrpReqHdrForApproval(String requestId)
    {
        Map result = new HashMap();
        StringBuffer sqlText = new StringBuffer();
        
        sqlText.append(" SELECT REQUEST_ID, REQUEST_STATUS, REQUEST_USERID, REQUEST_CREATION_DATE, ")
               .append(" REPLY_REQUIRED_DATE, PRIOR_REQUEST_ID ")
               .append(" FROM UTASGE_WID_PAAR_DESC ")
               .append(" WHERE REQUEST_ID = ? ");
        
        ParameterHolder params = new ParameterHolder();
        params.addParameter(requestId);
        
        try
        {
            result = dao.queryForMap(sqlText.toString(), params);
        }
        catch (IncorrectResultSizeDataAccessException e)
        {
            // do nothing, we'll just return an empty map
        }   
        return result;
    }
    
}
