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
 *  File:    OperationPW.java
 * 
 *  Created: 2018-01-09
 * 
 *  Author:  Raeann Thorpe
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2018-01-09		R. Thorpe		Initial Release SMRO_WOE_202 - Buyoffs
 *  2018-03-22		T. Phan		    SMRO_TR_T203 - Labor Interface
 *  2018-04-14		D. Miron		SMRO_WOE_202 Defect 572 - Revised getBadgeId
 *  2018-04-26      D. Miron        SMRO_TR_T203 Defect 733 - Set sapOrderNo to empty string
 *  2018-06-17      Sam Niu         defect 805, Alteration Completion check for copied operations  
 *  2018-07-11      Fred Ettefagh   defect 889/805, fixed code to set SupNet, SubNet when copying opers from order or plan
 *  2018-10-02		Bob Preston		SoluminaOOB, defect 970 -- Call getNowClientTimeStamp() rather than getNowTimeStamp().
 *  2018-10-01      Fred Ettefagh   Defect 980 Cannot Create Suborder due to supNet subNet and conf Numbers
 *  2018-10-11		Bob Preston		SoluminaOOB, defect 970 -- Call getNowClientTimeStamp(serverTZ, clientTZ) rather than getNowTimeStamp().
 *  2018-10-23		Bob Preston		SoluminaOOB, defect 970 -- Added call to getLocationId2().
 *  2018-11-05      D. Miron        SMRO_WOE_202 Defect 1036 - Revised getBadgeId for R2 to validate badge id using reqular expressions.
 *  2018-12-17      Fred Ettefagh   Defect 1044, fix repair order create
 *  2019-09-04		Bahram J.       SMRO_TR_T303, defect 1366, Fixed code for Labor Rings interface
 *  2019-09-24      Fred Ettefagh   SMRO_TR_T303, defect 1382, labor rings method, changed method insertT203Labor to pass in oper_key instead of oper_no
 *  2020-07-02      John DeNinno	defect 1685 Missing order number and parent of suplemental added code for different ways order type stored in database
 *                                  added additional code for operNo for supplemental to be passed
 *  2020-09-18		S. Edgerly		defect 1768 - Repair Labor Message update for Operation Final Confirmation.
 *  2020-12-04		S. Edgerly		SMRO_WOE_308 - Defect 1819 - Supplemental Orders
 *  2021-10-22		B.Polak			defect2005 - getBadgeId- remove check for matching userId
*/
package com.pw.solumina.sfwid.application;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.jna.Library;
import com.sun.jna.Native;

import com.ibaset.common.Reference;
import com.pw.solumina.sfwid.dao.UserDaoPW;
import com.pw.solumina.sfwid.dao.WorkOrderDaoPW;
import com.pw.solumina.sfwid.dao.impl.OrderOperConfirmationDao;
import com.pw.solumina.sfwid.impl.OrderOperConfirmationCheck;
import com.singularsys.jep.functions.If;
import com.pw.solumina.sfwid.dao.OperationDaoPW;
import com.pw.solumina.sfwid.dao.OrderAssembleTextDaoPW;
import com.pw.solumina.sfwid.dao.OrderDaoPW;
import com.ibaset.solumina.sfwid.application.impl.OrderImpl;

import static com.ibaset.common.FrameworkConstants.FOUNDATION;
import static com.ibaset.common.FrameworkConstants.OPER_NUM_MASK;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.ListIterator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.util.SoluminaUtils;
import com.ibaset.solumina.sfwid.application.IOperation;
import com.ibaset.solumina.sfwid.application.ISerial;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IDateUtils;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.dao.IUserActivityDao;
import com.ibaset.solumina.sffnd.dao.IUserDao;
import com.ibaset.solumina.sfcore.application.IUser;
import com.ibaset.solumina.sfwid.application.IHold;
import com.ibaset.solumina.sfwid.dao.IBuyoffDao;
import com.ibaset.solumina.sfwid.dao.IOrderDao;
import com.ibaset.solumina.sfwid.dao.IOperationDao;
import com.ibaset.solumina.sfwid.dao.ISerialDao;
import com.ibaset.solumina.sfwid.dao.IStepDao;
import com.pw.common.dao.CommonDaoPW;
import com.pw.common.utils.CommonUtilsPW;
import com.pw.common.utils.DateUtils;
import com.pw.solumina.sfwid.application.BuyoffPW;
import com.pw.solumina.sfwid.dao.BuyoffDaoPW;
import com.pw.solumina.sfpl.dao.PlanDaoPW;

