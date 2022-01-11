package com.pw.solumina.xClassWebService;

public class XClassWS_SoapProxy implements com.pw.solumina.xClassWebService.XClassWS_Soap {
  private String _endpoint = null;
  private com.pw.solumina.xClassWebService.XClassWS_Soap xClassWS_Soap = null;
  
  public XClassWS_SoapProxy() {
    _initXClassWS_SoapProxy();
  }
  
  public XClassWS_SoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initXClassWS_SoapProxy();
  }
  
  private void _initXClassWS_SoapProxy() {
    try {
      xClassWS_Soap = (new com.pw.solumina.xClassWebService.XClassWSLocator()).getXClassWSSoap();
      if (xClassWS_Soap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)xClassWS_Soap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)xClassWS_Soap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (xClassWS_Soap != null)
      ((javax.xml.rpc.Stub)xClassWS_Soap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.pw.solumina.xClassWebService.XClassWS_Soap getXClassWS_Soap() {
    if (xClassWS_Soap == null)
      _initXClassWS_SoapProxy();
    return xClassWS_Soap;
  }
  
  public com.pw.solumina.xClassWebService.GetItemInfoResponseGetItemInfoResult getItemInfo(com.pw.solumina.xClassWebService.AuthHeader auth, java.lang.String itemId, java.lang.String category, java.lang.String type, java.lang.String revision, java.lang.String nomenclature) throws java.rmi.RemoteException{
    if (xClassWS_Soap == null)
      _initXClassWS_SoapProxy();
    return xClassWS_Soap.getItemInfo(auth, itemId, category, type, revision, nomenclature);
  }
  
  public java.lang.String newRequest(com.pw.solumina.xClassWebService.AuthHeader auth, java.lang.String itemId, java.lang.String cageCode, java.lang.String category, java.lang.String type, java.lang.String revision, java.lang.String nomenclature, java.lang.String baerClockId, java.lang.String baerName, java.lang.String baerEmail, java.lang.String functionalArea, java.lang.String businessUnit) throws java.rmi.RemoteException{
    if (xClassWS_Soap == null)
      _initXClassWS_SoapProxy();
    return xClassWS_Soap.newRequest(auth, itemId, cageCode, category, type, revision, nomenclature, baerClockId, baerName, baerEmail, functionalArea, businessUnit);
  }
  
  public java.lang.String fastPath(com.pw.solumina.xClassWebService.AuthHeader auth, java.math.BigDecimal prevRecNumber, java.lang.String itemId, java.lang.String category, java.lang.String type, java.lang.String revision, java.lang.String nomenclature, java.lang.String baerClockId, java.lang.String baerName, java.lang.String baerEmail, java.lang.String reason, java.lang.String certificationTag) throws java.rmi.RemoteException{
    if (xClassWS_Soap == null)
      _initXClassWS_SoapProxy();
    return xClassWS_Soap.fastPath(auth, prevRecNumber, itemId, category, type, revision, nomenclature, baerClockId, baerName, baerEmail, reason, certificationTag);
  }
  
  public java.lang.String cancelRequest(com.pw.solumina.xClassWebService.AuthHeader auth, java.math.BigDecimal recNumber, java.lang.String archivedBy, java.lang.String archivedReason) throws java.rmi.RemoteException{
    if (xClassWS_Soap == null)
      _initXClassWS_SoapProxy();
    return xClassWS_Soap.cancelRequest(auth, recNumber, archivedBy, archivedReason);
  }
  
  
}