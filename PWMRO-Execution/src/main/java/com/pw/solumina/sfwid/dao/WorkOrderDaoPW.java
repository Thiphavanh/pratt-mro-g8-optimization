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
 *  File:    WorkOrderDaoPW.java
 * 
 *  Created: 2018-04-05
 * 
 *  Author:  Fred Ettefagh
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2018-04-05		Fred Ettefagh   Initial Release PW_SMRO_WOE_eWORK - Defect 577
 *  2018-04-06		Fred Ettefagh   added method selectOrderPartInfoForTab for defect 44
 *  2018-04-26		Catrina N	    Added a method to lookup the system order no using the Pratt Order number.  Defect 699
 *  2018-04-26		Jim Petraitis	SMRO_RPT_201 - Add methods selectOrderNoByOutgoingSalesOrder(String) & getHeader
 *  2018-05-17		Jim Petraitis	Defect 773 - Get/Use the Extended Order Number.
 *  2018-06-05      Fred Ettefagh   defect 760, changed insert and update oper, so that conf number is updated via LO table.  This is to fix issue of adding/updating oper when order in active or inqueue
 *  2018-06-08      David Miron     MRO_RPT_201 Defect 650 - Added ORDER_CONTROL_TYPE to getHeader SELECT.
 *  2018-06-17      Sam Niu         defect 805, Alteration Completion check for copied operations  
 *  2018-07-06		Bob Preston		SMRO_WOE_211 Defect 779, Added updateAltCount.
 *  2018-07-24      Raeann Thorpe   SMRO_RPT_201- Defect 869 Modified getHeader method
 *  2018-07-26      Fred Ettefagh   SMRO_RPT_201- Defect 904/911 Modified getHeader method to use NVL to put  N/A for plan header data 
 *  2018-07-30      Raeann Thorpe   SMRO_RPT_201- Defect 910  - added 'order by' to selectOrderNoByOutgoingSalesOrder and getHeader
 *  2018-08-08      Fred Ettefagh   defect 935 fixed method getPlanOperNetworkActivityRow to check to see if WO oper exists in a plan via oper_key instead of oper_no 
 *  2018-10-01      Fred Ettefagh   Defect 980 Cannot Create Suborder due to supNet subNet and conf Numbers
 *  2018-11-09      Raeann Thorpe   Defect 1035 - added COMMODITY_JURISDICTION,   COMMODITY_CLASSIFICATION in get Header, removed commented out code
 *  2018-11-15      Fred Ettefagh   Defect 1008 - Task groups are not carried over through Sub Orders
 *  2021-01-19      B.Polak         SMRO_RPT_201 Defect 1832 - Added Tag and Sales Order to call to delivery package header
 *  2021-10-12      J DeNinno       Defect 1984
 */

package com.pw.solumina.sfwid.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.FrameworkConstants;
import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;

public class WorkOrderDaoPW {

	@Reference
	private JdbcDaoSupport jdbcDaoSupport;
	
	
	// defect 1008
	public int updateSfwidOrderDescPlanData (String orderId, 
				                                 String planId,
				                                 Number planVer,
				                                 Number planRev,
				                                 Number planAlt,
				                                 String planNo,
				                                 Number planUpdtNo,
				                                 String planType){
			
			
			StringBuffer updateSql = new StringBuffer().append(" UPDATE SFWID_ORDER_DESC  T " +
												               " SET T.PLAN_ID = ?, " + 
				                                               "     T.PLAN_VERSION = ?, " +
				                                               "     T.PLAN_REVISION = ?,  " +
				                                               "     T.PLAN_ALTERATIONS = ?, " +
				                                               "     T.PLAN_NO = ?, " +
				                                               "     T.PLAN_UPDT_NO = ?, " +
				                                               "     T.PLAN_TYPE = ? " +
															   " WHERE T.ORDER_ID = ?");
			
			ParameterHolder parameters = new ParameterHolder();
			parameters.addParameter(planId);
			parameters.addParameter(planVer);
			parameters.addParameter(planRev);
			parameters.addParameter(planAlt);
			parameters.addParameter(planNo);
			parameters.addParameter(planUpdtNo);
			parameters.addParameter(planType);
			parameters.addParameter(orderId);
			
			int numbOfRows = jdbcDaoSupport.update(updateSql.toString(), parameters);
			return numbOfRows;
	}
	
