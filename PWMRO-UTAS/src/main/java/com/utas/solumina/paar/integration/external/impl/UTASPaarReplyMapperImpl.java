package com.utas.solumina.paar.integration.external.impl;

import org.apache.commons.lang.StringUtils;
import org.openapplications.oagis.x9.AcknowledgeSolPAARDocument;
import org.openapplications.oagis.x9.DocumentIDType;
import org.openapplications.oagis.x9.DocumentReferenceType;
import org.openapplications.oagis.x9.EffectType;
import org.openapplications.oagis.x9.FacilityType;
import org.openapplications.oagis.x9.IdentifierType;
import org.openapplications.oagis.x9.NameValuePairType;
import org.openapplications.oagis.x9.PAARHeaderType;
import org.openapplications.oagis.x9.PermissionEntityType;
import org.openapplications.oagis.x9.PermissionSetType;
import org.openapplications.oagis.x9.PermissionType;
import org.openapplications.oagis.x9.PersonnelType;
import org.openapplications.oagis.x9.ProductionOrderHeaderType;
import org.openapplications.oagis.x9.PropertyType;
import org.openapplications.oagis.x9.ResponseExpressionType;
import org.openapplications.oagis.x9.RoutingHeaderType;
import org.openapplications.oagis.x9.SolPAARType;
import org.openapplications.oagis.x9.TimePeriodType;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.solumina.integration.common.ActionCode;
import com.ibaset.solumina.integration.common.MappingAction;
import com.ibaset.common.util.OAGIUtils; // G8R2SP4
import com.utas.solumina.common.UtasConstants;
import com.utas.solumina.paar.domain.Permission;
import com.utas.solumina.paar.domain.SolPaarExternal;
import com.utas.solumina.paar.integration.external.IUTASPaarReplyMapper;

public class UTASPaarReplyMapperImpl implements IUTASPaarReplyMapper 
{
	@Reference  
	private OAGIUtils oagiUtils = null;  // G8R2SP4

	@Override
	public void map(MappingAction[] outboundActions,
					AcknowledgeSolPAARDocument acknowledgeSolPAARDocument) 
	{	
		for (int i = 0; i < outboundActions.length; i++) 
		{
			if (outboundActions[i].getSourceObject() instanceof SolPaarExternal) 
			{
				SolPaarExternal solPaarExternal = (SolPaarExternal) outboundActions[i].getSourceObject();
				setActionCode(acknowledgeSolPAARDocument ,solPaarExternal);
				mapSolPaar(acknowledgeSolPAARDocument, solPaarExternal);
			}
		}
	}

	protected void setActionCode(AcknowledgeSolPAARDocument acknowledgeSolPAARDocument, SolPaarExternal solPaarExternal)
	{
		String actionEx = "/AcknowledgeSolPAAR/DataArea/SolPAAR[1]";		 
		ResponseExpressionType responseExpression = acknowledgeSolPAARDocument.getAcknowledgeSolPAAR()
														  .getDataArea()
														  .getAcknowledge()
														  .getResponseCriteriaArray(0)
														  .addNewResponseExpression();
														  
		
		responseExpression.setActionCode(ActionCode.ACCEPTED.toString());
		responseExpression.setStringValue(actionEx);
	}
		
	protected void mapSolPaar(AcknowledgeSolPAARDocument acknowledgeSolPAARDocument, SolPaarExternal solPaarExternal) 
	{
		// Add Sol PAAR in the Process Sol PAAR Document
		SolPAARType solPaarType = acknowledgeSolPAARDocument.getAcknowledgeSolPAAR().getDataArea().addNewSolPAAR();
		
		// Add PAAR Header and Map data in it
		PAARHeaderType paarHeader = solPaarType.addNewPAARHeader();
		mapSolPaarHeader(paarHeader, solPaarExternal);
		
		// Add Permission Set and Map data in it
		if(solPaarExternal.getAuthPermissionSet()!= null && solPaarExternal.getAuthPermissionSet().getSghtPermissionList().size() > 0)
		{
			PermissionSetType permissionSetType = solPaarType.addNewPermissionSet();
			mapPermissionSet(permissionSetType, solPaarExternal);
		}
	}
	
