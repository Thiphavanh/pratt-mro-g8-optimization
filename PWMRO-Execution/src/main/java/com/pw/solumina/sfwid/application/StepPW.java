package com.pw.solumina.sfwid.application;

/*
 *  This unpublished work, first created in 2020 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2020.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    StepPW.java
 * 
 *  Created: 2020-12-02
 * 
 *  Author:  Scott Edgerly
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2020-12-02		S.Edgerly	    Initial Release - SMRO_WOE_308 - Defect 1819 - Supplemental Orders
*/

import com.ibaset.common.Reference;
import com.ibaset.solumina.sfwid.application.IStep;
import com.pw.solumina.sfwid.dao.StepDaoPW;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class StepPW {
	@Reference IStep iStep;
	@Reference StepDaoPW stepDaoPW;
    public void copyStepPre(String fromOrderNumber,
            String fromOperationNumber,
            String fromStepNumber,
            String toOrderNumber,
            String toOperationNumber,
            String toStepNumber,
            String toStepTitle,
            String copyPl,
            Number toExecutionOrder){
    	
    	iStep.copyStepPre(fromOrderNumber, fromOperationNumber, fromStepNumber, toOrderNumber, toOperationNumber, toStepNumber, toStepTitle, copyPl, toExecutionOrder);
		
    	List list = stepDaoPW.getOrderInfo(toOrderNumber);
    	ListIterator listIterator = list.listIterator();
    	
    	String toOrderId = "";
    	while(listIterator.hasNext()){
    		Map map = (Map) listIterator.next();
    		toOrderId = (String) map.get("ORDER_ID");
    	}
    	
    	//clear phantom data here
    	stepDaoPW.clearCopyStepsExtraneousData(toOrderId, toOperationNumber, toStepNumber);
    	
	}
}
