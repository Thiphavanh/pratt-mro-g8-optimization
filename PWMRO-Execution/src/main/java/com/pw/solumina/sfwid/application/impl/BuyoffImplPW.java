/*
 *  This unpublished work, first created in 2018 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2018,2021.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    BuyoffImplPW.java
 * 
 *  Created: 2018-01-09
 * 
 *  Author:  Raeann Thorpe
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2018-01-09		R. Thorpe		Initial Release SMRO_WOE_202 - Buyoffs
 *  2017-08-18      N.Gnassounou    SMRO_WOE_204
 *  2018-03-12      R. Thorpe       Defect 347 - Buyoffs on Supplemental Orders
 *  2018-03-16      R. Thorpe       Defect 416 - acknowledge work instructions read before last buyoff
 *  2018-04-18      D. Miron        SMRO_WOE_202 Defect 695: Added call to setNaNdStatusNull to set ucfSerialOperationBuyoffVch1 
 *                                  to null when status is REOPEN.
 *  2018-04-19      R. Thorpe		Defect 696 - Do NOT close buyoff when percent is 100 and from Partial.  Added method selectPartialBuyoffExecutionParameters
 *                                               Removed check for 100% and partial since they do not want it completed under those circumstances
 *  2018-04-27      R. Thorpe       SMRO_WOE_202 Defect 727 - Added call to Method isFinalBuyoffBlockInOper 
 *  2018-05-01      R. Thorpe       SMRO_WOE_202 Defect 727 - Added check if (StringUtils.equals(action, "ACCEPT")) around isFinalBuyoffBlock
 *  2018-05-03      R. Thorpe       SMRO_WOE_202 Defect 727 - Added orderId as a parameter in call to isworkInstructionsRead
 *  2018-05-07		R. Thorpe       SMRO_WOE_202 Defect 727 - Added call to method isSupplementalWorkInstructionsRead
 *  2018-05-11      R. Thorpe       SMRO_WOE_202 Defect 727 - Added check for alterationWorkInstructionsRead
 *  2018-05-16      R. Thorpe       SMRO_WOE_202 Defect 727 - Commented out check for work instruction block of code TEMPORARILY  
 *  2018-05-16      R. Thorpe       SMRO_WOE_202 Defect 792 - Removed Plan revision as param in call to  isworkInstructionsRead and
 *                                  added check for global parameter READ_ACKNOWLEDGE_ENABLE
 *  2018-05-31      D. Miron        SMRO_WOE_202 Defect 778 - Copied insertBuyoffExecution from OOTB along with all protected methods it calls.
 *  2018-06-07      R. Thorpe       SMRO_WOE_202 Defect 712 - Changes for Partial buyoffs
 *  2018-08-04      D. Miron        SMRO_WOE_202 Defect 927 - Partial buyoffs updated.  Moved code from preInsertBuyoffExecution to preExecutionInsert2
 *  2018-08-15      R. Thorpe       SMRO_WOE_202 Defect 949 - Remove step_key as a param to isSupplementalWorkInstructionsRead
 *  2018-09-06      D. Miron        R2 Upgrade: Replaced insertBuyoffExecution and protected methods with latest ootb from R2. 
 *  2018-12-07      D. Miron        SMRO_WOE_202 Defect 947 - Removed extra record from Buyoff History Tab on buyoff reopen
 *  2018-12-11      B. Preston      SMRO_USR_203 Defect 1005,1024 - Removed permission check and added calls to updateSerialBuyoffTimeStamp.
 *  2018-12-12      B. Preston      Defect 1024 - Removed calls to updateSerialBuyoffTimeStamp as per Dave.
 *  2019-01-21	    R. Thorpe       Defect 1099 - Added check for REOPEN Status before updating data collection status
 *  2019-04-12      B. Preston      SMRO_USR_203 Defect 1005 Put back permission check.
 *  2019-05-10      R. Thorpe       SMRO_WOE_302 (Defect 1258) - Added "REPAIR" as an ordertype when checking for Read acknowledge in preInsertBuyoffExecution
 *                                                Added loop for SerialNumers in REOPEN.  Removed blocks of commented out code.  see previous revision.
 *  2019-06-07      R. Thorpe       SMRO_WOE_302 Defect 1033 - modified preExecutionInsert2                                                    
 *  2019-06-12      D. Miron        Defect 1208 - Copied latest source code from R2SP4.
 *  2019-11-23      D. Miron        SMRO_WOE_202 Defect 1424 - R2 upgrade issue, reinstated update from Defect 778.
 *  2020-04-09      Fred Ettefagh   SMRO_WOE_302 - Defect 1480,1370 added new method checkPWRules and changed method preInsertBuyoffExecution, preExecutionInsert2preExecutionInsert2
 *  2020-05-15      Fred Ettefagh   SMRO_WOE_302 - Defect 1480 changed methods preInsertBuyoffExecution and  preExecutionInsert2 so that for only overhaul order type custom code is called  
 *  2020-05-26      Fred Ettefagh   SMRO_WOE_302 - Defect 1480 changed methods preInsertBuyoffExecution and  preExecutionInsert2 so that for orders that do not have serial numbers custom code is called
 *  2020-06-19      Fred Ettefagh   Defect 1683  - changed methods preInsertBuyoffExecution and preExecutionInsert2 to use serial_flag data to call custom code for repair vs overhaudl code
 *  2020-08-05      Fred Ettefagh   Defect 1734 - Changed buyoff NA/ND code to call oob method 
 *  2020-08-14      Fred Ettefagh   Defect 1748 - Changed method preExecutionInsert2, to show correct buyoff userId and timestamp when accepting a buyoff, or NA/ND or partial buyoff
 *  2020_08-18      Fred Ettefagh   Defect 1734 and 1748 - changed code to get save/get buyoff timestamp and userId from sfwid_oper_buyoff ucf columns. Also change code to set Data col to Optional  prior to NA/ND
 *  2020-08-23      Fred Ettefagh   Defect 1753 - Added code to implement PW rules on accepting buyoffs (N/A, N/D, Partial and Accept)
 *  2020-08-24      Fred Ettefagh   Defect 1755 - Changed code pass tagType to oob method as ucfSerialOperationBuyoffVch1
 *  2020-09-04      John DeNinno    Defect 1714 - added calledFrom to show where thumbs up came from for cert code check later
 *  2020-09-10      D. Miron        Defect 1760 - When Buyoff is accepted, display message with DC is being skipped.  Don't display msg for N/A and N/D.
 *  2020-09-10      D. Miron        Defect 1761 - Update skipped DC's to pending when reopening N/A or N/D buyoff. 
 *  2020-09-15      J DeNinno       Defect 1776 - Sequence number error
 *  2020-09-18		S. Edgerly		defect 1768 - Repair Labor Message update for Operation Final Confirmation.
 *  2020-10-02      John DeNinno,Fred    Defect 1772 - added code to insert into new custom PW buyoff hist table
 *  2020-10-09      Fred Ettefagh   Defect 1792, changed code to get full user name by using secondLoginUser 
 *  2020-10-12      Fred Ettefagh   Defect 1791, changed code in method insertIntoPWBuyoffHistTable
 *  2020-10-23      Fred Ettefagh   Defect 1796, changed method preExecutionInsert2
 *  2021-06-25      John DeNinno    Defect 1908 username was coming in null on group jobs 
 *  2021-07-28      John DeNinno    Defect 1946 Serial numbers from group jobs not appearing in buyoff history tab
 */
package com.pw.solumina.sfwid.application.impl;

import static com.ibaset.common.DbColumnNameConstants.ALT_COUNT;
import static com.ibaset.common.DbColumnNameConstants.ALT_ID;
import static com.ibaset.common.DbColumnNameConstants.BUYOFF_STATUS;
import static com.ibaset.common.DbColumnNameConstants.COMPLETE_QTY;
import static com.ibaset.common.DbColumnNameConstants.DISC_ID;
import static com.ibaset.common.DbColumnNameConstants.DISC_LINE_NO;
import static com.ibaset.common.DbColumnNameConstants.LOT_ID;
import static com.ibaset.common.DbColumnNameConstants.LOT_NO;
import static com.ibaset.common.DbColumnNameConstants.OPERATION_OVERLAP_FLAG;
import static com.ibaset.common.DbColumnNameConstants.OPER_KEY;
import static com.ibaset.common.DbColumnNameConstants.ORDER_ID;
import static com.ibaset.common.DbColumnNameConstants.ORDER_NO;
import static com.ibaset.common.DbColumnNameConstants.ORDER_QTY;
import static com.ibaset.common.DbColumnNameConstants.ORDER_SCRAP_QTY;
import static com.ibaset.common.DbColumnNameConstants.PLAN_ID;
import static com.ibaset.common.DbColumnNameConstants.SERIAL_FLAG;
import static com.ibaset.common.DbColumnNameConstants.SERIAL_ID;
import static com.ibaset.common.DbColumnNameConstants.SERIAL_NO_COLUMN;
import static com.ibaset.common.DbColumnNameConstants.UPDT_USERID;
import static com.ibaset.common.FrameworkConstants.ACCEPT;
import static com.ibaset.common.FrameworkConstants.ANY;
import static com.ibaset.common.FrameworkConstants.FOUNDATION;
import static com.ibaset.common.FrameworkConstants.INSERTED;
import static com.ibaset.common.FrameworkConstants.NO;
import static com.ibaset.common.FrameworkConstants.NOT_APPLICABLE;
import static com.ibaset.common.FrameworkConstants.UPDATED;
import static com.ibaset.common.FrameworkConstants.YES;
import static com.ibaset.common.SoluminaConstants.ACCEPT_ALL;
import static com.ibaset.common.SoluminaConstants.AT_SIGN;
import static com.ibaset.common.SoluminaConstants.BUYOFF;
import static com.ibaset.common.SoluminaConstants.CLOSE;
import static com.ibaset.common.SoluminaConstants.DAT_COL_OUTSIDE;
import static com.ibaset.common.SoluminaConstants.DELETE;
import static com.ibaset.common.SoluminaConstants.DISP;
import static com.ibaset.common.SoluminaConstants.IMPLICIT_SKIPPED;
import static com.ibaset.common.SoluminaConstants.INITIATE_DISCREPANCY;
import static com.ibaset.common.SoluminaConstants.INSERT;
import static com.ibaset.common.SoluminaConstants.LOT;
import static com.ibaset.common.SoluminaConstants.LowTouch;
import static com.ibaset.common.SoluminaConstants.MAX;
import static com.ibaset.common.SoluminaConstants.MFG2_TYPE;
import static com.ibaset.common.SoluminaConstants.MFG_TYPE;
import static com.ibaset.common.SoluminaConstants.MIN;
import static com.ibaset.common.SoluminaConstants.N;
import static com.ibaset.common.SoluminaConstants.OFF;
import static com.ibaset.common.SoluminaConstants.OPER_UNDERSCORE_NO;
import static com.ibaset.common.SoluminaConstants.PARTIAL;
import static com.ibaset.common.SoluminaConstants.PERCENT;
import static com.ibaset.common.SoluminaConstants.QA_PR;
import static com.ibaset.common.SoluminaConstants.QTY;
import static com.ibaset.common.SoluminaConstants.READ_ACKNOWLEDGE_ENABLE;
import static com.ibaset.common.SoluminaConstants.REJECT;
import static com.ibaset.common.SoluminaConstants.REJECT_BUYOFF;
import static com.ibaset.common.SoluminaConstants.REOPEN;
import static com.ibaset.common.SoluminaConstants.REOPEN_PRIOR_BUYOFF;
import static com.ibaset.common.SoluminaConstants.SEMI_COLON;
import static com.ibaset.common.SoluminaConstants.SERIAL;
import static com.ibaset.common.SoluminaConstants.SKIP;
import static com.ibaset.common.SoluminaConstants.TECH2_TYPE;
import static com.ibaset.common.SoluminaConstants.TECH_TYPE;
import static com.ibaset.common.SoluminaConstants.TempOrderCtrlNextInfo;
import static com.ibaset.common.SoluminaConstants.UDV;
import static com.ibaset.common.SoluminaConstants.WORK_ORDER;
import static com.ibaset.common.SoluminaConstants.Y;
import static com.ibaset.common.SoluminaConstants.ZERO;
import static com.ibaset.common.util.SoluminaUtils.stringEquals;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.StringUtils.contains;
import static org.apache.commons.lang.StringUtils.defaultIfEmpty;
import static org.apache.commons.lang.StringUtils.equalsIgnoreCase;
import static org.apache.commons.lang.StringUtils.isEmpty;
import static org.apache.commons.lang.StringUtils.isNotEmpty;
import static org.apache.commons.lang.StringUtils.lastIndexOf;
import static org.apache.commons.lang.StringUtils.substring;
import static org.apache.commons.lang.StringUtils.upperCase;
import static org.apache.commons.lang.math.NumberUtils.INTEGER_MINUS_ONE;
import static org.apache.commons.lang.math.NumberUtils.INTEGER_ZERO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.security.context.UserContext;
import com.ibaset.common.solumina.IValidator;
import com.ibaset.common.util.LanguageUtils;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sfcore.application.IUser;
import com.ibaset.solumina.sffnd.application.ICertification;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.application.IParse;
import com.ibaset.solumina.sffnd.application.IUserActivity;
import com.ibaset.solumina.sffnd.application.IWrapUtils;
import com.ibaset.solumina.sffnd.dao.IGlobalConfigurationDao;
import com.ibaset.solumina.sfor.application.ISfwidUndo;
import com.ibaset.solumina.sfqa.dao.IDiscrepancyDao;
import com.ibaset.solumina.sfsqa.application.IChangeLog;
import com.ibaset.solumina.sfwid.application.IBuyoff;
import com.ibaset.solumina.sfwid.application.IBuyoffExecution;
import com.ibaset.solumina.sfwid.application.IBuyoffRollup;
import com.ibaset.solumina.sfwid.application.ICDCExecution;
import com.ibaset.solumina.sfwid.application.IGroupJob;
import com.ibaset.solumina.sfwid.application.IGroupJobSignOff;
import com.ibaset.solumina.sfwid.application.IOperation;
import com.ibaset.solumina.sfwid.application.IOperationComplete;
import com.ibaset.solumina.sfwid.application.IOrder;
import com.ibaset.solumina.sfwid.application.IOrderDetails;
import com.ibaset.solumina.sfwid.application.IOrderExecutionValidator;
import com.ibaset.solumina.sfwid.application.IOrderHelper;
import com.ibaset.solumina.sfwid.application.ISerial;
import com.ibaset.solumina.sfwid.application.ISerialOperation;
import com.ibaset.solumina.sfwid.application.ISerialOperationOfd;
import com.ibaset.solumina.sfwid.application.ISerialOperationSignOnOff;
import com.ibaset.solumina.sfwid.application.IWorkOrderStatus;
import com.ibaset.solumina.sfwid.application.impl.BuyoffImpl;
import com.ibaset.solumina.sfwid.application.impl.SerialInformation;
import com.ibaset.solumina.sfwid.dao.IAssembleDao;
import com.ibaset.solumina.sfwid.dao.IBuyoffDao;
import com.ibaset.solumina.sfwid.dao.IOperationDao;
import com.ibaset.solumina.sfwid.dao.IOrderDao;
import com.ibaset.solumina.sfwid.dao.IPartDao;
import com.ibaset.solumina.sfwid.domain.UnitInformation;
import com.pw.common.dao.CommonDaoPW;
import com.pw.solumina.sfwid.application.OperationPW;
import com.pw.solumina.sfwid.dao.BuyoffDaoPW;

