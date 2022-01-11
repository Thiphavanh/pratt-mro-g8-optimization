/*
 *  This unpublished work, first created in 2018 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2018,2019,2020,2021.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    SfplSubjectImplPW.java
 * 
 *  Created: 2018-10-01
 * 
 *  Author:  N.Gnassounou
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2018-10-01      F. Ettefagh    Initial Release Defect 980 Cannot Create Suborder due to supNet subNet and conf Numbers
 *  2019-03-26    	B. Preston	   SMRO_PLG_305 - Defect 1147 insert into task group extension table on copy if not already there.
 *  2020-01-17      Fred Ettefagh  SMRO_PLG_205 - Defect 1097 Fixed bug for inserting into PWUST_SFOR_SFPL_PLAN_SUBJECT when calling OOB to copy tasks from a plan or an order
 *  2020-01-30      Fred ETtefagh  SMRO_WOE_204 - Defect 1501 changed method copyTaskGroup to get toPlanUpdateNumber from sfwid_order_desc if plan key data is null
 *  2020-02-18		B. Polak	   Defect 1097 - when copying task groups between plans task part number must be the same as plan part number
 *  2021-01-20      J DeNinno      Defect 1829  - when copying task groups from work order to plans the part number from the plan of that work order must be copied or default to work order part
 *  2021-02-03      S. Edgerly     Defect 1859  - Null pointer exception on create sub-order.
 */
package com.pw.solumina.sfor.application.impl;

import static com.ibaset.common.DbColumnNameConstants.OPER_KEY;
import static com.ibaset.common.DbColumnNameConstants.OPER_NO;
import static com.ibaset.common.DbColumnNameConstants.OPER_UPDT_NO;
import static com.ibaset.common.DbColumnNameConstants.PART_CHG;
import static com.ibaset.common.DbColumnNameConstants.PART_NO_KEY;
import static com.ibaset.common.DbColumnNameConstants.SUBJECT_NO;
import static com.ibaset.common.FrameworkConstants.NO;
import static com.ibaset.common.FrameworkConstants.YES;
import static com.ibaset.common.SoluminaConstants.AUTO_PARALLEL;
import static com.ibaset.common.SoluminaConstants.AUTO_SERIAL;
import static com.ibaset.common.SoluminaConstants.COMMA;
import static com.ibaset.common.SoluminaConstants.COPY;
import static com.ibaset.common.SoluminaConstants.COPY_TASK_GROUP_FROM_ORDER;
import static com.ibaset.common.SoluminaConstants.COPY_TASK_GROUP_FROM_PLAN;
import static com.ibaset.common.SoluminaConstants.COPY_TASK_GROUP_PART;
import static com.ibaset.common.SoluminaConstants.DISPOSITION_APPEND;
import static com.ibaset.common.SoluminaConstants.EXCLUDED;
import static com.ibaset.common.SoluminaConstants.EXECUTION_ORDER;
import static com.ibaset.common.SoluminaConstants.FLOW_DIAGRAM;
import static com.ibaset.common.SoluminaConstants.MRO_UPGRADE;
import static com.ibaset.common.SoluminaConstants.OPERATION;
import static com.ibaset.common.SoluminaConstants.OPER_INS;
import static com.ibaset.common.SoluminaConstants.ORDER;
import static com.ibaset.common.SoluminaConstants.PART;
import static com.ibaset.common.SoluminaConstants.PLAN;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.StringUtils.defaultIfEmpty;
import static org.apache.commons.lang.StringUtils.equalsIgnoreCase;
import static org.apache.commons.lang.StringUtils.indexOf;
import static org.apache.commons.lang.StringUtils.substring;
import static org.apache.commons.lang.StringUtils.upperCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.solumina.IValidator;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.application.IHtReferenceText;
import com.ibaset.solumina.sffnd.application.IParse;
import com.ibaset.solumina.sfor.application.IExecutionOrderNode;
import com.ibaset.solumina.sfor.application.ISfplSubject;
import com.ibaset.solumina.sfor.dao.ISfplPlanDao;
import com.ibaset.solumina.sfor.dao.ISfplSubjectDao;
import com.ibaset.solumina.sfor.dao.ISfwidSubjectDao;
import com.ibaset.solumina.sfor.dao.IWidSubjectDao;
import com.ibaset.solumina.sfpl.application.IOperation;
import com.ibaset.solumina.sfpl.application.IPlanOfd;
import com.ibaset.solumina.sfpl.dao.IPlanDao;
import com.ibaset.solumina.sfwid.application.IAlterationGet;
import com.ibaset.solumina.sfwid.application.IHold;
import com.ibaset.solumina.sfwid.application.IOfdLink;
import com.ibaset.solumina.sfwid.application.IOperationAlteration;
import com.ibaset.solumina.sfwid.application.IOrderDetails;
import com.ibaset.solumina.sfwid.dao.IHoldDao;
import com.ibaset.solumina.sfwid.dao.IOperationDao;
import com.ibaset.solumina.sfwid.dao.IOrderDao;
import com.pw.common.dao.CommonDaoPW;
import com.pw.solumina.sfpl.application.impl.PlanDaoImplPW;
import com.pw.solumina.sfor.dao.TaskGroupsDaoPW;


