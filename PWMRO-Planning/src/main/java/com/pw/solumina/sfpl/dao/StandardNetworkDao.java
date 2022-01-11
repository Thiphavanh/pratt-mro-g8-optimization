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
*  File:    StandardNetworkDao.java
* 
*  Created: 2017-09-06
* 
*  Author:  xcs4331
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-09-06 	xcs4331		    Initial Release XXXXXXX
* 2018-04-26    x002174         Added SUPERIOR_NETWORK_ACT_DESC to update Parent Networks
* 2019-02-20    xcs4331         defect 1130, process new xml format 
* 2019-03-18    xcs4331         defect 1130, added code to insert program into sffnd_program_def table is engine type is missing
*/

package com.pw.solumina.sfpl.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;


/**
 * @author xcs4331
 *
 */
public class StandardNetworkDao {

	
	@Reference
	private JdbcDaoSupport jdbcDao;
	
	
	@SuppressWarnings("rawtypes")
	public List selectStandardNetworkRecord(String networkNo, String networkNodeNo, String subNetworkNo,String subNetworkNodeNo){

		StringBuffer selectSql = new StringBuffer(" SELECT T.* " +
												  " FROM PWUST_NETWORK_DEF_MAP_V T " +
												  " WHERE T.NETWORK_NO = ? " +
												  " AND   T.NETWORK_NODE_NO =  ?" +
												  " AND   T.SUB_NETWORK_NO = ? " +
												  " AND   T.SUB_NETWORK_NODE_NO = ? ");	
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(networkNo);
		params.addParameter(networkNodeNo);
		params.addParameter(subNetworkNo);
		params.addParameter(subNetworkNodeNo);

		List list = jdbcDao.queryForList(selectSql.toString(), params);

		return list;
	}


