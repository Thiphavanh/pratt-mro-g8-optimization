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
 *  File:    WorkOrderDiscrepancyImplPW.java
 * 
 *  Created: 2018-04-26
 * 
 *  Author:  Catrina Nguyen
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2018-04-26		Catrina N.		Defect 699.  Look up system order no using the Pratt order no.  What's
 *  								get passed into the method is the pratt order no and we can't use this
 *  								Therefore, need to look up the actual system order no before calling the oob code.
 */


package com.pw.solumina.sfor.application.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;

import static com.ibaset.common.FrameworkConstants.NO;
import static com.ibaset.common.FrameworkConstants.YES;
import static com.ibaset.common.SoluminaConstants.ALTER_EXISTING_INSTRUCTIONS;
import static com.ibaset.common.SoluminaConstants.APPEND_TO_OPERATION;
import static com.ibaset.common.SoluminaConstants.CANDIDATE_DEFECTS;
import static com.ibaset.common.SoluminaConstants.LOT;
import static com.ibaset.common.SoluminaConstants.MFG_ORDER;
import static com.ibaset.common.SoluminaConstants.NONE;
import static com.ibaset.common.SoluminaConstants.PR;
import static com.ibaset.common.SoluminaConstants.REWORK;
import static com.ibaset.common.SoluminaConstants.SCRAP_STATUS;
import static com.ibaset.common.SoluminaConstants.SEMI_COLON;
import static com.ibaset.common.SoluminaConstants.SERIAL;
import static com.ibaset.common.SoluminaConstants.SIMP_DISC;
import static com.ibaset.common.SoluminaConstants.SQUAWK_Component;
import static com.ibaset.common.SoluminaConstants.UNDERSCORE;
import static com.ibaset.common.SoluminaConstants.UNIT;
import static com.ibaset.common.util.SoluminaUtils.stringEquals;
import static org.apache.commons.lang.StringUtils.contains;
import static org.apache.commons.lang.StringUtils.defaultIfEmpty;
import static org.apache.commons.lang.StringUtils.equalsIgnoreCase;
import static org.apache.commons.lang.StringUtils.isEmpty;
import static org.apache.commons.lang.StringUtils.isNotEmpty;
import static org.apache.commons.lang.StringUtils.upperCase;
import static org.apache.commons.lang.math.NumberUtils.INTEGER_MINUS_ONE;
import static org.apache.commons.lang.math.NumberUtils.INTEGER_ONE;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.solumina.sfor.application.IWorkOrderDiscrepancy;
import com.ibaset.solumina.sfor.application.impl.WorkOrderDiscrepancyImpl.DiscrepancyItemInformationFields;
import com.ibaset.solumina.sfwid.application.IHold;
import com.ibaset.solumina.sfwid.dao.IOrderDao;
import com.ibaset.solumina.sfqa.dao.IDiscrepancyDao;
import com.ibaset.solumina.sfrpt.application.IReport;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.common.solumina.IValidator;
import com.ibaset.solumina.sfcore.application.IUser;
import com.ibaset.solumina.sfsqa.dao.IInspectionOrderDao;
import com.ibaset.solumina.sfwid.dao.IOperationDao;
import com.ibaset.solumina.sfwid.application.IOrderDetails;
import com.ibaset.solumina.sffnd.application.IParse;
import com.ibaset.solumina.sfsqa.dao.IDiscrepancyItemDao;
import com.ibaset.solumina.sfor.application.ISfwidUndo;
import com.pw.solumina.sfwid.dao.IHoldDaoPW;
import com.pw.solumina.sfwid.dao.WorkOrderDaoPW;	//Defect 699

public abstract class WorkOrderDiscrepancyImplPW extends ImplementationOf<IWorkOrderDiscrepancy> implements IWorkOrderDiscrepancy {

	@Reference
	private IHold hold = null;
	
	@Reference
    private IOrderDao orderDao = null;

