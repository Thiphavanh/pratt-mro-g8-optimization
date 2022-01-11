/*
 *  This unpublished work, first created in 2018 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2018 - 2021.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    PlanObjectParams.java
 *  
 *  SMRO_CV_202
 * 
 *  Created: 2018.02.12
 * 
 *  Author:  R Cannon
 * 
 *  Revision History
 * 
 *  Date       	  	Who          	Description
 *  -----------   	------------ 	---------------------------------------------------
 *  2018-03-27 	  	R. Cannon		SMRO_CV_202 - Go_Live Release
 *  2018-08-28		R. Cannon		SMRO_CV_202 - OKC Additions (StdTxt, StdOp, RichTxt and Graphics added)
 *  2018-10-10		R. Cannon		SMRO_CV_202 - Standardized plan object insertion and error logging
 *  2018-10-19		R. Cannon		SMRO_CV_202 - OKC Specific - Insert operation header to Step 10
 *  2020-02-13		S. Edgerly		Defect 1411 - Supports Repair Plans need to be run using serial numbers.
 *  2020-02-13		S. Edgerly		Defect 1412 - Supports Repair Plans need to be run using lot numbers.
 *	2020-02-21		S. Edgerly	    SMRO_CV_302 - Fixed bug if SerialFlag or LotFlag is null.
 *  2020-07-22		S.Edgerly		SMRO_CV_302 - Defect 1741 - Engine type fix  
 *  2021-07-29		S. Edgerly		SMRO_CV_302 - Defect 1944 Conversion support for General Procedure plan types
 */
package com.pw.solumina.migration.application;

import java.util.Map;

class PlanObjectParams {

    /* ****************
     * Class Variables
     * ****************/
	private String src_plan_id;
    private String new_plan_id;
	
	private String part_no;
	private String plan_title;
	private String plg_work_package;
	private String plan_type;
	private String engine_type;
	private String module_code;
	private String change_authority;
	private String plnd_work_loc;
	private String serial_flag;
	private String lot_flag;
	private String alternative_type; //defect 1944
	private Number new_oper_key;
	private String oper_no;
	private Number new_step_key;
	private String step_no;
	
    /* **************
     * Constructors
     * **************/
    PlanObjectParams(String src_plan_id, Map<String, Object> planInfo){

    	this.src_plan_id = src_plan_id;
		part_no = (String) planInfo.get("PART_NO");
		plan_title = (String) planInfo.get("PLAN_TITLE");
		plg_work_package = (String) planInfo.get("PLG_WORK_PACKAGE");
		plan_type = (String) planInfo.get("PLAN_TYPE");
		engine_type = (String) planInfo.get("ENGINE_TYPE");
		module_code = (String) planInfo.get("MODULE_CODE");
		change_authority = (String) planInfo.get("CHANGE_AUTHORITY");
		plnd_work_loc = (String) planInfo.get("PLND_WORK_LOC");
		serial_flag = (String) planInfo.get("SERIAL_FLAG");//Defect 1411 
		lot_flag = (String) planInfo.get("LOT_FLAG"); //Defect 1412
		alternative_type = (String) planInfo.get("ALTERNATIVE_PLAN_TYPE"); //Defect 1944
		
		new_plan_id = null;
		new_oper_key = null;
		oper_no = null;
		setStepParamsWithHdrFtrInfo();
    }
    
    PlanObjectParams(String new_plan_id, Number new_oper_key, String oper_no, PlanObjectParams planInfo){

    	src_plan_id = planInfo.getSrcPlanId();
		part_no = planInfo.getPartNo();
		plan_title = planInfo.getTitle();
		plg_work_package = planInfo.getPWP();
		plan_type = planInfo.getPlanType();
		engine_type = planInfo.getEngineType();
		module_code = planInfo.getModuleCode();
		change_authority = planInfo.getAuthority();
		plnd_work_loc = planInfo.getLocation();
		serial_flag = planInfo.getSerialFlag();
		lot_flag = planInfo.getLotFlag();
		
		this.new_plan_id = new_plan_id;
		this.new_oper_key = new_oper_key;
		this.oper_no = oper_no;
		setStepParamsWithHdrFtrInfo();
    }
    
    /* ************
     *   Getters
     * ************/
    String getSrcPlanId() {
    	return src_plan_id;
    }
    String getNewPlanId() {
    	return new_plan_id;
    }
    String getPartNo() {
    	return part_no;
    }
    String getTitle() {
    	return plan_title;
    }
    String getPWP() {
    	return plg_work_package;
    }
    String getPlanType() {
    	return plan_type;
    }
    //defect 1741
    void setEngineType(String engine_type) {
    	this.engine_type = engine_type;
    }
    String getEngineType() {
    	return engine_type;
    }
    String getModuleCode() {
    	return module_code;
    }
    String getAuthority() {
    	return change_authority;
    }
    String getLocation() {
    	return plnd_work_loc;
    }
    Number getNewOperKey() {
    	return new_oper_key;
    }
    String getOperNo() {
    	return oper_no;
    }
    Number getNewStepKey() {
    	return new_step_key;
    }
    String getStepNo() {
    	return step_no;
    }
    //defect 1411
    String getSerialFlag(){
    	return serial_flag;
    }//end defect 1411
    //defect 1412
    String getLotFlag(){
    	return lot_flag;
    }//end defect 1412
    
    //defect 1944
    public String getPlanAlternativeType() {
		return alternative_type;
	}

	public void setPlanAlternativeType(String alternative_type) {
		this.alternative_type = alternative_type;
	}
	//end defect 1944
	/* ************
     *   Setters
     * ************/
    void setNewPlanId(Map<String, Object> newPlanInfo) {
    	new_plan_id = (String) newPlanInfo.get("PLAN_ID");
    }
    void setNewOperKey(Number new_oper_key) {
    	this.new_oper_key = new_oper_key;
    }
    void setOperNo(String oper_no) {
    	this.oper_no = oper_no;
    }
    void setNewStepKey(Number new_step_key) {
    	this.new_step_key = new_step_key;
    }
    void setStepNo(String step_no) {
    	this.step_no = step_no;
    }

    /* ************
     *   Methods
     * ************/
    void setStepParamsWithHdrFtrInfo() {

    	new_step_key = null;
		step_no = "...";
    }
    //defect 1411
    boolean isSerialFlagSet(){ //SMRO_CV_302: supports backwards compatibility (i.e Exporter didn't set flags)
    	if(this.getSerialFlag() != null){
	    	if(this.getSerialFlag().equals("Y")){
	    		return true;
	    	}
    	}
    	return false;
    }
    //defect 1412
    boolean isLotFlagSet(){
    	if(this.getLotFlag() != null){ //SMRO_CV_302: supports backwards compatibility (i.e Exporter didn't set flags)
	    	if(this.getLotFlag().equals("Y")){
	    		return true;
	    	}
    	}
    	return false;
    }
} //end PlanObjectParams Class