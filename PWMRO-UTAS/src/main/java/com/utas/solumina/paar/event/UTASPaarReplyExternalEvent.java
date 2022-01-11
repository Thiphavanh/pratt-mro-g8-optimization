package com.utas.solumina.paar.event;

import com.ibaset.common.event.SoluminaApplicationEvent;
import com.utas.solumina.paar.domain.SolPaar;
import com.utas.solumina.paar.domain.SolPaarExternal;

public class UTASPaarReplyExternalEvent extends SoluminaApplicationEvent 
{			
	private static final long serialVersionUID = -7186348263750360451L;
	private SolPaarExternal solPaarExternal;

	public SolPaarExternal getSolPaarExternal() 
	{
		return solPaarExternal;
	}

	public void addSolPaar(SolPaarExternal solPaarExternal) 
	{
		this.solPaarExternal = solPaarExternal;
	}

	public UTASPaarReplyExternalEvent(Object source) 
	{
		super(source);
	}
}
