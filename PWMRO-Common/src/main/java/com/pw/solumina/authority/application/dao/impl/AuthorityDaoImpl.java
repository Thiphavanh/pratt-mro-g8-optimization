package com.pw.solumina.authority.application.dao.impl;
/*
 *  This unpublished work, first created in 2019 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2019-2021.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    AuthorityDao.java
 * 
 *  Created: April 2019
 * 
 *  Author:  Marcel LeClerc (iBaset)
 * 
 *  Revision History
 * 
 *  Date        Who          	Description
 *  ----------	--------------	-----------------------------------------------------------
 *  04-02-2019  D.Miron         Initial Release PWU-116 Task Authority
 *  09-13-2019  D.Miron         Updated for PWU-116C release
 *  01-24-2020  D.Sibrian		Updated for T314 Interface messaging
 *  03-18-2020  D.Sibrian		PWU-116F Upgrade
 *  03-19-2020  S.Edgerly		SMRO_CV_301 - Defect 1383; Engine Manuals and Subject Authorities functionality added
 *  04-13-2021  J. DeNinno	    Defect 1890 Add sequence number for the primary key
 *  05-11-2021  J. DeNinno       Defect 1914 - Delete task authority when revision is deleted
*/

import java.util.Date;
import java.util.Map;
import java.util.List;

import com.ibaset.common.Reference;
import com.ibaset.common.dao.JdbcDaoSupport;
import com.ibaset.common.security.context.ContextUtil;
import com.ibaset.common.sql.ParameterHolder;
import com.ibaset.solumina.sffnd.application.IWrapUtils;
import com.pw.solumina.authority.application.dao.IAuthorityDao;

public class AuthorityDaoImpl implements IAuthorityDao {  //defect 1890


	@Reference
	private JdbcDaoSupport dao;
	
	@Reference
    private IWrapUtils wrapUtils;
	


	@Override
	public int selectProviderCount(String manualProvider)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT count(*) ")
		           .append("FROM   ")
		           .append("	PWMROI_MANUAL_PROVIDERS ")
		    	   .append("WHERE ")
		    	   .append(" MANUAL_PROVIDER = ?");
		
		  ParameterHolder params = new ParameterHolder();
		  params.addParameter(manualProvider);
		    
