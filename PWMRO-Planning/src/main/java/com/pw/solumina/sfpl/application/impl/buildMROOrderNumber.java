/*
*  This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017,2021.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    buildMROOrderNumber.java
*  
*  PW_SMRO_eWORK_202-Order-Creation
* 
*  Created: 2018.03.08
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date             Who            Description
*  -----------    ------------     ---------------------------------------------------
*  2018-03-08       Fred Ettefagh  
*  2018-03-09       Fred Ettefagh  added "-%" to get sub order number and Suppemental order no
*  2018-03-269      Fred Ettefagag defect 386  long order no
*  2018-03-29		Bob Preston	   Defect 457 -- Changed "-%" to "-0__" in buildSubOrderNumber
*  2018-04-03		Bob Preston	   Defect 457 -- Create default suffix if orderCreateDaoPW.getWorkOrderNumber returns "1"
*  2018-04-04		Bob Preston	   Defect 457 -- Convert parentOrderNumber to upper case.
*  2018-04-10		Bob Preston	   SMRO_WOE_204, Defect 457 -- Incorporated changes for 80 char order no.
*  2018-04-29		Fred Ettefagh  defect 632  
*  2018-11-02		Fred Ettefagh  defect 995, Sub and supplemental order numbering is wrong again
*  2018-12-17       Fred Ettefagh  Defect 1044, fix repair order create
*  2021-02-10       John DeNinno   Defect 1864 add part number to the lookup
*/
package com.pw.solumina.sfpl.application.impl;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.ibaset.common.Reference;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.pw.solumina.sfpl.dao.impl.BuildMROOrderNumberDao;

public class buildMROOrderNumber {
	
	@Reference
	private BuildMROOrderNumberDao buildMROOrderNumberDao = null;
	
	@Reference
	private IMessage message = null;
	
	@SuppressWarnings("rawtypes")
	public String buildSubOrderNumber(String pwParentOrderNumber) throws Exception{
		
		String newOrderNumber = null;
		String subOrderSuffix = "001";
		
		if (StringUtils.isBlank(pwParentOrderNumber)){
			message.raiseError("parentOrderNumber cannot be blank");
		}
		
		Map pwustSfwidOrderMap = buildMROOrderNumberDao.selectPwustSwidOrderDescViaPwOrderNo(pwParentOrderNumber);
		String pwOrderType = (String)pwustSfwidOrderMap.get("PW_ORDER_TYPE");
		
		if (StringUtils.equalsIgnoreCase(pwOrderType, "SUB ORDER")  || 	StringUtils.equalsIgnoreCase(pwOrderType, "OVERHAUL")){

			List list = buildMROOrderNumberDao.selectPwustSfwidOrderDesc(pwParentOrderNumber,"SUB ORDER","-%");
			if (list==null || list.isEmpty()){
				newOrderNumber = pwParentOrderNumber + "-001";
			}else{
				Map map = (Map)list.get(0);
				String suffix = (String) map.get("SUFFIX");
				int existingSupplSffixInt= Integer.parseInt(suffix) ;
				existingSupplSffixInt++;
				subOrderSuffix = String.valueOf(existingSupplSffixInt);
		    	if (subOrderSuffix.length()==1){
		    		subOrderSuffix = "00"+subOrderSuffix;
		    	}else if (subOrderSuffix.length()==2){
		    		subOrderSuffix = "0"+subOrderSuffix;
		    	}
				
			}
			
			newOrderNumber = pwParentOrderNumber + "-" + subOrderSuffix;
		    if (newOrderNumber.length() > 80){
		    	message.raiseError("Max Order number length is 80");
		    }			
		}
		
		return newOrderNumber;
		
	}

