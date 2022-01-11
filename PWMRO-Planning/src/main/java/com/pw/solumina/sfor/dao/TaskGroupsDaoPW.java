/*
 * This unpublished work, first created in 2017 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2017, 2020.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    TaskGroupsDaoPW.java
 * 
 *  Created: 2017-07-26
 * 
 *  Author:  c293011
 * 
 *  Revision History
 * 
 *  Date      	Who             Description
 *  ----------	---------------	---------------	---------------------------------------------------
 * 2017-07-26 	c293011		    Initial Release SMRO_PLG_205 Task Groups
 * 2017-09-28   R. Thorpe		added methods for copying Custom Task Tables during Plan Copy and Plan Revisions.
 *                              update custom tables when Obsolete and Un-Obsolete Task Groups.
 * 2017-11-07   R. Thorpe       Defect 181 - modify selectTaskGroupsForPlan  
 * 2017-11-13   R. Thorpe       updated update methods to include time_stamp, last_action and updt_userid   
 * 2018-01-02   R. Thorpe       SMRO_PLG_205 SCR-004 add methods doesWorkscopeExist and doesNetworkExist
 * 2020-12-07   J DeNinno       defect 1814
 * 2020-12-18   John DeNinno    defect 1841 fix pk problem
 * 2021-01-11   John DeNinno    defect 1846 make it a whole number so it can find the correct revision
 * 2021-03-09		S. Edgerly		SMRO_CV_301 - Defect 1875 - TEC Conversion Extract from G5
 */

package com.pw.solumina.sfor.dao;

import java.util.List;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.common.security.context.ContextUtil;

public class TaskGroupsDaoPW 
{
	@Reference private ExtensionDaoSupport eds;

	public int updateTaskGroupAuthority(String planId, 
								        Number planUpdtNo,
								        Number subjectNumber,
								        Number subjectRev,
								        String authority)
	{
	    //// Explain Plan cost =
		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFOR_SFPL_PLAN_SUBJECT")
											       .append("    SET AUTHORITY = ?,")
											       .append("        UPDT_USERID = ?,")
				                                   .append("        LAST_ACTION = 'UPDATED',")
				                                   .append("        TIME_STAMP = SYSDATE")
											       .append("  WHERE PLAN_ID = ?")
											       .append("    AND PLAN_UPDT_NO = ?")
											   	   .append("    AND SUBJECT_NO = ?")
											   	   .append("    AND SUBJECT_REV = ?");
									
		ParameterHolder params = new ParameterHolder();
		params.addParameter(authority);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(subjectRev);


		int rowCount = eds.update(updateSql.toString(), params);

		return rowCount;
	}
	
