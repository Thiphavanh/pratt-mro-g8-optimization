/*
 * This unpublished work, first created in 2017 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2017, 2018, 2019, 2020.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    UtasSerialDaoImplExt.java
 * 
 *  Created: 2017-08-08
 * 
 *  Author:  c079222
 * 
 *  Revision History
 * 
 *  Date      	Who                	Description
 *  ----------	---------------	---------------	---------------------------------------------------
 * ? 	?		    Initial Release 
 * 12-11-2020   John DeNinno        Defect 1834 add code that checks the last buyoff for hold
 * */
package com.utas.solumina.sfwid.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.solumina.sfwid.dao.ISerialDao;
import com.pw.common.dao.CommonDaoPW;
import com.utas.solumina.common.UtasConstants;

public abstract class UtasSerialDaoImplExt extends ImplementationOf<ISerialDao> implements ISerialDao 
{

	@Reference
	private JdbcDaoSupport dao;

	@Reference  private ExtensionDaoSupport eds;
	@Reference private CommonDaoPW commonDaoPW;



	@Override
	public void insertOperationItems(String operationNumber,
			String stepNumber,
			String orderId,
			Number operationKey,
			String referenceDesignator,
			Number stepKey,
			String itemId,
			Number plannedItemQuantity,
			String userName,
			String alterationId,
			Number alterationCount,
			String partNumber,
			String partChange,
			String partAction,
			String findNumber,
			Number referenceDesignatorPreferenceRank,
			String itemNotes,
			String serialFlag,
			String lotFlag,
			String expirationFlag,
			String spoolFlag,
			String optionalDataCollectionFlag1,
			String optionalDataCollectionFlag2,
			String optionalDataCollectionFlag3,
			String optionalDataCollectionFlag4,
			String ucfPlanItemVch1,
			String ucfPlanItemVch2,
			String ucfPlanItemVch3,
			String ucfPlanItemVch4,
			String ucfPlanItemVch5,
			String ucfPlanItemFlag1,
			String ucfPlanItemFlag2,
			Number ucfPlanItemNumber1,
			Number ucfPlanItemNumber2,
			String overConsumptionFlag,
			String orientationFlag,
			String crossOrderFlag,
			String itemCategory,
			String storeLocation,
			String unloadingPoint,
			String optionalFlag,
			String suspectFlag,
			String referenceId,
			String blockId,
			String ucfPlanItemVch6,
			String ucfPlanItemVch7,
			String ucfPlanItemVch8,
			String ucfPlanItemVch9,
			String ucfPlanItemVch10,
			String ucfPlanItemVch11,
			String ucfPlanItemVch12,
			String ucfPlanItemVch13,
			String ucfPlanItemVch14,
			String ucfPlanItemVch15,
			Number ucfPlanItemNumber3,
			Number ucfPlanItemNumber4,
			Number ucfPlanItemNumber5,
			Date ucfPlanItemDate1,
			Date ucfPlanItemDate2,
			Date ucfPlanItemDate3,
			Date ucfPlanItemDate4,
			Date ucfPlanItemDate5,
			String ucfPlanItemFlag3,
			String ucfPlanItemFlag4,
			String ucfPlanItemFlag5,
			String ucfPlanItemVch2551,
			String ucfPlanItemVch2552,
			String ucfPlanItemVch2553,
			String ucfPlanItemVch40001,
			String ucfPlanItemVch40002,
			String uom,
			String removeAction,
			String utilizationRule,
			String isTrackable,
			String uidEntryName,
			String uidItemFlag,
			String partDataCollectionId,
			Number displayLineNumber,
			String securityGroup,
			String standardPartFlag,
			String dispositionPartDatColId, 
			String bomLineNumber,
			String phantomParent,
			String bomCompId,
			String replacementPartNo,
			String replacementPartChg)
	{
		securityGroup = inheritOrderSecurityGroup(securityGroup);

		Super.insertOperationItems(operationNumber,
				stepNumber,
				orderId,
				operationKey,
				referenceDesignator,
				stepKey,
				itemId,
				plannedItemQuantity,
				userName,
				alterationId,
				null,
				partNumber,
				partChange,
				partAction,
				findNumber,
				referenceDesignatorPreferenceRank,
				itemNotes,
				serialFlag,
				lotFlag,
				expirationFlag,
				spoolFlag,
				optionalDataCollectionFlag1,
				optionalDataCollectionFlag2,
				optionalDataCollectionFlag3,
				optionalDataCollectionFlag4,
				ucfPlanItemVch1,
				ucfPlanItemVch2,
				ucfPlanItemVch3,
				ucfPlanItemVch4,
				ucfPlanItemVch5,
				ucfPlanItemFlag1,
				ucfPlanItemFlag2,
				ucfPlanItemNumber1,
				ucfPlanItemNumber2,
				overConsumptionFlag,
				orientationFlag,
				crossOrderFlag,
				itemCategory,
				storeLocation,
				unloadingPoint,
				optionalFlag,
				suspectFlag,
				referenceId,
				blockId,
				ucfPlanItemVch6,
				ucfPlanItemVch7,
				ucfPlanItemVch8,
				ucfPlanItemVch9,
				ucfPlanItemVch10,
				ucfPlanItemVch11,
				ucfPlanItemVch12,
				ucfPlanItemVch13,
				ucfPlanItemVch14,
				ucfPlanItemVch15,
				ucfPlanItemNumber3,
				ucfPlanItemNumber4,
				ucfPlanItemNumber5,
				ucfPlanItemDate1,
				ucfPlanItemDate2,
				ucfPlanItemDate3,
				ucfPlanItemDate4,
				ucfPlanItemDate5,
				ucfPlanItemFlag3,
				ucfPlanItemFlag4,
				ucfPlanItemFlag5,
				ucfPlanItemVch2551,
				ucfPlanItemVch2552,
				ucfPlanItemVch2553,
				ucfPlanItemVch40001,
				ucfPlanItemVch40002,
				uom,
				removeAction,
				utilizationRule,
				isTrackable,
				uidEntryName,
				uidItemFlag,
				partDataCollectionId,
				displayLineNumber,
				securityGroup,
				standardPartFlag,
				dispositionPartDatColId, 
				bomLineNumber,
				phantomParent,
				bomCompId,
				replacementPartNo,
				replacementPartChg);
	}

