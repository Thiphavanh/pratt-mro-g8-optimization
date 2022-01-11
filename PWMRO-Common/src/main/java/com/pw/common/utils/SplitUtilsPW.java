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
*  File:    SplitUtilsPW.java
* 
*  Created: 2019-05-18
* 
*  Author:  XCS4331
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2019-06-05    Fred Ettefagh   Initial Release XXXXXXX for PW_SMRO_TR_319
* 2019-08-12    Fred Ettefagh   added new methods updateSerial, stopMultipleOperation and insertSerialPre
* 2019-08-29    Fred Ettefagh   added checks for input values for all methods 
* 2019-11-18    Fred Ettefagh   changed error check for order split
* 2019-11-20    Fred Ettefagh   PW_SMRO_TR_319, added code to update new spitOrder's ASGND_LOCATION_ID if its null 
* 2020-06-01    Fred Ettefagh   SMRO_TR_319, Defect 1611, added method deleteSerial
* 2020-07-14    John DeNinno	defect 1392 Add Sub Order Type
*/

package com.pw.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sfpl.application.IOrder;
import com.ibaset.solumina.sfwid.application.ISerial;
import com.ibaset.solumina.sfwid.application.ISerialItems;
import com.pw.common.dao.CommonDaoPW;

public class SplitUtilsPW {

	@Reference ISerial serial = null;
	@Reference IOrder order =null;
	@Reference CommonDaoPW commonDaoPW = null;
	@Reference CommonUtilsPW commonUtilsPW = null;
	@Reference IMessage message = null;
	@Reference ISerialItems serialItems = null;

	
	public void deleteSerial(String orderId,String serialId, String calledFrom){
		
		serialItems.deleteSerial(orderId, serialId, calledFrom);
	}
	
	@SuppressWarnings("rawtypes")
    public void updateSerial(HashMap argsHashMap) {

		String orderId =  (String) argsHashMap.get("parentOrderId");
        String oldSerialId = (String) argsHashMap.get("oldSerialId"); 
        String newSerialNumber = (String) argsHashMap.get("newSerialNumber");
    	
        if ( StringUtils.isBlank(orderId) ||
        	 StringUtils.isBlank(oldSerialId) ||
        	 StringUtils.isBlank(newSerialNumber)){
        		message.raiseError("MFI_20","orderId, oldSerialId, newSerialNumber cannot be null");
        }
        
    	serial.updateSerial(orderId, 
    			            oldSerialId, 
    			            newSerialNumber, 
    			            null,//uidLabel, 
    			            null,//constructType, 
    			            null,//validateEnterpriseId, 
    			            null,//validatePartNo, 
    			            null);//calledFrom);
    }
	
	
	@SuppressWarnings("rawtypes")
	public  void stopMultipleOperation(HashMap argsHashMap){
			
		String orderId = (String) argsHashMap.get("parentOrderId");
		String serialNumberList =(String)argsHashMap.get("serialNumberList");
		String lotNo = (String) argsHashMap.get("parentLotNo");
		
        if ( StringUtils.isBlank(orderId) ||
           	 StringUtils.isBlank(serialNumberList) ||
           	 StringUtils.isBlank(lotNo)){
           		message.raiseError("MFI_20","orderId, serialNumberList, lotNo cannot be null");
         }
		
		serial.stopMultipleOperation(orderId, 
				                     serialNumberList, 
				                     lotNo, 
				                     "called from pw repair order split", //notes, 
				                     null);//calledFrom);
		
	}

