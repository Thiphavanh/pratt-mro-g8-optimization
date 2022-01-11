/*
*  This unpublished work, first created in 2018 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2018.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    PaarMapperImpl.java
* 
*  Created: 2018-11-15
* 
*  Author:  David Miron
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	-------------------------------------------------------------------
* 2018-08-24 	D.Miron		    Added PWU-124 Update from iBaset - Francis Graham
* 2019-03-01    D.Miron         Updated for G8R2SP4
*/

package com.utas.solumina.paar.integration.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.openapplications.oagis.x9.ActionExpressionType;
import org.openapplications.oagis.x9.ClassificationType;
import org.openapplications.oagis.x9.DocumentIDType;
import org.openapplications.oagis.x9.DocumentReferenceType;
import org.openapplications.oagis.x9.EffectType;
import org.openapplications.oagis.x9.FacilityType;
import org.openapplications.oagis.x9.IdentifierType;
import org.openapplications.oagis.x9.ItemIDType;
import org.openapplications.oagis.x9.ItemInstanceType;
import org.openapplications.oagis.x9.LocationType;
import org.openapplications.oagis.x9.ManufacturingItemType;
import org.openapplications.oagis.x9.NameValuePairType;
import org.openapplications.oagis.x9.PAARHeaderType;
import org.openapplications.oagis.x9.PermissionEntityType;
import org.openapplications.oagis.x9.PermissionSetType;
import org.openapplications.oagis.x9.PermissionType;
import org.openapplications.oagis.x9.PersonnelType;
import org.openapplications.oagis.x9.ProcessSolPAARDocument;
import org.openapplications.oagis.x9.ProductionOrderHeaderType;
import org.openapplications.oagis.x9.PropertyType;
import org.openapplications.oagis.x9.RoutingHeaderType;
import org.openapplications.oagis.x9.SequencedCodeType;
import org.openapplications.oagis.x9.SolPAARType;
import org.openapplications.oagis.x9.TimePeriodType;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.solumina.integration.common.ActionCode;
import com.ibaset.solumina.integration.common.MappingAction;
import com.ibaset.common.util.OAGIUtils; // G8R2SP4
import com.ibaset.solumina.integration.exception.MappingException;
import com.ibaset.solumina.sfpl.domain.PlanDesc;
import com.ibaset.solumina.sfpl.domain.PlanRevId;
import com.ibaset.solumina.sfwid.domain.OrderDesc;
import com.utas.solumina.common.UtasConstants;
import com.utas.solumina.paar.domain.Permission;
import com.utas.solumina.paar.domain.PermissionDesc;
import com.utas.solumina.paar.domain.SolPaar;
import com.utas.solumina.paar.domain.SolPaarWO;
import com.utas.solumina.paar.integration.PaarMapper;

public class PaarMapperImpl implements PaarMapper 
{
	@Reference  private OAGIUtils oagiUtils = null;  // G8R2SP4
	
	public void map(MappingAction[] outboundActions, ProcessSolPAARDocument processSolPAARDocument)
	{
		for (int i = 0; i < outboundActions.length; i++) 
		{			
			if (outboundActions[i].getSourceObject() instanceof SolPaar) 
			{
				SolPaar solPaar = (SolPaar) outboundActions[i].getSourceObject();
				setActionCode(processSolPAARDocument);
				mapSolPaar(processSolPAARDocument, solPaar,null); // For Plan PAAR Request
			}
			else if (outboundActions[i].getSourceObject() instanceof SolPaarWO) 
			{
				SolPaarWO solPaarWO = (SolPaarWO) outboundActions[i].getSourceObject();
				setActionCode(processSolPAARDocument);
				mapSolPaar(processSolPAARDocument, null,solPaarWO); // For WO PAAR Request
			}
		}
	}
	
	protected void setActionCode(ProcessSolPAARDocument processSolPAARDocument)
	{
		String actionEx = "/ProcessSolPAAR/DataArea/SolPAAR[1]";
		ActionExpressionType actionExpression = processSolPAARDocument.getProcessSolPAAR()
														  .getDataArea()
														  .getProcess()
														  .getActionCriteriaArray(0)
														  .addNewActionExpression();
		
		actionExpression.setActionCode(ActionCode.REPLACE.toString());
		actionExpression.setStringValue(actionEx);
	}

