/**
 * Proprietary and Confidential
 * Copyright 1995-2017 iBASEt, Inc.
 * Unpublished-rights reserved under the Copyright Laws of the United States
 * US Government Procurements:
 * Commercial Software licensed with Restricted Rights.
 * Use, reproduction, or disclosure is subject to restrictions set forth in
 * license agreement and purchase contract.
 * iBASEt, Inc. 27442 Portola Parkway, Suite 300, Foothill Ranch, CA 92610
 *
 * Solumina software may be subject to United States Dept of Commerce Export Controls.
 * Contact iBASEt for specific Expert Control Classification information.
 * 
 *  File:    AlterationImplPW.java
 * 
 *  Created: 2019-08-08
 * 
 *  Author:  Thien-Long Phan
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2019-08-08		T.Phan		Initial Release defect 468 - remove ORDER_AUTHORING priv for releasing sub/supp orders
 */
package com.pw.solumina.sfwid.application.impl;

import static com.ibaset.common.DbColumnNameConstants.*;
import static com.ibaset.common.FrameworkConstants.*;
import static com.ibaset.common.SoluminaConstants.*;
import static com.ibaset.common.util.SoluminaUtils.stringEquals;
import static org.apache.commons.lang.StringUtils.*;
import static org.apache.commons.lang.math.NumberUtils.INTEGER_MINUS_ONE;
import static org.apache.commons.lang.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang.math.NumberUtils.INTEGER_ZERO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;

import com.ibaset.common.DbColumnNameConstants;
import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.client.SoluminaServiceLocator;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.solumina.IValidator;
import com.ibaset.common.util.LanguageUtils;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sfcore.application.IUser;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.application.IHistoryControl;
import com.ibaset.solumina.sffnd.application.IHtReferenceText;
import com.ibaset.solumina.sffnd.application.IParameters;
import com.ibaset.solumina.sffnd.application.IParse;
import com.ibaset.solumina.sffnd.application.IPlanObject;
import com.ibaset.solumina.sffnd.application.IQueue;
import com.ibaset.solumina.sffnd.application.ITask;
import com.ibaset.solumina.sffnd.dao.IDocumentTypeDao;
import com.ibaset.solumina.sffnd.dao.ITaskDao;
import com.ibaset.solumina.sfor.dao.IFndDao;
import com.ibaset.solumina.sfpl.dao.IItemDao;
import com.ibaset.solumina.sfpl.dao.IPlanDao;
import com.ibaset.solumina.sfqa.application.IDiscrepancy;
import com.ibaset.solumina.sfwid.application.IAlteration;
import com.ibaset.solumina.sfwid.application.IAlterationDiscrepancy;
import com.ibaset.solumina.sfwid.application.IAlterationGet;
import com.ibaset.solumina.sfwid.application.IAlterationValidator;
import com.ibaset.solumina.sfwid.application.IBuildPart;
import com.ibaset.solumina.sfwid.application.IHold;
import com.ibaset.solumina.sfwid.application.INode;
import com.ibaset.solumina.sfwid.application.IOfd;
import com.ibaset.solumina.sfwid.application.IOfdNeedsReview;
import com.ibaset.solumina.sfwid.application.IOfdRearrange;
import com.ibaset.solumina.sfwid.application.IOrder;
import com.ibaset.solumina.sfwid.application.IOrderAlteration;
import com.ibaset.solumina.sfwid.application.IOrderDetails;
import com.ibaset.solumina.sfwid.application.IWorkflow;
import com.ibaset.solumina.sfwid.dao.IAlterationDao;
import com.ibaset.solumina.sfwid.dao.IBuyoffDao;
import com.ibaset.solumina.sfwid.dao.IOfdDao;
import com.ibaset.solumina.sfwid.dao.IOfdWorkOrderDao;
import com.ibaset.solumina.sfwid.dao.IOperationDao;
import com.ibaset.solumina.sfwid.dao.IOrderDao;
import com.ibaset.solumina.type.impl.PlanChange;
import com.ibaset.solumina.type.impl.PlanType;

public class AlterationImplPW implements IAlteration
{
    static Log logger = LogFactory.getLog(AlterationImplPW.class);
    
    @Reference
    private IAlterationDao alterationDao;
    @Reference
    private IAlterationDiscrepancy alterationDiscrepancy;
    @Reference
    private IFndDao fndDao;
    @Reference
    private ITaskDao fndTaskDao;
    @Reference
    private IHistoryControl historyControl;
    @Reference
    private IHold hold;
    @Reference
    private IHtReferenceText htReferenceText;
    @Reference
    private IMessage message;
    @Reference
    private IOfd ofd;
    @Reference
    private IOfdWorkOrderDao ofdWorkOrderDao;
    @Reference
    private IOperationDao operationDao;
    @Reference
    private IOrder order;
    @Reference
    private IOrderDao orderDao;
    @Reference
    private IOrderDetails orderDetails;
    @Reference
    private IPlanObject planObject;
    @Reference
    private IUser privilege;
    @Reference
    private IQueue queue;
    @Reference
    private ITask task;
    @Reference
    private IValidator validator;
    @Reference
    private IWorkflow workflow;
    @Reference
    private IBuildPart buildPart;
    @Reference
    private com.ibaset.solumina.sfqa.dao.IDiscrepancyDao qaDiscrepancyDao;
    @Reference
    private IItemDao itemDao;
    @Reference
    private IEvent event;
    @Reference
    private IOfdDao ofdDao;
    @Reference
    private IDocumentTypeDao documentTypeDao;
    @Reference
    private IPlanDao planDao;

    @Reference
    private IParameters parameters;
    @Reference
    private IDiscrepancy discrepancy;
    @Reference
    private IBuyoffDao buyoffDao;
    
    @Reference
    private IParse parse;

