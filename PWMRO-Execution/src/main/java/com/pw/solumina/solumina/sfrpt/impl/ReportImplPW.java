/*
 *  This unpublished work, first created in 2017 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2017.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    ReportImplPW.java
 * 
 *  Created: 2018-08-1
 * 
 *  Author:  Fred Ettefagh
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2018-08-01		Fred Ettefagh   Initial Release SMRO_RPT_201 - Defect 907 added EXECQUERY to get list of buyoffs for Rave report 
 *  2018-08-03      Fred Ettefagh   defect 908, added check to call diff Dao to get list of buyoffs based on order status
 *  2018-08-05		R. Cannon		SMRO_RPT_201 Defect 908 - Changes to method selectReportSerialBuyoffHist;
 *  								Moved getAddtionalSignoffs4DPReport() to here from com.pw.solumina.sfwid.application.BuyoffPW
 *  2018-08-06		R. Cannon		SMRO_RPT_201 Defect 930 - Sort buyoff history in timestamp descending order instead of ascending
 *  2018-08-07		R. Cannon		SMRO_RPT_201 Defect 930 - Sort buyoff history by type and then timestamp ascending (revert prev chg)
 *  2018-08-24		R. Cannon		SMRO_RPT_201 Defect 958 - Fix duplicate buyoffs
 *  2020-10-12      Fred Ettefagh   Defect 1792, Changed method selectReportSerialBuyoffHist
 *  2021-01-19      B.Polak         SMRO_RPT_201 Defect 1832 - Added Check to prevent null pointer exception
 *  2021-03-15      B.Polak         SMRO_RPT_201 Defect 1848 - Added serial no to getReportOrderBuyoffsList
 *  2021-05-26		B.Polak			SMRO_RPT_201 Defect 1896 - Added additional buyoff handling to getReportOrderBuyoffsList
*/
package com.pw.solumina.solumina.sfrpt.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.ibaset.common.Reference;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sfrpt.dao.IReportDao;
import com.ibaset.solumina.sfwid.application.IAssemble;
import com.pw.common.dao.CommonDaoPW;
import com.pw.solumina.sfpl.dao.impl.OrderCreateDaoPW;
import com.pw.solumina.sfwid.application.BuyoffPW;
import com.pw.solumina.sfwid.dao.BuyoffDaoPW;
import com.pw.solumina.solumina.sfrpt.dao.impl.ReportDaoImplPW;


public class ReportImplPW {
	
