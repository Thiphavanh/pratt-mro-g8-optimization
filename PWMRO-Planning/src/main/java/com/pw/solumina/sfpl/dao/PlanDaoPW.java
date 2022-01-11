/*
* This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017,2020.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    PlanDaoPW.java
* 
*  Created: 2017-08-11
* 
*  Author:  c079222
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-08-11    D.Miron		    Initial Release XXXXXXX
* 2017-11-05    D.Miron         SMRO_APP_203    Updated for email notifications. Added plan_title to getPlanInfoForEmail select.
* 2018-03-29    xcs5279         Defect 528 - Create an error when all operations do not have work dept/centers
* 2018-09-26    R.Thorpe        SMRO_APP_301 -  Add method getPlanType
* 2018-12-17	B. Preston		SMRO_PLG_301:  Added selectEngineModelTabList() and selectEngineModelSel().
* 2019-07-10    D.Miron         Defect 1356 - Added method updatePlanPPVQuanity.
* 2019-08-30	German Rodriguez defect 1317  - Security groups do not populate properly when copying between sites
* 2020-04-03    Fred Ettefagh   SMRO_PLG_305, Defect 1616, added method selectPlanPW
* 2020-09-01	Scott Edgerly	Defect 1756: Auto-fill location, department, and center on ops when copying plans
* 2020-09-21	Scott Edgerly	Defect 1785: Java Error Creating Standard Operation
* 2020-09-23	Scott Edgelry 	Defect 1786: Java Error when creating Repair or Overhaul Process Plans
* 2020-10-29    D.Miron         Defect 1808 - Added updateBuildPartSecurityGroup
*/
package com.pw.solumina.sfpl.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import static com.ibaset.common.FrameworkConstants.UPDATED;
import static com.ibaset.common.security.context.ContextUtil.getUsername;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.solumina.sfcore.application.IUser;

/**
 * @author c079222
 *
 */


public class PlanDaoPW {
	
	@Reference
	private JdbcDaoSupport jdbcDao;
	
	@Reference	
	private IUser user = null;
	
	/** 
	 * SAEP_APP_01 - Aug 14, 2017
	 * 
	 * @author c079222 - David Miron
	 *
	 * @param planID
	 * @param planUpdtNo
	 * @param changeNotes
	 * @param notificationMatrix
	 * @return
	 */
	
	
	public Map selectPlanPW(String planId,
			                Number planVersion,
			                Number planRevision,
			                Number planAlterations){
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT T.* ")
		                                           .append(" FROM SFPL_PLAN_V T ")
		                                           .append(" WHERE T.PLAN_ID = ? ")
		                                           .append(" AND T.PLAN_VERSION = ? ")
		                                           .append(" AND T.PLAN_REVISION = ? ")
		                                           .append(" AND T.PLAN_ALTERATIONS = ? ");
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(planId);
		parameters.addParameter(planVersion);
		parameters.addParameter(planRevision);
		parameters.addParameter(planAlterations);
		
		return jdbcDao.queryForMap(selectSql.toString(), parameters);
	}
	
	
	public int updateChangeNotes(String planID, 
			                     Number planUpdtNo, 
			                     String changeNotes, 
			                     String notificationMatrix)
    {
		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFPL_PLAN_DESC")
				                                   .append(" SET UCF_PLAN_VCH4000_1 = ?, ")
				                                   .append("     UCF_PLAN_VCH255_1 = ? ")
				                                   .append(" WHERE PLAN_ID = ? ")
				                                   .append("   AND PLAN_UPDT_NO = ?");
    	
		ParameterHolder params = new ParameterHolder();
		params.addParameter(changeNotes);
		params.addParameter(notificationMatrix);
		params.addParameter(planID);
		params.addParameter(planUpdtNo);
    		
    	return jdbcDao.update(updateSql.toString(),params);
    }
    