    @Reference
    private IOrderAlteration orderAlteration;
    @Reference
	private INode node;
    @Reference
    private IAlterationValidator alterationValidator;
    @Reference
	private IAlterationGet alterationGet;
    @Reference
    private IOfdNeedsReview ofdNeedsReview;
	@Reference
	private IOfdRearrange ofdRearrange;
    
    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfwid.application.IAlteration#assignAlterationPart(java.lang.String,
     *      java.lang.Number, java.lang.Number, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String)
     */
    public void assignAlterationPart(String orderId,
                                     Number operationKey,
                                     Number stepKey,
                                     String plannedItemId,
                                     String assignedItemId,
                                     String referenceDesignator,
                                     String assignmentFlag)
    {
    	assignAlterationPart(orderId, 
    						 operationKey, 
    						 stepKey, 
    						 null, 
    						 plannedItemId, 
    						 assignedItemId,
    						 referenceDesignator, 
    						 assignmentFlag);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfwid.application.IAlteration#assignAlterationPart(java.lang.String,
     *      java.lang.Number, java.lang.Number, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String)
     */
    public void assignAlterationPart(String orderId,
                                     Number operationKey,
                                     Number stepKey,
                                     String partDataCollectionId,
                                     String plannedItemId,
                                     String assignedItemId,
                                     String referenceDesignator,
                                     String assignmentFlag)

    {
    	this.assignAlterationPart(orderId,
	             				  operationKey,
	             				  stepKey,
	             				  partDataCollectionId,
	             				  plannedItemId,
	             				  assignedItemId,
	             				  referenceDesignator,
	             				  assignmentFlag,
	             				  null,
	             				  null,
	             				  PART);
    }
    
    //FND-22656
    public void assignAlterationPart(String orderId,
						             Number operationKey,
						             Number stepKey,
						             String itemDataCollectionId,
						             String plannedItemId,
						             String assignedItemId,
						             String referenceDesignator,
						             String assignmentFlag,
						             String itemNo,
						             String itemRev,
						             String itemType)
    {
    	if(stringEquals(itemType, PART))
    	{
	        // Checks user has Alternate Part Assignment privileges.
	        validator.checkUserPrivilege("ORDER_ALTPART_UPDATE");
	
	        // FND-13953
	        //Check Obsolete flag
	        String obsoleteFlag = itemDao.selectObsoleteFlag(assignedItemId);
	        
	        if(stringEquals(obsoleteFlag, YES))
	        {
	        	// Item No and Item Rev has been marked as Obsolete
	        	message.raiseError("MFI_1127");
	        }
	        
	        /** * START Nikunj ** */
	        // 11/17/2006 Prasad FND-5567.if the assigned part is a standard part
	        // raise error.
	        // Selects the Standard Part Flag.
	        String standardPartFlag = alterationDao.selectStandardPartFlag(assignedItemId);
	
	        // When Standard Part Flag is 'Y' and Assignment Flag is 'Y'.
	        if (stringEquals(standardPartFlag, YES)
	                && stringEquals(assignmentFlag, YES))
	        {
	            // Raises an error.
	            message.raiseError("MFI_227860B6EFB16B2EE0440003BA041A64");
	        }
	        /** * END Nikunj ** */
	
	        // Updates the Assigned Item Flag.
	        alterationDao.updateAssignedItemFlag(assignmentFlag,
	                                             orderId,
	                                             operationKey,
	                                             stepKey,
	                                             itemDataCollectionId,//FND-13188
	                                             assignedItemId);
    	}
    	
    	else
    	{
    		// Checks user has Alternate Part Assignment privileges.
	        validator.checkUserPrivilege("ORDER_ALTPART_UPDATE");
	
	        String itemId = itemDao.selectItemId(itemNo, itemRev);
	        
	        // FND-13953
	        //Check Obsolete flag
	        String obsoleteFlag = itemDao.selectObsoleteFlag(itemId);
	        
	        if(stringEquals(obsoleteFlag, YES))
	        {
	        	// Item No and Item Rev has been marked as Obsolete
	        	message.raiseError("MFI_1127");
	        }
	        
	        if(stringEquals(itemType, TOOL))
	        {
	        	// Updates the Assigned Item Flag.
	        	alterationDao.updateAssignedItemFlag(assignmentFlag,
	                                             	 orderId,
	                                             	 operationKey,
	                                             	 stepKey,
	                                             	 itemDataCollectionId,//FND-13188
	                                             	 itemNo,
	                                             	 itemRev,
	                                             	 itemType);
	        }
    	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfwid.application.IAlteration#excludeFromAlteration(java.lang.String,
     *      java.lang.String)
     */
    public void excludeFromAlteration(String targetOrderId, String sourceOrderId)
    {
        String holdId = null;
        String holdReference1 = null;
        String targetOrderNumber = null;

        try
        {
            Map row1 = orderDao.selectOrderDetails(targetOrderId);

            targetOrderNumber = (String) row1.get("ORDER_NO");
        }
        catch (EmptyResultDataAccessException erdae)
        {

        }

        Map row2 = alterationDao.selectHoldIdReference1(targetOrderId);

        if (row2.isEmpty())
        {
            message.raiseError("MFI_61", targetOrderNumber); // Message Text:
            // Work
            // order %V1 is not
            // Included to
            // Alteration
            // Propagation.
        }

        holdId = (String) row2.get("HOLD_ID");
        holdReference1 = (String) row2.get("HOLD_REF1");

        Map row = alterationDao.selectOrderNumber(sourceOrderId, holdReference1);

        if (row.isEmpty())
        {
            message.raiseError("MFI_61", targetOrderNumber); // Message Text:
            // Work
            // order %V1 is not
            // Included to
            // Alteration
            // Propagation.
        }

        hold.closeHold(targetOrderId,
                       null,
                       holdId,
                       null,
                       ALT_PROP,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfwid.application.IAlteration#getAlteration(java.lang.String)
     */
    public String getAlteration(String orderId)
    {
        return htReferenceText.getAlteration(orderId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfwid.application.IAlteration#getAlteration(java.lang.String,
     *      java.lang.Number)
     */
    public String getAlteration(String orderId, Number operationKey)
    {
        return htReferenceText.getAlteration(orderId, operationKey);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfwid.application.IAlteration#getAlterationPropHoldReference1(java.lang.String)
     */
    public String getAlterationPropagationHoldReference1(String orderId)
    {
        String holdReference1 = null;

        Map holdSet = alterationDao.selectHoldIdReference1(orderId);

        holdReference1 = (String) holdSet.get("HOLD_REF1");

        return holdReference1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfwid.application.IAlteration#getAlterationPropHoldType(java.lang.String)
     */
    public String getAlterationPropHoldType(String orderId)
    {
        String holdType = null;

        Map row = alterationDao.selectHoldType(orderId);

        holdType = (String) row.get("HOLD_TYPE");

        return holdType;
    }
    
    /*
     * (non-Javadoc)
     * @see com.ibaset.solumina.sfwid.application.IAlteration#alterationOrderOk(java.lang.String, java.lang.String, java.lang.String)
     */
    public void alterationOrderOk(String orderId, 
			  					  String editMode,
			  					  String calledFrom)
	{
		alterationValidator.alterationOrderOk(orderId, editMode, calledFrom);
	}
    
    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfwid.application.IAlteration#holdAlteration(java.lang.String,
     *      java.lang.String)
     */
    public void holdAlteration(String orderId,
                               String alterationId,
                               String alterationType,
                               String operationNumberList)

	{
		orderAlteration.holdAlteration(orderId, alterationId, alterationType, null, operationNumberList, null);
	}

    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfwid.application.IAlteration#includeInAlteration(java.lang.String)
     */
    public void includeInAlteration(String orderId)
    {
    	orderAlteration.includeInAlteration(orderId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfwid.application.IAlteration#includeInAltProp(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public void includeInAlterationProp(String targetOrderId,
                                        String orderNumber,
                                        String alterationId)
    {
        /** * Start Ankur ** */
        includeInAlterationProp(targetOrderId, orderNumber);
        /** * End Ankur ** */
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfwid.application.IAlteration#includeInAlterationProp(java.lang.String,
     *      java.lang.String)
     */
    public void includeInAlterationProp(String targetOrderId, String orderNumber)
    {
        String holdId = null;

        Map holdRow = alterationDao.selectHoldId(targetOrderId);

        holdId = (String) holdRow.get("HOLD_ID");

        if (!isEmpty(holdId))
        {
            message.raiseError("MFI_63",
                               orderDetails.getOrderNumber(targetOrderId)); // Message
        }

        
        // 08/04/2006 Prasad FND-5081.raise proper message if the work order is
        // in OPERATION
        // Level alteration.
        Map orderDescriptionMap = operationDao.selectOrderInformation(orderNumber);

        String orderId = (String) orderDescriptionMap.get("ORDER_ID");
        String alterationStatus = (String) orderDescriptionMap.get("ALT_STATUS");

        // 08/18/2010 FND-12084 Changed the order of error raise.
        // Gets the Source Alteration Type.
        String sourceAlterationType = getType(orderId);

        // When Source Alteration Type is 'OPERATION'.
        if (stringEquals(sourceAlterationType,
                               upperCase(OPERATION)))
        {
            // Cannot include other work orders for OPERATION level alterations
            message.raiseError("MFI_1A34937F72CC21C6E0440003BA560E35");
        }

        // 06/17/2009 Utpal FND-10798
        // Do not allow to include any work order if the source work order is
        // included into alteration due to Disposition.
        if (contains(sourceAlterationType, DISP))
        {
        	// GE-5512
        	// Cannot include work order(%V1) in alteration when alteration type is Disposition on source work order
            message.raiseError("MFI_6C8A908F00EF48D2E0440003BA041A64",
                               orderDetails.getOrderNumber(targetOrderId));
        }

        // 08/10/2006 Rujuta Jira# FND-5108 Stops from including WO in OPER
        // level alteration
        // Gets the Target Alteration Type.
        String targetAlterationType = getType(targetOrderId);
        String targetOrderNumber = orderDetails.getOrderNumber(targetOrderId);
        // When Target Alteration Type is 'OPERATION'.
        if (stringEquals(targetAlterationType,
                               upperCase(OPERATION)))
        {
            // Cannot include work order having OPERATION level alterations in
            // ORDER level alteration.
            message.raiseError("MFI_1AA45609F5984501E0440003BA560E35");
        }
        
        // 05/30/2007 Prasad FND-6510.Cannot include the target work order if it
        // is in alteration.
        else if (isNotEmpty(targetAlterationType))
        {
            // Cannot include work order %V1 to alteration propagation because
            // it is in alteration mode
            message.raiseError("MFI_62",
            					targetOrderNumber);
        }

        // FND-6510
        if (isEmpty(alterationStatus))
        {
            // Alteration on work order %V1 has not been started
            message.raiseError("MFI_20422", orderNumber);
        }
        
        //GE-13388
        boolean groupJobExists = operationDao.isOrderOperIncludedIntoInQueueOrActiveGroupJob(targetOrderId);

        if(groupJobExists)
        {
            // GE-5512 : propagate Alteration
            String varV1 = message.buildMessage(EMPTY, "MFI_6A8E44B7D2184F9BAF6041BCADEA24EA");
            // GE-5512 : Cannot %V1-- Target work order %V2 is included in one or more Group Jobs
        	message.raiseError("MFI_B36AE697C2014A97BDDE10723F957739", varV1,targetOrderNumber);
        }

        // IF Condition commented out.

        hold.insertHold(targetOrderId,
                        null,
                        PROPAGATION_HOLD,
                        ORDER_STOP,
                        null,
                        "Included in Alt. Prop.",
                        orderDetails.getOrderNumber(targetOrderId),
                        null,
                        null,
                        orderNumber,
                        null,
                        null,
                        null,
                        null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfwid.application.IAlteration#insertAlterationPart(java.lang.String,
     *      java.lang.Number, java.lang.Number, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.Number, java.lang.Number,
     *      java.lang.String, java.lang.String, java.util.Date, java.util.Date)
     */
    public void insertAlterationPart(String orderId,
                                     Number operationKey,
                                     Number stepKey,
                                     String plannedItemId,
                                     String referenceDesignator,
                                     String partNumber,
                                     String partChange,
                                     String program,
                                     String workLocation,
                                     String ucfOrderItemAlterationVch1,
                                     String ucfOrderItemAlterationVch2,
                                     String ucfOrderItemAlterationVch3,
                                     String ucfOrderItemAlterationVch4,
                                     String ucfOrderItemAlterationVch5,
                                     Number ucfOrderItemAlterationNumber1,
                                     Number ucfOrderItemAlterationNumber2,
                                     String ucfOrderItemAlterationFlag1,
                                     String ucfOrderItemAlterationFlag2,
                                     Date ucfOrderItemAlterationDate1,
                                     Date ucfOrderItemAlterationDate2)
    {
    	insertAlterationPart(orderId, 
    						 operationKey, 
    						 stepKey, 
    						 plannedItemId,
    						 referenceDesignator,
    						 partNumber, 
    						 partChange, 
    						 program, 
    						 workLocation, 
    						 ucfOrderItemAlterationVch1, 
    						 ucfOrderItemAlterationVch2, 
    						 ucfOrderItemAlterationVch3, 
    						 ucfOrderItemAlterationVch4, 
    						 ucfOrderItemAlterationVch5, 
    						 ucfOrderItemAlterationNumber1, 
    						 ucfOrderItemAlterationNumber2,
    						 ucfOrderItemAlterationFlag1, 
    						 ucfOrderItemAlterationFlag2, 
    						 ucfOrderItemAlterationDate1, 
    						 ucfOrderItemAlterationDate2,
    						 null,
    						 null,
    						 null,
    						 null,
    						 null,
    						 null,
    						 null,
    						 null,
    						 null,  //FND-22919
    						 null, 
    						 null, 
    						 null, 
    						 null,
    						 null, 
    						 null, 
    						 null, 
    						 null, 
    						 null,
    						 null, 
    						 null, 
    						 null, 
    						 null, 
    						 null,
    						 null, 
    						 null, 
    						 null, 
    						 null, 
    						 null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfwid.application.IAlteration#insertAlterationPart(java.lang.String,
     *      java.lang.Number, java.lang.Number, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.Number, java.lang.Number,
     *      java.lang.String, java.lang.String, java.util.Date,
     *      java.util.Date, java.lang.String)
     */
    public void insertAlterationPart(String orderId,
                                     Number operationKey,
                                     Number stepKey,
                                     String plannedItemId,
                                     String referenceDesignator,
                                     String partNumber,
                                     String partChange,
                                     String program,
                                     String workLocation,
                                     String ucfOrderItemAlterationVch1,
                                     String ucfOrderItemAlterationVch2,
                                     String ucfOrderItemAlterationVch3,
                                     String ucfOrderItemAlterationVch4,
                                     String ucfOrderItemAlterationVch5,
                                     Number ucfOrderItemAlterationNumber1,
                                     Number ucfOrderItemAlterationNumber2,
                                     String ucfOrderItemAlterationFlag1,
                                     String ucfOrderItemAlterationFlag2,
                                     Date ucfOrderItemAlterationDate1,
                                     Date ucfOrderItemAlterationDate2,
                                     String partDataCollectionId)
    {
    	this.insertAlterationPart(orderId, 
    							  operationKey, 
    							  stepKey, 
    							  plannedItemId, 
    							  referenceDesignator, 
    							  partNumber, 
    							  partChange, 
    							  program, 
    							  workLocation, 
    							  ucfOrderItemAlterationVch1, 
    							  ucfOrderItemAlterationVch2, 
    							  ucfOrderItemAlterationVch3, 
    							  ucfOrderItemAlterationVch4, 
    							  ucfOrderItemAlterationVch5, 
    							  ucfOrderItemAlterationNumber1, 
    							  ucfOrderItemAlterationNumber2, 
    							  ucfOrderItemAlterationFlag1, 
    							  ucfOrderItemAlterationFlag2, 
    							  ucfOrderItemAlterationDate1, 
    							  ucfOrderItemAlterationDate2, 
    							  partDataCollectionId, 
    							  null, 
    							  null, 
    							  null, 
    							  null, 
    							  null, 
    							  null, 
    							  null,
    							  null,  //FND-22919
    							  null, 
    							  null, 
    							  null, 
    							  null,
    							  null, 
    							  null, 
    							  null, 
    							  null, 
    							  null,
    							  null, 
    							  null, 
    							  null, 
    							  null, 
    							  null,
    							  null, 
    							  null, 
    							  null, 
    							  null, 
    							  null);
    }
    public void insertAlterationPart(String orderId,
            						 Number operationKey,
            						 Number stepKey,
            						 String plannedItemId,
            						 String referenceDesignator,
            						 String itemNumber,
            						 String itemChange,
            						 String program,
            						 String workLocation,
            						 String ucfOrderItemAlterationVch1,
            						 String ucfOrderItemAlterationVch2,
            						 String ucfOrderItemAlterationVch3,
            						 String ucfOrderItemAlterationVch4,
            						 String ucfOrderItemAlterationVch5,
            						 Number ucfOrderItemAlterationNumber1,
            						 Number ucfOrderItemAlterationNumber2,
            						 String ucfOrderItemAlterationFlag1,
            						 String ucfOrderItemAlterationFlag2,
            						 Date ucfOrderItemAlterationDate1,
            						 Date ucfOrderItemAlterationDate2,
            						 String itemDataCollectionId,                                     
            						 String plndItemNo,						
            						 String plndItemRev,					
                                     String itemType,						
                                     Number ucfOrderItemAlterationNumber3,	
                                     String ucfOrderItemAlterationFlag3,	
                                     String ucfOrderItemAltVch2551,		
                                     String ucfOrderItemAltVch2552,
                                     String ucfOrderItemAlterationVch6,  //FND-22919
                                     String ucfOrderItemAlterationVch7,
                                     String ucfOrderItemAlterationVch8,
                                     String ucfOrderItemAlterationVch9,
                                     String ucfOrderItemAlterationVch10,
                                     String ucfOrderItemAlterationVch11,
                                     String ucfOrderItemAlterationVch12,
                                     String ucfOrderItemAlterationVch13,
                                     String ucfOrderItemAlterationVch14,
                                     String ucfOrderItemAlterationVch15,
                                     Number ucfOrderItemAlterationNumber4,
                                     Number ucfOrderItemAlterationNumber5,
                                     String ucfOrderItemAlterationFlag4,
                                     String ucfOrderItemAlterationFlag5,
                                     Date ucfOrderItemAlterationDate3,
                                     Date ucfOrderItemAlterationDate4,
                                     Date ucfOrderItemAlterationDate5,
                                     String ucfOrderItemAltVch2553,
                                     String ucfOrderItemAltVch40001,
                                     String ucfOrderItemAltVch40002)		

    {
        // Gets the User Name.
        String userName = ContextUtil.getUsername();

        // Checks whether user has the privilege or not.
        validator.checkUserPrivilege("ORDER_ALTPART_FROMITEMMASTER");

        // Sets program as 'ANY' when passed as empty.
        program = defaultIfEmpty(program, ANY);

        // Sets workLocation as 'ANY' when passed as empty.
        workLocation = defaultIfEmpty(workLocation,
                                                  ANY);
        
        //FND-22681 Selects Location Id 
        String locationId = orderDao.selectLocationId(workLocation);

        // Checks whether order Item Information exists or not.
        boolean orderItemInformationExists = alterationDao.selectOrderItemInformationExists(orderId,
                                                                                            operationKey,
                                                                                            stepKey,
                                                                                            itemDataCollectionId,//FND-13188
                                                                                            itemNumber,
                                                                                            itemChange,
                                                                                            program,
                                                                                            locationId);

        // Raises an error when Order Item Information exists.
        if (orderItemInformationExists)
        {
            message.raiseError("MFI_81850");
        }
        
        // FND-17296
        String itemId = itemDao.selectItemId(itemNumber, itemChange);
        
        String itemStatus = itemDao.selectItemStatus(itemId);
        
        if(stringEquals(itemStatus, IN_PROCESS))
        {
            // Cannot assign a Item which is not Completed // GE-5511
        	message.raiseError("MFI_AA004F75C86E4A6B81FDBF90599AC924");
        }

        // Gets the Order Item Alteration Id as a String.
        String orderItemAlterationId = alterationDao.selectNextAlterationSequence();

        if(stringEquals(itemType, PART))
        {
        	/** * Start Ankur ** */
        	// -- 03/14/2006 Sholeh Moinee - JIRA# FND-2716 - added ucf parameters
        	// Inserts the Order Item Alternation Reference record.
        	alterationDao.insertOrderItemAlternationReference(orderItemAlterationId,
        													  userName,
        													  ucfOrderItemAlterationVch1,
        													  ucfOrderItemAlterationVch2,
        													  ucfOrderItemAlterationVch3,
        													  ucfOrderItemAlterationVch4,
        													  ucfOrderItemAlterationVch5,
        													  ucfOrderItemAlterationNumber1,
        													  ucfOrderItemAlterationNumber2,
        													  ucfOrderItemAlterationFlag1,
        													  ucfOrderItemAlterationFlag2,
        													  ucfOrderItemAlterationDate1,
        													  ucfOrderItemAlterationDate2,
        													  NO,
        													  orderId,
        													  operationKey,
        													  stepKey,
        													  itemDataCollectionId,//FND-13188
        													  itemNumber,
        													  itemChange,
        													  program,
        													  locationId,
        													  ucfOrderItemAlterationVch6,  //FND-22919
        													  ucfOrderItemAlterationVch7,
        													  ucfOrderItemAlterationVch8,
        													  ucfOrderItemAlterationVch9,
        													  ucfOrderItemAlterationVch10,
        													  ucfOrderItemAlterationVch11,
        													  ucfOrderItemAlterationVch12,
        													  ucfOrderItemAlterationVch13,
        													  ucfOrderItemAlterationVch14,
        													  ucfOrderItemAlterationVch15,
        													  ucfOrderItemAlterationNumber3,
        													  ucfOrderItemAlterationNumber4,
        													  ucfOrderItemAlterationNumber5,
        													  ucfOrderItemAlterationFlag3,
        													  ucfOrderItemAlterationFlag4,
        													  ucfOrderItemAlterationFlag5,
        													  ucfOrderItemAlterationDate3,
        													  ucfOrderItemAlterationDate4,
        													  ucfOrderItemAlterationDate5,
        													  ucfOrderItemAltVch2551,
        													  ucfOrderItemAltVch2552,
        													  ucfOrderItemAltVch2553,
        													  ucfOrderItemAltVch40001,
        													  ucfOrderItemAltVch40002);
        }
        else if(stringEquals(itemType, TOOL))
        {
        	alterationDao.insertOrderItemAlternationReference(orderItemAlterationId,
					  										  userName,
					  										  ucfOrderItemAlterationVch1,
					  										  ucfOrderItemAlterationVch2,
					  										  ucfOrderItemAlterationVch3,
					  										  ucfOrderItemAlterationVch4,
					  										  ucfOrderItemAlterationVch5,
					  										  ucfOrderItemAlterationNumber1,
					  										  ucfOrderItemAlterationNumber2,
					  										  ucfOrderItemAlterationNumber3,
					  										  ucfOrderItemAlterationFlag1,
					  										  ucfOrderItemAlterationFlag2,
					  										  ucfOrderItemAlterationFlag3,
					  										  ucfOrderItemAlterationDate1,
					  										  ucfOrderItemAlterationDate2,
					  										  ucfOrderItemAltVch2551,
					  										  ucfOrderItemAltVch2552,
					  										  NO,
					  										  orderId,
					  										  operationKey,
					  										  stepKey,
					  										  itemDataCollectionId,	
					  										  itemNumber,
					  										  itemChange,
					  										  program,
					  										  locationId,
					  										  ucfOrderItemAlterationVch6,  //FND-22919
        													  ucfOrderItemAlterationVch7,
        													  ucfOrderItemAlterationVch8,
        													  ucfOrderItemAlterationVch9,
        													  ucfOrderItemAlterationVch10,
        													  ucfOrderItemAlterationVch11,
        													  ucfOrderItemAlterationVch12,
        													  ucfOrderItemAlterationVch13,
        													  ucfOrderItemAlterationVch14,
        													  ucfOrderItemAlterationVch15,
        													  ucfOrderItemAlterationNumber4,
        													  ucfOrderItemAlterationNumber5,
        													  ucfOrderItemAlterationFlag4,
        													  ucfOrderItemAlterationFlag5,
        													  ucfOrderItemAlterationDate3,
        													  ucfOrderItemAlterationDate4,
        													  ucfOrderItemAlterationDate5,
        													  ucfOrderItemAltVch2553,
        													  ucfOrderItemAltVch40001,
        													  ucfOrderItemAltVch40002);
        	
        }
        /** * End Ankur ** */
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfwid.application.IAlteration#runCopyAlteration(java.lang.String,
     *      java.lang.String)
     */
    public void runCopyAlteration(String sourceOrderId, String targetOrderId)
    {
        PlanType sourceOrder = new PlanType();
        PlanType targetOrder = new PlanType();
        PlanType originalOrder = new PlanType();
        ArrayList sqlTable = new ArrayList();
        ArrayList modSqlTable = new ArrayList();        
        boolean buyoffDeleted = false; 
        boolean buyoffInserted = false; //GE-5459

        if (!privilege.hasPrivilege("ORDER_ALT_INITIATION"))
        {
            message.raiseError("MFI_20780"); 
            // Message Text: You are not authorized to copy a work order alteration
        }

        // 12/20/2002 Prasad Added order_qty for target order.
        // 11/20/2002 Prasad TD#3741.Added alt_status to the query.
        Map information = alterationDao.selectOrderInformation(sourceOrderId,
                                                               targetOrderId);
        String sourceOrderNumber = (String) information.get("SRC_ORDER_NO");        
        String sourceNeedsReviewFlag = (String) information.get("SRC_NEEDS_REVIEW_FLAG");
        String sourceAlterationId = (String) information.get("SRC_ALT_ID");
        String targetOrderNumber = (String) information.get("TGT_ORDER_NO");        
        String targetNeedsReviewFlag = (String) information.get("TGT_NEEDS_REVIEW_FLAG");
        Number targetOrderCompleteQuantity = (Number) information.get("TGT_ORDER_COMPLETE_QTY");

        validateCopyAlteration( targetOrderId, 
                                sourceAlterationId,
                                sourceOrderNumber,
                                targetNeedsReviewFlag,
                                sourceNeedsReviewFlag, 
                                targetOrderCompleteQuantity );

        // 07/25/2006 Prasad FND-4561.get the source order alt_no and pass
        // it to target order.
        // Gets the Change Authorization Number.
        Map map = alterationDao.selectAlterationNo(sourceAlterationId);
        String sourceAlterationNo = (String) map.get(ALT_NO); // FND-25795

        // HS 5/30/2003 END see also the end of this proc !!!!
        alterateStart(targetOrderId, ALT_PROP_COPY, // alt_reason
                      ALT_PROP_COPY, // alter_type
                      sourceAlterationId,
                      null,
                      sourceAlterationNo); // use the src alt_id

        // alt_no after alteration hold is created we need to set tgt_alt_id and
        // alt_status to avoid error messages below such as MFI_20422 and MFI_20760
        Map alterationInfo = orderDao.selectOrderDetails(targetOrderId);

        String targetAlterationStatus = (String) alterationInfo.get(ALT_STATUS);
        String targetAlterationId = (String) alterationInfo.get(ALT_ID);

        // Deleted selectAlterationDescription.
        // updateAlteration removed. SFWID_ALTERATION_UPD
        validateTargetOrder(sourceOrderNumber, sourceAlterationId,
				targetOrderNumber, targetAlterationId,
				targetAlterationStatus,information);

        // 07/10/2008 Suniket FND-8369
        // Deleted the code to allow alteration propagation regardless of build
        // flags.

        sourceOrder = planObject.buildOrderObject(sourceOrderId);
        targetOrder = planObject.buildOrderObject(targetOrderId);
        
        // FND-17329
        boolean isDisplaySequenceDifferent = !stringEquals( sourceOrder.getDisplaySequence(), targetOrder.getDisplaySequence() );
        if(isDisplaySequenceDifferent)
        {
            ofd.validateDisplaySequence( targetOrder.getOrderId(), targetOrder.getDisplaySequence() );
        }
        
        // FND-22135
        boolean isOperationOverlapFlagDifferent = !stringEquals( sourceOrder.getOperationOverlapFlag(), targetOrder.getOperationOverlapFlag() );
        if(isOperationOverlapFlagDifferent)
        {
        	validateOperationOverlapFlag(targetOrder.getOrderId(), 
        							   sourceOrder.getOperationOverlapFlag(), 
    								   targetOrder.getOperationOverlapFlag());
        }

        originalOrder = planObject.buildOrderObject(targetOrderId);
        
        // FND-13299
        // Remove the invalid Components from Source order
        boolean partRemovedDueToEffectivityOrBom = planObject.removeInvalidComponents(targetOrder, sourceOrder);
        
        // FND-21467
        boolean planObjectCompare = compareObjects(sourceOrder,
                                                   targetOrder,
                                                   sqlTable,
                                                   NO);
        
        // FND-12027
        // Check if Action is required for buyoff, tool, part and dc if
        // 1 of them is included and its related operation text not.       

        if (planObjectCompare)
        {
        	
        	orderAlteration.comparePlanObject(sourceOrderId, targetOrderId, sourceOrder,
					targetOrder, originalOrder, sqlTable, modSqlTable,
					targetAlterationId, buyoffDeleted, buyoffInserted,
					partRemovedDueToEffectivityOrBom);
        }
        deleteStandardTextVariableValue(targetOrderId);
        updateStandardTextVariableValue(sourceOrderId, targetOrderId);   
        changeBuildPartIfPropagatingOrderBuildPartModified(sourceOrderId,targetOrderId, sourceOrder, targetOrder);

        // FND-6213
        // JL 03/28/2007 FND-6189 Call proc to move unit into first oper in
        // case first oper is deleted during supercede.
        order.moveFirstOperation(targetOrderId);

        boolean isOrderMRO = stringEquals( orderDetails.isMRO(targetOrderId), YES);
        if(isDisplaySequenceDifferent ||
        		(!isDisplaySequenceDifferent && stringEquals(sourceOrder.getDisplaySequence(), AUTO_SERIAL)))
        {
            updateOfdDiagram(targetOrderId, sourceOrder, targetOrder, modSqlTable, isDisplaySequenceDifferent, isOrderMRO);
        }
        
        // FND-18081
        else if(stringEquals(sourceOrder.getDisplaySequence(), EXECUTION_ORDER))
        {
        	rearrangeSwimLaneDiagram(targetOrderId, sourceOrder, modSqlTable,isDisplaySequenceDifferent);
            
        }
        
        // FND-22135
        if(isOperationOverlapFlagDifferent)
        {
        	doActionWhenOperationOverlapFlagIsUpdated(targetOrderId, 
        										    sourceOrder.getOperationOverlapFlag(),
        										    YES);//FND-24314
        }
        
        // alteration for the target
        // order if needs review flag is Y
        ofdNeedsReview.updateNeedsReview(targetOrderId, null);
        
        updateOverlappedOperationQty(targetOrderId, sourceOrder, modSqlTable);
    }

	protected void validateTargetOrder(String sourceOrderNumber,			
			String sourceAlterationId, String targetOrderNumber,
			String targetAlterationId, String targetAlterationStatus,
			Map information) 
	{
		String sourcePlanId = (String) information.get("SRC_PLAN_ID");
        Number sourcePlanVersion = (Number) information.get("SRC_PLAN_VERSION");
        Number sourcePlanRevision = (Number) information.get("SRC_PLAN_REVISION");
        Number sourcePlanAlterations = (Number) information.get("SRC_PLAN_ALTERATIONS");
		String targetPlanId = (String) information.get("TGT_PLAN_ID");
        Number targetPlanVersion = (Number) information.get("TGT_PLAN_VERSION");
        Number targetPlanRevision = (Number) information.get("TGT_PLAN_REVISION");
        Number targetPlanAlterations = (Number) information.get("TGT_PLAN_ALTERATIONS");
		if (!stringEquals(sourcePlanId, targetPlanId)
                || sourcePlanVersion.intValue() != targetPlanVersion.intValue()
                || sourcePlanRevision.intValue() != targetPlanRevision.intValue()
                || sourcePlanAlterations.intValue() != targetPlanAlterations.intValue())
        {
            message.raiseError("MFI_20800",
                               sourceOrderNumber,
                               targetOrderNumber);
        }

        if (isEmpty(targetAlterationStatus))
        {
            message.raiseError("MFI_20422", targetOrderNumber);
        }
        if (!stringEquals(sourceAlterationId, targetAlterationId))
        {
            message.raiseError("MFI_20760",
                               targetOrderNumber,
                               sourceOrderNumber);
        }
	}

	protected void deleteStandardTextVariableValue(String targetOrderId)
	{
		alterationDao.deleteStandardTextVariableValue(targetOrderId);
	}
	protected void updateStandardTextVariableValue(String sourceOrderId,
			String targetOrderId) {
		List<String> updatedVariableList = alterationDao.selectUpdatedVariable(sourceOrderId);
        Iterator updatedVariableIterator = updatedVariableList.listIterator(); 
        while (updatedVariableIterator.hasNext())
        {
            Map updatedVariableMap = (Map) updatedVariableIterator.next();
            String svValue = (String) updatedVariableMap.get(SVVALUE);
            String tag = (String) updatedVariableMap.get(TAG);
            alterationDao.updateVariableValue(targetOrderId, svValue, tag);
        }
	}

	protected void updateOverlappedOperationQty(String targetOrderId,
												PlanType sourceOrder, 
												ArrayList modSqlTable) 
	{
		//FND-22732 Raise Error or Update Oper Qty if author control in between during alteration-propagation
        if( stringEquals( sourceOrder.getOperationOverlapFlag(), YES ) )
        {
            List operKeyListToUpdateQtys = new ArrayList();
            for (int i = 0; i < modSqlTable.size(); i++)
            {
                if(modSqlTable.get(i) != null)
                {
                    PlanChange pc = ((PlanChange) modSqlTable.get(i));
                    
                    if(stringEquals( pc.getAction(), upperCase(INSERT) ) &&
                            ( stringEquals( pc.getClassP(), upperCase(BUYOFF) ) ||
                               stringEquals( pc.getClassP(), PART ) ||
                               stringEquals( pc.getClassP(), TOOL ) ||
                               stringEquals( pc.getClassP(), DATCOL )
                             )
                       )
                    {
                        Number operKey = (Number) pc.getOperKey();
                        if(!operKeyListToUpdateQtys.contains( operKey ))
                        {
                            operKeyListToUpdateQtys.add(operKey);
                        }
                        
                    }
                    
                }
            }
            
            for(int i=0; i < operKeyListToUpdateQtys.size(); i++)
            {
                htReferenceText.updateOperationQuantitiesAndMoveUnits( targetOrderId, (Number) operKeyListToUpdateQtys.get(i) );
            }
            
        }
	}

	protected void rearrangeSwimLaneDiagram(String targetOrderId,
											PlanType sourceOrder, 
											ArrayList modSqlTable,
											boolean isDisplaySequenceDifferent)
	{
		if(!isDisplaySequenceDifferent)
		{
		    // Fetch all the operations which are excluded or sequence number = 0
		    // and column number is not 1.
		    List selectOperationsForFirstColumn = operationDao.selectOperationsForFirstColumn( targetOrderId, sourceOrder.getDisplaySequence() );
		    for(int i=0; i < selectOperationsForFirstColumn.size(); i++)
		    {
		        Map row = (Map) selectOperationsForFirstColumn.get(i);
		        String nodeId = (String) row.get("NODE_ID");
		        Number nodesColumnNumber = (Number) row.get("NODE_COLUMN");
		        
		        // Shift the node to 1st column and other nodes if this is the only node in that column.
		        if(nodesColumnNumber!= null && nodesColumnNumber.intValue() > 1)
		        {
		            // Delete all the links for the node being updated
		            operationDao.deleteOrderLink(targetOrderId, nodeId);
		        }
		    }

		    // Fetch all the operations newly updated with EXECUTION ORDER
		    for (int i = 0; i < modSqlTable.size(); i++)
		    {
		        if(modSqlTable.get(i) != null)
		        {
		            PlanChange pc = ((PlanChange) modSqlTable.get(i));
		            
		            // FND-18081
		            // For Alteration propagation copyOperation method always makes operation in 
		            // INCLUDED Status so when pc.isActionInsert() we need to move that operation only
		            // based on new sequence number passed.
		            if(pc.isClassOPER() && (pc.isActionUPDATE() || pc.isActionINSERT())) 
		            {
		                // Call method to move operation to background OFD
		                if(pc.getKey3() != null)
		                {
		                    Number newSequenceNumber = (Number) pc.getKey3();
		                    
		                    if(pc.isActionUPDATE())
		                    {
		                        if(pc.getKey4() != null)
		                        {
		                            Number oldSequenceNumber = (Number) pc.getKey4();
		                            if(oldSequenceNumber.doubleValue() != 0)
		                            {
		                            	ofdRearrange.rearrangeBackgroundOfdForSwimLane( targetOrderId, 
		                                                                       pc.getOperKey(), 
		                                                                       oldSequenceNumber, 
		                                                                       null,
		                                                                       PROPAGATE );
		                            }
		                        }
		                    }
		               
		                	operationDao.updateSequenceNumber( pc.getOperKey(), targetOrderId, newSequenceNumber );
		                	
		                    ofd.moveToBackgroundOfd(targetOrderId, 
		                                            pc.getKey1(),
		                                            sourceOrder.getDisplaySequence(), 
		                                            PROPAGATE,
		                                            newSequenceNumber);
		                }
		            }
		        }
		    }
		}
	}

	protected void updateOfdDiagram(String targetOrderId,
									PlanType sourceOrder,
									PlanType targetOrder, 
									ArrayList modSqlTable,
									boolean isDisplaySequenceDifferent, 
									boolean isOrderMRO)
	{
		orderDao.updateDisplaySequence(targetOrderId, sourceOrder.getDisplaySequence());
		
		if(isOrderMRO && (!isDisplaySequenceDifferent && stringEquals( sourceOrder.getDisplaySequence(), AUTO_SERIAL)))
		{
		    // Fetch all the operations which are excluded and column number is not 1.
		    List selectOperationsForFirstColumn = operationDao.selectOperationsForFirstColumn( targetOrderId, sourceOrder.getDisplaySequence() );
		    for(int i=0; i < selectOperationsForFirstColumn.size(); i++)
		    {
		        Map row = (Map) selectOperationsForFirstColumn.get(i);
		        String nodeId = (String) row.get("NODE_ID");
		        Number nodesColumnNumber = (Number) row.get("NODE_COLUMN");
		        Number nodesRowNumber = (Number) row.get("NODE_ROW");
		        
		        // Shift the node to 1st column and other nodes if this is the only node in that column.
		        if(nodesColumnNumber!= null && nodesColumnNumber.intValue() > 1)
		        {
		            // Get the first available position.
		            Number columnNumber[] = new Number[1];
		            Number rowNumber = ofd.getFreeCell(targetOrderId, columnNumber);
		            
		            // Delete all the links for the node being updated
		            operationDao.deleteOrderLink(targetOrderId, nodeId);
		        
		            // Update Node column and Row
		            ofdDao.updateOrderNode(columnNumber[0], 
		                                   rowNumber, 
		                                   targetOrderId, 
		                                   nodeId);
		            
		            boolean isNodeWithSameColumnNumberExists = ofdDao.selectIsNodeWithSameColumnNumberExists( targetOrderId, nodesColumnNumber );
		            if(!isNodeWithSameColumnNumberExists)
		            {
						node.shiftNode(targetOrderId, nodesRowNumber, nodesColumnNumber, SWIM_LANE_SHIFT_LEFT,
								INTEGER_ONE, nodeId);
		            }
		        }
		    }
		}
		
		ofd.updateBackgroungOFD( sourceOrder.getDisplaySequence(), targetOrder.getDisplaySequence(), targetOrderId );
		
		// FND-19062
		if(stringEquals(sourceOrder.getDisplaySequence(), EXECUTION_ORDER))
		{
			// Fetch all the operations newly updated with EXECUTION ORDER
		    for (int i = 0; i < modSqlTable.size(); i++)
		    {
		        if(modSqlTable.get(i) != null)
		        {
		            PlanChange pc = ((PlanChange) modSqlTable.get(i));
		            
		            // For Alteration propagation copyOperation method always makes operation in 
		            // INCLUDED Status so when pc.isActionInsert() we need to move that operation only
		            // based on new sequence number passed.
		            if(pc.isClassOPER() && (pc.isActionUPDATE() || pc.isActionINSERT())) 
		            {
		                // Call method to move operation to background OFD
		                if(pc.getKey3() != null)
		                {
		                    Number newSequenceNumber = (Number) pc.getKey3();
		                    
		                    if(newSequenceNumber.doubleValue() != 0)
		                    {
		                    	operationDao.updateSequenceNumber( pc.getOperKey(), targetOrderId, newSequenceNumber );
		                    	
		                        ofd.moveToBackgroundOfd(targetOrderId, 
		                                                pc.getKey1(),
		                                                sourceOrder.getDisplaySequence(), 
		                                                PROPAGATE,
		                                                newSequenceNumber);
		                    }
		                }
		            }
		        }
		    }
		}
	}

	protected void changeBuildPartIfPropagatingOrderBuildPartModified(String sourceOrderId,
																	  String targetOrderId, 
																	  PlanType sourceOrder,
																	  PlanType targetOrder) 
	{
		// FND-8425
        String newPartNumber = sourceOrder.getPartNo();
        String newPartChange = sourceOrder.getPartChg();
        String newBomNo = sourceOrder.getBomNumber();
        String newMfgBomChg = sourceOrder.getMfgBomChg();
        String oldPartNumber = targetOrder.getPartNo();
        String oldPartChange = targetOrder.getPartChg();
        String oldBomNo = targetOrder.getBomNumber();
        String oldMfgBomChg = targetOrder.getMfgBomChg();
        String newExplicitBomLink = sourceOrder.getExplicitBomLink();
        String oldExplicitBomLink = targetOrder.getExplicitBomLink();
        String orderUom = sourceOrder.getOrderUom();

        boolean isItemChanged = !stringEquals(newPartNumber, oldPartNumber) || !stringEquals(newPartChange, oldPartChange);
        boolean isBomChanged = !stringEquals(newBomNo, oldBomNo) || !stringEquals(newMfgBomChg, oldMfgBomChg) || !stringEquals(newExplicitBomLink, oldExplicitBomLink);
        
        if (isItemChanged || isBomChanged)
        {

            // 02/17/2009 FND-10032 Utpal
            // Modified MBOM Change along with Part No and Part Rev
            Map sourceMbomChgMap = orderDao.selectOrderDetails(sourceOrderId);

            String mfgBomChg = (String) sourceMbomChgMap.get(MFG_BOM_CHG);
            String bomNumber = (String) sourceMbomChgMap.get(BOM_NO);
            String itemType = (String) sourceMbomChgMap.get(ITEM_TYPE);
            String itemSubtype = (String) sourceMbomChgMap.get(ITEM_SUBTYPE);
            String bomId = (String) sourceMbomChgMap.get(BOM_ID);  // FND-23462
            String explicitBomLink = (String) sourceMbomChgMap.get(EXPLICIT_BOM_LINK_FLAG);  // FND-23462
            String assignedWorkLocation = (String) sourceMbomChgMap.get(ASGND_WORK_LOC);  // GE-6282
            String program = (String) sourceMbomChgMap.get(PROGRAM);  // GE-6282

            buildPart.updateBuildPartPre(targetOrderId,
                                         oldPartNumber,
                                         oldPartChange,
                                         newPartNumber,
                                         newPartChange,
                                         ALTERATION_PROPAGATION,
                                         mfgBomChg,
                                         itemType,
                                         itemSubtype,
                                         bomNumber,
                                         bomId,  // FND-23462
                                         explicitBomLink,  // FND-24392
                                         orderUom, 
                                         null,  //FND-26844
                                         assignedWorkLocation, // GE-6282
                                         program); // GE-6282
        }
	}

    protected void validateCopyAlteration ( String targetOrderId,
                                            String sourceAlterationId,
                                            String sourceOrderNumber,
                                            String targetNeedsReviewFlag,
                                            String sourceNeedsReviewFlag,
                                            Number targetOrderCompleteQuantity )
    {
        String holdId;
        /** * Start Ankur ** */
        // 07/25/2006 Prasad FND-4561 moved these checks from bottom.
        if (stringEquals(targetNeedsReviewFlag, YES))
        {
            message.raiseError("MFI_57672"); // Message Text: Operation Flow
            // Diagram of Target Order needs
            // Review.
        }
        if (stringEquals(sourceNeedsReviewFlag, YES))
        {
            message.raiseError("MFI_57673"); // Message Text: Operation Flow
            // Diagram of Source Order needs
            // Review.
        }
        if (targetOrderCompleteQuantity != null
                && targetOrderCompleteQuantity.intValue() > 0)
        {
            message.raiseError("MFI_57674"); // Message Text: Cannot perform
            // alteration as target order
            // has completed quantity.
        }
        if (isEmpty(sourceAlterationId))
        {
            message.raiseError("MFI_20422", sourceOrderNumber); // Message Text:
            // Alteration on
            // work order %V1
            // has not been
            // started
        }
        /** * End Ankur ** */

        // HS 5/30/2003
        // before start copying first close PROPAGATION HOLD and then start
        // alteration
        // by calling sfwid_alteration_hold.
        holdId = alterationDao.selectHoldIdWithHoldRef1(targetOrderId,
                                                        sourceOrderNumber);

        if (isEmpty(holdId))
        {
            message.raiseError("MFI_61",
                               orderDetails.getOrderNumber(targetOrderId));// Message
            // Text:
            // Work
            // order
            // %V1
            // is
            // not
            // Included
            // to
            // Alteration
            // Propagation.
        }

        hold.closeHold(targetOrderId,
                       null,
                       holdId,
                       null,
                       ALT_PROP,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null);
    }
    
    /* (non-Javadoc)
     * @see com.ibaset.solumina.sfwid.application.IAlteration#compareObjects(java.lang.Object, java.lang.Object, java.util.List, java.lang.String)
     */
    public boolean compareObjects(Object sourceOrder,
    		                      Object targetOrder,
    		                      List sqlTable,
    		                      String exitOnDiff)
    {
    	boolean planObjectCompare = planObject.comparePlanObject((PlanType)sourceOrder,
    			                                                 (PlanType)targetOrder,
    			                                                 sqlTable,
    			                                                 NO);
    	
    	return planObjectCompare;
    }  
    
    public void showUdvWithOrderListForAlterationPropogation(String orderId,
                                                             String orderNumber,
                                                             String alterationId,
                                                             String taskId,
                                                             String taskNotes)
    {
        StringBuffer warningMessage = selectWarningMessageForOrdersMissingUseActionWhileAlterationPorpogation(orderId, orderNumber, alterationId);
        
        StringBuffer parameters = new StringBuffer();
        parameters.append("ORDER_ID=")
                  .append(orderId)
                  .append(",TASK_ID=")
                  .append(taskId)
                  .append(",NOTES=")
                  .append(taskNotes)
                  .append(",MESSAGE_TEXT=")
                  .append(warningMessage);

        // MFI_5239
        event.showUdv("MFI_5239", UPDATE, LanguageUtils.getMessage("confirmation"), // GE-7326
                      null,
                      parameters.toString());
    }
    
    //GE-5561
    public StringBuffer selectWarningMessageForOrdersMissingUseActionWhileAlterationPorpogation(String orderId, String orderNo, String alterationId)
    {
        StringBuffer warningMessage = new StringBuffer();
        
        // get the list of orders that are included in alteration propogation.
        List<Map> orderList = alterationDao.selectHoldOrderId(orderNo);
        
        List<Map<String,String>> removedPartList = alterationDao.selectPartListWithRemoveActionInstalledInGivenAlteration(orderId, alterationId);
        orderList.stream().forEach(targetOrder -> appendMessageForOrderListMissingPartWithUseAction(targetOrder, removedPartList, warningMessage, orderId, alterationId));
        
        return warningMessage;
    }
    
    protected void appendMessageForOrderListMissingPartWithUseAction(Map targetOrder, List<Map<String,String>> removedPartList, StringBuffer warningMessage, String sourceOrderId, String sourceAlterationId)
    {
        StringBuffer warningMessageForOrder = prepareWarningMessageForOrdersMissingProperPartAction((String)targetOrder.get(ORDER_ID), removedPartList, ALT_PROP, sourceOrderId, sourceAlterationId, targetOrder);
        if(!warningMessageForOrder.toString().isEmpty())
        {
            warningMessage.append(warningMessageForOrder);
        }
    }
    
    /*
     * (non-Javadoc)
     * @see com.ibaset.solumina.sfwid.application.IAlteration#prepareWarningMessageForOrdersMissingProperPartAction(java.lang.String, java.util.List, java.lang.String, java.lang.String, java.lang.String)
     */
    public StringBuffer prepareWarningMessageForOrdersMissingProperPartAction(String orderId,
                                                                              List removePartList,
                                                                              String calledFrom, 
                                                                              String sourceOrderId, 
                                                                              String sourceAlterationId,
                                                                              Map targetOrderMap)
    {        
        Number targetOrderQty = orderDao.selectOrderQuantity(orderId);
        List serialLotUnitsList = operationDao.selectSerialLotUnits(orderId);

        StringBuffer messageString = new StringBuffer().append("");
        int warningPartCount = 0;
        // boolean isSendMsg = false;

        Map<String, Map> removedUniquePartMap = prepareUniquePartMapFromRemovedPartList(removePartList);
        
        Iterator iterator = removedUniquePartMap.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry mapEntry = (Map.Entry) iterator.next();
            Map removedPart = (Map) mapEntry.getValue();
            warningPartCount = checkIfPartInstalledInOrderForRelatedRemovedPartQuantity(orderId,
                                                                                        targetOrderQty,
                                                                                        serialLotUnitsList,
                                                                                        messageString,
                                                                                        warningPartCount,
                                                                                        removedPart, 
                                                                                        calledFrom, 
                                                                                        sourceOrderId, 
                                                                                        sourceAlterationId,
                                                                                        targetOrderMap);
        }

        String preMessageTextForHeading = fetchPreMessageTextForHeadingInOriginalMessage(warningPartCount);
        messageString.insert(0, preMessageTextForHeading);
        
        return messageString;
    }

    /*
     * (non-Javadoc)
     * @see com.ibaset.solumina.sfwid.application.IAlteration#fetchPreMessageTextForHeadingInOriginalMessage(int)
     */
    public String fetchPreMessageTextForHeadingInOriginalMessage(int warningPartCount) 
    {
        String preMessageTextForHeading = StringUtils.EMPTY;
        
        // FND-25224-Changed Message String
        //Re-installation required for following Component Item/s in either same or related order.
        if (warningPartCount == 1)
        {
            preMessageTextForHeading = message.buildMessage(EMPTY, "MFI_44298F1CC5A249E1B9F843797EA9BBEC") + FRAMEWORK_LINEFEED;
        }
        
        else if (warningPartCount > 1)
        {
            // FND-25224-Changed Message String
            //Re-installation required for following Component Item/s
            preMessageTextForHeading = message.buildMessage(EMPTY, "MFI_B6F58044BA5742F8821A145479A25BA2") + FRAMEWORK_LINEFEED;
        }
        
        return preMessageTextForHeading;
    }

    protected int checkIfPartInstalledInOrderForRelatedRemovedPartQuantity(String orderId,
                                                                           Number targetOrderQty,
                                                                           List serialLotUnitsList,
                                                                           StringBuffer messageString,
                                                                           int warningPartCount,
                                                                           Map removedPart, 
                                                                           String calledFrom, 
                                                                           String sourceOrderId, 
                                                                           String sourceAlterationId,
                                                                           Map targetOrderMap)
 {
        String removePartNo = (String) removedPart.get(PART_NO_KEY);
        String removePartChg = (String) removedPart.get(PART_CHG);
        String replacementPartNo = (String) removedPart.get(REPLACEMENT_PART_NO);
        String replacementPartChg = (String) removedPart.get(REPLACEMENT_PART_CHG);
        String replaceSamePartFlag = (String) removedPart.get(REPLACE_WITH_SAME_PART_FLAG);
        String reinstallSameUnitFlag = (String) removedPart.get(REINSTALL_SAME_UNIT_FLAG);
        String replaceDiffPartFlag = (String) removedPart.get(REPLACE_WITH_DIFF_PART_FLAG);
        String refDes = (String) removedPart.get(REF_DES);
        String targetOrderNo = targetOrderMap != null ? (String) targetOrderMap.get(ORDER_NO) : null;

        if (equalsIgnoreCase(YES, replaceSamePartFlag) || equalsIgnoreCase(YES, reinstallSameUnitFlag)) {
            Number totalRemoveOrderPartQty = getTotalRemovedComponentQuantity(orderId, calledFrom, sourceOrderId,
                    sourceAlterationId, removePartNo, removePartChg, false, null, null, refDes, targetOrderQty);

            boolean isPartInstalled = isPartInstalledInTargetOrder(orderId, sourceOrderId, sourceAlterationId,
                    removePartNo, removePartChg, refDes, serialLotUnitsList, totalRemoveOrderPartQty, calledFrom,
                    targetOrderQty);
            if (!isPartInstalled) {
                // FND-25224-Changed Message String
                messageString.append(buildMessageForRemovedPartIsNotReInstalled(removePartNo, removePartChg,
                        replacementPartNo, replacementPartChg, refDes, false, calledFrom, targetOrderNo));

                warningPartCount++;
            }
        } else if (equalsIgnoreCase(YES, replaceDiffPartFlag)) {
            Number totalRemoveOrderPartQty = getTotalRemovedComponentQuantity(orderId, calledFrom, sourceOrderId,
                    sourceAlterationId, removePartNo, removePartChg, true, replacementPartNo, replacementPartChg,
                    refDes, targetOrderQty);
            // FND-25536
            if (totalRemoveOrderPartQty == null) {
                messageString.append(" Replacement Part No and Part Rev is missing .");

                warningPartCount++;
            } else {
                boolean isPartInstalled = isPartInstalledInTargetOrder(orderId, sourceOrderId, sourceAlterationId,
                        replacementPartNo, replacementPartChg, refDes, serialLotUnitsList, totalRemoveOrderPartQty,
                        calledFrom, targetOrderQty);
                if (!isPartInstalled)

                {
                    messageString.append(buildMessageForRemovedPartIsNotReInstalled(removePartNo, removePartChg,
                            replacementPartNo, replacementPartChg, refDes, true, calledFrom, targetOrderNo));
                    warningPartCount++;
                }
            }

        }
        return warningPartCount;
    }

    protected Number getTotalRemovedComponentQuantity(String orderId, String calledFrom,
                                                      String sourceOrderId,
                                                      String sourceAlterationId,
                                                      String removePartNo, String removePartChg,
                                                      boolean isCheckForReplacementPart,
                                                      String replacementPartNo,
                                                      String replacementPartChg, String refDes,
                                                      Number targetOrderQty)
 {
        Number removePartQty = 0;
        if (StringUtils.equals(calledFrom, ALT_PROP)) {
            removePartQty = orderDao.selectRemovePartQtyForAlterationPropagation(orderId, removePartNo, removePartChg,
                    isCheckForReplacementPart, replacementPartNo, replacementPartChg, refDes, sourceOrderId,
                    sourceAlterationId);
        } else if (StringUtils.equals(calledFrom, ALT)) {
            removePartQty = orderDao.selectRemovePartQtyForOrderForRemoveAction(orderId, removePartNo, removePartChg,
                    isCheckForReplacementPart, replacementPartNo, replacementPartChg, refDes);
        }

        return removePartQty.doubleValue() * targetOrderQty.doubleValue();
    }

    /*
     * (non-Javadoc)
     * @see com.ibaset.solumina.sfwid.application.IAlteration#buildMessageForRemovedPartIsNotReInstalled(java.lang.String, 
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean)
     */
    public String buildMessageForRemovedPartIsNotReInstalled(String removePartNo, 
                                                              String removePartChg,
                                                              String replacementPartNo, 
                                                              String replacementPartChg,
                                                              String refDes, 
                                                              boolean isReplaceDiffPartFlagSet,
                                                              String calledFrom, 
                                                              String targetOrderNo) 
    {
        String finalMessageText = EMPTY;
        
        if(!ALT_PROP.equals(calledFrom))
        {
            String removePartMessageText = message.buildUnformattedMessage(EMPTY, "MFI_B11F2DFDA57F4F01BD3483B697E31EC8", removePartNo, removePartChg, refDes, false); 
            String reInstallPartMessageText = message.buildUnformattedMessage(EMPTY, "MFI_4494FD8C443C4B54B7B1CFF9F3D1E021",(isReplaceDiffPartFlagSet ? replacementPartNo :removePartNo),
                    (isReplaceDiffPartFlagSet ? replacementPartChg :removePartChg),  refDes, false);
            
            finalMessageText = FRAMEWORK_LINEFEED + removePartMessageText + FRAMEWORK_LINEFEED + reInstallPartMessageText; 
        }
        else
        {
            String orderNoMessageText = message.buildUnformattedMessage(EMPTY, "MFI_F8CF1AACE074421FAF5076DAF0CC20D0", targetOrderNo, false); 
            String reInstallPartMessageText = message.buildUnformattedMessage(EMPTY, "MFI_C2C9FF8F7E0B4F878D76A8FDC1B29A12", (isReplaceDiffPartFlagSet ? replacementPartNo :removePartNo),
                    (isReplaceDiffPartFlagSet ? replacementPartChg :removePartChg),  refDes, false);
            
            finalMessageText = orderNoMessageText + FRAMEWORK_LINEFEED +reInstallPartMessageText + FRAMEWORK_LINEFEED + FRAMEWORK_LINEFEED;
        }
        
        return finalMessageText;
    }

    protected boolean isPartInstalledInTargetOrder(String orderId, String sourceOrderId,
                                                   String sourceAlterationId, String removePartNo,
                                                   String removePartChg, String refDes,
                                                   List serialLotUnitsList,
                                                   Number totalRemoveOrderPartQty, String calledFrom, 
                                                   Number targetOrderQty)
    {
        boolean isPartInstalled = false;
        ListIterator serialLotUnitsListIterator = serialLotUnitsList.listIterator();
        if (StringUtils.equals(calledFrom, ALT_PROP))
        {
            isPartInstalled = orderDao.isPartInstalledInOrderForAlterationPropagation(orderId,
                                                                                      removePartNo,
                                                                                      removePartChg,
                                                                                      totalRemoveOrderPartQty,
                                                                                      serialLotUnitsListIterator,
                                                                                      refDes,
                                                                                      sourceOrderId,
                                                                                      sourceAlterationId,
                                                                                      targetOrderQty);
        }
        else if (StringUtils.equals(calledFrom, ALT))
        {
            isPartInstalled = orderDao.isPartInstalledInOrder(orderId, removePartNo, removePartChg,
                                                              totalRemoveOrderPartQty,
                                                              serialLotUnitsListIterator, refDes);
        }
        return isPartInstalled;
    }

    
    protected Map prepareUniquePartMapFromRemovedPartList(List removePartList)
    {
        ListIterator removedPartList = removePartList.listIterator();
        Map removedUniquePartMap = new HashMap();
        while (removedPartList.hasNext())
        {
            Map removedPart = (Map) removedPartList.next();
            String removeItemId = (String) removedPart.get(DbColumnNameConstants.ITEM_ID);
            String refDes = (String) removedPart.get(REF_DES);
            
            if (!removedUniquePartMap.containsKey(removeItemId + "-" + refDes))
            {
                removedUniquePartMap.put(removeItemId + "-" + refDes, removedPart);
            }
        }
        return removedUniquePartMap;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfwid.application.IAlteration#runCopyAlterationAll(java.lang.String)
     */
    public void runCopyAlterationAll(String sourceOrderId)
    {
        runCopyAlterationAll(sourceOrderId, null, null);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfwid.application.IAlteration#runCopyAlterationAll(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public void runCopyAlterationAll(String sourceOrderId,
                                     String taskId,
                                     String notes)
    {
    	//FND-19116
        runCopyAlterationAll( sourceOrderId,
                              taskId,
                              notes,
                              null);
    }
    
    /* (non-Javadoc)
     * @see com.ibaset.solumina.sfwid.application.IAlteration#runCopyAlterationAll(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public void runCopyAlterationAll(String sourceOrderId,
                                     String taskId,
                                     String notes,
                                     String calledFrom)
    {
        String sourceOrderNumber = null;
        String targetAlterationId = null;
        String srcBuildPartNo = null;
        String srcBuildPartChg = null;

        try
        {
            Map orderNumber = orderDao.selectOrderDetails(sourceOrderId);

            sourceOrderNumber = (String) orderNumber.get(ORDER_NO);
            srcBuildPartNo = (String) orderNumber.get(PART_NO_KEY);
            srcBuildPartChg = (String) orderNumber.get(PART_CHG);
        }
        catch (EmptyResultDataAccessException erdae)
        {
            logger.error(" No Source order found while alteration propogation with OrderId : " + sourceOrderId + erdae);
        }

        List<Map> targetOrderList = selectCommaSeparatedTargetOrdersList(sourceOrderNumber);
        String semiCollonSeparatedTargetOrder = (String) targetOrderList.get(0).get("TARGET_ORDER_INFO");
        
        //FND-19116 when user click CANCEL button on "Copy alteration to All Work Orders? " UDV
        if(!stringEquals(calledFrom, CANCEL))
        {
            runCopyAlterationForSelectedTargetOrders(sourceOrderId, srcBuildPartNo, srcBuildPartChg, sourceOrderNumber, semiCollonSeparatedTargetOrder, taskId, notes);
        }
        else if(stringEquals(calledFrom, CANCEL))
        {
            
            List<String> targetOrderListOfString = Stream.of(semiCollonSeparatedTargetOrder.split(SEMI_COLON)).collect(Collectors.toList());
            createOrCloseHoldForFailedAlterationPropagation(sourceOrderId, 
                                                            calledFrom, 
                                                            sourceOrderNumber, null, null);

            // 05/10/2007 Prasad FND-6020
            // 06/09/2006 SamL FND 4619
            workflow.completeAlteration(taskId, YES, notes);

            event.refreshTool();
        }
    }
    
    /*
     * (non-Javadoc)
     * @see com.ibaset.solumina.sfwid.application.IAlteration#selectCommaSeparatedTargetOrdersList(java.lang.String)
     */
    public List selectCommaSeparatedTargetOrdersList(String sourceOrderNumber)
    {
        List returnList = new ArrayList();
        Map returnRow = new ListOrderedMap();
        String orderNo = EMPTY;
        
        List<Map> list = alterationDao.selectHoldOrderId(sourceOrderNumber);
        
        for(Map row :list )
        {
            orderNo = orderNo
                    + SEMI_COLON + row.get(ORDER_NO)
                    + COMMA + row.get(ORDER_ID)
                    + COMMA + row.get(PART_NO_KEY)
                    + COMMA + row.get(PART_CHG)
                    + COMMA + row.get(NEEDS_REVIEW_FLAG)
                    + COMMA + defaultIfEmpty((String)row.get(ALT_ID),EMPTY);
        
        }
        orderNo = removeStart(orderNo, SEMI_COLON);
        returnRow.put("TARGET_ORDER_INFO", orderNo);
        returnRow.put("SECURITY_GROUP", null);
        returnList.add(returnRow);
        return returnList;
    }
    
    //GE-5561
    public void runCopyAlterationForSelectedTargetOrders(String sourceOrderId, 
                                                         String srcBuildPartNo, 
                                                         String srcBuildPartChg,
                                                         String sourceOrderNumber,
                                                         String targetOrders,
                                                         String taskId,
                                                         String notes)
    {
        StringBuffer failedPropOrderNoList = new StringBuffer();
        StringBuffer failedPropOrderForErrorMessage = new StringBuffer();
        StringBuffer succeedPropOrderNoList = new StringBuffer();
        StringBuffer succeedPropOrdersForErrorMessage = new StringBuffer();
        
        targetOrders = defaultIfEmpty(targetOrders, EMPTY);
        List<String> targetOrderList = Stream.of(targetOrders.split(SEMI_COLON)).filter(str->!str.isEmpty()).collect(Collectors.toList());
        targetOrderList.forEach(targetOrderId -> propogateChanges(sourceOrderId,  
                                                                   srcBuildPartNo, 
                                                                   srcBuildPartChg,
                                                                   targetOrderId, 
                                                                   failedPropOrderNoList,
                                                                   failedPropOrderForErrorMessage,
                                                                   succeedPropOrderNoList, 
                                                                   succeedPropOrdersForErrorMessage));
        
        validateWorkOrderForAlterationPropagation(true,
                                                  succeedPropOrdersForErrorMessage.toString(),
                                                  failedPropOrderForErrorMessage.toString());        

        createOrCloseHoldForFailedAlterationPropagation(sourceOrderId,
                                                        null, // calledFrom
                                                        sourceOrderNumber,
                                                        succeedPropOrderNoList, 
                                                        failedPropOrderNoList);

        workflow.completeAlteration(taskId, YES, notes);

        event.refreshTool();
    }
    
    protected List<Map> getTargetOrdersAsListOfMap(List<String> targetOrderList)
    {
        List<Map> list = new ArrayList();
        Iterator itr = targetOrderList.iterator();
        while(itr.hasNext())
        {
            String s = (String) itr.next();
            
            String[] targetOrderInfo = (String[]) parse.parseFcn(s, COMMA).toArray(new String[6]);
            
            String targetOrderNumber = targetOrderInfo[0];
            String targetOrderId = targetOrderInfo[1];
            
            Map orderDescriptionMap = orderDao.selectOrderDetails(targetOrderId);
            Map<String,String> map = new HashMap();
            map.put(ORDER_NO, targetOrderNumber);
            map.put(ORDER_ID, targetOrderId);
            map.put(ALT_ID, (String)orderDescriptionMap.get(ALT_ID));
            
            list.add(map);
            
        }
        return list ;
    }
    
    public void propogateChanges(String sourceOrderId, 
                                 String srcBuildPartNo, 
                                 String srcBuildPartChg,
                                 String targetOrderInfoCommaSeparated, 
                                 StringBuffer failedPropOrderNoList,
                                 StringBuffer failedPropOrderForErrorMessage,
                                 StringBuffer succeedOrderNoList, 
                                 StringBuffer succeedPropOrdersForErrorMessage)
    {
        final IAlteration nestedAlteration = SoluminaServiceLocator.locateService(IAlteration.class, true);
        
        String[] targetOrderInfo = new String[6]; 
        targetOrderInfo = (String[]) parse.parseFcn(targetOrderInfoCommaSeparated, COMMA).toArray(new String[6]);
        
        String targetOrderNumber = targetOrderInfo[0];
        String targetOrderId = targetOrderInfo[1];
        String targetBuildPartNo = targetOrderInfo[2];
        String targetBuildPartChg = targetOrderInfo[3];
        
        historyControl.enableHistory();
        try
        {
            nestedAlteration.runCopyAlteration(sourceOrderId, targetOrderId);
        } 
        catch (Exception e) 
        {
            logger.error(String.format("Order alteration propagation from %s to %s failed.",sourceOrderId, targetOrderId), e);
            appendOrdersToSucceedOrFailedOrders(failedPropOrderNoList, targetOrderNumber, targetOrderId);
            appendOrdersToSucceedOrFailedOrdersForWarningMsg(failedPropOrderForErrorMessage, targetOrderNumber);
            return ;
        }
        
        // Gets the Needs Review Flag and Alteration Id after runCopyAlteration() method executes. Don't use these information from UDV.
        Map orderDescriptionMap = null;
        String targetReviewFlag = null;
        String targetAlterationId = null;
        try
        {
            orderDescriptionMap = orderDao.selectOrderDetails(targetOrderId);
            targetReviewFlag = (String) orderDescriptionMap.get(NEEDS_REVIEW_FLAG);
            targetAlterationId = (String) orderDescriptionMap.get(ALT_ID);
        }
        catch (EmptyResultDataAccessException erdae)
        {
            logger.error("Order not found for orderId :" + targetOrderId + erdae);
        }

        
        updateTaskStatusAndNeedsReviewFlag(targetOrderId,
                                           targetReviewFlag,
                                           targetAlterationId);
        
        appendOrdersToSucceedOrFailedOrders(succeedOrderNoList, targetOrderNumber, targetOrderId);
        // FND-20614
        if (!stringEquals(targetBuildPartNo, srcBuildPartNo)
                || !stringEquals(targetBuildPartChg, srcBuildPartChg))
        {
            appendOrdersToSucceedOrFailedOrdersForWarningMsg(succeedPropOrdersForErrorMessage, targetOrderNumber);
        }
    }

    protected void appendOrdersToSucceedOrFailedOrders(StringBuffer orderNoList,
                                                       String targetOrderNumber,
                                                       String targetOrderId)
    {
        String appendedOrderString = selectFailedPropagationOrderNoList(orderNoList.toString(),
                                                                      targetOrderNumber, 
                                                                      targetOrderId);
        orderNoList.replace(0, orderNoList.length(), appendedOrderString);
    }
    
    protected void appendOrdersToSucceedOrFailedOrdersForWarningMsg(StringBuffer orderNoList,
            										   String targetOrderNumber)
    {
    	String appendedOrderString = selectFailedPropagationOrderNoListForWarningMsg(orderNoList.toString(),
                           														 	 targetOrderNumber);
    	orderNoList.replace(0, orderNoList.length(), appendedOrderString);
    }

    protected void createOrCloseHoldForFailedAlterationPropagation(String sourceOrderId,
                                                                   String calledFrom,
                                                                   String sourceOrderNumber, 
                                                                   StringBuffer succeedPropOrderNoList, 
                                                                   StringBuffer failedPropOrderNoList)
    {
        //FND-22169
        //Get list of target orders for which alteration propagation failed and ALT_PROP hold is still open
        //If alt prop fails for any order, close Propagation Hold on the order, Complete Alteration & Create Order Hold
        List<String> succeedPropOrders = Stream.of(succeedPropOrderNoList.toString().split(SEMI_COLON)).filter(str->!str.isEmpty()).collect(Collectors.toList());
        List succeedPropOrdersListOfMap = getTargetOrdersAsListOfMap(succeedPropOrders);
        
        updatePropagationHoldsForCompletedAndFailedOrders(sourceOrderId, 
                                                          calledFrom, 
                                                          sourceOrderNumber, 
                                                          succeedPropOrdersListOfMap,
                                                          false); 
        
        List<String> failedPropOrders = Stream.of(failedPropOrderNoList.toString().split(SEMI_COLON)).filter(str->!str.isEmpty()).collect(Collectors.toList());
        List failedPropOrdersListOfMap = getTargetOrdersAsListOfMap(failedPropOrders);
        
        updatePropagationHoldsForCompletedAndFailedOrders(sourceOrderId, 
                                                          calledFrom, 
                                                          sourceOrderNumber, 
                                                          failedPropOrdersListOfMap,
                                                          true); 
        
        List<Map> removedOrdersList = alterationDao.selectHoldOrderId(sourceOrderNumber);
        removedOrdersList.forEach(removedOrder -> closePropagationHoldWhenCalledFromCancel(CANCEL,
                                                                                           sourceOrderNumber,
                                                                                           (String)removedOrder.get(ORDER_ID),
                                                                                           false));
    }

    protected void updatePropagationHoldsForCompletedAndFailedOrders(String sourceOrderId,
                                                                     String calledFrom,
                                                                     String sourceOrderNumber,
                                                                     List ordersList,
                                                                     boolean targetOrderFailed) {
        ListIterator iterator2 = ordersList.listIterator();
        while (iterator2.hasNext())
        {
            Map row = (Map) iterator2.next();
            String targetOrderId = (String) row.get(ORDER_ID);
            
            closePropagationHoldWhenCalledFromCancel(calledFrom,
                                                     sourceOrderNumber,
                                                     targetOrderId,
                                                     targetOrderFailed);

	        Map needsReviewFlagAndOrderStatus = completeAlterationIfTargetNeedsReviewFlagIsNo(targetOrderId);

            insertHoldfIfTargetOrderIsFailed(sourceOrderId,
                                             targetOrderId,
                                             targetOrderFailed,
                                             needsReviewFlagAndOrderStatus);
        }
    }

    protected void updateTaskStatusAndNeedsReviewFlag(String targetOrderId,
                                                      String targetReviewFlag,
                                                      String targetAlterationId)
    {
        if (stringEquals(targetReviewFlag, YES))
        {
            operationDao.updateNeedsReviewFlag(targetOrderId, NO);

            // Close the alteration and start a new alteration.
            Map taskIdMap = fndDao.selectAlterationTask(targetOrderId,
                                                        targetAlterationId);
            String targetTaskId = (String) taskIdMap.get(TASK_ID);

            fndTaskDao.updateTaskStatus(targetTaskId,
                                        upperCase(COMPLETE),
                                        ContextUtil.getUsername(),
                                        UPDATED);

            alterationDao.updateHoldStatusAndActualEndDate(CLOSED_STATUS,
                                                           targetOrderId,
                                                           ALTERATION,
                                                           targetAlterationId);

            // Creates an alteration header record and then puts the order on hold.
            String alterationId = null;
            alterationId = alterationDiscrepancy.startAlteration(targetOrderId,
                                                                 null,
                                                                 ORDER,
                                                                 alterationId);

            ofdWorkOrderDao.updateNeedsReviewFlag(targetOrderId,
                                                  YES,
                                                  NO);

            // 05/10/2007 Prasad end here.
        }
    }

    protected void insertHoldfIfTargetOrderIsFailed(String sourceOrderId,
                                                    String targetOrderId,
                                                    boolean targetOrderFailed,
                                                    Map needsReviewFlagAndOrderStatus)
    {
        //FND-22169
        if (targetOrderFailed)
        {
            // GE-5936
            String targetOrderNumber = (String) needsReviewFlagAndOrderStatus.get(ORDER_NO);
            String targetAlterationId = (String) needsReviewFlagAndOrderStatus.get(ALT_ID);
            String notes = message.buildUnformattedMessage(EMPTY, "MFI_91448860477A45D6B332AF81FBAAF1AF", targetOrderNumber, false);
            
            // Create Order Hold on this order as Propagation failed
            hold.insertHold(targetOrderId,
                            EMPTY,
                            "ORDER HOLD",
                            ORDER_STOP,
                            null,
                            notes, // GE-5936
                            targetOrderNumber,
                            null,
                            null,
                            targetAlterationId,
                            sourceOrderId,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            NO,
                            NO,
                            NO,
                            null);
        }
    }

    protected Map completeAlterationIfTargetNeedsReviewFlagIsNo(String targetOrderId)
    {
        Map needsReviewFlagAndOrderStatus = orderDao.selectOrderDetails(targetOrderId);

        String targetNeedsReviewFlag = (String) needsReviewFlagAndOrderStatus.get(NEEDS_REVIEW_FLAG);
        String targetAlterationId = (String) needsReviewFlagAndOrderStatus.get(ALT_ID);
        
        if (stringEquals(targetNeedsReviewFlag, NO))
        {
            Map taskMap = alterationDao.selectTaskId(targetOrderId,
                                                     targetAlterationId);

            if (!taskMap.isEmpty())
            {
                String tempTaskId = (String) taskMap.get(TASK_ID);

                workflow.completeAlteration(tempTaskId, null, null);
            }
        }
        return needsReviewFlagAndOrderStatus;
    }

    protected void closePropagationHoldWhenCalledFromCancel(String calledFrom,
                                                            String sourceOrderNumber,
                                                            String targetOrderId,
                                                            boolean targetOrderFailed)
    {
        //FND-19116 when user click CANCEL button, close the Propagation Hold for target Work Orders
        if(stringEquals(calledFrom, CANCEL) || targetOrderFailed)
        {
            String holdId = alterationDao.selectHoldIdWithHoldRef1(targetOrderId,
            		                                               sourceOrderNumber);

            hold.closeHold(targetOrderId,
        		           null,
        		           holdId,
        		           null,
        		           ALT_PROP,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null,
        		           null);
        }
    }

    protected void validateWorkOrderForAlterationPropagation(boolean targetFound,
                                                             String targetOrderNoList,
                                                             String failedPropOrderNoList)
    {
        // FND-20614
        if(!targetOrderNoList.isEmpty())
        {
            // Build Part is changed in ORDERS %V1
            message.showMessage("MFI_5F9159E54D424AD0E0440003BA041A64", targetOrderNoList);
        }

        if(!failedPropOrderNoList.isEmpty())
        {
            // Alteration Propagation failed for Orders - %V1
            message.showMessage("MFI_71BA1EEE9CB54C1B968F415A8682687A", failedPropOrderNoList);
        }
        else
        {
            if (!targetFound)
            {
                // No Work order Included to Alteration Propagation!
                message.raiseError("MFI_67");
            }	            
        }
    }

    protected String selectFailedPropagationOrderNoList(String failedPropOrderNoList,
                                                        String targetOrderNumber, 
                                                        String targetOrderId)
    {
        if (isEmpty(failedPropOrderNoList))
        {
            failedPropOrderNoList = targetOrderNumber + COMMA + targetOrderId;
        }
        else
        {
            failedPropOrderNoList = failedPropOrderNoList
                    + SEMI_COLON + BLANK + targetOrderNumber + COMMA + targetOrderId;
        }
        return failedPropOrderNoList;
    }
    protected String selectFailedPropagationOrderNoListForWarningMsg(String failedPropOrderNoList,
            													     String targetOrderNumber)
    {
    	if (isEmpty(failedPropOrderNoList))
    	{
    		failedPropOrderNoList = targetOrderNumber;
    	}
    	else
    	{
    		failedPropOrderNoList = failedPropOrderNoList
    				+ SEMI_COLON + BLANK + targetOrderNumber;
    	}
    	return failedPropOrderNoList;
}

    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfwid.application.IAlteration#updateAlteration(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String)
     */
    public void updateAlteration(String alterationId,
                                 String alterationReason,
                                 String changeAuthorizationType,
                                 String changeAuthorizationNumber)

    {
        updateAlteration(alterationId,
                         alterationReason,
                         changeAuthorizationType,
                         changeAuthorizationNumber,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null,
                         null);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfwid.application.IAlteration#updateAlteration(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.Number, java.lang.Number, java.lang.Number,
     *      java.lang.Number, java.lang.Number, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String, java.util.Date, java.util.Date, java.util.Date,
     *      java.util.Date, java.util.Date)
     */
    public void updateAlteration(String alterationId,
                                 String alterationReason,
                                 String changeAuthorizationType,
                                 String changeAuthorizationNumber,
                                 String ucfAlterationVch1,
                                 String ucfAlterationVch2,
                                 String ucfAlterationVch3,
                                 String ucfAlterationVch4,
                                 String ucfAlterationVch5,
                                 String ucfAlterationVch6,
                                 String ucfAlterationVch7,
                                 String ucfAlterationVch8,
                                 String ucfAlterationVch9,
                                 String ucfAlterationVch10,
                                 String ucfAlterationVch11,
                                 String ucfAlterationVch12,
                                 String ucfAlterationVch13,
                                 String ucfAlterationVch14,
                                 String ucfAlterationVch15,
                                 Number ucfAlterationNumber1,
                                 Number ucfAlterationNumber2,
                                 Number ucfAlterationNumber3,
                                 Number ucfAlterationNumber4,
                                 Number ucfAlterationNumber5,
                                 String ucfAlterationFlag1,
                                 String ucfAlterationFlag2,
                                 String ucfAlterationFlag3,
                                 String ucfAlterationFlag4,
                                 String ucfAlterationFlag5,
                                 Date ucfAlterationDate1,
                                 Date ucfAlterationDate2,
                                 Date ucfAlterationDate3,
                                 Date ucfAlterationDate4,
                                 Date ucfAlterationDate5)
    {
    	this.updateAlteration(alterationId, 
    						  alterationReason, 
    						  // changeAuthorizationType, FND-25795 
    						  changeAuthorizationNumber, 
    						  ucfAlterationVch1, 
    						  ucfAlterationVch2, 
    						  ucfAlterationVch3, 
    						  ucfAlterationVch4, 
    						  ucfAlterationVch5, 
    						  ucfAlterationVch6, 
    						  ucfAlterationVch7, 
    						  ucfAlterationVch8, 
    						  ucfAlterationVch9, 
    						  ucfAlterationVch10, 
    						  ucfAlterationVch11, 
    						  ucfAlterationVch12, 
    						  ucfAlterationVch13, 
    						  ucfAlterationVch14, 
    						  ucfAlterationVch15, 
    						  ucfAlterationNumber1, 
    						  ucfAlterationNumber2, 
    						  ucfAlterationNumber3, 
    						  ucfAlterationNumber4, 
    						  ucfAlterationNumber5, 
    						  ucfAlterationFlag1, 
    						  ucfAlterationFlag2, 
    						  ucfAlterationFlag3, 
    						  ucfAlterationFlag4, 
    						  ucfAlterationFlag5, 
    						  ucfAlterationDate1, 
    						  ucfAlterationDate2, 
    						  ucfAlterationDate3, 
    						  ucfAlterationDate4, 
    						  ucfAlterationDate5, 
    						  null, 
    						  null, 
    						  null, 
    						  null, 
    						  null);
    }
    
    //FND-22919
    public void updateAlteration(String alterationId,
			 				     String alterationReason,
			 				     String alterationNo, // FND-25795
			 				     String ucfAlterationVch1,
			 				     String ucfAlterationVch2,
			 				     String ucfAlterationVch3,
			 				     String ucfAlterationVch4,
			 				     String ucfAlterationVch5,
			 				     String ucfAlterationVch6,
			 				     String ucfAlterationVch7,
			 				     String ucfAlterationVch8,
			 				     String ucfAlterationVch9,
			 				     String ucfAlterationVch10,
			 				     String ucfAlterationVch11,
			 				     String ucfAlterationVch12,
			 				     String ucfAlterationVch13,
			 				     String ucfAlterationVch14,
			 				     String ucfAlterationVch15,
			 				     Number ucfAlterationNumber1,
			 				     Number ucfAlterationNumber2,
			 				     Number ucfAlterationNumber3,
			 				     Number ucfAlterationNumber4,
			 				     Number ucfAlterationNumber5,
			 				     String ucfAlterationFlag1,
			 				     String ucfAlterationFlag2,
			 				     String ucfAlterationFlag3,
			 				     String ucfAlterationFlag4,
			 				     String ucfAlterationFlag5,
			 				     Date ucfAlterationDate1,
			 				     Date ucfAlterationDate2,
			 				     Date ucfAlterationDate3,
			 				     Date ucfAlterationDate4,
			 				     Date ucfAlterationDate5,
			 				     String ucfAlterationVch2551,
			 				     String ucfAlterationVch2552,
			 				     String ucfAlterationVch2553,
			 				     String ucfAlterationVch40001,
			 				     String ucfAlterationVch40002)
    {
        validator.checkUserPrivilege("ORDER_ALT_INITIATION");

        /** * START Nikunj** */
        // 10/03/2006 Vishal - JIRA#FND-5368
        boolean alterationDescriptionExists = alterationDao.selectAlterationDescriptionExists(alterationId,
                                                                                              upperCase(COMPLETE));

        if (alterationDescriptionExists)
        {
            // Raises an error when Alteration doesn't exist or has an empty
            // Alteration Status.
            message.raiseError("MFI_50361");
        }

        /** * END Nikunj** */

        /** * Start Ankur ** */
        // 05/16/2006 Prasad FND-4561.check for chg auth num.
        validator.checkMandatoryField("Alteration No",
                                       alterationNo, // FND-25795
                                      "MFI_13D9F1EFDF416498E0440003BA560E35");

        // Update chg auth num for hold_ref2
        String holdId = alterationDao.selectHoldIdWithAltId(alterationId);
        alterationDao.updateHoldRef2(alterationNo, holdId);

        // 03/16/2006 IAS Added new UCF fields to set
        alterationDao.updateAlterationDescription(alterationReason,
                                                  alterationNo, // FND-25795
                                                  ucfAlterationVch1,
                                                  ucfAlterationVch2,
                                                  ucfAlterationVch3,
                                                  ucfAlterationVch4,
                                                  ucfAlterationVch5,
                                                  ucfAlterationVch6,
                                                  ucfAlterationVch7,
                                                  ucfAlterationVch8,
                                                  ucfAlterationVch9,
                                                  ucfAlterationVch10,
                                                  ucfAlterationVch11,
                                                  ucfAlterationVch12,
                                                  ucfAlterationVch13,
                                                  ucfAlterationVch14,
                                                  ucfAlterationVch15,
                                                  ucfAlterationNumber1,
                                                  ucfAlterationNumber2,
                                                  ucfAlterationNumber3,
                                                  ucfAlterationNumber4,
                                                  ucfAlterationNumber5,
                                                  ucfAlterationFlag1,
                                                  ucfAlterationFlag2,
                                                  ucfAlterationFlag3,
                                                  ucfAlterationFlag4,
                                                  ucfAlterationFlag5,
                                                  ucfAlterationDate1,
                                                  ucfAlterationDate2,
                                                  ucfAlterationDate3,
                                                  ucfAlterationDate4,
                                                  ucfAlterationDate5,
                                                  alterationId,
                                                  ucfAlterationVch2551,
                                                  ucfAlterationVch2552,
                                                  ucfAlterationVch2553,
                                                  ucfAlterationVch40001,
                                                  ucfAlterationVch40002);
        /** * End Ankur ** */
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfwid.application.IAlteration#startAlteration(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String)
     */
    public String startAlteration(String orderId,
                                  String alterationReason,
                                  String alterationType,
                                  String alternateId)
    {
        return null;
        // TODO: This method is obsolete
//        return alterationDiscrepancy.startAlteration(orderId,
//                                                     alterationReason,
//                                                     alterationType,
//                                                     alternateId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfwid.application.IStart#alterateStart(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public String alterateStart(String orderId,
                                String alterationReason,
                                String alterationType,
                                String alterationId,
                                String changeAuthorizationType,
                                String changeAuthorizationNumber)

	{
		return orderAlteration.alterateStart(orderId, alterationReason, alterationType, alterationId,
				changeAuthorizationType, changeAuthorizationNumber, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, NO, null, null, null, null, null, null);
	}

    // 06-02-2011 TASK-17572
    /*
     * (non-Javadoc)
     * @see com.ibaset.solumina.sfwid.application.IAlteration#updateTask(java.lang.String, java.lang.String)
     */
    public void updateTask( String taskId,
		    				String type)
	{
    	updateTask(taskId, type, null, null);
	}
    
    // 06-08-2011 TASK-17572
    /*
     * (non-Javadoc)
     * @see com.ibaset.solumina.sfwid.application.IAlteration#updateTaskPre(java.lang.String, java.lang.String)
     */
    public void updateTaskPre( String orderId,
    						   String type)
	{
    	// Initialize the local variables.
    	String taskId = null;
    	String udvId = null;
    	boolean showDialog = false;
        StringBuffer parameters = new StringBuffer();
        
        // When the Type is CLAIM.
        if (stringEquals(type,
                               upperCase(CLAIM)))
        {
        	List claimRemainList = alterationDao.selectClaimRemainList(orderId);
        	
        	ListIterator claimIterator = claimRemainList.listIterator();
        	
        	// Gets the size of the list.
        	// Claim Remain count.        	
        	int claimRemainNo = claimRemainList.size();
        	
        	// If Claim Remain count is one then need to auto accept.
        	// else needs to show udv.
        	if(claimRemainNo == 1)
        	{
	        	while (claimIterator.hasNext())
	            {
	                Map claimRemainMap = (Map) claimIterator.next();
	                taskId = (String)claimRemainMap.get("TASK_ID");
	            }
        	}
        	else if( claimRemainNo > 1)
        	{
        		showDialog = true;
        		udvId = "MFI_4AC26752770B4824AF7C0B4F88DF5A8E";
        	}
        }
        // When the Type is RELEASE.
        else if (stringEquals(type,
                                    upperCase(SoluminaConstants.RELEASE)))
        {
        	List releaseRemainList = alterationDao.selectReleaseRemainList(orderId);
        	
        	ListIterator releaseIterator = releaseRemainList.listIterator();
        	
        	// Gets the size of the list.
        	// Release Remain count.
        	int releaseRemainNo = releaseRemainList.size();
        	
        	// If Release Remain count is one then need to auto accept.
        	// else needs to show udv.
        	if(releaseRemainNo == 1)
        	{
	        	while (releaseIterator.hasNext())
	            {
	                Map releaseRemainMap = (Map) releaseIterator.next();
	                taskId = (String)releaseRemainMap.get("TASK_ID");
	            }
        	}
        	else if( releaseRemainNo > 1)
        	{
        		showDialog = true;
        		udvId = "MFI_760F9AC455DB4276A2F1E12276A8D3A0";
        	}
        }
        
        // Show Dialog Box for Confirmation
        if (showDialog)
        {
            parameters.append("TASK_ID=")
		              .append(taskId);
		   
	        event.showUdv( udvId,
	                       INSERT,
	                       "",
	                       null,
	                       parameters.toString());
        }
        else
        {
        	// Updates the Task.
        	updateTask(taskId, type, null, null);
        }
	}
    
    /*
	 * (non-Javadoc)
	 * @see com.ibaset.solumina.sfwid.application.IAlteration#updateTask(java.lang.String, java.lang.String, java.lang.String)
	 */
	/**
	 * @deprecated Use {@link #updateTask(String,String,String,String)} instead
	 */
	public void updateTask( String taskId,
						    String type,
							String notes)  // 06-02-2011 TASK-17572 Added Notes
	{
		updateTask(taskId, type, notes, null);
	}

	/*
     * (non-Javadoc)
     * @see com.ibaset.solumina.sfwid.application.IAlteration#updateTask(java.lang.String, java.lang.String, java.lang.String)
     */
    public void updateTask( String taskId,
    					    String type,
    						String notes,  // 06-02-2011 TASK-17572 Added Notes
    						String assignedToUserId)
    {
        // Declares the local variable.
        String queueType = null;
        String taskType = null;

        //FND-28054
        if(isEmpty(assignedToUserId) &&
        		stringEquals(type,upperCase(ASSIGN)))
        {
        	type = upperCase(SoluminaConstants.RELEASE);
        }	
        
        try
        {
        	// Gets Task Information.
        	Map task = alterationDao.selectTask(taskId);
            queueType = (String) task.get("QUEUE_TYPE");
            taskType = (String) task.get("TASK_TYPE");                        
        }
        catch (EmptyResultDataAccessException exception)
        {
            // Raises and error when the Queue Type does not exist.
            message.raiseError("INVALID_TASK_STATUS_CHANGE", type);
        }

        // Checks whether user has Queue Type privilege or not.
        boolean userPrivilegeFlag = privilege.hasPrivilege(queueType);
        
        //Defect 468. ORDER_AUTHORING and ORDER_AUTHORING2 no longer required to release privs
        if ( (queueType.equals("ORDER_AUTHORING") || queueType.equals("ORDER_AUTHORING2")) && type.equals("RELEASE"))
        {
        	userPrivilegeFlag = true;
        }

        if (!userPrivilegeFlag)
        {
            // Raises an error when user doe not have Queue Type privilege.
            message.raiseError("NO_AUTH_UPDATE_TASK");
        }

        // Gets the User Name.
        String userId = ContextUtil.getUsername();

        // When the Type is CLAIM.
        if (stringEquals(type,
                               upperCase(CLAIM)))
        {
            // Update the Task.
            alterationDao.updateTask(userId, notes, userId, taskId, C); // 06-02-2011 TASK-17572
        }
        // When the Type is RELEASE.
        else if (stringEquals(type,
                                    upperCase(SoluminaConstants.RELEASE)))
        {
            // Update the Task.
            alterationDao.updateTask(null, notes, userId, taskId, null); // 06-02-2011 TASK-17572
        }
        // When the Type is ASSIGN.
        else if (stringEquals(type,
                                    upperCase(ASSIGN)))
        {
            // Update the Task.
            alterationDao.updateTask(assignedToUserId, notes, userId, taskId, A); // 06-02-2011 TASK-17572
        }
        // 01-04-2011 FND-16122
        // Update Last Activity Time Stamp
        task.updateLastActivityTimeStamp(taskType, 
        								 taskId);

        // 01/12/2012 FND-19450
        // Commented Refresh Tool and Refreshed using Aux UDV
        event.refresh_alias("DispTaskSelect+@AuxUdv");
//        event.refreshTool(); // 06-02-2011 TASK-17572
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfwid.application.IAlteration#getType(java.lang.String)
     */
    public String getType(String orderId)
	{
		return alterationGet.getType(orderId);
	}

    /*
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfwid.application.IAlteration#returnPriorTask(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public void returnPriorTask(String taskId,
                                String orderId,
                                String alterationId,
                                String currentQueueType,
                                String returnQueueType,
                                String notes)
    {
        // Checks whether user has Queue Type privilege or not.
        boolean userPrivilegeFlag = privilege.hasPrivilege(currentQueueType);

        if (!userPrivilegeFlag)
        {
            // Raises an error when user doe not have Queue Type privilege.
            message.raiseError("MFI_53125");
        }

        // Checks for Mandatory Field.
        validator.checkMandatoryField("Return to Queue Type", returnQueueType);

        // Checks whether the work order is in alteration or not.
        checkAlterationTaskAndDescriptionExists(taskId, orderId, alterationId);

        // Gets the User Name in local variable.
        String userId = ContextUtil.getUsername();

        // Gets the Communication Id in a List.
        updateCommunication(taskId, userId);

        // Cancels the Task.
        fndTaskDao.updateTask(taskId,
					   		  CANCEL,
					   		  notes,
					   		  userId,
					   		  UPDATED);
        
        // FND-15621
        String taskType = task.selectTaskTypeByTaskId(taskId);
        String initialQueueType = queue.selectQueueType(taskType, taskType);
        
        // Updates the ALT_STATUS in OPER_DESC table if this is operation level
        // alteration.
        updateOperationOrOrderLevelAlterationStatus(orderId, 
        											alterationId, 
        											returnQueueType, 
        											initialQueueType);
        // FND-18711
        Map orderDetails = orderDao.selectOrderDetails(orderId);
        String documentType = (String) orderDetails.get("DOC_TYPE");

        // Inserts the Task.
        task.insertTask(null,
                        taskType,
                        returnQueueType,
                        ACTIVE_STATUS,
                        null, // FND-18942
                        userId,
                        null,
                        null,
                        null,
                        orderId,
                        alterationId,
                        null,
                        null,
                        documentType); // FND-18711

        Map documentInfoMap = documentTypeDao.selectOrderDocumentInformation(taskId);

        String documentSubType = (String) documentInfoMap.get("DOC_SUB_TYPE");
        String documentWorkFlow = (String) documentInfoMap.get("WORK_FLOW");

        List validFromQueueEditModesStatusList = planDao.selectEditModes(currentQueueType,
    			  														   documentType,
    			  														   documentSubType,
    			  														   documentWorkFlow);

        List validToQueueEditModesStatusList = planDao.selectEditModes(returnQueueType,
    			  														 documentType,
    			  														 documentSubType,
    			  														 documentWorkFlow);
        eventLaunchAfterReturnPriorTask(orderId, 
									    validFromQueueEditModesStatusList, 
									    validToQueueEditModesStatusList);
    }
    
	public void eventLaunchAfterReturnPriorTask(String orderId, 
											    List validFromQueueEditModesStatusList,
											    List validToQueueEditModesStatusList) 
	{
		boolean isLaunch = false;

        if ((validFromQueueEditModesStatusList == null || validFromQueueEditModesStatusList.size() == 0)
	        && (validToQueueEditModesStatusList != null && validToQueueEditModesStatusList.size() > 0))
        {
        	isLaunch = true;
        }

        // 06/30/2007 Pratibha FND-6467 Refresh tool
        if (isLaunch)
        {
        	event.launchManufacturing(orderId, EDIT);
        } else
        {
    		  event.refreshTool();
        }
	}

	public void updateOperationOrOrderLevelAlterationStatus(String orderId, 
															String alterationId, 
															String returnQueueType,
															String initialQueueType) 
	{
		// Gets the Alteration Type.
        String alterationType = getType(orderId);
        if (stringEquals(alterationType,
                               upperCase(OPERATION)))
        {
            // Updates the Operation Alteration Status.
            alterationDao.updateOperationAlterationStatus(returnQueueType,
                                                          orderId,
                                                          INTEGER_MINUS_ONE,
                                                          alterationId);
        }
        else
        {
            // taskType = ALTERATION;

            // Updates the Order Alteration Status.
            alterationDao.updateOrderAlterationStatus(returnQueueType,
                                                      orderId,
                                                      alterationId);

            if (stringEquals(returnQueueType, initialQueueType))
            {
                // Includes in Alteration.
            	orderAlteration.includeInAlteration(orderId);
            }
        }
	}

	public void updateCommunication(String taskId, String userId) 
	{
		List communicationList = alterationDao.selectCommunicationId(taskId);

        Iterator communicationIterator = communicationList.listIterator();
        // Gets the Iterator for Communication Id List.

        // For each row.
        while (communicationIterator.hasNext())
        {
            Map communicationMap = (Map) communicationIterator.next();
            // Gets the Communication Id in local variable.
            String communicationId = (String) communicationMap.get("COMM_ID");

            // Updates the Communication.
            alterationDao.updateCommunication(CANCEL,
                                              userId,
                                              communicationId);
        }
	}

	public void checkAlterationTaskAndDescriptionExists(String taskId, String orderId, String alterationId) 
	{
		boolean alterationTaskAndDescriptionExists = alterationDao.selectAlterationTaskAndDescriptionExists(orderId,
                                                                                                            alterationId,
                                                                                                            taskId,
                                                                                                            IN_PROCESS);
        if (!alterationTaskAndDescriptionExists)
        {
            // Raises an error when Alteration Task and Description does not
            // exist.
            message.raiseError("MFI_67194");
        }
	}

    /*
     * Implementation of SQL_ID = widscope
     * 
     * (non-Javadoc)
     * 
     * @see com.ibaset.solumina.sfwid.application.IAlteration#getWidScope(java.lang.String)
     */
    public List getWidScope(String orderId)
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see com.ibaset.solumina.sfwid.application.IAlteration#alterationTextTypeCheck(java.lang.String, 
     * java.lang.Number, java.lang.String)
     */
    /**
     * @deprecated Use {@link #alterationTextTypeCheck(String,Number,String,String)} instead
     */
    public void alterationTextTypeCheck(String discrepancyId,
    									Number discrepancyLineNumber,
    									String textType)
    {
        alterationTextTypeCheck(discrepancyId,
                                discrepancyLineNumber,
                                textType,
                                null);
    }

    /*
     * (non-Javadoc)
     * @see com.ibaset.solumina.sfwid.application.IAlteration#alterationTextTypeCheck(java.lang.String, 
     * java.lang.Number, java.lang.String)
     */
    public void alterationTextTypeCheck(String discrepancyId,
    									Number discrepancyLineNumber,
    									String textType, 
    									String calledFrom)
    {
        // Checks whether the Text type is Disposition Text or not.
        if (isNotEmpty(textType)
                && contains(textType.toUpperCase(),
                                        DISP)
                && stringEquals(calledFrom, WO))
        {
            // Disposition Instructions cannot be changed through Work Order
            // Alteration.
            message.raiseError("MFI_64AAC046F5934917E0440003BA041A64");
        }
        else if(isEmpty(textType))
        {
        	String discrepancyLineStatus = null;
        	String dispositionInstructionType = null;
        	String dispositionOrderId = null;
        	String appendToOperationNo = null;
        	String appendToStepNo = null;
        	
        	// Gets the Discrepancy Line Status.
            List discrepancyLineList = qaDiscrepancyDao.selectDiscrepancyLineStatus(discrepancyId,
                                                                                    discrepancyLineNumber);
            ListIterator it = discrepancyLineList.listIterator();
            if (it.hasNext())
            {
                Map map = (Map) it.next();
                discrepancyLineStatus = (String) map.get("DISC_LINE_STATUS");
                dispositionInstructionType = (String) map.get("DISP_INSTR_TYPE");
                dispositionOrderId = (String) map.get("DISP_ORDER_ID");
                appendToOperationNo = (String) map.get("APPEND_TO_OPER_NO");
                appendToStepNo = (String) map.get("APPEND_TO_STEP_NO");
            }
            
            // When Discrepancy Line Status is COMPLETE.
            if (stringEquals(discrepancyLineStatus,
                                   DISPOSITIONED.toUpperCase()) || // FND-20657
                      stringEquals(discrepancyLineStatus,
                                           IMPLEMENTED) ||
                      stringEquals(discrepancyLineStatus,
                                           CANCEL))
            {
            	// Disposition Work Instructions cannot be updated in Discrepancy Item with status %V1.
            	message.raiseError("MFI_7E36A5541D5D4C43E04400144FA7B7D2", discrepancyLineStatus);
            }
            
            //FND-27989
            if(stringEquals(dispositionInstructionType, APPEND_TO_OPERATION))
            {
            	if(isEmpty(dispositionOrderId))
            	{
            		// GE-5511
            		// Please provide Disposition Order No in Disposition Instructions Section
            		message.raiseError("MFI_88A929197B544F0DABCF593FFEC1F301");
            	}
            	else if(isEmpty(appendToOperationNo))
            	{
            		// GE-5511
            		// Please provide Append to Operation No in Disposition Instructions Section
            		message.raiseError("MFI_1C2FDEC27FDC47BEA924C8BCAEDFEE52");
            	}
            	else if(isEmpty(appendToStepNo))
            	{
            		// GE-5511
            		// Please provide Append to Section in Disposition Instructions Section
            		message.raiseError("MFI_9DB6D1FD6A5C4B7C9630990F3B0AE504");
            	}
            }
        }   

    }
    
    /* (non-Javadoc)
     * @see com.ibaset.solumina.sfwid.application.IAlteration#includeExcludeFAIOperation(java.lang.String, java.lang.String, java.lang.String)
     */
    public void includeExcludeFAIOperation(String orderId,
                                           String faiOperationRequired,
                                           String currentOrderStatus)
    {
        //FND-25450
    }
    
    /*
     * (non-Javadoc)
     * @see com.ibaset.solumina.sfwid.application.IAlteration#validateOperationOverlapFlag(java.lang.String, java.lang.String, java.lang.String)
     */
    public void validateOperationOverlapFlag(String orderId,
                                             String operationOverlapFlag,
                                             String oldOperationOverlapFlag)
    {
         // FND-22135
         // Selects the Order Status as well as Serial Flag
         Map orderDetails = (Map) orderDao.selectOrderDetails(orderId);
         String orderStatus = (String) orderDetails.get("ORDER_STATUS");
         String serialFlag = (String) orderDetails.get("SERIAL_FLAG");
         
         if(stringEquals(serialFlag, YES) &&
                 stringEquals(operationOverlapFlag, YES))
         {
             message.raiseError("MFI_78CBAD033932445599E2B6765197B89D");
         }
        
         if ((!stringEquals(operationOverlapFlag, oldOperationOverlapFlag)) 
                && (!stringEquals(orderStatus, IN_QUEUE)
                       && !stringEquals(orderStatus, PENDING)))
         {
        	 // GE-5511
        	 // The Operation Overlap Flag cannot be updated after the work order has been made active
             message.raiseError("MFI_B9DB7E4D257A44A6BF39DFD654E0B2C6");
         }
        
         // FND-24314
         // Restrict update of operationOverlapFlag in work order which are included in the Group Job.
         if((!stringEquals(operationOverlapFlag, oldOperationOverlapFlag))
        		 && stringEquals(operationOverlapFlag, YES))
         {
        	 boolean operIncludedGroupJobExists = operationDao.selectOperIncludedIntoGroupJobExists(orderId);

        	 if(operIncludedGroupJobExists)
        	 {
        		 //Operation Overlap Flag cannot be updated to 'Y' after operation(s) has been added for the group job
        		 message.raiseError("MFI_9712664A6840449B871D6E210F8C8937");
        	 }
         }      
    }
    
    /*
     * (non-Javadoc)
     * @see com.ibaset.solumina.sfwid.application.IAlteration#doActionWhenOperationOverlapFlagIsUpdated(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    public void doActionWhenOperationOverlapFlagIsUpdated (String orderId,
                                                         String operationOverlapFlag,
                                                         String showMessage)//FND-24314
    {
        
        if(stringEquals(operationOverlapFlag, YES))
        {   
            // FND-22732 Raises error to update operationOverlapFlag = 'Y', if Return Node Exists
            boolean returnNodeExists = ofdDao.selectNodeExists(orderId, RETURN + PERCENT_SIGN);
            
            if(returnNodeExists && stringEquals( operationOverlapFlag, YES ))
            {
            	// GE-5512
                // Cannot change Operation Overlap Flag to 'Y' for Work Order because Return Node exists
                message.raiseError("MFI_7B2DAABBD01447638F040AB19F2931AC");
            }
            
            // Low TOuch Material Handling is not allowed for Continuous Flag = Y
            int noOfRowsUpdated = orderDao.updateUnitProcessing(orderId);
            if(noOfRowsUpdated > 0)
            {
            	// GE-5513
                // Operation Unit Processing Low Touch is updated to "Normal" as Low Touch is not allowed when Operation Overlap Flag is Y
                message.showMessage("MFI_53F6D71D9EB14485837F4A46C963FECC");
            }
            
            // Auto Start and Auto Complete Operations are not allowed for Continuous Flag = Y
            int noOfRowsUpdt = orderDao.updateAutoStartCompleteFlags(orderId);
            if(noOfRowsUpdt > 0)
            {
                //Operation Auto Start and Auto Complete Flags are set to N as these flags are not allowed when Continuous Flow Flag is Y
                message.showMessage("MFI_6D9D6D80C0BD4BD4B29A42B0280D080C");
            }
            

            // FND-22139 Update the Available Qty and Missing Qty for Starting Operation
            Map orderDetails = orderDao.selectOrderDetails( orderId );
            Number orderQuantity = (Number) orderDetails.get("ORDER_QTY");
            Number orderScrapQuantity = (Number) orderDetails.get("ORDER_SCRAP_QTY");
            
            Number operAvailableQty = new Double(orderQuantity.doubleValue() - orderScrapQuantity.doubleValue());
            
            orderDao.updateOperationAvailableQuantity(orderId, 
                                                      operAvailableQty);
            
            int recordUpdt = operationDao.updateOperationBatchFlag(orderId);
            if(stringEquals(showMessage, YES)
            		&& recordUpdt > 0)
            {
            	//Operation Batch Flag is set to N as this flag is not allowed when Continuous Flow Flag is Y
            	message.showMessage("MFI_8019A114CD6742FDA20C7AB0A9BFA396");
            }
        }
        
        
        else
        {
            // PIND-34 Off Reconcile Scrap is not allowed for Continuous Flag = N
            int noOfRowsUpdated = orderDao.updateReconcileScrap(orderId);
            
            if(noOfRowsUpdated > 0)
            {
                //Operation Reconcile Scrap is updated to 'Off' as Continuous Flow Flag is N
                message.showMessage("MFI_0E4CD7B11C764B10891D0D53EBD86147");
            }
            
            // FND-22139 Update Operation available Qty, Complete Qty and Missing Qty
            orderDao.updateOperationQuantities(orderId, 
                                               INTEGER_ZERO, 
                                               INTEGER_ZERO, 
                                               INTEGER_ZERO);
        }
    }    

    public void setAlterationDao(IAlterationDao alterationDao)
    {
        this.alterationDao = alterationDao;
    }

    public void setEvent(IEvent event)
    {
        this.event = event;
    }

    public void setHold(IHold hold)
    {
        this.hold = hold;
    }

    public void setMessage(IMessage message)
    {
        this.message = message;
    }

    public void setOfd(IOfd ofd)
    {
        this.ofd = ofd;
    }

    public void setOrder(IOrder order)
    {
        this.order = order;
    }

    public void setPlanObject(IPlanObject planObject)
    {
        this.planObject = planObject;
    }

    public void setPrivilege(IUser privilege)
    {
        this.privilege = privilege;
    }

    /**
     * Sets the IValidator
     * 
     * @param validator
     *            the IValidator
     */
    public void setValidator(IValidator validator)
    {
        this.validator = validator;
    }

    public void setWorkflow(IWorkflow workflow)
    {
        this.workflow = workflow;
    }

    public void setOrderDetails(IOrderDetails orderDetails)
    {
        this.orderDetails = orderDetails;
    }

    /**
     * @param alterationDiscrepancy
     *            the alterationDiscrepancy to set
     */
    public void setAlterationDiscrepancy(IAlterationDiscrepancy alterationDiscrepancy)
    {
        this.alterationDiscrepancy = alterationDiscrepancy;
    }

    /**
     * Sets the IQueue.
     * 
     * @param queue
     *            the IQueue
     */
    public void setQueue(IQueue queue)
    {
        this.queue = queue;
    }

    /**
     * Sets the ITask.
     * 
     * @param task
     *            the ITask
     */
    public void setTask(ITask task)
    {
        this.task = task;
    }

    /**
     * Sets the IHistoryControl.
     * 
     * @param historyControl
     *            the IHistoryControl
     */
    public void setHistoryControl(IHistoryControl historyControl)
    {
        this.historyControl = historyControl;
    }

    /**
     * Sets the IOrderDao.
     * 
     * @param orderDao
     *            the IOrderDao
     */
    public void setOrderDao(IOrderDao orderDao)
    {
        this.orderDao = orderDao;
    }

    /**
     * Sets the IFndDao.
     * 
     * @param fndDao
     *            the IFndDao
     */
    public void setFndDao(IFndDao fndDao)
    {
        this.fndDao = fndDao;
    }

    /**
     * Sets the IOfdWorkOrderDao.
     * 
     * @param ofdWorkOrderDao
     *            the IOfdWorkOrderDao
     */
    public void setOfdWorkOrderDao(IOfdWorkOrderDao ofdWorkOrderDao)
    {
        this.ofdWorkOrderDao = ofdWorkOrderDao;
    }

    public void setOperationDao(IOperationDao operationDao)
    {
        this.operationDao = operationDao;
    }

    public void setFndTaskDao(com.ibaset.solumina.sffnd.dao.ITaskDao fndTaskDao)
    {
        this.fndTaskDao = fndTaskDao;
    }

    public void setHtReferenceText(IHtReferenceText htReferenceText)
    {
        this.htReferenceText = htReferenceText;
    }

    public void setBuildPart(IBuildPart buildPart)
    {
        this.buildPart = buildPart;
    }

    /**
     * @param qaDiscrepancyDao
     *            the qaDiscrepancyDao to set
     */
    public void setQaDiscrepancyDao(com.ibaset.solumina.sfqa.dao.IDiscrepancyDao qaDiscrepancyDao)
    {
        this.qaDiscrepancyDao = qaDiscrepancyDao;
    }
    
    /**
     * Set IItemDao
     * @param itemDao the IItemDao to set
     */
    public void setItemDao(IItemDao itemDao)
    {
    	this.itemDao = itemDao;
    }
    
    public void setOfdDao(IOfdDao ofdDao)
    {
        this.ofdDao = ofdDao;
    }

	public void setDocumentTypeDao(IDocumentTypeDao documentTypeDao) {
		this.documentTypeDao = documentTypeDao;
	}

	public void setPlanDao(IPlanDao planDao) {
		this.planDao = planDao;
	}

	public void setOrderAlteration(IOrderAlteration orderAlteration) {
		this.orderAlteration = orderAlteration;
	}
    

}
