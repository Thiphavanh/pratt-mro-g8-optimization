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
 *  File:    ROMHistoryReportDaoPW.java
 * 
 *  Created: 
 * 
 *  Author:  Raeann Thorpe
 * 
 *  Revision History
 * 
 *  Date        Who          	Description
 *  ----------	--------------	-----------------------------------------------------------
 *  08-08-2019  R. Thorpe       Initial Release SMRO_WOE_306
 *  03-23-2020  B.Polak         Defect 1487: Added Order No as a parameter to ROM report calls so that it can identify orders more accurately
 *  05-04-2020  Fred Ettefagh   SMRO_WOE_306,Defect 1487, Changed Dao Methods selectSNListForROMHistoryRpt, getROMReportHeader added new Dao method selectSalesOrderForROMHistoryRpt
 *  05-05-2020  Fred Ettefagh   SMRO_WOE_306,Defect 1487, changed Dao Method selectSalesOrderForROMHistoryRpt by adding order_type = 'REPAIR'
 *  06-01-2020  Fred Ettefagh   SMRO_TR_319,Defect 1611, added new method selectRomHistoryReportData
 */

package com.pw.solumina.sfwid.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.solumina.sfcore.application.IMessage;

public class ROMHistoryReportDaoPW {

	@Reference private JdbcDaoSupport jdbcDaoSupport;
	@Reference private IMessage message = null;
	
