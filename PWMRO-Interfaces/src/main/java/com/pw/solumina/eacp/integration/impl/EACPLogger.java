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
*  File:    EACPLogger.java
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

package com.pw.solumina.eacp.integration.impl;

/**
 * @author c293011
 *
 */

import java.io.Serializable;

import com.ibaset.solumina.domain.dao.DAO;
import com.pw.solumina.eacp.integration.IEACPLogger;

public class EACPLogger implements IEACPLogger {

	private DAO dao = null;
	
	public void updateObject(Serializable object)
	{
		dao.update(object);
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}
}
