/*
 *  This unpublished work, first created in 2018 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2018.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    M211MessageProcessor.java
 * 
 *  Created: 2018-03-02 
 * 
 *  Author:  Raeann Thorpe  
 * 
 *  Revision History
 * 
 *  Date        Who          	Description
 *  --------    ------------ 	---------------------------------------------------
 *  2018-03-02	R. Thorpe		Initial Release for SMRO_MD_M211
 *  2018-05-16  R. Thorpe       SMRO_MD_211 Defect 782  if certType = Q set it to QA, if T set to MFG
 *  2018-05-21  R. Thorpe       SMRO_MD_211 Defect 798  fix null pointer exception on update of certification
 *  2019-03-01  D.Miron         Updated for G8R2SP4 
 */

package com.pw.solumina.M211.integration.impl;

import java.util.Calendar;
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
import com.ibaset.solumina.integration.common.BaseMessageProcessor;
import com.ibaset.common.util.OAGIUtils; // G8R2SP4;
import com.ibaset.solumina.integration.exception.ParsingException;
import com.ibaset.solumina.integration.logging.MessageLogger;
import com.ibaset.solumina.integration.logging.MessageLoggerParams;

import com.pw.common.dao.CommonDaoPW;
import com.pw.solumina.M211.integration.impl.IM211MessageProcessor;

public class M211MessageProcessor extends BaseMessageProcessor implements IM211MessageProcessor {
	
	@Reference  private com.ibaset.solumina.sffnd.dao.impl.CertificationDaoImpl certDao = null;
	@Reference  private com.ibaset.solumina.sffnd.dao.impl.UserCertificationDaoImpl userCertDao = null;
	@Reference  private com.ibaset.solumina.sffnd.dao.impl.UserDaoImpl userDao = null;  
	@Reference	private CommonDaoPW commonDaoPW = null;
	@Reference  private com.pw.solumina.interfaces.dao.UserCertDaoPW userCertDaoPW = null; 
	@Reference  private OAGIUtils oagiUtils = null;  // G8R2SP4
	
	@Override
	public SchemaType getInboundBodType() 
	{
		return SyncRecordDocument.type;  ///RTT changed was returning  null
	}
	
