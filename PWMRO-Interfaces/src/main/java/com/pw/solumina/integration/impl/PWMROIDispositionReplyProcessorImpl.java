package com.pw.solumina.integration.impl;

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
*  File:    PWMROIDispositionMapperImpl.java
* 
*  Created: 2020-01-24
* 
*  Author:  X005703
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	 ------------------------------------------------------------------
* 2020-02-06 	D.Sibrian		 Initial creation for update of T314 Interface messaging	    
*/

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;

import com.ibaset.common.Reference;
import com.ibaset.solumina.integration.common.ActionCode;
import com.ibaset.solumina.integration.common.BaseMessageProcessor;
import com.ibaset.solumina.integration.common.MappingAction;
import com.ibaset.solumina.integration.exception.MappingException;
import com.ibaset.solumina.integration.exception.ParsingException;
import com.ibaset.solumina.integration.logging.MessageLoggerDao;
import com.ibaset.solumina.integration.logging.MessageLoggerParams;
import com.ibaset.solumina.integration.logging.ReplyLoggerParams;
import com.ibaset.solumina.integration.logging.impl.MessageLoggerParamsImpl;
import com.pw.solumina.domain.PwmroiSerial;
import com.pw.solumina.integration.PWMROIDispositionReplyProcessor;
import com.pw.solumina.sfwid.application.IPWMROISerial;
import com.pw.solumina.sfwid.dao.IPWMROISerialDao;
import com.ibaset.solumina.oagis.ZSMI714RETURNDocument;
import com.ibaset.solumina.oagis.ZSMI714RETURNDocument.ZSMI714RETURN;
import com.ibaset.solumina.sfbis.domain.LogTables;
import com.ibaset.solumina.sfcore.application.IMessage;
public class PWMROIDispositionReplyProcessorImpl extends BaseMessageProcessor implements PWMROIDispositionReplyProcessor
{
	@Reference
	private MessageLoggerDao transactionLoggerDao = null;
	
	@Reference
    private IPWMROISerialDao pwmroiSerialDao;
	
	@Reference
	private IPWMROISerial pwmroiSerial = null;
	
	@Reference
    private IMessage message = null;

	@Override
	public XmlObject parseReply(String inboundReply) throws ParsingException {
		return parseMessage(inboundReply);
	}
	
	@Override
	public MappingAction[] getMappingActions(XmlObject inboundBod) throws MappingException 
	{
	       return buildMappingActionFromResponseExpression(inboundBod);
	}

	@Override
	public void processReply(XmlObject inboundReply, ReplyLoggerParams replyLoggerParams) throws Exception {
		List mappingActions = new ArrayList(0);
		ActionCode actionCode = ActionCode.forName("Accepted");
		
		ZSMI714RETURNDocument zsmi714ReturnDocument = (ZSMI714RETURNDocument) inboundReply;
		MappingAction mappingAction = new MappingAction(actionCode,zsmi714ReturnDocument);
		mappingActions.add(mappingAction);
		MappingAction[] mappingActionsArray = (MappingAction[]) mappingActions.toArray(new MappingAction[mappingActions.size()]);
		if(zsmi714ReturnDocument.getZSMI714RETURN() != null && 
				zsmi714ReturnDocument.getZSMI714RETURN().getIDOC().getEDIDC40().getSNDLAD() != null)
		{
			replyLoggerParams.setMessageID(zsmi714ReturnDocument.getZSMI714RETURN().getIDOC().getEDIDC40().getSNDLAD());
		}
		
		if(StringUtils.equals(zsmi714ReturnDocument.getZSMI714RETURN().getIDOC().getZSMI714RETURNMESSAGEArray(0).getSTATUS(),"SUCCESS"))
		{
			MessageLoggerParams messageLoggerParams = new MessageLoggerParamsImpl(replyLoggerParams.getServiceName());
			processMessage(mappingActionsArray, inboundReply, messageLoggerParams);
		}
		else
			System.out.println("HERE");
		{   String transactionId = zsmi714ReturnDocument.getZSMI714RETURN().getIDOC().getEDIDC40().getSNDLAD();
		    String errorMessage = zsmi714ReturnDocument.getZSMI714RETURN().getIDOC().getZSMI714RETURNMESSAGEArray(0).getERRORMESSAGE();
		    String replyStatus = zsmi714ReturnDocument.getZSMI714RETURN().getIDOC().getZSMI714RETURNMESSAGEArray(0).getSTATUS();
		    String newMessage = replyStatus + " " + errorMessage;
		    pwmroiSerialDao.updateErrorMessageForDelivery(transactionId, newMessage);
		    pwmroiSerialDao.updateErrorMessageForScrap(transactionId, newMessage);
			//message.raiseBISError("MFI_20", zsmi714ReturnDocument.getZSMI714RETURN().getIDOC().getZSMI714RETURNMESSAGEArray(0).getERRORMESSAGE());
		}
		
	}

	@Override
	public void processMessage(MappingAction[] mappingActions, XmlObject inboundBod, MessageLoggerParams messageLoggerParams) throws Exception 
	{
		String requestId = null;
		XmlObject xmlObject = (XmlObject) mappingActions[0].getSourceObject();
		ZSMI714RETURNDocument zsmi714ReturnDocument = null;
		if(xmlObject instanceof ZSMI714RETURNDocument)
		{
			zsmi714ReturnDocument = ((ZSMI714RETURNDocument)xmlObject);
		}
		requestId = zsmi714ReturnDocument.getZSMI714RETURN().getIDOC().getEDIDC40().getSNDLAD();
		List<LogTables> messgeLoggerParamList = transactionLoggerDao.selectMessageLogByMessageID(requestId);
		if (messgeLoggerParamList.size() ==1)
        {            
             LogTables messageLogger = messgeLoggerParamList.get(0);;

             String orderNo = messageLogger.getRef1();
             String type = messageLogger.getRef2();
             PwmroiSerial pwmroiSerialDomain = pwmroiSerial.selectPwmroiSerialForReply(requestId, type);
             if(StringUtils.equals(type, "DELIVER"))
             {
            	 pwmroiSerialDao.updateDeliverSerialStatus(pwmroiSerialDomain.getOrderId(), pwmroiSerialDomain.getSerialId());
             }
             else
             {
            	 pwmroiSerial.scrapUnitAndUpdateField(pwmroiSerialDomain);
             }
         }
		
	}
	@Override
	public SchemaType getInboundBodType() {
		return ZSMI714RETURNDocument.type;
	}

}