public abstract class BuyoffImplPW extends ImplementationOf<IBuyoff> implements IBuyoff
{
	@Reference private IOrder order = null;
	@Reference private BuyoffDaoPW buyoffDaoPW = null;
	@Reference private IOrderDao orderDaoImpl = null;
	@Reference private IBuyoff buyoff = null;
	@Reference private OperationPW operationPW = null; //defect 1768
	@Reference private CommonDaoPW commonDaoPW = null;
	@Reference private IBuyoffDao buyoffDao = null;
	@Reference private IBuyoffRollup buyoffRollup = null;
	@Reference private IDiscrepancyDao discrepancyDao = null;
	@Reference private IEvent event = null;
	@Reference private IMessage message = null;
	@Reference private IOperation operation = null;
	@Reference private IOrderDao orderDao = null;
	@Reference private IOrderDetails orderDetails = null;
	@Reference private IParse parse = null;
	@Reference private IUser privilege = null;
	@Reference private ISerial serial = null;
	@Reference private ISerialOperation serialOperation = null;
	@Reference private ISerialOperationOfd serialOperationOfd = null;
	@Reference private IValidator validator = null;
	@Reference private IWrapUtils wrapUtils = null;
	@Reference private ISfwidUndo sfwidUndo = null;
	@Reference private com.ibaset.solumina.sffnd.application.IUser sffndUser = null;
	@Reference private ICertification certification = null;
	@Reference private IOperationDao operationDao = null;
	@Reference private IGlobalConfigurationDao globalConfigurationsDao = null;
	@Reference private IAssembleDao assembleDao = null;
	@Reference private IPartDao partDao = null;
	@Reference private IChangeLog changeLogImpl = null;
	@Reference private IOrderExecutionValidator orderExecValidator = null;
	@Reference private ICDCExecution cdcExecution = null;
	@Reference private IBuyoffExecution buyoffExecution;
	@Reference private ISerialOperationSignOnOff serialOperSignOnOff;
	@Reference private IWorkOrderStatus workOrderStatus = null;
	@Reference private IOrderHelper orderHelper;
	@Reference private IOperationComplete operationComplete;
	@Reference private IGroupJobSignOff groupJobSignOff = null;
	@Reference private IGroupJob groupJob;
	@Reference private IUserActivity userActivity = null;

	protected final Log logger = LogFactory.getLog(BuyoffImpl.class);
	private static final String TEMP_PARTIAL_COMPLETE="TEMP_PARTIAL_COMPLETE";
	private static final String PARTIAL_COMPLETE_VALUE="PARTIAL_COMPLETE_VALUE";

	public void checkForMixMatchBuyoffStatus(String orderId, Number operationKey, Number stepKey, String buyoffId, String buyoffStatus , String serialNumberList, String calledFromNAND) {

		// check for Pw bus Rules for buyoffs
		// for one buyoff-grid and a one serial no
		// if buyoff status is accepted via N/A then all serial no for buyoff-grid be accepted via N/A
		// if buyoff status is accepted via N/D then all serial no for buyoff-grid be accepted via N/D
		// if buyoff status is accepted not via N/D or N/A then all serial no for buyoff-grid cannot be accepted via N/A or N/D 

		String buyoffMixMatchCheck = commonDaoPW.getPwustGlobalConfigValue("BUYOFF_MIX_MATCH_CHECK_ENABLED");
		if (StringUtils.isBlank(buyoffMixMatchCheck) || StringUtils.isEmpty(buyoffMixMatchCheck)){
			buyoffMixMatchCheck = "Y";
		}

		if (StringUtils.equalsIgnoreCase(buyoffMixMatchCheck, "Y")){

			List sfwidOperBuyoffList = buyoffDaoPW.selectBuyoffInfoFromOperBuyoff(orderId, operationKey, stepKey, buyoffId);
			Map sfwidOperBuyoffMap = (Map) sfwidOperBuyoffList.get(0);
			String refId = (String) sfwidOperBuyoffMap.get("REF_ID");

			// System.out.println("checkForMixMatchBuyoffStatus orderId = " + orderId);
			// System.out.println("checkForMixMatchBuyoffStatus operationKey = " + operationKey);
			// System.out.println("checkForMixMatchBuyoffStatus stepKey = " + stepKey);
			// System.out.println("checkForMixMatchBuyoffStatus buyoffId = " + buyoffId);
			// System.out.println("checkForMixMatchBuyoffStatus buyoffStatus = " + buyoffStatus);
			// System.out.println("checkForMixMatchBuyoffStatus serialNumberList = " + serialNumberList);
			// System.out.println("checkForMixMatchBuyoffStatus calledFromNAND = " + calledFromNAND);
			// System.out.println("checkForMixMatchBuyoffStatus refId = " + refId);


			//if (StringUtils.isNotEmpty((serialNumberList)) {
			StringTokenizer serialNoTokens = new StringTokenizer(serialNumberList, ";");
			while (serialNoTokens.hasMoreElements()){
				String serialNo = serialNoTokens.nextElement().toString();
				// System.out.println("checkForMixMatchBuyoffStatus serialNo = " + serialNo);
				if (StringUtils.equalsIgnoreCase(calledFromNAND, "N/A")) {

					// end user is  accepting a buyoff via N/A 
					{
						List buyoffsAcceptedViaOOB = buyoffDaoPW.selectSfwidSerialOperBuyoffAcceptedViaOOBAccept(orderId, operationKey, stepKey, refId, serialNo ,"N/A", "N/D");
						if (buyoffsAcceptedViaOOB!=null && !buyoffsAcceptedViaOOB.isEmpty()){
							if (StringUtils.equalsIgnoreCase(serialNo, "N/A")){
								message.raiseError("MFI_20", "Cannot set status to N/A , prior buyffoffs has been set to ACCEPT");
							}else{
								message.raiseError("MFI_20", "Cannot set status to N/A , prior buyffoffs has been set to ACCEPT for serial Number " + serialNo);
							}
						}
					}

					{
						List buyoffsAcceptedViaND = buyoffDaoPW.selectSfwidSerialOperBuyoffAccpetedviaTagType(orderId, operationKey, stepKey, refId, serialNo, "N/D");
						if (buyoffsAcceptedViaND!=null && !buyoffsAcceptedViaND.isEmpty()){
							if (StringUtils.equalsIgnoreCase(serialNo, "N/A")){
								message.raiseError("MFI_20", "Cannot set status to N/A , prior buyffoffs has been set to N/D");
							}else{
								message.raiseError("MFI_20", "Cannot set status to N/A , prior buyffoffs has been set to N/D for serial Number " + serialNo);
							}
						}
					}

				}else if (StringUtils.equalsIgnoreCase(calledFromNAND, "N/D")) {

					// end user is  accepting a buyoff via ND
					{
						List buyoffsAcceptedViaOOB = buyoffDaoPW.selectSfwidSerialOperBuyoffAcceptedViaOOBAccept(orderId, operationKey, stepKey, refId, serialNo ,"N/A", "N/D");
						if (buyoffsAcceptedViaOOB!=null && !buyoffsAcceptedViaOOB.isEmpty()){
							if (StringUtils.equalsIgnoreCase(serialNo, "N/A")){
								message.raiseError("MFI_20", "Cannot set status to N/D , prior buyffoffs has been set to ACCEPT");
							}else{
								message.raiseError("MFI_20", "Cannot set status to N/D , prior buyffoffs has been set to ACCEPT for serial Number " + serialNo);
							}
						}
					}

					{
						List buyoffsAcceptedViaND = buyoffDaoPW.selectSfwidSerialOperBuyoffAccpetedviaTagType(orderId, operationKey, stepKey, refId,serialNo, "N/A");
						if (buyoffsAcceptedViaND!=null && !buyoffsAcceptedViaND.isEmpty()){
							if (StringUtils.equalsIgnoreCase(serialNo, "N/A")){
								message.raiseError("MFI_20", "Cannot set status to N/D , prior buyffoffs has been set to N/A");
							}else{
								message.raiseError("MFI_20", "Cannot set status to N/D , prior buyffoffs has been set to N/A for serial Number " + serialNo);
							}
						}
					}

				}else{
					// end user is accepting a buyoff but not via NA  or ND
					List buyoffsAcceptedViaNAND = buyoffDaoPW.selectSfwidSerialOperBuyoffAccpetedviaNAandND(orderId, operationKey, stepKey, refId, serialNo, "N/A", "N/D");
					if (buyoffsAcceptedViaNAND!=null && !buyoffsAcceptedViaNAND.isEmpty()){
						if (buyoffsAcceptedViaNAND!=null && !buyoffsAcceptedViaNAND.isEmpty()){
							if (StringUtils.equalsIgnoreCase(serialNo, "N/A")){
								message.raiseError("MFI_20", "Cannot set status to ACCEPT , prior buyffoffs has been set to  N/A or N/D");
							}else{
								message.raiseError("MFI_20", "Cannot set status to ACCEPT , prior buyffoffs has been set to  N/A or N/D for serial Number " + serialNo);
							}
						}
					}
				}
			}
		}
	} 


	public void preInsertBuyoffExecution(   String orderId, 
			Number operationKey, 
			Number stepKey, 
			String lotId, 
			String serialId,
			String buyoffIds, 
			String action, 
			String buyoffTypes, 
			String buyoffCertifications, 
			String calledFrom,
			String buyoffComment, 
			String rejectType, 
			String operationNumber, 
			String stepNumber, 
			String crossOrderFlag,
			String control, 
			String groupJobNo) {
		// System.out.println("preInsertBuyoffExecution - action is " + action);
		// System.out.println("preInsertBuyoffExecution - action is " + calledFrom);
		calledFrom = "GRID"; // defect 1714
		String serialFlag = getOrderSerialFlag(orderId);
		String oobOrderType = checkPWRules(orderId, operationKey, action,serialFlag);
		// System.out.println("action is " + action);
		if (!StringUtils.equals(oobOrderType, "Supplemental")){

			boolean suppExists = buyoffDaoPW.getSupplementalBuyoffStatus(orderId, operationNumber);
			if(suppExists){
				message.raiseError("MFI_20", "Buyoff not allowed when Supplemental Order is still open");
			}else{
				//supplemental order does not exist for OVERHAUL OR SUB ORDER.  Process as normal
				Super.preInsertBuyoffExecution(orderId,
						operationKey,
						stepKey,
						lotId,
						serialId,
						buyoffIds,
						action,
						buyoffTypes,
						buyoffCertifications,
						calledFrom,
						buyoffComment, 
						rejectType,
						operationNumber, 
						stepNumber, 
						crossOrderFlag, 
						control, 
						groupJobNo);
			}
		}else{ 
			//This is a supplemental order. Do the buyoff
			this.Super.preInsertBuyoffExecution(orderId,
					operationKey,
					stepKey,
					lotId,
					serialId,
					buyoffIds,
					action,
					buyoffTypes,
					buyoffCertifications,
					calledFrom,
					buyoffComment, 
					rejectType,
					operationNumber, 
					stepNumber, 
					crossOrderFlag, 
					control, 
					groupJobNo);
		}
	}


	@SuppressWarnings("rawtypes")
	public String getOrderSerialFlag(String orderId) {

		List sfwidOrderDesclist = buyoffDaoPW.selectSfwidOrderDesc(orderId);
		Map sfwidOrderDescMap = (Map)sfwidOrderDesclist.get(0);
		String serialFlag = (String)sfwidOrderDescMap.get("SERIAL_FLAG");
		if (StringUtils.isBlank(serialFlag)){
			message.raiseError("MFI_20", "serialFlag cannot be null");
		}
		return serialFlag;
	}



