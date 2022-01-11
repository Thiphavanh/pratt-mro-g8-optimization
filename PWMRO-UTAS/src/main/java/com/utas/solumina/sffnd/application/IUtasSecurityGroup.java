package com.utas.solumina.sffnd.application;

public interface IUtasSecurityGroup 
{
	public void insertWorkPlanSecurityGroup(String planId,
											Number planVersion,
											Number planRevision,
											Number planAlterations, 
											String inheritedSecurityGroupFlag, 
											String securityGroup,
											Number planUpdtNo);
	
	public void deleteWorkPlanSecurityGroup (String planNumber,
			  								 Number planVersion,
			  								 Number planRevision,
			  								 Number planAlterations, 
			  								 String inheritedSecurityGroupFlag, 
			  								 String securityGroup,
			  								 Number planUpdtNo);
}
