/*
 *  This unpublished work, first created in 2018 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2018.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    ModuleSwapsDaoImplPW.java
 * 
 *  Created: 2018-02-05
 * 
 *  Author:  Catrina Nguyen
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2018-02-05		C. Nguyen		Initial Release SMRO_TR_T226 - Module Swaps Interface
 *  2018-04-10		C. Nguyen		Modify insertModuleSwapsDef to put in the try catch block.  If one fails, try the other. 
 *  								This is because of 2 different xml formats that could be coming down from SAP for the dates.
 *  2018-05-18		C. Nguyen		Added logic to process swaps information
 *  2018-07-09		C. Nguyen		Added a few methods process swaps
 *  2018-08-28		Fred Ettefagh   defect 971, fixed sql bugs 
 */

package com.pw.solumina.sfwid.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;
import com.pw.solumina.sfwid.dao.IModuleSwapDaoPW;

public class ModuleSwapsDaoImplPW implements IModuleSwapDaoPW {
	
	@Reference
	private JdbcDaoSupport jdbcDao;
	

	
	public void insertModuleSwapsDef(String salesOrder,
									 String dokNo,
									 String cfgModuleCode,
									 String serialNo, 
									 String custSerialNo, 
									 String updtUserId, 
									 String updtTime, 
									 String messageAction){

		//different updtTime dates formats.  If the first insert block fails, try the second blocks.
		try {
			StringBuffer insertSql = new StringBuffer("INSERT INTO PWUST_R_MODULE_SWAP_LOG  ( SALES_ORDER, " +
														" DOK_NO, "+
														" CFG_MODULE_CODE, "+
														" SERIAL_NO,"+ 
														" CUST_SERIAL_NO, "+
														" UPDT_USERID, "+ 
														" MESSAGE_UPDT_TIMESTR, "+ 
														" MESSAGE_UPDT_TIMESTAMP, "+
														" MESSAGE_ACTION , "+
														" LAST_ACTION )   " +
													" VALUES ( ? , ? , ? , ?, ? , ?, ?,  TO_DATE(?, 'YYYYMMDDHH24MISS') , ?, ?) ") ; 

			ParameterHolder params = new ParameterHolder();

			params.addParameter(0, salesOrder  );
			params.addParameter(1, dokNo);
			params.addParameter(2, cfgModuleCode);

			params.addParameter(3, serialNo); 
			params.addParameter(4, custSerialNo);
			params.addParameter(5, updtUserId); 
			params.addParameter(6, updtTime);
			params.addParameter(7, updtTime);
			params.addParameter(8, messageAction);
			params.addParameter(9, "INSERTED");

			jdbcDao.insert(insertSql.toString(),params);
			
		} catch (Exception e) {
			
			StringBuffer insertSql = new StringBuffer("INSERT INTO PWUST_R_MODULE_SWAP_LOG  ( SALES_ORDER, " +
													  " DOK_NO, "+
													  " CFG_MODULE_CODE, "+
													  " SERIAL_NO,"+ 
													  " CUST_SERIAL_NO, "+
													  " UPDT_USERID, "+ 
													  " MESSAGE_UPDT_TIMESTR, "+ 
													  " MESSAGE_UPDT_TIMESTAMP, "+
													  " MESSAGE_ACTION , "+
													  " LAST_ACTION )   " +
													  " VALUES ( ? , ? , ? , ?, ? , ?, ?,  TO_DATE(?, 'MM/DD/YYYY') , ?, ?) ") ; 

			ParameterHolder params = new ParameterHolder();

			params.addParameter(0, salesOrder  );
			params.addParameter(1, dokNo);
			params.addParameter(2, cfgModuleCode);

			params.addParameter(3, serialNo); 
			params.addParameter(4, custSerialNo);
			params.addParameter(5, updtUserId); 
			params.addParameter(6, updtTime);
			params.addParameter(7, updtTime);
			params.addParameter(8, messageAction);
			params.addParameter(9, "INSERTED");

			jdbcDao.insert(insertSql.toString(),params);
			
		}

	}


