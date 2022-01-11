package com.utas.solumina.sfwid.application.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.event.SoluminaEventPublisher;
import com.ibaset.solumina.sfcore.application.IMessage;

import com.utas.solumina.paar.dao.PaarRequestDao;
import com.utas.solumina.paar.domain.SolPaarWO;
import com.utas.solumina.paar.event.PaarWORequestEvent;
import com.utas.solumina.paar.integration.PaarRequestApplication;
import com.utas.solumina.sfwid.application.IUtasOrder;

public class UtasOrderImpl implements IUtasOrder
{
	@Reference
	IMessage message;

	@Reference
	PaarRequestApplication paarRequestApplication;
	@Reference
	SoluminaEventPublisher soluminaEventPublisher;
	@Reference
	PaarRequestDao paarRequestDao;

	
	protected final Log logger = LogFactory.getLog(UtasOrderImpl.class);


	@Override
	public void sendPaarWORequest(String requestId)
	{
		if(StringUtils.isEmpty(requestId))
		{
			message.raiseError("MFI_20","Request Id can not be null ");
		}
		SolPaarWO solPaarWO = paarRequestApplication.buildSolPaarWO(requestId);
		
		PaarWORequestEvent paarWORequestEvent = new PaarWORequestEvent(this);
		
		paarWORequestEvent.addSolPaarWO(solPaarWO);
		
		soluminaEventPublisher.publishEvent(paarWORequestEvent);
		
		paarRequestDao.updatePaarWORequestStatus(requestId, SoluminaConstants.IN_REVIEW.toUpperCase(), null);
				
	}
	
}
