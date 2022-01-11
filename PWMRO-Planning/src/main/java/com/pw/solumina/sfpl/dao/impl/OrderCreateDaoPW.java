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
*  File:    OrderCreateDaoPW.java
*  
*  PW_SMRO_eWORK_202-Order-Creation
* 
*  Created: 2017.08.09
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date             Who            Description
*  -----------    ------------     ---------------------------------------------------
*  2018-03-08       Fred Ettefagh  
*  2018-03-16       Fred Ettefagh  defect 409
*  2018-03-19       Fred Ettefagh  fixed typo in for update sql in method updateSfwidOrderSubjectTaskIncludeFlag
*  2018-03-26       Fred Ettefagh  defect 412  change how overhaul overs are created via input matrix
*  2018-03-269      Fred Ettefagh  defect 386  long order no
*  2018-04-05		Fred Ettefagh  Defect 577
*  2018-04-29		Fred Ettefagh  defect 632
*  2018-05-04       Fred Ettefagh  defect 754  Input Matrix not including expected task groups - All Customers
*  2018-05-04       Fred Ettefagh  added check in method insertIntoErrorTable to prevent "value too large for column" sql errors  
*  2018-05-04       Fred Ettefagh  defect 754  for update all task, removed join SUBJECT_NO
*  2018-05-10       Fred Ettefagh  defect 765  added trace code to track OOB error Scheduled Start Date is required and must be within the effectivity range
*  2018-05-11       Fred Ettefagh  defect 754  changed task update dao to fix customer ALL filter
*  2018-05-18       Fred Ettefagh  defect N/A  changed oob method insertCopyOrderNode to fix production time out issue.
*  2018-05-22       Fred Ettefagh  defect N/A  Changed order create to commit after each call to OOB order create method 
*  2018-07-09       Fred Ettefagh  Changed code for defect 820, update tasks based on each IM data row
*  2018-07-13       Fred Ettefagh  Changed sql code for defect 820 to fix sub net select for task update
*  2018-07-13       Fred Ettefagh  Removed hard coded value in Work order SQL lookup from previous commit 
*  2018-08-30		Bob Preston	   SMRO_WOE_209 defect 748, Added updateOrderParentInfo().
*  2018-10-01       Fred Ettefagh  Defect 980 Cannot Create Suborder due to supNet subNet and conf Numbers
*  2010-03-15	    Raeann Thorpe  Defect 1107 -  modified selectOrderTaskToUpdateStatus
*  2019-04-09       Fred Ettefagh  defect 1182, sp4 upgrade, added method  updateSfwidOrderDescWorkLoc and selectSfplPlanV
*  2019-04-19       Fred Ettefagh  added code for repair order create 
*  2019-04-19       Fred Ettefagh  defect 1104, start and complete WO ALT when updating tasks after WO create. 
*  2019-05-03       Fred Ettefagh  added new methods used for repair WO create  
*  2019-06-05       Fred Ettefagh  deleted method insertIntoPwustSfwidOrderDesc
*  2019-10-17       Fred Ettefagh  PW_SMRO_eWORK_202, defect 1389, fixed typo in method selectSfplPlanV
*  2019-11-20       Fred Ettefagh  SMRO_TR_319, move method to update order's ASGND_LOCATION_ID to commonDaoPW.updateSfwidOrderDescWorkLoc
*  2020-01-30       John DeNinno   PW_SMRO_eWORK_202 , defect 953 - insert create by date clock
*  2020-11-13       John DeNinno   Defect 1267 Missing Parameter
*/
package com.pw.solumina.sfpl.dao.impl;

import static com.ibaset.common.DbColumnNameConstants.INCLUDED;
import static com.ibaset.common.FrameworkConstants.INSERTED;
import static com.ibaset.common.FrameworkConstants.YES;
import static com.ibaset.common.SoluminaConstants.EXCLUDED;
import static com.ibaset.common.SoluminaConstants.MRO_PART;
import static com.ibaset.common.SoluminaConstants.MRO_UPGRADE;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.solumina.exception.SoluminaException;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.common.util.SoluminaUtils;

public class OrderCreateDaoPW {
	
	@Reference 	private JdbcDaoSupport jdbcDaoSupport;
	@Reference	private LogOrderCreateErrors logOrderCreateErrors;

	public List selectSfwidOrderDescMulti (String ucfOrderVch15){
		
		ParameterHolder params = new ParameterHolder();
		
		StringBuffer select = new StringBuffer().append(" SELECT T.* " +
	                                                    " FROM SFWID_ORDER_DESC T "+
	                                                    " WHERE T.UCF_ORDER_VCH15 = ? ");
		
		params.addParameter(ucfOrderVch15);
		List list = jdbcDaoSupport.queryForList(select.toString(), params);
		return list;		
	}
		
	// defect 1182
	public int updateSfwidOrderDescWorkLoc(String orderId, String asgndLocationID){

		StringBuffer updateSql = new StringBuffer().append("UPDATE SFWID_ORDER_DESC T  SET T.ASGND_LOCATION_ID = ? WHERE T.ORDER_ID = ? ");
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(asgndLocationID);
		parameters.addParameter(orderId);

		int i = jdbcDaoSupport.update(updateSql.toString(), parameters);
		return i;
	}
	
	// defect 1182
	public Map selectSfplPlanV(String planId, Number planVer, Number planRev, Number planAlt){

		Map returnMapVal = null;
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT T.* " + 
		                                                   " FROM SFPL_PLAN_V T " +
		                                                   " WHERE T.PLAN_ID = ? " +
		                                                   " AND T.PLAN_VERSION = ? " +
		                                                   " AND T.PLAN_REVISION = ? " +
		                                                   " AND T.PLAN_ALTERATIONS = ? ");
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(planId);
		parameters.addParameter(planVer);
		parameters.addParameter(planRev);
		parameters.addParameter(planAlt);
		