		 return dao.queryForInt(selectSql.toString(), params);
	}
	
	public int selectProviderUsedCount(String manualProvider)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT count(*) ")
		           .append("FROM   ")
		           .append("	PWMROI_MANUAL_DESC ")
		    	   .append("WHERE ")
		    	   .append(" MANUAL_PROVIDER = ?");
		
		  ParameterHolder params = new ParameterHolder();
		  params.addParameter(manualProvider);
		    
		 return dao.queryForInt(selectSql.toString(), params);
	}
	public void insertProvider(String manualProvider)
	{StringBuffer selectSql = new StringBuffer().append("INSERT INTO  ")
		           .append("	PWMROI_MANUAL_PROVIDERS ")
		    	   .append("(MANUAL_PROVIDER,UPDT_USERID)")
		    	   .append(" VALUES (?,?)");
	
	 ParameterHolder params = new ParameterHolder();
	  params.addParameter(manualProvider);
	  params.addParameter(ContextUtil.getUsername());
	    
	 dao.insert(selectSql.toString(), params);
		    	   
	}
	public void updateProvider(String manualProvider, String obsoleteRecordFlag)
	{StringBuffer selectSql = new StringBuffer().append("UPDATE ")
		           .append("	PWMROI_MANUAL_PROVIDERS ")
		           .append("  SET OBSOLETE_RECORD_FLAG = ?,")
		           .append("	UPDT_USERID = ?,")
		   		   .append("	LAST_ACTION = ?,")
		   		 .append("	TIME_STAMP = " + dao.getTimestampFunction())		   	
		   		   .append("  WHERE MANUAL_PROVIDER = ?");
	
	 ParameterHolder params = new ParameterHolder();
	  params.addParameter(obsoleteRecordFlag);
	  params.addParameter(ContextUtil.getUsername());
	  params.addParameter("UPDATED");
	  params.addParameter(manualProvider);
	    
	 dao.update(selectSql.toString(), params);
		    	   
	}
	
	public void deleteProvider(String manualProvider)
	{StringBuffer selectSql = new StringBuffer().append("DELETE FROM ")
		           .append("	PWMROI_MANUAL_PROVIDERS ")
		   		   .append("  WHERE MANUAL_PROVIDER = ?");
	
	 ParameterHolder params = new ParameterHolder();
	  params.addParameter(manualProvider);	    
	 dao.delete(selectSql.toString(), params);
		    	   
	}
	public int selectManualTypeCount(String manualType)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT count(*) ")
		           .append("FROM   ")
		           .append("	PWMROI_MANUAL_TYPE ")
		    	   .append("WHERE ")
		    	   .append(" MANUAL_TYPE = ?");
		
		  ParameterHolder params = new ParameterHolder();
		  params.addParameter(manualType);
		    
		 return dao.queryForInt(selectSql.toString(), params);
	}
	public void insertManualType(String manualType)
	{StringBuffer selectSql = new StringBuffer().append("INSERT INTO  ")
		           .append("	PWMROI_MANUAL_TYPE ")
		    	   .append("(MANUAL_TYPE,UPDT_USERID)")
		    	   .append(" VALUES (?,?)");
	
	 ParameterHolder params = new ParameterHolder();
	  params.addParameter(manualType);
	  params.addParameter(ContextUtil.getUsername());
	    
	 dao.insert(selectSql.toString(), params);
		    	   
	}
	public void updateManualType(String manualType, String obsoleteRecordFlag)
	{StringBuffer selectSql = new StringBuffer().append("UPDATE ")
		           .append("	PWMROI_MANUAL_TYPE ")
		           .append("  SET OBSOLETE_RECORD_FLAG = ?,")
		           .append("	UPDT_USERID = ?,")
		   		   .append("	LAST_ACTION = ?,")
		   		   .append("	TIME_STAMP = " + dao.getTimestampFunction())
		   		   .append("  WHERE MANUAL_TYPE = ?");
	
	 ParameterHolder params = new ParameterHolder();
	  params.addParameter(obsoleteRecordFlag);
	  params.addParameter(ContextUtil.getUsername());
	  params.addParameter("UPDATED");
	  params.addParameter(manualType);
	    
	 dao.update(selectSql.toString(), params);
	}
	public void deleteManualType(String manualType)
	{StringBuffer selectSql = new StringBuffer().append("DELETE FROM ")
		           .append("	PWMROI_MANUAL_TYPE ")
		   		   .append("  WHERE MANUAL_TYPE = ?");
	
	 ParameterHolder params = new ParameterHolder();
	  params.addParameter(manualType);	    
	 dao.delete(selectSql.toString(), params);
		    	   
	}
	public int selectManualTypeUsedCount(String manualType)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT count(*) ")
		           .append("FROM   ")
		           .append("	PWMROI_MANUAL_DESC ")
		    	   .append("WHERE ")
		    	   .append(" MANUAL_TYPE = ?");
		
		  ParameterHolder params = new ParameterHolder();
		  params.addParameter(manualType);
		    
		 return dao.queryForInt(selectSql.toString(), params);
	}
	public int selectManualUsedCount(String manualNo, String manualRev)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT count(*) ")
		           .append("FROM   ")
		           .append("	PWMROI_PLAN_SUBJECT_AUTHORITY ")
		    	   .append("WHERE ")
		    	   .append(" MANUAL_NO = ? AND")
		    	   .append(" MANUAL_REV = ?");
		   		
		
		  ParameterHolder params = new ParameterHolder();
		  params.addParameter(manualNo);
		  params.addParameter(manualRev);
		    
		 return dao.queryForInt(selectSql.toString(), params);
	}
	
	
	
	public void deleteAuthorityType(String authorityType)
	{StringBuffer selectSql = new StringBuffer().append("DELETE FROM ")
		           .append("	PWMROI_AUTHORITY_TYPE ")
		   		   .append("  WHERE AUTHORITY_TYPE = ?");
	
	 ParameterHolder params = new ParameterHolder();
	  params.addParameter(authorityType);	    
	 dao.delete(selectSql.toString(), params);
	 
	
		    	   
	}
	
	public int selectAuthorityTypeUsedCount(String authorityType)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT count(*) ")
		           .append("FROM   ")
		           .append("	PWMROI_PLAN_SUBJECT_AUTHORITY ")
		    	   .append("WHERE ")
		    	   .append(" AUTHORITY_TYPE = ?");
		
		  ParameterHolder params = new ParameterHolder();
		  params.addParameter(authorityType);
		    
		 return dao.queryForInt(selectSql.toString(), params);
	}
	public void updateAuthorityType(String authorityType, String obsoleteRecordFlag)
	{StringBuffer selectSql = new StringBuffer().append("UPDATE ")
		           .append("	PWMROI_AUTHORITY_TYPE ")
		           .append("  SET OBSOLETE_RECORD_FLAG = ?,")
		           .append("	UPDT_USERID = ?,")
		   		   .append("	LAST_ACTION = ?,")
		   		   .append("	TIME_STAMP = " + dao.getTimestampFunction())
		   		   .append("  WHERE AUTHORITY_TYPE = ?");
	
	 ParameterHolder params = new ParameterHolder();
	  params.addParameter(obsoleteRecordFlag);
	  params.addParameter(ContextUtil.getUsername());
	  params.addParameter("UPDATED");
	  params.addParameter(authorityType);
	    
	 dao.update(selectSql.toString(), params);
	}
	public void updateAuthorityType(String authorityType, String authRevRule, String authDateRule, String sbPartNo, String obsoleteRecordFlag)
	{StringBuffer selectSql = new StringBuffer().append("UPDATE ")
		           .append("	PWMROI_AUTHORITY_TYPE ")
		           .append("  SET OBSOLETE_RECORD_FLAG = ?,")
		           .append("	AUTHORITY_REV_RULE = ?,")
		           .append("	AUTHORITY_DATE_RULE = ?,")
		           .append("	SB_PART_NO_RULE = ?,")
		           .append("	UPDT_USERID = ?,")
		   		   .append("	LAST_ACTION = ?,")
		   		   .append("	TIME_STAMP = " + dao.getTimestampFunction())
		   		   .append("  WHERE AUTHORITY_TYPE = ?");
	
	 ParameterHolder params = new ParameterHolder();
	  params.addParameter(obsoleteRecordFlag);
	  params.addParameter(authRevRule);
	  params.addParameter(authDateRule);
	  params.addParameter(sbPartNo);
	  params.addParameter(ContextUtil.getUsername());
	  params.addParameter("UPDATED");
	  params.addParameter(authorityType);
	    
	 dao.update(selectSql.toString(), params);
	}
	
	public void insertAuthorityType(String authorityType, String authRevRule, String authDateRule, String sbPartNo, String obsoleteRecordFlag)
	{
		StringBuffer selectSql = new StringBuffer().append("INSERT INTO  ")
	
		           .append("	PWMROI_AUTHORITY_TYPE ")
		           .append("(AUTHORITY_TYPE,AUTHORITY_REV_RULE,AUTHORITY_DATE_RULE,SB_PART_NO_RULE,OBSOLETE_RECORD_FLAG,UPDT_USERID) ")
		           .append(" VALUES ")
		           .append("(?,?,?,?,?,?)");
	
	 ParameterHolder params = new ParameterHolder();
	  params.addParameter(authorityType);
	  params.addParameter(authRevRule);
	  params.addParameter(authDateRule);
	  params.addParameter(sbPartNo);
	  params.addParameter(obsoleteRecordFlag);
	  params.addParameter(ContextUtil.getUsername());
	 dao.insert(selectSql.toString(), params);
	}
	public void insertManual(String manualNo, String manualRev, String manualTransmittalNo,String manualRemarks, 
						     String manualProvider, String manualTitle, Date manualReleaseDate, String manualType,
						     String engineSeries, String obsoleteRecordFlag)
	{
		StringBuffer selectSql = new StringBuffer().append("INSERT INTO  ")
				
		           .append("	PWMROI_MANUAL_DESC ")
		           .append("(manual_no, manual_rev, manual_transmittal_no, manual_remarks, ")
		           .append(" manual_provider, manual_title, manual_release_date, manual_type,engine_series, updt_userid)")
		           .append(" VALUES (?,?,?,?,?,?,?,?,?,?)");
		
		ParameterHolder params = new ParameterHolder();
		  params.addParameter(manualNo);
		  params.addParameter(manualRev);
		  params.addParameter(manualTransmittalNo);
		  params.addParameter(manualRemarks);
		  params.addParameter(manualProvider);
		  params.addParameter(manualTitle);
		  params.addParameter(manualReleaseDate);
		  params.addParameter(manualType);
		  params.addParameter(engineSeries);
		  params.addParameter(ContextUtil.getUsername());
		 dao.insert(selectSql.toString(), params);

	}
	public void updateManual(String manualNo, String manualRev, String manualTransmittalNo,String manualRemarks, 
		     String manualProvider, String manualTitle, Date manualReleaseDate, String manualType,
		     String engineSeries, String obsoleteRecordFlag)
	{
				StringBuffer selectSql = new StringBuffer().append("UPDATE  ")	
			    .append("	PWMROI_MANUAL_DESC ")
			    .append(" SET manual_transmittal_no = ?, manual_remarks = ?, ")
			    .append(" manual_provider= ?, manual_title = ?, manual_release_date = ?, manual_type = ?, ")
			    .append(" engine_series = ?, obsolete_record_flag = ?, updt_userid=? ")
			    .append(" where manual_no = ? and manual_rev = ?");
				
				ParameterHolder params = new ParameterHolder();				
				params.addParameter(manualTransmittalNo);
				params.addParameter(manualRemarks);
				params.addParameter(manualProvider);
				params.addParameter(manualTitle);
				params.addParameter(manualReleaseDate);
				params.addParameter(manualType);
				params.addParameter(engineSeries);
				params.addParameter(obsoleteRecordFlag);
				params.addParameter(ContextUtil.getUsername());
				params.addParameter(manualNo);
				params.addParameter(manualRev);
				
				dao.update(selectSql.toString(), params);
	}
	
	public void updateManual(String manualNo, String manualRev, String obsoleteRecordFlag)
	{
		StringBuffer selectSql = new StringBuffer().append("UPDATE  ")	
			    .append("	PWMROI_MANUAL_DESC ")
			    .append(" SET OBSOLETE_RECORD_FLAG = ?")
			    .append(" WHERE MANUAL_NO = ? and MANUAL_REV = ?");
		
		ParameterHolder params = new ParameterHolder();				
		params.addParameter(manualNo);
		params.addParameter(manualRev);
		params.addParameter(obsoleteRecordFlag);
		dao.delete(selectSql.toString(), params);
	}
	public void deleteManual(String manualNo, String manualRev)
	{
		StringBuffer selectSql = new StringBuffer().append("DELETE FROM   ")	
			    .append("	PWMROI_MANUAL_DESC ")
			    .append(" WHERE MANUAL_NO = ? and MANUAL_REV = ?");
		
			ParameterHolder params = new ParameterHolder();				
			params.addParameter(manualNo);
			params.addParameter(manualRev);
			dao.delete(selectSql.toString(), params);
	}
	
	//defect 1383, added SUBJECT_DESCRIPTION
	public void insertPlanTaskAuth(String planId,Number planRevision, String subjectNo, String subjectRev, String subjectDesc, String authorityType,
            String authorityDetail, String manualNo, String manualRev, String manualProvider, 
            String manualType, String manualSection, String sbPartNo, String autorityRev,
            Date authorityRevDate)
	{
		StringBuffer selectSql = new StringBuffer().append("INSERT INTO PWMROI_PLAN_SUBJECT_AUTHORITY   ")
		           .append("(PLAN_ID, PLAN_REVISION, SUBJECT_NO, SUBJECT_REV, SUBJECT_DESCRIPTION, AUTHORITY_TYPE,	 ")
		           .append("AUTHORITY_DETAIL,MANUAL_NO,MANUAL_REV,MANUAL_PROVIDER,MANUAL_TYPE, ")
		           .append("MANUAL_SECTION,SB_PART_NO,AUTHORITY_REV,AUTHORITY_REV_DATE,UPDT_USERID,SEQNO) ") //defect1890
		           .append(" VALUES ")
		           .append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,PWMROI_PLAN_SUBJ_AUTH_SEQ.NEXTVAL)"); //DEFECT 1890)");
	
	 ParameterHolder params = new ParameterHolder();
	  params.addParameter(planId);
	  params.addParameter(planRevision);
	  params.addParameter(subjectNo);
	  params.addParameter(subjectRev);
	  params.addParameter(subjectDesc);
	  params.addParameter(authorityType);
	  params.addParameter(authorityDetail);
	  params.addParameter(manualNo);
	  params.addParameter(manualRev);
	  params.addParameter(manualProvider);
	  params.addParameter(manualType);
	  params.addParameter(manualSection);
	  params.addParameter(sbPartNo);
	  params.addParameter(autorityRev);
	  params.addParameter(authorityRevDate);
	  params.addParameter(ContextUtil.getUsername());
  
	  dao.insert(selectSql.toString(), params);
	}
	public void updatePlanTaskAuth(String planId,Number planRevision, String subjectNo, String subjectRev, String authorityType,
            String authorityDetail, String manualNo, String manualRev, String manualProvider, 
            String manualType, String manualSection, String sbPartNo, String autorityRev,
            Date authorityRevDate,Number seqNo) //defect 1890
	{
		StringBuffer selectSql = new StringBuffer().append("UPDATE PWMROI_PLAN_SUBJECT_AUTHORITY   ")
		           .append(" SET AUTHORITY_TYPE = ?,	 ")
		           .append(" AUTHORITY_DETAIL   = ?, ")		          
		           .append(" MANUAL_REV = ?, ")
		           .append(" MANUAL_PROVIDER = ?, ")
		           .append(" MANUAL_TYPE = ?, ")
		           .append(" MANUAL_SECTION = ?,")
		           .append(" SB_PART_NO = ? ,")
		           .append(" AUTHORITY_REV = ?,")
		           .append(" AUTHORITY_REV_DATE = ?, ")
		           .append(" UPDT_USERID = ? ")
		           .append(" WHERE PLAN_ID = ? AND ") 
		           .append(" PLAN_REVISION = ?  AND ")
		           .append(" SUBJECT_NO = ? AND " )
		           .append(" SUBJECT_REV = ? AND ") 
		           .append(" MANUAL_NO = ? AND ")
		           .append(" SEQNO = ? "); //defect 1890
		          
	
	 ParameterHolder params = new ParameterHolder();
	  params.addParameter(authorityType);
	  params.addParameter(authorityDetail);
	  params.addParameter(manualRev);
	  params.addParameter(manualProvider);
	  params.addParameter(manualType);
	  params.addParameter(manualSection);
	  params.addParameter(sbPartNo);
	  params.addParameter(autorityRev);
	  params.addParameter(authorityRevDate);
	  params.addParameter(ContextUtil.getUsername());
	  params.addParameter(planId);
	  params.addParameter(planRevision);
	  params.addParameter(subjectNo);
	  params.addParameter(subjectRev);
	  params.addParameter(manualNo);
	  params.addParameter(seqNo);  //defect 1890
	  dao.update(selectSql.toString(), params);
	}
	public Map selectAuthReq(String authorityType)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT AUTHORITY_REV_RULE, ")	
					.append(" AUTHORITY_DATE_RULE, ")
					.append(" SB_PART_NO_RULE ")
		           .append(" FROM 	PWMROI_AUTHORITY_TYPE ")		          
		           .append(" WHERE AUTHORITY_TYPE = ? ");
	
	 ParameterHolder params = new ParameterHolder();
	  params.addParameter(authorityType);
	  return dao.queryForMap(selectSql.toString(), params);
	}
	public void deletePlanTaskAuth(String planId,Number planRevision, String subjectNo, String subjectRev, String manualNo,Number seqNo) //defect 1890
	{
		StringBuffer selectSql = new StringBuffer().append("DELETE FROM  PWMROI_PLAN_SUBJECT_AUTHORITY   ")
		           .append("  WHERE PLAN_ID = ? AND ")
		           .append("        PLAN_REVISION= ? AND ")
		           .append("        SUBJECT_NO = ? AND ")
		           .append("        SUBJECT_REV = ?  AND ")
		           .append("        MANUAL_NO= ?  AND ")
				    .append("       SEQNO= ?   "); //defect 1890		
		 ParameterHolder params = new ParameterHolder();
		 params.addParameter(planId);
		 params.addParameter(planRevision);
		 params.addParameter(subjectNo);
		 params.addParameter(subjectRev);
		 params.addParameter(manualNo);
		 params.addParameter(seqNo); //defect 1890
		 dao.delete(selectSql.toString(), params);
		 
	}
	
	//begin defect 1914 
	public void deletePlanTaskAuthByRevision(String planId,Number planRevision) //defect 1890
	{
		StringBuffer selectSql = new StringBuffer().append("DELETE FROM  PWMROI_PLAN_SUBJECT_AUTHORITY   ")
		           .append("  WHERE PLAN_ID = ? AND ")
		           .append("  PLAN_REVISION= ? "); //defect 1890

		
		 ParameterHolder params = new ParameterHolder();
		 params.addParameter(planId);
		 params.addParameter(planRevision);
		 dao.delete(selectSql.toString(), params);
		 
	} //end defect 1914
	
	public void insertOrderTaskAuth(String orderId, String subjectNo, String subjectRev, String authorityType,
            String authorityDetail, String manualNo, String manualRev, String manualProvider, 
            String manualType, String manualSection, String sbPartNo, String autorityRev,
            Date authorityRevDate,String authority, String arcAuthority)
	{
		StringBuffer selectSql = new StringBuffer().append("INSERT INTO PWMROI_ORDER_SUBJECT_AUTHORITY   ")
		           .append("(ORDER_ID, SUBJECT_NO, SUBJECT_REV, AUTHORITY_TYPE,	 ")
		           .append("AUTHORITY_DETAIL,MANUAL_NO,MANUAL_REV,MANUAL_PROVIDER,MANUAL_TYPE, ")
		           .append("MANUAL_SECTION,SB_PART_NO,AUTHORITY_REV,AUTHORITY_REV_DATE, ")
		           .append(" AUTHORITY, ARC_AUTHORITY,UPDT_USERID,SEQNO)")  //defect 1890
		           .append(" VALUES ")
		           .append("(  ?,  ")
		           .append("  ?,  ")
		           .append("  ?,  ")
		           .append("  ?,  ")
		           .append("  ?,  ")
		           .append("  ?,  ")
		           .append("  ?,  ")
		           .append("  ?,  ")
		           .append("  ?,  ")
		           .append("  ?,  ")
		           .append("  ?,  ")
		           .append("  ?,  ")
		           .append("  ?,  ")
		           .append("  ?,  ")
		           .append("  ?,  ")
		           .append("  ?,  ")
		           .append("  PWMROI_ORDER_SUBJ_AUTH_SEQ.NEXTVAL)"); //DEFECT 1890
	
	 ParameterHolder params = new ParameterHolder();
	  params.addParameter(orderId);;
	  params.addParameter(subjectNo);
	  params.addParameter(subjectRev);
	  params.addParameter(authorityType);
	  params.addParameter(authorityDetail);
	  params.addParameter(manualNo);
	  params.addParameter(manualRev);
	  params.addParameter(manualProvider);
	  params.addParameter(manualType);
	  params.addParameter(manualSection);
	  params.addParameter(sbPartNo);
	  params.addParameter(autorityRev);
	  params.addParameter(authorityRevDate);
	  params.addParameter(authority);
	  params.addParameter(arcAuthority);
	  params.addParameter(ContextUtil.getUsername());
	                                                 
	  
	  dao.insert(selectSql.toString(), params);
	}
	public void updateOrderTaskAuth(String orderId, String subjectNo, String subjectRev, String authorityType,
            String authorityDetail, String manualNo, String manualRev, String manualProvider, 
            String manualType, String manualSection, String sbPartNo, String autorityRev,
            Date authorityRevDate,String authority, String arcAuthority, Number seqNo) //defect 1890
	{
		StringBuffer selectSql = new StringBuffer().append("UPDATE PWMROI_ORDER_SUBJECT_AUTHORITY   ")
		           .append(" SET AUTHORITY_TYPE = ?,	 ")
		           .append(" AUTHORITY_DETAIL   = ?, ")		         
		           .append(" MANUAL_REV = ?, ")
		           .append(" MANUAL_PROVIDER = ?, ")
		           .append(" MANUAL_TYPE = ?, ")
		           .append(" MANUAL_SECTION = ?,")
		           .append(" SB_PART_NO = ? ,")
		           .append(" AUTHORITY_REV = ?,")
		           .append(" AUTHORITY_REV_DATE = ?, ")
		           .append(" AUTHORITY = ?, ")
		           .append(" ARC_AUTHORITY = ?, ")
		           .append(" UPDT_USERID = ? ")
		           .append(" WHERE ORDER_ID = ? AND ") 		          
		           .append(" SUBJECT_NO = ? AND " )
		           .append(" SUBJECT_REV = ? AND")
		  		   .append(" MANUAL_NO = ? AND")
		  		   .append(" SEQNO = ?"); //defect 1890
		          
	
	 ParameterHolder params = new ParameterHolder();
	  params.addParameter(authorityType);
	  params.addParameter(authorityDetail);	
	  params.addParameter(manualRev);
	  params.addParameter(manualProvider);
	  params.addParameter(manualType);
	  params.addParameter(manualSection);
	  params.addParameter(sbPartNo);
	  params.addParameter(autorityRev);
	  params.addParameter(authorityRevDate);
	  params.addParameter(authority);
	  params.addParameter(arcAuthority);
	  params.addParameter(ContextUtil.getUsername());
	  params.addParameter(orderId);
	  params.addParameter(subjectNo);
	  params.addParameter(subjectRev);
	  params.addParameter(manualNo);
	  params.addParameter(seqNo);  //defect 1890
	  dao.update(selectSql.toString(), params);
	}
	public void deleteOrderTaskAuth(String orderId, String subjectNo, String subjectRev, String manualNo,Number seqNo) //defect 1890
	{
		StringBuffer selectSql = new StringBuffer().append("DELETE FROM  PWMROI_ORDER_SUBJECT_AUTHORITY   ")
		           .append("  WHERE ORDER_ID = ? AND ")
		           .append("        SUBJECT_NO = ? AND ")
		           .append("        SUBJECT_REV = ?  AND ")
		           .append("        MANUAL_NO = ? AND  ")
		           .append("        SEQNO = ? "); //defect 1890
		 ParameterHolder params = new ParameterHolder();
		 params.addParameter(orderId);
		 params.addParameter(subjectNo);
		 params.addParameter(subjectRev);
		 params.addParameter(manualNo);
		 params.addParameter(seqNo); //defect 1890
		 dao.delete(selectSql.toString(), params);
	}
	public void updateSforTaskAuth(String planId, Number planUpdtNo, Number subjectNo, String authority)
	{
		StringBuffer selectSql = new StringBuffer().append("UPDATE SFOR_SFPL_PLAN_SUBJECT ")
				   .append(" SET AUTHORITY  = ? ")
		           .append("  WHERE PLAN_ID = ? AND ")
		           .append("        PLAN_UPDT_NO = ? AND ")		        
		           .append("        SUBJECT_NO = ? ");
		           
		 ParameterHolder params = new ParameterHolder();
		 params.addParameter(authority);
		 params.addParameter(planId);
		 params.addParameter(planUpdtNo);
		 params.addParameter(subjectNo);
		 dao.update(selectSql.toString(), params);
		
	}
	public void updateAuthManRev(String planId, Number planRevision, String subjectNo, String subjectRev,
            String manualNo,String manualRev,Number seqNo) //defect 1890
	{
		StringBuffer selectSql = new StringBuffer().append("UPDATE PWMROI_PLAN_SUBJECT_AUTHORITY")
				   .append(" SET MANUAL_REV  = ? ")
		           .append("  WHERE PLAN_ID = ? AND ")
		           .append("        PLAN_REVISION = ? AND ")		        
		           .append("        SUBJECT_NO = ? AND ")
		           .append("        SUBJECT_REV = ? AND ")
		           .append("        MANUAL_NO = ? AND ")
		           .append("        SEQNO = ? ");  //defect 1890
		           
		 ParameterHolder params = new ParameterHolder();
		 params.addParameter(manualRev);
		 params.addParameter(planId);
		 params.addParameter(planRevision);
		 params.addParameter(subjectNo);
		 params.addParameter(subjectRev);
		 params.addParameter(manualNo);	
		 params.addParameter(seqNo);	//defect 1890
		 dao.update(selectSql.toString(), params);
	}
	public Map selectPlanInfo(String planId, Number planRevision)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT PART_CHG, MFG_BOM_CHG,PLAN_NO,")	
				   .append("PLND_WORK_LOC,PROGRAM,BOM_NO,PART_NO,BOM_ID,WORK_FLOW")
		           .append(" FROM 	SFPL_PLAN_V ")		          
		           .append(" WHERE PLAN_ID = ? ")
		           .append(" AND PLAN_REVISION = ? ")
		           .append(" AND PLAN_VERSION = ? ")
		           .append(" AND PLAN_ALTERATIONS= ? ");
	
	 ParameterHolder params = new ParameterHolder();
	  params.addParameter(planId);
	  params.addParameter(planRevision);
	  params.addParameter(1);
	  params.addParameter(0);
	 
	  return dao.queryForMap(selectSql.toString(), params);
	}
	public String selectPWP(String planId, Number planRevision)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT PWP_ID ")
	    .append(" FROM SFPL_PLAN_PWP_XREF ")		          
	    .append(" WHERE PLAN_ID = ? ")
	    .append(" AND PLAN_REVISION = ? ")
	    .append(" AND PLAN_VERSION = ? ")
	    .append(" AND PLAN_ALTERATIONS= ? ");
	
		ParameterHolder params = new ParameterHolder();
		params.addParameter(planId);
		params.addParameter(planRevision);
		params.addParameter(1);
		params.addParameter(0);
		
		return dao.queryForString(selectSql.toString(), params);
	}	
  public void updateOrderManRev(String orderId, Number subjectNo, Number subjectRev,String manualNo,String manualRev,Number seqNo) //defect 1890
  {
	  
	  StringBuffer selectSql = new StringBuffer().append("UPDATE PWMROI_ORDER_SUBJECT_AUTHORITY")
			   .append(" SET MANUAL_REV  = ? ")
	           .append("  WHERE ORDER_ID = ? AND ")	        
	           .append("        SUBJECT_NO = ? AND ")
	           .append("        SUBJECT_REV = ? AND ")
	           .append("        MANUAL_NO = ? AND ")
	           .append("        SEQNO = ? ");  //defect 1890
	           
	 ParameterHolder params = new ParameterHolder();
	 params.addParameter(manualRev);
	 params.addParameter(orderId);
	 params.addParameter(subjectNo);
	 params.addParameter(subjectRev);
	 params.addParameter(manualNo);	
	 params.addParameter(seqNo);	//defect 1890
	 dao.update(selectSql.toString(), params);
  }
  public Number selectMinOperKey(String orderId, Number subjectNo, Number subjectRev)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT MIN(OPER_KEY) AS OPER_KEY ")
	    .append(" FROM SFOR_SFWID_OPER_SUBJECT ")		          
	    .append(" WHERE ORDER_ID = ? ")
	    .append(" AND SUBJECT_NO = ? ")
	    .append(" AND SUBJECT_REV = ? ");
	
		ParameterHolder params = new ParameterHolder();
		params.addParameter(orderId);
		params.addParameter(subjectNo);
		params.addParameter(subjectRev);		
		return dao.queryForInt(selectSql.toString(), params);
	}	
  public String selectOrderWorkFlow(String orderId)
  {
		  StringBuffer selectSql = new StringBuffer().append("SELECT MIN(WORK_FLOW) AS WORK_FLOW ")
		  .append(" FROM SFFND_DOC_TYPE_DEF A, SFWID_ORDER_DESC B ")		          
		  .append("WHERE B.ORDER_ID =  ? AND ")
		  .append(" A.DOC_TYPE= ? AND ")
		  .append(" A.DOC_SUB_TYPE = B.ORDER_TYPE  AND ")
		  .append("  OBSOLETE_RECORD_FLAG =  ? AND ")
		  .append(" IS_VISIBLE = ? ");
		
			ParameterHolder params = new ParameterHolder();
			params.addParameter(orderId);
			params.addParameter("Process Order");
			params.addParameter("N");	
			params.addParameter("Y");	
			return dao.queryForString(selectSql.toString(), params);
			
  }			
  public String selectGUID()
  {
	  StringBuffer selectSql = new StringBuffer().append("SELECT SFDB_GUID() from dual ");
	  return dao.queryForString(selectSql.toString());
  }
  public Map selectPlanTaskInfo(String planId, Number planRevision)
  {
	  StringBuffer selectSql = new StringBuffer().append("SELECT A.TASK_ID, B.TASK_TYPE, B.QUEUE_TYPE, B.QUEUE_TYPE ,B.NOTES")				  
	           .append(" FROM SFFND_PLG_TASK A, SFFND_TASK B ")		          
	           .append(" WHERE PLAN_ID = ? ")
	           .append(" AND PLAN_REVISION = ? ")
	           .append(" AND PLAN_VERSION = ? ")
	           .append(" AND PLAN_ALTERATIONS= ? ")
	           .append(" AND A.TASK_ID =  B.TASK_ID" )
	           .append(" AND B.STATUS in (?,?) ") ;

				ParameterHolder params = new ParameterHolder();
				 params.addParameter(planId);
				 params.addParameter(planRevision);
				 params.addParameter(1);
				 params.addParameter(0);
				 params.addParameter("IN QUEUE");
				 params.addParameter("ACTIVE");
				 return dao.queryForMap(selectSql.toString(), params);
  }
  public int selectPlanAuthCount(String planId)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT count(*) ")
		           .append("FROM   ")
		           .append(" SFPL_PLAN_REV ")
		    	   .append(" WHERE ")
		    	   .append(" PLAN_ID= ? AND ")
		    	   .append(" REV_STATUS = ?" );
		
		  ParameterHolder params = new ParameterHolder();
		  params.addParameter(planId);
		  params.addParameter("PLG_AUTHORING");
		    
		 return dao.queryForInt(selectSql.toString(), params);
	}
  public int selectOrderAuthCount(String orderId)
	{
		StringBuffer selectSql = new StringBuffer().append("SELECT count(*) ")
		           .append("FROM   ")
		           .append(" SFWID_ORDER_DESC ")
		    	   .append(" WHERE ")
		    	   .append("  ORDER_ID = ? AND ")
		    	   .append(" ALT_STATUS LIKE ? " );
		
		  ParameterHolder params = new ParameterHolder();
		  params.addParameter(orderId);
		  params.addParameter("%AUTHORING%");
		    
		 return dao.queryForInt(selectSql.toString(), params);
	}
  
  @Override
  public List selectOrderSubjectAuthority(String orderId,String[] manual)
  {
	    String manualNo=""; 
  		StringBuilder selectSql = new StringBuilder().append(" SELECT A.SUBJECT_NO,	  ")
  												 	 .append("        A.SUBJECT_REV,  ")
  												 	 .append("        A.SUBJECT_DESCRIPTION,  ")
  												 	 .append("        A.AUTHORITY_TYPE,  ")
  												 	 .append("        A.AUTHORITY_DETAIL,  ")
  												 	 .append("        A.MANUAL_NO,  ")
  												 	 .append("        A.MANUAL_SECTION,  ")
  												 	 .append("        A.AUTHORITY_REV,  ")
  												 	 .append("        A.AUTHORITY_REV_DATE,  ")
  												 	 .append("        A.SB_PART_NO,  ")
  												 	 .append("        A.AUTHORITY  ")
  												 	 .append("   FROM PWMROI_ORDER_SUBJECT_AUTHORITY A ,         ")
  												 	 .append("        SFOR_SFWID_ORDER_SUBJECT B WHERE A.ORDER_ID = ? ")
  												 	 .append("        AND A.ORDER_ID = B.ORDER_ID AND A.SUBJECT_NO = B.SUBJECT_NO AND A.SUBJECT_REV = B.SUBJECT_REV ")
  												 	 .append("		  AND B.INCLUDED_FLAG = ?");
  	
  		ParameterHolder parameters = new ParameterHolder();
  		parameters.addParameter(orderId);
  		parameters.addParameter("INCLUDED");
  		if(manual != null)
  		{
  			selectSql.append(" AND MANUAL_NO IN( ");
  			for(int i = 0; i < manual.length ; i++)
  			{
  				manualNo = manualNo + wrapUtils.wrapValue(manual[i]) + ",";
  			}
  			manualNo = manualNo.substring(0, manualNo.length()-1);
  			selectSql.append(manualNo +")");  			
  		}

  		return dao.queryForList(selectSql.toString(), parameters);
  }
}
