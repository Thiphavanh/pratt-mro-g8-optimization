package com.utas.solumina.paar.integration.impl;

import javax.jms.Message;

import com.ibaset.common.Reference;
import com.ibaset.solumina.domain.validator.ApplicationMessageSource;
import com.ibaset.solumina.integration.common.JmsTemplate;
import com.ibaset.solumina.integration.common.LoginUtility;
import com.ibaset.solumina.integration.common.ReceiveOAGISReplyListener;
import com.ibaset.solumina.integration.common.ReplyProcessor;
import com.ibaset.solumina.integration.logging.MessageLogger;
import com.utas.solumina.common.UtasConstants;
import com.utas.solumina.paar.integration.PaarRequestReplyListener;
import com.utas.solumina.paar.integration.PaarRequestReplyProcessor;

public class PaarRequestReplyListenerImpl extends ReceiveOAGISReplyListener implements PaarRequestReplyListener
{
	PaarRequestReplyProcessor paarRequestReplyProcessor;
	
	@Reference
	MessageLogger msgLogger;
	@Reference
	ApplicationMessageSource applicationMsgSource;
	@Reference
	LoginUtility loginUtil;
	@Reference
	JmsTemplate replyJmsTemp;

	@Override
	public String getServiceName() 
	{
		return UtasConstants.S_PAAR_REQUEST;
	}

	@Override
	public ReplyProcessor getReplyProcessor(String messageText) 
	{
		return paarRequestReplyProcessor;
	}
	
	public PaarRequestReplyProcessor getPaarRequestReplyProcessor() 
	{
		return paarRequestReplyProcessor;
	}

	public void setPaarRequestReplyProcessor(PaarRequestReplyProcessor paarRequestReplyProcessor) 
	{
		this.paarRequestReplyProcessor = paarRequestReplyProcessor;
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
}