	@Reference private CommonDaoPW commonDaoPW;
	@Reference private BuyoffPW buyoffPW;
	@Reference private BuyoffDaoPW buyoffDaoPW;
	@Reference private ReportDaoImplPW reportDaoImplPW;
	@Reference private OrderCreateDaoPW orderCreateDaoPW;
	@Reference private IReportDao reportDao = null;
	@Reference IMessage message;
	@Reference IEvent event;
	@Reference private IAssemble assemble;	 
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getReportOrderBuyoffsList (String orderId, Number operKey, Number stepKey, String refId ){

		List buyoffListsArray = new ArrayList<Map>(0);
		List<Map> buyOFfListsMap = null;

		Map sfwidOrderDescMap = orderCreateDaoPW.selectOrderDesc(orderId);
		String orderStatus = (String) sfwidOrderDescMap.get("ORDER_STATUS");

		String pwOrderType = reportDaoImplPW.getPWOrderType(orderId);

		
		if (StringUtils.equalsIgnoreCase(orderStatus, "ACTIVE") || StringUtils.equalsIgnoreCase(orderStatus, "CLOSE")){
			buyOFfListsMap = this.selectReportSerialBuyoffHist(orderId, operKey, stepKey, refId, pwOrderType);
			
		}else{ //order status = "PENDING" or "IN QUEUE"
			buyOFfListsMap = reportDaoImplPW.selectReportOrderBuyoffs (orderId, operKey, stepKey, refId, pwOrderType );	
		}
			
		//1896 grab the buyoff statuses for non-additional buyoffs.
		if (buyOFfListsMap!=null && !buyOFfListsMap.isEmpty()){
			
			Map<String,String> statusMap = new HashMap();
			for (Map buyoffMap : buyOFfListsMap) {
				
				String buyoff_id = (String) buyoffMap.get("BUYOFF_ID");
				String buyoff_status = (String) buyoffMap.get("BUYOFF_STATUS");
				String ucf_Srlopbuyoff_Vch4000_1  = (String) buyoffMap.get("UCF_SRLOPBUYOFF_VCH4000_1");
				String serial_no = (String) buyoffMap.get("SERIAL_NO");
				
				if (ucf_Srlopbuyoff_Vch4000_1 == null || ucf_Srlopbuyoff_Vch4000_1.isEmpty()) 
				{
					statusMap.put((buyoff_id+serial_no), buyoff_status);
				}
				
				
			}
			
			for (Map buyoffMap : buyOFfListsMap) {
				
				Map newMap = new HashMap();
				
				//1896  if this is an additional buyoff, give it the status of parent buyoff 
				String ucf_Srlopbuyoff_Vch4000_1  = (String) buyoffMap.get("UCF_SRLOPBUYOFF_VCH4000_1");
				String buyoff_id = (String) buyoffMap.get("BUYOFF_ID");
				String buyoff_status = (String) buyoffMap.get("BUYOFF_STATUS");
				String serial_no = (String) buyoffMap.get("SERIAL_NO");
				
				if (ucf_Srlopbuyoff_Vch4000_1 != null && !ucf_Srlopbuyoff_Vch4000_1.isEmpty()) 
				{
					buyoff_status = statusMap.get(buyoff_id+serial_no);
				}
				
				newMap.put("BUYOFF_ID", buyoff_id);
				newMap.put("OPER_NO",(String) buyoffMap.get("OPER_NO"));
				newMap.put("STEP_NO",(String) buyoffMap.get("STEP_NO"));
				newMap.put("BUYOFF_TYPE",(String) buyoffMap.get("BUYOFF_TYPE"));
				newMap.put("BUYOFF_CERT",(String) buyoffMap.get("BUYOFF_CERT"));
				newMap.put("BUYOFF_TITLE",(String) buyoffMap.get("BUYOFF_TITLE"));
				newMap.put("OPTIONAL_FLAG",(String) buyoffMap.get("OPTIONAL_FLAG"));
				newMap.put("UPDT_USERID",(String) buyoffMap.get("UPDT_USERID"));
				Object timeStampObject= buyoffMap.get("TIME_STAMP");
				newMap.put("TIME_STAMP",timeStampObject);
				newMap.put("SERIAL_NO",(String) buyoffMap.get("SERIAL_NO"));
				newMap.put("USER_NAME",(String) buyoffMap.get("USER_NAME"));
				newMap.put("BUYOFF_STATUS", buyoff_status);
				newMap.put("PERCENT_COMPLETE",(String) buyoffMap.get("PERCENT_COMPLETE"));
				newMap.put("COMMENTS",(String) buyoffMap.get("COMMENTS"));
				newMap.put("UCF_SRLOPBUYOFF_VCH4000_1", ucf_Srlopbuyoff_Vch4000_1);
				newMap.put("SECURITY_GROUP",(String) buyoffMap.get("SECURITY_GROUP"));
				
				
				
				buyoffListsArray.add(newMap);
				
			}		
		}else{
			Map emptyMap = new HashMap();
			
			emptyMap.put("BUYOFF_ID",null);
			emptyMap.put("OPER_NO",null);
			emptyMap.put("STEP_NO",null);
			emptyMap.put("BUYOFF_TYPE",null);
			emptyMap.put("BUYOFF_CERT",null);
			emptyMap.put("BUYOFF_TITLE",null);
			emptyMap.put("OPTIONAL_FLAG",null);
			emptyMap.put("UPDT_USERID",null);
			emptyMap.put("TIME_STAMP",null);
			emptyMap.put("USER_NAME",null);
			emptyMap.put("SERIAL_NO",null);
			emptyMap.put("BUYOFF_STATUS",null);
			emptyMap.put("PERCENT_COMPLETE",null);
			emptyMap.put("COMMENTS",null);
			emptyMap.put("UCF_SRLOPBUYOFF_VCH4000_1",null);
			emptyMap.put("SECURITY_GROUP",null);
			
			buyoffListsArray.add(emptyMap);
		}
		
		return buyoffListsArray;
	}
		
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getReportOrderOperationsList(String orderId, Number StepKey) {
		
		List OperationstList = new ArrayList<Map>(0);
		List<Map> operList = reportDaoImplPW.selectReportOrderOperations(orderId, StepKey);
		
		if (operList!=null && !operList.isEmpty()){
			for (Map operMap : operList) {
				
				Map newMap = new HashMap();
				
				newMap.put("OPER_NO",(String) operMap.get("OPER_NO"));
				newMap.put("OPER_KEY",(Number) operMap.get("OPER_KEY"));
				newMap.put("STEP_KEY",(Number) operMap.get("STEP_KEY"));
				newMap.put("OPER_UPDT_NO",(Number) operMap.get("OPER_UPDT_NO"));
				newMap.put("OPER_TYPE",(String) operMap.get("OPER_TYPE"));
				newMap.put("AUTO_START_FLAG",(String) operMap.get("AUTO_START_FLAG"));
				newMap.put("AUTO_COMPLETE_FLAG",(String) operMap.get("AUTO_COMPLETE_FLAG"));
				newMap.put("OCCUR_RATE",(Number) operMap.get("OCCUR_RATE"));
				newMap.put("PLND_MACHINE_NO",(String) operMap.get("PLND_MACHINE_NO"));
				newMap.put("OPER_OPT_FLAG",(String) operMap.get("OPER_OPT_FLAG"));
				newMap.put("SUPPLIER_CODE",(String) operMap.get("SUPPLIER_CODE"));
				newMap.put("OSP_DAYS",(Number) operMap.get("OSP_DAYS"));
				newMap.put("OSP_COST_PER_UNIT",(Number) operMap.get("OSP_COST_PER_UNIT"));
				newMap.put("TITLE",(String) operMap.get("TITLE"));				
				newMap.put("ASGND_WORK_DEPT",(String) operMap.get("ASGND_WORK_DEPT"));
				newMap.put("ASGND_WORK_CENTER",(String) operMap.get("ASGND_WORK_CENTER"));
				newMap.put("ASGND_WORK_LOC",(String) operMap.get("ASGND_WORK_LOC"));
				newMap.put("TEST_TYPE",(String) operMap.get("TEST_TYPE"));
				newMap.put("STD_OPER_FLAG",(String) operMap.get("STD_OPER_FLAG"));
				newMap.put("SECURITY_GROUP",(String) operMap.get("SECURITY_GROUP"));							
				OperationstList.add(newMap);				
			}
		}else{
			Map emptyMap = new HashMap();
			
			emptyMap.put("OPER_NO",null);
			emptyMap.put("OPER_KEY",null);
			emptyMap.put("STEP_KEY",null);
			emptyMap.put("OPER_UPDT_NO",null);
			emptyMap.put("OPER_TYPE",null);
			emptyMap.put("AUTO_START_FLAG",null);
			emptyMap.put("AUTO_COMPLETE_FLAG",null);
			emptyMap.put("OCCUR_RATE",null);
			emptyMap.put("PLND_MACHINE_NO",null);
			emptyMap.put("OPER_OPT_FLAG",null);
			emptyMap.put("SUPPLIER_CODE",null);
			emptyMap.put("OSP_DAYS",null);
			emptyMap.put("OSP_COST_PER_UNIT",null);
			emptyMap.put("TITLE",null);				
			emptyMap.put("ASGND_WORK_DEPT",null);
			emptyMap.put("ASGND_WORK_CENTER",null);
			emptyMap.put("ASGND_WORK_LOC",null);
			emptyMap.put("TEST_TYPE",null);
			emptyMap.put("STD_OPER_FLAG",null);
			emptyMap.put("SECURITY_GROUP",null);
			OperationstList.add(emptyMap);
		}
		
		return OperationstList;
	}

