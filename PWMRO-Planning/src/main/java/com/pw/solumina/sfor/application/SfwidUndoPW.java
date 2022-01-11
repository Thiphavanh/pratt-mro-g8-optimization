/*
 *  This unpublished work, first created in 2018 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2018, 2019.  All rights reserved.  Information contained 
 *  herein is proprietary and confidential and improper use or disclosure 
 *  may result in civil and penal sanctions.
 *
 *  File:    SfwidUndoPW.java
 * 
 *  Created: 
 * 
 *  Author: 
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2018-04-19		T. Phan		    Added Dat_col_id to refine search. Defect 702
 *  2018-05-02		T. Phan		    Uses checkFlag to get comment flag. SMRO_WOE_206 - Defect 702
 *  2018-05-10		T. Phan		    Added check so if an error occurs, the flag won't trigger. SMRO_WOE_206 - Defect 702
 *  2018-07-24		Bob Preston		SMRO_WOE_206, Defect 795 - Update data collection user id and time stamp.
 *  2018-10-19		Bob Preston		SMRO_USR_203, Defect 468 - Added NoCheckPriv methods to not check for ORDER_RELEASE priv.
 *                                  when releasing Supp. or Sub orders.
 *  2019-09-05      B. Corson       Defect 1370 - Take Serial Number into account for the checkFlag.
 *  2020-03-01      J DeNinno		Defect 1451 - add serialId parameter per David Miron notes in defect
 *  2020-11-19		S. Edgerly		SMRO_WOE_206 - Defect 941 - SCR001: Split Data collections into two different privs.
 */
package com.pw.solumina.sfor.application;

import static com.ibaset.common.DbColumnNameConstants.EXTERNAL_ERP_NO;
import static com.ibaset.common.DbColumnNameConstants.LTA_SEND_FLAG;
import static com.ibaset.common.DbColumnNameConstants.ORDER_NO;
import static com.ibaset.common.FrameworkConstants.IN_QUEUE;
import static com.ibaset.common.FrameworkConstants.NO;
import static com.ibaset.common.FrameworkConstants.NOT_APPLICABLE;
import static com.ibaset.common.FrameworkConstants.YES;
import static com.ibaset.common.SoluminaConstants.COMPLETE;
import static com.ibaset.common.SoluminaConstants.DISABLE;
import static com.ibaset.common.SoluminaConstants.EXCLUDE;
import static com.ibaset.common.SoluminaConstants.EXCLUDED;
import static com.ibaset.common.SoluminaConstants.EXTERNAL_ORDER_RELEASE;
import static com.ibaset.common.SoluminaConstants.N;
import static com.ibaset.common.SoluminaConstants.OPERATION;
import static com.ibaset.common.SoluminaConstants.PENDING;
import static com.ibaset.common.SoluminaConstants.PERCENT_SIGN;
import static com.ibaset.common.SoluminaConstants.SCRAP_STATUS;
import static com.ibaset.common.SoluminaConstants.START;
import static com.ibaset.common.SoluminaConstants.WO_RELEASE;
import static com.ibaset.common.SoluminaConstants.Y;
import static com.ibaset.common.util.SoluminaUtils.stringEquals;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.StringUtils.defaultIfEmpty;
import static org.apache.commons.lang.StringUtils.equalsIgnoreCase;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.pw.common.dao.CommonDaoPW;
import com.ibaset.common.Reference;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.event.SoluminaEventPublisher;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.solumina.exception.SoluminaException;
import com.ibaset.solumina.execution.order.OrderKey;
import com.ibaset.solumina.execution.order.WorkOrder;
import com.ibaset.solumina.execution.order.WorkOrderDao;
import com.ibaset.solumina.execution.order.event.WorkOrderAlterationCompleteEvent;
import com.ibaset.solumina.execution.order.event.WorkOrderReleaseEvent;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sfcore.application.IUser;
import com.ibaset.solumina.sfcore.dao.IMessageDao;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.application.IParameters;
import com.ibaset.solumina.sfor.application.ISfwidUndo;
import com.ibaset.solumina.sfsqa.application.IInspectionOrder;
import com.ibaset.solumina.sfwid.application.IBuyoffRollup;
import com.ibaset.solumina.sfwid.application.IOrderDetails;
import com.ibaset.solumina.sfwid.dao.IBuyoffDao;
import com.ibaset.solumina.sfwid.dao.IOperationDao;
import com.ibaset.solumina.sfwid.dao.IOrderDao;
import com.ibaset.solumina.sfwid.dao.ISerialDao;
import com.ibaset.solumina.sfwid.dao.ISerialOperationDao;
import com.pw.solumina.sffnd.dao.UserDaoPW;
import com.pw.solumina.sfor.dao.SfwidUndoDaoPW;

