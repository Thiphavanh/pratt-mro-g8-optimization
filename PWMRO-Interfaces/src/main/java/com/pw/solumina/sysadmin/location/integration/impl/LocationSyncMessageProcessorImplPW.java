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
*  File:    LocationSyncMessageProcessorImplPW.java
* 
*  Created: 2017-10-03
* 
*  Author:  C293011
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-10-03 	293011		    Initial Release SMRO_MD_M207 - Work Center Interface
* 2019-03-01    D.Miron         Updated for G8R2SP4
*/

package com.pw.solumina.sysadmin.location.integration.impl;

import org.apache.commons.logging.Log;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openapplications.oagis.x9.ActionExpressionType;
import org.openapplications.oagis.x9.IdentifierType;
import org.openapplications.oagis.x9.LocationType;
import org.openapplications.oagis.x9.SyncLocationDocument;
import org.openapplications.oagis.x9.UserAreaType;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.solumina.integration.common.MappingAction;
import com.ibaset.solumina.integration.common.MessageProcessor;
import com.ibaset.common.util.OAGIUtils; // G8R2SP4
import com.ibaset.solumina.integration.common.ReceiveOAGISMessageListener;
import com.ibaset.solumina.integration.exception.MappingException;
import com.ibaset.solumina.integration.logging.MessageLogger;
import com.ibaset.solumina.integration.logging.MessageLoggerParams;
import com.ibaset.solumina.oagis.LocationUserAreaType;
import com.ibaset.solumina.systemadmin.location.integration.LocationSyncMessageProcessor;
import com.pw.solumina.sysadmin.location.dao.LocationDaoPW;

import org.w3c.dom.Node;

/**
 * @author C293011 *
 */
public abstract class LocationSyncMessageProcessorImplPW extends ImplementationOf<LocationSyncMessageProcessor> implements LocationSyncMessageProcessor
{
	@Reference	LocationDaoPW locationDaoPW;
	@Reference	ReceiveOAGISMessageListener receiveOAGISMessageListener;
	@Reference  OAGIUtils oagiUtils = null;  // G8R2SP4

	protected Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());
	
	@Override
	public MappingAction[] getMappingActions(XmlObject inboundBod)
			throws MappingException {

		boolean workDeptExists = false;
		boolean workCenterExists = false;
		String workDept = "";
		String workCenter = "";
		String workCenterDesc = "";

		if (inboundBod instanceof SyncLocationDocument ) 
		{
			SyncLocationDocument ld = (SyncLocationDocument) inboundBod;
			LocationType loctype = ld.getSyncLocation().getDataArea().getLocationArray(0);	
			workDept = loctype.getIDArray(0).getStringValue();
			
			LocationType loctypewc = ld.getSyncLocation().getDataArea().getLocationArray(1);
			workCenter = loctypewc.getIDArray(0).getStringValue(); 
			workCenterDesc = loctypewc.getNameArray(0).getStringValue();  
			
			//get the action code here.  If the action is "Replace" then perform the checks.  If action is "Delete", leave the message as is
			ActionExpressionType actionExp = ld.getSyncLocation().getDataArea().getSync().getActionCriteriaArray(0).getActionExpressionArray(0);
			String actionCode = actionExp.getActionCode();
			if ("REPLACE".equals(actionCode.toUpperCase())) 
			{
				//action code is Replace, performs the checks
				workDeptExists = locationDaoPW.existsWorkDept(workDept);
				if (workDeptExists) 
				{
					workCenterExists = locationDaoPW.existsWorkCenter(workDept, workCenter);
					if (workCenterExists) 
					{
						actionExp.setActionCode("Change");
						actionExp.setStringValue("/NS1:SyncLocation/NS1:DataArea/NS1:Location[2]");
					} 
					else 
					{
						//work center does not exists
						actionExp.setActionCode("Add");
						actionExp.setStringValue("/NS1:SyncLocation/NS1:DataArea/NS1:Location[2]");
					}
				} 
				else 
				{
					//work department does not exist
					actionExp.setActionCode("Replace");
					actionExp.setStringValue("/NS1:SyncLocation/NS1:DataArea/NS1:Location[1]");				
				}
			}
		}
		return Super.getMappingActions(inboundBod);
	}
 
    ///processMessage is From AT the rest is from OEM
    public void processMessage(MappingAction[] mappingActions,
    		XmlObject inboundBod,
    		MessageLoggerParams messageLoggerParams) throws Exception
    {
    	
    	//process original message
    	Super.processMessage(mappingActions, inboundBod, messageLoggerParams);

    	String messageText = null;
    	String workDept = null;
    	String workCenter = null;
    	String workCenterDesc = "";
    	
		SyncLocationDocument ld = (SyncLocationDocument) inboundBod;
		LocationType loctype = ld.getSyncLocation().getDataArea().getLocationArray(0);	
		workDept = loctype.getIDArray(0).getStringValue();
		
		LocationType loctypewc = ld.getSyncLocation().getDataArea().getLocationArray(1);
		workCenter = loctypewc.getIDArray(0).getStringValue(); 
		workCenterDesc = loctypewc.getNameArray(0).getStringValue(); 
		
    	MessageProcessor messageProcessor = receiveOAGISMessageListener.getMessageProcessor(messageText);
    	SyncLocationDocument syncLocationDocument = (SyncLocationDocument) inboundBod;
    	LocationType locationType = syncLocationDocument.getSyncLocation().getDataArea().getLocationArray(0);
    	UserAreaType userAreaType = locationType.getUserArea();    	            
    	LocationUserAreaType locationUserAreaDocument = (LocationUserAreaType) oagiUtils.getObjectFromAnyTypeByType(userAreaType, LocationUserAreaType.type);
    	LocationType locType = locationUserAreaDocument.getParentLocationArray(0);
    	String type = locType.getType();
    	
    	String workLoc = locType.getIDArray(0).getStringValue();
    	
    	String workCenterType = "MFG";
    	if (workCenterDesc.toUpperCase().contains("INSP ") || workCenterDesc.toUpperCase().contains("INSPECTION"))
    	{
    		workCenterType = "INSP";
    	}
   	
    	//update work center type here
    	String workLocationID = locationDaoPW.getWorkLocationID(workLoc);
    	String workDepartmentID = locationDaoPW.getWorkDeptID(workLocationID, workDept);
    	String workCenterID = locationDaoPW.getWorkCenterID(workLocationID, workDepartmentID, workCenter);
    	
    	locationDaoPW.updateWorkCenterType(workLocationID, workDepartmentID, workCenterID, workCenterType); 		
    } 


    public static XmlObject getObjectFromAnyTypeByType(XmlObject source,
            SchemaType searchType)
    {
    	Node base = null;
    	XmlCursor cur = null;
    	XmlObject testObject = null;
    	XmlObject result = null;

    	if (source != null)
    	{
    		// Get DOM to traverse source XmlObject
    		cur = source.newCursor();

    		if (cur.toFirstChild())
    		{
    			base = cur.getDomNode();
    			testObject = XmlBeans.nodeToXmlObject(base);

    			result = testObject;
    		}
    	}
    	// Dispose Cursor
    	cur.dispose();

    	return result;
    }
   
    }