    /** 
     * SAEP_APP_01 - Aug 15, 2017
     * 
     * @author c079222 - David Miron
     *
     * @param workLoc
     * @param orClause
     * @param whereClause
     * @return
     */
    public List getApprovalUsers(String workLoc, String orClause, String whereClause)  
	{  
		
		List listA = null;
		StringBuffer selectSql = new StringBuffer().append("SELECT DISTINCT ")
								                   .append("  t.priv, u.full_name, u.userid, (U.USERID)  as NOTIFICATION_MATRIX, null as SECURITY_GROUP") 
								                   .append(" from sfcore_user_privs t, sffnd_user u ")
								                   .append(" where t.userid = u.userid ")
								                   .append(" and u.email_address is not null ")
								                   .append(" and u.full_name is not null ")
								                   .append(" and t.priv in ("+orClause+")");

		ParameterHolder parameters = new ParameterHolder();

 		if(!StringUtils.isEmpty(whereClause))
        {
            selectSql.append(" AND " + whereClause.replace("NOTIFICATION_MATRIX=","(U.USERID)="));   ///SAEP_APP_01
        }

		selectSql.append(" order by t.priv, u.full_name"); 
		
		listA = jdbcDao.queryForList(selectSql.toString(), parameters);
		
		List listB = null;
		selectSql = new StringBuffer().append("SELECT DISTINCT ")
								      .append("       T.PRIV, U.FULL_NAME, U.USERID, (U.USERID) AS NOTIFICATION_MATRIX, NULL AS SECURITY_GROUP ") 
								      .append("  FROM SFCORE_USER_PRIVS T, SFFND_USER U, SFFND_WORK_LOC_DEF L ")
								      .append(" WHERE T.USERID = U.USERID ")
								      .append("   AND U.EMAIL_ADDRESS IS NOT NULL ")
								      .append("   AND U.FULL_NAME IS NOT NULL ")
								      .append("   AND U.LOCATION_ID = L.LOCATION_ID ")
								      .append("   AND T.PRIV IN ('ME_APPROVAL', 'QA_APPROVAL') ")
								      .append("   AND L.WORK_LOC = ? ")
								      .append(" ORDER BY T.PRIV, U.FULL_NAME ");

		parameters = new ParameterHolder();
    	parameters.addParameter(workLoc);
 			
		listB = jdbcDao.queryForList(selectSql.toString(), parameters);
		
        listA.addAll(listB);
		return listA;
	}
    
    /** 
     * SAEP_APP_01 - Aug 14, 2017
     * 
     * @author c079222 - David Miron
     *
     * @param planID
     * @param planUpdtNo
     * @return
     */
    public String getPlantForPlan(String planID, Number planUpdtNo)
    {  
    	String workLoc = null;
    	
    	StringBuffer selectSql = new StringBuffer().append("SELECT WORK_LOC ")
												   .append("  FROM SFPL_PLAN_DESC A, SFFND_WORK_LOC_DEF B")
										           .append(" WHERE A.PLND_LOCATION_ID = B.LOCATION_ID ")
										           .append("   AND PLAN_ID = ? ")
												   .append("   AND PLAN_UPDT_NO = ? ");

    	ParameterHolder parameters = new ParameterHolder();
    	parameters.addParameter(planID);
    	parameters.addParameter(planUpdtNo);

    	List list = jdbcDao.queryForList(selectSql.toString(), parameters);
    	if(list.size() > 0) 
    	{
    		Map map = (Map) list.get(0);
    		workLoc = (String) map.get("WORK_LOC");
    	}

    	return workLoc;
    }
    
    
    
        /** 
         * SAEP_APP_01 - Sep 26, 2017
         * 
         * @author c079222 - David Miron
         *
         * @param planId
         * @param planVersion
         * @param planRevision
         * @param planAlterations
         * @return
         */
        public boolean userHasExportBypass(String planId, Number planVersion, Number planRevision, Number planAlterations)
        {
        	boolean hasExptPriv = false;
        	
        	StringBuffer selectSql = new StringBuffer().append(" SELECT 'X' ")
				                                       .append("   FROM SFPL_PLAN_PWP_XREF A, SFPL_PWP_DESC B ")
				                                       .append("  WHERE A.PWP_ID = B.PWP_ID ")
				                                       .append("    AND B.PWP_NOTE LIKE 'BYPASS%' ")
				                                       .append("    AND A.PLAN_ID = ? ")
				                                       .append("    AND A.PLAN_VERSION = ? ")
				                                       .append("    AND A.PLAN_REVISION = ? ")
				                                       .append("    AND A.PLAN_ALTERATIONS = ? ");
        	
        	ParameterHolder parameters = new ParameterHolder();
        	parameters.addParameter(planId);
        	parameters.addParameter(planVersion);
        	parameters.addParameter(planRevision);
        	parameters.addParameter(planAlterations);
        	
        	List list = jdbcDao.queryForList(selectSql.toString(), parameters);
        	
        	if(list.size() > 0 && user.hasPrivilege("DEV_EX"))
        	{
        		hasExptPriv = true;
        	}
        	
        	return hasExptPriv;
        } 

