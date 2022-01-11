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
*  File:    WorkOrderSplitInterface.java
* 
*  Created: 2019-06-05
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2019-06-04 	Fred Ettefagh   Initial Release for PW_SMRO_TR_319 
* 2019-06-10    Fred Ettefagh   added parentOrderWorkLoc to event t319EventPW  
* 2019-08-12    Fred Ettefagh   changed column name from REPLY_VALUE to DATA_RETURNED for selectPwustSyncIntReplyData DAO call
* 2019-08-21    Fred Ettefagh   change methods to add outboundMessageId, to look for correct return message   
* 2019-09-12    Fred Ettefagh   SMRO_TR_319, added code for SCR-001 
* 2019-09-20    Bahram J.       SMRO_TR_319,updated error handling 
* 2019-10-30    Fred Ettefagh   SMRO_TR_319, changed code to set t319 msg bodid value as JMSCorrelationID, this is done to help WM with handling  JMSCorrelationID
* 2019-10-30    Fred Ettefagh   SMRO_TR_319, changed code to use jmsMessageID instead of JMSCorrelationID
* 2019-11-01    Fred Ettefagh   SMRO_TR_319, changed code to remove "ID:" from jmsMessageID.  
* 2019-11-12    Fred Ettefagh   SMRO_TR_319, changed how return message is processed based on testing with WM and SAP
* 2019-11-12    Fred Ettefagh   SMRO_TR_319, changed how messageProducerSetTimeToLive and jmsResponseTimeoutSeconds are set
* 2019-11-13    Fred Ettefagh   SMRO_TR_319, changed how order_type and split/sub data is set
* 2019-11-15    Fred Ettefagh   SMRO_TR_319, changed default values  for jmsResponseTimeoutSeconds and  messageProducerSetTimeToLive
* 2019-11-18    Fred Ettefagh   SMRO_TR_319, changed SapOrderType and SapOrderTypeIndicator values based on end to end testing
* 2020-03-02    Fred Ettefagh   SMRO_TR_319, Defect 1526, changed SapOrderType for STD sub to get sap order type from parent order 
* 2020-08-18    D.Miron         SMRO_TR_319  Defect 1757 - Added method getWorkOrderSAPOrderTypeAndIndicator, replaced planNo with planItemNo
* 2020-11-02    Fred Ettefagh   Defect  1812 Changed code so that we do not replace "ID:" for jmsMessageID/CorrelationID 
*/
package com.pw.solumina.T319.integration.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;

import com.ibaset.common.Reference;
import com.ibaset.solumina.integration.logging.MessageLogger;
import com.ibaset.solumina.integration.logging.MessageLoggerParams;
import com.ibaset.solumina.integration.logging.impl.MessageLoggerParamsImpl;
import com.ibaset.solumina.integration.logging.impl.ReplyLoggerParamsImpl;
import com.ibaset.solumina.sfbis.dao.ServiceDefinitionDao;
import com.ibaset.solumina.sfbis.domain.ServiceDef;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.pw.common.dao.CommonDaoPW;


@SuppressWarnings({ "rawtypes", "unchecked" })
public class WorkOrderSplitInterface {


	protected final Log logger = LogFactory.getLog(WorkOrderSplitInterface.class);
	
	@Reference ServiceDefinitionDao serviceDefinitionDao;
	@Reference WorkOrderSplitInterfaceDao workOrderSplitInterfaceDao;
	@Reference T319EventProcesserImpl t319EventProcesserImpl;
	@Reference ReceiveT319RelyProcesserImplPW receiveT319RelyProcesserImplPW;
	@Reference ConnectionFactory connectionFactory = null; 
	@Reference CommonDaoPW commonDaoPW = null;
	@Reference MessageLogger messageLogger;  
	@Reference IMessage message = null;
	
	static final String SERVICE_NAME = "PW_S_T319_ORDER_SPLIT";  
	int jmsResponseTimeoutSeconds = 20;	
	long messageProducerSetTimeToLive = 40;	
	
