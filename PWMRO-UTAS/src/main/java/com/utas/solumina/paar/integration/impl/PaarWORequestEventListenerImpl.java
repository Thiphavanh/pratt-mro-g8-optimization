package com.utas.solumina.paar.integration.impl;

import com.ibaset.common.Reference;
import com.ibaset.common.event.SoluminaApplicationEvent;
import com.ibaset.solumina.domain.validator.ApplicationMessageSource;
import com.ibaset.solumina.integration.common.EventProcessor;
import com.ibaset.solumina.integration.common.SendSoluminaEventInOAGISListener;
import com.ibaset.solumina.integration.logging.MessageLogger;
import com.utas.solumina.common.UtasConstants;
import com.utas.solumina.paar.event.PaarWORequestEvent;
import com.utas.solumina.paar.integration.PaarRequestEventProcessor;
import com.utas.solumina.paar.integration.PaarWORequestEventListener;

public class PaarWORequestEventListenerImpl extends SendSoluminaEventInOAGISListener implements PaarWORequestEventListener 
{
	PaarRequestEventProcessor paarRequestEventProcessor;
	@Reference
	MessageLogger msgLogger;
	
	@Reference
	ApplicationMessageSource applicationMsgSource;
	
	@Override
	public String getServiceName() 
	{
		return UtasConstants.S_PAAR_REQUEST;
	}

	
	public PaarRequestEventProcessor getPaarRequestEventProcessor() 
	{		
		return paarRequestEventProcessor;
	}


	public void setPaarRequestEventProcessor(PaarRequestEventProcessor paarRequestEventProcessor) 
	{
		this.paarRequestEventProcessor = paarRequestEventProcessor;
	}


	@Override
	public EventProcessor getEventProcessor() 
	{
		return paarRequestEventProcessor;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getSupportedEventType() 
	{
		return PaarWORequestEvent.class;
	}

	@Override
	public void onApplicationEvent(SoluminaApplicationEvent event) 
	{
		if(messageLogger==null) messageLogger=msgLogger;
		if(applicationMessageSource==null) applicationMessageSource=applicationMsgSource;
		if(eventProcessor==null) eventProcessor=paarRequestEventProcessor;
		super.onApplicationEvent(event);
	}
}
