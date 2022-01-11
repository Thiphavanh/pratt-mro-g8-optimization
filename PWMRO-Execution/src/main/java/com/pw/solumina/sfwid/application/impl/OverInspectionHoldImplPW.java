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
 *  File:    OverInspectionHoldImplPW.java
 * 
 *  Created: 2019-09-25
 * 
 *  Author:  Fred Ettefagh
 * 
 *  Revision History
 * 
 *  Date      	Who                	Description
 *  ----------	---------------	---------------	---------------------------------------------------
 * 2019-09-25 	Fred Ettefagh   Initial Release for PW_SMRO_TR_308
 * 2020-02-13   Fred Ettefagh   SMRO_TR_308, Defect 1474, changed code to send t308 message after certain changes for WO Alt and task include exclude
 * 2020-02-26   Fred Ettefagh   SMRO_TR_308, Defect 1474, changed code to send t308 message after certain changes for WO Alt and task include exclude
 */
package com.pw.solumina.sfwid.application.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.solumina.sfbis.dao.ServiceDefinitionDao;
import com.ibaset.solumina.sfbis.domain.ServiceDef;
import com.ibaset.solumina.sfcore.application.IUser;
import com.ibaset.solumina.sfwid.application.IOverInspectionHold;
import com.pw.common.dao.CommonDaoPW;
import com.pw.common.utils.CommonUtilsPW;
import com.pw.solumina.T308.integration.impl.RepairOrderT308Interface;
import com.pw.solumina.sfwid.dao.impl.RepairWorkOrderT308Dao;


public abstract class OverInspectionHoldImplPW extends ImplementationOf<IOverInspectionHold> implements IOverInspectionHold{

	 @Reference  CommonDaoPW commonDaoPW;
	 @Reference  RepairOrderT308Interface repairOrderT308Interface;
	 @Reference  CommonUtilsPW commonUtilsPW;
     @Reference	 RepairWorkOrderT308Dao repairWorkOrderT308Dao;
	 
