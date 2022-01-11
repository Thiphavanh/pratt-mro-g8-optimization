/*
 *  This unpublished work, first created in 2020 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2018.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    SerialOperationImplPW.java
 * 
 *  Created: 2020-08-20
 * 
 *  Author:  John DeNinno
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2020-08-20		J DeNinno		Initial Release defect 1711
*/
package com.pw.solumina.sfwid.application.impl;

import static com.ibaset.common.FrameworkConstants.YES;
import static com.ibaset.common.SoluminaConstants.ACTIVE_STATUS;
import static com.ibaset.common.SoluminaConstants.CLOSE;
import static com.ibaset.common.SoluminaConstants.INSERT;
import static com.ibaset.common.util.SoluminaUtils.stringEquals;
import static org.apache.commons.lang.math.NumberUtils.INTEGER_MINUS_ONE;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.common.util.LanguageUtils;
import com.ibaset.solumina.pdf.arch.application.IOrderArchiving;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sfwid.application.ISerialOperation;
import com.ibaset.solumina.sfwid.dao.IOrderDao;
import com.pw.common.dao.CommonDaoPW;
import com.ibaset.solumina.sffnd.application.IWrapUtils;
import org.apache.commons.lang3.StringUtils;

public class SerialOperationImplPW  {

	@Reference
	private IOrderArchiving orderArchivingImpl;

	@Reference
	private IOrderDao orderDao;

	@Reference
	private IMessage message;

	@Reference
	private IEvent event;

	@Reference
	private IWrapUtils wrapUtils;

	@Reference private CommonDaoPW commonDaoPW;




	public void reopenOperationPre(String orderId, String hideColumns, String parameters, Number operKey) {


		orderArchivingImpl.raiseErrorIfOrderArchivingIsStarted(orderId);    //GE-8182

		if(operKey != null)
		{
			Map operInfo = orderDao.selectOperationInformation(orderId, operKey, INTEGER_MINUS_ONE);
			String operationStatus = operInfo.get("OPER_STATUS").toString();
			if(!stringEquals(operationStatus, CLOSE) 
					&& !stringEquals(operationStatus, ACTIVE_STATUS))
			{
				// Operation status must be ACTIVE or CLOSE for reopen operation
				message.raiseError("MFI_72608FAAC9B84F519143184DA3986304");
			}
		}
		List<String> serials = commonDaoPW.getSerialNumbers(orderId, operKey);

		String serialNoString="";
		ListIterator serialIt = serials.listIterator();
		while (serialIt.hasNext()){
			Map operationKeys = (Map) serialIt.next();
			serialNoString = serialNoString + (String) operationKeys.get("SERIAL_NO") + ";";
		}
		
        
		String orderNo = orderDao.selectOrderNumberByOrderId(orderId);
		StringBuffer params = new StringBuffer();
		params.append(parameters)
		.append(",SERIAL_NO=")
		.append( serialNoString);
		
		/*params.append(",ORDER_ID=")
		.append(wrapUtils.wrapValue(orderId))
		.append(",OPER_KEY=")
		.append(wrapUtils.wrapValue(operKey))
		.append(",SERIAL_NO=")
		.append(wrapUtils.wrapValue(serials.toString()));*/


		if ( orderDao.selectOrderLocked(orderId).equals(YES) )
		{
			// This order has been locked and cannot be restarted:
			message.raiseError("MFI_519AE61E97243965E0440003BA041A64", orderNo);
		}
		else
		{
			event.showUdv("PWUST_98BCBF588622271FE05387971F0A018D", INSERT,
					LanguageUtils.getMessage("reopen.operation"), // GE-7326
					hideColumns, params.toString());
		}

	}



}