	protected void mapSolPaar(ProcessSolPAARDocument processSolPAARDocument, SolPaar solPaar, SolPaarWO solPaarWO) 
	{
		// Add Sol PAAR in the Process Sol PAAR Document
		SolPAARType solPaarType = processSolPAARDocument.getProcessSolPAAR().getDataArea().addNewSolPAAR();
		
		// Add PAAR Header and Map data in it
		PAARHeaderType paarHeader = solPaarType.addNewPAARHeader();
		mapSolPaarHeader(paarHeader, solPaar, solPaarWO);
		
		boolean addPermission = false;
		if(solPaar!=null  
				&& solPaar.getSghtPermissionSet()!=null 
				&& solPaar.getSghtPermissionSet().getSghtPermissionList() != null 
				&& solPaar.getSghtPermissionSet().getSghtPermissionList().size() > 0)
		{
			addPermission = true;
		}
		else if(solPaarWO!=null 
					&& solPaarWO.getSghtPermissionSet()!=null 
					&& solPaarWO.getSghtPermissionSet().getSghtPermissionList() != null 
					&& solPaarWO.getSghtPermissionSet().getSghtPermissionList().size() > 0)
		{
			addPermission = true;
		}
		// Add Permission Set and Map data in it
		if(addPermission)
		{
			PermissionSetType permissionSetType = solPaarType.addNewPermissionSet();
			mapPermissionSet(permissionSetType, solPaar, solPaarWO);
		}
	}
	
	

	protected void mapSolPaarHeader(PAARHeaderType paarHeaderType, SolPaar solPaar, SolPaarWO solPaarWO) 
	{
		// Add Request Id
		DocumentIDType documentId = paarHeaderType.addNewDocumentID();
		IdentifierType id = documentId.addNewID();
		if(solPaar!=null)
		{
			id.setStringValue(solPaar.getPermissionDesc().getRequestId());
		}
		else if(solPaarWO!=null)
		{
			id.setStringValue(solPaarWO.getRequestId());
		}
		
		Date creationDate = null;
		String requestReason = StringUtils.EMPTY;
		String priorRequestId = StringUtils.EMPTY;
		Date replyRequiredDate = null;
		
		if(solPaar!=null)
		{
			if(solPaar.getPermissionDesc().getCreationDate() != null)
			{
				creationDate = solPaar.getPermissionDesc().getCreationDate();
			}
			if(StringUtils.isNotEmpty(solPaar.getPermissionDesc().getRequestReason()))
			{
				requestReason = solPaar.getPermissionDesc().getRequestReason();
			}
			if(StringUtils.isNotEmpty(solPaar.getPermissionDesc().getPriorRequestId()))
			{
				priorRequestId = solPaar.getPermissionDesc().getPriorRequestId();
			}
			if(solPaar.getPermissionDesc().getReplyRequiredDate() != null)
			{
				replyRequiredDate = solPaar.getPermissionDesc().getReplyRequiredDate();
			}			
		}		
		if(solPaarWO!=null)
		{
			if(solPaarWO.getRequestCreationDate() != null)
			{
				creationDate = solPaarWO.getRequestCreationDate();
			}
			if(StringUtils.isNotEmpty(solPaarWO.getRequestReason()))
			{
				requestReason = solPaarWO.getRequestReason();
			}
			if(StringUtils.isNotEmpty(solPaarWO.getPriorRequestId()))
			{
				priorRequestId = solPaarWO.getPriorRequestId();
			}
			if(solPaarWO.getReplyRequiredDate() != null)
			{
				replyRequiredDate = solPaarWO.getReplyRequiredDate();
			}			
		}
		
		// Add Request Creation Date
		if(creationDate != null)
		{
			paarHeaderType.xsetDocumentDateTime(oagiUtils.dateToDateTimeType(creationDate));
		}
		
		// Add Request Reason
		if(StringUtils.isNotEmpty(requestReason))
		{
			paarHeaderType.addNewDescription().setStringValue(requestReason);
		}
		
		// Add Prior Request Id
		if(StringUtils.isNotEmpty(priorRequestId))
		{
			DocumentReferenceType documentReferenceType = paarHeaderType.addNewDocumentReference();
			documentReferenceType.setType2("PriorRequest");
			documentReferenceType.addNewDocumentID().addNewID().setStringValue(priorRequestId);
		}
		
		
		//For Plan PAAR Request
		if(solPaar!=null)
		{
			// Map Plan and Part Related Info in PAAR Header
			RoutingHeaderType routingType = paarHeaderType.addNewTarget().addNewRouting();
			mapPAARPlanInfo(routingType, solPaar);		
			
			// Map Prior Request Id
			paarHeaderType.addNewOriginatorParty().addNewPartyIDs().addNewID().setStringValue(StringUtils.defaultIfEmpty(solPaar.getPermissionDesc().getRequestUserId(), StringUtils.EMPTY));
		}
		//For WO PAAR Request
		if(solPaarWO!=null)
		{
			//Map Order and Order related Info in PAAR Header
			ProductionOrderHeaderType productionOrderHeaderType = paarHeaderType.addNewTarget().addNewProductionOrder();
			mapPAARWOInfo(productionOrderHeaderType,solPaarWO);
			
			// Map Prior Request Id
			paarHeaderType.addNewOriginatorParty().addNewPartyIDs().addNewID().setStringValue(StringUtils.defaultIfEmpty(solPaarWO.getRequestUserId(), StringUtils.EMPTY));
		}
		
		// Map Response Required Time
		if(replyRequiredDate != null)
		{
			paarHeaderType.xsetResponseRequiredByDateTime(oagiUtils.dateToDateTimeType(replyRequiredDate));
		}
	}
	
