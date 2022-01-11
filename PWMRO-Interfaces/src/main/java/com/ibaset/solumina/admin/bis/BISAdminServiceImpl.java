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
*  File:    BISAdminServiceImpl.java
* 
*  Created: 2019-06-12
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2019-06-12 	Fred Ettefagh   Initial Release for PW_SMRO_TR_319
*/
package com.ibaset.solumina.admin.bis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlObject;
import org.springframework.security.web.authentication.www.BasicProcessingFilter;
import org.springframework.ws.soap.security.wss4j.Wss4jSecurityInterceptor;

import com.ibaset.common.IntegrationConstants;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.client.SoluminaServiceLocator;
import com.ibaset.solumina.admin.SoluminaBeanService;
import com.ibaset.solumina.bis.BISAdminService;
import com.ibaset.solumina.domain.dao.DAO;
import com.ibaset.solumina.domain.dao.ObjectQuery;
import com.ibaset.solumina.integration.common.IntegrationWSMessageEndpoint;
import com.ibaset.solumina.sfbis.domain.DestinationDef;
import com.ibaset.solumina.sfbis.domain.ServiceDef;
import com.ibaset.solumina.sfbis.domain.ServiceDestDef;
import com.ibaset.solumina.sfcore.application.ILogin;
import com.ibaset.solumina.sfcore.domain.User;
import com.ibaset.solumina.sffnd.application.IGlobalConfiguration;
import com.ibaset.solumina.sffnd.domain.GlobalConfigInterface;
import com.ibaset.solumina.sffnd.domain.GlobalConfiguration;


public class BISAdminServiceImpl implements BISAdminService 
{
	private boolean validateInboundWS = true;
	private DAO dao;
	private BISAdminUtils bisAdminUtils = new BISAdminUtils();
	private IGlobalConfiguration globalConfiguration = SoluminaServiceLocator.locateService(IGlobalConfiguration.class);
	private SoluminaBeanService soluminaBeanService = SoluminaServiceLocator.locateService(SoluminaBeanService.class);
	
