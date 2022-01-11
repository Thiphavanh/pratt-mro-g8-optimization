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
*  File:    OrderMroRepairSplitDao.java
* 
*  Created: 2019-06-05
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2019-06-05 	Fred Ettefagh   Initial Release XXXXXXX
* 2019-08-29    Fred Ettefagh   added new DAO method checkSfwidAsWorkedItem, used for repair WO spit/sub order create
* 2020-06-01    Fred ETtefagh   SMRO_TR_319, Defect 1611, added methods selectSfwidSerialDescPartNo, selectSfwidSerialDescList,selectSfwidSerialDescList, updateSfwidOrderDescQty, updateSfwidLotDescQty
* 2020-06-10    Fred Ettefagh   SMRO_TR_319, Defect 1611, added code to update SFWID_ORDER_DESC.ORDER_STOP_QTY to 0 after a split
* 2020-06-10    Fred Ettefagh   SMRO_TR_319, Defect 1611, added code to update SFWID_ORDER_DESC.ORDER_STOP_QTY to 0 after a split and update sfwid_order_desc.SPLIT_FROM_ORDER_ID and sfwid_order_desc.SPLIT_FLAG
* 2020-06-22    Fred Ettefagh   SMRO_TR_319, Defect 1611, changed metthod updateOrderSplitColumns to update SFWID_ORDER_DESC.SPLIT_FROM_ORDER_ID for both new order and parent order
*/
package com.ibaset.solumina.sfwid.dao.impl;

import static com.ibaset.common.SoluminaConstants.SEMI_COLON;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.solumina.sffnd.application.IParse;

public class OrderMroRepairSplitDao {
	
	@Reference 	private JdbcDaoSupport jdbcDaoSupport;
	@Reference IParse parse = null;
	
	public int updateOrderSplitColumns(String parentOrderId,String newOrderId, String splitValue){

		StringBuffer updateSqlNewOrder = new StringBuffer().append(" UPDATE SFWID_ORDER_DESC T " +
						                                           " SET T.SPLIT_FROM_ORDER_ID = ?, " + 
						                                           " T.SPLIT_FLAG = ? "+ 
																   " WHERE T.ORDER_ID = ? " );
		
		ParameterHolder params1 = new ParameterHolder();
		params1.addParameter(parentOrderId);
		params1.addParameter(splitValue);
		params1.addParameter(newOrderId);
		
		int i = jdbcDaoSupport.update(updateSqlNewOrder.toString(), params1);
		

		
		StringBuffer updateSqlParentOrder = new StringBuffer().append(" UPDATE SFWID_ORDER_DESC T " +
												                      " SET T.SPLIT_FROM_ORDER_ID = ?, " + 
												                      " T.SPLIT_FLAG = ? "+ 
																      " WHERE T.ORDER_ID = ? " );

		ParameterHolder params2 = new ParameterHolder();
		params2.addParameter(parentOrderId);
		params2.addParameter(splitValue);
		params2.addParameter(parentOrderId);
		
		i = jdbcDaoSupport.update(updateSqlParentOrder.toString(), params2);

		
		
		return i;

	}

	
	
	
	public List selectSfwidSerialDescPartNo(String serialNo, String partNo){
		
		StringBuffer select = new StringBuffer().append(" SELECT T1.PART_NO, T.* " +
		                                                " FROM SFWID_SERIAL_DESC T, SFWID_ORDER_DESC T1 " +
														" WHERE T.ORDER_ID = T1.ORDER_ID " +
														" AND T.SERIAL_NO LIKE ? "+
														" AND T1.PART_NO = ? "+
														" ORDER BY T.SERIAL_NO DESC  ");
		
		ParameterHolder params = new ParameterHolder();		
		params.addParameter(serialNo);
		params.addParameter(partNo);
		List list = jdbcDaoSupport.queryForList(select.toString(), params);
		return list;
	}
	
	
	@SuppressWarnings("rawtypes")
	public List selectSfwidSerialDescList(String orderId, String serialNumberList){
		
		
		String[] serialNumberArray  = parse.parse(serialNumberList,SEMI_COLON,true);
		
		ParameterHolder params = new ParameterHolder();
		
		StringBuffer select = new StringBuffer().append(" SELECT T.* " +
	                                                    " FROM SFWID_SERIAL_DESC T "+
	                                                    " WHERE T.ORDER_ID = ? " + 
                                                        " AND T.SERIAL_NO IN( ");
		
		for (int i=0; i< serialNumberArray.length; i++){
		    if (i==0){
		    	select.append(" ? ");
		    }else{
		    	select.append(" , ? ");
		    }
		}
		select.append(" ) ");
		
		params.addParameter(orderId);
		
		for (int i=0; i< serialNumberArray.length; i++){
			params.addParameter(serialNumberArray[i]);	
		}
		
		List list = jdbcDaoSupport.queryForList(select.toString(), params);
		return list;
		
	}
	
