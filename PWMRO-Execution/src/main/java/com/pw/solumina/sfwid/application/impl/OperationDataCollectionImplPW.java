/*
 *  This unpublished work, first created in 2019 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2019.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    OperationDataCollectionImplPW.java
 * 
 *  Created: 2019-05-17
 * 
 *  Author:  B. Corson
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2019-05-17		B. Corson		Initial Release SMRO_WOE_306 - Add UCF to Data Collections
 *  2020-03-25      D. Miron        Defedt 1589 - Added SECURITY_GROUP to return resolve S/G error in System Config Manager
 *  2020-10-22      S. Edgerly      Defect 1799 - Added Over Inspect field to Data Collection Block
 */

package com.pw.solumina.sfwid.application.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.solumina.sfwid.application.IOperationDataCollection;
import com.pw.solumina.sfwid.dao.DataCollectionDaoPW;

public abstract class OperationDataCollectionImplPW 
                extends ImplementationOf<IOperationDataCollection> 
                implements IOperationDataCollection{
    @Reference private DataCollectionDaoPW dataCollectionDaoPW = null;
    
    /*
     * (non-Javadoc)
     * @see com.ibaset.solumina.sfwid.application.IOperationDataCollection#getOperationDataCollection(java.lang.String, java.lang.Number,
     *  java.lang.Number, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public List getOperationDataCollection(String orderId,
                                           Number operationKey,
                                           Number stepKey,
                                           String referenceId,
                                           String orderControlType,
                                           String consolidatedList,
                                           String navigationLevel,
                                           String groupJobNo)
    {
        List resultList = new ArrayList();
        resultList = this.Super.getOperationDataCollection(orderId, 
                                                           operationKey, 
                                                           stepKey, 
                                                           referenceId, 
                                                           orderControlType, 
                                                           consolidatedList,
                                                           navigationLevel,
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
            String auditFlag 			= (String) operDCMap.get("AUDIT_FLAG");//defect 1799

            dataCollectionMap.put("UCF_OPER_DC_FLAG1", tempUcfOperDCFlag1);
            dataCollectionMap.put("UCF_OPER_DC_FLAG2", tempUcfOperDCFlag2);
            dataCollectionMap.put("UCF_OPER_DC_VCH255_1", tempUcfOperDCVch2551);
            dataCollectionMap.put("SECURITY_GROUP", null);  // Defect 1589
            dataCollectionMap.put("AUDIT_FLAG", auditFlag); // Defect 1799
            resultList.set(ndx, dataCollectionMap);    
            ndx = ndx + 1;
        }
        
        return resultList;
    }
}
