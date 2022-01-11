/*
 * This unpublished work, first created in 2017 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2021.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    SfplSubjectDaoImplPW.java
 * 
 *  Created: 2021-01-21
 * 
 *  Author:  X007084
 * 
 *  Revision History
 * 
 *  Date      	Who                	Description
 *  ----------	---------------	---------------	---------------------------------------------------
 * 2021-01-21 	John DeNinno		Initial Release defect 1829 part number not appearing in Sfor_Sfpl_Plan_Subject_Part
 * */
package com.pw.solumina.sfor.dao.impl;

import static com.ibaset.common.SoluminaConstants.COPY_TASK_GROUP_PART;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.h2.util.StringUtils;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.pw.common.dao.CommonDaoPW;
import com.pw.solumina.sfor.dao.TaskGroupsDaoPW;
import com.ibaset.solumina.sfor.dao.ISfplPlanDao;
import com.ibaset.solumina.sfor.dao.ISfplSubjectDao;
import com.pw.solumina.sfor.dao.TaskGroupsDaoPW;
import com.ibaset.solumina.planning.plan.integration.impl.PlanMapperDaoImplPW;

public abstract class SfplSubjectDaoImplPW extends ImplementationOf<ISfplSubjectDao> implements ISfplSubjectDao {

	@Reference
	TaskGroupsDaoPW taskGroupsDaoPW;
	@Reference	
	CommonDaoPW  commonDaoPW;
	@Reference PlanMapperDaoImplPW planMapperDaoImplPW;
	@Reference  private ExtensionDaoSupport eds;
	@Reference
	private ISfplPlanDao sfplPlanDao; // defect 1829

	@Override
	public void insertPlanSubject(String toPlanId, Number toPlanUpdateNumber, Number toSubjectNumber,
			Number subjectRevision, String userName, String orderId, Number fromSubjectNumber) {


		Super.insertPlanSubject(toPlanId, toPlanUpdateNumber, toSubjectNumber, subjectRevision, userName, orderId, fromSubjectNumber);



		String orderType = "";
		String itemId = "";
		String partNo = "";
		String fromPlanId = "";
		String partChg = "";
		String partTitle = "";

		//defect 1829
		List  orderList = commonDaoPW.selectSfwidOrderDesc(orderId);
		if (orderList.size() ==1){
			Map map = (Map)orderList.get(0);
			orderType = (String) map.get("ORDER_TYPE");
			itemId = (String) map.get("ITEM_ID");
			partNo = (String) map.get("PART_NO");
			partChg = (String) map.get("PART_CHG");
			partTitle = (String) map.get("PART_TITLE");
			fromPlanId = (String) map.get("PLAN_ID");

		}
				
		//begin defect 1829
		if (StringUtils.equals(orderType, "OVERHAUL")) {
			sfplPlanDao.insertPlanSubjectPart(toPlanId,
					toPlanUpdateNumber,
					toSubjectNumber,
					itemId,
					userName,
					COPY_TASK_GROUP_PART,
					partNo, 
					partChg,
					partTitle);
			//end defect 1829
			
			}		

	}



}
