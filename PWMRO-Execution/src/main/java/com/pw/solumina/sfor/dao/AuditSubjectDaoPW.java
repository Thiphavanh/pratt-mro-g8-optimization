/*
 *  This unpublished work, first created in 2018 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2018, 2019.  All rights reserved.  Information contained 
 *  herein is proprietary and confidential and improper use or disclosure 
 *  may result in civil and penal sanctions.
 *  Corporation 2018, 2019.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    AuditSubjectDaoPW.java
 * 
 *  Created: 2018-05-02
 * 
 *  Author:  Bob Preston
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *	2018-05-02		B. Preston		Initial Release, SMRO_WOE_211, Defect 51.
 *  2018-08-09      R. Thorpe       Defect 850 - added method checkForPartialBuyoffsOnOperation 
 *  2019-07-31      J. DeNinno      WE308 - add code to insert multiple multiple task history
 *  2019-09-10      Fred Ettefagh   DER SMRO_WOE_211, Defect 1363 - moved code from R2 to R2SP4 to log task include/exclude in sfwid_order_taskgroup_hist table
 *  2019-11-26      B. Corson       Defect 1423 - Fix Java error in insertIncludeExcludeMultiWidSubject with multiple serial numbers.
 *  2019-09-16      T. Phan         Defect 979 - Checks for other non-accepted statuses so the operation cannot be Excluded
 *  2020-02-06      B. Corson       Defect 979 - Fix Check for statuses.
 *
 */
package com.pw.solumina.sfor.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Begin Defect 1423
import static com.ibaset.common.FrameworkConstants.*;
import static com.ibaset.common.SoluminaConstants.*;
// End Defect 1423

import org.apache.commons.lang3.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.solumina.sfor.application.IParse;      // Defect 1423

public class AuditSubjectDaoPW {

	@Reference
	private ExtensionDaoSupport eds = null;
    @Reference                              // Defect 1423
	private IParse parse = null;            // Defect 1423
	
	 /* WE308 7/31/19 JPD Add code to insert history*/
	public void insertIncludeExcludeMultiWidSubject(String orderId, String subjectNumber, String includeExclude,
				String notes, String discrepancyId, Number discrepancyLineNo) {

			ParameterHolder params1 = new ParameterHolder();
            
            // Begin Defect 1423
            // Gets the Subject Number in an array.
            String[] subjectNbr = parse.parse(subjectNumber, COMMA, YES);

			StringBuffer select = new StringBuffer().append(" SELECT b.oper_no" +
					" FROM sfmfg.sfor_sfwid_oper_subject a, sfmfg.sfwid_oper_desc b "+
					" WHERE a.order_id = '" + orderId + "'");
            
            if (subjectNbr.length > 1) {
                select.append(" AND a.subject_no IN (");
                for (int i = 0; i < subjectNbr.length; i++) {
                    if (i > 0)
                        select.append(",");
                    select.append(subjectNbr[i]);
                }
                select.append(")");
            } else {
                select.append(" AND a.subject_no = " + subjectNbr[0]);
            }

            select.append(" AND a.order_id = b.order_id " +
                          " AND a.oper_key = b.oper_key " +
                          " AND b.step_key = -1 " +
                          " ORDER BY oper_no");
            // End Defect 1423

			List operationList = eds.queryForList(select.toString(), params1);


			String operations = "";
			Iterator operationIterator = operationList.iterator();
			if (operationIterator.hasNext())
			{

				while (operationIterator.hasNext()) {
					Map row = (Map)operationIterator.next();
					operations = StringUtils.defaultIfEmpty((String)row.get("OPER_NO"),StringUtils.EMPTY);
                    // Begin Defect 1423
                    for (int i = 0; i < subjectNbr.length; i++) {
                        StringBuffer insert = new StringBuffer().append("INSERT INTO sfwid_order_taskgroup_hist ")
                            .append(" (HIST_ID, ORDER_ID, TYPE_FLAG, TITLE, TASK_NO, OPER_NO, ACTION, UPDT_USERID, TIME_STAMP) ")
                            .append(" (SELECT SFDB_GUID, ?, 'T', ")
                            .append("   (SELECT TITLE FROM SFOR_SFWID_ORDER_SUBJECT WHERE ORDER_ID = ? AND SUBJECT_NO = ? AND SUBJECT_REV = 1), ?, ?, ?, ?, ")
                            .append(eds.getTimestampFunction()).append(" FROM DUAL) ");
                        
                        ParameterHolder params = new ParameterHolder();
                        params.addParameter(orderId);
                        params.addParameter(orderId);
                        params.addParameter(new Double(subjectNbr[i]));
                        params.addParameter(new Double(subjectNbr[i]));
                        params.addParameter(operations);
                        params.addParameter(includeExclude);
                        params.addParameter(ContextUtil.getUsername());
                        
                        eds.insert(insert.toString(), params);
                    }
                    // End Defect 1423
				}
			}
	}


