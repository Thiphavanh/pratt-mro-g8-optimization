/*
* This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017, 2019.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    StepDaoImplPW.java
* 
*  Created: 2017-08-11
* 
*  Author:  c079222
* 
*  Revision History
* 
*  Date      	Who             Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-11-21    D.Miron		    SRMO_WOE_202    Defect 134 Initial Release
* 2018-04-18    D.Miron         SMRO_USR_205 Defect 144 - Renamed PWUST_GENERAL_LOOKUP to PWUE_GENERAL_LOOKUP
* 2018-11-08    R.Thorpe        SMRO_WOE_202 Defect 793 - remove union to SFFND_CERT_DEF in selectPlanBuyoffForResourcePalette
* 2019-01-14    B. Corson       SMRO_PLG_313 Add method selectPlanOperDC()
* 2019-06-18    R.Thorpe        R2 Upgrade - Added DISPLAY_LINE_NO to selectPlanBuyoffForResourcePalette
* 2019-05-22    B. Corson       SMRO_PLG_313 Add new fields UCF_STEP_DC_VCH255_1 (Engineer Remarks) and UCF_STEP_DC_FLAG2 (Serviceable Limit Used).
*/
package com.pw.solumina.sfpl.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.context.SoluminaContextHolder; 
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.solumina.sfpl.dao.IStepDao;

public abstract class StepDaoImplPW  extends ImplementationOf<IStepDao> implements IStepDao{
	
	@Reference
	private JdbcDaoSupport jdbcDao;

