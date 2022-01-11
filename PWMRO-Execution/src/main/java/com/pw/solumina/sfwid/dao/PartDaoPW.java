/*
 *  This unpublished work, first created in 2014 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2014, 2015.  All rights reserved.  Information contained 
 *  herein is proprietary and confidential and improper use or disclosure 
 *  may result in civil and penal sanctions.
 *
  *  File:    PartPW.java
 * 
 *  Created: 2014-10-8
 * 
 *  Author:  Nazaire Gnassounou
 * 
 *  Revision History
 * 
 *  Date        Who          	Description
 *  --------    ------------ 	---------------------------------------------------
 *  2014-06-24	Nazaire Gnassounou		SAEE_WOE_19  Display new parts by adding extra fields
 *  2014-12-03  S. Lusardi              SAEE_WOE_19   Added Alternate Part selection methods
 *  2014-12-22  D. Miron                SAEE-WOE_12  Added method insertOrderPartDCValues and selectPartDCOperStepInfo.
 *  2015-01-05  D. Miron                SAEE-WOE_12  Added method selectSerialOperationItemStatus.
 *  2015-01-28	N. St. Jarre			SAIP_MD_M101 Defect 1469 - Milk Run post processing now updates part qty from plan, get & display sfwid_oper_items.UCF_PLAN_ITEM_VCH3
 *  2015-02-27	A. Fields				SAIE_TR_T123 Defect 1618 - Added method existsPartDC
 *  2015-03-21  D. Miron                SAEE_WOE_20 Defect 1670 - Added method selectOrderNonSerialParts
 *  2015-04-09  B. Corson               SAEE_WOE_19 Defect 1695 - Part Type - Source should be VCH1 not VCH5
 *  2015-04-17  D. Miron                SAEE_WOE_12 Defect 1710 - Changed selectOperPartDCOperStepInfo to not throw exception when no data found.
 *  2015-04-30  A. Fields				SAEE_WOE_19 Defect 1724 - Added method getSelectedAltPart
 *  2015-06-09	A. Fields				SAEE_WOE_19 Defect 1765 - Modified method selectAlternateItems
 *  2016-06-02	N. Mazza				SAEE_WOE_19 Defect 1971 - Serialized ALT parts allow selecting the primary ALT part
 *  2016-07-01  N. Mazza		        SAEE_WOE_19  Defect 2075 - no-serialized ALTs allow spilt qtys
*/
package com.pw.solumina.sfwid.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.ParameterHolder;
import com.singularsys.jep.misc.functions.Case;

import com.pw.solumina.migration.application.SoluminaMigrationDaoPW;

public class PartDaoPW {
	
	@Reference
    private JdbcDaoSupport eds;
	
	@Reference
	private SoluminaMigrationDaoPW migDaoPW;
      
    public String getPartType(String orderId,
    		                  Number operationKey,
    		                  Number stepKey,
    		                  String partDatColId)
    {
    	String returnString = null;
    	StringBuffer selectSql = new StringBuffer()
    	.append(" SELECT ")
    	.append(" UCF_PLAN_ITEM_VCH1 ")            //SAEE_WOE_19 Defect 1695 - Should be VCH1 not VCH5
    	.append(" FROM SFWID_OPER_ITEMS ")
    	.append("    WHERE  ORDER_ID =? ")
    	.append("	   AND OPER_KEY = ?  ")
    	.append("	   AND STEP_KEY = ? ")
    	.append("	   AND PART_DAT_COL_ID = ? "); //12/9/14 CHANGED FROM REF_ID
    	
    	ParameterHolder params = new ParameterHolder();	
    	params.addParameter(orderId);
    	params.addParameter(operationKey);
    	params.addParameter(stepKey);        
    	params.addParameter(partDatColId); //12/9/14 CHANGED FROM REF_ID
    	try
    	{
    		returnString = eds.queryForString(selectSql.toString(), params);
    	}
    	catch(Exception e){
    		returnString = null;
    	}//string stays empty

    	return returnString;
    } 
        
       
    public String getBluePrintNo(String orderId,
    		Number operationKey,
    		Number stepKey, 
    		String partDatColId) //SGL 12/9/14
    {
    	String returnString = null;
    	StringBuffer selectSql = new StringBuffer()
    	.append(" SELECT ")
    	.append("    UCF_PLAN_ITEM_VCH14 ")
    	.append(" FROM SFWID_OPER_ITEMS ")
    	.append("    WHERE  ORDER_ID =? ")
    	.append("	   AND OPER_KEY = ?  ")
    	.append("	   AND STEP_KEY = ? ")
    	.append("	   AND PART_DAT_COL_ID = ? "); //12/9/14 CHANGED FROM REF_ID
    	ParameterHolder params = new ParameterHolder();	
    	params.addParameter(orderId);
    	params.addParameter(operationKey);
    	params.addParameter(stepKey);        
    	params.addParameter(partDatColId); //12/9/14 CHANGED FROM REF_ID
    	try
    	{
    		returnString = eds.queryForString(selectSql.toString(), params);
    	}
    	catch(Exception e){
    		returnString = null;
    	}//string stays empty

    	return returnString;
    }
    
    
    public String FindNo(String orderId,
    		Number operationKey,
    		Number stepKey,
    		String partDatColId) //12/9/14 CHANGED FROM REF_ID
    {
    	String returnString = null;
    	StringBuffer selectSql = new StringBuffer()
    	.append(" SELECT ")
    	.append("   UCF_PLAN_ITEM_VCH2 ")
    	.append(" FROM SFWID_OPER_ITEMS ")
    	.append("    WHERE  ORDER_ID =? ")
    	.append("	   AND OPER_KEY = ?  ")
    	.append("	   AND STEP_KEY = ? ")
    	.append("	   AND PART_DAT_COL_ID = ? "); //12/9/14 CHANGED FROM REF_ID
    	ParameterHolder params = new ParameterHolder();	
    	params.addParameter(orderId);
    	params.addParameter(operationKey);
    	params.addParameter(stepKey);   
        params.addParameter(partDatColId); //12/9/14 CHANGED FROM REF_ID

    	try
    	{
    		returnString = eds.queryForString(selectSql.toString(), params);
    	}
    	catch(Exception e){}//string stays empty

    	return returnString;
    }
    // Start SGL SAEE_WOE_W19
    
