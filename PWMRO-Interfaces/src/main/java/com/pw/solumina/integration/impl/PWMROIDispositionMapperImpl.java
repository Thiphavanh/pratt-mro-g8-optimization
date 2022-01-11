package com.pw.solumina.integration.impl;
/*
* This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017,2021.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    PWMROIDispositionMapperImpl.java
* 
*  Created: 2020-01-24
* 
*  Author:  X005703
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	 ------------------------------------------------------------------
* 2020-01-24 	D.Sibrian		 Initial creation for update of T314 Interface messaging
* 2021-10-29    J DeNinno		 Defect 2010 T314 message xml change	    
*/

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.solumina.integration.common.MappingAction;
import com.ibaset.solumina.oagis.ZSMI714DISPOSITIONDocument;
import com.ibaset.solumina.oagis.ZSMI714DISPOSITIONDocument.ZSMI714DISPOSITION.DATAAREA;
import com.ibaset.solumina.oagis.ZSMI714DISPOSITIONDocument.ZSMI714DISPOSITION.DATAAREA.AUTHORITYAREA;
import com.ibaset.solumina.oagis.ZSMI714DISPOSITIONDocument.ZSMI714DISPOSITION.DATAAREA.DEFECTAREA;
import com.ibaset.solumina.oagis.ZSMI714DISPOSITIONDocument.ZSMI714DISPOSITION.DATAAREA.ENGINEMANUALAREA;
import com.ibaset.solumina.oagis.ZSMI714DISPOSITIONDocument.ZSMI714DISPOSITION.DATAAREA.SCRAPAREA;
import com.ibaset.solumina.oagis.ZSMI714DISPOSITIONDocument.ZSMI714DISPOSITION.DATAAREA.SERIALNUMBERAREA;
import com.pw.common.dao.CommonDaoPW;
import com.pw.solumina.authority.domain.PwmroiOrderSubjectAuthority;
import com.pw.solumina.domain.PwmroiDefect;
import com.pw.solumina.domain.PwmroiSerial;
import com.pw.solumina.integration.PWMROIDispositionMapper;

public class PWMROIDispositionMapperImpl implements PWMROIDispositionMapper{
	@Reference private CommonDaoPW commonDaoPW;
	
	@Override
	public void map(MappingAction[] outboundActions, ZSMI714DISPOSITIONDocument ZSMI714DispositionDocument) {
		if(outboundActions.length > 0)
		{
			PwmroiSerial pwmroiSerial = (PwmroiSerial) outboundActions[0].getSourceObject();
			mapDataArea(ZSMI714DispositionDocument,pwmroiSerial);
		}
	}
	
