/*
 * This unpublished work, first created in 2017 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2017,2020.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    SfplPlanPW.java
 * 
 *  Created: 2017-08-11
 * 
 *  Author:  c293011
 * 
 *  Revision History
 * 
 *  Date      	Who                	Description
 *  ----------	---------------	---------------	---------------------------------------------------
 * 2017-08-11 	c293011		    Initial Release SMRO_PLG_205
 * 2017-11-13   R. Thorpe	    Defect 181 added methods deleteSubjectOperation and insertSubjectOperation
 * 2018-03-22   R. Thorpe       Defect 477 - do not delete custom task info if OOB was not deleted
 * 2019-02-27	R. Thorpe       Removed two System.out.println("subjectRev is "+subjectRev) lines
 * 2018-01-18	B. Preston		SMRO_PLG_305:  Changes based on functional spec.
 * 2019-02-27	R. Thorpe       Removed two System.out.println("subjectRev is "+subjectRev) lines
 * 2019-04-24   T. Phan         SMRO_PLG_305: Defect 1169. Skips Task Group Item No if REPAIR plan type.
 * 2019-11-29   T. Phan         SMRO_PLG_305: Defect 1410. Skips Task Group Item No autofill if REPAIR plan type.
 * 2020-03-04	Fred Ettefagh	SMRO_PLG_305- Defect 1410 for overhaul plans only auto-populate task groups parts with the Plan's item_no.
 * 2020-04-03	Fred Ettefagh	SMRO_PLG_305- Defect 1616, in method updateSubjectTaskGroup, Dao call to get plan_type was returning null for value for column plan_type
 * 2020-04-08	S. Edgerly		SMRO_CV_302 - Defect 1623 - Parts on Repair Task Groups.
 * 2020-12-18   John DeNinno    defect 1841 fix the pk error
 * 2020-01-06   John DeNinno    defect 1846 fix the pk error
 *  2021-03-09		S. Edgerly		SMRO_CV_301 - Defect 1875 - TEC Conversion Extract from G5
*/

package com.pw.solumina.sfor.application;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.dao.EmptyResultDataAccessException;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sfor.application.ISfplPlan;
import com.ibaset.solumina.sfor.dao.ISfplPlanDao;
import com.ibaset.solumina.sfor.dao.IPlanDao;
import com.ibaset.solumina.sfpl.application.IPlanOfd;
import com.pw.solumina.migration.application.MigrationVariablesPW;
import com.pw.solumina.migration.application.SoluminaMigrationDaoPW;
//import com.ibaset.solumina.sfpl.dao.IPlanDao;
import com.pw.solumina.migration.application.SoluminaMigrationPW;
import com.pw.solumina.sfor.dao.TaskGroupsDaoPW;
import com.pw.solumina.sfpl.dao.PlanDaoPW;

public class SfplPlanPW {

	@Reference private ISfplPlan sfplPlan = null;
	@Reference private IPlanDao planDao = null;
	@Reference private ISfplPlanDao sfplPlanDao = null;
	@Reference private IMessage message;
	@Reference private IPlanOfd planOfd = null;
	@Reference private TaskGroupsDaoPW taskGroupsDaoPW;
	@Reference private PlanDaoPW planDaoPW = null; // SMRO_PLG_305
	@Reference private SoluminaMigrationDaoPW SoluminaMigrationDaoPW;

