package com.utas.solumina.paar.domain;

public class SolPaar 
{
	private PermissionDesc permissionDesc;
	private SghtPermissionSet sghtPermissionSet;
	private AuthPermissionSet authPermissionSet;
	
	public SolPaar() 
	{
	}
	
	public SolPaar(PermissionDesc permissionDesc,
				   SghtPermissionSet sghtPermissionSet,
				   AuthPermissionSet authPermissionSet) 
	{
		this.permissionDesc = permissionDesc;
		this.sghtPermissionSet = sghtPermissionSet;
		this.authPermissionSet = authPermissionSet;
	}
	
	public PermissionDesc getPermissionDesc() 
	{
		return permissionDesc;
	}
	
	public void setPermissionDesc(PermissionDesc permissionDesc) 
	{
		this.permissionDesc = permissionDesc;
	}
	
	public SghtPermissionSet getSghtPermissionSet() 
	{
		return sghtPermissionSet;
	}
	
	public void setSghtPermissionSet(SghtPermissionSet sghtPermissionSet) 
	{
		this.sghtPermissionSet = sghtPermissionSet;
	}
	
	public AuthPermissionSet getAuthPermissionSet() 
	{
		return authPermissionSet;
	}
	
	public void setAuthPermissionSet(AuthPermissionSet authPermissionSet) 
	{
		this.authPermissionSet = authPermissionSet;
	}
}
