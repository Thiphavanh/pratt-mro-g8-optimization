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
*  File:    ReceiveStandardNetworkListener.java
* 
*  Created: 2017-09-25
* 
*  Author:  xcs4331
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-09-25 		xcs4331		    Initial Release XXXXXXX
*/

package com.pw.solumina.T221.integration.impl;

import javax.jms.Message;
import java.util.Date;
import com.ibaset.common.Reference;
import com.ibaset.solumina.domain.validator.ApplicationMessageSource;
import com.ibaset.solumina.integration.common.IntegrationMessageListener;
import com.ibaset.solumina.integration.common.LoginUtility;
import com.ibaset.solumina.integration.confirm.SendConfirmBOD;
import com.ibaset.solumina.integration.logging.MessageLogger;
import com.ibaset.solumina.integration.logging.MessageLoggerParams;

/**
 * @author xcs4331
 *
 */


public class ReceiveM221Listener extends IntegrationMessageListener implements IReceiveM221Listener{

	@Reference	private LoginUtility loginUtil;		
	@Reference	private MessageLogger messageLogger;
	@Reference	private ApplicationMessageSource applicationMessageSource;
	@Reference	private IM221MessageProcessor m221MessageProcessor;
	                                          
	@Reference	private SendConfirmBOD sendConfirmBOD;
	
	@Override
	public String getServiceName() {
		
		return "PW_R_M221_NETWORK";
	}

	@Override
	public void processMessage(Message message, MessageLoggerParams loggerParams) {
	
		try 
		{			
			// Extract Message Text
			String xmlMessageString = super.getMessageText(message);
			
			// Setup Logger Info
			loggerParams.setMessageText(xmlMessageString);
			if(message.getJMSMessageID() != null)
			{
				if(message.getJMSMessageID().length() > 40)
					loggerParams.setMessageID(message.getJMSMessageID().substring(0, 40));
				else
					loggerParams.setMessageID(message.getJMSMessageID());
			}
			if(message.getJMSTimestamp() > 0)
				loggerParams.setMessageCreationDate(new Date(message.getJMSTimestamp()));
			
			// Process
			m221MessageProcessor.processMessage(xmlMessageString, loggerParams);
			
		} catch (Throwable t) 
		{		
			loggerParams.setStatus(MessageLogger.STATUS_RECEIVE_FAILURE);
			loggerParams.setErrors(t);
		}
		
	}
	
	
	@Override
	public void onMessage(Message inboundMessage)
	{
		if(loginUtility == null) loginUtility = loginUtil;
		if(messageLogger == null) messageLogger = messageLogger;
		if(applicationMessageSource ==null) applicationMessageSource = applicationMessageSource;
		super.onMessage(inboundMessage);
	}
	
    public SendConfirmBOD getConfirm()
    {
        SendConfirmBOD sendConfirmBOD = null;
		return sendConfirmBOD;
    }

    public void setConfirm(SendConfirmBOD confirm)
    {
        this.sendConfirmBOD = confirm;
    }
    
    public IM221MessageProcessor getMessageProcessor()
    {
        return m221MessageProcessor;
    }

    public void setMessageProcessor(IM221MessageProcessor messageProcessor)
    {
        this.m221MessageProcessor = messageProcessor;
    }    
	
}
