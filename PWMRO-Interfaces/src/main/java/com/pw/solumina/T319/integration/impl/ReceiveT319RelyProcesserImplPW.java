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
*  File:    ReceiveT319RelyProcesserImplPW.java
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
* 2019_08_21    Fred Ettefagh   changed raiseBISError to raiseError
* 2019-09-12    Fred Ettefagh   SMRO_TR_319, added code for SCR-001
* 2019-09-20    Bahram J.       updated error handling
* 2019-11-15    Fred Ettefagh   changed xml name tag from "STATUS" to  "NEW_SM_ORDER_NUMBER"
*/
package com.pw.solumina.T319.integration.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openapplications.oagis.x9.KeysType;
import org.openapplications.oagis.x9.NameValuePairType;
import org.openapplications.oagis.x9.RecordType;
import org.openapplications.oagis.x9.SyncRecordDataAreaType;
import org.openapplications.oagis.x9.SyncRecordDocument;
import org.openapplications.oagis.x9.SyncRecordType;

import com.ibaset.common.Reference;
import com.ibaset.solumina.integration.common.BaseMessageProcessor;
import com.ibaset.solumina.integration.common.MappingAction;
import com.ibaset.solumina.integration.exception.ParsingException;
import com.ibaset.solumina.integration.logging.MessageLoggerParams;
import com.ibaset.solumina.integration.logging.ReplyLoggerParams;
import com.ibaset.solumina.integration.logging.impl.MessageLoggerParamsImpl;
import com.ibaset.solumina.integration.logging.impl.ReplyLoggerParamsImpl;
import com.pw.common.dao.CommonDaoPW;


public class ReceiveT319RelyProcesserImplPW  extends BaseMessageProcessor implements ReceiveT319RelyProcesser{
	
	@Reference T319MapperImplPW t319MapperImplPW = null;
	@Reference CommonDaoPW commonDaoPW = null;
	
	
	@Override
	public SchemaType getInboundBodType() {
		
		return SyncRecordDocument.type;
	}


    @SuppressWarnings({ "rawtypes", "unused" })
	public void processMessage(MappingAction[] mappingActions,
					           XmlObject inboundBod,
					           ReplyLoggerParamsImpl replyLoggerParams) throws Exception{
    	
		Map actionMap = buildActionMap(mappingActions);
		Set objectIdSet = actionMap.keySet();
		Map nounMap = buildNounMap(objectIdSet);
		MessageLoggerParams messageLoggerParams = new MessageLoggerParamsImpl(replyLoggerParams.getServiceName());
		doProcessing(actionMap, nounMap, inboundBod, replyLoggerParams);
   }

    @SuppressWarnings({ "rawtypes" })
	protected void doProcessing(Map actionMap,
                                Map nounMap,
                                XmlObject inboundBOD,
                                ReplyLoggerParamsImpl replyLoggerParams) throws Exception{		


		String smOrderNo = null;
		String errorMsg = null;
		if(inboundBOD instanceof SyncRecordDocument) {
			
			SyncRecordDocument syncRecordDocument = (SyncRecordDocument) inboundBOD;
			SyncRecordType syncRecordType = syncRecordDocument.getSyncRecord();
			SyncRecordDataAreaType dataArea = syncRecordType.getDataArea();
			List<RecordType> recordTypeList = dataArea.getRecordList();
			
			for (int i=0; i<recordTypeList.size(); i++) {

				RecordType recordType = (RecordType) recordTypeList.get(i);
				KeysType keysType = recordType.getKeys();
				List<NameValuePairType> keyList = keysType.getRecordKeyList();
				
				if (keyList!=null && !keyList.isEmpty()){
					
					for (int j=0; j<keyList.size(); j++) {
						NameValuePairType nameValuePairType = (NameValuePairType) keyList.get(j);
						String recordKeyName = nameValuePairType.getName();
						
						if ("NEW_SM_ORDER_NUMBER".equalsIgnoreCase(recordKeyName)){
							smOrderNo = nameValuePairType.getStringValue();
						}
						if ("ERROR_MSG".equalsIgnoreCase(recordKeyName)){
							errorMsg = nameValuePairType.getStringValue();
						}
					}
				}
			}
		}else{
			errorMsg="response xml message is not valid from SAP for T319";
		}
	
		if (StringUtils.isNotBlank(errorMsg)){
			replyLoggerParams.setErrorText(errorMsg);
		}else if (StringUtils.isNotBlank(smOrderNo)){
			replyLoggerParams.setRef5(smOrderNo);
		}
	}

	@Override
	public XmlObject parseReply(String inboundMessage) throws ParsingException {
		
	        XmlObject inboundBod = null;
	        SchemaType inboundBodDocumentType = getInboundBodType();
	        
	        try {
	            inboundBod = XmlObject.Factory.parse(inboundMessage, new XmlOptions().setDocumentType(inboundBodDocumentType));
	        }
	        catch (XmlException xe) {
	        	return null;
	        }

	        return inboundBod;
    }

	@Override
	public void processReply(XmlObject inboundReply, ReplyLoggerParams replyLoggerParams) throws Exception {

        MappingAction[] mappingActions = getMappingActions(inboundReply);
        processMessage(mappingActions, inboundReply, (ReplyLoggerParamsImpl)replyLoggerParams);
	}
	
	
    public void validateMappingAction(MappingAction mappingAction) throws UnsupportedOperationException {
        
    }

}