    @SuppressWarnings("rawtypes")
	public void insertSerialPre( HashMap argsHashMap){
    	
		String orderId = (String) argsHashMap.get("newOrderId");
		String serialString =(String)argsHashMap.get("serialNumberList");
		//String lotNo = (String) argsHashMap.get("newOrderLotNo");
		String partNumber = (String) argsHashMap.get("partNo");
		String partChange = (String) argsHashMap.get("partChg");
		
		
        if ( StringUtils.isBlank(orderId) ||
             StringUtils.isBlank(serialString) ||
             StringUtils.isBlank(partNumber) ||
             StringUtils.isBlank(partChange) ){
           		message.raiseError("MFI_20","orderId, serialString, partNumber, partChange  cannot be null");
         }
	
    	serial.insertSerialPre(orderId, 
    			               serialString, 
    			               null,//calledFrom, 
    			               null,//uidLabel, 
    			               null,//validateEnterpriseId, 
    			               null,//validatePartNo, 
    			               partNumber, 
    			               partChange);
    }
	
	
	@SuppressWarnings("rawtypes")
	public  void reassignSerialNumbers(HashMap argsHashMap){
			
		String parentOrderId = (String) argsHashMap.get("parentOrderId");
		String newOrderId = (String)argsHashMap.get("newOrderId");
		String serialNumberList =(String)argsHashMap.get("serialNumberList");
		String newLotNumber = (String) argsHashMap.get("newLotNumber");
		
        if ( StringUtils.isBlank(parentOrderId) ||
             StringUtils.isBlank(newOrderId) ||
             StringUtils.isBlank(serialNumberList) ||
             StringUtils.isBlank(newLotNumber) ){
             		message.raiseError("MFI_20","parentOrderId, newOrderId, serialNumberList, newLotNumber  cannot be null");
         }

        serial.reassignMultipleSerial(parentOrderId, 
						              newOrderId, 
						              serialNumberList, 
						              newLotNumber);
		
	}
	
