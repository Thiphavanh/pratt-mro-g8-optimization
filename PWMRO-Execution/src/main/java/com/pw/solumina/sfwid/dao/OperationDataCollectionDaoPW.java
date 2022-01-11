/*
 *  This unpublished work, first created in 2019 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2019.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    OperationDataCollectionDaoPW.java
 * 
 *  Created: 2019-02-27
 * 
 *  Author:  B. Corson
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2019-02-25      B. Corson       SMRO_WOE_306 - New class - Clone methods getOrderOperationDC() and getOrderOperationDCAltLog(). 
 */
package com.pw.solumina.sfwid.dao;

import java.util.List;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;

public class OperationDataCollectionDaoPW {
    @Reference 
    private static JdbcDaoSupport jdbcDaoSupport;
    
    public List getOrderOperationDC(String orderId,
                                    Number operKey, 
                                    Number stepKey,
                                    String refId, 
                                    String orderControlType,
                                    String userId)
    {
        
        StringBuffer select = new StringBuffer().append("SELECT A.ORDER_ID,")
                                                .append("       A.OPER_NO,")
                                                .append("       A.STEP_NO,")
                                                .append("       A.DAT_COL_ID,")
                                                .append("       A.OPER_KEY,")
                                                .append("       A.STEP_KEY,")
                                                .append("       C.DAT_COL_TYPE,")
                                                .append("       A.STD_DATCOL_ID,")
                                                .append("       A.DAT_COL_CERT,")
                                                .append("       A.DAT_COL_UOM,")
                                                .append("       A.DAT_COL_TITLE,")
                                                .append("       A.UPPER_LIMIT,")
                                                .append("       A.LOWER_LIMIT,")
                                                .append("       A.TARGET_VALUE,")
                                                .append("       A.ALT_ID,")
                                                .append("       A.ALT_COUNT,")
                                                .append("       A.REF_ID,")
                                                .append("       A.DISPLAY_LINE_NO,")
                                                .append("       CASE A.ORIENTATION_FLAG")
                                                .append("         WHEN 'D' THEN")
                                                .append("          'Data Collection Centric'")
                                                .append("         WHEN 'U' THEN")
                                                .append("          'Unit Centric'")
                                                .append("       END AS ORIENTATION_FLAG,")
                                                .append("       A.CROSS_ORDER_FLAG,")
                                                .append("       OPTIONAL_FLAG,")
                                                .append("       B.DCVALUE  AS VALUE, /*hidden based on order_control_type */")
                                                .append("       B.COMMENTS AS COMMENTS, /*hidden based on order_control_type */")
                                                .append("       NULL    AS   UDV_GRID_TERMINATOR,")
                                                .append("       B.UPDT_USERID,")
                                                .append("       B.TIME_STAMP,")
                                                .append("       VARIABLE_NAME,")
                                                .append("       VISIBILITY,")
                                                .append("       NUM_DECIMAL_DIGITS,")
                                                .append("       CASE CALC_DC_FLAG WHEN 'Y' THEN 'CALCULATED_DC' END AS CALC_DC_FLAG,")
                                                .append("       CALC_DC_FLAG AS CALC_FLAG,")
                                                .append("       A.DAT_COL_TITLE AS DAT_COL_TITLE_DISP,")
                                                .append("       CASE WHEN A.SLIDE_EMBEDDED_REF_ID IS NULL THEN NULL ")
                                                .append("	    WHEN SFMFG.SFFND_MM_SECURITY_GROUP_OK(a.SLIDE_ID,?) <> 'Y' then NULL")
                                                .append("	    ELSE 'Image' ")
                                                .append("       END AS IMAGE,")
                                                .append("       A.SLIDE_ID,")
                                                .append("       A.SLIDE_EMBEDDED_REF_ID,")
                                                .append("       A.AUDIT_FLAG, ")
                                                .append("       A.RESULT_ID, ")
                                                .append("       V.VALID_RESULT_TYPE, ")
                                                .append("       NULL AS FILE1, ")
                                                .append(" 		CASE WHEN O.OBJECT_REFERENCE IS NOT NULL THEN " +  jdbcDaoSupport.getSchemaPrefix() + "SFDB_SUBSTR_VARCHAR(O.OBJECT_REFERENCE,"
                                                        + jdbcDaoSupport.getSchemaPrefix() + "SFDB_INSTR_VARCHAR(O.OBJECT_REFERENCE, '\\', -1, 1)+1, " + jdbcDaoSupport.getSchemaPrefix() + "SFDB_LENGTH_VARCHAR(O.OBJECT_REFERENCE)) ELSE NULL END AS FILE_DISP, ")
                                                .append("       U.FORMAT, ")
                                                .append("       O.SECURITY_GROUP AS SECURITY_GROUP, ")
                                                .append("       A.UCF_OPER_DC_FLAG1, ")
                                                .append("       A.UCF_OPER_DC_FLAG2, ")
                                                .append("       A.UCF_OPER_DC_VCH255_1 ")
                                                .append("  FROM SFWID_OPER_DAT_COL A  LEFT OUTER JOIN SFSQA_VALID_RESULT_TYPE_DEF V ON (A.RESULT_ID = V.RESULT_ID) ")
                                                .append("                                   LEFT OUTER JOIN SFCORE_MM_OBJECT O ON (A.TEMPLATE_FILE_ID = O.OBJECT_ID) ")
                                                .append("       LEFT OUTER JOIN /* Outer Join needed for multi-serial orders */")
                                                .append("      (SELECT T.ORDER_ID,")
                                                .append("              T.OPER_KEY,")
                                                .append("              T.STEP_KEY,")
                                                .append("              T.DAT_COL_ID,")
                                                .append("              T.DCVALUE,")
                                                .append("              T.COMMENTS,")
                                                .append("              T.UPDT_USERID,")
                                                .append("              T.TIME_STAMP")
                                                .append("         FROM SFWID_SERIAL_OPER_DAT_COL T")
                                                .append("              /* Parameter, order_control_type, is in Scope query */")
                                                .append("        WHERE ? NOT IN ('SERIAL', 'SERIAL-LOT')) B ")
                                                .append("       ON A.ORDER_ID = B.ORDER_ID")
                                                .append("      AND A.OPER_KEY = B.OPER_KEY")
                                                .append("      AND A.STEP_KEY = B.STEP_KEY")
                                                .append("      AND A.DAT_COL_ID = B.DAT_COL_ID, ")
                                                .append("      SFFND_STD_DATCOL_TYPE_DEF C , SFFND_UOM_DEF U ")
                                                .append(" WHERE A.ORDER_ID = ?")
                                                .append("   AND A.OPER_KEY = ?")
                                                .append("   AND A.STEP_KEY = ?")
                                                .append("   AND A.REF_ID = ?")
                                                .append("   AND A.STD_DATCOL_ID = C.STD_DATCOL_ID ")
                                                .append(" AND A.DAT_COL_UOM = U.UOM ")
                                                .append(" ORDER BY A.DISPLAY_LINE_NO ");
        ParameterHolder params = new ParameterHolder();
        params.addParameter(userId);
        params.addParameter(orderControlType);
        params.addParameter(orderId);
        params.addParameter(operKey);
        params.addParameter(stepKey);
        params.addParameter(refId);
        
        return jdbcDaoSupport.queryForList(select.toString(), params);
    }
    
