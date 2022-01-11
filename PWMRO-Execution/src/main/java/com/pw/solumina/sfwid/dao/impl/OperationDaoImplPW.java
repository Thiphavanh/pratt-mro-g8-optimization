/*
 *  This unpublished work, first created in 2019 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2019, 2020.  All rights reserved.  Information contained 
 *  herein is proprietary and confidential and improper use or disclosure 
 *  may result in civil and penal sanctions.
 *
 *  File:    DataCollectionDaoPW.java
 * 
 *  Created: 2018-04-16
 * 
 *  Author:  Preston, Robert
 * 
 *  Revision History
 * 
 *  Date        	Who          	    Description
 *  -----------		------------ 	   -----------------------------------------------------------
 *  2018-04-16		Preston, Robert	   Defect 722
 *  2018-06-01      Fred Ettefagh      Defect 722
 *  2019-06-11      Preston, Robert    Defect 790
 *  2019-06-18      Preston, Robert    Defect 805
 *  2020-07-10		Scott Edgerly	   Defect 1698 - List Builder UDV - Doesn't Sort the S/N's in Ascending Order
 *  
 */
package com.pw.solumina.sfwid.dao.impl;

import static com.ibaset.common.FrameworkConstants.IN_QUEUE;
import static com.ibaset.common.FrameworkConstants.X;
import static com.ibaset.common.SoluminaConstants.PENDING;
import static org.apache.commons.lang.StringUtils.replace;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.dao.ExtensionDaoSupport;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.solumina.sfwid.dao.IOperationDao;
import com.ibaset.solumina.sfwid.dao.impl.OperationDaoImpl;
import com.pw.common.dao.CommonDaoPW;

public abstract class OperationDaoImplPW extends ImplementationOf<IOperationDao> implements IOperationDao{
	
	@Reference private static ExtensionDaoSupport eds;
	@Reference private CommonDaoPW commonDaoPW;
	
