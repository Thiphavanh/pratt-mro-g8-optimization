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
*  File:    UserImplPW.java
* 
*  Created: 2017
* 
*  Author:  
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-12-11 	R. Thorpe	    removed raise errors and replace with insertErrorLog 
* 2018-03-30    R. Thorpe		initial release of HR Interface SMRO_MD_M206
* 2018-04-27    R. Thorpe       remove check for ASSIGN_SUPERUSER priv since we do not have that in G8
* 2018-04-30	S. Niu			Defect 576 Create a priv for ability to add SUPERUSER to a user and add the priv to the SUPERUSER role
* 
*/

package com.pw.solumina.sffnd.application.impl;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.solumina.IValidator;
import com.ibaset.common.solumina.exception.SoluminaException;
import com.ibaset.solumina.sfcore.application.ILicense;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.ISecurityGroup;
import com.ibaset.solumina.sffnd.application.IUser;
import com.ibaset.solumina.sffnd.dao.IUserDao;
import com.ibaset.solumina.sffnd.dao.IUserWorkCenterDao;
import com.ibaset.solumina.sfsqa.dao.IUserSupplierDao;
import com.pw.common.dao.CommonDaoPW;
import com.pw.common.utils.CommonUtilsPW;
import com.pw.common.utils.DateUtils;
import com.pw.common.utils.StringUtil;

//eACP Web Service imports
import com.pw.solumina.eacpWebService.Authinfo;
import com.pw.solumina.eacpWebService.EACP_Service_TestSoapProxy;
import com.pw.solumina.interfaces.dao.EACPDaoPW;
import com.pw.solumina.interfaces.dao.UserDaoPW;
import com.pw.solumina.sysadmin.location.dao.LocationDaoPW;

public abstract class UserImplPW extends ImplementationOf<IUser> implements IUser 
{

	@Reference    private Log logger = LogFactory.getLog(this.getClass());
	@Reference    private IMessage message = null;
	@Reference    private com.ibaset.solumina.sfcore.application.IUser user = null;
	@Reference    private IUserDao userDao = null;
	@Reference	  private IUserWorkCenterDao userWorkCenterDao = null;
	@Reference    private ILicense license = null;
	@Reference    private IUserSupplierDao userSupplierDao = null;
	@Reference    private CommonDaoPW commmonDaoPW = null;
//	@Reference    private com.ibaset.solumina.opcert.sffnd.dao.IUserDao opcertUserDao;
	@Reference    private IValidator validator = null;
	@Reference	  private UserDaoPW userDaoPW;
	@Reference	  private CommonUtilsPW commonUtilsPW;	
	@Reference    private EACP_Service_TestSoapProxy EACP_Service_TestSoapStub;
	@Reference    private ISecurityGroup securityGroup;
	@Reference    private EACPDaoPW eacpDaoPW;
	@Reference    private LocationDaoPW locationDaoPW;
	
