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
 *  File:    OrderAssembleTextDaoPW.java
 * 
 *  Created: 2017-08-30
 * 
 *  Author:  Rusty Cannon
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2017-11-23		C. Nguyen		Initial Release SMRO_QA_201 - Discrepancy
 *  								Extention of ibaset IHoldDao
 */

package com.pw.solumina.sfwid.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ibaset.common.ImplementationOf;
import com.ibaset.solumina.sfwid.dao.IHoldDao;
import com.pw.solumina.sfwid.dao.IHoldDaoPW;

import org.springframework.util.StringUtils;

import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.sql.ParameterHolder;

public abstract class HoldDaoImplPW  extends ImplementationOf<IHoldDao> implements IHoldDao, IHoldDaoPW 
{
	@Reference 
	private JdbcDaoSupport eds;
	
    /**
     * 
     * @param orderId
     * @param Ref1
     * @param Ref2
     * @return
     */
    public boolean selectHoldExistsForOrder( String orderId,
			 							  String Ref2,
			 							  String Ref3,
			 							  String stopType,
			 							  String holdType)
    {
    	int retVal = 0;
    	boolean holdExists = false;
    	
        StringBuilder selectSql = new StringBuilder().append("SELECT Count(HOLD_ID) ")
                                                   .append("FROM ")
                                                   .append("    SFWID_HOLDS ")
                                                   .append("WHERE ")
                                                   .append("    ORDER_ID = ? AND ")
                                                   .append("    HOLD_REF2 = ? AND")
                                                   .append("    HOLD_REF3 = ? AND")
                                                   .append("    STOP_TYPE = ? AND ")
                                                   .append("    HOLD_TYPE= ?  AND  ")
                                                   .append("    HOLD_STATUS IN (?, ?) ");

        ParameterHolder parameters = new ParameterHolder();

        parameters.addParameter(orderId);
        parameters.addParameter(Ref2);
        parameters.addParameter(Ref3);
        parameters.addParameter(stopType);
        parameters.addParameter(holdType);
        parameters.addParameter(SoluminaConstants.OPEN_STATUS);
        parameters.addParameter(SoluminaConstants.IN_WORK_STATUS);         
 
        retVal =  eds.queryForInt(selectSql.toString(), parameters);
        if (retVal > 0) {
        	holdExists = true;
        }
        
        return holdExists;
        
    }
    
    /**
     * 
     * @param orderId
     * @param discId
     * @param discLineNo
     * @return
     */
    public List getHoldList( String orderId,
			 				 String discId,
			 				 String discLineNo,
							 String stopType,
							 String holdType)
    {
        StringBuilder selectSql = new StringBuilder().append("SELECT HOLD_ID, ORDER_ID, OPER_NO ")
                                                   .append("FROM ")
                                                   .append("    SFWID_HOLDS ")
                                                   .append("WHERE ")
                                                   .append("    ORDER_ID = ? AND ")
                                                   .append("    HOLD_REF2 = ? AND")
                                                   .append("    HOLD_REF3 = ? AND")
                                                   .append("    STOP_TYPE = ? AND ")
                                                   .append("    HOLD_TYPE= ?  AND  ")
                                                   .append("    HOLD_STATUS IN (?, ?) ") ;

        ParameterHolder parameters = new ParameterHolder();

        parameters.addParameter(orderId);
        parameters.addParameter(discId);
        parameters.addParameter(discLineNo);
        parameters.addParameter(stopType);
        parameters.addParameter(holdType);
        parameters.addParameter(SoluminaConstants.OPEN_STATUS);
        parameters.addParameter(SoluminaConstants.IN_WORK_STATUS);        

        return eds.queryForList(selectSql.toString(), parameters);
    }
    
    /**
     * 
     * @param DiscId
     * @param Disc Line No
     * @return
     */
    public String selectOrderId( String discId,
			 							  Number discLineNo)
    {
    	String retVal = "";
    	
        StringBuilder selectSql = new StringBuilder().append("SELECT ORDER_ID ")
                                                   .append("FROM ")
                                                   .append("    SFQA_DISC_ITEM ")
                                                   .append("WHERE ")
                                                   .append("    DISC_ID = ? AND ")
                                                   .append("    DISC_LINE_NO = ?  ");

        ParameterHolder parameters = new ParameterHolder();

        parameters.addParameter(discId);
        parameters.addParameter(discLineNo);
 
        retVal =  eds.queryForString(selectSql.toString(), parameters);

        return retVal;
        
    }
    
	
	
}
