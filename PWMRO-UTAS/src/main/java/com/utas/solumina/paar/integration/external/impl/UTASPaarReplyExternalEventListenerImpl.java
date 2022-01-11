package com.utas.solumina.paar.integration.external.impl;

import com.ibaset.common.Reference;
import com.ibaset.common.event.SoluminaApplicationEvent;
import com.ibaset.solumina.domain.validator.ApplicationMessageSource;
import com.ibaset.solumina.integration.common.EventProcessor;
import com.ibaset.solumina.integration.common.SendSoluminaEventInOAGISListener;
import com.ibaset.solumina.integration.logging.MessageLogger;
import com.utas.solumina.common.UtasConstants;
import com.utas.solumina.paar.event.UTASPaarReplyExternalEvent;
import com.utas.solumina.paar.integration.external.IUTASPaarReplyExternalEventListener;
import com.utas.solumina.paar.integration.external.IUTASPaarReplyExternalEventProcessor;

public class UTASPaarReplyExternalEventListenerImpl extends SendSoluminaEventInOAGISListener implements IUTASPaarReplyExternalEventListener
{
	IUTASPaarReplyExternalEventProcessor paarReplyExternalEventProcessor;

	@Reference
	MessageLogger msgLogger;
	
	@Reference
	ApplicationMessageSource applicationMsgSource;
	
	@Override
	public String getServiceName() 
	{
		return UtasConstants.S_PAAR_REPLY;
//		return UtasConstants.R_PAAR_REQUEST;
	}

	@Override
	public EventProcessor getEventProcessor() 
	{
		return paarReplyExternalEventProcessor;
	}

	@Override
	public Class getSupportedEventType() 
	{
		return UTASPaarReplyExternalEvent.class;
	}

	
	
	public IUTASPaarReplyExternalEventProcessor getPaarReplyExternalEventProcessor() 
	{
		return paarReplyExternalEventProcessor;
	}

	public void setPaarReplyExternalEventProcessor(IUTASPaarReplyExternalEventProcessor paarReplyExternalEventProcessor) 
	{
		this.paarReplyExternalEventProcessor = paarReplyExternalEventProcessor;
	}

	@Override
	public void onApplicationEvent(SoluminaApplicationEvent event) 
	{
		if(messageLogger==null) messageLogger=msgLogger;
		if(applicationMessageSource==null) applicationMessageSource=applicationMsgSource;
		if(eventProcessor==null) eventProcessor=paarReplyExternalEventProcessor;
		super.onApplicationEvent(event);
	}	
}
