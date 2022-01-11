/*
 * This unpublished work, first created in 2019 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2019.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    T301MessageProcessor.java
 * 
 *  Created: 2019-05-24
 * 
 *  Author:  David Miron
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2019-05-24		D. Miron		Initial Release SMRO_TR_T301 - SM Order Interface
 *  								
 */
package com.pw.solumina.T301.integration.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openapplications.oagis.x9.KeysType;
import org.openapplications.oagis.x9.NameValuePairType;
import org.openapplications.oagis.x9.RecordType;
import org.openapplications.oagis.x9.SyncRecordDataAreaType;
import org.openapplications.oagis.x9.SyncRecordDocument;
import org.openapplications.oagis.x9.SyncRecordType;

import com.ibaset.common.Reference;
import com.ibaset.common.util.OAGIUtils; // G8R2SP4
import com.ibaset.solumina.integration.common.BaseMessageProcessor;
import com.ibaset.solumina.integration.exception.MappingException;
import com.ibaset.solumina.integration.exception.ParsingException;
import com.ibaset.solumina.integration.logging.MessageLogger;
import com.ibaset.solumina.integration.logging.MessageLoggerParams;
import com.pw.solumina.T301.integration.IT301MessageProcessor;
import com.pw.solumina.T301.integration.impl.T301MessageProcessor.SmOrderRecord;
import com.pw.solumina.interfaces.dao.IT301DaoPW;

enum XMLFields {
	IDOC_NUMBER,			
	MESSAGE_TYPE,
	UPDT_USERID,
	ORDER_TYPE,
	PLANT,
	NOTIFICATION,
	SALES_ORDER,
	SALES_DOC_ITEM,
	SUPERIOR_SM_ORDER,
	SM_ORDER,
	CUSTOMER_NUMBER,
	CUSTOMER_NAME,
	PURCHASE_ORDER,
	QUOTE_REQUIRED,
	REQUIRED_DELIVERY_DATE,
	ENGINE_MODEL,
	ENGINE_SERIAL_NUMBER,
	ENGINE_SECTION,
	ENGINE_SECTION_DESC,
	INCOMING_MATERIAL,
	OUTGOING_MATERIAL,
	QUANTITY,
	LOT_CODE,
	SERIAL_NUMBER
}

public class T301MessageProcessor extends BaseMessageProcessor implements IT301MessageProcessor {

	@Reference	
	private IT301DaoPW t301DaoPW = null;
	@Reference  
	private OAGIUtils oagiUtils = null;  // G8R2SP4
	
