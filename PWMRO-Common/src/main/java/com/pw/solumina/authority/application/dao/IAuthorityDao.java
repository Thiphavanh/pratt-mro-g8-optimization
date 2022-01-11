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
 *  File:    IAuthorityDao.java
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
 *  03-18-2020  D.Sibrian		PWU-116F Upgrade
 *  04-13-2021  J. DeNinno      Defect 1890 add sequence number for primary key
*/
package com.pw.solumina.authority.application.dao;

import java.util.Date;
import java.util.Map;
import java.util.List;

public interface IAuthorityDao {
    public int selectProviderCount(String manualProvider);
    public void insertProvider(String manualProvider);
    public void updateProvider(String manualProvider, String obsoleteRecordFlag);
    public int selectManualTypeCount(String manualType);
    public void insertManualType(String manualType);
    public void updateManualType(String manualType, String obsoleteRecordFlag);
    public void deleteProvider(String manualProvider);
    public int selectProviderUsedCount(String manualProvider);
    public void deleteManualType(String manualType);
    public int selectManualTypeUsedCount(String manualType);
    public void deleteAuthorityType(String AuthorityType);
    public int selectAuthorityTypeUsedCount(String AuthorityType);
    public void updateAuthorityType(String authorityType, String authRevRule, String authDateRule, String sbPartNo, String obsoleteRecordFlag);
    public void updateAuthorityType(String authorityType,String obsoleteRecordFlag);
    public void insertAuthorityType(String authorityType, String authRevRule, String authDateRule, String sbPartNo, String obsoleteRecordFlag);
	public void insertManual(String manualNo, String manualRev, String manualTransmittalNo,String manualRemarks, 
		     String manualProvider, String manualTitle, Date manualReleaseDate, String manualType,
		     String engineSeries, String obsoleteRecordFlag);
	public void updateManual(String manualNo, String manualRev, String manualTransmittalNo,String manualRemarks, 
		     String manualProvider, String manualTitle, Date manualReleaseDate, String manualType,
		     String engineSeries, String obsoleteRecordFlag);
	public void updateManual(String manualNo, String manualRev,  String obsoleteRecordFlag);
	public void deleteManual(String manualNo, String manualRev);
	public void insertPlanTaskAuth(String planId,Number planUpdtNo, String ubjectNo, String subjectRev, String subjectDesc, String authorityType,
            String authorityDetail, String manulNo, String manualRev, String manualProvider, 
            String manualType, String manualSection, String sbPartNo, String autorityRev,
            Date authorityRevDate);
	public void updatePlanTaskAuth(String planId,Number planUpdtNo, String ubjectNo, String subjectRev, String authorityType,
            String authorityDetail, String manulNo, String manualRev, String manualProvider, 
            String manualType, String manualSection, String sbPartNo, String autorityRev,
            Date authorityRevDate,Number seqNo); //defect 1890
	public void insertOrderTaskAuth(String orderId, String ubjectNo, String subjectRev, String authorityType,
            String authorityDetail, String manulNo, String manualRev, String manualProvider, 
            String manualType, String manualSection, String sbPartNo, String autorityRev,
            Date authorityRevDate,String authority, String arcAuthority);
	public void updateOrderTaskAuth(String orderId, String ubjectNo, String subjectRev, String authorityType,
            String authorityDetail, String manulNo, String manualRev, String manualProvider, 
            String manualType, String manualSection, String sbPartNo, String autorityRev,
            Date authorityRevDate,String authority, String arcAuthority,Number seqNo); // defect 1890
	public Map selectAuthReq(String authorityType);
	public void deletePlanTaskAuth(String planId,Number planUpdtNo, String subjectNo, String subjectRev, String manualNo,Number seqNo); //defect 1890
	public void deleteOrderTaskAuth(String orderId, String subjectNo, String subjectRev, String manualNo,Number seqNo ); //defect 1890
	public void updateSforTaskAuth(String planId, Number planUpdtNo,Number subjectNo, String authority);	
	public void updateAuthManRev(String planId, Number planRevision, String subjectNo, String subjectRev,
			                     String manualNo,String manualRev, Number seqNo); //defect 1890
	public Map selectPlanInfo(String planId, Number planRevision);
	public String selectPWP(String planId, Number planRevision);
	public void updateOrderManRev(String orderId, Number subjectNo, Number subjectRev,String manualNo,String manualRev,Number seqNo); //defect 1890
	public Number selectMinOperKey(String orderId, Number subjectNo, Number subjectRev);
    public String selectOrderWorkFlow(String orderId);
    public String selectGUID();
    public Map selectPlanTaskInfo(String planId, Number planRevision);
	public int selectManualUsedCount(String manualNo, String manualRev);
	public int selectPlanAuthCount(String planId);
	public int selectOrderAuthCount(String orderId);
	public List selectOrderSubjectAuthority(String orderId,String[] manual);
}