public abstract class SfplSubjectImplPW extends ImplementationOf<ISfplSubject> implements ISfplSubject {


	@Reference	protected PlanDaoImplPW planDaoImplPW = null; // Defect 1147
	@Reference	ISfplSubjectDao sfplSubjectDao = null; // Defect 1147
	@Reference  private IParse parse = null; // Defect 1147
	@Reference	private IMessage message = null;
	@Reference	CommonDaoPW  commonDaoPW = null;
	@Reference  TaskGroupsDaoPW taskGroupsDaoPW;
	@Reference
	private Log logger = LogFactory.getLog(this.getClass());

	@Reference
	private IValidator validator;
	@Reference
	private IOperation sfplOperation = null;
	@Reference
	private IHtReferenceText htReferenceText = null;
	@Reference
	private IOperationDao operationDao = null;

	@Reference
	private IPlanOfd planOfd;
	@Reference
	private com.ibaset.solumina.sfwid.application.IOperation sfwidOperation; 
	@Reference
	private IEvent event;
	@Reference
	private IOrderDao orderDao;
	@Reference
	private IOfdLink ofdLink;
	@Reference
	private IOperationAlteration operationAlteration;

	@Reference
	private ISfwidSubjectDao sfwidSubjectDao = null;
	@Reference
	private IPlanDao planDao;
	@Reference
	private com.ibaset.solumina.sfpl.dao.IOperationDao sfplOperationDao = null;
	@Reference
	private ISfplPlanDao sfplPlanDao = null;
	@Reference
	private IWidSubjectDao widSubjectDao = null;
	@Reference
	private IHoldDao holdDao = null;
	@Reference
	private IHold hold = null;

	@Reference 
	private IOrderDetails orderDetails = null; 
	@Reference
	private IExecutionOrderNode executionOrderNode;
	@Reference
	private IAlterationGet alterationGet;

	// defect 980
	@Override
	public void copyTaskGroup ( String fromPlanId, 
			Number fromPlanVersion,
			Number fromPlanRevision,
			Number fromPlanAlteration,
			String toPlanId,
			Number toPlanVersion,
			Number toPlanRevision,
			Number toPlanAlteration,
			String fromOrderId,
			String toOrderId,
			String source,
			String destination,
			String fromTaskGroupNumberList ){


		String planType=null;
		Number toPlanUpdateNumber = null;
		//1097
		String partNumber = null;
		String partChg = null;

		// Defect 1501
		if (StringUtils.isBlank(toPlanId)){
			// get plan key data using toOrderId
			List  list = commonDaoPW.selectPWOrderDataByOrderId(toOrderId);
			if (list==null || list.isEmpty()){
				message.raiseError("MFI_20","Error getting no data from selecting sfwid_order_desc, pwust_sfwid_order_Desc. orderid = " + toOrderId);
			}else{
				if (list.size() ==1){
					Map map = (Map)list.get(0);
					toPlanId = (String) map.get("PLAN_ID");
					toPlanUpdateNumber = (Number) map.get("PLAN_UPDT_NO");

					//1097
					partNumber = (String) map.get("PART_NO");
					partChg = (String) map.get("PART_CHG");

				}else{
					message.raiseError("MFI_20","Error getting too many rows from selecting sfwid_order_desc, pwust_sfwid_order_Desc. orderid = " + toOrderId);
				}
			}
		}else{
			List SfplplanVList = planDaoImplPW.selectSfplplanV(toPlanId, toPlanVersion, toPlanRevision, toPlanAlteration);
			if (SfplplanVList!=null && !SfplplanVList.isEmpty() && SfplplanVList.size() ==1){
				Map map = (Map)SfplplanVList.get(0);
				planType = (String)map.get("PLAN_TYPE");
				toPlanUpdateNumber = (Number)map.get("PLAN_UPDT_NO");

				//1097
				partNumber = (String) map.get("PART_NO");
				partChg = (String) map.get("PART_CHG");

			}else{
				message.raiseError("MFI_20","Error getting selecting from sfpl_plan_v");
			}
		}
		// Defect 1501

		try{

			if (StringUtils.isNotBlank(fromOrderId)){
				ContextUtil.getUser().getContext().set("PWUST_ORDER_TASK_COPY_OPER_CONF_UPD","TRUE");
			}
		//begin defect 1829 this is mostly a copy of the supercopyTaskGroup
		if (StringUtils.equalsIgnoreCase(planType, "REPAIR") && StringUtils.equals(source, ORDER)){ //defect 1859
			copyTaskGroupPWRepair ( fromPlanId, 
					fromPlanVersion,
					fromPlanRevision,
					fromPlanAlteration,
					toPlanId,
					toPlanVersion,
					toPlanRevision,
					toPlanAlteration,
					fromOrderId,
					toOrderId,
					source,
					destination,
					fromTaskGroupNumberList,
					planType);
			}
			else //defect 1829, 1859
			{ 
				Super.copyTaskGroup(fromPlanId, 
						fromPlanVersion, 
						fromPlanRevision, 
						fromPlanAlteration, 
						toPlanId, 
						toPlanVersion, 
						toPlanRevision, 
						toPlanAlteration, 
						fromOrderId, 
						toOrderId, 
						source, 
						destination, 
						fromTaskGroupNumberList);
			}
		}finally {
			ContextUtil.getUser().getContext().set("PWUST_ORDER_TASK_COPY_OPER_CONF_UPD","");			
		}

		planDaoImplPW.insertTaskGroupExtension(toPlanId, toPlanUpdateNumber);

		if (StringUtils.equalsIgnoreCase(planType, "OVERHAUL")){
			// defect 1097, overhaul plans only
			// task part number must be the same as plan part number
			Map sfplItemDescMasterAllMap = planDaoImplPW.selectSfplItemDescMasterAll(partNumber, partChg);
			planDaoImplPW.updateOverHaulPlanSubjectItemID(toPlanId,
					toPlanUpdateNumber, 
					(String)sfplItemDescMasterAllMap.get("PART_NO"), 
					(String)sfplItemDescMasterAllMap.get("PART_CHG"), 
					(String)sfplItemDescMasterAllMap.get("PART_TITLE"), 
					(String)sfplItemDescMasterAllMap.get("ITEM_ID"));
		}
	}


