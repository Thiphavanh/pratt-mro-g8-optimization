/*
 *  This unpublished work, first created in 2014 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2014.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    com.pw.solumina.export_control.CallXClassWebServicePW.java
 * 
 *  Created: 2014-06-18
 * 
 *  Author:  T. Damble
 * 
 *  Revision History
 * 
 *  Date        Who          	Description
 *  ----------  ------------ 	---------------------------------------------------
 *  2014-06-18  T. Damble		New Development for SAIP_MD_M103.	 
 *  2018-01-11  X002174			SMRO_EXPT_201 - Defect 272. Changed BAER credentials to JCL2 credentials
 *  
 */

package com.pw.solumina.export_control;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.axis.message.MessageElement;
import org.xml.sax.helpers.ParserAdapter;
import org.xml.sax.InputSource;

import com.ibaset.common.Reference;
import com.pw.common.dao.CommonDaoPW;
import com.pw.common.utils.StringUtil;
import com.pw.solumina.xClassWebService.XClassWS_SoapStub;
import com.pw.solumina.xClassWebService.XClassWSLocator;
import com.pw.solumina.xClassWebService.AuthHeader;
import com.pw.solumina.xClassWebService.GetItemInfoResponseGetItemInfoResult;

public class CallXClassWebServicePW {
	@Reference
	private AuthHeader auth;
	@Reference 
	private XClassWebServiceCallResult EIIbeanResults;

	@Reference
	private CommonDaoPW commDao;

	@Reference
	XClassItemInfoSaxHandler handler;

	private String url1 = null;
	private String ldapID = null;
	private String ldapIDpw = null;
//	private String url1 = new String("http://webapps-staging.pw.utc.com/xclassws/XClassWS.svc");
//	private String ldapID = new String("pw_solumina_g7");
//	private String ldapIDpw = new String("Lu4$soLUminA_g7$8Sz");

	private final String CAGECODE = new String("NONE"); //new String("77445");
	//private final String FUNCTIONALAREA = new String("Manufacturing Engineering");
	//private final String BUSINESSUNIT = new String("Module Centers and Operations");
	private final String EXTPROPSAGENTKEY = new String("xclassWebService");

	/** Constructor Method
	 **/
	public CallXClassWebServicePW() {
		EIIbeanResults = new XClassWebServiceCallResult();
		auth = new AuthHeader();
	}// End Constructor CallEClassWebServicePW

	public XClassWebServiceCallResult requestToClassify(String itemID, 
			                                            String category, 
			                                            String type, 
			                                            String revision, 
			                                            String nomenclature, 
			                                            String baerClockId,
			                                            String businessUnit,
			                                            String functionalArea)
	{

		handler = new XClassItemInfoSaxHandler();  // Instantiate the handler. 
		String baerName = new String("");
		String baerEmail = new String("");
//		String cageCode = new String("");

		try{
			getLDAPInfo(EXTPROPSAGENTKEY);
		} catch(Exception ex){
			System.out.println(ex.toString());
		}

		if (url1 == null){
			EIIbeanResults.setReturnCode(1);
			EIIbeanResults.setReturnMsg("XClass WebService URL not found!!");
		} else {
			try {
				XClassWS_SoapStub service = (XClassWS_SoapStub)new XClassWSLocator().getXClassWSSoap(new URL(url1));

				auth.setId(ldapID);
				auth.setPassword(ldapIDpw);			

				System.out.println("ItemIdentifier = '" + itemID + "'");
				System.out.println("Cage Code = '" + CAGECODE + "'");
				System.out.println("ClassCategory = '" + category + "'");
				System.out.println("ClassType = '" + type + "'");
				System.out.println("revision = '" + revision + "'");
				System.out.println("Nomenclature = '" + nomenclature + "'");
				System.out.println("Baer Clock ID = '" + baerClockId + "'");
				System.out.println("BaerName = '" + baerName + "'");
				System.out.println("BaerEmail = '" + baerEmail + "'");
				System.out.println("FUNCTIONALAREA = '" + functionalArea + "'");
				System.out.println("BUSINESSUNIT = '" + businessUnit + "'");

				String rtcResponse = service.newRequest(auth, itemID, CAGECODE, category, type, revision, nomenclature, baerClockId, baerName, baerEmail, functionalArea, businessUnit);
				//Start Defect 272
				if (rtcResponse.equals("NewRequest denied. Invalid BAER credentials.")){
						rtcResponse = "NewRequest denied. Invalid JCL2 credentials.";
			    }
				//End Defect 272
				System.out.println("rtcResponse: '" + rtcResponse + "'");

				if (rtcResponse.toUpperCase().contains("FINISHED SUCCESSFULLY")){
					EIIbeanResults.setReturnCode(0);
				} else {
					EIIbeanResults.setReturnCode(1);
				}
				EIIbeanResults.setReturnMsg(rtcResponse);
			} catch(Exception e) {
				EIIbeanResults.setReturnCode(1);
				if (e.getMessage().length() == 0){
					EIIbeanResults.setReturnMsg("SOAP Call to XClass WebService failed");
				} else {
					EIIbeanResults.setReturnMsg("SOAP Call to XClass WebService failed; " + e.getMessage());
				}
				System.out.println(e);			
			}			
		}

		return EIIbeanResults;
	} //End Method RequestToClassify

