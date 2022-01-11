package com.utas.solumina.paar.integration;

import java.util.List;

import org.openapplications.oagis.x9.ProcessSolPAARDocument;
import org.openapplications.oagis.x9.SolPAARType;

import com.ibaset.solumina.integration.common.MappingAction;
import com.ibaset.solumina.integration.exception.MappingException;
import com.utas.solumina.paar.domain.Permission;
import com.utas.solumina.paar.domain.PermissionDesc;
import com.utas.solumina.paar.domain.SolPaarWO;

public interface PaarMapper 
{
	public void map(MappingAction[] outboundActions, ProcessSolPAARDocument processSolPAARDocument);
	public void validatePlanInfo(PermissionDesc permissionDesc, SolPAARType solPAARType) throws MappingException;
	public void mapPermissionList(List<Permission> permissionList, SolPAARType solPAARType);
	
	//UTAS-493
	public void validateOrderInfo(SolPaarWO solPaarWO,SolPAARType solPAARType) throws MappingException;
}