	public void insertIncludeExcludeWidSubject(String orderId, Number subjectNumber, String includeExclude,
			String notes, String discrepancyId, Number discrepancyLineNo) {
		StringBuffer insert = new StringBuffer().append("INSERT INTO sfwid_order_taskgroup_hist ")
				.append(" (HIST_ID, ORDER_ID, TYPE_FLAG, TITLE, TASK_NO, OPER_NO, ACTION, UPDT_USERID, TIME_STAMP) ")
				.append(" (SELECT SFDB_GUID, ?, 'T', ")
				.append("   (SELECT TITLE FROM SFOR_SFWID_ORDER_SUBJECT WHERE ORDER_ID = ? AND SUBJECT_NO = ? AND SUBJECT_REV = 1), ?, 0, ?, ?, ")
				.append(eds.getTimestampFunction()).append(" FROM DUAL) ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(orderId);
		params.addParameter(subjectNumber);
		params.addParameter(subjectNumber);
		params.addParameter(includeExclude);
		params.addParameter(ContextUtil.getUsername());

		eds.insert(insert.toString(), params);
	}

	public void insertIncludeExcludeOperation(String orderId, Number operationKey, String includeExclude, String notes,
			String discrepancyId, Number discrepancyLineNumber, String stepKey) {

		StringBuffer insert = new StringBuffer().append("INSERT INTO sfwid_order_taskgroup_hist ")
				.append(" (HIST_ID, ORDER_ID, TYPE_FLAG, TITLE, TASK_NO, OPER_NO, ACTION, UPDT_USERID, TIME_STAMP) ")
				.append(" (SELECT SFDB_GUID, ?, 'O', ")
				.append("   (SELECT TITLE FROM SFWID_OPER_DESC WHERE ORDER_ID = ? AND OPER_KEY = ? AND STEP_KEY = ?), 0, ")
				.append("   (SELECT OPER_NO FROM SFWID_OPER_DESC WHERE ORDER_ID = ? AND OPER_KEY = ? AND STEP_KEY = ?), ?, ?, ")
				.append(eds.getTimestampFunction()).append(" FROM DUAL) ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(orderId);
		params.addParameter(operationKey);
		params.addParameter(stepKey);
		params.addParameter(orderId);
		params.addParameter(operationKey);
		params.addParameter(stepKey);
		params.addParameter(includeExclude);
		params.addParameter(ContextUtil.getUsername());

		eds.insert(insert.toString(), params);
	}
	
	//Defect 850
	public boolean checkForPartialBuyoffOnOperation(String orderId, Number operKey) 
	{
		boolean isPartial = false;
		
		StringBuffer selectSql = new StringBuffer().append("SELECT COUNT(*) ")
				                                   .append("  FROM SFWID_SERIAL_OPER_BUYOFF T ")
				                                   .append(" WHERE ORDER_ID = ?")
				                                   .append("   AND OPER_KEY = ?")
				                                   .append("   AND UCF_SRLOPBUYOFF_FLAG1 IS NOT NULL");


		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(operKey);

		int count = eds.queryForInt(selectSql.toString(), params);

		if (count > 0)
		{
			isPartial = true;
		}

		return isPartial;		
	}
	
	//Defect 850 / 979
		public boolean checkOpenOrders(String orderId, Number num) 
		{
			boolean isOpen = false;
			
			StringBuffer selectSql = new StringBuffer().append("SELECT COUNT(*) ")
					                                   .append("  FROM SFMFG.SFWID_SERIAL_OPER_BUYOFF A ")
					                                   .append(" INNER JOIN SFMFG.SFWID_SERIAL_OPER_BUYOFF_HIST H ")
					                                   .append("         ON A.ORDER_ID = H.ORDER_ID ")
					                                   .append("        AND A.OPER_KEY = H.OPER_KEY ")
					                                   .append(" WHERE A.ORDER_ID = ?")
					                                   .append("   AND A.OPER_KEY = ?")
							                           .append("   AND (NOT (A.BUYOFF_STATUS IN ('PENDING', 'OPEN') ")
							                           .append("         AND A.UCF_SRLOPBUYOFF_FLAG1 IS null ")
							                           .append("         AND A.UCF_SRLOPBUYOFF_VCH1 IS null) ")
							                           .append("        OR H.BUYOFF_STATUS = 'TRAINING') ");

			ParameterHolder params = new ParameterHolder();
			params.addParameter(orderId);
			params.addParameter(num);

			int count = eds.queryForInt(selectSql.toString(), params);

			if (count > 0)
			{
				isOpen = true;
			}

			return isOpen;		
		}
}
