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

import com.pw.solumina.integration.PWMROIDispositionReplyListener;
import com.pw.solumina.integration.PWMROIDispositionReplyProcessor;

import static com.pw.solumina.common.PWMROIConstants.PW_S_T314_DISPOSITION;

import javax.jms.Message;

import com.ibaset.common.Reference;
import com.ibaset.solumina.domain.validator.ApplicationMessageSource;
import com.ibaset.solumina.integration.common.JmsTemplate;
import com.ibaset.solumina.integration.common.LoginUtility;
import com.ibaset.solumina.integration.common.ReceiveOAGISReplyListener;
import com.ibaset.solumina.integration.common.ReplyProcessor;
import com.ibaset.solumina.integration.logging.MessageLogger;

public class PWMROIDispositionReplyListenerImpl extends ReceiveOAGISReplyListener implements PWMROIDispositionReplyListener 
{
	private PWMROIDispositionReplyProcessor pwmroiDispositionReplyProcessor;
	@Reference
	private MessageLogger msgLogger;
	@Reference
	private ApplicationMessageSource applicationMsgSource;
	@Reference
	private LoginUtility loginUtil;
	@Reference
	private JmsTemplate replyJmsTemp;

	@Override
	public ReplyProcessor getReplyProcessor(String messageText) {
		return pwmroiDispositionReplyProcessor;
	}

	@Override
	public String getServiceName() {
		return PW_S_T314_DISPOSITION;
	}	

	@Override
	public void onMessage(Message inboundMessage) 
	{
		 if(messageLogger==null) messageLogger=msgLogger;
	     if(applicationMessageSource==null) applicationMessageSource=applicationMsgSource;
	     if(loginUtility==null) loginUtility=loginUtil;
	     if(replyJmsTemplate==null) replyJmsTemplate = replyJmsTemp;
	     super.onMessage(inboundMessage);
	}
	
	public PWMROIDispositionReplyProcessor getPwmroiDispositionReplyProcessor() {
		return pwmroiDispositionReplyProcessor;
	}

	public void setPwmroiDispositionReplyProcessor(PWMROIDispositionReplyProcessor pwmroiDispositionReplyProcessor) {
		this.pwmroiDispositionReplyProcessor = pwmroiDispositionReplyProcessor;
	}

}
