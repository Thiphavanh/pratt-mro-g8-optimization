/**
 * Proprietary and Confidential
 * Copyright 1995-2021 iBASEt, Inc.
 * Unpublished-rights reserved under the Copyright Laws of the United States
 * US Government Procurements:
 * Commercial Software licensed with Restricted Rights.
 * Use, reproduction, or disclosure is subject to restrictions set forth in
 * license agreement and purchase contract.
 * iBASEt, Inc. 27442 Portola Parkway, Suite 300, Foothill Ranch, CA 92610
 *
 * Solumina software may be subject to United States Dept of Commerce Export Controls.
 * Contact iBASEt for specific Expert Control Classification information.
 * 
 *  File:    CircularDependencyLeafImpl1PW.java
 * 
 *  Created: 2021-03-30
 * 
 *  Author:  John DeNinno
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2021-03-30		John DeNinno	Initial Release defect 1839 
 */
package com.pw.solumina.sfwid.application.impl;


import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.solumina.sfwid.application.ISerialOperationOfd;
import com.ibaset.solumina.sfwid.dao.IGroupJobDao;
import com.ibaset.solumina.sfwid.dao.ISerialDao;
import com.ibaset.solumina.sfwid.dao.IBuyoffDao;
import java.util.Map;
import com.ibaset.solumina.sfcore.application.IMessage;



public abstract class CircularDependencyLeafImpl1PW extends ImplementationOf<ISerialOperationOfd> implements ISerialOperationOfd {

	@Reference
	private IGroupJobDao groupJobDao;
	@Reference
	private IBuyoffDao buyoffDao;
	@Reference
	private ISerialDao serialDao;
	@Reference
	private IMessage message;

	@Override
	public void checkBuyoffExecution(String orderId,
			Number operationKey,
			Number stepKey,
			String lotId,
			String serialId,
			String buyoffId,
			String status,
			String buyoffType,
			String buyoffCertification,
			String oldBuyoffId,
			String secondLoginUser,
			String buyoffStatus, 
			String userId,
			String calledFrom, 
			String groupJobNo) {


		System.out.println("checkBuyoffExecution calledFrom - " + calledFrom);

		Map buyoffReferenceAndSequenceNumberMap = buyoffDao.selectBuyoffSerialSequenceAndExecutionControlNumber(orderId,
                operationKey,
                stepKey,
                lotId,
                serialId,
                buyoffId);
		
		String referenceId = (String) buyoffReferenceAndSequenceNumberMap.get("REF_ID");
		
		
		
		boolean isStatusAccept = StringUtils.equalsIgnoreCase(status,
				SoluminaConstants.ACCEPT);

		boolean isStatusReject = StringUtils.equalsIgnoreCase(status,
				SoluminaConstants.REJECT);
		
		boolean isStatusSkip = StringUtils.equalsIgnoreCase(status,
                SoluminaConstants.SKIP);

		boolean isStatusPartial = StringUtils.equalsIgnoreCase(status, 
		SoluminaConstants.PARTIAL.toUpperCase()); 
		
		boolean isStatusInAcceptRejectSkip = isStatusAccept || isStatusReject
                || isStatusSkip;
		
		String serialNumber = serialDao.selectSerialNumber(orderId,
                serialId,
                lotId);
		
		
		if(isStatusInAcceptRejectSkip || isStatusPartial)  // FND-14465  06/04/2010
		{
			
			
			String priorDataCollectionsOk = buyoffDao.selectPriorDataCollectionsOkForBuyoff(orderId,
					operationKey,
					stepKey,
					serialId,
					lotId,
					buyoffId,
					referenceId,
					SoluminaConstants.NO);		
			
			

			if (StringUtils.equals(calledFrom, "GRID") && (StringUtils.equalsIgnoreCase(priorDataCollectionsOk,
					SoluminaConstants. PART)))
			{
				

				message.raiseError("MFI_20","Cannot " + status + " buyoff because of missing part data collection for serial " + serialNumber);
			}

		}

		Super.checkBuyoffExecution(orderId, operationKey, stepKey, lotId, serialId, buyoffId, status, buyoffType, buyoffCertification, oldBuyoffId, secondLoginUser, buyoffStatus, userId, calledFrom, groupJobNo);
	}

	String getBuyoffAcceptAllStatus(String buyoffStatus,
			String acceptAll)
	{
		// ACCEPTALL
		if (buyoffStatus.startsWith(SoluminaConstants.ENABLE.toUpperCase())
				|| StringUtils.equals(buyoffStatus,
						SoluminaConstants.REJECTED.toUpperCase())
				|| StringUtils.equals(buyoffStatus,
						SoluminaConstants.SKIP_BUYOFF_CANBE_ACCEPTED)
				|| StringUtils.equals(buyoffStatus,
						SoluminaConstants.COMPLETE_SKIP_CANBE_ACCEPTED))
		{
			acceptAll = SoluminaConstants.ACCEPTALL.toUpperCase();
		}
		return acceptAll;
	}
	protected String getGroupJobStatus(String groupJobNo)
	{
		//FND-24257
		String groupJobStatus = EMPTY;
		if(isNotEmpty(groupJobNo))
		{	
			groupJobStatus = groupJobDao.selectGroupJobStatus(groupJobNo);
		}
		return groupJobStatus;
	}
}
