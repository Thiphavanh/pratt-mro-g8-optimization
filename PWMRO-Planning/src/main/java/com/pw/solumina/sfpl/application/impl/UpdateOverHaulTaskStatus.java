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
*  File:    UpdateOverHaulTaskStatus.java
*  
*  PW_SMRO_eWORK_202-Order-Creation
* 
*  Created: 2018.06.06
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date             Who            Description
*  -----------    ------------     ---------------------------------------------------
*  2018-07-09       Fred Ettefagh  Changed code for defect 820, update tasks based on each IM data row
*  2019-03-03       D.Miron        G8R2SP4 Upgrade - Added 7th argument to method call for includeExcludeWidSubject.
*/
package com.pw.solumina.sfpl.application.impl;

import static com.ibaset.common.DbColumnNameConstants.HOLD_ID;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.solumina.sfor.application.IWidSubject;
import com.ibaset.solumina.sfwid.application.IOrderAssembleText;
import com.ibaset.solumina.sfwid.application.IOverInspectionHold;
import com.pw.solumina.sfpl.dao.impl.LogOrderCreateErrors;
import com.pw.solumina.sfpl.dao.impl.OrderCreateDaoPW;

public class UpdateOverHaulTaskStatus {
	
	
	@Reference
	private OrderCreateDaoPW orderCreateDaoPW = null;
	
	@Reference
	private IWidSubject widSubject = null;
	
    @Reference
    private  LogOrderCreateErrors logOrderCreateErrors;
    
    
	@Reference
	private  IOrderAssembleText orderAssembleText;
	
	@Reference
	private IOverInspectionHold overInspectionHold = null;
	
	public void updateTaskStatus(String pwOrderNo,
					             String oobOrderNumber,
					             String partNo,
					             String planId,
					             Number planVer,
					             Number planRev,
					             Number planAlt,
					             String notes, 
					             String calledFrom, 
					             String discrepancyId,
					             String orderId, 
					             String superNetPW, 
					             String subNetPW, 
					             String workScopePW, 
					             String customerPW) {

		Number subjectNumber = null;
		Number subjectNumberAlTask = null;

		try{
			
			if (StringUtils.isBlank(oobOrderNumber) ||  StringUtils.isBlank(orderId)){
				
				Map map22 =orderCreateDaoPW.selectPwustSwidOrderDescViaPwOrderNo(pwOrderNo);
				orderId = (String)map22.get("ORDER_ID");
				oobOrderNumber = (String)map22.get("ORDER_NO");
			}
					

			List<Map> tasksToUpdatelist = orderCreateDaoPW.selectOrderTaskToUpdateStatus(orderId, workScopePW, customerPW, superNetPW, subNetPW);
			if (null!=tasksToUpdatelist && !tasksToUpdatelist.isEmpty()){
	
				for (Map taskMap : tasksToUpdatelist) {
					subjectNumber  = (Number)  taskMap.get("SUBJECT_NO");
					if (null!= subjectNumber){
						widSubject.includeExcludeWidSubject(orderId, 
	                            subjectNumber, 
	                            "INCLUDED", 
	                            notes, 
	                            discrepancyId, 
	                            null, /*discrepancyLineNo*/
	                            false); // includeOrIncludedActiveOperOrIncludedInSubjectExist  G8R2SP4
					}
				}
			}
		}catch( Exception e){
			String errMsg = e.getMessage();

			if (StringUtils.isNotBlank(errMsg)   && errMsg.length() >2000){
				errMsg = errMsg.substring(1, 1900);
			}

			String tasksBeginUpdated = "subjectNumber = " + subjectNumber + ", subjectNumberAlTask = " + subjectNumberAlTask;

			logOrderCreateErrors.insertIntoErrorTable(tasksBeginUpdated +  System.lineSeparator() + errMsg,
								                     pwOrderNo,
								                     partNo,//planDataSelected.getPartNo(),
								                     planId,//planDataSelected.getPlanId(),
								                     planVer,//planDataSelected.getPlanRev(),
								                     planRev,//planDataSelected.getPlanRev(),
								                     planAlt,//planDataSelected.getPlanAlt(),
								                     null,//planDataSelected.getPlanUpdtNo(),
								                     null,//serEnteredData.getSalesOrderNumber(),
								                     null,//userEnteredData.getEngineType(),
								                     null,//userEnteredData.getEngineModel(),
								                     null,//userEnteredData.getSapWorkLoc(),
								                     workScopePW,//userEnteredData.getWorkScope(),
								                     superNetPW,//userEnteredData.getSuperiorNet(),
								                     subNetPW,
								                     customerPW,
								                     "task update");
			throw e ;
		}

		try{
			closeOOBHolds(orderId,oobOrderNumber,notes,calledFrom);

		}catch( Exception e){
			String errMsg = e.getMessage();

			if (StringUtils.isNotBlank(errMsg)   && errMsg.length() >2000){
				errMsg = errMsg.substring(1, 1900);
			}
			logOrderCreateErrors.insertIntoErrorTable(errMsg,
								                     pwOrderNo,
								                     partNo,//planDataSelected.getPartNo(),
								                     planId,//planDataSelected.getPlanId(),
								                     planVer,//planDataSelected.getPlanRev(),
								                     planRev,//planDataSelected.getPlanRev(),
								                     planAlt,//planDataSelected.getPlanAlt(),
								                     null,//planDataSelected.getPlanUpdtNo(),
								                     null,//serEnteredData.getSalesOrderNumber(),
								                     null,//userEnteredData.getEngineType(),
								                     null,//userEnteredData.getEngineModel(),
								                     null,//userEnteredData.getSapWorkLoc(),
								                     workScopePW,//userEnteredData.getWorkScope(),
								                     superNetPW,//userEnteredData.getSuperiorNet(),
								                     subNetPW,
								                     customerPW,
								                     "task update close hold");
			throw e ;
		}
	
	}