	  @SuppressWarnings({ "rawtypes" })
	   public List selectAlternateItemsExist(String orderId, String operKey, String stepKey, String partDatColId)
	    {
		   StringBuffer selectSql = new StringBuffer().append(" select count(*) ")
				                                      .append("       from   sfmfg.sfwid_order_item_alt_xref order_xref,")
				                                      .append("              sfmfg.sfwid_oper_items oper_item,")
				                                      .append("              sfmfg.sfpl_item_desc_master_all plnd_part,")
				                                      .append("              sfmfg.pwue_milkrun_detail milkrun_detail")
				                                      .append("       where  order_xref.order_id = ?")
				                                      .append("         and  order_xref.oper_key = ?")
				                                      .append("    		and  order_xref.step_key = ?")
				                                      .append("         and  order_xref.part_dat_col_id = ?")
				                                      .append("         and  oper_item.order_id = order_xref.order_id")
				                                      .append("         and  oper_item.oper_key = order_xref.oper_key")
				                                      .append("         and  oper_item.step_key = order_xref.step_key")
				                                      .append("         and  oper_item.part_dat_col_id = order_xref.part_dat_col_id")
				                                      .append("         and  milkrun_detail.order_id = order_xref.order_id")
				                                      .append("         and  milkrun_detail.position_no = oper_item.position_no")
				                                      .append("         and  plnd_part.part_no = milkrun_detail.component_part_no")
				                                      .append("         and ((milkrun_detail.item_code in ('INT','PKI','RPI') and milkrun_detail.qty = '0') ")
				                                      .append("          or  (milkrun_detail.item_code in ('ALT','PKA','RPA') and order_xref.asgnd_item_id = plnd_part.item_id)) ");
		   
				                                     
    	
	    	ParameterHolder parameters = new ParameterHolder();
	    	parameters.addParameter(orderId);
	    	parameters.addParameter(operKey);
	    	parameters.addParameter(stepKey);
	    	parameters.addParameter(partDatColId);
	    	
	    	int iCount= eds.queryForInt(selectSql.toString(), parameters);

	        String strAltsExist = null;
	        
	        if (iCount == 0) {
	        	strAltsExist = SoluminaConstants.N;
	        }else {
	        	strAltsExist = SoluminaConstants.Y;
	        }

	       selectSql = new StringBuffer().append(" select count(*) ")
	        		.append("       from   sfmfg.sfwid_oper_items i ")
	        		.append("       where  i.order_id = ?")
	        		.append("         and  i.oper_key = ?")
	        		.append("    	  and  i.step_key = ?")
	        		.append("         and  i.part_dat_col_id = ?")
	        		.append("         and  i.serial_flag = 'N' ")
	        		.append("         and  i.ucf_plan_item_vch1 in ('ALT','PKA','RPA') ");
	        	
	        ParameterHolder param = new ParameterHolder();
	        param.addParameter(orderId);
	        param.addParameter(operKey);
	        param.addParameter(stepKey);
	        param.addParameter(partDatColId);

	        iCount= eds.queryForInt(selectSql.toString(), param);

	        String strNonSNAltsExist = null;

	        if (iCount == 0) {
	        	strNonSNAltsExist = SoluminaConstants.N;
	        }else {
	        	strNonSNAltsExist = SoluminaConstants.Y;
	        }
	        
	        List altsExistList = new ArrayList();

	        Map altsExistMap = new ListOrderedMap();

	        altsExistMap.put("ALTS_EXIST", strAltsExist);
	        altsExistMap.put("NONSN_ALTS_EXIST", strNonSNAltsExist);
	        altsExistMap.put("SECURITY_GROUP", null);
	        altsExistList.add(altsExistMap);
	        
	        return altsExistList;
	    }
	  

