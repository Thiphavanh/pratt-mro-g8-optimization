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
*  File:    EACPMessageProcessor.java
* 
*  Created: 2017-10-19
* 
*  Author:  c293011
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-10-19 		c293011		    Initial Release XXXXXXX
* 2019-03-10        D.Miron         Updated for G8R2SP4
*/


package com.pw.solumina.eacp.integration.impl;

/**
 * @author c293011
 *
 */

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openapplications.oagis.x9.KeysType;
import org.openapplications.oagis.x9.NameValuePairType;
import org.openapplications.oagis.x9.RecordType;
import org.openapplications.oagis.x9.SyncRecordDataAreaType;
import org.openapplications.oagis.x9.SyncRecordDocument;
import org.openapplications.oagis.x9.SyncRecordType;

import com.ibaset.common.Reference;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.util.OAGIUtils; // G8R2SP4
import com.ibaset.solumina.integration.common.BaseMessageProcessor;
import com.ibaset.solumina.integration.exception.ParsingException;
import com.ibaset.solumina.integration.logging.MessageLogger;
import com.ibaset.solumina.integration.logging.MessageLoggerParams;
import com.ibaset.solumina.sffnd.dao.impl.SecurityGroupDaoImpl;
import com.pw.common.dao.CommonDaoPW;
import com.pw.solumina.eacp.integration.IEACPMessageProcessor;
import com.pw.solumina.interfaces.dao.UserDaoPW;


public class EACPMessageProcessor extends BaseMessageProcessor implements IEACPMessageProcessor {
	
	@Reference  private SecurityGroupDaoImpl securityGroupDao = null;
	@Reference  private UserDaoPW userDaoPW = null;
	@Reference	private CommonDaoPW commonDaoPW = null;
	@Reference  private OAGIUtils oagiUtils = null;  // G8R2SP4
	
	@Override
	public SchemaType getInboundBodType() 
	{
		return SyncRecordDocument.type;  
	}
	