@SuppressWarnings("unused")
public class OperationPW 
{
	//@Reference private OperationPW operationPW;
	@Reference private com.pw.solumina.sfwid.dao.UserDaoPW userDaoPW;
	@Reference private OperationDaoPW operationDaoPW;
	@Reference private IOrderDao orderDao = null;
	@Reference private OrderDaoPW orderDaoPW; //Right type?
	@Reference private DateUtils dateUtils =null;
	//Defect 805
	@Reference private IMessage message = null;
	@Reference private IOperation operation = null;
	@Reference private WorkOrderDaoPW workOrderDaoPW = null;
	@Reference private ISerialDao serialDao = null;
	//End Defect 805
	
	@Reference private  OrderOperConfirmationCheck orderOperConfirmationCheck;
	@Reference private  OrderOperConfirmationDao orderOperConfirmationDao;
	@Reference private CommonUtilsPW commonUtilsPW;
	
	@Reference private CommonDaoPW commonDaoPW = null;  // Defect 970.
	
	@Reference private BuyoffDaoPW buyoffDaoPW = null;
	
	public interface Reader extends Library {
		/*
		Helper of  usbconnect, Will restrict
		the device search to a particular category
		for example.
		Device Type could be one of following
		0 : To search only USB devices
		1 :  To Search Serial RS-232 only.
		-1 : Both USB and serial devices.
		This function will return true in case of
		success false otherwise.
		*/
		public short SetDevTypeSrch(short iSrchType);
		
		/*
		return true in case success, false otherwise.
		Will open connection to all rfidea's readers/devices
		in onces.
		Individual device can be accessed by using setActiveDev first
		then call other functions.
		*/
		public int usbConnect();
		
		/*
		Will return true in case success, false otherwise.
		As per API documentation USBDisconnect always returns
		true.
		It will close the handle to all rfidea's devices, should be
		called during clean up.
		*/
		public int USBDisconnect();
		
		//Will return the LUID of active device/Reader.
		public int GetLUID();
		
		//Will Return the total number of connected rfidea's readers.
		public short GetDevCnt();
		
		//Will return true if able to set active device to given device, false otherwise
		public short SetActDev(short iNdx);
		
		//Will return the part number of active device and none in case of failure.
		public String getPartNumberString();
		
		/*
		Will return None in case of error other wise will return
		the VID PID and product name in following format
		<VID>:<PID> <product name>
		*/
		public String GetVidPidVendorName();

		/*
		Will return a tuple consisting number of bits read and actual data.
		Minimum 8 byte of data will be returned.
		*/
		public short GetActiveID32(byte[] pBuf, short wBufMaxSz);
		
		/*
		Will return the SDK version in following format
		<Major>.<Minor>.<Dev>.
		In case of error will return None
		*/
		public short GetLibVersion(int[] piVerMaj,int[] piVerMin,int[] piVerDev);
    }
	
	// Attempt connection/placement 
	public Map readRFIDId() {
		String swipedBadgeId;
		Reader lib;
		String lib_home = "";
		lib = Native.loadLibrary("pcProxAPI.dll",Reader.class);
		short PRXDEVTYP_USB = 0;
		Map error = new HashMap();
		lib.SetDevTypeSrch(PRXDEVTYP_USB);
		if(lib.usbConnect() == 0){
			System.out.println("\nReader not connected");
			throw new RuntimeException("No reader connected");
			
		}
		try {
			Thread.sleep(250);
		} 
		catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		
		short wBufMaxSz = 32;
		byte buf[] = new byte [wBufMaxSz];
		short bits = lib.GetActiveID32(buf, wBufMaxSz);
		
		if(bits == 0){
			lib.USBDisconnect();
			throw new RuntimeException("No ID found");
		}
		else{
			int bytes_to_read = (bits + 7) / 8 ;
			if(bytes_to_read < 8){
				bytes_to_read = 8;
			}
			error.put("CLOCK_NO", buf);
			lib.USBDisconnect();
			return error;
		}
	}
	
