package com.utas.solumina.paar.integration.external;

import org.openapplications.oagis.x9.AcknowledgeSolPAARDocument;

import com.ibaset.solumina.integration.common.MappingAction;

public interface IUTASPaarReplyMapper 
{
	public void map(MappingAction[] outboundActions, AcknowledgeSolPAARDocument acknowledgeSolPAARDocument);		

}
