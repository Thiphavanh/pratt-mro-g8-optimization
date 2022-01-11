/*
 *  This unpublished work, first created in 2019 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2019, 2020.  All rights reserved.  Information contained 
 *  herein is proprietary and confidential and improper use or disclosure 
 *  may result in civil and penal sanctions.
 *
 *  File:    DataCollectionImplPW.java
 * 
 *  Created: 2019-03-01
 * 
 *  Author:  B. Corson
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2019-03-01		B. Corson		Initial Release SMRO_WOE_306 - Add UCF to Data Collections
 *  2020-03-03		B. Corson		SMRO_MD_311 Defect 1464 - Add logic to get Over Inspection Req'd Flag.
 */
package com.pw.solumina.sfwid.application.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sfwid.application.IDataCollection;
import com.ibaset.solumina.sfwid.dao.IOrderDao;
import com.pw.solumina.sfcore.dao.LoginDaoPW;
import com.pw.solumina.sfwid.dao.BuyoffDaoPW;
import com.pw.solumina.sfwid.dao.DataCollectionDaoPW;
import com.ibaset.solumina.sfcore.application.IMessage;


public abstract class DataCollectionImplPW 
                extends ImplementationOf<IDataCollection> 
                implements IDataCollection{
	@Reference private DataCollectionDaoPW dataCollectionDaoPW = null;
	@Reference IMessage message;
	@Reference private LoginDaoPW loginDaoPW;
	@Reference  private ExtensionDaoSupport eds;
	@Reference private IOrderDao orderDaoImpl;    
    @Override
    public void executeDataCollectionMatrixPre(String dataCollectionType, String orderId, Number operationKey,
                                    Number stepKey, String dataCollectionList, String fromSidePanelFlag, String calledFrom,
                                    String operationStatus, String orderControlType, String mustIssuePartsFlag, String groupJobNo) {
                    
                    String userId = ContextUtil.getUsername();
                    // Begin Defect 1464
                    // See if there is any Over Inspection Flag/Audit Flag (Y) for the Data Collection (Operation level)
                    String overInspFlag = dataCollectionDaoPW.selectOperDCOverInspFlag(orderId, 
                                                                                       operationKey, 
                                                                                       stepKey,
                                                                                       dataCollectionList); 
                    // End Defect 1464
                    
                    //check if todays date is > today's date
                    String eyeExamComplete = loginDaoPW.validEyeExam(userId);
                    Map orderMap = orderDaoImpl.selectOrderDetails(orderId);
                    //Begin Defect 347  -- do not check for a supplemental Order if this order is a supplemental
                    String orderType = (String) orderMap.get("ORDER_TYPE"); // Defect 347
                    // If Over Inspection Flag is Y(es) then continue with checking for recent Eye Exam    // Defect 1464
                    if ("Y".equals(overInspFlag)) {                                                        // Defect 1464
                        if (orderType.equalsIgnoreCase("REPAIR") && ("N".equalsIgnoreCase(eyeExamComplete)))
                        {
                            message.raiseError("MFI_20", "Unable to complete Data Collections/Buyoff. Eye exam date is overdue." );
                        }
                    }                                                                                      // Defect 1464
                    Super.executeDataCollectionMatrixPre(dataCollectionType, orderId, operationKey, stepKey, dataCollectionList, fromSidePanelFlag, calledFrom, operationStatus, orderControlType, mustIssuePartsFlag, groupJobNo);
    }


    
    public List selectOrderOperCrossDCUnitMatrixPre(String orderId,
                                                    Number operationKey,
                                                    Number stepKey,
                                                    String dataCollectionId,
                                                    String orderControlType,
                                                    String lotNumbers,
                                                    String orderNumbers,
                                                    String serialNumbers,
                                                    String calledFrom,
                                                    String groupJobNo)
    {
        List resultList = new ArrayList();
        resultList = this.Super.selectOrderOperCrossDCUnitMatrixPre(orderId, 
                                                                    operationKey, 
                                                                    stepKey, 
                                                                    dataCollectionId, 
                                                                    orderControlType, 
                                                                    lotNumbers, 
                                                                    orderNumbers, 
                                                                    serialNumbers, 
                                                                    calledFrom, 
                                                                    groupJobNo);
        
        Integer ndx = 0;
        Iterator resultListIterator = resultList.listIterator();
        
        while (resultListIterator.hasNext())
        {
            Map dataCollectionMap = (Map) resultListIterator.next();
            
            String tempOrderId          = (String) dataCollectionMap.get("ORDER_ID");
            Number tempOperationKey     = (Number) dataCollectionMap.get("OPER_KEY");
            Number tempStepKey          = (Number) dataCollectionMap.get("STEP_KEY");
            String tempDataCollectionId = (String) dataCollectionMap.get("DAT_COL_ID");
            
            Map operDCMap = dataCollectionDaoPW.selectOperDC(tempOrderId, 
                                                             tempOperationKey, 
                                                             tempStepKey, 
                                                             tempDataCollectionId);
            
            String tempUcfOperDCFlag1   = (String) operDCMap.get("UCF_OPER_DC_FLAG1");
            String tempUcfOperDCFlag2   = (String) operDCMap.get("UCF_OPER_DC_FLAG2");
            String tempUcfOperDCVch2551 = (String) operDCMap.get("UCF_OPER_DC_VCH255_1");
            dataCollectionMap.put("UCF_OPER_DC_FLAG1", tempUcfOperDCFlag1);
            dataCollectionMap.put("UCF_OPER_DC_FLAG2", tempUcfOperDCFlag2);
            dataCollectionMap.put("UCF_OPER_DC_VCH255_1", tempUcfOperDCVch2551);
            resultList.set(ndx, dataCollectionMap);    
            ndx = ndx + 1;
        }
        
        return resultList;
    }
}
