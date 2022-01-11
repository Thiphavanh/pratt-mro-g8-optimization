/*
* This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017, 2021.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    PWMROISerialImpl.java
* 
*  Created: 2019-12-10
* 
*  Author:  X005703
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2019-12-10 		X005703		    Initial Release IBaset delivery cockpit
* 01-24-2020 		D.Sibrian		Updated for T314 Interface messaging
* 03-08-2021        J DeNinno		defect 1873 Update related orders with delivered for same serial numbers
* 04-01-2021        J DeNinno	    defect 1886 have only one message sent to the interface
* 2021-08-24        X006881         Defect 1862 & 1868
* 2021-10-28        J DeNinno		Defect 2010 engine manuals T314
*/

package com.pw.solumina.sfwid.application.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.StringUtils.isEmpty;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.event.SoluminaEventPublisher;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IEvent;

import com.ibaset.solumina.sffnd.application.IWrapUtils;
import com.ibaset.solumina.sfwid.application.ISerial;
import com.ibaset.solumina.sfwid.dao.IBuildPartDao;
import com.ibaset.solumina.sfwid.dao.IOrderDao;

import com.pw.common.dao.CommonDaoPW;
import com.pw.solumina.authority.application.dao.IAuthorityDao;
import com.pw.solumina.authority.domain.PwmroiOrderSubjectAuthority;
import com.pw.solumina.domain.PwmroiDefect;
import com.pw.solumina.domain.PwmroiSerial;
import com.pw.solumina.event.PWSDispositionEvent;
import com.pw.solumina.sfwid.application.IPWMROISerial;
import com.pw.solumina.sfwid.dao.IPWMROISerialDao;
import com.pw.solumina.sfwid.dao.impl.PWMROISerialDaoImpl;
import com.ibaset.solumina.sfwid.dao.IOperationDao;

import static com.pw.solumina.common.PWMROIConstants.DLVR_STAT_LOCAL_DISPOSE_INIT;
import static com.pw.solumina.common.PWMROIConstants.DLVR_STAT_RETURN_AS_IS_INIT;
import static com.pw.solumina.common.PWMROIConstants.DLVR_STAT_SALVAGE_REVIEW_INIT;
import static com.pw.solumina.common.PWMROIConstants.SCRAP_TYPE_LOCAL_DISPOSE;
import static com.pw.solumina.common.PWMROIConstants.SCRAP_TYPE_RETURN_AS_IS;
import static com.pw.solumina.common.PWMROIConstants.SCRAP_TYPE_SALVAGE_REVIEW;

import static com.ibaset.common.SoluminaConstants.INSERT;
import static com.ibaset.common.SoluminaConstants.YES;
import static com.ibaset.common.SoluminaConstants.NO;
import static com.ibaset.common.SoluminaConstants.P;
import static com.ibaset.common.SoluminaConstants.S;

import static com.pw.solumina.common.PWMROIConstants.NR;
import static com.pw.solumina.common.PWMROIConstants.UNSR;

public class PWMROISerialImpl implements IPWMROISerial
{
    @Reference
    IPWMROISerialDao pwmroiSerialDao;

    @Reference
    IOrderDao orderDao;
    

    @Reference
    IMessage message;

    @Reference
    IEvent event;
    
    @Reference
    protected IWrapUtils wrapUtils = null;
    
    @Reference 
    private IAuthorityDao authorityDao;
    
    @Reference
    private SoluminaEventPublisher soluminaEventPublisher;
    
    @Reference
    private ISerial serial;
    
    @Reference
    private IBuildPartDao buildPartDao;
    
    @Reference
    CommonDaoPW commonDaoPW;
    
    @Reference
    private IOperationDao operationDao;
    
    @Reference
    private PWMROISerialDaoImpl pwmroDao;
        
