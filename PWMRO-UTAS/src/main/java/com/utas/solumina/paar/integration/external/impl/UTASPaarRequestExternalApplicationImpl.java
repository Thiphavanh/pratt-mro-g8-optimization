package com.utas.solumina.paar.integration.external.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.solumina.exception.SoluminaApplicationException;
import com.utas.solumina.common.UtasConstants;
import com.utas.solumina.paar.dao.PaarRequestDao;
import com.utas.solumina.paar.domain.Permission;
import com.utas.solumina.paar.domain.SolPaarExternal;
import com.utas.solumina.paar.integration.external.IUTASPaarRequestExternalApplication;

public class UTASPaarRequestExternalApplicationImpl implements IUTASPaarRequestExternalApplication
{
	@Reference
	PaarRequestDao paarRequestDao;
	
	@Override
	public SolPaarExternal[] getPaarReequestInfo(String[] solPaarReqIdIdentifiers) throws SoluminaApplicationException 
	{
		 try
	        {
	            ArrayList<SolPaarExternal> resultList = new ArrayList<SolPaarExternal>();

	            for (int i = 0; i < solPaarReqIdIdentifiers.length; i++)
	            {
	            	SolPaarExternal solPaarExternal = new SolPaarExternal();
	            	
	            	solPaarExternal.setRequestId(solPaarReqIdIdentifiers[i]);
	            	
	            	resultList.add(solPaarExternal);	                
	            }
	            return (SolPaarExternal[]) resultList.toArray(new SolPaarExternal[resultList.size()]);
	        }
	        catch (RuntimeException e)
	        {
	            throw new SoluminaApplicationException(e.getMessage(), e);
	        }
	}

	@Override
	public void insertSolPaarExternalRequest(SolPaarExternal[] solPaarExternalArray) 
	{
		if(solPaarExternalArray!=null)
		{
			for(int i=0;i<solPaarExternalArray.length;i++)
			{
				SolPaarExternal solPaarExternal = solPaarExternalArray[i];
				
				// Check for Planning and Execution
				String requestComponentId = solPaarExternal.getRequestComponentId();
				if(StringUtils.equals(requestComponentId, UtasConstants.PLANNING_PROPER_CASE))
				{
					// Insert in PAAR Request Plan
					Number planAlterations = (Number) paarRequestDao.selectRequestInfo(solPaarExternal.getRequestId()).get("PLAN_ALTERATIONS");
					solPaarExternal.setPlanAlteration(planAlterations);
					paarRequestDao.insertExternalPlanPAARDesc(solPaarExternal);
					
					List<Permission> solPaarPermissionList = solPaarExternal.getAuthPermissionSet().getSghtPermissionList();
					if(solPaarPermissionList!=null && solPaarPermissionList.size()>0)
					{
						for(int j=0;j<solPaarPermissionList.size();j++)
						{
							Permission permission = solPaarPermissionList.get(j);
							paarRequestDao.insertExternalPlanPAARPermissionAuth(solPaarExternal.getRequestId(), permission);
						}
					}
					
					if (StringUtils.isNotEmpty(solPaarExternal.getPriorRequestId()))
					{
					    cancelPriorRequestPlg(solPaarExternal.getPriorRequestId());
					}   
				}
				else if(StringUtils.equals(requestComponentId, UtasConstants.EXECUTION_PROPER_CASE))
				{
					// Insert PAAR Request Order
					paarRequestDao.insertExternalOrderPAARDesc(solPaarExternal);
					
					List<Permission> solPaarPermissionList = solPaarExternal.getAuthPermissionSet().getSghtPermissionList();
					if(solPaarPermissionList!=null && solPaarPermissionList.size()>0)
					{
						for(int j=0;j<solPaarPermissionList.size();j++)
						{
							Permission permission = solPaarPermissionList.get(j);
							paarRequestDao.insertExternalOrderPAARPermissionAuth(solPaarExternal.getRequestId(), permission);
						}
					}
					
					if (StringUtils.isNotEmpty(solPaarExternal.getPriorRequestId()))
					{
					    cancelPriorRequestWid(solPaarExternal.getPriorRequestId());
					}					
				}
			}
		}
		
	}
	
	private void cancelPriorRequestPlg(String priorRequestId)
	{
	    // Cancel the prior request - if it is still in Open status
	    paarRequestDao.cancelExternalPriorRequestPlg(priorRequestId);
	}
	
	private void cancelPriorRequestWid(String priorRequestId)
	{
	    //Cancel the prior request - if it is still in Open status
	    paarRequestDao.cancelExternalPriorRequestWid(priorRequestId);
	}

}
