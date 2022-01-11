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
*  File:    CommonUtilsPW.java
* 
*  Created: 2017-08-08
* 
*  Author:  c079222
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-08-08 	c079222		    Initial Release XXXXXXX
* 2018-04-13	R. Thorpe		SMRO_MD_M206 Added sendErrorEmail 
* 2018-10-01    Fred Ettefagh   Defect 980 Cannot Create Suborder due to supNet subNet and conf Numbers
* 2018-12-17    Fred Ettefagh   Defect 1044, fix repair order create 
* 2019-06-05    F. Ettefagh     Added new methods for Repair shop order split 
* 2019-07_18    Fred Ettefagh   Defect 1327, Added new methods for R2SP4 order create upgrade
* 2020-02-13    Fred Ettefagh   SMRO_TR_308, Defect 1474, changed code to send t308 message after certain changes for WO Alt and task include exclude
* 2020-02-24    Fred Ettefagh   SMRO_TR_308, Defect 1474, changed code to add user t308ForceSend flag
* 2020-02-27    Fred Ettefagh   SMRO_TR_308, Defect 1474, added Y/N check for t308ForceSend flag
* 2020-03-03    Fred Ettefagh   SMRO_TR_308, Defect 1474, changed method syncT308WithSap by adding dao call to check to see if any rows exists in table PWUST_REPAIR_ORDER_T308_DATA for an order id
* 2020-03-03    Fred Ettefagh   SMRO_WOE_310, Defect 1574 added method  launchOrderOper
* 2020-03-03    Fred Ettefagh   SMRO_WOE_310, Defect 1574 added method  launchTravelerUDV, and changed method launchTravelerUDV
* 2020-04-15    D.Miron         SMRO_WOE_310, Defect 1574 Changed input arguments to launchOrderOper: (barcode, mode)
* 2021-07-01    John DeNinno    Defect 1927, add code that accepted the turkish ö Ö
*/
package com.pw.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.security.Md5ShaPasswordEncoder;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.security.context.UserContext;
import com.ibaset.solumina.alert.mail.MailService;
import com.ibaset.solumina.sfbis.dao.ServiceDefinitionDao;
import com.ibaset.solumina.sfbis.domain.ServiceDef;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sfcore.application.IUser;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.application.IHistoryControl;
import com.ibaset.solumina.sffnd.application.IParse;
import com.ibaset.solumina.sffnd.application.IWrapUtils;
import com.ibaset.solumina.sfwid.application.ISerial;
import com.ibaset.solumina.sfwid.application.ISerialItems;
import com.pw.common.dao.CommonDaoPW;
import com.pw.solumina.sfwid.dao.impl.RepairWorkOrderT308Dao;

public class CommonUtilsPW {
	@Reference	private MailService mailService;
	@Reference  private IMessage message; 	///2013-03-25 SIQA_TR_T11
	@Reference  private IEvent   event;			///2013-03-25 SIQA_TR_T11
	@Reference	private CommonDaoPW commonInfo;
	@Reference	IHistoryControl historyControl = null;
	@Reference  CommonDaoPW commonDaoPW = null;
	@Reference ISerialItems serialItems = null;
	@Reference ISerial serial = null;
	@Reference IParse parse = null;
	@Reference RepairWorkOrderT308Dao repairWorkOrderT308Dao;
	@Reference IUser user = null;
	@Reference ServiceDefinitionDao serviceDefinitionDao;
	@Reference private IWrapUtils wrapUtils = null;
	
	
	
	public void launchTravelerUDV(String orderNo,String operNo){
		
		
		System.out.println("in CommonUtilsPW.lunchOrderOper orderno = " + orderNo);
		System.out.println("in CommonUtilsPW.lunchOrderOper operationNumber =  "+ operNo);
		
		StringBuffer parameters = new StringBuffer();
		
		parameters.append("BARCODE=").append(wrapUtils.wrapValue(orderNo))
				  .append(",OPER_NO=").append(wrapUtils.wrapValue(operNo));
		
		
		event.showUdv("PWUSD_A1425C7A9DFD1E66E05387971F0A02D6",
		 		 	  SoluminaConstants.INSERT,
		 		 	  "Message",
		 		 	  null,
		 		 	  parameters.toString());
	}
	
