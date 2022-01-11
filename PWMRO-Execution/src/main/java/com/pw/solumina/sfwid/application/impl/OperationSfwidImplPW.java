/*
 *  This unpublished work, first created in 2017 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2017, 2020.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    OperationImplPW.java
 * 
 *  Created: 2018-05-14
 * 
 *  Author:  Fred Ettefagh
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2018-05-14		Fred Ettefagh	Initial Release SMRO_eWORK_202 - Order Creation 2018-05-17 defect 760  update oper conf no for sub order and Supplemental Order
 *  2018-06-05      Fred Ettefagh   defect 760, changed insert and update oper, so that conf number is updated via LO table.  This is to fix issue of adding/updating oper when order in active or inqueue  
 *  2018-06-17      Sam Niu         defect 805, Alteration Completion check for copied operations  
 *  2018-08-03      Bob Preston     SoluminaOOB, defect 848 - Check for nulls.
 *  2018-10-01      Fred Ettefagh   Defect 980 Cannot Create Suborder due to supNet subNet and conf Numbers
 *  2018-10-12      Fred Ettefagh   Defect 996 for pending orders, changed code to get conf data from source order/oper if source/oper has conf data
 *  2018-12-17      Fred Ettefagh   Defect 1044, fix repair order create
 *  2018-12-21      Fred Ettefagh   Defect 1044, fix repair order create
 *  2020-03-09      John DeNinno    Defect 1462, signature fix
 *  2020-06-24      D.Miron         Defect 1696 - Removed selectAvailableCollectionUnits. 
 *  2020-07-29      J DeNinno       Defect 1041 - check PWUST_LONOTE_BYPASS priv before doing the check
 *  2020-09-20      j DeNinno		Defect 1767 - Error when processing buyoff badge swipe on Overhaul. Serial Number missing
 *  2020-09-09      J DeNinno       Defect 1770 - check PWUST_LONOTE_BYPASS priv before doing the error
 *  2020-09-16      J DeNinno       Defect 1776 - backout change for  1767  and add new change                            
 *  2020-09-16      J DeNinno       Defect 1776 - and add new change                            
 *  2020-10-06      J DeNinno		Defect 1782 - Add code that removes buyoff data from copied buyoff
 *  2020-11-23		S. Edgerly		SMRO_WOE_211 - Defect 1780 - Copying multiple operations into orders
 *  2020-10-09      J DeNinno       Defect 1114 - Superior Net and Sub Net set to plan's
 *  2021-02-04      J DeNinno       Defect 1854 - remove check that stops user from changing the sup and sub
 */
package com.pw.solumina.sfwid.application.impl;

import static com.ibaset.common.FrameworkConstants.FOUNDATION;
import static com.ibaset.common.FrameworkConstants.OPER_NUM_MASK;
import static com.ibaset.common.SoluminaConstants.COMMA;
import static com.ibaset.common.SoluminaConstants.SEMI_COLON;
import static com.ibaset.common.SoluminaConstants.TRANSACTION_INPUT_MATRIX;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.StringUtils.defaultIfEmpty;
import static org.apache.commons.lang.StringUtils.isEmpty;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sfcore.application.IUser;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.application.IParse;
import com.ibaset.solumina.sffnd.application.IWrapUtils;
import com.ibaset.solumina.sfor.application.ISfwidUndo;
import com.ibaset.solumina.sfwid.application.IOrderDetails;
import com.ibaset.solumina.sfwid.dao.IOperationDao;
import com.ibaset.solumina.sfwid.dao.ISerialDao;
import com.pw.common.utils.CommonUtilsPW;
import com.pw.common.dao.CommonDaoPW;
import com.pw.solumina.sfwid.dao.BuyoffDaoPW;  //defect 1114
import com.pw.solumina.sfwid.dao.WorkOrderDaoPW;
import com.pw.solumina.sfwid.dao.impl.OrderOperConfirmationDao;
import com.pw.solumina.sfwid.impl.OrderOperConfirmationCheck;

public abstract class OperationSfwidImplPW extends ImplementationOf<com.ibaset.solumina.sfwid.application.IOperation> implements com.ibaset.solumina.sfwid.application.IOperation{


	// order sfwid IOperation extension code

	@Reference private com.ibaset.solumina.sfcore.application.IUser privilege;

	@Reference
	private IMessage message = null;

	@Reference
	private WorkOrderDaoPW workOrderDaoPW;

	@Reference
	private ISerialDao serialDao = null;

	@Reference
	private ISfwidUndo sfwidUndo = null;

	@Reference
	private IOperationDao operationDao = null; //Defect 805

	@Reference	private CommonUtilsPW commonUtilsPW;
	@Reference private CommonDaoPW commonDaoPW;
	@Reference	private OrderOperConfirmationDao orderOperConfirmationDao;

	@Reference	private OrderOperConfirmationCheck orderOperConfirmationCheck;

	@Reference private IParse parse = null;
	protected final Log logger = LogFactory.getLog(this.getClass()); //defect 1780 (Removed @Reference)
	@Reference private IOrderDetails orderDetails = null;
	@Reference private IWrapUtils wrapUtils = null;
	@Reference private IEvent event = null;
	@Reference private IUser user = null;

	@Reference private BuyoffDaoPW buyoffDaoPW; // defect 1114

