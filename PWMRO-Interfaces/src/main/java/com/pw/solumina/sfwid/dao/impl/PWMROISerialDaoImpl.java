/*
* This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017,2021.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    PWMROISerialDaoImpl.java
* 
*  Created: 2019-12-10
* 
*  Author:  X005703
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2019-12-10 		X005703		    Initial Release IBaset delivery cockpit
* 01-24-2020  		D.Sibrian		Updated for T314 Interface messaging
* 2021-08-24        X006881         Defect 1862 & 1868
* 10-06-2021        J DeNinno  defect 1996 change part no to use plan part no  
* 2021-10-28		J DeNinno		Defect 2010 T314 message change
*/

package com.pw.solumina.sfwid.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.solumina.sffnd.application.IWrapUtils;
import com.pw.solumina.sfwid.dao.IPWMROISerialDao;

public class PWMROISerialDaoImpl implements IPWMROISerialDao
{
    @Reference
    private JdbcDaoSupport dao;
    
    @Reference
    private IWrapUtils wrapUtils;

    public List getInCompleteSerialsFromOtherOrder(String orderId,
                                                  String serialNo,
                                                  String itemId)
    {
        StringBuilder selectSql = new StringBuilder().append("    SELECT")
                                                     .append("         S.ORDER_ID,")
                                                     .append("         S.SERIAL_NO,")
                                                     .append("         S.SERIAL_STATUS,")
                                                     .append("         O.ORDER_NO ")
                                                     .append("    FROM")
                                                     .append("         SFWID_SERIAL_DESC S,")
                                                     .append("         SFWID_ORDER_DESC O ")
                                                     .append("    WHERE")
                                                     .append("         O.ORDER_ID = S.ORDER_ID  ")                                                  
                                                     .append("            AND S.SERIAL_NO = ?")
                                                     .append("            AND O.ITEM_ID = ? ")
                                                     .append("            AND UPPER(O.ORDER_TYPE) = ? ")
                                                     .append("            AND UPPER(NVL(S.SERIAL_STATUS,'X')) <> ? ");

        ParameterHolder params = new ParameterHolder();
        params.addParameter(serialNo);
        params.addParameter(itemId);
        params.addParameter("Repair".toUpperCase());
        params.addParameter(SoluminaConstants.COMPLETE.toUpperCase());
        try
        {
            return dao.queryForList(selectSql.toString(), params);
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    public void insertSerialDelivery(String orderId,
                                     String serialNos,
                                     String manualNos,
                                     String interfaceTrxId)
    {
        String[] st = serialNos.split(SoluminaConstants.SEMI_COLON);
        String[] stManual = manualNos.split(SoluminaConstants.SEMI_COLON);
        String[] serial, manual = null;

        StringBuilder insertSql = new StringBuilder()
        		.append(" INSERT INTO PWMROI_SERIAL_DELIVERY(ORDER_ID,SERIAL_ID,ENGINE_MANUAL, ")
        		.append("    ENGINE_MANUAL_REVISION,INTERFACE_TRANSACTION_ID,UPDT_USERID) ")
        		.append(" VALUES (?,?,?,?,?,?)");
        
        List<ParameterHolder> paramList = new ArrayList<ParameterHolder>();
        for (String serialNo : st)
        {
            serial = serialNo.split(",");
            for (String manualNo : stManual)
            {
                manual = manualNo.split(",");
                ParameterHolder params = new ParameterHolder();
                params.addParameter(orderId);
                params.addParameter(serial[0]);
                params.addParameter(manual[0]);
                params.addParameter(manual[1]);
                params.addParameter(interfaceTrxId);
                params.addParameter(ContextUtil.getUsername());
                paramList.add(params);
            }
        }

        dao.batchUpdate(insertSql.toString(), paramList);
    }
    
    public void updateDeliverSerialStatus(String orderId, String serialList, String deliverStatus)
    {    	
    	String sql = "UPDATE sfwid_serial_desc "
    			+ "   SET ucf_serial_vch10 = ?, "
    			+ "       last_action = ?, "
    			+ "       updt_userid = ?, "
    			+ "       time_stamp = " + dao.getTimestampFunction()
    			+ " WHERE order_id = ? "
    			+ "   AND serial_id = ?";

    	List<ParameterHolder> paramList = new ArrayList<ParameterHolder>();
    	String[] serials = serialList.split(SoluminaConstants.SEMI_COLON);
    	String[] serial = null;
    	
    	for (String serialStr : serials)
    	{
    		serial = serialStr.split(",");
    		ParameterHolder params = new ParameterHolder();
    		params.addParameter(deliverStatus);
    		params.addParameter(SoluminaConstants.UPDATED);
    		params.addParameter(ContextUtil.getUsername());
    		params.addParameter(orderId);
    		params.addParameter(serial[0]);
    		paramList.add(params);
    	}
    	
    	dao.batchUpdate(sql.toString(), paramList);
    }
    	
    public void insertSerialScrap(String orderId, Number operationKey, 
    		String serialList, String scrapType, String nonRepairType,
    		String category1, String code1, String primary1,
    		String category2, String code2, String primary2,
    		String category3, String code3, String primary3,
    		String category4, String code4, String primary4,
    		String category5, String code5, String primary5,
    		String category6, String code6, String primary6,
    		String scrapText, String zcomSalesOrder, String interfaceTrxId)	   
    {
    	String sql = "INSERT INTO pwmroi_serial_scrap( "
    			+ "     order_id, oper_key, serial_id, scrap_type, non_repair_type, "
    			+ "     category_1, code_1, primary_secondary_1, "
    			+ "     category_2, code_2, primary_secondary_2, "
    			+ "     category_3, code_3, primary_secondary_3, "
    			+ "     category_4, code_4, primary_secondary_4, "
    			+ "     category_5, code_5, primary_secondary_5, "
    			+ "     category_6, code_6, primary_secondary_6, "    			
    			+ "     scrap_text, zcom_sales_order, interface_transaction_id, "    			
    			+ "     last_action, updt_userid, time_stamp) "
    			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
    			+ "     ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " + dao.getTimestampFunction() + ") ";

    	List<ParameterHolder> paramList = new ArrayList<ParameterHolder>();
    	String[] serials = serialList.split(SoluminaConstants.SEMI_COLON);
    	String[] serial = null;
    	
    	for (String serialStr : serials)
    	{
    		serial = serialStr.split(",");
    		ParameterHolder params = new ParameterHolder();
    		params.addParameter(orderId);    	
    		params.addParameter(operationKey);
    		params.addParameter(serial[0]);    	
    		params.addParameter(scrapType);    	
    		params.addParameter(nonRepairType);
    		params.addParameter(category1);
    		params.addParameter(code1);
    		params.addParameter(primary1);
    		params.addParameter(category2);
    		params.addParameter(code2);
    		params.addParameter(primary2);
    		params.addParameter(category3);
    		params.addParameter(code3);
    		params.addParameter(primary3);
    		params.addParameter(category4);
    		params.addParameter(code4);
    		params.addParameter(primary4);    	
    		params.addParameter(category5);
    		params.addParameter(code5);
    		params.addParameter(primary5);
    		params.addParameter(category6);
    		params.addParameter(code6);
    		params.addParameter(primary6);
    		params.addParameter(scrapText);
    		params.addParameter(zcomSalesOrder);
    		params.addParameter(interfaceTrxId);
    		params.addParameter(SoluminaConstants.INSERTED);    	
    		params.addParameter(ContextUtil.getUsername());
    		paramList.add(params);
    	}
    	
    	dao.batchUpdate(sql.toString(), paramList);
    }
    
    @Override
    public Map selectOrderDetails(String orderId)
    {
    	
    	StringBuilder selectSql = new StringBuilder().append("    SELECT")
                									 .append("        UCF_ORDER_VCH11,")
                									 .append("        ORDER_NO,")
                								//	 .append("        PART_NO")
                									  .append("(SELECT DISTINCT PART_NO FROM SFPL_PLAN_DESC g WHERE g.PLAN_ID = t.PLAN_ID AND g.PLAN_UPDT_NO = t.PLAN_REVISION) AS PART_NO ") //defect 1996  
                									 .append("    FROM ")
                									 .append("        SFWID_ORDER_DESC t")
                									 .append("    WHERE ")
                									 .append("        ORDER_ID = ? ");

    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(orderId);
    	try
    	{
    		return dao.queryForMap(selectSql.toString(), params);
    	}
    	catch (EmptyResultDataAccessException e)
    	{
    		return null;
    	}
    }
    
    @Override
    public Map selectPwmroiSerialDelivery(String orderId,String serialId,String engineManual, String manualRev)
    {
    	StringBuilder selectSql = new StringBuilder().append("    SELECT")
                									 .append("        UPDT_USERID,")
                									 .append("        TIME_STAMP ")
                									 .append("    FROM")
                									 .append("        PWMROI_SERIAL_DELIVERY")
                									 .append("    WHERE ")
                									 .append("        	 ORDER_ID = ? ")
                									 .append("    AND    SERIAL_ID = ? ")
                									 .append("    AND  	 ENGINE_MANUAL = ? ")
                									 .append("    AND  	 ENGINE_MANUAL_REVISION = ? "); //defect 2010

    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(orderId);
    	params.addParameter(serialId);
    	params.addParameter(engineManual);
    	params.addParameter(manualRev); //defect 2010
    	try
    	{
    		return dao.queryForMap(selectSql.toString(), params);
    	}
    	catch (EmptyResultDataAccessException e)
    	{
    		return null;
    	}
    }
    
    @Override
    public Map selectPwmroiSerialScrap(String orderId,String serialId,String scrapType)
    {
    	StringBuilder selectSql = new StringBuilder().append("    SELECT")
                									 .append("        UPDT_USERID,")
                									 .append("        TIME_STAMP ")
                									 .append("    FROM")
                									 .append("        PWMROI_SERIAL_SCRAP")
                									 .append("    WHERE ")
                									 .append("        	 ORDER_ID = ? ")
                									 .append("    AND    SERIAL_ID = ? ")
                									 .append("    AND  	 SCRAP_TYPE = ? ");

    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(orderId);
    	params.addParameter(serialId);
    	params.addParameter(scrapType);
    	try
    	{
    		return dao.queryForMap(selectSql.toString(), params);
    	}
    	catch (EmptyResultDataAccessException e)
    	{
    		return null;
    	}
    }
    
    @Override
    public String selectWorkLocation (String orderId)
	{
    	StringBuilder selectSql = new StringBuilder().append("SELECT ")
		                                             .append("    WORK_LOC ")
		                                             .append("FROM ")
		                                             .append("    SFFND_WORK_LOC_DEF ")
		                                             .append("WHERE ")
		                                             .append("    LOCATION_ID = (SELECT ASGND_LOCATION_ID FROM SFWID_ORDER_DESC WHERE ORDER_ID = ? ) ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		try
		{
			return dao.queryForString(selectSql.toString(), params);
		}
		catch (EmptyResultDataAccessException e)
    	{
    		return null;
    	}
	}
    
    @Override
    public String selectGroupCategory(String catalog,String workLoc)
	{
    	StringBuilder selectSql = new StringBuilder().append("SELECT ")
		                                           	 .append("    CATEGORY ")
		                                           	 .append("FROM ")
		                                           	 .append("    PWUST_GENERAL_CATALOG_LOOKUP ")
		                                           	 .append("WHERE ")
		                                           	 .append("    CATALOG = ?  ")
		                                           	 .append("AND WORK_LOC = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(catalog);
		params.addParameter(workLoc);
		try
		{
			return dao.queryForString(selectSql.toString(), params);
		}
		catch (EmptyResultDataAccessException e)
    	{
    		return null;
    	}
	}
    
    @Override
    public String selectCode(String catalog,String category)
	{
    	StringBuilder selectSql = new StringBuilder().append("SELECT ")
		                                           	 .append("    CODE ")
		                                           	 .append("FROM ")
		                                           	 .append("    PWUST_GENERAL_CATALOG_LOOKUP ")
		                                           	 .append("WHERE ")
		                                           	 .append("    CATALOG = ?  ")
		                                           	 .append("AND CATEGORY = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(catalog);
		params.addParameter(category);
		try
		{
			return dao.queryForString(selectSql.toString(), params);
		}
		catch (EmptyResultDataAccessException e)
    	{
    		return null;
    	}
	}
    
    @Override
    public void updateTransIDForScrap(String orderId,Number operKey,String[] serialIdList,String transactionId)
    {
    	StringBuilder updateSql = new StringBuilder().append("UPDATE  ")
    										   		 .append("    PWMROI_SERIAL_SCRAP ")
    										   		 .append("SET ")
    										   		 .append("    INTERFACE_TRANSACTION_ID = ? ")
    										   		 .append("WHERE ")
    										   		 .append("    ORDER_ID = ?  ")
    										   		 .append("AND OPER_KEY = ?  ")
    										   		 .append("AND SERIAL_ID = ?  ");

    	List<ParameterHolder> paramList = new ArrayList<ParameterHolder>();
    	for(int i = 0; i < serialIdList.length ; i++)
    	{
    		ParameterHolder params = new ParameterHolder();
    		params.addParameter(transactionId);
    		params.addParameter(orderId);
    		params.addParameter(operKey);
    		params.addParameter(serialIdList[i]);
    		paramList.add(params);
    	}    	
    	dao.batchUpdate(updateSql.toString(), paramList);
    }
    
    @Override
    public void updateTransIDForDelivery(String orderId,String[] serialIdList,String transactionId)
    {
    	String serialId="";
    	StringBuilder updateSql = new StringBuilder().append("UPDATE  ")
    										   		 .append("    PWMROI_SERIAL_DELIVERY ")
    										   		 .append("SET ")
    										   		 .append("    INTERFACE_TRANSACTION_ID = ? ")
    										   		 .append("WHERE ")
    										   		 .append("    ORDER_ID = ?  ")
    										   		 .append("AND SERIAL_ID = ?  ");

    	List<ParameterHolder> paramList = new ArrayList<ParameterHolder>();
    	for(int i = 0; i < serialIdList.length ; i++)
    	{
    		ParameterHolder params = new ParameterHolder();
    		params.addParameter(transactionId);
    		params.addParameter(orderId);
    		params.addParameter(serialIdList[i]);
    		paramList.add(params);
    	} 
    	dao.batchUpdate(updateSql.toString(), paramList);
    }
    
    @Override
    public List selectPwmroiSerialDeliveryDetails(String interFaceTransactionId)
    {
    	StringBuilder selectSql = new StringBuilder().append("    SELECT")
                									 .append("        ORDER_ID,")
                									 .append("        SERIAL_ID ")
                									 .append("    FROM")
                									 .append("        PWMROI_SERIAL_DELIVERY")
                									 .append("    WHERE ")
                									 .append("        	 INTERFACE_TRANSACTION_ID = ? ");

    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(interFaceTransactionId);
    	try
    	{
    		return dao.queryForList(selectSql.toString(), params);
    	}
    	catch (EmptyResultDataAccessException e)
    	{
    		return null;
    	}
    }
    
    @Override
    public List selectPwmroiSerialScrapDetails(String interFaceTransactionId)
    {
    	StringBuilder selectSql = new StringBuilder().append("    SELECT")
                									 .append("        ORDER_ID,")
                									 .append("        SERIAL_ID, ")
                									 .append("        OPER_KEY ")
                									 .append("    FROM")
                									 .append("        PWMROI_SERIAL_SCRAP")
                									 .append("    WHERE ")
                									 .append("        	 INTERFACE_TRANSACTION_ID = ? ");

    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(interFaceTransactionId);
    	try
    	{
    		return dao.queryForList(selectSql.toString(), params);
    	}
    	catch (EmptyResultDataAccessException e)
    	{
    		return null;
    	}
    }
    
    @Override
    public void updateDeliverSerialStatus(String orderId,String[] serialIdList)
    {
    	String serialId="";
    	StringBuilder updateSql = new StringBuilder().append("UPDATE  ")
    										   		 .append("    SFWID_SERIAL_DESC ")
    										   		 .append("SET ")
    										   		 .append("    UCF_SERIAL_VCH10 = 'DELIVERED' ")
    										   		.append("WHERE ")
    										   		 .append("    ORDER_ID = ?  ")
    										   		 .append("AND SERIAL_ID = ?  "); 

    	List<ParameterHolder> paramList = new ArrayList<ParameterHolder>();    	
    	for(int i = 0; i < serialIdList.length ; i++)    	
    	{
    		ParameterHolder params = new ParameterHolder();
    		params.addParameter(orderId);
    		params.addParameter(serialIdList[i]);
    		paramList.add(params);
    	} 
    	dao.batchUpdate(updateSql.toString(), paramList);
    }
    
    @Override
    public void updateScrapSerialStatus(String orderId,String[] serialIdList)
    {
    	String serialId="";
    	StringBuilder updateSql = new StringBuilder().append("UPDATE  ")
    										   		 .append("    SFWID_SERIAL_DESC ")
    										   		 .append("SET ")
    										   		 .append("    UCF_SERIAL_VCH10 = SUBSTR(UCF_SERIAL_VCH10,0,INSTR(UCF_SERIAL_VCH10,' INITIATED') - 1) ")
    										   		 .append("WHERE ")
    										   		 .append("    ORDER_ID = ?  ")
    										   		 .append("AND SERIAL_ID = ?  ");

    	List<ParameterHolder> paramList = new ArrayList<ParameterHolder>();    	
    	for(int i = 0; i < serialIdList.length ; i++)    	
    	{
    		ParameterHolder params = new ParameterHolder();
    		params.addParameter(orderId);
    		params.addParameter(serialIdList[i]);
    		paramList.add(params);
    	} 
    	dao.batchUpdate(updateSql.toString(), paramList);
    }
    
    @Override
    public String selectSerialNo(String orderId, String[] serialIdList)
    {
    	StringBuilder selectSql = new StringBuilder().append("    SELECT")
				 									 .append("        LISTAGG(SERIAL_NO, ';') within group (order by SERIAL_NO) AS SERIAL_NO")
				 									 .append("    FROM")
				 									 .append("        SFWID_SERIAL_DESC")
				 									 .append("    WHERE ")
				 									 .append("        	 ORDER_ID = ? ")
				 									 .append("    AND    SERIAL_ID IN( ");

    	ParameterHolder params = new ParameterHolder();
    	params.addParameter(orderId);
    	for(int i =0; i < serialIdList.length ; i++)
    	{
    		selectSql.append(wrapUtils.wrapValue(serialIdList[i]));
    		if(i < serialIdList.length -1 )
    		{
    			selectSql.append(",");
    		}
    		else
    		{
    			selectSql.append(")");
    		}
    		
    	}
    	try
    	{
    		return dao.queryForString(selectSql.toString(), params);
    	}
    	catch (EmptyResultDataAccessException e)
    	{
    		return null;
    	}
    }
    
    @Override
    public void updateErrorMessageForDelivery(String transactionId, String errorMessage)
    {
    	String serialId="";
    	StringBuilder updateSql = new StringBuilder().append("UPDATE  ")
    										   		 .append("    PWMROI_SERIAL_DELIVERY ")
    										   		 .append("SET ")
    										   		 .append("    ERROR_MESSAGE = ? ")
    										   		 .append("WHERE ")    										 
    										   		 .append("INTERFACE_TRANSACTION_ID = ?  ");

    	List<ParameterHolder> paramList = new ArrayList<ParameterHolder>();
    	
    	{
    		ParameterHolder params = new ParameterHolder();
    		params.addParameter(errorMessage);
    		params.addParameter(transactionId);
    		paramList.add(params);
    	} 
    	dao.batchUpdate(updateSql.toString(), paramList);
    }
    @Override
    public void updateErrorMessageForScrap(String transactionId, String errorMessage)
    {
    	String serialId="";
    	StringBuilder updateSql = new StringBuilder().append("UPDATE  ")
    										   		 .append("    PWMROI_SERIAL_SCRAP ")
    										   		 .append("SET ")
    										   		 .append("    ERROR_MESSAGE = ? ")
    										   		 .append("WHERE ")    										 
    										   		 .append("INTERFACE_TRANSACTION_ID = ?  ");

    	List<ParameterHolder> paramList = new ArrayList<ParameterHolder>();
    	
    	{
    		ParameterHolder params = new ParameterHolder();
    		params.addParameter(errorMessage);
    		params.addParameter(transactionId);
    		paramList.add(params);
    	} 
    	dao.batchUpdate(updateSql.toString(), paramList);
    }
	public int selectEngineManualCount(String orderId)
	{
		StringBuilder updateSql = new StringBuilder().append("select count(*) from pwmroi_order_subject_authority a,  ")
				 									 .append(" SFOR_SFWID_ORDER_SUBJECT b ")
				 									 .append(" where a.order_id = b.order_id")
				 									 .append(" and a.subject_no = b.subject_no ") 
				 									 .append(" and a.subject_rev = b.subject_rev ")
				 									 .append(" and a.order_id = ?")
				 									 .append(" and b.included_flag = ?");
		
					ParameterHolder params = new ParameterHolder();
					params.addParameter(orderId);
					params.addParameter("INCLUDED");
					
					return dao.queryForInt(updateSql.toString(), params);				
	}
	
	
	public void deleteDeliverSerial(String orderId,String serialIdList)
	{
		String[] st = serialIdList.split(SoluminaConstants.SEMI_COLON);
		String[] serial = null;

    	StringBuilder updateSql = new StringBuilder().append("DELETE FROM   ")
    										   		 .append("    PWMROI_SERIAL_DELIVERY ")
    										   		 .append("WHERE ")    										    										   		 
    										   		 .append("     ORDER_ID = ?  ")
    										   		 .append("AND SERIAL_ID = ?  "); 

    	  List<ParameterHolder> paramList = new ArrayList<ParameterHolder>();
          for (String serialNo : st)
          {
              serial = serialNo.split(",");
              
                  ParameterHolder params = new ParameterHolder();
                  params.addParameter(orderId);
                  params.addParameter(serial[0]);
                  paramList.add(params);
          }

          dao.batchUpdate(updateSql.toString(), paramList);

	}
	
	public void deleteDeliverScrap(String orderId,String serialIdList)
	{
		String[] st = serialIdList.split(SoluminaConstants.SEMI_COLON);
		String[] serial = null;

    	StringBuilder updateSql = new StringBuilder().append("DELETE FROM   ")
    										   		 .append("    PWMROI_SERIAL_SCRAP ")
    										   		 .append("WHERE ")    										    										   		 
    										   		 .append("     ORDER_ID = ?  ")
    										   		 .append("AND SERIAL_ID = ?  "); 

    	  List<ParameterHolder> paramList = new ArrayList<ParameterHolder>();
          for (String serialNo : st)
          {
              serial = serialNo.split(",");
              
                  ParameterHolder params = new ParameterHolder();
                  params.addParameter(orderId);
                  params.addParameter(serial[0]);
                  paramList.add(params);
          }

          dao.batchUpdate(updateSql.toString(), paramList);

	}
	
	//Defect 1862 & 1868
			public void insertReversedDeliveredSNHist(String order_id, String serial_id)
			{
				StringBuilder selectSql = new StringBuilder().append(" insert into PWUST_DELIVERY_COCKPIT_HIST")
						 .append("    (HIST_ID,")
						 .append("    HIST_ACTION,")
						 .append("    HIST_USERID,")
						 .append("    HIST_TIME_STAMP,")
						 .append("    ORDER_ID,")
						 .append("    SERIAL_ID,")
						 .append("    SERIAL_NO,")
						 .append("    SERIAL_NO_STATUS,")
						 .append("    DELIVER_SERIAL_STATUS,")
						 .append("    ENGINE_MANUAL,")
						 .append("    INTDT,")
						 .append("    INT_RESPDT,")
						 .append("    INT_RESPONSE,")
						 .append("    UPDT_USERID)")
						 .append("    SELECT PWUST_COCKPIT_HIST_SEQ.NEXTVAL,")
				         .append("    'INSERTED',")
						 .append("    ? ,")
						 .append("    SYSDATE,")
						 .append("    s.ORDER_ID, ")
						 .append("    s.serial_id, ")
						 .append("    S.SERIAL_NO, ")
						 .append("    S.SERIAL_STATUS, ")
						 .append("    SC.SCRAP_TYPE,")
						 .append("    D.ENGINE_MANUAL,")
						 .append("     CASE   ")
						 .append("    WHEN S.SERIAL_STATUS = 'SCRAP'")
						 .append("    THEN ")
						 .append("    (SELECT L2.TIME_STAMP")
						 .append("    FROM SFWID_SERIAL_DESC S2,")
						 .append("    PWMROI_SERIAL_DELIVERY D2,")
						 .append("    PW_S_T314_DISPOSITION_LOG L2,")
						 .append("    PWMROI_SERIAL_SCRAP SC2")
						 .append("    WHERE S2.ORDER_ID = D2.ORDER_ID(+)")
						 .append("    AND S2.SERIAL_ID = D2.SERIAL_ID(+)")
						 .append("    AND S2.ORDER_ID = SC2.ORDER_ID(+)")
						 .append("    AND S2.SERIAL_ID = SC2.SERIAL_ID(+)")
						 .append("    AND nvl(sc2.INTERFACE_TRANSACTION_ID, d2.INTERFACE_TRANSACTION_ID) = L2.MESSAGE_ID(+)")
						 .append("    AND S2.ORDER_ID in ")
						 .append("    (SELECT T.ORDER_ID ")
						 .append("    FROM SFWID_ORDER_DESC T,")
						 .append("    PWUST_SFWID_ORDER_DESC T1")
						 .append("    WHERE T.ORDER_ID = T1.ORDER_ID")
						 .append("    AND ((T.UCF_ORDER_VCH15 IS NULL")
						 .append("    AND NVL('', NULL) IS NULL)")
						 .append("    OR T.UCF_ORDER_VCH15 = NVL(T1.UCF_ORDER_VCH15, NULL))")
						 .append("    AND T.UCF_ORDER_VCH12 = T1.UCF_ORDER_VCH12 ")
						 .append("    and l2.message_id is not null")
						 .append("    and sc2.interface_transaction_id != 'X')")
						 .append("    AND S2.SERIAL_NO NOT LIKE '%-SPLIT%'")
						 .append("    and s2.serial_id = s.serial_id")
						 .append("    and sc2.serial_id = sc.serial_id)")
						 .append("    ELSE")
						 .append("    L.TIME_STAMP")
						 .append("    END AS INTDT,")
						 .append("    CASE")
						 .append("    WHEN S.SERIAL_STATUS = 'SCRAP'")
						 .append("    THEN")
						 .append("    (SELECT R3.TIME_STAMP")
						 .append("    FROM SFWID_SERIAL_DESC S3,")
						 .append("    PWMROI_SERIAL_DELIVERY D3,")
						 .append("    PW_S_T314_DISPOSITION_LOG L3,")
						 .append("    PWMROI_SERIAL_SCRAP SC3,")
						 .append("    PW_S_T314_DISPOSITION_RPLY R3")
						 .append("    WHERE S3.ORDER_ID = D3.ORDER_ID(+)")
						 .append("    AND S3.SERIAL_ID = D3.SERIAL_ID(+)")
						 .append("    AND S3.ORDER_ID = SC3.ORDER_ID(+)")
						 .append("    AND S3.SERIAL_ID = SC3.SERIAL_ID(+)")
						 .append("    AND L3.MESSAGE_ID = R3.MESSAGE_ID(+)")
						 .append("    AND nvl(sc3.INTERFACE_TRANSACTION_ID,")
						 .append("    d3.INTERFACE_TRANSACTION_ID) = L3.MESSAGE_ID(+)")
						 .append("    AND S3.ORDER_ID in")
						 .append("    (SELECT T.ORDER_ID")
						 .append("    FROM SFWID_ORDER_DESC T,")
						 .append("    PWUST_SFWID_ORDER_DESC T1")
						 .append("    WHERE T.ORDER_ID = T1.ORDER_ID")
						 .append("    AND ((T.UCF_ORDER_VCH15 IS NULL")
						 .append("    AND NVL('', NULL) IS NULL)")
						 .append("    OR T.UCF_ORDER_VCH15 = NVL(T1.UCF_ORDER_VCH15, NULL))")
						 .append("    AND T.UCF_ORDER_VCH12 = T1.UCF_ORDER_VCH12")
						 .append("    and l3.time_stamp is not null")
						 .append("    and sc3.interface_transaction_id != 'X')")
						 .append("    AND S3.SERIAL_NO NOT LIKE '%-SPLIT%'")
						 .append("    and s3.serial_id = s.serial_id")
						 .append("    and sc3.serial_id = sc.serial_id)")
						 .append("    ELSE")
						 .append("    L.TIME_STAMP")
						 .append("    END as INT_RESPDT,")
						 .append("    CASE")
						 .append("    WHEN S.SERIAL_STATUS = 'SCRAP'")
						 .append("    THEN")
						 .append("    (SELECT sc4.error_message")
						 .append("    FROM SFWID_SERIAL_DESC S4,")
						 .append("    PWMROI_SERIAL_DELIVERY D4,")
						 .append("    PW_S_T314_DISPOSITION_LOG L4,")
						 .append("    PW_S_T314_DISPOSITION_RPLY R4,")
						 .append("    PWMROI_SERIAL_SCRAP SC4")
						 .append("    WHERE S4.ORDER_ID = D4.ORDER_ID(+)")
						 .append("    AND S4.SERIAL_ID = D4.SERIAL_ID(+)")
						 .append("    AND S4.ORDER_ID = SC4.ORDER_ID(+)")
						 .append("    AND S4.SERIAL_ID = SC4.SERIAL_ID(+)")
						 .append("    AND nvl(sc4.INTERFACE_TRANSACTION_ID,")
						 .append("    d4.INTERFACE_TRANSACTION_ID) = L4.MESSAGE_ID(+)")
						 .append("    AND L4.MESSAGE_ID = R4.MESSAGE_ID(+)")
						 .append("    AND S4.ORDER_ID in")
						 .append("    (SELECT T.ORDER_ID")
						 .append("    FROM SFWID_ORDER_DESC T,")
						 .append("    PWUST_SFWID_ORDER_DESC T1")
						 .append("    WHERE T.ORDER_ID = T1.ORDER_ID")
						 .append("    AND ((T.UCF_ORDER_VCH15 IS NULL AND")
						 .append("    NVL('', NULL) IS NULL)")
						 .append("    OR T.UCF_ORDER_VCH15 = NVL(T1.UCF_ORDER_VCH15, NULL))")
						 .append("    AND T.UCF_ORDER_VCH12 = T1.UCF_ORDER_VCH12")
						 .append("    and l4.message_id is not null")
						 .append("    and sc4.interface_transaction_id != 'X')")
						 .append("    AND S4.SERIAL_NO NOT LIKE '%-SPLIT%'")
						 .append("    and s4.serial_id = s.serial_id")
						 .append("    and sc4.serial_id = sc.serial_id)")
						 .append("    WHEN d.order_id is null and sc.order_id is null")
						 .append("    and  r.time_stamp is null")
						 .append("    THEN")
						 .append("    null")
						 .append("    WHEN (d.order_id is not null or sc.order_id is not null)")
						 .append("    and r.time_stamp is not null")
						 .append("    and (d.error_message is null and sc.error_message is null)")
						 .append("    then 'SUCCESS'")
						 .append("    WHEN d.order_id is not null ")
						 .append("    and d.error_message is not null")
						 .append("    then d.error_message")
						 .append("    WHEN sc.order_id is not null")
						 .append("    and sc.error_message is not null")
						 .append("    then sc.error_message")
						 .append("    END as INT_RESPONSE,")
						 .append("    NVL(SC.UPDT_USERID, D.UPDT_USERID) AS USERID")
						 .append("    FROM SFWID_SERIAL_DESC S,")
						 .append("    PWMROI_SERIAL_DELIVERY D,")
						 .append("    PW_S_T314_DISPOSITION_LOG  L,")
						 .append("    PW_S_T314_DISPOSITION_RPLY R,")
						 .append("    PWMROI_SERIAL_SCRAP SC,")
						 .append("    SFWID_ORDER_DESC T1")
						 .append("    WHERE S.ORDER_ID = D.ORDER_ID(+)")
						 .append("    AND S.SERIAL_ID = D.SERIAL_ID(+)")
						 .append("   AND S.ORDER_ID = SC.ORDER_ID(+)")
						 .append("    AND S.SERIAL_ID = SC.SERIAL_ID(+)")
						 .append("    AND nvl(sc.INTERFACE_TRANSACTION_ID,")
						 .append("    d.INTERFACE_TRANSACTION_ID) = L.MESSAGE_ID(+)")
						 .append("    AND L.MESSAGE_ID = R.MESSAGE_ID(+)")
						 .append("     AND S.ORDER_ID = ? ")
						 .append("     AND S.SERIAL_ID = ? ")
						 .append("    AND S.ORDER_ID = T1.ORDER_ID")
						 .append("    AND S.SERIAL_NO NOT LIKE '%-SPLIT%'")
						 .append("    AND S.SERIAL_STATUS IN ('COMPLETE', 'SCRAP')");

				ParameterHolder params = new ParameterHolder();
				params.addParameter(ContextUtil.getUsername());
				params.addParameter(order_id);
				params.addParameter(serial_id);
				
			    dao.insert(selectSql.toString(), params);	
			}
			
			public void deleteDeliveredSN(String order_id, String serial_id, String engine_manual)
			{
				
				StringBuilder selectSql = new StringBuilder().append(" delete")
						 .append("    from PWMROI_SERIAL_DELIVERY")
						 .append("    where order_id = ?")
						 .append("    and serial_id = ?")
						 .append("    and engine_manual = ?");

				ParameterHolder params = new ParameterHolder();
				params.addParameter(order_id);
				params.addParameter(serial_id);
				params.addParameter(engine_manual);

			    dao.delete(selectSql.toString(),params);
			}
			
			public void updateSerialStatus(String order_id, String serial_id, String lot_id)
			{
				
				StringBuilder selectSql = new StringBuilder().append(" update sfwid_serial_desc")
						 .append("    set ucf_serial_vch10 = null")
						 .append("    where order_id = ?")
						 .append("    and serial_id = ?")
						 .append("    and lot_id = ?");

				ParameterHolder params = new ParameterHolder();
				params.addParameter(order_id);
				params.addParameter(serial_id);
				params.addParameter(lot_id);

			    dao.update(selectSql.toString(),params);	
			}
			
			//REOPEN ORDER IF CLOSED for scrap
			public void updateOrderStatusToActive(String order_id)
			{	   
				StringBuilder selectSql = new StringBuilder().append(" UPDATE SFMFG.SFWID_ORDER_DESC T")
						.append("    SET T.ORDER_STATUS    = 'ACTIVE', ")
						.append("    ORDER_SCRAP_QTY   = ORDER_SCRAP_QTY - 1,")
						.append("    ACTUAL_END_DATE   = NULL,")
						.append("    STATUS_CHG_REASON = 'REOPEN',")
						.append("    UPDT_USERID       = ?,")
						.append("    TIME_STAMP = SYSDATE")
						.append("    WHERE ORDER_ID = ?");

				ParameterHolder params = new ParameterHolder();
				params.addParameter(ContextUtil.getUsername());
				params.addParameter(order_id);

				dao.update(selectSql.toString(),params);
			}
			
			//REOPEN ORDER IF CLOSED 
					public void updateOrderStatusCompleteToActive(String order_id)
					{	   
						StringBuilder selectSql = new StringBuilder().append(" UPDATE SFMFG.SFWID_ORDER_DESC T")
								.append("    SET T.ORDER_STATUS    = 'ACTIVE', ")
								.append("    ACTUAL_END_DATE   = NULL,")
								.append("    STATUS_CHG_REASON = 'REOPEN',")
								.append("    UPDT_USERID       = ?,")
								.append("    TIME_STAMP = SYSDATE")
								.append("    WHERE ORDER_ID = ?");

						ParameterHolder params = new ParameterHolder();
						params.addParameter(ContextUtil.getUsername());
						params.addParameter(order_id);

						dao.update(selectSql.toString(),params);
					}
			
			public void updateSerialStatusToInProcess(String order_id, String serial_id, String  lot_id)
			{	  
				StringBuilder selectSql = new StringBuilder().append(" UPDATE SFMFG.SFWID_SERIAL_DESC")
						.append("    SET SERIAL_STATUS     = 'IN PROCESS',")
						.append("    ACTUAL_END_DATE   = NULL,")
						.append("    STATUS_CHG_REASON = 'REOPEN',")
						.append("    UCF_SERIAL_VCH10  = NULL,")
						.append("    UPDT_USERID       = ?,")
						.append("    TIME_STAMP = SYSDATE")
						.append("    WHERE ORDER_ID = ?")
						.append("    AND SERIAL_ID = ?")
						.append("    AND LOT_ID = ?");
				

				ParameterHolder params = new ParameterHolder();
				params.addParameter(ContextUtil.getUsername());
				params.addParameter(order_id);
				params.addParameter(serial_id);
				params.addParameter(lot_id);

				dao.update(selectSql.toString(),params);
			}
			
			//when serial oper status is COMPLETE
			public void updateSerialOperStatusToActive(String order_id, String serial_id, Number step_key)
			{	
				StringBuilder selectSql = new StringBuilder().append(" UPDATE SFMFG.SFWID_SERIAL_OPER")
						 .append("    SET SERIAL_OPER_STATUS = 'ACTIVE',")
						 .append("    ACTUAL_END_DATE    = NULL,")
						 .append("    STATUS_CHG_REASON  = 'REOPEN',")
						 .append("    UPDT_USERID        = ?,")
						 .append("    TIME_STAMP = SYSDATE")
						 .append("    WHERE ORDER_ID = ?")
						 .append("    AND SERIAL_ID = ?")
						 .append("    AND STEP_KEY = ?");

				ParameterHolder params = new ParameterHolder();
				params.addParameter(ContextUtil.getUsername());
				params.addParameter(order_id);
				params.addParameter(serial_id);
				params.addParameter(step_key);

			    dao.update(selectSql.toString(),params);	
			}
			
			//when serial oper status is CANCEL (SCRAP)
			public void updateSerialOperStatusToInQueue(String order_id, String serial_id, Number step_key)
			{

				StringBuilder selectSql = new StringBuilder().append(" UPDATE SFMFG.SFWID_SERIAL_OPER")
						 .append("    SET SERIAL_OPER_STATUS = 'IN QUEUE',")
						 .append("    STATUS_CHg_REASON  = 'REOPEN',")
						 .append("    UPDT_USERID = ?,")
						 .append("    TIME_STAMP = SYSDATE")
						 .append("    WHERE ORDER_ID = ?")
						 .append("    AND SERIAL_ID = ?")
						 .append("    AND STEP_KEY = ?");

				ParameterHolder params = new ParameterHolder();
				params.addParameter(ContextUtil.getUsername());
				params.addParameter(order_id);
				params.addParameter(serial_id);
				params.addParameter(step_key);

			    dao.update(selectSql.toString(),params);	
			}
			
			//Get last buyoff_status
			public List  selectLastBuyoffStatus(String serial_id, String order_id, Number oper_key)
			{
				
				StringBuilder selectSql = new StringBuilder().append(" select (select buyoff_status")
						 .append("    from SFWID_SERIAL_OPER_BUYOFF bs")
						 .append("    WHERE bs.order_id = T.ORDER_ID")
						 .append("    AND BS.OPER_KEY = T.OPER_KEY")
						 .append("    AND BS.STEP_KEY = T.STEP_KEY")
						 .append("    AND BS.SERIAl_ID = ?")
						 .append("    AND BS.BUYOFF_ID = T.BUYOFF_ID ) AS BUYOFF_STATUS,T.*")
						 .append("    from SFWID_OPER_BUYOFF t")
						 .append("    where order_id = ?")
						 .append("    and display_line_no = (select min(display_line_no) from SFWID_OPER_BUYOFF j where j.order_id = t.order_id and j.oper_key = t.oper_key and j.ref_id = t.ref_id)")
						 .append("    and ref_id in (select ref_id")
						 .append("    from SFWID_ORDER_EMBEDDED_CONTROLS t")
						 .append("    where order_id = ?")
						 .append("    and oper_key = ?")
						 .append("    and control_seq_no  = (select max(control_seq_no)")
						 .append("    from SFWID_ORDER_EMBEDDED_CONTROLS z")
						 .append("    where z.tag_type = t.tag_type")
						 .append("    and z.order_id = t.order_id")
						 .append("    and z.oper_key = t.oper_key))");

				ParameterHolder params = new ParameterHolder();
				params.addParameter(serial_id);
				params.addParameter(order_id);
				params.addParameter(order_id);
				params.addParameter(oper_key);
				
				try 
				{
			    return dao.queryForList(selectSql.toString(),params);
				}
				catch (EmptyResultDataAccessException e)
				{
					return null;
				}
			}
			
			
			//REOPEN LAST BUYOFF IN EACH OP IF LAST BUYOFF HAS ACCEPT STATUS -when CANCEL (SCRAP)
			public void updateBuyoffStatusToOpen(String order_id, Number oper_key, Number step_key, String buyoff_id, String serial_id, String buyoff_status)
			{
			
				StringBuilder selectSql = new StringBuilder().append(" UPDATE SFMFG.SFWID_SERIAL_OPER_BUYOFF")
						 .append("    SET BUYOFF_STATUS = 'OPEN',")
						 .append("    UCF_SRLOPBUYOFF_VCH2 = NULL,")
						 .append("    UCF_SRLOPBUYOFF_VCH3 = NULL,")
						 .append("    PERCENT_COMPLETE = NULL,")
						 .append("    UPDT_USERID = ?,")
						 .append("    TIME_STAMP = SYSDATE")
						 .append("    WHERE ORDER_ID = ?")
						 .append("    AND OPER_KEY = ?")
						 .append("    AND STEP_KEY = ?")
						 .append("    AND BUYOFF_ID = ?")
						 .append("    AND SERIAL_ID = ?")
						 .append("    AND BUYOFF_STATUS = ?");

				ParameterHolder params = new ParameterHolder();
				params.addParameter(ContextUtil.getUsername());
				params.addParameter(order_id);
				params.addParameter(oper_key);
				params.addParameter(step_key);
				params.addParameter(buyoff_id);
				params.addParameter(serial_id);
				params.addParameter(buyoff_status);

			    dao.update(selectSql.toString(),params);	
			}
			
			//WHEN CANCEL (SCRAP) - OR PENDING, IN QUEUE, COMPLETE, STOP
			public void updateScrapToInProcess(String part_no, String lot_no, String serial_no, String scrap_flag)
			{
				StringBuilder selectSql = new StringBuilder().append(" UPDATE SFMFG.SFWID_AS_WORKED_ITEM")
						 .append("    SET AS_WORKED_ITEM_STATUS = 'IN PROCESS',")
						 .append("    SCRAP_FLAG = 'N',")
						 .append("    LOT_SCRAP_QTY = NULL,")
						 .append("    TIME_STAMP = SYSDATE")
						 .append("    WHERE part_no = ?")
						 .append("    AND LOT_NO = ?")
						 .append("    AND SERIAL_NO = ?")
						 .append("    AND SCRAP_FLAG = ?");

				ParameterHolder params = new ParameterHolder();
				params.addParameter(part_no);
				params.addParameter(lot_no);
				params.addParameter(serial_no);
				params.addParameter(scrap_flag);

			    dao.update(selectSql.toString(),params);	
			}
			
			//Get list of serial_no
			public List selectSerialNoInfo(String serial_no)
			{	
				
				StringBuilder selectSql = new StringBuilder().append(" select B.order_id, C.serial_id,")
						 .append("     D.order_status, ")
						 .append("     C.part_no, ")
						 .append("     C.lot_no,")
						 .append("     A.serial_no,")
						 .append("     C.scrap_flag,")
						 .append("     A.serial_status,")
						 .append("     C.lot_id,")
						 .append("     A.actual_start_date")
						 .append("     FROM SFWID_SERIAL_DESC A,")
						 .append("     SFWID_LOT_DESC B,")
						 .append("     SFWID_AS_WORKED_ITEM C,")
						 .append("     SFWID_ORDER_DESC     D")
						 .append("     WHERE A.Serial_No = ?")
						 .append("     AND A.ORDER_ID = B.ORDER_ID  ")
						 .append("     AND A.ORDER_ID = D.ORDER_ID")
						 .append("     AND B.ORDER_ID = D.ORDER_ID")
						 .append("     AND C.PART_NO = D.PART_NO")
						 .append("     AND A.LOT_ID = B.LOT_ID")
						 .append("     AND A.SERIAL_ID = C.SERIAL_ID")
						 .append("     AND A.LOT_ID = C.LOT_ID");

				ParameterHolder params = new ParameterHolder();
				params.addParameter(serial_no);
				
			    return dao.queryForList(selectSql.toString(),params);
			}
			
			 
			
			//get serial_oper_status
			public String selectSerialOperStatus(String order_id, String serial_id)
			{
				StringBuilder selectSql = new StringBuilder().append(" select serial_oper_status From SFWID_SERIAL_OPER")
						 .append("    where order_id = ?")
						 .append("    and serial_id = ?");

				ParameterHolder params = new ParameterHolder();
				params.addParameter(order_id);
				params.addParameter(serial_id);
				
				try{	
					return dao.queryForString(selectSql.toString(),params);
				}
				catch (EmptyResultDataAccessException e)
				{
					return null;
				}
			}
			
			//get engine_manual
			public String selectEngineManual(String order_id, String serial_id)
			{
				StringBuilder selectSql = new StringBuilder().append(" select engine_manual from PWMROI_SERIAL_DELIVERY")
						 .append("    where order_id = ?")
						 .append("    and serial_id = ?");

				ParameterHolder params = new ParameterHolder();
				params.addParameter(order_id);
				params.addParameter(serial_id);
				
				try{
					return dao.queryForString(selectSql.toString(),params);
				}
				catch (EmptyResultDataAccessException e)
				{
					return null;
				}
			}
				
			public List selectOperKey(String order_id, String serial_id) {
				 
				StringBuilder selectSql = new StringBuilder().append(" select oper_key, serial_oper_status, actual_start_date from sfwid_serial_oper")
						 .append("    where order_id = ?")
						 .append("    and serial_id = ?");

				ParameterHolder params = new ParameterHolder();
				params.addParameter(order_id);
				params.addParameter(serial_id);
				
			   return dao.queryForList(selectSql.toString(),params);
			}
			
			
			public void deletePWMROISerialScrap(String order_id, String serial_id)
			{
				
				StringBuilder selectSql = new StringBuilder().append(" DELETE FROM SFMFG.PWMROI_SERIAL_SCRAP")
						 .append("    WHERE ORDER_ID = ? ")
						 .append("    AND SERIAL_ID = ?");

				ParameterHolder params = new ParameterHolder();
				params.addParameter(order_id);
				params.addParameter(serial_id);

			    dao.delete(selectSql.toString(),params);
			}
			
			//when serial oper status is CLOSE  
			         
			 
			 public void updateOperStatusToActive (String order_id, Number oper_key) {
				 
				 StringBuilder selectSql = new StringBuilder().append(" UPDATE SFMFG.SFWID_OPER_DESC ")
							.append("    SET OPER_STATUS = 'ACTIVE',    ")
							.append("    ACTUAL_END_DATE    = NULL,     ")
							.append("    STATUS_CHG_REASON  = 'REOPEN', ")
							.append("    UPDT_USERID        = ?,        ")
							.append("    TIME_STAMP = SYSDATE           ")
							.append("    WHERE ORDER_ID = ?             ")
							.append("	 AND OPER_KEY = ?               ")
							.append("	 AND STEP_KEY = -1              ");

					ParameterHolder params = new ParameterHolder();
					params.addParameter(ContextUtil.getUsername());
					params.addParameter(order_id);
					params.addParameter(oper_key);

					dao.update(selectSql.toString(),params);
			 }

			
			 
	         public void updateOperStatusToInQueue(String order_id,Number oper_key) {
				 
				 StringBuilder selectSql = new StringBuilder().append("  UPDATE SFMFG.SFWID_OPER_DESC ")
							.append("    SET OPER_STATUS = 'IN QUEUE', ")
							.append("    STATUS_CHG_REASON  = 'REOPEN',       ")
							.append("    UPDT_USERID        = ?,              ")
							.append("    TIME_STAMP = SYSDATE                 ")
							.append("    WHERE ORDER_ID = ?                   ")
							.append("	 AND OPER_KEY = ?                     ")
							.append("	 AND STEP_KEY = -1                    ");

					ParameterHolder params = new ParameterHolder();
					params.addParameter(ContextUtil.getUsername());
					params.addParameter(order_id);
					params.addParameter(oper_key);

					dao.update(selectSql.toString(),params);
			 }


			//End Defect 1862 & 1868
	
}
