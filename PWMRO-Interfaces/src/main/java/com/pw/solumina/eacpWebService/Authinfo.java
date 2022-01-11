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
*  File:    Authinfo.java
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

public class Authinfo  implements java.io.Serializable {

	private java.lang.String authorizationNo;
    private java.lang.String authorizationType;
    private java.lang.String IRN;
    private java.lang.String businessUnit;
    private java.lang.String expirationDate;
    private java.lang.String USGApprovalDate;
    private java.lang.String terminationDate;
    private java.lang.String ACESite;
    private java.lang.String status;
    private java.lang.String xALTRecordNo;

    public Authinfo() {
    }

    public Authinfo(
           java.lang.String authorizationNo,
           java.lang.String authorizationType,
           java.lang.String IRN,
           java.lang.String businessUnit,
           java.lang.String expirationDate,
           java.lang.String USGApprovalDate,
           java.lang.String terminationDate,
           java.lang.String ACESite,
           java.lang.String status,
           java.lang.String xALTRecordNo) 
    {
           this.authorizationNo = authorizationNo;
           this.authorizationType = authorizationType;
           this.IRN = IRN;
           this.businessUnit = businessUnit;
           this.expirationDate = expirationDate;
           this.USGApprovalDate = USGApprovalDate;
           this.terminationDate = terminationDate;
           this.ACESite = ACESite;
           this.status = status;
           this.xALTRecordNo = xALTRecordNo;
    }


    /**
     * Gets the authorizationNo value for this Authinfo.
     * 
     * @return authorizationNo
     */
    public java.lang.String getAuthorizationNo() {
        return authorizationNo;
    }


    /**
     * Sets the authorizationNo value for this Authinfo.
     * 
     * @param authorizationNo
     */
    public void setAuthorizationNo(java.lang.String authorizationNo) {
        this.authorizationNo = authorizationNo;
    }


    /**
     * Gets the authorizationType value for this Authinfo.
     * 
     * @return authorizationType
     */
    public java.lang.String getAuthorizationType() {
        return authorizationType;
    }


    /**
     * Sets the authorizationType value for this Authinfo.
     * 
     * @param authorizationType
     */
    public void setAuthorizationType(java.lang.String authorizationType) {
        this.authorizationType = authorizationType;
    }


    /**
     * Gets the IRN value for this Authinfo.
     * 
     * @return IRN
     */
    public java.lang.String getIRN() {
        return IRN;
    }


    /**
     * Sets the IRN value for this Authinfo.
     * 
     * @param IRN
     */
    public void setIRN(java.lang.String IRN) {
        this.IRN = IRN;
    }


    /**
     * Gets the businessUnit value for this Authinfo.
     * 
     * @return businessUnit
     */
    public java.lang.String getBusinessUnit() {
        return businessUnit;
    }


    /**
     * Sets the businessUnit value for this Authinfo.
     * 
     * @param businessUnit
     */
    public void setBusinessUnit(java.lang.String businessUnit) {
        this.businessUnit = businessUnit;
    }


    /**
     * Gets the expirationDate value for this Authinfo.
     * 
     * @return expirationDate
     */
    public java.lang.String getExpirationDate() {
        return expirationDate;
    }


    /**
     * Sets the expirationDate value for this Authinfo.
     * 
     * @param expirationDate
     */
    public void setExpirationDate(java.lang.String expirationDate) {
        this.expirationDate = expirationDate;
    }


    /**
     * Gets the USGApprovalDate value for this Authinfo.
     * 
     * @return USGApprovalDate
     */
    public java.lang.String getUSGApprovalDate() {
        return USGApprovalDate;
    }


    /**
     * Sets the USGApprovalDate value for this Authinfo.
     * 
     * @param USGApprovalDate
     */
    public void setUSGApprovalDate(java.lang.String USGApprovalDate) {
        this.USGApprovalDate = USGApprovalDate;
    }


    /**
     * Gets the terminationDate value for this Authinfo.
     * 
     * @return terminationDate
     */
    public java.lang.String getTerminationDate() {
        return terminationDate;
    }


