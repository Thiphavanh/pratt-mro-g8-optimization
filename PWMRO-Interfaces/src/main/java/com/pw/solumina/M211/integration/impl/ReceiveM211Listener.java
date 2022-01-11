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
 *  File:    ReceiveM211Listener.java
 * 
 *  Created: 2018-03-02 
 * 
 *  Author:  Raeann Thorpe  
 * 
 *  Revision History
 * 
 *  Date        Who          	Description
 *  --------    ------------ 	---------------------------------------------------
 *  2018-03-02	R. Thorpe		Initial Release for SMRO_MD_M211
 * 
 */

package com.pw.solumina.M211.integration.impl;

import java.util.Date;

import javax.jms.Message;

import com.ibaset.common.Reference;
import com.ibaset.solumina.domain.validator.ApplicationMessageSource;
import com.ibaset.solumina.integration.common.IntegrationMessageListener;
import com.ibaset.solumina.integration.common.LoginUtility;
import com.ibaset.solumina.integration.confirm.SendConfirmBOD;
import com.ibaset.solumina.integration.logging.MessageLogger;
import com.ibaset.solumina.integration.logging.MessageLoggerParams;
import com.pw.solumina.M211.integration.impl.IM211MessageProcessor;
import com.pw.solumina.M211.integration.impl.IReceiveM211Listener;

public class ReceiveM211Listener extends IntegrationMessageListener implements IReceiveM211Listener{
	
	@Reference	private LoginUtility loginUtil;		
	@Reference	private MessageLogger msgLg;
	@Reference	private ApplicationMessageSource ams;
	@Reference	private IM211MessageProcessor m211MessageProcessor = null;
	@Reference  private com.pw.common.dao.CommonDaoPW commonDaoPW = null;
	
	@Reference	private SendConfirmBOD sendConfirmBOD;
	
	@Override
	public String getServiceName()
	{
		return "PW_R_M211_TEM";
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
			m211MessageProcessor.processMessage(msg, loggerParams);
			
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
		if(messageLogger == null) messageLogger = msgLg;
		if(applicationMessageSource ==null) applicationMessageSource = ams;
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
    
    public IM211MessageProcessor getMessageProcessor()
    {
        return m211MessageProcessor;
    }

    public void setMessageProcessor(IM211MessageProcessor messageProcessor)
    {
        this.m211MessageProcessor = messageProcessor;
    }
    
}