    	/** 
    	 * SAEP_APP_01 - Sep 27, 2017
    	 * 
    	 * @author c079222 - David Miron
    	 *
    	 * @param taskId
    	 * @return
    	 */
    	public Map getEmailFromTask(String taskId) 
    	{
    		//2014-07-16 RTT Add PLAN_REVISION to select
    		StringBuffer sql = new StringBuffer().append("SELECT ")
    											 .append("  PLAN_ID, ")
    											 .append("  PLAN_UPDT_NO, ")
    											 .append("  PLAN_VERSION, ")
    											 .append("  PLAN_REVISION, ")
    											 .append("  PLAN_ALTERATIONS, ")
    											 .append("  ITEM_SUBTYPE, ")
    											 .append("  PART_NO ")
    											 .append("FROM SFFND_PLG_TASK ")
    											 .append("WHERE TASK_ID = ? ");
    		
    		ParameterHolder params = new ParameterHolder();
    			params.addParameter(taskId);
    		
    		return jdbcDao.queryForMap(sql.toString(), params);	
    	}
    	
        /** 
         * SAEP_APP_01 - Sep 27, 2017
         * 
         * @author c079222 - David Miron
         *
         * @param planId
         * @param planVersion
         * @param planRevision
         * @param planAlterations
         * @return
         */
        public Map getPlanInfoForEmail(String planId, Number planVersion, Number planRevision, Number planAlterations)
    	{  
    		StringBuffer selectSQL = new StringBuffer().append("SELECT DISTINCT ")
    				                                   .append("  A.PLAN_TYPE, ")
    				                                   .append("  A.PLAN_NO, ")
    				                                   .append("  A.PART_NO, ")
    				                                   .append("  A.MFG_BOM_CHG, ")
    				                                   .append("  C.PWP_ID, ")
    				                                   .append("  A.PROGRAM, ")
    				                                   .append("  A.UCF_PLAN_VCH5, ")
    				                                   .append("  A.UCF_PLAN_VCH6, ")
    				                                   .append("  A.UCF_PLAN_VCH4000_1, ")
    				                                   .append("  A.PLAN_TITLE ")
    				                                   .append("FROM SFPL_PLAN_DESC A, SFPL_PLAN_REV B, SFPL_PLAN_PWP_XREF C ")
    				                                   .append("WHERE A.PLAN_ID = B.PLAN_ID ")
    				                                   .append("  AND A.PLAN_UPDT_NO = B.PLAN_UPDT_NO ")
    				                                   .append("  AND C.PLAN_ID = B.PLAN_ID ")
    				                                   .append("  AND C.PLAN_VERSION = B.PLAN_VERSION ")
    				                                   .append("  AND C.PLAN_REVISION = B.PLAN_REVISION ")
    				                                   .append("  AND C.PLAN_ALTERATIONS = B.PLAN_ALTERATIONS ")
    				                                   .append("  AND b.plan_id = ?")
    				                                   .append("  AND b.plan_version = ?")
    				                                   .append("  AND b.plan_revision = ?")
    				                                   .append("  AND b.plan_alterations = ?");
    				
    		
    		ParameterHolder params = new ParameterHolder();
    		params.addParameter(planId);
    		params.addParameter(planVersion);
    		params.addParameter(planRevision);
    		params.addParameter(planAlterations);


    		Map planMap = jdbcDao.queryForMap(selectSQL.toString(), params);
    		return planMap;
    	} 
        
