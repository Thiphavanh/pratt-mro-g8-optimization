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
 *  File:    BardcodedTravelerDaoPW.java
 * 
 *  Created: September 2019
 * 
 *  Author:  Raeann Thorpe
 * 
 *  Revision History
 * 
 *  Date        Who          	Description
 *  ----------	--------------	-----------------------------------------------------------
 *  09-11-2019  R. Thorpe       Initial Release SMRO_WOE_310
*/
package com.pw.solumina.sfwid.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;

public class BarcodedTravelerDaoPW {

	@Reference
	private JdbcDaoSupport jdbcDaoSupport;

	public List getTravelerHeader(String orderNoList)
	{
		orderNoList = "'" + StringUtils.replace(orderNoList, ";", "','") + "'";
		
		StringBuffer selectSql = new StringBuffer().append("SELECT A.ORDER_ID as ORDER_ID,")
				                                   .append("       PW_ORDER_NO,")
				                                   .append("       SALES_ORDER,")
				                                   .append("       ORDER_QTY,")
				                                   .append("       A.PART_NO as PART_NO,")
				                                   .append("       PLAN_TITLE,")
				                                   .append("       ORDER_UOM,")
				                                   .append("       UNIT_TYPE,")
				                                   .append("       A.SECURITY_GROUP as SECURITY_GROUP,")
				                                   .append("       ORDER_TYPE")
				                                   .append("  FROM SFPL_ITEM_DESC_MASTER C,")
				                                   .append("       SFWID_ORDER_DESC A, ")
				                                   .append("       PWUST_SFWID_ORDER_DESC P")
				                                   .append(" WHERE PW_ORDER_NO in  ("+orderNoList+")")
				                                   .append("   AND A.ITEM_ID = C.ITEM_ID")
				                                   .append("   AND A.ORDER_ID = P.ORDER_ID")
				                                   .append(" ORDER BY PW_ORDER_NO ASC");
		
		List orderIdList;

		try
		{
			orderIdList = jdbcDaoSupport.queryForList(selectSql.toString());
		}
		catch(Exception e)
		{
			orderIdList = null;
		}

		return orderIdList ;

	}
}