public class SfwidUndoPW
{
	protected final Log logger = LogFactory.getLog(SfwidUndoPW.class);

	@Reference
	private ISfwidUndo sfwidUndoImpl;

	@Reference
	private IMessage message = null;

	@Reference 
	private ExtensionDaoSupport eds = null;

	@Reference
	private SfwidUndoDaoPW sfwidUndoDaoPW;

	@Reference
	private IMessageDao messageDao;

	@Reference
	private IParameters parameters;

	@Reference private CommonDaoPW commonDaoPw;
	@Reference private IEvent event; // Defect 795

	// Begin defect 468.

	@Reference
	protected IOperationDao operationDao = null;

	@Reference
	protected IOrderDao orderDao = null;

	@Reference
	protected IOrderDetails orderDetails = null;

	@Reference
	protected IInspectionOrder inspectionOrder = null;

	@Reference
	protected SoluminaEventPublisher publisher = null;

	@Reference
	private WorkOrderDao workOrderApplicationDao = null;

	@Reference
	private IBuyoffDao buyoffDao = null;

	@Reference
	private ISerialDao serialDao = null;

	@Reference
	private ISerialOperationDao serialOperationDao = null;

	@Reference
	private IBuyoffRollup buyoffRollup = null;

	@Reference
	private IUser privilege = null;

	@Reference
	private com.ibaset.solumina.sflta.dao.IOrderDao sfltaOrderDao = null;

	// End defect 468.

	//Defect 941. Cannot call SFWID.UserDaoPW from here.
	@Reference
	private UserDaoPW userDaoPW;



