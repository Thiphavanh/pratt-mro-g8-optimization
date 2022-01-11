/**
 * NewRequestResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.pw.solumina.xClassWebService;

public class NewRequestResponse  implements java.io.Serializable {
    private java.lang.String newRequestResult;

    public NewRequestResponse() {
    }

    public NewRequestResponse(
           java.lang.String newRequestResult) {
           this.newRequestResult = newRequestResult;
    }


    /**
     * Gets the newRequestResult value for this NewRequestResponse.
     * 
     * @return newRequestResult
     */
    public java.lang.String getNewRequestResult() {
        return newRequestResult;
    }


    /**
     * Sets the newRequestResult value for this NewRequestResponse.
     * 
     * @param newRequestResult
     */
    public void setNewRequestResult(java.lang.String newRequestResult) {
        this.newRequestResult = newRequestResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NewRequestResponse)) return false;
        NewRequestResponse other = (NewRequestResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.newRequestResult==null && other.getNewRequestResult()==null) || 
             (this.newRequestResult!=null &&
              this.newRequestResult.equals(other.getNewRequestResult())));
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
        if (getNewRequestResult() != null) {
            _hashCode += getNewRequestResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NewRequestResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("PW.XClass", ">NewRequestResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newRequestResult");
        elemField.setXmlName(new javax.xml.namespace.QName("PW.XClass", "NewRequestResult"));
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
