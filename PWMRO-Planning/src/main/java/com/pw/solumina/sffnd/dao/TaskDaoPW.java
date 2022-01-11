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
*  File:    TaskDaoPW.java
* 
*  Created: 2017-09-06
* 
*  Author:  c079222
* 
*  Revision History
* 
*  Date      	Who             Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-09-06 	c079222		    SRMO_EXPT_201   Initial Release 
* 2017-11-05    D.Miron         SMRO_APP_203    Updated for email notifications. Added method selectTaskSubmitter.
*/
package com.pw.solumina.sffnd.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;

public class TaskDaoPW {
	
	@Reference
	private JdbcDaoSupport jdbcDao;
	
    /** 
     * SMRO_EXPT_201 - Oct 13, 2017
     * 
     * @author c079222 - David Miron
     *
     * @param taskId
     * @return
     */
    public Map selectPlanningTaskInformation(String taskId)
    {
        StringBuffer c1 = new StringBuffer().append("SELECT ")
                                            .append("    CHANGE_DOC_TYPE, ")
                                            .append("    CHANGE_DOC_ID, ")
                                            .append("    A.PLAN_ID, ")
                                            .append("    A.PLAN_VERSION, ")
                                            .append("    A.PLAN_REVISION, ")
                                            .append("    A.PLAN_ALTERATIONS, ")
                                            .append("	 C.DOC_TYPE, ")
                                            .append("    C.PLAN_TYPE, ")
                                            .append("    C.WORK_FLOW, ")
                                            .append("    B.REV_STATUS, ")
                                            .append("    C.PART_NO, ") 
                                            .append("    C.PART_CHG, ")
                                            .append("    C.PLAN_UPDT_NO, ")
                                            .append("    C.UCF_PLAN_VCH255_1 AS NOTIFICATION_MATRIX ")
                                            .append("FROM ")
                                            .append("    SFFND_PLG_TASK A, ")
                                            .append("    SFPL_PLAN_REV B, ")
                                            .append("    SFPL_PLAN_DESC C ")
                                            .append("WHERE ")
                                            .append("    TASK_ID = ? AND ")
                                            .append(" 	 A.PLAN_ID = B.PLAN_ID AND ")
                                            .append(" 	 A.PLAN_VERSION = B.PLAN_VERSION AND ")
                                            .append(" 	 A.PLAN_REVISION = B.PLAN_REVISION AND ")
                                            .append(" 	 A.PLAN_ALTERATIONS = B.PLAN_ALTERATIONS AND ")
                                            .append(" 	 B.PLAN_ID = C.PLAN_ID AND ")
                                            .append(" 	 B.PLAN_UPDT_NO = C.PLAN_UPDT_NO ");                                            
        
        ParameterHolder params = new ParameterHolder();
        params.addParameter(taskId);
        
        return jdbcDao.queryForMap(c1.toString(), params);
    }
    
    /** 
     * SMRO_EXPT_201 - Nov 5, 2017
     * 
     * @author c079222 - David Miron
     *
     * @param planId
     * @param planVersion
     * @param planRevision
     * @param planAlterations
     * @return
     */
    public String selectTaskSubmitter(String planId, Number planVersion, Number planRevision, Number planAlterations)
    {  
    	String submitter = null;
   
    	StringBuffer selectSql = new StringBuffer().append("SELECT UPDT_USERID ")
    											   .append("  FROM (SELECT B.UPDT_USERID ")
    											   .append("          FROM SFFND_PLG_TASK A, SFFND_TASK B ")
    											   .append("         WHERE A.TASK_ID = B.TASK_ID ")
    											   .append("           AND A.PLAN_ID = ? ")
    											   .append("           AND A.PLAN_VERSION = ? ")
    											   .append(" 		   AND A.PLAN_REVISION = ? ")
    											   .append("           AND A.PLAN_ALTERATIONS = ? ")
    											   .append("           AND B.QUEUE_TYPE = 'PLG_AUTHORING' ")
    											   .append("         ORDER BY A.TIME_STAMP DESC) ")
    											   .append(" WHERE ROWNUM = 1 ");

    	ParameterHolder parameters = new ParameterHolder();
    	parameters.addParameter(planId);
    	parameters.addParameter(planVersion);
    	parameters.addParameter(planRevision);
    	parameters.addParameter(planAlterations);

    	List list = jdbcDao.queryForList(selectSql.toString(), parameters);
    	if(list.size() > 0) 
    	{
    		Map map = (Map) list.get(0);
    		submitter = (String) map.get("UPDT_USERID");
    	}

    	return submitter;
    }
}