	// This is called from UDV 
	public void insertSubjectTaskGroup(String planId,
			                           Number planVersion,
			                           Number planRevision,
			                           Number planAlterations,
							            Number planUpdtNo,
			                           Number subjectNumber,
			                           String title,
			                           String isDefault,
			                           String authority,
							            String mandatory,
							            String planType,
							            String partNumber ){		

		if ("Y".equals(mandatory)){
			isDefault = "Y";
		}
		
		// call OOB insertSubjectTaskGroup method first
		sfplPlan.insertSubjectTaskGroup(planId, 
									    planVersion, 
									    planRevision, 
									    planAlterations, 
									    subjectNumber, 
									    title, 
									    isDefault);

		//get the subjectRev
		Number subjectRev = sfplPlanDao.selectSubjectRevision(planId, planUpdtNo, subjectNumber);

		// insert row into custom table
		taskGroupsDaoPW.insertTaskGroup(planId, 
										planUpdtNo, 
										subjectNumber, 
										subjectRev, 
										mandatory, 
										SoluminaConstants.N /*Defect 1875*/);
		
		// now update the Authority custom field	 
		taskGroupsDaoPW.updateTaskGroupAuthority(planId, planUpdtNo, subjectNumber, subjectRev, authority);   

		// defect 1410
		if (StringUtils.equalsIgnoreCase(planType, "OVERHAUL")){
		sfplPlan.insertSubjectPart(planId, planVersion, planRevision, planAlterations, subjectNumber, partNumber);
	}
		}
	
	//Defect 1623
	public void migrateSubjectTaskGroup(String planId,
										String srcPlanId,
							            Number planVersion,
							            Number planRevision,
							            Number planAlterations,
								        Number planUpdtNo,
							            Number subjectNumber,
							            String title,
							            String isDefault,
							            String authority,
							            String mandatory,
							            String planType,
							            String partNumber,
							            List<String> subjectParts,
							            String obsoleteRecordFlag /*defect 1875*/){	
		if ("Y".equals(mandatory)){
			isDefault = "Y";
		}
		
		// call OOB insertSubjectTaskGroup method first
		sfplPlan.insertSubjectTaskGroup(planId, 
									    planVersion, 
									    planRevision, 
									    planAlterations, 
									    subjectNumber, 
									    title, 
									    isDefault);

		//get the subjectRev
		Number subjectRev = sfplPlanDao.selectSubjectRevision(planId, 
															  planUpdtNo, 
															  subjectNumber);

		// insert row into custom table
		taskGroupsDaoPW.insertTaskGroup(planId, 
										planUpdtNo, 
										subjectNumber, 
										subjectRev, 
										mandatory, 
										obsoleteRecordFlag /*defect 1875*/);
		
		// now update the Authority custom field	 
		taskGroupsDaoPW.updateTaskGroupAuthority(planId, planUpdtNo, subjectNumber, subjectRev, authority);   

		// defect 1410
		if (StringUtils.equalsIgnoreCase(planType, "OVERHAUL")){
		sfplPlan.insertSubjectPart(planId, planVersion, planRevision, planAlterations, subjectNumber, partNumber);
		}
		else{
			ListIterator subjPartIter = subjectParts.listIterator();
			//get all parts given plan id and subject/task group number
			while(subjPartIter.hasNext()){
				Map subjectPart = (Map) subjPartIter.next();
				String partNo = (String) subjectPart.get("PART_NO");
				try{
					sfplPlan.insertSubjectPart(planId, planVersion, planRevision, planAlterations, subjectNumber, partNo);
				}
				catch(EmptyResultDataAccessException e){
					
					String errorText = "Error on subject insert: part_no=" + srcPlanId
									   + ", subj_id=" + subjectNumber + "Part Number="+ partNo + "\n" + e.toString();
					errorText = convertStackTraceToG8ErrText(e, errorText);

					SoluminaMigrationDaoPW.insertErrorLogG8("SUBJECTS", MigrationVariablesPW.WARN, srcPlanId,
							MigrationVariablesPW.PLAN_UPDT_NO, MigrationVariablesPW.NA_ERR, MigrationVariablesPW.NA_ERR,
							partNo, 0, errorText, MigrationVariablesPW.SFMFG);
				}
			}	
		}
	
	}

