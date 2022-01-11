/*
 * This unpublished work, first created in 2017 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2017. 2020  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    OrderOperConfirmationCheck.java
 * 
 *  Created: 2018-09-24
 * 
 *  Author:  XCS4331
 * 
 *  Revision History
 * 
 *  Date      	Who                	Description
 *  ----------	---------------	---------------	---------------------------------------------------
 * 2018-09-24 	XCS4331		    Initial Release XXXXXXX
 * 2018-10-01    Fred Ettefagh   Defect 980 Cannot Create Suborder due to supNet subNet and conf Numbers
 * 2018-10-04    Fred Ettefagh   Defect 980 added error msg to method updateConfirmationUpdateViaIM when no data is found in LO table and conf number is null
 * 2018-10-26    Fred Ettefagh   Defect 969 when creating a Supplemental Order, update oper 0010 conf/sup/sub data from user selected oper no 
 *   2020-07-29   J DeNinno      Defect 1041 - check PWUST_LONOTE_BYPASS priv before doing the check
 */

package com.pw.solumina.sfwid.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.solumina.IValidator;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sfcore.application.IUser;
import com.pw.solumina.sfwid.dao.impl.OrderOperConfirmationDao;


public class OrderOperConfirmationCheck {

	// common 

	@Reference 	private IMessage message = null;
	@Reference private IUser user = null;
	@Reference OrderOperConfirmationDao orderOperConfirmationDao;
	@Reference private IValidator validator = null;

	public String removeTitle(String stringValue) {

		String returnValue = null;

		if (StringUtils.isNotBlank(stringValue)){
			if (StringUtils.contains(stringValue, ",")){
				// 0120  , Front Bearing (1-2-3) Compartment
				String[] valueSplit = StringUtils.split(stringValue, ",");
				if (valueSplit!=null && valueSplit.length>=1){
					returnValue= valueSplit[0].trim();
				}
			}else{
				returnValue = stringValue.trim();
			}
		}
		return returnValue;
	}

	// Defect 969
	public void updateConfirmData(String fromOrderId, String fromOperNo, String toOrderId, String toOperNo ){

		Map parentOrderMap = orderOperConfirmationDao.selectOrderOperDescMap(fromOrderId, fromOperNo);
		String confirmNo = (String) parentOrderMap.get("UCF_ORDER_OPER_VCH1");
		String superNet = (String) parentOrderMap.get("UCF_ORDER_OPER_VCH2");
		String subNet = (String) parentOrderMap.get("UCF_ORDER_OPER_VCH3");


		Map toOrderMap = orderOperConfirmationDao.selectOrderOperDescMap(toOrderId, toOperNo);
		Number toOperKey = (Number) toOrderMap.get("OPER_KEY");
		orderOperConfirmationDao.updateOperConfNumberByOperKey(toOrderId, toOperKey, -1, confirmNo, superNet, subNet);
	}



	// defect 980
	public void updateConfirmationUpdateViaIM(String orderId,String salesOrder, String OperationNumber,String superNet,String subNet){


		validator.checkUserPrivilege("PW_OPER_CONF_UPDATE");

		String confNumber = null;

		superNet = removeTitle(superNet);
		subNet = removeTitle(subNet);

		if (StringUtils.isNotBlank(superNet) && StringUtils.isNotBlank(subNet)){

			Map loMap = orderOperConfirmationDao.selectPwustSalesOrder(salesOrder, superNet, subNet);
			if (loMap==null){
				message.raiseError("MFI_20"," Oper =" + OperationNumber +
						" Sales Order = " + salesOrder+
						" superNet = "+ superNet +
						" subNet = "+ subNet + " Missing from Sales Order Table");
			}else{
				confNumber = (String) loMap.get("CONFIRM_NO");
				if (StringUtils.isBlank(confNumber)){
					message.raiseError("MFI_20"," Oper =" + OperationNumber +
							" Sales Order = " + salesOrder+
							" superNet = "+ superNet +
							" subNet = "+ subNet + " CONFIRM_NO is null " );		    					
				}else{
					orderOperConfirmationDao.updateOperConfData(orderId,OperationNumber,confNumber,superNet, subNet);
				}
			}
		}
	}