	public int updateSfwidOrderDescQty(String orderId,Number orderQty,Number orderStopQty){

		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFWID_ORDER_DESC T " +
				                                           " SET T.ORDER_QTY = ?, " + 
				                                               " T.ORDER_STOP_QTY = ? "+ 
														   " WHERE T.ORDER_ID = ? " );
		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderQty);
		params.addParameter(orderStopQty);
		params.addParameter(orderId);
		
		int numOfRowsUpd = jdbcDaoSupport.update(updateSql.toString(), params);
		return  numOfRowsUpd;
	}
	
	public int updateSfwidLotDescQty(String orderId,Number orderQty){

		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFWID_LOT_DESC T SET T.LOT_QTY = ? WHERE T.ORDER_ID = ? " );
		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderQty);
		params.addParameter(orderId);
		
		int numOfRowsUpd = jdbcDaoSupport.update(updateSql.toString(), params);
		return  numOfRowsUpd;
	}
	

	public void updateSfwidSerialDescUCF(String orderId, String serialNo, String ucfSerialVch1){
		
		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFWID_SERIAL_DESC T " +
				                                           " SET T.UCF_SERIAL_VCH1 = ? " + 
				                                           " WHERE T.ORDER_ID = ? " +
				                                           " AND T.SERIAL_NO = ? " );
		ParameterHolder params = new ParameterHolder();
		params.addParameter(ucfSerialVch1);
		params.addParameter(orderId);
		params.addParameter(serialNo);
		jdbcDaoSupport.update(updateSql.toString(), params);

	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String checkSfwidAsWorkedItem(String partNo, String lotNo, String[] serialNumberArray ){
		
		StringBuilder selectSql = new  StringBuilder().append(" SELECT T.* ") 
				                                      .append(" FROM SFWID_AS_WORKED_ITEM T " )
				                                      .append(" WHERE T.PART_NO = ? " )
				                                      .append(" AND T.LOT_NO = ? ")
				                                      .append(" AND T.SCRAP_FLAG = ? ")
				                                      .append(" AND T.AS_WORKED_ITEM_STATUS = ? ")
				                                      .append(" AND T.SERIAL_NO IN  ");
		
		for (int i=0; i< serialNumberArray.length; i++){
			if (i == 0){
				selectSql.append(" ( ? " );
			}else{
				selectSql.append(" , ? " );
			}
		}
		selectSql.append(" ) " );
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(partNo);
		parameters.addParameter(lotNo);
		parameters.addParameter("Y");
		parameters.addParameter("STOP");
		
		for (int i=0; i< serialNumberArray.length; i++){
			parameters.addParameter("'"+serialNumberArray[i]+ "''" );
		}
		
		String serialNo = null;
		List<Map> list = jdbcDaoSupport.queryForList(selectSql.toString(),parameters);
		if (list!=null && !list.isEmpty()){
			for (Map  sfwidAsWorkedItemMap: list) {
				if (StringUtils.isBlank(serialNo)){
					serialNo = (String)sfwidAsWorkedItemMap.get("SERIAL_NO");
				}else{
					serialNo = serialNo + "," +(String)sfwidAsWorkedItemMap.get("SERIAL_NO");
				}
			}
		}
		return serialNo;
	}
    
}
