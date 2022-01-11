package com.utas.solumina.paar.integration.external;

import com.ibaset.solumina.exception.SoluminaApplicationException;
import com.utas.solumina.paar.domain.SolPaarExternal;

public interface IUTASPaarRequestExternalApplication 
{
	public SolPaarExternal[] getPaarReequestInfo(String[] diIdentifiers) throws SoluminaApplicationException;
	
	public void insertSolPaarExternalRequest(SolPaarExternal[] solPaarExternals);
}
