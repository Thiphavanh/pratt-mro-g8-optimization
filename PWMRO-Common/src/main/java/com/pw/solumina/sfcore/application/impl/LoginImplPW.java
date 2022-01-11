/*
*  This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    LoginImplPW.java
* 
*  Created: 2017-09-25
* 
*  Author:  R. Cannon
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------		-------------------------------------------------------------------
* 2017-09-25 	R. Cannon		    Initial Release SMRO_USR_201
* 2018-08-22 	Fred Ettefagh       defect 777 force client exits after 3 invalid login attempts 
* 2018-11-30    D.Miron             SMRO_USR_201 Defect 345 - Moved update to UCF for last LDAP change date after
*                                   login.  This fixes "User Terminted Session" caused by history trigger on 
*                                   SFFND_USER. 
* 2019-05-28    T. Phan             Added loginType param so loginUser runs. Defect 1273 R2_upgrade
* 2019-06-20    D.Miron             SMRO_USR_201 Defect 1176 - Raise Error instead of Show Message when LDAP password is expired.
*                                   Show Message was creating trigger error on table SFFND_USER on date update.
* 2020-01-28    D.Miron             SMRO_USR_201 Defect 1176 - Reworded message displayed to change LDAP password. 
*/

package com.pw.solumina.sfcore.application.impl;

import static com.ibaset.common.FrameworkConstants.FOUNDATION;
import static com.ibaset.common.FrameworkConstants.USER_LOGIN_FAILED_LIMIT;
import static org.apache.commons.lang.StringUtils.isEmpty;
import static org.apache.commons.lang.StringUtils.substring;
import static org.apache.commons.lang.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang.math.NumberUtils.INTEGER_ZERO;
import static org.apache.commons.lang.math.NumberUtils.toInt;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

import org.springframework.dao.EmptyResultDataAccessException;

import com.ibaset.common.context.SoluminaContextHolder;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.solumina.sfcore.application.ILogin;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sfcore.dao.ILoginDao;
import com.ibaset.solumina.sffnd.application.IUserClass;
import com.ibaset.solumina.sffnd.dao.IGlobalConfigurationDao;
import com.ibaset.solumina.sffnd.dao.ISecurityGroupDao;
import com.pw.common.utils.LdapBean;
import com.pw.common.utils.StringUtil;
import com.pw.solumina.sfcore.dao.LoginDaoPW;

import com.netegrity.sdk.apiutil.SmApiConnection;
import com.netegrity.sdk.apiutil.SmApiResult;
import com.netegrity.sdk.apiutil.SmApiSession;
import com.netegrity.sdk.dmsapi.SmDmsApi;
import com.netegrity.sdk.dmsapi.SmDmsApiImpl;
import com.netegrity.sdk.dmsapi.SmDmsConfig;
import com.netegrity.sdk.dmsapi.SmDmsDirectory;
import com.netegrity.sdk.dmsapi.SmDmsDirectoryContext;
import com.netegrity.sdk.dmsapi.SmDmsOrganization;
import com.netegrity.sdk.dmsapi.SmDmsUser;
import com.netegrity.sdk.dmsapi.SmDmsUserPWState;
import com.netegrity.sdk.policyapi.SmUserDirectory;

import netegrity.siteminder.javaagent.AgentAPI;
import netegrity.siteminder.javaagent.InitDef;
import netegrity.siteminder.javaagent.ServerDef;

import netscape.ldap.LDAPAttribute;
import netscape.ldap.LDAPAttributeSet;
import netscape.ldap.LDAPConnection;
import netscape.ldap.LDAPEntry;
import netscape.ldap.LDAPException;
import netscape.ldap.LDAPSearchResults;

public abstract class LoginImplPW extends ImplementationOf<ILogin> implements ILogin
{
	private static final String MT_FAT = "MT_FAT";
	protected static String ATTRS[] = {"cn", "pwcitizenship", "givenName", "sn", "mail"}; 
	private String myHost;
	private Number myPort;
	private String mgrDN;
	private String mgrPW;
	
