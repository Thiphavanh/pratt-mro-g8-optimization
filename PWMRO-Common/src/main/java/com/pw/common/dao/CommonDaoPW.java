/*
 * This unpublished work, first created in 2017 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2017, 2018, 2019, 2020,2021.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    CommonDaoPW.java
 * 
 *  Created: 2017-08-08
 * 
 *  Author:  c079222
 * 
 *  Revision History
 * 
 *  Date      	Who                	Description
 *  ----------	---------------	---------------	---------------------------------------------------
 * 2017-08-08 	c079222		    Initial Release 
 * 2017-09-25	R. Cannon		SMRO_USR_201 - Moved LDAP methods to LoginDaoPW
 * 2017-09-26    R. Thorpe       Added method selectSfdbGuid from Fred
 * 2017-10-13    D. Miron        SMRO_EXPT_201 - Added getUserFullName
 * 2018-03-20    R. Thorpe		SMRO_MD_M206 - Added method getRoleForPlantOccCode
 * 2018-05-18    R. Thorpe       SMOR_WOE_202 - Defect 792 Added method getGlobalConfigValue
 * 2018-06-25    R. Thorpe       SMRO_RPT_201 - Defect 864 Removed selectDataValueFromGeneralLookup it was replaced with getLookupValList 
 *                               because the table was renamed from PWUST_ to PWUE_
 * 2018-07-20    R. Thorpe       SMRO_RPT_201 - Defect 849  uncommented method selectDataValueFromGeneralLookup and changed table name from PWUST to PWUE 
 * 2018-10-01    F. Ettefagh     Defect 980 Cannot Create Suborder due to supNet subNet and conf Numbers     
 * 2019-02-12    R. Thorpe       Defect 1119 - Added method getWorkLocListByPriv                         
 * 2019-05-03    F. Ettefagh     Added methods updatePwustIntRepairWoData, selectSfwidOrderDesc for repair work order create
 * 2019-06-05    F. Ettefagh     Added new methods for Repair shop order split 
 * 2019-6-10     F. Ettefagh     added work loc to sql selectsfwidOrderDescList
 * 2019-07_18    Fred Ettefagh   Defect 1327, Added new methods for R2SP4 order create upgrade
 * 2019-08-01    R. Thorpe       added getParentOrderId
 * 2019-09-12    F. Ettefagh     SMRO_TR_319, added code for SCR-001 
 * 2019-09-20    Bahram J.       removed methods selectPwustSyncIntReplyData, insertIntoPwustSyncIntReplyData, deletePwustSyncIntReplyData
 * 2019-09-25    F. Ettefagh     added method selectSfwidHoldsInfo for T308 interface
 * 2019-10-24    F. Ettefagh     PW_SMRO_WOE_310, changed method updatePwustIntRepairWoData to update table PWUST_INT_SAP_SM_ORDER
 * 2019-10-30    f. Ettefagh     PW_SMRO_WOE_310, added new methods selectMroOrderTasks, selectMroOrderTasks
 * 2019-10-30    f. Ettefagh     SMRO_TR_319, added new methods getPwustGlobalConfigValue
 * 2019-11-14    F. Ettefagh     SMRO_TR_308, fixed Incorrect result size: expected 1, actual 0 error in getPwustGlobalConfigValue
 * 2019-11-20    F. Ettefagh     SMRO_TR_301, added method updateSfwidOrderDescWorkLoc 
 * 2019-12-16    F. Ettefagh     SMRO_TR_319, added new dao method sfplPlanVSelect
 * 2020-01-17    J. DeNinno      defect 1430 Supplemental work orders erroring
 * 2020-01-27    B.	Polak		 added methods for defect 1406 and 1416
 * 2020-02-13    Fred Ettefagh   Defect 1474, changed code to send t308 message after certain changes for WO Alt and task include exclude
 * 2020-02-13    Fred Ettefagh   SMRO_TR_319, Defect 1526, changed method updatePwustSfwidOrderDesc to add sap_order_type
 * 2020-03-03    John DeNinno	 defect 1451
 * 2020-03-09    John DeNinno    defect 1462, used in signature
 * 2020-03-03    Fred Ettefagh   SMRO_WOE_310, Defect 1574 added method  selectSfwidOperDesc
 * 2020-03-25	 J. DeNinno		Defect 953 add additional columns to insert for correct sales order create date
 * 2020-05-26    Fred Ettefagh   SMRO_WOE_302 - Defect 1480 added new method selectSfwidSerialDescListExcludeSerialNo
 * 2020-03-29    Fred Ettefagh   SMRO_WOE_302 - Defect 1584, moved  method updatePwustSfwidOrderDesc(String orderId, String outgoingPartNo, String sapOrderType ) to RepairOrderCreateDaoPW class
 *                                              and fixed method getSerialfromOrderNo(String orderId, String lotNo) to return serial no that have not been STOPPED,  COMPLETE, SCRAP
 * 2020-06-05    Fred Ettefagh   Defect 1582; Added methods repairWorkOrderSplitList    
 * 2020-06-23    Fred ETtefagh   Defect 1703, added new method   isRepairWorkOrderSplit 
 * 2020-06-24    D.Miron         Defect 1696 - Removed getOperationStatus.   
 * 2020-06-10    John DeNinno	defect 1685 Missing order number and parent of supplemental
 * 2020-06-30	 S. Edgerly		Defect 1416 Reason for Change for Overhaul workorders
 * 2020-07-02    J DeNinno       Defect 1685 retrieve oper no of parent for supplemental
 * 2020-07-14    John DeNinno	Defect 1392 add Sub Order Type
 * 2020-07-27    John DeNinno	Defect 1742 get Plan Id from Plan Number
 * 2020-07-31    John DeNinno   Defect 1413 add method to null out CommodityJurisdiction so that banner appears correct
 * 2020-08-11    John DeNinno   Defect 1749 add distinct if more than one revision    
 * 2020-08-18    D.Miron        SMRO_TR_319 Defect 1757 -  Added method getPlanItemNo.   
 * 2020-08-19    John DeNinno   Defect 1711 lookup serials to display in popup  
 * 2020-10-02    John DeNinno,Fred   Defect 1772 added code to insert into new custom PW buyoff hist table
 * 2020-10-06    Fred Ettefagh  Defect 1772, added method updateSfwidBuyoffHistPW  to update custom table SFWID_BUYOFF_HIST_PW column serial no after order split 
 * 2020-10-06    John DeNinno   Defect 1782 add code that gets operNo
 * 2020-11-06    John DeNinno   Defect 1775 add code for last active operation and last active buyoff
 * 2020-11-20    John DeNinno   Defect 1775 made additional change for oper buyoff
 * 2020-12-211   John DeNinno   Defect 1834 made additional change for oper buyoff
 * 2021-01-15    John DeNinno   defect 1829 lookup SFOR_SFPL_PLAN_SUBJECT_PART
 * 2021-01-28    John DeNinno   defect 1844 get plan info using primary key
 * 2021-03-08    John DeNinno   defect 1873 related work order DELIVERED status update
 * 2021-03-24    John DeNinno	SMRO_USR_205 - Certifcations SCR-002 Defect 1840
 * 2021-08-23    John DeNinno   Defect 1906
 * 2021-09-28    John DeNinno   Defect 1994 - Change how display tab on buyoff selects the history add serialId Lookup
 * 2021-10-26    John DeNinno   Defect 2010 - get manual info for T314
 */
package com.pw.common.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.solumina.sfcore.application.IUser;
import com.ibaset.solumina.sffnd.certification.Certification; //defect 1840
import com.ibaset.solumina.sffnd.certification.CertificationValidator;
import com.ibaset.solumina.sfcore.application.IMessage; //defect 1840
import com.ibaset.solumina.sffnd.certification.ICertificationDao; //defect 1840

public class CommonDaoPW {

	@Reference  private ExtensionDaoSupport eds;
	@Reference	private IUser user = null;
	@Reference
	private IMessage message;//defect 1840
	@Reference
	private ICertificationDao certificationDao;//defect 1840
	@Reference
	private CertificationValidator certificationValidator;//defect 1840

	@Reference
	private JdbcDaoSupport dao;