	public String requestSMOrderNo(String orderId, String parentWorkOrderNo, String planItemNo, String[] serialNumberToSplitArray,String parentOrderWorkLoc, String calledFrom  ){
		
		if (logger.isDebugEnabled()) {
			logger.debug("T319, Start: com.pw.solumina.T319.integration.impl.WorkOrderSplitInterfaceT319.requestSMOrderNo()");
		}
		String newOrderNo = null;
		
		List<ServiceDef> servDefs = serviceDefinitionDao.selectServiceDefinition(SERVICE_NAME);
		ServiceDef servDef = servDefs.get(0);	
		
		if (!servDef.isEnabled()){
			logger.debug("T319, sfbis_service_def.PW_S_T319_ORDER_SPLIT is not enabled");
			message.raiseError("MFI_50400","Interface " +SERVICE_NAME + " is not truned on");
		}
	
		T319EventPW t319EventPW = new T319EventPW(this);
		t319EventPW.setWorkOrderNumber(parentWorkOrderNo);
		t319EventPW.setOrderQty((Number)serialNumberToSplitArray.length);
		t319EventPW.setPlanItemNo(planItemNo);                             // 1757
		t319EventPW.setNumberOfSerialNo(serialNumberToSplitArray.length);
		t319EventPW.setSerialNoArray(serialNumberToSplitArray);
		t319EventPW.setWorkLocation(parentOrderWorkLoc);
		
		// Defect 1757
		Map sapTypeMap = getWorkOrderSAPOrderTypeAndIndicator(calledFrom, orderId);
		t319EventPW.setSapOrderType((String) sapTypeMap.get("SAP_ORDER_TYPE"));
		t319EventPW.setSapOrderTypeIndicator((String) sapTypeMap.get("SAP_ORDER_TYPE_IND"));
		// End Defect 1757
		
		List taskList = workOrderSplitInterfaceDao.selectSforSfwidOrderSubjectsList(orderId, "INCLUDED");
		if (taskList!=null && !taskList.isEmpty()){
			
			 List<String> result = new ArrayList();
			 for(int i=0; i < taskList.size(); i++){
				 Map map = (Map)taskList.get(i);
				 Number subjectNo = (Number) map.get("SUBJECT_NO");
				 result.add((String)subjectNo.toString() );	 
			 }
			 String[] taskGroupArray = result.toArray(new String[result.size()]);
			 
			 t319EventPW.setNumberOfTaskGroups(taskGroupArray.length);
			 t319EventPW.setTaskGroupArray(taskGroupArray);
		}
			
		MessageLoggerParamsImpl messageLoggerParamsImpl = new MessageLoggerParamsImpl(SERVICE_NAME);
		String outBoundXml =  t319EventProcesserImpl.generateMessage(t319EventPW, messageLoggerParamsImpl);
		newOrderNo = sendMessage(outBoundXml,servDef,t319EventPW,messageLoggerParamsImpl);

		return  newOrderNo;
	}


