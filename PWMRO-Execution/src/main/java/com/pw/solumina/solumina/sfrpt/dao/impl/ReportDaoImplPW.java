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
 *  File:    ReportDaoImplPW.java
 * 
 *  Created: 2018-07-26
 * 
 *  Author:  Fred Ettefagh
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2018-07-26		Fred Ettefagh   Initial Release SMRO_RPT_201 - Defect 907/911
 *  2018-07-27		Fred Ettefagh   back out code for defects 907/911
 *  2018-08-01		Fred Ettefagh   defect 907 added method "selectReportOrderBuyoffs" to use EXECQUERY to get list of buyoffs 
 *  2018-08-03      Fred Ettefagh   defect 908, changed method  "selectReportOrderBuyoffs" to add "pwOrderType"
 *  2018-08-05		R. Cannon		SMRO_RPT_201 Defect 908 - Revised exists statement in selectReportOrderBuyoffs()
 *  2021-03-15      B.Polak         SMRO_RPT_201 Defect 1848 - Added serial no to selectReportOrderBuyoffs
*/
package com.pw.solumina.solumina.sfrpt.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.pw.solumina.sfwid.dao.WorkOrderDaoPW;


public class ReportDaoImplPW{
	
	@Reference
	private JdbcDaoSupport jdbcDaoSupport;
	
	@Reference
	private WorkOrderDaoPW workOrderDaoPW;
	
	@Reference
	private IMessage message = null;
	
	
	public String getPWOrderType(String orderId){

		Map pwustSfwidOrderDescMap = workOrderDaoPW.selectPwustSfwidOrderDesc(orderId);

		if (null==pwustSfwidOrderDescMap){
    		message.raiseError("MFI_20", "Bad Order. Missing data in PWUST_SFWID_ORDER_DESC table");
    	}
    	
   		String pwOrderType = (String) pwustSfwidOrderDescMap.get("PW_ORDER_TYPE");	
   		return pwOrderType;
	}
	
