/*
 *  This unpublished work, first created in 2018 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2018.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    ReceiveT226Listener.java
 * 
 *  Created: 2018-02-05
 * 
 *  Author:  Catrina Nguyen
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2018-02-05		C. Nguyen		Initial Release SMRO_TR_T226 - Module Swaps Interface
 *  2018-04-10		C. Nguyen		Took out the getter and setter for loginUtil, messageLogger, and applicationMessageSource.  These are not needed
 *  								
 */


package com.pw.solumina.T226.integration.impl;

import java.util.Date;

import javax.jms.Message;

import com.ibaset.common.Reference;
import com.ibaset.solumina.domain.validator.ApplicationMessageSource;
import com.ibaset.solumina.integration.common.IntegrationMessageListener;
import com.ibaset.solumina.integration.common.LoginUtility;
import com.ibaset.solumina.integration.confirm.SendConfirmBOD;
import com.ibaset.solumina.integration.logging.MessageLogger;
import com.ibaset.solumina.integration.logging.MessageLoggerParams;
import com.pw.solumina.T226.integration.IReceiveT226Listener;
import com.pw.solumina.T226.integration.IT226MessageProcessor;

public class ReceiveT226Listener extends IntegrationMessageListener implements IReceiveT226Listener {

	@Reference	private LoginUtility loginUtil;		
	@Reference	private MessageLogger messageLogger;
	@Reference	private ApplicationMessageSource applicationMessageSource;
	@Reference	private IT226MessageProcessor t226MessageProcessor;
	                                          
	@Reference	private SendConfirmBOD sendConfirmBOD;
	
	@Override
	public String getServiceName() {
		
		return "PW_R_T226_MODULE_SWAPS"; 
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
			t226MessageProcessor.processMessage(xmlMessageString, loggerParams);
			
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
    
    public IT226MessageProcessor getMessageProcessor()
    {
        return t226MessageProcessor;
    }

    public void setMessageProcessor(IT226MessageProcessor messageProcessor)
    {
        this.t226MessageProcessor = messageProcessor;
    }    


	
}
