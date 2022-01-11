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

import static com.ibaset.common.SoluminaConstants.N;
import static com.ibaset.common.SoluminaConstants.Y;

import java.util.HashMap;

import com.ibaset.solumina.sfwid.application.impl.AsWorkedItemInformation;
import com.ibaset.solumina.sfwid.application.impl.SerialInformation;
import com.ibaset.solumina.sfwid.domain.OrderDesc;

public class OrderInformation
{
    SerialInformation serialInfo;
    AsWorkedItemInformation asWorkedItemInfo;
    BuyoffInformation buyoffInformation;
    OperInformation operInformation;
    String orderId;
    String orderNumber;
    String itemId;
    String partNumber;
    String partChange;
    String orderType;
    String unscrapFlag;
    String aliasPartNo;
    String aliasPartChg;
    String documentType;
    String serialFlag;
    String lotFlag;
    String isMRO ;
    boolean mroFlagExists = false;
    String orderControl;
    Number orderQuantity;
    String groupJobNo;
    
    
    String instructionType;
    HashMap<Object,Object> userFields = new HashMap();

    Number orderScrapQuantity;
    
    String blockId = null;
    String textType = null;
    Number controlSequence = null;
    String referenceId = null;
    
    public OrderInformation()
    {
        
    }
    public OrderInformation(String orderId)
    {
        this.orderId = orderId;
    }
    
    public OrderInformation(OrderDesc orderDesc)
    {
        this.orderId = orderDesc.getOrderId();
        this.itemId = orderDesc.getItemId();
        this.partNumber = orderDesc.getPartNo();
        this.partChange = orderDesc.getPartChg();
        this.orderType = orderDesc.getOrderType();
        this.unscrapFlag = orderDesc.getUnscrapFlag()!=null?(orderDesc.getUnscrapFlag())?Y:N:N; //GE-5884
        this.aliasPartNo = orderDesc.getAliasPartNo();
        this.aliasPartChg = orderDesc.getAliasPartChg();
        this.documentType = orderDesc.getDocType();
        this.serialFlag = (orderDesc.getSerialFlag())?Y:N;
        this.lotFlag = (orderDesc).getLotFlag()?Y:N;
        
    }

    public SerialInformation getSerialInfo()
    {
        return serialInfo;
    }

    public void setSerialInfo(SerialInformation serialInfo)
    {
        this.serialInfo = serialInfo;
    }

    public AsWorkedItemInformation getAsWorkedItemInfo()
    {
        return asWorkedItemInfo;
    }

    public void setAsWorkedItemInfo(AsWorkedItemInformation asWorkedItemInfo)
    {
        this.asWorkedItemInfo = asWorkedItemInfo;
    }

    public BuyoffInformation getBuyoffInformation()
    {
        return buyoffInformation;
    }

    public void setBuyoffInformation(BuyoffInformation buyoffInformation)
    {
        this.buyoffInformation = buyoffInformation;
    }

    public OperInformation getOperInformation()
    {
        return operInformation;
    }
    public void setOperInformation(OperInformation operInformation)
    {
        this.operInformation = operInformation;
    }
    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }
    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }
    public String getItemId()
    {
        return itemId;
    }

    public void setItemId(String itemId)
    {
        this.itemId = itemId;
    }

    public String getPartNumber()
    {
        return partNumber;
    }

    public void setPartNumber(String partNumber)
    {
        this.partNumber = partNumber;
    }

    public String getPartChange()
    {
        return partChange;
    }

    public void setPartChange(String partChange)
    {
        this.partChange = partChange;
    }

    public String getOrderType()
    {
        return orderType;
    }

    public void setOrderType(String orderType)
    {
        this.orderType = orderType;
    }

    public String getUnscrapFlag()
    {
        return unscrapFlag;
    }

    public void setUnscrapFlag(String unscrapFlag)
    {
        this.unscrapFlag = unscrapFlag;
    }

    public String getAliasPartNo()
    {
        return aliasPartNo;
    }

    public void setAliasPartNo(String aliasPartNo)
    {
        this.aliasPartNo = aliasPartNo;
    }

    public String getAliasPartChg()
    {
        return aliasPartChg;
    }

    public void setAliasPartChg(String aliasPartChg)
    {
        this.aliasPartChg = aliasPartChg;
    }

    public String getDocumentType()
    {
        return documentType;
    }

    public void setDocumentType(String documentType)
    {
        this.documentType = documentType;
    }

    public String getSerialFlag()
    {
        return serialFlag;
    }

    public void setSerialFlag(String serialFlag)
    {
        this.serialFlag = serialFlag;
    }

    public String getLotFlag()
    {
        return lotFlag;
    }

    public void setLotFlag(String lotFlag)
    {
        this.lotFlag = lotFlag;
    }

    public String getIsMRO()
    {
        return isMRO;
    }

    public void setIsMRO(String isMRO)
    {
        this.isMRO = isMRO;
    }

    public boolean isMroFlagExists()
    {
        return mroFlagExists;
    }
    public void setMroFlagExists(boolean mroFlagExists)
    {
        this.mroFlagExists = mroFlagExists;
    }
    public String getOrderControl()
    {
        return orderControl;
    }

    public void setOrderControl(String orderControl)
    {
        this.orderControl = orderControl;
    }

    public Number getOrderQuantity()
    {
        return orderQuantity;
    }
    public void setOrderQuantity(Number orderQuantity)
    {
        this.orderQuantity = orderQuantity;
    }
    public String getInstructionType()
    {
        return instructionType;
    }

    public void setInstructionType(String instructionType)
    {
        this.instructionType = instructionType;
    }

    public HashMap<Object, Object> getUserFields()
    {
        return userFields;
    }

    public void setUserFields(HashMap<Object, Object> userFields)
    {
        this.userFields = userFields;
    }
    public Number getOrderScrapQuantity()
    {
        return orderScrapQuantity;
    }
    public void setOrderScrapQuantity(Number orderScrapQuantity)
    {
        this.orderScrapQuantity = orderScrapQuantity;
    }
    public String getBlockId()
    {
        return blockId;
    }
    public void setBlockId(String blockId)
    {
        this.blockId = blockId;
    }
    public String getTextType()
    {
        return textType;
    }
    public void setTextType(String textType)
    {
        this.textType = textType;
    }
    public Number getControlSequence()
    {
        return controlSequence;
    }
    public void setControlSequence(Number controlSequence)
    {
        this.controlSequence = controlSequence;
    }
    public String getReferenceId()
    {
        return referenceId;
    }
    public void setReferenceId(String referenceId)
    {
        this.referenceId = referenceId;
    }
    public String getGroupJobNo()
    {
        return groupJobNo;
    }
    public void setGroupJobNo(String groupJobNo)
    {
        this.groupJobNo = groupJobNo;
    }
    
}
