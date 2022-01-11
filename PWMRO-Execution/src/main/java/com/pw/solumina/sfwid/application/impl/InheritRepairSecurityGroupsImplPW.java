/*
 *  This unpublished work, first created in 2020 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2019.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    InheritRepairSecurityGroupsImplPW.java
 * 
 *  Created: 2020-10-30
 * 
 *  Author:  D. Sibrian
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2019-05-17		D. Sibrian		Defect 1809 - Stop security groups from being inherited from part items if order is a repair oder  
 */

package com.pw.solumina.sfwid.application.impl;

import static com.ibaset.common.SoluminaConstants.COPY;
import static com.ibaset.common.SoluminaConstants.ITEM;
import static com.ibaset.common.SoluminaConstants.MRO_PART;
import static com.ibaset.common.SoluminaConstants.PLAN;
import static com.ibaset.common.SoluminaConstants.REWORK;
import static com.ibaset.common.util.SoluminaUtils.stringEquals;
import static org.apache.commons.lang.StringUtils.equalsIgnoreCase;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

import com.ibaset.common.ImplementationOf;
import com.ibaset.solumina.sfwid.application.IInheritSecurityGroup;

public abstract class InheritRepairSecurityGroupsImplPW extends ImplementationOf<IInheritSecurityGroup> implements IInheritSecurityGroup {
	
	public void inheritWorkOrderSecurityGroup(String itemNo, 
    										  String parentOrderId, String planPlanId,
    										  String planOrderNumber, 
    										  Number planPlanUpdateNumber, 
    										  String newOrderType, 
    										  String instructionsType,
    										  String parentOrderNumber) {
        // Inherits Security Groups from item master
        if (isNotEmpty(parentOrderId) && !equalsIgnoreCase(newOrderType, REWORK))
        {
        	inheritOrderSecurityGroups(planOrderNumber, parentOrderNumber, COPY, null, null);
        } else {
        	inheritOrderSecurityGroups(planOrderNumber, planPlanId, PLAN, null, planPlanUpdateNumber);
            // FND-28325
            if (stringEquals(instructionsType, MRO_PART) && !"REPAIR".equals(newOrderType))
            {
            	inheritOrderSecurityGroups(planOrderNumber, itemNo, ITEM, null, planPlanUpdateNumber);
            }
        }
    }

}
