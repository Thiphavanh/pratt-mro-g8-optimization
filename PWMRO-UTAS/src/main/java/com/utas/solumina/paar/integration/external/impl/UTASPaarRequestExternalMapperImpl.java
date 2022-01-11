package com.utas.solumina.paar.integration.external.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.xmlbeans.XmlObject;
import org.openapplications.oagis.x9.DocumentIDType;
import org.openapplications.oagis.x9.IdentifierType;
import org.openapplications.oagis.x9.LocationType;
import org.openapplications.oagis.x9.PermissionType;
import org.openapplications.oagis.x9.ProcessSolPAARType;
import org.openapplications.oagis.x9.ProductionOrderHeaderType;
import org.openapplications.oagis.x9.RoutingHeaderType;
import org.openapplications.oagis.x9.SequencedCodeType;
import org.openapplications.oagis.x9.SolPAARType;
import org.openapplications.oagis.x9.StatusType;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.solumina.integration.common.MappingAction;
import com.ibaset.common.util.OAGIUtils; // G8R2SP4
import com.ibaset.solumina.integration.exception.MappingException;
import com.utas.solumina.common.UtasConstants;
import com.utas.solumina.paar.domain.AuthPermissionSet;
import com.utas.solumina.paar.domain.Permission;
import com.utas.solumina.paar.domain.SolPaarExternal;
import com.utas.solumina.paar.integration.external.IUTASPaarRequestExternalMapper;

public class UTASPaarRequestExternalMapperImpl implements IUTASPaarRequestExternalMapper
{
	@Reference  
	private OAGIUtils oagiUtils = null;  // G8R2SP4
	
	@Override
	public SolPaarExternal getRequestId(XmlObject xmlObject)  throws MappingException
	{
		SolPaarExternal solPaarExternal = new SolPaarExternal();

		SolPAARType solPaarType = null;
		
		if(xmlObject instanceof SolPAARType)
		{
			solPaarType = ((SolPAARType) xmlObject);
		}
		
		if(solPaarType!=null && solPaarType.getPAARHeader().getDocumentID().getID()!=null)
		{
			IdentifierType identifierType =  solPaarType.getPAARHeader().getDocumentID().getID();

			String s = identifierType.getStringValue();
			if(StringUtils.isNotEmpty(s))
			{       				
				solPaarExternal.setRequestId(s);
			}
		}

		return solPaarExternal;
	}

	@Override
	public SolPaarExternal[] map(MappingAction[] inboundActions, SolPaarExternal destinationSolPaarReq)
			throws Exception 
	{
		SolPaarExternal[] solPaarExtItem= null;
	
        for (int i = 0; i < inboundActions.length; i++)
        {
            MappingAction mappingAction = inboundActions[i];

            Object sourceObject = mappingAction.getSourceObject();
            
            if (sourceObject != null && sourceObject instanceof SolPAARType)
            {
            	SolPAARType solPaarType =((SolPAARType)sourceObject);
            	
            	solPaarExtItem = mapSolPaarrequestAction(solPaarType, destinationSolPaarReq);
            }
        }
        
        return solPaarExtItem;
	}

