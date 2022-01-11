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
*  File:    SendT319ListenerImpl.java
* 
*  Created: 2019-06-05
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2019-06-04 	Fred Ettefagh       Initial Release for PW_SMRO_TR_319
*/
package com.pw.solumina.T319.integration.impl;

import com.ibaset.common.Reference;
import com.ibaset.common.event.SoluminaApplicationEvent;
import com.ibaset.common.event.SoluminaEventPublisher;
import com.ibaset.solumina.integration.common.EventProcessor;
import com.ibaset.solumina.integration.common.SendSoluminaEventInOAGISListener;

public class SendT319ListenerImpl extends SendSoluminaEventInOAGISListener implements SendT319Listener{

	
	@Reference SoluminaEventPublisher publisher = null;
	@Reference T319EventProcesserImpl t319EventProcesserImpl = null;
	
	@Override
	public String getServiceName() {

		return "PW_S_T319_ORDER_SPLIT";
	}

	@Override
	public EventProcessor getEventProcessor() {

		return t319EventProcesserImpl;
	}

	public Class getSupportedEventType() {

		return T319EventPW.class;
	}
	
    public void onApplicationEvent(SoluminaApplicationEvent event)
    {
        if (event instanceof T319EventPW)
        {
        	T319EventPW t319EventPW = (T319EventPW) event;

           sendMessageForEvent(t319EventPW);
        }
    }

    public void setPublisher(SoluminaEventPublisher publisher)
    {
        this.publisher = publisher;
    }	

}