    public void insertUser(String userId,
                           String lastName,
                           String firstName,
                           String middleName,
                           String fullName,
                           String nameSuffix,
                           String workLocation,
                           String workDepartment,
                           String workCenter,
                           String shift,
                           String payrollId,
                           String obsoleteRecordFlag,
                           String ucfUserInfo1,
                           String ucfUserInfo2,
                           String ucfUserInfo3,
                           String ucfUserInfo4,
                           String ltaSendFlag,
                           String userClass,
                           String emailAddress,
                           String userType,
                           String laborType, 
                           String ucfUserVch5,
                           String ucfUserVch6,
                           String ucfUserVch7,
                           String ucfUserVch8,
                           String ucfUserVch9,
                           String ucfUserVch10,
                           String ucfUserVch11,
                           String ucfUserVch12,
                           String ucfUserVch13,
                           String ucfUserVch14,
                           String ucfUserVch15,
                           Number ucfUserNum1,
                           Number ucfUserNum2,
                           Number ucfUserNum3,
                           Number ucfUserNum4,
                           Number ucfUserNum5,
                           Date ucfUserDate1,
                           Date ucfUserDate2,
                           Date ucfUserDate3,
                           Date ucfUserDate4,
                           Date ucfUserDate5,
                           String ucfUserFlag1,
                           String ucfUserFlag2,
                           String ucfUserFlag3,
                           String ucfUserFlag4, 
                           String ucfUserFlag5,
                           String includeAllSuppliers,
                           Date dateOflastEyeExam, 
			               String ucfUserVch2551, 
			               String ucfUserVch2552, 
			               String ucfUserVch2553, 
			               String ucfUserVch40001, 
			               String ucfUserVch40002,
			               String userDefination, 
			               String toolNo, 
			               String toolChg, 
			               String toolSerialNo, 
			               String homeLocationId, 
			               String miUserFlag) 
    {
        
    	userType = StringUtils.defaultIfEmpty(userType, "@IBASET");		
    	if (!messageFromEACP(userId, shift, ucfUserInfo1, ucfUserInfo2, ucfUserVch6, ucfUserVch7, ucfUserVch8))
    	{
    		if ("SOLUMINA-IC".equalsIgnoreCase(ContextUtil.getUsername()))
    		{
    			// If Insert request not from IDM, throw an error.
    			if (!messageFromIDM(userId, lastName, firstName, fullName, payrollId, shift, ucfUserInfo1, ucfUserInfo2, ucfUserVch6, ucfUserVch7))
    			{
    				message.raiseError("MFI_20","Insert request only allowed from IDM. UserId, " + userId);
    			}
    			shift = StringUtil.iif((shift==null),"1",shift);	
    		}

    		//Check if user already exists.
    		this.Super.insertUser(userId, 
    				              lastName, 
    				              firstName, 
    				              middleName, 
    				              fullName, 
    				              nameSuffix, 
    				              workLocation,
    				              workDepartment, 
    				              workCenter, 
    				              shift, 
    				              payrollId, 
    				              obsoleteRecordFlag, 
    				              ucfUserInfo1, 
    				              ucfUserInfo2, 
    				              ucfUserInfo3, 
    				              ucfUserInfo4, 
    				              ltaSendFlag, 
    				              userClass, 
    				              emailAddress,
    				              userType, 
    				              laborType,
    				              ucfUserVch5,
    				              ucfUserVch6,
    				              ucfUserVch7,
    				              ucfUserVch8,
    				              ucfUserVch9,
    				              ucfUserVch10,
    				              ucfUserVch11,
    				              ucfUserVch12,
    				              ucfUserVch13,
    				              ucfUserVch14,
    				              ucfUserVch15,
    				              ucfUserNum1,
    				              ucfUserNum2,
    				              ucfUserNum3,
    				              ucfUserNum4,
    				              ucfUserNum5,
    				              ucfUserDate1,
    				              ucfUserDate2,
    				              ucfUserDate3,
    				              ucfUserDate4,
    				              ucfUserDate5,
    				              ucfUserFlag1,
    				              ucfUserFlag2,
    				              ucfUserFlag3,
    				              ucfUserFlag4, 
    				              ucfUserFlag5,
    				              includeAllSuppliers,
    	                          dateOflastEyeExam, 
    				              ucfUserVch2551, 
    				              ucfUserVch2552, 
    				              ucfUserVch2553, 
    				              ucfUserVch40001, 
    				              ucfUserVch40002,
    				              userDefination, 
    				              toolNo, 
    				              toolChg, 
    				              toolSerialNo, 
    				              homeLocationId,
    				              miUserFlag);

    		assignDefaultSecurityGroup(userId, workLocation); 

            //Call Authinfo here to get Authorization AKA Security Groups for newly added users
        	try 
        	{   
        		String errorMessage = null;

				try {
					Authinfo[] arrayofAuthinfo = EACP_Service_TestSoapStub.getAuthInfo(userId);

					if (arrayofAuthinfo != null && arrayofAuthinfo.length > 0) {
						for (int i = 0; i < arrayofAuthinfo.length; i++)
						{   				
							String authNumber   = arrayofAuthinfo[i].getAuthorizationNo();    
							String status       = arrayofAuthinfo[i].getStatus();  

							//Add Authorization AKA Security Group to User
							updateUserSecurityGroupEACP(userId,
				                                    null,
				                                    null,
				                                    authNumber,
				                                    status);
						}			    						
					} 
					else
					{
						//No authorizations for new user were returned from eACP 
						errorMessage = "No Authorizations from eACP for New User: "+userId;
						commonUtilsPW.sendErrorEmail(userId, errorMessage,"EACP_M6_EMAIL", "EACP Interface Error - "+commmonDaoPW.selectDbName());
						commmonDaoPW.insertErrorLog(commmonDaoPW.selectNextErrorSequence(), null, "EACP", null, "MFI_20", errorMessage, "EACP New User Authorization Interface Error", "Userid: "+userId, null , ContextUtil.getUsername());		
					}
        		} catch (NullPointerException e) {
        			
    				String endpoint = EACPDaoPW.getEACPEndpoint();
        			errorMessage = "NullPointerException caught for: EACP_Service_TestSoapStub.getAuthInfo(userId)";
        			commonUtilsPW.sendErrorEmail(userId, errorMessage,"EACP_M6_EMAIL", "EACP Interface Error - NullPointerException - "+commmonDaoPW.selectDbName());
        			commmonDaoPW.insertErrorLog(commmonDaoPW.selectNextErrorSequence(), null, "EACP", null, "MFI_20", "eACP Connection Error from "+endpoint+" \n"+errorMessage , "EACP New User Authorization Interface Error", "Userid: "+userId, null, ContextUtil.getUsername());			
        		}        		
    		}
    		catch (RemoteException re)
    		{
				String endpoint = EACPDaoPW.getEACPEndpoint();
    			String errorMessage = re.getMessage();
    			commonUtilsPW.sendErrorEmail(userId, errorMessage,"EACP_M6_EMAIL", "EACP Interface Error - RemoteException - "+commmonDaoPW.selectDbName());
    			commmonDaoPW.insertErrorLog(commmonDaoPW.selectNextErrorSequence(), null, "EACP", null, "MFI_20", "eACP Connection Error from "+endpoint+" \n"+errorMessage , "EACP New User Authorization Interface Error", "Userid: "+userId, null, ContextUtil.getUsername());	
        	}
    	}
  	}
    