	@Reference private ILoginDao loginDao;
    @Reference private IMessage message;
	@Reference private ISecurityGroupDao iSecGrpDao;
	@Reference private LdapBean lBean;
	@Reference private LoginDaoPW loginDaoPW;
    @Reference private IUserClass sffndUserClass = null;
    @Reference private IGlobalConfigurationDao globalConfigurationDao = null;
	
	@SuppressWarnings("rawtypes")
	public String loginUser (String userId,
					         String supplierId,
					         String connectionType,
					         String ipAddress,
					         String appIniId,
					         String appVersion,
					         String pcFlag,
					         String userType,
					         String loginType) //Defect 1273
	{
 		String env = getEnvironment();
		String smAgentName = getSMagentName();
		
		boolean exemptId;
		boolean smEnabled;
		
		Date smDate = null;    // Defect 345
				
		if (!"local".equalsIgnoreCase(env))
		{
			if (!"SOLUMINA"  .equalsIgnoreCase(userId) &&
				!"SUPERUSER" .equalsIgnoreCase(userId) &&
				!"ANONYMOUS" .equalsIgnoreCase(userId) &&
				!"CONVERSION".equalsIgnoreCase(userId))
			{
				exemptId = loginDaoPW.isUserLdapExempt(userId);
				smEnabled = loginDaoPW.globalConfigObjEnabled("SITEMINDER_ENABLED");
				
				if (!exemptId && smEnabled && !"local".equalsIgnoreCase(smAgentName))
				{
					Map extPropsMap = loginDaoPW.getExternalProps(smAgentName);
					//String pwdLink = (String) extPropsMap.get("LDAP_CHG_PWD_LINK");

					lBean = getLdapUserInfo(userId, extPropsMap);
					String userDN = lBean.getUserDN();
					Date lastPwdChg = loginDaoPW.getLastPasswordChange(userId);
					
					int daysSinceUpd;
						
					if (lastPwdChg == null) //new user
					{
						smDate = getSMinfo(userDN, extPropsMap);

						if (smDate != null) 
						{ 
							lastPwdChg = smDate;
							// loginDaoPW.setLastDtPwdChgd(userId, smDate);
						}
						else //Siteminder didn't have a value for updating when the last password was changed. Why?
						{
							message.raiseError("MFI_20", "Siteminder date \"last password change\" unavailable. Please contact system administrator.");
						}
					}
						
					daysSinceUpd = loginDaoPW.numDaysTillPwdExp(lastPwdChg);
						
					if (daysSinceUpd > 50)
					{
						smDate = getSMinfo(userDN, extPropsMap);
						
						if (smDate != null) //refresh info with latest Siteminder date
						{ 
							lastPwdChg = smDate;
							// loginDaoPW.setLastDtPwdChgd(userId, smDate);
							daysSinceUpd = loginDaoPW.numDaysTillPwdExp(lastPwdChg);
						}
						
						// Defect 1176
						if (daysSinceUpd >= 60)
						{
							// Defect 1176
							message.raiseError("MFI_20", "Your password has expired. Go to Mysite to manage your password."); //UPDT - See SMRO_USR_201 spec for correct msg
						}
						else if (daysSinceUpd > 50)
						{
							int expiresIn = 60 - daysSinceUpd;
							message.showMessage("MFI_20", "Your password expires in " + expiresIn + " day(s). Go to Mysite to manage your password."); //UPDT - See SMRO_USR_201 spec for correct msg						
							
							if(hasValidCertifications(userId)){
								//outOfBoxLogin(userId, supplierId, connectionType, ipAddress, appIniId, appVersion, pcFlag, userType);
								outOfBoxLogin(connectionType, userType);
							}
						}
						else //daysSince < 51 on second SM check)
						{
							if(hasValidCertifications(userId)){
								//outOfBoxLogin(userId, supplierId, connectionType, ipAddress, appIniId, appVersion, pcFlag, userType);
								outOfBoxLogin(connectionType, userType);
							}
						}
					}else{ //daysSince < 51
							if(hasValidCertifications(userId)){
								//outOfBoxLogin(userId, supplierId, connectionType, ipAddress, appIniId, appVersion, pcFlag, userType);
								outOfBoxLogin(connectionType, userType);
							}
					}
					
					// Defect 345
					if (smDate != null) 
					{ 
						loginDaoPW.setLastDtPwdChgd(userId, smDate);
					}
				}else  { 
					//outOfBoxLogin(userId, supplierId, connectionType, ipAddress, appIniId, appVersion, pcFlag, userType);
					outOfBoxLogin(connectionType, userType);
				}
				
			}
			else { 
				//outOfBoxLogin(userId, supplierId, connectionType, ipAddress, appIniId, appVersion, pcFlag, userType);
				outOfBoxLogin(connectionType, userType);
				
			}
			
		} 
		
		else { 
			//outOfBoxLogin(userId, supplierId, connectionType, ipAddress, appIniId, appVersion, pcFlag, userType);
			outOfBoxLogin(connectionType, userType);
		}

		return SoluminaContextHolder.getUserContext().getConnectionId();
	} 
	
