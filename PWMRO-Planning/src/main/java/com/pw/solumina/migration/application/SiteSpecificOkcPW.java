/*
 *  This unpublished work, first created in 2018 and updated thereafter,
 *  is under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2018.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    MigrationHeaderVariablesPW.java
 *  
 *  SMRO_CV_202
 * 
 *  Created: 2018.10.19
 * 
 *  Author:  R Cannon
 * 
 *  Revision History
 * 
 *  Date       	 	Who          	Description
 *  -----------		------------ 	---------------------------------------------------
 *  2018-10-19		R. Cannon		SMRO_CV_202 - Initial Release
 */
package com.pw.solumina.migration.application;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import com.ibaset.common.Reference;

class SiteSpecificOkcPW {

	@Reference private SoluminaMigrationDaoPW SoluminaMigrationDaoPW;

	void logFooterWarning(PlanObjectParams plan) {

		String errorText = "Unexpected Footer case encountered. OKC plan data expected "
				 + "on Headers only. Data entered on operation Footer. Part_no="
				 + plan.getPartNo();

		SoluminaMigrationDaoPW.insertErrorLogG8("UNEXPECTED_OKC_FTR", MigrationVariablesPW.WARN,
				plan.getSrcPlanId(), MigrationVariablesPW.PLAN_UPDT_NO, plan.getOperNo(), plan.getStepNo(),
				MigrationVariablesPW.NA_ERR, 0, errorText, MigrationVariablesPW.SFMFG);
	}

	/* **************************************************************************************
	 *  Headers to insert as the first "Step" on an operation. This method ensures this
	 *  as well as increments any pre-existing step numbers (if necessary) in the unlikely
	 *  event a subsequent list of steps exists.
	 * **************************************************************************************/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	List addHeaderToStepList(PlanObjectParams plan, Number src_oper_key, List stepList) {

		String src_plan_id = plan.getSrcPlanId();

		/******************
		 * Handle STEP(s) *
		 ******************/
		int hdrStepNo = Integer.parseInt(MigrationVariablesPW.STEP_TEN);

		boolean needRenum = false;
		boolean parseFail = false;

		ListIterator stepNoCheckIter = stepList.listIterator();

		//First, loop over the list of steps to determine if a step renumbering is necessary
		while(stepNoCheckIter.hasNext()) {

			Map stepMap = (Map) stepNoCheckIter.next();
			String stepNoStr = (String) stepMap.get("STEP_NO");

			try {
				int stepNoInt = Integer.parseInt(stepNoStr);

				//If 1+ steps are less than or equal to the predetermined step value for the header,
				//then the entire list needs to be renumbered
				if(stepNoInt <= hdrStepNo) {
					needRenum = true;
				}
			} catch (NumberFormatException e) {

				parseFail = true;
				String errorText = "Unable to parse step. Cannot increment value. Part_no=" + plan.getPartNo();

				SoluminaMigrationDaoPW.insertErrorLogG8("OKC_STEP_PARSE", MigrationVariablesPW.FAIL,
						src_plan_id, MigrationVariablesPW.PLAN_UPDT_NO, plan.getOperNo(), plan.getStepNo(),
						MigrationVariablesPW.NA_ERR, 0, errorText, MigrationVariablesPW.SFMFG);
			}
		} //end while("check need for step list renumbering")

		if(parseFail) {

			String errorText = "One or more steps were not parsed. Check step " + hdrStepNo + " (OKC Header) and "
							   + "any step logged for parse failure against surrounding steps to ensure correct "
							   + "order maintained. Part_no=" + plan.getPartNo();

			SoluminaMigrationDaoPW.insertErrorLogG8("STEP_RENUM_ALERT", MigrationVariablesPW.WARN,
					src_plan_id, MigrationVariablesPW.PLAN_UPDT_NO, plan.getOperNo(), plan.getStepNo(),
					MigrationVariablesPW.NA_ERR, 0, errorText, MigrationVariablesPW.SFMFG);
		}

		if(needRenum) {

			ListIterator stepRenumIter = stepList.listIterator();

			//Increment step numbering by the predetermined step value for the header
			//ensuring the header will insert accurately as the first step on the list.
			while(stepRenumIter.hasNext()) {

				Map stepMap = (Map) stepRenumIter.next();
				String stepNoStr = (String) stepMap.get("STEP_NO");

				try {
					int stepNoInt = Integer.parseInt(stepNoStr);
					stepNoInt += hdrStepNo; //increment
					stepNoStr = String.valueOf(stepNoInt);
					stepMap.replace("STEP_NO", stepNoStr);
				}
				catch (NumberFormatException e) {} //handled previously
			}
		} //end if(renumbering necessary)

		/*****************
		 * Insert HEADER *
		 *****************/
		List operHdrFtrList = SoluminaMigrationDaoPW.operHeaderFooterXML(src_plan_id, src_oper_key);
		ListIterator hdrFtrIter = operHdrFtrList.listIterator();

		//Insert Header as the first step for a step list
		while (hdrFtrIter.hasNext()) {

			Map operHdrFtrMap = (Map) hdrFtrIter.next();

			//VIS table STEP_NO for hdr/ftr's will always be "HEAD" or "FOOT"
			String operHdrFtr = (String) operHdrFtrMap.get("STEP_NO");

			if("HEAD".equals(operHdrFtr)) {

				String title = SoluminaMigrationDaoPW.getOperationTitle(src_plan_id, src_oper_key);

				operHdrFtrMap.replace("STEP_NO", MigrationVariablesPW.STEP_TEN);
				operHdrFtrMap.replace("STEP_TITLE", title);

				stepList.add(0, operHdrFtrMap);
			}
		}
		return stepList;

	} //end addHeaderToStepList()

} //end Class SiteSpecificOKC