	@Reference
    private IDiscrepancyDao discrepancyDao = null;

	@Reference
    private IMessage message = null;
	
	@Reference
    private IValidator validator = null;

	@Reference
    private IUser privilege = null;
	
	@Reference
    private IInspectionOrderDao inspectionOrderDao = null;

	@Reference
    private IOperationDao operationDao = null;

	@Reference
    private IOrderDetails orderDetails = null;

	@Reference
    private IParse parse = null;

	@Reference
    private IDiscrepancyItemDao discrepancyItemDao = null;

    @Reference
    private ISfwidUndo sfwidUndo = null;
    
    @Reference
    private IHoldDaoPW holdDaoPW = null;
    
    @Reference
    private WorkOrderDaoPW workOrderDaoPW = null;	//Defect 699
	
	@Override
    public void createDiscrepancy(String partNumber,
            String partChange,
            String orderNumber,
            String operationNumber,
            String rejectLocation,
            String rejectDepartment,
            String rejectCenter,
            Number affectedQuantity,
            String notes,
            String process,
            String symptom,
            String defect,
            String cause,
            String dispositionType,
            String dispositionInstructionType,
            String discrepancyLineTitle,
            String routeType,
            String serialNumber,
            String ucfDiscrepancyItemVch1,
            String ucfDiscrepancyItemVch2,
            String ucfDiscrepancyItemVch3,
            String ucfDiscrepancyItemVch4,
            String ucfDiscrepancyItemVch5,
            String ucfDiscrepancyItemVch6,
            String ucfDiscrepancyItemVch7,
            String ucfDiscrepancyItemVch8,
            String ucfDiscrepancyItemVch9,
            String ucfDiscrepancyItemVch10,
            String ucfDiscrepancyItemVch11,
            String ucfDiscrepancyItemVch12,
            String ucfDiscrepancyItemVch13,
            String ucfDiscrepancyItemVch14,
            String ucfDiscrepancyItemVch15,
            String ucfDiscrepancyItemFlag1,
            String ucfDiscrepancyItemFlag2,
            String ucfDiscrepancyItemFlag3,
            String ucfDiscrepancyItemFlag4,
            String ucfDiscrepancyItemFlag5,
            Number ucfDiscrepancyItemNumber1,
            Number ucfDiscrepancyItemNumber2,
            Number ucfDiscrepancyItemNumber3,
            Number ucfDiscrepancyItemNumber4,
            Number ucfDiscrepancyItemNumber5,
            Date ucfDiscrepancyItemDate1,
            Date ucfDiscrepancyItemDate2,
            Date ucfDiscrepancyItemDate3,
            Date ucfDiscrepancyItemDate4,
            Date ucfDiscrepancyItemDate5,
            String ucfDiscrepancyVch1,
            String ucfDiscrepancyVch2,
            String ucfDiscrepancyVch3,
            String ucfDiscrepancyVch4,
            String ucfDiscrepancyVch5,
            String ucfDiscrepancyVch6,
            String ucfDiscrepancyVch7,
            String ucfDiscrepancyVch8,
            String ucfDiscrepancyVch9,
            String ucfDiscrepancyVch10,
            String ucfDiscrepancyVch11,
            String ucfDiscrepancyVch12,
            String ucfDiscrepancyVch13,
            String ucfDiscrepancyVch14,
            String ucfDiscrepancyVch15,
            Number ucfDiscrepancyNumber1,
            Number ucfDiscrepancyNumber2,
            Number ucfDiscrepancyNumber3,
            Number ucfDiscrepancyNumber4,
            Number ucfDiscrepancyNumber5,
            String ucfDiscrepancyFlag1,
            String ucfDiscrepancyFlag2,
            String ucfDiscrepancyFlag3,
            String ucfDiscrepancyFlag4,
            String ucfDiscrepancyFlag5,
            Date ucfDiscrepancyDate1,
            Date ucfDiscrepancyDate2,
            Date ucfDiscrepancyDate3,
            Date ucfDiscrepancyDate4,
            Date ucfDiscrepancyDate5,
            String inspectionOrderFlag,
            String actualCondition,
            Number rejectedQuantity,
            String serialFlag,
            String inspectionType,
            String itemId,
            String drawingNumber,
            String drawingChange,
            String subComponentPartNumber,
            String supplierCode,
            String supplierName,
            String lotFlag,
            String flightSafetyFlag,
            Number idpQuantity,
            Number sampleTaken,
            String characteristicCodeRevision,
            String characteristicDescription,
            String lsl,
            String usl,
            String characteristicName,
            String inspectionOrderId,
            String characteristicId,
            String processState,
            String criteria,
            Number characteristicCount,
            String slotId,
            String subComponentItemId,
            String ucfDiscrepancyVch2551,
            String ucfDiscrepancyVch2552,
            String ucfDiscrepancyVch2553,
            String ucfDiscrepancyVch40001,
            String ucfDiscrepancyVch40002,
            String ucfDiscrepancyItemVch2551,
            String ucfDiscrepancyItemVch2552,
            String ucfDiscrepancyItemVch2553,
            String ucfDiscrepancyItemVch40001,
            String ucfDiscrepancyItemVch40002,
            Map createDiscMap,
            String discrepancyType,
            String calledFrom,
            String respResourceType,
            String groupJobNo,
            String inspItemId,
            String inspItemNo,
            String sampleNumber,
            String inspStepId,
            Number inspItemCount,
            String advanceToWorkFlowQueue)
	{
		
		orderNumber = workOrderDaoPW.selectSystemOrderNo(orderNumber);	//Defect 699
		
		this.Super.createDiscrepancy(partNumber,
	            partChange,
	            orderNumber,
	            operationNumber,
	            rejectLocation,
	            rejectDepartment,
	            rejectCenter,
	            affectedQuantity,
	            notes,
	            process,
	            symptom,
	            defect,
	            cause,
	            dispositionType,
	            dispositionInstructionType,
	            discrepancyLineTitle,
	            routeType,
	            serialNumber,
	            ucfDiscrepancyItemVch1,
	            ucfDiscrepancyItemVch2,
	            ucfDiscrepancyItemVch3,
	            ucfDiscrepancyItemVch4,
	            ucfDiscrepancyItemVch5,
	            ucfDiscrepancyItemVch6,
	            ucfDiscrepancyItemVch7,
	            ucfDiscrepancyItemVch8,
	            ucfDiscrepancyItemVch9,
	            ucfDiscrepancyItemVch10,
	            ucfDiscrepancyItemVch11,
	            ucfDiscrepancyItemVch12,
	            ucfDiscrepancyItemVch13,
	            ucfDiscrepancyItemVch14,
	            ucfDiscrepancyItemVch15,
	            ucfDiscrepancyItemFlag1,
	            ucfDiscrepancyItemFlag2,
	            ucfDiscrepancyItemFlag3,
	            ucfDiscrepancyItemFlag4,
	            ucfDiscrepancyItemFlag5,
	            ucfDiscrepancyItemNumber1,
	            ucfDiscrepancyItemNumber2,
	            ucfDiscrepancyItemNumber3,
	            ucfDiscrepancyItemNumber4,
	            ucfDiscrepancyItemNumber5,
	            ucfDiscrepancyItemDate1,
	            ucfDiscrepancyItemDate2,
	            ucfDiscrepancyItemDate3,
	            ucfDiscrepancyItemDate4,
	            ucfDiscrepancyItemDate5,
	            ucfDiscrepancyVch1,
	            ucfDiscrepancyVch2,
	            ucfDiscrepancyVch3,
	            ucfDiscrepancyVch4,
	            ucfDiscrepancyVch5,
	            ucfDiscrepancyVch6,
	            ucfDiscrepancyVch7,
	            ucfDiscrepancyVch8,
	            ucfDiscrepancyVch9,
	            ucfDiscrepancyVch10,
	            ucfDiscrepancyVch11,
	            ucfDiscrepancyVch12,
	            ucfDiscrepancyVch13,
	            ucfDiscrepancyVch14,
	            ucfDiscrepancyVch15,
	            ucfDiscrepancyNumber1,
	            ucfDiscrepancyNumber2,
	            ucfDiscrepancyNumber3,
	            ucfDiscrepancyNumber4,
	            ucfDiscrepancyNumber5,
	            ucfDiscrepancyFlag1,
	            ucfDiscrepancyFlag2,
	            ucfDiscrepancyFlag3,
	            ucfDiscrepancyFlag4,
	            ucfDiscrepancyFlag5,
	            ucfDiscrepancyDate1,
	            ucfDiscrepancyDate2,
	            ucfDiscrepancyDate3,
	            ucfDiscrepancyDate4,
	            ucfDiscrepancyDate5,
	            inspectionOrderFlag,
	            actualCondition,
	            rejectedQuantity,
	            serialFlag,
	            inspectionType,
	            itemId,
	            drawingNumber,
	            drawingChange,
	            subComponentPartNumber,
	            supplierCode,
	            supplierName,
	            lotFlag,
	            flightSafetyFlag,
	            idpQuantity,
	            sampleTaken,
	            characteristicCodeRevision,
	            characteristicDescription,
	            lsl,
	            usl,
	            characteristicName,
	            inspectionOrderId,
	            characteristicId,
	            processState,
	            criteria,
	            characteristicCount,
	            slotId,
	            subComponentItemId,
	            ucfDiscrepancyVch2551,
	            ucfDiscrepancyVch2552,
	            ucfDiscrepancyVch2553,
	            ucfDiscrepancyVch40001,
	            ucfDiscrepancyVch40002,
	            ucfDiscrepancyItemVch2551,
	            ucfDiscrepancyItemVch2552,
	            ucfDiscrepancyItemVch2553,
	            ucfDiscrepancyItemVch40001,
	            ucfDiscrepancyItemVch40002,
	            createDiscMap,
	            discrepancyType,
	            calledFrom,
	            respResourceType,
	            groupJobNo,
	            inspItemId,
	            inspItemNo,
	            sampleNumber,
	            inspStepId,
	            inspItemCount,
	            advanceToWorkFlowQueue);


        @SuppressWarnings("deprecation")
		String discrepancyId = ContextUtil.getUser().getContext().get("DISC_ID").toString();
        @SuppressWarnings("deprecation")
		Number discrepancyLineNumber = (Number) ContextUtil.getUser().getContext().get("DISC_LINE_NO");

        
        
    		
		
		
		if ("WORKSTOP_REVIEW".equalsIgnoreCase(routeType)) {
			
			String orderId =  orderDao.selectOrderIdByOrderNumber(orderNumber);
	        //Number discrepancyLineNumber = discrepancyDao.selectDiscrepancyLineNumber(discrepancyId[0]);
			//Check if hold exists
	        
	        boolean holdExists = holdDaoPW.selectHoldExistsForOrder(orderId, discrepancyId, discrepancyLineNumber.toString(), "ORDER STOP", "DISCREPANCY LIEN");
	        if (!holdExists) {
	        	//insert hold
	        	hold.insertHold(orderId,
	        			null,
	        			"DISCREPANCY LIEN", 
	        			"ORDER STOP", 
	        			null, 
	        			null, 
	        			orderNumber, 
	        			null, 
	        			null, 
	        			null, 		//holdReference1
	        			discrepancyId, 	//holdReference2
	        			discrepancyLineNumber.toString(), 	//holdReference3
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
	        			NO,	//Include UCF Flags
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
		
	}


	
}
