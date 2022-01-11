/*
 *  This unpublished work, first created in 2018 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2018.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    T226MessageProcessor.java
 * 
 *  Created: 2018-02-05
 * 
 *  Author:  Catrina Nguyen
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2018-02-05		C. Nguyen		Initial Release SMRO_TR_T226 - Module Swaps Interface
 *  2018-05-18		C. Nguyen		Added logic to process swaps information
 *  2018-07-09		C. Nguyen		Changed the logic for process swaps	
 *  2018-08-28		Fred Ettefagh   defect 971, ContextUtil.getUser() bug
 *  2019-03-01      D.Miron         Updated for G8R2SP4
 */
package com.pw.solumina.T226.integration.impl;

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
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.security.context.SoluminaUser;
import com.ibaset.solumina.integration.common.BaseMessageProcessor;
import com.ibaset.common.util.OAGIUtils; // G8R2SP4
import com.ibaset.solumina.integration.exception.MappingException;
import com.ibaset.solumina.integration.exception.ParsingException;
import com.ibaset.solumina.integration.logging.MessageLogger;
import com.ibaset.solumina.integration.logging.MessageLoggerParams;
import com.pw.solumina.T226.integration.IT226MessageProcessor;
import com.pw.solumina.sfwid.dao.IModuleSwapDaoPW;

public class T226MessageProcessor extends BaseMessageProcessor implements IT226MessageProcessor {

	@Reference	
	private IModuleSwapDaoPW moduleSwapsDao;
	
	@Reference  
	private OAGIUtils oagiUtils = null;  // G8R2SP4
	
	/*
	<?xml version="1.0" encoding="utf-8" standalone="yes"?>
	<NS1:SyncRecord releaseID="9_4" xmlns:NS1="http://www.openapplications.org/oagis/9">
	  <NS1:ApplicationArea>
	    <NS1:Sender>
	      <NS1:LogicalID>SOLG8MRO</NS1:LogicalID>
	      <NS1:ComponentID>T226 Interface</NS1:ComponentID>
	      <NS1:TaskID>Module Swaps</NS1:TaskID>
	      <NS1:ReferenceID>ETV2500G</NS1:ReferenceID>
	      <NS1:ConfirmationCode>Never</NS1:ConfirmationCode>
	      <NS1:AuthorizationID>SOLUMINA-IC</NS1:AuthorizationID>
	    </NS1:Sender>
	    <NS1:CreationDateTime>2018-02-05T05:56:29.642490-04:00</NS1:CreationDateTime>
	  </NS1:ApplicationArea>
	  <NS1:DataArea>
	    <NS1:Sync>
	      <NS1:ActionCriteria>
	        <NS1:ActionExpression actionCode="REL">SyncRecord/DataArea/Record</NS1:ActionExpression>
	      </NS1:ActionCriteria>
	    </NS1:Sync>
	<NS1:Record>
	      <NS1:Keys>
	        <NS1:RecordKey name="SALES_ORDER">ESAMSN1-02</NS1:RecordKey>            -- SALES ORDER
	        <NS1:RecordKey name="DOKNR">IAEDIF-P421934</NS1:RecordKey>  		-- DOKNR
	        <NS1:RecordKey name="CFG_MODULE_CODE">V-DIF</NS1:RecordKey>           -- CONFIGURATION MODULE CODE
			<NS1:RecordKey name="SERIAL_NO">SAMSN1</NS1:RecordKey>                 -- SERIAL NO
	        <NS1:RecordKey name="CUST_SERIAL_NO">SAMSN1A</NS1:RecordKey>           -- CUSTOMER SERIAL NO
			<NS1:RecordKey name="UPDT_USERID">M305420</NS1:RecordKey>              -- UPDATED USERID
	        <NS1:RecordKey name="UPDATE_TIME_STAMP">4/13/2017</NS1:RecordKey>      -- UPDATED TIME STAMP
	        <NS1:RecordKey name="MESSAGE_ACTION">REL</NS1:RecordKey>		-- MESSAGE ACTION: REL/CHG/DEL
	      </NS1:Keys>
	</NS1:Record>
  </NS1:DataArea>
	</NS1:SyncRecord>
   */
		
	class ModuleSwapsHeaderRecord {
		