    @SuppressWarnings("unchecked")
    @Override
    public void initiateSerialDelivery(String orderId,
                                      String serialNos,
                                      String manualNos)
    {
		//Begin Defect 1873
    	initiateSerialDelivery2(orderId, serialNos, manualNos);
    	
		List relatedOrders = commonDaoPW.getrelatedOrders(orderId);
		ListIterator relatedOrdersIt = relatedOrders.listIterator();
		while (relatedOrdersIt.hasNext()){
			Map relatedElementsKeys = (Map) relatedOrdersIt.next();
			orderId = (String) relatedElementsKeys.get("ORDER_ID");
			System.out.println("initiateSerialDelivery - " + orderId + " " + serialNos + " " + manualNos);
			initiateSerialDelivery2(orderId, serialNos, manualNos);
		} //end defect 1873
    }
    
    public void initiateSerialDelivery2(String orderId,
            String serialNos,
            String manualNos)
     {
         if (manualNos == null)
        	 manualNos = "N/A,N/A";
        // validation
        StringTokenizer st = new StringTokenizer(serialNos,
                                                 SoluminaConstants.SEMI_COLON);
        String[] serial = null;
        String[] serialNoList = new String[st.countTokens()];
        String[] serialIdList = new String[st.countTokens()];
        int i = 0;
        
        String orderNos = " "; 
        String errSns = " ";
        boolean dispError = false;
        String errMessage = " is ACTIVE in ORDER(S) " ;
        String errMessageDisp = "";
        while (st.hasMoreElements())
        {
            serial = st.nextToken().split(",");
            serialNoList[i] = serial[1];
            serialIdList[i] = serial[0];
            i++;
            String itemId = orderDao.selectOrderItemId(orderId);
            List notCompletedSerials = pwmroiSerialDao.getInCompleteSerialsFromOtherOrder(orderId,
                                                                                            serial[1],
                                                                                            itemId);
            if (notCompletedSerials.size() > 0)
            {   Iterator it = notCompletedSerials.iterator();
                dispError = true;
                errMessageDisp = errMessageDisp+serial[1] + errMessage;   
                while (it.hasNext())
                { Map rec = (Map) it.next();
                  String orderNumber = (String) rec.get("ORDER_NO");
                  orderNos = orderNos + orderNumber + ", ";                 
                }
                errMessageDisp = errMessageDisp + orderNos + "they should be COMPLETE" + "\n";
                orderNos = "";
              }
            }
    
        
        // %V1 serial is in %V2 status in the order %V3 , It should be
        // in COMPLETE.
        if (dispError)
        {
	        message.raiseError("MFI_50400",errMessageDisp);
	        	//	errSns,
	            //    "ACTIVE",
	             //   orderNos);
        }
        
        st = new StringTokenizer(manualNos,
				 				SoluminaConstants.SEMI_COLON);
        String[] manualNo = null;
        
        String[] manual = new String[st.countTokens()];
        String[] revNo = new String[st.countTokens()]; //defect 2010
        i = 0;
        System.out.println("manual Nos" + manual.toString());
        
        while (st.hasMoreElements())
        {
        	manualNo = st.nextToken().split(",");
        	System.out.println("manualNo is - " + manualNo[0]);
        	System.out.println("rev No is - " + manualNo[1]);
        	manual[i] = manualNo[0];
        	revNo[i] = manualNo[1];
        	i++;
        }
        String interfaceTrxId = "X";
        // pwu=160  
        pwmroiSerialDao.deleteDeliverSerial(orderId, serialNos);
        pwmroiSerialDao.insertSerialDelivery(orderId,
                                             serialNos,
                                             manualNos,
                                             interfaceTrxId);
       
        pwmroiSerialDao.updateDeliverSerialStatus(orderId, serialNos, "DELIVERY INITIATED");
        //here check manualNo
        PwmroiSerial pwmroiSerial = buildPwmroiSerialObject(orderId,serialNoList,serialIdList,serial,manual,revNo,null);//defect 2010
        pwmroiSerial.setCondition("SERV");
        
        PWSDispositionEvent pwsDispositionEvent = new PWSDispositionEvent(this);
        
        pwsDispositionEvent.setPwmroiSerial(pwmroiSerial);
        
        soluminaEventPublisher.publishEvent(pwsDispositionEvent);
        
        event.refreshDataPages();
    }
    
