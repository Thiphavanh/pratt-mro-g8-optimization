package com.utas.solumina.paar.integration.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.openapplications.oagis.x9.AcknowledgeSolPAARDocument;
import org.openapplications.oagis.x9.SolPAARType;

import com.ibaset.common.SoluminaConstants;
import com.ibaset.solumina.integration.common.ActionCode;
import com.ibaset.solumina.integration.common.BaseMessageProcessor;
import com.ibaset.solumina.integration.common.MappingAction;
import com.ibaset.solumina.integration.exception.MappingException;
import com.ibaset.solumina.integration.exception.ParsingException;
import com.ibaset.solumina.integration.logging.MessageLoggerParams;
import com.ibaset.solumina.integration.logging.ReplyLoggerParams;
import com.ibaset.solumina.integration.logging.impl.MessageLoggerParamsImpl;
import com.utas.solumina.common.UtasConstants;
import com.utas.solumina.paar.dao.PaarRequestDao;
import com.utas.solumina.paar.domain.Permission;
import com.utas.solumina.paar.domain.PermissionDesc;
import com.utas.solumina.paar.domain.SolPaarWO;
import com.utas.solumina.paar.integration.PaarMapper;
import com.utas.solumina.paar.integration.PaarRequestApplication;
import com.utas.solumina.paar.integration.PaarRequestReplyProcessor;

public class PaarRequestReplyProcessorImpl extends BaseMessageProcessor implements PaarRequestReplyProcessor 
{

	private PaarMapper paarMapper;
	private PaarRequestApplication paarRequestApplication;
	private PaarRequestDao paarRequestDao;

	@Override
	public XmlObject parseReply(String inboundReply) throws ParsingException 
	{
		return parseMessage(inboundReply);
	}
	
	@Override
	public SchemaType getInboundBodType() 
	{
		return AcknowledgeSolPAARDocument.type;
	}
	
	@Override
	public MappingAction[] getMappingActions(XmlObject inboundBod) throws MappingException 
	{
		return buildMappingActionFromResponseExpression(inboundBod);
	}

	@Override
	public void processReply(XmlObject inboundReply, ReplyLoggerParams replyLoggerParams) throws Exception 
	{
		// Get mapping actions from Verb section of the incoming reply
        MappingAction[] mappingActions = getMappingActions(inboundReply);
        
        // Process incoming BOD
        // add original message id to the reply logger parameters
        AcknowledgeSolPAARDocument acknowledgeSolPaar = (AcknowledgeSolPAARDocument)inboundReply;
        if ( acknowledgeSolPaar.getAcknowledgeSolPAAR().getDataArea().getAcknowledge()!= null &&
        		acknowledgeSolPaar.getAcknowledgeSolPAAR().getDataArea().getAcknowledge().isSetOriginalApplicationArea() &&
        		acknowledgeSolPaar.getAcknowledgeSolPAAR().getDataArea().getAcknowledge().getOriginalApplicationArea().isSetBODID()) 
        {
            replyLoggerParams.setOriginalMessageID(acknowledgeSolPaar.getAcknowledgeSolPAAR().getDataArea().getAcknowledge()
                                                                  			 .getOriginalApplicationArea().getBODID().getStringValue());
        }

        // Process incoming BOD
        MessageLoggerParams messageLoggerParams = new MessageLoggerParamsImpl(replyLoggerParams.getServiceName());
        processMessage(mappingActions, inboundReply, messageLoggerParams);
	}

	public void validateMappingAction(MappingAction mappingAction) throws UnsupportedOperationException
    {
        XmlObject xmlObject = (XmlObject) mappingAction.getSourceObject();
        ActionCode actionCode = mappingAction.getActionCode();

        if ((xmlObject instanceof SolPAARType) && actionCode.isAccepted())
        {
        }
        else
        {
            throw new UnsupportedOperationException("Action "
                    + actionCode.toString() + " on Object \""
                    + xmlObject.getDomNode().getNodeName()
                    + "\" is not supported.");
        }
    }
	
