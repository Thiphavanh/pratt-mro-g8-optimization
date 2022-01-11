package com.utas.solumina.paar.integration.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.xmlbeans.XmlObject;
import org.openapplications.oagis.x9.ProcessSolPAARDocument;
import org.openapplications.oagis.x9.ProcessSolPAARType;

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
import com.utas.solumina.paar.domain.SolPaar;
import com.utas.solumina.paar.domain.SolPaarWO;
import com.utas.solumina.paar.event.PaarRequestEvent;
import com.utas.solumina.paar.event.PaarWORequestEvent;
import com.utas.solumina.paar.integration.PaarMapper;
import com.utas.solumina.paar.integration.PaarRequestEventProcessor;

public class PaarRequestEventProcessorImpl extends BaseEventProcessor implements PaarRequestEventProcessor 
{
	private PaarMapper paarMapper;
	
	private ILicense license;
	
	@Reference  private OAGIUtils oagiUtils = null;  // G8R2SP4

	@Override
	public XmlObject createBod(SoluminaApplicationEvent event) 
	{
		if(event instanceof PaarRequestEvent)
		{
			ProcessSolPAARDocument processSolPAARDocument = ProcessSolPAARDocument.Factory.newInstance();
			
			ProcessSolPAARType processSolPAARType = processSolPAARDocument.addNewProcessSolPAAR();
			
			processSolPAARType.setReleaseID(oagiUtils.RELEASE_ID);

        	oagiUtils.initalizeApplicationArea(processSolPAARType.addNewApplicationArea(),
					   						   oagiUtils.LOGICAL_ID+SoluminaConstants.UNDERSCORE+license.getLicensePrefix(),
					   						   "Planning", //componentId,
					   						   "PAAR Request", //taskId,
					   						   java.util.UUID.randomUUID().toString(),
					   						   "Never", //StringUtils.defaultIfEmpty(confirmationType,oagiUtils.DEFAULT_CONFIRMATION_TYPE),
					   						   ContextUtil.getUsername(),
					   						   java.util.UUID.randomUUID().toString().toUpperCase(),
					   						   new Date());
			
			processSolPAARType.addNewDataArea();
			processSolPAARType.getDataArea().addNewProcess().addNewActionCriteria();
			
			return processSolPAARDocument;
		}
		else if(event instanceof PaarWORequestEvent)
		{
			ProcessSolPAARDocument processSolPAARDocument = ProcessSolPAARDocument.Factory.newInstance();
			
			ProcessSolPAARType processSolPAARType = processSolPAARDocument.addNewProcessSolPAAR();
			
			processSolPAARType.setReleaseID(oagiUtils.RELEASE_ID);

        	oagiUtils.initalizeApplicationArea(processSolPAARType.addNewApplicationArea(),
					   						   oagiUtils.LOGICAL_ID+SoluminaConstants.UNDERSCORE+license.getLicensePrefix(),
					   						   "Execution", //componentId,
					   						   "PAAR Request", //taskId,
					   						   java.util.UUID.randomUUID().toString(),
					   						   "Always", //StringUtils.defaultIfEmpty(confirmationType,oagiUtils.DEFAULT_CONFIRMATION_TYPE),
					   						   ContextUtil.getUsername(),
					   						   java.util.UUID.randomUUID().toString().toUpperCase(),
					   						   new Date());
			
			processSolPAARType.addNewDataArea();
			processSolPAARType.getDataArea().addNewProcess().addNewActionCriteria();
			
			return processSolPAARDocument;
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
		if(event instanceof PaarRequestEvent)
		{
			actionList.add(new MappingAction(ActionCode.REPLACE, ((PaarRequestEvent) event).getSolPaar()));
		}
		else if(event instanceof PaarWORequestEvent)
		{
			actionList.add(new MappingAction(ActionCode.REPLACE, ((PaarWORequestEvent) event).getSolPaarWO()));
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
							 XmlObject processSolPAARDocument, 
							 SoluminaApplicationEvent event,
							 MessageLoggerParams messageLoggerParams) 
	{
		if (mappingActions == null || mappingActions.length == 0)
        {
			processSolPAARDocument = null;
        }
        else
        {
        	SolPaar solPaar = null;
        	SolPaarWO solPaarWO = null;
        	
        	if(event instanceof PaarRequestEvent)
        	{
        		solPaar = (SolPaar) mappingActions[0].getSourceObject();
        		
        		paarMapper.map(mappingActions, (ProcessSolPAARDocument) processSolPAARDocument);
        		
        		if (solPaar != null) 
            	{
    				messageLoggerParams.setRef1(solPaar.getPermissionDesc().getRequestId());
    				messageLoggerParams.setRef2(solPaar.getPermissionDesc().getRequestUserId());
    				messageLoggerParams.setRef3(solPaar.getPermissionDesc().getPriorRequestId());
    			}
        	}
        	else if(event instanceof PaarWORequestEvent)
        	{
        		solPaarWO = (SolPaarWO) mappingActions[0].getSourceObject();
        		
        		paarMapper.map(mappingActions, (ProcessSolPAARDocument) processSolPAARDocument);
        		
        		if (solPaarWO != null) 
            	{
    				messageLoggerParams.setRef1(solPaarWO.getRequestId());
    				messageLoggerParams.setRef2(solPaarWO.getRequestUserId());
    				messageLoggerParams.setRef3(solPaarWO.getPriorRequestId());
    			}
        	}
        }
	}
	
	public PaarMapper getPaarMapper() 
	{
		return paarMapper;
	}

	public void setPaarMapper(PaarMapper paarMapper) 
	{
		this.paarMapper = paarMapper;
	}
	
	public ILicense getLicense() 
	{
		return license;
	}

	public void setLicense(ILicense license) 
	{
		this.license = license;
	}
}