	// defect 889/805
	public void updateConfirmationUpdate(String orderId, String salesOrder, String OperationNumber,String superNet,String subNet){
		if (!user.hasPrivilege("PWUST_LONOTE_BYPASS")) {   //defect 1041
			String confNumber = null;

			if (StringUtils.isBlank(superNet)){
				message.raiseError("MFI_20", "Sup Net cannot be blank");
			}
			if (StringUtils.isBlank(subNet)){
				message.raiseError("MFI_20", "Sub Net cannot be blank");
			}

			superNet = removeTitle(superNet);
			subNet = removeTitle(subNet);

			if (StringUtils.isNotBlank(superNet) && StringUtils.isNotBlank(subNet)){
				Map loMap = orderOperConfirmationDao.selectPwustSalesOrder(salesOrder, superNet, subNet);
				if (loMap==null){
					message.raiseError("MFI_20"," Oper =" + OperationNumber +
							" Sales Order = " + salesOrder+
							" superNet = "+ superNet +
							" subNet = "+ subNet + " Missing from Sales Order Table");
				}else{
					confNumber = (String) loMap.get("CONFIRM_NO");

					if (StringUtils.isBlank(confNumber)){
						message.raiseError("MFI_20"," Oper =" + OperationNumber +
								" Sales Order = " + salesOrder+
								" superNet = "+ superNet +
								" subNet = "+ subNet + " CONFIRM_NO is null " );		    					
					}
				}			
				orderOperConfirmationDao.updateOperConfData(orderId,OperationNumber,confNumber,superNet, subNet);
			}
		}
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String checkOrderOperationConfirmationData(String orderId){

		String operNoList = null;

		List<Map> operList = orderOperConfirmationDao.selectOrderOperConfiramatinNo(orderId);
		if (operList!=null && !operList.isEmpty()){

			for (Map operMap : operList) {
				String operNo = (String)operMap.get("OPER_NO");
				if (operNoList==null){
					operNoList = "Oper no = " + operNo;
				}else{
					operNoList = operNoList + System.lineSeparator()  + "Oper no = " + operNo;
				}
			}
		}
		return operNoList;
	}



	public void checkOrderData(String salesOrder,String engineModel,String engineType,String workLoc){

		if (null==salesOrder){
			message.raiseError("MFI_20","Sales Order No can not be null");
		}
		if (null==engineModel){
			message.raiseError("MFI_20","Engine Model can not be null");
		}
		if (null==engineType){
			message.raiseError("MFI_20","Engine Type can not be null");
		}    	
		if (null==workLoc){
			message.raiseError("MFI_20","Work Loc can not be null");
		}   

		List list = orderOperConfirmationDao.selectPwustSalesOrder(salesOrder, engineType, engineModel, workLoc);
		if (list == null && !list.isEmpty()){
			message.raiseError("MFI_20","Sales Order, Engine Type, Engine Model, Work Loc, not found Sales Order Table ");
		}
	}

	public void updateConfNumberForSubOrders(String orderId, String salesOrder, String engineType, String engineModel, String workLoc) {

	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public void updateConfNumberForOverHaulOrders(String orderId, String salesOrder, String engineType, String engineModel, String workLoc, String planId,Number planUpdtNo) {

		checkOrderData( salesOrder, engineModel, engineType, workLoc);

		List<Map> operList =orderOperConfirmationDao.selectOrderOperWithNoConfNumber(orderId);
		if (null!=operList && !operList.isEmpty()){

			for (Map operMap : operList) {

				String operNo = (String) operMap.get("OPER_NO");
				Number operKey = (Number) operMap.get("OPER_KEY");

				Map networkDataMap = orderOperConfirmationDao.getPlanOperNetworkActivityRow(planId,planUpdtNo,operKey,engineType);
				if (networkDataMap!=null){
					String	superNet = (String) networkDataMap.get("SUPERIOR_NETWORK_ACT");
					String	subNet = (String) networkDataMap.get("SUB_NETWORK_ACT");
					if (StringUtils.isNotBlank(superNet) && StringUtils.isNotBlank(subNet)){
						updateOrderOperConfDataNoError(orderId,operNo,operKey, salesOrder, superNet, subNet);
					}
				}
			}
		}
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  void updateConfNumberForSupplementalOrder(String orderId, String salesOrder, String engineType, String engineModel, String workLoc, String planId, Number planUpdtNo) {

		checkOrderData( salesOrder, engineModel, engineType, workLoc);

		List<Map> operList =orderOperConfirmationDao.selectOrderOperWithNoConfNumber(orderId);
		if (null!=operList && !operList.isEmpty()){

			for (Map operMap : operList) {
				String operNo = (String) operMap.get("OPER_NO");
				Number operKey = (Number) operMap.get("OPER_KEY");

				String superNet = (String) operMap.get("UCF_ORDER_OPER_VCH2");
				String subNet   = (String) operMap.get("UCF_ORDER_OPER_VCH3");

				if (StringUtils.isNotBlank(superNet) && StringUtils.isNotBlank(subNet)){

					String superNetString = null;
					String subNetString = null;

					if (StringUtils.contains(superNet, ",")){
						// 0120  , Front Bearing (1-2-3) Compartment
						String[] superiorNetSplit = StringUtils.split(superNet, ",");
						if (superiorNetSplit!=null && superiorNetSplit.length>=1){
							superNetString= superiorNetSplit[0].trim();
						}
					}else{
						superNetString = superNet.trim();
					}

					if (StringUtils.contains(subNet, ",")){
						// 0120  , Front Bearing (1-2-3) Compartment
						String[] subNetSplit = StringUtils.split(subNet, ",");
						if (subNetSplit!=null && subNetSplit.length>=1){
							subNetString= subNetSplit[0].trim();
						}

					}else{
						subNetString = subNet.trim();
					}

					if (StringUtils.isNotBlank(superNetString) && StringUtils.isNotBlank(subNetString)){
						updateOrderOperConfDataNoError(orderId, operNo, operKey, salesOrder, superNetString, subNetString);
					}
				}				
			}
		}		
	}

	public void updateOrderOperConfFromPendingOrderOperData(String oldOrderId,Number oldOperationKey, String newOrderId,String newOperationNumber,Number newOperationKey, Map oldPwustOrdreDescMap){

		String oldPwOrderType = (String)oldPwustOrdreDescMap.get("PW_ORDER_TYPE");
		if (StringUtils.equalsIgnoreCase(oldPwOrderType, "OVERHAUL")){

			String planId= (String)oldPwustOrdreDescMap.get("PLAN_ID");
			Number planUpdtNo= (Number)oldPwustOrdreDescMap.get("PLAN_UPDT_NO");
			updateOrderOperConfFromPlanOperData(newOrderId, 
					planId, 
					null,//Number planVer, 
					null, //Number planRev,  
					null, //Number planAlt, 
					planUpdtNo,
					oldOperationKey, 
					newOperationNumber, 
					newOperationKey);

		}else if (StringUtils.equalsIgnoreCase(oldPwOrderType, "SUB ORDER")){
			String oldOrgOrderId = (String) oldPwustOrdreDescMap.get("ORIG_ORDER_ID");

			Map orgOrderMap = orderOperConfirmationDao.selectPwustSfwidOrderDesc(oldOrgOrderId);
			String planId= (String)orgOrderMap.get("PLAN_ID");
			Number planUpdtNo= (Number)orgOrderMap.get("PLAN_UPDT_NO");

			updateOrderOperConfFromPlanOperData(newOrderId, 
					planId, 
					null, //Number planVer, 
					null, //Number planRev,  
					null, //Number planAlt, 
					planUpdtNo,
					oldOperationKey, 
					newOperationNumber, 
					newOperationKey);			

		}

	}


	public void updateOrderOperConfFromPlanOperData(String toOrderId, String planId, Number planVer, Number planRev,  Number planAlt, Number planUpdtNo, Number fromOperationKey, String toOperationNumber, Number toOperationKey){

		if (planUpdtNo == null){
			Map sfplPlanRevMap =orderOperConfirmationDao.selectPlanUpdtNo(planId, planVer, planRev, planAlt);
			planUpdtNo = (Number)sfplPlanRevMap.get("PLAN_UPDT_NO");
		}

		Map pwustSfwidOrderDescMap =orderOperConfirmationDao.selectPwustSfwidOrderDesc(toOrderId);
		//String engineType = (String) pwustSfwidOrderDescMap.get("PROGRAM");
		String salesOrder = (String) pwustSfwidOrderDescMap.get("SALES_ORDER");

		Map networkDataMap = orderOperConfirmationDao.getPlanOperNetworkActivityRow(planId,
				planUpdtNo,
				fromOperationKey,
				null);//engineType
		if (networkDataMap == null){

			message.raiseError("MFI_20","Error getting Sup/Sub net data " + System.lineSeparator() + System.lineSeparator() +
					"fromPlanId = "+ planId + System.lineSeparator() +
					"planUpdtNo = " + planUpdtNo + System.lineSeparator() +
					"fromOperationKey ="  + fromOperationKey + System.lineSeparator() +
					"toOperationNumber = "+ toOperationNumber);
		}
		String supNetAct = (String) networkDataMap.get("SUPERIOR_NETWORK_ACT");
		String subNetAct = (String) networkDataMap.get("SUB_NETWORK_ACT");
		if (StringUtils.isNotBlank(supNetAct) && (StringUtils.isNotBlank(subNetAct))){
			updateOrderOperConfDataWithError(toOrderId, toOperationNumber, toOperationKey, salesOrder, supNetAct, subNetAct);
		}else{
			message.raiseError("MFI_20","Error Sup/Sub net data are blank " + System.lineSeparator() +  System.lineSeparator() +
					"fromPlanId = "+ planId + System.lineSeparator() +
					"planUpdtNo = " + planUpdtNo + System.lineSeparator() +
					"fromOperationKey ="  + fromOperationKey + System.lineSeparator() +
					"toOperationNumber = "+ toOperationNumber);

		}


	}



	public void updateOrderOperConfDataNoError(String orderId, String operNo, Number operKey, String salesOrder, String superNet,String subNet) {

		Map loMap = orderOperConfirmationDao.selectPwustSalesOrder(salesOrder, superNet, subNet);
		if (null != loMap){
			String confirmNo = (String) loMap.get("CONFIRM_NO");
			if (StringUtils.isNotBlank(confirmNo)){
				orderOperConfirmationDao.updateOperConfNumberByOperKey(orderId, operKey, -1, confirmNo,superNet,subNet);	
			}
		}
	}


	public void updateOrderOperConfDataWithError(String orderId, String operNo, Number operKey, String salesOrder, String superNet,String subNet) {
		if (!user.hasPrivilege("PWUST_LONOTE_BYPASS")) {   //defect 1041
			Map loMap = orderOperConfirmationDao.selectPwustSalesOrder(salesOrder, superNet, subNet);
			if (null == loMap){
				message.raiseError("MFI_20","Error Sales Order Data missing " + System.lineSeparator() +  System.lineSeparator() +
						"salesOrder = "+ salesOrder + System.lineSeparator() +
						"operNo = " + operNo + System.lineSeparator() +
						"superNet ="  + superNet + System.lineSeparator() +
						"subNet = " + subNet + System.lineSeparator());
			}
			if (null != loMap){
				String confirmNo = (String) loMap.get("CONFIRM_NO");
				if (StringUtils.isNotBlank(confirmNo)){
					orderOperConfirmationDao.updateOperConfNumberByOperKey(orderId, operKey, -1, confirmNo,superNet,subNet);	
				}else{
					message.raiseError("MFI_20","Error conf data is blank " + System.lineSeparator() +  System.lineSeparator() +
							"salesOrder = "+ salesOrder + System.lineSeparator() +
							"operNo = " + operNo + System.lineSeparator() +
							"superNet ="  + superNet + System.lineSeparator() +
							"subNet = " + subNet + System.lineSeparator());

				}
			}
		}
	}
}


