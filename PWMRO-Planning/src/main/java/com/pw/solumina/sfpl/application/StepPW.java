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
package com.pw.solumina.sfpl.application;

import com.ibaset.common.Reference;
import com.ibaset.solumina.sfpl.application.IStep;
import com.pw.solumina.sfpl.dao.StepDaoPW;

public class StepPW {
	@Reference IStep iStep;
	@Reference StepDaoPW stepDaoPW;
	
	public void preCopyStepFromWid(String fromOrderId,
            String fromOperationNumber,
            String fromStepNumber,
            String toPlanId,
            Number toPlanVersion,
            Number toPlanRevision,
            Number toPlanAlterations,
            String toOperationNumber,
            String toStepNumber,
            String partsCopyFlag,
            String toStepTitle,
            Number toExecutionOrder){
		
		iStep.preCopyStepFromWid(fromOrderId, 
				fromOperationNumber, 
				fromStepNumber, 
				toPlanId, 
				toPlanVersion, 
				toPlanRevision, 
				toPlanAlterations, 
				toOperationNumber, 
				toStepNumber,
				partsCopyFlag,
				toStepTitle, 
				toExecutionOrder);
		
		//Clear phantom data here
		stepDaoPW.clearCopyStepsExtraneousData(toPlanId, toPlanVersion, toPlanRevision, toPlanAlterations, toOperationNumber, toStepNumber);
	}
}