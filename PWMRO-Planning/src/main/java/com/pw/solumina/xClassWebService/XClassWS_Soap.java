/**
 * XClassWS_Soap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.pw.solumina.xClassWebService;

public interface XClassWS_Soap extends java.rmi.Remote {

    /**
     * Returns item data
     */
    public com.pw.solumina.xClassWebService.GetItemInfoResponseGetItemInfoResult getItemInfo(com.pw.solumina.xClassWebService.AuthHeader auth, java.lang.String itemId, java.lang.String category, java.lang.String type, java.lang.String revision, java.lang.String nomenclature) throws java.rmi.RemoteException;

    /**
     * Creates a new
     * 				classification request
     */
    public java.lang.String newRequest(com.pw.solumina.xClassWebService.AuthHeader auth, java.lang.String itemId, java.lang.String cageCode, java.lang.String category, java.lang.String type, java.lang.String revision, java.lang.String nomenclature, java.lang.String baerClockId, java.lang.String baerName, java.lang.String baerEmail, java.lang.String functionalArea, java.lang.String businessUnit) throws java.rmi.RemoteException;

    /**
     * Start a FastPath
     * 				request
     */
    public java.lang.String fastPath(com.pw.solumina.xClassWebService.AuthHeader auth, java.math.BigDecimal prevRecNumber, java.lang.String itemId, java.lang.String category, java.lang.String type, java.lang.String revision, java.lang.String nomenclature, java.lang.String baerClockId, java.lang.String baerName, java.lang.String baerEmail, java.lang.String reason, java.lang.String certificationTag) throws java.rmi.RemoteException;

    /**
     * Cancels a pending
     * 				classification request
     */
    public java.lang.String cancelRequest(com.pw.solumina.xClassWebService.AuthHeader auth, java.math.BigDecimal recNumber, java.lang.String archivedBy, java.lang.String archivedReason) throws java.rmi.RemoteException;
}