    /**
     * Sets the terminationDate value for this Authinfo.
     * 
     * @param terminationDate
     */
    public void setTerminationDate(java.lang.String terminationDate) {
        this.terminationDate = terminationDate;
    }


    /**
     * Gets the ACESite value for this Authinfo.
     * 
     * @return ACESite
     */
    public java.lang.String getACESite() {
        return ACESite;
    }


    /**
     * Sets the ACESite value for this Authinfo.
     * 
     * @param ACESite
     */
    public void setACESite(java.lang.String ACESite) {
        this.ACESite = ACESite;
    }


    /**
     * Gets the status value for this Authinfo.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this Authinfo.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the xALTRecordNo value for this Authinfo.
     * 
     * @return xALTRecordNo
     */
    public java.lang.String getXALTRecordNo() {
        return xALTRecordNo;
    }


    /**
     * Sets the xALTRecordNo value for this Authinfo.
     * 
     * @param xALTRecordNo
     */
    public void setXALTRecordNo(java.lang.String xALTRecordNo) {
        this.xALTRecordNo = xALTRecordNo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Authinfo)) return false;
        Authinfo other = (Authinfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.authorizationNo==null && other.getAuthorizationNo()==null) || 
             (this.authorizationNo!=null &&
              this.authorizationNo.equals(other.getAuthorizationNo()))) &&
            ((this.authorizationType==null && other.getAuthorizationType()==null) || 
             (this.authorizationType!=null &&
              this.authorizationType.equals(other.getAuthorizationType()))) &&
            ((this.IRN==null && other.getIRN()==null) || 
             (this.IRN!=null &&
              this.IRN.equals(other.getIRN()))) &&
            ((this.businessUnit==null && other.getBusinessUnit()==null) || 
             (this.businessUnit!=null &&
              this.businessUnit.equals(other.getBusinessUnit()))) &&
            ((this.expirationDate==null && other.getExpirationDate()==null) || 
             (this.expirationDate!=null &&
              this.expirationDate.equals(other.getExpirationDate()))) &&
            ((this.USGApprovalDate==null && other.getUSGApprovalDate()==null) || 
             (this.USGApprovalDate!=null &&
              this.USGApprovalDate.equals(other.getUSGApprovalDate()))) &&
            ((this.terminationDate==null && other.getTerminationDate()==null) || 
             (this.terminationDate!=null &&
              this.terminationDate.equals(other.getTerminationDate()))) &&
            ((this.ACESite==null && other.getACESite()==null) || 
             (this.ACESite!=null &&
              this.ACESite.equals(other.getACESite()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.xALTRecordNo==null && other.getXALTRecordNo()==null) || 
             (this.xALTRecordNo!=null &&
              this.xALTRecordNo.equals(other.getXALTRecordNo())));
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
        if (getAuthorizationNo() != null) {
            _hashCode += getAuthorizationNo().hashCode();
        }
        if (getAuthorizationType() != null) {
            _hashCode += getAuthorizationType().hashCode();
        }
        if (getIRN() != null) {
            _hashCode += getIRN().hashCode();
        }
        if (getBusinessUnit() != null) {
            _hashCode += getBusinessUnit().hashCode();
        }
        if (getExpirationDate() != null) {
            _hashCode += getExpirationDate().hashCode();
        }
        if (getUSGApprovalDate() != null) {
            _hashCode += getUSGApprovalDate().hashCode();
        }
        if (getTerminationDate() != null) {
            _hashCode += getTerminationDate().hashCode();
        }
        if (getACESite() != null) {
            _hashCode += getACESite().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getXALTRecordNo() != null) {
            _hashCode += getXALTRecordNo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Authinfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "Authinfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authorizationNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "AuthorizationNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authorizationType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "AuthorizationType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IRN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "IRN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("businessUnit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "BusinessUnit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expirationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "ExpirationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("USGApprovalDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "USGApprovalDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("terminationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "TerminationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACESite");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "ACESite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XALTRecordNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "xALTRecordNo"));
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