	@SuppressWarnings("rawtypes")
	public List selectReportOrderBuyoffs (String orderId, Number operKey, Number StepKey, String RefId, String pwOrderType ){

		StringBuffer selectSql = new StringBuffer(" SELECT A.BUYOFF_ID, " +
													      " A.OPER_NO, " +
													      " A.STEP_NO, " +
													      " A.BUYOFF_TYPE, " +
													      " A.BUYOFF_CERT, " +
													      " A.BUYOFF_TITLE, " +
													      "  A.OPTIONAL_FLAG, " +
													      " CASE " +
													         " WHEN B.BUYOFF_STATUS = 'PENDING' AND B.UCF_SRLOPBUYOFF_FLAG1 IS NULL THEN NULL " +
													         " WHEN B.BUYOFF_STATUS = 'OPEN' AND B.UCF_SRLOPBUYOFF_FLAG1 IS NULL THEN NULL " +
													         " ELSE B.UPDT_USERID " +
													      " END AS UPDT_USERID, " +
													      " CASE  " +
													         " WHEN B.BUYOFF_STATUS = 'PENDING' AND B.UCF_SRLOPBUYOFF_FLAG1 IS NULL THEN NULL " +
													         " WHEN B.BUYOFF_STATUS = 'OPEN' AND B.UCF_SRLOPBUYOFF_FLAG1 IS NULL THEN NULL " +
													         " ELSE TO_CHAR(B.TIME_STAMP,'MM/DD/YYYY HH:MI:SS AM') " +
													       " END AS TIME_STAMP, " +
													       " CASE " +
													         " WHEN B.BUYOFF_STATUS = 'PENDING' AND B.UCF_SRLOPBUYOFF_FLAG1 IS NULL THEN NULL " + 
													         " WHEN B.BUYOFF_STATUS = 'OPEN' AND B.UCF_SRLOPBUYOFF_FLAG1 IS NULL THEN NULL " +
													         " ELSE SFFND_USER_NAME_GET(B.UPDT_USERID) " +
													       " END AS USER_NAME, " +
													       "CASE  " +
													         " WHEN B.UCF_SRLOPBUYOFF_FLAG1 = 'P' THEN 'PARTIAL' " +
													         " WHEN B.UCF_SRLOPBUYOFF_VCH1 IS NULL THEN B.BUYOFF_STATUS " +
													         " ELSE 'Closed-' || B.UCF_SRLOPBUYOFF_VCH1 " +
													       " END AS BUYOFF_STATUS, " +
													       " TO_CHAR(CAST(PERCENT_COMPLETE AS FLOAT) / 100) AS PERCENT_COMPLETE, " +
													       " B.COMMENTS, " +
													       " UCF_SRLOPBUYOFF_VCH4000_1, " +
													       " c.SERIAL_NO "+
													       " '' AS SECURITY_GROUP " +
												  " FROM SFWID_OPER_BUYOFF A  " +
												  " LEFT OUTER JOIN SFWID_SERIAL_OPER_BUYOFF B  " +
												  " ON A.ORDER_ID = B.ORDER_ID " +
												  " AND A.BUYOFF_ID = B.BUYOFF_ID " +
												  " AND A.OPER_KEY = B.OPER_KEY " +
												  " AND A.STEP_KEY = B.STEP_KEY " +
												  " left outer join sfmfg.sfwid_serial_desc c" +
												  " on b.serial_id = c.serial_id "+
												  " and b.order_id = c.order_id  "+
												  " and b.lot_id = c.lot_id " +
												  " WHERE A.ORDER_ID = ?  " +
												  " AND A.OPER_KEY = ? " +
												  " AND A.STEP_KEY = ?  " +
												  " AND A.REF_ID = ?  " +
												  " AND (A.LAST_ACTION <> 'SUSPECTEDI' AND A.LAST_ACTION <> 'SUSPECTEDU') " +
												  " AND ((A.OPTIONAL_FLAG = 'N') OR   (A.OPTIONAL_FLAG = 'Y' AND B.BUYOFF_STATUS NOT IN ('PENDING', 'OPEN')) OR  (NVL(B.UCF_SRLOPBUYOFF_FLAG1, 'x') = 'P'))" );
		
		
		if (StringUtils.equalsIgnoreCase(pwOrderType, "OVERHAUL") || StringUtils.equalsIgnoreCase(pwOrderType, "SUB ORDER") ){
			
			//Defect 908
			selectSql.append(" AND EXISTS (SELECT 1 FROM SFWID_OPER_DESC TT ")
					 .append(" 				WHERE TT.ORDER_ID = A.ORDER_ID ")
					 .append("				  AND TT.OPER_KEY = A.OPER_KEY ")
					 .append("				  AND TT.STEP_KEY = A.STEP_KEY ")
					 .append("				  AND TT.OPER_STATUS != 'EXCLUDE') ");
		}

		selectSql.append(" ORDER BY SERIAL_NO ASC, TIME_STAMP ASC " );

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(operKey);
		params.addParameter(StepKey);
		params.addParameter(RefId);
		
		List list = jdbcDaoSupport.queryForList(selectSql.toString(), params);

		return list;
	}
	
	
	@SuppressWarnings("rawtypes")
	public List selectReportOrderOperations(String orderId, Number StepKey) {

		String pwOrderType = getPWOrderType(orderId);
		
		StringBuffer selectSql = new StringBuffer("SELECT OPER_NO, " +
														  " OPER_KEY, " +
														  " STEP_KEY, " +
														  " NVL(OPER_UPDT_NO,0) AS OPER_UPDT_NO, " +
														  " OPER_TYPE, " +
														  " AUTO_START_FLAG, " +
														  " AUTO_COMPLETE_FLAG, " +
														  " OCCUR_RATE, " +
														  " PLND_MACHINE_NO, " +
														  " OPER_OPT_FLAG, " +
														  " CASE WHEN SUPPLIER_CODE IS NULL THEN NULL ELSE (SELECT WORK_LOC FROM SFFND_WORK_LOC_DEF LOC WHERE LOC.LOCATION_ID = SUPPLIER_CODE) END AS SUPPLIER_CODE, " +
														  " NVL(OSP_DAYS,0) AS OSP_DAYS, " +
														  " OSP_COST_PER_UNIT, " +
														  " TITLE, " +
														  " DEPT.WORK_DEPT AS ASGND_WORK_DEPT, " +
														  " CENTER.WORK_CENTER AS ASGND_WORK_CENTER, " +
														  " LOC.WORK_LOC AS ASGND_WORK_LOC, " +
														  " TEST_TYPE, " +
														  " CASE WHEN A.STDOPER_OBJECT_ID IS NOT NULL THEN 'Y' ELSE 'N' END AS STD_OPER_FLAG, " +
														  " '' AS SECURITY_GROUP " +
												" FROM SFWID_OPER_DESC A  LEFT OUTER JOIN SFFND_WORK_LOC_DEF LOC ON A.ASGND_LOCATION_ID = LOC.LOCATION_ID " +
												                        " LEFT OUTER JOIN SFFND_WORK_DEPT_DEF DEPT ON A.ASGND_DEPARTMENT_ID = DEPT.DEPARTMENT_ID AND A.ASGND_LOCATION_ID =  DEPT.LOCATION_ID " +
												                        " LEFT OUTER JOIN SFFND_WORK_CENTER_DEF CENTER ON A.ASGND_CENTER_ID= CENTER.CENTER_ID AND A.ASGND_LOCATION_ID = CENTER.LOCATION_ID AND A.ASGND_DEPARTMENT_ID = CENTER.DEPARTMENT_ID " +
												" WHERE ORDER_ID = ?  " +
												" AND STEP_KEY = ?  ");
		
		if (StringUtils.equalsIgnoreCase(pwOrderType, "OVERHAUL")){
			selectSql.append(" AND nvl(INCLUDED,'INCLUDED') = 'INCLUDED'");
		}
												
		selectSql.append(" ORDER BY LPAD(OPER_NO, 10, '0') ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(StepKey);
		
		List list = jdbcDaoSupport.queryForList(selectSql.toString(), params);
		
		return list;
	}
}
