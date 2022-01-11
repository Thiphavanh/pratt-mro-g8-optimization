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
 *  File:    DeliveryPackagePW.java
 * 
 *  Created: 2018-02-19
 * 
 *  Author:  N.Gnassounou
 * 
 *  Revision History
 * 
 *  Date        Who          	Description
 *  ----------	--------------	-----------------------------------------------------------
 *  2018-02-19	N.Gnassounou    Initial Release SMRO_RPT_201
 *  2018-04-26	J.Petraitis		Rewrite for SMRO_RPT_201
 *  2018-05-18	J.Petraitis		Defect 783 - Use Sales Order from Order Extension table instead of Outgoing Sales Order.
 *  2018-06-08  D.Miron         SMRO_RPT_201 Defect 650  - Added USERID argument to launchDeliveryReport
 *  2018-06-25  R.Thorpe        SMRO_RPT_201 Defect 864  - changed launchDeliveryReport to call getLookupValList
 *  2018-07-20  R.Thorpe        SMRO_RPT_201 Defect 849  - changed commonDaoPW.getLookupValList to commonDaoPW.selectDataValueFromGeneralLookup which
 *                                                         returns a string instead of a List (unnecessary)
 *  2018-07-22  R.Thorpe        SMRO_RPT_201 Defect 849  - rebuilt RAVE report - new object Id    
 *  2018-12-18  R.Thorpe        SMRO_RPT_201 Defect 1018 - External Delivery Package RAVE report - new object Id  
 *  2021-01-19  B.Polak         SMRO_RPT_201 Defect 1832 - Added Tag and Sales Order to call to delivery package                                                     
 */
package com.pw.solumina.sfwid.application;

import com.ibaset.common.Reference;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.application.IWrapUtils;
import com.pw.common.dao.CommonDaoPW;
import com.pw.solumina.sfwid.dao.BuyoffDaoPW;
import com.pw.solumina.sfwid.dao.WorkOrderDaoPW;

import com.ibaset.common.sql.Event;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DeliveryPackagePW
{
	@Reference private IWrapUtils wrapUtils;
	@Reference private IEvent event = null;
	@Reference private IMessage message = null;
	@Reference private WorkOrderDaoPW workOrderDaoPW;
	@Reference private CommonDaoPW commonDaoPW;
	@Reference private BuyoffDaoPW buyoffDaoPW;

	public void displayWorkOrderTab(String salesOrder) 																						// Defect 783 - Change from outgoingSalesOrderNo to salesOrder.
	{
		//String command = "Extinvoke(DefinableData.DeliveryPackage(UCF_ORDER_VCH12=" + wrapUtils.wrapValue(outgoingSalesOrderNo) + "))";	// Defect 783
		String command = "Extinvoke(DefinableData.DeliveryPackage(SALES_ORDER=" + wrapUtils.wrapValue(salesOrder) + "))";					// Defect 783

		Event e = new Event(new Date(), "EVENT7", command);
		message.addUserEvent(e);           
	}

	@SuppressWarnings({ "rawtypes" })
	public void launchDeliveryReport(String orderNoList,
									 String salesOrder,																						// Defect 783 - Change from outgoingSalesOrderNo to salesOrder.
									 String atTag,
									 String userid)     // Defect 650
	{
		String objectId   = "";
		String reportName = "";
		String coverSheet = "";
		
		// atTag values:
		// 1 = Internal Delivery Report
		// 2 = External Delivery Report
		// 3 = Internal Delivery Package
		// 4 = External Delivery Package
		
		if ("1".equals(atTag))
		{
			objectId = "PWUST_7187B0D77A9778AEE053EF9F1F0AFBA5";  //Defect 849
			reportName = "Delivery Report - Internal";
		}
		else
		if ("2".equals(atTag))
		{
			objectId   = "PWUST_7CC495680A8CFAB9E05387971F0AEC5C";// Defect 1018
			reportName = "Delivery Report - External";
		}
		else
		{
			// Since this is a full-package report, we need to get all of the Work Orders for the Sales Order.
			List orderList = workOrderDaoPW.selectOrderNoByOutgoingSalesOrder(salesOrder);													// Defect 783 - Change from outgoingSalesOrderNo to salesOrder.
			Iterator orderIter = orderList.listIterator();
			
			Map orderMap;
			String orderNo;
			
			orderNoList = "";
			
			while (orderIter.hasNext())
			{
				orderMap = (Map) orderIter.next();
				
				orderNo = (String) orderMap.get("ORDER_NO");
				
				if (orderNoList.isEmpty())
				{
					orderNoList = orderNo;
				}
				else
				{
					orderNoList = orderNoList + ";" + orderNo;
				}
			}
			
			if ("3".equals(atTag))
			{
				objectId = "PWUST_7187B0D77A9778AEE053EF9F1F0AFBA5";   //Defect 849
				reportName = "Delivery Package - Internal";
			}
			else
			if ("4".equals(atTag))
			{
				objectId   = "PWUST_7CC495680A8CFAB9E05387971F0AEC5C";// Defect 1018
				reportName = "Delivery Package - External";
			}
			else
			{
				message.raiseError("MFI_20", "Unknown report request - Contact IT.");
			}
		}
		
		if ("1".equals(atTag) || "2".equals(atTag))
		{
			if (orderNoList.contains(";"))
			{
				coverSheet = "2";
			}
			else
			{
				coverSheet = "1";
			}
		}
		else
		{
			coverSheet = "3";
		}
		
		String reportParams	= null;
		String idenId 		= commonDaoPW.selectDataValueFromGeneralLookup("DELIVERY_REPORT_IDEN_ID"); 
		
		reportParams = "ORDER_NO_LIST=" + orderNoList + "," +
					   "IDEN_ID="       + idenId	  + "," +		
					   "COVER_SHEET="   + coverSheet  + "," +
					   "@USERID="       + userid      + "," +
					   "TAG="			+ atTag		  + "," +
					   "SALES_ORDER="	+ salesOrder;                  // Defect 650

		//System.out.println(reportParams);
		
		event.launchReportInNewTab(objectId, reportName, reportParams);
	}
}