    // This method was added to act as a router, depending on whether we are processing a non-IDM transaction or not.
    // The original updateUser method was renamed to updateUserOther.
    public void updateUser(String userIdToModify,
            			   String lastName,
            			   String firstName,
            			   String middleName,
            			   String fullName,
            			   String nameSuffix,
            			   String workLocation,
            			   String workDepartment,
            			   String workCenter,
            			   String shift,
            			   String payrollId,
            			   String obsoleteRecordFlag,
            			   String ucfUserInfo1,
            			   String ucfUserInfo2,
            			   String ucfUserInfo3,
            			   String ucfUserInfo4,
            			   String ltaSendFlag,
            			   String userClass,
            			   String emailAddress,
            			   String userType,
            			   String ucfUserVch5,
            			   String ucfUserVch6,
            			   String ucfUserVch7,
            			   String ucfUserVch8,
            			   String ucfUserVch9,
            			   String ucfUserVch10,
            			   String ucfUserVch11,
            			   String ucfUserVch12,
            			   String ucfUserVch13,
            			   String ucfUserVch14,
            			   String ucfUserVch15,
            			   Number ucfUserNum1,
            			   Number ucfUserNum2,
            			   Number ucfUserNum3,
            			   Number ucfUserNum4,
            			   Number ucfUserNum5,
            			   Date ucfUserDate1,
            			   Date ucfUserDate2,
            			   Date ucfUserDate3,
            			   Date ucfUserDate4,
            			   Date ucfUserDate5,
            			   String ucfUserFlag1,
            			   String ucfUserFlag2,
            			   String ucfUserFlag3,
            			   String ucfUserFlag4, 
            			   String ucfUserFlag5,
            			   String includeAllSuppliers,
                           Date dateOflastEyeExam, 
			               String ucfUserVch2551, 
			               String ucfUserVch2552, 
			               String ucfUserVch2553, 
			               String ucfUserVch40001, 
			               String ucfUserVch40002,
			               String laborType,
			               String miUserFlag) 
    {
    	
    	if (("SOLUMINA-IC".equalsIgnoreCase(ContextUtil.getUsername())) &&
    		(messageFromIDM(userIdToModify, lastName, firstName, fullName, payrollId, shift, ucfUserInfo1, ucfUserInfo2, ucfUserVch6, ucfUserVch7)))
    	{    		
    		// Check if this is a transaction to 'Obsolete' the user.  
    		if ("DISABLE".equalsIgnoreCase(payrollId))
        	{
    			obsoleteRecordFlag = "Y";
    			String origPayrollId = userDaoPW.getUserPayrollId(userIdToModify);
        		payrollId = origPayrollId;
        	}
        	else
        	{
        		obsoleteRecordFlag = "N";
        	}
    		
    		updateUserIDM(userIdToModify,
  				  		  lastName,
  				  		  firstName,
  				  		  middleName,
  				  		  fullName,
  				  		  nameSuffix,
  				  		  shift,
  				  		  payrollId,
  				  		  obsoleteRecordFlag,
  				  		  ucfUserInfo1,
  				  		  ucfUserVch6,
  				  		  ucfUserVch7);
    	}
    	//EACP Begin
    	else if (("SOLUMINA-IC".equalsIgnoreCase(ContextUtil.getUsername())) &&
        		(messageFromEACP(userIdToModify, shift, ucfUserInfo1, ucfUserInfo2, ucfUserVch6, ucfUserVch7, ucfUserVch8)))
        {
    		
    		updateUserSecurityGroupEACP(userIdToModify,
		  		                        ucfUserInfo1,
				  		                ucfUserVch6,
				  		                ucfUserVch7,  
				  		                ucfUserVch8);
    		
    	}
    	//EACP Ends
    	else
    	{
    		updateUserOther(userIdToModify,
               				lastName,
               				firstName,
               				middleName,
               				fullName,
               				nameSuffix,
               				workLocation,
               				workDepartment,
               				workCenter,
               				shift,
               				payrollId,
               				obsoleteRecordFlag,
               				ucfUserInfo1,
               				ucfUserInfo2,
               				ucfUserInfo3,
               				ucfUserInfo4,
               				ltaSendFlag,
               				userClass,
               				emailAddress,
               				userType,
               				ucfUserVch5,
               				ucfUserVch6,
               				ucfUserVch7,
               				ucfUserVch8,
               				ucfUserVch9,
               				ucfUserVch10,
               				ucfUserVch11,
               				ucfUserVch12,
               				ucfUserVch13,
               				ucfUserVch14,
               				ucfUserVch15,
               				ucfUserNum1,
               				ucfUserNum2,
               				ucfUserNum3,
               				ucfUserNum4,
               				ucfUserNum5,
               				ucfUserDate1,
               				ucfUserDate2,
               				ucfUserDate3,
               				ucfUserDate4,
               				ucfUserDate5,
               				ucfUserFlag1,
               				ucfUserFlag2,
               				ucfUserFlag3,
               				ucfUserFlag4, 
               				ucfUserFlag5,
               				includeAllSuppliers,
	                        dateOflastEyeExam, 
				            ucfUserVch2551, 
				            ucfUserVch2552, 
				            ucfUserVch2553, 
				            ucfUserVch40001, 
				            ucfUserVch40002,
				            laborType,
				            miUserFlag);
    	}
    }
	
