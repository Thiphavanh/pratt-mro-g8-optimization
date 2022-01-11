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
*  File:    LONotificationDaoPW.java
* 
*  Created: 2017-11-27
* 
*  Author:  c293011
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-11-27 	c293011		    Initial Release SMRO_TR_T222 LO Notification
* 2018-04-13    R. Thorpe		Defect 681 - modify insertSalesOrder and updateSalesOrder (add params.addParameter(ContextUtil.getUsername());) 
* 2018-06-08	R. Thorpe       Defect 837 - modify doesEngineModelTypeExist to use OOB table (SFFND_MODEL_DEF) instead of custom table (PWUST_ENGINE_TYPE_MODEL_DEF)
* 2018-09-06	R. Thorpe       Defect 962 - modify doesEngineModelTypeExist to use custom table (PWUST_ENGINE_TYPE_MODEL_DEF) instead of OOB (SFFND_MODEL_DEF) 
*                                          - REVERSE Defect 837
*/

package com.pw.solumina.interfaces.dao;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;
import com.pw.common.dao.CommonDaoPW;
/**
 * @author c293011
 *
 */
public class LONotificationDaoPW
{
	@Reference private JdbcDaoSupport jdbcDao;
	@Reference  private CommonDaoPW commonDaoPW = null; 
	

    /** 
     * SMRO_T222 -November, 2017
     * 
     * @author c293011 Raeann Thorpe
     *
     * @param engineType, actNo, subActNo
     * @return
     */
    public boolean doesNetworkExist(String engineType, String actNo, String subActNo) 
    {
    	boolean exists = false;
    	
         StringBuffer selectSql = new StringBuffer().append("SELECT ?")
        		 									.append("  FROM PWUST_NETWORK_DEF")
        		 									.append(" WHERE ENGINE_TYPE = ?")
        		 									.append("   AND SUPERIOR_NETWORK_ACT = ?")
        		 									.append("   AND SUB_NETWORK_ACT = ?")
        		 									.append("   AND OBSOLETE_RECORD_FLAG = 'N'");
         
         ParameterHolder params = new ParameterHolder();
         params.addParameter(SoluminaConstants.X);
         params.addParameter(engineType);
         params.addParameter(actNo);
         params.addParameter(subActNo);
         
         List list = jdbcDao.queryForList(selectSql.toString(), params);
         
         if (!list.isEmpty())
         {
        	 exists = true;
         }
         return exists;
    }
    
    public boolean doesEngineModelTypeExist(String engineType, String engineModel) 
    {
    	boolean exists = false;
    	
         StringBuffer selectSql = new StringBuffer().append("SELECT ?")
        		 									.append("  FROM PWUST_ENGINE_TYPE_MODEL_DEF")  //Defect 837 // Defect 962
        		 									.append(" WHERE ENGINE_TYPE = ?")  // Defect 837  // Defect 962
        		 									.append("   AND MODEL = ?")
        		 									.append("   AND OBSOLETE_RECORD_FLAG = 'N'");
         
         ParameterHolder params = new ParameterHolder();
         params.addParameter(SoluminaConstants.X);
         params.addParameter(engineType);
         params.addParameter(engineModel);
     
         List list = jdbcDao.queryForList(selectSql.toString(), params);
         
         if (!list.isEmpty())
         {
        	 exists = true;
         }
         return exists;
    }
   
    public boolean doesCustomerExist(String customer) 
    {
    	boolean exists = false;
    	
         StringBuffer selectSql = new StringBuffer().append("SELECT ?")
        		 									.append("  FROM SFFND_CUST_ID_DEF")
        		 									.append(" WHERE CUST_ID = ?")
        		 									.append("   AND OBSOLETE_RECORD_FLAG = 'N'");
         
         ParameterHolder params = new ParameterHolder();
         params.addParameter(SoluminaConstants.X);
         params.addParameter(customer);

         List list = jdbcDao.queryForList(selectSql.toString(), params);
         
         if (!list.isEmpty())
         {
        	 exists = true;
         }
         return exists;
    }
    
    public boolean doesWorkscopeExist(String engineType, 
    		                          String workLoc, 
    		                          String workscope) 
    {
    	boolean exists = false;
    	
         StringBuffer selectSql = new StringBuffer().append("SELECT ?")
        		 									.append("  FROM PWUST_WORKSCOPE_DEF")
        		 									.append(" WHERE ENGINE_TYPE = ?")
        		 									.append("   AND WORK_LOC = ?")
        		 									.append("   AND WORKSCOPE = ?")
        		 									.append("   AND OBSOLETE_RECORD_FLAG = 'N'");
         
         ParameterHolder params = new ParameterHolder();
         params.addParameter(SoluminaConstants.X);
         params.addParameter(engineType);
         params.addParameter(workLoc);
         params.addParameter(workscope);

         List list = jdbcDao.queryForList(selectSql.toString(), params);
         
         if (!list.isEmpty())
         {
        	 exists = true;
         }
         return exists;
    }
    
    public boolean doesSalesOrderRecordExist(String salesOrder, String actNo, String subActNo) 
    {
    	boolean exists = false;
    	
         StringBuffer selectSql = new StringBuffer().append("SELECT ?")
        		 									.append("  FROM PWUST_SALES_ORDER")
        		 									.append(" WHERE SALES_ORDER = ?")
        		 									.append("   AND ACT_NO = ?")
        		 									.append("   AND SUB_ACT_NO = ?");
         
         ParameterHolder params = new ParameterHolder();
         params.addParameter(SoluminaConstants.X);
         params.addParameter(salesOrder);
         params.addParameter(actNo);
         params.addParameter(subActNo);
         
         List list = jdbcDao.queryForList(selectSql.toString(), params);
         
         if (!list.isEmpty())
         {
        	 exists = true;
         }
         return exists;
    }
    