	public void closeOOBHolds(String orderId, String orderNumber,String notes,String calledFrom) {

		List holdList =orderAssembleText.getOrderHolds( orderId,
										               null,//String whereClause,
					                                   null,//String groupJobNo,
					                                   orderNumber);

		if (null!= holdList && !holdList.isEmpty()){
			Iterator iteratorHolds = holdList.iterator();
			while (iteratorHolds.hasNext()){

				Map map1 = (Map) iteratorHolds.next();
				String holdId = (String) map1.get(HOLD_ID);
				String holdStatus = (String) map1.get("HOLD_STATUS");
				
				if (holdId!=null && !StringUtils.equalsIgnoreCase(holdStatus, "CLOSED")){
	

					overInspectionHold.closeHold(orderId, 
											     null,//operationNumber, 
											     holdId, 
											     notes, 
											     calledFrom, 
											     null,//ucfHoldVarchar1, 
											     null,//ucfHoldVarchar2, 
											     null,//ucfHoldVarchar3, 
											     null,//ucfHoldVarchar4, 
											     null,//ucfHoldVarchar5, 
											     null,//ucfHoldVarchar6, 
											     null,//ucfHoldVarchar7, 
											     null,//ucfHoldVarchar8, 
											     null,//ucfHoldVarchar9, 
											     null,//ucfHoldVarchar10, 
											     null,//ucfHoldVarchar11, 
											     null,//ucfHoldVarchar12, 
											     null,//ucfHoldVarchar13, 
											     null,//ucfHoldVarchar14, 
											     null,//ucfHoldVarchar15, 
											     null,//ucfHoldNumber1, 
											     null,//ucfHoldNumber2, 
											     null,//ucfHoldNumber3,
											     null,//ucfHoldNumber4, 
											     null,//ucfHoldNumber5, 
											     null,//ucfHoldFlag1, 
											     null,//ucfHoldFlag2, 
											     null,//ucfHoldFlag3, 
											     null,//ucfHoldFlag4, 
											     null,//ucfHoldFlag5, 
											     null,//ucfHoldDate1,
											     null,//ucfHoldDate2, 
											     null,//ucfHoldDate3, 
											     null,//ucfHoldDate4, 
											     null,//ucfHoldDate5, 
											     null,//includeUcfFlag, 
											     null,//ucfHoldVarchar2551, 
											     null,//ucfHoldVarchar2552,
											     null,//ucfHoldVarchar2553, 
											     null,//ucfHoldVarchar40001,
											     null,//ucfHoldVarchar40002, 
											     null,//calledFromHoldClose, 
											     null,//nextingOperKey, 
											     null,//nextingStepKey, 
											     null,//groupJobNo, 
											     null,//groupJobStepKey, 
											     null,//groupJobNavLevel, 
											     null,//groupJobStepNo, 
											     null,//groupJobPlanId, 
											     null,//groupJobPlanVer, 
											     null,//groupJobPlanRev, 
											     null,//groupJobPlanAlt, 
											     null);//groupJobOrderId);
				}
			}
		}
	}	
}