	public void launchOrderOper(String barcode,String mode){

		String orderNo = null;
		String operationNumber = null;


		StringTokenizer stringTokenizer = new StringTokenizer(barcode,","); 
		StringTokenizer stringTokenizer2 = new StringTokenizer(barcode,"ö");//defect 1927; 
		StringTokenizer stringTokenizer3 = new StringTokenizer(barcode,"Ö");//defect 1927
		


		if (barcode.contains("ö")) //defect 1927
		{
			stringTokenizer = stringTokenizer2;//defect 1927
		}
		else if (barcode.contains("Ö")) //defect 1927
		{
			stringTokenizer = stringTokenizer3;//defect 1927
		}
		

		orderNo = stringTokenizer.nextToken();
		operationNumber = stringTokenizer.nextToken();


		List orderList = commonDaoPW.selectsfwidOrderDescList(orderNo);
		if (orderList!=null && !orderList.isEmpty() && orderList.size()==1){
			Map orderMap = (Map)orderList.get(0);
			String orderId = (String) orderMap.get("ORDER_ID");

			List operList = commonDaoPW.selectSfwidOperDesc(orderId, operationNumber);
			if (operList!=null && !operList.isEmpty() && operList.size() ==1){
				System.out.println("in CommonUtilsPW.lunchOrderOper operList.size =  "+ operList.size());
				Map operMap = (Map)operList.get(0);
				Number oper_key = (Number)operMap.get("OPER_KEY");
				event.launchManufacturingOperation(orderId, oper_key.toString(), mode);
			}else{
				message.raiseError("MFI_20","operation no = " + operationNumber + " not found in solumina table");
			}
		}else {
			message.raiseError("MFI_20","order no = " + orderNo + " not found in solumina table");
		}
	}

	
	
	public boolean sendRepairT308(){
		
		boolean result = true;
		
		List<ServiceDef> servDefs = serviceDefinitionDao.selectServiceDefinition("PW_S_T308_ORDER_RELEASE");
		ServiceDef servDef = servDefs.get(0);	

		if (!servDef.isEnabled()){
			System.out.println("in CommonUtilsPW.sendRepairT308 PW_S_T308_ORDER_RELEASE is disabled");
			result = false;
		}
		
		if (user.hasPrivilege("PWUST_T308_BYPASS")){
			System.out.println("in CommonUtilsPW.sendRepairT308 user " + ContextUtil.getUser().getUsername() + " has PWUST_T308_BYPASS Priv");
			result = false;
		}
		
		return result;
	}
	
	
	public int deleteRepairOrderAltT308Data(String orderId){
		
		return repairWorkOrderT308Dao.deleteRepairOrderAltT308Data(orderId);
	}
	
	public void saveRepairOrderAltT308Data(String orderId){
			
			repairWorkOrderT308Dao.insertOperData(orderId);
			repairWorkOrderT308Dao.insertPartData(orderId);	
	}
	
	public boolean syncT308WithSap(String orderId,String calledFrom){
		
		boolean sapNeedsToBeSynced = false;
		
		if 	(StringUtils.equalsIgnoreCase(calledFrom, "releaseRepairOrder")){
			sapNeedsToBeSynced = true;
		}else{
			
			Map map = repairWorkOrderT308Dao.selectSfwidOrderDesc(orderId);
			String t308ForceSend = (String)map.get("T308_FORCE_SEND");
			if (StringUtils.equalsIgnoreCase(t308ForceSend, "Y")){
				sapNeedsToBeSynced = true;
				System.out.println("in CommonUtilsPW.syncT308WithSap T308_FORCE_SEND = Y " );
			}else if (StringUtils.equalsIgnoreCase(t308ForceSend, "N")){
				sapNeedsToBeSynced = false;
				System.out.println("in CommonUtilsPW.syncT308WithSap T308_FORCE_SEND = N " );
			}else{
				
				 int count = repairWorkOrderT308Dao.selectCountFromPwustRepairOrderT308Data(orderId);
				 System.out.println("in CommonUtilsPW.syncT308WithSap count = " + count);
				 if (count == 0 ){
					 sapNeedsToBeSynced =false;
				 }else{
				
						List operChangesList = repairWorkOrderT308Dao.opersChanged(orderId);
						if (operChangesList!=null && !operChangesList.isEmpty()){
							System.out.println("in CommonUtilsPW.syncT308WithSap operChangesList = " + operChangesList);
							sapNeedsToBeSynced =true;
						}else{
							List operDeleteList = repairWorkOrderT308Dao.opersDeleted(orderId);
							if (operDeleteList!=null && !operDeleteList.isEmpty()){
								System.out.println("in CommonUtilsPW.syncT308WithSap operDeleteList = " + operDeleteList);
								sapNeedsToBeSynced =true;
							}
						}
				
						if (!sapNeedsToBeSynced){
							List operPartChangeList = repairWorkOrderT308Dao.partsChanged(orderId);
							if (operPartChangeList!=null && !operPartChangeList.isEmpty()){
								System.out.println("in CommonUtilsPW.syncT308WithSap operPartChangeList = " + operPartChangeList);
								sapNeedsToBeSynced =true;
							}else{
								List operPartDeleteList = repairWorkOrderT308Dao.partsDeleted(orderId);
								if (operPartDeleteList!=null && !operPartDeleteList.isEmpty()){
									System.out.println("in CommonUtilsPW.syncT308WithSap operPartDeleteList = " + operPartDeleteList);
									sapNeedsToBeSynced =true;
								}
							}
						}
				 }
			}
		}
		
		System.out.println("in CommonUtilsPW.syncT308WithSap  sapNeedsToBeSynced= " + sapNeedsToBeSynced);
		return sapNeedsToBeSynced;
	}
	
	
	
