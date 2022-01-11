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
 *  File:    CallImplPW.java
 * 
 *  Created: 2018-06-05
 * 
 *  Author:  Sam Niu
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2018-06-05      S.Niu   		defect 468, Create Sub Orders Priv and Supplemental Orders Priv
 *  2018-07-06		Bob Preston		SMRO_WOE_211 Defect 779, Update new alt count.
 *  2018-07-19		Bob Preston		SMRO_WOE_211 Defect 779, Use UCF_ALT_VCH1 as new alt count.
 *  2018-07-23		Bob Preston		SMRO_WOE_211 Defect 779, Check for null UCF_ALT_VCH1.
 *  2018-12-17      Fred Eettefagh  Defect 1044, fix repair order create
 *  2019-06-04      Fred Ettefagh   added code to stop user from starting alt if repair order and pending status and is a multi order create
 *  2020-02-13      Fred Ettefagh   SMRO_TR_308, Defect 1474, changed code to send t308 message after certain changes for WO Alt and task include exclude
 *  2020-02-26      Fred Ettefagh   SMRO_TR_308, Defect 1474, changed code to send t308 message after certain changes for WO Alt and task include exclude     
 */

package com.pw.solumina.sfwid.application.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.solumina.IValidator;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sfcore.application.IUser;
import com.ibaset.solumina.sfwid.application.ICall;
import com.pw.common.dao.CommonDaoPW;
import com.pw.common.utils.CommonUtilsPW;
import com.pw.solumina.sfwid.dao.WorkOrderDaoPW;
import com.pw.solumina.sfwid.dao.impl.RepairWorkOrderT308Dao;

public abstract class CallImplPW extends ImplementationOf<ICall> implements ICall
{
	@Reference 	private WorkOrderDaoPW workOrderDaoPW;
	@Reference	private IMessage message = null;
	@Reference	private IValidator validator = null;
	@Reference	private RepairWorkOrderT308Dao repairWorkOrderT308Dao;
	@Reference	private IUser user = null;
	@Reference	CommonUtilsPW commonUtilsPW;
	@Reference  CommonDaoPW commonDaoPW;
	
