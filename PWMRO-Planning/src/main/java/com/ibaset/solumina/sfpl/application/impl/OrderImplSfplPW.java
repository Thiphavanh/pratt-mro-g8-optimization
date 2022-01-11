/*
 *  This unpublished work, first created in 2017 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2017.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    OrderImplPW.java
 * 
 *  Created: 2018-05-29
 * 
 *  Author:  Fred Ettefagh
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2019-05-03      Fred Ettefagh   when creating an order insert record in to  PWUST_SFWID_ORDER_DESC table
 *  2019-06-05      F. Ettefagh     changed code for Repair shop order split 
 *  2019-06-24      D.Miron         Defect 1327 - R2SP4 Upgrade Issue - Add additional arguments to createWorkOrder.
 *  2019-07_18      Fred Ettefagh   Defect 1327, rename file from OrderImplPW to OrderImplSfplPW to fix upgrade issues
 */
package com.ibaset.solumina.sfpl.application.impl;

import java.util.Date;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sfpl.application.IOrder;
import com.pw.common.dao.CommonDaoPW;
import com.pw.common.utils.CommonUtilsPW;

public abstract class OrderImplSfplPW  extends ImplementationOf<IOrder> implements IOrder {
	
	@Reference public CommonDaoPW commonDaoPW = null;
	@Reference	private IMessage message = null;
	@Reference	CommonUtilsPW commonUtilsPW = null;
	
