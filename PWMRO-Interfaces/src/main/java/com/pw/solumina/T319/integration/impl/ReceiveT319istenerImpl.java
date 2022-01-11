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
*  File:    ReceiveT319istenerImpl.java
* 
*  Created: 2019-06-05
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2019-06-04 	Fred Ettefagh       Initial Release for PW_SMRO_TR_319
*/
package com.pw.solumina.T319.integration.impl;

import com.ibaset.common.Reference;
import com.ibaset.solumina.integration.common.ReceiveOAGISReplyListener;
import com.ibaset.solumina.integration.common.ReplyProcessor;

public class ReceiveT319istenerImpl extends ReceiveOAGISReplyListener implements ReceiveT319istener{

	
	@Reference ReceiveT319RelyProcesserImplPW receiveT319RelyProcesserImplPW = null;
	
	@Override
	public String getServiceName() {

		return "PW_S_T319_ORDER_SPLIT";
	}

	@Override
	public ReplyProcessor getReplyProcessor(String messageText) {
		
		return receiveT319RelyProcesserImplPW;
	}

}