	public XClassWebServiceCallResult getJurisdictionClassification(String itemID, String category, String type, String revision, String nomenclature, String baerClockNbr){

		handler = new XClassItemInfoSaxHandler();  // Instantiate the handler. 

		try{
			getLDAPInfo(EXTPROPSAGENTKEY);
		} catch(Exception ex){
			System.out.println(ex.toString());
		}

		if (url1 == null){
			EIIbeanResults.setReturnCode(1);
			EIIbeanResults.setReturnMsg("XClass WebService URL not found!!");
		} else {
			try {
				XClassWS_SoapStub service = (XClassWS_SoapStub)new XClassWSLocator().getXClassWSSoap(new URL(url1));

				auth.setId(ldapID);
				auth.setPassword(ldapIDpw);			


				GetItemInfoResponseGetItemInfoResult response = service.getItemInfo(auth, itemID, category, type, revision, nomenclature);

				org.apache.axis.message.MessageElement[] responseME_Array = response.get_any();
				String responseXML = null;
				if(responseME_Array != null) {
					for (int x = 0;x < responseME_Array.length; x++) {
						List<MessageElement> list_any16= java.util.Arrays.asList(responseME_Array[x]);
						responseXML = list_any16.toString();
						//		        		System.out.println("get_any() MessageElement[" + x + "] responseXML: '" + responseXML + "'");
					}
				}

				SAXParserFactory saxFactory = SAXParserFactory.newInstance();
				try {
					responseXML = responseXML.substring(1, (responseXML.length()-1));

					System.out.println("responseXML = '" + responseXML +"'");

					SAXParser      saxParser = saxFactory.newSAXParser();

					ParserAdapter pa =	new ParserAdapter(saxParser.getParser());   
					pa.setContentHandler(handler);
					byte bytearray[] = responseXML.getBytes();
					ByteArrayInputStream bais = new ByteArrayInputStream(bytearray);
					pa.parse(new InputSource(bais));
					EIIbeanResults = handler.xcwscResult;

				} catch (Throwable err) {
					EIIbeanResults.setReturnCode(1);
					EIIbeanResults.setReturnMsg(err.getMessage());
					err.printStackTrace ();
				}

			} catch(Exception e) {
				EIIbeanResults.setReturnCode(1);
				if (e.getMessage().length() == 0){
					EIIbeanResults.setReturnMsg("SOAP Call to XClass WebService failed");
				} else {
					EIIbeanResults.setReturnMsg("SOAP Call to XClass WebService failed; " + e.getMessage());
				}
				System.out.println(e);			
			}			
		}

		return EIIbeanResults;
	} //End Method GetJurisdictionClassification

	public XClassWebServiceCallResult requestToCancel(int recNbr, String archivedBy, String archivedReason){

		try{
			getLDAPInfo(EXTPROPSAGENTKEY);
		} catch(Exception ex){
			System.out.println(ex.toString());
		}

		if (url1 == null){
			EIIbeanResults.setReturnCode(1);
			EIIbeanResults.setReturnMsg("XClass WebService URL not found!!");
		} else {
			try {
				XClassWS_SoapStub service = (XClassWS_SoapStub)new XClassWSLocator().getXClassWSSoap(new URL(url1));

				auth.setId(ldapID);
				auth.setPassword(ldapIDpw);
				BigDecimal recNumber = new BigDecimal(recNbr);

				String cancelReqResponse = service.cancelRequest(auth, recNumber, archivedBy, archivedReason);
				System.out.println("cancelReqResponse: '" + cancelReqResponse + "'");

				if (cancelReqResponse.toUpperCase().contains("FINISHED SUCCESSFULLY")){
					EIIbeanResults.setReturnCode(0);
				} else {
					EIIbeanResults.setReturnCode(1);
				}
				EIIbeanResults.setReturnMsg(cancelReqResponse);
			} catch(Exception e) {
				EIIbeanResults.setReturnCode(1);
				if (e.getMessage().length() == 0){
					EIIbeanResults.setReturnMsg("SOAP Call to XClass WebService failed");
				} else {
					EIIbeanResults.setReturnMsg("SOAP Call to XClass WebService failed; " + e.getMessage());
				}
				System.out.println(e);			
			}			
		}

		return EIIbeanResults;
	} //End Method RequestToCancel

