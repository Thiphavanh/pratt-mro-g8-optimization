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
 *  File:    ROMHistoryReportPW.java
 * 
 *  Created: 
 * 
 *  Author:  Raeann Thorpe
 * 
 *  Revision History
 * 
 *  Date        Who          	Description
 *  ----------	--------------	-----------------------------------------------------------
 *  08-08-2019  R. Thorpe       Initial Release SMRO_WOE_306
 *  03-23-2020  B.Polak         Defect 1487: Added Order No as a parameter to ROM report calls so that it can identify orders more accurately
 *  05-04-2020  Fred Ettefagh   SMRO_WOE_306, Defect 1487, Changed methods launchROMReportFromInputMatrix, launchROMReportHistory. added method validateData
*/
package com.pw.solumina.sfwid.application;

import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.sql.Event;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.application.IWrapUtils;
import com.pw.solumina.sfwid.dao.ROMHistoryReportDaoPW;

public class ROMHistoryReportPW {
	
	@Reference private IWrapUtils wrapUtils;
	@Reference private IEvent event = null;
	@Reference private IMessage message = null;
	@Reference private ROMHistoryReportDaoPW romHistoryReportDaoPW;
	
	//1487 added order no as param
	public void launchROMReportFromInputMatrix(String serialNo, 
											   String salesOrder,
											   String userid){
	
		System.out.println("in launchROMReportFromInputMatrix serialNo = " +serialNo);
		System.out.println("in launchROMReportFromInputMatrix salesOrder = " +salesOrder);
		System.out.println("in launchROMReportFromInputMatrix userid = " +userid);
		
		String objectId   = "";
		String reportName = "";
		String orderId    = "";
		String planId     = "";
		String partNo     = "";
		String serialId   = "";
		Number planRev    = 0;
		
		if (serialNo != null && salesOrder != null){

			StringTokenizer stringTokenizer = new StringTokenizer(salesOrder,",");
			salesOrder = stringTokenizer.nextToken();
			String orderNo = stringTokenizer.nextToken();

			
			Map infoMap = romHistoryReportDaoPW.selectOrderInfo(serialNo, salesOrder,orderNo);

			orderId  = (String) infoMap.get("ORDER_ID");
			planId   = (String) infoMap.get("PLAN_ID");
			planRev  = (Number) infoMap.get("PLAN_REVISION");
			partNo   = (String) infoMap.get("PART_NO");
			serialId = (String) infoMap.get("SERIAL_ID");

			objectId   = "PWUST_8477461F5FF9F273E05387971F0A4EB8";
			reportName = "ROM Report";

			String reportParams	= null;

			reportParams = "SALES_ORDER=" + (salesOrder) + "," +
					       "SERIAL_NO="   + (serialNo)   + "," + 
					       "SERIAL_ID="   + (serialId)   + "," + 
					       "ORDER_ID="    + (orderId)    + "," +
					       "ORDER_NO="    + (orderNo)    + "," +
					       "PLAN_ID="     + (planId)     + "," +
					       "PLAN_REV="    + (planRev)    + "," +
					       "PART_NO="     + (partNo)     + "," +
					       "@USERID="     + (userid);   
			
			event.launchReportInNewTab(objectId, reportName, reportParams);
		}
	}
	  