	//**********************************
	// BIS Service Beans Data Structure
	private static final int INBOUND_LISTENER_BEAN_INDEX = 0;
	private static final int ENDPOINT_BEAN_INDEX = 1;
	private static final int MESSAGE_PROCESSOR_BEAN_INDEX = 2;
	private static final int MAPPER_BEAN_INDEX = 3;
	private static final Map<String, String[]> inboundServiceBeans = new HashMap<String, String[]>();
	static
	{
		// INBOUND SERVICE BEANS
		// Key = service name
		// Values: 1. Inbound Listener Bean
		//         2. Endpoint Bean
		//         3. Message Processor Bean
		//         4. Mapper Bean
		
		String[] R_BOM_SYNC_Beans = {"receiveBomSyncListener","bomSyncEndpoint","bomSyncMessageProcessor","bomMapper"};
		String[] R_CATALOG_NOTIFY_Beans = {"receiveNotifyCatalogSyncListener","notifyCatalogSyncEndpoint","notifyCatalogMessageProcessor","notifyCatalogMapper"};
		String[] R_ECN_SYNC_Beans = {"receiveEcnSyncListener","ecnSyncEndpoint","ecnSyncMessageProcessor","ecnMapper"};
		String[] R_EXTERNAL_DAT_COL_Beans = {"receiveExternalDatColListener","receiveExternalDataCollectionEndpoint","externalDatColMessageProcessor","externalDatColMapper"};
		String[] R_LOCATION_SYNC_Beans = {"receiveLocationSyncListener","locationSyncEndpoint","locationSyncMessageProcessor","syncLocationMapper"};
		String[] R_MACHINE_CYCLE_SYNC_Beans = {"receiveMachineCycleListener",null,"machineCycleMessageProcessor",null};
		String[] R_MATERIAL_ISSUE_Beans = {"receiveMaterialIssueListener","receiveMaterialIssueEndpoint","materialIssueMessageProcessor","issueInventoryMapper"};
		String[] R_OPER_TYPE_SYNC_Beans = {"receiveOperTypeSyncListener","operTypeSyncEndpoint","operTypeSyncMessageProcessor","operTypeMapper"};
		String[] R_PART_SYNC_Beans = {"receiveItemSyncListener","partMaterialSyncEndpoint","itemSyncMessageProcessor","itemMapper"};
		String[] R_PERSONNEL_SYNC_Beans = {"receivePersonnelSyncListener","personnelSyncEndpoint","personnelSyncMessageProcessor","personnelMapper"};
		String[] R_POLINE_RECEIPT_Beans = {"receivePOLineReceiptListener","receivePOLineReceiptEndpoint","pOLineReceiptMessageProcessor","poLineReceiptMapper"};
		String[] R_PO_SYNC_Beans = {"receivePOSyncListener","purchaseOrderSyncEndpoint","pOSyncMessageProcessor","poMapper"};
		String[] R_PROCESS_ROUTG_SYNC_Beans = {"receiveProcessRoutingSyncListener","receiveProcessRoutingSyncEndpoint","processRoutingSyncMessageProcessor","planMapper"};
		String[] R_SUPPLIER_SYNC_Beans = {"receiveSupplierSyncListener","supplierSyncEndpoint","supplierSyncMessageProcessor","supplierMapper"};
		String[] R_WO_ISSUE_Beans = {"receiveWOIssueListener","woIssueEndpoint","WOIssueMessageProcessor","workOrderMapper"};
		String[] R_WO_OPER_SCHED_Beans = {"receiveWOOperationScheduleListener","woOperationScheduleSyncEndpoint","wOOperationScheduleSyncMessageProcessor","workOrderMapper"};
		String[] R_WO_STATUS_Beans = {"receiveWOStatusUpdateListener","woStatusUpdateSyncEndpoint","wOStatusUpdateMessageProcessor","productionOrderMapper"};
		
		String[] PW_R_T301_ORDER_Beans = {"ReceiveT301Listener",null,"T301MessageProcessor",null};
		String[] PW_R_T226_MODULE_SWAPS_Beans = {"ReceiveT226Listener",null,"T226MessageProcessor",null};
		String[] PW_R_M221_NETWORK_Beans = {"ReceiveM211Listener",null,"M211MessageProcessor",null};
		String[] PW_R_EACP_AUTH_Beans = {"ReceiveEACPListener",null,"EACPMessageProcessor",null};
		String[] PW_R_LO_NOTIFICATION_Beans  = {"ReceiveT222Listener",null,"T222MessageProcessor",null};
		String[] PW_R_M316_CAT_CODES_Beans  ={"ReceiveM316Listener",null,"M316MessageProcessor",null};
		
		inboundServiceBeans.put("R_BOM_SYNC", R_BOM_SYNC_Beans);
		inboundServiceBeans.put("R_CATALOG_NOTIFY", R_CATALOG_NOTIFY_Beans);
		inboundServiceBeans.put("R_ECN_SYNC", R_ECN_SYNC_Beans);
		inboundServiceBeans.put("R_EXTERNAL_DAT_COL", R_EXTERNAL_DAT_COL_Beans);
		inboundServiceBeans.put("R_LOCATION_SYNC", R_LOCATION_SYNC_Beans);
		inboundServiceBeans.put("R_MACHINE_CYCLE_SYNC", R_MACHINE_CYCLE_SYNC_Beans);
		inboundServiceBeans.put("R_MATERIAL_ISSUE", R_MATERIAL_ISSUE_Beans);
		inboundServiceBeans.put("R_OPER_TYPE_SYNC", R_OPER_TYPE_SYNC_Beans);
		inboundServiceBeans.put("R_PART_SYNC", R_PART_SYNC_Beans);
		inboundServiceBeans.put("R_PERSONNEL_SYNC", R_PERSONNEL_SYNC_Beans);
		inboundServiceBeans.put("R_POLINE_RECEIPT", R_POLINE_RECEIPT_Beans);
		inboundServiceBeans.put("R_PO_SYNC", R_PO_SYNC_Beans);
		inboundServiceBeans.put("R_PROCESS_ROUTG_SYNC", R_PROCESS_ROUTG_SYNC_Beans);
		inboundServiceBeans.put("R_SUPPLIER_SYNC", R_SUPPLIER_SYNC_Beans);
		inboundServiceBeans.put("R_WO_ISSUE", R_WO_ISSUE_Beans);
		inboundServiceBeans.put("R_WO_OPER_SCHED", R_WO_OPER_SCHED_Beans);
		inboundServiceBeans.put("R_WO_STATUS", R_WO_STATUS_Beans);
		
		inboundServiceBeans.put("PW_R_T301_ORDER", PW_R_T301_ORDER_Beans);
		inboundServiceBeans.put("PW_R_T226_MODULE_SWAPS",PW_R_T226_MODULE_SWAPS_Beans);
		inboundServiceBeans.put("PW_R_M221_NETWORK",PW_R_M221_NETWORK_Beans);
		inboundServiceBeans.put("PW_R_EACP_AUTH",PW_R_EACP_AUTH_Beans);
		inboundServiceBeans.put("PW_R_LO_NOTIFICATION",PW_R_LO_NOTIFICATION_Beans);
		inboundServiceBeans.put("PW_R_M316_CAT_CODES",PW_R_M316_CAT_CODES_Beans);

		
	}
	/*private static final Map<String, List<String>> bisServiceBeans = new HashMap<String, List<String>>();
    static
    {
    	//endPointMap.put("R_BOM_SYNC_RPLY", "b");
    	//endPointMap.put("R_CATALOG_NOTIFY_RPLY", "d");
    }*/
	//***********************

