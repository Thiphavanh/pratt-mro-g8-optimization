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
 *  File:    ReceiveM316Listener.java
 * 
 *  Created: 2019-08-05
 * 
 *  Author:  B. Corson
 * 
 *  Revision History
 * 
 *  Date          Who            Description
 *  -----------   ------------   -----------------------------------------------------------
 *  2019-08-05    B. Corson      Initial Release SMRO_MD_M316 - Scrap Codes and Hold Types Groups Interface
 *  
 */
package com.pw.solumina.M316.integration.impl;

import java.util.Date;

import javax.jms.Message;

import com.ibaset.common.Reference;
import com.ibaset.solumina.domain.validator.ApplicationMessageSource;
import com.ibaset.solumina.integration.common.IntegrationMessageListener;
import com.ibaset.solumina.integration.common.LoginUtility;
import com.ibaset.solumina.integration.confirm.SendConfirmBOD;
import com.ibaset.solumina.integration.logging.MessageLogger;
import com.ibaset.solumina.integration.logging.MessageLoggerParams;
import com.pw.solumina.M316.integration.IReceiveM316Listener;
import com.pw.solumina.M316.integration.IM316MessageProcessor;

public class ReceiveM316Listener 
     extends IntegrationMessageListener 
     implements IReceiveM316Listener {
    
    @Reference private LoginUtility loginUtil;
    @Reference private MessageLogger messageLogger;
    @Reference private ApplicationMessageSource applicationMessageSource;
    @Reference private IM316MessageProcessor m316MessageProcessor;
    
    @Reference private SendConfirmBOD sendConfirmBOD;
    
    @Override
    public String getServiceName() {
        return "PW_R_M316_CAT_CODES";
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
            m316MessageProcessor.processMessage(xmlMessageString, loggerParams);
        } catch (Throwable t) {
            loggerParams.setStatus(MessageLogger.STATUS_RECEIVE_FAILURE);
            loggerParams.setErrors(t);
        }
    }
    
    @Override
    public void onMessage(Message inboundMessage) {
        if(loginUtility == null) loginUtility = loginUtil;
        if(messageLogger == null) messageLogger = messageLogger;
        if(applicationMessageSource == null) applicationMessageSource = applicationMessageSource;
        super.onMessage(inboundMessage);
    }
    
    public SendConfirmBOD getConfirm() {
        SendConfirmBOD sendConfirmBOD = null;
        return sendConfirmBOD;
    }
    
    public void setConfirm(SendConfirmBOD confirm) {
        this.sendConfirmBOD = confirm;
    }
    
    public IM316MessageProcessor getMessageProcessor() {
        return m316MessageProcessor;
    }
    
    public void setMessageProcessor(IM316MessageProcessor messageProcessor) {
        this.m316MessageProcessor = messageProcessor;
    }
}