	@SuppressWarnings("rawtypes")
	public List selectStandardNetworkRecord(String engineType,String whereClause){

		StringBuffer selectSql = new StringBuffer(" SELECT * " +
												  " FROM PWUST_NETWORK_DEF_MAP_V " +
												  " WHERE ENGINE_TYPE = ? ");	
		
		if (StringUtils.isNotBlank(whereClause)){
			selectSql.append(" AND " + whereClause);
		}
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(engineType);
		List list = jdbcDao.queryForList(selectSql.toString(), params);

		return list;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List selectStandardNetworkRecord(String engineType,String networkActNo, String whereClause){

		// this method is called from execquery for standard network dispatch 
		
		StringBuffer selectSql = null;
		
		if (StringUtils.isBlank(networkActNo)){
			selectSql = new StringBuffer(" SELECT * " +
				                         " FROM PWUST_NETWORK_DEF_MAP_V " +
				                         " WHERE ENGINE_TYPE = ? " +
				                         " AND NETWORK_ACT_NO is null ");	
			
		}else{
			selectSql = new StringBuffer(" SELECT * " +
									     " FROM PWUST_NETWORK_DEF_MAP_V " +
									     " WHERE ENGINE_TYPE = ? " +
									     " AND NETWORK_ACT_NO = ?");	
		}
		
		if (StringUtils.isNotBlank(whereClause)){
			selectSql.append(" AND " + whereClause);
		}
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(engineType);
		if (StringUtils.isNotBlank(networkActNo)){
			params.addParameter(networkActNo);	
		}
		List list = jdbcDao.queryForList(selectSql.toString(), params);
		if (list!=null && !list.isEmpty()){
			return list;
		}else{
			
			Map emptyMap = new HashMap();
			
			
			emptyMap.put("NETWORK_NO",null);
			emptyMap.put("NETWORK_NODE_NO",null);
			emptyMap.put("NETWORK_DESC",null);

			emptyMap.put("SUB_NETWORK_NO",null);
			emptyMap.put("SUB_NETWORK_NODE_NO",null);
			emptyMap.put("SUB_NETWORK_DESC",null);
			
			emptyMap.put("NETWORK_ACT_NO",null);
			emptyMap.put("NETWORK_ACT_DESC",null);

			emptyMap.put("SUB_NETWORK_ACT_NO",null);
			emptyMap.put("SUB_NETWORK_ACT_DESC",null);

			emptyMap.put("ENGINE_TYPE",null);
			emptyMap.put("ENGINE_TYPE_DESC",null);
			
			emptyMap.put("MODULE_CAT_CODE",null);
			emptyMap.put("MODULE_CAT_DESC",null);
			emptyMap.put("WORK_CENTER",null);
			emptyMap.put("CONTROL_KEY",null);
			emptyMap.put("GATE",null);
			emptyMap.put("DURATION",null);
			emptyMap.put("DURATION_UNIT",null);
			emptyMap.put("WORK",null);
			emptyMap.put("WORK_UNIT",null);
			emptyMap.put("EWORKSCOPING_FLAG",null);
			emptyMap.put("LID_NO",null);
			emptyMap.put("UPDT_USERID",null);
			emptyMap.put("TIME_STAMP",null);
			emptyMap.put("ACTION",null);
			emptyMap.put("OBSOLETE_RECORD_FLAG",null);

			List networkStandardChildList = new ArrayList<Map>(0);
			networkStandardChildList.add(emptyMap);
			return networkStandardChildList;
		}
	}
	
	public int updateSandardNetworkRecordToObsolete(String networkNo, String networkNodeNo, String subNetworkNo,String subNetworkNodeNo){

		StringBuffer updateSql = new StringBuffer(" UPDATE PWUST_NETWORK_DEF_MAP_V T  " +
		                                          " SET T.TIME_STAMP =  "  + jdbcDao.getTimestampFunction() + " , " +
								                  "     T.UPDT_USERID = ?, " +
				                                  "     T.OBSOLETE_RECORD_FLAG = ? " + 
												  " WHERE T.NETWORK_NO = ? " +
												  " AND   T.NETWORK_NODE_NO =  ?" +
												  " AND   T.SUB_NETWORK_NO = ? " +
												  " AND   T.SUB_NETWORK_NODE_NO = ? ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(ContextUtil.getUser().getUsername());
		params.addParameter("Y");
		params.addParameter(networkNo);
		params.addParameter(networkNodeNo);
		params.addParameter(subNetworkNo);
		params.addParameter(subNetworkNodeNo);

		int numOfRowsUpdated= jdbcDao.update(updateSql.toString(),params);
		return numOfRowsUpdated; 
	}

	
	
	public int deletetSandardNetworkRecord(String networkNo, String networkNodeNo, String subNetworkNo,String subNetworkNodeNo){

		StringBuffer deleteSql = new StringBuffer(" DELETE FROM  PWUST_NETWORK_DEF_MAP_V T " + 
												  " WHERE T.NETWORK_NO = ? " +
												  " AND   T.NETWORK_NODE_NO =  ?" +
												  " AND   T.SUB_NETWORK_NO = ? " +
												  " AND   T.SUB_NETWORK_NODE_NO = ? ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(networkNo);
		params.addParameter(networkNodeNo);
		params.addParameter(subNetworkNo);
		params.addParameter(subNetworkNodeNo);

		int numOfRowsDeleted = jdbcDao.delete(deleteSql.toString(),params);
		return numOfRowsDeleted; 
	}
	

	
	public int updateSandardNetworkRecordToObsolete1(String networkNo, String networkNodeNo){

		StringBuffer updateSql = new StringBuffer(" UPDATE PWUST_NETWORK_DEF_MAP_V T  " +
									              " SET T.TIME_STAMP =  "  + jdbcDao.getTimestampFunction() + " , " +
									              "     T.UPDT_USERID = ?, " +
									              "     T.OBSOLETE_RECORD_FLAG = ? " + 
												  " WHERE T.NETWORK_NO = ? " +
												  " AND   T.NETWORK_NODE_NO =  ?" );
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(ContextUtil.getUser().getUsername());
		params.addParameter("Y");
		params.addParameter(networkNo);
		params.addParameter(networkNodeNo);

		int numOfRowsUpdated= jdbcDao.update(updateSql.toString(),params);
		return numOfRowsUpdated; 
	}
	


	public int updateSandardNetworkRecordToObsolete2(String subNetworkNo,String subNetworkNodeNo){

		StringBuffer updateSql = new StringBuffer(" UPDATE PWUST_NETWORK_DEF_MAP_V T  " +
												  " SET T.TIME_STAMP =  "  + jdbcDao.getTimestampFunction() + " , " +
												  "     T.UPDT_USERID = ?, " +
											      "     T.OBSOLETE_RECORD_FLAG = ? " + 
				                                  " WHERE T.SUB_NETWORK_NO = ? " +
				                                  " AND   T.SUB_NETWORK_NODE_NO = ? ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(ContextUtil.getUser().getUsername());
		params.addParameter("Y");
		params.addParameter(subNetworkNo);
		params.addParameter(subNetworkNodeNo);

		int numOfRowsUpdated= jdbcDao.update(updateSql.toString(),params);
		return numOfRowsUpdated; 
	}

	
	@SuppressWarnings("rawtypes")
	public List getPwustNetworkDefColumnLenghts(){

		StringBuffer selectSql = new StringBuffer(" SELECT T.DATA_LENGTH,T.COLUMN_NAME " +
		                                          " FROM DBA_TAB_COLS T " +
				                                  " WHERE T.TABLE_NAME = ? " + 
		                                          " AND T.DATA_TYPE =  ? ");


		ParameterHolder params = new ParameterHolder();
		params.addParameter(0, "PWUST_NETWORK_DEF_MAP_V");
		params.addParameter(1, "VARCHAR2");
	
		return jdbcDao.queryForList(selectSql.toString(), params);
		
	}	
	
	
	public void insertNetworkDef(String networkNo, 
					            String networkNodeNo,
					            String networkDesc,
					            String subNetworkNo, 
					            String subNetworkNodeNo,
					            String subNetworkDesc,
					            String engineType,
					            String engineTypeDesc,
					            String networkActNo,       // SUPERIOR_NETWORK_ACT
								String networkActDesc,     // SUPERIOR_NETWORK_ACT_DESC
								String subNetworkActNo,
								String subNetworkActDesc,
								String moduleCatCode, 
								String moduleCatDesc, 
								String workCenter, 
								String controlKey, 
								String gate, 
								String duration, 
								String durationUnit, 
								String work, 
								String workUnit, 
								String eWorkScopingFlag, 
								String lidNo,
								String obsoleteFlag){

				StringBuffer insertSql = new StringBuffer( "INSERT INTO PWUST_NETWORK_DEF_MAP_V (   NETWORK_NO,"+  
																								" NETWORK_NODE_NO, "+
																								" NETWORK_DESC, "+
																								
																								" SUB_NETWORK_NO, "+
																								" SUB_NETWORK_NODE_NO, "+
																								" SUB_NETWORK_DESC, "+
																								
																								" NETWORK_ACT_NO, "+
																								" NETWORK_ACT_DESC, "+
																								
																								" SUB_NETWORK_ACT_NO, "+
																								" SUB_NETWORK_ACT_DESC, "+
																								
																								" ENGINE_TYPE, "+
																								" ENGINE_TYPE_DESC, "+
																								
																								" MODULE_CAT_CODE, "+
																								" MODULE_CAT_DESC, "+
																								" WORK_CENTER, "+ 
																							" CONTROL_KEY, "+
																							" GATE, "+
																							" DURATION, "+
																							" DURATION_UNIT, "+
																							" WORK, "+
																							" WORK_UNIT, "+
																							" EWORKSCOPING_FLAG, "+
																							" LID_NO, "+
																							" UPDT_USERID, "+
																							" ACTION, "+
																							" OBSOLETE_RECORD_FLAG," +
																							" TIME_STAMP  ) " +  
												" VALUES ( ? , ? , ? , ?, ? , ?, ? , ?, ? , ?, ? , ? , ? , ?, ? , ?, ? , ?, ? , ?, ? , ?, ?, ?, ?, ? ," + jdbcDao.getTimestampFunction() + ")") ; 

				ParameterHolder params = new ParameterHolder();

				params.addParameter(networkNo );
				params.addParameter(networkNodeNo);
				params.addParameter(networkDesc);
				
				params.addParameter(subNetworkNo); 
				params.addParameter(subNetworkNodeNo);
				params.addParameter(subNetworkDesc); 
				
				
				params.addParameter(networkActNo);
				params.addParameter(networkActDesc);

				params.addParameter(subNetworkActNo);
				params.addParameter(subNetworkActDesc);

				params.addParameter(engineType);
				params.addParameter(engineTypeDesc);
				
				
				params.addParameter(moduleCatCode);
				params.addParameter(moduleCatDesc);
				params.addParameter(workCenter);
				params.addParameter(controlKey);
				params.addParameter(gate);
				params.addParameter(duration); 
				params.addParameter(durationUnit); 
				params.addParameter(work);
				params.addParameter(workUnit); 
				params.addParameter(eWorkScopingFlag);
				params.addParameter(lidNo);
				params.addParameter(ContextUtil.getUser().getUsername());
				params.addParameter("INSERTED");
				params.addParameter(obsoleteFlag);
				
				jdbcDao.insert(insertSql.toString(),params);

	}
	
	public int updateNetworkDef(String networkNo, 
					            String networkNodeNo,
					            String networkDesc,
					            String subNetworkNo, 
					            String subNetworkNodeNo,
					            String subNetworkDesc,
					            String engineType,
					            String engineTypeDesc,
					            String networkActNo,   
								String networkActDesc, 
								String subNetworkActNo,
								String subNetworkActDesc,
								String moduleCatCode, 
								String moduleCatDesc, 
								String workCenter, 
								String controlKey, 
								String gate, 
								String duration, 
								String durationUnit, 
								String work, 
								String workUnit, 
								String eWorkScopingFlag, 
								String lidNo,
								String obsoleteFlag){

				//  new pk is  std_network_no, std_network_node_no, module_network_no, module_network_node_no


					StringBuffer upateSql = new StringBuffer(" UPDATE PWUST_NETWORK_DEF_MAP_V T " +
					
															 " SET T.NETWORK_DESC = ? ," +
																 " T.SUB_NETWORK_DESC = ? ," +
																 " T.NETWORK_ACT_NO = ? ," +
																 " T.NETWORK_ACT_DESC = ? ," +
																 " T.SUB_NETWORK_ACT_NO = ? ," +
																 " T.SUB_NETWORK_ACT_DESC = ? ," +
																 " T.ENGINE_TYPE = ? ," +
																 " T.ENGINE_TYPE_DESC = ? ," +
														 	 	 " T.MODULE_CAT_CODE = ? ," +
																 " T.MODULE_CAT_DESC = ? ," +
																 " T.WORK_CENTER = ? ," +
																 " T.CONTROL_KEY = ? ," +
																 " T.GATE = ? ," +
																 " T.DURATION = ? ," +
																 " T.DURATION_UNIT = ? ," +
																 " T.WORK = ? ," +
																 " T.WORK_UNIT = ? ," +
																 " T.EWORKSCOPING_FLAG = ? ," +
																 " T.LID_NO = ? ," +
																 " T.UPDT_USERID = ? ," +
																 " T.OBSOLETE_RECORD_FLAG  = ?, " +
																 " T.ACTION = ?, " +
																 " T.TIME_STAMP = "+ jdbcDao.getTimestampFunction()  +
								                             " WHERE  T.NETWORK_NO = ?  " +
									                         " AND    T.NETWORK_NODE_NO = ? " +
								                             " AND    T.SUB_NETWORK_NO = ? " +
								                             " AND    T.SUB_NETWORK_NODE_NO = ? ");
					
					
					ParameterHolder params = new ParameterHolder();
					
					params.addParameter(networkDesc);
					params.addParameter(subNetworkDesc); 
					
					params.addParameter(networkActNo);
					params.addParameter(networkActDesc);

					params.addParameter(subNetworkActNo);
					params.addParameter(subNetworkActDesc);

					params.addParameter(engineType);
					params.addParameter(engineTypeDesc);
										
					params.addParameter(moduleCatCode);
					params.addParameter(moduleCatDesc);
					params.addParameter(workCenter);
					params.addParameter(controlKey);
					params.addParameter(gate);
					params.addParameter(duration); 
					params.addParameter(durationUnit); 
					params.addParameter(work);
					params.addParameter(workUnit); 
					params.addParameter(eWorkScopingFlag);
					params.addParameter(lidNo);
					params.addParameter(ContextUtil.getUser().getUsername());
					params.addParameter(obsoleteFlag);
					params.addParameter("UPDATED");
					
					params.addParameter(networkNo );
					params.addParameter(networkNodeNo);
					params.addParameter(subNetworkNo); 
					params.addParameter(subNetworkNodeNo);

					
					int numberOfRowsUpdated=  jdbcDao.update(upateSql.toString(),params);
					return numberOfRowsUpdated;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List selectStandardNetworkParentList(String engineType,String whereClause){
		
		// this method is called from execquery for standard network dispatch 
		
		StringBuffer selectSql = null;
		List list = null;
		ParameterHolder params = new ParameterHolder();

		List networkStandardParentList = new ArrayList<Map>(0);
		Map emptyMap = new HashMap();
		emptyMap.put("ENGINE_TYPE_P", null);
		emptyMap.put("NETWORK_ACT_NO_P", null);
		emptyMap.put("NETWORK_ACT_DESC_P", null);
		networkStandardParentList.add(emptyMap);
		
		if(StringUtils.isNotBlank(engineType)){
		
				selectSql = new StringBuffer("  SELECT DISTINCT " +
			                                     	" ENGINE_TYPE           AS ENGINE_TYPE_P, " +
                                                    " NETWORK_ACT_NO        AS NETWORK_ACT_NO_P,   " +
                                                    " NETWORK_ACT_DESC      AS NETWORK_ACT_DESC_P" +
		                                     " FROM PWUST_NETWORK_DEF_MAP_V  " +
		                                     " WHERE ENGINE_TYPE = ? ");
				
				if (StringUtils.isNotBlank(whereClause)){
					
					if (StringUtils.contains(whereClause, "NETWORK_ACT_NO_P") || 
						StringUtils.contains(whereClause, "NETWORK_ACT_DESC_P")){
						
								whereClause = whereClause.replaceAll("NETWORK_ACT_DESC_P", "NETWORK_ACT_DESC");
								whereClause = whereClause.replaceAll("NETWORK_ACT_NO_P", "NETWORK_ACT_NO");
								selectSql.append(" AND " + whereClause);
					}
				}
		
				selectSql.append(" ORDER BY ENGINE_TYPE, NETWORK_ACT_NO ");
		
				
				params.addParameter(engineType);
		
				list = jdbcDao.queryForList(selectSql.toString(), params);
		}
		
		if (list!=null && !list.isEmpty()){
			return jdbcDao.queryForList(selectSql.toString(), params);
		}else{
			return networkStandardParentList;
		}
		
	}

	@SuppressWarnings("rawtypes")
	public boolean engineTypeExistsInProgramTable(String engineType){
		
		StringBuffer selectSql = new StringBuffer(" SELECT 1 FROM SFFND_PROGRAM_DEF T WHERE T.PROGRAM = ? "); 
		ParameterHolder params = new ParameterHolder();
		params.addParameter(engineType);

		List list = jdbcDao.queryForList(selectSql.toString(), params);		
		
		if (list!=null && !list.isEmpty() && list.size() >0){
			return true;
		}else{
			return false;
		}
	}


	
	@SuppressWarnings("rawtypes")
	public void insertEngineTypeInProgramTable(String program, String programDesc){
		// defect 1130
		StringBuffer query = new StringBuffer(" INSERT INTO SFFND_PROGRAM_DEF(PROGRAM,PROGRAM_DESC) VALUES ( ? , ?) "); 
		ParameterHolder params = new ParameterHolder();
		params.addParameter(program);
		params.addParameter(programDesc);

		jdbcDao.insert(query.toString(), params);		
		
	}
	
    @SuppressWarnings("rawtypes")
	public boolean engineTypeExistsInPwustNetworkDefTable(String engineType){
		
		
		StringBuffer selectSql = new StringBuffer(" SELECT 1  FROM PWUST_NETWORK_DEF T WHERE T.ENGINE_TYPE = ? " );
		ParameterHolder params = new ParameterHolder();
		params.addParameter(engineType);
		
		List list = jdbcDao.queryForList(selectSql.toString(), params);		
		
		if (list!=null && !list.isEmpty() && list.size() >0){
			return true;
		}else{
			return false;
		}
		

	}	

}
