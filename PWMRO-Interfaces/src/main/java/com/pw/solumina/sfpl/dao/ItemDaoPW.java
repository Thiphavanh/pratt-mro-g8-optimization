package com.pw.solumina.sfpl.dao;
/*
 *  This unpublished work, first created in 2017 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2017,2021.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    ItemDaoPW.java
 * 
 *  Created: November 7, 2017 
 * 
 *  Author:  B. Corson
 * 
 *  Revision History
 * 
 *  Date      	Who                	Description
 *  ----------	---------------	------------------------------------------------------------------
 * 2017-11-07	B. Corson       SMRO_MD_204 Clone getItemNumber1() of ItemDaoPW from AutoAir.
 * 2018-04-05	B. Preston		PW_SMRO_EXPT_203 Defect 365 Added updateSecurity.
 * 2018-05-01	B. Preston		PW_SMRO_EXPT_203 Defect 365 Check for ANY in updateSecurity.
 * 2018-05-07	B. Preston		PW_SMRO_EXPT_203 Defect 365 return update results in updateSecurity.
 * 2018-08-14	B. Preston		PW_SMRO_EXPT_203 Defect 933 Added getWorkLocation().
 * 2019-02-28   T. Phan         SCR SMRO_PLG_207-001 Added repairUpdate.
 * 2019-03-01   X002174         Switched Security Group record from a field to a tab – Defect 1135
 * 2019-06-24   B. Corson       SMRO_MD_304 - Added getItemID() and deleteLocation().
 * 2021-07-03   D. Miron        SMRO_MD_204 Defect 1901 Added getItemSubtype, workLocObsolete, and isRevisionExists
 */
import java.util.List;
import java.util.Map;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;

public class ItemDaoPW implements IItemDaoPW {
	@Reference 
	private JdbcDaoSupport jdbcDao = null;

	public Number getItemNumber1(String itemId)
    {
        Number ItemNumber1=null;
        StringBuffer selectSql = new StringBuffer().append(" SELECT UCF_ITEM_NUM1 ")
                                                   .append("  FROM SFPL_ITEM_DESC_MASTER_ALL ")
                                                   .append(" WHERE ITEM_ID = ? ");
					   							  					   							   
        ParameterHolder params = new ParameterHolder();
        params.addParameter(itemId);
    	    	
        List list = jdbcDao.queryForList(selectSql.toString(), params);
        if (list.size()>0)
        {
            Map map = (Map) list.get(0);
            ItemNumber1 = (Number)map.get("UCF_ITEM_NUM1");
        }
    	
        return ItemNumber1;	      
    }
	
	// Begin Defect 365
	public int updateSecurity(String itemNo, String workLocation) {
		
		if ("ANY".equals(workLocation)) {
			return 0;
		}
	    // Defect 1135. Changed SQL to insert record into Security Group tab
		StringBuffer updateSql = new StringBuffer().append("INSERT INTO SFPL_ITEM_DESC_SEC_GRP (PART_NO, SECURITY_GROUP, "
				+ "OBSOLETE_RECORD_FLAG, UPDT_USERID, TIME_STAMP, LAST_ACTION, EXTERNAL_PLM_NO, EXTERNAL_ERP_NO) ")
				.append("VALUES(?, (SELECT SECURITY_GROUP FROM UTASGI_USER_SEC_GRP_XREF WHERE HR_LOC = ?), "
						+ "'N', ?, SYSDATE, 'INSERTED', null, null )");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(itemNo);
		params.addParameter(workLocation);
		params.addParameter(ContextUtil.getUsername());

		return jdbcDao.update(updateSql.toString(), params);
	}
	// End Defect 365
	
	// Begin SMRO_PLG_207-001
		public int repairUpdate(String itemNo, String itemRev) {
			
			String externalPlmNo = itemNo + itemRev;
			
			StringBuffer updateSql = new StringBuffer().append("UPDATE SFPL_ITEM_DESC_MASTER_ALL ")
					.append("  SET EXTERNAL_PLM_NO = ? ")
					.append("  WHERE PART_NO = ? ")
					.append("  AND PART_CHG = ? ");

			ParameterHolder params = new ParameterHolder();
			params.addParameter(externalPlmNo);
			params.addParameter(itemNo);
			params.addParameter(itemRev);

			return jdbcDao.update(updateSql.toString(), params);
		}
		// End SMRO_PLG_207-001
	
