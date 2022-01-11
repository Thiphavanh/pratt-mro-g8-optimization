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
 *  File:    BuyoffDaoPW.java
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
 *  2018-01-25      N.Gnassounou    Defect 344 - Missing Task Group Data for SUB ORDER
 *  2018-03-12		R. Thorpe       Defect 347 - changed parameter from parentOrderId to orderId
 *  2018-03-15      N.Gnassounou    SMRO_RPT_201- Initial Release- Delivery Package
 *  2018-03-16      R. Thorpe		Defect 416 - added isworkInstructionsRead, selectPlanInfoFromOrder, isLastBuyoffInOper methods
 *  2018-03-22		S.Niu			Defect 433 - Second Supp Order on an Order brings you into Infinite Loop
 *  2018-03-28      D. Miron        SMRO_WOE_202 Additional updates - Methods changed/added: selectAdditionalSignoffs, 
 *                                  selectUsersCertsForWorkLoc, selectCertsforWorkCenter, updateAdditionalSignoffs
 *  2018-04-10		Bob Preston	    SMRO_WOE_204, Defect 457 -- Incorporated changes for 80 char order no.
 *  2018-04-17		Bob Preston		SMRO_WOE_204, Defect 705 -- Name supp. orders with correct order no.
 *  2018-04-18      D.Miron         SMRO_WOE_202 Defect 695 - Added method setNaNdStatusNull.
 *  2018-04-26      D.Miron         SMRO_WOE_202 Defect 694 - Added method selectSerialBuyoffList.
 *  2018-04-30      R. Thorpe	    SMRO_WOE_202 Defect 727 - Added param to isworkInstructionsRead, added new method isFinalBuyoffBlockInOper
 *  2018-05-01		R. Thorpe		SMRO_WOE_202 Defect 727 - changed is isworkInstructionsRead if (count > 0)  from  (count == 1)  
 *  2018-05-03      R. Thorpe       SMRO_WOE_202 Defect 727 - Added orderId as a parameter in call to isworkInstructionsRead/ set count == 0 for check
 *  2018-05-04      D.Miron         SMRO_WOE_202 Defect 750 - Added method updateSerialBuyoffUserid
 *  2018-05-07		R. Thorpe       SMRO_WOE_202 Defect 727 - Added method isSupplementalWorkInstructionsRead
 *  2018-05-14      D.Miron         SMRO_WOE_202 Defect 694 - Added method selectSerialBuyoffListHistTab, clear additional signatures on buyoff reopen, 
 *                                  removed updateSerialBuyoffUserid.
 *  2018-05-16      R. Thorpe       SMRO_WOE_202 Defect 792 - Removed Plan revision as param in isworkInstructionsRead  
 *  2018-06-06      R. Thorpe       SMRO_WOE_202 Defect 712/815 - Changes for 'P'ARTIAL buyoff                             
 *  2018-06-21      N.Gnassounou    SMRO_RPT_201 Defect 849 - Update selectAdditionalSignoffs Method to exclude Buyoffid to display Additional Signoffs 
 *  2018-06-22      D.Miron         SMRO_WOE_202 Defect 694 - Changes made to remove duplicate records from buyoff side window.  
 *  2018-07-02      R. Thorpe       SMRO_RPT_201 Defect 856 - Add an 'order by' on selectAdditionalSignoffs
 *  2018-07-20      R. Thorpe       SMRO_RPT_201 Defect 849 - Add method selectAddtionalSignoffs4DPReport
 *  2018-07-24      D.Miron         SMRO_WOE_202 Defect 884 - Added methods insertBadgeSwipeLog and updateBadgeSwipeLog. 
 *  2018-07-24		R. Cannon		SMRO_RPT_201 Defect 849 - Changed order by in selectAddtionalSignoffs4DPReport to be like Buyoff tab
 *  2018-07-31      D. Miron        SMRO_WOE_202 Defect 732 - Added method insertBuyoffAcceptForTrainee
 *  2018-08-01		R. Cannon		SMRO_RPT_201 Defect 908 - Changes to selectSerialBuyoffListHistTab
 *  2018-08-07		R. Cannon		SMRO_RPT_201 Defect 930 - Change order by in selectSerialBuyoffListHistTab
 *  2018-08-06      R. Thorpe       SMRO_WOE_204 Defect 879 - added method selectBuyoffTabList
 *  2018-08-07      D.Miron         SMRO_WOE_202 Defect 884 - Added Client Asset Tag and Error Text to Badge Swipe log table.
 *  2018-08-10      R. Thorpe       SMRO_WOE_202 Defect 938 - Added method updateOrderNotesTimestamp
 *  2018-08-15      R. Thorpe       SMRO_WOE_202 Defect 949 - Remove step_key as a param to isSupplementalWorkInstructionsRead
 *  2018-08-21		R. Cannon		SMRO_RPT_201 Defect 929 - Change order by in selectSerialBuyoffListHistTab
 *  2018-10-17      D.Miron         SMRO_WOE_202 Defect 973 - Added oper_key,step_key to methods updateSerialBuyoffAccept, selectOrderNoteId, updateOrderNotesTimestamp
 *  2018-11-07      D.Miron         SMRO_WOE_202 Defect 860 - Revised selectNANDBuyoffStatus to return last buyoff staturs not pending or reopen.
 *  2018-11-13      D.Miron         SMRO_WOE_202 Defect 860 - Added methods getNANDFlag and isNANDBuyoff. 
 *  2018-12-07      D.Miron         SMRO_WOE_202 Defect 947 - Added methods clearBuyoffUCFs and deleteReopenHistory.
 *  2018-12-11      B. Preston      Defect 1024 - Added updateSerialBuyoffTimeStamp. 
 *  2018-12-12      D.Miron         SMRO_WOE_202 Defect 860 - Added selectBuyoffStatus.
 *  2019-05-10      R. Thorpe       SMRO_WOE_302 (Defect 1258)- Added method getSerialId, added serialId as a param to clearBuyoffUCFs and deleteReopenHistory
 *                                                            - Added serialId to updateAdditionalSignoffs, Added new method selectAdditionalSignoffs with serialID as a param 
 * 2019-06-07       R. Thorpe		SMRO_WOE_302 Defect 1033  - modified selectSerialBuyoffList
 * 2020-02-24       B. Corson       Defect 1530 - Sometimes complete qty is text and sometimes numeric which causes error, so treat complete qty as text.
 * 2002-04-09       Fred Ettefagh   SMRO_WOE_302 - Defect 1480,1370, renamed method selectCurrentDatColStatus to selectPendingDatColStatus. Changed sql to select units that are pending and not stopped
 * 2020-06-19       Fred Ettefagh   Defect 1683 - added methods selectSfwidOrderDesc, selectSerialNumberInfo, changed methods updateOrderNotesTimestamp,selectBuyoffTabList,isFinalBuyoffInOper,updateSerialBuyoffAccept, updateOrderOperOptFlag, selectSerialNumberInfo
 * 2020-06-24       Fred Ettefagh   Defect 1534 - added methods  selectSfwidHolds
 * 2020-06-30       Fred Ettefagh   Defect 1683 - changed method isNANDBuyoff, to get query for List not Map to fix  "Incorrect result size expected 1 actual 2" error
 * 2020-07-07       Fred Ettefagh   Defect 1722 - added new method SelectAllBuyoffSerialNoToCloseOper
 * 2020-07-20       D. Miron        Defect 1708 - Added check for Oper Stop in SelectAllBuyoffSerialNoToCloseOper
 * 2020-08-05       Fred Ettefagh   Defect 1734 - Changed buyoff NA/ND code to call oob method 
 * 2020-08-10       Fred Ettefagh   Defect 1734 - added new method updateSfwidSerialOperBuyoffUcfSrlopbuyoffVch1 
 * 2020-08-14       Fred Ettefagh   Defect 1748 - added/changed methods updateSfwidOperBuyoffUcfOperVch1, selectBuyoffInfoFromOperBuyoff, updatePartialBuyoffPW, clearBuyoffUCFs
 * 2020_08-18       Fred Ettefagh   Defect 1734 and 1748 - changed code to get save/get buyoff timestamp and userId from sfwid_oper_buyoff ucf columns.
 * 2020-08-23       Fred Ettefagh   Defect 1753 - Added code to implement PW rules on accepting buyoffs (N/A, N/D, Partial and Accept) 
 * 2020-08-24       Fred Ettefagh   Defect 1755 - Fixed buyoff hist tab issue with Closed-PARTIAL, fixed typo in insertBuyoffAcceptForTrainee method
 * 2020-08-25       Fred Ettefagh   Defect 1755 - Fixed userid and username columns in buyoff hist dao method selectSerialBuyoffListHistTab 
 * 2020-09-10       D.Miron         Defect 1761 - Added methods checkNANDBuyoff and updateSerialSkipDatColToPending.
 * 2020-09-11       Fred Ettefagh   Defect 1774 - Fixed selectBuyoffTabList(String orderId, String orderType, String userid) by adding AND A.BUYOFF_ID = B.BUYOFF_ID to where clause 
 * 2020-10-02       John DeNinno,Fred    Defect 1772 - added code to insert into new custom PW buyoff hist table 
 * 2020-10-09       Fred Ettefagh   Defect 1792 Added method selectSerialBuyoffListHistReport
 * 2020-10-12       Fred Ettefagh   Defect 1791,1792  Changed method insertBuyoffHistory and selectSerialBuyoffListHistTab
 * 2020-10-13       Fred Ettefagh   Defect 1791,1792  fixed typo in method insertBuyoffHistory
 * 2020-10-23       Fred Ettefagh   Defect 1796, added methods updateSfwidSerialOperBuyoffVch5Date1, updateSfwidSerialOperBuyoffUcfSrlopbuyoffVch1 and changed method selectBuyoffTabList
 * 2020-11-17/2020-12-01 John DeNinno    Defect 1823 changed view since operations were missing
 * 2021-06-25       John DeNinno    Defect 1908 Add code to check if serial number exists with the order id
 * 2021-07-28       John DeNinno    Defect 1946 serial numbers not apearing in history
 * 2021-8-19        Fred Ettefagh   Defect 1965 Buyoff Performance, added new method getUserPriv(String clockNoList, String UserPrivilege)
 * 2021-09-30       D.Miron         Defect 1965 Commented System.out.println's
 * 2021-09-28       John DeNinno    Defect 1994 - Change how display tab on buyoff selects the history
 */      
package com.pw.solumina.sfwid.dao;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;
import com.pw.common.dao.CommonDaoPW;

public class BuyoffDaoPW{

	@Reference private ExtensionDaoSupport eds;
	@Reference private CommonDaoPW commonDaoPW;


	
	public String getUserPriv(String clockNoList, String UserPrivilege){
		
	
		StringBuffer selectSql = new StringBuffer().append("SELECT SFCORE_HAS_PRIV( ? ,? ) AS PRIV_CHECK FROM DUAL " );
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(clockNoList);
		parameters.addParameter(UserPrivilege);
		
		String returnVal = eds.queryForString(selectSql.toString(),parameters);
		return returnVal;
	}

