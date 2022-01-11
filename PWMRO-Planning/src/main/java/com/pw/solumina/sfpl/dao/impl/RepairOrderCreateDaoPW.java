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
*  File:    RepairOrderCreateDaoPW.java
*  
*  PW_SMRO_WOE_310_Order_Creation
* 
*  Created: 2019.04.12
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date             Who            Description
*  -----------    ------------     ---------------------------------------------------
*  2019-04-30     Fred Ettefagh  
*  2019-05-03     Fred Ettefagh    added more code for repair order create 
*  2019-06-05     Fred Ettefagh    changed code based on Dave M code for interface T301
*  2019-12-03     Fred Ettefagh    added new method sfplPlanVSelect
*  2019-12-16     Fred Ettefagh    moved method sfplPlanVSelect to CommonDaoPW
*  2020-05-29     Fred Ettefagh    SMRO_WOE_302 - Defect 1584,added new method updatePwustSfwidOrderDesc(String orderId, String outgoingPartNo, String sapOrderType ) to RepairOrderCreateDaoPW class
*/
package com.pw.solumina.sfpl.dao.impl;

import java.util.List;
import java.util.Map;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;

public class RepairOrderCreateDaoPW {

	@Reference	private JdbcDaoSupport jdbcDaoSupport;
	
	public int updatePwustSfwidOrderDesc(String orderId, String outgoingPartNo, String sapOrderType, String sapLotCode){
		
		StringBuffer updateSql = new StringBuffer().append(" UPDATE PWUST_SFWID_ORDER_DESC  T "+
 														   " SET T.OUTGOING_PART_NO = ?," +
													   	   "	 T.SAP_ORDER_TYPE = ? ," +
													   	   "	 T.SAP_LOT_CODE = ? " +
														   " WHERE T.ORDER_ID =  ? " );
		ParameterHolder params = new ParameterHolder();
		params.addParameter(outgoingPartNo);
		params.addParameter(sapOrderType);
		params.addParameter(sapLotCode);
		params.addParameter(orderId);
		int numOfRowsUpd = jdbcDaoSupport.update(updateSql.toString(), params);
		return  numOfRowsUpd;
	}
	
	@SuppressWarnings("rawtypes")
	public Map sfplItemDescMasterAllSelect(String partNo, String partChg){
		
		Map returnMapValue = null;
		
		StringBuilder selectSql = new StringBuilder().append("SELECT T.* FROM SFPL_ITEM_DESC_MASTER_ALL T  WHERE T.PART_NO = ?  AND T.PART_CHG = ?");

        ParameterHolder parameters = new ParameterHolder();
        parameters.addParameter(partNo);
        parameters.addParameter(partChg);
        
        List list = jdbcDaoSupport.queryForList(selectSql.toString(), parameters);
        
        if (list != null && !list.isEmpty()){
        	returnMapValue = (Map)list.get(0);
        }
        
        return returnMapValue;
	}
	

	@SuppressWarnings("rawtypes")
	public Map sfwidOrderDescSelect(String orderNo){
		
		Map returnMapValue = null;
		
		StringBuilder selectSql = new StringBuilder().append(" SELECT T.* FROM SFWID_ORDER_DESC T WHERE T.ORDER_NO = ?");

        ParameterHolder parameters = new ParameterHolder();
        parameters.addParameter(orderNo);
        
        List list = jdbcDaoSupport.queryForList(selectSql.toString(), parameters);
        
        if (list != null && !list.isEmpty()){
        	returnMapValue = (Map)list.get(0);
        }
        
        return returnMapValue;
	}

	
	@SuppressWarnings("rawtypes")
	public List pwustIntRepairWoDataSelect(String iDocNumber){
		
		StringBuilder selectSql = new StringBuilder().append("SELECT T.* FROM PWUST_INT_SAP_SM_ORDER T WHERE T.IDOC_NUMBER = ? AND T.ORDER_ID IS NULL ");

        ParameterHolder parameters = new ParameterHolder();
        parameters.addParameter(iDocNumber);
        
        List list = jdbcDaoSupport.queryForList(selectSql.toString(), parameters);

		return list;
	}
	
	
	@SuppressWarnings("rawtypes")
	public List pwustIntRepairWoSerialNoSelect(String iDocNumber, String smOrderNo){
		
		StringBuilder selectSql = new StringBuilder().append("SELECT T.* FROM PWUST_INT_SAP_SM_SERIALS T WHERE T.IDOC_NUMBER = ? AND T.SM_ORDER = ?");

        ParameterHolder parameters = new ParameterHolder();
        parameters.addParameter(iDocNumber);
        parameters.addParameter(smOrderNo);
        List list = jdbcDaoSupport.queryForList(selectSql.toString(), parameters);

		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public List sfplPlanVSelect (String planNo){
		StringBuilder selectSql = new StringBuilder().append("SELECT T.* 	FROM SFPL_PLAN_V T	WHERE T.PLAN_NO = ?");

        ParameterHolder parameters = new ParameterHolder();
        parameters.addParameter(planNo);
        
        List list = jdbcDaoSupport.queryForList(selectSql.toString(), parameters);

		return list;
	}
	
	public int updatePwustIntRepairWoDataProcesFlagToSuccess(String iDocNumber, String smOrderNo, String orderId){
		
		StringBuilder updateSql = new StringBuilder().append(" UPDATE PWUST_INT_SAP_SM_ORDER T " +
				                                             " SET T.ORDER_CREATE_DATE = (SELECT SFMFG.SFDB_SYSDATE() FROM DUAL), " +
				                                                 " T.ORDER_ID = ? " + 
				                                             " WHERE T.IDOC_NUMBER = ?  " + 
				                                             " AND T.SM_ORDER = ?");
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(iDocNumber);
		parameters.addParameter(smOrderNo);
		
		int returnVal = jdbcDaoSupport.update(updateSql.toString(), parameters);
		return returnVal;
	}
}
