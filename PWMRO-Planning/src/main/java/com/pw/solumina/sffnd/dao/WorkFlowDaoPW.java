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
 *  File:    WorkFlowDaoPW.java
 * 
 *  Created: 
 * 
 *  Author: X002174
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2018-04-19		T. Phan		    Added Dat_col_id to refine search. Defect 702
 */
package com.pw.solumina.sffnd.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.solumina.sfor.application.ISfwidUndo;
import com.pw.solumina.sfor.dao.SfwidUndoDaoPW;

public class WorkFlowDaoPW
{
    protected final Log logger = LogFactory.getLog(SfwidUndoDaoPW.class);
    
    @Reference 
	private ExtensionDaoSupport eds = null;

    public boolean getWorkIds(String taskId, String whichId)
    {
    	boolean exists = false;
            StringBuffer updateSql = new StringBuffer().append(" SELECT " + whichId + " ")
    				.append(" from SFPL_OPERATION_DESC x, SFFND_STDOPER y, SFFND_PLG_TASK z ")
    				.append(" where z.task_id = ? ")
    				.append(" and z.plan_id = y.stdoper_plan_id ")
    				.append(" and z.plan_id = x.plan_id ")
    				.append(" and " + whichId + " is not null ");
                    
            ParameterHolder params = new ParameterHolder();
            params.addParameter(taskId); //Defect 743
            
            List retVal = eds.queryForList(updateSql.toString(), params);
            if (retVal.size() > 0) {
            	exists = true;
            }
            
            return exists;
    }
}