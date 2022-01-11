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
 *  File:    ItemLegacyImplPW.java
 * 
 *  Created: 2017-08-25
 * 
 *  Author:  xcs4331
 * 
 *  Revision History
 * 
 *  Date      	Who                	Description
 *  ----------	---------------	---------------	---------------------------------------------------
 * 2017-08-25 		xcs4331		    Initial Release XXXXXXX
 * 2017-11-09		N.Gnassounou    Defect 192 When creating new Plan Item No do not allow special Char in the PART NO field
 * 2017-11-15		N.Gnassounou    Defect 192 Update Defect to handle Part No if it contain Apostrophe
 * 2018-01-05        X002174         Defect 243 SMRO_PLG_207. Removed Plan No. Created Successfully message from Standard Operation.
 * 2018-02-22        X002174         Defect 333 Removed Plan No. Created Successfully message from Tools.
 * 2018-04-05		B. Preston		PW_SMRO_EXPT_203 Defect 365 Added preInsert.
 * 2018-04-23		B. Preston		PW_SMRO_EXPT_203 Defect 365 Added call to updateSecurity in insertItemDesc.
 * 2018-05-01		B. Preston		PW_SMRO_EXPT_203 Defect 365 Save ASGND_WORK_LOC.
 * 2018-05-02		B. Preston		PW_SMRO_EXPT_203 Defect 365 Reset ASGND_WORK_LOC.
 * 2018-05-07		B. Preston		PW_SMRO_EXPT_203 Defect 365 Only set ASGND_WORK_LOC if updateSecurity returns 0.
 * 2018-07-05		D. Miron		PW_SMRO_EXPT_203 Defect 876 Changed insertItemDesc to insert work locations for both the user entered and "ANY".
 * 2018-07-16		B. Preston		PW_SMRO_EXPT_203 Defect 896 Only insertItemDesc once if STDOPER.
 * 2018-08-14		B. Preston		PW_SMRO_EXPT_203 Defect 933 Only insertItemDesc once.  Fix Update Build Item.
 * 2019-02-28        T. Phan         SCR SMRO_PLG_207-001 Added repairUpdate for new field
 * 2019-03-01        X002174         Switched Security Group record from a field to a tab – Defect 1135
 * 2019-05-21		B. Preston		SoluminaOOB Defect 1207 Use part's work location.
 * 2019-07-26		B. Preston		PW_SMRO_EXPT_203 Defect 1272.  Added back defect 876.
 * 2019-08-23		B. Polak		M309 tooling interface exception for defect 192 logic 
 * 2019-12-03        T. Phan         Defect 1421: Added placeholder value to bypass work location check
 * 2019-12-16        J. DeNinno      Defect 1421: There was a second call to Super.InsertItemDesc which caused a problem that I commented out
 * 2020-01-16        T. Phan         Defect 1421: Upon seeing this is a duplicate, I just deleted the line.
 * 2020-03-13        J. DeNinno      Defect 1598: Add change to set the Serial Flag or Lot Flag
 * 2020-05-29       J DeNinno        Defect 1646: Work location can't be set until after the item number is saved in case the user cancels
 */

package com.pw.solumina.sfpl.application.impl;

import static com.ibaset.common.FrameworkConstants.ANY;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.ImplementationOf;
import com.ibaset.common.Reference;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.solumina.sfcore.application.IMessage;
import com.ibaset.solumina.sffnd.application.IEvent;
import com.ibaset.solumina.sfpl.application.IItem;
import com.ibaset.solumina.sfpl.dao.IItemDao;
import com.pw.solumina.sfpl.application.IItemLegacyPW;
import com.pw.solumina.sfpl.dao.IItemDaoPW;



/**
 * @author xcs4331
 *
 */
public abstract class ItemLegacyImplPW extends ImplementationOf<com.ibaset.solumina.sfpl.application.IItem> implements IItem, IItemLegacyPW {



	@Reference
	protected IMessage message = null;

	@Reference  
	private IEvent   event;	

	@Reference
	private IItemDao itemDao = null;

	@Reference
	private IItemDaoPW itemDaoPW = null;

	@Reference
	private IItemLegacyPW itemLegacyPW = null;




