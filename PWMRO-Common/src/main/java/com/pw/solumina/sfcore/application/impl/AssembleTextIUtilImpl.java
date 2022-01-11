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
*  File:    AssembleTextIUtilImpl.java
* 
*  Created: 2018-02-22
* 
*  Author:  XCS4331
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2018-02-22 		XCS4331		    Initial Release XXXXXXX
*/

package com.pw.solumina.sfcore.application.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.pw.solumina.sfcore.dao.AssembleTextDaoPW;

public class AssembleTextIUtilImpl {
	
	@Reference private AssembleTextDaoPW assembleTextDaoPW;
	
	
	
	public List buildOrderTextHashMap(Map orderTextHashMap,String orderId){
		
		Map newOrderTextHashMap = null;
		
		 String orderText     = (String) orderTextHashMap.get("ALL_TEXT");
		 String security      = (String) orderTextHashMap.get("SECURITY_GROUP");

		 String newOrderText = null;
		 String line1   = null;
		 String line2   = null;

		 String calledFrom = null;
		 
		 if (assembleTextDaoPW.isOrderClassified(orderId)){

				Map orderMap =assembleTextDaoPW.selectExportControlInfoForOrder(orderId);
				String orderNo		       = (String) orderMap.get("ORDER_NO");
	 			String jurisdiction	       = (String) orderMap.get("COMMODITY_JURISDICTION");
	 			String classification      = (String) orderMap.get("COMMODITY_CLASSIFICATION");
	 			String sme                 = (String) orderMap.get("UCF_PLAN_FLAG1");
	 			String workLoc             = (String) orderMap.get("WORK_LOC");
	 			String nonUsJurisdiction   = (String) orderMap.get("NON_US_JURISDICTION"); 
	 			String nonUsClassification = (String) orderMap.get("NON_US_JURISDICTION"); 
	 			String license             = (String) orderMap.get("LICENSE");
	
				if (StringUtils.equals(jurisdiction, "ITAR") )
				{
					line1 = getItarStatement(calledFrom,jurisdiction,classification,sme);
					
				}else if (StringUtils.equals(jurisdiction, "EAR")){
				
					line1 = getEarStatement(calledFrom,jurisdiction,classification);
					
				}else if (StringUtils.equals(jurisdiction, "NSR")){
					
					line1 = getNsrStatement(calledFrom);
					
				}if(nonUsJurisdiction != null || nonUsClassification != null){
					
					line2 = getNonUsStatement(calledFrom, nonUsJurisdiction, nonUsClassification, license);	
				}

				if(line1 != null && line2 != null) 
				{
					newOrderText = line1 + "\n\n" + line2 + "\n" + orderText;
				}else if(line1 != null)
				{
					newOrderText = line1 + "\n" + orderText;
				}
				else if(line2 != null)
				{
					newOrderText = line2 + "\n" + orderText;
				}

				String application = "G8MROP";
				String busUnit = "PWA";
				String authLicense = null;
				//String docId = workLoc + "," + planNo + "," + planVersion + "," + planRevision;
				String docId =  workLoc + "," + orderNo;
	
				assembleTextDaoPW.insertExportAuditLog(application, busUnit, jurisdiction, classification, sme, authLicense, docId);
	
				newOrderTextHashMap = new HashMap();
				newOrderTextHashMap.put("ALL_TEXT", newOrderText);
				newOrderTextHashMap.put("SECURITY_GROUP", security);

		 }else{

			String notYetClassifiedStatement = "This document may contain export controlled technical data, but has not yet been classified for export purposes.  " +
                          					   "Do not transfer without proper export classification and approval.";

			newOrderText = notYetClassifiedStatement + "\n" + orderText;

			newOrderTextHashMap = new HashMap();
			newOrderTextHashMap.put("ALL_TEXT", newOrderText);
			newOrderTextHashMap.put("SECURITY_GROUP", security);

		}
	 
		ArrayList newOrderTextList = new ArrayList();
		newOrderTextList.add(0, newOrderTextHashMap);
	    return newOrderTextList;
	}
	
	
	public Map buildPlanTextHashMap(Map planTextMap,String planId, Number planVer, Number planRev, Number planAlt, String calledFrom){
		
		Map planTextMap2 = null;
		
		if (assembleTextDaoPW.isPlanClassified(planId, planVer, planRev, planAlt)){
			
				//Get export control information for the plan
				Map expControlMap = assembleTextDaoPW.selectExportControlInfoForPlan(planId,planVer,planRev,planAlt);
		
				String planNo		       = (String) expControlMap.get("PLAN_NO");
				String jurisdiction	       = (String) expControlMap.get("COMMODITY_JURISDICTION");
				String classification      = (String) expControlMap.get("COMMODITY_CLASSIFICATION");
				String sme                 = (String) expControlMap.get("UCF_PLAN_FLAG1");
				String workLoc             = (String) expControlMap.get("WORK_LOC");
				String nonUsJurisdiction   = (String) expControlMap.get("NON_US_JURISDICTION"); 
				String nonUsClassification = (String) expControlMap.get("NON_US_JURISDICTION"); 
				String license             = (String) expControlMap.get("LICENSE");
		
		
				String newText = null;
				String line1   = null;
				String line2   = null;
		
				String text     = (String) planTextMap.get("TEXT");
				String security = (String) planTextMap.get("SECURITY_GROUP");
		
				if (StringUtils.equals(jurisdiction, "ITAR") ){
					line1 = getItarStatement(calledFrom, jurisdiction, classification, sme);
				} else if (StringUtils.equals(jurisdiction, "EAR"))	{
					line1 = getEarStatement(calledFrom, jurisdiction, classification);
				} else if (StringUtils.equals(jurisdiction, "NSR"))	{
					line1 = getNsrStatement(calledFrom);	
				}
		
				if(nonUsJurisdiction != null || nonUsClassification != null) {
					line2 = getNonUsStatement(calledFrom, nonUsJurisdiction, nonUsClassification, license);	
				}
		
				if(line1 != null && line2 != null) {
					newText = line1 + "\n\n" + line2 + "\n" + text;
				}else if(line1 != null){
					newText = line1 + "\n" + text;
				}else if(line2 != null)	{
					newText = line2 + "\n" + text;
				}
		
				String application = "G8MROP";
				String busUnit = "PWA";
				String authLicense = null;
				String docId = workLoc + "," + planNo + "," + planVer + "," + planRev;        		   
		
				assembleTextDaoPW.insertExportAuditLog(application, busUnit, jurisdiction, classification, sme, authLicense, docId);
		
				planTextMap2 = new HashMap();
				planTextMap2.put("TEXT", newText);
				planTextMap2.put("SECURITY_GROUP", security);

		}	else{
			String newText = null;
	
			String text     = (String) planTextMap.get("TEXT");
			String security = (String) planTextMap.get("SECURITY_GROUP");
			String notYetClassifiedStatement = "This document may contain export controlled technical data, but has not yet been classified for export purposes.  " +
					                           "Do not transfer without proper export classification and approval.";
	
			newText = notYetClassifiedStatement + "\n" + text;

			planTextMap2 = new HashMap();
			planTextMap2.put("TEXT", newText);
			planTextMap2.put("SECURITY_GROUP", security);
		}
		
		return planTextMap2;
		
	}
	
	
    public String getItarStatement(String calledFrom, String jurisdiction, String classification, String sme)
    {
    	String itarStatement = null;
    	if (StringUtils.equals(calledFrom, "Report") )
    	{
    		if ("Y".equals(sme))
    		{
    			itarStatement = 	"US Classification: " + "Jurisdiction: " + jurisdiction + "  Classification: " + classification + "  SME";
    		}
    		else
    		{
    			itarStatement = "US Classification: " + "Jurisdiction: " + jurisdiction + "  Classification: " + classification;
    		}

    	} 
    	else
    	{  	    
    		if ("Y".equals(sme))
    		{
    			itarStatement = 	"US Classification: " + "Jurisdiction: " + jurisdiction + " Classification: " + classification + " SME";
    		}
    		else
    		{
    			itarStatement = "US Classification: " + "Jurisdiction: " + jurisdiction + " Classification: " + classification;
    		}

    	}	
    	return itarStatement;
    }

