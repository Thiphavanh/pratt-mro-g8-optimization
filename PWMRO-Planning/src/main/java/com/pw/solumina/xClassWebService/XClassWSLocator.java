/**
 * XClassWSLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.pw.solumina.xClassWebService;

public class XClassWSLocator extends org.apache.axis.client.Service implements com.pw.solumina.xClassWebService.XClassWS {

    public XClassWSLocator() {
    }


    public XClassWSLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public XClassWSLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for XClassWSSoap
    private java.lang.String XClassWSSoap_address = "http://webapps.pw.utc.com/xclassws/XClassWS.svc";

    public java.lang.String getXClassWSSoapAddress() {
        return XClassWSSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String XClassWSSoapWSDDServiceName = "XClassWSSoap";

    public java.lang.String getXClassWSSoapWSDDServiceName() {
        return XClassWSSoapWSDDServiceName;
    }

    public void setXClassWSSoapWSDDServiceName(java.lang.String name) {
        XClassWSSoapWSDDServiceName = name;
    }

    public com.pw.solumina.xClassWebService.XClassWS_Soap getXClassWSSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(XClassWSSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getXClassWSSoap(endpoint);
    }

    public com.pw.solumina.xClassWebService.XClassWS_Soap getXClassWSSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.pw.solumina.xClassWebService.XClassWS_SoapStub _stub = new com.pw.solumina.xClassWebService.XClassWS_SoapStub(portAddress, this);
            _stub.setPortName(getXClassWSSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setXClassWSSoapEndpointAddress(java.lang.String address) {
        XClassWSSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.pw.solumina.xClassWebService.XClassWS_Soap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.pw.solumina.xClassWebService.XClassWS_SoapStub _stub = new com.pw.solumina.xClassWebService.XClassWS_SoapStub(new java.net.URL(XClassWSSoap_address), this);
                _stub.setPortName(getXClassWSSoapWSDDServiceName());
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
        if ("XClassWSSoap".equals(inputPortName)) {
            return getXClassWSSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("PW.XClass", "XClassWS");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("PW.XClass", "XClassWSSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("XClassWSSoap".equals(portName)) {
            setXClassWSSoapEndpointAddress(address);
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
