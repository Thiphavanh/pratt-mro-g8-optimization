/*
 *  This unpublished work, first created in 2020 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2020.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    OptionalDCImplPW.java
 * 
 *  Created: 2020-09-04
 * 
 *  Author:  David Miron
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2020-09-04      D.Miron         Initial Release - SMRO_WOE_302 Defect 1760 - Always notify
 *                                  user when optional Data Collections is being skipped except 
 *                                  for N/A and N/D of buyoff.
 *                                  
*/
package com.pw.solumina.sfwid.application.impl;

import static com.ibaset.common.FrameworkConstants.YES;
import static com.ibaset.common.SoluminaConstants.IMPLICIT_SKIPPED;
import static com.ibaset.common.SoluminaConstants.NOTIFY_OF_OPTIONAL_DC_IMPLICITLY_SKIPPED;
import static com.ibaset.common.SoluminaConstants.OPER;
import static com.ibaset.common.util.SoluminaUtils.stringEquals;
import static org.apache.commons.lang.StringUtils.upperCase;

import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.solumina.sfwid.application.IOptionalDC;
import com.ibaset.solumina.sfwid.dao.ISerialDao;
import com.ibaset.solumina.sfcore.application.IMessage;

public abstract class OptionalDCImplPW extends ImplementationOf<IOptionalDC> implements IOptionalDC {

	@Reference
	private IMessage message;
	@Reference
	private ISerialDao serialDao;
	
	public void implicitSkipOptionalDataCollectionsAndNotify(String orderId, Number operationKey, String lotId, String serialId, String dataCollectionStatus) {
		
		if (stringEquals(dataCollectionStatus, upperCase(IMPLICIT_SKIPPED))){
		    // Collects the all the optional data collections Operation and Step level.
			optionalDataCollection(orderId, operationKey, NumberUtils.INTEGER_MINUS_ONE, serialId, lotId, OPER);
		    // Gets the Notification flag.
		    String notifyFlag = serialDao.selectParameterValue(upperCase(NOTIFY_OF_OPTIONAL_DC_IMPLICITLY_SKIPPED));
		    
		    // Defect 1760
		    // if (stringEquals(notifyFlag, YES)){
		        // Shows the message when DC are implicitly skipped and notification is 'Y'.
		        message.showMessage("MFI_14AB23A67B7E6369E0440003BA560E35");
		    // }
		}
	}
}
