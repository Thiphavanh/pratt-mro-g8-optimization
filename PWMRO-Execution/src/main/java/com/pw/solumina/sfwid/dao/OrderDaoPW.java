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
 *  File:    OrderDaoPW.java
 * 
 *  Created: 2018-03-20
 * 
 *  Author:  Thien-Long Phan
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2018-03-22		T. Phan		    Initial Release SMRO_TR_T203 - Labor Interface
 *  2018-04-30      D. Miron        SMRO_TR_T203  Defect 733 Correct select of part_no in selectOrderInfo.
 *  2018-05-23      D. Miron        SMRO_TR_t203 Defect 802 - Updated selectOrderInfo for ASGND_LOCATION_ID.
 *  2018-10-11		Bob Preston		SoluminaOOB, defect 970 -- Added getTimeZone().
 *  2019-08-19		T. Phan		    SMRO_eWORK_202 Defect 1364 - Added addConfNumber
 *  2019-08-20      D. Miron        SMRO_WOE_304 Sub Orders - Added methods selectSerialNoQty and selectSerialNumberListAgg.
 *  2019-09-19		Fred Ettefagh   removed code check in by T. Phan for defect 1364
 *  2019-09-20      Bahram J        SMRO_TR_T203, defect 1366, fixed sql for labor interface to get confirrmation number from correct UCF
 *  2020-01-14      John DeNinno	defect 953      update UCF with released by
 *  2021-03-15      Brendan Polak   1848, added serial_no to selectOperDatColInfo
 *  2021-03-22      Brendan Polak   1848, reverted selectOperDatColInfo to return dcvalues and updateuser if dc is complete 
 *  2021-03-25      Brendan Polak   1848, corrected issue with updateuser in selectOperDatColInfo
 *  2021-04-29		Brendan Polak   1897, selectOperDatColInfo, return dcvalues and updateuser if dc is complete OR skipped
 *  2021-05-13		Brendan Polak   1897, selectOperDatColInfo, fixed typo in sql query   
 *  2021-07-27      John DeNinno    defect 1940 check for existence of released date
 */
package com.pw.solumina.sfwid.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;

public class OrderDaoPW
{
	//Begin SMRO_TR_T203 - OH Labor Interface
	@Reference private static ExtensionDaoSupport eds;

	public Map selectOrderInfo(String orderId)
	{
		StringBuilder selectSql = new StringBuilder().append("SELECT ")
				.append("  A.ORDER_NO, ")
                .append("  A.UCF_ORDER_VCH12 as CONFIRMATION_NO, ")    // Defect 1366
				.append("  A.PART_NO, ")                               // Defect 733
				.append("  A.SECURITY_GROUP, ")
				.append("  C.SERIAL_NO, ")                                          
				.append("  C.SERIAL_ID, ")
				.append("  A.UCF_ORDER_VCH2 AS SALES_ORDER_NO,  ")
				.append("  (SELECT T.WORK_LOC FROM SFFND_WORK_LOC_DEF T WHERE T.LOCATION_ID = A.ASGND_LOCATION_ID) AS ASGND_WORK_LOC, ")                        // Defect 802
				.append("  A.ucf_plan_vch5 as afs_checkoff, ")
				.append("  A.ucf_order_vch4 as sap_order ")
				.append("FROM ")
				.append("   SFWID_ORDER_DESC A, ")
				.append("   SFWID_SERIAL_DESC C ")
				.append("WHERE ")
				.append("   A.ORDER_ID = C.ORDER_ID")
                        .append("   AND A.ORDER_ID = ? ")
                        .append("   AND ROWNUM = 1 ");    ////  RTTTT 1258 added for serial numbers.  labor is by Operation not Serial number per Kathryn

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);

		Map orderMap = eds.queryForMap(selectSql.toString(), parameters);

