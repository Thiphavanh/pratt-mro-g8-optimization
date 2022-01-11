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
 *  File:    T222MessageProcessor.java
 * 
 *  Created: 2017-11-XX
 * 
 *  Author:  c293011
 * 
 *  Revision History
 * 
 *  Date      	Who                	Description
 *  ----------	---------------	---------------	---------------------------------------------------
 * 2017-11-XX 	c293011		    Initial Release SMRO_TR_T222 LO Notification
 * 2018-04-12   R. Thorpe       Defect 681 Change WORKSCOPE to WORK_SCOPE and UPDT_TIMESTAMP to UPDATE_TIME_STAMP to match SAP's message
 * 2019-03-01   D.Miron         Updated for G8R2SP4
*/

package com.pw.solumina.T222.integration.impl;


import java.util.Date;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openapplications.oagis.x9.SyncRecordDocument;

import com.ibaset.common.Reference;
import com.ibaset.solumina.integration.common.BaseMessageProcessor;
import com.ibaset.common.util.OAGIUtils; // G8R2SP4
import com.ibaset.solumina.integration.exception.ParsingException;
import com.ibaset.solumina.integration.logging.MessageLogger;
import com.ibaset.solumina.integration.logging.MessageLoggerParams;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.pw.common.dao.CommonDaoPW;
import com.pw.solumina.T222.integration.impl.IT222MessageProcessor;
import com.pw.solumina.T222.integration.impl.T222MessageProcessor;
import com.pw.solumina.interfaces.dao.LONotificationDaoPW;
import com.pw.solumina.sysadmin.location.dao.LocationDaoPW;

import org.openapplications.oagis.x9.ActionExpressionType;
import org.openapplications.oagis.x9.KeysType;
import org.openapplications.oagis.x9.NameValuePairType;
import org.openapplications.oagis.x9.RecordType;
import org.openapplications.oagis.x9.SyncRecordDataAreaType;
import org.openapplications.oagis.x9.SyncRecordType;

public class T222MessageProcessor extends BaseMessageProcessor implements IT222MessageProcessor
{
	@Reference  private IMessage message; 
	@Reference	private LONotificationDaoPW loNotificationDaoPW = null;
	@Reference  private CommonDaoPW commonDaoPW = null; 
	@Reference  private LocationDaoPW locationDaoPW = null;
	@Reference  private OAGIUtils oagiUtils = null;  // G8R2SP4

	@Override
	public SchemaType getInboundBodType() 
	{
		
		return SyncRecordDocument.type;
	}
	
	@Override
	public XmlObject parseMessage(String inboundMessage) throws ParsingException
	{
		XmlObject inboundBod = null;
		SchemaType inboundBodDocumentType = getInboundBodType();

		try{
			inboundBod = XmlObject.Factory.parse(inboundMessage,new XmlOptions().setDocumentType(inboundBodDocumentType));
		}
		catch (XmlException xe)
		{
			throw new ParsingException("Error parsing message", xe);
		}

		return inboundBod;
	}

	@Override
	public void processMessage(String messageText, MessageLoggerParams loggerParams) 
	{  
		try 
		{
			doProcessing (messageText, loggerParams);
			if (loggerParams.getWarningText() != null)
			{
				loggerParams.setStatus(MessageLogger.STATUS_RECEIVE_NEEDS_REVIEW);
			}
			else
			{
			    loggerParams.setStatus(MessageLogger.STATUS_RECEIVE_SUCCESS);
			}
		}
		catch (Throwable t)
		{
			loggerParams.setErrors(t);
			loggerParams.setStatus(MessageLogger.STATUS_RECEIVE_FAILURE);
			loggerParams.setErrorText(t.getMessage());
		}
	}	
	