	String convertStackTraceToG8ErrText(Exception e, String errorText){


		if(e!=null){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stackTrace = sw.toString();
			//This should prevent duplicate exception messages and print the stack trace.
			if(errorText.contains(e.getMessage())){
				errorText = errorText.replace(e.getMessage(), stackTrace);
			}
			//otherwise, just append the stack trace
			else{
				errorText = errorText.concat("\n" + stackTrace);
			}
			//Truncate error message at 3000 characters.
			if(errorText.length() > 3000){
				errorText = errorText.substring(0, 2999);
			}
		}
		return errorText;
	}
	
	public void insertSubjectOperation(String planId,
			                           Number planVersion,
			                           Number planRevision,
			                           Number planAlterations,
			                           Number subjectNumber,
			                           String operationNumber)

	{		
		// call OOB insertSubjectOperation method first
		sfplPlan.insertSubjectOperation(planId, 
				                        planVersion, 
				                        planRevision, 
				                        planAlterations, 
				                        subjectNumber, 
				                        operationNumber);

		//get the planupdtNo
		Map planMap = planDao.selectPlan(planId, planVersion, planRevision, planAlterations);
		Number planUpdtNo = (Number) planMap.get("PLAN_UPDT_NO");
		//get the subjectRev
		Number subjectRev = sfplPlanDao.selectSubjectRevision(planId, planUpdtNo, subjectNumber);
		
		// update the subject number revisions for custom tables
        taskGroupsDaoPW.updatePlanSubjectRev2(subjectRev, planId, planUpdtNo, subjectNumber); // defect 1841
        taskGroupsDaoPW.updatePlanSubjectCustomerExceptionRev(subjectRev, planId, planUpdtNo, subjectNumber);
        taskGroupsDaoPW.updatePlanSubjectCustomerRev(subjectRev, planId, planUpdtNo, subjectNumber);
        taskGroupsDaoPW.updatePlanSubjectSubNetworkRev(subjectRev, planId, planUpdtNo, subjectNumber);
        taskGroupsDaoPW.updatePlanSubjectSuperiorNetworkRev(subjectRev, planId, planUpdtNo, subjectNumber);
        taskGroupsDaoPW.updatePlanSubjectWorkscopeRev(subjectRev, planId, planUpdtNo, subjectNumber);
	}
	

	public void copySubject(String planId,
                            Number planVersion,
                            Number planRevision,
                            Number planAlterations,
                            Number fromSubjectNumber,
                            Number toSubjectNumber)
	{
		// call OOB insertSubjectTaskGroup method first
		sfplPlan.copySubject(planId, 
				             planVersion, 
				             planRevision, 
				             planAlterations, 
				             fromSubjectNumber, 
				             toSubjectNumber);

		// Gets the User Name.
        String userName = ContextUtil.getUsername();
        // Gets the Plan Update Number.
        Number planUpdtNo = planOfd.startPlanUpdate(planId,
                                                          "0",
                                                          SoluminaConstants.PART,
                                                          "0",
                                                          "0",
                                                          "0",
                                                          planVersion,
                                                          planRevision,
                                                          planAlterations,
                                                          null,
                                                          null,
                                                          null,
                                                          null, 
                                                          null);
        
		taskGroupsDaoPW.insertSubjectExtension(toSubjectNumber, NumberUtils.INTEGER_ONE, userName, planId, planUpdtNo, fromSubjectNumber);
		taskGroupsDaoPW.insertCustomer(toSubjectNumber, NumberUtils.INTEGER_ONE, userName, planId, planUpdtNo, fromSubjectNumber);
		taskGroupsDaoPW.insertCustomerException(toSubjectNumber, NumberUtils.INTEGER_ONE, userName, planId, planUpdtNo, fromSubjectNumber);
		taskGroupsDaoPW.insertSuperiorNetwork(toSubjectNumber, NumberUtils.INTEGER_ONE, userName, planId, planUpdtNo, fromSubjectNumber);
		taskGroupsDaoPW.insertSubNetwork(toSubjectNumber, NumberUtils.INTEGER_ONE, userName, planId, planUpdtNo, fromSubjectNumber);
		taskGroupsDaoPW.insertWorkscope(toSubjectNumber, NumberUtils.INTEGER_ONE, userName, planId, planUpdtNo, fromSubjectNumber);
	}

