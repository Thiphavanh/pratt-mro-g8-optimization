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
*  File:    EACP_Service_TestSoap.java
* 
*  Created: 2017-10-17
* 
*  Author:  c293011
* 
*  Revision History
* 
*  Date      	Who             Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-10-17 	c293011		    Initial Release SMRO_EXPT_204
*/


package com.pw.solumina.eacpWebService;

/**
 * @author c293011
 *
 */
public interface EACP_Service_TestSoap extends java.rmi.Remote {
    public com.pw.solumina.eacpWebService.Authinfo[] getAuthInfo(java.lang.String clockId) throws java.rmi.RemoteException;
}
