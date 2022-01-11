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
*  File:    ReceiveT308ListenerImpl.java
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
import com.ibaset.solumina.integration.common.ReceiveOAGISReplyListener;
import com.ibaset.solumina.integration.common.ReplyProcessor;
import com.pw.solumina.T308.integration.impl.ReceiveT308ReplyProcessorImpl;
import com.pw.solumina.T308.integration.IReceiveT308Listener;

public class ReceiveT308ListenerImpl extends ReceiveOAGISReplyListener implements IReceiveT308Listener
{
	
	@Reference ReceiveT308ReplyProcessorImpl receiveT308ReplyProcessorImpl = null;
	
	@Override
	public String getServiceName() 
	{
		return "PW_S_T308_ORDER_RELEASE";
	}

	@Override
	public ReplyProcessor getReplyProcessor(String messageText) 
	{
		return receiveT308ReplyProcessorImpl;
	}

}
