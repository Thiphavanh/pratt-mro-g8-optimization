package com.utas.solumina.sffnd.application.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.ISecurityGroup;
import com.ibaset.solumina.sffnd.dao.ISecurityGroupDao;
import com.ibaset.solumina.sfpl.dao.IPlanDao;
import com.utas.solumina.sffnd.application.IUtasSecurityGroup;
import com.utas.solumina.sffnd.dao.IUtasSecurityGroupDao;

public abstract class UtasSecurityGroupImplExt extends ImplementationOf<ISecurityGroup> implements ISecurityGroup, IUtasSecurityGroup
{
	@Reference
	ISecurityGroupDao securityGroupDao;
	@Reference
	IPlanDao planDao;
	@Reference
	IUtasSecurityGroupDao utasSecurityGroupDao;
	@Reference
	IMessage message;
	
	@Override
	public void insertItemSecurityGroup(String partNumber,
										String securityGroup, 
										String externalErpNo, 
										String externalPlmNo) 
	{
		Super.insertItemSecurityGroup(partNumber, securityGroup, externalErpNo, externalPlmNo);
				
		 // Select the Item Type.
		String itemType = securityGroupDao.selectItemType(partNumber);

		// Check if the Item type is 'TOOL'.
		if (StringUtils.equals(itemType, SoluminaConstants.MACHINE))
		{
			// Update Security Group in Tool.
			securityGroupDao.updateSecurityGroupInTool(partNumber,
													   securityGroup,
													   SoluminaConstants.INSERT);
		}
	}


	@Override
	public void insertWorkPlanSecurityGroup(String planId,
											Number planVersion,
											Number planRevision,
											Number planAlterations, 
											String inheritedSecurityGroupFlag, 
											String securityGroup,
											Number planUpdtNo)
	{
		try
		{
			String planSecurityGroup = planDao.selectPlanDescSecurityGroup(planId, planUpdtNo);
			
			boolean securityGroupExists = isSecurityGroupExists(planId, planUpdtNo,securityGroup);
			
			if(StringUtils.contains(securityGroup, planSecurityGroup)
					&& securityGroupExists)
			{
				ContextUtil.getUser().getContext().set("EXECUTE_METHOD", true);
			}
			else if(!StringUtils.contains(securityGroup, planSecurityGroup)
					&& securityGroupExists)
			{
				ContextUtil.getUser().getContext().set("EXECUTE_METHOD", false);
			}
			else
			{
				ContextUtil.getUser().getContext().set("EXECUTE_METHOD", true);
			}
			ContextUtil.getUser().getContext().set("PLAN_UPDT_NO", planUpdtNo);
			Super.insertWorkPlanSecurityGroup(planId, 											  
											  planVersion, 
											  planRevision, 
											  planAlterations, 
											  inheritedSecurityGroupFlag, 
											  securityGroup,
											  planUpdtNo);
		}
		finally
		{
			ContextUtil.getUser().getContext().set("PLAN_UPDT_NO", null);
			ContextUtil.getUser().getContext().set("EXECUTE_METHOD", false);
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	private boolean isOrderSecurityGroupExists(String orderNumber,
											   String securityGroup) {
		boolean isSecurityGroupExists = false;
		List orderSecurityGroupList = utasSecurityGroupDao.selectOrderSecurityGroup(orderNumber);
		Iterator orderSecurityGroupIter = orderSecurityGroupList.iterator();
		while(orderSecurityGroupIter.hasNext())
		{
			Map tempSecurityGroupMap = (Map) orderSecurityGroupIter.next();
			String tempSecurityGroup = (String) tempSecurityGroupMap.get("SECURITY_GROUP");
			if(StringUtils.equals(tempSecurityGroup, securityGroup))
			{
				isSecurityGroupExists = true;
				break;
			}
		}
		return isSecurityGroupExists;
	}

	@SuppressWarnings("rawtypes")
	private boolean isSecurityGroupExists(String planId, Number planUpdateNumber,
			String securityGroup) {
		boolean isSecurityGroupExists = false;
		
		
		List planSecurityGroupList = utasSecurityGroupDao.selectPlanSecurityGroup(planId,planUpdateNumber);
		Iterator planSecurityGroupIter = planSecurityGroupList.iterator();
		while(planSecurityGroupIter.hasNext())
		{
			Map tempSecurityGroupMap = (Map) planSecurityGroupIter.next();
			String tempSecurityGroup = (String) tempSecurityGroupMap.get("SECURITY_GROUP");
			if(StringUtils.equals(tempSecurityGroup, securityGroup))
			{
				isSecurityGroupExists = true;
				break;
			}
		}
		return isSecurityGroupExists;
	}
	
	@Override
	public void deleteWorkPlanSecurityGroup ( String planNumber,
											  Number planVersion,
											  Number planRevision,
											  Number planAlterations, 
											  String inheritedSecurityGroupFlag, 
											  String securityGroup,
											  Number planUpdtNo)
	{
	    String planId = utasSecurityGroupDao.getPlanId(planNumber);
		try
		{
			ContextUtil.getUser().getContext().set("PLAN_UPDT_NO", planUpdtNo);
			
			securityGroupDao.updateSecurityGroupInPlan(planId, 
													   planUpdtNo,
													   securityGroup, 
													   SoluminaConstants.DELETE);
			
			// UTAS-484
			// Check if Plan has at least one Security Group or not for Current User			
			boolean userLastSecurityGroup = utasSecurityGroupDao.isUserLastSecurityGroup(planNumber, planUpdtNo, ContextUtil.getUsername());
			if(!userLastSecurityGroup)
			{
				message.raiseError("HAMSI_18CA3E8D18B25B9DE053D20F0A0A2D31");
			}
			
			boolean securityGroupExistsInAnyPlan = utasSecurityGroupDao.securityGroupExistsInAnyRevision(planNumber, securityGroup);
			
			if(!securityGroupExistsInAnyPlan)
			{
		    	securityGroupDao.deleteWorkPlanSecurityGroup( planId,
		    												  planUpdtNo,
		    	                                              securityGroup );
			}
	    	
	        
		}
		finally
		{
			ContextUtil.getUser().getContext().set("PLAN_UPDT_NO", null);
		}
	}
}
