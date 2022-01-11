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
 *  File:    T301DaoImplPW.java
 * 
 *  Created: 2019-05-24
 * 
 *  Author:  David Miron
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2019-05-24		D. Miron		Initial Release SMRO_TR_T301 - SM Order Interface
 *  								
 */
package com.pw.solumina.interfaces.dao.impl;

import java.util.List;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;
import com.pw.solumina.interfaces.dao.IT301DaoPW;

public class T301DaoImplPW  implements IT301DaoPW {
	
	@Reference
	private JdbcDaoSupport jdbcDao;
	
	@SuppressWarnings("rawtypes")
	public List getT301ColumnLengths()
	{

		StringBuffer selectSql = new StringBuffer().append(" SELECT T.DATA_LENGTH,T.COLUMN_NAME ")
				                                   .append("   FROM DBA_TAB_COLS T ")
						                           .append("  WHERE T.TABLE_NAME = ? ") 
								                   .append("    AND T.DATA_TYPE =  ? ")
								                   .append(" UNION ")
								                   .append(" SELECT T.DATA_LENGTH,T.COLUMN_NAME ")
				                                   .append("   FROM DBA_TAB_COLS T ")
						                           .append("  WHERE T.TABLE_NAME = ? ") 
								                   .append("    AND T.DATA_TYPE =  ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter("PWUST_INT_SAP_SM_ORDER");
		params.addParameter("VARCHAR2");
		params.addParameter("PWUST_INT_SAP_SM_SERIALS");
		params.addParameter("VARCHAR2");
	
		return jdbcDao.queryForList(selectSql.toString(), params);
	}
	
	public void insertT301Order(String idocNumber,			
			                    String messageType,
			                    String updtUserid,
			                    String orderType,
			                    String plant,
			                    String notification,
			                    String salesOrder,
			                    String salesDocitem,
			                    String superiorSmOrder,
			                    String smOrder,
			                    String customerNumber,
			                    String customerName,
			                    String purchaseOrder,
			                    String quoteRequired,
			                    String requiredDeliveryDate,
			                    String engineModel,
			                    String engineSerialNumber,
			                    String engineSection,
			                    String engineSectionDesc,
			                    String incomingMaterial,
			                    String outgoingMaterial,
			                    String quantity,
			                    String lotCode)
	{
		StringBuffer insertSql = new StringBuffer().append(" INSERT INTO PWUST_INT_SAP_SM_ORDER ( ")
												   .append("    IDOC_NUMBER, ") 
												   .append("    MESSAGE_TYPE, ")
												   .append("    SAP_UPDT_USERID, ")
												   .append("    ORDER_TYPE, ")
												   .append("    PLANT, ")
												   .append("    NOTIFICATION, ")
												   .append("    SALES_ORDER, ")
												   .append("    SALES_DOC_ITEM, ")
												   .append("    SUPERIOR_SM_ORDER, ")
												   .append("    SM_ORDER, ")
												   .append("    CUSTOMER_NUMBER, ")
												   .append("    CUSTOMER_NAME, ")
												   .append("    PURCHASE_ORDER, ")
												   .append("    QUOTE_REQUIRED, ")
												   .append("    REQUIRED_DELIVERY_DATE, ")
												   .append("    ENGINE_MODEL, ")
												   .append("    ENGINE_SERIAL_NUMBER, ")
												   .append("    ENGINE_SECTION, ")
												   .append("    ENGINE_SECTION_DESC, ")
												   .append("    INCOMING_MATERIAL, ")
												   .append("    OUTGOING_MATERIAL, ")
												   .append("    QUANTITY, ")
												   .append("    LOT_CODE, ")
												   .append("    TIME_STAMP ")
												   .append("    ) VALUES ( ")
												   .append("    ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ")
												   .append("    ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ")
												   .append(     jdbcDao.getTimestampFunction() + ")" );
												   
		ParameterHolder params = new ParameterHolder();
		params.addParameter(idocNumber);			
		params.addParameter(messageType);
		params.addParameter(updtUserid);
		params.addParameter(orderType);
		params.addParameter(plant);
		params.addParameter(notification);
		params.addParameter(salesOrder);
		params.addParameter(salesDocitem);
		params.addParameter(superiorSmOrder);
		params.addParameter(smOrder);
		params.addParameter(customerNumber);
		params.addParameter(customerName);
		params.addParameter(purchaseOrder);
		params.addParameter(quoteRequired);
		params.addParameter(requiredDeliveryDate);
		params.addParameter(engineModel);
		params.addParameter(engineSerialNumber);
		params.addParameter(engineSection);
		params.addParameter(engineSectionDesc);
		params.addParameter(incomingMaterial);
		params.addParameter(outgoingMaterial);
		params.addParameter(quantity);
		params.addParameter(lotCode);
		
		jdbcDao.insert(insertSql.toString(), params);
	}

	public void insertT301Serials(String idocNumber,			
			String smOrder,
			String serialNumber)
	{
		StringBuffer insertSql = new StringBuffer().append(" INSERT INTO PWUST_INT_SAP_SM_SERIALS ( ")
				.append("    IDOC_NUMBER, ") 
				.append("    SM_ORDER, ")
				.append("    SERIAL_NUMBER, ")
				.append("    TIME_STAMP ")
				.append("    ) VALUES ( ")
				.append("    ?, ?, ?, " +      jdbcDao.getTimestampFunction() + ")" );

		ParameterHolder params = new ParameterHolder();
		params.addParameter(idocNumber);			
		params.addParameter(smOrder);
		params.addParameter(serialNumber);

		jdbcDao.insert(insertSql.toString(), params);
	}
	
	public boolean smOrderExists(String idocNumber) {
		
		Boolean exists = false;
	
		StringBuffer selectSql = new StringBuffer().append(" SELECT *  ")
				                                   .append("   FROM PWUST_INT_SAP_SM_ORDER ") 
				                                   .append("  WHERE IDOC_NUMBER = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(idocNumber);			

		List list = jdbcDao.queryForList(selectSql.toString(), params);
	    if(list.size() > 0) exists = true;

		return exists;
	}
}