	protected PwmroiSerial buildPwmroiSerialObject(String orderId, String[] serialNoList, String[] serialIdList,String[] serial, String[] manualNo,String[] revNo,String scrapType) {  //defect 2010
		PwmroiSerial pwmroiSerial = new PwmroiSerial();
		pwmroiSerial.setOrderId(orderId);
		Map orderDetails = pwmroiSerialDao.selectOrderDetails(orderId);
		pwmroiSerial.setSalesOrderNo((String)orderDetails.get("UCF_ORDER_VCH11"));
		pwmroiSerial.setOrderNo((String)orderDetails.get("ORDER_NO"));
		String partNo = (String)orderDetails.get("PART_NO");
		Map map = null;
		if(manualNo != null)
		{
			map = pwmroiSerialDao.selectPwmroiSerialDelivery(orderId, serial[0], manualNo[0],revNo[0]);			
		}
		else
		{
			map = pwmroiSerialDao.selectPwmroiSerialScrap(orderId, serial[0], scrapType);
		}
		pwmroiSerial.setUserId((String)map.get("UPDT_USERID"));
		Date timeStamp = (Date)map.get("TIME_STAMP");
		SimpleDateFormat localDateFormat = new SimpleDateFormat("HHmmss");
		String time = localDateFormat.format(timeStamp);
		localDateFormat = new SimpleDateFormat("MMddyyyy");
		String date = localDateFormat.format(timeStamp);
		pwmroiSerial.setDate(date);
		pwmroiSerial.setTime(time);
		pwmroiSerial.setEngineManual(manualNo);
		pwmroiSerial.setEngineManualRevNo(revNo); //defect 2010
        pwmroiSerial.setSerialNo(serialNoList);
        pwmroiSerial.setSerialId(serialIdList);
        List list = authorityDao.selectOrderSubjectAuthority(orderId,manualNo);
        List<PwmroiOrderSubjectAuthority> authorityList = new ArrayList<PwmroiOrderSubjectAuthority>();
        Iterator it = list.iterator();
        while(it.hasNext())
        {
        	Map m = (Map)it.next();
        	PwmroiOrderSubjectAuthority authority = new PwmroiOrderSubjectAuthority();
        	authority.setPlanPartNo(partNo);
        	authority.setSubjectNo((Number)m.get("SUBJECT_NO"));
        	authority.setSubjectRev((Number)m.get("SUBJECT_REV"));
        	authority.setSubjectDesc((String)m.get("SUBJECT_DESCRIPTION"));
        	authority.setAuthorityType((String)m.get("AUTHORITY_TYPE"));
        	authority.setAuthorityDetail((String)m.get("AUTHORITY_DETAIL"));
        	authority.setManualSelection((String)m.get("MANUAL_SECTION"));
        	authority.setAuthorityRev((String)m.get("AUTHORITY_REV"));
        	authority.setAuthorityRevDate((Date)m.get("AUTHORITY_REV_DATE"));
        	authority.setSbPartNo((String)m.get("SB_PART_NO"));
        	authority.setAuthority((String)m.get("AUTHORITY"));
        	authorityList.add(authority);
        }
        pwmroiSerial.setPwmroiOrderSubjectAuthorityList(authorityList);
        return pwmroiSerial;
	}

	public void initiateSerialScrapPre(String orderId,
									   Number operationKey,
									   String serialList,
									   String scrapType)
	{
		String hideParameters = StringUtils.EMPTY;
		
        if (scrapType.equals(SCRAP_TYPE_RETURN_AS_IS) || scrapType.equals(SCRAP_TYPE_LOCAL_DISPOSE))
        {
        	hideParameters = "ZCOM_SALES_ORDER";
        }
        
        String parameters = "ORDER_ID=" + orderId + ",OPER_KEY=" + operationKey
        		+ ",SERIAL_LIST=" + wrapUtils.wrapValue(serialList) + ",SCRAP_TYPE=" + scrapType;
		       
        event.showUdv("PWMROI_AD07B576EDB24E288C2D2D9AC5BDE963",
        			  INSERT,
        			  "Disposition Codes",								
        			  hideParameters,
        			  parameters);
	}
	