	//begin defect 1829
	public void copyTaskGroupPWRepair ( String fromPlanId, 
			Number fromPlanVersion,
			Number fromPlanRevision,
			Number fromPlanAlteration,
			String toPlanId,
			Number toPlanVersion,
			Number toPlanRevision,
			Number toPlanAlteration,
			String fromOrderId,
			String toOrderId,
			String source,
			String destination,
			String fromTaskGroupNumberList,
			String planType){

		// Task Group Number can not be blank.
		//	validator.checkMandatoryField("Task Group No", fromTaskGroupNumberList);

		//FND-27688 raiseError for MRO-Upgrade order ,if from/to Item Item Type does not match.
		upgradeItemTypeNotMatchErrorRaise(fromPlanId, 
				fromPlanVersion, 
				fromPlanRevision, 
				fromPlanAlteration, 
				toPlanId, 
				toPlanVersion, 
				toPlanRevision, 
				toPlanAlteration, 
				fromOrderId, 
				toOrderId,
				null,
				null, 
				COPY_TASK_GROUP_PART,  
				fromTaskGroupNumberList);

		String[] fromTaskGroupNumbers = parse.parse(fromTaskGroupNumberList,
				COMMA,
				false);

		Number[] toTaskGroupNumbers = new Number[fromTaskGroupNumbers.length];

		Number[] fromTaskGroupNumberArray = new Number[fromTaskGroupNumbers.length];

		for(int i = 0;i < fromTaskGroupNumbers.length; i++)
		{
			fromTaskGroupNumberArray[i] = NumberUtils.createNumber(fromTaskGroupNumbers[i]);
		}

		Number fromPlanUpdateNumber = null;
		Number toPlanUpdateNumber = null;
		String userName = ContextUtil.getUsername();
		List<Map<String,Object>> taskGroupOperations = null;
		List<Map<String,Object>> sourceTaskGroupNumbers = new ArrayList<>();
		Number[][] taskGroupMapping = new Number[fromTaskGroupNumberArray.length][2];
		String fromTaskGroupNumberString = getCommaSeparatedString(fromTaskGroupNumberArray);

		// If Source is a Plan
		if(equalsIgnoreCase(source, PLAN))
		{  
			// Selects the Plan Update Number
			fromPlanUpdateNumber = sfplSubjectDao.selectPlanUpdateNumber( fromPlanId, 
					fromPlanVersion, 
					fromPlanRevision, 
					fromPlanAlteration);

			// Select all the Operations of the Task Group
			taskGroupOperations = sfplSubjectDao.selectTaskGroupOperations( fromPlanId, 
					fromPlanVersion, 
					fromPlanRevision, 
					fromPlanAlteration, 
					fromTaskGroupNumberArray);

			sourceTaskGroupNumbers = sfplSubjectDao.selectPlanSubjects(fromPlanId, fromPlanUpdateNumber, // PBL-2597
					fromTaskGroupNumberString);
		}
		// If Source is an Order 
		else if(equalsIgnoreCase(source, ORDER))
		{
			taskGroupOperations = sfplSubjectDao.selectTaskGroupOperations( fromOrderId, 
					fromTaskGroupNumberArray, 
					NumberUtils.INTEGER_MINUS_ONE);

			sourceTaskGroupNumbers = sfplSubjectDao.selectOrderSubjects(fromOrderId, fromTaskGroupNumberString); // PBL-2597
		}

		String alterationId = EMPTY;
		String alterationType = EMPTY;

		if(equalsIgnoreCase(destination, PLAN))
		{

			toPlanUpdateNumber = planOfd.startPlanUpdate(toPlanId,
					"0",
					PART,
					"0",
					"0",
					"0",
					toPlanVersion,
					toPlanRevision,
					toPlanAlteration,
					null,
					null,
					null,
					null, 
					null);

			// Selects the Plan Update Number
			//		    		toPlanUpdateNumber = sfplSubjectDao.selectPlanUpdateNumber( toPlanId, 
			//		    				                                                    toPlanVersion, 
			//		    				                                                    toPlanRevision, 
			//		    				                                                    toPlanAlteration);



		}

		else if(equalsIgnoreCase(destination, ORDER))
		{
			// Get the Alteration Type
			alterationType = htReferenceText.getType(toOrderId);
			if(equalsIgnoreCase(alterationType, upperCase(OPERATION)))
			{
				// FND-13152 Raise an error if the alteration type is "OPERATION"
				message.raiseError("MFI_1DF05060E12D18B3E0440003BA041A64");
			}
			// Get the Alteration Id for destination work order
			alterationId = htReferenceText.getAlteration(toOrderId);

			// Checks whether Alteration Description exists or not.
			boolean alterationDescriptionExists = orderDao.selectAlterationDescriptionExists(alterationId,
					DISPOSITION_APPEND);

			// Raise an error when Alteration Description exists.
			if (alterationDescriptionExists)
			{
				// Cannot edit work order because open Discrepancy Item with
				// Disposition Instructions exist.
				message.raiseError("MFI_81950");
			}
		}

		// For all the task groups
		for(int i = 0;i < fromTaskGroupNumberArray.length; i++)
		{
			if(equalsIgnoreCase(destination, PLAN))
			{
				toTaskGroupNumbers[i] = fromTaskGroupNumberArray[i];
				// Checks whether the task group number is already there in destination plan
				boolean taskGroupNumberExists = sfplSubjectDao.selectTaskGroupNumberExists( fromTaskGroupNumberArray[i], 
						toPlanId, 
						toPlanVersion, 
						toPlanRevision, 
						toPlanAlteration);
				// If task group number already exists
				if(taskGroupNumberExists)
				{
					// Get new Task Group Number
					toTaskGroupNumbers[i] = sfplSubjectDao.selectNextTaskGroupNumber( toPlanId, 
							toPlanVersion, 
							toPlanRevision, 
							toPlanAlteration);
				}


				// Copy Task From Plan to Plan
				if(equalsIgnoreCase(source, PLAN))
				{
					// Copy Task Group
					sfplSubjectDao.insertPlanSubject( toPlanId, 
							toPlanUpdateNumber, 
							toTaskGroupNumbers[i], 
							NumberUtils.INTEGER_ONE, 
							userName, 
							fromPlanId, 
							fromPlanUpdateNumber, 
							fromTaskGroupNumberArray[i]);
				}
				else if(equalsIgnoreCase(source, ORDER))
				{
					// Copy Task Group
					sfplSubjectDao.insertPlanSubject( toPlanId, 
							toPlanUpdateNumber, 
							toTaskGroupNumbers[i], 
							NumberUtils.INTEGER_ONE, 
							userName, 
							fromOrderId, 
							fromTaskGroupNumberArray[i]);

				}
			}
			else if(equalsIgnoreCase(destination, ORDER))
			{
				toTaskGroupNumbers[i] = fromTaskGroupNumberArray[i];
				// Checks whether the task group number is already there in destination Order
				boolean taskGroupNumberExists = sfplSubjectDao.selectTaskGroupNumberExists( fromTaskGroupNumberArray[i], 
						toOrderId );
				// If task group number already exists
				if(taskGroupNumberExists)
				{
					// Get new Task Group Number
					toTaskGroupNumbers[i] = sfplSubjectDao.selectNextTaskGroupNumber( toOrderId );
				}
				// Copy Task From Plan to Order
				if(equalsIgnoreCase(source, PLAN))
				{
					// Copy Task Group
					sfplSubjectDao.insertPlanSubject( toOrderId, 
							toTaskGroupNumbers[i], 
							NumberUtils.INTEGER_ONE, 
							userName, 
							EXCLUDED, 
							NO, 
							fromPlanId, 
							fromPlanUpdateNumber, 
							fromTaskGroupNumberArray[i], 
							alterationId);
				}
				else if(equalsIgnoreCase(source, ORDER))
				{
					// Copy Task Group
					sfplSubjectDao.insertPlanSubject( toOrderId, 
							toTaskGroupNumbers[i], 
							NumberUtils.INTEGER_ONE, 
							userName, 
							EXCLUDED, 
							NO, 
							fromOrderId, 
							fromTaskGroupNumberArray[i], 
							alterationId);
				}
			}

			// Create a mapping of old and new task group numbers
			taskGroupMapping[i][0] = fromTaskGroupNumberArray[i];
			taskGroupMapping[i][1] = toTaskGroupNumbers[i];
		}

		List operationsToCopy = new ArrayList();
		Iterator taskGroupOperationIterator = taskGroupOperations.listIterator();

		List<Map<String,Object>> destinationOperationList = new ArrayList<>();
		Set<String> destinationOperationSet = new HashSet<>();
		if(equalsIgnoreCase(destination, ORDER)) {
			destinationOperationList = sfplSubjectDao.selectOrderOperations(toOrderId);

			for(Map<String,Object> operationMap : destinationOperationList) {
				destinationOperationSet.add((String) operationMap.get(OPER_NO));
			}
		}

		// For all the Operations
		while(taskGroupOperationIterator.hasNext())
		{
			Map taskGroupOperationMap = (Map) taskGroupOperationIterator.next();

			Number operationKey = (Number)taskGroupOperationMap.get("OPER_KEY");
			String operationNumber = (String)taskGroupOperationMap.get("OPER_NO");
			String operationTitle = (String)taskGroupOperationMap.get("OPER_TITLE");
			Number operationUpdtNumber = (Number)taskGroupOperationMap.get("OPER_UPDT_NO"); // FND-14800
			Number sequenceNumber = (Number)taskGroupOperationMap.get("EXE_ORDER"); // FND-18129

			String operationNumberTemp = operationNumber;
			String toOperationNumber = operationNumberTemp;
			boolean newOperationNumberExists = false;
			boolean continueOperNumberCheck  = true;
			int count = 0;

			while(continueOperNumberCheck)
			{
				if(equalsIgnoreCase(destination, PLAN))
				{
					// Checks if Operation Number already exists in destination plan
					newOperationNumberExists = sfplSubjectDao.selectOperationNumberExists( operationNumberTemp, 
							toPlanId, 
							toPlanVersion, 
							toPlanRevision, 
							toPlanAlteration);
					if (!newOperationNumberExists)
					{
						newOperationNumberExists =  operationExistsInCopyList(operationsToCopy,operationNumberTemp);
					}

				}
				else if(equalsIgnoreCase(destination, ORDER))
				{	
					newOperationNumberExists = destinationOperationSet.contains(operationNumberTemp); // PBL-384

				}

				// If operation number exists
				if(newOperationNumberExists)
				{
					count = count + 1;

					// FND-12759
					// Find the index of '-' in Operation No
					int dash = indexOf(toOperationNumber, "-");

					// If '-' is not there
					if(dash == -1)
					{
						// Substring the whole operation number
						dash = toOperationNumber.length();

						// Truncate the length if it is crossing the 
						// Operation Number datatype length (10)
						if(dash > 8)
						{
							dash = 8;
						}
					}

					operationNumberTemp = substring(toOperationNumber,0,dash) + "-" + count;
				}
				else
				{
					continueOperNumberCheck = false;
					toOperationNumber = operationNumberTemp;
					destinationOperationSet.add(toOperationNumber); // PBL-384
				}
			}

			Map operationsToCopyMap = new HashMap();
			operationsToCopyMap.put("FROM_OPER_KEY", operationKey);
			operationsToCopyMap.put("TO_OPER_NO", toOperationNumber);
			operationsToCopyMap.put("FROM_OPER_NO", operationNumber);
			operationsToCopyMap.put("OPER_TITLE", operationTitle);
			operationsToCopyMap.put("FROM_OPER_UPDT_NO", operationUpdtNumber);
			operationsToCopyMap.put("FROM_EXE_ORDER", sequenceNumber);

			operationsToCopy.add(operationsToCopyMap);
		}

		List operationMapping = new ArrayList();

		// FND-17329
		// Fetch the Display Sequence. Default to Auto  Parallel
		String displaySequence = null;
		String toOrderDisplaySequence = null;
		if(equalsIgnoreCase(destination, PLAN))
		{
			try
			{
				// Select display sequence
				displaySequence = planDao.selectPlanDisplaySequence(toPlanId, 
						toPlanUpdateNumber);
			}
			catch(EmptyResultDataAccessException ex)
			{
				logger.debug(ex.getMessage(), ex);
			}
		}
		else if(equalsIgnoreCase(destination, ORDER))
		{
			toOrderDisplaySequence = orderDao.selectDisplaySequence(toOrderId); // PBL-385
			// FND-18129
			// Do not Move to OFD Based on Display Sequence
			// As by Default the Operation will be in EXCLUDED Status.
		}

		displaySequence = defaultIfEmpty(displaySequence, AUTO_PARALLEL);

		Iterator operationsIterator = operationsToCopy.listIterator();

		while(operationsIterator.hasNext())
		{
			Map operationsMap = (Map)operationsIterator.next();
			Number fromOperationKey = (Number)operationsMap.get("FROM_OPER_KEY");
			String toOperationNumber = (String)operationsMap.get("TO_OPER_NO");
			String fromOperationNumber = (String)operationsMap.get("FROM_OPER_NO");
			String operationTitle = (String)operationsMap.get("OPER_TITLE");
			Number fromSequenceNumber = (Number)operationsMap.get("FROM_EXE_ORDER");

			if(equalsIgnoreCase(destination, PLAN))
			{
				if(equalsIgnoreCase(source, PLAN))
				{
					// copy operation from plan to plan
					sfplOperation.copyOperation( fromPlanId, 
							fromPlanVersion, 
							fromPlanRevision, 
							fromPlanAlteration, 
							fromOperationNumber, 
							toPlanId, 
							toPlanVersion, 
							toPlanRevision, 
							toPlanAlteration, 
							toOperationNumber, 
							YES, 
							YES, 
							operationTitle, 
							COPY,
							null,
							fromSequenceNumber);

					Map newOperationKeyMap = sfplSubjectDao.selectOperationKey( toOperationNumber, 
							toPlanId, 
							toPlanVersion, 
							toPlanRevision, 
							toPlanAlteration );

					Number newOperationKey = (Number)newOperationKeyMap.get("OPER_KEY");
					Number newOperationUpdateNumber = (Number)newOperationKeyMap.get("OPER_UPDT_NO");

					operationsMap.put("NEW_OPER_UPDT_NO", newOperationUpdateNumber);
					operationsMap.put("TO_OPER_KEY", newOperationKey);
					operationMapping.add(operationsMap);
				}
				else if(equalsIgnoreCase(source, ORDER))
				{
					// copy operation from order to plan
					sfplOperation.copyOperationFromWid( fromOrderId, 
							fromOperationNumber, 
							toPlanId, 
							toPlanVersion, 
							toPlanRevision, 
							toPlanAlteration, 
							toOperationNumber, 
							YES, 
							operationTitle,
							fromSequenceNumber);

					Map newOperationKeyMap = sfplSubjectDao.selectOperationKey( toOperationNumber, 
							toPlanId, 
							toPlanVersion, 
							toPlanRevision, 
							toPlanAlteration );

					Number newOperationKey = (Number)newOperationKeyMap.get("OPER_KEY");
					Number newOperationUpdateNumber = (Number)newOperationKeyMap.get("OPER_UPDT_NO");

					operationsMap.put("NEW_OPER_UPDT_NO", newOperationUpdateNumber);
					operationsMap.put("TO_OPER_KEY", newOperationKey);
					operationMapping.add(operationsMap);
				}

				// FND-17329
				// Move to OFD Based on Display Sequence

				if (equalsIgnoreCase(displaySequence, FLOW_DIAGRAM) 
						|| equalsIgnoreCase(displaySequence, AUTO_PARALLEL))
				{
					planOfd.moveOperationToOfd(toPlanId,
							toPlanVersion,
							toPlanRevision,
							toPlanAlteration,
							toPlanUpdateNumber,
							toOperationNumber,
							OPER_INS);
				}
				// FND-17329 maintain background OFD for AUTO SERIAL
				else if(equalsIgnoreCase(displaySequence, AUTO_SERIAL) ||
						equalsIgnoreCase(displaySequence, EXECUTION_ORDER))
				{
					// Call method to move operation to background OFD
					planOfd.moveOperationToBackgroundOfd(toPlanId, 
							toPlanVersion, 
							toPlanRevision, 
							toPlanAlteration, 
							toPlanUpdateNumber, 
							toOperationNumber,
							displaySequence, 
							OPER_INS,
							fromSequenceNumber);
				}
			}
			else if(equalsIgnoreCase(destination, ORDER))
			{
				Number toOperationKey = null;
				if(equalsIgnoreCase(source, PLAN))
				{
					toOperationKey = operationDao.selectNextSequenceNumber();

					// copy operation from plan to order
					sfplOperation.copyOperationToWid( fromPlanId, 
							fromPlanVersion, 
							fromPlanRevision, 
							fromPlanAlteration, 
							fromOperationKey, 
							toOrderId, 
							toOperationKey, 
							toOperationNumber, 
							YES,operationTitle,
							COPY_TASK_GROUP_FROM_PLAN,null,null,null,null);

					operationsMap.put("NEW_OPER_UPDT_NO", null);
					operationsMap.put("TO_OPER_KEY", toOperationKey);
					operationMapping.add(operationsMap);
				}
				else if(equalsIgnoreCase(source, ORDER))
				{
					toOperationKey = operationDao.selectNextSequenceNumber();

					// copy operation from order to order
					sfwidOperation.copyOperation(fromOrderId,
							fromOperationKey,
							toOrderId,
							toOperationNumber,
							toOperationKey,
							YES,
							operationTitle,
							fromSequenceNumber, // FND-21428
							COPY_TASK_GROUP_FROM_ORDER);

					// Updates the Alteration Id
					operationAlteration.updateAlterationId(toOrderId,
							toOperationKey,
							NumberUtils.INTEGER_MINUS_ONE,
							alterationId);

					operationsMap.put("NEW_OPER_UPDT_NO", null);
					operationsMap.put("TO_OPER_KEY", toOperationKey);
					operationMapping.add(operationsMap);
				}

				// FND-18129
				// Do not Move to OFD Based on Display Sequence
				// As by Default the Operation will be in EXCLUDED Status.

				if(!equalsIgnoreCase( toOrderDisplaySequence, EXECUTION_ORDER ))
				{
					ofdLink.moveToOfd(toOrderId, toOperationNumber);
				}
				//GE-1003 Now while copy task group for MRO orders, Operations will go to particular column rather than Un-sequenced column 
				else
				{
					ofdLink.moveOperWithSeqNumberToOfd( toOrderId, 
							toOperationNumber, 
							EXCLUDED, 
							fromSequenceNumber, 
							null, 
							null );

				}
			}
		}

		Set<Number> operationKeySet = new HashSet<>();

		// Insert the relation between Operation and Task Group
		for(int i = 0; i< taskGroupMapping.length; i++ )
		{
			Number fromTaskGroupNumber = taskGroupMapping[i][0];
			Number toTaskGroupNumber = taskGroupMapping[i][1];



			//defect 1829 - begin whole reason for defect 1829
			//This was done here because of the mapping from the task to task 
			String orderType = "";
			String itemId = "";
			String partNo = "";
			String partChg = "";
			String partTitle = "";
			String woPartNo = "";
			String woPartChg = "";
			String woPartTitle = "";
			String woItemId = "";
			String woPlanId = "";
			Number woPlanUpdateNumber = 0;


			//get the plan from the work order
			List orderList = commonDaoPW.selectSfwidOrderDesc(fromOrderId);
			
			
			if (orderList.size() ==1){
				Map map = (Map)orderList.get(0);
				orderType = (String) map.get("ORDER_TYPE");
				woPlanId = (String) map.get("PLAN_ID");
				woPlanUpdateNumber = (Number) map.get("PLAN_UPDT_NO");
				woPartNo = (String)map.get("PART_NO");
				woPartChg = (String)map.get("PART_CHG");
				woPartTitle = (String)map.get("PART_TITLE");
				woItemId = (String)map.get("ITEM_ID");

			}

			//get the part number from the SforSfplPlanSubjectData of the plan from the work order's task
			List  orderPartTaskList = commonDaoPW.selectSforSfplPlanSubjectData(woPlanId, woPlanUpdateNumber, taskGroupMapping[i][0]);

			//if the from task had parts do this
			if (orderPartTaskList.size() > 0) {

				sfplSubjectDao.insertPlanSubjectParts( toPlanId, 
						toPlanUpdateNumber, 
						toTaskGroupNumber, 
						NumberUtils.INTEGER_ONE, 
						userName, 
						woPlanId, 
						woPlanUpdateNumber, 
						fromTaskGroupNumber);

			} else { 
				//there was no partNo stored in the task groups part table in the from WO's plan
				sfplPlanDao.insertPlanSubjectPart(toPlanId,
						toPlanUpdateNumber,
						toTaskGroupNumber,
						woItemId,
						userName,
						COPY_TASK_GROUP_PART,
						woPartNo, 
						woPartChg,
						woPartTitle);
			}

			//defect 1829 - end whole reason for defect 1829


			Iterator operationIterator = operationMapping.listIterator();

			while(operationIterator.hasNext())
			{
				Map operationsMap = (Map)operationIterator.next();

				Number fromOperationKey = (Number)operationsMap.get("FROM_OPER_KEY");
				Number toOperationKey = (Number)operationsMap.get("TO_OPER_KEY");
				Number newOperationUpdateNumber = (Number)operationsMap.get("NEW_OPER_UPDT_NO");
				Number fromOperationUpdateNumber = (Number)operationsMap.get("FROM_OPER_UPDT_NO");

				if(equalsIgnoreCase(destination, PLAN))
				{
					if(equalsIgnoreCase(source, PLAN))
					{
						// Copy Task Group Operation information from Plan to Plan
						sfplSubjectDao.insertPlanSubjectOperation( toPlanId,
								toPlanUpdateNumber,
								toTaskGroupNumber,
								NumberUtils.INTEGER_ONE,
								toOperationKey,
								userName,
								newOperationUpdateNumber, 
								fromPlanId, 
								fromPlanUpdateNumber, 
								fromTaskGroupNumber, 
								fromOperationKey,
								fromOperationUpdateNumber);
					}
					else if(equalsIgnoreCase(source, ORDER))
					{
						// Copy Task Group Operation information from Order to Plan
						sfplSubjectDao.insertPlanSubjectOperation( toPlanId,
								toPlanUpdateNumber,
								toTaskGroupNumber,
								NumberUtils.INTEGER_ONE,
								toOperationKey,
								userName,
								newOperationUpdateNumber, 
								fromOrderId, 
								fromTaskGroupNumber, 
								fromOperationKey);

					}
				}
				else if(equalsIgnoreCase(destination, ORDER))
				{
					if(equalsIgnoreCase(source, PLAN))
					{
						boolean taskGroupExistsInSourcePlan =  sourceTaskGroupNumbers.stream().anyMatch(map -> // PBL-385
						equalsIgnoreCase(fromTaskGroupNumber.toString(), ((Number)map.get(SUBJECT_NO)).toString()) &&
						equalsIgnoreCase(fromOperationKey.toString(), ((Number)map.get(OPER_KEY)).toString()) && 
						equalsIgnoreCase(fromOperationUpdateNumber.toString(), ((Number)map.get(OPER_UPDT_NO)).toString())
								);

						if(taskGroupExistsInSourcePlan) { // PBL-384
							// Copy Task Group Operation information from Plan to Order
							sfplSubjectDao.insertOrderSubjectOperation( toOrderId, 
									toTaskGroupNumber, 
									NumberUtils.INTEGER_ONE, 
									toOperationKey, 
									userName, 
									NO, 
									EXCLUDED, 
									alterationId, 
									fromPlanId, 
									fromPlanUpdateNumber, 
									fromTaskGroupNumber, 
									fromOperationKey,
									fromOperationUpdateNumber);
						}
					}
					else if(equalsIgnoreCase(source, ORDER))
					{
						boolean taskGroupExistsInSourceOrder =  sourceTaskGroupNumbers.stream().anyMatch(map -> // PBL-385
						equalsIgnoreCase(fromTaskGroupNumber.toString(), ((Number)map.get(SUBJECT_NO)).toString()) &&
						equalsIgnoreCase(fromOperationKey.toString(), ((Number)map.get(OPER_KEY)).toString()) );

						if(taskGroupExistsInSourceOrder) {						
							// Copy Task Group Operation information from Order to Order
							sfplSubjectDao.insertOrderSubjectOperation( toOrderId, 
									toTaskGroupNumber, 
									NumberUtils.INTEGER_ONE, 
									toOperationKey, 
									userName, 
									NO, 
									EXCLUDED, 
									alterationId, 
									fromOrderId, 
									fromTaskGroupNumber, 
									fromOperationKey);
						}
					}

					if(!operationKeySet.contains(toOperationKey)) { // PBL-385 
						operationKeySet.add(toOperationKey);

						// Set the operation as EXCLUDED
						sfplSubjectDao.updateIncluded( EXCLUDED, 
								toOrderId, 
								toOperationKey, 
								NumberUtils.INTEGER_MINUS_ONE);
					}
				}
			}

			if(equalsIgnoreCase(destination, PLAN) && equalsIgnoreCase(source, PLAN))
			{
				// GE-5211
				checkCircularReference(fromPlanId, toPlanId, toPlanVersion,
						toPlanRevision, toPlanAlteration, fromPlanUpdateNumber,
						fromTaskGroupNumber);

				// Copy Parts of Operations
				sfplSubjectDao.insertPlanSubjectParts( toPlanId, 
						toPlanUpdateNumber, 
						toTaskGroupNumber, 
						NumberUtils.INTEGER_ONE, 
						userName, 
						fromPlanId, 
						fromPlanUpdateNumber, 
						fromTaskGroupNumber);
			}
		}

		if(equalsIgnoreCase(destination, ORDER)){
			if(equalsIgnoreCase(toOrderDisplaySequence, EXECUTION_ORDER) && executionOrderNode.isMRO(toOrderId)) {
				executionOrderNode.fillOperationSequenceGap(toOrderId, null, null);
			}
		}

		event.refreshTool();

	}//end defect 1829