	//1487 adding orderNo to identify order more accurately
	public void launchROMReportHistory(String serialNo, 
									   String orderNo,
									   String salesOrder,	
			                           String romType,
			                           String inputMatrixFlag,
			                           String userid){
		
		System.out.println("in launchROMReportHistory serialNo = " +serialNo);
		System.out.println("in launchROMReportHistory orderNo = " +orderNo);
		System.out.println("in launchROMReportHistory salesOrder = " +salesOrder);
		System.out.println("in launchROMReportHistory romType = " +romType);
		System.out.println("in launchROMReportHistory inputMatrixFlag = " +inputMatrixFlag);
		System.out.println("in launchROMReportHistory userid = " +userid);
		
		String objectId   = "";
		String reportName = "";
		String orderId    = "";
		String planId     = "";
		String partNo     = "";
		String serialId   = "";
		Number planRev    = 0;
		String orderStatus = null;
		
		String udvId      = null;
        String UDVcaption = null;
        String hideColumns= null;
        String sqlTrans   = null;
        
        validateData(serialNo, orderNo, salesOrder, romType, inputMatrixFlag);
        
        StringBuilder stringBuilder = new StringBuilder();
        
        if ("Report".equalsIgnoreCase(romType) && "Y".equalsIgnoreCase(inputMatrixFlag)){
			
			udvId = "PWUST_936262125DF81CB6E05387971F0A2DFD";
    		UDVcaption = "Create ROM Report";    
    		sqlTrans = 	SoluminaConstants.INSERT;
			
    		stringBuilder.append("SALES_ORDER= '' "  );
    		stringBuilder.append(",SERIAL_NO= '' " );
    		stringBuilder.append(",ORDER_NO='' ");
    		stringBuilder.append(",@USERID=" + wrapUtils.wrapValue(userid));           
    		stringBuilder.append(",@SqlSourceName=@Auxparams");
    		
   	    	event.showUdv(udvId,
       		              sqlTrans,
       		              UDVcaption, 
       		              null,// hideColumns
       		              stringBuilder.toString());
   	    	
		}else{

        	Map infoMap = romHistoryReportDaoPW.selectOrderInfo(serialNo, salesOrder, orderNo);
        	
			orderId  = (String) infoMap.get("ORDER_ID");
			planId   = (String) infoMap.get("PLAN_ID");
			planRev  = (Number) infoMap.get("PLAN_REVISION");
			partNo   = (String) infoMap.get("PART_NO");
			serialId = (String) infoMap.get("SERIAL_ID");
			orderStatus =(String) infoMap.get("ORDER_STATUS"); 
	
			if ("History".equalsIgnoreCase(romType)){
		    	
		 		String histParams = "SALES_ORDER=" + wrapUtils.wrapValue(salesOrder)  + "," +
		 				            "SERIAL_NO=" + wrapUtils.wrapValue(serialNo) + "," + 
		 				            "SERIAL_ID=" + wrapUtils.wrapValue(serialId) + "," + 
		 				            "ORDER_ID=" + wrapUtils.wrapValue(orderId) + "," +
		 				            "ORDER_NO=" + wrapUtils.wrapValue(orderNo) + "," +
		 				            "PLAN_ID=" + wrapUtils.wrapValue(planId) + "," +
		 				            "PLAN_REV=" + wrapUtils.wrapValue(planRev) + "," +
		 				            "PART_NO=" + wrapUtils.wrapValue(partNo);

				String command = "Extinvoke(DefinableData.ROMHistory(SALES_ORDER=" + wrapUtils.wrapValue(salesOrder) + "," + "SERIAL_NO=" + wrapUtils.wrapValue(serialNo) + ","+ "SERIAL_ID=" + wrapUtils.wrapValue(serialId) + "," + "ORDER_ID=" + wrapUtils.wrapValue(orderId) + "," + "ORDER_NO=" + wrapUtils.wrapValue(orderNo) + "," + "PLAN_ID=" + wrapUtils.wrapValue(planId) + "," + "PLAN_REV=" + wrapUtils.wrapValue(planRev) + "," + "PART_NO=" + wrapUtils.wrapValue(partNo) +"))";					
				Event e = new Event(new Date(), "EVENT7", command);
				message.addUserEvent(e);
				
			}else if ("Report".equalsIgnoreCase(romType) && "N".equalsIgnoreCase(inputMatrixFlag)){
				
				///Check to see if Order is CLOSE for SN.  This is in case they select a SN while rom_type is History
				/// then change to Report
				//String orderStatus = romHistoryReportDaoPW.getOrderStatusFromSN(serialNo, orderNo);
				if (!"CLOSE".equalsIgnoreCase(orderStatus)){
					message.raiseError("MFI_20", "Work order needs to be completed prior to viewing ROM report");
				}

				// Call the RAVE report here	
				objectId   = "PWUST_8477461F5FF9F273E05387971F0A4EB8";
				reportName = "ROM Report";
				String reportParams	= null;
				reportParams = "SALES_ORDER=" + (salesOrder) + "," +
				               "SERIAL_NO="   + (serialNo)   + "," + 
				               "SERIAL_ID="   + (serialId)   + "," + 
				               "ORDER_ID="    + (orderId)    + "," +
				               "ORDER_NO="    + (orderNo)    + "," +
				               "PLAN_ID="     + (planId)     + "," +
				               "PLAN_REV="    + (planRev)    + "," +
				               "PART_NO="     + (partNo)     + "," +
						       "@USERID="     + (userid);                 

				event.launchReportInNewTab(objectId, reportName, reportParams);
				
			}
		}
	}

	protected void validateData(String serialNo, String orderNo, String salesOrder, String romType, String inputMatrixFlag) {
		
		if (StringUtils.isBlank(romType)){
        	message.raiseError("MFI_20", "RomType must be selected");
        }
        
        if (StringUtils.equalsIgnoreCase(inputMatrixFlag, "N")){
        	
            if (StringUtils.isBlank(serialNo)){
            	message.raiseError("MFI_20", "serial No must be selected");
            }
            if (StringUtils.isBlank(salesOrder)){
            	message.raiseError("MFI_20", "Sales Order must be selected");
            }
            if (StringUtils.isBlank(orderNo)){
            	message.raiseError("MFI_20", "Order No cannot be null");
            }
        }
        
        if  ("History".equalsIgnoreCase(romType) && "Y".equalsIgnoreCase(inputMatrixFlag)){
	    	message.raiseError("MFI_20", "Input Matrix is available for 'Report' Only.");	
	    }
	}
}