        /**OK 
         * SAEP_APP_01 - Sep 7, 2017
         * 
         * @author c079222 - David Miron
         *
         * @param planID
         * @param planUpdtNo
         * @return
         */
        public Map getPlanInfoForRTC(String planID, Number planUpdtNo)
        {  
        	StringBuffer selectSql = new StringBuffer().append(" SELECT A.PART_NO, ")
    											       .append("        A.PLAN_TITLE, ")
    											       .append("        A.PLAN_NO, ")
    											       .append("        B.UCF_WORK_LOC_VCH1 AS BUSINESS_UNIT, ")
    											       .append("        B.UCF_WORK_LOC_VCH2 AS FUNCTIONAL_AREA, ")
    											       .append("        B.WORK_LOC")
    											       .append("   FROM SFPL_PLAN_DESC A, SFFND_WORK_LOC_DEF B")
    											       .append("  WHERE A.PLND_LOCATION_ID = B.LOCATION_ID ")
    											       .append("    AND A.PLAN_ID = ? ")
    											       .append("    AND A.PLAN_UPDT_NO = ? ");

        	ParameterHolder parameters = new ParameterHolder();
        	parameters.addParameter(planID);
        	parameters.addParameter(planUpdtNo);

        	Map returnMap = jdbcDao.queryForMap(selectSql.toString(), parameters);

        	return returnMap;
        }  
	
	/**OK 
	 * SAEP_APP_01 - Sep 28, 2017
	 * 
	 * @author c079222 - David Miron
	 *
	 * @param planId
	 * @param planUpdtNo
	 * @param jurisdiction
	 * @param classification
	 * @param sme
	 * @param recNo
	 * @return
	 */
	public int updateJCInfo(String planId, Number planUpdtNo, String jurisdiction,
    		                String classification, String sme, int recNo) 
	{		
		StringBuffer update = new StringBuffer().append(" UPDATE ")
		.append(" SFPL_PLAN_DESC ")
		.append(" SET ")
		.append("    UPDT_USERID = ?, ") 
		.append("    TIME_STAMP = " + jdbcDao.getTimestampFunction() + ", ") 
		.append("    LAST_ACTION = ?, ") 
		.append("    COMMODITY_JURISDICTION = ?, ")
		.append("    COMMODITY_CLASSIFICATION = ?, ")
		.append("    UCF_PLAN_FLAG1 = ?, ")  // sme
		.append("    UCF_PLAN_NUM3 = ? ")    // rec no
		.append("  WHERE PLAN_ID = ?")      
		.append("   AND PLAN_UPDT_NO = ?");
		
					
		ParameterHolder params = new ParameterHolder();
		//Set
		params.addParameter(ContextUtil.getUsername());   // updt_userid
		params.addParameter(SoluminaConstants.UPDATED);   // last action
		params.addParameter(jurisdiction);     
		params.addParameter(classification);      
		params.addParameter(sme); 
		params.addParameter(recNo); 
		params.addParameter(planId);       
		params.addParameter(planUpdtNo);       
		
		int rowCount = jdbcDao.update(update.toString(), params);
		return rowCount;	
	}

	public int updateRecNoFromFastPath(String planId, Number planUpdtNo, int recNo) 
	{		
		StringBuffer update = new StringBuffer().append(" UPDATE ")
		.append(" SFPL_PLAN_DESC ")
		.append(" SET ")
		.append("    UPDT_USERID = ?, ") 
		.append("    TIME_STAMP = " + jdbcDao.getTimestampFunction() + ", ") 
		.append("    LAST_ACTION = ?, ") 
		.append("    UCF_PLAN_NUM3 = ? ")   // rec no
		.append("  WHERE PLAN_ID = ?")      
		.append("   AND PLAN_UPDT_NO = ?");
		
					
		ParameterHolder params = new ParameterHolder();
		//Set
		params.addParameter(ContextUtil.getUsername());   // updt_userid
		params.addParameter(SoluminaConstants.UPDATED);   // last action
		params.addParameter(recNo); 
		params.addParameter(planId);       
		params.addParameter(planUpdtNo);       
		
		int rowCount = jdbcDao.update(update.toString(), params);
		return rowCount;	
	}

