/*
 *  This unpublished work, first created in 2017 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2017-2021.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    SoluminaMigrationPW.java
 *  
 *  SMRO_CV_202
 * 
 *  Created: 2017.08.09
 * 
 *  Author:  R Palmer
 * 
 *  Revision History
 * 
 *  Date       	 	Who          	Description
 *  -----------		------------ 	---------------------------------------------------
 *  2018-03-27		R. Cannon		SMRO_CV_202 - Go_Live Release (NZ)
 *  2018-08-28		R. Cannon		SMRO_CV_202 - OKC Additions (StdTxt, StdOp, RichTxt and Graphics added)
 *  2018-10-10		R. Cannon		SMRO_CV_202 - Standardized plan object insertion and error logging
 *  2018-10-19		R. Cannon		SMRO_CV_202 - OKC Specific - Insert operation header to Step 10
 *  2018-12-19		P. Petracca		SMRO_CV_202 - Add repair functionality; dynamic stdOp secGrps; rich text auto-size workaround  
 *  2019-02-13		R. Cannon		SMRO_CV_202 - Update buyoff mapping; consolidate text processing
 *  2019-04-01		R. Cannon		Defect 1198 - Put operation header content onto step 10 for any OKC plan
 *  2019-04-04      D. Sibrian		Defect 1254	- ESA enhancements (insert plan header authority; no blue NOTE text)
 *  2019-05-22		R. Cannon		Defect 1269 - ESA enhancements (copy plan header text)
 *  2019-09-27		R. Vega			SMRO_CV_202 - Update RTF encoding logic;
 *  2019-11-06		S. Edgerly		Defect 1396 - Text in converted plans not shown in text editor, now using correct method call
 *	2019-11-18		S. Edgerly		Defect 1422 - Resolved issue where UOM and Serial Flag we not being set correctly for Repair plans.
 *  2020-01-29		S. Edgerly		Defect 1346 - Buyoff title retrieved from PWUE_GENERAL_LOOKUP instead of hard-coded hash map.
 *  2020-02-13		S. Edgerly		Defect 1411 - Supports Repair Plans need to be run using serial numbers.
 *  2020-02-13		S. Edgerly		Defect 1412 - Supports Repair Plans need to be run using lot numbers.
 *  2020-02-25      S. Edgerly      SMRO_CV_202 - Defect 1304; Tool functionality added 
 *  2020-03-04	    Fred Ettefagh	SMRO_PLG_305- Defect 1410 for overhaul plans only auto-populate task groups parts with the Plan's item_no.
 *  2020-03-09		S. Edgerly		SMRO_CV_301 - Defect 1383; Engine Manuals and Subject Authorities functionality added
 *  2020-03-18		S. Edgerly		SMRO_CV_202 - Defect 1303; Component Part functionality added
 *  2020-03-19 		S.Edgerly		SMRO_CV_301 - Defect 1383; Engine Manuals and Subject Authorities functionality added
 *  2020-04-08		S. Edgerly		SMRO_CV_302 - Defect 1623 - Parts on Repair Task Groups.
 *  2020-04-23		S.Edgerly		SMRO_CV_302 - Defect 1636 - Optional flags for Data Collections supported
 *  2020-04-24		S.Edgerly		SMRO_CV_202 - Defect 1185 - Security groups on plan item number creation
 *  2020-05-07		S.Edgerly		Solumina OOB - Defect 1096 - Conversion - Standard Operations not Updating
 *  2020-05-14		S.Edgerly		Defect 1659 - EXPT_ACCEPTANCE Error Message, UOM not matching when SELECTABLE flag is set
 *  2020-05-21		S.Edgerly		Defect 1659 - Improved query to find data collections with whitespace after "/"
 *  2020-05-26		S.Edgerly		SMRO_CV_302 - Defect 1666 Conversion Import into G8- Updating EXTERNAL_PLM_NO with "<PART_NO>N/A"
 * 2020-07-22		S.Edgerly		SMRO_CV_302 - Defect 1741 - Engine type fix
 * 2021-01-26		S. Edgerly	 	Defect 1803; ESA Enhancements
 * 2021-02-25		S. Edgerly		Defect 1850, Item #9 - Subject Status support.
 * 2021-03-09		S. Edgerly		SMRO_CV_301 - Defect 1875 - TEC Conversion Extract from G5
 * 2021-03-18		S. Edgerly		SMRO_CV_302 - Defect 1884 - Conversion Import into G8
 * 2021-05-04		S. Edgerly		SMRO_CV_301 - Defect 1872, NBRO Conversion Enhancements
 * 2021-07-08		S. Edgerly		SMRO_CV_302 - Defect 1928, Improved Error Logging
 * 2021-07-29		S. Edgerly		SMRO_CV_302 - Defect 1944 Conversion support for General Procedure plan types
 * 2021-07-30		S. Edgerly		PW_SMRO_eWORK_201 - Defect 1912, Improved Error Logging
 * 2021-08-06		S. Edgerly		SMRO_CV_201 - Defect 1958 - Supports conversion of Labor Hours Per Unit
 * 2021-08-19		S. Edgerly		PW_SMRO_eWORK_201 - Defect 1912 Graphic Hotfix 
 * 2021-08-18		S. Edgerly		PW_SMRO_eWORK_301 - Defect 1956 - TOS Stamp Warranty, Removed extra double quote from beginning of block text.
 * 2021-09-10		S. Edgerly		PW_SMRO_eWORK_201 - Defect 1991 TOS image issues
 * 2021-09-20		S. Edgerly		PW_SMRO_eWORK_201 - Defect 1991, update 1 - Improved nested query to prevent multiple records from being returned. 
 * 2021-10-25		S. Edgerly		PW_SMRO_eWORK_201 - Defect 2000, PWCS Image, buyoff, and DC failure investigation 
 * 2021-10-26		S. Edgerly		SMRO_CV_302 - Defect 1928a, Improved Error Logging for Step Text reporting and null engine types
 * 2021-10-27		S. Edgerly		Defect 1972, Adding security groups to images converted in library and completing the image.
 * 2021-10-28		S. Edgerly		PW_SMRO_eWORK_201 - Defect 2000, PWCS Image, buyoff, and DC failure investigation 
 * 2021-11-08		S. Edgerly		Defect 1928b - Improved Error Logging, Added support for task groups, plan name and separated technical details in ERROR_TEXT into new STACK_TRACE column.
*/

package com.pw.solumina.migration.application;

import java.awt.Color;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBElement;
import org.apache.commons.codec.binary.Hex;

import com.ibaset.common.OutputParameter;
import com.ibaset.common.Reference;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.solumina.sfcore.application.IMultiMediaObject;
import com.ibaset.solumina.sfor.application.ISfplPlan;
import com.ibaset.solumina.sfpl.application.IPlan;
import com.ibaset.solumina.sfpl.application.IItem;
import com.ibaset.solumina.sfpl.application.IOperation;
import com.ibaset.solumina.sfpl.application.IOperationDataCollection;
import com.ibaset.solumina.sfpl.application.ISlide;
import com.ibaset.solumina.sfpl.application.IStep;
import com.ibaset.solumina.sfpl.application.IWorkPackage;
import com.ibaset.solumina.sfpl.dao.IOperationDao;
import com.ibaset.solumina.sfpl.dao.IStepDao;
import com.ibaset.solumina.sfpl.dao.IWorkPackageDao;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sffnd.application.IHtReferenceText;
import com.ibaset.solumina.sffnd.application.IStandardOperation;
import com.ibaset.solumina.sffnd.application.IStandardText;
import com.ibaset.solumina.sffnd.application.IWrapUtils;
import com.ibaset.solumina.sfwid.application.IAlteration;
import com.pw.solumina.authority.application.impl.AuthorityImpl;
import com.pw.solumina.sffnd.application.IStandardOperationPW;
import com.pw.solumina.sffnd.application.IStandardTextPW;
import com.pw.solumina.sfor.application.SfplPlanPW;
import com.pw.solumina.sfpl.application.PlanModelPW;
import com.utc.pw.solumina.StepInterpretter;
import com.utc.pw.solumina.model.BuyOff;
import com.utc.pw.solumina.model.BuyOffBlock;
import com.utc.pw.solumina.model.Content;
import com.utc.pw.solumina.model.DataCollection;
import com.utc.pw.solumina.model.DataCollectionBlock;
import com.utc.pw.solumina.model.Graphic;
import com.utc.pw.solumina.model.Link;
import com.utc.pw.solumina.model.ObjectFactory;
import com.utc.pw.solumina.model.OptionSet;
import com.utc.pw.solumina.model.Part;
import com.utc.pw.solumina.model.PartBlock;
import com.utc.pw.solumina.model.RichText;
import com.utc.pw.solumina.model.StdText;
import com.utc.pw.solumina.model.Tool;
import com.utc.pw.solumina.model.ToolBlock;


import org.apache.commons.lang.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;

import com.ibaset.common.solumina.exception.SoluminaException;

public class SoluminaMigrationPW {

	/* *************************************************************************************
	 *  2018-07-17: DEBUG created for testing purposes. Current functionality when active:
	 *  1) Allow duplicate plans to migrate. 2) Prevent bypass flag setting to "Y"
	 * *************************************************************************************/
	private final static boolean DEBUG = false;

	//Class Objects
	@Reference private IAlteration IAlteration;
	@Reference private IEvent IEvent;
	@Reference private IHtReferenceText IHtReferenceText;
	@Reference private IItem IItem;
	@Reference private IMultiMediaObject IMultiMediaObject;
	@Reference private IOperation IOperation;
	@Reference private IOperationDao IOperationDao;
	@Reference private IOperationDataCollection IOperationDataCollection;
	@Reference private IPlan IPlan;
	@Reference private ISfplPlan ISfplPlan;
	@Reference private ISlide ISlide;
	@Reference private IStandardText IStandardText;
	@Reference private IStandardTextPW IStandardTextPW;
	@Reference private IStandardOperation IStandardOperation;
	@Reference private IStandardOperationPW IStandardOperationPW;
	@Reference private IStep IStep;
	@Reference private IStepDao IStepDao;
	@Reference private IWorkPackage workPackageRef;
	@Reference private IWorkPackageDao workPackageDaoRef;
	@Reference private IWrapUtils IWrapUtils;
	@Reference private PlanModelPW PlanModelPW;
	@Reference private SfplPlanPW SfplPlanPW;
	@Reference private SoluminaMigrationDaoPW SoluminaMigrationDaoPW;
	@Reference private SiteSpecificOkcPW SiteSpecificOKC;
	@Reference private AuthorityImpl AuthorityImpl;


