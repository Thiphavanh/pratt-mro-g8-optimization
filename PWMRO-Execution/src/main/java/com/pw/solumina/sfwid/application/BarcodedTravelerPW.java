/*
 *  This unpublished work, first created in 2019 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2019.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    BardcodedTravelerPW.java
 * 
 *  Created: January 2019
 * 
 *  Author:  Raeann Thorpe
 * 
 *  Revision History
 * 
 *  Date        Who          	Description
 *  ----------	--------------	-----------------------------------------------------------
 *  01-28-2019  R. Thorpe       Initial Release SMRO_WOE_310
 *  11-07-2019  B. Polak        Barcode Traveler Updates
*/
package com.pw.solumina.sfwid.application;

import java.util.StringTokenizer;

import com.ibaset.common.Reference;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IEvent;

public class BarcodedTravelerPW
{
	@Reference private IEvent event = null;
	@Reference private IMessage message = null;
	
	@SuppressWarnings({ "rawtypes" })
	public void printBarcodedTraveler(String orderNoList, String atTag, String userid)
	{
		//System.out.println("-- Barcode Traveler Start ---");
		 
		//System.out.println("orderNoList: "+orderNoList);
		//System.out.println("atTag: "+atTag);
		//System.out.println("userid: "+userid);
		
		
		// atTag values:
		// 1 = Single Order Traveler
		// 2 = Multiple Order Traveler from Order Dispatch - Print Directly to Printer - do not display on screen

		String objectId   = "";
		String reportName = "";

		if ("1".equals(atTag))
		{
			objectId =  "PWUST_809B0889CCFC1CBEE05387971F0A6A9D";
		///	objectId =  "PWUST_92732816ADBD7632E05387971F0AA2BB"; /////// TESTING direct to printer---  DOES NOT WORK...
			reportName = "Barcoded Traveler";
		}
		else
		{
			message.raiseError("MFI_20", "Unknown report request - Contact IT.");
		}
	
		//check for multiple orders selected
		
		StringTokenizer tokenizer = new StringTokenizer(orderNoList,";");
		
		while(tokenizer.hasMoreTokens())
		{
			String reportParams = null;
			reportParams = "ORDER_NO_LIST=" + tokenizer.nextToken() + "," +
				       "@USERID="  + userid;
			event.launchReportInNewTab(objectId, reportName, reportParams);
		}
		
		//System.out.println("--- Barcode Traveler End --");
	}
}