	private SolPaarExternal[] mapSolPaarrequestAction(SolPAARType solPAARType, SolPaarExternal destinationSolPaarReq) 
	{
		SolPaarExternal[] solPaarExtList=new SolPaarExternal[1];
		int i=0;

		solPaarExtList[i] = new SolPaarExternal();
		
		// Set Component Id (Planning, Execution)
		ProcessSolPAARType processSolPAARType = (ProcessSolPAARType) oagiUtils.getRootNoun(solPAARType, ProcessSolPAARType.class);
		String requestComponentId = StringUtils.EMPTY;
    	if(processSolPAARType.getApplicationArea().getSender().getComponentID() != null)
    	{
    		requestComponentId = StringUtils.defaultIfEmpty(processSolPAARType.getApplicationArea().getSender().getComponentID().getStringValue(), UtasConstants.PLANNING_PROPER_CASE);
    		solPaarExtList[i].setRequestComponentId(requestComponentId);
    		
    		//String approvingUserId = processSolPAARType.getApplicationArea().getSender().getAuthorizationID().getStringValue();
    		//solPaarExtList[i].setApprovingUserId(approvingUserId);
    	}
    	else
    	{
    		requestComponentId = UtasConstants.PLANNING_PROPER_CASE;
    		solPaarExtList[i].setRequestComponentId(requestComponentId);
    	}
    	
		solPaarExtList[i].setRequestId(solPAARType.getPAARHeader().getDocumentID().getID().getStringValue());
		solPaarExtList[i].setRequestStatus(SoluminaConstants.OPEN_STATUS);
		solPaarExtList[i].setRequestUserId(solPAARType.getPAARHeader().getOriginatorParty().getPartyIDs().getIDArray(0).getStringValue());
		
		solPaarExtList[i].setReqCreationDate(solPAARType.getPAARHeader().getDocumentDateTime().getTime());
		if(solPAARType.getPAARHeader().getResponseRequiredByDateTime()!=null)
		{
			solPaarExtList[i].setReplyRequiredDate(solPAARType.getPAARHeader().getResponseRequiredByDateTime().getTime());
		}
		
		if(solPAARType.getPAARHeader().getDescriptionList()!=null && solPAARType.getPAARHeader().getDescriptionList().size()>0)
		{
			solPaarExtList[i].setRequestReason(solPAARType.getPAARHeader().getDescriptionArray(0).getStringValue());			
		}
		if(solPAARType.getPAARHeader().getDocumentReferenceList()!=null && solPAARType.getPAARHeader().getDocumentReferenceList().size()>0)
		{			
			solPaarExtList[i].setPriorRequestId(solPAARType.getPAARHeader().getDocumentReferenceArray(0).getDocumentID().getID().getStringValue());
		}
		
		// Check if received Paar Request is of Plan or Order
		if(StringUtils.equals(requestComponentId, UtasConstants.PLANNING_PROPER_CASE))
		{
			// Map Planning Information
			mapPlanInfo(solPaarExtList[i], solPAARType.getPAARHeader().getTarget().getRouting());
		}
		else if(StringUtils.equals(requestComponentId, UtasConstants.EXECUTION_PROPER_CASE))
		{
			// Map Order Information
			mapOrderInfo(solPaarExtList[i], solPAARType.getPAARHeader().getTarget().getProductionOrder());
		}

		AuthPermissionSet authPermissionSet = new AuthPermissionSet();
		List<Permission> permissionSet = new ArrayList<Permission>();

		if(solPAARType.getPermissionSet()!=null && solPAARType.getPermissionSet().getPermissionList()!=null && solPAARType.getPermissionSet().getPermissionList().size()>0)
		{
			List<PermissionType> inboundPermissionSet = solPAARType.getPermissionSet().getPermissionList();		
			for(int ind =0;ind<inboundPermissionSet.size();ind++)
			{
				PermissionType permissionType = inboundPermissionSet.get(ind);
				Permission permission = new Permission();

				if(permissionType.getGrantedTo().getEmployee()!=null)
				{
					permission.setType(SoluminaConstants.USER);   
					permission.setTypeEntity(permissionType.getGrantedTo().getEmployee().getIDArray(0).getStringValue());
				}
				if(permissionType.getGrantedTo().getFacility()!=null)
				{
					permission.setType(UtasConstants.LOCATION);
					permission.setTypeEntity(permissionType.getGrantedTo().getFacility().getIDs().getIDArray(0).getStringValue());

				}
				if(permissionType.getGrantedTo().getProperty()!=null)
				{
					permission.setType("Security Group".toUpperCase());    				
					permission.setTypeEntity(permissionType.getGrantedTo().getProperty().getNameValue().getStringValue());
				}  
				if(permissionType.getEffectiveTimePeriod() != null
						&& permissionType.getEffectiveTimePeriod().getStartDateTime()!=null)
				{    				    		
					permission.setStartDate(permissionType.getEffectiveTimePeriod().getStartDateTime().getTime());
				}
				if(permissionType.getEffectiveTimePeriod() != null
						&& permissionType.getEffectiveTimePeriod().getEndDateTime()!=null)
				{    				    			
					permission.setEndDate(permissionType.getEffectiveTimePeriod().getEndDateTime().getTime());
				}
				permission.setSghtId(permissionType.getPermissionID());
				permission.setAction(permissionType.getPermissionAction());
				permission.setPermissionStatus(SoluminaConstants.DENY);
				permissionSet.add(permission);
			}
		}

		authPermissionSet.setSghtPermissionList(permissionSet);
		solPaarExtList[i].setAuthPermissionSet(authPermissionSet);    		    	
		return solPaarExtList;
	}

