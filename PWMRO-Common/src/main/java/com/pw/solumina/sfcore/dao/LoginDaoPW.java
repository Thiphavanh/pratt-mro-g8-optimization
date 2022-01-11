/*
* This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    LoginDaoPW.java
* 
*  Created: 2017-09-26
* 
*  Author:  R. Cannon
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------		-------------------------------------------------------------------
* 2017-09-26 	R. Cannon		    Initial Release SMRO_USR_201
* 2018-02-11    D. Miron            Defect 306 - Renamed field from ECLASS_HOST to XCLASS_HOST. 
* 2018-08-22 	Fred Ettefagh       defect 777 force client exits after 3 invalid login attempts 
*/

package com.pw.solumina.sfcore.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.sql.ParameterHolder;


public class LoginDaoPW
{
	@Reference private ExtensionDaoSupport eds;

	// defect 777
    public int updateUserLoginAttempt(String userID, Number userLoginAttempt){

    	StringBuffer selectSql = new StringBuffer().append("UPDATE SFFND_USER T SET T.USER_LOGIN_ATTEMPT = ? WHERE T.USERID = ? ");
    	
    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(userLoginAttempt);
    	params.addParameter(userID);
    	
    	int numberRowsUpdated = eds.update(selectSql.toString(), params);
    	return numberRowsUpdated;
    	
    }
	
	public boolean globalConfigObjEnabled(String globalParamName)
    {   
    	boolean objectEnabled;

		StringBuffer selectSql = new StringBuffer().append(" SELECT PARAMETER_VALUE ")
												   .append("   FROM PWUST_GLOBAL_CONFIG ")
				   								   .append("  WHERE PARAMETER_NAME = ? ");
		
		ParameterHolder params = new ParameterHolder();
    	params.addParameter(globalParamName);
        	
		try
		{
        	String paramValue = eds.queryForString(selectSql.toString(), params);

        	if("Y".equalsIgnoreCase(paramValue))
        		objectEnabled = true;
        	else
        		objectEnabled = false;
        }
		catch (Exception e)
		{
			objectEnabled = false;
		}
		
		return objectEnabled;
    }
    
