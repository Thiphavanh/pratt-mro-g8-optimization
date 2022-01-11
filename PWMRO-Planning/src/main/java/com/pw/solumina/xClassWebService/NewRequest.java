/**
 * NewRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.pw.solumina.xClassWebService;

public class NewRequest  implements java.io.Serializable {
    private AuthHeader auth;

    private java.lang.String itemId;

    private java.lang.String cageCode;

    private java.lang.String category;

    private java.lang.String type;

    private java.lang.String revision;

    private java.lang.String nomenclature;

    private java.lang.String baerClockId;

    private java.lang.String baerName;

    private java.lang.String baerEmail;

    private java.lang.String functionalArea;

    private java.lang.String businessUnit;

    public NewRequest() {
    }

    public NewRequest(
           AuthHeader auth,
           java.lang.String itemId,
           java.lang.String cageCode,
           java.lang.String category,
           java.lang.String type,
           java.lang.String revision,
           java.lang.String nomenclature,
           java.lang.String baerClockId,
           java.lang.String baerName,
           java.lang.String baerEmail,
           java.lang.String functionalArea,
           java.lang.String businessUnit) {
           this.auth = auth;
           this.itemId = itemId;
           this.cageCode = cageCode;
           this.category = category;
           this.type = type;
           this.revision = revision;
           this.nomenclature = nomenclature;
           this.baerClockId = baerClockId;
           this.baerName = baerName;
           this.baerEmail = baerEmail;
           this.functionalArea = functionalArea;
           this.businessUnit = businessUnit;
    }


    /**
     * Gets the auth value for this NewRequest.
     * 
     * @return auth
     */
    public AuthHeader getAuth() {
        return auth;
    }


    /**
     * Sets the auth value for this NewRequest.
     * 
     * @param auth
     */
    public void setAuth(AuthHeader auth) {
        this.auth = auth;
    }


    /**
     * Gets the itemId value for this NewRequest.
     * 
     * @return itemId
     */
    public java.lang.String getItemId() {
        return itemId;
    }


    /**
     * Sets the itemId value for this NewRequest.
     * 
     * @param itemId
     */
    public void setItemId(java.lang.String itemId) {
        this.itemId = itemId;
    }


    /**
     * Gets the cageCode value for this NewRequest.
     * 
     * @return cageCode
     */
    public java.lang.String getCageCode() {
        return cageCode;
    }


    /**
     * Sets the cageCode value for this NewRequest.
     * 
     * @param cageCode
     */
    public void setCageCode(java.lang.String cageCode) {
        this.cageCode = cageCode;
    }


    /**
     * Gets the category value for this NewRequest.
     * 
     * @return category
     */
    public java.lang.String getCategory() {
        return category;
    }


    /**
     * Sets the category value for this NewRequest.
     * 
     * @param category
     */
    public void setCategory(java.lang.String category) {
        this.category = category;
    }


    /**
     * Gets the type value for this NewRequest.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this NewRequest.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the revision value for this NewRequest.
     * 
     * @return revision
     */
    public java.lang.String getRevision() {
        return revision;
    }


    /**
     * Sets the revision value for this NewRequest.
     * 
     * @param revision
     */
    public void setRevision(java.lang.String revision) {
        this.revision = revision;
    }


    /**
     * Gets the nomenclature value for this NewRequest.
     * 
     * @return nomenclature
     */
    public java.lang.String getNomenclature() {
        return nomenclature;
    }


    /**
     * Sets the nomenclature value for this NewRequest.
     * 
     * @param nomenclature
     */
    public void setNomenclature(java.lang.String nomenclature) {
        this.nomenclature = nomenclature;
    }


    /**
     * Gets the baerClockId value for this NewRequest.
     * 
     * @return baerClockId
     */
    public java.lang.String getBaerClockId() {
        return baerClockId;
    }


    /**
     * Sets the baerClockId value for this NewRequest.
     * 
     * @param baerClockId
     */
    public void setBaerClockId(java.lang.String baerClockId) {
        this.baerClockId = baerClockId;
    }


    /**
     * Gets the baerName value for this NewRequest.
     * 
     * @return baerName
     */
    public java.lang.String getBaerName() {
        return baerName;
    }


    /**
     * Sets the baerName value for this NewRequest.
     * 
     * @param baerName
     */
    public void setBaerName(java.lang.String baerName) {
        this.baerName = baerName;
    }


    /**
     * Gets the baerEmail value for this NewRequest.
     * 
     * @return baerEmail
     */
    public java.lang.String getBaerEmail() {
        return baerEmail;
    }


    /**
     * Sets the baerEmail value for this NewRequest.
     * 
     * @param baerEmail
     */
    public void setBaerEmail(java.lang.String baerEmail) {
        this.baerEmail = baerEmail;
    }


    /**
     * Gets the functionalArea value for this NewRequest.
     * 
     * @return functionalArea
     */
    public java.lang.String getFunctionalArea() {
        return functionalArea;
    }


    /**
     * Sets the functionalArea value for this NewRequest.
     * 
     * @param functionalArea
     */
    public void setFunctionalArea(java.lang.String functionalArea) {
        this.functionalArea = functionalArea;
    }


    /**
     * Gets the businessUnit value for this NewRequest.
     * 
     * @return businessUnit
     */
    public java.lang.String getBusinessUnit() {
        return businessUnit;
    }


    /**
     * Sets the businessUnit value for this NewRequest.
     * 
     * @param businessUnit
     */
    public void setBusinessUnit(java.lang.String businessUnit) {
        this.businessUnit = businessUnit;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NewRequest)) return false;
        NewRequest other = (NewRequest) obj;
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
            ((this.itemId==null && other.getItemId()==null) || 
             (this.itemId!=null &&
              this.itemId.equals(other.getItemId()))) &&
            ((this.cageCode==null && other.getCageCode()==null) || 
             (this.cageCode!=null &&
              this.cageCode.equals(other.getCageCode()))) &&
            ((this.category==null && other.getCategory()==null) || 
             (this.category!=null &&
              this.category.equals(other.getCategory()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.revision==null && other.getRevision()==null) || 
             (this.revision!=null &&
              this.revision.equals(other.getRevision()))) &&
            ((this.nomenclature==null && other.getNomenclature()==null) || 
             (this.nomenclature!=null &&
              this.nomenclature.equals(other.getNomenclature()))) &&
            ((this.baerClockId==null && other.getBaerClockId()==null) || 
             (this.baerClockId!=null &&
              this.baerClockId.equals(other.getBaerClockId()))) &&
            ((this.baerName==null && other.getBaerName()==null) || 
             (this.baerName!=null &&
              this.baerName.equals(other.getBaerName()))) &&
            ((this.baerEmail==null && other.getBaerEmail()==null) || 
             (this.baerEmail!=null &&
              this.baerEmail.equals(other.getBaerEmail()))) &&
            ((this.functionalArea==null && other.getFunctionalArea()==null) || 
             (this.functionalArea!=null &&
              this.functionalArea.equals(other.getFunctionalArea()))) &&
            ((this.businessUnit==null && other.getBusinessUnit()==null) || 
             (this.businessUnit!=null &&
              this.businessUnit.equals(other.getBusinessUnit())));
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
        if (getItemId() != null) {
            _hashCode += getItemId().hashCode();
        }
        if (getCageCode() != null) {
            _hashCode += getCageCode().hashCode();
        }
        if (getCategory() != null) {
            _hashCode += getCategory().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getRevision() != null) {
            _hashCode += getRevision().hashCode();
        }
        if (getNomenclature() != null) {
            _hashCode += getNomenclature().hashCode();
        }
        if (getBaerClockId() != null) {
            _hashCode += getBaerClockId().hashCode();
        }
        if (getBaerName() != null) {
            _hashCode += getBaerName().hashCode();
        }
        if (getBaerEmail() != null) {
            _hashCode += getBaerEmail().hashCode();
        }
        if (getFunctionalArea() != null) {
            _hashCode += getFunctionalArea().hashCode();
        }
        if (getBusinessUnit() != null) {
            _hashCode += getBusinessUnit().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NewRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("PW.XClass", ">NewRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auth");
        elemField.setXmlName(new javax.xml.namespace.QName("PW.XClass", "auth"));
        elemField.setXmlType(new javax.xml.namespace.QName("PW.XClass", "AuthHeader"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("itemId");
        elemField.setXmlName(new javax.xml.namespace.QName("PW.XClass", "itemId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cageCode");
        elemField.setXmlName(new javax.xml.namespace.QName("PW.XClass", "cageCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("category");
        elemField.setXmlName(new javax.xml.namespace.QName("PW.XClass", "category"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("PW.XClass", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("revision");
        elemField.setXmlName(new javax.xml.namespace.QName("PW.XClass", "revision"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomenclature");
        elemField.setXmlName(new javax.xml.namespace.QName("PW.XClass", "nomenclature"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("baerClockId");
        elemField.setXmlName(new javax.xml.namespace.QName("PW.XClass", "baerClockId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("baerName");
        elemField.setXmlName(new javax.xml.namespace.QName("PW.XClass", "baerName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("baerEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("PW.XClass", "baerEmail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("functionalArea");
        elemField.setXmlName(new javax.xml.namespace.QName("PW.XClass", "functionalArea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("businessUnit");
        elemField.setXmlName(new javax.xml.namespace.QName("PW.XClass", "businessUnit"));
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
