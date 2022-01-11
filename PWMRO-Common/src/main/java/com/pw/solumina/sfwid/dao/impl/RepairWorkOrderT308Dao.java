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
*  File:    RepairWorkOrderT308Dao.java
* 
*  Created: 2020-01-22
* 
*  Author:  XCS4331
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2020-01-22 	XCS4331		    Initial Release XXXXXXX
* 2020-02-13    Fred Ettefagh   SMRO_TR_308, Defect 1474, changed code to send t308 message after certain changes for WO Alt and task include exclude
* 2020-02-24    Fred Ettefagh   SMRO_TR_308, Defect 1474, added new method selectSfwidOrderDesc
* 2020-02-24    Fred Ettefagh   SMRO_TR_308, Defect 1474, fixed nvl bug in method selectSfwidOrderDesc
* 2020-03-03    Fred Ettefagh   SMRO_TR_308, Defect 1474, added method selectCountFromPwustRepairOrderT308Data
* 2020-03-04    Fred Ettefagh   SMRO_TR_308, Defect 1474, added step_key to where clause for oper and part check
* 2020-03-10    Fred Ettefagh   SMRO_TR_308, Defect 1474, changed != to =  for step_key in method opersDeleted
* 2020-03-11    Fred Ettefagh   SMRO_TR_308, Defect 1474, method opersDeleted  removed  " AND NVL(TT.ASGND_CENTER_ID, ? ) =  NVL(T.OPER_CENTER_ID, ? )) "
* 2020-03-11    Fred Ettefagh   SMRO_TR_308, Defect 1474, method opersDeleted  removed null, null from parameters list
* 2020-09-01	Brendan Polak	Defect 1971, insertPartData updated to handle multiple steps using same tool and only count tool USE
* */

package com.pw.solumina.sfwid.dao.impl;
import java.util.List;
import java.util.Map;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;

public class RepairWorkOrderT308Dao {
	
	@Reference private JdbcDaoSupport jdbcDaoSupport;
	
	
	public int selectCountFromPwustRepairOrderT308Data(String orderId ){
		
		
		ParameterHolder params = new ParameterHolder();
		StringBuffer select = new StringBuffer().append(" SELECT COUNT(*) FROM PWUST_REPAIR_ORDER_T308_DATA T WHERE T.ORDER_ID = ? ");
		params.addParameter(orderId);
		int count = jdbcDaoSupport.queryForInt(select.toString(), params);
		return count;
				
	}
	
	
	public Map selectSfwidOrderDesc(String orderId){
	
		ParameterHolder params = new ParameterHolder();
		StringBuffer select = new StringBuffer().append(" SELECT NVL(T.UCF_ORDER_FLAG5,'A') AS T308_FORCE_SEND, T.* FROM SFWID_ORDER_DESC T WHERE T.ORDER_ID = ? ");
		params.addParameter(orderId);
		Map map = jdbcDaoSupport.queryForMap(select.toString(), params);
		return map;
	}
	
	public void insertOperData(String orderId){
		
		StringBuffer insertSql = new StringBuffer().append(" INSERT INTO PWUST_REPAIR_ORDER_T308_DATA( ORDER_ID, " +
		                                                                                             " OPER_PART_FLAG, " + 
				                                                                                     " OPER_NO, " + 
		                                                                                             " UPDT_USERID, " +
		                                                                                             " OPER_CENTER_ID, " +
		                                                                                             " PART_NO) " +
                                                           " SELECT T.ORDER_ID, " +
                                                                    " ? , " +
                                                                    " T1.OPER_NO, " +
                                                                    " ?, " +
                                                                    " T1.ASGND_CENTER_ID, " +
                                                                    " ? " +
                                                           " FROM SFWID_ORDER_DESC T, SFWID_OPER_DESC T1 " +
                                                           " WHERE T.ORDER_ID = T1.ORDER_ID " +
                                                           " AND T.ORDER_ID = ? " +
                                                           " AND T1.STEP_KEY = ? " +
                                                           " AND T1.INCLUDED = ? "); //
		
	
		String user = ContextUtil.getUsername();
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter("OPER");
		parameters.addParameter(user);
		parameters.addParameter("NA");
		parameters.addParameter(orderId);
		parameters.addParameter(-1);
		parameters.addParameter("INCLUDED");
		
		System.out.println("RepairWorkOrderT308Dao.insertOperData insertSql = " + insertSql.toString());
		System.out.println("RepairWorkOrderT308Dao.insertOperData parameters = " + parameters);
		
		jdbcDaoSupport.insert(insertSql.toString(), parameters);
		
	}
	
