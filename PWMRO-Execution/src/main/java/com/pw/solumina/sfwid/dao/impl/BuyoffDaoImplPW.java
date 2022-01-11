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
*  File:    BuyoffDaoImplPW.java
* 
*  Created: 2018-05-04
* 
*  Author:  c079222
* 
*  Revision History
* 
*  Date      	Who             Description
*  ----------	---------------	-------------------------------------------------------------------
*  2018-05-04   D.Miron         SMRO_WOE_202 Defect 750 - Initial Release    
*  2018-05-11   D.Miron         Defect 694: Changed ucfSerialOperationBuyoffVch1 to ucfSerialOperationBuyoffVch2  
*  2018-07-24   D.Miron         Defect 884: Added code for Badge Swipe logging
*  2018-11-21   R.Thorpe	    Defect 947: Added selectOrderAndSerialLot OOB was using ORDER_NO, changed to use PW_ORDER_NO for REOPEN
*  2018-12-06   D.Miron	        Defect 947: Removed selectOrderAndSerialLot.  Buyoffs could not be Accepted.
*  2020-06-05   Fred Ettefagh   Defect 1582: Added methods getPartialBuyoffInformation, getPartialBuyoffInformationPW, to call custom DB function if a repair work order split has been done on the order
*  2020-06-23   Fred Ettefagh   Defect 1703: Moved  method getPWOrderType and logic to find split order list into CommonDaoPW.isRepairWorkOrderSplit class
*/

package com.pw.solumina.sfwid.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.solumina.sfwid.dao.IBuyoffDao;
import com.pw.common.dao.CommonDaoPW;
import com.pw.solumina.sfwid.dao.BuyoffDaoPW;

public abstract class BuyoffDaoImplPW extends ImplementationOf<IBuyoffDao> implements IBuyoffDao {

	@Reference private BuyoffDaoPW buyoffDaoPW = null;
	@Reference private ExtensionDaoSupport eds;
	@Reference private CommonDaoPW commonDaoPW = null;

	public Map getPartialBuyoffInformation( String orderId, 
								            Number operationKey, 
								            Number stepKey, 
								            String buyoffId, 
								            Number qtyForPartialBuyoffComplete, 
								            Number qtyForPartialBuyoffComment, 
								            boolean retriveBuyoffStatus, 
								            String userName, 
								            String serialNumber, 
								            String calledFrom){

		 // Defect 1582
		 // 2020-06-04 Fred Ettefagh
		 // changed oob method to call custom method getPartialBuyoffInformationPW if a repair split has been done on order
		
		Map returnMapValue = null;
		
		boolean isRepairOrderlist = commonDaoPW.isRepairWorkOrderSplit(orderId); 
		if (isRepairOrderlist){
				returnMapValue = getPartialBuyoffInformationPW(orderId, operationKey, stepKey, buyoffId, qtyForPartialBuyoffComplete, qtyForPartialBuyoffComment, retriveBuyoffStatus, userName, serialNumber, calledFrom);
		}else{
				returnMapValue = Super.getPartialBuyoffInformation(orderId, operationKey, stepKey, buyoffId, qtyForPartialBuyoffComplete, qtyForPartialBuyoffComment, retriveBuyoffStatus, userName, serialNumber, calledFrom);			
		}
		
		return returnMapValue;
	}
	
	
	 public Map getPartialBuyoffInformationPW(String orderId, 
								             Number operationKey, 
								             Number stepKey, 
								             String buyoffId, 
								             Number qtyForPartialBuyoffComplete, 
								             Number qtyForPartialBuyoffComment, 
								             boolean retriveBuyoffStatus, 
								             String userName, 
								             String serialNumber, 
								             String calledFrom){
		 // Defect 1582
		 // 2020-06-04 Fred Ettefagh
		 // only change in this oob method is to call custom DB function instead of oob function 
		 // oob DB function: SFWID_BUYOFF_PERCENT_GET
		 // custom DB function: PWUST_SFWID_BUYOFF_PERCENT_GET
		 
		 StringBuilder selectSql = new StringBuilder().append("SELECT ");

		 if(retriveBuyoffStatus){
			 selectSql.append(eds.getSchemaPrefix() + "SFWID_BUYOFF_STATUS(?, ?, ?, ?, ?, ?, ?, ? ) AS BUYOFF_STATUS, ");
		 }
                       
		 selectSql.append(eds.getSchemaPrefix() + "PWUST_SFWID_BUYOFF_PERCENT_GET(?, ?, ?, ?, ? ) AS PERCENT_QTY_COMPLETE, ")
		                .append(eds.getSchemaPrefix() + "SFWID_BUYOFF_COMMENTS_GET(?, ?, ?, ?, ? ) AS COMMENTS ")
		                .append(eds.getDualTable());

		 ParameterHolder params = new ParameterHolder();
		 if(retriveBuyoffStatus){
			 params.addParameter(orderId);
			 params.addParameter(operationKey);
			 params.addParameter(stepKey);
			 params.addParameter(buyoffId);
			 params.addParameter(userName);
			 params.addParameter(serialNumber);
			 params.addParameter(calledFrom);
			 params.addParameter((String)null);
		 }

		 params.addParameter(orderId);
		 params.addParameter(operationKey);
		 params.addParameter(stepKey);
		 params.addParameter(buyoffId);
		 params.addParameter(qtyForPartialBuyoffComplete);
		 params.addParameter(orderId);
		 params.addParameter(operationKey);
		 params.addParameter(stepKey);
		 params.addParameter(buyoffId);
		 params.addParameter(qtyForPartialBuyoffComment);
		 return eds.queryForMap(selectSql.toString(), params);
	 }

