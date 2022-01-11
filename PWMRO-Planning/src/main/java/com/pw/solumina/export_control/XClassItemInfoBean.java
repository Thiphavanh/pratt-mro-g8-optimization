/*
 *  This unpublished work, first created in 2014 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2014.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    com.pw.solumina.export_control.XClassItemInfoBean.java
 * 
 *  Created: 2014-06-18
 * 
 *  Author:  T. Damble
 * 
 *  Revision History
 * 
 *  Example Item record from XClass
 * 
 *  <Item diffgr:id="Item1" msdata:rowOrder="0" diffgr:hasChanges="modified">
 *    <RECNUMBER>457404</RECNUMBER> 
 *    <REQ_NAME>Jose E Ortiz</REQ_NAME> 
 *    <REQ_ORG>pw</REQ_ORG> 
 *    <REQ_EMAIL>Jose.Ortiz2@pw.utc.com</REQ_EMAIL> 
 *    <RECDATE>2012-11-12T00:00:00-05:00</RECDATE> 
 *    <BAER_NAME>Ortiz,Jose E - XAPR114</BAER_NAME> 
 *    <BAER_EMAIL>jose.ortiz2@pw.utc.com</BAER_EMAIL> 
 *    <ITEM_IDENTIFIER><![CDATA[600084]]></ITEM_IDENTIFIER> 
 *    <CLASS_CATEGORY>Commodity</CLASS_CATEGORY> 
 *    <CLASS_TYPE>Engine</CLASS_TYPE> 
 *    <NOMENCLATURE><![CDATA[600084 ]]></NOMENCLATURE> 
 *    <CLASSIFICATION_DATE>2012-11-12T17:47:15-05:00</CLASSIFICATION_DATE> 
 *    <ITEM_DESCRIPTION><![CDATA[600084 ]]></ITEM_DESCRIPTION> 
 *    <IS_SME>TRUE</IS_SME> 
 *    <DESIGN_PW>No</DESIGN_PW> 
 *  </Item>
 *
 *  Date        Who          	Description
 *  ----------  ------------ 	---------------------------------------------------
 *  2014-06-18  T. Damble		New Development for SAIP_MD_M103.
 *  
 */
package com.pw.solumina.export_control;

public class XClassItemInfoBean {

	private String recnumber = null;
	private String reqClock = null;
	private String reqName = null;
	private String reqPhone = null;
	private String reqOrg = null;
	private String reqEmail = null;
	private String recDate = null;
	private String baerName = null;
	private String baerEmail = null;
	private String itemIdentifier = null;
	private String cageCode = null;
	private String classCategory = null;
	private String classType = null;
	private String sftVersion = null;
	private String nomenclature = null;
	private String jurisdiction = null;
	private String classification = null;
	private String classificationDate = null;
	private String exportVerification = null;
	private String verificationDate = null;
	private String itemDescription = null;
	private String isSme = null;
	private String designPW = null;
	private String jcSource = null;

	/**
	 * Default constructor
	 */
	public XClassItemInfoBean() {
		super();
		this.recnumber = "";
		this.reqClock = "";
		this.reqName = "";
		this.reqPhone = "";
		this.reqOrg = "";
		this.reqEmail = "";
		this.recDate = "";
		this.baerName = "";
		this.baerEmail = "";
		this.itemIdentifier = "";
		this.cageCode = "";
		this.classCategory = "";
		this.classType = "";
		this.sftVersion = "";
		this.nomenclature = "";
		this.jurisdiction = "";
		this.classification = "";
		this.classificationDate = "";
		this.exportVerification = "";
		this.verificationDate = "";
		this.itemDescription = "";
		this.isSme = "";
		this.designPW = "";
		this.jcSource = "";
	}


	/**
	 * @return the recnumber
	 */
	public String getRecnumber() {
		return recnumber;
	}

	/**
	 * @param the recnumber to set
	 */
	public void setRecnumber(String recnumber) {
		this.recnumber = recnumber;
	}

	/**
	 * @return the reqClock
	 */
	public String getReqClock() {
		return reqClock;
	}

	/**
	 * @param the reqClock to set
	 */
	public void setReqClock(String reqClock) {
		this.reqClock = reqClock;
	}

	/**
	 * @return the reqName
	 */
	public String getReqName() {
		return reqName;
	}

	/**
	 * @param the reqName to set
	 */
	public void setReqName(String req_name) {
		this.reqName = req_name;
	}

	/**
	 * @return the reqPhone
	 */
	public String getReqPhone() {
		return reqPhone;
	}

	/**
	 * @param the reqPhone to set
	 */
	public void setReqPhone(String reqPhone) {
		this.reqPhone = reqPhone;
	}

	/**
	 * @return the reqOrg
	 */
	public String getReqOrg() {
		return reqOrg;
	}

