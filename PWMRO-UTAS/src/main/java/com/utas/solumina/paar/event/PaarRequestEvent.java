package com.utas.solumina.paar.event;

import com.ibaset.common.event.SoluminaApplicationEvent;
import com.utas.solumina.paar.domain.SolPaar;

public class PaarRequestEvent extends SoluminaApplicationEvent 
{
	private static final long serialVersionUID = -9045864103004542582L;
	private SolPaar solPaar;
	
	public SolPaar getSolPaar() 
	{
		return solPaar;
	}

	public void addSolPaar(SolPaar solPaar) 
	{
		this.solPaar = solPaar;
	}
	
	public PaarRequestEvent(Object source) 
	{
		super(source);
	}
}