	protected void mapSolPaarHeader(PAARHeaderType paarHeaderType, SolPaarExternal solPaarExternal) 
	{
		// Add Request Id
		DocumentIDType documentId = paarHeaderType.addNewDocumentID();
		IdentifierType id = documentId.addNewID();
		id.setStringValue(solPaarExternal.getRequestId());
		
		// Add Request Creation Date
		if(solPaarExternal.getReqCreationDate() != null)
		{
			paarHeaderType.xsetDocumentDateTime(oagiUtils.dateToDateTimeType(solPaarExternal.getReqCreationDate()));
		}
		
		// Add Request Reason
		if(StringUtils.isNotEmpty(solPaarExternal.getRequestReason()))
		{
			paarHeaderType.addNewDescription().setStringValue(solPaarExternal.getRequestReason());
		}
		
		// Add Prior Request Id
		if(StringUtils.isNotEmpty(solPaarExternal.getPriorRequestId()))
		{
			DocumentReferenceType documentReferenceType = paarHeaderType.addNewDocumentReference();
			documentReferenceType.setType2("PriorRequest");
			documentReferenceType.addNewDocumentID().addNewID().setStringValue(solPaarExternal.getPriorRequestId());
		}
		
		if(StringUtils.equals(solPaarExternal.getRequestComponentId(), UtasConstants.PLANNING_PROPER_CASE))
		{
			// Map Plan and Part Related Info in PAAR Header
			RoutingHeaderType routingType = paarHeaderType.addNewTarget().addNewRouting();
			mapPAARPlanInfo(routingType, solPaarExternal);
		}
		else if(StringUtils.equals(solPaarExternal.getRequestComponentId(), UtasConstants.EXECUTION_PROPER_CASE))
		{
			// Map Order Related Info in PAAR Header
			ProductionOrderHeaderType productionOrderType = paarHeaderType.addNewTarget().addNewProductionOrder();
			mapPAAROrderInfo(productionOrderType, solPaarExternal);
		}
	}

	protected void mapPermissionSet(PermissionSetType permissionSetType, SolPaarExternal solPaarExternal) 
	{
		// Check if PAAR Request contains any Permission Set
		// Map User Permission
		// UTAS-497 Skip to Add User Permission for Work Order Paar Request 
		if(!StringUtils.equals(solPaarExternal.getRequestComponentId(), UtasConstants.EXECUTION_PROPER_CASE))
		{
			mapUserPermission(permissionSetType, solPaarExternal);
		}
			
		// Map Location Permission
		mapLocationPermission(permissionSetType, solPaarExternal);

		// Map Security Group Permission
		mapSecurityGroupPermission(permissionSetType, solPaarExternal);
	}

	private void mapUserPermission(PermissionSetType permissionSetType, SolPaarExternal solPaarExternal) 
	{
		// Add User Permission
		for (Permission userPermission : solPaarExternal.getAuthPermissionSet().getSghtPermissionList())
		{
			if (StringUtils.equalsIgnoreCase(userPermission.getType(), SoluminaConstants.USER))
			{
				//<Permission>
				PermissionType permissionType = permissionSetType.addNewPermission();
				permissionType.setPermissionID(userPermission.getSghtId());
				permissionType.setPermissionAction(userPermission.getAction());
				
				if (StringUtils.equals(userPermission.getPermissionStatus().toUpperCase(),UtasConstants.PERMIT.toUpperCase()))
				{
					permissionType.setEffect(EffectType.PERMIT);
				}
				else if (StringUtils.equals(userPermission.getPermissionStatus().toUpperCase(),SoluminaConstants.DENY.toUpperCase()))
				{
					permissionType.setEffect(EffectType.DENY);
				}
				
				
				// <GrantedTo>
				PermissionEntityType permissionEntityType = permissionType.addNewGrantedTo();
				PersonnelType personnelType = permissionEntityType.addNewEmployee();
				personnelType.addNewID().setStringValue(userPermission.getTypeEntity()); 
				
				if(userPermission.getUser().getFullName() != null)
				{
				    personnelType.addNewName().setStringValue(userPermission.getUser().getFullName());
				}
					
				
				// <GrantedTo><Facility>
				if(userPermission.getUser().getWorkLocDef() != null)
				{
					FacilityType facilityType = personnelType.addNewFacility();
					facilityType.addNewIDs().addNewID().setStringValue(userPermission.getUser().getWorkLocDef().getWorkLoc()) ; // sffnd_user.work_loc
					if(StringUtils.isNotEmpty(userPermission.getUser().getWorkLocDef().getWorkLocationTitle()))
						facilityType.addNewName().setStringValue(userPermission.getUser().getWorkLocDef().getWorkLocationTitle()); // sffnd_work_loc_def.loc_title
				}
				
				// <EffectiveTimePeriod>
				TimePeriodType timePeriodType = permissionType.addNewEffectiveTimePeriod();
				timePeriodType.xsetStartDateTime(oagiUtils.dateToDateTimeType(userPermission.getStartDate()));
				timePeriodType.xsetEndDateTime(oagiUtils.dateToDateTimeType(userPermission.getEndDate()));
			}
		}
	}