	private String getEnvironment()
	{
		String environment;
		Properties sysProp = System.getProperties();

		if (sysProp.getProperty("pw.server.environment") == null) //localhost
		{
			environment = "local";
		}
		else
		{
			environment = sysProp.getProperty("pw.server.environment");			
		}

		return environment;
	}


	public String getSMagentName() 
	{
		String agentName;
		Properties sysprop = System.getProperties();

		if (sysprop.getProperty("pw.server.smAgent") == null)
		{
			agentName = "local"; //localhost
		}
		else
		{
			agentName = sysprop.getProperty("pw.server.smAgent"); //pdlldsolap002
		}

		return agentName;
	}


	@SuppressWarnings("rawtypes")
	public LdapBean getLdapUserInfo(String userName, Map props) 
	{
		LDAPConnection ldapConnect = new LDAPConnection();
		LdapBean ldapUser = new LdapBean();
        String orgRoot = new String(); 
		
		try
		{	
			myHost = (String) props.get("LDAP_HOST");
			myPort = (Number) props.get("LDAP_PORT");
			mgrDN  = (String) props.get("LDAP_USER");
			mgrPW  = StringUtil.decrypt((String) props.get("LDAP_PWD"));
            orgRoot = (String) props.get("ORG_ROOT");

			//connect to LDAP Server
			ldapConnect.connect(myHost, myPort.intValue(), mgrDN, mgrPW);
			
			//connect user to LDAP server
			LDAPSearchResults ldapResults = ldapConnect.search(orgRoot,
															   LDAPConnection.SCOPE_SUB,
															   "(uid=" + userName + ')',
															   ATTRS,
															   false);
			//found user if true
			if (ldapResults.hasMoreElements())
			{
				//next directory entry 
				LDAPEntry entry = ldapResults.next();
				String userDN = entry.getDN();
				ldapUser.setUserDN(userDN);
				
				LDAPAttributeSet findAttrs = entry.getAttributeSet();
				Enumeration<?> enumAttrs = findAttrs.getAttributes();   
				
				//loop on attributes to get user's name 
				while (enumAttrs.hasMoreElements())
				{
					LDAPAttribute anAttr = (LDAPAttribute)enumAttrs.nextElement();	   		   	   		   
					Enumeration<?> enumVals = anAttr.getStringValues();		   		   
					String base = anAttr.getBaseName();

					if (enumVals != null)
					{
						while (enumVals.hasMoreElements())
						{
							String attrValue = (String)enumVals.nextElement();

							if(base.equalsIgnoreCase("cn")) {
								ldapUser.setCn(attrValue);
							} else if (base.equals("pwcitizenship")) {
								ldapUser.setCitzn(attrValue);
							} else if (base.equals("givenName")) {
								ldapUser.setFirstName(attrValue);
							} else if (base.equals("sn")) {
								ldapUser.setLastName(attrValue);
							} else if(base.equalsIgnoreCase("mail")) {
								ldapUser.setEmail(attrValue);
							}
						}
					} //end if(enumVals != null)
				} //end while(hasMoreElements())
			} //end if(hasMoreElements())			
		} //end try statement
		
		catch(LDAPException e)
		{
			System.out.println("LDAPException@catch: " + e.getMessage());			
		}
		
		finally
		{
			//all done, so disconnect
			if ( (ldapConnect != null) && ldapConnect.isConnected() )
			{
				try
				{			    
					ldapConnect.disconnect();
				}
				catch (LDAPException e)
				{
					System.out.println("LDAPException@finally: " + e.toString());
				}
			}
		}
		return ldapUser;
	
	} //end getLdapUserInfo()


