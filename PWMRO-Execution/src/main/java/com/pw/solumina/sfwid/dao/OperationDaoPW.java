/*
 *  This unpublished work, first created in 2018 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2018, 2020.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    OperationDaoPW.java
 * 
 *  Created: 2018-03-19
 * 
 *  Author:  Thien-Long Phan
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2018-03-19		T. Phan		    Initial Release SMRO_TR_T303 - Labor Interface
 *  2019-04-26      D. Miron        SMRO_TR_T203 Defect 733 - Correct select of confirmation number
*   2019-09-20		Bahram J.       Defect 1366, Fixed code for Labor Rings interface *
*   2019-09-24		Bahram J.       SMRO_TR_T303, added new Dao Method getSfwidOperDescMap(String orderId, Number operKey, Number stepKey) used for labor rings
*   2019-09-24      Fred Ettefagh   SMRO_TR_T303, defect 1382, labor rings method, changed method insertT203Labor to pass in oper_key instead of oper_no*   
*	2020-11-13		S. Edgerly		SMRO_WOE_308 - defect 1811 - Supplemental Orders
*	2020-12-04		S. Edgerly		SMRO_WOE_308 - Defect 1819 - Supplemental Orders
 */
package com.pw.solumina.sfwid.dao;

import java.util.List;
import java.util.Map;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.sql.ParameterHolder;

public class OperationDaoPW 
{
	//Begin SMRO_TR_T303 - OH Labor Interface
	@Reference private static ExtensionDaoSupport eds;
	
