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
 *  File:    IM309MessageProcessor.java
 * 
 *  Created: 2019-08-23
 * 
 *  Author:  Brendan Polak 
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2019-08-23		B.Polak 		Initial Release SMRO_M309 - Tooling Serial Interface
 *  								
 */

package com.pw.solumina.M309.integration;

import com.ibaset.solumina.integration.logging.MessageLoggerParams;

public interface IM309MessageProcessor {

	public void processMessage(String msg, MessageLoggerParams loggerParams) ;

	public void doProcessing(String messageText,MessageLoggerParams messageLoggerParams) throws Throwable ;

}