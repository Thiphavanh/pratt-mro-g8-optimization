package com.pw.solumina.sffnd.dao.impl;

/*
* This unpublished work, first created in 2018 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2018.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    StandardOperationDaoPW.java
* 
*  Created: 2018-03-15
* 
*  Author:  X002475
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	------------------------------------------------------------
* 2018-03-15	B. Preston		Defect 50		Initial release -- Limit standard operations by work location. 
* 2020-02-07   G.Rodriguez     Defect 1354 - Copy standard Operations does not bring over security groups
* 2020-08-03    D.Miron         Defect 1578 - Added methods insertDataInSfcoreMmObjectGrp and selectDefaultSecurityGroup
* 2020-07-06    J DeNinno      Defect 1936 - have latsted revision be the latest completed revision
* 2021-12-03    S.Edgerly      Defect 1354 - Copy standard Operations now also inserts records into SFCORE_MM_OBJECT_SEC_GRP post copy
*/

import com.pw.solumina.sffnd.dao.IStandardOperationDaoPW;
import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.solumina.sffnd.dao.IStandardOperationDao;

public abstract class StandardOperationDaoPW extends ImplementationOf<IStandardOperationDao>
		implements IStandardOperationDaoPW, IStandardOperationDao {

	@Reference
	private IStandardOperationDaoPW standardOperationDaoPW = null;

	@Reference 
	private ExtensionDaoSupport eds = null;
	
	// Begin defect 50.
	@Override
	public void insertWorkLocation(String tag, String workLocation) {
		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFCORE_MM_OBJECT ")
				.append("    SET UCF_MMOBJ_VCH1 = ?, ")
				.append("		 SECURITY_GROUP = (SELECT SECURITY_GROUP FROM UTASGI_USER_SEC_GRP_XREF WHERE HR_LOC = ?)")
				.append("  WHERE OBJECT_ID = (SELECT STDOPER_OBJECT_ID FROM SFFND_STDOPER WHERE STDOPER_TAG = ?) ")
				.append("  AND OBJECT_TYPE = 'STDOPER'");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(workLocation);
		params.addParameter(workLocation);
		params.addParameter(tag);

		eds.update(updateSql.toString(), params);
		
	}
	// End defect 50.
	
	// Begin defect 1323.
	@Override
	public String getLocationId(String workLocation) {
		StringBuffer updateSql = new StringBuffer().append(" SELECT LOCATION_ID ")
				.append("  FROM SFFND_WORK_LOC_DEF ")
				.append("  WHERE WORK_LOC = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(workLocation);

		return eds.queryForString(updateSql.toString(), params);
	} 
	
	@Override
	public void insertLocationId(String locId, String planId) {
		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFPL_OPERATION_DESC ")
				.append("    SET PLND_LOCATION_ID = ? ")
				.append("  WHERE PLAN_ID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(locId);
		params.addParameter(planId);

		eds.update(updateSql.toString(), params);
		
	}
	
	//Begin Defect 1354
	
	public void  updateSecGrpWorkLoc(String newObjectId, String cacheWorkLoc, String cacheSecurityGroup){
		
		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFCORE_MM_OBJECT ")
				   .append("    SET UCF_MMOBJ_VCH1 = ?, ")
				   .append("    SECURITY_GROUP = ? ")
				   .append("  WHERE object_id = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(cacheWorkLoc);
		params.addParameter(cacheSecurityGroup);
		params.addParameter(newObjectId);

		eds.update(updateSql.toString(), params);
		
		
	}
	
	//Defect 1354 - 2021-12-03
	public void insertStandardOperationSecurityGroup(String newObjectId, 
													 String standardOperationType, 
													 String standardOperationTag, 
													 Integer standardOperationRevision, 
													 String securityGroup,
													 String username){
		
		StringBuffer selectSQL = new StringBuffer().append(" SELECT * ")
				   .append("   FROM SFCORE_MM_OBJECT_SEC_GRP ")
				   .append("  WHERE OBJECT_TYPE = ? ")
				   .append("    AND OBJECT_TAG = ? ")
				   .append("    AND OBJECT_REV = ? ")
				   .append("    AND SECURITY_GROUP = ? ")
				   .append("    AND OBJECT_ID = ? ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(standardOperationType);
		params.addParameter(standardOperationTag);
		params.addParameter(1);
		params.addParameter(securityGroup);
		params.addParameter(newObjectId);
		
		//if no record exists, insert.
		if(eds.queryForList(selectSQL.toString(), params).isEmpty()){
		
		StringBuffer insertSql = new StringBuffer().append(" INSERT INTO SFCORE_MM_OBJECT_SEC_GRP ")
							   .append("   (OBJECT_TYPE, OBJECT_TAG, OBJECT_REV, SECURITY_GROUP, UPDT_USERID, OBJECT_ID)")
							   .append(" VALUES ")
							   .append("   (?, ")
							   .append("    ?, ")
							   .append("    ?, ")
							   .append("    ?, ")
							   .append("    ?, ")
							   .append("    ?) ");
		
		params = new ParameterHolder();
		params.addParameter(standardOperationType);
		params.addParameter(standardOperationTag);
		params.addParameter(1);
		params.addParameter(securityGroup);
		params.addParameter(username);
		params.addParameter(newObjectId);
		
		eds.insert(insertSql.toString(), params);
		}
	}
	//End Defect 1354 - 2021-12-03

	
	public String selectWorkLocation(String standardOperationObjectId){
		
		StringBuffer selectSQL = new StringBuffer().append("SELECT ")
	            .append("    ucf_mmobj_vch1 ")
	            .append("FROM ")
	            .append("    SFCORE_MM_OBJECT ")
	            .append("WHERE ")
	            .append("    object_id = ?");
		
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(standardOperationObjectId);
		
		String workLocation = eds.queryForString(selectSQL.toString(), params);
	
		return workLocation;
		
	}
	
	public String selectSecurityGroup(String standardOperationObjectId){
		
		StringBuffer selectSQL = new StringBuffer().append("SELECT ")
	            .append("    security_group ")
	            .append("FROM ")
	            .append("    SFCORE_MM_OBJECT ")
	            .append("WHERE ")
	            .append("    object_id = ?");
		
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(standardOperationObjectId);
		
		String securityGroup = eds.queryForString(selectSQL.toString(), params);
	
		return securityGroup;
		
	}
	
	
	public String selectStdOperPlan(String standardOperationTag){
		
		StringBuffer selectSQL = new StringBuffer().append("SELECT ")
	            .append("    STDOPER_PLAN_ID ")
	            .append("FROM ")
	            .append("    SFFND_STDOPER ")
	            .append("WHERE ")
	            .append("    STDOPER_TAG = ?");
		
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(standardOperationTag);
		
		String planId = eds.queryForString(selectSQL.toString(), params);
	
		return planId;
	}
	
	
	public String selectObjectIdFromSFFND_STDOPER(String newPlanId){
		
		StringBuffer selectSQL = new StringBuffer().append("SELECT ")
	            .append("    STDOPER_OBJECT_ID ")
	            .append("FROM ")
	            .append("    SFFND_STDOPER ")
	            .append("WHERE ")
	            .append("    STDOPER_PLAN_ID = ?");
		
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(newPlanId);
		
		String objectId = eds.queryForString(selectSQL.toString(), params);
	
		return objectId;
	}
	
	//End Defect 1354
	
	// Defect 1578
	public void insertDataInSfcoreMmObjectGrp(String standardOperationType, 
			                                  String standardOperationTag, 
			                                  String planRevision, 
			                                  String securityGroup)
	{
		StringBuffer insertSql = new StringBuffer().append(" INSERT INTO sfcore_mm_object_sec_grp ")
				   .append("   (object_type, ")
				   .append("    object_tag, ")
				   .append("    object_rev, ")
				   .append("    security_group, ")
				   .append("    updt_userid) ")
				   .append(" VALUES ")
				   .append("   (?, ?, ?, ?, ?) ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(standardOperationType);
		params.addParameter(standardOperationTag);
		params.addParameter(planRevision);
		params.addParameter(securityGroup);
		params.addParameter(ContextUtil.getUsername());
		
		eds.update(insertSql.toString(), params);
	}
	
	// Defect 1578
	public String selectDefaultSecurityGroup(String workLocationId)
	{		
		String securityGroup = null;
		
		StringBuffer selectSql = new StringBuffer().append("SELECT SECURITY_GROUP ")
				                                   .append(" FROM UTASGI_USER_SEC_GRP_XREF ")
				                                   .append(" WHERE HR_LOC = (SELECT WORK_LOC FROM SFFND_WORK_LOC_DEF WHERE LOCATION_ID = ? )");
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(workLocationId);
		
		try
		{
			securityGroup = eds.queryForString(selectSql.toString(), parameters);
		}
		catch(Exception e)
		{
			securityGroup = "NONE" ;
		}
		
		return securityGroup;  
	}
	
	//defect 1936
	@Override
	public String selectStandardOperationLatestRevObjectId(String operationTag)
    {
    	System.out.println("Inside selectStandardOperationLatestRevObjectId");
    	
        StringBuffer select = new StringBuffer().append("SELECT STDOPER_OBJECT_ID ")
        										.append("  FROM SFFND_STDOPER a ")
        										.append(" WHERE STDOPER_TAG = ? ")
        										.append(" and  a.stdoper_rev = (select max(stdoper_rev) from sffnd_stdoper x where x.stdoper_tag = a.stdoper_tag and x.status = 'COMPLETE') ");

 
        ParameterHolder params = new ParameterHolder();
        params.addParameter(operationTag);
       // params.addParameter(operationTag);
        
        System.out.println(select.toString());
        
        String query = eds.queryForString(select.toString(), params);
        return query;
    }
}