	  @SuppressWarnings({ "rawtypes" })
	   public List selectAlternateItems(String orderId, String operKey, String stepKey, String partDatColId)
	    {
		   StringBuffer selectSql = new StringBuffer().append(" SELECT ")
				   .append(" P.PART_NO, ")
				   .append(" 'PART' AS ITEM_TYPE, ")
				   .append(" CASE ")
				   .append(" WHEN P.ITEM_ID = X.ASGND_ITEM_ID AND x.ASSIGNED_ITEM_FLAG = 'Y' then 'Y' ")						   
				   .append(" ELSE 'N' ")
				   .append(" END AS ASSIGNED, ")
				   .append(" O.PART_ACTION, ")
				   .append(" P.SECURITY_GROUP, ")
				   .append(" O.SECURITY_GROUP ")
				   .append(" FROM ")
				   .append(" sfmfg.sfpl_item_desc_master_all p, ")
				   .append(" sfmfg.sfwid_order_item_alt_xref x, ")
				   .append(" sfmfg.sfwid_oper_items o, ")
				   .append(" sfmfg.pwue_milkrun_detail m ")
				   .append(" WHERE ")
				   .append(" x.order_id = ? ")
				   .append(" and   x.oper_key = ? ")
				   .append(" and   x.step_key = ? ")
				   .append(" and   x.part_dat_col_id = ? ")
				   .append(" and   o.order_id = x.order_id ")
				   .append(" and   o.oper_key = x.oper_key ")
				   .append(" and   o.step_key = x.step_key ")
				   .append(" and   o.part_dat_col_id = x.part_dat_col_id")
				   .append(" and   m.order_id = x.order_id ")
				   .append(" and   m.position_no = o.position_no ")
				   .append(" and ((m.item_code in ('INT','PKI','RPI') and m.qty = '0') ")
				   .append("       or  m.item_code in ('ALT','PKA','RPA')) ")
				   .append(" and   p.part_no = m.component_part_no ");
						       	
	    	ParameterHolder parameters = new ParameterHolder();
	    	parameters.addParameter(orderId);
	    	parameters.addParameter(operKey);
	    	parameters.addParameter(stepKey);
	    	parameters.addParameter(partDatColId);

	    	List returnString = eds.queryForList(selectSql.toString(), parameters);
	    	return returnString;  



	    } 
	  
	  @SuppressWarnings({ "rawtypes" })
	   public List altAssignmentValidation(String partAction, String assignedItemFlag, String serialFlag, String orderId, Number operKey, Number stepKey, String partDatColId)
	    {
		  String returnString = null;
		  StringBuffer selectSql = new StringBuffer()
		  .append(" SELECT ASSIGNED_ITEM_FLAG")
		  .append(" FROM SFWID_ORDER_ITEM_ALT_XREF A ")
		  .append("    WHERE  ORDER_ID =? ")
		  .append("	   AND OPER_KEY = ?  ")
		  .append("	   AND STEP_KEY = ? ")
		  .append("	   AND PART_DAT_COL_ID = ? "); 

		  ParameterHolder params = new ParameterHolder();	
		  params.addParameter(orderId);
		  params.addParameter(operKey);
		  params.addParameter(stepKey);        
		  params.addParameter(partDatColId); 

		  returnString = eds.queryForString(selectSql.toString(), params);

		  List altsExistList = new ArrayList();
		  Map altsExistMap = new ListOrderedMap();

		  altsExistMap.put("ALTS_ASSIGNED", returnString);
		  altsExistMap.put("SECURITY_GROUP", null);
		  altsExistList.add(altsExistMap);
		  return altsExistList;
	    }

	// End SGL SAEE_WOE_W19
	  
		/**
		 * Returns oper_key and step_no from SFWID_OPER_ITEMS
		 * SAEE_WOE_12
		 * 
		 * Author: David Miron - December 22, 2014
		 * 
		 * @author C079222
		 * @return 
		 *
		 */
	    // Defect 1710
		public Map selectOperPartDCOperStepInfo(String orderId, String operNo, Number stepKey, String partDatColId) 
		{
			
			Map returnMap = null;
			List list = null;

			StringBuffer selectSql = new StringBuffer().append(" SELECT ")
					                                   .append("   OPER_KEY, ")
													   .append("   STEP_NO, ")
													   .append("   SERIAL_FLAG, ")
													   .append("   PART_ACTION, ")
													   .append("   UCF_PLAN_ITEM_VCH1 as PART_TYPE, ")
													   .append("   PLND_ITEM_QTY ")
													   .append(" FROM ")
													   .append("   SFWID_OPER_ITEMS ")
													   .append(" WHERE ")
													   .append("   ORDER_ID = ? ")
													   .append(" AND " )
													   .append("   OPER_NO = ? ")
													   .append(" AND " )
													   .append("   STEP_KEY = ? ")
													   .append(" AND " )
													   .append("   PART_DAT_COL_ID = ? ");


			ParameterHolder parameters = new ParameterHolder();
			parameters.addParameter(orderId);
			parameters.addParameter(operNo);
			parameters.addParameter(stepKey);
			parameters.addParameter(partDatColId);

			list = eds.queryForList(selectSql.toString(), parameters);
			
			if(list.size() > 0)
			{
				returnMap = (Map) list.get(0);
			}

			return returnMap;	
		}
		
