/*
 * This unpublished work, first created in 2021 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassemble, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2021.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    ToolDaoImplPW.java
 * 
 *  Created: 2021-02-23
 * 
 *  Author:  John DeNinno 
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *  2021-02-23		John DeNinno    Initial Defect 1867 - Tooling Serial 
 *  2021-04-27      John DeNinno    Defect 1909 Set toolSerialCalibrateDays to Y
 *  2021-07-08      John DeNinno    Defect 1942 - Insert obsoleterecordflag N and leave alone on update
 *  2021-09-09      C. Almodovar    Defect 1981 - Default status of tools recieved from tool interface to active
 *  */
package com.pw.solumina.sffnd.dao;

import java.util.Date;

import com.ibaset.common.ImplementationOf;
import com.ibaset.solumina.sffnd.dao.IToolDao;


public abstract class ToolDaoImplPW extends ImplementationOf<IToolDao> implements IToolDao {

	
	@Override
	public int updateToolSerialDescription(String status, String manufacturerCode, Date lastCalibrationDate,
			String toolSerialCalibrateDays, String toolSerialCalibrateHours, String toolSerialCalibrateUses,
			Integer toolSerialCalibDaysFreq, Integer toolSerialCalibHoursFreq, Integer toolSerialCalibUsesFreq,
			String calibrationProcedure, String calibrationLocationId, String calibrationDepartmentId,
			String calibrationCenterId, String certificate, String issueLocationId, String issueDepartmentId,
			String issueCenterId, String homeLocationId, String homeDepartmentId, String homeCenterId,
			Date expirationDate, Date estimatedInserviceDate, String comments, Number maximumNumberOfUses,
			Number maximumNumberOfHours, Number maximumNumberOfDays, Number cumulativeUseControl,
			Number cumulativeHoursControl, Date outOfServiceDate, Date missingDate, Date toRepairDate, String userName,
			String toolNumber, String toolChange, String toolSerialNumber, String ucfToolSerialVch1,
			String ucfToolSerialVch2, String ucfToolSerialVch3, String ucfToolSerialVch4, String ucfToolSerialVch5,
			String ucfToolSerialVch6, String ucfToolSerialVch7, String ucfToolSerialVch8, String ucfToolSerialVch9,
			String ucfToolSerialVch10, String ucfToolSerialVch11, String ucfToolSerialVch12, String ucfToolSerialVch13,
			String ucfToolSerialVch14, String ucfToolSerialVch15, Number ucfToolSerialNum1, Number ucfToolSerialNum2,
			Number ucfToolSerialNum3, Number ucfToolSerialNum4, Number ucfToolSerialNum5, Date ucfToolSerialDate1,
			Date ucfToolSerialDate2, Date ucfToolSerialDate3, Date ucfToolSerialDate4, Date ucfToolSerialDate5,
			String ucfToolSerialFlag1, String ucfToolSerialFlag2, String ucfToolSerialFlag3, String ucfToolSerialFlag4,
			String ucfToolSerialFlag5, String obsoleteRecordFlag, Number totalHours, Number totalUses, String ipAddress,
			String ucfToolSerialVch2551, String ucfToolSerialVch2552, String ucfToolSerialVch2553,
			String ucfToolSerialVch40001, String ucfToolSerialVch40002) {
		
		obsoleteRecordFlag = "N"; //defect 1942
						                         //Defect 1981                                    //defect 1909 toolSerialCalibrateDays "Y"                                                                                                                                                                                                                                                                                                                                                                                                            //defect 1867                         
		return Super.updateToolSerialDescription("ACTIVE", manufacturerCode, lastCalibrationDate, "Y", toolSerialCalibrateHours, toolSerialCalibrateUses, toolSerialCalibDaysFreq, toolSerialCalibHoursFreq, toolSerialCalibUsesFreq, calibrationProcedure, calibrationLocationId, calibrationDepartmentId, calibrationCenterId, certificate, issueLocationId, issueDepartmentId, issueCenterId, homeLocationId, homeDepartmentId, homeCenterId, ucfToolSerialDate1, estimatedInserviceDate, comments, maximumNumberOfUses, maximumNumberOfHours, maximumNumberOfDays, cumulativeUseControl, cumulativeHoursControl, outOfServiceDate, missingDate, toRepairDate, userName, toolNumber, toolChange, toolSerialNumber, ucfToolSerialVch1, ucfToolSerialVch2, ucfToolSerialVch3, ucfToolSerialVch4, ucfToolSerialVch5, ucfToolSerialVch6, ucfToolSerialVch7, ucfToolSerialVch8, ucfToolSerialVch9, ucfToolSerialVch10, ucfToolSerialVch11, ucfToolSerialVch12, ucfToolSerialVch13, ucfToolSerialVch14, ucfToolSerialVch15, ucfToolSerialNum1, ucfToolSerialNum2, ucfToolSerialNum3, ucfToolSerialNum4, ucfToolSerialNum5, ucfToolSerialDate1, ucfToolSerialDate2, ucfToolSerialDate3, ucfToolSerialDate4, ucfToolSerialDate5, ucfToolSerialFlag1, ucfToolSerialFlag2, ucfToolSerialFlag3, ucfToolSerialFlag4, ucfToolSerialFlag5, obsoleteRecordFlag, totalHours, totalUses, ipAddress, ucfToolSerialVch2551, ucfToolSerialVch2552, ucfToolSerialVch2553, ucfToolSerialVch40001, ucfToolSerialVch40002);
	}