	@SuppressWarnings("rawtypes")
	public String getOrderLotNo(String orderId) {
		
		String lotNumber = null;
		List sfwidLotList = commonDaoPW.selectSfwidLotDescList(orderId);
		Map sfwidLotDescMap = (Map)sfwidLotList.get(0);
		lotNumber = (String) sfwidLotDescMap.get("LOT_NO");
		return lotNumber;
	}

	
	@SuppressWarnings("rawtypes")
	public  String insertIntoPwustSfwidOrderDesc(String oobOrderNumber,
					                             String pwOrderNo,
												 String newOrderId,
			                                     String superNetPW, 
			                                     String subNetPW, 
			                                     String workScopePW, 
			                                     String customerPW, 
			                                     String salesOrder, 
			                                     String engineModel,
			                                     String orderType) {
		
		List  sfwidOrderDescList = commonDaoPW.selectsfwidOrderDescList(oobOrderNumber);
	    Map sfwidOrderDescMap = (Map)sfwidOrderDescList.get(0);
	    
		if (StringUtils.isBlank(pwOrderNo)){
			pwOrderNo = oobOrderNumber;
		}
		if (StringUtils.isBlank(newOrderId)){
			newOrderId= (String)sfwidOrderDescMap.get("ORDER_ID"); 
		}
		if (StringUtils.isBlank(orderType)){
			orderType = (String)sfwidOrderDescMap.get("ORDER_TYPE");
		}
		if (StringUtils.isBlank(salesOrder)){
			salesOrder = (String)sfwidOrderDescMap.get("UCF_ORDER_VCH11");
			if (StringUtils.isBlank(salesOrder)){
				salesOrder= "NA";
			}
		}
		if (StringUtils.isBlank(engineModel)){
			engineModel = (String)sfwidOrderDescMap.get("UCF_ORDER_VCH14");
			if (StringUtils.isBlank(engineModel)){
				engineModel= "NA";
			}
		}
	    
		commonDaoPW.insertIntoPwustSfwidOrderDesc(newOrderId, 
				                                  pwOrderNo, 
				                                  salesOrder, 
				                                  engineModel,  
				                                  workScopePW, 
				                                  superNetPW, 
				                                  subNetPW, 
				                                  customerPW,
				                                  orderType.toUpperCase());
		return  newOrderId;

	}

	
	
	@SuppressWarnings("deprecation")
	public void disableConnectionHistory(){
		UserContext userContext = ContextUtil.getUser().getContext();
		String connectionId = userContext.getConnectionId();
		historyControl.disableHistory(connectionId);
	}
	
	public void enableConnectionHistory(){
		historyControl.enableHistory();
	}
    
	
	// defect 980
	@SuppressWarnings("rawtypes")
	public String getOrderTypePW(String orderId){
		
		String pwOrderType = null;
		List list = commonInfo.selectPWOrderDataByOrderId(orderId);
		if (list!=null && !list.isEmpty() && list.size() == 1){
			Map pwOrderTypeMap = (Map) list.iterator().next();
			pwOrderType = (String)pwOrderTypeMap.get("PW_ORDER_TYPE");
			
			String orgOrderId = (String)pwOrderTypeMap.get("ORIG_ORDER_ID");
			List orgList = commonInfo.selectPWOrderDataByOrderId(orgOrderId);
			if (orgList!=null && !orgList.isEmpty() && orgList.size() == 1){
				Map orgMap = (Map) orgList.iterator().next();
				String orgOrderType = (String)orgMap.get("ORDER_TYPE");
				if (StringUtils.equalsIgnoreCase(orgOrderType, "REPAIR") && StringUtils.equalsIgnoreCase(pwOrderType, "SUPPLEMENTAL")){
					pwOrderType= pwOrderType + "-REPAIR";
				}
			}
		}
		return pwOrderType;
	}
	
	
	public static String writeChgHist(String chgNote, String chgField)
	{

		chgNote  = StringUtil.iif((chgNote==null),"",chgNote);
		chgField = StringUtil.iif((chgField==null),"",chgField);

		String newNote = "";
		String user = ContextUtil.getUsername();
		
		/*
		 * Defect 320 states change history should include time
		 * Updating Nancys code to reflect new requirement. 
		 * - S.Randall
		 */	
		Date now = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/y h:m:ss a");
		String today = dateFormatter.format(now);
		
		String userToday = user + " " + today +":";
		
		// SEPL_APP_02 Defect 931 Write no Change History when change history is null.
		if (chgNote.length() > 0)
		{  
			newNote = userToday + " " + chgNote + "\n";
		}
		
		if (newNote.length() > 0)
			chgField = chgField + " " + newNote;

		return chgField;
	}
	
