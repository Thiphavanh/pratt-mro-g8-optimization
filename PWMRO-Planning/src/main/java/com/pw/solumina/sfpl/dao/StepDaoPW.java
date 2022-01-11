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
 *  File:    StepPW.java
 * 
 *  Created: 2020-12-10
 * 
 *  Author:  Scott Edgerly
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2020-12-10		S.Edgerly	    Initial Release - SMRO_WOE_308 - Defect 1819 - Supplemental Orders
*/
package com.pw.solumina.sfpl.dao;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.sql.ParameterHolder;

public class StepDaoPW {
	@Reference private static ExtensionDaoSupport eds;
	
	public void clearCopyStepsExtraneousData(String planID, Number planVersion, Number planRevision, Number planAlterations, String operNo, String stepNo){
		StringBuffer updateSql = new StringBuffer().append("UPDATE SFPL_STEP_BUYOFF T")
				   .append(" SET T.UCF_STEP_BUYOFF_VCH1 = NULL, ")
				   .append(" T.UCF_STEP_BUYOFF_VCH2 = NULL, ")
				   .append(" T.UCF_STEP_BUYOFF_VCH3 = NULL, ")
				   .append(" T.UCF_STEP_BUYOFF_DATE1 = NULL, ")
				   .append(" T.UCF_STEP_BUYOFF_NUM1 = NULL ")
				   .append(" WHERE T.PLAN_ID = ? ")
				   .append(" AND (T.OPER_KEY, T.STEP_KEY) IN ")
				   .append(" 	 (SELECT DISTINCT X.OPER_KEY, X.STEP_KEY ")
				   .append(" 		FROM SFPL_STEP_V X ")
				   .append(" 	   WHERE X.PLAN_ID = T.PLAN_ID ")
				   .append("         AND X.PLAN_REVISION = ? ")
				   .append(" 		 AND x.PLAN_VERSION = ? ")
				   .append(" 		 AND x.PLAN_ALTERATIONS = ? ")
				   .append(" 	 	 AND OPER_NO = ? ")
				   .append(" 		 AND STEP_NO = ?) ");

		
		 ParameterHolder params = new ParameterHolder();
		 params.addParameter(planID);
		 params.addParameter(planRevision);
		 params.addParameter(planVersion);
		 params.addParameter(planAlterations);
		 params.addParameter(operNo);
		 params.addParameter(stepNo);

		 eds.update(updateSql.toString(), params);
	}
}
