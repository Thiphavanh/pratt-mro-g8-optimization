package com.utas.solumina.paar.integration.external.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlObject;
import org.openapplications.oagis.x9.AcknowledgeSolPAARDocument;
import org.openapplications.oagis.x9.AcknowledgeSolPAARType;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.event.SoluminaApplicationEvent;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.solumina.integration.common.ActionCode;
import com.ibaset.solumina.integration.common.BaseEventProcessor;
import com.ibaset.solumina.integration.common.MappingAction;
import com.ibaset.common.util.OAGIUtils; // G8R2SP4
import com.ibaset.solumina.integration.logging.MessageLoggerParams;
import com.ibaset.solumina.sfcore.application.ILicense;
import com.utas.solumina.common.UtasConstants;
import com.utas.solumina.paar.domain.SolPaarExternal;
import com.utas.solumina.paar.event.UTASPaarReplyExternalEvent;
import com.utas.solumina.paar.integration.external.IUTASPaarReplyExternalEventProcessor;
import com.utas.solumina.paar.integration.external.IUTASPaarReplyMapper;

public class UTASPaarReplyExternalEventProcessorImpl extends BaseEventProcessor implements IUTASPaarReplyExternalEventProcessor
{
	@Reference
	private ILicense license;
	
	@Reference
	private IUTASPaarReplyMapper paarReplyMapper;
	
	@Reference  
	private OAGIUtils oagiUtils = null;  // G8R2SP4
	
	@Override
	public XmlObject createBod(SoluminaApplicationEvent event) 
	{
		if(event instanceof UTASPaarReplyExternalEvent)
		{
			UTASPaarReplyExternalEvent utasPaarReplyEvent = (UTASPaarReplyExternalEvent) event;
			
			AcknowledgeSolPAARDocument acknowledgeSolPAARDocument = AcknowledgeSolPAARDocument.Factory.newInstance();
			
			AcknowledgeSolPAARType acknowledgeSolPAARType = acknowledgeSolPAARDocument.addNewAcknowledgeSolPAAR();
			
			acknowledgeSolPAARType.setReleaseID(oagiUtils.RELEASE_ID);
			
			String componentId = UtasConstants.PLAN_REVIEW;
			if(StringUtils.equals(utasPaarReplyEvent.getSolPaarExternal().getRequestComponentId(), UtasConstants.EXECUTION_PROPER_CASE))
			{
				componentId = UtasConstants.ORDER_REVIEW;
			}

        	oagiUtils.initalizeApplicationArea(acknowledgeSolPAARType.addNewApplicationArea(),
					   						   oagiUtils.LOGICAL_ID+SoluminaConstants.UNDERSCORE+license.getLicensePrefix(),
					   						   componentId,
					   						   "PAAR Reply", //taskId,
					   						   java.util.UUID.randomUUID().toString(),
					   						   "Always", //StringUtils.defaultIfEmpty(confirmationType,.DEFAULT_CONFIRMATION_TYPE),
					   						   ContextUtil.getUsername(),
					   						   java.util.UUID.randomUUID().toString().toUpperCase(),
					   						   new Date());
			        	
        	acknowledgeSolPAARType.addNewDataArea();
        	acknowledgeSolPAARType.getDataArea().addNewAcknowledge().addNewOriginalApplicationArea();
        	
        	acknowledgeSolPAARType.getDataArea()
        						  .getAcknowledge()
        						  .getOriginalApplicationArea()
        						  .xsetCreationDateTime(oagiUtils.dateToDateTimeType(Calendar.getInstance().getTime()));
        	
        	acknowledgeSolPAARType.getDataArea().getAcknowledge().addNewResponseCriteria();   
			
			return acknowledgeSolPAARDocument;
		}
		else
		{
			return null;
		}		
	}

	@Override
	public MappingAction[] getMappingActions(SoluminaApplicationEvent event) 
	{
	
		List<MappingAction> actionList = new ArrayList<MappingAction>();
		if(event instanceof UTASPaarReplyExternalEvent)
		{
			actionList.add(new MappingAction(ActionCode.ACCEPTED, ((UTASPaarReplyExternalEvent) event).getSolPaarExternal()));
		}
		
		if (CollectionUtils.isEmpty(actionList))
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
							 XmlObject acknowledgeSolPAARDocument, 
							 SoluminaApplicationEvent event,
							 MessageLoggerParams messageLoggerParams) 
	{
		if (mappingActions == null || mappingActions.length == 0)
        {
			acknowledgeSolPAARDocument = null;
        }
        else
        {
        	SolPaarExternal solPaarExternal = null;
        	
        	if(event instanceof UTASPaarReplyExternalEvent)
        	{
        		solPaarExternal = (SolPaarExternal) mappingActions[0].getSourceObject();
        		
        		paarReplyMapper.map(mappingActions, (AcknowledgeSolPAARDocument) acknowledgeSolPAARDocument);
        		
        		if (solPaarExternal != null) 
            	{
    				messageLoggerParams.setRef1(solPaarExternal.getRequestId());
    				messageLoggerParams.setRef2(solPaarExternal.getRequestUserId());
    				messageLoggerParams.setRef3(solPaarExternal.getPriorRequestId());
    			}
        	}
        }
		
	}

}