		/**
		 * Inserts part data collection into SFWID_AS_WORKED_BOM.
		 * SAEE_WOE_12
		 * 
		 * Author: David Miron - December 22, 2014
		 * 
		 * @author C079222
		 * @return 
		 *
		 */
		public void insertOrderPartDCValues(String orderId,
											String orderNo,
											String operNo, 
											String stepNo,
											Number operKey,
											Number stepKey,
											String parentOrderId,
											String asWorkedBomId)
		{ 
			String newAsWorkedBomId;

			StringBuffer insertSql = new StringBuffer().append(" INSERT INTO SFWID_AS_WORKED_BOM ")
					                                   .append("   (ORDER_ID, " )
					                                   .append("    ORDER_NO, " )
					                                   .append("    OPER_KEY, " )
					                                   .append("    STEP_KEY, " )
					                                   .append("    AS_WORKED_BOM_ID, " )
					                                   .append("    ASGND_ITEM_ID, " )
					                                   .append("    PART_NO, " )
					                                   .append("    PART_CHG, " )
					                                   .append("    PARENT_ITEM_ID, " )
					                                   .append("    LOT_NO, " )
					                                   .append("    SERIAL_NO, " )
					                                   .append("    PARENT_LOT_NO, " )
					                                   .append("    PARENT_SERIAL_NO, " )
					                                   .append("    UPDT_USERID, " )
					                                   .append("    TIME_STAMP, " )
					                                   .append("    LAST_ACTION, " )
					                                   .append("    OPER_NO, " )
					                                   .append("    REF_DES, " )
					                                   .append("    LOT_ID, " )
					                                   .append("    SERIAL_ID, " )
					                                   .append("    PARENT_SERIAL_ID, " )
					                                   .append("    PARENT_LOT_ID, " )
					                                   .append("    PART_TITLE, " )
					                                   .append("    PART_QTY, " )
					                                   .append("    FIND_NO, " )
					                                   .append("    SPOOL_NO, " )
					                                   .append("    EXP_DATE, " )
					                                   .append("    REMOVE_FLAG, " )
					                                   .append("    PART_ACTION, " )
					                                   .append("    PARENT_PART_NO, " )
					                                   .append("    COMMENTS, " )
					                                   .append("    STEP_NO, " )
					                                   .append("    UOM, " )
					                                   .append("    ALLOC_QTY_FLAG, " )
					                                   .append("    REMOVE_WITHOUT_INSTALL_FLAG, " )
					                                   .append("    OPT_DC1_VALUE, " )
					                                   .append("    OPT_DC2_VALUE, " )
					                                   .append("    OPT_DC3_VALUE, " )
					                                   .append("    OPT_DC4_VALUE, " )
					                                   .append("    OPER_ITERATION, " )
					                                   .append("    OPER_EXE_COUNT, " )
					                                   .append("    PLND_ITEM_ID, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH1, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH2, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH3, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH4, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH5, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH6, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH7, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH8, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH9, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH10, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH11, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH12, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH13, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH14, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH15, " )
					                                   .append("    UCF_ASWRKD_BOM_NUM1, " )
					                                   .append("    UCF_ASWRKD_BOM_NUM2, " )
					                                   .append("    UCF_ASWRKD_BOM_NUM3, " )
					                                   .append("    UCF_ASWRKD_BOM_NUM4, " )
					                                   .append("    UCF_ASWRKD_BOM_NUM5, " )
					                                   .append("    UCF_ASWRKD_BOM_DATE1, " )
					                                   .append("    UCF_ASWRKD_BOM_DATE2, " )
					                                   .append("    UCF_ASWRKD_BOM_DATE3, " )
					                                   .append("    UCF_ASWRKD_BOM_DATE4, " )
					                                   .append("    UCF_ASWRKD_BOM_DATE5, " )
					                                   .append("    UCF_ASWRKD_BOM_FLAG1, " )
					                                   .append("    UCF_ASWRKD_BOM_FLAG2, " )
					                                   .append("    UCF_ASWRKD_BOM_FLAG3, " )
					                                   .append("    UCF_ASWRKD_BOM_FLAG4, " )
					                                   .append("    UCF_ASWRKD_BOM_FLAG5, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH255_1, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH255_2, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH255_3, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH4000_1, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH4000_2, " )
					                                   .append("    REMOVE_TO_LOC, " )
					                                   .append("    REPLACEMENT_ACTION, " )
					                                   .append("    NEW_SERIAL_NO, " )
					                                   .append("    REF_DOC, " )
					                                   .append("    UID_ITEM_FLAG, " )
					                                   .append("    UID_ENTRY_NAME, " )
					                                   .append("    UID_COMPOSITE, " )
					                                   .append("    PART_DAT_COL_ID, " )
					                                   .append("    UID_LABEL, " )
					                                   .append("    ITEM_TYPE, " )
					                                   .append("    ITEM_SUBTYPE, " )
					                                   .append("    SECURITY_GROUP, " )
					                                   .append("    REMOVE_FROM_PART, " )
					                                   .append("    REMOVE_FROM_SERIAL_NO, " )
					                                   .append("    REMOVE_FROM_LOT_NO) " )
					                                   .append(" SELECT ")
					                                   .append("    ? AS ORDER_ID, " )
					                                   .append("    ? AS ORDER_NO, " )
					                                   .append("    ? AS OPER_KEY, " )
					                                   .append("    ? AS STEP_KEY, " )
					                                   .append("    ? AS AS_WORKED_BOM_ID, " )
					                                   .append("    ASGND_ITEM_ID, " )
					                                   .append("    PART_NO, " )
					                                   .append("    PART_CHG, " )
					                                   .append("    PARENT_ITEM_ID, " )
					                                   .append("    LOT_NO, " )
					                                   .append("    SERIAL_NO, " )
					                                   .append("    PARENT_LOT_NO, " )
					                                   .append("    PARENT_SERIAL_NO, " )
					                                   .append("    UPDT_USERID, " )
					                                   .append("    TIME_STAMP, " )
					                                   .append("    LAST_ACTION, " )
					                                   .append("    OPER_NO, " )
					                                   .append("    REF_DES, " )
					                                   .append("    LOT_ID, " )
					                                   .append("    SERIAL_ID, " )
					                                   .append("    PARENT_SERIAL_ID, " )
					                                   .append("    PARENT_LOT_ID, " )
					                                   .append("    PART_TITLE, " )
					                                   .append("    PART_QTY, " )
					                                   .append("    FIND_NO, " )
					                                   .append("    SPOOL_NO, " )
					                                   .append("    EXP_DATE, " )
					                                   .append("    REMOVE_FLAG, " )
					                                   .append("    PART_ACTION, " )
					                                   .append("    PARENT_PART_NO, " )
					                                   .append("    COMMENTS, " )
					                                   .append("    STEP_NO, " )
					                                   .append("    UOM, " )
					                                   .append("    ALLOC_QTY_FLAG, " )
					                                   .append("    REMOVE_WITHOUT_INSTALL_FLAG, " )
					                                   .append("    OPT_DC1_VALUE, " )
					                                   .append("    OPT_DC2_VALUE, " )
					                                   .append("    OPT_DC3_VALUE, " )
					                                   .append("    OPT_DC4_VALUE, " )
					                                   .append("    OPER_ITERATION, " )
					                                   .append("    OPER_EXE_COUNT, " )
					                                   .append("    PLND_ITEM_ID, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH1, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH2, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH3, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH4, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH5, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH6, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH7, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH8, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH9, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH10, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH11, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH12, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH13, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH14, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH15, " )
					                                   .append("    UCF_ASWRKD_BOM_NUM1, " )
					                                   .append("    UCF_ASWRKD_BOM_NUM2, " )
					                                   .append("    UCF_ASWRKD_BOM_NUM3, " )
					                                   .append("    UCF_ASWRKD_BOM_NUM4, " )
					                                   .append("    UCF_ASWRKD_BOM_NUM5, " )
					                                   .append("    UCF_ASWRKD_BOM_DATE1, " )
					                                   .append("    UCF_ASWRKD_BOM_DATE2, " )
					                                   .append("    UCF_ASWRKD_BOM_DATE3, " )
					                                   .append("    UCF_ASWRKD_BOM_DATE4, " )
					                                   .append("    UCF_ASWRKD_BOM_DATE5, " )
					                                   .append("    UCF_ASWRKD_BOM_FLAG1, " )
					                                   .append("    UCF_ASWRKD_BOM_FLAG2, " )
					                                   .append("    UCF_ASWRKD_BOM_FLAG3, " )
					                                   .append("    UCF_ASWRKD_BOM_FLAG4, " )
					                                   .append("    UCF_ASWRKD_BOM_FLAG5, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH255_1, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH255_2, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH255_3, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH4000_1, " )
					                                   .append("    UCF_ASWRKD_BOM_VCH4000_2, " )
					                                   .append("    REMOVE_TO_LOC, " )
					                                   .append("    REPLACEMENT_ACTION, " )
					                                   .append("    NEW_SERIAL_NO, " )
					                                   .append("    REF_DOC, " )
					                                   .append("    UID_ITEM_FLAG, " )
					                                   .append("    UID_ENTRY_NAME, " )
					                                   .append("    UID_COMPOSITE, " )
					                                   .append("    PART_DAT_COL_ID, " )
					                                   .append("    UID_LABEL, " )
					                                   .append("    ITEM_TYPE, " )
					                                   .append("    ITEM_SUBTYPE, " )
					                                   .append("    SECURITY_GROUP, " )
					                                   .append("    REMOVE_FROM_PART, " )
					                                   .append("    REMOVE_FROM_SERIAL_NO, " )
					                                   .append("    REMOVE_FROM_LOT_NO " )
					                                   .append(" FROM ")
					                                   .append("   	SFWID_AS_WORKED_BOM ")
					                                   .append(" WHERE (ORDER_ID, OPER_KEY, STEP_KEY) IN ")
					                                   .append("   	   (SELECT ORDER_ID, OPER_KEY, STEP_KEY ")
					                                   .append("      	  FROM SFWID_OPER_DESC ")
					                                   .append("  	 	 WHERE ORDER_ID = ? ")
					                                   .append(" 	   	   AND OPER_NO  = ? ")
					                                   .append(" 	   	   AND STEP_KEY  = ?) ")
					                                   .append(" 	   	   AND AS_WORKED_BOM_ID  = ? ");

			newAsWorkedBomId = migDaoPW.getNextRefId();

			ParameterHolder parameters = new ParameterHolder();
			parameters.addParameter(orderId);
			parameters.addParameter(orderNo);
			parameters.addParameter(operKey);
			parameters.addParameter(stepKey);
			parameters.addParameter(newAsWorkedBomId);
			parameters.addParameter(parentOrderId);
			parameters.addParameter(operNo);
			parameters.addParameter(stepKey);
			parameters.addParameter(asWorkedBomId);

			eds.insert(insertSql.toString(), parameters);

			return;
		}
		