	 /**OK 
	 * SAEP_APP_01 - Sep 28, 2017
	 * 
	 * @author c079222 - David Miron
	 *
	 * @param planId
	 * @param planVersion
	 * @param planRevision
	 * @param planAlterations
	 * @return
	 */
	public List selectAllPriorPlanApprovals(String planId,
	            Number planVersion,
	            Number planRevision,
	            Number planAlterations)
			{  ///SAEP_APP_01 - Return list of userids and queues for prior plan approvals.
			
				List list = null;
				
				StringBuffer selectSql = new StringBuffer().append("select d.queue_type, d.updt_userid ")
				.append("from SFFND_PLG_TASK c, sffnd_task d ")
				.append("where c.task_id = d.task_id ")
				.append("and c.plan_id = ? ") 
				.append("and c.plan_version = ? ")
				.append("and c.plan_revision = ? ")
				.append("and c.plan_alterations = ? ")
				.append("and d.queue_type in ('PLG_AUTHORING','ME_APPROVAL','QA_APPROVAL') ")
				.append("and d.status IN ('ACCEPT', 'COMPLETE') ")
				.append("and d.time_stamp > ")
				.append("(select e.time_stamp ")
				.append("from (select b.status, b.queue_type, b.time_stamp ")
				.append("from SFFND_PLG_TASK a, sffnd_task b ")
				.append("where a.task_id = b.task_id ")
				.append("and a.plan_id = ? ") 
				.append("and a.plan_version = ? ")
				.append("and a.plan_revision = ? ")
				.append("and a.plan_alterations = ? ")
				.append("and (b.status = 'COMPLETE' and ")
				.append("b.queue_type = 'PLG_AUTHORING') ")
				.append("order by b.time_stamp desc) e ")
				.append("where rownum = 1) ");
				
				ParameterHolder parameters = new ParameterHolder();
				parameters.addParameter(planId);
				parameters.addParameter(planVersion);
				parameters.addParameter(planRevision);
				parameters.addParameter(planAlterations);
					
				parameters.addParameter(planId);
				parameters.addParameter(planVersion);
				parameters.addParameter(planRevision);
				parameters.addParameter(planAlterations);
				
				list = jdbcDao.queryForList(selectSql.toString(), parameters);
				
				return list;
			}
	
	/**OK 
	 * SAEP_APP_01 - Sep 29, 2017
	 * 
	 * @author c079222 - David Miron
	 *
	 * @param planId
	 * @param planUpdtNo
	 * @return
	 */
	public Map getPlanInfoForUpdateNo(String planId, Number planUpdtNo) 
	{
		StringBuffer selectSQL = new StringBuffer().append("SELECT t.part_no, ")
				                                   .append("       t.item_type, ")
				                                   .append("       t.item_subtype, ")
				                                   .append("       t.part_chg, ")
				                                   .append("       t.program   ")
				                                   .append("  from sfpl_plan_desc t ")
				                                   .append(" WHERE t.PLAN_ID = ? ")
				                                   .append("   AND t.PLAN_UPDT_NO = ?");
		
		ParameterHolder params = new ParameterHolder();
			params.addParameter(planId);
			params.addParameter(planUpdtNo);
			
			
		Map planMap = jdbcDao.queryForMap(selectSQL.toString(), params);
		return planMap;
	}
	
