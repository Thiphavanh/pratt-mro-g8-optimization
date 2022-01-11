/*
 *  This unpublished work, first created in 2020 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2020.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    OperationImplPW.java
 * 
 *  Created: 2021-02-17
 * 
 *  Author:  Brendan Polak
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2021-02-17		B. Polak	    Initial Release - Defect 1852 - Create operations with ucf fields 
*/

package com.pw.solumina.sfwid.application.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ibaset.common.Reference;
import com.ibaset.solumina.sfwid.application.IOperation;
import com.ibaset.solumina.sfwid.dao.IOperationDao;

public class OperationImplPW {
	
	@Reference private IOperationDao operationDao = null;
	
	@Reference IOperation iOperation;
	


	public void insertAlteration(String orderId, String operationNumber, String operationType,
			String operationOptionalFlag, String operationTitle, String plannedMachineNumber, String reworkFlag,
			String outSideProcessFlag, String supplierCode, Number outSideProcessDays, Number outSideProcessCostPerUnit,
			String autoStartFlag, String autoCompleteFlag, String assignedWorkLocation, String assignedWorkDepartment,
			String assignedWorkCenter, String planAssignedWorkLocation, Number occurrenceRate, String testType,
			String ucfOrderOperationVch1, String ucfOrderOperationVch2, Number ucfOrderOperationNumber1,
			String ucfOrderOperationFlag1, Date ucfOrderOperationDate1, Number ucfOrderOperationNumber2,
			String moveToOfd, String ucfOrderOperVch3, String ucfOrderOperVch4, String ucfOrderOperVch5,
			String ucfOrderOperVch6, String ucfOrderOperVch7, String ucfOrderOperVch8, String ucfOrderOperVch9,
			String ucfOrderOperVch10, String ucfOrderOperVch11, String ucfOrderOperVch12, String ucfOrderOperVch13,
			String ucfOrderOperVch14, String ucfOrderOperVch15, Number ucfOrderOperNum3, Number ucfOrderOperNum4,
			Number ucfOrderOperNum5, Date ucfOrderOperDate2, Date ucfOrderOperDate3, Date ucfOrderOperDate4,
			Date ucfOrderOperDate5, String ucfOrderOperFlag2, String ucfOrderOperFlag3, String ucfOrderOperFlag4,
			String ucfOrderOperFlag5, String ucfOrderOperVch2551, String ucfOrderOperVch2552,
			String ucfOrderOperVch2553, String ucfOrderOperVch40001, String ucfOrderOperVch40002,
			String stepsSequenceFlag, String operationChangeLevel, String unitProcessing, Number sequenceNumber,
			String orientationFlag, String crossOrderFlag, String mustIssuePartsFlag, String calledFrom,
			Number unitsPerCycle, String autoBatchFlag, String printLabel, Number labelsPerCycle, String reportId,
			String reconcileScrap, Number unitsPerCycleActual, String batchFlag, String ucfPlanOperationVch1,
			String ucfPlanOPerationVch2, String ucfPlanOperationVch3, String ucfPlanOperationVch4,
			String ucfPlanOperationVch5, Number ucfPlanOperationNumber1, Number ucfPlanOperationNumber2,
			String ucfPlanOperationFlag1, String ucfPlanOperationFlag2, String sapReference, String authority,
			String comments, String ucfPlanOperVch6, String ucfPlanOperVch7, String ucfPlanOperVch8,
			String ucfPlanOperVch9, String ucfPlanOperVch10, String ucfPlanOperVch11, String ucfPlanOperVch12,
			String ucfPlanOperVch13, String ucfPlanOperVch14, String ucfPlanOperVch15, Number ucfPlanOperNum3,
			Number ucfPlanOperNum4, Number ucfPlanOperNum5, Date ucfPlanOperDate1, Date ucfPlanOperDate2,
			Date ucfPlanOperDate3, Date ucfPlanOperDate4, Date ucfPlanOperDate5, String ucfPlanOperFlag3,
			String ucfPlanOperFlag4, String ucfPlanOperFlag5, String ucfPlanOperVch2551, String ucfPlanOperVch2552,
			String ucfPlanOperVch2553, String ucfPlanOperVch40001, String ucfPlanOperVch40002) 
	{
		iOperation.insertAlteration(orderId, operationNumber, operationType, operationOptionalFlag, operationTitle,
				plannedMachineNumber, reworkFlag, outSideProcessFlag, supplierCode, outSideProcessDays,
				outSideProcessCostPerUnit, autoStartFlag, autoCompleteFlag, assignedWorkLocation,
				assignedWorkDepartment, assignedWorkCenter, planAssignedWorkLocation, occurrenceRate, testType,
				ucfOrderOperationVch1, ucfOrderOperationVch2, ucfOrderOperationNumber1, ucfOrderOperationFlag1,
				ucfOrderOperationDate1, ucfOrderOperationNumber2, moveToOfd, ucfOrderOperVch3, ucfOrderOperVch4,
				ucfOrderOperVch5, ucfOrderOperVch6, ucfOrderOperVch7, ucfOrderOperVch8, ucfOrderOperVch9,
				ucfOrderOperVch10, ucfOrderOperVch11, ucfOrderOperVch12, ucfOrderOperVch13, ucfOrderOperVch14,
				ucfOrderOperVch15, ucfOrderOperNum3, ucfOrderOperNum4, ucfOrderOperNum5, ucfOrderOperDate2,
				ucfOrderOperDate3, ucfOrderOperDate4, ucfOrderOperDate5, ucfOrderOperFlag2, ucfOrderOperFlag3,
				ucfOrderOperFlag4, ucfOrderOperFlag5, ucfOrderOperVch2551, ucfOrderOperVch2552, ucfOrderOperVch2553,
				ucfOrderOperVch40001, ucfOrderOperVch40002, stepsSequenceFlag, operationChangeLevel, unitProcessing,
				sequenceNumber, orientationFlag, crossOrderFlag, mustIssuePartsFlag, calledFrom, unitsPerCycle,
				autoBatchFlag, printLabel, labelsPerCycle, reportId, reconcileScrap, unitsPerCycleActual, batchFlag);

		List operList = operationDao.selectOrderIdOperNoOperKey(orderId, operationNumber);
		Number operationKey = null;
		Number stepKey = new Integer(-1);

		if (operList != null) {
			Map operInfo = (Map) operList.get(0);
			operationKey = (Number) operInfo.get("OPER_KEY");
		}

		iOperation.updateAlteration(orderId, operationKey, stepKey, operationType, operationTitle, plannedMachineNumber,
				reworkFlag, autoStartFlag, autoCompleteFlag, assignedWorkLocation, assignedWorkDepartment,
				assignedWorkCenter, planAssignedWorkLocation, operationOptionalFlag, occurrenceRate, outSideProcessFlag,
				supplierCode, outSideProcessDays, outSideProcessCostPerUnit, testType, ucfOrderOperationVch1,
				ucfOrderOperationVch2, ucfOrderOperationNumber1, ucfOrderOperationFlag1, ucfOrderOperationDate1,
				ucfOrderOperationNumber2, ucfPlanOperationVch1, ucfPlanOPerationVch2, ucfPlanOperationVch3,
				ucfPlanOperationVch4, ucfPlanOperationVch5, ucfPlanOperationNumber1, ucfPlanOperationNumber2,
				ucfPlanOperationFlag1, ucfPlanOperationFlag2, sapReference, authority, comments, ucfPlanOperVch6,
				ucfPlanOperVch7, ucfPlanOperVch8, ucfPlanOperVch9, ucfPlanOperVch10, ucfPlanOperVch11, ucfPlanOperVch12,
				ucfPlanOperVch13, ucfPlanOperVch14, ucfPlanOperVch15, ucfPlanOperNum3, ucfPlanOperNum4, ucfPlanOperNum5,
				ucfPlanOperDate1, ucfPlanOperDate2, ucfPlanOperDate3, ucfPlanOperDate4, ucfPlanOperDate5,
				ucfPlanOperFlag3, ucfPlanOperFlag4, ucfPlanOperFlag5, ucfPlanOperVch2551, ucfPlanOperVch2552,
				ucfPlanOperVch2553, ucfPlanOperVch40001, ucfPlanOperVch40002, ucfOrderOperVch3, ucfOrderOperVch4,
				ucfOrderOperVch5, ucfOrderOperVch6, ucfOrderOperVch7, ucfOrderOperVch8, ucfOrderOperVch9,
				ucfOrderOperVch10, ucfOrderOperVch11, ucfOrderOperVch12, ucfOrderOperVch13, ucfOrderOperVch14,
				ucfOrderOperVch15, ucfOrderOperNum3, ucfOrderOperNum4, ucfOrderOperNum5, ucfOrderOperDate2,
				ucfOrderOperDate3, ucfOrderOperDate4, ucfOrderOperDate5, ucfOrderOperFlag2, ucfOrderOperFlag3,
				ucfOrderOperFlag4, ucfOrderOperFlag5, ucfOrderOperVch2551, ucfOrderOperVch2552, ucfOrderOperVch2553,
				ucfOrderOperVch40001, ucfOrderOperVch40002, stepsSequenceFlag, operationChangeLevel, unitProcessing,
				sequenceNumber, orientationFlag, crossOrderFlag, mustIssuePartsFlag, calledFrom, unitsPerCycle,
				autoBatchFlag, printLabel, labelsPerCycle, reportId, reconcileScrap, unitsPerCycleActual, batchFlag);
	}

}