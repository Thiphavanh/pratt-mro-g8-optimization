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
 *  File:    M309MessageProcessor.java
 * 
 *  Created: 2019-08-23
 * 
 *  Author:  Brendan Polak 
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2019-08-23		B.Polak 		Initial Release SMRO_M309 - Tooling Serial Interface
 *  2020-04-02      D.Miron         Defect 1617:  Added action code "Add".
 *  2020-13-04      G.Rodriguez     T309 tooling interface SODMRO2 Errors								
 *  2020-04-20      D.Miron         Defect 1617: Resolved duplicate tool error and issue caused by 
 *                                  Defect 1135 when Repair Execution code was merged with Overhaul.
 *  2020-06-26      D.Miron         Defect 1686: When MANSERNO is null in XML, set MANSERNO=EQUIP_NO
 *  2020-07-15      S.Edgerly       Defect 1686: Multi-Tool Records now supported, logs multiple errors in WARNING_TEXT
 *  2021-02-22      J DeNinno       Defect 1867 change Tool Calibration days to N and TOOL_CALIBRATION_DAYS_FREQ to null
 */

package com.pw.solumina.M309.integration.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.openapplications.oagis.x9.ActionCriteriaType;
import org.openapplications.oagis.x9.ActionExpressionType;
import org.openapplications.oagis.x9.ApplicationAreaType;
import org.openapplications.oagis.x9.IdentifierType;
import org.openapplications.oagis.x9.KeysType;
import org.openapplications.oagis.x9.NameValuePairType;
import org.openapplications.oagis.x9.RecordType;
import org.openapplications.oagis.x9.SyncRecordDataAreaType;
import org.openapplications.oagis.x9.SyncRecordDocument;
import org.openapplications.oagis.x9.SyncRecordType;


import com.ibaset.common.Reference;
import com.ibaset.common.util.OAGIUtils;
import com.ibaset.solumina.integration.common.BaseMessageProcessor;
import com.ibaset.solumina.integration.common.ProcessingMessage;
import com.ibaset.solumina.integration.exception.MappingException;
import com.ibaset.solumina.integration.logging.MessageLogger;
import com.ibaset.solumina.integration.logging.MessageLoggerParams;
import com.ibaset.solumina.sffnd.application.ITool;
import com.ibaset.solumina.sffnd.dao.IToolDao;
import com.ibaset.solumina.sfpl.application.IItem;
import com.pw.solumina.M309.integration.IM309MessageProcessor;
import com.pw.solumina.interfaces.dao.IM309DaoPW;

public class M309MessageProcessor extends BaseMessageProcessor implements IM309MessageProcessor {

	@Reference	
	private ITool toolImpl = null;
	
	@Reference	
	private IItem item = null;
	
	@Reference  
	private OAGIUtils oagiUtils = null;
	
	@Reference	
	private IM309DaoPW m309DaoPW = null;
	
    @Reference
    private IToolDao sffndToolDao = null;
	
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
	
	
	