	public XClassWebServiceCallResult requestFastPath(int prevRecNbr, String itemId, String category, String type, String revision, String nomenclature, String baerName, String baerClockId, String baerEmail, String reason, String certTag){

		try{
			getLDAPInfo(EXTPROPSAGENTKEY);
		} catch(Exception ex){
			System.out.println(ex.toString());
		}

		if (url1 == null){
			EIIbeanResults.setReturnCode(1);
			EIIbeanResults.setReturnMsg("XClass WebService URL not found!!");
		} else {
			try {
				XClassWS_SoapStub service = (XClassWS_SoapStub)new XClassWSLocator().getXClassWSSoap(new URL(url1));

				auth.setId(ldapID);
				auth.setPassword(ldapIDpw);
				BigDecimal prevRecNumber = new BigDecimal(prevRecNbr);

				String fastPathResponse = service.fastPath(auth, prevRecNumber, itemId, category, type, revision, nomenclature, baerClockId, baerName, baerEmail, reason, certTag);
				System.out.println("fastPathResponse: '" + fastPathResponse + "'");

				try{
					//Verify for good return by checking for integer value
					Integer recNo = new Integer(fastPathResponse);
					EIIbeanResults.setReturnCode(0);  // Success
				} catch(NumberFormatException nfe){
					EIIbeanResults.setReturnCode(1);  // Not a valid Integer value
				}
				EIIbeanResults.setReturnMsg(fastPathResponse);
			} catch(Exception e) {
				EIIbeanResults.setReturnCode(1);
				if (e.getMessage().length() == 0){
					EIIbeanResults.setReturnMsg("SOAP Call to XClass WebService failed");
				} else {
					EIIbeanResults.setReturnMsg("SOAP Call to XClass WebService failed; " + e.getMessage());
				}
				System.out.println(e);			
			}			
		}

		return EIIbeanResults;
	} //End Method RequestFastPath

	private String getEnvironment() {
		String env = "";

		Properties sysprop = System.getProperties();
		if (sysprop.getProperty("pw.server.environment") == null)   //localhost
		{
//			env = "local";
			env = "devl";
			System.out.println("CallXClassWebServicePW.getEnvironment:   setting env to " + env + " as pw.server.environment was null");
		}
		else
		{
			env = sysprop.getProperty("pw.server.environment");
			System.out.println("CallXClassWebServicePW.getEnvironment:   on server, pw.server.environment = "+ env);			
		}

		if (!env.toUpperCase().equalsIgnoreCase("local"))
		{
			if (env.toUpperCase().equalsIgnoreCase("DEVL"))
			{
				env = "devl";
			}
			else if (env.toUpperCase().equalsIgnoreCase("TEST"))
			{
				env = "test";
			}
			else if (env.toUpperCase().equalsIgnoreCase("PROD"))
			{
				env = "prod";
			}
		}
		return env;
	} //End Method getEnvironment

	public void getPropertiesInfo(String propertyFile) {
		ResourceBundle rb = null;
		rb = ResourceBundle.getBundle(propertyFile);
		String ldapDN = null;

		url1 = rb.getString("XCLASS_HOST");
		ldapDN = rb.getString("APP_USER");  		// returns something like "uid=pw_solumina_g7,ou=ApplicationIDs,o=utc.com"
		ldapIDpw = rb.getString("APP_PWD");
		String[] ldapDNArray = ldapDN.split(","); 	// separate based on comma's
		int ldapDNArrayLen = ldapDNArray.length;
		for (int i = 0; i < ldapDNArrayLen;i++){
			if (ldapDNArray[i].contains("uid=")){	// if attribute is IUD
				String[] ldapDNArrayUID = ldapDNArray[i].split("=");
				ldapID = ldapDNArrayUID[1];			// Get actual LDAP user id
				i = ldapDNArrayLen;
			}
		}
	} //End Method getPropertiesInfo

	public void getLDAPInfo(String ExternalPropsKey) throws Exception{

		Map extPropsMap = commDao.getExternalProps(ExternalPropsKey);

		if (extPropsMap == null){
			String msg = new String("XClass properties not found in table PWUST_EXT_SYS_PROPERTIES!!!");
			System.out.println(msg);
			throw new Exception(msg);
		}

		String ldapDN = null;

		url1 = (String) extPropsMap.get("XCLASS_HOST");
		ldapDN = (String) extPropsMap.get("LDAP_USER");
		ldapIDpw = StringUtil.decrypt((String) extPropsMap.get("LDAP_PWD"));

		String[] ldapDNArray = ldapDN.split(","); 	// separate based on comma's
		int ldapDNArrayLen = ldapDNArray.length;
		for (int i = 0; i < ldapDNArrayLen;i++){
			if (ldapDNArray[i].contains("uid=")){	// if attribute is IUD
				String[] ldapDNArrayUID = ldapDNArray[i].split("=");
				ldapID = ldapDNArrayUID[1];			// Get actual LDAP user id
				i = ldapDNArrayLen;
			}
		}
	} //End Method getPropertiesInfo

}//End Class CallEClassWebServicePW