	// Begin Defect 365
	public void preInsert(String itemNumber,
			String itemRev,
			String itemType,
			String calledFrom,
			String workLoc) {
		int x = 1;

		Super.preInsert(itemNumber, itemRev, itemType, calledFrom);

		//defect 1646 don't insert work location here since the user might cancel, but save the location for after the item is inserted in insertitemDesc
		//		if (itemDaoPW.updateSecurity(itemNumber, workLoc) == 0) {
		ContextUtil.getUser().getContext().set("ASGND_WORK_LOC", workLoc);
		//		} else {
		//			ContextUtil.getUser().getContext().set("ASGND_WORK_LOC", "");
		//		}

	}
	// End Defect 365


	public void insertItemDesc(String itemNumber,
			String itemRev,
			String itemType,
			String itemSubtype,
			String program,
			String workLocation,
			String itemTitle,
			String stockUom,
			String workOrderSerialFlag,
			String workOrderLotFlag,
			String componentSerialFlag,
			String componentLotFlag,
			String expirationFlag,
			String spoolFlag,
			String parentEngineeringPartNumber,
			String parentEngineeringPartChange,
			String ucfItemVch1,
			String ucfItemVch2,
			String ucfItemVch3,
			String ucfItemVch4,
			String ucfItemVch5,
			String ucfItemVch6,
			String ucfItemVch7,
			String ucfItemVch8,
			String ucfItemVch9,
			String ucfItemVch10,
			String ucfItemVch11,
			String ucfItemVch12,
			String ucfItemVch13,
			String ucfItemVch14,
			String ucfItemVch15,
			Number ucfItemNumber1,
			Number ucfItemNumber2,
			Number ucfItemNumber3,
			Number ucfItemNumber4,
			Number ucfItemNumber5,
			String ucfItemFlag1,
			String ucfItemFlag2,
			String ucfItemFlag3,
			String ucfItemFlag4,
			String ucfItemFlag5,
			Date ucfItemDate1,
			Date ucfItemDate2,
			Date ucfItemDate3,
			Date ucfItemDate4,
			Date ucfItemDate5,
			String calledFrom,
			String optionalDataCollection1Flag,
			String optionalDataCollection2Flag,
			String optionalDataCollection3Flag,
			String optionalDataCollection4Flag,
			String standardPartFlag,
			String flightSafetyFlag,
			String flightEssentialFlag,
			String cotsiPAFlag,
			String sourceInspPlanId,
			String destInspPlanId,
			String manufacturingInspPlanId,
			String utilizationRule,
			String isTrackable,
			String uidEntryName,
			String uidAcquisitionCode,
			String uidItemFlag,
			String toolCalibrateDays, 
			String toolCalibrateHours,
			String toolCalibrateUses,
			Integer toolCalibrationDaysFreq,
			Integer toolCalibrationHoursFreq,
			Integer toolCalibrationUsesFreq,
			Number flightDays,
			Number flightHours,
			Number flightCycles,
			Number stdCost,
			String phantomKit, 
			String serialNoGenerator,
			String ucfItemVch2551,
			String ucfItemVch2552,
			String ucfItemVch2553,
			String ucfItemVch40001,
			String ucfItemVch40002,
			String ucfItemProgDetailVch1,
			String ucfItemProgDetailVch2,
			String ucfItemProgDetailVch3,
			String ucfItemProgDetailVch4,
			String ucfItemProgDetailVch5,
			String ucfItemProgDetailVch6,
			String ucfItemProgDetailVch7,
			String ucfItemProgDetailVch8,
			String ucfItemProgDetailVch9,
			String ucfItemProgDetailVch10,
			String ucfItemProgDetailVch11,
			String ucfItemProgDetailVch12,
			String ucfItemProgDetailVch13,
			String ucfItemProgDetailVch14,
			String ucfItemProgDetailVch15,
			Number ucfItemProgDetailNumber1,
			Number ucfItemProgDetailNumber2,
			Number ucfItemProgDetailNumber3,
			Number ucfItemProgDetailNumber4,
			Number ucfItemProgDetailNumber5,
			String ucfItemProgDetailFlag1,
			String ucfItemProgDetailFlag2,
			String ucfItemProgDetailFlag3,
			String ucfItemProgDetailFlag4,
			String ucfItemProgDetailFlag5,
			Date ucfItemProgDetailDate1,
			Date ucfItemProgDetailDate2,
			Date ucfItemProgDetailDate3,
			Date ucfItemProgDetailDate4,
			Date ucfItemProgDetailDate5,
			String ucfItemProgDetailVch2551,	 
			String ucfItemProgDetailVch2552,
			String ucfItemProgDetailVch2553,
			String ucfItemProgDetailVch40001,
			String ucfItemProgDetailVch40002,
			String buyFlag,  					
			String makeFlag,   					
			Number avgPurchasePricePerUnit,  	
			Number avgMaterialCostPerUnit, 		
			Number avgLaborCostPerUnit, 			
			String priceCostUnit, 				
			Number avgOrderLeadTimeDays, 
			String commodityJurisdiction,
			String commodityClassification, 
			String associateChange){

		//excluding defect 192 logic iff called from 309 tools interface 

		//begin defect 1598
		if (StringUtils.equals(itemSubtype, "REPAIR")) {
			if (StringUtils.equals(workOrderSerialFlag, "Serial")) {
				workOrderSerialFlag = "Y";
				workOrderLotFlag = "N";
			}
			else if (StringUtils.equals(workOrderSerialFlag, "Lot")) {
				workOrderSerialFlag = "N";
				workOrderLotFlag = "Y";
			}
		}
		else { //overhaul
			workOrderSerialFlag = "N";
			workOrderLotFlag = "N";
		}
		//end defect 1598

		if(!calledFrom.equals("PW_R_M309_TOOL"))
		{		 
			//Start Defect 192
			int apostosphe = itemNumber.indexOf("'");
			if (itemNumber.contains("!") || itemNumber.contains("@") || itemNumber.contains("#") || itemNumber.contains("$") || itemNumber.contains("^")
					|| itemNumber.contains("&") || itemNumber.contains("*") || itemNumber.contains("?") || apostosphe > 0 || apostosphe ==0)
			{
				message.raiseError("MFI_20", "Only special characters such as dashes (-) and underscores (_) are allowed in part no field" );
			}

			//End Defect 192
		}

		// Begin defect 365.
		String savedWorkLoc = (String)ContextUtil.getUser().getContext().get("ASGND_WORK_LOC");

		if (StringUtils.isNotEmpty(savedWorkLoc)) {
			workLocation = savedWorkLoc;
		}
		ContextUtil.getUser().getContext().set("ASGND_WORK_LOC", "");
		// End defect 365.


		// Start Defect 876, 1272
		Super.insertItemDesc(itemNumber,
				itemRev,
				itemType,
				itemSubtype,
				program,
				"ANY",      // workLocation,
				itemTitle,
				stockUom,
				workOrderSerialFlag,
				workOrderLotFlag,
				componentSerialFlag,
				componentLotFlag,
				expirationFlag,
				spoolFlag,
				parentEngineeringPartNumber,
				parentEngineeringPartChange,
				ucfItemVch1,
				ucfItemVch2,
				ucfItemVch3,
				ucfItemVch4,
				null,        //Defect 1135. Nullified for Security Group tab
				ucfItemVch6,
				ucfItemVch7,
				ucfItemVch8,
				ucfItemVch9,
				ucfItemVch10,
				ucfItemVch11,
				ucfItemVch12,
				ucfItemVch13,
				ucfItemVch14,
				ucfItemVch15,
				ucfItemNumber1,
				ucfItemNumber2,
				ucfItemNumber3,
				ucfItemNumber4,
				ucfItemNumber5,
				ucfItemFlag1,
				ucfItemFlag2,
				ucfItemFlag3,
				ucfItemFlag4,
				ucfItemFlag5,
				ucfItemDate1,
				ucfItemDate2,
				ucfItemDate3,
				ucfItemDate4,
				ucfItemDate5,
				calledFrom,
				optionalDataCollection1Flag,
				optionalDataCollection2Flag,
				optionalDataCollection3Flag,
				optionalDataCollection4Flag,
				standardPartFlag,
				flightSafetyFlag,
				flightEssentialFlag,
				cotsiPAFlag,
				sourceInspPlanId,
				destInspPlanId,
				manufacturingInspPlanId,
				utilizationRule,
				isTrackable,
				uidEntryName,
				uidAcquisitionCode,
				uidItemFlag,
				toolCalibrateDays, 
				toolCalibrateHours,
				toolCalibrateUses,
				toolCalibrationDaysFreq,
				toolCalibrationHoursFreq,
				toolCalibrationUsesFreq,
				flightDays,
				flightHours,
				flightCycles,
				stdCost,
				phantomKit, 
				serialNoGenerator,
				ucfItemVch2551,
				ucfItemVch2552,
				ucfItemVch2553,
				ucfItemVch40001,
				ucfItemVch40002,
				ucfItemProgDetailVch1,
				ucfItemProgDetailVch2,
				ucfItemProgDetailVch3,
				ucfItemProgDetailVch4,
				ucfItemProgDetailVch5,
				ucfItemProgDetailVch6,
				ucfItemProgDetailVch7,
				ucfItemProgDetailVch8,
				ucfItemProgDetailVch9,
				ucfItemProgDetailVch10,
				ucfItemProgDetailVch11,
				ucfItemProgDetailVch12,
				ucfItemProgDetailVch13,
				ucfItemProgDetailVch14,
				ucfItemProgDetailVch15,
				ucfItemProgDetailNumber1,
				ucfItemProgDetailNumber2,
				ucfItemProgDetailNumber3,
				ucfItemProgDetailNumber4,
				ucfItemProgDetailNumber5,
				ucfItemProgDetailFlag1,
				ucfItemProgDetailFlag2,
				ucfItemProgDetailFlag3,
				ucfItemProgDetailFlag4,
				ucfItemProgDetailFlag5,
				ucfItemProgDetailDate1,
				ucfItemProgDetailDate2,
				ucfItemProgDetailDate3,
				ucfItemProgDetailDate4,
				ucfItemProgDetailDate5,
				ucfItemProgDetailVch2551,	 
				ucfItemProgDetailVch2552,
				ucfItemProgDetailVch2553,
				ucfItemProgDetailVch40001,
				ucfItemProgDetailVch40002,
				buyFlag,  					
				makeFlag,   					
				avgPurchasePricePerUnit,  	
				avgMaterialCostPerUnit, 		
				avgLaborCostPerUnit, 			
				priceCostUnit, 				
				avgOrderLeadTimeDays, 
				commodityJurisdiction,
				commodityClassification, 
				"N");//associateChange);
		// End Defect 876, 1272

		// (Defect 1421: Deleted duplicate call of above)

		itemDaoPW.updateSecurity(itemNumber, workLocation); // Defect 365 Defect 1135: Changed parameters


		if (itemSubtype.equals("REPAIR")){
			itemDaoPW.repairUpdate(itemNumber, itemRev); // SCR SMRO_PLG_207-001
		}

		if (!(itemSubtype.equals("STDOPER")) && !(itemType.equals("TOOL"))){ //Defect 243 & Defect 333
			message.showMessage("MFI_20", "Plan Item No Created Successfully.");	 // Fred E.  This show message is not being displayed. //243
		}
	}

	// Begin Defect 933.
	/**
	 * Returns the item description information for the specified part no, part
	 * chg, and program or work location.
	 * 
	 * @param partNumber
	 * @param partChange
	 * @param program
	 * @param workLocation
	 * @return A Map of values whose keys are SFPL_ITEM_DESC_ALL column names
	 */
	public Map getItemDescriptionInformation(String partNumber,
			String partChange,
			final String program,
			final String workLocation)
	{
		// Begin defect 1207.
		String workLoc = itemDaoPW.getWorkLocation(partNumber, partChange);
		if (StringUtils.isEmpty(workLoc)) {
			workLoc = StringUtils.isEmpty(workLocation) ? "ANY" : workLocation;
		}
		// End defect 1207.

		return Super.getItemDescriptionInformation(partNumber, partChange, program, workLoc);
	}
	// End Defect 933.

}
