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
*  File:    T308EventProcesserImpl.java
* 
*  Created: 2019-08-01
* 
*  Author:  Raeann Thorpe
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
*  2019-08-01 	Raeann Thorpe   Initial Release for PW_SMRO_TR_308
*  2019-09-20   Bahram J.       SMRO_TR_308, changed xml to use synWorkOrder instead of syncRecord
*  2019-10-30   Fred Ettefagh   SMRO_TR_308, changed code to replace "-" with "a"  for  bodId, this is due to MW issues with "-" in JMS CorrelationID ID/BodID
*  2019-10-31   Fred Ettefagh   SMRO_TR_308, removed code to replace "-" with "a"  for  bodId
*/
package com.pw.solumina.T308.integration.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List; 

import org.apache.xmlbeans.XmlObject;
import org.openapplications.oagis.x9.SyncWorkOrderDocument;
import org.openapplications.oagis.x9.SyncWorkOrderType;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.event.SoluminaApplicationEvent;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.util.OAGIUtils;
import com.ibaset.solumina.integration.common.ActionCode;
import com.ibaset.solumina.integration.common.BaseEventProcessor;
import com.ibaset.solumina.integration.common.MappingAction;
import com.ibaset.solumina.integration.logging.MessageLoggerParams;
import com.ibaset.solumina.sfcore.application.ILicense;
import com.pw.solumina.T308.integration.IT308EventProcessor;

public class T308EventProcessorImpl extends BaseEventProcessor implements IT308EventProcessor{
	
	@Reference T308MapperImpl t308MapperImpl = null;
	@Reference  OAGIUtils oagiUtils = null; 
	@Reference ILicense license;

	@Override
	public XmlObject createBod(SoluminaApplicationEvent event) 
	{
		if (event instanceof T308Event){

			SyncWorkOrderDocument syncWorkOrderDocument = SyncWorkOrderDocument.Factory.newInstance();
            SyncWorkOrderType syncWorkOrderType = syncWorkOrderDocument.addNewSyncWorkOrder();
            syncWorkOrderType.setReleaseID(super.getReleaseId());

          
	        oagiUtils.initalizeApplicationArea( syncWorkOrderType.addNewApplicationArea(), 
							                    oagiUtils.LOGICAL_ID+SoluminaConstants.UNDERSCORE+license.getLicensePrefix(), 
							                    "TR_308",//componentId, 
							                    "Work Order Release",//taskId, 
							                    java.util.UUID.randomUUID().toString(),//referenceId, 
							                    "Always",//confirmationType, 
							                    ContextUtil.getUsername(),//authorizationId, 
							                    java.util.UUID.randomUUID().toString().toUpperCase(),//bodId, 
							                    new Date());//creationDate);

            
            
            syncWorkOrderType.addNewDataArea();
            syncWorkOrderType.getDataArea().addNewSync().addNewActionCriteria();
            return syncWorkOrderDocument;
          
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	@Override
	public MappingAction[] getMappingActions(SoluminaApplicationEvent event) 
	{
		
		    List actionList = null;
	        actionList = new ArrayList();
	        ActionCode actionCode = ActionCode.CHANGE;
	        MappingAction mappingAction= new MappingAction(actionCode,event);
	        actionList.add(new MappingAction(actionCode,event));
	        return (MappingAction[]) actionList.toArray(new MappingAction[actionList.size()]);
	}

	@Override
	public void processEvent(MappingAction[] mappingActions, 
			                 XmlObject outboundBod, 
			                 SoluminaApplicationEvent event,	
			                 MessageLoggerParams messageLoggerParams) 
	{
		
        if (mappingActions != null) 
        {
            messageLoggerParams.setRef1(((T308Event) mappingActions[0].getSourceObject()).getWorkOrderNumber());
            messageLoggerParams.setRef2(((T308Event) mappingActions[0].getSourceObject()).getOrderQty().toString());
            t308MapperImpl.map(mappingActions, (SyncWorkOrderDocument) outboundBod);
            
            String bodId = ((SyncWorkOrderDocument) outboundBod).getSyncWorkOrder().getApplicationArea().getBODID().getStringValue();
            messageLoggerParams.setRef3(bodId);
        }
		
	}

	public String generateMessage(SoluminaApplicationEvent event,MessageLoggerParams messageLoggerParams)
    {
    	XmlObject outboundBod = createBod(event);
    	MappingAction[] mappingActions = getMappingActions(event);
    	processEvent(mappingActions, outboundBod, event,messageLoggerParams);
    	return outboundBod.toString();
    	
    }
}