	public List selectRomHistoryReportData(String serialNo){

		ParameterHolder params = new ParameterHolder();

		StringBuffer select = new StringBuffer().append("  SELECT DISTINCT " +
															    "  T.PART_NO, " +
															    "  T1.DAT_COL_ID AS DC_ID, " +
															    "  T1.DAT_COL_TITLE AS DAT_COL_TITLE, " +
															    "  T2.TIME_STAMP AS DC_TIME, " +
															    "  T2.DCVALUE AS MEASUREMENT, " +
															    "  T2.COMMENTS AS MEASUREMENT_REMARK, " +
															    "  T1.UCF_OPER_DC_FLAG2  AS SERVICEABLE_LIMIT_USED, " +
															    "  T.ORDER_NO AS ORDER_NO, " +
															    "  T1.OPER_NO, " +
															    "  'ROM' AS DAT_COL_TYPE,  " +
															    "  T1.UCF_OPER_DC_VCH255_1 AS ENGINEERING_REMARKS, " +
															    "  T2.UPDT_USERID AS USERID, " +
															    "  T.SECURITY_GROUP " +
													    " FROM SFWID_ORDER_DESC T, SFWID_OPER_DAT_COL T1, SFWID_SERIAL_OPER_DAT_COL T2, SFWID_SERIAL_DESC T3  " +
														" WHERE T.ORDER_ID = T3.ORDER_ID " +
														" AND T.ORDER_ID = T1.ORDER_ID " +
														" AND T1.ORDER_ID = T2.ORDER_ID " +
														" AND T1.OPER_KEY = T2.OPER_KEY " +
														" AND T1.STEP_KEY = T2.STEP_KEY " +
														" AND T1.DAT_COL_ID = T2.DAT_COL_ID " +
														" AND T3.SERIAL_NO LIKE ?  " +
														" AND NVL(T1.UCF_OPER_DC_FLAG1, ?) = ?  " +
														" UNION " +
														" SELECT DISTINCT " +
														        " T.PART_NO, " +
														        " T1.DAT_COL_ID AS DC_ID, " +
														        " T1.DAT_COL_TITLE AS DAT_COL_TITLE, " +
														        " T2.TIME_STAMP AS DC_TIME, " +
														        " T2.DCVALUE AS MEASUREMENT, " +
														        " T2.COMMENTS AS MEASUREMENT_REMARK, " +
														        " T1.UCF_OPER_DC_FLAG2  AS SERVICEABLE_LIMIT_USED, " +
														        " T.ORDER_NO AS ORDER_NO, " +
														        " T1.OPER_NO, " +
														        " 'ROM' AS DAT_COL_TYPE, " +
														        " T1.UCF_OPER_DC_VCH255_1 AS ENGINEERING_REMARKS, " +
														        " T2.UPDT_USERID AS USERID, " +
														        " T.SECURITY_GROUP " +
													" FROM SFWID_ORDER_DESC T, SFWID_OPER_DAT_COL T1, SFWID_SERIAL_OPER_DAT_COL_HIST T2, SFWID_SERIAL_DESC T3 " +
													" WHERE T.ORDER_ID = T3.ORDER_ID " +
													" AND T.ORDER_ID = T1.ORDER_ID " +
													" AND T1.ORDER_ID = T2.ORDER_ID " +
													" AND T1.OPER_KEY = T2.OPER_KEY " +
													" AND T1.STEP_KEY = T2.STEP_KEY " +
													" AND T1.DAT_COL_ID = T2.DAT_COL_ID " +
													" AND T3.SERIAL_NO LIKE ? " +
													" AND NVL(T1.UCF_OPER_DC_FLAG1, ? ) = ? " +
													" AND T2.DCVALUE IS NOT NULL");
		
		params.addParameter(serialNo + "%");
		params.addParameter("N");
		params.addParameter("Y");
		params.addParameter(serialNo + "%");
		params.addParameter("N");
		params.addParameter("Y");
		List list = jdbcDaoSupport.queryForList(select.toString(), params);
		return list;

		
	}
	
	
	//1487 added orderNo as parameter
	public Map selectOrderInfo(String serialNo, String salesOrder, String orderNo){
	
		System.out.println("in ROMHistoryReportDaoPW.selectOrderInfo serialNo = " +serialNo);
		System.out.println("in ROMHistoryReportDaoPW,selectOrderInfo salesOrder = " +salesOrder);
		System.out.println("in ROMHistoryReportDaoPW,selectOrderInfo orderNo = " +orderNo);
		
		Map returnMap = new HashMap();

		StringBuffer selectSql = new StringBuffer().append("SELECT O.ORDER_ID        AS ORDER_ID,")
				                                   .append("       O.PLAN_ID         AS PLAN_ID,")
				                                   .append("       O.PLAN_REVISION   AS PLAN_REVISION,")
				                                   .append("       O.PART_NO         AS PART_NO,")
				                                   .append("       S.SERIAL_ID       AS SERIAL_ID, ")
				                                   .append("       O.ORDER_STATUS    AS ORDER_STATUS ")
				                                   .append("  FROM PWUST_SFWID_ORDER_DESC P,")
				                                   .append("       SFWID_ORDER_DESC       O,")
				                                   .append("       SFWID_SERIAL_DESC      S")
				                                   .append(" WHERE S.ORDER_ID = O.ORDER_ID")
				                                   .append("   AND O.ORDER_ID = P.ORDER_ID")
				                                   .append("   AND S.SERIAL_NO = ?")
				                                   .append("   AND P.SALES_ORDER = ?")
				                                   .append("   AND P.PW_ORDER_NO = ?");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(serialNo);
		parameters.addParameter(salesOrder);
		parameters.addParameter(orderNo);	

		returnMap = jdbcDaoSupport.queryForMap(selectSql.toString(), parameters);
		return returnMap;	
	}

