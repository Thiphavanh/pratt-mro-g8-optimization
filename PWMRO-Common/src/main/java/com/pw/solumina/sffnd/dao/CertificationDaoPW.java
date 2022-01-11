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
*  File:    CertificationPW.java
* 
*  Created: 2018-03-29
* 
*  Author:  c079222
* 
*  Revision History
* 
*  Date      	Who             Description
*  ----------	---------------	-------------------------------------------------------------------
* 2018-03-29 	D.Miron         SMRO_USR_205 Defect 144 Initial Release
* 2018-04-18    D.Miron         SMRO_USR_205 Defect 144 - Renamed PWUST_GENERAL_LOOKUP to PWUE_GENERAL_LOOKUP
* 2018-06-19	B. Preston		SMRO_USR_205 Defect 503 - Added updateWorkLocation.
* 2018-06-27	B. Preston		SMRO_USR_205 Defect 503 - Call insert if update returns 0.
*/

package com.pw.solumina.sffnd.dao;

import java.util.List;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;


public class CertificationDaoPW {

	@Reference
	private JdbcDaoSupport jdbcDao;
	
	public void insertWorkLocationCerts(String workLocation, String cert, String desc) 
	{

		StringBuffer insertSql = new StringBuffer().append(" INSERT INTO PWUE_GENERAL_LOOKUP ")
				                                   .append(" (DATA_FIELD, DATA_VALUE, DATA_DESC, UPDT_USERID, DATA_VALUE_1) ")
												   .append("  VALUES ")
												   .append(" ('CERT' , ?, ?, ?, ? )");
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(workLocation);
		parameters.addParameter(desc);
		parameters.addParameter(ContextUtil.getUsername());
		parameters.addParameter(cert);

		jdbcDao.insert(insertSql.toString(), parameters);

		return;   	
	}
	
	public boolean checkWorkLocationCerts(String workLocation, String cert) 
	{
		boolean exists = false;
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT 'X' ")
												   .append("   FROM PWUE_GENERAL_LOOKUP ")
												   .append("  WHERE DATA_FIELD = 'CERT' ")
												   .append("    AND DATA_VALUE = ? ")
												   .append("    AND DATA_VALUE_1 = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(workLocation);
		parameters.addParameter(cert);

		List returnList = jdbcDao.queryForList(selectSql.toString(), parameters);

		if(returnList.size() > 0)
		{
			exists = true;
		}

		return exists;     	
	}
	
	public int deleteWorkLocationCert(String cert, String workLocation) 
	{

		StringBuffer updateSql = new StringBuffer().append(" DELETE FROM PWUE_GENERAL_LOOKUP  ")
												   .append("  WHERE DATA_FIELD = 'CERT' ")
												   .append("    AND DATA_VALUE = ? ")
												   .append("    AND DATA_VALUE_1 = ? ");
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(workLocation);
		parameters.addParameter(cert);

		int rowCount = jdbcDao.update(updateSql.toString(), parameters);

		return rowCount;   	
	}
	
	public boolean checkCertDefExists(String cert) 
	{
		boolean exists = false;

		StringBuffer selectSql = new StringBuffer().append(" SELECT 'X' ")
				                                   .append("   FROM SFFND_CERT_DEF ")
												   .append("  WHERE CERT = ? ");
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(cert);

		List returnList = jdbcDao.queryForList(selectSql.toString(), parameters);
		
		if(returnList.size() > 0)
		{
			exists = true;
		}
		
		return exists;   	
	}
	
	public int updateCertUpdtUseridTimeStamp(String cert)
	{
		int rowCount = 0;

		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFFND_CERT_DEF ")
												   .append(" SET UPDT_USERID = ?,  ")
												   .append(" TIME_STAMP = SYSDATE  ")
												   .append("  WHERE ")
												   .append(" CERT = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(ContextUtil.getUsername());
		parameters.addParameter(cert);

		rowCount = jdbcDao.update(updateSql.toString(), parameters); 

		return rowCount;
	}
	
	public List selectGeneralLookupCert(String workLocation, String cert) 
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT * ")
												   .append("   FROM PWUE_GENERAL_LOOKUP ")
												   .append("  WHERE DATA_FIELD = 'CERT' ")
												   .append("    AND DATA_VALUE_1 = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(cert);

		List returnList = jdbcDao.queryForList(selectSql.toString(), parameters);

		return returnList;     	
	}
	
	// Begin defect 503
	public void updateWorkLocation(String cert, String workLocation) {
		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFFND_CERT_DEF ")
				   .append(" SET cert_group = ?  ")
				   .append(" WHERE ")
				   .append(" CERT = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(workLocation);
		parameters.addParameter(cert);

		jdbcDao.update(updateSql.toString(), parameters); 
		
		updateSql = new StringBuffer().append(" UPDATE PWUE_GENERAL_LOOKUP ")
				   .append(" SET DATA_VALUE = ?  ")
				   .append(" WHERE DATA_FIELD = 'CERT' ")
				   .append(" AND DATA_VALUE_1 = ? ");

		parameters = new ParameterHolder();
		parameters.addParameter(workLocation);
		parameters.addParameter(cert);

		if (jdbcDao.update(updateSql.toString(), parameters) == 0) {
			
			insertWorkLocationCerts(workLocation, cert, cert);
		}
		
	}
	// End defect 503
}

