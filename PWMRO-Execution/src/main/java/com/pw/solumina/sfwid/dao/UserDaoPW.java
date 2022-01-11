/*
 *  This unpublished work, first created in 2018 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2018.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    OperationPW.java
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
 *  2018-04-16		R. Thorpe       moved getLaborFlag(String) method from the UserDAoPW class in the Planning project to here.
 *  2018-04-14		R. Miron		SMRO_WOE_202 Defect 572 - Revised selectUseridfromBadgeSwipe
 *  2018-10-11		Bob Preston		SoluminaOOB, defect 970 -- Added getLocationId().
 *  2018-10-23		Bob Preston		SoluminaOOB, defect 970 -- Added getLocationId2().
 */
package com.pw.solumina.sfwid.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.common.Reference;
import com.ibaset.common.dao.ExtensionDaoSupport;

public class UserDaoPW 
{
	@Reference private ExtensionDaoSupport eds;
	
	// Defect 572
	public Map selectUseridfromBadgeSwipe(String BadgeId, boolean firstPass)
	{
		Map returnMap = new HashMap();
		Map map;
		String userid = null;

		if(firstPass)
		{
			String clockNo = BadgeId.substring(1);
			String userId = "%" + clockNo;

			StringBuffer selectSql = new StringBuffer().append("SELECT USERID ")
													   .append("  FROM SFFND_USER ")
													   .append(" WHERE USERID LIKE ? ")
													   .append("   AND OBSOLETE_RECORD_FLAG <> 'Y' ");

			ParameterHolder parameters = new ParameterHolder();
			parameters.addParameter(userId);

			List useridList =  eds.queryForList(selectSql.toString(), parameters);

			if (useridList.size() == 1) 
			{
				map = (Map) useridList.get(0);
				userid = (String) map.get("USERID");
			}
		}
		else
		{
			StringBuffer selectSql = new StringBuffer().append("SELECT USERID ")
					                                   .append("  FROM SFFND_USER")
					                                   .append(" WHERE UCF_USER_VCH1 = ? ")
					                                   .append("   AND OBSOLETE_RECORD_FLAG <> 'Y' ");

			ParameterHolder parameters = new ParameterHolder();
			parameters.addParameter(BadgeId);

			List useridList =  eds.queryForList(selectSql.toString(), parameters);

			if (useridList.size() == 1) 
			{
				map = (Map) useridList.get(0);
				userid = (String) map.get("USERID");
			}
		}

		returnMap.put("CLOCK_NO", userid);
		return returnMap;
	}
	
	//Begin SMRO_TR_T203 - OH Labor Interface
	public String getLaborFlag(String userId)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT U.UCF_USER_FLAG2 ")
				                                   .append("  FROM SFFND_USER U ")
				                                   .append(" WHERE U.USERID = ? ");

		ParameterHolder param = new ParameterHolder();
		param.addParameter(userId);

		String laborFlag = eds.queryForString(selectSql.toString(), param);
		return laborFlag;              
	}

	// Begin defect 970
	public String getLocationId(String userId)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT U.LOCATION_ID ")
				                                   .append("  FROM SFFND_USER U ")
				                                   .append(" WHERE U.USERID = ? ");

		ParameterHolder param = new ParameterHolder();
		param.addParameter(userId);

		String locationId = "";
		try {
			locationId = eds.queryForString(selectSql.toString(), param);
		} catch (Exception e) {
		}
		return locationId;              
	}
	
	public String getLocationId2(String userId)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT U.WC_INFO ")
				                                   .append("  FROM SFFND_USER U ")
				                                   .append(" WHERE U.USERID = ? ");

		ParameterHolder param = new ParameterHolder();
		param.addParameter(userId);

		String locationId = eds.queryForString(selectSql.toString(), param);
		return locationId;              
	}
	// End defect 970
	
}
