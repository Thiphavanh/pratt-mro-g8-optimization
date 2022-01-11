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
*  File:    PwmroiDefect.java
* 
*  Created: 2020-01-24
* 
*  Author:  X005703
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2020-01-24 	D.Sibrian		Initial creation for update of T314 Interface messaging
*/

package com.pw.solumina.domain;

public class PwmroiDefect {
	 private String defectCat;
	 private String defectCode;
	 private String defectPrimary;
	 
	 
	public String getDefectCat() {
		return defectCat;
	}
	public void setDefectCat(String defectCat) {
		this.defectCat = defectCat;
	}
	public String getDefectCode() {
		return defectCode;
	}
	public void setDefectCode(String defectCode) {
		this.defectCode = defectCode;
	}
	public String getDefectPrimary() {
		return defectPrimary;
	}
	public void setDefectPrimary(String defectPrimary) {
		this.defectPrimary = defectPrimary;
	}	 
}
