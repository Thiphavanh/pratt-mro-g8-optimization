
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
*  File:    PlanImplPW.java
* 
*  Created: 2020-03-23
* 
*  Author:  x007084
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
*  2020		    John DeNinno        Initial Release defect 1485 task group was losing a task
*  6/4/2020     John DeNinno        refresh the import export
* */

package com.pw.solumina.sfor.application.impl;

import java.util.List;
import java.util.Map;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sfor.application.ISfplPlan;
import com.ibaset.solumina.sfor.dao.ISfplPlanDao;
import com.ibaset.solumina.sfpl.application.IPlanOfd;
import com.pw.solumina.sfpl.application.impl.PlanDaoImplPW;




public abstract class SfplPlanImplPw extends ImplementationOf<ISfplPlan> implements ISfplPlan {

	@Reference
	private IPlanOfd planOfd;

	@Reference
	private com.ibaset.solumina.sfor.application.IPlan sforPlan;

	@Reference
	private ISfplPlanDao sfplPlanDao;
	@Reference	
	protected PlanDaoImplPW planDaoImplPW;
	@Reference
	private IEvent event;

	//Begin defect 1485
	@Override
	public void insertSubjectPart(String planId, Number planVersion, Number planRevision, Number planAlterations,
			Number subjectNumber, String partNumber, String partChange) {

		Number toPlanUpdateNumber = null;
		Number SUBJECT_REV = null;
		
		Super.insertSubjectPart(planId, planVersion, planRevision, planAlterations, subjectNumber, partNumber, partChange);

		List SfplplanVList = planDaoImplPW.selectSfplplanV(planId, planVersion, planRevision, planAlterations);
		if (SfplplanVList!=null && !SfplplanVList.isEmpty() && SfplplanVList.size() ==1){
			Map map = (Map)SfplplanVList.get(0);
			toPlanUpdateNumber = (Number)map.get("PLAN_UPDT_NO");
		
			
			planDaoImplPW.insertTaskGroupExtension2(planId, toPlanUpdateNumber);
		}
      //end defect 1485
    


	}

	//defect 1380 add refresh
	@Override
	public void includeAllExcludeAllTaskGroup(String planId, Number planVersion, Number planRevision,
			Number planAlterations, String calledFrom) {

      Super.includeAllExcludeAllTaskGroup(planId, planVersion, planRevision, planAlterations, calledFrom);
		
      event.refreshDataPages();
      
	}
	
	
	
}