	public void doProcessing(String messageIn, MessageLoggerParams loggerParams) throws Throwable 
	{			
		XmlObject inboundBod = parseMessage(messageIn);

		oagiUtils.validateXml(inboundBod);
		if(inboundBod instanceof SyncRecordDocument)
		{
			//int i = 1;
			SyncRecordDocument doc = (SyncRecordDocument) inboundBod;

			SyncRecordType syncRecord = doc.getSyncRecord();

			SyncRecordDataAreaType dataArea = syncRecord.getDataArea();

			List<RecordType> recordTypeList = dataArea.getRecordList();

			for (Iterator recordTypeIterator = recordTypeList.iterator(); recordTypeIterator.hasNext();)
			{ ///for each <Record> tag section...
				RecordType recordType = (RecordType) recordTypeIterator.next();

				String authNumber     = null;	 		 /* initialize */
				String authIrn        = null;			 /* initialize */
				String authType       = "AUTHORIZATION"; /* initialize */
				String businessUnit   = "EACP";	     	 /* initialize */
				String expirationDate = null;			 /* initialize */
				String activationDate = null;			 /* initialize */
				String authStatus     = null;			 /* initialize */

				KeysType keys = recordType.getKeys();

				List<NameValuePairType> nameValuePairTypeList = keys.getRecordKeyList();
				
				for (Iterator nameValuePairTypeIterator = nameValuePairTypeList.iterator(); nameValuePairTypeIterator.hasNext();)
				{
			       NameValuePairType nameValuePairType = (NameValuePairType) nameValuePairTypeIterator.next();

			       String recordKeyName = nameValuePairType.getName();
				   
			       if (recordKeyName.contentEquals("AuthNumber"))
			       {
			    	   authNumber     = nameValuePairType.getStringValue();      //security group
			       }
			       if (recordKeyName.contentEquals("AuthIrn"))
			       {
			    	   authIrn        = nameValuePairType.getStringValue();  	 //security group desc
			       }
			       if (recordKeyName.contentEquals("AuthStatus"))
			       {
			    	   authStatus     = nameValuePairType.getStringValue();      //obsolete record flag
			       }
			       if (recordKeyName.contentEquals("ExpirationDate"))
			       {
			    	   expirationDate     = nameValuePairType.getStringValue();      
			       }	
			       if (recordKeyName.contentEquals("ActivationDate"))
			       {
			    	   activationDate     = nameValuePairType.getStringValue();      
			       }			       
				}
				/***************************************************************
				 * 
				 * do your processing of the data here
				 * 		
				 ***************************************************************/
				// Gets the user name
		        String userName = ContextUtil.getUsername();
		        
		        Date expireDate = null;
		        if(StringUtils.isNotEmpty(expirationDate))
		        {
		        	//Convert expirationDate to date
		        	List dateList = commonDaoPW.getDatefromString2(expirationDate);			
		        	ListIterator iterDate = dateList.listIterator();
		        	Map dateMap = (Map) iterDate.next();
		            expireDate = (Date)dateMap.get("RETURNDATE");
		        }
				   
				String obsoleteRecordFlag = "N";
				if (StringUtils.equalsIgnoreCase(authStatus, "Inactive"))
				{
					obsoleteRecordFlag = "Y";
				}
				
				boolean securityGroupExists = userDaoPW.doesSecurityGroupExist(authNumber);

				if (securityGroupExists)
				{
					securityGroupDao.updateSecurityGroup(expireDate,//expirationDate, 
							                             businessUnit, //issuingAgency, 
							                             userName, 
							                             obsoleteRecordFlag, 
							                             authType,//securityGroupType, 
							                             authIrn,//securityGroupDescription, 
							                             null, //ucfSecurityGroupVch1, 
							                             null, //ucfSecurityGroupVch2, 
							                             null, //ucfSecurityGroupVch3, 
							                             null, //ucfSecurityGroupVch4, 
							                             null, //ucfSecurityGroupVch5, 
							                             null, //ucfSecurityGroupVch6, 
							                             null, //ucfSecurityGroupVch7, 
							                             null, //ucfSecurityGroupVch8, 
							                             null, //ucfSecurityGroupVch9, 
							                             null, // ucfSecurityGroupVch10, 
							                             null, //ucfSecurityGroupVch11, 
							                             null, //ucfSecurityGroupVch12, 
							                             null, //ucfSecurityGroupVch13, 
							                             null, //ucfSecurityGroupVch14, 
							                             null, //ucfSecurityGroupVch15, 
							                             null, //ucfSecurityGroupNumber1, 
							                             null, //ucfSecurityGroupNumber2, 
							                             null, //ucfSecurityGroupNumber3, 
							                             null, // ucfSecurityGroupNumber4, 
							                             null, // ucfSecurityGroupNumber5, 
							                             null, // ucfSecurityGroupDate1, 
							                             null, //ucfSecurityGroupDate2, 
							                             null, //ucfSecurityGroupDate3, 
							                             null, // ucfSecurityGroupDate4, 
							                             null, //  ucfSecurityGroupDate5, 
							                             null, //  ucfSecurityGroupFlag1, 
							                             null, // ucfSecurityGroupFlag2, 
							                             null, // ucfSecurityGroupFlag3, 
							                             null, //ucfSecurityGroupFlag4, 
							                             null, //ucfSecurityGroupFlag5, 
							                             authNumber,//securityGroup, 
							                             null, //ucfSecurityGroupVch2551, 
							                             null, //ucfSecurityGroupVch2552, 
							                             null, //ucfSecurityGroupVch2553, 
							                             null, //ucfSecurityGroupVch40001, 
							                             null); //ucfSecurityGroupVch40002);
					
				
				}
				else
				{
					//insert security group
					securityGroupDao.insertSecurityGroup(authNumber,//securityGroup, 
							                             expireDate,//expirationDate, 
							                             businessUnit,//issuingAgency, ????
							                             userName, 
							                             authType,//securityGroupType, 
							                             authIrn,//securityGroupDescription, 
							                             null,//ucfSecurityGroupVch1, 
							                             null,//ucfSecurityGroupVch2, 
							                             null,//ucfSecurityGroupVch3, 
							                             null,//ucfSecurityGroupVch4, 
							                             null,//ucfSecurityGroupVch5, 
							                             null,//ucfSecurityGroupVch6, 
							                             null,//ucfSecurityGroupVch7, 
							                             null,//ucfSecurityGroupVch8, 
							                             null,//ucfSecurityGroupVch9, 
							                             null,//ucfSecurityGroupVch10, 
							                             null,//ucfSecurityGroupVch11, 
							                             null,//ucfSecurityGroupVch12, 
							                             null,//ucfSecurityGroupVch13, 
							                             null,//ucfSecurityGroupVch14, 
							                             null,//ucfSecurityGroupVch15, 
							                             null,//ucfSecurityGroupNumber1, 
							                             null,//ucfSecurityGroupNumber2, 
							                             null,//ucfSecurityGroupNumber3, 
							                             null,//ucfSecurityGroupNumber4, 
							                             null,//ucfSecurityGroupNumber5, 
							                             null,//ucfSecurityGroupDate1, 
							                             null,//ucfSecurityGroupDate2, 
							                             null,//ucfSecurityGroupDate3, 
							                             null,//ucfSecurityGroupDate4, 
							                             null,//ucfSecurityGroupDate5, 
							                             null,//ucfSecurityGroupFlag1, 
							                             null,//ucfSecurityGroupFlag2, 
							                             null,//ucfSecurityGroupFlag3, 
							                             null,//ucfSecurityGroupFlag4, 
							                             null,//ucfSecurityGroupFlag5, 
							                             null,//ucfSecurityGroupVch2551, 
							                             null,//ucfSecurityGroupVch2552, 
							                             null,//ucfSecurityGroupVch2553, 
							                             null,//ucfSecurityGroupVch40001, 
							                             null);//ucfSecurityGroupVch40002);
					
					}
			} //end for (Iterator recordTypeIterator = recordTypeList.iterator(); recordTypeIterator.hasNext();)
		} //end if( inboundBod instance of SyncRecordDocument)
	} //end doProcessing
	
    public XmlObject parseMessage(String inboundMessage) throws ParsingException
    {
        XmlObject inboundBod = null;
        SchemaType inboundBodDocumentType = getInboundBodType();

        try
        {
            // Parse inbound message
            inboundBod = XmlObject.Factory.parse(inboundMessage,
                                                 new XmlOptions().setDocumentType(inboundBodDocumentType));
        }
        catch (XmlException xe)
        {
            throw new ParsingException("Error parsing message", xe);
        }

        return inboundBod;
    }
	
	//@Override
	public void processMessage(String messageText, MessageLoggerParams loggerParams) 
	{  
		String [] rec;
		String delimiter = "\r\n";
		rec = messageText.split(delimiter);
		try 
		{
			for (int i = 0; i < rec.length; i++)
			{		
				if(rec[i].trim().length()>0)
					doProcessing (rec[i], loggerParams);
			}
			loggerParams.setStatus(MessageLogger.STATUS_RECEIVE_SUCCESS);
		}
		catch (Throwable t)
		{
			loggerParams.setErrors(t);
			loggerParams.setStatus(MessageLogger.STATUS_RECEIVE_FAILURE);
		}
	}
}
