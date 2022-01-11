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
*  File:    UserDaoPW.java
* 
*  Created: 2017-09-28
* 
*  Author:  R. Cannon
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------		-------------------------------------------------------------------
* 2017-09-28	R. Cannon			Initial Release SMRO_USR_201
* 2017-10-17    R. Thorpe           Added methods for eACP SMRO_EXPT_204
* 2018-03-21    R. Thorpe           Moved class from Planning to Interfaces moved SMRO_USR_201 related method 
* 2018-03-22    R. Thorpe			SMRO_MD_M206 added method selectWorkLocBySubsystemGroup 
*/

package com.pw.solumina.interfaces.dao;

import java.util.Date;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.security.context.ContextUtil;

public class UserDaoPW
{
	@Reference private static ExtensionDaoSupport eds;
	
	public boolean checkIfUserExists(String userId)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT USERID ")
				                                   .append("   FROM SFFND_USER ")
				                                   .append("  WHERE USERID = ? ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(userId);

		boolean userExists;
		
		try
		{
			eds.queryForString(selectSql.toString(), params);
			
			userExists = true;
		}
		catch(Exception e)
		{
			userExists = false;
		}
		
		return userExists;
	}
	
	public boolean checkIfUserObsolete(String userId)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT OBSOLETE_RECORD_FLAG ")
				                                   .append("   FROM SFFND_USER ")
				                                   .append("  WHERE USERID               = ? ")
				                                   .append("    AND OBSOLETE_RECORD_FLAG = 'Y' ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(userId);

		boolean userObsolete;
		
		try
		{
			eds.queryForString(selectSql.toString(), params);
			
			userObsolete = true;
		}
		catch(Exception e)
		{
			userObsolete = false;
		}
		
		return userObsolete;
	}
	public boolean checkIfUserExistsAndNotObsolete(String userId)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT USERID ")
				                                   .append("   FROM SFFND_USER ")
				                                   .append("  WHERE USERID = ? ")
				                                   .append(" AND OBSOLETE_RECORD_FLAG = 'N'");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(userId);

		boolean userExists;
		
		try
		{
			eds.queryForString(selectSql.toString(), params);
			
			userExists = true;
		}
		catch(Exception e)
		{
			userExists = false;
		}
		