	// defect 44
    public List selectOrderPartInfoForTab(String orderId,
										  String userId,
										  Number operationKey,
										  String whereClause){
    	
			StringBuffer select = new StringBuffer().append("	SELECT 	")
													.append("	 OPER_NO,OPER_KEY	")
													.append("	,REF_DES	")
													.append("	,REF_DES_PREF_RANK,STEP_KEY,PART_NO,PART_CHG,ITEM_TYPE,ITEM_SUBTYPE,PART_LINE_NO,PART_DAT_COL_ID,PLND_ITEM_ID,PLND_ITEM_QTY,PART_TITLE,UOM	")
													.append("	,SERIAL_FLAG,LOT_FLAG,EXP_FLAG,SPOOL_FLAG,FIND_NO,BOM_LINE_NO,ITEM_NOTES,PART_ACTION	")
													.append("	,OPT_DC1_FLAG,OPT_DC2_FLAG,OPT_DC3_FLAG,OPT_DC4_FLAG,ucf_plan_item_vch1,ucf_plan_item_vch2,ucf_plan_item_vch3,ucf_plan_item_vch4	")
													.append("	,ucf_plan_item_vch5,ucf_plan_item_flag1,ucf_plan_item_flag2,ucf_plan_item_num1,ucf_plan_item_num2,over_consumption_flag	")
													.append("	,ORIENTATION_FLAG	")
													.append("	,cross_order_flag,item_category,store_loc,unloading_point,step_no,standard_part_flag,optional_flag,updt_userid,time_stamp	")
													.append("	,UCF_PLAN_ITEM_VCH6,UCF_PLAN_ITEM_VCH7,UCF_PLAN_ITEM_VCH8,UCF_PLAN_ITEM_VCH9,UCF_PLAN_ITEM_VCH10,UCF_PLAN_ITEM_VCH11,UCF_PLAN_ITEM_VCH12	")
													.append("	,UCF_PLAN_ITEM_VCH13,UCF_PLAN_ITEM_VCH14,UCF_PLAN_ITEM_VCH15,UCF_PLAN_ITEM_NUM3,UCF_PLAN_ITEM_NUM4,UCF_PLAN_ITEM_NUM5,UCF_PLAN_ITEM_DATE1	")
													.append("	,UCF_PLAN_ITEM_DATE2,UCF_PLAN_ITEM_DATE3,UCF_PLAN_ITEM_DATE4,UCF_PLAN_ITEM_DATE5,UCF_PLAN_ITEM_FLAG3,UCF_PLAN_ITEM_FLAG4,UCF_PLAN_ITEM_FLAG5	")
													.append("	,UCF_PLAN_ITEM_VCH255_1,UCF_PLAN_ITEM_VCH255_2,UCF_PLAN_ITEM_VCH255_3,UCF_PLAN_ITEM_VCH4000_1,UCF_PLAN_ITEM_VCH4000_2	")
													.append("	,EXTERNAL_PLM_NO,EXTERNAL_ERP_NO	")
													.append("	,IMAGE,	")
													.append("	SLIDE_ID,SLIDE_EMBEDDED_REF_ID,REMOVE_ACTION,UTILIZATION_RULE,TRACKABLE_FLAG,UID_ITEM_FLAG,	")
													.append("	UID_ENTRY_NAME,	")
													.append("	CLOCK_SYMBOL,	")
													.append("	DISPLAY_LINE_NO,	")
													.append("	SECURITY_GROUP,	")
													.append("	PHANTOM_KIT_PART_NO,	")
													.append("	UNIT_TYPE,	")
													.append("	EFF_FROM,	")
													.append("	EFF_THRU,	")
													.append("	EFF_FROM_DATE,	")
													.append("	EFF_THRU_DATE,	")
													.append("	BOM_COMP_ID,	")
													.append("	AUTHOR_FROM,	")
													.append("	STEP_NO_ORDERBY,	")
													.append("   EFF_FROM_DATE_STR, ")
								                    .append("   EFF_THRU_DATE_STR, ")
													.append("	BOM_ID,	")
													.append("	COMP_PART_OBSOLETE_FLAG, ")
													.append("	EXPLICIT_BOM_LINK_FLAG,	")
													.append("	TOTAL_QTY	")
													.append("	FROM (	")
													.append("	        SELECT DISTINCT	")
													.append("	               OPER_NO,OPER_KEY	")
													.append("                  ,CASE REF_DES WHEN 'N/A' THEN NULL ELSE REF_DES END AS REF_DES	")
													.append("	               ,REF_DES_PREF_RANK,STEP_KEY,A.PART_NO,A.PART_CHG,'PART' AS ITEM_TYPE,A.ITEM_SUBTYPE,PART_LINE_NO,PART_DAT_COL_ID,PLND_ITEM_ID,PLND_ITEM_QTY,PART_TITLE,UOM	")
													.append("	               ,A.SERIAL_FLAG,A.LOT_FLAG,EXP_FLAG,SPOOL_FLAG,FIND_NO,BOM_LINE_NO,ITEM_NOTES,PART_ACTION	")
													.append("	               ,OPT_DC1_FLAG,OPT_DC2_FLAG,OPT_DC3_FLAG,OPT_DC4_FLAG,ucf_plan_item_vch1,ucf_plan_item_vch2,ucf_plan_item_vch3,ucf_plan_item_vch4	")
													.append("	               ,ucf_plan_item_vch5,ucf_plan_item_flag1,ucf_plan_item_flag2,ucf_plan_item_num1,ucf_plan_item_num2,over_consumption_flag	")
													.append("	               ,CASE ORIENTATION_FLAG	")
													.append("	               WHEN 'U' THEN 'Unit Centric'	")
													.append("	               WHEN 'D' THEN 'Data Collection Centric'	")
													.append("	               END AS ORIENTATION_FLAG	")
													.append("	               ,cross_order_flag,item_category,store_loc,unloading_point,step_no,standard_part_flag,optional_flag,A.updt_userid,A.time_stamp	")
													.append("				   ,UCF_PLAN_ITEM_VCH6,UCF_PLAN_ITEM_VCH7,UCF_PLAN_ITEM_VCH8,UCF_PLAN_ITEM_VCH9,UCF_PLAN_ITEM_VCH10,UCF_PLAN_ITEM_VCH11,UCF_PLAN_ITEM_VCH12	")
													.append("	               ,UCF_PLAN_ITEM_VCH13,UCF_PLAN_ITEM_VCH14,UCF_PLAN_ITEM_VCH15,UCF_PLAN_ITEM_NUM3,UCF_PLAN_ITEM_NUM4,UCF_PLAN_ITEM_NUM5,UCF_PLAN_ITEM_DATE1	")
													.append("	               ,UCF_PLAN_ITEM_DATE2,UCF_PLAN_ITEM_DATE3,UCF_PLAN_ITEM_DATE4,UCF_PLAN_ITEM_DATE5,UCF_PLAN_ITEM_FLAG3,UCF_PLAN_ITEM_FLAG4,UCF_PLAN_ITEM_FLAG5	")
													.append("	               ,UCF_PLAN_ITEM_VCH255_1,UCF_PLAN_ITEM_VCH255_2,UCF_PLAN_ITEM_VCH255_3,UCF_PLAN_ITEM_VCH4000_1,UCF_PLAN_ITEM_VCH4000_2	")
													.append("	               ,A.EXTERNAL_PLM_NO,A.EXTERNAL_ERP_NO	")
													.append("	               ,CASE WHEN SLIDE_EMBEDDED_REF_ID IS NULL THEN NULL	")
													.append("	               WHEN SFMFG.SFFND_MM_SECURITY_GROUP_OK(SLIDE_ID,?) <> 'Y' THEN NULL	")
													.append("	               ELSE 'Image' END AS IMAGE,	")
													.append("	               SLIDE_ID,SLIDE_EMBEDDED_REF_ID,REMOVE_ACTION,UTILIZATION_RULE,TRACKABLE_FLAG,A.UID_ITEM_FLAG,	")
													.append("	               (CASE WHEN A.UID_ITEM_FLAG <> 'N' AND A.UID_ENTRY_NAME IS NULL THEN	")
													.append("	               SFMFG.SFFND_UID_ENTRY_COMP_PART_GET(NULL, NULL, A.ORDER_ID, OPER_KEY, STEP_KEY, NULL,	")
													.append("	               PLND_ITEM_ID,COALESCE(REF_DES, 'N/A'), NULL, NULL, NULL, PART_DAT_COL_ID,'OrderPartList' )	")
													.append("	               ELSE A.UID_ENTRY_NAME END) AS UID_ENTRY_NAME,	")
													.append("	               CASE SFMFG.SFWID_ORDER_PART_ALERT(A.ORDER_ID, OPER_KEY, STEP_KEY,PART_DAT_COL_ID)	")
													.append("	               WHEN  '0' THEN NULL	")
													.append("	               WHEN  '1' THEN 'CLOCK_BLUE'	")
													.append("	               WHEN  '3' THEN 'CLOCK_RED'	")
													.append("	               ELSE NULL	")
													.append("	               END AS CLOCK_SYMBOL,	")
													.append("	               DISPLAY_LINE_NO,	")
													.append("	               A.SECURITY_GROUP,	")
													.append("	               PHANTOM_KIT_PART_NO,	")
													.append("	               A.UNIT_TYPE,	")
													.append("	               EFF_FROM,	")
													.append("	               EFF_THRU,	")
													.append("	               EFF_FROM_DATE,	")
													.append("	               EFF_THRU_DATE,	")
													.append("	               BOM_COMP_ID,	")
													.append("                  CASE WHEN BOM_COMP_ID IS NULL  THEN 'I' ELSE 'B' END AUTHOR_FROM,    ")
													.append("	               COALESCE(STEP_NO,'...') AS STEP_NO_ORDERBY,	")
													.append("                  SFMFG.SFDB_DATE_TO_VARCHAR(EFF_FROM_DATE,'"+FrameworkConstants.DATE_MASK_NOTIME+"') AS EFF_FROM_DATE_STR, ")
													.append("                  SFMFG.SFDB_DATE_TO_VARCHAR(EFF_THRU_DATE,'"+FrameworkConstants.DATE_MASK_NOTIME+"') AS EFF_THRU_DATE_STR, ")
													.append("	               B.BOM_ID,	")
													.append("                  (SELECT OBSOLETE_RECORD_FLAG FROM SFPL_ITEM_DESC_MASTER_ALL IT WHERE IT.ITEM_ID=A.PLND_ITEM_ID) AS COMP_PART_OBSOLETE_FLAG, ")
													.append("	               B.EXPLICIT_BOM_LINK_FLAG,	") // FND-24392
													.append("	               ( SELECT SUM(C.PLND_ITEM_QTY) FROM SFWID_OPER_ITEMS C WHERE C.BOM_COMP_ID = A.BOM_COMP_ID AND C.ORDER_ID = A.ORDER_ID) AS TOTAL_QTY	")
					.append("	FROM SFWID_OPER_ITEMS A, SFWID_ORDER_DESC B	")
					.append("	WHERE  A.ORDER_ID    = ?	")
					.append("	AND A.ORDER_ID = B.ORDER_ID	")
					.append("   AND SUSPECT_FLAG = 'N'	")
					// defect 44
					.append("   AND EXISTS (SELECT 1 FROM sfor_sfwid_oper_subject TT WHERE TT.ORDER_ID = A.ORDER_ID AND TT.OPER_KEY = A.OPER_KEY AND  TT.INCLUDED_FLAG = ? ) ");			
					
		if(operationKey!=null){
			select.append(" AND OPER_KEY= " +operationKey +") X");
		}else		{
			select.append(") X");
		}


		select.append(" WHERE 1 = 1 /*+ @FILTER */");
		if (!StringUtils.isEmpty(whereClause)){
				select.append(" AND "+ whereClause);
		}
		select.append("	  ORDER BY OPER_NO,STEP_NO_ORDERBY, DISPLAY_LINE_NO,PART_NO, PART_CHG,REF_DES	");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(userId);
		params.addParameter(orderId);
		params.addParameter("INCLUDED");
			
		return jdbcDaoSupport.queryForList(select.toString(), params);
    }
	
	
	