	public List selectSalesOrderForROMHistoryRpt(String romType, String serialNo, String userid, String calledFrom){
		
		StringBuffer selectSql = new StringBuffer();
		ParameterHolder parameters = new ParameterHolder();
		
		
		if (StringUtils.equalsIgnoreCase(calledFrom, "INPUT_MATRIX")){

			selectSql = new StringBuffer().append(" SELECT DISTINCT   T2.SALES_ORDER || ',' ||T1.ORDER_NO as SALES_ORDER, " +
													               " T1.SECURITY_GROUP ");
			
		}else{
			
			selectSql = new StringBuffer().append(" SELECT DISTINCT  T2.SALES_ORDER , " +
 													               " T1.SECURITY_GROUP , " +
													               " T1.ORDER_NO as ORDER_NO ");
		}
		
		selectSql.append(					 " FROM SFWID_ORDER_DESC          T1, " + 
											 "      PWUST_SFWID_ORDER_DESC    T2, " +
											 "	    SFWID_SERIAL_DESC         T3, " +
											 "	    SFWID_OPER_DAT_COL        T4, " +
											 " 	    SFWID_SERIAL_OPER_DAT_COL T5 " +
											 " WHERE T1.ORDER_ID = T2.ORDER_ID " +
											 " AND   T1.ORDER_ID = T3.ORDER_ID " +
											 " AND   T1.ORDER_ID = T4.ORDER_ID  " +
											 " AND   T4.ORDER_ID = T5.ORDER_ID " +
											 " AND   T4.OPER_KEY = T5.OPER_KEY " +
											 " AND   T4.STEP_KEY = T5.STEP_KEY " +
											 " AND   T4.DAT_COL_ID = T5.DAT_COL_ID " +
											 " AND   T3.LOT_ID = T5.LOT_ID " +
											 " AND   T3.SERIAL_ID = T5.SERIAL_ID " +
											 " AND   T3.SERIAL_NO = ?  " +
											 " AND   T1.ORDER_TYPE = ?  " +
											 " AND   T1.ASGND_LOCATION_ID IN (SELECT ASGND_LOCATION_ID FROM SFMFG.SFFND_USER_WORK_CENTERS WHERE USERID = ? )");
		
	    if (StringUtils.equalsIgnoreCase("Report", romType)){
	    	selectSql.append(" AND T1.ORDER_STATUS = ? ");
	    }
	    selectSql.append(" ORDER BY 1 ");
											 
	    parameters.addParameter(serialNo);
	    parameters.addParameter("REPAIR");
	    parameters.addParameter(userid);
	    
	    if (StringUtils.equalsIgnoreCase("Report", romType)){
	    	parameters.addParameter("CLOSE");
	    }
	    
		List SalesOrderList = null;

		System.out.println("selectSql = " + selectSql);
		System.out.println("parameters = " + parameters);
		
		SalesOrderList = jdbcDaoSupport.queryForList(selectSql.toString(), parameters);
		
		if (SalesOrderList==null || SalesOrderList.isEmpty() || SalesOrderList.size() ==0){
			
			Map map = new HashMap();
			map.put("SALES_ORDER", null);
			map.put("SECURITY_GROUP", null);
			if (!StringUtils.equalsIgnoreCase(calledFrom, "INPUT_MATRIX")){
				map.put("ORDER_NO", null);
			}
			SalesOrderList.add(map);
		}

		return SalesOrderList;
	}
	
	
	public List selectSNListForROMHistoryRpt(String romType, String userid){
		
		System.out.println("in ROMHistoryReportDaoPW.selectSNListForROMHistoryRpt romType = " +romType);
		System.out.println("in ROMHistoryReportDaoPW.selectSNListForROMHistoryRpt userid = " +userid);
		
		
		if (StringUtils.isBlank(romType)){
			message.raiseError("MFI_20", "romType cannot be null");
		}

		if (StringUtils.isBlank(userid)){
			message.raiseError("MFI_20", "userid cannot be null");
		}

		
		StringBuffer selectSql = new StringBuffer();
		ParameterHolder parameters = new ParameterHolder();
		
		
		selectSql = new StringBuffer().append(" SELECT DISTINCT T1.SERIAL_NO ,T.SECURITY_GROUP " +
		                                      " FROM SFWID_ORDER_DESC T , " +
											  "      SFWID_SERIAL_DESC T1, " +
											  "      SFWID_OPER_DAT_COL T2, " +
											  "      SFWID_SERIAL_OPER_DAT_COL T3 " +
											  " WHERE T.ORDER_ID = T1.ORDER_ID  " +
											  " AND T1.ORDER_ID = T2.ORDER_ID " +
											  " AND T2.ORDER_ID = T3.ORDER_ID " +
											  " AND T2.OPER_KEY = T3.OPER_KEY " +
											  " AND T2.STEP_KEY = T3.STEP_KEY " +
											  " AND T2.DAT_COL_ID = T3.DAT_COL_ID " +
											  " AND T1.SERIAL_NO != ? " +
											  " AND T.ASGND_LOCATION_ID IN (SELECT ASGND_LOCATION_ID FROM SFFND_USER_WORK_CENTERS WHERE USERID =  ? )  " + 
											  " AND NVL(T2.UCF_OPER_DC_FLAG1, ?) = ? " );
		

	    if (StringUtils.equalsIgnoreCase("Report", romType)){
	    	
	    	selectSql.append(" AND T.ORDER_STATUS = ? ");
	    }
	    
	    selectSql.append(" ORDER BY T1.SERIAL_NO");

	    parameters.addParameter("N/A");
	    parameters.addParameter(userid);
	    parameters.addParameter("N");
	    parameters.addParameter("Y");
	    
	    if (StringUtils.equalsIgnoreCase("Report", romType)){
	    	
	    	parameters.addParameter("CLOSE");
	    }

		List serialNoList = null;

		System.out.println("selectSql = " + selectSql);
		System.out.println("parameters = " + parameters);
		
		serialNoList = jdbcDaoSupport.queryForList(selectSql.toString(), parameters);
		
		if (serialNoList==null || serialNoList.isEmpty() || serialNoList.size() ==0){
			Map map = new HashMap();
			map.put("SERIAL_NO", null);
			map.put("SECURITY_GROUP", null);
			serialNoList.add(map);
		}
		
		return serialNoList;
	}	
	
