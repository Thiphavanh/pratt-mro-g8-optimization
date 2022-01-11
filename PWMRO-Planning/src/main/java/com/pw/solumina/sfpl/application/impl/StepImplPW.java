/*
 *  This unpublished work, first created in 2017 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2017.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    StepImplPW.java
 * 
 *  Created: 2019-09-23
 * 
 *  Author:  Thien-Long Phan
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2019-09-23		T. Phan		    Initial release. Defect 1103. Changed insertStepDatCol so last_action starts as INSERTED
 *  2019-01-11      B. Corson       Initial Release SMRO_PLG_313 - selectPlanOperDC method extended to include new
 *                                  new field UCF_STEP_DC_FLAG1 (ROM Incl?)
 *  2019-05-01      B. Corson       SMRO_PLG_313 - Include new fields UCF_STEP_DC_VCH255_1 (Engineer Remarks) and 
 *                                  UCF_STEP_DC_FLAG2 (Serviceable Limit Used).  Also extend selectPlanStepDC method 
 *                                  in same manner as selectPlanOperDC.                              
 */
package com.pw.solumina.sfpl.application.impl;

import static com.ibaset.common.DbColumnNameConstants.*;
import static com.ibaset.common.SoluminaConstants.*;
import static org.apache.commons.lang.StringUtils.*;
import static com.ibaset.common.IntegrationConstants.RECEIVE_PROCESS_ROUTING_SYNC;
import static com.ibaset.common.FrameworkConstants.INSERTED;
import static com.ibaset.common.util.SoluminaUtils.stringEquals;
import static org.apache.commons.lang.StringUtils.defaultIfEmpty;
import static org.apache.commons.lang.StringUtils.upperCase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.context.SoluminaContextHolder;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.util.SoluminaUtils;
import com.ibaset.solumina.sffnd.dao.ICommunicationDao;
import com.ibaset.solumina.sffnd.dao.IStandardOperationDao;
import com.ibaset.solumina.sfpl.application.IBom;
import com.ibaset.solumina.sfpl.application.IFile;
import com.ibaset.solumina.sfpl.application.IOperation;
import com.ibaset.solumina.sfpl.application.IOperationDataCollection;
import com.ibaset.solumina.sfpl.application.IOperationHelper;
import com.ibaset.solumina.sfpl.application.IPlan;
import com.ibaset.solumina.sfpl.application.IPlanOfd;
import com.ibaset.solumina.sfpl.application.IStep;
import com.ibaset.solumina.sfpl.application.IStepHelper;
import com.ibaset.solumina.sfpl.application.IStepItems;
import com.ibaset.solumina.sfpl.application.impl.StepImpl;
import com.ibaset.solumina.sfpl.dao.ICDCVariableDao;
import com.ibaset.solumina.sfpl.dao.IOperationDataCollectionDao;
import com.ibaset.solumina.sfpl.dao.IStepDao;
import com.ibaset.solumina.sfwid.application.IDataCollection;
import com.ibaset.solumina.sfwid.application.IOrder;
import com.ibaset.solumina.sfwid.application.IPartDCExecution;
import com.ibaset.solumina.sfwid.dao.IDataCollectionDao;

public abstract class StepImplPW extends ImplementationOf<com.ibaset.solumina.sfpl.application.IStep> implements IStep{
	
	static final int MAX_STEP_NUMBER_LENGTH = 10;
	@Reference
    private ICDCVariableDao cdcVariableDao = null;
    @Reference
    private IOperationDataCollectionDao operationDatColDao = null;
    @Reference
    private IPlanOfd planOfd = null;
    @Reference
    private IStepDao stepDao = null;
    @Reference
    private IStepHelper stepHelper = null;
    @Reference
    private IOperationDataCollection operationDataCollection = null;
    @Reference
    private IOperationHelper operationHelper = null;
    
