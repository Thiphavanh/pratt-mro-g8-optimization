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
*  File:    MessageImplPW.java
* 
*  Created: 2018-03-27
* 
*  Author:  XCS4331
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2018-03-27 	XCS4331		    Initial Release XXXXXXX
* 2019-10-24    Fred Ettefagh   changed lunchMroOverHaulFlag to pwOrderlunchFlag
* 2020-04-08    John DeNinno	defect 1615 suppress message MFI_55188267F0894F44AA43B7255B29C9B5
* 2020-08-23    Fred Ettefagh   Defect 1753 - Added code to implement PW rules on accepting buyoffs (N/A, N/D, Partial and Accept) 
* 2021-03-26    John DeNinno    Defect 1839 - suppress message MFI_14AB23A67B7E6369E0440003BA560E35
*/

package com.pw.solumina.sfcore.application.impl;


import org.apache.commons.lang.StringUtils;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.solumina.sfcore.application.IMessage;


public abstract class MessageImplPW extends ImplementationOf<com.ibaset.solumina.sfcore.application.IMessage> implements IMessage{

	public void showMessage(String messageId, String s1, String s2, String s3, String s4) {
		
		String pwOrderlunchFlag = (String)ContextUtil.getUser().getContext().get("LAUNCH_ORDER");
		String pwSkipOptionalDataCollectionsMSG = (String)ContextUtil.getUser().getContext().get("SKIP_Optional_Data_Collections_MSG");
		
		
		if ("MFI_52341".equalsIgnoreCase(messageId) && !"FALSE".equalsIgnoreCase(pwOrderlunchFlag)){
			if ("MFI_14AB23A67B7E6369E0440003BA560E35".equalsIgnoreCase(messageId) && !StringUtils.equalsIgnoreCase(pwSkipOptionalDataCollectionsMSG, "TRUE")){
				Super.showMessage(messageId, s1, s2, s3, s4);
			}
		}
    }
	
	//defect 1615 begin
	@Override
	public void raiseError(String messageId, String s1, String s2) {
		
		if ("MFI_55188267F0894F44AA43B7255B29C9B5".equalsIgnoreCase(messageId) 
		|| (StringUtils.equals("MFI_2D89A4C24A134C82A1999BF245A7E1E7",messageId))) {  //defect 1839
			//do nothing - ignore error
		}
		else
		{
			Super.raiseError(messageId, s1, s2);
		}
		
	}
	//defect 1615 end

	@Override
	public String buildMessage(String errorMessage, String errorMessageId) {

        if (!StringUtils.equals(errorMessageId, "MFI_5BEFDFC60ECB4008BBABB05453B41381") && (!StringUtils.equals(errorMessageId, "MFI_51901")))
        {
		   return Super.buildMessage(errorMessage, errorMessageId);
        }
        else {
        	return "";
        }
		
	}
	
	
	
}