	private void mapPAARWOInfo(ProductionOrderHeaderType productionOrderHeaderType,
							   SolPaarWO solPaarWO) 
	{
		OrderDesc orderDesc = solPaarWO.getOrderDesc();
		
		if(orderDesc!=null)
		{
			productionOrderHeaderType.addNewDocumentID().addNewID().setStringValue(orderDesc.getOrderNo());
			productionOrderHeaderType.getDocumentID().setAgencyRole("MES");
			productionOrderHeaderType.getDocumentID().getID().setSchemeID("WorkOrder");
			productionOrderHeaderType.getDocumentID().getID().setSchemeAgencyID("Solumina");
			
			if(orderDesc.getPlanTitle()!=null)
			{
				productionOrderHeaderType.addNewDescription().setStringValue(orderDesc.getPlanTitle());	
			}
			
			productionOrderHeaderType.addNewStatus().addNewCode().setStringValue(orderDesc.getOrderStatus());
			
			if(orderDesc.getMfgBomRev()!=null)
			{
				DocumentIDType documentIdType = productionOrderHeaderType.addNewBOMReference().addNewDocumentID();
				documentIdType.addNewID().setStringValue(StringUtils.defaultIfEmpty(orderDesc.getMfgBomRev().getBomNo(), StringUtils.EMPTY));
				documentIdType.addNewRevisionID().setStringValue(StringUtils.defaultIfEmpty(orderDesc.getMfgBomRev().getMfgBomChg(), StringUtils.EMPTY));
			}
			
			ItemInstanceType itemInstanceType = productionOrderHeaderType.addNewItemInstance();
			itemInstanceType.addNewItemID().addNewID().setStringValue(orderDesc.getPartNo());
			itemInstanceType.getItemIDArray(0).addNewRevisionID().setStringValue(orderDesc.getPartChg());
			
			if(orderDesc.getItem().getPartTitle()!=null)
			{
				itemInstanceType.addNewDescription().setStringValue(orderDesc.getItem().getPartTitle());				
			}
			
			if(orderDesc.getItem().getItemTypeDef() != null && StringUtils.isNotEmpty(orderDesc.getItemTypeDef().getId().getItemType()))
			{
				// Item Type
				ClassificationType classificationType = itemInstanceType.addNewClassification();

				SequencedCodeType codeType = classificationType.addNewCodes().addNewCode();
				codeType.setSequence(new BigInteger("1"));
				codeType.setName("PartType");
				codeType.setStringValue(orderDesc.getItemTypeDef().getId().getItemType());
				
				// Item Sub Type
				if(orderDesc.getItem().getItemSubtype() != null && StringUtils.isNotEmpty(orderDesc.getItemTypeDef().getId().getItemSubtype()))
				{
					SequencedCodeType codeSubType = classificationType.getCodes().addNewCode();
					codeSubType.setSequence(new BigInteger("2"));
					codeSubType.setName("PartSubType");
					codeSubType.setStringValue(orderDesc.getItemTypeDef().getId().getItemSubtype());
				}											
			}
			
			if(orderDesc.getWorkLocDef() != null)
			{
				// Add Planned Location
				LocationType locationType = productionOrderHeaderType.addNewSite();
				locationType.addNewID().setStringValue(orderDesc.getWorkLocDef().getWorkLocationId().getValue());
				
				if(orderDesc.getWorkLocDef().getLocTitle() != null) 
					locationType.addNewName().setStringValue(orderDesc.getWorkLocDef().getLocTitle());
			}
			
			// ExecutionTimePeriod 
			TimePeriodType executionTimePeriodType = productionOrderHeaderType.addNewExecutionTimePeriod();
			if(orderDesc.getActualStartDate()!=null)
			{
				executionTimePeriodType.xsetStartDateTime(oagiUtils.dateToDateTimeType(orderDesc.getActualStartDate()));
				if(orderDesc.getActualEndDate()!=null)
				{
					executionTimePeriodType.xsetEndDateTime(oagiUtils.dateToDateTimeType(orderDesc.getActualEndDate()));
				}
			}
			
			// ForecastedTimePeriod
			TimePeriodType forcastedTimePeriodType = productionOrderHeaderType.addNewForecastedTimePeriod();
			if(orderDesc.getSchedStartDate()!=null)
			{
				forcastedTimePeriodType.xsetStartDateTime(oagiUtils.dateToDateTimeType(orderDesc.getSchedStartDate()));
				if(orderDesc.getSchedEndDate()!=null)
				{
					forcastedTimePeriodType.xsetEndDateTime(oagiUtils.dateToDateTimeType(orderDesc.getSchedEndDate()));
				}
			}
		}
	}