	//1971 - 1) only include occurrences where part action = USE, not REFERENCE
	//2) group results and sum up QTY, in case same part is used in multiple steps
	public void insertPartData(String orderId){
		
		StringBuffer insertSql = new StringBuffer().append(" INSERT INTO PWUST_REPAIR_ORDER_T308_DATA( ORDER_ID," +
				                                                                                     " OPER_PART_FLAG, " +
				                                                                                     " OPER_NO, " + 
				                                                                                     " UPDT_USERID, " +
				                                                                                     " PART_NO, " + 
				                                                                                     " PLND_ITEM_QTY) " +
                                                           " SELECT T.ORDER_ID, " +
                                                                    " ? , " +
                                                                    " T1.OPER_NO, " +
                                                                    " ?, " +
                                                                    " T1.PART_NO, " +
                                                                    " sum(T1.PLND_ITEM_QTY) " +
                                                           " FROM SFWID_OPER_DESC T, SFWID_OPER_ITEMS t1 "+
                                                           " WHERE T.ORDER_ID = T1.ORDER_ID " +
                                                           " AND T.OPER_KEY = T1.OPER_KEY " +
                                                           " AND T.STEP_KEY = T1.STEP_KEY " +
                                                           " AND T.ORDER_ID = ? " +
                                                           " AND T1.PART_ACTION = ? " +
                                                           " AND T.OPER_NO IN (SELECT T2.OPER_NO " + 
                                                                             " FROM SFWID_OPER_DESC T2 " + 
                                                                             " WHERE T2.ORDER_ID = ? " + 
                                                                             " AND T2.INCLUDED = ? ) " +
                                                           " GROUP BY T.ORDER_ID, T1.OPER_NO, T1.PART_NO "); 
		
		String user = ContextUtil.getUsername();
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter("PART");
		parameters.addParameter(user);
		parameters.addParameter(orderId);
		parameters.addParameter("USE");
		parameters.addParameter(orderId);
		parameters.addParameter("INCLUDED");
		
		System.out.println("RepairWorkOrderT308Dao.insertPartData insertSql = " + insertSql.toString());
		System.out.println("RepairWorkOrderT308Dao.insertPartData parameters = " + parameters);
		
		jdbcDaoSupport.insert(insertSql.toString(), parameters);
		
	}

	
	public List opersChanged(String orderId){
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT T.* " + 
                                                           " FROM SFWID_OPER_DESC T " +
                                                           " WHERE T.ORDER_ID = ? " +
                                                           " AND T.STEP_KEY = ? " +
                                                           " AND T.INCLUDED = ? " + 
                                                           " AND NOT EXISTS (SELECT 1 " + 
                                                                           " FROM PWUST_REPAIR_ORDER_T308_DATA TT " +
                                                                           " WHERE TT.ORDER_ID = T.ORDER_ID  " + 
                                                                           " AND TT.OPER_PART_FLAG = ?  " +
                                                                           " AND TT.OPER_NO = T.OPER_NO  " +
                                                                           " AND NVL(TT.OPER_CENTER_ID, ? ) =  NVL(T.ASGND_CENTER_ID, ? )) ");
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter("-1");
		parameters.addParameter("INCLUDED");
		parameters.addParameter("OPER");
		parameters.addParameter("NULL");
		parameters.addParameter("NULL");
		List list = jdbcDaoSupport.queryForList(selectSql.toString(),parameters);
		return list;
		
	}
	
	
	public List partsChanged(String orderId){
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT T.* " +
                                                           " FROM SFWID_OPER_ITEMS T " +
                                                           " WHERE T.ORDER_ID = ? " +
                                                           " AND T.OPER_NO IN (SELECT T2.OPER_NO " + 
                                                                             " FROM SFWID_OPER_DESC T2 " + 
                                                                             " WHERE T2.ORDER_ID = T.ORDER_ID " +
                                                                             " AND T2.INCLUDED = ? ) " +
                                                           " AND NOT EXISTS (SELECT 1 " + 
                                                                           " FROM PWUST_REPAIR_ORDER_T308_DATA TT  " + 
                                                                           " WHERE TT.ORDER_ID = T.ORDER_ID  " +
                                                                           " AND TT.OPER_PART_FLAG = ? " +
                                                                           " AND TT.OPER_NO = T.OPER_NO " +
                                                                           " AND TT.PART_NO = T.PART_NO " +
                                                                           " AND TT.PLND_ITEM_QTY = T.PLND_ITEM_QTY)");
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter("INCLUDED");
		parameters.addParameter("PART");
		List list = jdbcDaoSupport.queryForList(selectSql.toString(),parameters);
		return list;
		
	}
	
	public List opersDeleted(String orderId){
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT T.* " +
		                                                   " FROM PWUST_REPAIR_ORDER_T308_DATA T " +
		                                                   " WHERE T.ORDER_ID = ? " +
		                                                   " AND T.OPER_PART_FLAG = ? " +
		                                                   " AND NOT EXISTS (SELECT 1 " + 
		                                                                   " FROM SFWID_OPER_DESC TT "+ 
		                                                                   " WHERE TT.ORDER_ID = T.ORDER_ID "+ 
		                                                                   " AND TT.OPER_NO = T.OPER_NO "+
		                                                                   " AND TT.STEP_KEY = ? " +
                                                                           " AND TT.OPER_STATUS != ? ) ") ;
		
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter("OPER");
		parameters.addParameter(-1);
		parameters.addParameter("EXCLUDE");
		List list = jdbcDaoSupport.queryForList(selectSql.toString(),parameters);
		return list;
	}
	
	
	public List partsDeleted(String orderId){
		
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT T.* "+
		                                                   " FROM PWUST_REPAIR_ORDER_T308_DATA  T " +
		                                                   " WHERE T.ORDER_ID = ? " +
		                                                   " AND T.OPER_PART_FLAG = ? "+
		                                                   " AND T.OPER_NO IN (SELECT T2.OPER_NO  " + 
		                                                                     " FROM SFWID_OPER_DESC T2 " + 
		                                                                     " WHERE T2.ORDER_ID = T.ORDER_ID " + 
		                                                                     " AND T2.STEP_KEY = ?  " + 
		                                                                     " AND T2.INCLUDED = ? )" +
		                                                   " AND NOT EXISTS (SELECT 1  " + 
		                                                                   " FROM SFWID_OPER_ITEMS TT " + 
		                                                                   " WHERE TT.ORDER_ID = T.ORDER_ID " + 
		                                                                   " AND TT.OPER_NO = T.OPER_NO " + 
		                                                                   " AND TT.PART_NO = T.PART_NO " + 
		                                                                   " AND TT.PLND_ITEM_QTY = T.PLND_ITEM_QTY)");
		
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter("PART");
		parameters.addParameter(-1);
		parameters.addParameter("INCLUDED");
		List list = jdbcDaoSupport.queryForList(selectSql.toString(),parameters);
		return list;
	}
	
	public int deleteRepairOrderAltT308Data(String orderId){
		
		StringBuffer deleteSql = new StringBuffer().append("DELETE FROM PWUST_REPAIR_ORDER_T308_DATA T WHERE T.ORDER_ID = ? ");
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		int i = jdbcDaoSupport.delete(deleteSql.toString(), parameters);
		return i;
		
	}

}