    /**OK 
     * SAEP_APP_01 - Sep 29, 2017
     * 
     * @author c079222 - David Miron
     *
     * @param planID
     * @param planUpdtNo
     * @return
     */
    public int selectPlanRevision(String planID, Number planUpdtNo)
    {
     	StringBuffer selectSql = new StringBuffer().append("SELECT MAX(PLAN_REVISION) ")
				                                   .append("  FROM SFPL_PLAN_REV ")
				                                   .append(" WHERE PLAN_ID = ? ")
				                                   .append("   AND PLAN_UPDT_NO = ? ");

     	ParameterHolder parameters = new ParameterHolder();
     	parameters.addParameter(planID);
     	parameters.addParameter(planUpdtNo);

     	int planRevision = jdbcDao.queryForInt(selectSql.toString(), parameters);
     	return planRevision;
    }

    
    //defect 1736, 1786
    public Map selectPlanWorkLocationData(String planID, Number planVersion, Number planRevision, Number planAlterations)
    {
     	StringBuffer selectSql = new StringBuffer().append("SELECT * ")
				                                   .append("  FROM SFFND_WORK_LOC_DEF ")
				                                   .append(" WHERE LOCATION_ID = ")
				                                   .append("   (SELECT PLND_LOCATION_ID ")
     											   .append("   		FROM SFPL_PLAN_V ")
     											   .append("     WHERE PLAN_ID =          ? ")
     											   .append("       AND PLAN_VERSION =     ? ")
     											   .append("       AND PLAN_REVISION =    ? ")
     											   .append("       AND PLAN_ALTERATIONS = ?)");




     	ParameterHolder parameters = new ParameterHolder();
     	parameters.addParameter(planID);
     	parameters.addParameter(planVersion);
     	parameters.addParameter(planRevision);
     	parameters.addParameter(planAlterations);

     	return jdbcDao.queryForMap(selectSql.toString(), parameters); //defect 1786
    }

    
    //Defect 528
    public String getOperationsMissingWorkCenterOrWorkDept(String planId, Number planVersion, Number planRevision, Number planAlterations)
    {
    	StringBuffer selectSql = new StringBuffer().append(" SELECT a.oper_no ")
			                                       .append("   from SFPL_OPERATION_DESC a, SFPL_OPERATION_REV b ")
			                                       .append("  WHERE a.plan_id = ? ")
			                                       .append("    AND b.plan_version = ? ")
			                                       .append("    AND b.plan_revision = ? ")
			                                       .append("    AND b.plan_alterations = ? ")
			                                       .append("    AND a.plan_id = b.plan_id ")
			                                       .append("    AND a.oper_key = b.oper_key ")
			                                       .append("    AND a.oper_updt_no = b.oper_updt_no ")
			                                       .append("    AND plnd_center_id is null ")
			                                       .append("  order by a.oper_no ")
			                                       ;
    	
    	ParameterHolder parameters = new ParameterHolder();
    	parameters.addParameter(planId);
    	parameters.addParameter(planVersion);
    	parameters.addParameter(planRevision);
    	parameters.addParameter(planAlterations);
    	
		List<Map<String, String>> result = jdbcDao.queryForList(selectSql.toString(), parameters);
		String operations = "";
		for(Map<String, String> row : result){
			operations = operations + "\nOperation " + row.get("OPER_NO");
		}
    	
    	return operations;
    } 

    
    //defect 1756
    public List<Map<String, Object>> getCopiedPlanOperations(String planId){


    	StringBuffer selectSql = new StringBuffer().append(" SELECT PLAN_ID, ")
									                .append(" 		OPER_KEY,")
									                .append("  		PLND_LOCATION_ID, ")
									                .append("  		 (SELECT WORK_LOC FROM SFFND_WORK_LOC_DEF B WHERE B.LOCATION_ID = A.PLND_LOCATION_ID) AS WORK_LOC, ")
									                .append("   		 PLND_DEPARTMENT_ID, ")
									                .append("    		 (SELECT C.WORK_DEPT FROM SFFND_WORK_DEPT_DEF C WHERE C.DEPARTMENT_ID = A.PLND_DEPARTMENT_ID) AS DEPT, ")
									                .append("   		 PLND_CENTER_ID, ")
									                .append("   		 (SELECT D.WORK_CENTER FROM SFFND_WORK_CENTER_DEF D WHERE D.CENTER_ID = A.PLND_CENTER_ID) AS WORK_CNTR ")
									                .append("    FROM SFPL_OPERATION_DESC A")
									                .append("    WHERE PLAN_ID = ? ")
									                .append("    AND OPER_UPDT_NO = 1 ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(planId);
		
		List<Map<String, Object>> result = jdbcDao.queryForList(selectSql.toString(), parameters);
		
		return result;
    	
    }
    
    //Get Plan Type
    public String getPlanType(String planId)
    {
    	String planType = null;
    	
    	StringBuffer selectSql = new StringBuffer().append("SELECT DISTINCT (UPPER(PLAN_TYPE))")
			                                       .append("  FROM SFPL_PLAN_DESC ")
			                                       .append(" WHERE PLAN_ID = ? ")                ;
    	
    	ParameterHolder parameters = new ParameterHolder();
    	parameters.addParameter(planId);
    	
    	planType = jdbcDao.queryForString(selectSql.toString(), parameters);
	
    	
    	return planType;
    }
    
	//defect 1756, 1785
    public Map getWorkLocationData(String workLocation){
    	Map planType = null;

    	StringBuffer selectSql = new StringBuffer().append("SELECT LOCATION_ID ")
			                                       .append("  FROM SFFND_WORK_LOC_DEF ")
			                                       .append(" WHERE WORK_LOC = ? ")                ;
    	
    	ParameterHolder parameters = new ParameterHolder();
    	parameters.addParameter(workLocation);
    	planType = jdbcDao.queryForMap(selectSql.toString(), parameters);
	
    	
    	return planType;
    }
	
    //defect 1756
    public Map<String, String> getWorkCenterKey(String workLocationId, String workCenter){
    	Map<String, String> result;
    	StringBuffer selectSql = new StringBuffer().append("SELECT LOCATION_ID, ")
    											   .append("	   DEPARTMENT_ID, ")
    											   .append("	   CENTER_ID ")
			                                       .append("  FROM SFFND_WORK_CENTER_DEF ")
			                                       .append(" WHERE LOCATION_ID = ? ")  
			                                       .append(" AND WORK_CENTER = ? ");
    	
    	ParameterHolder parameters = new ParameterHolder();
    	parameters.addParameter(workLocationId);
    	parameters.addParameter(workCenter);

    	try{
    		result = jdbcDao.queryForMap(selectSql.toString(), parameters);
    	}
    	catch(Exception e){
    		result = null;
    	}

    	return result;
    }
    
    //defect 1756
    
    public void updateOperationWorkLocation(String planId, String locationId, String deptId, String workCenterId, BigDecimal operKey){
    	StringBuffer updateSql = new StringBuffer().append(" UPDATE SFPL_OPERATION_DESC")
				   								   .append(" 	SET PLND_LOCATION_ID   = ?, ")
				   								   .append(" 	    PLND_DEPARTMENT_ID = ?, ")
				   								   .append("        PLND_CENTER_ID     = ?")
				   								   .append(" WHERE PLAN_ID =     ? ")
				   								   .append("  AND OPER_KEY =     ? ")
				   							       .append("  AND OPER_UPDT_NO = 1 ");
    	
    	ParameterHolder parameters = new ParameterHolder();
    	parameters.addParameter(locationId);
    	parameters.addParameter(deptId);
    	parameters.addParameter(workCenterId);
    	parameters.addParameter(planId);
    	parameters.addParameter(operKey);
    	
    	jdbcDao.update(updateSql.toString(),parameters);

    }
    
	// Begin SMRO_PLG_301
	public List selectEngineModelTabList(String planId,
			Number planVersion, 
			Number planRevison,
			String planAlterations,
      String program)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT T.ENGINE_MODEL, ")
        .append(" T2.PROGRAM_MODEL_DESC, ")
        .append(" T.UPDT_USERID, ")
        .append(" T.TIME_STAMP ")
		.append(" FROM PWUST_PLAN_ENGINE_MODEL T, ")
        .append("  SFPL_PLAN_REV               T1,")
        .append("  PWUST_ENGINE_TYPE_MODEL_DEF T2 ")
		.append("  WHERE  T.PLAN_ID = T1.PLAN_ID ")
        .append(" AND T.PLAN_UPDT_NO = T1.PLAN_UPDT_NO ")
        .append(" AND T.ENGINE_MODEL = T2.MODEL ")
        .append(" AND T1.PLAN_ID = ? ")
        .append(" AND T1.PLAN_VERSION = ? ")
        .append(" AND T1.PLAN_REVISION = ? ")
		.append("    AND PLAN_ALTERATIONS = ? ");
		
        if(program != null)
        {
               selectSql.append("   AND T2.MODEL = ? ");
        }

        selectSql.append("    ORDER BY T.ENGINE_MODEL"); 
        
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(planId);
		parameters.addParameter(planVersion);
		parameters.addParameter(planRevison);
    parameters.addParameter(planAlterations);
    
    if(program != null)
    {
     parameters.addParameter(program);  
    }
		return jdbcDao.queryForList(selectSql.toString(), parameters);
	}
	
	
	  public List selectEngineModelSel(String program, String planId, Number planUpdtNo)
	  {
	      StringBuffer selectSql = new StringBuffer();
	      if(program != null)
	      {
	        selectSql = new StringBuffer().append(" SELECT T.MODEL AS ENGINE_MODEL,")
	                                      .append("        T.PROGRAM_MODEL_DESC, ") //AS PROGRAM_MODEL_DESC,")
	                                      .append("        T.UPDT_USERID,")
	                                      .append("        T.TIME_STAMP")
	                                      .append("   FROM PWUST_ENGINE_TYPE_MODEL_DEF T ") //SFFND_MODEL_DEF T")
	                                      .append("  WHERE T.ENGINE_TYPE = ? ") //PROGRAM = ?")
	                                      .append("    AND UPPER(T.MODEL) NOT IN ")
	                                      .append("( SELECT UPPER(TT.ENGINE_MODEL) ")
	                                      .append("    FROM PWUST_PLAN_ENGINE_MODEL TT")
	                                      .append("   WHERE TT.PLAN_ID = ?")
	                                      .append("     AND TT.PLAN_UPDT_NO = ? ) ") 
	                                      .append("ORDER BY ENGINE_MODEL");
	      }
	       else
	      {     

	        selectSql = new StringBuffer().append(" SELECT T.MODEL AS ENGINE_MODEL,")
	                                      .append("        T.PROGRAM_MODEL_DESC, ") // AS PROGRAM_MODEL_DESC,")
	                                      .append("        T.UPDT_USERID,")
	                                      .append("        T.TIME_STAMP")
	                                      .append("   FROM PWUST_ENGINE_TYPE_MODEL_DEF T ") //SFFND_MODEL_DEF T")
	                                      .append("   WHERE UPPER(T.MODEL) NOT IN ")
	                                      .append("( SELECT UPPER(TT.ENGINE_MODEL) ")
	                                      .append("    FROM PWUST_PLAN_ENGINE_MODEL TT")
	                                      .append("   WHERE TT.PLAN_ID = ?")
	                                      .append("     AND TT.PLAN_UPDT_NO = ? ) ") 
	                                      .append("ORDER BY ENGINE_MODEL");      
	      }
	            
	      ParameterHolder parameters = new ParameterHolder();  
	      if(program != null)
	      {
	        parameters.addParameter(program);  
	      }
	      parameters.addParameter(planId);  
	      parameters.addParameter(planUpdtNo);  

	      return jdbcDao.queryForList(selectSql.toString(), parameters);
	  }
	 // End SMRO_PLG_301
	  
