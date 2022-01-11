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
 *  File:    IModuleSwapDaoPW.java
 * 
 *  Created: 2018-02-05
 * 
 *  Author:  Catrina Nguyen
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2018-02-05		C. Nguyen		Initial Release SMRO_TR_T226 - Module Swaps Interface
 *  2018-05-18		C. Nguyen		Added logic to process swaps information
 *  2018-07-09		C. Nguyen		Added a few methods process swaps
 *  								
 */

package com.pw.solumina.sfwid.dao;

import java.util.List;

public interface IModuleSwapDaoPW {
	
	public void insertModuleSwapsDef(String salesOrder,
			String dokNo,
			String cfgModuleCode,
			String serialNo, 
			String custSerialNo, 
			String updtUserId, 
			String updtTime, 
			String messageAction);
	
	public List getPwustModuleSwapsLogColumnLenghts();
	
	public String checkExsitsPwustOrders(String salesOrder, String engineModel);
	
	public String checkExsitsPwustOrders(String orderId);
	
	public List getExsitsPwustSalesOrder(String salesOrder, String moduleCode);

	public void insertPwustOrders(String salesOrder,
			String engineModel,
			String workScope,
			String prevSalesOrder, 
			String prevEngineModel,
			String updtUserId);
	
	public void updatePwustOrders(String salesOrder,
			String prevSalesOrder, 
			String prevEngineModel,
			String updtUserId);
	
	public void updatePwustOrders(String salesOrder,
			String orderId, 
			String updtUserId);
	
	public void deleteOutgoingSalesOrder(String salesOrder,
			String engineModel);
	
	public void updateOutgoingSalesOrder(String salesOrder,
			String engineModel, String outgoingSO);
	
	public void updateOutgoingSalesOrder(String orderId, String outgoingSO);
	
	public void updateOutgoingSalesOrder(String orderId, String outgoingSO, String serialNo, String dummy);
	
	public String getSalesOrder( String moduleCode, String serialNo);		//String dokNo,
	
	public List getOrderId( String serialNo);
	
	public List getOrderId( String serialNo, String modCode);
	
	public List getOrderId(  String modCode, String salesOrder, String dummy);

}
