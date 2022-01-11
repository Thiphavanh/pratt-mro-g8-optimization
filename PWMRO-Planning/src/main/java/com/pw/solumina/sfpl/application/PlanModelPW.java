/*
*  This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    PlanModelPW.java
* 
*  Created: 2017-08-24
* 
*  Author:  xcs4331
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-08-24 		xcs4331		    Initial Release XXXXXXX
*/

package com.pw.solumina.sfpl.application;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.solumina.sffnd.application.IParse;
import com.ibaset.solumina.sfpl.dao.impl.PlanDaoImpl;
import com.pw.solumina.sfpl.application.impl.PlanDaoImplPW;

public class PlanModelPW {

	@Reference
	protected PlanDaoImplPW planDaoImplPW;
	
	@Reference
	protected IParse parse = null;
	
	public void insertPlanModel(String planId, Number planUpdtNo, String engineModel){
		
		if (StringUtils.isNotBlank(planId) && StringUtils.isNotBlank(engineModel) && planUpdtNo!=null){
			String engineModels[] = parse.parse(engineModel,SoluminaConstants.SEMI_COLON,true);
			if (engineModels!=null){
				for (int i=0; i<engineModels.length; i++){
					planDaoImplPW.insertIntoPwustPlanEngineModel(planId, planUpdtNo, engineModels[i]);	
				}
			}			
		}
	}
	
	public void deletePlanModel(String planId, Number planUpdtNo, String engineModel){
		
		if (StringUtils.isNotBlank(planId) && StringUtils.isNotBlank(engineModel) && planUpdtNo!=null){
			planDaoImplPW.deletePwustPlanEngineModel(planId, planUpdtNo, engineModel);
		}
	}
}