	/* **************************************************************
	 * NOTE: execMig called iteratively by Solumina's input matrix.
	 * 		 Multi-plan batches call execMig once per plan.
	 * **************************************************************/

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void execMig(String src_plan_id, String changeDocType, String changeDocId) {
		Map planMap = SoluminaMigrationDaoPW.getPlan(src_plan_id);
		PlanObjectParams plan = new PlanObjectParams(src_plan_id, planMap);

		this.addPwp(plan);
		this.insertPartIntoPartMaster(plan);
		this.addPlan(plan, changeDocType, changeDocId);
		this.postConversionActions();
	} // end execMig()


	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  PLANNING WORK PACKAGES
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	private void addPwp(PlanObjectParams plan) {

		String plg_work_package = plan.getPWP();
		String pwp_desc = "Auto-created during G8 plan migrations";
		String pwp_notes = "Add notes here";

		boolean workPackagExists = workPackageDaoRef.selectIsWorkPackageExists(plg_work_package);

		if (!workPackagExists) {

			try {
				workPackageRef.insertWorkPackage(plg_work_package, 		//:PWP_ID
												 pwp_desc,		   		//:PWP_DESC
												 pwp_notes,		   		//:PWP_NOTE
												 null,			   		//:BOM_NO
												 null,			   		//:MFG_BOM_CHG
												 null,					//:EXCLUDE_ITEM_LIST_PAR
												 SoluminaConstants.LOW,	//:PRIORITY
												 null,			   		//:START_DATE
												 null,			   		//:END_DATE
												 null,			   		//:DISPOSITION
												 null,			   		//:RESP_USER_ID
												 null,			   		//:UNIT_TYPE
												 null,			   		//:EFF_FROM
												 null,			   		//:EFF_THRU
												 null,			   		//:EFF_FROM_DATE
												 null);			   		//:EFF_THRU_DATE
			} catch (Exception e) {

				String errorText = "Error on pwp create: part_no=" + plan.getPartNo();
				SoluminaMigrationDaoPW.insertErrorLogG8("PWP_AUTO_INS", MigrationVariablesPW.FAIL, plan.getSrcPlanId(),
						MigrationVariablesPW.PLAN_UPDT_NO, MigrationVariablesPW.NA_ERR, MigrationVariablesPW.NA_ERR,
						MigrationVariablesPW.NA_ERR, 0, errorText, MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), null, plan.getPartNo());
			}
		} // end if(!workPackagExists)
	} // end addPwp();


	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  PART AUTO-INSERT
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	private void insertPartIntoPartMaster(PlanObjectParams plan) {

		String part_no = plan.getPartNo();

		boolean partExists = SoluminaMigrationDaoPW.partInItemMaster(part_no);

		if (!partExists) {
			//defect 1411 & 1412
			//First set auto default values for Overhaul plans
			String partUOM = SoluminaConstants.EA;
			String workOrderSerialFlag = SoluminaConstants.N;
			String workOrderLotFlag = SoluminaConstants.N;
			
			if(plan.getPlanType().equals("REPAIR")){
				if(plan.isSerialFlagSet()){ //Defect 1411
					partUOM = SoluminaConstants.INTEGER;
					workOrderSerialFlag = SoluminaConstants.YES;
				}
				else if(plan.isLotFlagSet()){ //Defect 1412
					partUOM = SoluminaConstants.INTEGER;
					workOrderLotFlag = SoluminaConstants.YES;
				}
			}
			try {
				IItem.insertItemDesc(part_no,	 						//String itemNumber,
									 MigrationVariablesPW.NA_OOB,		//String itemRev, (aka PART_CHG)
									 SoluminaConstants.PART,     		//String itemType,
									 plan.getPlanType(),  				//String itemSubtype,
									 null, 		 						//String program,
									 plan.getLocation(), 		 		//String workLocation, Defect 1185
									 plan.getTitle(), 					//String itemTitle,
									 partUOM,							//String stockUom,
									 workOrderSerialFlag, 		 		//String workOrderSerialFlag,
									 workOrderLotFlag, 		 			//String workOrderLotFlag,
									 SoluminaConstants.N, 		 		//String componentSerialFlag,
									 SoluminaConstants.N, 		 		//String componentLotFlag,
									 SoluminaConstants.N, 		 		//String expirationFlag,
									 SoluminaConstants.N, 		 		//String spoolFlag,
									 null, 		 						//String parentEngineeringPartNumber,
									 null, 		 						//String parentEngineeringPartChange,
									 null, 		 						//String ucfItemVch1,
									 null, 		 						//String ucfItemVch2,
									 null, 		 						//String ucfItemVch3,
									 null, 		 						//String ucfItemVch4,
									 null, 		 						//String ucfItemVch5,
									 null, 		 						//String ucfItemVch6,
									 null, 		 						//String ucfItemVch7,
									 null, 		 						//String ucfItemVch8,
									 null, 		 						//String ucfItemVch9,
									 null, 		 						//String ucfItemVch10,
									 null, 		 						//String ucfItemVch11,
									 null, 		 						//String ucfItemVch12,
									 null, 		 						//String ucfItemVch13,
									 null, 		 						//String ucfItemVch14,
									 null, 		 						//String ucfItemVch15,
									 null, 		 						//Number ucfItemNumber1,
									 null, 		 						//Number ucfItemNumber2,
									 null, 		 						//Number ucfItemNumber3,
									 null, 		 						//Number ucfItemNumber4,
									 null, 		 						//Number ucfItemNumber5,
									 null, 		 						//String ucfItemFlag1,
									 null, 		 						//String ucfItemFlag2,
									 null, 		 						//String ucfItemFlag3,
									 null, 		 						//String ucfItemFlag4,
									 null, 		 						//String ucfItemFlag5,
									 null, 		 						//Date ucfItemDate1,
									 null, 		 						//Date ucfItemDate2,
									 null, 		 						//Date ucfItemDate3,
									 null, 		 						//Date ucfItemDate4,
									 null, 		 						//Date ucfItemDate5,
									 SoluminaConstants.TWO,  			//String calledFrom,
									 SoluminaConstants.N, 		 		//String optionalDataCollection1Flag,
									 SoluminaConstants.N, 		 		//String optionalDataCollection2Flag,
									 SoluminaConstants.N, 		 		//String optionalDataCollection3Flag,
									 SoluminaConstants.N, 		 		//String optionalDataCollection4Flag,
									 SoluminaConstants.N, 		 		//String standardPartFlag,
									 SoluminaConstants.N, 		 		//String flightSafetyFlag,
									 SoluminaConstants.N, 		 		//String flightEssentialFlag,
									 null,		 						//String cotsiPAFlag,
									 null, 		 						//String sourceInspPlanId,
									 null, 		 						//String destInspPlanId,
									 null, 		 						//String manufacturingInspPlanId,
									 SoluminaConstants.ANY_PROPERCASE,	//String utilizationRule,
									 SoluminaConstants.N, 		 		//String isTrackable,
									 null, 		 						//String uidEntryName,
									 null, 		 						//String uidAcquisitionCode,
									 null, 		 						//String uidItemFlag,
									 null,		 						//String toolCalibrateDays,
									 null,		 						//String toolCalibrateHours,
									 null,		 						//String toolCalibrateUses,
									 null,		 						//Integer toolCalibrationDaysFreq,
									 null,		 						//Integer toolCalibrationHoursFreq,
									 null,		 						//Integer toolCalibrationUsesFreq,
									 null,		 						//Number flightDays,
									 null,		 						//Number flightHours,
									 null,		 						//Number flightCycles,
									 null,		 						//Number stdCost,
									 null,		 						//String phantomKit,
									 null,		 						//String serialNoGenerator,
									 null, null, null, null, null, null, null, null, null, null, null, null,
									 null, null, null, null, null, null, null, null, null, null, null, null,
									 null, null, null, null, null, null, null, null, null, null, null, null, 
									 null, null, null, null, null, null, null, null, null, null, null, null,
									 null, 		 						//String changeType,
									 SoluminaConstants.M); 		 		//String changeNo (stop udv popup once part is created)
				
				//defect 1666
				SoluminaMigrationDaoPW.updateExternal_PLM_NO(part_no);
				
				IEvent.closeAllTools(); /*TODO: This line intended to prevent migrated plans from opening in
												Solumina pending successful completion. In G8, this command
												doesn't seem to have effect. Consult with iBASEt how to prevent
												mig code from opening newly migrated plans. Search this code
												for "closeAllTools" to find other 2 references.*/
			} catch (Exception e) {

				String errorText = "Part auto-create failed for " + part_no;
				
				SoluminaMigrationDaoPW.insertErrorLogG8("PART_AUTO_INS", MigrationVariablesPW.FAIL, plan.getSrcPlanId(),
						MigrationVariablesPW.PLAN_UPDT_NO, MigrationVariablesPW.NA_ERR, MigrationVariablesPW.NA_ERR,
						MigrationVariablesPW.NA_ERR, 0, errorText, MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), null, plan.getPartNo());
			}
		} // end if(!partExists)

	} // end insertPartIntoPartMaster()


	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  PLANS
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@Deprecated
	String convertStackTraceToG8ErrText(Exception e, String errorText){


		if(e!=null){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stackTrace = sw.toString();
			//This should prevent duplicate exception messages and print the stack trace.
			//efect 1872, prevents null pointer exception
			if(e.getMessage()!=null){
				if(errorText.contains(e.getMessage())){
					errorText = errorText.replace(e.getMessage(), stackTrace);
				}
				//otherwise, just append the stack trace
				else{
					errorText = errorText.concat("\n" + stackTrace);
				}
			}
			//Truncate error message at 3000 characters.
			if(errorText.length() > 3000){
				errorText = errorText.substring(0, 2999);
			}
		}
		return errorText;
	}
	
	String convertStackTraceToG8ErrText(Exception e){
		String stackTrace = "N/A";
		if(e!=null){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			stackTrace = sw.toString();
		
			//Truncate error message at 4000 characters.
			if(stackTrace.length() > 4000){
				stackTrace = stackTrace.substring(0, 2999);
			}
		}
		return stackTrace;
	}

	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addPlan(PlanObjectParams plan, String changeDocType, String changeDocId) {

		/* **********************************************************************************
		 * NOTE: Parameter changeDocId is no longer used due to addPwp() functionality
		 * 		 added above. Replaced within this method as "plg_work_package".
		 * **********************************************************************************/

		Map newPlanMap = null;

		String src_plan_id = plan.getSrcPlanId();
		String part_no = plan.getPartNo();
		String workFlowAuth = "REPAIR".equals(plan.getPlanType()) ?
							  MigrationVariablesPW.RPR_WORKFLOW : MigrationVariablesPW.OH_WORKFLOW;
		
		boolean planSuccess = true;
		boolean duplicatePlan = SoluminaMigrationDaoPW.planAlreadyExist(part_no);

		//Prevent adding duplicate plans to SFPL_PLAN_DESC (unless exempt for testing)
		if(!duplicatePlan || DEBUG) {
			try {
				// Defect 1422 ************************************
				String workOrderSerialNumberFlag = null;
				@SuppressWarnings("unused")
				String workOrderLotFlag = null;
				String orderUOM = null;
				
				// Defect 1422 ************************************
				if(plan.getPlanType().equals("REPAIR")){
					if(plan.isSerialFlagSet()){ //Defect 1411
						orderUOM = SoluminaConstants.INTEGER;
						workOrderSerialNumberFlag = SoluminaConstants.YES;
						//workOrderLotFlag = SoluminaConstants.NO;
					}
					else if(plan.isLotFlagSet()){ //Defect 1412
						orderUOM = SoluminaConstants.INTEGER;
						//workOrderSerialNumberFlag = "N";
						workOrderLotFlag = SoluminaConstants.YES;
					}
				}
				else{
					List partNoList =SoluminaMigrationDaoPW.selectSfplItemDescMaster(part_no, "ANY","ANY");
					if (partNoList!=null && !partNoList.isEmpty()){
						Map map = (Map)partNoList.get(0);
						workOrderSerialNumberFlag = (String)map.get("WO_SERIAL_FLAG");
						orderUOM =(String) map.get("STOCK_UOM");
					}else{
						String message = "Work Order Serial Number Flag and UOM could not be determined for this part.";
						throw new SoluminaException(message);
					}
				}
				// end Defect 1422 ******************************************
				
				// Defect 1741
				//Query SFFND_PROGRAM_DEF for Engine Type, if not exists then set to null 
				//defect 1928a, Do not log an error if the engine type was null in G5
				if(!StringUtils.isEmpty(plan.getEngineType()) && SoluminaMigrationDaoPW.queryEngineType(plan.getEngineType()).isEmpty()){
					//log warning
					String warningText = "Engine Type " + plan.getEngineType() + " not found in SFFND_PROGRAM_DEF.  Setting to NULL";
					SoluminaMigrationDaoPW.insertErrorLogG8("PLAN_CREATE", MigrationVariablesPW.WARN, src_plan_id,
							MigrationVariablesPW.PLAN_UPDT_NO, MigrationVariablesPW.NA_ERR, MigrationVariablesPW.NA_ERR,
							MigrationVariablesPW.NA_ERR, 0, warningText, MigrationVariablesPW.SFMFG, null, null, plan.getPartNo());
					
					plan.setEngineType(null);
				}
				//End Defect 1741
				
				//defect 1944
				String planType;
				if(!StringUtils.isEmpty(plan.getPlanAlternativeType())){
					planType = plan.getPlanAlternativeType();
					workFlowAuth = MigrationVariablesPW.GP_WORKFLOW;
				}
				else{
					planType = plan.getPlanType();
				}
				//end defect 1944
				newPlanMap = IPlan.insertPlanAndReturnKeyInfo(part_no, 					 			//String partNumber,
															  MigrationVariablesPW.NA_OOB,			//String partChange,
														   	  planType, 						 	//String planType,
														   	  SoluminaConstants.PART, 				//String itemType,
														   	  null, 								//String itemSubtype,
														   	  plan.getEngineType(), 				//String program,
														   	  null, 								//String project,
														   	  plan.getLocation(), 			 		//String plannedWorkLocation,
														   	  MigrationVariablesPW.NEW_PLAN_VER, 	//Number planVersion,
														   	  MigrationVariablesPW.NEW_PLAN_REV, 	//Number planRevision,
														   	  MigrationVariablesPW.NEW_PLAN_ALT,	//Number planAlterations,
														   	  plan.getTitle(), 				  		//String planTitle,
														   	  SoluminaConstants.ONE_NUMBER,			//Number plannedOrderQuantity,
														   	  null, 								//String plannedCustomerId,
														   	  null, 								//String model,
														   	  null, 								//String engineeringPartNumber,
														   	  null, 								//String engineeringPartChange,
														   	  null, 								//String engineeringGroup,
														   	  null, 								//String taskId,
														   	  MigrationVariablesPW.LOWEST_NAV_LVL,	//String lowestNavigationLevel,
														   	  changeDocType, 						//String changeDocumentType,
														   	  plan.getPWP(),			 			//String changeDocId,
														   	  null, 								//String sourcePartNumber,
														   	  null, 								//String sourcePartChange,
														   	  null, 								//String sourcePlanNumber,
														   	  null, 								//String sourceItemType,
														   	  null, 								//String sourceItemSubtype,
														   	  null, 								//String sourceProgram,
														   	  null, 								//Number sourcePlanVersion,
														   	  null, 								//Number sourcePlanRevision,
														   	  null, 								//Number sourcePlanAlterations,
														   	  SoluminaConstants.Y, 					//String copyComponentPartsFlag,
														   	  SoluminaConstants.UDV_CREATE, 		//String calledFrom,
														   	  null, 								//String ucfPlanVch1,
														   	  null, 								//String ucfPlanVch2,
														   	  plan.getModuleCode(), 				//String ucfPlanVch3,
														   	  null, 								//String ucfPlanVch4,
														   	  null, 								//String ucfPlanVch5,
														   	  null, 								//String ucfPlanVch6,
														   	  null, 								//String ucfPlanVch7,
														   	  null, 								//String ucfPlanVch8,
														   	  null, 								//String ucfPlanVch9,
														   	  null, 								//String ucfPlanVch10,
														   	  null, 								//String ucfPlanVch11,
														   	  null, 								//String ucfPlanVch12,
														   	  null, 								//String ucfPlanVch13,
														   	  null, 								//String ucfPlanVch14,
														   	  null, 								//String ucfPlanVch15,
														   	  null, 								//Number ucfPlanNum1,
														   	  null, 								//Number ucfPlanNum2,
														   	  null, 								//Number ucfPlanNum3,
														   	  null, 								//Number ucfPlanNum4,
														   	  null, 								//Number ucfPlanNum5,
														   	  null, 								//String ucfPlanFlag1,
														   	  null, 								//String ucfPlanFlag2,
														   	  null, 								//String ucfPlanFlag3,
														   	  null, 								//String ucfPlanFlag4,
														   	  null, 								//String ucfPlanFlag5,
														   	  null, 								//Date ucfPlanDate1,
														   	  null, 								//Date ucfPlanDate2,
														   	  null, 								//Date ucfPlanDate3,
														   	  null, 								//Date ucfPlanDate4,
														   	  null, 								//Date ucfPlanDate5,
														   	  null, 								//String ucfPlanVch2551,
														   	  plan.getAuthority(), 					//String ucfPlanVch2552, --Defect 1254
														   	  null, 								//String ucfPlanVch2553,
														   	  null, 								//String ucfPlanVch40001,
														   	  null, 								//String ucfPlanVch40002,
														   	  null, 								//String fromPlanType,
														   	  //SoluminaConstants.AUTO_PARALLEL,	//String displaySequence, --(original value)
														   	  SoluminaConstants.EXECUTION_ORDER,	//String displaySequence, --(value needed to surface "Display Operation Sequence" button)
														   	  SoluminaConstants.PROCESS_PLAN,		//String documentType,
														   	  null, 								//String mfgBomRev,
														   	  null, 								//String bomNumber,  
														   	  workFlowAuth,					    	//String workFlow,
														   	  null, 								//String finalStores,
														   	  null, 								//String inspectionPlanId,
														   	  null, 								//String bomId,
														   	  null, 								//String changeRequestId,
														   	  null, 								//String plannedActionId, 
														   	  null, 								//String explicitBomLink,
														   	  SoluminaConstants.Y, 					//String ppvRequiredFlag,
														   	  SoluminaConstants.FULL, 				//String ppvType,
														   	  SoluminaConstants.ONE_NUMBER, 		//Number ppvQuantity,
														   	  SoluminaConstants.N, 					//String associateChange,
														   	  orderUOM, 							//String orderUOM,
														   	  null, 								//String calledFromExternalSystem,
														   	  null, 								//String workOrderLotNumberFlag,
														   	  workOrderSerialNumberFlag);								//String workOrderSerialNumberFlag
				IEvent.closeAllTools();

			} catch (Exception e) {

				planSuccess = false;
				String errorText = "Error on plan create: part_no=" + part_no;

				SoluminaMigrationDaoPW.insertErrorLogG8("PLAN_CREATE", MigrationVariablesPW.FAIL, src_plan_id,
						MigrationVariablesPW.PLAN_UPDT_NO, MigrationVariablesPW.NA_ERR, MigrationVariablesPW.NA_ERR,
						MigrationVariablesPW.NA_ERR, 0, errorText, MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), null, plan.getPartNo());
			}

			if(planSuccess) {

				plan.setNewPlanId(newPlanMap);
				String new_plan_id = plan.getNewPlanId();

				this.addPlanHeaderText(plan); //Defect 1269
				this.addEngineModels(plan);
				this.addPlanSubjects(plan); 
				this.addSubjCustomersIncl(plan);
				this.addOperations(plan);
				this.addSubjOperations(plan);
				this.addSubjAuthorities(plan);//defect 1383
				//update obsolete flags after processing all subject related data, otherwise relavant data may not be displayed.
				this.updatePlanSubjectFlags(plan); //defect 1884
				//Release migrated plan so unclaimed by anyone in Solumina
				String taskId = SoluminaMigrationDaoPW.getTaskId(new_plan_id);
				IAlteration.updateTask(taskId, "RELEASE", null, null);

				//Update VIS_PLAN to link new plan id to the original source plan
				SoluminaMigrationDaoPW.logNewPlanId(src_plan_id, new_plan_id);

				//Auto-mark migrated plans complete
				if(!DEBUG) {
					SoluminaMigrationDaoPW.setBypassFlag(src_plan_id, "Y", "MIG_SUCCESS");
				}
			} //end if (planSuccess)
		} //end if(!duplicatePlan)
		else { //plan is duplicate

			String errorText = "Part_no=" + part_no + " already exists in SFPL_PLAN_DESC.\n" +
							   "Source planid=" + src_plan_id + " not migrated.";
			SoluminaMigrationDaoPW.setBypassFlag(src_plan_id, "Y", "DUPLICATE_PLAN");
			
			SoluminaMigrationDaoPW.insertErrorLogG8("DUPLICATE_PLAN", MigrationVariablesPW.FAIL, src_plan_id,
					MigrationVariablesPW.PLAN_UPDT_NO, MigrationVariablesPW.NA_ERR, MigrationVariablesPW.NA_ERR,
					MigrationVariablesPW.NA_ERR, 0, errorText, MigrationVariablesPW.SFMFG, null, null, plan.getPartNo());
		}

		IEvent.closeAllTools();

	} // end addPlans()

	//Defect 1835 - Needed in order to display images to an external viewer
	private void postConversionActions(){
		//call procedure thumbnail_fixer
		try{
			SoluminaMigrationDaoPW.call_thumbnail_fixer();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  PLAN HEADER TEXT
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@SuppressWarnings("rawtypes")
	private void addPlanHeaderText(PlanObjectParams plan) { //Defect 1269

		String src_plan_id = plan.getSrcPlanId();

		Map hdrTxtInfo = SoluminaMigrationDaoPW.getPlanHeaderText(src_plan_id);
		String planHdrTxt = (String) hdrTxtInfo.get("PLAN_HDR_TEXT");
		Number textLength = (Number) hdrTxtInfo.get("TEXT_LENGTH");

		EContentLocation location = EContentLocation.PLAN;

		if (planHdrTxt != null) {

			try {
				String rich_text = renderRichText("text", planHdrTxt, "", plan);
				this.insertTextXML(plan, planHdrTxt, rich_text, location);
				
				if (textLength.intValue() >= 4000) {
	
					String warningText = "Plan header text may have been truncated during conversion "
							+ "(max 4000 chars). Please review";
					
					SoluminaMigrationDaoPW.insertErrorLogG8("PLAN_HDR_TXT", MigrationVariablesPW.WARN, src_plan_id,
							MigrationVariablesPW.PLAN_UPDT_NO, MigrationVariablesPW.NA_ERR, MigrationVariablesPW.NA_ERR,
							MigrationVariablesPW.NA_ERR, 0, warningText, MigrationVariablesPW.SFMFG, null, null, plan.getPartNo());
				}
	
			} catch (Exception e) {
	
				String errorText = "Fail on plan header text insert: part_no=" + plan.getPartNo();
				
				SoluminaMigrationDaoPW.insertErrorLogG8("PLAN_HDR_TXT", MigrationVariablesPW.FAIL, src_plan_id,
						MigrationVariablesPW.PLAN_UPDT_NO, MigrationVariablesPW.NA_ERR, MigrationVariablesPW.NA_ERR,
						MigrationVariablesPW.NA_ERR, 0, errorText, MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), null, plan.getPartNo());
			}
		} // end if (planHdrTxt != null)
	} // end addPlanHeaderText()

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  ENGINE MODELS
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@SuppressWarnings("rawtypes")
	private void addEngineModels(PlanObjectParams plan) {

		String src_plan_id = plan.getSrcPlanId();

		List modelList = SoluminaMigrationDaoPW.queryPlanEngineModels(src_plan_id);
		ListIterator modelIter = modelList.listIterator();

		while (modelIter.hasNext()) {

			Map modelInfo = (Map) modelIter.next();
			String model = (String) modelInfo.get("MODEL");

			try {
				PlanModelPW.insertPlanModel(plan.getNewPlanId(),				//:PLAN_ID,
											MigrationVariablesPW.PLAN_UPDT_NO,	//:PLAN_UPDT_NO,
											model);		 						//:ENGINE_MODEL
			} catch (Exception e) {

				String errorText = "Fail on engine model insert: part_no=" + plan.getPartNo() +
								   ", model=" + model;
				
				SoluminaMigrationDaoPW.insertErrorLogG8("ENGINE_MODEL", MigrationVariablesPW.FAIL, src_plan_id,
						MigrationVariablesPW.PLAN_UPDT_NO, MigrationVariablesPW.NA_ERR, MigrationVariablesPW.NA_ERR,
						MigrationVariablesPW.NA_ERR, 0, errorText, MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), null, plan.getPartNo());
			}
		} // end while
	} // end addEngineModels()


	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  SUBJECTS
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@SuppressWarnings("rawtypes")
	private void addPlanSubjects(PlanObjectParams plan) {

		String src_plan_id = plan.getSrcPlanId();

		List subjList = SoluminaMigrationDaoPW.querySubjects(src_plan_id);
		ListIterator subjIter = subjList.listIterator();

		while (subjIter.hasNext()) {

			Map subjInfo = (Map) subjIter.next();
			String subject_id = (String) subjInfo.get("SUBJECT_ID");
			Number subject_no = (Number) subjInfo.get("SUBJECT_NO");
			String subject_title = (String) subjInfo.get("SUBJECT_TITLE");
			String auto_include = (String) subjInfo.get("AUTO_INCLUDE");
			String authority = (String) subjInfo.get("AUTHORITY");
			String mandatory = (String) subjInfo.get("MANDATORY");

			try {
				//Defect 1623
				List<String> subjectParts = SoluminaMigrationDaoPW.querySubjectParts(src_plan_id, subject_no);
				SfplPlanPW.migrateSubjectTaskGroup(plan.getNewPlanId(),					//:PLAN_ID,
												  plan.getPartNo(),
												  MigrationVariablesPW.NEW_PLAN_VER,	//:PLAN_VERSION,
												  MigrationVariablesPW.NEW_PLAN_REV,	//:PLAN_REVISION,
												  MigrationVariablesPW.NEW_PLAN_ALT,	//:PLAN_ALTERATIONS,
												  MigrationVariablesPW.PLAN_UPDT_NO,
												  subject_no,							//:NEW_SUBJECT_NO,
												  subject_title,						//:TITLE,
												  auto_include,							//:IS_DEFAULT,
												  authority,							//:AUTHORITY,
												  mandatory,                            //:MANDATORY
												  plan.getPlanType(),	  // defect 1410
												  plan.getPartNo(),
												  subjectParts,
												  SoluminaConstants.N/*defect 1875, 1884*/);

				this.addSubjWorkscopes(plan, subject_id, subject_no);
				this.addSuperiorNetworks(plan, subject_id, subject_no);
				
			} catch (Exception e) {
				
				String errorText = "Error on subject insert: PART_NO=" + plan.getPartNo() + ", SUBJECT_NO=" + subject_no
								   + ", SUBJECT_ID=" + subject_id;
				
				SoluminaMigrationDaoPW.insertErrorLogG8("SUBJECTS", MigrationVariablesPW.FAIL, src_plan_id,
						MigrationVariablesPW.PLAN_UPDT_NO, MigrationVariablesPW.NA_ERR, MigrationVariablesPW.NA_ERR,
						MigrationVariablesPW.NA_ERR, 0, errorText, MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), subject_no, plan.getPartNo());
			}
		} // end while
	} // end addPlanSubjects()

	//defect 1884
	private void updatePlanSubjectFlags(PlanObjectParams plan){

		String src_plan_id = plan.getSrcPlanId();

		List subjList = SoluminaMigrationDaoPW.querySubjects(src_plan_id);
		ListIterator subjIter = subjList.listIterator();

		while (subjIter.hasNext()) {
			Map subjInfo = (Map) subjIter.next();
			String subject_id = (String) subjInfo.get("SUBJECT_ID");
			Number subject_no = (Number) subjInfo.get("SUBJECT_NO");
			String status = (String) subjInfo.get("SUBJECT_STATUS");
			if(status == null){
				status = "";
			}
			String obsolete_record_flag = (String) subjInfo.get("OBSOLETE_RECORD_FLAG");//Defect 1872
			//defect 1872
			switch (plan.getPWP()) {
			case "TEC":
				//defect 1875, Item #3
				if("HOLD".equals(status)){
					obsolete_record_flag = SoluminaConstants.Y;
				}
				break;
				
			case "NBRO":
				//Carry in HOLD status
				break;
				
			default://other sites have not requested any changes to subject status/obsolete flag
				if(!status.isEmpty()){
					status = "";
				}
				break;
			}
			//end defect 1872
			
			try {
				
				//defect 1875, Item #3
				//only execute update statement for records we know have changed. (By default, obsolete record is "N" and status is null/empty.)
				//defect 1872
				if(SoluminaConstants.Y.equals(obsolete_record_flag) || !status.isEmpty()){
					updateSubjectAttributes(plan, subject_id, subject_no, obsolete_record_flag, status);
				}
				//end defect 1875
			} catch (Exception e) {
				
				String errorText = "Error on subject update: part_no=" + plan.getPartNo()+ ", subj_no=" + subject_no
								   + ", subj_id=" + subject_id;
				SoluminaMigrationDaoPW.insertErrorLogG8("SUBJECT UPDATE", MigrationVariablesPW.FAIL, src_plan_id,
						MigrationVariablesPW.PLAN_UPDT_NO, MigrationVariablesPW.NA_ERR, MigrationVariablesPW.NA_ERR,
						MigrationVariablesPW.NA_ERR, 0, errorText, MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), null, plan.getPartNo());
			}
		} // end while
	} //end defect 1884
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  SUBJECT WORKSCOPES
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@SuppressWarnings("rawtypes")
	private void addSubjWorkscopes(PlanObjectParams plan, String subject_id, Number subject_no) {

		String src_plan_id = plan.getSrcPlanId();

		List subjWrkScopeList = SoluminaMigrationDaoPW.querySubWorkscope(src_plan_id, subject_id);
		ListIterator subjWrkScopeIter = subjWrkScopeList.listIterator();

		while (subjWrkScopeIter.hasNext()) {

			Map subjInfo = (Map) subjWrkScopeIter.next();
			String workscope = (String) subjInfo.get("WORKSCOPE");

			try {
				SfplPlanPW.insertSubjectWorkscope(plan.getNewPlanId(),					//:PLAN_ID,
												  MigrationVariablesPW.PLAN_UPDT_NO,	//:PLAN_UPDT_NO,
												  subject_no,							//:SUBJECT_NO,
												  MigrationVariablesPW.NEW_SUBJ_REV,	//:SUBJECT_REV,
												  workscope);							//:WORKSCOPE
			} catch (Exception e) {

				String errorText = "Error on subject workscope insert: part_no=" + plan.getPartNo()+ ", subj_no=" + subject_no + ", subj_id="
								   + subject_id + ", workscope=" + workscope;
				
				SoluminaMigrationDaoPW.insertErrorLogG8("SUBJ_WORKSCOPES", MigrationVariablesPW.FAIL, src_plan_id,
						MigrationVariablesPW.PLAN_UPDT_NO, MigrationVariablesPW.NA_ERR, MigrationVariablesPW.NA_ERR,
						MigrationVariablesPW.NA_ERR, 0, errorText, MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), subject_no, plan.getPartNo());
			}
		} // end while
	} // end addSubjWorkscopes()


	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  SUPERIOR NETWORKS
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@SuppressWarnings("rawtypes")
	private void addSuperiorNetworks(PlanObjectParams plan, String subject_id, Number subject_no) {

		String src_plan_id = plan.getSrcPlanId();

		List supNetList = SoluminaMigrationDaoPW.querySuperiorNetwork(src_plan_id, subject_id);
		ListIterator supNetIter = supNetList.listIterator();

		while (supNetIter.hasNext()) {

			Map supNetInfo = (Map) supNetIter.next();
			String activity_number = (String) supNetInfo.get("ACTIVITY_NUMBER");

			try {
				SfplPlanPW.insertSubjectSuperiorNetwork(plan.getNewPlanId(),				//:PLAN_ID,
														MigrationVariablesPW.PLAN_UPDT_NO,	//:PLAN_UPDT_NO,
														subject_no,							//:SUBJECT_NO,
														MigrationVariablesPW.NEW_SUBJ_REV,	//:SUBJECT_REV,
														plan.getEngineType(),				//:PROGRAM
														activity_number);					//:SUPERIOR_NETWORK_ACT

				this.addSubNetworks(plan, subject_id, subject_no, activity_number);	

			} catch (Exception e) {

				String errorText = "Error on superior network insert: part_no=" + plan.getPartNo() + ", subj_no=" + subject_no + ", subj_id="
								   + subject_id + ", supNet=" + activity_number;
				
				SoluminaMigrationDaoPW.insertErrorLogG8("SUP_NET", MigrationVariablesPW.FAIL, src_plan_id,
						MigrationVariablesPW.PLAN_UPDT_NO, MigrationVariablesPW.NA_ERR, MigrationVariablesPW.NA_ERR,
						MigrationVariablesPW.NA_ERR, 0, errorText, MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), subject_no, plan.getPartNo());
			}
		} // end while
	} // end addSuperiorNetworks()

	//defect 1872
	private void updateSubjectAttributes(PlanObjectParams plan, String subject_id, Number subject_no, String obsoleteRecordFlag, String status){
		if(obsoleteRecordFlag == null){
			obsoleteRecordFlag = "";
		}
		if(status == null){
			status = "";
		}
		if(obsoleteRecordFlag.isEmpty()){
			//log a warning of unknown status so that the business is aware and can take appropriate action(s)			
			String warningText = "Warning: Empty value for OBSOLETE_RECORD_FLAG. Value should be 'Y' or 'N'.  part_no=" + plan.getPartNo() + ", subj_id="
					   + subject_id;
			
			SoluminaMigrationDaoPW.insertErrorLogG8("SUBJECT_OBSOLETE_RECORD_FLAG", MigrationVariablesPW.WARN, plan.getSrcPlanId(),
					MigrationVariablesPW.PLAN_UPDT_NO, MigrationVariablesPW.NA_ERR, MigrationVariablesPW.NA_ERR,
					MigrationVariablesPW.NA_ERR, 0, warningText, MigrationVariablesPW.SFMFG, "SoluminaMigrationPW.updateSubjectStatusAndAutoIncludeFlag()", subject_no, plan.getPartNo());
		}
		try {
			SoluminaMigrationDaoPW.updateSubjectAttributes(plan.getNewPlanId(),
															 MigrationVariablesPW.PLAN_UPDT_NO,
															 subject_no,
															 obsoleteRecordFlag,
															 status);

		} catch (Exception e) {

			String errorText = "Error on update subject obsolete record flag: part_no=" + plan.getPartNo() + ", subj_no="
					   + subject_no + ", subj_id=" + subject_id;
			
			SoluminaMigrationDaoPW.insertErrorLogG8("SUBJECT_OBSOLETE_FLAG", MigrationVariablesPW.FAIL, plan.getSrcPlanId(),
					MigrationVariablesPW.PLAN_UPDT_NO, MigrationVariablesPW.NA_ERR, MigrationVariablesPW.NA_ERR,
					MigrationVariablesPW.NA_ERR, 0, errorText, MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), subject_no, plan.getPartNo());
		}	
	}
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  SUB NETWORKS
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addSubNetworks(PlanObjectParams plan, String subject_id, Number subject_no, String sup_activity_number) {

		String src_plan_id = plan.getSrcPlanId();

		List subNetList = SoluminaMigrationDaoPW.querySubNetwork(src_plan_id, subject_id, sup_activity_number);

		//2018-03-17 Added to handle missing subnetworks
		if(subNetList.isEmpty())
		{
			Map fakeSubNet = new HashMap();
			fakeSubNet.put("SUB_ACTIVITY_NUMBER", "0000");
			subNetList.add(fakeSubNet);
		}

		ListIterator subNetIter = subNetList.listIterator();

		while (subNetIter.hasNext()) {

			Map subNetInfo = (Map) subNetIter.next();
			String sub_activity_number = (String) subNetInfo.get("SUB_ACTIVITY_NUMBER");

			try {
				SfplPlanPW.insertSubjectSubNetwork(plan.getNewPlanId(),					//:PLAN_ID,
												   MigrationVariablesPW.PLAN_UPDT_NO,	//:PLAN_UPDT_NO,
												   subject_no,							//:SUBJECT_NO,
												   MigrationVariablesPW.NEW_SUBJ_REV,	//:SUBJECT_REV,
												   plan.getEngineType(),				//:PROGRAM
												   sup_activity_number,					//:SUPERIOR_NETWORK_ACT,
												   sub_activity_number);				//:SUB_NETWORK_ACT
			} catch (Exception e) {

				String errorText = "Error on sub network insert: part_no=" + plan.getPartNo() + ", subj_no=" + subject_no + ", subj_id=" +
								   subject_id + ", supNet=" + sup_activity_number + ", subNet=" + sub_activity_number;
				SoluminaMigrationDaoPW.insertErrorLogG8("SUB_NET", MigrationVariablesPW.FAIL, src_plan_id,
						MigrationVariablesPW.PLAN_UPDT_NO, MigrationVariablesPW.NA_ERR, MigrationVariablesPW.NA_ERR,
						MigrationVariablesPW.NA_ERR, 0, errorText, MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), subject_no, plan.getPartNo());
			}
		} // end while
	} // end addSubNetworks()


	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  SUBJECT CUSTOMERS INCL
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@SuppressWarnings("rawtypes")
	private void addSubjCustomersIncl(PlanObjectParams plan) {

		String src_plan_id = plan.getSrcPlanId();

		List includeList = SoluminaMigrationDaoPW.querySubjectCustIncl(src_plan_id);
		ListIterator includeIter = includeList.listIterator();

		while (includeIter.hasNext()) {

			Map includeInfo = (Map) includeIter.next();
			String subject_id = (String) includeInfo.get("SUBJECT_ID");
			String customer = (String) includeInfo.get("CUSTOMER");
			Number subject_no = (Number) includeInfo.get("SUBJECT_NO");

			try {
				SfplPlanPW.insertSubjectCustomer(plan.getNewPlanId(),				//:PLAN_ID,
												 MigrationVariablesPW.PLAN_UPDT_NO,	//:PLAN_UPDT_NO,
												 subject_no,						//:SUBJECT_NO,
												 MigrationVariablesPW.NEW_SUBJ_REV,	//:SUBJECT_REV,
												 customer);							//:WORKSCOPE

				//For each included customer add an exclusion if found
				this.addSubjCustomersExcl(plan, subject_id, subject_no, customer);

			} catch (Exception e) {

				String errorText = "Error on subject inclusion insert: part_no=" + plan.getPartNo() + ", subj_no=" + subject_no + ", subj_id="
								   + subject_id + ", include=" + customer;
				SoluminaMigrationDaoPW.insertErrorLogG8("SUBJ_CUST_INCL", MigrationVariablesPW.FAIL, src_plan_id,
						MigrationVariablesPW.PLAN_UPDT_NO, MigrationVariablesPW.NA_ERR, MigrationVariablesPW.NA_ERR,
						MigrationVariablesPW.NA_ERR, 0, errorText, MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), subject_no, plan.getPartNo());
			}
		} // end while
	} // end addSubjCustomersIncl()


	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  SUBJECT CUSTOMERS EXCL
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@SuppressWarnings("rawtypes")
	private void addSubjCustomersExcl(PlanObjectParams plan, String subject_id, Number subject_no, String customer) {

		String src_plan_id = plan.getSrcPlanId();

		List excludeList = SoluminaMigrationDaoPW.querySubjectCustExcl(src_plan_id, subject_id);
		ListIterator excludeIter = excludeList.listIterator();

		while (excludeIter.hasNext()) {

			Map excludeInfo = (Map) excludeIter.next();
			String customer_exception = (String) excludeInfo.get("CUSTOMER");

			try {
				SfplPlanPW.insertSubjectCustomerException(plan.getNewPlanId(),					//:PLAN_ID,
														  MigrationVariablesPW.PLAN_UPDT_NO,	//:PLAN_UPDT_NO,
														  subject_no,							//:SUBJECT_NO,
														  MigrationVariablesPW.NEW_SUBJ_REV,	//:SUBJECT_REV,
														  customer,								//:CUST_ID, 
														  customer_exception);					//:CUST_ID_EXCEPTION 
			} catch (Exception e) {

				String errorText = "Error on subject exclusion insert: part_no=" + plan.getPartNo() + ", subject_no=" + subject_no +
								   ", subject_id=" + subject_id + ", include=" + customer + ", exclude="
								   + customer_exception;
				errorText = convertStackTraceToG8ErrText(e, errorText);
				SoluminaMigrationDaoPW.insertErrorLogG8("SUBJ_CUST_EXCL", MigrationVariablesPW.FAIL, src_plan_id,
						MigrationVariablesPW.PLAN_UPDT_NO, MigrationVariablesPW.NA_ERR, MigrationVariablesPW.NA_ERR,
						MigrationVariablesPW.NA_ERR, 0, errorText, MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), subject_no, plan.getPartNo());
			}
		} // end while
	} // end addSubjCustomersExcl()


	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  OPERATIONS
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@SuppressWarnings("rawtypes")
	private void addOperations(PlanObjectParams plan) {

		String part_no = plan.getPartNo();
		String src_plan_id = plan.getSrcPlanId();
		String new_plan_id = plan.getNewPlanId();
				
		boolean continueOp = true;
		
		//defect 1916
		//delete the operation that is auto-genereated when creating a new plan.
		IOperation.deleteOperation(new_plan_id, MigrationVariablesPW.NEW_PLAN_VER, MigrationVariablesPW.NEW_PLAN_REV,
									   MigrationVariablesPW.NEW_PLAN_ALT, MigrationVariablesPW.OP_TEN, SoluminaConstants.M);
		//end defect 1916
		
		List operList = SoluminaMigrationDaoPW.queryOperations(src_plan_id);
		ListIterator operIter = operList.listIterator();

		while (operIter.hasNext()) {

			Map operInfo = (Map) operIter.next();
			Number exe_order = (Number) operInfo.get("EXE_ORDER");
			Number src_oper_key = (Number) operInfo.get("OPER_KEY");
			String oper_no = (String) operInfo.get("OPER_NO");
			String title = (String) operInfo.get("OPER_TITLE");
			String plnd_work_loc = (String) operInfo.get("PLND_WORK_LOC");
			String plnd_work_dept = (String) operInfo.get("PLND_WORK_DEPT");
			String plnd_work_center = (String) operInfo.get("PLND_WORK_CENTER");
			BigDecimal scheduledLaborHoursPerUnit = (BigDecimal) operInfo.get("SCHED_LABOR_HOURS_PER_UNIT");//defect 1958

			
			/* **********************************************************************************
			 * NOTE: Operations with invalid work centers will continue to fail; thus when
			 * 		 center is invalid, stop processing oper to prevent further error logging.
			 * **********************************************************************************/
			continueOp = this.validateWorkCenter(plan, oper_no, plnd_work_dept, plnd_work_center);

			if (continueOp) {
				try {
					IOperation.eventsAfterCopyOperation(new_plan_id,						//String planId,
														MigrationVariablesPW.NEW_PLAN_VER,	//Number planVersion,
														MigrationVariablesPW.NEW_PLAN_REV, 	//Number planRevision,
														MigrationVariablesPW.NEW_PLAN_ALT, 	//Number planAlterations,
														oper_no,							//String operationNumberToLaunch,
														SoluminaConstants.N,				//String refreshTool,
														SoluminaConstants.N);				//String invokeCopiedOperation (shows the object after it's created)
				} catch (Exception e) {

					continueOp = false;
					String errorText = "Error on oper copy: part_no=" + part_no;
					
					SoluminaMigrationDaoPW.insertErrorLogG8("OPER_COPY", MigrationVariablesPW.FAIL, src_plan_id,
							MigrationVariablesPW.PLAN_UPDT_NO, oper_no, MigrationVariablesPW.NA_ERR,
							MigrationVariablesPW.NA_ERR, 0, errorText, MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e),null, plan.getPartNo());
				}

				if (continueOp) {
					try {
						IOperation.insertOperation(new_plan_id, 	 					//String planId,
												   MigrationVariablesPW.NEW_PLAN_VER,	//Number planVersion,
												   MigrationVariablesPW.NEW_PLAN_REV,	//Number planRevision,
												   MigrationVariablesPW.NEW_PLAN_ALT,	//Number planAlterations,
												   oper_no, 		 					//String operationNo,
												   SoluminaConstants.NO_EDIT, 		 	//String operationState,
												   title,			 					//String operationTitle,
												   null, 			 					//String plndMachineNumber,
												   scheduledLaborHoursPerUnit,			//Number scheduledLaborHoursPerUnit,
												   MigrationVariablesPW.LOWEST_NAV_LVL,	//String lowNavigationLevel,
												   null, 			 					//String operationType,
												   SoluminaConstants.N,  			 	//String operationOptFlag,
												   MigrationVariablesPW.PLAN_UPDT_NO,	//Number planUpdtNo,
												   SoluminaConstants.N, 	 		 	//String outsideProcessFlag,
												   null,  			 					//String supplierCode,
												   0, 	 			 					//Number outsideProcessDays,
												   plnd_work_loc,  	 					//String workLocation,
												   plnd_work_dept, 	 					//String plndWorkDeptartment,
												   plnd_work_center, 					//String plndWorkCenter,
												   null, 			 					//String planPlndWorkLoc,
												   0,  				 					//Number outsideProcessCostPerUnit,
												   SoluminaConstants.N,					//String autoCompleteFlag,
												   SoluminaConstants.N,					//String autoStartFlag,
												   SoluminaConstants.ONE_NUMBER,  		//Number occurRate,
												   null,  			 					//String testType,
												   null,  			 					//String ucfPlanOperVch1,
												   null,  			 					//String ucfPlanOperVch2,
												   null,  			 					//String ucfPlanOperVch3,
												   null,  			 					//String ucfPlanOperVch4,
												   null,  			 					//String ucfPlanOperVch5,
												   null,  			 					//String ucfPlanOperFlag1,
												   null,  			 					//String ucfPlanOperFlag2,
												   null,  			 					//Number ucfPlanOperNum1,
												   null,  			 					//Number ucfPlanOperNum2,
												   null,  			 					//String changeAuthority,
												   null,  			 					//String changeComments,
												   SoluminaConstants.Y, 	 			//String moveToOfd,
												   null,  			 					//String ucfPlanOperVch6,
												   null,  			 					//String ucfPlanOperVch7,
												   null,  			 					//String ucfPlanOperVch8,
												   null,  			 					//String ucfPlanOperVch9,
												   null,  			 					//String ucfPlanOperVch10,
												   null,  			 					//String ucfPlanOperVch11,
												   null,  			 					//String ucfPlanOperVch12,
												   null,  			 					//String ucfPlanOperVch13,
												   null,  			 					//String ucfPlanOperVch14,
												   null,  			 					//String ucfPlanOperVch15,
												   null,  			 					//Number ucfPlanOperNum3,
												   null,  			 					//Number ucfPlanOperNum4,
												   null,  			 					//Number ucfPlanOperNum5,
												   null,  			 					//Date ucfPlanOperDate1,
												   null,  			 					//Date ucfPlanOperDate2,
												   null,  			 					//Date ucfPlanOperDate3,
												   null,  			 					//Date ucfPlanOperDate4,
												   null,  			 					//Date ucfPlanOperDate5,
												   null,  			 					//String ucfPlanOperFlag3,
												   null,  			 					//String ucfPlanOperFlag4,
												   null,  			 					//String ucfPlanOperFlag5,
												   null,  			 					//String ucfPlanOperVch2551,
												   null,  			 					//String ucfPlanOperVch2552,
												   null,  			 					//String ucfPlanOperVch2553,
												   null,  			 					//String ucfPlanOperVch40001,
												   null,  			 					//String ucfPlanOperVch40002,
												   SoluminaConstants.N,   			 	//String stepsSequenceFlag,
												   null,  			 					//String operationChangeLevel,
												   SoluminaConstants.NORMAL_MIXCASE,	//String unitProcessing,
												   exe_order,		 					//Number sequenceNumber,
												   SoluminaConstants.UNIT_CENTRIC, 	 	//String orientationFlag,
												   SoluminaConstants.N,   			 	//String crossOrderFlag, 
												   SoluminaConstants.N,   			 	//String mustIssuePartFlag,
												   null,  			 					//String calledFrom,
												   SoluminaConstants.ONE_NUMBER,   		//Number unitsPerCycle,
												   SoluminaConstants.N, 			 	//String autoBatchFlag,
												   SoluminaConstants.OFF_MIXCASE,  		//String printLabel,
												   SoluminaConstants.ONE_NUMBER,   		//Number labelsPerCycle,
												   null,  			 					//String reportId,
												   null,  			 					//String reconcileScrap,
												   SoluminaConstants.N);  			 	//String batchFlag
					} catch (Exception e) {

						continueOp = false;
						String errorText = "Error on oper insert: part_no=" + part_no;
						
						SoluminaMigrationDaoPW.insertErrorLogG8("OPER_INSERT", MigrationVariablesPW.FAIL, src_plan_id,
								MigrationVariablesPW.PLAN_UPDT_NO, oper_no, MigrationVariablesPW.NA_ERR,
								MigrationVariablesPW.NA_ERR, 0, errorText, MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), null, plan.getPartNo());
					}
				} //end if (continueOp) "for insertOperation()"

				if (continueOp) {

					Map operHdrMap = IOperationDao.selectOperationHeaderInformation(new_plan_id,
																					MigrationVariablesPW.NEW_PLAN_VER,
																					MigrationVariablesPW.NEW_PLAN_REV,
																					MigrationVariablesPW.NEW_PLAN_ALT,
																				 	oper_no);
					//Update PlanObjectParams with the current oper
					plan.setNewOperKey((Number) operHdrMap.get("OPER_KEY"));
					plan.setOperNo(oper_no);

					this.addHdrFtrXML(plan, src_oper_key);
					this.addStepsXML(plan, src_oper_key);

					//Standard Operation Check
					String stdOperTag = SoluminaMigrationDaoPW.checkOperForStdOperTag(src_plan_id, oper_no);

					if (stdOperTag != null) {
						insertStdOperation(plan, src_oper_key, stdOperTag);
					}
				} //end if (continueOp) "for oper content insertions"
			} //end if (continueOp) "for is valid work center"
		} //end while(operIter)
	} // end addOperations()


	//Defect 1383
	private void addSubjAuthorities(PlanObjectParams plan){
		String part_no = plan.getPartNo();
		String src_plan_id = plan.getSrcPlanId();
		String new_plan_id = plan.getNewPlanId();
		
		List operList = SoluminaMigrationDaoPW.querySubjectAuthorities(src_plan_id);
		ListIterator operIter = operList.listIterator();

		while(operIter.hasNext()) {
			Map operInfo = (Map) operIter.next();
			
			String planId = new_plan_id;
			Number planRevision = MigrationVariablesPW.NEW_PLAN_REV;
			Number subjectNo = (Number) operInfo.get("SUBJECT_NO");
			Number subjectRev = MigrationVariablesPW.NEW_PLAN_REV;
			String authorityType = (String) operInfo.get("AUTHORITY_TYPE");
			String authorityDetail = (String) operInfo.get("AUTHORITY_DETAIL");
			String manualNo = (String) operInfo.get("MANUAL_NO");
			String manualRev = (String) operInfo.get("MANUAL_REV");
			String manualProvider = (String) operInfo.get("MANUAL_PROVIDER");
			String manualType = (String) operInfo.get("MANUAL_TYPE");
			String manualSection = (String) operInfo.get("MANUAL_SECTION");
			String sbPartNo = (String) operInfo.get("SB_PART_NO");
			String authorityRev = (String) operInfo.get("AUTHORITY_REV");
			Date authorityRevDate = (Date) operInfo.get("AUTHORITY_REV_DATE");

			String subjectDesc =(String) operInfo.get("SUBJECT_TITLE");
			
			try{
			AuthorityImpl.insertPlanTaskAuth(planId, planRevision, subjectNo.toString(), subjectRev.toString(), subjectDesc, authorityType,
											authorityDetail, manualNo, manualRev, manualProvider, 
											manualType, manualSection, sbPartNo, authorityRev, 
											authorityRevDate);
			}
			catch(Exception e){
				StringBuffer errMessage = new StringBuffer();
				errMessage.append(e.getMessage());
				errMessage.append("\nTask Group Number: " + subjectNo);
				errMessage.append("\n  Task Rev Number: " + subjectRev);
				errMessage.append("\n           Manual: " + manualNo);
				errMessage.append("\n   Authority Type: " + authorityType);
				errMessage.append("\n  Authority Notes: " + authorityDetail);
			
				SoluminaMigrationDaoPW.insertErrorLogG8("TASK_AUTH_INSERT", MigrationVariablesPW.WARN, src_plan_id,
						MigrationVariablesPW.PLAN_UPDT_NO, plan.getOperNo(), MigrationVariablesPW.NA_ERR,
						manualNo, 0, errMessage.toString(), MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), subjectNo, plan.getPartNo());
			}
		}
	}
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  OPER HEADER / FOOTER
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@SuppressWarnings("rawtypes")
	private void addHdrFtrXML(PlanObjectParams plan, Number src_oper_key) {

		String src_plan_id = plan.getSrcPlanId();

		//new_step_key = null; step_no = "...";
		plan.setStepParamsWithHdrFtrInfo();

		List operHdrFtrList = SoluminaMigrationDaoPW.operHeaderFooterXML(src_plan_id, src_oper_key);
		ListIterator hdrFtrIter = operHdrFtrList.listIterator();

		while (hdrFtrIter.hasNext()) {

			Map operHdrFtrMap = (Map) hdrFtrIter.next();

			//VIS table STEP_NO for hdr/ftr's will always be "HEAD" or "FOOT"
			String operHdrFtr = (String) operHdrFtrMap.get("STEP_NO");
			String rawXML = (String) operHdrFtrMap.get("TEXT");

			EContentLocation location = ("HEAD".equals(operHdrFtr)) ?
										 EContentLocation.HEAD : EContentLocation.FOOT;

			if(plan.getPWP() != null && plan.getPWP().contains("OKC")) { //Defect 1198

				//Note... OKC headers aren't dealt with in this method
				if ("FOOT".equals(operHdrFtr)) { //This is an unexpected event

					this.addObjectsFromXmlSource(plan, rawXML, location);
					SiteSpecificOKC.logFooterWarning(plan);
				}
			} else { //behavior for non-OKC locations

				this.addObjectsFromXmlSource(plan, rawXML, location);
			}
		} //end while
	} // end addHdrFtrXML()

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  STEPS
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@SuppressWarnings({ "rawtypes" })
	private void addStepsXML(PlanObjectParams plan, Number src_oper_key) {

		String src_plan_id = plan.getSrcPlanId();
		String new_plan_id = plan.getNewPlanId();
		Number new_oper_key = plan.getNewOperKey();
		String oper_no = plan.getOperNo();

		EContentLocation location = EContentLocation.STEP;
		boolean continueStep;
		boolean stepExists = false;//defect 1928
		List stepList = SoluminaMigrationDaoPW.queryStepsXML(src_plan_id, src_oper_key);

		if(plan.getPWP() != null && plan.getPWP().contains("OKC")) { //Defect 1198 
			stepList = SiteSpecificOKC.addHeaderToStepList(plan, src_oper_key, stepList);
		}

		ListIterator stepIter = stepList.listIterator();

		while (stepIter.hasNext()) {
			//defect 1928
			boolean isXMLEmpty = true;
			if(stepExists == false){
				stepExists = true;
			}
			//end defect 1928
			continueStep = true;

			Map stepInfo = (Map) stepIter.next();
			String step_no = (String) stepInfo.get("STEP_NO");
			String step_title = (String) stepInfo.get("STEP_TITLE");
			String rawXML = (String) stepInfo.get("TEXT");
			//defect 1928
			if(rawXML != null){
				if(!rawXML.isEmpty()){
					isXMLEmpty = false;
				}
			}
			else{
				isXMLEmpty = false;
			}
			if(isXMLEmpty){
				String warningText = "Warning: XML content was empty.";
				SoluminaMigrationDaoPW.insertErrorLogG8("STEP", MigrationVariablesPW.WARN, src_plan_id,
						MigrationVariablesPW.PLAN_UPDT_NO, oper_no, step_no, MigrationVariablesPW.NA_ERR,
						0, warningText, MigrationVariablesPW.SFMFG, null, null, plan.getPartNo());
			}
			//end defect 1928
			try {
					IStep.insertSteps(new_plan_id,							//String planId,
									  MigrationVariablesPW.NEW_PLAN_VER,	//Number planVersion,
									  MigrationVariablesPW.NEW_PLAN_REV,	//Number planRevision,
									  MigrationVariablesPW.NEW_PLAN_ALT,	//Number planAlterations,
									  oper_no, 								//String operationNumber,
									  step_no, 								//String stepNumbers,
									  step_title,							//String stepTitle,
									  MigrationVariablesPW.LOWEST_NAV_LVL,	//String lowestNavigationLevel,
									  new_oper_key, 						//Number operationKey,
									  null, 								//String userConfigurableField1,
									  null, 								//String userConfigurableField2,
									  null, 								//String userConfigurableField3,
									  null, 								//String userConfigurableField4,
									  null, 								//String userConfigurableField5,
									  null, 								//Number userConfigurableField6,
									  null, 								//Number userConfigurableField7,
									  null, 								//String userConfigurableFlag1,
									  null); 								//String userConfigurableFlag2
			} catch (Exception e) {

				continueStep = false;
				String errorText = "Error on step create: part_no=" + plan.getPartNo();

				SoluminaMigrationDaoPW.insertErrorLogG8("STEP", MigrationVariablesPW.FAIL, src_plan_id,
						MigrationVariablesPW.PLAN_UPDT_NO, oper_no, step_no, MigrationVariablesPW.NA_ERR,
						0, errorText, MigrationVariablesPW.SFMFG, null, null, plan.getPartNo());
			}

			if(continueStep) {
				//Get the new step key for the newly created step
				List stepHeaderList = IStepDao.selectStepHeaderInformation(new_plan_id,
																		   MigrationVariablesPW.NEW_PLAN_VER,
																		   MigrationVariablesPW.NEW_PLAN_REV,
																		   MigrationVariablesPW.NEW_PLAN_ALT,
																		   oper_no,
																		   step_no);
				Map stepMap = (Map) stepHeaderList.get(0);

				//Update PlanObjectParams with the current step
				plan.setNewStepKey((Number) stepMap.get("STEP_KEY"));
				plan.setStepNo(step_no);

				//Add objects to newly created step
				this.addObjectsFromXmlSource(plan, rawXML, location);
			}
		} // end while(stepIter)
		//defect 1928
		if(stepExists == false && !SoluminaConstants.ELLIPSIS.equals(plan.getStepNo())){//defect 1928a, do log errors for headers and footers.
			String errorText = "Error: Step did not exist in VIS_STEP.";

			SoluminaMigrationDaoPW.insertErrorLogG8("STEP", MigrationVariablesPW.FAIL, src_plan_id,
					MigrationVariablesPW.PLAN_UPDT_NO, oper_no, MigrationVariablesPW.NA_ERR, MigrationVariablesPW.NA_ERR,
					0, errorText, MigrationVariablesPW.SFMFG, null, null, plan.getPartNo());
		}
		//end defect 1928
	} // end addStepsXML()


	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  SUBJECT OPERATIONS
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@SuppressWarnings("rawtypes")
	private void addSubjOperations(PlanObjectParams plan) {

		String src_plan_id = plan.getSrcPlanId();

		List subjOperList = SoluminaMigrationDaoPW.queryOperationSubjects(src_plan_id);
		ListIterator subjOperIter = subjOperList.listIterator();

		while (subjOperIter.hasNext()) {

			Map subjOperInfo = (Map) subjOperIter.next();
			String oper_no = (String) subjOperInfo.get("OPER_NO");
			Number subject_no = (Number) subjOperInfo.get("SUBJECT_NO");
			String plnd_work_dept = (String) subjOperInfo.get("PLND_WORK_DEPT");
			String plnd_work_center = (String) subjOperInfo.get("PLND_WORK_CENTER");

			/* **********************************************************************************
			 * NOTE: If a work center is invalid, the operation never added and corresponding
			 * 		 oper-subjects will likewise fail. This check added to prevent further error
			 * 		 logging when root problem is "invalid work center."
			 * **********************************************************************************/
			boolean validCntr = SoluminaMigrationDaoPW.validateWorkCenter(plan.getLocation(),
																		  plnd_work_dept, plnd_work_center);
			if(validCntr) {
				try {
					ISfplPlan.insertSubjectOperation (plan.getNewPlanId(),					//:PLAN_ID,
													  MigrationVariablesPW.NEW_PLAN_VER,	//:PLAN_VERSION,
													  MigrationVariablesPW.NEW_PLAN_REV,	//:PLAN_REVISION,
													  MigrationVariablesPW.NEW_PLAN_ALT,	//:PLAN_ALTERATIONS,
													  subject_no, 							//:NEW_SUBJECT_NO,
													  oper_no);								//:OPER_NO 
				} catch (Exception e) {

					String errorText = "Error on subject operation insert: part_no=" + plan.getPartNo()
									   + ", subj_no=" + subject_no;

					SoluminaMigrationDaoPW.insertErrorLogG8("SUBJ_OPERS", MigrationVariablesPW.FAIL, src_plan_id,
							MigrationVariablesPW.PLAN_UPDT_NO, oper_no, MigrationVariablesPW.NA_ERR,
							MigrationVariablesPW.NA_ERR, 0, errorText, MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), subject_no, plan.getPartNo());
				}
			}
		} // end while
	} // end addSubjOperations()


	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  ADD OBJECTS FROM XML SOURCE
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	private void addObjectsFromXmlSource(PlanObjectParams plan, String rawXML, EContentLocation location) {
		try {
			Content xmlContent = StepInterpretter.readStepContent(rawXML);

			String plain_text = null;
			String rich_text = null;

			for (JAXBElement<?> xmlObject : xmlContent.getTextOrNoteOrWarning()) {

				String name = xmlObject.getName().getLocalPart();

				switch (name) {

				case "maintenance-plan-references":
				case "lids":
				case "text":

					plain_text = renderTxt((RichText) xmlObject.getValue(), "");
					rich_text = renderRichText(name, (RichText) xmlObject.getValue(), "", plan);
					
					this.insertTextXML(plan, plain_text, rich_text, location);
					break;

				case "note":

					plain_text = renderTxt((RichText) xmlObject.getValue(), "NOTE:\n");
					rich_text = renderRichText(name, (RichText) xmlObject.getValue(), "NOTE:\n", plan);
					
					this.insertTextXML(plan, plain_text, rich_text, location);
					break;

				case "warning":

					plain_text = renderTxt((RichText) xmlObject.getValue(), "WARNING\n\n");
					rich_text = renderRichText(name, (RichText) xmlObject.getValue(), "WARNING\n\n", plan);
					
					this.insertTextXML(plan, plain_text, rich_text, location);
					break;

				case "caution":

					plain_text = renderTxt((RichText) xmlObject.getValue(), "CAUTION\n\n");
					rich_text = renderRichText(name, (RichText) xmlObject.getValue(), "CAUTION\n\n", plan);
					
					this.insertTextXML(plan, plain_text, rich_text, location);
					break;

				case "native-rich-text":			

					rich_text = (String) xmlObject.getValue();
					plain_text = null;

					this.insertTextXML(plan, plain_text, rich_text, location);
					break;

				case "standard-text": 

					StdText std_text = (StdText) xmlObject.getValue(); 
					String object_tag = std_text.getId();

					this.insertStdTextXML(plan, object_tag, location);
					break;

				case "part-block": //Defect 1303
					PartBlock part_block = (PartBlock) xmlObject.getValue();
					this.insertPartXmlMulti(plan, part_block, location);
					break;

				case "tool-block":

					ToolBlock tool_block = (ToolBlock) xmlObject.getValue(); //Defect 1304
					this.insertToolXmlMulti(plan, tool_block, location);
					break;

				case "option-set":

					//An option set is a group of rich text objects and a data collection
					OptionSet options = (OptionSet) xmlObject.getValue();
					{
						StringBuilder optionSet = new StringBuilder();
						StringBuilder plainTxt = new StringBuilder();

						//Add the option header
						optionSet.append(renderTxt("Select from ONE of the following options:",
												   MigrationVariablesPW.RTF));

						plainTxt.append("Select from ONE of the following options:");

						//Add the options
						int i = 1;
						for (RichText option : options.getOption()) {

							optionSet.append(renderTxt(String.format("\n%d)\t", i),
											 MigrationVariablesPW.RTF));
							optionSet.append(renderTxt(option, MigrationVariablesPW.RTF));
							plainTxt.append(renderTxt(option, String.format("\r\n%d)\t", i)));
							i++;
						}

						rich_text = renderRichText("text", optionSet.toString(), "", plan);
						plain_text = plainTxt.toString();

						this.insertTextXML(plan, plain_text, rich_text, location);

						//Add data collections
						ObjectFactory objfactory = new ObjectFactory();
						DataCollectionBlock optDcb = objfactory.createDataCollectionBlock();

						DataCollection dc = objfactory.createDataCollection();
						dc.setName("Option");
						dc.setType("Integer");
						dc.setUnits("Integer");
						dc.setUpperLimit(options.getOption().size() + "");
						dc.setLowerLimit("1");
						optDcb.getDataCollection().add(dc);

						this.insertDataCollectionXmlMulti(plan, optDcb, location);
					}
					break;

				case "buy-off-block":

					BuyOffBlock bob = (BuyOffBlock) xmlObject.getValue();

					this.insertBuyoffXmlMulti(plan, bob, location);
					break;

				case "data-collection-block":

					DataCollectionBlock dcb = (DataCollectionBlock) xmlObject.getValue();

					this.insertDataCollectionXmlMulti(plan, dcb, location);
					break;

				case "graphic":

					Graphic graphic = (Graphic) xmlObject.getValue(); 
					String img_object_id = graphic.getId();

					this.insertGraphicsXML(plan, img_object_id, location);
					break;

				default:

					String errorText = "Plan object \"" + name + "\" undefined and went unprocessed";

					SoluminaMigrationDaoPW.insertErrorLogG8("UNDEFINED_OBJ", MigrationVariablesPW.FAIL,
							plan.getSrcPlanId(), MigrationVariablesPW.PLAN_UPDT_NO, plan.getOperNo(), plan.getStepNo(),
							MigrationVariablesPW.NA_ERR, 0, errorText, MigrationVariablesPW.SFMFG, null, null, plan.getPartNo());
					break;

				} // end switch
			} // end for(JAXBElement)

		} catch (Exception e) {

			String errorText = "Error on plan object insert: part_no=" + plan.getPartNo();
			SoluminaMigrationDaoPW.insertErrorLogG8("OBJECT_INSERT", MigrationVariablesPW.FAIL, plan.getSrcPlanId(),
					MigrationVariablesPW.PLAN_UPDT_NO, plan.getOperNo(), plan.getStepNo(), MigrationVariablesPW.NA_ERR,
					0, errorText, MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), null, plan.getPartNo());
		}
	} // end addObjectsFromXmlSource()


	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  STANDARD OPERATION INSERT
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@SuppressWarnings("rawtypes")
	private void insertStdOperation(PlanObjectParams plan, Number src_oper_key, String stdOperTag) {

		String work_location = plan.getLocation();

		boolean stdOperExists = SoluminaMigrationDaoPW.mmObjectExists(stdOperTag, SoluminaConstants.STANDARD_OPERATION);

		//Add standard op to library if it doesn't already exist
		if (!stdOperExists) {

			String description = SoluminaMigrationDaoPW.getStdOperDesc(stdOperTag);

			try {
				IStandardOperationPW.createStandardOperation(stdOperTag,		  				   			 //String tag
															 description,		  				   			 //String description
															 plan.getPWP(),		  				   			 //String releasePackage
															 SoluminaConstants.STANDARD_OPERATION,			 //String standardOperationType
															 SoluminaConstants.STANDARD_OPERATION_WORK_FLOW, //String workFlow
															 null,				  				   			 //String commodityJurisdiction
															 null,				  				   			 //String commodityClassification
															 SoluminaConstants.N,				  			 //String associateChange
															 work_location,									//String workLocation
															 plan.getNewPlanId()); 				   			 	 

				Map stdOperMap = SoluminaMigrationDaoPW.getStdOperInfo(stdOperTag);
				String std_plan_id = (String) stdOperMap.get("STDOPER_PLAN_ID");
				Number std_oper_key = (Number) stdOperMap.get("STDOPER_OPER_KEY");

				PlanObjectParams stdOpPlan = new PlanObjectParams(std_plan_id, std_oper_key,
																  MigrationVariablesPW.OP_TEN, plan);

				this.addHdrFtrXML(stdOpPlan, src_oper_key);
				this.addStepsXML(stdOpPlan, src_oper_key);

				SoluminaMigrationDaoPW.setStdOperTitle(stdOperTag, std_plan_id);

				try {
					SoluminaMigrationDaoPW.setStdOperSecurityGroup(stdOperTag, work_location);

				} catch (Exception e) {

					String defaultLoc = "1350"; //1350=SecGrp=OKCF135 //1180=OKCF119 //4100=CECF100
					SoluminaMigrationDaoPW.setStdOperSecurityGroup(stdOperTag, defaultLoc);

					String errorText = "Error on stdOp security group insert. Defaulted to security group for work location "
									   + defaultLoc + ": stdOp tag=" + stdOperTag;

					SoluminaMigrationDaoPW.insertErrorLogG8("STDOP_SEC_GRP", MigrationVariablesPW.WARN, plan.getSrcPlanId(),
							MigrationVariablesPW.PLAN_UPDT_NO, plan.getOperNo(), plan.getStepNo(), MigrationVariablesPW.NA_ERR,
							0, errorText, MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), null, plan.getPartNo());
				}
				//TODO: Insert Loc/Dept/Cntr. Ask about auto-releasing?

			} catch (Exception e) {
				this.logPlanObjectFail(plan, MigrationVariablesPW.STDOPER, "CREATE", "create", e);
			}
		} //end if (!stdOperExists)

		//Mark current op as a standard op
		try {
			Map stdOperMap = SoluminaMigrationDaoPW.getStdOperInfo(stdOperTag);
			String stdoper_object_id = (String) stdOperMap.get("STDOPER_OBJECT_ID");
			BigDecimal stdoper_rev = (BigDecimal) stdOperMap.get("STDOPER_REV");
			SoluminaMigrationDaoPW.setOperAsStdOper(stdoper_object_id, plan.getNewPlanId(), plan.getNewOperKey());

			//Defect 1096
			//Set standard op as complete
			if(stdoper_rev.intValue() == 1){
				SoluminaMigrationDaoPW.updateStdOperAsComplete(stdoper_object_id);
			}
		} catch (Exception e) {
						
			this.logPlanObjectFail(plan, MigrationVariablesPW.STDOPER, "ID_LINK", "assignment", e);
		}
	} //end insertStdOperation()


	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  TEXT INSERT
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	private void insertTextXML(PlanObjectParams plan, String plain_text, String rich_text, EContentLocation location) {

		rich_text = this.encodeRichTextXML(rich_text);

		Map<String,String> objParamMap = getPlanObjectParams(MigrationVariablesPW.TEXT, "Embed", null, location);
		String block_type = (String) objParamMap.get("BLOCK_TYPE");
		String ref_id = (String) objParamMap.get("REF_ID");

		try {
			//Defect 1396
			
			IPlan.insertTextObject(ref_id, 					//:OBJECT_ID
								   SoluminaConstants.EN, 	//:@LANG
								   rich_text, 				//:@TEXT
								   plain_text, 				//:@PLAINTEXT
								   null, 
								   null, 
								   null, 
								   null, 
								   null, 
								   null, 
								   null, 
								   null, 
								   null);
			//end Defect 1396
			try {
				this.savePlanObject(plan, objParamMap, location);
			} catch (Exception e) {

				if(location.equals(EContentLocation.PLAN)) { //Defect 1269
					block_type = "PLAN_HDR";
				}
				this.logPlanObjectFail(plan, MigrationVariablesPW.TEXT, block_type, "save", e);
			}

		} catch (Exception e) {

			if(location.equals(EContentLocation.PLAN)) { //Defect 1269
				block_type = "PLAN_HDR";
			}
			this.logPlanObjectFail(plan, MigrationVariablesPW.TEXT, block_type, "insert", e);
		}

	} // end insertTextXML()


	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  STANDARD TEXT INSERT
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@SuppressWarnings("rawtypes")
	private void insertStdTextXML(PlanObjectParams plan, String object_tag, EContentLocation location) {

		/* *******************************************************************************************
		 * Note: "Block type" also derived in call to getObjectParams() further below. Must be
		 * 		 prematurely derived at this point for StdTxt because StdTxt insertion must occur
		 * 		 BEFORE instantiating object parameters - unlike all other plan object inserts.
		 * *******************************************************************************************/
    	String block_type;

    	if (location.equals(EContentLocation.HEAD)) { block_type = "HEADER"; }
		else if (location.equals(EContentLocation.FOOT)) { block_type = "FOOTER"; }
		else { block_type = null; } //(location.equals(EContentLocation.STEP))

		boolean stdTextExists = SoluminaMigrationDaoPW.mmObjectExists(object_tag, SoluminaConstants.STDTEXT);

		//Only add NEW stdTxt to library (prevent duplicates)
		if (!stdTextExists) {

			try {
				IStandardTextPW.insertStandardText(object_tag,						//String standardTextTag
												   "Migrated",						//String description //TODO: Need to bring over from G5
												   SoluminaConstants.AT_ANY_BLOCK,	//String blockType
												   plan.getPWP(),					//String releasePackage
												   "DISPATCH",						//String calledFrom
												   SoluminaConstants.STDTEXT,		//String standardTextType
												   SoluminaConstants.STD_TEXT_AUTH,	//String workFlow
												   null,							//String commodityJurisdiction
												   null,							//String commodityClassification
												   SoluminaConstants.N,				//String associateChange
												   plan.getLocation());				//String workLocation

				try {
					SoluminaMigrationDaoPW.setStandardTextText(object_tag);

				} catch (Exception e) {

					String errorText;
					boolean duplicateTags = e.toString().contains("ORA-01427"); //single-row subquery returns more than one row

					if(duplicateTags) {
						errorText = "Multiple instances of object_tag=\"" + object_tag + "\" detected in staging table.\n"
									+ "Standard text inserted to SFCORE_MM_OBJECT but TEXT_DATA left empty.";
					} else {
						errorText = e.toString();
					}
							SoluminaMigrationDaoPW.insertErrorLogG8("SET_STD_TXT", MigrationVariablesPW.FAIL, plan.getSrcPlanId(),
							MigrationVariablesPW.PLAN_UPDT_NO, plan.getOperNo(), plan.getStepNo(),
							MigrationVariablesPW.NA_ERR, 0, errorText, MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), null, plan.getPartNo());
				}

			} catch (Exception e) {
				SoluminaMigrationDaoPW.insertErrorLogG8("INSERT_STD_TXT", MigrationVariablesPW.FAIL, plan.getSrcPlanId(),
						MigrationVariablesPW.PLAN_UPDT_NO, plan.getOperNo(), plan.getStepNo(),
						MigrationVariablesPW.NA_ERR, 0, "Failed to insert Standard Text.", MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), null, plan.getPartNo());
			}
		} //end if(!stdTextExists)

		try {
			String object_id = SoluminaMigrationDaoPW.getObjectIdForObjectTag(object_tag);

			//Standard Text the only "plan object insert" that custom feeds its own ref_id (aka object_id)
			Map objParamMap = getPlanObjectParams(MigrationVariablesPW.STDTEXT, "Embed", object_id, location);

			this.savePlanObject(plan, objParamMap, location);

		} catch (Exception e) {
			this.logPlanObjectFail(plan, MigrationVariablesPW.STDTEXT, block_type, "save", e);
		}
	} // end insertStdTextXML()


	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  PART INSERT
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//Defect 1303
	@SuppressWarnings("rawtypes")
	private void insertPartXmlMulti(PlanObjectParams plan, PartBlock part_block, EContentLocation location) {

		String udvId = "OB1_100085";
		OutputParameter item_id = new OutputParameter();

		String new_plan_id = plan.getNewPlanId();
		String oper_no = plan.getOperNo();
		String step_no = plan.getStepNo();

		Map objParamMap = getPlanObjectParams(MigrationVariablesPW.PART, udvId, null, location);
		String block_type = (String) objParamMap.get("BLOCK_TYPE");
		String block_id = (String) objParamMap.get("BLOCK_ID");
		String ref_id = (String) objParamMap.get("REF_ID");
		
		for(Part part : part_block.getPart()){
			String partNumber = part.getId();
			String partAction = part.getAction();
			String itemNotes = part.getNotes();
			String partChange = part.getChg();
			String expirationFlag = part.getExpFlag();
			Number itemQuantity = Double.parseDouble(part.getQty());
			String serialFlag = part.getSerialFlag();
			String lotFlag = part.getLotFlag();
			String spoolFlag= part.getSpoolFlag();
			String uom = "MATERIAL";
			String ucfPlanItemVch1 = part.getVch1();
			String ucfPlanItemVch2 = part.getVch2();
			String ucfPlanItemVch3 = part.getVch3();

			try {
				IStep.insertStepItems(new_plan_id,							//String planId
									  MigrationVariablesPW.NEW_PLAN_VER,	//Number planVersion
									  MigrationVariablesPW.NEW_PLAN_REV,	//Number planRevision
									  MigrationVariablesPW.NEW_PLAN_ALT,	//Number planAlterations
									  oper_no,								//String operationNumber
									  partNumber,							//String partNumber
									  partChange,							//String partChange
									  null,									//String referenceDesignation
									  null,									//Number referenceDesignationPreferredRank
									  null,									//String findNumber
									  itemQuantity,							//Number itemQuantity
									  partAction,							//String partAction
									  itemNotes,							//String itemNotes
									  serialFlag,							//String serialFlag
									  lotFlag,								//String lotFlag
									  expirationFlag,						//String expirationFlag
									  spoolFlag,							//String spoolFlag
									  null,									//String optionalDc1Flag
									  null,									//String optionalDc2Flag
									  null,									//String optionalDc3Flag
									  null,									//String optionalDc4Flag
									  ucfPlanItemVch1,						//String ucfPlanItemVch1
									  ucfPlanItemVch2,						//String ucfPlanItemVch2
									  ucfPlanItemVch3,						//String ucfPlanItemVch3
									  null,									//String ucfPlanItemVch4
									  null,									//String ucfPlanItemVch5
									  SoluminaConstants.N,					//String ucfPlanItemFlag1
									  SoluminaConstants.N,					//String ucfPlanItemFlag2
									  null,									//Number ucfPlanItemNum1
									  null,									//Number ucfPlanItemNum2
									  SoluminaConstants.N,					//String overConsumptionFlag
									  SoluminaConstants.UNIT_CENTRIC,		//String orientationFlag
									  SoluminaConstants.N,					//String crossOrderFlag
									  null,									//String itemCategory
									  null,									//String storeLocation
									  null,									//String unloadingPoint
									  step_no,								//String stepNumber
									  SoluminaConstants.N,					//String optionalFlag
									  null,									//String ucfPlanItemVch6
									  null,									//String ucfPlanItemVch7
									  null,									//String ucfPlanItemVch8
									  null,									//String ucfPlanItemVch9
									  null,									//String ucfPlanItemVch10
									  null,									//String ucfPlanItemVch11
									  null,									//String ucfPlanItemVch12
									  null,									//String ucfPlanItemVch13
									  null,									//String ucfPlanItemVch14
									  null,									//String ucfPlanItemVch15
									  null,									//Number ucfPlanItemNum3
									  null,									//Number ucfPlanItemNum4
									  null,									//Number ucfPlanItemNum5
									  null,									//Date ucfPlanItemDate1
									  null,									//Date ucfPlanItemDate2
									  null,									//Date ucfPlanItemDate3
									  null,									//Date ucfPlanItemDate4
									  null,									//Date ucfPlanItemDate5
									  null,									//String ucfPlanItemFlag3
									  null,									//String ucfPlanItemFlag4
									  null,									//String ucfPlanItemFlag5
									  null,									//String ucfPlanItemVch2551
									  null,									//String ucfPlanItemVch2552
									  null,									//String ucfPlanItemVch2553
									  null,									//String ucfPlanItemVch40001
									  null,									//String ucfPlanItemVch40002
									  item_id,								//OutputParameter itemId
									  ref_id,								//String referenceId
									  block_id,								//String blockId
									  block_type,							//String blockType
									  null,									//String removeAction
									  null,									//String utilizationRule
									  SoluminaConstants.N,					//String isTrackable
									  null,									//String uidEntryName
									  SoluminaConstants.N,					//String uidItemFlag
									  null,									//String unitType
									  null,									//String effectivityFrom
									  null,									//String effectivityThru
									  null,									//Date effectivityFromDate
									  null,									//Date effectivityThruDate
									  plan.getPlanType(),					//String itemSubtype
									  "Part Title",							//String partTitle
									  null,									//String standardPartFlag
									  null,									//Number displayLineNo
									  null,									//String bomLineNumber
									  null,									//String phantomParent
									  null,									//Number dropDispLineNo
									  null,									//String bomCompId
									  null,									//String replacementPartNo
									  null,									//String replacementPartChg
									  uom,									//String uom
									  null,									//String bomLineId
									  null);								//String calledFrom
	
				IOperationDataCollection.checkDifferentOrientation(new_plan_id,    	   	 					//String planId
																   plan.getNewOperKey(), 					//Number operationKey
																   plan.getNewStepKey(), 					//Number stepKey
																   ref_id,			   	 					//String referenceId
																   null,			   	 					//String excludedOrientationFlag
																   null,			   	 					//String excludedPrimaryKey1
																   null,			   	 					//String excludedPrimaryKey2
																   SoluminaConstants.PART_DATA_COLLECTION); //String calledFrom
				}catch (Exception e) {
					//this.logPlanObjectFail(plan, MigrationVariablesPW.PART, block_type, "insert", "Failed to insert Component Part " + partNumber+ "!\n" + e.toString());
					//String position = block_type == null ? "STEP" : block_type;

					String errorText = "Error on " + MigrationVariablesPW.PART + " " + "insert" + ": part_no="
									   + plan.getPartNo() + "\n" + partNumber;

					if(DEBUG){
						System.err.println(errorText);
					}
					SoluminaMigrationDaoPW.insertErrorLogG8(MigrationVariablesPW.PART + "_" + block_type, MigrationVariablesPW.FAIL, plan.getSrcPlanId(),
															MigrationVariablesPW.PLAN_UPDT_NO, plan.getOperNo(), plan.getStepNo(),
															partNumber, 0, errorText, MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), null, plan.getPartNo());
				}
			}
			try {
				this.savePlanObject(plan, objParamMap, location);
			} catch (Exception e) {
				this.logPlanObjectFail(plan, MigrationVariablesPW.PART, block_type, "save", e);
			}
	} // end defect 1303


	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  TOOL INSERT
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@SuppressWarnings("rawtypes")
	private void insertToolXmlMulti(PlanObjectParams plan, ToolBlock toolBlock, EContentLocation location) {

		String udvId = "OB1_100084";

		String new_plan_id = plan.getNewPlanId();
		String oper_no = plan.getOperNo();
		String step_no = plan.getStepNo();

		Map objParamMap = getPlanObjectParams(MigrationVariablesPW.TOOL, udvId, null, location);
		String block_type = (String) objParamMap.get("BLOCK_TYPE");
		String block_id = (String) objParamMap.get("BLOCK_ID");
		String ref_id = (String) objParamMap.get("REF_ID");
		//Defect 1304
		for(Tool tool : toolBlock.getTool()){
			String toolNumber = tool.getId();
			//tool.getRev();
			Integer quantity = Integer.parseInt(tool.getQty());
			String toolNotes = tool.getNotes();
			//end Defect 1304
			try {
				IStep.insertStepTool(new_plan_id, 							//String planId
									 MigrationVariablesPW.NEW_PLAN_VER,		//Number planVersion
									 MigrationVariablesPW.NEW_PLAN_REV,		//Number planRevision
									 MigrationVariablesPW.NEW_PLAN_ALT,		//Number planAlterations
									 oper_no,								//String operationNumber
									 step_no,								//String stepNumber
									 toolNumber,							//String toolNumber
									 MigrationVariablesPW.NA_OOB,			//String toolChange
									 SoluminaConstants.N,					//String serialFlag
									 SoluminaConstants.N,					//String expirationFlag
									 MigrationVariablesPW.LOWEST_NAV_LVL,	//String lowestNavigationLevel
									 toolNotes,								//String toolNotes
									 quantity,								//Number quantity
									 null,									//String ucfPlanToolVch1
									 null,									//String ucfPlanToolVch2
									 null,									//String ucfPlanToolVch3
									 null,									//String ucfPlanToolFlag1
									 null,									//Number ucfPlanToolNum1
									 null,									//Date ucfPlanToolDate1
									 SoluminaConstants.UNIT_CENTRIC,		//String orientationFlag
									 SoluminaConstants.N,					//String crossOrderFlag
									 SoluminaConstants.N,					//String optionalFlag
									 ref_id,								//String referenceId
									 block_id,								//String blockId
									 null,									//String ucfPlanToolVch4
									 null,									//String ucfPlanToolVch5
									 null,									//String ucfPlanToolVch6
									 null,									//String ucfPlanToolVch7
									 null,									//String ucfPlanToolVch8
									 null,									//String ucfPlanToolVch9
									 null,									//String ucfPlanToolVch10
									 null,									//String ucfPlanToolVch11
									 null,									//String ucfPlanToolVch12
									 null,									//String ucfPlanToolVch13
									 null,									//String ucfPlanToolVch14
									 null,									//String ucfPlanToolVch15
									 null,									//Number ucfPlanToolNum2
									 null,									//Number ucfPlanToolNum3
									 null,									//Number ucfPlanToolNum4
									 null,									//Number ucfPlanToolNum5
									 null,									//Date ucfPlanToolDate2
									 null,									//Date ucfPlanToolDate3
									 null,									//Date ucfPlanToolDate4
									 null, 									//Date ucfPlanToolDate5
									 null,									//String ucfPlanToolFlag2
									 null,									//String ucfPlanToolFlag3
									 null,									//String ucfPlanToolFlag4
									 null,									//String ucfPlanToolFlag5
									 null,									//String ucfPlanToolVch2551
									 null,									//String ucfPlanToolVch2552
									 null,									//String ucfPlanToolVch2553
									 null,									//String ucfPlanToolVch40001
									 null,									//String ucfPlanToolVch40002
									 block_type,							//String blockType
									 null,									//Number displayLineNo
									 SoluminaConstants.N,					//String overUsedFlag
									 null,									//String toolId
									 null,									//Number dropDispLineNo
									 null,									//String bomCompToolId
									 null,									//String bomLineNumber
									 null);									//String bomLineId
	
				IOperationDataCollection.checkDifferentOrientation(new_plan_id,    	   	 					//String planId
							   									   plan.getNewOperKey(), 					//Number operationKey
																   plan.getNewStepKey(), 					//Number stepKey
																   ref_id,			   	 					//String referenceId
																   null,			   	 					//String excludedOrientationFlag
																   null,			   	 					//String excludedPrimaryKey1
																   null,			   	 					//String excludedPrimaryKey2
																   SoluminaConstants.TOOL_DATA_COLLECTION); //String calledFrom
			} catch (Exception e) {
				//defect 1928
				String errMessage = "Failed to insert tool_no=\"" + toolNumber + "\"";
				SoluminaMigrationDaoPW.insertErrorLogG8("INSERT_STEP_TOOL", MigrationVariablesPW.FAIL, plan.getSrcPlanId(),
						MigrationVariablesPW.PLAN_UPDT_NO, plan.getOperNo(), plan.getStepNo(),
						MigrationVariablesPW.NA_ERR, 0, errMessage, MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), null, plan.getPartNo());
				//end defect 1928
			}
		}
		try {
			this.savePlanObject(plan, objParamMap, location);
		} catch (Exception e) {
			this.logPlanObjectFail(plan, MigrationVariablesPW.TOOL, block_type, "save", e);
		}
	} // end insertToolXML()


	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  BUYOFFS INSERT
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@SuppressWarnings("rawtypes")
	private void insertBuyoffXmlMulti(PlanObjectParams plan, BuyOffBlock bob, EContentLocation location) {

		String udvId = "MF1_1000529";

		String new_plan_id = plan.getNewPlanId();
		String oper_no = plan.getOperNo();
		String step_no = plan.getStepNo();

		Map objParamMap = getPlanObjectParams(MigrationVariablesPW.BUYOFF, udvId, null, location);
		String block_type = (String) objParamMap.get("BLOCK_TYPE");
		String block_id = (String) objParamMap.get("BLOCK_ID");
		String ref_id = (String) objParamMap.get("REF_ID");

		for (BuyOff bo : bob.getBuyOff()) {

			String buyoffType = bo.getType();
			String buyoffCert = bo.getCert();
			String buyoff_title = this.getBuyoffTitle(buyoffType);

			this.autoInsertBuyoffCert(plan, buyoffCert);

			try {
				IStep.insertStepBuyoff(new_plan_id,							//String planId
									   MigrationVariablesPW.NEW_PLAN_VER,	//Number planVersion
									   MigrationVariablesPW.NEW_PLAN_REV,	//Number planRevision
									   MigrationVariablesPW.NEW_PLAN_ALT,	//Number planAlterations
									   oper_no, 							//String operationNumber
									   step_no, 							//String stepNumber
									   ref_id, 								//String referenceId
									   buyoffType, 							//String buyoffType
									   buyoffCert,							//String buyoffCertification
									   MigrationVariablesPW.LOWEST_NAV_LVL,	//String lowestNavigationLevel
									   SoluminaConstants.N,					//String optionalFlag
									   SoluminaConstants.N, 				//String crossOrderFlag
									   block_id,							//String blockId
									   null, 								//String ucfStepBuyoffVch1
									   null, 								//String ucfStepBuyoffVch2
									   null,  								//String ucfStepBuyoffVch3
									   null,  								//String ucfStepBuyoffVch4
									   null,  								//String ucfStepBuyoffVch5
									   null,  								//String ucfStepBuyoffVch6
									   null,  								//String ucfStepBuyoffVch7
									   null,  								//String ucfStepBuyoffVch8
									   null,  								//String ucfStepBuyoffVch9
									   null,  								//String ucfStepBuyoffVch10
									   null,  								//String ucfStepBuyoffVch11
									   null,  								//String ucfStepBuyoffVch12
									   null,  								//String ucfStepBuyoffVch13
									   null,  								//String ucfStepBuyoffVch14
									   null,  								//String ucfStepBuyoffVch15
									   null,  								//Number ucfStepBuyoffNum1
									   null,  								//Number ucfStepBuyoffNum2
									   null,  								//Number ucfStepBuyoffNum3
									   null,  								//Number ucfStepBuyoffNum4
									   null,  								//Number ucfStepBuyoffNum5
									   null,  								//Date ucfStepBuyoffDate1
									   null,  								//Date ucfStepBuyoffDate2
									   null,  								//Date ucfStepBuyoffDate3
									   null,  								//Date ucfStepBuyoffDate4
									   null,  								//Date ucfStepBuyoffDate5
									   null,  								//String ucfStepBuyoffFlag1
									   null,  								//String ucfStepBuyoffFlag2
									   null,  								//String ucfStepBuyoffFlag3
									   null,  								//String ucfStepBuyoffFlag4
									   null,  								//String ucfStepBuyoffFlag5
									   null,  								//String ucfStepBuyoffVch2551
									   null,  								//String ucfStepBuyoffVch2552
									   null,  								//String ucfStepBuyoffVch2553
									   null,  								//String ucfStepBuyoffVch40001
									   null,  								//String ucfStepBuyoffVch40002
									   buyoff_title,	    				//String buyoffTitle
									   null);			    				//Number displayLineNo -- Added for R2 upgrade

			} catch (Exception e) {
				this.logPlanObjectFail(plan, MigrationVariablesPW.BUYOFF, block_type, "insert", e);
			}
		} // end for(BuyOff bo : bob.getBuyOff())

		IOperationDataCollection.checkDifferentOrientation(new_plan_id,    	   	 	  //String planId
														   plan.getNewOperKey(), 	  //Number operationKey
														   plan.getNewStepKey(), 	  //Number stepKey
														   ref_id,			   	 	  //String referenceId
														   null,			   	 	  //String excludedOrientationFlag
														   null,			   	 	  //String excludedPrimaryKey1
														   null,			   	 	  //String excludedPrimaryKey2
														   SoluminaConstants.BUYOFF); //String calledFrom

		try {
			this.savePlanObject(plan, objParamMap, location);
		} catch (Exception e) {
			if(DEBUG){
				e.printStackTrace();
			}
			this.logPlanObjectFail(plan, MigrationVariablesPW.BUYOFF, block_type, "save", e);
		}

		SoluminaMigrationDaoPW.updLastActionBuyoff(new_plan_id);

		/* *************************************************************
		 * 			Insert "Code" at bottom of buyoff block
		 * 			---------------------------------------
		 * NOTE: Even if several buyoffs have codes, the loop below
		 * 		 only retains and makes use of the last one
		 * *************************************************************/
		String code = null;

		for (BuyOff bo : bob.getBuyOff()) {
			if (bo.getCode() != null && !bo.getCode().isEmpty()) {
				code = String.format("[Code %s]", bo.getCode());
			}
		};

		if (code != null) {

			RichText rt = new RichText();
			rt.getContent().add(code);

			String plain_text = renderTxt(rt, "");
			String rich_text = renderRichText("text", rt, "", plan);

			this.insertTextXML(plan, plain_text, rich_text, location);
		}
	} // end insertBuyoffXmlMulti()


	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  DATA COLLECTION INSERT
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @SuppressWarnings("rawtypes")
	private void insertDataCollectionXmlMulti(PlanObjectParams plan, DataCollectionBlock dcb, EContentLocation location) {

		String udvId = "MF1_1000540";

		String src_plan_id = plan.getSrcPlanId();
		String new_plan_id = plan.getNewPlanId();
		String oper_no = plan.getOperNo();
		String step_no = plan.getStepNo();

		Map objParamMap = getPlanObjectParams(MigrationVariablesPW.DATA_COLLECTION, udvId, null, location);
		String block_type = (String) objParamMap.get("BLOCK_TYPE");
		String block_id = (String) objParamMap.get("BLOCK_ID");
		String ref_id = (String) objParamMap.get("REF_ID");
		
		//defect 1803
		String optionalFlag = SoluminaConstants.N;
		List oper_list = SoluminaMigrationDaoPW.queryOperations(src_plan_id);
		ListIterator listIterator = oper_list.listIterator();
		while(listIterator.hasNext()){
			Map map = (Map) listIterator.next();
			if(map.get("OPER_NO").equals(oper_no)){
				String oper_title = (String) map.get("OPER_TITLE");
				if(oper_title.contains("FPI")){
					optionalFlag = SoluminaConstants.Y;
					break;
				}
			}
		}
		
		//check operation title
		
    	for (DataCollection dc : dcb.getDataCollection()) {
			try {
				
				//defect 1659
				String datColUom = dc.getUnits();
				//Query SFFND_STD_DATCOL_TYPE_DEF

				List list = SoluminaMigrationDaoPW.queryDataCollectionTypeData(dc.getType());
				ListIterator dataColTypeIter = list.listIterator();
				//Code validates if there is a valid data collection type later and will log appropriate error.
				
				boolean uomFound = false;
				boolean allRecordsObsolete = true;
				while (dataColTypeIter.hasNext()) {
					
					Map dataColTypeInfo = (Map) dataColTypeIter.next();
					String uom = (String) dataColTypeInfo.get("UOM");
					String obsoleteFlag = (String) dataColTypeInfo.get("OBSOLETE_RECORD_FLAG");
					String selectableFlag = (String) dataColTypeInfo.get("SELECTABLE_DC_FLAG");
					
					if(obsoleteFlag.equals("N")){ //Need to ensure that UOM values are identical
						allRecordsObsolete = false;
						if (selectableFlag.equals("Y")){
							if(!uomFound){
								datColUom = uom;
								uomFound = true;
								dc.setType((String) dataColTypeInfo.get("DAT_COL_TYPE"));
							}else{
								//log error indicating multiple records were returned for this data collection type and use original G5 UOM value for this insert.
								String errorMsg = "Data type \"" + dc.getType() + "\" has ambigous UOM values (Multiple records returned by SFFND_STD_DATCOL_TYPE_DEF.). Using original UOM value.";
								SoluminaMigrationDaoPW.insertErrorLogG8("INSERT_STEP_TOOL", MigrationVariablesPW.FAIL, plan.getSrcPlanId(),
										MigrationVariablesPW.PLAN_UPDT_NO, plan.getOperNo(), plan.getStepNo(),
										MigrationVariablesPW.NA_ERR, 0, errorMsg, MigrationVariablesPW.SFMFG, null, null, plan.getPartNo());
								datColUom = dc.getUnits();
								break;
							}
						}				
					}
				}
				//log warnings
				if(allRecordsObsolete){
					//log that all records were obsolete.
					String errorMsg = "Data type \"" + dc.getType() + "\" was found to have obsoleted record(s) in SFFND_STD_DATCOL_TYPE_DEF.";
					
					SoluminaMigrationDaoPW.insertErrorLogG8("INSERT_DATA_COLLECTION", MigrationVariablesPW.FAIL, plan.getSrcPlanId(),
							MigrationVariablesPW.PLAN_UPDT_NO, plan.getOperNo(), plan.getStepNo(),
							MigrationVariablesPW.NA_ERR, 0, errorMsg, MigrationVariablesPW.SFMFG, null, null, plan.getPartNo());
					
				}
				//end defect 1659
				
				String std_dat_col_id = SoluminaMigrationDaoPW.getStdDatColIdFromType(dc.getType());
				//defect 1636
				//Need to determine if title contains an optional keyword
				Pattern optionalPattern = Pattern.compile("[ (](opt)(ional)?", Pattern.CASE_INSENSITIVE); //Can match OPT or OPTIONAL but must contain a whitespace or left parenthesis to delimit from the rest of the title
				Matcher m = optionalPattern.matcher(dc.getName());
				if(m.find()){
					optionalFlag = SoluminaConstants.Y;
				}
				//end defect 1636
				
				//defect 1872
				Number numericDecimalDigits = null;
				Pattern integerPattern = Pattern.compile("\\d");
				Matcher integerMatcher = integerPattern.matcher(dc.getUnits());
				//Extract decimal from units and set empty limit/target values to null
				//Solumina OOB code does not cleanly handle empty String values, setting to null if empty.
				if(integerMatcher.find()){
					numericDecimalDigits = Integer.parseInt(integerMatcher.group());
				}
				if(dc.getLowerLimit().isEmpty()){
					dc.setLowerLimit(null);
				}
				if(dc.getUpperLimit().isEmpty()){
					dc.setUpperLimit(null);
				}
				if(dc.getTargetValue().isEmpty()){
					dc.setTargetValue(null);
				}
				
				//defect 1872, Item 14
				String overInspectionReqdFlag;
				if(plan.getPWP().equals("NBRO")){
					overInspectionReqdFlag = SoluminaConstants.Y;
				}
				else{
					overInspectionReqdFlag = SoluminaConstants.N;
				}
				//end defect 1872
				IStep.insertUpdateStepDCPre(new_plan_id, 		   					//String planId
											MigrationVariablesPW.NEW_PLAN_VER,		//Number planVersion
											MigrationVariablesPW.NEW_PLAN_REV,  	//Number planRevision
											MigrationVariablesPW.NEW_PLAN_ALT,		//Number planAlterations
											oper_no,			   					//String operNo
											step_no,			   					//String stepNo
											ref_id,				   					//String refId
											null,				   					//String datColId
											null,				   					//String datColLimitId
											std_dat_col_id,		   					//String stdDatColId
											dc.getCert(),		   					//String datColCert
											MigrationVariablesPW.LOWEST_NAV_LVL,	//String lowNavLevel
											null,				   					//Number operKey //TODO: 2018-09-18 These values ok?
											null,				   					//Number stepKey
											null,				   					//Number stepUpdtNo
											datColUom,		   						//String datColUom
											dc.getName(),		   					//String datColTitle
											dc.getUpperLimit(),	   					//String upperLimit
											dc.getLowerLimit(),						//String lowerLimit
											dc.getTargetValue(),					//String targetValue
											SoluminaConstants.N,					//String orientationFlag
											SoluminaConstants.N,					//String crossOrderFlag
											optionalFlag,							//String optionalFlag
											block_id,			   					//String blockId
											SoluminaConstants.N,					//String calculatedDataCollectionFlag
											null,				   					//String variableName
											null,				   					//String visibility
											numericDecimalDigits,  					//Number numericDecimalDigits
											null,				   					//String ucfStepDcVch1
											null,				   					//String ucfStepDcVch2
											null,				   					//String ucfStepDcVch3
											null,				   					//String ucfStepDcVch4
											null,				   					//String ucfStepDcVch5
											null,				   					//String ucfStepDcVch6
											null,				   					//String ucfStepDcVch7
											null,				   					//String ucfStepDcVch8
											null,				   					//String ucfStepDcVch9
											null,				   					//String ucfStepDcVch10
											null,				   					//String ucfStepDcVch11
											null,				   					//String ucfStepDcVch12
											null,				   					//String ucfStepDcVch13
											null,				   					//String ucfStepDcVch14
											null,				   					//String ucfStepDcVch15
											null,				   					//Number ucfStepDcNum1
											null,				   					//Number ucfStepDcNum2
											null,				   					//Number ucfStepDcNum3
											null,				   					//Number ucfStepDcNum4
											null,				   					//Number ucfStepDcNum5
											null,				   					//Date ucfStepDcDate1
											null,				   					//Date ucfStepDcDate2
											null,				   					//Date ucfStepDcDate3
											null,				   					//Date ucfStepDcDate4
											null,				   					//Date ucfStepDcDate5
											null,				   					//String ucfStepDcFlag1
											null,				   					//String ucfStepDcFlag2
											null,				   					//String ucfStepDcFlag3
											null,				   					//String ucfStepDcFlag4
											null,				   					//String ucfStepDcFlag5
											null,				   					//String ucfStepDcVch2551
											null,				   					//String ucfStepDcVch2552
											null,				   					//String ucfStepDcVch2553
											null,				   					//String ucfStepDcVch40001
											null,				   					//String ucfStepDcVch40002
											null,				   					//Number displayLineNo
											overInspectionReqdFlag,					//String overInspectionReqdFlag; defect 1872
											null,				   					//String resultId
											MigrationVariablesPW.INSERT,			//String calledFrom
											null,				   					//String fileName
											null,				   					//byte[] fileData
											null);				   					//Number dropDispLineNo

			} catch (Exception e) {

				boolean dcTypeNotFound = e.toString().contains("EmptyResultDataAccessException");

				if(dcTypeNotFound) {
					if(DEBUG){
						e.printStackTrace();
					}
					String errorMsg = "Data type \"" + dc.getType() + "\" with UOM of \"" + dc.getUnits() + "\" not found in SFFND_STD_DATCOL_TYPE_DEF " +
									  "(Check not obsolete).\n" + e.toString();
					
					SoluminaMigrationDaoPW.insertErrorLogG8("INSERT_DATA_COLLECTION", MigrationVariablesPW.FAIL, plan.getSrcPlanId(),
							MigrationVariablesPW.PLAN_UPDT_NO, plan.getOperNo(), plan.getStepNo(),
							MigrationVariablesPW.NA_ERR, 0, errorMsg, MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), null, plan.getPartNo());
				}
				else {
					if(DEBUG){
						e.printStackTrace();
					}
					this.logPlanObjectFail(plan, MigrationVariablesPW.DATA_COLLECTION, block_type, "insert", e);
				}
			}
		} // end for(DataCollection dc : dcb.getDataCollection())

		IOperationDataCollection.checkDifferentOrientation(new_plan_id,    	   	 			   //String planId
														   plan.getNewOperKey(), 			   //Number operationKey
														   plan.getNewStepKey(), 			   //Number stepKey
														   ref_id,			   	 			   //String referenceId,
														   null,			   	 			   //String excludedOrientationFlag
														   null,			   	 			   //String excludedPrimaryKey1
														   null,			   	 			   //String excludedPrimaryKey2
														   SoluminaConstants.DATA_COLLECTION); //String calledFrom

		try {
			this.savePlanObject(plan, objParamMap, location);
		} catch (Exception e) {
			this.logPlanObjectFail(plan, MigrationVariablesPW.DATA_COLLECTION, block_type, "save", e);
		}

		SoluminaMigrationDaoPW.updLastActionDC(new_plan_id);

	} // end insertDataCollectionXmlMulti()


    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  GRAPHICS INSERT
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

  //defect 1835
  	@SuppressWarnings("rawtypes")
  	private void insertGraphicsXML(PlanObjectParams plan, String src_object_id, EContentLocation location) {

  		String udvId = "MFI_B5AADED3AC164051B5A40B9249045E42";
  		Map objParamMap = getPlanObjectParams(MigrationVariablesPW.GRAPHIC, udvId, src_object_id, null, location);
  		String block_type = (String) objParamMap.get("BLOCK_TYPE");
		String objectPWPId = "";
  		try {
  			List slideObjectInfo = SoluminaMigrationDaoPW.getMMObjectInfo(src_object_id);
			if (slideObjectInfo!=null && !slideObjectInfo.isEmpty()){
				Map map = (Map) slideObjectInfo.get(0);
				//defect 2000
				objectPWPId = (String) map.get("PWP_ID");
				//update previous Slide object records to COMPLETE
				if(!SoluminaConstants.COMPLETE.toUpperCase().equals((String) map.get("STATUS"))){
					SoluminaMigrationDaoPW.updateMmSlideObjectStatusToComplete(src_object_id, (String) map.get("OBJECT_TYPE"));
				}
				//defect 1972
				//update previous record's security group only if it's null
				if(StringUtils.isEmpty((String) map.get("SECURITY_GROUP"))){
					SoluminaMigrationDaoPW.setMmObjectSecurityGroup(src_object_id, plan.getLocation());
				}
				//end defect 2000
			}
  			//if PWP_ID don't match, log an error
		  	if(plan.getPWP().equals(objectPWPId) || StringUtils.isEmpty(objectPWPId)){
	
		  		//Process step no String
	  			String step_no = plan.getStepNo();
	  			if(step_no.equals("...")){
	  				step_no = location.name();
	  			} 
	  			
	  			//check if source object id exists in db
	  			if(slideObjectInfo.isEmpty()){
			  		IMultiMediaObject.insertMultiMediaObject("SFMFG",							//String userId
								 null,		 	 					//String folderId
								 src_object_id, 					//String objectId
								 "SLIDE",	 	 					//String objectType
								 src_object_id, 					//String objectTag
								 SoluminaConstants.ONE_STRING,		//String objectRevision
								 null,		 	 					//String objectDescription
								 null,		 						//String blockType
								 null,	 	 	 					//String format
								 null,		 	 					//Number ownerGroup
								 null, 		 	 					//String objectReference
								 null,		 	 					//String objectParameters
								 null,		 	 					//BinaryParameter textData
								 null,		 	 					//BinaryParameter binaryData
								 null,		 	 					//BinaryParameter thumbnail
								 SoluminaConstants.OTHER_MIXCASE,	//String classification
								 null,		 	 					//String category
								 null);		 	 					//String status
			  		
		
		  			//now to insert and update all embedded images
		 			ListIterator embeddedImageIterator = SoluminaMigrationDaoPW.queryEmbeddedImages(src_object_id).listIterator();
		  			while(embeddedImageIterator.hasNext()){
		  		  		Map embeddedImage = (Map) embeddedImageIterator.next();
		  				
		  				String embedded_object_id = (String) embeddedImage.get("G5_OBJECT_ID");
		  				String objectType = (String) embeddedImage.get("OBJECT_TYPE");
		  				String g5ref_id =  (String) embeddedImage.get("G5_REF_ID");
		  	  			String embeddedObjectPWPId = "";
		  	  			
		  				List embeddedObjectInfo = SoluminaMigrationDaoPW.getMMObjectInfo(embedded_object_id);
		  				if (embeddedObjectInfo!=null && !embeddedObjectInfo.isEmpty()){
		  					Map map = (Map) embeddedObjectInfo.get(0);
		  					embeddedObjectPWPId = (String) map.get("PWP_ID");				
		  				}
		  				//raise an error if PWP_IDs do not match
		  				if(!plan.getPWP().equals(embeddedObjectPWPId) && !StringUtils.isEmpty(embeddedObjectPWPId)){
		  	 				String message = objectType + " Object ID of " + embedded_object_id + " already exists within another PWP ID";
		  	 				Exception e = new Exception(message);
		  		 			throw e;
		  				}
		  				if(embeddedObjectInfo.isEmpty()){
			  				//create the record with empty binary data.
			  		  		IMultiMediaObject.insertMultiMediaObject("SFMFG",							//String userId
									 null,		 	 					//String folderId
									 embedded_object_id, 					//String objectId
									 objectType,	 	 					//String objectType
									 embedded_object_id, 					//String objectTag
									 SoluminaConstants.ONE_STRING,		//String objectRevision
									 null,		 	 					//String objectDescription
									 null,		 						//String blockType
									 null,	 	 	 					//String format
									 null,		 	 					//Number ownerGroup
									 null, 		 	 					//String objectReference
									 null,		 	 					//String objectParameters
									 null,		 	 					//BinaryParameter textData
									 null,		 	 					//BinaryParameter binaryData
									 null,		 	 					//BinaryParameter thumbnail
									 SoluminaConstants.OTHER_MIXCASE,	//String classification
									 null,		 	 					//String category
									 null);		 	 					//String status
			  			
					  				
				  			//Create the reference link between the slide object and the image object in SFCORE_MM_HTREF
							SoluminaMigrationDaoPW.call_sfcore_mm_obj_add_embd_obj("SFMFG",	 	   //String updt_userid
																				   src_object_id, //String object_id
																				   embedded_object_id,   //String embedded_object_id
																				   objectType,		   //String embedded_object_type
																				   null,	 	   //String block_id
																				   null,	 	   //String url_prefix
																				   null,	 	   //String url_params
																				   null,	 	   //String url_suffix
																				   g5ref_id);   //String ref_id
							//previous call creates a ref id, update to used g5 ref id
							SoluminaMigrationDaoPW.updateToG5RefId(src_object_id, embedded_object_id, g5ref_id);
		  				}
		  			}
		  		}  			
  				//this ref id associates the slide object with the operation. It is in no way related to embedded images
	  			String ref_id = (String) objParamMap.get("REF_ID");
	  			
	  			//Insert the slide object into the plan operation
	  			ISlide.insertMMObjectsToPlanOperation(plan.getNewPlanId(),					//String planId
	  												  MigrationVariablesPW.NEW_PLAN_VER,	//Number planVersion
	  												  MigrationVariablesPW.NEW_PLAN_REV,	//Number planRevision
	  												  MigrationVariablesPW.NEW_PLAN_ALT,	//Number planAlterations
	  												  plan.getOperNo(),						//String operationNumber
	  												  plan.getStepNo(),						//String stepNumber
	  												  plan.getNewOperKey(), 				//Number operationKey
	  												  plan.getNewStepKey(), 				//Number stepKey
	  												  src_object_id,	   					//String objectId
	  												  ref_id,	   		   					//String refId
	  												  null,				   					//String blockId
	  												  plan.getPartNo(), 					//String partNumber
	  												  MigrationVariablesPW.NA_OOB,			//String partChange
	  												  SoluminaConstants.PART,				//String itemType
	  												  plan.getPlanType(),		   			//String itemSubtype
	  												  plan.getEngineType(),					//String program
	  												  MigrationVariablesPW.LOWEST_NAV_LVL);	//String lowestNavigationLevel

				SoluminaMigrationDaoPW.updateMmObject(src_object_id, src_object_id /*defect 1991*/, plan.getOperNo(), step_no, plan.getPWP(), "SLIDE", null, plan.getLocation()/*defect 1972*/); 	  	
	 			ListIterator embeddedImageIterator = SoluminaMigrationDaoPW.queryEmbeddedImages(src_object_id).listIterator();
	  			while(embeddedImageIterator.hasNext()){
	  		  		Map embeddedImage = (Map) embeddedImageIterator.next();
	  				
	  				String embedded_object_id = (String) embeddedImage.get("G5_OBJECT_ID");
	  				String objectType = (String) embeddedImage.get("OBJECT_TYPE");
	  				String g5ref_id =  (String) embeddedImage.get("G5_REF_ID");
	  				
	  				SoluminaMigrationDaoPW.updateMmObject(embedded_object_id, src_object_id /*defect 1991*/, plan.getOperNo(), step_no, plan.getPWP(), objectType, g5ref_id, plan.getLocation()/*defect 1972*/);
					SoluminaMigrationDaoPW.updateToG5RefId(src_object_id, embedded_object_id, g5ref_id);
	  			}
	  			  	  			
 			try {
  				this.savePlanObject(plan, objParamMap, location);
  			} catch (Exception e) {
  				this.logPlanObjectFail(plan, MigrationVariablesPW.GRAPHIC, block_type, "save", e);
  				}
 			}
		  	else{
			String message = "Slide Object ID of " + src_object_id + " already exists with another PWP ID. Plan PWP ID: " + plan.getPWP() + "Object PWP ID: " + objectPWPId;
			Exception e = new Exception(message);
 			throw e;
		}
		  	} catch (Exception e) {
  			this.logPlanObjectFail(plan, MigrationVariablesPW.GRAPHIC, block_type, "insert", e);
  		}				
  	} // end insertGraphicsXML()
  //end defect 1912 Graphic Hotfix


	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  SUPPORT METHODS
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	private boolean validateWorkCenter(PlanObjectParams plan, String oper_no,
									   String plnd_work_dept, String plnd_work_center) {

		String plnd_work_loc = plan.getLocation();

		boolean validCenter = true;
		boolean workCenterExists = SoluminaMigrationDaoPW.validateWorkCenter(plnd_work_loc,
											 				 				 plnd_work_dept,
											 				 				 plnd_work_center);
		if((plnd_work_center == null) || (!workCenterExists)) {

			validCenter = false;
			String errorText;

			if(plnd_work_center == null) {
				errorText = "Work center not defined: part_no=" + plan.getPartNo();

			} else {
				errorText = "Work loc=" + plnd_work_loc + ", dept=" + plnd_work_dept + ", cntr=" +
							plnd_work_center + " not found for part_no=" + plan.getPartNo();
			}
			errorText += ".\nOperation and related oper-subjects skipped.";

			SoluminaMigrationDaoPW.insertErrorLogG8("MISSING_WRK_CNTR", MigrationVariablesPW.FAIL, plan.getSrcPlanId(),
													MigrationVariablesPW.PLAN_UPDT_NO, oper_no, MigrationVariablesPW.NA_ERR,
													MigrationVariablesPW.NA_ERR, 0, errorText, MigrationVariablesPW.SFMFG, null, null, plan.getPartNo());
		}
		return validCenter;
	}

	private String getBuyoffTitle(String type) {
		
		//defect 1346
		String title = null;
		List buyoffTitleList = SoluminaMigrationDaoPW.queryBuyoffDataDesc(type);
		ListIterator operIter = buyoffTitleList.listIterator();
		
		while (operIter.hasNext()) {
			Map operInfo = (Map) operIter.next();
			title = (String) operInfo.get("DATA_DESC");
		}
		//End defect 1346 
		if (title == null) {
			title = "Unknown Buyoff Type";
		}
		return title;
	}

	private void autoInsertBuyoffCert(PlanObjectParams plan, String buyoffCert) {

		boolean buyoffCertExists = SoluminaMigrationDaoPW.doesBuyoffCertExist(buyoffCert);

		//Auto-insert non-existent buyoff certifications
		if (!buyoffCertExists && !this.isBlank(buyoffCert)) {

			try { //TODO: Confirm hard-coded QA certType ok for all sites
				SoluminaMigrationDaoPW.insertBuyoffCert(buyoffCert, "QA");

			} catch (Exception e) {

				String errorText = "Buyoff cert \"" + buyoffCert + "\" failed auto-insertion: part_no=" +
                				   plan.getPartNo();

                SoluminaMigrationDaoPW.insertErrorLogG8("CERT_AUTO_INS", MigrationVariablesPW.FAIL, plan.getSrcPlanId(),
                		MigrationVariablesPW.PLAN_UPDT_NO, plan.getOperNo(), plan.getStepNo(), MigrationVariablesPW.NA_ERR,
                		0, errorText, MigrationVariablesPW.SFMFG, convertStackTraceToG8ErrText(e), null, plan.getPartNo());
			}
		} //end if "cert non-existent"
	}

	private boolean isBlank(String str) {

		int strLen = 0;

		if (str == null || ((strLen = str.length()) == 0) || str.equalsIgnoreCase("null")) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

//defect 1912 Graphic Hotfix
	private Map<String,String> getPlanObjectParams(String planObjType, String url_prefix, String object_id, String ref_id,
			   EContentLocation location) {
	Map<String, String> objParamMap = new HashMap<String, String>();
	
	String block_id = null;
	String block_type = null;
	String text_type = null;
	String url_params = null;
	String block = null;
	String object_type = null;
	String object_name = null;
	
	if (location.equals(EContentLocation.PLAN)) { //Defect 1269
		block_id = SoluminaConstants.PLAN_TEXT; //PlanText
		block_type = null;
		text_type = SoluminaConstants.PLANNING;
	} else if (location.equals(EContentLocation.HEAD)) {
		block_id = SoluminaConstants.PLAN_OPER_TEXT; //PlanOperText
		block_type = SoluminaConstants.HEADER;
		text_type = SoluminaConstants.HEADER_PLANNING;
	} else if (location.equals(EContentLocation.FOOT)) {
		block_id = SoluminaConstants.PLAN_OPER_TEXT; //PlanOperText
		block_type = SoluminaConstants.FOOTER;
		text_type = SoluminaConstants.PLANNING;
	} else if (location.equals(EContentLocation.STEP)) {
		block_id = SoluminaConstants.PLAN_STEP_TEXT; //PlanStepText
		block_type = null;
		text_type = SoluminaConstants.PLANNING;
	}
	
	//Standard Text uses SFCORE_MM_OBJECT.OBJECT_ID as its ref_id
	if(!MigrationVariablesPW.STDTEXT.equals(planObjType)) {
		ref_id = SoluminaMigrationDaoPW.getNextRefId();
	}
	
	if(MigrationVariablesPW.TEXT.equals(planObjType) || MigrationVariablesPW.STDTEXT.equals(planObjType)/* || MigrationVariablesPW.GRAPHIC.equals(planObjType)*/) {
		
		object_type = SoluminaConstants.TEXT_OBJECTS; //"TEXTOBJECTS"
		object_name = SoluminaConstants.TEXT_OBJECT; //"TextObject"
		
		url_params = "TextObject(@SQL_ID=@Inline,OBJECT_ID=" + ref_id + ",REF_ID=" + ref_id + ")";
		
		if(MigrationVariablesPW.TEXT.equals(planObjType)) {
			block = "<UT=\"Embed(" + url_params + ")\">";
		}
		else if(MigrationVariablesPW.STDTEXT.equals(planObjType)){
			block = "\"<UT=\"Embed(Reference Text(@SQL_ID=@Config,OBJECT_ID=" + ref_id +
			",LATEST_REV_FLAG=Y,REF_ID=" + ref_id + "))\">";
		}
	}
	
	else { //object type = PART; TOOL; BUYOFF; DATA COLLECTION (DC); GRAPHIC
	
		object_type = "UDV";
		object_name = SoluminaConstants.UDV; //"Udv"
		url_params = "@CONTROL=" + ref_id + ",REF_ID=" + ref_id;
		if(MigrationVariablesPW.GRAPHIC.equals(planObjType)){
			url_params = "OBJECT_ID=" + object_id + ",REF_ID=" + ref_id;
			block = "<UT=\"LinkInvoke(Slide(" + url_params + ",@GlyphName=@InlineObject,@Classification=Other,@RenderDescText=False))\">";
		}
		else{
			url_params = "@CONTROL=" + ref_id + ",REF_ID=" + ref_id;
			block = "<IMG \"" + url_prefix + "(" + url_params + ").@UDV\">";
		}
	}
	
	objParamMap.put("BLOCK_TYPE", block_type);
	objParamMap.put("TEXT_TYPE", text_type);
	objParamMap.put("BLOCK_ID", block_id);
	objParamMap.put("REF_ID", ref_id);
	objParamMap.put("URL_PREFIX", url_prefix);
	objParamMap.put("URL_PARAMS", url_params);
	objParamMap.put("BLOCK", block);
	objParamMap.put("OBJECT_TYPE", object_type);
	objParamMap.put("OBJECT_NAME", object_name);
	
	return objParamMap;
	}
	//end defect 1912 Graphic Hotfix
	
	
	private Map<String,String> getPlanObjectParams(String planObjType, String url_prefix, String ref_id,
												   EContentLocation location) {
		Map<String, String> objParamMap = new HashMap<String, String>();

		String block_id = null;
    	String block_type = null;
		String text_type = null;
		String url_params = null;
		String block = null;
		String object_type = null;
		String object_name = null;

		if (location.equals(EContentLocation.PLAN)) { //Defect 1269
			block_id = SoluminaConstants.PLAN_TEXT; //PlanText
			block_type = null;
			text_type = SoluminaConstants.PLANNING;
		} else if (location.equals(EContentLocation.HEAD)) {
			block_id = SoluminaConstants.PLAN_OPER_TEXT; //PlanOperText
			block_type = SoluminaConstants.HEADER;
			text_type = SoluminaConstants.HEADER_PLANNING;
		} else if (location.equals(EContentLocation.FOOT)) {
			block_id = SoluminaConstants.PLAN_OPER_TEXT; //PlanOperText
			block_type = SoluminaConstants.FOOTER;
			text_type = SoluminaConstants.PLANNING;
		} else if (location.equals(EContentLocation.STEP)) {
			block_id = SoluminaConstants.PLAN_STEP_TEXT; //PlanStepText
			block_type = null;
			text_type = SoluminaConstants.PLANNING;
		}

		//Standard Text uses SFCORE_MM_OBJECT.OBJECT_ID as its ref_id
		if(!MigrationVariablesPW.STDTEXT.equals(planObjType)) {
			ref_id = SoluminaMigrationDaoPW.getNextRefId();
		}

		if(MigrationVariablesPW.TEXT.equals(planObjType) || MigrationVariablesPW.STDTEXT.equals(planObjType)/* || MigrationVariablesPW.GRAPHIC.equals(planObjType)*/) {

			object_type = SoluminaConstants.TEXT_OBJECTS; //"TEXTOBJECTS"
			object_name = SoluminaConstants.TEXT_OBJECT; //"TextObject"

			url_params = "TextObject(@SQL_ID=@Inline,OBJECT_ID=" + ref_id + ",REF_ID=" + ref_id + ")";

			if(MigrationVariablesPW.TEXT.equals(planObjType)) {
				block = "<UT=\"Embed(" + url_params + ")\">";
			}
			else if(MigrationVariablesPW.STDTEXT.equals(planObjType)){
				block = "<UT=\"Embed(Reference Text(@SQL_ID=@Config,OBJECT_ID=" + ref_id + //defect 1956
						",LATEST_REV_FLAG=Y,REF_ID=" + ref_id + "))\">";
			}
		}

		else { //object type = PART; TOOL; BUYOFF; DATA COLLECTION (DC); GRAPHIC

			object_type = "UDV";
			object_name = SoluminaConstants.UDV; //"Udv"
			url_params = "@CONTROL=" + ref_id + ",REF_ID=" + ref_id;
			block = "<IMG \"" + url_prefix + "(" + url_params + ").@UDV\">";
		}

    	objParamMap.put("BLOCK_TYPE", block_type);
    	objParamMap.put("TEXT_TYPE", text_type);
    	objParamMap.put("BLOCK_ID", block_id);
    	objParamMap.put("REF_ID", ref_id);
    	objParamMap.put("URL_PREFIX", url_prefix);
    	objParamMap.put("URL_PARAMS", url_params);
    	objParamMap.put("BLOCK", block);
    	objParamMap.put("OBJECT_TYPE", object_type);
    	objParamMap.put("OBJECT_NAME", object_name);

        return objParamMap;
    }

    @SuppressWarnings("rawtypes")
	private void savePlanObject(PlanObjectParams plan, Map objParamMap, EContentLocation location) {

    	String new_plan_id = plan.getNewPlanId();
		Number new_oper_key = plan.getNewOperKey();
		Number new_step_key = plan.getNewStepKey();

		String block_type = (String) objParamMap.get("BLOCK_TYPE");
		String text_type = (String) objParamMap.get("TEXT_TYPE");
		String block_id = (String) objParamMap.get("BLOCK_ID");
		String ref_id = (String) objParamMap.get("REF_ID");
		String url_prefix = (String) objParamMap.get("URL_PREFIX");
		String url_params = (String) objParamMap.get("URL_PARAMS");
		String block = (String) objParamMap.get("BLOCK");
		String object_type = (String) objParamMap.get("OBJECT_TYPE");
		String object_name = (String) objParamMap.get("OBJECT_NAME");

		//Defect 1269 - Added if/else to distinguish plan header text vs operation text
		if (location.equals(EContentLocation.PLAN)) {
			
			IPlan.insertPlanText(plan.getNewPlanId(),					//String planId,
					 			 plan.getPartNo(),						//String partNumber,
					 			 MigrationVariablesPW.NA_OOB,			//String partChange,
					 			 SoluminaConstants.PART,				//String itemType,
					 			 plan.getPlanType(),					//String itemSubtype,
					 			 plan.getEngineType(),					//String program,
					 			 MigrationVariablesPW.NEW_PLAN_VER,		//Number planVersion,
					 			 MigrationVariablesPW.NEW_PLAN_REV,		//Number planRevision,
					 			 MigrationVariablesPW.NEW_PLAN_ALT,		//Number planAlterations,
					 			 MigrationVariablesPW.LOWEST_NAV_LVL,	//String lowestNavigationLevel,
					 			 null,									//OutputParameter planUpdateNumberOutput,
					 			 block,									//String text,
					 			 SoluminaConstants.Y);					//String textUpdate

		} else { //operation...

			List textInfo = (location.equals(EContentLocation.STEP)) ?
							IStepDao.selectStepText(new_plan_id, new_oper_key, new_step_key, MigrationVariablesPW.STEP_UPDT_NO) :
							IOperationDao.selectOperationText(new_plan_id, new_oper_key, MigrationVariablesPW.OPER_UPDT_NO);
			//defect 2000
			/* Solumina uses the same Step No (...) and Step Key for header and
			 * footer entries. It determines where text should be written to by
			 *  it's text type
			 * 
			 * Also note that Solumina considers Headers and Footers to be 
			 * located in an operation, NOT in a Step.
			 */
			String text = "";
			if(location.equals(EContentLocation.HEAD) || location.equals(EContentLocation.FOOT)){
				/* textInfo typically will store Header in index 0 and 
				 * Footer in index 1 but the code below does not make this 
				 * assumption and searches explicitly for TEXT_TYPE.
				 */
				for(int i = 0; i<textInfo.size(); i++){
					Map textMap = (Map) textInfo.get(i);
					if(location.equals(EContentLocation.HEAD) && textMap.get("TEXT_TYPE").equals(SoluminaConstants.HEADER_PLANNING)){
						text = (String) textMap.get("TEXT");
					}
					else if(location.equals(EContentLocation.FOOT) && textMap.get("TEXT_TYPE").equals(SoluminaConstants.PLANNING)){
						text = (String) textMap.get("TEXT");
					}
				}
			}
			//end defect 2000
			//Default process to get previous oper/step text and append new object
			else{
				Map textMap = (Map) textInfo.get(0);
				text = (String) textMap.get("TEXT");
			}
			text = this.isBlank(text) ? block : text + "\n" + block;
	
			if (location.equals(EContentLocation.STEP)) {
	
				IStep.insertUpdateStepText(new_plan_id,							//String planId,
										   MigrationVariablesPW.NEW_PLAN_VER,	//Number planVersion,
										   MigrationVariablesPW.NEW_PLAN_REV,	//Number planRevision,
										   MigrationVariablesPW.NEW_PLAN_ALT,	//Number planAlterations,
										   plan.getOperNo(),					//String operationNumber,
										   plan.getStepNo(),					//String stepNumber,
										   MigrationVariablesPW.LOWEST_NAV_LVL,	//String lowestNavigationLevel,
										   text,								//String text,
										   SoluminaConstants.Y,					//String textUpdate,
										   MigrationVariablesPW.INSERT,			//String transactionType,
										   text_type);							//String textType
			} else {
	
				IOperation.insertUpdateOperationText(new_plan_id,  							//String planId,
													 MigrationVariablesPW.NEW_PLAN_VER,		//Number planVersion,
													 MigrationVariablesPW.NEW_PLAN_REV,		//Number planRevision,
													 MigrationVariablesPW.NEW_PLAN_ALT,		//Number planAlterations,
													 plan.getOperNo(),						//String operationNumber,
													 MigrationVariablesPW.LOWEST_NAV_LVL,	//String lowestNavigationLevel,
						 							 text, 		   							//String text,
						 							 SoluminaConstants.Y,					//String textUpdate,
						 							 MigrationVariablesPW.INSERT,			//String transactionType,
						 							 text_type,								//String textType,
						 							 false,									//boolean isSfor,
						 							 block_type);							//String blockType		
			}
		} //end if (!location.equals(EContentLocation.PLAN))

		IHtReferenceText.deleteAllHyperTextReferences(new_plan_id,							//String planId,
													  MigrationVariablesPW.PLAN_UPDT_NO,	//Number planUpdateNumber,
				  									  new_oper_key,							//Number operationKey,
				  									  MigrationVariablesPW.OPER_UPDT_NO,	//Number operationUpdateNumber,
				  									  new_step_key,							//Number stepKey,
				  									  MigrationVariablesPW.STEP_UPDT_NO,	//Number stepUpdateNumber,
				  									  null,									//String blockId,
				  									  null,									//String orderId,
				  									  null,									//String discrepancyId,
				  									  null,									//Number discrepancyLineNumber,
				  									  null, 								//String correctiveActionId,
				  									  null,									//String nodeId,
				  									  null,									//String resolutionId,
				  									  null,									//String requestId,
				  									  block_type,							//String blockType,
				  									  text_type);							//String textType

		IHtReferenceText.updateHtReferenceText(ref_id,					  			//String referenceId,
											   ContextUtil.getUsername(), 			//String updateUserId,
											   null,					  			//String parentReferenceId,
											   null,					  			//String sourceReferenceId,
											   ref_id,					  			//String objectId,
											   object_type,			  	  			//String objectType,
											   object_name,			  	  			//String objectName,
											   block_id, 				  			//String blockId,
											   url_prefix,				  			//String urlPrefix,
											   url_params,				  			//String urlParameters,
											   SoluminaConstants.NOTAPPLICABLE,		//String urlSuffix,
											   new_plan_id,				  			//String planId,
											   MigrationVariablesPW.NEW_PLAN_VER,	//Number planVersion,
											   MigrationVariablesPW.NEW_PLAN_REV,	//Number planRevision,
											   MigrationVariablesPW.NEW_PLAN_ALT,	//Number planAlterations,
											   new_oper_key,			  			//Number operationKey,
											   MigrationVariablesPW.OPER_UPDT_NO,	//Number operationUpdateNumber,
											   new_step_key,			  			//Number stepKey,
											   MigrationVariablesPW.STEP_UPDT_NO,	//Number stepUpdateNumber,
											   null,					  			//String orderId,
											   null,					  			//String discrepancyId,
											   null,					  			//Number discrepancyLineNumber,
											   null,					  			//String correctiveActionId,
											   null,					  			//String nodeId,
											   null,					  			//String resolutionId,
											   null,					  			//String requestId,
											   null,					  			//String communicationId,
											   block_type,				  			//String blockType,
											   text_type);				  			//String tempTextType

		IHtReferenceText.reconcileHtReferenceText(new_plan_id,							//String planId,
												  MigrationVariablesPW.NEW_PLAN_VER,	//Number planVersion,
												  MigrationVariablesPW.NEW_PLAN_REV,	//Number planRevision,
												  MigrationVariablesPW.NEW_PLAN_ALT,	//Number planAlterations,
												  MigrationVariablesPW.PLAN_UPDT_NO,	//Number planUpdateNumber,
												  new_oper_key,							//Number operationKey,
												  MigrationVariablesPW.OPER_UPDT_NO,	//Number operationUpdateNumber,
												  new_step_key,							//Number stepKey,
												  MigrationVariablesPW.STEP_UPDT_NO,	//Number stepUpdateNumber,
												  text_type,							//String textType,
												  block_id,								//String blockId,
												  null,									//String orderId,
												  null,									//String discrepancyId,
												  null,									//Number discrepancyLineNumber,
												  null,									//String correctiveActionId,
												  null,									//String nodeId,
												  null,									//String resolutionId,
												  null,									//String requestId,
												  null);								//String communicationId
	} //end savePlanObject()

    private void logPlanObjectFail(PlanObjectParams plan, String planObjType, String block_type,
			   String action, Exception e /*defect 1928b*/) {

		String position = block_type == null ? "STEP" : block_type;
	
		//defect 1928
		//initialize default values to log to error table
		String type = planObjType + "_" + position;
		String err_id = MigrationVariablesPW.FAIL;	
		String src_plan_id = plan.getSrcPlanId();
		Number plan_updt_no = MigrationVariablesPW.PLAN_UPDT_NO;
		String oper_no = plan.getOperNo();
		String step_no = plan.getStepNo();
		String tool_no = MigrationVariablesPW.NA_ERR;
		int error_code = 0;
		String errorText = "Error on " + planObjType + " " + action + ": plan_name="
		+ plan.getPartNo() + " oper_no=" + oper_no + " step_no = " + step_no;
		String userid = MigrationVariablesPW.SFMFG;
		String stack_trace = convertStackTraceToG8ErrText(e);
		Number task_no = null;
		String plan_item_no = plan.getPartNo();
		String sysErrMsg = convertStackTraceToG8ErrText(e);
		
		switch (planObjType) {
		
		case MigrationVariablesPW.TOOL:
		Pattern p = Pattern.compile("tool_no=\"([^\"]*)\"");
		Matcher m = p.matcher(e.getMessage());
		if(m.find()){
			tool_no = m.group(1);
			//check for EmptyResultDataAccessException, this indicates the tool wasn't found in G8
			if(e instanceof EmptyResultDataAccessException){
				//remove the tag at the beginning of the string and replace it with an descriptive error message
				String userMessage = "Tool \"" + tool_no + "\" was not found in SFFND_TOOL.";
				sysErrMsg = sysErrMsg.replace(m.group(), userMessage);
			}
		}
		break;
		
		default:
		
		break;
		}
		

		SoluminaMigrationDaoPW.insertErrorLogG8(type, err_id, src_plan_id,
									plan_updt_no, oper_no, step_no,
									tool_no, error_code, errorText, userid, stack_trace, task_no, plan_item_no);
		//end defect 1928
	}

	
	private enum EContentLocation {
		PLAN, //Defect 1269
		HEAD,
		STEP,
		FOOT
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//
	//  TEXT-SPECIFIC SUPPORT METHODS
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	/* *********************************************************************
	 * NOTE: Expected (type, value) for different text objects:
	 * 		 --------------------------------------------------------
	 * 		 PLAIN TEXT:	RichText,		!MigrationVariablesPW.RTF
	 *		 OPTION SETS:	(Str or RT),	MigrationVariablesPW.RTF
	 *		 RICH TEXT:		RichText,		MigrationVariablesPW.RTF
	 * *********************************************************************/
	private String renderTxt(Object txtContent, String txtFormat) {

		StringBuilder strBldr = new StringBuilder();

		//Append "text format" as a prefix if non-RTF value 
		if(!MigrationVariablesPW.RTF.equals(txtFormat)) {
			strBldr.append(txtFormat); 
		}

		if (txtContent instanceof RichText) {

			RichText rt = (RichText) txtContent;

			for (Serializable s: rt.getContent()) {
				//Defect 1872, item 6
				s = s.toString().replaceAll("\u00C2", "");
				if (s instanceof JAXBElement<?>) {

					JAXBElement<?> e = (JAXBElement<?>) s;
					String name = e.getName().getLocalPart();

					switch (name) {

					case "lid":
						strBldr.append(String.format("(LID#%s)", e.getValue()));
						break;

					case "link":
						Link link = (Link)e.getValue();

						if(link.getPrefix() != null) {
							strBldr.append(String.format("%s ", link.getPrefix()));
						}

						if(MigrationVariablesPW.RTF.equals(txtFormat)) { //rich text
							strBldr.append(String.format("{\\field{\\*\\fldinst HYPERLINK \"%s\"}{\\fldrslt{\\ul\\cf2 %s}}}",
														 link.getUrl(), link.getValue()));
						} else { //plain text
							strBldr.append(String.format("%s [%s]", link.getValue(),link.getUrl()));
						}
						break;
					} //end switch

				} else { //not an instance of JAXBElement
					strBldr.append((MigrationVariablesPW.RTF.equals(txtFormat)) ?
									rtfSafe(s.toString()) : s.toString());
				}
			} //end for(Serializable)

		} else { //text not an instance of RichText
			strBldr.append((MigrationVariablesPW.RTF.equals(txtFormat)) ?
							rtfSafe(txtContent.toString()) : txtContent.toString());
		}

		return strBldr.toString();

	} //end renderTxt()

	private String renderRichText(String txtType, RichText txtContent, String prefix, PlanObjectParams plan) {

		String rtf = renderTxt(txtContent, MigrationVariablesPW.RTF);
		return renderRichText(txtType, rtf, prefix, plan);	
	}

	private String renderRichText(String txtType, String rtf, String prefix, PlanObjectParams plan) {

		StringBuilder strBldr = new StringBuilder();
		SoluminaFont font = getFont(txtType, plan);

		//Only add text block if there is content
		if (!rtf.isEmpty()) {

			String modifiers = "\\fs" + (2*font.size) + "\\cf1";

			if (font.italic) modifiers += "\\i";
			if (font.bold) modifiers += "\\b";
			if (font.underline) modifiers += "\\ul";
			if (font.opaque) modifiers += "\\cbpat3";
			if (!modifiers.isEmpty()) modifiers += " ";

			String colorTable = String.format("{\\colortbl;%s%s%s}",
											  rtfColor(font.textColor),
											  rtfColor(font.linkColor),
											  rtfColor(font.backgroundColor));   

			strBldr.append(String.format("{\\rtf1\\ansi\\deff0 {\\fonttbl\\f0 %s;}%s%s%s%s}\n",
								  		 font.font, colorTable, modifiers, rtfSafe(prefix), rtf));
		}
		return strBldr.toString();

	} // end renderRichText()

	private String encodeRichTextXML(String text) {

		String richText = "";

		if (!this.isBlank(text)) {

			text += "\n";
			char[] hexString = Hex.encodeHex(text.getBytes());
			
			richText = String.valueOf(hexString);

//			for (int i = 0; i < hexString.length; i++) {
//				richText += hexString[i];
//			}
			
			//Defect 1065 - Begin rich text auto-size workaround
			String hexNewLine = "5c6c696e65";	// "\line"
			String hexEndCurlBracket = "7d"; 	// "}"

			richText = StringUtils.substringBeforeLast(richText, hexEndCurlBracket)
					   + hexNewLine + hexEndCurlBracket; //Add a new line to end of rich text
			//Defect 1065 - End rich text auto-size workaround
			
			richText = "<UT=\"RtfEditControl(Text=" + richText + ",@Width=auto,@Height=auto)\">";
		}
		return richText;
	}

	private String rtfSafe(String in) {
		String out = in.replaceAll("\\r?\\n", "\\\\line ").replaceAll("\\t", "\\\\tab ");
		return out;
	}

	private String rtfColor(Color color) {
		return String.format("\\red%d\\green%d\\blue%d;", color.getRed(), color.getGreen(), color.getBlue());
	}

	//Determines font for differing text block types
	//Need to pass in the plan
	private SoluminaFont getFont(String txtType, PlanObjectParams plan) {

		SoluminaFont font = new SoluminaFont(); //Instantiate default font. Update per case...
		//Defect 1872, item #11
		if(plan.getPWP().equals("NBRO")){
			font.size = 16;
		}//end Defect 1872, item #11
		switch (txtType) {

		case "text":
			break;

		case "note":
			if (plan.getPWP() != null && !plan.getPWP().contains("ESA")) { //Defect 1254
				font.textColor = Color.BLUE;
				font.italic = true;
			}
			break;

		case "warning":
			font.textColor = Color.RED;
			font.opaque = true;
			break;

		case "caution":
			font.backgroundColor = Color.YELLOW;
			font.opaque = true;
			break;
		}
		return font;
	}

	//Default font for text block inserts
	private class SoluminaFont {

		public String font = "Tahoma";
		public int size = 10;
		public boolean bold = false;
		public boolean italic= false;
		public boolean underline = false;
		public Color textColor = Color.BLACK;
		public Color linkColor = Color.BLUE;
		public boolean opaque = false;
		public Color backgroundColor = Color.WHITE;
	}

} // end SoluminaMigrationPW Class