	//begin defect 1829
	String getCommaSeparatedString(Number[] taskGroupNumberArray) {        
		List<String> valueListOfString = new ArrayList<String>();
		for(Number value : taskGroupNumberArray) {
			valueListOfString.add("'" + value.toString() + "'");            
		}

		return String.join(",", valueListOfString);
	}
	private boolean operationExistsInCopyList(List operationCopyList, String operationToFind){
		for(int i=0;i<operationCopyList.size();++i)
		{
			Map map=(Map)operationCopyList.get(i);
			if(operationToFind.equals(map.get("TO_OPER_NO"))) return true;
		}
		return false;
	}
	protected void checkCircularReference(String fromPlanId, String toPlanId,
			Number toPlanVersion, Number toPlanRevision,
			Number toPlanAlteration, Number fromPlanUpdateNumber,
			Number fromTaskGroupNumber) {
		String instructionsType = planDao.selectPlanInstructionsType(toPlanId,
				toPlanVersion,
				toPlanRevision,
				toPlanAlteration);

		if(instructionsType.equalsIgnoreCase(MRO_UPGRADE))
		{       
			List<Map<String,String>> subjectItemList =  sfplSubjectDao.selectPlanSubjectParts(fromPlanId, fromPlanUpdateNumber, fromTaskGroupNumber);

			String partNo= EMPTY;
			String partChange= EMPTY;

			List<Map<String,String>> buildItemList = planDao.selectBuildPartFromPlan(toPlanId, toPlanVersion, toPlanRevision, toPlanAlteration);
			if(buildItemList != null && !buildItemList.isEmpty())
			{
				Map<String,String> buildMap = buildItemList.get(0);
				partNo = buildMap.get(PART_NO_KEY);
				partChange = buildMap.get(PART_CHG);
			}

			// For each Row.
			for ( Map<String,String> subjectNumberMap: subjectItemList )
			{
				// Gets the Subject Number.
				String itemPartNo = subjectNumberMap.get(PART_NO_KEY);
				String itemPartChg =  subjectNumberMap.get(PART_CHG);

				try
				{
					// Checks whether part circular reference occurs or not
					boolean refOccurs = sfplPlanDao.checkPartCircularReference(partNo, 
							partChange, 
							itemPartNo, 
							itemPartChg);

					if (refOccurs)
					{
						// Part %V1 can not be inserted. Inserting this part will create a circular reference
						message.raiseError("MFI_6D830CFE928D41E18A59B4B2C7DA2ADA", partNo + "/" + partChange);
					}
				}
				catch (UncategorizedSQLException usqle)
				{
					logger.debug(usqle.getMessage(), usqle);
					// in the case that the CONNECT BY logic finds a loop.
					message.raiseError("MFI_6D830CFE928D41E18A59B4B2C7DA2ADA", partNo + "/" + partChange);
				}
			}
		}
	}
	//end defect 1829

}

