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
 *  File:    IHoldDaoPW.java
 * 
 *  Created: 2017-08-30
 * 
 *  Author:  Catrina Nguyen
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2017-11-23		C. Nguyen		Initial Release SMRO_QA_201 - Discrepancy
 *  								Extention of ibaset IHoldDao
 *  2018-01-09		C. Nguyen		Add method selectOrderId to get the OrderId based on the DiscId and DiscLineNo
 */

package com.pw.solumina.sfwid.dao;


import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ibaset.class
 */

public interface IHoldDaoPW {

    /**
     * 
     * @param orderId
     * @param Ref1
     * @param Ref2
     * @return
     */
    public boolean selectHoldExistsForOrder( String orderId,
			 							  String Ref1,
			 							  String Ref2,
			 							  String stopType,
			 							  String holdType);
    
    
    /**
     * 
     * @param orderId
     * @param discId
     * @param discLineNo
     * @return
     */
    public List getHoldList( String orderId,
			 				 String discId,
			 				 String discLineNo,
							  String stopType,
							  String holdType);
    
    /**
     * 
     * @param DiscId
     * @param Disc Line No
     * @return
     */
    public String selectOrderId( String discId,
			  Number discLineNo);
    

}
