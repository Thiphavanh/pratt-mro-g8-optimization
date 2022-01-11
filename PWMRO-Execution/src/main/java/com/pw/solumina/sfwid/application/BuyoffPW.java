/*
 *  This unpublished work, first created in 2018 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2018, 2019,2021.  All rights reserved.  Information contained 
 *  herein is proprietary and confidential and improper use or disclosure 
 *  may result in civil and penal sanctions.
 *
 *  File:    BuyoffPW.java
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
 *  2018-03-28      D. Miron        SMRO_WOE_202 Additional updates - Methods added/changed: checkUsersWorkCenterCerts, 
 *                                  getAddtionalSignoffs
 *  2018-05-14      D. Miron        SMRO_WOE_202 Defect 694 - Added methods getSerialBuyoffList, getSerialBuyoffListHistTab
 *  2018-06-06      R. Thorpe       SMRO_WOE_202 Defect 712/815 - Changes for 'P'ARTIAL buyoff    
 *  2018-06-22      D. Miron        SMRO_WOE_202 Defect 694 - Changes made to remove duplicate records from buyoff side window. 
 *  2018-07-20      R. Thorpe       SMRO_RPT_201 Defect 849 - Added method getAddtionalSignoffs4DPReport
 *  2018-07-24		R. Cannon		SMRO_RPT_201 Defect 849 - Added iterator to get all "additional buyoffs" on the oper within
 *  								getAddtionalSignoffs4DPReport
 *  2018-08-01		R. Cannon		SMRO_RPT_201 Defect 908 - Added method getSerialBuyoffListHist4DPReport;
 *  								Additional changes to getSerialBuyoffListHistTab
 *  2018-08-05		R. Cannon		SMRO_RPT_201 Defect 908 - Moved and renamed getSerialBuyoffListHist4DPReport to
 *  								com.pw.solumina.solumina.sfrpt.impl.ReportImplPW.selectReportSerialBuyoffHist();
 *  								Moved getAddtionalSignoffs4DPReport() to aforementioned path.
 *  2018-08-07		R. Cannon		SMRO_RPT_201 Defect 930 - Changes to buyoff history additional signoff list insertion
 *  2018-08-06      R. Thorpe       SMRO_WOE_204 Defect 879 - Added method getSerialBuyoffTabList
 *  2018-08-10      R. Thorpe       SMRO_WOE_202 Defect 938 - Create Order Notes for N/A and N/D buyoffs
 *  2018-08-21		R. Cannon		SMRO_RPT_201 Defect 929 - Fix buyoff timestamp formatting
 *  2018-08-29      R. Thorpe       SMRO_WOE_204 Defect 879 - Fix method getSerialBuyoffTabList to return BUYOFF_ID and USER_NAME 
 *  2018-09-10      D. Miron        SMRO_WOE_202 Defect 963 - Changed getSerialBuyoffLIst to return list with null map values
 *                                                            operation contains no buyoff history. 
 *  2018-10-17      D. Miron        SMRO_WOE_202 Defect 860 - Added call to rollUpBuyoff for N/A and N/D.
 *  2018-10-17      D. Miron        SMRO_WOE_202 Defect 973 - Added step key to order notes insert
 *  2018-11-07      D. Miron        SMRO_WOE_202 Defect 860 - Revised selectNANDBuyoffStatus to return last buyoff staturs not pending or reopen.
 *  2018-11-13      D. Miron        SMRO_WOE_203 Defect 860 - Fixed issue with Add Signature button not enabled due to BUYOFF_STATUS = DISABLED.
 *  2018-12-12      B. Preston      Defect 1024 - Changed signoffDate to timestamp.  
 *  2018-12-12      D. Miron        SMRO_WOE_203 Defect 860 - Added call to selectBuyoffStatus to return actual buyoff status used by Add Signature button
 *  2018-12-12      D. Miron        SMRO_WOE_203 Defect 1066 - Correct time stamp on Buyoff History Tab for additional signatures.
 *  2019-05-16      R. Thorpe	    SMRO_WOE_302 (1258) - Modified methods selectOrderOperationBuyoffInfo, insertBuyoffAcceptForTrainee, getAddtionalSignoffs, 
 *                                                        signOffBuyoffForNaNdOperations, addSignOff
 *  2019-06-07      R. Thorpe       SMRO_WOE_302 Defect 1033 - modified insertBuyoffAcceptForTrainee
 *  2020-02-04      B. Corson       Defect 1388 - Fix problem of Buyoff Status not working for WO created in Solumina.  
 *  2020-05-01	    J DeNinno       Defect 1534 - Operation not changing to Active for Partial Buyoff and Serial number not changing to IN PROCESS  
 *  2020-06-06		S. Edgerly		Defect 1675 - Error creating Order Notes for N/D buyoff types when comments are NULL.  
 *  2020-06-166     D. Miron        Defects 1692, 1693: Uncommented code commented by Defect 1534.  Causef greyed out Additional Signature button and 
 *                                  missing user name in buyoff block.  Issues with Repair buyoff to be resolved.  
 *  2020-06-19      Fred Ettefagh   Defect 1683 - changed method  signOffBuyoffForNaNdOperations to fix repair and overhaul bugs    
 *   2020-06-24      Fred Ettefagh   Defect 1534 - changed method  processPartialBuyoffPW to call oob sign in method  and call oob buyfoff rollup   
 *   2020-06-26      Fred Ettefagh   Defect 1714 - Changed methods signOffBuyoffForNaNdOperations and processPartialBuyoffPW to skip changing buyoff status when oper status is not Active or in queue
 *   2020-07-07      Fred Ettefagh   Defect 1722 - changed method finalBuyoffChecKtoCloseOper. Fixed buyoff NA/ND bug to selected correct serial no lists to pass to oob method to close operations
 *   2020-07-07      Fred Ettefagh   Defect 1711 - Added new method  getOrderCurrentUnitsPW to call oob  IOperation.getCurrentUnits then filter out buyoff serial data that have been accepted
 *   2020-07-08      Fred Ettefagh   Defect 1711 - Added new method  selectAvailableCollectionUnitsPW to call oob  oobIOperation.selectAvailableCollectionUnits then filter out buyoff serial data that have been accepted
 *   2020-07-09      Fred Ettefagh   Defect 1722 - fixed bug when selecting serial_no from sfwid_serial_desc    
 *   2020-07-08      Fred Ettefagh   Defect 1711 - after closing oper, event.refreshTool() was not working in method finalBuyoffChecKtoCloseOper.  Had to move event.refreshTool() to calling methods.
 *   2020-08-05      Fred Ettefagh   Defect 1734 - Changed buyoff NA/ND code to call oob method 
 *   2020-08-10      Fred Ettefagh   Defect 1734 - Changed code to add order notes when order get closed.  added new update Dao call to update SFWID_SERIAL_OPER_BUYOFF.UCF_SRLOPBUYOFF_VCH1
 *   2020-08-11      Fred Ettefagh   Defect 1753 - changed method  processPartialBuyoffPW so that oob method is not called 
 *   2020-08-14      Fred Ettefagh   Defect 1748 - Changed code to show correct buyoff userId and timestamp when accepting a buyoff, or NA/ND or partial buyoff 
 *   2020_08-18      Fred Ettefagh   Defect 1734 and 1748 - changed code to get save/get buyoff timestamp and userId from sfwid_oper_buyoff ucf columns. Also change code to set Data col to Optional  prior to NA/ND
 *   2020-08-23      Fred Ettefagh   Defect 1753 - Added code to implement PW rules on accepting buyoffs (N/A, N/D, Partial and Accept)    
 *   2020-08-24      Fred Ettefagh   Defect 1755 - Changed code pass tagType to oob method as ucfSerialOperationBuyoffVch1
 *   2020-10-02      John DeNinno,Fred    Defect 1772 - added code to insert into new custom PW buyoff hist table
 *   2020-10-09      Fred Ettefagh   Defect 1792, changed code to get full user name by using secondLoginUser  
 *   2020-10-12      Fred Ettefagh   Defect 1791  Changed to to use userName to insert into insertBuyoffHistory table
 *   2020-10-23      Fred Ettefagh   Defect 1796  Changed methods processPartialBuyoffPW, getSerialBuyoffTabList
 *   2020-11-4       John DeNinno    Defect 1775 added method to check for holds on oper completion
 *   2020-12-11      John DeNinno    defect 1834 removed some system.out.printlns from 1775
 *   2021-8-19       Fred Ettefagh   Defect 1965 Buyoff Performance, added new method checkBuyoffPrivsPw(String buyoffType, String tagType, String clockNoList)
 *   2021-8-27       Fred Ettefagh   Defect 1965 changed method signOffBuyoffForNaNdOperations to refresh order or only buyoff grid based on oper status 
 *   2021-09-30      D.Miron         Defect 1965 Revised IF statement in checkBuyoffPrivsPw to check privs when TAG_TYPE is null.
 *   2021-09-28      John DeNinno    Defect 1994 - Change how display tab on buyoff selects the history
 */
package com.pw.solumina.sfwid.application;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import com.ibaset.solumina.sfwid.application.impl.OperationCompleteImpl;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.solumina.exception.SoluminaException;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.application.IParse;
import com.ibaset.solumina.sffnd.application.IUserActivity;
import com.ibaset.solumina.sfor.application.ISfwidUndo;
import com.ibaset.solumina.sfwid.application.IBuyoff;
import com.ibaset.solumina.sfwid.application.IBuyoffRollup;   // Defect 860
import com.ibaset.solumina.sfwid.application.IOperation;
import com.ibaset.solumina.sfwid.application.IOperationSignOnOff;
import com.ibaset.solumina.sfwid.application.IOrder;
import com.ibaset.solumina.sfwid.application.IOrderDetails;
import com.ibaset.solumina.sfwid.application.IRollUp;
import com.ibaset.solumina.sfwid.application.ISerial;
import com.ibaset.solumina.sfwid.application.IWorkOrderStatus;
import com.ibaset.solumina.sfwid.dao.IOperationDao;
import com.ibaset.solumina.sfwid.dao.IOrderDao;
import com.ibaset.solumina.sfwid.dao.ISerialDao;
import com.ibaset.solumina.sfwid.dao.IStepDatColDao;
import com.ibaset.solumina.sfwid.domain.SerialOperBuyoffId;
import com.ibaset.solumina.sfwid.domain.UnitInformation;
import com.pw.common.dao.CommonDaoPW;
import com.pw.solumina.sfwid.dao.BuyoffDaoPW;
import com.pw.solumina.sfwid.dao.PartDaoPW;