	private void mapPlanInfo(SolPaarExternal solPaarExternal, RoutingHeaderType routing) 
	{
		solPaarExternal.setPlanNo(routing.getDocumentID().getID().getStringValue());
		Number planRev = NumberUtils.createNumber(routing.getDocumentID().getRevisionID().getStringValue());
		solPaarExternal.setPlanRevision(planRev);
		Number planVer = NumberUtils.createNumber(routing.getDocumentID().getVariationID().getStringValue());
		solPaarExternal.setPlanVersion(planVer);

		if(routing.getDescriptionList()!=null && routing.getDescriptionList().size()>0)
		{
			solPaarExternal.setPlanTitle(routing.getDescriptionArray(0).getStringValue());
		}
		solPaarExternal.setPlanType(routing.getType().getStringValue());
		if(routing.getBOMReferenceList()!=null && routing.getBOMReferenceList().size()>0)
		{
			solPaarExternal.setBomNo(routing.getBOMReferenceArray(0).getDocumentID().getID().getStringValue());
			solPaarExternal.setBomRev(routing.getBOMReferenceArray(0).getDocumentID().getRevisionID().getStringValue());
		}
			
		solPaarExternal.setPartNo(routing.getManufacturingItem().getItemIDArray(0).getID().getStringValue());
		solPaarExternal.setPartRev(routing.getManufacturingItem().getItemIDArray(0).getRevisionID().getStringValue());

		if(routing.getManufacturingItem().getDescriptionList()!=null && routing.getManufacturingItem().getDescriptionList().size()>0)
		{
			solPaarExternal.setPartTitle(routing.getManufacturingItem().getDescriptionArray(0).getStringValue());
		}
		if(routing.getManufacturingItem().getClassificationList()!=null && routing.getManufacturingItem().getClassificationList().size()>0)
		{
			solPaarExternal.setPartType(routing.getManufacturingItem().getClassificationArray(0).getType());
			solPaarExternal.setPartSubType(routing.getManufacturingItem().getClassificationArray(0).getCodes().getCodeArray(0).getStringValue());
		}
		
		//UTAS-480
		if(routing.getSiteList() != null && routing.getSiteList().size() > 0)
		{
			LocationType locationType = routing.getSiteList().get(0);
			solPaarExternal.setFacilityId(locationType.getIDList().get(0).getStringValue());
			if(locationType.getNameList() != null && locationType.getNameList().size() > 0)  
			{
				solPaarExternal.setFacilityDesc(locationType.getNameList().get(0).getStringValue());
			}
		}
	}
	
