/*
*  This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017, 2018, 2019, 2020.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    PlanDaoImplPW.java
* 
*  Created: 2017-08-24
* 
*  Author:  xcs4331
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-08-24 	xcs4331		    Initial Release XXXXXXX
* 2017-09-25    R. Thorpe		SMRO_PLG_205 Added insert methods for custom task group tables
* 2017-11-07    R. Thorpe       Defect 181 - Modify methods insertCustomer, insertCustomerException, 
*                               insertSubjectExtension, insertSubNetwork, insertSuperiorNetwork, 
*                               insertWorkscope to remove subject Revision and copy over Obsolete_record_flag
* 2018-01-03    R. Thorpe       SMRO_PLG_205 SCR-004 - Defect 186 - add selectWorkscopeForTask method   
*                               add engineType as parameter in methods insertSubNetwork, insertSuperiorNetwork, 
*                               insertWorkscope 
* 2018-02-27	B. Preston		Defect 323    - Fix exceptions.
* 2018-03-14    B. Preston      Fix for defect #323 by adding method selectPwustPlanEngineModelCount back into source.                  
* 2018-10-22    Fred Ettefagh   defect 977    - Copying plans uses 1 item for tasks and another for the plans
* 2018-11-02	R. Thorpe       SMRO_PLG_205 - Defect 1020 added part_chg param to sub-query in updateOverHaulPlanSubjectItemID - was returning > 1 row
* 2019-03-26    B. Preston		SMRO_PLG_305 - Defect 1147 insert into task group extension table if not already there.
* 2019-04-15    B. Preston		SMRO_PLG_305 - Defect 1147 insert all subject_nos into task group extension table that are not there.
* 2019-08-30	German Rodriguez defect 1317  - Security groups do not populate properly when copying between sites
* 2019-10-17    Fred Ettefagh   SMRO_PLG_205 - Defect 1097, added method selectSfplItemDescMasterAll and updated method updateOverHaulPlanSubjectItemID
* 2020-01-17    Fred Ettefagh   SMRO_PLG_205 - Defect 1097, added method selectSfplplanV and changed method insertTaskGroupExtension
* 2020-01-23	German Rodriguez defect 1317  - Security groups do not populate properly when copying between sites
* 2020-01-30    Fred ETtefagh   SMRO_WOE_204 - Defect 1501 added two new methods selectSfplplanV,insertTaskGroupExtension
* 2020-03-23    John DeNinno	defect 1485 task group was disappearing 
* 2020-10-30    D.Miron         Defect 1807 - Added deleteAllWorkPlanSecurityGroups
* */

package com.pw.solumina.sfpl.application.impl;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.apache.commons.lang.math.NumberUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;

public class PlanDaoImplPW {
	
	@Reference private JdbcDaoSupport jdbcDaoSupport;
	
	public List selectSfplplanV(String planId,
						        Number planVersion,
						        Number planRevision,
						        Number planAlteration){

		StringBuffer selectSql = new StringBuffer().append(" SELECT T.* FROM SFPL_PLAN_V T  WHERE T.PLAN_ID = ? 	AND T.PLAN_VERSION = ? 	AND T.PLAN_REVISION = ?  AND T.PLAN_ALTERATIONS = ?");
	
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planVersion);
		params.addParameter(planRevision);
		params.addParameter(planAlteration);
		