		String salesOrder = null;
		String dokNo = null;
		String cfgModuleCode = null;
		String serialNo = null;
		String custSerialNo = null;
		String updtUserId = null;
		String updtTime = null;
		String messageAction = null;

		
		public String getSalesOrder() {
			return salesOrder;
		}
		public void setSalesOrder(String saleOrder) {
			this.salesOrder = saleOrder;
		}
	    public String getDOKNo() {
			return dokNo;
		}
		public void setDOKNo(String DOTNo) {
			this.dokNo = DOTNo;
		}
		public String getCfgModuleCode() {
			return cfgModuleCode;
		}
		public void setCfgModuleCode(String cfgModCode) {
			this.cfgModuleCode = cfgModCode;
		}
		public String getSerialNo() {
			return serialNo;
		}
		public void setSerialNo(String serialNum) {
			this.serialNo = serialNum;
		}
		public String getCustSerialNo() {
			return custSerialNo;
		}
		public void setCustSerialNo(String custSerialNum) {
			this.custSerialNo = custSerialNum;
		}
		public String getUpdtUserId() {
			return updtUserId;
		}
		public void setUpdtUserId(String updtUserID) {
			this.updtUserId = updtUserID;
		}
		public String getUpdtTime() {
			return updtTime;
		}
		public void setUpdtTime(String updtTimeStamp) {
			this.updtTime = updtTimeStamp;
		}
		public String getMessageAction() {
			return messageAction;
		}
		public void setMessageAction(String messageAct) {
			this.messageAction = messageAct;
		}
	}
	
	
	public List<ModuleSwapsHeaderRecord> ModuleSwapsHeaderRecordList;
	
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
	
	@Override
	public void doProcessing(String message, MessageLoggerParams loggerParams) throws Throwable 
	{		
	

		XmlObject inboundBod = parseMessage(message);
		oagiUtils.validateXml(inboundBod);

		if(inboundBod instanceof SyncRecordDocument) {
			
			SyncRecordDocument syncRecordDocument = (SyncRecordDocument) inboundBod;

			SyncRecordType syncRecord = syncRecordDocument.getSyncRecord();
			//String swapId = syncRecord.getApplicationArea().getSender().getAuthorizationID().getStringValue();
			String idoc = syncRecord.getApplicationArea().getSender().getReferenceID().getStringValue();
			String successInd = syncRecord.getApplicationArea().getSender().getConfirmationCode().getStringValue();
			//loggerParams.setRef1(swapId);

			SyncRecordDataAreaType dataArea = syncRecord.getDataArea();
			List<RecordType> recordTypeList = dataArea.getRecordList();
			
			ModuleSwapsHeaderRecord moduleSwapsHeaderRecord = null;
			List<ModuleSwapsHeaderRecord> moduleSwapsHeaderRecordList = new ArrayList<ModuleSwapsHeaderRecord>();
			
			for (int i=0; i<recordTypeList.size(); i++) {

				RecordType recordType = (RecordType) recordTypeList.get(i);
				KeysType keysType = recordType.getKeys();
				List<NameValuePairType> recordKeyList = keysType.getRecordKeyList();				

				HashMap columnHashMap = getTableColumnSizes();
				moduleSwapsHeaderRecord = mapModuleSwapsHeaderRecord(recordKeyList,columnHashMap);
				if (moduleSwapsHeaderRecord!=null){
					moduleSwapsHeaderRecordList.add(moduleSwapsHeaderRecord);
					loggerParams.setRef1(moduleSwapsHeaderRecord.getSalesOrder());
				}
			}
			
			processData(moduleSwapsHeaderRecordList);
			
		}else{
			throw new Exception("XML message is not SyncRecordDocument ");
		}
		
	}


	private void processData(List<ModuleSwapsHeaderRecord> moduleSwapsHeaderRecordList) throws Throwable {

		/*
		XML message will be processes in doProcessing as follows.

		1.	Read ENGINE_TYPE in XML.
		2.	If ENGINE_TYPE exists in PWUST_NETWORK_DEF, update all records to OBSOLETE_RECORD_FLAG=Y.
		3.	For each network activity/sub network activity grouping in XML:
		a.	Check if activity exists in PWUST_NETWORK_DEF.
		i.	If activity does not exist, insert new record.
		ii.	If activity does exist, update existing record and set OBSOLETE_RECORD_FLAG=N

		Above logic ensures activities are made obsolete if not included in the XML message.
		 */
		
		if (moduleSwapsHeaderRecordList!=null && moduleSwapsHeaderRecordList.size()>0){
			Iterator iterator = moduleSwapsHeaderRecordList.iterator();
			while (iterator.hasNext()){
				ModuleSwapsHeaderRecord moduleSwapsRecord = (ModuleSwapsHeaderRecord)iterator.next();
				insertIntoModuleSwapsDef(moduleSwapsRecord);
			}
		}			
		
	}

