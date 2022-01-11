package com.pw.solumina.event;
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
*  File:    PWSDispositionEvent.java
* 
*  Created: 2020-01-24
* 
*  Author:  X005703
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	 ------------------------------------------------------------------
* 2020-01-24 	D.Sibrian		 Initial creation for update of T314 Interface messaging	    
*/

import com.ibaset.common.event.SoluminaApplicationEvent;
import com.pw.solumina.domain.PwmroiSerial;

public class PWSDispositionEvent extends SoluminaApplicationEvent {

	private static final long serialVersionUID = -9045864103004542582L;
	private PwmroiSerial pwmroiSerial;
	
	public PwmroiSerial getPwmroiSerial() {
		return pwmroiSerial;
	}

	public void setPwmroiSerial(PwmroiSerial pwmroiSerial) {
		this.pwmroiSerial = pwmroiSerial;
	}

	public PWSDispositionEvent(Object source) {
		super(source);
	}
	

}