	public String buildOverhaulOrderNumber(String salesOrderNumber, String partNumber) throws Exception{
		
		String orderSuffix = null;
		orderSuffix = "01";
		String orderNumber = null;
		
		if (StringUtils.isBlank(salesOrderNumber) || StringUtils.isBlank(partNumber)){
			message.raiseError("salesOrderNumber and partNumber cannot be blank");
		}
		
		
		String orderNumberToLookeup = salesOrderNumber + "-" + partNumber + "-%";
	    String existingOrderNumber =buildMROOrderNumberDao.getOverHaulWorkOrderNumber(orderNumberToLookeup,"OVERHAUL",partNumber);  //defect 1864
	    
	    if (StringUtils.isNotBlank(existingOrderNumber)){

	    	// natetest-12345-5
	    	// pm12345-07-BLCYCLE3-01
	    	String[] existingOrderNumberSplit = StringUtils.split(existingOrderNumber, "-");
	    	if (existingOrderNumberSplit!=null && existingOrderNumberSplit.length > 0){

	    		//String salesOrderNumber1 = existingOrderNumberSplit[0];
		    	//String partNumber1 = existingOrderNumberSplit[1];
		    	//String suffix1 = existingOrderNumberSplit[2];
		    	
		    	String suffix1=existingOrderNumberSplit[existingOrderNumberSplit.length-1];
		    	
		    	if (StringUtils.isNotBlank(suffix1) && StringUtils.isNumeric(suffix1)){
			    	
		    		int suffixIint= Integer.parseInt(suffix1) ;
			    	suffixIint++;
			    	
			    	orderSuffix = String.valueOf(suffixIint);
			    	if (orderSuffix.length()==1){
			    		orderSuffix = "0"+orderSuffix;
			    	}
		    	}
	    	}
	    }
	    orderNumber = salesOrderNumber + "-" + partNumber + "-" + orderSuffix;
	    if (orderNumber.length() >80){
	    	message.raiseError("Max Order number lenght is 80");
	    }

	    return orderNumber;
	}
	
	@SuppressWarnings("rawtypes")
	public String buildSuppementalOrderNumber(String pwParentOrderNumber) throws Exception{
		
		
		String newOrderNumber = null;
		String orderSuffix = "001";
		
		if (StringUtils.isBlank(pwParentOrderNumber)){
			message.raiseError("parentOrderNumber cannot be blank");
		}

		Map parentOrderNumberMap = buildMROOrderNumberDao.selectPwustSwidOrderDescViaPwOrderNo(pwParentOrderNumber);
		String parentOrderType = (String)parentOrderNumberMap.get("PW_ORDER_TYPE");
		
		if (  StringUtils.equalsIgnoreCase(parentOrderType, "OVERHAUL") ||  
			  StringUtils.equalsIgnoreCase(parentOrderType, "SUPPLEMENTAL") ||
			  StringUtils.equalsIgnoreCase(parentOrderType, "SUB ORDER" )||
			  StringUtils.equalsIgnoreCase(parentOrderType, "REPAIR") ){
			
			List list = buildMROOrderNumberDao.selectPwustSfwidOrderDesc(pwParentOrderNumber,"SUPPLEMENTAL","-SUP%");
			if (list==null || list.isEmpty()){
				newOrderNumber = pwParentOrderNumber + "-SUP001";
			}else{
				Map map = (Map)list.get(0);
				String suffix = (String) map.get("SUFFIX");
				suffix = StringUtils.substring(suffix, 3,6);
				int existingSupplSffixInt= Integer.parseInt(suffix) ;
				existingSupplSffixInt++;
				orderSuffix = String.valueOf(existingSupplSffixInt);
		    	if (orderSuffix.length()==1){
		    		orderSuffix = "00"+orderSuffix;
		    	}else if (orderSuffix.length()==2){
		    		orderSuffix = "0"+orderSuffix;
		    	}
				
				newOrderNumber = pwParentOrderNumber + "-SUP" + orderSuffix;
			}
			
		    if (newOrderNumber.length() >80){
		    	message.raiseError("Max Order number length is 80");
		    }
		}
	    
        return newOrderNumber;
	}
}