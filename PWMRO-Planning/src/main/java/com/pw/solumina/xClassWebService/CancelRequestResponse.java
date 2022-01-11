/**
 * CancelRequestResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.pw.solumina.xClassWebService;

public class CancelRequestResponse  implements java.io.Serializable {
    private java.lang.String cancelRequestResult;

    public CancelRequestResponse() {
    }

    public CancelRequestResponse(
           java.lang.String cancelRequestResult) {
           this.cancelRequestResult = cancelRequestResult;
    }


    /**
     * Gets the cancelRequestResult value for this CancelRequestResponse.
     * 
     * @return cancelRequestResult
     */
    public java.lang.String getCancelRequestResult() {
        return cancelRequestResult;
    }


    /**
     * Sets the cancelRequestResult value for this CancelRequestResponse.
     * 
     * @param cancelRequestResult
     */
    public void setCancelRequestResult(java.lang.String cancelRequestResult) {
        this.cancelRequestResult = cancelRequestResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CancelRequestResponse)) return false;
        CancelRequestResponse other = (CancelRequestResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cancelRequestResult==null && other.getCancelRequestResult()==null) || 
             (this.cancelRequestResult!=null &&
              this.cancelRequestResult.equals(other.getCancelRequestResult())));
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
        if (getCancelRequestResult() != null) {
            _hashCode += getCancelRequestResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CancelRequestResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("PW.XClass", ">CancelRequestResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cancelRequestResult");
        elemField.setXmlName(new javax.xml.namespace.QName("PW.XClass", "CancelRequestResult"));
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
