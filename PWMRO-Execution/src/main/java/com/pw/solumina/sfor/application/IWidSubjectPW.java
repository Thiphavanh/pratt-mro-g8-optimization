package com.pw.solumina.sfor.application;
/*
 *  This unpublished work, first created in 2018 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2018.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    IWidSubjectPW.java
 * 
 *  Created: 2018-05-02
 * 
 *  Author:  Bob Preston
 * 
 *  Revision History
 * 
 *  Date        	Who          	Description
 *  -----------		------------ 	-----------------------------------------------------------
 *	2018-05-02		B. Preston		Initial Release, SMRO_WOE_211, Defect 51.
 *
 */

public interface IWidSubjectPW {

	void includeExcludeMultOp(String orderId, String operationKeyList, String includeExclude, String calledFrom,
			String notes, String discrepancyId, Number discrepancyLineNumber, String stepKey);

}