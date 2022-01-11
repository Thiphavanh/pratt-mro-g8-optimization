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
 *  File:    RepairOrderT308Interface.java
 * 
 *  Created: 2019-08-01
 * 
 *  Author:  Raeann Thorpe
 * 
 *  Revision History
 * 
 *  Date      	Who                	Description
 *  ----------	---------------	---------------	---------------------------------------------------
 * 2019-08-01 	Raeann Thorpe   Initial Release for PW_SMRO_TR_308
 * 2019-08-21   Fred Ettefagh   change methods to add outboundMessageId, to look for correct return message
*  2019-09-20   Bahram J.       SMRO_TR_308, changed xml to use synWorkOrder instead of syncRecord
*  2019-09-20   Bahram J        SMRO_TR_308, added role to bypass t308 interface 
*  2019-09-25   Fred Ettefagh   SMRO_TR_308, changed class name from ReleaseAlterationOrderInterface to  RepairOrderT308Interface
*  2019-10-30   Fred Ettefagh   SMRO_TR_308, changed code to set t308 msg bodid value as JMSCorrelationID, this is done to help WM with handling  JMSCorrelationID
*  2019-10-31   Fred Ettefagh   SMRO_TR_308, changed code to use jmsMessageID instead of JMSCorrelationID
*  2019-11-01   Fred Ettefagh   SMRO_TR_308, changed code to remove "ID:" from jmsMessageID.
*  2019-11-04   Fred Ettefagh   SMRO_TR_308, changed code to handle reply message ERROR/SUCCESS XML data and tags 
*  2019-11-06   Fred Ettefagh   SMRO_TR_308, changed how error msg is dispalyed to user
*  2019-11-12   Fred Ettefagh   SMRO_TR_308, changed how messageProducerSetTimeToLive and jmsResponseTimeoutSeconds are set
*  2019-11-12   Fred Ettefagh   SMRO_TR_308, changed how messageProducerSetTimeToLive and jmsResponseTimeoutSeconds based on t319 interface testing 
*  2020-01-16	Brendan Polak	SMRO_TR_308, defect 1474, temporary fix to not fail out if SAP does not respond 
*  2020-02-13   Fred Ettefagh   SMRO_TR_308, Defect 1474, changed code to send t308 message after certain changes for WO Alt and task include exclude
*  2020-02-24   Fred Ettefagh   SMRO_TR_308, Defect 1474, removed test code and put back fail message if a valid SAP response is not returned  
*  2020-05-13   John DeNinno    SMRO_TR_308, Defect 1656, They wanted the Plan Part No which may be different than the order number in some situations
*  2020-11-02   Fred Ettefagh   Defect 1812 Changed code so that we do not replace "ID:" for jmsMessageID/CorrelationID
 */
package com.pw.solumina.T308.integration.impl;   

import java.util.Date;
import java.util.Enumeration;
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
import com.pw.common.utils.CommonUtilsPW;
import com.pw.solumina.sfwid.dao.impl.RepairWorkOrderT308Dao;

public class RepairOrderT308Interface  
{
	Log logger = LogFactory.getLog(RepairOrderT308Interface.class);
	@Reference OrderReleaseInterfaceDao orderReleaseDao;
	@Reference T308EventProcessorImpl t308EventProcessorImpl;
	@Reference ReceiveT308ReplyProcessorImpl receiveT308ReplyProcessorImpl;
	@Reference ConnectionFactory connectionFactory = null; 
	@Reference CommonDaoPW commonDaoPW = null;
	@Reference MessageLogger messageLogger;  
	@Reference IMessage message = null;
	@Reference ServiceDefinitionDao serviceDefinitionDao;
	@Reference RepairWorkOrderT308Dao repairWorkOrderT308Dao;
	@Reference CommonUtilsPW commonUtilsPW;