	 public boolean selectSerialOperationInformationExists(String orderId,
             											   Number operationKey,
                                                           Number stepKey){
		 boolean returnValue = false;
		 boolean isRepairSplitOrder = commonDaoPW.isRepairWorkOrderSplit(orderId);
		 if (isRepairSplitOrder){
			 returnValue = selectSerialOperationInformationExistsPW(orderId, operationKey, stepKey);
		 }else{
			 returnValue = Super.selectSerialOperationInformationExists(orderId, operationKey, stepKey);
		 }
		 return returnValue;
	 }
	
	
    public boolean selectSerialOperationInformationExistsPW(String orderId,
												                Number operationKey,
												                Number stepKey){
				boolean serialOperationExists = false;
				
				StringBuilder selectSql = new StringBuilder().append(" SELECT  ? ")
													         .append(eds.getDualTable())
													         .append(" WHERE EXISTS ( SELECT ? ")
													         .append("                FROM SFWID_SERIAL_OPER ")
													         .append("                WHERE ORDER_ID  = ? ")
													         .append("                AND OPER_KEY  = ?  ")
													         .append("                AND STEP_KEY  = ? ")
													         .append("                AND SERIAL_OPER_STATUS NOT IN ( ?, ?, ? ) ) ");
				
				ParameterHolder parameters = new ParameterHolder();
				parameters.addParameter(X);
				parameters.addParameter(X);
				parameters.addParameter(orderId);
				parameters.addParameter(operationKey);
				parameters.addParameter(stepKey);
				parameters.addParameter(PENDING);
				parameters.addParameter(IN_QUEUE);
				parameters.addParameter("CANCEL");
				List serialOperationList = eds.queryForList(selectSql.toString(),parameters);
				if (serialOperationList.size() > 0){
					serialOperationExists = true;
				}
				return serialOperationExists;
    }
	 
	 
	// Begin Defect 790
    public List selectOrderToOrderOperCopy(String targetOperation,
								            Number incrementBy,
								            String mroFlag,
								            Number executionOrder,
								            String operationNumberList,
								            String fromOrderNumber){
    	
    	
			StringBuffer select = new StringBuffer().append(" SELECT ")
									                 .append(" A.TITLE AS TO_OPER_TITLE, ")
									                 .append(" C.OPER_NO AS FROM_OPER_NO, ")
									                 .append(" DECODE(?, ")
									                 .append("        NULL, C.OPER_NO, ")
									                 .append("        DECODE(?, ")
									                 .append("               NULL, DECODE(C.ORDERING_NUMBER, ")
									                 .append("                            1, ?, ")
									                 .append("                            UPPER(C.OPER_NO)), ")
									                 .append("               SUBSTR(SFMFG.SFFND_NEXTVALUEINSERIES_GET(?, ?, C.ORDERING_NUMBER, C.OPER_NO,?), 1, 40))) AS TO_OPER_NO, ")
									                 .append(" SUBSTR(SFMFG.SFWID_OPER_HAS_PARTS(B.ORDER_ID, A.OPER_KEY), 1, 1) AS HAS_PL, ")
									                 .append(" SUBSTR(SFMFG.SFWID_OPER_HAS_PARTS(B.ORDER_ID, A.OPER_KEY), 1, 1) AS COPY_PL, ")
									                 .append(" SUBSTR(SFMFG.SFWID_OPER_IS_STDOPER(B.ORDER_ID, A.OPER_KEY), 1, 1) AS STD_OPER_FLAG, ")
									                 .append(" (CASE WHEN TO_NUMBER(?) IS NULL THEN A.EXE_ORDER ELSE TO_NUMBER(?) END) AS EXE_ORDER, ")
									                 .append(" B.SECURITY_GROUP, ")
									                 .append(" A.UCF_ORDER_OPER_VCH2, ") //Defect 805
									                 .append(" A.UCF_ORDER_OPER_VCH3 ") //Defect 805
									                 .append(" FROM SFWID_OPER_DESC A, ")
									                 .append("      SFWID_ORDER_DESC B, PWUST_SFWID_ORDER_DESC B1, ")
									                 .append("      (SELECT * FROM (SELECT COLUMN_VALUE AS OPER_NO,ROWNUM AS ORDERING_NUMBER FROM TABLE(SFMFG.SFFND_PARSE_FCN('" + replace(operationNumberList,"'","''") + "', ';')))) C ")
									                 .append(" WHERE B.ORDER_ID = B1.ORDER_ID ") 
									                 .append(" AND B1.PW_ORDER_NO = ? ")
									                 .append(" AND A.ORDER_ID=B.ORDER_ID ")
									                 .append(" AND A.OPER_NO = C.OPER_NO ")
									                 .append(" AND A.STEP_KEY = -1 ")
									                 .append(" ORDER BY C.ORDERING_NUMBER ");
			
			ParameterHolder params = new ParameterHolder();
			params.addParameter(targetOperation);
			params.addParameter(incrementBy);
			params.addParameter(targetOperation);
			params.addParameter(targetOperation);
			params.addParameter(incrementBy);
			params.addParameter(mroFlag);
			params.addParameter(executionOrder);
			params.addParameter(executionOrder);
			params.addParameter(fromOrderNumber);
			
			return eds.queryForList(select.toString(), params);
    }
	// End Defect 790

	
    public List selectOrderIdOperNoOperKey(String orderId,String operationNumber){
    	
			StringBuilder select = new StringBuilder().append(" SELECT ORDER_ID, OPER_NO, STDOPER_OBJECT_ID , OPER_KEY            ")
													  .append(" FROM   SFWID_OPER_DESC     ")
													  .append(" WHERE  ORDER_ID = ?        ")
													  .append(" AND    STEP_KEY = -1       ");
			if (StringUtils.isNotEmpty(operationNumber))
			{
				select.append(" AND OPER_NO = ? ");
			}

			 select.append(" ORDER BY OPER_NO ");
			/*
			 select.append(" ORDER BY  CASE OPER_STATUS WHEN 'ACTIVE' THEN 1 ")
			.append("                            WHEN 'IN QUEUE' THEN 2 ")
			.append("                            WHEN 'PENDING' THEN 3 ")
			.append("                            WHEN 'EXCLUDE' THEN 4 ")
			.append("                            WHEN 'CLOSE' THEN 5 ")
			.append("                            ELSE 6 END,  ")
			.append(getSchemaPrefix() + "SFDB_LPAD_VARCHAR(OPER_NO, ?, ?) ");
            */
			ParameterHolder parameters = new ParameterHolder();
			parameters.addParameter(orderId);
			if (StringUtils.isNotEmpty(operationNumber))
			{
				parameters.addParameter(operationNumber);
			}
			//parameters.addParameter(10);
			//parameters.addParameter("0");

			return eds.queryForList(select.toString(), parameters);
    }
	
