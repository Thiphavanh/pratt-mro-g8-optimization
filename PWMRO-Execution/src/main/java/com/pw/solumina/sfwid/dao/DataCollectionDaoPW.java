/*
 *  This unpublished work, first created in 2019 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2019, 2020.  All rights reserved.  Information contained 
 *  herein is proprietary and confidential and improper use or disclosure 
 *  may result in civil and penal sanctions.
 *
 *  File:    DataCollectionDaoPW.java
 * 
 *  Created: 2019-03-01
 * 
 *  Author:  B. Corson
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2019-03-01		B. Corson		Initial Release SMRO_WOE_306 - Add UCF to Data Collections
 *  2020-03-03		B. Corson		SMRO_MD_311 Defect 1464 - Add logic to get Over Inspection Req'd Flag.
 *  2020-07-14      D. Miron        SMRO_MD_311 Defect 1729 - Changed delimiter from comma to semi-colon.  Resolves java error.
 */
package com.pw.solumina.sfwid.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.sql.ParameterHolder;

public class DataCollectionDaoPW {
    @Reference private ExtensionDaoSupport eds;
    
    public Map selectOperDC(String orderId, 
                            Number operKey, 
                            Number stepKey, 
                            String datColId)
    {
        Map returnMap = new HashMap();
        
        StringBuffer selectSql = new StringBuffer().append(" SELECT ")
                                                   .append("    ORDER_ID, ")
                                                   .append("    OPER_KEY, ")
                                                   .append("    STEP_KEY, ")
                                                   .append("    DAT_COL_ID, ")
                                                   .append("    OPER_NO, ")
                                                   .append("    STEP_NO, ")
                                                   .append("    UPDT_USERID, ")
                                                   .append("    TIME_STAMP, ")
                                                   .append("    LAST_ACTION, ")
                                                   .append("    UPPER_LIMIT, ")
                                                   .append("    LOWER_LIMIT, ")
                                                   .append("    TARGET_VALUE, ")
                                                   .append("    DAT_COL_CERT, ")
                                                   .append("    DAT_COL_TITLE, ")
                                                   .append("    DAT_COL_UOM, ")
                                                   .append("    ALT_ID, ")
                                                   .append("    ALT_COUNT, ")
                                                   .append("    BLOCK_ID, ")
                                                   .append("    REF_ID, ")
                                                   .append("    DISPLAY_LINE_NO, ")
                                                   .append("    ORIENTATION_FLAG, ")
                                                   .append("    CROSS_ORDER_FLAG, ")
                                                   .append("    OPTIONAL_FLAG, ")
                                                   .append("    VARIABLE_NAME, ")
                                                   .append("    VISIBILITY, ")
                                                   .append("    NUM_DECIMAL_DIGITS, ")
                                                   .append("    CALC_DC_FLAG, ")
                                                   .append("    SUSPECT_FLAG, ")
                                                   .append("    UCF_OPER_DC_VCH1, ")
                                                   .append("    UCF_OPER_DC_VCH2, ")
                                                   .append("    UCF_OPER_DC_VCH3, ")
                                                   .append("    UCF_OPER_DC_VCH4, ")
                                                   .append("    UCF_OPER_DC_VCH5, ")
                                                   .append("    UCF_OPER_DC_VCH6, ")
                                                   .append("    UCF_OPER_DC_VCH7, ")
                                                   .append("    UCF_OPER_DC_VCH8, ")
                                                   .append("    UCF_OPER_DC_VCH9, ")
                                                   .append("    UCF_OPER_DC_VCH10, ")
                                                   .append("    UCF_OPER_DC_VCH11, ")
                                                   .append("    UCF_OPER_DC_VCH12, ")
                                                   .append("    UCF_OPER_DC_VCH13, ")
                                                   .append("    UCF_OPER_DC_VCH14, ")
                                                   .append("    UCF_OPER_DC_VCH15, ")
                                                   .append("    UCF_OPER_DC_NUM1, ")
                                                   .append("    UCF_OPER_DC_NUM2, ")
                                                   .append("    UCF_OPER_DC_NUM3, ")
                                                   .append("    UCF_OPER_DC_NUM4, ")
                                                   .append("    UCF_OPER_DC_NUM5, ")
                                                   .append("    UCF_OPER_DC_DATE1, ")
                                                   .append("    UCF_OPER_DC_DATE2, ")
                                                   .append("    UCF_OPER_DC_DATE3, ")
                                                   .append("    UCF_OPER_DC_DATE4, ")
                                                   .append("    UCF_OPER_DC_DATE5, ")
                                                   .append("    NVL(UCF_OPER_DC_FLAG1,'N') AS UCF_OPER_DC_FLAG1, ")
                                                   .append("    NVL(UCF_OPER_DC_FLAG2,'N') AS UCF_OPER_DC_FLAG2, ")
                                                   .append("    UCF_OPER_DC_FLAG3, ")
                                                   .append("    UCF_OPER_DC_FLAG4, ")
                                                   .append("    UCF_OPER_DC_FLAG5, ")
                                                   .append("    UCF_OPER_DC_VCH255_1, ")
                                                   .append("    UCF_OPER_DC_VCH255_2, ")
                                                   .append("    UCF_OPER_DC_VCH255_3, ")
                                                   .append("    UCF_OPER_DC_VCH4000_1, ")
                                                   .append("    UCF_OPER_DC_VCH4000_2, ")
                                                   .append("    SLIDE_EMBEDDED_REF_ID, ")
                                                   .append("    SLIDE_ID, ")
                                                   .append("    AUDIT_FLAG, ")
                                                   .append("    STD_DATCOL_ID, ")
                                                   .append("    RESULT_ID, ")
                                                   .append("    TEMPLATE_FILE_ID, ")
                                                   .append("    TEMPLATE_UPDT_COUNT ")
                                                   .append("   FROM SFMFG.SFWID_OPER_DAT_COL ")
                                                   .append("  WHERE ORDER_ID = ? ")
                                                   .append("    AND OPER_KEY = ? ")
                                                   .append("    AND STEP_KEY = ? ")
                                                   .append("    AND DAT_COL_ID = ? ");
        
        ParameterHolder parameters = new ParameterHolder();
        parameters.addParameter(orderId);
        parameters.addParameter(operKey);
        parameters.addParameter(stepKey);
        parameters.addParameter(datColId);
        
        try
        {
            returnMap = eds.queryForMap(selectSql.toString(), parameters);
        } catch(Exception e) {
            returnMap.put("ORDER_ID",null);
            returnMap.put("OPER_KEY",null);
            returnMap.put("STEP_KEY",null);
            returnMap.put("DAT_COL_ID",null);
            returnMap.put("OPER_NO",null);
            returnMap.put("STEP_NO",null);
            returnMap.put("UPDT_USERID",null);
            returnMap.put("TIME_STAMP",null);
            returnMap.put("LAST_ACTION",null);
            returnMap.put("UPPER_LIMIT",null);
            returnMap.put("LOWER_LIMIT",null);
            returnMap.put("TARGET_VALUE",null);
            returnMap.put("DAT_COL_CERT",null);
            returnMap.put("DAT_COL_TITLE",null);
            returnMap.put("DAT_COL_UOM",null);
            returnMap.put("ALT_ID",null);
            returnMap.put("ALT_COUNT",null);
            returnMap.put("BLOCK_ID",null);
            returnMap.put("REF_ID",null);
            returnMap.put("DISPLAY_LINE_NO",null);
            returnMap.put("ORIENTATION_FLAG",null);
            returnMap.put("CROSS_ORDER_FLAG",null);
            returnMap.put("OPTIONAL_FLAG",null);
            returnMap.put("VARIABLE_NAME",null);
            returnMap.put("VISIBILITY",null);
            returnMap.put("NUM_DECIMAL_DIGITS",null);
            returnMap.put("CALC_DC_FLAG",null);
            returnMap.put("SUSPECT_FLAG",null);
            returnMap.put("UCF_OPER_DC_VCH1",null);
            returnMap.put("UCF_OPER_DC_VCH2",null);
            returnMap.put("UCF_OPER_DC_VCH3",null);
            returnMap.put("UCF_OPER_DC_VCH4",null);
            returnMap.put("UCF_OPER_DC_VCH5",null);
            returnMap.put("UCF_OPER_DC_VCH6",null);
            returnMap.put("UCF_OPER_DC_VCH7",null);
            returnMap.put("UCF_OPER_DC_VCH8",null);
            returnMap.put("UCF_OPER_DC_VCH9",null);
            returnMap.put("UCF_OPER_DC_VCH10",null);
            returnMap.put("UCF_OPER_DC_VCH11",null);
            returnMap.put("UCF_OPER_DC_VCH12",null);
            returnMap.put("UCF_OPER_DC_VCH13",null);
            returnMap.put("UCF_OPER_DC_VCH14",null);
            returnMap.put("UCF_OPER_DC_VCH15",null);
            returnMap.put("UCF_OPER_DC_NUM1",null);
            returnMap.put("UCF_OPER_DC_NUM2",null);
            returnMap.put("UCF_OPER_DC_NUM3",null);
            returnMap.put("UCF_OPER_DC_NUM4",null);
            returnMap.put("UCF_OPER_DC_NUM5",null);
            returnMap.put("UCF_OPER_DC_DATE1",null);
            returnMap.put("UCF_OPER_DC_DATE2",null);
            returnMap.put("UCF_OPER_DC_DATE3",null);
            returnMap.put("UCF_OPER_DC_DATE4",null);
            returnMap.put("UCF_OPER_DC_DATE5",null);
            returnMap.put("UCF_OPER_DC_FLAG1","N");
            returnMap.put("UCF_OPER_DC_FLAG2","N");
            returnMap.put("UCF_OPER_DC_FLAG3",null);
            returnMap.put("UCF_OPER_DC_FLAG4",null);
            returnMap.put("UCF_OPER_DC_FLAG5",null);
            returnMap.put("UCF_OPER_DC_VCH255_1",null);
            returnMap.put("UCF_OPER_DC_VCH255_2",null);
            returnMap.put("UCF_OPER_DC_VCH255_3",null);
            returnMap.put("UCF_OPER_DC_VCH4000_1",null);
            returnMap.put("UCF_OPER_DC_VCH4000_2",null);
            returnMap.put("SLIDE_EMBEDDED_REF_ID",null);
            returnMap.put("SLIDE_ID",null);
            returnMap.put("AUDIT_FLAG",null);
            returnMap.put("STD_DATCOL_ID",null);
            returnMap.put("RESULT_ID",null);
            returnMap.put("TEMPLATE_FILE_ID",null);
            returnMap.put("TEMPLATE_UPDT_COUNT",null);
        }
        
        return returnMap;
    }
    // Begin Defect 1464
    public String selectOperDCOverInspFlag(String orderId,
                                           Number operKey,
                                           Number stepKey,
                                           String datColList)
    {
        String overInsps = "N";
        
        // With Data Collections, Over Inspection Req'd Flag seems to be stored in AUDIT_FLAG.
        StringBuffer selectSql = new StringBuffer().append(" SELECT ")
                                                   .append("    count(*) ")
                                                   .append("   FROM SFMFG.SFWID_OPER_DAT_COL ")
                                                   .append("  WHERE ORDER_ID = ? ")
                                                   .append("    AND OPER_KEY = ? ")
                                                   .append("    AND STEP_KEY = ? ")
                                                   .append("    AND DAT_COL_ID IN ( ");
        
        String[] datColId = datColList.split(";");  // Defect 1729 
        for (int i = 0; i < datColId.length; i++) {
            if (i > 0) 
                selectSql.append(", ");
            
            selectSql.append("'" + datColId[i] + "'");
        }
        
        selectSql.append(")")
                 .append("   AND upper(AUDIT_FLAG) = 'Y'");
        
        ParameterHolder parameters = new ParameterHolder();
        parameters.addParameter(orderId);
        parameters.addParameter(operKey);
        parameters.addParameter(stepKey);
        
        int overInspFlagCnt = eds.queryForInt(selectSql.toString(), parameters);
        if (overInspFlagCnt > 0)
            overInsps = "Y";
        
        return overInsps;
    }
    // End Defect 1464
}
