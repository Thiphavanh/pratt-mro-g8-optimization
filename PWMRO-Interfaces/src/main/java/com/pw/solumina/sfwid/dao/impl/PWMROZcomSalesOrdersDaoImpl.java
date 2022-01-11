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
*  File:    PWMROZcomSalesOrdersDaoImpl.java
* 
*  Created: 2019-12-10
* 
*  Author:  X005703
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2019-12-10 		X005703		    Initial Release IBaset delivery cockpit
*/


package com.pw.solumina.sfwid.dao.impl;

import static com.ibaset.common.FrameworkConstants.YES;
import static com.ibaset.common.SoluminaConstants.*;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;
import com.pw.solumina.sfwid.dao.IPWMROZcomSalesOrdersDao;

public class PWMROZcomSalesOrdersDaoImpl implements IPWMROZcomSalesOrdersDao
{
    @Reference
    private JdbcDaoSupport dao;
    
    @Override
    public void insertZcomSalesOrder(String workLocation,
                                     String zComSalesOrder,
                                     String description,
                                     String soldTo)
    {
        StringBuilder insertSql = new StringBuilder().append(" INSERT ")
                                                     .append("    INTO")
                                                     .append("        PWMROI_ZCOM_SALES_ORDERS")
                                                     .append("        (WORK_LOCATION, ZCOM_SALES_ORDER, DESCRIPTION, SOLD_TO, OBSOLETE_RECORD_FLAG, LAST_ACTION, UPDT_USERID, TIME_STAMP) ")
                                                     .append("    VALUES")
                                                     .append("        (?,?,?,?,?,?,?,"
                                                             + dao.getTimestampFunction()
                                                             + ")");

        ParameterHolder params = new ParameterHolder();
        params.addParameter(workLocation);
        params.addParameter(zComSalesOrder);
        params.addParameter(description);
        params.addParameter(soldTo);
        params.addParameter(NO);
        params.addParameter(INSERTED);
        params.addParameter(ContextUtil.getUsername());

        dao.insert(insertSql.toString(), params);

    }
    
    @Override
    public void updateZcomSalesOrder(String workLocation,
                                     String zComSalesOrder,
                                     String description,
                                     String soldTo,
                                     String obsoleteFlag)
    {
        StringBuilder selectSql = new StringBuilder().append("UPDATE  ")
                                                     .append("   PWMROI_ZCOM_SALES_ORDERS ")
                                                     .append(" SET ")
                                                     .append(" DESCRIPTION= ?, SOLD_TO = ?, OBSOLETE_RECORD_FLAG = ?, LAST_ACTION = ?, ")
                                                     .append(" UPDT_USERID=? , TIME_STAMP =  "
                                                             + dao.getTimestampFunction())
                                                     .append(" where WORK_LOCATION = ? and ZCOM_SALES_ORDER = ?");

        ParameterHolder params = new ParameterHolder();
        params.addParameter(description);
        params.addParameter(soldTo);
        params.addParameter(obsoleteFlag);
        params.addParameter(UPDATED);
        params.addParameter(ContextUtil.getUsername());
        params.addParameter(workLocation);
        params.addParameter(zComSalesOrder);

        dao.update(selectSql.toString(), params);
    }

    public boolean isZcomSalesOrderReferencedInScrap(String zComSalesOrder)
    {
        String sql = "SELECT 1 " 
        		+ dao.getDualTable()
        		+ " WHERE EXISTS ("
        		+ "    SELECT 1 "
        		+ "      FROM pwmroi_serial_scrap "
        		+ "     WHERE zcom_sales_order = ?) ";

        ParameterHolder parameters = new ParameterHolder();
        parameters.addParameter(zComSalesOrder);

        return (dao.queryForList(sql, parameters).size() > 0);
    }

    public void obsoleteZcomSalesOrder(String workLocation, String zComSalesOrder)
    {
        String sql = " UPDATE pwmroi_zcom_sales_orders "
        		+ "   SET obsolete_record_flag = ?, "
        		+ "       last_action = ?, "
        		+ "       updt_userid = ?, " 
        		+ "       time_stamp = " + dao.getTimestampFunction()
        		+ " WHERE work_location = ? "
        		+ "   AND UPPER(zcom_sales_order) = UPPER(?) ";

        ParameterHolder params = new ParameterHolder();
        params.addParameter(YES);
        params.addParameter(UPDATED);
        params.addParameter(ContextUtil.getUsername());
        params.addParameter(workLocation);
        params.addParameter(zComSalesOrder);

        dao.update(sql.toString(), params);
    }

    @Override
    public void deleteZcomSalesOrder(String workLocation, String zComSalesOrder)
    {
        StringBuilder selectSql = new StringBuilder().append("DELETE FROM ")
                                                     .append("    PWMROI_ZCOM_SALES_ORDERS ")
                                                     .append(" WHERE WORK_LOCATION = ? and ZCOM_SALES_ORDER = ?");

        ParameterHolder params = new ParameterHolder();
        params.addParameter(workLocation);
        params.addParameter(zComSalesOrder);
        dao.delete(selectSql.toString(), params);
    }    
}
