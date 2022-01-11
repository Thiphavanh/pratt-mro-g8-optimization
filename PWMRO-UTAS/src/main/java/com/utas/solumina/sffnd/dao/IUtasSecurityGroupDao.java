package com.utas.solumina.sffnd.dao;

import java.util.List;
import java.util.Map;

public interface IUtasSecurityGroupDao 
{
	public List selectPlanSecurityGroup(String planId, Number planUpdateNumber);
	
	public List selectOrderSecurityGroup(String orderNo);
	
	public boolean securityGroupExistsInAnyRevision(String planNo, String securityGroup);
	
	public boolean isUserLastSecurityGroup(String planNo, Number planUpdtNo, String userId);
	
	public String selectOrderDescSecurityGroup(String orderNo);
	
	public int deleteWorkPlanSecurityGroup(String planNumber);
	
	public Map getPlanInfo(String planNumber);
	
	public String getPlanId(String planNumber);

}
