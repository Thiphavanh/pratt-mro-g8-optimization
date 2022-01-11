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
*  File:   PWMROIZcomSalesOrderImpl.java
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
*/

package com.pw.solumina.sfwid.application.impl;

import org.springframework.dao.DataIntegrityViolationException;

import com.ibaset.common.Reference;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.pw.solumina.sfwid.application.IPWMROIZcomSalesOrders;
import com.pw.solumina.sfwid.dao.IPWMROZcomSalesOrdersDao;

import static com.ibaset.common.SoluminaConstants.YES;

public class PWMROIZcomSalesOrderImpl implements IPWMROIZcomSalesOrders
{
    @Reference
    private IPWMROZcomSalesOrdersDao zcomSalesOrdersDao;

    @Reference
    IMessage message;

    @Override
    public void insertZcomSalesOrder(String workLocation,
                                     String zComSalesOrder,
                                     String description,
                                     String soldTo)
    {
        try
        {
            zcomSalesOrdersDao.insertZcomSalesOrder(workLocation,
                                                    zComSalesOrder,
                                                    description,
                                                    soldTo);
        }
        catch (DataIntegrityViolationException e)
        {
            //Zcom Sales order %V1 already exist at work location %V2
            message.raiseError("PWMROI_CDB044D6B456447EB78589E79A9105AC",
                               zComSalesOrder,
                               workLocation);
        }

    }

    @Override
    public void updateZcomSalesOrder(String workLocation,
                                     String zComSalesOrder,
                                     String description,
                                     String soldTo,
                                     String obsoleteFlag)
    {
        zcomSalesOrdersDao.updateZcomSalesOrder(workLocation,
                                                zComSalesOrder,
                                                description,
                                                soldTo,
                                                obsoleteFlag);
    }

    @Override
    public void deleteZcomSalesOrder(String workLocation, String zComSalesOrder)
    {
    	boolean isReferenced = zcomSalesOrdersDao.isZcomSalesOrderReferencedInScrap(zComSalesOrder);
    	if (isReferenced)
    	{
    		zcomSalesOrdersDao.obsoleteZcomSalesOrder(workLocation, zComSalesOrder);
    		message.showMessage("MFI_53688");
    	}
    	else
    	{
    		zcomSalesOrdersDao.deleteZcomSalesOrder(workLocation, zComSalesOrder);
    	}
    }    
}