	public void doProcessing(String messageIn, MessageLoggerParams loggerParams) throws Throwable 
	{			
		XmlObject inboundBod = parseMessage(messageIn);
		oagiUtils.validateXml(inboundBod);

		if(inboundBod instanceof SyncRecordDocument)
		{
			int i = 1;
			SyncRecordDocument doc = (SyncRecordDocument) inboundBod;

			SyncRecordType syncRecord = doc.getSyncRecord();

			SyncRecordDataAreaType dataArea = syncRecord.getDataArea();

			List<RecordType> recordTypeList = dataArea.getRecordList();

			for (Iterator recordTypeIterator = recordTypeList.iterator(); recordTypeIterator.hasNext();)
			{ ///for each <Record> tag section...
				RecordType recordType = (RecordType) recordTypeIterator.next();

				String employeeNumber    = null;	/* initialize */
				String employeeName      = null;	/* initialize */
				String eyeExamDate       = null;	/* initialize */
				String certificationCode = null;	/* initialize */
				String certificationDesc = null;	/* initialize */
				String certificationType = null;	/* initialize */
				String effectiveDate     = null;	/* initialize */
				String expirationDate    = null;	/* initialize */

				KeysType keys = recordType.getKeys();

				List<NameValuePairType> nameValuePairTypeList = keys.getRecordKeyList();

				for (Iterator nameValuePairTypeIterator = nameValuePairTypeList.iterator(); nameValuePairTypeIterator.hasNext();)
				{ ///for each <RecordKey> tag NameValuePair within <Keys> section...

					NameValuePairType nameValuePairType = (NameValuePairType) nameValuePairTypeIterator.next();

					String recordKeyName = nameValuePairType.getName();

					if (recordKeyName.contentEquals("EmployeeNumber"+i))
					{
						employeeNumber = nameValuePairType.getStringValue();
					}

					if (recordKeyName.contentEquals("EmployeeName"+i))
					{
						employeeName = nameValuePairType.getStringValue();
					}

					if (recordKeyName.contentEquals("EyeExamDate"+i))
					{
						eyeExamDate = nameValuePairType.getStringValue();
					}

					if (recordKeyName.contentEquals("CertificationCode"+i))
					{
						certificationCode = nameValuePairType.getStringValue();
					}

					if (recordKeyName.contentEquals("Certification Desc"+i))
					{
						certificationDesc = nameValuePairType.getStringValue();
					}

					if (recordKeyName.contentEquals("CertificationType"+i))
					{
						certificationType = nameValuePairType.getStringValue();
                        // BEFIN Defect 782
						if("Q".equals(certificationType))
						{
							certificationType = "QA";
						}
						else if ("T".equals(certificationType))
						{
							certificationType = "MFG";
						}
						// END Defect 782
					}

					if (recordKeyName.contentEquals("EffectiveDate"+i))
					{
						effectiveDate = nameValuePairType.getStringValue();
					}

					if (recordKeyName.contentEquals("ExpirationDate"+i))
					{
						expirationDate = nameValuePairType.getStringValue();
						System.out.println("expirationDate is "+expirationDate);
					}
				} ///end for (Iterator nameValuePairTypeIterator = nameValuePairTypeList.iterator(); nameValuePairTypeIterator.hasNext();)

				i++;  // increment i
				/***************************************************************
				 * 
				 * do your processing of the data here
				 * 		
				 ***************************************************************/
				if (employeeNumber.equals("99999999"))
				{
					//Master Data Add to SFFND_CERT_DEF
					//does the certification exist?
					boolean certExists = certDao.selectIsExistingCertification(certificationCode);
					if(certExists)
					{
						//Begin Defect 798
						String obsolete = "N";
						// check cert expiration date to determine what obsolete_record_flag should be set to
					
				    	if(expirationDate != null)
				    	{
				    		List expDateList = commonDaoPW.getDatefromString(expirationDate);			
							ListIterator expDate = expDateList.listIterator();
							Map expDateMap = (Map) expDate.next();
							Date expirationDate1 = (Date)expDateMap.get("RETURNDATE");
							
							System.out.println("expirationDate is "+expirationDate1);
							
				    		Calendar cal = Calendar.getInstance();
							cal.setTime(new Date());

							Date currentDate = cal.getTime();
							
							System.out.println("currentDate is "+currentDate);
							
							if(expirationDate1.before(currentDate))
							{
								obsolete = "Y";	
							}
				    	}
				    	//End Defect 798
				    	
						// update
						certDao.updateCertification(certificationCode, certificationType, certificationDesc, "", "", obsolete);// Defect 798
					}
					else
					{
						//insert
						certDao.insertCertification(certificationCode, certificationType, certificationDesc, "", "");
					}

				}
				else
				{
					// does the employee exist
					boolean isExistingUser = userDao.isExistingUser(employeeNumber);
					if (isExistingUser)
					{
						// EYE EXAM DATE PROCESSING
						//is eye exam date not null
						if(StringUtils.isNotEmpty(eyeExamDate))
						{

							List dateList = commonDaoPW.getDatefromString(eyeExamDate);			
							ListIterator iterDate = dateList.listIterator();
							Map dateMap = (Map) iterDate.next();
							Date examDate = (Date)dateMap.get("RETURNDATE");
							
							userDao.updatedateOflastEyeExam(employeeNumber, examDate);
						}

						// USER CERTIFICATION PROCESSING
						String certificationNumber = null;
						
						List effDateList = commonDaoPW.getDatefromString(effectiveDate);			
						ListIterator effDate = effDateList.listIterator();
						Map effDateMap = (Map) effDate.next();
						Date effectiveDate1 = (Date)effDateMap.get("RETURNDATE");

						List expDateList = commonDaoPW.getDatefromString(expirationDate);			
						ListIterator expDate = expDateList.listIterator();
						Map expDateMap = (Map) expDate.next();
						Date expirationDate1 = (Date)expDateMap.get("RETURNDATE");
						
						// does the user currently have this certification
						boolean userCertExists = userCertDaoPW.doesUserCertExist(employeeNumber, certificationCode);
						if (userCertExists)
						{   // update
							userCertDao.updateUserCertification(employeeNumber, certificationCode, effectiveDate1, expirationDate1, certificationNumber);
						}
						else
						{
							// insert
							userCertDao.insertUserCertification(employeeNumber, certificationCode, effectiveDate1, expirationDate1, certificationNumber);
						}
					}	
				} // end isExistingUser
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