	protected void mapDataArea(ZSMI714DISPOSITIONDocument ZSMI714DispositionDocument,PwmroiSerial pwmroiSerial) {
		DATAAREA dataArea = ZSMI714DispositionDocument.getZSMI714DISPOSITION().getDATAAREA();
		dataArea.setSALESORDERNUMBER(pwmroiSerial.getSalesOrderNo());
		dataArea.setSMWORKORDER(pwmroiSerial.getOrderNo());
		dataArea.setDELUSERID(pwmroiSerial.getUserId());
		dataArea.setDELDATESTAMP(pwmroiSerial.getDate());
		dataArea.setDELTIMESTAMP(pwmroiSerial.getTime());
		dataArea.setCONDITION(pwmroiSerial.getCondition());
		String[] manual = pwmroiSerial.getEngineManual();
		String[] manualRevNo = pwmroiSerial.getEngineManualRevNo();
		if (manual !=null)
		{		
			if(!StringUtils.equals(manual[0], "N/A" ) && manual !=null)
			{			
				ENGINEMANUALAREA engineManualArea = dataArea.addNewENGINEMANUALAREA();
				for(int i = 0; i < manual.length ; i++)  //defect 2010
			  
					//engineManualArea.addENGINEMANUAL(manual[i]); defect 2010 comment out
					engineManualArea.addENGINEMANUAL(commonDaoPW.getEngineManualInfo(manual[i],manualRevNo[i], commonDaoPW.getOrderIdOrderNo(pwmroiSerial.getOrderNo())));//defect 2010
					
				} 
		
		}	
		String[] serialNo = pwmroiSerial.getSerialNo();
		SERIALNUMBERAREA serialNumberArea = dataArea.addNewSERIALNUMBERAREA();
		for(int i = 0; i < serialNo.length ; i++)
		{
			serialNumberArea.addSERIALNUMBER(serialNo[i]);
		}
		
		List<PwmroiOrderSubjectAuthority> list = pwmroiSerial.getPwmroiOrderSubjectAuthorityList();
		Iterator<PwmroiOrderSubjectAuthority> it = list.iterator();
		while(it.hasNext())
		{
			PwmroiOrderSubjectAuthority pwmroiOrderSubjectAuthority = it.next();
			AUTHORITYAREA authorityArea = dataArea.addNewAUTHORITYAREA();
			authorityArea.setPLAN(pwmroiOrderSubjectAuthority.getPlanPartNo());
			if(pwmroiOrderSubjectAuthority.getSubjectNo() != null)
			{
				authorityArea.setSUBJECT(pwmroiOrderSubjectAuthority.getSubjectNo().toString());
			}
			else
			{
				authorityArea.setSUBJECT(null);
			}
			authorityArea.setSECTIONS(pwmroiOrderSubjectAuthority.getManualSelection());
			authorityArea.setAUTHTYPE(pwmroiOrderSubjectAuthority.getAuthorityType());
			authorityArea.setAUTHTYPEDETAIL(pwmroiOrderSubjectAuthority.getAuthorityDetail());
			authorityArea.setAUTHREV(pwmroiOrderSubjectAuthority.getAuthorityRev());
			if(pwmroiOrderSubjectAuthority.getAuthorityRevDate() != null)
			{
				authorityArea.setAUTHDATE(pwmroiOrderSubjectAuthority.getAuthorityRevDate().toString());
			}
			else
			{
				authorityArea.setAUTHDATE(null);
			}
			authorityArea.setSBPARTNUMBER(pwmroiOrderSubjectAuthority.getSbPartNo());
			authorityArea.setAUTHDETAIL(pwmroiOrderSubjectAuthority.getAuthority());
		}
		if(pwmroiSerial.getTransType() != null)
		{
			SCRAPAREA scrapArea = dataArea.addNewSCRAPAREA();
			if(pwmroiSerial.getQuantity() != null)
			{
				scrapArea.setQUANTITY(pwmroiSerial.getQuantity().toString());
			}
			else
			{
				scrapArea.setQUANTITY(null);
			}
			
			scrapArea.setTRANSTYPE(pwmroiSerial.getTransType());
			scrapArea.setREVERSALSAPID(null);
			scrapArea.setSENTTOGROUPCAT(pwmroiSerial.getGroupCat());
			scrapArea.setSENTTOGROUPCODE(pwmroiSerial.getGroupCode());
			scrapArea.setCAUSECAT(pwmroiSerial.getCauseCat());
			scrapArea.setCAUSECODE(pwmroiSerial.getCauseCode());
			scrapArea.setNONREPAIRTYPE(pwmroiSerial.getNonRepairType());
			scrapArea.setPWOWNEDEXCHPT(null);
			scrapArea.setZCOMSALESORDER(pwmroiSerial.getZcomSalesOrder());
			scrapArea.setDESCRIPTION(pwmroiSerial.getZcomSaleOrderText());

			List<PwmroiDefect> listDefect = pwmroiSerial.getPwmroiDefect();
			Iterator<PwmroiDefect> itDefect = listDefect.iterator();
			while(itDefect.hasNext())
			{
				PwmroiDefect pwmroiDefect = itDefect.next();
				DEFECTAREA defectArea = dataArea.addNewDEFECTAREA();
				defectArea.setDEFECTCAT(pwmroiDefect.getDefectCat());
				defectArea.setDEFECTCODE(pwmroiDefect.getDefectCode());
				defectArea.setDEFECTPRIMARY(pwmroiDefect.getDefectPrimary());
			}
		}		
	}

}