    @Reference
    private IBom bom = null;
    @Reference
    private IPlan planImpl = null;
    @Reference
    private IOperation oper = null;
    @Reference
    private IOrder order = null;
    @Reference
    private IFile file = null;
    @Reference
    private IDataCollectionDao dataCollectionDao = null;
    @Reference
    private IDataCollection dataCollection = null;
    //GE-9
    @Reference
    private ICommunicationDao communicationDao = null;
    @Reference
    private IStepItems stepItems = null;
    @Reference
    private com.ibaset.solumina.sfwid.application.IOperation sfwidOperation = null ;
    @Reference
    private IPartDCExecution partDCExec;
    
    @Reference
    private IStandardOperationDao standardOperationDao = null;
    
    protected Log logger = LogFactory.getLog(StepImpl.class);
    @Reference
	private com.ibaset.solumina.sfwid.dao.IStepDao widStepDao;
	
    /**
     * Returns the edit mode in terms of Block Id
     * 
     * @param blockId
     * @return String edit mode
     */
    protected String getEditModeForBlockId(String blockId) {
        String editMode = EDITMODES_EDIT_PL_AND_QA;

        if (!RECEIVE_PROCESS_ROUTING_SYNC.equalsIgnoreCase((String) SoluminaContextHolder.getUserContext().get(
                SERVICE_NAME))) {
            if (stringEquals(blockId, PLAN_STEP_TEXT))
                editMode = EDITMODES_EDIT_PL;
            else if (stringEquals(blockId, PLAN_STEP_QA_TEXT))
                editMode = EDITMODES_EDIT_QA;
        }
        return editMode;
    }
    protected void parseAndInsertDataCollectionLimit(String planId,
	            String upperLimit,
	            String lowerLimit,
	            String targetValue,
	            String format,
	            Number[] newStepKey,
	            Number[] newStepUpdtNo,
	            Number newOperKey,
	            String datColId)
	            
