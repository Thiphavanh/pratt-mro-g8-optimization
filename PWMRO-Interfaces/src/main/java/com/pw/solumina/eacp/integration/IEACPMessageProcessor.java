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
*  File:    IEACPMessageProcessor.java
* 
*  Created: 2017-10-19
* 
*  Author:  c293011
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-10-19 		c293011		    Initial Release XXXXXXX
*/


package com.pw.solumina.eacp.integration;

/**
 * @author c293011
 *
 */
import com.ibaset.solumina.integration.logging.MessageLoggerParams;

public interface IEACPMessageProcessor {

	public void processMessage(String msg, MessageLoggerParams loggerParams);

	public void doProcessing(String messageText,
			MessageLoggerParams messageLoggerParams) throws Throwable;

}