	@SuppressWarnings("deprecation")
	public void preExecutionInsert2(    String orderId, 
			String orderNumberList,
			Number operationKey,
			Number stepKey,
			String lotNumberList, 
			String serialNumberList,
			String buyoffId,
			String status, 
			String buyoffType,
			String buyoffCertificate, 
			String buyoffComment, 
			String calledFrom, 
			String ucfSerialOperationBuyoffVch1,
			String ucfSerialOperationBuyoffVch2,
			String ucfSerialOperationBuyoffVch3,
			String ucfSerialOperationBuyoffVch4,
			String ucfSerialOperationBuyoffVch5,
			String ucfSerialOperationBuyoffVch6,
			String ucfSerialOperationBuyoffVch7,
			String ucfSerialOperationBuyoffVch8,
			String ucfSerialOperationBuyoffVch9,
			String ucfSerialOperationBuyoffVch10,
			String ucfSerialOperationBuyoffVch11,
			String ucfSerialOperationBuyoffVch12,
			String ucfSerialOperationBuyoffVch13,
			String ucfSerialOperationBuyoffVch14,
			String ucfSerialOperationBuyoffVch15,
			Number ucfSerialOperationBuyoffNum1,
			Number ucfSerialOperationBuyoffNum2,
			Number ucfSerialOperationBuyoffNum3,
			Number ucfSerialOperationBuyoffNum4,
			Number ucfSerialOperationBuyoffNum5, 
			String ucfSerialOperationBuyoffFlag1,
			String ucfSerialOperationBuyoffFlag2, 
			String ucfSerialOperationBuyoffFlag3,
			String ucfSerialOperationBuyoffFlag4,
			String ucfSerialOperationBuyoffFlag5,
			Date ucfSerialOperationBuyoffDate1, 
			Date ucfSerialOperationBuyoffDate2,
			Date ucfSerialOperationBuyoffDate3,
			Date ucfSerialOperationBuyoffDate4,
			Date ucfSerialOperationBuyoffDate5, 
			String rejectType,
			String ucfSerialOperationBuyoffVch2551, 
			String ucfSerialOperationBuyoffVch2552,
			String ucfSerialOperationBuyoffVch2553, 
			String ucfSerialOperationBuyoffVch40001,
			String ucfSerialOperationBuyoffVch40002, 
			Number percentComplete, 
			Number qtyComplete, 
			String partialComplete,
			String oldUserId, 
			String secondLoginUser,
			String groupJobNo,
			String serialInfo) {

		// System.out.println("BuyoffImplPw.preExecutionInsert2 - calledFrom is " + calledFrom);
		StringTokenizer serialNoTokens;  //1258
		String serialNo = "";  //1258
		String delimiter = ";";  //1258
		String orderNo = ""; //defect 1946


		if ("ACCEPT".equals(status)){
			//String partialFlag = buyoffDaoPW.getPartialFlag(orderId, operationKey, stepKey, buyoffId);  Defect 1258
			int partialCount  = buyoffDaoPW.getPartialFlagCount(orderId, operationKey, stepKey, buyoffId);
			if (partialCount > 0){
				ucfSerialOperationBuyoffFlag1 = "X";   
			}
		}

		if (StringUtils.equalsIgnoreCase(status, "ACCEPT")){
			checkForMixMatchBuyoffStatus(orderId, 
					operationKey, 
					stepKey, 
					buyoffId, 
					status,
					serialNumberList,
					ucfSerialOperationBuyoffVch1);  // Fred E.  This is tagType based in from UDV, values can be N/A or N/D
		}	
		ucfSerialOperationBuyoffVch10 = null;

		this.Super.preExecutionInsert2(orderId,
				orderNumberList,
				operationKey,
				stepKey,
				lotNumberList,
				serialNumberList,
				buyoffId,
				status,
				buyoffType,
				buyoffCertificate,
				buyoffComment,
				calledFrom,
				ucfSerialOperationBuyoffVch1,
				ucfSerialOperationBuyoffVch2,
				ucfSerialOperationBuyoffVch3,
				ucfSerialOperationBuyoffVch4,
				ucfSerialOperationBuyoffVch5,
				ucfSerialOperationBuyoffVch6,
				ucfSerialOperationBuyoffVch7,
				ucfSerialOperationBuyoffVch8,
				ucfSerialOperationBuyoffVch9,
				ucfSerialOperationBuyoffVch10,
				ucfSerialOperationBuyoffVch11,
				ucfSerialOperationBuyoffVch12,
				ucfSerialOperationBuyoffVch13,
				ucfSerialOperationBuyoffVch14,
				ucfSerialOperationBuyoffVch15,
				ucfSerialOperationBuyoffNum1,
				ucfSerialOperationBuyoffNum2,
				ucfSerialOperationBuyoffNum3,
				ucfSerialOperationBuyoffNum4,
				ucfSerialOperationBuyoffNum5,
				ucfSerialOperationBuyoffFlag1,
				ucfSerialOperationBuyoffFlag2,
				ucfSerialOperationBuyoffFlag3,
				ucfSerialOperationBuyoffFlag4,
				ucfSerialOperationBuyoffFlag5,
				ucfSerialOperationBuyoffDate1,
				ucfSerialOperationBuyoffDate2,
				ucfSerialOperationBuyoffDate3,
				ucfSerialOperationBuyoffDate4,
				ucfSerialOperationBuyoffDate5,
				rejectType,
				ucfSerialOperationBuyoffVch2551,
				ucfSerialOperationBuyoffVch2552,
				ucfSerialOperationBuyoffVch2553,
				ucfSerialOperationBuyoffVch40001,
				ucfSerialOperationBuyoffVch40002,
				percentComplete,
				qtyComplete,
				partialComplete,
				oldUserId,
				secondLoginUser, 
				groupJobNo, 
				serialInfo);
		//defect 1908
		if (StringUtils.isEmpty(oldUserId)) {
			oldUserId = ContextUtil.getUsername();
		}
		if (serialNumberList == null || serialNumberList.isEmpty() ){  //Defect 1033
			serialNumberList = "N/A";
		}

		if (StringUtils.equalsIgnoreCase(status, "ACCEPT")){
			// SFWID_OPER_BUYOFF.UCF_OPER_BUYOFF_VCH1  to user id
			// SFWID_OPER_BUYOFF.UCF_OPER_BUYOFF_DATE1 date accepted
			// SFWID_OPER_BUYOFF.UCF_OPER_BUYOFF_VCH3  percent complete 
			operationPW.insertT203Labor(orderId, operationKey, oldUserId, "Final", "1", "100", ";", buyoffType, "X");//defect 1768
			buyoffDaoPW.updateSfwidOperBuyoffUcfOperVch1AndVch2(orderId, operationKey, stepKey, buyoffId, oldUserId, "null");
	
			StringTokenizer orderNoTokens = new StringTokenizer(orderNumberList,delimiter);  //defect 1946


			while (orderNoTokens.hasMoreElements()){ //defect 1946
				orderNo = orderNoTokens.nextElement().toString();  //defect 1946
				orderId = commonDaoPW.getOrderIdOrderNo(orderNo); //defect 1946
				serialNoTokens = new StringTokenizer(serialNumberList, delimiter);
				while (serialNoTokens.hasMoreElements()){ //defect 1946
					serialNo = serialNoTokens.nextElement().toString(); 
					if (buyoffDaoPW.SerialNumberOrderComboExists(orderId,serialNo)) //defect 1946
					{
						Map serialMap = buyoffDaoPW.selectSerialNumberInfo(orderId,serialNo);
						String lotId = (String) serialMap.get("LOT_ID");
						String serialId = (String) serialMap.get("SERIAL_ID");
						orderId = (String)serialMap.get("ORDER_ID");
						buyoffDaoPW.clearSfwidSerialOperBuyoffVch5Date1(orderId, operationKey, stepKey, buyoffId, serialId, lotId);
					}

				}
			}
			// Defect 695
			// Defect 947
			if("REOPEN".equals(status)){

				buyoffDaoPW.updateSfwidOperBuyoffUcfOperVch1AndVch2(orderId, operationKey, stepKey, buyoffId, oldUserId, "null");
				serialNoTokens = new StringTokenizer(serialNumberList, delimiter);
				while (serialNoTokens.hasMoreElements()){
					serialNo = serialNoTokens.nextElement().toString();
					Map serialMap = buyoffDaoPW.selectSerialNumberInfo(orderId,serialNo);
					String lotId = (String) serialMap.get("LOT_ID");
					String serialId = (String) serialMap.get("SERIAL_ID");

					buyoffDaoPW.clearSfwidSerialOperBuyoffVch5Date1(orderId, operationKey, stepKey, buyoffId, serialId, lotId);

					// Defect 1761
					if(buyoffDaoPW.checkNANDBuyoff(orderId, operationKey, stepKey, buyoffId, serialId, lotId)){
						buyoffDaoPW.updateSerialSkipDatColToPending(orderId,operationKey,buyoffId, serialId, lotId);
					}
					if (buyoffDaoPW.clearSfwidSerialOperBuyoffUCFs(orderId, operationKey, stepKey, buyoffId, serialId,lotId)){
						buyoffDaoPW.deleteReopenHistory(orderId, operationKey, stepKey, buyoffId, serialId,lotId);
					}
				}
			}
		}
	}

	public Map selectPartialBuyoffExecutionParameters(String orderId,
			Number percentComplete,
			String partialComplete,
			Number qtyComplete,
			Number operationKey,
			String lotNumberList,
			String serialNumberList,
			String status){

		Map buyoffParameters = new HashMap();
		String buyoffAcceptRequired = NO;
		// Sets the Show Accept UDV flag to 'Y', to call confirm accept
		// udv when percent is '100' or qty is equal to order qty.
		// Defect 696 P&W does NOT want close the buyoff if it is partial and 100%....

		//Get Order Control Details
		Map orderControlDetails=orderDaoImpl.selectOrderControlDetails(orderId);
		// Gets the Order and Serial Lot Information.
		Number orderQuantity =(Number)orderControlDetails.get("ORDER_QTY");

		//Get Order Scrap Qty
		Number orderScrapQuantity =(Number)orderControlDetails.get("ORDER_SCRAP_QTY");
		String operationOverlapFlag = (String) orderControlDetails.get("OPERATION_OVERLAP_FLAG");

		if (StringUtils.equals(partialComplete, QTY)||  StringUtils.equals(operationOverlapFlag, YES))
		{
			if (qtyComplete.intValue() == (orderQuantity.intValue() - orderScrapQuantity.intValue()))//FND-19919
			{
				buyoffAcceptRequired = YES;
			}
		}

		buyoffParameters.put("BUYOFF_ACCEPT_REQUIRED", buyoffAcceptRequired);
		buyoffParameters.put("SERIAL_NO", serialNumberList);
		buyoffParameters.put("SECURITY_GROUP", null);

		return buyoffParameters;
	}