	public void obsoleteSubject(String planId, Number planUpdtNo, Number subjectNumber, Number subjectRev, String obsoleteRecordFlag)
	{
		// call OOB insertSubjectTaskGroup method first
		sfplPlan.obsoleteSubject(planId, subjectNumber, obsoleteRecordFlag);
		
     	//call update for custom tables
		taskGroupsDaoPW.updatePlanSubjectExtension(obsoleteRecordFlag, planId, planUpdtNo, subjectNumber);
		taskGroupsDaoPW.updatePlanSubjectCustomer(obsoleteRecordFlag, planId, planUpdtNo, subjectNumber);
		taskGroupsDaoPW.updatePlanSubjectCustomerException(obsoleteRecordFlag, planId, planUpdtNo, subjectNumber);
		taskGroupsDaoPW.updatePlanSubjectSuperiorNetwork(obsoleteRecordFlag, planId, planUpdtNo, subjectNumber);
		taskGroupsDaoPW.updatePlanSubjectSubNetwork(obsoleteRecordFlag, planId, planUpdtNo, subjectNumber);
		taskGroupsDaoPW.updatePlanSubjectWorkscope(obsoleteRecordFlag, planId, planUpdtNo, subjectNumber);
	};
	
	// This is called from UDV PWUST_SFOR_SfplPlanSubjectUpd:PWUST_550FF0D87F4241A0E05387971F0AD3EA
	// Begin SMRO_PLG_305
	public void updateSubjectTaskGroup(String planId,
			                           Number planVersion,
			                           Number planRevision,
			                           Number planAlterations,
			                           Number subjectNumber,
			                           String title,
			                           String isDefault,
			                           String authority,
			                           String mandatory)
	{
		
		System.out.println("in updateSubjectTaskGroup ");
		if ("Y".equals(mandatory)){
			isDefault = "Y";
		}
		
		// call OOB insertSubjectTaskGroup method first
		sfplPlan.updateSubjectTaskGroup(planId,
				                        planVersion,
				                        planRevision,
				                        planAlterations,
				                        subjectNumber,
				                        title,
				                        isDefault);

		Map planMap = planDaoPW.selectPlanPW(planId, planVersion, planRevision, planAlterations);
		Number planUpdtNo = (Number) planMap.get("PLAN_UPDT_NO");
		String planType = (String)planMap.get("PLAN_TYPE");
		
		Number subjectRev = sfplPlanDao.selectSubjectRevision(planId, planUpdtNo, subjectNumber);
		
		System.out.println("in updateSubjectTaskGroup planUpdtNo = " + planUpdtNo);
		System.out.println("in updateSubjectTaskGroup subjectRev = " + subjectRev);
		System.out.println("in updateSubjectTaskGroup planType = " + planType);

		// update the subject number revisions for custom tables
        taskGroupsDaoPW.updatePlanSubjectRev2(subjectRev, planId, planUpdtNo, subjectNumber); //defect 1846
        taskGroupsDaoPW.updatePlanSubjectCustomerExceptionRev(subjectRev, planId, planUpdtNo, subjectNumber);
        taskGroupsDaoPW.updatePlanSubjectCustomerRev(subjectRev, planId, planUpdtNo, subjectNumber);
		
		if ("OVERHAUL".equals(planType)) {
			taskGroupsDaoPW.updatePlanSubjectSubNetworkRev(subjectRev, planId, planUpdtNo, subjectNumber);
			taskGroupsDaoPW.updatePlanSubjectSuperiorNetworkRev(subjectRev, planId, planUpdtNo, subjectNumber);
			taskGroupsDaoPW.updatePlanSubjectWorkscopeRev(subjectRev, planId, planUpdtNo, subjectNumber);
		}

		taskGroupsDaoPW.updateTaskGroupMandatoryFlag(planId, planUpdtNo, subjectNumber, mandatory);
		taskGroupsDaoPW.updateTaskGroupAuthority(planId, planUpdtNo, subjectNumber, subjectRev, authority);
	}	
	// End SMRO_PLG_305

	
	