	private void mapLocationPermission(PermissionSetType permissionSetType, SolPaarExternal solPaarExternal) 
	{
		// Add Location Permission
		for (Permission locationPermission : solPaarExternal.getAuthPermissionSet().getSghtPermissionList())
		{
			if (StringUtils.equalsIgnoreCase(locationPermission.getType(), UtasConstants.LOCATION))
			{
				// <Permission>
				PermissionType permissionType = permissionSetType.addNewPermission();
				permissionType.setPermissionID(locationPermission.getSghtId()); 				
				permissionType.setPermissionAction(locationPermission.getAction());

				if (StringUtils.equals(locationPermission.getPermissionStatus().toUpperCase(),UtasConstants.PERMIT.toUpperCase()))
				{
					permissionType.setEffect(EffectType.PERMIT);
				}
				else if (StringUtils.equals(locationPermission.getPermissionStatus().toUpperCase(),SoluminaConstants.DENY.toUpperCase()))
				{
					permissionType.setEffect(EffectType.DENY);
				}
		
				// <GrantedTo><Facility>
				PermissionEntityType permissionEntityType = permissionType.addNewGrantedTo();
				FacilityType facilityType = permissionEntityType.addNewFacility();
				facilityType.addNewIDs().addNewID().setStringValue(locationPermission.getTypeEntity()); 
				
				if (StringUtils.isNotEmpty(locationPermission.getWorkloc().getLocTitle()))
				{
					facilityType.addNewName().setStringValue(locationPermission.getWorkloc().getLocTitle()); 
				}
			}
		}
	}

	private void mapSecurityGroupPermission(PermissionSetType permissionSetType, SolPaarExternal solPaarExternal) 
	{
		// Add Security Group Permission
		for (Permission securityGroupPermission : solPaarExternal.getAuthPermissionSet().getSghtPermissionList())
		{
			if (StringUtils.equalsIgnoreCase(securityGroupPermission.getType(), "Security Group"))
			{
				// <Permission>
				PermissionType permissionType = permissionSetType.addNewPermission();
				permissionType.setPermissionID(securityGroupPermission.getSghtId()); 
				permissionType.setPermissionAction(securityGroupPermission.getAction());
			
				if (StringUtils.equals(securityGroupPermission.getPermissionStatus().toUpperCase(),UtasConstants.PERMIT.toUpperCase()))
				{
					permissionType.setEffect(EffectType.PERMIT);
				}
				else if (StringUtils.equals(securityGroupPermission.getPermissionStatus().toUpperCase(),SoluminaConstants.DENY.toUpperCase()))
				{
					permissionType.setEffect(EffectType.DENY);
				}
				
				// <GrantedTo><PropertyType>
				PermissionEntityType permissionEntityType = permissionType.addNewGrantedTo();
				PropertyType propertyType = permissionEntityType.addNewProperty();
				NameValuePairType nameValuePairType = propertyType.addNewNameValue();
				nameValuePairType.setName("Security Group");
				nameValuePairType.setStringValue(securityGroupPermission.getTypeEntity());
				
                if (StringUtils.isNotEmpty(securityGroupPermission.getSecurityGroup().getSecurityGroupDescription()))
                {
                    propertyType.addNewDescription().setStringValue(securityGroupPermission.getSecurityGroup().getSecurityGroupDescription());
                }				
			}
		}
	}
	
	protected void mapPAARPlanInfo(RoutingHeaderType routingType, SolPaarExternal solPaarExternal) 
	{
		routingType.addNewDocumentID().addNewID().setStringValue(solPaarExternal.getPlanNo());
		routingType.getDocumentID().addNewRevisionID().setStringValue(solPaarExternal.getPlanRevision().toString());
		routingType.getDocumentID().addNewVariationID().setStringValue(solPaarExternal.getPlanVersion().toString());
	}
	
	// UTAS-497
	protected void mapPAAROrderInfo(ProductionOrderHeaderType productionOrderType, SolPaarExternal solPaarExternal) 
	{
		// Order No
		DocumentIDType documentIdType = productionOrderType.addNewDocumentID();
		documentIdType.setAgencyRole("MES");
		IdentifierType identifierType = documentIdType.addNewID();
		identifierType.setSchemeID("WorkOrder");
		identifierType.setSchemeAgencyID("Solumina");
		identifierType.setStringValue(solPaarExternal.getOrderNo());
	}
}