	public void insertBuyoffExecution(String orderId,
			Number operationKey,
			Number stepKey,
			String lotId,
			String serialId,
			String lotNumberList,
			String serialNumberList,
			String buyoffId,
			String status,
			String buyoffType,
			String buyoffCertification,
			String buyoffComment,
			String rejectType,
			String calledFrom,
			String ucfSerialOperationBuyoffVch1,
			String ucfSerialOperationBuyoffVch2,
			String ucfSerialOperationBuyoffVch3,
			String ucfSerialOperationBuyoffVch4,
			String ucfSerialOperationBuyoffVch5,
			String ucfSerialOperationBuyoffVch6,
			String ucfSerialOperationBuyoffVch7,
			String ucfSerialOperationBuyoffVch8,
			String ucfSerialOperationBuyoffVch9,
			String ucfSerialOperationBuyoffVch10,
			String ucfSerialOperationBuyoffVch11,
			String ucfSerialOperationBuyoffVch12,
			String ucfSerialOperationBuyoffVch13,
			String ucfSerialOperationBuyoffVch14,
			String ucfSerialOperationBuyoffVch15,
			Number ucfSerialOperationBuyoffNum1,
			Number ucfSerialOperationBuyoffNum2,
			Number ucfSerialOperationBuyoffNum3,
			Number ucfSerialOperationBuyoffNum4,
			Number ucfSerialOperationBuyoffNum5,
			String ucfSerialOperationBuyoffFlag1,
			String ucfSerialOperationBuyoffFlag2,
			String ucfSerialOperationBuyoffFlag3,
			String ucfSerialOperationBuyoffFlag4,
			String ucfSerialOperationBuyoffFlag5,
			Date ucfSerialOperationBuyoffDate1,
			Date ucfSerialOperationBuyoffDate2,
			Date ucfSerialOperationBuyoffDate3,
			Date ucfSerialOperationBuyoffDate4,
			Date ucfSerialOperationBuyoffDate5,
			String orderNumberList,
			String ucfSerialOperationBuyoffVch2551,
			String ucfSerialOperationBuyoffVch2552,
			String ucfSerialOperationBuyoffVch2553,
			String ucfSerialOperationBuyoffVch40001,
			String ucfSerialOperationBuyoffVch40002,
			Number percentComplete,
			Number qtyComplete,
			String partialComplete,
			String oldUserId,
			String secondLoginUser, 
			String groupJobNo, 
			String serialInfo){

		
		

		SerialOperationBuyoffUcfObjects serialOperBuyoffUcfs =SerialOperationBuyoffUcfObjects.getSerialOperationBuyoffUcfObjects(ucfSerialOperationBuyoffVch1,ucfSerialOperationBuyoffVch2,
				ucfSerialOperationBuyoffVch3,ucfSerialOperationBuyoffVch4,ucfSerialOperationBuyoffVch5,ucfSerialOperationBuyoffVch6,
				ucfSerialOperationBuyoffVch7,ucfSerialOperationBuyoffVch8,ucfSerialOperationBuyoffVch9,ucfSerialOperationBuyoffVch10,
				ucfSerialOperationBuyoffVch11,ucfSerialOperationBuyoffVch12,ucfSerialOperationBuyoffVch13,ucfSerialOperationBuyoffVch14,
				ucfSerialOperationBuyoffVch15,ucfSerialOperationBuyoffNum1,ucfSerialOperationBuyoffNum2,ucfSerialOperationBuyoffNum3,
				ucfSerialOperationBuyoffNum4,ucfSerialOperationBuyoffNum5,ucfSerialOperationBuyoffFlag1,ucfSerialOperationBuyoffFlag2,
				ucfSerialOperationBuyoffFlag3,ucfSerialOperationBuyoffFlag4,ucfSerialOperationBuyoffFlag5,ucfSerialOperationBuyoffDate1,
				ucfSerialOperationBuyoffDate2,ucfSerialOperationBuyoffDate3,ucfSerialOperationBuyoffDate4,ucfSerialOperationBuyoffDate5,
				ucfSerialOperationBuyoffVch2551,ucfSerialOperationBuyoffVch2552,ucfSerialOperationBuyoffVch2553,
				ucfSerialOperationBuyoffVch40001,ucfSerialOperationBuyoffVch40002);

		BuyoffInformation buyoffInformation = new BuyoffInformation();
		buyoffInformation.setOrderId(orderId);
		buyoffInformation.setOperKey(operationKey);
		buyoffInformation.setStepKey(stepKey);
		buyoffInformation.setBuyoffId(buyoffId);
		buyoffInformation.setBuyoffStatus(status);
		buyoffInformation.setBuyoffType(buyoffType);
		buyoffInformation.setBuyoffCertification(buyoffCertification);
		buyoffInformation.setBuyoffComment(buyoffComment);
		buyoffInformation.setRejectType(rejectType);
		buyoffInformation.setPercentComplete(percentComplete);
		buyoffInformation.setPartialComplete(partialComplete);
		buyoffInformation.setSerialOperBuyoffUcfs(serialOperBuyoffUcfs);

		SerialInformation serialInformation = new SerialInformation(serialId, lotId);
		serialInformation.setSerialNumberList(serialNumberList);
		serialInformation.setLotNumberList(lotNumberList);
		serialInformation.setOrderNumberList(orderNumberList);


		OperInformation operInformation = new OperInformation(orderId,operationKey,stepKey);
		operInformation.setQtyComplete(qtyComplete);

		String orderControl = orderDetails.orderControl(orderId);

		OrderInformation orderInformation = new OrderInformation(orderId);
		orderInformation.setSerialInfo(serialInformation);
		orderInformation.setBuyoffInformation(buyoffInformation);
		orderInformation.setOperInformation(operInformation);
		orderInformation.setOrderControl(orderControl);
		orderInformation.setGroupJobNo(groupJobNo);


		// GE-4267
		String readAknowledgeEnable = globalConfigurationsDao.selectParameterValue(FOUNDATION, READ_ACKNOWLEDGE_ENABLE);
		validator.checkMandatoryField(BUYOFF_STATUS, buyoffInformation.getBuyoffStatus());

		// Defect 778
		// String userName = ContextUtil.getUsername();
		String userName = oldUserId;

		//defect 1908
		if (StringUtils.isEmpty(userName))
		{
			userName = ContextUtil.getUsername();
			oldUserId = userName;
			System.out.println("inside null username is " + userName);
		} //end defect 1908

		serialInformation.setSerialArray(parse.parse(serialInformation.getSerialNumberList(),SEMI_COLON,true));
		serialInformation.setLotArray(parse.parse(serialInformation.getLotNumberList(),SEMI_COLON,true));
		serialInformation.setOrderNumberArray(parse.parse(serialInformation.getOrderNumberList(),SEMI_COLON,true));

		setUnitListInSerialInformation(serialInformation, orderInformation.getOrderControl());
		//defect 1776
		//checkUserCollectedReverseBuyoffPrior(orderInformation, calledFrom);

		String oldOrderId = null;
		boolean first = true;
		Number partialCompleteValue = null;
		String orderIdPassed = orderId;
		Number operKeyPassed = operationKey;   // FND-24257
		int progressCounter=0;

		int totalUnits = serialInformation.getList().length+1;
		final UserContext ctx = ContextUtil.getUser().getContext();
		ctx.setProgressMaximum(totalUnits);

		boolean isStatusAccept = stringEquals(buyoffInformation.getBuyoffStatus(),ACCEPT);
		boolean isStatusReject = stringEquals(buyoffInformation.getBuyoffStatus(),REJECT);
		boolean isStatusSkip = stringEquals(buyoffInformation.getBuyoffStatus(),SKIP);
		boolean isStatusInAcceptRejectSkip = isStatusAccept || isStatusReject || isStatusSkip;


		if (StringUtils.equals(calledFrom, "GRID"))
		{
			checkBuyoffCertificateIsExpiredOrNot(operInformation.getOrderId(), 
					buyoffInformation.getBuyoffCertification(),
					userName, 
					isStatusSkip,
					isStatusInAcceptRejectSkip);
		}

		// Check if operation is part of automation process.
		// PIND-6
		validateAndSetQuantitiesForPartialBuyoffCompletion(orderInformation, isStatusInAcceptRejectSkip);
		setOperAvailableQtyNullIfOperationIsNotPartOfContinuousFlow(operInformation);

		setAcknowledgementRelatedParameters(oldUserId,orderInformation,readAknowledgeEnable, userName);

		// Defect 1208 - DXC R2SP4 Upgrade
		//		 		Map<String,Map<String,Object>> allOrdersWithInfo = new HashMap<String,Map<String,Object>>(); // PBL-87
		List<Map<String, Object>> allOrdersWithInfoList = new ArrayList<Map<String, Object>>(); 
		// Defect 1208 - End DXC R2SP4 Upgrade

		List<Map<String, Object>> orderAndSerialLotList = getOrderAndSerialLotList(operInformation.getOrderId(),
				operInformation.getOperationKey(),
				operInformation.getStepKey(),
				buyoffInformation.getBuyoffId(),
				buyoffInformation.getBuyoffStatus(),
				calledFrom,
				orderInformation.getGroupJobNo(),
				serialInformation.getSerialArray(),
				serialInformation.getLotArray(),
				serialInformation.getOrderNumberArray(),
				orderInformation.getOrderControl(),
				serialInformation.getList(),
				operInformation.isOperationPartOfContinuousFlow());

		ListIterator<Map<String,Object>> orderAndSerialLotListIterator = orderAndSerialLotList.listIterator();
		while (orderAndSerialLotListIterator.hasNext())
		{
			ctx.setProgress(progressCounter++);

			partialCompleteValue = executeSerialWiseBuyoff(calledFrom, oldUserId, secondLoginUser,
					serialInfo, orderInformation, userName,
					oldOrderId, first, partialCompleteValue,
					orderIdPassed, isStatusAccept,
					isStatusSkip,
					isStatusInAcceptRejectSkip,
					orderAndSerialLotListIterator);

			// Set Old order Id to current order id when new serial iteration to be started.
			oldOrderId = operInformation.getOrderId();
			first = false;

			Map<String,Object> secondUserSignOffParam = new HashMap<String,Object>();
			secondUserSignOffParam.put(OPER_KEY, operInformation.getOperationKey());
			secondUserSignOffParam.put(BUYOFF_STATUS, buyoffInformation.getBuyoffStatus());
			secondUserSignOffParam.put("COMPLETE_MULTI_OPER_SERIAL", serialInformation.getCompleteMultipleOperationSerial());
			secondUserSignOffParam.put(OPERATION_OVERLAP_FLAG, operInformation.isOperationPartOfContinuousFlow());
			// Defect 1208 - DXC R2SP4 Upgrade			
			secondUserSignOffParam.put(ORDER_ID,oldOrderId);  
			//		  	    allOrdersWithInfo.put(oldOrderId, secondUserSignOffParam);
			allOrdersWithInfoList.add(secondUserSignOffParam); 
		}

		// PBL-87 : System should sign off all group Job orders. So, created distinct order loop
		/*		allOrdersWithInfo.forEach((orderIdToSignOff, params)->{

					// Check if the User Logged In is Second User and the Multiple Serial Operation are going to complete or not.
					validateSecondaryUserAndSignOffPrimaryUser(orderIdToSignOff,
							(Number) params.get(OPER_KEY),
							(String) params.get(BUYOFF_STATUS),
							secondLoginUser, 
							userName,
							(String) params.get("COMPLETE_MULTI_OPER_SERIAL"),
							(boolean) params.get(OPERATION_OVERLAP_FLAG));
				});*/
		for (int i = 0; i<allOrdersWithInfoList.size(); i++) 
		{
			Map<String,Object> params = allOrdersWithInfoList.get(i);

			// Check if the User Logged In is Second User and the Multiple Serial Operation are going to complete or not.
			validateSecondaryUserAndSignOffPrimaryUser((String) params.get(ORDER_ID),
					(Number) params.get(OPER_KEY),
					(String) params.get(BUYOFF_STATUS),
					secondLoginUser, 
					userName,
					(String) params.get("COMPLETE_MULTI_OPER_SERIAL"),
					(boolean) params.get(OPERATION_OVERLAP_FLAG));
		};
		// Defect 1208 - End DXC R2SP4 Upgrade

		// PBL-1485 Sign off group job for secondary user
		groupJobSignOff.signOffGroupJobAutomaticallyForSecondUser(groupJobNo, userName, secondLoginUser);

		//FND-24257
		showAcceptedSerialUnitsForBuyoff(orderInformation, calledFrom);

		// PBL-1485 : Complete Group Job UDV call to complete GJ, related order, operation and sign off all manually 
		//            For last buyoff with second user when all serials are completed
		// Commented as this needs to call only when secondary buyoff is last control and able to complete Group Job and all, 
		//           Not, for the secondary buyoff after which some execution remains 
		/*if(isNotEmpty(orderInformation.getGroupJobNo()) 
		&& Y.equals(secondLoginUser) 
		&& isEmpty(serialInformation.getCompleteMultipleOperationSerial())
		&& !operInformation.isInstructionReadAcknowledgementRequired() 
		&& !operInformation.isDeviationReadAcknowledgementRequired()
		&& !operInformation.isReadAckRequiredForGroupJob() ) 
		{
		serial.completeMultipleOperationForGroupJob(N, Y, groupJobNo);
		}*/

		// Complete the operation for passed order at the end of the loop.
		if (isNotEmpty(serialInformation.getCompleteMultipleOperationSerial())
				&& (!operInformation.isInstructionReadAcknowledgementRequired() 
						&& !operInformation.isDeviationReadAcknowledgementRequired() //FND-25011
						&& !operInformation.isReadAckRequiredForGroupJob()) ) //GE-496
		{
			serial.completeMultipleOperation(orderIdPassed,
					operKeyPassed,
					serialInformation.getCompleteMultipleOperationLot(),
					serialInformation.getCompleteMultipleOperationSerial(),
					NO,
					YES,
					orderIdPassed,
					NO, // FND-16843 Passed Current Order ID
					null,
					orderInformation.getGroupJobNo(), // FND-24315
					null,
					null);  //FND-28737
		}

		updateReadyToCollectFlagAndDoAutoNexting(operInformation.getStepKey(),
				orderIdPassed,
				operKeyPassed,
				progressCounter,
				ctx,
				orderAndSerialLotList);

		insertIntoPWBuyoffHistTable(operationKey, 
				stepKey, 
				orderNumberList,  //defect 1946
				serialNumberList,
				buyoffId, 
				status, 
				buyoffType,
				buyoffCertification, 
				buyoffComment, 
				ucfSerialOperationBuyoffVch1, 
				ucfSerialOperationBuyoffVch40001,
				percentComplete, 
				oldOrderId, 
				orderIdPassed,
				userName);
	}



	protected void setUnitListInSerialInformation(SerialInformation serialInformation,String orderControl){

		if (orderControl.startsWith(SERIAL))
		{
			serialInformation.setUnitList(serialInformation.getSerialNumberList());
			serialInformation.setList(serialInformation.getSerialArray());
		}
		else if (orderControl.startsWith(LOT))
		{
			serialInformation.setUnitList(serialInformation.getLotNumberList());
			serialInformation.setList(serialInformation.getLotArray());
		}
		else
		{
			serialInformation.setUnitList(serialInformation.getOrderNumberList());
			serialInformation.setList(serialInformation.getOrderNumberArray());// FND-22732 Since lot no will always be 'N/A', distinguish list as order numbers.
		}
	}

	protected void checkUserCollectedReverseBuyoffPrior(OrderInformation orderInformation,String calledFrom){

		BuyoffInformation buyoffInformation = orderInformation.getBuyoffInformation();
		SerialInformation serialInformation = orderInformation.getSerialInfo();
		OperInformation operInformation = orderInformation.getOperInformation();

		// if this is not a reopening of units

		String buyoffStatus = buyoffInformation.getBuyoffStatus();
		if (!stringEquals(buyoffStatus, REOPEN)
				&& !stringEquals(calledFrom, upperCase(ACCEPT_ALL)))
		{
			// 03/22/2012 Suresh FND-19872                                         
			// When called From is ACCEPT ALL then skip checking as it already done
			// in it's script UDV.
			// remove from the unit list any mfg collected buyoff units for this
			// user
			String serialNumberList = serialInformation.getSerialNumberList();
			String remainingUnits = operation.removeMfgCollectedBuyoffUnits(operInformation.getOrderId(),
					operInformation.getOperationKey(),
					operInformation.getStepKey(),
					buyoffInformation.getBuyoffId(),
					serialNumberList, 
					buyoffStatus);

			// if any were removed, then this is in error
			if (!stringEquals(remainingUnits, serialNumberList))
			{
				String buyoffType = buyoffInformation.getBuyoffType();
				String reverseBuyoffType = null;

				if (stringEquals(buyoffType, MFG2_TYPE))
				{
					reverseBuyoffType = MFG_TYPE;
				}
				else if (stringEquals(buyoffType, upperCase(TECH2_TYPE)))
				{
					reverseBuyoffType = upperCase(TECH_TYPE);
				}

				message.raiseError("MFI_54371", reverseBuyoffType, buyoffType);
			}
		}
	}

	protected void checkBuyoffCertificateIsExpiredOrNot(String orderId, String buyoffCertification,String userName, boolean isStatusSkip,boolean isStatusInAcceptRejectSkip){

		//FND-22746 Checked Buyoff certificate is expired or not.
		// If status is either 'ACCEPT' or 'REJECT' and Buyoff certification is
		// not empty
		if (isStatusInAcceptRejectSkip && !isStatusSkip
				&& isNotEmpty(buyoffCertification))
		{
			// Gets the program in order to check the certification.
			Map programMap = orderDao.selectOrderDetails(orderId);
			String program = (String) programMap.get("PROGRAM");

			// System.out.println("I am in the checkBuyoffCertificateIsExpiredOrNot code");
			// Checks the certification.
			certification.hasCertification(userName,
					buyoffCertification,
					program,
					null);
			logger.trace("3 > " + program);
		}
	}

	protected void validateAndSetQuantitiesForPartialBuyoffCompletion(OrderInformation orderInformation, boolean isStatusInAcceptRejectSkip){

		OperInformation operInformation = orderInformation.getOperInformation();
		// PIND-36
		if(!orderInformation.getOrderControl().startsWith(SERIAL))
		{
			Map operationMap = operationDao.selectOperationDescriptionInformation( operInformation.getOrderId(), 
					operInformation.getOperationKey(), 
					INTEGER_MINUS_ONE );

			String operationOverlapFlag = (String) operationMap.get("OPERATION_OVERLAP_FLAG");
			operInformation.setOperAvailableQty((Number) operationMap.get("OPER_AVAILABLE_QTY"));
			orderInformation.setOrderQuantity((Number) operationMap.get("ORDER_QTY"));
			operInformation.setOperationPartOfContinuousFlow(stringEquals( operationOverlapFlag, YES ));

			validateQuantityForOperationPartOfContinuousFlow(operInformation.getQtyComplete(),
					isStatusInAcceptRejectSkip,
					operInformation.isOperationPartOfContinuousFlow(),
					operInformation.getOperAvailableQty());

		}
	}

	protected void setOperAvailableQtyNullIfOperationIsNotPartOfContinuousFlow(OperInformation operInformation)	{

		if(!operInformation.isOperationPartOfContinuousFlow())
		{
			operInformation.setOperAvailableQty(null);
		}
	}

	protected void setAcknowledgementRelatedParameters(String oldUserId,
			OrderInformation orderInformation,
			String readAknowledgeEnable,
			String userName){


		OperInformation operInformation = orderInformation.getOperInformation();
		String orderId = operInformation.getOrderId();
		Number operationKey = operInformation.getOperationKey();
		Number stepKey = operInformation.getStepKey();

		boolean isInstructionReadAcknowledgementRequired = checkReadAcknowledgementRequired(orderId,
				operationKey,
				stepKey,
				oldUserId,
				readAknowledgeEnable);
		operInformation.setInstructionReadAcknowledgementRequired(isInstructionReadAcknowledgementRequired);

		boolean isDeviationReadAcknowledgementRequired = checkDeviationReadAckRequired(orderId, operationKey, stepKey,
				userName, operInformation.isInstructionReadAcknowledgementRequired(), readAknowledgeEnable);
		operInformation.setDeviationReadAcknowledgementRequired(isDeviationReadAcknowledgementRequired);

		//GE-496
		boolean isReadAckRequiredForGroupJob = isRequiredAckForGroupJob(orderInformation.getGroupJobNo(),readAknowledgeEnable);
		operInformation.setReadAckRequiredForGroupJob(isReadAckRequiredForGroupJob);
	}

	protected List<Map<String, Object>> getOrderAndSerialLotList(String orderId,
			Number operationKey,
			Number stepKey,
			String buyoffId,
			String status,
			String calledFrom,
			String groupJobNo,
			String[] serialArray, 
			String[] lotArray,
			String[] orderNumberArray,
			String orderControl,
			String[] list,
			boolean isOperationPartOfContinuousFlow){


		List<Map<String,Object>> orderAndSerialLotList;
		// When Called From is equal to 'ACCEPT_ALL'.
		if (stringEquals(calledFrom,
				upperCase(ACCEPT_ALL)))
		{
			// Gets the Order and Serial Lot Information.
			orderAndSerialLotList = buyoffDao.selectOrderAndSerialLot(orderNumberArray,
					serialArray,
					lotArray,
					operationKey, // FND-19872 Fetched serial oper buyoff info.
					stepKey,
					buyoffId, 
					groupJobNo); // FND-24257
		}
		else
		{
			// Gets the Order and Serial Lot Information.
			// FND-19872 Fetched serial oper buyoff info also.
			orderAndSerialLotList = buyoffDao.selectOrderAndSerialLot(orderId,
					operationKey,
					stepKey,
					buyoffId,
					orderControl,
					list,
					status, 
					NO,
					isOperationPartOfContinuousFlow,
					groupJobNo);  //FND-24257
		}
		return orderAndSerialLotList;
	}

