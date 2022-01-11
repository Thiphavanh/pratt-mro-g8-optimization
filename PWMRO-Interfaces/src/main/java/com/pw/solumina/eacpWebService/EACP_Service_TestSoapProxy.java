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

public class EACP_Service_TestSoapProxy implements com.pw.solumina.eacpWebService.EACP_Service_TestSoap {
	  private String _endpoint = null;
	  private com.pw.solumina.eacpWebService.EACP_Service_TestSoap eACP_Service_TestSoap = null;
	  
	  public EACP_Service_TestSoapProxy() {
	    _initEACP_Service_TestSoapProxy();
	  }
	  
	  public EACP_Service_TestSoapProxy(String endpoint) {
	    _endpoint = endpoint;
	    _initEACP_Service_TestSoapProxy();
	  }
	  
	  private void _initEACP_Service_TestSoapProxy() {
	    try {
	      eACP_Service_TestSoap = (new com.pw.solumina.eacpWebService.EACP_Service_TestLocator()).geteACP_Service_TestSoap();
	      if (eACP_Service_TestSoap != null) {
	        if (_endpoint != null)
	          ((javax.xml.rpc.Stub)eACP_Service_TestSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
	        else
	          _endpoint = (String)((javax.xml.rpc.Stub)eACP_Service_TestSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
	      }
	      
	    }
	    catch (javax.xml.rpc.ServiceException serviceException) {}
	  }
	  
	  public String getEndpoint() {
	    return _endpoint;
	  }
	  
	  public void setEndpoint(String endpoint) {
	    _endpoint = endpoint;
	    if (eACP_Service_TestSoap != null)
	      ((javax.xml.rpc.Stub)eACP_Service_TestSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
	    
	  }
	  
	  public com.pw.solumina.eacpWebService.EACP_Service_TestSoap getEACP_Service_TestSoap() {
	    if (eACP_Service_TestSoap == null)
	      _initEACP_Service_TestSoapProxy();
	    return eACP_Service_TestSoap;
	  }
	  
	  public com.pw.solumina.eacpWebService.Authinfo[] getAuthInfo(java.lang.String clockId) throws java.rmi.RemoteException{
	    if (eACP_Service_TestSoap == null)
	      _initEACP_Service_TestSoapProxy();
	    return eACP_Service_TestSoap.getAuthInfo(clockId);
	  }
	  
	  
	}