    public void preStartAlteration(String orderId,
									String alterationReason,
									String changeAuthorizationType,
									String alterationNo,
									String calledFrom,
									String ucfOrderAlterationVch1,
									String ucfOrderAlterationVch2,
									String ucfOrderAlterationVch3,
									String ucfOrderAlterationVch4,
									String ucfOrderAlterationVch5,
									String ucfOrderAlterationVch6,
									String ucfOrderAlterationVch7,
									String ucfOrderAlterationVch8,
									String ucfOrderAlterationVch9,
									String ucfOrderAlterationVch10,
									String ucfOrderAlterationVch11,
									String ucfOrderAlterationVch12,
									String ucfOrderAlterationVch13,
									String ucfOrderAlterationVch14,
									String ucfOrderAlterationVch15,
									Number ucfOrderAlterationNum1,
									Number ucfOrderAlterationNum2,
									Number ucfOrderAlterationNum3,
									Number ucfOrderAlterationNum4,
									Number ucfOrderAlterationNum5,
									String ucfOrderAlterationFlag1,
									String ucfOrderAlterationFlag2,
									String ucfOrderAlterationFlag3,
									String ucfOrderAlterationFlag4,
									String ucfOrderAlterationFlag5,
									Date ucfOrderAlterationDate1,
									Date ucfOrderAlterationDate2,
									Date ucfOrderAlterationDate3,
									Date ucfOrderAlterationDate4,
									Date ucfOrderAlterationDate5,
									String alterationType,
									Number operationKey,
									String discrepancyId,
									Number discrepancyLineNumber,
									String objectId,
									String workFlow,
									String ucfOrderAlterationVch2551,
									String ucfOrderAlterationVch2552,
									String ucfOrderAlterationVch2553,
									String ucfOrderAlterationVch40001,
									String ucfOrderAlterationVch40002,
									String releasePackageId, //FND-24168 
									String associateChange) // FND-25795
{
		Map pwustSfwidOrderDescMap = workOrderDaoPW.selectPwustSfwidOrderDesc(orderId);
    	if (null==pwustSfwidOrderDescMap){
    		message.raiseError("MFI_20","Bad Order. Missing data in PWUST_SFWID_ORDER_DESC table");
    	}
    	
   		String pwOrderType = (String) pwustSfwidOrderDescMap.get("PW_ORDER_TYPE");	
   		String orderStatus = (String) pwustSfwidOrderDescMap.get("ORDER_STATUS");
   		
   		if (StringUtils.equalsIgnoreCase(pwOrderType, "REPAIR")){
   			String sapIDocNumber = (String) pwustSfwidOrderDescMap.get("UCF_ORDER_VCH15");
   			Number orderMutilCreateFlag = (Number) pwustSfwidOrderDescMap.get("UCF_ORDER_NUM5");
   			if (StringUtils.equalsIgnoreCase(orderStatus, "PENDING") && 
   				StringUtils.isNotEmpty(sapIDocNumber) &&
   				null!= orderMutilCreateFlag &&
   				orderMutilCreateFlag.intValue() >0 ) {
   				
   					message.raiseError("MFI_20","Cannot start alterations on this order. This order is part of a mutil order create ");
   			}
   		}
   		
   		
   		if ("SUB ORDER".equalsIgnoreCase(pwOrderType)) {
			validator.checkUserPrivilege("PWUST_SUB_ORDER_START_ALT"); //Defect 468
		}
		if ("SUPPLEMENTAL".equalsIgnoreCase(pwOrderType)) {
			validator.checkUserPrivilege("PWUST_SUPPLEM_ORDER_ALT_ST"); //Defect 468
		}

		this.Super.preStartAlteration(orderId, alterationReason, changeAuthorizationType, alterationNo, calledFrom,
										ucfOrderAlterationVch1, ucfOrderAlterationVch2, ucfOrderAlterationVch3, ucfOrderAlterationVch4,
										ucfOrderAlterationVch5, ucfOrderAlterationVch6, ucfOrderAlterationVch7, ucfOrderAlterationVch8,
										ucfOrderAlterationVch9, ucfOrderAlterationVch10, ucfOrderAlterationVch11, ucfOrderAlterationVch12,
										ucfOrderAlterationVch13, ucfOrderAlterationVch14, ucfOrderAlterationVch15, ucfOrderAlterationNum1,
										ucfOrderAlterationNum2, ucfOrderAlterationNum3, ucfOrderAlterationNum4, ucfOrderAlterationNum5,
										ucfOrderAlterationFlag1, ucfOrderAlterationFlag2, ucfOrderAlterationFlag3, ucfOrderAlterationFlag4,
										ucfOrderAlterationFlag5, ucfOrderAlterationDate1, ucfOrderAlterationDate2, ucfOrderAlterationDate3,
										ucfOrderAlterationDate4, ucfOrderAlterationDate5, alterationType, operationKey, discrepancyId,
										discrepancyLineNumber, objectId, workFlow, ucfOrderAlterationVch2551, ucfOrderAlterationVch2552,
										ucfOrderAlterationVch2553, ucfOrderAlterationVch40001, ucfOrderAlterationVch40002, releasePackageId,
										associateChange);
		
		// Begin defect 779
        if ("SUB ORDER".equalsIgnoreCase(pwOrderType) || 
            "SUPPLEMENTAL".equalsIgnoreCase(pwOrderType) ||
            "OVERHAUL".equalsIgnoreCase(pwOrderType) ||
            "REPAIR".equalsIgnoreCase(pwOrderType)){

                if (StringUtils.isBlank(ucfOrderAlterationVch1)){
                      message.raiseError("MFI_20","PW ALT Count can not be null");
                }
                // Use PW_ALT_COUNT column.
                int altCount = Integer.parseInt(ucfOrderAlterationVch1);
                workOrderDaoPW.updateAltCount(orderId, altCount);
        }
		// End defect 779        
        
        if (StringUtils.equalsIgnoreCase(pwOrderType, "REPAIR") &&
            !StringUtils.equalsIgnoreCase(orderStatus, "PENDING")){
        	
        	List sfwidHoldsList =commonDaoPW.selectSfwidHoldsInfo(orderId, "WORK SCOPE HOLD", "OPEN");
        	if (sfwidHoldsList ==null){
            	commonUtilsPW.deleteRepairOrderAltT308Data(orderId);
    			commonUtilsPW.saveRepairOrderAltT308Data(orderId);
        	}else if (sfwidHoldsList.isEmpty()){
            	commonUtilsPW.deleteRepairOrderAltT308Data(orderId);
    			commonUtilsPW.saveRepairOrderAltT308Data(orderId);
        	}else if (sfwidHoldsList.size()==0){
            	commonUtilsPW.deleteRepairOrderAltT308Data(orderId);
    			commonUtilsPW.saveRepairOrderAltT308Data(orderId);
        	}
        }
	}
}