	public BISAdminServiceImpl() 
	{
		super();
		
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	public void createService(Object serviceDef) 
	{
		bisAdminUtils.createService(serviceDef);
	}
	
	public void removeService(String serviceName) 
	{
		bisAdminUtils.removeService(serviceName);
	}
	
	public void enableAllServices()
	{
		
	}
	
	public void insertServiceDestDef(Object serviceDestDef) 
	{
		dao.insert((ServiceDestDef)serviceDestDef);
	}

	public void insertDestinationDef(Object destination) 
	{
		dao.insert((DestinationDef)destination);
	}

	public void updateServiceDef(Object serviceDef) 
	{
		dao.update((ServiceDef)serviceDef);
	}

	public void updateServiceDestDef(Object serviceDestDef) 
	{
		dao.update((ServiceDestDef)serviceDestDef);
	}
	
	public void updateDestinationDef(Object destinationDef) 
	{
		dao.update((DestinationDef)destinationDef);
	}
	
	public void replaceDestinationDef(Object newDestinationDef, String oldDestinationName, String oldUri)
	{
		DestinationDef newDest = (DestinationDef) newDestinationDef;
		dao.executeNamedQuery("BISAdmin.updateDestinationDef", 
							  	new Object[]{
									newDest.getUri(),
									newDest.getDescription(),
									newDest.getRef1(),
									newDest.getRef2(),
									newDest.getRef3(),
									newDest.getRef4(),
									newDest.getRef5(),
									oldDestinationName,
									oldUri
								});
	}
	
	public void deleteServiceDestDef(Object serviceDestDef)
	{
		dao.delete((ServiceDestDef)serviceDestDef);
	}
	
	public void deleteDestinationDef(Object destinationDef)
	{
		dao.delete((DestinationDef) destinationDef);
	}
	
	@SuppressWarnings("unchecked")
	public GlobalConfigInterface[] getGlobalConfigs(String configModuleName, String parameterName)
	{
		ObjectQuery q = new ObjectQuery(GlobalConfiguration.class);
		q.add(q.eq("id.configModuleName", configModuleName));
		if (parameterName != null)
		{
			q.add(q.eq("id.parameterName", parameterName));
		}
		List<GlobalConfiguration> list = dao.select(q);
		GlobalConfigInterface[] res = new GlobalConfigInterface[list.size()];
		for(int i=0;i<res.length;++i) res[i] = list.get(i);
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public GlobalConfigInterface[] getGlobalConfigs()
	{
		ObjectQuery q = new ObjectQuery(GlobalConfiguration.class);
		q.addAscendingOrder("id.configModuleName");
		q.addAscendingOrder("id.parameterName");
		List<GlobalConfiguration> list = dao.select(q);
		GlobalConfigInterface[] res = new GlobalConfigInterface[list.size()];
		for(int i=0;i<res.length;++i) res[i] = list.get(i);
		return res;
	}
	
	public String getGlobalConfigValue(String configModuleName, String parameterName)
	{
		GlobalConfigInterface[] res = this.getGlobalConfigs(configModuleName, parameterName);
		if ((res != null) && (res.length == 1))
		{
			return res[0].getParameterValue();
		}
		else
		{
			return null;
		}
	}
	
	public void updateGlobalConfiguration(Object globalConfiguration)
	{
		GlobalConfiguration config = (GlobalConfiguration) globalConfiguration;
		this.globalConfiguration.updateGlobalConfiguration(config.getConfigModuleName(), config.getParameterName(), config.getParameterValue());
		//dao.update((GlobalConfiguration) globalConfiguration);
	}
	
	public boolean isCPMESEnabled()
	{
		return this.getGlobalConfigs("COSTPOINT_MES", null).length > 0;
	}
	
	public boolean isAutotimeEnabled()
	{
		GlobalConfigInterface[] res = this.getGlobalConfigs(SoluminaConstants.FOUNDATION, 
															IntegrationConstants.AUTOTIME_INTEGRATION_OPTIONS);
		if ((res != null) && (res.length != 0))
		{
			if (StringUtils.equals(res[0].getParameterValue(),SoluminaConstants.NONE))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		else
		{
			return false;
		}
	}
	
	public void saveSfcoreUser(String userid, String password)
	{
		User user = new User();
		user.setUserid(userid);
		user.setPassword(password);
		user.setAccountStatus(SoluminaConstants.OPEN_STATUS);
		user.setAccountType(SoluminaConstants.NORMAL);
		dao.save(user);
	}
	
	public void deleteSfcoreUser(String userid, String password)
	{
		dao.executeNamedQuery("BISAdmin.deleteSfcoreUser", 
			  	new Object[]{
					userid,
					password
				});
	}
	
	public void deleteSffndUser(String userid)
	{
		dao.executeNamedQuery("BISAdmin.deleteSffndUser", 
			  	new Object[]{
					userid
				});
	}
	
	public String getSfcoreUserPassword(String userid)
	{
		User user = this.getSfcoreUser(userid);
		if (user != null)
		{
			return user.getPassword();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public User getSfcoreUser(String userid)
	{
		ObjectQuery q = new ObjectQuery(User.class);
		q.add(q.eq("userid", userid));
		List<User> list = dao.select(q);
		if ((list != null)&&(list.size()==1))
		{
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public void updateWss4jSecurityInterceptorBean(String beanName, String username, String password)
	{
		Map beans = SoluminaServiceLocator.getServicesForClass(Wss4jSecurityInterceptor.class);
		if (beans.containsKey(beanName))
		{
			Wss4jSecurityInterceptor bean = (Wss4jSecurityInterceptor)beans.get(beanName);
			bean.setSecurementUsername(username);
			bean.setSecurementPassword(password);
		}
	}
	
	@SuppressWarnings({ "static-access", "unchecked" })
	public boolean setInboundWSSecurity(boolean validate)
	{
		boolean processingOkay = false;
		Map beans = SoluminaServiceLocator.getServicesForClass(Wss4jSecurityInterceptor.class);
		if (beans.containsKey(this.WSS_VALIDATION_BEAN_NAME))
		{
			Map filterBeans = SoluminaServiceLocator.getServicesForClass(BasicProcessingFilter.class);
			if (filterBeans.containsKey(this.GATEWAY_FILTER_BEAN_NAME))
			{
				Map loginBeans = SoluminaServiceLocator.getServicesForClass(ILogin.class);
				if (loginBeans.containsKey(this.LOGIN_BEAN_NAME))
				{
					ILogin loginBean = (ILogin)loginBeans.get(this.LOGIN_BEAN_NAME);
					loginBean.setBypassDBLogin(!validate);
					Wss4jSecurityInterceptor bean = (Wss4jSecurityInterceptor)beans.get(this.WSS_VALIDATION_BEAN_NAME);
					bean.setValidationActions(validate ? this.USERNAME_TOKEN : this.NO_SECURITY);
					BasicProcessingFilter filterBean = (BasicProcessingFilter)filterBeans.get(this.GATEWAY_FILTER_BEAN_NAME);
					filterBean.setDenyAllClientAccess(!validate);
					if (validate)
					{
						this.deleteSfcoreUser("SOLUMINA-IC", "DATA-MIGRATION");
					}
					else
					{
						this.saveSfcoreUser("SOLUMINA-IC", "DATA-MIGRATION");
					}
				}
			}
		}
		this.validateInboundWS = validate;
		return processingOkay;
	}
	
	public boolean validateInboundWS()
	{
		return this.validateInboundWS;
	}
	
	
	private Object getBean(String serviceName, int beanNameIndex)
	{
		Object bean = null;
		String[] beans = inboundServiceBeans.get(serviceName);
		if (beans != null)
		{
			String beanName = beans[beanNameIndex];
			if (beanName != null)
			{
				bean = soluminaBeanService.getBean(beanName);
			}
		}
		
		return bean;
	}
	
	private Object getEndPointImplBean(String serviceName)
	{
		return this.getBean(serviceName, ENDPOINT_BEAN_INDEX);
	}
	
	private Object getInboundListenerlBean(String serviceName)
	{
		return this.getBean(serviceName, INBOUND_LISTENER_BEAN_INDEX);
	}

	public void injectInboundJMSMessage(String serviceName, String message) throws Throwable
	{
		this.injectInboundMessageThroughInboundListener(serviceName, message);
	}
	
	public void injectInboundWSMessage(String serviceName, String message) throws Throwable
	{
		this.injectInboundMessageThroughWSEndpoint(serviceName, message);
	}
	
	private void injectInboundMessageThroughInboundListener(String serviceName, String message) throws Throwable
	{
		MessageListener inboundMessageListener = (MessageListener) getInboundListenerlBean(serviceName);
		if (inboundMessageListener != null)
		{
			Session session = (Session) soluminaBeanService.getBean("session");
			Message jmsMessage = session.createTextMessage(message);
			inboundMessageListener.onMessage(jmsMessage);
		}
		else
		{
			throw new RuntimeException("Could not find inbound message listener for "+serviceName);
		}
	}
	
	private void injectInboundMessageThroughWSEndpoint(String serviceName, String message) throws Throwable
	{
		IntegrationWSMessageEndpoint wsEndpoint = (IntegrationWSMessageEndpoint) getEndPointImplBean(serviceName);
		if (wsEndpoint != null)
		{
			XmlObject inboundXmlObject = XmlObject.Factory.parse(message);
			wsEndpoint.invokeInternal(inboundXmlObject);
		}
		else
		{
			throw new RuntimeException("Could not find WS End Point for "+serviceName);
		}
	}
	
	 public static Map<String, String[]> getInboundservicebeans()
	 {
	     return inboundServiceBeans;
	 }
	
	
	
	
}