	// Defect 572
	// Defect 1036
	// Defect 2005
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getBadgeId(String swipedBadgeId)
	 {
		 String returnBadgeId = null;
		 Map returnMap = new HashMap();

		 Pattern p1 = Pattern.compile("^\u00df{3}\u0025");
		 Pattern p2 = Pattern.compile("^\u00df{3}");	
		 
		 //2005 - changed to false so that we check badge id, 
		 // not the userid 
		 boolean firstPass = false;
		 
		 int ibeg = 0;
		 int iend = swipedBadgeId.length();
		     
		 try
		 {
			 if (p1.matcher(swipedBadgeId).find())  // Check for preamble w/%
			 {
				 ibeg = 4;
				 if(iend == 12)
				 {
					 iend = iend - 1;
				 }
			 }
			 else if(p2.matcher(swipedBadgeId).find()) // Check for preamble wo/%
			 {
				 ibeg = 3;
				 if(iend == 11)
				 {
					 iend = iend - 1;
				 }
			 }
			 else
			 {
				 returnMap.put("CLOCK_NO", null);
				 return returnMap;
			 }
			 
			 returnBadgeId = swipedBadgeId.substring(ibeg, iend);
			 returnMap = userDaoPW.selectUseridfromBadgeSwipe(returnBadgeId, firstPass);	
			 String userid = (String) ((Map)returnMap).get("CLOCK_NO");
			 
			 //Defect 2005 - remove this extra check 
//			 if(userid == null)
//			 {
//			 	 firstPass = false;
//			 	 returnBadgeId = swipedBadgeId.substring(ibeg);
//			 	 returnMap = userDaoPW.selectUseridfromBadgeSwipe(returnBadgeId, firstPass);	
//			 }
			 
		 }
		 catch (Exception e)
		 {
			 returnMap.put("CLOCK_NO", null);
			 return returnMap;
		 }

		 return returnMap;
	 }
	 