    //This method was renamed from updateUser.  It will only handle non-IDM transactions.
    public void updateUserOther(String userIdToModify,
                           		String lastName,
                           		String firstName,
                           		String middleName,
                           		String fullName,
                           		String nameSuffix,
                           		String workLocation,
                           		String workDepartment,
                           		String workCenter,
                           		String shift,
                           		String payrollId,
                           		String obsoleteRecordFlag,
                           		String ucfUserInfo1,
                           		String ucfUserInfo2,
                           		String ucfUserInfo3,
                           		String ucfUserInfo4,
                           		String ltaSendFlag,
                           		String userClass,
                           		String emailAddress,
                           		String userType,
                           		String ucfUserVch5,
                           		String ucfUserVch6,
                           		String ucfUserVch7,
                           		String ucfUserVch8,
                           		String ucfUserVch9,
                           		String ucfUserVch10,
                           		String ucfUserVch11,
                           		String ucfUserVch12,
                           		String ucfUserVch13,
                           		String ucfUserVch14,
                           		String ucfUserVch15,
                           		Number ucfUserNum1,
                           		Number ucfUserNum2,
                           		Number ucfUserNum3,
                           		Number ucfUserNum4,
                           		Number ucfUserNum5,
                           		Date ucfUserDate1,
                           		Date ucfUserDate2,
                           		Date ucfUserDate3,
                           		Date ucfUserDate4,
                           		Date ucfUserDate5,
                           		String ucfUserFlag1,
                           		String ucfUserFlag2,
                           		String ucfUserFlag3,
                           		String ucfUserFlag4, 
                           		String ucfUserFlag5,
                           		String includeAllSuppliers,
                                Date dateOflastEyeExam, 
     			                String ucfUserVch2551, 
     			                String ucfUserVch2552, 
     			                String ucfUserVch2553, 
     			                String ucfUserVch40001, 
     			                String ucfUserVch40002,
     			                String laborType,
     			                String miUserFlag)
    {
    	String oldBusinessUnit = null;
        String oldCostCenter = null;
        String oldOccCode = null;
        String oldWorkLocation = null;
        String oldWorkLocationId = null;

        String userMakingModification = ContextUtil.getUsername();
        
        //Verify that the userId for this update exists.
    	if (!userDaoPW.checkIfUserExists(userIdToModify))
    	{
    		if ("SOLUMINA-IC".equalsIgnoreCase(userMakingModification))
            {
    			throw new SoluminaException("Update request not processed. UserId, " +userIdToModify+ ", does not exists.");
            }
    		else
    		{
    			message.raiseError("MFI_20","Update request not processed. UserId, " +userIdToModify+ ", does not exists.");
    		}
    	}

        includeAllSuppliers = StringUtils.defaultIfEmpty(includeAllSuppliers, SoluminaConstants.NO);

        //Make sure user has update privileges
        if (!user.hasPrivilege(SoluminaConstants.USER_UPDATE_PRIV))
        {
            message.raiseError("MFI_20402", SoluminaConstants.USER_UPDATE_PRIV);
        }

        //The ltaSendFlag's default is 'N'
        if (ltaSendFlag == null)
        {
            ltaSendFlag = SoluminaConstants.NO;
        }

        //obsolete record flag should only be Y, N, or null
        if (obsoleteRecordFlag != null
                && !StringUtils.equals(SoluminaConstants.YES, obsoleteRecordFlag)
                && !StringUtils.equals(SoluminaConstants.NO, obsoleteRecordFlag))
        {
            message.raiseError("MFI_52965");
        }

        //Gets the User Information from SFFND_USER.
        Map userInformation = userDao.selectUserInformation(userIdToModify);

        String userObsoleteRecordFlag = (String) userInformation.get("OBSOLETE_RECORD_FLAG");

        //Sets the User Obsolete Record Flag to 'N' if it is empty.
        userObsoleteRecordFlag = StringUtils.defaultIfEmpty(userObsoleteRecordFlag, SoluminaConstants.NO);

        //Get the global LTA parameter
        boolean ltaSystemFlag = ContextUtil.getUser()
                                           .getContext()
                                           .getSendLtaSystemFlag();

        //LTA User can NOT be Y when global parameter is N.
        if (StringUtils.equals(ltaSendFlag, SoluminaConstants.YES) && !ltaSystemFlag)
        {
            message.raiseError("MFI_87657");
        }
        
        if (workLocation == null &&
        		(workDepartment != null || workCenter != null))
        {
        	message.raiseError("MFI_569E6F058A235083E0440003BA041A64");
        }

        if ((workLocation == null || workDepartment == null)
        		&& workCenter != null)
        {
        	message.raiseError("MFI_569E6F058A275083E0440003BA041A64");
        }

        if ("SOLUMINA-IC".equalsIgnoreCase(userMakingModification))
        {
        	//Obtain current information from sffnd_user before updating with new values
            Map userMap = new HashMap();
            
            userMap = commmonDaoPW.selectUserInformation(userIdToModify);
            
            oldBusinessUnit  = (String) userMap.get("UCF_USER_VCH5");
    		oldCostCenter 	 = (String) userMap.get("UCF_USER_VCH3");
    		oldOccCode 		 = (String) userMap.get("UCF_USER_VCH1");
    		//oldWorkLocation = (String) userMap.get("WORK_LOC");
    		oldWorkLocation = (String) userMap.get("LOCATION_ID");
    		
    		oldBusinessUnit	= StringUtil.iif((oldBusinessUnit==null),"",oldBusinessUnit);
    		oldCostCenter	= StringUtil.iif((oldCostCenter==null),"",oldCostCenter);
    		oldOccCode 		= StringUtil.iif((oldOccCode==null),"",oldOccCode);
    		//oldWorkLocation   = StringUtil.iif((oldWorkLocation==null),"",oldWorkLocation);
    		oldWorkLocation = StringUtil.iif((oldWorkLocation==null),"",oldWorkLocation);
    		
    		//Get the work location based on the Subsystem Grouping (UserVarChar2 field in the XML message - comes into the method as ucfUserInfo2).
    		try
    		{
    			workLocation = userDaoPW.selectWorkLocBySubsystemGroup(ucfUserInfo2); 
    	    	
    	    	assignDefaultSecurityGroup(userIdToModify, workLocation); ///RTTT moved here from above
    		}
    		catch(Exception e)
    		{
    			throw new SoluminaException("Work Location not found for Subsystem Group  " + ucfUserInfo2);
    		}
            
        	workLocation = StringUtil.iif((workLocation==null),"",workLocation);
        }

        int count = 0;
        
        //For updates from M6 Interface, call PW method instead of OOB method. 	
    	//G8 need to lookup workLocation ID, workDepartment ID, workCenterID   
        String locationId = null;
        String departmentId = null;
        String centerId = null;
        
        if (!StringUtils.isEmpty(workLocation) || workLocation != null)
        {
        	try
        	{
        	locationId = locationDaoPW.getWorkLocationID(workLocation);
        	}
        	catch(Exception e)
    		{
    			throw new SoluminaException("Work Location not found " + ucfUserInfo2);
    		}
        }

        if (!StringUtils.isEmpty(workDepartment) || workDepartment != null )
        {
        	departmentId = locationDaoPW.getWorkDeptID(locationId,workDepartment);
        }

        if (!StringUtils.isEmpty(workCenter) || workCenter != null)
        {
        	centerId = locationDaoPW.getWorkCenterID(locationId, departmentId, workCenter);
        }
    	
        if ("SOLUMINA-IC".equalsIgnoreCase(userMakingModification))
        {
        	count = userDaoPW.updateUser(lastName,
        								 firstName,
        								 middleName,
        								 fullName,
        								 nameSuffix,
        								 locationId,
        								 departmentId,
        								 centerId,
        								 shift,
        								 payrollId,
        								 obsoleteRecordFlag,
        								 ucfUserInfo1,
        								 ucfUserInfo2,
        								 ucfUserInfo3,
        								 ucfUserInfo4,
        								 ltaSendFlag,
        								 userClass,
        								 emailAddress,
        								 userIdToModify,
        								 userType,
        								 ucfUserVch5,
        								 ucfUserVch6,
        								 ucfUserVch7,
        								 ucfUserVch8,
        								 ucfUserVch9,
        								 ucfUserVch10,
        								 ucfUserVch11,
        								 ucfUserVch12,
        								 ucfUserVch13,
        								 ucfUserVch14,
        								 ucfUserVch15,
        								 ucfUserNum1,
        								 ucfUserNum2,
        								 ucfUserNum3,
        								 ucfUserNum4,
        								 ucfUserNum5,
        								 ucfUserDate1,
        								 ucfUserDate2,
        								 ucfUserDate3,
        								 ucfUserDate4,
        								 ucfUserDate5,
        								 ucfUserFlag1,
        								 ucfUserFlag2,
        								 ucfUserFlag3,
        								 ucfUserFlag4, 
        								 ucfUserFlag5,
        								 includeAllSuppliers);
        }
        else
        {
        	count = userDao.updateUser(lastName,
                                       firstName,
                                       middleName,
                                       fullName,
                                       nameSuffix,
                                       locationId,
                                       departmentId,
                                       centerId,
                                       shift,
                                       payrollId,
                                       obsoleteRecordFlag,
                                       ucfUserInfo1,
                                       ucfUserInfo2,
                                       ucfUserInfo3,
                                       ucfUserInfo4,
                                       userClass,
                                       emailAddress,
                                       userIdToModify,
                                       userType,
                                       ucfUserVch5,
                                       ucfUserVch6,
                                       ucfUserVch7,
                                       ucfUserVch8,
                                       ucfUserVch9,
                                       ucfUserVch10,
                                       ucfUserVch11,
                                       ucfUserVch12,
                                       ucfUserVch13,
                                       ucfUserVch14,
                                       ucfUserVch15,
                                       ucfUserNum1,
                                       ucfUserNum2,
                                       ucfUserNum3,
                                       ucfUserNum4,
                                       ucfUserNum5,
                                       ucfUserDate1,
                                       ucfUserDate2,
                                       ucfUserDate3,
                                       ucfUserDate4,
                                       ucfUserDate5,
                                       ucfUserFlag1,
                                       ucfUserFlag2,
                                       ucfUserFlag3,
                                       ucfUserFlag4,
                                       ucfUserFlag5,
                                       includeAllSuppliers,
                                       ucfUserVch2551,
                                       ucfUserVch2552,
                                       ucfUserVch2553,
                                       ucfUserVch40001,
                                       ucfUserVch40002,
                                       null,
                                       miUserFlag);
      }

        if (count < 1)
        {
            logger.debug("UserImpl[updateUser]: Rowcount for update = " + count);
        }

        //Begin M206
        if ("SOLUMINA-IC".equalsIgnoreCase(ContextUtil.getUsername()))
        {
        	ucfUserInfo1 = StringUtil.iif((ucfUserInfo1==null),"",ucfUserInfo1);
        	ucfUserInfo3 = StringUtil.iif((ucfUserInfo3==null),"",ucfUserInfo3);
        	ucfUserVch5  = StringUtil.iif((ucfUserVch5==null),"",ucfUserVch5);
        	
			String oldRole = "";
			String newRole = "";

			// If the Work Location has not changed, check the value of the Occupation Code on the incoming message (ucf_user_vch1).
			// If it is not empty, compare the incoming OCC Code (ucf_user_vch1) with the old Occ Code.  If it has changed, update the role.
			if (oldWorkLocation.equals(workLocation))
			{
				if (!ucfUserInfo1.isEmpty())
				{
					if (!(ucfUserInfo1.equals(oldOccCode)))
					{
						// Get the role that was originally associated with the user.
						oldRole = commmonDaoPW.getRoleForPlantOccCode(oldWorkLocation, oldOccCode);
						
						//Get the new role for the user.
						newRole = commmonDaoPW.getRoleForPlantOccCode(workLocation, ucfUserInfo1);
						System.out.println("newRole is "+newRole);
						
						
						//If the user's role has changed, reassign it.
						if (!(newRole.equals(oldRole)))
						{
							if (!(oldRole.isEmpty()))
							{
							    Super.removeUserRole(userIdToModify, oldRole);
							}
							
							if (!(newRole.isEmpty()))
							{
								Super.assignUserRole(userIdToModify, newRole); 
							}
						}
					}
				}
			}
			else
			{
				String occCodeToUse = "";
				
				if (ucfUserInfo1.isEmpty())
				{
					occCodeToUse = oldOccCode;
				}
				else
				{
					occCodeToUse = ucfUserInfo1;
				}
				
				//Get the role that was originally associated with the user
				oldRole = commmonDaoPW.getRoleForPlantOccCode(oldWorkLocation, oldOccCode);
				
				//Get the new role for the user.
				newRole = commmonDaoPW.getRoleForPlantOccCode(workLocation, occCodeToUse);
				System.out.println("newRole is "+newRole);
				
				if (!(newRole.equals(oldRole)))
				{
					if (!(oldRole.isEmpty()))
					{
					    Super.removeUserRole(userIdToModify, oldRole);
					}
					
					if (!(newRole.isEmpty()))
					{
						Super.assignUserRole(userIdToModify, newRole); 
					}

		}
			}

        }
		// End M206

        if (!"SOLUMINA-IC".equalsIgnoreCase(ContextUtil.getUsername()))	//All INTERFACE changes to the obsolete flag are handled by IDM Update.
        {
        	//Update Named License counts as well for the USER if obsolete record
        	//flag has been changed if licensing mode is NAMED
        	if (StringUtils.equals(license.getLicensingMode(),
        			SoluminaConstants.LICENSING_MODE_NAMED))
        	{
        		if ((obsoleteRecordFlag != null) && (!StringUtils.equals(obsoleteRecordFlag, userObsoleteRecordFlag)))
        		{
        			// New flag (obsoleteRecordFlag) is different from existing
        			// (userObsoleteRecordFlag from DB)
        			if (StringUtils.equals(obsoleteRecordFlag, SoluminaConstants.YES))
        			{
        				// Check out named license
        				license.checkInOutNamedLicense(user.getLoggedInUsername(), "I", "USER");
        			}
        			else
        			{
        				// Check in because now obsolete record flag became N
        				license.checkInOutNamedLicense(user.getLoggedInUsername(), "O", "USER");
        			}
        		}
        		
        		//If we are making a record non-obsolete we need to ensure that the SF$CONNECT role is granted
                if (StringUtils.equals(userObsoleteRecordFlag, SoluminaConstants.YES))
                {
                    if (StringUtils.equals(obsoleteRecordFlag, SoluminaConstants.NO))
                    {
                        user.assignUserRole(userMakingModification,
                                            userIdToModify,
                                            SoluminaConstants.ROLE_CONNECT,
                                            com.ibaset.solumina.sfcore.application.IUser.GRANT,
                                            SoluminaConstants.YES,
                                            SoluminaConstants.YES);

                        this.Super.resetPassword(userIdToModify);
                    }
                }
                else
                {
                    //If we are changing a user from non-obsolete to obsolete, we
                    //should revoke all roles
                    if (StringUtils.equals(obsoleteRecordFlag, SoluminaConstants.YES))
                    {
                        //revokeAllSoluminaRoles(userIdToModify);
                        String userName = ContextUtil.getUsername();
                        userDao.obsoleteUser(userName, userIdToModify);
                    }
                }
        	}
        }
       
                
        //Assign all suppliers to User.
        if(StringUtils.equals(includeAllSuppliers,SoluminaConstants.YES))
        {
        	userSupplierDao.assignUserSuppliers(userIdToModify);
        }
    }
    