	public List selectTaskGroupsForPlan(String planId, 
			                           Number planVersion,
			                           Number planRevision,
			                           Number planAlterations)
	{
		//// Explain Plan cost =
		StringBuffer selectSql = new StringBuffer().append("SELECT B.SUBJECT_NO,")
				                                   .append("       B.SUBJECT_REV,") 
				                                   .append("       C.PROGRAM,") 
				                                   .append("       B.PLAN_UPDT_NO")
				                                   .append("  FROM SFPL_PLAN_REV A, ")
				                                   .append("       SFOR_SFPL_PLAN_SUBJECT B,")
				                                   .append("       SFPL_PLAN_DESC C")
				                                   .append(" WHERE A.PLAN_ID = B.PLAN_ID")
				                                   .append("   AND A.PLAN_UPDT_NO = B.PLAN_UPDT_NO")
				                                   .append("   AND A.PLAN_ID = C.PLAN_ID")
				                                   .append("   AND A.PLAN_UPDT_NO = C.PLAN_UPDT_NO")
				                                   .append("   AND A.PLAN_ID = ?")
				                                   .append("   AND A.PLAN_VERSION = ?")
				                                   .append("   AND A.PLAN_REVISION = ?")
				                                   .append("   AND A.PLAN_ALTERATIONS = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planVersion);
		params.addParameter(planRevision);
		params.addParameter(planAlterations);

		return eds.queryForList(selectSql.toString(), params);
	}

	public void insertTaskGroup(String planId,  
							    Number planUpdtNo,
							    Number subjectNumber,
							    Number subjectRev,
							    String mandatory,
							    String obsoleteRecordFlag /*Defect 1875*/)
	{
	    //// Explain Plan cost =
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_SFOR_SFPL_PLAN_SUBJECT")
				                                   .append(" (PLAN_ID,")
				                                   .append("  PLAN_UPDT_NO,")
				                                   .append("  SUBJECT_NO,")
				                                   .append("  SUBJECT_REV,")
				                                   .append("  MANDATORY, ")
				                                   .append("  UPDT_USERID,")
				                                   .append("  OBSOLETE_RECORD_FLAG)")//Defect 1875
				                                   .append("  VALUES (")
				                                   .append("  ?,")
				                                   .append("  ?,")
				                                   .append("  ?,")
				                                   .append("  ?,")
				                                   .append("  ?,")
				                                   .append("  ?,") //Defect 1875
				                                   .append("  ?)");
                                             
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(subjectNumber);	
		params.addParameter(subjectRev);
		params.addParameter(mandatory);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(obsoleteRecordFlag); //Defect 1875
		eds.insert(insertSql.toString(), params);
	}
	
	public void deleteTaskGroup(String planId,  
			                    Number planUpdtNo,
			                    Number subjectNumber,
			                    Number subjectRev)
	{
		//// Explain Plan cost = 0
		StringBuffer deleteSql = new StringBuffer().append("DELETE FROM PWUST_SFOR_SFPL_PLAN_SUBJECT")
				                                   .append("  WHERE PLAN_ID = ?")
				                                   .append("    AND PLAN_UPDT_NO = ?")
				                                   .append("    AND SUBJECT_NO = ?")
				                                   .append("    AND SUBJECT_REV = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(subjectRev);
		
		eds.delete(deleteSql.toString(), params);
	}
	
	public int updateTaskGroupMandatoryFlag(String planId, 
									        Number planUpdtNo,
									        Number subjectNumber,
									        String mandatory)
	{
	    //// Explain Plan cost =
		StringBuffer updateSql = new StringBuffer().append("UPDATE PWUST_SFOR_SFPL_PLAN_SUBJECT")
				                                   .append("   SET MANDATORY = ?,")
				                                   .append("       UPDT_USERID = ?,")
				                                   .append("       LAST_ACTION = 'UPDATED',")
				                                   .append("       TIME_STAMP = SYSDATE")
				                                   .append(" WHERE PLAN_ID = ?")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(mandatory);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(subjectNumber);

		int rowCount = eds.update(updateSql.toString(), params);

		return rowCount;
	}
	
	public String selectCurrentWorkscope(String planId, Number planUpdtNo, Number subjectNumber, Number subjectRev)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT WORKSCOPE")
				                                   .append("  FROM PWUST_SFOR_SFPL_PLAN_SUBJECT")
				                                   .append(" WHERE PLAN_ID = ?")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?")
				                                   .append("   AND SUBJECT_REV = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);	
		params.addParameter(subjectNumber);
		params.addParameter(subjectRev);
		
		String currentWorkscope = eds.queryForString(selectSql.toString(), params);

		return currentWorkscope;
	}
	
	public void insertWorkscope(String planId, Number planUpdtNo, Number subjectNumber, Number subjectRev, String workscope)
	{
	    //// Explain Plan cost =
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_PL_SUBJ_WORKSCOPE")
				                                   .append(" (PLAN_ID,")
				                                   .append("  PLAN_UPDT_NO,")
				                                   .append("  SUBJECT_NO,")
				                                   .append("  SUBJECT_REV,")
				                                   .append("  WORKSCOPE,")
				                                   .append("  UPDT_USERID)")						                         
				                                   .append("  VALUES (")
				                                   .append("  ?,")
				                                   .append("  ?,")
				                                   .append("  ?,")
				                                   .append("  ?,")
				                                   .append("  ?,")
				                                   .append("  ? )");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);	
		params.addParameter(subjectNumber);
		params.addParameter(subjectRev);
		params.addParameter(workscope);
		params.addParameter(ContextUtil.getUsername());
		
		eds.update(insertSql.toString(), params);
	}
	
	public int deleteWorkscope(String planId, Number planUpdtNo, Number subjectNumber, Number subjectRev, String workscope)
	{
	    //// Explain Plan cost =
		StringBuffer updateSql = new StringBuffer().append("DELETE FROM PWUST_PL_SUBJ_WORKSCOPE")
				                                   .append(" WHERE PLAN_ID = ?")
				                               	   .append("   AND PLAN_UPDT_NO = ?")
				                               	   .append("   AND SUBJECT_NO = ?")
				                               	   .append("   AND SUBJECT_REV = ?")
				                               	   .append("   AND WORKSCOPE = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);	
		params.addParameter(subjectNumber);
		params.addParameter(subjectRev);
		params.addParameter(workscope);
		
		int rowCount = eds.update(updateSql.toString(), params);

		return rowCount;
	}
	
	public void insertSuperiorNetwork(String planId, Number planUpdtNo, Number subjectNumber, Number subjectRev, String engineType, String superiorNetwork)
	{
	    //// Explain Plan cost =
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_PL_SUBJ_NETWORK_ACTIVITY")
                                                   .append(" (PLAN_ID,")
                                                   .append("  PLAN_UPDT_NO,")
                                                   .append("  SUBJECT_NO,")
                                                   .append("  SUBJECT_REV,")
                                                   .append("  ENGINE_TYPE,")
                                                   .append("  SUPERIOR_NETWORK_ACT,")
                                                   .append("  UPDT_USERID)")	
                                                   .append("  VALUES (")
                                                   .append("  ?,") 
                                                   .append("  ?,")
                                                   .append("  ?,")
                                                   .append("  ?,")
                                                   .append("  ?,")
                                                   .append("  ?,")
                                                   .append("  ?)");
		
		ParameterHolder params = new ParameterHolder();

		params.addParameter(planId);
		params.addParameter(planUpdtNo);	
		params.addParameter(subjectNumber);
		params.addParameter(subjectRev);
		params.addParameter(engineType);
		params.addParameter(superiorNetwork);
		params.addParameter(ContextUtil.getUsername());
		
		eds.insert(insertSql.toString(), params);
	}

	public boolean doesSuperiorNetworkExist(String planId, Number planUpdtNo, Number subjectNumber, Number subjectRev, String engineType)
	{
	    //// Explain Plan cost =
		StringBuffer selectSql = new StringBuffer().append("SELECT COUNT(SUPERIOR_NETWORK_ACT)")
				                                   .append("  FROM PWUST_PL_SUBJ_NETWORK_ACTIVITY")
				                                   .append(" WHERE PLAN_ID = ?")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?")
				                                   .append("   AND SUBJECT_REV = ?")
				                                   .append("   AND ENGINE_TYPE = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);	
		params.addParameter(subjectNumber);
		params.addParameter(subjectRev);
		params.addParameter(engineType);

        int count  = eds.queryForInt(selectSql.toString(), params);

		boolean superiorNetworkExists;

		if (count > 0)
		{
			superiorNetworkExists = true;
		}
		else
		{
			superiorNetworkExists = false;
		}

		return superiorNetworkExists;
	}
	
	public String selectSuperiorNetwork(String planId, Number planUpdtNo, Number subjectNumber, Number subjectRev, String engineType)
	{
	    //// Explain Plan cost =
		StringBuffer selectSql = new StringBuffer().append("SELECT SUPERIOR_NETWORK_ACT")
				                                   .append("  FROM PWUST_PL_SUBJ_NETWORK_ACTIVITY")
				                                   .append(" WHERE PLAN_ID = ?")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?")
				                                   .append("   AND SUBJECT_REV = ?")
				                                   .append("   AND ENGINE_TYPE = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);	
		params.addParameter(subjectNumber);
		params.addParameter(subjectRev);
		params.addParameter(engineType);

        String superiorNetwork  = eds.queryForString(selectSql.toString(), params);

		return superiorNetwork;
	}
	
	public int deleteSuperiorNetwork(String planId, Number planUpdtNo, Number subjectNumber, Number subjectRev, String engineType, String superiorNetwork)
	{
	    //// Explain Plan cost =
		StringBuffer deleteSql = new StringBuffer().append("DELETE FROM PWUST_PL_SUBJ_NETWORK_ACTIVITY")
											       .append(" WHERE PLAN_ID = ?")
											       .append("   AND PLAN_UPDT_NO = ?")
											       .append("   AND SUBJECT_NO = ?")
											       .append("   AND SUBJECT_REV = ?")
											       .append("   AND ENGINE_TYPE = ?")
											       .append("   AND SUPERIOR_NETWORK_ACT = ?");
		
		ParameterHolder params = new ParameterHolder();

		params.addParameter(planId);
		params.addParameter(planUpdtNo);	
		params.addParameter(subjectNumber);
		params.addParameter(subjectRev);
		params.addParameter(engineType);
		params.addParameter(superiorNetwork);
		
		int rowCount = eds.delete(deleteSql.toString(), params);

		return rowCount;
	}

	public void insertSubNetwork(String planId, Number planUpdtNo, Number subjectNumber, Number subjectRev, String engineType, String superiorNetwork, String subNetwork)
	{
	    //// Explain Plan cost =
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_PL_SUBJ_SUBNET_ACTIVITY")
                                                   .append(" (PLAN_ID,")
                                                   .append("  PLAN_UPDT_NO,")
                                                   .append("  SUBJECT_NO,")
                                                   .append("  SUBJECT_REV,")
                                                   .append("  ENGINE_TYPE,")
                                                   .append("  SUPERIOR_NETWORK_ACT,")
                                                   .append("  SUB_NETWORK_ACT,")
                                                   .append("  UPDT_USERID)")	
                                                   .append("  VALUES (")
                                                   .append("  ?,") 
                                                   .append("  ?,")
                                                   .append("  ?,")
                                                   .append("  ?,")
                                                   .append("  ?,")
                                                   .append("  ?,")
                                                   .append("  ?,")
                                                   .append("  ?)");
		
		ParameterHolder params = new ParameterHolder();

		params.addParameter(planId);
		params.addParameter(planUpdtNo);	
		params.addParameter(subjectNumber);
		params.addParameter(subjectRev);
		params.addParameter(engineType);
		params.addParameter(superiorNetwork);
		params.addParameter(subNetwork);
		params.addParameter(ContextUtil.getUsername());
		
		eds.insert(insertSql.toString(), params);
	}
	
	public int deleteSubNetwork(String planId, Number planUpdtNo, Number subjectNumber, Number subjectRev, String engineType, String superiorNetwork, String subNetwork)
	{
	    //// Explain Plan cost =
		StringBuffer deleteSql = new StringBuffer().append("DELETE FROM PWUST_PL_SUBJ_SUBNET_ACTIVITY")
				                                   .append(" WHERE PLAN_ID = ?")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?")
				                                   .append("   AND SUBJECT_REV = ?")
				                                   .append("   AND ENGINE_TYPE = ?")
				                                   .append("   AND SUPERIOR_NETWORK_ACT = ?")
				                                   .append("   AND SUB_NETWORK_ACT = ?");
		
		ParameterHolder params = new ParameterHolder();

		params.addParameter(planId);
		params.addParameter(planUpdtNo);	
		params.addParameter(subjectNumber);
		params.addParameter(subjectRev);
		params.addParameter(engineType);
		params.addParameter(superiorNetwork);
		params.addParameter(subNetwork);
		
		int rowCount = eds.delete(deleteSql.toString(), params);

		return rowCount;
	}
	
	public boolean isCustomerAllSelected(String planId, Number planUpdtNo, Number subjectNumber, Number subjectRev)
	{
	    //// Explain Plan cost =
		StringBuffer selectSql = new StringBuffer().append("SELECT COUNT(CUST_ID)")
				                                   .append("  FROM PWUST_PL_SUBJ_CUSTOMER")
				                                   .append(" WHERE PLAN_ID = ?")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?")
				                                   .append("   AND SUBJECT_REV = ?")
				                                   .append("   AND CUST_ID = 'ALL'");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);	
		params.addParameter(subjectNumber);
		params.addParameter(subjectRev);

        int count  = eds.queryForInt(selectSql.toString(), params);

		boolean customerAllExists;

		if (count > 0)
		{
			customerAllExists = true;
		}
		else
		{
			customerAllExists = false;
		}

		return customerAllExists;
	}

	public boolean doCustomersExist(String planId, Number planUpdtNo, Number subjectNumber, Number subjectRev)
	{
	    //// Explain Plan cost =
		StringBuffer selectSql = new StringBuffer().append("SELECT COUNT(CUST_ID)")
				                                   .append("  FROM PWUST_PL_SUBJ_CUSTOMER")
				                                   .append(" WHERE PLAN_ID = ?")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?")
				                                   .append("   AND SUBJECT_REV = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);	
		params.addParameter(subjectNumber);
		params.addParameter(subjectRev);

        int count  = eds.queryForInt(selectSql.toString(), params);

		boolean customersExist;

		if (count > 0)
		{
			customersExist = true;
		}
		else
		{
			customersExist = false;
		}

		return customersExist;
	}
	
	public boolean doesCustomerExceptionsExist(String planId, Number planUpdtNo, Number subjectNumber, Number subjectRev)
	{
	    //// Explain Plan cost =
		StringBuffer selectSql = new StringBuffer().append("SELECT COUNT(CUST_ID_EXCEPTION)")
				                                   .append("  FROM PWUST_PL_SUBJ_CUST_EXCEPT")
				                                   .append(" WHERE PLAN_ID = ?")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?")
				                                   .append("   AND SUBJECT_REV = ?")
				                                   .append("   AND CUST_ID = 'ALL'");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);	
		params.addParameter(subjectNumber);
		params.addParameter(subjectRev);

        int count  = eds.queryForInt(selectSql.toString(), params);

		boolean customerExceptionsExist;

		if (count > 0)
		{
			customerExceptionsExist = true;
		}
		else
		{
			customerExceptionsExist = false;
		}

		return customerExceptionsExist;
	}
	
	public boolean doesSubNetworkExist(String planId, Number planUpdtNo, Number subjectNumber, Number subjectRev, String engineType, String superiorNetwork)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT COUNT(SUB_NETWORK_ACT)")
				                                   .append("  FROM PWUST_PL_SUBJ_SUBNET_ACTIVITY")
				                                   .append(" WHERE PLAN_ID = ?")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?")
				                                   .append("   AND SUBJECT_REV = ?")
				                                   .append("   AND ENGINE_TYPE = ?")
				                                   .append("   AND SUPERIOR_NETWORK_ACT = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);	
		params.addParameter(subjectNumber);
		params.addParameter(subjectRev);
		params.addParameter(engineType);
		params.addParameter(superiorNetwork);

        int count  = eds.queryForInt(selectSql.toString(), params);

		boolean subNetworkExists;

		if (count > 0)
		{
			subNetworkExists = true;
		}
		else
		{
			subNetworkExists = false;
		}

		return subNetworkExists;
	}
	
	public void insertCustomer(String planId, Number planUpdtNo, Number subjectNumber, Number subjectRev, String customer)
	{
	    //// Explain Plan cost =
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_PL_SUBJ_CUSTOMER")
                                                   .append(" (PLAN_ID,")
                                                   .append("  PLAN_UPDT_NO,")
                                                   .append("  SUBJECT_NO,")
                                                   .append("  SUBJECT_REV,")
                                                   .append("  CUST_ID,")
                                                   .append("  UPDT_USERID)")	
                                                   .append("  VALUES (")
                                                   .append("  ?,") 
                                                   .append("  ?,")
                                                   .append("  ?,")
                                                   .append("  ?,")
                                                   .append("  ?,")
                                                   .append("  ?)");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);	
		params.addParameter(subjectNumber);
		params.addParameter(subjectRev);
		params.addParameter(customer);
		params.addParameter(ContextUtil.getUsername());

        eds.update(insertSql.toString(), params);
	}

	public int deleteCustomer(String planId, Number planUpdtNo, Number subjectNumber, Number subjectRev, String customer)
	{
	    //// Explain Plan cost =
		StringBuffer deleteSql = new StringBuffer().append("DELETE FROM PWUST_PL_SUBJ_CUSTOMER")
			                                  	   .append(" WHERE PLAN_ID = ?")
			                                  	   .append("   AND PLAN_UPDT_NO = ?")
			                                  	   .append("   AND SUBJECT_NO = ?")
			                                  	   .append("   AND SUBJECT_REV = ?")
			                                  	   .append("   AND CUST_ID = ?");
		
		ParameterHolder params = new ParameterHolder();

		params.addParameter(planId);
		params.addParameter(planUpdtNo);	
		params.addParameter(subjectNumber);
		params.addParameter(subjectRev);
		params.addParameter(customer);
		
		int rowCount = eds.delete(deleteSql.toString(), params);

		return rowCount;
	}

	public void insertCustomerException(String planId, Number planUpdtNo, Number subjectNumber, Number subjectRev, String customer, String customerException)
	{
	    //// Explain Plan cost =
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_PL_SUBJ_CUST_EXCEPT")
                                                   .append(" (PLAN_ID,")
                                                   .append("  PLAN_UPDT_NO,")
                                                   .append("  SUBJECT_NO,")
                                                   .append("  SUBJECT_REV,")
                                                   .append("  CUST_ID,")
                                                   .append("  CUST_ID_EXCEPTION,")
                                                   .append("  UPDT_USERID)")	
                                                   .append("  VALUES (")
                                                   .append("  ?,")
                                                   .append("  ?,")
                                                   .append("  ?,")
                                                   .append("  ?,")
                                                   .append("  ?,")
                                                   .append("  ?,")
                                                   .append("  ?)");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);	
		params.addParameter(subjectNumber);
		params.addParameter(subjectRev);
		params.addParameter(customer);
		params.addParameter(customerException);
		params.addParameter(ContextUtil.getUsername());

		eds.insert(insertSql.toString(), params);
	}
	
	public int deleteCustomerException(String planId, Number planUpdtNo, Number subjectNumber, Number subjectRev, String customer, String customerException)
	{
		StringBuffer deleteSql = new StringBuffer().append("DELETE FROM PWUST_PL_SUBJ_CUST_EXCEPT")
											       .append(" WHERE PLAN_ID = ?")
											       .append("   AND PLAN_UPDT_NO = ?")
											       .append("   AND SUBJECT_NO = ?")
											       .append("   AND SUBJECT_REV = ?")
											       .append("   AND CUST_ID = ?")
											       .append("   AND CUST_ID_EXCEPTION = ?");
		
		ParameterHolder params = new ParameterHolder();
		
		params.addParameter(planId);
		params.addParameter(planUpdtNo);	
		params.addParameter(subjectNumber);
		params.addParameter(subjectRev);
		params.addParameter(customer);
		params.addParameter(customerException);
		
		int rowCount = eds.delete(deleteSql.toString(), params);

		return rowCount;
	}
	
	public String selectWorkscopeTitle(String workscope, 
			                           String workLoc,
			                           String engineType)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT WORKSCOPE_TITLE")
				                                   .append("   FROM PWUST_WORKSCOPE_DEF")
				                                   .append("  WHERE WORKSCOPE = ?")
				                                   .append("    AND WORK_LOC = ?")
				                                   .append("    AND ENGINE_TYPE = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(workscope);
		params.addParameter(workLoc);
		params.addParameter(engineType);
		
		String workscopeTitle = eds.queryForString(selectSql.toString(), params);

		return workscopeTitle;
	}
	
	public String getPartItemIdForPlan(String planId, 
			                           Number planUpdtNo)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT ITEM_ID")
				                                   .append("   FROM SFPL_ITEM_DESC_MASTER_ALL I")
				                                   .append("  WHERE I.PART_NO = (SELECT P.PART_NO ")
				                                   .append("                       FROM SFPL_PLAN_DESC P")
				                                   .append("                      WHERE P.PLAN_ID = ?")
				                                   .append("                        AND P.PLAN_UPDT_NO = ?)");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);

