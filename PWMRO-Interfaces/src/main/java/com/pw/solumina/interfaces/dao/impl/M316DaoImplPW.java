/*
 * This unpublished work, first created in 2019 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2019.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    M316DaoImplPW.java
 * 
 *  Created: 2019-08-05
 * 
 *  Author:  B. Corson
 * 
 *  Revision History
 * 
 *  Date          Who            Description
 *  -----------   ------------   -----------------------------------------------------------
 *  2019-08-05    B. Corson      Initial Release SMRO_MD_M316 - Scrap Codes and Hold Types Groups Interface
 *  
 */
package com.pw.solumina.interfaces.dao.impl;

import java.util.List;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;
import com.pw.solumina.interfaces.dao.IM316DaoPW;

public class M316DaoImplPW implements IM316DaoPW {
    
    @Reference
    private JdbcDaoSupport jdbcDao;
    
    @SuppressWarnings("rawtypes")
    public List getM316ColumnLengths() {
        
        StringBuffer selectSql = new StringBuffer().append(" SELECT T.DATA_LENGTH,T.COLUMN_NAME ")
                                                   .append("   FROM DBA_TAB_COLS T ")
                                                   .append("  WHERE T.TABLE_NAME = ? ") 
                                                   .append("    AND T.DATA_TYPE =  ? ");
        
        ParameterHolder params = new ParameterHolder();
        params.addParameter("PWUST_GENERAL_CATALOG_LOOKUP");
        params.addParameter("VARCHAR2");
        
        return jdbcDao.queryForList(selectSql.toString(), params);
    }
    
    public void insertM316CatCode(String workLoc,
                                  String catalog,
                                  String category,
                                  String code,
                                  String catDesc,
                                  String codeDesc,
                                  String obsoleteRecordFlag,
                                  String updtUserid)
    {
        
        StringBuffer insertSql = new StringBuffer().append(" INSERT INTO PWUST_GENERAL_CATALOG_LOOKUP ( ") 
                                                   .append("    WORK_LOC, ") 
                                                   .append("    CATALOG, ")
                                                   .append("    CATEGORY, ")
                                                   .append("    CATEGORY_DESC, ")
                                                   .append("    CODE, ")
                                                   .append("    CODE_DESC, ")
                                                   .append("    OBSOLETE_RECORD_FLAG, ");
                                                    
        ParameterHolder params = new ParameterHolder();
        params.addParameter(workLoc);
        params.addParameter(catalog);
        params.addParameter(category);
        params.addParameter(catDesc);
        params.addParameter(code);
        params.addParameter(codeDesc);
        params.addParameter(obsoleteRecordFlag);
        if (updtUserid == null || updtUserid.isEmpty())
            insertSql.append("    TIME_STAMP ")
                     .append("    ) VALUES ( ")
                     .append("    ?, ?, ?, ?, ?, ?, ?, ")
                     .append(     jdbcDao.getTimestampFunction() + ")" );
        else {
            insertSql.append("    UPDT_USERID, ")
                     .append("    TIME_STAMP ")
                     .append("    ) VALUES ( ")
                     .append("    ?, ?, ?, ?, ?, ?, ?, ?, ")
                     .append(     jdbcDao.getTimestampFunction() + ")" );
            params.addParameter(updtUserid);
        }
        
        jdbcDao.insert(insertSql.toString(), params);
        
    }
    
    public int updateM316CatCode(String workLoc,
                                 String catalog,
                                 String category,
                                 String code,
                                 String catDesc,
                                 String codeDesc,
                                 String obsoleteRecordFlag,
                                 String updtUserid)
    {
        
        StringBuffer updateSql = new StringBuffer().append(" UPDATE PWUST_GENERAL_CATALOG_LOOKUP ") 
                                                   .append("    SET CATEGORY_DESC = ?, ") 
                                                   .append("        CODE_DESC = ?, ")
                                                   .append("        UPDT_USERID = ?, ")
                                                   .append("        TIME_STAMP = " )
                                                   .append( jdbcDao.getTimestampFunction() )
                                                   .append("  WHERE WORK_LOC = ? ")
                                                   .append("    AND CATALOG = ? ")
                                                   .append("    AND CATEGORY = ? ")
                                                   .append("    AND CODE = ? ");
                              
        ParameterHolder params = new ParameterHolder();
        params.addParameter(catDesc);
        params.addParameter(codeDesc);
        if (updtUserid == null || updtUserid.isEmpty())
            params.addParameter(ContextUtil.getUsername());
        else
            params.addParameter(updtUserid);
        params.addParameter(workLoc);
        params.addParameter(catalog);
        params.addParameter(category);
        params.addParameter(code);
        
        return jdbcDao.update(updateSql.toString(), params);
    }
    
    public int updateM316ObsoleteFL(String workLoc,
                                    String catalog,
                                    String category,
                                    String code,
                                    String obsoleteRecordFlag,
                                    String updtUserid)
    {
        
        StringBuffer updateSql = new StringBuffer().append(" UPDATE PWUST_GENERAL_CATALOG_LOOKUP ") 
                                                   .append("    SET OBSOLETE_RECORD_FLAG = ?, ") 
                                                   .append("        UPDT_USERID = ?, ")
                                                   .append("        TIME_STAMP = " )
                                                   .append( jdbcDao.getTimestampFunction() )
                                                   .append("  WHERE WORK_LOC = ? ")
                                                   .append("    AND CATALOG = ? ")
                                                   .append("    AND CATEGORY = ? ")
                                                   .append("    AND CODE = ? ");
         
        ParameterHolder params = new ParameterHolder();
        params.addParameter(obsoleteRecordFlag);
        if (updtUserid == null || updtUserid.isEmpty())
            params.addParameter(ContextUtil.getUsername());
        else
            params.addParameter(updtUserid);
        params.addParameter(workLoc);
        params.addParameter(catalog);
        params.addParameter(category);
        params.addParameter(code);
        
        return jdbcDao.update(updateSql.toString(), params);
    }
    
    public boolean catCodeExists(String workLoc,
                                 String catalog,
                                 String category,
                                 String code) 
    {
        
        Boolean exists = false;
        
        StringBuffer selectSql = new StringBuffer().append(" SELECT *  ")
                                                   .append("   FROM PWUST_GENERAL_CATALOG_LOOKUP ") 
                                                   .append("  WHERE WORK_LOC = ? ")
                                                   .append("    AND CATALOG = ? ")
                                                   .append("    AND CATEGORY = ? ")
                                                   .append("    AND CODE = ? ");
                                                   
        ParameterHolder params = new ParameterHolder();
        params.addParameter(workLoc);
        params.addParameter(catalog);
        params.addParameter(category);
        params.addParameter(code);
        
        List list = jdbcDao.queryForList(selectSql.toString(), params);
        if(list.size() > 0) exists = true;
        
        return exists;
    }
}
