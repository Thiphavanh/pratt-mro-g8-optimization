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
*  File:    IPlanEffectivityPW.java
* 
*  Created: 2018-02-07
* 
*  Author:  c079222
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2018-02-07 		c079222		    Initial Release SRMO_PLG_209
*/
package com.pw.solumina.sfpl.application;

import java.util.Map;

public interface IPlanEffectivityPW {

	public void generateRoutingOutboundXml();
	
    public Map preparePlanKeyMap(String planId,Number planVersion,Number planRevision,Number planAlterations);

}