	// This is called from UDV PWUST_SFOR_SfplPlanSubjectSel:PWUST_5658B31457E15029E05387971F0AB2C1
	public void deleteSubjectTaskGroup(String planId,
			                           Number planVersion,
			                           Number planRevision,
			                           Number planAlterations,
			                           Number subjectNumber)

	{		
		//get the planupdtNo
		Map planMap = planDao.selectPlan(planId, planVersion, planRevision, planAlterations);
		Number planUpdtNo = (Number) planMap.get("PLAN_UPDT_NO");
		//get the subjectRev
		Number subjectRev = sfplPlanDao.selectSubjectRevision(planId, planUpdtNo, subjectNumber);
				
		// call OOB deleteSubject method first
		sfplPlan.deleteSubject(planId, 
							   planVersion, 
							   planRevision, 
						       planAlterations, 
						       subjectNumber);
		
		//Begin defect 477
		// did the OOB delete finish?
		boolean subjectExists = sfplPlanDao.selectPlanSubjectExists(planId, subjectNumber);

		if (!subjectExists)
		{	
			// delete row from custom table
			taskGroupsDaoPW.deleteTaskGroup(planId, planUpdtNo, subjectNumber, subjectRev);
		}
		//End defect 477

	}
	
	public void deleteSubjectOperation(String planId,
                                       Number planVersion,
                                       Number planRevision,
                                       Number planAlterations,
                                       Number subjectNumber,
                                       String operationNumber)
	{				
		// call OOB deleteSubjectOperation method first
		sfplPlan.deleteSubjectOperation(planId, 
							            planVersion, 
							            planRevision, 
							            planAlterations, 
							            subjectNumber, 
							            operationNumber);

		//get the planupdtNo
		Map planMap = planDao.selectPlan(planId, planVersion, planRevision, planAlterations);
		Number planUpdtNo = (Number) planMap.get("PLAN_UPDT_NO");
		//get the subjectRev
		Number subjectRev = sfplPlanDao.selectSubjectRevision(planId, planUpdtNo, subjectNumber);
			
		// update the subject number revisions for custom tables
	    taskGroupsDaoPW.updatePlanSubjectRev(subjectRev, planId, planUpdtNo, subjectNumber);
	    taskGroupsDaoPW.updatePlanSubjectCustomerExceptionRev(subjectRev, planId, planUpdtNo, subjectNumber);
	    taskGroupsDaoPW.updatePlanSubjectCustomerRev(subjectRev, planId, planUpdtNo, subjectNumber);
	    taskGroupsDaoPW.updatePlanSubjectSubNetworkRev(subjectRev, planId, planUpdtNo, subjectNumber);
	    taskGroupsDaoPW.updatePlanSubjectSuperiorNetworkRev(subjectRev, planId, planUpdtNo, subjectNumber);
	    taskGroupsDaoPW.updatePlanSubjectWorkscopeRev(subjectRev, planId, planUpdtNo, subjectNumber);
	}

	// This is called from UDV 
	public void insertSubjectWorkscope(String planId,
			                           Number planUpdtNo,
			                           Number subjectNumber,
			                           Number subjectRev,
			                           String workscope)

	{	
		String [] workscopes  = null;

		// Checks whether Plan Subject is obsolete or not.
        boolean planSubjectObsolete = taskGroupsDaoPW.isPlanSubjectObsolete(planId,
                                                                            planUpdtNo,
                                                                            subjectNumber);

        // Raises an error when Plan Subject is obsolete.
        if (planSubjectObsolete)
        {
            message.raiseError("MFI_20","Can not insert workscope, because the task group has been obsoleted");
        }
			
		if (workscope != null) 
		{
			workscopes = workscope.split(";");

			for (int i = 0; i < workscopes.length; i++) 
			{
				//Insert selected subject task groups into the PWUST_PL_SUBJ_WORKSCOPE
				taskGroupsDaoPW.insertWorkscope(planId, planUpdtNo, subjectNumber, subjectRev, workscopes[i]);	
			}
		}
		else
		{
			message.raiseError("MFI_20", "Please select at least one Workscope");
		}
	}
	
