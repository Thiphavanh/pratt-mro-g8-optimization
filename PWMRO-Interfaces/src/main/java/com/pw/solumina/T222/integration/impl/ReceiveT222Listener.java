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
 *  File:    IReceiveT222Listener.java
 * 
 *  Created: 2017-11-XX
 * 
 *  Author:  c293011
 * 
 *  Revision History
 * 
 *  Date      	Who                	Description
 *  ----------	---------------	---------------	---------------------------------------------------
 * 2017-11-XX 	c293011		    Initial Release SMRO_TR_T222 LO Notification
 */

package com.pw.solumina.T222.integration.impl;

import javax.jms.Message;
import java.util.Date;

import com.ibaset.common.Reference;
import com.ibaset.solumina.domain.validator.ApplicationMessageSource;
import com.ibaset.solumina.integration.common.IntegrationMessageListener;
import com.ibaset.solumina.integration.common.LoginUtility;
import com.ibaset.solumina.integration.confirm.SendConfirmBOD;
import com.ibaset.solumina.integration.logging.MessageLogger;
import com.ibaset.solumina.integration.logging.MessageLoggerParams;

import com.pw.solumina.T222.integration.impl.IT222MessageProcessor;


public class ReceiveT222Listener extends IntegrationMessageListener implements IReceiveT222Listener
{

	@Reference	private LoginUtility loginUtil;		
	@Reference	private MessageLogger messageLogger;
	@Reference	private ApplicationMessageSource applicationMessageSource;
	@Reference	private IT222MessageProcessor t222MessageProcessor;

	@Reference	private SendConfirmBOD sendConfirmBOD;


	@Override
	public String getServiceName() {

		return "PW_R_LO_NOTIFICATION";
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
			t222MessageProcessor.processMessage(xmlMessageString, loggerParams);

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

	public IT222MessageProcessor getMessageProcessor()
	{
		return t222MessageProcessor;
	}

	public void setMessageProcessor(IT222MessageProcessor messageProcessor)
	{
		this.t222MessageProcessor = messageProcessor;
	}    
}