public class BuyoffPW 
{   	
	@Reference private CommonDaoPW commonDaoPW;
	@Reference private BuyoffDaoPW buyoffDaoPW;  
	@Reference private IBuyoff oobBuyoff;
	@Reference private IEvent event;
	@Reference private IOperationDao operationDao = null;
	@Reference private PartDaoPW partDaoPW = null;
	@Reference private ISerial oobSerial = null;
	@Reference private ISfwidUndo undo = null;
	@Reference private ISerialDao serialDao = null;
	@Reference private IMessage message = null;
	@Reference private IOrder order = null;  //712
	@Reference private IBuyoffRollup oobBuyoffRollup = null;  // Defect 860
	@Reference private IParse parse = null;  ///1258
	@Reference private IUserActivity userActivity; //defect 1534
	@Reference private IWorkOrderStatus workOrderStatus; //defect 1534
	@Reference private IOperationSignOnOff operSignOnOff; //defect 1534
	@Reference private IOperation oobIOperation;
	@Reference private IOrderDao orderDao;
	@Reference private IStepDatColDao widStepDatColDao;
	@Reference private IOrderDetails orderDetails;
	@Reference private IRollUp rollUp;

	
	// Defect 1965
	public Map checkBuyoffPrivsPw(String buyoffType, String tagType, String clockNoList){
		
		Map map = new HashMap();
		String buyoffPrivCheck = null;
		String errorMSg = null;
		
		if (StringUtils.isNotBlank(tagType)){

			if (StringUtils.equalsIgnoreCase(tagType, "N/A") || 
					StringUtils.equalsIgnoreCase(tagType, "N/D")){

				buyoffPrivCheck = buyoffDaoPW.getUserPriv(clockNoList, "BUYOFF_NA_ND");
				if (StringUtils.equalsIgnoreCase(buyoffPrivCheck, "N")){
					errorMSg = "User does not have BUYOFF_NA_ND privilege";
					map.put("BUYOFF_PRIV_CHECK", errorMSg);
				}else{
					map.put("BUYOFF_PRIV_CHECK", "Y");
				}
				return map;

			}
		}
		
		if (StringUtils.equalsIgnoreCase(buyoffType, "MFG")){
			buyoffPrivCheck = buyoffDaoPW.getUserPriv(clockNoList, "BUYOFF_MFG");
			if (StringUtils.equalsIgnoreCase(buyoffPrivCheck, "N")){
				errorMSg = "User does not have BUYOFF_MFG privilege";
			}

		}else if (StringUtils.equalsIgnoreCase(buyoffType, "TECH2")){
			buyoffPrivCheck = buyoffDaoPW.getUserPriv(clockNoList, "BUYOFF_TECH2");
			if (StringUtils.equalsIgnoreCase(buyoffPrivCheck, "N")){
				errorMSg = "User does not have BUYOFF_TECH2 privilege";
			}

		}else if (StringUtils.equalsIgnoreCase(buyoffType, "MFG2")){
			buyoffPrivCheck = buyoffDaoPW.getUserPriv(clockNoList, "BUYOFF_MFG2");
			if (StringUtils.equalsIgnoreCase(buyoffPrivCheck, "N")){
				errorMSg = "User does not have BUYOFF_MFG2 privilege";
			}					

		}else if (StringUtils.equalsIgnoreCase(buyoffType, "INSP")){
			buyoffPrivCheck = buyoffDaoPW.getUserPriv(clockNoList, "BUYOFF_INSP");
			if (StringUtils.equalsIgnoreCase(buyoffPrivCheck, "N")){
				errorMSg = "User does not have BUYOFF_INSP privilege";
			}					

		}else if (StringUtils.equalsIgnoreCase(buyoffType, "QA")){
			buyoffPrivCheck = buyoffDaoPW.getUserPriv(clockNoList, "BUYOFF_QA");
			if (StringUtils.equalsIgnoreCase(buyoffPrivCheck, "N")){
				errorMSg =  "User does not have BUYOFF_QA privilege";
			}					

		}else if (StringUtils.equalsIgnoreCase(buyoffType, "TECH")){
			buyoffPrivCheck = buyoffDaoPW.getUserPriv(clockNoList, "BUYOFF_TECH");
			if (StringUtils.equalsIgnoreCase(buyoffPrivCheck, "N")){
				errorMSg =  "User does not have BUYOFF_TECH privilege";
			}	

		}else if (StringUtils.equalsIgnoreCase(buyoffType, "CUST")){
			buyoffPrivCheck = buyoffDaoPW.getUserPriv(clockNoList, "BUYOFF_CUST");
			if (StringUtils.equalsIgnoreCase(buyoffPrivCheck, "N")){
				errorMSg =  "User does not have BUYOFF_CUST privilege";
			}	
		}


		if (StringUtils.isNotBlank(errorMSg)){
			map.put("BUYOFF_PRIV_CHECK", errorMSg);
		}else{
			map.put("BUYOFF_PRIV_CHECK", "Y");
		}
		
		return map;
		
	}
	
	
	// Defect 973
	// Defect 860   ///1258  this needs to change...   3 sn's - 2 NA, one still open.  it is selecting OPEN and will not let me NA the 3rd SN
	public Map selectNANDBuyoffStatus(String orderId, Number operKey, Number stepKey, String buyoffId, String refId){
		Map map = new HashMap();
		//String priorBuyoffStatus = buyoffDaoPW.selectNANDBuyoffStatus(orderId, operKey, stepKey, buyoffId, refId);
		String priorBuyoffStatus = "NONE";
		map.put("PRIOR_BUYOFF_STATUS", priorBuyoffStatus);
		return map;
	}

