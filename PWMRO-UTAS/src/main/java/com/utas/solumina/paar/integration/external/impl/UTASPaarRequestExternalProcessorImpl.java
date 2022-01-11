package com.utas.solumina.paar.integration.external.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.openapplications.oagis.x9.ProcessSolPAARDocument;
import org.openapplications.oagis.x9.SolPAARType;

import com.ibaset.common.Reference;
import com.ibaset.solumina.integration.common.ActionCode;
import com.ibaset.solumina.integration.common.BaseMessageProcessor;
import com.ibaset.solumina.integration.common.MappingAction;
import com.ibaset.solumina.integration.exception.MappingException;
import com.ibaset.solumina.integration.logging.MessageLoggerParams;
import com.utas.solumina.paar.domain.SolPaarExternal;
import com.utas.solumina.paar.integration.external.IUTASPaarRequestExternalApplication;
import com.utas.solumina.paar.integration.external.IUTASPaarRequestExternalMapper;
import com.utas.solumina.paar.integration.external.IUTASPaarRequestExternalProcessor;

public class UTASPaarRequestExternalProcessorImpl extends BaseMessageProcessor implements IUTASPaarRequestExternalProcessor 
{
	@Reference
	private IUTASPaarRequestExternalMapper paarRequestExternalMapper;

	@Reference
	private IUTASPaarRequestExternalApplication paarRequestExternalApplication;

	@Override
	public SchemaType getInboundBodType() 
	{	
		return ProcessSolPAARDocument.type;
	}

	@Override
	protected void validateMappingAction(MappingAction mappingAction) throws UnsupportedOperationException 
	{
		// TODO Auto-generated method stub
		XmlObject xmlObject = (XmlObject) mappingAction.getSourceObject();
		ActionCode actionCode = mappingAction.getActionCode();
		
		if ((xmlObject instanceof SolPAARType) && actionCode.isReplace())
		{
		}
		else
		{
			throw new UnsupportedOperationException("Action "
					+ actionCode.toString() + " on Object \""
					+ xmlObject.getDomNode().getNodeName()
					+ "\" is not supported.");
			//Action %V1 on Object "%V2" is not supported
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Map buildActionMap(MappingAction[] mappingActions)
			throws MappingException {
		Map actionMap = new HashMap();

		for (int i = 0; i < mappingActions.length; i++)
		{
			XmlObject xmlObject = (XmlObject) mappingActions[i].getSourceObject();

			SolPaarExternal solPaarExternal = paarRequestExternalMapper.getRequestId(xmlObject);

			if (!actionMap.containsKey(solPaarExternal))
			{
				actionMap.put(solPaarExternal.getRequestId(), new ArrayList());
			}

			// add the new action code to action map
			((List) actionMap.get(solPaarExternal.getRequestId())).add(mappingActions[i]);
		}
		return actionMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Map buildNounMap(Set objectIdSet) 
	{
		Map nounMap = new HashMap();
		SolPaarExternal[] solPaarExtDetails = paarRequestExternalApplication.getPaarReequestInfo((String[]) objectIdSet.toArray((new String[objectIdSet.size()])));

		for (int i = 0; i < solPaarExtDetails.length; i++)
		{
			nounMap.put(solPaarExtDetails[i].getRequestId(), solPaarExtDetails[i]);
		}
		return nounMap;		
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void doProcessing(Map actionMap, 
								Map nounMap,
								XmlObject inboundBOD, 
								MessageLoggerParams messageLoggerParams) throws Exception 
	{
		SolPaarExternal[] solPaarExtArray = null;
		
		SolPaarExternal solPaarRequestExternal=null;

		Set solPaarRequestIdList = actionMap.keySet();
		for (Iterator iterator = solPaarRequestIdList.iterator(); iterator.hasNext();)
		{
			Object id = (Object) iterator.next();
			solPaarRequestExternal = (SolPaarExternal) nounMap.get(id);
			List actionList = (List) actionMap.get(id);
						
			solPaarExtArray = paarRequestExternalMapper.map((MappingAction[]) actionList.toArray(new MappingAction[actionList.size()]),solPaarRequestExternal);		
		}
		paarRequestExternalApplication.insertSolPaarExternalRequest(solPaarExtArray);

		messageLoggerParams.setStatus("PROCESS SUCCESS");
	}
}
