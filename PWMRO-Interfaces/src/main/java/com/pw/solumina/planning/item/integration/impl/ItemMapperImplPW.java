package com.pw.solumina.planning.item.integration.impl;
/*
 *  This unpublished work, first created in 2017 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2017, 2019, 2020,2021.  All rights reserved.  Information contained 
 *  herein is proprietary and confidential and improper use or disclosure 
 *  may result in civil and penal sanctions.
 *
 *  File:    ItemMapperImplPW.java
 * 
 *  Created: October 24, 2017 
 * 
 *  Author:  B. Corson
 * 
 *  Revision History
 * 
 *  Date      	Who                	Description
 *  ----------	---------------	------------------------------------------------------------------
 * 2017-10-24	B. Corson       SMRO_MD_204 Clone map() of ItemMapperImplPW from AutoAir.
 * 2019-03-01   D.Miron         Updated for G8R2SP4
 * 2019-06-04   B. Corson       SMRO_MD_304 Add Security Groups for Work Locations.
 * 2020-03-30   D. Miron        SMRO_MD_304 Defect 1612: Removed NONE work location, changed UOM and item subtype to MATERIAL.
 * 2020-05-21	D. Miron        SMRO_MD_204 Defect 1665 - Moved code to set Item Revision to N/A to OAGIUtilsImplPW
 * 2020-10-23	John DeNinno    Defect 1795 if the UOM exists. leave it to what it was in the part
 * 2021-02-17   John DeNinno    Defect 1861 change flag to be false only on new parts
 * 2021-06-03   D. Miron        Defect 1901 Rewrite to better handle existing parts and obsolete work locations.
 * 2021-06-03   D. Miron        Defect 1901 Rewrite to better handle existing parts and obsolete work locations.
 * 2021-06-07   D. Miron        Defect 1901 Moved getItemSubtype and workLocObsolete to ItemDaoPW.
 */

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Iterator;

import org.h2.util.StringUtils;
import org.openapplications.oagis.x9.FacilityType;             // 2019-06-04 - SMRO_MD_304
import org.openapplications.oagis.x9.ItemHeaderType;
import org.openapplications.oagis.x9.ItemIDType;
import org.openapplications.oagis.x9.ItemLocationType;         // 2019-06-04 - SMRO_MD_304
import org.openapplications.oagis.x9.ItemMasterType;
import org.openapplications.oagis.x9.NameValuePairType;        // 2019-06-04 - SMRO_MD_304
import org.openapplications.oagis.x9.PropertyType;             // 2019-06-04 - SMRO_MD_304
import org.openapplications.oagis.x9.SpecificationType;        // 2019-06-04 - SMRO_MD_304
import org.openapplications.oagis.x9.UOMCodeType;              // Defect 1612
import org.openapplications.oagis.x9.SequencedCodeType;        // Defect 1612

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.solumina.integration.common.MappingAction;
import com.ibaset.common.util.OAGIUtils; // G8R2SP4
import com.ibaset.solumina.oagis.ItemLocationUserAreaType;
import com.ibaset.solumina.oagis.ItemMasterHeaderUserAreaType;
import com.ibaset.solumina.oagis.UserConfigurableFieldType;
import com.ibaset.solumina.planning.item.Item;
import com.ibaset.solumina.planning.item.integration.ItemMapper;
import com.ibaset.solumina.sfpl.dao.IItemDao;
import com.ibaset.solumina.planning.item.ItemId;
import com.pw.common.dao.CommonDaoPW;

import com.pw.solumina.interfaces.dao.UserDaoPW;               // 2019-06-04 - SMRO_MD_304
import com.pw.solumina.sfpl.dao.ItemDaoPW;

public abstract class ItemMapperImplPW extends ImplementationOf<ItemMapper> implements ItemMapper {
    @Reference private ItemDaoPW   itemDaoPW;
	@Reference  private OAGIUtils oagiUtils = null;  // G8R2SP4
    @Reference private UserDaoPW   userDaoPW = null;           // 2019-06-04 - SMRO_MD_304
    @Reference private IItemDao itemDao = null;
    @Reference  private ExtensionDaoSupport eds; // DEFECT 1795
    @Reference private CommonDaoPW commonDaoPW;
    
