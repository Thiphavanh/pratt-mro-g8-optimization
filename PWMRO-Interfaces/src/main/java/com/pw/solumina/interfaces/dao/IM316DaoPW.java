/*
 * This unpublished work, first created in 2019 and updated thereafter,
 *  is protected under the copyright laws of the United States and of
 *  other countries throughout the world.  Use, disassembly, reproduction,
 *  distribution, etc. without the express written consent of United
 *  Technologies Corporation is prohibited.  Copyright United Technologies
 *  Corporation 2019.  All rights reserved.  Information contained herein
 *  is proprietary and confidential and improper use or disclosure may
 *  result in civil and penal sanctions.
 *
 *  File:    IM316DaoPW.java
 * 
 *  Created: 2019-08-05
 * 
 *  Author:  B. Corson
 * 
 *  Revision History
 * 
 *  Date          Who            Description
 *  -----------   ------------   -----------------------------------------------------------
 *  2019-08-05    B. Corson      Initial Release SMRO_MD_M316 - Scrap Codes and Hold Types Groups Interface
 *  								
 */
package com.pw.solumina.interfaces.dao;

import java.util.List;

public interface IM316DaoPW {

    public List getM316ColumnLengths();
    
    public void insertM316CatCode(String workLoc,
                                  String catalog,
                                  String category,
                                  String code,
                                  String catDesc,
                                  String codeDesc,
                                  String obsoleteRecordFlag,
                                  String updtUserid);
    
    public int updateM316CatCode(String workLoc,
                                 String catalog,
                                 String category,
                                 String code,
                                 String catDesc,
                                 String codeDesc,
                                 String obsoleteRecordFlag,
                                 String updtUserid);
    
    public int updateM316ObsoleteFL(String workLoc,
                                    String catalog,
                                    String category,
                                    String code,
                                    String obsoleteRecordFlag,
                                    String updtUserid);
    
    public boolean catCodeExists(String workLoc,
                                 String catalog,
                                 String category,
                                 String code);
}
