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
 *  File:    UserCertsDao.java
 * 
 *  Created: 2018-03-02 
 * 
 *  Author:  Raeann Thorpe  
 * 
 *  Revision History
 * 
 *  Date        Who          	Description
 *  --------    ------------ 	---------------------------------------------------
 *  2018-03-02	R. Thorpe		Initial Release for SMRO_MD_M211
 * 
 */

package com.pw.solumina.interfaces.dao;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;

public class UserCertDaoPW
{	
	@Reference private JdbcDaoSupport eds;

    public boolean doesUserCertExist(String employeeNumber,String certificationCode)
    {
    	boolean userCertExists = true;
    	StringBuffer selectSql = new StringBuffer().append("SELECT COUNT(*) ")
					   							   .append("FROM ")
					   							   .append("SFFND_USER_CERTS ")
					   							   .append("WHERE ")
					   						       .append("USERID = ? ")
  		                  						   .append("AND CERT = ? ");
    	
    	ParameterHolder params = new ParameterHolder();
    	//Where
    	params.addParameter(employeeNumber);
    	params.addParameter(certificationCode);
    	
    	int rowCount = eds.queryForInt(selectSql.toString(), params);

        if (rowCount <= 0)
        {
        	userCertExists = false;
        }
        return userCertExists;
    }
}