	@SuppressWarnings("rawtypes")
	public String createNewRepairOrder(HashMap argsHashMap){
		
		String newOrderNo = (String)argsHashMap.get("newOrderNo"); 

		Number orderQty = (Number)argsHashMap.get("newOrderQty"); 
		String salesOrderNumber =(String) argsHashMap.get("parentSalesOrderNumber"); 
		String interfaceTablePK = (String)argsHashMap.get("parentInterfaceTablePK"); 
		String partNo= (String)argsHashMap.get("partNo"); 
		String partChg=(String) argsHashMap.get("partChg"); 

		String planId= (String)argsHashMap.get("planId"); 
		Number planVer=(Number) argsHashMap.get("planVer");  
		Number planRev=(Number) argsHashMap.get("planRev");  
		Number planAlt=(Number) argsHashMap.get("planAlt");
		String orderType = (String)argsHashMap.get("orderType"); 
		String effectivityType = (String)argsHashMap.get("effectivityType");
		String scheduledStartDate = (String)argsHashMap.get("scheduledStartDate"); 
		String userName = (String)argsHashMap.get("userName"); 
		String parentOrderWorkLoc =(String)argsHashMap.get("parentOrderWorkLoc"); 
		String calledFrom = (String)argsHashMap.get("calledFrom");
		
		if ( orderQty== null ){
			message.raiseError("MFI_20","orderQty cannot be null");
		}
		// interfaceTablePK is iDocNumber
		if (StringUtils.isBlank(interfaceTablePK)){
			//message.raiseError("MFI_20","interfaceTablePK cannot be null");
		}
		if (StringUtils.isBlank(partNo)){
			message.raiseError("MFI_20","partNo cannot be null");
		}
		if (StringUtils.isBlank(partChg)){
			message.raiseError("MFI_20","partChg cannot be null");
		}
		if (StringUtils.isBlank(planId)){
			message.raiseError("MFI_20","planId cannot be null");
		}
		if (planVer==null){
			message.raiseError("MFI_20","planVer cannot be null");
		}
		if (planRev==null){
			message.raiseError("MFI_20","planRev cannot be null");
		}
		if (planAlt==null){
			message.raiseError("MFI_20","planAlt cannot be null");
		}
		if (StringUtils.isBlank(effectivityType)){
			message.raiseError("MFI_20","effectivityType cannot be null");
		}
		if (StringUtils.isBlank(scheduledStartDate)){
			message.raiseError("MFI_20","scheduledStartDate cannot be null");
		}
		
		System.out.println("Repair order interfaceTablePK = " +interfaceTablePK);
		order.createOrderFromPlanPre(partNo, //itemNo, 
				                     partChg,//itemRev, 
				                     planId, 
				                     newOrderNo,//orderNumber, 
				                     orderQty,//orderQuantity, 
				                     scheduledStartDate, 
				                     null,//scheduledEndDate, 
				                     effectivityType, 
				                     null,//effectivityUnit, 
				                     null,//notes,
				                     null,//model, 
				                     orderType, 
				                     null,//customer, 
				                     null,//customerDescription, 
				                     null,//customerOrderNumber, 
				                     null,//contract, 
				                     parentOrderWorkLoc,//workLocation, 
				                     null,//orderReview, 
				                     null,//owpId, 
				                     userName,//ucfOrderVch1, 
				                     null,//ucfOrderVch2, 
				                     null,//ucfOrderVch3, 
				                     null,//ucfOrderVch4, 
				                     null,//ucfOrderVch5, 
				                     null,//ucfOrderVch6,
				                     null,//ucfOrderVch7,
				                     null,//ucfOrderVch8, 
				                     null,//ucfOrderVch9, 
				                     null,//ucfOrderVch10, 
				                     salesOrderNumber,//ucfOrderVch11,
				                     salesOrderNumber,//ucfOrderVch12, 
				                     null,//ucfOrderVch13, 
				                     null,//ucfOrderVch14,
				                     interfaceTablePK,//ucfOrderVch15, 
				                     null,//ucfOrderNum1, 
				                     null,//ucfOrderNum2, 
				                     null,//ucfOrderNumber3,
				                     null,//ucfOrderNumber4, 
				                     null,//ucfOrderNumber5, 
				                     null,//ucfOrderDate1, 
				                     null,//ucfOrderDate2, 
				                     null,//ucfOrderDate3, 
				                     null,//ucfOrderDate4, 
				                     null,//ucfOrderDate5, 
				                     null,//ucfOrderFlag1,
				                     null,//ucfOrderFlag2, 
				                     null,//ucfOrderFlag3, 
				                     null,//ucfOrderFlag4, 
				                     null,//ucfOrderFlag5, 
				                     null,//ucfOrderVch2551, 
				                     null,//ucfOrderVch2552, 
				                     null,//ucfOrderVch2553,
				                     null,//ucfOrderVch40001,
				                     null,//ucfOrderVch40002, 
				                     null,//replacementAction, 
				                     null,//asWorkedBomId, 
				                     null,//relatedOrderId,
				                     null,//discrepancyId, 
				                     null,//discrepancyLineNumber, 
				                     null,//serialNumbersPassed, 
				                     null,//lotNumberPassed, 
				                     null,//unitNumberPassed,
				                     null,//planVersionPassed,
				                     null,//planRevisionPassed,
				                     null,//planAlterationsPassed,
				                     null,//hiddenValues, 
				                     null,//startingItemNo,
				                     null,//startingItemRev, 
				                     null,//configName, 
				                     null,//changeRequestId,
				                     null,//plannedActionId, 
				                     null,//releasePackageId,
				                     planVer,//planVersionSelected, 
				                     planRev,//planRevisionSelected, 
				                     planAlt);//planAlterationsSelected);
		
		List list =commonDaoPW.selectsfwidOrderDescList(newOrderNo);
		Map map =(Map)list.get(0);
		String newOrderId = (String) map.get("ORDER_ID");
		String newOrderAsgndLocationId = (String) map.get("ASGND_LOCATION_ID");
		if (StringUtils.isBlank(newOrderAsgndLocationId)){
			
			Map sffndWorkLocMap = commonDaoPW.selectSffndWorkLocDec(parentOrderWorkLoc);
			if (sffndWorkLocMap!=null){
				String locationId = (String)sffndWorkLocMap.get("LOCATION_ID");
				commonDaoPW.updateSfwidOrderDescWorkLoc(newOrderId, locationId);				
			}
		}
		//defect 1392
		if (StringUtils.equals(calledFrom, "STD")) {
			commonDaoPW.updatePwSubOrderType(newOrderId,"STANDARD");
		}
		return newOrderId;

	}

}

