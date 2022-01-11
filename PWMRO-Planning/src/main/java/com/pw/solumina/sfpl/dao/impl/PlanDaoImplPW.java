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
*  File:    PlanDaoPW.java
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
* 2018-12-17    Fred Ettefagh   Defect 1044, fix repair order create 
*/
package com.pw.solumina.sfpl.dao.impl;

import static com.ibaset.common.FrameworkConstants.INSERTED;
import static com.ibaset.common.security.context.ContextUtil.getUsername;
import static org.apache.commons.lang.StringUtils.lowerCase;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.solumina.sfpl.dao.IPlanDao;

public abstract class PlanDaoImplPW  extends ImplementationOf<IPlanDao> implements IPlanDao{
	
	@Reference
	private JdbcDaoSupport jdbcDao;

    public Number planVersionRevisionCount(String planNumber)
    {
        StringBuffer select = new StringBuffer().append(" SELECT COUNT(?) ")
                                                .append(" FROM SFPL_PLAN_REV  A, SFPL_PLAN_DESC B ")
                                                .append(" WHERE  ")
                                                .append(" 	  A.PLAN_ID=B.PLAN_ID ")
                                                .append(" AND A.PLAN_UPDT_NO=B.PLAN_UPDT_NO ")
                                                .append(" AND B.PLAN_NO = ? ");

        ParameterHolder params = new ParameterHolder();
        params.addParameter(SoluminaConstants.X);
        params.addParameter(planNumber);

        return (jdbcDao.queryForInt(select.toString(), params) > 0 ? 1 : -1);
    }
    
    
    
	 public void insertTextObject(String objectId,
								  String language,
								  String text,
								  String plainText)
	 {
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO SFFND_TEXT_OBJECT ( ")
									   .append("    OBJECT_ID, ")
									   .append("    LANGUAGE_CODE, ")
									   .append("    TEXT , ")
									   .append("    PLAIN_TEXT , ") // FND-26634
									   .append("    UPDT_USERID, ")
									   .append("    TIME_STAMP, ")
									   .append("    LAST_ACTION ")
									   .append(")VALUES( ")
									   .append("    ?, ")
									   .append("    ?, ")
									   .append("    ?, ")
									   .append("    ?, ")
									   .append("    ?, ")
									   .append(jdbcDao.getTimestampFunction()
											   + ", ")
									   .append("    ?)");
		ParameterHolder params = new ParameterHolder();
		params.addParameter(objectId);
		if (language==null){
			language="en";
		}
		params.addParameter(lowerCase(language));
		params.addParameter(text);
		params.addParameter(plainText); // FND-26634
		params.addParameter(getUsername());
		params.addParameter(INSERTED);
		jdbcDao.insert(insertSql.toString(), params);
	 }


}
