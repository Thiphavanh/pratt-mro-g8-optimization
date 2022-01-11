/**
 * CancelRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.pw.solumina.xClassWebService;

public class CancelRequest  implements java.io.Serializable {
    private AuthHeader auth;

    private java.math.BigDecimal recNumber;

    private java.lang.String archivedBy;

    private java.lang.String archivedReason;

    public CancelRequest() {
    }

    public CancelRequest(
           AuthHeader auth,
           java.math.BigDecimal recNumber,
           java.lang.String archivedBy,
           java.lang.String archivedReason) {
           this.auth = auth;
           this.recNumber = recNumber;
           this.archivedBy = archivedBy;
           this.archivedReason = archivedReason;
    }


    /**
     * Gets the auth value for this CancelRequest.
     * 
     * @return auth
     */
    public AuthHeader getAuth() {
        return auth;
    }


    /**
     * Sets the auth value for this CancelRequest.
     * 
     * @param auth
     */
    public void setAuth(AuthHeader auth) {
        this.auth = auth;
    }


    /**
     * Gets the recNumber value for this CancelRequest.
     * 
     * @return recNumber
     */
    public java.math.BigDecimal getRecNumber() {
        return recNumber;
    }


    /**
     * Sets the recNumber value for this CancelRequest.
     * 
     * @param recNumber
     */
    public void setRecNumber(java.math.BigDecimal recNumber) {
        this.recNumber = recNumber;
    }


    /**
     * Gets the archivedBy value for this CancelRequest.
     * 
     * @return archivedBy
     */
    public java.lang.String getArchivedBy() {
        return archivedBy;
    }


    /**
     * Sets the archivedBy value for this CancelRequest.
     * 
     * @param archivedBy
     */
    public void setArchivedBy(java.lang.String archivedBy) {
        this.archivedBy = archivedBy;
    }


    /**
     * Gets the archivedReason value for this CancelRequest.
     * 
     * @return archivedReason
     */
    public java.lang.String getArchivedReason() {
        return archivedReason;
    }


    /**
     * Sets the archivedReason value for this CancelRequest.
     * 
     * @param archivedReason
     */
    public void setArchivedReason(java.lang.String archivedReason) {
        this.archivedReason = archivedReason;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CancelRequest)) return false;
        CancelRequest other = (CancelRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.auth==null && other.getAuth()==null) || 
             (this.auth!=null &&
              this.auth.equals(other.getAuth()))) &&
            ((this.recNumber==null && other.getRecNumber()==null) || 
             (this.recNumber!=null &&
              this.recNumber.equals(other.getRecNumber()))) &&
            ((this.archivedBy==null && other.getArchivedBy()==null) || 
             (this.archivedBy!=null &&
              this.archivedBy.equals(other.getArchivedBy()))) &&
            ((this.archivedReason==null && other.getArchivedReason()==null) || 
             (this.archivedReason!=null &&
              this.archivedReason.equals(other.getArchivedReason())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAuth() != null) {
            _hashCode += getAuth().hashCode();
        }
        if (getRecNumber() != null) {
            _hashCode += getRecNumber().hashCode();
        }
        if (getArchivedBy() != null) {
            _hashCode += getArchivedBy().hashCode();
        }
        if (getArchivedReason() != null) {
            _hashCode += getArchivedReason().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CancelRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("PW.XClass", ">CancelRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auth");
        elemField.setXmlName(new javax.xml.namespace.QName("PW.XClass", "auth"));
        elemField.setXmlType(new javax.xml.namespace.QName("PW.XClass", "AuthHeader"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("PW.XClass", "recNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("archivedBy");
        elemField.setXmlName(new javax.xml.namespace.QName("PW.XClass", "archivedBy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("archivedReason");
        elemField.setXmlName(new javax.xml.namespace.QName("PW.XClass", "archivedReason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