	public void preDataCollectionExecution(String orderId,
			Number operationKey,
			Number stepKey,
			String dataCollectionId,
			String lotNumberList,
			String serialNumberList,
			String orderNumberList,
			String value,
			String comments,
			String dataCollectionCertification,
			String ucfSerialOperDataCollectionVch1,
			String ucfSerialOperDataCollectionVch2,
			String ucfSerialOperDataCollectionVch3,
			String ucfSerialOperDataCollectionVch4,
			String ucfSerialOperDataCollectionVch5,
			String ucfSerialOperDataCollectionVch6,
			String ucfSerialOperDataCollectionVch7,
			String ucfSerialOperDataCollectionVch8,
			String ucfSerialOperDataCollectionVch9,
			String ucfSerialOperDataCollectionVch10,
			String ucfSerialOperDataCollectionVch11,
			String ucfSerialOperDataCollectionVch12,
			String ucfSerialOperDataCollectionVch13,
			String ucfSerialOperDataCollectionVch14,
			String ucfSerialOperDataCollectionVch15,
			Number ucfSerialOperDataCollectionNumber1,
			Number ucfSerialOperDataCollectionNumber2,
			Number ucfSerialOperDataCollectionNumber3,
			Number ucfSerialOperDataCollectionNumber4,
			Number ucfSerialOperDataCollectionNumber5,
			String ucfSerialOperDataCollectionFlag1,
			String ucfSerialOperDataCollectionFlag2,
			String ucfSerialOperDataCollectionFlag3,
			String ucfSerialOperDataCollectionFlag4,
			String ucfSerialOperDataCollectionFlag5,
			Date ucfSerialOperDataCollectionDate1,
			Date ucfSerialOperDataCollectionDate2,
			Date ucfSerialOperDataCollectionDate3,
			Date ucfSerialOperDataCollectionDate4,
			Date ucfSerialOperDataCollectionDate5,
			String calledFrom,
			String ucfSerialOperDataCollectionVch2551,
			String ucfSerialOperDataCollectionVch2552,
			String ucfSerialOperDataCollectionVch2553,
			String ucfSerialOperDataCollectionVch40001,
			String ucfSerialOperDataCollectionVch40002,
			String fileName,
			byte[] fileData,
			String oldUserId,
			String secondLoginUser,
			String skip,
			String serialFlag, // FND-20337
			Number orderQuantity, // FND-20337
			String program, // FND-20337
			String orderControl, // FND-20337
			String crossOrderFlag, // FND-20337
			String orientationFlag, // FND-20337
			String operationStatus,
			Number availableQty,
			Number completeQty, // FND-20337
			String groupJobNo, // FND-24258
			String groupJobInfo, // FND-24258
			String acceptReject) // FND-24287
	{
		// Begin Defect 1370
		String serialId = sfwidUndoDaoPW.getSerialId(orderId,serialNumberList);
		ucfSerialOperDataCollectionFlag1 = sfwidUndoDaoPW.checkFlag(orderId, operationKey, stepKey, 
				dataCollectionId, //Defect 702 (Rare instance)
				serialId);
		// End Defect 1370
		
		//Defect 941
		validateUserPrivs(orderId, operationKey, stepKey, dataCollectionId, serialId);
		
		if ((comments == null && ucfSerialOperDataCollectionFlag1 == null) || !(comments == null))
		{
			sfwidUndoImpl.preDataCollectionExecution(orderId,
					operationKey,
					stepKey,
					dataCollectionId,
					lotNumberList,
					serialNumberList,
					orderNumberList,
					value,
					comments,
					dataCollectionCertification,
					ucfSerialOperDataCollectionVch1,
					ucfSerialOperDataCollectionVch2,
					ucfSerialOperDataCollectionVch3,
					ucfSerialOperDataCollectionVch4,
					ucfSerialOperDataCollectionVch5,
					ucfSerialOperDataCollectionVch6,
					ucfSerialOperDataCollectionVch7,
					ucfSerialOperDataCollectionVch8,
					ucfSerialOperDataCollectionVch9,
					ucfSerialOperDataCollectionVch10,
					ucfSerialOperDataCollectionVch11,
					ucfSerialOperDataCollectionVch12,
					ucfSerialOperDataCollectionVch13,
					ucfSerialOperDataCollectionVch14,
					ucfSerialOperDataCollectionVch15,
					ucfSerialOperDataCollectionNumber1,
					ucfSerialOperDataCollectionNumber2,
					ucfSerialOperDataCollectionNumber3,
					ucfSerialOperDataCollectionNumber4,
					ucfSerialOperDataCollectionNumber5,
					ucfSerialOperDataCollectionFlag1,
					ucfSerialOperDataCollectionFlag2,
					ucfSerialOperDataCollectionFlag3,
					ucfSerialOperDataCollectionFlag4,
					ucfSerialOperDataCollectionFlag5,
					ucfSerialOperDataCollectionDate1,
					ucfSerialOperDataCollectionDate2,
					ucfSerialOperDataCollectionDate3,
					ucfSerialOperDataCollectionDate4,
					ucfSerialOperDataCollectionDate5,
					calledFrom,
					ucfSerialOperDataCollectionVch2551,
					ucfSerialOperDataCollectionVch2552,
					ucfSerialOperDataCollectionVch2553,
					ucfSerialOperDataCollectionVch40001,
					ucfSerialOperDataCollectionVch40002,
					fileName,
					fileData,
					oldUserId,
					secondLoginUser,
					skip,
					serialFlag, // FND-20337
					orderQuantity, // FND-20337
					program, // FND-20337
					orderControl, // FND-20337
					crossOrderFlag, // FND-20337
					orientationFlag, // FND-20337
					operationStatus,
					availableQty,
					completeQty, // FND-20337
					groupJobNo, // FND-24258
					groupJobInfo, // FND-24258
					acceptReject);
			String errorMessage = parameters.getUserMessage();    // Defect 702 (Third time). Checks if an error occured so flag does not trigger.

			if (errorMessage == null || errorMessage.equals("")){ 
				
				// begin defect 1451
				String[] elements = serialNumberList.split(";");
				List<String> serialList = Arrays.asList(elements);
				ArrayList<String> serialArrayList = new ArrayList<String>(serialList);
				for (int i = 0;i<serialArrayList.size();i++) {
					serialId = commonDaoPw.getSerialIdfromSerialNo(serialArrayList.get(i),orderId);
					
					sfwidUndoDaoPW.firstTimeFlag(orderId, operationKey, stepKey, dataCollectionId,serialId); //defect 1451 add serialId

					// Begin defect 795
					sfwidUndoDaoPW.updateUserId(orderId, operationKey, stepKey, dataCollectionId, secondLoginUser,serialId);//defect 1451 add serialId
				}
				//end defect 1451
				event.refreshTool();
				// End defect 795
			}                         // End of defect 702 (Third time)
		}
		else
		{
			message.raiseError("MFI_20", "Comments are required for data collections the second time onwards.");
		}
	}