	public boolean ValidatePassword(String UserID, String UserPassword) {
		boolean Validated = false;

		Md5ShaPasswordEncoder UserSecurity = new Md5ShaPasswordEncoder();
		
		String encryptedPass = commonInfo.selectUserPassword(UserID);
		return Validated;
	
	}
	
	/*
	 * @params sendToList - expecting List where "DATA_VALUE" is key.  See CommonDaoPW.getLookupValList
	 * @params subj - subject line for email note
	 * @params msg -  body of email
	 */
	public void sendEmailDistr(List sendToList, String subj, String msg)
	{

		ListIterator toListItr = sendToList.listIterator();
		if (toListItr.hasNext())
		{
			while (toListItr.hasNext())
			{
				Map row = (Map) toListItr.next();
				String to = (String) row.get("DATA_VALUE");
				mailService.sendEmail(to, subj, msg);
			}
		}
	}

	public void cscRaiseError(String messageId, String messageText, String flagInteractiveUser)		///2013-03-25
	{	///2013-03-25 SIQA_TR_T11 
		if ("Y".equalsIgnoreCase(flagInteractiveUser))	///2013-03-25 SIQA_TR_T11
		{ 
			message.raiseError(messageId, messageText); 
		}
		else
		{
			System.out.println("CommonUtilsPW.cscRaiseError: messageText="+messageText);
			//todo  sendmail?????????
			System.out.println("CommonUtilsPW.cscRaiseError: TODO: Must sendemail!!!!"); //TODO
			message.raiseError(messageId, messageText); 
		}
	} 

	public void cscShowMessage(String messageId, String messageText, String flagInteractiveUser)		///2013-03-25
	{	///2013-03-25 SIQA_TR_T11
		if ("Y".equalsIgnoreCase(flagInteractiveUser))	///2013-03-25 SIQA_TR_T11
		{
			message.showMessage(messageId, messageText);
		}
	} 
	
	public void cscCloseAllTools(String flagInteractiveUser)		///2013-03-25
	{	///2013-03-25 SIQA_TR_T11
		if ("Y".equalsIgnoreCase(flagInteractiveUser))	///2013-03-25 SIQA_TR_T11
		{
			event.closeAllTools();
		}
	} 

	public void cscRefreshTool(String flagInteractiveUser)		///2013-03-25
	{	///2013-03-25 SIQA_TR_T11
		if ("Y".equalsIgnoreCase(flagInteractiveUser))	///2013-03-25 SIQA_TR_T11
		{
			event.refreshTool();
		}
	} 
	
	public void cscShowRedMessage(String messageText, String flagInteractiveUser)
	{
		if ("Y".equalsIgnoreCase(flagInteractiveUser))	
		{
			StringBuffer params = new StringBuffer(4000);
			params.append("MESSAGE_TEXT=" + messageText);
	 
			event.showUdv("PWUE_D5C91266D20C0026E043AC1346870026",
	 		 	  SoluminaConstants.INSERT,
	 		 	  "Message",
	 		 	  null,
	 		 	  params.toString());
		}
	} 
	
	//SMRO_MD_M206
	public void sendErrorEmail(String userId,
			                   String errorMessage,
			                   String distributionList,
			                   String subject)
	{
		List sendAddrs = null;
		String messageText = null;

		sendAddrs = commonInfo.getLookupValList(distributionList);

		if (sendAddrs.size() > 0) 
		{
			messageText =				"USER ID: " + userId + "\n";
			messageText = messageText + "ERROR  : " + errorMessage;

			sendEmailDistr(sendAddrs, subject, messageText);
		}
		else
		{
			messageText = "No email addresses found in pwue_general_lookup table for "+distributionList;
			String errorId = commonInfo.selectNextErrorSequence();
			commonInfo.insertErrorLog(errorId, null, "SEND_EMAIL", null, "MFI_20", errorMessage, "Error Sending Email", messageText, userId, ContextUtil.getUsername());	
		}

	}
}
