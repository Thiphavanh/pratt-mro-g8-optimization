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
*  File:    PaarRequestEventListenerImpl.java
* 
*  Created: 2018-11-15
* 
*  Author:  David Miron
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	-------------------------------------------------------------------
* 2018-08-24 	D.Miron		    Added PWU-124 Update from iBaset - Francis Graham
*/

package com.utas.solumina.paar.integration.impl;

import com.ibaset.common.Reference;
import com.ibaset.common.event.SoluminaApplicationEvent;
import com.ibaset.solumina.domain.validator.ApplicationMessageSource;
import com.ibaset.solumina.integration.common.EventProcessor;
import com.ibaset.solumina.integration.common.SendSoluminaEventInOAGISListener;
import com.ibaset.solumina.integration.logging.MessageLogger;
import com.utas.solumina.common.UtasConstants;
import com.utas.solumina.paar.event.PaarRequestEvent;
import com.utas.solumina.paar.integration.PaarRequestEventListener;
import com.utas.solumina.paar.integration.PaarRequestEventProcessor;

public class PaarRequestEventListenerImpl extends SendSoluminaEventInOAGISListener implements PaarRequestEventListener 
{
	private PaarRequestEventProcessor paarRequestEventProcessor;
	
	@Reference
	MessageLogger msgLogger;
	
	@Reference
	ApplicationMessageSource applicationMsgSource;

	@Override
	public String getServiceName() 
	{
		return UtasConstants.S_PAAR_REQUEST;
	}

	@Override
	public EventProcessor getEventProcessor() 
	{
		return paarRequestEventProcessor;
	}
	
	public void setPaarRequestEventProcessor(PaarRequestEventProcessor paarRequestEventProcessor) 
	{
		this.paarRequestEventProcessor = paarRequestEventProcessor;
	}
	
	public PaarRequestEventProcessor getPaarRequestEventProcessor() 
	{
		return paarRequestEventProcessor;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Class getSupportedEventType() 
	{
		return PaarRequestEvent.class;
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
