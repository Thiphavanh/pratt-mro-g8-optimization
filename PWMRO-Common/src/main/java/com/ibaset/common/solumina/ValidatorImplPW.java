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
*  File:    ValidatorImplPW.java
* 
*  Created: 2020-04-08
* 
*  Author:  XCS4331
* 
*  Revision History
* 
*  Date      	Who             Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2020-04-08 	Fred Ettefagh   Initial Release SMRO_USR_203 - Defect 468, changed code checked in by T.Phan to replace OOB priv check for order delete from ORDER_DELETE to PW_OH_PARENT_ORDER_DELETE
*/
package com.ibaset.common.solumina;

import org.apache.commons.lang.StringUtils;
import com.ibaset.common.ImplementationOf;

public abstract class ValidatorImplPW extends ImplementationOf<IValidator> implements IValidator {
	
	public void checkUserPrivilege(String privilegeName){
		
		// defect 468
		if (StringUtils.equalsIgnoreCase("ORDER_DELETE", privilegeName)){
			Super.checkUserPrivilege("PW_OH_PARENT_ORDER_DELETE");
		}else{
			Super.checkUserPrivilege(privilegeName);
		}
	}
}