	public int updateSerialOperationBuyoff(String buyoffStatus,
			                               String comment,
			                               String userName,
			                               String lastAction,
			                               Number operationIteration,
			                               Number operationExecutionCount,
			                               String ucfSerialOperationBuyoffVch1,
			                               String ucfSerialOperationBuyoffVch2,
			                               String ucfSerialOperationBuyoffVch3,
			                               String ucfSerialOperationBuyoffVch4,
			                               String ucfSerialOperationBuyoffVch5,
			                               String ucfSerialOperationBuyoffVch6,
			                               String ucfSerialOperationBuyoffVch7,
			                               String ucfSerialOperationBuyoffVch8,
			                               String ucfSerialOperationBuyoffVch9,
			                               String ucfSerialOperationBuyoffVch10,
			                               String ucfSerialOperationBuyoffVch11,
			                               String ucfSerialOperationBuyoffVch12,
			                               String ucfSerialOperationBuyoffVch13,
			                               String ucfSerialOperationBuyoffVch14,
			                               String ucfSerialOperationBuyoffVch15,
			                               Number ucfSerialOperationBuyoffNum1,
			                               Number ucfSerialOperationBuyoffNum2,
			                               Number ucfSerialOperationBuyoffNum3,
			                               Number ucfSerialOperationBuyoffNum4,
			                               Number ucfSerialOperationBuyoffNum5,
			                               String ucfSerialOperationBuyoffFlag1,
			                               String ucfSerialOperationBuyoffFlag2,
			                               String ucfSerialOperationBuyoffFlag3,
			                               String ucfSerialOperationBuyoffFlag4,
			                               String ucfSerialOperationBuyoffFlag5,
			                               Date ucfSerialOperationBuyoffDate1,
			                               Date ucfSerialOperationBuyoffDate2,
			                               Date ucfSerialOperationBuyoffDate3,
			                               Date ucfSerialOperationBuyoffDate4,
			                               Date ucfSerialOperationBuyoffDate5,
			                               Number partialCompleteValue,
			                               String partialComplete,
			                               String orderId,
			                               Number operationKey,
			                               Number stepKey,
			                               String lotId,
			                               String serialId,
			                               String buyoffId,
			                               String ucfSerialOperationBuyoffVch2551,
			                               String ucfSerialOperationBuyoffVch2552,
			                               String ucfSerialOperationBuyoffVch2553,
			                               String ucfSerialOperationBuyoffVch40001,
			                               String ucfSerialOperationBuyoffVch40002) {


		// Update userid for Badge Swipe or typed id
		// Defect 694
		if(ucfSerialOperationBuyoffVch2 != null)         
		{
			userName = ucfSerialOperationBuyoffVch2;
		}
			
		Integer retInt = Super.updateSerialOperationBuyoff(buyoffStatus,
				                                           comment,
				                                           userName,
				                                           lastAction,
				                                           operationIteration,
				                                           operationExecutionCount,
				                                           ucfSerialOperationBuyoffVch1,
				                                           ucfSerialOperationBuyoffVch2,
				                                           ucfSerialOperationBuyoffVch3,
				                                           ucfSerialOperationBuyoffVch4,
				                                           ucfSerialOperationBuyoffVch5,
				                                           ucfSerialOperationBuyoffVch6,
				                                           ucfSerialOperationBuyoffVch7,
				                                           ucfSerialOperationBuyoffVch8,
				                                           ucfSerialOperationBuyoffVch9,
				                                           ucfSerialOperationBuyoffVch10,
				                                           ucfSerialOperationBuyoffVch11,
				                                           ucfSerialOperationBuyoffVch12,
				                                           ucfSerialOperationBuyoffVch13,
				                                           ucfSerialOperationBuyoffVch14,
				                                           ucfSerialOperationBuyoffVch15,
				                                           ucfSerialOperationBuyoffNum1,
				                                           ucfSerialOperationBuyoffNum2,
				                                           ucfSerialOperationBuyoffNum3,
				                                           ucfSerialOperationBuyoffNum4,
				                                           ucfSerialOperationBuyoffNum5,
				                                           ucfSerialOperationBuyoffFlag1,
				                                           ucfSerialOperationBuyoffFlag2,
				                                           ucfSerialOperationBuyoffFlag3,
				                                           ucfSerialOperationBuyoffFlag4,
				                                           ucfSerialOperationBuyoffFlag5,
				                                           ucfSerialOperationBuyoffDate1,
				                                           ucfSerialOperationBuyoffDate2,
				                                           ucfSerialOperationBuyoffDate3,
				                                           ucfSerialOperationBuyoffDate4,
				                                           ucfSerialOperationBuyoffDate5,
				                                           partialCompleteValue,
				                                           partialComplete,
				                                           orderId,
				                                           operationKey,
				                                           stepKey,
				                                           lotId,
				                                           serialId,
				                                           buyoffId,
				                                           ucfSerialOperationBuyoffVch2551,
				                                           ucfSerialOperationBuyoffVch2552,
				                                           ucfSerialOperationBuyoffVch2553,
				                                           ucfSerialOperationBuyoffVch40001,
				                                           ucfSerialOperationBuyoffVch40002);

		 // Defect 884
		if(ucfSerialOperationBuyoffVch3 != null)         
		{
			if(!"0".equals(ucfSerialOperationBuyoffVch3))
			{
				Map map = this.selectSerialOperationBuyoffInformation(orderId, 
						                                              operationKey, 
						                                              stepKey, 
						                                              lotId, 
						                                              serialId, 
						                                              buyoffId);
				String signoffUserid = (String) map.get("UPDT_USERID");
				buyoffDaoPW.updateBadgeSwipeLog(ucfSerialOperationBuyoffVch3, signoffUserid);
			}
		}

		return retInt;
	}
}