	public void initiateSerialScrap(String orderId, Number operationKey, 
			String serialList, String scrapType, String nonRepairType,
			String category1, String code1, String primary1, 
			String category2, String code2, String primary2,
			String category3, String code3, String primary3, 
			String category4, String code4, String primary4,
			String category5, String code5, String primary5, 
			String category6, String code6, String primary6,
			String scrapText, String zcomSalesOrder)
	{	
		initiateSerialScrap2(orderId, operationKey, 
				serialList, scrapType, nonRepairType,
				category1, code1, primary1, 
				category2, code2, primary2,
				category3, code3, primary3, 
				category4, code4, primary4,
				category5, code5, primary5, 
				category6, code6, primary6,
				scrapText, zcomSalesOrder,false); //defect 1886
		
		//defect 1873 begin
		List relatedOrders = commonDaoPW.getrelatedOrders(orderId);
		ListIterator relatedOrdersIt = relatedOrders.listIterator();
		while (relatedOrdersIt.hasNext()){
			Map relatedElementsKeys = (Map) relatedOrdersIt.next();
			orderId = (String) relatedElementsKeys.get("ORDER_ID");
			operationKey = operationDao.selectOperationKey(orderId);
			String orderNo = (String) relatedElementsKeys.get("PW_ORDER_NO");
			
		//	System.out.println("initiateSerialScrap - " + orderId + " " + orderNo + " " + serialList + " " + scrapType);
			
			initiateSerialScrap2(orderId, operationKey, 
					serialList, scrapType, nonRepairType,
					category1, code1, primary1, 
					category2, code2, primary2,
					category3, code3, primary3, 
					category4, code4, primary4,
					category5, code5, primary5, 
					category6, code6, primary6,
					scrapText, zcomSalesOrder,true); //defect 1886
		} //end defect 1873
	}
		