	// begin inbound methods
	@SuppressWarnings({ "rawtypes"})
	public boolean map(MappingAction[] actions, Item destinationPart, List ecnList) throws Exception
	{
		boolean wasDeleted = false;
		String itemId      = "";	      	  	
		itemId             = destinationPart.getItemId();
		Number itemNumber1 = itemDaoPW.getItemNumber1(itemId);
		String uom = null;
		String itemSubtype = null;

		if(itemNumber1 != null)
		{
			MappingAction partAction = actions[0];	  	    	
			Object sourceObject = partAction.getSourceObject();
			destinationPart.changeItemNumber1((BigDecimal) itemNumber1);

			if (sourceObject instanceof ItemMasterType)
			{                  
				ItemHeaderType itemHdrType = ((ItemMasterType) sourceObject).getItemMasterHeader();
				ItemMasterHeaderUserAreaType itemMasterHeaderUserArea = (ItemMasterHeaderUserAreaType) oagiUtils.getObjectFromAnyTypeByType(itemHdrType.getUserArea(),ItemMasterHeaderUserAreaType.type);
				UserConfigurableFieldType userConfigurableFieldType =  itemMasterHeaderUserArea.getUserConfigurableFields();

				if (userConfigurableFieldType != null) 
				{
					oagiUtils.addUserConfigurableNumberType(userConfigurableFieldType,
							new BigDecimal(itemNumber1.intValue()),
							"ItemNumber1",
							"ItemNumber1");   
				} 
			}
		} 

		// 2019-06-04 - SMRO_MD_304 Begin
		MappingAction partAction = actions[0];
		Object sourceObject = partAction.getSourceObject();
		if (sourceObject instanceof ItemMasterType)
		{
			ItemHeaderType itemHdrType = ((ItemMasterType) sourceObject).getItemMasterHeader();
			ItemId partId = null;

			for (Iterator iterator = itemHdrType.getItemIDList().iterator(); iterator.hasNext();)
			{
				partId = this.getPartId((ItemIDType) iterator.next());

				String partNo = partId.getItemNumber();
				String partRevision = partId.getItemRevision();

				Map uomMap = itemDaoPW.getItemSubtype(partNo, partRevision); 

				//if the part already exists in the part table use the one in the part table
				if(uomMap == null) {
				
					uom = "MATERIAL";
					itemSubtype = "MATERIAL";

					// Set Item Subtype to MATERIAL
					// Defect 1612
					SequencedCodeType seqCodeType = (SequencedCodeType) itemHdrType.getClassificationArray(0).getCodes().getCodeArray(0);
					seqCodeType.setStringValue("MATERIAL");
					seqCodeType.setSequence(BigInteger.valueOf(1));

					UOMCodeType uomCodeType = (UOMCodeType) itemHdrType.getStorageUOMCode();           
					uomCodeType.setStringValue("MATERIAL");
					itemHdrType.setSerialControlIndicator(false);

					ItemMasterHeaderUserAreaType itemMasterHeaderUserArea = (ItemMasterHeaderUserAreaType) oagiUtils.getObjectFromAnyTypeByType(itemHdrType.getUserArea(),ItemMasterHeaderUserAreaType.type);
					itemMasterHeaderUserArea.setComponentSerialControlIndicator(false);
				}	
				else
				{
					uom = (String)uomMap.get("STOCK_UOM");
					itemSubtype = (String)uomMap.get("ITEM_SUBTYPE");

					if("MATERIAL".equals(uom) && "MATERIAL".equals(itemSubtype)) 
					{
						SequencedCodeType seqCodeType = (SequencedCodeType) itemHdrType.getClassificationArray(0).getCodes().getCodeArray(0);
						seqCodeType.setStringValue("MATERIAL");
						seqCodeType.setSequence(BigInteger.valueOf(1));

						UOMCodeType uomCodeType = (UOMCodeType) itemHdrType.getStorageUOMCode();           
						uomCodeType.setStringValue("MATERIAL");
						itemHdrType.setSerialControlIndicator(false);

						ItemMasterHeaderUserAreaType itemMasterHeaderUserArea = (ItemMasterHeaderUserAreaType) oagiUtils.getObjectFromAnyTypeByType(itemHdrType.getUserArea(),ItemMasterHeaderUserAreaType.type);
						itemMasterHeaderUserArea.setComponentSerialControlIndicator(false);
					}
					else if("MATERIAL".equals(itemSubtype)) 
					{
						SequencedCodeType seqCodeType = (SequencedCodeType) itemHdrType.getClassificationArray(0).getCodes().getCodeArray(0);
						seqCodeType.setStringValue("MATERIAL");
						seqCodeType.setSequence(BigInteger.valueOf(1));
					}
				}
			}

			List<ItemLocationType> itemLocTypeList = ((ItemMasterType) sourceObject).getItemLocationList();
	
			for (int i=0; i<itemLocTypeList.size(); i++)
			{
				// Get work location
				ItemLocationType itemLocType = ((ItemMasterType) sourceObject).getItemLocationArray(i);
				FacilityType facType = itemLocType.getFacility();
				String workLoc = facType.getIDs().getIDArray(0).getStringValue();
				
				if(!itemDaoPW.workLocObsolete(workLoc))
				{
					//  Get securityGroup from table  UTASGI_USER_SEC_GRP_XREF         
					String securityGroup = userDaoPW.getDefaultSecurityGroup(workLoc);

					// Add Security Group to XML
					if(!"NONE".equals(securityGroup))    // Defect 1612
					{
						SpecificationType specType = itemHdrType.addNewSpecification();
						specType.setType("SecurityGroups");
						PropertyType propType = specType.addNewProperty();
						NameValuePairType nameValuePairType = propType.addNewNameValue();
						nameValuePairType.setName("SecurityGroup");
						nameValuePairType.setStringValue(securityGroup);
					}

					ItemLocationUserAreaType itemLocationUserArea = (ItemLocationUserAreaType) oagiUtils.getObjectFromAnyTypeByType(itemLocType.getUserArea(),ItemLocationUserAreaType.type);
					if("MATERIAL".equals(uom) && "MATERIAL".equals(itemSubtype)) 
					{
						itemLocationUserArea.setWorkOrderSerialControlIndicator(false);
						itemLocationUserArea.setComponentSerialControlIndicator(false);
					}
					else 
					{
						// Defect 1612
						if(itemLocationUserArea.getWorkOrderSerialControlIndicator())
						{
							itemLocationUserArea.setComponentSerialControlIndicator(true);
						}
					}
				}
				else
				{
					itemLocTypeList.remove(i);
					i--;
				}
			}

			// 2019-06-04 - SMRO_MD_304 End
		}
		wasDeleted = this.Super.map(actions, destinationPart, ecnList);  

		return wasDeleted;
	}
}

