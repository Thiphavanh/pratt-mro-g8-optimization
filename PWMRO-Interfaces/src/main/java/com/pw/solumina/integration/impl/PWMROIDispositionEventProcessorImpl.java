package com.pw.solumina.integration.impl;

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
*  File:    PWMROIDispositionEventProcessorImpl.java
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
*/
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.xmlbeans.XmlObject;
import org.springframework.util.CollectionUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.event.SoluminaApplicationEvent;
import com.ibaset.solumina.integration.common.ActionCode;
import com.ibaset.solumina.integration.common.BaseEventProcessor;
import com.ibaset.solumina.integration.common.MappingAction;
import com.ibaset.solumina.integration.logging.MessageLoggerParams;
import com.ibaset.solumina.oagis.ZSMI714DISPOSITIONDocument;
import com.ibaset.solumina.oagis.ZSMI714DISPOSITIONDocument.ZSMI714DISPOSITION;
import com.ibaset.solumina.oagis.ZSMI714DISPOSITIONDocument.ZSMI714DISPOSITION.CNTROLAREA;
import com.ibaset.solumina.sffnd.application.IDateUtils;
import com.pw.solumina.domain.PwmroiSerial;
import com.pw.solumina.event.PWSDispositionEvent;
import com.pw.solumina.integration.PWMROIDispositionEventProcessor;
import com.pw.solumina.integration.PWMROIDispositionMapper;
import com.pw.solumina.sfwid.dao.IPWMROISerialDao;

import java.util.UUID;

public class PWMROIDispositionEventProcessorImpl extends BaseEventProcessor implements PWMROIDispositionEventProcessor 
{
	private PWMROIDispositionMapper pwmroiDispositionMapper;
	
	@Reference
	private IDateUtils dateUtils = null;
	
	@Reference
    private IPWMROISerialDao pwmroiSerialDao = null;

	@Override
	public XmlObject createBod(SoluminaApplicationEvent event) {
		if(event instanceof PWSDispositionEvent)
		{	
			ZSMI714DISPOSITIONDocument ZSMI714DispositionDocument = ZSMI714DISPOSITIONDocument.Factory.newInstance();
			ZSMI714DISPOSITION  ZSMI714Disposition = ZSMI714DispositionDocument.addNewZSMI714DISPOSITION();
			CNTROLAREA  cntrlArea = ZSMI714Disposition.addNewCNTROLAREA();
			Date date = new Date();
			Calendar calendar = new GregorianCalendar();
			cntrlArea.addNewSENDER();
			cntrlArea.getSENDER().setLOGICALID("SOPUS");
			cntrlArea.getSENDER().setAUTHID("M296860");
			cntrlArea.getSENDER().setTRANSID(UUID.randomUUID().toString().toUpperCase());
			cntrlArea.addNewDATETIME().setQualifier("CREATION");
			cntrlArea.getDATETIME().setYEAR((short) calendar.get(Calendar.YEAR));
			cntrlArea.getDATETIME().setMONTH(((byte) (calendar.get(Calendar.MONTH)+1)));
			cntrlArea.getDATETIME().setDAY((byte) (calendar.get(Calendar.DAY_OF_MONTH)));
			cntrlArea.getDATETIME().setHOUR((byte) (calendar.get(Calendar.HOUR)));
			cntrlArea.getDATETIME().setMINUTE((byte) (calendar.get(Calendar.MINUTE)));
			cntrlArea.getDATETIME().setSECOND((byte) (calendar.get(Calendar.SECOND)));
			cntrlArea.getDATETIME().setSUBSECOND(calendar.get(Calendar.MILLISECOND));
			cntrlArea.getDATETIME().setTIMEZONE((short) date.getTimezoneOffset());
			ZSMI714Disposition.addNewDATAAREA();
			return ZSMI714DispositionDocument;
			
		}
		else
		{
			return null;
		}
	}

	@Override
	public MappingAction[] getMappingActions(SoluminaApplicationEvent event) {
		List<MappingAction> actionList = new ArrayList<MappingAction>();
		if(event instanceof PWSDispositionEvent)
		{
			actionList.add(new MappingAction(ActionCode.ADD, ((PWSDispositionEvent) event).getPwmroiSerial()));
		}
		
		if(CollectionUtils.isEmpty(actionList))
		{
			return null;
		}
		else
		{
			return (MappingAction[]) actionList.toArray(new MappingAction[actionList.size()]);
		}
	}

	@Override
	public void processEvent(MappingAction[] mappingActions, 
							 XmlObject ZSMI714Disposition, 
							 SoluminaApplicationEvent event,
							 MessageLoggerParams messageLoggerParams) {
		
		if(mappingActions == null || mappingActions.length == 0)
		{
			ZSMI714Disposition = null;
		}
		else
		{
			pwmroiDispositionMapper.map(mappingActions, (ZSMI714DISPOSITIONDocument) ZSMI714Disposition);
			ZSMI714DISPOSITIONDocument tempZSMI714Disposition = (ZSMI714DISPOSITIONDocument) ZSMI714Disposition;
			PwmroiSerial pwmroiSerial = (PwmroiSerial) mappingActions[0].getSourceObject();
			messageLoggerParams.setRef1(pwmroiSerial.getOrderNo());
			if(pwmroiSerial.getTransType() == null)
			{
				pwmroiSerialDao.updateTransIDForDelivery(pwmroiSerial.getOrderId(), 
														 pwmroiSerial.getSerialId(), 
														 tempZSMI714Disposition.getZSMI714DISPOSITION().getCNTROLAREA().getSENDER().getTRANSID().toString());
				messageLoggerParams.setRef2("DELIVER");
			}
			else
			{
				pwmroiSerialDao.updateTransIDForScrap(pwmroiSerial.getOrderId(), 
													  pwmroiSerial.getOperKey(), 
													  pwmroiSerial.getSerialId(), 
													  tempZSMI714Disposition.getZSMI714DISPOSITION().getCNTROLAREA().getSENDER().getTRANSID().toString());
				messageLoggerParams.setRef2("SCRAP");
			}
		}
	}

	public PWMROIDispositionMapper getPwmroiDispositionMapper() {
		return pwmroiDispositionMapper;
	}

	public void setPwmroiDispositionMapper(PWMROIDispositionMapper pwmroiDispositionMapper) {
		this.pwmroiDispositionMapper = pwmroiDispositionMapper;
	}	
}