	@SuppressWarnings({ "rawtypes", "unused" })
	public Date getSMinfo(String userDN, Map props)
	{
		Date smUserPWlastDate = null;
		String adminUser = new String();
		String adminpwd = new String();
		String agentName = new String();
		String agentSecret = new String();
		String user_dir = new String();
		String org_root = new String();
		String psIP = new String();
		Number psConMin;
		Number psConMax;
		Number psConStep;
		Number psTimeOut;
		Number psAZPort;
		Number psAUPort;
		Number psACPort;
		
		int status = 0;
		AgentAPI aa = new AgentAPI();
		SmApiResult result = new SmApiResult();

		agentName =   (String) props.get("AGENT_NAME");
        agentSecret = StringUtil.decrypt((String) props.get("AGENT_SECRET"));
        user_dir =	  (String) props.get("USER_DIR");
        org_root =	  (String) props.get("ORG_ROOT");
        psIP = 		  (String) props.get("PS_IP");
		psConMin = 	  (Number) props.get("PS_CONMIN");
		psConMax =	  (Number) props.get("PS_CONMAX");
		psConStep =   (Number) props.get("PS_CONSTEP");
		psTimeOut =   (Number) props.get("PS_TIMEOUT");
		psAZPort = 	  (Number) props.get("PS_AZPORT");
		psAUPort = 	  (Number) props.get("PS_AUPORT");
		psACPort = 	  (Number) props.get("PS_ACPORT");
        
		System.out.println("LoginImplPW.getSMinfo(): will make call to SiteMinder now - aa.init()");
		System.out.println("LoginImplPW.getSMinfo(): agentName = " + agentName);
		System.out.println("LoginImplPW.getSMinfo(): agentSecret = " + agentSecret);
		System.out.println("LoginImplPW.getSMinfo(): user_dir = " + user_dir);
		System.out.println("LoginImplPW.getSMinfo(): org_root = " + org_root);
		System.out.println("LoginImplPW.getSMinfo(): PS_IP = " + psIP);
		System.out.println("LoginImplPW.getSMinfo(): PS_CONMIN = " + psConMin);
		System.out.println("LoginImplPW.getSMinfo(): PS_CONMAX = " + psConMax);
		System.out.println("LoginImplPW.getSMinfo(): PS_CONSTEP = " + psConStep);
		System.out.println("LoginImplPW.getSMinfo(): PS_TIMEOUT = " + psTimeOut);
		System.out.println("LoginImplPW.getSMinfo(): PS_AZPORT = " + psAZPort);
		System.out.println("LoginImplPW.getSMinfo(): PS_AUPORT = " + psAUPort);
		System.out.println("LoginImplPW.getSMinfo(): PS_ACPORT = " + psACPort);

        ServerDef sd = new ServerDef();
        sd.serverIpAddress = psIP;
        sd.connectionMin = psConMin.intValue();
        sd.connectionMax = psConMax.intValue();
        sd.connectionStep = psConStep.intValue();
        sd.timeout = psTimeOut.intValue();
        sd.authorizationPort = psAZPort.intValue();
        sd.authenticationPort = psAUPort.intValue();
        sd.accountingPort = psACPort.intValue();
        
        InitDef init = new InitDef(agentName, agentSecret, false, sd);
        
		status = aa.init(init);
		
		if (status != AgentAPI.SUCCESS)
		{
			System.out.println("LoginImplPW Failed trying to connect Agent...");            
		} 
		else 
		{
			System.out.println("LoginImplPW Login Agent Successfully...");      
			SmApiSession session = new SmApiSession (new SmApiConnection (aa));
			
			System.out.println("LoginImplPW.getSMinfo(): session set up now");

			adminpwd = StringUtil.decrypt((String) props.get("ADMIN_PWD"));
			adminUser = (String) props.get("ADMIN_USRNAME");
						
			System.out.println("LoginImplPW.getSMinfo(): admin pwd = " + adminpwd);
			System.out.println("LoginImplPW.getSMinfo(): admin user = " + adminUser);

			try
			{
				 result = session.login(adminUser, adminpwd, InetAddress.getLocalHost(), 0);
				 
				 System.out.println("LoginImplPW.getSMinfo(): Login to Policy Server Successfully..." + "---" + result);
				 // Create a DMS API object from a valid session
		         SmDmsApi dmsApi = new SmDmsApiImpl (session);
		         System.out.println("LoginImplPW.getSMinfo(): create SmDmsApi successfully...");  
		            
		         // Create the directory context object.
		         SmDmsDirectoryContext dirContext = new SmDmsDirectoryContext();
		         System.out.println("LoginImplPW.getSMinfo(): Directory Context Status:...OK");
		         
		         // Create user directory  
		         SmUserDirectory userDir = new SmUserDirectory();
		             
		         // setOid() method can take the name of the user directory
		         userDir.setOid(user_dir);
  
		         // userDir.setOid(org_root);
		         System.out.println("LoginImplPW.getSMinfo(): after userDir.setOid(" + user_dir +")" );
		                
		         // This call returns directory information thru dirContext
		         System.out.println("LoginImplPW.getSMinfo(): This call returns directory information thru dirContext...");
		         result = dmsApi.getDirectoryContext(userDir, new SmDmsConfig(), dirContext); 
		         System.out.println("LoginImplPW.getSMinfo(): Directory Information: " + result.toString());
		                  
		         // dirContext contains a valid agent API connection and administrator session spec.
		         if (dirContext != null) 
		         {     
		        	  System.out.println("LoginImplPW.getSMinfo(): dirContext not null:   " + dirContext.toString());
				      System.out.println("LoginImplPW.getSMinfo(): Creating SmDmsDirectory..." + result.toString());
				      SmDmsDirectory dmsDirectory = dirContext.getDmsDirectory();
				      System.out.println("LoginImplPW.getSMinfo(): SmDmsDirectory created...OK");
				      
				      System.out.println("LoginImplPW.getSMinfo: Creating SmDmsOrganization..." + org_root);
				      SmDmsOrganization dmsOrg = dmsDirectory.newOrganization(org_root);
				      System.out.println("LoginImplPW.getSMinfo(): after dmsOrg = dmsDir.newOrganization(org_root) org_root= "+ org_root);
				      
				      result = dmsOrg.getObject(); 
				     
				      if (result.isSuccess()) 
				      {
				    	  System.out.println("LoginImplPW.getSMinfo(): SmDmsOrganization created and dmsOrg.getObject result...OK: " + result.toString());
				    	  //get user's lastPWchangeDate
				    	   
				    	  SmDmsUser smUser = dmsOrg.newUser(userDN); //should be DN
				    	  //newUser requires DN - which is like utcuniqueid = 8028783, ou = utcworker, o=utc.com
				    	  System.out.println("LoginImplPW.getSMinfo(): after smUser=dmsOrg.newUser(userDN) where userDN=" + userDN);
				    	  
				    	  SmDmsUserPWState smPWState = new SmDmsUserPWState();
				    	  System.out.println("LoginImplPW.getSMinfo(): after smPWState = new smDmsUserPWState()");
				    	  
				    	  result = smUser.getUserPWState(smPWState);
				    	  System.out.println("LoginImplPW.getSMinfo(): after smUser.getUserPWState(smPWState) result :" + result.toString());
				    	  
				    	  if (result.isSuccess())
				    	  {
				    		smUserPWlastDate = smPWState.getLastPWChangeTime(); //smUser.getUserPWState(smPWState); 
				    		System.out.println("LoginImplPW.getSMinfo(): SmUser.getUserPWState works! result = "+ result.toString());
				    	  } 
				    	  else
				    	  {
				    		  System.out.println("LoginImplPW.getSMinfo(): SmUser.getUserPWState did not work. result = " + result.toString());
				    	  }
				      } 
				      else 
				      {
				    	  System.out.println("LoginImplPW.getSMinfo(): dmsOrg.getObject result not success..." + result.toString());
				      }
		         } //end if(dirContext != null)
		         
		         else
		         {
		        	 System.out.println("LoginImplPW.getSMinfo(): SmDmsOrganization was null..." );
		         }   
			} //end try statement
			catch(Exception e)
			{
				System.out.println("LoginImplPW.getSMinfo(): Failed to authenticate Admin. Exception = " + e.toString());
			}
		} //end else [AgentAPI status is success]
		
		return smUserPWlastDate;
		
	} //end getSMinfo()

	
	private boolean hasValidCertifications(String userId)
	{
		boolean isApproved = true;
		boolean certCheck = loginDaoPW.globalConfigObjEnabled("CERTIFICATION_CHECK");
		
		//Check if user falls into any of the following roles:
		//SF$MECHANIC, SF$NEW_MECHANIC, SF$BYPASS_MECHANIC, SF$INSPECTOR
		if (certCheck && loginDaoPW.hasMechanicOrInspectorRole(userId))
		{
			String eyeExamComplete = loginDaoPW.validEyeExam(userId);
			String FODcomplete = loginDaoPW.validFODCertification(userId);

			if("N".equalsIgnoreCase(eyeExamComplete) || "N".equalsIgnoreCase(FODcomplete))
			{
				isApproved = false;
				message.showMessage("MFI_20", "This clock number " + userId + " is deactivated in Solumina.\n" +
									"FOD Course Complete: (" + FODcomplete + ").\n" +
									"Eye Exam Complete: (" + eyeExamComplete + ").\n" +
									"Please have your supervisor contact Solumina Support.");
				this.Super.logout();
			}
		}
		
		return isApproved;
	}

