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
*  File:    StandardOperationImplPW.java
* 
*  Created: 2017-11-20
* 
*  Author:  XCS4071
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	------------------------------------------------------------
* 2017-11-20 	N.Gnassounou	Initial Release -Defect 244 : Do not allow apostrophe in STDOPER_TAG field
* 2018-03-15	B. Preston		Defect 50		Limit standard operations by work location. 
* 2019-07-10    D.Miron         Defect 1356 - Added method copyStandardOperationPlan.
* 2020-02-07    G.Rodriguez     Defect 1354 - Copy standard Operations does not bring over security groups
* 2020-04-17    J DeNinno	Defect 1601 fix how planId is updated
* 2020-08-03    D.Miron         Defect 1578 - Insert Security Group in table SFCORE_MM_OBJECT_SEC_GRP when creating std op
* 2021-12-03    S.Edgerly      Defect 1354 - Copy standard Operations now also inserts records into SFCORE_MM_OBJECT_SEC_GRP post copy
*/

package com.pw.solumina.sffnd.application.impl;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.OutputParameter;
import com.ibaset.common.Reference;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IStandardOperation;
import com.ibaset.solumina.sffnd.application.impl.StandardOperationImpl;
import com.ibaset.solumina.sffnd.dao.IStandardOperationDao;

import com.pw.solumina.sffnd.application.IStandardOperationPW;
import com.pw.solumina.sffnd.dao.IStandardOperationDaoPW;
import com.pw.solumina.sfpl.dao.PlanDaoPW;

