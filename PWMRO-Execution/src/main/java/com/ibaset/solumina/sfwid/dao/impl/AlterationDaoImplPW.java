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
*  File:    AlterationDaoImplPW.java
* 
*  Created: 2020-06-23
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2020-06-23 	Fred Ettefagh   Initial Release Defect 1703
* */
package com.ibaset.solumina.sfwid.dao.impl;

import static com.ibaset.common.FrameworkConstants.IN_QUEUE;
import static com.ibaset.common.FrameworkConstants.X;
import static com.ibaset.common.SoluminaConstants.PENDING;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.solumina.sfwid.dao.IAlterationDao;
import com.pw.common.dao.CommonDaoPW;

public abstract class AlterationDaoImplPW extends ImplementationOf<IAlterationDao> implements IAlterationDao {

	@Reference 	private JdbcDaoSupport jdbcDaoSupport;
	@Reference private CommonDaoPW commonDaoPW;
	

	
    public int selectSerialOperationCount(String orderId, Number operationKey) {
    	
    	 int  returnValue = 0;
		 boolean isRepairOrderSplit = commonDaoPW.isRepairWorkOrderSplit(orderId);
		 
		 if (isRepairOrderSplit){
			 returnValue = selectSerialOperationCountPW(orderId, operationKey);
		 }else{
			 returnValue = Super.selectSerialOperationCount(orderId, operationKey);
		 }
		 return returnValue;
    }
    	
    public int selectSerialOperationCountPW(String orderId, Number operationKey) {
    
        StringBuffer select = new StringBuffer().append("SELECT COUNT(?) ")
								                .append("FROM SFWID_SERIAL_OPER ")
								                .append("WHERE ORDER_ID = ? ")
								                .append("AND OPER_KEY = ? ")
								                .append("AND SERIAL_OPER_STATUS NOT IN (?, ?, ?) ");
        
        ParameterHolder params = new ParameterHolder();
		params.addParameter(X);
		params.addParameter(orderId);
		params.addParameter(operationKey);
		params.addParameter(PENDING);
		params.addParameter(IN_QUEUE);
		params.addParameter("CANCEL");
		return jdbcDaoSupport.queryForInt(select.toString(), params);
    
    }
}
