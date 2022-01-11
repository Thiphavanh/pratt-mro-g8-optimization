/*
*  This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017,2020.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    OperationSfplImplPW.java
*  
*  PW_SMRO_eWORK_202-Order-Creation
* 
*  Created: 2018.10.01
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date             Who            Description
*  -----------    ------------     ---------------------------------------------------
*  2018-10-01     Fred Ettefagh   Initial Release, Defect 980 Cannot Create Suborder due to supNet subNet and conf Numbers
*  2020-07-31     John DeNinno    defect 1413 add updateCommodityJurisdiction so that the banner appears correct
*/

package com.pw.solumina.sfpl.application.impl;

import java.util.List;
import java.util.Map;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.pw.common.utils.CommonUtilsPW;
import com.pw.solumina.sfpl.dao.impl.OrderCreateDaoPW;
import com.pw.solumina.sfwid.dao.impl.OrderOperConfirmationDao;
import com.pw.solumina.sfwid.impl.OrderOperConfirmationCheck;
import com.pw.common.dao.CommonDaoPW;

public abstract class OperationSfplImplPW extends ImplementationOf<com.ibaset.solumina.sfpl.application.IOperation> implements com.ibaset.solumina.sfpl.application.IOperation{

	// Plan sfpl IOperation extension code
	
	@Reference	private CommonUtilsPW commonUtilsPW;
	
	@Reference	private CommonDaoPW commonDaoPW; //defect 1413
	
	@Reference 	private IMessage message = null;
	
	@Reference 	private OrderOperConfirmationDao orderOperConfirmationDao;
	
	@Reference 	private OrderCreateDaoPW orderCreateDaoPW;
	
	@Reference 	private OrderOperConfirmationCheck orderOperConfirmationCheck;
	
	// defect 980
	public void copyOperationToWid( String fromPlanId,
									Number fromPlanVersion,
									Number fromPlanRevision,
									Number fromPlanAlterations,
									Number fromOperationKey,
									String toOrderId,
									Number toOperationKey,
									String toOperationNumber,
									String copyComponentParts,
									String toOperationTitle,
									String calledFrom,
									String[] serialList,
									String lotNumber,
									Number sequenceNumber,
									String stdOperCopyType){
		
		            //planning oper copy to wid
					Super.copyOperationToWid( fromPlanId,
											  fromPlanVersion,
											  fromPlanRevision,
											  fromPlanAlterations,
											  fromOperationKey,
											  toOrderId,
											  toOperationKey,
											  toOperationNumber,
											  copyComponentParts,
											  toOperationTitle,
											  calledFrom,
											  serialList,
											  lotNumber,
											  sequenceNumber,
											  stdOperCopyType);
			
					String pwOrderType = commonUtilsPW.getOrderTypePW(toOrderId);
					
					if ("OVERHAUL".equalsIgnoreCase(pwOrderType)|| 
						"SUPPLEMENTAL".equalsIgnoreCase(pwOrderType) ||
						"SUB ORDER".equalsIgnoreCase(pwOrderType)){
						
						    orderOperConfirmationCheck.updateOrderOperConfFromPlanOperData(toOrderId, 
						    		                                                       fromPlanId, 
						    		                                                       fromPlanVersion, 
						    		                                                       fromPlanRevision,
						    		         											   fromPlanAlterations, 
						    		         											   null, //planUpdtNo
						    		                                                       fromOperationKey, 
						    		                                                       toOperationNumber, 
						    		                                                       toOperationKey);
				    }
	}
//begin defect 1413
	@Override
	public void copyPlanOperation(String fromPlanId, Number fromPlanVersion, Number fromPlanRevision,
			Number fromPlanAlterations, String fromOperationNo, String toPlanId, Number toPlanVersion,
			Number toPlanRevision, Number toPlanAlterations, String toOperationNo, String partsCopyFlag,
			String newOperationKeyFlag, String toOperationTitle, String calledFrom, Number newOperationKey,
			Number toSequenceNumber, String latestStandardOperationRevisionFlag, String stdOperCopyType,
			Map<String, Object> operationInfoMap, List<Map<String, Object>> operationPrecedenceList,
			Map operationNumberMap, List toPlanHeaderInfo, List fromPlanHeaderInfo) {
		
		
		Super.copyPlanOperation(fromPlanId, fromPlanVersion, fromPlanRevision, fromPlanAlterations, fromOperationNo, toPlanId, toPlanVersion, toPlanRevision, toPlanAlterations, toOperationNo, partsCopyFlag, newOperationKeyFlag, toOperationTitle, calledFrom, newOperationKey, toSequenceNumber, latestStandardOperationRevisionFlag, stdOperCopyType, operationInfoMap, operationPrecedenceList, operationNumberMap, toPlanHeaderInfo, fromPlanHeaderInfo);
		commonDaoPW.updateCommodityJurisdiction(toPlanId);
	} //end defect 1413

	 
}