	private String inheritOrderSecurityGroup(String securityGroup) 
	{
		String orderSecurityGroup = (String)ContextUtil.getUser().getContext().get("ORDER_SECURITY_GROUP");
		if(StringUtils.isNotEmpty(orderSecurityGroup))
		{
			String planSecurityGroupArr[] = orderSecurityGroup.split(",");
			String itemSecurityGroupArr[] = StringUtils.isEmpty(securityGroup) ? null : securityGroup.split(",");
			if(itemSecurityGroupArr != null && itemSecurityGroupArr.length > 0)
			{
				boolean securityGroupContain = false;
				for(int i = 0; i < itemSecurityGroupArr.length; i++)
				{
					for(int j = 0; j < planSecurityGroupArr.length; j++)
					{
						if(StringUtils.equals(planSecurityGroupArr[j], itemSecurityGroupArr[i]))
						{
							securityGroupContain = true;
							break;
						}
					}
				}
				if(!securityGroupContain)
				{
					securityGroup = securityGroup + "," + orderSecurityGroup;
				}
			}
			else
			{
				securityGroup = orderSecurityGroup;
			}
		}
		return securityGroup;
	}

	//defect 1834 begin
	@Override
	public boolean selectHoldExists(String orderId, String stopType) {
		boolean holdExists = false;

		holdExists = Super.selectHoldExists(orderId, stopType);

		if (!commonDaoPW.isLastActiveBuyoffInAllOperations(orderId)) {
			holdExists = false;
		}

		return holdExists;

	} //end defect 1834



}
