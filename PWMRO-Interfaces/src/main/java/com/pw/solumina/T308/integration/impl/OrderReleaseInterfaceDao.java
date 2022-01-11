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
 *  File:    ReleaseAlterationOrderDao.java
 * 
 *  Created: 2019-08-01
 * 
 *  Author:  Raeann Thorpe
 * 
 *  Revision History
 * 
 *  Date      	Who                	Description
 *  ----------	---------------	---------------	---------------------------------------------------
 * 2019-08-01	Raeann Thorpe   Initial Release for PW_SMRO_TR_308
 * 2019-09-20   Bahram J.       SMRO_TR_308, changed xml to use synWorkOrder instead of syncRecord  
 * 2020-05-13   DeNinno J		SMRO_TR_308 Defect 1656 Wanted Plan Part Number
 * 2021-09-30   Polak B. 		SMRO_TR_308 Defect 1971 Only include parts with part action = USE
 */

package com.pw.solumina.T308.integration.impl;

import java.util.List;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;

public class OrderReleaseInterfaceDao {

	@Reference private JdbcDaoSupport jdbcDaoSupport;

	public List selectSfwidOrderDescList(String orderId)
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
				                                   .append(" A.ACTUAL_START_DATE, ")
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
				                                   .append(" A.INVENTORY_STOCK_LOC, ")
				                                   .append(" A.ACCOUNT_LABOR, ")
				                                   .append(" A.ACCOUNT_MATERIAL, ")
				                                   .append(" A.ALT_ID, ")
				                            /*       .append(" A.LTA_SEND_FLAG, ")*/
				                                   .append(" A.INSTRUCTIONS_TYPE AS INSTRUCTIONS_TYPE, ")
				                                   .append(" A.ALIAS_PART_NO,")  
				                                   .append(" A.ALIAS_PART_CHG,")
				                                   .append("(select b.part_no from sfpl_plan_desc b where b.plan_id = a.plan_id and plan_updt_no = a.plan_revision) as PLAN_PART_NO")  //DEFECT 1656                                  
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
	public List selectOrderSubjectsList(String orderId, String includedFlag){

		ParameterHolder params = new ParameterHolder();

		StringBuffer select = new StringBuffer().append(" SELECT T.* ")
				                                .append(" FROM SFOR_SFWID_ORDER_SUBJECT T ")
						                        .append(" WHERE T.ORDER_ID = ? ")  
								                .append(" AND T.INCLUDED_FLAG = ? ");

		params.addParameter(orderId);
		params.addParameter(includedFlag);
		
		List list = jdbcDaoSupport.queryForList(select.toString(), params);
		return list;

	}

	public List selectOrderTaskOperations (String orderID, Number taskNumber){
		
		StringBuffer select = new StringBuffer().append(" SELECT B.*, " +
		                                                     " C.WORK_CENTER AS ASGND_WORK_CENTER , " +
				                                             "  NVL(B.SCHED_DUR_HOURS_PER_UNIT,0) AS DURATION " +
                                                        " FROM SFOR_SFWID_OPER_SUBJECT A, SFWID_OPER_DESC B,  SFFND_WORK_CENTER_DEF C" +
                                                        " WHERE A.ORDER_ID = ? " +
                                                        " AND A.SUBJECT_NO = ? " +
                                                        " AND A.ORDER_ID = B.ORDER_ID " +
                                                        " AND A.OPER_KEY = B.OPER_KEY " +
                                                        " AND B.STEP_KEY = -1 " +
                                                        " AND C.LOCATION_ID = B.ASGND_LOCATION_ID " +
                                                        " AND C.DEPARTMENT_ID = B.ASGND_DEPARTMENT_ID " +
                                                        " AND C.CENTER_ID = B.ASGND_CENTER_ID " +
                                                        " ORDER BY B.OPER_NO ");
		
		ParameterHolder params = new ParameterHolder();	
		params.addParameter(orderID);
		params.addParameter(taskNumber);
		
		List list = jdbcDaoSupport.queryForList(select.toString(), params);
		return list;
	}
	
	
	public List selectOrderIncludedOperations(String orderId)
	{

		StringBuffer select = new StringBuffer().append("SELECT DISTINCT A.OPER_NO,")
				                                .append("       NVL(A.SCHED_DUR_HOURS_PER_UNIT,0) AS DURATION,")
				                                .append("       A.TITLE OPER_TITLE,")
				                                .append("       CENTER.WORK_CENTER AS ASGND_WORK_CENTER")
				                                .append("  FROM SFWID_OPER_DESC A")
				                                .append("  LEFT OUTER JOIN SFFND_WORK_LOC_DEF LOC ON A.ASGND_LOCATION_ID = LOC.LOCATION_ID")
				                                .append("  LEFT OUTER JOIN SFFND_WORK_DEPT_DEF DEPT ON A.ASGND_DEPARTMENT_ID = DEPT.DEPARTMENT_ID")
				                                .append("                                          AND A.ASGND_LOCATION_ID = DEPT.LOCATION_ID")
				                                .append("  LEFT OUTER JOIN SFFND_WORK_CENTER_DEF CENTER ON A.ASGND_CENTER_ID = CENTER.CENTER_ID")
				                                .append("                                              AND A.ASGND_LOCATION_ID = CENTER.LOCATION_ID")
				                                .append("                                              AND A.ASGND_DEPARTMENT_ID = CENTER.DEPARTMENT_ID")
				                          /*      .append(" SFOR_SFWID_OPER_SUBJECT C")*/
				                                .append(" WHERE A.ORDER_ID = ?")
				                                .append("   AND STEP_KEY = -1")
				                           /*     .append("   AND A.ORDER_ID = C.ORDER_ID")
				                                .append("   AND A.OPER_KEY = C.OPER_KEY")*/
				                             /*   .append("   AND C.INCLUDED_FLAG = 'INCLUDED'")*/
				                                .append("   AND A.INCLUDED = 'INCLUDED'")
				                                .append(" ORDER BY OPER_NO");


		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);

		return jdbcDaoSupport.queryForList(select.toString(), params);
	}

	//defect 1971 - only include parts where part action = USE
    public List selectOrderOperationParts(String orderId,
										  String operNo)
    {
			StringBuffer select = new StringBuffer().append("SELECT A.OPER_NO,")
													.append("       A.PART_NO,")
													.append("       A.PLND_ITEM_QTY")
													.append("  FROM SFWID_OPER_ITEMS A")
													.append(" WHERE A.ORDER_ID = ?")
													.append("   AND A.OPER_NO = ?")
													.append("   AND A.PART_ACTION = ?")
													.append(" ORDER BY A.OPER_NO, A.PART_LINE_NO");
					
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(operNo);
		params.addParameter("USE");

		return jdbcDaoSupport.queryForList(select.toString(), params);
    }
	
    public int getOrderOperationPartsCount(String orderId, String operNo)
    {

    	StringBuffer select = new StringBuffer().append("SELECT COUNT(A.OPER_NO)")
    			                                .append("  FROM SFWID_OPER_ITEMS A")
    			                                .append(" WHERE A.ORDER_ID = ?")
    			                                .append("   AND A.OPER_NO = ?");
    	
    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(orderId);
    	params.addParameter(operNo);

    	return jdbcDaoSupport.queryForInt(select.toString(), params);
    }

}