	protected Number executeSerialWiseBuyoff(String calledFrom, String oldUserId, String secondLoginUser,
			String serialInfo, OrderInformation orderInformation, String userName, String oldOrderId, boolean first,
			Number partialCompleteValue, String orderIdPassed, boolean isStatusAccept, boolean isStatusSkip,
			boolean isStatusInAcceptRejectSkip, ListIterator<Map<String, Object>> orderAndSerialLotListIterator) {


		BuyoffInformation buyoffInformation = orderInformation.getBuyoffInformation();
		SerialInformation serialInformation = orderInformation.getSerialInfo();
		OperInformation operInformation = orderInformation.getOperInformation();

		Map<String, Object> orderAndSerialLotMap = orderAndSerialLotListIterator.next();

		serialInformation.setSerialNumber((String) orderAndSerialLotMap.get(SERIAL_NO_COLUMN));
		serialInformation.setLotNumber((String) orderAndSerialLotMap.get(LOT_NO));
		operInformation.setOrderId((String) orderAndSerialLotMap.get(ORDER_ID));
		orderInformation.setOrderNumber((String) orderAndSerialLotMap.get(ORDER_NO));
		serialInformation.setSerialId((String) orderAndSerialLotMap.get(SERIAL_ID));
		serialInformation.setLotId((String) orderAndSerialLotMap.get(LOT_ID));
		orderInformation.setOrderQuantity((Number) orderAndSerialLotMap.get(ORDER_QTY));
		String orderSerialFlag = (String) orderAndSerialLotMap.get(SERIAL_FLAG);

		orderInformation.setOrderScrapQuantity((Number) orderAndSerialLotMap.get(ORDER_SCRAP_QTY));

		String oldBuyoffStatus = (String) orderAndSerialLotMap.get(BUYOFF_STATUS);
		Number oldCompleteQuantity = (Number) orderAndSerialLotMap.get(COMPLETE_QTY);
		String userId = (String) orderAndSerialLotMap.get(UPDT_USERID);

		if (isNotEmpty(orderInformation.getGroupJobNo())) {
			operInformation.setOperationKey((Number) orderAndSerialLotMap.get(OPER_KEY));
			orderInformation.setOrderControl(orderDetails.orderControl(operInformation.getOrderId()));

			setUnitListInSerialInformation(serialInformation, orderInformation.getOrderControl());

			validateAndSetQuantitiesForPartialBuyoffCompletion(orderInformation, isStatusInAcceptRejectSkip);
		} else
			checkAnyUnitIncludedInGroupJob(orderInformation);

		orderInformation.setOrderScrapQuantity((orderInformation.getOrderScrapQuantity() == null) ? (double) ZERO
				: orderInformation.getOrderScrapQuantity());
		oldCompleteQuantity = (oldCompleteQuantity == null) ? INTEGER_ZERO : oldCompleteQuantity; // FND-22141

		if (!first && isStatusAccept)

			buyoffInformation.setBuyoffStatus(ACCEPT);

		updateSerialDescriptionAndBlockParamsForNewOrder(orderInformation, userName, oldOrderId);
		reopenOperationWhileReopenBuyoffIfOperationIsCloseIfOrderIsMRO(orderInformation);

		// Defect 1760
		String priorDataCollectionsOk = buyoffDao.selectPriorDataCollectionsOkForBuyoff(orderInformation.getOrderId(),
				operInformation.getOperationKey(),
				operInformation.getStepKey(),
				serialInformation.getSerialId(),
				serialInformation.getLotId(),
				buyoffInformation.getBuyoffId(),
				orderInformation.getReferenceId(),
				NO);	

		privilegeCheckAndValidateBuyoffExecution(operInformation.getOrderId(), operInformation.getOperationKey(),
				operInformation.getStepKey(), serialInformation.getLotId(), serialInformation.getSerialId(),
				buyoffInformation.getBuyoffId(), buyoffInformation.getBuyoffStatus(),
				buyoffInformation.getBuyoffType(), buyoffInformation.getBuyoffCertification(), calledFrom, oldUserId,
				secondLoginUser, orderInformation.getGroupJobNo(), userName, orderInformation.getControlSequence(),
				orderInformation.getReferenceId(), oldOrderId, oldBuyoffStatus, userId, ACCEPT.equals(oldBuyoffStatus),
				REOPEN.equals(buyoffInformation.getBuyoffStatus()));

		// Defect 1760
		String  priorDataCollectionsOk2 = buyoffDao.selectPriorDataCollectionsOkForBuyoff(orderInformation.getOrderId(),
				operInformation.getOperationKey(),
				operInformation.getStepKey(),
				serialInformation.getSerialId(),
				serialInformation.getLotId(),
				buyoffInformation.getBuyoffId(),
				orderInformation.getReferenceId(),
				NO);	

		String ucfSerialOperationBuyoffVch1 = buyoffInformation.getSerialOperBuyoffUcfs().getUcfSerialOperationBuyoffVch1();

		if(!("N/A".equals(ucfSerialOperationBuyoffVch1)) && !("N/D".equals(ucfSerialOperationBuyoffVch1)) )
		{
			boolean isStatusAcceptPW = equalsIgnoreCase(buyoffInformation.getBuyoffStatus(), ACCEPT);
			boolean isStatusSkipPW = equalsIgnoreCase(buyoffInformation.getBuyoffStatus(), SKIP);

			if (contains(priorDataCollectionsOk,
					upperCase(IMPLICIT_SKIPPED))
					&& (isStatusAcceptPW || isStatusSkipPW))
			{
				message.showMessage("MFI_14AB23A67B7E6369E0440003BA560E35");
			}
		}
		// End Defect 1760

		setBuyoffStatusPartialCompleteAndQuantities(orderInformation, isStatusAccept, isStatusSkip);

		partialCompleteValue = updateSerialOperBuyoffAndGetPartialCompleteValue(orderInformation, userName, oldOrderId,
				first, partialCompleteValue, orderIdPassed, orderSerialFlag, orderInformation.getOrderScrapQuantity());

		cdcExecution.recalculateCDC(operInformation.getOrderId(), operInformation.getOperationKey(),
				operInformation.getStepKey(),
				BUYOFF + AT_SIGN + orderInformation.getBlockId() + AT_SIGN + orderInformation.getTextType() +AT_SIGN
				+ orderInformation.getControlSequence(), NO, serialInformation.getSerialNumber());

		rollUpOrRejectBuyoff(orderInformation, oldBuyoffStatus);
		updateTextTypeAndDiscrepancyItemStatusToImplemented(orderInformation, oldOrderId);

		String dataCollectionStatus = orderExecValidator.checkAllDataCollectionOk(operInformation.getOrderId(),
				operInformation.getOperationKey(), INTEGER_MINUS_ONE, serialInformation.getSerialId(),
				serialInformation.getLotId(),userName);

		raiseErrorIfDataCollectionIsOutSideLimit(dataCollectionStatus);

		// PBL-1485
		if( !(isNotEmpty(orderInformation.getGroupJobNo()) && Y.equals(secondLoginUser) ) )
		{
			completeMultipleOperation(orderInformation, orderIdPassed, dataCollectionStatus);
		}

		completeOperationPartially(orderInformation, partialCompleteValue, oldCompleteQuantity);

		if (!operInformation.isOperationComplete() && !REOPEN.equals(buyoffInformation.getBuyoffStatus())
				&& YES.equals(secondLoginUser))
			serialOperSignOnOff.signOnOrderUnitAutomatically(operInformation.getOrderId(), operInformation.getOperationKey(),
					serialInformation.getSerialId(), serialInformation.getLotId(), ContextUtil.getUsername(), OFF, UDV, null);

		if (!orderAndSerialLotListIterator.hasNext())
			createDiscrepancyForRejectBuyoff(orderInformation, serialInfo, userId);

		return partialCompleteValue;
	}

	protected void validateSecondaryUserAndSignOffPrimaryUser(String orderId,
			Number operationKey, 
			String status, 
			String secondLoginUser,
			String userName, 
			String completeMultipleOperationSerial,
			boolean isOperationPartOfContinuousFlow)
	{


		String operationStatus;
		if(stringEquals(secondLoginUser, YES)
				//GE-5855
				//&& StringUtils.isEmpty(completeMultipleOperationSerial)
				&& !stringEquals(status, REOPEN))
		{
			// FND-26807
			// If order is not serial controlled, and if order's operation overlap flag is 'Y'
			// Check whether operation is CLOSED or not. If CLOSED, do not call sign off, as operation was signed off during operation CLOSE.
			boolean operationClosed = false;
			if(isOperationPartOfContinuousFlow)
			{
				operationStatus = operationDao.selectOperationStatus( orderId, operationKey, INTEGER_MINUS_ONE );
				if(stringEquals( operationStatus, CLOSE ))
				{
					operationClosed = true;
				}
			}

			if(!operationClosed)
			{
				userActivity.updateSignOff(WORK_ORDER,
						orderId,
						operationKey,
						null,
						null,
						null,
						userName,
						NO,
						null);
			}
		}
	}

	protected void showAcceptedSerialUnitsForBuyoff(OrderInformation orderInformation,String calledFrom){


		SerialInformation serialInformation = orderInformation.getSerialInfo() ;
		BuyoffInformation buyoffInformation = orderInformation.getBuyoffInformation();

		String groupJobNo = orderInformation.getGroupJobNo();

		if (isEmpty(groupJobNo)
				&& (stringEquals(calledFrom, upperCase(ACCEPT_ALL)) 
						|| stringEquals(calledFrom,"NEXT_TASK")))
		{
			// Show Message, for which Serials Buyoff has been accepted

			// only display the message if there are more than one units in
			// this order to differentiate between and for SERIAL
			// Controlled Work Orders only.
			Number orderQuantity = orderInformation.getOrderQuantity();
			String orderControl = orderInformation.getOrderControl();

			if (orderControl.startsWith(SERIAL)
					&& (orderQuantity != null && orderQuantity.doubleValue() > 1))
			{
				String buyoffType = buyoffInformation.getBuyoffType();
				String buyoffStatus = buyoffInformation.getBuyoffStatus();
				String unitList = serialInformation.getUnitList();

				String statusMessage = isNotEmpty(buyoffStatus) ? (buyoffStatus + "ed").toLowerCase(): EMPTY;

				if (isNotEmpty(unitList)
						&& lastIndexOf(unitList, ";") == unitList.length() - 1)
				{
					unitList = substring(unitList, 0, unitList.length() - 1);
					serialInformation.setUnitList(unitList);
				}

				message.showMessage("MFI_7DFA782DEDD522F6E04400144FA7B7D2", buyoffType, statusMessage, unitList);
			}
		}
	}

	protected void updateReadyToCollectFlagAndDoAutoNexting(Number stepKey,
			String orderIdPassed,
			Number operKeyPassed, 
			int progressCounter,
			final UserContext ctx,
			List orderAndSerialLotList)
	{


		String orderId;
		Number operationKey;
		//FND-24507
		List orderList = new ArrayList();        
		ListIterator it1 = orderAndSerialLotList.listIterator();

		while (it1.hasNext())
		{
			Map orderAndSerialLotMap = (Map) it1.next();
			orderId = (String) orderAndSerialLotMap.get("ORDER_ID");

			if(orderList.isEmpty() || !orderList.contains(orderId))
			{	
				// Gets the Map values in local variables.	            
				operationKey = (Number) orderAndSerialLotMap.get("OPER_KEY");  //FND-24257
				buyoffExecution.updateReadyToCollectFlag(orderId, operationKey, null);
				orderList.add(orderId);
			}
		}

		ctx.setProgress(progressCounter);

		// FND-24723 Auto Nexting
		String lowTouch = (String) ContextUtil.getUser().getContext().get(LowTouch);
		if(isEmpty(lowTouch))
		{
			UnitInformation tempOrderCtrlNextInfo = (UnitInformation) ContextUtil.getUser().getContext().get(TempOrderCtrlNextInfo);
			if(tempOrderCtrlNextInfo != null
					&& stringEquals(orderIdPassed, tempOrderCtrlNextInfo.getOrderId())
					&& operKeyPassed.longValue() == tempOrderCtrlNextInfo.getOperKey().longValue())
			{
				String stepNumber = operationDao.selectStepNumber(orderIdPassed, operKeyPassed, stepKey);
				operation.autoNextTaskPre(orderIdPassed, 
						operKeyPassed, 
						stepKey, 
						OPER_UNDERSCORE_NO,
						stepNumber,
						null, 
						null);
			}
		}
	}

	protected void validateQuantityForOperationPartOfContinuousFlow(Number qtyComplete,
			boolean isStatusInAcceptRejectSkip,
			boolean isOperationPartOfContinuousFlow,
			Number operAvailableQty){

		raiseErrorIfOperationPartOfContinuousFlowAndMoreThanTotalAvailableQuantity(qtyComplete,
				isOperationPartOfContinuousFlow,
				operAvailableQty);

		raiseErrorIfOperationPartOfContinuousFlowAndNoActiveUnitsAvailable(isStatusInAcceptRejectSkip,
				isOperationPartOfContinuousFlow,
				operAvailableQty);
	}

	protected boolean isRequiredAckForGroupJob(String groupJobNo,
			String readAknowledgeEnable){


		boolean isReadAckRequiredForGroupJob = false;
		if(isNotEmpty(groupJobNo))
		{
			// GE-4267
			if(equalsIgnoreCase(readAknowledgeEnable, Y))
			{
				isReadAckRequiredForGroupJob = operationDao.selectIsRequiredAckForGroupJob(groupJobNo);
			}
		}
		return isReadAckRequiredForGroupJob;
	}

	protected boolean checkReadAcknowledgementRequired(String orderId,
			Number operationKey, 
			Number stepKey,
			String oldUserId,
			String readAknowledgeEnable){


		// FND-24640
		boolean isInstructionReadAcknowledgementRequired = false;
		Number operationUpdateNumber = null;
		String planId = EMPTY;
		try
		{
			operationUpdateNumber = operationDao.selectOperationUpdtNo(orderId, 
					operationKey, 
					stepKey);
		}
		catch(IncorrectResultSizeDataAccessException e)
		{

		}

		if(isEmpty(planId))
		{	
			Map orderDescriptionMap = orderDao.selectOrderDetails(orderId);
			planId = (String) orderDescriptionMap.get("PLAN_ID");
		}

		if(isNotEmpty(planId)
				&& operationUpdateNumber != null)
		{
			String orderMigratedToGEFlag = assembleDao.selectMigratedOrderFlag(orderId);	//GE-1200
			if(!stringEquals(orderMigratedToGEFlag, "5000"))
			{	
				// GE-4267
				if(equalsIgnoreCase(readAknowledgeEnable, Y))
				{
					isInstructionReadAcknowledgementRequired =assembleDao.isOpenInstructionReadAcknowledgementExists(defaultIfEmpty(oldUserId, ContextUtil.getUsername()),
							planId,
							operationKey,
							operationUpdateNumber);
				}
			}
		}
		return isInstructionReadAcknowledgementRequired;
	}

