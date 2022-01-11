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
*  File:    ReceiveEACPListener.java
* 
*  Created: 2017-10-19
* 
*  Author:  c293011
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-10-19 		c293011		    Initial Release XXXXXXX
*/


package com.pw.solumina.eacp.integration.impl;

/**
 * @author c293011
 *
 */
import java.util.Date;

import javax.jms.Message;

import com.ibaset.common.Reference;
import com.ibaset.solumina.domain.validator.ApplicationMessageSource;
import com.ibaset.solumina.integration.common.IntegrationMessageListener;
import com.ibaset.solumina.integration.common.LoginUtility;
import com.ibaset.solumina.integration.confirm.SendConfirmBOD;
import com.ibaset.solumina.integration.logging.MessageLogger;
import com.ibaset.solumina.integration.logging.MessageLoggerParams;

import com.pw.solumina.eacp.integration.IReceiveEACPListener;
import com.pw.solumina.eacp.integration.IEACPMessageProcessor;


public class ReceiveEACPListener extends IntegrationMessageListener implements IReceiveEACPListener {

	
	//@Reference	private M111MessageProcessor partReltIntInterfaceProcessor;	
	@Reference	private LoginUtility loginUtil;		
	@Reference	private MessageLogger msgLg;
	@Reference	private ApplicationMessageSource ams;
	@Reference	private IEACPMessageProcessor messageProcessor = null;
	@Reference	private SendConfirmBOD sendConfirmBOD;
	//@Reference  private com.pw.common.dao.CommonDaoPW commonDaoPW = null;
	
	@Override
	public String getServiceName()
	{
		return "PW_R_EACP_AUTH";
	} 
	

	@Override
	public void onMessage(Message inboundMessage)
	{
		if(loginUtility == null) loginUtility = loginUtil;
		if(messageLogger == null) messageLogger = msgLg;
		if(applicationMessageSource ==null) applicationMessageSource = ams;
		super.onMessage(inboundMessage);
	}

	@Override
	public void processMessage(Message message, MessageLoggerParams loggerParams) 
	{
		try 
		{			
			// Extract Message Text
			String msg = super.getMessageText(message);
			
			// Setup Logger Info
			loggerParams.setMessageText(msg);
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
			messageProcessor.processMessage(msg, loggerParams);
			
		} catch (Throwable t) 
		{		
			loggerParams.setStatus(MessageLogger.STATUS_RECEIVE_FAILURE);
			loggerParams.setErrors(t);
		}
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
    
    public IEACPMessageProcessor getMessageProcessor()
    {
        return messageProcessor;
    }

    public void setMessageProcessor(IEACPMessageProcessor messageProcessor)
    {
        this.messageProcessor = messageProcessor;
    }
}