	private void insertIntoModuleSwapsDef(ModuleSwapsHeaderRecord moduleSwapsHeaderRecord) {
		
	
		moduleSwapsDao.insertModuleSwapsDef(moduleSwapsHeaderRecord.getSalesOrder(), //String superiorNetworkAct,
										moduleSwapsHeaderRecord.getDOKNo(),// String subNetworkAct,
										moduleSwapsHeaderRecord.getCfgModuleCode(),// String superiorNetworkActDesc, 
										moduleSwapsHeaderRecord.getSerialNo(),// String moduleNetworkNo, 
										moduleSwapsHeaderRecord.getCustSerialNo(),// String subNetworkActDesc,
										moduleSwapsHeaderRecord.getUpdtUserId(),// String moduleCatCode, 
										moduleSwapsHeaderRecord.getUpdtTime(),// String moduleCatDesc, 
										moduleSwapsHeaderRecord.getMessageAction());// String workCenter, 
		
		if ("DEL".equalsIgnoreCase(moduleSwapsHeaderRecord.getMessageAction())) {
			String newSalesOrder = "UNASSIGNED";		//moduleSwapsHeaderRecord.getSalesOrder().trim() + "-HOLD";
			List orderIdList = moduleSwapsDao.getOrderId(moduleSwapsHeaderRecord.getSerialNo());
			
			String updtUserId = ContextUtil.getUser().getUsername();
			
			if (orderIdList != null && !orderIdList.isEmpty())
			{
				Iterator iterator = orderIdList.iterator();
				while (iterator.hasNext())
				{
					Map map = (Map) iterator.next();
					String orderId = (String)map.get("ORDER_ID");

					moduleSwapsDao.updatePwustOrders(newSalesOrder, orderId, updtUserId);
					//update the outgoing sales order
					moduleSwapsDao.updateOutgoingSalesOrder(orderId ,newSalesOrder);

				}
			}

//			List salesOrderList = moduleSwapsDao.getExsitsPwustSalesOrder(orderId);		//moduleSwapsHeaderRecord.getSalesOrder(),moduleSwapsHeaderRecord.getCfgModuleCode(), moduleSwapsHeaderRecord.getSerialNo()
//			if (salesOrderList!=null && !salesOrderList.isEmpty())
//			{
//				Iterator iterator = salesOrderList.iterator();
//
//				while (iterator.hasNext())
//				{
//					Map map = (Map) iterator.next();
//					String salesOrder = (String)map.get("SALES_ORDER");
//					String engineModel = (String) map.get("ENGINE_MODEL");
//					String actNo = (String) map.get("ACT_NO");
//					String subActNo = (String) map.get("SUB_ACT_NO");
//					String workScope = (String) map.get("WORK_SCOPE");
//					String serialNo = (String) map.get("SERIAL_NO");
//					String workLoc = (String) map.get("WORK_LOC");
//					String confirmNo = (String) map.get("CONFIRM_NO");
//					
//					String modExists = moduleSwapsDao.checkExsitsPwustOrders(salesOrder, engineModel);
//					String updtUserId = ContextUtil.getUser().toString();
//					if ("Y".equals(modExists)) {
//						//moduleSwapsDao.insertPwustOrders(newSalesOrder, engineModel, workScope, salesOrder, engineModel, updtUserId);
//						moduleSwapsDao.updatePwustOrders(newSalesOrder, orderId, updtUserId);
//					}
//					
//					//delete the outgoing sales order
//					moduleSwapsDao.updateOutgoingSalesOrder(salesOrder,engineModel,newSalesOrder);
//				}
//			}
			
		} else if ("REL".equalsIgnoreCase(moduleSwapsHeaderRecord.getMessageAction())) {
			String newSalesOrder = moduleSwapsHeaderRecord.getSalesOrder().trim();
			List orderIdList = moduleSwapsDao.getOrderId(moduleSwapsHeaderRecord.getSerialNo(), moduleSwapsHeaderRecord.getCfgModuleCode());
			String updtUserId = ContextUtil.getUser().getUsername();
			
			if (orderIdList != null && !orderIdList.isEmpty())
			{
				Iterator iterator = orderIdList.iterator();
				while (iterator.hasNext())
				{
					Map map = (Map) iterator.next();
					String orderId = (String)map.get("ORDER_ID");

					String modExists = moduleSwapsDao.checkExsitsPwustOrders(orderId);
					
					if ("Y".equals(modExists)) 
					{ //module serial exists in the unassigned bucket.  Assigned it to the new sales order
						
						moduleSwapsDao.updatePwustOrders(newSalesOrder, orderId, updtUserId);
						//update the outgoing sales order
						moduleSwapsDao.updateOutgoingSalesOrder(orderId ,newSalesOrder);

					} else 
					{ //doesn't exist in the unassigned bucket.  Just assigned it to the new sales order
						moduleSwapsDao.updatePwustOrders(newSalesOrder, orderId, updtUserId);
						//update the outgoing sales order
						moduleSwapsDao.updateOutgoingSalesOrder(orderId ,newSalesOrder, moduleSwapsHeaderRecord.getSerialNo(), null);
						
					}
					

				}
			} else {
				//Module serial Number doesn't exists
				orderIdList = moduleSwapsDao.getOrderId( moduleSwapsHeaderRecord.getCfgModuleCode(), newSalesOrder, null);
				if (orderIdList != null && !orderIdList.isEmpty())
				{
					Iterator iterator = orderIdList.iterator();
					while (iterator.hasNext())
					{
						Map map = (Map) iterator.next();
						String orderId = (String)map.get("ORDER_ID");
						
						moduleSwapsDao.updatePwustOrders(newSalesOrder, orderId, updtUserId);
						//update the outgoing sales order
						moduleSwapsDao.updateOutgoingSalesOrder(orderId ,newSalesOrder, moduleSwapsHeaderRecord.getSerialNo(), null);
						
					}
				}
				
			}

			
			
//			//String orderId = moduleSwapsDao.getOrderId(moduleSwapsHeaderRecord.getSerialNo());
//			String prevSalesOrder = moduleSwapsDao.getSalesOrder(moduleSwapsHeaderRecord.getCfgModuleCode(),moduleSwapsHeaderRecord.getSerialNo()); //moduleSwapsHeaderRecord.getDOKNo(),
//			List salesOrderList = moduleSwapsDao.getExsitsPwustSalesOrder(moduleSwapsHeaderRecord.getCfgModuleCode(), moduleSwapsHeaderRecord.getSerialNo());		//moduleSwapsHeaderRecord.getSalesOrder(),moduleSwapsHeaderRecord.getCfgModuleCode()
//			if (salesOrderList!=null && !salesOrderList.isEmpty())
//			{
//				Iterator iterator = salesOrderList.iterator();
//
//				while (iterator.hasNext())
//				{
//					Map map = (Map) iterator.next();
//					String salesOrder = (String)map.get("SALES_ORDER");
//					String engineModel = (String) map.get("ENGINE_MODEL");
//					String actNo = (String) map.get("ACT_NO");
//					String subActNo = (String) map.get("SUB_ACT_NO");
//					String workScope = (String) map.get("WORK_SCOPE");
//					String serialNo = (String) map.get("SERIAL_NO");
//					String workLoc = (String) map.get("WORK_LOC");
//					String confirmNo = (String) map.get("CONFIRM_NO");
//					
//					String modExists = moduleSwapsDao.checkExsitsPwustOrders(salesOrder, engineModel);
//					String updtUserId = ContextUtil.getUser().toString();
//					
//
//					if (!"".equals(prevSalesOrder)) {
//						moduleSwapsDao.updatePwustOrders(newSalesOrder, salesOrder, engineModel, updtUserId);
//
//						//update the outgoing sales order
//						moduleSwapsDao.updateOutgoingSalesOrder(salesOrder,engineModel,newSalesOrder);
//					}
//				}
//			}
			
			
		}
		
	}


