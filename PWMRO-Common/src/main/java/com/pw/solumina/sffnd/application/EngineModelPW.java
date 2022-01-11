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
 *  File:    EngineModelPW.java
 * 
 *  Created: 2018-09-07
 * 
 *  Author:  c293011
 * 
 *  Revision History
 * 
 *  Date      	Who                	Description
 *  ----------	---------------	---------------	---------------------------------------------------
 * 2018-09-07 	R. Thorpe		Initial Release Defect 962
 */

package com.pw.solumina.sffnd.application;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.solumina.sfcore.application.IMessage;

import com.pw.solumina.sffnd.dao.EngineModelDaoPW;

public class EngineModelPW 
{

	@Reference private EngineModelDaoPW engineModelDaoPW = null;
	@Reference private IMessage message = null;
	
	public void insertModel(String model, 
			                String modelDescription,
			                String program) 
	{
		// check if model/type exists already
		if (engineModelDaoPW.doesModelTypeExist(model, program))
		{
			message.raiseError("MFI_20", "Engine Model/Type ("+model+"/"+program+") already exists");		
		}
		else
		{
			//insert into custom table PWUST_ENGINE_TYPE_MODEL_DEF
		    engineModelDaoPW.insertModelType(model, modelDescription, program);
		}
	}
	
	public void updateModel(String model,
			                String modelDescription,
			                String program,
			                String origProgram,
			                String obsoleteRecordFlag)
	{

		if (!StringUtils.equals(program, origProgram))
		{
			// check if model/type exists already
			if (engineModelDaoPW.doesModelTypeExist(model, program))
			{
				message.raiseError("MFI_20", "Engine Model/Type ("+model+"/"+program+") already exists");		
			}
		}

		//Update the custom table PWUST_ENGINE_TYPE_MODEL_DEF
		engineModelDaoPW.updateModelType(model, modelDescription, program, origProgram, obsoleteRecordFlag);
	}

	public void deleteModel(String model, String program) 
	{
		//  is model referenced
		if (engineModelDaoPW.isModelReferenced(model, program))		
		{
			engineModelDaoPW.obsoleteModelType(model, program);
			message.showMessage("MFI_53688");  ///Referenced marked obsolete
		}
		else
		{
			engineModelDaoPW.deleteModelType(model, program);
		}

	}

	}

