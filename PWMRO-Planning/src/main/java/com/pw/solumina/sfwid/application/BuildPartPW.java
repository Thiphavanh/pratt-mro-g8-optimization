/*
 * This unpublished work, first created in 2017 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2017,2020.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:   BuildPartPW
 * 
 *  Created: 2017-09-22
 * 
 *  Author:  c293011
 * 
 *  Revision History
 * 
 *  Date      	Who                	Description
 *  ----------	---------------	---------------	---------------------------------------------------
 * 2017-09-22 	c293011		    Initial Release SMRO_PLG_205
 * 2018-10-22   R. Thorpe		R2 Upgrade - Defect# 1014 - added params to the updatePlanBuildPartPre methods
 * 2020-10-29   D.Miron         Defect 1808 - Update plan security group based on plan work location.
 * 2020-12-04   J DeNinno       Defect 1822/1814 - Only update taskgroups for Overhaul
 */

package com.pw.solumina.sfwid.application;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.ibaset.common.Reference;
import com.ibaset.solumina.sfor.application.ISfplPlan;
import com.ibaset.solumina.sfor.dao.ISfplPlanDao;
import com.ibaset.solumina.sfpl.dao.IPlanDao;
import com.ibaset.solumina.sfwid.application.IBuildPart;
import com.pw.solumina.sfor.dao.TaskGroupsDaoPW;
import com.pw.solumina.sfpl.dao.PlanDaoPW;


public class BuildPartPW
{

	@Reference private ISfplPlan sfplPlan = null;
	@Reference private IPlanDao planDao = null;
	@Reference private ISfplPlanDao sfplPlanDao = null;
	@Reference private IBuildPart buildPart = null;

	@Reference private TaskGroupsDaoPW taskGroupsDaoPW;
	@Reference private PlanDaoPW planDaoPW = null;

	public void updatePlanBuildPartPre(String planId,
			Number planVersion,
			Number planRevision,
			Number planAlterations,
			String oldPartNumber,
			String oldPartChange,
			String newPartNumber,
			String newPartChange,
			String bomNo,
			String mfgBomChg,
			String bomId,
			String itemType,
			String itemSubtype,
			String explicitBomLink, 
			String orderUom,
			String locationId, //Defect 1014
			String program)//Defect 1014
	{
		//Call OOOB Method First
		buildPart.updatePlanBuildPartPre(planId,
				planVersion,
				planRevision,
				planAlterations,
				oldPartNumber,
				oldPartChange,
				newPartNumber,
				newPartChange,
				bomNo,
				mfgBomChg,
				bomId,
				itemType,
				itemSubtype,
				explicitBomLink, 
				orderUom,
				locationId, //Defect 1014
				program);//Defect 1014

		
			//get the planupdtNo
			Map planMap = planDao.selectPlan(planId, planVersion, planRevision, planAlterations);
			Number planUpdtNo = (Number) planMap.get("PLAN_UPDT_NO");
			
			String planType =  planDaoPW.getPlanType(planId); //defect 1822/1814
			if ("OVERHAUL".equals(planType)) {    //defect 1822/1814

			//get a list of subject and revision in plan 
			List taskInformation = taskGroupsDaoPW.selectTaskGroupsForPlan(planId, planVersion, planRevision, planAlterations);
			ListIterator task = taskInformation.listIterator();

			while (task.hasNext())
			{
				Map collection = (Map) task.next();

				Number subjectNumber = (Number) collection.get("SUBJECT_NO");
				Number subjectRev    = (Number) collection.get("SUBJECT_REV");

				//Update Subject Part
				String itemId = taskGroupsDaoPW.getPartItemIdForPlan(planId, planUpdtNo);
				String partNo = taskGroupsDaoPW.getPartNoForPlan(planId, planUpdtNo); //defect 1814
				
				taskGroupsDaoPW.updateSubjectPart(planId, planUpdtNo, subjectNumber, subjectRev, itemId,partNo); //defect 1814
			}    

		}  //defect 1822/1814
		// Defect 1808
		planDaoPW.updateBuildPartSecurityGroup(planId, planUpdtNo, locationId);
	}
}
