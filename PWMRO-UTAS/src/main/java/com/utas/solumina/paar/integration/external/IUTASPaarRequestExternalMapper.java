package com.utas.solumina.paar.integration.external;

import org.apache.xmlbeans.XmlObject;

import com.ibaset.solumina.integration.common.MappingAction;
import com.ibaset.solumina.integration.exception.MappingException;
import com.utas.solumina.paar.domain.SolPaarExternal;

public interface IUTASPaarRequestExternalMapper {
	public SolPaarExternal getRequestId(XmlObject xmlObject) throws MappingException;
	
	public SolPaarExternal[] map(MappingAction[] inboundActions, SolPaarExternal destinationSolPaarReq) throws Exception;
}
