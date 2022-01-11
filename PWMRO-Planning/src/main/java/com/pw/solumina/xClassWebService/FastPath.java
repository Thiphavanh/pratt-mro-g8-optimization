/**
 * FastPath.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.pw.solumina.xClassWebService;

public class FastPath  implements java.io.Serializable {
    private AuthHeader auth;

    private java.math.BigDecimal prevRecNumber;

    private java.lang.String itemId;

    private java.lang.String category;

    private java.lang.String type;

    private java.lang.String revision;

    private java.lang.String nomenclature;

    private java.lang.String baerClockId;

    private java.lang.String baerName;

    private java.lang.String baerEmail;

    private java.lang.String reason;

    private java.lang.String certificationTag;

    public FastPath() {
    }

    public FastPath(
           AuthHeader auth,
           java.math.BigDecimal prevRecNumber,
           java.lang.String itemId,
           java.lang.String category,
           java.lang.String type,
           java.lang.String revision,
           java.lang.String nomenclature,
           java.lang.String baerClockId,
           java.lang.String baerName,
           java.lang.String baerEmail,
           java.lang.String reason,
           java.lang.String certificationTag) {
           this.auth = auth;
           this.prevRecNumber = prevRecNumber;
           this.itemId = itemId;
           this.category = category;
           this.type = type;
           this.revision = revision;
           this.nomenclature = nomenclature;
           this.baerClockId = baerClockId;
           this.baerName = baerName;
           this.baerEmail = baerEmail;
           this.reason = reason;
           this.certificationTag = certificationTag;
    }


    /**
     * Gets the auth value for this FastPath.
     * 
     * @return auth
     */
    public AuthHeader getAuth() {
        return auth;
    }


    /**
     * Sets the auth value for this FastPath.
     * 
     * @param auth
     */
    public void setAuth(AuthHeader auth) {
        this.auth = auth;
    }


    /**
     * Gets the prevRecNumber value for this FastPath.
     * 
     * @return prevRecNumber
     */
    public java.math.BigDecimal getPrevRecNumber() {
        return prevRecNumber;
    }


    /**
     * Sets the prevRecNumber value for this FastPath.
     * 
     * @param prevRecNumber
     */
    public void setPrevRecNumber(java.math.BigDecimal prevRecNumber) {
        this.prevRecNumber = prevRecNumber;
    }


    /**
     * Gets the itemId value for this FastPath.
     * 
     * @return itemId
     */
    public java.lang.String getItemId() {
        return itemId;
    }


    /**
     * Sets the itemId value for this FastPath.
     * 
     * @param itemId
     */
    public void setItemId(java.lang.String itemId) {
        this.itemId = itemId;
    }


    /**
     * Gets the category value for this FastPath.
     * 
     * @return category
     */
    public java.lang.String getCategory() {
        return category;
    }


    /**
     * Sets the category value for this FastPath.
     * 
     * @param category
     */
    public void setCategory(java.lang.String category) {
        this.category = category;
    }


    /**
     * Gets the type value for this FastPath.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this FastPath.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the revision value for this FastPath.
     * 
     * @return revision
     */
    public java.lang.String getRevision() {
        return revision;
    }


    /**
     * Sets the revision value for this FastPath.
     * 
     * @param revision
     */
    public void setRevision(java.lang.String revision) {
        this.revision = revision;
    }


    /**
     * Gets the nomenclature value for this FastPath.
     * 
     * @return nomenclature
     */
    public java.lang.String getNomenclature() {
        return nomenclature;
    }


    /**
     * Sets the nomenclature value for this FastPath.
     * 
     * @param nomenclature
     */
    public void setNomenclature(java.lang.String nomenclature) {
        this.nomenclature = nomenclature;
    }


    /**
     * Gets the baerClockId value for this FastPath.
     * 
     * @return baerClockId
     */
    public java.lang.String getBaerClockId() {
        return baerClockId;
    }


    /**
     * Sets the baerClockId value for this FastPath.
     * 
     * @param baerClockId
     */
    public void setBaerClockId(java.lang.String baerClockId) {
        this.baerClockId = baerClockId;
    }


    /**
     * Gets the baerName value for this FastPath.
     * 
     * @return baerName
     */
    public java.lang.String getBaerName() {
        return baerName;
    }


    /**
     * Sets the baerName value for this FastPath.
     * 
     * @param baerName
     */
    public void setBaerName(java.lang.String baerName) {
        this.baerName = baerName;
    }


    /**
     * Gets the baerEmail value for this FastPath.
     * 
     * @return baerEmail
     */
    public java.lang.String getBaerEmail() {
        return baerEmail;
    }


    /**
     * Sets the baerEmail value for this FastPath.
     * 
     * @param baerEmail
     */
    public void setBaerEmail(java.lang.String baerEmail) {
        this.baerEmail = baerEmail;
    }


    /**
     * Gets the reason value for this FastPath.
     * 
     * @return reason
     */
    public java.lang.String getReason() {
        return reason;
    }


    /**
     * Sets the reason value for this FastPath.
     * 
     * @param reason
     */
    public void setReason(java.lang.String reason) {
        this.reason = reason;
    }


    /**
     * Gets the certificationTag value for this FastPath.
     * 
     * @return certificationTag
     */
    public java.lang.String getCertificationTag() {
        return certificationTag;
    }


    /**
     * Sets the certificationTag value for this FastPath.
     * 
     * @param certificationTag
     */
    public void setCertificationTag(java.lang.String certificationTag) {
        this.certificationTag = certificationTag;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FastPath)) return false;
        FastPath other = (FastPath) obj;
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
            ((this.prevRecNumber==null && other.getPrevRecNumber()==null) || 
             (this.prevRecNumber!=null &&
              this.prevRecNumber.equals(other.getPrevRecNumber()))) &&
            ((this.itemId==null && other.getItemId()==null) || 
             (this.itemId!=null &&
              this.itemId.equals(other.getItemId()))) &&
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
            ((this.reason==null && other.getReason()==null) || 
             (this.reason!=null &&
              this.reason.equals(other.getReason()))) &&
            ((this.certificationTag==null && other.getCertificationTag()==null) || 
             (this.certificationTag!=null &&
              this.certificationTag.equals(other.getCertificationTag())));
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
        if (getPrevRecNumber() != null) {
            _hashCode += getPrevRecNumber().hashCode();
        }
        if (getItemId() != null) {
            _hashCode += getItemId().hashCode();
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
        if (getReason() != null) {
            _hashCode += getReason().hashCode();
        }
        if (getCertificationTag() != null) {
            _hashCode += getCertificationTag().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FastPath.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("PW.XClass", ">FastPath"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auth");
        elemField.setXmlName(new javax.xml.namespace.QName("PW.XClass", "auth"));
        elemField.setXmlType(new javax.xml.namespace.QName("PW.XClass", "AuthHeader"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prevRecNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("PW.XClass", "prevRecNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
        elemField.setFieldName("reason");
        elemField.setXmlName(new javax.xml.namespace.QName("PW.XClass", "reason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("certificationTag");
        elemField.setXmlName(new javax.xml.namespace.QName("PW.XClass", "certificationTag"));
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