		public void initiateSerialScrap2(String orderId, Number operationKey, 
				String serialList, String scrapType, String nonRepairType,
				String category1, String code1, String primary1, 
				String category2, String code2, String primary2,
				String category3, String code3, String primary3, 
				String category4, String code4, String primary4,
				String category5, String code5, String primary5, 
				String category6, String code6, String primary6,
				String scrapText, String zcomSalesOrder,boolean relatedOrder) {  // defect 1886
		
		
		HashMap<String, String> psMap = new HashMap<String, String>();
		psMap.put(YES, P);
		psMap.put(NO, S);
		
		StringTokenizer st = new StringTokenizer(serialList,
                								 SoluminaConstants.SEMI_COLON);
		String[] serial = null;
		String[] serialNoList = new String[st.countTokens()];
		String[] serialIdList = new String[st.countTokens()];
		int i = 0;
		while (st.hasMoreElements())
		{
			serial = st.nextToken().split(",");
			serialNoList[i] = serial[1];
			serialIdList[i] = serial[0];
			i++;
		}		
		
		primary1 = psMap.get(primary1);
		primary2 = isEmpty(category2) && isEmpty(code2) ? EMPTY : psMap.get(primary2);
		primary3 = isEmpty(category3) && isEmpty(code3) ? EMPTY : psMap.get(primary3);
		primary4 = isEmpty(category4) && isEmpty(code4) ? EMPTY : psMap.get(primary4);
		primary5 = isEmpty(category5) && isEmpty(code5) ? EMPTY : psMap.get(primary5);
		primary6 = isEmpty(category6) && isEmpty(code6) ? EMPTY : psMap.get(primary6);
		
		validateDefectCodes(category1, code1, primary1, category2, code2, primary2,
				category3, code3, primary3, category4, code4, primary4, 
				category5, code5, primary5, category6, code6, primary6);  
				
		String interfaceTransactionId = "X";
		pwmroiSerialDao.deleteDeliverScrap(orderId, serialList);
		pwmroiSerialDao.insertSerialScrap(orderId, operationKey,
				serialList, scrapType, nonRepairType,
				category1, code1, primary1, category2, code2, primary2,
				category3, code3, primary3, category4, code4, primary4,
				category5, code5, primary5, category6, code6, primary6,
				scrapText, zcomSalesOrder, interfaceTransactionId);

		String deliverSerialStatus = getSerialDeliverStatus(scrapType);		
		
		pwmroiSerialDao.updateDeliverSerialStatus(orderId, serialList, deliverSerialStatus);
		
		String workLoc = pwmroiSerialDao.selectWorkLocation(orderId);
	//	String category = pwmroiSerialDao.selectGroupCategory(STG, workLoc);
	//	String groupCode = pwmroiSerialDao.selectCode(STG, category);
		String causeCode = null;
		if (StringUtils.equals("Customer", nonRepairType))
			{
			     causeCode="CUS";
			}
		else
			{
			     causeCode="PW";
			}
		PwmroiSerial pwmroiSerial = buildPwmroiSerialObject(orderId,serialNoList,serialIdList,serial,null,null,scrapType);
		pwmroiSerial.setCondition(UNSR);
		pwmroiSerial.setQuantity(serialNoList.length);
		if (StringUtils.equalsIgnoreCase(scrapType, "Return As Is"))
			{
				scrapType="RAI";
			}
		if (StringUtils.equalsIgnoreCase(scrapType, "Local Dispose"))
		{
			scrapType="LD";
		}
		if (StringUtils.equalsIgnoreCase(scrapType, "Salvage Review"))
		{
			scrapType="SR";
		}
		pwmroiSerial.setTransType(scrapType);
		pwmroiSerial.setRevesalSapId(null);
		pwmroiSerial.setOperKey(operationKey);
		pwmroiSerial.setGroupCat(null);
		pwmroiSerial.setGroupCode(null);
		pwmroiSerial.setCauseCat(NR);
		pwmroiSerial.setCauseCode(causeCode);
		pwmroiSerial.setNonRepairType(nonRepairType);
		pwmroiSerial.setExchPt(null);
		pwmroiSerial.setZcomSalesOrder(zcomSalesOrder);
		pwmroiSerial.setZcomSaleOrderText(scrapText);
		List<PwmroiDefect> defectArea = new ArrayList<PwmroiDefect>();
		if(category1 != null && code1 != null)
		{
			PwmroiDefect pwmroiDefect = new PwmroiDefect();
			pwmroiDefect.setDefectCat(category1);
			pwmroiDefect.setDefectCode(code1);
			pwmroiDefect.setDefectPrimary(primary1);
			defectArea.add(pwmroiDefect);
		}
		if(category2 != null && code2 != null)
		{
			PwmroiDefect pwmroiDefect = new PwmroiDefect();
			pwmroiDefect.setDefectCat(category2);
			pwmroiDefect.setDefectCode(code2);
			pwmroiDefect.setDefectPrimary(primary2);
			defectArea.add(pwmroiDefect);
		}
		if(category3 != null && code3 != null)
		{
			PwmroiDefect pwmroiDefect = new PwmroiDefect();
			pwmroiDefect.setDefectCat(category3);
			pwmroiDefect.setDefectCode(code3);
			pwmroiDefect.setDefectPrimary(primary3);
			defectArea.add(pwmroiDefect);
		}
		if(category4 != null && code4 != null)
		{
			PwmroiDefect pwmroiDefect = new PwmroiDefect();
			pwmroiDefect.setDefectCat(category4);
			pwmroiDefect.setDefectCode(code4);
			pwmroiDefect.setDefectPrimary(primary4);
			defectArea.add(pwmroiDefect);
		}
		if(category5 != null && code5 != null)
		{
			PwmroiDefect pwmroiDefect = new PwmroiDefect();
			pwmroiDefect.setDefectCat(category5);
			pwmroiDefect.setDefectCode(code5);
			pwmroiDefect.setDefectPrimary(primary5);
			defectArea.add(pwmroiDefect);
		}
		if(category6 != null && code6 != null)
		{
			PwmroiDefect pwmroiDefect = new PwmroiDefect();
			pwmroiDefect.setDefectCat(category6);
			pwmroiDefect.setDefectCode(code6);
			pwmroiDefect.setDefectPrimary(primary6);
			defectArea.add(pwmroiDefect);
		}
		pwmroiSerial.setPwmroiDefect(defectArea);
		
		PWSDispositionEvent pwsDispositionEvent = new PWSDispositionEvent(this);
		pwsDispositionEvent.setPwmroiSerial(pwmroiSerial);
        
		if (!relatedOrder) { //defect 1886
			
           soluminaEventPublisher.publishEvent(pwsDispositionEvent);
		}
	}
	