    public void createWorkOrder(String itemNo,
								String itemRev,
								String planId,
								String orderNumber,
								Number orderQuantity,
								String scheduledStartDate,
								String scheduledEndDate,
								String effectivityType,
								String effectivityUnit,
								String notes,
								String model,
								String orderType,
								String customer,
								String customerDescription,
								String customerOrderNumber,
								String contract,
								String workLocation,
								String orderReview,
								String owpId,
								String ucfOrderVch1,
								String ucfOrderVch2,
								String ucfOrderVch3,
								String ucfOrderVch4,
								String ucfOrderVch5,
								String ucfOrderVch6,
								String ucfOrderVch7,
								String ucfOrderVch8,
								String ucfOrderVch9,
								String ucfOrderVch10,
								String ucfOrderVch11,
								String ucfOrderVch12,
								String ucfOrderVch13,
								String ucfOrderVch14,
								String ucfOrderVch15,
								Number ucfOrderNum1,
								Number ucfOrderNum2,
								Number ucfOrderNumber3,
								Number ucfOrderNumber4,
								Number ucfOrderNumber5,
								Date ucfOrderDate1,
								Date ucfOrderDate2,
								Date ucfOrderDate3,
								Date ucfOrderDate4,
								Date ucfOrderDate5,
								String ucfOrderFlag1,
								String ucfOrderFlag2,
								String ucfOrderFlag3,
								String ucfOrderFlag4,
								String ucfOrderFlag5,
								String ucfOrderVch2551,
								String ucfOrderVch2552,
								String ucfOrderVch2553,
								String ucfOrderVch40001,
								String ucfOrderVch40002,
								String replacementAction,
								String asWorkedBomId,
								String relatedOrderId,
								String selectedField,
								String groupOfSerialList,
								String orderNumberForGroupOfSerial,
								String calledFrom,
								String serialNumberForCompEffectivity,
								String lotNumberForCompEffectivity,
								String unitNumberForCompEffectivity,
								Number planVersionForCompEffectivity,
								Number planRevisionForCompEffectivity,
								Number planAlterationsForCompEffectivity,
								String hiddenValues,
								String parentOrderId,
								String startingItemNo, 
								String startingItemRev,
								String configName,
								String changeRequestId,
					            String plannedActionId, 
					            String releasePackageId,
					            String discrepancyId,
					            Number discrepancyLineNumber,
					            Number planVersionSelected,
					            Number planRevisionSelected,
					            Number planAlterationsSelected,
					            String planEndUnitType,             // Defect 1327 - Added args from here to end
								String endUnitNo,
								String schedulePriority,
								String planPlannedWorkLocation,
								String planPlannedLocationId,
								String planOperWorkLocation,
								String planOperWorkDepartment,
								String planOperWorkCenter,
								String serialNo, 
								String lotNo){
    	
    	Super.createWorkOrder(itemNo, 
    			              itemRev, 
    			              planId, 
    			              orderNumber, 
    			              orderQuantity, 
    			              scheduledStartDate, 
    			              scheduledEndDate, 
    			              effectivityType, 
    			              effectivityUnit,
    			              notes, 
    			              model, 
    			              orderType,
    			              customer,
    			              customerDescription,
    			              customerOrderNumber,
    			              contract,
    			              workLocation,
    			              orderReview, 
    			              owpId,
    			              ucfOrderVch1, 
    			              ucfOrderVch2,
    			              ucfOrderVch3,
    			              ucfOrderVch4, 
    			              ucfOrderVch5,
    			              ucfOrderVch6, 
    			              ucfOrderVch7,
    			              ucfOrderVch8,
    			              ucfOrderVch9, 
    			              ucfOrderVch10,
    			              ucfOrderVch11,
    			              ucfOrderVch12,
    			              ucfOrderVch13,
    			              ucfOrderVch14, 
    			              ucfOrderVch15,
    			              ucfOrderNum1, 
    			              ucfOrderNum2,
    			              ucfOrderNumber3, 
    			              ucfOrderNumber4, 
    			              ucfOrderNumber5,
    			              ucfOrderDate1,
    			              ucfOrderDate2, 
    			              ucfOrderDate3, 
    			              ucfOrderDate4, 
    			              ucfOrderDate5, 
    			              ucfOrderFlag1,
    			              ucfOrderFlag2,
    			              ucfOrderFlag3, 
    			              ucfOrderFlag4,
    			              ucfOrderFlag5, 
    			              ucfOrderVch2551,
    			              ucfOrderVch2552, 
    			              ucfOrderVch2553,
    			              ucfOrderVch40001, 
    			              ucfOrderVch40002, 
    			              replacementAction, 
    			              asWorkedBomId,
    			              relatedOrderId, 
    			              selectedField, 
    			              groupOfSerialList, 
    			              orderNumberForGroupOfSerial, 
    			              calledFrom,
    			              serialNumberForCompEffectivity,
    			              lotNumberForCompEffectivity, 
    			              unitNumberForCompEffectivity,
    			              planVersionForCompEffectivity,
    			              planRevisionForCompEffectivity,
    			              planAlterationsForCompEffectivity, 
    			              hiddenValues,
    			              parentOrderId,
    			              startingItemNo,
    			              startingItemRev,
    			              configName, 
    			              changeRequestId,
    			              plannedActionId,
    			              releasePackageId, 
    			              discrepancyId, 
    			              discrepancyLineNumber,
    			              planVersionSelected,
    			              planRevisionSelected,
    			              planAlterationsSelected,
    			              planEndUnitType,               // Defect 1327 - Added args from here to end
    			              endUnitNo,
    			              schedulePriority,
    			              planPlannedWorkLocation,
    			              planPlannedLocationId,
    			              planOperWorkLocation,
    			              planOperWorkDepartment,
    			              planOperWorkCenter,
    			              serialNo, 
    			              lotNo);
    	
    	commonUtilsPW.insertIntoPwustSfwidOrderDesc(orderNumber, 
    			                                    ucfOrderVch13, //pwOrderNo, 
    			                                    null,//newOrderId, 
    			                                    null,//superNetPW, 
    			                                    null,//subNetPW, 
    			                                    null,//workScopePW, 
    			                                    null,//customerPW, 
    			                                    ucfOrderVch11,//salesOrder, 
    			                                    ucfOrderVch14,//engineModel, 
    			                                    orderType);
    }
}
