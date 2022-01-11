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
*  File:    ReceiveT308ReplyProcessorImpl.java
* 
*  Created: 2019-08-01
* 
*  Author:  Raeann Thorpe
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2019-08-01 	Raeann Thorpe   Initial Release for PW_SMRO_TR_308
* 2019-09-20    Bahram J.       SMRO_TR_308, changed xml to use synWorkOrder instead of syncRecord
* 2019-11-06    Fred Ettefagh   SMRO_TR_308, changed how xml is parsed for error msg
* 2019-11-08    Fred Ettefagh   SMRO_TR_308, changed parser to that if <code> tag is "Success", <Reason> tag is not ignored.  This is due to SAP sending "Operations added Successfully" 
*                                            for <Reason> tag when we have "Success"   
*/

package com.pw.solumina.T308.integration.impl;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openapplications.oagis.x9.StatusType;
import org.openapplications.oagis.x9.SyncWorkOrderDataAreaType;
import org.openapplications.oagis.x9.SyncWorkOrderDocument;
import org.openapplications.oagis.x9.SyncWorkOrderType;
import org.openapplications.oagis.x9.TextType;
import org.openapplications.oagis.x9.WorkOrderHeaderType;
import org.openapplications.oagis.x9.WorkOrderType;

import com.ibaset.common.Reference;
import com.ibaset.solumina.integration.common.BaseMessageProcessor;
import com.ibaset.solumina.integration.common.IOAGIUtils;
import com.ibaset.solumina.integration.common.MappingAction;
import com.ibaset.solumina.integration.exception.ParsingException;
import com.ibaset.solumina.integration.logging.MessageLoggerParams;
import com.ibaset.solumina.integration.logging.ReplyLoggerParams;
import com.ibaset.solumina.integration.logging.impl.MessageLoggerParamsImpl;
import com.ibaset.solumina.integration.logging.impl.ReplyLoggerParamsImpl;
import com.pw.common.dao.CommonDaoPW;
import com.pw.solumina.T308.integration.IReceiveT308ReplyProcessor;


public class ReceiveT308ReplyProcessorImpl  extends BaseMessageProcessor implements IReceiveT308ReplyProcessor{
	
	@Reference T308MapperImpl t308MapperImpl = null;
	@Reference CommonDaoPW commonDaoPW = null;
	@Reference IOAGIUtils oagiUtils = null;
	
	@Override
	public SchemaType getInboundBodType(){ 
		return SyncWorkOrderDocument.type;
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
	
    @SuppressWarnings({ "rawtypes"})
	protected void doProcessing(Map actionMap,
                                Map nounMap,
                                XmlObject inboundBOD,
                                ReplyLoggerParamsImpl replyLoggerParams) throws Exception{		
		String status = null;
		String errorMsg = null;
		if(inboundBOD instanceof SyncWorkOrderDocument){
			
			SyncWorkOrderDocument syncWorkOrderDocument = (SyncWorkOrderDocument) inboundBOD;
			SyncWorkOrderType syncWorkOrderType = syncWorkOrderDocument.getSyncWorkOrder();
			SyncWorkOrderDataAreaType syncWorkOrderDataAreaType =  syncWorkOrderType.getDataArea();
			WorkOrderType workOrderType = syncWorkOrderDataAreaType.getWorkOrderArray(0);
			WorkOrderHeaderType  workOrderHeaderType = workOrderType.getWorkOrderHeader();
			
			if (workOrderHeaderType.getStatusArray(0)==null){
				errorMsg= "XML return message is missing <Status> ";
			}else{
				StatusType statusType=  workOrderHeaderType.getStatusArray(0);	

				if (statusType.isSetCode()){
					status = statusType.getCode().getStringValue();
				}
				
				if(!StringUtils.equalsIgnoreCase(status, "SUCCESS")){
					TextType textType = statusType.getReasonArray(0);
					if (textType!=null){
						errorMsg = "SAP error: " + textType.getStringValue();
					}
				}
			}
		}
		else{
			errorMsg = "Response xml message is not valid from SAP for T308";
			
		}
		
		if (StringUtils.isNotBlank(errorMsg)){
			replyLoggerParams.setErrorText(errorMsg);
		}else if (StringUtils.isNotBlank(status)){
			replyLoggerParams.setRef5(status);	
		}
		
	}

	@Override
	public XmlObject parseReply(String inboundMessage) throws ParsingException 
	{
	        XmlObject inboundBod = null;
	        SchemaType inboundBodDocumentType = getInboundBodType();
	        
	        try {
	            inboundBod = XmlObject.Factory.parse(inboundMessage, new XmlOptions().setDocumentType(inboundBodDocumentType));
	        }
	        catch (XmlException xe){
	        	//message.raiseError("MFI_50400","Response xml message is not valid from SAP for T308");
	        	return null;
	        }
	        return inboundBod;
    }

	@Override
	public void processReply(XmlObject inboundReply, ReplyLoggerParams replyLoggerParams) throws Exception{
        MappingAction[] mappingActions = getMappingActions(inboundReply);
        processMessage(mappingActions, inboundReply, (ReplyLoggerParamsImpl)replyLoggerParams);
	}
	
	
    public void validateMappingAction(MappingAction mappingAction) throws UnsupportedOperationException {
        
    }

}
