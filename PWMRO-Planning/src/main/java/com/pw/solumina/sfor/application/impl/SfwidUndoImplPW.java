/*
 *  This unpublished work, first created in 2019 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2018.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    SfwidUndoImplPW.java
 * 
 *  Created: 
 * 
 *  Author: 
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2019-02-28		Bob Preston     Initial revision.
 *  2019-04-11		B. Preston		SMRO_USR_203 Defect 1005 Added priv check.
 */
package com.pw.solumina.sfor.application.impl;

import static com.ibaset.common.FrameworkConstants.NOT_APPLICABLE;
import static com.ibaset.common.FrameworkConstants.YES;
import static com.ibaset.common.SoluminaConstants.COMPLETE;
import static com.ibaset.common.SoluminaConstants.DISABLE;
import static com.ibaset.common.SoluminaConstants.EXCLUDE;
import static com.ibaset.common.SoluminaConstants.EXCLUDED;
import static com.ibaset.common.SoluminaConstants.EXTERNAL_ORDER_RELEASE;
import static com.ibaset.common.SoluminaConstants.N;
import static com.ibaset.common.SoluminaConstants.OPERATION;
import static com.ibaset.common.SoluminaConstants.PENDING;
import static com.ibaset.common.SoluminaConstants.PERCENT_SIGN;
import static com.ibaset.common.SoluminaConstants.SCRAP_STATUS;
import static com.ibaset.common.SoluminaConstants.START;
import static com.ibaset.common.SoluminaConstants.WO_RELEASE;
import static com.ibaset.common.SoluminaConstants.Y;
import static com.ibaset.common.util.SoluminaUtils.stringEquals;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.StringUtils.defaultIfEmpty;
import static org.apache.commons.lang.StringUtils.equalsIgnoreCase;
import static org.apache.commons.lang.StringUtils.isEmpty;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.solumina.IValidator;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sfor.application.ISfwidUndo;
import com.ibaset.solumina.sfwid.application.IHold;
import com.ibaset.solumina.sfwid.dao.IHoldDao;

public abstract class SfwidUndoImplPW extends ImplementationOf<ISfwidUndo> implements ISfwidUndo
{
    protected final Log logger = LogFactory.getLog(SfwidUndoImplPW.class);
    
    
    @Reference private IEvent event;
    @Reference private IHold hold = null;
    @Reference private IHoldDao holdDao = null;
	@Reference private IValidator validator = null; // Defect 1005.

    
    // Begin defect 1005.
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ibaset.solumina.sfor.application.ISfwidUndo#authorizeAllWorkScopeOperations(java.lang.
     * String, java.lang.String)
     */
    public void authorizeAllWorkScopeOperations(String orderId, String notes)
    {
        String userName = ContextUtil.getUsername();
        
        try
        {
            // if there is an open hold for work scope
            Map holdInfo = holdDao.selectOrderWorkScopeHold(orderId);
            String holdId = (String) holdInfo.get("HOLD_ID");
            
            // check priv
            validator.checkUserPrivilege("WORK_SCOPE_AUTHORIZATION");
            
            StringBuffer returnNotes = new StringBuffer();
            returnNotes.append("Approved Scope Changes").append(" - ").append(userName);
            if (!isEmpty(notes))
            {
                returnNotes.append(" - ").append(defaultIfEmpty(notes, EMPTY));
            }
            
            // close the hold
            hold.closeHold(orderId, null, holdId, returnNotes.toString());
            
            event.refreshTool(); // FND-20072
        }
        // if there is no hold
        catch (EmptyResultDataAccessException erdae)
        {}
    }
    // End defect 1005.
}