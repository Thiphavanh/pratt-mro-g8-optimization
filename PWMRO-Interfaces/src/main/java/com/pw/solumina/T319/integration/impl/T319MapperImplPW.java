/*
* This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    T319MapperImplPW.java
* 
*  Created: 2019-06-05
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2019-06-04 	Fred Ettefagh   Initial Release for PW_SMRO_TR_319
* 2019-06-10    Fred Ettefagh   added work loc to outbound msg
* 2019-09-12    Fred Ettefagh   SMRO_TR_319, added code for SCR-001 
* 2019-10-10    Fred Ettefagh   SMRO_TR_319, removed task data from outbound msg.  not needed any more
* 2020-08-18    D.MIron         SMRO_TR_319 Defect 1757 - All planItemNo instead of planNo to outbound message.
*/
package com.pw.solumina.T319.integration.impl;

import org.openapplications.oagis.x9.ActionExpressionType;
import org.openapplications.oagis.x9.KeysType;
import org.openapplications.oagis.x9.NameValuePairType;
import org.openapplications.oagis.x9.RecordType;
import org.openapplications.oagis.x9.SyncRecordDocument;

import com.ibaset.solumina.integration.common.ActionCode;
import com.ibaset.solumina.integration.common.MappingAction;

public class T319MapperImplPW {

	
    public void map(MappingAction[] outboundActions, SyncRecordDocument syncRecordDocument)
    {
    	 for (int i = 0; i < outboundActions.length; i++)
         {
             MappingAction mappingAction = outboundActions[i];

             ActionCode actionCode = mappingAction.getActionCode();
             T319EventPW t319EventPW = (T319EventPW)mappingAction.getSourceObject();             

             setActionCode(actionCode, syncRecordDocument);

             RecordType recordType = syncRecordDocument.getSyncRecord().getDataArea().getRecordArray(0);

             mapOutboundRecord(recordType, t319EventPW);
         }
    	
    }
    
    protected void setActionCode(ActionCode actionCode,SyncRecordDocument destinationDocument)
    {
        String actionEx = "/SyncRecord/DataArea/Record[1]";

        ActionExpressionType actionExpression = destinationDocument.getSyncRecord()
                                                                   .getDataArea()
                                                                   .getSync()
                                                                   .getActionCriteriaArray(0)
                                                                   .addNewActionExpression();
        actionExpression.setActionCode(actionCode.toString());
        actionExpression.setStringValue(actionEx);
    }
    
    public RecordType mapOutboundRecord(RecordType recordType, T319EventPW t319EventPW)
    {
    	KeysType keysType = recordType.addNewKeys();
        NameValuePairType keyValues = keysType.addNewRecordKey();
        keyValues.setName("WORK_ORDER_NUMBER");
        keyValues.setStringValue(t319EventPW.getWorkOrderNumber());

        keyValues = keysType.addNewRecordKey();
        keyValues.setName("ORDER_TYPE");
        keyValues.setStringValue(t319EventPW.getSapOrderType());

        keyValues = keysType.addNewRecordKey();
        keyValues.setName("SPLIT_SUB_INDICATOR");
        keyValues.setStringValue(t319EventPW.getSapOrderTypeIndicator());
        
        keyValues = keysType.addNewRecordKey();
        keyValues.setName("MASTER_PLAN_ID");
        keyValues.setStringValue(t319EventPW.getPlanItemNo());   // Defect 1757 
        
        keyValues = keysType.addNewRecordKey();
        keyValues.setName("QUANTITY");
        keyValues.setStringValue(t319EventPW.getOrderQty().toString());
        
        keyValues = keysType.addNewRecordKey();
        keyValues.setName("PLANT_ID");
        keyValues.setStringValue(t319EventPW.getWorkLocation());

        
        String[] serialArray = t319EventPW.getSerialNoArray();
        for (int i = 0; i < serialArray.length; i++) {
            keyValues = keysType.addNewRecordKey();
            keyValues.setName("SERIAL_NUMBER");
            keyValues.setStringValue((String) serialArray[i]);
        }
        
        // Fred Ettefagh
        // 9/26/2019  task data is not needed for T319 any more
        /*
        String[] TaskGroupArray = t319EventPW.getTaskGroupArray();
        for (int i = 0; i < TaskGroupArray.length; i++) {
            keyValues = keysType.addNewRecordKey();
            keyValues.setName("TASK_GROUP_NUMBER");
            keyValues.setStringValue((String) TaskGroupArray[i]);
        }
        */

        
        return recordType;
    }
    
}