	// This is called from UDV 
	public void deleteSubjectWorkscope(String planId,
                                       Number planUpdtNo,
                                       Number subjectNumber,
                                       Number subjectRev,
                                       String workscope)

	{		
		// Checks whether Plan Subject is obsolete or not.
        boolean planSubjectObsolete = taskGroupsDaoPW.isPlanSubjectObsolete(planId,
                                                                            planUpdtNo,
                                                                            subjectNumber);

        // Raises an error when Plan Subject is obsolete.
        if (planSubjectObsolete)
        {
            message.raiseError("MFI_20","Can not delete workscope, because the task group has been obsoleted");
        }
        
		taskGroupsDaoPW.deleteWorkscope(planId, planUpdtNo, subjectNumber, subjectRev, workscope);
	}
	
	// This is called from UDV
	public void insertSubjectCustomer(String planId,
			                          Number planUpdtNo,
			                          Number subjectNumber,
			                          Number subjectRev,
			                          String customer)
	{		
		String [] customers  = null;
		boolean customerAllExists = false;
		boolean customersExist = false;
		
		// Checks whether Plan Subject is obsolete or not.
        boolean planSubjectObsolete = taskGroupsDaoPW.isPlanSubjectObsolete(planId,
                                                                            planUpdtNo,
                                                                            subjectNumber);

        // Raises an error when Plan Subject is obsolete.
        if (planSubjectObsolete)
        {
            message.raiseError("MFI_20","Can not insert customer, because the task group has been obsoleted");
        }
		
		if ("ALL".equals(customer))
		{
			customersExist = taskGroupsDaoPW.doCustomersExist(planId, planUpdtNo, subjectNumber, subjectRev);
			if (customersExist)
			{
				message.raiseError("MFI_20", "Customers are already selected, ALL cannot be selected in addition for the Task Group");
			}
		}
		
		customerAllExists = taskGroupsDaoPW.isCustomerAllSelected(planId, planUpdtNo, subjectNumber, subjectRev);
		if (customerAllExists)
		{
			message.raiseError("MFI_20", "ALL is selected as a Customer, no other Customer is allowed for the Task Group");
		}
		
		if ((StringUtils.contains(customer, ";")) && (StringUtils.contains(customer, "ALL")))
		{
			message.raiseError("MFI_20", "If ALL is selected as a Customer, no other Customer is allowed for the Task Group");
		}
		
		if (customer != null) 
		{
			customers = customer.split(";");

			// Begin SMRO_PLG_305
			String planType =  planDaoPW.getPlanType(planId);
			if ("REPAIR".equals(planType) || "Repair".equals(planType) || "OVERHAUL".equals(planType)) {
				if (customers.length >= 8) {
					message.raiseError("MFI_20", "No more than 7 Customers are allowed to be selected for the Task Group");
				}
			}
			// End SMRO_PLG_305

			for (int i = 0; i < customers.length; i++) 
			{
				//Insert selected subject task groups into the PWUST_PL_SUBJ_CUSTOMER
				taskGroupsDaoPW.insertCustomer(planId, planUpdtNo, subjectNumber, subjectRev, customers[i]);	
			}
		}
		else
		{
			message.raiseError("MFI_20", "Please select at least one Customer");
		}
	}
	
	// This is called from UDV
	public void deleteSubjectCustomer(String planId,
                                      Number planUpdtNo,
                                      Number subjectNumber,
                                      Number subjectRev,
                                      String customer)

