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
 *  File:    SoluminaMigrationDaoPW.java
 *  
 *  SMRO_CV_202
 * 
 *  Created: 2017.08.09
 * 
 *  Author:  R Palmer
 * 
 *  Revision History
 * 
 *  Date       	  	Who          	Description
 *  -----------   	------------ 	---------------------------------------------------
 *  2018-03-27		R. Cannon		SMRO_CV_202 - NZ Go-Live Release
 *  2018-08-28		R. Cannon		SMRO_CV_202 - OKC Additions (StdTxt, StdOp, RichTxt and Graphics added)
 *  2018-10-10		R. Cannon		SMRO_CV_202 - Standardized plan object insertion and error logging
 *  2018-10-19		R. Cannon		SMRO_CV_202 - OKC Specific - Insert operation header to Step 10
 *  2019-02-13		R. Cannon		SMRO_CV_202 - setStdOperSecurityGroup() updated to set stdOp secGrp dynamically
 *  2019-04-01		R. Cannon		SMRO_CV_202 - Update mmObjectExists()
 *  2019-05-22		R. Cannon		Defect 1269 - ESA enhancements - added method getPlanHeaderText()
 *  2019-11-18		S. Edgerly		Defect 1422 - Resolved issue where UOM and Serial Flag we not being set correctly for Repair plans.
 *  2020-01-29		S. Edgerly		Defect 1346 - Buyoff title retrieved from PWUE_GENERAL_LOOKUP instead of hard-coded hash map.
 *  2020-02-13		S. Edgerly		Defect 1411 - Supports Repair Plans need to be run using serial numbers.
 *  2020-02-13		S. Edgerly		Defect 1412 - Supports Repair Plans need to be run using lot numbers.
 *  2020-03-19 		 S.Edgerly		SMRO_CV_301 - Defect 1383; Engine Manuals and Subject Authorities functionality added
 *  2020-04-08		S. Edgerly		SMRO_CV_302 - Defect 1623 - Parts on Repair Task Groups.
 *  2020-05-07		S.Edgerly		Solumina OOB - Defect 1096 - Conversion - Standard Operations not Updating
 *  2020-05-14		S.Edgerly		Defect 1659 - EXPT_ACCEPTANCE Error Message, UOM not matching when SELECTABLE flag is set
 *  2020-05-21		S.Edgerly		Defect 1659 - Improved query to find data collections with whitespace after "/"
 *  2020-05-26		S.Edgerly		SMRO_CV_302 - Defect 1666 Conversion Import into G8- Updateing EXTERNAL_PLM_NO with "<PART_NO>N/A"
 *  2020-07-22		S.Edgerly		SMRO_CV_302 - Defect 1741 - Engine type fix
 *  2021-02-25		S. Edgerly		Defect 1850, Item #9 - Subject Status support.
 *  2021-03-09		S. Edgerly		SMRO_CV_301 - Defect 1875 - TEC Conversion Extract from G5
 *  2021-03-18		S. Edgerly		SMRO_CV_302 - Defect 1884 - Conversion Import into G8
 *  2021-05-3       J DeNinno       Defect 1902 Task Authority tab info missing on copy plan
 *  2021-05-04		S. Edgerly		SMRO_CV_301 - Defect 1872, NBRO Conversion Enhancements
 *  2021-05-18		S. Edgerly		SMRO_CV_302 - Defect 1835 - Conversion Import into G8 - Multi-reference images
 * 2021-07-20		S. Edgerly		PW_SMRO_eWORK_201 - Defect 1912, Improved Error Logging
 *  2021-07-29		S. Edgerly		SMRO_CV_302 - Defect 1944 Conversion support for General Procedure plan types
 *  2021-08-06		S. Edgerly		SMRO_CV_201 - Defect 1958 - Supports conversion of Labor Hours Per Unit
 * 2021-08-19		S. Edgerly		PW_SMRO_eWORK_201 - Defect 1912 Graphic Hotfix 
 * 2021-09-10		S. Edgerly		PW_SMRO_eWORK_201 - Defect 1991 TOS image issues
 * 2021-09-20		S. Edgerly		PW_SMRO_eWORK_201 - Defect 1991, update 1 - Improved nested query to prevent multiple records from being returned. 
 * 2021-10-27		S. Edgerly		Defect 1972, Adding security groups to images converted in library and completing the image.
 * 2021-10-28		S. Edgerly		Defect 2000 - PWCS Image, buyoff, and DC failure investigation
 * 2021-11-02		S. Edgerly		Defect 1990 - Converting task groups even if the part isn't loaded
 * 2021-11-08		S. Edgerly		Defect 1928b - Improved Error Logging, Added support for task groups, plan name and separated technical details in ERROR_TEXT into new STACK_TRACE column.
*/
package com.pw.solumina.migration.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibaset.common.Reference;
import com.ibaset.common.SoluminaConstants;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.sql.BlobValue;
import com.ibaset.common.sql.ParameterHolder;

public class SoluminaMigrationDaoPW {
	