	public int updateSfwidBuyoffHistPW(String orderId, String oldSerialNo, String newStrialNo, String user ){


		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFWID_BUYOFF_HIST_PW T " +
				" SET   T.SERIAL_NO = ?, " +
				"       T.TIME_STAMP = SYSDATE, " +
				"       T.UPDT_USERID = ? " +
				" WHERE T.ORDER_ID = ? " + 
				" AND   T.SERIAL_NO =  ?");
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(newStrialNo);
		parameters.addParameter(user);
		parameters.addParameter(orderId);
		parameters.addParameter(oldSerialNo);
		int i = eds.update(updateSql.toString(), parameters);
		return i;
	}


	@SuppressWarnings("rawtypes")
	public boolean isRepairWorkOrderSplit(String orderId){

		boolean returnValue=false;
		String PWOrderType = null;
		List pwustSfwidOrderDescList = selectPWOrderDataByOrderId(orderId);
		if (pwustSfwidOrderDescList!=null && !pwustSfwidOrderDescList.isEmpty()){
			Map pwustSfwidOrderDescMap = (Map)pwustSfwidOrderDescList.get(0);
			PWOrderType = (String)pwustSfwidOrderDescMap.get("PW_ORDER_TYPE");
		}

		if (StringUtils.equalsIgnoreCase(PWOrderType,  "REPAIR")){

			StringBuilder selectSql = new StringBuilder().append(" SELECT T.* " + 
					" FROM SFWID_SERIAL_DESC T " +
					" WHERE T.ORDER_ID = ? "+
					" AND T.UCF_SERIAL_VCH1 = ? ");

			ParameterHolder parameters = new ParameterHolder();
			parameters.addParameter(orderId);
			parameters.addParameter("Cancel via repair order split");
			List list = eds.queryForList(selectSql.toString(), parameters);
			if (list!=null && !list.isEmpty()){
				returnValue = true;
			}
		}

		return returnValue;
	}

	@SuppressWarnings("rawtypes")
	public List selectSfwidSerialDescListExcludeSerialNo(String orderId, String SerialNo){

		ParameterHolder params = new ParameterHolder();

		StringBuffer select = new StringBuffer().append(" SELECT T.* " +
				" FROM SFWID_SERIAL_DESC T "+
				" WHERE T.ORDER_ID = ? " + 
				" AND   T.SERIAL_NO != ? ");
		params.addParameter(orderId);
		params.addParameter(SerialNo);
		List list = eds.queryForList(select.toString(), params);
		return list;
	}

	public List selectSfwidOperDesc(String orderId, String operNo){

		StringBuilder selectSql = new StringBuilder().append(" SELECT T.* " + 
				" FROM SFWID_OPER_DESC T " +
				" WHERE T.ORDER_ID = ? " +
				" AND T.OPER_NO = ? " +
				" AND T.STEP_KEY = ? " +
				" AND T.OPER_STATUS NOT IN ('CANCEL', 'EXCLUDE' )");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operNo);
		parameters.addParameter(-1);

		System.out.println("in CommonDaoPW.selectSfwidOperDesc selectSql =  "+ selectSql);
		System.out.println("in CommonDaoPW.selectSfwidOperDesc parameters =  "+ parameters);

		List list = eds.queryForList(selectSql.toString(), parameters);
		return list;		
	}

	public List selectSfwidHoldInfoV(String orderId, String holdStatus, String holdType){

		StringBuilder selectSql = new StringBuilder().append(" SELECT T.* " + 
				" FROM SFWID_HOLDS_INFO_V T " +
				" WHERE T.ORDER_ID = ? " +
				" AND T.HOLD_STATUS = ? " +
				" AND T.HOLD_TYPE = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(holdStatus);
		parameters.addParameter(holdType);

		List list = eds.queryForList(selectSql.toString(), parameters);
		return list;
	}


	@SuppressWarnings("rawtypes")
	public Map sfplPlanVSelect(String planId, Number planVer, Number planRev, Number planAlt){

		Map returnMapValue = null;

		StringBuilder selectSql = new StringBuilder().append(" SELECT T.* FROM SFPL_PLAN_V T  "
				+ " WHERE T.PLAN_ID = ? "
				+ " AND T.PLAN_VERSION = ? "
				+ " AND T.PLAN_REVISION = ? "
				+ " AND T.PLAN_ALTERATIONS = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(planId);
		parameters.addParameter(planVer);
		parameters.addParameter(planRev);
		parameters.addParameter(planAlt);

		List list = eds.queryForList(selectSql.toString(), parameters);

		if (list != null && !list.isEmpty()){
			returnMapValue = (Map)list.get(0);
		}

		return returnMapValue;
	}


	// defect 1182
	public int updateSfwidOrderDescWorkLoc(String orderId, String asgndLocationID){

		StringBuffer updateSql = new StringBuffer().append("UPDATE SFWID_ORDER_DESC T  SET T.ASGND_LOCATION_ID = ? WHERE T.ORDER_ID = ? ");
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(asgndLocationID);
		parameters.addParameter(orderId);

		int i = eds.update(updateSql.toString(), parameters);
		return i;
	}

	public Map selectSffndWorkLocDec(String workLoc){

		Map map = null;
		StringBuffer selectSql = new StringBuffer().append(" SELECT T.* FROM SFFND_WORK_LOC_DEF T WHERE T.WORK_LOC = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(workLoc);

		List list =eds.queryForList(selectSql.toString(), params);
		if (list!=null && !list.isEmpty()){
			map = (Map)list.get(0);
		}
		return map;
	}


	public String getPwustGlobalConfigValue(String globalParamName)
	{   
		String globalParamValue = null;

		StringBuffer selectSql = new StringBuffer().append(" SELECT T.PARAMETER_VALUE FROM PWUST_GLOBAL_CONFIG T WHERE T.PARAMETER_NAME = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(globalParamName);

		List list =eds.queryForList(selectSql.toString(), params);
		if (list!=null && !list.isEmpty()){
			Map map = (Map)list.get(0);
			globalParamValue = (String)map.get("PARAMETER_VALUE");
		}
		return globalParamValue;
	}


	public List selectMroOrderTasks(String orderId){

		StringBuilder selectSql = new StringBuilder().append(" SELECT T.* FROM SFOR_SFWID_ORDER_SUBJECT T WHERE ORDER_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);

		List  list = eds.queryForList(selectSql.toString(), parameters);
		return list;
	}

	public Map selectMroOrderTasks(String orderId,String subjectNumberList){

		Map map = null;
		StringBuilder selectSql = new StringBuilder().append(" SELECT T.* "
				+ " FROM SFOR_SFWID_ORDER_SUBJECT T "
				+ " WHERE T.ORDER_ID = ? "
				+ " AND TSUBJECT_NO IN ("+ subjectNumberList + ")");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		List list  = eds.queryForList(selectSql.toString(), parameters);
		if (list!= null && !list.isEmpty()){
			map = (Map)list.get(0);
		}
		return map;
	}


	public List selectSfwidHoldsInfo(String orderId, String holdType, String holdStatus){

		ParameterHolder params = new ParameterHolder();

		StringBuffer select = new StringBuffer().append(" SELECT T.* " +
				" FROM SFWID_HOLDS_INFO_V T " +
				" WHERE T.ORDER_ID = ? " + 
				" AND T.HOLD_TYPE = ? " +
				" AND T.HOLD_STATUS = ? ");

		params.addParameter(orderId);
		params.addParameter(holdType);
		params.addParameter(holdStatus);

		List list = eds.queryForList(select.toString(), params);
		return list;

	}

	public List selectsfwidOrderDescMutilOrdersList(String mutiOrderCreateFlag){

		ParameterHolder params = new ParameterHolder();

		StringBuffer select = new StringBuffer().append(" SELECT T.* " +
				" FROM SFWID_ORDER_DESC T "+
				" WHERE NVL(T.UCF_ORDER_VCH15, ? ) = ? ");

		params.addParameter("null");
		params.addParameter(mutiOrderCreateFlag);
		List list = eds.queryForList(select.toString(), params);
		return list;
	}

	public int updateSfwidOrderDescQty(String orderId,Number orderQty){

		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFWID_ORDER_DESC T SET T.ORDER_QTY = T.ORDER_QTY - ? WHERE T.ORDER_ID = ? " );
		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderQty);
		params.addParameter(orderId);

		int numOfRowsUpd = eds.update(updateSql.toString(), params);
		return  numOfRowsUpd;
	}

	public List selectPartNoSerialNo(String partNo, String SerialNumber){

		StringBuilder selectSql = new StringBuilder().append(" SELECT TT.SERIAL_NO, T.* " +
				"  FROM SFWID_ORDER_DESC T, SFWID_SERIAL_DESC TT " +
				"  WHERE T.ORDER_ID = TT.ORDER_ID " +
				"  AND T.PART_NO = ? " +
				"  AND TT.SERIAL_NO = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(partNo);
		parameters.addParameter(SerialNumber);

		List list = eds.queryForList(selectSql.toString(), parameters);	

		return list;

	}

	@SuppressWarnings("rawtypes")
	public Map selectSfplPlanV(String planId, Number planVer, Number planRev, Number planAlt){


		StringBuffer selectSql = new StringBuffer().append(" SELECT T.* " +
				" FROM SFPL_PLAN_V T "+
				" WHERE T.PLAN_ID = ? " +
				" AND T.PLAN_VERSION  = ? "+
				" AND T.PLAN_REVISION = ? " +
				" AND T.PLAN_ALTERATIONS = ? ");
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planVer);
		params.addParameter(planRev);
		params.addParameter(planAlt);

		Map map = eds.queryForMap(selectSql.toString(), params);

		return map;

	}


	public String selectDBTimeStamp(String format)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT TO_CHAR(SFMFG.SFDB_SYSDATE(), '" + format + "') AS DB_SYSDATE FROM DUAL ");
		return (String) eds.queryForObject(selectSql.toString(), String.class);
	}

	public String getTodaysDate(){

		StringBuffer select = new StringBuffer().append(" SELECT TRUNC(SYSDATE) AS TODAYSDATE FROM DUAL  ");
		Map map = eds.queryForMap(select.toString());
		Timestamp todaysDate = (Timestamp)map.get("TODAYSDATE");
		return todaysDate.toString();
	}

	@SuppressWarnings("rawtypes")
	public List selectSfwidSerialDescList(String orderId, String serialNo){

		ParameterHolder params = new ParameterHolder();

		StringBuffer select = new StringBuffer().append(" SELECT T.* " +
				" FROM SFWID_SERIAL_DESC T "+
				" WHERE T.ORDER_ID = ? " + 
				" AND T.SERIAL_NO = ? ");

		params.addParameter(orderId);
		params.addParameter(serialNo);
		List list = eds.queryForList(select.toString(), params);
		return list;

	}

	@SuppressWarnings("rawtypes")
	public List selectSfwidLotDescList(String orderId){

		ParameterHolder params = new ParameterHolder();

		StringBuffer select = new StringBuffer().append(" SELECT T.* " +
				" FROM SFWID_LOT_DESC T "+
				" WHERE T.ORDER_ID = ? ");
		params.addParameter(orderId);
		List list = eds.queryForList(select.toString(), params);
		return list;

	}

	@SuppressWarnings("rawtypes")
	public List selectSfwidSerialDescList(String orderId){

		ParameterHolder params = new ParameterHolder();

		StringBuffer select = new StringBuffer().append(" SELECT T.* " +
				" FROM SFWID_SERIAL_DESC T "+
				" WHERE T.ORDER_ID = ? ");
		params.addParameter(orderId);
		List list = eds.queryForList(select.toString(), params);
		return list;

	}




	public void insertIntoPwustSfwidOrderDesc (String orderId,
			String pwOrderNo,
			String salesOrder,
			String engineModel,
			String workScope,
			String supNetworkAct,
			String subNetworkAct,
			String customer,
			String pwOrderType){

		insertIntoPwustSfwidOrderDesc(orderId, pwOrderNo, salesOrder, engineModel, workScope, supNetworkAct, subNetworkAct, 
				customer, pwOrderType,null,null);
	}



	//defect 1406 added outgoing_part_no to PWUST_SFWID_ORDER_DESC
	//defect 1416 added NoteText to PWUST_SFWID_ORDER_DESC
	public void insertIntoPwustSfwidOrderDesc (String orderId,
			String pwOrderNo,
			String salesOrder,
			String engineModel,
			String workScope,
			String supNetworkAct,
			String subNetworkAct,
			String customer,
			String pwOrderType,
			String noteText,
			String outgoingPartNo){
		StringBuffer insert = new StringBuffer().append("INSERT INTO PWUST_SFWID_ORDER_DESC ( ORDER_ID, " +
				" PW_ORDER_NO, "+ 
				" SALES_ORDER, "+ 
				" ENGINE_MODEL, "+
				" WORK_SCOPE, "+
				" SUPERIORNET, "+
				" SUBNET, "+
				" CUSTOMER, "+
				" UPDT_USERID,"+
				" PW_ORDER_TYPE, "+
				" NOTE_TEXT, "+
				" OUTGOING_PART_NO, "+
				" SALES_ORDER_CREATE_BY,"+     //DEFECT 953
				" SALES_ORDER_CREATE_DATE) "+  //DEFECT 953
				" VALUES ( ? , ? , ? , ? , ? , ? , ? , ?, ?, ?, ?, ? , ?, SYSDATE ) ");    //DEFECT 953

		ParameterHolder params = new ParameterHolder();

		params.addParameter(orderId);
		params.addParameter(pwOrderNo);
		params.addParameter(salesOrder);
		params.addParameter(engineModel);
		params.addParameter(workScope);
		params.addParameter(supNetworkAct);
		params.addParameter(subNetworkAct);
		params.addParameter(customer);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(pwOrderType);
		params.addParameter(noteText);
		params.addParameter(outgoingPartNo);
		params.addParameter(ContextUtil.getUsername()); //defect 953
		eds.insert(insert.toString(),params);
	}

	@SuppressWarnings("rawtypes")
	public List selectPwustSfwidOrderDesc(String orderID){

		StringBuffer selectSql = new StringBuffer().append(" SELECT T.* FROM PWUST_SFWID_ORDER_DESC T WHERE T.ORDER_ID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderID);

		List list = eds.queryForList(selectSql.toString(), params);
		return  list;
	}

	//defect 1416 added for updating note text in PWUST_SFWID_ORDER_DESC
	public int updatePwustSfwidOrderDescNoteText(String orderId, String noteText){

		StringBuffer updateSql = new StringBuffer().append(" UPDATE PWUST_SFWID_ORDER_DESC  T "+
				" SET T.NOTE_TEXT = ? " +
				" WHERE T.ORDER_ID =  ? " );
		ParameterHolder params = new ParameterHolder();
		params.addParameter(noteText);
		params.addParameter(orderId);

		int numOfRowsUpd = eds.update(updateSql.toString(), params);
		return  numOfRowsUpd;
	}

	//defect 1416
	public int updateSfwidAlterationDesc(String orderId, String noteText){
		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFWID_ALTERATION_DESC T "+
				" SET T.ALT_REASON = ? " +
				" WHERE T.ALT_ID = (SELECT ALT_ID FROM SFWID_ORDER_DESC WHERE ORDER_ID = ?) " );
		ParameterHolder params = new ParameterHolder();
		params.addParameter(noteText);
		params.addParameter(orderId);

		int numOfRowsUpd = eds.update(updateSql.toString(), params);
		return  numOfRowsUpd;
	}

	public List selectsfwidOrderDescList(String orderNumber){

		ParameterHolder params = new ParameterHolder();

		StringBuffer select = new StringBuffer().append(" SELECT  (SELECT T3.WORK_LOC FROM SFFND_WORK_LOC_DEF T3 WHERE T3.LOCATION_ID = T.ASGND_LOCATION_ID) AS WORK_LOC, T.* " +
				" FROM SFWID_ORDER_DESC T "+
				" WHERE T.ORDER_NO = ? ");

		params.addParameter(orderNumber);
		List list = eds.queryForList(select.toString(), params);
		return list;		
	}

	public int updatePwustIntRepairWoData(String orderId,String processFlag){

		StringBuffer updateSql = new StringBuffer().append(" UPDATE PWUST_INT_SAP_SM_ORDER  T "+
				" SET T.ORDER_CREATE_DATE = NULL ," +
				"     T.ORDER_ID = NULL " +
				" WHERE T.ORDER_ID =  ? " );
		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);

		int numOfRowsUpd = eds.update(updateSql.toString(), params);
		return  numOfRowsUpd;
	}


	@SuppressWarnings("rawtypes")
	public List selectSfwidOrderDesc(String orderID){

		StringBuffer selectSql = new StringBuffer().append(" SELECT T.* FROM SFWID_ORDER_DESC T WHERE T.ORDER_ID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderID);

		List list = eds.queryForList(selectSql.toString(), params);
		return  list;
	}



	// defect 980
	@SuppressWarnings("rawtypes")
	public List selectPWOrderDataByOrderId(String orderId){

		StringBuffer selectSql = new StringBuffer().append(" SELECT T1.PARENT_ORDER_ID, " +   ///T308
				" T.PW_ORDER_NO, " +
				" T.SALES_ORDER, " +
				" T.ENGINE_MODEL, " +
				" T.WORK_SCOPE, " +
				" T.SUPERIORNET, " +
				" T.SUPERIORNET, " +
				" T.CUSTOMER, " +
				" T.SAP_SERIAL_NO, " +
				" T.PW_ORDER_TYPE, " +
				" T.PW_ALT_COUNT," +
				" (SELECT T3.WORK_LOC FROM SFFND_WORK_LOC_DEF T3 WHERE T3.LOCATION_ID = T1.ASGND_LOCATION_ID) AS WORK_LOC, " +
				" T1.* " +
				" FROM PWUST_SFWID_ORDER_DESC T, SFWID_ORDER_DESC T1 " +
				" WHERE T.ORDER_ID = T1.ORDER_ID " +
				" AND T.ORDER_ID = ? " );

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);

		List list = eds.queryForList(selectSql.toString(), params);
		return  list;
	}

	// defect 980
	@SuppressWarnings("rawtypes")
	public List selectPWOrderDataByPWOrderNo(String pwOrderNo){

		StringBuffer selectSql = new StringBuffer().append(" SELECT T.PW_ORDER_NO, " +
				" T.SALES_ORDER, " +
				" T.ENGINE_MODEL, " +
				" T.WORK_SCOPE, " +
				" T.SUPERIORNET, " +
				" T.SUPERIORNET, " +
				" T.CUSTOMER, " +
				" T.SAP_SERIAL_NO, " +
				" T.PW_ORDER_TYPE, " +
				" T.PW_ALT_COUNT," +
				" (SELECT T3.WORK_LOC FROM SFFND_WORK_LOC_DEF T3 WHERE T3.LOCATION_ID = T1.ASGND_LOCATION_ID) AS WORK_LOC, " +
				" T1.* " +
				" FROM PWUST_SFWID_ORDER_DESC T, SFWID_ORDER_DESC T1 " +
				" WHERE T.ORDER_ID = T1.ORDER_ID " +
				" AND T.PW_ORDER_NO = ? " );

		ParameterHolder params = new ParameterHolder();
		params.addParameter(pwOrderNo);

		List list = eds.queryForList(selectSql.toString(), params);
		return  list;
	}


	public String selectNextErrorSequence()
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT SFCORE_ERROR_LOG_SEQ.NEXTVAL ")
				.append(eds.getDualTable());

		ParameterHolder params = new ParameterHolder();
		String nextValue = eds.queryForString(selectSql.toString(), params);

		return nextValue ;
	}


	public String selectNextTransactionId()
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT PWUST_TRANS_ID.NEXTVAL ")
				.append(eds.getDualTable());

		ParameterHolder params = new ParameterHolder();
		String nextValue = eds.queryForString(selectSql.toString(), params);

		return nextValue ;
	}

	public String selectNextRecordId()
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT PWUST_INT_RECORD_ID_SEQ.NEXTVAL ")
				.append(eds.getDualTable());

		ParameterHolder params = new ParameterHolder();
		String nextValue = eds.queryForString(selectSql.toString(), params);

		return nextValue ;
	}

	public String selectNextSnSequenceId()
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT PWUST_SPLIT_SN_SEQ.NEXTVAL ")
				.append(eds.getDualTable());

		ParameterHolder params = new ParameterHolder();
		String nextValue = eds.queryForString(selectSql.toString(), params);

		return nextValue ;
	}

	//m112328.  CBN 2012-08-08
	public String selectUserPassword(String UserID) 
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT Password from SFCORE_USER ")
				.append(" WHERE UserID = ? ");
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(UserID);
		String userPassword = eds.queryForString(selectSql.toString(), parameters);

		return userPassword;
	}	

	public int selectJmsReplyTimeout(String transactionShortname) 
	{   // SIQA_TR_T6 (T6 Interface): Add method selectJmsReplyTimeout. Can be used by other JMS messages too.
		// Input: transactionShortname values T6, etc.

		int waitSeconds = 60;  ///initialize to default value
		String parameterValue;

		StringBuffer selectSql = new StringBuffer().append("select count(*) from PWUST_GLOBAL_CONFIG ")
				.append(" where PARAMETER_NAME = ? ");
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(transactionShortname+"_JMS_TIMEOUT_SECONDS");

		int rowCount = eds.queryForInt(selectSql.toString(), parameters);

		if (rowCount == 1)
		{
			StringBuffer selectSql2 = new StringBuffer().append("select PARAMETER_VALUE from PWUST_GLOBAL_CONFIG ")
					.append(" where PARAMETER_NAME = ? ");
			ParameterHolder parameters2 = new ParameterHolder();
			parameters2.addParameter(transactionShortname+"_JMS_TIMEOUT_SECONDS");

			parameterValue = eds.queryForString(selectSql2.toString(), parameters2);	
		}
		else
		{
			parameterValue = null;
		}


		if (!StringUtils.equals(parameterValue, null))
		{
			waitSeconds = Integer.parseInt(parameterValue);  
		}			

		return waitSeconds;

	}	


	public String selectServiceNameOverride(String transactionShortname) 
	{   // SIQA_TR_T7 (QN Disposition Interface): Add method selectServiceNameOverride. Can be used by other JMS messages too.
		// Input: transactionShortname values T7, etc.

		String parameterValue;

		StringBuffer selectSql = new StringBuffer().append("select count(*) from PWUST_GLOBAL_CONFIG ")
				.append(" where PARAMETER_NAME = ? ");
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(transactionShortname+"_SERVICE_NAME_OVERRIDE");

		int rowCount = eds.queryForInt(selectSql.toString(), parameters);

		if (rowCount == 1)
		{
			StringBuffer selectSql2 = new StringBuffer().append("select PARAMETER_VALUE from PWUST_GLOBAL_CONFIG ")
					.append(" where PARAMETER_NAME = ? ");
			ParameterHolder parameters2 = new ParameterHolder();
			parameters2.addParameter(transactionShortname+"_SERVICE_NAME_OVERRIDE");

			parameterValue = eds.queryForString(selectSql2.toString(), parameters2);	
		}
		else
		{
			parameterValue = null;
		}

		return parameterValue;

	}	


	public String splitWorkDept(String inWorkDept)	///2013-01-18 SIOE_TR_T6: Defect 792
	{
		/* 
		 * inWorkDept formats: (a) wwww -or (b) iiii/wwww, where wwww=WorkDept and iiii=InspDept 
		 */
		String OutWorkDept = inWorkDept;

		if (inWorkDept.contains("/"))
		{
			String[] tokens = inWorkDept.split("/");
			OutWorkDept = tokens[1];
		}

		System.out.println("CommonDaoPW.splitWorkDept: inWorkDept="+inWorkDept+",OutWorkDept="+OutWorkDept);
		return OutWorkDept;	    	
	}

	public String selectDisableRaiseError(String transactionShortname) 
	{   // SIOE_TR_T5 (rMRP Interface): Add method selectDisableRaiseError. Can be used by other JMS messages too.
		// Input: transactionShortname values T5, etc.

		String parameterValue;

		StringBuffer selectSql = new StringBuffer().append("select count(*) from PWUST_GLOBAL_CONFIG ")
				.append(" where PARAMETER_NAME = ? ");
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(transactionShortname+"_DISABLE_RAISE_ERROR");

		int rowCount = eds.queryForInt(selectSql.toString(), parameters);

		if (rowCount == 1)
		{
			StringBuffer selectSql2 = new StringBuffer().append("select PARAMETER_VALUE from PWUST_GLOBAL_CONFIG ")
					.append(" where PARAMETER_NAME = ? ");
			ParameterHolder parameters2 = new ParameterHolder();
			parameters2.addParameter(transactionShortname+"_DISABLE_RAISE_ERROR");

			parameterValue = eds.queryForString(selectSql2.toString(), parameters2);	
		}
		else
		{
			parameterValue = null;
		}

		return parameterValue;

	}	


	public String selectIfNotEnabledFlagSuccess(String transactionShortname) 
	{   // SIOE_TR_T5 (rMRP Interface): Add method selectIfNotEnabledFlagSuccess. Can be used by other JMS messages too.
		// Input: transactionShortname values T5, etc.

		String parameterValue;

		StringBuffer selectSql = new StringBuffer().append("select count(*) from PWUST_GLOBAL_CONFIG ")
				.append(" where PARAMETER_NAME = ? ");
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(transactionShortname+"_IF_NOT_ENABLED_FLAG_SUCCESS");

		int rowCount = eds.queryForInt(selectSql.toString(), parameters);

		if (rowCount == 1)
		{
			StringBuffer selectSql2 = new StringBuffer().append("select PARAMETER_VALUE from PWUST_GLOBAL_CONFIG ")
					.append(" where PARAMETER_NAME = ? ");
			ParameterHolder parameters2 = new ParameterHolder();
			parameters2.addParameter(transactionShortname+"_IF_NOT_ENABLED_FLAG_SUCCESS");

			parameterValue = eds.queryForString(selectSql2.toString(), parameters2);	
		}
		else
		{
			parameterValue = null;
		}

		return parameterValue;

	}

	/*
	 * SESA_USR_02 (LDAP/Siteminder)
	 */
	public String selectGlobalConfigValue(String paramName) 

	{   
		String parameterValue;

		try
		{
			StringBuffer selectSql = new StringBuffer().append("select PARAMETER_VALUE from PWUST_GLOBAL_CONFIG ")
					.append(" where PARAMETER_NAME = ? ");
			ParameterHolder parameters = new ParameterHolder();
			parameters.addParameter(paramName);

			parameterValue = eds.queryForString(selectSql.toString(), parameters);
		}
		catch (Exception sqlE)
		{
			parameterValue="noParamSet";
		}

		return parameterValue;

	}

	/*
	 * SIQA_TR_T12 - Vendor Assist interface
	 * Method added to get multiple records 
	 *  from PWUST_GENERAL_LOOKUP table
	 */
	public List getLookupValList(String lookupField) 

	{   
		List valList = null;

		try
		{
			StringBuffer selectSql = new StringBuffer().append("select DATA_VALUE from PWUE_GENERAL_LOOKUP ")  //  Defect 144 changed to PWUE
					.append(" where DATA_FIELD = ? ");
			ParameterHolder parameters = new ParameterHolder();
			parameters.addParameter(lookupField);

			valList = eds.queryForList(selectSql.toString(), parameters);


		}
		catch (Exception sqlE)
		{
			valList = null;
		}

		return valList;

	}		

	/*SESA_USR_02 - use webSphere environment variable to get smAgentName
	 * 				to use as key in PWUST_EXT_SYS_PROPERTIES
	 *
	 */
	public String getSMagentName() 
	{
		String agentName = "";
		Properties sysprop = System.getProperties();

		if (sysprop.getProperty("pw.server.smAgent") == null)   //localhost
		{
			agentName = "local";
		}
		else
		{
			//pwsb7dsolapp
			agentName =sysprop.getProperty("pw.server.smAgent"); 
		}

		return agentName;
	}

	public Map getExternalProps(String smAgentName)
	{

		StringBuffer selectSql = new StringBuffer().append("SELECT ")
				.append(" agent_name, " )
				.append(" ps_ip, ")
				.append(" ps_conmin, ")
				.append(" ps_conmax, ")
				.append(" ps_constep, ")
				.append(" ps_timeout, ")
				.append(" ps_azport, ")
				.append(" ps_auport, ")
				.append(" ps_acport, ")
				.append(" agent_secret, ") //will be encrypted in db, decrypt b4 sending to sm
				.append(" admin_usrname, ")
				.append(" admin_pwd, ")    //will be encrypted in db, decrypt b4 sending to sm
				.append(" user_dir, ")
				.append(" org_root, ")
				.append(" ldap_host, ")
				.append(" ldap_port, ")
				.append(" ldap_user,")
				.append(" ldap_pwd, ")
				.append(" ldap_chg_pwd_link,")
				.append(" xclass_host, ")
				.append(" was_instance, ")
				.append(" agent_descr, ")
				.append(" comments, ")
				.append(" key10, ")
				.append(" key11, ")
				.append(" key12, ")
				.append(" key13, ")
				.append(" key14, ")
				.append(" key15 ")
				.append(" ")
				.append(" FROM PWUST_EXT_SYS_PROPERTIES T ")
				.append("  WHERE T.AGENT_NAME = ? ");


		ParameterHolder params = new ParameterHolder();
		params.addParameter(smAgentName);
		Map returnMap = eds.queryForMap(selectSql.toString(), params);
		return returnMap;

	}

	// Begin SIOE_MD_M6
	public Map selectUserInformation(String userId)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT ")
				.append("    UCF_USER_VCH5, ")  //  business unit
				.append("    UCF_USER_VCH3, ")  //  cost center
				.append("    UCF_USER_VCH1, ")  //  occ code
				//.append("    WORK_LOC, ")        //  work location
				.append("    LOCATION_ID ")        //  work location
				.append("FROM  ")
				.append("    SFFND_USER ")
				.append("WHERE ")
				.append("    USERID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(userId);

		Map returnMap = eds.queryForMap(selectSql.toString(), parameters);
		return returnMap;
	}


	public String getWorkLocation(String workLocCode) 
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT DATA_VALUE_1 FROM PWUE_GENERAL_LOOKUP ")  //  Defect 144 changed to PWUE
				.append(" WHERE DATA_FIELD = 'WORK_LOCATION' ")
				.append("   AND DATA_VALUE = ? ");
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(workLocCode);
		String workLocation = eds.queryForString(selectSql.toString(), parameters);

		return workLocation;   	
	}

	//SMRO_MD_M206
	public String getRoleForPlantOccCode(String workLocation, String occCode) 
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT SROLE FROM PWUST_OCC_ROLE ")
				.append(" WHERE PLANT    = ? ")
				.append("   AND OCC_CODE = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(workLocation);
		parameters.addParameter(occCode);

		String role = "";

		try
		{
			role = eds.queryForString(selectSql.toString(), parameters);
		}
		catch (Exception e)
		{
			ParameterHolder param2 = new ParameterHolder();
			parameters.addParameter("ALL");
			parameters.addParameter(occCode);

			role = eds.queryForString(selectSql.toString(), param2);
		}

		return role;
	}

	//SMRO_MD_M206
	public int getUserWorkLocCount(String userId)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT COUNT(*) FROM SFFND_USER_WORK_CENTERS ")
				.append("WHERE USERID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(userId);

		int rowCount = eds.queryForInt(selectSql.toString(), parameters);

		return rowCount;
	}	 

	public List getDatefromString(String stringDate)
	{
		List returnList;

		StringBuffer selectSql = new StringBuffer().append("SELECT TO_DATE(?, 'YYYY-MM-DD') AS RETURNDATE FROM DUAL");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(stringDate);
		returnList = eds.queryForList(selectSql.toString(), parameters);

		return returnList;
	}

	public List getDatefromString2(String stringDate)
	{
		List returnList;

		StringBuffer selectSql = new StringBuffer().append("SELECT TO_DATE(?, 'MM/DD/YYYY') AS RETURNDATE FROM DUAL");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(stringDate);
		returnList = eds.queryForList(selectSql.toString(), parameters);

		return returnList;
	}
	public String getObjectIdForeWirLink() 
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT OBJECT_ID")
				.append("  FROM SFCORE_MM_OBJECT")
				.append(" WHERE OBJECT_TYPE = 'WEBBROWSER'")
				.append("   AND UPPER(OBJECT_TAG) = 'EWIR'");

		String objectId = eds.queryForString(selectSql.toString());

		return objectId;
	}

	public String selectDbName()
	{
		String dbName;
		StringBuffer selectSql = new StringBuffer().append("SELECT sys_context('USERENV','DB_NAME') AS Instance FROM dual");

		ParameterHolder parameters = new ParameterHolder();
		dbName = eds.queryForString(selectSql.toString(), parameters);
		return dbName;
	}

	public Map returnMessageText(String textToReturn)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT ? as MESSAGE_TEXT FROM DUAL ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(textToReturn);

		Map textMap = new HashMap();

		textMap = eds.queryForMap(selectSql.toString(), params);

		String messageText = (String) textMap.get("MESSAGE_TEXT");

		textMap.put("MESSAGE_TEXT", messageText);

		return textMap ;
	}

	public String getSamplePlan(String inspectionCodeDesc)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT INSPECTION_CODE ")
				.append("  FROM PWUST_INSPECTION_CODE_DEF ")
				.append(" WHERE INSPECTION_CODE_DESC = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(inspectionCodeDesc);

		String samplePlan = null;

		try
		{
			samplePlan = eds.queryForString(selectSql.toString(), params);
		}
		catch (Exception e)
		{

		}

		return samplePlan;
	}

	public String getInspectionCodeDesc(String samplePlan)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT INSPECTION_CODE_DESC ")
				.append("  FROM PWUST_INSPECTION_CODE_DEF ")
				.append(" WHERE INSPECTION_CODE = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(samplePlan);

		String inspectionCode = null;

		try
		{
			samplePlan = eds.queryForString(selectSql.toString(), params);
		}
		catch (Exception e)
		{

		}

		return inspectionCode;
	}

	public String selectDataValueFromGeneralLookup(String dataField) 

	{   
		StringBuffer selectSql = new StringBuffer().append("SELECT DATA_VALUE ")
				.append("  FROM PWUE_GENERAL_LOOKUP ")
				.append(" WHERE DATA_FIELD = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(dataField);

		String dataValue = null;

		try
		{
			dataValue = eds.queryForString(selectSql.toString(), parameters);
		}
		catch (Exception e)
		{
			dataValue = null;
		}

		return dataValue;
	}

	// SEOE_WOE_39 Defect 2865
	public Long selectJMSExpirationTimeout(String transactionShortname) 
	{   
		long waitSeconds = 60000; 
		String parameterValue;

		StringBuffer selectSql = new StringBuffer().append("select PARAMETER_VALUE from PWUST_GLOBAL_CONFIG ")
				.append(" where PARAMETER_NAME = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(transactionShortname+"_JMS_EXPIRATION_SECONDS");

		List list = eds.queryForList(selectSql.toString(), parameters);

		if (list.size() == 1)
		{
			Map map = (Map) list.get(0);
			parameterValue = (String) map.get("PARAMETER_VALUE");
		}
		else
		{
			parameterValue = null;
		}

		if (!StringUtils.equals(parameterValue, null))
		{
			waitSeconds = Long.parseLong(parameterValue);  
		}			

		return waitSeconds;    	
	}

	public String selectUserPriv(String userId, String priv)
	{
		String returnStatus = "";
		StringBuffer selectSql = new StringBuffer().append("SELECT SFMFG.SFCORE_HAS_PRIV( ?, ?) AS PRIV ")
				.append(eds.getDualTable());

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(userId);
		parameters.addParameter(priv);
		try
		{
			returnStatus = eds.queryForString(selectSql.toString(), parameters);
		} catch (Exception e) {
			returnStatus = "";
		}
		return returnStatus;
	}

	public String selectSfdbGuid()
	{
		String guid = "";
		StringBuffer selectSql = new StringBuffer().append("SELECT SFDB_GUID AS KEY FROM DUAL");

		guid = eds.queryForString(selectSql.toString());

		return guid;
	}

	public String getUserFullName(String userid)
	{
		StringBuffer selectSQL = new StringBuffer().append("SELECT NVL(FULL_NAME, FIRST_NAME || ' ' || LAST_NAME) AS FULL_NAME ")
				.append(" FROM SFFND_USER ")
				.append(" WHERE USERID = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(userid);

		/*
		 * All users supposed to have a 'Full Name' but no guarantee
		 */
		try{
			Map map = eds.queryForMap(selectSQL.toString(), params);
			return (String) map.get("FULL_NAME");
		}catch(Exception e){
			return ContextUtil.getUsername();
		}
	}

	public String getUserEmail(String userId) {

		StringBuffer selectSql = new StringBuffer().append("SELECT ")
				.append(" nvl(t.email_address,'NONE') AS EMAIL_ADDRESS ")
				.append(" FROM ")
				.append(" sffnd_user t ")
				.append(" WHERE ")
				.append(" t.userid = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(userId);

		Map selectApprovers = eds.queryForMap(selectSql.toString(), parameters);
		return (String) selectApprovers.get("EMAIL_ADDRESS");    
	}

	public void insertErrorLog(String errorId,
			String loadId,
			String transactionType,
			String returnCode,
			String messageId,
			String messageText,
			String key1,
			String key2,
			String key3,
			String userName)
	{
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_ERROR_LOG (")
				.append("    ERROR_ID, ")
				.append("    LOAD_ID, ")
				.append("    TRANS_TYPE, ")
				.append("    RETURN_CODE, ")
				.append("    MSG_ID, ")
				.append("    MSG_TEXT, ")
				.append("    KEY1, ")
				.append("    KEY2, ")
				.append("    KEY3, ")
				.append("    UPDT_USERID, ")
				.append("    TIME_STAMP, ")
				.append("    LAST_ACTION ")
				.append(") VALUES ( ")
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
				.append("  SYSDATE, ")
				.append("    ? ) ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(errorId);
		parameters.addParameter(loadId);
		parameters.addParameter(transactionType);
		parameters.addParameter(returnCode);
		parameters.addParameter(messageId);
		parameters.addParameter(messageText);
		parameters.addParameter(key1);
		parameters.addParameter(key2);
		parameters.addParameter(key3);
		parameters.addParameter(userName);
		parameters.addParameter(StringUtils.upperCase(SoluminaConstants.INSERT));

		eds.insert(insertSql.toString(), parameters);
	}

	public String getGlobalConfigValue(String globalParamName)
	{   
		String globalParamValue = null;

		StringBuffer selectSql = new StringBuffer().append(" SELECT PARAMETER_VALUE ")
				.append("   FROM SFFND_GLOBAL_CONFIGURATION ")
				.append("  WHERE PARAMETER_NAME = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(globalParamName);

		try
		{
			globalParamValue = eds.queryForString(selectSql.toString(), params);   
		}
		catch (Exception e)
		{
			globalParamValue = null;
		}

		return globalParamValue;
	}

	public List getWorkLocListByPriv(String userid, String priv)
	{   
		List workLocList = null;
		if( user.hasPrivilege(priv))	
		{
			StringBuffer selectSql = new StringBuffer().append("SELECT DISTINCT WORK_LOC AS NEW_ASGND_WORK_LOC, LOC_TITLE, C.COMPANY")
					.append("  FROM SFFND_WORK_LOC_DEF L,  SFFND_COMPANY_DEF C")
					.append(" WHERE NVL(L.OBSOLETE_RECORD_FLAG, 'N') = 'N'")
					.append("   AND L.COMPANY_ID = C.COMPANY_ID")
					.append(" ORDER BY WORK_LOC");

			ParameterHolder parameters = new ParameterHolder();
			workLocList = eds.queryForList(selectSql.toString(), parameters);
		}
		else
		{
			StringBuffer selectSql = new StringBuffer().append("SELECT DISTINCT WORK_LOC AS NEW_ASGND_WORK_LOC, LOC_TITLE, C.COMPANY")
					.append("  FROM SFFND_USER_WORK_CENTERS W, SFFND_WORK_LOC_DEF L, SFFND_COMPANY_DEF C")
					.append(" WHERE W.ASGND_LOCATION_ID = L.LOCATION_ID")
					.append("   AND L.COMPANY_ID = C.COMPANY_ID")
					.append("   AND NVL(L.OBSOLETE_RECORD_FLAG, 'N') = 'N'")
					.append("   AND W.USERID = ?")
					.append(" ORDER BY WORK_LOC");

			ParameterHolder parameters = new ParameterHolder();
			parameters.addParameter(userid);	
			workLocList = eds.queryForList(selectSql.toString(), parameters);

		}

		return workLocList;

	}		


	public String getParentOrderId(String orderId)
	{   
		String parentOrderId = null;

		StringBuffer selectSql = new StringBuffer().append(" SELECT PARENT_ORDER_ID  ")
				.append("   FROM SFWID_ORDER_DESC ")
				.append("  WHERE ORDER_ID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);

		try
		{
			parentOrderId = eds.queryForString(selectSql.toString(), params);   
		}
		catch (Exception e)
		{
			parentOrderId = null;
		}

		return parentOrderId;
	}	    

	//defect 1430
	public String getOrderIdOrderNo(String orderNo)
	{
		String orderId = null;

		StringBuffer selectSql = new StringBuffer().append(" SELECT ORDER_ID  ")
				.append("   FROM SFWID_ORDER_DESC ")
				.append("  WHERE ORDER_NO = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderNo);

		try
		{
			orderId = eds.queryForString(selectSql.toString(), params);   
		}
		catch (Exception e)
		{
			orderId = null;
		}

		return orderId;
	}
	//defect 1430
	public String getLotIdfromLotNo(String lotNo,String orderId)
	{
		String lotId = null;

		StringBuffer selectSql = new StringBuffer().append(" SELECT LOT_ID  ")
				.append("   FROM SFWID_LOT_DESC ")
				.append("  WHERE ORDER_ID = ? AND LOT_NO = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(lotNo);

		try
		{
			lotId = eds.queryForString(selectSql.toString(), params);   
		}
		catch (Exception e)
		{
			lotId = null;
		}

		return lotId;
	}

	//defect 1430
	public String getSerialfromOrderNo(String orderId, String lotNo)
	{

		String serialNoString = "";
		List serialNosList = null;

		String lotId = getLotIdfromLotNo(lotNo,orderId);

		StringBuilder selectSql = new StringBuilder().append(" SELECT SERIAL_NO ")
				.append(" FROM SFWID_SERIAL_DESC ")
				.append(" WHERE ORDER_ID = ?  " )
				.append(" AND LOT_ID = ? ")
				.append(" AND SERIAL_STATUS IN( ?, ?, ?, ? )");


		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(lotId);
		parameters.addParameter("PENDING");
		parameters.addParameter("IN QUEUE");
		parameters.addParameter("IN PROCESS");
		parameters.addParameter("ACTIVE");

		serialNosList = eds.queryForList(selectSql.toString(), parameters);
		ListIterator serialIt = serialNosList.listIterator();
		while (serialIt.hasNext()){
			Map operationKeys = (Map) serialIt.next();
			serialNoString = serialNoString + (String) operationKeys.get("SERIAL_NO") + ";";
		}
		return serialNoString;

	}

	//defect 1451
	public String getSerialIdfromSerialNo(String serialNo, String orderId)
	{

		String serialId = "";





		StringBuilder selectSql = new StringBuilder().append("SELECT ")
				.append("SERIAL_ID ")
				.append("FROM ")
				.append("SFWID_SERIAL_DESC ")
				.append("WHERE ")
				.append(" SERIAL_NO = ? ")
				.append(" AND ORDER_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(serialNo);
		parameters.addParameter(orderId);


		serialId = eds.queryForString(selectSql.toString(), parameters);



		return serialId;
	}
	
	

	public Map getSfwidOperDescMap(String orderId,Number operKey,Number stepKey ){

		Map sfwidOperDescMap = null;
		StringBuilder selectSql = new StringBuilder().append(" SELECT T.* ")
				.append(" FROM SFWID_OPER_DESC T")
				.append(" WHERE T.ORDER_ID = ? " )
				.append(" AND T.OPER_KEY = ? ")
				.append(" AND T.STEP_KEY = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		sfwidOperDescMap = eds.queryForMap(selectSql.toString(), parameters);

		return sfwidOperDescMap;
	}


	public Map getSfwidOperBuyoffMap(String orderId, Number operKey,Number stepKey,String buyoffId){


		Map sfwidOperBuyoffMap = null;
		StringBuilder selectSql = new StringBuilder().append(" SELECT T.* ")
				.append(" FROM SFWID_OPER_BUYOFF T ")
				.append(" WHERE T.ORDER_ID = ? ")
				.append(" AND T.OPER_KEY = ? ")
				.append(" AND T.STEP_KEY = ? ")
				.append(" AND T.BUYOFF_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(buyoffId);

		sfwidOperBuyoffMap = eds.queryForMap(selectSql.toString(), parameters);
		return sfwidOperBuyoffMap;

	}


	//defect 1462
	public List getSerialNos(String orderId)
	{

		List serials = null;

		StringBuilder selectSql = new StringBuilder().append("SELECT DISTINCT ")
				.append("SERIAL_NO, '' AS SECURITY_GROUP ")
				.append("FROM ")
				.append("SFWID_SERIAL_DESC ")
				.append("WHERE ")
				.append(" order_id = ? ")
				.append("AND SERIAL_HOLD_STATUS is null ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);

		serials = eds.queryForList(selectSql.toString(), parameters);

		return serials;

	}

	//defect 1462
	public String getOrderNoOrderId(String orderId)
	{
		String orderNo = null;

		StringBuffer selectSql = new StringBuffer().append(" SELECT ORDER_NO  ")
				.append("   FROM SFWID_ORDER_DESC ")
				.append("  WHERE ORDER_ID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);

		try
		{
			orderNo = eds.queryForString(selectSql.toString(), params);   
		}
		catch (Exception e)
		{
			orderNo = null;
		}

		return orderNo;
	}
	//defect 1685 get sales order number from orderid
	public String getSalesOrderNoFromOrderId(String orderId)
	{
		String orderNo = null;

		StringBuffer selectSql = new StringBuffer().append(" SELECT PW_ORDER_NO  ")
				.append("   FROM PWUST_SFWID_ORDER_DESC ")
				.append("  WHERE ORDER_ID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);

		try
		{
			orderNo = eds.queryForString(selectSql.toString(), params);   
		}
		catch (Exception e)
		{
			orderNo = null;
		}

		return orderNo;
	}

	//defect 1685 retrieve oper no of parent to supplemental
	public String getSalesOrderOpFromOrderNo(String orderNo)
	{
		String operNo = null;

		StringBuffer selectSql = new StringBuffer().append(" select UCF_ORDER_VCH13 ")
				.append("   from SFWID_ORDER_DESC t, pwust_sfwid_order_desc p ")
				.append("   where p.order_id = t.order_id and pw_order_no = ? ");



		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderNo);

		try
		{
			operNo = eds.queryForString(selectSql.toString(), params);   
		}
		catch (Exception e)
		{
			operNo = null;
		}

		return operNo;
	}

	//defect 1392
	public void updatePwSubOrderType(String orderId,String PWSubOrderType) {
		StringBuffer updateSql = new StringBuffer().append(" UPDATE PWUST_SFWID_ORDER_DESC  T "+
				" SET T.PW_SUB_ORDER_TYPE = ? " +
				" WHERE T.ORDER_ID =  ? " );
		ParameterHolder params = new ParameterHolder();
		params.addParameter(PWSubOrderType);
		params.addParameter(orderId);

		eds.update(updateSql.toString(), params);
	}

	//defect 1742 
	public String getPlanIdFromPlanNo(String planNo)
	{
		String planId = null;

		StringBuffer selectSql = new StringBuffer().append(" SELECT distinct plan_id  ") // defect 1749
				.append("   FROM SFPL_PLAN_DESC ")
				.append("  WHERE PLAN_NO = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(planNo);

		try
		{
			planId = eds.queryForString(selectSql.toString(), params);   
		}
		catch (Exception e)
		{
			planId = null;
		}

		return planId;
	}

	//begin defect 1413
	public void updateCommodityJurisdiction(String PLAN_ID) {
		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFPL_PLAN_DESC  T "+
				" SET T.COMMODITY_JURISDICTION = ? " +
				" WHERE T.PLAN_ID =  ? " );
		ParameterHolder params = new ParameterHolder();
		params.addParameter("");
		params.addParameter(PLAN_ID);

		eds.update(updateSql.toString(), params);
	} //end defect 1413

	// Defect 1757
	public String getPlanItemNo(String planNo)
	{
		String planItemNo = null;

		StringBuffer selectSql = new StringBuffer().append(" SELECT DISTINCT PART_NO  ") 
				.append("   FROM SFPL_PLAN_DESC ")
				.append("  WHERE PLAN_NO = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(planNo);

		try
		{
			planItemNo = eds.queryForString(selectSql.toString(), params);   
		}
		catch (Exception e)
		{
			planItemNo = null;
		}

		return planItemNo;
	}

	//begin defect 1711
	public List getSerialNumbers(String orderId, Number operKey)
	{
		List serials = null;

		StringBuffer selectSql = new StringBuffer().append("select serial_no as SERIAL_NO "+
				"from  sfwid_Serial_oper a, sfwid_serial_desc b "+
				" where a.order_id = ? " +
				" and a.oper_key = ? " +
				" and a.serial_oper_status = 'COMPLETE' " +
				" and a.order_id = b.order_id " +
				" and a.lot_id = b.lot_id " +
				" and a.serial_id = b.serial_id " +
				" and b.serial_status != 'STOP' " +
				" and UPPER(b.serial_no) not LIKE '%SPLIT%' ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);

		serials = eds.queryForList(selectSql.toString(), parameters);

		return serials;
	}

	//defect 1782
	public String getOperNofromOperKeyStepKey(Number operKey,Number stepKey, String orderId)
	{

		String operlNo = "";


		StringBuilder selectSql = new StringBuilder().append("SELECT ")
				.append("OPER_NO ")
				.append("FROM ")
				.append("SFWID_OPER_DESC ")
				.append("WHERE ")
				.append(" OPER_KEY = ? ")
				.append(" AND STEP_KEY = ? ")
				.append(" AND ORDER_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(orderId);


		operlNo = eds.queryForString(selectSql.toString(), parameters);



		return operlNo;

	}



	//defect 1775
	public boolean isLastActiveOperation(String orderId)
	{
		//determine if this is the last active operation
		boolean isLast = false;

		StringBuilder selectSql = new StringBuilder().append("SELECT count(*) FROM ")
				.append("(SELECT STATUS FROM SFWID_ORDER_NODE A ")
				.append(" WHERE  ORDER_ID = ? ") 
				.append(" AND STATUS = 'ACTIVE' ")
				.append(" AND NOT EXISTS (SELECT  * FROM SFWID_ORDER_LINK  WHERE ORDER_ID = ? ")
				.append(" AND  PRED_NODE_ID = A.NODE_ID)) ");



		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(orderId);

		int rowCount = eds.queryForInt(selectSql.toString(), parameters);

		if (rowCount == 1)
		{
			isLast = true;
		}

		return isLast;

	}

	//DEFECT 1775 1834
	public boolean isLastActiveBuyoffInAllOperations(String orderId)
	{
		//determine if this is the last active operation
		boolean isLast = false;

		StringBuilder selectSql = new StringBuilder().append("SELECT count(*) FROM ")
				.append("( SELECT DISTINCT A.BUYOFF_ID FROM PWUST_OPER_BUYOFF_V A, SFWID_OPER_DESC B ")
				.append(" WHERE A.ORDER_ID = ? ") 
				.append(" and B.ORDER_ID = A.ORDER_ID ")
				.append(" and A.OPER_NO = B.OPER_NO ")
				.append(" and A.OPER_KEY = B.OPER_KEY ")
				.append(" and B.INCLUDED != 'EXCLUDED' ")
				.append(" AND A.BUYOFF_STATUS NOT IN ('Closed-N/A','Closed-N/D','ACCEPT')) ");


		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);

		int rowCount = eds.queryForInt(selectSql.toString(), parameters);


		if (rowCount < 2) // defect 1834
		{
			isLast = true;
		}

		return isLast;

	}
	//DEFECT 1775
	public boolean isLastActiveBuyoffInSpecificOperationBySerial(String orderId, Number operKey, String serial_no)
	{
		//determine if this is the last active operation
		boolean isLast = false;

		StringBuilder selectSql = new StringBuilder().append("SELECT count(*) FROM ")
				.append("(select a.buyoff_id, b.serial_id,b.buyoff_status from SFWID_OPER_BUYOFF a, sfwid_serial_oper_buyoff b ")
				.append(" WHERE  A.ORDER_ID = ? ")
				.append(" and    A.OPER_KEY = ? " )
				.append(" AND    B.BUYOFF_STATUS != 'ACCEPT' ")
				.append(" and    A.ORDER_ID = B.ORDER_ID ")
				.append(" and    A.OPER_KEY = B.OPER_KEY ")
				.append(" and    A.BUYOFF_ID = B.BUYOFF_ID ")
				.append(" and    A.STEP_KEY = B.STEP_KEY ")
				.append(" AND b.SERIAL_ID IN (select serial_id  from SFWID_SERIAL_HOLDS t where order_id = ? ")
				.append(" and (select serial_no from sfwid_serial_desc a where a.serial_id =  b.serial_id) in (select regexp_substr( ? ,'[^;]+', 1, level) from dual ")
				.append(" connect by regexp_substr( ? , '[^;]+', 1, level) is not null))) ");




		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(orderId);
		parameters.addParameter(serial_no);
		parameters.addParameter(serial_no);

		int rowCount = eds.queryForInt(selectSql.toString(), parameters);

		if (rowCount == 1)
		{
			isLast = true;
		}

		return isLast;

	}

	//DEFECT 1775
	public boolean isLastActiveBuyoffInSpecificOperation(String orderId, Number operKey)
	{
		//determine if this is the last active operation
		boolean isLast = false;

		StringBuilder selectSql = new StringBuilder().append("SELECT count(*) FROM ")
				.append("(select A.OPER_NO from SFWID_OPER_BUYOFF a, sfwid_serial_oper_buyoff b ")
				.append(" WHERE  A.ORDER_ID = ? ")
				.append(" and    A.OPER_KEY = ? " )
				.append(" AND    B.BUYOFF_STATUS != 'ACCEPT' ")
				.append(" and    A.ORDER_ID = B.ORDER_ID ")
				.append(" and    A.OPER_KEY = B.OPER_KEY ")
				.append(" and    A.BUYOFF_ID = B.BUYOFF_ID ")
				.append(" and    A.STEP_KEY = B.STEP_KEY ") 
				.append(" GROUP BY A.OPER_NO ) ");



		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);


		int rowCount = eds.queryForInt(selectSql.toString(), parameters);

		if (rowCount == 1)
		{
			isLast = true;
		}

		return isLast;

	}

	//defect 1829
	@SuppressWarnings("rawtypes")
	public List selectSforSfplPlanSubjectData(String planId, Number planUpdtNo, Number subjectNo){

		StringBuffer select = new StringBuffer().append(" SELECT T.* FROM SFOR_SFPL_PLAN_SUBJECT_PART T WHERE T.PLAN_ID= ? AND T.PLAN_UPDT_NO= ? AND T.SUBJECT_NO = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(subjectNo);

		List list = eds.queryForList(select.toString(), params);

		return list;
	}
	//defect 1844
	public List selectPlanInfoUsingPrimaryKey(String primaryKey) {

		StringBuffer select = new StringBuffer().append(" SELECT * FROM pwust_planned_orders_v WHERE primery_key = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(primaryKey);

		List list = eds.queryForList(select.toString(), params);

		return list;

	}

	//defect 1861
	public boolean partExists(String part_no)
	{
		StringBuffer selectSQL = new StringBuffer().append(" SELECT COUNT(*) ")
				.append("   FROM SFPL_ITEM_DESC ")
				.append("  WHERE PART_NO = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(part_no);

		int count = eds.queryForInt(selectSQL.toString(), params);
		return (count > 0);
	}

	//defect 1873
	public List getRelatedElements(String order_id)
	{

		StringBuffer selectSQL = new StringBuffer().append(" SELECT DISTINCT UCF_ORDER_VCH15,PART_NO,UCF_ORDER_VCH12 ")
				.append(" FROM SFWID_ORDER_DESC ")
				.append(" WHERE ORDER_ID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(order_id);

		return eds.queryForList(selectSQL.toString(), params);

	}

	//defect 1873
	public List getrelatedOrders(String order_id)
	{
		String ucf_order_vch15 = "";
		String ucf_order_vch_12 = "";
		String part_no = "";

		List relatedElements = getRelatedElements(order_id);

		ListIterator relatedElementsIt = relatedElements.listIterator();
		if (relatedElementsIt.hasNext()){
			Map relatedElementsKeys = (Map) relatedElementsIt.next();
			ucf_order_vch15 = (String) relatedElementsKeys.get("UCF_ORDER_VCH15");
			ucf_order_vch_12 = (String) relatedElementsKeys.get("UCF_ORDER_VCH12");
			part_no = (String) relatedElementsKeys.get("PART_NO");
		}

		StringBuffer selectSQL = new StringBuffer().append(" SELECT T.ORDER_ID,T1.PW_ORDER_NO ")
				.append(" FROM SFMFG.SFWID_ORDER_DESC T, SFMFG.PWUST_SFWID_ORDER_DESC T1 ")
				.append(" WHERE T.ORDER_ID = T1.ORDER_ID ")
				.append(" AND T.ORDER_ID != ? ")
				.append(" AND ((T.UCF_ORDER_VCH15 IS NULL AND NVL('',NULL) IS NULL) OR ")
				.append(" T.UCF_ORDER_VCH15 = NVL( ? ,NULL)) ")
				.append(" AND T.UCF_ORDER_VCH12 = ? ")
				.append(" AND T.PART_NO = ? ");


		ParameterHolder params = new ParameterHolder();
		params.addParameter(order_id);
		params.addParameter(ucf_order_vch15);
		params.addParameter(ucf_order_vch_12);
		params.addParameter(part_no);

		return eds.queryForList(selectSQL.toString(), params);

	}

	//SMRO_USR_205 - Certifcations SCR-002 Defect 1840
	public void removeAlternateCertification(String cert, String alternateKey,String workLoc)
	{
		Certification mainCert = certificationValidator.checkExistance(cert);
		

		StringBuffer selectSql = new StringBuffer().append("DELETE FROM ")
				.append("	PWUE_GENERAL_LOOKUP ")
				.append("WHERE  DATA_VALUE_1 = ? ")
				.append(" AND DATA_VALUE = ?")
				.append(" AND  DATA_FIELD = 'CERT' ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(cert);	
		params.addParameter(workLoc);	    
		dao.delete(selectSql.toString(), params);
		
	}
	
	//DEFECT 1906
	public List getMMobjectSecurityGroups(String objectId,String objectTag)
	{
		StringBuffer selectSQL = new StringBuffer().append(" SELECT DISTINCT SECURITY_GROUP ")
				.append(" FROM SFCORE_MM_OBJECT_SEC_GRP ")
				.append(" WHERE OBJECT_ID = ? ")
				.append(" AND OBJECT_TAG = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(objectId);
		params.addParameter(objectTag);

		return eds.queryForList(selectSQL.toString(), params);
	}
	
	//defect 1994
	public String getSerialNofromSerialId(String serialId, String orderId)
	{

		String serialNo = "";





		StringBuilder selectSql = new StringBuilder().append("SELECT ")
				.append("SERIAL_NO ")
				.append("FROM ")
				.append("SFWID_SERIAL_DESC ")
				.append("WHERE ")
				.append(" SERIAL_ID = ? ")
				.append(" AND ORDER_ID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(serialId);
		parameters.addParameter(orderId);


		serialNo = eds.queryForString(selectSql.toString(), parameters);



		return serialNo;
	}
	
	//defect 2010
	public String getEngineManualInfo(String manualNo,String manualRev, String orderId)
	{

		List manualInfo = null;
        String manualString = "";



		StringBuilder selectSql = new StringBuilder().append("SELECT ")
				.append("DISTINCT t.MANUAL_REV,t.MANUAL_PROVIDER,TRIM(TO_CHAR(z.MANUAL_RELEASE_DATE, 'DD MON YYYY')) AS MANUAL_RELEASE_DATE ")
				.append("FROM ")
				.append("pwmroi_order_subject_authority t, ")
				.append("pwmroi_manual_desc z ")
				.append("WHERE ")
				.append("t.MANUAL_NO = ? ")
				.append("AND t.MANUAL_REV = ? ")
				.append("AND t.ORDER_ID = ? ")
				.append("and z.MANUAL_REV = t.MANUAL_REV ")
				.append("and z.MANUAL_NO = t.MANUAL_NO ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(manualNo);
		parameters.addParameter(manualRev);
		parameters.addParameter(orderId);


		manualInfo = eds.queryForList(selectSql.toString(), parameters);
		
		System.out.println("selectsql - " + selectSql.toString());

		ListIterator manualInfoIt = manualInfo.listIterator();
		while (manualInfoIt.hasNext()){
			Map manualInfoKeys = (Map) manualInfoIt.next();
			manualString = manualString + (String) manualInfoKeys.get("MANUAL_PROVIDER") + " " + manualNo + " Rev: " + manualInfoKeys.get("MANUAL_REV") + " Dated: " + manualInfoKeys.get("MANUAL_RELEASE_DATE");
		}


		return manualString;
	}
	  
}