	protected void mapPAARPlanInfo(RoutingHeaderType routingType, SolPaar solPaar) 
	{
		PlanRevId planRevId = solPaar.getPermissionDesc().getPlanRevId();
		PlanDesc planDesc = solPaar.getPermissionDesc().getPlanDesc();
				
		if(planRevId != null && planDesc != null)
		{			
			// Map Plan Info
			routingType.addNewDocumentID().addNewID().setStringValue(planDesc.getPlanNo());
			routingType.getDocumentID().addNewRevisionID().setStringValue(planRevId.getPlanRevision().toString());
			routingType.getDocumentID().addNewVariationID().setStringValue(planRevId.getPlanVersion().toString());
			
			// Plan Title
			if(StringUtils.isNotEmpty(planDesc.getPlanTitle()))
			{
				routingType.addNewDescription().setStringValue(planDesc.getPlanTitle());
			}
			
			// Plan Type
			routingType.addNewType().setStringValue(planDesc.getPlanType());
			
			// Bom No+
			if(planDesc.getMfgBomRev() != null)
			{
				DocumentIDType documentIdType = routingType.addNewBOMReference().addNewDocumentID();
				documentIdType.addNewID().setStringValue(StringUtils.defaultIfEmpty(planDesc.getMfgBomRev().getBomNo(), StringUtils.EMPTY)); // Need to changed
				documentIdType.addNewRevisionID().setStringValue(StringUtils.defaultIfEmpty(planDesc.getMfgBomRev().getMfgBomChg(), StringUtils.EMPTY)); // Need to changed
			}
			
			// Plan Item
			ManufacturingItemType manufacturingItemType = routingType.addNewManufacturingItem();
			
			ItemIDType itemIdType = manufacturingItemType.addNewItemID();
			itemIdType.addNewID().setStringValue(planDesc.getPartNo());
			itemIdType.addNewRevisionID().setStringValue(planDesc.getPartChg());
			
			// Item Title
			if(StringUtils.isNotEmpty(planDesc.getItem().getTitle()))
			{
				manufacturingItemType.addNewDescription().setStringValue(planDesc.getItem().getTitle());
			}
			
			if(planDesc.getItemTypeDef() != null && StringUtils.isNotEmpty(planDesc.getItemTypeDef().getId().getItemSubtype()))
			{
				// Item Sub Type
				ClassificationType classificationType = manufacturingItemType.addNewClassification();
				classificationType.setType("PartType");
				SequencedCodeType codeType = classificationType.addNewCodes().addNewCode();
				codeType.setSequence(new BigInteger("1"));
				codeType.setStringValue(planDesc.getItemTypeDef().getId().getItemSubtype());
			}
			
			// Item Type
			manufacturingItemType.addNewType().setStringValue(planDesc.getItem().getItemType());
			
			if(planDesc.getWorkLocDef() != null)
			{
				// Add Planned Location
				LocationType locationType = routingType.addNewSite();
				locationType.setType("WorkLocation");
				locationType.addNewID().setStringValue(planDesc.getWorkLocDef().getWorkLocationId().getValue());
				
				if(planDesc.getWorkLocDef().getLocTitle() != null) 
					locationType.addNewName().setStringValue(planDesc.getWorkLocDef().getLocTitle());
			}
		}
	}

