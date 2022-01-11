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
 *  File:    M316MessageProcessor.java
 * 
 *  Created: 2019-08-05
 * 
 *  Author:  B. Corson
 * 
 *  Revision History
 * 
 *  Date          Who            Description
 *  -----------   ------------   -----------------------------------------------------------
 *  2019-08-05    B. Corson      Initial Release SMRO_MD_316 - Scrap Codes and Hold Types Groups Interface
 *  
 */
package com.pw.solumina.M316.integration.impl;

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
import org.openapplications.oagis.x9.AttributesType;
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
import com.pw.solumina.M316.integration.IM316MessageProcessor;
//import com.pw.solumina.M316.integration.impl.M316MessageProcessor.CatCodeRecord;
import com.pw.solumina.interfaces.dao.IM316DaoPW;

enum XMLKeyFields {
    WORK_LOC,
    CATALOG,
    CATEGORY,
    CODE
}

enum XMLAttribFields {
    CATEGORY_DESC,
    CODE_DESC,
    OBSOLETE_RECORD_FLAG,
    UPDT_USERID
}

public class M316MessageProcessor 
     extends BaseMessageProcessor 
     implements IM316MessageProcessor {
    
	@Reference
    private IM316DaoPW m316DaoPW = null;
    @Reference
    private OAGIUtils oagiUtils = null;  // G8R2SP4
    
    /*
    <?xml version="1.0" encoding="UTF-8"?>
    <SyncRecord releaseID="" xmlns="http://www.openapplications.org/oagis/9" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.openapplications.org/oagis/9 file:///C:/iBASEt%20project/OAGi-BPI-Platform_941/OAGi-BPI-Platform/com_ibaset_solumina_oagis/9_4_1/Developer/BODs/SyncRecord.xsd">
        <ApplicationArea>
            <Sender>
                <LogicalID>SAPD10</LogicalID>
                <ComponentID>Ztbd</ComponentID>
                <TaskID>Ztbd</TaskID>
                <ReferenceID>SOLMROUS</ReferenceID>
                <ConfirmationCode>Never</ConfirmationCode>
            </Sender>
            <CreationDateTime>2019-02-11</CreationDateTime>
            <BODID>0000000000396827</BODID>
        </ApplicationArea>
        <DataArea>
            <Sync/>
                <!--First record...-->
                <Record>
                    <Keys>
                        <RecordKey name="WORK_LOC">5540</RecordKey>
                        <RecordKey name="CATALOG">STG</RecordKey>
                        <RecordKey name="CATEGORY">SM-JTT</RecordKey>
                        <RecordKey name="CODE">JTT1</RecordKey>
                    </Keys>
                    <Attributes>
                        <Attribute name="CATEGORY_DESC">Japanese Turbine Tech</Attribute>
                        <Attribute name="CODE_DESC">JTT-Customer Support Group</Attribute>
                        <Attribute name="OBSOLETE_RECORD_FLAG">N</Attribute>
                    </Attributes>
                </Record>
                <!--Second record...-->
                <Record>
                    <Keys>
                        <RecordKey name="WORK_LOC">5540</RecordKey>
                        <RecordKey name="CATALOG">STG</RecordKey>
                        <RecordKey name="CATEGORY">SM-JTT</RecordKey>
                        <RecordKey name="CODE">JTT2</RecordKey>
                    </Keys>
                    <Attributes>
                        <Attribute name="CATEGORY_DESC">Japanese Turbine Tech</Attribute>
                        <Attribute name="CODE_DESC">JTT-Engineering Group</Attribute>
                        <Attribute name="OBSOLETE_RECORD_FLAG">N</Attribute>
                    </Attributes>
                </Record>
            </DataArea>
        </SyncRecord>
    */
    
    public List<CatCodeRecord> CatCodeRecordList;
    
    @Override
    public SchemaType getInboundBodType() {
        return SyncRecordDocument.type;
    }
    
    @Override
    public XmlObject parseMessage(String inboundMessage) throws ParsingException
    {
        XmlObject inboundBod = null;
        SchemaType inboundBodDocumentType = getInboundBodType();
        
        try {
            inboundBod = XmlObject.Factory.parse(inboundMessage, new XmlOptions().setDocumentType(inboundBodDocumentType));
        } catch (XmlException xe) {
            throw new ParsingException("Error parsing message", xe);
        }
        
        return inboundBod;
    }
    
    @Override
    public void processMessage(String messageText, MessageLoggerParams loggerParams)
    {
        try {
            doProcessing(messageText, loggerParams);
            if (loggerParams.getWarningText() != null) {
                loggerParams.setStatus(MessageLogger.STATUS_RECEIVE_NEEDS_REVIEW);
            } else {
                loggerParams.setStatus(MessageLogger.STATUS_RECEIVE_SUCCESS);
            }
        } catch (Throwable t) {
            loggerParams.setErrors(t);
            loggerParams.setStatus(MessageLogger.STATUS_RECEIVE_FAILURE);
            loggerParams.setErrorText(t.getMessage());
        }
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public void doProcessing(String message, MessageLoggerParams loggerParams) throws Throwable
    {
        System.out.println("Start M316");
        XmlObject inboundBod = parseMessage(message);
        oagiUtils.validateXml(inboundBod);
        
        if (inboundBod instanceof SyncRecordDocument) {
            SyncRecordDocument syncRecordDocument = (SyncRecordDocument) inboundBod;
            SyncRecordType syncRecord = syncRecordDocument.getSyncRecord();
            
            if (syncRecord!=null &&
                syncRecord.getApplicationArea()!=null &&
                syncRecord.getApplicationArea().getBODID()!=null) 
            {
                String bodId = syncRecord.getApplicationArea().getBODID().getStringValue();
                loggerParams.setRef1("IDOC=" + bodId);
            }
            
            SyncRecordDataAreaType dataArea = syncRecord.getDataArea();
            List<RecordType> recordTypeList = dataArea.getRecordList();
            CatCodeRecord catCodeRecord = null;
            List<CatCodeRecord> catCodeRecordList = new ArrayList<CatCodeRecord>();
            
            for (int i=0; i<recordTypeList.size(); i++) {
                RecordType recordType = (RecordType) recordTypeList.get(i);
                KeysType keysType = recordType.getKeys();
                List<NameValuePairType> recordKeyList = keysType.getRecordKeyList();
                
                AttributesType attribType = recordType.getAttributes();
                List<NameValuePairType> recordAttribList = attribType.getAttributeList();
                      
                HashMap columnHashMap = getTableColumnSizes();
                catCodeRecord = mapCatCodeRecord(recordKeyList, recordAttribList, columnHashMap);
                catCodeRecordList.add(catCodeRecord);
            }
            processData(catCodeRecordList);
        } else {
            throw new Exception("XML message is not SyncRecordDocument");
        }
        System.out.println("End M316");
    }
    
    @SuppressWarnings("rawtypes")
    private void processData(List<CatCodeRecord> catCodeRecordList) throws Throwable {
        
        if (catCodeRecordList != null && catCodeRecordList.size() > 0) {
            Iterator iterator = catCodeRecordList.iterator();
            while (iterator.hasNext()) {
                CatCodeRecord catCodeRecord = (CatCodeRecord) iterator.next();
                if (catCodeRecord.getObsoleteRecordFlag().equalsIgnoreCase("N")) {
                    if (m316DaoPW.catCodeExists(catCodeRecord.getWorkLoc(),
                                                catCodeRecord.getCatalog(),
                                                catCodeRecord.getCategory(),
                                                catCodeRecord.getCode()))
                        updateM316Record(catCodeRecord);
                    else
                        insertM316Record(catCodeRecord);
                } else
                    updateM316RecordOF(catCodeRecord);
            }
        }
    }
    
    private void insertM316Record(CatCodeRecord catCodeRecord) {
        
        m316DaoPW.insertM316CatCode(catCodeRecord.getWorkLoc(),
                                    catCodeRecord.getCatalog(),
                                    catCodeRecord.getCategory(),
                                    catCodeRecord.getCode(),
                                    catCodeRecord.getCatDesc(),
                                    catCodeRecord.getCodeDesc(),
                                    catCodeRecord.getObsoleteRecordFlag(),
                                    catCodeRecord.getUpdtUserid());
        
    }
    
    private void updateM316Record(CatCodeRecord catCodeRecord) {
        
        int uc = m316DaoPW.updateM316CatCode(catCodeRecord.getWorkLoc(),
                                             catCodeRecord.getCatalog(),
                                             catCodeRecord.getCategory(),
                                             catCodeRecord.getCode(),
                                             catCodeRecord.getCatDesc(),
                                             catCodeRecord.getCodeDesc(),
                                             catCodeRecord.getObsoleteRecordFlag(),
                                             catCodeRecord.getUpdtUserid());
        
    }
    
    private void updateM316RecordOF(CatCodeRecord catCodeRecord) {
        
        int uc = m316DaoPW.updateM316ObsoleteFL(catCodeRecord.getWorkLoc(),
                                                catCodeRecord.getCatalog(),
                                                catCodeRecord.getCategory(),
                                                catCodeRecord.getCode(),
                                                catCodeRecord.getObsoleteRecordFlag(),
                                                catCodeRecord.getUpdtUserid());
        
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    protected HashMap getTableColumnSizes() {
        HashMap hashMap = new HashMap();
        List columnDataList = m316DaoPW.getM316ColumnLengths();
        if (columnDataList != null && !columnDataList.isEmpty()) {
            Iterator iterator = columnDataList.iterator();
            while (iterator.hasNext()) {
                Map map = (Map) iterator.next();
                
                String columnName = (String) map.get("COLUMN_NAME");
                Number columnSize = (Number) map.get("DATA_LENGTH");
                hashMap.put(columnName, columnSize);
            }
        }
        return hashMap;
    }
    
    @SuppressWarnings("rawtypes")
    private CatCodeRecord mapCatCodeRecord(List recordKeyList, 
                                           List recordAttribList, 
                                           HashMap columnHashMap) throws MappingException {
        
        CatCodeRecord catCodeRecord = null;
        if (recordKeyList != null && !recordKeyList.isEmpty()) {
            catCodeRecord = new CatCodeRecord();
            for (int i=0; i<recordKeyList.size(); i++) {
                NameValuePairType nameValuePairType = (NameValuePairType) recordKeyList.get(i);
                String recordKeyName = nameValuePairType.getName();
                
                switch (XMLKeyFields.valueOf(recordKeyName)) {
                case WORK_LOC:
                    catCodeRecord.setWorkLoc(nameValuePairType.getStringValue());
                    if (StringUtils.isNotBlank(catCodeRecord.getWorkLoc())) {
                        Number tableColMaxLen = (Number) columnHashMap.get(XMLKeyFields.WORK_LOC.toString());
                        if (tableColMaxLen != null && catCodeRecord.getWorkLoc().length() > tableColMaxLen.intValue()) {
                            throw new MappingException ("WORK_LOC max size is " + tableColMaxLen.intValue());
                        }
                    }
                    break;
                case CATALOG:
                    catCodeRecord.setCatalog(nameValuePairType.getStringValue());
                    if (StringUtils.isNotBlank(catCodeRecord.getCatalog())) {
                        Number tableColMaxLen = (Number) columnHashMap.get(XMLKeyFields.CATALOG.toString());
                        if (tableColMaxLen != null && catCodeRecord.getCatalog().length() > tableColMaxLen.intValue()) {
                            throw new MappingException ("CATALOG max size is " + tableColMaxLen.intValue());
                        }
                    }
                    break;
                case CATEGORY:
                    catCodeRecord.setCategory(nameValuePairType.getStringValue());
                    if (StringUtils.isNotBlank(catCodeRecord.getCategory())) {
                        Number tableColMaxLen = (Number) columnHashMap.get(XMLKeyFields.CATEGORY.toString());
                        if (tableColMaxLen != null && catCodeRecord.getCategory().length() > tableColMaxLen.intValue()) {
                            throw new MappingException ("CATEGORY max size is " + tableColMaxLen.intValue());
                        }
                    }
                    break;
                case CODE:
                    catCodeRecord.setCode(nameValuePairType.getStringValue());
                    if (StringUtils.isNotBlank(catCodeRecord.getCode())) {
                        Number tableColMaxLen = (Number) columnHashMap.get(XMLKeyFields.CODE.toString());
                        if (tableColMaxLen != null && catCodeRecord.getCode().length() > tableColMaxLen.intValue()) {
                            throw new MappingException ("CODE max size is " + tableColMaxLen.intValue());
                        }
                    }
                    break;
                }
            }
        }
        
        if (recordAttribList != null && !recordAttribList.isEmpty()) {
            for (int i=0; i<recordAttribList.size(); i++) {
                NameValuePairType nameValuePairType = (NameValuePairType) recordAttribList.get(i);
                String recordKeyName = nameValuePairType.getName();
                       
                switch (XMLAttribFields.valueOf(recordKeyName)) {
                case CATEGORY_DESC:
                    catCodeRecord.setCatDesc(nameValuePairType.getStringValue());
                    if (StringUtils.isNotBlank(catCodeRecord.getCatDesc())) {
                        Number tableColMaxLen = (Number) columnHashMap.get(XMLAttribFields.CATEGORY_DESC.toString());
                        if (tableColMaxLen != null && catCodeRecord.getCatDesc().length() > tableColMaxLen.intValue()) {
                            throw new MappingException ("CATEGORY_DESC max size is " + tableColMaxLen.intValue());
                        }
                    }
                    break;
                case CODE_DESC:
                    catCodeRecord.setCodeDesc(nameValuePairType.getStringValue());
                    if (StringUtils.isNotBlank(catCodeRecord.getCodeDesc())) {
                        Number tableColMaxLen = (Number) columnHashMap.get(XMLAttribFields.CODE_DESC.toString());
                        if (tableColMaxLen != null && catCodeRecord.getCodeDesc().length() > tableColMaxLen.intValue()) {
                            throw new MappingException ("CODE_DESC max size is " + tableColMaxLen.intValue());
                        }
                    }
                    break;
                case OBSOLETE_RECORD_FLAG:
                    catCodeRecord.setObsoleteRecordFlag(nameValuePairType.getStringValue());
                    if (StringUtils.isNotBlank(catCodeRecord.getObsoleteRecordFlag())) {
                        Number tableColMaxLen = (Number) columnHashMap.get(XMLAttribFields.OBSOLETE_RECORD_FLAG.toString());
                        if (tableColMaxLen != null && catCodeRecord.getObsoleteRecordFlag().length() > tableColMaxLen.intValue()) {
                            throw new MappingException ("OBSOLETE_RECORD_FLAG max size is " + tableColMaxLen.intValue());
                        }
                    }
                    break;
                case UPDT_USERID:
                    catCodeRecord.setUpdtUserid(nameValuePairType.getStringValue());
                    if (StringUtils.isNotBlank(catCodeRecord.getUpdtUserid())) {
                        Number tableColMaxLen = (Number) columnHashMap.get(XMLAttribFields.UPDT_USERID.toString());
                        if (tableColMaxLen != null && catCodeRecord.getUpdtUserid().length() > tableColMaxLen.intValue()) {
                            throw new MappingException ("UPDT_USERID max size is " + tableColMaxLen.intValue());
                        }
                    }
                    break;
                }
            }
        }
        return catCodeRecord;
    }
}

class CatCodeRecord {
    String workLoc = null;
    String catalog = null;
    String category = null;
    String code = null;
    String catDesc = null;
    String codeDesc = null;
    String obsoleteRecordFlag = null;
    String updtUserid = null;
    
    public String getWorkLoc() {
        return workLoc;
    }
    
    public void setWorkLoc(String workLoc) {
        this.workLoc = workLoc;
    }
    
    public String getUpdtUserid() {
        return updtUserid;
    }
    
    public void setUpdtUserid(String updtUserid) {
        this.updtUserid = updtUserid;
    }
    
    public String getCatalog() {
        return catalog;
    }
    
    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getCatDesc() {
        return catDesc;
    }
    
    public void setCatDesc(String catDesc) {
        this.catDesc = catDesc;
    }
    
    public String getCodeDesc() {
        return codeDesc;
    }
    
    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }
    
    public String getObsoleteRecordFlag() {
        return obsoleteRecordFlag;
    }
    
    public void setObsoleteRecordFlag(String obsoleteRecordFlag) {
        this.obsoleteRecordFlag = obsoleteRecordFlag;
    }
}