	protected HashMap getTableColumnSizes() {
		HashMap hashMap = new HashMap();
		List columnDataList = moduleSwapsDao.getPwustModuleSwapsLogColumnLenghts();
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

	private ModuleSwapsHeaderRecord mapModuleSwapsHeaderRecord(List recordKeyList,HashMap columnHashMap) throws MappingException {


		int k = 0;
		ModuleSwapsHeaderRecord moduleSwapsHeaderRecord=null;

		if (recordKeyList!=null && !recordKeyList.isEmpty()){

			moduleSwapsHeaderRecord = new ModuleSwapsHeaderRecord();

			for (int i=0; i<recordKeyList.size(); i++) {

				NameValuePairType nameValuePairType = (NameValuePairType) recordKeyList.get(i);
				String recordKeyName = nameValuePairType.getName();

				if ("SALES_ORDER".equalsIgnoreCase(recordKeyName)){
					moduleSwapsHeaderRecord.setSalesOrder(nameValuePairType.getStringValue());

					if (StringUtils.isNotBlank(moduleSwapsHeaderRecord.getSalesOrder())){
						Number tableColMaxLen = (Number)columnHashMap.get("SALES_ORDER");
						if (tableColMaxLen!=null && moduleSwapsHeaderRecord.getSalesOrder().length() > tableColMaxLen.intValue()){
							throw new MappingException ("SALES_ORDER max size is " + tableColMaxLen.intValue());
						}
					}


				}else if ("DOKNR".equalsIgnoreCase(recordKeyName)){
					moduleSwapsHeaderRecord.setDOKNo(nameValuePairType.getStringValue());

					if (StringUtils.isNotBlank(moduleSwapsHeaderRecord.getDOKNo())){

						Number tableColMaxLen = (Number)columnHashMap.get("DOKNR");
						if (tableColMaxLen!=null && moduleSwapsHeaderRecord.getDOKNo().length() > tableColMaxLen.intValue()){
							throw new MappingException ("DOKNR max size is " + tableColMaxLen.intValue());
						}
					}


				}else if ("CFG_MODULE_CODE".equalsIgnoreCase(recordKeyName)){
					moduleSwapsHeaderRecord.setCfgModuleCode(nameValuePairType.getStringValue());

					if (StringUtils.isNotBlank(moduleSwapsHeaderRecord.getCfgModuleCode())){
						Number tableColMaxLen = (Number)columnHashMap.get("CFG_MODULE_CODE");
						if (tableColMaxLen!=null && moduleSwapsHeaderRecord.getCfgModuleCode().length() > tableColMaxLen.intValue()){
							throw new MappingException ("CFG_MODULE_CODE max size is " + tableColMaxLen.intValue());
						}
					}

				}else if ("SERIAL_NO".equalsIgnoreCase(recordKeyName)){
					moduleSwapsHeaderRecord.setSerialNo(nameValuePairType.getStringValue());

					if (StringUtils.isNotBlank(moduleSwapsHeaderRecord.getSerialNo())){
						Number tableColMaxLen = (Number)columnHashMap.get("SERIAL_NO");
						if (tableColMaxLen!=null && moduleSwapsHeaderRecord.getSerialNo().length() > tableColMaxLen.intValue()){
							throw new MappingException ("SERIAL_NO max size is " + tableColMaxLen.intValue());
						}
					}


				}else if ("CUST_SERIAL_NO".equalsIgnoreCase(recordKeyName)){
					moduleSwapsHeaderRecord.setCustSerialNo(nameValuePairType.getStringValue());

					if (StringUtils.isNotBlank(moduleSwapsHeaderRecord.getCustSerialNo())){
						Number tableColMaxLen = (Number)columnHashMap.get("CUST_SERIAL_NO");
						if (tableColMaxLen!=null && moduleSwapsHeaderRecord.getCustSerialNo().length() > tableColMaxLen.intValue()){
							throw new MappingException ("CUST_SERIAL_NO max size is " + tableColMaxLen.intValue());
						}
					}

				}else if ("UPDT_USERID".equalsIgnoreCase(recordKeyName)){
					moduleSwapsHeaderRecord.setUpdtUserId(nameValuePairType.getStringValue());

					if (StringUtils.isNotBlank(moduleSwapsHeaderRecord.getUpdtUserId())){
						Number tableColMaxLen = (Number)columnHashMap.get("UPDT_USERID");
						if (tableColMaxLen!=null && moduleSwapsHeaderRecord.getUpdtUserId().length() > tableColMaxLen.intValue()){
							throw new MappingException ("UPDT_USERID max size is " + tableColMaxLen.intValue());
						}
					}

				}else if ("UPDATE_TIME_STAMP".equalsIgnoreCase(recordKeyName)){
					moduleSwapsHeaderRecord.setUpdtTime(nameValuePairType.getStringValue());

					if (StringUtils.isNotBlank(moduleSwapsHeaderRecord.getUpdtTime())){
						Number tableColMaxLen = (Number)columnHashMap.get("MESSAGE_UPDT_TIMESTR");
						if (tableColMaxLen!=null && moduleSwapsHeaderRecord.getUpdtTime().length() > tableColMaxLen.intValue()){
							throw new MappingException ("MESSAGE_UPDT_TIMESTR max size is " + tableColMaxLen.intValue());
						}
					}

				}else if ("MESSAGE_ACTION".equalsIgnoreCase(recordKeyName)){
					moduleSwapsHeaderRecord.setMessageAction(nameValuePairType.getStringValue());

					if (StringUtils.isNotBlank(moduleSwapsHeaderRecord.getMessageAction())){
						Number tableColMaxLen = (Number)columnHashMap.get("MESSAGE_ACTION");
						if (tableColMaxLen!=null && moduleSwapsHeaderRecord.getMessageAction().length() > tableColMaxLen.intValue()){
							throw new MappingException ("MESSAGE_ACTION max size is " + tableColMaxLen.intValue());
						}
					}

				}
			}


		}
		return moduleSwapsHeaderRecord;
	}

	
}