	//Begin SMRO_TR_T303 - OH Labor Interface
	 public void insertT203Labor(String orderId, 
			 					 Number operKey,
			                     String clockNoList,
			                     String completionType,
			                     String quanityCompleted,
			                     String percentComplete,
			                     String delimiter,
			                     String buyoffType,
			                     String workType /*defect 1768*/) {

		 String clockNo;
		 String confirmationNo = null;
		 String msgText;
		 String lotId = null;
		 String comments = null;
		 StringTokenizer clockNoTokens;
		 String workLocForMsgText = "";
		 String laborFlag = null;
         //String sapOrderNo = "";  // Defect 733
		 String sapOrderNo = commonDaoPW.getOrderNoOrderId(orderId); //defect 1685
		 String pwOrderType = commonUtilsPW.getOrderTypePW(orderId);
		 String salesOrder = null;
	     
		 //defect 1768
		 if("X".equals(workType)){
			 if(pwOrderType.toUpperCase().equals("OVERHAUL") || 
			   (pwOrderType.toUpperCase().equals("REPAIR") && !buyoffDaoPW.isFinalBuyoffInOper(orderId, operKey, "Y"))){
				 return;
			 }
		 }
	     //end defect 1768
		 
         String sapPrefixNo = null;

		 Map orderMap = (Map) orderDaoPW.selectOrderInfo(orderId); 

	 	// if no work loc, default to empty string
		 try{
			 workLocForMsgText =StringUtils.defaultIfEmpty((String) orderMap.get("ASGND_WORK_LOC"), ""); 
		 }
		 catch(Exception e)
		 {}

		 //boolean isExempt = operationDaoPW.isExemptLabor(orderId); // do not report labor for teardowns, audit orders, or supplemental orders
		 
		 String orderNo      = StringUtils.defaultIfEmpty(operationDaoPW.getOrderNo(orderId),"");
		 String salesOrderNo = StringUtils.defaultIfEmpty((String) orderMap.get("SALES_ORDER_NO"),"");
		 String serialId     = StringUtils.defaultIfEmpty((String) orderMap.get("SERIAL_ID"),"");
		 String partNo       = StringUtils.defaultIfEmpty((String) orderMap.get("PART_NO"),"");
		 String sapPrefix    = StringUtils.defaultIfEmpty((String) orderMap.get("SAP_PREFIX"),"");
		 String workLoc      = StringUtils.defaultIfEmpty((String) orderMap.get("ASGND_WORK_LOC"),"");
		 String action       = StringUtils.defaultIfEmpty(completionType.toUpperCase(),"");
         String operNo = "";
		 Map sfwidOperDescMap = operationDaoPW.getSfwidOperDescMap(orderId, operKey, -1);
         confirmationNo = (String) sfwidOperDescMap.get("UCF_ORDER_OPER_VCH1");
         
	      //defect 1685 if it is a supplemental order get the parent work order number and oper no
	      if ("SUPPLEMENTAL".equalsIgnoreCase(pwOrderType.toUpperCase()) || ("SUPPLEMENTAL-REPAIR".equalsIgnoreCase(pwOrderType.toUpperCase()))) {
	    		sapOrderNo = commonDaoPW.getSalesOrderNoFromOrderId(commonDaoPW.getParentOrderId(orderId)); //defect 1685
	    		operNo = commonDaoPW.getSalesOrderOpFromOrderNo(commonDaoPW.getSalesOrderNoFromOrderId(orderId)); //defect 1685
	      }
	      else {
              operNo = (String) sfwidOperDescMap.get("OPER_NO");
	      }
         
		 confirmationNo = StringUtils.defaultIfEmpty(confirmationNo,"");   // Defect 733
		 	//defect 1768
		 if(!StringUtils.equals(workType, "X")){
			 if (buyoffType.equals("MFG")){ 
			 	 workType = "P";
			 } else if (buyoffType.equals("INSP")){
		     	 workType = "I";
			 } else{ 
		    	 workType = "?";
			 }
		 }
		 String serverTimeZone = commonDaoPW.selectGlobalConfigValue("SERVER_TZ");
		 if ("".equals(serverTimeZone) || "noParamSet".equals(serverTimeZone)) {
			 serverTimeZone = null;
		 }
			 
		 clockNoTokens = new StringTokenizer(clockNoList, delimiter);

		 while (clockNoTokens.hasMoreElements()){
			 
				 clockNo = clockNoTokens.nextElement().toString();
				 laborFlag = userDaoPW.getLaborFlag(clockNo);

				 if(!"Y".equals(laborFlag)){
					 if(!StringUtils.equals(workType, "X")){ //defect 1768
						 if (StringUtils.equals(workType, "?")) {
							 // check if QT or Inspector and set work type
							 String PrivReturnCode = operationDaoPW.CheckPriv(clockNo, "BUYOFF_INSP");		
							 if (PrivReturnCode.equals("N")){
								 workType = "V";
							 }else{ 
								 workType = "I";
							 }
						 }
					 }
					 // Begin defect 970
					 String userLocationId = userDaoPW.getLocationId(clockNo);
					 if (StringUtils.isEmpty(userLocationId)) {
						 userLocationId = userDaoPW.getLocationId2(clockNo);
					 }
					 
					 String userTimeZone = orderDaoPW.getTimeZone(userLocationId);
					 if (StringUtils.isEmpty(userTimeZone)) {
						 throw new RuntimeException("User time zone not found");
					 }
					 String timestamp = dateUtils.getNowClientTimeStamp(serverTimeZone, userTimeZone);
					 
					 msgText = sapOrderNo+ "," +
					  		   confirmationNo+ "," +
							   operNo+ "," +
							   timestamp+ "," +
							   clockNo+ "," +
							   partNo+ "," +
							   salesOrderNo+ "," +
							   sapPrefix+ "," +
							   workType+ "," +
							   quanityCompleted+ "," +
							   percentComplete+ "," +
							   "05"+ "," +
							   "001"+ "," +
							   workLocForMsgText;

					 orderDaoPW.insertT203Labor(orderId, operKey, serialId, lotId, action, msgText, workLoc, comments);
				 }
			 }
	 }