    public void updateUserIDM(String userIdToModify,
            				  String lastName,
            				  String firstName,
            				  String middleName,
            				  String fullName,
            				  String nameSuffix,
            				  String shift,
            				  String payrollId,
            				  String obsoleteRecordFlag,
            				  String ucfUserInfo1,
            				  String ucfUserVch6,
            				  String ucfUserVch7)
    {
    	if (!userDaoPW.checkIfUserExists(userIdToModify))
    	{
      		String errorMessage = "Update request not processed. UserId, " +userIdToModify+ ", does not exists.";
			commonUtilsPW.sendErrorEmail(userIdToModify, errorMessage,"IDM_M6_EMAIL", "IDM Interface Error - "+commmonDaoPW.selectDbName());
			commmonDaoPW.insertErrorLog(commmonDaoPW.selectNextErrorSequence(), null, "IDM", null, "MFI_20", errorMessage , "IDM Error", "Userid: "+userIdToModify, null , ContextUtil.getUsername());		
			
			throw new SoluminaException("For User Id, " + userIdToModify + ": " + errorMessage);
    	}
    	
    	shift = StringUtil.iif((shift==null),"1",shift);
    	
    	//Get the user's current Obsolete Flag before it is updated.
    	Map userInformation = userDao.selectUserInformation(userIdToModify);
        String oldObsoleteFlag = (String) userInformation.get("OBSOLETE_RECORD_FLAG");
        
        //Update the user.
    	userDaoPW.updateUserIDM(lastName, 
    							firstName,
    							middleName,
    							fullName,
    							nameSuffix,
    							shift,
    							payrollId,
    							obsoleteRecordFlag,
    							ucfUserInfo1,
    							ucfUserVch6,
    							ucfUserVch7,
    							userIdToModify);
    	
    	//If we are changing a record from obsolete to non-obsolete, we need to ensure that the SF$CONNECT role is granted.
    	if (StringUtils.equals(oldObsoleteFlag, SoluminaConstants.YES) && StringUtils.equals(obsoleteRecordFlag, SoluminaConstants.NO))
    	{
    		license.checkInOutNamedLicense(user.getLoggedInUsername(), "O", "USER");  // IDM 2 OIM   ????  RTT was commented out.. why?
    		
    		user.assignUserRole(ContextUtil.getUsername(),
    							userIdToModify,
    							SoluminaConstants.ROLE_CONNECT,
    							com.ibaset.solumina.sfcore.application.IUser.GRANT,
    							SoluminaConstants.YES,
    							SoluminaConstants.YES);

    		this.Super.resetPassword(userIdToModify);
    	}
    	else
    	{
    		// If we are changing a user from non-obsolete to obsolete, we should revoke all roles.
    		if (StringUtils.equals(oldObsoleteFlag, SoluminaConstants.NO) && StringUtils.equals(obsoleteRecordFlag, SoluminaConstants.YES))
    		{
    			license.checkInOutNamedLicense(user.getLoggedInUsername(), "I", "USER"); // IDM 2 OIM   ?????  RTT was commented out.. why?			
    			userDao.obsoleteUser(ContextUtil.getUsername(), userIdToModify);
    		}
    	}
    }
    