	//Defect 941
	private void validateUserPrivs(String orderId, Number operKey, Number stepKey, String datColId, String serialId){

		List list = sfwidUndoDaoPW.getSerialOperDatColData(orderId, operKey, stepKey, datColId, serialId);
		Map map = (Map) list.get(0);

    	String notPreviouslyCompletedFlag = (String) map.get("UCF_SRL_OPER_DC_FLAG1");//Defect 941 multiple serial fix
    	if("N".equals(notPreviouslyCompletedFlag)){
			String requiredPriv = "PW_DATCOL_UPDATE";
			String substitution = "the " + requiredPriv;
			if(!userDaoPW.hasPriv(ContextUtil.getUsername(), requiredPriv)){
				message.raiseError("PRIVILEGE_VIOLATION", substitution);
			}
		}
	}
	//End Defect 941
	
	// Begin defect 468.


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibaset.solumina.sfor.application.ISfwidUndo#releaseOrder(java.lang.String,
	 * java.lang.String)
	 */
	public Map releaseOrderNoCheckPriv(String orderId, String sendLtaFlag)
	{

		// Releases the Work Order.
		// Checks the user has privileges to release the order or not.
		//validator.checkUserPrivilege("ORDER_RELEASE");

		String orderNumber = EMPTY;

		Map orderDetails = null;
		List serialNoList = new ArrayList();
		try
		{
			try
			{
				// Gets the Order Description.
				orderDetails = orderDao.selectOrderDetails(orderId);
			}
			catch (EmptyResultDataAccessException erdae)
			{}

			// Raises an error when Order Item Details is null.
			if (orderDetails == null)
			{
				message.raiseError("MFI_50140", orderNumber);
			}

			updateSendLTAFlagNoCheckPriv(orderId, sendLtaFlag, orderDetails);

			// Gets the Values of Order Number,Order Status and Needs Review
			// Flag.
			orderNumber = (String) orderDetails.get("ORDER_NO");
			String orderStatus = (String) orderDetails.get("ORDER_STATUS");
			String needsReviewFlag = (String) orderDetails.get("NEEDS_REVIEW_FLAG");

			// Raises an error when Order Status is not 'PENDING'.
			if (!stringEquals(orderStatus, PENDING))
			{
				message.raiseError("MFI_50141");
			}

			// Raises an error when Value of Needs Review Flag is 'Y'.
			if (stringEquals(needsReviewFlag, YES))
			{
				// Don't allow to release the order if the OFD needs review
				// because,can not find the first Operation.
				// Order Number cann't be released because its Operation Flow
				// Diagram needs review.
				message.raiseError("MFI_96", orderNumber);
			}

			// Updates Order Status.
			this.orderDetails.updateOrderStatus(orderId, IN_QUEUE, WO_RELEASE);

			// Gets the Value of Starting Operation Keys.
			Number[] startingOperations = getStartingOperationsNoCheckPriv(orderId);

			// Loop Through Starting Operations to update its status.

			// Gets the Serial Id and Lot Id.
			List serialLotDetails = orderDao.selectSerialLotId(orderId);

			// Sets the multi unit flag.
			Number orderQty = (Number) orderDetails.get("ORDER_QTY");
			String serialFlag = (String) orderDetails.get("SERIAL_FLAG");
			String multiUnitOrderFlag = NO;
			if (stringEquals(serialFlag, YES) && orderQty != null && orderQty.intValue() > 1)
			{
				multiUnitOrderFlag = YES;
			}

			// FND-19881
			boolean includedOperRemains = operationDao.selectIncludedOperExists(orderId);

			if (!includedOperRemains)
			{
				// At least one %V1 must remain included in work order.
				message.raiseError("MFI_74E50D93B5836539E04400144FA7B7D2", OPERATION);
			}

			updateOrderOperationNoCheckPriv(orderId,
					startingOperations,
					multiUnitOrderFlag,
					serialLotDetails,
					serialNoList);
		}
		catch (EmptyResultDataAccessException exception)
		{
			// Raises an error when Order No %V1 not found.
			message.raiseError("MFI_50140");
		}

		// FND-22663
		updateReadyToCollectFlagNoCheckPriv(orderId, null, serialNoList);

		// GE-1437
		String inspectionOrderId = (String) orderDetails.get("INSP_ORDER_ID");
		if (isNotEmpty(inspectionOrderId))
		{
			// Release Inspectoin Order
			inspectionOrder.releaseInspectionOrder(inspectionOrderId);
		}

		return publishWorkOrderEventNoCheckPriv(orderId);
	}

	public void updateSendLTAFlagNoCheckPriv(String orderId, String sendLtaFlag, Map<String,String> orderDetails)
	{
		String orderNumber;
		if (sendLtaFlag != null)
			// Input UDV dropdown values are: No Change, Y, N (null means hidden
			// field) Conditionally updates sfwid_order_desc.lta_send_flag
			// "before" release
		{
			// FND-17283
			String externalErpNumber = orderDetails.get(EXTERNAL_ERP_NO);
			orderNumber = orderDetails.get(ORDER_NO);

			//raiseErrorForErpOrderIfUserDoesNotHaveExternalOrderReleasePrivilege(orderNumber,
			//                                                                    externalErpNumber);

			if (isNotEmpty(externalErpNumber) && !privilege.hasPrivilege(EXTERNAL_ORDER_RELEASE) ) //GE-4597
			{
				// Work Order %V1 cannot be released as it is managed through external ERP
				message.raiseError("MFI_D49D5706259D4E65A6A72E210748D079", orderNumber);
			}

			// Checks the LTA System Flag.
			if (ContextUtil.getUser().getContext().getSendLtaSystemFlag())
			{
				updateSendLtaFlagNoCheckPriv(orderId, sendLtaFlag, orderDetails);
			}
			else
			{
				//raiseErrorIfLtaSystemIsNotAvailable(sendLtaFlag);
				if (stringEquals(sendLtaFlag, YES))
				{
					// Cannot Send LTA Flag when LTA System is not
					// available.
					message.raiseError("SFLTA_87642");
				}
			}
		}
	}

	protected void updateSendLtaFlagNoCheckPriv(String orderId, String sendLtaFlag, Map<String,String> orderDetails)
	{
		boolean updateFlag = false;

		// Gets the lta_send_flag from order_desc
		String orderLtaSendFlag = defaultIfEmpty(orderDetails.get(LTA_SEND_FLAG),NO);  // FND-20270

		if (stringEquals(sendLtaFlag, YES)
				&& stringEquals(orderLtaSendFlag, NO))
		{
			updateFlag = true;
		}
		else if (stringEquals(sendLtaFlag, NO)
				&& stringEquals(orderLtaSendFlag, YES))
		{
			updateFlag = true;
		}

		// Update sfwid_order_desc.lta_send_flag with user selection
		if (updateFlag)
		{
			// Updates the Order Description when Update Flag is true.
			sfltaOrderDao.updateOrderDescription(sendLtaFlag, orderId);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibaset.solumina.sfor.application.ISfwidUndo#getStartingOperations(java.lang.String)
	 */
	public Number[] getStartingOperationsNoCheckPriv(String orderId)
	{
		// Get the List of Starting operations
		List startingOperationKeysList = operationDao.selectStartingOperations(orderId);

		Number[] startingOperations = new Number[startingOperationKeysList.size()];

		// For each record
		for (int i = 0; i < startingOperationKeysList.size(); i++)
		{
			// Gets the Map Values in local variables.
			Map startingOperationKeysMap = (Map) startingOperationKeysList.get(i);

			startingOperations[i] = (Number) startingOperationKeysMap.get("OPER_KEY");
		}

		return startingOperations;
	}


	public void updateReadyToCollectFlagNoCheckPriv(String orderId, Number operKey, List serialNoList)
	{
		List serialUnitList = new ArrayList();
		if (serialNoList == null)
		{
			serialUnitList = serialDao.selectUnitInformation(orderId);
		}
		else
		{
			serialUnitList = serialNoList;
		}
		Iterator serialUnitIterator = serialUnitList.listIterator();

		String serialStringList = null;
		boolean first = true;

		while (serialUnitIterator.hasNext())
		{
			Map serialUnitMap = (Map) serialUnitIterator.next();
			if (first)
			{
				if (stringEquals((String) serialUnitMap.get("SERIAL_NO"), NOT_APPLICABLE))
				{
					break;
				}
				else
				{
					serialStringList = (String) serialUnitMap.get("SERIAL_NO");
					first = false;
				}
			}
			else
			{
				serialStringList = serialStringList + ";" + (String) serialUnitMap.get("SERIAL_NO");
			}
		}

		List buyoffListStatus = buyoffDao.selectBuyoffStatus(orderId, operKey, serialStringList);

		Iterator buyoffStatusIterator = buyoffListStatus.listIterator();
		List notReadyToCollect = new ArrayList();
		List readyToCollect = new ArrayList();

		while (buyoffStatusIterator.hasNext())
		{
			Map buyoffStatusMap = (Map) buyoffStatusIterator.next();
			String buyoffStatus = (String) buyoffStatusMap.get("STATUS");
			String collectionFlag = (String) buyoffStatusMap.get("READY_TO_COLLECT_FLAG");
			Number rollupOperationKey = (Number) buyoffStatusMap.get("OPER_KEY");

			if (equalsIgnoreCase(buyoffStatus, DISABLE) || equalsIgnoreCase(buyoffStatus, PENDING)
					|| equalsIgnoreCase(buyoffStatus, COMPLETE))
			{
				if (!stringEquals(collectionFlag, N))
				{
					notReadyToCollect.add(buyoffStatusMap);
				}
			}
			else
			{
				if (!stringEquals(collectionFlag, Y))
				{
					readyToCollect.add(buyoffStatusMap);

				}
			}
		}

		if (notReadyToCollect.size() > 0)
		{
			buyoffDao.updateReadyToCollectBuyoffs(orderId, N, notReadyToCollect);
		}

		if (readyToCollect.size() > 0)
		{
			buyoffDao.updateReadyToCollectBuyoffs(orderId, Y, readyToCollect);

		}
	}

	public void updateOrderOperationNoCheckPriv(String orderId,
			Number[] startingOperations,
			String multiUnitOrderFlag,
			List serialLotDetails,
			List serialNoList) throws SoluminaException
	{
		// Restricted method call: updateSerialStatus to execute only
		// Serialwise for first operation,
		// No need to call serialwise for operations other than first.
		boolean firstOperation = true;
		String newOperationStatus;

		for (int i = 0; i < startingOperations.length; i++)
		{
			newOperationStatus = IN_QUEUE;
			if (equalsIgnoreCase(EXCLUDED,
					operationDao.selectIncludedFlag(orderId,
							startingOperations[i],
							NumberUtils.INTEGER_MINUS_ONE)))
			{
				newOperationStatus = EXCLUDE;
			}

			// Updates Operation Status.
			sfwidUndoImpl.updateOperationStatus(orderId,
					startingOperations[i],
					newOperationStatus,
					NO,
					WO_RELEASE);

			// 10/28/2005 Prasad TD#8005.Get the auto complete flag for the
			// first operation key.
			// Selects the Operation Description Information into Map.
			Map operationDescriptionInformationMap = serialOperationDao.selectOperationDescriptionAndOrderNodeInformation(orderId,
					startingOperations[i],
					NumberUtils.INTEGER_MINUS_ONE);
			// Gets the Auto Complete Flag and First Node Id.
			String autoCompleteFlag = (String) operationDescriptionInformationMap.get("AUTO_COMPLETE_FLAG");
			autoCompleteFlag = defaultIfEmpty(autoCompleteFlag, NO);
			checkForAutoCompleteFlagNoCheckPriv(orderId,
					newOperationStatus,
					autoCompleteFlag,
					operationDescriptionInformationMap);

			updatePartStatusSerialWiseNoCheckPriv(orderId,
					serialNoList,
					startingOperations,
					serialLotDetails,
					multiUnitOrderFlag,
					firstOperation,
					newOperationStatus,
					i,
					autoCompleteFlag);

			firstOperation = false;
		}
	}

	public void updatePartStatusSerialWiseNoCheckPriv(String orderId,
			List serialNoList,
			Number[] startingOperations,
			List serialLotDetails,
			String multiUnitOrderFlag,
			boolean firstOperation,
			String newOperationStatus,
			int i,
			String autoCompleteFlag) throws SoluminaException
	{
		// FND-20270
		boolean serialOperInserted = false;

		// Check is there any buyoff in this operation to call the buyoff rollup
		boolean buyoffExists = operationDao.selectOperationBuyoffExist(orderId,
				startingOperations[i]);

		// FND-20270 Need to set part status to NOT USED if same PART is installed at same ref_des
		// in build unit (on any Work order for that build unit),
		// So get the component list here and called the updatePartStatus()
		// serial wise
		List componentsList = operationDao.selectComponentPartInfo(orderId, startingOperations[i]);

		Iterator serialLotDetailsIterator = serialLotDetails.listIterator();

		while (serialLotDetailsIterator.hasNext()) // FND-11056
		{
			// Gets the value of Serial Id and Lot Id in local
			// variables.
			Map serialLotIdMap = (Map) serialLotDetailsIterator.next();

			String serialId = (String) serialLotIdMap.get("SERIAL_ID");
			String lotId = (String) serialLotIdMap.get("LOT_ID");

			// FND-21938, If serial is already scrapped dont change to IN QUEUE while performing
			// releaseOrder
			String serialStatus = (String) serialLotIdMap.get("SERIAL_STATUS");

			serialNoList.add(serialLotIdMap);

			if (!stringEquals(serialStatus, SCRAP_STATUS))
			{
				// Restricted method call: updateSerialStatus to execute
				// only Serialwise for first operation,
				// No need to call serialwise for operations other than
				// first.
				if (firstOperation)
				{
					// Updates Serial Status.
					sfwidUndoImpl.updateSerialStatus(orderId,
							lotId,
							serialId,
							IN_QUEUE, // FND-16775
							WO_RELEASE,
							null);
				}

				// FND-20270
				// method which calls Opearion wise only, No need to call for each serial
				if (!serialOperInserted)
				{
					sfwidUndoImpl.insertAllOperationWhileWORelease(orderId,
							startingOperations[i],
							newOperationStatus,
							multiUnitOrderFlag);
					serialOperInserted = true;

				}

				Iterator componentsIterator = componentsList.listIterator();

				// For each component in opeartion
				while (componentsIterator.hasNext())
				{
					Map componentsListMap = (Map) componentsIterator.next();

					// Gets the Map values in local variables.
					String itemId = (String) componentsListMap.get("PLND_ITEM_ID");
					String refDes = (String) componentsListMap.get("REF_DES");
					Number stepKey = (Number) componentsListMap.get("STEP_KEY");
					String partDatColId = (String) componentsListMap.get("PART_DAT_COL_ID");

					sfwidUndoImpl.updatePartStatus(orderId,
							startingOperations[i],
							stepKey,
							lotId,
							serialId,
							itemId,
							refDes,
							partDatColId, // FND-13188
							WO_RELEASE);
				}

				if (buyoffExists)
				{
					buyoffRollup.rollUpBuyoff(orderId,
							startingOperations[i],
							lotId,
							serialId,
							START);
				}

				// 10/28/2005 Prasad TD#8005.Move the units to the next
				// successors if the first operation
				// is auto complete. Checks when Auto Complete Flag is 'Y'.
				if (stringEquals(autoCompleteFlag, YES))
				{
					// Get and Move Next Operation.
					sfwidUndoImpl.getAndMoveNextOperation(orderId,
							startingOperations[i],
							lotId,
							serialId,
							WO_RELEASE);
				}
			}
		}
	}


	public void checkForAutoCompleteFlagNoCheckPriv(String orderId,
			String newOperationStatus,
			String autoCompleteFlag,
			Map operationDescriptionInformationMap) throws SoluminaException
	{
		String firstNodeId = (String) operationDescriptionInformationMap.get("NODE_ID");

		// Sets the Auto Complete Flag.
		String nodeType = OPERATION + PERCENT_SIGN;

		// Checks when Auto Complete Flag is 'Y'.
		if (stringEquals(autoCompleteFlag, YES))
		{
			// Selects the Order Node Information.
			List orderNodeList = orderDao.selectOrderNode(orderId, nodeType, firstNodeId);

			Iterator orderNodeIterator = orderNodeList.listIterator();

			// For each row.
			while (orderNodeIterator.hasNext())
			{
				// Gets the Operation Key and Successor Node Id.
				Map orderNodeMap = (Map) orderNodeIterator.next();
				Number tempOperationKey = (Number) orderNodeMap.get("OPER_KEY");
				String successorNodeId = (String) orderNodeMap.get("NODE_ID");

				try
				{
					// Gets the Order Link Count.
					orderDao.selectOrderLinkExists(orderId, successorNodeId);

					sfwidUndoImpl.updateOperationStatus(orderId,
							tempOperationKey,
							newOperationStatus,
							NO,
							WO_RELEASE);
				}
				catch (IncorrectResultSizeDataAccessException incorrectResultSizeException)
				{
					if (incorrectResultSizeException.getActualSize() == 0)
					{
						throw new SoluminaException(incorrectResultSizeException.getMessage());
					}
				}
			}
		}
	}


	// GE-1437: Refactor Method
	public Map publishWorkOrderEventNoCheckPriv(String orderId)
	{
		Map workOrderMap = new HashMap();
		if (publisher.isListenerRegistered(WorkOrderReleaseEvent.class)
				|| publisher.isListenerRegistered(WorkOrderAlterationCompleteEvent.class))
		{
			WorkOrder workOrder = workOrderApplicationDao.selectWorkOrder(new OrderKey(orderId));

			if (publisher.isListenerRegistered(WorkOrderReleaseEvent.class))
			{
				logger.debug("Publishing WorkOrderReleaseEvent");
				// Create and publish Work Order Release Event.
				WorkOrderReleaseEvent wore = new WorkOrderReleaseEvent(this);
				wore.addWorkOrder(workOrder);
				publisher.publishEvent(wore);
			}

			if (publisher.isListenerRegistered(WorkOrderAlterationCompleteEvent.class))
			{
				logger.debug("Publishing WorkOrderAlterationCompleteEvent");
				// Create and publish Work Order Alt Release Event
				WorkOrderAlterationCompleteEvent woare = new WorkOrderAlterationCompleteEvent(this);
				woare.addWorkOrder(workOrder);
				woare.setAlterationId(WO_RELEASE);
				woare.setHideAlterationFlag(false);
				publisher.publishEvent(woare);
			}
			workOrderMap.put("WORK_ORDER", workOrder);
		}
		return workOrderMap;
	}


	// End defect 468.

}