	{
	
		Map map = operationDataCollection.formatLowerLimitUpperLimitTargetValueForTypeDateAndTime(lowerLimit, upperLimit, targetValue, format);
		lowerLimit = (String) map.get("LOWER_LIMIT");
		upperLimit = (String) map.get("UPPER_LIMIT");
		targetValue = (String) map.get("TARGET_VALUE");
		
		stepDao.insertStepDataCollectionLimit(planId,
		     newOperKey,
		     newStepKey[0],
		     newStepUpdtNo[0],
		     datColId,
		     "0",
		     ContextUtil.getUsername(),
		     upperLimit,
		     lowerLimit,
		     targetValue);
	}
    //Defect 1103. Change last_action variable
	public void insertStepDatCol(String planId,
				 Number planVersion,
				 Number planRevision,
				 Number planAlterations,
				 String operNo,
				 String stepNo,
				 String refId,
				 String stdDatColId, // FND-24180
				 String datColCert,
				 String lowNavLevel,
				 String datColUom,
				 String datColTitle,
				 String upperLimit,
				 String lowerLimit,
				 String targetValue,
				 String orientationFlag,
				 String crossOrderFlag,
				 String optionalFlag,
				 String blockId,
				 String calculatedDataCollectionFlag,
				 String variableName,
				 String visibility,
				 Number numericDecimalDigits,
				 String ucfStepDcVch1,
				 String ucfStepDcVch2,
				 String ucfStepDcVch3,
				 String ucfStepDcVch4,
				 String ucfStepDcVch5,
				 String ucfStepDcVch6,
				 String ucfStepDcVch7,
				 String ucfStepDcVch8,
				 String ucfStepDcVch9,
				 String ucfStepDcVch10,
				 String ucfStepDcVch11,
				 String ucfStepDcVch12,
				 String ucfStepDcVch13,
				 String ucfStepDcVch14,
				 String ucfStepDcVch15,
				 Number ucfStepDcNum1,
				 Number ucfStepDcNum2,
				 Number ucfStepDcNum3,
				 Number ucfStepDcNum4,
				 Number ucfStepDcNum5,
				 Date ucfStepDcDate1,
				 Date ucfStepDcDate2,
				 Date ucfStepDcDate3,
				 Date ucfStepDcDate4,
				 Date ucfStepDcDate5,
				 String ucfStepDcFlag1,
				 String ucfStepDcFlag2,
				 String ucfStepDcFlag3,
				 String ucfStepDcFlag4,
				 String ucfStepDcFlag5,
				 String ucfStepDcVch2551,
				 String ucfStepDcVch2552,
				 String ucfStepDcVch2553,
				 String ucfStepDcVch40001,
				 String ucfStepDcVch40002,
				 Number displayLineNo,
				 String overInspectionReqdFlag, 
				 String resultId,
				 String fileName,
				 byte[] fileData, 
				 Number dropDispLineNo)
	{
		ContextUtil.clearContextStore();
		ContextUtil.putContextStore(OVER_INSPECTION_REQD_FLAG, defaultIfEmpty(overInspectionReqdFlag,NO));
		
		visibility = operationDataCollection.setDefaultValueForVisibility(variableName, visibility);
		
		operationDataCollection.raiseErrorIfDataCollectionTitleIsBlank(datColTitle);
		
		String format = cdcVariableDao.selectUomFormat(datColUom);
		
		Map dcFields = operationDataCollection.setDCFieldsValuesToDefaultForDCTypeFileAcceptRejectOrTemplate(format,
		                                                                                        lowerLimit,
		                                                                                        upperLimit,
		                                                                                        targetValue,
		                                                                                        variableName,
		                                                                                        visibility,
		                                                                                        numericDecimalDigits,
		                                                                                        calculatedDataCollectionFlag,
		                                                                                        resultId);
		
		lowerLimit = (String) dcFields.get("LOWER_LIMIT");
		upperLimit = (String) dcFields.get("UPPER_LIMIT");
		targetValue = (String) dcFields.get("TARGET_VALUE");
		variableName = (String) dcFields.get("VARIABLE_NAME");
		visibility = (String) dcFields.get("VISIBILITY");
		numericDecimalDigits = (Number) dcFields.get("NO_DEC_DIGIT");
		calculatedDataCollectionFlag = (String) dcFields.get("CALCULATED_FLAG");
		resultId = (String) dcFields.get("RESULT_ID");
		
		
		Map stdDatcolFlags = operationDatColDao.selectStdDatColFlags(stdDatColId); // FND-24180
		
		String dctypeSelectableFlag = (String) stdDatcolFlags.get("SELECTABLE_DC_FLAG");
		String dctypeSpcFlag = (String) stdDatcolFlags.get("SPC_FLAG");
		String dataCollectionType = (String) stdDatcolFlags.get("DAT_COL_TYPE");
		String stdDatColTemplateFileId = (String) stdDatcolFlags.get("TEMPLATE_FILE_ID");
		
		operationDataCollection.preDataCollectionValidations(stdDatColId,
		                                        targetValue,
		                                        datColUom,
		                                        upperLimit,
		                                        lowerLimit,
		                                        numericDecimalDigits,
		                                        calculatedDataCollectionFlag,
		                                        displayLineNo,
		                                        fileName,
		                                        format,
		                                        dctypeSelectableFlag,
		                                        dctypeSpcFlag,
		                                        dataCollectionType,
		                                        stdDatColTemplateFileId);
		
		
		Number noDecDigit = operationDataCollection.getNumberDecimalDigit(datColUom, // FND-24180
		                                                     numericDecimalDigits);
		
		operationDataCollection.validateResultType(calculatedDataCollectionFlag, resultId, format, noDecDigit);
		
		variableName = operationDataCollection.validateAndInsertVariableAndVisibility(variableName,
		                                                                 visibility,
		                                                                 noDecDigit);
		
		String editMode = getEditModeForBlockId(blockId);
		
		planOfd.isPlanAuthoringOk(planId,
		             planVersion,
		             planRevision,
		             planAlterations,
		             editMode);
		
		Number[] operationKeyArray = new Number[1];
		Number[] operationUpdateNoArray = new Number[1];
		operationKeyArray[0] = operationHelper.startOperationUpdate(planId,
		                                               planVersion,
		                                               planRevision,
		                                               planAlterations,
		                                               operNo,
		                                               upperCase(STEP),
		                                               operationUpdateNoArray,
		                                               null);
		
		Number newStepKey[] = new Number[1];
		Number newStepUpdtNo[] = new Number[1];
		Number newOperKey = stepHelper.startStepUpdate(planId,
		                                  planVersion,
		                                  planRevision,
		                                  planAlterations,
		                                  operNo,
		                                  stepNo,
		                                  lowNavLevel,
		                                  newStepKey,
		                                  newStepUpdtNo);
		
		operationDataCollection.raiseErrorIfAutoCompleteFlagIsTrueInPlan(planId,
		                                              planVersion,
		                                              planRevision,
		                                              planAlterations,
		                                              operNo,
		                                              newOperKey);
		String datColId = operationDatColDao.selectDataCollectionId();
		
		displayLineNo = operationDataCollection.getStepDatColDisplayLineNo(planId,
		                                                      refId,
		                                                      displayLineNo,
		                                                      dropDispLineNo,
		                                                      newStepKey,
		                                                      newStepUpdtNo,
		                                                      newOperKey);
		
		
		Number operationUpdateNumber = stepDao.selectOperationUpdateNumber(planId,
		                                                      planVersion,
		                                                      planRevision,
		                                                      planAlterations,
		                                                      newOperKey);
		
		operationDataCollection.raiseErrorIfOverInspectionRequiredFlagAndBatchFlagIsTrue(planId,
		                                                                    overInspectionReqdFlag,
		                                                                    newOperKey,
		                                                                    operationUpdateNumber);
		
		String lastAction = INSERTED;
		lastAction = operationDataCollection.getStepDatColLastAction(planId,
		                                                stepNo,
		                                                refId,
		                                                newStepKey,
		                                                newStepUpdtNo,
		                                                newOperKey,
		                                                lastAction,
		                                                operationUpdateNumber);
		
		String orientFlag = operationDataCollection.setOrientationFlag(orientationFlag);
		
		optionalFlag = defaultIfEmpty(optionalFlag,
		                             NO);
		
		operationDataCollection.raiseErrorIfOrientationFlagIsNotConsistentWithinGridForPlan(planId,
		                                                                       refId,
		                                                                       newStepKey,
		                                                                       newStepUpdtNo,
		                                                                       newOperKey,
		                                                                       datColId,
		                                                                       orientFlag);
		
		String templateFileId = operationDataCollection.validateTemplateFileAndGetTemplateFileId(fileName,
		                                                                            fileData,
		                                                                            format,
		                                                                            stdDatColTemplateFileId);
		
		stepDao.insertStepDataCollection(planId,
		                    newOperKey,
		                    newStepKey[0],
		                    newStepUpdtNo[0],
		                    datColId,
		                    stdDatColId, // FND-24180
		                    datColCert,
		                    ContextUtil.getUsername(),
		                    lastAction,
		                    datColUom,
		                    datColTitle,
		                    refId,
		                    displayLineNo,
		                    orientFlag,
		                    crossOrderFlag,
		                    optionalFlag,
		                    noDecDigit,
		                    calculatedDataCollectionFlag,
		                    variableName,
		                    visibility,
		                    blockId,
		                    ucfStepDcVch1,
		                    ucfStepDcVch2,
		                    ucfStepDcVch3,
		                    ucfStepDcVch4,
		                    ucfStepDcVch5,
		                    ucfStepDcVch6,
		                    ucfStepDcVch7,
		                    ucfStepDcVch8,
		                    ucfStepDcVch9,
		                    ucfStepDcVch10,
		                    ucfStepDcVch11,
		                    ucfStepDcVch12,
		                    ucfStepDcVch13,
		                    ucfStepDcVch14,
		                    ucfStepDcVch15,
		                    ucfStepDcNum1,
		                    ucfStepDcNum2,
		                    ucfStepDcNum3,
		                    ucfStepDcNum4,
		                    ucfStepDcNum5,
		                    ucfStepDcDate1,
		                    ucfStepDcDate2,
		                    ucfStepDcDate3,
		                    ucfStepDcDate4,
		                    ucfStepDcDate5,
		                    ucfStepDcFlag1,
		                    ucfStepDcFlag2,
		                    ucfStepDcFlag3,
		                    ucfStepDcFlag4,
		                    ucfStepDcFlag5,
		                    ucfStepDcVch2551,
		                    ucfStepDcVch2552,
		                    ucfStepDcVch2553,
		                    ucfStepDcVch40001,
		                    ucfStepDcVch40002,
		                    resultId, 
		                    templateFileId);
		parseAndInsertDataCollectionLimit(planId,
		                     upperLimit,
		                     lowerLimit,
		                     targetValue,
		                     format,
		                     newStepKey,
		                     newStepUpdtNo,
		                     newOperKey,
		                     datColId);
	
	}
	// End defect 1103
	