		return jdbcDaoSupport.queryForList(selectSql.toString(), params);

	}
	
	public Map selectSfplItemDescMasterAll(String partNo, String partChg){
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT T.* FROM SFPL_ITEM_DESC_MASTER_ALL T 	WHERE T.PART_NO = ? AND T.PART_CHG = ? "); 
		ParameterHolder params = new ParameterHolder();
		params.addParameter(partNo);
		params.addParameter(partChg);
		
		return (Map) jdbcDaoSupport.queryForMap(selectSql.toString(), params);
	}
	
	public int updateOverHaulPlanSubjectItemID(String planID,
			                                   Number planUpdtNo,
			                                   String partNo,
			                                   String partChg,
			                                   String partTitle,
			                                   String itemId){
		
		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFOR_SFPL_PLAN_SUBJECT_PART T ") // primary key (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, ITEM_ID)
					                               .append(" SET T.ITEM_ID =  ? , ")
					                               .append(    " T.PART_NO = ? , ")
					                               .append(    " T.PART_CHG = ? , ")
					                               .append(    " T.PART_TITLE = ?  ")
					                               .append(" WHERE PLAN_ID = ? ")
					                               .append(" AND PLAN_UPDT_NO = ?");

		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(itemId);
		params.addParameter(partNo);
		params.addParameter(partChg);
		params.addParameter(partTitle);
		params.addParameter(planID);
		params.addParameter(planUpdtNo);

		return jdbcDaoSupport.update(updateSql.toString(),params);
	}
	

	// Begin Defect 323
	public Map selectPwustPlanEngineModelCount(String planId, Number planUpdtNo, String engineModel)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT COUNT(*) CNT ")
                                                   .append("  FROM PWUST_PLAN_ENGINE_MODEL ")
                                                   .append(" WHERE PLAN_ID = ? ")
                                                   .append("   AND PLAN_UPDT_NO = ? ")
                                                   .append("   AND ENGINE_MODEL =  ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(engineModel);
		
		return (Map) jdbcDaoSupport.queryForMap(selectSql.toString(), params);
	}
	// End Defect 323
	
	
	public int deletePwustPlanEngineModel(String planId, Number planUpdtNo, String engineModel){

		StringBuffer delete = new StringBuffer().append(" DELETE FROM PWUST_PLAN_ENGINE_MODEL T WHERE T.PLAN_ID = ? AND T.PLAN_UPDT_NO = ? AND T.ENGINE_MODEL = ?  ");
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(engineModel);
		
		int numRowsDeleted = jdbcDaoSupport.delete(delete.toString(), params);
		return numRowsDeleted;
	}
	
	public void insertIntoPwustPlanEngineModel(String planId, Number planUpdtNo, String engineModel){

		StringBuffer insert = new StringBuffer().append(" INSERT INTO PWUST_PLAN_ENGINE_MODEL (PLAN_ID, PLAN_UPDT_NO, ENGINE_MODEL, UPDT_USERID ) VALUES ( ?,  ? , ? , ?  ) ");
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(engineModel);
		params.addParameter(ContextUtil.getUsername());
		
		jdbcDaoSupport.insert(insert.toString(), params);
	}

	public String selectDBTimeStamp()
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT TO_CHAR(SFMFG.SFDB_SYSDATE(),'MM/DD/YYYY HH:MI:SS AM') AS DB_SYSDATE FROM DUAL ");
		return (String) jdbcDaoSupport.queryForObject(selectSql.toString(), String.class);
	}	

	
	public Map selectSfplPlanRevMap(String planId, Number planVer, Number PlanRev, Number planAtl){
		
		 StringBuffer select = new StringBuffer().append(" SELECT T.* ")
				                                 .append(" FROM SFPL_PLAN_REV T ")
				                                 .append(" WHERE T.PLAN_ID = ? ") 
				                                 .append(" AND T.PLAN_VERSION = ? ")
				                                 .append(" AND T.PLAN_REVISION = ? ")
				                                 .append(" AND T.PLAN_ALTERATIONS = ? " );

		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planVer);
		params.addParameter(PlanRev);
		params.addParameter(planAtl);
		
		Map map = jdbcDaoSupport.queryForMap(select.toString(), params);
		
		
		return map;
		
	}
	
	
	public Map selectSffndWorkLocMap(String locationId){
		
		 StringBuffer select = new StringBuffer().append(" SELECT T.* ")
				                                 .append(" FROM SFFND_WORK_LOC_DEF T ")
				                                 .append(" WHERE T.LOCATION_ID = ? "); 

		ParameterHolder params = new ParameterHolder();
		params.addParameter(locationId);
		
		Map map = jdbcDaoSupport.queryForMap(select.toString(), params);
		
		return map;
	}
	
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
	
	
	public int updateSfplPlanDesc(String planId,
                                  Number planUpdateNo,
                                  String changeLevelNotes){
		
		StringBuffer update = new StringBuffer().append(" UPDATE SFPL_PLAN_DESC T ")
                                                .append(" SET T.UCF_PLAN_VCH4000_1 = ?, ")
                                                .append("     T. UCF_PLAN_VCH255_1 = ?, ")
                                                .append("     T.UPDT_USERID = ?, ")
                                                .append("     T.TIME_STAMP =  "  + jdbcDaoSupport.getTimestampFunction() ) 
                                                .append(" WHERE T.PLAN_ID = ? ")
                                                .append(" AND T.PLAN_UPDT_NO = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(changeLevelNotes);
		params.addParameter("");
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(planId);
		params.addParameter(planUpdateNo);
		int rowCount = jdbcDaoSupport.update(update.toString(), params);
		return rowCount;
	}	

	// Begin defect 1147
	public void insertTaskGroupExtension(String srcPlanId, Number srcUpdtNo, String destPlanId, Number destUpdtNo) {
		StringBuffer insertSql = new StringBuffer().append("insert ")
				.append("into pwust_sfor_sfpl_plan_subject ")
				.append("(plan_id, plan_updt_no, subject_no, subject_rev, mandatory, updt_userid, time_stamp, last_action, obsolete_record_flag) ")
				.append("    select ?, ?, subject_no, subject_rev, 'N', ?, sysdate, 'COPIED', 'N' ")
				.append("    from sfor_sfpl_plan_subject s1 ")
				.append("    where plan_id = ? and plan_updt_no = ? and not exists ")
				.append("   (select 1 from pwust_sfor_sfpl_plan_subject s2 where plan_id = ? and plan_updt_no = ? and s1.subject_no = s2.subject_no)");
		
		String userName = ContextUtil.getUsername();
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(destPlanId);
		params.addParameter(destUpdtNo);
		params.addParameter(userName);
		params.addParameter(destPlanId);
		params.addParameter(destUpdtNo);
		params.addParameter(destPlanId);
		params.addParameter(destUpdtNo);
		
		jdbcDaoSupport.insert(insertSql.toString(), params);
	}
	// End defect 1147
	
	
	// Begin defect 1147
	public void insertTaskGroupExtension(String planId, Number planUpdtNo) {
		
		StringBuffer insertSql = new StringBuffer().append(" INSERT INTO PWUST_SFOR_SFPL_PLAN_SUBJECT( PLAN_ID, "
				                                                                                   + " PLAN_UPDT_NO, "
				                                                                                   + " SUBJECT_NO, "
				                                                                                   + " SUBJECT_REV, "
				                                                                                   + " MANDATORY, "
				                                                                                   + " UPDT_USERID, "
				                                                                                   + " TIME_STAMP, "
				                                                                                   + " LAST_ACTION, "
				                                                                                   + " OBSOLETE_RECORD_FLAG ) "  
		                                                  + " SELECT S1.PLAN_ID, "
		                                                         + " S1.PLAN_UPDT_NO, "
		                                                         + " S1.SUBJECT_NO, "
		                                                         + " S1.SUBJECT_REV, "
		                                                         + " 'N', "
		                                                         + " S1.UPDT_USERID, "
		                                                         + " S1.TIME_STAMP, "
		                                                         + " S1.LAST_ACTION, "
		                                                         + " S1.OBSOLETE_RECORD_FLAG " +
		                                                  " FROM SFOR_SFPL_PLAN_SUBJECT S1 "+   // primary key (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO)
		                                                  " WHERE S1.PLAN_ID = ? " + 
													      " AND S1.PLAN_UPDT_NO = ? " + 
														  " AND NOT EXISTS (SELECT 1 " + 
														                    " FROM PWUST_SFOR_SFPL_PLAN_SUBJECT S2 " + 
														                    " WHERE S2.PLAN_ID =S1.PLAN_ID " +
														                    " AND   S2.PLAN_UPDT_NO = S1.PLAN_UPDT_NO " +
														                    " AND   S2.SUBJECT_NO = S1.SUBJECT_NO) ");
		
		
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		
		System.out.println("insertSql=" + insertSql);
		System.out.println("planId=" + planId);
		System.out.println("planUpdtNo=" + planUpdtNo);
		
		jdbcDaoSupport.insert(insertSql.toString(), params);
	}
	
	//defect 1485 begin
		public void insertTaskGroupExtension2(String planId, Number planUpdtNo) {
			
			StringBuffer insertSql = new StringBuffer().append(" INSERT INTO PWUST_SFOR_SFPL_PLAN_SUBJECT( PLAN_ID, "
					                                                                                   + " PLAN_UPDT_NO, "
					                                                                                   + " SUBJECT_NO, "
					                                                                                   + " SUBJECT_REV, "
					                                                                                   + " MANDATORY, "
					                                                                                   + " UPDT_USERID, "
					                                                                                   + " TIME_STAMP, "
					                                                                                   + " LAST_ACTION, "
					                                                                                   + " OBSOLETE_RECORD_FLAG ) "  
			                                                  + " SELECT S1.PLAN_ID, "
			                                                         + " S1.PLAN_UPDT_NO, "
			                                                         + " S1.SUBJECT_NO, "
			                                                         + " S1.SUBJECT_REV, "
			                                                         + " 'N', "
			                                                         + " S1.UPDT_USERID, "
			                                                         + " S1.TIME_STAMP, "
			                                                         + " S1.LAST_ACTION, "
			                                                         + " S1.OBSOLETE_RECORD_FLAG " +
			                                                  " FROM SFOR_SFPL_PLAN_SUBJECT S1 "+   // primary key (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO)
			                                                  " WHERE S1.PLAN_ID = ? " + 
														      " AND S1.PLAN_UPDT_NO = ? " +
															  " AND NOT EXISTS (SELECT 1 " + 
															                    " FROM PWUST_SFOR_SFPL_PLAN_SUBJECT S2 " + 
															                    " WHERE S2.PLAN_ID =S1.PLAN_ID " +
															                    " AND   S2.PLAN_UPDT_NO = S1.PLAN_UPDT_NO " +
															                    " AND   S2.SUBJECT_REV = (select max(S1.SUBJECT_REV) from SFOR_SFPL_PLAN_SUBJECT WHERE S2.PLAN_ID =S1.PLAN_ID AND S2.PLAN_UPDT_NO = S1.PLAN_UPDT_NO and  S2.SUBJECT_NO = S1.SUBJECT_NO ) " +
															                    " AND   S2.SUBJECT_NO = S1.SUBJECT_NO) ");
			
			
			
			ParameterHolder params = new ParameterHolder();
			params.addParameter(planId);
			params.addParameter(planUpdtNo);
			
			System.out.println("insertSql=" + insertSql);
			System.out.println("planId=" + planId);
			System.out.println("planUpdtNo=" + planUpdtNo);
			
			jdbcDaoSupport.insert(insertSql.toString(), params);
		}
		// End defect 1485
	public void insertSubjectExtension(Number subjectNumber, Number subjectRevision, String userName, String sourcePlanId, Number sourcePlanUpdtNo, String newPlanId, Number newPlanUpdtNo)
	{
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_SFOR_SFPL_PLAN_SUBJECT")
				                                   .append(" (PLAN_ID,")
				                                   .append("  PLAN_UPDT_NO,")
				                                   .append("  SUBJECT_NO,")
				                                   .append("  SUBJECT_REV,")  
				                                   .append("  MANDATORY,")
				                                   .append("  UPDT_USERID,")
				                                   .append("  OBSOLETE_RECORD_FLAG,")
				                                   .append("  LAST_ACTION) ")
				                                   .append(" SELECT ?, ")
				                                   .append("        ?, ") 
				                                   .append("        ?, ")  
				                                   .append("        ?, ")  
				                                   .append("        MANDATORY,")
				                                   .append("        ?, ")
				                                   .append("        OBSOLETE_RECORD_FLAG,")
				                                   .append("        ? ")
				                                   .append("  FROM PWUST_SFOR_SFPL_PLAN_SUBJECT")
				                                   .append(" WHERE PLAN_ID = ? ")  
				                                   .append("   AND PLAN_UPDT_NO = ?")  
				                                   .append("   AND SUBJECT_NO = ?")
				                                   .append("   AND SUBJECT_REV = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(newPlanId);		
		params.addParameter(newPlanUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(NumberUtils.INTEGER_ONE);
		params.addParameter(userName);
		params.addParameter(SoluminaConstants.COPIED);
		params.addParameter(sourcePlanId);
		params.addParameter(sourcePlanUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(subjectRevision);
		
		jdbcDaoSupport.insert(insertSql.toString(), params);
	}
	
	public void insertCustomer(Number subjectNumber, Number subjectRevision, String userName, String sourcePlanId, Number sourcePlanUpdtNo, String newPlanId, Number newPlanUpdtNo)
	{
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_PL_SUBJ_CUSTOMER")
                                                   .append(" (PLAN_ID,")
                                                   .append("  PLAN_UPDT_NO,")
                                                   .append("  SUBJECT_NO,")
                                                   .append("  SUBJECT_REV,")
                                                   .append("  CUST_ID,")
                                                   .append("  UPDT_USERID,")
                                                   .append("  OBSOLETE_RECORD_FLAG,")
                                                   .append("  LAST_ACTION) ")
				                                   .append(" SELECT ?, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")
				                                   .append("        CUST_ID,")
				                                   .append("        ?, ")
				                                   .append("        OBSOLETE_RECORD_FLAG,")
				                                   .append("        ? ")
				                                   .append("  FROM PWUST_PL_SUBJ_CUSTOMER")
				                                   .append(" WHERE PLAN_ID = ? ")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?")
				                                   .append("   AND SUBJECT_REV = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(newPlanId);		
		params.addParameter(newPlanUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(NumberUtils.INTEGER_ONE);
		params.addParameter(userName);
		params.addParameter(SoluminaConstants.COPIED);
		params.addParameter(sourcePlanId);
		params.addParameter(sourcePlanUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(subjectRevision);
		
		jdbcDaoSupport.insert(insertSql.toString(), params);
	}
	
	public void insertCustomerException(Number subjectNumber, Number subjectRevision, String userName, String sourcePlanId, Number sourcePlanUpdtNo, String newPlanId, Number newPlanUpdtNo)
	{
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_PL_SUBJ_CUST_EXCEPT")
                                                   .append(" (PLAN_ID,")
                                                   .append("  PLAN_UPDT_NO,")
                                                   .append("  SUBJECT_NO,")
                                                   .append("  SUBJECT_REV,")
                                                   .append("  CUST_ID,")
                                                   .append("  CUST_ID_EXCEPTION,")
                                                   .append("  UPDT_USERID,")	
                                                   .append("  OBSOLETE_RECORD_FLAG,")
                                                   .append("  LAST_ACTION) ")
				                                   .append(" SELECT ?, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")
				                                   .append("        CUST_ID,")
				                                   .append("        CUST_ID_EXCEPTION,")
				                                   .append("        ?, ")
				                                   .append("        OBSOLETE_RECORD_FLAG,")
				                                   .append("        ? ")
				                                   .append("  FROM PWUST_PL_SUBJ_CUST_EXCEPT")
				                                   .append(" WHERE PLAN_ID = ? ")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?")
				                                   .append("   AND SUBJECT_REV = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(newPlanId);		
		params.addParameter(newPlanUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(NumberUtils.INTEGER_ONE);
		params.addParameter(userName);
		params.addParameter(SoluminaConstants.COPIED);
		params.addParameter(sourcePlanId);
		params.addParameter(sourcePlanUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(subjectRevision);

		jdbcDaoSupport.insert(insertSql.toString(), params);
	}
	
	public void insertSuperiorNetwork(Number subjectNumber, Number subjectRevision, String userName, String sourcePlanId, Number sourcePlanUpdtNo, String newPlanId, Number newPlanUpdtNo, String newEngineType)
	{
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_PL_SUBJ_NETWORK_ACTIVITY")
                                                   .append(" (PLAN_ID,")
                                                   .append("  PLAN_UPDT_NO,")
                                                   .append("  SUBJECT_NO,")
                                                   .append("  SUBJECT_REV,")
                                                   .append("  ENGINE_TYPE,")
                                                   .append("  SUPERIOR_NETWORK_ACT,")
                                                   .append("  UPDT_USERID,")
                                                   .append("  OBSOLETE_RECORD_FLAG,")
                                                   .append("  LAST_ACTION) ")
				                                   .append(" SELECT ?, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")     //SMRO_PLG_205 SCR-004
                                                   .append("        SUPERIOR_NETWORK_ACT,")
				                                   .append("        ?, ")
				                                   .append("        OBSOLETE_RECORD_FLAG,")
				                                   .append("        ? ")
				                                   .append("  FROM PWUST_PL_SUBJ_NETWORK_ACTIVITY")
				                                   .append(" WHERE PLAN_ID = ? ")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?")
				                                   .append("   AND SUBJECT_REV = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(newPlanId);		
		params.addParameter(newPlanUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(NumberUtils.INTEGER_ONE);
		params.addParameter(newEngineType);    // SMRO_PLG_205 SCR-004
		params.addParameter(userName);
		params.addParameter(SoluminaConstants.COPIED);
		params.addParameter(sourcePlanId);
		params.addParameter(sourcePlanUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(subjectRevision);
		
		jdbcDaoSupport.insert(insertSql.toString(), params);
	}

	public void insertSubNetwork(Number subjectNumber, Number subjectRevision, String userName, String sourcePlanId, Number sourcePlanUpdtNo, String newPlanId, Number newPlanUpdtNo, String newEngineType)
	{
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_PL_SUBJ_SUBNET_ACTIVITY")
                                                   .append(" (PLAN_ID,")
                                                   .append("  PLAN_UPDT_NO,")
                                                   .append("  SUBJECT_NO,")
                                                   .append("  SUBJECT_REV,")
                                                   .append("  ENGINE_TYPE,")
                                                   .append("  SUPERIOR_NETWORK_ACT,")
                                                   .append("  SUB_NETWORK_ACT,")
                                                   .append("  UPDT_USERID,")	
                                                   .append("  OBSOLETE_RECORD_FLAG,")
                                                   .append("  LAST_ACTION) ")
				                                   .append(" SELECT ?, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")   //SMRO_PLG_205 SCR-004
				                                   .append("        SUPERIOR_NETWORK_ACT,")
                                                   .append("        SUB_NETWORK_ACT,")
				                                   .append("        ?, ")
				                                   .append("        OBSOLETE_RECORD_FLAG,")
				                                   .append("        ? ")
				                                   .append("  FROM PWUST_PL_SUBJ_SUBNET_ACTIVITY")
				                                   .append(" WHERE PLAN_ID = ? ")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?")
				                                   .append("   AND SUBJECT_REV = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(newPlanId);		
		params.addParameter(newPlanUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(NumberUtils.INTEGER_ONE);
		params.addParameter(newEngineType);   // SMRO_PLG_205 SCR-004
		params.addParameter(userName);
		params.addParameter(SoluminaConstants.COPIED);
		params.addParameter(sourcePlanId);
		params.addParameter(sourcePlanUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(subjectRevision);
		
		
		jdbcDaoSupport.insert(insertSql.toString(), params);
	}
	
	public void insertWorkscope(Number subjectNumber, Number subjectRevision, String userName, String sourcePlanId, Number sourcePlanUpdtNo, String newPlanId, Number newPlanUpdtNo)
	{
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_PL_SUBJ_WORKSCOPE")
				                                   .append(" (PLAN_ID,")
				                                   .append("  PLAN_UPDT_NO,")
				                                   .append("  SUBJECT_NO,")
				                                   .append("  SUBJECT_REV,")
				                                   .append("  WORKSCOPE,")
				                                   .append("  UPDT_USERID,")	
				                                   .append("  OBSOLETE_RECORD_FLAG,")
                                                   .append("  LAST_ACTION) ")
				                                   .append(" SELECT ?, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")
				                                   .append("        WORKSCOPE,")   
				                                   .append("        ?, ")
				                                   .append("        OBSOLETE_RECORD_FLAG,")
				                                   .append("        ? ") 
				                                   .append("  FROM PWUST_PL_SUBJ_WORKSCOPE")
				                                   .append(" WHERE PLAN_ID = ? ")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?")
				                                   .append("   AND SUBJECT_REV = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(newPlanId);		
		params.addParameter(newPlanUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(NumberUtils.INTEGER_ONE);
		params.addParameter(userName);
		params.addParameter(SoluminaConstants.COPIED);  
		params.addParameter(sourcePlanId);
		params.addParameter(sourcePlanUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(subjectRevision);

		jdbcDaoSupport.insert(insertSql.toString(), params);
	}
	
	//SMRO_PLG_205 SCR-004
	public void insertWorkscope(Number subjectNumber, Number subjectRevision, String userName, String sourcePlanId, Number sourcePlanUpdtNo, String newPlanId, Number newPlanUpdtNo, String workscope)
	{
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_PL_SUBJ_WORKSCOPE")
				                                   .append(" (PLAN_ID,")
				                                   .append("  PLAN_UPDT_NO,")
				                                   .append("  SUBJECT_NO,")
				                                   .append("  SUBJECT_REV,")
				                                   .append("  WORKSCOPE,")
				                                   .append("  UPDT_USERID,")	
				                                   .append("  OBSOLETE_RECORD_FLAG,")
                                                   .append("  LAST_ACTION) ")
				                                   .append(" SELECT ?, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")
				                                   .append("        WORKSCOPE,")   
				                                   .append("        ?, ")
				                                   .append("        OBSOLETE_RECORD_FLAG,")
				                                   .append("        ? ") 
				                                   .append("  FROM PWUST_PL_SUBJ_WORKSCOPE")
				                                   .append(" WHERE PLAN_ID = ? ")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?")
				                                   .append("   AND SUBJECT_REV = ?")
				                                   .append("   AND WORKSCOPE = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(newPlanId);		
		params.addParameter(newPlanUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(NumberUtils.INTEGER_ONE);
		params.addParameter(userName);
		params.addParameter(SoluminaConstants.COPIED);  
		params.addParameter(sourcePlanId);
		params.addParameter(sourcePlanUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(subjectRevision);
		params.addParameter(workscope);

		jdbcDaoSupport.insert(insertSql.toString(), params);
	}

	public void insertSubjectExtension(Number subjectNumber, String userName, String planId, Number newPlanUpdtNo)
	{
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_SFOR_SFPL_PLAN_SUBJECT")
				                                   .append(" (PLAN_ID,")
				                                   .append("  PLAN_UPDT_NO,")
				                                   .append("  SUBJECT_NO,")
				                                   .append("  SUBJECT_REV,")
				                                   .append("  MANDATORY,")
				                                   .append("  UPDT_USERID,")
				                                   .append("  OBSOLETE_RECORD_FLAG,")
				                                   .append("  LAST_ACTION) ")
				                                   .append(" SELECT PLAN_ID, ")
				                                   .append("        ?, ")  
				                                   .append("        ?, ")  
				                                   .append("        SUBJECT_REV, ")
				                                   .append("        MANDATORY,")
				                                   .append("        ?, ")
				                                   .append("        OBSOLETE_RECORD_FLAG,")
				                                   .append("        ? ")
				                                   .append("  FROM PWUST_SFOR_SFPL_PLAN_SUBJECT")
				                                   .append(" WHERE PLAN_ID = ? ")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(newPlanUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(userName);
		params.addParameter(SoluminaConstants.COPIED);
		params.addParameter(planId);
		params.addParameter(newPlanUpdtNo.intValue() - 1);
		params.addParameter(subjectNumber);
		
		jdbcDaoSupport.insert(insertSql.toString(), params);
	}
	
	public void insertCustomer(Number subjectNumber,  String userName, String planId, Number newPlanUpdtNo)
	{
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_PL_SUBJ_CUSTOMER")
                                                   .append(" (PLAN_ID,")
                                                   .append("  PLAN_UPDT_NO,")
                                                   .append("  SUBJECT_NO,")
                                                   .append("  SUBJECT_REV,")
                                                   .append("  CUST_ID,")
                                                   .append("  UPDT_USERID,")
                                                   .append("  OBSOLETE_RECORD_FLAG,")
                                                   .append("  LAST_ACTION) ")
				                                   .append(" SELECT PLAN_ID, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")
				                                   .append("        SUBJECT_REV, ")
				                                   .append("        CUST_ID,")
				                                   .append("        ?, ")
				                                   .append("        OBSOLETE_RECORD_FLAG,")
				                                   .append("        ? ")
				                                   .append("  FROM PWUST_PL_SUBJ_CUSTOMER")
				                                   .append(" WHERE PLAN_ID = ? ")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(newPlanUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(userName);
		params.addParameter(SoluminaConstants.COPIED);
		params.addParameter(planId);
		params.addParameter(newPlanUpdtNo.intValue() - 1);
		params.addParameter(subjectNumber);
		
		jdbcDaoSupport.insert(insertSql.toString(), params);
	}
	
	public void insertCustomerException(Number subjectNumber, String userName, String planId, Number newPlanUpdtNo)
	{
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_PL_SUBJ_CUST_EXCEPT")
                                                   .append(" (PLAN_ID,")
                                                   .append("  PLAN_UPDT_NO,")
                                                   .append("  SUBJECT_NO,")
                                                   .append("  SUBJECT_REV,")
                                                   .append("  CUST_ID,")
                                                   .append("  CUST_ID_EXCEPTION,")
                                                   .append("  UPDT_USERID,")	
                                                   .append("  OBSOLETE_RECORD_FLAG,")
                                                   .append("  LAST_ACTION) ")
				                                   .append(" SELECT PLAN_ID, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")
				                                   .append("        SUBJECT_REV, ")
				                                   .append("        CUST_ID,")
				                                   .append("        CUST_ID_EXCEPTION,")
				                                   .append("        ?, ")
				                                   .append("        OBSOLETE_RECORD_FLAG,")
				                                   .append("        ? ")
				                                   .append("  FROM PWUST_PL_SUBJ_CUST_EXCEPT")
				                                   .append(" WHERE PLAN_ID = ? ")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(newPlanUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(userName);
		params.addParameter(SoluminaConstants.COPIED);
		params.addParameter(planId);
		params.addParameter(newPlanUpdtNo.intValue() - 1);
		params.addParameter(subjectNumber);

		jdbcDaoSupport.insert(insertSql.toString(), params);
	}
	
	public void insertSuperiorNetwork(Number subjectNumber, String userName, String planId, Number newPlanUpdtNo)
	{
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_PL_SUBJ_NETWORK_ACTIVITY")
                                                   .append(" (PLAN_ID,")
                                                   .append("  PLAN_UPDT_NO,")
                                                   .append("  SUBJECT_NO,")
                                                   .append("  SUBJECT_REV,")
                                                   .append("  ENGINE_TYPE,")
                                                   .append("  SUPERIOR_NETWORK_ACT,")
                                                   .append("  UPDT_USERID,")	
                                                   .append("  OBSOLETE_RECORD_FLAG,")
                                                   .append("  LAST_ACTION) ")
				                                   .append(" SELECT PLAN_ID, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")
				                                   .append("        SUBJECT_REV, ")
				                                   .append("        ENGINE_TYPE,")
                                                   .append("        SUPERIOR_NETWORK_ACT,")
				                                   .append("        ?, ")
				                                   .append("        OBSOLETE_RECORD_FLAG,")
				                                   .append("        ? ")
				                                   .append("  FROM PWUST_PL_SUBJ_NETWORK_ACTIVITY")
				                                   .append(" WHERE PLAN_ID = ? ")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(newPlanUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(userName);
		params.addParameter(SoluminaConstants.COPIED);
		params.addParameter(planId);
		params.addParameter(newPlanUpdtNo.intValue() - 1);
		params.addParameter(subjectNumber);
		
		jdbcDaoSupport.insert(insertSql.toString(), params);
	}

	public void insertSubNetwork(Number subjectNumber, String userName, String planId, Number newPlanUpdtNo)
	{
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_PL_SUBJ_SUBNET_ACTIVITY")
                                                   .append(" (PLAN_ID,")
                                                   .append("  PLAN_UPDT_NO,")
                                                   .append("  SUBJECT_NO,")
                                                   .append("  SUBJECT_REV,")
                                                   .append("  ENGINE_TYPE,")
                                                   .append("  SUPERIOR_NETWORK_ACT,")
                                                   .append("  SUB_NETWORK_ACT,")
                                                   .append("  UPDT_USERID,")	
                                                   .append("  OBSOLETE_RECORD_FLAG,")
                                                   .append("  LAST_ACTION) ")
				                                   .append(" SELECT PLAN_ID, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")
				                                   .append("        SUBJECT_REV, ")
				                                   .append("        ENGINE_TYPE,")
				                                   .append("        SUPERIOR_NETWORK_ACT,")
                                                   .append("        SUB_NETWORK_ACT,")
				                                   .append("        ?, ")
				                                   .append("        OBSOLETE_RECORD_FLAG,")
				                                   .append("        ? ")
				                                   .append("  FROM PWUST_PL_SUBJ_SUBNET_ACTIVITY")
				                                   .append(" WHERE PLAN_ID = ? ")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(newPlanUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(userName);
		params.addParameter(SoluminaConstants.COPIED);
		params.addParameter(planId);
		params.addParameter(newPlanUpdtNo.intValue() - 1);
		params.addParameter(subjectNumber);
		
		jdbcDaoSupport.insert(insertSql.toString(), params);
	}
	
	public void insertWorkscope(Number subjectNumber, String userName, String planId, Number newPlanUpdtNo)
	{
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_PL_SUBJ_WORKSCOPE")
				                                   .append(" (PLAN_ID,")
				                                   .append("  PLAN_UPDT_NO,")
				                                   .append("  SUBJECT_NO,")
				                                   .append("  SUBJECT_REV,")
				                                   .append("  WORKSCOPE,")
				                                   .append("  UPDT_USERID,")	
				                                   .append("  OBSOLETE_RECORD_FLAG,")
                                                   .append("  LAST_ACTION) ")
				                                   .append(" SELECT PLAN_ID, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")
				                                   .append("        SUBJECT_REV, ")
				                                   .append("        WORKSCOPE,")
				                                   .append("        ?, ")
				                                   .append("        OBSOLETE_RECORD_FLAG,")
				                                   .append("        ? ")
				                                   .append("  FROM PWUST_PL_SUBJ_WORKSCOPE")
				                                   .append(" WHERE PLAN_ID = ? ")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(newPlanUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(userName);
		params.addParameter(SoluminaConstants.COPIED);
		params.addParameter(planId);
		params.addParameter(newPlanUpdtNo.intValue() - 1);
		params.addParameter(subjectNumber);
		
		jdbcDaoSupport.insert(insertSql.toString(), params);
	}

	public Map selectSourcePlanMap(String sourcePlanNumber, Number sourcePlanVersion, Number sourcePlanRevision, Number sourcePlanAlterations)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT D.PLAN_ID, D.PLAN_UPDT_NO, D.PROGRAM ")     //  SMRO_PLG_205 SCR-004  Add Program
                                                   .append("  FROM SFPL_PLAN_DESC D, SFPL_PLAN_REV R")
                                                   .append(" WHERE D.PLAN_ID = R.PLAN_ID ")
                                                   .append("   AND D.PLAN_UPDT_NO = R.PLAN_UPDT_NO")
                                                   .append("   AND D.PLAN_NO =  ?")
                                                   .append("   AND R.PLAN_VERSION = ?")
                                                   .append("   AND R.PLAN_REVISION = ?")
                                                   .append("   AND R.PLAN_ALTERATIONS = ?");	
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(sourcePlanNumber);
		params.addParameter(sourcePlanVersion);
		params.addParameter(sourcePlanRevision);
		params.addParameter(sourcePlanAlterations);
		
		return (Map) jdbcDaoSupport.queryForMap(selectSql.toString(), params);
	}

	// Begin Defect 323
	public Map selectSourcePlanIdMap(String sourcePartNumber, Number sourcePlanVersion, Number sourcePlanRevision, Number sourcePlanAlterations)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT D.PLAN_ID  ")
                                                   .append("  FROM SFPL_PLAN_DESC D, SFPL_PLAN_REV R")
                                                   .append(" WHERE D.PLAN_ID = R.PLAN_ID ")
                                                   .append("   AND D.PLAN_UPDT_NO = R.PLAN_UPDT_NO")
                                                   .append("   AND D.PART_NO =  ?")
                                                   .append("   AND R.PLAN_VERSION = ?")
                                                   .append("   AND R.PLAN_REVISION = ?")
                                                   .append("   AND R.PLAN_ALTERATIONS = ? ")
                                                   .append("   AND R.TIME_STAMP = ")
                                                   .append(" (SELECT MAX(R2.TIME_STAMP) ")	
                                                   .append("  FROM SFPL_PLAN_DESC D2, SFPL_PLAN_REV R2")
                                                   .append("  WHERE D2.PLAN_ID = R2.PLAN_ID ")
                                                   .append("   AND D2.PLAN_UPDT_NO = R2.PLAN_UPDT_NO")
                                                   .append("   AND D2.PART_NO =  ?")
                                                   .append("   AND R2.PLAN_VERSION = ?")
                                                   .append("   AND R2.PLAN_REVISION = ?")
                                                   .append("   AND R2.PLAN_ALTERATIONS = ?)");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(sourcePartNumber);
		params.addParameter(sourcePlanVersion);
		params.addParameter(sourcePlanRevision);
		params.addParameter(sourcePlanAlterations);
		
		params.addParameter(sourcePartNumber);
		params.addParameter(sourcePlanVersion);
		params.addParameter(sourcePlanRevision);
		params.addParameter(sourcePlanAlterations);
		
		return (Map) jdbcDaoSupport.queryForMap(selectSql.toString(), params);
	}
	// End Defect 323

	
	public Map selectNetworkForTask(String sourcePlanId, Number sourcePlanUpdtNo, Number subjectNumber, Number subjectRevision)
	{
		 StringBuffer selectSql = new StringBuffer().append("SELECT T.SUPERIOR_NETWORK_ACT,")
				                                    .append("       T.SUB_NETWORK_ACT")
				                                    .append("  FROM PWUST_PL_SUBJ_SUBNET_ACTIVITY T")
				                                    .append(" WHERE T.PLAN_ID = ?")
				                                    .append("   AND T.PLAN_UPDT_NO = ?")
				                                    .append("   AND T.SUBJECT_NO = ?")
				                                    .append("   AND T.SUBJECT_REV = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(sourcePlanId);
		params.addParameter(sourcePlanUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(subjectRevision);
		
		Map map = jdbcDaoSupport.queryForMap(selectSql.toString(), params);
		
		return map;
	}
	
	// SMRO_PLG_205 SCR-004
	public List selectWorkscopeForTask(String sourcePlanId, Number sourcePlanUpdtNo, Number subjectNumber, Number subjectRevision)
	{
		Map taskWorkscope = null;
		StringBuffer selectSql = new StringBuffer().append("SELECT T.WORKSCOPE")
				                                   .append("  FROM PWUST_PL_SUBJ_WORKSCOPE T")
				                                   .append(" WHERE T.PLAN_ID = ?")
				                                   .append("   AND T.PLAN_UPDT_NO = ?")
				                                   .append("   AND T.SUBJECT_NO = ?")
				                                   .append("   AND T.SUBJECT_REV = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(sourcePlanId);
		params.addParameter(sourcePlanUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(subjectRevision);

		return jdbcDaoSupport.queryForList(selectSql.toString(), params);
	}
	
	public String selectPlanEngineType(String planId, Number planVersion, Number planRevision, Number planAlterations)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT D.PROGRAM") 
                                                   .append("  FROM SFPL_PLAN_DESC D, SFPL_PLAN_REV R")
                                                   .append(" WHERE D.PLAN_ID = R.PLAN_ID ")
                                                   .append("   AND D.PLAN_UPDT_NO = R.PLAN_UPDT_NO")
                                                   .append("   AND D.PLAN_ID =  ?")
                                                   .append("   AND R.PLAN_VERSION = ?")
                                                   .append("   AND R.PLAN_REVISION = ?")
                                                   .append("   AND R.PLAN_ALTERATIONS = ?");	
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planVersion);
		params.addParameter(planRevision);
		params.addParameter(planAlterations);
		
		String engineType = jdbcDaoSupport.queryForString(selectSql.toString(), params);

		return engineType;
	}
	
	public String selectPlanWorkLocation(String planId, Number planUpdtNo)
	{
	
		StringBuffer selectSql = new StringBuffer().append("SELECT W.WORK_LOC") 
				                                   .append("  FROM SFPL_PLAN_DESC D, SFFND_WORK_LOC_DEF W")
                                                   .append(" WHERE D.PLND_LOCATION_ID = W.LOCATION_ID")
                                                   .append("   AND D.PLAN_ID =  ?")
                                                   .append("   AND D.PLAN_UPDT_NO = ?");	
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		
		String workLocation = jdbcDaoSupport.queryForString(selectSql.toString(), params);

		return workLocation;
	}    

	public List<String> selectPwustPlanEngineModel(String planId, Number planVer, Number PlanRev, Number planAtl){
		
		 StringBuffer select = new StringBuffer().append(" SELECT T2.ENGINE_MODEL ")
				                                 .append(" 	FROM SFPL_PLAN_REV T1, PWUST_PLAN_ENGINE_MODEL T2 ")
				                                 .append(" WHERE T1.PLAN_ID = ? ") 
				                                 .append(" 	AND T1.PLAN_VERSION = ? ")
				                                 .append(" 	AND T1.PLAN_REVISION = ? ")
				                                 .append(" 	AND T1.PLAN_ALTERATIONS = ? " )
				                                 .append(" 	AND T1.PLAN_ID = T2.PLAN_ID " )
				                                 .append(" 	AND T1.PLAN_UPDT_NO = T2.PLAN_UPDT_NO " )
				                                 ;

		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planVer);
		params.addParameter(PlanRev);
		params.addParameter(planAtl);
		
		List<Map<String, String>> result = jdbcDaoSupport.queryForList(select.toString(), params);
		ArrayList<String> engineModels=new ArrayList<String>(result.size());
		for(Map<String, String> row:result){
			engineModels.add(row.get("ENGINE_MODEL"));
		}

		return engineModels;
		
	}
	
	//Defect 1317
	public int updateSecurityGrpToNull(String planID){

		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFMFG.SFPL_PLAN_DESC ") 
													.append(" SET SECURITY_GROUP = NULL ")
													.append(" WHERE PLAN_ID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(planID);

		return jdbcDaoSupport.update(updateSql.toString(),params);
	}
	
	  // Defect 1807
	  public int deleteAllWorkPlanSecurityGroups( String planId,
			                                      Number planUpdtNo)
	  {
		  StringBuffer deleteSql = new StringBuffer().append("DELETE FROM ")
				                                     .append("    SFPL_PLAN_DESC_SEC_GRP ")
				                                     .append("WHERE ")
				                                     .append("    PLAN_ID = ? AND ")
				                                     .append("    PLAN_UPDT_NO = ? ");

		  ParameterHolder parameters = new ParameterHolder();
		  parameters.addParameter(planId);
		  parameters.addParameter(planUpdtNo);

		  return jdbcDaoSupport.delete(deleteSql.toString(), parameters);
	  }		
}