		/**
		 * Selects part data collection from SFWID_AS_WORKED_BOM.
		 * SAEE_WOE_12
		 * 
		 * Author: David Miron - December 22, 2014
		 * 
		 * @author C079222
		 * @return 
		 *
		 */
		public List selectPartDCOperStepInfo(String orderId, String operNo) 
		{
			List list = null;

			StringBuffer selectSql = new StringBuffer().append(" SELECT ")
					                                   .append("   STEP_NO, ")
					                                   .append("   OPER_KEY, ")
					                                   .append("   STEP_KEY, ")
					                                   .append("   AS_WORKED_BOM_ID, ")  
					                                   .append("   PART_DAT_COL_ID, ")
					                                   .append("   PARENT_LOT_ID AS LOT_ID, ")
					                                   .append("   PARENT_SERIAL_ID AS SERIAL_ID ")
					                                   .append(" FROM ")
					                                   .append("   SFWID_AS_WORKED_BOM ")
					                                   .append(" WHERE ")
					                                   .append("   ORDER_ID = ? ")
					                                   .append(" AND " )
					                                   .append("   OPER_NO = ? ");


			ParameterHolder parameters = new ParameterHolder();
			                                 parameters.addParameter(orderId);
			                                 parameters.addParameter(operNo);

			list = eds.queryForList(selectSql.toString(), parameters);

			return list;	
		}
		
