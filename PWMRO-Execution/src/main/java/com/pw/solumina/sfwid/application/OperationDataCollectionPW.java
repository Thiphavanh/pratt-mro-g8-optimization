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
 *  File:    OperationDataCollectionPW.java
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
package com.pw.solumina.sfwid.application;

import static org.apache.commons.lang.StringUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.util.SoluminaUtils;
import com.ibaset.solumina.sfpl.application.IStep;

import com.pw.solumina.sfwid.dao.OperationDataCollectionDaoPW;

public class OperationDataCollectionPW {
	@Reference                               
	private IStep sfplStep = null;

	@Reference                               
	private OperationDataCollectionDaoPW operationDatColDaoPW = null;

	/*
     * (non-Javadoc)
     * @see com.ibaset.solumina.sfpl.application.IOperationDataCollection#getOrderOperationDC(java.lang.String, java.lang.Number, java.lang.Number, java.lang.String, java.lang.String)
     */
    public List getOrderOperationDC(String orderId,
                                    Number operKey, 
                                    Number stepKey,
                                    String refId, 
                                    String orderControlType )
    {
        return getOrderOperationDCAltLog(orderId,
                                         operKey,
                                         stepKey,
                                         refId,
                                         orderControlType,
                                         null);
    }
    /* (non-Javadoc)
     * @see com.ibaset.solumina.sfpl.application.IOperationDataCollection#getOrderOperationDCAltLog(java.lang.String,
     * java.lang.Number, java.lang.Number, java.lang.String, java.lang.String, java.lang.String)
     */
    public List getOrderOperationDCAltLog(String orderId,
                                          Number operKey, 
                                          Number stepKey,
                                          String refId, 
                                          String orderControlType,
                                          String completeAltId)
    {
        List resultList = new ArrayList();
        String userId = ContextUtil.getUsername();
        if(StringUtils.isEmpty(completeAltId))
        {
//            resultList = operationDatColDao.getOrderOperationDC(orderId,operKey,stepKey,refId,orderControlType,userId);
            resultList = operationDatColDaoPW.getOrderOperationDC(orderId,operKey,stepKey,refId,orderControlType,userId);
        }
        else
        {
//            resultList = operationDatColDao.getOrderOperationDCAltLog(orderId,
            resultList = operationDatColDaoPW.getOrderOperationDCAltLog(orderId,
                                                                        operKey,
                                                                        stepKey,
                                                                        refId,
                                                                        orderControlType,
                                                                        userId,
                                                                        completeAltId); 
        }
        
        ListIterator i = resultList.listIterator();
        while(i.hasNext())
        {
            Map mapList = (Map)i.next();
            
            String datColUom = (String) mapList.get("DAT_COL_UOM");
            String targetValue = (String) mapList.get("TARGET_VALUE");
            String lowerLimit = (String) mapList.get("LOWER_LIMIT");
            String upperLimit = (String) mapList.get("UPPER_LIMIT");
            
            String format = (String)mapList.get("FORMAT");
            
            if(StringUtils.isNotEmpty(format))
            {
                if(isNotEmpty(targetValue))
                {
                    targetValue = SoluminaUtils.parseDateGeneralToLocaleSpecific(format, targetValue);
                    mapList.put("TARGET_VALUE", targetValue);
                }
                if(isNotEmpty(lowerLimit))
                {
                    lowerLimit = SoluminaUtils.parseDateGeneralToLocaleSpecific(format, lowerLimit);
                    mapList.put("LOWER_LIMIT", lowerLimit);
                }
                if(isNotEmpty(upperLimit))
                {
                    upperLimit = SoluminaUtils.parseDateGeneralToLocaleSpecific(format, upperLimit);
                    mapList.put("UPPER_LIMIT", upperLimit);
                }
            }
             
            sfplStep.parseDataCollectionValue(mapList, upperLimit, lowerLimit, targetValue, null, null, format);
             
            mapList.put("CALLED_FROM", SoluminaConstants.EDIT_BLOCK);
        }
        if(resultList.size()==0 || resultList == null)
        {
            
            Map emptyMap = new ListOrderedMap();
            //emptyMap.put("ORDER_ID", null);
            emptyMap.put("OPER_NO", null);
            emptyMap.put("STEP_NO", null);
            //emptyMap.put("DAT_COL_ID", null);
            //emptyMap.put("OPER_KEY", null);
            //emptyMap.put("STEP_KEY", null);
            emptyMap.put("UCF_OPER_DC_FLAG1", null);     // SMRO_WOE_306
            emptyMap.put("DAT_COL_TYPE", null);
            emptyMap.put("STD_DATCOL_ID", null);
            emptyMap.put("DAT_COL_CERT", null);
            emptyMap.put("DAT_COL_UOM", null);
            emptyMap.put("UCF_OPER_DC_VCH255_1", null);  // SMRO_WOE_306
            emptyMap.put("DAT_COL_TITLE", null);
            emptyMap.put("UPPER_LIMIT", null);
            emptyMap.put("LOWER_LIMIT", null);
            emptyMap.put("TARGET_VALUE", null);
            emptyMap.put("ALT_ID", null);
            emptyMap.put("ALT_COUNT", null);
            //emptyMap.put("REF_ID", null);
            emptyMap.put("DISPLAY_LINE_NO", null);
            emptyMap.put("ORIENTATION_FLAG", null);
            emptyMap.put("CROSS_ORDER_FLAG", null);
            emptyMap.put("OPTIONAL_FLAG", null);
            emptyMap.put("VALUE", null);
            emptyMap.put("COMMENTS", null);
            emptyMap.put("UCF_OPER_DC_FLAG2", null);   // SMRO_WOE_306
            emptyMap.put("UDV_GRID_TERMINATOR", null);
            emptyMap.put("UPDT_USERID", null);
            emptyMap.put("TIME_STAMP", null);
            emptyMap.put("VARIABLE_NAME", null);
            emptyMap.put("VISIBILITY", null);
            emptyMap.put("NUM_DECIMAL_DIGITS", null);
            emptyMap.put("CALC_DC_FLAG", null);
            
            emptyMap.put("CALC_FLAG", null);
            emptyMap.put("DAT_COL_TITLE_DISP", null);
            emptyMap.put("IMAGE", null);
            emptyMap.put("SLIDE_ID", null);
            emptyMap.put("SLIDE_EMBEDDED_REF_ID", null);
            emptyMap.put("RESULT_ID", null);
            emptyMap.put("VALID_RESULT_TYPE", null);
            emptyMap.put("FILE1", null);
            emptyMap.put("FILE_DISP", null);
            emptyMap.put("SECURITY_GROUP", null);
            emptyMap.put("FORMAT", null);
            emptyMap.put("CALLED_FROM", null);
            
            resultList.add(emptyMap);
        }
        
        /** Start OPCERT Migration **/
        
        String datColType = (String)((Map)resultList.get(0)).get("DAT_COL_TYPE");
        
        if(StringUtils.isEmpty(datColType))
        {
            List returnList1 =new ArrayList(); 
            Map emptyMap = new ListOrderedMap();
            
            emptyMap.put("OPER_NO", null);
            emptyMap.put("STEP_NO", null);
            emptyMap.put("UCF_OPER_DC_FLAG1", null);     // SMRO_WOE_306
            emptyMap.put("DAT_COL_TYPE", null);
            emptyMap.put("STD_DATCOL_ID", null);
            emptyMap.put("DAT_COL_CERT", null);
            emptyMap.put("DAT_COL_UOM", null);
            emptyMap.put("UCF_OPER_DC_VCH255_1", null);  // SMRO_WOE_306
            emptyMap.put("DAT_COL_TITLE", null);
            emptyMap.put("UPPER_LIMIT", null);
            emptyMap.put("LOWER_LIMIT", null);
            emptyMap.put("TARGET_VALUE", null);
            emptyMap.put("ALT_ID", null);
            emptyMap.put("ALT_COUNT", null);
            //emptyMap.put("REF_ID", null);
            emptyMap.put("DISPLAY_LINE_NO", null);
            emptyMap.put("ORIENTATION_FLAG", null);
            emptyMap.put("CROSS_ORDER_FLAG", null);
            emptyMap.put("OPTIONAL_FLAG", null);
            emptyMap.put("VALUE", null);
            emptyMap.put("COMMENTS", null);
            emptyMap.put("UCF_OPER_DC_FLAG2", null);   // SMRO_WOE_306
            emptyMap.put("UDV_GRID_TERMINATOR", null);
            emptyMap.put("UPDT_USERID", null);
            emptyMap.put("TIME_STAMP", null);
            emptyMap.put("VARIABLE_NAME", null);
            emptyMap.put("VISIBILITY", null);
            emptyMap.put("NUM_DECIMAL_DIGITS", null);
            emptyMap.put("CALC_DC_FLAG", null);
            emptyMap.put("CALC_FLAG", null);
            emptyMap.put("DAT_COL_TITLE_DISP", null);
            emptyMap.put("IMAGE", null);
            emptyMap.put("SLIDE_ID", null);
            emptyMap.put("SLIDE_EMBEDDED_REF_ID", null);
            emptyMap.put("AUDIT_FLAG", null);
            emptyMap.put("RESULT_ID", null);
            emptyMap.put("VALID_RESULT_TYPE", null);
            emptyMap.put("FILE1", null);
            emptyMap.put("FILE_DISP", null);
            emptyMap.put("SECURITY_GROUP", null);
            emptyMap.put("FORMAT", null);
            emptyMap.put("CALLED_FROM", null);
            
            returnList1.add(emptyMap);
            
            resultList= returnList1;
        }
        /** End OPCERT Migration **/
        
        return resultList;
    }

}