	// defect 1044
	public void showCopyOperationFromPlanInputMatrix(String fromPlanId,
			Number fromPlanVersion,
			Number fromPlanRevision,
			Number fromPlanAlterations,
			String fromPartNumber,
			String fromItemType,
			String fromItemSubtype,
			String fromProgram,
			String operationNumberList,
			String toOrderNumber,
			String toPartNumber,
			String toPartChange,
			String targetOperationNumber,
			String incrementBy,
			String fromPartChange,
			String fromPlanNo,
			Number sequenceNumber){
		// Fred Ettefagh 12/20/2018
		// the code in this method is copied from OOB method "showCopyOperationFromPlanInputMatrix".
		// so there are no diff between code in the method and OOB method "showCopyOperationFromPlanInputMatrix".
		// The only change is to call a diff input matrix UDV when we have a repair order vs overhaul order. 
		// this input matrix UDV is used for copy oper from plan to Order.
		// the section of code that is changed is marked with "only diff from OOB"


		Number tempIncrementBy;

		// Default these to empty Strings so that if they are null, they don't
		// show up as "null" in the UDV call
		fromPlanId = defaultIfEmpty(fromPlanId, EMPTY);
		fromPartNumber = defaultIfEmpty(fromPartNumber,
				EMPTY);
		fromItemType = defaultIfEmpty(fromItemType,
				EMPTY);
		fromItemSubtype = defaultIfEmpty(fromItemSubtype,
				EMPTY);
		fromProgram = defaultIfEmpty(fromProgram, EMPTY);
		operationNumberList = defaultIfEmpty(operationNumberList,
				EMPTY);
		toPartNumber = defaultIfEmpty(toPartNumber,
				EMPTY);
		toPartChange = defaultIfEmpty(toPartChange,
				EMPTY);
		targetOperationNumber = defaultIfEmpty(targetOperationNumber,
				EMPTY);
		// FND-12215
		fromPartChange = defaultIfEmpty(fromPartChange,
				EMPTY);
		fromPlanNo = defaultIfEmpty(fromPlanNo, EMPTY);

		String planVersionString = EMPTY;
		String planRevisionString = EMPTY;
		String planAlterationsString = EMPTY;

		if (fromPlanVersion != null)
		{
			planVersionString = fromPlanVersion.toString();
		}

		if (fromPlanRevision != null)
		{
			planRevisionString = fromPlanRevision.toString();
		}

		if (fromPlanAlterations != null)
		{
			planAlterationsString = fromPlanAlterations.toString();
		}

		// When incrementBy is null sets it with 10
		if (isEmpty(incrementBy))
		{
			tempIncrementBy = new Integer(0);
		}
		else
		{
			tempIncrementBy = new Double(incrementBy);
		}

		// Raises an error if incrementBy is not null and not between 0 and 1000
		if (isNotEmpty(incrementBy)
				&& (tempIncrementBy.intValue() < 1 || tempIncrementBy.intValue() > 1000))
		{
			message.raiseError("MFI_800");
		}

		// If specifying an Operation increment, must also specify a starting
		// Operation
		if (isEmpty(targetOperationNumber)
				&& isNotEmpty(incrementBy))
		{
			message.raiseError("MFI_81053");
		}
		// Raises an error if length Operation Number List exceeds 4000 in order
		// to catch a buffer overflow error
		else if (operationNumberList.length() > 4000)
		{
			message.raiseError("MFI_960");
		}
		else
		{
			if (isNotEmpty(incrementBy))
			{
				try
				{
					// String array of Operation Numbers
					String[] serialVariables = parse.parse(operationNumberList,
							SEMI_COLON,
							true);

					// Gets Total Number of Operations from Operation List
					serialVariables = parse.parse(operationNumberList,
							SEMI_COLON,
							true);
					int totalOperations = serialVariables.length;

					// Get the length of the largest Operation Number
					int operationNumberStringLength = getOperationNumberStringLength(targetOperationNumber,
							totalOperations,
							tempIncrementBy);

					// Raises an error if length of Operation Number length
					// exceeds 10
					if (operationNumberStringLength > 10)
					{
						message.raiseError("MFI_82211");
					}
				}
				// Catches an exception if targetOperationNumber can not be
				// converted into number
				catch (NumberFormatException exception)
				{
					// When targetOperationNumber can not be converted into
					// number, let the execution continue
					logger.debug(exception.getMessage(), exception);
				}
			}

			// Gets Order Id for given Order No
			String orderId = null;
			try
			{
				Map orderInformationMap = operationDao.selectOrderInformation(toOrderNumber);

				orderId = (String) orderInformationMap.get("ORDER_ID");
			}
			catch (EmptyResultDataAccessException erdae)
			{
				logger.debug(erdae.getMessage(), erdae);
			}

			String udvId = EMPTY;

			String mroFlag = orderDetails.isMRO(orderId);

			udvId = "MFI_18569";

			String transaction = TRANSACTION_INPUT_MATRIX;
			String hideColumns = EMPTY;

			// Gets the absolute and truncated value of Increment By to append
			// if Increment By is not null
			String absoluteTruncatedIncrementBy = EMPTY;
			if (isNotEmpty(incrementBy))
			{
				absoluteTruncatedIncrementBy = String.valueOf(Math.abs(tempIncrementBy.intValue()));
			}

			// Appends parameters
			// GE-212 Pass all fields Using wrapUtils
			StringBuilder parameters = new StringBuilder().append("From_Plan_ID=")
					.append(wrapUtils.wrapValue(fromPlanId))
					.append(COMMA)
					.append("From_Plan_Version=")
					.append(wrapUtils.wrapValue(planVersionString))
					.append(COMMA)
					.append("From_Plan_Revision=")
					.append(wrapUtils.wrapValue(planRevisionString))
					.append(COMMA)
					.append("From_Plan_Alterations=")
					.append(wrapUtils.wrapValue(planAlterationsString))
					.append(COMMA)
					.append("From_Item_No=")
					.append(wrapUtils.wrapValue(fromPartNumber))
					.append(COMMA)
					.append("From_Item_Type=")
					.append(wrapUtils.wrapValue(fromItemType))
					.append(COMMA)
					.append("From_Item_Subtype=")
					.append(wrapUtils.wrapValue(fromItemSubtype))
					.append(COMMA)
					.append("From_Program=")
					.append(wrapUtils.wrapValue(fromProgram))
					.append(COMMA)
					.append("Oper_No_List=")
					.append(wrapUtils.wrapValue(operationNumberList))
					.append(COMMA)
					.append("To_Order_ID=")
					.append(wrapUtils.wrapValue(orderId))
					.append(COMMA)
					.append("To_Order_No=")
					.append(wrapUtils.wrapValue(toOrderNumber))
					.append(COMMA)
					.append("To_Part_No=")
					.append(wrapUtils.wrapValue(toPartNumber))
					.append(COMMA)
					.append("To_Part_Chg=")
					.append(wrapUtils.wrapValue(toPartChange))
					.append(COMMA)
					.append("TARGET_OPER_NO=")
					.append(wrapUtils.wrapValue(targetOperationNumber))
					.append(COMMA)
					.append("MRO_FLAG=")
					.append(wrapUtils.wrapValue(mroFlag))
					.append(COMMA)
					.append("INCR_BY=")
					.append(wrapUtils.wrapValue(absoluteTruncatedIncrementBy))
					.append(COMMA)
					.append("FROM_ITEM_CHG=")
					.append(wrapUtils.wrapValue(fromPartChange))
					.append(COMMA)
					.append("FROM_PLAN_NO=")
					.append(wrapUtils.wrapValue(fromPlanNo))
					.append(COMMA)
					.append("EXE_ORDER=")
					.append((sequenceNumber==null)?wrapUtils.wrapValue(EMPTY):wrapUtils.wrapValue(sequenceNumber));


			// only diff from OOB
			// start
			String pwOrderType = commonUtilsPW.getOrderTypePW(orderId);

			if ("REPAIR".equalsIgnoreCase(pwOrderType)){ 
				// custom IM UDV so that sub_net and sup_net is not displayed.
				udvId = "PWUST_7D7B62E1627844AFE05387971F0A5D05";
			}else{
				udvId = "MFI_18569";
			}
			// only diff from OOB
			// end


			// Shows the EventImpl UDV
			event.showUdv(udvId,
					transaction,
					EMPTY,
					hideColumns,
					parameters.toString());
		}
	}