		/**
		 * Returns status of serial operation item.
		 * SAEE_WOE_12
		 * 
		 * Author: David Miron - January 5, 2015
		 * 
		 * @author C079222
		 * @return 
		 *
		 */
		public Map selectSerialOperationItemStatus(String orderId,
													  Number operationKey,
													  Number stepKey,
													  String partDatColId,
													  String lotId,
													  String serialId)
		{

			StringBuilder selectSql = new StringBuilder().append("SELECT ")
					                                     .append("    ITEM_DAT_COL_STATUS, ")
					                                     .append("    LAST_ACTION, ")
					                                     .append("    UPDT_USERID, ")
					                                     .append("    TIME_STAMP ")
					                                     .append("FROM  ")
					                                     .append("    SFWID_SERIAL_OPER_ITEMS ")
					                                     .append("WHERE ")
					                                     .append("    ORDER_ID = ? AND ")
					                                     .append("    OPER_KEY = ? AND ")
					                                     .append("    STEP_KEY = ? AND ")
					                                     .append("    LOT_ID = ? AND ")
					                                     .append("    SERIAL_ID = ? AND ")
					                                     .append("    PART_DAT_COL_ID = ? ");

			ParameterHolder parameters = new ParameterHolder();
			parameters.addParameter(orderId);
			parameters.addParameter(operationKey);
			parameters.addParameter(stepKey);
			parameters.addParameter(lotId);
			parameters.addParameter(serialId);
			parameters.addParameter(partDatColId);

			return eds.queryForMap(selectSql.toString(), parameters);
		}
		
		/**
		 * Updates status of serial operation item.
		 * SAEE_WOE_12
		 * 
		 * Author: David Miron - January 5, 2015
		 * 
		 * @author C079222
		 * @return 
		 *
		 */
		public int updateSerialOperationItems(String orderId,
				                              Number operationKey,
				                              String lotId,
				                              String serialId,
				                              String partDataCollectionId,
				                              String itemDataCollectionStatus,
				                              String updtUser,
				                              Date timeStamp,
				                              String lastAction)
		{
			StringBuilder updateSql = new StringBuilder().append("UPDATE ")
					                                     .append("    SFWID_SERIAL_OPER_ITEMS ")
					                                     .append("SET  ")
					                                     .append("    ITEM_DAT_COL_STATUS = ?, ")
					                                     .append("    UPDT_USERID = ?, ")
					                                     .append("    TIME_STAMP = ?, ")
					                                     .append("    LAST_ACTION = ? ")
					                                     .append("WHERE ")
					                                     .append("    ORDER_ID = ? AND ")
					                                     .append("    OPER_KEY = ? AND ")
					                                     .append("    LOT_ID = ? AND ")
					                                     .append("    SERIAL_ID = ? AND ")
					                                     .append("    PART_DAT_COL_ID = ? ");

			ParameterHolder parameters = new ParameterHolder();
			parameters.addParameter(itemDataCollectionStatus);
			parameters.addParameter(updtUser);
			parameters.addParameter(timeStamp);
			parameters.addParameter(lastAction);
			parameters.addParameter(orderId);
			parameters.addParameter(operationKey);
			parameters.addParameter(lotId);
			parameters.addParameter(serialId);
			parameters.addParameter(partDataCollectionId);
			
			Integer updCount = eds.update(updateSql.toString(), parameters);
			
			return updCount;
		}
		
