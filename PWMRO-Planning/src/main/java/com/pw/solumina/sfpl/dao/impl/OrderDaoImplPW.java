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
*  File:    OrderDaoImplPW.java
*  
*  PW_SMRO_eWORK_202-Order-Creation
* 
*  Created: 2018.06.02
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date             Who            Description
*  -----------    ------------     ---------------------------------------------------
*  2018-03-08       Fred Ettefagh   overwrite oob method selectPlanEffectivityInformation to try to fix production error  
*                                   >>Scheduled Start Date is required and must be within the effectivity range(Msg ID: MFI_52561)<<
*  2019-04-19       Fred Ettefagh  added code for repair order create    
*  2019-04-19       Fred Ettefagh defect 1104, start and complete WO ALT when updating tasks after WO create.                                
*  
*/
package com.pw.solumina.sfpl.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;


public abstract class OrderDaoImplPW extends ImplementationOf<com.ibaset.solumina.sfpl.dao.IOrderDao> implements com.ibaset.solumina.sfpl.dao.IOrderDao{

	@Reference
	private JdbcDaoSupport jdbcDaoSupport;
	
	
	
	public void insertCopyOrderNode(String orderId,
								    String planId,
								    Number planVersion,
								    Number planRevision,
								    Number planAlterations,
								    List  operationInformationList) {
	
		// 5/18/2018 Fred E.
		// customized the insert sql below so that oper Key is not repeated.
		// this was causing timeout due to too many oper key.
		// most of the oper key are repeated.
		
		
		//Super.insertCopyOrderNode(orderId, planId, planVersion, planRevision, planAlterations, operationInformation);
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO SFWID_ORDER_NODE ( ")
												   .append("    ORDER_ID, ")
												   .append("    NODE_ID, ")
												   .append("    STATUS, ")
												   .append("    UPDT_USERID, ")
												   .append("    TIME_STAMP, ")
												   .append("    LAST_ACTION, ")
												   .append("    OPER_KEY, ")
												   .append("    STEP_KEY, ")
												   .append("    NODE_TYPE, ")
												   .append("    NODE_NO, ")
												   .append("    NODE_TITLE, ")
												   .append("    NODE_ROW, ")
												   .append("    NODE_COLUMN, ")
												   .append("    NODE_DESC, ")
												   .append("    DECISION_TYPE, ")
												   .append("    RETURN_TO_OPER_NO, ")
												   .append("    RETURN_TO_OPER_KEY ) ")
												   .append(" SELECT ")
												   .append("    ?, ")
												   .append("    NODE_ID, ")
												   .append("    CASE ")	//FND-22926 Insert status only for node type Operation
									               .append("    WHEN NODE_TYPE = ? THEN ? ")
									               .append("	ELSE NULL ")
									               .append("	END, ")
									               .append("    ?, ")
									               .append("    "  + jdbcDaoSupport.getTimestampFunction()  + ", ")
									               .append("    ?, ")
									               .append("    OPER_KEY, ")
									               .append("    ?, ")
									               .append("    NODE_TYPE, ")
									               .append("    NODE_NO, ")
												   .append("    NODE_TITLE, ")
												   .append("    NODE_ROW, ")
												   .append("    NODE_COLUMN, ")
												   .append("    NODE_DESC, ")
												   .append("    DECISION_TYPE, ")
												   .append("    RETURN_TO_OPER_NO, ")
												   .append("    RETURN_TO_OPER_KEY ")
												   .append(" FROM  SFPL_PLAN_NODE_REV_V ")
												   .append(" WHERE  PLAN_ID = ? ")
												   .append(" AND    PLAN_VERSION = ? ")
									               .append(" AND    PLAN_REVISION = ? ")
									               .append(" AND    PLAN_ALTERATIONS = ?")
									               .append(" AND    OPER_KEY IN ( ");

		Set<Number> operationInformationSet = new HashSet<Number>();
		ListIterator operationIt = operationInformationList.listIterator();
		while (operationIt.hasNext())
		{
				Map operationKeys = (Map) operationIt.next();
				Number operOperationKey = (Number) operationKeys.get("OPER_KEY");

				operationInformationSet.add(operOperationKey);
			}

			int i=0;
			for (Number operKey :operationInformationSet ){
				insertSql.append( operKey );
				i++;
				if (operationInformationSet.size()!=i){
					insertSql.append( "," );
				}
		}
		insertSql.append( ")" );

		/*
			ListIterator operationIt = operationInformationList.listIterator();
			while (operationIt.hasNext())
			{
				Map operationKeys = (Map) operationIt.next();
				Number operOperationKey = (Number) operationKeys.get("OPER_KEY");


				insertSql.append(operOperationKey);
				if (operationIt.hasNext())
				{
					insertSql.append(", ");
				}
			}
		*/

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(SoluminaConstants.OPERATION);
		params.addParameter(SoluminaConstants.PENDING);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(SoluminaConstants.INSERTED);
		params.addParameter(-1);
		params.addParameter(planId);
		params.addParameter(planVersion);
		params.addParameter(planRevision);
		params.addParameter(planAlterations);
		jdbcDaoSupport.insert(insertSql.toString(), params);
		
	}
	
    public Map selectPlanEffectivityInformation(String planId,
									            String effectivityType,
									            Date scheduledStartDate){
    	
		StringBuffer selectSql = new StringBuffer().append("SELECT TO_CHAR(SFMFG.SFDB_SYSDATE(), 'MM/DD/YYYY') AS DB_SYSDATE FROM DUAL ");
		String todaysDate =  (String) jdbcDaoSupport.queryForObject(selectSql.toString(), String.class);
		
		StringBuffer select = new StringBuffer().append("SELECT ")
									            .append("    PLAN_VERSION, ")
									            .append("    PLAN_REVISION, ")
									            .append("    PLAN_ALTERATIONS ")
									            .append("FROM SFPL_PLAN_EFF ")
									            .append("WHERE PLAN_ID = ? ")
									            .append("AND EFF_TYPE = ? ")
									            .append("AND  TO_DATE( ? ,'MM/DD/YYYY') > = EFF_FROM_DATE ")
									            .append("AND  TO_DATE( ? ,'MM/DD/YYYY') < = CASE WHEN EFF_THRU_DATE IS NULL THEN TO_DATE( ? ,'MM/DD/YYYY') ELSE EFF_THRU_DATE END");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(effectivityType);
		params.addParameter(todaysDate);
		params.addParameter(todaysDate);
		params.addParameter("12/31/9990");
		List list = jdbcDaoSupport.queryForList(select.toString(), params);
		ListIterator it = list.listIterator();
		Map map = new HashMap();
	
		if (it.hasNext())
		{
			map = (Map) it.next();
		}
	
		return map;
    }
}