	@Reference private JdbcDaoSupport eds;
	// Defect 1422 *****************
		public List selectSfplItemDescMaster (String partNo, String program, String locationId){
			
			StringBuffer selectSql = new StringBuffer().append(" SELECT DISTINCT C.WO_SERIAL_FLAG, " +
	                                                                           " A.PART_NO, " +
	                                                                           " A.PART_CHG , " + 
	                                                                           " A.PART_TITLE, " + 
	                                                                           " A.ITEM_TYPE, " + 
	                                                                           " A.ITEM_SUBTYPE, " + 
	                                                                           " C.STOCK_UOM " +
	                                                          " FROM  SFPL_ITEM_DESC_MASTER_ALL A LEFT OUTER JOIN  SFSQA_INSP_PLAN_DESC B ON A.MFG_INSP_PLAN_ID = B.INSP_PLAN_ID, " +
	                                                                " SFPL_ITEM_PROGRAM_DETAILS C " +
	                                                          " WHERE  OBSOLETE_RECORD_FLAG = 'N' " +
	                                                          " AND    A.PART_FLAG = 'Y' " +
	                                                          " AND    COALESCE(A.ITEM_SUBTYPE,'X') != 'STDOPER' " +
	                                                          " AND    A.ITEM_ID = C.ITEM_ID " +
	                                                          " AND    C.PROGRAM = ? " +
	                                                          " AND    C.LOCATION_ID = ? " +
	                                                          " AND    A.PART_NO = ? ");
			
			ParameterHolder params = new ParameterHolder();
			params.addParameter(program);
			params.addParameter(locationId);
			params.addParameter(partNo);
			
			List partNoList = eds.queryForList(selectSql.toString(), params);
			return partNoList;
		}
		// end Defect 1422 ************************

	
	@SuppressWarnings("rawtypes")
	public Map getPlan(String src_plan_id) { //defect 1902 make public
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT DISTINCT T.PLAN_ID, ")
												   .append(" 		T.PART_NO, ")
												   .append(" 		T.PLAN_TITLE, ")
												   .append("		T.PLG_WORK_PACKAGE, ")
												   .append(" 		T.PLAN_TYPE, ")
												   .append(" 		T.ENGINE_TYPE, ")
												   .append(" 		T.MODULE_CODE, ")
												   .append(" 		T.CHANGE_AUTHORITY, ")
												   .append(" 		T.PLND_WORK_LOC, ")
												   .append(" 		T.EXPORT_JURISDICTION, ")
												   .append(" 		T.EXPORT_CLASSIFICATION, ")
												   .append(" 		T.BYPASS, ")
												   .append(" 		T.BYPASS_REASON, ")
												   .append("        T.SERIAL_FLAG, ") //only works in SOQMROQ2
												   .append("        T.LOT_FLAG, ")
												   .append("        T.ALTERNATIVE_PLAN_TYPE ")//defect 1944
												   .append("   FROM SFMFG.VIS_PLAN T ")
												   .append("  WHERE T.BYPASS IS NULL ")
												   .append("    AND T.PLAN_ID = ? ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(src_plan_id);

		Map planInfo = eds.queryForMap(selectSql.toString(), params);
		return planInfo;
	}
	
    public boolean partInItemMaster(String part_no)
    {
        StringBuffer selectSQL = new StringBuffer().append(" SELECT COUNT(*) ")
                                                   .append("   FROM SFPL_ITEM_DESC_MASTER_ALL ")
                                                   .append("  WHERE PART_NO = ? ")
                                                   .append("    AND PART_CHG = 'N/A' ");
        
        ParameterHolder params = new ParameterHolder();
        params.addParameter(part_no);
        
        int count = eds.queryForInt(selectSQL.toString(), params);
        return (count > 0);
    }
	
    public boolean planAlreadyExist(String part_no)
    {
        StringBuffer selectSQL = new StringBuffer().append(" SELECT COUNT(*) ")
                                                   .append("   FROM SFPL_PLAN_DESC ")
                                                   .append("  WHERE PART_NO = ? ");
        
        ParameterHolder params = new ParameterHolder();
        params.addParameter(part_no);
        
        int count = eds.queryForInt(selectSQL.toString(), params);
        return (count > 0);
    }
	
	@SuppressWarnings("rawtypes")
	Map getPlanHeaderText(String src_plan_id) {
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT PLAN_HDR_TEXT, ")
												   .append(" 		LENGTH(PLAN_HDR_TEXT) AS TEXT_LENGTH ")
												   .append("   FROM SFMFG.VIS_PLAN ")
												   .append("  WHERE PLAN_ID = ? ")
												   .append("  GROUP BY PLAN_HDR_TEXT ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(src_plan_id);

		Map hdrTextMap = eds.queryForMap(selectSql.toString(), params);
		return hdrTextMap;
	}

	@SuppressWarnings("rawtypes")
	List queryPlanEngineModels(String src_plan_id) {

		List list = null;

		try {

			StringBuffer selectSql = new StringBuffer().append(" SELECT DISTINCT T.MODEL ")
													   .append("   FROM SFMFG.VIS_PLAN_MODEL T ")
													   .append("  WHERE T.PLAN_ID = ? ");

			ParameterHolder params = new ParameterHolder();
			params.addParameter(src_plan_id);

			list = eds.queryForList(selectSql.toString(), params);

		} catch (Exception sqlE) {
			list = null;
		}
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	List querySubjects(String src_plan_id) {
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT DISTINCT T.PLAN_ID, ")
												   .append(" 				 T.SUBJECT_ID, ")
												   .append(" 				 T.SUBJECT_NO, ")
												   .append(" 				 T.SUBJECT_TITLE, ")
												   .append(" 				 T.AUTHORITY, ")
												   .append(" 				 T.AUTO_INCLUDE, ")
												   .append(" 				 T.MANDATORY, ")
												   .append(" 				 T.SUBJECT_STATUS ")
												   .append("   FROM SFMFG.VIS_SUBJECT T ")
												   .append("  WHERE T.PLAN_ID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(src_plan_id);

		List list = eds.queryForList(selectSql.toString(), params);
		return list;
	}
	
	//defect 1623
	@SuppressWarnings("rawtypes")
	List querySubjectParts(String src_plan_id, Number subjectNumber){
		StringBuffer selectSql = new StringBuffer().append(" SELECT T.PART_NO ")
												   .append("   FROM SFMFG.VIS_SUBJECT_PART T ")
												   .append("  INNER JOIN SFMFG.SFPL_ITEM_DESC_MASTER_ALL A ") //Defect 1990, only include parts that exist in G8
												   .append("     ON T.PART_NO = A.PART_NO ")
												   .append("  WHERE T.PLAN_ID = ? ")
												   .append("  	AND T.SUBJECT_NO = ? ");
	
		ParameterHolder params = new ParameterHolder();
		params.addParameter(src_plan_id);
		params.addParameter(subjectNumber);
		
		List list = eds.queryForList(selectSql.toString(), params);
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	List querySubWorkscope(String src_plan_id, String subject_id) {
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT DISTINCT T.SUBJECT_ID, ")
												   .append(" 				 T.WORKSCOPE ")
												   .append("   FROM SFMFG.VIS_SUBJECT_WORKSCOPE T ")
												   .append("  WHERE T.PLAN_ID = ? ")
												   .append("    AND T.SUBJECT_ID = ? ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(src_plan_id);
		params.addParameter(subject_id);

		List list = eds.queryForList(selectSql.toString(), params);
		return list;	
	}
	
	@SuppressWarnings("rawtypes")
	List querySuperiorNetwork(String src_plan_id, String subject_id) {
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT DISTINCT T.SUBJECT_ID, ")
												   .append(" 				 T.ACTIVITY_NUMBER ")
												   .append("   FROM SFMFG.VIS_SUBJECT_SUPERIOR_NETWORK T ")
												   .append("  WHERE T.PLAN_ID = ? ")
												   .append(" 	AND T.SUBJECT_ID = ? ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(src_plan_id);
		params.addParameter(subject_id);

		List list = eds.queryForList(selectSql.toString(), params);
		return list;		
	}
	
	@SuppressWarnings("rawtypes")
	List querySubNetwork(String src_plan_id, String subject_id, String activity_number) {
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT DISTINCT T.SUB_NET_ID, ")
												   .append(" 				 T.SUBJECT_ID, ")
												   .append(" 				 T.ACTIVITY_NUMBER, ")
												   .append(" 				 T.SUB_ACTIVITY_NUMBER ")
												   .append("   FROM SFMFG.VIS_SUBJECT_SUB_NETWORK T ")
												   .append("  WHERE T.PLAN_ID = ? ")
												   .append("    AND T.SUBJECT_ID = ? ")
												   .append(" 	AND T.ACTIVITY_NUMBER = ? ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(src_plan_id);
		params.addParameter(subject_id);
		params.addParameter(activity_number);

		List list = eds.queryForList(selectSql.toString(), params);
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	List querySubjectCustIncl(String src_plan_id) {
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT DISTINCT T.SUBJECT_ID, ")
												   .append(" 				 T.CUSTOMER, ")
												   .append(" 				 S.SUBJECT_NO ")
												   .append("   FROM SFMFG.VIS_SUBJECT_CUSTOMER_INCLUSION T,")
												   .append(" 		SFMFG.VIS_SUBJECT S ")
												   .append("  WHERE T.SUBJECT_ID = S.SUBJECT_ID ")
												   .append("    AND T.PLAN_ID = ? ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(src_plan_id);

		List list = eds.queryForList(selectSql.toString(), params);
		return list;	
	}
	
	@SuppressWarnings("rawtypes")
	List querySubjectCustExcl(String src_plan_id, String subject_id) {
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT DISTINCT T.SUBJECT_ID, ")
												   .append(" 				 T.CUSTOMER ")
												   .append("   FROM SFMFG.VIS_SUBJECT_CUSTOMER_EXCLUSION T ")
												   .append("  WHERE T.PLAN_ID = ? ")
												   .append("    AND T.SUBJECT_ID = ? ");
				
		ParameterHolder params = new ParameterHolder();
		params.addParameter(src_plan_id);
		params.addParameter(subject_id);

		List list = eds.queryForList(selectSql.toString(), params);
		return list;
	}
	
	//Defect 1346
	@SuppressWarnings("rawtypes")
	public List queryBuyoffDataDesc(String dataValue){
		
		StringBuffer selectSql = new StringBuffer().append( "SELECT DATA_DESC ")
												   .append("    FROM PWUE_GENERAL_LOOKUP ")
												   .append("   WHERE DATA_FIELD = 'BUYOFF' ")
												   .append("    AND DATA_VALUE = ? ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(dataValue);

		List list = eds.queryForList(selectSql.toString(), params);
		return list;
	}
	//end Defect 1346
	
	//Defect 1741
	public List queryEngineType(String engine_type){
		
		StringBuffer selectSql = new StringBuffer().append( "SELECT PROGRAM ")
												   .append("    FROM SFFND_PROGRAM_DEF ")
												   .append("   WHERE PROGRAM = ? ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(engine_type);

		List list = eds.queryForList(selectSql.toString(), params);
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	List queryOperations(String src_plan_id) {
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT DISTINCT T.OPER_KEY, ")
												   .append(" 				 T.OPER_TYPE, ")
												   .append(" 				 T.OPER_TITLE, ")
												   .append(" 				 T.STDOPER_TAG, ")
												   .append(" 				 T.PLAN_ID, ")
												   .append(" 				 T.OPER_NO, ")
												   .append(" 				 P.PLND_WORK_LOC, ")
												   .append(" 				 T.PLND_WORK_DEPT, ")
												   .append(" 				 T.PLND_WORK_CENTER, ")
												   .append(" 				 T.SCHED_LABOR_HOURS_PER_UNIT, ")//Defect 1958
												   .append(" 				 T.EXE_ORDER ")
												   .append("   FROM SFMFG.VIS_PLAN P, SFMFG.VIS_OPERATION T ")
												   .append("  WHERE P.PLAN_ID = T.PLAN_ID ")
												   .append("    AND T.PLAN_ID = ? ")
												   .append("  ORDER BY T.OPER_NO ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(src_plan_id);

		List list = eds.queryForList(selectSql.toString(), params);
		return list;
	}
	
	//begin defect 1902
	public List querySubjectAuthorities(String planId, Number planRevision) {
		StringBuffer selectSql = new StringBuffer().append(" SELECT ")
				   .append(" 				 a.subject_no, ")
			       .append(" 				 a.subject_rev, ")
				   .append(" 				 a.subject_description, ")
				   .append(" 				 a.authority_type, ")
				   .append(" 				 a.authority_detail, ")
				   .append(" 				 b.authority as authority_notes, ")
				   .append(" 				 a.manual_no, ")
				   .append(" 				 a.manual_rev, ")
				   .append(" 				 a.manual_provider, ")
				   .append(" 				 a.manual_type, ")
				   .append(" 				 a.manual_section, ")
				   .append(" 				 a.sb_part_no, ")
				   .append(" 				 a.authority_rev, ")
				   .append(" 				 a.authority_rev_date, ")
				   .append(" 				 a.authority_type||' '||a.manual_section||' '||a.authority_detail as arc_auth, ")
				   .append(" 				 a.updt_userid, ")
				   .append(" 				 a.time_stamp, ")
				   .append(" 				 a.last_action, ")
				   .append(" 				 null as security_group, ")
				   .append(" 				 a.SEQNO ")
				   .append(" 				 from sfmfg.PWMROI_PLAN_SUBJECT_AUTHORITY a,sfmfg.SFOR_SFPL_PLAN_SUBJECT b ,sfmfg.SFPL_PLAN_V C ")
				   .append(" 				 WHERE A.PLAN_ID = ? AND A.PLAN_REVISION = ? AND ")
				   .append(" 				 A.PLAN_ID = B.PLAN_ID AND ")
				   .append(" 				 A.PLAN_ID = C.PLAN_ID  AND ")
				   .append(" 				 A.PLAN_REVISION = C.PLAN_REVISION AND ")
				   .append(" 				 B.PLAN_ID = C.PLAN_ID AND ")
				   .append(" 				 B.PLAN_UPDT_NO = C.PLAN_UPDT_NO AND ")
				   .append(" 				 A.SUBJECT_NO = B.SUBJECT_NO ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planRevision);

		List list = eds.queryForList(selectSql.toString(), params);
		return list;
	}	//end defect 1902	
		
	//Defect 1383
	@SuppressWarnings("rawtypes")
	List querySubjectAuthorities(String src_plan_id) { 
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT DISTINCT T.SUBJ_AUTH_ID, ")
												   .append(" 				 T.PLAN_ID, ")
												   .append(" 				 T.PLAN_REVISION, ")
												   .append(" 				 T.SUBJECT_NO, ")
												   .append(" 				 T.SUBJECT_REV, ")
												   .append(" 				 T.SUBJECT_DESCRIPTION, ")
												   .append(" 				 T.AUTHORITY_TYPE, ")
												   .append(" 				 T.AUTHORITY_DETAIL, ")
												   .append(" 				 O.MANUAL_NO, ")
												   .append(" 				 O.MANUAL_REV, ")
												   .append(" 				 D.MANUAL_PROVIDER, ")
												   .append(" 				 O.MANUAL_TYPE, ")
												   .append(" 				 T.MANUAL_SECTION, ")
												   .append(" 				 T.SB_PART_NO, ")
												   .append(" 				 T.AUTHORITY_REV, ")
												   .append(" 				 T.AUTHORITY_REV_DATE, ")
												   .append(" 				 T.REVISION_CREATE_DATE, ")
												   .append(" 				 S.SUBJECT_TITLE ")
												   .append("   FROM SFMFG.VIS_SUBJECT_AUTHORITY T, SFMFG.VIS_ENGINE_MANUAL O, PWMROI_MANUAL_DESC D, SFMFG.VIS_SUBJECT S ")
												   .append("  WHERE T.PLAN_ID = ? ")
												   .append("  AND T.PLAN_ID = O.PLAN_ID ")
												   .append("  AND T.PLAN_ID = S.PLAN_ID ")
												   .append("  AND T.SUBJECT_NO = S.SUBJECT_NO ")
												   .append("  AND T.MANUAL_NO = O.MANUAL_NO ")
												   .append("  AND O.MANUAL_NO = D.MANUAL_NO ")
												   .append("  AND O.MANUAL_REV = D.MANUAL_REV ")
												   .append("  ORDER BY O.MANUAL_NO ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(src_plan_id);

		List list = eds.queryForList(selectSql.toString(), params);
		return list;
	}
//end Defect 1383
	String getOperationTitle(String src_plan_id, Number src_oper_key) {
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT T.OPER_TITLE ")
												   .append("   FROM SFMFG.VIS_OPERATION T ")
												   .append("  WHERE T.PLAN_ID = ? ")
												   .append("	AND T.OPER_KEY = ? ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(src_plan_id);
		params.addParameter(src_oper_key);

		String title;

		try {
			title = eds.queryForString(selectSql.toString(), params);
		} catch (Exception e){
			title = null;
		}
		return title;
	}

	@SuppressWarnings("rawtypes")
	List operHeaderFooterXML(String src_plan_id, Number src_oper_key) {
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT DISTINCT T.OPER_KEY, ")
												   .append(" 				 T.STDOPER_TAG, ")
												   .append(" 				 T.STEP_TYPE, ")
												   .append(" 				 T.STEP_KEY, ")
												   .append(" 				 T.STEP_NO, ")
												   .append(" 				 T.STEP_TITLE, ")
												   .append(" 				 T.TEXT ")
												   .append("  FROM SFMFG.VIS_STEP T ")
												   .append(" WHERE T.PLAN_ID = ? ")
												   .append("   AND T.OPER_KEY = ? ")
												   .append("   AND T.STEP_NO IN ('HEAD', 'FOOT') ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(src_plan_id);
		params.addParameter(src_oper_key);

		List list = eds.queryForList(selectSql.toString(), params);
		return list;
	}

	@SuppressWarnings("rawtypes")
	List queryStepsXML(String src_plan_id, Number src_oper_key) {
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT DISTINCT T.OPER_KEY, ")
												   .append(" 				 T.STDOPER_TAG, ")
												   .append(" 				 T.STEP_TYPE, ")
												   .append(" 				 T.STEP_KEY, ")
												   .append(" 				 T.STEP_NO, ")
												   .append(" 				 T.STEP_TITLE, ")
												   .append(" 				 T.TEXT ")
												   .append("   FROM SFMFG.VIS_STEP T ")
												   .append("  WHERE T.PLAN_ID = ? ")
												   .append(" 	AND T.OPER_KEY = ? ")
												   .append(" 	AND T.STEP_NO NOT IN ('HEAD','FOOT') ")
												   .append("  ORDER BY T.STEP_NO ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(src_plan_id);
		params.addParameter(src_oper_key);

		List list = eds.queryForList(selectSql.toString(), params);
		return list;
	}

	@SuppressWarnings("rawtypes")
	List queryOperationSubjects(String src_plan_id) {
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT DISTINCT S.PLAN_ID, ")
												   .append("		O.OPER_KEY, ")
												   .append("		O.OPER_NO, ")
												   .append("		O.PLND_WORK_DEPT, ")
												   .append("		O.PLND_WORK_CENTER, ")
												   .append("		S.SUBJECT_ID, ")
												   .append("		S.SUBJECT_NO, ")
												   .append("		S.SUBJECT_TITLE ")
												   .append("   FROM SFMFG.VIS_SUBJECT S, ")
												   .append("		SFMFG.VIS_OPERATION_SUBJECT T, ")
												   .append("		SFMFG.VIS_OPERATION O ")
												   .append("  WHERE S.PLAN_ID = T.PLAN_ID ")
												   .append("	AND S.SUBJECT_ID = T.SUBJECT_ID ")
												   .append("	AND T.PLAN_ID = O.PLAN_ID ")
												   .append("	AND T.OPER_KEY = O.OPER_KEY ")
												   .append("	AND T.PLAN_ID = ? ")
												   .append("  ORDER BY O.OPER_NO, S.SUBJECT_NO ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(src_plan_id);

		List list = eds.queryForList(selectSql.toString(), params);
		return list;
	}
	
	public String getNextRefId() {

		StringBuffer select = new StringBuffer().append(" SELECT SFDB_GUID FROM DUAL ");

		ParameterHolder params = new ParameterHolder();

		String ref_id = eds.queryForString(select.toString(), params);
		return ref_id;
	}
	
	public boolean doesBuyoffCertExist(String buyoffCert)
	{
		StringBuffer select = new StringBuffer().append(" SELECT COUNT(*) ")
												.append("   FROM SFFND_CERT_DEF ")
	                							.append("  WHERE CERT = ? ")
	                							.append("	 AND NVL(OBSOLETE_RECORD_FLAG, 'N') = 'N' ");        

		ParameterHolder params = new ParameterHolder();
	    params.addParameter(buyoffCert);

	    int count = eds.queryForInt(select.toString(), params);
	    return (count > 0);
	}

	public void insertBuyoffCert(String buyoffCert, String certType) {

		StringBuffer insertSql = new StringBuffer().append(" INSERT INTO SFFND_CERT_DEF ")
												   .append("		(CERT, ")
												   .append("    	 CERT_TYPE, ")
												   .append("    	 OBSOLETE_RECORD_FLAG) ")
												   .append(" VALUES ( ?, ?, ? ) ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(buyoffCert);
		params.addParameter(certType);
		params.addParameter(SoluminaConstants.NO);

		eds.insert(insertSql.toString(), params);
	}

	//defect 1666
	void updateExternal_PLM_NO(String part_no){
		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFPL_ITEM_DESC_MASTER_ALL ")
				   .append("	SET EXTERNAL_PLM_NO = ? ")
				   .append("  WHERE PART_NO = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(part_no + "N/A");
		params.addParameter(part_no);

		eds.update(updateSql.toString(), params);
	}
	//end defect 1666
	
	void updLastActionBuyoff(String new_plan_id) {

		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFPL_STEP_BUYOFF ")
												   .append("	SET LAST_ACTION = 'INSERTED' ")
												   .append("  WHERE LAST_ACTION = 'SUSPECTEDI' ")
												   .append("	AND PLAN_ID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(new_plan_id);

		eds.update(updateSql.toString(), params);
	}

	void updLastActionDC(String new_plan_id) {

		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFPL_STEP_DAT_COL ")
												   .append("	SET LAST_ACTION = 'INSERTED' ")
												   .append("  WHERE LAST_ACTION = 'SUSPECTEDI' ")
												   .append("	AND PLAN_ID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(new_plan_id);

		eds.update(updateSql.toString(), params);
	}

	/* ***********************************************************************************************************
	 *	NOTE: iBASEt code ensures only unique DC types are added to the definition table
	 *		  (Process Planning --> Library - Standard Data Collections). Search code for message_id
	 *		  "MFI_3ADCECCC4C353AAEE0440003BA041A64" within the following Java:
	 * 		  com.ibaset.solumina.sffnd.datacollectiontype.DataCollectionTypeApplication.insertDatColTypeDef()
	 * ***********************************************************************************************************/
	public String getStdDatColIdFromType(String dat_col_type) {

		StringBuffer selectSql = new StringBuffer().append(" SELECT STD_DATCOL_ID ")
												   .append("   FROM SFFND_STD_DATCOL_TYPE_DEF ")
												   .append("  WHERE DAT_COL_TYPE = ? ")
												   .append("    AND NVL(OBSOLETE_RECORD_FLAG, ?) = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(dat_col_type);
		params.addParameter(SoluminaConstants.NO);
		params.addParameter(SoluminaConstants.NO);

		String std_dat_col_id = eds.queryForString(selectSql.toString(), params);
		return std_dat_col_id;
	}	

 	public String getTaskId(String new_plan_id) {
		
		StringBuffer selectSql = new StringBuffer().append(" SELECT T.TASK_ID ")
												   .append("   FROM SFMFG.SFPL_TASK_V T ")
												   .append("  WHERE T.PLAN_ID = ? ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(new_plan_id);

		String taskId = eds.queryForString(selectSql.toString(), params);
		
		return taskId;
	}
	
 	//defect 1659
 	public List queryDataCollectionTypeData(String dataCollectionType){
 		if(dataCollectionType.contains("/")){
 			System.err.println(dataCollectionType);
 			dataCollectionType = dataCollectionType.replaceFirst("/\\s*", "/%");
 		}
 		
		StringBuffer selectSql = new StringBuffer().append(" SELECT T.STD_DATCOL_ID, ")
				   									.append(" T.DAT_COL_TYPE, ")
												   .append(" T.OBSOLETE_RECORD_FLAG, ")
												   .append(" T.UOM, ")
												   .append(" T.SELECTABLE_DC_FLAG ")
												   .append("   FROM SFFND_STD_DATCOL_TYPE_DEF T")
												   .append(" WHERE T.DAT_COL_TYPE like ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(dataCollectionType);

		
		List list = eds.queryForList(selectSql.toString(), params);
		return list;

 	}
 	//end defect 1659
	void logNewPlanId(String src_plan_id, String new_plan_id) {

		StringBuffer updateSql = new StringBuffer().append(" UPDATE VIS_PLAN ")
												   .append("	SET G8_PLAN_ID = ? ")
												   .append("  WHERE PLAN_ID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(new_plan_id);
		params.addParameter(src_plan_id);

		eds.update(updateSql.toString(), params);
	}

	void setBypassFlag(String src_plan_id, String bypass, String bypass_reason) {

		StringBuffer updateSql = new StringBuffer().append(" UPDATE VIS_PLAN ")
												   .append(" 	SET BYPASS = ?, ")
												   .append("		BYPASS_REASON = ? ")
												   .append("  WHERE PLAN_ID = ? ")
												   .append(" 	AND BYPASS IS NULL ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(bypass);
		params.addParameter(bypass_reason);
		params.addParameter(src_plan_id);	

		eds.update(updateSql.toString(), params);
	}
	
	public boolean validateWorkCenter(String location, String department, String center) {
	
	    StringBuffer selectSql = new StringBuffer().append(" SELECT COUNT(*) ")
	    										   .append("   FROM SFFND_WORK_LOC_DEF L, ")
	    										   .append(" 		 SFFND_WORK_DEPT_DEF D, ")
	    										   .append(" 		 SFFND_WORK_CENTER_DEF C ")
	    										   .append("  WHERE C.LOCATION_ID = L.LOCATION_ID ")
	    										   .append("    AND C.LOCATION_ID = D.LOCATION_ID ")
	    										   .append("    AND C.DEPARTMENT_ID = D.DEPARTMENT_ID ")
	    										   .append("    AND C.WORK_CENTER = ? ")
	    										   .append("    AND D.WORK_DEPT = ? ")
	    										   .append("    AND L.WORK_LOC = ? ")
	    										   .append("	AND NVL(C.OBSOLETE_RECORD_FLAG, ?) = ? ");        
	
	    ParameterHolder params = new ParameterHolder();
	    params.addParameter(center);
	    params.addParameter(department);
	    params.addParameter(location);
	    params.addParameter(SoluminaConstants.NO);
	    params.addParameter(SoluminaConstants.NO);
	
	    int count = eds.queryForInt(selectSql.toString(), params);
	    return (count > 0);
	}
	
	@Deprecated
	public void insertErrorLogG8(String type, String err_id, String src_plan_id, Number plan_updt_no,
			 String oper_no, String step_no, String tool_no, int error_code, String error_text,
			 String userid) {

		StringBuffer insertSql = new StringBuffer().append(" INSERT INTO VIS_MIG_ERROR_G8 ")
												   .append("    	 (TYPE, ")
												   .append("    	  ID, ")
												   .append("    	  PLAN_ID, ")
												   .append("    	  PLAN_UPDT_NO, ")
												   .append("    	  OPER_NO, ")
												   .append("    	  STEP_NO, ")
												   .append("    	  TOOL_NO, ")
												   .append("    	  ERROR_CODE, ")
												   .append("    	  ERROR_TEXT, ")
												   .append("    	  UPDT_USERID)  ")
												   .append(" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(type);
		params.addParameter(err_id);
		params.addParameter(src_plan_id);
		params.addParameter(plan_updt_no);
		params.addParameter(oper_no);
		params.addParameter(step_no);
		params.addParameter(tool_no);
		params.addParameter(error_code);
		params.addParameter(error_text);
		params.addParameter(userid);
		
		eds.insert(insertSql.toString(), params);
	
	} //end insertErrorLogG8()

	public void insertErrorLogG8(String type, String err_id, String src_plan_id, Number plan_updt_no,
			 String oper_no, String step_no, String tool_no, int error_code, String error_text,
			 String userid,
			 //defect 1928b
			 String stack_trace, 
			 Number task_no, 
			 String plan_item_no
			 /*end defect 1928b*/) {

		StringBuffer insertSql = new StringBuffer().append(" INSERT INTO VIS_MIG_ERROR_G8 ")
												   .append("    	 (TYPE, ")
												   .append("    	  ID, ")
												   .append("    	  PLAN_ID, ")
												   .append("    	  PLAN_UPDT_NO, ")
												   .append("    	  OPER_NO, ")
												   .append("    	  STEP_NO, ")
												   .append("    	  TOOL_NO, ")
												   .append("    	  ERROR_CODE, ")
												   .append("    	  ERROR_TEXT, ")
												   .append("    	  UPDT_USERID, ")
												   //defect 1928b
												   .append("    	  STACK_TRACE, ")
												   .append("    	  TASK_NO, ")
												   .append("    	  PLAN_ITEM_NO) ")
												   //end defect 1928b
												   .append(" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
		
		//ensure text can fit with constraints of database column
		if(error_text.length() > 3000){
		error_text = error_text.substring(0, 2999);
		}
		//defect 1928b
		//ensure text can fit with constraints of database column
		if(stack_trace != null){
			if(stack_trace.length() > 4000){
				stack_trace = stack_trace.substring(0, 3999);
			}
		}
		//dend defect 1928b
		ParameterHolder params = new ParameterHolder();
		params.addParameter(type);
		params.addParameter(err_id);
		params.addParameter(src_plan_id);
		params.addParameter(plan_updt_no);
		params.addParameter(oper_no);
		params.addParameter(step_no);
		params.addParameter(tool_no);
		params.addParameter(error_code);
		params.addParameter(error_text);
		params.addParameter(userid);
		//defect 1928b
		params.addParameter(stack_trace);
		params.addParameter(task_no);
		params.addParameter(plan_item_no);
		//end defect 1928b
		eds.insert(insertSql.toString(), params);
	
	} //end insertErrorLogG8()

	
	//BEGIN STANDARD TEXT RELATED
    void setStandardTextText(String object_tag) {

		StringBuffer updateSql = new StringBuffer()
				.append(" UPDATE SFCORE_MM_OBJECT A ")
				.append("	 SET A.TEXT_DATA = (SELECT T.TEXT FROM VIS_LIB_STANDARD_TEXT T ")
				.append("						 WHERE UPPER(T.STD_TEXT_TAG) = UPPER(?)) ")
				.append("  WHERE A.OBJECT_TYPE = ? ")
				.append(" 	 AND UPPER(A.OBJECT_TAG) = UPPER(?) ")
				.append(" 	 AND A.OBJECT_REV IN (SELECT MAX(B.OBJECT_REV) ")
				.append("							FROM SFCORE_MM_OBJECT B ")
				.append("						   WHERE B.OBJECT_TYPE = A.OBJECT_TYPE ")
				.append(" 							 AND UPPER(B.OBJECT_TAG) = UPPER(A.OBJECT_TAG)) ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(object_tag);
		params.addParameter(SoluminaConstants.STANDARD_TEXT);
		params.addParameter(object_tag);	

		eds.update(updateSql.toString(), params);
	}

    public String getObjectIdForObjectTag(String object_tag) {

		StringBuffer selectSql = new StringBuffer()
				.append(" SELECT T.OBJECT_ID ")
				.append("	FROM SFCORE_MM_OBJECT T")
				.append("  WHERE T.OBJECT_TYPE = ? ")
				.append(" 	 AND UPPER(T.OBJECT_TAG) = UPPER(?) ")
				.append("	 AND T.OBJECT_REV IN (SELECT MAX(TT.OBJECT_REV) ")
				.append(" 						    FROM SFCORE_MM_OBJECT TT ")
				.append(" 						   WHERE TT.OBJECT_TYPE = T.OBJECT_TYPE ")
				.append("							 AND UPPER(TT.OBJECT_TAG) = UPPER(T.OBJECT_TAG)) ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(SoluminaConstants.STANDARD_TEXT);
		params.addParameter(object_tag);
		 
		String objectId = eds.queryForString(selectSql.toString(), params);
		return objectId;
	}
	//END STANDARD TEXT RELATED
	
	//BEGIN STANDARD OPERATION RELATED
	String checkOperForStdOperTag(String src_plan_id, String oper_no) {

		StringBuffer selectSql = new StringBuffer().append(" SELECT STDOPER_TAG ")
												   .append("   FROM VIS_OPERATION T ")
												   .append("  WHERE T.PLAN_ID = ? ")
												   .append("	AND T.OPER_NO = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(src_plan_id);
		params.addParameter(oper_no);
		
		String stdoper_tag = eds.queryForString(selectSql.toString(), params);
		return stdoper_tag; 
	 }
	
	public boolean mmObjectExists(String object_tag, String object_type) {

		StringBuffer selectSql = new StringBuffer()
				.append(" SELECT COUNT(T.OBJECT_ID) ")
				.append("	FROM SFCORE_MM_OBJECT T")
				.append("  WHERE T.OBJECT_TYPE = ? ")
				.append("	 AND T.OBJECT_TAG = ? ")
				.append(" 	 AND T.OBJECT_REV IN (SELECT MAX(TT.OBJECT_REV) ")
				.append("						  	FROM SFCORE_MM_OBJECT TT ")
				.append("						   WHERE TT.OBJECT_TYPE = T.OBJECT_TYPE ")
				.append("						   	 AND TT.OBJECT_TAG = T.OBJECT_TAG) ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(object_type);	
		params.addParameter(object_tag);
		
		int count = eds.queryForInt(selectSql.toString(), params);
		return (count > 0);
	}

	String getStdOperDesc(String stdoper_tag) {

		StringBuffer selectSql = new StringBuffer().append(" SELECT STDOPER_DESC ")
				 								   .append("   FROM VIS_STANDARD_OPERATION ")
				 								   .append("  WHERE STDOPER_TAG = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(stdoper_tag);
		
		String stdoper_desc = eds.queryForString(selectSql.toString(), params);
		return stdoper_desc;
	 }
	
	@SuppressWarnings("rawtypes")
	Map getStdOperInfo(String stdoper_tag) {
		
		StringBuffer selectSql = new StringBuffer()
				.append(" SELECT DISTINCT T.STDOPER_OBJECT_ID, ")
				.append("		 T.STDOPER_PLAN_ID, ")
				.append("		 T.STDOPER_OPER_KEY, ")
				.append("		 T.STDOPER_REV ") //Defect 1096
				.append("   FROM SFFND_STDOPER T ")
				.append("  WHERE T.STDOPER_REV IN (SELECT MAX(TT.STDOPER_REV) ")
				.append("							 FROM SFFND_STDOPER TT ")
				.append("							WHERE TT.STDOPER_TAG = T.STDOPER_TAG ) ")
				.append("	 AND T.STDOPER_TAG = ? ");
		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(stdoper_tag);

		Map stdOpInfo = eds.queryForMap(selectSql.toString(), params);
		return stdOpInfo;
	}

	void setStdOperTitle(String stdoper_tag, String std_plan_id) {

		StringBuffer updateSql = new StringBuffer()
				.append(" UPDATE SFPL_OPERATION_DESC T ")
				.append("	 SET T.OPER_TITLE = (SELECT TITLE ")
				.append("						   FROM VIS_STANDARD_OPERATION ")
				.append("						  WHERE STDOPER_TAG = ? ) ")
				.append("  WHERE T.PLAN_ID = ? ");
				

		ParameterHolder params = new ParameterHolder();
		params.addParameter(stdoper_tag);	
		params.addParameter(std_plan_id);

		eds.update(updateSql.toString(), params);
	}

	//TODO: Possible replacement DAO: com.pw.solumina.sffnd.dao.impl.StandardOperationDaoPW.insertWorkLocation()?
	void setStdOperSecurityGroup(String stdoper_tag, String work_loc) {

		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFCORE_MM_OBJECT M ")
												   .append("	SET M.SECURITY_GROUP = ")
												   .append("		(SELECT X.SECURITY_GROUP ")
												   .append("		   FROM UTASGI_USER_SEC_GRP_XREF X ")
												   .append("		  WHERE X.HR_LOC = ?) ")
												   .append("  WHERE M.OBJECT_TAG = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(work_loc);
		params.addParameter(stdoper_tag);	

		eds.update(updateSql.toString(), params);
	 }

	void setOperAsStdOper(String stdoper_object_id, String new_plan_id, Number new_oper_key) {

		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFPL_OPERATION_DESC T ")
												   .append("	SET T.STDOPER_OBJECT_ID = ?, ")
												   .append("		T.LATEST_REV_FLAG = ? ")
												   .append("  WHERE T.PLAN_ID = ? ")
												   .append("	AND T.OPER_KEY = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(stdoper_object_id);
		params.addParameter(SoluminaConstants.YES);
		params.addParameter(new_plan_id);
		params.addParameter(new_oper_key);	

		eds.update(updateSql.toString(), params);
	}
	//END STANDARD OPERATION RELATED

	//Defect 1096
	void updateStdOperAsComplete(String stdoper_object_id) {
		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFFND_STDOPER T ")
				   .append("	SET T.STATUS = ? ")
				   .append("  WHERE T.STDOPER_OBJECT_ID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(SoluminaConstants.COMPLETE.toUpperCase());
		params.addParameter(stdoper_object_id);	
		
		eds.update(updateSql.toString(), params);
	}

	
	//BEGIN GRAPHIC RELATED
	//defect 1835
	List queryEmbeddedImages(String object_id) {

		StringBuffer selectSql = new StringBuffer().append(" SELECT * ")
				 								   .append("   FROM SFMFG.VIS_GRAPHIC ")
				 								   .append("  WHERE G5_PARENT_OBJECT_ID = ?")
				 								   .append("    AND OBJECT_TYPE = 'IMAGE'");


		ParameterHolder params = new ParameterHolder();
		params.addParameter(object_id);
		
		List result = eds.queryForList(selectSql.toString(), params);
		return result;
	 }


	//defect 1912 Graphic Hotfix
	List getMMObjectInfo(String object_id){
		
		StringBuffer selectSql = new StringBuffer()
				.append(" SELECT * FROM SFCORE_MM_OBJECT ")
				.append("   WHERE OBJECT_ID = ? ");

		
		ParameterHolder params = new ParameterHolder();
		params.addParameter(object_id);
		
		return eds.queryForList(selectSql.toString(), params, 1);
	}

	void updateMmObject(String src_object_id, 
						String parent_object_id /*defect 1991*/, 
						String oper_no, 
						String step_no, 
						String pwp_id, 
						String object_type, 
						String ref_id, //defect 1991, update 1
						String work_loc /*defect 1972*/) {
		//defect 2000
		updateMmObjectBinaryData(src_object_id, parent_object_id, oper_no, step_no, pwp_id, object_type, ref_id);
		updateMmObjectThumbnailData(src_object_id, parent_object_id, oper_no, step_no, pwp_id, object_type, ref_id);
		updateMmObjectPwpIdData(src_object_id, pwp_id);
		
		//migrated images should be marked as COMPLETE, not SLIDE_AUTHORING
		updateMmSlideObjectStatusToComplete(src_object_id, object_type);
		//end defect 2000
		//defect 1972
		setMmObjectSecurityGroup(object_type, work_loc);
	}

	void updateMmObjectBinaryData(String src_object_id, 
			String parent_object_id /*defect 1991*/, 
			String oper_no, 
			String step_no, 
			String pwp_id, 
			String object_type, 
			String ref_id /*defect 1991, update 1*/) {

		String sourceColumn;
		if(object_type.equals("SLIDE")){
			sourceColumn = "BINARY_DATA";
		}
		else{
			sourceColumn = "IMAGE";
		}
		//defect 1911
		String ref_id_param = " = ? ";
		if(StringUtils.isEmpty(ref_id)){
			ref_id_param = " IS NULL ";
		}

		StringBuffer updateSql = new StringBuffer()
				.append(" UPDATE SFCORE_MM_OBJECT M ")
				.append("  SET M.BINARY_DATA = (SELECT G." + sourceColumn + " ")
				.append("		     		   FROM SFMFG.VIS_GRAPHIC G ")
				.append("		     		  WHERE G.G5_OBJECT_ID = M.OBJECT_ID ")
				.append(" 					 	AND G.G5_PARENT_OBJECT_ID = ? ")
				.append("					    AND G.OPER_NO = ? ")
				.append("						AND G.STEP_NO = ? ")
				.append("						AND G.PWP_ID = ? ")
				.append("						AND G.G5_REF_ID " + ref_id_param + ") ") //defect 1991, update 1
				.append(" WHERE M.OBJECT_ID = ?	");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(parent_object_id);
		params.addParameter(oper_no);
		params.addParameter(step_no);
		params.addParameter(pwp_id);
		if(!StringUtils.isEmpty(ref_id)){
			params.addParameter(ref_id);
		}
		params.addParameter(src_object_id);
		//end defect 1991
		eds.update(updateSql.toString(), params);	
	}
	//defect 2000
	void updateMmObjectThumbnailData(String src_object_id, 
			String parent_object_id /*defect 1991*/, 
			String oper_no, 
			String step_no, 
			String pwp_id, 
			String object_type, 
			String ref_id /*defect 1991, update 1*/) {
		//defect 1911
		String ref_id_param = " = ? ";
		if(StringUtils.isEmpty(ref_id)){
			ref_id_param = " IS NULL ";
		}

		StringBuffer updateSql = new StringBuffer()
				.append(" UPDATE SFCORE_MM_OBJECT M ")
				.append("  SET M.THUMBNAIL = (SELECT G.THUMBNAIL ")
				.append("		     		   FROM SFMFG.VIS_GRAPHIC G ")
				.append("		     		  WHERE G.G5_OBJECT_ID = M.OBJECT_ID ")
				.append(" 					 	AND G.G5_PARENT_OBJECT_ID = ? ")
				.append("					    AND G.OPER_NO = ? ")
				.append("						AND G.STEP_NO = ? ")
				.append("						AND G.PWP_ID = ? ")
				.append("						AND G.G5_REF_ID " + ref_id_param + ") ") //defect 1991, update 1
				.append(" WHERE M.OBJECT_ID = ?	");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(parent_object_id);
		params.addParameter(oper_no);
		params.addParameter(step_no);
		params.addParameter(pwp_id);
		if(!StringUtils.isEmpty(ref_id)){
			params.addParameter(ref_id);
		}
		params.addParameter(src_object_id);
		//end defect 1991
		eds.update(updateSql.toString(), params);	
	}

	void updateMmObjectPwpIdData(String src_object_id, String pwp_id) {
		
		StringBuffer updateSql = new StringBuffer()
				.append(" UPDATE SFCORE_MM_OBJECT M ")
				.append("  SET M.PWP_ID = ? ")
				.append(" WHERE M.OBJECT_ID = ?	");

		ParameterHolder params = new ParameterHolder();

		params.addParameter(pwp_id);
		params.addParameter(src_object_id);

		eds.update(updateSql.toString(), params);	
	}
	//end defect 2000
	//defect 1972
	void setMmObjectSecurityGroup(String object_id, String work_loc) {

		StringBuffer updateSql = new StringBuffer().append(" UPDATE SFCORE_MM_OBJECT M ")
												   .append("	SET M.SECURITY_GROUP = ")
												   .append("		(SELECT X.SECURITY_GROUP ")
												   .append("		   FROM UTASGI_USER_SEC_GRP_XREF X ")
												   .append("		  WHERE X.HR_LOC = ?) ")
												   .append("  WHERE M.OBJECT_ID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(work_loc);
		params.addParameter(object_id);	

		eds.update(updateSql.toString(), params);
	 }

	//defect 1972
	public void updateMmSlideObjectStatusToComplete(String src_object_id, String object_type) {
		//Slide object types have only been obsereved to use status column (i.e. image types are null)
		if(object_type.equals("SLIDE")){
			StringBuffer updateSql = new StringBuffer()
					.append(" UPDATE SFCORE_MM_OBJECT M ")
					.append("  SET M.STATUS = ? ")
					.append(" WHERE M.OBJECT_ID = ?	");

			ParameterHolder params = new ParameterHolder();
			params.addParameter(SoluminaConstants.COMPLETE.toUpperCase());
			params.addParameter(src_object_id);
			
			eds.update(updateSql.toString(), params);				
		}
	}
	//end defect 1972
	void updateToG5RefId(String objectId, String embeddedObjectId, String refId){
		StringBuffer updateSql = new StringBuffer()
				.append(" UPDATE SFMFG.SFCORE_MM_HTREF H ")
				.append("		SET H.REF_ID = ? ")
				.append("  WHERE H.OBJECT_ID = ? ")
				.append("  AND H.EMBEDDED_OBJECT_ID = ? ");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(refId); 
		params.addParameter(objectId); 
		params.addParameter(embeddedObjectId); 

		eds.update(updateSql.toString(), params);		
	}
	//end defect 1912 Graphic Hotfix
	
	void call_sfcore_mm_obj_add_embd_obj(String updt_userid, String object_id,
			String embedded_object_id, String embedded_object_type, String block_id,
			String url_prefix, String url_params, String url_suffix, String ref_id) {

		String insertSql = " call SFMFG.sfcore_mm_obj_add_embd_obj(?,?,?,?,?,?,?,?,?) ";

		ParameterHolder params = new ParameterHolder();
		params.addParameter(updt_userid);
		params.addParameter(object_id);
		params.addParameter(embedded_object_id);
		params.addParameter(embedded_object_type);
		params.addParameter(block_id);
		params.addParameter(url_prefix);
		params.addParameter(url_params);
		params.addParameter(url_suffix);
		params.addParameter(ref_id);

		eds.update(insertSql, params);
	}

	void call_thumbnail_fixer() {
		try{
		String procedureCallSql = " call SFMFG.thumbnail_fixer() ";

		eds.update(procedureCallSql);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

		//END GRAPHIC RELATED

	//Defect 1850
	public void updateSubjectStatusAndAutoInclude(String plan_id, Number plan_update_no, Number subject_no, String status, String autoIncludeFlag){
		StringBuffer updateSql = new StringBuffer()
				.append("UPDATE SFOR_SFPL_PLAN_SUBJECT M ")
				.append("   SET M.SUBJECT_STATUS = ?, ")
				.append("  		M.IS_DEFAULT     = ?")
				.append(" 	WHERE M.PLAN_ID     = ? ")
				.append("    AND M.PLAN_UPDT_NO = ? ")
				.append(" 	 AND M.SUBJECT_NO   = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(status);
		params.addParameter(autoIncludeFlag);
		params.addParameter(plan_id);
		params.addParameter(plan_update_no);
		params.addParameter(subject_no);

		eds.update(updateSql.toString(), params);
		
	}
	//end defect 1850

	//defect 1875
	public void updateSubjectAttributes(String plan_id, Number plan_update_no, Number subject_no, String obsoleteRecordFlag, String status){
		StringBuffer updateSql = new StringBuffer()
				.append("UPDATE SFOR_SFPL_PLAN_SUBJECT M ")
				.append("   SET M.OBSOLETE_RECORD_FLAG = ?, ")
				.append("   	M.SUBJECT_STATUS = ? ") //defect 1872
				.append(" 	WHERE M.PLAN_ID     = ? ")
				.append("    AND M.PLAN_UPDT_NO = ? ")
				.append(" 	 AND M.SUBJECT_NO   = ?");

		ParameterHolder params = new ParameterHolder();
		params.addParameter(obsoleteRecordFlag);
		params.addParameter(status);//defect 1872
		params.addParameter(plan_id);
		params.addParameter(plan_update_no);
		params.addParameter(subject_no);

		eds.update(updateSql.toString(), params);
		//begin defect 1884
		updateSql = new StringBuffer()
				
			.append("UPDATE PWUST_SFOR_SFPL_PLAN_SUBJECT M ")
			.append("   SET M.OBSOLETE_RECORD_FLAG = ? ")
			.append(" 	WHERE M.PLAN_ID     = ? ")
			.append("    AND M.PLAN_UPDT_NO = ? ")
			.append(" 	 AND M.SUBJECT_NO   = ?");
		
		params = new ParameterHolder();
		params.addParameter(obsoleteRecordFlag);
		params.addParameter(plan_id);
		params.addParameter(plan_update_no);
		params.addParameter(subject_no);
		
		eds.update(updateSql.toString(), params);
		//end defect 1884
	}
} //end SoluminaMigrationDaoPW Class