	@Override
	public void processMessage(String messageText, MessageLoggerParams loggerParams) {
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
	
	@Override
	public void doProcessing(String messageText, MessageLoggerParams logger) throws Throwable {
		
			
		//TODO ? 301 overwrites the parseMessage method ,but why?
		XmlObject inboundObj = parseMessage(messageText);

		// Check XML is formatted correctly
		oagiUtils.validateXml(inboundObj);
		if (!(inboundObj instanceof SyncRecordDocument)) {
			throw new Exception("XML message is not SyncRecordDocument ");
		}

		
		// Parse XML Message into Java Objects
		String iDocNumber = null;
		String actionCode = null;
		String toolIds = null;
		String serialIds = null;
		ArrayList<ToolRecord> tools = new ArrayList<ToolRecord>();

		SyncRecordDocument syncRecordDocument = (SyncRecordDocument) inboundObj;
		SyncRecordType syncRecord = syncRecordDocument.getSyncRecord();

		SyncRecordDataAreaType dataArea = syncRecord.getDataArea();
		ApplicationAreaType applicationArea = syncRecord.getApplicationArea();
		
		//get idoc number
		IdentifierType identifier = applicationArea.getBODID();
		iDocNumber = identifier.getStringValue();
		logger.setRef1(iDocNumber);
		
		//get action code
		List<ActionCriteriaType> actionCriterias = dataArea.getSync().getActionCriteriaList();
		Iterator<ActionCriteriaType> iterAC = actionCriterias.iterator();
		if (iterAC.hasNext()) {
			ActionCriteriaType actionCriteria = iterAC.next();
			Iterator<ActionExpressionType> iterAE = actionCriteria.getActionExpressionList().iterator();
			if (iterAE.hasNext()) {
				ActionExpressionType actionExpression = iterAE.next();
				actionCode = actionExpression.getActionCode();
			}
		}

		//get table sizes to validate attr lengths
		HashMap<String, Number> tableSizes = getTableColumnSizes();
		
		// Read through XML and create a ToolRecord object for each tool,
		// placing them all in tools
		for (RecordType recordType : dataArea.getRecordList()) {
			KeysType keys = recordType.getKeys();
			List<NameValuePairType> recordKeys = keys.getRecordKeyList();
			ToolRecord newTool = new ToolRecord();

			// read through all recordkeys and assign ToolRecord values
			for (NameValuePairType recordKey : recordKeys) {
				if (recordKey.getName().equals("EQUIP_NO")) {
					if(tableSizes.get("TOOL_SERIAL_NO") != null && recordKey.getStringValue().length() > tableSizes.get("TOOL_SERIAL_NO").intValue())
					{throw new MappingException ("EQUIP_NO max size is " + tableSizes.get("TOOL_SERIAL_NO").intValue());}
					newTool.setEquip_no(recordKey.getStringValue());
				}
				if (recordKey.getName().equals("EQUIP_TYPE")) {
					if(tableSizes.get("UCF_TOOLSRL_VCH1") != null && recordKey.getStringValue().length() > tableSizes.get("UCF_TOOLSRL_VCH1").intValue())
						{throw new MappingException ("EQUIP_TYPE max size is " + tableSizes.get("UCF_TOOLSRL_VCH1").intValue());}
					newTool.setEquip_type(recordKey.getStringValue());
				}
				if (recordKey.getName().equals("EQUIP_DESC")) {
					if(tableSizes.get("UCF_TOOLSRL_VCH2") != null && recordKey.getStringValue().length() > tableSizes.get("UCF_TOOLSRL_VCH2").intValue())
						{throw new MappingException ("EQUIP_DESC max size is " + tableSizes.get("UCF_TOOLSRL_VCH2").intValue());}
					newTool.setEquip_desc(recordKey.getStringValue().toUpperCase());
				}
				if (recordKey.getName().equals("PLANT")) {
					if(tableSizes.get("UCF_ITEM_VCH5") != null && recordKey.getStringValue().length() > tableSizes.get("UCF_ITEM_VCH5").intValue())
					{throw new MappingException ("PLANT max size is " + tableSizes.get("UCF_ITEM_VCH5").intValue());}
					newTool.setPlant(recordKey.getStringValue());
				}
				if (recordKey.getName().equals("MATERIAL")) {
					if(tableSizes.get("PART_NO") != null && recordKey.getStringValue().length() > tableSizes.get("PART_NO").intValue())
					{throw new MappingException ("MATERIAL max size is " + tableSizes.get("PART_NO").intValue());}
					newTool.setMaterial(recordKey.getStringValue());
				}
				if (recordKey.getName().equals("MATERIAL_DESC")) {
					if(tableSizes.get("PART_TITLE") != null && recordKey.getStringValue().length() > tableSizes.get("PART_TITLE").intValue())
					{throw new MappingException ("MATERIAL_DESC max size is " + tableSizes.get("PART_TITLE").intValue());}
					newTool.setMaterial_desc(recordKey.getStringValue());
				}
				if (recordKey.getName().equals("FUNC_LOC")) {
					if(tableSizes.get("UCF_TOOLSRL_VCH3") != null && recordKey.getStringValue().length() > tableSizes.get("UCF_TOOLSRL_VCH3").intValue())
						{throw new MappingException ("FUNC_LOC max size is " + tableSizes.get("UCF_TOOLSRL_VCH3").intValue());}
					newTool.setFunc_loc(recordKey.getStringValue());
				}
				if (recordKey.getName().equals("FUNC_LOC_DESC")) {
					if(tableSizes.get("UCF_TOOLSRL_VCH4") != null && recordKey.getStringValue().length() > tableSizes.get("UCF_TOOLSRL_VCH4").intValue())
						{throw new MappingException ("FUNC_LOC_DESC max size is " + tableSizes.get("UCF_TOOLSRL_VCH4").intValue());}
					newTool.setFunc_loc_desc(recordKey.getStringValue());
				}
				if (recordKey.getName().equals("LAST_CALB")) {      // Defect 1617 4/20/2020
					try{
						Date in = dateFormatter.parse(recordKey.getStringValue());
						newTool.setLast_calb(in);						
						}
					catch (ParseException e)
						{throw new MappingException ("LAST_CALIB is not a valid date format");}
				}
				if (recordKey.getName().equals("NEXT_CALB")) {      // Defect 1617 4/20/2020
					try{
						Date in = dateFormatter.parse(recordKey.getStringValue());
						newTool.setNext_calb(in);						
						}
					catch (ParseException e)
					{throw new MappingException ("NEXT_CALIB is not a valid date format");}
				}
				if (recordKey.getName().equals("OWNER")) {
					if(tableSizes.get("UCF_TOOLSRL_VCH5") != null && recordKey.getStringValue().length() > tableSizes.get("UCF_TOOLSRL_VCH5").intValue())
						{throw new MappingException ("OWNER max size is " + tableSizes.get("UCF_TOOLSRL_VCH5").intValue());}
					newTool.setOwner(recordKey.getStringValue());
				}
				
				if (recordKey.getName().equals("STATUS")) {
					if(tableSizes.get("UCF_TOOLSRL_VCH6") != null && recordKey.getStringValue().length() > tableSizes.get("UCF_TOOLSRL_VCH6").intValue())
						{throw new MappingException ("STATUS max size is " + tableSizes.get("UCF_TOOLSRL_VCH6").intValue());}
					newTool.setStatus(recordKey.getStringValue());
				}
				
				//Defect 1617 - MANSERNO value
				if (recordKey.getName().equals("MANSERNO")) {
					if(tableSizes.get("UCF_TOOLSRL_VCH8") != null && recordKey.getStringValue().length() > tableSizes.get("UCF_TOOLSRL_VCH8").intValue())
						{throw new MappingException ("MANSERNO max size is " + tableSizes.get("UCF_TOOLSRL_VCH8").intValue());}
					newTool.setManserno(recordKey.getStringValue());
				}
				
				//Defect 1617 - INVENTORY value
				if (recordKey.getName().equals("INVENTORY")) {
					if(tableSizes.get("UCF_TOOLSRL_VCH9") != null && recordKey.getStringValue().length() > tableSizes.get("UCF_TOOLSRL_VCH9").intValue())
						{throw new MappingException ("INVENTORY max size is " + tableSizes.get("UCF_TOOLSRL_VCH9").intValue());}
					newTool.setInventory(recordKey.getStringValue());
				}
				
				//Defect 1617 - U_STATUS value
				if (recordKey.getName().equals("U_STATUS")) {
					if(tableSizes.get("UCF_TOOLSRL_VCH10") != null && recordKey.getStringValue().length() > tableSizes.get("UCF_TOOLSRL_VCH10").intValue())
						{throw new MappingException ("U_STATUS max size is " + tableSizes.get("UCF_TOOLSRL_VCH10").intValue());}
					newTool.setUserStatus(recordKey.getStringValue());
				}
				
			}
			tools.add(newTool);
			if(toolIds == null)
				{toolIds = newTool.getMaterial();}
			else
				{toolIds += ", "+newTool.getMaterial();}
			if(serialIds == null)
				{serialIds = newTool.getEquip_no();}
			else
				{serialIds += ", "+newTool.getEquip_no();}
		}
		logger.setRef2(toolIds);
		logger.setRef3(serialIds);
		//XML parsing complete
		
		//action code change = create new tools
		StringBuffer warningText = new StringBuffer();
		for(ToolRecord tool: tools)
		{	
			try{//ensures code completes if any errors are detected.
				// Defect 1686
				if(tool.getManserno() == null || tool.getManserno().isEmpty())
				{
					tool.setManserno(tool.getEquip_no());
				}
				
				//check if new tool or just new serial
				String item_id = m309DaoPW.getItemId(tool.getMaterial(), "N/A");
				if(item_id == null)
				{
					//new tool & new serial
					insertTool(tool);
					item_id = m309DaoPW.getItemId(tool.getMaterial(), "N/A");
					tool.setItem_id(item_id);
					updateSecurity(tool);
					insertSerial(tool);	
				}
				else
				{
					//existing tool & new serial 
					tool.setItem_id(item_id);
					updateSecurity(tool);
	
					String location_id = m309DaoPW.getLocationIDforPlant(tool.getPlant());
					boolean ipdct = m309DaoPW.itemProgramDetailContainsTool(item_id, location_id);
					if(!ipdct)
					{
						//if tool+plant is not in itemProgramDetail table then we must insert so update will find them.
						m309DaoPW.insertIntoItemProgramDetail(tool.getItem_id(), location_id);
					}
					updateTool(tool);
	
					// Defect 1617 4/20/2020
					boolean toolSerialExists = false;
					toolSerialExists = sffndToolDao.selectToolSeriaInformationlExists(tool.getMaterial(),
							tool.getEquip_no());
					if(toolSerialExists)  
					{
						updateSerial(tool);
					}
					else
					{
						insertSerial(tool);
					}
				}	
				//defect 1686
			}catch(Exception e){
				//captures multiple tool record errors
				warningText.append(e.getMessage());
				warningText.append("\n");
				ProcessingMessage m = new ProcessingMessage(null, e.getMessage());
				logger.getProcessingMessages().add(m);
			}
		}
		//This marks the message as needing review in the log table
		if(warningText.length() > 0){
			logger.setWarningText(warningText.toString()); //markes the messase as needing review
		}
		//defect 1686
	}
	
	
	
	private void insertTool(ToolRecord toolRecord) throws ParseException, Throwable
	{
		item.insertItemDesc(
				toolRecord.getMaterial(),
				"N/A",
				"TOOL",
				"DURABLE",
				null,
				toolRecord.getPlant(),
				toolRecord.getMaterial_desc(),
				"EA",
				"N",
				"N",
				"N",
				"N",
				"N",
				"N",
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null, 
				null,
				null, 
				null,
				null,
				null,
				null,
				null,
				null,
				"PW_R_M309_TOOL", 
				"N",
				"N",
				"N",
				"N", 
				"N",
				"N",
				"N",
				null,
				null,
				null,
				null,
				"N",
				"N",
				null,
				null,
				"N",
				"N",  //defect 1867
				"N", 
				"N",
				null, // defect 1867
				null,
				null,
				null,
				null, 
				null, 
				null, 
				"N", 
				null, 
				null, 
				null, 
				null, 
				null,
				null, 
				null, 
				null, 
				null, 
				null, 
				null,
				null, 
				null, 
				null, 
				null, 
				null,
				null, 
				null, 
				null, 
				null, 
				null, 
				null,
				null, 
				null, 
				null, 
				null, 
				null,
				null,
				null, 
				null,
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				"N");
	}
			
	private void insertSerial(ToolRecord toolRecord) throws ParseException
	{
		toolImpl.insertSerial(
				toolRecord.getMaterial(),
				"N/A",
				toolRecord.getEquip_no(),
				null,
				toolRecord.getLast_calb(),
				"N",  //defect 1867
				"N",
				"N",
				null, // defect 1867
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				toolRecord.getPlant(),
				null,
				null,
				null,
				null,
				null,
				toolRecord.getEquip_type(),
				toolRecord.getEquip_desc(),
				toolRecord.getFunc_loc(),
				toolRecord.getFunc_loc_desc(),
				toolRecord.getOwner(),
				toolRecord.getStatus(),
				null,
				toolRecord.getManserno(),//Defect 1617
				toolRecord.getInventory(),//Defect 1617
				toolRecord.getUserStatus(),//Defect 1617
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				toolRecord.getNext_calb(),
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null);
		
	}
	
	private void updateTool(ToolRecord toolRecord) throws ParseException
	{
		item.updateItemDesc(
				toolRecord.getItem_id(),  
				toolRecord.getMaterial(),
				"N/A",
				"TOOL",
				"DURABLE",
				null,
				toolRecord.getPlant(),
				toolRecord.getMaterial_desc(),
				"EA",
				"N",
				"N",
				"N",
				"N",
				"N",
				"N",
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				"N",
				"N",
				"N",
				"N",
				"N",
				"N",
				"N",
				"N",
				null,
				null,
				null,
				null,
				"N",
				"N",
				null,
				null,
				"N",
				"N",  //defect 1867
				"N",
				"N",
				null, //defect 1867
				null,
				null,
				null,
				null,
				null,
				null,
				"N",
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				"N",
				null);
	}

	private void updateSerial(ToolRecord toolRecord) throws ParseException
	{
		toolImpl.updateSerial(
				toolRecord.getMaterial(),
				"N/A",
				toolRecord.getEquip_no(),
				null,
				toolRecord.getLast_calb(),
				"N",  //defect 1867
				"N",
				"N",
				null, //defect 1867
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				toolRecord.getNext_calb(),  //null defect 1867
				null,
				null,
				toolRecord.getPlant(),
				null,
				null,
				toolRecord.getEquip_type(),
				toolRecord.getEquip_desc(),
				toolRecord.getFunc_loc(),
				toolRecord.getFunc_loc_desc(),
				toolRecord.getOwner(),
				toolRecord.getStatus(),
				null,
				toolRecord.getManserno(),//Defect 1617
				toolRecord.getInventory(),//Defect 1617
				toolRecord.getUserStatus(),//Defect 1617
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				toolRecord.getNext_calb(),
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null);
		
	}

	private void updateSecurity(ToolRecord toolRecord)throws Throwable
	{
		//lookup current security groups
		StringTokenizer currentSecurityGroups = null;
		String currentSecurityGroupString = m309DaoPW.getSecurityGroupForItem(toolRecord.getItem_id());
		if(currentSecurityGroupString != null)   // Defect 1617
		{
			currentSecurityGroups = new StringTokenizer(currentSecurityGroupString,",");
		}
		
		
		//lookup security group for new plant
		String newSecurityGroup = m309DaoPW.getSecurityGroupForPlant(toolRecord.getPlant());
		//Defect 1617
		if(newSecurityGroup == null)
		{
			throw new Exception("Security Group not found in table UTASGI_USER_SEC_GRP_XREF for plant " + toolRecord.getPlant());
		}
		//End Defect 1617
		
		//determine if this is a new security group
		boolean isPlantNew = true; 
		String currentSecurityGroup = null;
		
		if(currentSecurityGroupString != null)
		{
			while(currentSecurityGroups.hasMoreTokens())
			{
				
				currentSecurityGroup = currentSecurityGroups.nextToken().trim();
				if(currentSecurityGroup.equals(newSecurityGroup))
					{
						isPlantNew = false;
					}
			}
		}
		
		
		//if plant is new then we need to update the security groups
		if(isPlantNew)
		{
			if(!m309DaoPW.itemDescSecGrpContainsTool(toolRecord.getMaterial()) && 
					currentSecurityGroupString != null)      // Defect 1617
			{
				//no entries in itemDescSecGrp table, need to add original plant info
				currentSecurityGroups = new StringTokenizer(currentSecurityGroupString,",");
				while(currentSecurityGroups.hasMoreTokens())
				{
					m309DaoPW.insertIntoItemDescSecGroup(toolRecord.getMaterial(), currentSecurityGroups.nextToken());
				}
			}
			
			// add new info into itemDescDecGrp
			try      // Defect 1617 4/20/2020
			{
				m309DaoPW.insertIntoItemDescSecGroup(toolRecord.getMaterial(), newSecurityGroup);			
			} catch (Exception e) {
				// do nothing
			}
		
			//build new security group string
			StringBuffer securityGroup = new StringBuffer();
			
			//add all security groups to a set
			HashSet<String> securityGroups = new HashSet<String>();
			securityGroups.add(newSecurityGroup);
			if(currentSecurityGroupString != null)    // Defect 1617
			{
				StringTokenizer tokens = new StringTokenizer(currentSecurityGroupString, ",");
				while(tokens.hasMoreTokens())
				{
					securityGroups.add(tokens.nextToken());
				}
			}
			
			//convert set to string
			Iterator<String> iter = securityGroups.iterator();
			securityGroup.append(iter.next());
			while(iter.hasNext())
			{
				securityGroup.append(", ");
				securityGroup.append(iter.next());
			}
		
			
			//update security group 
			m309DaoPW.updateSecurityGroupForItem(toolRecord.getItem_id(), securityGroup.toString());
		}
	}
	
	@Override
	public SchemaType getInboundBodType() {
		return SyncRecordDocument.type;
	}
	
	@SuppressWarnings("rawtypes")
	protected HashMap<String, Number> getTableColumnSizes() {
		HashMap<String, Number> hashMap = new HashMap<String, Number>();
		List columnDataList = m309DaoPW.getM309ColumnLengths();
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
	

	private class ToolRecord {
		String item_id;
		String equip_no;
		String equip_type;
		String equip_desc;
		String plant;
		String material;
		String material_desc;
		String func_loc;
		String func_loc_desc;
		Date last_calb;
		Date next_calb;;
		String owner;
		String status;
		//Start Defect 1617
		String inventory;
		String manserno;
		String userStatus;
		
		public String getInventory() {
			return inventory;
		}
		
		public void setInventory(String inventory) {
			this.inventory = inventory;
		}
		
		public String getManserno() {
			return manserno;
		}
		
		public void setManserno(String manserno) {
			this.manserno = manserno;
		}
		
		public String getUserStatus() {
			return userStatus;
		}
		
		public void setUserStatus(String userStatus) {
			this.userStatus = userStatus;
		}
		//End Defect 1617
		
		public String getItem_id() {
			return item_id;
		}

		public void setItem_id(String item_id) {
			this.item_id = item_id;
		}

	

		public String getEquip_no() {
			return equip_no;
		}

		public void setEquip_no(String equip_no) {
			this.equip_no = equip_no;
		}

		public String getEquip_type() {
			return equip_type;
		}

		public void setEquip_type(String equip_type) {
			this.equip_type = equip_type;
		}

		public String getEquip_desc() {
			return equip_desc;
		}

		public void setEquip_desc(String equip_desc) {
			this.equip_desc = equip_desc;
		}

		public String getPlant() {
			return plant;
		}

		public void setPlant(String plant) {
			this.plant = plant;
		}

		public String getMaterial() {
			return material;
		}

		public void setMaterial(String material) {
			this.material = material;
		}

		public String getMaterial_desc() {
			return material_desc;
		}

		public void setMaterial_desc(String material_desc) {
			this.material_desc = material_desc;
		}

		public String getFunc_loc() {
			return func_loc;
		}

		public void setFunc_loc(String func_loc) {
			this.func_loc = func_loc;
		}

		public String getFunc_loc_desc() {
			return func_loc_desc;
		}

		public void setFunc_loc_desc(String func_loc_desc) {
			this.func_loc_desc = func_loc_desc;
		}

		public Date getLast_calb() {
			return last_calb;
		}

		public void setLast_calb(Date last_calb) {
			this.last_calb = last_calb;
		}

		public Date getNext_calb() {
			return next_calb;
		}

		public void setNext_calb(Date next_calb) {
			this.next_calb = next_calb;
		}

		public String getOwner() {
			return owner;
		}

		public void setOwner(String owner) {
			this.owner = owner;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

	}
}