	public List getROMReportHeader(String serialNoList){
		
		System.out.println("in ROMHistoryReportDaoPW.getROMReportHeader serialNoList = " +serialNoList);
		
		serialNoList = "'" + StringUtils.replace(serialNoList, ";", "','") + "'";
		
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
				                                   .append(" WHERE PW_ORDER_NO in  ("+serialNoList+")")
				                                   .append("   AND A.ITEM_ID = C.ITEM_ID")
				                                   .append("   AND A.ORDER_ID = P.ORDER_ID")
				                                   .append(" ORDER BY PW_ORDER_NO ASC");
		
		List orderIdList;
		orderIdList = jdbcDaoSupport.queryForList(selectSql.toString());
		

		
		if (orderIdList==null || orderIdList.isEmpty() || orderIdList.size() ==0){
			Map map = new HashMap();
			map.put("ORDER_ID", null);
			map.put("PW_ORDER_NO", null);
			map.put("SALES_ORDER",null);
			map.put("ORDER_QTY",null);
			map.put("PART_NO",null);
			map.put("PLAN_TITLE",null);
			map.put("ORDER_UOM",null);
			map.put("UNIT_TYPE",null);
			map.put("SECURITY_GROUP",null);
			map.put("ORDER_TYPE",null);
			orderIdList.add(map);
		}

		
		return orderIdList ;

	}
	
	public String getOrderStatusFromSN(String serialNo, String orderNo){
		
		
		StringBuffer selectSql = new StringBuffer().append("SELECT O.ORDER_STATUS")
									               .append("  FROM SFWID_ORDER_DESC  O,")
									               .append("       SFWID_SERIAL_DESC S ")
									               .append(" WHERE S.ORDER_ID = O.ORDER_ID")
									               .append("   AND S.SERIAL_NO =? ")
									               .append("   AND O.ORDER_NO = ? ");
		
		System.out.println("in ROMHistoryReportDaoPW.getOrderStatusFromSN serialNo = " +serialNo);
		System.out.println("in ROMHistoryReportDaoPW.getOrderStatusFromSN orderNo = " +orderNo);
		
		ParameterHolder params = new ParameterHolder();
    	params.addParameter(serialNo);
    	params.addParameter(orderNo);
    	
		// System.out.println(selectSql.toString());
		// System.out.println(serialNo);
		// System.out.println(orderNo);
    	    	
    	String orderStatus = jdbcDaoSupport.queryForString(selectSql.toString(), params);
		return orderStatus;	
	}

}
