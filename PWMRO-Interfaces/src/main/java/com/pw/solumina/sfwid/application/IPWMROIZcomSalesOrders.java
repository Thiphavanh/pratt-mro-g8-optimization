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
*  File:    IPWMROIZcomSalesOrders.java
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

package com.pw.solumina.sfwid.application;

public interface IPWMROIZcomSalesOrders
{
    public void insertZcomSalesOrder(String workLocation,
                                     String zComSalesOrder,
                                     String description,
                                     String soldTo);

    void updateZcomSalesOrder(String workLocation,
                              String zComSalesOrder,
                              String description,
                              String soldTo,
                              String obsoleteFlag);

    void deleteZcomSalesOrder(String workLocation, String zComSalesOrder);
}