    public void assignUserRole(String userId, String role)
    {
        validator.checkUserPrivilege(SoluminaConstants.USER_UPDATE_PRIV);
        String obsoleteRecordFlag = userDao.getObsoleteFlag(userId);

        if (StringUtils.equals(obsoleteRecordFlag, SoluminaConstants.YES))
        {
            message.raiseError("MFI_16", userId);
        }

        if (StringUtils.isEmpty(role))
        {
            message.raiseError("MANDATORY_FIELD", "ROLE");
        }

        //Check if role is SF$SUPERUSER, if yes, check for ASSIGN_SUPERUSER Priv
        if ("SF$SUPERUSER".equals(role) || "SF$SYSTEM_ADMINISTRATION".equals(role)) //Defect 576
        {
        	validator.checkUserPrivilege("ASSIGN_SUPERUSER");
        }
        user.assignUserRole(ContextUtil.getUsername(),
                            userId,
                            role,
                            com.ibaset.solumina.sfcore.application.IUser.GRANT,
                            SoluminaConstants.YES,
                            SoluminaConstants.NO);
    }
    
    public boolean messageFromIDM(String userId,
    							  String lastName,
    							  String firstName,
    							  String fullName,
    							  String payrollId,
    							  String shift,
    							  String ucfUserInfo1,
    							  String ucfUserInfo2,
            			   		  String ucfUserVch6,
            			   		  String ucfUserVch7)
    {
    	boolean messageFromIDM = false;
    	String  errorMessage   = null;
    	
    	ucfUserInfo2 = StringUtil.iif((ucfUserInfo2==null),"",ucfUserInfo2);
    	
    	//Check to see if this message is from IDM.
    	if (ucfUserInfo2.contains("IDM"))
    	{
    		if (shift == null)
    		{
    			shift = "1";
    		}
    		
    		// Since the message is from IDM, validate the fields.
    		if (fullName == null)
    		{
    			errorMessage = "User name is required.";
    		}
    		else
    		if (ucfUserVch6 == null)
        	{
        		errorMessage = "Supervisor Id is required.";
        	}
        	else
        	if (ucfUserVch7 == null)
        	{
        		errorMessage = "Supervisor Name is required.";
        	}	
    		
    		// Don't do the following checks for contractors.
    		if (!"X".equals(userId.substring(0,1)))
    		{
    			if (payrollId == null)
        		{
        			errorMessage = "Payroll Id is required.";
        		}
        		else
        		if (ucfUserInfo1 == null)
        		{
        			errorMessage = "ucfUserInfo1 (Job Code) is required.";
        		}
    		}
    		
    		if (errorMessage != null)
    		{
    			commonUtilsPW.sendErrorEmail(userId, errorMessage,"IDM_M6_EMAIL", "IDM Interface Error - "+commmonDaoPW.selectDbName());
    			commmonDaoPW.insertErrorLog(commmonDaoPW.selectNextErrorSequence(), null, "IDM", null, "MFI_20", errorMessage, "IDM Error", "Userid: "+userId, null , ContextUtil.getUsername());	

				throw new SoluminaException("For User Id, " + userId + ": " + errorMessage);
    		}  		
    		messageFromIDM = true;
    	}
    	return messageFromIDM;
    }
    
