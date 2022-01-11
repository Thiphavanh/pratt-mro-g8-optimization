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
*  File:    UserPW.java
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
*/

package com.pw.solumina.sffnd.application;

import java.util.Date;

import com.ibaset.common.Reference;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IUser;
import com.pw.solumina.sffnd.dao.UserDaoPW;

public class UserPW
{
	@Reference private IMessage message;
	@Reference private IUser user;
	@Reference private UserDaoPW userDaoPW;
	
	public void updateUser(String userId,
			  			   String userDef,
			  			   String lastName,
			  			   String firstName,
			  			   String middleName,
			  			   String fullName,
			  			   String nameSuffix,
			  			   String ltaSendFlag,
			  			   String workLoc,
			  			   String workDept,
			  			   String workCenter,
			  			   String shift,
			  			   String payrollId,
			  			   String userClass,
			  			   String emailAddress,
			  			   String userType,
			  			   String includeAllSupp,
			  			   String laborType,
			  			   Date lastEyeExamDate,
			  			   String obsoleteRecordFlag,
			  			   String miUserFlag,
			  			   String ucf_user_vch1,
			  			   String ucf_user_vch2,
			  			   String ucf_user_vch3,
			  			   String ucf_user_vch4,
			  			   String ucf_user_vch5,
			  			   String ucf_user_vch6,
			  			   String ucf_user_vch7,
			  			   String ucf_user_vch8,
			  			   String ucf_user_vch9,
			  			   String ucf_user_vch10, //notes
			  			   String ucf_user_vch11,
			  			   String ucf_user_vch12,
			  			   String ucf_user_vch13,
			  			   String ucf_user_vch14,
			  			   String ucf_user_vch15,
			  			   Number ucf_user_num1,
			  			   Number ucf_user_num2,
			  			   Number ucf_user_num3,
			  			   Number ucf_user_num4,
			  			   Number ucf_user_num5,
			  			   Date ucf_user_date1, //password last changed
			  			   Date ucf_user_date2,
			  			   Date ucf_user_date3,
			  			   Date ucf_user_date4,
			  			   Date ucf_user_date5,
			  			   String ucf_user_flag1,
			  			   String ucf_user_flag2, //user no labor
			  			   String ucf_user_flag3,
			  			   String ucf_user_flag4,
			  			   String ucf_user_flag5,
			  			   String ucf_user_vch255_1,
			  			   String ucf_user_vch255_2,
			  			   String ucf_user_vch255_3,
			  			   String ucf_user_vch4000_1,
			  			   String ucf_user_vch4000_2)
	{
		//OOB SFFND_USER update
		user.updateUserDef(userId,
						   userDef,
						   lastName,
						   firstName,
						   middleName,
						   fullName,
						   nameSuffix,
						   ltaSendFlag,
						   workLoc,
						   workDept,
						   workCenter,
						   shift,
						   payrollId,
						   userClass,
						   emailAddress,
						   userType,
						   includeAllSupp,
						   laborType,
						   lastEyeExamDate,
						   obsoleteRecordFlag,
						   miUserFlag);
		
		userDaoPW.updateUserDefUcfs(userId,
						  			ucf_user_vch1,
						  			ucf_user_vch2,
						  			ucf_user_vch3,
						  			ucf_user_vch4,
						  			ucf_user_vch5,
						  			ucf_user_vch6,
						  			ucf_user_vch7,
						  			ucf_user_vch8,
						  			ucf_user_vch9,
						  			ucf_user_vch10,
						  			ucf_user_vch11,
						  			ucf_user_vch12,
						  			ucf_user_vch13,
						  			ucf_user_vch14,
						  			ucf_user_vch15,
						  			ucf_user_num1,
						  			ucf_user_num2,
						  			ucf_user_num3,
						  			ucf_user_num4,
						  			ucf_user_num5,
						  			ucf_user_date1,
						  			ucf_user_date2,
						  			ucf_user_date3,
						  			ucf_user_date4,
						  			ucf_user_date5,
						  			ucf_user_flag1,
						  			ucf_user_flag2,
						  			ucf_user_flag3,
						  			ucf_user_flag4,
						  			ucf_user_flag5,
						  			ucf_user_vch255_1,
						  			ucf_user_vch255_2,
						  			ucf_user_vch255_3,
						  			ucf_user_vch4000_1,
						  			ucf_user_vch4000_2);
	} //end updateUser()

} //end UserPW Class