	public List<Map<String, Object>>  selectPlanBuyoffForResourcePalette(String whereClause)
	{
	    	
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT * FROM ( ")
	    											   .append(" SELECT ? AS BUYOFF_TYPE, ")
	    											   .append("       NULL AS BUYOFF_CERT, ")
	    											   .append("       DATA_DESC AS BUYOFF_TITLE, ")
	    											   .append("       NULL AS DESCRIPTION, ")
	    											   .append("       NULL AS PLAN_ID, ")
	    											   .append("       NULL AS OPER_KEY, ")
	    											   .append("       NULL AS STEP_KEY, ")
	    											   .append("       NULL AS STEP_UPDT_NO, ")
	    											   .append("       NULL AS BUYOFF_ID, ")
	    											   .append("       NULL AS UPDT_USERID, ")
	    											   .append("       NULL AS TIME_STAMP, ")
	    											   .append("       NULL AS LAST_ACTION, ")
	    											   .append("       NULL AS BUYOFF_STATUS, ")
	    											   .append("       ? AS CROSS_ORDER_FLAG, ")
	    											   .append("       NULL AS OPTIONAL_FLAG, ")
	    											   .append("       NULL AS REF_ID, ")
	    											   .append("       NULL AS BLOCK_ID, ")
	    											   .append("       NULL AS SUSPECT_FLAG, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH6, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH7, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH8, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH9, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH10, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH11, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH12, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH13, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH14, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH15, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH255_1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH255_2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH255_3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH4000_1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH4000_2, ")
	    											   .append("       NULL AS SLIDE_EMBEDDED_REF_ID, ")
	    											   .append("       NULL AS SLIDE_ID, ")
	    											   .append("       ? AS CALLED_FROM, ")  
	    											   .append("       NULL AS DISPLAY_LINE_NO ")
	    											   .append("  FROM PWUE_GENERAL_LOOKUP ")
	    				                               .append(" WHERE DATA_FIELD = 'BUYOFF' ")
	    				                               .append("   AND DATA_VALUE = ? ")
	    											   .append("UNION ALL ")
	    											   .append("SELECT ? AS BUYOFF_TYPE, ")
	    											   .append("       NULL AS BUYOFF_CERT, ")
	    											   .append("       DATA_DESC AS BUYOFF_TITLE, ")
	    											   .append("       NULL AS DESCRIPTION, ")
	    											   .append("       NULL AS PLAN_ID, ")
	    											   .append("       NULL AS OPER_KEY, ")
	    											   .append("       NULL AS STEP_KEY, ")
	    											   .append("       NULL AS STEP_UPDT_NO, ")
	    											   .append("       NULL AS BUYOFF_ID, ")
	    											   .append("       NULL AS UPDT_USERID, ")
	    											   .append("       NULL AS TIME_STAMP, ")
	    											   .append("       NULL AS LAST_ACTION, ")
	    											   .append("       NULL AS BUYOFF_STATUS, ")
	    											   .append("       ? AS CROSS_ORDER_FLAG, ")
	    											   .append("       NULL AS OPTIONAL_FLAG, ")
	    											   .append("       NULL AS REF_ID, ")
	    											   .append("       NULL AS BLOCK_ID, ")
	    											   .append("       NULL AS SUSPECT_FLAG, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH6, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH7, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH8, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH9, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH10, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH11, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH12, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH13, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH14, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH15, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH255_1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH255_2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH255_3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH4000_1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH4000_2, ")
	    											   .append("       NULL AS SLIDE_EMBEDDED_REF_ID, ")
	    											   .append("       NULL AS SLIDE_ID, ")
	    											   .append("       ? AS CALLED_FROM, ")
	    											   .append("       NULL AS DISPLAY_LINE_NO ")
	    											   .append("  FROM PWUE_GENERAL_LOOKUP ")
	    				                               .append(" WHERE DATA_FIELD = 'BUYOFF' ")
	    				                               .append("   AND DATA_VALUE = ? ")
	    											   .append("UNION ALL ")
	    											   .append("SELECT ? AS BUYOFF_TYPE, ")
	    											   .append("       NULL AS BUYOFF_CERT, ")
	    											   .append("       DATA_DESC AS BUYOFF_TITLE, ")
	    											   .append("       NULL AS DESCRIPTION, ")
	    											   .append("       NULL AS PLAN_ID, ")
	    											   .append("       NULL AS OPER_KEY, ")
	    											   .append("       NULL AS STEP_KEY, ")
	    											   .append("       NULL AS STEP_UPDT_NO, ")
	    											   .append("       NULL AS BUYOFF_ID, ")
	    											   .append("       NULL AS UPDT_USERID, ")
	    											   .append("       NULL AS TIME_STAMP, ")
	    											   .append("       NULL AS LAST_ACTION, ")
	    											   .append("       NULL AS BUYOFF_STATUS, ")
	    											   .append("       ? AS CROSS_ORDER_FLAG, ")
	    											   .append("       NULL AS OPTIONAL_FLAG, ")
	    											   .append("       NULL AS REF_ID, ")
	    											   .append("       NULL AS BLOCK_ID, ")
	    											   .append("       NULL AS SUSPECT_FLAG, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH6, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH7, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH8, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH9, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH10, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH11, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH12, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH13, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH14, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH15, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH255_1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH255_2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH255_3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH4000_1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH4000_2, ")
	    											   .append("       NULL AS SLIDE_EMBEDDED_REF_ID, ")
	    											   .append("       NULL AS SLIDE_ID, ")
	    											   .append("       ? AS CALLED_FROM, ")
	    											   .append("       NULL AS DISPLAY_LINE_NO ")
	    											   .append("  FROM PWUE_GENERAL_LOOKUP ")
	    				                               .append(" WHERE DATA_FIELD = 'BUYOFF' ")
	    				                               .append("   AND DATA_VALUE = ? ")
	    											   .append("UNION ALL ")
	    											   .append("SELECT ? AS BUYOFF_TYPE, ")
	    											   .append("       NULL AS BUYOFF_CERT, ")
	    											   .append("       DATA_DESC AS BUYOFF_TITLE, ")
	    											   .append("       NULL AS DESCRIPTION, ")
	    											   .append("       NULL AS PLAN_ID, ")
	    											   .append("       NULL AS OPER_KEY, ")
	    											   .append("       NULL AS STEP_KEY, ")
	    											   .append("       NULL AS STEP_UPDT_NO, ")
	    											   .append("       NULL AS BUYOFF_ID, ")
	    											   .append("       NULL AS UPDT_USERID, ")
	    											   .append("       NULL AS TIME_STAMP, ")
	    											   .append("       NULL AS LAST_ACTION, ")
	    											   .append("       NULL AS BUYOFF_STATUS, ")
	    											   .append("       ? AS CROSS_ORDER_FLAG, ")
	    											   .append("       NULL AS OPTIONAL_FLAG, ")
	    											   .append("       NULL AS REF_ID, ")
	    											   .append("       NULL AS BLOCK_ID, ")
	    											   .append("       NULL AS SUSPECT_FLAG, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH6, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH7, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH8, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH9, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH10, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH11, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH12, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH13, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH14, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH15, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH255_1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH255_2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH255_3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH4000_1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH4000_2, ")
	    											   .append("       NULL AS SLIDE_EMBEDDED_REF_ID, ")
	    											   .append("       NULL AS SLIDE_ID, ")
	    											   .append("       ? AS CALLED_FROM, ")
	    											   .append("       NULL AS DISPLAY_LINE_NO ")
	    											   .append("  FROM PWUE_GENERAL_LOOKUP ")
	    				                               .append(" WHERE DATA_FIELD = 'BUYOFF' ")
	    				                               .append("   AND DATA_VALUE = ? ")
	    											   .append("UNION ALL ")
	    											   .append("SELECT ? AS BUYOFF_TYPE, ")
	    											   .append("       NULL AS BUYOFF_CERT, ")
	    											   .append("       DATA_DESC AS BUYOFF_TITLE, ")
	    											   .append("       NULL AS DESCRIPTION, ")
	    											   .append("       NULL AS PLAN_ID, ")
	    											   .append("       NULL AS OPER_KEY, ")
	    											   .append("       NULL AS STEP_KEY, ")
	    											   .append("       NULL AS STEP_UPDT_NO, ")
	    											   .append("       NULL AS BUYOFF_ID, ")
	    											   .append("       NULL AS UPDT_USERID, ")
	    											   .append("       NULL AS TIME_STAMP, ")
	    											   .append("       NULL AS LAST_ACTION, ")
	    											   .append("       NULL AS BUYOFF_STATUS, ")
	    											   .append("       ? AS CROSS_ORDER_FLAG, ")
	    											   .append("       NULL AS OPTIONAL_FLAG, ")
	    											   .append("       NULL AS REF_ID, ")
	    											   .append("       NULL AS BLOCK_ID, ")
	    											   .append("       NULL AS SUSPECT_FLAG, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH6, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH7, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH8, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH9, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH10, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH11, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH12, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH13, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH14, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH15, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH255_1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH255_2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH255_3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH4000_1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH4000_2, ")
	    											   .append("       NULL AS SLIDE_EMBEDDED_REF_ID, ")
	    											   .append("       NULL AS SLIDE_ID, ")
	    											   .append("       ? AS CALLED_FROM, ")
	    											   .append("       NULL AS DISPLAY_LINE_NO ")
	    											   .append("  FROM PWUE_GENERAL_LOOKUP ")
	    				                               .append(" WHERE DATA_FIELD = 'BUYOFF' ")
	    				                               .append("   AND DATA_VALUE = ? ")
	    											   .append("UNION ALL ")
	    											   .append("SELECT ? AS BUYOFF_TYPE, ")
	    											   .append("       NULL AS BUYOFF_CERT, ")
	    											   .append("       DATA_DESC AS BUYOFF_TITLE, ")
	    											   .append("       NULL AS 	DESCRIPTION, ")
	    											   .append("       NULL AS PLAN_ID, ")
	    											   .append("       NULL AS OPER_KEY, ")
	    											   .append("       NULL AS STEP_KEY, ")
	    											   .append("       NULL AS STEP_UPDT_NO, ")
	    											   .append("       NULL AS BUYOFF_ID, ")
	    											   .append("       NULL AS UPDT_USERID, ")
	    											   .append("       NULL AS TIME_STAMP, ")
	    											   .append("       NULL AS LAST_ACTION, ")
	    											   .append("       NULL AS BUYOFF_STATUS, ")
	    											   .append("       ? AS CROSS_ORDER_FLAG, ")
	    											   .append("       NULL AS OPTIONAL_FLAG, ")
	    											   .append("       NULL AS REF_ID, ")
	    											   .append("       NULL AS BLOCK_ID, ")
	    											   .append("       NULL AS SUSPECT_FLAG, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH6, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH7, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH8, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH9, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH10, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH11, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH12, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH13, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH14, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH15, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH255_1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH255_2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH255_3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH4000_1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH4000_2, ")
	    											   .append("       NULL AS SLIDE_EMBEDDED_REF_ID, ")
	    											   .append("       NULL AS SLIDE_ID, ")
	    											   .append("       ? AS CALLED_FROM, ")
	    											   .append("       NULL AS DISPLAY_LINE_NO ")
	    											   .append("  FROM PWUE_GENERAL_LOOKUP ")
	    				                               .append(" WHERE DATA_FIELD = 'BUYOFF' ")
	    				                               .append("   AND DATA_VALUE = ? ")
	    											   .append("UNION ALL ")
	    											   .append("SELECT ? AS BUYOFF_TYPE, ")
	    											   .append("       NULL AS BUYOFF_CERT, ")
	    											   .append("       DATA_DESC AS BUYOFF_TITLE, ")
	    											   .append("       NULL AS 	DESCRIPTION, ")
	    											   .append("       NULL AS PLAN_ID, ")
	    											   .append("       NULL AS OPER_KEY, ")
	    											   .append("       NULL AS STEP_KEY, ")
	    											   .append("       NULL AS STEP_UPDT_NO, ")
	    											   .append("       NULL AS BUYOFF_ID, ")
	    											   .append("       NULL AS UPDT_USERID, ")
	    											   .append("       NULL AS TIME_STAMP, ")
	    											   .append("       NULL AS LAST_ACTION, ")
	    											   .append("       NULL AS BUYOFF_STATUS, ")
	    											   .append("       ? AS CROSS_ORDER_FLAG, ")
	    											   .append("       NULL AS OPTIONAL_FLAG, ")
	    											   .append("       NULL AS REF_ID, ")
	    											   .append("       NULL AS BLOCK_ID, ")
	    											   .append("       NULL AS SUSPECT_FLAG, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH6, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH7, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH8, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH9, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH10, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH11, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH12, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH13, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH14, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH15, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_NUM5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_DATE5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG4, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_FLAG5, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH255_1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH255_2, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH255_3, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH4000_1, ")
	    											   .append("       NULL AS UCF_STEP_BUYOFF_VCH4000_2, ")
	    											   .append("       NULL AS SLIDE_EMBEDDED_REF_ID, ")
	    											   .append("       NULL AS SLIDE_ID, ")
	    											   .append("       ? AS CALLED_FROM, ")
	    											   .append("       NULL AS DISPLAY_LINE_NO ")
	    											   .append("  FROM PWUE_GENERAL_LOOKUP ")
	    				                               .append(" WHERE DATA_FIELD = 'BUYOFF' ")
	    				                               .append("   AND DATA_VALUE = ? ) X");
	    										
	    	
	    	if(StringUtils.isNotEmpty(whereClause))
	    	{
	    		selectSql.append("  WHERE 1=1 AND " + whereClause  );
	    	}

	    	ParameterHolder params = new ParameterHolder();
	    	params.addParameter(SoluminaConstants.MFG_TYPE);
	    	params.addParameter(SoluminaConstants.N);
	    	params.addParameter(SoluminaConstants.AUTHORING_PALETTE);
	    	params.addParameter(SoluminaConstants.MFG_TYPE);
	    	params.addParameter(SoluminaConstants.MFG2_TYPE);
	    	params.addParameter(SoluminaConstants.N);
	    	params.addParameter(SoluminaConstants.AUTHORING_PALETTE);
	    	params.addParameter(SoluminaConstants.MFG2_TYPE);
	    	params.addParameter(SoluminaConstants.TECH_TYPE.toUpperCase());
	    	params.addParameter(SoluminaConstants.N);
	    	params.addParameter(SoluminaConstants.AUTHORING_PALETTE);
	    	params.addParameter(SoluminaConstants.TECH_TYPE.toUpperCase());
	    	params.addParameter(SoluminaConstants.TECH2_TYPE.toUpperCase());
	    	params.addParameter(SoluminaConstants.N);
	    	params.addParameter(SoluminaConstants.AUTHORING_PALETTE);
	    	params.addParameter(SoluminaConstants.TECH2_TYPE.toUpperCase());
	    	params.addParameter(SoluminaConstants.QA);
	    	params.addParameter(SoluminaConstants.N);
	    	params.addParameter(SoluminaConstants.AUTHORING_PALETTE);
	    	params.addParameter(SoluminaConstants.QA);
	    	params.addParameter(SoluminaConstants.INSP.toUpperCase());
	    	params.addParameter(SoluminaConstants.N);
	    	params.addParameter(SoluminaConstants.AUTHORING_PALETTE);
	    	params.addParameter(SoluminaConstants.INSP.toUpperCase());
	    	params.addParameter(SoluminaConstants.CUST);
	    	params.addParameter(SoluminaConstants.N);
	    	params.addParameter(SoluminaConstants.AUTHORING_PALETTE);
	    	params.addParameter(SoluminaConstants.CUST);
    	
	    	return(jdbcDao.queryForList(selectSql.toString(),params));
	}
    
	// Begin Add for SMRO_PLG_313
    public List<Map<String, Object>>  selectPlanOperDC(String planId,
                                                       Number planVersion,
                                                       Number planRevision,
                                                       Number planAlteration,
                                                       Number operKey,
                                                       String stepNo,
                                                       String refId,
                                                       String userId)
    {
        StringBuilder selectSql = new StringBuilder().append("SELECT A.DAT_COL_ID, A.DAT_COL_TYPE,A.STD_DATCOL_ID, A.DAT_COL_CERT, A.UPDT_USERID, A.TIME_STAMP, A.OPER_KEY, A.STEP_KEY, A.STEP_UPDT_NO, A.LAST_ACTION, A.DAT_COL_UOM, ") // FND-24180
                                                     .append(" A.DAT_COL_TITLE, B.DAT_COL_LIMIT_ID, B.UPPER_LIMIT, B.LOWER_LIMIT, B.TARGET_VALUE, ")
                                                     .append(" CASE WHEN ORIENTATION_FLAG = 'D' THEN 'Data Collection Centric' WHEN A.ORIENTATION_FLAG = 'U' THEN 'Unit Centric' END AS ORIENTATION_FLAG, ")
                                                     .append(" CROSS_ORDER_FLAG, DISPLAY_LINE_NO, OPTIONAL_FLAG, VARIABLE_NAME, VISIBILITY, NUM_DECIMAL_DIGITS, ")
                                                     .append(" CASE WHEN CALC_DC_FLAG = 'Y' THEN 'CALCULATED_DC' END AS CALC_DC_FLAG, CALC_DC_FLAG AS CALC_FLAG, A.DAT_COL_TITLE AS DAT_COL_TITLE_DISP, BLOCK_ID, ")
                                                     .append(" CASE WHEN A.SLIDE_EMBEDDED_REF_ID IS NULL THEN NULL WHEN SFMFG.SFFND_MM_SECURITY_GROUP_OK(A.SLIDE_ID,?) <> 'Y' THEN NULL ELSE 'Image' END AS IMAGE, ")
                                                     .append(" A.SLIDE_ID, A.SLIDE_EMBEDDED_REF_ID, A.AUDIT_FLAG, A.RESULT_ID, V.VALID_RESULT_TYPE, ") // FND-22108 , FND-24285											       
                                                     .append( jdbcDao.getSchemaPrefix() + "SFDB_SUBSTR_VARCHAR(O.OBJECT_REFERENCE,"
                                                             + jdbcDao.getSchemaPrefix() + "SFDB_INSTR_VARCHAR(O.OBJECT_REFERENCE, '\\', -1, 1)+1, " + jdbcDao.getSchemaPrefix() + "SFDB_LENGTH_VARCHAR(O.OBJECT_REFERENCE)) AS FILE_DISP, ") 
                                                     .append(" NULL AS FILE1, O.SECURITY_GROUP, U.FORMAT, A.TEMPLATE_FILE_ID, ")	 // FND-24936
                                                     .append(" A.UCF_STEP_DC_FLAG1, A.UCF_STEP_DC_VCH255_1, A.UCF_STEP_DC_FLAG2 ")
                                                     .append("FROM PWUST_SFPL_DAT_COL_V A LEFT OUTER JOIN SFPL_STEP_DAT_COL_LIMIT B ON ")
                                                     .append(" (A.PLAN_ID = B.PLAN_ID")
                                                     .append(" AND A.OPER_KEY = B.OPER_KEY")
                                                     .append(" AND A.STEP_KEY = B.STEP_KEY")
                                                     .append(" AND A.STEP_UPDT_NO = B.STEP_UPDT_NO")
                                                     .append(" AND A.DAT_COL_ID = B.DAT_COL_ID ) LEFT OUTER JOIN SFSQA_VALID_RESULT_TYPE_DEF V  ON ( A.RESULT_ID = V.RESULT_ID) ")
                                                     .append("                                   LEFT OUTER JOIN SFCORE_MM_OBJECT O ON A.TEMPLATE_FILE_ID = O.OBJECT_ID, ")											      
                                                     .append(" SFFND_UOM_DEF U ")
                                                     .append(" WHERE A.PLAN_ID = ? ")
                                                     .append(" AND A.PLAN_VERSION = ? ")
                                                     .append(" AND A.PLAN_REVISION = ? ")
                                                     .append(" AND A.PLAN_ALTERATIONS = ? ")
                                                     .append(" AND A.OPER_KEY = ? ")
                                                     .append(" AND STEP_NO = ( CASE WHEN ('HEADER' = ? OR 'FOOTER' = ? ) THEN ? ELSE ? END ) ") // FND-18510 
                                                     .append(" AND A.REF_ID = ? ")
                                                     .append(" AND A.DAT_COL_UOM = U.UOM ")
                                                     .append(" ORDER BY DISPLAY_LINE_NO ");
        
        ParameterHolder params = new ParameterHolder();
        params.addParameter(userId);
        params.addParameter(planId);
        params.addParameter(planVersion);
        params.addParameter(planRevision);
        params.addParameter(planAlteration);
        params.addParameter(operKey);
        params.addParameter(stepNo); // FND-18510
        params.addParameter(stepNo); // FND-18510
        params.addParameter(SoluminaConstants.ELLIPSIS); // FND-18510
        params.addParameter(stepNo); // FND-18510
        params.addParameter(refId);
        
        return(jdbcDao.queryForList(selectSql.toString(),params));
        
    }

    /*
     * (non-Javadoc)
     * @see com.ibaset.solumina.sfpl.dao.IStepDao#selectPlanStepDC(java.lang.String, java.lang.Number, java.lang.Number, java.lang.Number, java.lang.Number, java.lang.Number, java.lang.String, java.lang.String)
     */
    public List<Map<String, Object>>  selectPlanStepDC(String planId,
                                                       Number planVersion,
                                                       Number planRevision,
                                                       Number planAlteration,
                                                       Number operKey,
                                                       Number stepKey,
                                                       String refId,
                                                       String userId)
    {
        
        String selectSql = " SELECT A.DAT_COL_ID, A.DAT_COL_TYPE, A.DAT_COL_CERT, A.UPDT_USERID, A.TIME_STAMP, A.OPER_KEY, A.STEP_KEY, A.STEP_UPDT_NO, A.LAST_ACTION, A.DAT_COL_UOM, "
                         + "  A.DAT_COL_TITLE, D.DAT_COL_LIMIT_ID, "
                         + "    SFMFG.SFFND_FORMAT_VALUE_TO_CLIENT(D.UPPER_LIMIT, U.FORMAT, ? ) AS UPPER_LIMIT, "		// GE-5084
                         + "     SFMFG.SFFND_FORMAT_VALUE_TO_CLIENT(D.LOWER_LIMIT, U.FORMAT, ? ) AS LOWER_LIMIT, "		// GE-5084
                         + "    SFMFG.SFFND_FORMAT_VALUE_TO_CLIENT(D.TARGET_VALUE, U.FORMAT, ? ) AS TARGET_VALUE, "		// GE-5084
                         + "  DISPLAY_LINE_NO, "
                         + "  CASE WHEN ORIENTATION_FLAG = 'D' THEN 'Data Collection Centric' WHEN ORIENTATION_FLAG = 'U' THEN 'Unit Centric' END AS ORIENTATION_FLAG, "
                         + "  CROSS_ORDER_FLAG, OPTIONAL_FLAG, VARIABLE_NAME, VISIBILITY, NUM_DECIMAL_DIGITS, CASE WHEN CALC_DC_FLAG = 'Y' THEN 'CALCULATED_DC' END AS CALC_DC_FLAG, "
                         + "  CALC_DC_FLAG AS CALC_FLAG, A.DAT_COL_TITLE AS DAT_COL_TITLE_DISP, BLOCK_ID, "
                         + "  CASE WHEN A.SLIDE_EMBEDDED_REF_ID IS NULL THEN NULL WHEN SFMFG.SFFND_MM_SECURITY_GROUP_OK(A.SLIDE_ID,?) <> 'Y' THEN NULL "
                         + "       ELSE 'Image' END AS IMAGE, A.SLIDE_ID,A.SLIDE_EMBEDDED_REF_ID, A.AUDIT_FLAG, "		// FND-22108
                         + "       A.STD_DATCOL_ID, A.RESULT_ID, V.VALID_RESULT_TYPE, "		// FND-24180 , FND-24285
                         +  jdbcDao.getSchemaPrefix() + "SFDB_SUBSTR_VARCHAR(O.OBJECT_REFERENCE,"
                         +  jdbcDao.getSchemaPrefix() + "SFDB_INSTR_VARCHAR(O.OBJECT_REFERENCE, '\\', -1, 1)+1, " + jdbcDao.getSchemaPrefix() + "SFDB_LENGTH_VARCHAR(O.OBJECT_REFERENCE)) AS FILE_DISP, " 
                         + "  NULL AS FILE1, O.SECURITY_GROUP, U.FORMAT, A.TEMPLATE_FILE_ID, "	 // FND-24936	
                         + " A.UCF_STEP_DC_FLAG1, A.UCF_STEP_DC_VCH255_1, A.UCF_STEP_DC_FLAG2 "
                         + "  FROM PWUST_SFPL_DAT_COL_V A "
                         + "  LEFT OUTER JOIN SFFND_CERT_DEF C ON"
                         + "  A.DAT_COL_CERT = C.CERT"
                         + "  LEFT OUTER JOIN SFPL_STEP_DAT_COL_LIMIT D ON"
                         + "  (A.PLAN_ID = D.PLAN_ID "
                         + "  AND A.OPER_KEY = D.OPER_KEY "
                         + "  AND A.STEP_KEY = D.STEP_KEY "
                         + "  AND A.STEP_UPDT_NO = D.STEP_UPDT_NO "
                         + "  AND A.DAT_COL_ID = D.DAT_COL_ID)"
                         + "  LEFT OUTER JOIN SFSQA_VALID_RESULT_TYPE_DEF V ON (A.RESULT_ID = V.RESULT_ID)"
                         + "  LEFT OUTER JOIN SFCORE_MM_OBJECT O ON A.TEMPLATE_FILE_ID = O.OBJECT_ID, "
                         + "  SFFND_UOM_DEF U "
                         + "  WHERE A.PLAN_ID = ? "
                         + "  AND PLAN_VERSION = ? "
                         + "  AND PLAN_REVISION = ? "
                         + "  AND PLAN_ALTERATIONS = ? "
                         + "  AND A.OPER_KEY = ? "
                         + "  AND A.STEP_KEY = ? "
                         + "  AND REF_ID = ? "
                         + "  AND A.DAT_COL_UOM = U.UOM "
                         + "  ORDER BY DISPLAY_LINE_NO ";
        
        String userConnectionId = SoluminaContextHolder.getUserContext().getConnectionId();			// GE-5084
        ParameterHolder params = new ParameterHolder();
        // GE-5084
        params.addParameter(userConnectionId);
        params.addParameter(userConnectionId);
        params.addParameter(userConnectionId);
        
        params.addParameter(userId);
        params.addParameter(planId);
        params.addParameter(planVersion);
        params.addParameter(planRevision);
        params.addParameter(planAlteration);
        params.addParameter(operKey);
        params.addParameter(stepKey);
        params.addParameter(refId);
        
        return(jdbcDao.queryForList(selectSql, params));
    }
    // End Add for SMRO_PLG_313
}
