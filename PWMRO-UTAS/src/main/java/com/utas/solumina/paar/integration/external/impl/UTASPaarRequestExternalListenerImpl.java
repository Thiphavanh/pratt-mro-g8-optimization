package com.utas.solumina.paar.integration.external.impl;

import javax.jms.Message;

import org.apache.xmlbeans.XmlObject;

import com.ibaset.common.Reference;
import com.ibaset.solumina.integration.common.MessageProcessor;
import com.ibaset.solumina.integration.common.ReceiveOAGISMessageListener;
import com.utas.solumina.common.UtasConstants;
import com.utas.solumina.paar.integration.external.IUTASPaarRequestExternalListener;
import com.utas.solumina.paar.integration.external.IUTASPaarRequestExternalProcessor;

public class UTASPaarRequestExternalListenerImpl   extends ReceiveOAGISMessageListener 
 implements IUTASPaarRequestExternalListener 
{

	@Reference
	private IUTASPaarRequestExternalProcessor paarRequestExternalProcessor;
	
	@Override
	public MessageProcessor getMessageProcessor(String messageText) 
	{
		return paarRequestExternalProcessor;
	}

	@Override
	public String getServiceName() 
	{
		return UtasConstants.R_PAAR_REQUEST;
	}

	@Override
	public void postMessageProcessing(Message inboundMessage,
									  XmlObject inboundBod) 
	{	
		
	}

	public IUTASPaarRequestExternalProcessor getPaarRequestExternalProcessor() 
	{
		return paarRequestExternalProcessor;
	}

	public void setPaarRequestExternalProcessor(IUTASPaarRequestExternalProcessor paarRequestExternalProcessor) 
	{
		this.paarRequestExternalProcessor = paarRequestExternalProcessor;
	}

}