    public Map selectOrderInformation(String orderNumber)
    {
    	StringBuilder countSql = new StringBuilder().append("SELECT COUNT(*) ")
    												.append("FROM PWUST_SFWID_ORDER_DESC ")
    												.append("WHERE PW_ORDER_NO = ?");
		ParameterHolder countParameters = new ParameterHolder();
		countParameters.addParameter(orderNumber);
		 
        int rowCount = eds.queryForInt(countSql.toString(), countParameters);
    	
    	
        StringBuilder selectSql;
        
        if (rowCount > 0) {
        	// New 80 char order number.
        	selectSql = new StringBuilder().append("SELECT ")
                                                   .append("   a.ORDER_ID, ")
                                                   .append("   ORDER_STATUS, ")
                                                   .append("   ORDER_QTY, ")
                                                   .append("   ORDER_UOM, ")
                                                   .append("   SERIAL_FLAG, ")
                                                   .append("   LOT_FLAG, ")
                                                   .append("   PROGRAM, ")
                                                   .append("   PLAN_ID, ")
                                                   .append("   PLAN_ALTERATIONS, ")
                                                   .append("   PLAN_UPDT_NO, ")
                                                   .append("   PART_NO, ")
                                                   .append("   PART_CHG, ")
                                                   .append("   ALIAS_PART_NO, ") //FND-21646
                                                   .append("   ALIAS_PART_CHG, ") // FND-21646
                                                   .append("   ITEM_TYPE, ")
                                                   .append("   ITEM_SUBTYPE, ")
                                                   .append("   ALT_STATUS, ")
                                                   .append("   ALT_ID, ")
                                                   .append("   LTA_SEND_FLAG, ")
                                                   .append("   ASGND_LOCATION_ID, ") // FND-22681
                                                   .append("   NEEDS_REVIEW_FLAG, ")
                                                   .append("   ORDER_SCRAP_QTY, ")
                                                   .append("   ORDER_STOP_QTY, ")
                                                   .append("   ORDER_COMPLETE_QTY, ")
                                                   .append("   ORDER_TYPE, ")
                                                   .append("   ITEM_ID ")
                                                   .append("    ,UID_ENTRY_NAME ")
                                                   // UID_UTPAL
                                                   .append("    ,UID_ITEM_FLAG ")
                                                   .append("    ,BOM_NO ")
                                                   .append("    ,MFG_BOM_CHG ")
                                                   .append("    ,DISPLAY_SEQUENCE ")
                                                   .append("    ,OPERATION_OVERLAP_FLAG ") //FND-22142
                                                   .append("FROM ")
                                                   .append("   SFWID_ORDER_DESC a, PWUST_SFWID_ORDER_DESC b ")
                                                   .append("WHERE ")
                                                   .append("    a.ORDER_ID = b.ORDER_ID ")
                                                   .append("AND PW_ORDER_NO = ? ");
        } else {
        	selectSql = new StringBuilder().append("SELECT ")
        											.append("   ORDER_ID, ")
        											.append("   ORDER_STATUS, ")
        											.append("   ORDER_QTY, ")
        											.append("   ORDER_UOM, ")
        											.append("   SERIAL_FLAG, ")
        											.append("   LOT_FLAG, ")
        											.append("   PROGRAM, ")
        											.append("   PLAN_ID, ")
        											.append("   PLAN_ALTERATIONS, ")
        											.append("   PLAN_UPDT_NO, ")
        											.append("   PART_NO, ")
        											.append("   PART_CHG, ")
        											.append("   ALIAS_PART_NO, ") //FND-21646
        											.append("   ALIAS_PART_CHG, ") // FND-21646
        											.append("   ITEM_TYPE, ")
        											.append("   ITEM_SUBTYPE, ")
        											.append("   ALT_STATUS, ")
        											.append("   ALT_ID, ")
        											.append("   LTA_SEND_FLAG, ")
        											.append("   ASGND_LOCATION_ID, ") // FND-22681
        											.append("   NEEDS_REVIEW_FLAG, ")
        											.append("   ORDER_SCRAP_QTY, ")
        											.append("   ORDER_STOP_QTY, ")
        											.append("   ORDER_COMPLETE_QTY, ")
        											.append("   ORDER_TYPE, ")
        											.append("   ITEM_ID ")
        											.append("    ,UID_ENTRY_NAME ")
        											// UID_UTPAL
        											.append("    ,UID_ITEM_FLAG ")
        											.append("    ,BOM_NO ")
        											.append("    ,MFG_BOM_CHG ")
        											.append("    ,DISPLAY_SEQUENCE ")
        											.append("    ,OPERATION_OVERLAP_FLAG ") //FND-22142
        											.append("FROM ")
        											.append("   SFWID_ORDER_DESC ")
        											.append("WHERE ORDER_NO = ?");
        }

        ParameterHolder selectParameters = new ParameterHolder();
        selectParameters.addParameter(orderNumber);

        return eds.queryForMap(selectSql.toString(), selectParameters);
    }
    // End defect 649
    
    //defect 1698
    @Override
    public Map selectCurrentUnits(String orderId, Number operationKey, Number stepKey, String userId,
    		boolean isNotSubselected) {
    	//get current units from Solumina OOB source code
    	Map result = super.Super.selectCurrentUnits(orderId, operationKey, stepKey, userId, isNotSubselected);
    	
    	//Sort Units in ascending order
    	String[] currentUnits = ((String)result.get("CURRENT_UNITS")).split(";");
    	Arrays.sort(currentUnits);
    	result.put("CURRENT_UNITS", String.join(";", currentUnits));
    	
    	return result;
    }
    //end defect 1698
}