	public void outOfBoxLogin(String connectionType, String userType)
	{	
		this.Super.logout();

		if (connectionType.equalsIgnoreCase("FAT"))
		{
			connectionType = MT_FAT;
		}
		
		this.Super.setUp(false, connectionType, userType);
	}

	// defect 777
	public void userLoginLockCheck(String userId)
    {
		try
		{
			String validUserLoginAttemptLimit = globalConfigurationDao.selectParameterValue(FOUNDATION,	USER_LOGIN_FAILED_LIMIT);
			int userLoginAttemptLimit = toInt(validUserLoginAttemptLimit);
			@SuppressWarnings("rawtypes")
			Map userDetails =  loginDao.selectUserDetails(userId);

			Number userAttempt = (Number) userDetails.get("USER_LOGIN_ATTEMPT");

			if(userAttempt.intValue() > userLoginAttemptLimit)
			{
				// User %V1 is locked, Please contact Solumina Administrator
				message.raiseError("MFI_1A003B6B509048D684EE5F52DCD1BE10", userId);
			}
		}
		catch(EmptyResultDataAccessException e)
		{
			
		}
	}	

     // defect 777
    @SuppressWarnings("deprecation")
	public void saveLoginAttempt( String connectionId,
								  String userId,
								  String loginStatus,
								  String failureReason,
								  String ipAddress,
								  String applicationIniId,
								  String applicationVersion){

    	if(isEmpty(connectionId)){
    		connectionId =  ContextUtil.getUser().getContext().getConnectionId();
    	}    

		// Insert/Update User Login Attempt Details.
		sffndUserClass.updateUserLoginAttemptDetail(connectionId, 
							                        userId, 
							                        loginStatus, 
							                        substring(failureReason, 0, 250), 
							                        ContextUtil.getUser().getContext().getConnectionType(), 
							                        ipAddress,
							                        applicationIniId,
							                        applicationVersion, 
							                        INTEGER_ONE,
							                        "Fail"); 
		
		Number actualUserAttempt = INTEGER_ZERO;
    	try	{
    		actualUserAttempt =  loginDao.selectUserLoginAttempt(userId);
    	}
    	catch(EmptyResultDataAccessException e)	{
    	} 
		
    	if (actualUserAttempt.intValue() > 3){
    		
    		loginDaoPW.updateUserLoginAttempt(userId, 0);

    		try {
    			Runtime.getRuntime().exec("TASKKILL /IM sf32.exe /f");
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }
    
	/*
	public void outOfBoxLogin(String userId,
					         String supplierId,
					         String connectionType,
					         String ipAddress,
					         String appIniId,
					         String appVersion,
					         String pcFlag,
					         String userType)
	{	
		this.Super.logout();
		this.Super.loginUser(userId, supplierId, connectionType, ipAddress, appIniId, appVersion, pcFlag, userType);
	}
	*/

} //end LoginImplPW Class
