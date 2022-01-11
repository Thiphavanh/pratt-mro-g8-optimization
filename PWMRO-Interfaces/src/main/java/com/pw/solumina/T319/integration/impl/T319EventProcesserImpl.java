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
*  File:    T319EventProcesserImpl.java
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
* 2019-08-21    Fred Ettefagh   changed super.initializeApplicationArea to oagiUtils.initalizeApplicationArea call to 
* 2019-10-30    Fred Ettefagh   SMRO_TR_319, changed code to replace "-" with "a"  for  bodId, this is due to MW issues with "-" in JMS CorrelationID ID/BodID
* 2019-10-30    Fred Ettefagh   SMRO_TR_319, removed code to replace "-" with "a"  for  bodId
*/
package com.pw.solumina.T319.integration.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.xmlbeans.XmlObject;
import org.openapplications.oagis.x9.SyncRecordDocument;
import org.openapplications.oagis.x9.SyncRecordType;

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

public class T319EventProcesserImpl extends BaseEventProcessor implements T319EventProcesser{

	
	@Reference T319MapperImplPW t319MapperImplPW = null;
	@Reference  OAGIUtils oagiUtils = null; 
	@Reference ILicense license;
	
	@Override
	public XmlObject createBod(SoluminaApplicationEvent event) {

		if (event instanceof T319EventPW){
			
			
	        SyncRecordDocument syncRecordDocument = SyncRecordDocument.Factory.newInstance();

	        SyncRecordType syncRecordType = (SyncRecordType) syncRecordDocument.addNewSyncRecord();
	        syncRecordType.setReleaseID(super.getReleaseId());

	        
	        oagiUtils.initalizeApplicationArea(syncRecordType.addNewApplicationArea(), 
	        		                           oagiUtils.LOGICAL_ID+SoluminaConstants.UNDERSCORE+license.getLicensePrefix(), 
	        		                           "TR_319",//componentId, 
	        		                           "REPAIR_WO_SPLIT",//taskId, 
	        		                           java.util.UUID.randomUUID().toString(),//referenceId, 
	        		                           "Always",//confirmationType, 
	        		                           ContextUtil.getUsername(),//authorizationId, 
	        		                           java.util.UUID.randomUUID().toString().toUpperCase(),//bodId, 
	        		                           new Date());//creationDate);
	        

	        syncRecordType.addNewDataArea();
	        syncRecordType.getDataArea().addNewRecord();
	        syncRecordType.getDataArea().addNewSync();
	        syncRecordType.getDataArea().getSync().addNewActionCriteria();
			
	        return syncRecordDocument;
		}
		
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	@Override
	public MappingAction[] getMappingActions(SoluminaApplicationEvent event) {
		
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
			                 MessageLoggerParams messageLoggerParams) {
		
        if (mappingActions != null) {
            messageLoggerParams.setRef1(((T319EventPW) mappingActions[0].getSourceObject()).getWorkOrderNumber());
            messageLoggerParams.setRef2(((T319EventPW) mappingActions[0].getSourceObject()).getOrderQty().toString());
            t319MapperImplPW.map(mappingActions, (SyncRecordDocument) outboundBod);
            String bodId = ((SyncRecordDocument) outboundBod).getSyncRecord().getApplicationArea().getBODID().getStringValue();
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
