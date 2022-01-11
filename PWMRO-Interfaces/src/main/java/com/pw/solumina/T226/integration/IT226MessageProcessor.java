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
 *  File:    IT226MessageProcessor.java
 * 
 *  Created: 2018-02-05
 * 
 *  Author:  Catrina Nguyen
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2018-02-05		C. Nguyen		Initial Release SMRO_TR_T226 - Module Swaps Interface
 *  								
 */


package com.pw.solumina.T226.integration;

import com.ibaset.solumina.integration.logging.MessageLoggerParams;

public interface IT226MessageProcessor {

	public void processMessage(String msg, MessageLoggerParams loggerParams) ;

	public void doProcessing(String messageText,MessageLoggerParams messageLoggerParams) throws Throwable ;

}
