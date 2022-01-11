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
*  File:    SendT308ListenerImpl.java
* 
*  Created: 2019-08-01
* 
*  Author:  Raeann Thorpe
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2019-08-01 	Raeann Thorpe       Initial Release for PW_SMRO_TR_308
*/
package com.pw.solumina.T308.integration.impl;

import com.ibaset.common.Reference;
import com.ibaset.common.event.SoluminaApplicationEvent;
import com.ibaset.common.event.SoluminaEventPublisher;
import com.ibaset.solumina.integration.common.EventProcessor;
import com.ibaset.solumina.integration.common.SendSoluminaEventInOAGISListener;
import com.pw.solumina.T308.integration.ISendT308Listener;

public class SendT308ListenerImpl extends SendSoluminaEventInOAGISListener implements ISendT308Listener
{
	@Reference SoluminaEventPublisher publisher = null;
	@Reference T308EventProcessorImpl t308EventProcessorImpl = null;
	
	@Override
	public String getServiceName() 
	{
		return "PW_S_T308_ORDER_RELEASE";
	}

	@Override
	public EventProcessor getEventProcessor() 
	{
		return t308EventProcessorImpl;
	}

	public Class getSupportedEventType() 
	{
		return T308Event.class;
	}
	
    public void onApplicationEvent(SoluminaApplicationEvent event)
    {
        if (event instanceof T308Event)
        {
        	T308Event t308Event = (T308Event) event;

           sendMessageForEvent(t308Event);
        }
    }

    public void setPublisher(SoluminaEventPublisher publisher)
    {
        this.publisher = publisher;
    }	

}