	protected boolean checkDeviationReadAckRequired(String orderId, 
			Number operationKey, 
			Number stepKey,
			String userName, 
			boolean isInstructionReadAcknowledgementRequired, 
			String readAknowledgeEnableFlag) {

		// FND-25011
		if (equalsIgnoreCase(readAknowledgeEnableFlag, N))
			return false;

		if (isInstructionReadAcknowledgementRequired)
			return true;

		boolean isDeviationReakAckRequired = false;
		Map orderMap = buyoffDao.selectPlanIdandAltInfoFromOrder(orderId);
		Number altCount = (Number) orderMap.get(ALT_COUNT);
		if (orderMap.get(PLAN_ID) == null || altCount.intValue() != 0) {
			String altId = (String) orderMap.get(ALT_ID);

			// GE-13391
			boolean deviationReadAckExist = assembleDao.selectDeviationReadAckExist(userName, orderId, operationKey, altId);
			if(!deviationReadAckExist){
				Number alterationRev = operationDao.selectAlterationRev(altId); 
				assembleDao.insertDeviationAck(userName, orderId, operationKey, altId, alterationRev, EMPTY, N, Y, userName);
			}

			isDeviationReakAckRequired = buyoffDao.isAcknowledgeRequiredForUser(orderId, operationKey, userName, altId);
		}
		return isDeviationReakAckRequired;
	}

	protected void checkAnyUnitIncludedInGroupJob(OrderInformation orderInformation){


		OperInformation operInformation = orderInformation.getOperInformation();
		SerialInformation serialInformation = orderInformation.getSerialInfo() ;

		// GE - 14457
		boolean unitIncludedInGroup = groupJob.isUnitIncludedInGroupJob(operInformation.getOrderId(),
				operInformation.getOperationKey(),
				serialInformation.getSerialId(),
				serialInformation.getLotId());

		if(unitIncludedInGroup)
		{
			String tempUnit = serialInformation.getSerialNumber();
			if(stringEquals(serialInformation.getSerialNumber(), NOT_APPLICABLE))
			{
				if(stringEquals(serialInformation.getLotNumber(), NOT_APPLICABLE))
				{
					tempUnit = orderInformation.getOrderNumber();
				}
				else
				{
					tempUnit = serialInformation.getLotNumber();
				}
			}
			message.raiseError("MFI_08FD7C10546F49FD9FF4EF9F138CC4D8",tempUnit);
		}
	}

	protected void updateSerialDescriptionAndBlockParamsForNewOrder(OrderInformation orderInformation,
			String userName,
			String oldOrderId){


		BuyoffInformation buyoffInformation = orderInformation.getBuyoffInformation();
		OperInformation operInformation = orderInformation.getOperInformation(); 

		// Re-Initialize variables order wise.
		if (!stringEquals(operInformation.getOrderId(), oldOrderId))
		{
			updateMroFlagAndSerialDescriptionIfOrderIsMro(orderInformation, userName, operInformation);
			setBlockLevelParameters(orderInformation, buyoffInformation, operInformation);
		}
	}

	protected void reopenOperationWhileReopenBuyoffIfOperationIsCloseIfOrderIsMRO(OrderInformation orderInformation)
	{


		OperInformation operInformation = orderInformation.getOperInformation();
		BuyoffInformation buyoffInformation = orderInformation.getBuyoffInformation();
		SerialInformation serialInformation = orderInformation.getSerialInfo();
		if (orderInformation.isMroFlagExists())
		{
			// When Status is 'REOPEN' and Operation Status is 'CLOSE'.
			if (stringEquals(buyoffInformation.getBuyoffStatus(), REOPEN)
					&& stringEquals(operInformation.getOperationStatus(), CLOSE))
			{
				// When operation is closed reopen the operation when
				// reopening the Buyoff.
				serialOperation.reopenOperation(operInformation.getOrderId(), 
						operInformation.getOperationKey(),
						serialInformation.getSerialNumber());
			}
		}

	}

	protected void privilegeCheckAndValidateBuyoffExecution(String orderId,
			Number operationKey,
			Number stepKey,
			String lotId,
			String serialId,
			String buyoffId,
			String status,
			String buyoffType,
			String buyoffCertification,
			String calledFrom,
			String oldUserId,
			String secondLoginUser,
			String groupJobNo,
			String userName,
			Number controlSequence,
			String referenceId, 
			String oldOrderId,
			String oldBuyoffStatus, 
			String userId,
			boolean isOldBuyoffStatusAccept,
			boolean isStatusReopen){

		// Checks for Privileges.
		// FND-19872 Moved the checking of privilege from method 
		// com.ibaset.solumina.sfwid.application.impl.CircularDependencyLeafImpl1.checkBuyoffExecution()
		// If Status is 'REOPEN' and Buyoff Status is 'ACCEPT' and User Id is
		// not
		// of currently logged in user
		if (isStatusReopen && isOldBuyoffStatusAccept
				&& !stringEquals(userId, userName))
		{
			// Override privilege is required, user must have privilege to
			// reopen Buyoff previously accepted by another user.

			// Checks the Buyoff Privilege.
			String privilege = serialOperationOfd.hasBuyoffPrivilege(buyoffType, status);
			// Raises error when user doesn't have Buyoff Privilege.
			if (!stringEquals(privilege, YES))
			{
				message.raiseError("MFI_55970", status);
			}
		}
		else
		{
			if (!stringEquals(orderId, oldOrderId))
			{
				// Checks the user has privilege or not.
				//validator.checkUserPrivilege("BUYOFF_" + buyoffType);  // Defect 778, 1424
				logger.trace("4 > " + buyoffType);
			}
		} // -- FND-19872

		if (equalsIgnoreCase(status,
				REOPEN))
		{
			boolean contrlHasFinalStatus = checkAnyControlHasFinalStatus(orderId,
					operationKey,
					stepKey,
					lotId,
					serialId,
					referenceId,
					controlSequence,
					BUYOFF);
			if (contrlHasFinalStatus)
			{
				// GE-5512 : Cannot reopen buyoff because work has been started in next section/block
				message.raiseError("MFI_B1D999DE1C054DB1B6BE0C4CB066087D");
			}
			else
			{

				Number displayLineNo = buyoffDao.selectBuyoffDisplayLineNo(orderId, operationKey, stepKey, referenceId, buyoffId);

				Number buyoffWithHigherExeOrdCollected = buyoffDao.selectBuyoffWithhigherExeOrdCollected(orderId,
						operationKey,
						stepKey,
						referenceId,
						lotId,
						serialId,
						displayLineNo);

				if (buyoffWithHigherExeOrdCollected.intValue() > 0)
				{
					// GE-5511
					// Cannot Reopen Buyoff because a buyoff / data collection has been completed after this buyoff
					message.raiseError("MFI_6A65E673E51918B1E0440003BA041A64");
				}
			}
		}
		else
		{
			//GE-4101
			/* if(StringUtils.equals(secondLoginUser, YES))
		{
		// Unit level sign on.
		sfwidUndo.signOnOrderUnitAutomatically(orderId,
		           operationKey,
		           serialId,
		           lotId,
		           oldUserId,
		           ON,
		           null);
		}
			 */
			// Checks Buyoff Execution.
			serialOperationOfd.checkBuyoffExecution(orderId,
					operationKey,
					stepKey,
					lotId,
					serialId,
					buyoffId,
					status,
					buyoffType,
					buyoffCertification,
					null,
					secondLoginUser,
					oldBuyoffStatus, // FND-19872
					userId,
					calledFrom, 
					groupJobNo);
		}
	}

	protected void setBuyoffStatusPartialCompleteAndQuantities(OrderInformation orderInformation,
			boolean isStatusAccept,
			boolean isStatusSkip){


		BuyoffInformation buyoffInformation = orderInformation.getBuyoffInformation();
		SerialInformation serialInformation = orderInformation.getSerialInfo();
		OperInformation operInformation = orderInformation.getOperInformation();
		// PIND-36
		if(operInformation.isOperationPartOfContinuousFlow() && (isStatusAccept || 
				stringEquals(buyoffInformation.getBuyoffStatus(), upperCase(PARTIAL))))
		{
			buyoffInformation.setBuyoffStatus(upperCase(PARTIAL));
			buyoffInformation.setPartialComplete(QTY);

			// qtyComplete is null, it means accept all or accept.
			// PIND-37
			if(operInformation.getQtyComplete() == null) // FND-22732
			{
				Number minimumControlAvailableQty = null;
				try
				{
					Map controlQuantity = operationDao.selectCompleteControlQuantity( operInformation.getOrderId(), 
							operInformation.getOperationKey(), 
							operInformation.getStepKey(), 
							buyoffInformation.getBuyoffId(), 
							null, 
							serialInformation.getSerialId(), 
							serialInformation.getLotId(), 
							BUYOFF.toUpperCase(), 
							MIN );

					minimumControlAvailableQty = (Number) controlQuantity.get("QTY_COMPLETE_MINMAX");
					operInformation.setOperAvailableQty(minimumControlAvailableQty);
				}
				catch(EmptyResultDataAccessException e)
				{
					// Do nothing
				}
			}

			// FND-22732
			operInformation.setQtyComplete((operInformation.getQtyComplete()==null)?operInformation.getOperAvailableQty():operInformation.getQtyComplete());

			if(operInformation.getOperAvailableQty() == null)
			{
				operInformation.setOperAvailableQty((orderInformation.getOrderQuantity().floatValue() - orderInformation.getOrderScrapQuantity().floatValue()));
			}

			if(operInformation.getQtyComplete().floatValue() >  operInformation.getOperAvailableQty().floatValue())
			{
				// GE-5512
				// Cannot Accept Buyoff for more than total available quantity %V1
				message.raiseError("MFI_66EC842F997C4326BBB988F18F9A12F2", operInformation.getOperAvailableQty().toString());
			}

			if(operInformation.getQtyComplete().doubleValue() >= (orderInformation.getOrderQuantity().doubleValue() - orderInformation.getOrderScrapQuantity().doubleValue()))
			{
				buyoffInformation.setBuyoffStatus(ACCEPT);
				buyoffInformation.setPartialComplete(null);
			}

			if(isStatusSkip)
			{
				buyoffInformation.setBuyoffStatus(SKIP);
				buyoffInformation.setPartialComplete(null);
			}
		}
	}

