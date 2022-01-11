package com.pw.solumina.sfwid.dao;

/*
 *  This unpublished work, first created in 2020 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2020.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    StepDaoPW.java
 * 
 *  Created: 2020-12-02
 * 
 *  Author:  Scott Edgerly
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2020-12-02		S.Edgerly	    Initial Release - SMRO_WOE_308 - Defect 1819 - Supplemental Orders
*/

import java.util.List;
import com.ibaset.common.Reference;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.sql.ParameterHolder;

public class StepDaoPW {
	@Reference private static ExtensionDaoSupport eds;
	
	public List getOrderInfo(String orderNo){
		StringBuffer selectSql;
		selectSql = new StringBuffer().append("SELECT *                      ")
				   					  .append("  FROM PWUST_SFWID_ORDER_DESC ")
				   					  .append(" WHERE PW_ORDER_NO = ?        ");

		ParameterHolder params = new ParameterHolder();
		 params.addParameter(orderNo);
		 
		 return eds.queryForList(selectSql.toString(), params);

	}
	
	public void clearCopyStepsExtraneousData(String orderID, String operNo, String stepNo){
		StringBuffer updateSql;
		updateSql = new StringBuffer().append("UPDATE SFWID_OPER_BUYOFF            ")
				   					  .append("  SET OPTIONAL_FLAG         = 'N',  ")
				   					  .append("      UCF_OPER_BUYOFF_VCH1  = NULL, ")
				   					  .append("      UCF_OPER_BUYOFF_VCH3  = NULL, ")
				   					  .append("      UCF_OPER_BUYOFF_DATE1 = NULL  ")
				   					  .append(" WHERE ORDER_ID = ?                 ")
				   					  .append("   AND OPER_NO  = ?                 ")
				   					  .append("   AND STEP_NO  = ?                 ");

		ParameterHolder params = new ParameterHolder();
		 params.addParameter(orderID);
		 params.addParameter(operNo);
		 params.addParameter(stepNo);
		 
		 eds.update(updateSql.toString(), params);
	}
}