	public List selectOrderOper(String orderId){
		
		
		StringBuffer select = new StringBuffer().append(" SELECT T.* " +
		                                                " FROM SFWID_OPER_DESC T " +
		                                                " WHERE T.ORDER_ID = ? " +
		                                                " AND T.STEP_KEY = ? ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(-1);
		
		List list = jdbcDaoSupport.queryForList(select.toString(),params);
		return list;
		
	}
	
	public Map getPlanOperNetworkActivityRow(String planId,
			                                 Number planUpdtNo,
			                                 Number operKey,
			                                 String engineType){
		
		
		Map map = null;
		StringBuffer select = new StringBuffer().append("  SELECT T.OPER_NO, " +
														        " T.OPER_KEY, " +
														        " T2.SUPERIOR_NETWORK_ACT, " +
														        " T3.SUB_NETWORK_ACT " +
	                                                    "  FROM SFPL_OPERATION_REV T,    " +         
													    "       SFOR_SFPL_PLAN_SUBJECT_OPER T1, " +  
													    "       PWUST_PL_SUBJ_NETWORK_ACTIVITY T2, " +
													    "       PWUST_PL_SUBJ_SUBNET_ACTIVITY T3  " +
													    
														" WHERE T.PLAN_ID = T1.PLAN_ID " +
														" AND T.PLAN_UPDT_NO = T1.PLAN_UPDT_NO " +
														" AND T.OPER_KEY = T1.OPER_KEY " +
														" AND T.OPER_UPDT_NO = T1.OPER_UPDT_NO " +

														" AND T1.PLAN_ID = T2.PLAN_ID " +
														" AND T1.PLAN_UPDT_NO = T2.PLAN_UPDT_NO " +
														" AND T1.SUBJECT_NO = T2.SUBJECT_NO " +
														" AND T2.ENGINE_TYPE = ? " +

														" AND T2.PLAN_ID = T3.PLAN_ID " +
														" AND T2.PLAN_UPDT_NO = T3.PLAN_UPDT_NO " +
														" AND T2.SUBJECT_NO = T3.SUBJECT_NO " +
														" AND T2.ENGINE_TYPE = T3.ENGINE_TYPE " +
														" AND T2.SUPERIOR_NETWORK_ACT = T3.SUPERIOR_NETWORK_ACT " +
													
														" AND T.PLAN_ID = ? " +
														" AND T.PLAN_UPDT_NO = ? " +
														" AND T.OPER_KEY = ? ");	
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(engineType);
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(operKey);
		
		List list = jdbcDaoSupport.queryForList(select.toString(),params);
		if (list!=null && !list.isEmpty()){
			map =(Map)list.iterator().next();
		}
		return map;
		
	}
	
	public Map selectPwustSfwidOrderDesc(String orderId){
		
		Map map = null;
		StringBuffer select = new StringBuffer().append(" SELECT T1.PW_ORDER_NO, " +
                                                               " T1.PW_ORDER_TYPE, "+
                                                               " T1.SALES_ORDER, " +
														       " T1.ENGINE_MODEL, " +
														       " T1.WORK_SCOPE, " +
														       " T1.SUPERIORNET, " +
														       " T1.SUBNET, " +
														       " T1.CUSTOMER, " +
														       " (SELECT TT.WORK_LOC FROM SFFND_WORK_LOC_DEF TT WHERE TT.LOCATION_ID = T.ASGND_LOCATION_ID) AS WORK_LOC, "+
														       " T.* " +
														" FROM SFWID_ORDER_DESC T, PWUST_SFWID_ORDER_DESC T1  " +
														" WHERE T.ORDER_ID = T1.ORDER_ID " +
														" AND T.ORDER_ID = ? ");
				
		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		List list = jdbcDaoSupport.queryForList(select.toString(),params);
		if (list!=null && !list.isEmpty()){
			map =(Map)list.iterator().next();
		}
		return map;
	}
	
	
	public List selectPwustSalesOrder(String salesOrder, String engineType, String engineModel, String workLoc){
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT T.* " +
                                                           " FROM PWUST_SALES_ORDER  T " + // PRIMARY KEY (SALES_ORDER, ACT_NO, SUB_ACT_NO)
                                                           " WHERE T.SALES_ORDER = ? " +
                                                           " AND T.ENGINE_TYPE = ? " + 
														   " AND T.ENGINE_MODEL = ? " + 
														   " AND T.WORK_LOC = ?  ");
		 


		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(salesOrder);
		parameters.addParameter(engineType);
		parameters.addParameter(engineModel);
		parameters.addParameter(workLoc);
		
		List list = jdbcDaoSupport.queryForList(selectSql.toString(), parameters);
		
		return list;	
	}
	