	public void doProcessing(String messageIn, MessageLoggerParams loggerParams) throws Throwable 
	{			
		XmlObject inboundBod = parseMessage(messageIn);
		oagiUtils.validateXml(inboundBod);

		String MessageText = null;
		
		if(inboundBod instanceof SyncRecordDocument)
		{			
			SyncRecordDocument doc = (SyncRecordDocument) inboundBod;
			SyncRecordType syncRecord = doc.getSyncRecord();
			SyncRecordDataAreaType dataArea = syncRecord.getDataArea();
			
			ActionExpressionType actionExp = doc.getSyncRecord().getDataArea().getSync().getActionCriteriaArray(0).getActionExpressionArray(0);
			String actionCode = actionExp.getActionCode();
			
			String invalidDataMessage = null;
			List<RecordType> recordTypeList = dataArea.getRecordList();

			String salesOrder  = null;
			String engineType  = null;
			String engineModel = null;

			for (Iterator recordTypeIterator = recordTypeList.iterator(); recordTypeIterator.hasNext();)
			{ ///for each <Record> tag section...
				RecordType recordType = (RecordType) recordTypeIterator.next();

				String moduleCode 	= null;	/* initialize */
				String actNo 		= null;	/* initialize */
				String subActNo     = null;	/* initialize */
				String customer     = null;	/* initialize */
				String customerDesc = null;	/* initialize */
				String workScope    = null;	/* initialize */
				String serialNo     = null;	/* initialize */
				String workLoc      = null;	/* initialize */
				String confirmNo  	= null;	/* initialize */
				String startDate	= null;	/* initialize */
				String finishDate 	= null;	/* initialize */
				String updtDate   	= null;	/* initialize */
				
				KeysType keys = recordType.getKeys();

				List<NameValuePairType> nameValuePairTypeList = keys.getRecordKeyList();
				
				for (Iterator nameValuePairTypeIterator = nameValuePairTypeList.iterator(); nameValuePairTypeIterator.hasNext();)
				{ ///for each <RecordKey> tag NameValuePair within <Keys> section...

					NameValuePairType nameValuePairType = (NameValuePairType) nameValuePairTypeIterator.next();

					String recordKeyName = nameValuePairType.getName();

					if (recordKeyName.contentEquals("SALES_ORDER"))
					{
						salesOrder = nameValuePairType.getStringValue();
					}

					if (recordKeyName.contentEquals("ENGINE_TYPE"))
					{
						engineType = nameValuePairType.getStringValue();
					}

					if (recordKeyName.contentEquals("ENGINE_MODEL"))
					{
						engineModel = nameValuePairType.getStringValue();
					}

					if (recordKeyName.contentEquals("MODULE_CODE"))
					{
						moduleCode = nameValuePairType.getStringValue();
					}

					if (recordKeyName.contentEquals("ACT_NO"))
					{
						actNo = nameValuePairType.getStringValue();
					}

					if (recordKeyName.contentEquals("SUB_ACT_NO"))
					{
						subActNo = nameValuePairType.getStringValue();
					}

					if (recordKeyName.contentEquals("CUSTOMER"))
					{
						customer = nameValuePairType.getStringValue();
					}

					if (recordKeyName.contentEquals("CUSTOMER_DESC"))
					{
						customerDesc = nameValuePairType.getStringValue();
					}
					if (recordKeyName.contentEquals("WORK_SCOPE"))    //Defect 681
					{
						workScope = nameValuePairType.getStringValue();
					}

					if (recordKeyName.contentEquals("SERIAL_NO"))
					{
						serialNo = nameValuePairType.getStringValue();
					}

					if (recordKeyName.contentEquals("WORK_LOC"))
					{
						workLoc = nameValuePairType.getStringValue();
					}

					if (recordKeyName.contentEquals("CONFIRMATION_NO"))
					{
						confirmNo = nameValuePairType.getStringValue();
					}

					if (recordKeyName.contentEquals("SUB_ACT_START_DATE"))
					{
						startDate = nameValuePairType.getStringValue();
					}

					if (recordKeyName.contentEquals("SUB_ACT_FINISH_DATE"))
					{
						finishDate = nameValuePairType.getStringValue();
					}

					if (recordKeyName.contentEquals("UPDATE_TIME_STAMP"))//Defect 681
					{
						updtDate = nameValuePairType.getStringValue();
					}
				} ///end for (Iterator nameValuePairTypeIterator = nameValuePairTypeList.iterator(); nameValuePairTypeIterator.hasNext();)


				/***************************************************************
				 * 
				 * do your processing of the data here
				 * 
				 * Data Validity Checks		
				 * 1.  check if engine_type, superior_network, sub_network combination (PK)
				 *     exists in pwust_network_def.  
				 * 2.  check if engine model/type combination exists
				 * 3.  check if workLocation exists    
				 * 4.  check if work scope exists  
				 * 5.  check if customer exists  
				 ***************************************************************/
				    boolean validData = true;
				    // Validate Data
					boolean networkExists = loNotificationDaoPW.doesNetworkExist(engineType, actNo, subActNo);
					boolean engineModelTypeExists = loNotificationDaoPW.doesEngineModelTypeExist(engineType, engineModel);
					boolean workLocationExists = locationDaoPW.existsWorkLocation(workLoc);
					boolean workscopeExists = loNotificationDaoPW.doesWorkscopeExist(engineType, workLoc, workScope);
					boolean customerExists = loNotificationDaoPW.doesCustomerExist(customer);

					if (!engineModelTypeExists )  // cannot process at all
					{
						invalidDataMessage = "Invalid Engine Model/Type. EngineType= "+engineType+" EngineModel= "+engineModel+"\n";
						loggerParams.setRef1("SalesOrder= "+salesOrder+" EngineType= "+engineType+" EngineModel= "+engineModel);  
						loggerParams.setRef2(invalidDataMessage);
						throw new Exception(invalidDataMessage); 
					}
					else if (!workLocationExists)  // cannot process at all
					{
						invalidDataMessage = "Invalid WorkLocation "+workLoc+"\n";
						loggerParams.setRef1("SalesOrder= "+salesOrder+" EngineType= "+engineType+" EngineModel= "+engineModel);  
						loggerParams.setRef2(invalidDataMessage);
						throw new Exception(invalidDataMessage);
					}
					else if (!customerExists )  // cannot process at all
					{
						invalidDataMessage = "Invalid Customer "+customer+"\n";
						loggerParams.setRef1("SalesOrder= "+salesOrder+" EngineType= "+engineType+" EngineModel= "+engineModel);  
						loggerParams.setRef2(invalidDataMessage);
						throw new Exception(invalidDataMessage);
					}
					else if (!workscopeExists)  // can be multiple work scopes in feed
					{
						if (invalidDataMessage != null)
						{
						    invalidDataMessage = invalidDataMessage+"Invalid Workscope. EngineType "+engineType+" workLocation "+workLoc+" workscope "+workScope+"\n";  
						
						}
						else
						{
							invalidDataMessage = "Invalid Workscope. EngineType "+engineType+" workLocation "+workLoc+" workscope "+workScope+"\n";
						}
						validData = false;
					}
					else if (!networkExists)
					{

						if (invalidDataMessage != null)
						{
							invalidDataMessage = invalidDataMessage+"Invalid Network. EngineType "+engineType+" actNo "+actNo+" subActNo "+subActNo+"\n";
						}
						else
						{
							invalidDataMessage = "Invalid Network. EngineType "+engineType+" actNo "+actNo+" subActNo "+subActNo+"\n";
						}
						validData = false;
					}
					
					if (validData)
					{	
						// All Validity Checks complete
						//start date							
						List startDateList = commonDaoPW.getDatefromString2(startDate);			
						ListIterator startDateIt = startDateList.listIterator();
						Map startDateMap = (Map) startDateIt.next();
						Date startDate1  = (Date) startDateMap.get("RETURNDATE");
						
						//finish date
						List finishDateList = commonDaoPW.getDatefromString2(finishDate);			
						ListIterator finishDateIt = finishDateList.listIterator();
						Map finishDateMap = (Map) finishDateIt.next();
						Date finishDate1  = (Date) finishDateMap.get("RETURNDATE");
						
						//update date
						List updtDateList = commonDaoPW.getDatefromString2(updtDate);			
						ListIterator updtDateIt = updtDateList.listIterator();
						Map updtDateMap = (Map) updtDateIt.next();
						Date updtDate1  = (Date) updtDateMap.get("RETURNDATE");	
						
						// Action code Add
						if ("Add".equalsIgnoreCase(actionCode))	
						{
							loNotificationDaoPW.insertSalesOrder(salesOrder, engineType, engineModel, moduleCode, actNo, subActNo, customer, customerDesc, workScope, serialNo, workLoc, confirmNo, startDate1, finishDate1, updtDate1);
						}
						else if ("Change".equalsIgnoreCase(actionCode))
						{
							// check if the record exists before updating. if it does not, add it
							boolean recordExists = loNotificationDaoPW.doesSalesOrderRecordExist(salesOrder, actNo, subActNo);
							
						    if (recordExists)
						    {
							    loNotificationDaoPW.updateSalesOrder(salesOrder, engineModel, actNo, subActNo, workScope, customer, customerDesc, startDate1, finishDate1, updtDate1);
						    }
						    else
						    {
						    	loNotificationDaoPW.insertSalesOrder(salesOrder, engineType, engineModel, moduleCode, actNo, subActNo, customer, customerDesc, workScope, serialNo, workLoc, confirmNo, startDate1, finishDate1, updtDate1);
						    }
						}
						else 
						{
							loggerParams.setRef1("SalesOrder= "+salesOrder+" EngineType= "+engineType+" EngineModel= "+engineModel);  
							loggerParams.setRef2("Invalid ActionCode '"+actionCode+"'");
							throw new Exception("Invalid ActionCode '"+actionCode+"'");
						}			
					}	

			} //end for (Iterator recordTypeIterator = recordTypeList.iterator(); recordTypeIterator.hasNext();)
			
			//after entire message is processed, delete the records that are not flagged, update delete flag to null
			loNotificationDaoPW.deleteSalesOrderRecords(salesOrder);
			loNotificationDaoPW.updateDeleteFlag(salesOrder);
			
			if (invalidDataMessage != null)
			{			
				loggerParams.setWarningText(invalidDataMessage);
				loggerParams.setRef1("SalesOrder= "+salesOrder+" EngineType= "+engineType+" EngineModel= "+engineModel);  
				
				if (invalidDataMessage.length() > 100)
				{
					String newInvalidDataMessage= invalidDataMessage.substring(0,92)+" more..."; 
					loggerParams.setRef2(newInvalidDataMessage);
				}
				else
				{
				     loggerParams.setRef2(invalidDataMessage);
				}
			}
		} 
		else 
		{
			throw new Exception("XML message is not SyncRecordDocument ");
		} //end if( inboundBod instance of SyncRecordDocument)
	} //end doProcessing
}