    public void updateUserSecurityGroupEACP(String userIdToModify,
    						                String ucfUserInfo1,
    						                String ucfUserVch6,
    						                String ucfUserVch7,
    						                String ucfUserVch8)
    {
    	//Does the security group exist in the master table?
    	boolean securityGroupExists = UserDaoPW.doesSecurityGroupExist(ucfUserVch7);

    	if (securityGroupExists)
    	{
    		//Does the user exist
    		if (!userDaoPW.checkIfUserExistsAndNotObsolete(userIdToModify))
    		{
    			String errorMessage = "Update request not processed. UserId, " +userIdToModify+ ", does not exist or is Obsolete.";
    			commonUtilsPW.sendErrorEmail(userIdToModify, errorMessage,"EACP_M6_EMAIL", "EACP M6 Interface Error - "+commmonDaoPW.selectDbName());

    			throw new SoluminaException("For User Id, " + userIdToModify + ": " + errorMessage);
    		}  	

    		boolean userSecurityGroupExists = UserDaoPW.doesUserSecurityGroupExist(userIdToModify, ucfUserVch7);
    		if (userSecurityGroupExists)
    		{ 
    			//Update the users security group
    			if ("Active".equalsIgnoreCase(ucfUserVch8))
    			{
    			userDaoPW.updateActiveUserSecurityGroupEACP(ucfUserInfo1,
    					                                    ucfUserVch6,
    					                                    ucfUserVch7,
    					                                    ucfUserVch8,
    					                                    userIdToModify);
    			}
    			else
    			{
    			//Expire the users license/security group
    			userDaoPW.updateInactiveUserSecurityGroupEACP(ucfUserInfo1,
                                                              ucfUserVch6,
                                                              ucfUserVch7,
                                                              ucfUserVch8,
                                                              userIdToModify);
    			}
    		}
    		else 
    		{
    			if ("Active".equalsIgnoreCase(ucfUserVch8))	
    			{
    			// insert user security group
    			userDaoPW.insertUserSecurityGroupEACP(ucfUserVch7,
    					                              ucfUserVch8,
    					                              userIdToModify);
    			}
    		}
    	}
    	else
    	{
    		//Security group does not exist.
			String errorMessage = "Update request not processed. Security Group "+ucfUserVch7+ " does not exist in solumina and was not added to userid: "+userIdToModify;
			commonUtilsPW.sendErrorEmail(userIdToModify, errorMessage,"EACP_M6_EMAIL", "EACP M6 Interface Error - "+commmonDaoPW.selectDbName());
			commmonDaoPW.insertErrorLog(commmonDaoPW.selectNextErrorSequence(), null, "EACP" , null, "MFI_20" , errorMessage, "EACP New User Authorization Interface Error", "Userid: "+userIdToModify+" Security Group: "+ucfUserVch7 , null,  ContextUtil.getUsername());	
    	}
    }

