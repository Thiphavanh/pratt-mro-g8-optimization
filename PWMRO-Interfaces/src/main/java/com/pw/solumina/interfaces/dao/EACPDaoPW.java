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
*  File:    EACPDaoPW.java
* 
*  Created: 2017-10-17
* 
*  Author:  c293011
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-10-17 	C293011		    Initial Release SMRO_EXPT_204 eACP
*/


package com.pw.solumina.interfaces.dao;

/**
 * @author c293011
 *
 */

import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;

public class EACPDaoPW 
{

	@Reference	private  static JdbcDaoSupport eds;
	//@Reference	private static JdbcDaoSupport eds1;
	
	public String getEACPAuthMessageTextFromLog(String messageId)
	{
		String messageText = "";
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT T.MESSAGE_TEXT ")
												   .append("   FROM PW_R_EACP_AUTH_LOG T ")
												   .append("  WHERE T.SERVICE_NAME = 'PW_R_EACP_AUTH' ")
	    										   .append("    AND T.MESSAGE_ID   = ? ")
	    										   .append("    AND ROWNUM         = 1 ");
		 
		ParameterHolder params = new ParameterHolder();
		params.addParameter(messageId);
		
        try
        {
		  messageText = eds.queryForString(selectSql.toString(), params);
        }
        catch (Exception e)
        {
          messageText = "";
        }
		return messageText;
	}

	public String getEACPUserMessageTextFromLog(String messageId)
	{
		String messageText = "";
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT T.MESSAGE_TEXT ")
												   .append("   FROM R_PERSONNEL_SYNC_LOG T ")
												   .append("  WHERE T.SERVICE_NAME = 'R_PERSONNEL_SYNC' ")
	    										   .append("    AND T.MESSAGE_ID   = ? ")
	    										   .append("    AND ROWNUM         = 1 ");
		 
		ParameterHolder params = new ParameterHolder();
		params.addParameter(messageId);
		
        try
        {
		  messageText = eds.queryForString(selectSql.toString(), params);
        }
        catch (Exception e)
        {
          messageText = "";
        }
		return messageText;
	}
	
	public static String getEACPEndpoint()
	{
		String endpoint = "";
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT T.PARAMETER_VALUE ")
												   .append("   FROM PWUST_GLOBAL_CONFIG T ")
												   .append("  WHERE T.PARAMETER_NAME = 'EACP_ENDPOINT' ");
		 
		ParameterHolder params = new ParameterHolder();
		
        try
        {
        	endpoint = eds.queryForString(selectSql.toString(), params);
        }
        catch (Exception e)
        {
          endpoint = "";
        }
		return endpoint;
	}
	
	public static String getEACPPassword()
	{
		String pwd = "";
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT T.PARAMETER_VALUE ")
												   .append("   FROM PWUST_GLOBAL_CONFIG T ")
												   .append("  WHERE T.PARAMETER_NAME = 'EACP_PWD' ");
		 
		ParameterHolder params = new ParameterHolder();
		
        try
        {
        	pwd = eds.queryForString(selectSql.toString(), params);
        }
        catch (Exception e)
        {
        	pwd = "";
        }
		return pwd;
	}
	
	public static String getEACPUserid()
	{
		String id = "";
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT T.PARAMETER_VALUE ")
												   .append("   FROM PWUST_GLOBAL_CONFIG T ")
												   .append("  WHERE T.PARAMETER_NAME = 'EACP_ID' ");
		 
		ParameterHolder params = new ParameterHolder();
		
        try
        {
        	id = eds.queryForString(selectSql.toString(), params);
        }
        catch (Exception e)
        {
        	id = "";
        }
		return id;
	}
}
