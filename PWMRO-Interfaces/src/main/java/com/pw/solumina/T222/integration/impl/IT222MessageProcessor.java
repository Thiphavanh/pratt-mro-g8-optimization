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
 *  File:    IReceiveT222Listener.java
 * 
 *  Created: 2017-11-XX
 * 
 *  Author:  c293011
 * 
 *  Revision History
 * 
 *  Date      	Who                	Description
 *  ----------	---------------	---------------	---------------------------------------------------
 * 2017-11-XX 	c293011		    Initial Release SMRO_TR_T222 LO Notification
 */

package com.pw.solumina.T222.integration.impl;

import com.ibaset.solumina.integration.logging.MessageLoggerParams;
/**
 * @author c293011
 *
 */
public interface IT222MessageProcessor {

	public void processMessage(String msg, MessageLoggerParams loggerParams) ;

	public void doProcessing(String messageText,MessageLoggerParams messageLoggerParams) throws Throwable ;
}

