/*
*  This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    RollUpDaoImplPW.java
* 
*  Created: 2020-06-01
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2020-06-05 	Fred Ettefagh   Initial Release SMRO_TR_319, Defect 1611
* 2020-06-05    Fred Ettefagh   Defect 1582, moved method repairWorkOrderSplitList to CommonDaoPW
* 
* */
package com.ibaset.solumina.sfwid.dao.impl;

import java.util.Map;
import com.ibaset.common.Reference;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.sql.ParameterHolder;

public class RollUpDaoImplPW {
	
	@Reference  private ExtensionDaoSupport eds;
	
    public Map selectOrderAndSerialDescription(String orderId) {
    	
        StringBuilder selectSql = new StringBuilder().append("SELECT ")
                                                   .append("    ( CASE WHEN ")
                                                   .append("        ( ORDER_QTY IS NULL ) THEN 0 ")
                                                   .append("        ELSE ORDER_QTY END ) AS ORDER_QTY, ")
                                                   .append("    COUNT( B.ORDER_ID ) AS SERIAL_CNT ")
                                                   .append("    FROM ")
                                                   .append("        SFWID_ORDER_DESC A, SFWID_SERIAL_DESC B ")
                                                   .append("    WHERE ")
                                                   .append("        A.ORDER_ID = ? AND ")
                                                   .append("        A.ORDER_ID = B.ORDER_ID ")
                                                   .append("        AND B.SERIAL_STATUS != ? ")
                                                   .append("    GROUP BY ( CASE WHEN ")
                                                   .append("    ( ORDER_QTY IS NULL ) THEN 0 ")
                                                   .append("     ELSE ORDER_QTY END ) ");

        ParameterHolder parameters = new ParameterHolder();
        parameters.addParameter(orderId);
        parameters.addParameter("STOP");
        return eds.queryForMap(selectSql.toString(), parameters);
    }
	
}
