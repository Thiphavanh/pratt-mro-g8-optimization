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
*  File:    IPWMROISerial.java
* 
*  Created: 2019-12-10
* 
*  Author:  X005703
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2019-12-10 		X005703		    Initial Release IBaset delivery cockpit
* 2021-08-24        X006881         Defect 1862 & 1868
*/

package com.pw.solumina.sfwid.application;

import com.pw.solumina.domain.PwmroiSerial;

public interface IPWMROISerial
{
    public void initiateSerialDelivery(String orderId, String serialNos, String manualNos);
    
	public void initiateSerialScrapPre(String orderId, Number operationKey, 
			String serialList, String scrapType);

	public void initiateSerialScrap(String orderId, Number operationKey,
			String serialNumberList, String scrapType, String nonRepairType,
			String category1, String code1, String primary1,
			String category2, String code2, String primary2,
			String category3, String code3, String primary3,
			String category4, String code4, String primary4,
			String category5, String code5, String primary5,
			String category6, String code6, String primary6,
			String scrapText, String zcomSalesOrder);
	
	public PwmroiSerial selectPwmroiSerialForReply(String transactionId,String type);
	
	public void scrapUnitAndUpdateField(PwmroiSerial pwmroiSerial);
	
	public void initiateDelivery(String orderId, String serial);
	
	//Defect 1862 & 1868
		public void reverseDeliveredSerialNo(String serial_No_List);
}