      public void closeHold(String orderId,
							String operationNumber,
							String holdId,
							String notes,
							String calledFrom,
							String ucfHoldVarchar1,
							String ucfHoldVarchar2,
							String ucfHoldVarchar3,
							String ucfHoldVarchar4,
							String ucfHoldVarchar5,
							String ucfHoldVarchar6,
							String ucfHoldVarchar7,
							String ucfHoldVarchar8,
							String ucfHoldVarchar9,
							String ucfHoldVarchar10,
							String ucfHoldVarchar11,
							String ucfHoldVarchar12,
							String ucfHoldVarchar13,
							String ucfHoldVarchar14,
							String ucfHoldVarchar15,
							Number ucfHoldNumber1,
							Number ucfHoldNumber2,
							Number ucfHoldNumber3,
							Number ucfHoldNumber4,
							Number ucfHoldNumber5,
							String ucfHoldFlag1,
							String ucfHoldFlag2,
							String ucfHoldFlag3,
							String ucfHoldFlag4,
							String ucfHoldFlag5,
							Date ucfHoldDate1,
							Date ucfHoldDate2,
							Date ucfHoldDate3,
							Date ucfHoldDate4,
							Date ucfHoldDate5,
							String includeUcfFlag,
							String ucfHoldVarchar2551,
							String ucfHoldVarchar2552,
							String ucfHoldVarchar2553,
							String ucfHoldVarchar40001,
							String ucfHoldVarchar40002, 
							String calledFromHoldClose,
							Number nextingOperKey, 
							Number nextingStepKey,
							String groupJobNo,
							Number groupJobStepKey,
							String groupJobNavLevel,
							String groupJobStepNo,
							String groupJobPlanId,
							Number groupJobPlanVer, 
							Number groupJobPlanRev, 
							Number groupJobPlanAlt, 
							String groupJobOrderId){

    	    List pwSfwidOrderDescList =commonDaoPW.selectPWOrderDataByOrderId(orderId);
    	    if (pwSfwidOrderDescList!=null && !pwSfwidOrderDescList.isEmpty()){
    	    	
    	    	 Map pwOrderDataMap = (Map) pwSfwidOrderDescList.iterator().next();
    	    	 String pwOrderType = (String) pwOrderDataMap.get("PW_ORDER_TYPE");
    	    	 
    	    	 if (StringUtils.equalsIgnoreCase(pwOrderType, "REPAIR")){
    	    		
    	    		if (!StringUtils.equals("AUTO REPAIR END OF ALT CLOSE", calledFrom)){
    	    			
	    	    		String orderStatus = (String)pwOrderDataMap.get("ORDER_STATUS");
	    	    		String altStatus = (String)pwOrderDataMap.get("ALT_STATUS");
	    	    		if (StringUtils.isBlank(altStatus)){
	    	    			if (StringUtils.equals(orderStatus, "IN QUEUE") || StringUtils.equals(orderStatus, "ACTIVE")){
	
	    	    				List sfwidHoldsList =commonDaoPW.selectSfwidHoldsInfo(orderId, "WORK SCOPE HOLD", "OPEN");
	    	    				if (sfwidHoldsList!=null && !sfwidHoldsList.isEmpty()){
	    	    					// interface with SAP T308
	    	    					if (commonUtilsPW.sendRepairT308()){
	    	    						repairOrderT308Interface.sendOrderT308Msg(orderId,"closeWorkScopeHold");
	    	    					}
	    	    				}
	    	    			}
				    	}
	 				}
    	    	 }
 			}

    	    Super.closeHold(orderId, 
    	  			        operationNumber, 
    	  			        holdId, 
    	  			        notes, 
    	  			        calledFrom, 
    	  			        ucfHoldVarchar1, 
    	  			        ucfHoldVarchar2, 
    	  			        ucfHoldVarchar3, 
    	  			        ucfHoldVarchar4, 
    	  			        ucfHoldVarchar5, 
    	  			        ucfHoldVarchar6, 
    	  			        ucfHoldVarchar7, 
    	  			        ucfHoldVarchar8, 
    	  			        ucfHoldVarchar9, 
    	  			        ucfHoldVarchar10, 
    	  			        ucfHoldVarchar11, 
    	  			        ucfHoldVarchar12, 
    	  			        ucfHoldVarchar13, 
    	  			        ucfHoldVarchar14, 
    	  			        ucfHoldVarchar15, 
    	  			        ucfHoldNumber1,
    	  			        ucfHoldNumber2, 
    	  			        ucfHoldNumber3, 
    	  			        ucfHoldNumber4, 
    	  			        ucfHoldNumber5,
    	  			        ucfHoldFlag1,
    	  			        ucfHoldFlag2, 
    	  			        ucfHoldFlag3,
    	  			        ucfHoldFlag4,
    	  			        ucfHoldFlag5,
    	  			        ucfHoldDate1,
    	  			        ucfHoldDate2,
    	  			        ucfHoldDate3, 
    	  			        ucfHoldDate4,
    	  			        ucfHoldDate5, 
    	  			        includeUcfFlag,
    	  			        ucfHoldVarchar2551,
    	  			        ucfHoldVarchar2552, 
    	  			        ucfHoldVarchar2553, 
    	  			        ucfHoldVarchar40001,
    	  			        ucfHoldVarchar40002, 
    	  			        calledFromHoldClose, 
    	  			        nextingOperKey, 
    	  			        nextingStepKey, 
    	  			        groupJobNo, 
    	  			        groupJobStepKey, 
    	  			        groupJobNavLevel,
    	  			        groupJobStepNo, 
    	  			        groupJobPlanId, 
    	  			        groupJobPlanVer,
    	  			        groupJobPlanRev,
    	  			        groupJobPlanAlt, 
    	  			        groupJobOrderId);
    	  	
    	
    		
    	
      }
}
