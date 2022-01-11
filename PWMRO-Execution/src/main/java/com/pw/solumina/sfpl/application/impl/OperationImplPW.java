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
 *  File:    OperationImplPW.java
 * 
 *  Created: 2020-11-12
 * 
 *  Author:  Scott Edgerly
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2020-11-12		S. Edgerly	    Initial Release PW_SMRO_WOE _08 - Defect 1811 - Supplemental Orders
 *  2021-02-17		B. Polak		defect 1852 - moved from com.pw.solumina.sfwid.application.impl to com.pw.solumina.sfpl.application.impl
*/

package com.pw.solumina.sfpl.application.impl;

import com.ibaset.common.Reference;
import com.ibaset.solumina.sfpl.application.IOperation;
import com.pw.solumina.sfwid.dao.OperationDaoPW;

public class OperationImplPW {
	@Reference private OperationDaoPW operationDaoPW;
	@Reference private IOperation iOperation;
	
	public void preCopyOperationFromWid(String fromOrderId,
			String fromOperationNumber,
			String toPlanId,
			Number toPlanVersion,
			Number toPlanRevision,
			Number toPlanAlterations,
			String toOperationNumber,
			String partsCopyFlag,
			String toOperationTitle,
			String from,
			Number toSequenceNumber){

		iOperation.preCopyOperationFromWid(fromOrderId, fromOperationNumber, 
				toPlanId, toPlanVersion, toPlanRevision, toPlanAlterations, 
				toOperationNumber, partsCopyFlag, toOperationTitle, from, 
				toSequenceNumber);

		//clear out "phantom data"
		operationDaoPW.clearBuyoffUserAndTimestampFromPlan(toPlanId, toOperationNumber);
		}
	}
