package com.utas.solumina.paar.event;

import com.ibaset.common.event.SoluminaApplicationEvent;
import com.utas.solumina.paar.domain.SolPaarWO;

public class PaarWORequestEvent extends SoluminaApplicationEvent 
{

	private static final long serialVersionUID = -6517105684604071061L;
	private SolPaarWO solPaarWO ;
	
	public SolPaarWO getSolPaarWO() 
	{
		return solPaarWO;
	}

	public void addSolPaarWO(SolPaarWO solPaarWO) {
		this.solPaarWO = solPaarWO;
	}



	public PaarWORequestEvent(Object source) 
	{
		super(source);
	}
}