		//Defect 1469  NSTJ
		public String getPlanPosQty(String orderId,
				Number operationKey,
				Number stepKey,
				String partDatColId) 
		{
			String returnString = null;
			StringBuffer selectSql = new StringBuffer()
			.append(" SELECT ")
			.append("   UCF_PLAN_ITEM_VCH3 ")
			.append(" FROM SFWID_OPER_ITEMS ")
			.append("    WHERE  ORDER_ID =? ")
			.append("	   AND OPER_KEY = ?  ")
			.append("	   AND STEP_KEY = ? ")
			.append("	   AND PART_DAT_COL_ID = ? "); 
			ParameterHolder params = new ParameterHolder();	
			params.addParameter(orderId);
			params.addParameter(operationKey);
			params.addParameter(stepKey);   
			params.addParameter(partDatColId); 

			try
			{
				returnString = eds.queryForString(selectSql.toString(), params);
			}
			catch(Exception e){}//string stays empty

			return returnString;
		}
		
		
		/**
		 * Check if part data collection was completed for a part
		 * SAIE_TR_T123
		 * 
		 * Author: Ashley Fields - February 27, 2015
		 * 
		 * @author XCS4070
		 * @return 
		 *
		 */
		public boolean existsPartDC(String orderId,
	    		Number operKey,
	    		Number stepKey, 
	    		String datColId)
		{
	    
			    	boolean dcExists = false;
			    	
			         StringBuffer selectSql = new StringBuffer().append("select ? ")		        		 									
			        		 									.append("from sfwid_as_worked_bom ")
			        		 									.append("where order_id = ? ")
			        		 									.append("and oper_key = ? ")
			        		 									.append("and step_key = ? ")
			        		 									.append("and part_dat_col_id = ? ");
			         
			         ParameterHolder params = new ParameterHolder();
			         params.addParameter(SoluminaConstants.X);
			         params.addParameter(orderId);
			         params.addParameter(operKey);
			         params.addParameter(stepKey);
			         params.addParameter(datColId);
			        			         
			         List list = eds.queryForList(selectSql.toString(), params);
			         
			         if (!list.isEmpty())
			         {
			        	 dcExists = true;
			         }

			         return dcExists;			    
		}
		/**
		 * Get non-serial part information.
		 * SAEE_WOE_20 Defect 1670
		 * 
		 * Author: David Miron - March 21, 2015
		 * 
		 * @author C079222
		 * @return 
		 *
		 */
		   public Map selectOrderNonSerialParts(String orderId,
				                                Number operationKey,
				                                Number stepKey,
				                                String partDataCollectionId)
		   {
			   StringBuffer selectSql = new StringBuffer().append("SELECT ")
					                                      .append("    PART_ACTION, ")
					                                      .append("    PLND_ITEM_QTY, ")
					                                      .append("    SERIAL_FLAG, ")
					                                      .append("    UCF_PLAN_ITEM_VCH3 ")
					                                      .append(" FROM  ")
					                                      .append("    SFWID_OPER_ITEMS ")                                                  
					                                      .append("  WHERE ")
					                                      .append("     ORDER_ID = ?  ")
					                                      .append(" AND OPER_KEY = ?  ")
					                                      .append(" AND STEP_KEY = ?  ")
					                                      .append(" AND PART_DAT_COL_ID = ? ");


			   ParameterHolder parameters = new ParameterHolder();
			   parameters.addParameter(orderId);
			   parameters.addParameter(operationKey);
			   parameters.addParameter(stepKey);
			   parameters.addParameter(partDataCollectionId);

			   return eds.queryForMap(selectSql.toString(), parameters);
		   }	
		   
			/**
			 * Get part number for assigned INT parts
			 * SAEE_WOE_19 Defect 1724
			 * 
			 * Author: Ashley Fields - April 29, 2015
			 * 
			 * @author XCS4070
			 * @return 
			 *
			 */
		   public Map getSelectedAltPart(String orderId,
                   Number operationKey,
                   Number stepKey,
                   String partDataCollectionId)
		   {
			   String returnString = null;
		    	StringBuffer selectSql = new StringBuffer()
		    	.append(" SELECT ")
		    	.append(" B.PART_NO, ")
		    	.append(" B.ITEM_ID, ")
		    	.append(" B.PART_CHG ")
		    	.append(" FROM SFWID_ORDER_ITEM_ALT_XREF A, ")
		    	.append(" SFPL_ITEM_DESC_MASTER_ALL B ")
		    	.append("    WHERE  ORDER_ID =? ")
		    	.append("	   AND OPER_KEY = ?  ")
		    	.append("	   AND STEP_KEY = ? ")
		    	.append("	   AND PART_DAT_COL_ID = ? ")
		    	.append("      AND A.ASGND_ITEM_ID = B.ITEM_ID "); 
		    	
		    	ParameterHolder params = new ParameterHolder();	
		    	params.addParameter(orderId);
		    	params.addParameter(operationKey);
		    	params.addParameter(stepKey);        
		    	params.addParameter(partDataCollectionId); 

		    	return eds.queryForMap(selectSql.toString(), params);
		   }
		   
		  
	public String selectPartType(String orderId, Number operKey, Number stepKey, String partDatColId)	
	{
		String returnString = null;
		StringBuffer selectSql = new StringBuffer()
		.append(" SELECT ")
		.append("   UCF_PLAN_ITEM_VCH1 ")
		.append(" FROM SFWID_OPER_ITEMS ")
		.append("    WHERE  ORDER_ID =? ")
		.append("	   AND OPER_KEY = ?  ")
		.append("	   AND STEP_KEY = ? ")
		.append("	   AND PART_DAT_COL_ID = ? "); 
		ParameterHolder params = new ParameterHolder();	
		params.addParameter(orderId);
		params.addParameter(operKey);
		params.addParameter(stepKey);   
		params.addParameter(partDatColId); 
		
		returnString = eds.queryForString(selectSql.toString(), params);
        return returnString;
	}
	
	
	public int selectPlannedQty(String orderId, Number operKey, Number stepKey, String partDatColId)	
	{
		int qty = 0;
		
		StringBuffer selectSql = new StringBuffer().append(" Select ")
				.append("  sum(nvl(decode(upper(t.ucf_plan_item_vch3),'A/R','0', substr(t.ucf_plan_item_vch3, instr(t.ucf_plan_item_vch3,'-',1,1)+1)),t.plnd_Item_qty)) ")		//A/R=>0,range=>max,#=>#	
				.append("  from sfwid_oper_items t ")		
				.append("    WHERE  ORDER_ID =? ")
				.append("	   AND OPER_KEY = ?  ")
				.append("	   AND STEP_KEY = ? ")
				.append("	   AND PART_DAT_COL_ID = ? "); 
		
		ParameterHolder params = new ParameterHolder();	
		params.addParameter(orderId);
		params.addParameter(operKey);
		params.addParameter(stepKey);   
		params.addParameter(partDatColId); 
		
    	try
    	{
    		qty = eds.queryForInt(selectSql.toString(), params);
    	}
    	catch(Exception e){
    		qty = 0;
    	}

    	return qty;
		
	}