	  // Defect 1356
	  public void updatePlanPPVQuanity(String planId,
			  String planVersion,
			  String planRevision,
			  String planAlterations,
			  Number ppvQuantity)
	  {
		  StringBuffer update = new StringBuffer().append(" UPDATE ")
				                                 .append("    SFPL_PLAN_REV ")
				                                 .append("SET ")
				                                 .append("    PPV_QTY = ?, ")
				                                 .append("    UPDT_USERID = ?, ")
				                                 .append("    TIME_STAMP = "
				                                		 + jdbcDao.getTimestampFunction()
				                                		 + ", ")
				                                 .append("    LAST_ACTION = ? ")
				                                 .append("WHERE ")
				                                 .append("    PLAN_ID = ? AND ")
				                                 .append("    PLAN_VERSION = ? AND ")
				                                 .append("    PLAN_REVISION = ? AND ")
				                                 .append("    PLAN_ALTERATIONS = ? ");

		  ParameterHolder params = new ParameterHolder();
		  params.addParameter(ppvQuantity);
		  params.addParameter(getUsername());
		  params.addParameter(UPDATED);
		  params.addParameter(planId);
		  params.addParameter(planVersion);
		  params.addParameter(planRevision);
		  params.addParameter(planAlterations);
		  jdbcDao.update(update.toString(), params);
	  }
	  
	  
	  // Defect 1808
	  public void updateBuildPartSecurityGroup(String planId,
			                                   Number planUpdtNo,
			                                   String locationId)
	  {
		  StringBuffer update = new StringBuffer().append(" UPDATE SFPL_PLAN_DESC ")
				                                  .append("     SET SECURITY_GROUP = (SELECT LISTAGG(X.SECURITY_GROUP, ',') WITHIN ")
				                                  .append("                            GROUP( ")
				                                  .append("                            ORDER BY X.SECURITY_GROUP) AS SG ")
				                                  .append("                             FROM (SELECT DISTINCT SECURITY_GROUP ")
				                                  .append("                                     FROM UTASGI_USER_SEC_GRP_XREF ")
				                                  .append("                                    WHERE HR_LOC = ")
				                                  .append("                                         (SELECT WORK_LOC ")
				                                  .append("                                            FROM SFFND_WORK_LOC_DEF ")
				                                  .append("                                           WHERE LOCATION_ID = ?)) X) ")
				                                  .append("    WHERE PLAN_ID = ? ")
				                                  .append("      AND PLAN_UPDT_NO = ? ");

		  ParameterHolder params = new ParameterHolder();
		  params.addParameter(locationId);
		  params.addParameter(planId);
		  params.addParameter(planUpdtNo);

		  jdbcDao.update(update.toString(), params);
	  }
}