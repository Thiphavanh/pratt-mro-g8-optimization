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
*  File:    PlanMapperDaoImplPW.java
* 
*  Created: 2019-01-24
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2019-01-24 	XCS4331		    Initial Release XXXXXXX
* 2020-12-21    Fred Ettefagh   defect 1843 added code to select non obsolete task groups when sending our sync routing 
*/
package com.ibaset.solumina.planning.plan.integration.impl;

import java.util.List;
import java.util.Map;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;

public class PlanMapperDaoImplPW {
	
	@Reference
	private JdbcDaoSupport jdbcDaoSupport;
	
	
	@SuppressWarnings("rawtypes")
	public Map selectSfplPlanDescMap(String planId, Number planUpdtNo){
		
		 StringBuffer select = new StringBuffer().append(" SELECT T.* ")
				                                 .append(" FROM SFPL_PLAN_DESC T ")
				                                 .append(" WHERE T.PLAN_ID = ? ") 
				                                 .append(" AND T.PLAN_UPDT_NO = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		
		Map map = jdbcDaoSupport.queryForMap(select.toString(), params);
		
		
		return map;
		
	}
	
	
    @SuppressWarnings("rawtypes")
	public List selectSforSfplPlanSubject(String planId, Number planUpdtNo, String obsoleteFlag){
    	
    	StringBuffer select = new StringBuffer().append(" SELECT T.* " +
    			                                        " FROM SFOR_SFPL_PLAN_SUBJECT T " + 
    			                                        " WHERE T.PLAN_ID = ? " + 
    			                                        " AND T.PLAN_UPDT_NO = ? " +
    			                                        " AND NVL(T.OBSOLETE_RECORD_FLAG,'X') = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(obsoleteFlag);
		
		List list = jdbcDaoSupport.queryForList(select.toString(), params);
		//System.out.println("select.toString() = " + select.toString());
		//System.out.println("params = " + params);
		//System.out.println("list = " + list);
		
		return list;
    }
    
    

    @SuppressWarnings("rawtypes")
	public List selectSforSfplPlanSubjectPart(String planId, Number planUpdtNo, Number subjectNo){
    	
    	StringBuffer select = new StringBuffer().append(" SELECT (SELECT TT.PART_NO FROM SFPL_ITEM_DESC_MASTER_ALL TT WHERE TT.ITEM_ID = T.ITEM_ID) AS PART_NO, T.* " 
    	                                              + " FROM SFOR_SFPL_PLAN_SUBJECT_PART T WHERE T.PLAN_ID= ? AND T.PLAN_UPDT_NO= ? AND T.SUBJECT_NO = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(subjectNo);
		
		List list = jdbcDaoSupport.queryForList(select.toString(), params);
		
		return list;
    }

    
    

}
