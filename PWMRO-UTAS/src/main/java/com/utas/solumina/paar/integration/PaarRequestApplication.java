package com.utas.solumina.paar.integration;

import java.util.List;

import com.utas.solumina.paar.domain.Permission;
import com.utas.solumina.paar.domain.PermissionDesc;
import com.utas.solumina.paar.domain.SolPaar;
import com.utas.solumina.paar.domain.SolPaarWO;

public interface PaarRequestApplication 
{
	public PermissionDesc selectPermissionDesc(String requestId);
	public void savePermissionList(String tableName, String requestId, List<Permission> permissionList);
	public SolPaar buildSolPaar(String requestId);
	
	/**
	 * UTAS-465
	 * 
	 * @param requestId
	 */
	public void authorizedSecGrpToPlanAndUser(String requestId);
	
	/**
	 * UTAS-493
	 * @param requestId
	 * @return SolPaarWO
	 */
	public SolPaarWO selectPaarWORequestInfo(String requestId);

	/**
	 * UTAS-493
	 * @param requestId
	 * @return SolPaarWO
	 */
	public SolPaarWO buildSolPaarWO(String requestId);
	
	/**
	 * UTAS-493
	 * @param requestId
	 * @return 
	 */
	public void authorizedSecGrpToOrder(String requestId);
}
