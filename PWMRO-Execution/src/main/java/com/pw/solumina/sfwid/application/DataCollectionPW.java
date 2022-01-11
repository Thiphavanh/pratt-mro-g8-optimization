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
 *  File:    DataCollectionPW.java
 * 
 *  Created: 2020-11-18
 * 
 *  Author:  Scott Edgerly
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2018-11-18		S. Edgerly		Initial Release SMRO_WOE_206 - Defect 941 - SCR001: Split Data collections into two different privs.

 */
package com.pw.solumina.sfwid.application;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.ibaset.common.Reference;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IParse;
import com.ibaset.solumina.sfpl.application.ICDCJep;
import com.ibaset.solumina.sfpl.application.IStep;
import com.ibaset.solumina.sfwid.application.IDataCollection;
import com.ibaset.solumina.sfwid.application.IOperation;
import com.ibaset.solumina.sfwid.application.impl.OperationDataCollectionImpl;
import com.ibaset.solumina.sfwid.dao.IGroupJobDao;
import com.ibaset.solumina.sfwid.dao.IOperationDao;

public class DataCollectionPW {
	@Reference private IDataCollection dc;
	@Reference private IOperation operation;
	@Reference private IOperationDao operationDao;
	@Reference private IParse parse;
	@Reference private IGroupJobDao groupJobDao;
	@Reference private ICDCJep cdcJep;
	@Reference private IStep step;
	@Reference private IMessage message;
	@Reference private com.pw.solumina.sffnd.dao.UserDaoPW userDaoPW;
	
	public void executeDataCollectionMatrixPre(String dataCollectionType, String orderId, Number operationKey,
            	Number stepKey, String dataCollectionList, String referenceId, String fromSidePanelFlag, String calledFrom,
            	String operationStatus, String orderControlType, String mustIssuePartsFlag, String groupJobNo,
            	String consolidatedQueryType, String navLevelFieldName, String dcStatusDisp) {
	
		validateUserPrivs(ContextUtil.getUsername(), orderId, operationKey, 
				stepKey, dataCollectionList, referenceId, orderControlType,
				groupJobNo, consolidatedQueryType, navLevelFieldName,
				dcStatusDisp);
		
		dc.executeDataCollectionMatrixPre(dataCollectionType, orderId, 
				operationKey, stepKey, dataCollectionList, fromSidePanelFlag, 
				calledFrom, operationStatus, orderControlType, 
				mustIssuePartsFlag, groupJobNo);
	}
	
	
	private void validateUserPrivs(String userId, String orderId, 
			Number operationKey, Number stepKey, String dataCollectionList, 
			String referenceId, String orderControlType, String groupJobNo,
			String consolidatedQueryType, String navLevelFieldName, 
			String dcStatusDisp){
		
		String[] list = dataCollectionList.split(";");

		if(list.length > 1 && !"Complete".equals(dcStatusDisp)){
			//if any of the data collections are marked as complete, then we need to check if user has the correct privilege.
			dcStatusDisp = getGroupedDataCollectionDCStatus(dcStatusDisp, 
					orderId, operationKey, stepKey, referenceId, 
					orderControlType, consolidatedQueryType, navLevelFieldName,
					groupJobNo);
		}
		
		//Validate that user has privilege to update Completed data collections
		if("Complete".equals(dcStatusDisp)){
			String requiredPriv = "PW_DATCOL_UPDATE";
			String substitution = "the " + requiredPriv;
			if(!userDaoPW.hasPriv(userId, requiredPriv)){
				message.raiseError("PRIVILEGE_VIOLATION", substitution);
			}
		}
	}
	
	
	private String getGroupedDataCollectionDCStatus(String dcStatusDisp, 
			String orderId, Number operationKey, Number stepKey, 
			String referenceId, String orderControlType, 
			String consolidatedQueryType, String navLevelFieldName, 
			String groupJobNo){
		/*We cannot trust that the dcStatusDisp value is valid when the 
		 *"Collect" button is clicked and must check each individual Data
		 *Collection's DC_STATUS_DISP in a referenced table/grid .
		 */
		//Initialize implementation
		OperationDataCollectionImpl odcl = new OperationDataCollectionImpl();
		odcl.setOperation(operation);
		odcl.setOperationDao(operationDao);
		odcl.setParse(parse);
		odcl.setCdcJep(cdcJep);
		odcl.setStep(step);
		
		//return list of all data collections within a given RefId.
		List list = odcl.getOperationDataCollection(orderId, operationKey, stepKey, referenceId, orderControlType, consolidatedQueryType, navLevelFieldName, groupJobNo);
		ListOrderedMap lom;
		
		for(int i = 0; i < list.size(); i++){
			lom = (ListOrderedMap) list.get(i);
			dcStatusDisp = (String) lom.get("DC_STATUS_DISP");
			if("Complete".equals(dcStatusDisp)){
				break;
			}
		}
		return dcStatusDisp;
	}
}
