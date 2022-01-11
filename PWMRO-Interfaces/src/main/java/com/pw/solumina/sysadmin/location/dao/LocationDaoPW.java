/*
* This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    LocationDaoPW.java
* 
*  Created: 2017-10-05
* 
*  Author:  C293011
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-10-05 	C293011		    Initial Release SMRO_MD_M207
* 2017-11-27    R. Thorpe		Added method existsWorkLocation 
*/


package com.pw.solumina.sysadmin.location.dao;

import java.util.List;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;

/**
 * @author C293011
 *
 */
public class LocationDaoPW 
{

	@Reference private JdbcDaoSupport jdbcDao;

    /** 
     * SMRO_MD_M207 -October, 2017
     * 
     * @author c293011 Raeann Thorpe
     *
     * @param workDept
     * @return
     */
    public boolean existsWorkDept(String workDept) 
    {
    	boolean workDeptExists = false;
    	
         StringBuffer selectSql = new StringBuffer().append("SELECT ? ")
        		 									.append("FROM SFFND_WORK_DEPT_DEF ")
        		 									.append("WHERE WORK_DEPT = ? ");
         
         ParameterHolder params = new ParameterHolder();
         params.addParameter(SoluminaConstants.X);
         params.addParameter(workDept);
         
         List list = jdbcDao.queryForList(selectSql.toString(), params);
         
         if (!list.isEmpty())
         {
        	 workDeptExists = true;
         }
         return workDeptExists;
    }

    /** 
     * SMRO_MD_M207 -October, 2017
     * 
     * @author c293011 Raeann Thorpe
     *
     * @param workDept
     * @param workCenter
     * @return
     */
    public boolean existsWorkCenter(String workDept, String workCenter) 
    {
    	boolean workCenterExists = false;
    	
         StringBuffer selectSql = new StringBuffer().append("SELECT ? ")
        		 									.append("  FROM SFFND_WORK_DEPT_DEF D,")
        		 									.append("       SFFND_WORK_CENTER_DEF C")
        		 									.append(" WHERE D.DEPARTMENT_ID = C.DEPARTMENT_ID ")
        		 									.append("   AND WORK_DEPT = ? ")
        		 									.append("   AND WORK_CENTER = ? ");
         
         ParameterHolder params = new ParameterHolder();
         params.addParameter(SoluminaConstants.X);
         params.addParameter(workDept);
         params.addParameter(workCenter);
         
         List list = jdbcDao.queryForList(selectSql.toString(), params);
         
         if (!list.isEmpty())
         {
        	 workCenterExists = true;
         }
         return workCenterExists;
    }

    /** 
     * SMRO_MD_M207 -October, 2017
     * 
     * @author c293011 Raeann Thorpe
     *
     * @param workLoc
     * @param workDept
     * @param workCenter
     * @return
     */ 
    public boolean existsWorkCenter(String workLoc, String workDept, String workCenter) 
    {
    	 boolean workCenterExists = false;
    	
         StringBuffer selectSql = new StringBuffer().append("SELECT ?")
        		                                    .append("  FROM SFFND_WORK_LOC_DEF L, ")
        		                                    .append("       SFFND_WORK_DEPT_DEF D, ")
        		                                    .append("       SFFND_WORK_CENTER_DEF C")
        		                                    .append(" WHERE L.LOCATION_ID = D.LOCATION_ID")
        		                                    .append("   AND D.LOCATION_ID = C.LOCATION_ID")
        		                                    .append("   AND D.DEPARTMENT_ID = C.DEPARTMENT_ID")
        		                                    .append("   AND L.WORK_LOC = ?")
        		                                    .append("   AND D.WORK_DEPT = ?")
        		                                    .append("   AND C.WORK_CENTER = ?");
        
         ParameterHolder params = new ParameterHolder();
         params.addParameter(SoluminaConstants.X);
         params.addParameter(workLoc);
         params.addParameter(workDept);
         params.addParameter(workCenter);
         
         List list = jdbcDao.queryForList(selectSql.toString(), params);
         
         if (!list.isEmpty())
         {
        	 workCenterExists = true;
         }
             
         return workCenterExists;
    }

    public boolean existsWorkLocation(String workLoc) 
    {
    	boolean exists = false;
    	
         StringBuffer selectSql = new StringBuffer().append("SELECT ? ")
        		 									.append("  FROM SFFND_WORK_LOC_DEF")
        		 									.append(" WHERE WORK_LOC = ?")
        		 									.append("   AND OBSOLETE_RECORD_FLAG = 'N' ");
         
         ParameterHolder params = new ParameterHolder();
         params.addParameter(SoluminaConstants.X);
         params.addParameter(workLoc);
         
         List list = jdbcDao.queryForList(selectSql.toString(), params);
         
         if (!list.isEmpty())
         {
        	 exists = true;
         }
         return exists;
    }
    
    public String getWorkLocationID(String workLoc)
    {
    	StringBuffer selectSql = new StringBuffer().append(" SELECT LOCATION_ID ")
    			                                   .append("   FROM SFFND_WORK_LOC_DEF ")
    			                                   .append("  WHERE WORK_LOC = ?");

    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(workLoc);
    	
    	String workLocationID = jdbcDao.queryForString(selectSql.toString(), params);
    	
    	return workLocationID;
    }
    
    public String getWorkDeptID(String workLocationID, 
                                String workDept)
    {
    	StringBuffer selectSql = new StringBuffer().append(" SELECT DEPARTMENT_ID ")
    			                                   .append("   FROM SFFND_WORK_DEPT_DEF")
    			                                   .append("  WHERE LOCATION_ID = ?")
    			                                   .append("    AND WORK_DEPT = ?");

    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(workLocationID);
    	params.addParameter(workDept);
    	
    	String workDepartmentID = jdbcDao.queryForString(selectSql.toString(), params);

    	return workDepartmentID;
    }
    
    public String getWorkCenterID(String workLocationID, 
                                  String workDepartmentID, 
                                  String workCenter)
    {
    	StringBuffer selectSql = new StringBuffer().append(" SELECT CENTER_ID ")
    			                                   .append("   FROM SFFND_WORK_CENTER_DEF ")
    			                                   .append("  WHERE LOCATION_ID = ?")
    			                                   .append("    AND DEPARTMENT_ID = ?")
    			                                   .append("    AND WORK_CENTER = ?");

    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(workLocationID);
    	params.addParameter(workDepartmentID);
    	params.addParameter(workCenter);

    	String workCenterID = jdbcDao.queryForString(selectSql.toString(), params);

    	return workCenterID;
    }
    
	public int updateWorkCenterType(String workLocationID,
                                    String workDepartmentID,
                                    String workCenterID,
                                    String workCenterType)
	{
		StringBuffer updateSql = new StringBuffer().append("UPDATE SFFND_WORK_CENTER_DEF ")
				                                   .append("   SET WORK_CENTER_TYPE = ? ")
				                                   .append(" WHERE LOCATION_ID = ?")
    			                                   .append("   AND DEPARTMENT_ID = ?")
				                                   .append("   AND CENTER_ID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(workCenterType);
		params.addParameter(workLocationID);
		params.addParameter(workDepartmentID);
		params.addParameter(workCenterID);
		
		int rowCount = jdbcDao.update(updateSql.toString(), params);

		return rowCount;
	}
}
