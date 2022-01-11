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
 *  File:    AssembleTextImplPW.java
 * 
 *  Created: 2017-08-29
 * 
 *  Author:  Rusty Cannon
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2017-08-29		R. Cannon		Initial Release SMRO_EXPT_205 - assembleText method created
 *  2017-10-13      D. Miron        SMRO_EXPT_201 - Added export markings for plan, various new methods
 */

package com.pw.solumina.sfpl.application.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.solumina.sfpl.application.IAssembleText;
//import com.pw.solumina.sfpl.dao.AssembleTextDaoPW;
import com.pw.solumina.sfcore.application.impl.AssembleTextIUtilImpl;
import com.pw.solumina.sfcore.dao.AssembleTextDaoPW;


public abstract class AssembleTextImplPW extends ImplementationOf<IAssembleText> implements IAssembleText
{
	@Reference private AssembleTextDaoPW assembleTextDaoPW;
	
	@Reference private AssembleTextIUtilImpl assembleTextIUtilImpl;
	
    @SuppressWarnings("rawtypes")
	public Map assembleText(String planLevel,
				            String planId,
				            Number planVersion,
				            Number planRevision,
				            Number planAlterations,
				            String operNo,
				            String stepNo,
				            String editMode,
				            String blockType,
				            String currentNavLevelFieldName,
				            String calledFrom){
    	
    	Map planTextMap = null;
    	
    	planTextMap = this.Super.assembleText(planLevel,
									 	   planId,
									 	   planVersion,
									 	   planRevision,
									 	   planAlterations,
									 	   operNo,
									 	   stepNo,
									 	   editMode,
									 	   blockType,
									 	   currentNavLevelFieldName,
									 	   calledFrom);
        	

        	
    	if ((operNo == null || operNo.isEmpty()) &&  (stepNo == null || stepNo.isEmpty())){
    		planTextMap = assembleTextIUtilImpl.buildPlanTextHashMap(planTextMap, planId, planVersion, planRevision, planAlterations, calledFrom);
    	}
    	
    	return planTextMap;
    }
} //end AssembleTextImplPW Class
