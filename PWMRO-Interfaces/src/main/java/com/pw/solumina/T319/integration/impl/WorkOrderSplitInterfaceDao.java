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
*  File:    WorkOrderSplitInterfaceDao.java
* 
*  Created: 2019-06-05
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2019-06-04 	Fred Ettefagh   Initial Release for PW_SMRO_TR_319 
*/

package com.pw.solumina.T319.integration.impl;

import java.util.List;
import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;


public class WorkOrderSplitInterfaceDao {

	@Reference private JdbcDaoSupport jdbcDaoSupport;

	
	public List selectsfwidOrderDescList(String orderId)
    {
        StringBuffer selectSql = new StringBuffer().append("SELECT  CASE WHEN A.ASGND_LOCATION_ID IS NULL THEN NULL ")
                                                   .append("        ELSE ( SELECT WORK_LOC FROM SFFND_WORK_LOC_DEF S WHERE S.LOCATION_ID = A.ASGND_LOCATION_ID ) END AS ASGND_WORK_LOC, ") // FND-22681
                                                   .append(" A.CONTRACT_NO, ")
                                                   .append(" A.CUSTOMER_ORDER_NO, ")
                                                   .append(" A.ENG_GROUP, ")
                                                   .append(" A.ENG_PART_CHG, ")
                                                   .append(" A.ENG_PART_NO, ")
                                                   .append(" A.FINAL_STORES, ")
                                                   .append(" A.INITIAL_STORES, ")
                                                   .append(" A.LOT_FLAG, ")
                                                   .append(" A.SERIAL_FLAG, ")
                                                   .append(" A.BOM_ID, ")
                                                   .append(" A.BOM_NO, ")
                                                   .append(" A.MFG_BOM_CHG, ")
                                                   .append(" A.MFG_INDEX_NO,  ")
                                                   .append(" A.MODEL, ")
                                                   .append(" A.ORDER_CUST_ID, ")
                                                   .append(" A.ORDER_NO, ")
                                                   .append(" A.ORDER_QTY, ")
                                                   .append(" A.DOC_TYPE, ")
                                                   .append(" A.ORDER_TYPE, ")
                                                   .append(" A.ORDER_STATUS, ")
                                                   .append(" A.ORDER_HOLD_STATUS, ")
                                                   .append(" A.ORDER_UOM, ")
                                                   .append(" A.ITEM_ID, ")
                                                   .append(" A.PART_CHG, ")
                                                   .append(" A.PART_NO, ")
                                                   .append(" A.ITEM_TYPE, ")
                                                   .append(" A.ITEM_SUBTYPE, ")
                                                   .append(" A.PLAN_ID, ")
                                                   .append(" A.PLAN_NO, ")
                                                   .append(" A.PLAN_VERSION, ")
                                                   .append(" A.PLAN_REVISION, ")
                                                   .append(" A.PLAN_ALTERATIONS, ")
                                                   .append(" A.PLAN_TITLE, ")
                                                   .append(" A.PLAN_TYPE, ")
                                                   .append(" A.PLG_GROUP, ")
                                                   .append(" A.PROGRAM, ")
                                                   .append(" A.PROJECT, ")
                                                   .append(" A.SCHED_END_DATE, ")
                                                   .append(" A.ACTUAL_START_DATE, ")//FND-17021
                                                   .append(" A.ACTUAL_END_DATE, ")
                                                   .append(" A.ORDER_SCRAP_QTY, ")
                                                   .append(" A.ORDER_COMPLETE_QTY, ")
                                                   .append(" A.SCHED_PRIORITY, ")
                                                   .append(" A.SCHED_START_DATE, ")
                                                   .append(" A.SERIAL_FLAG, ")

                                                   .append(" A.UCF_PLAN_VCH1, ")
                                                   .append(" A.UCF_PLAN_VCH2, ")
                                                   .append(" A.UCF_PLAN_VCH3, ")
                                                   .append(" A.UCF_PLAN_VCH4, ")
                                                   .append(" A.UCF_PLAN_VCH5, ")
                                                   .append(" A.UCF_PLAN_VCH6, ")
                                                   .append(" A.UCF_PLAN_VCH7, ")
                                                   .append(" A.UCF_PLAN_VCH8, ")
                                                   .append(" A.UCF_PLAN_VCH9, ")
                                                   .append(" A.UCF_PLAN_VCH10, ")
                                                   .append(" A.UCF_PLAN_VCH11, ")
                                                   .append(" A.UCF_PLAN_VCH12, ")
                                                   .append(" A.UCF_PLAN_VCH13, ")
                                                   .append(" A.UCF_PLAN_VCH14, ")
                                                   .append(" A.UCF_PLAN_VCH15, ")

                                                   .append(" A.UCF_PLAN_FLAG1, ")
                                                   .append(" A.UCF_PLAN_FLAG2, ")
                                                   .append(" A.UCF_PLAN_FLAG3, ")
                                                   .append(" A.UCF_PLAN_FLAG4, ")
                                                   .append(" A.UCF_PLAN_FLAG5, ")

                                                   .append(" A.UCF_ORDER_VCH1, ")
                                                   .append(" A.UCF_ORDER_VCH2, ")
                                                   .append(" A.UCF_ORDER_VCH3, ")
                                                   .append(" A.UCF_ORDER_VCH4, ")
                                                   .append(" A.UCF_ORDER_VCH5, ")
                                                   .append(" A.UCF_ORDER_VCH6, ")
                                                   .append(" A.UCF_ORDER_VCH7, ")
                                                   .append(" A.UCF_ORDER_VCH8, ")
                                                   .append(" A.UCF_ORDER_VCH9, ")
                                                   .append(" A.UCF_ORDER_VCH10, ")
                                                   .append(" A.UCF_ORDER_VCH11, ")
                                                   .append(" A.UCF_ORDER_VCH12, ")
                                                   .append(" A.UCF_ORDER_VCH13, ")
                                                   .append(" A.UCF_ORDER_VCH14, ")
                                                   .append(" A.UCF_ORDER_VCH15, ")

                                                   .append(" A.UCF_ORDER_FLAG1, ")
                                                   .append(" A.UCF_ORDER_FLAG2, ")
                                                   .append(" A.UCF_ORDER_FLAG3, ")
                                                   .append(" A.UCF_ORDER_FLAG4, ")
                                                   .append(" A.UCF_ORDER_FLAG5, ")

                                                   .append(" A.UCF_PLAN_VCH255_1, ")
                                                   .append(" A.UCF_PLAN_VCH255_2, ")
                                                   .append(" A.UCF_PLAN_VCH255_3, ")
                                                   .append(" A.UCF_PLAN_VCH4000_1, ")
                                                   .append(" A.UCF_PLAN_VCH4000_2, ")

                                                   .append(" A.UCF_ORDER_VCH255_1, ")
                                                   .append(" A.UCF_ORDER_VCH255_2, ")
                                                   .append(" A.UCF_ORDER_VCH255_3, ")
                                                   .append(" A.UCF_ORDER_VCH4000_1, ")
                                                   .append(" A.UCF_ORDER_VCH4000_2, ")

                                                   .append(" A.UCF_PLAN_NUM1, ")
                                                   .append(" A.UCF_PLAN_NUM2, ")
                                                   .append(" A.UCF_PLAN_NUM3, ")
                                                   .append(" A.UCF_PLAN_NUM4, ")
                                                   .append(" A.UCF_PLAN_NUM5, ")

                                                   .append(" A.UCF_ORDER_NUM1, ")
                                                   .append(" A.UCF_ORDER_NUM2, ")
                                                   .append(" A.UCF_ORDER_NUM3, ")
                                                   .append(" A.UCF_ORDER_NUM4, ")
                                                   .append(" A.UCF_ORDER_NUM5, ")

                                                   .append(" A.UCF_PLAN_DATE1, ")
                                                   .append(" A.UCF_PLAN_DATE2, ")
                                                   .append(" A.UCF_PLAN_DATE3, ")
                                                   .append(" A.UCF_PLAN_DATE4, ")
                                                   .append(" A.UCF_PLAN_DATE5, ")

                                                   .append(" A.UCF_ORDER_DATE1, ")
                                                   .append(" A.UCF_ORDER_DATE2, ")
                                                   .append(" A.UCF_ORDER_DATE3, ")
                                                   .append(" A.UCF_ORDER_DATE4, ")
                                                   .append(" A.UCF_ORDER_DATE5, ")

                                                   .append(" A.UNIT_NO, ")
                                                   .append(" A.UNIT_TYPE, ")
                                                   .append(" A.LTA_SEND_FLAG, ")
                                                   .append(" A.PARENT_ORDER_ID, ")
                                                   .append(" A.SPLIT_FLAG, ")
                                                   .append(" A.EXTERNAL_ERP_NO, ")
                                                   .append(" A.EXTERNAL_PLM_NO, ")
                                                   .append(" A.INVENTORY_STOCK_LOC, ")//FND-17297
                                                   .append(" A.ACCOUNT_LABOR, ")
                                                   .append(" A.ACCOUNT_MATERIAL, ")
                                                   .append(" A.ALT_ID, ")
                                                   .append(" A.LTA_SEND_FLAG, ")
                                                   .append(" A.INSTRUCTIONS_TYPE AS INSTRUCTIONS_TYPE, ")//FND-21200
                                                   .append(" A.ALIAS_PART_NO,")  //FND-21646
                                                   .append(" A.ALIAS_PART_CHG")
                                                   .append(" FROM ")
                                                   .append(" SFWID_ORDER_DESC A ")
                                                   .append(" WHERE ")
                                                   .append("   A.ORDER_ID = ? ");

        ParameterHolder parameters = new ParameterHolder();
        parameters.addParameter(orderId);

        List list = jdbcDaoSupport.queryForList(selectSql.toString(), parameters);

        return list;
    }

	
	
	@SuppressWarnings("rawtypes")
	public List selectSfwidSerialDescList(String orderId){
		
		ParameterHolder params = new ParameterHolder();
		
		StringBuffer select = new StringBuffer().append(" SELECT T.* " +
	                                                    " FROM SFWID_SERIAL_DESC T "+
	                                                    " WHERE T.ORDER_ID = ? " );
		
		params.addParameter(orderId);
		List list = jdbcDaoSupport.queryForList(select.toString(), params);
		return list;
		
	}
	
	@SuppressWarnings("rawtypes")
	public List selectSforSfwidOrderSubjectsList(String orderId, String includedFlag){
		
		ParameterHolder params = new ParameterHolder();
		
		StringBuffer select = new StringBuffer().append(" SELECT T.* " +
	                                                    " FROM SFOR_SFWID_ORDER_SUBJECT T "+
	                                                    " WHERE T.ORDER_ID = ? "  +
	                                                    " AND T.INCLUDED_FLAG = ? ");
		
		params.addParameter(orderId);
		params.addParameter(includedFlag);
		List list = jdbcDaoSupport.queryForList(select.toString(), params);
		return list;
		
	}
	
}
