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
*  File:    T221MessageProcessor.java
* 
*  Created: 2017-09-25
* 
*  Author:  xcs4331
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-09-25 	xcs4331		    Initial Release XXXXXXX
* 2019-02-20    xcs4331         defect 1130, process new xml format 
* 2019-03-01    D.Miron         Updated for G8R2SP4
* 2019-03-18    xcs4331         defect 1130, added code to insert program into sffnd_program_def table is engine type is missing
* 2019-03-18    xcs4331         defect 1130, added key data of loggerParams.setRef2  
*/

package com.pw.solumina.T221.integration.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openapplications.oagis.x9.SyncRecordDocument;

import com.ibaset.common.Reference;
import com.ibaset.solumina.integration.common.BaseMessageProcessor;
import com.ibaset.common.util.OAGIUtils; // G8R2SP4
import com.ibaset.solumina.integration.exception.MappingException;
import com.ibaset.solumina.integration.exception.ParsingException;
import com.ibaset.solumina.integration.logging.MessageLogger;
import com.ibaset.solumina.integration.logging.MessageLoggerParams;
import com.pw.solumina.sfpl.dao.StandardNetworkDao;

import org.openapplications.oagis.x9.KeysType;
import org.openapplications.oagis.x9.NameValuePairType;
import org.openapplications.oagis.x9.RecordType;
import org.openapplications.oagis.x9.SyncRecordDataAreaType;
import org.openapplications.oagis.x9.SyncRecordType;


/**
 * @author xcs4331
 *
 */
public class M221MessageProcessor extends BaseMessageProcessor implements IM221MessageProcessor{


	@Reference	private StandardNetworkDao standardNetworkDao;
	@Reference  private OAGIUtils oagiUtils = null;  // G8R2SP4
	
	@Override
	public SchemaType getInboundBodType() {
		
		return SyncRecordDocument.type;
	}