	@SuppressWarnings("unchecked")
	public List selectOrderOperationBuyoffInfo(String orderId,
			Number operationKey,
			Number stepKey,
			String referenceId,
			String userId,
			String consolidatedList,
			String navigationLevel,
			String groupJobNo){

		// called from solumina client to get all buyfoff for a grid

		List oobList = new ArrayList();
		List resultList = new ArrayList(); 

		String buyoffType = null;
		String buyoffCert = null;
		String buyoffId = null;
		Number operKey = null;
		Number mapStepKey = null;
		String crossOrderFlag = null;
		String rejectInd = null;
		String acceptInd = null;
		String skipInd = null;
		String reopenInd = null;
		String partialInd = null;
		String acceptAllInd = null;
		String percentQtyCompl = null;
		String comments = null;
		String optFlag = null;
		String widOperStatus = null;
		String image = null;
		String slideId = null;
		String slideEmbRefId = null;
		String updtUserId = null;
		Date timeStamp = null;
		String buyoffTitle = null;
		String secGroup = null;
		String userName = null;
		String statusChgReason = null;
		String operStatus = null;
		String buyoffStatus = null;
		Number partialCnt = 0;
		String collected = SoluminaConstants.NO;
		String actualBuyoffStatus = null;

		oobList = oobBuyoff.selectOrderOperationBuyoffInfo(orderId, operationKey, stepKey, referenceId, userId, consolidatedList, navigationLevel, groupJobNo );
		Iterator it = oobList.iterator();

		if (!oobList.isEmpty()){
			while (it.hasNext() ){
				Map resultMap = new ListOrderedMap();
				Map map = (Map) it.next();
				buyoffType = StringUtils.defaultIfEmpty((String) map.get("BUYOFF_TYPE"),StringUtils.EMPTY);
				buyoffCert = StringUtils.defaultIfEmpty((String) map.get("BUYOFF_CERT"),StringUtils.EMPTY);
				buyoffId = StringUtils.defaultIfEmpty((String) map.get("BUYOFF_ID"),StringUtils.EMPTY);
				operKey = (Number) map.get("OPER_KEY");
				mapStepKey = (Number) map.get("STEP_KEY");
				crossOrderFlag = StringUtils.defaultIfEmpty((String) map.get("CROSS_ORDER_FLAG"),StringUtils.EMPTY);
				rejectInd = StringUtils.defaultIfEmpty((String) map.get("REJECT"),StringUtils.EMPTY);
				acceptInd = StringUtils.defaultIfEmpty((String) map.get("ACCEPT"),StringUtils.EMPTY);
				skipInd = StringUtils.defaultIfEmpty((String) map.get("SKIP"),StringUtils.EMPTY);
				reopenInd = StringUtils.defaultIfEmpty((String) map.get("REOPEN"),StringUtils.EMPTY);
				partialInd = StringUtils.defaultIfEmpty((String) map.get("PARTIAL"),StringUtils.EMPTY);
				acceptAllInd = StringUtils.defaultIfEmpty((String) map.get("ACCEPTALL"),StringUtils.EMPTY);
				percentQtyCompl = StringUtils.defaultIfEmpty((String) map.get("PERCENT_QTY_COMPLETE"),StringUtils.EMPTY);
				comments = StringUtils.defaultIfEmpty((String) map.get("COMMENTS"),StringUtils.EMPTY);
				optFlag = StringUtils.defaultIfEmpty((String) map.get("OPTIONAL_FLAG"),StringUtils.EMPTY);
				widOperStatus = StringUtils.defaultIfEmpty((String) map.get("WID_OPER_STATUS"),StringUtils.EMPTY);
				image = StringUtils.defaultIfEmpty((String) map.get("IMAGE"),StringUtils.EMPTY);
				slideId = StringUtils.defaultIfEmpty((String) map.get("SLIDE_ID"),StringUtils.EMPTY);
				slideEmbRefId = StringUtils.defaultIfEmpty((String) map.get("SLIDE_EMBEDDED_REF_ID"),StringUtils.EMPTY);
				updtUserId = StringUtils.defaultIfEmpty((String) map.get("UPDT_USERID"),StringUtils.EMPTY);
				timeStamp = (Date) map.get("TIME_STAMP");
				buyoffTitle = StringUtils.defaultIfEmpty((String) map.get("BUYOFF_TITLE"),StringUtils.EMPTY);
				secGroup = StringUtils.defaultIfEmpty((String) map.get("SECURITY_GROUP"),StringUtils.EMPTY);
				statusChgReason = StringUtils.defaultIfEmpty((String) map.get("STATUS_CHG_REASON"),StringUtils.EMPTY); //defect 1889 - changed to STATUS_CHG_REASON
				operStatus = StringUtils.defaultIfEmpty((String) map.get("OPER_STATUS"),StringUtils.EMPTY);
				buyoffStatus = StringUtils.defaultIfEmpty((String) map.get("BUYOFF_STATUS"),StringUtils.EMPTY);
				partialCnt = (Number) map.get("PARTIAL_COUNT");

				List sfwidOperBuyoffList = buyoffDaoPW.selectBuyoffInfoFromOperBuyoff(orderId, operationKey, mapStepKey, buyoffId);
				if (sfwidOperBuyoffList!=null && !sfwidOperBuyoffList.isEmpty()){
					Map sfwidOperBuyoffMap = (Map) sfwidOperBuyoffList.get(0);

					// SFWID_OPER_BUYOFF.UCF_OPER_BUYOFF_VCH1  user id
					// SFWID_OPER_BUYOFF.UCF_OPER_BUYOFF_VCH3  percent complete
					// SFWID_OPER_BUYOFF.UCF_OPER_BUYOFF_DATE1 date buyoff is accepted 

					if (StringUtils.isNotBlank((String)sfwidOperBuyoffMap.get("UCF_OPER_BUYOFF_VCH1"))){
						updtUserId = (String)sfwidOperBuyoffMap.get("UCF_OPER_BUYOFF_VCH1");
					}
					Date sfwidOperBuyoffUcfOperBuyoffDate1 = (Date)sfwidOperBuyoffMap.get("UCF_OPER_BUYOFF_DATE1");
					if (null != sfwidOperBuyoffUcfOperBuyoffDate1){
						timeStamp = (Date)sfwidOperBuyoffMap.get("UCF_OPER_BUYOFF_DATE1");
					}
					if (StringUtils.isNotBlank((String)sfwidOperBuyoffMap.get("UCF_OPER_BUYOFF_VCH3"))){
						percentQtyCompl = (String)sfwidOperBuyoffMap.get("UCF_OPER_BUYOFF_VCH3");
					}
				}

				// Defect 860 - 20181212
				//actualBuyoffStatus is ONLY used to activate the Additional Signature button
				int count = buyoffDaoPW.selectBuyoffStatus(orderId, operKey, stepKey, buyoffId, referenceId);  //1258
				actualBuyoffStatus = null;
				if (count > 0) {
					actualBuyoffStatus = "ACCEPT";
				} else { 
					actualBuyoffStatus = "PENDING";
				}
				resultMap.put("ACTUAL_BUYOFF_STATUS", actualBuyoffStatus);

				//Defect 712
				resultMap.put("BUYOFF_TYPE", buyoffType); 
				resultMap.put("BUYOFF_CERT", buyoffCert);
				resultMap.put("BUYOFF_ID", buyoffId);
				resultMap.put("OPER_KEY", operKey);
				resultMap.put("STEP_KEY", mapStepKey);
				resultMap.put("REF_ID", referenceId);
				resultMap.put("CROSS_ORDER_FLAG", crossOrderFlag);
				resultMap.put("REJECT", rejectInd);
				resultMap.put("ACCEPT", acceptInd);
				resultMap.put("SKIP", skipInd);
				resultMap.put("REOPEN", reopenInd);
				resultMap.put("PARTIAL", partialInd);
				resultMap.put("ACCEPTALL", acceptAllInd);
				resultMap.put("PERCENT_QTY_COMPLETE", percentQtyCompl);
				resultMap.put("COMMENTS", comments);
				resultMap.put("OPTIONAL_FLAG", optFlag);
				resultMap.put("WID_OPER_STATUS", widOperStatus);
				resultMap.put("IMAGE", image);
				resultMap.put("SLIDE_ID", slideId);
				resultMap.put("SLIDE_EMBEDDED_REF_ID", slideEmbRefId);
				resultMap.put("UPDT_USERID", updtUserId);
				resultMap.put("TIME_STAMP", timeStamp);
				resultMap.put("BUYOFF_TITLE", buyoffTitle);
				resultMap.put("SECURITY_GROUP", secGroup);
				resultMap.put("STATUS_CHANGE_REASON", statusChgReason);
				resultMap.put("OPER_STATUS", operStatus);
				resultMap.put("BUYOFF_STATUS", buyoffStatus);
				resultMap.put("PARTIAL_COUNT", partialCnt);

				UnitInformation unitInformation = (UnitInformation) ContextUtil.getUser().getContext().get(SoluminaConstants.UnitInformation);
				// String collected = SoluminaConstants.NO;
				if (unitInformation != null){
					String serialId = unitInformation.getSerialId();
					String lotId = unitInformation.getLotId();
					String tempOrderId = unitInformation.getOrderId();
					if (StringUtils.equals(tempOrderId, orderId)){
						SerialOperBuyoffId serialOperBuyoffId = new SerialOperBuyoffId(orderId,
								new BigDecimal(operationKey.doubleValue()),
								new BigDecimal(stepKey.doubleValue()),lotId,serialId,buyoffId);

						boolean isSerialOperBuyoffCompleted = operationDao.isSerialOperBuyoffCompleted(serialOperBuyoffId);

						if (isSerialOperBuyoffCompleted){
							collected = SoluminaConstants.YES;
						}
					}
				}

				resultMap.put("COLLECTED", collected);

				if  (updtUserId == null || updtUserId.isEmpty()){
					userName = "";
				}else{	
					userName = commonDaoPW.getUserFullName(updtUserId);
				}
				resultMap.put("USERNAME", userName);
				resultList.add(resultMap);
			}
		}else{
			Map resultMap = new ListOrderedMap();

			resultMap.put("BUYOFF_TYPE", buyoffType);
			resultMap.put("BUYOFF_CERT", buyoffCert);
			resultMap.put("BUYOFF_ID", buyoffId);
			resultMap.put("OPER_KEY", operKey);
			resultMap.put("STEP_KEY", mapStepKey);
			resultMap.put("REF_ID", referenceId);
			resultMap.put("CROSS_ORDER_FLAG", crossOrderFlag);
			resultMap.put("REJECT", rejectInd);
			resultMap.put("ACCEPT", acceptInd);
			resultMap.put("SKIP", skipInd);
			resultMap.put("REOPEN", reopenInd);
			resultMap.put("PARTIAL", partialInd);
			resultMap.put("ACCEPTALL", acceptAllInd);
			resultMap.put("PERCENT_QTY_COMPLETE", percentQtyCompl);
			resultMap.put("COMMENTS", comments);
			resultMap.put("OPTIONAL_FLAG", optFlag);
			resultMap.put("WID_OPER_STATUS", widOperStatus);
			resultMap.put("IMAGE", image);
			resultMap.put("SLIDE_ID", slideId);
			resultMap.put("SLIDE_EMBEDDED_REF_ID", slideEmbRefId);
			resultMap.put("UPDT_USERID", updtUserId);
			resultMap.put("TIME_STAMP", timeStamp);
			resultMap.put("BUYOFF_TITLE", buyoffTitle);
			resultMap.put("SECURITY_GROUP", secGroup);
			resultMap.put("STATUS_CHANGE_REASON", statusChgReason);
			resultMap.put("OPER_STATUS", operStatus);
			resultMap.put("BUYOFF_STATUS", buyoffStatus);
			resultMap.put("PARTIAL_COUNT", partialCnt);
			resultMap.put("COLLECTED", collected);
			resultMap.put("USERNAME", userName);
			resultList.add(resultMap);
		}
		return resultList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void signOffBuyoffForNaNdOperations ( String orderId, 
			Number operKey, 
			Number stepKey,
			String userID, 
			String tagType,
			String comments, 
			String buyoffId,
			String serialNumberList){
		// call from solumina client when end user is setting buyoff to accept via N/A or to N/D

		if(userID == null){
			userID = ContextUtil.getUsername();
		}
		if (StringUtils.isNotBlank(serialNumberList)){

			List sfwidOrderDescList =commonDaoPW.selectSfwidOrderDesc(orderId);
			Map sfwidOrderDescMAp = (Map)sfwidOrderDescList.get(0);
			String orderNo = (String) sfwidOrderDescMAp.get("ORDER_NO");

			Map sfwidOperDescMap = buyoffDaoPW.SelectSfwidOperDesc(orderId, operKey,-1);
			String operStatus = (String) sfwidOperDescMap.get("OPER_STATUS");

			Map lotMap = buyoffDaoPW.selectLotNumberInfo(orderId);
			String lotNo	= (String) lotMap.get("LOT_NO");

			List sfwidOperBuyoffList = buyoffDaoPW.selectBuyoffInfoFromOperBuyoff(orderId, operKey, stepKey, buyoffId);
			Map sfwidOperBuyoffMap = (Map)sfwidOperBuyoffList.get(0);
			String buyoffType = (String) sfwidOperBuyoffMap.get("BUYOFF_TYPE");
			String buyoffCertificate = (String) sfwidOperBuyoffMap.get("BUYOFF_CERT");
			String buyoffRedID = (String) sfwidOperBuyoffMap.get("REF_ID");

			if ((StringUtils.equalsIgnoreCase(operStatus, "IN QUEUE")) ||
					(StringUtils.equalsIgnoreCase(operStatus, "ACTIVE"))){

				List sfwidOperDatColOptionalFlagList = buyoffDaoPW.SelectSfwidOperDatColOptionalFlag(orderId, operKey);
				if (sfwidOperDatColOptionalFlagList!=null && !sfwidOperDatColOptionalFlagList.isEmpty()){
					// set all oper dat col optional values that are N to Y
					// this is so that we can do a accept a buyoff via NA or ND with out collecting data for dat_col 
					setOrderOperDatColOptionalFlag(orderId, sfwidOperDatColOptionalFlagList,"Y");
				}

				oobPreExecutionInsert2(orderId, 
						operKey, 
						stepKey, 
						userID, 
						comments, 
						buyoffId,
						"ACCEPT",
						serialNumberList,
						orderNo, 
						lotNo, 
						buyoffType, 
						buyoffCertificate,
						null,
						tagType);

				if (sfwidOperDatColOptionalFlagList!=null && !sfwidOperDatColOptionalFlagList.isEmpty()){
					setOrderOperDatColOptionalFlag(orderId, sfwidOperDatColOptionalFlagList,"N");
				}

				String noteId = insertOrderNote(orderId,operKey,stepKey,comments,buyoffId,"Oper "+tagType);
				if (StringUtils.isNotBlank(noteId)){
					StringTokenizer serialNoTokens; 
					String delimiter = ";";
					serialNoTokens = new StringTokenizer(serialNumberList, delimiter);
					String serialNo = serialNoTokens.nextElement().toString();
					Map serialMap = buyoffDaoPW.selectSerialNumberInfo(orderId,serialNo);
					String lotId = (String) serialMap.get("LOT_ID");
					String serialId = (String) serialMap.get("SERIAL_ID");
					buyoffDaoPW.updateOrderNotesTimestamp(orderId, operKey, stepKey, noteId, buyoffId, serialId,lotId);
				}

				finalBuyoffChecKtoCloseOper(orderId, operKey, stepKey, tagType);
				
				Map sfwidOperDescMap1 = buyoffDaoPW.SelectSfwidOperDesc(orderId, operKey, stepKey);
				String operStatus1 = (String) sfwidOperDescMap1.get("OPER_STATUS");
				if (StringUtils.equalsIgnoreCase(operStatus1, "CLOSE")){
					event.refreshTool();
				}else{
					event.refresh_alias("BuyoffExe");	
				}
			}
		}
	}

	public void setOrderOperDatColOptionalFlag(String orderId, List sfwidOperDatColOptionalFlagList, String optinalValue) {

		for (int i=0;i<sfwidOperDatColOptionalFlagList.size();i++){

			Map sfwidOperDatColMap = (Map) sfwidOperDatColOptionalFlagList.get(i);
			Number sfwidOperDatColOperKey = (Number)sfwidOperDatColMap.get("OPER_KEY");
			Number sfwidOperDatColStepKey = (Number)sfwidOperDatColMap.get("STEP_KEY");
			String sfwidOperDatColDatColId = (String)sfwidOperDatColMap.get("DAT_COL_ID");

			buyoffDaoPW.updateSfwidOperDatColOptionalFlag(orderId,
					sfwidOperDatColOperKey,
					sfwidOperDatColStepKey,
					sfwidOperDatColDatColId,
					optinalValue);

		}
	}

	@SuppressWarnings("deprecation")
	public void processPartialBuyoffPW( String orderId, 
			Number operKey,
			Number stepKey,
			String buyoffId,
			Number percentComplete,
			String buyoffComment,
			String secondLoginUser,
			String serialNumberList,
			String tagType){

		// call from solumina client when user sets a buyffoff to Partial value

		if (StringUtils.isNotBlank(serialNumberList)){

			List sfwidOrderDescList =commonDaoPW.selectSfwidOrderDesc(orderId);
			Map sfwidOrderDescMAp = (Map)sfwidOrderDescList.get(0);
			String orderNo = (String) sfwidOrderDescMAp.get("ORDER_NO");
			Number orderQty = (Number) sfwidOrderDescMAp.get("ORDER_QTY");


			Map sfwidOperDescMap = buyoffDaoPW.SelectSfwidOperDesc(orderId, operKey,-1);
			String operStatus = (String) sfwidOperDescMap.get("OPER_STATUS");
			String operNo = (String)sfwidOperDescMap.get("OPER_NO");
			String stepNo = (String) sfwidOperDescMap.get("STEP_NO");
			String operOptFlag =(String) sfwidOperDescMap.get("OPER_OPT_FLAG");

			Map lotMap = buyoffDaoPW.selectLotNumberInfo(orderId);
			String lotNo	= (String) lotMap.get("LOT_NO");

			List sfwidOperBuyoffList = buyoffDaoPW.selectBuyoffInfoFromOperBuyoff(orderId, operKey, stepKey, buyoffId);
			Map sfwidOperBuyoffMap = (Map)sfwidOperBuyoffList.get(0);
			String buyoffType = (String) sfwidOperBuyoffMap.get("BUYOFF_TYPE");
			String buyoffCertificate = (String) sfwidOperBuyoffMap.get("BUYOFF_CERT");
			String buyoffRedID = (String) sfwidOperBuyoffMap.get("REF_ID");
			String buyoffTitle =  (String) sfwidOperBuyoffMap.get("BUYOFF_TITLE");

			String userID = ContextUtil.getUsername();

			if ((StringUtils.equalsIgnoreCase(operStatus, "IN QUEUE")) ||
					(StringUtils.equalsIgnoreCase(operStatus, "ACTIVE"))){

				checkForPriorBuyoffNAND(orderId, operKey, stepKey, buyoffId, serialNumberList);

				if (StringUtils.equalsIgnoreCase(operStatus, "IN QUEUE")){
					oobOperationsignOnOff(orderId, operKey, stepKey, userID);
				}

				String str = String.valueOf(percentComplete);
				String str2 = str+"00";
				int percentCompleteInt = Integer.parseInt(str2);
				//String daoSerialNoList = convertSerialNumberList(serialNumberList);
				//updateAllDataColPriorToBuyoff(orderId, operKey, stepKey,buyoffRedID,daoSerialNoList);
				StringTokenizer serialNoTokens; 
				String delimiter = ";";
				serialNoTokens = new StringTokenizer(serialNumberList, delimiter);

				String userFullName = null;
				if (StringUtils.isNotBlank(secondLoginUser)){
					userFullName = commonDaoPW.getUserFullName(secondLoginUser);
				}



				while (serialNoTokens.hasMoreElements()){
					String serialNo = serialNoTokens.nextElement().toString();
					Map serialMap = buyoffDaoPW.selectSerialNumberInfo(orderId,serialNo);
					String lotId = (String) serialMap.get("LOT_ID");
					String serialId = (String) serialMap.get("SERIAL_ID");
					buyoffDaoPW.updatePartialBuyoffPW(orderId, operKey, stepKey, buyoffId, percentCompleteInt, buyoffComment, secondLoginUser, serialId,lotId );
					buyoffDaoPW.updateSfwidSerialOperBuyoffVch5Date1(orderId, operKey, stepKey, buyoffId, serialId, lotId, secondLoginUser);

					// begin defect 1772

					buyoffDaoPW.insertBuyoffHistory(orderId,
							operKey, 
							stepKey, 
							buyoffId, 
							"PARTIAL",
							String.valueOf(percentComplete), 
							buyoffComment, 
							serialNo, 
							operNo, 
							stepNo,  
							buyoffTitle, 
							buyoffType, 
							buyoffCertificate, 
							operOptFlag, 
							secondLoginUser,
							userFullName, 
							"");


					// end defect 1772
				}

				String percentCompleteString = buyoffDaoPW.selectSfwidBuyoffPercnetGet(orderId, operKey, stepKey, buyoffId, orderQty);
				buyoffDaoPW.updateSfwidOperBuyoffUcfOperVch1AndVch2(orderId, operKey, stepKey, buyoffId, secondLoginUser, percentCompleteString);
				operSignOnOff.updateOperationDate(orderId, NumberUtils.INTEGER_MINUS_ONE, SoluminaConstants.START);


			}

			String noteId = insertOrderNote(orderId,operKey,stepKey,buyoffComment,buyoffId,null);//noteTitle, 
			if (StringUtils.isNotBlank(noteId)){
				buyoffDaoPW.updateOrderNotesUserid(orderId, noteId, secondLoginUser);
			}
			//event.refreshTool();
			event.refresh_alias("BuyoffExe");
		}
	}

	public void checkForPriorBuyoffNAND(String orderId, Number operKey, Number stepKey, String buyoffId, String serialNumberList) {


		// pratt bus rule
		// for a serial no, if a buyff is accepted via N/A or N/D, then Partial buyfoff can no be done     
		String buyoffMixMatchCheck = commonDaoPW.getPwustGlobalConfigValue("BUYOFF_MIX_MATCH_CHECK_ENABLED");
		if (StringUtils.isBlank(buyoffMixMatchCheck) || StringUtils.isEmpty(buyoffMixMatchCheck)){
			buyoffMixMatchCheck = "Y";
		}

		if (StringUtils.equalsIgnoreCase(buyoffMixMatchCheck, "Y")){

			List sfwidOperBuyoffList = buyoffDaoPW.selectBuyoffInfoFromOperBuyoff(orderId, operKey, stepKey, buyoffId);
			Map sfwidOperBuyoffMap = (Map) sfwidOperBuyoffList.get(0);
			String refId = (String) sfwidOperBuyoffMap.get("REF_ID");


			StringTokenizer serialNoTokens = new StringTokenizer(serialNumberList, ";");
			while (serialNoTokens.hasMoreElements()){
				String serialNo = serialNoTokens.nextElement().toString();

				{
					List buyoffsAcceptedViaNA = buyoffDaoPW.selectSfwidSerialOperBuyoffAccpetedviaTagType(orderId, operKey, stepKey, refId, serialNo, "N/A");
					if (buyoffsAcceptedViaNA!=null && !buyoffsAcceptedViaNA.isEmpty()){
						if (StringUtils.equalsIgnoreCase(serialNo, "N/A")){
							message.raiseError("MFI_20", "Cannot set status to Partial , prior buyffoffs has been set to  N/A");
						}else{
							message.raiseError("MFI_20", "Cannot set status to Partial , prior buyffoffs has been set to  N/A for serial Number " + serialNo);
						}
					}
				}

				{
					List buyoffsAcceptedViaND = buyoffDaoPW.selectSfwidSerialOperBuyoffAccpetedviaTagType(orderId, operKey, stepKey, refId, serialNo, "N/D");
					if (buyoffsAcceptedViaND!=null && !buyoffsAcceptedViaND.isEmpty()){
						if (StringUtils.equalsIgnoreCase(serialNo, "N/A")){
							message.raiseError("MFI_20", "Cannot set status to Partial , prior buyffoffs has been set to  N/D");
						}else{
							message.raiseError("MFI_20", "Cannot set status to Partial , prior buyffoffs has been set to  N/D for serial Number " + serialNo);
						}
					}
				}
			}
		}
	}

	public List getSerialBuyoffListHistTab(String orderId, String calledFrom, String whereClauseFromClient){

		return getSerialBuyoffListHistTab(orderId, calledFrom, whereClauseFromClient, null, null, null);
	}

	public List getSerialBuyoffListHistTab(String orderId, String calledFrom, String whereClauseFromClient,Number operKey,Number stepKey, String refID){

		List list = buyoffDaoPW.selectSerialBuyoffListHistTab(orderId, calledFrom, whereClauseFromClient,operKey,stepKey,refID);


		if(list.size() == 0){
			Map retMap = new HashMap();
			retMap.put("SERIAL_NO",null); ///////RTT  1258
			retMap.put("BUYOFF_ID",null);
			retMap.put("OPER_NO",null);
			retMap.put("STEP_NO",null);
			retMap.put("BUYOFF_TYPE",null);
			retMap.put("BUYOFF_TITLE",null);
			retMap.put("USER_NAME",null);
			retMap.put("UPDT_USERID",null); 
			retMap.put("BUYOFF_STATUS",null); 
			retMap.put("PERCENT_COMPLETE",null); 
			retMap.put("COMPLETE_QTY",null);
			retMap.put("BUYOFF_CERT",null);
			retMap.put("COMMENTS",null);
			retMap.put("TIME_STAMP",null);
			retMap.put("OPTIONAL_FLAG",null);
			retMap.put("UCF_SRLOPBUYOFF_VCH4000_1",null);

			list = new ArrayList();
			list.add(retMap);
		}

		return list;
	}


	public List getSerialBuyoffTabList(String orderId, 	String orderType){

		return getSerialBuyoffTabList(orderId, orderType,null);
	}

	public List getSerialBuyoffTabList(String orderId, 	String orderType, String whereClauseFromClient){

		// called from solumina client to get rows for buyoff tab List
		// Date signoffDate = null;
		List retList = new ArrayList();
		List buyoffList = buyoffDaoPW.selectBuyoffTabList(orderId, orderType, ContextUtil.getUsername(),whereClauseFromClient);

		if(buyoffList.size() > 0){

			for (int i=0;i<buyoffList.size();i++){
				Map map = (Map) buyoffList.get(i);

				String buyoffID = (String) map.get("BUYOFF_ID");   //Defect 879
				String buyoffStatus = (String) map.get("BUYOFF_STATUS"); 
				String comments = (String) map.get("COMMENTS");
				String percentQtyComplete = (String) map.get("PERCENT_QTY_COMPLETE");
				String operNo = (String) map.get("OPER_NO");
				String stepNo = (String) map.get("STEP_NO");
				String buyoffType = (String) map.get("BUYOFF_TYPE");
				String buyoffCert = (String) map.get("BUYOFF_CERT");
				String optionalFlag = (String) map.get("OPTIONAL_FLAG");
				String crossOrderFlag = (String) map.get("CROSS_ORDER_FLAG"); 
				String updtUserid = (String) map.get("UPDT_USERID");   
				Date   timestamp = (Date) map.get("TIME_STAMP"); 
				String userName = (String) map.get("USER_NAME");  //Defect 879 
				String image = (String) map.get("IMAGE"); 
				String slideId = (String) map.get("SLIDE_ID"); 
				String refId = (String) map.get("SLIDE_EMBEDDED_REF_ID");   
				String buyoffTitle = (String) map.get("BUYOFF_TITLE"); 
				Number operKey = (Number) map.get("OPER_KEY");   
				Number stepKey = (Number) map.get("STEP_KEY"); 

				Map retMap = new HashMap();
				retMap.put("BUYOFF_ID",buyoffID);  //Defect 879
				retMap.put("BUYOFF_STATUS",buyoffStatus);
				retMap.put("COMMENTS",comments);
				retMap.put("PERCENT_QTY_COMPLETE",percentQtyComplete); 
				retMap.put("OPER_NO",operNo); 
				retMap.put("STEP_NO",stepNo); 
				retMap.put("BUYOFF_TYPE",buyoffType); 
				retMap.put("BUYOFF_CERT",buyoffCert); 
				retMap.put("OPTIONAL_FLAG",optionalFlag); 
				retMap.put("CROSS_ORDER_FLAG",crossOrderFlag); 
				retMap.put("UPDT_USERID", updtUserid);
				retMap.put("TIME_STAMP", timestamp); // Defect 1024 signoffDate);
				retMap.put("USER_NAME", userName);//Defect 879
				retMap.put("IMAGE",image);
				retMap.put("SLIDE_ID",slideId);
				retMap.put("SLIDE_EMBEDDED_REF_ID",refId);
				retMap.put("BUYOFF_TITLE",buyoffTitle);
				retMap.put("OPER_KEY",operKey);
				retMap.put("STEP_KEY",stepKey);




				retList.add(retMap);
			}
		}else{
			Map retMap = new HashMap();
			retMap.put("BUYOFF_ID",null);//Defect 879
			retMap.put("BUYOFF_STATUS",null);
			retMap.put("COMMENTS",null);
			retMap.put("PERCENT_QTY_COMPLETE",null); 
			retMap.put("OPER_NO",null); 
			retMap.put("STEP_NO",null); 
			retMap.put("BUYOFF_TYPE",null); 
			retMap.put("BUYOFF_CERT",null); 
			retMap.put("OPTIONAL_FLAG",null); 
			retMap.put("CROSS_ORDER_FLAG",null); 
			retMap.put("UPDT_USERID", null);
			retMap.put("TIME_STAMP", null);
			retMap.put("USER_NAME", null);//Defect 879
			retMap.put("IMAGE",null);
			retMap.put("SLIDE_ID",null);
			retMap.put("SLIDE_EMBEDDED_REF_ID",null);
			retMap.put("BUYOFF_TITLE",null);
			retMap.put("OPER_KEY",null);
			retMap.put("STEP_KEY",null);
			retList.add(retMap);
		}
		return retList;
	}


	public void insertBuyoffAcceptForTrainee(String orderId, 
			Number operKey, 
			Number stepKey,
			String buyoffId,
			String serialNumberList,
			String buyoffComment,
			String signoffUserid){ 

		StringTokenizer serialNoTokens;  //1258
		String serialNo = "";  //1258
		String delimiter = ";";  //1258
		String serialId = null;
		String lotId = null;

		if (serialNumberList == null || serialNumberList.isEmpty() ){ //Defect 1033
			serialNumberList = "N/A";
		}

		Map sfwidOperDescMap = commonDaoPW.getSfwidOperDescMap(orderId, operKey, stepKey);
		String operNo = (String)sfwidOperDescMap.get("OPER_NO");
		String stepNo = (String) sfwidOperDescMap.get("STEP_NO");
		String operOptFlag =(String) sfwidOperDescMap.get("OPER_OPT_FLAG");

		Map sfwidOperBuyoffMap = commonDaoPW.getSfwidOperBuyoffMap(orderId, operKey, stepKey, buyoffId);
		String buyoffTitle =  (String) sfwidOperBuyoffMap.get("BUYOFF_TITLE");
		String buyoffCert =  (String) sfwidOperBuyoffMap.get("BUYOFF_CERT");

		String userFullName = null;
		if (StringUtils.isNotBlank(signoffUserid)){
			userFullName = commonDaoPW.getUserFullName(signoffUserid);
		}

		serialNoTokens = new StringTokenizer(serialNumberList, delimiter);



		while (serialNoTokens.hasMoreElements()){
			serialNo = serialNoTokens.nextElement().toString();
			Map serialMap = buyoffDaoPW.selectSerialNumberInfo(orderId,serialNo);
			serialId = (String) serialMap.get("SERIAL_ID");
			lotId =(String) serialMap.get("LOT_ID");
			buyoffDaoPW.insertBuyoffAcceptForTrainee( orderId, operKey, stepKey, lotId, serialId, buyoffId, signoffUserid, buyoffComment);

			//begin defect 1772

			buyoffDaoPW.insertBuyoffHistory(orderId,
					operKey, 
					stepKey, 
					buyoffId,
					"TRAINING",
					"100", 
					buyoffComment, 
					serialNo, 
					operNo,
					stepNo, 
					buyoffTitle, 
					"", //buyoffType
					buyoffCert,
					operOptFlag, 
					signoffUserid,
					userFullName,
					"");  //signoffs

			//end defect 1772

		}
		//event.refreshTool();
		event.refresh_alias("BuyoffExe");
	}

	//defect 1775 begin
	public Map validateHolds(Number operationKey,
			String serialNumberList,
			String orderId)
	{
		Map returnMap = new HashMap();
		returnMap.put("SECURITY_GROUP", null);
		returnMap.put("FINAL_HOLDS", "N");
		String orderControl = orderDetails.orderControl(orderId);
		List holdsInfo = widStepDatColDao.selectOrderHoldsInfo(orderId, null, null);
		Iterator holdsInfoIter = holdsInfo.iterator();
		while (holdsInfoIter.hasNext())
		{

			Map holdInfoMap = (Map) holdsInfoIter.next();
			String stopType = (String) holdInfoMap.get("STOP_TYPE");
			String holdId = (String) holdInfoMap.get("HOLD_ID");
			Number holdOperKey = (Number) holdInfoMap.get("OPER_KEY");
			String holdStatus = (String) holdInfoMap.get("HOLD_STATUS");
			String stringHoldOperKey = String.valueOf(holdOperKey);
			String stringOperationKey = String.valueOf(operationKey);
			List holdSerialNumbers = new ArrayList();

			if (StringUtils.equals(stopType, SoluminaConstants.OPER_COMPLETION))
			{

					holdSerialNumbers = orderDao.selectSerialNumbers(holdId);

					if (holdSerialNumbers.isEmpty()
							&& StringUtils.equals(stringOperationKey, stringHoldOperKey)
							&& StringUtils.equals(holdStatus.toUpperCase(), SoluminaConstants.OPEN_STATUS)
							&& commonDaoPW.isLastActiveBuyoffInSpecificOperation(orderId,operationKey))
					{
						// GE-5511
						// Cannot complete operation because there is an open hold with stop type OPER COMPLETION on it
         
						message.raiseError("MFI_FE6B14D6F42A4233BB2F5E424377DA0B");
						returnMap.put("FINAL_HOLDS", "Y");
						return returnMap;
					}

					if (StringUtils.equals(stringOperationKey, stringHoldOperKey)
							&& StringUtils.equals(holdStatus.toUpperCase(), SoluminaConstants.OPEN_STATUS))
					{
						Iterator holdSerialNumberIter = holdSerialNumbers.iterator();
						String[] serialArray = parse.parse(serialNumberList, SoluminaConstants.SEMI_COLON, false);
						String tempSerials = "";
						while (holdSerialNumberIter.hasNext())
						{
							Map tempSerialMap = (Map) holdSerialNumberIter.next();
							String tempSerial = (String) tempSerialMap.get("SERIAL_NO");
							for (int i = 0; i < serialArray.length; i++)
							{
								if (StringUtils.equals(tempSerial, serialArray[i]))
								{
								  if (commonDaoPW.isLastActiveBuyoffInSpecificOperationBySerial(orderId, operationKey, tempSerial))
								{
									  message.raiseError("MFI_20A6BD881C114013876687D5D05850ED",
												SoluminaConstants.OPER_COMPLETION,
												tempSerial);
										//message.raiseError("MFI_FE6B14D6F42A4233BB2F5E424377DA0B");

										returnMap.put("FINAL_HOLDS", "Y");
										return returnMap;
								}
									//tempSerials = tempSerials + tempSerial + SoluminaConstants.COMMA;
								}
							}
						}

					}

			}
			else 
			
			if (StringUtils.equals(stopType, SoluminaConstants.ORDER_COMPLETION))
			{    //last active operation and last active buyoff
				//if (commonDaoPW.isLastActiveOperation(orderId)
				if ( commonDaoPW.isLastActiveBuyoffInAllOperations(orderId)
				   && StringUtils.equals(holdStatus.toUpperCase(), SoluminaConstants.OPEN_STATUS)){

					holdSerialNumbers = orderDao.selectSerialNumbers(holdId);

					
					if (holdSerialNumbers.isEmpty()) {
						message.raiseError("MFI_BDAC1DB44ACB4C8CB8FCE02C52D695E8");
					}
					else 
					{
						Iterator holdSerialNumberIter = holdSerialNumbers.iterator();
						String[] serialArray = parse.parse(serialNumberList, SoluminaConstants.SEMI_COLON, false);
						String tempSerials = "";
						while (holdSerialNumberIter.hasNext())
						{
							Map tempSerialMap = (Map) holdSerialNumberIter.next();
							String tempSerial = (String) tempSerialMap.get("SERIAL_NO");
							for (int i = 0; i < serialArray.length; i++)
							{
								if (StringUtils.equals(tempSerial, serialArray[i]))
								{
									tempSerials = tempSerials + tempSerial + SoluminaConstants.COMMA;
								}
							}
						}
						
						if (StringUtils.isNotEmpty(tempSerials))
						{
							//"MFI_20A6BD881C114013876687D5D05850ED"
							tempSerials = tempSerials.substring(0, tempSerials.length() - 1);

							message.raiseError("MFI_BDAC1DB44ACB4C8CB8FCE02C52D695E8",
									SoluminaConstants.ORDER_COMPLETION,
									tempSerials);

							returnMap.put("FINAL_HOLDS", "Y");
							return returnMap;

						}
					}		
				}
			}
		}

		return returnMap;
	} //defect 1775 end

	public void oobOperationsignOnOff(String orderId, Number operationKey, Number stepKey, String signOnUserId){

		oobIOperation.signOnOff("ON", //onOffFlag, 
				"WORK_ORDER", //signOnType, 
				orderId, 
				operationKey, 
				stepKey, 
				null, // project, 
				null, // activity, 
				null, // accountName, 
				signOnUserId, 
				"N", // rollUpFlag, 
				null, //ucfOperationSignOnVch1, 
				null, //ucfOperationSignOnVch2, 
				null, //ucfOperationSignOnVch3, 
				null, //ucfOperationSignOnVch4, 
				null, //ucfOperationSignOnVch5, 
				null, //ucfOperationSignOnFlag1, 
				null, //ucfOperationSignOnFlag2, 
				null, //ucfOperationSignOnNumber1,
				null, //ucfOperationSignOnNumber2, 
				null, //signOffAllFlag, 
				null, //laborSignOnType, 
				null, //ucfOperationSignOnVch6, 
				null, //ucfOperationSignOnVch7, 
				null, //ucfOperationSignOnVch8, 
				null, //ucfOperationSignOnVch9, 
				null, //ucfOperationSignOnVch10, 
				null, //ucfOperationSignOnVch11, 
				null, //ucfOperationSignOnVch12, 
				null, //ucfOperationSignOnVch13, 
				null, //ucfOperationSignOnVch14, 
				null, //ucfOperationSignOnVch15, 
				null, //ucfOperationSignOnFlag3, 
				null, //ucfOperationSignOnFlag4, 
				null, //ucfOperationSignOnFlag5,
				null, //ucfOperationSignOnNumber3, 
				null, //ucfOperationSignOnNumber4, 
				null, //ucfOperationSignOnNumber5,
				null, //ucfOperationSignOnDate1,
				null, //ucfOperationSignOnDate2,
				null, //ucfOperationSignOnDate3,
				null, //ucfOperationSignOnDate4,
				null, //ucfOperationSignOnDate5,
				null, //ucfOperationSignOnVch2551, 
				null, //ucfOperationSignOnVch2552,
				null, //ucfOperationSignOnVch2553,
				null, //ucfOperationSignOnVch40001,
				null, //ucfOperationSignOnVch40002,
				null); //groupJobNo);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List selectAvailableCollectionUnitsPW( String orderId,
			Number operationKey,
			Number stepKey,
			String tagType,
			String crossOrderFlag,
			String dataCollectionList,
			String skipFlagOrStatus,
			boolean isForDefault, 
			String whereClause,
			String buyoffId,
			String orderNo){

		List returnListPW = new ArrayList();
		String lotId;
		String serialId;

		List oobList = oobIOperation.selectAvailableCollectionUnits(orderId, 
				operationKey, 
				stepKey, 
				tagType, 
				crossOrderFlag, 
				dataCollectionList, 
				skipFlagOrStatus, 
				isForDefault, 
				whereClause);
		if (oobList!=null && !oobList.isEmpty()){
			for (int i=0; i<oobList.size(); i++){
				Map oobMap = (Map) oobList.get(i);
				String serialNo = (String)oobMap.get("SERIAL_NO");
				if (StringUtils.isNotBlank(serialNo) ){

					Map serialMap = buyoffDaoPW.selectSerialNumberInfo(orderId,serialNo);
					lotId = (String) serialMap.get("LOT_ID");
					serialId = (String) serialMap.get("SERIAL_ID");

					String buyoffStatus = getBuyoffStatus(orderId, operationKey, stepKey, buyoffId, lotId, serialId);
					if (!StringUtils.equalsIgnoreCase(buyoffStatus, "ACCEPT")){

						Map returnMap = new HashMap();
						returnMap.put("SERIAL_NO",serialNo);
						returnMap.put("ORDER_NO",orderNo);
						returnMap.put("SECURITY_GROUP","");
						returnListPW.add(returnMap);
					}
				}
			}
		}

		if (returnListPW!=null && !returnListPW.isEmpty()){
			return returnListPW;
		}else{
			return oobList;	
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public List getOrderCurrentUnitsPW(String orderId,
			Number operationKey,
			Number stepKey,
			String buyoffId,
			String userid){

		List returnListPW = new ArrayList();
		StringTokenizer serialNoTokens; 
		String serialNo = "";  
		String delimiter = ";";
		StringBuilder serialNoListPW = new StringBuilder();

		List oobCurrentUnitsList = oobIOperation.getCurrentUnits(orderId, operationKey, stepKey, userid);
		String serialNoList = null;
		String availableUnits  = null;
		String subSelectedFlag = null;
		String refresh = null;
		String firstAvailableUnit  = null;
		String securityGroup  = null;

		if (oobCurrentUnitsList!=null && !oobCurrentUnitsList.isEmpty()){

			Map currentUnitMap = (Map)oobCurrentUnitsList.get(0);
			serialNoList = (String) currentUnitMap.get("SERIAL_NO");
			availableUnits = (String) currentUnitMap.get("AVAILABLE_UNITS");
			subSelectedFlag = (String) currentUnitMap.get("SUBSELECTED_FLAG");
			refresh = (String) currentUnitMap.get("REFRESH");
			firstAvailableUnit = (String) currentUnitMap.get("FIRST_AVAILABLE_UNIT");
			securityGroup = (String) currentUnitMap.get("SECURITY_GROUP");

			if (StringUtils.isNotBlank(serialNoList)){
				serialNoTokens = new StringTokenizer(serialNoList, delimiter);
				while (serialNoTokens.hasMoreElements()){
					serialNo = serialNoTokens.nextElement().toString();

					Map serialMap = buyoffDaoPW.selectSerialNumberInfo(orderId,serialNo);
					String lotId = (String) serialMap.get("LOT_ID");
					String serialId = (String) serialMap.get("SERIAL_ID");

					String buyoffStatus = getBuyoffStatus(orderId, operationKey, stepKey, buyoffId, lotId,serialId);
					if (!StringUtils.equalsIgnoreCase(buyoffStatus, "ACCEPT")){
						serialNoListPW.append(serialNo + ";"); 
					}
				}

				Map returnMap = new HashMap();

				returnMap.put("SERIAL_NO", serialNoListPW.toString());
				returnMap.put("AVAILABLE_UNITS", serialNoListPW.toString());
				returnMap.put("SUBSELECTED_FLAG", subSelectedFlag);
				returnMap.put("REFRESH", refresh);
				returnMap.put("FIRST_AVAILABLE_UNIT", firstAvailableUnit);
				returnMap.put("SECURITY_GROUP", null);
				returnListPW.add(returnMap);
			}
		}

		if (returnListPW!=null && !returnListPW.isEmpty()){
			return returnListPW;
		}else{
			return oobCurrentUnitsList;
		}
	}


	@SuppressWarnings("rawtypes")
	public String getBuyoffStatus(String orderId, Number operationKey, Number stepKey, String buyoffId,	String lotId,String serialId) {

		Map SfwidSerialOperBuyoffMap = buyoffDaoPW.selectSfwidSerialOperBuyoff(orderId, operationKey, stepKey, buyoffId, serialId, lotId);
		String buyoffStatus = (String)SfwidSerialOperBuyoffMap.get("BUYOFF_STATUS");
		return buyoffStatus;
	}

	@SuppressWarnings("rawtypes")
	public Map checkUsersWorkCenterCerts(String workLoc,
			String workLocId, 
			String workDeptId,
			String workCenterId,
			String userId){

		String cert = null;
		Map userCertMap = null;
		Map returnMap = new HashMap();
		returnMap.put("SECURITY_GROUP", null);

		if(workLocId == null || workDeptId == null || workCenterId == null){
			message.raiseError("MFI_20", "Work Center not found for this operation" );
		}

		List workCenterCertList = buyoffDaoPW.selectCertsforWorkCenter(workLocId, workDeptId, workCenterId);
		List usersCertList = buyoffDaoPW.selectUsersCertsForWorkLoc(userId, workLoc);

		if(workCenterCertList.size() > 0 && usersCertList.size() > 0){
			Map workCenterCertsMap = (Map) workCenterCertList.get(0);
			String[] workCenterCertsTokens = ((String) workCenterCertsMap.get("CERT")).split(";");
			workCenterCertList = Arrays.asList(workCenterCertsTokens);

			List<String> usersCertArrayList = new ArrayList();
			for (int i=0; i<usersCertList.size(); i++){
				userCertMap = (Map) usersCertList.get(i);
				cert = (String) userCertMap.get("CERT");
				usersCertArrayList.add(cert);
			}

			if(!usersCertArrayList.containsAll(workCenterCertList)){
				returnMap.put("BUYOFF_AUTH", "N");
			}else{
				returnMap.put("BUYOFF_AUTH", "Y");
			}
		}else if(workCenterCertList.size() > 0 && usersCertList.size() == 0){
			returnMap.put("BUYOFF_AUTH", "N");
		}else{
			returnMap.put("BUYOFF_AUTH", "Y");
		}
		return returnMap;
	}

	public String getOrderSerialFlag(String orderId) {

		List sfwidOrderDesclist = buyoffDaoPW.selectSfwidOrderDesc(orderId);
		Map sfwidOrderDescMap = (Map)sfwidOrderDesclist.get(0);
		String serialFlag = (String)sfwidOrderDescMap.get("SERIAL_FLAG");
		if (StringUtils.isBlank(serialFlag)){
			message.raiseError("MFI_20", "serialFlag cannot be null");
		}
		return serialFlag;
	}
	public String convertSerialNumberList(String serialNumberList) {

		String changedSerialNumberList = "";
		StringTokenizer serialNoTokens; 
		String delimiter = ";";
		serialNoTokens = new StringTokenizer(serialNumberList, delimiter);
		while (serialNoTokens.hasMoreElements()){
			String serialNo = serialNoTokens.nextElement().toString();

			if (StringUtils.isBlank(changedSerialNumberList)){
				changedSerialNumberList =  "'" + serialNo + "'" ;
			}else{
				changedSerialNumberList = changedSerialNumberList + ",'" + serialNo + "'" ;
			}
		}
		return changedSerialNumberList;
	}


	public void oobPreExecutionInsert2(String orderId, 
			Number operKey, 
			Number stepKey, 
			String userID, 
			String buyoffComment,
			String buyoffId, 
			String buyoffStatus,
			String serialNumberList, 
			String pwOrderNo, 
			String lotNo, 
			String buyoffType,
			String buyoffCertificate,
			Number percentComplete,
			String tagType) {

		try{
			ContextUtil.getUser().getContext().set("SKIP_Optional_Data_Collections_MSG", "TRUE");
			oobBuyoff.preExecutionInsert2(orderId, 
					pwOrderNo, 
					operKey, 
					stepKey, 
					lotNo, 
					serialNumberList, 
					buyoffId, 
					buyoffStatus,//status, 
					buyoffType, 
					buyoffCertificate, 
					buyoffComment, 
					null, //calledFrom, 
					tagType, //ucfSerialOperationBuyoffVch1, 
					userID, //ucfSerialOperationBuyoffVch2, 
					null, //ucfSerialOperationBuyoffVch3, 
					null, //ucfSerialOperationBuyoffVch4, 
					null, //ucfSerialOperationBuyoffVch5, 
					null, //ucfSerialOperationBuyoffVch6, 
					null, //ucfSerialOperationBuyoffVch7, 
					null, //ucfSerialOperationBuyoffVch8, 
					null, //ucfSerialOperationBuyoffVch9, 
					null, //ucfSerialOperationBuyoffVch10, 
					null, //ucfSerialOperationBuyoffVch11, 
					null, //ucfSerialOperationBuyoffVch12, 
					null, //ucfSerialOperationBuyoffVch13, 
					null, //ucfSerialOperationBuyoffVch14, 
					null, //ucfSerialOperationBuyoffVch15, 
					null, //ucfSerialOperationBuyoffNum1,
					null, //ucfSerialOperationBuyoffNum2, 
					null, //ucfSerialOperationBuyoffNum3,
					null, //ucfSerialOperationBuyoffNum4,
					null, //ucfSerialOperationBuyoffNum5,
					null, //ucfSerialOperationBuyoffFlag1, 
					null, //ucfSerialOperationBuyoffFlag2,
					null, //ucfSerialOperationBuyoffFlag3, 
					null, //ucfSerialOperationBuyoffFlag4, 
					null, //ucfSerialOperationBuyoffFlag5, 
					null, //ucfSerialOperationBuyoffDate1,
					null, //ucfSerialOperationBuyoffDate2,
					null, //ucfSerialOperationBuyoffDate3, 
					null, //ucfSerialOperationBuyoffDate4, 
					null, //ucfSerialOperationBuyoffDate5, 
					null, //rejectType, 
					null, //ucfSerialOperationBuyoffVch2551,
					null, //ucfSerialOperationBuyoffVch2552,
					null, //ucfSerialOperationBuyoffVch2553,
					null, //ucfSerialOperationBuyoffVch40001,
					null, //ucfSerialOperationBuyoffVch40002, 
					percentComplete,
					null, //qtyComplete,
					null, //partialComplete, 
					userID, //userId,
					null, //"N", //secondLoginUser,
					null, //groupJobNo, 
					null);//serialInfo);
		}finally {
			ContextUtil.getUser().getContext().set("SKIP_Optional_Data_Collections_MSG", null);
		}
	}


	@SuppressWarnings("rawtypes")
	public void finalBuyoffChecKtoCloseOper(String orderId, Number operKey, Number stepKey, String tagType) {

		boolean finalBuyoff;		
		String orderSerialFlag = getOrderSerialFlag(orderId);
		finalBuyoff = buyoffDaoPW.isFinalBuyoffInOper(orderId, operKey,orderSerialFlag);

		if (finalBuyoff){

			String serialNoList = buyoffDaoPW.SelectAllBuyoffSerialNoToCloseOper(orderId, operKey, stepKey);
			if(StringUtils.isNotBlank(serialNoList)){

				Map lotMap = buyoffDaoPW.selectLotNumberInfo(orderId);
				String lotNo	= (String) lotMap.get("LOT_NO");

				//Set Operation Optional Flag
				buyoffDaoPW.updateOrderOperOptFlag(orderId,operKey, "Y");	

				oobSerial.completeMultipleOperation(orderId,
						operKey,
						lotNo,
						serialNoList,
						SoluminaConstants.NO,  // rollup flag
						SoluminaConstants.YES,  // move flag
						orderId,
						SoluminaConstants.NO); //check all Data collections?
				//Set Operation Optional Flag
				buyoffDaoPW.updateOrderOperOptFlag(orderId, operKey, "N");	
				buyoffDaoPW.updateOrderOperStatusChgReason(orderId, operKey, tagType);
				//event.refreshTool();
			}
		}
	}

	@SuppressWarnings("deprecation")
	public String insertOrderNote(String orderId, 
			Number operKey, 
			Number stepKey, 
			String noteText, 
			String buyoffId, 
			String noteTitle) {

		String noteId = null;

		if (StringUtils.isNotBlank(noteText)){
			String userName = ContextUtil.getUsername();
			noteId = orderDao.selectNextOrderSequence();
			orderDao.insertOrderNote(orderId,
					noteId,
					"BUYOFF", //noteType,
					operKey,
					stepKey,
					noteTitle,
					noteText,
					SoluminaConstants.OPEN_STATUS, //noteStatus,
					buyoffId, //ucfOrderNoteVch1,
					null,//ucfOrderNoteVch2,
					null,//ucfOrderNoteVch3,
					null,//ucfOrderNoteVch4,
					null,//ucfOrderNoteVch5,
					null,//ucfOrderNoteFlag1,
					null,//ucfOrderNoteFlag2,
					null,//ucfOrderNoteNumber1,
					null,//ucfOrderNoteNumber2,
					null,//ucfOrderNoteDate1,
					userName,
					null,//objectId,
					null,//fileName,
					null,//ucfOrderNoteVch6,
					null,//ucfOrderNoteVch7,
					null,//ucfOrderNoteVch8,
					null,//ucfOrderNoteVch9,
					null,//ucfOrderNoteVch10,
					null,//ucfOrderNoteVch11,
					null,//ucfOrderNoteVch12,
					null,//ucfOrderNoteVch13,
					null,//ucfOrderNoteVch14,
					null,//ucfOrderNoteVch15,
					null,//ucfOrderNoteFlag3,
					null,//ucfOrderNoteFlag4,
					null,//ucfOrderNoteFlag5,
					null,//ucfOrderNoteNumber3,
					null,//ucfOrderNoteNumber4,
					null,//ucfOrderNoteNumber5,
					null,//ucfOrderNoteDate2,
					null,//ucfOrderNoteDate3,
					null,//ucfOrderNoteDate4,
					null,//ucfOrderNoteDate5,
					null,//ucfOrderNoteVch2551,
					null,//ucfOrderNoteVch2552,
					null,//ucfOrderNoteVch2553,
					null,//ucfOrderNoteVch40001,
					null);//ucfOrderNoteVch40002);
		}

		return noteId;
	}

	public void addSignOff( String orderId, 
			Number operKey, 
			Number stepKey,
			String buyoffId,
			String buyoffType,    
			String signoffUserid,
			String serialNumberList){

		StringTokenizer serialNoTokens;  //1258
		String serialNo = "";  //1258
		String delimiter = ";";  //1258
		String serialId = null;
		String lotId = null;

		if (serialNumberList == null || serialNumberList.isEmpty() ){
			serialNumberList = "N/A";
		}
		serialNoTokens = new StringTokenizer(serialNumberList, delimiter);


		Map sfwidOperDescMap = commonDaoPW.getSfwidOperDescMap(orderId, operKey, stepKey);
		String operNo = (String)sfwidOperDescMap.get("OPER_NO");
		String stepNo = (String) sfwidOperDescMap.get("STEP_NO");
		String operOptFlag =(String) sfwidOperDescMap.get("OPER_OPT_FLAG");

		Map sfwidOperBuyoffMap = commonDaoPW.getSfwidOperBuyoffMap(orderId, operKey, stepKey, buyoffId);
		String buyoffTitle =  (String) sfwidOperBuyoffMap.get("BUYOFF_TITLE");
		String buyoffCert =  (String) sfwidOperBuyoffMap.get("BUYOFF_CERT");

		String userFullName = null;
		if (StringUtils.isNotBlank(signoffUserid)){
			userFullName = commonDaoPW.getUserFullName(signoffUserid);
		}

		while (serialNoTokens.hasMoreElements()){

			serialNo = serialNoTokens.nextElement().toString();
			Map serialMap = buyoffDaoPW.selectSerialNumberInfo(orderId,serialNo);
			serialId = (String) serialMap.get("SERIAL_ID");
			lotId =(String) serialMap.get("LOT_ID");
			//for each sn in the list get the serialId and update the Partial buyoff

			String signoffs = null;
			SimpleDateFormat amPMDatetimeFormatter = new SimpleDateFormat ("MM/dd/yyyy hh:mm:ss a");
			List signoffList = buyoffDaoPW.selectAdditionalSignoffs(orderId, operKey, stepKey, buyoffId, serialId,lotId);  /// add serialId
			if(signoffList.size() > 0){
				Map map = (Map) signoffList.get(0);
				signoffs = (String) map.get("UCF_SRLOPBUYOFF_VCH4000_1"); 
			}

			Date now = new Date();
			String dateStr = amPMDatetimeFormatter.format(now);

			if(signoffs == null){
				signoffs = signoffUserid + "-" + dateStr;
			}else{
				signoffs = signoffs + "," + signoffUserid + "-" + dateStr;
			}

			if (signoffs.length() > 4000){
				message.raiseError("MFI_20", "Number of signoffs is longer than 4000 characters" );
			}

			buyoffDaoPW.updateAdditionalSignoffs(orderId, operKey, stepKey, buyoffId, signoffs, serialId,lotId);  //1258
			Map sfwidSerialOperBuyoffMapp = buyoffDaoPW.selectSfwidSerialOperBuyoff(orderId, operKey, stepKey, buyoffId, serialId, lotId);
			String buyoffStatus = (String) sfwidSerialOperBuyoffMapp.get("BUYOFF_STATUS");
			String percentComplete = (String) sfwidSerialOperBuyoffMapp.get("PERCENT_COMPLETE_PW");

			//begin defect 1772
			buyoffDaoPW.insertBuyoffHistory(orderId, 
					operKey, 
					stepKey, 
					buyoffId, 
					buyoffStatus, 
					percentComplete, 
					"Additional Signoff", 
					serialNo, 
					operNo, 
					stepNo,
					buyoffTitle, 
					buyoffType, 
					buyoffCert, 
					operOptFlag, 
					signoffUserid,
					userFullName, 
					signoffs);


			//end defect 1772

		}

		//event.refreshTool();
		event.refresh_alias("BuyoffExe+GetSignoffs");
		return;
	}

	public List getAddtionalSignoffs(String orderId,
			Number operKey, 
			Number stepKey,
			String buyoffId){

		List retList = new ArrayList();
		String token;
		String signoffUserid;
		String signoffDate;
		String signoffName;	
		Map retMap;
		List signoffList = buyoffDaoPW.selectAdditionalSignoffs(orderId, operKey, stepKey, buyoffId);

		if(signoffList.size() > 0){
			ArrayList newList = new ArrayList();

			for (int i=0;i<signoffList.size();i++){
				Map map = (Map) signoffList.get(i);  
				String signoffs = (String) map.get("UCF_SRLOPBUYOFF_VCH4000_1"); 
				StringTokenizer tokens = new StringTokenizer(signoffs, ",");
				while (tokens.hasMoreElements()) {
					token = tokens.nextElement().toString();
					signoffUserid = token.substring(0, token.indexOf("-"));
					signoffDate = token.substring(token.indexOf("-")+1);
					signoffName = StringUtils.defaultIfEmpty(commonDaoPW.getUserFullName(signoffUserid), signoffUserid);
					if (signoffName == null || signoffName.isEmpty()){
						signoffName = signoffUserid;
					}
					retMap = new HashMap();
					retMap.put("SIGNOFF_USERID", signoffUserid);
					retMap.put("SIGNOFF_DATE", signoffDate);
					retMap.put("SIGNOFF_NAME", signoffName);
					newList.add(retMap); 
				}
			}  
			// set removes duplicate records
			Set set = new HashSet(newList);
			retList = new ArrayList(set);
			Collections.sort(retList, new Comparator<Map>(){
				public int compare (Map m1, Map m2){
					return ((String) m1.get("SIGNOFF_DATE")).compareTo((String) m2.get("SIGNOFF_DATE"));
				}
			});
			return retList;
		}else{
			retMap = new HashMap();
			retMap.put("SIGNOFF_USERID", null);
			retMap.put("SIGNOFF_DATE", null);
			retMap.put("SIGNOFF_NAME", null);
			retList.add(retMap);
		}
		return retList;
	}

	// Defect 694
	public List getSerialBuyoffList(String orderId, 
			Number operKey, 
			Number stepKey, 
			String buyoffId, 
			String serialId, 
			String lotId, 
			String userId, 
			String userConnectionId){

		Date signoffDate = null;
		Boolean addSigsExist = false;
        String serialNo = commonDaoPW.getSerialNofromSerialId(serialId,orderId);
        
		//List list = buyoffDaoPW.selectSerialBuyoffList(orderId, operKey, stepKey, buyoffId, serialId, lotId, userId, userConnectionId); //defect 1994
		List list = buyoffDaoPW.selectSerialBuyoffListHistTabForDisplayTab(orderId, "BuyoffDisplayTab", "",operKey,stepKey,serialNo); //defect 1994

		if(list.size() > 0 ){
			ArrayList newList = new ArrayList();

			for (int i=0;i<list.size();i++){
				Map map = (Map) list.get(i);
				String signoffs = (String) map.get("UCF_SRLOPBUYOFF_VCH4000_1"); 

				if(signoffs != null){
					String buyoffStatus = (String) map.get("BUYOFF_STATUS"); 
					if(!"REOPEN".equals(buyoffStatus)){
						addSigsExist = true;

						String comments = (String) map.get("COMMENTS");
						String percentQtyComplete = (String) map.get("PERCENT_QTY_COMPLETE");
						Date ucfDate1 = (Date) map.get("UCF_SRLOPBUYOFF_DATE1");
						Date ucfDate2 = (Date) map.get("UCF_SRLOPBUYOFF_DATE2");
						Date ucfDate3 = (Date) map.get("UCF_SRLOPBUYOFF_DATE3");
						Date ucfDate4 = (Date) map.get("UCF_SRLOPBUYOFF_DATE4");
						Date ucfDate5 = (Date) map.get("UCF_SRLOPBUYOFF_DATE5");
						/*	Number ucfFlag1 = (Number) map.get("UCF_SRLOPBUYOFF_FLAG1"); 
	    					Number ucfFlag2 = (Number) map.get("UCF_SRLOPBUYOFF_FLAG2");   
	    					Number ucfFlag3 = (Number) map.get("UCF_SRLOPBUYOFF_FLAG3"); 
	    					Number ucfFlag4 = (Number) map.get("UCF_SRLOPBUYOFF_FLAG4");   
	    					Number ucfFlag5 = (Number) map.get("UCF_SRLOPBUYOFF_FLAG5"); */// 1033
						String ucfFlag1 = (String) map.get("UCF_SRLOPBUYOFF_FLAG1"); // 1033
						String ucfFlag2 = (String) map.get("UCF_SRLOPBUYOFF_FLAG2"); // 1033
						String ucfFlag3 = (String) map.get("UCF_SRLOPBUYOFF_FLAG3"); // 1033
						String ucfFlag4 = (String) map.get("UCF_SRLOPBUYOFF_FLAG4"); // 1033
						String ucfFlag5 = (String) map.get("UCF_SRLOPBUYOFF_FLAG5"); // 1033
						Number ucfNum1 = (Number) map.get("UCF_SRLOPBUYOFF_NUM1"); 
						Number ucfNum2 = (Number) map.get("UCF_SRLOPBUYOFF_NUM2");   
						Number ucfNum3 = (Number) map.get("UCF_SRLOPBUYOFF_NUM3"); 
						Number ucfNum4 = (Number) map.get("UCF_SRLOPBUYOFF_NUM4");   
						Number ucfNum5 = (Number) map.get("UCF_SRLOPBUYOFF_NUM5"); 
						String ucfVch1 = (String) map.get("UCF_SRLOPBUYOFF_VCH1");  
						String ucfVch2 = (String) map.get("UCF_SRLOPBUYOFF_VCH2");    
						String ucfVch3 = (String) map.get("UCF_SRLOPBUYOFF_VCH3");    
						String ucfVch4 = (String) map.get("UCF_SRLOPBUYOFF_VCH4");    
						String ucfVch5 = (String) map.get("UCF_SRLOPBUYOFF_VCH5");  
						String ucfVch6 = (String) map.get("UCF_SRLOPBUYOFF_VCH6");    
						String ucfVch7 = (String) map.get("UCF_SRLOPBUYOFF_VCH7");    
						String ucfVch8 = (String) map.get("UCF_SRLOPBUYOFF_VCH8");    
						String ucfVch9 = (String) map.get("UCF_SRLOPBUYOFF_VCH9");  
						String ucfVch10 = (String) map.get("UCF_SRLOPBUYOFF_VCH10");   
						String ucfVch11 = (String) map.get("UCF_SRLOPBUYOFF_VCH11");   
						//	String ucfVch12 = (String) map.get("UCF_SRLOPBUYOFF_VCH12");   
						String ucfVch13 = (String) map.get("UCF_SRLOPBUYOFF_VCH13"); 
						String ucfVch14 = (String) map.get("UCF_SRLOPBUYOFF_VCH14");   
						String ucfVch15 = (String) map.get("UCF_SRLOPBUYOFF_VCH15");   
						String ucfVch255_1 = (String) map.get("UCF_SRLOPBUYOFF_VCH255_1");   
						String ucfVch255_2 = (String) map.get("UCF_SRLOPBUYOFF_VCH255_2"); 
						String ucfVch255_3 = (String) map.get("UCF_SRLOPBUYOFF_VCH255_3");   
						//    					String ucfVch4000_1 = (String) map.get("UCF_SRLOPBUYOFF_VCH4000_1");   
						String ucfVch4000_2 = (String) map.get("UCF_SRLOPBUYOFF_VCH4000_2"); 

						// Set UCF to null.  Otherwise, duplicate records will appear in Display button side window 
						map.put("UCF_SRLOPBUYOFF_VCH4000_1",null);

						StringTokenizer tokens = new StringTokenizer(signoffs, ",");

						while (tokens.hasMoreElements()) {

							String token = tokens.nextElement().toString();
							String signoffUserid = token.substring(0, token.indexOf("-"));
							String signoffDateStr = token.substring(token.indexOf("-")+1);
							SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

							try	{
								signoffDate = df.parse(signoffDateStr);
							}catch (ParseException pe){
								throw new SoluminaException(pe.getMessage());
							}

							String signoffName = StringUtils.defaultIfEmpty(commonDaoPW.getUserFullName(signoffUserid), signoffUserid);
							if (signoffName == null || signoffName.isEmpty()){
								signoffName = signoffUserid;
							}

							Map retMap = new HashMap();
							retMap.put("UPDT_USERID", signoffUserid);
							retMap.put("TIME_STAMP", signoffDate);
							retMap.put("USERNAME", signoffName);
							retMap.put("ORDER_ID",buyoffStatus);
							retMap.put("BUYOFF_ID",buyoffStatus);
							retMap.put("BUYOFF_STATUS",buyoffStatus);
							retMap.put("COMMENTS",comments);
							retMap.put("PERCENT_QTY_COMPLETE",percentQtyComplete);
							retMap.put("UCF_SRLOPBUYOFF_DATE1",ucfDate1);
							retMap.put("UCF_SRLOPBUYOFF_DATE2",ucfDate2);
							retMap.put("UCF_SRLOPBUYOFF_DATE3",ucfDate3);
							retMap.put("UCF_SRLOPBUYOFF_DATE4",ucfDate4);
							retMap.put("UCF_SRLOPBUYOFF_DATE5",ucfDate5);
							retMap.put("UCF_SRLOPBUYOFF_FLAG1",ucfFlag1);
							retMap.put("UCF_SRLOPBUYOFF_FLAG2",ucfFlag2);
							retMap.put("UCF_SRLOPBUYOFF_FLAG3",ucfFlag3);
							retMap.put("UCF_SRLOPBUYOFF_FLAG4",ucfFlag4);
							retMap.put("UCF_SRLOPBUYOFF_FLAG5",ucfFlag5);
							retMap.put("UCF_SRLOPBUYOFF_NUM1",ucfNum1);
							retMap.put("UCF_SRLOPBUYOFF_NUM2",ucfNum2);
							retMap.put("UCF_SRLOPBUYOFF_NUM3",ucfNum3);
							retMap.put("UCF_SRLOPBUYOFF_NUM4",ucfNum4);
							retMap.put("UCF_SRLOPBUYOFF_NUM5",ucfNum5);
							retMap.put("UCF_SRLOPBUYOFF_VCH1",ucfVch1);
							retMap.put("UCF_SRLOPBUYOFF_VCH2",ucfVch2);
							retMap.put("UCF_SRLOPBUYOFF_VCH3",ucfVch3);
							retMap.put("UCF_SRLOPBUYOFF_VCH4",ucfVch4);
							retMap.put("UCF_SRLOPBUYOFF_VCH5",ucfVch5);
							retMap.put("UCF_SRLOPBUYOFF_VCH6",ucfVch6);
							retMap.put("UCF_SRLOPBUYOFF_VCH7",ucfVch7);
							retMap.put("UCF_SRLOPBUYOFF_VCH8",ucfVch8);
							retMap.put("UCF_SRLOPBUYOFF_VCH9",ucfVch9);
							retMap.put("UCF_SRLOPBUYOFF_VCH10",ucfVch10);
							retMap.put("UCF_SRLOPBUYOFF_VCH11",ucfVch11);
							// Issue with History trigger writes VCH2 to VCH12.  Reported to iBaset.
							retMap.put("UCF_SRLOPBUYOFF_VCH12",null);
							retMap.put("UCF_SRLOPBUYOFF_VCH13",ucfVch13);
							retMap.put("UCF_SRLOPBUYOFF_VCH14",ucfVch14);
							retMap.put("UCF_SRLOPBUYOFF_VCH15",ucfVch15);
							retMap.put("UCF_SRLOPBUYOFF_VCH255_1",ucfVch255_1);
							retMap.put("UCF_SRLOPBUYOFF_VCH255_2",ucfVch255_2);
							retMap.put("UCF_SRLOPBUYOFF_VCH255_3",ucfVch255_3);
							// Set UCF to null.  Otherwise, duplicate records will appear in Display button side window
							retMap.put("UCF_SRLOPBUYOFF_VCH4000_1",null);
							retMap.put("UCF_SRLOPBUYOFF_VCH4000_2",ucfVch4000_2);

							newList.add(retMap);
						}
					}
					else
					{
						// Set UCF to null.  Otherwise, duplicate records will appear in Display button side window 
						map.put("UCF_SRLOPBUYOFF_VCH4000_1",null);
					}
				}
				else
				{
					// Issue with History trigger writes VCH2 to VCH12.  Reported to iBaset.
					map.put("UCF_SRLOPBUYOFF_VCH12",null);			
					// Set UCF to null.  Otherwise, duplicate records will appear in Display button side window 
					map.put("UCF_SRLOPBUYOFF_VCH4000_1",null);
					newList.add(map);
				}
			}  

			if(addSigsExist)
			{

				// set removes duplicate records
				Set set = new HashSet(newList);

				// create list from set and sort by time stamp descending
				List retList = new ArrayList(set);
				Collections.sort(retList, new Comparator<Map>(){
					public int compare (Map m1, Map m2){
						return -((Date) m1.get("TIME_STAMP")).compareTo((Date) m2.get("TIME_STAMP"));
					}
				});

				return retList;
			}
		}
		else
		{
			// Defect 963
			list = new ArrayList();
			Map retMap = new HashMap();
			retMap.put("UPDT_USERID", null);
			retMap.put("TIME_STAMP", null);
			retMap.put("USERNAME", null);
			retMap.put("ORDER_ID",null);
			retMap.put("BUYOFF_ID",null);
			retMap.put("BUYOFF_STATUS",null);
			retMap.put("COMMENTS",null);
			retMap.put("PERCENT_QTY_COMPLETE",null);
			retMap.put("UCF_SRLOPBUYOFF_DATE1",null);
			retMap.put("UCF_SRLOPBUYOFF_DATE2",null);
			retMap.put("UCF_SRLOPBUYOFF_DATE3",null);
			retMap.put("UCF_SRLOPBUYOFF_DATE4",null);
			retMap.put("UCF_SRLOPBUYOFF_DATE5",null);
			retMap.put("UCF_SRLOPBUYOFF_FLAG1",null);
			retMap.put("UCF_SRLOPBUYOFF_FLAG2",null);
			retMap.put("UCF_SRLOPBUYOFF_FLAG3",null);
			retMap.put("UCF_SRLOPBUYOFF_FLAG4",null);
			retMap.put("UCF_SRLOPBUYOFF_FLAG5",null);
			retMap.put("UCF_SRLOPBUYOFF_NUM1",null);
			retMap.put("UCF_SRLOPBUYOFF_NUM2",null);
			retMap.put("UCF_SRLOPBUYOFF_NUM3",null);
			retMap.put("UCF_SRLOPBUYOFF_NUM4",null);
			retMap.put("UCF_SRLOPBUYOFF_NUM5",null);
			retMap.put("UCF_SRLOPBUYOFF_VCH1",null);
			retMap.put("UCF_SRLOPBUYOFF_VCH2",null);
			retMap.put("UCF_SRLOPBUYOFF_VCH3",null);
			retMap.put("UCF_SRLOPBUYOFF_VCH4",null);
			retMap.put("UCF_SRLOPBUYOFF_VCH5",null);
			retMap.put("UCF_SRLOPBUYOFF_VCH6",null);
			retMap.put("UCF_SRLOPBUYOFF_VCH7",null);
			retMap.put("UCF_SRLOPBUYOFF_VCH8",null);
			retMap.put("UCF_SRLOPBUYOFF_VCH9",null);
			retMap.put("UCF_SRLOPBUYOFF_VCH10",null);
			retMap.put("UCF_SRLOPBUYOFF_VCH11",null);
			retMap.put("UCF_SRLOPBUYOFF_VCH12",null);
			retMap.put("UCF_SRLOPBUYOFF_VCH13",null);
			retMap.put("UCF_SRLOPBUYOFF_VCH14",null);
			retMap.put("UCF_SRLOPBUYOFF_VCH15",null);
			retMap.put("UCF_SRLOPBUYOFF_VCH255_1",null);
			retMap.put("UCF_SRLOPBUYOFF_VCH255_2",null);
			retMap.put("UCF_SRLOPBUYOFF_VCH255_3",null);
			retMap.put("UCF_SRLOPBUYOFF_VCH4000_1",null);
			retMap.put("UCF_SRLOPBUYOFF_VCH4000_2",null);
			list.add(retMap);
		}
		return list;
	}
}
