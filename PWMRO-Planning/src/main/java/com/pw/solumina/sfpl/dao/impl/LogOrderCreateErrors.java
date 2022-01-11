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
*  File:    LogOrderCreateErrors.java
*  
*  PW_SMRO_eWORK_202-Order-Creation
* 
*  Created: 2018.05.22
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date             Who            Description
*  -----------    ------------     ---------------------------------------------------
*  2018-05-22       Fred Ettefagh  
*  2018-06-06       Fred Ettefagh  Changed code for defect 820, update tasks based on each IM data row
*  2019-05-03       Fred Ettefagh  Added code to handle repair order create errors.
*  2019-06-05       F. Ettefagh    changed method insertIntoErrorTable for Repair shop order split 
*  2019-11-05       F. Ettefagh    changed sql for handling repair order errors 
*/
package com.pw.solumina.sfpl.dao.impl;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;

public class LogOrderCreateErrors {
	
	@Reference private JdbcDaoSupport jdbcDaoSupport;
	
	public void insertIntoErrorTable(String errerMsg,
						             String orderNumber,
									 String partNo,
									 String planId,
									 Number planVer,
									 Number planRev,
									 Number planAlt,
									 Number planUpdtNo,
									 String salesOrderNumber,
									 String engineType,
									 String engineModel,
									 String sapWorkLoc,
									 String workScope,
									 String superiorNet,
									 String subNet,
									 String customer,
									 String calledFrom){

		if (StringUtils.equalsIgnoreCase(calledFrom,"Multiple Repair Order Create") ||
			StringUtils.equalsIgnoreCase(calledFrom,"Single Repair Order Create")){
				
						StringBuilder updateSql = new StringBuilder().append("UPDATE PWUST_INT_SAP_SM_ORDER T SET T.SOL_ERROR_TEXT = ?  WHERE T.IDOC_NUMBER = ? AND T.SM_ORDER = ? ");
						ParameterHolder parameters = new ParameterHolder();
						
						if (errerMsg!=null && errerMsg.length() >4000){
							errerMsg = errerMsg.substring(1, 3999);
						}
						parameters.addParameter(errerMsg);
						parameters.addParameter(customer);
						parameters.addParameter(orderNumber);

						jdbcDaoSupport.update(updateSql.toString(), parameters);				
				
			}else{
						StringBuffer insert = new StringBuffer(" INSERT INTO PWUST_OVERHAUL_ORDER_ERRORS( UPDT_USERID, " +
																										" ERROR_MSG, " +
																										" ORDER_NO, " +
																										" PART_NO, " +
																										" PLAN_ID, " + 
																										" PLAN_VER, " +
																										" PLAN_REV, " +
																										" PLAN_ALT, " +
																										" PLAN_UPDT_NO, " +
																										" SALES_ORDER, " +
																										" ENGINE_TYPE, " +
																										" ENGINE_MODEL, " +
																										" WORK_LOC, " +
																										" WORK_SCOPE, " +
																										" SUPERIOR_NETWORK_ACT, " +
																										" SUB_NETWORK_ACT, " +
																										" CUSTOMER,  " +
																										" CALLED_FROM ) " + 
																										" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " );
			
					ParameterHolder params = new ParameterHolder();
					params.addParameter(ContextUtil.getUser().getUsername());
					
					if (StringUtils.isNotBlank(errerMsg) && errerMsg.length() >4000 ){
						errerMsg =  errerMsg.substring(1, 3999);
					}
					params.addParameter(errerMsg);
			
					if (StringUtils.isNotBlank(orderNumber) && orderNumber.length() >2000 ){
						orderNumber =  orderNumber.substring(1, 1999);
					}
					params.addParameter(orderNumber);
			
					params.addParameter(partNo);
					params.addParameter(planId);
					params.addParameter(planVer);
					params.addParameter(planRev);
					params.addParameter(planAlt);
					params.addParameter(planUpdtNo);
					params.addParameter(salesOrderNumber);
					params.addParameter(engineType);
					params.addParameter(engineModel);
					params.addParameter(sapWorkLoc);
					
					if (StringUtils.isNotBlank(workScope) && workScope.length() >2000 ){
						workScope =  workScope.substring(1, 1999);
					}
					params.addParameter(workScope);
					
					if (StringUtils.isNotBlank(superiorNet) && superiorNet.length() >2000 ){
						superiorNet =  superiorNet.substring(1, 1999);
					}
					params.addParameter(superiorNet);
					
					
					if (StringUtils.isNotBlank(subNet) && subNet.length() >2000 ){
						subNet =  subNet.substring(1, 1999);
					}
					params.addParameter(subNet);
					
					
					params.addParameter(customer);
					
					if (StringUtils.isNotBlank(calledFrom) && calledFrom.length() >20 ){
						calledFrom = calledFrom.substring(1,19);
					}
					params.addParameter(calledFrom);
					
					jdbcDaoSupport.insert(insert.toString(), params);
			}
	}	

}
