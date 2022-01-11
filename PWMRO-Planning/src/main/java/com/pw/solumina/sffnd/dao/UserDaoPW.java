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
* 2018-03-21    R. Thorpe			Removed eACP related methods
* 2018-04-16	R. Thorpe           moved getLaborFlag(String) method from the UserDAoPW class to the Execution project.
*  2020-11-19	S. Edgerly			SMRO_WOE_206 - Defect 941 - SCR001: Split Data collections into two different privs.
*/
package com.pw.solumina.sffnd.dao;

import java.util.Date;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.sql.ParameterHolder;

public class UserDaoPW
{
	@Reference private static ExtensionDaoSupport eds;
	
	public void updateUserDefUcfs(String userId,
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
		StringBuffer update = new StringBuffer().append(" UPDATE SFFND_USER ")
												.append("    SET UCF_USER_VCH1      = ?, ")
												.append(" 		 UCF_USER_VCH2      = ?, ")
												.append(" 		 UCF_USER_VCH3      = ?, ")
												.append(" 		 UCF_USER_VCH4      = ?, ")
												.append(" 		 UCF_USER_VCH5      = ?, ")
												.append(" 		 UCF_USER_VCH6      = ?, ")
												.append(" 		 UCF_USER_VCH7      = ?, ")
												.append(" 		 UCF_USER_VCH8      = ?, ")
												.append(" 		 UCF_USER_VCH9      = ?, ")
												.append(" 		 UCF_USER_VCH10     = ?, ")
												.append(" 		 UCF_USER_VCH11     = ?, ")
												.append(" 		 UCF_USER_VCH12     = ?, ")
												.append(" 		 UCF_USER_VCH13     = ?, ")
												.append(" 		 UCF_USER_VCH14     = ?, ")
												.append(" 		 UCF_USER_VCH15     = ?, ")
												.append(" 		 UCF_USER_NUM1      = ?, ")
												.append(" 		 UCF_USER_NUM2      = ?, ")
												.append(" 		 UCF_USER_NUM3      = ?, ")
												.append(" 		 UCF_USER_NUM4      = ?, ")
												.append(" 		 UCF_USER_NUM5      = ?, ")
												.append(" 		 UCF_USER_DATE1     = ?, ")
												.append(" 		 UCF_USER_DATE2     = ?, ")
												.append(" 		 UCF_USER_DATE3     = ?, ")
												.append(" 		 UCF_USER_DATE4     = ?, ")
												.append(" 		 UCF_USER_DATE5     = ?, ")
												.append(" 		 UCF_USER_FLAG1     = ?, ")
												.append(" 		 UCF_USER_FLAG2     = ?, ")
												.append(" 		 UCF_USER_FLAG3     = ?, ")
												.append(" 		 UCF_USER_FLAG4     = ?, ")
												.append(" 		 UCF_USER_FLAG5     = ?, ")
												.append(" 		 UCF_USER_VCH255_1  = ?, ")
												.append(" 		 UCF_USER_VCH255_2  = ?, ")
												.append(" 		 UCF_USER_VCH255_3  = ?, ")
												.append(" 		 UCF_USER_VCH4000_1 = ?, ")
												.append(" 		 UCF_USER_VCH4000_2 = ? ")
												.append("  WHERE USERID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(ucf_user_vch1);
		params.addParameter(ucf_user_vch2);
		params.addParameter(ucf_user_vch3);
		params.addParameter(ucf_user_vch4);
		params.addParameter(ucf_user_vch5);
		params.addParameter(ucf_user_vch6);
		params.addParameter(ucf_user_vch7);
		params.addParameter(ucf_user_vch8);
		params.addParameter(ucf_user_vch9);
		params.addParameter(ucf_user_vch10);
		params.addParameter(ucf_user_vch11);
		params.addParameter(ucf_user_vch12);
		params.addParameter(ucf_user_vch13);
		params.addParameter(ucf_user_vch14);
		params.addParameter(ucf_user_vch15);
		params.addParameter(ucf_user_num1);
		params.addParameter(ucf_user_num2);
		params.addParameter(ucf_user_num3);
		params.addParameter(ucf_user_num4);
		params.addParameter(ucf_user_num5);
		params.addParameter(ucf_user_date1);
		params.addParameter(ucf_user_date2);
		params.addParameter(ucf_user_date3);
		params.addParameter(ucf_user_date4);
		params.addParameter(ucf_user_date5);
		params.addParameter(ucf_user_flag1);
		params.addParameter(ucf_user_flag2);
		params.addParameter(ucf_user_flag3);
		params.addParameter(ucf_user_flag4);
		params.addParameter(ucf_user_flag5);
		params.addParameter(ucf_user_vch255_1);
		params.addParameter(ucf_user_vch255_2);
		params.addParameter(ucf_user_vch255_3);
		params.addParameter(ucf_user_vch4000_1);
		params.addParameter(ucf_user_vch4000_2);
		params.addParameter(userId);
		
		eds.update(update.toString(), params);
	}
	
    //Defect 941
    public boolean hasPriv(String userId, String priv){
    	
        StringBuffer selectSql = new StringBuffer().append(" SELECT ")
                								   .append("   SFMFG.SFCORE_HAS_PRIV(?, ?) ")
                								   .append(" AS ")
                								   .append("   V_PRIV ")
                								   .append(" FROM  ")
                								   .append("   DUAL");
        
        ParameterHolder parameters = new ParameterHolder();
        parameters.addParameter(userId);
        parameters.addParameter(priv);
        
        String hasPrivFlag = eds.queryForString(selectSql.toString(), parameters);
        
        boolean result = false;
        
        if("Y".equalsIgnoreCase(hasPrivFlag)){
        	result = true;
        }
        
    	return result;
    }
    // End defect 941

}