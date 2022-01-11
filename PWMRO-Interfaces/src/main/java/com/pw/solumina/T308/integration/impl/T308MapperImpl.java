/*
 * This unpublished work, first created in 2019 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2019.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    T308MapperImpl.java
 * 
 *  Created: 2019-08-01
 * 
 *  Author:  Raeann Thorpe
 * 
 *  Revision History
 * 
 *  Date      	Who                	Description
 *  ----------	---------------	---------------	---------------------------------------------------
 * 2019-08-01 	Raeann Thorpe   Initial Release for PW_SMRO_TR_308
 * 2019-09-20   Bahram J.       SMRO_TR_308, changed xml to use synWorkOrder instead of syncRecord
 * 2019-09-23   Fred Ettefagh   SMRO_TR_308, added order work loc to outbound xml file
 * 2019-09-23   Fred Ettefagh   SMRO_TR_308, changed xml mapping to send partNo instead of planNo.
 */
package com.pw.solumina.T308.integration.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.openapplications.oagis.x9.ActionExpressionType;
import org.openapplications.oagis.x9.ActualResources;
import org.openapplications.oagis.x9.DocumentIDType;
import org.openapplications.oagis.x9.IdentifierType;
import org.openapplications.oagis.x9.InventoryActualType;
import org.openapplications.oagis.x9.ItemIDType;
import org.openapplications.oagis.x9.ItemInstanceType;
import org.openapplications.oagis.x9.LocationType;
import org.openapplications.oagis.x9.LotType;
import org.openapplications.oagis.x9.OperationType;
import org.openapplications.oagis.x9.QualifiedResourceType;
import org.openapplications.oagis.x9.QuantityType;
import org.openapplications.oagis.x9.SequencedIDsType;
import org.openapplications.oagis.x9.SerializedLotType;
import org.openapplications.oagis.x9.StatusType;
import org.openapplications.oagis.x9.SyncWorkOrderDocument;
import org.openapplications.oagis.x9.WorkOrderHeaderType;
import org.openapplications.oagis.x9.WorkOrderType;
import com.ibaset.common.Reference;
import com.ibaset.solumina.integration.common.ActionCode;
import com.ibaset.solumina.integration.common.MappingAction;
import com.pw.common.dao.CommonDaoPW;
import com.pw.solumina.T308.integration.impl.T308Event;

public class T308MapperImpl 
{
	
	@Reference OrderReleaseInterfaceDao orderReleaseDao;
	@Reference CommonDaoPW commonDaoPW;
	
	public void map(MappingAction[] outboundActions, SyncWorkOrderDocument syncWorkOrderDocument)
	{
		for (int i = 0; i < outboundActions.length; i++)
		{
			MappingAction mappingAction = outboundActions[i];
			ActionCode actionCode = mappingAction.getActionCode();
			T308Event t308Event = (T308Event)mappingAction.getSourceObject();             
			setActionCode(actionCode, syncWorkOrderDocument);
			WorkOrderType workOrderType = syncWorkOrderDocument.getSyncWorkOrder().getDataArea().addNewWorkOrder();
			mapOutboundRecord(workOrderType, t308Event);
		}
	}

	protected void setActionCode(ActionCode actionCode,SyncWorkOrderDocument syncWorkOrderDocument)
	{
		String actionEx = "/SyncWorkOrder/DataArea/WorkOrder[1]";

		ActionExpressionType actionExpression = syncWorkOrderDocument.getSyncWorkOrder()
				                                                   .getDataArea()
				                                                   .getSync()
				                                                   .getActionCriteriaArray(0)
				                                                   .addNewActionExpression();
		actionExpression.setActionCode(actionCode.toString());
		actionExpression.setStringValue(actionEx);
	
	}