    /** 
     * SMRO_EXPT_201 - Oct 13, 2017
     * 
     * @author c079222 - David Miron
     *
     * @param calledFrom
     * @param jurisdiction
     * @param classification
     * @return
     */
    public String getEarStatement(String calledFrom, String jurisdiction, String classification)
    {
    	String earStatement = null;
    	if (StringUtils.equals(calledFrom, "Report") )
    	{
    		earStatement = "US Classification: " + "Jurisdiction: " + jurisdiction + " Classification: " + classification;
    	}
    	else
    	{
    		earStatement = "US Classification: " + "Jurisdiction: " + jurisdiction + " Classification: " + classification;
    	}	

    	return earStatement;
    }

    /** 
     * SMRO_EXPT_201 - Oct 13, 2017
     * 
     * @author c079222 - David Miron
     *
     * @param calledFrom
     * @return
     */
    public String getNsrStatement(String calledFrom)
    {
    	//String nsrStatement  = "US Classification: Jurisdiction: NSR Classification: N/A";   // A&T
    	String nsrStatement  = "This document does not contain any export regulated technical data.";  // G8

    	return nsrStatement;
    }

    /** 
     * SMRO_EXPT_201 - Oct 13, 2017
     * 
     * @author c079222 - David Miron
     *
     * @param calledFrom
     * @param nonUsJurisdiction
     * @param nonUsClassification
     * @param license
     * @return
     */
    public String getNonUsStatement(String calledFrom, String nonUsJurisdiction, String nonUsClassification, String license)
    {
    	String nonUsStatement = null;
    	if (StringUtils.equals(calledFrom, "Report") )
    	{
    		nonUsStatement = "Local Classification: Jurisdiction: " + nonUsJurisdiction + " Classification: " + nonUsClassification+ " License: " + license;
    	}
    	else
    	{
    		nonUsStatement = "Local Classification: Jurisdiction: " + nonUsJurisdiction + " Classification: " + nonUsClassification+ " License: " + license;
    	}	

    	return nonUsStatement;
    }	
	
	
}