	// UTAS-497
	private void mapOrderInfo(SolPaarExternal solPaarExternal, ProductionOrderHeaderType productionOrder) 
	{
		DocumentIDType documentIdType = productionOrder.getDocumentID();
		String agencyRole = documentIdType.getAgencyRole();
		if(StringUtils.equals(agencyRole, "MES"))
		{
			IdentifierType identifierType  = documentIdType.getID();
			if(StringUtils.equals(identifierType.getSchemeID(), "WorkOrder")
					&& StringUtils.equals(identifierType.getSchemeAgencyID(), "Solumina"))
			{
				solPaarExternal.setOrderNo(productionOrder.getDocumentID().getID().getStringValue());
			}
		}
		
		if(productionOrder.getDescriptionList()!=null && productionOrder.getDescriptionList().size()>0)
		{
			solPaarExternal.setOrderTitle(productionOrder.getDescriptionArray(0).getStringValue());
		}
		
		if(productionOrder.getStatusList() != null && productionOrder.getStatusList().size() > 0)
		{
			StatusType statusType = productionOrder.getStatusList().get(0);
			if(statusType.getCode() != null)
			{
				solPaarExternal.setOrderStatus(statusType.getCode().getStringValue());
			}
		}
		
		if(productionOrder.getBOMReference() != null)
		{
			solPaarExternal.setBomNo(productionOrder.getBOMReference().getDocumentID().getID().getStringValue());
			solPaarExternal.setBomRev(productionOrder.getBOMReference().getDocumentID().getRevisionID().getStringValue());
		}
			
		solPaarExternal.setPartNo(productionOrder.getItemInstance().getItemIDList().get(0).getID().getStringValue());
		solPaarExternal.setPartRev(productionOrder.getItemInstance().getItemIDList().get(0).getRevisionID().getStringValue());

		if(productionOrder.getItemInstance().getDescriptionList()!=null && productionOrder.getItemInstance().getDescriptionList().size()>0)
		{
			solPaarExternal.setPartTitle(productionOrder.getItemInstance().getDescriptionArray(0).getStringValue());
		}
		
		if(productionOrder.getItemInstance().getClassificationList()!=null && productionOrder.getItemInstance().getClassificationList().size()>0)
		{
			if(productionOrder.getItemInstance().getClassificationList().get(0).getCodes() != null 
					&& productionOrder.getItemInstance().getClassificationList().get(0).getCodes().getCodeList() != null
					&& productionOrder.getItemInstance().getClassificationList().get(0).getCodes().getCodeList().size() > 0);
			{
				for(SequencedCodeType codeType : productionOrder.getItemInstance().getClassificationList().get(0).getCodes().getCodeList())
				{
					if(StringUtils.equals(codeType.getName(), "PartType"))
					{
						solPaarExternal.setPartType(codeType.getStringValue());
					}
					else if(StringUtils.equals(codeType.getName(), "PartSubType"))
					{
						solPaarExternal.setPartSubType(codeType.getStringValue());
					}
				}
			}
		}
		
		if(productionOrder.getSiteList() != null && productionOrder.getSiteList().size() > 0)
		{
			LocationType locationType = productionOrder.getSiteList().get(0);
			solPaarExternal.setFacilityId(locationType.getIDList().get(0).getStringValue());
			if(locationType.getNameList() != null && locationType.getNameList().size() > 0)  //UTAS-557
			{
				solPaarExternal.setFacilityDesc(locationType.getNameList().get(0).getStringValue());
			}
		}
		
		if(productionOrder.getExecutionTimePeriod() != null)
		{
			if(productionOrder.getExecutionTimePeriod().getStartDateTime() != null)
			{
				solPaarExternal.setActualStartDate(productionOrder.getExecutionTimePeriod().getStartDateTime().getTime());
			}
			if(productionOrder.getExecutionTimePeriod().getEndDateTime() != null)
			{
				solPaarExternal.setActualEndDate(productionOrder.getExecutionTimePeriod().getEndDateTime().getTime());
			}
		}
			
		if(productionOrder.getForecastedTimePeriod() != null)
		{
			if(productionOrder.getForecastedTimePeriod().getStartDateTime() != null)
			{
				solPaarExternal.setSchedStartDate(productionOrder.getForecastedTimePeriod().getStartDateTime().getTime());
			}
			if(productionOrder.getForecastedTimePeriod().getEndDateTime() != null)
			{
				solPaarExternal.setSchedEndDate(productionOrder.getForecastedTimePeriod().getEndDateTime().getTime());
			}
		}
	}
}
