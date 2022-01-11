/*
* This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    PlanMapperImplPW.java
* 
*  Created: 2019-01-24
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2019-01-24 	XCS4331		    Initial Release XXXXXXX
* 2019-01-31 	XCS4331		    fixed mapping issue of task parts into xml tags <AllocatedResources>
* 2020-12-21    Fred Ettefagh   defect 1843 added code to select non obsolete task groups when sending our sync routing 
*/
package com.ibaset.solumina.planning.plan.integration.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.openapplications.oagis.x9.AllocatedResourcesType;
import org.openapplications.oagis.x9.InventoryAllocationType;
import org.openapplications.oagis.x9.OperationType;
import org.openapplications.oagis.x9.RoutingType;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.solumina.planning.plan.Plan;
import com.ibaset.solumina.planning.plan.integration.PlanMapper;
import com.ibaset.solumina.planning.plan.integration.impl.PlanMapperImpl;
import com.ibaset.solumina.sfcore.application.IMessage;


public abstract class PlanMapperImplPW extends ImplementationOf<PlanMapperImpl> implements PlanMapper{ 
	
	@Reference private PlanMapperDaoImplPW planMapperDaoImplPW;

	@Reference private IMessage message = null; 

	
	@SuppressWarnings("rawtypes")
	public void mapOutboundPlan(RoutingType routingType, Plan plan){
    	
        if (plan != null) {

        	Super.mapPlanToXml(plan,routingType);
    		Map planDescMap  = planMapperDaoImplPW.selectSfplPlanDescMap(plan.getPlanId(), plan.getPlanUpdtNo());
    		
    		String planType = (String)planDescMap.get("PLAN_TYPE");
    		
    		if (StringUtils.equalsIgnoreCase(planType, "REPAIR")){
    			
    			addRepairPlanTaskXmlTags(routingType,plan);
    		}
       }
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addRepairPlanTaskXmlTags(RoutingType routingType, Plan plan) {
		
		List<Map> sforSfplPlanSubjectList =  planMapperDaoImplPW.selectSforSfplPlanSubject(plan.getPlanId(), plan.getPlanUpdtNo(),"N");
		
		if (sforSfplPlanSubjectList!=null && !sforSfplPlanSubjectList.isEmpty()){
			
			List<OperationType> operationTypeList = routingType.getOperationList();
			
			for (Map planSubjectMap : sforSfplPlanSubjectList) {
				
				Number subjectNumber  = (Number)  planSubjectMap.get("SUBJECT_NO");
				String subjectTitle = (String)  planSubjectMap.get("TITLE");
				String authority = (String)  planSubjectMap.get("AUTHORITY");
				String obsoleteFlag = (String)  planSubjectMap.get("OBSOLETE_RECORD_FLAG");
				
				//System.out.println("obsoleteFlag = " + obsoleteFlag);
				
				OperationType operXml = OperationType.Factory.newInstance();
		         
		        operXml.addNewDocumentID().addNewID().setStringValue(subjectNumber.toString());
		        if (StringUtils.isNotBlank(subjectTitle)){
		        	operXml.addNewDescription().setStringValue(subjectTitle);	
		        }
		        
		        if (StringUtils.isNotBlank(authority)){
		        	operXml.addNewNote().setStringValue(authority);	
		        }
		        
		        operXml.addNewType().setStringValue("TASK_GROUP");
		        
		        List<Map>  sforSfplPlanSubjectPartList = planMapperDaoImplPW.selectSforSfplPlanSubjectPart(plan.getPlanId(), plan.getPlanUpdtNo(), subjectNumber);
		        if (sforSfplPlanSubjectPartList!=null && !sforSfplPlanSubjectPartList.isEmpty()){
		        	
		        	AllocatedResourcesType allocatedResourcesType =  operXml.addNewQualifiedResource().addNewAllocatedResources();
					for (Map planSubjectPartMap : sforSfplPlanSubjectPartList) {
						
						String partNumber  = (String) planSubjectPartMap.get("PART_NO");
						if (StringUtils.isBlank(partNumber)){
							message.raiseError("MFI_20", "part number cannot be null");
						}
						InventoryAllocationType inventoryAllocationType = allocatedResourcesType.addNewInventoryAllocation();
				        inventoryAllocationType.addNewItem().addNewItemID().addNewID().setStringValue(partNumber);
					}
		        }    			        
				operationTypeList.add(operXml);
			}
		}
	}
}

