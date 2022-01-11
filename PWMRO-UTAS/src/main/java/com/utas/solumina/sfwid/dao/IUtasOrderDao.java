package com.utas.solumina.sfwid.dao;

import java.util.List;


public interface IUtasOrderDao
{

    /**
     * UTAS-450 
     * @param planId
     * @param planVersion
     * @param planRevision
     * @param planAlterations
     * @return
     */
    public boolean selectOpenPAARRequestExists(String planId, 
    										   Number planVersion,
    										   Number planRevision,
    										   Number planAlterations);
    
    /**
     *UTAS-487
     *
     * @param planId
     * @param planVersion
     * @param planRevision
     * @param planAlterations
     * @return
     */
    public boolean isWorkPlanHasSecurityGroup(String planId, 
			   								  Number planVersion,
			   								  Number planRevision,
			   								  Number planAlterations);
    
    /**
     * UTAS-487
     * 
     * @param workLocation
     * @return
     */
    public List selectSecurityGroupBasedOnWorkLocation(String workLocation);
}