		return orderMap;
	}


	public void insertT203Labor(String orderId, 
			Number operKey, 
			String serialId, 
			String lotId, 
			String action,
			String msgText,
			String workLoc, 
			String comments)
	{
		StringBuffer insert = new StringBuffer()
				.append("INSERT INTO PWUE_XML_WID_ORDER_EXEC_REQS  ")
				.append("  (ORDER_ID, OPER_KEY, SERIAL_ID, LOT_ID, ACTION, MSG_TXT, MOD_CENTER, COMMENTS, TIME_STAMP) ")
				.append("VALUES ")
				.append("  (?, ?, ?, ?, ?, ?, ?, ?, ")
				.append(eds.getTimestampFunction() + ") ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(operKey);
		params.addParameter(serialId);
		params.addParameter(lotId);
		params.addParameter(action);
		params.addParameter(msgText);
		params.addParameter(workLoc);
		params.addParameter(comments);

		eds.insert(insert.toString(), params);

		return;
	}
	//End SMRO_TR_T203 - OH Labor Interface

	// Begin defect 970
	public String getTimeZone(String locationId)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT W.UCF_WORK_LOC_VCH4 ")
				.append("  FROM SFFND_WORK_LOC_DEF W ")
				.append(" WHERE W.LOCATION_ID = ? ");

		ParameterHolder param = new ParameterHolder();
		param.addParameter(locationId);

		String timeZone = eds.queryForString(selectSql.toString(), param);
		return timeZone;              
	}
	// End defect 970

	
	// SMRO_WOE_304
	public int selectSerialNoQty(String orderId)	
	{
		int qty = 0;
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT COUNT(*) ")
				                                   .append("  FROM SFWID_SERIAL_DESC A, SFWID_ORDER_DESC B ")		
				                                   .append(" WHERE A.ORDER_ID = B.ORDER_ID ")
				                                   .append("   AND A.SERIAL_NO <> 'N/A' ")
				                                   .append("   AND A.ORDER_ID = ?  ");
		
		ParameterHolder params = new ParameterHolder();	
		params.addParameter(orderId);
		
    	try
    	{
    		qty = eds.queryForInt(selectSql.toString(), params);
    	}
    	catch(Exception e){
    		qty = 0;
    	}

    	return qty;	
	}
	
	// SMRO_WOE_304
	public String selectSerialNumberListAgg(String orderId)
	{
		Map returnMap = new HashMap();
		String serialNoListAgg = null;
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT A.SERIAL_NO, ")
				                                   .append("        B.SECURITY_GROUP ")
				                                   .append("   FROM SFWID_SERIAL_DESC A, SFWID_ORDER_DESC B ")
				                                   .append("  WHERE A.ORDER_ID = ? ")
				                                   .append("    AND A.ORDER_ID = B.ORDER_ID ")
				                                   .append("    AND A.SERIAL_NO <> 'N/A' ")
				                                   .append("    AND A.SERIAL_STATUS IN ('IN QUEUE','IN PROCESS','COMPLETE') ")
				                                   .append(" ORDER BY A.SERIAL_NO ");

		ParameterHolder param = new ParameterHolder();
		param.addParameter(orderId);

		List serialNoList = eds.queryForList(selectSql.toString(), param);
		if(serialNoList.size() > 0)
		{
			Map map = (Map) serialNoList.get(0);
			serialNoListAgg = (String) map.get("SERIAL_NO");
			if (serialNoList.size() > 1)
			{
				for (int i=1; i<serialNoList.size(); i++)
				{
					map = (Map) serialNoList.get(i);
					serialNoListAgg = serialNoListAgg + ";" + (String) map.get("SERIAL_NO"); 
				}
			}
		}

		return serialNoListAgg;              
	}
	
	//defect 1940
	public boolean ReleasedByDateExists(String orderId)
	{
		boolean alreadyReleased = false;
		

		StringBuffer selectSQL = new StringBuffer().append(" SELECT COUNT(*) ")
				.append(" FROM PWUST_SFWID_ORDER_DESC ")
				.append(" WHERE SALES_ORDER_RELEASE_DATE IS NOT NULL ")
				.append(" and ORDER_ID = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);

		int count = eds.queryForInt(selectSQL.toString(), params);
		if  (count > 0) {
			alreadyReleased = true;
		}
		
		return alreadyReleased;
	}
	
	//defect 953
	public int updateReleasedBy(String orderId)
	{
		StringBuffer updateSQL = new StringBuffer().append("UPDATE PWUST_SFWID_ORDER_DESC ")
				.append("   SET SALES_ORDER_RELEASED_BY = ? , SALES_ORDER_RELEASE_DATE = SYSDATE  ")
				.append(" WHERE ORDER_ID = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(ContextUtil.getUsername()); 
		params.addParameter(orderId);

		return eds.update(updateSQL.toString(), params);
	}
	
	
	@SuppressWarnings("rawtypes")
	public List selectOperDatColInfo(String orderControlType,
				 String orderId,
				 Number operKey,
				 Number stepKey,
				 String referenceId)
	{
		StringBuilder selectSql = new StringBuilder().append(" SELECT DISTINCT")
				 .append("	A.ORDER_ID,") 
				 .append("	A.OPER_NO,") 
				 .append("    A.STEP_NO,") 
				 .append("    A.STD_DATCOL_ID,")    
				 .append("    A.OPER_KEY,") 
				 .append("    A.STEP_KEY,") 
				 .append("    D.DAT_COL_TYPE,") 
				 .append("    A.DAT_COL_CERT,") 
				 .append("    A.DAT_COL_UOM,") 
				 .append("    A.DAT_COL_TITLE,") 
				 .append("    A.UPPER_LIMIT,") 
				 .append("    A.LOWER_LIMIT,") 
				 .append("    A.TARGET_VALUE,") 
				 .append("    A.ALT_ID,") 
				 .append("    A.ALT_COUNT,") 
				 .append("    A.REF_ID,") 
				 .append("    A.DISPLAY_LINE_NO,") 
				 .append("    'Data Collection Centric' AS ORIENTATION_FLAG,") 
				 .append("    A.CROSS_ORDER_FLAG,") 
				 .append("    OPTIONAL_FLAG,") 
				 .append("    B.DCVALUE  AS VALUE,") 
				 .append("    B.COMMENTS AS COMMENTS,") 
				 .append("    null as SERIAL_NO,") 
				 .append("    NULL AS UDV_GRID_TERMINATOR,") 
				 .append("    CASE B.DAT_COL_STATUS WHEN 'PENDING' THEN NULL ELSE B.UPDT_USERID END AS UPDT_USERID, ") 
				 .append("    CASE B.DAT_COL_STATUS WHEN 'PENDING' THEN NULL ELSE B.TIME_STAMP END AS TIME_STAMP ")  
				 .append("  FROM  SFMFG.SFWID_OPER_DAT_COL A") 
				 .append("    LEFT OUTER JOIN SFMFG.SFWID_SERIAL_OPER_DAT_COL B") 
				 .append("        ON A.ORDER_ID = B.ORDER_ID") 
				 .append("        AND A.STEP_KEY = B.STEP_KEY") 
				 .append("        AND A.DAT_COL_ID = B.DAT_COL_ID") 
				 .append("        AND A.OPER_KEY = B.OPER_KEY ") 
				 .append("        AND B.DCVALUE is not null  ") 
				 .append("    LEFT OUTER JOIN sfmfg.SFFND_STD_DATCOL_TYPE_DEF D ") 
				 .append("        on A.STD_DATCOL_ID = D.STD_DATCOL_ID ") 
				 .append("    where A.ORIENTATION_FLAG = 'D'      ") 
				 .append("        AND A.ORDER_ID = ? ") 
				 .append("        AND A.OPER_KEY = ? ") 
				 .append("        AND A.STEP_KEY =  ? ") 
				 .append("        AND A.REF_ID =  ?  ") 
				 .append("UNION SELECT distinct") 
				 .append("    A.ORDER_ID,") 
				 .append("    A.OPER_NO,") 
				 .append("    A.STEP_NO,") 
				 .append("    A.STD_DATCOL_ID,    ") 
				 .append("    A.OPER_KEY,") 
				 .append("    A.STEP_KEY,") 
				 .append("    D.DAT_COL_TYPE,") 
				 .append("    A.DAT_COL_CERT,") 
				 .append("    A.DAT_COL_UOM,") 
				 .append("    A.DAT_COL_TITLE,") 
				 .append("    A.UPPER_LIMIT,") 
				 .append("    A.LOWER_LIMIT,") 
				 .append("    A.TARGET_VALUE,") 
				 .append("    A.ALT_ID,") 
				 .append("    A.ALT_COUNT,") 
				 .append("    A.REF_ID,") 
				 .append("    A.DISPLAY_LINE_NO,") 
				 .append("    'Unit Centric' AS ORIENTATION_FLAG,") 
				 .append("    A.CROSS_ORDER_FLAG,") 
				 .append("    OPTIONAL_FLAG,") 
				 .append("    B.DCVALUE  AS VALUE,") 
				 .append("    B.COMMENTS AS COMMENTS,") 
				 .append("    E.SERIAL_NO,") 
				 .append("    NULL as UDV_GRID_TERMINATOR,") 
				 .append("    CASE B.DAT_COL_STATUS WHEN 'PENDING' THEN NULL ELSE B.UPDT_USERID END AS UPDT_USERID, ") 
				 .append("    CASE B.DAT_COL_STATUS WHEN 'PENDING' THEN NULL ELSE B.TIME_STAMP END AS TIME_STAMP ")  
				 .append("	FROM  SFMFG.SFWID_OPER_DAT_COL A") 
				 .append("    LEFT OUTER JOIN SFMFG.SFWID_SERIAL_OPER_DAT_COL B") 
				 .append("        ON A.ORDER_ID = B.ORDER_ID") 
				 .append("        AND A.STEP_KEY = B.STEP_KEY") 
				 .append("        AND A.DAT_COL_ID = B.DAT_COL_ID") 
				 .append("        AND A.OPER_KEY = B.OPER_KEY ") 
				 .append("    LEFT OUTER JOIN sfmfg.SFFND_STD_DATCOL_TYPE_DEF D ") 
				 .append("        on A.STD_DATCOL_ID = D.STD_DATCOL_ID ") 
				 .append("    LEFT JOIN sfmfg.SFWID_SERIAL_DESC E ") 
				 .append("        on e.serial_id = B.serial_id") 
				 .append("        and e.lot_id = b.lot_id ") 
				 .append("        and e.order_id = b.order_id") 
				 .append("    where ORIENTATION_FLAG = 'U'   ") 
				 .append("        AND A.ORDER_ID =  ? ") 
				 .append("        AND A.OPER_KEY =  ? ") 
				 .append("        AND A.STEP_KEY =  ? ") 
				 .append("        AND A.REF_ID =  ? ")  
				 .append("ORDER  BY DISPLAY_LINE_NO, SERIAL_NO ")  ;


		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(referenceId);
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(referenceId);

		List returnList = eds.queryForList(selectSql.toString(), parameters);
		
		return returnList;
	}
	
	
	
	public List selectOrderBuyoff( String orderId,
			 Number operKey,
			 Number stepKey,
			 String referenceId)
	{
		StringBuilder selectSql = new StringBuilder()
				.append(" select D.oper_no,D.step_no,D.buyoff_type,D.buyoff_cert,D.buyoff_id, D.alt_id, D.alt_count,   ")
				.append("        COALESCE((SELECT FULL_NAME FROM SFMFG.SFFND_USER WHERE USERID = D.updt_userid), D.updt_userid) AS updt_userid,   ")
				.append("        D.time_stamp,   ")
				.append("        D.oper_key,D.step_key,   ")
				.append("        D.optional_flag, D.cross_order_flag, D.ref_id, D.buyoff_title, D.serial_no from   ")
				.append(" (    ")
				.append("  select a.oper_no,a.step_no,a.display_line_no,a.buyoff_type,a.buyoff_cert,a.buyoff_id, a.alt_id, a.alt_count,   ")
				.append("        SFMFG.SFWID_BUYOFF_USER_SEL(a.ORDER_ID,a.OPER_KEY,a.STEP_KEY,a.BUYOFF_ID,'UPDT_USERID') as updt_userid,   ")
				.append("        SFMFG.SFWID_BUYOFF_USER_SEL(a.ORDER_ID,a.OPER_KEY,a.STEP_KEY,a.BUYOFF_ID,'TIME_STAMP') as time_stamp,   ")
				.append("        a.oper_key,a.step_key,   ")
				.append("        a.optional_flag, a.cross_order_flag, a.ref_id, a.buyoff_title,   ")
				.append("        b.serial_id, c.serial_no   ")
				.append("   from sfwid_oper_buyoff a    ")
				.append("    left outer join sfmfg.SFWID_SERIAL_OPER_BUYOFF b   ")
				.append("    on a.order_id = b.order_id   ")
				.append("    and a.buyoff_id = b.buyoff_id   ")
				.append("    and a.oper_key = b.oper_key   ")
				.append("    and a.step_key = b.step_key   ")
				.append("    left outer join sfmfg.sfwid_serial_desc c   ")
				.append("    on b.serial_id = c.serial_id   ")
				.append("    and b.order_id = c.order_id   ")
				.append("    and b.lot_id = c.lot_id   ")
				.append("    where a.order_id = ?   ")
				.append("      and a.oper_key = ?   ")
				.append("     and a.step_key = ? ")
				.append("      and a.ref_id   = ?    ")
				.append(" ) D   ")
				.append(" order by D.oper_no,D.step_no, D.display_line_no, case D.buyoff_type  when 'MFG' then 1   ")
				.append("                     when 'TECH' then 1   ")
				.append("                     when 'MFG2' then 2   ")
				.append("                     when 'TECH2' then 2   ")
				.append("                     when 'QA' then 3   ")
				.append("                     when 'INSP' then 3   ")
				.append("                     when 'CUST' then 4  ")
				.append("                     else 5 end, D.buyoff_id  ");
		
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(orderId);
		parameters.addParameter(operKey);
		parameters.addParameter(stepKey);
		parameters.addParameter(referenceId);

		return eds.queryForList(selectSql.toString(), parameters);
					
	}

}