	@Override
	public void insertToolSerial(String toolNumber, String toolChange, String toolSerialNumber, String manufacturerCode,
			String userName, Date lastCalibrationDate, String toolSerialCalibrateDays, String toolSerialCalibrateHours,
			String toolSerialCalibrateUses, Integer toolSerialCalibDaysFreq, Integer toolSerialCalibHoursFreq,
			Integer toolSerialCalibUsesFreq, String calibrationProcedure, String calibrationLocationId,
			String calibrationDepartmentId, String calibrationCenterId, String certificate, String homeLocationId,
			String homeDepartmentId, String homeCenterId, String issueLocationId, String issueDepartmentId,
			String issueCenterId, Date estimatedInserviceDate, String comments, String obsoleteRecordFlag,
			String status, Date expirationDate, Number maximumNumberOfUses, Number maximumNumberOfHours,
			Number cumulativeUseControl, Number cumulativeHoursControl, Number maximumNumberOfDays,
			String ucfToolSerialVch1, String ucfToolSerialVch2, String ucfToolSerialVch3, String ucfToolSerialVch4,
			String ucfToolSerialVch5, String ucfToolSerialVch6, String ucfToolSerialVch7, String ucfToolSerialVch8,
			String ucfToolSerialVch9, String ucfToolSerialVch10, String ucfToolSerialVch11, String ucfToolSerialVch12,
			String ucfToolSerialVch13, String ucfToolSerialVch14, String ucfToolSerialVch15, Number ucfToolSerialNum1,
			Number ucfToolSerialNum2, Number ucfToolSerialNum3, Number ucfToolSerialNum4, Number ucfToolSerialNum5,
			Date ucfToolSerialDate1, Date ucfToolSerialDate2, Date ucfToolSerialDate3, Date ucfToolSerialDate4,
			Date ucfToolSerialDate5, String ucfToolSerialFlag1, String ucfToolSerialFlag2, String ucfToolSerialFlag3,
			String ucfToolSerialFlag4, String ucfToolSerialFlag5, Number totalHours, Number totalUses, String ipAddress,
			String ucfToolSerialVch2551, String ucfToolSerialVch2552, String ucfToolSerialVch2553,
			String ucfToolSerialVch40001, String ucfToolSerialVch40002) {
		
	                                                                                                                                                         //defect 1909 "Y" toolSerialCalibrateDays                                                                                                                                                                                                                                                                                         //obsoleteRecordFlag defect 1942, Status active Defect 1981                                                                                                    //defect 1867                                                                                                                                                                                                                                                                    
		Super.insertToolSerial(toolNumber, toolChange, toolSerialNumber, manufacturerCode, userName, lastCalibrationDate, "Y", toolSerialCalibrateHours, toolSerialCalibrateUses, toolSerialCalibDaysFreq, toolSerialCalibHoursFreq, toolSerialCalibUsesFreq, calibrationProcedure, calibrationLocationId, calibrationDepartmentId, calibrationCenterId, certificate, homeLocationId, homeDepartmentId, homeCenterId, issueLocationId, issueDepartmentId, issueCenterId, estimatedInserviceDate, comments, "N", "ACTIVE", ucfToolSerialDate1, maximumNumberOfUses, maximumNumberOfHours, cumulativeUseControl, cumulativeHoursControl, maximumNumberOfDays, ucfToolSerialVch1, ucfToolSerialVch2, ucfToolSerialVch3, ucfToolSerialVch4, ucfToolSerialVch5, ucfToolSerialVch6, ucfToolSerialVch7, ucfToolSerialVch8, ucfToolSerialVch9, ucfToolSerialVch10, ucfToolSerialVch11, ucfToolSerialVch12, ucfToolSerialVch13, ucfToolSerialVch14, ucfToolSerialVch15, ucfToolSerialNum1, ucfToolSerialNum2, ucfToolSerialNum3, ucfToolSerialNum4, ucfToolSerialNum5, ucfToolSerialDate1, ucfToolSerialDate2, ucfToolSerialDate3, ucfToolSerialDate4, ucfToolSerialDate5, ucfToolSerialFlag1, ucfToolSerialFlag2, ucfToolSerialFlag3, ucfToolSerialFlag4, ucfToolSerialFlag5, totalHours, totalUses, ipAddress, ucfToolSerialVch2551, ucfToolSerialVch2552, ucfToolSerialVch2553, ucfToolSerialVch40001, ucfToolSerialVch40002);
		
	}
	
	
	
	
	

}