	public List getPwustModuleSwapsLogColumnLenghts(){

		StringBuffer selectSql = new StringBuffer(" SELECT T.DATA_LENGTH,T.COLUMN_NAME " +
		                                          " FROM DBA_TAB_COLS T " +
				                                  " WHERE T.TABLE_NAME = ? " + 
		                                          " AND T.DATA_TYPE =  ? ");


		ParameterHolder params = new ParameterHolder();
		params.addParameter(0, "PWUST_R_MODULE_SWAP_LOG");
		params.addParameter(1, "VARCHAR2");
	
		return jdbcDao.queryForList(selectSql.toString(), params);
		
	}
	
	public String checkExsitsPwustOrders(String salesOrder, String engineModel)
	{
		String return_val = null;
		
		StringBuffer selectSql = new StringBuffer(" SELECT 1  " +
		                                          " FROM PWUST_SFWID_ORDER_DESC T " +
				                                  " WHERE T.SALES_ORDER = ? "  +
				                                  " AND T.ENGINE_MODEL = ? ") ;


		ParameterHolder params = new ParameterHolder();
		params.addParameter(0, salesOrder);
		params.addParameter(1, engineModel);
	
		String result = jdbcDao.queryForString(selectSql.toString(), params);
		if (StringUtils.equalsIgnoreCase(result, "1")){
			return_val = "Y";
		}else{
			return_val = "N";
		}
		
		return return_val;
		
	}
	
	
	public String checkExsitsPwustOrders(String orderId)
	{

		String return_val = null;
		
		StringBuffer selectSql = new StringBuffer(" SELECT 1  " +
		                                          " FROM PWUST_SFWID_ORDER_DESC T " +
				                                  " WHERE T.ORDER_ID = ? "  +
				                                  " AND T.SALES_ORDER = 'UNASSIGNED' ") ;


		ParameterHolder params = new ParameterHolder();
		params.addParameter(0, orderId);
	
		String result = jdbcDao.queryForString(selectSql.toString(), params);
		if (StringUtils.equalsIgnoreCase(result, "1")){
			return_val = "Y";
		}else{
			return_val = "N";
		}
		
		return return_val;
		
	}

	
	public List getExsitsPwustSalesOrder( String moduleCode, String serialNo){	
		
		List list = null;
		StringBuffer selectSql = new StringBuffer(" select DISTINCT T.SALES_ORDER, "
				                                                + " T.ENGINE_TYPE, "
				                                                + " T.ENGINE_MODEL, "
				                                                + " T.MODULE_CODE, "
				                                                + " T.ACT_NO, "
				                                                + " T.SUB_ACT_NO, "
				                                                + " T.CUSTOMER, "
				                                                + " T.WORK_SCOPE, "
				                                                + " T.SERIAL_NO, "
				                                                + " T.WORK_LOC, "
				                                                + " T.CONFIRM_NO  ")
										  .append(" from PWUST_SALES_ORDER T ")
										  //.append(" where T.sales_order like ? || '%' ")
										  .append(" where T.MODULE_CODE = ? " )
										  .append(" and T.SERIAL_NO = ? ");


		ParameterHolder params = new ParameterHolder();
		params.addParameter(0, moduleCode);
		params.addParameter(1, serialNo);
	
		list = jdbcDao.queryForList(selectSql.toString(), params);
		return list;
		
	}