public abstract class StandardOperationImplPW extends ImplementationOf<IStandardOperation>
		implements IStandardOperationPW, IStandardOperation, IStandardOperationDaoPW {

	@Reference
	protected IMessage message = null;

	@Reference
	private IStandardOperationDao standardOperationDao = null;

	@Reference
	private IStandardOperationDaoPW standardOperationDaoPW = null;

	@Reference
	private IStandardOperationPW standardOperationPW = null;

	@Reference
	private PlanDaoPW planDaoPW = null;
	
	
    private static String workLocation; //defect 1601
    
	
	public void createStandardOperation(String tag, String description, String releasePackage,
			String standardOperationType, String workFlow, String commodityJurisdiction, String commodityClassification,
			String associateChange)
	
	{
		int apostosphe = tag.indexOf("'");
		if (apostosphe > 0 || apostosphe == 0) {
			message.raiseError("MFI_20", "Special characters such as Apostrophe (') is not allowed in tag field");
		}

		Super.createStandardOperation(tag, description, releasePackage, standardOperationType, workFlow,
				commodityJurisdiction, commodityClassification, associateChange);
	}

	// Begin defect 50.
	public void createStandardOperation(String tag, String description, String releasePackage,
			String standardOperationType, String workFlow, String commodityJurisdiction, String commodityClassification,
			String associateChange, String workLocation, String planId) {
	
		
		//defect 1601
		setWorkLocation(workLocation);
		
		// Call original method.
		createStandardOperation(tag, description, releasePackage, standardOperationType, workFlow,
				commodityJurisdiction, commodityClassification, associateChange);
		
		// Insert work location.
		standardOperationDaoPW.insertWorkLocation(tag, workLocation);
		

		/* defect 1601 
		//Insert location id
		String locationId = standardOperationDaoPW.getLocationId(workLocation);
		
		standardOperationDaoPW.insertLocationId(locationId, planId);
		
		end defect 1601 */
	}
	// End defect 50.
	
	
	
	
	
	// Defect 1356
    public void copyStandardOperationPlan(String standardOperationObjectId,
                                          String standardOperationTag,
                                          String standardOperationDescription,
                                          String folder,
                                          String standardOperationType,
                                          String fromPartNumber,
                                          String fromPartChange,
                                          OutputParameter planId,
                                          OutputParameter planVersion,
                                          OutputParameter planRevision,
                                          OutputParameter planAlterations,
                                          OutputParameter invokeToolMode,
                                          String releasePackage,
                                          String workFlow,
                                          String commodityJurisdiction,
                                          String commodityClassification, 
                                          String associateChange)
    {
    	
    	Super.copyStandardOperationPlan(standardOperationObjectId,
                                        standardOperationTag,
                                        standardOperationDescription,
                                        folder,
                                        standardOperationType,
                                        fromPartNumber,
                                        fromPartChange,
                                        planId,
                                        planVersion,
                                        planRevision,
                                        planAlterations,
                                        invokeToolMode,
                                        releasePackage,
                                        workFlow,
                                        commodityJurisdiction,
                                        commodityClassification, 
                                        associateChange);
    	
    	String planIdStr = planId.toString();
    	String planVersionStr = planVersion.toString();
    	String planRevisonStr = planRevision.toString();
    	String planAlterationsStr = planAlterations.toString();
    	
    	planDaoPW.updatePlanPPVQuanity(planIdStr,
    			                       planVersionStr, 
    			                       planRevisonStr,
    			                       planAlterationsStr, 
    			                       1);
    	
    
    	
    	
    }
    
    
  
	//begin defect 1601
	@Override
	public String createStandardOperationPlan(String tag, String description, String folder, Number[] planVersion,
			Number[] planRevision, Number[] planAlterations, String standardOperationType, String releasePackage,
			String workFlow, String commodityJurisdiction, String commodityClassification) {
		// TODO Auto-generated method stub
		
		
		String planId = Super.createStandardOperationPlan(tag, description, folder, planVersion, planRevision,
				planAlterations, standardOperationType, releasePackage, workFlow, commodityJurisdiction, commodityClassification);
	
		String locationId = standardOperationDaoPW.getLocationId(getWorkLocation());
		
		standardOperationDaoPW.insertLocationId(locationId, planId);
		
		// Begin Defect 1578
    	String stdOperObjectId = standardOperationDaoPW.selectObjectIdFromSFFND_STDOPER(planId); //Defect 1354
    	String securityGroup = standardOperationDaoPW.selectDefaultSecurityGroup(locationId);    //Defect 1354
    	
    	if(!"NONE".equals(securityGroup))
    	{
    		standardOperationDaoPW.insertDataInSfcoreMmObjectGrp("STDOPER", 
    				tag, 
    				Integer.toString((Integer) planRevision[0]),  
    				securityGroup);
    	}
    	// End Defect 1578
		
		return planId;
	}
   //end defect 1601
	

	
	//Begin defect 1354
    public void copyStandardOperationPlan2(String standardOperationObjectId,
    									   String standardOperationTag,
    									   String standardOperationDescription,
    									   String folder,
    									   String standardOperationType,
    									   String fromPartNumber,
    									   String fromPartChange,
								           OutputParameter planId,
								           OutputParameter planVersion,
								           OutputParameter planRevision,
								           OutputParameter planAlterations,
								           OutputParameter invokeToolMode,
								           String releasePackage,
								           String workFlow,
								           String commodityJurisdiction,
								           String commodityClassification, 
								           String associateChange,
								           String securityGroup,
								           String userId,
								           Number stdOperKey)
    {

    	Super.copyStandardOperationPlan(standardOperationObjectId,
								        standardOperationTag,
								        standardOperationDescription,
								        folder,
								        standardOperationType,
								        fromPartNumber,
								        fromPartChange,
							            planId,
							            planVersion,
								        planRevision,
								        planAlterations,
								        invokeToolMode,
								        releasePackage,
								        workFlow,
								        commodityJurisdiction,
								        commodityClassification, 
								        associateChange);

    	String planIdStr = planId.toString();
    	String planVersionStr = planVersion.toString();
    	String planRevisonStr = planRevision.toString();
    	String planAlterationsStr = planAlterations.toString();

    	planDaoPW.updatePlanPPVQuanity(planIdStr,
    								   planVersionStr, 
    								   planRevisonStr,
    								   planAlterationsStr, 
    								   1);

    	String cacheWorkLoc = standardOperationDaoPW.selectWorkLocation(standardOperationObjectId);//Defect 1354
    	String cacheSecurityGroup = standardOperationDaoPW.selectSecurityGroup(standardOperationObjectId);//Defect 1354
    	String newPlanId = standardOperationDaoPW.selectStdOperPlan(standardOperationTag);//Defect 1354
    	String newObjectId = standardOperationDaoPW.selectObjectIdFromSFFND_STDOPER(newPlanId);//Defect 1354
    	standardOperationDaoPW.updateSecGrpWorkLoc(newObjectId, cacheWorkLoc, cacheSecurityGroup);//Defect 1354
    	
    	//Defect 1354 - 2021-12-03
    	standardOperationDaoPW.insertStandardOperationSecurityGroup(newObjectId, standardOperationType, standardOperationTag, 1, securityGroup, ContextUtil.getUsername());
    }
    //End defect 1354
    
    //defect 1601 begin
    public void setWorkLocation(String loc) {
    	workLocation = loc;
    }
    
    public String getWorkLocation() {
    	return workLocation;
    }
    //defect 1601 end
    
    
}