	protected void mapPermissionSet(PermissionSetType permissionSetType, SolPaar solPaar,SolPaarWO solPaarWO) 
	{
		// Check if PAAR Request contains any Permission Set
		// Map User Permission
		if(solPaar!=null)
		{
			mapUserPermission(permissionSetType, solPaar,solPaarWO);
		}
			
		// Map Location Permission
		mapLocationPermission(permissionSetType, solPaar,solPaarWO);

		// Map Security Group Permission
		mapSecurityGroupPermission(permissionSetType, solPaar,solPaarWO);
	}

	private void mapUserPermission(PermissionSetType permissionSetType, SolPaar solPaar, SolPaarWO solPaarWO) 
	{
		List<Permission> userPermissionList = new ArrayList<Permission>();
		if(solPaar!=null)
		{
			//Plan PAAR Permissions
			userPermissionList = solPaar.getSghtPermissionSet().getSghtPermissionList();
		}
		if(solPaarWO!=null)
		{
			//WO PAAR Permissions
			userPermissionList = solPaarWO.getSghtPermissionSet().getSghtPermissionList();
		}
		
		// Add User Permission		
		for(Permission userPermission : userPermissionList)
		{
			if(StringUtils.equalsIgnoreCase(userPermission.getType(), SoluminaConstants.USER))
			{
				//<Permission>
				PermissionType permissionType = permissionSetType.addNewPermission();
				permissionType.setPermissionID(userPermission.getSghtId()); // utasgi_plg_paar_pmn_sght.sght_id
				permissionType.setPermissionAction(userPermission.getAction());
				permissionType.setEffect(EffectType.PERMIT);
				
				// <GrantedTo>
				PermissionEntityType permissionEntityType = permissionType.addNewGrantedTo();
				PersonnelType personnelType = permissionEntityType.addNewEmployee();
				personnelType.addNewID().setStringValue(userPermission.getTypeEntity()); //utasgi_plg_paar_pmn_sght.pmn_type_entity
				if(userPermission.getUser().getFullName() != null)
					personnelType.addNewName().setStringValue(userPermission.getUser().getFullName()); // sffnd_user.full_name
				
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

	private void mapLocationPermission(PermissionSetType permissionSetType, SolPaar solPaar, SolPaarWO solPaarWO) 
	{
		List<Permission> locationPermissionList = new ArrayList<Permission>();
		if(solPaar!=null)
		{
			//Plan PAAR Permissions
			locationPermissionList = solPaar.getSghtPermissionSet().getSghtPermissionList();
		}
		if(solPaarWO!=null)
		{
			//WO PAAR Permissions
			locationPermissionList = solPaarWO.getSghtPermissionSet().getSghtPermissionList();
		}
		// Add Location Permission
		for(Permission locationPermission : locationPermissionList)
		{
			if(StringUtils.equalsIgnoreCase(locationPermission.getType(), UtasConstants.LOCATION))
			{
				// <Permission>
				PermissionType permissionType = permissionSetType.addNewPermission();
				permissionType.setPermissionID(locationPermission.getSghtId()); // utasgi_plg_paar_pmn_sght.sght_id
				permissionType.setPermissionAction(locationPermission.getAction()); // utasgi_plg_paar_pmn_sght.pmn_action
				permissionType.setEffect(EffectType.PERMIT);
		
				// <GrantedTo><Facility>
				PermissionEntityType permissionEntityType = permissionType.addNewGrantedTo();
				FacilityType facilityType = permissionEntityType.addNewFacility();
				facilityType.addNewIDs().addNewID().setStringValue(locationPermission.getTypeEntity()); // utasgi_plg_paar_pmn_sght.pmn_type_entity
				if(locationPermission.getWorkloc()!=null && StringUtils.isNotEmpty(locationPermission.getWorkloc().getLocTitle()))
				{
					facilityType.addNewName().setStringValue(locationPermission.getWorkloc().getLocTitle()); // sffnd_work_loc_def.loc_title
				}
		
				// <EffectiveTimePeriod>
				if(locationPermission.getStartDate() != null || locationPermission.getEndDate() != null)
				{
					TimePeriodType timePeriodType = permissionType.addNewEffectiveTimePeriod();
					if(locationPermission.getStartDate() != null)
						timePeriodType.xsetStartDateTime(oagiUtils.dateToDateTimeType(locationPermission.getStartDate()));
					if(locationPermission.getEndDate() != null)
						timePeriodType.xsetEndDateTime(oagiUtils.dateToDateTimeType(locationPermission.getEndDate()));
				}
			}
		}
	}

	private void mapSecurityGroupPermission(PermissionSetType permissionSetType, SolPaar solPaar, SolPaarWO solPaarWO) 
	{
		List<Permission> securityGroupPermissionList = new ArrayList<Permission>();
		if(solPaar!=null)
		{
			//Plan PAAR Permissions
			securityGroupPermissionList = solPaar.getSghtPermissionSet().getSghtPermissionList();
		}
		if(solPaarWO!=null)
		{
			//WO PAAR Permissions
			securityGroupPermissionList = solPaarWO.getSghtPermissionSet().getSghtPermissionList();
		}
		// Add Security Group Permission
		for(Permission securityGroupPermission : securityGroupPermissionList)
		{
			if(StringUtils.equalsIgnoreCase(securityGroupPermission.getType(), "Security Group"))
			{
				// <Permission>
				PermissionType permissionType = permissionSetType.addNewPermission();
				permissionType.setPermissionID(securityGroupPermission.getSghtId()); // utasgi_plg_paar_pmn_sght.sght_id
				permissionType.setPermissionAction(securityGroupPermission.getAction()); // utasgi_plg_paar_pmn_sght.pmn_action
				permissionType.setEffect(EffectType.PERMIT);
				
				// <GrantedTo><PropertyType>
				PermissionEntityType permissionEntityType = permissionType.addNewGrantedTo();
				PropertyType propertyType = permissionEntityType.addNewProperty();
				NameValuePairType nameValuePairType = propertyType.addNewNameValue();
				nameValuePairType.setName("Security Group");
				nameValuePairType.setStringValue(securityGroupPermission.getTypeEntity());
				if(StringUtils.isNotEmpty(securityGroupPermission.getSecurityGroup().getSecurityGroupDescription()))
					propertyType.addNewDescription().setStringValue(securityGroupPermission.getSecurityGroup().getSecurityGroupDescription());
				
				// <EffectiveTimePeriod> only for Plan PAAR Request			
				if(solPaar!=null)
				{
					if(securityGroupPermission.getStartDate() != null || securityGroupPermission.getEndDate() != null)
					{
						TimePeriodType timePeriodType = permissionType.addNewEffectiveTimePeriod();
						if(securityGroupPermission.getStartDate() != null)
							timePeriodType.xsetStartDateTime(oagiUtils.dateToDateTimeType(securityGroupPermission.getStartDate()));
						if(securityGroupPermission.getEndDate() != null)
							timePeriodType.xsetEndDateTime(oagiUtils.dateToDateTimeType(securityGroupPermission.getEndDate()));
					}
				}
			}
		}
	}
	
	@Override
	public void validatePlanInfo(PermissionDesc permissionDesc, SolPAARType solPAARType) throws MappingException
	{
		if(permissionDesc.getPlanRevId() != null)
		{
			String planNo = permissionDesc.getPlanDesc().getPlanNo();
			Number planRevision = permissionDesc.getPlanRevId().getPlanRevision();
			Number planVersion = permissionDesc.getPlanRevId().getPlanVersion();
			
			if(solPAARType.getPAARHeader().getTarget() != null
					&& solPAARType.getPAARHeader().getTarget().getRouting() != null)
			{
				RoutingHeaderType routingHeaderType = solPAARType.getPAARHeader().getTarget().getRouting();
				if(routingHeaderType.isSetDocumentID())
				{
					String xmlPlanNo = routingHeaderType.getDocumentID().getID().getStringValue();
					String xmlPlanRevision = routingHeaderType.getDocumentID().getRevisionID().getStringValue();
					String xmlPlanVersion = routingHeaderType.getDocumentID().getVariationID().getStringValue();
					
					if(!StringUtils.equals(planNo, xmlPlanNo)
							|| !StringUtils.equals(String.valueOf(planRevision.intValue()), xmlPlanRevision)
							|| !StringUtils.equals(String.valueOf(planVersion.intValue()), xmlPlanVersion))
					{
						// Raise Error that Plan Info Provided in the XML does not matches with the Original Plan associated with Request Id
						throw new MappingException("Plan Info provided in the XML does not matches with the Original Plan assoicated with Request Id.");
					}						
				}
			}
		}
	}
	
	@Override
	public void validateOrderInfo(SolPaarWO solPaarWO, SolPAARType solPAARType) throws MappingException
	{
		if(solPaarWO.getOrderDesc() != null)
		{
			String orderNo = solPaarWO.getOrderDesc().getOrderNo();
			
			if(solPAARType.getPAARHeader().getTarget() != null
					&& solPAARType.getPAARHeader().getTarget().getProductionOrder() != null)
			{
				ProductionOrderHeaderType productionOrderHeaderType = solPAARType.getPAARHeader().getTarget().getProductionOrder();
				if(productionOrderHeaderType.isSetDocumentID())
				{
					String xmlOrderNo = productionOrderHeaderType.getDocumentID().getID().getStringValue();
										
					if(!StringUtils.equals(orderNo, xmlOrderNo))					
					{
						// Raise Error that Plan Info Provided in the XML does not matches with the Original Plan associated with Request Id
						throw new MappingException("Work Order Info provided in the XML does not matches with the Original Work Order assoicated with Request Id.");
					}						
				}
			}
		}
	}
	
	@Override
	public void mapPermissionList(List<Permission> permissionList, SolPAARType solPAARType)
	{
		if(solPAARType.getPermissionSet() != null
				&& solPAARType.getPermissionSet().getPermissionList() != null
				&& solPAARType.getPermissionSet().getPermissionList().size() > 0)
		{
			List<PermissionType> xmlPermissionList =  solPAARType.getPermissionSet().getPermissionList();
			for(PermissionType permissionType : xmlPermissionList)
			{
				Permission permission = new Permission();
				
				permission.setSghtId(permissionType.getPermissionID());
				permission.setAction(permissionType.getPermissionAction().toString().toUpperCase());
				permission.setEffect(permissionType.getEffect().toString().toUpperCase());
				
				PermissionEntityType permissionEntityType = permissionType.getGrantedTo();
				
				// Map User Permission
				if(permissionEntityType.getEmployee() != null)
				{
					permission.setType(SoluminaConstants.USER);
					permission.setTypeEntity(permissionEntityType.getEmployee().getIDArray(0).getStringValue());
				}
				
				// Map Location Permission
				else if(permissionEntityType.getFacility() != null)
				{
					permission.setType(UtasConstants.LOCATION);
					permission.setTypeEntity(permissionEntityType.getFacility().getIDs().getIDArray(0).getStringValue());
				}
				
				// Map Security Group Permission
				else if(permissionEntityType.getProperty() != null)
				{
					permission.setType("Security Group".toUpperCase());
					
					if(StringUtils.equalsIgnoreCase(permissionEntityType.getProperty().getNameValue().getName(), UtasConstants.SECURITYGROUP)
							|| StringUtils.equalsIgnoreCase(permissionEntityType.getProperty().getNameValue().getName(), "Security Group"))
					{
						permission.setTypeEntity(permissionEntityType.getProperty().getNameValue().getStringValue());
					}
				}
				
				if(permissionType.getEffectiveTimePeriod() != null)
				{
					if(permissionType.getEffectiveTimePeriod().getStartDateTime() != null)
					{
						permission.setStartDate(permissionType.getEffectiveTimePeriod().getStartDateTime().getTime());
					}
					if(permissionType.getEffectiveTimePeriod().getEndDateTime() != null)
					{
						permission.setEndDate(permissionType.getEffectiveTimePeriod().getEndDateTime().getTime());
					}
				}
				
				permissionList.add(permission);
			}
		}
	}
}
