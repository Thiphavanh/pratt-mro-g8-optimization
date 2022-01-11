/*
 *  This unpublished work, first created in 2018 and updated thereafter,
 *  is under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2018-2021.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    MigrationHeaderVariablesPW.java
 *  
 *  SMRO_CV_202
 * 
 *  Created: 2018.10.19
 * 
 *  Author:  R Cannon
 * 
 *  Revision History
 * 
 *  Date       	 	Who          	Description
 *  -----------		------------ 	---------------------------------------------------
 *  2018-10-19		R. Cannon		SMRO_CV_202 - Initial release
 *  2019-02-14		R. Cannon		SMRO_CV_202 - Added workflow types and rich text plan object
 *  2020-04-08		S. Edgerly		SMRO_CV_302 - Defect 1623 - Parts on Repair Task Groups. Changed scope of class and variables
 *  2021-07-29		S. Edgerly		SMRO_CV_302 - Defect 1944 Conversion support for General Procedure plan types
 */
package com.pw.solumina.migration.application;

public class MigrationVariablesPW {

	//Revision Constants for New Plans
	public final static Number PLAN_UPDT_NO = 1;
	public final static Number OPER_UPDT_NO = 1;
	public final static Number STEP_UPDT_NO = 1;
	public final static Number NEW_PLAN_VER = 1;
	public final static Number NEW_PLAN_REV = 1;
	public final static Number NEW_PLAN_ALT = 0;
	public final static Number NEW_SUBJ_REV = 1;

	//Error Log Constants
	public final static String NA_ERR = "N/A";
	public final static String FAIL = "FAIL";
	public final static String WARN = "WARN";
	public final static String SFMFG = "SFMFG";

	//Method Parameter Constants
	public final static String NA_OOB = "N/A";
	public final static String OP_TEN = "0010";
	public final static String STEP_TEN = "0010";
	public final static String LOWEST_NAV_LVL = "STEP";
	public final static String INSERT = "INSERT";
	
	//Plan Workflow Types
	public final static String RPR_WORKFLOW = "REPAIR_EXPT_AUTH";
	public final static String OH_WORKFLOW = "OH_EXPT_AUTH";
	public final static String GP_WORKFLOW = "GP_EXPT_AUTH"; //defect 1944
	
	//Plan Object Types
	public final static String STDOPER = "STDOP";
	public final static String TEXT = "TEXT";
	public final static String RTF = "RTXT"; //rich text format
	public final static String STDTEXT = "STDTEXT";
	public final static String PART = "PART";
	public final static String TOOL = "TOOL";
	public final static String BUYOFF = "BUYOFF";
	public final static String DATA_COLLECTION = "DC";
	public final static String GRAPHIC = "GRAPHIC";
}