	public boolean isExemptLabor(String orderId) //(Not needed)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT COUNT(*) ")
				.append("  FROM SFWID_ORDER_DESC A ")
				.append(" WHERE A.ORDER_ID       = ? ")
				.append("   AND (A.ORDER_TYPE = 'TD' ")
				.append(" OR (A.ORDER_TYPE = 'SUPPLEMENTAL' ")
				.append(" AND (A.ORDER_NO LIKE '%-A%' OR A.ORDER_NO LIKE '%-S%'))) ");


		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);

		int count = eds.queryForInt(selectSql.toString(), params);

		boolean isExempt = false;

		if (count > 0)
		{
			isExempt = true;
		}

		return isExempt;		
	}

	public  Map getSfwidOperDescMap(String orderId, Number operKey, Number stepKey)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT Y.* ")
												   .append(" FROM SFWID_OPER_DESC Y ")
												   .append(" WHERE Y.ORDER_ID = ? ")
												   .append(" AND Y.OPER_KEY = ? ")
												   .append(" AND Y.STEP_KEY = ? ");

		ParameterHolder param = new ParameterHolder();
		param.addParameter(orderId);
		param.addParameter(operKey);
		param.addParameter(stepKey);
		
        Map  map = eds.queryForMap(selectSql.toString(), param);
        return map;
	}
	
	public  Map getSfwidOperDescMap(String orderId, String operNo)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT Y.* ")   // Defect 733
												   .append(" FROM SFWID_OPER_DESC Y ")
												   .append(" WHERE Y.ORDER_ID = ? ")
												   .append(" AND Y.OPER_NO = ? ")
												   .append(" AND Y.STEP_KEY = ? ");

		ParameterHolder param = new ParameterHolder();
		param.addParameter(orderId);
		param.addParameter(operNo);
		param.addParameter(-1);
		
        Map  map = eds.queryForMap(selectSql.toString(), param);
        return map;
	}

	
	public Map selectOrderInfo(String orderId)
	{
		StringBuilder selectSql = new StringBuilder().append("SELECT ")
				.append("  A.ORDER_NO, ")
				.append("  A.UCF_ORDER_VCH2 as SALES_ORDER_NO, ")
				.append("  A.PART_NO, ")
				.append("  A.SECURITY_GROUP, ")
				.append("  C.SERIAL_NO, ")                                          
				.append("  C.SERIAL_ID, ")
				.append("  A.asgnd_work_loc ")
				.append("FROM ")
				.append("   SFWID_ORDER_DESC A, ")
				.append("   SFWID_SERIAL_DESC C ")
				.append("WHERE ")
				.append("   A.ORDER_ID = C.ORDER_ID")
				.append("   AND A.ORDER_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);

		Map orderMap = eds.queryForMap(selectSql.toString(), parameters);

		return orderMap;
	}
	
	public String CheckPriv(String userid, String priv)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT SFMFG.SFCORE_HAS_PRIV( ?,?) as priv ")
                .append(eds.getDualTable());
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(userid);
		params.addParameter(priv);
		
		String returnValue = eds.queryForString(selectSql.toString(),params);

		return returnValue;

	}
	public String getOrderNo(String orderId)
	 {
		 StringBuffer selectSql = new StringBuffer().append("SELECT DISTINCT ORDER_NO ")
				 .append("  FROM SFWID_ORDER_DESC ")
				 .append(" WHERE ORDER_ID = ? ");

				 ParameterHolder params = new ParameterHolder();
				 params.addParameter(orderId);

				 String orderNo = "";

				 try
				 {
					 orderNo = eds.queryForString(selectSql.toString(), params);
				 }
				 catch(Exception e)
				 {
					 // Do nothing
				 }

				 return orderNo;
	 }
			
	//End SMRO_TR_T203 - OH Labor Interface
	
	//defect 1811
	public void clearBuyoffUserAndTimestampFromPlan(String planID, String operNo){
		
		StringBuffer updateSql = new StringBuffer().append("UPDATE SFPL_STEP_BUYOFF ")
				   .append(" SET UCF_STEP_BUYOFF_VCH1 = NULL, ")
				   .append(" UCF_STEP_BUYOFF_VCH2 = NULL, ")
				   .append(" UCF_STEP_BUYOFF_VCH3 = NULL, ")
				   .append(" UCF_STEP_BUYOFF_DATE1 = NULL, ")
				   .append(" UCF_STEP_BUYOFF_NUM1 = NULL ")
				   .append(" WHERE PLAN_ID = ? ")
				   .append(" AND OPER_KEY = (SELECT OPER_KEY ")
				   .append(" FROM SFPL_OPERATION_DESC ")
				   .append(" WHERE PLAN_ID = ? ")
				   .append(" AND OPER_NO = ?) ");
	
		 ParameterHolder params = new ParameterHolder();
		 params.addParameter(planID);
		 params.addParameter(planID);
		 params.addParameter(operNo);
		 
		 eds.update(updateSql.toString(), params);
	}
	//defect 1819
	public void clearBuyoffUserAndTimestampFromOrder(String orderID, String operNo){
		
		StringBuffer updateSql = new StringBuffer().append("UPDATE SFWID_OPER_BUYOFF ")
				   .append(" SET UCF_OPER_BUYOFF_VCH1 = NULL, ")
				   .append(" UCF_OPER_BUYOFF_VCH2 = NULL, ")
				   .append(" UCF_OPER_BUYOFF_VCH3 = NULL, ")
				   .append(" UCF_OPER_BUYOFF_DATE1 = NULL, ")
				   .append(" UCF_OPER_BUYOFF_NUM1 = NULL ")
				   .append(" WHERE ORDER_ID = ? ")
				   .append(" AND OPER_KEY = (SELECT OPER_KEY ")
				   .append(" FROM SFWID_OPERATIONS ")
				   .append(" WHERE ORDER_ID = ? ")
				   .append(" AND OPER_NO = ?) ");
	
		 ParameterHolder params = new ParameterHolder();
		 params.addParameter(orderID);
		 params.addParameter(orderID);
		 params.addParameter(operNo);
		 
		 eds.update(updateSql.toString(), params);
	}
	
	public List getOrderInfo(String orderNo){
		StringBuffer selectSql;
		selectSql = new StringBuffer().append("SELECT *                      ")
				   					  .append("  FROM PWUST_SFWID_ORDER_DESC ")
				   					  .append(" WHERE PW_ORDER_NO = ?        ");

		ParameterHolder params = new ParameterHolder();
		 params.addParameter(orderNo);
		 
		 return eds.queryForList(selectSql.toString(), params);
	}
	//end defect 1819
}