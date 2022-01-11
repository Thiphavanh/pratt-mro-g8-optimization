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
*  File:    PWMROIDispositionEventProcessorImpl.java
* 
*  Created: 2020-01-24
* 
*  Author:  X005703
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	 ------------------------------------------------------------------
* 2020-01-24 	D.Sibrian		 Initial creation for update of T314 Interface messaging	    
*/
import com.ibaset.common.Reference;
import com.ibaset.common.event.SoluminaApplicationEvent;
import com.ibaset.solumina.domain.validator.ApplicationMessageSource;
import com.ibaset.solumina.integration.common.EventProcessor;
import com.ibaset.solumina.integration.common.MappingAction;
import com.ibaset.common.util.OAGIUtils; 
import com.ibaset.solumina.integration.common.SendSoluminaEventInOAGISListener;

import java.util.Date;

import org.apache.xmlbeans.XmlObject;

import com.ibaset.solumina.integration.logging.MessageLogger;
import com.ibaset.solumina.integration.logging.MessageLoggerParams;
import com.ibaset.solumina.oagis.ZSMI714DISPOSITIONDocument;
import com.ibaset.solumina.oagis.ZSMI714DISPOSITIONDocument.ZSMI714DISPOSITION.CNTROLAREA;
import com.pw.solumina.event.PWSDispositionEvent;
import com.pw.solumina.integration.PWMROIDispositionEventListener;
import com.pw.solumina.integration.PWMROIDispositionEventProcessor;

import java.util.UUID;
import static com.pw.solumina.common.PWMROIConstants.PW_S_T314_DISPOSITION;

public class PWMROIDispositionEventListenerImpl extends SendSoluminaEventInOAGISListener implements PWMROIDispositionEventListener
{
	private PWMROIDispositionEventProcessor pwmroiDispositionEventProcessor;
	
	@Reference
	private MessageLogger msgLogger;
	
	@Reference
	private ApplicationMessageSource applicationMsgSource;
	
	@Reference  private OAGIUtils OAGIUtils = null; 
	
	@Override
	public String getServiceName() {
		return PW_S_T314_DISPOSITION;
	}

	@Override
	public EventProcessor getEventProcessor() {
		return pwmroiDispositionEventProcessor;
	}	

	public PWMROIDispositionEventProcessor getPwmroiDispositionEventProcessor() {
		return pwmroiDispositionEventProcessor;
	}

	public void setPwmroiDispositionEventProcessor(PWMROIDispositionEventProcessor pwmroiDispositionEventProcessor) {
		this.pwmroiDispositionEventProcessor = pwmroiDispositionEventProcessor;
	}

	@Override
	public Class getSupportedEventType() {
		return PWSDispositionEvent.class;
	}
	
	@Override
	public void onApplicationEvent(SoluminaApplicationEvent event) 
	{
	      if(messageLogger==null) messageLogger=msgLogger;
	      if(applicationMessageSource==null) applicationMessageSource=applicationMsgSource;
	      if(eventProcessor==null) eventProcessor=pwmroiDispositionEventProcessor;
	      super.onApplicationEvent(event);
	}
	public String generateOAGISMessage(SoluminaApplicationEvent event,
            						   MessageLoggerParams messageLoggerParams) throws Exception
	{
		String outboundXml = null;

		EventProcessor eventProcessor = getEventProcessor(event);
		if (eventProcessor == null)
		{
			eventProcessor =  getEventProcessor();
		}

		if (eventProcessor != null)
		{
			MappingAction[] mappingActions = eventProcessor.getMappingActions(event);

			if (mappingActions != null && mappingActions.length > 0)
			{
				if (logger.isDebugEnabled())
				{
					logger.debug("Start generate message for "
							+ this.getServiceName() + " service");
				}

				XmlObject outboundBod = eventProcessor.createBod(event);

				if(outboundBod != null)
				{
					eventProcessor.processEvent(mappingActions,
							outboundBod,
							event,
							messageLoggerParams);

					outboundXml = OAGIUtils.convertToXmlText(outboundBod,
							OAGIUtils.getDefaultXmlOptions());
					String msgId = getMessageId((ZSMI714DISPOSITIONDocument)outboundBod);
					messageLoggerParams.setMessageID(msgId);
					messageLoggerParams.setMessageCreationDate(new Date());

					messageLoggerParams.setMessageText(outboundXml);

					// Validate outbound message
					OAGIUtils.validateXml(outboundBod);
					logger.info("Message sent for " + this.getServiceName());
				}                
			}
		}
		else
		{
			logger.error("EventProcessor in service "+this.getServiceName()+" is null");
		}

		return outboundXml;
	}

	private String getMessageId(ZSMI714DISPOSITIONDocument outboundBod) {
		CNTROLAREA cntrlArea = outboundBod.getZSMI714DISPOSITION().getCNTROLAREA();
		return cntrlArea.getSENDER().getTRANSID();
	}
}