	{	
		// Checks whether Plan Subject is obsolete or not.
        boolean planSubjectObsolete = taskGroupsDaoPW.isPlanSubjectObsolete(planId,
                                                                            planUpdtNo,
                                                                            subjectNumber);

        // Raises an error when Plan Subject is obsolete.
        if (planSubjectObsolete)
        {
            message.raiseError("MFI_20","Can not delete customer, because the task group has been obsoleted");
        }
        
		boolean customerExceptionsExist = taskGroupsDaoPW.doesCustomerExceptionsExist(planId, planUpdtNo, subjectNumber, subjectRev);
		//if customer = ALL 
		if ("ALL".equals(customer) && customerExceptionsExist)
		{
			 message.raiseError("MFI_20", "Please delete Customer Exceptions first");
		}
		
		taskGroupsDaoPW.deleteCustomer(planId, planUpdtNo, subjectNumber, subjectRev, customer);
	}
	
	// This is called from UDV 
	public void insertSubjectCustomerException(String planId,
			                                   Number planUpdtNo,
			                                   Number subjectNumber,
			                                   Number subjectRev,
			                                   String customer,
			                                   String customerException)

	{		
		String [] customerExceptions  = null;
		
		// Checks whether Plan Subject is obsolete or not.
        boolean planSubjectObsolete = taskGroupsDaoPW.isPlanSubjectObsolete(planId,
                                                                            planUpdtNo,
                                                                            subjectNumber);

        // Raises an error when Plan Subject is obsolete.
        if (planSubjectObsolete)
        {
            message.raiseError("MFI_20","Can not insert customer exception, because the task group has been obsoleted");
        }
		
		if (!"ALL".equals(customer))
		{
			 message.raiseError("MFI_20", "The Customer section must have a Customer of ALL to enter Customer Exceptions");
		}
		
		if (customerException != null) 
		{
			customerExceptions = customerException.split(";");

			for (int i = 0; i < customerExceptions.length; i++) 
			{
				//Insert selected subject task groups into the PWUST_PL_SUBJ_WORKSCOPE
				taskGroupsDaoPW.insertCustomerException(planId, planUpdtNo, subjectNumber, subjectRev, customer, customerExceptions[i]);	
			}
		}
		else
		{
			message.raiseError("MFI_20", "Please select at least one Exception");
		}	
	}
	
	// This is called from UDV ?????
	public void deleteSubjectCustomerException(String planId,
                                               Number planUpdtNo,
                                               Number subjectNumber,
                                               Number subjectRev,
                                               String customer,
			                                   String customerException)

	{	
		// Checks whether Plan Subject is obsolete or not.
        boolean planSubjectObsolete = taskGroupsDaoPW.isPlanSubjectObsolete(planId,
                                                                            planUpdtNo,
                                                                            subjectNumber);

        // Raises an error when Plan Subject is obsolete.
        if (planSubjectObsolete)
        {
            message.raiseError("MFI_20","Can not delete customer exception, because the task group has been obsoleted");
        }
        
		taskGroupsDaoPW.deleteCustomerException(planId, planUpdtNo, subjectNumber, subjectRev, customer, customerException);
	}

	// This is called from UDV ...
	public void insertSubjectSuperiorNetwork(String planId,
			                                 Number planUpdtNo,
			                                 Number subjectNumber,
			                                 Number subjectRev,
			                                 String engineType,
			                                 String superiorNetwork)

	{		
		// Checks whether Plan Subject is obsolete or not.
        boolean planSubjectObsolete = taskGroupsDaoPW.isPlanSubjectObsolete(planId,
                                                                            planUpdtNo,
                                                                            subjectNumber);

        // Raises an error when Plan Subject is obsolete.
        if (planSubjectObsolete)
        {
            message.raiseError("MFI_20","Can not insert superior network, because the task group has been obsoleted");
        }
        
		boolean superiorNetworkExists = taskGroupsDaoPW.doesSuperiorNetworkExist(planId, planUpdtNo, subjectNumber, subjectRev, engineType);
		 
		if (superiorNetworkExists)
		{
			 message.raiseError("MFI_20", "Only one (1) Superior Network Entry is allowed");
		}
		
		taskGroupsDaoPW.insertSuperiorNetwork(planId, planUpdtNo, subjectNumber, subjectRev, engineType, superiorNetwork);
	}
	
