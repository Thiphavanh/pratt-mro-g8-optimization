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
*  File:    RollUpImplPW.java
* 
*  Created: 2020-06-01
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2020-06-05 	Fred Ettefagh   Initial Release SMRO_TR_319, Defect 1611
* 2020-06-05 	Fred Ettefagh   Defect 1582, moved method repairWorkOrderSplitList to CommanDaoPW
* 2020-06-23    Fred ETtefagh   Defect 1703, moved check for repair work order split to CommonDaoPW class 
* 
* */
package com.ibaset.solumina.sfwid.application.impl;

import static com.ibaset.common.FrameworkConstants.CANCEL;
import static com.ibaset.common.FrameworkConstants.NO;
import static com.ibaset.common.SoluminaConstants.CLOSE;
import static com.ibaset.common.SoluminaConstants.END;
import static com.ibaset.common.SoluminaConstants.EXCLUDE;
import static com.ibaset.common.SoluminaConstants.PENDING;
import static com.ibaset.common.SoluminaConstants.ROLLUP;
import static com.ibaset.common.SoluminaConstants.SERIAL;
import static com.ibaset.common.SoluminaConstants.SKIP;
import static com.ibaset.common.util.SoluminaUtils.stringEquals;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.solumina.sfwid.application.IOperationSignOnOff;
import com.ibaset.solumina.sfwid.application.IOrderDetails;
import com.ibaset.solumina.sfwid.application.IRollUp;
import com.ibaset.solumina.sfwid.application.IWorkOrderStatus;
import com.ibaset.solumina.sfwid.dao.IOperationDao;
import com.ibaset.solumina.sfwid.dao.impl.RollUpDaoImplPW;
import com.pw.common.dao.CommonDaoPW;

public abstract class RollUpImplPW extends ImplementationOf<IRollUp> implements IRollUp{

    @Reference private CommonDaoPW commonDaoPW = null;
    @Reference private RollUpDaoImplPW rollUpDaoImplPW = null;
	@Reference private IOperationDao operationDao = null;
	@Reference private IOrderDetails orderDetails = null;
	@Reference private IOperationSignOnOff operSignOnOff;
	@Reference private IWorkOrderStatus workOrderStatus = null;
	
    
	public void rollUp(String orderId, Number operationKey) {
		
		boolean isRepairOrderSplit = commonDaoPW.isRepairWorkOrderSplit(orderId);
		
		if (isRepairOrderSplit){
				repairRollupPW(orderId, operationKey);
		}else{
				Super.rollUp(orderId, operationKey);
		}
	}
	
	
    public void repairRollupPW(String orderId, Number operationKey) {

    	// 06-01-2020
    	// Fred Ettefagh
    	// this method is a copy of out of the box method.
    	// only change is to call custom Dao method if repair split is done on the order.
    	// custome dao method is: Map orderAndSerialDescriptionMap = rollUpDaoImplPW.selectOrderAndSerialDescription(orderId);
    	// instead of out of the box Dao method: Map orderAndSerialDescriptionMap = operationDao.selectOrderAndSerialDescription(orderId);
    	
        // Declares the local variables.
        String closeStatus = CLOSE;
        String reason = ROLLUP;
        
        // Checks whether the Serial Operation Information exists or not.
        boolean serialOperationInformationExists = operationDao.selectSerialOperationInformationExists(orderId,
                                                                                                       operationKey);
                                                                                                       
        // When the Serial Operation Information does not exist.
        if (!serialOperationInformationExists)
        {
            // Finds Serials that are not in this Operation yet and checks if
            // they
            // are in a final Status 'COMPLETE', 'SCRAP' or 'STOP'.
            boolean serialIdExists = operationDao.selectSerialIdExists(orderId, operationKey);
            
            // When the Serial Id does not exist.
            if (!serialIdExists)
            {
                // Checks if all Serial Operations are 'EXCLUDE' or 'CANCEL' or
                // 'CLOSE'
                // with Reason 'SKIP'.
                Map serialOperationMap = operationDao.selectSerialOperation(orderId, operationKey);
                
                // Gets the values of Serial Operation in local variables.
                Number excludeCount = (Number) serialOperationMap.get("EXCLUDE_CNT");
                Number cancelCount = (Number) serialOperationMap.get("CANCEL_CNT");
                Number skipCount = (Number) serialOperationMap.get("SKIP_CNT");
                Number total = (Number) serialOperationMap.get("TOTAL");
                
                if (excludeCount != null && excludeCount.longValue() == total.longValue())
                {
                    // Sets the Close Status to 'EXCLUDE'.
                    closeStatus = EXCLUDE;
                }
                else if (cancelCount != null && cancelCount.longValue() == total.longValue())
                {
                    // Sets the Close Status to 'CANCEL'.
                    closeStatus = CANCEL;
                }
                else if (skipCount != null && skipCount.longValue() == total.longValue())
                {
                    // Sets the Reason to 'SKIP'.
                    reason = SKIP;
                }
                
                // Selects the Order Description and Serial Description.
                //Map orderAndSerialDescriptionMap = operationDao.selectOrderAndSerialDescription(orderId);
                Map orderAndSerialDescriptionMap = rollUpDaoImplPW.selectOrderAndSerialDescription(orderId);
                
                // Gets the values of Order Description and Serial Description
                // in local variables.
                Number orderQuantity = (Number) orderAndSerialDescriptionMap.get("ORDER_QTY");
                Number serialCount = (Number) orderAndSerialDescriptionMap.get("SERIAL_CNT");
                
                // Gets the Order Control.
                String orderControl = orderDetails.orderControl(orderId);
                
                // Close operation if order quantity = total no of serials in
                // SERIAL_DESC.
                if ((orderQuantity != null
                        && serialCount.doubleValue() == orderQuantity.doubleValue())
                        || !orderControl.startsWith(SERIAL))
                {
                    // 11/14/2007 Meena Desai FND-7047 Update the Operation or
                    // Order Dates before updating the Status
                    // For excluded Operations no need to update the End Date.
                    if (!stringEquals(closeStatus, EXCLUDE))
                    {
                        
                        // Sets the End Dates.
                    	operSignOnOff.updateOperationDate(orderId, operationKey, END);
                    }
                    // Updates the Operation Status.
                    workOrderStatus.updateOperationStatus(orderId, operationKey, closeStatus, NO, reason);
                    
                    // Signs of all users for that operation.
                    operSignOnOff.signOffAll(orderId, operationKey);
                    
                    // Rolls Up Order Dates.
                    rollUp(orderId);
                }
            }
            // GE-124
            else
            {
                boolean serialInfoExist = operationDao.selectSerialOperInfoExists(orderId,
                                                                                  operationKey);
                if (!serialInfoExist)
                {
                    // Updates the Operation Status.
                	workOrderStatus.updateOperationStatus(orderId, operationKey, PENDING, NO, reason);
                }
            }
        }
	}
	
}