    /*
     * (non-Javadoc)
     * @see com.ibaset.solumina.sfpl.application.IStep#selectPlanOperDC(java.lang.String, java.lang.Number, java.lang.Number, java.lang.Number, java.lang.String, java.lang.Number, java.lang.String, java.lang.String)
     */
	@SuppressWarnings("rawtypes")
    public List  selectPlanOperDC(String planId,
                                  Number planVersion,
                                  Number planRevision,
                                  Number planAlteration,
                                  String operNo,
                                  Number operKey,
                                  String stepNo,
                                  String refId)
    {
        
        List returnList = new ArrayList();
        String userId = ContextUtil.getUsername();
        returnList = stepDao.selectPlanOperDC(planId, 
                                              planVersion, 
                                              planRevision, 
                                              planAlteration, 
                                              operKey, 
                                              stepNo, 
                                              refId, 
                                              userId);
        ListIterator i = returnList.listIterator();
        while(i.hasNext())
        {
            Map mapList = (Map) i.next();
            String upperLimit = (String)mapList.get("UPPER_LIMIT");
            String lowerLimit = (String)mapList.get("LOWER_LIMIT");
            String targetValue = (String)mapList.get("TARGET_VALUE");
            String datColUom = (String)mapList.get("DAT_COL_UOM"); // FND-24180
            
            String format = (String)mapList.get("FORMAT"); // FND-24936
            
            if(isNotEmpty(format))
            {
                if(isNotEmpty(targetValue))
                {
                     targetValue = SoluminaUtils.parseDateGeneralToLocaleSpecific(format, targetValue); // GE-8951
                     mapList.put("TARGET_VALUE", targetValue); 
                }
                if(isNotEmpty(lowerLimit))
                {
                    lowerLimit = SoluminaUtils.parseDateGeneralToLocaleSpecific(format, lowerLimit); // GE-8951 
                    mapList.put("LOWER_LIMIT", lowerLimit); 
                }
                if(isNotEmpty(upperLimit))
                {
                    upperLimit = SoluminaUtils.parseDateGeneralToLocaleSpecific(format, upperLimit); // GE-8951 
                    mapList.put("UPPER_LIMIT", upperLimit);
                }
            }
            
            parseDataCollectionValue(mapList, upperLimit, lowerLimit,targetValue, null, null, format); // GE-3723
            
            mapList.put("CALLED_FROM", EDIT_BLOCK);		//FND-22897
        }
        
        if(returnList.size()==0 || returnList == null)
        {
            Map emptyMap = new ListOrderedMap();
            
            emptyMap.put("DAT_COL_ID", null);
            emptyMap.put("STD_DATCOL_ID", null); // FND-24180
            emptyMap.put("DAT_COL_TYPE", null);
            emptyMap.put("DAT_COL_CERT", null);
            emptyMap.put("TIME_STAMP", null);
            emptyMap.put("STEP_UPDT_NO", null);
            emptyMap.put("LAST_ACTION", null);
            emptyMap.put("DAT_COL_UOM", null);
            emptyMap.put("DAT_COL_TITLE", null);
            emptyMap.put("DAT_COL_LIMIT_ID", null);
            emptyMap.put("UPPER_LIMIT", null);
            emptyMap.put("LOWER_LIMIT", null);
            emptyMap.put("TARGET_VALUE", null);
            emptyMap.put("ORIENTATION_FLAG", null);
            emptyMap.put("CROSS_ORDER_FLAG", null);
            emptyMap.put("DISPLAY_LINE_NO", null);
            emptyMap.put("OPTIONAL_FLAG", null);
            emptyMap.put("VARIABLE_NAME", null);
            emptyMap.put("VISIBILITY", null);
            emptyMap.put("NUM_DECIMAL_DIGITS", null);
            emptyMap.put("CALC_DC_FLAG", null);
            emptyMap.put("CALC_FLAG", null);
            emptyMap.put("DAT_COL_TITLE_DISP", null);
            emptyMap.put("BLOCK_ID", null);
            emptyMap.put("IMAGE", null);
            emptyMap.put("SLIDE_ID", null);
            emptyMap.put("SLIDE_EMBEDDED_REF_ID",null);
            emptyMap.put("RESULT_ID", null); // FND-24285
            emptyMap.put("VALID_RESULT_TYPE", null);
            emptyMap.put("UPDT_USERID", null);
            emptyMap.put("OPER_KEY", null);
            emptyMap.put("STEP_KEY", null);
            emptyMap.put("FILE_DISP", null);   //FND-24936
            emptyMap.put("FILE1", null);      //FND-24936
            emptyMap.put(SECURITY_GROUP, null);   //FND-24936
            emptyMap.put("FORMAT", null);   //FND-24936
            emptyMap.put("CALLED_FROM", null);		//FND-22897
            emptyMap.put("UCF_STEP_DC_FLAG1", null);
            emptyMap.put("UCF_STEP_DC_VCH255_1", null);
            emptyMap.put("UCF_STEP_DC_FLAG2", null);
            returnList.add(emptyMap);
            
        }
        
        String datColId = (String)((Map)returnList.get(0)).get("DAT_COL_ID");
        
        if(isEmpty(datColId))
        {
            List returnList1 =new ArrayList(); 
            Map emptyMap = new ListOrderedMap();
            
            emptyMap.put("DAT_COL_ID", null);
            emptyMap.put("STD_DATCOL_ID", null); // FND-24180
            emptyMap.put("DAT_COL_TYPE", null);
            emptyMap.put("DAT_COL_CERT", null);
            emptyMap.put("TIME_STAMP", null);
            emptyMap.put("STEP_UPDT_NO", null);
            emptyMap.put("LAST_ACTION", null);
            emptyMap.put("DAT_COL_UOM", null);
            emptyMap.put("DAT_COL_TITLE", null);
            emptyMap.put("DAT_COL_LIMIT_ID", null);
            emptyMap.put("UPPER_LIMIT", null);
            emptyMap.put("LOWER_LIMIT", null);
            emptyMap.put("TARGET_VALUE", null);
            emptyMap.put("ORIENTATION_FLAG", null);
            emptyMap.put("CROSS_ORDER_FLAG", null);
            emptyMap.put("DISPLAY_LINE_NO", null);
            emptyMap.put("OPTIONAL_FLAG", null);
            emptyMap.put("VARIABLE_NAME", null);
            emptyMap.put("VISIBILITY", null);
            emptyMap.put("NUM_DECIMAL_DIGITS", null);
            emptyMap.put("CALC_DC_FLAG", null);
            emptyMap.put("CALC_FLAG", null);
            emptyMap.put("DAT_COL_TITLE_DISP", null);
            emptyMap.put("BLOCK_ID", null);
            emptyMap.put("IMAGE", null);
            emptyMap.put("SLIDE_ID", null);
            emptyMap.put("SLIDE_EMBEDDED_REF_ID",null);
            emptyMap.put("RESULT_ID", null); // FND-24285
            emptyMap.put("VALID_RESULT_TYPE", null);
            emptyMap.put("UPDT_USERID", null);
            emptyMap.put("OPER_KEY", null);
            emptyMap.put("STEP_KEY", null);
            emptyMap.put("AUDIT_FLAG", null);
            emptyMap.put("FILE_DISP", null);   //FND-24936
            emptyMap.put("FILE1", null);      //FND-24936
            emptyMap.put(SECURITY_GROUP, null);   //FND-24936
            emptyMap.put("FORMAT", null);   //FND-24936
            emptyMap.put("CALLED_FROM", null);		//FND-22897
            emptyMap.put("UCF_STEP_DC_FLAG1", null);
            emptyMap.put("UCF_STEP_DC_VCH255_1", null);
            emptyMap.put("UCF_STEP_DC_FLAG2", null);
            returnList1.add(emptyMap);
            
            returnList= returnList1;
        }
        
        return returnList;
    }

