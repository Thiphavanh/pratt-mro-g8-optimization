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
 *  File:    IT301MessageProcessor.java
 * 
 *  Created: 2019-05-24
 * 
 *  Author:  David Miron
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2019-05-24		D. Miron		Initial Release SMRO_TR_T301 - SM Order Interface
 *  								
 */
package com.pw.solumina.T301.integration;

import com.ibaset.solumina.integration.logging.MessageLoggerParams;

public interface IT301MessageProcessor {

	public void processMessage(String msg, MessageLoggerParams loggerParams) ;

	public void doProcessing(String messageText,MessageLoggerParams messageLoggerParams) throws Throwable ;

}