		List list =jdbcDaoSupport.queryForList(selectSql.toString(), parameters);
		returnMapVal = (Map)list.get(0);
		return returnMapVal;
		
	}
	
	// defect 820
	public int updatePwustSfwidOrderDesc (String pwOrderNo,
			                              String workScope,
			                              String superNet,
			                              String subNet,
			                              String customer){
		
		
		StringBuffer updateSql = new StringBuffer().append(" UPDATE PWUST_SFWID_ORDER_DESC T " +
		                                                   " SET T.WORK_SCOPE = ?, "+
		                                                   "     T.SUPERIORNET = ?, "+
		                                                   "     T.SUBNET = ?, "+
		                                                   "     T.CUSTOMER = ? " +
		                                                   " WHERE T.PW_ORDER_NO = ? " );

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(workScope);
		parameters.addParameter(superNet);
		parameters.addParameter(subNet);
		parameters.addParameter(customer);
		parameters.addParameter(pwOrderNo);
		
		int numbOfRows = jdbcDaoSupport.update(updateSql.toString(), parameters);
		return numbOfRows;
				
		
		
	}
	
	
	
	public void insertCopyOrderNode(String orderId,
								   String planId,
								   Number planVersion,
								   Number planRevision,
								   Number planAlterations,
								   List  operationInformationList) {
		   
			StringBuffer insertSql = new StringBuffer().append("INSERT INTO SFWID_ORDER_NODE ( ")
										   .append("    ORDER_ID, ")
										   .append("    NODE_ID, ")
										   .append("    STATUS, ")
										   .append("    UPDT_USERID, ")
										   .append("    TIME_STAMP, ")
										   .append("    LAST_ACTION, ")
										   .append("    OPER_KEY, ")
										   .append("    STEP_KEY, ")
										   .append("    NODE_TYPE, ")
										   .append("    NODE_NO, ")
										   .append("    NODE_TITLE, ")
										   .append("    NODE_ROW, ")
										   .append("    NODE_COLUMN, ")
										   .append("    NODE_DESC, ")
										   .append("    DECISION_TYPE, ")
										   .append("    RETURN_TO_OPER_NO, ")
										   .append("    RETURN_TO_OPER_KEY ) ")
										   .append(" SELECT ")
										   .append("    ?, ")
										   .append("    NODE_ID, ")
										   .append("    CASE ")	//FND-22926 Insert status only for node type Operation
			                               .append("    WHEN NODE_TYPE = ? THEN ? ")
			                               .append("	ELSE NULL ")
			                               .append("	END, ")
										   .append("    ?, ")
										   .append("    "  + jdbcDaoSupport.getTimestampFunction()  + ", ")
										   .append("    ?, ")
										   .append("    OPER_KEY, ")
										   .append("    ?, ")
										   .append("    NODE_TYPE, ")
										   .append("    NODE_NO, ")
										   .append("    NODE_TITLE, ")
										   .append("    NODE_ROW, ")
										   .append("    NODE_COLUMN, ")
										   .append("    NODE_DESC, ")
										   .append("    DECISION_TYPE, ")
										   .append("    RETURN_TO_OPER_NO, ")
										   .append("    RETURN_TO_OPER_KEY ")
										   .append(" FROM  SFPL_PLAN_NODE_REV_V ")
										   .append(" WHERE  PLAN_ID = ? ")
										   .append(" AND    PLAN_VERSION = ? ")
							               .append(" AND    PLAN_REVISION = ? ")
							               .append(" AND    PLAN_ALTERATIONS = ?")
							               .append(" AND    OPER_KEY IN ( ");
			
			Set<Number> operationInformationSet = new HashSet<Number>();
			ListIterator operationIt = operationInformationList.listIterator();
           	while (operationIt.hasNext())
           	{
           		Map operationKeys = (Map) operationIt.next();
           		Number operOperationKey = (Number) operationKeys.get("OPER_KEY");

           		operationInformationSet.add(operOperationKey);
           	}

           	int i=0;
			for (Number operKey :operationInformationSet ){
				insertSql.append( operKey );
				i++;
				if (operationInformationSet.size()!=i){
					insertSql.append( "," );
				}
			}
			insertSql.append( ")" );
			
			/*
			ListIterator operationIt = operationInformationList.listIterator();
           	while (operationIt.hasNext())
           	{
           		Map operationKeys = (Map) operationIt.next();
           		Number operOperationKey = (Number) operationKeys.get("OPER_KEY");

           		
           		insertSql.append(operOperationKey);
           		if (operationIt.hasNext())
           		{
           			insertSql.append(", ");
           		}
           	}
           	*/

			ParameterHolder params = new ParameterHolder();
			params.addParameter(orderId);
			params.addParameter(SoluminaConstants.OPERATION);
			params.addParameter(SoluminaConstants.PENDING);
			params.addParameter(ContextUtil.getUsername());
			params.addParameter(SoluminaConstants.INSERTED);
			params.addParameter(-1);
			params.addParameter(planId);
			params.addParameter(planVersion);
			params.addParameter(planRevision);
			params.addParameter(planAlterations);
			jdbcDaoSupport.insert(insertSql.toString(), params);
	}

	public Map selectPlanEffectivityInformation(String planId,
									            String effectivityType,
									            Date scheduledStartDate) throws Exception{

			StringBuffer select = new StringBuffer().append("SELECT PLAN_VERSION, ")
										            .append("       PLAN_REVISION, ")
										            .append("       PLAN_ALTERATIONS ")
										            .append("FROM SFPL_PLAN_EFF ")
										            .append("WHERE  PLAN_ID = ? ")
										            .append("AND    EFF_TYPE = ? ")
										            .append("AND    ? > = EFF_FROM_DATE ")
										            .append("AND    ? < = CASE WHEN EFF_THRU_DATE IS NULL THEN ? ELSE EFF_THRU_DATE END");

			ParameterHolder params = new ParameterHolder();
			params.addParameter(planId);
			params.addParameter(effectivityType);
			params.addParameter(scheduledStartDate);
			params.addParameter(scheduledStartDate);
			params.addParameter(SoluminaUtils.parseDate(SoluminaConstants.MAX_DATE));
			
			List list = jdbcDaoSupport.queryForList(select.toString(), params);
			ListIterator it = list.listIterator();
			Map map = new HashMap();

			if (it.hasNext()){
				map = (Map) it.next();
			}

			return map;
	}
	
	public int updateSfwidOrderDescSalesOrderUCF(String orderID, String salesOrder){
		
		StringBuffer update = new StringBuffer().append("UPDATE SFWID_ORDER_DESC T SET T.UCF_ORDER_VCH11 = ? , T.UCF_ORDER_VCH12= ?  WHERE T.ORDER_ID = ? ");
		ParameterHolder params = new ParameterHolder();
		params.addParameter(salesOrder);
		params.addParameter(salesOrder);
		params.addParameter(orderID);
		return jdbcDaoSupport.update(update.toString(), params);
	}
	

	public Map selectPwustSfwidOrderDesc(String orderId){
		
		ParameterHolder params = new ParameterHolder();
		
		StringBuffer select = new StringBuffer().append(" SELECT T.* " +
	                                                    " FROM PWUST_SFWID_ORDER_DESC T "+
	                                                    " WHERE T.ORDER_ID = ? ");
		
		params.addParameter(orderId);
		Map map = jdbcDaoSupport.queryForMap(select.toString(), params);
		return map;		
	}
	
	public List selectMroOrderTasks(String orderId){
		
		StringBuilder selectSql = new StringBuilder().append(" SELECT T.* FROM SFOR_SFWID_ORDER_SUBJECT T WHERE ORDER_ID = ? ");

        ParameterHolder parameters = new ParameterHolder();
        parameters.addParameter(orderId);

        List  list = jdbcDaoSupport.queryForList(selectSql.toString(), parameters);
        return list;
	}
	
	public Map selectMroOrderTasks(String orderId,Number taskNo){
		
		StringBuilder selectSql = new StringBuilder().append(" SELECT T.* FROM SFOR_SFWID_ORDER_SUBJECT T WHERE T.ORDER_ID = ? AND T.SUBJECT_NO = ? ");

        ParameterHolder parameters = new ParameterHolder();
        parameters.addParameter(orderId);
        parameters.addParameter(taskNo);
        
        Map  map = jdbcDaoSupport.queryForMap(selectSql.toString(), parameters);
        return map;
	}
	
    public Map selectOrderDesc(String orderId)
    {
        StringBuilder selectSql = new StringBuilder().append("SELECT T.* FROM SFWID_ORDER_DESC T WHERE T.ORDER_ID = ? ");

        ParameterHolder parameters = new ParameterHolder();
        parameters.addParameter(orderId);

        Map map = jdbcDaoSupport.queryForMap(selectSql.toString(), parameters);
        
        return map;
    }
	

	
	public List<Map> selectOrderOperations(String orderId){

		List<Map> list = null;

		StringBuffer selectSql = new StringBuffer().append(" SELECT T.* FROM SFWID_OPER_DESC T WHERE T.ORDER_ID = ? AND  T.STEP_KEY = -1 ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);

		list = jdbcDaoSupport.queryForList(selectSql.toString(), parameters);

		return list;	
		
	}
	
	
	public Map selectOrderOperationNo(String orderId, String operNo){

		Map map = null;

		StringBuffer selectSql = new StringBuffer().append(" SELECT T.* FROM SFWID_OPER_DESC T WHERE T.ORDER_ID = ? AND T.OPER_NO = ? AND  T.STEP_KEY = -1 ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operNo);
		
		map = jdbcDaoSupport.queryForMap(selectSql.toString(), parameters);

		return map;	
		
	}
	
	
	public List<Map> selectAllTempOrderOperations(String orderId){

		List<Map> list = null;

		StringBuffer selectSql = new StringBuffer().append(" SELECT T.* " +
				                                           " FROM SFWID_OPER_DESC T " +
				                                           " WHERE T.ORDER_ID = ? " +
				                                           " AND T.OPER_NO LIKE ? " +
				                                           " AND  T.STEP_KEY = -1 ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter("%-TMP");
		list = jdbcDaoSupport.queryForList(selectSql.toString(), parameters);

		return list;	
		
	}	
	
	public int updatePwustSfwidOrderDesc(String salesOrder, String customer, String sapSerialNo){
		
		StringBuffer update = new StringBuffer().append(" UPDATE PWUST_SFWID_ORDER_DESC T "+
		                                                " SET T.CUSTOMER = ? , "+
		                                                "     T.SAP_SERIAL_NO = ? "+
		                                                " WHERE T.SALES_ORDER = ? ");
		
		ParameterHolder params = new ParameterHolder();
		
		params.addParameter(customer);
		params.addParameter(sapSerialNo);
		params.addParameter(salesOrder);
		
		return jdbcDaoSupport.update(update.toString(),params);
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
		
		StringBuffer insert = new StringBuffer().append("INSERT INTO PWUST_SFWID_ORDER_DESC ( ORDER_ID, " +
		                                                                                    " PW_ORDER_NO, "+ 
				                                                                            " SALES_ORDER, "+ 
		                                                                                    " ENGINE_MODEL, "+
		                                                                                    " WORK_SCOPE, "+
				                                                                            " SUPERIORNET, "+
		                                                                                    " SUBNET, "+
				                                                                            " CUSTOMER, "+
		                                                                                    " UPDT_USERID,"+
		                                                                                    " PW_ORDER_TYPE,"+
		                                                                                    " SALES_ORDER_CREATE_BY,"+     //DEFECT 953
		                                                                                    " SALES_ORDER_CREATE_DATE) "+  //DEFECT 953
	                                                    " VALUES ( ? , ? , ? , ? , ? , ? , ? , ?, ?, ?,?, SYSDATE ) ");    //DEFECT 953
		
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
		params.addParameter(ContextUtil.getUsername());
		
		
		jdbcDaoSupport.insert(insert.toString(),params);
	}
	
	private String selectPlanForOrderCreate = " SELECT A.PART_NO," +
												 	 " A.PART_CHG, "+
												 	 " A.PLAN_ID, "+ 
												 	 " A.PLAN_VERSION, "+
												 	 " MAX(A.PLAN_REVISION) AS PLAN_REVISION, "+
												 	 " A.PLAN_ALTERATIONS " +
											" FROM SFPL_PLAN_V A, "+ 
											     " SFFND_DOC_TYPE_DEF B, "+  
											     " SFOR_SFPL_PLAN_SUBJECT_PART C, "+  
											     " SFPL_PLAN_EFF D,  "+ 
											     " PWUST_PLAN_ENGINE_MODEL F "+ 
											" WHERE A.DOC_TYPE = B.DOC_TYPE "+ 
											" AND A.PLAN_TYPE = B.DOC_SUB_TYPE "+ 
											" AND B.INSTRUCTIONS_TYPE =  'MRO-Part' " + // 
											" AND A.REV_STATUS IN  ('PLAN RELEASED','PLAN COMPLETE') " +
											" AND D.EFF_TYPE = 'DATE' " + 
											" AND TRUNC(SYSDATE) BETWEEN TRUNC(D.EFF_FROM_DATE) AND TRUNC(NVL(D.EFF_THRU_DATE,TO_DATE( '01/01/9999' ,'MM/DD/YYYY'))) " +
											" AND A.PLAN_ID = C.PLAN_ID "+ 
											" AND A.PLAN_UPDT_NO = C.PLAN_UPDT_NO "+ 
											" AND A.PLAN_ID = D.PLAN_ID "+ 
											" AND A.PLAN_VERSION = D.PLAN_VERSION "+ 
											" AND A.PLAN_REVISION = D.PLAN_REVISION "+ 
											" AND A.PLAN_ALTERATIONS = D.PLAN_ALTERATIONS "+ 
											" AND A.PLAN_ID = F.PLAN_ID "+ 
											" AND A.PLAN_UPDT_NO = F.PLAN_UPDT_NO "+ 
											" AND A.DOC_TYPE = 'Process Plan' " +
											" AND UPPER(A.PLAN_TYPE) = UPPER( 'Overhaul' ) "+
											" AND A.PROGRAM = ? "+  //'ETV25CHC'
											" AND A.PLND_WORK_LOC = ? "+  //'6100'
											" AND F.ENGINE_MODEL = ? "; //'MODEL-1'
	
	
	public List selectTempOrderCreateData(){

		List list = null;
		String userId = ContextUtil.getUsername();
		StringBuffer select = new StringBuffer().append(" SELECT  T.* " +
		                                                " FROM PWUST_TEMP_ORDER_CREATE_DATA T " +
				                                        " WHERE T.UPDT_USERID =  ? " );
				
		ParameterHolder params = new ParameterHolder();
		params.addParameter(userId);
		list = jdbcDaoSupport.queryForList(select.toString(),params);
		
		return list;
	}
    
    // defect 820
	public List selectTempOrderCreateDataDistinct(){

		List list = null;
		String userId = ContextUtil.getUsername();
		StringBuffer select = new StringBuffer().append(" SELECT  DISTINCT T.PLAN_ID,T.PLAN_VERSION,T.PLAN_REVISION,T.PLAN_ALTERATIONS,T.PLAN_UPDT_NO, T.PART_NO, T.PLANNEDORDERFLAG " +
		                                                " FROM PWUST_TEMP_ORDER_CREATE_DATA T " +
				                                        " WHERE T.UPDT_USERID =  ? " );
				
		ParameterHolder params = new ParameterHolder();
		params.addParameter(userId);
		list = jdbcDaoSupport.queryForList(select.toString(),params);
		
		return list;
	}

	
	public Map selectTempOrderCreateDataSaleOrder(){

		List list = null;
		String userId = ContextUtil.getUsername();
		StringBuffer select = new StringBuffer().append(" SELECT  DISTINCT T.SALES_ORDER,T.ENGINE_TYPE,T.ENGINE_MODEL,T.SAPWORKLOC ,T.PLANNEDORDERFLAG" +   //defect 1267
		                                                " FROM PWUST_TEMP_ORDER_CREATE_DATA T " +
				                                        " WHERE T.UPDT_USERID =  ? " );
				
		ParameterHolder params = new ParameterHolder();
		params.addParameter(userId);
		Map map = jdbcDaoSupport.queryForMap(select.toString(),params);
		
		return map;
	}
	
	public List selectTempOrderCreateDataPlans( String planId,
									            Number planVer,
									            Number planRev,
									            Number planAlt,
									            Number planUpdtNo){

		List list = null;
		String userId = ContextUtil.getUsername();
		StringBuffer select = new StringBuffer().append(" SELECT T.* " +
														" FROM PWUST_TEMP_ORDER_CREATE_DATA T " +
														" WHERE T.UPDT_USERID = ?  " +
														" AND PLAN_ID = ?  " +
														" AND PLAN_VERSION = ?  " +
														" AND PLAN_REVISION = ?  " +
														" AND PLAN_ALTERATIONS = ?  " +
														" AND PLAN_UPDT_NO = ? " );
				
		ParameterHolder params = new ParameterHolder();
		params.addParameter(userId);
		params.addParameter(planId);
		params.addParameter(planVer);
		params.addParameter(planRev);
		params.addParameter(planAlt);
		params.addParameter(planUpdtNo);
		
		list = jdbcDaoSupport.queryForList(select.toString(),params);
		
		return list;
	}
	
	
	public Map selectTempOrderData( String planId,
						            Number planVer,
						            Number planRev,
						            Number planAlt,
						            Number planUpdtNo){
		
		Map map = null;
		StringBuffer select = new StringBuffer().append(" SELECT T.* "+
											            " FROM PWUST_TEMP_ORDER_CREATE_DATA T " +
											            " WHERE T.PLAN_ID = ? " + 
											            " AND T.PLAN_VERSION =  ? " +
											            " AND T.PLAN_REVISION = ? " + 
											            " AND T.PLAN_ALTERATIONS = ? " + 
											            " AND T.PLAN_UPDT_NO = ? " );


		ParameterHolder params = new ParameterHolder();
		
		params.addParameter(planId);
		params.addParameter(planVer);
		params.addParameter(planRev);
		params.addParameter(planAlt);
		params.addParameter(planUpdtNo);
		
		List list = jdbcDaoSupport.queryForList(select.toString(),params);
		
		if (list!=null && !list.isEmpty()){
			map = (Map) list.iterator().next();
		}
		
		return map;
		
	}
	
	public boolean tempOrderDataWorkScopeExists(String key,String workScope ){
		
		boolean returnValue = false;
		StringBuffer select = new StringBuffer().append(" SELECT 1 	FROM PWUST_TEMP_ORDER_CREATE_DATA T 	WHERE T.PRIMERY_KEY = ? AND T.WORK_SCOPE LIKE ? " );
		ParameterHolder params = new ParameterHolder();
		params.addParameter(key);
		params.addParameter(workScope);
		
		List list = jdbcDaoSupport.queryForList(select.toString(), params);
		if (list!=null && !list.isEmpty()){
			returnValue=true;
		}
		return returnValue;
	}
	
	public boolean tempOrderDataSuperNetExists(String key,String supNetworkAct ){
		
		boolean returnValue = false;
		StringBuffer select = new StringBuffer().append(" SELECT 1 	FROM PWUST_TEMP_ORDER_CREATE_DATA T 	WHERE T.PRIMERY_KEY = ? AND T.SUPERIORNET LIKE ? " );
		ParameterHolder params = new ParameterHolder();
		params.addParameter(key);
		params.addParameter(supNetworkAct);
		
		List list = jdbcDaoSupport.queryForList(select.toString(), params);
		if (list!=null && !list.isEmpty()){
			returnValue=true;
		}
		return returnValue;
	}
	
	
	public boolean tempOrderDataSubNetExists(String key,String subNetworkAct ){
		
		boolean returnValue = false;
		StringBuffer select = new StringBuffer().append(" SELECT 1 	FROM PWUST_TEMP_ORDER_CREATE_DATA T 	WHERE T.PRIMERY_KEY = ? AND T.SUBNET LIKE ? " );
		ParameterHolder params = new ParameterHolder();
		params.addParameter(key);
		params.addParameter(subNetworkAct);
		
		List list = jdbcDaoSupport.queryForList(select.toString(), params);
		if (list!=null && !list.isEmpty()){
			returnValue=true;
		}
		return returnValue;
	}
	
	public boolean tempOrderDataCustomerExists(String key,String customer ){
		
		boolean returnValue = false;
		StringBuffer select = new StringBuffer().append(" SELECT 1 	FROM PWUST_TEMP_ORDER_CREATE_DATA T 	WHERE T.PRIMERY_KEY = ? AND T.CUSTOMER LIKE ? " );
		ParameterHolder params = new ParameterHolder();
		params.addParameter(key);
		params.addParameter(customer);
		
		List list = jdbcDaoSupport.queryForList(select.toString(), params);
		if (list!=null && !list.isEmpty()){
			returnValue=true;
		}
		return returnValue;
	}
	
	public int updateTempOrderData( String key,
									String workScope,
								    String supNetworkAct,
								    String subNetworkAct,
								    String customer){

		StringBuffer update = new StringBuffer().append(" UPDATE PWUST_TEMP_ORDER_CREATE_DATA T " +
		                                                " SET ");  
		if (StringUtils.isNotBlank(workScope)){
			update.append(" T.WORK_SCOPE = T.WORK_SCOPE || ? , ");
		}
		if (StringUtils.isNotBlank(supNetworkAct)){
			update.append(" T.SUPERIORNET = T.SUPERIORNET || ?, ");
		}
		if (StringUtils.isNotBlank(subNetworkAct)){
			update.append(" T.SUBNET = T.SUBNET || ?, ");
		}
		if (StringUtils.isNotBlank(customer)){
			update.append("  T.CUSTOMER = T.CUSTOMER || ?, ");
		}	
		
		update.append(" T.SALES_ORDER = T.SALES_ORDER  WHERE T.PRIMERY_KEY = ? " );
		
		ParameterHolder params = new ParameterHolder();
		
		
		if (StringUtils.isNotBlank(workScope)){
			params.addParameter(","+workScope);
		}
		if (StringUtils.isNotBlank(supNetworkAct)){
			params.addParameter(","+supNetworkAct);
		}
		if (StringUtils.isNotBlank(subNetworkAct)){
			params.addParameter(","+subNetworkAct);
		}
		if (StringUtils.isNotBlank(customer)){
			params.addParameter(","+customer);
		}	
		
		params.addParameter(key);

		
		int numberRows = jdbcDaoSupport.update(update.toString(), params);
		return numberRows;
		
		
	}
	
	public String getNextImTableKey(){

		StringBuffer insert = new StringBuffer().append("select 'PWUST_' || PWUST_PLANNED_ORDERS_SEQ.NEXTVAL from dual ");
		String nextValue = jdbcDaoSupport.queryForString(insert.toString());
		return nextValue;
		
	}

	
	public void insertTempOrderData(String key,
						            String salesOrder,
								    String engineType,
								    String engineModel,
								    String sapWorkLoc,
								    String workScope,
								    String partNoSel,
								    String supNetworkAct,
								    String subNetworkAct,
								    String customer,
								    String plannedOrderFlag,
								    String planId,
						            Number planVer,
						            Number planRev,
						            Number planAlt,
						            Number planUpdtNo,
						            String PartNo){



		StringBuffer insert = new StringBuffer().append(" INSERT INTO PWUST_TEMP_ORDER_CREATE_DATA (  PRIMERY_KEY, "+
													                                                " SALES_ORDER, "+
													                                                " ENGINE_TYPE, "+
													                                                " ENGINE_MODEL, "+
													                                                " SAPWORKLOC, "+
													                                                " WORK_SCOPE, "+
													                                                " ITEM_NO_SEL, "+
													                                                " SUPERIORNET, "+
													                                                " SUBNET, "+
													                                                " CUSTOMER, "+
													                                                " PLANNEDORDERFLAG, " +
													                                                " PLAN_ID, "+
													                                                " PLAN_VERSION, "+
													                                                " PLAN_REVISION, "+
													                                                " PLAN_ALTERATIONS, "+
													                                                " PLAN_UPDT_NO, "+
													                                                " PART_NO, "+
													                                                " UPDT_USERID ) "+
		                " VALUES( ? , "+
		                        " ? , "+
		                        " ? , "+
		                        " ? , "+
		                        " ? , "+
		                        " ? , "+
		                        " ? , "+ 
		                        " ? , "+
		                        " ? , "+
		                        " ? , "+
		                        " ? , "+
		                        " ? , "+
		                        " ? , "+
		                        " ? , "+ 
		                        " ? , "+
		                        " ? , "+
		                        " ? , "+
		                        " ? ) ");

		ParameterHolder params = new ParameterHolder();
		
		params.addParameter(key);
		params.addParameter(salesOrder);
		params.addParameter(engineType);
		params.addParameter(engineModel);
		params.addParameter(sapWorkLoc);
		params.addParameter(workScope);
		params.addParameter(partNoSel);
		params.addParameter(supNetworkAct);
		params.addParameter(subNetworkAct);
		params.addParameter(customer); 
		params.addParameter(plannedOrderFlag);
		params.addParameter(planId);
		params.addParameter(planVer);
		params.addParameter(planRev);
		params.addParameter(planAlt);
		params.addParameter(planUpdtNo);
		params.addParameter(PartNo);
		params.addParameter(ContextUtil.getUsername());
		
		jdbcDaoSupport.insert(insert.toString(), params);

	}
	
	
	public List selectPwustSalesOrder(String salesOrder, String superNet,String subNet){

		StringBuffer selectSql = new StringBuffer().append(" SELECT T.* " +
		                                                   " FROM PWUST_SALES_ORDER  T " + // PRIMARY KEY (SALES_ORDER, ACT_NO, SUB_ACT_NO)
		                                                   " WHERE T.SALES_ORDER = ? " );
		
		if (StringUtils.isNotBlank(superNet)){
			selectSql.append(" AND T.ACT_NO = ? " ) ;
		}
		if (StringUtils.isNotBlank(subNet)){
			selectSql.append(" AND T.SUB_ACT_NO = ? " ) ;
		}
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(salesOrder);

		if (StringUtils.isNotBlank(superNet)){
			parameters.addParameter(superNet);
		}
		if (StringUtils.isNotBlank(subNet)){
			parameters.addParameter(subNet);
		}
		

		List list = jdbcDaoSupport.queryForList(selectSql.toString(), parameters);
		return list;
	
	}
			
	public List selectPlanTaskWithAllCustId (String planId,
			                                 Number planUptNo,
			                                 String custIdException){
		
		StringBuffer selectSql = new StringBuffer().append( " SELECT DISTINCT  T.SUBJECT_NO " +
                                                            " FROM PWUST_PL_SUBJ_CUSTOMER T, " + 
                                                            "      PWUST_PL_SUBJ_CUST_EXCEPT T1 " +
                                                            " WHERE T.PLAN_ID = T1.PLAN_ID " +
                                                            " AND T.PLAN_UPDT_NO = T1.PLAN_UPDT_NO "+
                                                           // " AND T.SUBJECT_NO = T1.SUBJECT_NO " +
                                                            " AND T.CUST_ID = T1.CUST_ID " +
                                                            " AND T.PLAN_ID = ? "+
                                                            " AND T.PLAN_UPDT_NO = ? "+
                                                            " AND T.CUST_ID = ? "+
                                                            " AND T1.CUST_ID_EXCEPTION NOT IN ( ");
		 
		 if (null!= custIdException && ",".equalsIgnoreCase(custIdException.substring(0, 1))){
				custIdException = custIdException.substring(1, custIdException.length());
		 }

		 String[] custIdExceptionSplit = StringUtils.split(custIdException,",");
		 for (int i=0; i< custIdExceptionSplit.length; i++){
			  if (i==0){
				  selectSql.append(" ?  ");  
			  }else{
				  selectSql.append(" , ?  ");
			  }
		  }	
		  selectSql.append(")");
		 
  		 ParameterHolder parameters = new ParameterHolder();
		 parameters.addParameter(planId);
		 parameters.addParameter(planUptNo);
		 parameters.addParameter("ALL");

		 
		 for (int i=0; i< custIdExceptionSplit.length; i++){
			 parameters.addParameter(custIdExceptionSplit[i]);	 
		 }
		 
		 List list = jdbcDaoSupport.queryForList(selectSql.toString(), parameters);
		 return list;
		
	}

	
	// defect 820
	public List selectOrderTaskToUpdateStatus(String orderId,String workScope, String customerId,String superNet,String subNet){
		
		
		if (null!= workScope && ",".equalsIgnoreCase(workScope.substring(0, 1))){
			  workScope = workScope.substring(1, workScope.length());
		}
		if (null!= customerId && ",".equalsIgnoreCase(customerId.substring(0, 1))){
			customerId = customerId.substring(1, customerId.length());
		}
		if (null!=superNet &&  ",".equalsIgnoreCase(superNet.substring(0, 1))){
			superNet = superNet.substring(1, superNet.length());
		}
		if (null!=subNet &&  ",".equalsIgnoreCase(subNet.substring(0, 1))){
			subNet = subNet.substring(1, subNet.length());
		}

		String[] workScopeSplit = null;
		String[] customerIdSplit = null;
		String[] superNetSplit = null;
		String[] subNetSplit = null;

		if (StringUtils.isNotBlank(workScope)){
			workScopeSplit= StringUtils.split(workScope,",");
		}

		if (StringUtils.isNotBlank(customerId)){
			customerIdSplit = StringUtils.split(customerId,",");
		}		
		if (StringUtils.isNotBlank(superNet)){
			superNetSplit = StringUtils.split(superNet,",");
		}
		if (StringUtils.isNotBlank(subNet)){
			subNetSplit = StringUtils.split(subNet,",");
		}
			
			
		StringBuffer selectSql = new StringBuffer().append(" SELECT TT.PLAN_ID, TT.PLAN_UPDT_NO, T.* " +
	                                                       " FROM SFOR_SFWID_ORDER_SUBJECT T, SFWID_ORDER_DESC TT " +
	                                                       " WHERE T.ORDER_ID = TT.ORDER_ID " +
	                                                       " AND T.ORDER_ID= ? " +
	                                                       " AND T.INCLUDED_FLAG = ? " );
		
/*		//  Backing out for now
		 if  (null!= workScopeSplit && null!= customerIdSplit)//Begin Defect 1007
		{ 
			
			selectSql.append(" AND EXISTS (SELECT 1 "+
							 "               FROM PWUST_PL_SUBJ_WORKSCOPE T1 " +
							 "              WHERE T1.PLAN_ID = TT.PLAN_ID " +
							 "                AND T1.PLAN_UPDT_NO = TT.PLAN_UPDT_NO " +
							 "                AND T1.SUBJECT_NO = T.SUBJECT_NO " +
							 "                AND T1.WORKSCOPE IN(  " );

			for (int i=0; i< workScopeSplit.length; i++){
				if (i==0){
					selectSql.append(" ?  ");  
				}else{
					selectSql.append(" , ?  ");
				}
			}
			selectSql.append(" ) )" );

			selectSql.append(" AND EXISTS ( SELECT 1 " + 
							 "                FROM PWUST_PL_SUBJ_CUSTOMER T2 " +
							 "               WHERE T2.PLAN_ID = TT.PLAN_ID " + 
							 "                 AND T2.PLAN_UPDT_NO = TT.PLAN_UPDT_NO "+
							 "                 AND T2.SUBJECT_NO = T.SUBJECT_NO  " +
							 "                 AND T2.CUST_ID  in ( " );

			for (int i=0; i< customerIdSplit.length; i++){
				if (i==0){
					selectSql.append(" ?  ");  
				}else{
					selectSql.append(" , ?  ");
				}
			}

			// add ALL customer ID
			selectSql.append(" , 'ALL'  )");

			selectSql.append(" AND NOT EXISTS (SELECT 1 " +
					         "                   FROM PWUST_PL_SUBJ_CUST_EXCEPT ET " +
					         "                  WHERE ET.PLAN_ID = T2.PLAN_ID " +
					         "				      AND ET.PLAN_UPDT_NO = T2.PLAN_UPDT_NO " +
					         "					  AND ET.SUBJECT_NO =T2.SUBJECT_NO " +
					         " 					  AND ET.CUST_ID = 'ALL' " +
						" 						  AND ET.CUST_ID_EXCEPTION IN( ");

			for (int i=0; i< customerIdSplit.length; i++){
				if (i==0){
					selectSql.append(" ?  ");  
				}else{
					selectSql.append(" , ?  ");
				}
			}
			selectSql.append( " ) ) ) " );
		} 
		    else 
		{// END Defect 1007*/
	if (null!= workScopeSplit){

			 
			selectSql.append(" AND EXISTS (SELECT 1 "+
	                         "             FROM PWUST_PL_SUBJ_WORKSCOPE T1 " +
	                         "             WHERE T1.PLAN_ID = TT.PLAN_ID " +
				             "             AND T1.PLAN_UPDT_NO = TT.PLAN_UPDT_NO " +
				             "             AND T1.SUBJECT_NO = T.SUBJECT_NO " +
				             "             AND T1.WORKSCOPE IN(  " );
		  
			  for (int i=0; i< workScopeSplit.length; i++){
				  if (i==0){
					  selectSql.append(" ?  ");  
				  }else{
					  selectSql.append(" , ?  ");
				  }
			  }
			  selectSql.append( " ) )" );
		 }	
		
		if (null!= customerIdSplit){
			 
			selectSql.append(" AND EXISTS ( SELECT 1 " + 
					         "              FROM PWUST_PL_SUBJ_CUSTOMER T2 " +
				             "              WHERE T2.PLAN_ID = TT.PLAN_ID " + 
					         "              AND T2.PLAN_UPDT_NO = TT.PLAN_UPDT_NO "+
	                         "              AND T2.SUBJECT_NO = T.SUBJECT_NO  " +
					         "              AND T2.CUST_ID  in ( " );
			 
			 for (int i=0; i< customerIdSplit.length; i++){
				  if (i==0){
					  selectSql.append(" ?  ");  
				  }else{
					  selectSql.append(" , ?  ");
				  }
			  }
			 
			  // add ALL cusotmer ID
			  selectSql.append(" , 'ALL'  )");
			 
			  selectSql.append( " AND NOT EXISTS (SELECT 1 " +
                                "                 FROM PWUST_PL_SUBJ_CUST_EXCEPT ET " +
					                              " WHERE ET.PLAN_ID = T2.PLAN_ID " +
					                              " AND ET.PLAN_UPDT_NO = T2.PLAN_UPDT_NO " +
					                              " AND ET.SUBJECT_NO =T2.SUBJECT_NO " +
					                              " AND ET.CUST_ID = 'ALL' " +
					                              " AND ET.CUST_ID_EXCEPTION IN( ");
			  
			for (int i=0; i< customerIdSplit.length; i++){
					  if (i==0){
						  selectSql.append(" ?  ");  
					  }else{
						  selectSql.append(" , ?  ");
					  }
			}
			 selectSql.append( " ) ) ) " );
		 }

	//} //Defect 1007

		
		 if (null != superNetSplit){
			 
			 selectSql.append(" AND EXISTS( SELECT 1 " +
					          "             FROM  PWUST_PL_SUBJ_NETWORK_ACTIVITY T3 " +
					          "             WHERE T3.PLAN_ID =  TT.PLAN_ID " +
					          "             AND   T3.PLAN_UPDT_NO = TT.PLAN_UPDT_NO "+
					          "             AND   T3.SUBJECT_NO = T.SUBJECT_NO "+
					          "             AND   T3.ENGINE_TYPE = TT.PROGRAM " +
					          "             AND   T3.SUPERIOR_NETWORK_ACT IN  ( " );
					          
				 
			 for (int i=0; i< superNetSplit.length; i++){
				  if (i==0){
					  selectSql.append(" ?  ");  
				  }else{
					  selectSql.append(" , ?  ");
				  }
			  }
			 selectSql.append( " ) )" );
		 }

		 if (null!= superNetSplit && null!= subNetSplit){
			 
			 selectSql.append(" AND EXISTS( SELECT 1 " +
	                          "             FROM  PWUST_PL_SUBJ_NETWORK_ACTIVITY T4, " +
	                          "                   PWUST_PL_SUBJ_SUBNET_ACTIVITY T5 " +
	                          "             WHERE T4.PLAN_ID = T5.PLAN_ID " +
					          "             AND T4.PLAN_UPDT_NO = T5.PLAN_UPDT_NO " +
					          "             AND T4.SUBJECT_NO = T5.SUBJECT_NO " +
					          "             AND T4.ENGINE_TYPE = T5.ENGINE_TYPE " +
					          "             AND T4.SUPERIOR_NETWORK_ACT = T5.SUPERIOR_NETWORK_ACT " +
					          "             AND T5.PLAN_ID = TT.PLAN_ID " +
					          "             AND T5.PLAN_UPDT_NO = TT.PLAN_UPDT_NO " +
					          "             AND T5.SUBJECT_NO = T.SUBJECT_NO " +
					          "             AND T5.ENGINE_TYPE = TT.PROGRAM "+
					          "             AND T4.SUPERIOR_NETWORK_ACT IN(   ");
			 for (int i=0; i< superNetSplit.length; i++){
				  if (i==0){
					  selectSql.append(" ?  ");  
				  }else{
					  selectSql.append(" , ?  ");
				  }
			  }
			 selectSql.append( " ) " );

			 
			 selectSql.append(" AND   T5.SUB_NETWORK_ACT IN ( ");
			 
				 for (int i=0; i< subNetSplit.length; i++){
					  if (i==0){
						  selectSql.append(" ?  ");  
					  }else{
						  selectSql.append(" , ?  ");
					  }
				 }
				 selectSql.append( " ) )" );
		 }
		 
		 
		 ParameterHolder parameters = new ParameterHolder();
		 parameters.addParameter(orderId);
		 parameters.addParameter(EXCLUDED);
		
		 if (null!=workScopeSplit){
			 for (int i=0; i< workScopeSplit.length; i++){
				 parameters.addParameter(workScopeSplit[i]);
			 }
		 }
	
	    if (null!= customerIdSplit){
	       // for PWUST_PL_SUBJ_CUSTOMER table
		   for (int i=0; i< customerIdSplit.length; i++){
			 parameters.addParameter(customerIdSplit[i]);
		   }
		   // FOR PWUST_PL_SUBJ_CUST_EXCEPT table 
		   for (int i=0; i< customerIdSplit.length; i++){
			 parameters.addParameter(customerIdSplit[i]);
		   }

	    }
		
		if (null!=superNetSplit){
		   for (int i=0; i< superNetSplit.length; i++){
				 parameters.addParameter(superNetSplit[i]);
		   } 
		}
			
		if (null!=superNetSplit && null!=subNetSplit){
				
		   for (int i=0; i< superNetSplit.length; i++){
				 parameters.addParameter(superNetSplit[i]);
		   } 
		   for (int i=0; i< subNetSplit.length; i++){
				 parameters.addParameter(subNetSplit[i]);
		   } 
		}
		
		List list = jdbcDaoSupport.queryForList(selectSql.toString(), parameters);
		return list;
	}
	
	

	                  
	
	//Defect 409
	public int updateSfwidOrderOperSubjectTaskIncludeFlagNotUsed(String orderId,String workScope, String customerId,String superNet,String subNet){
	
		
		//,CAN,CON,CPL,HOT
		
		int numberOfRowsUpdate = 0;

		if (null!= workScope && ",".equalsIgnoreCase(workScope.substring(0, 1))){
			  workScope = workScope.substring(1, workScope.length());
		}
		if (null!= customerId && ",".equalsIgnoreCase(customerId.substring(0, 1))){
			customerId = customerId.substring(1, customerId.length());
		}
		if (null!=superNet &&  ",".equalsIgnoreCase(superNet.substring(0, 1))){
			superNet = superNet.substring(1, superNet.length());
		}
		if (null!=subNet &&  ",".equalsIgnoreCase(subNet.substring(0, 1))){
			subNet = subNet.substring(1, subNet.length());
		}

		String[] workScopeSplit = null;
		String[] customerIdSplit = null;
		String[] superNetSplit = null;
		String[] subNetSplit = null;

		if (StringUtils.isNotBlank(workScope)){
			workScopeSplit= StringUtils.split(workScope,",");
		}

		if (StringUtils.isNotBlank(customerId)){
			customerIdSplit = StringUtils.split(customerId,",");
		}		
		if (StringUtils.isNotBlank(superNet)){
			superNetSplit = StringUtils.split(superNet,",");
		}
		if (StringUtils.isNotBlank(subNet)){
			subNetSplit = StringUtils.split(subNet,",");
		}
		
		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFOR_SFWID_OPER_SUBJECT T " )
								                   .append(" SET T.INCLUDED_FLAG =  ? ")   //'INCLUDED'
									               .append(" WHERE T.ORDER_ID = ? ")       //'8AC2233532EF0B81AE9A37DFC9D70EE9'
									               .append(" AND T.INCLUDED_FLAG = ? ");   //'EXCLUDED'		


		 if (null!= workScopeSplit){
			 
			  updateSql.append(" AND EXISTS (SELECT 1 " + 
				                          " FROM PWUST_PL_SUBJ_WORKSCOPE T1, " +  // PRIMARY KEY (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, WORKSCOPE)
				                          " SFWID_OPER_V T2 "+ 
				                          " WHERE T2.ORDER_ID = T.ORDER_ID " +
				                          " AND   T2.OPER_KEY = T.OPER_KEY " +
				                          " AND   T1.SUBJECT_NO = T.SUBJECT_NO " +
				                          " AND T2.PLAN_ID = T1.PLAN_ID " +
				                          " AND T2.PLAN_UPDT_NO = T1.PLAN_UPDT_NO " +
				                          " AND T1.WORKSCOPE in (   " );
		  
			  for (int i=0; i< workScopeSplit.length; i++){
				  if (i==0){
					  updateSql.append(" ?  ");  
				  }else{
					  updateSql.append(" , ?  ");
				  }
			  }
			  updateSql.append( " ) )" );
		 }
		 
		 if (null!= customerIdSplit){
				 
			 updateSql.append(" AND EXISTS ( SELECT 1 " + 
					                         " FROM PWUST_PL_SUBJ_CUSTOMER T9, " + //PRIMARY KEY (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, CUST_ID)
					                         " SFWID_OPER_V T10 " +
					                         " WHERE T10.ORDER_ID = T.ORDER_ID " + 
					                         " AND T10.OPER_KEY = T.OPER_KEY " +
					                         " AND T9.PLAN_ID = T9.PLAN_ID " + 
					                         " AND T9.PLAN_UPDT_NO = T9.PLAN_UPDT_NO " +
					                         " AND T9.SUBJECT_NO = T.SUBJECT_NO " + 
					                         " AND T9.CUST_ID  in ( " );
			 
			 for (int i=0; i< customerIdSplit.length; i++){
				  if (i==0){
					  updateSql.append(" ?  ");  
				  }else{
					  updateSql.append(" , ?  ");
				  }
			  }
			  updateSql.append( " ) )" );
		 }
		 
		 if (null != superNetSplit){
				 
			 updateSql.append(" AND EXISTS( SELECT 1 " +
					                         " FROM  PWUST_PL_SUBJ_NETWORK_ACTIVITY T5, " +  //PRIMARY KEY (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, ENGINE_TYPE, SUPERIOR_NETWORK_ACT)
					                         " SFWID_OPER_V T6 " +
					                         " WHERE T6.ORDER_ID = T.ORDER_ID " +
					                         " AND T6.OPER_KEY = T.OPER_KEY  " +
					                         " AND   T5.PLAN_ID =  T6.PLAN_ID " +
					                         " AND   T5.PLAN_UPDT_NO = T6.PLAN_UPDT_NO " +
					                         " AND   T5.SUBJECT_NO = T.SUBJECT_NO " +
					                         " AND   T5.ENGINE_TYPE = T6.PROGRAM " +
					                         " AND   T5.SUPERIOR_NETWORK_ACT in (  " );
				 
			 for (int i=0; i< superNetSplit.length; i++){
				  if (i==0){
					  updateSql.append(" ?  ");  
				  }else{
					  updateSql.append(" , ?  ");
				  }
			  }
			  updateSql.append( " ) )" );
		 }
		 
		
		 if (null!= superNetSplit && null!= subNetSplit){
				 
				 updateSql.append(" AND EXISTS( SELECT 1 " +
					                         " FROM  PWUST_PL_SUBJ_SUBNET_ACTIVITY T7,  " +  //PRIMARY KEY (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, ENGINE_TYPE, SUPERIOR_NETWORK_ACT, SUB_NETWORK_ACT)
					                         " SFWID_OPER_V T8 " + 
								             " WHERE T8.ORDER_ID = T.ORDER_ID "+
								             " AND  T8.OPER_KEY = T.OPER_KEY "+
											 " AND   T7.PLAN_ID =  T8.PLAN_ID "+
											 " AND   T7.PLAN_UPDT_NO = T8.PLAN_UPDT_NO "+
											 " AND   T7.SUBJECT_NO = T.SUBJECT_NO "+
											 " AND   T7.ENGINE_TYPE = T8.PROGRAM "+
											 " AND   T7.SUPERIOR_NETWORK_ACT in ( ");
				 
				 for (int i=0; i< superNetSplit.length; i++){
					  if (i==0){
						  updateSql.append(" ?  ");  
					  }else{
						  updateSql.append(" , ?  ");
					  }
				  }
				  updateSql.append( " ) " );
				 
				  updateSql.append(" AND   T7.SUB_NETWORK_ACT in ( ");
					 for (int i=0; i< subNetSplit.length; i++){
						  if (i==0){
							  updateSql.append(" ?  ");  
						  }else{
							  updateSql.append(" , ?  ");
						  }
					 }
					 updateSql.append( " ) )" );
		}
		 
		 
		 ParameterHolder parameters = new ParameterHolder();
		 parameters.addParameter(INCLUDED);
		 parameters.addParameter(orderId);
		 parameters.addParameter(EXCLUDED);
		
		 if (null!=workScopeSplit){
			 for (int i=0; i< workScopeSplit.length; i++){
				 parameters.addParameter(workScopeSplit[i]);
			 }
		 }
	
	    if (null!= customerIdSplit){
		   for (int i=0; i< customerIdSplit.length; i++){
			 parameters.addParameter(customerIdSplit[i]);
		   }
	    }
		
		if (null!=superNetSplit){
		   for (int i=0; i< superNetSplit.length; i++){
				 parameters.addParameter(superNetSplit[i]);
		   } 
		}
			
		if (null!=superNetSplit && null!=subNetSplit){
				
		   for (int i=0; i< superNetSplit.length; i++){
				 parameters.addParameter(superNetSplit[i]);
		   } 
		   for (int i=0; i< subNetSplit.length; i++){
				 parameters.addParameter(subNetSplit[i]);
		   } 
		}
		
		numberOfRowsUpdate = jdbcDaoSupport.update(updateSql.toString(), parameters);
		
	    return numberOfRowsUpdate;	 
	}
	
	//Defect 409
	public int updateSfwidOrderSubjectTaskIncludeFlagNotUsed(String orderId,String workScope, String customerId,String superNet,String subNet){

		int numberOfRowsUpdate = 0;
		if (null!= workScope && ",".equalsIgnoreCase(workScope.substring(0, 1))){
			  workScope = workScope.substring(1, workScope.length());
		}
		if (null!= customerId && ",".equalsIgnoreCase(customerId.substring(0, 1))){
			customerId = customerId.substring(1, customerId.length());
		}
		if (null!=superNet &&  ",".equalsIgnoreCase(superNet.substring(0, 1))){
			superNet = superNet.substring(1, superNet.length());
		}
		if (null!=subNet &&  ",".equalsIgnoreCase(subNet.substring(0, 1))){
			subNet = subNet.substring(1, subNet.length());
		}

		String[] workScopeSplit = null;
		String[] customerIdSplit = null;
		String[] superNetSplit = null;
		String[] subNetSplit = null;
		
		if (StringUtils.isNotBlank(workScope)){
			workScopeSplit= StringUtils.split(workScope,",");
		}
		if (StringUtils.isNotBlank(customerId)){
			customerIdSplit = StringUtils.split(customerId,",");
		}		
		if (StringUtils.isNotBlank(superNet)){
			superNetSplit = StringUtils.split(superNet,",");
		}
		if (StringUtils.isNotBlank(subNet)){
			subNetSplit = StringUtils.split(subNet,",");
		}
		
		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFOR_SFWID_ORDER_SUBJECT T " )
		   						                   .append(" SET T.INCLUDED_FLAG =  ? ")   //'INCLUDED'
									               .append(" WHERE T.ORDER_ID = ? ")       //'8AC2233532EF0B81AE9A37DFC9D70EE9'
									               .append(" AND T.INCLUDED_FLAG = ? ");   //'EXCLUDED'		
			
	   if (null != workScopeSplit){
			   
		   updateSql.append(" AND EXISTS ( SELECT 1 ") 
					.append(             " FROM PWUST_PL_SUBJ_WORKSCOPE T1, ")   //  PRIMARY KEY (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, WORKSCOPE)
					.append(             " SFWID_ORDER_DESC T2 " )
					.append(             " WHERE T2.ORDER_ID = T.ORDER_ID " )
					.append(             " AND T1.PLAN_ID = T2.PLAN_ID " )
					.append(             " AND T1.PLAN_UPDT_NO = T2.PLAN_UPDT_NO ")
					.append(             " AND T1.SUBJECT_NO = T.SUBJECT_NO ")
					.append(             " AND T1.WORKSCOPE in (  " );
		   
		   for (int i=0; i< workScopeSplit.length; i++){
				  if (i==0){
					  updateSql.append(" ?  ");  
				  }else{
					  updateSql.append(" , ?  ");
				  }
		   }
		   updateSql.append( " ) )" );
		}
	   
		   
	   if (null!= customerIdSplit){
			   updateSql.append(" AND EXISTS ( SELECT 1 ") 
	            .append(             " FROM PWUST_PL_SUBJ_CUSTOMER T3, ") //PRIMARY KEY (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, CUST_ID)
	            .append(             " SFWID_ORDER_DESC T4 ") 
	            .append(             " WHERE T4.ORDER_ID = T.ORDER_ID ")
	            .append(             " AND T3.PLAN_ID = T4.PLAN_ID ")
				.append(             " AND T3.PLAN_UPDT_NO = T4.PLAN_UPDT_NO ")
				.append(             " AND T3.SUBJECT_NO = T.SUBJECT_NO ") 
				.append(             " AND T3.CUST_ID in ( " );
			   
			   for (int i=0; i< customerIdSplit.length; i++){
					  if (i==0){
						  updateSql.append(" ?  ");  
					  }else{
						  updateSql.append(" , ?  ");
					  }
			   }
			   updateSql.append( " ) )" );
			   
		}
	  
					                           
	   
	   if (null!= superNetSplit){
			   updateSql.append("  AND EXISTS( SELECT 1 ")
	            .append(             " FROM  PWUST_PL_SUBJ_NETWORK_ACTIVITY T5, ") // PRIMARY KEY (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, ENGINE_TYPE, SUPERIOR_NETWORK_ACT)
	            .append(             " SFWID_ORDER_DESC T6 ")   
	            .append(             " WHERE T6.ORDER_ID = T.ORDER_ID ")
	            .append(             " AND   T5.PLAN_ID =  T6.PLAN_ID ")
	            .append(             " AND   T5.PLAN_UPDT_NO = T6.PLAN_UPDT_NO ")
	            .append(             " AND   T5.SUBJECT_NO = T.SUBJECT_NO ")
	            .append(             " AND   T5.ENGINE_TYPE = T6.PROGRAM ")
	            .append(             " AND   T5.SUPERIOR_NETWORK_ACT in ( ");
			   
			   for (int i=0; i< superNetSplit.length; i++){
					  if (i==0){
						  updateSql.append(" ?  ");  
					  }else{
						  updateSql.append(" , ?  ");
					  }
			   }
			   updateSql.append( " ) )" );
	   }

	   
	   if (null!=superNetSplit  && null!=subNetSplit){

			   updateSql.append("  AND EXISTS( SELECT 1 ")
	            .append(             " FROM  PWUST_PL_SUBJ_SUBNET_ACTIVITY T7, ")//  PRIMARY KEY (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, ENGINE_TYPE, SUPERIOR_NETWORK_ACT, SUB_NETWORK_ACT)
	            .append(                   " SFWID_ORDER_DESC T8 ") 
	            .append(             " WHERE T8.ORDER_ID = T.ORDER_ID ")
	            .append(             " AND   T7.PLAN_ID =  T8.PLAN_ID ")
	            .append(             " AND   T7.PLAN_UPDT_NO = T8.PLAN_UPDT_NO ")
	            .append(             " AND   T7.SUBJECT_NO = T.SUBJECT_NO ")
	            .append(             " AND   T7.ENGINE_TYPE = T8.PROGRAM ")
	            .append(             " AND   T7.SUPERIOR_NETWORK_ACT in ( "); 
	            
			   for (int i=0; i< superNetSplit.length; i++){
					  if (i==0){
						  updateSql.append(" ?  ");  
					  }else{
						  updateSql.append(" , ?  ");
					  }
			   }
			   updateSql.append( " ) " );
	            
			   updateSql.append(             " AND   T7.SUB_NETWORK_ACT in ( ");
			   for (int i=0; i< subNetSplit.length; i++){
					  if (i==0){
						  updateSql.append(" ?  ");  
					  }else{
						  updateSql.append(" , ?  ");
					  }
			   }
			   updateSql.append( " ) )" );
	   }
	   
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(INCLUDED);
		parameters.addParameter(orderId);
		parameters.addParameter(EXCLUDED);
		
		if (null!= workScopeSplit){
			 for (int i=0; i< workScopeSplit.length; i++){
				 parameters.addParameter(workScopeSplit[i]);
			 }
		}
			
		
		if (null!= customerIdSplit){
			 for (int i=0; i< customerIdSplit.length; i++){
				 parameters.addParameter(customerIdSplit[i]);
			 }
		}
		
		if (null!= superNetSplit){
			 for (int i=0; i< superNetSplit.length; i++){
				 parameters.addParameter(superNetSplit[i]);
			 }
		}
		
			
		if (null!=superNetSplit && null!=subNetSplit ){
			 for (int i=0; i< superNetSplit.length; i++){
				 parameters.addParameter(superNetSplit[i]);
			 }
			 for (int i=0; i< subNetSplit.length; i++){
				 parameters.addParameter(subNetSplit[i]);
			 }				 
		}
		
		numberOfRowsUpdate = jdbcDaoSupport.update(updateSql.toString(), parameters);
					
		
		return numberOfRowsUpdate;
	}
	
	
	
	public void insertCopyOrderSubjectsNotUsed(String orderId,
							       		String planId,
							       		Number planUpdateNumber,
							       		String itemNo,
							       		String instructionType,
							       		String itemRev,
							       		String startingItemNo,
							       		String startingItemRev,
							       		String superNet,
							       		String subNet,
							       		String workScope,
							       		String customer){
		
	
			StringBuffer insertSql = new StringBuffer().append("INSERT INTO SFOR_SFWID_ORDER_SUBJECT ( ")
								                       .append("    ORDER_ID, ")
								                       .append("    SUBJECT_NO, ")
								                       .append("    SUBJECT_REV, ")
								                       .append("    SUBJECT_STATUS, ")
								                       .append("    TITLE, ")
								                       .append("    AUTHORITY, ")
								                       .append("    INCLUDED_FLAG, ")
								                       .append("    STANDARD_FLAG, ")
								                       .append("    UPDT_USERID, ")
								                       .append("    TIME_STAMP, ")
								                       .append("    LAST_ACTION, ")
								                       .append("    OBSOLETE_RECORD_FLAG ) ")
								                       .append("SELECT  ?, ")
								                       .append("    C.SUBJECT_NO, ")
								                       .append("    C.SUBJECT_REV, ")
								                       .append("    C.SUBJECT_STATUS, ")
								                       .append("    C.TITLE, ")
								                       .append("    C.AUTHORITY, ")
								                       .append("    (CASE WHEN C.IS_DEFAULT = 'Y' THEN ? ELSE ? END), ")
								                       .append("    ?, ")
								                       .append("    ?, ")
								                       .append( jdbcDaoSupport.getTimestampFunction() + ", ")
								                       .append("    ?, ")
								                       .append("    C.OBSOLETE_RECORD_FLAG ")
								                       .append("FROM SFOR_SFPL_PLAN_SUBJECT C ")
								                       .append("WHERE C.PLAN_ID = ? AND ")
								                       .append("      C.PLAN_UPDT_NO = ? ");

			// FND-12710
			if(StringUtils.equalsIgnoreCase(instructionType, MRO_PART)|| StringUtils.equalsIgnoreCase(instructionType, MRO_UPGRADE)){
			
				insertSql.append("     AND C.SUBJECT_NO IN (SELECT E.SUBJECT_NO")
						 .append("                          FROM SFOR_SFPL_PLAN_SUBJECT_PART D,")
						 .append("                               SFOR_SFPL_PLAN_SUBJECT      E,")
						 .append("                               SFPL_ITEM_DESC_MASTER_ALL   F")
						 .append("                         WHERE D.PLAN_ID = E.PLAN_ID")
						 .append("                           AND D.PLAN_UPDT_NO = E.PLAN_UPDT_NO")
						 .append("                           AND D.SUBJECT_NO = E.SUBJECT_NO")
						 .append("                           AND D.PLAN_ID = ? ")
						 .append("                           AND D.PLAN_UPDT_NO = ?")
						 .append("                           AND D.ITEM_ID = F.ITEM_ID")
						 .append("                           AND F.PART_NO = ?")
						 .append("                           AND F.PART_CHG = ? )");
				
				/*
				String superNet,
	       		String subNet,
	       		String workScope,
	       		String customer
				*/
				
				if (StringUtils.isNotBlank(superNet)){
					insertSql.append("  AND EXISTS ( SELECT 1 "+
                                                   " FROM SFPL_PLAN_DESC C1, " +                 // primary key (PLAN_ID, PLAN_UPDT_NO)
                                                        " PWUST_PL_SUBJ_NETWORK_ACTIVITY C3 "+   // PRIMARY KEY (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, ENGINE_TYPE, SUPERIOR_NETWORK_ACT)
                                                   " WHERE C1.PLAN_ID = C3.PLAN_ID " +
                                                   " AND   C1.PLAN_UPDT_NO = C3.PLAN_UPDT_NO " +
                                                   " AND C3.PLAN_ID = C.PLAN_ID " + 
                                                   " AND   C3.PLAN_UPDT_NO = C.PLAN_UPDT_NO " + 
                                                   " AND   C3.SUBJECT_NO = C.SUBJECT_NO " +
                                                   " AND   C3.ENGINE_TYPE = C1.PROGRAM " +
                                                   " AND   C3.SUPERIOR_NETWORK_ACT = ? ) ");	
				}
				
				if (StringUtils.isNotBlank(subNet)){
					insertSql.append(" AND EXISTS ( SELECT 1 "+ 
				                                  " FROM SFPL_PLAN_DESC BB1, "+                 // primary key (PLAN_ID, PLAN_UPDT_NO)
				                                       " PWUST_PL_SUBJ_NETWORK_ACTIVITY B2, "+  // PRIMARY KEY (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, ENGINE_TYPE, SUPERIOR_NETWORK_ACT)
				                                       " PWUST_PL_SUBJ_SUBNET_ACTIVITY B3 "+    // PRIMARY KEY (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, ENGINE_TYPE, SUPERIOR_NETWORK_ACT, SUB_NETWORK_ACT)
				                                  
				                                  " WHERE BB1.PLAN_ID = B2.PLAN_ID " +
				                                  " AND   BB1.PLAN_UPDT_NO = B2.PLAN_UPDT_NO " + 
				               
				                                  " AND B2.PLAN_ID = B3.PLAN_ID " + 
				                                  " AND B2.PLAN_UPDT_NO = B3.PLAN_UPDT_NO " + 
									              " AND B2.SUBJECT_NO = B3.SUBJECT_NO " + 
									              " AND B2.ENGINE_TYPE = B3.ENGINE_TYPE " + 
									              " AND B2.SUPERIOR_NETWORK_ACT = B3.SUPERIOR_NETWORK_ACT " + 
									               
									              " AND   B3.PLAN_ID = C.PLAN_ID " + 
									              " AND   B3.PLAN_UPDT_NO = C.PLAN_UPDT_NO " +  
									              " AND   B3.SUBJECT_NO = C.SUBJECT_NO " +
									              " AND   B3.ENGINE_TYPE = BB1.PROGRAM  " + 
									              " AND   B3.SUPERIOR_NETWORK_ACT = ? " +
									              " AND   B3.SUB_NETWORK_ACT = ? )" ); 
				}
				
				if (StringUtils.isNotBlank(workScope)){
					insertSql.append(" AND C.SUBJECT_NO IN  ( SELECT C1.SUBJECT_NO " +
							                                " FROM PWUST_PL_SUBJ_WORKSCOPE C1 " +   //  primary key (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, WORKSCOPE)
							                                " WHERE C1.PLAN_ID = C.PLAN_ID " + 
							                                " AND C1.PLAN_UPDT_NO = C.PLAN_UPDT_NO " + 
							                                " AND  C1.SUBJECT_NO = C.SUBJECT_NO " + 
							                                " AND C1.WORKSCOPE = ? " + 
							                                " AND C1.OBSOLETE_RECORD_FLAG = ? ) ");				
				}			
				if (StringUtils.isNotBlank(customer)){
					
					insertSql.append(" AND EXISTS ( SELECT 1 " +
							                      " FROM PWUST_PL_SUBJ_CUSTOMER C2 " + // PRIMARY KEY (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, CUST_ID)
			                                      " WHERE C2.PLAN_ID = C.PLAN_ID " +
			                                      " AND C2.PLAN_UPDT_NO = C.PLAN_UPDT_NO " +
			                                      " AND C2.SUBJECT_NO = C.SUBJECT_NO "+
			                                      " AND C2.CUST_ID = ? ) ");					
				}				
			}
			

			
			ParameterHolder parameters = new ParameterHolder();
			parameters.addParameter(orderId);
			parameters.addParameter(INCLUDED);
			parameters.addParameter(EXCLUDED);
			parameters.addParameter(YES);
			parameters.addParameter(ContextUtil.getUsername());
			parameters.addParameter(INSERTED);
			parameters.addParameter(planId);
			parameters.addParameter(planUpdateNumber);

			if(StringUtils.equalsIgnoreCase(instructionType, MRO_PART)){
				parameters.addParameter(planId);
				parameters.addParameter(planUpdateNumber);
				parameters.addParameter(itemNo);
				parameters.addParameter(itemRev);
			}

			if(StringUtils.equalsIgnoreCase(instructionType, MRO_UPGRADE)){
				parameters.addParameter(planId);
				parameters.addParameter(planUpdateNumber);
				parameters.addParameter(startingItemNo);
				parameters.addParameter(startingItemRev);
			}

			if (StringUtils.isNotBlank(superNet)){
				parameters.addParameter(superNet);
			}
			
			if (StringUtils.isNotBlank(subNet)){
				parameters.addParameter(superNet);
				parameters.addParameter(subNet);
			}
			
			if (StringUtils.isNotBlank(workScope)){
				parameters.addParameter(workScope);
				parameters.addParameter("N");
			}			

			if (StringUtils.isNotBlank(customer)){
				parameters.addParameter(customer);
			}
			
			jdbcDaoSupport.insert(insertSql.toString(), parameters);
	}
	
    public void insertCopyOperationSubjectsNotUsed(String orderId,
								            String planId,
								            Number planVersion,
											Number planRevision,
											Number planAlterations, 
											Number planUpdateNumber, 
											String itemNo, 
											String instructionType,
											String itemRev,
											String startingItemNo,
											String startingItemRev,
											String superNet,
								       		String subNet,
								       		String workScope,
								       		String customer){
    	
    	StringBuffer insertSql = new StringBuffer().append("INSERT INTO SFOR_SFWID_OPER_SUBJECT ( ")
													.append("    ORDER_ID, ")
													.append("    SUBJECT_NO, ")
													.append("    SUBJECT_REV, ")
													.append("    OPER_KEY, ")
													.append("    INCLUDED_FLAG, ")
													.append("    UPDT_USERID, ")
													.append("    TIME_STAMP, ")
													.append("    LAST_ACTION ) ")
													.append("SELECT ")
													.append("    ?, ")
													.append("    A.SUBJECT_NO, ")
													.append("    A.SUBJECT_REV, ")
													.append("    A.OPER_KEY, ")
													.append("    CASE WHEN IS_DEFAULT = 'Y' THEN ? ELSE ? END, ")
													.append("    ?, ")
													.append(jdbcDaoSupport.getTimestampFunction() + ", ")
													.append("    ? ")
													.append("FROM ")
													.append("    SFOR_SFPL_PLAN_SUBJECT_OPER A, ")
													.append("    SFOR_SFPL_PLAN_SUBJECT B, ")
													.append("    SFPL_OPERATION_REV O, ")
													.append("    SFPL_PLAN_REV P ")
													.append("WHERE ")
													.append("    A.PLAN_ID = ? AND ")
													.append("    P.PLAN_VERSION = ? AND ")
													.append("    P.PLAN_REVISION = ? AND ")
													.append("    P.PLAN_ALTERATIONS = ? AND ")
													.append("    A.OPER_UPDT_NO = O.OPER_UPDT_NO AND ")
													.append("    A.PLAN_ID = B.PLAN_ID AND ")
													.append("    B.PLAN_ID = O.PLAN_ID AND ")
													.append("    O.PLAN_ID = P.PLAN_ID AND ")
													.append("    P.PLAN_ID = A.PLAN_ID AND ")
													.append("    P.PLAN_VERSION = O.PLAN_VERSION AND ")
													.append("    P.PLAN_REVISION = O.PLAN_REVISION AND ")
													.append("    P.PLAN_ALTERATIONS = O.PLAN_ALTERATIONS AND ")
													.append("    A.OPER_KEY = O.OPER_KEY AND ")
													.append("    A.OPER_UPDT_NO = O.OPER_UPDT_NO AND ")
													.append("    A.PLAN_UPDT_NO = B.PLAN_UPDT_NO AND ")
													.append("    B.PLAN_UPDT_NO = P.PLAN_UPDT_NO AND ")
													.append("    P.PLAN_UPDT_NO = A.PLAN_UPDT_NO AND ")
													.append("    B.SUBJECT_NO = A.SUBJECT_NO AND ")
													.append("    B.SUBJECT_REV = A.SUBJECT_REV ");

    	// FND-12710
    	if(StringUtils.equalsIgnoreCase(instructionType, MRO_PART)|| StringUtils.equalsIgnoreCase(instructionType, MRO_UPGRADE)){
    		insertSql.append("     AND B.SUBJECT_NO IN (SELECT E.SUBJECT_NO")
					.append("                            FROM SFOR_SFPL_PLAN_SUBJECT_PART D,")
					.append("                                 SFOR_SFPL_PLAN_SUBJECT      E,")
					.append("                                 SFPL_ITEM_DESC_MASTER_ALL   F")
					.append("                           WHERE D.PLAN_ID = E.PLAN_ID")
					.append("                             AND D.PLAN_UPDT_NO = E.PLAN_UPDT_NO")
					.append("                             AND D.SUBJECT_NO = E.SUBJECT_NO")
					.append("                             AND D.PLAN_ID = ?")
					.append("                             AND D.PLAN_UPDT_NO = ?")
					.append("                             AND D.ITEM_ID = F.ITEM_ID")
					.append("                             AND F.PART_NO = ?")
					.append("                             AND F.PART_CHG = ?)");

    		/*
    		String superNet,
       		String subNet,
       		String workScope,
       		String customer
    		*/
    		
			if (StringUtils.isNotBlank(superNet)){
				insertSql.append("  AND EXISTS ( SELECT 1 "+
                                               " FROM SFPL_PLAN_DESC C1, " +                 // primary key (PLAN_ID, PLAN_UPDT_NO)
                                                    " PWUST_PL_SUBJ_NETWORK_ACTIVITY C3 "+   // PRIMARY KEY (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, ENGINE_TYPE, SUPERIOR_NETWORK_ACT)
                                               " WHERE C1.PLAN_ID = C3.PLAN_ID " +
                                               " AND   C1.PLAN_UPDT_NO = C3.PLAN_UPDT_NO " +
                                               " AND C3.PLAN_ID = B.PLAN_ID " + 
                                               " AND C3.PLAN_UPDT_NO = B.PLAN_UPDT_NO " + 
                                               " AND C3.SUBJECT_NO = B.SUBJECT_NO " +
                                               " AND C3.ENGINE_TYPE = C1.PROGRAM " +
                                               " AND C3.SUPERIOR_NETWORK_ACT = ? ) ");	
			}
			
			if (StringUtils.isNotBlank(subNet)){
				insertSql.append(" AND EXISTS ( SELECT 1 "+ 
			                                  " FROM SFPL_PLAN_DESC BB1, "+                  // primary key (PLAN_ID, PLAN_UPDT_NO)
			                                       " PWUST_PL_SUBJ_NETWORK_ACTIVITY B2, "+  // PRIMARY KEY (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, ENGINE_TYPE, SUPERIOR_NETWORK_ACT)
			                                       " PWUST_PL_SUBJ_SUBNET_ACTIVITY B3 "+    // PRIMARY KEY (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, ENGINE_TYPE, SUPERIOR_NETWORK_ACT, SUB_NETWORK_ACT)
			                                  
			                                  " WHERE BB1.PLAN_ID = B2.PLAN_ID " +
			                                  " AND   BB1.PLAN_UPDT_NO = B2.PLAN_UPDT_NO " + 
			               
			                                  " AND B2.PLAN_ID = B3.PLAN_ID " + 
			                                  " AND B2.PLAN_UPDT_NO = B3.PLAN_UPDT_NO " + 
								              " AND B2.SUBJECT_NO = B3.SUBJECT_NO " + 
								              " AND B2.ENGINE_TYPE = B3.ENGINE_TYPE " + 
								              " AND B2.SUPERIOR_NETWORK_ACT = B3.SUPERIOR_NETWORK_ACT " + 
								               
								              " AND   B3.PLAN_ID = B.PLAN_ID " + 
								              " AND   B3.PLAN_UPDT_NO = B.PLAN_UPDT_NO " +  
								              " AND   B3.SUBJECT_NO = B.SUBJECT_NO " +
								              " AND   B3.ENGINE_TYPE = BB1.PROGRAM  " + 
								              " AND   B3.SUPERIOR_NETWORK_ACT = ? " +
								              " AND   B3.SUB_NETWORK_ACT = ? )" ); 
			}
    		
    		
    		if (StringUtils.isNotBlank(workScope)){
    			insertSql.append(" AND B.SUBJECT_NO IN  ( SELECT C1.SUBJECT_NO  " + 
    					                                " FROM PWUST_PL_SUBJ_WORKSCOPE C1 "+ 
    				                                	" WHERE C1.PLAN_ID = B.PLAN_ID "+
    					                                " AND C1.PLAN_UPDT_NO = B.PLAN_UPDT_NO "+ 
    					                                " AND C1.SUBJECT_NO = B.SUBJECT_NO " + 
    				                                	" AND C1.WORKSCOPE = ? "+ 
    					                                " AND C1.OBSOLETE_RECORD_FLAG = ? ) ");
    		}			
			if (StringUtils.isNotBlank(customer)){
				
				insertSql.append(" AND EXISTS ( SELECT 1 " +
						                      " FROM PWUST_PL_SUBJ_CUSTOMER C2 " + // PRIMARY KEY (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, CUST_ID)
		                                      " WHERE C2.PLAN_ID = B.PLAN_ID " +
		                                      " AND C2.PLAN_UPDT_NO = B.PLAN_UPDT_NO " +
		                                      " AND C2.SUBJECT_NO = B.SUBJECT_NO "+
		                                      " AND C2.CUST_ID = ? ) ");					
			} 	
    	
    	}


    	
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(INCLUDED);
		parameters.addParameter(EXCLUDED);
		parameters.addParameter(ContextUtil.getUsername());
		parameters.addParameter(INSERTED);
		parameters.addParameter(planId);
		parameters.addParameter(planVersion);
		parameters.addParameter(planRevision);
		parameters.addParameter(planAlterations);

		if(StringUtils.equalsIgnoreCase(instructionType, MRO_PART)){
			parameters.addParameter(planId);
			parameters.addParameter(planUpdateNumber);
			parameters.addParameter(itemNo);
			parameters.addParameter(itemRev);
		}

		if(StringUtils.equalsIgnoreCase(instructionType, MRO_UPGRADE)){
			parameters.addParameter(planId);	
			parameters.addParameter(planUpdateNumber);
			parameters.addParameter(startingItemNo);
			parameters.addParameter(startingItemRev);
		}

		if (StringUtils.isNotBlank(superNet)){
			parameters.addParameter(superNet);
		}
		
		if (StringUtils.isNotBlank(subNet)){
			parameters.addParameter(superNet);
			parameters.addParameter(subNet);
		}
		if (StringUtils.isNotBlank(workScope)){
			parameters.addParameter(workScope);	
			parameters.addParameter("N");
		}			

		if (StringUtils.isNotBlank(customer)){
			parameters.addParameter(customer);
		}
		
		jdbcDaoSupport.insert(insertSql.toString(), parameters);
    }	
	
	
	public List selectPlansForWOCreate( String engineType,
									    String sapWorkLoc,
									    String engineModel,
									    String workScope,
									    String itemNo,
									    String startDate,
									    String endDate,
									    String superNet,
									    String subNet,
									    String customer){

		StringBuffer select = new StringBuffer(selectPlanForOrderCreate);

		if (StringUtils.isNotBlank(workScope)){
			select.append(" AND EXISTS (SELECT 1 FROM PWUST_PL_SUBJ_WORKSCOPE C1 WHERE C1.PLAN_ID = A.PLAN_ID AND C1.PLAN_UPDT_NO = A.PLAN_UPDT_NO AND C1.WORKSCOPE = ? ) ");
		}

		if (StringUtils.isNotBlank(itemNo)){
			select.append(" AND A.PART_NO = ? ");
			select.append(" AND A.PART_CHG = ? ");
		}	
	
		if (StringUtils.isNotBlank(customer)){
			select.append(" AND EXISTS ( select 1 " +
		                             " from SFOR_SFPL_PLAN_SUBJECT c1, "+    // primary key (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO)
		                             " PWUST_PL_SUBJ_CUSTOMER  c2 " +        // primary key (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, CUST_ID)
		                             
		                             " where  c1.plan_id = a.plan_id " +
		                             " and    c1.plan_updt_no = a.plan_updt_no " +
		                             
		                             " and    c1.plan_id = c2.plan_id " +
		                             " and    c1.plan_updt_no =c2.plan_updt_no " +
		                             " and    c1.subject_no = c2.subject_no " +
		                             " and    c2.cust_id in ( ? , ? ) ) ");
		}
		
		
		if (StringUtils.isNotBlank(superNet)){
			
			select.append( " and exists ( select 1 " + 
		                                " from SFOR_SFPL_PLAN_SUBJECT a1, "+     //primary key (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO)   
	                                    " PWUST_PL_SUBJ_NETWORK_ACTIVITY a2  "+  // primary key (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, ENGINE_TYPE, SUPERIOR_NETWORK_ACT)
	                                    
	                                    " where a1.plan_id = a2.plan_id " +
	                                    " and   a1.plan_updt_no = a2.plan_updt_no "+
	                                    " and   a1.subject_no = a2.subject_no "+
	                                    
	                                    " and   a2.plan_id = a.plan_id " + 
	                                    " and   a2.plan_updt_no = a.plan_updt_no "+ 
	                                    " and   a2.engine_type = a.program "+ 
	                                    " and   a2.superior_network_act = ? ) " );
		}
		if (StringUtils.isNotBlank(subNet)){
			select.append( " and exists ( select 1 "+
                                        " from SFOR_SFPL_PLAN_SUBJECT b1, "+        // primary key (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO)
                                        " PWUST_PL_SUBJ_SUBNET_ACTIVITY b2   "+     // primary key (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, ENGINE_TYPE, SUPERIOR_NETWORK_ACT, SUB_NETWORK_ACT)
               
                                        " where b1.plan_id = b2.plan_id "+
                                        " and   b1.plan_updt_no = b2.plan_updt_no "+
                                        " and   b1.subject_no = b2.subject_no "+
               
                                        " and   b2.plan_id = a.plan_id "+ 
                                        " and   b2.plan_updt_no = a.plan_updt_no "+ 
                                        " and   b2.engine_type = a.program "+ 
                                        " and   b2.superior_network_act = ? "+
                                        " and   b2.sub_network_act = ? ) ");
		}
		
		/*
		if (StringUtils.isNotBlank(subNet)){
			select.append( " and exists ( select 1 "+
                                        " from SFOR_SFPL_PLAN_SUBJECT b1, "+        // primary key (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO)
                                        " PWUST_PL_SUBJ_NETWORK_ACTIVITY b2, "+     // primary key (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, ENGINE_TYPE, SUPERIOR_NETWORK_ACT)
                                        " PWUST_PL_SUBJ_SUBNET_ACTIVITY b3   "+     // primary key (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, ENGINE_TYPE, SUPERIOR_NETWORK_ACT, SUB_NETWORK_ACT)
               
                                        " where b1.plan_id = b2.plan_id "+
                                        " and   b1.plan_updt_no = b2.plan_updt_no "+
                                        " and   b1.subject_no = b2.subject_no "+
               
                                        " and b2.plan_id = b3.plan_id "+
                                        " and b2.plan_updt_no = b3.plan_updt_no "+
                                        " and b2.subject_no = b3.subject_no "+
                                        " and b2.engine_type = b3.engine_type "+
                                        " and b2.superior_network_act = b3.superior_network_act "+
               
                                        " and   b3.plan_id = a.plan_id "+ 
                                        " and   b3.plan_updt_no = a.plan_updt_no "+ 
                                        " and   b3.engine_type = a.program "+ 
                                        " and   b3.superior_network_act = ? "+
                                        " and   b3.sub_network_act = ? ) ");
		}
		*/
		
		
		select.append(" GROUP BY  A.PART_NO,A.PART_CHG,A.PLAN_ID,A.PLAN_VERSION, A.PLAN_ALTERATIONS ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(engineType);
		params.addParameter(sapWorkLoc);
		params.addParameter(engineModel);


		if (StringUtils.isNotBlank(workScope)){
			params.addParameter(workScope);
		}

		if (StringUtils.isNotBlank(itemNo)){
			params.addParameter(itemNo);
			params.addParameter("N/A");
		} 

		if (StringUtils.isNotBlank(customer)){
			params.addParameter("ALL");
			params.addParameter(customer);
		}
		
		if (StringUtils.isNotBlank(superNet)){
			params.addParameter(superNet);
		}
		
		if (StringUtils.isNotBlank(subNet)){
			params.addParameter(superNet);
			params.addParameter(subNet);
		}

		
		List list = jdbcDaoSupport.queryForList(select.toString(), params);

		if (list==null ||  list.isEmpty()){
			
			logOrderCreateErrors.insertIntoErrorTable("no plan found " + select.toString(),
										                null,//orderNumber,
										                null,//planDataSelected.getPartNo(),
										                null,//planDataSelected.getPlanId(),
										                null,//planDataSelected.getPlanRev(),
										                null,//planDataSelected.getPlanRev(),
										                null,//planDataSelected.getPlanAlt(),
										                null,//planDataSelected.getPlanUpdtNo(),
										                null,//userEnteredData.getSalesOrderNumber(),
										                engineType,
										                engineModel,
										                sapWorkLoc,
										                workScope,
										                superNet,
										                subNet,
										                customer,
										                "DAO" ) ;//calledFrom);
		}
		
		return list;
	}


	
	
	public List selectPlansForWOCreate( String engineType,
							            String sapWorkLoc,
							            String engineModel,
							            String workScope,
							            String itemNo,
							            String startDate,
							            String endDate){
		
		StringBuffer select = new StringBuffer(selectPlanForOrderCreate);
		
		if (StringUtils.isNotBlank(workScope)){
				select.append(" AND EXISTS (SELECT 1 FROM PWUST_PL_SUBJ_WORKSCOPE C1 WHERE C1.PLAN_ID = A.PLAN_ID AND C1.PLAN_UPDT_NO = A.PLAN_UPDT_NO AND C1.WORKSCOPE = ? ) ");
		}

		if (StringUtils.isNotBlank(itemNo)){
				select.append(" AND E.PART_NO = ? ");
				select.append(" AND E.PART_CHG = ? ");
		}	
								
		select.append(" GROUP BY  A.PART_NO,A.PART_CHG,A.PLAN_ID,A.PLAN_VERSION, A.PLAN_ALTERATIONS ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(engineType);
		params.addParameter(sapWorkLoc);
		params.addParameter(engineModel);
		
		
		if (StringUtils.isNotBlank(workScope)){
			params.addParameter(workScope);
		}
		
		if (StringUtils.isNotBlank(itemNo)){
			params.addParameter(itemNo);
			params.addParameter("N/A");
		} 
		
		List list = jdbcDaoSupport.queryForList(select.toString(), params);
		
		return list;
	}

	
	
	public List selectPlansForWOCreate( String engineType,
							            String sapWorkLoc,
							            String engineModel,
							            String itemNo,
							            String startDate,
							            String endDate){

		StringBuffer select = new StringBuffer(selectPlanForOrderCreate);


		if (StringUtils.isNotBlank(itemNo)){
			select.append(" AND E.PART_NO = ? ");
			select.append(" AND E.PART_CHG = ? ");
		}	
	
		select.append(" GROUP BY  A.PART_NO,A.PART_CHG,A.PLAN_ID,A.PLAN_VERSION, A.PLAN_ALTERATIONS ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(engineType);
		params.addParameter(sapWorkLoc);
		params.addParameter(engineModel);



		if (StringUtils.isNotBlank(itemNo)){
			params.addParameter(itemNo);
			params.addParameter("N/A");
		} 

		List list = jdbcDaoSupport.queryForList(select.toString(), params);

		return list;
	}
	
	
	public Map selectPlanUpdtNo(String planId, Number planVer, Number planRev, Number planAlt){
		
		ParameterHolder params = new ParameterHolder();
		
		StringBuffer select = new StringBuffer().append(" SELECT T.PLAN_UPDT_NO AS  PLAN_UPDT_NO  " +
		                                                " FROM SFPL_PLAN_REV T " +
		                                                " WHERE T.PLAN_ID = ? "+
		                                                " AND T.PLAN_VERSION = ? " +
		                                                " AND T.PLAN_REVISION = ? " +
		                                                " AND T.PLAN_ALTERATIONS = ?");
		
		params.addParameter(planId);
		params.addParameter(planVer);
		params.addParameter(planRev);
		params.addParameter(planAlt);
		
		Map  Map = jdbcDaoSupport.queryForMap(select.toString(), params);
		return Map;		
		
	}
	
	
	public Map selectOrderId(String orderNumber){
		
		ParameterHolder params = new ParameterHolder();
		
		StringBuffer select = new StringBuffer().append(" SELECT T.* " +
	                                                    " FROM SFWID_ORDER_DESC T "+
	                                                    " WHERE T.ORDER_NO = ? ");
		
		params.addParameter(orderNumber);
		Map map = jdbcDaoSupport.queryForMap(select.toString(), params);
		return map;		
	}

	
	
	public Map selectPwustSwidOrderDescViaPwOrderNo(String pwOrderNo){
		
		Map map = null;
		ParameterHolder params = new ParameterHolder();
		
		StringBuffer select = new StringBuffer().append(" SELECT T.* , " +
													    "        T1.PW_ORDER_NO, " +
													    "        T1.SALES_ORDER,  " +
													    "        T1.ENGINE_MODEL,  " +
													    "        T1.WORK_SCOPE, " +
													    "        T1.SUPERIORNET, " +
													    "        T1.SUBNET, " +
													    "        T1.CUSTOMER,  " +
													    "        T1.PW_ORDER_TYPE " +
													    " FROM SFWID_ORDER_DESC T,  " + 
													    "      PWUST_SFWID_ORDER_DESC T1  "+
	                                                    " WHERE T.ORDER_ID = T1.ORDER_ID " +
	                                                    " AND T1.PW_ORDER_NO = ? ");
		
		params.addParameter(pwOrderNo);
		map = jdbcDaoSupport.queryForMap(select.toString(), params);
		return map;		
	}

	
	public Map selectPwustSwidOrderDescViaObbOrderNo(String oobOrderNo){
		
		Map map = null;
		ParameterHolder params = new ParameterHolder();
		
		StringBuffer select = new StringBuffer().append(" SELECT T.* , " +
													    "        T1.PW_ORDER_NO, " +
													    "        T1.SALES_ORDER,  " +
													    "        T1.ENGINE_MODEL,  " +
													    "        T1.WORK_SCOPE, " +
													    "        T1.SUPERIORNET, " +
													    "        T1.SUBNET, " +
													    "        T1.CUSTOMER, " +
													    "        T1.PW_ORDER_TYPE " +
													    " FROM SFWID_ORDER_DESC T,  " + 
													    "      PWUST_SFWID_ORDER_DESC T1  "+
	                                                    " WHERE T.ORDER_ID = T1.ORDER_ID " +
	                                                    " AND T.ORDER_NO = ? ");
		
		params.addParameter(oobOrderNo);
		map = jdbcDaoSupport.queryForMap(select.toString(), params);
		return map;		
	}
	
	public Map selectPwustSwidOrderDescByOrderId(String orderId){
		
		ParameterHolder params = new ParameterHolder();
		
		StringBuffer select = new StringBuffer().append(" SELECT T.* , " +
													    "        T1.PW_ORDER_NO, " +
													    "        T1.SALES_ORDER,  " +
													    "        T1.ENGINE_MODEL,  " +
													    "        T1.WORK_SCOPE, " +
													    "        T1.SUPERIORNET, " +
													    "        T1.SUBNET, " +
													    "        T1.CUSTOMER,  " +
													    "        T1.PW_ORDER_TYPE  " +
													    " FROM SFWID_ORDER_DESC T,  " + 
													    "      PWUST_SFWID_ORDER_DESC T1  "+
	                                                    " WHERE T.ORDER_ID = T1.ORDER_ID " +
	                                                    " AND T1.ORDER_ID = ? ");
		
		params.addParameter(orderId);
		Map map = jdbcDaoSupport.queryForMap(select.toString(), params);
		return map;		
	}
	
	
	
	public Map selectSupplementalOrderNumber(String orderID){
		
		ParameterHolder params = new ParameterHolder();
		
		StringBuffer select = new StringBuffer().append(" SELECT T.ORDER_NO " +
                                                        " FROM SFWID_ORDER_DESC T " +
                                                        " WHERE T.ORDER_ID = ? " +
                                                        " AND T.ORDER_TYPE = ? ");
		
		params.addParameter(orderID);
		params.addParameter("Supplemental");
		Map map = jdbcDaoSupport.queryForMap(select.toString(), params);
		return map;		
	}	

	public List selectSfplPlannEff(String planId, Number planVer, Number planRev, Number planAlt, String effType){

		ParameterHolder params = new ParameterHolder();
		
		StringBuffer select = new StringBuffer().append(" SELECT  SUBSTR(T.EFF_FROM,1,10) EFF_FROM " +
	                                                    " FROM SFPL_PLAN_EFF T  "+ //  PRIMARY KEY (PLAN_ID, PLAN_VERSION, PLAN_REVISION, PLAN_ALTERATIONS, EFF_TYPE, EFF_FROM)
	                                                    " WHERE T.PLAN_ID = ? " +
	                                                    " AND T.PLAN_VERSION = ? "+
	                                                    " AND T.PLAN_REVISION = ? "+
	                                                    " AND T.PLAN_ALTERATIONS =  ? "+
	                                                    " AND T.EFF_TYPE = ? "+
	                                                    " ORDER BY T.TIME_STAMP DESC ");

		params.addParameter(planId);
		params.addParameter(planVer);
		params.addParameter(planRev);
		params.addParameter(planAlt);
		params.addParameter(effType);
		
		List list = jdbcDaoSupport.queryForList(select.toString(), params);
		return list;

	}
	
	
	public List selectPwustSalesOrderDates(String salesOrder, String actNo, String subActNo){
		
		
		ParameterHolder params = new ParameterHolder();
		
		StringBuffer select = new StringBuffer().append(" SELECT TO_CHAR(T.SUB_ACT_NO_START_DATE,  'MM/DD/YYYY') AS SUB_ACT_NO_START_DATE, " + 
				                                               " TO_CHAR(T.SUB_ACT_NO_FINISH_DATE, 'MM/DD/YYYY') AS SUB_ACT_NO_FINISH_DATE " +
	                                                    " FROM PWUST_SALES_ORDER T  " + // PRIMARY KEY (SALES_ORDER, ACT_NO, SUB_ACT_NO)
	                                                    " WHERE T.SALES_ORDER = ? " + 
	                                                    " AND T.ACT_NO = ? " + 
	                                                    " AND T.SUB_ACT_NO = ? ");
		
		params.addParameter(salesOrder);
		params.addParameter(actNo);
		params.addParameter(subActNo);
		
		List list = jdbcDaoSupport.queryForList(select.toString(), params);
		return list;

	}
	
	public String getOverHaulWorkOrderNumber(String orderNo,String pwOrderType) throws Exception{


		String exitingOrderNumber = null;
		/*
		Connection dbConnection = DataSourceUtils.getConnection(jdbcDaoSupport.getDataSource());
		CallableStatement cstmt = null;
		cstmt  = dbConnection.prepareCall("{call "+jdbcDaoSupport.getSchemaPrefix()+"PWUST_GET_OVERHAUL_WO_SUFFIX( ?, ?, ?  )}");
		cstmt.setString(1, orderNo);
		cstmt.setString(2, pwOrderType);
		cstmt.registerOutParameter(3, Types.VARCHAR);
		cstmt.execute();
		exitingOrderNumber = cstmt.getString(3);
		*/
		
		StringBuffer select = new StringBuffer().append( " SELECT TT.PW_ORDER_NO " +
                                                         " FROM (SELECT T.PW_ORDER_NO " +
													     "       FROM PWUST_SFWID_ORDER_DESC T " +
													     "       WHERE T.PW_ORDER_NO LIKE ? " +
													     "       AND T.PW_ORDER_TYPE = ? " +
													     "       ORDER BY T.PW_ORDER_NO DESC ) TT " +
													     " WHERE ROWNUM =1 ");
		
		
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderNo);
		params.addParameter(pwOrderType);
		List list = jdbcDaoSupport.queryForList(select.toString(), params);
		if (list!=null && !list.isEmpty()){
			Map map = (Map) list.iterator().next();
			exitingOrderNumber = (String )map.get("PW_ORDER_NO");
		}

		return exitingOrderNumber;

	}
	
	

	
	
	
	public List selectWorkOrderOverhaulDispatch (){
		
		StringBuffer select = new StringBuffer().append(" SELECT DISTINCT T.UCF_ORDER_VCH2 AS SALES_ORDER, " +
	                                                                    " T.UCF_ORDER_VCH7 AS ENGINE_TYPE, " +
	                                                                    " T.UCF_ORDER_VCH8 AS ENGINE_MODEL," +
	                                                                    " T.UCF_ORDER_VCH9 AS WORK_LOC, " +
	                                                                    " T.UCF_ORDER_VCH10 AS CUSTOMER_NO," +
	                                                                    " T.UCF_ORDER_VCH255_1 AS CUSTOMER_DESC, " +
	                                                                    " T.UCF_ORDER_VCH6 AS SERIAL_NO, " +
	                                                                    " '' AS NEW_SALES_ORDER, " +
	                                                                    " '' AS NEW_ENGINE_TYPE, " +
	                                                                    " '' AS NEW_ENGINE_MODEL, " +
	                                                                    " '' AS NEW_SAP_WORK_LOC, " +
	                                                                    " '' AS NEW_WORK_SCOPE, " +
	                                                                    " '' AS NEW_Sup_Net_Act, " +
	                                                                    " '' AS NEW_Sub_Net_Act, " +
	                                                                    " '' as NEW_CUSTOMER_NO, " +
	                                                                    " '' as NEW_PLANED_ORDER, " +
	                                                                    " T.SECURITY_GROUP " +
	                                                    " FROM SFWID_ORDER_DESC T " +
	                                                    " WHERE T.ORDER_TYPE =  ?  " +
	                                                    " AND T.UCF_ORDER_VCH2 IS NOT NULL " +
	                                                    " ORDER  BY SALES_ORDER ");


		ParameterHolder params = new ParameterHolder();
		params.addParameter("Overhaul");
		List list = jdbcDaoSupport.queryForList(select.toString(), params);

		return list;

	}
	
	public List selectWorkOrderOverhaulTab (String salesOrder){
		
		StringBuffer select = new StringBuffer().append(" SELECT  T.ORDER_NO AS ORDER_NO, " +
                                                        	    " T.PLAN_TITLE AS PLAN_TITLE, " +
                                                        	    " (SELECT T1.ALT_NO FROM SFWID_ALTERATION_DESC T1 WHERE T1.ALT_ID = T.ALT_ID) AS ALT_NO, " +
                                                        	    " T.ORDER_STATUS AS ORDER_STATUS, " +
                                                        	    " T.UCF_ORDER_VCH5 AS WORK_SCOPE, " +
                                                        	    " T.UCF_ORDER_VCH3 AS SUPERIOR_NETWORK_ACTIVITY, " +
                                                        	    " T.UCF_ORDER_VCH4 AS SUB_NETWORK_ACTIVITY, " +
                                                        	    " T.UCF_ORDER_VCH255_1 AS SUB_NETWORK_ACTIVITY_DESC, " +
                                                        	    " T.ALT_STATUS AS ALT_STATUS , " +
                                                        	    " T.ORDER_HOLD_STATUS AS ORDER_HOLD_STATUS, " +
                                                        	    "  (SELECT T2.WORK_LOC FROM SFFND_WORK_LOC_DEF T2 WHERE T2.LOCATION_ID = T.ASGND_LOCATION_ID) AS  ASGND_WORK_LOC, " +
                                                        	    " T.TIME_STAMP AS CREATE_DATE, " +
                                                        	    " T.UPDT_USERID AS RELEASE_DATE, " +
                                                        	    " T.UPDT_USERID AS CREATE_BY, " +
                                                        	    " T.ORDER_TYPE AS ORDER_TYPE, " +
                                                        	    " T.PLAN_NO AS PLAN_NO, " +
                                                        	    " T.PART_NO AS PART_NO, " +
                                                        	    " T.PARENT_ORDER_ID AS PARENT_ORDER_ID, " +
                                                        	    " (SELECT T1.UPDT_USERID FROM SFWID_ALTERATION_DESC T1 WHERE T1.ALT_ID = T.ALT_ID) AS ALTERED_BY, " +
                                                        	    " (SELECT T1.ALT_REASON FROM SFWID_ALTERATION_DESC T1 WHERE T1.ALT_ID = T.ALT_ID) AS REASON_FOR_CHANGE, " +
                                                        	    " T.SECURITY_GROUP AS SECURITY_GROUP" +
                                                        " FROM SFWID_ORDER_DESC T " +
                                                        " WHERE NVL(T.UCF_ORDER_VCH2,'NULL') =  ? " );
		
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(salesOrder);
		List list = jdbcDaoSupport.queryForList(select.toString(), params);

		return list;
	}

	public int getNextAutoNumber(String numberGeneratorName)
	{
		int nextNumber = 0;
		Connection conn = null;
		try
		{
			conn = DataSourceUtils.getConnection(jdbcDaoSupport.getDataSource());
			CallableStatement cstmt = null;
			try
			{
				cstmt  = conn.prepareCall("{call "+jdbcDaoSupport.getSchemaPrefix()+"SFFND_GET_NEXT_AUTO_NUM(?, ?)}");
				cstmt.setString(1, numberGeneratorName);
				cstmt.registerOutParameter(2, Types.NUMERIC);
				cstmt.execute();
				nextNumber = cstmt.getInt(2);
			}
			finally
			{
				// FND-25983 Close the Opened Callable Statement 
				if(cstmt != null)
				{
					cstmt.close();
				}
			}
			
		}
		catch(Exception e)
		{
			throw new SoluminaException("ERROR CALLING SFFND_GET_NEXT_AUTO_NUM PROC: " + e);
		}

		return nextNumber;
	}
	
	
	public List getOrderNunmberSuffix(String orderNumber){

		
		StringBuffer select = new StringBuffer().append(" SELECT SUBSTR(T.ORDER_NO,LENGTH(T.ORDER_NO)-1,2) ORDER_SUFFIX " + 
		                                                " FROM SFWID_ORDER_DESC T " +
		                                                " WHERE UPPER(T.ORDER_NO) LIKE UPPER ? " +
		                                                " ORDER BY ORDER_SUFFIX DESC " ); 
		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderNumber);
		
		List list = jdbcDaoSupport.queryForList(select.toString(), params);
		
		return list;
		
	}
	
	
	public String selectDBTimeStamp(String format)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT TO_CHAR(SFMFG.SFDB_SYSDATE(), '" + format + "') AS DB_SYSDATE FROM DUAL ");
		return (String) jdbcDaoSupport.queryForObject(selectSql.toString(), String.class);
	}
	
	public String getTodaysDate(){

		StringBuffer select = new StringBuffer().append(" SELECT TRUNC(SYSDATE) AS TODAYSDATE FROM DUAL  ");
		Map map = jdbcDaoSupport.queryForMap(select.toString());
		Timestamp todaysDate = (Timestamp)map.get("TODAYSDATE");
		return todaysDate.toString();
	}
	
	
	public List selectPwustNetworkDef(String engineType,
			                          String supNetworkAct,
			                          String subNetworkAct){

		StringBuffer select = new StringBuffer().append(" SELECT  ENGINE_TYPE, " +
	                                                            " ENGINE_TYPE_DESC, "+
	                                                            " STD_NETWORK_NO, "+
	                                                            " SUPERIOR_NETWORK_ACT, "+
	                                                            " SUPERIOR_NETWORK_ACT_DESC, "+
	                                                            " MODULE_NETWORK_NO, "+
	                                                            " SUB_NETWORK_ACT,  "+
	                                                            " SUB_NETWORK_ACT_DESC, "+
	                                                            " MODULE_CAT_CODE, "+
	                                                            " MODULE_CAT_DESC, "+
	                                                            " WORK_CENTER, "+
	                                                            " CONTROL_KEY, "+
	                                                            " GATE, "+
	                                                            " DURATION, "+
	                                                            " DURATION_UNIT, "+
	                                                            " WORK, "+
	                                                            " WORK_UNIT, "+
	                                                            " EWORKSCOPING_FLAG, "+
	                                                            " LID_NO  "+ 
	                                                      " FROM PWUST_NETWORK_DEF  "+ 
	                                                      " WHERE ENGINE_TYPE = ?  "+ 
	                                                      " AND SUPERIOR_NETWORK_ACT = ?  "+
	                                                      " AND SUB_NETWORK_ACT = "+ 
	                                                      " AND OBSOLETE_RECORD_FLAG = ? ");
		ParameterHolder params = new ParameterHolder();
		params.addParameter(engineType);
		params.addParameter(supNetworkAct);
		params.addParameter(subNetworkAct);
		params.addParameter("N");
		
		List list = jdbcDaoSupport.queryForList(select.toString(), params);
		
		return list;
	}

	
	public void insertInfoPwustPlannedOrders(String salesOrder,
			                                 String engineType,
			                                 String engineModel,
			                                 String sapWorkLoc,
								             String workScope,
								             String partNoSel,
                                             String supNetworkAct,
                                             String subNetworkAct,
                                             String customer,
                                             String planId,
                                             Number planVer,
                                             Number planRev,
                                             Number planAlt,
                                             Number planUpdtNo,
                                             String partNo){

					String userName = ContextUtil.getUser().getUsername();
					
		            StringBuffer insert1 = new StringBuffer().append("select 'PWUST_' || PWUST_PLANNED_ORDERS_SEQ.NEXTVAL from dual ");
		            String nextPLValue = jdbcDaoSupport.queryForString(insert1.toString());
		
		
					StringBuffer insert = new StringBuffer().append(" INSERT INTO PWUST_PLANNED_ORDERS( PRIMERY_KEY, "+
														                                                " SALES_ORDER, "+
														                                                " ENGINE_TYPE, "+
														                                                " ENGINE_MODEL, "+
														                                                " SAPWORKLOC, "+
														                                                " WORK_SCOPE, "+
														                                                " ITEM_NO_SEL, "+
														                                                " SUPERIORNET, "+
														                                                " SUBNET, "+
														                                                " CUSTOMER, "+
														                                                " PLAN_ID, "+
														                                                " PLAN_VERSION, "+
														                                                " PLAN_REVISION, "+
														                                                " PLAN_ALTERATIONS, "+
														                                                " PLAN_UPDT_NO, " +
														                                                " PART_NO, UPDT_USERID ) "+
					                                                " VALUES( ? , "+
							                                                " ? , "+
							                                                " ? , "+
							                                                " ? , "+
							                                                " ? , "+
							                                                " ? , "+
							                                                " ? , "+ 
							                                                " ? , "+
							                                                " ? , "+
							                                                " ? , "+
							                                                " ? , "+
							                                                " ? , "+
							                                                " ? , "+
							                                                " ? , "+
							                                                " ? ," +
							                                                " ? , ? ) ");
					ParameterHolder params = new ParameterHolder();
					
					params.addParameter(nextPLValue);
					params.addParameter(salesOrder);
					params.addParameter(engineType);
					params.addParameter(engineModel);
					params.addParameter(sapWorkLoc);
					params.addParameter(workScope);
					params.addParameter(partNoSel);
					params.addParameter(supNetworkAct);
					params.addParameter(subNetworkAct);
					params.addParameter(customer); 
					params.addParameter(planId);
					params.addParameter(planVer);
					params.addParameter(planRev);
					params.addParameter(planAlt);
					params.addParameter(planUpdtNo);
					params.addParameter(partNo);
					params.addParameter(userName);
					
					jdbcDaoSupport.insert(insert.toString(), params);

	}
	
	
	public int deleteFromPwustPlannedOrders(String primeryKey){
		
		StringBuffer delete = new StringBuffer().append("DELETE FROM PWUST_PLANNED_ORDERS T  WHERE T.PRIMERY_KEY = ? ");
		ParameterHolder params = new ParameterHolder();
		params.addParameter(primeryKey);
		
		int numberRowsDel = jdbcDaoSupport.delete(delete.toString(),params);
		return numberRowsDel;
		
		
	}


}