		return userExists;
	}
	
	public void insertUserSecurityGroupEACP(String securityGroup, String ucfUserVch8, String userId)
	{
		String effectiveEndDate;
		StringBuffer insertSql = new StringBuffer().append(" INSERT INTO ")
				                                   .append(" SFFND_USER_SEC_GRP ")
				                                   .append("    (USERID, ")
				                                   .append("    SECURITY_GROUP, ")
				                                   .append("    UPDT_USERID, ")
				                                   .append("    TIME_STAMP, ")
				                                   .append("    LAST_ACTION, ")
				                                   .append("    DEFAULT_ON_CREATE_FLAG,")
				                                   .append("    EFFECTIVE_END_DATE) ")                               
				                                   .append(" VALUES ")
				                                   .append("    ( ?, ?, ?, SYSDATE, ?, 'N', TO_DATE('99990101', 'yyyy/mm/dd')) ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(userId);
		params.addParameter(securityGroup);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(SoluminaConstants.INSERTED);
		
		eds.insert(insertSql.toString(), params);
	}
	
	public int updateActiveUserSecurityGroupEACP(String ucfUserInfo1,
			                                     String ucfUserVch6,
			                                     String ucfUserVch7,
			                                     String ucfUserVch8, 
			                                     String userId)
	{
		int rowCount = 0;
		StringBuffer updateSQL = new StringBuffer().append("UPDATE SFFND_USER_SEC_GRP")
				                                   .append("   SET SECURITY_GROUP         = ?, ")
				                                   .append("	   UPDT_USERID            = ?, ")
				                                   .append("	   TIME_STAMP             = SYSDATE, ")
				                                   .append("	   LAST_ACTION            = ?, ")
				                                   .append("	   DEFAULT_ON_CREATE_FLAG = 'N', ")
				                                   .append("	   EFFECTIVE_END_DATE     = TO_DATE('99990101', 'yyyy/mm/dd') ")
				                                   .append(" WHERE USERID = ?")
				                                   .append("   AND SECURITY_GROUP = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(ucfUserVch7);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(SoluminaConstants.UPDATED);
		params.addParameter(userId);
		params.addParameter(ucfUserVch7);

		rowCount = eds.update(updateSQL.toString(), params);

		return rowCount;
	}

	public int updateInactiveUserSecurityGroupEACP(String ucfUserInfo1,
			                                       String ucfUserVch6,
			                                       String ucfUserVch7,
			                                       String ucfUserVch8, 
			                                       String userId)
	{
		int rowCount = 0;
		StringBuffer updateSQL = new StringBuffer().append("UPDATE SFFND_USER_SEC_GRP")
				                                   .append("   SET SECURITY_GROUP         = ?, ")
				                                   .append("	   UPDT_USERID            = ?, ")
				                                   .append("	   TIME_STAMP             = SYSDATE, ")
				                                   .append("	   LAST_ACTION            = ?, ")
				                                   .append("	   DEFAULT_ON_CREATE_FLAG = 'N', ")
				                                   .append("	   EFFECTIVE_END_DATE     = SYSDATE ")
				                                   .append(" WHERE USERID = ?")
				                                   .append("   AND SECURITY_GROUP = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(ucfUserVch7);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(SoluminaConstants.UPDATED);
		params.addParameter(userId);
		params.addParameter(ucfUserVch7);

		rowCount = eds.update(updateSQL.toString(), params);

		return rowCount;
	}

	public int updateUserIDM(String lastName,
			String firstName,
			String middleName,
			String fullName,
			String nameSuffix,
			String shift,
			String payrollId,
			String obsoleteRecordFlag,
			String ucfUserInfo1,
			String ucfUserVch6,
			String ucfUserVch7,
			String userId)
	{
		int rowCount = 0;

		StringBuffer updateSQL = new StringBuffer().append("UPDATE SFFND_USER ")
			                 	                   .append("   SET LAST_NAME            = ?, ")
			                 	                   .append("	   FIRST_NAME           = ?, ")
			                 	                   .append("	   MIDDLE_NAME          = ?, ")
			                 	                   .append("	   FULL_NAME            = ?, ")
			                 	                   .append("	   NAME_SUFFIX          = ?, ")
			                 	                   .append("	   SHIFT                = DECODE(?,'I',SHIFT,?), ")
			                 	                   .append("	   PAYROLL_ID           = ?, ")
			                 	                   .append("	   UPDT_USERID          = ?, ")
			                 	                   .append("	   TIME_STAMP           = SYSDATE ")
			                 	                   .append(",	   LAST_ACTION          = ? , ")
			                 	                   .append("	   OBSOLETE_RECORD_FLAG = ?, ")
			                 	                   .append("	   UCF_USER_VCH1        = ?, ")
			                 	                   .append("	   UCF_USER_VCH6        = ?, ")
			                 	                   .append("	   UCF_USER_VCH7        = ? ")
			                 	                   .append(" WHERE USERID = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(lastName);
		params.addParameter(firstName);
		params.addParameter(middleName);
		params.addParameter(fullName);
		params.addParameter(nameSuffix);
		params.addParameter(shift);
		params.addParameter(shift);
		params.addParameter(payrollId);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(SoluminaConstants.UPDATED);
		params.addParameter(obsoleteRecordFlag);
		params.addParameter(ucfUserInfo1);
		params.addParameter(ucfUserVch6);
		params.addParameter(ucfUserVch7);
		params.addParameter(userId);

		rowCount = eds.update(updateSQL.toString(), params);

		return rowCount;
	}
	
	public static boolean doesSecurityGroupExist(String securityGroup)
	{		
		StringBuffer selectSql = new StringBuffer().append("SELECT COUNT(*) ")
				                                   .append(" FROM SFFND_SECURITY_GROUP_DEF A ")
				                                   .append(" WHERE SECURITY_GROUP = ? ");
				                                 

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(securityGroup);
		    
		int count = eds.queryForInt(selectSql.toString(), parameters);
		
		if(count > 0)
			return true;
		else
			return false;
	}

	public static boolean doesUserSecurityGroupExist(String userId, String securityGroup)
	{		
		StringBuffer selectSql = new StringBuffer().append("SELECT COUNT(*) ")
				                                   .append(" FROM SFFND_USER_SEC_GRP A ")
				                                   .append(" WHERE USERID = ? ")
				                                   .append(" AND SECURITY_GROUP = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(userId);
		parameters.addParameter(securityGroup);
		    
		int count = eds.queryForInt(selectSql.toString(), parameters);
		
		if(count > 0)
			return true;
		else
			return false;
	}
	
	public String getDefaultSecurityGroup(String workLocation)
	{		
		String securityGroup = null;
		
		StringBuffer selectSql = new StringBuffer().append("SELECT SECURITY_GROUP ")
				                                   .append(" FROM UTASGI_USER_SEC_GRP_XREF ")
				                                   .append(" WHERE HR_LOC = ? ");
		
		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(workLocation);
		
		try
		{
			securityGroup = eds.queryForString(selectSql.toString(), parameters);
		}
		catch(Exception e)
		{
			securityGroup = "NONE" ;
		}
		
		return securityGroup;  
	}
	
	public String getUserPayrollId(String userIdToModify)
	{		
		String payrollId = null;
		
		StringBuffer selectSql = new StringBuffer().append("SELECT PAYROLL_ID ")
				                                   .append("  FROM SFFND_USER ")
				                                   .append(" WHERE USERID = ? ");

		ParameterHolder parameters = new ParameterHolder();
		parameters.addParameter(userIdToModify);

		payrollId = eds.queryForString(selectSql.toString(), parameters);
		
		return payrollId;  
	}


	public void deleteDefaultSecurityGroupByUser(String userid)   
	{
		StringBuffer deleteSql = new StringBuffer().append("DELETE FROM SFFND_USER_SEC_GRP")
				                                   .append(" WHERE USERID = ? ")
				                                   .append("   AND SECURITY_GROUP IN ")
				                                   .append(" (SELECT DISTINCT SECURITY_GROUP FROM UTASGI_USER_SEC_GRP_XREF)");


		ParameterHolder params = new ParameterHolder();
		params.addParameter(userid);

		eds.delete(deleteSql.toString(), params);
	}

	public int updateUser(String lastName,
			              String firstName,
			              String middleName,
			              String fullName,
			              String nameSuffix,
			              String locationID,
			              String departmentID,
			              String centerID,
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
			              String userId,
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
			              String includeAllSuppliers)
	{
		int rowCount = 0;

		StringBuffer updateSQL = new StringBuffer().append("UPDATE SFFND_USER ")
				                                   .append("   SET  ")
				                                   .append("	   LOCATION_ID = ?, ")
				                                   .append("	   DEPARTMENT_ID = ?, ")
				                                   .append("	   CENTER_ID = ?, ")
				                                   .append("	   UPDT_USERID = ?, ")
				                                   .append("	   TIME_STAMP = SYSDATE, ")
				                                   .append("	   LAST_ACTION = ?, ")
				                                   .append("	   UCF_USER_VCH2 = ?, ")
				                                   .append("	   UCF_USER_VCH3 = ?, ")
				                                   .append("	   UCF_USER_VCH4 = ?, ")
				                                   .append("	   USER_CLASS = ?, ")
				                                   .append("	   EMAIL_ADDRESS = ?, ")
				                                   .append("       USER_TYPE = ?, ")
				                                   .append("	   UCF_USER_VCH5 = ?, ")
				                                   .append("	   UCF_USER_VCH8 = ?, ")
				                                   .append("	   UCF_USER_VCH9 = ?, ")
				                                   .append("	   UCF_USER_VCH10 = ?, ")
				                                   .append("	   UCF_USER_VCH11 = ?, ")
				                                   .append("	   UCF_USER_VCH12 = ?, ")
				                                   .append("	   UCF_USER_VCH13 = ?, ")
				                                   .append("	   UCF_USER_VCH14 = ?, ")
				                                   .append("	   UCF_USER_VCH15 = ?, ")
				                                   .append("	   UCF_USER_NUM1 = ?, ")
				                                   .append("	   UCF_USER_NUM2 = ?, ")
				                                   .append("	   UCF_USER_NUM3 = ?, ")
				                                   .append("	   UCF_USER_NUM4 = ?, ")
				                                   .append("	   UCF_USER_NUM5 = ?, ")
				                                   .append("	   UCF_USER_DATE1 = ?, ")
				                                   .append("	   UCF_USER_DATE2 = ?, ")
				                                   .append("	   UCF_USER_DATE3 = ?, ")
				                                   .append("	   UCF_USER_DATE4 = ?, ")
				                                   .append("	   UCF_USER_DATE5 = ?, ")
				                                   .append("	   UCF_USER_FLAG1 = ?, ")
				                                   .append("	   UCF_USER_FLAG2 = ?, ")
				                                   .append("	   UCF_USER_FLAG3 = ?, ")
				                                   .append("	   UCF_USER_FLAG4 = ?, ")
				                                   .append("	   UCF_USER_FLAG5 = ?, ")
				                                   .append("	   INCLUDE_ALL_SUPPLIERS = ? ")
				                                   .append(" WHERE USERID = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(locationID);
		params.addParameter(departmentID);
		params.addParameter(centerID);
		params.addParameter(ContextUtil.getUsername());
		params.addParameter(SoluminaConstants.UPDATED);
		params.addParameter(ucfUserInfo2);
		params.addParameter(ucfUserInfo3);
		params.addParameter(ucfUserInfo4);
		params.addParameter(userClass);
		params.addParameter(emailAddress);
		params.addParameter(userType);
		params.addParameter(ucfUserVch5);
		params.addParameter(ucfUserVch8);
		params.addParameter(ucfUserVch9);
		params.addParameter(ucfUserVch10);
		params.addParameter(ucfUserVch11);
		params.addParameter(ucfUserVch12);
		params.addParameter(ucfUserVch13);
		params.addParameter(ucfUserVch14);
		params.addParameter(ucfUserVch15);
		params.addParameter(ucfUserNum1);
		params.addParameter(ucfUserNum2);
		params.addParameter(ucfUserNum3);
		params.addParameter(ucfUserNum4);
		params.addParameter(ucfUserNum5);
		params.addParameter(ucfUserDate1);
		params.addParameter(ucfUserDate2);
		params.addParameter(ucfUserDate3);
		params.addParameter(ucfUserDate4);
		params.addParameter(ucfUserDate5);
		params.addParameter(ucfUserFlag1);
		params.addParameter(ucfUserFlag2);
		params.addParameter(ucfUserFlag3);
		params.addParameter(ucfUserFlag4);
		params.addParameter(ucfUserFlag5);
		params.addParameter(includeAllSuppliers);
		params.addParameter(userId);

		rowCount = eds.update(updateSQL.toString(), params);

		return rowCount;
	}
	
	//SMRO_MD_M206
	public String selectWorkLocBySubsystemGroup(String subsystemGroup)
	{
		StringBuffer selectSql = new StringBuffer().append(" SELECT WORK_LOC ")
			                                       .append("   FROM SFFND_WORK_LOC_DEF ")
			                                       .append("  WHERE UCF_WORK_LOC_VCH3 = ? ");
	
		ParameterHolder params = new ParameterHolder();
		params.addParameter(subsystemGroup);

		String 	workLoc = eds.queryForString(selectSql.toString(), params);
	
		return workLoc;
	} 

} //end UserDaoPW Class
