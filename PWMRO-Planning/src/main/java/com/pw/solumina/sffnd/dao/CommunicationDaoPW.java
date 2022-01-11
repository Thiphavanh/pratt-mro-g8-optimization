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
*  File:    CommunicationDaoPW.java
* 
*  Created: 2017-09-26
* 
*  Author:  c079222
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-09-26 	c079222		    SMRO_EXPT_201 - Initial Release
*/
package com.pw.solumina.sffnd.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;

public class CommunicationDaoPW {

	@Reference
	private JdbcDaoSupport jdbcDao;
	
    public CommunicationDaoPW()
    {
    	///jdbcDao = (JdbcDaoSupport) SoluminaServiceLocator.locateService(JdbcDaoSupport.class);
    }
    
	public Map selectSubmitterClock(String taskId)
	{	
		StringBuffer select = new StringBuffer().append(" select a.comm_id, a.from_userid ")
				                                .append("   from sffnd_comm a ")
				                                .append("  where a.task_id = ? ")
				                                .append("    and rownum = 1 ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(taskId);

		return jdbcDao.queryForMap(select.toString(), params);	
	}
	
    public void updateRecNoFromFastPath(String communicationId, int recordNo) 
	{		
		StringBuffer update = new StringBuffer().append("UPDATE ")
												.append(" SFFND_COMM  ")
												.append("SET ")  
												.append("UCF_COMM_NUM3 = ?, ")  // Record No
												.append(" UCF_COMM_DATE1 = "+ jdbcDao.getTimestampFunction() )
												.append(" WHERE COMM_ID = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(recordNo);
		params.addParameter(communicationId);

		jdbcDao.update(update.toString(), params);	
	}
    
    public Map getExptComm(String planId,
    		               Number planVersion,
    		               Number planRevision,
    		               Number planAlteration)
    {

    	StringBuffer selectSql = new StringBuffer().append("SELECT ")
    			                                   .append(" COMM_ID, ")
    			                                   .append(" COMM_STATUS, ")
    			                                   .append(" SUBJECT, ")
    			                                   .append(" UCF_COMM_FLAG3, ") // rtc flag
    			                                   .append(" UCF_COMM_NUM3 ") // eclass rec no
    			                                   .append(" FROM ")
    			                                   .append(" SFFND_COMM ")
    			                                   .append(" WHERE TASK_ID IN (SELECT TASK_ID FROM SFFND_PLG_TASK ")
    			                                   .append(" WHERE PLAN_ID = ? ")
    			                                   .append(" AND PLAN_VERSION = ? ")
    			                                   .append(" AND PLAN_REVISION = ? ")
    			                                   .append(" AND PLAN_ALTERATIONS = ?) ")
    			                                   .append(" AND COMM_STATUS = 'IN PROCESS' ")
    			                                   .append(" AND subject LIKE '%EXPT%' ");

    	ParameterHolder parameters = new ParameterHolder();
    	parameters.addParameter(planId);
    	parameters.addParameter(planVersion);
    	parameters.addParameter(planRevision);
    	parameters.addParameter(planAlteration);

    	Map commMap = null; 
    	try
    	{
    		// this can throw exception if the no match found for criteria, which is a valid result when the comm_status legitimately does not equal IN PROCESS 
    		commMap = jdbcDao.queryForMap(selectSql.toString(), parameters);
    	}
    	catch (Exception e)
    	{
    		// do nothing
    	}  		 
    	return commMap;	
    }

    public int getCommInfo(String commID, String agency){
    	
    	StringBuffer selectSql = new StringBuffer().append(" select count(comm_id) from sffnd_comm t ")
												   .append("  where t.comm_status = 'COMPLETE' ")
												   .append("    and t.updt_userid = ? ")
												   .append("    and t.ucf_comm_vch15 = ? ")
												   .append("    and t.task_id = (select t.task_id from sffnd_comm t ")
												   .append("                      where t.comm_id = ?) ");

		ParameterHolder parameters = new ParameterHolder();
			parameters.addParameter(ContextUtil.getUsername());
			parameters.addParameter(agency);
		    parameters.addParameter(commID);

		int count = jdbcDao.queryForInt(selectSql.toString(), parameters);
		return count;
    }
    
    public String getCommApprover(String commID){
    	StringBuffer selectSql = new StringBuffer().append("SELECT UCF_COMM_VCH15 ")
												   .append("  FROM sffnd_comm ")
												   .append(" WHERE comm_id = ? ");

		 ParameterHolder parameters = new ParameterHolder();
		    parameters.addParameter(commID);

		 Map commMap = jdbcDao.queryForMap(selectSql.toString(), parameters);
		 return (String) commMap.get("UCF_COMM_VCH15");
    }
    
	public void updateApprovalStatus(String communicationId, String userid) 
	{
		/*
		 UPDATE SFFND_COMM
		   SET UCF_COMM_VCH1 = 'SCOTT', UCF_COMM_DATE1 = sysdate
		 WHERE COMM_ID = 'PWUE_9FB13ED39939B87B5B1D41261DCFB2ED'
		 */
		StringBuffer update = new StringBuffer().append("UPDATE ")
												.append(" SFFND_COMM  ")
												.append("SET UCF_COMM_VCH1 = ?, ")
												.append("    UCF_COMM_DATE1 = "+ jdbcDao.getTimestampFunction())
												.append("WHERE COMM_ID = ?");
		
		ParameterHolder params = new ParameterHolder();
				params.addParameter(userid);
				params.addParameter(communicationId);

		jdbcDao.update(update.toString(), params);	
	}
	
	/*
	 * Java implementation of OOB sqlid CommReplySel 
	 */
	public String commReplySel(String commId)
	{
		/*SELECT TO_QUEUE
		   FROM SFFND_COMM_ROUTING B
		   WHERE COMM_ID = :COMM_ID      AND
		   REPLY_STATUS != 'COMPLETE'    AND
		   TO_QUEUE IN (SELECT PRIV
		                  FROM SFCORE_USER_PRIVS WHERE USERID =:@USERID    )*/
    	StringBuffer selectSql = new StringBuffer().append("SELECT TO_QUEUE ")
													.append("    FROM SFFND_COMM_ROUTING B ")
													.append(" WHERE COMM_ID = ?      AND ")
													.append(" REPLY_STATUS != 'COMPLETE'    AND ")
										            .append(" TO_QUEUE IN (SELECT PRIV ")
										            .append(" FROM SFCORE_USER_PRIVS WHERE USERID = ? ) ");

		 ParameterHolder parameters = new ParameterHolder();
		    parameters.addParameter(commId);
		    parameters.addParameter(ContextUtil.getUsername());

		 Map commMap = jdbcDao.queryForMap(selectSql.toString(), parameters);
		 return (String) commMap.get("TO_QUEUE");
	}

	public Map selectTaskId(String communicationId) 
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT A.TASK_ID, B.ITEM_SUBTYPE ")
													.append(" FROM SFFND_COMM A, SFFND_PLG_TASK B ")
													.append(" WHERE A.TASK_ID = B.TASK_ID ")
													.append(" AND A.COMM_ID = ?");
			
		ParameterHolder params = new ParameterHolder();
			params.addParameter(communicationId);
			
		Map commMap = jdbcDao.queryForMap(selectSql.toString(), params);
		
		return commMap;
	}

    public String getToQueue(String commID){
    	StringBuffer selectSql = new StringBuffer().append("SELECT ")
				.append(" TO_QUEUE ")
				.append(" FROM ")
				.append(" SFFND_COMM_ROUTING ")
	            .append(" WHERE ")
	            .append(" COMM_ID = ? ");

		 ParameterHolder parameters = new ParameterHolder();
		 parameters.addParameter(commID);

		 String toQueue = jdbcDao.queryForString(selectSql.toString(), parameters);
		 return toQueue;
    	
    }
    
    public String getRTCFlag(String commID){
    	StringBuffer selectSql = new StringBuffer().append("SELECT ")
				.append(" UCF_COMM_FLAG3 ")
				.append(" FROM ")
				.append(" SFFND_COMM ")
	            .append(" WHERE ")
	            .append(" COMM_ID = ? ");

		 ParameterHolder parameters = new ParameterHolder();
		 parameters.addParameter(commID);

		 String toQueue = jdbcDao.queryForString(selectSql.toString(), parameters);
		 return toQueue;
    	
    }
     
    public Map getCommStatus(String commID){
    	StringBuffer selectSql = new StringBuffer().append("SELECT ")
				.append(" COMM_STATUS, ")
				.append(" UCF_COMM_NUM3 ") // eclass rec no
				.append(" FROM ")
				.append(" SFFND_COMM ")
	            .append(" WHERE ")
	            .append(" COMM_ID = ? ");

		 ParameterHolder parameters = new ParameterHolder();
		 parameters.addParameter(commID);

		 Map commMap = jdbcDao.queryForMap(selectSql.toString(), parameters);
		 
		 return commMap;
    	
    }
    
         public Map getExptCompletedComm(String planId,
                 Number planVersion,
                 Number planRevision,
                 Number planAlteration){
         	StringBuffer selectSql = new StringBuffer().append("SELECT ")
         			.append(" COMM_ID, ")
     				.append(" COMM_STATUS, ")
     				.append(" SUBJECT, ")
     				.append(" UCF_COMM_FLAG3, ") // rtc flag
     				.append(" UCF_COMM_NUM3 ") // eclass rec no
     				.append(" FROM ")
     				.append(" SFFND_COMM ")
     	            .append(" WHERE TASK_ID IN (SELECT TASK_ID FROM SFFND_PLG_TASK ")
     	            .append(" WHERE PLAN_ID = ? ")
     	            .append(" AND PLAN_VERSION = ? ")
     	            .append(" AND PLAN_REVISION = ? ")
     	            .append(" AND PLAN_ALTERATIONS = ?) ")
     	            .append(" AND COMM_STATUS = 'COMPLETE' ")
     	            /*.append(" AND UCF_COMM_FLAG3 = 'Y' ")*/
     	            .append(" AND UCF_COMM_FLAG3 <> 'C' ")
     	            .append(" AND subject LIKE '%EXPT%' ");

     		 ParameterHolder parameters = new ParameterHolder();
     		 parameters.addParameter(planId);
     		 parameters.addParameter(planVersion);
     		 parameters.addParameter(planRevision);
     		 parameters.addParameter(planAlteration);

     		Map commMap = null; 
       		try
        	{
        	// this can throw exception if the no match found for criteria
       		   commMap = jdbcDao.queryForMap(selectSql.toString(), parameters);
        	}
        	catch (Exception e)
        	{
        	// do nothing
        	}

       		    		 
       		 return commMap;
     		   		 
         	
         }   
           
    public String getBaerClockId(String commID){
    	StringBuffer selectSql = new StringBuffer().append("SELECT ")
				.append(" UCF_COMM_VCH14 ")
				.append(" FROM ")
				.append(" SFFND_COMM ")
	            .append(" WHERE ")
	            .append(" COMM_ID = ? ");

		 ParameterHolder parameters = new ParameterHolder();
		 parameters.addParameter(commID);

		 String baerClockId = jdbcDao.queryForString(selectSql.toString(), parameters);
		 return baerClockId;
    	
    }
    
    public void updateJCInfo(String communicationId, String jurisdiction,
    		String classification, int sme, int recordNo, String certId) 
	{		
		StringBuffer update = new StringBuffer().append("UPDATE ")
												.append(" SFFND_COMM  ")
												.append("SET UCF_COMM_VCH8 = ?, ")  // Jurisdiction
												.append("    UCF_COMM_VCH9 = ?, ")  // Classification
												.append("    UCF_COMM_NUM2 = ?, ")  // SME
												.append("    UCF_COMM_NUM3 = ?, ")  // Record No
												.append("    UCF_COMM_VCH10 = ?, ")  // Cert Stmt ID
												.append("    UCF_COMM_DATE1 = "+ jdbcDao.getTimestampFunction() )
												.append(" WHERE COMM_ID = ?");
		
		ParameterHolder params = new ParameterHolder();
				params.addParameter(jurisdiction);
				params.addParameter(classification);
				params.addParameter(sme);
				params.addParameter(recordNo);
				params.addParameter(certId);
				params.addParameter(communicationId);

		jdbcDao.update(update.toString(), params);	
	}
    
    public boolean openRTCExists(String commId)
    {
    
	    StringBuffer select = new StringBuffer().append("select PWUE_EXPT_OPEN_RTC(?) from dual ");
	 
	    ParameterHolder params = new ParameterHolder();
	    params.addParameter(commId);
	        
	    String openRtc = jdbcDao.queryForString(select.toString(), params);
	    	    
	    if (openRtc.equals("TRUE"))
	        return true;
	    else 
	        return false;
    }
    
    
    public boolean openUEAExists(String commId, String rtcRequest)
    {
    
	    StringBuffer select = new StringBuffer().append("select PWUE_EXPT_OPEN_UEA(?, ?) from dual ");
	 
	    ParameterHolder params = new ParameterHolder();
	    params.addParameter(commId);
	    params.addParameter(rtcRequest);
	        
	    String openRtc = jdbcDao.queryForString(select.toString(), params);
	    	    
	    if (openRtc.equals("TRUE"))
	        return true;
	    else 
	        return false;
    }
    
    
    public boolean isCommOper(String commId)
    {
    	boolean commOper = false;
    	
    	StringBuffer select = new StringBuffer().append("SELECT R.PLAN_ID, ")
    											.append("       R.PLAN_VERSION, ")
    											.append("       R.PLAN_REVISION, ")
    											.append("       R.PLAN_ALTERATIONS, ")
    											.append("       T.PLAN_NO, ")
    											.append("       R.REV_STATUS ")
    											.append("  FROM SFPL_PLAN_DESC T, SFPL_PLAN_REV R ")
    											.append(" WHERE T.PLAN_ID = R.PLAN_ID ")
    											.append("   AND T.PLAN_UPDT_NO = R.PLAN_UPDT_NO ")
    											.append("   AND T.PLAN_NO = ")
    											.append("       (select distinct pwue_gettokenfromstdopertag(s.stdoper_tag, 1) as PLAN_NO ")
    											.append("          from sffnd_comm c, sffnd_plg_task pt, sffnd_stdoper s ")
    											.append("         where c.task_id = pt.task_id ")
    											.append("           and pt.plan_id = s.stdoper_plan_id ")
    											.append("           and c.comm_id = ?) ")
    											.append("   AND R.PLAN_REVISION = ")
    											.append("       (SELECT MAX(X.PLAN_REVISION) ")
    											.append("          FROM SFPL_PLAN_REV X ")
    											.append("         WHERE X.PLAN_ID = R.PLAN_ID ")
    											.append("           AND X.PLAN_VERSION = R.PLAN_VERSION) ");
    	
    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(commId);

    	List list = jdbcDao.queryForList(select.toString(), params);
    	
    	if (list.size() == 0)
    	{
    		commOper = false;
    		return commOper;
    	}
    	
    	if (list.size() > 0)
    	{
    		Map map = (Map) list.get(0);
    		String status = (String)map.get("REV_STATUS");
    		if (status.equals("PLAN COMPLETE") || status.equals("PLAN RELEASED"))
    		{
    			commOper = true;
    			return commOper;
    		}
    		else
    		{
    			commOper = false;
    			return commOper;
    		}
    	}
    	return commOper;
    }
    
    
    public void updateCommRTCFlag(String communicationId, String rtcFlag) 
	{
		
		StringBuffer update = new StringBuffer().append("UPDATE ")
												.append(" SFFND_COMM  ")
												.append("SET UCF_COMM_FLAG3 = ?, ")
												.append("    UCF_COMM_DATE1 = "+ jdbcDao.getTimestampFunction())
												.append("WHERE COMM_ID = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(rtcFlag);
		params.addParameter(communicationId);
		
		jdbcDao.update(update.toString(), params);	
	}

    public int exportBypassCount(String planId)
    {
    	StringBuffer sql = new StringBuffer().append("select count(t.task_id) as comm_count ")
    			.append("  from sffnd_plg_task t, sffnd_comm c ")
    			.append(" where t.task_id = c.task_id ")
    			.append("   and t.plan_id = ?  ")
    			.append("   and c.ucf_comm_vch15 = 'EXPT' ")
    			.append("   and c.ucf_comm_flag2 = 'Y' ");

    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(planId);

    	return jdbcDao.queryForInt(sql.toString(), params);
    }

    public void updateExptCompletedCommsOnCancel(String planId,
    		Number planVersion,
    		Number planRevision,
    		Number planAlteration)
    {
    	StringBuffer selectSql = new StringBuffer()
	    	.append(" UPDATE SFFND_COMM ")
	    	.append("    SET UCF_COMM_FLAG5 = 'C' ")
	    	.append("  WHERE COMM_ID in (SELECT COMM_ID ")
	    	.append("                     FROM SFFND_COMM ")
	    	.append("                    WHERE TASK_ID IN (SELECT TASK_ID ")
	    	.append("                                        FROM SFFND_PLG_TASK ")
	    	.append("                                       WHERE PLAN_ID = ? ")
	    	.append("                                         AND PLAN_VERSION = ? ")
	    	.append("                                         AND PLAN_REVISION = ? ")
	    	.append("                                         AND PLAN_ALTERATIONS = ?) ")
	    	.append("                      AND COMM_STATUS = 'COMPLETE' ")
	    	.append("                      AND NVL(UCF_COMM_FLAG5,?) != 'C' ")
	    	.append("                      AND UCF_COMM_VCH15 = 'EXPT') ");

    	ParameterHolder parameters = new ParameterHolder();
	    	parameters.addParameter(planId);
	    	parameters.addParameter(planVersion);
	    	parameters.addParameter(planRevision);
	    	parameters.addParameter(planAlteration);
	    	parameters.addParameter("X");	    	
	    	
    	jdbcDao.update(selectSql.toString(), parameters); 
    }  
    
    public List getPlanOperCommStatusAllAgencies(String planId,
									    	     Number planVersion,
									    	     Number planRevision,
									    	     Number planAlteration,
									    	     String whereClause)
    {
    	List list = null;
    	String newWhereClause;
    	
		StringBuffer selectSql = new StringBuffer().append("SELECT  ")
				.append("   'PLAN' as OPER_NO, R.PLAN_UPDT_NO as OPER_REV, X.PLAN_TITLE as OPER_TITLE, R.REV_STATUS as REL_STATUS, ")
				.append("   R.TIME_STAMP,Y1.COMM_STATUS as ME, Y2.COMM_STATUS as TMGR, Y3.COMM_STATUS as QA, Y4.COMM_STATUS as EXPT, ")
				.append("   Y5.COMM_STATUS as COAT, Y6.COMM_STATUS as HEAT, Y7.COMM_STATUS as SURF, Y8.COMM_STATUS as WELD, ")
				.append("   Y9.COMM_STATUS as MATL, Y10.COMM_STATUS as MCLP, Y11.COMM_STATUS as MCLQ, Y12.COMM_STATUS as QAR, ")
				.append("   Y13.COMM_STATUS as NDT, Y14.COMM_STATUS as QRC, Y15.COMM_STATUS as MRE, ")
				.append("   R.PLAN_ID, R.PLAN_VERSION, R.PLAN_REVISION, R.PLAN_ALTERATIONS ")
				.append(" FROM SFPL_PLAN_DESC X,SFPL_PLAN_REV R, ")
				.append("      (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
				.append("      FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
				.append("      WHERE UCF_COMM_VCH15 = 'ME' ")
				.append("      AND T1.TASK_ID = T2.TASK_ID) Y1, ")
				.append(" 	   (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
				.append(" 	   FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
				.append("      WHERE UCF_COMM_VCH15 = 'TMGR' ")
				.append("      AND T1.TASK_ID = T2.TASK_ID) Y2, ")
				.append("      (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
				.append("      FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
				.append("      WHERE UCF_COMM_VCH15 = 'QA' ")
				.append("      AND T1.TASK_ID = T2.TASK_ID) Y3, ")
				.append("      (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
				.append("      FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
				.append("      WHERE UCF_COMM_VCH15 = 'EXPT' ")
				.append("      AND T1.TASK_ID = T2.TASK_ID) Y4, ")
				.append("      (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
				.append("      FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ") 
				.append("      WHERE UCF_COMM_VCH15 = 'COAT' ")
				.append("      AND T1.TASK_ID = T2.TASK_ID) Y5, ")
				.append(" 	   (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
				.append(" 	   FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
				.append(" 	   WHERE UCF_COMM_VCH15 = 'HEAT' ")
				.append(" 	   AND T1.TASK_ID = T2.TASK_ID) Y6, ")
				.append(" 	   (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
				.append(" 	   FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
				.append(" 	   WHERE UCF_COMM_VCH15 = 'SURF' ")
				.append(" 	   AND T1.TASK_ID = T2.TASK_ID) Y7, ")
				.append(" 	   (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
				.append(" 	   FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
				.append(" 	   WHERE UCF_COMM_VCH15 = 'WELD' ")
				.append(" 	   AND T1.TASK_ID = T2.TASK_ID) Y8, ")
				.append(" 	   (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
				.append(" 	   FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
		        .append(" 	   WHERE UCF_COMM_VCH15 = 'MATL' ")
		        .append(" 	   AND T1.TASK_ID = T2.TASK_ID) Y9, ")
		        .append(" 	   (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
		        .append(" 	   FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
		        .append(" 	   WHERE UCF_COMM_VCH15 = 'MCLP' ")
		        .append(" 	   AND T1.TASK_ID = T2.TASK_ID) Y10, ")
		        .append(" 	   (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
		        .append(" 	   FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
		        .append(" 	   WHERE UCF_COMM_VCH15 = 'MCLQ' ")
		        .append(" 	   AND T1.TASK_ID = T2.TASK_ID) Y11, ")
		        .append(" 	   (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
		        .append(" 	   FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
		        .append(" 	   WHERE UCF_COMM_VCH15 = 'QAR' ")
		        .append(" 	   AND T1.TASK_ID = T2.TASK_ID) Y12, ")
		        .append("      (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
		        .append(" 	   FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
		        .append(" 	   WHERE UCF_COMM_VCH15 = 'NDT' ")
		        .append(" 	   AND T1.TASK_ID IN (SELECT TASK_ID FROM SFFND_PLG_TASK) ")
		        .append(" 	   AND T1.TASK_ID = T2.TASK_ID) Y13, ")
		        .append("      (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
		        .append(" 	   FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
		        .append(" 	   WHERE UCF_COMM_VCH15 = 'QRC' ")
		        .append(" 	   AND T1.TASK_ID = T2.TASK_ID) Y14, ")
		 		.append("      (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
		 		.append(" 	   FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
		 		.append(" 	   WHERE UCF_COMM_VCH15 = 'MRE' ")
		 		.append(" 	   AND T1.TASK_ID = T2.TASK_ID) Y15 ")
                .append(" 	   WHERE Y1.PLAN_ID(+)= R.PLAN_ID ")
                .append("      AND Y1.PLAN_VERSION(+) = R.PLAN_VERSION ")
			    .append("      AND Y1.PLAN_REVISION(+) = R.PLAN_REVISION ")
			    .append("      AND Y1.PLAN_ALTERATIONS(+) = R.PLAN_ALTERATIONS ")
 	 		    .append("      AND Y2.PLAN_ID(+) = R.PLAN_ID ")
		 	    .append("      AND Y2.PLAN_VERSION(+) = R.PLAN_VERSION ")
	 		    .append("      AND Y2.PLAN_REVISION(+) = R.PLAN_REVISION ")
			    .append("      AND Y2.PLAN_ALTERATIONS(+) = R.PLAN_ALTERATIONS ")
			    .append("      AND Y3.PLAN_ID(+)= R.PLAN_ID ")
			    .append("      AND Y3.PLAN_VERSION(+)= R.PLAN_VERSION ")
			    .append("      AND Y3.PLAN_REVISION(+) = R.PLAN_REVISION ")
			    .append("      AND Y3.PLAN_ALTERATIONS(+) = R.PLAN_ALTERATIONS ")
			    .append("      AND Y4.PLAN_ID(+)= R.PLAN_ID ")
			    .append("      AND Y4.PLAN_VERSION(+) = R.PLAN_VERSION ")
			    .append("      AND Y4.PLAN_REVISION(+) = R.PLAN_REVISION ")
			    .append("      AND Y4.PLAN_ALTERATIONS(+) = R.PLAN_ALTERATIONS ")
			    .append("      AND Y5.PLAN_ID(+)= R.PLAN_ID ")
			    .append("      AND Y5.PLAN_VERSION(+) = R.PLAN_VERSION ")
			    .append("      AND Y5.PLAN_REVISION(+) = R.PLAN_REVISION ")
			    .append("      AND Y5.PLAN_ALTERATIONS(+) = R.PLAN_ALTERATIONS ")
		  	    .append("      AND Y6.PLAN_ID(+)= R.PLAN_ID ")
			    .append("      AND Y6.PLAN_VERSION(+) = R.PLAN_VERSION ")
			    .append("      AND Y6.PLAN_REVISION(+) = R.PLAN_REVISION ")
			    .append("      AND Y6.PLAN_ALTERATIONS(+) = R.PLAN_ALTERATIONS ")
			    .append("      AND Y7.PLAN_ID(+)= R.PLAN_ID ")
			    .append("      AND Y7.PLAN_VERSION(+) = R.PLAN_VERSION ")
			    .append("      AND Y7.PLAN_REVISION(+) = R.PLAN_REVISION ")
			    .append("      AND Y7.PLAN_ALTERATIONS(+) = R.PLAN_ALTERATIONS ")
			    .append("      AND Y8.PLAN_ID(+)= R.PLAN_ID ")
			    .append("      AND Y8.PLAN_VERSION(+) = R.PLAN_VERSION ")
			    .append("      AND Y8.PLAN_REVISION(+) = R.PLAN_REVISION ")
			    .append("      AND Y8.PLAN_ALTERATIONS(+) = R.PLAN_ALTERATIONS ")
			    .append("      AND Y9.PLAN_ID(+)= R.PLAN_ID ")
			    .append("      AND Y9.PLAN_VERSION(+) = R.PLAN_VERSION ")
			    .append("      AND Y9.PLAN_REVISION(+) = R.PLAN_REVISION ")
			    .append("      AND Y9.PLAN_ALTERATIONS(+) = R.PLAN_ALTERATIONS ")
			    .append("      AND Y10.PLAN_ID(+)= R.PLAN_ID ")
			    .append("      AND Y10.PLAN_VERSION(+) = R.PLAN_VERSION ")
			    .append("      AND Y10.PLAN_REVISION(+) = R.PLAN_REVISION ")
			    .append("      AND Y10.PLAN_ALTERATIONS(+) = R.PLAN_ALTERATIONS ")
			    .append("      AND Y11.PLAN_ID(+)= R.PLAN_ID ")
			    .append("      AND Y11.PLAN_VERSION(+) = R.PLAN_VERSION ")
			    .append("      AND Y11.PLAN_REVISION(+) = R.PLAN_REVISION ")
			    .append("      AND Y11.PLAN_ALTERATIONS(+) = R.PLAN_ALTERATIONS ")
			    .append("      AND Y12.PLAN_ID(+)= R.PLAN_ID ")
			    .append("      AND Y12.PLAN_VERSION(+) = R.PLAN_VERSION ")
			    .append("      AND Y12.PLAN_REVISION(+) = R.PLAN_REVISION ")
			    .append("      AND Y12.PLAN_ALTERATIONS(+) = R.PLAN_ALTERATIONS ")
			    .append("      AND Y13.PLAN_ID(+)= R.PLAN_ID ")
			    .append("      AND Y13.PLAN_VERSION(+) = R.PLAN_VERSION ")
			    .append("      AND Y13.PLAN_REVISION(+) = R.PLAN_REVISION ")
			    .append("      AND Y13.PLAN_ALTERATIONS(+) = R.PLAN_ALTERATIONS ")
			    .append("      AND Y14.PLAN_ID(+)= R.PLAN_ID ")
			    .append("      AND Y14.PLAN_VERSION(+) = R.PLAN_VERSION ")
			    .append("      AND Y14.PLAN_REVISION(+) = R.PLAN_REVISION ")
			    .append("      AND Y14.PLAN_ALTERATIONS(+) = R.PLAN_ALTERATIONS ")
			    .append("      AND Y15.PLAN_ID(+)= R.PLAN_ID ")
			    .append("      AND Y15.PLAN_VERSION(+) = R.PLAN_VERSION ")
			    .append("      AND Y15.PLAN_REVISION(+) = R.PLAN_REVISION ")
			    .append("      AND Y15.PLAN_ALTERATIONS(+) = R.PLAN_ALTERATIONS ")
			    .append(" 	   AND R.PLAN_ID = X.PLAN_ID ")
			    .append(" 	   AND R.PLAN_UPDT_NO = X.PLAN_UPDT_NO ")
			    .append(" 	   AND R.PLAN_ID = ? ")
			    .append(" 	   AND R.PLAN_VERSION = ? ")
			    .append(" 	   AND R.PLAN_REVISION = ? ")
			    .append(" 	   AND R.PLAN_ALTERATIONS = ? ");
		
	    if(StringUtils.isNotEmpty(whereClause))
	   	{
	    	newWhereClause = whereClause.replaceFirst("\\(OPER_NO", "('PLAN'" );
			newWhereClause = newWhereClause.replaceFirst("\\(OPER_REV", "(R.PLAN_UPDT_NO" );
			newWhereClause = newWhereClause.replaceFirst("\\(OPER_TITLE", "(X.PLAN_TITLE" );
			newWhereClause = newWhereClause.replaceFirst("\\(REL_STATUS", "(R.REV_STATUS" );
			newWhereClause = newWhereClause.replaceFirst("\\(TIME_STAMP", "(R.TIME_STAMP" );
			newWhereClause = newWhereClause.replaceFirst("\\(ME", "(Y1.COMM_STATUS" );
			newWhereClause = newWhereClause.replaceFirst("\\(TMGR", "(Y2.COMM_STATUS" );
			newWhereClause = newWhereClause.replaceFirst("\\(QA", "(Y3.COMM_STATUS" );
			newWhereClause = newWhereClause.replaceFirst("\\(EXPT", "(Y4.COMM_STATUS" );
			newWhereClause = newWhereClause.replaceFirst("\\(COAT", "(Y5.COMM_STATUS" );
			newWhereClause = newWhereClause.replaceFirst("\\(HEAT", "(Y6.COMM_STATUS" );
			newWhereClause = newWhereClause.replaceFirst("\\(SURF", "(Y7.COMM_STATUS" );
			newWhereClause = newWhereClause.replaceFirst("\\(WELD", "(Y8.COMM_STATUS" );
			newWhereClause = newWhereClause.replaceFirst("\\(MATL", "(Y9.COMM_STATUS" );
			newWhereClause = newWhereClause.replaceFirst("\\(MCLP", "(Y10.COMM_STATUS" );
			newWhereClause = newWhereClause.replaceFirst("\\(MCLQ", "(Y11.COMM_STATUS" );
			newWhereClause = newWhereClause.replaceFirst("\\(QAR", "(Y12.COMM_STATUS" );
			newWhereClause = newWhereClause.replaceFirst("\\(NDT", "(Y13.COMM_STATUS" );
			newWhereClause = newWhereClause.replaceFirst("\\(QRC", "(Y14.COMM_STATUS" );
			newWhereClause = newWhereClause.replaceFirst("\\(MRE", "(Y15.COMM_STATUS" );

	   		selectSql.append(" AND " + newWhereClause  );
	   	}
	    
	   selectSql.append(" UNION ")
		        .append(" SELECT  ")
				.append("   OPER_NO, STDOPER_REV as OPER_REV, OPER_TITLE, STDOPER_STATUS as REL_STATUS,TIME_STAMP, ")
				.append("   Y1.COMM_STATUS as ME, Y2.COMM_STATUS as TMGR, Y3.COMM_STATUS as QA, Y4.COMM_STATUS as EXPT, ")
				.append("   Y5.COMM_STATUS as COAT, Y6.COMM_STATUS as HEAT, Y7.COMM_STATUS as SURF, Y8.COMM_STATUS as WELD, ")
				.append("   Y9.COMM_STATUS as MATL, Y10.COMM_STATUS as MCLP, Y11.COMM_STATUS as MCLQ, Y12.COMM_STATUS as QAR, ")
				.append("   Y13.COMM_STATUS as NDT, Y14.COMM_STATUS as QRC, Y15.COMM_STATUS as MRE, ")
				.append("   STDOPER_PLAN_ID, STDOPER_PLAN_VER,  STDOPER_PLAN_REV,  STDOPER_PLAN_ALT ")
				.append("   FROM(SELECT B1.OPER_NO,Z.STDOPER_REV,OPER_TITLE,Z.STDOPER_STATUS,A1.TIME_STAMP,Z.STDOPER_PLAN_ID,  ")
				.append("              Z.STDOPER_PLAN_VER,Z.STDOPER_PLAN_REV,Z.STDOPER_PLAN_ALT,SECURITY_GROUP ")
				.append("      FROM (SELECT G.STDOPER_REV, G.STDOPER_PLAN_ID, G.STDOPER_PLAN_VER,G.STDOPER_PLAN_REV,G.STDOPER_PLAN_ALT,  ")
				.append("                   G.STATUS as STDOPER_STATUS, SECURITY_GROUP ")
				.append("            FROM SFPL_OPERATION_REV B,SFPL_OPERATION_DESC A,SFPL_PLAN_REV D,SFPL_PLAN_DESC C, SFFND_STDOPER G ")
				.append("            WHERE A.PLAN_ID = B.PLAN_ID ")
				.append("            AND A.OPER_KEY = B.OPER_KEY ")
				.append("            AND A.OPER_UPDT_NO = B.OPER_UPDT_NO ")
				.append("            AND B.PLAN_ID = D.PLAN_ID ")
				.append("            AND B.PLAN_VERSION = D.PLAN_VERSION ")
				.append("            AND B.PLAN_REVISION = D.PLAN_REVISION ")
				.append("            AND B.PLAN_ALTERATIONS = D.PLAN_ALTERATIONS ")
				.append("            AND D.PLAN_ID = C.PLAN_ID ")
				.append("            AND D.PLAN_UPDT_NO = C.PLAN_UPDT_NO ")
				.append("            AND D.PLAN_ID = ? ")
				.append("            AND D.PLAN_VERSION = ? ")            
				.append("            AND D.PLAN_REVISION = ? ")
				.append("            AND D.PLAN_ALTERATIONS = ? ")
				.append("            AND G.STDOPER_OBJECT_ID = ")
				.append("            	(SELECT E.STDOPER_OBJECT_ID ")
				.append("                 FROM SFFND_STDOPER E ")
				.append("                 WHERE E.STDOPER_TAG = PWUE_GETSTDOPERTAG(C.PLAN_NO, C.PART_NO, A.OPER_NO) ")
				.append("                 AND E.STDOPER_REV = ")
				.append("                    (SELECT MAX(F.STDOPER_REV) ")
				.append("                     FROM SFFND_STDOPER F ")
				.append("                     WHERE E.STDOPER_PLAN_ID = F.STDOPER_PLAN_ID ")
				.append("                     AND E.STDOPER_PLAN_VER = F.STDOPER_PLAN_VER))) Z, ")
				.append("      SFPL_OPERATION_REV A1, SFPL_OPERATION_DESC B1 ")
				.append("      WHERE A1.PLAN_ID = Z.STDOPER_PLAN_ID ")
				.append("      AND A1.PLAN_VERSION = Z.STDOPER_PLAN_VER ")
				.append("      AND A1.PLAN_REVISION = Z.STDOPER_PLAN_REV ")
				.append("      AND A1.PLAN_ALTERATIONS = Z.STDOPER_PLAN_ALT ")
				.append("      AND A1.PLAN_ID = B1.PLAN_ID ")
				.append("      AND A1.OPER_UPDT_NO = B1.OPER_UPDT_NO ")
				.append("      AND A1.OPER_KEY = B1.OPER_KEY) X,  ")
				.append("      (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
				.append("      FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
				.append("      WHERE UCF_COMM_VCH15 = 'ME' ")
				.append("      AND T1.TASK_ID = T2.TASK_ID) Y1, ")
				.append(" 	   (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
				.append(" 	   FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
				.append("      WHERE UCF_COMM_VCH15 = 'TMGR' ")
				.append("      AND T1.TASK_ID = T2.TASK_ID) Y2, ")
				.append("      (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
				.append("      FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
				.append("      WHERE UCF_COMM_VCH15 = 'QA' ")
				.append("      AND T1.TASK_ID = T2.TASK_ID) Y3, ")
				.append("      (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
				.append("      FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
				.append("      WHERE UCF_COMM_VCH15 = 'EXPT' ")
				.append("      AND T1.TASK_ID = T2.TASK_ID) Y4, ")
				.append("      (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
				.append("      FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ") 
				.append("      WHERE UCF_COMM_VCH15 = 'COAT' ")
				.append("      AND T1.TASK_ID = T2.TASK_ID) Y5, ")
				.append(" 	   (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
				.append(" 	   FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
				.append(" 	   WHERE UCF_COMM_VCH15 = 'HEAT' ")
				.append(" 	   AND T1.TASK_ID = T2.TASK_ID) Y6, ")
				.append(" 	   (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
				.append(" 	   FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
				.append(" 	   WHERE UCF_COMM_VCH15 = 'SURF' ")
				.append(" 	   AND T1.TASK_ID = T2.TASK_ID) Y7, ")
				.append(" 	   (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
				.append(" 	   FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
				.append(" 	   WHERE UCF_COMM_VCH15 = 'WELD' ")
				.append(" 	   AND T1.TASK_ID = T2.TASK_ID) Y8, ")
				.append(" 	   (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
				.append(" 	   FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
		        .append(" 	   WHERE UCF_COMM_VCH15 = 'MATL' ")
		        .append(" 	   AND T1.TASK_ID = T2.TASK_ID) Y9, ")
		        .append(" 	   (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
		        .append(" 	   FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
		        .append(" 	   WHERE UCF_COMM_VCH15 = 'MCLP' ")
		        .append(" 	   AND T1.TASK_ID = T2.TASK_ID) Y10, ")
		        .append(" 	   (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
		        .append(" 	   FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
		        .append(" 	   WHERE UCF_COMM_VCH15 = 'MCLQ' ")
		        .append(" 	   AND T1.TASK_ID = T2.TASK_ID) Y11, ")
		        .append(" 	   (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
		        .append(" 	   FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
		        .append(" 	   WHERE UCF_COMM_VCH15 = 'QAR' ")
		        .append(" 	   AND T1.TASK_ID = T2.TASK_ID) Y12, ")
		        .append("      (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
		        .append(" 	   FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
		        .append(" 	   WHERE UCF_COMM_VCH15 = 'NDT' ")
		        .append(" 	   AND T1.TASK_ID IN (SELECT TASK_ID FROM SFFND_PLG_TASK) ")
		        .append(" 	   AND T1.TASK_ID = T2.TASK_ID) Y13, ")
		        .append("      (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
		        .append(" 	   FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
		        .append(" 	   WHERE UCF_COMM_VCH15 = 'QRC' ")
		        .append(" 	   AND T1.TASK_ID = T2.TASK_ID) Y14, ")
		 		.append("      (SELECT UCF_COMM_VCH15,COMM_STATUS,PLAN_ID,PLAN_VERSION,PLAN_REVISION,PLAN_ALTERATIONS ")
		 		.append(" 	   FROM SFFND_COMM T1, SFFND_PLG_TASK T2 ")
		 		.append(" 	   WHERE UCF_COMM_VCH15 = 'MRE' ")
		 		.append(" 	   AND T1.TASK_ID = T2.TASK_ID) Y15 ")
				.append(" 	   WHERE Y1.PLAN_ID(+)= X.STDOPER_PLAN_ID ")
				.append(" 	   AND Y1.PLAN_VERSION(+) = X.STDOPER_PLAN_VER ")
			    .append(" 	   AND Y1.PLAN_REVISION(+) = X.STDOPER_PLAN_REV ")
			    .append(" 	   AND Y1.PLAN_ALTERATIONS(+) = X.STDOPER_PLAN_ALT ")
			    .append(" 	   AND Y2.PLAN_ID(+) = X.STDOPER_PLAN_ID ")
			    .append(" 	   AND Y2.PLAN_VERSION(+) = X.STDOPER_PLAN_VER ")
			    .append(" 	   AND Y2.PLAN_REVISION(+) = X.STDOPER_PLAN_REV ")
				.append(" 	   AND Y2.PLAN_ALTERATIONS(+) = X.STDOPER_PLAN_ALT ")
				.append(" 	   AND Y3.PLAN_ID(+)= X.STDOPER_PLAN_ID ")
				.append(" 	   AND Y3.PLAN_VERSION(+)= X.STDOPER_PLAN_VER ")
				.append(" 	   AND Y3.PLAN_REVISION(+) = X.STDOPER_PLAN_REV ")
				.append(" 	   AND Y3.PLAN_ALTERATIONS(+) = X.STDOPER_PLAN_ALT ")
				.append(" 	   AND Y4.PLAN_ID(+)= X.STDOPER_PLAN_ID ")
				.append(" 	   AND Y4.PLAN_VERSION(+) = X.STDOPER_PLAN_VER ")
				.append(" 	   AND Y4.PLAN_REVISION(+) = X.STDOPER_PLAN_REV ")
				.append(" 	   AND Y4.PLAN_ALTERATIONS(+) = X.STDOPER_PLAN_ALT ")
				.append(" 	   AND Y5.PLAN_ID(+)= X.STDOPER_PLAN_ID ")
				.append(" 	   AND Y5.PLAN_VERSION(+) = X.STDOPER_PLAN_VER ")
				.append(" 	   AND Y5.PLAN_REVISION(+) = X.STDOPER_PLAN_REV ")
				.append(" 	   AND Y5.PLAN_ALTERATIONS(+) = X.STDOPER_PLAN_ALT ")
				.append(" 	   AND Y6.PLAN_ID(+)= X.STDOPER_PLAN_ID ")
				.append(" 	   AND Y6.PLAN_VERSION(+) = X.STDOPER_PLAN_VER ")
				.append(" 	   AND Y6.PLAN_REVISION(+) = X.STDOPER_PLAN_REV ")
				.append(" 	   AND Y6.PLAN_ALTERATIONS(+) = X.STDOPER_PLAN_ALT ")
				.append(" 	   AND Y7.PLAN_ID(+)= X.STDOPER_PLAN_ID ")
				.append(" 	   AND Y7.PLAN_VERSION(+) = X.STDOPER_PLAN_VER ")
				.append(" 	   AND Y7.PLAN_REVISION(+) = X.STDOPER_PLAN_REV ")
				.append(" 	   AND Y7.PLAN_ALTERATIONS(+) = X.STDOPER_PLAN_ALT ")
				.append(" 	   AND Y8.PLAN_ID(+)= X.STDOPER_PLAN_ID ")
				.append(" 	   AND Y8.PLAN_VERSION(+) = X.STDOPER_PLAN_VER ")
				.append(" 	   AND Y8.PLAN_REVISION(+) = X.STDOPER_PLAN_REV ")
				.append(" 	   AND Y8.PLAN_ALTERATIONS(+) = X.STDOPER_PLAN_ALT ")
				.append(" 	   AND Y9.PLAN_ID(+)= X.STDOPER_PLAN_ID ")
				.append(" 	   AND Y9.PLAN_VERSION(+) = X.STDOPER_PLAN_VER ")
				.append(" 	   AND Y9.PLAN_REVISION(+) = X.STDOPER_PLAN_REV ")
				.append(" 	   AND Y9.PLAN_ALTERATIONS(+) = X.STDOPER_PLAN_ALT ")
				.append(" 	   AND Y10.PLAN_ID(+)= X.STDOPER_PLAN_ID ")
				.append(" 	   AND Y10.PLAN_VERSION(+) = X.STDOPER_PLAN_VER ")
				.append(" 	   AND Y10.PLAN_REVISION(+) = X.STDOPER_PLAN_REV ")
				.append(" 	   AND Y10.PLAN_ALTERATIONS(+) = X.STDOPER_PLAN_ALT ")
				.append(" 	   AND Y11.PLAN_ID(+)= X.STDOPER_PLAN_ID ")
				.append(" 	   AND Y11.PLAN_VERSION(+) = X.STDOPER_PLAN_VER ")
				.append(" 	   AND Y11.PLAN_REVISION(+) = X.STDOPER_PLAN_REV ")
				.append(" 	   AND Y11.PLAN_ALTERATIONS(+) = X.STDOPER_PLAN_ALT ")
				.append(" 	   AND Y12.PLAN_ID(+)= X.STDOPER_PLAN_ID ")
				.append(" 	   AND Y12.PLAN_VERSION(+) = X.STDOPER_PLAN_VER ")
				.append(" 	   AND Y12.PLAN_REVISION(+) = X.STDOPER_PLAN_REV ")
				.append(" 	   AND Y12.PLAN_ALTERATIONS(+) = X.STDOPER_PLAN_ALT ")
				.append(" 	   AND Y13.PLAN_ID(+)= X.STDOPER_PLAN_ID ")
				.append(" 	   AND Y13.PLAN_VERSION(+) = X.STDOPER_PLAN_VER ")
				.append(" 	   AND Y13.PLAN_REVISION(+) = X.STDOPER_PLAN_REV ")
				.append(" 	   AND Y13.PLAN_ALTERATIONS(+) = X.STDOPER_PLAN_ALT ")
				.append(" 	   AND Y14.PLAN_ID(+)= X.STDOPER_PLAN_ID ")
				.append(" 	   AND Y14.PLAN_VERSION(+) = X.STDOPER_PLAN_VER ")
				.append(" 	   AND Y14.PLAN_REVISION(+) = X.STDOPER_PLAN_REV ")
				.append(" 	   AND Y14.PLAN_ALTERATIONS(+) = X.STDOPER_PLAN_ALT ")
				.append(" 	   AND Y15.PLAN_ID(+)= X.STDOPER_PLAN_ID ")
				.append(" 	   AND Y15.PLAN_VERSION(+) = X.STDOPER_PLAN_VER ")
				.append(" 	   AND Y15.PLAN_REVISION(+) = X.STDOPER_PLAN_REV ")
				.append(" 	   AND Y15.PLAN_ALTERATIONS(+) = X.STDOPER_PLAN_ALT ");
		
	    if(StringUtils.isNotEmpty(whereClause))
	   	{	
	    	newWhereClause = whereClause.replaceFirst("\\(OPER_REV", "(STDOPER_REV" );
	    	newWhereClause = newWhereClause.replaceFirst("\\(REL_STATUS", "(STDOPER_STATUS" );
	    	newWhereClause = newWhereClause.replaceFirst("\\(TIME_STAMP", "(TIME_STAMP" );
	    	newWhereClause = newWhereClause.replaceFirst("\\(ME", "(Y1.COMM_STATUS" );
	    	newWhereClause = newWhereClause.replaceFirst("\\(TMGR", "(Y2.COMM_STATUS" );
	    	newWhereClause = newWhereClause.replaceFirst("\\(QA", "(Y3.COMM_STATUS" );
	    	newWhereClause = newWhereClause.replaceFirst("\\(EXPT", "(Y4.COMM_STATUS" );
	    	newWhereClause = newWhereClause.replaceFirst("\\(COAT", "(Y5.COMM_STATUS" );
	    	newWhereClause = newWhereClause.replaceFirst("\\(HEAT", "(Y6.COMM_STATUS" );
	    	newWhereClause = newWhereClause.replaceFirst("\\(SURF", "(Y7.COMM_STATUS" );
	    	newWhereClause = newWhereClause.replaceFirst("\\(WELD", "(Y8.COMM_STATUS" );
	    	newWhereClause = newWhereClause.replaceFirst("\\(MATL", "(Y9.COMM_STATUS" );
	    	newWhereClause = newWhereClause.replaceFirst("\\(MCLP", "(Y10.COMM_STATUS" );
	    	newWhereClause = newWhereClause.replaceFirst("\\(MCLQ", "(Y11.COMM_STATUS" );
	    	newWhereClause = newWhereClause.replaceFirst("\\(QAR", "(Y12.COMM_STATUS" );
	    	newWhereClause = newWhereClause.replaceFirst("\\(NDT", "(Y13.COMM_STATUS" );
	    	newWhereClause = newWhereClause.replaceFirst("\\(QRC", "(Y14.COMM_STATUS" );
	    	newWhereClause = newWhereClause.replaceFirst("\\(MRE", "(Y15.COMM_STATUS" );
	    	
	   		selectSql.append(" AND " + newWhereClause  );
	   	}

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(planId);
		parameters.addParameter(planVersion);
		parameters.addParameter(planRevision);
		parameters.addParameter(planAlteration);
		parameters.addParameter(planId);
		parameters.addParameter(planVersion);
		parameters.addParameter(planRevision);
		parameters.addParameter(planAlteration);
		
		list = jdbcDao.queryForList(selectSql.toString(), parameters);
		
		if (list.isEmpty())
		{
	        list = new ArrayList();
	        Map returnMap = new HashMap();
	        returnMap.put("OPER_NO", "");
	        returnMap.put("OPER_REV", "");
	        returnMap.put("OPER_TITLE", "");
	        returnMap.put("REL_STATUS", "");
	        returnMap.put("TIME_STAMP", "");
	        returnMap.put("ME", "");
	        returnMap.put("TMGR", "");
	        returnMap.put("QA", "");
	        returnMap.put("EXPT", "");
	        returnMap.put("COAT", "");
	        returnMap.put("HEAT", "");
	        returnMap.put("SURF", "");
	        returnMap.put("WELD", "");
	        returnMap.put("MATL", "");
	        returnMap.put("MCLP", "");
	        returnMap.put("MCLQ", "");
	        returnMap.put("QAR", "");
	        returnMap.put("NDT", "");
	        returnMap.put("QRC", "");
	        returnMap.put("MRE", "");
	        returnMap.put("R.PLAN_ID", "");
	        returnMap.put("R.PLAN_VERSION", "");
	        returnMap.put("R.PLAN_REVISION", "");
	        returnMap.put("R.PLAN_ALTERATIONS", "");
	        list.add(returnMap);
		}

		return list;
				
    }	

    public boolean checkForCommApproval(String taskId,
    					   				String agency,
    					   				String userId)
    {	
    	StringBuffer selectSql = new StringBuffer().append("select count(comm_id) ")
												   .append("  from sffnd_comm t ")
												   .append(" where t.comm_status    = 'COMPLETE' ")
												   .append("   and t.ucf_comm_vch1  = ? ")
												   .append("   and t.ucf_comm_vch15 = ? ")
												   .append("   and t.task_id        = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(userId);
		parameters.addParameter(agency);
	    parameters.addParameter(taskId);

		int count = jdbcDao.queryForInt(selectSql.toString(), parameters);
	
		boolean communicationApproved = false;

		if (count > 0)
		{
			communicationApproved = true;
		}
		
		return communicationApproved;
    }

	public Map getPlanInfoFromCommID(String commID)
	{
    	StringBuffer selectSql = new StringBuffer().append(" SELECT t.plan_id, ")
				                                   .append("        t.plan_version, ")
				                                   .append("        t.plan_revision, ")
				                                   .append("        t.plan_alterations, ")
				                                   .append("        t.task_id ")
				                                   .append("   from sffnd_plg_task t ")
	                                               .append("  where t.task_id = (select t.task_id ")
	                                               .append("                       from sffnd_comm t ")
	                                               .append("                      where t.comm_id = ? ) ");
		
		 ParameterHolder parameters = new ParameterHolder();
		    parameters.addParameter(commID);

		 Map planMap = jdbcDao.queryForMap(selectSql.toString(), parameters);
		 return planMap;
    	
	}

	public String getRTCFlagForPlanExptTask(String planID, Number planUpdtNo)
    {	
		
		/*UCF_COMM_FLAG3 = rtc flag*/
		
		StringBuffer selectSQL = new StringBuffer().append("SELECT ")
				                                   .append("  UCF_COMM_FLAG3 ")
				                                   .append("FROM (SELECT UCF_COMM_FLAG3 FROM SFFND_COMM a, SFFND_PLG_TASK b ")
				                                   .append("WHERE b.PLAN_ID = ? ")
				                                   .append("  AND b.PLAN_UPDT_NO = ? ")
				                                   .append("  AND b.TASK_ID = a.TASK_ID ")
				                                   .append("  AND a.UCF_COMM_VCH15 = 'EXPT' ")
				                                   .append("  AND a.COMM_STATUS = 'COMPLETE' ")  
				                                   .append("ORDER BY a.UCF_COMM_DATE1 DESC) ")
				                                   .append(" WHERE ROWNUM = 1");
				
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planID);
		params.addParameter(planUpdtNo);
							
		String rtcFlag = jdbcDao.queryForString(selectSQL.toString(), params);
		return rtcFlag;		
	}

	public Map getInfoForPlanExptTask(String planID, Number planUpdtNo)
	{	
		StringBuffer selectSQL = new StringBuffer().append(" SELECT ")
				                                  .append("   COMM_ID, ")
				                                  .append("   UCF_COMM_VCH14, ")  // baer clock id
				                                  .append("   UCF_COMM_VCH8, ")   // jurisdiction
				                                  .append("   UCF_COMM_VCH9, ")   // classification
				                                  .append("   UCF_COMM_NUM2, ")   // sme number
				                                  .append("   UCF_COMM_NUM3, ")   // record number
				                                  .append("   UCF_COMM_FLAG3, ")  // rtc flag
				                                  .append("   UCF_COMM_VCH1, ")   // approver id (may be diff than the baer id)
				                                  .append("   UCF_COMM_VCH10, ")  // cert tag
				                                  .append("   UCF_COMM_DATE1 ")   // approval date
				                                  .append(" FROM ")
				                                  .append("     (SELECT COMM_ID, ")
				                                  .append("        UCF_COMM_VCH14, ")
				                                  .append("        UCF_COMM_VCH8, ")
				                                  .append("        UCF_COMM_VCH9, ")
				                                  .append("        UCF_COMM_NUM2, ")
				                                  .append("        UCF_COMM_NUM3, ")
				                                  .append("        UCF_COMM_FLAG3, ")
				                                  .append("        UCF_COMM_VCH1, ")
				                                  .append("        UCF_COMM_VCH10, ")
				                                  .append("        UCF_COMM_DATE1 ")
				                                  .append("   FROM SFFND_COMM a, ")
				                                  .append("        SFFND_PLG_TASK b ")
				                                  .append("   WHERE b.PLAN_ID = ? ")
				                                  .append("     AND b.PLAN_UPDT_NO = ? ")
				                                  .append("     AND b.TASK_ID = a.TASK_ID ")
				                                  .append("     AND a.UCF_COMM_VCH15 = 'EXPT' ")
				                                  .append("     AND a.COMM_STATUS = 'COMPLETE' ")
				                                  .append("     AND a.UCF_COMM_DATE1 IS NOT NULL ")
				                                  .append("   ORDER BY a.UCF_COMM_DATE1 DESC) ")
				                                  .append(" WHERE ROWNUM = 1");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(planID);
		params.addParameter(planUpdtNo);

		Map commInfo = jdbcDao.queryForMap(selectSQL.toString(), params);
		return commInfo;	
	}

	public Map getPlanInfoForCommunicationId(String communicationId)
    {		
		StringBuffer selectSQL = new StringBuffer().append("SELECT b.PLAN_ID, b.PLAN_UPDT_NO ")
				                                   .append("  FROM SFFND_COMM a, SFFND_PLG_TASK b ")
				                                   .append(" WHERE a.COMM_ID = ? ")
				                                   .append("   AND a.TASK_ID = b. TASK_ID");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(communicationId);
					
		Map planMap = jdbcDao.queryForMap(selectSQL.toString(), params);
		return planMap;
	}
}