	// Begin Defect 933.
	public String getWorkLocation(String partNo, String partChg)
    {
        StringBuffer selectSql = new StringBuffer().append(" SELECT UCF_ITEM_VCH5 ")
                                                   .append("  FROM SFPL_ITEM_DESC_MASTER_ALL ")
                                                   .append(" WHERE PART_NO = ? AND PART_CHG = ? ")
                                                   .append(" AND ITEM_TYPE = 'PART' AND UCF_ITEM_VCH5 IS NOT NULL ");
					   							  					   							   
        ParameterHolder params = new ParameterHolder();
        params.addParameter(partNo);
        params.addParameter(partChg);
    	    	
        String workLocation = "";
        
        List list = jdbcDao.queryForList(selectSql.toString(), params);
        if (list.size() > 0)
        {
            Map map = (Map) list.get(0);
            workLocation = (String)map.get("UCF_ITEM_VCH5");
        }
    	
        return workLocation;	      
    }
	// End Defect 933.
	
    // 2019-06-04 - SMRO_MD_304 Begin
    public String getItemId(String partNo)
    {
        StringBuffer selectSql = new StringBuffer().append(" SELECT ITEM_ID ")
                                                   .append("  FROM SFPL_ITEM_DESC_MASTER_ALL ")
                                                   .append(" WHERE PART_NO = ? ")
                                                   .append("   AND TO_CHAR(TIME_STAMP, 'MM/DD/YYYY') = TO_CHAR(CURRENT_DATE, 'MM/DD/YYYY')");
        ParameterHolder params = new ParameterHolder();
        
        params.addParameter(partNo);
        
        String itemId = "";
        
        List list = jdbcDao.queryForList(selectSql.toString(), params);
        if (list.size() > 0)
        {
            Map map = (Map) list.get(0);
            itemId = (String)map.get("ITEM_ID");
        }
        
        return itemId;	      
    }
    
    public int deleteLocation(String itemId, String locationId)
    {
        StringBuffer deleteSql = new StringBuffer().append(" DELETE ")
                                                   .append("  SFPL_ITEM_PROGRAM_DETAILS ")
                                                   .append(" WHERE ITEM_ID = ? ")
                                                   .append("   AND LOCATION_ID = ? ");
        
        ParameterHolder params = new ParameterHolder();
        params.addParameter(itemId);
        params.addParameter(locationId);
        
        return jdbcDao.delete(deleteSql.toString(), params);
    }
    // 2019-06-04 - SMRO_MD_304 End
    
    // Defect 1901
	public Map getItemSubtype(String itemNo, String itemChg)
	{
		Map returnMap = null;
		List returnList = null;

		StringBuffer selectSql = new StringBuffer().append(" SELECT ITEM_SUBTYPE, STOCK_UOM  ") 
				                                   .append("   FROM SFPL_ITEM_DESC_ALL ")
				                                   .append("  WHERE PART_NO = ? ")
				                                   .append("   AND PART_CHG = ? ")
				                                   .append("   AND WORK_LOC = 'ANY' "); 
						
		ParameterHolder params = new ParameterHolder();
		params.addParameter(itemNo);
		params.addParameter(itemChg);

		returnList = jdbcDao.queryForList(selectSql.toString(), params);   
		if(returnList.size() > 0) 
		{
			returnMap = (Map) returnList.get(0);
		}

		return returnMap;
	}
	
	public boolean workLocObsolete(String workLoc) {
		
		Boolean obsolete = false;
	
		StringBuffer selectSql = new StringBuffer().append(" SELECT '1'  ")
				                                   .append("   FROM SFFND_WORK_LOC_DEF ") 
				                                   .append("  WHERE WORK_LOC = ? ")
		                                           .append("    AND OBSOLETE_RECORD_FLAG = 'Y' ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(workLoc);			

		List list = jdbcDao.queryForList(selectSql.toString(), params);
	    if(list.size() > 0) obsolete = true;

		return obsolete;
	}
	
	public boolean isRevisionExists(String itemNo, String itemChg)
	{
		Boolean exists = false;

		StringBuffer selectSql = new StringBuffer().append(" SELECT '1'  ") 
				                                   .append("   FROM SFPL_ITEM_DESC_ALL ")
				                                   .append("  WHERE PART_NO = ? ")
				                                   .append("   AND PART_CHG = ? ")
				                                   .append("   AND WORK_LOC = 'ANY' "); 
						
		ParameterHolder params = new ParameterHolder();
		params.addParameter(itemNo);
		params.addParameter(itemChg);

		List list = jdbcDao.queryForList(selectSql.toString(), params);
	    if(list.size() > 0) exists = true;

		return exists;
	}
}
