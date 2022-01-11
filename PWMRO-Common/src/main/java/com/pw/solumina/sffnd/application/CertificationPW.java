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
*  File:    CertificationPW.java
* 
*  Created: 2018-03-29
* 
*  Author:  c079222
* 
*  Revision History
* 
*  Date      	Who             Description
*  ----------	---------------	------------------------------------------------------------
* 2018-03-29    D.Miron         SMRO_USR_205 Defect 144 Initial Release
* 2018-06-19	B. Preston		SMRO_USR_205 Defect 503 - Added updateCertification.
*/

package com.pw.solumina.sffnd.application;

import java.util.List;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.ICertification;
import com.ibaset.solumina.sffnd.certification.ICertificationDao;
import com.pw.solumina.sffnd.dao.CertificationDaoPW;

public class CertificationPW 
{
	@Reference 
	private ICertification certification = null;
	
	@Reference 
	private CertificationDaoPW certDaoPW = null;
	
	@Reference
    private ICertificationDao certificationDao = null;
	
	@Reference
	private IMessage message = null;
	
	public void insertCertification(String key,
			String certType,
			String description,
			String queueType,
			String queueId,
			String workLocation)
	{
		if (!certDaoPW.checkCertDefExists(key))
		{
			certification.insertCertification(key, certType, description, queueType, queueId);
		}

		if (!certDaoPW.checkWorkLocationCerts(workLocation, key))
		{
			certDaoPW.insertWorkLocationCerts(workLocation, key, key);
			certDaoPW.updateCertUpdtUseridTimeStamp(key);
		}
		else
		{
			message.raiseError("MFI_20", "CERT already exists for work location" );
		}
	}

	public void deleteCertification(String key, String workLocation)
	{
		List list = certDaoPW.selectGeneralLookupCert(workLocation, key);

		if(list.size() <= 1)
		{
			certification.deleteCertification(key);
		}

		if(certDaoPW.checkWorkLocationCerts(workLocation, key))
		{
			certDaoPW.deleteWorkLocationCert(key, workLocation);
		}
	}
	
	// Begin defect 503
	public void updateCertification(String key,
			String certType,
			String description,
			String queueType,
			String queueId,
			String obsoleteFlag,
			String workLocation) {
		
		certification.updateCertification(key, certType, description, queueType, queueId, obsoleteFlag);
		
		certDaoPW.updateWorkLocation(key, workLocation);	
		
	}
	// End defect 503
	
}

