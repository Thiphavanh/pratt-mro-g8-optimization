/*
 *  This unpublished work, first created in 2018 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2018, 2020,2021.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    OrderPW.java
 * 
 *  Created: 2018-02-01
 * 
 *  Author:  N.Gnassounou
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2018-01-25      N.Gnassounou    Initial Release SMRO_WOE_204
 *  2018-01-25      N.Gnassounou    Defect 344 - Missing Task Group Data for SUB ORDER
 *  2018-03-25		S.Niu			Defect 410 - Sub Orders should be a copy of the parent from when it was released, not current copies of parent orders
 *  2018-04-12		Fred Ettefagh	Fixed java error when creating sub orders
 *  2018-04-13		Fred Ettefagh	added missing method updateOrderWorkFlowPre
 *  2018-04-29		Fred Ettefagh	defect 632
 *  2018-05-04      Fred Ettefagh   defect 754  Input Matrix not including expected task groups - All Customers
 *  2018-05-17      Fred Ettefagh   defect 760  update oper conf no for sub order and Supplemental Order
 *  2018-06-05      Fred Ettefagh   defect 760, changed insert and update oper, so that conf number is updated via LO table.  This is to fix issue of adding/updating oper when order in active or inqueue
 *  2018-06-06      T. Phan         Defect 796  Work Dept and work center is copied to Supplemental Orders
 *  2018-06-08      S. Niu          Defect 468  Create Sub Orders Priv and Supplemental Orders Priv
 *  2018-08-06      Bob Preston     SMRO_WOE_211, Defect 919 - Initialize PW_ALT_COUNT (rather than null).
 *  2018-08-08      Fred Ettefagh   Defect 935 fixed method updateSupplmentalOrSubOrderSupSubNetData to use oper_key instead of oper_no
 *  2018-08-30		Bob Preston	    SMRO_WOE_209 defect 748, Added call to updateOrderParentInfo().
 *  2018-10-01      F. Ettefagh     Defect 980 Cannot Create Suborder due to supNet subNet and conf Numbers
 *  2018-10-04      F. Ettefagh     Defect 980 added check to skip order oper conf check if order is in pending status
 *  2018-10-26      Fred Ettefagh   Defect 969 when creating a Supplemental Order, update oper 0010 conf/sup/sub data from user selected oper no
 *  2018-11-15      Fred Ettefagh   Defect 1008 - Defect Task groups are not carried over through Sub Orders
 *  2019-03-03      D.Miron         G8R2SP4 Upgrade - Added 7th argument to method call for includeExcludeWidSubject.
 *  2019-06-05      F. Ettefagh     changed code for Repair shop order split 
 *  2019-07-29      R. Thorpe	    added code to call T308 in updateOrderWorkFlowPre
 *  2019-08-20      D. Miron        SMRO_WOE_304 Sub Orders - Added methods createSubOrderRepairPre and createSubOrderRepairPreWi.
 *   2019-09-12      F. Ettefagh     SMRO_TR_319, added code for SCR-001 
 *   2019-09-20      Bahram J        SMRO_TR_308, added role to bypass t308 interface  
 *   2019-09-25      F. Ettefagh     SMRO_TR_308, renamed orderReleaseInterface to  RepairOrderT308Interface
 *   2019-11-13      F. Ettefagh     SMRO_TR_319, Changed callFrom to tag for interface data
 *   2019-12-12      F. Ettefagh     added system.out.print for debug 
 *   2020-01-17      J DeNinno       defect 1430 fixed error when creating supplemental work order
 *   2020-02-13      Fred Ettefagh   SMRO_TR_308,Defect 1474, changed code to send t308 message after certain changes for WO Alt and task include exclude
 *   2020-02-26      Fred Ettefagh   SMRO_TR_308,Defect 1474, changed code to send t308 message after certain changes for WO Alt and task include exclude
 *   2020-03-03		B. Polak		Defect 1513 - Close Parent Order when Replace Parent Order is performed. 
 *   2020-05-06		J. DeNinno      Deefect 1645 method not in HF3. calling new 
 *   2020-05-12      J DeNinno		Defect 1654 don't close parent order on Sub Order creation
 *   2020-06-22		S.Edgerly		Defect 1416 - Supplemental work order's reason for change not populating
 *   2020-06-23      D.Mrion         Defect 1712 - Replaced sfwidUndo.createBlankOrder with sfwidOrder.createBlankOrder.  Fixes java abstract error.
 *   2020-06-25      J DeNinno       Defect 1702 - 		//defect 1702 need to clear the cache otherwise the thumbs up on buyoffs stays gray
 *   2020-07-27      j DeNinno		Defect 1742 - Plan Id was not correct
 *   2020-07-29      J DeNinno      Defect 1041 - check PWUST_LONOTE_BYPASS priv before doing the check
 *   2021-02-17      J DeNinno		Defect 1144 - Add note when creating Sub Order
 */
package com.pw.solumina.sfwid.application;

import static com.ibaset.common.FrameworkConstants.NO;
import static com.ibaset.common.FrameworkConstants.NOT_APPLICABLE;
import static com.ibaset.common.FrameworkConstants.YES;
import static com.ibaset.common.SoluminaConstants.BASELINE;
import static com.ibaset.common.SoluminaConstants.EDIT;
import static com.ibaset.common.SoluminaConstants.INSERT;
import static com.ibaset.common.SoluminaConstants.N;
import static com.ibaset.common.SoluminaConstants.ORDER;
import static com.ibaset.common.SoluminaConstants.PROCESS_ORDER;
import static com.ibaset.common.SoluminaConstants.SEMI_COLON;
import static com.ibaset.common.SoluminaConstants.SUPPLEMENTAL;
import static com.ibaset.common.SoluminaConstants.SUB_ORDER;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.StringUtils.defaultIfEmpty;
import static org.apache.commons.lang.StringUtils.isNotEmpty;
import static org.apache.commons.lang.StringUtils.upperCase;
import static org.apache.commons.lang.math.NumberUtils.INTEGER_MINUS_ONE;
import com.pw.common.dao.CommonDaoPW;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

