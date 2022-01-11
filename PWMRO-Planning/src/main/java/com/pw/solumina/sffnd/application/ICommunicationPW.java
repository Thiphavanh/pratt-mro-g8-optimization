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
*  File:    ICommunicationPW.java
* 
*  Created: 2017-09-29
* 
*  Author:  c079222
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-09-29 	c079222		    SMRO_EXPT_201 - Initial Release 
*/
package com.pw.solumina.sffnd.application;

import java.util.Date;

public interface ICommunicationPW
{
	public void createCommunication(String taskId, String documentType,
			String queueType, String toQueues, String priority,
			String replyRequirement, String subject, String messageInput,
			String scheduleStart, String scheduleEnd,
			String ucfCommunicationVch1, String ucfCommunicationVch2,
			String ucfCommunicationVch3, String ucfCommunicationVch4,
			String ucfCommunicationVch5, String ucfCommunicationVch6,
			String ucfCommunicationVch7, String ucfCommunicationVch8,
			String ucfCommunicationVch9, String ucfCommunicationVch10,
			String ucfCommunicationVch11, String ucfCommunicationVch12,
			String ucfCommunicationVch13, String ucfCommunicationVch14,
			String ucfCommunicationVch15, Number ucfCommunicationNumber1,
			Number ucfCommunicationNumber2, Number ucfCommunicationNumber3,
			Number ucfCommunicationNumber4, Number ucfCommunicationNumber5,
			String ucfCommunicationFlag1, String ucfCommunicationFlag2,
			String ucfCommunicationFlag3, String ucfCommunicationFlag4,
			String ucfCommunicationFlag5, Date ucfCommunicationDate1,
			Date ucfCommunicationDate2, Date ucfCommunicationDate3,
			Date ucfCommunicationDate4, Date ucfCommunicationDate5);

    public void preAcknowledgeCommunication(String communicationId,
                                            String userid);

	public void replyCommunicationPre(String communicationId,
                                      String ucfCommunicationVch1,
                                      String ucfCommunicationVch2,
                                      String ucfCommunicationVch3,
                                      String ucfCommunicationVch4,
                                      String ucfCommunicationVch5,
                                      String ucfCommunicationVch6,
                                      String ucfCommunicationVch7,
                                      String ucfCommunicationVch8,
                                      String ucfCommunicationVch9,
                                      String ucfCommunicationVch10,
                                      String ucfCommunicationVch11,
                                      String ucfCommunicationVch12,
                                      String ucfCommunicationVch13,
                                      String ucfCommunicationVch14,
                                      String ucfCommunicationVch15,
                                      Number ucfCommunicationNumber1,
                                      Number ucfCommunicationNumber2,
                                      Number ucfCommunicationNumber3,
                                      Number ucfCommunicationNumber4,
                                      Number ucfCommunicationNumber5,
                                      String ucfCommunicationFlag1,
                                      String ucfCommunicationFlag2,
                                      String ucfCommunicationFlag3,
                                      String ucfCommunicationFlag4,
                                      String ucfCommunicationFlag5,
                                      Date ucfCommunicationDate1,
                                      Date ucfCommunicationDate2,
                                      Date ucfCommunicationDate3,
                                      Date ucfCommunicationDate4,
                                      Date ucfCommunicationDate5,
                          			  String ucfCommunicationVch2551,
                        			  String ucfCommunicationVch2552,
                        			  String ucfCommunicationVch2553,
                        			  String ucfCommunicationVch40001,
                        			  String ucfCommunicationVch40002,
                                      String calledFrom);

	public void replyCommunication(String communicationId,
                                   String toQueue,
                                   String fromQueue,
                                   String priority,
                                   String replyRequirement,
                                   String subject,
                                   String messageInput,
                                   String scheduledStart,
                                   String scheduledEnd,
                                   String ucfCommunicationVch1,
                                   String ucfCommunicationVch2,
                                   String ucfCommunicationVch3,
                                   String ucfCommunicationVch4,
                                   String ucfCommunicationVch5,
                                   String ucfCommunicationVch6,
                                   String ucfCommunicationVch7,
                                   String ucfCommunicationVch8,
                                   String ucfCommunicationVch9,
                                   String ucfCommunicationVch10,
                                   String ucfCommunicationVch11,
                                   String ucfCommunicationVch12, 
                                   String ucfCommunicationVch13,
                                   String ucfCommunicationVch14,
                                   String ucfCommunicationVch15,
                                   Number ucfCommunicationNumber1,
                                   Number ucfCommunicationNumber2,
                                   Number ucfCommunicationNumber3,
                                   Number ucfCommunicationNumber4,
                                   Number ucfCommunicationNumber5,
                                   String ucfCommunicationFlag1,
                                   String ucfCommunicationFlag2,
                                   String ucfCommunicationFlag3,
                                   String ucfCommunicationFlag4,
                                   String ucfCommunicationFlag5,
                                   Date ucfCommunicationDate1,
                                   Date ucfCommunicationDate2,
                                   Date ucfCommunicationDate3,
                                   Date ucfCommunicationDate4,
                                   Date ucfCommunicationDate5,
                                   String ucfCommunicationVch2551,
                                   String ucfCommunicationVch2552,
                                   String ucfCommunicationVch2553,
                                   String ucfCommunicationVch40001,
                                   String ucfCommunicationVch40002);
}
