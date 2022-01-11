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
 *  File:    IT301DaoPW.java
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
package com.pw.solumina.interfaces.dao;

import java.util.List;

public interface IT301DaoPW {

	public List getT301ColumnLengths();

	public void insertT301Order(String idocNumber,			
			String messageType,
			String updtUserid,
			String orderType,
			String plant,
			String notification,
			String salesOrder,
			String salesDocitem,
			String superiorSmOrder,
			String smOrder,
			String customerNumber,
			String customerName,
			String purchaseOrder,
			String quoteRequired,
			String requiredDeliveryDate,
			String engineModel,
			String engineSerialNumber,
			String engineSection,
			String engineSectionDesc,
			String incomingMaterial,
			String outgoingMaterial,
			String quantity,
			String lotCode);

	public void insertT301Serials(String idocNumber,			
			String smOrder,
			String serialNumber);
	
	public boolean smOrderExists(String idocNumber);
	
}