import com.ibaset.common.Reference;
import com.ibaset.common.solumina.IValidator;
import com.ibaset.common.solumina.exception.SoluminaException;
import com.ibaset.common.util.SoluminaUtils;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sfcore.application.IUser;
import com.ibaset.solumina.sffnd.application.IAutoNumberGenerator;
import com.ibaset.solumina.sffnd.application.IDateUtils;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.application.IParse;
import com.ibaset.solumina.sffnd.application.IWrapUtils;
import com.ibaset.solumina.sffnd.dao.IDocumentTypeDao;
import com.ibaset.solumina.sfor.application.ISfplSubject;
import com.ibaset.solumina.sfwid.application.ISfwidOrder;
import com.ibaset.solumina.sfor.application.IWidSubject;
import com.ibaset.solumina.sfwid.application.IAlterationDiscrepancy;
import com.ibaset.solumina.sfwid.application.ICall;
import com.ibaset.solumina.sfwid.application.IOperation;
import com.ibaset.solumina.sfwid.application.IOrder;
import com.ibaset.solumina.sfwid.application.IOrderDetails;
import com.ibaset.solumina.sfwid.application.IOverInspectionHold;
import com.ibaset.solumina.sfwid.application.impl.OrderImpl;
import com.ibaset.solumina.sfwid.dao.IOperationDao;
import com.ibaset.solumina.sfwid.dao.IOrderDao;
import com.ibaset.solumina.sfwid.dao.ISerialDao;
import com.pw.common.dao.CommonDaoPW;
import com.pw.common.utils.CommonUtilsPW;
import com.pw.solumina.T308.integration.impl.RepairOrderT308Interface;
import com.pw.solumina.sfpl.application.impl.PlanDaoImplPW;
import com.pw.solumina.sfpl.application.impl.buildMROOrderNumber;
import com.pw.solumina.sfpl.dao.impl.OrderCreateDaoPW;
import com.pw.solumina.sfwid.application.impl.OrderDetailsImplPW;
import com.pw.solumina.sfwid.dao.BuyoffDaoPW;
import com.pw.solumina.sfwid.dao.OrderAssembleTextDaoPW;
import com.pw.solumina.sfwid.dao.OrderDaoPW;
import com.pw.solumina.sfwid.dao.WorkOrderDaoPW;
import com.pw.solumina.sfwid.dao.impl.OrderOperConfirmationDao;
import com.pw.solumina.sfwid.impl.OrderOperConfirmationCheck;
import static com.ibaset.common.util.WorkorderExecutionHelper.clearBuyoffStatusCachedMap;

import com.ibaset.solumina.sfwid.application.impl.OrderMroRepairSplitPW;

@SuppressWarnings("unused")
public class OrderPW {

	private static final String SUB_ORDER ="SUB ORDER";
	@Reference private IOrder orderImpl =null;
	@Reference private OrderAssembleTextDaoPW assemblyDao=null;
	@Reference private IWrapUtils wrapUtils = null;
	@Reference private IEvent event = null;
	@Reference private BuyoffDaoPW buyoffDao =null;
	@Reference private IDocumentTypeDao documentTypeDao = null;
	@Reference private buildMROOrderNumber buildMROOrderNumber =null;
	@Reference private IMessage message = null;
	@Reference private IOrderDao orderDao = null;
	@Reference private IValidator validator = null;
	@Reference private IDateUtils dateUtils =null;
	@Reference private ISerialDao serialDao = null;
	@Reference private IParse parse = null;
	@Reference private ISfwidOrder sfwidOrder;
	@Reference private IAutoNumberGenerator orderNumberGenerator = null;
	@Reference private IOperationDao operationDao = null;  //Defect 344
	@Reference private IOrderDao orderDaoImpl =null;
	@Reference private IOperation operation =null; //Defect 410
	@Reference private OrderAssembleTextDaoPW orderAssembleTxtDaoPW ; //Defect 410
	@Reference private IAlterationDiscrepancy alterationDiscrepancy = null; //Defect 796
	@Reference private OrderOperConfirmationCheck orderOperConfirmationCheck;
	@Reference private RepairOrderT308Interface repairOrderT308Interface;
	@Reference private OrderDaoPW orderDaoPW = null;
	protected final Log logger = LogFactory.getLog(OrderImpl.class);
	@Reference 	private DataFieldMaxValueIncrementer orderStringSequence = null;
	@Reference	private OrderCreateDaoPW orderCreateDaoPW = null;
	@Reference	private IOrder order ; //Defect 624
	@Reference	private  ISfplSubject sfplSubject = null;
	@Reference	private ICall call=null;
	@Reference	private IWidSubject widSubject = null;
	@Reference	private WorkOrderDaoPW workOrderDaoPW;
	@Reference	private PlanDaoImplPW planDaoImplPW;
	@Reference	private CommonUtilsPW commonUtilsPW;
	@Reference	private CommonDaoPW commonDaoPW;
	@Reference private OrderOperConfirmationDao orderOperConfirmationDao;
	@Reference private OrderMroRepairSplitPW orderMroRepairSplitPW = null;
	@Reference private IOverInspectionHold overInspectionHold  = null;
	@Reference private IOrderDetails orderDetails  = null; //defect 1513
	@Reference	private IUser user; //defect 1041
	