	static final String SERVICE_NAME = "PW_S_T308_ORDER_RELEASE";  
	int t308JmsResponseTimeoutSeconds = 20;	
	long t308MessageProducerSetTimeToLive = 40;	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void sendOrderT308Msg(String orderId, String calledFrom){
		
		System.out.println("in sendOrderT308Msg");
		boolean syncWithSapNeeed = false;
		String successFailMsg = null;
					
		syncWithSapNeeed = commonUtilsPW.syncT308WithSap(orderId,calledFrom);
		
		if (!syncWithSapNeeed){
			commonUtilsPW.deleteRepairOrderAltT308Data(orderId);
		}else{
				List<ServiceDef> servDefs = serviceDefinitionDao.selectServiceDefinition("PW_S_T308_ORDER_RELEASE");
				ServiceDef servDef = servDefs.get(0);
				
				T308Event t308Event = new T308Event(this);
				List orderHeaderList = orderReleaseDao.selectSfwidOrderDescList(orderId);
				if (orderHeaderList!= null && !orderHeaderList.isEmpty()){
					for(int i=0; i < orderHeaderList.size(); i++){
						Map headerMap = (Map)orderHeaderList.get(i);
						String orderNo = (String) headerMap.get("ORDER_NO");
						String planNo = (String) headerMap.get("PLAN_NO");
						String workLocation = (String) headerMap.get("ASGND_WORK_LOC");
						Number orderQty = (Number) headerMap.get("ORDER_QTY");
						//String orderPartNo = (String) headerMap.get("PART_NO"); //defect 1656
						String planPartNo = (String) headerMap.get("PLAN_PART_NO"); //defect 1656
		
						t308Event.setWorkOrderNumber(orderNo);
						t308Event.setOrderId(orderId);
						t308Event.setPlanNo(planNo);
						t308Event.setWorkLocation(workLocation);
						t308Event.setOrderQty(orderQty);
						t308Event.setOrderPartNo(planPartNo); //defect 1656
					}	
				}  
		
				MessageLoggerParamsImpl messageLoggerParamsImpl = new MessageLoggerParamsImpl(SERVICE_NAME);
				String outBoundXml =  t308EventProcessorImpl.generateMessage(t308Event, messageLoggerParamsImpl);
				successFailMsg = sendMessage(outBoundXml,servDef,t308Event,messageLoggerParamsImpl);
			}
			
	}