	@Override
	public XmlObject parseMessage(String inboundMessage) throws ParsingException{
		
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
	public void processMessage(String messageText, MessageLoggerParams loggerParams){  

		try 
		{
			doProcessing (messageText, loggerParams);
			loggerParams.setStatus(MessageLogger.STATUS_RECEIVE_SUCCESS);
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
	public void doProcessing(String message, MessageLoggerParams loggerParams) throws Throwable {		
	

		XmlObject inboundBod = parseMessage(message);
		oagiUtils.validateXml(inboundBod);

		if(inboundBod instanceof SyncRecordDocument) {
			
			SyncRecordDocument syncRecordDocument = (SyncRecordDocument) inboundBod;

			SyncRecordType syncRecord = syncRecordDocument.getSyncRecord();
			
			if (syncRecord!=null && 
				syncRecord.getApplicationArea()!=null  && 
				syncRecord.getApplicationArea().getBODID()!=null){
				
					String bodId = syncRecord.getApplicationArea().getBODID().getStringValue();
					loggerParams.setRef1(bodId);
			}

			SyncRecordDataAreaType dataArea = syncRecord.getDataArea();
			List<RecordType> recordTypeList = dataArea.getRecordList();
			
			StandardNetworkRecord standardNetworkRecord = null;
			for (int i=0; i<recordTypeList.size(); i++) {

				RecordType recordType = (RecordType) recordTypeList.get(i);
				KeysType keysType = recordType.getKeys();
				List<NameValuePairType> recordKeyList = keysType.getRecordKeyList();				

				HashMap columnHashMap = getTableColumnSizes();
				standardNetworkRecord= mapStandardNetworkRecord(recordKeyList,columnHashMap);

				
				if (standardNetworkRecord!=null){
					
					// defect 1130 start ************************************
					
					
					if (standardNetworkRecord.getMessgeAction()!=null){
						loggerParams.setRef2("MessgeAction=" +standardNetworkRecord.getMessgeAction());
					}else{
						loggerParams.setRef2("MessgeAction=null");
					}
					
					String ref3String = null;
					if (standardNetworkRecord.getNetworkNo()!=null){
						ref3String = "NetworkNo="+standardNetworkRecord.getNetworkNo();
					}else{
						ref3String = "NetworkNo=null";
					}
					if (standardNetworkRecord.getNetworkNodeNo()!=null){
						ref3String =  ref3String + ",NetworkNodeNo="+standardNetworkRecord.getNetworkNodeNo();
					}else{
						ref3String =  ref3String + ",NetworkNodeNo=null";
					}
					loggerParams.setRef3(ref3String);
					
					
					
					String ref4String = null;
					if (standardNetworkRecord.getSubNetworkNo()!=null){
						ref4String =  "SubNetworkNo="+standardNetworkRecord.getSubNetworkNo();
					}else{
						ref4String =  "SubNetworkNo=null";
					}
					if (standardNetworkRecord.getSubNetworkNodeNo()!=null){
						ref4String =  ref4String + ",SubNetworkNodeNo="+standardNetworkRecord.getSubNetworkNodeNo();
					}else{
						ref4String =  ref4String + ",SubNetworkNodeNo=null";
					}
					loggerParams.setRef4(ref4String);
					
					
					// defect 1130 end ************************************
					
  				    if (StringUtils.isNotBlank(standardNetworkRecord.getMessgeAction())){
						processRecordWithMessageAction(standardNetworkRecord);
					}else{
						processRecordWithMessageNoAction(standardNetworkRecord);
					}
				}				
			}
		}else{
			throw new Exception("XML message is not SyncRecordDocument ");
		}
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  void processRecordWithMessageNoAction(StandardNetworkRecord standardNetworkRecord) throws Exception {

		if (StringUtils.isNotBlank(standardNetworkRecord.getNetworkNo()) && 
			StringUtils.isNotBlank(standardNetworkRecord.getNetworkNodeNo()) &&
			StringUtils.isNotBlank(standardNetworkRecord.getSubNetworkNo()) &&
		    StringUtils.isNotBlank(standardNetworkRecord.getSubNetworkNodeNo())){

				List<Map> list = getStandardNetworkRecord(standardNetworkRecord);
				if (list!=null && !list.isEmpty()){
					updateNetworkDef(standardNetworkRecord);
					
				}else{
					insertNetworkDef(standardNetworkRecord);
				}
			}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  void processRecordWithMessageAction(StandardNetworkRecord standardNetworkRecord) throws Exception {

		//  new pk is  network_no, _network_node_no, SUB_NETWORK_NO, SUB_NETWORK_NODE_NO

		
		if (StringUtils.equalsIgnoreCase(standardNetworkRecord.getMessgeAction(), "REL")){
			
			if (StringUtils.isNotBlank(standardNetworkRecord.getNetworkNo()) && 
				StringUtils.isNotBlank(standardNetworkRecord.getNetworkNodeNo()) &&
				StringUtils.isNotBlank(standardNetworkRecord.getSubNetworkNo()) &&
			    StringUtils.isNotBlank(standardNetworkRecord.getSubNetworkNodeNo())){

					List<Map> list = getStandardNetworkRecord(standardNetworkRecord);
					if (list!=null && !list.isEmpty()){
						updateNetworkDef(standardNetworkRecord);
						
					}else{
						insertNetworkDef(standardNetworkRecord);
					}
			}
		}else if (StringUtils.equalsIgnoreCase(standardNetworkRecord.getMessgeAction(), "DEL")){

			deleteStandardNetwork(standardNetworkRecord);
			
		}else if (StringUtils.equalsIgnoreCase(standardNetworkRecord.getMessgeAction(), "CHG")){
			
			if (StringUtils.isNotBlank(standardNetworkRecord.getNetworkNo()) && 
				StringUtils.isNotBlank(standardNetworkRecord.getNetworkNodeNo()) &&
				StringUtils.isNotBlank(standardNetworkRecord.getSubNetworkNo()) &&
				StringUtils.isNotBlank(standardNetworkRecord.getSubNetworkNodeNo())){

					List<Map> list = getStandardNetworkRecord(standardNetworkRecord);
					if (list!=null && !list.isEmpty()){
						updateNetworkDef(standardNetworkRecord);
						
					}else{
						insertNetworkDef(standardNetworkRecord);
					}
			}

		}
	}

	private void deleteStandardNetwork(StandardNetworkRecord standardNetworkRecord) throws Exception {

	
		if (StringUtils.isNotBlank(standardNetworkRecord.getNetworkNo()) && 
			StringUtils.isNotBlank(standardNetworkRecord.getNetworkNodeNo()) &&
			StringUtils.isNotBlank(standardNetworkRecord.getSubNetworkNo()) &&
		    StringUtils.isNotBlank(standardNetworkRecord.getSubNetworkNodeNo())){
			
				standardNetworkDao.updateSandardNetworkRecordToObsolete(standardNetworkRecord.getNetworkNo(),    
						                                                standardNetworkRecord.getNetworkNodeNo(), 
						                                                standardNetworkRecord.getSubNetworkNo(),  
						                                                standardNetworkRecord.getSubNetworkNodeNo());
				
		}else if (StringUtils.isNotBlank(standardNetworkRecord.getNetworkNo()) && 
				  StringUtils.isNotBlank(standardNetworkRecord.getNetworkNodeNo()) &&
				  StringUtils.isBlank(standardNetworkRecord.getSubNetworkNo()) &&
			      StringUtils.isBlank(standardNetworkRecord.getSubNetworkNodeNo())){

				standardNetworkDao.updateSandardNetworkRecordToObsolete1(standardNetworkRecord.getNetworkNo(), standardNetworkRecord.getNetworkNodeNo());
				
		}else if (StringUtils.isBlank(standardNetworkRecord.getNetworkNo()) && 
				  StringUtils.isBlank(standardNetworkRecord.getNetworkNodeNo()) &&
				  StringUtils.isNotBlank(standardNetworkRecord.getSubNetworkNo()) &&
			      StringUtils.isNotBlank(standardNetworkRecord.getSubNetworkNodeNo())){

				standardNetworkDao.updateSandardNetworkRecordToObsolete2(standardNetworkRecord.getSubNetworkNo(),standardNetworkRecord.getSubNetworkNodeNo());
		}
		
	}

	public void insertNetworkDef(StandardNetworkRecord standardNetworkRecord) throws Exception {

		
		checkToSeeEngineTypeExistsInProgramDefTable(standardNetworkRecord.getEngineType());
		
		standardNetworkDao.insertNetworkDef(standardNetworkRecord.getNetworkNo(), 
							                standardNetworkRecord.getNetworkNodeNo(), 
							                standardNetworkRecord.getNetworkDesc(), 
							                standardNetworkRecord.getSubNetworkNo(),
							                standardNetworkRecord.getSubNetworkNodeNo(),
							                standardNetworkRecord.getSubNetworkDesc(), 
							                standardNetworkRecord.getEngineType(),
							                standardNetworkRecord.getEngineTypeDesc(), 
							                standardNetworkRecord.getNetworkActNo(),
							                standardNetworkRecord.getNetworkActDesc(), 
							                standardNetworkRecord.getSubNetworkActNo(),
							                standardNetworkRecord.getSubNetworkActNoDesc(),
							                standardNetworkRecord.getModuleCatCode(), 
							                standardNetworkRecord.getModuleCatDesc(),
							                standardNetworkRecord.getWorkCenter(),
							                standardNetworkRecord.getControlKey(),
							                standardNetworkRecord.getGate(), 
							                standardNetworkRecord.getDuration(),
							                standardNetworkRecord.getDurationUnit(),
							                standardNetworkRecord.getWork(), 
							                standardNetworkRecord.getWorkUnit(), 
							                standardNetworkRecord.geteWorkScopingFlag(),
							                standardNetworkRecord.getLidNo(), 
							                "N");
	}

	public void updateNetworkDef(StandardNetworkRecord standardNetworkRecord) throws Exception {

		checkToSeeEngineTypeExistsInProgramDefTable(standardNetworkRecord.getEngineType());
		
		standardNetworkDao.updateNetworkDef(standardNetworkRecord.getNetworkNo(), 
				                            standardNetworkRecord.getNetworkNodeNo(), 
				                            standardNetworkRecord.getNetworkDesc(), 
				                            standardNetworkRecord.getSubNetworkNo(),
				                            standardNetworkRecord.getSubNetworkNodeNo(),
				                            standardNetworkRecord.getSubNetworkDesc(), 
				                            standardNetworkRecord.getEngineType(),
				                            standardNetworkRecord.getEngineTypeDesc(), 
				                            standardNetworkRecord.getNetworkActNo(),
				                            standardNetworkRecord.getNetworkActDesc(), 
				                            standardNetworkRecord.getSubNetworkActNo(),
				                            standardNetworkRecord.getSubNetworkActNoDesc(),
				                            standardNetworkRecord.getModuleCatCode(), 
				                            standardNetworkRecord.getModuleCatDesc(),
				                            standardNetworkRecord.getWorkCenter(),
				                            standardNetworkRecord.getControlKey(),
				                            standardNetworkRecord.getGate(), 
				                            standardNetworkRecord.getDuration(),
				                            standardNetworkRecord.getDurationUnit(),
				                            standardNetworkRecord.getWork(), 
				                            standardNetworkRecord.getWorkUnit(), 
				                            standardNetworkRecord.geteWorkScopingFlag(),
				                            standardNetworkRecord.getLidNo(), 
				                            "N");//obsoleteFlag)
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getStandardNetworkRecord(StandardNetworkRecord standardNetworkRecord){
		
		List<Map> list =  standardNetworkDao.selectStandardNetworkRecord(standardNetworkRecord.getNetworkNo(),
	                                                                     standardNetworkRecord.getNetworkNodeNo(),
	                                                                     standardNetworkRecord.getSubNetworkNo(),
	                                                                     standardNetworkRecord.getSubNetworkNodeNo());
		return list;
	}
	


	private void checkToSeeEngineTypeExistsInProgramDefTable(String engineType) throws Exception {
		
		// defect 1130
		boolean engineTypeExistsInProgramDefTable = standardNetworkDao.engineTypeExistsInProgramTable(engineType);
		if (!engineTypeExistsInProgramDefTable){
			standardNetworkDao.insertEngineTypeInProgramTable(engineType, "Added automatically via standard Network interface");
		}
	}

	
	@SuppressWarnings("rawtypes")
	private StandardNetworkRecord mapStandardNetworkRecord(List recordKeyList,HashMap columnHashMap) throws MappingException {
		
		
		StandardNetworkRecord standardNetworkRecord = new StandardNetworkRecord();
		
		if (recordKeyList!=null && !recordKeyList.isEmpty()){
				for (int i=0; i<recordKeyList.size(); i++) {
					NameValuePairType nameValuePairType = (NameValuePairType) recordKeyList.get(i);
					String recordKeyName = nameValuePairType.getName();
					
					if ("MESSAGE_ACTION".equalsIgnoreCase(recordKeyName)){
						
						standardNetworkRecord.setMessgeAction(nameValuePairType.getStringValue());
					
						if (StringUtils.isNotBlank(standardNetworkRecord.getMessgeAction())){
							if (!StringUtils.equalsIgnoreCase(standardNetworkRecord.getMessgeAction(), "DEL") &&
								!StringUtils.equalsIgnoreCase(standardNetworkRecord.getMessgeAction(), "CHG") &&
								!StringUtils.equalsIgnoreCase(standardNetworkRecord.getMessgeAction(), "REL")){
									throw new MappingException ("Message Action of =" + standardNetworkRecord.getMessgeAction() + " is Invalid ");
							}
						}
					}if ("ENGINE_TYPE".equalsIgnoreCase(recordKeyName)){
						
						standardNetworkRecord.setEngineType(nameValuePairType.getStringValue());
						
						if (StringUtils.isNotBlank(standardNetworkRecord.getEngineType())){
							Number tableColMaxLen = (Number)columnHashMap.get("ENGINE_TYPE");
							if (tableColMaxLen!=null && 
								standardNetworkRecord.getEngineType().length() > 
							    tableColMaxLen.intValue()){
									throw new MappingException ("Engine Type = " +standardNetworkRecord.getEngineType() + " max size is " + tableColMaxLen.intValue());
							}
						}
					}else if ("ENGINE_TYPE_DESC".equalsIgnoreCase(recordKeyName)){
						
						standardNetworkRecord.setEngineTypeDesc(nameValuePairType.getStringValue());
						
						if (StringUtils.isNotBlank(standardNetworkRecord.getEngineTypeDesc())){
							Number tableColMaxLen = (Number)columnHashMap.get("ENGINE_TYPE_DESC");
							if (tableColMaxLen!=null && standardNetworkRecord.getEngineTypeDesc().length() > tableColMaxLen.intValue()){
								throw new MappingException ("Engine Type Desc = "  + standardNetworkRecord.getEngineTypeDesc() + " max size is " + tableColMaxLen.intValue());
							}
						}
						
						
					}else if ("NETWORK_NO".equalsIgnoreCase(recordKeyName)){
						
						standardNetworkRecord.setNetworkNo(nameValuePairType.getStringValue());

						if (StringUtils.isBlank(standardNetworkRecord.getNetworkNo())){
							throw new MappingException ("NETWORK_NO can not be blank ");
						}else{
							Number tableColMaxLen = (Number)columnHashMap.get("NETWORK_NO");
							if (tableColMaxLen!=null && standardNetworkRecord.getNetworkNo().length() > tableColMaxLen.intValue()){
								throw new MappingException ("NETWORK_NO = "  + standardNetworkRecord.getNetworkNo() + " max size is " + tableColMaxLen.intValue());
							}
						}
					}else if ("NETWORK_DESC".equalsIgnoreCase(recordKeyName)){
						standardNetworkRecord.setNetworkDesc(nameValuePairType.getStringValue());
						
						if (StringUtils.isNotBlank(standardNetworkRecord.getNetworkDesc())){
							Number tableColMaxLen = (Number)columnHashMap.get("NETWORK_DESC");
							if (tableColMaxLen!=null && standardNetworkRecord.getNetworkNo().length() > tableColMaxLen.intValue()){
								throw new MappingException ("NETWORK_DESC = " + standardNetworkRecord.getNetworkNo() + " max size is " + tableColMaxLen.intValue());
							}
						}
					}else if ("NETWORK_NODE_NO".equalsIgnoreCase(recordKeyName)){
						standardNetworkRecord.setNetworkNodeNo(nameValuePairType.getStringValue());
						
						if (StringUtils.isBlank(standardNetworkRecord.getNetworkNodeNo())){
							throw new MappingException ("NETWORK_NODE_NO can not be blank ");
						}else{
							Number tableColMaxLen = (Number)columnHashMap.get("NETWORK_NODE_NO");
							if (tableColMaxLen!=null && standardNetworkRecord.getNetworkNodeNo().length() > tableColMaxLen.intValue()){
								throw new MappingException ("NETWORK_NODE_NO = " +standardNetworkRecord.getNetworkNodeNo() + " max size is " + tableColMaxLen.intValue());
							}
						}
					}else if ("NETWORK_ACT_NO".equalsIgnoreCase(recordKeyName)){
						standardNetworkRecord.setNetworkActNo(nameValuePairType.getStringValue());
						
						if (StringUtils.isNotBlank(standardNetworkRecord.getNetworkActNo())){
							Number tableColMaxLen = (Number)columnHashMap.get("NETWORK_ACT_NO");
							if (tableColMaxLen!=null && standardNetworkRecord.getNetworkActNo().length() > tableColMaxLen.intValue()){
								throw new MappingException ("NETWORK_ACT_NO = " + standardNetworkRecord.getNetworkActNo()+ " max size is " + tableColMaxLen.intValue());
							}
						}
					}else if ("NETWORK_ACT_DESC".equalsIgnoreCase(recordKeyName)){
						
						standardNetworkRecord.setNetworkActDesc((nameValuePairType.getStringValue()));
						
						if (StringUtils.isNotBlank(standardNetworkRecord.getNetworkActDesc())){
							Number tableColMaxLen = (Number)columnHashMap.get("NETWORK_ACT_DESC");
							if (tableColMaxLen!=null && standardNetworkRecord.getNetworkActDesc().length() > tableColMaxLen.intValue()){
								throw new MappingException ("NETWORK_ACT_DESC = " + standardNetworkRecord.getNetworkActDesc() + " max size is " + tableColMaxLen.intValue());
							}
						}
					}else if ("SUB_NETWORK_NO".equalsIgnoreCase(recordKeyName)){
						standardNetworkRecord.setSubNetworkNo(nameValuePairType.getStringValue());
						
						if (StringUtils.isBlank(standardNetworkRecord.getSubNetworkNo())){
							throw new MappingException ("SUB_NETWORK_NO can not be blank ");
						}else{					
							Number tableColMaxLen = (Number)columnHashMap.get("SUB_NETWORK_NO");
							if (tableColMaxLen!=null && standardNetworkRecord.getSubNetworkNo().length() > tableColMaxLen.intValue()){
								throw new MappingException ("SUB_NETWORK_NO = " + standardNetworkRecord.getSubNetworkNo() + " max size is " + tableColMaxLen.intValue());
							}
						}
						
					}else if ("SUB_NETWORK_DESC".equalsIgnoreCase(recordKeyName)){
						standardNetworkRecord.setSubNetworkDesc(nameValuePairType.getStringValue());
						
						if (StringUtils.isNotBlank(standardNetworkRecord.getSubNetworkDesc())){
							Number tableColMaxLen = (Number)columnHashMap.get("SUB_NETWORK_DESC");
							if (tableColMaxLen!=null && standardNetworkRecord.getSubNetworkDesc().length() > tableColMaxLen.intValue()){
								throw new MappingException ("SUB_NETWORK_DESC = " + standardNetworkRecord.getSubNetworkDesc() + " max size is " + tableColMaxLen.intValue());
							}
						}
						
					}else if ("SUB_NETWORK_NODE_NO".equalsIgnoreCase(recordKeyName)){
						standardNetworkRecord.setSubNetworkNodeNo(nameValuePairType.getStringValue());
						
						if (StringUtils.isBlank(standardNetworkRecord.getSubNetworkNodeNo())){
							throw new MappingException ("SUB_NETWORK_NODE_NO can not be blank ");
						}else{
							Number tableColMaxLen = (Number)columnHashMap.get("SUB_NETWORK_NODE_NO");
							if (tableColMaxLen!=null && standardNetworkRecord.getSubNetworkNodeNo().length() > tableColMaxLen.intValue()){
								throw new MappingException ("SUB_NETWORK_NODE_NO = " + standardNetworkRecord.getSubNetworkNodeNo() + " max size is " + tableColMaxLen.intValue());
							}
						}
						
					}else if ("SUB_NETWORK_ACT_NO".equalsIgnoreCase(recordKeyName)){
						standardNetworkRecord.setSubNetworkActNo(nameValuePairType.getStringValue());
						
						if (StringUtils.isNotBlank(standardNetworkRecord.getSubNetworkActNo())){
							Number tableColMaxLen = (Number)columnHashMap.get("SUB_NETWORK_ACT_NO");
							if (tableColMaxLen!=null && standardNetworkRecord.getSubNetworkActNo().length() > tableColMaxLen.intValue()){
								throw new MappingException ("SUB_NETWORK_ACT_NO = "+ standardNetworkRecord.getSubNetworkActNo() +" max size is " + tableColMaxLen.intValue());
							}
						}
					}else if ("SUB_NETWORK_ACT_DESC".equalsIgnoreCase(recordKeyName)){
						standardNetworkRecord.setSubNetworkActNoDesc(nameValuePairType.getStringValue());
						
						if (StringUtils.isNotBlank(standardNetworkRecord.getSubNetworkActNoDesc())){
							Number tableColMaxLen = (Number)columnHashMap.get("SUB_NETWORK_ACT_DESC");
							if (tableColMaxLen!=null && standardNetworkRecord.getSubNetworkActNo().length() > tableColMaxLen.intValue()){
								throw new MappingException ("SUB_NETWORK_ACT_DESC = " + standardNetworkRecord.getSubNetworkActNo() + " max size is " + tableColMaxLen.intValue());
							}
						}
					}else if ("MODULE_CAT_CODE".equalsIgnoreCase(recordKeyName)){
						standardNetworkRecord.setModuleCatCode(nameValuePairType.getStringValue());
						
						if (StringUtils.isNotBlank(standardNetworkRecord.getModuleCatCode())){
							Number tableColMaxLen = (Number)columnHashMap.get("MODULE_CAT_CODE");
							if (tableColMaxLen!=null && standardNetworkRecord.getModuleCatCode().length() > tableColMaxLen.intValue()){
								throw new MappingException ("MODULE_CAT_CODE = " + standardNetworkRecord.getModuleCatCode() + " max size is " + tableColMaxLen.intValue());
							}
						}
						
					}else if ("MODULE_CAT_DESC".equalsIgnoreCase(recordKeyName)){
						standardNetworkRecord.setModuleCatDesc(nameValuePairType.getStringValue());
						
						if (StringUtils.isNotBlank(standardNetworkRecord.getModuleCatDesc())){
							Number tableColMaxLen = (Number)columnHashMap.get("MODULE_CAT_DESC");
							if (tableColMaxLen!=null && standardNetworkRecord.getModuleCatDesc().length() > tableColMaxLen.intValue()){
								throw new MappingException ("MODULE_CAT_DESC = " + standardNetworkRecord.getModuleCatDesc() + " max size is " + tableColMaxLen.intValue());
							}
						}
						
					}else if ("WORK_CENTER".equalsIgnoreCase(recordKeyName)){
						standardNetworkRecord.setWorkCenter(nameValuePairType.getStringValue());
						
						if (StringUtils.isNotBlank(standardNetworkRecord.getWorkCenter())){
							Number tableColMaxLen = (Number)columnHashMap.get("WORK_CENTER");
							if (tableColMaxLen!=null && standardNetworkRecord.getWorkCenter().length() > tableColMaxLen.intValue()){
								throw new MappingException ("WORK_CENTER = "+standardNetworkRecord.getWorkCenter() +" max size is " + tableColMaxLen.intValue());
							}
						}
						
					}else if ("CONTROL_KEY".equalsIgnoreCase(recordKeyName)){
						standardNetworkRecord.setControlKey(nameValuePairType.getStringValue());
						
						if (StringUtils.isNotBlank(standardNetworkRecord.getControlKey())){
							Number tableColMaxLen = (Number)columnHashMap.get("CONTROL_KEY");
							if (tableColMaxLen!=null && standardNetworkRecord.getControlKey().length() > tableColMaxLen.intValue()){
								throw new MappingException ("CONTROL_KEY = "+standardNetworkRecord.getControlKey()+ " max size is " + tableColMaxLen.intValue());
							}
						}
					}else if ("GATE".equalsIgnoreCase(recordKeyName)){
						standardNetworkRecord.setGate(nameValuePairType.getStringValue());
						
						if (StringUtils.isNotBlank(standardNetworkRecord.getGate())){
							Number tableColMaxLen = (Number)columnHashMap.get("GATE");
							if (tableColMaxLen!=null && standardNetworkRecord.getGate().length() > tableColMaxLen.intValue()){
								throw new MappingException ("GATE = "+standardNetworkRecord.getGate()+ " max size is " + tableColMaxLen.intValue());
							}
						}
						
					}else if ("DURATION".equalsIgnoreCase(recordKeyName)){
						standardNetworkRecord.setDuration(nameValuePairType.getStringValue());
						
						if (StringUtils.isNotBlank(standardNetworkRecord.getDuration())){
							Number tableColMaxLen = (Number)columnHashMap.get("DURATION");
							if (tableColMaxLen!=null && standardNetworkRecord.getDuration().length() > tableColMaxLen.intValue()){
								throw new MappingException ("DURATION = "+standardNetworkRecord.getDuration()+ " max size is " + tableColMaxLen.intValue());
							}
						}
					}else if ("WORK".equalsIgnoreCase(recordKeyName)){
						standardNetworkRecord.setWork(nameValuePairType.getStringValue());
						
						if (StringUtils.isNotBlank(standardNetworkRecord.getWork())){
							Number tableColMaxLen = (Number)columnHashMap.get("WORK");
							if (tableColMaxLen!=null && standardNetworkRecord.getWork().length() > tableColMaxLen.intValue()){
								throw new MappingException ("WORK = "+standardNetworkRecord.getWork()+" max size is " + tableColMaxLen.intValue());
							}
						}
					}else if ("WORK_UNIT".equalsIgnoreCase(recordKeyName)){
						standardNetworkRecord.setWorkUnit(nameValuePairType.getStringValue());
						
						if (StringUtils.isNotBlank(standardNetworkRecord.getWorkUnit())){
							Number tableColMaxLen = (Number)columnHashMap.get("WORK_UNIT");
							if (tableColMaxLen!=null && standardNetworkRecord.getWorkUnit().length() > tableColMaxLen.intValue()){
								throw new MappingException ("WORK_UNIT = "+standardNetworkRecord.getWorkUnit()+" max size is " + tableColMaxLen.intValue());
							}
						}
					}else if ("DURATION_UNIT".equalsIgnoreCase(recordKeyName)){
						standardNetworkRecord.setDurationUnit(nameValuePairType.getStringValue());
						
						if (StringUtils.isNotBlank(standardNetworkRecord.getDurationUnit())){
							Number tableColMaxLen = (Number)columnHashMap.get("DURATION_UNIT");
							if (tableColMaxLen!=null && standardNetworkRecord.getDurationUnit().length() > tableColMaxLen.intValue()){
								throw new MappingException ("DURATION_UNIT = "+standardNetworkRecord.getDurationUnit()+" max size is " + tableColMaxLen.intValue());
							}
						}
					}else if ("EWORKSCOPING".equalsIgnoreCase(recordKeyName)){
						standardNetworkRecord.seteWorkScopingFlag(nameValuePairType.getStringValue());
						
						if (StringUtils.isNotBlank(standardNetworkRecord.geteWorkScopingFlag())){
							Number tableColMaxLen = (Number)columnHashMap.get("EWORKSCOPING_FLAG");
							if (tableColMaxLen!=null && standardNetworkRecord.geteWorkScopingFlag().length() > tableColMaxLen.intValue()){
								throw new MappingException ("EWORKSCOPING_FLAG = "+standardNetworkRecord.geteWorkScopingFlag()+" max size is " + tableColMaxLen.intValue());
							}
						}
						
					}else if ("LID_NO".equalsIgnoreCase(recordKeyName)){
						standardNetworkRecord.setLidNo(nameValuePairType.getStringValue());
						if (StringUtils.isNotBlank(standardNetworkRecord.getLidNo())){
							Number tableColMaxLen = (Number)columnHashMap.get("LID_NO");
							if (tableColMaxLen!=null && standardNetworkRecord.getLidNo().length() > tableColMaxLen.intValue()){
								throw new MappingException ("LID_NO = "+standardNetworkRecord.getLidNo()+" max size is " + tableColMaxLen.intValue());
							}
						}
					}
					
				}
		}

		return  standardNetworkRecord;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected HashMap getTableColumnSizes() {
		HashMap hashMap = new HashMap();
		List columnDataList = standardNetworkDao.getPwustNetworkDefColumnLenghts();
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

	class StandardNetworkRecord{
		/*
		 <NS1:Keys>
        <NS1:RecordKey name="ENGINE_TYPE">ETCFMTEC</NS1:RecordKey>
        <NS1:RecordKey name="ENGINE_TYPE_DESC">Engine Type CFM56 TEC</NS1:RecordKey>
        <NS1:RecordKey name="NETWORK_NO">00090005</NS1:RecordKey>
        <NS1:RecordKey name="NETWORK_DESC">CFM56 Engine Network Shanghai</NS1:RecordKey>
        <NS1:RecordKey name="NETWORK_NODE_NO">00000001</NS1:RecordKey>
        <NS1:RecordKey name="NETWORK_ACT_NO">0010</NS1:RecordKey>
        <NS1:RecordKey name="NETWORK_ACT_DESC">Engine Receipt</NS1:RecordKey>
        <NS1:RecordKey name="SUB_NETWORK_NO">00000000</NS1:RecordKey>
        <NS1:RecordKey name="SUB_NETWORK_DESC">Default</NS1:RecordKey>
	    <NS1:RecordKey name="SUB_NETWORK_NODE_NO">00000000</NS1:RecordKey>
	    <NS1:RecordKey name="SUB_NETWORK_ACT_NO">0000</NS1:RecordKey>
        <NS1:RecordKey name="SUB_NETWORK_ACT_DESC">Default</NS1:RecordKey>
	    <NS1:RecordKey name="MODULE_CAT_CODE">0080</NS1:RecordKey>
        <NS1:RecordKey name="MODULE_CAT_DESC">Engine</NS1:RecordKey>
        <NS1:RecordKey name="WORK_CENTER">V1100</NS1:RecordKey>
	    <NS1:RecordKey name="CONTROL_KEY">ZZCX</NS1:RecordKey>
        <NS1:RecordKey name="GATE">3</NS1:RecordKey>
        <NS1:RecordKey name="DURATION">38.5</NS1:RecordKey>
        <NS1:RecordKey name="DURATION_UNIT">111</NS1:RecordKey>
	    <NS1:RecordKey name="WORK">2.0</NS1:RecordKey>
	    <NS1:RecordKey name="WORK_UNIT">H</NS1:RecordKey>
	    <NS1:RecordKey name="MESSAGE_ACTION"></NS1:RecordKey>
	    <NS1:RecordKey name="PLANT">6150</NS1:RecordKey>
       </NS1:Keys>
		 */
		String engineType = null;
		String engineTypeDesc = null;
		String networkNo = null;                     // NETWORK_NO
		String networkDesc = null;                   // NETWORK_DESC 
		String networkNodeNo = null;                 // NETWORK_NODE_NO
		String networkActNo = null;                  // network_act_no
		String networkActDesc = null;                // network_act_desc
		String subNetworkNo = null;                  // sub_network_no
		String subNetworkDesc = null;                // sub_network_dsc
		String subNetworkNodeNo = null;              // SUB_NETWORK_NODE_NO
		String subNetworkActNo = null;               // SUB_NETWORK_ACT_NO
		String subNetworkActNoDesc = null;           // SUB_NETWORK_ACT_DESC
		String moduleCatCode = null;
		String moduleCatDesc = null;
		String workCenter = null;
		String controlKey = null;
		String gate = null;
		String duration = null;
		String durationUnit = null;
		String work = null;
		String workUnit = null;
		String messgeAction = null;
		String plant = null;
		
		String eWorkScopingFlag = null;
		String lidNo = null;
		public String getEngineType() {
			return engineType;
		}
		public void setEngineType(String engineType) {
			this.engineType = engineType;
		}
		public String getEngineTypeDesc() {
			return engineTypeDesc;
		}
		public void setEngineTypeDesc(String engineTypeDesc) {
			this.engineTypeDesc = engineTypeDesc;
		}
		public String getNetworkNo() {
			return networkNo;
		}
		public void setNetworkNo(String networkNo) {
			this.networkNo = networkNo;
		}
		public String getNetworkDesc() {
			return networkDesc;
		}
		public void setNetworkDesc(String networkDesc) {
			this.networkDesc = networkDesc;
		}
		public String getNetworkNodeNo() {
			return networkNodeNo;
		}
		public void setNetworkNodeNo(String networkNodeNo) {
			this.networkNodeNo = networkNodeNo;
		}
		public String getNetworkActNo() {
			return networkActNo;
		}
		public void setNetworkActNo(String networkActNo) {
			this.networkActNo = networkActNo;
		}
		public String getNetworkActDesc() {
			return networkActDesc;
		}
		public void setNetworkActDesc(String networkActDesc) {
			this.networkActDesc = networkActDesc;
		}
		public String getSubNetworkNo() {
			return subNetworkNo;
		}
		public void setSubNetworkNo(String subNetworkNo) {
			this.subNetworkNo = subNetworkNo;
		}
		public String getSubNetworkDesc() {
			return subNetworkDesc;
		}
		public void setSubNetworkDesc(String subNetworkDesc) {
			this.subNetworkDesc = subNetworkDesc;
		}
		public String getSubNetworkNodeNo() {
			return subNetworkNodeNo;
		}
		public void setSubNetworkNodeNo(String subNetworkNodeNo) {
			this.subNetworkNodeNo = subNetworkNodeNo;
		}
		public String getSubNetworkActNo() {
			return subNetworkActNo;
		}
		public void setSubNetworkActNo(String subNetworkActNo) {
			this.subNetworkActNo = subNetworkActNo;
		}
		public String getSubNetworkActNoDesc() {
			return subNetworkActNoDesc;
		}
		public void setSubNetworkActNoDesc(String subNetworkActNoDesc) {
			this.subNetworkActNoDesc = subNetworkActNoDesc;
		}
		public String getModuleCatCode() {
			return moduleCatCode;
		}
		public void setModuleCatCode(String moduleCatCode) {
			this.moduleCatCode = moduleCatCode;
		}
		public String getModuleCatDesc() {
			return moduleCatDesc;
		}
		public void setModuleCatDesc(String moduleCatDesc) {
			this.moduleCatDesc = moduleCatDesc;
		}
		public String getWorkCenter() {
			return workCenter;
		}
		public void setWorkCenter(String workCenter) {
			this.workCenter = workCenter;
		}
		public String getControlKey() {
			return controlKey;
		}
		public void setControlKey(String controlKey) {
			this.controlKey = controlKey;
		}
		public String getGate() {
			return gate;
		}
		public void setGate(String gate) {
			this.gate = gate;
		}
		public String getDuration() {
			return duration;
		}
		public void setDuration(String duration) {
			this.duration = duration;
		}
		public String getDurationUnit() {
			return durationUnit;
		}
		public void setDurationUnit(String durationUnit) {
			this.durationUnit = durationUnit;
		}
		public String getWork() {
			return work;
		}
		public void setWork(String work) {
			this.work = work;
		}
		public String getWorkUnit() {
			return workUnit;
		}
		public void setWorkUnit(String workUnit) {
			this.workUnit = workUnit;
		}
		public String getMessgeAction() {
			return messgeAction;
		}
		public void setMessgeAction(String messgeAction) {
			this.messgeAction = messgeAction;
		}
		public String getPlant() {
			return plant;
		}
		public void setPlant(String plant) {
			this.plant = plant;
		}
		public String geteWorkScopingFlag() {
			return eWorkScopingFlag;
		}
		public void seteWorkScopingFlag(String eWorkScopingFlag) {
			this.eWorkScopingFlag = eWorkScopingFlag;
		}
		public String getLidNo() {
			return lidNo;
		}
		public void setLidNo(String lidNo) {
			this.lidNo = lidNo;
		}		
	}
	
	
}