	public WorkOrderHeaderType mapOutboundRecord(WorkOrderType workOrderType, T308Event t308Event)
	{
		
		 WorkOrderHeaderType workOrderHeaderType = workOrderType.addNewWorkOrderHeader();
		 {
			 DocumentIDType documentIDType = workOrderHeaderType.addNewDocumentID();
        	 IdentifierType identifierType = documentIDType.addNewID();
        	 identifierType.setStringValue(t308Event.getWorkOrderNumber());
         }
         
		 {
			 QuantityType quantityType = workOrderHeaderType.addNewOrderQuantity();
			 quantityType.setStringValue(t308Event.getOrderQty().toString());
			 
		 }
         
		 {
			 ItemInstanceType itemInstanceType = workOrderHeaderType.addNewItemInstance();
			 ItemIDType itemIDType = itemInstanceType.addNewItemID();
        	 IdentifierType identifierType = itemIDType.addNewID();
        	 identifierType.setStringValue(t308Event.getOrderPartNo());
         }

		 {
			 // Fred E.
			 // 12/05/2019  
			 // after our testing, SAP needs to know the part no for this xml tag
			 // planNo is not used in SAP at all
			 // partNo will be send in two xml tags for now
			 DocumentIDType documentIDType = workOrderHeaderType.addNewAlternateDocumentID();
			 IdentifierType identifierType = documentIDType.addNewID();
        	 identifierType.setStringValue(t308Event.getOrderPartNo());
        	 
		 }
		 
		 {
			 LocationType locationType =  workOrderHeaderType.addNewSite();
			 IdentifierType identifierType = locationType.addNewID();
			 identifierType.setStringValue(t308Event.getWorkLocation());
		 }
		 
		 {
			 StatusType  statusType = workOrderHeaderType.addNewStatus();
			 statusType.addNewCode().setStringValue("REL_SEND_TO_SAP");
			 statusType.addNewReason().setStringValue("");
		 }
		 
		     // serial number if needed
		 {
			 String lotNo = "N/A";
			 List<Map> lotnoList = commonDaoPW.selectSfwidLotDescList(t308Event.getOrderId());
			 if (lotnoList!=null && !lotnoList.isEmpty()){
				 Map map =(Map)lotnoList.get(0);
				 lotNo= (String) map.get("LOT_NO");
			 }
			 SerializedLotType serializedLotType = workOrderHeaderType.addNewSerializedLot();
			 LotType lotType = serializedLotType.addNewLot();
			 SequencedIDsType SequencedCodeType = lotType.addNewLotIDs();
			 SequencedCodeType.addNewID().setStringValue(lotNo);
			 
			 String SerialNo = "N/A";
			 IdentifierType identifierType = serializedLotType.addNewSerialNumber();
			 List<Map> serialNoList =commonDaoPW.selectSfwidSerialDescList(t308Event.getOrderId());
			 if (serialNoList!=null && !serialNoList.isEmpty()){
				 for (int i=0; i<serialNoList.size(); i++) {
						Map  map = (Map) serialNoList.get(i);
						serializedLotType.addNewSerialNumber().setStringValue((String)map.get("SERIAL_NO"));
				 }
			 }else{
				 identifierType.setStringValue(SerialNo);
			 }
		 }
		 
		 
		 List<Map> operListt = orderReleaseDao.selectOrderIncludedOperations(t308Event.getOrderId());
		 if (operListt!=null && !operListt.isEmpty()){
			
		 
			 for (int i=0; i<operListt.size(); i++) {
					Map  sfwidOperDescMap = (Map) operListt.get(i);
					OperationType operationType = workOrderType.addNewWorkOrderOperation();
					operationType.addNewType().setStringValue("OPERATION");
					operationType.addNewDocumentID().addNewID().setStringValue((String)sfwidOperDescMap.get("OPER_NO"));
					operationType.addNewDescription().setStringValue((String)sfwidOperDescMap.get("OPER_TITLE"));
					operationType.addNewSite().addNewID().setStringValue((String)sfwidOperDescMap.get("ASGND_WORK_CENTER"));
					Number duration = (Number)sfwidOperDescMap.get("DURATION");
				    operationType.addNewCostAmount().setBigDecimalValue(new BigDecimal(duration.intValue()));

				    List operItemList = orderReleaseDao.selectOrderOperationParts(t308Event.getOrderId(), (String)sfwidOperDescMap.get("OPER_NO"));
				    if (operItemList!=null && !operItemList.isEmpty()){
					    QualifiedResourceType qualifiedResourceType = operationType.addNewQualifiedResource();
					    ActualResources actualResources =qualifiedResourceType.addNewActualResources();
				    	for (int j=0; j<operItemList.size(); j++) {
				    		Map  sfwidOperItemsMap = (Map) operItemList.get(j);
						    InventoryActualType inventoryActualType = actualResources.addNewInventoryActual();
						    inventoryActualType.addNewItem().addNewItemID().addNewID().setStringValue((String)sfwidOperItemsMap.get("PART_NO"));
						    Number plndItemQtyNumber =(Number)sfwidOperItemsMap.get("PLND_ITEM_QTY");
						    inventoryActualType.addNewQuantity().setBigDecimalValue(new BigDecimal(plndItemQtyNumber.intValue()));
					    }
				    }
			 }
		}
		 
		
		 List orderTasksLiet = orderReleaseDao.selectOrderSubjectsList(t308Event.getOrderId(), "INCLUDED");
		 if (orderTasksLiet!=null && !orderTasksLiet.isEmpty()){
			 
			 for (int i=0; i<orderTasksLiet.size(); i++) {
					Map  sfwidOrderTaskMap = (Map) orderTasksLiet.get(i);
					OperationType operationType = workOrderType.addNewWorkOrderOperation();
					operationType.addNewType().setStringValue("TASK_GROUP");
					Number subjectNo = (Number)sfwidOrderTaskMap.get("SUBJECT_NO");
					operationType.addNewDocumentID().addNewID().setStringValue(subjectNo.toString());
					operationType.addNewDescription().setStringValue((String)sfwidOrderTaskMap.get("TITLE"));
			 }
		 }
         
		return workOrderHeaderType;
	}
}
