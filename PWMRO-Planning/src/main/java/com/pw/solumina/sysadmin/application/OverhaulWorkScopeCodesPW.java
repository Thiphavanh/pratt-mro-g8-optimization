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
*  File:    OverhaulWorkScopeCodesPW.java
* 
*  Created: 2017-08-03
* 
*  Author:  c293011
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-08-03 	c293011		    Initial Release SMRO_PLG_205
*/

package com.pw.solumina.sysadmin.application;

import com.ibaset.common.Reference;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.pw.solumina.sysadmin.dao.OverhaulWorkScopeCodesDaoPW;

public class OverhaulWorkScopeCodesPW   
{
	
	@Reference private OverhaulWorkScopeCodesDaoPW workScopeCodesDaoPW;
	@Reference private IMessage message;
	
	  public void insertOverhaulWorkScopeCodes(String workScope, String workScopeTitle, String engineType, String workLoc)
	  {
	    	//does the work scope / eng Type/ work location already exist?
			boolean workScopeExists = workScopeCodesDaoPW.checkIfWorkScopeExists(workScope, engineType, workLoc);
			if (workScopeExists)
			{
				//Error
				message.raiseError("MFI_20","Entry already exists. You must obsolete the existing entry before adding the new one or change this entry so that it is unique");		
			}
			else
			{
				/// i don't need to do these checks becasue i am giving them a drop down list to pick form
//				boolean engineTypeExists = workScopeCodesDaoPW.checkIfEngineTypeExists(engineType);
//				boolean workLocExists = workScopeCodesDaoPW.checkIfWorkLocExists(workLoc);
//				
//				if(!engineTypeExists && !workLocExists)
//				{
//					message.raiseError("MFI_20", engineType+" is Not a Valid Engine Type and "+workLocExists+" is Not a Valid Work Location.  Please correct");
//				}
//				else if(!engineTypeExists)
//				{
//					message.raiseError("MFI_20", engineType+" is Not a Valid Engine Type.  Please correct");
//				} 
//				else if(!workLocExists)
//				{
//					message.raiseError("MFI_20", engineType+" is Not a Valid Engine Type.  Please correct");
//				} 
//				else
//				{
	                //Add the new Work Scope
		            workScopeCodesDaoPW.insertOverhaulWorkScopeCodes(workScope, workScopeTitle, engineType, workLoc);
				//}
			}
	  }

	  public void updateOverhaulWorkScopeCodes(String workScope, String workScopeTitle, String engineType, String workLoc, String obsoleteRecordFlag)
	  {	    	//does the work scope / work location already exist?
			boolean workScopeExists = workScopeCodesDaoPW.checkIfWorkScopeExists(workScope, engineType, workLoc);
			if (workScopeExists)
			{
				workScopeCodesDaoPW.updateOverhaulWorkScopeCodes(workScope, workScopeTitle, engineType, workLoc, obsoleteRecordFlag);		
			}
			else
			{
	            //Add the new Work Scope
				message.showMessage("MFI_20", "Warning: Changing the Work Location or Engine Type of an existing entry may result in a new entry being added to the table. If you require the original entry to be removed, you must do this in a separate Delete transaction" );
		        insertOverhaulWorkScopeCodes(workScope, workScopeTitle, engineType, workLoc);
			}
	  }
	  
	    public void deleteOverhaulWorkScopeCodes(String workScope, String engineType, String workLoc)
	    {
	     // add code
	    //	message.showMessage("MFI_20","In the delete This is a showMessage looking to see what happens if i continue....");
	    	
	  // 	message.raiseError("MFI_20","In the delete this is a raiseError looking to see what happens if i continue....");	
	    	  workScopeCodesDaoPW.deleteOverhaulWorkScopeCodes(workScope, engineType, workLoc);
	    	/// do we just want to obsolete this ?
	    	
	    	//or do we see if it is in use somewhere
	    //	if Yes then obsolete it so it can no longer be used
	    	
	   // 	else
	    	//	delete the row?
	    }

}