	   //Defect 908, 930
    @SuppressWarnings({ "unchecked", "rawtypes" })
    //This method only works if DAO for buyoff history sorts on time_stamp desc
	private List selectReportSerialBuyoffHist(String orderId, 
			                                  Number operKey, 
			                                  Number stepKey,
											  String refId, 
											  String pwOrderType) {
    	
    	List origBOHistList = buyoffPW.getSerialBuyoffListHistTab(orderId, "calledFromReport", null,operKey,stepKey,refId);
    	ArrayList filteredList = new ArrayList();
    	
    	Map currentRecordMap;
    	
    	String currentId = null;
    	String currentStatus = null;
    	String prevId = null;
    	String skipBuyoffIds = "";	//once buyoff is found in a certain status, stop processing this id
    	String usedBuyoffIds = "";	//list of distinct buyoffids for this section of the report
    	int idEntries = 0;
    	
    	if(origBOHistList.size() > 0)
    	{
    		for(int i=0;i<origBOHistList.size();i++)
    		{
    			currentRecordMap = (Map) origBOHistList.get(i);      
    			currentId = (String) currentRecordMap.get("BUYOFF_ID");
    			currentStatus = (String) currentRecordMap.get("BUYOFF_STATUS");
    			
    			if(currentId != null && !usedBuyoffIds.contains(currentId))
				{
					usedBuyoffIds += currentId + ",";
					idEntries = 0;
				}

    			//Build filtered list from buyoff history:
    			//  Omit history for a given id before the most recent PENDING, OPEN, or REOPEN status
    			//  Only show PENDING, OPEN, or REOPEN if no new history exists beyond these statuses
    			if(currentId != null && !skipBuyoffIds.contains(currentId))
    			{
    				if ("PENDING".equals(currentStatus) || "OPEN".equals(currentStatus) ||
    					"REOPEN".equals(currentStatus))
    				{
    					skipBuyoffIds += currentId + ",";

    					if(idEntries == 0)
    					{
    						filteredList.add(currentRecordMap);
    						idEntries++;
    					}
    				} else {
    					filteredList.add(currentRecordMap);
    					idEntries++;
    				}
    			}
    		}//end for loop

    		//Begin Defect 930
    		ArrayList tempList = new ArrayList();
    		ArrayList retList = new ArrayList();
    		
			//Filtered list already ordered by buyoff type, id and then timestamp descending
			//  Block out common buyoff ids and sort them timestamp ascending
			for(int i=0;i<filteredList.size();i++)
			{
				currentRecordMap = (Map) filteredList.get(i);
				currentId = (String) currentRecordMap.get("BUYOFF_ID");
				
				//Begin Defect 958
				//Ignoring 1st iteration, sort block of common ids when a new id encountered; add to final list
				if(!tempList.isEmpty() && !currentId.equals(prevId))
				{
					Collections.sort(tempList, new Comparator<Map>(){ //sort timestamp ascending
		    			public int compare (Map m1, Map m2){
		    				return ((Date) m1.get("TIME_STAMP")).compareTo((Date) m2.get("TIME_STAMP"));
		    			}});

					retList.addAll(tempList);
		    		tempList = new ArrayList();
				}
				tempList.add(currentRecordMap); //build up block of common ids
				prevId = currentId;

			} //end for loop
			
			//There will always be 1+ buyoffs in tempList at this point. Sort and append to final list
    		Collections.sort(tempList, new Comparator<Map>(){
    			public int compare (Map m1, Map m2){
    				return ((Date) m1.get("TIME_STAMP")).compareTo((Date) m2.get("TIME_STAMP"));
    			}});
    		retList.addAll(tempList);
			//End Defect 958
    		
			//Preserve PENDING/OPEN timestamps until the end to accommodate prior timestamp sorting
    		for(int i=0;i<retList.size();i++)
    		{
    			currentRecordMap = (Map) retList.get(i);
    			currentStatus = (String) currentRecordMap.get("BUYOFF_STATUS");
    			
    			if("PENDING".equals(currentStatus) || "OPEN".equals(currentStatus))
				{
					currentRecordMap.put("TIME_STAMP", null);
					currentRecordMap.put("USER_NAME", null);
					currentRecordMap.put("UPDT_USERID", null);
				}
    		}
    		return retList;
    		//End Defect 930

    	} //end if(origBOHistList.size() > 0)
    	else
    	{
    		return origBOHistList;
    	}
    } //end selectReportSerialBuyoffHist()

    
    /* ***************************************************************************
     * 	This method called from PWUST_SelectAdditionalBuyoffSignatures4DPReport
     * 	which connectionId and dataView were removed from the internal Delivery
     * 	Package report, PWUST_DP_InternalRpt2, on 2018-08-05. Obsolete method.
     * ***************************************************************************/
    @SuppressWarnings({ "rawtypes" })
	public List getAddtionalSignoffs4DPReport(String orderId,
    		                                  Number operKey, 
    		                                  /*Number stepKey,*/
    		                                  String buyoffId)
    {
    	//Gather all records on operation with non-null UCF_SRLOPBUYOFF_VCH4000_1
    	List retList = new ArrayList();
/*    	List signoffList = buyoffDaoPW.selectAddtionalSignoffs4DPReport(orderId, operKey, stepKey, buyoffId); //need to comment out stepKey
    	Map retMap;

    	String signoffUserid;
    	String signoffDate;
    	String signoffName;	
    	String token;

    	//Begin Defect 849
    	if(!signoffList.isEmpty())
    	{
    		//List records essentially keyed off step no and buyoff type
    		for (int i=0;i<signoffList.size();i++) {
    			//End Defect 849

    			Map map = (Map) signoffList.get(i);

    			String buyoffTitle = (String) map.get("BUYOFF_TITLE");
    			String buyoffStep = (String) map.get("STEP_NO");

    			String signoffs = (String) map.get("UCF_SRLOPBUYOFF_VCH4000_1"); 
    			StringTokenizer tokens = new StringTokenizer(signoffs, ",");

    			//Parse UCF_SRLOPBUYOFF_VCH4000_1 for all "additional buyoffs"
    			while (tokens.hasMoreElements()) {

    				token = tokens.nextElement().toString();

    				signoffUserid = token.substring(0, token.indexOf("-"));
    				signoffDate = token.substring(token.indexOf("-")+1);
    				signoffName = StringUtils.defaultIfEmpty(commonDaoPW.getUserFullName(signoffUserid), signoffUserid);

    				if (signoffName == null || signoffName.isEmpty()) {
    					signoffName = signoffUserid;
    				}

    				retMap = new HashMap();
    				retMap.put("BUYOFF_TITLE", buyoffTitle);
    				retMap.put("STEP_NO", buyoffStep);
    				retMap.put("SIGNOFF_USERID", signoffUserid);
    				retMap.put("SIGNOFF_DATE", signoffDate);
    				retMap.put("SIGNOFF_NAME", signoffName);
    				retList.add(retMap);
    			}
    		} //end for loop
    	} //end if (signoffList isn't empty)
    	else
    	{
    		retMap = new HashMap();
    		retMap.put("SIGNOFF_USERID", null);
    		retMap.put("SIGNOFF_DATE", null);
    		retMap.put("SIGNOFF_NAME", null);
    		retMap.put("BUYOFF_TITLE", null);
    		retMap.put("STEP_NO", null);
    		retList.add(retMap);
    	}
*/
    	return retList;

    } //end getAdditionalSignoffs4DReport()