	@Override
	public void processMessage(MappingAction[] mappingActions, XmlObject inboundBod, MessageLoggerParams messageLoggerParams) throws Exception 
	{
		String requestId = null;
        
        for (int i = 0; i < mappingActions.length; i++)
        {
            XmlObject xmlObject = (XmlObject) mappingActions[i].getSourceObject();
            
            SolPAARType solPAARType = null;
            
            if (xmlObject instanceof SolPAARType)
            {
            	solPAARType = ((SolPAARType) xmlObject);
            }
            
            AcknowledgeSolPAARDocument acknowledgeSolPaar = (AcknowledgeSolPAARDocument)inboundBod;
            String componentId = acknowledgeSolPaar.getAcknowledgeSolPAAR()
            									   .getApplicationArea()
            									   .getSender()
            									   .getComponentID()
            									   .getStringValue();
            
            String authorizationId = StringUtils.EMPTY;
            if(acknowledgeSolPaar.getAcknowledgeSolPAAR().getApplicationArea().getSender().getAuthorizationID()!=null)
            {
            	authorizationId= acknowledgeSolPaar.getAcknowledgeSolPAAR().getApplicationArea()
            											  			       .getSender()
            											  			       .getAuthorizationID()
            											  			       .getStringValue();

            }
			   				  						    
            requestId = solPAARType.getPAARHeader().getDocumentID().getID().getStringValue();
            
            if(StringUtils.isEmpty(requestId))
            {
            	throw new MappingException("Request Id is missing.");
            }
            
            if(StringUtils.equals(componentId, UtasConstants.PLAN_REVIEW))
            {            	            
            	// Select Main Permission Request Object
            	PermissionDesc permissionDesc = paarRequestApplication.selectPermissionDesc(requestId);

            	// Check Request Status
            	if(!StringUtils.equalsIgnoreCase(permissionDesc.getRequestStatus(), SoluminaConstants.IN_REVIEW))
            	{
            		throw new MappingException("Request ID: " + requestId + " is not in IN REVIEW status.");
            	}  

            	paarMapper.validatePlanInfo(permissionDesc, solPAARType);

            	// Map Permission Tags from the XML to List<Permission>
            	List<Permission> permissionList = new ArrayList<Permission>();
            	paarMapper.mapPermissionList(permissionList, solPAARType);

            	// Save Permission List
            	paarRequestApplication.savePermissionList("UTASGI_PLG_PAAR_PMN_AUTH", requestId, permissionList);

            	// Update Request Status to REVIEWED
            	paarRequestDao.updateRequestStatus(requestId, UtasConstants.REVIEWED, authorizationId);

            	//UTAS-465
            	paarRequestApplication.authorizedSecGrpToPlanAndUser(requestId);
            }
            else if(StringUtils.equals(componentId, UtasConstants.ORDER_REVIEW))
            {
            	SolPaarWO solPaarWO = paarRequestApplication.selectPaarWORequestInfo(requestId);
            	
            	// Check Request Status
            	if(!StringUtils.equalsIgnoreCase(solPaarWO.getRequestStatus(), SoluminaConstants.IN_REVIEW))
            	{
            		throw new MappingException("Request ID: " + requestId + " is not in IN REVIEW status.");
            	}  
            	
            	paarMapper.validateOrderInfo(solPaarWO, solPAARType);
            	
            	// Map Permission Tags from the XML to List<Permission>
            	List<Permission> permissionList = new ArrayList<Permission>();
            	paarMapper.mapPermissionList(permissionList, solPAARType);
            	
            	// Save Permission List
            	paarRequestApplication.savePermissionList("UTASGI_WID_PAAR_PMN_AUTH", requestId, permissionList);
            	
            	// Update Request Status to REVIEWED
            	paarRequestDao.updatePaarWORequestStatus(requestId, UtasConstants.REVIEWED, authorizationId);
            	
            	paarRequestApplication.authorizedSecGrpToOrder(requestId);
            }
            
            messageLoggerParams.setRef1(requestId);
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
	
	public PaarRequestApplication getPaarRequestApplication() 
	{
		return paarRequestApplication;
	}

	public void setPaarRequestApplication(PaarRequestApplication paarRequestApplication) 
	{
		this.paarRequestApplication = paarRequestApplication;
	}
	
	public PaarRequestDao getPaarRequestDao() 
	{
		return paarRequestDao;
	}

	public void setPaarRequestDao(PaarRequestDao paarRequestDao) 
	{
		this.paarRequestDao = paarRequestDao;
	}
}
