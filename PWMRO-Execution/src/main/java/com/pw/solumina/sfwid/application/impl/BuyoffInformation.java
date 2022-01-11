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
*  File:    BuyoffInformation.java
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

public class BuyoffInformation
{
    
    private String orderId ; 
    private Number operKey ;
    private Number stepKey ;
    private String buyoffId ;
    private String buyoffStatus ;
    private String buyoffType ;
    private String buyoffCertification ;
    private String buyoffComment ;
    private String rejectType ;
    private Number percentComplete ;
    private String partialComplete ;
    private SerialOperationBuyoffUcfObjects serialOperBuyoffUcfs;
    
    
    public String getOrderId()
    {
        return orderId;
    }
    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }
    public Number getOperKey()
    {
        return operKey;
    }
    public void setOperKey(Number operKey)
    {
        this.operKey = operKey;
    }
    public Number getStepKey()
    {
        return stepKey;
    }
    public void setStepKey(Number stepKey)
    {
        this.stepKey = stepKey;
    }
    public String getBuyoffId()
    {
        return buyoffId;
    }
    public void setBuyoffId(String buyoffId)
    {
        this.buyoffId = buyoffId;
    }
    public String getBuyoffStatus()
    {
        return buyoffStatus;
    }
    public void setBuyoffStatus(String buyoffstatus)
    {
        this.buyoffStatus = buyoffstatus;
    }
    public String getBuyoffType()
    {
        return buyoffType;
    }
    public void setBuyoffType(String buyoffType)
    {
        this.buyoffType = buyoffType;
    }
    public String getBuyoffCertification()
    {
        return buyoffCertification;
    }
    public void setBuyoffCertification(String buyoffCertification)
    {
        this.buyoffCertification = buyoffCertification;
    }
    public String getBuyoffComment()
    {
        return buyoffComment;
    }
    public void setBuyoffComment(String buyoffComment)
    {
        this.buyoffComment = buyoffComment;
    }
    public String getRejectType()
    {
        return rejectType;
    }
    public void setRejectType(String rejectType)
    {
        this.rejectType = rejectType;
    }
    public Number getPercentComplete()
    {
        return percentComplete;
    }
    public void setPercentComplete(Number percentComplete)
    {
        this.percentComplete = percentComplete;
    }
    public String getPartialComplete()
    {
        return partialComplete;
    }
    public void setPartialComplete(String partialComplete)
    {
        this.partialComplete = partialComplete;
    }
    public SerialOperationBuyoffUcfObjects getSerialOperBuyoffUcfs()
    {
        return serialOperBuyoffUcfs;
    }
    public void setSerialOperBuyoffUcfs(SerialOperationBuyoffUcfObjects serialOperBuyoffUcfs)
    {
        this.serialOperBuyoffUcfs = serialOperBuyoffUcfs;
    }
    

}