    public int updateSalesOrder(String salesOrder, 
    							String engineModel,
    		                    String actNo, 
    		                    String subActNo,
    		                    String workScope, 
    		                    String customer,
    		                    String customerDesc,
    		                    Date   startDate, 
    		                    Date   finishDate,
    		                    Date   updtDate)
    {
    	StringBuffer updateSql = new StringBuffer().append("UPDATE PWUST_SALES_ORDER")
    			                                   .append("   SET ENGINE_MODEL = ?,")
    			                                   .append("       WORK_SCOPE = ?,")
    			                                   .append("       CUSTOMER = ?,")
    			                                   .append("       CUSTOMER_DESC = ?,")
    			                                   .append("       SUB_ACT_NO_START_DATE = ?,")
    			                                   .append("       SUB_ACT_NO_FINISH_DATE = ?,")
    			                                   .append("       LAST_ACTION = 'UPDATED',")
    			                                   .append("       TIME_STAMP = ?,")
    			                                   .append("       UPDT_USERID = ?,")
    			                                   .append("       DELETE_FLAG = 'N'")
    			                                   .append(" WHERE SALES_ORDER = ?")
    			                                   .append("   AND ACT_NO = ?")
    			                                   .append("   AND SUB_ACT_NO = ?");

    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(engineModel);
    	params.addParameter(workScope);
    	params.addParameter(customer);
    	params.addParameter(customerDesc);
    	params.addParameter(startDate);
    	params.addParameter(finishDate);
    	params.addParameter(updtDate);
    	params.addParameter(ContextUtil.getUsername());  // Defect 681
    	params.addParameter(salesOrder);
    	params.addParameter(actNo);
    	params.addParameter(subActNo);

    	int rowCount = jdbcDao.update(updateSql.toString(), params);

    	return rowCount;
    }
    
    public int updateDeleteFlag(String salesOrder)
    {
    	StringBuffer updateSql = new StringBuffer().append("UPDATE PWUST_SALES_ORDER")
    			                                   .append("   SET DELETE_FLAG = NULL")
    			                                   .append(" WHERE SALES_ORDER = ?")
    			                                   .append("   AND DELETE_FLAG = 'N'");

    	ParameterHolder params = new ParameterHolder();

    	params.addParameter(salesOrder);

    	int rowCount = jdbcDao.update(updateSql.toString(), params);

    	return rowCount;
    }
    
    public void deleteSalesOrderRecords(String salesOrder)
    {
    	StringBuffer deleteSql = new StringBuffer().append("DELETE FROM PWUST_SALES_ORDER")
    			                                   .append(" WHERE SALES_ORDER = ?")
    			                                   .append("   AND DELETE_FLAG IS NULL");

    	ParameterHolder params = new ParameterHolder();

    	params.addParameter(salesOrder);

        jdbcDao.delete(deleteSql.toString(), params);

    }
    
    public void insertSalesOrder(String salesOrder, 
    		                     String engineType, 
    		                     String engineModel, 
    		                     String moduleCode, 
    		                     String actNo, 
    		                     String subActNo, 
    		                     String customer, 
    		                     String customerDesc,
    		                     String workScope, 
    		                     String serialNo, 
    		                     String workLoc, 
    		                     String confirmNo, 
    		                     Date   startDate, 
    		                     Date   finishDate,
    		                     Date   updtDate )
    {
    	//// Explain Plan cost =
    	StringBuffer insertSql = new StringBuffer().append("INSERT INTO PWUST_SALES_ORDER")
    			                                   .append(" (SALES_ORDER,")
    			                                   .append("  ENGINE_TYPE,")
    			                                   .append("  ENGINE_MODEL,")
    			                                   .append("  MODULE_CODE,")
    			                                   .append("  ACT_NO,")
    			                                   .append("  SUB_ACT_NO,")
    			                                   .append("  CUSTOMER,")
    			                                   .append("  CUSTOMER_DESC,")
    			                                   .append("  WORK_SCOPE,")
    			                                   .append("  SERIAL_NO,")
    			                                   .append("  WORK_LOC,")
    			                                   .append("  CONFIRM_NO,")
    			                                   .append("  SUB_ACT_NO_START_DATE,")
    			                                   .append("  SUB_ACT_NO_FINISH_DATE,")	
    			                                   .append("  UPDT_USERID,")
    			                                   .append("  TIME_STAMP,")
    			                                   .append("  DELETE_FLAG )")
    			                                   .append("  VALUES (")
    			                                   .append("?,")
    			                                   .append("?,")
    			                                   .append("?,")
    			                                   .append("?,")
    			                                   .append("?,")
    			                                   .append("?,")
    			                                   .append("?,")
    			                                   .append("?,")
    			                                   .append("?,")
    			                                   .append("?,")
    			                                   .append("?,")
    			                                   .append("?,")
    			                                   .append("?,")
    			                                   .append("?,")
    			                                   .append("?,")
    			                                   .append("?,")
    			                                   .append("'N')");

    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(salesOrder);
    	params.addParameter(engineType);
    	params.addParameter(engineModel);
    	params.addParameter(moduleCode);
    	params.addParameter(actNo);
    	params.addParameter(subActNo);
    	params.addParameter(customer);
    	params.addParameter(customerDesc);
    	params.addParameter(workScope);
    	params.addParameter(serialNo);    	
    	params.addParameter(workLoc);
    	params.addParameter(confirmNo);
    	params.addParameter(startDate);
    	params.addParameter(finishDate);
    	params.addParameter(ContextUtil.getUsername());  // Defect 681
    	params.addParameter(updtDate);
    	
    	jdbcDao.insert(insertSql.toString(), params);	
    }
}




