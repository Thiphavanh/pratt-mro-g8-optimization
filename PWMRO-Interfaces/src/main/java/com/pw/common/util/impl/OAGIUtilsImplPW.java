package com.pw.common.util.impl;
/*
 *  This unpublished work, first created in 2020 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2020,2021.  All rights reserved.  Information contained 
 *  herein is proprietary and confidential and improper use or disclosure 
 *  may result in civil and penal sanctions.
 *
 *  File:    OAGIUtilsImplPW.java
 * 
 *  Created: May 21, 2020
 * 
 *  Author:  D.Miron
 * 
 *  Revision History
 * 
 *  Date      	Who             Description
 *  ----------	---------------	------------------------------------------------------------------
 * 2020-05-21	D. Miron        SMRO_MD_204 Defect 1665 - Moved code here from ItemMapperImpl
 *                              to set Item Revision to N/A for R_PART_SYNC. Note:  Applies to S_PART_SYNC 
 *                              but this interface is not enabled.
 * 2021-06-07   D. Miron        Defect 1901 - Added check for existing part not revision N/A. 
 */

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlObject;
import org.openapplications.oagis.x9.IdentifierType;
import org.openapplications.oagis.x9.ItemHeaderType;
import org.openapplications.oagis.x9.ItemIDType;
import org.openapplications.oagis.x9.ItemMasterType;
import org.openapplications.oagis.x9.SyncItemMasterDataAreaType;
import org.openapplications.oagis.x9.SyncItemMasterType;
import org.openapplications.oagis.x9.impl.SyncItemMasterDocumentImpl;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.solumina.integration.common.IOAGIUtils;
import com.ibaset.solumina.integration.exception.ParsingException;

import com.pw.solumina.sfpl.dao.ItemDaoPW;

public abstract class OAGIUtilsImplPW extends ImplementationOf<IOAGIUtils> implements IOAGIUtils {
	
    @Reference private ItemDaoPW   itemDaoPW;

	public void validateXml(XmlObject xmlObject) throws ParsingException
	{
		this.Super.validateXml(xmlObject);

		// if (xmlObject instanceof ItemMasterType)
		if (xmlObject instanceof SyncItemMasterDocumentImpl)
		{              
			SyncItemMasterDocumentImpl doc = (SyncItemMasterDocumentImpl) xmlObject;
			SyncItemMasterType syncType = doc.getSyncItemMaster();
			SyncItemMasterDataAreaType dataAreaType = syncType.getDataArea();
			List<ItemMasterType> itemMasterTypeList = dataAreaType.getItemMasterList();
			
			for (Iterator itemMasterTypeIterator = itemMasterTypeList.iterator(); itemMasterTypeIterator.hasNext();)
			{ //for each <ItemMasterType> tag section...
				ItemMasterType itemMasterType = (ItemMasterType) itemMasterTypeIterator.next();
				ItemHeaderType headerType = itemMasterType.getItemMasterHeader();
				
				List<ItemIDType> itemIDTypeList = headerType.getItemIDList();

				for (Iterator itemIDTypeIterator = itemIDTypeList.iterator(); itemIDTypeIterator.hasNext();)
				{ //for each <ItemMasterType> tag section...
					ItemIDType itemIDType = (ItemIDType) itemIDTypeIterator.next();
					String revisionId = (String) itemIDType.getRevisionID().getStringValue();
					
					if (StringUtils.isEmpty(revisionId)) 
					{
						IdentifierType identifierType = (IdentifierType) itemIDType.getRevisionID(); 
						identifierType.setStringValue("N/A");
						itemIDType.setRevisionID(identifierType);
					}
					else 
					{
						String itemNo = (String) itemIDType.getID().getStringValue();
						if (!"N/A".equalsIgnoreCase(revisionId) && !itemDaoPW.isRevisionExists(itemNo, revisionId))
						{
						IdentifierType identifierType = (IdentifierType) itemIDType.getRevisionID(); 
						identifierType.setStringValue("N/A");
						itemIDType.setRevisionID(identifierType);
						}
					}
				}
			}
		}
	}
}
