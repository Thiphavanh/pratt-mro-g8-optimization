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
*  File:    EACP_Service_TestSoapStub.java
* 
*  Created: 2017-10-17
* 
*  Author:  c293011
* 
*  Revision History
* 
*  Date      	Who             Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-10-17 	c293011		    Initial Release SMRO_EXPT_204
*/



package com.pw.solumina.eacpWebService;

/**
 * @author c293011
 *
 */

import java.lang.reflect.Field;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

import org.apache.axis.message.PrefixedQName;
import org.apache.axis.message.SOAPHeaderElement;
import com.pw.solumina.eacpWebService.Authinfo;
import com.pw.solumina.interfaces.dao.EACPDaoPW;


public class EACP_Service_TestSoapStub extends org.apache.axis.client.Stub implements com.pw.solumina.eacpWebService.EACP_Service_TestSoap {
	private java.util.Vector cachedSerClasses = new java.util.Vector();
	private java.util.Vector cachedSerQNames = new java.util.Vector();
	private java.util.Vector cachedSerFactories = new java.util.Vector();
	private java.util.Vector cachedDeserFactories = new java.util.Vector();

	static org.apache.axis.description.OperationDesc [] _operations;

	static {
		_operations = new org.apache.axis.description.OperationDesc[1];
		_initOperationDesc1();
	}

	private static void _initOperationDesc1(){
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("GetAuthInfo");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "ClockId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "ArrayOfAuthinfo"));
		oper.setReturnClass(com.pw.solumina.eacpWebService.Authinfo[].class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "GetAuthInfoResult"));
		param = oper.getReturnParamDesc();
		param.setItemQName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "Authinfo"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[0] = oper;

	}

	public EACP_Service_TestSoapStub() throws org.apache.axis.AxisFault {
		this(null);
	}

	public EACP_Service_TestSoapStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	public EACP_Service_TestSoapStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
		if (service == null) {
			super.service = new org.apache.axis.client.Service();
		} else {
			super.service = service;
		}
		((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
		java.lang.Class cls;
		javax.xml.namespace.QName qName;
		javax.xml.namespace.QName qName2;
		java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
		java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
		java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
		java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
		java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
		java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
		java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
		java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
		java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
		java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
		qName = new javax.xml.namespace.QName("http://microsoft.com/webservices/", "ArrayOfAuthinfo");
		cachedSerQNames.add(qName);
		cls = com.pw.solumina.eacpWebService.Authinfo[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://microsoft.com/webservices/", "Authinfo");
		qName2 = new javax.xml.namespace.QName("http://microsoft.com/webservices/", "Authinfo");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://microsoft.com/webservices/", "Authinfo");
		cachedSerQNames.add(qName);
		cls = com.pw.solumina.eacpWebService.Authinfo.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

	}

	protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
		try {
			org.apache.axis.client.Call _call = super._createCall();
			if (super.maintainSessionSet) {
				_call.setMaintainSession(super.maintainSession);
			}
			if (super.cachedUsername != null) {
				_call.setUsername(super.cachedUsername);
			}
			if (super.cachedPassword != null) {
				_call.setPassword(super.cachedPassword);
			}
			if (super.cachedEndpoint != null) {
				_call.setTargetEndpointAddress(super.cachedEndpoint);
			}
			if (super.cachedTimeout != null) {
				_call.setTimeout(super.cachedTimeout);
			}
			if (super.cachedPortName != null) {
				_call.setPortName(super.cachedPortName);
			}
			java.util.Enumeration keys = super.cachedProperties.keys();
			while (keys.hasMoreElements()) {
				java.lang.String key = (java.lang.String) keys.nextElement();
				_call.setProperty(key, super.cachedProperties.get(key));
			}
			// All the type mapping information is registered
			// when the first call is made.
			// The type mapping information is actually registered in
			// the TypeMappingRegistry of the service, which
			// is the reason why registration is only needed for the first call.
			synchronized (this) {
				if (firstCall()) {
					// must set encoding style before registering serializers
					_call.setEncodingStyle(null);
					for (int i = 0; i < cachedSerFactories.size(); ++i) {
						java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
						javax.xml.namespace.QName qName =
								(javax.xml.namespace.QName) cachedSerQNames.get(i);
						java.lang.Object x = cachedSerFactories.get(i);
						if (x instanceof Class) {
							java.lang.Class sf = (java.lang.Class)
									cachedSerFactories.get(i);
							java.lang.Class df = (java.lang.Class)
									cachedDeserFactories.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						}
						else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
							org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
									cachedSerFactories.get(i);
							org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
									cachedDeserFactories.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						}
					}
				}
			}
			return _call;
		}
		catch (java.lang.Throwable _t) {
			throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
		}
	}

	public com.pw.solumina.eacpWebService.Authinfo[] getAuthInfo(java.lang.String clockId) throws java.rmi.RemoteException {

		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[0]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://microsoft.com/webservices/GetAuthInfo");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "GetAuthInfo"));

		SOAPHeaderElement wsseSecurity = new SOAPHeaderElement(new PrefixedQName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd","Security", "wsse"));      
		wsseSecurity.setActor(null);
		wsseSecurity.setMustUnderstand(false);

		//RTT -- lookup userid and pwd
		String userid = com.pw.solumina.interfaces.dao.EACPDaoPW.getEACPUserid();
		String pwd = com.pw.solumina.interfaces.dao.EACPDaoPW.getEACPPassword();
		
		try {
			// Add usernameToken to "Security" soapHeaderElement 
			SOAPElement usernameTokenSOAPElement = wsseSecurity.addChildElement("UsernameToken");
			usernameTokenSOAPElement.addNamespaceDeclaration("wsu", "http://schemas.xmlsoap.org/ws/2002/07/utility");

			// Add username to usernameToken
			SOAPElement userNameSOAPElement = usernameTokenSOAPElement.addChildElement("Username");
			userNameSOAPElement.addTextNode(userid);  

			// Add password to usernameToken
			SOAPElement passwordSOAPElement = usernameTokenSOAPElement.addChildElement("Password");
			passwordSOAPElement.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");

			passwordSOAPElement.addTextNode(pwd);
		}
		catch (SOAPException soapException) {
			throw new RuntimeException("WSSESecurityHeaderRequestWebServiceMessageCallback", soapException);
		}

		_call.addHeader(wsseSecurity);
		setRequestHeaders(_call);
		setAttachments(_call);

		try {java.lang.Object _resp = _call.invoke(new java.lang.Object[] {clockId});

		if (_resp instanceof java.rmi.RemoteException) {
			throw (java.rmi.RemoteException)_resp;
		}
		else {
			extractAttachments(_call);
			try {
						return (com.pw.solumina.eacpWebService.Authinfo[]) _resp;
			} catch (java.lang.Exception _exception) {
				return (com.pw.solumina.eacpWebService.Authinfo[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.pw.solumina.eacpWebService.Authinfo[].class);
			}
		}
		} 
		catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

}
