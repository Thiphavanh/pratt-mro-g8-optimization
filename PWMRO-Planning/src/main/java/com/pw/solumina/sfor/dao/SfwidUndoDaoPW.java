/*
 *  This unpublished work, first created in 2018 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2018, 2019, 2020.  All rights reserved.  Information contained 
 *  herein is proprietary and confidential and improper use or disclosure 
 *  may result in civil and penal sanctions.
 *
 *  File:    SfwidUndoDaoPW.java
 * 
 *  Created: 
 * 
 *  Author: 
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2018-04-19		T. Phan		    Added Dat_col_id to refine search. Defect 702
 *  2018-05-02		T. Phan		    Added checkFlag. SMRO_WOE_206 - Defect 702
 *  2018-07-24		Bob Preston		SMRO_WOE_206, Defect 795 - Update data collection user id and time stamp.
 *  2019-09-05      B. Corson       Defect 1370 - Take Serial Number into account when getting checkFlag.
 *  2020-03-01      J DeNinno		Defect 1451 - add serialId parameter per David Miron notes in defect
 *  2020-11-19		S. Edgerly		SMRO_WOE_206 - Defect 941 - SCR001: Split Data collections into two different privs.
 */
package com.pw.solumina.sfor.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sfor.application.ISfwidUndo;
import com.pw.solumina.sfor.dao.SfwidUndoDaoPW;

public class SfwidUndoDaoPW
{
    protected final Log logger = LogFactory.getLog(SfwidUndoDaoPW.class);
    
    @Reference
    private ISfwidUndo sfwidUndoImpl;
    
    @Reference
    private IMessage message = null;
    
    @Reference 
	private ExtensionDaoSupport eds = null;

    public void firstTimeFlag(String orderId,
                              Number operationKey,
                              Number stepKey,
                              String dataCollectionId,
                              String serialId)
    {
            StringBuffer updateSql = new StringBuffer().append(" UPDATE SFWID_SERIAL_OPER_DAT_COL ")
    				.append(" SET UCF_SRL_OPER_DC_FLAG1 = 'N' ")
    				.append(" WHERE ORDER_ID = ? ")
    				.append(" AND OPER_KEY = ? ")
                    .append(" AND STEP_KEY = ? ")
                    .append(" AND DAT_COL_ID = ? ") //Defect 702
                    .append(" AND SERIAL_ID = ? "); //Defect 1451
                    
            ParameterHolder params = new ParameterHolder();
            params.addParameter(orderId);
            params.addParameter(operationKey);
            params.addParameter(stepKey);
            params.addParameter(dataCollectionId); //Defect 702
            params.addParameter(serialId); //defect 1451
            
            eds.update(updateSql.toString(), params);
    }
    public String checkFlag(String orderId,
            Number operationKey,
            Number stepKey,
            String dataCollectionId,
            String serialId)            // Defect 1370
    {
       StringBuffer updateSql = new StringBuffer().append(" SELECT UCF_SRL_OPER_DC_FLAG1 ")
	    .append(" FROM SFWID_SERIAL_OPER_DAT_COL ")
     	.append(" WHERE ORDER_ID = ? ")
     	.append(" AND OPER_KEY = ? ")
        .append(" AND STEP_KEY = ? ")
        .append(" AND DAT_COL_ID = ? ")  //Defect 702
        .append(" AND SERIAL_ID = ? ");  // Defect 1370
  
        ParameterHolder params = new ParameterHolder();
        params.addParameter(orderId);
        params.addParameter(operationKey);
        params.addParameter(stepKey);
        params.addParameter(dataCollectionId); //Defect 702
        params.addParameter(serialId);         // Defect 1370

        return eds.queryForString(updateSql.toString(), params);
    }

    // Begin defect 795
    public void updateUserId(String orderId,
            Number operationKey,
            Number stepKey,
            String dataCollectionId,
            String userId,
            String serialId) //defect 1451
    {
    	StringBuffer updateSql = new StringBuffer().append("UPDATE SFWID_SERIAL_OPER_DAT_COL ") //DEFECT 1451 change table that is updated from SFWID_OPER_DAT_COL PER David Miron
    			.append(" SET UPDT_USERID = ?, ")
    			.append("     TIME_STAMP = SYSDATE ")
    			.append(" WHERE ORDER_ID = ? ")
    			.append(" AND OPER_KEY = ? ")
    			.append(" AND STEP_KEY = ? ")
    			.append(" AND DAT_COL_ID = ? ")
    			.append(" AND SERIAL_ID = ? "); //add defect 1451
  
    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(userId);
    	params.addParameter(orderId);
    	params.addParameter(operationKey);
    	params.addParameter(stepKey);
    	params.addParameter(dataCollectionId);
    	params.addParameter(serialId); //add defect 1451

    	eds.update(updateSql.toString(), params);
    }
    // End defect 795
    // Begin Defect 1370
    public String getSerialId(String orderId, String serialNo)
    {
        StringBuffer selectSql = new StringBuffer().append("SELECT SERIAL_ID ")
                                                   .append("  FROM SFMFG.SFWID_SERIAL_DESC ")
                                                   .append(" WHERE ORDER_ID = ? ")
                                                   .append("   AND SERIAL_NO = ? ");
        
        ParameterHolder params = new ParameterHolder();
        params.addParameter(orderId);
        params.addParameter(serialNo);
        
        return eds.queryForString(selectSql.toString(), params);
    }
    // End Defect 1370
    
    //Defect 941
    public List getSerialOperDatColData(String orderId, Number operKey, Number stepKey, String datColId, String serialId){

    	StringBuffer selectSql = new StringBuffer().append("SELECT * ")
                .append("  FROM SFWID_SERIAL_OPER_DAT_COL ")
                .append(" WHERE ORDER_ID = ? ")
                .append("   AND OPER_KEY = ? ")
                .append("   AND STEP_KEY = ? ")
                .append("   AND DAT_COL_ID  = ? ")
                .append("   AND SERIAL_ID = ? ")
                .append("   AND LOT_ID = (SELECT LOT_ID FROM SFWID_LOT_DESC WHERE ORDER_ID = ? ) ");
    
	    ParameterHolder params = new ParameterHolder();
	    params.addParameter(orderId);
	    params.addParameter(operKey);
	    params.addParameter(stepKey);
	    params.addParameter(datColId);
	    params.addParameter(serialId);
	    params.addParameter(orderId);
	
	    return eds.queryForList(selectSql.toString(), params);
    }
    //End Defect 941
    
}