	public void insertPwustOrders(String salesOrder,
							   	  String engineModel,
								  String workScope,
								  String prevSalesOrder, 
								  String prevEngineModel,
								  String updtUserId){

			StringBuffer insertSql = new StringBuffer("insert into PWUST_SFWID_ORDER_DESC (ORDER_ID, PW_ORDER_NO, SALES_ORDER, ENGINE_MODEL, WORK_SCOPE, SUPERIORNET, SUBNET, CUSTOMER, UPDT_USERID, TIME_STAMP, SAP_SERIAL_NO, PW_ORDER_TYPE) ")
											  .append(" (SELECT T.ORDER_ID, T.PW_ORDER_NO, ?, ?, ?, T.SUPERIORNET, T.SUBNET, T.CUSTOMER, ?, SYSDATE, T.SAP_SERIAL_NO, T.PW_ORDER_TYPE ")
											  .append(" FROM PWUST_SFWID_ORDER_DESC T ")
											  .append(" WHERE T.SALES_ORDER = ? ")
											  .append("   AND T.ENGINE_MODEL = ? ) "); 

			ParameterHolder params = new ParameterHolder();

			params.addParameter(0, salesOrder  );
			params.addParameter(1, engineModel);
			params.addParameter(2, workScope);

			params.addParameter(3, updtUserId); 
			params.addParameter(4, prevSalesOrder);
			params.addParameter(5, prevEngineModel);

			jdbcDao.insert(insertSql.toString(),params);
	}

	public void updatePwustOrders(String salesOrder,
								  String prevSalesOrder, 
								  String prevEngineModel,
								  String updtUserId){

			StringBuffer updateSql = new StringBuffer("update PWUST_SFWID_ORDER_DESC T ")
											  .append("       T.SALES_ORDER = ?, ")
											  .append("       T.UPDT_USERID = ?, ")
											  .append("       T.TIME_STAMP = sysdate ")
											  .append(" WHERE T.SALES_ORDER = ? ")
											  .append(" AND T.ENGINE_MODEL = ? ) "); 

			ParameterHolder params = new ParameterHolder();

			params.addParameter(0, salesOrder  );
			params.addParameter(1, updtUserId); 
			params.addParameter(2, prevSalesOrder);
			params.addParameter(3, prevEngineModel);

			jdbcDao.update(updateSql.toString(),params);

	}

	public void updatePwustOrders(String salesOrder,
			                      String orderId, 
			                      String updtUserId){

			StringBuffer updateSql = new StringBuffer(" UPDATE PWUST_SFWID_ORDER_DESC T ")
											  .append(" SET T.SALES_ORDER = ?, ")
											  .append("     T.UPDT_USERID = ?, ")
											  .append("     T.TIME_STAMP = sysdate ")
											  .append(" WHERE T.ORDER_ID = ? ");

			ParameterHolder params = new ParameterHolder();

			params.addParameter(0, salesOrder  );
			params.addParameter(1, updtUserId); 
			params.addParameter(2, orderId);

			jdbcDao.update(updateSql.toString(),params);

	}

	public void deleteOutgoingSalesOrder(String salesOrder,String engineModel)
	{

	
			StringBuffer updateSql = new StringBuffer("update SFWID_ORDER_DESC T   ")
											  .append(" SET T.UCF_ORDER_VCH12 = null ")
											  .append(" where T.ORDER_ID  = (SELECT T2.ORDER_ID ")
											  .append("                      FROM PWUST_SFWID_ORDER_DESC T2 ")
											  .append("                      WHERE T2.SALES_ORDER = ? ")
											  .append("                      AND T2.ENGINE_MODEL = ? ) "); 

			ParameterHolder params = new ParameterHolder();

			params.addParameter(0, salesOrder  );
			params.addParameter(1, engineModel);


			jdbcDao.update(updateSql.toString(),params);

	}

	public void updateOutgoingSalesOrder(String salesOrder,String engineModel, String outgoingSO)
	{

	
			StringBuffer updateSql = new StringBuffer("update SFWID_ORDER_DESC T   ")
											  .append(" SET T.UCF_ORDER_VCH12 = ? ")
											  .append(" where T.ORDER_ID  = (SELECT T2.ORDER_ID ")
											  .append("                      FROM PWUST_SFWID_ORDER_DESC T2 ")
											  .append("                      WHERE T2.SALES_ORDER = ? ")
											  .append("                      AND T2.ENGINE_MODEL = ? ) "); 

			ParameterHolder params = new ParameterHolder();
			params.addParameter(0, outgoingSO  );
			params.addParameter(1, salesOrder  );
			params.addParameter(2, engineModel);


			jdbcDao.update(updateSql.toString(),params);

	}

