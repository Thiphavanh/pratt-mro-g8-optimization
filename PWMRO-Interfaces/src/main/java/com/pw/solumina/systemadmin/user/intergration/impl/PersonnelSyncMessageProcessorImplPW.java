/*
* This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    PersonnelSyncMessageProcessorImplPW.java
* 
*  Created: 2018-03-21
* 
*  Author:  c293011
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2018-03-21 	R. Thorpe		Initial Release SMRO_MD_M206 
* 2019-03-01    D.Miron         Updated for G8R2SP4
*/

package com.pw.solumina.systemadmin.user.intergration.impl;

import java.util.Map;

import org.apache.xmlbeans.XmlObject;
import org.openapplications.oagis.x9.ActionExpressionType;
import org.openapplications.oagis.x9.IdentifierType;
import org.openapplications.oagis.x9.NameType;
import org.openapplications.oagis.x9.PersonnelType;
import org.openapplications.oagis.x9.SequencedIDsType;
import org.openapplications.oagis.x9.SyncPersonnelDocument;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.solumina.integration.common.MappingAction;
import com.ibaset.solumina.integration.exception.MappingException;
import com.ibaset.solumina.systemadmin.user.integration.PersonnelSyncMessageProcessor;
import com.pw.solumina.interfaces.dao.UserDaoPW;

public abstract class PersonnelSyncMessageProcessorImplPW 
			extends ImplementationOf<PersonnelSyncMessageProcessor> 
			implements PersonnelSyncMessageProcessor
{
	@Reference	private UserDaoPW userDaoPW;	
	
	@Override
	public MappingAction[] getMappingActions(XmlObject inboundBod)
			throws MappingException 
	{
		boolean userExists = false;
		String userId = "";
		String userName = "";
		
		if (inboundBod instanceof SyncPersonnelDocument ) 
		{
			SyncPersonnelDocument pd = (SyncPersonnelDocument) inboundBod;
			PersonnelType personnelType = pd.getSyncPersonnel().getDataArea().getPersonnelArray(0);
			
			userId = personnelType.getIDArray(0).getStringValue();
			userExists = userDaoPW.checkIfUserExists(userId);			
			
			ActionExpressionType actionExp = pd.getSyncPersonnel().getDataArea().getSync().getActionCriteriaArray(0).getActionExpressionArray(0);
			String actionCode = actionExp.getActionCode();
			
			// SIPL_MD_M6-IDM - Determine if this transaction comes from SAP or IDM. 
			IdentifierType idType = pd.getSyncPersonnel().getApplicationArea().getSender().getLogicalID();
	

			if("ADD".equalsIgnoreCase(actionCode))
			{
				// SIPL_MD_M6-IDM begins - For IDM transaction, check for obsolete.  If so, don't change the Action Code.
				if (idType.getStringValue().contains("IDM"))
				{
					if (userExists && (!userDaoPW.checkIfUserObsolete(userId)))
					{
						actionExp.setActionCode("Change");
					}
					else
					{
						actionExp.setActionCode("Add");
					}
				}
				// SIPL_MD_M6-IDM ends
				else
				{
					if (userExists)
					{
						actionExp.setActionCode("Change");
					}
					else
					{
						actionExp.setActionCode("Add");
					}
				}
			}
			// SIPL_MD_M6-IDM begins.
			else
			if ("DISABLE".equalsIgnoreCase(actionCode))  // When IDM sends a Termination, they send it as a Disable. This actionCode is exclusive to IDM.
			{
				// To trigger an Obsolete transaction, we will set the HROrganizationID to the actionCode of Disable
				pd.getSyncPersonnel().getDataArea().getPersonnelArray(0).getHROrganizationIDs().getIDArray(0).setStringValue(actionCode);
				
				// Since 'Disable' is not valid in the Super method, we convert it to 'Change' and indicate an obsolete (TERM) transaction.
				actionExp.setActionCode("Change");

			}
			// SIPL_MD_M6-IDM ends.
		}
		
		return Super.getMappingActions(inboundBod);
	}
}