    /* (non-Javadoc)
     * @see com.ibaset.solumina.sfpl.dao.IOperationDataCollectionDao#getOrderOperationDCAltLog(java.lang.String,
     * java.lang.Number, java.lang.Number, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public List getOrderOperationDCAltLog(String orderId,
                                          Number operKey, 
                                          Number stepKey,
                                          String refId, 
                                          String orderControlType,
                                          String userId,
                                          String altId)
    {
        
        StringBuffer select = new StringBuffer().append("SELECT A.ORDER_ID,")
                                                .append("       A.OPER_NO,")
                                                .append("       A.STEP_NO,")
                                                .append("       A.DAT_COL_ID,")
                                                .append("       A.OPER_KEY,")
                                                .append("       A.STEP_KEY,")
                                                .append("       C.DAT_COL_TYPE,")
                                                .append("       A.STD_DATCOL_ID,")
                                                .append("       A.DAT_COL_CERT,")
                                                .append("       A.DAT_COL_UOM,")
                                                .append("       A.DAT_COL_TITLE,")
                                                .append("       A.UPPER_LIMIT,")
                                                .append("       A.LOWER_LIMIT,")
                                                .append("       A.TARGET_VALUE,")
                                                .append("       A.ALT_ID,")
                                                .append("       A.ALT_COUNT,")
                                                .append("       A.REF_ID,")
                                                .append("       A.DISPLAY_LINE_NO,")
                                                .append("       CASE A.ORIENTATION_FLAG")
                                                .append("         WHEN 'D' THEN")
                                                .append("          'Data Collection Centric'")
                                                .append("         WHEN 'U' THEN")
                                                .append("          'Unit Centric'")
                                                .append("       END AS ORIENTATION_FLAG,")
                                                .append("       A.CROSS_ORDER_FLAG,")
                                                .append("       OPTIONAL_FLAG,")
                                                .append("       B.DCVALUE  AS VALUE, /*hidden based on order_control_type */")
                                                .append("       B.COMMENTS AS COMMENTS, /*hidden based on order_control_type */")
                                                .append("       NULL    AS   UDV_GRID_TERMINATOR,")
                                                .append("       B.UPDT_USERID,")
                                                .append("       B.TIME_STAMP,")
                                                .append("       VARIABLE_NAME,")
                                                .append("       VISIBILITY,")
                                                .append("       NUM_DECIMAL_DIGITS,")
                                                .append("       CASE CALC_DC_FLAG WHEN 'Y' THEN 'CALCULATED_DC' END AS CALC_DC_FLAG,")
                                                .append("       CALC_DC_FLAG AS CALC_FLAG,")
                                                .append("       A.DAT_COL_TITLE AS DAT_COL_TITLE_DISP,")
                                                .append("       CASE WHEN A.SLIDE_EMBEDDED_REF_ID IS NULL THEN NULL ")
                                                .append("	    WHEN SFMFG.SFFND_MM_SECURITY_GROUP_OK(a.SLIDE_ID,?) <> 'Y' then NULL")
                                                .append("	    ELSE 'Image' ")
                                                .append("       END AS IMAGE,")
                                                .append("       A.SLIDE_ID,")
                                                .append("       A.SLIDE_EMBEDDED_REF_ID,")
                                                .append("       A.AUDIT_FLAG, ")
                                                .append("       A.RESULT_ID, ")
                                                .append("       V.VALID_RESULT_TYPE, ")
                                                .append("       NULL AS FILE1, ")
                                                .append(" 		CASE WHEN O.OBJECT_REFERENCE IS NOT NULL THEN " +  jdbcDaoSupport.getSchemaPrefix() + "SFDB_SUBSTR_VARCHAR(O.OBJECT_REFERENCE,"
                                                        + jdbcDaoSupport.getSchemaPrefix() + "SFDB_INSTR_VARCHAR(O.OBJECT_REFERENCE, '\\', -1, 1)+1, " + jdbcDaoSupport.getSchemaPrefix() + "SFDB_LENGTH_VARCHAR(O.OBJECT_REFERENCE)) ELSE NULL END AS FILE_DISP, ")
                                                .append("       U.FORMAT, ")
                                                .append("       O.SECURITY_GROUP AS SECURITY_GROUP, ")
                                                .append("       A.UCF_OPER_DC_FLAG1, ")
                                                .append("       A.UCF_OPER_DC_FLAG2, ")
                                                .append("       A.UCF_OPER_DC_VCH255_1 ")
                                                .append("  FROM SFWID_OPER_DAT_COL_ALT A  LEFT OUTER JOIN SFSQA_VALID_RESULT_TYPE_DEF V ON (A.RESULT_ID = V.RESULT_ID) ")
                                                .append("                                   LEFT OUTER JOIN SFCORE_MM_OBJECT O ON (A.TEMPLATE_FILE_ID = O.OBJECT_ID) ")
                                                .append("       LEFT OUTER JOIN /* Outer Join needed for multi-serial orders */")
                                                .append("      (SELECT T.ORDER_ID,")
                                                .append("              T.OPER_KEY,")
                                                .append("              T.STEP_KEY,")
                                                .append("              T.DAT_COL_ID,")
                                                .append("              T.DCVALUE,")
                                                .append("              T.COMMENTS,")
                                                .append("              T.UPDT_USERID,")
                                                .append("              T.TIME_STAMP")
                                                .append("         FROM SFWID_SERIAL_OPER_DAT_COL T")
                                                .append("              /* Parameter, order_control_type, is in Scope query */")
                                                .append("        WHERE ? NOT IN ('SERIAL', 'SERIAL-LOT')) B ")
                                                .append("       ON A.ORDER_ID = B.ORDER_ID")
                                                .append("      AND A.OPER_KEY = B.OPER_KEY")
                                                .append("      AND A.STEP_KEY = B.STEP_KEY")
                                                .append("      AND A.DAT_COL_ID = B.DAT_COL_ID, ")
                                                .append("      SFFND_STD_DATCOL_TYPE_DEF C , SFFND_UOM_DEF U ")
                                                .append(" WHERE A.ORDER_ID = ?")
                                                .append("   AND A.OPER_KEY = ?")
                                                .append("   AND A.STEP_KEY = ?")
                                                .append("   AND A.REF_ID = ?")
                                                .append("   AND A.STD_DATCOL_ID = C.STD_DATCOL_ID ")
                                                .append("   AND A.COMPLETE_ALT_ID = ? ")
                                                .append(" AND A.DAT_COL_UOM = U.UOM ")
                                                .append(" ORDER BY A.DISPLAY_LINE_NO ");
        ParameterHolder params = new ParameterHolder();
        params.addParameter(userId);
        params.addParameter(orderControlType);
        params.addParameter(orderId);
        params.addParameter(operKey);
        params.addParameter(stepKey);
        params.addParameter(refId);
        params.addParameter(altId);
        
        return jdbcDaoSupport.queryForList(select.toString(), params);
    }
}