	//Begin Defect 624,980
	public void updateOrderWorkFlowPre( String taskId,
			String toTaskType,
			String orderAlterStatus,
			String toStatus,
			String notes,
			Number blockEdit,
			String orderId,
			String calledFrom,
			String alterationId,
			String calledFrmDialog,
			String deptId,
			String centId,
			String operNo,
			String orderStatus){

		if (deptId == null || centId == null){

			message.raiseError("MFI_20", "Some operations in the order are missing a "
					+ "work department or center. Please update the headers of the "
					+ "following operations: \nOperation " + operNo );
		}

		String pwOrderType = commonUtilsPW.getOrderTypePW(orderId);
		if (StringUtils.equalsIgnoreCase(pwOrderType, "OVERHAUL") ||
				StringUtils.equalsIgnoreCase(pwOrderType, "SUPPLEMENTAL") ||
				StringUtils.equalsIgnoreCase(pwOrderType, "SUB ORDER")){

			if (!StringUtils.equalsIgnoreCase(orderStatus, "PENDING")){
				//This will FAIL if the SUB ORDER is from a REPAIR - Assuming Dave is taking care of this in his SUB ORDER Code
				checkOperSupSubData(orderId);
			}
		}

		if (StringUtils.equalsIgnoreCase(pwOrderType, "SUB ORDER")){ 
			//  get the parent order type  -- SUB ORDER is for OH and REPAIR need to know the original order Type
			String parentOrderId = commonDaoPW.getParentOrderId(orderId);
			pwOrderType = commonUtilsPW.getOrderTypePW(parentOrderId);
		}

		if (StringUtils.equals("REPAIR", pwOrderType)){
			if ((StringUtils.equals(orderStatus, "IN QUEUE") || 
					StringUtils.equals(orderStatus, "ACTIVE")) && 
					StringUtils.equals(orderAlterStatus,"ORDER_AUTHORING2")){

				// interface with SAP T308
				if (commonUtilsPW.sendRepairT308()){
					repairOrderT308Interface.sendOrderT308Msg(orderId,"updateOrderWorkFlowPre");
				}
				List<Map> sfwidHoldsList = commonDaoPW.selectSfwidHoldInfoV(orderId, "OPEN", "WORK SCOPE HOLD");
				if (sfwidHoldsList!=null && !sfwidHoldsList.isEmpty()){

					for (Map holdMap : sfwidHoldsList) {

						String holdId = (String) holdMap.get("HOLD_ID");
						overInspectionHold.closeHold(orderId, 
								null,//operationNumber, 
								holdId, 
								null,//notes, 
								"AUTO REPAIR END OF ALT CLOSE",//calledFrom, 
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

		order.updateOrderWorkFlowPre(taskId, 
				toTaskType, 
				orderAlterStatus, 
				toStatus, 
				notes, 
				blockEdit, 
				orderId, 
				calledFrom, 
				alterationId, 
				calledFrmDialog);

		//defect 1702 need to clear the cache otherwise the thumbs up on buyoffs stays gray
		clearBuyoffStatusCachedMap();

	}
	//End Defect 624

	// defect 980
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void checkOperSupSubData(String orderId) {
		if (!user.hasPrivilege("PWUST_LONOTE_BYPASS")) {   //defect 1041
			List<Map> operList  = orderOperConfirmationDao.selectOrderOperWithNoConfNumber(orderId);
			if (operList!=null && !operList.isEmpty()){
				String operNoList = null;
				for (Map operMap : operList) {

					String operNumber = (String)operMap.get("OPER_NO");

					if (operNoList==null){
						operNoList = "Oper no = " + operNumber;
					}else{
						operNoList = operNoList + System.lineSeparator()  + "Oper no = " + operNumber;
					}
				}
				message.raiseError("MFI_20","following operations must have valid super/sub net data" + System.lineSeparator()  + operNoList);	
			}
		}
	}

	//Begin Defect 796
	@SuppressWarnings("rawtypes")
	public void createSupplementalOrderPreWi(String partNumber,
			String partChange,
			String pwOrderNumber,	
			String parentOrderNo,
			String lotNumber,
			String serialFlag,
			String lotFlag,
			String program,
			String workLocation,
			String itemType,
			String itemSubtype,
			String notes,
			String owpId,
			String displaySequence, 
			String parentOrderId,
			String workFlow,
			String configName,
			String operNo,
			String workDept,
			String workCenter)	{

		String newSupplementalOrderId= null;
		validator.checkUserPrivilege("PWUST_SUPPLEM_ORDER_CREATE"); //Defect 468

		String orderNumber = getNextAutoOrderNo();

		validator.checkUserPrivilege("ORDER_ALT_INITIATION");
		validator.checkMandatoryField("Order Number", orderNumber, "MFI_50844");
		validator.checkMandatoryField("Part Number", partNumber, "MFI_54835");

		System.out.print("createSupplementalOrderPreWi pwOrderNumber = " +pwOrderNumber);
		System.out.print("createSupplementalOrderPreWi orderNumber = " +orderNumber);
		System.out.print("createSupplementalOrderPreWi parentOrderId = " +parentOrderId);

		if ((partChange == null) || (partChange == "")){
			partChange = NOT_APPLICABLE;
		}

		//GE-2233
		if (YES.equals(lotFlag)){
			validator.checkMandatoryField("Lot Number", lotNumber, "MFI_56648");
		}

		// Checks whether Order Number exists or not.
		boolean orderDescriptionExists = orderDao.selectOrderDescriptionExists(orderNumber);
		if (orderDescriptionExists){
			message.raiseError("MFI_57291");
		}        

		Map orderDescription = orderDao.selectOrderDescription(parentOrderId);
		String orderType = (String)orderDescription.get("ORDER_TYPE");

		String itemId = orderDao.selectItemId(partNumber, partChange);
		System.out.print(" createSupplementalOrderPreWi, serialFlag = "+ serialFlag );
		if (YES.equals(serialFlag)){
			try
			{   
				String serialNumbers = commonDaoPW.getSerialfromOrderNo(parentOrderId, lotNumber); //defect 1430
				// String serialNumber = orderDao.selectSerialScrapNumber(partNumber, lotNumber, YES,NO);
				createSupplementalOrder(itemId,
						orderNumber,
						lotNumber,
						serialNumbers,
						serialFlag,
						lotFlag,
						program,
						workLocation,
						itemType,
						itemSubtype,
						notes,
						owpId,
						displaySequence, 
						parentOrderId,
						workFlow,
						configName,
						workDept,
						workCenter); 
			}catch (IncorrectResultSizeDataAccessException exception){
				if (exception.getActualSize() == 0){
					// 11/24/2011 FND-18634
					message.raiseError("MFI_1124",orderType);
				}
				/*defect 1430 comment out
							// When Serial Number have more then one record.
							else if (exception.getActualSize() > 1)
							{
									// If Lot Flag is null then assign default value as 'N'
									lotFlag = defaultIfEmpty(lotFlag, NO);

									// Creates the Parameter list
									StringBuffer parameters = new StringBuffer();
									parameters.append("NEW_ORDER_NO=")
												.append(wrapUtils.wrapValue(orderNumber))
												.append(",PART_NO=")
												.append(wrapUtils.wrapValue(partNumber))
												.append(",ITEM_ID=")
												.append(wrapUtils.wrapValue(itemId))
												.append(",PART_CHG=")
												.append(wrapUtils.wrapValue(partChange))
												.append(",LOT_FLAG= ")
												.append(wrapUtils.wrapValue(lotFlag))
												.append(",SERIAL_FLAG=")
												.append(wrapUtils.wrapValue(serialFlag))
												.append(",LOT_NO=")
												.append(wrapUtils.wrapValue(lotNumber))
												.append(",PROGRAM=")
												.append(wrapUtils.wrapValue(program))
												.append(",WORK_LOC=")
												.append(wrapUtils.wrapValue(workLocation))
												.append(",ITEM_TYPE=")
												.append(wrapUtils.wrapValue(itemType))
												.append(",ITEM_SUBTYPE=")
												.append(wrapUtils.wrapValue(itemSubtype))
												.append(",NOTES=")
												.append(wrapUtils.wrapValue(notes))
												.append(",DISPLAY_SEQUENCE=")
												.append(wrapUtils.wrapValue(displaySequence))
												.append(",PARENT_ORDER_ID=") // FND-17914 parentOrderId
												.append(wrapUtils.wrapValue(parentOrderId))
												.append(",CONFIG_NAME=") // FND-23204 configNamew
												.append(wrapUtils.wrapValue(configName))
												.append(",WORK_DEPARTMENT=")
												.append(wrapUtils.wrapValue(workDept))
												.append(",WORK_CENTER=")
												.append(wrapUtils.wrapValue(workCenter));

									String hideColumns = null;

									if (NO.equals(lotFlag)){
										//hideColumns = LOT_NO;
										hideColumns = "LOT_NO";
									}
									event.showUdv("MFI_2000541",INSERT, "Create Supplemental Work Order",hideColumns,parameters.toString());
							}
				 */
			}
		}
		else{
			createSupplementalOrder(itemId,
					orderNumber,
					lotNumber,
					NOT_APPLICABLE,
					serialFlag,
					lotFlag,
					program,
					workLocation,
					itemType,
					itemSubtype,
					notes,
					owpId,
					displaySequence, 
					parentOrderId,
					workFlow,
					configName,
					workDept,
					workCenter);//FND-23204
					logger.debug("Create Supplemental Order");
		}

		String newOrderId = insertIntoPwustSfwidOrderDesc(pwOrderNumber, orderNumber,parentOrderId,"SUPPLEMENTAL");
		assemblyDao.updateSupplemnetalOperNo(orderNumber, operNo);

		// defect 969
		if (StringUtils.isNotBlank(operNo)){
			orderOperConfirmationCheck.updateConfirmData(parentOrderId, operNo, newOrderId, "0010");
		}
		//defect 1416
		//if repair
		commonDaoPW.updatePwustSfwidOrderDescNoteText(newOrderId, notes);
		//if overhaul
		commonDaoPW.updateSfwidAlterationDesc(newOrderId, notes);
		//end defect 1416
	}

	@SuppressWarnings("rawtypes")
	public void createSupplementalOrder(String itemId,
			String orderNumber,
			String lotNumber,
			String serialList,
			String serialFlag,
			String lotFlag,
			String program,
			String workLocation,
			String itemType,
			String itemSubtype,
			String notes,
			String owpId,
			String displaySequence, 
			String parentOrderId,
			String workFlow,
			String configName,
			String workDept,
			String workCenter)//FND-23204
	{

		List list = commonDaoPW.selectPWOrderDataByOrderId(parentOrderId);
		Map pwOrderDataMap = (Map) list.iterator().next();
		String orderStatus = (String) pwOrderDataMap.get("ORDER_STATUS");

		if (!StringUtils.equalsIgnoreCase(orderStatus, "IN QUEUE") && !StringUtils.equalsIgnoreCase(orderStatus, "ACTIVE")){
			message.raiseError("MFI_20", "Can only create a Supplemnetal Order when order status is Active or In Queue");
		}

		Number orderQuantity = null;
		String tempSerialFlag = null;
		String tempLotFlag = null;
		Number parentOrderQuantity = null;
		Number parentOrderScrapQuantity = null;

		validator.checkMandatoryField("Order Number", orderNumber, "MFI_57251");

		if (YES.equals(serialFlag))	{
			validator.checkMandatoryField("Serial List", serialList, "MFI_55884");
		}

		Map itemDescriptionMasterAllMap = serialDao.selectItemDescriptionMasterAll(itemId);
		String partNumber = (String) itemDescriptionMasterAllMap.get("PART_NO");
		Date scheduleStartDate = dateUtils.tzShiftToClient(dateUtils.selectDBSystemDate());
		scheduleStartDate = dateUtils.appendStartTime(scheduleStartDate);
		scheduleStartDate = dateUtils.tzShiftToServer(scheduleStartDate);

		String[] serialNumbers = null;

		if (YES.equals(serialFlag)){
			serialNumbers = parse.parse(serialList,SEMI_COLON,true);

			orderQuantity = new Integer(serialNumbers.length);

			for (int i = 0; i < serialNumbers.length; i++){
				try	{
					Map asWorkedItemInformation = orderDao.selectSerialFlagLotFlag(partNumber, serialNumbers[i],lotNumber);
					tempSerialFlag = (String) asWorkedItemInformation.get("SERIAL_FLAG");
					tempLotFlag = (String) asWorkedItemInformation.get("LOT_FLAG");
				}
				catch (IncorrectResultSizeDataAccessException exception){
					if (exception.getActualSize() == 0){
						message.raiseError("MFI_50164", serialNumbers[i],lotNumber,partNumber,EMPTY);
					}
					else if (exception.getActualSize() > 1)
					{
						throw new SoluminaException(exception.getMessage());
					}
				}

				String nvlSerialFlag = defaultIfEmpty(serialFlag, NO);
				String nvlTempSerialFlag = defaultIfEmpty(tempSerialFlag, NO);
				String nvlLotFlag = defaultIfEmpty(lotFlag, NO);
				String nvlTempLotFlag = defaultIfEmpty(tempLotFlag, NO);

				if (!nvlSerialFlag.equals(nvlTempSerialFlag)|| !nvlLotFlag.equals(nvlTempLotFlag)){
					message.raiseError("MFI_50165");
				}
			}
		}
		else
		{
			Number lotQuantity = null;
			validator.checkMandatoryField("Lot Number", lotNumber, "MFI_56648");
			serialNumbers = parse.parse(NOT_APPLICABLE,SEMI_COLON,true);

			try
			{
				if(isNotEmpty(parentOrderId))		// FND-19919
				{	
					Map orderInfoMap = orderDao.selectOrderDetails(parentOrderId);

					tempSerialFlag = (String) orderInfoMap.get("SERIAL_FLAG");
					tempLotFlag = (String) orderInfoMap.get("LOT_FLAG");
					parentOrderQuantity = (Number) orderInfoMap.get("ORDER_QTY");
					parentOrderScrapQuantity = (Number) orderInfoMap.get("ORDER_SCRAP_QTY");
					orderQuantity = parentOrderQuantity.intValue() - parentOrderScrapQuantity.intValue();
				} 
				else
				{
					Map workedItemInformation = orderDao.selectSerialFlagLotFlagLotQuantity(partNumber,serialNumbers[0],lotNumber);
					tempSerialFlag = (String) workedItemInformation.get("SERIAL_FLAG");
					tempLotFlag = (String) workedItemInformation.get("LOT_FLAG");
					lotQuantity = (Number) workedItemInformation.get("LOT_QTY");
					orderQuantity = lotQuantity;
				}	
			}
			catch (IncorrectResultSizeDataAccessException exception)
			{
				if (exception.getActualSize() == 0)
				{
					message.raiseError("MFI_50164",serialNumbers[0], lotNumber,partNumber,EMPTY);
				}
				else if (exception.getActualSize() > 1)
				{
					throw new SoluminaException(exception.getMessage());
				}
			}

			String nvlSerialFlag = defaultIfEmpty(serialFlag, NO);
			String nvlTempSerialFlag = defaultIfEmpty(tempSerialFlag, NO);
			String nvlLotFlag = defaultIfEmpty(lotFlag, NO);
			String nvlTempLotFlag = defaultIfEmpty(tempLotFlag, NO);

			if (!nvlSerialFlag.equals(nvlTempSerialFlag) || !nvlLotFlag.equals(nvlTempLotFlag))
			{
				// Cannot create work order for this set of units because they
				// havedifferent values for the serial and lot flags.
				message.raiseError("MFI_50165");
			}
		}

		String orderType = SUPPLEMENTAL;
		if(!documentTypeDao.selectDocumentSubTypeExists(PROCESS_ORDER, SUPPLEMENTAL))
		{
			orderType = upperCase(orderType);
		}
		String[] orderId = new String[1];
		String unscrapFlag = NO;
		Date[] scheduleEndDate = new Date[1];

		// Creates the Blank Order.
		//ISfwidUndo.createBlankOrder(orderNumber,  //defect 1645 replace with below
		sfwidOrder.createBlankOrder(orderNumber,    //defect 1645 
				itemId,
				orderQuantity,
				scheduleStartDate,
				scheduleEndDate,
				serialNumbers,
				lotNumber,
				parentOrderId,
				orderType,
				serialFlag,
				lotFlag,
				orderId,
				unscrapFlag,
				program,
				workLocation,
				itemType,
				itemSubtype,
				notes,
				null, // ucfOrderVch1,
				null, // ucfOrderVch2,
				null, // ucfOrderVch3,
				null, // ucfOrderVch4,
				null, // ucfOrderVch5,
				null, // ucfOrderNumber1,
				null, // ucfOrderNumber2,
				null, // ucfOrderFlag1,
				owpId,
				null, // ucfOrderVch6,
				null, // ucfOrderVch7,
				null, // ucfOrderVch8,
				null, // ucfOrderVch9,
				null, // ucfOrderVch10,
				null, // ucfOrderVch11,
				null, // ucfOrderVch12,
				null, // ucfOrderVch13,
				null, // ucfOrderVch14,
				null, // ucfOrderVch15,
				null, // ucfOrderNumber3,
				null, // ucfOrderNumber4,
				null, // ucfOrderNumber5,
				null, // ucfOrderDate1,
				null, // ucfOrderDate2,
				null, // ucfOrderDate3,
				null, // ucfOrderDate4,
				null, // ucfOrderDate5,
				null, // ucfOrderFlag2,
				null, // ucfOrderFlag3,
				null, // ucfOrderFlag4,
				null, // ucfOrderFlag5,
				null, // ucfOrderVch2551,
				null, // ucfOrderVch2552,
				null, // ucfOrderVch2553,
				null, // ucfOrderVch40001,
				null, // ucfOrderVch40002,
				workDept, // workDepartment,
				workCenter, // workCenter,
				null, // customer,
				null, // customerOrderNumber,
				null, // contract,
				null, // replacementAction,
				null, // asWorkedBomId,
				null, // relatedOrderId
				displaySequence, 
				null, 
				null,// FND-17760
				null,
				null,
				null,
				configName,  //FND-23204
				null, // FND-23462
				N, // FND-24392
				null, // FND-24790
				false,
				null);//FND-22644

		String alterationId = null;
		alterationId = alterationDiscrepancy.startAlteration(orderId[0],
				"Supplemental WORK ORDER Authoring",
				ORDER,
				alterationId,
				workFlow,
				BASELINE.toUpperCase());//GE-1331

		orderImpl.insertDefaultOperationChangeLog(alterationId,orderId[0]);
		event.launchManufacturing(orderId[0], EDIT);

	}


	public String getNextAutoOrderNo() {
		String orderNoAutoGenerated  = orderStringSequence.nextStringValue();
		return orderNoAutoGenerated;
	}

	@SuppressWarnings("rawtypes")
	protected String insertIntoPwustSfwidOrderDesc(String pwOrderNumber, String orderNoAutoGenerated,String parentOrderId, String pwOrderType) {

		String salesOrder=null;
		String engineModel = null;
		String workScopePW=null;
		String superNetPW=null;
		String subNetPW = null;
		String customerPW = null;

		if (StringUtils.isNotBlank(parentOrderId)){
			Map pwustSfwidOrderDescMap = orderCreateDaoPW.selectPwustSfwidOrderDesc(parentOrderId);
			salesOrder = (String)pwustSfwidOrderDescMap.get("SALES_ORDER");
			engineModel = (String)pwustSfwidOrderDescMap.get("ENGINE_MODEL");
			workScopePW = (String)pwustSfwidOrderDescMap.get("WORK_SCOPE");
			superNetPW = (String)pwustSfwidOrderDescMap.get("SUPERIORNET");
			subNetPW = (String)pwustSfwidOrderDescMap.get("SUBNET");
			customerPW = (String)pwustSfwidOrderDescMap.get("CUSTOMER");
			System.out.println("pwustSfwidOrderDescMap= "+pwustSfwidOrderDescMap );
		}

		System.out.println("pwOrderNumber= "+ pwOrderNumber);
		System.out.println("orderNoAutoGenerated= "+ orderNoAutoGenerated);
		System.out.println("parentOrderId= "+ parentOrderId);
		System.out.println("pwOrderType= "+ pwOrderType);

		String newOrderId =commonUtilsPW.insertIntoPwustSfwidOrderDesc(orderNoAutoGenerated, 
				pwOrderNumber , 
				null,//newOrderId, 
				superNetPW, 
				subNetPW, 
				workScopePW, 
				customerPW, 
				salesOrder, 
				engineModel, 
				pwOrderType);

		updateSfwidOrderDescSalesOrderUCF(newOrderId,salesOrder);

		return newOrderId;
	}


	public void updateSfwidOrderDescSalesOrderUCF(String orderID, String salesOrder) {

		orderCreateDaoPW.updateSfwidOrderDescSalesOrderUCF(orderID, salesOrder);

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getMextSuppOrderNo(String oobParentOrderNo) throws Exception{


		Map map1 = orderCreateDaoPW.selectPwustSwidOrderDescViaObbOrderNo(oobParentOrderNo);
		String pwOrderNo = (String) map1.get("PW_ORDER_NO");
		String nextSuppementalOrderNumber =  buildMROOrderNumber.buildSuppementalOrderNumber(pwOrderNo);
		List list = new ArrayList();
		Map map = new HashMap();
		map.put("NEW_SUPL_ORDER_NO", nextSuppementalOrderNumber);
		map.put("PARENT_ORDER_NO", pwOrderNo);
		map.put("SECURITY_GROUP", null);
		list.add(map);
		return list;
	}



	//Sub Order No
	public void createSubOrderPre(String hideParams, String params, String pwOrderNumber) throws Exception {

		String subOrderNo =buildMROOrderNumber.buildSubOrderNumber(pwOrderNumber);
		params = "PARENT_ORDER_NO='"+ pwOrderNumber+ "',"+params ;
		params = params + ",ORDER_NO=" + wrapUtils.wrapValue(subOrderNo);

		event.showUdv("PWUST_6362FEB85E39F5A6E05387971F0A6201",
				INSERT,
				"Create Sub Order",
				null,
				params.toString());	       	       

	}

	@SuppressWarnings("rawtypes")
	public void createSubOrderPreWi( String partNumber,
			String partChange,
			String newPWSubOrderNo,
			String lotNumber,
			String serialFlag,
			String lotFlag,
			String program,
			String workLocation,
			String itemType,
			String itemSubtype,
			String notes,
			String owpId,
			String displaySequence, 
			String parentOrderId,
			String parentOrderNo,
			String workFlow,
			String configName) throws Exception{

		validator.checkUserPrivilege("PWUST_SUB_ORDER_CREATE"); //Defect 468

		List list = commonDaoPW.selectPWOrderDataByPWOrderNo(parentOrderNo);
		Map pwOrderDataMap = (Map) list.iterator().next();
		String parentOrderStatus = (String) pwOrderDataMap.get("ORDER_STATUS");
		String parentOrderType = (String) pwOrderDataMap.get("ORDER_TYPE");

		//defect 1008
		//-------------
		String parentPlanId = (String) pwOrderDataMap.get("PLAN_ID");
		Number parentPlanVer = (Number) pwOrderDataMap.get("PLAN_VERSION");
		Number parentPlanRev = (Number) pwOrderDataMap.get("PLAN_REVISION");
		Number parentPlanAlt = (Number) pwOrderDataMap.get("PLAN_ALTERATIONS");
		String parentPlanNo = (String) pwOrderDataMap.get("PLAN_NO");
		Number parentPlanUpdtNo = (Number) pwOrderDataMap.get("PLAN_UPDT_NO");
		String parentPlanType = (String) pwOrderDataMap.get("PLAN_TYPE");
		//-------------

		if (!parentOrderType.equalsIgnoreCase("OVERHAUL")){
			message.raiseError("MFI_20", "Can only create a Sub Order from OVERHAUL order type ");
		}

		if (!StringUtils.equalsIgnoreCase(parentOrderStatus, "IN QUEUE") && !StringUtils.equalsIgnoreCase(parentOrderStatus, "ACTIVE")){
			message.raiseError("MFI_20", "Can only create a Sub Orders when order status is Active or In Queue");
		}		   

		String itemId = orderDao.selectItemId(partNumber, partChange);
		String todaysDate = orderCreateDaoPW.selectDBTimeStamp("MM/DD/YYYY HH:MM:SS");
		Number orderQuantity = 1;

		String[] orderIdArray = new String[1];
		orderIdArray[0]=parentOrderId;

		Date scheduledStartDate = SoluminaUtils.parseDate(todaysDate);
		Date scheduledEndDate = SoluminaUtils.parseDate("01/01/2999");
		Date[] scheduledEndDateArray = new Date[1];
		scheduledEndDateArray[0]=scheduledEndDate;

		String newOrderNo = getNextAutoOrderNo();

		callOOBToCreateBlankOrder(newOrderNo, 
				serialFlag, 
				lotFlag, 
				program, 
				owpId, 
				displaySequence,
				parentOrderId, 
				itemId, 
				orderQuantity, 
				orderIdArray, 
				scheduledStartDate, 
				scheduledEndDateArray,
				parentOrderType,   //"OVERHAUL",    // orderType
				itemType,    //"PART",       //String itemType,
				itemSubtype, //"Overhaul",  //String itemSubtype,);
		lotNumber,
		workLocation,
		notes);  //defect 1144


		String newSubOrderId =insertIntoPwustSfwidOrderDesc(newPWSubOrderNo, newOrderNo,parentOrderId,"SUB ORDER");
		// defect 1008
		workOrderDaoPW.updateSfwidOrderDescPlanData(newSubOrderId, parentPlanId, parentPlanVer, parentPlanRev, parentPlanAlt, parentPlanNo, parentPlanUpdtNo, parentPlanType);
		callOOBToStartWorkOrderAlteration(newSubOrderId,workFlow);
		renameAllOperNumbers(newSubOrderId);

		if (StringUtils.equalsIgnoreCase(parentOrderType, "OVERHAUL")){
			copyTasksFromParentParentOrder(newSubOrderId,parentOrderId);
			updateTaskExcludeFlag(newSubOrderId,parentOrderId);
		}

		if (!StringUtils.equalsIgnoreCase(parentOrderType, "OVERHAUL")){
			copyOperationsFromParentOrder(newOrderNo,parentOrderId,parentOrderNo);
		}

		deleteAllTempOperations(newSubOrderId);
		event.launchManufacturing(newSubOrderId, EDIT);

	}


	@SuppressWarnings("rawtypes")
	public void copyOperationsFromParentOrder(String newOrderNo,String parentOrderId,String parentOrderNo) {

		List<Map> operList = orderCreateDaoPW.selectOrderOperations(parentOrderId);
		if (operList!=null&& !operList.isEmpty()){

			for (Map operMap : operList) {

				String operNo = (String) operMap.get("OPER_NO");
				String operTitle = (String) operMap.get("TITLE");

				operation.copyOperationPre(parentOrderNo, 
						operNo, 
						newOrderNo, 
						operNo, 
						com.ibaset.common.FrameworkConstants.YES,//copyComponentParts, 
						operTitle, 
						null);
			}
		}
	}

	public void callOOBToStartWorkOrderAlteration(String newOrderId,String workFlow) {


		// Map map = orderCreateDaoPW.selectOrderOperationNo(newOrderId,"0010");

		//Number operationKey = (Number) map.get("OPER_KEY");

		call.preStartAlteration(newOrderId, 
				"Sub Order Create",//alterationReason, 
				null,//changeAuthorizationType, 
				"0",//alterationNo, 
				null,//calledFrom, 
				"0", // Defect 919 //ucfOrderAlterationVch1, 
				null,//ucfOrderAlterationVch2, 
				null,//ucfOrderAlterationVch3, 
				null,//ucfOrderAlterationVch4, 
				null,//ucfOrderAlterationVch5, 
				null,//ucfOrderAlterationVch6, 
				null,//ucfOrderAlterationVch7, 
				null,//ucfOrderAlterationVch8, 
				null,//ucfOrderAlterationVch9, 
				null,//ucfOrderAlterationVch10, 
				null,//ucfOrderAlterationVch11,
				null,//ucfOrderAlterationVch12, 
				null,//ucfOrderAlterationVch13, 
				null,//ucfOrderAlterationVch14, 
				null,//ucfOrderAlterationVch15, 
				null,//ucfOrderAlterationNum1, 
				null,//ucfOrderAlterationNum2, 
				null,//ucfOrderAlterationNum3, 
				null,//ucfOrderAlterationNum4, 
				null,//ucfOrderAlterationNum5, 
				null,//ucfOrderAlterationFlag1, 
				null,//ucfOrderAlterationFlag2, 
				null,//ucfOrderAlterationFlag3, 
				null,//ucfOrderAlterationFlag4, 
				null,//ucfOrderAlterationFlag5, 
				null,//ucfOrderAlterationDate1, 
				null,//ucfOrderAlterationDate2, 
				null,//ucfOrderAlterationDate3, 
				null,//ucfOrderAlterationDate4, 
				null,//ucfOrderAlterationDate5, 
				"ORDER",//alterationType, 
				null,//operationKey, 
				null,//discrepancyId, 
				null,//discrepancyLineNumber, 
				null,//objectId, 
				workFlow, 
				null,//ucfOrderAlterationVch2551, 
				null,//ucfOrderAlterationVch2552,
				null,//ucfOrderAlterationVch2553,
				null,//ucfOrderAlterationVch40001, 
				null,//ucfOrderAlterationVch40002, 
				null,//releasePackageId, 
				"N");//associateChange);
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void copyTasksFromParentParentOrder(String newOrderId,String parentOrderId) {

		List<Map> orderTaskList = orderCreateDaoPW.selectMroOrderTasks(parentOrderId);
		if (orderTaskList!=null&& !orderTaskList.isEmpty()){

			String subjectNumberString = null;
			for (Map taskMap : orderTaskList) {

				Number subjectNo = (Number)taskMap.get("SUBJECT_NO");
				if (StringUtils.isBlank(subjectNumberString)){
					subjectNumberString = subjectNo.toString();
				}else{
					subjectNumberString = subjectNumberString + "," + subjectNo.toString();
				}
			}

			sfplSubject.copyTaskGroup(null,//fromPlanId, 
					null,//fromPlanVersion, 
					null,//fromPlanRevision, 
					null,//fromPlanAlteration, 
					null,//toPlanId, 
					null,//toPlanVersion, 
					null,//toPlanRevision, 
					null,//toPlanAlteration, 
					parentOrderId,//fromOrderId, 
					newOrderId,//toOrderId, 
					"ORDER",//source, 
					"ORDER",//destination, 
					subjectNumberString);//fromTaskGroupNumberList);	

		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateTaskExcludeFlag(String newOrderId,String parentOrderId){

		List<Map> orderTaskList = orderCreateDaoPW.selectMroOrderTasks(parentOrderId);

		if (orderTaskList!=null&& !orderTaskList.isEmpty()){

			for (Map taskMap : orderTaskList) {

				Number subjectNo = (Number)taskMap.get("SUBJECT_NO");
				String includeFlag = (String)taskMap.get("INCLUDED_FLAG");
				widSubject.includeExcludeWidSubject(newOrderId, 
						subjectNo, 
						includeFlag, 
						null,//notes, 
						null,//discrepancyId, 
						null, /*discrepancyLineNo*/
						false); // includeOrIncludedActiveOperOrIncludedInSubjectExist  G8R2SP4
			}
		}
	}



	@SuppressWarnings("rawtypes")
	public void renameAllOperNumbers(String orderId) {

		List<Map>  operList= orderCreateDaoPW.selectOrderOperations(orderId);
		for (Map operMap : operList) {
			Number operationKey = (Number)operMap.get("OPER_KEY");
			String operNumber= (String)operMap.get("OPER_NO");
			operation.renumberOperation(orderId, operationKey, operNumber + "-TMP", null);
		}
	}

	@SuppressWarnings("rawtypes")
	public void deleteAllTempOperations(String orderId){

		List<Map>  operList= orderCreateDaoPW.selectAllTempOrderOperations(orderId);
		for (Map operMap : operList) {
			String operNumber= (String)operMap.get("OPER_NO");
			operation.deleteOperation(orderId, operNumber);
		}		 
	}

	public void callOOBToCreateBlankOrder(String pwOrderNumber, 
			String serialFlag, 
			String lotFlag, 
			String program,
			String owpId, 
			String displaySequence, 
			String parentOrderId, 
			String itemId, 
			Number orderQuantity,
			String[] orderIdArray, 
			Date scheduledStartDate, 
			Date[] scheduledEndDateArray,
			String orderType,
			String itemType,
			String itemSubtype,
			String lotNo,
			String workLocation,
			String notes) {  //defect 1144

		// Defect 1712 - Replaced sfwidUndo with sfwidOrder
		sfwidOrder.createBlankOrder( pwOrderNumber,// orderNumber,
				itemId,
				orderQuantity,// orderQuantity,
				scheduledStartDate, //Date scheduledStartDate,
				scheduledEndDateArray,//Date[] scheduledEndDate,
				null,//String[] serials,
				lotNo,//String lotNumber,
				parentOrderId,//String parentOrderId,
				orderType,//String orderType,
				serialFlag,//String serialFlag,
				lotFlag,//String lotFlag,
				orderIdArray,//String[] orderId,
				null,//String unscrapFlag,
				program,//String program,
				workLocation,//String workLocation,
				itemType,
				itemSubtype,
				notes,//String notes, // defect 1141
				null,//String ucfOrderVch1,
				null,//String ucfOrderVch2,
				null,//String ucfOrderVch3,
				null,//String ucfOrderVch4,
				null,//String ucfOrderVch5,
				null,//Number ucfOrderNumber1,
				null,//Number ucfOrderNumber2,
				null,//String ucfOrderFlag1,
				owpId,//String owpId,
				null,//String ucfOrderVch6,
				null,//String ucfOrderVch7,
				null,//String ucfOrderVch8,
				null,//String ucfOrderVch9,
				null,//String ucfOrderVch10,
				null,//String ucfOrderVch11,
				null,//String ucfOrderVch12,
				null,//String ucfOrderVch13,
				null,//String ucfOrderVch14,
				null,//String ucfOrderVch15,
				null,//Number ucfOrderNumber3,
				null,//Number ucfOrderNumber4,
				null,//Number ucfOrderNumber5,
				null,//Date ucfOrderDate1,
				null,//Date ucfOrderDate2,
				null,//Date ucfOrderDate3,
				null,//Date ucfOrderDate4,
				null,//Date ucfOrderDate5,
				null,//String ucfOrderFlag2,
				null,//String ucfOrderFlag3,
				null,//String ucfOrderFlag4,
				null,//String ucfOrderFlag5,
				null,//String ucfOrderVch2551,
				null,//String ucfOrderVch2552,
				null,//String ucfOrderVch2553,
				null,//String ucfOrderVch40001,
				null,//String ucfOrderVch40002,
				null,//String workDepartment,
				null,//String workCenter,
				null,//String customer,
				null,//String customerOrderNumber,
				null,//String contract,
				null,//String replacementAction,
				null,//String asWorkedBomId,
				null,//String relatedOrderId,
				displaySequence,//String displaySequence,
				null,//String bomNumber,
				null,//String mfgBomChg, // FND-17760
				null,//String planType, // FND-21200
				null,//String aliasPartNumber,
				null,//String aliasPartChange,
				null,//String configName, // FND-23204
				null,//String bomId, // FND-23462
				null,//String explicitBomLink, // FND-24392
				null,//String stockUom, // FND-24790
				false,//boolean createInspectionOrder,
				null);//String calledFromService);
	}

	// SMRO_WOE_304 Sub Orders
	public void createSubOrderRepairPre(String hideParams, String params, String pwOrderNumber, String orderId, String tag) throws Exception {

		String title = null;
		String udvId = "PWUST_8DBF674E213F8E7AE05387971F0ADAEF";
		Number qty = 0;

		params = "PARENT_ORDER_NO='" + pwOrderNumber + "',TAG='"+ tag + "'," + params ;

		if("STD".equals(tag)) {
			title = "Standard Sub Order";
		}
		else if("REPLACE".equals(tag)) {
			title = "Replace Parent Order";
			udvId = "PWUST_8F8F5785332E8B96E05387971F0A746B";
			qty   = orderDaoPW.selectSerialNoQty(orderId);
			params = "QTY='" + qty + "'," + params ;
		}
		else if("REWORK".equals(tag)) {
			title = "Rework Sub Order";
		}

		event.showUdv(udvId,
				INSERT,
				title,
				null,
				params.toString());	       	       

	}

	// SMRO_WOE_304 Sub Orders
	@SuppressWarnings("rawtypes")
	public void createSubOrderRepairPreWi( String partNumber,
			String partChange,
			String newPWSubOrderNo, // not used
			String lotNumber,
			String serialFlag,
			String lotFlag,
			String program,
			String workLocation,
			String itemType,
			String itemSubtype,
			String notes,
			String owpId,
			String displaySequence, 
			String parentOrderId,
			String parentOrderNo,
			String workFlow,
			String configName,
			String parentPlanNo, 
			String parentPlanId,       
			Number parentPlanVer,  
			Number parentPlanRev,  
			Number parentPlanAlt,  
			Number parentPlanUpdtNo,      
			String parentPlanType,   
			Number qty,                    
			String serialNumberList,           
			String tag) throws Exception{   


		parentPlanId = commonDaoPW.getPlanIdFromPlanNo(parentPlanNo); //defect 1742 get the plan no associated with the selected plan from the pulldown


		validator.checkUserPrivilege("PWUST_SUB_ORDER_CREATE"); //Defect 468

		List list = commonDaoPW.selectPWOrderDataByPWOrderNo(parentOrderNo);
		Map pwOrderDataMap = (Map) list.iterator().next();
		String parentOrderStatus = (String) pwOrderDataMap.get("ORDER_STATUS");
		String parentOrderType = (String) pwOrderDataMap.get("ORDER_TYPE");

		if (!parentOrderType.equalsIgnoreCase("REPAIR")){
			message.raiseError("MFI_20", "Can only create a Sub Order from REPAIR order type ");
		}

		if (!StringUtils.equalsIgnoreCase(parentOrderStatus, "IN QUEUE") && !StringUtils.equalsIgnoreCase(parentOrderStatus, "ACTIVE")){
			message.raiseError("MFI_20", "Can only create a Sub Orders when order status is Active or In Queue");
		}		   

		String itemId = orderDao.selectItemId(partNumber, partChange);
		String todaysDate = orderCreateDaoPW.selectDBTimeStamp("MM/DD/YYYY HH:MM:SS");
		Number orderQuantity = 1;

		String[] orderIdArray = new String[1];
		orderIdArray[0]=parentOrderId;

		Date scheduledStartDate = SoluminaUtils.parseDate(todaysDate);
		Date scheduledEndDate = SoluminaUtils.parseDate("01/01/2999");
		Date[] scheduledEndDateArray = new Date[1];
		scheduledEndDateArray[0]=scheduledEndDate;

		if("REPLACE".equals(tag))
		{
			serialNumberList = orderDaoPW.selectSerialNumberListAgg(parentOrderId);
			if(serialNumberList == null)
			{
				message.raiseError("MFI_20", "No serial numbers available in parent order ");
			}
		}


		String newSubOrderId = orderMroRepairSplitPW.startRepairOrderSplit(parentOrderNo, 
				serialNumberList, 
				null, // newOrderNo
				notes, 
				partNumber, 
				partChange, 
				parentPlanNo, 
				parentPlanId, 
				parentPlanVer, 
				parentPlanRev, 
				parentPlanAlt,
				tag);

		//1513
		if (( newSubOrderId != null) && (!StringUtils.equals(tag, "STD") && (!StringUtils.equals(tag, "REWORK")))) //defect 1654
		{
			orderDetails.updateOrderStatus(parentOrderId, "CLOSE", null);
		}

		event.launchMROManufacturing(newSubOrderId);

	}
}