	/*
<?xml version="1.0" encoding="UTF-8"?>
<SyncRecord xmlns="http://www.openapplications.org/oagis/9" releaseID="9.1">
	<ApplicationArea>
		<Sender>
			<LogicalID>SAPQ10</LogicalID>
			<ComponentID>ZSMInnnn</ComponentID>
			<!--this is the idoc message type-->
			<TaskID>ZTable Update</TaskID>
			<!--this is the idoc basic type-->
			<ReferenceID>SOPPWNZ</ReferenceID>
			<!--this is the logical partner of recipient-->
			<ConfirmationCode>Never</ConfirmationCode>
			<AuthorizationID>Janet1</AuthorizationID>
		</Sender>
		<CreationDateTime>2009-01-30T15:43:14.661-08:00</CreationDateTime>
		<BODID>163838342</BODID>
		<!--this is idoc number-->
	</ApplicationArea>
	<DataArea>
		<Sync>
			<ActionCriteria>
				<ActionExpression actionCode="Replace"/>
			</ActionCriteria>
		</Sync>
		<Record>
			<!--this is first order-->
			<Keys>
				<RecordKey name="IDOC_NUMBER" type="String">16383842</RecordKey>
				<RecordKey name="SM_ORDER" type="String">60003964</RecordKey>
				<RecordKey name="MESSAGE_TYPE" type="String">ORD</RecordKey>
				<RecordKey name="UPDT_USERID" type="String">M107069</RecordKey>
				<RecordKey name="ORDER_TYPE" type="String">Z003</RecordKey>
				<RecordKey name="PLANT" type="String">DARO</RecordKey>
				<RecordKey name="NOTIFICATION" type="String">12345678</RecordKey>
				<RecordKey name="SALES_ORDER" type="String">8383338</RecordKey>
				<RecordKey name="SALES_DOC_ITEM" type="String">1234567890</RecordKey>
				<RecordKey name="SUPERIOR_SM_ORDER" type="String">13412348</RecordKey>
				<RecordKey name="CUSTOMER_NUMBER" type="String">4123125</RecordKey>
				<RecordKey name="CUSTOMER_NAME" type="String">Customer alpha</RecordKey>
				<RecordKey name="PURCHASE_ORDER" type="String">135123</RecordKey>
				<RecordKey name="QUOTE_REQUIRED" type="String">N</RecordKey>
				<RecordKey name="REQUIRED_DELIVERY_DATE" type="String">20190901</RecordKey>
				<RecordKey name="ENGINE_MODEL" type="String">PW38</RecordKey>
				<RecordKey name="ENGINE_SERIAL_NUMBER" type="String">384828</RecordKey>
				<RecordKey name="ENGINE_SECTION" type="String">FAN</RecordKey>
				<RecordKey name="ENGINE_SECTION_DESC" type="String">Fan section</RecordKey>
				<RecordKey name="INCOMING_MATERIAL" type="String">E54K10</RecordKey>
				<RecordKey name="OUTGOING_MATERIAL" type="String">E5RK11</RecordKey>
				<RecordKey name="QUANTITY" type="Numeric">3</RecordKey>
				<RecordKey name="LOT_CODE" type="String">LOT7</RecordKey>
				<RecordKey name="SERIAL_NUMBER" type="String">788888</RecordKey>
				<RecordKey name="SERIAL_NUMBER" type="String">788889</RecordKey>
				<RecordKey name="SERIAL_NUMBER" type="String">788890</RecordKey>
			</Keys>
		</Record>
		<Record>
		<!--this is second order-->
			<Keys>
				<RecordKey name="IDOC_NUMBER" type="String">16383842</RecordKey>
				<RecordKey name="SM_ORDER" type="String">60003965</RecordKey>
				<RecordKey name="MESSAGE_TYPE" type="String">CRT</RecordKey>
				<RecordKey name="UPDT_USERID" type="String">M107069</RecordKey>
				<RecordKey name="ORDER_TYPE" type="String">Z003</RecordKey>
				<RecordKey name="PLANT" type="String">DARO</RecordKey>
				<RecordKey name="NOTIFICATION" type="String">12345678</RecordKey>
				<RecordKey name="SALES_ORDER" type="String">8383338</RecordKey>
				<RecordKey name="SALES_DOC_ITEM" type="String">1234567890</RecordKey>
				<RecordKey name="SUPERIOR_SM_ORDER" type="String">13412348</RecordKey>
				<RecordKey name="CUSTOMER_NUMBER" type="String">4123125</RecordKey>
				<RecordKey name="CUSTOMER_NAME" type="String">Customer alpha</RecordKey>
				<RecordKey name="PURCHASE_ORDER" type="String">135123</RecordKey>
				<RecordKey name="QUOTE_REQUIRED" type="String">N</RecordKey>
				<RecordKey name="REQUIRED_DELIVERY_DATE" type="String">20190901</RecordKey>
				<RecordKey name="ENGINE_MODEL" type="String">PW38</RecordKey>
				<RecordKey name="ENGINE_SERIAL_NUMBER" type="String">384828</RecordKey>
				<RecordKey name="ENGINE_SECTION" type="String">FAN</RecordKey>
				<RecordKey name="ENGINE_SECTION_DESC" type="String">Fan section</RecordKey>
				<RecordKey name="INCOMING_MATERIAL" type="String">E54K10</RecordKey>
				<RecordKey name="OUTGOING_MATERIAL" type="String">E5RK11</RecordKey>
				<RecordKey name="QUANTITY" type="Numeric">3</RecordKey>
				<RecordKey name="LOT_CODE" type="String">LOT7</RecordKey>
				<RecordKey name="SERIAL_NUMBER" type="String">788891</RecordKey>
				<RecordKey name="SERIAL_NUMBER" type="String">788892</RecordKey>
				<RecordKey name="SERIAL_NUMBER" type="String">788893</RecordKey>
			</Keys>
		</Record>
	</DataArea>
</SyncRecord>

   */
	
