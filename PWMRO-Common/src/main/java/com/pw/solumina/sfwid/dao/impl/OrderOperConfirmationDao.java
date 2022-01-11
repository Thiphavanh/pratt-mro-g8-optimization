/*
* This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017, 2020.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    OrderOperConfirmationDao.java
* 
*  Created: 2018-09-24
* 
*  Author:  XCS4331
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2018-09-24 	Fred Ettefagh   Initial Release XXXXXXX
* 2018-10-12    Fred Ettefagh   Defect 996 added method selectOrderOperDescMap 
* 2018-10-26    Fred Ettefagh   Defect 969 when creating a Supplemental Order, update oper 0010 conf/sup/sub data from user selected oper no
* 2020-10-06    John DeNinno	Defect 1782 update oper buyoff fields to blank
* 2020-11-16    John DeNinno	Defect 1114 fix subnet value
*/

package com.pw.solumina.sfwid.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;

public class OrderOperConfirmationDao {
	
	
	@Reference 	private JdbcDaoSupport jdbcDaoSupport;
	

	// Defect 969
	public Map selectOrderOperDescMap(String orderId,String operNo){
		
		StringBuffer select = new StringBuffer().append(" SELECT T.* " +
										                " FROM SFWID_OPER_DESC T " +
										                " WHERE T.ORDER_ID = ? " +
										                " AND T.OPER_NO = ? " +
										                " AND T.STEP_KEY = ? " );

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(operNo);
		params.addParameter(-1);
		
		Map map = jdbcDaoSupport.queryForMap(select.toString(),params);
		return map;
		
	}
	
	public Map selectOrderOperDescMap(String orderId,Number operKey){
		
		StringBuffer select = new StringBuffer().append(" SELECT T.* " +
										                " FROM SFWID_OPER_DESC T " +
										                " WHERE T.ORDER_ID = ? " +
										                " AND T.OPER_KEY = ? " +
										                " AND T.STEP_KEY = ? " );

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(operKey);
		params.addParameter(-1);
		
		Map map = jdbcDaoSupport.queryForMap(select.toString(),params);
		return map;
		
	}

	
	public Map selectPwustSfwidOrderDesc(String orderId){
		
		Map map = null;
		StringBuffer select = new StringBuffer().append(" SELECT T1.PW_ORDER_NO, " +
                                                               " T1.PW_ORDER_TYPE, "+
                                                               " T1.SALES_ORDER, " +
														       " T1.ENGINE_MODEL, " +
														       " T1.WORK_SCOPE, " +
														       " T1.SUPERIORNET, " +
														       " T1.SUBNET, " +
														       " T1.CUSTOMER, " +
														       " (SELECT TT.WORK_LOC FROM SFFND_WORK_LOC_DEF TT WHERE TT.LOCATION_ID = T.ASGND_LOCATION_ID) AS WORK_LOC, "+
														       " T.* " +
														" FROM SFWID_ORDER_DESC T, PWUST_SFWID_ORDER_DESC T1  " +
														" WHERE T.ORDER_ID = T1.ORDER_ID " +
														" AND T.ORDER_ID = ? ");
				
		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		List list = jdbcDaoSupport.queryForList(select.toString(),params);
		if (list!=null && !list.isEmpty()){
			map =(Map)list.iterator().next();
		}
		return map;
	}
	public List selectOpersWithNoSupSubData(String orderId){
		
		StringBuffer select = new StringBuffer().append(" SELECT  T.* " +
		                                                " FROM SFWID_OPER_DESC T " +
		                                                " WHERE T.ORDER_ID = ? "+
		                                                " AND T.STEP_KEY = ? " +
		                                                " AND (T.UCF_ORDER_OPER_VCH1 IS NULL OR T.UCF_ORDER_OPER_VCH2 IS NULL OR T.UCF_ORDER_OPER_VCH3 IS NULL ) " +
		                                                " ORDER BY T.OPER_NO  ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(-1);
		List list = jdbcDaoSupport.queryForList(select.toString(),params);
		return list;
		
	}
	
	public int updateOperConfNumberByOperKey(String orderId,Number operKey,Number stepKey, String confirmNo, String superNet,String subNet ){

		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFWID_OPER_DESC  T " +
				                                           " SET T.UCF_ORDER_OPER_VCH1 = ?, " +
				                                           "     T.UCF_ORDER_OPER_VCH2 = ?, " +
				                                           "     T.UCF_ORDER_OPER_VCH3 = ? " +
														   " WHERE T.ORDER_ID = ?"+
														   " AND T.OPER_KEY = ? " +
														   " AND T.STEP_KEY = ? " );
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(confirmNo);
		parameters.addParameter(superNet);
		parameters.addParameter(subNet); //defect 1114
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		
		int numbOfRows = jdbcDaoSupport.update(updateSql.toString(), parameters);
		return numbOfRows;
	}
	