	public int updateSfwidSerialOperBuyoffVch5Date1(String orderId,
			Number operKey, 
			Number stepKey,
			String buyoffId,
			String serialId,
			String lotId,
			String vch5){

		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFWID_SERIAL_OPER_BUYOFF T")
				.append(" SET T.UCF_SRLOPBUYOFF_DATE1 = " + eds.getTimestampFunction() + " , " )
				.append("     T.UCF_SRLOPBUYOFF_VCH5 =  ? "  )
				.append("  WHERE ORDER_ID = ? ")
				.append("  AND OPER_KEY = ? ")
				.append("  AND STEP_KEY = ? ")
				.append("  AND BUYOFF_ID = ? ")
				.append("  AND SERIAL_ID = ? ")
				.append("  AND LOT_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(vch5);
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(buyoffId);
		parameters.addParameter(serialId);
		parameters.addParameter(lotId);

		return eds.update(updateSql.toString(), parameters);

	}

	public int clearSfwidSerialOperBuyoffVch5Date1(String orderId,
			Number operKey, 
			Number stepKey,
			String buyoffId,
			String serialId,
			String lotId){

		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFWID_SERIAL_OPER_BUYOFF T")
				.append(" SET T.UCF_SRLOPBUYOFF_DATE1 = null, "  )
				.append("     T.UCF_SRLOPBUYOFF_VCH5 =  null "  )
				.append(" WHERE ORDER_ID = ? ")
				.append(" AND OPER_KEY = ? ")
				.append(" AND STEP_KEY = ? ")
				.append(" AND BUYOFF_ID = ? ")
				.append(" AND SERIAL_ID = ? ")
				.append(" AND LOT_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(buyoffId);
		parameters.addParameter(serialId);
		parameters.addParameter(lotId);

		return eds.update(updateSql.toString(), parameters);

	}





	public List selectSfwidSerialOperBuyoffAcceptedViaOOBAccept( String orderId, 
			Number operKey,
			Number stepKey,
			String refID,
			String serialNo,
			String tagTypeNA,
			String tagTypeND){

		StringBuffer selectSql = new StringBuffer().append( " SELECT T2.SERIAL_NO, T.* " +
				" FROM SFWID_SERIAL_OPER_BUYOFF T, SFWID_OPER_BUYOFF T1, SFWID_SERIAL_DESC T2 "+ 
				" WHERE T.ORDER_ID =  T1.ORDER_ID " +
				" AND   T.OPER_KEY =  T1.OPER_KEY " +
				" AND   T.STEP_KEY =  T1.STEP_KEY " +
				" AND   T.BUYOFF_ID = T1.BUYOFF_ID " +
				" AND   T.ORDER_ID =  T2.ORDER_ID " +
				" AND   T.LOT_ID =    T2.LOT_ID " +
				" AND   T.SERIAL_ID = T2.SERIAL_ID " +
				" AND   T.ORDER_ID =? " +
				" AND   T.OPER_KEY= ? " +
				" AND   T.STEP_KEY= ? " +
				" AND   T1.REF_ID = ? " +
				" AND   T.BUYOFF_STATUS = ? " +
				" AND   T2.SERIAL_NO = ? " +
				" AND NVL(T.UCF_SRLOPBUYOFF_VCH1,'NULL') NOT IN ( ? , ? )");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(operKey);
		params.addParameter(stepKey);
		params.addParameter(refID);
		params.addParameter("ACCEPT");
		params.addParameter(serialNo);
		params.addParameter(tagTypeNA);
		params.addParameter(tagTypeND);

		List  list =eds.queryForList(selectSql.toString(),params);
		return list;
	}


	public List selectSfwidSerialOperBuyoffAccpetedviaTagType( String orderId, 
			Number operKey,
			Number stepKey,
			String refID,
			String serialNo,
			String tagType){

		StringBuffer selectSql = new StringBuffer().append( " SELECT T2.SERIAL_NO , T.* " +
				" FROM SFWID_SERIAL_OPER_BUYOFF T, SFWID_OPER_BUYOFF T1, SFWID_SERIAL_DESC T2 "+ 
				" WHERE T.ORDER_ID =  T1.ORDER_ID " +
				" AND   T.OPER_KEY =  T1.OPER_KEY " +
				" AND   T.STEP_KEY =  T1.STEP_KEY " +
				" AND   T.BUYOFF_ID = T1.BUYOFF_ID " +
				" AND   T.ORDER_ID =  T2.ORDER_ID " +
				" AND   T.LOT_ID =    T2.LOT_ID " +
				" AND   T.SERIAL_ID = T2.SERIAL_ID " +
				" AND   T.ORDER_ID =? " +
				" AND   T.OPER_KEY= ? " +
				" AND   T.STEP_KEY= ? " +
				" AND   T1.REF_ID = ? " +
				" AND   T.BUYOFF_STATUS = ? " +
				" AND   T2.SERIAL_NO = ? " +
				" AND NVL(T.UCF_SRLOPBUYOFF_VCH1,'NULL') =  ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(operKey);
		params.addParameter(stepKey);
		params.addParameter(refID);
		params.addParameter("ACCEPT");
		params.addParameter(serialNo);
		params.addParameter(tagType);
		List  list =eds.queryForList(selectSql.toString(),params);
		return list;
	}

	public List selectSfwidSerialOperBuyoffAccpetedviaNAandND(  String orderId, 
			Number operKey,
			Number stepKey,
			String refID,
			String serialNo,
			String tagTypeNA,
			String tagTypeND){

		StringBuffer selectSql = new StringBuffer().append( " SELECT T2.SERIAL_NO , T.* " +
				" FROM SFWID_SERIAL_OPER_BUYOFF T, SFWID_OPER_BUYOFF T1, SFWID_SERIAL_DESC T2 "+ 
				" WHERE T.ORDER_ID =  T1.ORDER_ID " +
				" AND   T.OPER_KEY =  T1.OPER_KEY " +
				" AND   T.STEP_KEY =  T1.STEP_KEY " +
				" AND   T.BUYOFF_ID = T1.BUYOFF_ID " +
				" AND   T.ORDER_ID =  T2.ORDER_ID " +
				" AND   T.LOT_ID =    T2.LOT_ID " +
				" AND   T.SERIAL_ID = T2.SERIAL_ID " +
				" AND   T.ORDER_ID =? " +
				" AND   T.OPER_KEY= ? " +
				" AND   T.STEP_KEY= ? " +
				" AND   T1.REF_ID = ? " +
				" AND   T.BUYOFF_STATUS = ? " +
				" AND   T2.SERIAL_NO = ? " +
				" AND NVL(T.UCF_SRLOPBUYOFF_VCH1,'NULL') in( ?,  ?) ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(operKey);
		params.addParameter(stepKey);
		params.addParameter(refID);
		params.addParameter("ACCEPT");
		params.addParameter(serialNo);
		params.addParameter(tagTypeNA);
		params.addParameter(tagTypeND);

		List  list =eds.queryForList(selectSql.toString(),params);
		return list;
	}



	public String selectSfwidBuyoffPercnetGet( String orderId, 
			Number operKey,
			Number stepKey,
			String buyoffId,
			Number orderQty){


		StringBuffer selectSql = new StringBuffer().append( " SELECT SFWID_BUYOFF_PERCENT_GET(? , " +   //ORDER_ID
				" ? , " +   //OPER_KEY 
				" ? , " +   //STEP_KEY
				" ? , "  +  // BUYOFF_ID
				"( ?  - (SELECT COUNT(1) " + // ORDER_QTY 
				"        FROM SFWID_SERIAL_OPER D " + 
				"        WHERE D.ORDER_ID = ? " +
				"        AND D.OPER_KEY = ? " + 
				"        AND D.STEP_KEY = ? " + 
				"        AND D.SERIAL_OPER_STATUS NOT IN (? ,? ,?)))) AS PERCENT_QTY_COMPLETE  FROM DUAL " );
		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(operKey);
		params.addParameter(stepKey);
		params.addParameter(buyoffId);
		params.addParameter(orderQty);
		params.addParameter(orderId);
		params.addParameter(operKey);
		params.addParameter(stepKey);
		params.addParameter("IN QUEUE");
		params.addParameter("ACTIVE");
		params.addParameter("COMPLETE");

		String retrunVal  = eds.queryForString(selectSql.toString(), params);
		return retrunVal;

	}

	public List SelectSfwidOperDatColOptionalFlag( String orderId, Number operKey){

		StringBuffer selectSql = new StringBuffer().append( " SELECT T.* " +
				" FROM SFWID_OPER_DAT_COL T "+
				" WHERE T.ORDER_ID = ? "+
				" AND T.OPER_KEY = ? "+
				" AND T.OPTIONAL_FLAG = ? " );

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(operKey);
		params.addParameter("N");

		List list  = eds.queryForList(selectSql.toString(), params);
		return list;

	}


	public int updateSfwidOperDatColOptionalFlag( String orderId,
			Number operKey, 
			Number stepKey,
			String datColId,
			String optinalFlag){

		StringBuffer updateSql = new StringBuffer().append( " UPDATE SFWID_OPER_DAT_COL T " +
				" SET T.OPTIONAL_FLAG = ? " +
				" WHERE T.ORDER_ID = ? " +
				" AND T.OPER_KEY = ? " +
				" AND T.STEP_KEY =  ? " +
				" AND T.DAT_COL_ID = ? " );

		ParameterHolder params = new ParameterHolder();
		params.addParameter(optinalFlag);
		params.addParameter(orderId);
		params.addParameter(operKey);
		params.addParameter(stepKey);
		params.addParameter(datColId);

		int numRows =eds.update(updateSql.toString(), params);
		return numRows;
	}

	public int updateSfwidOperBuyoffUcfOperVch1AndVch2( String orderId,
			Number operKey, 
			Number stepKey,
			String buyoffId,
			String userId,
			String percentCompleteString ){

		StringBuffer updateSql = new StringBuffer().append( " UPDATE SFWID_OPER_BUYOFF T SET T.UCF_OPER_BUYOFF_DATE1 = " + eds.getTimestampFunction()) ;

		if(StringUtils.isNotBlank(userId)){
			if (StringUtils.equalsIgnoreCase(userId, "null")){
				updateSql.append( " ,T.UCF_OPER_BUYOFF_VCH1 =  null " );
			}else{
				updateSql.append( " ,T.UCF_OPER_BUYOFF_VCH1 =  ? " );
			}
		}
		if(StringUtils.isNotBlank(percentCompleteString)){

			if (StringUtils.equalsIgnoreCase(percentCompleteString, "null")){
				updateSql.append( " ,T.UCF_OPER_BUYOFF_VCH3 =  null " );
			}else{
				updateSql.append( " ,T.UCF_OPER_BUYOFF_VCH3 =  ? " );
			}
		}
		updateSql.append(" WHERE T.ORDER_ID = ? " +
				" AND T.OPER_KEY = ? " +
				" AND T.STEP_KEY = ? " +
				" AND T.BUYOFF_ID = ? " );

		ParameterHolder params = new ParameterHolder();
		if(StringUtils.isNotBlank(userId)){
			if (!StringUtils.equalsIgnoreCase(userId, "null")){
				params.addParameter(userId);
			}
		}

		if(StringUtils.isNotBlank(percentCompleteString)){
			if (!StringUtils.equalsIgnoreCase(percentCompleteString, "null")){
				params.addParameter(percentCompleteString);
			}
		}
		params.addParameter(orderId);
		params.addParameter(operKey);
		params.addParameter(stepKey);
		params.addParameter(buyoffId);
		int numRows =eds.update(updateSql.toString(), params);
		//System.out.println("BuyoffDaoPW.updateSfwidOperBuyoffVch updateSql = " + updateSql);
		//System.out.println("BuyoffDaoPW.updateSfwidOperBuyoffVch params = " + params);
		//System.out.println("BuyoffDaoPW.updateSfwidOperBuyoffVch numRows = " + numRows);
		return numRows;

	}



	public String SelectAllBuyoffSerialNoToCloseOper(String orderId,
			Number operKey, 
			Number stepKey){

		ParameterHolder params = new ParameterHolder();

		StringBuilder selectSql = new StringBuilder().append(" SELECT T1.SERIAL_NO " +
				" FROM SFWID_SERIAL_OPER T, SFWID_SERIAL_DESC T1 " +
				" WHERE T.ORDER_ID = T1.ORDER_ID " +
				" AND T.LOT_ID = T1.LOT_ID " +
				" AND T.SERIAL_ID = T1.SERIAL_ID " +
				" AND T.ORDER_ID = ? " +
				" AND T.OPER_KEY= ?   " +
				" AND T.STEP_KEY= ?   " +
				" AND T.SERIAL_OPER_STATUS IN ( ? , ?) " +
				" AND NVL(T1.SERIAL_HOLD_STATUS,'X') <> 'OPER STOP'");  // Defect 1708

		params.addParameter(orderId);
		params.addParameter(operKey);
		params.addParameter(stepKey);
		params.addParameter("IN QUEUE");
		params.addParameter("ACTIVE");

		//System.out.println("BuyoffDaoPW.SelectAllBuyoffSerialNoToCloseOper selectSql = " + selectSql);
		//System.out.println("BuyoffDaoPW.SelectAllBuyoffSerialNoToCloseOper parameters = " + params);

		List list = eds.queryForList(selectSql.toString(), params);

		//System.out.println("BuyoffDaoPW.SelectAllBuyoffSerialNoToCloseOper list = " + list);
		//System.out.println("BuyoffDaoPW.SelectAllBuyoffSerialNoToCloseOper list.size() = " + list.size());

		ListIterator listIterator = list.listIterator();
		String serialNoString = "";
		while (listIterator.hasNext()){
			Map map = (Map) listIterator.next();
			serialNoString = serialNoString + (String) map.get("SERIAL_NO") + ";";
		}
		return serialNoString;
	}




	public Map selectSffndWidOperText ( String refId,
			String orderId,
			Number operKey, 
			Number stepKey){

		ParameterHolder params = new ParameterHolder();
		StringBuffer select = new StringBuffer().append(" SELECT TT.* ")
				.append(" FROM SFFND_HTREF_WID_OPER_TEXT  TT ") //   primary key (REF_ID, ORDER_ID, OPER_KEY, STEP_KEY, TEXT_TYPE)
				.append(" WHERE TT.REF_ID  = ? ")
				.append(" AND TT.ORDER_ID  = ? ")
				.append(" AND TT.OPER_KEY  = ? ")
				.append(" AND TT.STEP_KEY  = ? ");


		params.addParameter(refId);
		params.addParameter(orderId);
		params.addParameter(operKey);
		params.addParameter(stepKey);

		//System.out.println("BuyoffDaoPW.selectSffndWidOperText selectSql = " + select);
		//System.out.println("BuyoffDaoPW.selectSffndWidOperText parameters = " + params);


		Map map = eds.queryForMap(select.toString(), params);
		return map;
	}


	public List selectSfwidSerialOperCtrlBuyoff(String orderId,
			Number operKey, 
			Number stepKey,
			String lotId,
			Number seqNo,
			String textType){


		ParameterHolder params = new ParameterHolder();
		StringBuffer select = new StringBuffer().append(" SELECT  A.* " ) 
				.append(" FROM SFWID_SERIAL_OPER_CTRL_BUYOFF A, ")
				.append(" SFWID_ORDER_EMBEDDED_CONTROLS B ") 
				.append(" WHERE A.ORDER_ID  = ? ") 
				.append(" AND   A.OPER_KEY = ? ")
				.append(" AND   A.STEP_KEY  = ? ")  
				.append(" AND   A.SERIAL_ID IN ( SELECT AA.SERIAL_ID FROM SFWID_SERIAL_DESC AA WHERE AA.ORDER_ID = ? AND AA.SERIAL_STATUS NOT IN ( ?, ? )))")
				.append(" AND   A.LOT_ID = ? " )  
				.append(" AND   B.CONTROL_SEQ_NO > ? ")   
				.append(" AND   A.ORDER_ID = B.ORDER_ID " )  
				.append(" AND   A.OPER_KEY = B.OPER_KEY " )  
				.append(" AND   A.STEP_KEY = B.STEP_KEY " )  
				.append(" AND   A.CONTROL_ID = B.REF_ID " )  
				.append(" AND   A.TAG_TYPE = B.TAG_TYPE " )  
				.append(" AND   B.TEXT_TYPE = ? ") 
				.append(" AND   A.TAG_TYPE = ? ")
				.append(" AND   A.RELATED_OBJECT_STATUS != ? " );
		params.addParameter(orderId);
		params.addParameter(operKey);
		params.addParameter(stepKey);
		params.addParameter(orderId);
		params.addParameter(lotId);
		params.addParameter(seqNo);
		params.addParameter(textType);
		params.addParameter("ACCEPT");

		//System.out.println("BuyoffDaoPW.selectSffndWidOperText selectSql = " + select);
		//System.out.println("BuyoffDaoPW.selectSffndWidOperText parameters = " + params);

		List list = eds.queryForList(select.toString(), params);
		return list;
	}


	@SuppressWarnings("rawtypes")
	public List selectSfwidHolds(String orderId, String holdStatus){

		ParameterHolder params = new ParameterHolder();
		StringBuffer select = new StringBuffer().append(" SELECT T.* " +
				" FROM SFWID_HOLDS T " +
				" WHERE T.ORDER_ID = ? " + 
				" AND T.HOLD_STATUS = ? ");

		params.addParameter(orderId);
		params.addParameter(holdStatus);
		List list = eds.queryForList(select.toString(), params);
		return list;
	}


	@SuppressWarnings("rawtypes")
	public Map selectSfwidSerialOperBuyoff(String orderId,
			Number operKey, 
			Number stepKey,
			String buyoffId,
			String serialId,
			String lotId){

		StringBuffer selectSql = new StringBuffer().append(" SELECT T.*, ")
				.append("        TO_CHAR(CAST(PERCENT_COMPLETE AS FLOAT) / 100) AS PERCENT_COMPLETE_PW ")
				.append(" FROM SFWID_SERIAL_OPER_BUYOFF T ")
				.append(" WHERE T.ORDER_ID = ? ")
				.append(" AND T.OPER_KEY = ? ")
				.append(" AND T.STEP_KEY = ? ")
				.append(" AND T.BUYOFF_ID = ? ")
				.append(" AND T.SERIAL_ID = ? " )
				.append(" AND T.LOT_ID = ?" );



		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(buyoffId);
		parameters.addParameter(serialId);
		parameters.addParameter(lotId); 

		Map map =eds.queryForMap(selectSql.toString(), parameters);
		return map;
	}


	public Map SelectSfwidOperDesc(String orderId, Number operKey,Number stepKey){

		StringBuilder selectSql = new StringBuilder().append(" SELECT T.* ")
				.append(" FROM SFWID_OPER_DESC T")
				.append(" WHERE T.ORDER_ID = ? ")
				.append(" AND T.OPER_KEY = ? ")
				.append(" AND T.STEP_KEY = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		Map map = eds.queryForMap(selectSql.toString(), parameters);
		return map;

	}



	@SuppressWarnings("rawtypes")
	public List selectSfwidOrderDesc(String orderID){

		StringBuffer selectSql = new StringBuffer().append(" SELECT T.* FROM SFWID_ORDER_DESC T WHERE T.ORDER_ID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderID);

		List list = eds.queryForList(selectSql.toString(), params);
		return  list;
	}


	public List selectAdditionalSignoffs(String orderId,
			Number operKey, 
			Number stepKey,
			String buyoffId){

		StringBuffer selectSql = new StringBuffer().append(" SELECT DISTINCT(UCF_SRLOPBUYOFF_VCH4000_1), TIME_STAMP")/// RTT 1258 added distinct
				.append("   FROM SFWID_SERIAL_OPER_BUYOFF ")
				.append("  WHERE ORDER_ID = ? ")
				.append("    AND OPER_KEY = ? ")
				.append("    AND STEP_KEY = ? ")
				//.append("    AND BUYOFF_ID = ? ") //Defect 849
				.append("    AND UCF_SRLOPBUYOFF_VCH4000_1 IS NOT NULL ");

		//Defect 849
		if(buyoffId != null)
		{
			selectSql.append("    AND BUYOFF_ID = ? ");
		}
		//End Defect849

		selectSql.append("    ORDER BY TIME_STAMP ASC");  //defect 856

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		//parameters.addParameter(buyoffId); //Defect 849
		if(buyoffId != null) parameters.addParameter(buyoffId);  //Defect 849

		return eds.queryForList(selectSql.toString(), parameters);
	}

	///RTT new for 1258
	public List selectAdditionalSignoffs(String orderId,
			Number operKey, 
			Number stepKey,
			String buyoffId,
			String serialId,
			String lotId)   //1258
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT UCF_SRLOPBUYOFF_VCH4000_1 ")
				.append("   FROM SFWID_SERIAL_OPER_BUYOFF ")
				.append("  WHERE ORDER_ID = ? ")
				.append("    AND OPER_KEY = ? ")
				.append("    AND STEP_KEY = ? ")
				.append("    AND SERIAL_ID = ?" )
				.append("    AND LOT_ID = ?" )
				.append("    AND UCF_SRLOPBUYOFF_VCH4000_1 IS NOT NULL ");


		if(buyoffId != null)
		{
			selectSql.append("    AND BUYOFF_ID = ? ");
		}

		selectSql.append("    ORDER BY TIME_STAMP ASC");  

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(serialId);
		parameters.addParameter(lotId); 
		if(buyoffId != null) parameters.addParameter(buyoffId); 

		return eds.queryForList(selectSql.toString(), parameters);
	}

	public List selectUsersCertsForWorkLoc(String userId,
			String workLoc)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT A.CERT ")
				.append("  FROM SFFND_USER_CERTS_V A, SFFND_USER_CERTS B ")
				.append(" WHERE SFDB_NVL_VARCHAR(OBSOLETE_RECORD_FLAG, 'N') = 'N' ")
				.append("   AND A.USERID = B.USERID ")
				.append("   AND A.CERT = B.CERT ")
				.append("   AND SYSDATE BETWEEN NVL(A.EFF_DATE, SYSDATE) AND ")
				.append("       NVL(A.EXP_DATE, SYSDATE) ")
				.append("   AND B.CERT IS NOT NULL ")
				.append("   AND B.USERID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(userId);

		//System.out.println("in checkUsersWorkCenterCerts selectSql = " +selectSql );
		//System.out.println("in checkUsersWorkCenterCerts parameters = " +parameters );

		return eds.queryForList(selectSql.toString(), parameters);
	}

	public List selectCertsforWorkCenter(String locationId,
			String departmentId,
			String centerId)  
	{

		StringBuffer selectSql = new StringBuffer().append("SELECT UCF_WORK_CENTER_VCH255_1 AS CERT ")
				.append("  FROM SFFND_WORK_CENTER_DEF ")
				.append(" WHERE LOCATION_ID = ? ")
				.append("   AND DEPARTMENT_ID = ? ") 
				.append("   AND CENTER_ID = ? ")  
				.append("   AND UCF_WORK_CENTER_VCH255_1 IS NOT NULL ");  

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(locationId);
		parameters.addParameter(departmentId);
		parameters.addParameter(centerId); 

		//System.out.println("in selectCertsforWorkCenter selectSql =" +selectSql);
		//System.out.println("in selectCertsforWorkCenter parameters =" +parameters);

		List returnCerts = eds.queryForList(selectSql.toString(), parameters);

		return returnCerts;
	}

	public List selectPendingDatColStatus(String orderId,
			Number operKey, 
			Number stepKey)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT T.* ")
				.append(" FROM SFWID_SERIAL_OPER_DAT_COL T, SFWID_SERIAL_DESC T1 ")
				.append(" WHERE T.ORDER_ID = T1.ORDER_ID ")
				.append(" AND T.LOT_ID = T1.LOT_ID " )
				.append(" AND T.SERIAL_ID = T1.SERIAL_ID " )
				.append(" AND T.ORDER_ID = ? ")
				.append(" AND T.OPER_KEY = ? ")
				.append(" AND T.STEP_KEY = ? ")
				.append(" AND T.DAT_COL_STATUS = ? ")
				.append(" AND T1.SERIAL_STATUS NOT IN ( ? , ?, ? )");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		parameters.addParameter("PENDING");
		parameters.addParameter("STOP");
		parameters.addParameter("SCRAP");
		parameters.addParameter("COMPLETE");

		return eds.queryForList(selectSql.toString(), parameters);                                          
	}


	public String selectBuyoffStatus(String orderId,
			Number operKey, 
			Number stepKey,
			String buyoffId)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT NVL(BUYOFF_STATUS,'NOTSTARTED')")
				.append("   FROM SFWID_SERIAL_OPER_BUYOFF")
				.append("  WHERE ORDER_ID = ?")
				.append("    AND OPER_KEY = ?")
				.append("    AND STEP_KEY = ?")
				.append("    AND BUYOFF_ID = ?");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(buyoffId);

		return eds.queryForString(selectSql.toString(), parameters);
	}

	public List selectBuyoffInfoFromOperBuyoff(String orderId,
			Number operationKey,
			Number stepKey, 
			String refId,
			String NANDFlag){

		StringBuffer selectSql = new StringBuffer().append(" SELECT T.* ")  
				.append(" FROM SFWID_OPER_BUYOFF T ")
				.append(" WHERE T.ORDER_ID = ? ")
				.append(" AND T.OPER_KEY = ? ")
				.append(" AND T.STEP_KEY = ? ")
				.append(" AND T.REF_ID = ? " )
				.append(" AND NVL(T.UCF_OPER_BUYOFF_VCH2,'NULL') = ? " );

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operationKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(refId);
		parameters.addParameter(NANDFlag);

		//System.out.println("BuyoffDaoPW.selectBuyoffInfoFromOperBuyoff selectSql = " + selectSql);
		//System.out.println("BuyoffDaoPW.selectBuyoffInfoFromOperBuyoff parameters = " + parameters);

		return eds.queryForList(selectSql.toString(), parameters);
	}


	public List selectBuyoffInfoFromOperBuyoff(String orderId,
			Number operationKey,
			Number stepKey, 
			String buyoffId){

		StringBuffer selectSql = new StringBuffer().append(" SELECT (CAST(NVL(T.UCF_OPER_BUYOFF_NUM1,0) AS FLOAT) / 100) AS PW_PERCENT_COMPLETE, " )
				.append(" T.* ")  
				.append(" FROM SFWID_OPER_BUYOFF T ")
				.append(" WHERE T.ORDER_ID = ? ")
				.append(" AND T.OPER_KEY = ? ")
				.append(" AND T.STEP_KEY = ? ")
				.append(" AND T.BUYOFF_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operationKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(buyoffId);

		return eds.queryForList(selectSql.toString(), parameters);
	}




	public int updateOrderOperStatusChgReason(String orderId,
			Number oper_key,
			String statusChgReason)
	{
		StringBuffer updateSQL = new StringBuffer().append(" UPDATE SFWID_OPER_DESC")
				.append("    SET STATUS_CHG_REASON = ?")
				.append("  WHERE ORDER_ID = ?")
				.append("    AND OPER_KEY = ?")
				.append("    AND STEP_KEY = -1");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(statusChgReason);
		params.addParameter(orderId);
		params.addParameter(oper_key);

		return eds.update(updateSQL.toString(), params);
	}

	public int updateOrderOperOptFlag(String orderId, 
			Number operKey,
			String operOptFlag)
	{
		StringBuffer updateSQL = new StringBuffer().append("UPDATE SFWID_OPER_DESC")
				.append("   SET OPER_OPT_FLAG = ?")
				.append(" WHERE ORDER_ID = ? ")
				.append(" and OPER_KEY = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(operOptFlag);
		params.addParameter(orderId);
		params.addParameter(operKey);

		return eds.update(updateSQL.toString(), params);
	}

	public Map selectLotNumberInfo(String orderId)
	{

		StringBuffer selectSql = new StringBuffer().append("SELECT LOT_NO, LOT_ID ")
				.append("  FROM SFWID_LOT_DESC ")
				.append(" WHERE ORDER_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);

		Map lotMap = eds.queryForMap(selectSql.toString(), parameters);

		return lotMap;
	}

	// Defect 416   
	public Map selectPlanInfoFromOrder(String orderId, Number operKey) //Defect 727
	{

		StringBuffer selectSql = new StringBuffer().append("SELECT O.PLAN_ID, O.PLAN_VERSION, O.PLAN_REVISION, O.PLAN_UPDT_NO, OP.OPER_UPDT_NO ")
				.append("  FROM SFWID_ORDER_DESC O, SFWID_OPER_DESC OP ")
				.append(" WHERE O.ORDER_ID = OP.ORDER_ID ")
				.append("   AND O.ORDER_ID = ? ")
				.append("   AND OP.OPER_KEY = ? ")
				.append("   AND OP.STEP_KEY = -1 "); //Defect 727



		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);  //Defect 727

		Map planInfoForOrderMap = eds.queryForMap(selectSql.toString(), parameters);

		return planInfoForOrderMap;
	}

	@SuppressWarnings("rawtypes")
	public Map selectSerialNumberInfo(String orderId)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT * ")
				.append(" FROM SFWID_SERIAL_DESC ")
				.append(" WHERE ORDER_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);

		Map serialMap = eds.queryForMap(selectSql.toString(), parameters);

		return serialMap;
	}

	@SuppressWarnings("rawtypes")
	public Map selectSerialNumberInfo(String orderId,String serialNo)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT T.* ")
				.append(" FROM SFWID_SERIAL_DESC T ")
				.append(" WHERE T.ORDER_ID = ? ")
				.append(" AND  T.SERIAL_NO = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(serialNo);

		Map serialMap = eds.queryForMap(selectSql.toString(), parameters);

		return serialMap;
	}
	
	//defect 1908
	public boolean SerialNumberOrderComboExists(String orderId,String serialNo)
	{
		boolean serialOrderComboExists = false;
		
		StringBuffer selectSql = new StringBuffer().append("SELECT count(*) ")  //defect 1946
				.append(" FROM SFWID_SERIAL_DESC T ")
				.append(" WHERE T.ORDER_ID = ? ")
				.append(" AND  T.SERIAL_NO = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(serialNo);

		try {
		  int count = eds.queryForInt(selectSql.toString(), parameters);
		
		  if (count > 0 )
		   {
			serialOrderComboExists = true;
		   }
		}
		catch(Exception e) {
			System.out.println("serial doesn't exist for that order id");
		}

		return serialOrderComboExists;
	}

	public String selectOrderNumber(String orderId)
	{
		String orderNo = null;

		StringBuffer selectSql = new StringBuffer().append("SELECT ORDER_NO ")
				.append("  FROM SFWID_ORDERS ")
				.append(" WHERE ORDER_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);

		orderNo =  eds.queryForString(selectSql.toString(), parameters);

		return orderNo;
	}

	public boolean isFinalBuyoffInOper(String orderId,
			Number operKey,
			String serialFlag)
	{
		boolean buyoffsComplete = false;


		if (StringUtils.equalsIgnoreCase(serialFlag, "N")){
			StringBuffer selectSql = new StringBuffer().append(" SELECT COUNT(*)")		        		 									
					.append(" FROM SFWID_SERIAL_OPER_BUYOFF T")
					.append(" WHERE T.ORDER_ID = ?")
					.append(" AND T.OPER_KEY = ?")
					.append(" AND T.BUYOFF_STATUS  <> ? ");

			ParameterHolder parameters = new ParameterHolder();
			parameters.addParameter(orderId);
			parameters.addParameter(operKey);
			parameters.addParameter("ACCEPT");
			int count = eds.queryForInt(selectSql.toString(), parameters);

			//System.out.println("BuyoffDaoPW.isFinalBuyoffInOper selectSql = " + selectSql);
			//System.out.println("BuyoffDaoPW.isFinalBuyoffInOper parameters = " + parameters);
			//System.out.println("BuyoffDaoPW.isFinalBuyoffInOper count = " + count);

			if (count == 0){
				buyoffsComplete = true;
			}

		}else{
			StringBuffer selectSql = new StringBuffer().append(" SELECT COUNT(*) ")		        		 									
					.append(" FROM SFWID_SERIAL_OPER_BUYOFF T, SFWID_SERIAL_DESC T1")
					.append(" WHERE T.ORDER_ID = T1.ORDER_ID ")
					.append(" AND T.LOT_ID = T1.LOT_ID " )
					.append(" AND T.SERIAL_ID = T1.SERIAL_ID " )
					.append(" AND T1.SERIAL_STATUS NOT IN ( ? , ? ) ")
					.append(" AND T.ORDER_ID = ?")
					.append(" AND T.OPER_KEY = ?")
					.append(" AND T.BUYOFF_STATUS  <> ? ");

			ParameterHolder parameters = new ParameterHolder();
			parameters.addParameter("STOP");
			parameters.addParameter("SCRAP");
			parameters.addParameter(orderId);
			parameters.addParameter(operKey);
			parameters.addParameter("ACCEPT");
			int count = eds.queryForInt(selectSql.toString(), parameters);

			//System.out.println("BuyoffDaoPW.isFinalBuyoffInOper selectSql = " + selectSql);
			//System.out.println("BuyoffDaoPW.isFinalBuyoffInOper parameters = " + parameters);
			//System.out.println("BuyoffDaoPW.isFinalBuyoffInOper count = " + count);

			if (count == 0){
				buyoffsComplete = true;
			}
		}	
		return buyoffsComplete;			    
	}

	//Defect 416
	public boolean isworkInstructionsRead(String planId,
			Number operKey,
			Number operUpdtNo)   //Defect 727
	{
		boolean workInstructionsRead = false;   // ACKNOWLEDGE_REQ = Y

		StringBuffer selectSql = new StringBuffer().append("SELECT COUNT(*)")		        		 									
				.append("  FROM SFWID_USER_OPER_CHG_ACK")
				.append(" WHERE PLAN_ID = ?")
				.append("   AND OPER_KEY = ?")                           
				.append("   AND OPER_UPDT_NO = ?")
				.append("   AND USERID = ?")
				.append("   AND ACKNOWLEDGE_REQ = 'N'");  //Defect 727

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(planId);
		parameters.addParameter(operKey);
		parameters.addParameter(operUpdtNo);   ///Defect 727
		parameters.addParameter(ContextUtil.getUsername());

		int count = eds.queryForInt(selectSql.toString(), parameters);

		if (count == 1)  
		{
			workInstructionsRead = true;
		}

		return workInstructionsRead;			    
	}

	//Defect 727
	public boolean isSupplementalWorkInstructionsRead(String orderId,
			Number operKey,
			String altId)   
	{
		boolean workInstructionsRead = false;   // ACKNOWLEDGE_REQ = Y

		StringBuffer selectSql = new StringBuffer().append("SELECT COUNT(*)")		        		 									
				.append("  FROM SFWID_USER_OPER_DEV_ACK")
				.append(" WHERE ORDER_ID = ?")
				.append("   AND OPER_KEY = ?")                           
				.append("   AND STEP_KEY = -1")//Defect 949
				.append("   AND ALT_ID = ?")
				.append("   AND USERID = ?")
				.append("   AND ACKNOWLEDGE_REQ = 'N'");  

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(altId);  
		parameters.addParameter(ContextUtil.getUsername());

		int count = eds.queryForInt(selectSql.toString(), parameters);

		if (count == 1)  
		{
			workInstructionsRead = true;
		}

		return workInstructionsRead;			    
	}

	//Begin Defect 727
	public boolean isFinalBuyoffBlockInOper(String orderId,
			Number operKey,
			String orderSserialFlag)
	{
		boolean lastBuyoffBlock = false; 

		if (StringUtils.equalsIgnoreCase(orderSserialFlag, "N")){

			StringBuffer selectSql = new StringBuffer().append("SELECT DISTINCT REF_ID ")	
					.append("  FROM SFWID_OPER_BUYOFF OB, ") 
					.append("       SFWID_SERIAL_OPER_BUYOFF SO ")	
					.append(" WHERE SO.ORDER_ID = OB.ORDER_ID ")	
					.append("   AND SO.OPER_KEY = OB.OPER_KEY ")	
					.append("   AND SO.STEP_KEY = OB.STEP_KEY ")	
					.append("   AND SO.BUYOFF_ID = OB.BUYOFF_ID ")	
					.append("   AND SO.ORDER_ID = ? ")	
					.append("   AND SO.OPER_KEY = ? ")	
					.append("   AND SO.BUYOFF_STATUS <> ? ");


			ParameterHolder parameters = new ParameterHolder();
			parameters.addParameter(orderId);
			parameters.addParameter(operKey);
			parameters.addParameter("ACCEPT");
			List buyoffBlocksList = eds.queryForList(selectSql.toString(), parameters);
			if(buyoffBlocksList.size()  == 1 ){
				lastBuyoffBlock = true;
			}
		}else{
			StringBuffer selectSql = new StringBuffer().append("SELECT DISTINCT REF_ID ")	
					.append("  FROM SFWID_OPER_BUYOFF OB,") 
					.append("       SFWID_SERIAL_OPER_BUYOFF SO, ")
					.append("       SFWID_SERIAL_DESC ST ")

					.append(" WHERE SO.ORDER_ID = OB.ORDER_ID ")	
					.append(" AND SO.OPER_KEY = OB.OPER_KEY ")	
					.append(" AND SO.STEP_KEY = OB.STEP_KEY ")	
					.append(" AND SO.BUYOFF_ID = OB.BUYOFF_ID ")

					.append(" AND   SO.ORDER_ID = ST.ORDER_ID ")
					.append(" AND   SO.LOT_ID = ST.LOT_ID ")
					.append(" AND   SO.SERIAL_ID = ST.SERIAL_ID ")
					.append(" AND   ST.SERIAL_STATUS NOT IN ( ? , ? ) ") 

					.append(" AND SO.ORDER_ID = ? ")	
					.append(" AND SO.OPER_KEY = ? ")	
					.append(" AND SO.BUYOFF_STATUS <>  ? ");


			ParameterHolder parameters = new ParameterHolder();

			parameters.addParameter("STOP");
			parameters.addParameter("SCRAP");
			parameters.addParameter(orderId);
			parameters.addParameter(operKey);
			parameters.addParameter("ACCEPT");
			List buyoffBlocksList = eds.queryForList(selectSql.toString(), parameters);
			if(buyoffBlocksList.size()  == 1 ){
				lastBuyoffBlock = true;
			}
		}
		return lastBuyoffBlock;
	}
	//End Defect 727

	public String selectNANDBuyoffStatus(String orderId,
			Number operKey, 
			Number stepKey,
			String buyoffId,
			String refId){

		//Map returnMap = new HashMap();
		Map map;
		String priorBuyoffStatus= null;

		// Defect 973
		// Defect 860
		StringBuffer selectSql = new StringBuffer().append(" SELECT * ")
				.append("   FROM (SELECT CASE ")
				.append("                  WHEN B.UCF_SRLOPBUYOFF_FLAG1 = 'P' THEN ")
				.append("                   'PARTIAL' ")
				.append("                  WHEN B.UCF_SRLOPBUYOFF_VCH1 IS NULL THEN ")
				.append("                   B.BUYOFF_STATUS ")
				.append("                ELSE ")
				.append("                   B.UCF_SRLOPBUYOFF_VCH1 ")
				.append("                END AS PRIOR_BUYOFF_STATUS ")
				.append("           FROM SFWID_OPER_BUYOFF A, ")
				.append("                SFWID_SERIAL_OPER_BUYOFF B ")
				.append("          WHERE A.ORDER_ID = B.ORDER_ID ")
				.append("            AND A.OPER_KEY = B.OPER_KEY ")
				.append("            AND A.STEP_KEY = B.STEP_KEY ")
				.append("            AND A.BUYOFF_ID = B.BUYOFF_ID ")
				.append("            AND B.BUYOFF_STATUS NOT IN ('PENDING','REOPEN') ") 
				.append("            AND A.ORDER_ID = ? ")
				.append("            AND A.OPER_KEY = ? ")
				.append("            AND A.STEP_KEY = ? ")
				.append("            AND A.BUYOFF_ID <> ? ")
				.append("            AND A.REF_ID = ? ")
				.append("      ORDER BY B.TIME_STAMP DESC) ")
				.append(" WHERE ROWNUM = 1 ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(buyoffId);
		parameters.addParameter(refId);

		List priorBuyoffStatusList = eds.queryForList(selectSql.toString(), parameters);

		//System.out.println("BuyoffDaoPW.selectNANDBuyoffStatus selectSql = " + selectSql);
		//System.out.println("BuyoffDaoPW.selectNANDBuyoffStatus parameters = " + parameters);
		//System.out.println("BuyoffDaoPW.selectNANDBuyoffStatus priorBuyoffStatusList = " + priorBuyoffStatusList);


		if(priorBuyoffStatusList.size()  == 0 ){	
			priorBuyoffStatus = "NONE";
		}else{
			map = (Map) priorBuyoffStatusList.get(0);
			priorBuyoffStatus = (String) map.get("PRIOR_BUYOFF_STATUS");
		}
		return priorBuyoffStatus;
	}

	/**
	 * SMRO_WOE-204 
	 * @XCS4071
	 */
	public boolean getSupplementalBuyoffStatus(String orderId, String operNo)	 
	// Defect 347 - changed parameter from parentOrderId to orderId

	{
		boolean suppOperExists = false;

		StringBuffer selectSql = new StringBuffer().append(" SELECT COUNT(*) ")
				.append("   FROM SFWID_ORDER_DESC ")
				.append("  WHERE PARENT_ORDER_ID = ? ")
				.append("    AND UCF_ORDER_VCH13 = ? ")
				.append("    AND ORDER_STATUS   <> 'CLOSE' ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operNo);

		int count = eds.queryForInt(selectSql.toString(), parameters);

		if (count > 0)
		{
			suppOperExists = true;
		}

		return suppOperExists;	
	}


	/**SMRO_WOE_204  
	 * @XCS4071 N.Gnassounou
	 */
	public String suppOrderNo(String parentOrderNo, String buildCondition)
	{

		return getSupplementalOrderNo(parentOrderNo, buildCondition)[0];

	}

	/**SMRO_WOE_204  
	 * @XCS4071 N.Gnassounou
	 */

	@SuppressWarnings("unchecked")
	public String [] getSupplementalOrderNo (String orderNo , String newOrderType)	
	{
		Integer length, index, iSuppOrderStart;
		String snbr, sgroup;
		String suppOrderID[] = new String[2];


		// Begin defect 705
		StringBuilder realOrderNoSql = new StringBuilder().append("SELECT PW_ORDER_NO ")
				.append(" FROM PWUST_SFWID_ORDER_DESC A, SFWID_ORDER_DESC B ")
				.append(" WHERE ORDER_NO = ? AND A.ORDER_ID = B.ORDER_ID");
		ParameterHolder realOrderNoParameters = new ParameterHolder();
		realOrderNoParameters.addParameter(orderNo);
		List<Map<String, String>> realOrderNoList = eds.queryForList(realOrderNoSql.toString(), realOrderNoParameters);
		if ((realOrderNoList != null) && !realOrderNoList.isEmpty()) {
			Map<String, String> row = realOrderNoList.get(0);
			orderNo = row.get("PW_ORDER_NO");
		}


		StringBuilder countSql = new StringBuilder().append("SELECT COUNT(*) ")
				.append("FROM PWUST_SFWID_ORDER_DESC ")
				.append("WHERE PW_ORDER_NO = ?");

		ParameterHolder countParameters = new ParameterHolder();
		countParameters.addParameter(orderNo);

		int rowCount = eds.queryForInt(countSql.toString(), countParameters);


		StringBuilder selectSQL;

		if (rowCount > 0) {
			// Begin defect 457
			selectSQL = new StringBuilder().append("SELECT PW_ORDER_NO AS SUPP_ORDER_NO, SECURITY_GROUP ")
					.append(" FROM PWUST_SFWID_ORDER_DESC A, SFWID_ORDER_DESC B ")
					.append(" WHERE PW_ORDER_NO LIKE ? AND A.ORDER_ID = B.ORDER_ID");
			if (!newOrderType.equals("SUP"))
			{
				selectSQL.append(" AND PW_ORDER_NO NOT LIKE '%-S%' ");
			}
			selectSQL.append(" ORDER BY PW_ORDER_NO DESC ");
		} else {
			// Begin defect 457
			selectSQL = new StringBuilder().append("SELECT ORDER_NO AS SUPP_ORDER_NO, SECURITY_GROUP ")
					.append(" FROM SFWID_ORDER_DESC ")
					.append(" WHERE ORDER_NO LIKE ? ");
			if (!newOrderType.equals("SUP"))
			{
				selectSQL.append(" AND ORDER_NO NOT LIKE '%-S%' ");
			}
			selectSQL.append(" ORDER BY ORDER_NO DESC ");
		}
		// End defect 705

		StringBuilder selectDefStartSQL = new StringBuilder().append("SELECT PARAMETER_VALUE ")
				.append(" FROM PWUST_GLOBAL_CONFIG ")
				.append(" WHERE PARAMETER_NAME = 'SUPPSTART' ")
				.append(" AND PARAMETER_DESC = ? ");

		iSuppOrderStart = 1;
		suppOrderID[0] = orderNo + "-" + newOrderType + "0__";

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(suppOrderID[0].toUpperCase());
		// End defect 457

		ParameterHolder parameters2 = new ParameterHolder();
		parameters2.addParameter(newOrderType);

		List<Map<String, String>> list = eds.queryForList(selectSQL.toString(), parameters);
		List<Map<String, String>> list2 = eds.queryForList(selectDefStartSQL.toString(), parameters2);

		if(list2 != null && !list2.isEmpty()) {
			for(int i = 0; i < 1; i++) {
				Map<String, String> rowdef = list2.get(i);
				iSuppOrderStart = Integer.parseInt(rowdef.get("PARAMETER_VALUE"));
			}
		}

		if(list != null && !list.isEmpty()) {
			for(int i = 0; i < 1; i++) {
				Map<String, String> row = list.get(i);
				suppOrderID[0] = row.get("SUPP_ORDER_NO"); //Defect 433
				suppOrderID[1] = row.get("SECURITY_GROUP");
			}
			length = suppOrderID[0].length() - 3;
			index = Integer.parseInt(suppOrderID[0].substring(length)) + 1;
			snbr = String.format("%03d", index);
			snbr = snbr.substring(snbr.length() - 3);
			suppOrderID[0] = suppOrderID[0].substring(0, length) + snbr;
			return suppOrderID;
		} else {
			snbr = String.format("%03d", iSuppOrderStart);
			snbr = snbr.substring(snbr.length() - 3);
			suppOrderID[0] = orderNo + "-" + newOrderType + snbr;
			suppOrderID[1] = null;
			return suppOrderID;
		}
	}

	// SMRO-RPT_201
	public List selectSaleOrders(String ucf_order_vch12)
	{
		List list = null;
		StringBuffer selectSql = new StringBuffer().append("SELECT ORDER_ID, T.SECURITY_GROUP ")
				.append("  FROM SFWID_ORDER_DESC T ")
				.append(" WHERE T.ucf_order_vch12 = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(ucf_order_vch12);
		list = eds.queryForList(selectSql.toString(), parameters);
		return list;
	}           

	//SMRO_RPT_201
	public String getOrderNoByOrderId(String orderId)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT DISTINCT ORDER_NO, UCF_ORDER_VCH12 ")
				.append("  FROM SFWID_ORDER_DESC ")
				.append(" WHERE ORDER_ID = ? ");
		// .append(" AND  UCF_ORDER_VCH12 IS NOT NULL ");;

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);

		String orderNo = "";
		try {
			orderNo = eds.queryForString(selectSql.toString(), params);
		}
		catch(Exception e) {
			// Do nothing
		}
		return orderNo;
	}

	//SMRO_RPT_201
	public String getOrderId(String orderNumber)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT ")
				.append("ORDER_ID ")
				.append("FROM ")
				.append("SFWID_ORDER_DESC ")
				.append("WHERE ORDER_NO = ? ");


		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderNumber);
		String orderId = eds.queryForString(selectSql.toString(), params);
		return orderId;	
	}

	//SMRO_RPT_201
	@SuppressWarnings("rawtypes")
	public List selectDeliveryPackageInfo(String ucf_order_vch12)
	{
		List list = null;
		StringBuilder selectSql = new StringBuilder().append("SELECT DISTINCT ")
				.append("   1 AS LVL, ")	                                                  
				.append("     '' AS PARENT_UCF_ORDER_VCH12,  ")
				.append("     ?  AS UCF_ORDER_VCH12,         ")  //UCF_ORDER_VCH12	                                                  
				.append("     '' AS ORDER_NO,                ")	 //UCF_ORDER_VCH12	  	    	                                           
				.append("     '' AS PART_NO,                 ")
				.append("     NULL AS SECURITY_GROUP         ")
				.append("     FROM SFWID_ORDER_DESC A        ")
				.append("   WHERE A.UCF_ORDER_VCH12 = ?      ")
				.append("   UNION ALL                        ")
				.append("   SELECT DISTINCT                  ")
				.append("   2 AS LVL,                        ")	                                                 
				.append("   ? AS PARENT_UCF_ORDER_VCH12,     ")  //UCF_ORDER_VCH12
				.append("   B. ORDER_NO AS UCF_ORDER_VCH12,  ")	                                                   
				.append("   B.ORDER_NO,                      ")	   
				.append("   B.PART_NO,                       ")
				.append("     NULL AS SECURITY_GROUP         ")
				.append("   FROM SFWID_ORDER_DESC B, SFWID_ORDER_DESC C ")
				.append("   WHERE B.UCF_ORDER_VCH12 = C.UCF_ORDER_VCH12 ")
				.append("    AND B.ORDER_ID = C.ORDER_ID ")
				.append("    AND B.UCF_ORDER_VCH12 = ? ")	
				.append(" ORDER BY 1, 4 ");

		ParameterHolder params = new ParameterHolder();	       
		params.addParameter(ucf_order_vch12);
		params.addParameter(ucf_order_vch12);
		params.addParameter(ucf_order_vch12);
		params.addParameter(ucf_order_vch12);


		list = eds.queryForList(selectSql.toString(), params);
		return list;
	}

	// SMRO_RPT_201
	public List selectWorkOrders(String orderId)		                     

	{
		List list = null;
		StringBuffer selectSql = new StringBuffer().append("SELECT ")
				.append("    B.ORDER_ID, ")
				.append("    B.UCF_ORDER_VCH12, ")
				.append("    B.PART_NO, ")
				.append("    B.PLAN_NO, ")
				.append("    B.ORDER_NO, ")
				.append("    B.ORDER_STATUS, ")
				.append("    B.PROGRAM, ")
				.append("    B.PLAN_TITLE, ")
				.append("    B.SECURITY_GROUP ")
				.append("FROM ")			
				.append("    SFWID_ORDER_DESC B ")
				.append("WHERE ")				                                   				
				.append("    B.ORDER_ID = ? ")				
				.append("   AND  B.ORDER_STATUS <>'CLOSE' ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);

		list = eds.queryForList(selectSql.toString(), params);

		return list;
	}

	// SMRO_RPT_201
	public String getReportName(String objectId)
	{
		String reportName = "X";

		try
		{
			StringBuffer selectSql = new StringBuffer().append("select t.object_desc ")
					.append("  from sfmfg.sfcore_mm_object t ")
					.append(" where t.object_id = ? ");

			ParameterHolder parameter = new ParameterHolder();
			parameter.addParameter(objectId);

			reportName = eds.queryForString(selectSql.toString(), parameter);
			return reportName;
		}
		catch(EmptyResultDataAccessException e)
		{
			return reportName;
		}
	}//end method


	// Start SMRO_WOE_204-Defect 344
	public int updateOrderDesc(String orderId)

	{
		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFWID_ORDER_DESC ")
				.append("   SET ORDER_TYPE = 'SUB ORDER' ")
				.append("  WHERE ORDER_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);


		return eds.update(updateSql.toString(), parameters);  
	}

	public int updateActiveNQOrders(String orderId)

	{
		StringBuilder updateSql = new StringBuilder().append("UPDATE ")
				.append("    SFWID_ACTIV_NQ_ORDERS ")
				.append("    SET   ORDER_TYPE = 'SUB ORDER' ")							
				.append("    WHERE ORDER_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);

		Integer updCount = eds.update(updateSql.toString(), parameters);
		return updCount;

	}

	public int updateAsWorkedItem(String orderId)

	{
		StringBuilder updateSql = new StringBuilder().append("UPDATE ")
				.append("    SFWID_AS_WORKED_ITEM ")
				.append("    SET   ORDER_TYPE = 'SUB ORDER' ")							
				.append("    WHERE ORDER_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);

		Integer updCount = eds.update(updateSql.toString(), parameters);
		return updCount;
	}

	public int updateAsWorkedItemHist(String orderId)

	{
		StringBuilder updateSql = new StringBuilder().append("UPDATE ")
				.append("    SFWID_AS_WORKED_ITEM_HIST ")
				.append("    SET   ORDER_TYPE = 'SUB ORDER' ")							
				.append("    WHERE ORDER_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);

		Integer updCount = eds.update(updateSql.toString(), parameters);
		return updCount;

	}

	public int updateOrders(String orderId)

	{
		StringBuilder updateSql = new StringBuilder().append("UPDATE ")
				.append("    SFWID_ORDERS ")
				.append("    SET   ORDER_TYPE = 'SUB ORDER' ")							
				.append("    WHERE ORDER_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);

		Integer updCount = eds.update(updateSql.toString(), parameters);
		return updCount;

	}

	public int updateOrderDescAlt(String orderId)

	{
		StringBuilder updateSql = new StringBuilder().append("UPDATE ")
				.append("    SFWID_ORDER_DESC_ALT ")
				.append("    SET   ORDER_TYPE = 'SUB ORDER' ")							
				.append("    WHERE ORDER_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);

		Integer updCount = eds.update(updateSql.toString(), parameters);
		return updCount;

	}

	public int updateOrderDescHist(String orderId)

	{
		StringBuilder updateSql = new StringBuilder().append("UPDATE ")
				.append("    SFWID_ORDER_DESC_HIST ")
				.append("    SET   ORDER_TYPE = 'SUB ORDER' ")							
				.append("    WHERE ORDER_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);

		Integer updCount = eds.update(updateSql.toString(), parameters);
		return updCount;

	}

	public int updateOrderItemIssued(String orderId)

	{
		StringBuilder updateSql = new StringBuilder().append("UPDATE ")
				.append("    SFWID_ORDER_ITEM_ISSUED ")
				.append("    SET   ORDER_TYPE = 'SUB ORDER' ")							
				.append("    WHERE ORDER_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);

		Integer updCount = eds.update(updateSql.toString(), parameters);
		return updCount;

	}

	public int updateOrderItemIssuedHist(String orderId)

	{
		StringBuilder updateSql = new StringBuilder().append("UPDATE ")
				.append("    SFWID_ORDER_ITEM_ISSUED_HIST ")
				.append("    SET   ORDER_TYPE = 'SUB ORDER' ")							
				.append("    WHERE ORDER_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);

		Integer updCount = eds.update(updateSql.toString(), parameters);
		return updCount;

	}

	public int updateOrderOpersDispData(String orderId)

	{
		StringBuilder updateSql = new StringBuilder().append("UPDATE ")
				.append("    SFWID_ORDER_OPERS_DISP_DATA  ")
				.append("    SET   ORDER_TYPE = 'SUB ORDER' ")							
				.append("    WHERE ORDER_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);

		Integer updCount = eds.update(updateSql.toString(), parameters);
		return updCount;

	}


	public int updatePendingOrders(String orderId)

	{
		StringBuilder updateSql = new StringBuilder().append("UPDATE ")
				.append("    SFWID_PENDING_ORDERS ")
				.append("    SET   ORDER_TYPE = 'SUB ORDER' ")							
				.append("    WHERE ORDER_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);

		Integer updCount = eds.update(updateSql.toString(), parameters);
		return updCount;

	}

	public int updateOrderOpersDispatch(String orderId)

	{
		StringBuilder updateSql = new StringBuilder().append("UPDATE ")
				.append("    SFWID_ORDER_OPERS_DISPATCH ")
				.append("    SET   ORDER_TYPE = 'SUB ORDER' ")							
				.append("    WHERE ORDER_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);

		Integer updCount = eds.update(updateSql.toString(), parameters);
		return updCount;

	}

	// End SMRO_WOE_204 -Defect 344

	public int updateAdditionalSignoffs(String orderId,
			Number operKey, 
			Number stepKey,
			String buyoffId,
			String signoffs,
			String serialId,
			String lotId)  //1258
	{

		////Defect 1258 -- will need to add serialID to this 
		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFWID_SERIAL_OPER_BUYOFF ")
				.append("    SET UCF_SRLOPBUYOFF_VCH4000_1 = ? ")
				.append("  WHERE ORDER_ID = ? ")
				.append("    AND OPER_KEY = ? ")
				.append("    AND STEP_KEY = ? ")
				.append("    AND BUYOFF_ID = ? ")
				.append("    AND SERIAL_ID = ? ") //1258
				.append("    AND LOT_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(signoffs);
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(buyoffId);
		parameters.addParameter(serialId);   //1258
		parameters.addParameter(lotId);

		return eds.update(updateSql.toString(), parameters);
	}	

	// Defect 695
	public int setNaNdStatusNull(String orderId,
			Number operKey,
			Number stepKey,
			String buyoffId)
	{

		StringBuffer updateSQL = new StringBuffer().append(" UPDATE SFWID_SERIAL_OPER_BUYOFF ")
				.append("    SET UCF_SRLOPBUYOFF_VCH1 = null, ")
				.append("        UCF_SRLOPBUYOFF_VCH4000_1 = null ")  // Defect 694
				.append("  WHERE ORDER_ID = ? ")
				.append("    AND OPER_KEY = ? ")
				.append("    AND STEP_KEY = ? ")   // Defect 860 - 20181113
				.append("    AND BUYOFF_ID = ? ");


		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(operKey);
		params.addParameter(stepKey);     // Defect 860 - 20181113
		params.addParameter(buyoffId);

		Integer updCount = eds.update(updateSQL.toString(), params);
		return updCount;
	}

	// Defect 694
	public List selectSerialBuyoffList(String orderId, 
			Number operKey, 
			Number stepKey, 
			String buyoffId, 
			String serialId, 
			String lotId, 
			String userId, 
			String userConnectionId)
	{

		List list = null;
		StringBuilder selectSql = new StringBuilder().append(" SELECT ")
				.append(" CASE  WHEN UCF_SRLOPBUYOFF_FLAG1 ='P' THEN 'PARTIAL' WHEN UCF_SRLOPBUYOFF_VCH1 IS NULL THEN ")
				.append(" DECODE( BUYOFF_STATUS, 'SKIP', BUYOFF_STATUS, 'REJECT', BUYOFF_STATUS, ")
				.append(" DECODE( SFWID_BUYOFF_STATUS_OK(?, ?, ?, ?, NULL, ?, ?, 'Y', ?, NULL, NULL), ")
				.append(" 'Y', BUYOFF_STATUS, NULL)) ")
				.append(" ELSE 'Closed-'||UCF_SRLOPBUYOFF_VCH1 END AS BUYOFF_STATUS, ")
				.append(" COMMENTS, ")
				.append(" SUBSTR(SFFND_USER_NAME_GET(A.UPDT_USERID),1,90) AS USERNAME, ")
				.append(" A.UPDT_USERID, ")
				.append(" A.TIME_STAMP, ")
				.append(" A.BUYOFF_ID, ")
				.append(" A.ORDER_ID, ")
				.append(" (SFMFG.SFFND_FORMAT_VALUE_TO_CLIENT(CASE WHEN PERCENT_COMPLETE IS NOT NULL THEN (CAST(PERCENT_COMPLETE AS FLOAT)/100) ")
				.append(" ELSE COMPLETE_QTY END,'NUMERIC',?) || (CASE WHEN PERCENT_COMPLETE IS NOT NULL THEN '%' ELSE '' END)) AS PERCENT_QTY_COMPLETE, ")
				.append(" UCF_SRLOPBUYOFF_DATE1,  UCF_SRLOPBUYOFF_DATE2,  UCF_SRLOPBUYOFF_DATE3,  UCF_SRLOPBUYOFF_DATE4, ")
				.append(" UCF_SRLOPBUYOFF_DATE5,  UCF_SRLOPBUYOFF_FLAG1,  UCF_SRLOPBUYOFF_FLAG2,  UCF_SRLOPBUYOFF_FLAG3, ")
				.append(" UCF_SRLOPBUYOFF_FLAG4,  UCF_SRLOPBUYOFF_FLAG5,  UCF_SRLOPBUYOFF_NUM1 ,  UCF_SRLOPBUYOFF_NUM2 , ")
				.append(" UCF_SRLOPBUYOFF_NUM3 ,  UCF_SRLOPBUYOFF_NUM4 ,  UCF_SRLOPBUYOFF_NUM5 ,  UCF_SRLOPBUYOFF_VCH1 , ")
				.append(" UCF_SRLOPBUYOFF_VCH2 ,  UCF_SRLOPBUYOFF_VCH3 ,  UCF_SRLOPBUYOFF_VCH4 ,  UCF_SRLOPBUYOFF_VCH5 , ")
				.append(" UCF_SRLOPBUYOFF_VCH6 ,  UCF_SRLOPBUYOFF_VCH7 ,  UCF_SRLOPBUYOFF_VCH8 ,  UCF_SRLOPBUYOFF_VCH9 , ")
				.append(" UCF_SRLOPBUYOFF_VCH10,  UCF_SRLOPBUYOFF_VCH11,  UCF_SRLOPBUYOFF_VCH12,  UCF_SRLOPBUYOFF_VCH13, ")
				.append(" UCF_SRLOPBUYOFF_VCH14,  UCF_SRLOPBUYOFF_VCH15,  UCF_SRLOPBUYOFF_VCH255_1,  UCF_SRLOPBUYOFF_VCH255_2, ")
				.append(" UCF_SRLOPBUYOFF_VCH255_3,  UCF_SRLOPBUYOFF_VCH4000_1,  UCF_SRLOPBUYOFF_VCH4000_2 ")
				.append(" FROM SFWID_SERIAL_OPER_BUYOFF A ")
				.append(" WHERE A.BUYOFF_ID = ? ")
				.append(" AND A.ORDER_ID = ? ")
				.append(" AND A.SERIAL_ID = ? ")
				.append(" AND A.LOT_ID =    ? ")
				.append(" AND A.OPER_KEY = ? ")
				.append(" AND A.STEP_KEY = ? ")
				//.append(" AND A.BUYOFF_STATUS NOT IN ('OPEN','PENDING') ")  //1033
				.append(" AND A.BUYOFF_STATUS NOT IN ('PENDING') ")  
				.append(" UNION ")
				.append(" SELECT ")
				.append(" CASE  WHEN UCF_SRLOPBUYOFF_FLAG1 ='P' THEN 'PARTIAL' WHEN UCF_SRLOPBUYOFF_VCH1 IS NULL THEN ")
				.append(" DECODE( BUYOFF_STATUS, 'SKIP', BUYOFF_STATUS, 'REJECT', BUYOFF_STATUS, ")
				.append(" DECODE( SFWID_BUYOFF_STATUS_OK(?, ?, ?, ?, NULL, ?, ?, 'Y', ?, NULL, NULL), ")
				.append(" 'Y', BUYOFF_STATUS, NULL)) ")
				.append(" ELSE 'Closed-'||UCF_SRLOPBUYOFF_VCH1 END AS BUYOFF_STATUS, ")
				.append(" COMMENTS, ")
				.append(" SUBSTR(SFFND_USER_NAME_GET(A.UPDT_USERID),1,90) AS USERNAME, ") //defect 1940
				.append(" A.UPDT_USERID AS UPDT_USERID, ")  //DEFECT 1994
				.append(" A.TIME_STAMP AS TIME_STAMP, ")  //DEFECT 1994
				.append(" A.BUYOFF_ID, ")
				.append(" A.ORDER_ID, ")
				.append(" (SFMFG.SFFND_FORMAT_VALUE_TO_CLIENT(CASE WHEN PERCENT_COMPLETE IS NOT NULL THEN (CAST(PERCENT_COMPLETE AS FLOAT)/100) ")
				.append(" ELSE COMPLETE_QTY END,'NUMERIC',?) || (CASE WHEN PERCENT_COMPLETE IS NOT NULL THEN '%' ELSE '' END)) AS PERCENT_QTY_COMPLETE, ")
				.append(" UCF_SRLOPBUYOFF_DATE1,  UCF_SRLOPBUYOFF_DATE2,  UCF_SRLOPBUYOFF_DATE3,  UCF_SRLOPBUYOFF_DATE4, ")
				.append(" UCF_SRLOPBUYOFF_DATE5,  UCF_SRLOPBUYOFF_FLAG1,  UCF_SRLOPBUYOFF_FLAG2,  UCF_SRLOPBUYOFF_FLAG3, ")
				.append(" UCF_SRLOPBUYOFF_FLAG4,  UCF_SRLOPBUYOFF_FLAG5,  UCF_SRLOPBUYOFF_NUM1 ,  UCF_SRLOPBUYOFF_NUM2 , ")
				.append(" UCF_SRLOPBUYOFF_NUM3 ,  UCF_SRLOPBUYOFF_NUM4 ,  UCF_SRLOPBUYOFF_NUM5 ,  UCF_SRLOPBUYOFF_VCH1 , ")
				.append(" UCF_SRLOPBUYOFF_VCH2 ,  UCF_SRLOPBUYOFF_VCH3 ,  UCF_SRLOPBUYOFF_VCH4 ,  UCF_SRLOPBUYOFF_VCH5 , ")
				.append(" UCF_SRLOPBUYOFF_VCH6 ,  UCF_SRLOPBUYOFF_VCH7 ,  UCF_SRLOPBUYOFF_VCH8 ,  UCF_SRLOPBUYOFF_VCH9 , ")
				.append(" UCF_SRLOPBUYOFF_VCH10,  UCF_SRLOPBUYOFF_VCH11,  UCF_SRLOPBUYOFF_VCH12,  UCF_SRLOPBUYOFF_VCH13, ")
				.append(" UCF_SRLOPBUYOFF_VCH14,  UCF_SRLOPBUYOFF_VCH15,  UCF_SRLOPBUYOFF_VCH255_1,  UCF_SRLOPBUYOFF_VCH255_2, ")
				.append(" UCF_SRLOPBUYOFF_VCH255_3,  UCF_SRLOPBUYOFF_VCH4000_1,  UCF_SRLOPBUYOFF_VCH4000_2 ")
				.append(" FROM SFWID_SERIAL_OPER_BUYOFF_HIST A ")
				.append(" WHERE A.BUYOFF_ID = ? ")
				.append(" AND A.ORDER_ID = ? ")
				.append(" AND A.SERIAL_ID = ? ")
				.append(" AND A.LOT_ID =    ? ")
				.append(" AND A.OPER_KEY = ? ")
				.append(" AND A.STEP_KEY = ? ")
				///	 .append(" AND A.BUYOFF_STATUS NOT IN ('OPEN','PENDING') ")   //1033
				.append(" AND A.BUYOFF_STATUS NOT IN ('PENDING') ")  
				.append(" AND (A.BUYOFF_STATUS <> 'OPEN' AND A.PERCENT_COMPLETE IS NOT NULL)") 
				.append(" UNION ")  ///1033 Begin
				.append(" SELECT ")
				.append(" CASE  WHEN UCF_SRLOPBUYOFF_FLAG1 ='P' THEN 'PARTIAL' WHEN UCF_SRLOPBUYOFF_VCH1 IS NULL THEN ")
				.append(" DECODE( BUYOFF_STATUS, 'SKIP', BUYOFF_STATUS, 'REJECT', BUYOFF_STATUS, ")
				.append(" DECODE( SFWID_BUYOFF_STATUS_OK(?, ?, ?, ?, NULL, ?, ?, 'Y', ?, NULL, NULL), ")
				.append(" 'Y', BUYOFF_STATUS, NULL)) ")
				.append(" ELSE 'Closed-'||UCF_SRLOPBUYOFF_VCH1 END AS BUYOFF_STATUS, ")
				.append(" COMMENTS, ")
				.append(" SUBSTR(SFFND_USER_NAME_GET(A.HIST_USERID),1,90) AS USERNAME, ") //defect 1940
				.append(" A.HIST_USERID AS UPDT_USERID, ")    //DEFECT 1940
				.append(" A.HIST_TIME_STAMP AS TIME_STAMP, ")  //DEFECT 1940
				.append(" A.BUYOFF_ID, ")
				.append(" A.ORDER_ID, ")
				.append(" (SFMFG.SFFND_FORMAT_VALUE_TO_CLIENT(CASE WHEN PERCENT_COMPLETE IS NOT NULL THEN (CAST(PERCENT_COMPLETE AS FLOAT)/100) ")
				.append(" ELSE COMPLETE_QTY END,'NUMERIC',?) || (CASE WHEN PERCENT_COMPLETE IS NOT NULL THEN '%' ELSE '' END)) AS PERCENT_QTY_COMPLETE, ")
				.append(" UCF_SRLOPBUYOFF_DATE1,  UCF_SRLOPBUYOFF_DATE2,  UCF_SRLOPBUYOFF_DATE3,  UCF_SRLOPBUYOFF_DATE4, ")
				.append(" UCF_SRLOPBUYOFF_DATE5,  UCF_SRLOPBUYOFF_FLAG1,  UCF_SRLOPBUYOFF_FLAG2,  UCF_SRLOPBUYOFF_FLAG3, ")
				.append(" UCF_SRLOPBUYOFF_FLAG4,  UCF_SRLOPBUYOFF_FLAG5,  UCF_SRLOPBUYOFF_NUM1 ,  UCF_SRLOPBUYOFF_NUM2 , ")
				.append(" UCF_SRLOPBUYOFF_NUM3 ,  UCF_SRLOPBUYOFF_NUM4 ,  UCF_SRLOPBUYOFF_NUM5 ,  UCF_SRLOPBUYOFF_VCH1 , ")
				.append(" UCF_SRLOPBUYOFF_VCH2 ,  UCF_SRLOPBUYOFF_VCH3 ,  UCF_SRLOPBUYOFF_VCH4 ,  UCF_SRLOPBUYOFF_VCH5 , ")
				.append(" UCF_SRLOPBUYOFF_VCH6 ,  UCF_SRLOPBUYOFF_VCH7 ,  UCF_SRLOPBUYOFF_VCH8 ,  UCF_SRLOPBUYOFF_VCH9 , ")
				.append(" UCF_SRLOPBUYOFF_VCH10,  UCF_SRLOPBUYOFF_VCH11,  UCF_SRLOPBUYOFF_VCH12,  UCF_SRLOPBUYOFF_VCH13, ")
				.append(" UCF_SRLOPBUYOFF_VCH14,  UCF_SRLOPBUYOFF_VCH15,  UCF_SRLOPBUYOFF_VCH255_1,  UCF_SRLOPBUYOFF_VCH255_2, ")
				.append(" UCF_SRLOPBUYOFF_VCH255_3,  UCF_SRLOPBUYOFF_VCH4000_1,  UCF_SRLOPBUYOFF_VCH4000_2 ")
				.append(" FROM SFWID_SERIAL_OPER_BUYOFF_HIST A ")
				.append(" WHERE A.BUYOFF_ID = ? ")
				.append(" AND A.ORDER_ID = ? ")
				.append(" AND A.SERIAL_ID = ? ")
				.append(" AND A.LOT_ID =    ? ")
				.append(" AND A.OPER_KEY = ? ")
				.append(" AND A.STEP_KEY = ? ")
				.append(" AND (A.BUYOFF_STATUS = 'OPEN' AND UCF_SRLOPBUYOFF_FLAG1 ='P' )")
				.append(" AND A.BUYOFF_STATUS <> 'PENDING' ")   ///1033 End
				.append(" ORDER BY TIME_STAMP DESC ");

		ParameterHolder params = new ParameterHolder();	       
		params.addParameter(orderId);
		params.addParameter(operKey);
		params.addParameter(stepKey);
		params.addParameter(buyoffId);
		params.addParameter(serialId);
		params.addParameter(lotId);
		params.addParameter(userId);
		params.addParameter(userConnectionId);
		params.addParameter(buyoffId);
		params.addParameter(orderId);
		params.addParameter(serialId);
		params.addParameter(lotId);
		params.addParameter(operKey);
		params.addParameter(stepKey);
		params.addParameter(orderId);
		params.addParameter(operKey);
		params.addParameter(stepKey);
		params.addParameter(buyoffId);
		params.addParameter(serialId);
		params.addParameter(lotId);
		params.addParameter(userId);
		params.addParameter(userConnectionId);
		params.addParameter(buyoffId);
		params.addParameter(orderId);
		params.addParameter(serialId);
		params.addParameter(lotId);
		params.addParameter(operKey);
		params.addParameter(stepKey);
		params.addParameter(orderId);
		params.addParameter(operKey);
		params.addParameter(stepKey);
		params.addParameter(buyoffId);
		params.addParameter(serialId);
		params.addParameter(lotId);
		params.addParameter(userId);
		params.addParameter(userConnectionId);
		params.addParameter(buyoffId);
		params.addParameter(orderId);
		params.addParameter(serialId);
		params.addParameter(lotId);
		params.addParameter(operKey);
		params.addParameter(stepKey);

		list = eds.queryForList(selectSql.toString(), params);

		return list;				   
	} 

	//begin defect 1772
	public List selectSerialBuyoffListHistTab(String orderId){


		List list = null;
		StringBuilder selectSql = new StringBuilder().append(" SELECT BUYOFF_ID," +
				"OPER_NO, " +
				"STEP_NO," +
				"BUYOFF_TYPE," +
				"BUYOFF_TITLE, " +
				"SERIAL_NO," +
				"BUYOFF_STATUS," +
				"TIME_STAMP," +
				"USER_NAME," +
				"UPDT_USERID," +
				"PERCENT_COMPLETE," +
				"COMPLETE_QTY," +
				"BUYOFF_CERT," +
				"COMMENTS," +
				"OPTIONAL_FLAG," +
				"signoffs " +
				"from SFWID_BUYOFF_HIST_PW t " +
				"where ORDER_ID = ? ");

		ParameterHolder params = new ParameterHolder();	       
		params.addParameter(orderId);


		list = eds.queryForList(selectSql.toString(), params);

		return list;
	}
	//end defect 1772


	/* **************************************************************************
	 * 	DAO used for delivery package report.
	 * 	Report defined in calling methods.
	 * **************************************************************************/
	public List selectSerialBuyoffListHistReport(String orderId, String userConnection, String whereClause){

		if(whereClause != null) {
			whereClause = whereClause.replaceAll("\\(UPDT_USERID", "(B.UPDT_USERID").replaceAll("\\(TIME_STAMP", "(B.TIME_STAMP");
		}

		List list = null;
		StringBuilder selectSql = new StringBuilder().append(" SELECT * FROM (SELECT A.BUYOFF_ID, " +
				" A.OPER_NO, " + 
				" A.STEP_NO, "  +
				" A.BUYOFF_TYPE," + 
				" A.BUYOFF_TITLE, " + 
				" SERIAL_NO," +
				" CASE WHEN B.UCF_SRLOPBUYOFF_FLAG1 = 'P'       THEN 'PARTIAL' " +
				"	WHEN B.UCF_SRLOPBUYOFF_VCH1 IS NULL      THEN  B.BUYOFF_STATUS " +
				"	WHEN B.UCF_SRLOPBUYOFF_VCH1 = 'PARTIAL'  THEN  B.BUYOFF_STATUS " +
				"	ELSE 'Closed-'||B.UCF_SRLOPBUYOFF_VCH1 " +
				" END AS BUYOFF_STATUS, " + 
				" B.TIME_STAMP,  " +
				" SFFND_USER_NAME_GET(B.UPDT_USERID) AS USER_NAME, " +
				" B.UPDT_USERID, " +
				" (TO_CHAR(CAST(PERCENT_COMPLETE AS FLOAT) / 100)) AS PERCENT_COMPLETE, " +
				" TO_CHAR(CAST(B.COMPLETE_QTY AS INTEGER)) AS COMPLETE_QTY, " +
				" A.BUYOFF_CERT,  " + 
				"  B.COMMENTS," +
				" A.OPTIONAL_FLAG, " +
				" UCF_SRLOPBUYOFF_VCH4000_1 " +
				"  FROM SFWID_OPER_BUYOFF A, SFWID_SERIAL_OPER_BUYOFF B, SFWID_SERIAL_DESC S " +
				"  WHERE A.ORDER_ID = B.ORDER_ID " +
				"  AND A.OPER_KEY = B.OPER_KEY " + 
				"  AND A.STEP_KEY = B.STEP_KEY " +
				"  AND A.BUYOFF_ID = B.BUYOFF_ID " +

																			 "  AND B.ORDER_ID = S.ORDER_ID " +
																			 "  AND B.SERIAL_ID = S.SERIAL_ID" +
																			 "  AND B.LOT_ID = S.LOT_ID" +
				"  AND A.ORDER_ID = ? ");

		if(whereClause != null)
		{
			selectSql.append(" AND " + whereClause);
		}

		selectSql.append(" UNION ")
		.append(" SELECT A.BUYOFF_ID, " + 
				" A.OPER_NO, " + " A.STEP_NO, " + 
				" A.BUYOFF_TYPE," + 
				" A.BUYOFF_TITLE, " + 
				" SERIAL_NO," +
				" CASE WHEN B.UCF_SRLOPBUYOFF_FLAG1 = 'P'       THEN 'PARTIAL' " +
				"     	WHEN B.UCF_SRLOPBUYOFF_VCH1 = 'PARTIAL'  THEN  B.BUYOFF_STATUS " +
				"	    WHEN B.UCF_SRLOPBUYOFF_VCH1 IS NULL      THEN  B.BUYOFF_STATUS " +
				"	    ELSE 'Closed-'||B.UCF_SRLOPBUYOFF_VCH1 " +
				" END AS BUYOFF_STATUS, " +
				" B.TIME_STAMP, " +
				" SFFND_USER_NAME_GET(B.UPDT_USERID) AS USER_NAME," +
				" B.UPDT_USERID, " +
				" TO_CHAR(CAST(PERCENT_COMPLETE AS FLOAT) / 100) AS PERCENT_COMPLETE, " +
				" TO_CHAR(CAST(B.COMPLETE_QTY AS INTEGER)) AS COMPLETE_QTY, " +
				" A.BUYOFF_CERT," + 
				" B.COMMENTS," +
				" A.OPTIONAL_FLAG, " + 
				" UCF_SRLOPBUYOFF_VCH4000_1 " +
				"  FROM SFWID_OPER_BUYOFF A, SFWID_SERIAL_OPER_BUYOFF_HIST B , SFWID_SERIAL_DESC S" +
				"  WHERE A.ORDER_ID = B.ORDER_ID " +
				"  AND A.OPER_KEY = B.OPER_KEY " +
				"  AND A.STEP_KEY = B.STEP_KEY " +
				"  AND A.BUYOFF_ID = B.BUYOFF_ID " +
				"  AND B.ORDER_ID = S.ORDER_ID " +
				"  AND B.SERIAL_ID = S.SERIAL_ID" +
				"  AND B.LOT_ID = S.LOT_ID " +
				"  AND A.ORDER_ID = ? " );

		if(whereClause != null){
			selectSql.append(" AND " + whereClause);
		}

		selectSql.append(" ) ORDER BY OPER_NO, STEP_NO, DECODE(BUYOFF_TYPE, 'MFG',1, 'TECH',2, 'MFG2',3, ")
		.append(" 		   'TECH2',4, 'QA',5, 'INSP',6, 'CUST',7, 8), BUYOFF_ID, TIME_STAMP DESC, ")
		.append(" 		   DECODE(BUYOFF_STATUS, 'OPEN',2, 'PENDING',3, 1), ")
		.append("			   LENGTH(NVL(UCF_SRLOPBUYOFF_VCH4000_1,0)) DESC ");

		ParameterHolder params = new ParameterHolder();	       
		params.addParameter(orderId);
		params.addParameter(orderId);

		//System.out.println("selectSql.toString() = " + selectSql.toString());
		//System.out.println("params =" + params);
		list = eds.queryForList(selectSql.toString(), params);

		return list;				   
	} 

	public List selectSerialBuyoffListHistTab(String orderId, String calledFrom, String whereClauseFromClient,Number operKey,Number stepKey, String refID ){

		List sfwidBuyoffHistPWList = null; 
		StringBuilder selectSql = new StringBuilder().append(" SELECT BUYOFF_ID, " +
				" OPER_NO,  " +
				" STEP_NO, " +
				" BUYOFF_TYPE, " +
				" BUYOFF_TITLE, " + 
				" SERIAL_NO, " +
				" BUYOFF_STATUS, " +
				" TIME_STAMP, " +
				" USER_NAME, " +
				" UPDT_USERID, " +
				" PERCENT_COMPLETE, " +
				" COMPLETE_QTY, " +
				" BUYOFF_CERT, " +
				" COMMENTS, " +
				" OPTIONAL_FLAG, " +
				" signoffs AS UCF_SRLOPBUYOFF_VCH4000_1" +
				" from SFWID_BUYOFF_HIST_PW B" +
				" where ORDER_ID = ? " );


		if(StringUtils.isNotBlank(whereClauseFromClient)){
			selectSql.append(" AND " + whereClauseFromClient);
		}

		if (operKey!=null){
			selectSql.append(" AND OPER_KEY = ? ");
		}

		if (stepKey!=null){
			selectSql.append(" AND STEP_KEY = ? ");
		}

		if (StringUtils.isNotBlank(refID)){
			selectSql.append(" AND EXISTS (SELECT 1 " +
					" FROM SFWID_OPER_BUYOFF BB " + 
					" WHERE BB.ORDER_ID = B.ORDER_ID " + 
					" AND BB.OPER_KEY = B.OPER_KEY " + 
					" AND BB.STEP_KEY = B.STEP_KEY " + 
					" AND BB.BUYOFF_ID = B.BUYOFF_ID " + 
					" AND BB.REF_ID = ? )");
		}

		selectSql.append(" ORDER BY OPER_NO, " +
				" STEP_NO, " +
				" DECODE( BUYOFF_TYPE, 'MFG',1, " + 
				" 'TECH',2, "+
				" 'MFG2',3,  "+
				" 'TECH2',4,  "+ 
				" 'QA',5,  "+ 
				" 'INSP',6,  "+ 
				" 'CUST',7, 8),  "+ 
				" BUYOFF_ID, "+ 
				" TIME_STAMP DESC, " + 
				" DECODE(BUYOFF_STATUS, 'OPEN',2, 'PENDING',3, 1), " + 
				" LENGTH(NVL(signoffs,0)) DESC ");


		ParameterHolder params = new ParameterHolder();	       
		params.addParameter(orderId);

		if (operKey!=null){
			params.addParameter(operKey);
		}

		if (stepKey!=null){
			params.addParameter(stepKey);
		}

		if (StringUtils.isNotBlank(refID)){
			params.addParameter(refID);
		}

		//System.out.println("selectSql.toString() = " + selectSql.toString());
		//System.out.println("params =" + params);
		sfwidBuyoffHistPWList = eds.queryForList(selectSql.toString(), params);

		return sfwidBuyoffHistPWList;

	}
	
	//begin defect 1994
	public List selectSerialBuyoffListHistTabForDisplayTab(String orderId, String calledFrom, String whereClauseFromClient,Number operKey,Number stepKey, String serialNo ){

		List sfwidBuyoffHistPWList = null; 
		StringBuilder selectSql = new StringBuilder().append(" SELECT BUYOFF_ID, " +
				" OPER_NO,  " +
				" STEP_NO, " +
				" BUYOFF_TYPE, " +
				" BUYOFF_TITLE, " + 
				" SERIAL_NO, " +
				" BUYOFF_STATUS, " +
				" TIME_STAMP, " +
				" USER_NAME AS USERNAME, " +
				" UPDT_USERID, " +
				" PERCENT_COMPLETE AS PERCENT_QTY_COMPLETE, " +
				" COMPLETE_QTY, " +
				" BUYOFF_CERT, " +
				" COMMENTS, " +
				" OPTIONAL_FLAG, " +
				" signoffs AS UCF_SRLOPBUYOFF_VCH4000_1" +
				" from SFWID_BUYOFF_HIST_PW B" +
				" where ORDER_ID = ? " );


		if(StringUtils.isNotBlank(whereClauseFromClient)){
			selectSql.append(" AND " + whereClauseFromClient);
		}

		if (operKey!=null){
			selectSql.append(" AND OPER_KEY = ? ");
		}

		if (stepKey!=null){
			selectSql.append(" AND STEP_KEY = ? ");
		}
		
		if (serialNo!=null){
			selectSql.append(" AND SERIAL_NO = ? ");
		}
/*
		if (StringUtils.isNotBlank(refID)){
			selectSql.append(" AND EXISTS (SELECT 1 " +
					" FROM SFWID_OPER_BUYOFF BB " + 
					" WHERE BB.ORDER_ID = B.ORDER_ID " + 
					" AND BB.OPER_KEY = B.OPER_KEY " + 
					" AND BB.STEP_KEY = B.STEP_KEY " + 
					" AND BB.BUYOFF_ID = B.BUYOFF_ID " + 
					" AND BB.REF_ID = ? )");
		}
*/
		selectSql.append(" ORDER BY OPER_NO, " +
				" STEP_NO, " +
				" DECODE( BUYOFF_TYPE, 'MFG',1, " + 
				" 'TECH',2, "+
				" 'MFG2',3,  "+
				" 'TECH2',4,  "+ 
				" 'QA',5,  "+ 
				" 'INSP',6,  "+ 
				" 'CUST',7, 8),  "+ 
				" BUYOFF_ID, "+ 
				" TIME_STAMP DESC, " + 
				" DECODE(BUYOFF_STATUS, 'OPEN',2, 'PENDING',3, 1), " + 
				" LENGTH(NVL(signoffs,0)) DESC ");


		ParameterHolder params = new ParameterHolder();	       
		params.addParameter(orderId);

		if (operKey!=null){
			params.addParameter(operKey);
		}

		if (stepKey!=null){
			params.addParameter(stepKey);
		}
		
		if (serialNo!=null){
			params.addParameter(serialNo);
		}
/*
		if (StringUtils.isNotBlank(refID)){
			params.addParameter(refID);
		}
*/
		//System.out.println("selectSql.toString() = " + selectSql.toString());
		//System.out.println("params =" + params);
		sfwidBuyoffHistPWList = eds.queryForList(selectSql.toString(), params);

		return sfwidBuyoffHistPWList;

	}


	//Begin Defect 712
	public int updatePartialBuyoffPW(String orderId,
			Number operKey,
			Number stepKey,
			String buyoffId,
			Number percentComplete,
			String buyoffComment,
			String secondLoginUser,
			String serialId,
			String lotId){

		StringBuilder updateSql = new StringBuilder().append("UPDATE SFWID_SERIAL_OPER_BUYOFF ")
				.append("   SET UCF_SRLOPBUYOFF_FLAG1 = 'P', ")	
				.append("       UCF_SRLOPBUYOFF_VCH1 = 'PARTIAL', ")
				.append("       PERCENT_COMPLETE = ?, ")	
				.append("       COMMENTS = ?,")
				.append("       UPDT_USERID = ?,")
				.append("       TIME_STAMP = SYSDATE")
				.append(" WHERE ORDER_ID = ? ")
				.append("   AND OPER_KEY = ? ")
				.append("   AND STEP_KEY = ? ")
				.append("   AND BUYOFF_ID = ? ")
				.append("   AND SERIAL_ID = ? ")
				.append("   AND LOT_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(percentComplete);
		parameters.addParameter(buyoffComment);
		parameters.addParameter(secondLoginUser);
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(buyoffId);
		parameters.addParameter(serialId);
		parameters.addParameter(lotId);
		Integer updCount = eds.update(updateSql.toString(), parameters);

		//System.out.println("BuyoffDaoPW.updatePartialBuyoffPW updateSql = " + updateSql);
		//System.out.println("BuyoffDaoPW.updatePartialBuyoffPW parameters = " + parameters);
		//System.out.println("BuyoffDaoPW.updatePartialBuyoffPW updCount = " + updCount);

		return updCount;
	}

	//defect 1772 begin
	public void insertBuyoffHistory(String orderId,
			Number operKey,
			Number stepKey,
			String buyoffId,
			String buyoffStatus,
			String percentComplete,
			String buyoffComment,
			String serialNo,
			String operNo,
			String stepNo,
			String buyoffTitle,
			String buyoffType,
			String buyoffCert,
			String optionalFlag,
			String userClockID,
			String fullName,
			String signoffs){


		//System.out.println("in BuyoffDaoPW.insertBuyoffHistory");

		StringBuilder insertSql = new StringBuilder().append("INSERT INTO  SFWID_BUYOFF_HIST_PW (   BUYOFF_HIST_PK,")
				.append("   ORDER_ID,")
				.append("   OPER_KEY , ")
				.append("   OPER_NO , ")
				.append("   STEP_KEY,")
				.append("   STEP_NO,")				
				.append("   BUYOFF_ID, ")
				.append("   SERIAL_NO, ")
				.append("   BUYOFF_STATUS,  ")
				.append("   PERCENT_COMPLETE, ")	
				.append("   COMMENTS,")
				.append("   BUYOFF_TITLE,")
				.append("   BUYOFF_TYPE,")
				.append("   BUYOFF_CERT,")
				.append("   OPTIONAL_FLAG,")
				.append("   UPDT_USERID,")
				.append("   USER_NAME, ")
				.append("   SIGNOFFS, ")
				.append("   TIME_STAMP ")
				.append(") VALUES ( ")
				.append("    SFWID_BUYOFF_HIST_PW_SEQ.NEXTVAL, ")
				.append("    ?, ")
				.append("    ?, ")
				.append("    ?, ")
				.append("    ?, ")
				.append("    ?, ")
				.append("    ?, ")
				.append("    ?, ")
				.append("    ?, ")
				.append("    ?, ")
				.append("    ?, ")
				.append("    ?, ")
				.append("    ?, ")
				.append("    ?, ")
				.append("    ?, ")
				.append("    ?, ")
				.append("    ?, ")
				.append("    ?, ")
				.append("  SYSDATE) ");


		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(operNo);
		parameters.addParameter(stepKey);
		parameters.addParameter(stepNo);
		parameters.addParameter(buyoffId);
		parameters.addParameter(serialNo);
		parameters.addParameter(buyoffStatus);
		parameters.addParameter(percentComplete);
		parameters.addParameter(buyoffComment);
		parameters.addParameter(buyoffTitle);
		parameters.addParameter(buyoffType);
		parameters.addParameter(buyoffCert);
		parameters.addParameter(optionalFlag);
		parameters.addParameter(userClockID);
		parameters.addParameter(fullName);
		parameters.addParameter(signoffs);

		//System.out.println(" in BuyoffDaoPW.insertBuyoffHistory insertSql =" + insertSql.toString());
		//System.out.println(" in BuyoffDaoPW.insertBuyoffHistory parameters = " + parameters);

		eds.insert(insertSql.toString(),parameters);

	}
	//defect 1772 end

	public int updatePartialBuyoffFlag(String orderId,
			Number operKey,
			Number stepKey,
			String buyoffId)
	{
		StringBuilder updateSql = new StringBuilder().append("UPDATE SFWID_SERIAL_OPER_BUYOFF")
				.append("   SET UCF_SRLOPBUYOFF_FLAG1 = null")	
				.append(" WHERE ORDER_ID = ?")
				.append("   AND OPER_KEY = ?")
				.append("   AND STEP_KEY = ?")
				.append("   AND BUYOFF_ID = ?") ;


		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(buyoffId);

		Integer updCount = eds.update(updateSql.toString(), parameters);
		return updCount;
	}

	public String getPartialFlag(String orderId,
			Number operKey,
			Number stepKey,
			String buyoffId)
	{
		String partialFlag = null;

		try
		{
			StringBuffer selectSql = new StringBuffer().append("SELECT UCF_SRLOPBUYOFF_FLAG1")
					.append("  FROM SFWID_SERIAL_OPER_BUYOFF")
					.append(" WHERE ORDER_ID = ?")
					.append("   AND OPER_KEY = ?")
					.append("   AND STEP_KEY = ?")
					.append("   AND BUYOFF_ID = ?");

			ParameterHolder parameters = new ParameterHolder();
			parameters.addParameter(orderId);
			parameters.addParameter(operKey);
			parameters.addParameter(stepKey);
			parameters.addParameter(buyoffId);

			partialFlag = eds.queryForString(selectSql.toString(), parameters);
			return partialFlag;
		}
		catch(EmptyResultDataAccessException e)
		{
			return partialFlag;
		}
	}

	public int getPartialFlagCount(String orderId,
			Number operKey,
			Number stepKey,
			String buyoffId)
	{
		int partialCount = 0;

		try
		{
			StringBuffer selectSql = new StringBuffer().append("SELECT count(*)")
					.append("  FROM SFWID_SERIAL_OPER_BUYOFF")
					.append(" WHERE ORDER_ID = ?")
					.append("   AND OPER_KEY = ?")
					.append("   AND STEP_KEY = ?")
					.append("   AND BUYOFF_ID = ?")
					.append("   AND UCF_SRLOPBUYOFF_FLAG1 = 'P'");

			ParameterHolder parameters = new ParameterHolder();
			parameters.addParameter(orderId);
			parameters.addParameter(operKey);
			parameters.addParameter(stepKey);
			parameters.addParameter(buyoffId);

			partialCount = eds.queryForInt(selectSql.toString(), parameters);
			return partialCount;
		}
		catch(EmptyResultDataAccessException e)
		{
			return partialCount;
		}
	}
	public Map selectPartialBuyoffSigneeInfo(String orderId,
			Number operKey,
			Number stepKey,
			String buyoffId) 
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT UPDT_USERID, TIME_STAMP")	 
				.append("  FROM SFWID_SERIAL_OPER_BUYOFF")
				.append(" WHERE ORDER_ID = ?")
				.append("   AND OPER_KEY = ?")
				.append("   AND STEP_KEY = ?")
				.append("   AND BUYOFF_ID = ?")
				.append("   AND UCF_SRLOPBUYOFF_FLAG1 = 'P'")
				.append("   AND ROWNUM = 1");    //Defect 1258
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey); 
		parameters.addParameter(stepKey);
		parameters.addParameter(buyoffId);

		Map buyoffUserMap = eds.queryForMap(selectSql.toString(), parameters);

		return buyoffUserMap;
	}

	public int updateOrderNotesUserid(String orderId,
			String noteId,    
			String secondLoginUser)


	{

		StringBuilder updateSql = new StringBuilder().append("UPDATE SFWID_ORDER_NOTES")
				.append("   SET UPDT_USERID = ?")
				.append(" WHERE ORDER_ID = ? ")
				.append("   AND NOTE_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(secondLoginUser);
		parameters.addParameter(orderId);
		parameters.addParameter(noteId);


		Integer updCount = eds.update(updateSql.toString(), parameters);
		return updCount;
	}

	// Defect 973
	public String selectOrderNoteId(String orderId, Number operKey, Number stepKey, String buyoffId)
	{
		String noteId = null;

		StringBuffer selectSql = new StringBuffer().append("SELECT NOTE_ID ")
				.append("  FROM (SELECT NOTE_ID, TIME_STAMP ")
				.append("          FROM SFWID_ORDER_NOTES ")
				.append("         WHERE ORDER_ID = ? ")
				.append("           AND OPER_KEY = ? ")
				.append("           AND STEP_KEY = ? ")
				.append("           AND UCF_ORDER_NOTE_VCH1 = ? ")
				.append("        ORDER BY TIME_STAMP DESC) ")
				.append(" WHERE ROWNUM = 1 ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(buyoffId);

		noteId =  eds.queryForString(selectSql.toString(), parameters);

		return noteId;
	}

	public List selectAddtionalSignoffs4DPReport(String orderId,
			Number operKey, 
			/*      Number stepKey,*/
			String buyoffId)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT DISTINCT B.UCF_SRLOPBUYOFF_VCH4000_1, A.OPER_NO, A.STEP_NO, A.BUYOFF_TITLE, A.BUYOFF_TYPE ")
				.append("   FROM SFWID_OPER_BUYOFF A, SFWID_SERIAL_OPER_BUYOFF B ")
				.append("  WHERE A.ORDER_ID = B.ORDER_ID")
				.append("    AND A.BUYOFF_ID = B.BUYOFF_ID")
				.append("    AND A.OPER_KEY = B.OPER_KEY")
				.append("    AND A.STEP_KEY = B.STEP_KEY")
				.append("    AND A.ORDER_ID = ? ")
				.append("    AND A.OPER_KEY = ? ")
				.append("    AND B.UCF_SRLOPBUYOFF_VCH4000_1 IS NOT NULL ");

		//Defect 849
		if(buyoffId != null)
		{
			selectSql.append("    AND B.BUYOFF_ID = ? ");
		}
		//End Defect849

		//Defect 849 - Same "Order by" used on Buyoff tab
		selectSql.append(" ORDER BY OPER_NO, NVL(STEP_NO, '...'), DECODE(BUYOFF_TYPE, ")
		.append(" 			'MFG',1, 'TECH',2, 'MFG2',3, 'TECH2',4, 'QA',5, ")
		.append(" 			'INSP',6, 'CUST',7, 8) ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		//	parameters.addParameter(stepKey);
		//parameters.addParameter(buyoffId); //Defect 849
		if(buyoffId != null) parameters.addParameter(buyoffId);  //Defect 849

		return eds.queryForList(selectSql.toString(), parameters);
	}
	// Defect 884
	public Map insertBadgeSwipeLog(String orderId, 
			String pwOrderNo,
			Number operKey, 
			Number stepKey,
			String operNo,
			String stepNo,
			String buyoffId,
			String buyoffType,
			String buyoffStatus,
			String swipedBadgeId,
			String swipedUserid,
			String client,
			String errorMsg) 

	{
		String histId = null;

		StringBuffer selectSql = new StringBuffer().append("SELECT PWUST_HIST_SEQ.NEXTVAL ")
				.append("  FROM DUAL ");

		histId =  eds.queryForString(selectSql.toString(), null);

		StringBuffer insert = new StringBuffer()
				.append("INSERT INTO PWUST_BADGE_SWIPE_LOG  ")
				.append("  (HIST_ID, ORDER_ID, PW_ORDER_NO, OPER_KEY, STEP_KEY, OPER_NO, STEP_NO, BUYOFF_ID, ")
				.append("   BUYOFF_TYPE, BUYOFF_STATUS, SWIPED_BADGE_ID, SWIPED_USERID, SESSION_USERID, CLIENT, ERROR_TEXT) ")
				.append("VALUES ")
				.append("  (?, ?, (select pw_order_no from pwust_sfwid_order_desc where order_id = ?), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(histId);
		params.addParameter(orderId);
		params.addParameter(orderId); //params.addParameter(pwOrderNo);
		params.addParameter(operKey);
		params.addParameter(stepKey);
		params.addParameter(operNo);
		params.addParameter(stepNo);
		params.addParameter(buyoffId);
		params.addParameter(buyoffType);
		params.addParameter(buyoffStatus);
		params.addParameter(swipedBadgeId);
		params.addParameter(swipedUserid);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(client);
		params.addParameter(errorMsg);

		eds.insert(insert.toString(), params);

		Map returnMap = new HashMap();
		returnMap.put("UCF_SRLOPBUYOFF_VCH3", histId);

		return returnMap;
	}

	// Defect 884
	public int updateBadgeSwipeLog(String histId, String signoffUserid)

	{
		StringBuilder updateSql = new StringBuilder().append("UPDATE ")
				.append(" PWUST_BADGE_SWIPE_LOG ")
				.append(" SET SIGNOFF_USERID = ? ")							
				.append(" WHERE HIST_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(signoffUserid);
		parameters.addParameter(histId);

		Integer updCount = eds.update(updateSql.toString(), parameters);
		return updCount;
	}

	// Defect 732
	public void insertBuyoffAcceptForTrainee(String orderId,
			Number operationKey,
			Number stepKey,
			String lotId,
			String serialId,
			String buyoffId,
			String signoffUserid,
			String comments){

		StringBuffer insertSql = new StringBuffer().append("INSERT INTO SFWID_SERIAL_OPER_BUYOFF_HIST (")
				.append("    HIST_ID, ")
				.append("    HIST_USERID, ")
				.append("    ORDER_ID, ")
				.append("    OPER_KEY, ")
				.append("    STEP_KEY, ")
				.append("    LOT_ID, ")
				.append("    SERIAL_ID, ")
				.append("    BUYOFF_ID, ")
				.append("    UPDT_USERID, ")
				.append("    TIME_STAMP, ")
				.append("    LAST_ACTION, ")
				.append("    BUYOFF_STATUS, ")
				.append("    PERCENT_COMPLETE, ")
				.append("    COMMENTS ")
				.append(") VALUES ( ")
				.append("    sfwid_hist_seq.nextval, ")
				.append("    ?, ")
				.append("    ?, ")
				.append("    ?, ")
				.append("    ?, ")
				.append("    ?, ")
				.append("    ?, ")
				.append("    ?, ")
				.append("    ?, ")
				//.append(eds.getTimestampFunction() + ", ")   //DEFECT 1994
				.append(" SYSDATE, ")   //defect 1994
				.append("    ?,")
				.append("    ?, ")
				.append("  	 10000, ")
				.append("    ? )");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(signoffUserid);
		parameters.addParameter(orderId);
		parameters.addParameter(operationKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(lotId);
		parameters.addParameter(serialId);
		parameters.addParameter(buyoffId);
		parameters.addParameter(signoffUserid);
		parameters.addParameter("INSERTED");
		parameters.addParameter("TRAINING");
		parameters.addParameter(comments);


		eds.insert(insertSql.toString(), parameters);

		return;
	}	

	public List selectBuyoffTabList(String orderId, String orderType, String userid, String whereClauseFromClient){

		List list = null;
		StringBuffer selectSql = new StringBuffer().append("SELECT T.BUYOFF_ID,")
				.append("       T.OPER_NO,")
				.append("       T.STEP_NO,")
				.append("       T.BUYOFF_TYPE,")
				.append("       T.BUYOFF_CERT,")
				.append("       T.OPTIONAL_FLAG,")
				.append("       T.CROSS_ORDER_FLAG,")
				.append("       T.UPDT_USERID,")
				.append("       T.TIME_STAMP,")
				.append("       T.USER_NAME,")
				.append("       CASE WHEN T.SLIDE_EMBEDDED_REF_ID IS NULL THEN NULL")
				.append("            WHEN SFFND_MM_SECURITY_GROUP_OK(T.SLIDE_ID, ?) <> 'Y' THEN NULL")
				.append("            ELSE 'Image'")
				.append("       END AS IMAGE,")
				.append("       T.SLIDE_ID,")
				.append("       T.SLIDE_EMBEDDED_REF_ID,")
				.append("       T.BUYOFF_TITLE,")
				.append("       T.BUYOFF_STATUS,")
				.append("       T.OPER_KEY,")
				.append("       T.STEP_KEY,")
				.append("       T.COMMENTS")
				.append(" FROM PWUST_OPER_BUYOFF_TAB_V T ") //defect 1823
				.append(" WHERE T.ORDER_ID = ? ");
		;

		if (StringUtils.equalsIgnoreCase(orderType, "REPAIR") || StringUtils.equalsIgnoreCase(orderType, "OVERHAUL")){
			//start defect 1823
			//		selectSql.append(" AND EXISTS ( SELECT 1")
			//				.append("               FROM SFOR_SFWID_OPER_SUBJECT TT")
			//				.append("               WHERE TT.ORDER_ID(+)      = T.ORDER_ID")
			//				.append("               AND   TT.OPER_KEY (+)       = T.OPER_KEY")
			//				.append("               AND   TT.INCLUDED_FLAG(+)   = ? )");

            //notes - same as the operation tab to determine which operations are included 1823
			selectSql.append(" AND EXISTS ( SELECT a.oper_no ") //REVISED 1823
			.append("               from sfwid_oper_desc a left outer join sffnd_work_loc_def loc on a.asgnd_location_id = loc.location_id")
			.append("               left outer join sffnd_work_dept_def dept on a.asgnd_department_id = dept.department_id and a.asgnd_location_id =  dept.location_id")
			.append("               left outer join sffnd_work_center_def center on a.asgnd_center_id= center.center_id and a.asgnd_location_id = center.location_id and a.asgnd_department_id = center.department_id,")
			.append("               sfor_sfwid_oper_subject c")
			.append("               where a.order_id = T.ORDER_ID")
			.append("               and step_key = -1")
			.append("               and a.order_id = c.order_id")
			.append("               and a.oper_key = c.oper_key")
			.append("               AND A.OPER_KEY = T.OPER_KEY ") //REVISED 1823
			.append("               and a.included = ? ) ");
			//end defect 1823
		}
		if(StringUtils.isNotBlank(whereClauseFromClient)){
			selectSql.append(" AND " + whereClauseFromClient);
		}


		selectSql.append(" ORDER BY T.OPER_NO,")
		.append("                   NVL(T.STEP_NO, '...'),")
		.append("                   DECODE(BUYOFF_TYPE, 'MFG', 1,")
		.append("                                       'TECH', 2,")
		.append("                                       'MFG2', 3,")
		.append("                                       'TECH2', 4,")
		.append("                                       'QA', 5,")
		.append("                                       'INSP', 6,")
		.append("                                       'CUST', 7,")
		.append("                                        8),")
		.append("                   T.BUYOFF_ID");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(userid);
		params.addParameter(orderId);
		if (StringUtils.equalsIgnoreCase(orderType, "REPAIR") || StringUtils.equalsIgnoreCase(orderType, "OVERHAUL")){
			params.addParameter("INCLUDED");
		}

		list = eds.queryForList(selectSql.toString(), params);
		return list;

	}


	//Defect 938
	//Defect 973
	public int updateOrderNotesTimestamp(String orderId,
			Number operKey,
			Number stepKey,
			String noteId,
			String buyoffId,
			String serialId,
			String lotId)   //1258


	{

		StringBuilder updateSql = new StringBuilder().append(" UPDATE SFWID_ORDER_NOTES T")  // primary key (ORDER_ID, NOTE_ID)
				.append(" SET T.TIME_STAMP =  ( SELECT B.TIME_STAMP")
				.append("                       FROM SFWID_SERIAL_OPER_BUYOFF B") //primary key (ORDER_ID, OPER_KEY, STEP_KEY, LOT_ID, SERIAL_ID, BUYOFF_ID)
				.append("                       WHERE B.ORDER_ID  = ? ")
				.append("                       AND B.OPER_KEY    = ? ")
				.append("                       AND B.STEP_KEY    = ? ")
				.append("                       AND B.LOT_ID      = ? ")
				.append("                       AND B.SERIAL_ID   = ? ")
				.append("                       AND B.BUYOFF_ID   = ? )")
				.append(" WHERE  T.ORDER_ID = ? ")
				.append(" AND T.NOTE_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(lotId);
		parameters.addParameter(serialId);
		parameters.addParameter(buyoffId);

		parameters.addParameter(orderId);
		parameters.addParameter(noteId);

		int updCount = eds.update(updateSql.toString(), parameters);
		if (updCount!=1){
			//System.out.println("BuyoffDaoPW.updateOrderNotesTimestamp updateSql = " + updateSql);
			//System.out.println("BuyoffDaoPW.updateOrderNotesTimestamp parameters = " + parameters);
			//System.out.println("BuyoffDaoPW.updateOrderNotesTimestamp updCount = " + updCount);
		}

		return updCount;
	}

	// Defect 860 - 20181113
	public String getNANDFlag(String orderId,
			Number operKey,
			Number stepKey,
			String buyoffId)
	{
		String naNDFlag = null;

		try
		{
			StringBuffer selectSql = new StringBuffer().append("SELECT UCF_SRLOPBUYOFF_VCH1")
					.append("  FROM SFWID_SERIAL_OPER_BUYOFF")
					.append(" WHERE ORDER_ID = ?")
					.append("   AND OPER_KEY = ?")
					.append("   AND STEP_KEY = ?")
					.append("   AND BUYOFF_ID = ?")
					.append("   AND UCF_SRLOPBUYOFF_VCH1 IN ('N/A', 'N/D') ")
					.append("   AND BUYOFF_STATUS = 'ACCEPT' ");

			ParameterHolder parameters = new ParameterHolder();
			parameters.addParameter(orderId);
			parameters.addParameter(operKey);
			parameters.addParameter(stepKey);
			parameters.addParameter(buyoffId);

			naNDFlag = eds.queryForString(selectSql.toString(), parameters);
			return naNDFlag;
		}
		catch(EmptyResultDataAccessException e)
		{
			return naNDFlag;
		}
	}
	// Defect 860 - 20181113
	public Map isNANDBuyoff(String orderId,
			Number operKey,
			Number stepKey,
			String buyoffId){

		Map retMap = new HashMap();

		StringBuffer selectSql = new StringBuffer().append("SELECT UCF_SRLOPBUYOFF_VCH1")
				.append("  FROM SFWID_SERIAL_OPER_BUYOFF")
				.append(" WHERE ORDER_ID = ?")
				.append("   AND OPER_KEY = ?")
				.append("   AND STEP_KEY = ?")
				.append("   AND BUYOFF_ID = ?")
				.append("   AND UCF_SRLOPBUYOFF_VCH1 IN ('N/A', 'N/D') ")
				.append("   AND BUYOFF_STATUS = 'ACCEPT' ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(buyoffId);

		Map sfwidSerialOperBuyoffMap=null;
		List sfwidSerialOperBuyoffList = eds.queryForList(selectSql.toString(), parameters);

		//System.out.println("BuyoffDaoPW.isNANDBuyoff selectSql = " + selectSql);
		//System.out.println("BuyoffDaoPW.isNANDBuyoff parameters = " + parameters);
		//System.out.println("BuyoffDaoPW.isNANDBuyoff sfwidSerialOperBuyoffList = " + sfwidSerialOperBuyoffList);

		if (sfwidSerialOperBuyoffList!=null && !sfwidSerialOperBuyoffList.isEmpty()){
			retMap.put("NAND", "Y");

		}else{
			retMap.put("NAND", "N");
		}

		return retMap;
	}

	// Defect 1761
	public boolean checkNANDBuyoff(String orderId,
			Number operKey,
			Number stepKey,
			String buyoffId,
			String serialId,
			String lotId)
	{
		boolean isNAND = false;

		StringBuffer selectSql = new StringBuffer().append("SELECT COUNT(*) ")
				.append("  FROM SFWID_SERIAL_OPER_BUYOFF")
				.append(" WHERE ORDER_ID = ?")
				.append("   AND OPER_KEY = ?")
				.append("   AND STEP_KEY = ?")
				.append("   AND BUYOFF_ID = ?")
				.append("   AND SERIAL_ID = ?")
				.append("   AND LOT_ID = ?")
				.append("   AND UCF_SRLOPBUYOFF_VCH1 IN ('N/A', 'N/D') ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(buyoffId);
		parameters.addParameter(serialId);
		parameters.addParameter(lotId);

		int count = eds.queryForInt(selectSql.toString(), parameters);

		if (count > 0){
			isNAND = true;
		}

		return isNAND;
	}

	// Defect 947
	public boolean clearSfwidSerialOperBuyoffUCFs( String orderId,
			Number operKey,
			Number stepKey,
			String buyoffId,
			String serialId,
			String lotId){
		boolean ucfsCleared = false;

		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFWID_SERIAL_OPER_BUYOFF ")
				.append("    SET UCF_SRLOPBUYOFF_FLAG1 = null, ")
				.append("        UCF_SRLOPBUYOFF_VCH1 = null, ")
				.append("        UCF_SRLOPBUYOFF_VCH2 = null, ")
				.append("        UCF_SRLOPBUYOFF_VCH10 = null, ")
				.append("        UCF_SRLOPBUYOFF_VCH4000_1 = null ")
				.append("  WHERE ORDER_ID = ? ")
				.append("    AND OPER_KEY = ? ")
				.append("    AND STEP_KEY = ? ")
				.append("    AND BUYOFF_ID = ? ")
				.append("    AND SERIAL_ID = ? ")
				.append("    AND LOT_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(buyoffId);
		parameters.addParameter(serialId);
		parameters.addParameter(lotId);

		Integer updCount = eds.update(updateSql.toString(), parameters);
		if(updCount > 0)
		{
			ucfsCleared = true;
		}

		return ucfsCleared;
	}

	// Defect 947
	public Integer deleteReopenHistory(String orderId,
			Number operKey,
			Number stepKey,
			String buyoffId,
			String serialId,
			String lotId)
	{
		StringBuffer deleteSql = new StringBuffer().append(" DELETE FROM SFWID_SERIAL_OPER_BUYOFF_HIST " )
				.append("  WHERE HIST_ID = (select hist_id ")
				.append("                     FROM (SELECT * ")
				.append("                             FROM SFWID_SERIAL_OPER_BUYOFF_HIST ")
				.append("                            WHERE ORDER_ID = ? ")
				.append("                              AND OPER_KEY = ? ")
				.append("                              AND STEP_KEY = ? ")
				.append("                              AND BUYOFF_ID = ? ")
				.append("                              AND SERIAL_ID = ? ")
				.append("                              AND LOT_ID = ? ")
				.append("                        ORDER BY TIME_STAMP DESC) ")
				.append("                    WHERE ROWNUM = 1 ")
				.append("                      AND BUYOFF_STATUS = 'REOPEN') ");


		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(buyoffId);
		parameters.addParameter(serialId);
		parameters.addParameter(lotId);

		int numOfRowsDeleted = eds.delete(deleteSql.toString(),parameters);
		return numOfRowsDeleted; 
	}

	// Defect 860 - 20181212
	public  int selectBuyoffStatus(String orderId,
			Number operKey, 
			Number stepKey,
			String buyoffId,
			String refId)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT COUNT(BUYOFF_STATUS) ")
				.append("   FROM SFWID_OPER_BUYOFF A, ")
				.append("        SFWID_SERIAL_OPER_BUYOFF B ")
				.append("  WHERE A.ORDER_ID = B.ORDER_ID ")
				.append("    AND A.OPER_KEY = B.OPER_KEY ")
				.append("    AND A.STEP_KEY = B.STEP_KEY ")
				.append("    AND A.BUYOFF_ID = B.BUYOFF_ID ")
				.append("    AND A.ORDER_ID = ? ")
				.append("    AND A.OPER_KEY = ? ")
				.append("    AND A.STEP_KEY = ? ")
				.append("    AND A.BUYOFF_ID = ? ")
				.append("    AND A.REF_ID = ? ")
				.append("    AND BUYOFF_STATUS = 'ACCEPT' ");  //1258 

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(buyoffId);
		parameters.addParameter(refId);


		int count = eds.queryForInt(selectSql.toString(), parameters);   ///1258

		return count; //1258
	}	


	//Defect 1258
	public String getSerialId(String orderId, String buyoffId, Number operKey, Number stepKey, String serialNo)
	{
		String serialId = "";

		StringBuffer selectSql = new StringBuffer().append("SELECT S.SERIAL_ID")
				.append("  FROM SFWID_SERIAL_OPER_BUYOFF B,")
				.append("       SFWID_SERIAL_DESC S")
				.append(" WHERE B.ORDER_ID = S.ORDER_ID")
				.append("   AND B.SERIAL_ID = S.SERIAL_ID")
				.append("   AND B.ORDER_ID = ?")
				.append("   AND B.BUYOFF_ID = ?")
				.append("   AND B.OPER_KEY = ?")
				.append("   AND B.STEP_KEY = ?")
				.append("   AND S.SERIAL_NO = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(buyoffId);
		params.addParameter(operKey);
		params.addParameter(stepKey);
		params.addParameter(serialNo);

		try
		{
			serialId = eds.queryForString(selectSql.toString(), params);
		}
		catch(Exception e)
		{
			// Do nothing
		}

		return serialId;
	}

	public Map getSerialLotId(String orderId, String buyoffId, Number operKey, Number stepKey, String serialNo)
	{

		StringBuffer selectSql = new StringBuffer().append("SELECT S.SERIAL_ID, S.LOT_ID")
				.append("  FROM SFWID_SERIAL_OPER_BUYOFF B,")
				.append("       SFWID_SERIAL_DESC S")
				.append(" WHERE B.ORDER_ID = S.ORDER_ID")
				.append("   AND B.SERIAL_ID = S.SERIAL_ID")
				.append("   AND B.ORDER_ID = ?")
				.append("   AND B.BUYOFF_ID = ?")
				.append("   AND B.OPER_KEY = ?")
				.append("   AND B.STEP_KEY = ?")
				.append("   AND S.SERIAL_NO = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(buyoffId);
		params.addParameter(operKey);
		params.addParameter(stepKey);
		params.addParameter(serialNo);


		Map serialLotIdMap = eds.queryForMap(selectSql.toString(), params);

		return serialLotIdMap;
	}
	// Defect 1761 
	public int updateSerialSkipDatColToPending(String orderId,
			Number operKey,
			String buyoffId,
			String serialId,
			String lotId)
	{
		StringBuffer updateSQL = new StringBuffer().append(" UPDATE SFWID_SERIAL_OPER_DAT_COL T ")
				.append("    SET T.DAT_COL_STATUS = 'PENDING', ")
				.append("        T.DCVALUE = NULL ")
				.append("  WHERE (T.ORDER_ID, T.OPER_KEY, T.STEP_KEY, T.DAT_COL_ID, T.SERIAL_ID, T.LOT_ID) IN ")
				.append("        (SELECT B.ORDER_ID, B.OPER_KEY, B.STEP_KEY, B.RELATED_KEY1 AS BUYOFF_ID, B.SERIAL_ID, B.LOT_ID ")
				.append("           FROM SFWID_SERIAL_OPER_CTRL_DATCOL B ")
				.append("          WHERE EXISTS ")
				.append("         (SELECT 'x' ")
				.append("            FROM SFWID_ORDER_EMBEDDED_CONTROLS A ")
				.append("           WHERE A.ORDER_ID = B.ORDER_ID ")
				.append("             AND A.OPER_KEY = B.OPER_KEY ")
				.append("             AND A.STEP_KEY = B.STEP_KEY ")
				.append("             AND A.REF_ID = B.CONTROL_ID ")
				.append("             AND A.TAG_TYPE = B.TAG_TYPE ")
				.append("             AND B.related_object_status = 'SKIP' ")
				.append("             AND A.ORDER_ID = ? ")
				.append("             AND A.OPER_KEY = ? ")
				.append("             AND A.TAG_TYPE = 'DATCOL' ")
				.append("             AND B.serial_id = ? ")
				.append("             AND B.lot_id = ? ")
				.append("             AND A.CONTROL_SEQ_NO BETWEEN ")   
				.append("                (NVL((SELECT MAX(A2.CONTROL_SEQ_NO) ")   //  seq no greater than prior buyoff accepted
				.append("                 FROM SFWID_ORDER_EMBEDDED_CONTROLS A2, ")
				.append("                      SFWID_SERIAL_OPER_CTRL_BUYOFF B2 ")
				.append("                WHERE A2.ORDER_ID = A.ORDER_ID ")
				.append("                  AND A2.OPER_KEY = A.OPER_KEY ")
				.append("                  AND A2.TAG_TYPE = 'BUYOFF' ")
				.append("                  AND A2.ORDER_ID = B2.ORDER_ID ")
				.append("                  AND A2.OPER_KEY = B2.OPER_KEY ")
				.append("                  AND A2.STEP_KEY = B2.STEP_KEY ")
				.append("                  AND A2.REF_ID = B2.CONTROL_ID ")
				.append("                  AND A2.TAG_TYPE = B2.TAG_TYPE ")
				.append("                  AND B2.SERIAL_ID = B.SERIAL_ID ")
				.append("                  AND B2.LOT_ID = B.LOT_ID ")
				.append("                  AND B2.RELATED_KEY1 <> ? ")   // buyoff id
				.append("                  AND B2.RELATED_OBJECT_STATUS = 'ACCEPT'), 0)) ")
				.append("             AND  ")  
				.append("                (SELECT MAX(A1.CONTROL_SEQ_NO) ")   // seq no less than current buyoff 
				.append("                   FROM SFWID_ORDER_EMBEDDED_CONTROLS A1, ")
				.append("                        SFWID_SERIAL_OPER_CTRL_BUYOFF B1 ")
				.append("                  WHERE A1.ORDER_ID = A.ORDER_ID ")
				.append("                    AND A1.OPER_KEY = A.OPER_KEY ")
				.append("                    AND A1.TAG_TYPE = 'BUYOFF' ")
				.append("                    AND A1.ORDER_ID = B1.ORDER_ID ")
				.append("                    AND A1.OPER_KEY = B1.OPER_KEY ")
				.append("                    AND A1.STEP_KEY = B1.STEP_KEY ")
				.append("                    AND A1.REF_ID = B1.CONTROL_ID ")
				.append("                    AND A1.TAG_TYPE = B1.TAG_TYPE ")
				.append("                    AND B1.SERIAL_ID = B.SERIAL_ID ")
				.append("                    AND B1.LOT_ID = B.LOT_ID ")
				.append("                    AND B1.RELATED_KEY1 = ?))) ");   // buyoff id

		ParameterHolder params = new ParameterHolder();

		params.addParameter(orderId);
		params.addParameter(operKey);
		params.addParameter(serialId);  
		params.addParameter(lotId);
		params.addParameter(buyoffId);
		params.addParameter(buyoffId);

		Integer updCount = eds.update(updateSQL.toString(), params);
		return updCount;
	}
}


