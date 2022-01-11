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
 *  File:    IM316MessageProcessor.java
 * 
 *  Created: 2019-08-05
 * 
 *  Author:  B Corson
 * 
 *  Revision History
 * 
 *  Date          Who            Description
 *  -----------   ------------   -----------------------------------------------------------
 *  2019-08-05    B. Corson      Initial Release SMRO_MD_316 - Scrap Codes and Hold Types Groups Interface
 *  
 */

package com.pw.solumina.M316.integration;

import com.ibaset.solumina.integration.logging.MessageLoggerParams;

public interface IM316MessageProcessor {
    
    public void processMessage(String msg, MessageLoggerParams loggerParams);
    
    public void doProcessing(String messageText, MessageLoggerParams messageLoggerParams) throws Throwable;
}