	public boolean isUserLdapExempt(String userId)
	{
		boolean isExempt;

		StringBuffer selectSql = new StringBuffer().append(" SELECT COUNT(*) ")
												   .append("   FROM PWUST_EXEMPT_LDAP_USERID ")
				 								   .append("  WHERE USERID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(userId);

		try
		{
			int rowCount = eds.queryForInt(selectSql.toString(), params);
			 
			if(rowCount >= 1)
				isExempt = true;
			else
				isExempt = false;
		}
		catch (Exception e)
		{
			isExempt = false;
		}

		return isExempt;
	}
	 
	@SuppressWarnings("rawtypes")
	public Map getExternalProps(String smAgentName)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT AGENT_NAME, " )
				 								   .append(" 		PS_IP, ")
				 								   .append(" 		PS_CONMIN, ")
				 								   .append(" 		PS_CONMAX, ")
				 								   .append(" 		PS_CONSTEP, ")
				 								   .append(" 		PS_TIMEOUT, ")
				 								   .append(" 		PS_AZPORT, ")
				 								   .append(" 		PS_AUPORT, ")
				 								   .append(" 		PS_ACPORT, ")
				 								   .append(" 		AGENT_SECRET, ") //will be encrypted in db; decrypt before sending to sm
				 								   .append(" 		ADMIN_USRNAME, ")
				 								   .append(" 		ADMIN_PWD, ") //will be encrypted in db; decrypt before sending to sm
				 								   .append(" 		USER_DIR, ")
				 								   .append(" 		ORG_ROOT, ")
				 								   .append(" 		LDAP_HOST, ")
				 								   .append(" 		LDAP_PORT, ")
				 								   .append(" 		LDAP_USER,")
				 								   .append(" 		LDAP_PWD, ")
				 								   .append(" 		LDAP_CHG_PWD_LINK,")
				 								   .append(" 		XCLASS_HOST, ")    // DEFECT 306
				 								   .append(" 		WAS_INSTANCE, ")
				 								   .append(" 		AGENT_DESCR, ")
				 								   .append(" 		COMMENTS, ")
				 								   .append(" 		KEY10, ")
				 								   .append(" 		KEY11, ")
				 								   .append(" 		KEY12, ")
				 								   .append(" 		KEY13, ")
				 								   .append(" 		KEY14, ")
				 								   .append(" 		KEY15 ")
				 								   .append("   FROM PWUST_EXT_SYS_PROPERTIES T ")
				 								   .append("  WHERE T.AGENT_NAME = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(smAgentName);
			
		Map returnMap = eds.queryForMap(selectSql.toString(), params);
			
		return returnMap;
	}

	public String getUserHomeWorkLoc(String userId)
	{
		String workLoc;
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT L.WORK_LOC " )
				                          		   .append("   FROM SFFND_USER U, SFFND_WORK_LOC_DEF L ")
				                          		   .append("  WHERE U.LOCATION_ID = L.LOCATION_ID ")
				                          		   .append("    AND USERID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(userId);
	     		 
		try
		{
			workLoc = eds.queryForString(selectSql.toString(), params);
		}
		catch (Exception e) //User not in SFFND_USER, missing work loc, or has invalid work loc
		{
			workLoc = "NONE";
		}

		return workLoc;
	}

	public boolean isNonUSLocation(String workLoc)
	{
		boolean validNonUsLoc;
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT NVL(UCF_WORK_LOC_FLAG1, 'N') ")
			                                       .append("   FROM SFFND_WORK_LOC_DEF ")
            	                                   .append("  WHERE WORK_LOC = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(workLoc);
        
		try
		{
        	String paramValue = eds.queryForString(selectSql.toString(), params);

        	if("Y".equalsIgnoreCase(paramValue))
        		validNonUsLoc = true;
        	else
        		validNonUsLoc = false;
        }
		catch (Exception e)
		{
			validNonUsLoc = false;
		}
		
		return validNonUsLoc;
	}

	@SuppressWarnings("rawtypes")
	public Date getLastPasswordChange(String userId)
	{
		Date lastPswdChg;
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT UCF_USER_DATE1 ")
				   								   .append("   FROM SFFND_USER ")
				   								   .append("  WHERE USERID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(userId);
		
		Map userInfo = eds.queryForMap(selectSql.toString(), params);
		lastPswdChg = (Date) userInfo.get("UCF_USER_DATE1");
		
		return lastPswdChg;
	}
	
	public void setLastDtPwdChgd (String userId, Date smDate)
	{
		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFFND_USER ")
												   .append("    SET UCF_USER_DATE1 = ? ")
												   .append("  WHERE USERID = ? ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(smDate);
		params.addParameter(userId);

		eds.update(updateSql.toString(), params);
	}
	
	public int numDaysTillPwdExp(Date lastPwdDt)
	{
		Calendar now = Calendar.getInstance();
		Date calendarDate = now.getTime(); 
		
		//calculates milliseconds between lastPwdDt and today
		long difference = (calendarDate.getTime()-lastPwdDt.getTime());
		
		long diffDays = difference/(1000 * 60 * 60 * 24); //denominator equals 86,400,000 ms
		int diffSince = (int) diffDays;
		
		return diffSince;
	}

    public boolean hasMechanicOrInspectorRole(String userId)
	{
    	boolean userHasRole = false;
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT COUNT(ROLE) ")
				                                   .append("   FROM SFCORE_USER_ROLES ")
				                                   .append("  WHERE USERID = ? ")
				                                   .append("    AND ROLE IN ('SF$MECHANIC', ")
				                                   .append("     			 'SF$NEW_MECHANIC', ")
				                                   .append("     			 'SF$BYPASS_MECHANIC', ")
				                                   .append("     			 'SF$INSPECTOR') ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(userId);
		
		int count = eds.queryForInt(selectSql.toString(), params);
		
		if (count > 0)
		{
			userHasRole = true;
		}
		
		return userHasRole;
	}
    
    public String validEyeExam(String userId)
	{
    	String eyeExamValid = "N";
		
		//LAST_EYE_EXAM_DATE represents EXPIRATION date of most recent eye exam taken
    	StringBuffer selectSql = new StringBuffer().append(" SELECT COUNT(LAST_EYE_EXAM_DATE) ")
				                                   .append("   FROM SFFND_USER ")
				                                   .append("  WHERE USERID = ? ")
				                                   .append("    AND LAST_EYE_EXAM_DATE >= SYSDATE ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(userId);
		
		int count = eds.queryForInt(selectSql.toString(), params);
		
		if (count > 0)
		{
			eyeExamValid = "Y";
		}
		
		return eyeExamValid;
	}
    
    public String validFODCertification(String userId)
	{
    	String certified = "N";
		
		//Confirm user has at least 1 active non-eye-exam cert
    	StringBuffer selectSql = new StringBuffer().append(" SELECT COUNT(EXP_DATE) ")
				                                   .append("   FROM SFFND_USER_CERTS ")
				                                   .append("  WHERE USERID = ? ")
				                                   .append("    AND CERT NOT IN ('OJTALLEYEXMF') ") //Assembly's Universal Eye Exam Cert
				                                   .append("    AND EXP_DATE >= SYSDATE ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(userId);
		
		int count = eds.queryForInt(selectSql.toString(), params);
		
		if (count > 0)
		{
			certified = "Y";
		}
		
		return certified;
	}
	
} //end LoginDaoPW Class

