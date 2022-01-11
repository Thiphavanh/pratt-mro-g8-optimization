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
*  File:    EACP_Service_TestLocator.java
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

import com.pw.solumina.interfaces.dao.EACPDaoPW;

public class EACP_Service_TestLocator extends org.apache.axis.client.Service implements com.pw.solumina.eacpWebService.EACP_Service_Test {

    public EACP_Service_TestLocator() {
    }


    public EACP_Service_TestLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EACP_Service_TestLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for eACP_Service_TestSoap
    private java.lang.String eACP_Service_TestSoap_address = com.pw.solumina.interfaces.dao.EACPDaoPW.getEACPEndpoint(); 
                                                            
    public java.lang.String geteACP_Service_TestSoapAddress() {
        return eACP_Service_TestSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String eACP_Service_TestSoapWSDDServiceName = "eACP_Service_TestSoap";

    public java.lang.String geteACP_Service_TestSoapWSDDServiceName() {
        return eACP_Service_TestSoapWSDDServiceName;
    }

    public void seteACP_Service_TestSoapWSDDServiceName(java.lang.String name) {
        eACP_Service_TestSoapWSDDServiceName = name;
    }

    public com.pw.solumina.eacpWebService.EACP_Service_TestSoap geteACP_Service_TestSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(eACP_Service_TestSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return geteACP_Service_TestSoap(endpoint);
    }

    public com.pw.solumina.eacpWebService.EACP_Service_TestSoap geteACP_Service_TestSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.pw.solumina.eacpWebService.EACP_Service_TestSoapStub _stub = new com.pw.solumina.eacpWebService.EACP_Service_TestSoapStub(portAddress, this);
            _stub.setPortName(geteACP_Service_TestSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void seteACP_Service_TestSoapEndpointAddress(java.lang.String address) {
        eACP_Service_TestSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.pw.solumina.eacpWebService.EACP_Service_TestSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.pw.solumina.eacpWebService.EACP_Service_TestSoapStub _stub = new com.pw.solumina.eacpWebService.EACP_Service_TestSoapStub(new java.net.URL(eACP_Service_TestSoap_address), this);
                _stub.setPortName(geteACP_Service_TestSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("eACP_Service_TestSoap".equals(inputPortName)) {
            return geteACP_Service_TestSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://microsoft.com/webservices/", "eACP_Service_Test");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "eACP_Service_TestSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("eACP_Service_TestSoap".equals(portName)) {
            seteACP_Service_TestSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}