    public boolean messageFromEACP(String userId,
    		                       String shift,
    		                       String ucfUserInfo1,
    		                       String ucfUserInfo2,
    		                       String ucfUserVch6,
    		                       String ucfUserVch7,
    		                       String ucfUserVch8)
    {
    	boolean messageFromEACP = false;
    	String  errorMessage   = null;

    	ucfUserInfo2 = StringUtil.iif((ucfUserInfo2==null),"",ucfUserInfo2);

    	//Check to see if this message is from EACP.
    	if (ucfUserInfo2.contains("EACP"))
    	{
    		messageFromEACP = true;
    	}
    	return messageFromEACP;
    }
    
    public void assignDefaultSecurityGroup(String userId, String workLocation)
    {
    	if(workLocation != null && !workLocation.isEmpty())
    	{
        	//Assign default security group based on home work location.  if home work location is not in the XREF table  log it
        	String defaultSecurityGroup = userDaoPW.getDefaultSecurityGroup(workLocation);
        	
    		if (!"NONE".equalsIgnoreCase(defaultSecurityGroup))
    		{
    			//delete existing default security group 
    			userDaoPW.deleteDefaultSecurityGroupByUser(userId);
    			try 
    			{
    				java.util.Date startDate = new java.util.Date();
    				startDate = new java.sql.Timestamp(startDate.getTime()); 

    				Date endDate   =  DateUtils.getUtilDate("01/01/9999");

    				//Insert Default Security Group based on home work location
    				securityGroup.insertUserSecurityGroup(userId, defaultSecurityGroup, startDate, endDate);

    			} 
    			catch (ParseException e)
    			{
    				commonUtilsPW.sendErrorEmail(userId, e.getMessage(),"EACP_M6_EMAIL", "Default Security Group Error - "+commmonDaoPW.selectDbName()); 
    				commmonDaoPW.insertErrorLog(commmonDaoPW.selectNextErrorSequence() , null, "DEFAULT_SECURITY_GROUP" , null, "MFI_20", e.getMessage(), "Default Security Group", "Userid: "+userId+" Home Work Location: "+workLocation,  null,  ContextUtil.getUsername());	
    			}
    		} 
    		else
    		{
    			//Default Security Group does not exist for Work Location. Log into PWUST_ERROR_LOG and send an eamil
    			String errorMessage = "Default Security Group for Work Location " +workLocation+ " does not exist in solumina.  No Default Security Group was added to userid: "+userId;
    			commonUtilsPW.sendErrorEmail(userId, errorMessage,"EACP_M6_EMAIL", "Default Security Group Error - "+commmonDaoPW.selectDbName());  
    			commmonDaoPW.insertErrorLog(commmonDaoPW.selectNextErrorSequence(), null, "DEFAULT_SECURITY_GROUP", null, "MFI_20", errorMessage, "Default Security Group", "Userid: "+userId+" Home Work Location: "+workLocation, null,  ContextUtil.getUsername());	       	
    		}
    	}  
    }

}

