/*
* This unpublished work, first created in 2019 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2019.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    OperInformation.java
* 
*  Created: 2019-04-18
* 
*  Author:  c079222
* 
*  Revision History
* 
*  Date      	Who             Description
* ----------	---------------	-------------------------------------------------------------------
* 2019-04-18 	D.Miron		    Defect 1208 - Copied from OOTB R2SP4
*/
package com.pw.solumina.sfwid.application.impl;

public class OperInformation
{
    private String orderId ;
    private Number operationKey ;
    private Number stepKey ;
    private Number operAvailableQty;
    private boolean isOperationPartOfContinuousFlow;
    private boolean isInstructionReadAcknowledgementRequired;
    private boolean isDeviationReadAcknowledgementRequired;
    private boolean isReadAckRequiredForGroupJob;
    private Number qtyComplete ;
    private String operationStatus;
    boolean isOperationComplete;
    
    // Defect 1208 - DXC Added public to constructor
    public OperInformation(String orderId, Number operKey, Number stepKey)
    {
        this.orderId = orderId ;
        this.operationKey = operKey ;
        this.stepKey = stepKey ;
    }

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public Number getOperationKey()
    {
        return operationKey;
    }

    public void setOperationKey(Number operationKey)
    {
        this.operationKey = operationKey;
    }

    public Number getStepKey()
    {
        return stepKey;
    }

    public void setStepKey(Number stepKey)
    {
        this.stepKey = stepKey;
    }

    public Number getOperAvailableQty()
    {
        return operAvailableQty;
    }

    public void setOperAvailableQty(Number operAvailableQty)
    {
        this.operAvailableQty = operAvailableQty;
    }

    public boolean isOperationPartOfContinuousFlow()
    {
        return isOperationPartOfContinuousFlow;
    }

    public void setOperationPartOfContinuousFlow(boolean isOperationPartOfContinuousFlow)
    {
        this.isOperationPartOfContinuousFlow = isOperationPartOfContinuousFlow;
    }
    public boolean isInstructionReadAcknowledgementRequired()
    {
        return isInstructionReadAcknowledgementRequired;
    }

    public void setInstructionReadAcknowledgementRequired(boolean isInstructionReadAcknowledgementRequired)
    {
        this.isInstructionReadAcknowledgementRequired = isInstructionReadAcknowledgementRequired;
    }

    public boolean isDeviationReadAcknowledgementRequired()
    {
        return isDeviationReadAcknowledgementRequired;
    }

    public void setDeviationReadAcknowledgementRequired(boolean isDeviationReadAcknowledgementRequired)
    {
        this.isDeviationReadAcknowledgementRequired = isDeviationReadAcknowledgementRequired;
    }

    public boolean isReadAckRequiredForGroupJob()
    {
        return isReadAckRequiredForGroupJob;
    }

    public void setReadAckRequiredForGroupJob(boolean isReadAckRequiredForGroupJob)
    {
        this.isReadAckRequiredForGroupJob = isReadAckRequiredForGroupJob;
    }

    public Number getQtyComplete()
    {
        return qtyComplete;
    }
    public void setQtyComplete(Number qtyComplete)
    {
        this.qtyComplete = qtyComplete;
    }

    public String getOperationStatus()
    {
        return operationStatus;
    }

    public void setOperationStatus(String operationStatus)
    {
        this.operationStatus = operationStatus;
    }

    public boolean isOperationComplete()
    {
        return isOperationComplete;
    }

    public void setOperationComplete(boolean isOperationComplete)
    {
        this.isOperationComplete = isOperationComplete;
    }
    
}