	protected Number updateSerialOperBuyoffAndGetPartialCompleteValue(OrderInformation orderInformation,
			String userName, String oldOrderId, boolean first, Number partialCompleteValue, String orderIdPassed,
			String orderSerialFlag, Number orderScrapQuantity) {



		BuyoffInformation buyoffInformation = orderInformation.getBuyoffInformation();
		SerialOperationBuyoffUcfObjects serialOperBuyoffUcfs = buyoffInformation.getSerialOperBuyoffUcfs();
		SerialInformation serialInformation = orderInformation.getSerialInfo();
		OperInformation operInformation = orderInformation.getOperInformation();

		String action = getAssignedAction(buyoffInformation.getBuyoffStatus());

		Number[] operationExecutionCount = new Integer[1];
		Number operationIteration = 1;

		if (equalsIgnoreCase(orderSerialFlag, YES))
			operationIteration = buyoffDao.getOperationIteration(operInformation.getOrderId(),
					operInformation.getOperationKey(), operInformation.getStepKey(), serialInformation.getLotId(),
					serialInformation.getSerialId(), null, operationExecutionCount, new Integer[1], NO);

		Map partialCompleteValueMap = getPartialCompleteValue(orderIdPassed, oldOrderId,
				buyoffInformation.getPartialComplete(), first, buyoffInformation.getBuyoffStatus(),
				buyoffInformation.getPercentComplete(), orderInformation.getOrderQuantity(),
				operInformation.getQtyComplete(), orderScrapQuantity, orderInformation.getOrderControl(),
				operInformation.isOperationPartOfContinuousFlow(), partialCompleteValue);

		partialCompleteValue = (Number) partialCompleteValueMap.get(PARTIAL_COMPLETE_VALUE);

		buyoffDao.updateSerialOperationBuyoff(
				buyoffInformation.getBuyoffStatus(),
				defaultIfEmpty(buyoffInformation.getBuyoffComment(), null),
				userName,
				action,
				operationIteration,
				operationExecutionCount[0],
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffVch1(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffVch2(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffVch3(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffVch4(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffVch5(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffVch6(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffVch7(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffVch8(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffVch9(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffVch10(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffVch11(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffVch12(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffVch13(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffVch14(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffVch15(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffNum1(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffNum2(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffNum3(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffNum4(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffNum5(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffFlag1(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffFlag2(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffFlag3(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffFlag4(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffFlag5(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffDate1(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffDate2(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffDate3(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffDate4(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffDate5(),
				partialCompleteValue,
				defaultIfEmpty(buyoffInformation.getPartialComplete(),
						(String) partialCompleteValueMap.get(TEMP_PARTIAL_COMPLETE)), operInformation.getOrderId(),
				operInformation.getOperationKey(), operInformation.getStepKey(), serialInformation.getLotId(),
				serialInformation.getSerialId(), buyoffInformation.getBuyoffId(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffVch2551(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffVch2552(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffVch2553(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffVch40001(),
				serialOperBuyoffUcfs.getUcfSerialOperationBuyoffVch40002());

		return partialCompleteValue;
	}

	protected void rollUpOrRejectBuyoff(OrderInformation orderInformation,String oldBuyoffStatus){


		OperInformation operInformation = orderInformation.getOperInformation();
		SerialInformation serialInformation = orderInformation.getSerialInfo();
		BuyoffInformation buyoffInformation = orderInformation.getBuyoffInformation();

		String orderId= operInformation.getOrderId() ; 
		Number operationKey= operInformation.getOperationKey() ; 
		Number stepKey= operInformation.getStepKey() ;
		String lotId= serialInformation.getLotId() ; 
		String serialId= serialInformation.getSerialId() ; 
		String buyoffId= buyoffInformation.getBuyoffId() ;
		String status= buyoffInformation.getBuyoffStatus() ; 
		String buyoffType= buyoffInformation.getBuyoffType() ;
		String buyoffCertification= buyoffInformation.getBuyoffCertification() ; 
		String rejectType= buyoffInformation.getRejectType() ;
		String groupJobNo= orderInformation.getGroupJobNo() ; 
		boolean isOperationPartOfContinuousFlow= operInformation.isOperationPartOfContinuousFlow() ;

		// Makes sure that we do the roll up after all ACCEPTs, add a
		// refresh.
		// When Status is 'ACCEPT' or 'SKIP' or 'REOPEN' and Old Buyoff
		// Status is not
		// 'REJECT'.
		if (stringEquals(status, ACCEPT)
				|| stringEquals(status, SKIP)
				|| (stringEquals(status, REOPEN) && !stringEquals(oldBuyoffStatus, REJECT))
				|| (stringEquals(status, upperCase(PARTIAL)) && isOperationPartOfContinuousFlow)) // PIND-36
		{
			buyoffRollup.rollUpBuyoff(orderId, operationKey, lotId, serialId, status);
		}
		else if (stringEquals(status, REJECT)
				&& stringEquals(rejectType, REOPEN_PRIOR_BUYOFF))
		{
			serial.rejectBuyoff(orderId, operationKey, stepKey, lotId, serialId,
					buyoffCertification, buyoffType, buyoffId, groupJobNo);

			//GE-5509 : Clear UnitInformation object to stop auto-nexting after reject buyoff.
			ContextUtil.getUser().getContext().set(TempOrderCtrlNextInfo, null);
			logger.trace("6 > status : " + status);
		}
	}


	protected void updateTextTypeAndDiscrepancyItemStatusToImplemented(OrderInformation orderInformation,String oldOrderId)
	{



		OperInformation operInformation = orderInformation.getOperInformation();
		String status = orderInformation.getBuyoffInformation().getBuyoffStatus();
		String orderId = operInformation.getOrderId();
		Number operationKey = operInformation.getOperationKey();
		Number stepKey = operInformation.getStepKey();
		String referenceId = orderInformation.getReferenceId();

		// When Status is 'ACCEPT' and Object In Block exists.
		if ((stringEquals(status, ACCEPT) 
				|| stringEquals(status, SKIP)
				|| stringEquals(status, REOPEN))) // FND-21542
		{
			if(stringEquals(status, REOPEN)) // PBL-2662
			{
				List<Map<String, Object>> dispOrderDI = discrepancyDao.selectDIForDispOrderWhichHaveVerifyReworkHold(orderId);
				// Defect 1208 - DXC R2SP4 Upgrade
				/*			 dispOrderDI.stream().forEach(dispOrderDIMap -> {
							workOrderStatus.updateDiscrepancyItemStatusToImplemented((String) dispOrderDIMap.get(DISC_ID),
									(Number) dispOrderDIMap.get(DISC_LINE_NO),
									BUYOFF,
									null);
						});*/
				for (int i=0; i<dispOrderDI.size(); i++) {
					Map<String, Object> dispOrderDIMap = dispOrderDI.get(i);
					workOrderStatus.updateDiscrepancyItemStatusToImplemented((String) dispOrderDIMap.get(DISC_ID),
							(Number) dispOrderDIMap.get(DISC_LINE_NO),
							BUYOFF,
							null);
				};
				// Defect 1208 - End DXC R2SP4 Upgrade
			}

			if (!stringEquals(operInformation.getOrderId(), oldOrderId))
			{
				// Gets the Text Type.
				String textType = orderHelper.getTextType(orderId,
						operationKey,
						stepKey,
						referenceId);
				orderInformation.setTextType(textType);
			}

			// If it is Disposition block
			// FND-20847
			// Moved the implementation of code outside the above if block.
			if (contains(orderInformation.getTextType(), DISP))
			{
				// Gets the Discrepancy Item information.
				Map discrepancyItemMap = discrepancyDao.selectDiscrepancyItem(orderId,
						operationKey,
						stepKey,
						orderInformation.getTextType());

				// Gets the Discrepancy Item information in local
				// variable.
				String discrepancyId = (String) discrepancyItemMap.get("DISC_ID");
				Number discrepancyLineNumber = (Number) discrepancyItemMap.get("DISC_LINE_NO");

				if (isNotEmpty(discrepancyId))
				{
					// Updates the Discrepancy Item Status to
					// IMPLEMENTED
					workOrderStatus.updateDiscrepancyItemStatusToImplemented(discrepancyId,
							discrepancyLineNumber,
							BUYOFF);
				}
			}
		}
	}

	protected void raiseErrorIfDataCollectionIsOutSideLimit(String dataCollectionStatus)
	{


		// FND-27466
		if (stringEquals(dataCollectionStatus, DAT_COL_OUTSIDE))
		{
			// GE-5511
			// Cannot execute Buyoff because data collections are outside limits
			message.raiseError("MFI_5974EF65C0654892A91EDE28DB084CD8");
		}
	}

	protected void completeMultipleOperation(OrderInformation orderInformation,
			String orderIdPassed,
			String dataCollectionStatus)
	{



		OperInformation operInformation = orderInformation.getOperInformation();
		BuyoffInformation buyoffInformation =  orderInformation.getBuyoffInformation();
		SerialInformation serialInformation = orderInformation.getSerialInfo();

		String status = buyoffInformation.getBuyoffStatus() ;
		boolean isOperationPartOfContinuousFlow = operInformation.isOperationPartOfContinuousFlow();
		String textType = orderInformation.getTextType();
		String orderId= operInformation.getOrderId();
		Number operationKey = operInformation.getOperationKey();
		String completeMultipleOperationSerial= serialInformation.getCompleteMultipleOperationSerial() ;
		String completeMultipleOperationLot= serialInformation.getCompleteMultipleOperationLot() ;
		String serialId = serialInformation.getSerialId();
		String lotId = serialInformation.getLotId();
		String serialNumber= serialInformation.getSerialNumber() ;
		String lotNumber= serialInformation.getLotNumber() ;
		String groupJobNo= orderInformation.getGroupJobNo() ;
		boolean isInstructionReadAcknowledgementRequired= operInformation.isInstructionReadAcknowledgementRequired() ;
		boolean isDeviationReadAcknowledgementRequired= operInformation.isDeviationReadAcknowledgementRequired() ;
		boolean isReadAckRequiredForGroupJob = operInformation.isReadAckRequiredForGroupJob() ;


		// When buyoff is not from disposition block
		if ((((stringEquals(status, ACCEPT) || stringEquals(status, SKIP)) && !isOperationPartOfContinuousFlow)) // PIND-6
				&& !contains(textType, DISP))
		{
			// Checks whether all buyoff for the unit are completed or not.
			String serialBuyoffOKExists = orderExecValidator.checkSerialBuyoffOk(orderId,
					operationKey,
					INTEGER_MINUS_ONE,
					serialId,
					lotId);

			// When all buyoffs are completed for the unit
			if (stringEquals(serialBuyoffOKExists, YES))
			{
				// If any Part/Tool/Parametric DC are not remaining
				if (stringEquals(dataCollectionStatus, YES))
				{
					// Replaced method call instead of UDV:MFI_1010241
					if (stringEquals(orderId, orderIdPassed))
					{
						// Complete the operation for passed order at
						// the end of the loop.
						if (isEmpty(completeMultipleOperationSerial))
						{
							serialInformation.setCompleteMultipleOperationSerial(serialNumber);
						}
						else
						{
							serialInformation.setCompleteMultipleOperationSerial(completeMultipleOperationSerial
									+ SEMI_COLON
									+ serialNumber);
						}

						if (isEmpty(completeMultipleOperationLot))
						{
							serialInformation.setCompleteMultipleOperationLot(lotNumber);
						}
					}
					else
					{
						//FND-24640
						if(!isInstructionReadAcknowledgementRequired 
								&& !isDeviationReadAcknowledgementRequired //FND-25011
								&& !isReadAckRequiredForGroupJob) //GE-496
						{   
							// Complete the operation for Cross Order.
							// Applied the changes for FND-11452 also.
							serial.completeMultipleOperation(orderId,
									operationKey,
									lotNumber,
									serialNumber,
									NO,
									YES,
									orderIdPassed,
									YES,// FND-16843 Passed Current Order ID
									null,
									groupJobNo, // FND-24315
									null,  //FND-28737
									null);
						}    
					}
					operInformation.setOperationComplete(true);



				}
			}
		}
	}

	protected void completeOperationPartially(OrderInformation orderInformation,
			Number partialCompleteValue,
			Number oldCompleteQuantity) 
	{



		OperInformation operInformation = orderInformation.getOperInformation();
		BuyoffInformation buyoffInformation =  orderInformation.getBuyoffInformation();
		SerialInformation serialInformation = orderInformation.getSerialInfo();

		String orderId= operInformation.getOrderId();
		Number operationKey = operInformation.getOperationKey();
		Number stepKey = operInformation.getStepKey();
		String serialId = serialInformation.getSerialId();
		String lotId = serialInformation.getLotId();
		String serialNumber= serialInformation.getSerialNumber() ;
		String lotNumber= serialInformation.getLotNumber() ;
		String buyoffId = buyoffInformation.getBuyoffId() ;
		String status = buyoffInformation.getBuyoffStatus() ;
		boolean isOperationPartOfContinuousFlow = operInformation.isOperationPartOfContinuousFlow();
		boolean isOperationComplete = operInformation.isOperationComplete();



		if (isOperationPartOfContinuousFlow && !isOperationComplete
				&& (stringEquals(status, ACCEPT)
						|| stringEquals(status, upperCase(PARTIAL)) 
						|| stringEquals(status,REOPEN)))
		{
			// PIND-37
			if(oldCompleteQuantity != null && partialCompleteValue != null)
			{
				// if old complete quanity is less than new complete quantity
				if(oldCompleteQuantity.doubleValue() < partialCompleteValue.doubleValue())
					//FND-22141 - done correction in condition,so gives error when partialCompleteValue < operAvailableQty and partialCompleteValue > oldCompleteQuantity
					//&& partialCompleteValue.doubleValue() != operAvailableQty.doubleValue())   
				{
					// Check Minimum DC Complete Qty for control.
					Number minimumControlAvailableQty = null;
					try
					{
						Map controlQuantity = operationDao.selectCompleteControlQuantity( orderId, 
								operationKey, 
								stepKey, 
								buyoffId, 
								null, 
								serialId, 
								lotId, 
								BUYOFF.toUpperCase(), 
								MIN );

						minimumControlAvailableQty = (Number) controlQuantity.get("QTY_COMPLETE_MINMAX");
					}
					catch(EmptyResultDataAccessException e)
					{
						// Do nothing
					}

					if(minimumControlAvailableQty != null && partialCompleteValue.doubleValue() > minimumControlAvailableQty.doubleValue())
					{
						// GE-5512 : Total Complete qty cannot be greater than available qty %V1
						message.raiseError("MFI_59B4FE130011499CB12366E911DA72BD", minimumControlAvailableQty.toString());
					}

				}
				// // if old complete quanity is greater than new complete quantity
				else if(oldCompleteQuantity.doubleValue() > partialCompleteValue.doubleValue())
				{
					// Check Maximum DC Complete Qty for control.
					Number maximumControlAvailableQty = null;
					try
					{
						Map controlQuantity = operationDao.selectCompleteControlQuantity( orderId, 
								operationKey, 
								stepKey, 
								buyoffId, 
								null, 
								serialId, 
								lotId, 
								BUYOFF.toUpperCase(), 
								MAX );

						maximumControlAvailableQty = (Number) controlQuantity.get("QTY_COMPLETE_MINMAX");
					}
					catch(EmptyResultDataAccessException e)
					{
						// Do nothing
					}

					if(maximumControlAvailableQty != null && partialCompleteValue.doubleValue() < maximumControlAvailableQty.doubleValue())
					{
						if(partialCompleteValue.doubleValue() == 0)
						{
							// GE-5512 : Cannot reopen buyoff because work has been started in next section/block
							message.raiseError("MFI_B1D999DE1C054DB1B6BE0C4CB066087D");
						}

						// GE-5512 : Cannot decrease complete quantity because work has been started in next section/block
						message.raiseError("MFI_163E1D860D9D439CAF8815F58F528CB0");
					}
				}
			}

			operationComplete.completeOperationPartial( orderId, 
					operationKey, 
					serialId, 
					lotId, 
					serialNumber, 
					lotNumber, 
					partialCompleteValue,
					(stringEquals(status, REOPEN))?DELETE:
						BUYOFF);
		}
	}

	protected void createDiscrepancyForRejectBuyoff(OrderInformation orderInformation,
			String serialInfo,
			String userId) 
	{



		OperInformation operInformation = orderInformation.getOperInformation();
		BuyoffInformation buyoffInformation =  orderInformation.getBuyoffInformation();
		SerialInformation serialInformation = orderInformation.getSerialInfo();

		String orderId = operInformation.getOrderId();
		Number operationKey = operInformation.getOperationKey();
		String serialNumberList = serialInformation.getSerialNumberList();
		String status = buyoffInformation.getBuyoffStatus();
		String rejectType = buyoffInformation.getRejectType();
		String groupJobNo = orderInformation.getGroupJobNo(); 
		String[] serialArray = serialInformation.getSerialArray();
		String orderControl = orderInformation.getOrderControl();
		Number orderQuantity = orderInformation.getOrderQuantity();
		String lotNumber = serialInformation.getLotNumber();

		String orderNumber;

		// When Status is 'REJECT' and Reject
		// Type is 'Initiate discrepancy'.
		if (stringEquals(status, REJECT)
				&& stringEquals(rejectType,
						INITIATE_DISCREPANCY)
				&& isEmpty(groupJobNo))
		{
			// Gets the Order and Operation Description in Map.
			Map orderAndOperationDescription = buyoffDao.selectOrderAndOperationDescription(orderId,
					operationKey,
					INTEGER_MINUS_ONE);

			// Gets the Map values in local variables.
			String partNumber = (String) orderAndOperationDescription.get("PART_NO");
			String partChange = (String) orderAndOperationDescription.get("PART_CHG");
			orderNumber = (String) orderAndOperationDescription.get("ORDER_NO");
			String operatioNumber = (String) orderAndOperationDescription.get("OPER_NO");
			String serialFlag = (String) orderAndOperationDescription.get("SERIAL_FLAG");
			String lotFlag = (String) orderAndOperationDescription.get("LOT_FLAG");
			String program = (String) orderAndOperationDescription.get("PROGRAM");
			String assignedWorkLocation = (String) orderAndOperationDescription.get("PLAN_ASGND_WORK_LOC");
			String itemId = (String) orderAndOperationDescription.get("ITEM_ID");

			// Sets the Proram to null if it is equal to either
			// 'ANY' or 'N/A'.
			if (stringEquals(program, ANY)
					|| stringEquals(program,
							NOT_APPLICABLE))
			{
				program = null;
			}

			// Prepares the Parameters.
			StringBuffer parameters = new StringBuffer();
			parameters.append("PART_NO=")
			.append(wrapUtils.wrapValue(partNumber))
			.append(",PART_CHG=")
			.append(wrapUtils.wrapValue(partChange))
			.append(",ORDER_NO=")
			.append(wrapUtils.wrapValue(orderNumber))
			.append(",ORDER_ID=")
			.append(orderId)
			.append(",OPER_NO=")
			.append(wrapUtils.wrapValue(operatioNumber))
			.append(",OPER_KEY=")
			.append(operationKey)
			.append(",SERIAL_FLAG=")
			.append(defaultIfEmpty(serialFlag,
					EMPTY))
			.append(",LOT_FLAG=")
			.append(defaultIfEmpty(lotFlag,
					EMPTY))
			.append(",PROGRAM=")
			.append(wrapUtils.wrapValue(program))
			.append(",ASGND_WORK_LOC=")
			.append(wrapUtils.wrapValue(assignedWorkLocation))
			.append(",REJECT_WORK_LOC=")
			.append(wrapUtils.wrapValue(assignedWorkLocation))
			.append(",ITEM_ID=")
			.append(defaultIfEmpty(itemId,
					EMPTY))
			.append(",DI_CREATE_FROM=")
			.append(wrapUtils.wrapValue(EMPTY))//FND-26555
			.append(",V_CALLED_FROM="
					+ upperCase(REJECT_BUYOFF));

			// Concatenates the serial/lot number list
			parameters.append(",LOT_NO=")
			.append(wrapUtils.wrapValue(lotNumber))
			.append(",SERIAL_NO_LIST=")
			.append(wrapUtils.wrapValue(serialNumberList));

			// if buyoff is rejected for partial qty and initiated
			// discrepancy then
			// the affected quantity will be the number of serials
			// rejected.
			if (orderQuantity != null
					&& stringEquals(orderControl,
							SERIAL)
					&& orderQuantity.intValue() != serialArray.length)
			{
				parameters.append(",ORDER_QTY=")
				.append(serialArray.length);
			}
			// We need to determine if we create a new disc or
			// launch an existing one.

			// Checks whether User has 'QA_PR' privilege or not.
			boolean hasPrivilege = privilege.hasPrivilege(upperCase(QA_PR));

			// Declares the local variable.
			String hideParameters = "SUBCOMP_DRAWING_NO,CHAR_CODE_REV,CHARACTERISTIC_DESC,ORDER_ID";

			// Sets the Hide Parameter when User does not have
			// 'QA_PR' privilege.
			if (!hasPrivilege)
			{
				hideParameters = "DISPOSITION_TYPE,DISPOSITION_INSTR_TYPE,SUBCOMP_DRAWING_NO,CHAR_CODE_REV,CHARACTERISTIC_DESC,ORDER_ID";
			}

			// Shows the UDV.
			event.showUdv("MFI_3240",
					INSERT,
					LanguageUtils.getMessage("insert.discrepancy.record.and.discrepancy.item"), // GE-7326
					hideParameters,
					parameters.toString());

			ContextUtil.getUser().getContext().set(TempOrderCtrlNextInfo, null); //GE-164
		}
		else if(stringEquals(status, REJECT)
				&& stringEquals(rejectType,
						INITIATE_DISCREPANCY)
				&& isNotEmpty(groupJobNo))
		{
			Map hideColumnsMap = discrepancyDao.selectOrderMfgDIWebParams( userId );
			String hideColumns = null;
			if(hideColumnsMap != null){
				hideColumns = (String) hideColumnsMap.get( "HIDE_COLUMNS" ); 
			}

			StringBuffer parameters = new StringBuffer();
			parameters.append("SERIAL_INFO=" +wrapUtils.wrapValue(serialInfo));

			// Shows the UDV.
			event.showUdv("MFI_E24E6A084A944E94AADEBB5590FD242A",
					INSERT,
					LanguageUtils.getMessage("insert.discrepancy.record.and.discrepancy.item"), // GE-7326
					hideColumns,
					parameters.toString());

			ContextUtil.getUser().getContext().set(TempOrderCtrlNextInfo, null); //GE-164
		}
	}

	protected void raiseErrorIfOperationPartOfContinuousFlowAndMoreThanTotalAvailableQuantity(Number qtyComplete,
			boolean isOperationPartOfContinuousFlow,
			Number operAvailableQty)
	{

		if(isOperationPartOfContinuousFlow && qtyComplete!= null && qtyComplete.doubleValue() > operAvailableQty.doubleValue())
		{
			// GE-5512
			// Cannot Accept Buyoff for more than total available quantity %V1
			message.raiseError("MFI_66EC842F997C4326BBB988F18F9A12F2", operAvailableQty.toString());
		}
	}

	protected void raiseErrorIfOperationPartOfContinuousFlowAndNoActiveUnitsAvailable(boolean isStatusInAcceptRejectSkip,
			boolean isOperationPartOfContinuousFlow,
			Number operAvailableQty)
	{


		if(isStatusInAcceptRejectSkip && isOperationPartOfContinuousFlow && operAvailableQty.doubleValue() <= 0)
		{
			message.raiseError("MFI_50171");
		}
	}

	protected void updateMroFlagAndSerialDescriptionIfOrderIsMro(OrderInformation orderInformation,
			String userName,
			OperInformation operInformation)
	{


		String isMRO = orderDetails.isMRO(operInformation.getOrderId());

		// Sets the MRO flag to true When Order is MRO.
		if (stringEquals(isMRO, YES))
		{
			orderInformation.setMroFlagExists(true);
		}

		if (orderInformation.isMroFlagExists())
		{
			Map operationInformationMap = buyoffDao.selectOperationInformation(operInformation.getOrderId(),
					operInformation.getOperationKey(),
					INTEGER_MINUS_ONE);

			operInformation.setOperationStatus((String)operationInformationMap.get("OPER_STATUS"));

			// Updates User Name for Serial Description.
			buyoffDao.updateSerialDescription(userName, operInformation.getOrderId());
		}
	}

	protected void setBlockLevelParameters(OrderInformation orderInformation,
			BuyoffInformation buyoffInformation,
			OperInformation operInformation)
	{


		Map blockIdMap = buyoffDao.selectBlockIdAndTextType(operInformation.getOrderId(),
				operInformation.getOperationKey(),
				operInformation.getStepKey(),
				buyoffInformation.getBuyoffId());

		orderInformation.setBlockId((String) blockIdMap.get("BLOCK_ID"));
		orderInformation.setTextType((String) blockIdMap.get("TEXT_TYPE"));
		orderInformation.setControlSequence((Number) blockIdMap.get("CONTROL_SEQ_NO"));
		orderInformation.setReferenceId( (String) blockIdMap.get("REF_ID"));
	}

	protected Map<String, Object> getPartialCompleteValue(String orderId,
			String oldOrderId,
			String partialComplete,
			boolean first,
			String status,
			Number percentComplete,
			Number orderQuantity,
			Number qtyComplete,
			Number orderScrapQuantity,
			String orderControl,
			boolean isOperationPartOfContinuousFlow,
			Number partialCompleteValue )
	{


		Map<String,Object> partialCompleteValueMap = new HashMap<String,Object>();

		String tempPartialComplete = null;

		// Set Partial Complete Value according to Percent/Qty Complete
		if (stringEquals(partialComplete, PERCENT))
		{
			if (first)
			{
				if (stringEquals(status, upperCase(PARTIAL))
						|| stringEquals(status, ACCEPT))
				{
					partialCompleteValue = new Integer((int) (percentComplete.doubleValue() * 100));
				}
			}
		}
		else if (stringEquals(partialComplete, QTY))
		{
			if (first)
			{
				if (stringEquals(status, upperCase(PARTIAL))
						|| stringEquals(status, ACCEPT))
				{
					partialCompleteValue = qtyComplete;
				}
			}
		}
		else
		{
			// When Order is Serial controlled or Order quantity is
			// 1.
			if ((isNotEmpty(orderControl) && orderControl.startsWith(SERIAL))
					|| (orderQuantity != null && (orderQuantity.doubleValue()- orderScrapQuantity.doubleValue()) == 1) //FND-20126
					&& !isOperationPartOfContinuousFlow) // PIND-36
			{
				tempPartialComplete = PERCENT;

				// Sets the Percentage Complete value to 100% when
				// buyoff is accepted.
				if (stringEquals(status,
						ACCEPT))
				{
					partialCompleteValue = new Integer(100) * 100;
				}
				// Sets the Percentage Complete value to 0% when
				// buyoff is reopened.
				else if (stringEquals(status,
						REOPEN))
				{
					partialCompleteValue = INTEGER_ZERO;
				}
			}
			// When Order is Non-Serial controlled.
			else
			{
				tempPartialComplete = QTY;

				// Sets the Quantity Complete value to Order
				// quantity when buyoff is accepted.
				if (stringEquals(status,
						ACCEPT))
				{
					// FND-22732
					if(isOperationPartOfContinuousFlow)
					{
						partialCompleteValue = qtyComplete;
					}
					else if (orderQuantity != null)
					{
						partialCompleteValue = (orderQuantity.intValue() - orderScrapQuantity.intValue());//FND-19919
					}
				}
				// Sets the Percentage Complete value to 0 when
				// buyoff is reopened.
				else if (stringEquals(status,
						REOPEN))
				{
					partialCompleteValue = INTEGER_ZERO;
				}
			}
		}

		partialCompleteValueMap.put(PARTIAL_COMPLETE_VALUE, partialCompleteValue);
		partialCompleteValueMap.put(TEMP_PARTIAL_COMPLETE, tempPartialComplete);

		return partialCompleteValueMap ; 
	}

	protected String getAssignedAction(String status)
	{

		String action;
		// When Status is 'ACCEPT' or 'REJECT' or 'SKIP'.
		if (stringEquals(status, ACCEPT)
				|| stringEquals(status, REJECT)
				|| stringEquals(status, SKIP)
				|| stringEquals(status, upperCase(PARTIAL))) // PIND-36
		{
			// Assigns action as 'UPDATED'.
			action = UPDATED;

			logger.trace("3 > action : " + action);
		}
		// When Status is not in 'ACCEPT' and 'REJECT'.
		else
		{
			// Assigns action as 'INSERTED'.
			action = INSERTED;

			logger.trace("4 > action : " + action);
		}
		return action;
	}

	public String checkPWRules(String orderId, Number operationKey, String action,String serialFlag){

		Map orderMap = orderDaoImpl.selectOrderDetails(orderId);
		String orderType = (String) orderMap.get("ORDER_TYPE"); // Defect 347
		String altId = (String) orderMap.get("ALT_ID"); // Defect 727
		String parentOrderId = (String) orderMap.get("PARENT_ORDER_ID"); // Defect 727
		String readAcknowledgeEnabled = commonDaoPW.getGlobalConfigValue("READ_ACKNOWLEDGE_ENABLE");

		//Begin Defect 727
		if (StringUtils.equals(action, "ACCEPT") && "Y".equals(readAcknowledgeEnabled)){  // Defect 792

			boolean lastBuyoffBlock = buyoffDaoPW.isFinalBuyoffBlockInOper(orderId, operationKey,serialFlag);  

			if (lastBuyoffBlock){

				if (StringUtils.equals(orderType, "OVERHAUL") && 
						StringUtils.equals(parentOrderId, null) || 
						StringUtils.equals(orderType, "REPAIR")){

					Map planInfoForOrderMap = buyoffDaoPW.selectPlanInfoFromOrder(orderId, operationKey);   
					String planId = (String) planInfoForOrderMap.get("PLAN_ID");
					Number operUpdtNo = (Number) planInfoForOrderMap.get("OPER_UPDT_NO");  //Defect 727

					boolean workInstructionsRead = buyoffDaoPW.isworkInstructionsRead(planId, operationKey, operUpdtNo);  //Defect 727
					boolean alterationWorkInstructionsRead = buyoffDaoPW.isSupplementalWorkInstructionsRead(orderId, operationKey, altId);  //Defect 727/949

					if (!workInstructionsRead && !alterationWorkInstructionsRead){
						message.raiseError("MFI_20", "You must acknowledge that you have read the work instructions before the Final buyoff for this operation");
					}
				}
				else{
					boolean workInstructionsRead = buyoffDaoPW.isSupplementalWorkInstructionsRead(orderId, operationKey, altId);  //Defect 727/949
					if (!workInstructionsRead){
						message.raiseError("MFI_20", "You must acknowledge that you have read the work instructions before the Final buyoff for this operation");
					}
				}
			}
		}
		// End Defect 727
		return orderType;
	}	

	public void insertIntoPWBuyoffHistTable(Number operationKey, 
			Number stepKey, 
			String orderNumberList, // defect 1946
			String serialNumberList,
			String buyoffId, 
			String status, 
			String buyoffType, 
			String buyoffCertification, 
			String buyoffComment,
			String ucfSerialOperationBuyoffVch1, 
			String ucfSerialOperationBuyoffVch40001, 
			Number percentComplete,
			String oldOrderId, 
			String orderIdPassed,
			String userName) {


		//defect 1772
		if (StringUtils.isNotBlank(serialNumberList)){

			if (StringUtils.equals("ACCEPT", status)) {
				percentComplete = 100;
			} else if (StringUtils.equals("REOPEN", status)) {
				percentComplete = 0;
			}

			if (StringUtils.equals(ucfSerialOperationBuyoffVch1, "N/A")) {
				status  = "Closed - N/A";
			}else if (StringUtils.equals(ucfSerialOperationBuyoffVch1, "N/D")) {
				status = "Closed - N/D ";
			}

			String userId = ContextUtil.getUser().getUsername();

			Map sfwidOperDescMap = commonDaoPW.getSfwidOperDescMap(orderIdPassed, operationKey, stepKey);
			String operNo = (String)sfwidOperDescMap.get("OPER_NO");
			String stepNo = (String) sfwidOperDescMap.get("STEP_NO");
			String operOptFlag =(String) sfwidOperDescMap.get("OPER_OPT_FLAG");

			Map sfwidOperBuyoffMap = commonDaoPW.getSfwidOperBuyoffMap(orderIdPassed, operationKey, stepKey, buyoffId);
			String buyoffTitle =  (String) sfwidOperBuyoffMap.get("BUYOFF_TITLE");
			String userFullName = null;
			if (StringUtils.isNotBlank(userName)){
				userFullName = commonDaoPW.getUserFullName(userName);
			}

			List orderNoList = new ArrayList(); //defect 1946

			StringTokenizer orderNoTokens; //defect 1946
			String orderNo = ""; //defect 1946
			orderNoTokens = new StringTokenizer(orderNumberList, ";");  //defect 1946
			while (orderNoTokens.hasMoreElements()){  //defect 1946
				orderNo = orderNoTokens.nextElement().toString(); //defect 1946
				//check for duplicate order nos
				if (!orderNoList.contains(orderNo)) { //defect 1946
					orderNoList.add(orderNo);
					oldOrderId = commonDaoPW.getOrderIdOrderNo(orderNo); //defect 1946	

					StringTokenizer serialNoTokens;
					String serialNo = "";
					serialNoTokens = new StringTokenizer(serialNumberList, ";");
					while (serialNoTokens.hasMoreElements()){


						serialNo = serialNoTokens.nextElement().toString();
						
						//only serial numbers that go with the order
						if (buyoffDaoPW.SerialNumberOrderComboExists(oldOrderId,serialNo)) { //defect 1946

						buyoffDaoPW.insertBuyoffHistory(oldOrderId,
								operationKey, 
								stepKey, 
								buyoffId,
								status,
								String.valueOf(percentComplete), 
								buyoffComment, 
								serialNo, 
								operNo,
								stepNo, 
								buyoffTitle, 
								buyoffType, 
								buyoffCertification,
								operOptFlag,
								userName,
								userFullName, // full Name
								ucfSerialOperationBuyoffVch40001  //signoffs
								);
						}
					}
				}
			}
		}
	}


}