	public String sendMessage(String outboundXml, ServiceDef serviceDef,T319EventPW t319EventPW,MessageLoggerParams messageLoggerParams) {
		
        Connection connection = null;
        Session session = null;
        MessageProducer messageProducer = null;
        String newOrderNo = null;
        String errorMsg = null;
        String outboundJMSMessageId = null;
        
        try {
        
	        connection = connectionFactory.createConnection();
	        session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
	        Queue queueSend = session.createQueue(serviceDef.getQueueName());
	        messageProducer = session.createProducer(queueSend);
	        
	        String messageProducerSetTimeToLiveString = commonDaoPW.getPwustGlobalConfigValue("T319_OUTBOUND_JMS_EXPIRATION_SECONDS");
	        if (StringUtils.isNoneBlank(messageProducerSetTimeToLiveString)){
	        	messageProducerSetTimeToLive = Long.parseLong(messageProducerSetTimeToLiveString);
	        }
	    	logger.debug("T319, messageProducerSetTimeToLive = " + messageProducerSetTimeToLive);
	    	System.out.println("T319 messageProducerSetTimeToLive = " + messageProducerSetTimeToLive);
            String jmsReplyTimeOutString = commonDaoPW.getPwustGlobalConfigValue("T319_REPLY_JMS_TIMEOUT_SECONDS");
            if (StringUtils.isNoneBlank(jmsReplyTimeOutString)){
            	jmsResponseTimeoutSeconds = Integer.parseInt(jmsReplyTimeOutString);  
	        }
	    	logger.debug("T319, jmsResponseTimeoutSeconds = " + jmsResponseTimeoutSeconds);
	    	System.out.println("T319 jmsResponseTimeoutSeconds = " + jmsResponseTimeoutSeconds);
	    	
	    	MessageSender messageSender  = new MessageSender(outboundXml,serviceDef.getQueueName());
	    	outboundJMSMessageId= messageSender.doInJms(session, messageProducer, messageProducerSetTimeToLive);
	    	
	    	messageProducer.close();
	    	messageProducer=null;
            if (logger.isDebugEnabled()) {
            	logger.debug("T319, msg send  outboundXml = " + outboundXml );
            }
		    
		    messageLoggerParams.setStatus(MessageLogger.STATUS_SEND_SUCCESS);
		    messageLoggerParams.setRef1(t319EventPW.getWorkOrderNumber());
		    messageLoggerParams.setRef2(t319EventPW.getOrderQty().toString());
		    messageLoggerParams.setRef5(outboundJMSMessageId);
            messageLoggerParams.setMessageText(outboundXml);
            messageLoggerParams.setMessageID(outboundJMSMessageId);
            messageLoggerParams.setJmsMessageId(outboundJMSMessageId);
		    Date currentTimeStamp;
		    currentTimeStamp = new Date();
            messageLoggerParams.setMessageCreationDate(currentTimeStamp);
            messageLogger.addMessage(messageLoggerParams);

	    	connection.start();
             
        	long start = System.currentTimeMillis();
        	while( (System.currentTimeMillis()-start < (jmsResponseTimeoutSeconds*1000)) && (newOrderNo == null)){
        	
                if (logger.isDebugEnabled()) {
                	logger.debug("T319, looping ResponsePoller ");
                }

				Queue queue =session.createQueue(serviceDef.getReplyQueueName());
                QueueBrowser queueBrowser = session.createBrowser(queue);
                newOrderNo = (String) new ResponsePoller(outboundJMSMessageId).doInJms(session, queueBrowser);
                queueBrowser.close();
        	}
        		
        	connection.stop();
        }  
        catch (JMSException jex){
        	messageLogger.updateMessageStatus(outboundJMSMessageId, MessageLogger.REPLY_STATUS_FAILURE, jex.getLocalizedMessage() );
           	logger.error("T319, " + jex);
        } finally{
           	try{
           		if(messageProducer!=null)
           			messageProducer.close();
           		if(session!=null)
           			session.close();
           		if(connection!=null)
           			connection.close();
           	}
           	catch (JMSException jex){
           		throw new RuntimeException(jex);
           	}
        }
        
        if (newOrderNo ==null){
        	messageLogger.updateMessageStatus(outboundJMSMessageId, MessageLogger.REPLY_STATUS_FAILURE, "No Response Received from SAP for T319" );
        	message.raiseError("MFI_50400","No Response Received from SAP for T319 ");
        }else{
			
			ReplyLoggerParamsImpl replyLoggerParams = new ReplyLoggerParamsImpl(SERVICE_NAME);
           	replyLoggerParams.setReplyText(newOrderNo);
           	replyLoggerParams.setOriginalMessageID(messageLoggerParams.getMessageID());
           	replyLoggerParams.setTransactionStatus(MessageLogger.REPLY_STATUS_FAILURE);
           	replyLoggerParams.setRef1(t319EventPW.getWorkOrderNumber());
           	
           	
           	try{
	           	XmlObject xmlObject = receiveT319RelyProcesserImplPW.parseReply(newOrderNo);
	           	if (xmlObject==null){
	           		replyLoggerParams.setTransactionStatus(MessageLogger.REPLY_STATUS_FAILURE);
	           		replyLoggerParams.setErrorText("response xml message is not valid from SAP for T319");
	           		message.raiseError("MFI_50400","response xml message is not valid from SAP for T319 ");
	           		
	           	}else{
		           	receiveT319RelyProcesserImplPW.processReply(xmlObject, replyLoggerParams);
		           	newOrderNo= replyLoggerParams.getRef5();
		           	errorMsg = replyLoggerParams.getErrorText();
		           	
		           	if (!StringUtils.isBlank(newOrderNo)){
		           		// we get a new order no back from SAP
		           		if (newOrderNo.length() >40){
		            		message.raiseError("MFI_50400","New order no = " + newOrderNo  + " lenght is too long");
		            		replyLoggerParams.setTransactionStatus(MessageLogger.REPLY_STATUS_FAILURE);
		            		replyLoggerParams.setErrorText("New order no = " + newOrderNo  + " lenght is too long");
		           		}
		           		if (commonDaoPW.selectPWOrderDataByPWOrderNo(newOrderNo)!=null && !commonDaoPW.selectPWOrderDataByPWOrderNo(newOrderNo).isEmpty()){
			            	message.raiseError("MFI_50400","New order no = " + newOrderNo  + " already exists in solumina ");
		            		replyLoggerParams.setTransactionStatus(MessageLogger.REPLY_STATUS_FAILURE);
		            		replyLoggerParams.setErrorText("New order no = " + newOrderNo  + " already exists in solumina ");
			            }
		           		replyLoggerParams.setTransactionStatus(MessageLogger.REPLY_STATUS_SUCCESS);
		           		
		           	}else if (StringUtils.isNotBlank(errorMsg)){
		            		replyLoggerParams.setTransactionStatus(MessageLogger.REPLY_STATUS_FAILURE);
		            		replyLoggerParams.setErrorText("SAP error msg = " + errorMsg);
		           	}else{
		           		message.raiseError("MFI_50400","new order no is blank and error msg is blank");
	            		replyLoggerParams.setTransactionStatus(MessageLogger.REPLY_STATUS_FAILURE);
	            		replyLoggerParams.setErrorText("new order no is blank and error msg is blank");
		           	}
	           	}
           	}
           	catch (Exception e)	{
				e.printStackTrace();
	            replyLoggerParams.setErrors(e); 
	            messageLogger.updateMessageStatus(outboundJMSMessageId, MessageLogger.REPLY_STATUS_FAILURE, e.getMessage());
	            logger.error("T319, "+replyLoggerParams.getTextFromErrors());
	            newOrderNo = "ERROR: " + replyLoggerParams.getErrorText();
	            throw new RuntimeException(newOrderNo);
			}finally {
				messageLogger.addReplyMessage(replyLoggerParams);
			}
		}
        
		return newOrderNo;
	}
	
	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}


	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
	
    // Defect 1757
	public Map getWorkOrderSAPOrderTypeAndIndicator(String calledFrom, String orderId) {
		String sapOrderType = null;
		String sapOrderTypeIndicator = null;
		
		if (StringUtils.equalsIgnoreCase("REPAIR_WO_SPLIT", calledFrom)){
			sapOrderType = "Z003";
			sapOrderTypeIndicator ="SPLIT";
		}
		else if (StringUtils.equalsIgnoreCase("REWORK", calledFrom)){
			sapOrderType = "ZRCL";
			sapOrderTypeIndicator ="SUB";
		}
		else if (StringUtils.equalsIgnoreCase("REPLACE", calledFrom)){
			sapOrderType = "Z003";
			sapOrderTypeIndicator = "SUPER";
		}
		else if (StringUtils.equalsIgnoreCase("STD", calledFrom)){
			
			// if STD then we have to get sap order type from parent order from table pwust_sfwid_order_desc.SAP_ORDER_TYPE
			List pwustSfwidOrderDescList =  commonDaoPW.selectPwustSfwidOrderDesc(orderId);
			if (pwustSfwidOrderDescList!=null && !pwustSfwidOrderDescList.isEmpty()){
				Map  pwustSfwidOrderDescMap = (Map) pwustSfwidOrderDescList.get(0);
				sapOrderType = (String)pwustSfwidOrderDescMap.get("SAP_ORDER_TYPE"); 
			}
			sapOrderTypeIndicator = "SUB";
		}
		else if (StringUtils.equalsIgnoreCase("SUPERCEDE", calledFrom)){
			sapOrderType = "Z003";
			sapOrderTypeIndicator = "SUPER";
		}
		else if (StringUtils.equalsIgnoreCase("UPMOD", calledFrom)){
			sapOrderType = "ZUPM";
			//sapOrderTypeIndicator = "SUPER");
		}	
		
		Map returnMap = new HashMap();
        returnMap.put("SAP_ORDER_TYPE", sapOrderType);
        returnMap.put("SAP_ORDER_TYPE_IND", sapOrderTypeIndicator);
        
        return returnMap;
	}
	
	
	   
	private class MessageSender{
		
		String messageString = null;
			
		private MessageSender(String message, String queue)	{
				this.messageString = message;
		}
			
		public String doInJms(Session session, MessageProducer messageProducer, Long timeToLive) throws JMSException{
			
			TextMessage textMessage = session.createTextMessage(messageString);
			//textMessage.setJMSCorrelationID(UUID.randomUUID().toString());
			//textMessage.setJMSCorrelationID(bodid);
			messageProducer.setTimeToLive(timeToLive);
			messageProducer.send(textMessage);
				
			if(session.getTransacted()){
					session.commit();
			}

			// Fred E.
			// 11/02/2020 
			// Defect 1812
			/*
			String jmsMessageID = null;
			if (StringUtils.startsWith(textMessage.getJMSMessageID(), "ID:")){
				jmsMessageID = textMessage.getJMSMessageID().replaceFirst("ID:", "");
			}else{
				jmsMessageID = textMessage.getJMSMessageID();
			}
			*/
			String jmsMessageID = textMessage.getJMSMessageID();
			System.out.println("T319 in doInJmsjmsMessageID = " + jmsMessageID);
			return jmsMessageID;
		}
	}	
		
	private class ResponsePoller{
			
		String messageId;
			
		private ResponsePoller(String messageId){
				this.messageId = messageId;
		}
			
		public Object doInJms(Session session, QueueBrowser queueBrowser) throws JMSException{
			
			String returnResult = null;
		    String filter = String.format("JMSCorrelationID='%1$s'",messageId);
		    
			Enumeration enumerationQueueBrowser = queueBrowser.getEnumeration();
				
			while(enumerationQueueBrowser.hasMoreElements()){
	           
				
				MessageConsumer messageConsumer = session.createConsumer(queueBrowser.getQueue(),filter);
				Message message = messageConsumer.receive(5);

				//System.out.println("************** in ResponsePoller.doInJms filter =  " + filter);	            
				if(message!=null){
					//System.out.println("************** in ResponsePoller.doInJms message.getJMSMessageID() =  " + message.getJMSMessageID());
					//System.out.println("************** in ResponsePoller.doInJms message.getJMSCorrelationID() =  " + message.getJMSCorrelationID());		
					//System.out.println("************** in ResponsePoller.doInJms text =  " +((TextMessage)message).getText());
					
					message.acknowledge();      //// remove from queue
					returnResult=((TextMessage)message).getText();
				}
				
				messageConsumer.close();
				break;					
			}
				
			return returnResult;
		}		
	
	}

}