	/**
	 * @param the reqOrg to set
	 */
	public void setReqOrg(String req_org) {
		this.reqOrg = req_org;
	}

	/**
	 * @return the reqEmail
	 */
	public String getReqEmail() {
		return reqEmail;
	}

	/**
	 * @param the reqEmail to set
	 */
	public void setReqEmail(String req_email) {
		this.reqEmail = req_email;
	}

	/**
	 * @return the recDate
	 */
	public String getRecDate() {
		return recDate;
	}

	/**
	 * @param the recDate to set
	 */
	public void setRecDate(String recdate) {
		this.recDate = recdate;
	}

	/**
	 * @return the baerName
	 */
	public String getBaerName() {
		return baerName;
	}

	/**
	 * @param the baerName to set
	 */
	public void setBaerName(String baer_name) {
		this.baerName = baer_name;
	}

	/**
	 * @return the baerEmail
	 */
	public String getBaerEmail() {
		return baerEmail;
	}

	/**
	 * @param the baerEmail to set
	 */
	public void setBaerEmail(String baer_email) {
		this.baerEmail = baer_email;
	}

	/**
	 * @return the itemIdentifier
	 */
	public String getItemIdentifier() {
		return itemIdentifier;
	}

	/**
	 * @param the itemIdentifier to set
	 */
	public void setItemIdentifier(String item_identifier) {
		this.itemIdentifier = item_identifier;
	}

	/**
	 * @return the cageCode
	 */
	public String getCageCode() {
		return cageCode;
	}

	/**
	 * @param the cageCode to set
	 */
	public void setCageCode(String cage_code) {
		this.cageCode = cage_code;
	}

	/**
	 * @return the classCategory
	 */
	public String getClassCategory() {
		return classCategory;
	}

	/**
	 * @param the classCategory to set
	 */
	public void setClassCategory(String class_category) {
		this.classCategory = class_category;
	}

	/**
	 * @return the classType
	 */
	public String getClassType() {
		return classType;
	}

	/**
	 * @param the classType to set
	 */
	public void setClassType(String class_type) {
		this.classType = class_type;
	}

	/**
	 * @return the sftVersion
	 */
	public String getSftVersion() {
		return sftVersion;
	}

	/**
	 * @param the sftVersion to set
	 */
	public void setSftVersion(String sftVersion) {
		this.sftVersion = sftVersion;
	}

	/**
	 * @return the nomenclature
	 */
	public String getNomenclature() {
		return nomenclature;
	}

	/**
	 * @param the nomenclature to set
	 */
	public void setNomenclature(String nomenclature) {
		this.nomenclature = nomenclature;
	}

	/**
	 * @return the jurisdiction
	 */
	public String getJurisdiction() {
		return jurisdiction;
	}

	/**
	 * @param the jurisdiction to set
	 */
	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	/**
	 * @return the classification
	 */
	public String getClassification() {
		return classification;
	}

	/**
	 * @param the classification to set
	 */
	public void setClassification(String classification) {
		this.classification = classification;
	}

	/**
	 * @return the classificationDate
	 */
	public String getClassificationDate() {
		return classificationDate;
	}


	/**
	 * @param the classificationDate to set
	 */
	public void setClassificationDate(String classification_date) {
		this.classificationDate = classification_date;
	}

	/**
	 * @return the exportVerification
	 */
	public String getExportVerification() {
		return exportVerification;
	}

	/**
	 * @param the exportVerification to set
	 */
	public void setExportVerification(String exportVerification) {
		this.exportVerification = exportVerification;
	}

	/**
	 * @return the verificationDate
	 */
	public String getVerificationDate() {
		return verificationDate;
	}

	/**
	 * @param the verificationDate to set
	 */
	public void setVerificationDate(String verificationDate) {
		this.verificationDate = verificationDate;
	}

	/**
	 * @return the itemDescription
	 */
	public String getItemDescription() {
		return itemDescription;
	}


	/**
	 * @param the itemDescription to set
	 */
	public void setItemDescription(String item_description) {
		this.itemDescription = item_description;
	}


	/**
	 * @return the isSme
	 */
	public String getIsSme() {
		return isSme;
	}


	/**
	 * @param the isSme to set
	 */
	public void setIsSme(String is_sme) {
		this.isSme = is_sme;
	}


	/**
	 * @return the designPW
	 */
	public String getDesignPW() {
		return designPW;
	}


	/**
	 * @param the designPW to set
	 */
	public void setDesignPW(String design_pw) {
		this.designPW = design_pw;
	}

	/**
	 * @return the jcSource
	 */
	public String getJcSource() {
		return jcSource;
	}


	/**
	 * @param the jcSource to set
	 */
	public void setJcSource(String jc_source) {
		this.jcSource = jc_source;
	}

}