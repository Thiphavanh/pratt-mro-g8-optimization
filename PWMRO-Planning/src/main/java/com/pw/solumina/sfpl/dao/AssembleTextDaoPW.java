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
 *  File:    AssembleTextDaoPW.java
 * 
 *  Created: 2017-08-29
 * 
 *  Author:  Rusty Cannon
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2017-08-29		R. Cannon		Initial Release SMRO_EXPT_205
 */

package com.pw.solumina.sfpl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.Reference;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;

public class AssembleTextDaoPW
{
	@Reference private JdbcDaoSupport eds;
	
	public boolean isPlanClassified(String planId,
									Number planVersion,
									Number planRevision,
									Number planAlterations)
	{
		boolean planClassified = false;
		String jurisdiction;

		StringBuffer selectSql = new StringBuffer().append(" SELECT P.COMMODITY_JURISDICTION ")
				   								   .append("   FROM SFMFG.SFPL_PLAN_DESC P, SFMFG.SFPL_PLAN_REV R ")
				   								   .append("  WHERE P.PLAN_ID = R.PLAN_ID ")
				   								   .append("    AND P.PLAN_UPDT_NO = R.PLAN_UPDT_NO ")
				   								   .append("    AND R.PLAN_ID = ? ")
				   								   .append("    AND R.PLAN_VERSION = ? ")
				   								   .append("    AND R.PLAN_REVISION = ? ")
				   								   .append("    AND R.PLAN_ALTERATIONS = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planVersion);
		params.addParameter(planRevision);
		params.addParameter(planAlterations);

		jurisdiction = eds.queryForString(selectSql.toString(), params);
		
		if(!StringUtils.isEmpty(jurisdiction) || jurisdiction != null)
		{
			//Non-null COMMODITY_JURISDICTION means plan is classified
			planClassified = true;
		}
		
		return planClassified;
	}
	
	@SuppressWarnings("rawtypes")
	public Map selectExportControlInfoForPlan(String planId,
			                                  Number planVersion,
			                                  Number planRevision,
			                                  Number planAlterations)
    {
		StringBuffer selectSql = new StringBuffer().append(" SELECT P.PLAN_NO, ")
												   .append("  	    P.COMMODITY_JURISDICTION, ")
												   .append("	    P.COMMODITY_CLASSIFICATION, ")
												   .append("	    NVL(P.UCF_PLAN_FLAG1, 'N') AS UCF_PLAN_FLAG1, ")
												   .append(" 	    P.PLND_LOCATION_ID, ")
												   .append("	    W.WORK_LOC, ")
												   .append("        P.UCF_PLAN_VCH10 AS NON_US_JURISDICTION, ")
												   .append("        P.UCF_PLAN_VCH11 AS NON_US_CLASSIFICATION, ")
												   .append("        P.UCF_PLAN_VCH12 AS LICENSE, ")
												   .append("	    P.SECURITY_GROUP ")
												   .append("   FROM SFMFG.SFPL_PLAN_DESC P, SFMFG.SFPL_PLAN_REV R, SFMFG.SFFND_WORK_LOC_DEF W ")
												   .append("  WHERE P.PLAN_ID = R.PLAN_ID ")
												   .append("    AND P.PLAN_UPDT_NO = R.PLAN_UPDT_NO ")
												   .append("    AND P.PLND_LOCATION_ID = W.LOCATION_ID(+) ")
												   .append("    AND R.PLAN_ID = ? ")
												   .append("    AND R.PLAN_VERSION = ? ")
												   .append("    AND R.PLAN_REVISION = ? ")
												   .append("    AND R.PLAN_ALTERATIONS = ? ");

		ParameterHolder params = new ParameterHolder();
	    params.addParameter(planId);
	    params.addParameter(planVersion);
	    params.addParameter(planRevision);
	    params.addParameter(planAlterations); 
	
	    Map exportMap = eds.queryForMap(selectSql.toString(), params);
	    
		return exportMap;
    }

	/* ****************************************************************************
	 *  Log users' access to classified plans/orders. For the same user accessing
	 *  the same plan, log duplicate entries no sooner than 1 hour since the most
	 *  recent visit.
	 * ****************************************************************************/
	@SuppressWarnings("rawtypes")
	public void insertExportAuditLog(String application,
									 String busUnit,
									 String jurisdiction,
									 String classification,
									 String sme,
									 String authLicense,
									 String docId)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT CASE ")
												   .append(" 		  WHEN 24 * (SYSDATE - TIME_STAMP) > 1 THEN ")
												   .append(" 		   '1' ")
												   .append(" 		  ELSE ")
												   .append(" 		   '0' ")
												   .append(" 		END AS CHK ")
												   .append("   FROM (SELECT TIME_STAMP ")
												   .append(" 		   FROM PWUST_EXPORT_AUDIT_LOG ")
												   .append(" 		  WHERE DOCUMENTID = ? ")
												   .append("			AND USERID = ? ")
												   .append("		  ORDER BY TIME_STAMP DESC) ")
												   .append("  WHERE ROWNUM = 1 ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(docId);
		params.addParameter(ContextUtil.getUsername());

		List retList = eds.queryForList(selectSql.toString(), params);
		Boolean doInsert = false;
		
		if(retList.size() > 0)
		{
			String chk = (String)((Map)retList.get(0)).get("CHK");
			
			if("1".equals(chk))
				doInsert = true;
		}
		else
		{
			doInsert = true;
		}

		if(doInsert)
		{
			StringBuffer insertSql = new StringBuffer().append(" INSERT INTO PWUST_EXPORT_AUDIT_LOG ")
													   .append("   (TIME_STAMP, ")
													   .append("    USERID, ")
													   .append("    APPLICATION, ")
													   .append("    BUSINESS_UNIT, ")
													   .append("    JURISDICTION, ")
													   .append("    CLASSIFICATION, ")
													   .append("    SME, ")
													   .append("    AUTH_LICENSE, ")
													   .append("    DOCUMENTID) ")
													   .append(" VALUES ")
													   .append("   (SYSDATE, ?, ?, ?, ?, ?, ?, ?, ?) ");

			params = new ParameterHolder();
			params.addParameter(ContextUtil.getUsername());
			params.addParameter(application);
			params.addParameter(busUnit);
			params.addParameter(jurisdiction);
			params.addParameter(classification);
			params.addParameter(sme);
			params.addParameter(authLicense);
			params.addParameter(docId);

			eds.insert(insertSql.toString(), params);	
		}
		
	} //end insertExportAuditLog()
	
} //end AssembleTextDaoPW Class