	public List<SmOrderRecord> SmOrderRecordList;
	
	@Override
	public SchemaType getInboundBodType() {
		
		return SyncRecordDocument.type;
	}

	@Override
	public XmlObject parseMessage(String inboundMessage) throws ParsingException
	{
		XmlObject inboundBod = null;
		SchemaType inboundBodDocumentType = getInboundBodType();

		try{
			inboundBod = XmlObject.Factory.parse(inboundMessage,new XmlOptions().setDocumentType(inboundBodDocumentType));
		}
		catch (XmlException xe)
		{
			throw new ParsingException("Error parsing message", xe);
		}

		return inboundBod;
	}
	
	@Override
	public void processMessage(String messageText, MessageLoggerParams loggerParams) 
	{  
		try 
		{
			doProcessing (messageText, loggerParams);
			if (loggerParams.getWarningText() != null)
			{
				loggerParams.setStatus(MessageLogger.STATUS_RECEIVE_NEEDS_REVIEW);
			}
			else
			{
			    loggerParams.setStatus(MessageLogger.STATUS_RECEIVE_SUCCESS);
			}
		}
		catch (Throwable t)
		{
			loggerParams.setErrors(t);
			loggerParams.setStatus(MessageLogger.STATUS_RECEIVE_FAILURE);
			loggerParams.setErrorText(t.getMessage());
		}	
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void doProcessing(String message, MessageLoggerParams loggerParams) throws Throwable 
	{		
		XmlObject inboundBod = parseMessage(message);
		oagiUtils.validateXml(inboundBod);

		if(inboundBod instanceof SyncRecordDocument) {
			
			SyncRecordDocument syncRecordDocument = (SyncRecordDocument) inboundBod;
			SyncRecordType syncRecord = syncRecordDocument.getSyncRecord();

			SyncRecordDataAreaType dataArea = syncRecord.getDataArea();
			List<RecordType> recordTypeList = dataArea.getRecordList();
			
			SmOrderRecord smOrderRecord = null;
			List<SmOrderRecord> smOrderRecordList = new ArrayList<SmOrderRecord>();

			for (int i=0; i<recordTypeList.size(); i++) {

				RecordType recordType = (RecordType) recordTypeList.get(i);
				KeysType keysType = recordType.getKeys();
				List<NameValuePairType> recordKeyList = keysType.getRecordKeyList();

				HashMap columnHashMap = getTableColumnSizes();
				smOrderRecord = mapSmOrderRecord(recordKeyList,columnHashMap);
				if(i == 0) {
					if (smOrderRecord.getIdocNumber()!=null) {
						loggerParams.setRef1("IDOC=" +smOrderRecord.getIdocNumber());
					}else{
						loggerParams.setRef1("IDOC=null");
					}
				}
				smOrderRecordList.add(smOrderRecord);
			}
			processData(smOrderRecordList);
		}else{
			throw new Exception("XML message is not SyncRecordDocument ");
		}
	}

	@SuppressWarnings("rawtypes")
	private void processData(List<SmOrderRecord> smOrderRecordList) throws Throwable {

		if (smOrderRecordList!=null && smOrderRecordList.size()>0){
			Iterator iterator = smOrderRecordList.iterator();
			while (iterator.hasNext()){
				SmOrderRecord smOrderRecord = (SmOrderRecord)iterator.next();
				insertT301Records(smOrderRecord);
			}
		}			
	}

	private void insertT301Records(SmOrderRecord smOrderRecord) {

		t301DaoPW.insertT301Order(smOrderRecord.getIdocNumber(),	
				                  smOrderRecord.getMessageType(),
				                  smOrderRecord.getUpdtUserid(),
				                  smOrderRecord.getOrderType(),
				                  smOrderRecord.getPlant(),
				                  smOrderRecord.getNotification(),
				                  smOrderRecord.getSalesOrder(),
				                  smOrderRecord.getSalesDocItem(),
				                  smOrderRecord.getSuperiorSmOrder(),
				                  smOrderRecord.getSmOrder(),
				                  smOrderRecord.getCustomerNumber(),
				                  smOrderRecord.getCustomerName(),
				                  smOrderRecord.getPurchaseOrder(),
				                  smOrderRecord.getQuoteRequired(),
				                  smOrderRecord.getRequiredDeliveryDate(),
				                  smOrderRecord.getEngineModel(),
				                  smOrderRecord.getEngineSerialNumber(),
				                  smOrderRecord.getEngineSection(),
				                  smOrderRecord.getEngineSectionDesc(),
				                  smOrderRecord.getIncomingMaterial(),
				                  smOrderRecord.getOutgoingMaterial(),
				                  smOrderRecord.getQuantity(),
				                  smOrderRecord.getLotCode());
		
		 for (int i=0; i<smOrderRecord.serialNumberList.size(); i++ )
		 {
			 t301DaoPW.insertT301Serials(smOrderRecord.getIdocNumber(),
					                     smOrderRecord.getSmOrder(),
				                         smOrderRecord.serialNumberList.get(i));
		}
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected HashMap getTableColumnSizes() {
		HashMap hashMap = new HashMap();
		List columnDataList = t301DaoPW.getT301ColumnLengths();
		if (columnDataList!=null && !columnDataList.isEmpty()){
			
			Iterator iterator = columnDataList.iterator();
			while (iterator.hasNext()){
				Map map = (Map)iterator.next();
				
				String columnName = (String) map.get("COLUMN_NAME");
				Number columnSize = (Number)map.get("DATA_LENGTH");
				hashMap.put(columnName,columnSize);
			}
		}
		
		return hashMap;
	}

	@SuppressWarnings("rawtypes")
	private SmOrderRecord mapSmOrderRecord(List recordKeyList,HashMap columnHashMap) throws MappingException {

		SmOrderRecord smOrderRecord=null;
		String serialNumber = null;
		
		if (recordKeyList!=null && !recordKeyList.isEmpty()){

			smOrderRecord = new SmOrderRecord();

			for (int i=0; i<recordKeyList.size(); i++) {
				
				NameValuePairType nameValuePairType = (NameValuePairType) recordKeyList.get(i);
				String recordKeyName = nameValuePairType.getName();

				switch (XMLFields.valueOf(recordKeyName)) {
				case IDOC_NUMBER:
					smOrderRecord.setIdocNumber(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(smOrderRecord.getIdocNumber())){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.IDOC_NUMBER.toString());
						if (tableColMaxLen!=null && smOrderRecord.getIdocNumber().length() > tableColMaxLen.intValue()){
							throw new MappingException ("IDOC_NUMBER max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				case MESSAGE_TYPE:
					smOrderRecord.setMessageType(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(smOrderRecord.getMessageType())){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.MESSAGE_TYPE.toString());
						if (tableColMaxLen!=null && smOrderRecord.getMessageType().length() > tableColMaxLen.intValue()){
							throw new MappingException ("MESSAGE_TYPE max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				case UPDT_USERID:
					smOrderRecord.setUpdtUserid(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(smOrderRecord.getUpdtUserid())){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.UPDT_USERID.toString());
						if (tableColMaxLen!=null && smOrderRecord.getUpdtUserid().length() > tableColMaxLen.intValue()){
							throw new MappingException ("UPDT_USERID max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				case ORDER_TYPE:
					smOrderRecord.setOrderType(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(smOrderRecord.getOrderType())){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.ORDER_TYPE.toString());
						if (tableColMaxLen!=null && smOrderRecord.getOrderType().length() > tableColMaxLen.intValue()){
							throw new MappingException ("ORDER_TYPE max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				case PLANT:
					smOrderRecord.setPlant(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(smOrderRecord.getPlant())){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.PLANT.toString());
						if (tableColMaxLen!=null && smOrderRecord.getPlant().length() > tableColMaxLen.intValue()){
							throw new MappingException ("PLANT max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				case NOTIFICATION:
					smOrderRecord.setNotification(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(smOrderRecord.getNotification())){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.NOTIFICATION.toString());
						if (tableColMaxLen!=null && smOrderRecord.getNotification().length() > tableColMaxLen.intValue()){
							throw new MappingException ("NOTIFICATION max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				case SALES_ORDER:
					smOrderRecord.setSalesOrder(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(smOrderRecord.getSalesOrder())){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.SALES_ORDER.toString());
						if (tableColMaxLen!=null && smOrderRecord.getSalesOrder().length() > tableColMaxLen.intValue()){
							throw new MappingException ("SALES_ORDER max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				case SALES_DOC_ITEM:
					smOrderRecord.setSalesDocItem(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(smOrderRecord.getSalesDocItem())){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.SALES_DOC_ITEM.toString());
						if (tableColMaxLen!=null && smOrderRecord.getSalesDocItem().length() > tableColMaxLen.intValue()){
							throw new MappingException ("SALES_DOC_ITEM max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				case SUPERIOR_SM_ORDER:
					smOrderRecord.setSuperiorSmOrder(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(smOrderRecord.getSuperiorSmOrder())){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.SUPERIOR_SM_ORDER.toString());
						if (tableColMaxLen!=null && smOrderRecord.getSuperiorSmOrder().length() > tableColMaxLen.intValue()){
							throw new MappingException ("SUPERIOR_SM_ORDER max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				case SM_ORDER:
					smOrderRecord.setSmOrder(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(smOrderRecord.getSmOrder())){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.SM_ORDER.toString());
						if (tableColMaxLen!=null && smOrderRecord.getSmOrder().length() > tableColMaxLen.intValue()){
							throw new MappingException ("SM_ORDER max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				case CUSTOMER_NUMBER:
					smOrderRecord.setCustomerNumber(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(smOrderRecord.getCustomerNumber())){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.CUSTOMER_NUMBER.toString());
						if (tableColMaxLen!=null && smOrderRecord.getCustomerNumber().length() > tableColMaxLen.intValue()){
							throw new MappingException ("CUSTOMER_NUMBER max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				case CUSTOMER_NAME:
					smOrderRecord.setCustomerName(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(smOrderRecord.getCustomerName())){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.CUSTOMER_NAME.toString());
						if (tableColMaxLen!=null && smOrderRecord.getCustomerName().length() > tableColMaxLen.intValue()){
							throw new MappingException ("CUSTOMER_NAME max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				case PURCHASE_ORDER:
					smOrderRecord.setPurchaseOrder(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(smOrderRecord.getPurchaseOrder())){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.PURCHASE_ORDER.toString());
						if (tableColMaxLen!=null && smOrderRecord.getPurchaseOrder().length() > tableColMaxLen.intValue()){
							throw new MappingException ("PURCHASE_ORDER max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				case QUOTE_REQUIRED:
					smOrderRecord.setQuoteRequired(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(smOrderRecord.getQuoteRequired())){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.QUOTE_REQUIRED.toString());
						if (tableColMaxLen!=null && smOrderRecord.getQuoteRequired().length() > tableColMaxLen.intValue()){
							throw new MappingException ("QUOTE_REQUIRED max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				case REQUIRED_DELIVERY_DATE:
					smOrderRecord.setRequiredDeliveryDate(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(smOrderRecord.getRequiredDeliveryDate())){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.REQUIRED_DELIVERY_DATE.toString());
						if (tableColMaxLen!=null && smOrderRecord.getRequiredDeliveryDate().length() > tableColMaxLen.intValue()){
							throw new MappingException ("REQUIRED_DELIVERY_DATE max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				case ENGINE_MODEL:
					smOrderRecord.setEngineModel(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(smOrderRecord.getEngineModel())){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.ENGINE_MODEL.toString());
						if (tableColMaxLen!=null && smOrderRecord.getEngineModel().length() > tableColMaxLen.intValue()){
							throw new MappingException ("ENGINE_MODEL max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				case ENGINE_SERIAL_NUMBER:
					smOrderRecord.setEngineSerialNumber(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(smOrderRecord.getEngineSerialNumber())){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.ENGINE_SERIAL_NUMBER.toString());
						if (tableColMaxLen!=null && smOrderRecord.getEngineSerialNumber().length() > tableColMaxLen.intValue()){
							throw new MappingException ("ENGINE_SERIAL_NUMBER max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				case ENGINE_SECTION:
					smOrderRecord.setEngineSection(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(smOrderRecord.getEngineSection())){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.ENGINE_SECTION.toString());
						if (tableColMaxLen!=null && smOrderRecord.getEngineSection().length() > tableColMaxLen.intValue()){
							throw new MappingException ("ENGINE_SECTION max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				case ENGINE_SECTION_DESC:
					smOrderRecord.setEngineSectionDesc(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(smOrderRecord.getEngineSectionDesc())){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.ENGINE_SECTION_DESC.toString());
						if (tableColMaxLen!=null && smOrderRecord.getEngineSectionDesc().length() > tableColMaxLen.intValue()){
							throw new MappingException ("ENGINE_SECTION_DESC max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				case INCOMING_MATERIAL:
					smOrderRecord.setIncomingMaterial(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(smOrderRecord.getIncomingMaterial())){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.INCOMING_MATERIAL.toString());
						if (tableColMaxLen!=null && smOrderRecord.getIncomingMaterial().length() > tableColMaxLen.intValue()){
							throw new MappingException ("INCOMING_MATERIAL max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				case OUTGOING_MATERIAL:
					smOrderRecord.setOutgoingMaterial(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(smOrderRecord.getOutgoingMaterial())){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.OUTGOING_MATERIAL.toString());
						if (tableColMaxLen!=null && smOrderRecord.getOutgoingMaterial().length() > tableColMaxLen.intValue()){
							throw new MappingException ("OUTGOING_MATERIAL max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				case QUANTITY:
					smOrderRecord.setQuantity(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(smOrderRecord.getQuantity())){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.QUANTITY.toString());
						if (tableColMaxLen!=null && smOrderRecord.getQuantity().length() > tableColMaxLen.intValue()){
							throw new MappingException ("QUANTITY max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				case LOT_CODE:
					smOrderRecord.setLotCode(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(smOrderRecord.getLotCode())){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.LOT_CODE.toString());
						if (tableColMaxLen!=null && smOrderRecord.getLotCode().length() > tableColMaxLen.intValue()){
							throw new MappingException ("LOT_CODE max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				case SERIAL_NUMBER:
					serialNumber = nameValuePairType.getStringValue();
					smOrderRecord.setSerialNumberList(nameValuePairType.getStringValue());
					if (StringUtils.isNotBlank(serialNumber)){
						Number tableColMaxLen = (Number)columnHashMap.get(XMLFields.SERIAL_NUMBER);
						if (tableColMaxLen!=null && serialNumber.length() > tableColMaxLen.intValue()){
							throw new MappingException ("SERIAL_NUMBER max size is " + tableColMaxLen.intValue());
						}
					}
					break;
				}
			}
		}
		return smOrderRecord;
	}

	class SmOrderRecord {
		
		String idocNumber = null;		
		String messageType = null;
		String updtUserid = null;
		String orderType = null;
		String plant = null;
		String notification = null;
		String salesOrder = null;
		String salesDocItem = null;
		String superiorSmOrder = null;
		String smOrder = null;
		String customerNumber = null;
		String customerName = null;
		String purchaseOrder = null;
		String quoteRequired = null;
		String requiredDeliveryDate = null;
		String engineModel = null;
		String engineSerialNumber = null;
		String engineSection = null;
		String engineSectionDesc = null;
		String incomingMaterial = null;
		String outgoingMaterial = null;
		String quantity = null;
		String lotCode = null;
	    List<String> serialNumberList = new ArrayList<String>();
	    
		public String getIdocNumber() {
			return idocNumber;
		}
		public void setIdocNumber(String idocNumber) {
			this.idocNumber = idocNumber;
		}
		public String getMessageType() {
			return messageType;
		}
		public void setMessageType(String messageType) {
			this.messageType = messageType;
		}
		public String getUpdtUserid() {
			return updtUserid;
		}
		public void setUpdtUserid(String updtUserid) {
			this.updtUserid = updtUserid;
		}
		public String getOrderType() {
			return orderType;
		}
		public void setOrderType(String orderType) {
			this.orderType = orderType;
		}
		public String getPlant() {
			return plant;
		}
		public void setPlant(String plant) {
			this.plant = plant;
		}
		public String getNotification() {
			return notification;
		}
		public void setNotification(String notification) {
			this.notification = notification;
		}
		public String getSalesOrder() {
			return salesOrder;
		}
		public void setSalesOrder(String salesOrder) {
			this.salesOrder = salesOrder;
		}
		public String getSalesDocItem() {
			return salesDocItem;
		}
		public void setSalesDocItem(String salesDocItem) {
			this.salesDocItem = salesDocItem;
		}
		public String getSuperiorSmOrder() {
			return superiorSmOrder;
		}
		public void setSuperiorSmOrder(String superiorSmOrder) {
			this.superiorSmOrder = superiorSmOrder;
		}
		public String getSmOrder() {
			return smOrder;
		}
		public void setSmOrder(String smOrder) {
			this.smOrder = smOrder;
		}
		public String getCustomerNumber() {
			return customerNumber;
		}
		public void setCustomerNumber(String customerNumber) {
			this.customerNumber = customerNumber;
		}
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		public String getPurchaseOrder() {
			return purchaseOrder;
		}
		public void setPurchaseOrder(String purchaseOrder) {
			this.purchaseOrder = purchaseOrder;
		}
		public String getQuoteRequired() {
			return quoteRequired;
		}
		public void setQuoteRequired(String quoteRequired) {
			this.quoteRequired = quoteRequired;
		}
		public String getRequiredDeliveryDate() {
			return requiredDeliveryDate;
		}
		public void setRequiredDeliveryDate(String requiredDeliveryDate) {
			this.requiredDeliveryDate = requiredDeliveryDate;
		}
		public String getEngineModel() {
			return engineModel;
		}
		public void setEngineModel(String engineModel) {
			this.engineModel = engineModel;
		}
		public String getEngineSerialNumber() {
			return engineSerialNumber;
		}
		public void setEngineSerialNumber(String engineSerialNumber) {
			this.engineSerialNumber = engineSerialNumber;
		}
		public String getEngineSection() {
			return engineSection;
		}
		public void setEngineSection(String engineSection) {
			this.engineSection = engineSection;
		}
		public String getEngineSectionDesc() {
			return engineSectionDesc;
		}
		public void setEngineSectionDesc(String engineSectionDesc) {
			this.engineSectionDesc = engineSectionDesc;
		}
		public String getIncomingMaterial() {
			return incomingMaterial;
		}
		public void setIncomingMaterial(String incomingMaterial) {
			this.incomingMaterial = incomingMaterial;
		}
		public String getOutgoingMaterial() {
			return outgoingMaterial;
		}
		public void setOutgoingMaterial(String outgoingMaterial) {
			this.outgoingMaterial = outgoingMaterial;
		}
		public String getQuantity() {
			return quantity;
		}
		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}
		public String getLotCode() {
			return lotCode;
		}
		public void setLotCode(String lotCode) {
			this.lotCode = lotCode;
		}		
		public List<String> getSerialNumberList() {
			return serialNumberList;
		}
		public void setSerialNumberList(String serialNumber) {
			this.serialNumberList.add(serialNumber);
		}
	}
}
