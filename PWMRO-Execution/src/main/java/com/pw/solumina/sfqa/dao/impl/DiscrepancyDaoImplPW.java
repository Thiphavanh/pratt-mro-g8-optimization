/*
 *  This unpublished work, first created in 2018 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2018.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    DiscrepancyDaoImplPW.java
 * 
 *  Created: 2018-08-14
 * 
 *  Author:  Raeann Thorpe
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2018-01-09		R. Thorpe		Initial Release SMRO_QA_201 - Discrepancy
 *  2018-08-23      R. Thorpe       SMRO_QA_201 Defect 956 - modified selectOrderDescription to work with OOB order# or Custom Order#
 *  */
package com.pw.solumina.sfqa.dao.impl;

import java.util.Map;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;

import com.ibaset.solumina.sfqa.dao.IDiscrepancyDao;


public abstract class DiscrepancyDaoImplPW extends ImplementationOf<IDiscrepancyDao> implements IDiscrepancyDao
{
	@Reference
	private JdbcDaoSupport eds;
	
	@Reference
    private IDiscrepancyDao discrepancyDao = null;
	
	public Map selectOrderDescription(String orderNumber,
			                          String partNumber,
			                          String partChange)
	{
		
		StringBuffer selectSql = new StringBuffer().append("SELECT COUNT(*)")
                                                   .append("  FROM SFWID_ORDER_DESC O")
                                                   .append(" WHERE O.ORDER_NO = ?");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderNumber);

		int count = eds.queryForInt(selectSql.toString(), parameters);
		
		if (count > 0)
		{
			// Query with OOB order#   -- discrepancy Create uses this
			 selectSql = new StringBuffer().append("SELECT O.ORDER_ID,")
                                           .append("       O.ORDER_TYPE,")
                                           .append("       O.ORDER_UOM,")
                                           .append("       O.ORDER_CUST_ID,")
                                           .append("       O.ORDER_STATUS,")
                                           .append("       O.ORDER_QTY,")
                                           .append("       O.SERIAL_FLAG,")
                                           .append("       O.LOT_FLAG,")
                                           .append("       O.PROGRAM")
                                           .append(" FROM  SFWID_ORDER_DESC O ")
                                           .append("WHERE  O.ORDER_NO = ?")
                                           .append("  AND  O.PART_NO = ?")
                                           .append("  AND  O.PART_CHG = ?");

			parameters = new ParameterHolder();
			parameters.addParameter(orderNumber);
			parameters.addParameter(partNumber);
			parameters.addParameter(partChange);

			return eds.queryForMap(selectSql.toString(), parameters);
		}
		
		    // Query with Custom Order#  --   Discrepancy Complete uses this
			selectSql = new StringBuffer().append("SELECT O.ORDER_ID,")
                                          .append("       O.ORDER_TYPE,")
                                          .append("       O.ORDER_UOM,")
                                          .append("       O.ORDER_CUST_ID,")
                                          .append("       O.ORDER_STATUS,")
                                          .append("       O.ORDER_QTY,")
                                          .append("       O.SERIAL_FLAG,")
                                          .append("       O.LOT_FLAG,")
                                          .append("       O.PROGRAM")
                                          .append(" FROM  SFWID_ORDER_DESC O, PWUST_SFWID_ORDER_DESC C ")
                                          .append("WHERE  O.ORDER_ID = C.ORDER_ID")
                                          .append("  AND  C.PW_ORDER_NO = ?")
                                          .append("  AND  O.PART_NO = ? ")
                                          .append("  AND  O.PART_CHG = ?");

			parameters = new ParameterHolder();
			parameters.addParameter(orderNumber);
			parameters.addParameter(partNumber);
			parameters.addParameter(partChange);

			return eds.queryForMap(selectSql.toString(), parameters);
		
	}

}