	// This is called from UDV ?????
	public void deleteSubjectSuperiorNetwork(String planId,
			                                 Number planUpdtNo,
			                                 Number subjectNumber,
			                                 Number subjectRev,
			                                 String engineType,
			                                 String superiorNetwork)

	{		
		// Checks whether Plan Subject is obsolete or not.
        boolean planSubjectObsolete = taskGroupsDaoPW.isPlanSubjectObsolete(planId,
                                                                            planUpdtNo,
                                                                            subjectNumber);

        // Raises an error when Plan Subject is obsolete.
        if (planSubjectObsolete)
        {
            message.raiseError("MFI_20","Can not delete superior network, because the task group has been obsoleted");
        }
        
		boolean subNetworkExists = taskGroupsDaoPW.doesSubNetworkExist(planId, planUpdtNo, subjectNumber, subjectRev, engineType, superiorNetwork);
		
		if (subNetworkExists)
		{
			 message.raiseError("MFI_20", "Please delete Sub Network first");
		}
		
		taskGroupsDaoPW.deleteSuperiorNetwork(planId, planUpdtNo, subjectNumber, subjectRev, engineType, superiorNetwork);	
	}

	// This is called from UDV ?????
	public void insertSubjectSubNetwork(String planId,
			                            Number planUpdtNo,
			                            Number subjectNumber,
			                            Number subjectRev,
			                            String engineType,
			                            String superiorNetwork,
			                            String subNetwork)

	{		
		// Checks whether Plan Subject is obsolete or not.
        boolean planSubjectObsolete = taskGroupsDaoPW.isPlanSubjectObsolete(planId,
                                                                            planUpdtNo,
                                                                            subjectNumber);

        // Raises an error when Plan Subject is obsolete.
        if (planSubjectObsolete)
        {
            message.raiseError("MFI_20","Can not insert sub network, because the task group has been obsoleted");
        }
        
		boolean superiorNetworkExists = taskGroupsDaoPW.doesSuperiorNetworkExist(planId, planUpdtNo, subjectNumber, subjectRev, engineType);
	
		if (!superiorNetworkExists)
		{
			 message.raiseError("MFI_20", "A Superior Network must be selected before selecting a Sub Network");
		}
		
		boolean subNetworkExists = taskGroupsDaoPW.doesSubNetworkExist(planId, planUpdtNo, subjectNumber, subjectRev, engineType, superiorNetwork);
	
		if (subNetworkExists)
		{
			 message.raiseError("MFI_20", "No more than one (1) Sub Network can be selected per Task Group");
		}
		
		taskGroupsDaoPW.insertSubNetwork(planId, planUpdtNo, subjectNumber, subjectRev, engineType, superiorNetwork, subNetwork);
	}
	
	// This is called from UDV 
	public void deleteSubjectSubNetwork(String planId,
			                            Number planUpdtNo,
			                            Number subjectNumber,
			                            Number subjectRev,
			                            String engineType,
			                            String superiorNetwork,
			                            String subNetwork)

	{	
		// Checks whether Plan Subject is obsolete or not.
        boolean planSubjectObsolete = taskGroupsDaoPW.isPlanSubjectObsolete(planId,
                                                                            planUpdtNo,
                                                                            subjectNumber);

        // Raises an error when Plan Subject is obsolete.
        if (planSubjectObsolete)
        {
            message.raiseError("MFI_20","Can not delete sub network, because the task group has been obsoleted");
        }
        
		taskGroupsDaoPW.deleteSubNetwork(planId, planUpdtNo, subjectNumber, subjectRev, engineType, superiorNetwork, subNetwork);	
	}

}