	private void validateDefectCodes(String category1, String code1, String primary1, 
			String category2, String code2, String primary2, 
			String category3, String code3, String primary3, 
			String category4, String code4, String primary4, 
			String category5, String code5, String primary5, 
			String category6, String code6, String primary6)
	{
		if ((isNotEmpty(category2) && isEmpty(code2))
				|| (isNotEmpty(category3) && isEmpty(code3))
				|| (isNotEmpty(category4) && isEmpty(code4))
				|| (isNotEmpty(category5) && isEmpty(code5))
				|| (isNotEmpty(category6) && isEmpty(code6)))
			message.raiseError("MFI_20", "Code must be entered");	
		
		int primaryCount = (StringUtils.equals(primary1, P) ? 1 : 0)
				+ (StringUtils.equals(primary2, P) ? 1 : 0)
				+ (StringUtils.equals(primary3, P) ? 1 : 0)
				+ (StringUtils.equals(primary4, P) ? 1 : 0)
				+ (StringUtils.equals(primary5, P) ? 1 : 0)
				+ (StringUtils.equals(primary6, P) ? 1 : 0);
		
		if (primaryCount == 0)
			message.raiseError("MFI_20", "One of the Defect Codes must be set as Primary");
		else if (primaryCount > 1)
			message.raiseError("MFI_20", "Only one Defect Code can be set as Primary");
		
		ArrayList<Map<String, String>> defectCodes = new ArrayList<Map<String, String>>();		
		if (isNotEmpty(code1)) defectCodes.add(getDefectCodeMap (category1, code1, primary1));
		if (isNotEmpty(code2)) defectCodes.add(getDefectCodeMap (category2, code2, primary2));
		if (isNotEmpty(code3)) defectCodes.add(getDefectCodeMap (category3, code3, primary3));
		if (isNotEmpty(code4)) defectCodes.add(getDefectCodeMap (category4, code4, primary4));
		if (isNotEmpty(code5)) defectCodes.add(getDefectCodeMap (category5, code5, primary5));
		if (isNotEmpty(code6)) defectCodes.add(getDefectCodeMap (category6, code6, primary6));
		
		for (int i=0; i < defectCodes.size(); i++)
		{
			for (int j=i+1; j < defectCodes.size(); j++)
			{
				if (StringUtils.equals( defectCodes.get(i).get("CATEGORY"), defectCodes.get(j).get("CATEGORY"))
						&& StringUtils.equals(defectCodes.get(i).get("CODE"), defectCodes.get(j).get("CODE")))
				{
					message.raiseError("MFI_20", "Duplicate Entry for Category: " + defectCodes.get(i).get("CATEGORY") + ", Code: " + defectCodes.get(i).get("CODE"));
				}
			}
		}
	}