	public String sendMessage(String outboundXml, ServiceDef servDef,T308Event t308Event,MessageLoggerParams messageLoggerParams) 
	{
		Connection connection = null;
		Session session = null;
		MessageProducer messageProducer = null;
		String responseMsg = null;
        String errorMsg = null;
        String outboundJMSMessageId = null;

		try 
		{
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			Queue queueSend = session.createQueue(servDef.getQueueName());
			messageProducer = session.createProducer(queueSend);

	        String messageProducerSetTimeToLiveString = commonDaoPW.getPwustGlobalConfigValue("T308_OUTBOUND_JMS_EXPIRATION_SECONDS");
	        if (StringUtils.isNoneBlank(messageProducerSetTimeToLiveString)){
	        	t308MessageProducerSetTimeToLive = Long.parseLong(messageProducerSetTimeToLiveString);
	        }
	    	logger.debug("T308, t308MessageProducerSetTimeToLive = " + t308MessageProducerSetTimeToLive);
	    	System.out.println("Tt308MessageProducerSetTimeToLive = " + t308MessageProducerSetTimeToLive);
            String jmsReplyTimeOutString = commonDaoPW.getPwustGlobalConfigValue("T308_REPLY_JMS_TIMEOUT_SECONDS");
            if (StringUtils.isNoneBlank(jmsReplyTimeOutString)){
            	t308JmsResponseTimeoutSeconds = Integer.parseInt(jmsReplyTimeOutString);  
	        }
	    	logger.debug("T308, t308JmsResponseTimeoutSeconds = " + t308JmsResponseTimeoutSeconds);
	    	System.out.println("T308JmsResponseTimeoutSeconds = " + t308JmsResponseTimeoutSeconds);
			
			MessageSender messageSender  = new MessageSender(outboundXml,servDef.getQueueName());
			outboundJMSMessageId = messageSender.doInJms(session, messageProducer, t308MessageProducerSetTimeToLive);
			
			Date currentTimeStamp;
			currentTimeStamp = new Date();
            if (logger.isDebugEnabled()) {
            	logger.debug("T308, msg send  outboundXml = " + outboundXml );
            }
			messageLoggerParams.setStatus(MessageLogger.STATUS_SEND_SUCCESS);
			messageLoggerParams.setRef1(t308Event.getWorkOrderNumber());
			messageLoggerParams.setRef2(t308Event.getPlanNo());
			messageLoggerParams.setRef4(t308Event.getOrderQty().toString());
			messageLoggerParams.setRef5(outboundJMSMessageId);
			messageLoggerParams.setMessageText(outboundXml);
			messageLoggerParams.setMessageID(outboundJMSMessageId);
			messageLoggerParams.setJmsMessageId(outboundJMSMessageId );
			messageLoggerParams.setMessageCreationDate(currentTimeStamp);
			messageLogger.addMessage(messageLoggerParams);
			
			messageProducer.close();
			messageProducer = null;
			connection.start();
			long start = System.currentTimeMillis();

			while( (System.currentTimeMillis()-start < (t308JmsResponseTimeoutSeconds*1000)) && (responseMsg == null))
			{
				QueueBrowser queueBrowser = session.createBrowser(session.createQueue(servDef.getReplyQueueName()));
				responseMsg = (String) new ResponsePoller(outboundJMSMessageId ).doInJms(session, queueBrowser);
				queueBrowser.close();
			}
			connection.stop();
		}  
		catch (JMSException jex)
		{
			messageLogger.updateMessageStatus(outboundJMSMessageId , MessageLogger.REPLY_STATUS_FAILURE, jex.getLocalizedMessage() );
			logger.error("T308, "+ jex);
		}
		finally
		{
			try
			{
				if(messageProducer!= null)
					messageProducer.close();
				if(session!= null)
					session.close();
				if(connection!= null)
					connection.close();
			}
			catch (JMSException jex)
			{
				throw new RuntimeException(jex);
			}
		}

		if (responseMsg == null){
        	messageLogger.updateMessageStatus(outboundJMSMessageId, MessageLogger.REPLY_STATUS_FAILURE, "No Response Received from SAP for T308" );
        	message.raiseError("MFI_50400","No Response Received from SAP for T308");
		}
		else{
			ReplyLoggerParamsImpl replyLoggerParams = new ReplyLoggerParamsImpl(SERVICE_NAME);
			replyLoggerParams.setReplyText(responseMsg);
			replyLoggerParams.setOriginalMessageID(messageLoggerParams.getMessageID());
			replyLoggerParams.setTransactionStatus(MessageLogger.REPLY_STATUS_FAILURE);
			replyLoggerParams.setRef1(t308Event.getWorkOrderNumber());

			try	{
				
				XmlObject xmlObject = receiveT308ReplyProcessorImpl.parseReply(responseMsg);
				if (xmlObject==null){
	           		replyLoggerParams.setTransactionStatus(MessageLogger.REPLY_STATUS_FAILURE);
	           		replyLoggerParams.setErrorText("response xml message is not valid from SAP for T308 ");
	           		message.raiseError("MFI_50400","response xml message is not valid from SAP for T308 ");
				}
				
				receiveT308ReplyProcessorImpl.processReply(xmlObject, replyLoggerParams);
				responseMsg = replyLoggerParams.getRef5();
				errorMsg = replyLoggerParams.getErrorText();
				
				if (StringUtils.equalsIgnoreCase(responseMsg, "SUCCESS")){
            		replyLoggerParams.setTransactionStatus(MessageLogger.REPLY_STATUS_SUCCESS);
            		commonUtilsPW.deleteRepairOrderAltT308Data(t308Event.getOrderId());
				}else if (!StringUtils.isBlank(errorMsg)){
		           	messageLogger.updateMessageStatus(outboundJMSMessageId, MessageLogger.REPLY_STATUS_FAILURE, errorMsg );
		           	message.raiseError("MFI_50400", errorMsg);
				}else{
					errorMsg = "SAP Error: error msg tag was is empty";
					messageLogger.updateMessageStatus(outboundJMSMessageId, MessageLogger.REPLY_STATUS_FAILURE, errorMsg  );
		           	message.raiseError("MFI_50400",errorMsg);
				}
			}
			catch (Exception e)	{
				e.printStackTrace();
				replyLoggerParams.setErrors(e); 
            	messageLogger.updateMessageStatus(outboundJMSMessageId, MessageLogger.REPLY_STATUS_FAILURE, e.getMessage() );
            	logger.error("T308 ,"+ replyLoggerParams.getTextFromErrors());
            	responseMsg = "ERROR: " + replyLoggerParams.getErrorText();
            	throw new RuntimeException(responseMsg);
			}finally {
				messageLogger.addReplyMessage(replyLoggerParams);
			}
	}
    
	return responseMsg;
	}

	public ConnectionFactory getConnectionFactory(){
		return connectionFactory;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory){
		this.connectionFactory = connectionFactory;
	}

	private class MessageSender{
		String messageString = null;

		private MessageSender(String message, String queue){
			this.messageString = message;
		}

		public String doInJms(Session session, MessageProducer messageProducer, Long timeToLive) throws JMSException{
			
			TextMessage textMessage = session.createTextMessage(messageString);
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
			System.out.println("T308 in doInJmsjmsMessageID = " + jmsMessageID);
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
				//System.out.println("************** in ResponsePoller.doInJms message.getJMSMessageID() =  " + ((TextMessage)message).getText());				
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