	 //Defect 889/805//980
	 public void copyOperationPre(String sourceOrderNumber,
								  String sourceOperationNumber,
								  String targetOrderNumber,
								  String targetOperationNumber,
								  String copyComponentParts,
								  String toOperationTitle,
								  String from,
								  Number sequenceNumber,
								  String orderId,
								  String ucfOrderOperationVch2,
								  String ucfOrderOperationVch3){
		 
		 
		String pwOrderType = commonUtilsPW.getOrderTypePW(orderId);
		if (StringUtils.equalsIgnoreCase("OVERHAUL", pwOrderType) ||
			StringUtils.equalsIgnoreCase("SUPPLEMENTAL", pwOrderType) ||
			StringUtils.equalsIgnoreCase("SUB ORDER", pwOrderType)){
				 try{
					 ContextUtil.getUser().getContext().set("PWUST_SKIP_ORDER_OPER_CONF_UPD","TRUE");
					 operation.copyOperationPre(sourceOrderNumber, sourceOperationNumber, targetOrderNumber, targetOperationNumber, copyComponentParts, toOperationTitle, from, sequenceNumber);
	
					 String confNumber = null;
					 Map pwustSfwidOrderDescMap = getPwOrderMap(orderId);
					 String salesOrder = (String) pwustSfwidOrderDescMap.get("SALES_ORDER");
					 String orderStatus = (String) pwustSfwidOrderDescMap.get("ORDER_STATUS");
					
					 if ("OVERHAUL".equalsIgnoreCase(pwOrderType) || 
						"SUPPLEMENTAL".equalsIgnoreCase(pwOrderType) ||
						"SUB ORDER".equalsIgnoreCase(pwOrderType)){
	
							orderOperConfirmationCheck.updateConfirmationUpdate(orderId,salesOrder,targetOperationNumber,ucfOrderOperationVch2,ucfOrderOperationVch3);
							
					 }   	
				 }finally {
					 ContextUtil.getUser().getContext().set("PWUST_SKIP_ORDER_OPER_CONF_UPD","");			
				 }
		}else{
			operation.copyOperationPre(sourceOrderNumber, sourceOperationNumber, targetOrderNumber, targetOperationNumber, copyComponentParts, toOperationTitle, from, sequenceNumber);
		}
		//defect 1819
    	List list = operationDaoPW.getOrderInfo(targetOrderNumber);
    	ListIterator listIterator = list.listIterator();
    	
    	String targetOrderID = "";
    	while(listIterator.hasNext()){
    		Map map = (Map) listIterator.next();
    		targetOrderID= (String) map.get("ORDER_ID");
    	}
		operationDaoPW.clearBuyoffUserAndTimestampFromOrder(targetOrderID, targetOperationNumber);
		//end defect 1819
	 }



	public Map getPwOrderMap(String orderId) {
		
		Map pwustSfwidOrderDescMap = workOrderDaoPW.selectPwustSfwidOrderDesc(orderId);
		if (null == pwustSfwidOrderDescMap) {
			message.raiseError("MFI_20", "Bad Order. Missing data in PWUST_SFWID_ORDER_DESC table");
		}
		return pwustSfwidOrderDescMap;
	}


	//Defect 889/805/980
	public void copyOperationFromPlanPre(String sourcePlanId,
										 Number sourcePlanVersion,
										 Number sourcePlanRevision,
										 Number sourcePlanAlteration,
										 String sourceOperationNumber,
										 String targetOrderId,
										 String targetOperationNumber,
										 String copyComponentParts,
										 String toOperationTitle,
										 String copyToOfd,
										 Number toOperationKey, 
										 Number sequenceNumber,
										 String stdOperCopyType,
										 String callFrom,
										 String ucfOrderOperationVch2,
										 String ucfOrderOperationVch3){


		operation.copyOperationFromPlanPre(sourcePlanId, sourcePlanVersion, sourcePlanRevision, sourcePlanAlteration,
										   sourceOperationNumber, targetOrderId, targetOperationNumber, copyComponentParts, toOperationTitle,
										   copyToOfd, toOperationKey, sequenceNumber, stdOperCopyType, callFrom);

		String confNumber = null;
		String pwOrderType = (String)commonUtilsPW.getOrderTypePW(targetOrderId);

		if ("OVERHAUL".equalsIgnoreCase(pwOrderType) || 
			"SUPPLEMENTAL".equalsIgnoreCase(pwOrderType) ||
			"SUB ORDER".equalsIgnoreCase(pwOrderType)){
			    Map pwustSfwidOrderDescMap = getPwOrderMap(targetOrderId);
			    String salesOrder = (String) pwustSfwidOrderDescMap.get("SALES_ORDER");
				orderOperConfirmationCheck.updateConfirmationUpdate(targetOrderId,salesOrder,targetOperationNumber,ucfOrderOperationVch2,ucfOrderOperationVch3);
		}    		    
	}
}
