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
 *  File:    OrderAssembleTextDaoPW.java
 * 
 *  Created: 2017-08-30
 * 
 *  Author:  Rusty Cannon
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2017-08-30		R. Cannon		Initial Release SMRO_EXPT_205
 *  2018-01-25      N.Gnassounou    SMRO_WOE_204 
 *  2018-03-25		S.Niu			Defect 410 - Sub Orders should be a copy of the parent from when it was released, not current copies of parent orders
 *
 */

package com.pw.solumina.sfwid.dao;

import java.util.Map;

import static com.ibaset.common.FrameworkConstants.ACCEPT;
import static com.ibaset.common.SoluminaConstants.PENDING;
import static com.ibaset.common.SoluminaConstants.REJECT;
import static com.ibaset.common.SoluminaConstants.SKIP;

import java.util.List;

import org.springframework.util.StringUtils;

import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.Reference;
import com.ibaset.common.sql.ParameterHolder;

public class OrderAssembleTextDaoPW
{
	@Reference private JdbcDaoSupport eds;

	public boolean isOrderClassified(String orderId)
	{
		boolean orderClassified = false;
		String jurisdiction;
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT O.COMMODITY_JURISDICTION ")
												   .append("   FROM SFWID_ORDER_DESC O ")
												   .append("  WHERE O.ORDER_ID = ? ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		
		jurisdiction = eds.queryForString(selectSql.toString(), params);
		
		if(!StringUtils.isEmpty(jurisdiction) || jurisdiction != null)
		{
			//Non-null COMMODITY_JURISDICTION means order is classified
			orderClassified = true;
		}
		
		return orderClassified;
	}

	@SuppressWarnings("rawtypes")
	public Map selectExportControlInfoForOrder(String orderId)
   {
		StringBuffer selectSql = new StringBuffer().append(" SELECT O.ORDER_NO, ")
												   .append(" 		O.COMMODITY_JURISDICTION, ")
												   .append(" 		O.COMMODITY_CLASSIFICATION, ")
												   .append(" 		NVL(O.UCF_PLAN_FLAG1, 'N') AS UCF_PLAN_FLAG1, ")
												   .append(" 		O.ASGND_LOCATION_ID, ")
												   .append(" 		W.WORK_LOC, ")
												   .append(" 		O.SECURITY_GROUP ")
												   .append("   FROM SFWID_ORDER_DESC O, SFFND_WORK_LOC_DEF W ")
												   .append("  WHERE O.ASGND_LOCATION_ID = W.LOCATION_ID(+) ")
												   .append(" 	AND O.ORDER_ID = ? ");

        ParameterHolder params = new ParameterHolder();
        params.addParameter(orderId);

        Map exportMap = eds.queryForMap(selectSql.toString(), params);

        return exportMap;
    }

	/**SMRO_WOE_204  
	 * @XCS4071 N.Gnassounou
	 */	
	  public void updateSupplemnetalOperNo(String orderNo, String ucf_order_vch13)
	  
		{		
			StringBuffer update = new StringBuffer().append("UPDATE ")
													.append(" SFWID_ORDER_DESC ")
													.append("SET ")  
													.append("UCF_ORDER_VCH13 = ? ")  											
													.append(" WHERE ORDER_NO = ? ");
			
			ParameterHolder params = new ParameterHolder();
			params.addParameter(ucf_order_vch13);
			params.addParameter(orderNo);
		

			eds.update(update.toString(), params);	
		}
	  
	    //Defect 410
	    public String selectOrderIdByOrderNumber(String orderNumber)
	    {
	        StringBuilder selectSql = new StringBuilder().append("SELECT ")
	                                                   .append("    ORDER_ID ")
	                                                    .append("FROM ")
	                                                   .append("    SFWID_ORDER_DESC ")
	                                                   .append("WHERE ")
	                                                   .append("    ORDER_NO = ? ");

	        ParameterHolder parameters = new ParameterHolder();
	        parameters.addParameter(orderNumber);

	        String orderId = eds.queryForString(selectSql.toString(), parameters);
	        
	        return orderId;
	    }
	  
	    //Defect 410
		public List<Map> selectOperInfo(String orderId) 
		{
			List<Map> list = null;

			StringBuffer selectSql = new StringBuffer().append(" SELECT ")
					                                   .append("   OPER_NO, ")
					                                   .append("   OPER_KEY, ")
					                                   .append("   STEP_NO, ")
					                                   .append("   STEP_KEY ")
					                                   .append(" FROM ")
					                                   .append("   SFWID_OPER_DESC ")
					                                   .append(" WHERE ")
					                                   .append("   ORDER_ID = ? ")
					                                   .append(" AND " )
					                                   .append("   STEP_KEY = -1 ");


			ParameterHolder parameters = new ParameterHolder();
			parameters.addParameter(orderId);

			list = eds.queryForList(selectSql.toString(), parameters);

			return list;	
		}
	 
	    //Defect 410
		public String selectOperKey(String orderId) 
		{
			StringBuffer selectSql = new StringBuffer().append(" SELECT ")
					                                   .append("   OPER_KEY ")
					                                   .append(" FROM ")
					                                   .append("   SFWID_OPER_DESC ")
					                                   .append(" WHERE ")
					                                   .append("   ORDER_ID = ? ")
					                                   .append(" AND " )
					                                   .append("   STEP_KEY = -1 ");


			ParameterHolder parameters = new ParameterHolder();
			parameters.addParameter(orderId);

			String operKey = eds.queryForString(selectSql.toString(), parameters);

			return operKey;	
		}
	 
	    //Defect 410
		public List<Map> selectCopyOperInfo(String orderNumber) 
		{
			List<Map> list = null;

			StringBuffer selectSql = new StringBuffer().append(" SELECT ")
					                                   .append("   S.ORDER_NO, ")
					                                   .append("   T.OPER_NO, ")
					                                   .append("   S.PART_NO, ")
					                                   .append("   T.TITLE, ")
					                                   .append("   T.EXE_ORDER, ")
					                                   .append("   S.LTA_SEND_FLAG ")
					                                   .append(" FROM ")
					                                   .append("   SFWID_OPER_DESC T, SFWID_ORDER_DESC S ")
					                                   .append(" WHERE ")
					                                   .append("   S.ORDER_NO = ? ")
					                                   .append(" AND " )
					                                   .append("   T.STEP_KEY = -1 ")
					                                   .append(" AND " )
					                                   .append("   T.ORDER_ID = S.ORDER_ID ");

			ParameterHolder parameters = new ParameterHolder();
			parameters.addParameter(orderNumber);

			list = eds.queryForList(selectSql.toString(), parameters);

			return list;	
		}
	 
		//Defect 410
	    public int updateOrderType(String orderId, String newOrderType)
	    {
	        StringBuilder update = new StringBuilder()
	                                                .append(" UPDATE SFWID_ORDER_DESC      ")
	                                                .append(" SET ORDER_TYPE = ?          ")
	                                                .append(" WHERE ORDER_ID = ?          ")
	                                                ; 
	        ParameterHolder parameters = new ParameterHolder();
	        parameters.addParameter(newOrderType);
	        parameters.addParameter(orderId);

	        return eds.update(update.toString(), parameters);
	    }
	  
	  
	  
} //end OrderAssembleTextDaoPW Class
