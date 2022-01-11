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
*  File:    BuildMROOrderNumberDao.java
*  
*  PW_SMRO_eWORK_202-Order-Creation
* 
*  Created: 2018.01.11
* 
*  Author:  Fred Ettefagh
* 
*  Revision History
* 
*  Date             Who            Description
*  -----------    ------------     ---------------------------------------------------
*  2018-11-02	  Fred Ettefagh    defect 995, Sub and supplemental order numbering is wrong again 
*  2021-02-10     John DeNinno     defect 1864 add partNo to the select statement 
*/
package com.pw.solumina.sfpl.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;

public class BuildMROOrderNumberDao {

	@Reference
	private JdbcDaoSupport jdbcDaoSupport;

	
	@SuppressWarnings("rawtypes")
	public Map selectPwustSwidOrderDescViaPwOrderNo(String pwOrderNo){
		
		Map map = null;
		ParameterHolder params = new ParameterHolder();
		
		StringBuffer select = new StringBuffer().append(" SELECT T.* , " +
													    "        T1.PW_ORDER_NO, " +
													    "        T1.SALES_ORDER,  " +
													    "        T1.ENGINE_MODEL,  " +
													    "        T1.WORK_SCOPE, " +
													    "        T1.SUPERIORNET, " +
													    "        T1.SUBNET, " +
													    "        T1.CUSTOMER,  " +
													    "        T1.PW_ORDER_TYPE " +
													    " FROM SFWID_ORDER_DESC T,  " + 
													    "      PWUST_SFWID_ORDER_DESC T1  "+
	                                                    " WHERE T.ORDER_ID = T1.ORDER_ID " +
	                                                    " AND T1.PW_ORDER_NO = ? ");
		
		params.addParameter(pwOrderNo);
		map = jdbcDaoSupport.queryForMap(select.toString(), params);
		return map;		
	}
	
	@SuppressWarnings("rawtypes")
	public String getOverHaulWorkOrderNumber(String orderNo,String pwOrderType, String partNo) throws Exception{   //defect 1864


		String exitingOrderNumber = null;
		/*
		Connection dbConnection = DataSourceUtils.getConnection(jdbcDaoSupport.getDataSource());
		CallableStatement cstmt = null;
		cstmt  = dbConnection.prepareCall("{call "+jdbcDaoSupport.getSchemaPrefix()+"PWUST_GET_OVERHAUL_WO_SUFFIX( ?, ?, ?  )}");
		cstmt.setString(1, orderNo);
		cstmt.setString(2, pwOrderType);
		cstmt.registerOutParameter(3, Types.VARCHAR);
		cstmt.execute();
		exitingOrderNumber = cstmt.getString(3);
		*/
		
		StringBuffer select = new StringBuffer().append( " SELECT TT.PW_ORDER_NO " +
                                                         " FROM (SELECT T.PW_ORDER_NO " +
													     "       FROM PWUST_SFWID_ORDER_DESC T, SFWID_ORDER_DESC T2 " +   //defect 1864
													     "       WHERE T.PW_ORDER_NO LIKE ? " +
													     "       AND T.PW_ORDER_TYPE = ? " +
													     "       AND T2.ORDER_ID = T.ORDER_ID " +  //defect 1864
													     "       AND T2.PART_NO = ? " +   //defect 1864
													     "       ORDER BY T.PW_ORDER_NO DESC ) TT " +
													     " WHERE ROWNUM =1 ");
		
		
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderNo);
		params.addParameter(pwOrderType);
		params.addParameter(partNo);
		List list = jdbcDaoSupport.queryForList(select.toString(), params);
		if (list!=null && !list.isEmpty()){
			Map map = (Map) list.iterator().next();
			exitingOrderNumber = (String )map.get("PW_ORDER_NO");
		}

		return exitingOrderNumber;

	}
	
	@SuppressWarnings("rawtypes")
	public Map selectPwustSfwidOrderDesc(String orderId){
		
		ParameterHolder params = new ParameterHolder();
		
		StringBuffer select = new StringBuffer().append(" SELECT T.* " +
	                                                    " FROM PWUST_SFWID_ORDER_DESC T "+
	                                                    " WHERE T.ORDER_ID = ? ");
		
		params.addParameter(orderId);
		Map map = jdbcDaoSupport.queryForMap(select.toString(), params);
		return map;		
	}
	
	@SuppressWarnings("rawtypes")
	public List selectPwustSfwidOrderDesc (String pwOrderNo, String pwOrderType, String lookupSuffix){

		
		// used to build order no for overHaul sub order and supplemental orders
		StringBuffer select = null;
	
		if (StringUtils.equalsIgnoreCase(pwOrderType, "SUB ORDER")){
			
			select = new StringBuffer().append( " SELECT SUBSTR(REPLACE(T.PW_ORDER_NO, ? ,NULL),1,3) AS SUFFIX , T.*" +
							                    " FROM PWUST_SFWID_ORDER_DESC T " +
							                    " WHERE UPPER(T.PW_ORDER_TYPE) = ? "+
							                    " AND T.PW_ORDER_NO LIKE  ? " +
							                    " ORDER BY SUFFIX DESC ");
			
		}
		
		if (StringUtils.equalsIgnoreCase(pwOrderType, "SUPPLEMENTAL")){
			
			select = new StringBuffer().append( " SELECT REPLACE(T.PW_ORDER_NO, ? ,NULL) AS SUFFIX , T.* " +
		                                        " FROM PWUST_SFWID_ORDER_DESC T " +
		                                        " WHERE UPPER(T.PW_ORDER_TYPE) = ? "+
		                                        " AND T.PW_ORDER_NO LIKE  ? " +
		                                        " ORDER BY SUFFIX DESC ");
		}
		
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(pwOrderNo + "-");
		params.addParameter(pwOrderType);
		if (StringUtils.isNotBlank(lookupSuffix)){
			params.addParameter(pwOrderNo + lookupSuffix );// "-SUP%");
		}else{
			params.addParameter(pwOrderNo);	
		}
		
		List list = jdbcDaoSupport.queryForList(select.toString(), params);
		return list;
		
	}
	

}