	private Map<String, String> getDefectCodeMap(String category, String code, String primary)
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("CATEGORY", category);
		map.put("CODE", code);
		map.put("PRIMARY", primary);		
		return map;
	}

	private String getSerialDeliverStatus(String scrapType)
	{
		String deliverSerialStatus = StringUtils.EMPTY;
		
		if (StringUtils.equals(scrapType, SCRAP_TYPE_RETURN_AS_IS))
		{
			deliverSerialStatus = DLVR_STAT_RETURN_AS_IS_INIT; 
		}
		else if (StringUtils.equals(scrapType, SCRAP_TYPE_LOCAL_DISPOSE))
		{
			deliverSerialStatus = DLVR_STAT_LOCAL_DISPOSE_INIT;
		}
		else if (StringUtils.equals(scrapType, SCRAP_TYPE_SALVAGE_REVIEW))
		{
			deliverSerialStatus = DLVR_STAT_SALVAGE_REVIEW_INIT;	
		}	
		
		return deliverSerialStatus;		
	}	
	
	@Override
	public PwmroiSerial selectPwmroiSerialForReply(String transactionId,String type)
	{
		PwmroiSerial pwmroiSerial = new PwmroiSerial();
		List serialList;
		if(StringUtils.equals(type, "DELIVER"))
		{
			serialList = pwmroiSerialDao.selectPwmroiSerialDeliveryDetails(transactionId);
		}
		else
		{
			serialList = pwmroiSerialDao.selectPwmroiSerialScrapDetails(transactionId);
		}
		Iterator iter = serialList.listIterator();
		String[] serialIdList = new String[serialList.size()];
		String orderId=null;
		int i = 0;
		Number operKey = null;
		while(iter.hasNext())
		{
			Map map = (Map) iter.next();
			orderId = (String)map.get("ORDER_ID");
			serialIdList[i] = (String)map.get("SERIAL_ID");
			operKey = (Number)map.get("OPER_KEY");
			i++;
		}
		pwmroiSerial.setOrderId(orderId);
		pwmroiSerial.setSerialId(serialIdList);
		pwmroiSerial.setOperKey(operKey);
		return pwmroiSerial;
	}
	
	@Override
	public void scrapUnitAndUpdateField(PwmroiSerial pwmroiSerial)
	{
		pwmroiSerialDao.updateScrapSerialStatus(pwmroiSerial.getOrderId(), pwmroiSerial.getSerialId());
		String serialNo = pwmroiSerialDao.selectSerialNo(pwmroiSerial.getOrderId(), pwmroiSerial.getSerialId());
		serial.scrapUnits(pwmroiSerial.getOrderId(),
						  pwmroiSerial.getOperKey(),
                		  null,
                		  serialNo,
                		  "SCRAP_UNIT",
                		  null);
		
	}
	public void initiateDelivery(String orderId, String serial)
	{
		
		
	    String tag="FROM_TAB";
		int manualCount = pwmroiSerialDao.selectEngineManualCount(orderId);
		if (manualCount > 0)
		{
			 String parameters = "ORDER_ID=" + orderId + ",SERIAL=" + wrapUtils.wrapValue(serial)+ ",TAG="+tag;
             event.showUdv("PWMROI_8277C598F36145ACADEEE30656393896",
            		 		INSERT,
            		 		"Engine Manuals",								
            		 		null,
            		 		parameters);
		}
		else
		{   
			this.initiateSerialDelivery(orderId, serial, "N/A,N/A");

		}
	}
	
	//Defect 1862 & 1868
		public void reverseDeliveredSerialNo(String serialNoList)
		{
			String order_id, serial_id, engine_manual, lot_id, serial_oper_status = null, serial_status;
			String buyoff_status, part_no, lot_no, serial_no, scrap_flag, order_status, buyoff_id;
			Number oper_key, step_key;
			Date actual_start_date;
			
			StringTokenizer st = new StringTokenizer(serialNoList, SoluminaConstants.SEMI_COLON);
			
			while(st.hasMoreElements())
			{
				//create list from dao
				List serialNoInfoList = pwmroDao.selectSerialNoInfo(st.nextToken());
				
				//create iterator
				Iterator iter = serialNoInfoList.listIterator();
				while(iter.hasNext())
				{
					//iterate thru map
					Map map = (Map) iter.next();
					order_id = (String)map.get("ORDER_ID");
					serial_id =(String)map.get("SERIAL_ID");
					part_no = (String)map.get("PART_NO");
					lot_no = (String)map.get("LOT_NO");
					serial_no = (String)map.get("SERIAL_NO");
					scrap_flag = (String)map.get("SCRAP_FLAG");
					order_status = (String)map.get("ORDER_STATUS");
					lot_id = (String)map.get("LOT_ID");
					
					serial_status = (String)map.get("SERIAL_STATUS");
						
					//insert
					pwmroDao.insertReversedDeliveredSNHist(order_id, serial_id);
					
					//delete
					engine_manual = pwmroDao.selectEngineManual(order_id, serial_id);
					pwmroDao.deleteDeliveredSN(order_id, serial_id, engine_manual);
						
					if(StringUtils.equals("COMPLETE", serial_status))
					{
						//update
						pwmroDao.updateSerialStatusToInProcess(order_id, serial_id, lot_id);
					}
						
					//if order_status == SCRAP
					if(StringUtils.equals("SCRAP", serial_status))
					{
						
						if(StringUtils.equals("CLOSE", order_status))  
						{
							//pwmroDao.updateOrderStatusCompleteToActive(order_id);
							  pwmroDao.updateOrderStatusToActive(order_id);						
						}
						
						pwmroDao.updateScrapToInProcess(part_no, lot_no, serial_no, scrap_flag);
						pwmroDao.updateSerialStatusToInProcess(order_id, serial_id, lot_id);
						pwmroDao.deletePWMROISerialScrap(order_id, serial_id);
						
						
						// change this to get list of operation keys
						List operList = pwmroDao.selectOperKey(order_id, serial_id);
						
						Iterator iter1 = operList.listIterator();
						
						while(iter1.hasNext())
						{
							Map m = (Map) iter1.next();
							oper_key = (Number)m.get("OPER_KEY");
							serial_oper_status = (String)m.get("SERIAL_OPER_STATUS");
							actual_start_date = (Date)m.get("ACTUAL_START_DATE");
								
							List lastBuyoff = pwmroDao.selectLastBuyoffStatus(serial_id,order_id, oper_key);
							Iterator iter2 = lastBuyoff.listIterator();
							while(iter2.hasNext())
							{
								Map buyOffMap = (Map) iter2.next();
								buyoff_status = (String)buyOffMap.get("BUYOFF_STATUS");
								step_key = (Number)buyOffMap.get("STEP_KEY");
								buyoff_id = (String)buyOffMap.get("BUYOFF_ID");
										
								//scrap serial_oper_status cancel change to inqueue
								if(StringUtils.equals("CANCEL", serial_oper_status))
								{
								
									pwmroDao.updateSerialOperStatusToActive(order_id, serial_id, step_key);
									pwmroDao.updateOperStatusToActive(order_id, oper_key);
									
									/*if(actual_start_date == null)//change to actual start date
									{
										pwmroDao.updateSerialOperStatusToInQueue(order_id, serial_id, step_key);
										pwmroDao.updateOperStatusToInQueue(order_id, oper_key);
									}
									else 
									{
										pwmroDao.updateSerialOperStatusToActive(order_id, serial_id, step_key);
										pwmroDao.updateOperStatusToActive(order_id, oper_key);
									}*/
									
									if(StringUtils.equals("ACCEPT", buyoff_status))//if buyoff oper is ACCEPT change to OPEN
									{
									
										//REOPEN LAST BUYOFF IN EACH OP IF LAST BUYOFF HAS ACCEPT STATUS -when CANCEL (SCRAP)
										pwmroDao.updateBuyoffStatusToOpen(order_id, oper_key, step_key, buyoff_id, serial_id, buyoff_status);
									}
									
									//WHEN CANCEL (SCRAP) - OR PENDING, IN QUEUE, COMPLETE, STOP
									pwmroDao.updateScrapToInProcess(part_no, lot_no, serial_no, scrap_flag);
								}
						
								//serial_oper_status completed change to active
								if(StringUtils.equals("COMPLETE", serial_oper_status))
								{
									pwmroDao.updateSerialOperStatusToActive(order_id, serial_id, step_key);
									
									pwmroDao.updateOperStatusToActive(order_id, oper_key);
							
									pwmroDao.selectLastBuyoffStatus(serial_id,order_id, oper_key);
								
									//REOPEN LAST BUYOFF IN EACH OP IF LAST BUYOFF HAS ACCEPT STATUS -when CANCEL (SCRAP)
									pwmroDao.updateBuyoffStatusToOpen(order_id,oper_key, step_key, buyoff_id, serial_id, buyoff_status);
								}
							}
						}	
					}	
				}
					
			}
			
		}	
}