	public Map selectPwustSalesOrder(String salesOrder, String superNet,String subNet){

		Map map = null;
		
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
		

		List list = jdbcDaoSupport.queryForList(selectSql.toString(),parameters);
		if (list!=null && !list.isEmpty()){
			map =(Map)list.iterator().next();
		}
		return map;
	
	}
	
	public int updateOperConfNumberByOperKey(String orderId,Number operKey,Number stepKey, String confirmNo){

		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFWID_OPER_DESC  T " +
				                                           " SET T.UCF_ORDER_OPER_VCH1 = ? " + 
														   " WHERE T.ORDER_ID = ?"+
														   " AND T.OPER_KEY = ? " +
														   " AND T.STEP_KEY = ? " );
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(confirmNo);
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		
		int numbOfRows = jdbcDaoSupport.update(updateSql.toString(), parameters);
		return numbOfRows;
	}

	
	public int updateOperConfNumberByOperNo(String orderId,String  operNumber,Number stepKey, String confirmNo){

		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFWID_OPER_DESC  T " +
				                                           " SET T.UCF_ORDER_OPER_VCH1 = ? " + 
														   " WHERE T.ORDER_ID = ?"+
														   " AND T.OPER_NO = ? " +
														   " AND T.STEP_KEY = ? " );
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(confirmNo);
		parameters.addParameter(orderId);
		parameters.addParameter(operNumber);
		parameters.addParameter(stepKey);
		
		int numbOfRows = jdbcDaoSupport.update(updateSql.toString(), parameters);
		return numbOfRows;
	}

	//Defect 805
	public int updateOperSuperNetSubNetByOperNo(String orderId,String  operNumber,Number stepKey, String superNet, String subNet){

		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFWID_OPER_DESC  T " +
											               " SET T.UCF_ORDER_OPER_VCH2 = ?, " + 
											               "     T.UCF_ORDER_OPER_VCH3 = ? " + 
														   " WHERE T.ORDER_ID = ?"+
														   " AND T.OPER_NO = ? " +
														   " AND T.STEP_KEY = ? " );
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(superNet);
		parameters.addParameter(subNet);
		parameters.addParameter(orderId);
		parameters.addParameter(operNumber);
		parameters.addParameter(stepKey);
		
		int numbOfRows = jdbcDaoSupport.update(updateSql.toString(), parameters);
		return numbOfRows;
	}

	// Begin defect 779
	public int updateAltCount(String orderId, int altCount){

		StringBuffer updateSql = new StringBuffer().append(" UPDATE PWUST_SFWID_ORDER_DESC  T " +
											               " SET T.PW_ALT_COUNT = ? " + 
														   " WHERE T.ORDER_ID = ?");
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(altCount + 1);
		parameters.addParameter(orderId);
		
		int numbOfRows = jdbcDaoSupport.update(updateSql.toString(), parameters);
		return numbOfRows;
	}
	// End defect 779
	
	
	//Defect 699.  Get the System Order no (not PW order No
	public String selectSystemOrderNo(String pwOrderNo){
		
		String orderNo = "";
		StringBuffer select = new StringBuffer().append(" select t2.order_no from pwust_sfwid_order_desc t, sfwid_order_desc t2 ")
												.append(" where t.order_id = t2.order_id ")
												.append(" and t.pw_order_no = ? ");
				
		ParameterHolder params = new ParameterHolder();
		params.addParameter(pwOrderNo);
		
		try {
			orderNo = jdbcDaoSupport.queryForString(select.toString(),params);
		} catch (Exception e) {
			orderNo = pwOrderNo;



		}
		return orderNo;
	}
	
	// Defect 650
	public List selectOrderNoByOutgoingSalesOrder(String outgoingSalesOrder)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT PW_ORDER_NO as ORDER_NO")
												   .append("  FROM PWUST_SFWID_ORDER_DESC T ")
												   .append(" WHERE T.SALES_ORDER = ? ")
												   .append("ORDER BY PW_ORDER_NO ASC");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(outgoingSalesOrder);

		List orderIdList = null;

		try
		{
			orderIdList = jdbcDaoSupport.queryForList(selectSql.toString(), parameters);
		}
		catch (Exception e)
		{
			orderIdList = null;
		}

		return orderIdList;
	}	

	public List getHeader(String orderNoList,
						  String salesOrder,
			              String coverSheet,
			              String tag)
	{
		orderNoList = "'" + StringUtils.replace(orderNoList, ";", "','") + "'";
//dEFECT 849
		
		System.out.println("SO:"+salesOrder + " Tag:"+tag);
		
		StringBuffer selectSql = new StringBuffer().append("select NVL(PLAN_NO, 'N/A') AS PLAN_NO,")
				                                   .append("       ORDER_ID,")
				                                   .append("       PART_NO,")
				                                   .append("       ORDER_NO AS PW_ORDER_NO,")
				                                   .append("       CUSTOMER,")
				                                   .append("       CUSTOMER_DESC,")
				                                   .append("       ENGINE_MODEL,")
				                                   .append("       SAP_SERIAL_NO,")
				                                   .append("       SALES_ORDER,")
				                                   .append("	   NVL(PLAN_ID,'N/A') AS PLAN_ID,")
				                                   .append("       NVL(PLAN_REVISION,0) AS PLAN_REVISION, ")
				                                   .append("       NVL(PLAN_UPDT_NO,0) AS PLAN_UPDT_NO, ")
				                                   .append("       ORDER_STATUS,")
				                                   .append("       ASGND_WORK_LOC,")
				                                   .append("       UNIT_NO,")
				                                   .append("       CUSTOMER_ORDER_NO,")
				                                   .append("       PLAN_TYPE,")
				                                   .append("       SERIAL_FLAG,")
				                                   .append("       LOT_FLAG,")
				                                   .append("       PART_NO,")
				                                   .append("       PART_CHG,")
				                                   .append("       ITEM_TYPE,")
				                                   .append("       ITEM_SUBTYPE,")
				                                   .append("       PROGRAM,")
				                                   .append("       COMMODITY_JURISDICTION,")  // defect 1035
				                                   .append("       COMMODITY_CLASSIFICATION, ") // defect 1035
				                                   .append("       NVL(PLAN_TITLE,'N/A') AS PLAN_TITLE,")
				                                   .append("       ORDER_UOM,")
				                                   .append("       UNIT_TYPE,")
				                                   .append("       PROJECT,")
				                                   .append("       ORDER_QTY,")
				                                   .append("       LTA_SEND_FLAG,")
				                                   .append("       ORDER_TYPE,")
				                                   .append("       SFWID_ORDER_CONTROL(ORDER_ID) AS ORDER_CONTROL_TYPE, ")
				                                   .append("       OPERATION_OVERLAP_FLAG,")
				                                   .append("       DECODE(?, '1', SALES_ORDER, '2', SALES_ORDER, 'Delivery Package') as CS_LINE_1,")
				                                   .append("       DECODE(?, '1', PROGRAM, '2', PROGRAM, SALES_ORDER) as CS_LINE_2,")
				                                   .append("       DECODE(?, '1', PART_NO, '2', '', PROGRAM) as CS_LINE_3,")
				                                   .append("       NVL(SECURITY_GROUP, ' ') as SECURITY_GROUP, ")
				                                   .append("       SAP_LOT_CODE AS LOT_CODE ") //DEFECT 1984
				                                   .append(" FROM PWUST_ORDERHEADERTABSEL_V   ") ;

		if("1".equals(tag) || "2".equals(tag))
		{
			selectSql.append("WHERE ORDER_NO IN  ("+orderNoList+")")
				 	 .append("ORDER BY ORDER_NO ASC"); 
		}
		else if("3".equals(tag) || "4".equals(tag))
		{
			selectSql.append("WHERE SALES_ORDER = '"+salesOrder+"' ")
				 	 .append("ORDER BY ORDER_NO ASC"); 
		}
		
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(coverSheet);
		parameters.addParameter(coverSheet);
		parameters.addParameter(coverSheet);

		List orderIdList;

		try
		{
			orderIdList = jdbcDaoSupport.queryForList(selectSql.toString(), parameters);
		}
		catch(Exception e)
		{
			orderIdList = null;
		}

		return orderIdList;
	}
}