    @SuppressWarnings("rawtypes")
	public List selectReportOrderOperationaAndStepText(String orderId, Number operationKey, Number stepKey,
			String textType) {
		System.out.println("selectReportOrderOperationaAndStepText BEGIN");
		System.out.println("	OrderId:"+orderId+" operationKey:"+operationKey+" stepKey:"+stepKey+" textType:"+textType);
		List returnList = new ArrayList();
		Map map = new HashMap();

		String textToReplace = StringUtils.EMPTY;
		String stepNo = StringUtils.EMPTY;
		String blockId = StringUtils.EMPTY;
		String discId = StringUtils.EMPTY;
		Number discLineNo = null;

		List tempList = reportDao.selectReportOrderOperationAndStepText(orderId, operationKey, stepKey, textType);

		ListIterator planTextIterator = tempList.listIterator();

		if (!planTextIterator.hasNext()) {
			buildHeaderStepTextListForReport(operationKey, stepKey, returnList, map, textToReplace, stepNo, blockId,
					discId, discLineNo);
		}

		while (planTextIterator.hasNext()) {
			Map planTextRow = (Map) planTextIterator.next();
			// FND-26551
			String emptyTextMsg = message.buildMessage(StringUtils.EMPTY, "MFI_CE7EB89AFCDB4CDFA5FCCC12BD8AA4A5");
			textToReplace = StringUtils.defaultIfEmpty((String) planTextRow.get("TEXT"), emptyTextMsg);
			stepNo = (String) planTextRow.get("STEP_NO");
			blockId = (String) planTextRow.get("BLOCK_ID");
			discId = (String) planTextRow.get("DISC_ID");
			discLineNo = (Number) planTextRow.get("DISC_LINE_NO");
			textToReplace = assemble.replaceAllTextObjects(textToReplace, null, false);
			buildHeaderStepTextListForReport(operationKey, stepKey, returnList, map, textToReplace, stepNo, blockId,
					discId, discLineNo);
		}

		System.out.println("selectReportOrderOperationaAndStepText END");
		return returnList;
		
	}
    
    @SuppressWarnings("unchecked")
	protected void buildHeaderStepTextListForReport(Number operationKey, Number stepKey, List returnList, Map map,
			String textToReplace, String stepNo, String blockId, String discId, Number discLineNo) {
		textToReplace = StringUtils.rightPad(textToReplace, 5000);
		map.put( "TEXT", textToReplace );
		map.put( "OPER_KEY", operationKey );
		map.put( "STEP_KEY", stepKey );
		map.put( "STEP_NO", stepNo );
		map.put( "BLOCK_ID", blockId );
		map.put( "DISC_ID", discId );
		map.put( "DISC_LINE_NO", discLineNo==null ? NumberUtils.FLOAT_ZERO : discLineNo );  // GE-21287
		returnList.add( map );
	}
    
} //end ReportImplPW Class