	public Boolean AllNonSnALtInstalled(String orderId, Number operKey)	
	{
		int plndQty = 0;
		int asgndQty = 0;
		int cntQty = 0;
		Boolean returnValue = true;


		StringBuffer selectSql = new StringBuffer().append(" SELECT o.step_key,  o.part_dat_col_id ")
				.append(" FROM sfwid_oper_items o ")
				.append(" WHERE o.order_id = ? ")
				.append(" and   o.oper_key = ? ")
				.append(" and serial_flag = 'N' ")
				.append(" and ucf_plan_item_vch1 in ('ALT','PKA','RPA') ")
				.append(" and part_action = 'USE' ");

		ParameterHolder parameters = new ParameterHolder();
	    	parameters.addParameter(orderId);
	    	parameters.addParameter(operKey);

	    List nonSNAltList = eds.queryForList(selectSql.toString(), parameters);
	    
		if(nonSNAltList.size() > 0)
		{
   	    	Iterator nonSNAltIter = nonSNAltList.iterator();
			while (nonSNAltIter.hasNext())
			{
				Map nonSNAlt = (Map) nonSNAltIter.next();
				Number partStepKey      = (Number) nonSNAlt.get("STEP_KEY");
				String partDatColId = (String) nonSNAlt.get("PART_DAT_COL_ID");
			 	selectSql = new StringBuffer().append("  SELECT nvl(decode(upper(ucf_plan_item_vch3),'A/R','0', substr(ucf_plan_item_vch3,1,decode(instr(ucf_plan_item_vch3,'-',1,1)-1,-1,length(ucf_plan_item_vch3),instr(ucf_plan_item_vch3,'-',1,1)-1))),plnd_Item_qty) ")		//A/R=>0,range=>min,#=>#	
			 			.append("  from sfwid_oper_items t ")		
			 			.append("    WHERE  ORDER_ID =? ")
			 			.append("	   AND OPER_KEY = ?  ")
			 			.append("	   AND STEP_KEY = ? ")
			 			.append("	   AND PART_DAT_COL_ID = ? "); 

			 	parameters = new ParameterHolder();	
			 	parameters.addParameter(orderId);
			 	parameters.addParameter(operKey);
			 	parameters.addParameter(partStepKey);   
			 	parameters.addParameter(partDatColId); 
			 	
			 	plndQty = eds.queryForInt(selectSql.toString(), parameters);
			 	
				selectSql = new StringBuffer().append(" Select ")
						.append("  sum(nvl(ucf_order_item_alt_num2,'0')) ")			
						.append("  from sfwid_order_item_alt_xref t ")		
						.append("    WHERE  ORDER_ID =? ")
						.append("	   AND OPER_KEY = ?  ")
						.append("	   AND STEP_KEY = ? ")
						.append("	   AND PART_DAT_COL_ID = ? ") 
						.append(" AND ASSIGNed_ITEM_FLAG = 'Y' ");
			 	parameters = new ParameterHolder();	
			 	parameters.addParameter(orderId);
			 	parameters.addParameter(operKey);
			 	parameters.addParameter(partStepKey);   
			 	parameters.addParameter(partDatColId); 
			 	
			 	asgndQty = eds.queryForInt(selectSql.toString(), parameters);
			 	
				selectSql = new StringBuffer().append(" Select  count(*) ")		
						.append("  from sfwid_order_item_alt_xref t ")		
						.append("    WHERE  ORDER_ID =? ")
						.append("	   AND OPER_KEY = ?  ")
						.append("	   AND STEP_KEY = ? ")
						.append("	   AND PART_DAT_COL_ID = ? ")
						.append(" AND ASSIGNed_ITEM_FLAG = 'Y' ");
			 	parameters = new ParameterHolder();	
			 	parameters.addParameter(orderId);
			 	parameters.addParameter(operKey);
			 	parameters.addParameter(partStepKey);   
			 	parameters.addParameter(partDatColId); 
			 	
			 	cntQty = eds.queryForInt(selectSql.toString(), parameters);
			 	
			 	if ((asgndQty < plndQty) || (cntQty == 0)) returnValue = false;
			}
    	}

    	return returnValue;
		
	}
	
	public int selectAssignedQty(String orderId, Number operKey, Number stepKey, String partDatColId, String orderItemAltId)	
	{
		int qty = 0;
		
		StringBuffer selectSql = new StringBuffer().append(" Select ")
		.append("  sum(nvl(ucf_order_item_alt_num2,'0')) ")			
		.append("  from sfwid_order_item_alt_xref t ")		
		.append("    WHERE  ORDER_ID =? ")
		.append("	   AND OPER_KEY = ?  ")
		.append("	   AND STEP_KEY = ? ")
		.append("	   AND ORDER_ITEM_ALT_ID <> ? ") 
		.append("	   AND PART_DAT_COL_ID = ? "); 

		ParameterHolder params = new ParameterHolder();	
		params.addParameter(orderId);
		params.addParameter(operKey);
		params.addParameter(stepKey);   
		params.addParameter(orderItemAltId); 
		params.addParameter(partDatColId); 

		

    	qty = eds.queryForInt(selectSql.toString(), params);

    	return qty;
		
	}
}