	public int getOperationNumberStringLength(String operationNumber,
			int operationCount,
			Number incrementValue)
	{
		int stringLength = 0;

		BigDecimal incrementValueBD = new BigDecimal(incrementValue.toString());
		logger.debug("Increment Value as a BigDecimal = " + incrementValueBD);

		BigDecimal operationNumberBD = new BigDecimal(operationNumber.toString());
		logger.debug("Operation Number as a BigDecimal = " + operationNumberBD);

		BigDecimal incrementCountBD = new BigDecimal("" + (operationCount - 1));
		logger.debug("Increment Count as a BigDecimal = " + incrementCountBD);

		BigDecimal incrementSpan = incrementValueBD.multiply(incrementCountBD);
		logger.debug("Increment Span as a BigDecimal= " + incrementSpan);

		BigDecimal largestOperationNumberBD = incrementSpan.add(operationNumberBD);
		logger.debug("Largest Operation Number as a BigDecimal = "
				+ largestOperationNumberBD);

		String largestOperationNumberString = largestOperationNumberBD.toString();
		logger.debug("Largest Operation Number as a String = "
				+ largestOperationNumberString);

		stringLength = largestOperationNumberString.length();
		logger.debug("String length = " + stringLength);

		return stringLength;
	}



	// defect 980
	public void copyOperation(  String oldOrderId,
			Number oldOperationKey,
			String newOrderId,
			String newOperationNumber,
			Number newOperationKey,
			String copyComponentParts,
			String newOperationTitle,
			Number sequenceNumber,
			String calledFrom){

		// copy oper from wid to wid

		Super.copyOperation(oldOrderId,
				oldOperationKey,
				newOrderId,
				newOperationNumber,
				newOperationKey,
				copyComponentParts,
				newOperationTitle,
				sequenceNumber,
				calledFrom);

		//begin defect 1782
		String oldOperNo = commonDaoPW.getOperNofromOperKeyStepKey(oldOperationKey, -1, oldOrderId);
		orderOperConfirmationDao.updateOperBuyoffByOperKey(newOrderId, newOperationKey, -1, "");
		//end defect 1782

		String newPwOrderType = commonUtilsPW.getOrderTypePW(newOrderId);
		if ("OVERHAUL".equalsIgnoreCase(newPwOrderType)||"SUB ORDER".equalsIgnoreCase(newPwOrderType)){
			Map oldPwustOrdreDescMap = orderOperConfirmationDao.selectPwustSfwidOrderDesc(oldOrderId);
			String oldOrderStatus = (String) oldPwustOrdreDescMap.get("ORDER_STATUS");


			if (StringUtils.equalsIgnoreCase(oldOrderStatus, "PENDING")){

				// defect 996 start
				// --------------------
				// check to see if oldOperKey has sup/sub data. if yes, use it to update newOperationKey
				String oldConfNumber=null;
				String oldSupNumber =null;
				String oldSubNumber = null;

				Map oldOperKeyMap = orderOperConfirmationDao.selectOrderOperDescMap(oldOrderId,oldOperationKey);
				if (oldOperKeyMap!=null){

					oldConfNumber = (String) oldOperKeyMap.get("UCF_ORDER_OPER_VCH1");
					oldSupNumber = (String) oldOperKeyMap.get("UCF_ORDER_OPER_VCH2");
					oldSubNumber = (String) oldOperKeyMap.get("UCF_ORDER_OPER_VCH3");
				}
				if (StringUtils.isNotBlank(oldConfNumber) &&
						StringUtils.isNotBlank(oldSupNumber) && 
						StringUtils.isNotBlank(oldSubNumber) ){
					orderOperConfirmationDao.updateOperConfNumberByOperKey(newOrderId, newOperationKey, -1, oldConfNumber,oldSupNumber,oldSubNumber);
				}else{
					orderOperConfirmationCheck.updateOrderOperConfFromPendingOrderOperData(oldOrderId, oldOperationKey, newOrderId, newOperationNumber, newOperationKey, oldPwustOrdreDescMap);
				}
				// defect 996 end
				//----------------------
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public void insertAlteration(String orderId,
			String operationNumber,
			String operationType,
			String operationOptionalFlag,
			String operationTitle,
			String plannedMachineNumber,
			String reworkFlag,
			String outSideProcessFlag,
			String supplierCode,
			Number outSideProcessDays,
			Number outSideProcessCostPerUnit,
			String autoStartFlag,
			String autoCompleteFlag,
			String assignedWorkLocation,
			String assignedWorkDepartment,
			String assignedWorkCenter,
			String planAssignedWorkLocation,
			Number occurrenceRate,
			String testType,
			String ucfOrderOperationVch1,
			String ucfOrderOperationVch2,
			Number ucfOrderOperationNumber1,
			String ucfOrderOperationFlag1,
			Date ucfOrderOperationDate1,
			Number ucfOrderOperationNumber2,
			String moveToOfd,
			String ucfOrderOperVch3,
			String ucfOrderOperVch4,
			String ucfOrderOperVch5,
			String ucfOrderOperVch6,
			String ucfOrderOperVch7,
			String ucfOrderOperVch8,
			String ucfOrderOperVch9,
			String ucfOrderOperVch10,
			String ucfOrderOperVch11,
			String ucfOrderOperVch12,
			String ucfOrderOperVch13,
			String ucfOrderOperVch14,
			String ucfOrderOperVch15,
			Number ucfOrderOperNum3,
			Number ucfOrderOperNum4,
			Number ucfOrderOperNum5,
			Date ucfOrderOperDate2,
			Date ucfOrderOperDate3,
			Date ucfOrderOperDate4,
			Date ucfOrderOperDate5,
			String ucfOrderOperFlag2,
			String ucfOrderOperFlag3,
			String ucfOrderOperFlag4,
			String ucfOrderOperFlag5,
			String ucfOrderOperVch2551,
			String ucfOrderOperVch2552,
			String ucfOrderOperVch2553,
			String ucfOrderOperVch40001,
			String ucfOrderOperVch40002,
			String stepsSequenceFlag,
			String operationChangeLevel,
			String unitProcessing,
			Number sequenceNumber,
			String orientationFlag,
			String crossOrderFlag,
			String mustIssuePartsFlag,
			String calledFrom,
			Number unitsPerCycle,
			String autoBatchFlag,
			String printLabel,
			Number labelsPerCycle,
			String reportId,
			String reconcileScrap, 
			Number unitsPerCycleActual, 
			String batchFlag){

		// ucfOrderOperationVch2 is sup net
		// ucfOrderOperVch3 is sub net

		String confNumber = null;
		//Map pwustSfwidOrderDescMap = getPwOrderMap(orderId);
		//String pwOrderType = (String) pwustSfwidOrderDescMap.get("PW_ORDER_TYPE");

		String pwOrderType = commonUtilsPW.getOrderTypePW(orderId);

		if ("OVERHAUL".equalsIgnoreCase(pwOrderType) || 
				"SUPPLEMENTAL".equalsIgnoreCase(pwOrderType) ||
				"SUB ORDER".equalsIgnoreCase(pwOrderType)){

			if (!user.hasPrivilege("PWUST_LONOTE_BYPASS")) {   //defect 1041
				if (StringUtils.isBlank(ucfOrderOperationVch2)){
					message.raiseError("MFI_20", "Sup Net cannot be blank");
				}
				//ucfOrderOperVch3
				if (StringUtils.isBlank(ucfOrderOperVch3)){
					message.raiseError("MFI_20", "Sub Net cannot be blank");
				}



				if (StringUtils.contains(ucfOrderOperationVch2, ",")){
					// 0120  , Front Bearing (1-2-3) Compartment
					String[] superiorNetSplit = StringUtils.split(ucfOrderOperationVch2, ",");
					if (superiorNetSplit!=null && superiorNetSplit.length>=1){
						ucfOrderOperationVch2= superiorNetSplit[0].trim();
					}
				}else{
					ucfOrderOperationVch2 = ucfOrderOperationVch2.trim();
				}

				if (StringUtils.contains(ucfOrderOperVch3, ",")){
					// 0120  , Front Bearing (1-2-3) Compartment
					String[] subNetSplit = StringUtils.split(ucfOrderOperVch3, ",");
					if (subNetSplit!=null && subNetSplit.length>=1){
						ucfOrderOperVch3= subNetSplit[0].trim();
					}
				}else{
					ucfOrderOperVch3 = ucfOrderOperVch3.trim();
				}

				confNumber = getConfNumber(ucfOrderOperationVch2, ucfOrderOperVch3,orderId);
			}
		}

		Super.insertAlteration(orderId, 
				operationNumber, 
				operationType, 
				operationOptionalFlag, 
				operationTitle, 
				plannedMachineNumber, 
				reworkFlag, 
				outSideProcessFlag, 
				supplierCode, 
				outSideProcessDays, 
				outSideProcessCostPerUnit, 
				autoStartFlag, 
				autoCompleteFlag, 
				assignedWorkLocation, 
				assignedWorkDepartment, 
				assignedWorkCenter, 
				planAssignedWorkLocation, 
				occurrenceRate, 
				testType, 
				ucfOrderOperationVch1, 
				ucfOrderOperationVch2, 
				ucfOrderOperationNumber1, 
				ucfOrderOperationFlag1, 
				ucfOrderOperationDate1,
				ucfOrderOperationNumber2, 
				moveToOfd, 
				ucfOrderOperVch3, 
				ucfOrderOperVch4, 
				ucfOrderOperVch5, 
				ucfOrderOperVch6, 
				ucfOrderOperVch7, 
				ucfOrderOperVch8, 
				ucfOrderOperVch9, 
				ucfOrderOperVch10, 
				ucfOrderOperVch11, 
				ucfOrderOperVch12, 
				ucfOrderOperVch13, 
				ucfOrderOperVch14, 
				ucfOrderOperVch15, 
				ucfOrderOperNum3, 
				ucfOrderOperNum4, 
				ucfOrderOperNum5, 
				ucfOrderOperDate2, 
				ucfOrderOperDate3, 
				ucfOrderOperDate4, 
				ucfOrderOperDate5, 
				ucfOrderOperFlag2, 
				ucfOrderOperFlag3, 
				ucfOrderOperFlag4, 
				ucfOrderOperFlag5, 
				ucfOrderOperVch2551, 
				ucfOrderOperVch2552, 
				ucfOrderOperVch2553, 
				ucfOrderOperVch40001,
				ucfOrderOperVch40002,
				stepsSequenceFlag, 
				operationChangeLevel, 
				unitProcessing, 
				sequenceNumber, 
				orientationFlag, 
				crossOrderFlag, 
				mustIssuePartsFlag, 
				calledFrom, 
				unitsPerCycle, 
				autoBatchFlag, 
				printLabel, 
				labelsPerCycle, 
				reportId, 
				reconcileScrap, 
				unitsPerCycleActual,
				batchFlag);

		if ("OVERHAUL".equalsIgnoreCase(pwOrderType) || 
				"SUPPLEMENTAL".equalsIgnoreCase(pwOrderType) ||
				"SUB ORDER".equalsIgnoreCase(pwOrderType)){

			String mask = serialDao.selectParameterValue(OPER_NUM_MASK,FOUNDATION);
			operationNumber = sfwidUndo.applyMaskToOperationNumber(operationNumber, mask);
			workOrderDaoPW.updateOperConfNumberByOperNo(orderId, operationNumber, -1, confNumber);	
		}

	}


	@SuppressWarnings("rawtypes")
	public String getConfNumber(String ucfOrderOperationVch2, String ucfOrderOperVch3,String orderId) {

		String confNumber = null;
		Map pwustSfwidOrderDescMap = getPwOrderMap(orderId);
		String salesOrder = (String) pwustSfwidOrderDescMap.get("SALES_ORDER");
		Map loMap = workOrderDaoPW.selectPwustSalesOrder(salesOrder, ucfOrderOperationVch2, ucfOrderOperVch3);

		if (loMap==null) {
			if (!(user.hasPrivilege("PWUST_LONOTE_BYPASS"))) {  // defect 1770 
				message.raiseError("MFI_20","Sales Order = " + salesOrder+
						" superNet = "+ ucfOrderOperationVch2 +
						" subNet = "+ ucfOrderOperVch3 + " Missing from Sales Order Table");
			}
		}else{
			confNumber = (String) loMap.get("CONFIRM_NO");

			if (StringUtils.isBlank(confNumber)){
				message.raiseError("MFI_20","Sales Order = " + salesOrder+
						" superNet = "+ ucfOrderOperationVch2 +
						" subNet = "+ ucfOrderOperVch3 + " CONFIRM_NO is null " );		    					
			}
		}
		return confNumber;
	}


	@SuppressWarnings("rawtypes")
	public Map getPwOrderMap(String orderId) {
		Map pwustSfwidOrderDescMap = workOrderDaoPW.selectPwustSfwidOrderDesc(orderId);
		if (null==pwustSfwidOrderDescMap){
			message.raiseError("MFI_20","Bad Order. Missing data in PWUST_SFWID_ORDER_DESC table");
		}
		return pwustSfwidOrderDescMap;
	}


	@SuppressWarnings("rawtypes")
	public void updateAlteration(String orderId,
			Number operationKey,
			Number stepKey,
			String operationType,
			String operationTitle,
			String plannedMachineNumber,
			String reworkFlag,
			String autoStartFlag,
			String autoCompleteFlag,
			String assignedWorkLocation,
			String assignedWorkDepartment,
			String assignedWorkCenter,
			String planAssignedWorkLocation,
			String operationOptionalFlag,
			Number occurrenceRate,
			String outSideProcessFlag,
			String supplierCode,
			Number outSideProcessDays,
			Number outSideProcessCostPerUnit,
			String testType,
			String ucfOrderOperationVch1,
			String ucfOrderOperationVch2,
			Number ucfOrderOperationNumber1,
			String ucfOrderOperationFlag1,
			Date ucfOrderOperationDate1,
			Number ucfOrderOperationNumber2,
			String ucfPlanOperationVch1,
			String ucfPlanOPerationVch2,
			String ucfPlanOperationVch3,
			String ucfPlanOperationVch4,
			String ucfPlanOperationVch5,
			Number ucfPlanOperationNumber1,
			Number ucfPlanOperationNumber2,
			String ucfPlanOperationFlag1,
			String ucfPlanOperationFlag2,
			String sapReference,
			String authority,
			String comments,
			String ucfPlanOperVch6,
			String ucfPlanOperVch7,
			String ucfPlanOperVch8,
			String ucfPlanOperVch9,
			String ucfPlanOperVch10,
			String ucfPlanOperVch11,
			String ucfPlanOperVch12,
			String ucfPlanOperVch13,
			String ucfPlanOperVch14,
			String ucfPlanOperVch15,
			Number ucfPlanOperNum3,
			Number ucfPlanOperNum4,
			Number ucfPlanOperNum5,
			Date ucfPlanOperDate1,
			Date ucfPlanOperDate2,
			Date ucfPlanOperDate3,
			Date ucfPlanOperDate4,
			Date ucfPlanOperDate5,
			String ucfPlanOperFlag3,
			String ucfPlanOperFlag4,
			String ucfPlanOperFlag5,
			String ucfPlanOperVch2551,
			String ucfPlanOperVch2552,
			String ucfPlanOperVch2553,
			String ucfPlanOperVch40001,
			String ucfPlanOperVch40002,
			String ucfOrderOperVch3,
			String ucfOrderOperVch4,
			String ucfOrderOperVch5,
			String ucfOrderOperVch6,
			String ucfOrderOperVch7,
			String ucfOrderOperVch8,
			String ucfOrderOperVch9,
			String ucfOrderOperVch10,
			String ucfOrderOperVch11,
			String ucfOrderOperVch12,
			String ucfOrderOperVch13,
			String ucfOrderOperVch14,
			String ucfOrderOperVch15,
			Number ucfOrderOperNum3,
			Number ucfOrderOperNum4,
			Number ucfOrderOperNum5,
			Date ucfOrderOperDate2,
			Date ucfOrderOperDate3,
			Date ucfOrderOperDate4,
			Date ucfOrderOperDate5,
			String ucfOrderOperFlag2,
			String ucfOrderOperFlag3,
			String ucfOrderOperFlag4,
			String ucfOrderOperFlag5,
			String ucfOrderOperVch2551,
			String ucfOrderOperVch2552,
			String ucfOrderOperVch2553,
			String ucfOrderOperVch40001,
			String ucfOrderOperVch40002,
			String stepsSequenceFlag,
			String operationChangeLevel,
			String unitProcessing,
			Number sequenceNumber,
			String orientationFlag,
			String crossOrderFlag,
			String mustIssuePartsFlag,
			String calledFrom,
			Number unitsPerCycle,
			String autoBatchFlag,
			String printLabel,
			Number labelsPerCycle,
			String reportId,
			String reconcileScrap, 
			Number unitsPerCycleActual, 
			String batchFlag){

		String confNumber = null;

		//Map pwustSfwidOrderDescMap = getPwOrderMap(orderId);
		//String pwOrderType = (String) pwustSfwidOrderDescMap.get("PW_ORDER_TYPE");

		String pwOrderType =commonUtilsPW.getOrderTypePW(orderId);

		if ("OVERHAUL".equalsIgnoreCase(pwOrderType)){

			//begin defect 1114
			Map sfwidOperDescMap = buyoffDaoPW.SelectSfwidOperDesc(orderId, operationKey,-1);
			String existingUcfOrderOperationVch2 = (String) sfwidOperDescMap.get("UCF_ORDER_OPER_VCH2");
			if (StringUtils.isNotEmpty(existingUcfOrderOperationVch2)) {
				String[] supArray = existingUcfOrderOperationVch2.split("\\s*,\\s*");
				existingUcfOrderOperationVch2 = supArray[0];
			}
			String existingUcfOrderOperationVch3 = (String) sfwidOperDescMap.get("UCF_ORDER_OPER_VCH3");
			if (StringUtils.isNotEmpty(existingUcfOrderOperationVch3)) {
				String[] subArray = existingUcfOrderOperationVch3.split("\\s*,\\s*");
				existingUcfOrderOperationVch3 = subArray[0];
			}

			String newucfOrderOperationVch2 = ucfOrderOperationVch2;
			if (StringUtils.isNotEmpty(ucfOrderOperationVch2)) {
				String[] newSupArray = (ucfOrderOperationVch2.split("\\s*,\\s*",-1));
				newucfOrderOperationVch2 = newSupArray[0];
			}
			String newucfOrderOperVch3 = ucfOrderOperVch3;
			if (StringUtils.isNotEmpty(ucfOrderOperVch3)) {
				String[] newSubArray = ucfOrderOperVch3.split("\\s*,\\s*",-1);
				newucfOrderOperVch3 = newSubArray[0];
			}

			//end defect 1114
			//note while doing defect 1114, can't find documentation on what the ucfOrderOperationFlag1 is
			if (StringUtils.equalsIgnoreCase(ucfOrderOperationFlag1, "A")){
				// oper was added via WO alt
				if (StringUtils.isBlank(ucfOrderOperationVch2)){
					message.raiseError("MFI_20", "Sup Net cannot be blank");
				}
				if (StringUtils.isBlank(ucfOrderOperVch3)){
					message.raiseError("MFI_20", "Sub Net cannot be blank");
				}
			} /* begin defect 1854 allow them to change whenever by removing check
			else{ 

				if (!StringUtils.isBlank(ucfOrderOperationVch2)
				&&(!StringUtils.equals(existingUcfOrderOperationVch2, newucfOrderOperationVch2)
				&&(StringUtils.isNotEmpty(existingUcfOrderOperationVch2)))){ //defect 1114 add check to see if not the same as what is stored already
					message.raiseError("MFI_20", "Cannot add/change Sup Net to an oper that exists in plan that order was created against");
				}
				if (!StringUtils.isBlank(ucfOrderOperVch3)
				&&(!StringUtils.equals(existingUcfOrderOperationVch3, newucfOrderOperVch3)
				&&(StringUtils.isNotEmpty(existingUcfOrderOperationVch2)))){ //defect 1114 add check to see if not the same as what is stored already
					message.raiseError("MFI_20", "Cannot add/change Sub Net to an oper that exists in plan that order was created against");
				}
				if (StringUtils.isEmpty(ucfOrderOperationVch2)
					&& (StringUtils.isNotEmpty(existingUcfOrderOperationVch2))) {
					message.raiseError("MFI_20", "Cannot add/change Sup Net to an oper that exists in plan that order was created against");
				}
				if (StringUtils.isEmpty(ucfOrderOperVch3)
						&& (StringUtils.isNotEmpty(existingUcfOrderOperationVch3))) {
						message.raiseError("MFI_20", "Cannot add/change Sup Net to an oper that exists in plan that order was created against");
					}

			} 
               //end defect 1854 */ 



			//defect 1854
			//if the existing was not blank and it gets changed to blank then throw error
			if (!user.hasPrivilege("PWUST_LONOTE_BYPASS")) { 
				if (StringUtils.isBlank(ucfOrderOperationVch2) && StringUtils.isNotBlank(existingUcfOrderOperationVch2)) {
					message.raiseError("MFI_20", "SuperNet cannot be blank");
				}
				if (StringUtils.isBlank(ucfOrderOperVch3) && StringUtils.isNotBlank(existingUcfOrderOperationVch3)) {
					message.raiseError("MFI_20", "Sub Net cannot be blank");
				}
			}
			if (StringUtils.contains(ucfOrderOperationVch2, ",")){
				// 0120  , Front Bearing (1-2-3) Compartment
				String[] superiorNetSplit = StringUtils.split(ucfOrderOperationVch2, ",");
				if (superiorNetSplit!=null && superiorNetSplit.length>=1){
					ucfOrderOperationVch2= superiorNetSplit[0].trim();
				}
			} else if (ucfOrderOperationVch2 != null) { // Defect 848
				ucfOrderOperationVch2 = ucfOrderOperationVch2.trim();
			}

			if (StringUtils.contains(ucfOrderOperVch3, ",")){
				// 0120  , Front Bearing (1-2-3) Compartment
				String[] subNetSplit = StringUtils.split(ucfOrderOperVch3, ",");
				if (subNetSplit!=null && subNetSplit.length>=1){
					ucfOrderOperVch3= subNetSplit[0].trim();
				}
			} else if (ucfOrderOperVch3 != null) { // Defect 848
				ucfOrderOperVch3 = ucfOrderOperVch3.trim();
			}

			confNumber = getConfNumber(ucfOrderOperationVch2, ucfOrderOperVch3,orderId);



		}else if ("SUB ORDER".equalsIgnoreCase(pwOrderType) || "SUPPLEMENTAL".equalsIgnoreCase(pwOrderType)){
			if (StringUtils.isBlank(ucfOrderOperationVch2)){
				message.raiseError("MFI_20", "Sup Net cannot be blank");
			}
			if (StringUtils.isBlank(ucfOrderOperVch3)){
				message.raiseError("MFI_20", "Sub Net cannot be blank");
			}
			if (StringUtils.contains(ucfOrderOperationVch2, ",")){
				// 0120  , Front Bearing (1-2-3) Compartment
				String[] superiorNetSplit = StringUtils.split(ucfOrderOperationVch2, ",");
				if (superiorNetSplit!=null && superiorNetSplit.length>=1){
					ucfOrderOperationVch2= superiorNetSplit[0].trim();
				}
			}else{
				ucfOrderOperationVch2 = ucfOrderOperationVch2.trim();
			}

			if (StringUtils.contains(ucfOrderOperVch3, ",")){
				// 0120  , Front Bearing (1-2-3) Compartment
				String[] subNetSplit = StringUtils.split(ucfOrderOperVch3, ",");
				if (subNetSplit!=null && subNetSplit.length>=1){
					ucfOrderOperVch3= subNetSplit[0].trim();
				}
			}else{
				ucfOrderOperVch3 = ucfOrderOperVch3.trim();
			}
			confNumber = getConfNumber(ucfOrderOperationVch2, ucfOrderOperVch3,orderId);
		}


		Super.updateAlteration(orderId, 
				operationKey, 
				stepKey, 
				operationType, 
				operationTitle, 
				plannedMachineNumber, 
				reworkFlag, 
				autoStartFlag, 
				autoCompleteFlag, 
				assignedWorkLocation, 
				assignedWorkDepartment, 
				assignedWorkCenter, 
				planAssignedWorkLocation, 
				operationOptionalFlag, 
				occurrenceRate, 
				outSideProcessFlag, 
				supplierCode, 
				outSideProcessDays, 
				outSideProcessCostPerUnit, 
				testType, 
				confNumber,//ucfOrderOperationVch1, 
				ucfOrderOperationVch2, 
				ucfOrderOperationNumber1, 
				ucfOrderOperationFlag1,
				ucfOrderOperationDate1, 
				ucfOrderOperationNumber2, 
				ucfPlanOperationVch1, 
				ucfPlanOPerationVch2, 
				ucfPlanOperationVch3, 
				ucfPlanOperationVch4, 
				ucfPlanOperationVch5, 
				ucfPlanOperationNumber1, 
				ucfPlanOperationNumber2, 
				ucfPlanOperationFlag1, 
				ucfPlanOperationFlag2,
				sapReference, 
				authority, 
				comments, 
				ucfPlanOperVch6,
				ucfPlanOperVch7, 
				ucfPlanOperVch8, 
				ucfPlanOperVch9, 
				ucfPlanOperVch10, 
				ucfPlanOperVch11,
				ucfPlanOperVch12, 
				ucfPlanOperVch13, 
				ucfPlanOperVch14, 
				ucfPlanOperVch15, 
				ucfPlanOperNum3, 
				ucfPlanOperNum4, 
				ucfPlanOperNum5, 
				ucfPlanOperDate1, 
				ucfPlanOperDate2, 
				ucfPlanOperDate3, 
				ucfPlanOperDate4, 
				ucfPlanOperDate5, 
				ucfPlanOperFlag3, 
				ucfPlanOperFlag4, 
				ucfPlanOperFlag5, 
				ucfPlanOperVch2551, 
				ucfPlanOperVch2552, 
				ucfPlanOperVch2553, 
				ucfPlanOperVch40001,
				ucfPlanOperVch40002, 
				ucfOrderOperVch3, 
				ucfOrderOperVch4, 
				ucfOrderOperVch5,
				ucfOrderOperVch6, 
				ucfOrderOperVch7, 
				ucfOrderOperVch8,
				ucfOrderOperVch9, 
				ucfOrderOperVch10,
				ucfOrderOperVch11, 
				ucfOrderOperVch12,
				ucfOrderOperVch13,
				ucfOrderOperVch14,
				ucfOrderOperVch15, 
				ucfOrderOperNum3, 
				ucfOrderOperNum4,
				ucfOrderOperNum5,
				ucfOrderOperDate2,
				ucfOrderOperDate3,
				ucfOrderOperDate4,
				ucfOrderOperDate5, 
				ucfOrderOperFlag2,
				ucfOrderOperFlag3, 
				ucfOrderOperFlag4,
				ucfOrderOperFlag5, 
				ucfOrderOperVch2551,
				ucfOrderOperVch2552,
				ucfOrderOperVch2553, 
				ucfOrderOperVch40001,
				ucfOrderOperVch40002,
				stepsSequenceFlag, 
				operationChangeLevel,
				unitProcessing,
				sequenceNumber,
				orientationFlag, 
				crossOrderFlag,
				mustIssuePartsFlag,
				calledFrom,
				unitsPerCycle,
				autoBatchFlag, 
				printLabel,
				labelsPerCycle,
				reportId, 
				reconcileScrap, 
				unitsPerCycleActual,
				batchFlag);
	}

	//Defect 805
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List selectOrderToOrderOperCopy(String targetOperation,
			Number incrementBy,
			String mroFlag,
			Number executionOrder,
			String operationNumberList,
			String fromOrderNumber)
	{
		List returnList = operationDao.selectOrderToOrderOperCopy(targetOperation, 
				incrementBy, 
				mroFlag, 
				executionOrder, 
				operationNumberList, 
				fromOrderNumber);

		if (returnList.isEmpty()) {
			Map retMap = new HashMap();
			retMap.put("TO_OPER_TITLE", null);
			retMap.put("FROM_OPER_NO", null);
			retMap.put("TO_OPER_NO", null);
			retMap.put("HAS_PL", null);
			retMap.put("COPY_PL", null);
			retMap.put("STD_OPER_FLAG", null);
			retMap.put("EXE_ORDER", (Number) null);
			retMap.put("SECURITY_GROUP", null);
			retMap.put("UCF_ORDER_OPER_VCH2", null);
			retMap.put("UCF_ORDER_OPER_VCH3", null);

			returnList.add(retMap);
		} else {
			String mask = serialDao.selectParameterValue(OPER_NUM_MASK, FOUNDATION);

			for (int i = 0; i < returnList.size(); i++) {
				Map retMap = (Map) returnList.get(i);

				String toOperationNumber = (String) retMap.get("TO_OPER_NO");

				// FND-19205 : Apply the mask to operation number.
				toOperationNumber = sfwidUndo.applyMaskToOperationNumber(toOperationNumber, mask);

				retMap.put("TO_OPER_NO", toOperationNumber);
			}
		}

		return returnList;
	}







	//begin defect 1767
	@Override
	public String removeMfgCollectedBuyoffUnits(String orderId, Number operationKey, Number stepKey, String buyoffId,
			String unitList, String buyoffStatus) {



		String serialList = Super.removeMfgCollectedBuyoffUnits(orderId, operationKey, stepKey, buyoffId, unitList, buyoffStatus);  


		return unitList; // defect 1776
	}
	//end defect 1767

}