		String itemId = eds.queryForString(selectSql.toString(), params);

		return itemId;
	}
	
	public String getPartNoForPlan(String planId, 
			                       Number planUpdtNo)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT P.PART_NO ")
				                                   .append("   FROM SFPL_PLAN_DESC P")
				                                   .append("  WHERE P.PLAN_ID = ?")
				                                   .append("    AND P.PLAN_UPDT_NO = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);

		String partNumber = eds.queryForString(selectSql.toString(), params);

		return partNumber;
	}
	
	
	public int updateSubjectPart(String planId, 
			                     Number planUpdtNo,
			                     Number subjectNumber,
			                     Number subjectRev,
			                     String itemId,
			                     String part_no) //defect 1814
	{
		//// Explain Plan cost =
		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFOR_SFPL_PLAN_SUBJECT_PART")
												   .append("    SET ITEM_ID = ?,")
												   .append("       PART_NO = ?,") //defect 1814
												   .append("       UPDT_USERID = ?,")
				                                   .append("       LAST_ACTION = 'UPDATED',")
				                                   .append("       TIME_STAMP = SYSDATE")
												   .append("  WHERE PLAN_ID = ?")
												   .append("    AND PLAN_UPDT_NO = ?")
												   .append("    AND SUBJECT_NO = ?")
												   .append("    AND SUBJECT_REV = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(itemId);
		params.addParameter(part_no); //defect 1814
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(subjectNumber);
		params.addParameter(subjectRev);

		int rowCount = eds.update(updateSql.toString(), params);

		return rowCount;
	}
	
   // Begin Task Group Copy
	public void insertSubjectExtension(Number subjectNumber, Number subjectRevision, String userName, String planId, Number planUpdtNo, Number fromSubjectNumber)
	{
		//// Explain Plan cost =
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_SFOR_SFPL_PLAN_SUBJECT")
				                                   .append(" (PLAN_ID,")
				                                   .append("  PLAN_UPDT_NO,")
				                                   .append("  SUBJECT_NO,")
				                                   .append("  SUBJECT_REV,")
				                                   .append("  MANDATORY,")
				                                   .append("  UPDT_USERID,")
				                                   .append("  LAST_ACTION) ")
				                                   .append(" SELECT PLAN_ID, ")
				                                   .append("        PLAN_UPDT_NO, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")
				                                   .append("        MANDATORY,")
				                                   .append("        ?, ")
				                                   .append("        ? ")
				                                   .append("  FROM PWUST_SFOR_SFPL_PLAN_SUBJECT")
				                                   .append(" WHERE PLAN_ID = ? ")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(subjectNumber);
		params.addParameter(subjectRevision);
		params.addParameter(userName);
		params.addParameter(SoluminaConstants.COPIED);
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(fromSubjectNumber);
		
		eds.insert(insertSql.toString(), params);
	}
	
	public void insertCustomer(Number subjectNumber, Number subjectRevision, String userName, String planId, Number planUpdtNo, Number fromSubjectNumber)
	{
	    //// Explain Plan cost =
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_PL_SUBJ_CUSTOMER")
                                                   .append(" (PLAN_ID,")
                                                   .append("  PLAN_UPDT_NO,")
                                                   .append("  SUBJECT_NO,")
                                                   .append("  SUBJECT_REV,")
                                                   .append("  CUST_ID,")
                                                   .append("  UPDT_USERID,")
                                                   .append("  LAST_ACTION) ")
				                                   .append(" SELECT PLAN_ID, ")
				                                   .append("        PLAN_UPDT_NO, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")
				                                   .append("        CUST_ID,")
				                                   .append("        ?, ")
				                                   .append("        ? ")
				                                   .append("  FROM PWUST_PL_SUBJ_CUSTOMER")
				                                   .append(" WHERE PLAN_ID = ? ")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(subjectNumber);
		params.addParameter(subjectRevision);
		params.addParameter(userName);
		params.addParameter(SoluminaConstants.COPIED);
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(fromSubjectNumber);
		
        eds.insert(insertSql.toString(), params);
	}
	
	public void insertCustomerException(Number subjectNumber, Number subjectRevision, String userName, String planId, Number planUpdtNo, Number fromSubjectNumber)
	{
	    //// Explain Plan cost =
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_PL_SUBJ_CUST_EXCEPT")
                                                   .append(" (PLAN_ID,")
                                                   .append("  PLAN_UPDT_NO,")
                                                   .append("  SUBJECT_NO,")
                                                   .append("  SUBJECT_REV,")
                                                   .append("  CUST_ID,")
                                                   .append("  CUST_ID_EXCEPTION,")
                                                   .append("  UPDT_USERID,")	
                                                   .append("  LAST_ACTION) ")
				                                   .append(" SELECT PLAN_ID, ")
				                                   .append("        PLAN_UPDT_NO, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")
				                                   .append("        CUST_ID,")
				                                   .append("        CUST_ID_EXCEPTION,")
				                                   .append("        ?, ")
				                                   .append("        ? ")
				                                   .append("  FROM PWUST_PL_SUBJ_CUST_EXCEPT")
				                                   .append(" WHERE PLAN_ID = ? ")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(subjectNumber);
		params.addParameter(subjectRevision);
		params.addParameter(userName);
		params.addParameter(SoluminaConstants.COPIED);
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(fromSubjectNumber);

		eds.insert(insertSql.toString(), params);
	}
	
	public void insertSuperiorNetwork(Number subjectNumber, Number subjectRevision, String userName, String planId, Number planUpdtNo, Number fromSubjectNumber)
	{
	    //// Explain Plan cost =
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_PL_SUBJ_NETWORK_ACTIVITY")
                                                   .append(" (PLAN_ID,")
                                                   .append("  PLAN_UPDT_NO,")
                                                   .append("  SUBJECT_NO,")
                                                   .append("  SUBJECT_REV,")
                                                   .append("  ENGINE_TYPE,")
                                                   .append("  SUPERIOR_NETWORK_ACT,")
                                                   .append("  UPDT_USERID,")	
                                                   .append("  LAST_ACTION) ")
				                                   .append(" SELECT PLAN_ID, ")
				                                   .append("        PLAN_UPDT_NO, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")
				                                   .append("        ENGINE_TYPE,")
                                                   .append("        SUPERIOR_NETWORK_ACT,")
				                                   .append("        ?, ")
				                                   .append("        ? ")
				                                   .append("  FROM PWUST_PL_SUBJ_NETWORK_ACTIVITY")
				                                   .append(" WHERE PLAN_ID = ? ")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(subjectNumber);
		params.addParameter(subjectRevision);
		params.addParameter(userName);
		params.addParameter(SoluminaConstants.COPIED);
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(fromSubjectNumber);
		
		eds.insert(insertSql.toString(), params);
	}

	public void insertSubNetwork(Number subjectNumber, Number subjectRevision, String userName, String planId, Number planUpdtNo, Number fromSubjectNumber)
	{
	    //// Explain Plan cost =
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_PL_SUBJ_SUBNET_ACTIVITY")
                                                   .append(" (PLAN_ID,")
                                                   .append("  PLAN_UPDT_NO,")
                                                   .append("  SUBJECT_NO,")
                                                   .append("  SUBJECT_REV,")
                                                   .append("  ENGINE_TYPE,")
                                                   .append("  SUPERIOR_NETWORK_ACT,")
                                                   .append("  SUB_NETWORK_ACT,")
                                                   .append("  UPDT_USERID,")	
                                                   .append("  LAST_ACTION) ")
				                                   .append(" SELECT PLAN_ID, ")
				                                   .append("        PLAN_UPDT_NO, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")
				                                   .append("        ENGINE_TYPE,")
				                                   .append("        SUPERIOR_NETWORK_ACT,")
                                                   .append("        SUB_NETWORK_ACT,")
				                                   .append("        ?, ")
				                                   .append("        ? ")
				                                   .append("  FROM PWUST_PL_SUBJ_SUBNET_ACTIVITY")
				                                   .append(" WHERE PLAN_ID = ? ")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(subjectNumber);
		params.addParameter(subjectRevision);
		params.addParameter(userName);
		params.addParameter(SoluminaConstants.COPIED);
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(fromSubjectNumber);
		
		eds.insert(insertSql.toString(), params);
	}
	
	public void insertWorkscope(Number subjectNumber, Number subjectRevision, String userName, String planId, Number planUpdtNo, Number fromSubjectNumber)
	{
	    //// Explain Plan cost =
		StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_PL_SUBJ_WORKSCOPE")
				                                   .append(" (PLAN_ID,")
				                                   .append("  PLAN_UPDT_NO,")
				                                   .append("  SUBJECT_NO,")
				                                   .append("  SUBJECT_REV,")
				                                   .append("  WORKSCOPE,")
				                                   .append("  UPDT_USERID,")	
                                                   .append("  LAST_ACTION) ")
				                                   .append(" SELECT PLAN_ID, ")
				                                   .append("        PLAN_UPDT_NO, ")
				                                   .append("        ?, ")
				                                   .append("        ?, ")
				                                   .append("        WORKSCOPE,")
				                                   .append("        ?, ")
				                                   .append("        ? ")
				                                   .append("  FROM PWUST_PL_SUBJ_WORKSCOPE")
				                                   .append(" WHERE PLAN_ID = ? ")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(subjectNumber);
		params.addParameter(subjectRevision);
		params.addParameter(userName);
		params.addParameter(SoluminaConstants.COPIED);
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(fromSubjectNumber);
		
		eds.insert(insertSql.toString(), params);
	}
	// End Task group Copy
	
	// Start Task Group Obsolete
	public int updatePlanSubjectExtension(String obsoleteRecordFlag,
			                              String planId,
			                              Number planUpdtNo,
			                              Number subjectNumber)
	{
		StringBuffer updateSql = new StringBuffer().append("UPDATE PWUST_SFOR_SFPL_PLAN_SUBJECT ")
				                                   .append("   SET OBSOLETE_RECORD_FLAG = ?, ")
				                                   .append("       UPDT_USERID = ?,")
				                                   .append("       LAST_ACTION = 'UPDATED',")
				                                   .append("       TIME_STAMP = SYSDATE")
				                                   .append(" WHERE PLAN_ID = ?")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(obsoleteRecordFlag);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(subjectNumber);

		int rowCount = eds.update(updateSql.toString(), params);

		return rowCount;
	}
	
	public int updatePlanSubjectCustomer(String obsoleteRecordFlag,
			                             String planId,
			                             Number planUpdtNo,
			                             Number subjectNumber)
	{
		StringBuffer updateSql = new StringBuffer().append("UPDATE PWUST_PL_SUBJ_CUSTOMER ")
				                                   .append("   SET OBSOLETE_RECORD_FLAG = ?, ")
				                                   .append("       UPDT_USERID = ?,")
				                                   .append("       LAST_ACTION = 'UPDATED',")
				                                   .append("       TIME_STAMP = SYSDATE")
				                                   .append(" WHERE PLAN_ID = ?")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(obsoleteRecordFlag);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(subjectNumber);

		int rowCount = eds.update(updateSql.toString(), params);

		return rowCount;
	}
	
	public int updatePlanSubjectCustomerException(String obsoleteRecordFlag,
			                                      String planId,
			                                      Number planUpdtNo,
			                                      Number subjectNumber)
	{
		StringBuffer updateSql = new StringBuffer().append("UPDATE PWUST_PL_SUBJ_CUST_EXCEPT ")
			                                       .append("   SET OBSOLETE_RECORD_FLAG = ?, ")
			                                       .append("       UPDT_USERID = ?,")
				                                   .append("       LAST_ACTION = 'UPDATED',")
				                                   .append("       TIME_STAMP = SYSDATE")
			                                       .append(" WHERE PLAN_ID = ?")
			                                       .append("   AND PLAN_UPDT_NO = ?")
			                                       .append("   AND SUBJECT_NO = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(obsoleteRecordFlag);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(subjectNumber);

		int rowCount = eds.update(updateSql.toString(), params);

		return rowCount;
	}
	
	public int updatePlanSubjectSuperiorNetwork(String obsoleteRecordFlag,
			                                    String planId,
			                                    Number planUpdtNo,
			                                    Number subjectNumber)
	{
		StringBuffer updateSql = new StringBuffer().append("UPDATE PWUST_PL_SUBJ_NETWORK_ACTIVITY ")
				                                   .append("   SET OBSOLETE_RECORD_FLAG = ?, ")
				                                   .append("       UPDT_USERID = ?,")
				                                   .append("       LAST_ACTION = 'UPDATED',")
				                                   .append("       TIME_STAMP = SYSDATE")
				                                   .append(" WHERE PLAN_ID = ?")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(obsoleteRecordFlag);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(subjectNumber);

		int rowCount = eds.update(updateSql.toString(), params);

		return rowCount;
	}
	
	public int updatePlanSubjectSubNetwork(String obsoleteRecordFlag,
			                               String planId,
			                               Number planUpdtNo,
			                               Number subjectNumber)
	{
		StringBuffer updateSql = new StringBuffer().append("UPDATE PWUST_PL_SUBJ_SUBNET_ACTIVITY ")
			                                       .append("   SET OBSOLETE_RECORD_FLAG = ?, ")
			                                       .append("       UPDT_USERID = ?,")
				                                   .append("       LAST_ACTION = 'UPDATED',")
				                                   .append("       TIME_STAMP = SYSDATE")
			                                       .append(" WHERE PLAN_ID = ?")
			                                       .append("   AND PLAN_UPDT_NO = ?")
			                                       .append("   AND SUBJECT_NO = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(obsoleteRecordFlag);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(subjectNumber);

		int rowCount = eds.update(updateSql.toString(), params);

		return rowCount;
	}
	
	public int updatePlanSubjectWorkscope(String obsoleteRecordFlag,
			                              String planId,
			                              Number planUpdtNo,
			                              Number subjectNumber)
	{
		StringBuffer updateSql = new StringBuffer().append("UPDATE PWUST_PL_SUBJ_WORKSCOPE ")
				                                   .append("   SET OBSOLETE_RECORD_FLAG = ?, ")
				                                   .append("       UPDT_USERID = ?,")
				                                   .append("       LAST_ACTION = 'UPDATED',")
				                                   .append("       TIME_STAMP = SYSDATE")
				                                   .append(" WHERE PLAN_ID = ?")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(obsoleteRecordFlag);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(subjectNumber);

		int rowCount = eds.update(updateSql.toString(), params);

		return rowCount;
	}
	//End Task Group Obsolete
	
	//BEGIN Task Group Revision Update
	public int updatePlanSubjectRev(Number subjectRev,
			                        String planId,
			                        Number planUpdtNo,
			                        Number subjectNumber)
	{
		StringBuffer updateSql = new StringBuffer().append("UPDATE PWUST_SFOR_SFPL_PLAN_SUBJECT ")
				                                   .append("   SET SUBJECT_REV = ?, ")
				                                   .append("       UPDT_USERID = ?,")
				                                   .append("       LAST_ACTION = 'UPDATED',")
				                                   .append("       TIME_STAMP = SYSDATE")
				                                   .append(" WHERE PLAN_ID = ?")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(subjectRev);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(subjectNumber);

		int rowCount = eds.update(updateSql.toString(), params);

		return rowCount;
	}
	
	//BEGIN Task Group Revision Update defect 1841
	//added this one since we just want to update the timestamp and updated action for existing task
		public int updatePlanSubjectRev2(Number subjectRev,
				                        String planId,
				                        Number planUpdtNo,
				                        Number subjectNumber)
		{
			int subjectrev2 = subjectRev.intValue(); //defect 1846
			
			StringBuffer updateSql = new StringBuffer().append("UPDATE PWUST_SFOR_SFPL_PLAN_SUBJECT ")
					                                   .append("   SET SUBJECT_REV = ?, ")
					                                   .append("       UPDT_USERID = ?,")
					                                   .append("       LAST_ACTION = 'UPDATED',")
					                                   .append("       TIME_STAMP = SYSDATE")
					                                   .append(" WHERE PLAN_ID = ?")
					                                   .append("   AND PLAN_UPDT_NO = ?")
					                                   .append("   AND SUBJECT_NO = ? ");

			ParameterHolder params = new ParameterHolder();
			params.addParameter(subjectrev2);
			params.addParameter(ContextUtil.getUsername());
			params.addParameter(planId);
			params.addParameter(planUpdtNo);
			params.addParameter(subjectNumber);

			int rowCount = 0;
			
			try {

			   rowCount = eds.update(updateSql.toString(), params);

		     } catch(Exception e) {
		    	 System.out.println("updatePlanSubjectRev2 Exception is - " + e.toString());
		     }
			
			return rowCount;
		}
	public int updatePlanSubjectCustomerRev(Number subjectRev,
			                                String planId,
			                                Number planUpdtNo,
			                                Number subjectNumber)
	{
		StringBuffer updateSql = new StringBuffer().append("UPDATE PWUST_PL_SUBJ_CUSTOMER ")
				                                   .append("   SET SUBJECT_REV = ?, ")
				                                   .append("       UPDT_USERID = ?,")
				                                   .append("       LAST_ACTION = 'UPDATED',")
				                                   .append("       TIME_STAMP = SYSDATE")
				                                   .append(" WHERE PLAN_ID = ?")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ? ");

		ParameterHolder params = new ParameterHolder();
		
		params.addParameter(subjectRev);	
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(subjectNumber);

		int rowCount = eds.update(updateSql.toString(), params);

		return rowCount;
	}

	public int updatePlanSubjectCustomerExceptionRev(Number subjectRev,
			                                         String planId,
                                                     Number planUpdtNo,
                                                     Number subjectNumber)
	{
		StringBuffer updateSql = new StringBuffer().append("UPDATE PWUST_PL_SUBJ_CUST_EXCEPT ")
												   .append("   SET SUBJECT_REV = ?, ")
												   .append("       UPDT_USERID = ?,")
				                                   .append("       LAST_ACTION = 'UPDATED',")
				                                   .append("       TIME_STAMP = SYSDATE")
												   .append(" WHERE PLAN_ID = ?")
												   .append("   AND PLAN_UPDT_NO = ?")
												   .append("   AND SUBJECT_NO = ? ");

		ParameterHolder params = new ParameterHolder();
		
		params.addParameter(subjectRev);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(subjectNumber);
		
		int rowCount = eds.update(updateSql.toString(), params);

		return rowCount;
	}

	public int updatePlanSubjectSuperiorNetworkRev(Number subjectRev, 
												   String planId,
                                                   Number planUpdtNo,
                                                   Number subjectNumber)
	{
		StringBuffer updateSql = new StringBuffer().append("UPDATE PWUST_PL_SUBJ_NETWORK_ACTIVITY ")
				                                   .append("   SET SUBJECT_REV = ?, ")
				                                   .append("       UPDT_USERID = ?,")
				                                   .append("       LAST_ACTION = 'UPDATED',")
				                                   .append("       TIME_STAMP = SYSDATE")
				                                   .append(" WHERE PLAN_ID = ?")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ? ");

		ParameterHolder params = new ParameterHolder();
		
		params.addParameter(subjectRev);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(subjectNumber);
		

		int rowCount = eds.update(updateSql.toString(), params);

		return rowCount;
	}

	public int updatePlanSubjectSubNetworkRev(Number subjectRev,
                                              String planId,
                                              Number planUpdtNo,
                                              Number subjectNumber)
	{
		StringBuffer updateSql = new StringBuffer().append("UPDATE PWUST_PL_SUBJ_SUBNET_ACTIVITY ")
				                                   .append("   SET SUBJECT_REV = ?, ")
				                                   .append("       UPDT_USERID = ?,")
				                                   .append("       LAST_ACTION = 'UPDATED',")
				                                   .append("       TIME_STAMP = SYSDATE")
				                                   .append(" WHERE PLAN_ID = ?")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ? ");

		ParameterHolder params = new ParameterHolder();
		
		params.addParameter(subjectRev);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(subjectNumber);
		
		
		int rowCount = eds.update(updateSql.toString(), params);

		return rowCount;
	}

	public int updatePlanSubjectWorkscopeRev(Number subjectRev,
			                                 String planId,
                                             Number planUpdtNo,
                                             Number subjectNumber)
	{
		StringBuffer updateSql = new StringBuffer().append("UPDATE PWUST_PL_SUBJ_WORKSCOPE ")
				                                   .append("   SET SUBJECT_REV = ?, ")
				                                   .append("       UPDT_USERID = ?,")
				                                   .append("       LAST_ACTION = 'UPDATED',")
				                                   .append("       TIME_STAMP = SYSDATE")
				                                   .append(" WHERE PLAN_ID = ?")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ? ");

		ParameterHolder params = new ParameterHolder();
		
		params.addParameter(subjectRev);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(planId);
		params.addParameter(planUpdtNo);
		params.addParameter(subjectNumber);
	
		int rowCount = eds.update(updateSql.toString(), params);
		
		return rowCount;
	}
	// END Task Group Revision Update
	
	public boolean isPlanSubjectObsolete(String planId, Number planUpdtNo, Number subjectNumber)
	{
	    //// Explain Plan cost =
		StringBuffer selectSql = new StringBuffer().append("SELECT COUNT(*)")
				                                   .append("  FROM SFOR_SFPL_PLAN_SUBJECT")
				                                   .append(" WHERE PLAN_ID = ?")
				                                   .append("   AND PLAN_UPDT_NO = ?")
				                                   .append("   AND SUBJECT_NO = ?")
				                                   .append("   AND OBSOLETE_RECORD_FLAG = 'Y'");
	
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planUpdtNo);	
		params.addParameter(subjectNumber);
    
        int obsolete  = eds.queryForInt(selectSql.toString(), params);
		boolean subjectObsolete = false;
		if (obsolete > 0)
		{
			subjectObsolete = true;
		}
		return subjectObsolete;
	}
	
	//SMRO_PLG_205 SCR-004
	public boolean doesNetworkExist(String engineType, String actNo, String subActNo) 
    {
    	boolean exists = false;
    	
         StringBuffer selectSql = new StringBuffer().append("SELECT ?")
        		 									.append("  FROM PWUST_NETWORK_DEF")
        		 									.append(" WHERE ENGINE_TYPE = ?")
        		 									.append("   AND SUPERIOR_NETWORK_ACT = ?")
        		 									.append("   AND SUB_NETWORK_ACT = ?")
        		 									.append("   AND OBSOLETE_RECORD_FLAG = 'N'");
         
         ParameterHolder params = new ParameterHolder();
         params.addParameter(SoluminaConstants.X);
         params.addParameter(engineType);
         params.addParameter(actNo);
         params.addParameter(subActNo);
         
         List list = eds.queryForList(selectSql.toString(), params);
         
         if (!list.isEmpty())
         {
        	 exists = true;
         }
         return exists;
    }

	//SMRO_PLG_205 SCR-004
	public boolean doesWorkscopeExist(String engineType, String workscope, String workLoc) 
	{
		boolean exists = false;

		StringBuffer selectSql = new StringBuffer().append("SELECT ?")
				                                   .append("  FROM PWUST_WORKSCOPE_DEF")
				                                   .append(" WHERE ENGINE_TYPE = ?")
				                                   .append("   AND WORKSCOPE = ?")
				                                   .append("    AND WORK_LOC = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(SoluminaConstants.X);
		params.addParameter(engineType);
		params.addParameter(workscope);
		params.addParameter(workLoc);

		List list = eds.queryForList(selectSql.toString(), params);

		if (!list.isEmpty())
		{
			exists = true;
		}
		return exists;
	}
}
