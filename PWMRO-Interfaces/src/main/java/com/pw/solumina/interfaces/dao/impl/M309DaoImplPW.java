/*
 * This unpublished work, first created in 2019 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2019.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    M309DaoImplPW.java
 * 
 *  Created: 2019-08-23
 * 
 *  Author:  Brendan Polak 
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2019-08-23		B.Polak 		Initial Release SMRO_M309 - Tooling Serial Interface
 *  								
 */

package com.pw.solumina.interfaces.dao.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;
import com.pw.solumina.interfaces.dao.IM309DaoPW;

public class M309DaoImplPW implements IM309DaoPW{

	
	@Reference
	private JdbcDaoSupport jdbcDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	public List getM309ColumnLengths() {
		{

			StringBuffer selectSql = new StringBuffer().append(" SELECT T.DATA_LENGTH,T.COLUMN_NAME ")
					                                   .append("   FROM DBA_TAB_COLS T ")
							                           .append("  WHERE T.TABLE_NAME = ? ")
							                           .append(" or T.TABLE_NAME = ?");

			ParameterHolder params = new ParameterHolder();
			params.addParameter("SFFND_TOOL_SERIAL_DEF");
			params.addParameter("SFPL_ITEM_DESC_MASTER_ALL");
		
			return jdbcDao.queryForList(selectSql.toString(), params);
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	public String getSecurityGroupForPlant(String plant)
	{
		String securityGroup = null;
		StringBuffer selectSql = new StringBuffer().append("select SECURITY_GROUP from UTASGI_USER_SEC_GRP_XREF where HR_LOC =  ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(plant);
		
		List results = jdbcDao.queryForList(selectSql.toString(), params);
		if(results != null && !results.isEmpty())
		{
			Iterator iter = results.iterator();
			if(iter.hasNext())
			{
				Map map = (Map)iter.next();
				securityGroup = (String) map.get("SECURITY_GROUP");
			}	
		}
		return securityGroup;
	}

	@SuppressWarnings("rawtypes")
	public String getSecurityGroupForItem(String item_id)
	{
		StringBuffer selectSql = new StringBuffer().append("select SECURITY_GROUP from SFPL_ITEM_DESC_MASTER_ALL where ITEM_ID =  ?");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(item_id);
		
		String securityGroup = null;
		
		List results = jdbcDao.queryForList(selectSql.toString(), params);
		if(results != null && !results.isEmpty())
		{
			Iterator iter = results.iterator();
			if(iter.hasNext())
			{
				Map map = (Map)iter.next();
				securityGroup = (String) map.get("SECURITY_GROUP");
			}	
		}
		return securityGroup;
	}

	public void updateSecurityGroupForItem(String item_id, String newSecurityGroup)
	{
		
		StringBuffer updateSql = new StringBuffer().append("update SFPL_ITEM_DESC_MASTER_ALL set SECURITY_GROUP = ? ");
										  updateSql.append("where Item_id = ?");
										  
		ParameterHolder params = new ParameterHolder();
		params.addParameter(newSecurityGroup);
		params.addParameter(item_id);
		
		jdbcDao.update(updateSql.toString(), params);
	}
	
	@SuppressWarnings("rawtypes")
	public String getItemId(String part_no, String part_chg)
	{
		String item_id = null;
		
		StringBuffer selectSql = new StringBuffer().append("select ITEM_ID from SFPL_ITEM_DESC_MASTER_ALL ");
		selectSql.append("where PART_NO = ? and PART_CHG = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(part_no);
		params.addParameter(part_chg);

		 
		List results = jdbcDao.queryForList(selectSql.toString(), params);
		if(results != null && !results.isEmpty())
			{
				Iterator iter = results.iterator();
				if(iter.hasNext())
				{
					Map map = (Map)iter.next();
					item_id = (String) map.get("ITEM_ID");
				}	
			}
		return item_id;
	}
	
	@SuppressWarnings("rawtypes")
	public String getPlantForItem(String item_Id)
	{
		String plant = null;
		StringBuffer selectSql = new StringBuffer().append("select UCF_ITEM_VCH5 from SFPL_ITEM_DESC_MASTER_ALL ");
		selectSql.append("where ITEM_ID = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(item_Id);
		
		
		List results = jdbcDao.queryForList(selectSql.toString(), params);
		if(results != null && !results.isEmpty())
			{
				Iterator iter = results.iterator();
				if(iter.hasNext())
				{
					Map map = (Map)iter.next();
					plant = (String) map.get("UCF_ITEM_VCH5");
				}	
			}
		return plant;
	}
	
	@SuppressWarnings("rawtypes")
	public boolean itemDescSecGrpContainsTool(String part_no)
	{
		StringBuffer selectSql = new StringBuffer().append("select count(part_no) from SFPL_ITEM_DESC_SEC_GRP where part_no = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(part_no);
		
		
		List results = jdbcDao.queryForList(selectSql.toString(), params);
		if(results != null && !results.isEmpty())
			{
				Iterator iter = results.iterator();
				if(iter.hasNext())
				{
					Map map = (Map)iter.next();
					BigDecimal count = (BigDecimal) map.get("COUNT(PART_NO)");
					
					if(!count.equals(new BigDecimal(0)))
					{return true;}
				}	
			}
		
		return false;
	}
	
	public void insertIntoItemDescSecGroup(String part_no, String security_Group)
	{
		StringBuffer insertSql = new StringBuffer().append(" insert into SFPL_ITEM_DESC_SEC_GRP "
				+ "(PART_NO, SECURITY_GROUP, OBSOLETE_RECORD_FLAG) values "
				+ "( ?, ?, 'N')");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(part_no);			
		params.addParameter(security_Group);

		jdbcDao.insert(insertSql.toString(), params);
	}
	
	@SuppressWarnings("rawtypes")
	public boolean itemProgramDetailContainsTool(String item_id, String location_id)
	{
		StringBuffer selectSql = new StringBuffer().append("select count(item_id) from sfpl_item_program_details "
				+ "where item_id = ?"
				+ "and program = 'ANY'"
				+ "and location_id = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(item_id);;
		params.addParameter(location_id);
		
		
		List results = jdbcDao.queryForList(selectSql.toString(), params);
		if(results != null && !results.isEmpty())
			{
				Iterator iter = results.iterator();
				if(iter.hasNext())
				{
					Map map = (Map)iter.next();
					BigDecimal count = (BigDecimal) map.get("COUNT(ITEM_ID)");
									
					if(!count.equals(new BigDecimal(0)))
					{return true;}
				}	
			}
		
		return false;
	}
	
	public void insertIntoItemProgramDetail(String item_id, String location_id)
	{
		StringBuffer insertSql = new StringBuffer().append(" insert into sfpl_item_program_details "
				+ "(ITEM_ID, PROGRAM, LOCATION_ID, STOCK_UOM) values "
				+ "( ?, 'ANY', ?, 'EA')");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(item_id);			
		params.addParameter(location_id);

		jdbcDao.insert(insertSql.toString(), params);
	
	}
	
	@SuppressWarnings("rawtypes")
	public String getLocationIDforPlant(String plant)
	{
		String location_id = null;
		StringBuffer selectSql = new StringBuffer().append("select LOCATION_ID from sffnd_work_loc_def where WORK_LOC = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(plant);
		
		
		List results = jdbcDao.queryForList(selectSql.toString(), params);
		if(results != null && !results.isEmpty())
			{
				Iterator iter = results.iterator();
				if(iter.hasNext())
				{
					Map map = (Map)iter.next();
					location_id = (String) map.get("LOCATION_ID");
				}	
			}
			return location_id;
	}
}