	//defect 1782
	public int updateOperBuyoffByOperKey(String orderId,Number operKey,Number stepKey, String UCF_OPER_BUYOFF_VCH1 ){

		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFWID_OPER_BUYOFF  T " +
				                                           " SET T.UCF_OPER_BUYOFF_VCH1 = ?, " +
				                                           " T.UCF_OPER_BUYOFF_DATE1 = ? " +
														   " WHERE T.ORDER_ID = ?"+
														   " AND T.OPER_KEY = ? " +
														   " AND T.STEP_KEY = ? " );
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(UCF_OPER_BUYOFF_VCH1);
		parameters.addParameter("");
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		
		int numbOfRows = jdbcDaoSupport.update(updateSql.toString(), parameters);
		return numbOfRows;
	}
	
	public Map getPlanOperNetworkActivityRow(String planId,
											Number planUpdtNo,
											Number operKey,
											String engineType){


		Map map = null;
		StringBuffer select = new StringBuffer().append("  SELECT T.OPER_NO, " +
												        "         T.OPER_KEY, " +
												        "         T2.SUPERIOR_NETWORK_ACT, " +
												        "         T3.SUB_NETWORK_ACT " +
								                        " FROM SFPL_OPERATION_REV T,    " +         
													    "       SFOR_SFPL_PLAN_SUBJECT_OPER T1, " +  
													    "       PWUST_PL_SUBJ_NETWORK_ACTIVITY T2, " +
													    "       PWUST_PL_SUBJ_SUBNET_ACTIVITY T3  " +
					    
														" WHERE T.PLAN_ID = T1.PLAN_ID " +
														" AND T.PLAN_UPDT_NO = T1.PLAN_UPDT_NO " +
														" AND T.OPER_KEY = T1.OPER_KEY " +
														" AND T.OPER_UPDT_NO = T1.OPER_UPDT_NO " +

														" AND T1.PLAN_ID = T2.PLAN_ID " +
														" AND T1.PLAN_UPDT_NO = T2.PLAN_UPDT_NO " +
														" AND T1.SUBJECT_NO = T2.SUBJECT_NO " +
														//" AND T2.ENGINE_TYPE = ? " +

														" AND T2.PLAN_ID = T3.PLAN_ID " +
														" AND T2.PLAN_UPDT_NO = T3.PLAN_UPDT_NO " +
														" AND T2.SUBJECT_NO = T3.SUBJECT_NO " +
														" AND T2.ENGINE_TYPE = T3.ENGINE_TYPE " +
														" AND T2.SUPERIOR_NETWORK_ACT = T3.SUPERIOR_NETWORK_ACT " +
					
														" AND T.PLAN_ID = ? " +
														" AND T.PLAN_UPDT_NO = ? " +
														" AND T.OPER_KEY = ? ");	

			if (StringUtils.isNotBlank(engineType)){
				select.append("AND T2.ENGINE_TYPE = ?");
			}
		
			ParameterHolder params = new ParameterHolder();

			params.addParameter(planId);
			params.addParameter(planUpdtNo);
			params.addParameter(operKey);

			if (StringUtils.isNotBlank(engineType)){
				params.addParameter(engineType);
			}
			
			List list = jdbcDaoSupport.queryForList(select.toString(),params);
			if (list!=null && !list.isEmpty()){
				map =(Map)list.iterator().next();
			}
			return map;
	}

	
	public List selectOrderOperWithNoConfNumber(String orderId){
		
		
		StringBuffer select = new StringBuffer().append(" SELECT T.* " +
		                                                " FROM SFWID_OPER_DESC T " +
		                                                " WHERE T.ORDER_ID = ? " +
		                                                " AND T.STEP_KEY = ? "+
		                                                " AND T.UCF_ORDER_OPER_VCH1 IS NULL" );
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(-1);
		List list = jdbcDaoSupport.queryForList(select.toString(),params);
		return list;
		
	}
	
	public Map selectPwustSalesOrder(String salesOrder, String superNet,String subNet){

		Map map = null;
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT T.* " +
		                                                   " FROM PWUST_SALES_ORDER  T " + // PRIMARY KEY (SALES_ORDER, ACT_NO, SUB_ACT_NO)
		                                                   " WHERE T.SALES_ORDER = ? " );
		
		if (StringUtils.isNotBlank(superNet)){
			selectSql.append(" AND T.ACT_NO = ? " ) ;
		}
		if (StringUtils.isNotBlank(subNet)){
			selectSql.append(" AND T.SUB_ACT_NO = ? " ) ;
		}
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(salesOrder);

		if (StringUtils.isNotBlank(superNet)){
			parameters.addParameter(superNet);
		}
		if (StringUtils.isNotBlank(subNet)){
			parameters.addParameter(subNet);
		}
		

		List list = jdbcDaoSupport.queryForList(selectSql.toString(),parameters);
		if (list!=null && !list.isEmpty()){
			map =(Map)list.iterator().next();
		}
		return map;
	
	}
	
	
	public int updateOperConfData(String orderId,String  operNumber,String confirmNo, String supNet, String SubNet){

		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFWID_OPER_DESC  T " +
				                                           " SET T.UCF_ORDER_OPER_FLAG1 = ?," +
				                                           "     T.UCF_ORDER_OPER_VCH1 = ?, " +
				                                           "     T.UCF_ORDER_OPER_VCH2 = ?, "+
				                                           "     T.UCF_ORDER_OPER_VCH3 = ? " +
														   " WHERE T.ORDER_ID = ? " +
														   " AND T.OPER_NO = ? " +
														   " AND T.STEP_KEY = ? " );
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter("A");
		parameters.addParameter(confirmNo);
		parameters.addParameter(supNet);
		parameters.addParameter(SubNet);
		parameters.addParameter(orderId);
		parameters.addParameter(operNumber);
		parameters.addParameter(-1);
		
		int numbOfRows = jdbcDaoSupport.update(updateSql.toString(), parameters);
		return numbOfRows;
	}
	
	public List selectOrderOperConfiramatinNo(String orderId){
		
		StringBuffer select = new StringBuffer().append(" SELECT T.* " +
										                " FROM SFWID_OPER_DESC T " +
										                " WHERE T.ORDER_ID = ? " +
										                " AND T.STEP_KEY = ? " +
										                " AND T.UCF_ORDER_OPER_VCH1 IS NULL ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(-1);
		
		List list = jdbcDaoSupport.queryForList(select.toString(),params);
		return list;
		
	}	
	
	public List selectPwustSalesOrder(String salesOrder, String engineType, String engineModel, String workLoc){
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT T.* " +
                                                           " FROM PWUST_SALES_ORDER  T " + // PRIMARY KEY (SALES_ORDER, ACT_NO, SUB_ACT_NO)
                                                           " WHERE T.SALES_ORDER = ? " +
                                                           " AND T.ENGINE_TYPE = ? " + 
														   " AND T.ENGINE_MODEL = ? " + 
														   " AND T.WORK_LOC = ?  ");
		 


		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(salesOrder);
		parameters.addParameter(engineType);
		parameters.addParameter(engineModel);
		parameters.addParameter(workLoc);
		
		List list = jdbcDaoSupport.queryForList(selectSql.toString(), parameters);
		
		return list;	
	}
	
	public Map selectPlanUpdtNo(String planId, Number planVer, Number planRev, Number planAlt){
		
		ParameterHolder params = new ParameterHolder();
		
		StringBuffer select = new StringBuffer().append(" SELECT T.PLAN_UPDT_NO AS  PLAN_UPDT_NO  " +
		                                                " FROM SFPL_PLAN_REV T " +
		                                                " WHERE T.PLAN_ID = ? "+
		                                                " AND T.PLAN_VERSION = ? " +
		                                                " AND T.PLAN_REVISION = ? " +
		                                                " AND T.PLAN_ALTERATIONS = ?");
		
		params.addParameter(planId);
		params.addParameter(planVer);
		params.addParameter(planRev);
		params.addParameter(planAlt);
		
		Map  Map = jdbcDaoSupport.queryForMap(select.toString(), params);
		return Map;		
		
	}
	
}

