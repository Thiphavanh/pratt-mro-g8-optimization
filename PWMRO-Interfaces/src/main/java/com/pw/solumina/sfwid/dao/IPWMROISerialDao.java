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
*  File:   IPWMROISerialDao.java
* 
*  Created: 2020-01-24
* 
*  Author:  X005703
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2019-12-10 		X005703		    Initial Release IBaset delivery cockpit
* 01-24-2020  		D.Sibrian		Updated for T314 Interface messaging
* 2021-08-24        X006881         Defect 1862 & 1868
* 2021-10-29        J DeNinno		Defect 2010 T314 change
*/

package com.pw.solumina.sfwid.dao;

import java.util.List;
import java.util.Map;

public interface IPWMROISerialDao
{
    List getInCompleteSerialsFromOtherOrder(String orderId,
                                           String serialNo,
                                           String lotNo);

    void insertSerialDelivery(String orderId,
                              String serialNos,
                              String manualNos,
                              String interfaceTrxId);
    
    void updateDeliverSerialStatus(String orderId, String serialNos, String deliverStatus);
    
    public void insertSerialScrap(String orderId, Number operationKey,
    		String serialIdList, String scrapType, String nonRepairType,
    		String category1, String code1, String primary1,
    		String category2, String code2, String primary2,
    		String category3, String code3, String primary3,
    		String category4, String code4, String primary4,
    		String category5, String code5, String primary5,
    		String category6, String code6, String primary6,
    		String scrapText, String zcomSalesOrder, String interfaceTrxId);
    public Map selectOrderDetails(String orderId);
    
    public Map selectPwmroiSerialDelivery(String orderId,String serialId,String engineManual,String manualRev); //defect 2010
    
    public Map selectPwmroiSerialScrap(String orderId,String serialId,String scrapType);
    
    public String selectWorkLocation (String orderId);
    
    public String selectGroupCategory(String catalog,String workLoc);
    
    public String selectCode(String catalog,String category);
    
    public void updateTransIDForScrap(String orderId,Number operKey,String[] serialIdList,String transactionId);
    
    public void updateTransIDForDelivery(String orderId,String[] serialIdList,String transactionId);
    
    public List selectPwmroiSerialDeliveryDetails(String interFaceTransactionId);
    
    public List selectPwmroiSerialScrapDetails(String interFaceTransactionId);
    
    public void updateDeliverSerialStatus(String orderId,String[] serialIdList);
    
    public void updateScrapSerialStatus(String orderId,String[] serialIdList);
    
    public String selectSerialNo(String orderId, String[] serialIdList);

	public void updateErrorMessageForDelivery(String transactionId, String errorMessage);
	
	public void updateErrorMessageForScrap(String transactionId, String errorMessage);
	
	public int selectEngineManualCount(String orderId);
	
	public void deleteDeliverSerial(String orderId,String serialIdList);
	
	public void deleteDeliverScrap(String orderId,String serialIdList);
	
	//Defect 1862 & 1868
	public void insertReversedDeliveredSNHist(String order_id, String serial_id);
		
	public void deleteDeliveredSN(String order_id, String serial_id, String engine_manual);
		
	public void updateSerialStatus(String order_id, String serial_id, String lot_id);
		
	public void updateOrderStatusCompleteToActive(String order_id);
		
	public void deletePWMROISerialScrap(String order_id, String serial_id);
		
	public void updateOperStatusToActive (String order_id, Number oper_key);
		
	public void updateOperStatusToInQueue(String order_id, Number oper_key);
	//End Defect 1862 & 1868
}