	public void updateOutgoingSalesOrder(String orderId, String outgoingSO)
	{

	
			StringBuffer updateSql = new StringBuffer("update SFWID_ORDER_DESC T   ")
											  .append(" SET T.UCF_ORDER_VCH12 = ? ")
											  .append(" where T.ORDER_ID  = ? "); 

			ParameterHolder params = new ParameterHolder();
			params.addParameter(0, outgoingSO  );
			params.addParameter(1, orderId  );

			jdbcDao.update(updateSql.toString(),params);

	}

	public void updateOutgoingSalesOrder(String orderId, String outgoingSO, String serialNo, String dummy)
	{

	
			StringBuffer updateSql = new StringBuffer("update SFWID_ORDER_DESC T   ")
											  .append(" SET T.UCF_ORDER_VCH12 = ?, ")
											  .append("   T.UCF_ORDER_VCH14 = ? ")
											  .append(" where T.ORDER_ID  = ? "); 

			ParameterHolder params = new ParameterHolder();
			params.addParameter(0, outgoingSO  );
			params.addParameter(1, serialNo  );
			params.addParameter(2, orderId  );

			jdbcDao.update(updateSql.toString(),params);

	}


	public String getSalesOrder( String moduleCode, String serialNo){		//String dokNo,
		
		String retVal = "";
		StringBuffer selectSql = new StringBuffer("select sales_order from ")
										  .append(" (select distinct t.Sales_Order from PWUST_R_MODULE_SWAP_LOG t  ")
										  .append(" where T.CFG_MODULE_CODE = ? ")
										  //.append(" AND t.Dok_No = ? ")		
										  .append(" AND t.SERIAL_NO = ? " )
										  .append(" AND t.MESSAGE_ACTION = 'DEL' ")
										  .append(" ORDER BY t.UPDT_TIMESTAMP DESC) ")
										  .append(" where rownum = 1 ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(0, moduleCode);
		//params.addParameter(1, dokNo);
		params.addParameter(1, serialNo);
	
		retVal = jdbcDao.queryForString(selectSql.toString(), params);
		return retVal;
		
	}
	
	public List getOrderId( String serialNo){	
		List list = null;
		StringBuffer selectSql = new StringBuffer("SELECT distinct T.ORDER_ID FROM SFWID_ORDER_DESC T ")
										  .append(" WHERE T.UCF_ORDER_VCH14 = ?  ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(0, serialNo);
	
		list = jdbcDao.queryForList(selectSql.toString(), params);
		return list;
		
	}
	
	public List getOrderId( String serialNo, String modCode){	
		List list = null;
		StringBuffer selectSql = new StringBuffer("SELECT distinct T.ORDER_ID FROM SFWID_ORDER_DESC T ")
										  .append(" WHERE T.UCF_ORDER_VCH14 = ?  ")
										  .append("  and T.UCF_PLAN_VCH3 = ? " );

		ParameterHolder params = new ParameterHolder();
		params.addParameter(0, serialNo);
		params.addParameter(1, modCode);
	

		list = jdbcDao.queryForList(selectSql.toString(), params);
		return list;
		
	}
	
	
	public List getOrderId(  String modCode, String salesOrder, String dummy){	
		List list = null;
		StringBuffer selectSql = new StringBuffer("SELECT distinct T.ORDER_ID FROM PWUST_OVERHAUL_WO_DISP_TAB_V T ")
										  .append(" WHERE T.SALES_ORDER = ?  ")
										  .append("  and T.UCF_PLAN_VCH3 = ? " );

		ParameterHolder params = new ParameterHolder();
		params.addParameter(0, salesOrder);
		params.addParameter(1, modCode);
	
		list = jdbcDao.queryForList(selectSql.toString(), params);
		return list;
		
	}

}