    /*
     * (non-Javadoc)
     * @see com.ibaset.solumina.sfpl.application.IStep#selectPlanStepDC(java.lang.String, java.lang.Number, java.lang.Number, java.lang.Number, java.lang.Number, java.lang.Number, java.lang.String)
     */
    public List  selectPlanStepDC(String planId,
                                  Number planVersion,
                                  Number planRevision,
                                  Number planAlteration,
                                  Number operKey,
                                  Number stepKey,
                                  String refId)
    {
        List returnList = new ArrayList();
        String userId = ContextUtil.getUsername();
        returnList = stepDao.selectPlanStepDC(planId, 
                                              planVersion, 
                                              planRevision, 
                                              planAlteration, 
                                              operKey, 
                                              stepKey, 
                                              refId, 
                                              userId);
        ListIterator i = returnList.listIterator();
        while(i.hasNext())
        {
            Map mapList = (Map) i.next();
            
            mapList.put("CALLED_FROM", EDIT_BLOCK);		//FND-22897
        }
        
        if(returnList.size()==0 || returnList == null)
        {
            Map emptyMap = new ListOrderedMap();
            
            emptyMap.put("DAT_COL_ID", null);
            emptyMap.put("DAT_COL_TYPE", null);
            emptyMap.put("STD_DATCOL_ID", null); // FND-24180
            emptyMap.put("DAT_COL_CERT", null);
            emptyMap.put("TIME_STAMP", null);
            emptyMap.put("STEP_UPDT_NO", null);
            emptyMap.put("LAST_ACTION", null);
            emptyMap.put("DAT_COL_UOM", null);
            emptyMap.put("DAT_COL_TITLE", null);
            emptyMap.put("DAT_COL_LIMIT_ID", null);
            emptyMap.put("UPPER_LIMIT", null);
            emptyMap.put("LOWER_LIMIT", null);
            emptyMap.put("TARGET_VALUE", null);
            emptyMap.put("ORIENTATION_FLAG", null);
            emptyMap.put("CROSS_ORDER_FLAG", null);
            emptyMap.put("DISPLAY_LINE_NO", null);
            emptyMap.put("OPTIONAL_FLAG", null);
            emptyMap.put("VARIABLE_NAME", null);
            emptyMap.put("VISIBILITY", null);
            emptyMap.put("NUM_DECIMAL_DIGITS", null);
            emptyMap.put("CALC_DC_FLAG", null);
            emptyMap.put("CALC_FLAG", null);
            emptyMap.put("DAT_COL_TITLE_DISP", null);
            emptyMap.put("BLOCK_ID", null);
            emptyMap.put("IMAGE", null);
            emptyMap.put("SLIDE_ID", null);
            emptyMap.put("SLIDE_EMBEDDED_REF_ID",null);
            emptyMap.put("UPDT_USERID", null);
            emptyMap.put("OPER_KEY", null);
            emptyMap.put("STEP_KEY", null);
            emptyMap.put("STD_DATCOL_ID", null); // FND-24180
            emptyMap.put("VALID_RESULT_TYPE", null); // FND-24285
            emptyMap.put("RESULT_ID", null); // FND-24285
            emptyMap.put("FILE_DISP", null);      //FND-24936
            emptyMap.put("FILE1", null);      //FND-24936
            emptyMap.put(SECURITY_GROUP, null);   //FND-24936
            emptyMap.put("FORMAT", null);   //FND-24936
            emptyMap.put("CALLED_FROM", null);		//FND-22897
            emptyMap.put("UCF_STEP_DC_FLAG1", null);
            emptyMap.put("UCF_STEP_DC_VCH255_1", null);
            emptyMap.put("UCF_STEP_DC_FLAG2", null);
            returnList.add(emptyMap);
            
        }
        
        String datColId = (String)((Map)returnList.get(0)).get("DAT_COL_ID");
        
        if(isEmpty(datColId))
        {
            List returnList1 =new ArrayList(); 
            Map emptyMap = new ListOrderedMap();
            
            emptyMap.put("DAT_COL_ID", null);
            emptyMap.put("DAT_COL_TYPE", null);
            emptyMap.put("STD_DATCOL_ID", null); // FND-24180
            emptyMap.put("DAT_COL_CERT", null);
            emptyMap.put("TIME_STAMP", null);
            emptyMap.put("STEP_UPDT_NO", null);
            emptyMap.put("LAST_ACTION", null);
            emptyMap.put("DAT_COL_UOM", null);
            emptyMap.put("DAT_COL_TITLE", null);
            emptyMap.put("DAT_COL_LIMIT_ID", null);
            emptyMap.put("UPPER_LIMIT", null);
            emptyMap.put("LOWER_LIMIT", null);
            emptyMap.put("TARGET_VALUE", null);
            emptyMap.put("ORIENTATION_FLAG", null);
            emptyMap.put("CROSS_ORDER_FLAG", null);
            emptyMap.put("DISPLAY_LINE_NO", null);
            emptyMap.put("OPTIONAL_FLAG", null);
            emptyMap.put("VARIABLE_NAME", null);
            emptyMap.put("VISIBILITY", null);
            emptyMap.put("NUM_DECIMAL_DIGITS", null);
            emptyMap.put("CALC_DC_FLAG", null);
            emptyMap.put("CALC_FLAG", null);
            emptyMap.put("DAT_COL_TITLE_DISP", null);
            emptyMap.put("BLOCK_ID", null);
            emptyMap.put("IMAGE", null);
            emptyMap.put("SLIDE_ID", null);
            emptyMap.put("SLIDE_EMBEDDED_REF_ID",null);
            emptyMap.put("UPDT_USERID", null);
            emptyMap.put("OPER_KEY", null);
            emptyMap.put("STEP_KEY", null);
            emptyMap.put("AUDIT_FLAG", null);
            emptyMap.put("VALID_RESULT_TYPE", null); // FND-24285
            emptyMap.put("RESULT_ID", null); // FND-24285
            emptyMap.put("FILE_DISP", null);      //FND-24936
            emptyMap.put("FILE1", null);      //FND-24936
            emptyMap.put(SECURITY_GROUP, null);   //FND-24936
            emptyMap.put("FORMAT", null);   //FND-24936    		
            emptyMap.put("CALLED_FROM", null);		//FND-22897
            emptyMap.put("UCF_STEP_DC_FLAG1", null);
            emptyMap.put("UCF_STEP_DC_VCH255_1", null);
            emptyMap.put("UCF_STEP_DC_FLAG2", null);
            returnList1.add(emptyMap);
            
            returnList= returnList1;
        }
        return returnList;
    }
}
