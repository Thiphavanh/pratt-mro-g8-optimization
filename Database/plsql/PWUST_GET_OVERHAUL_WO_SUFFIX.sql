/*
* This unpublished work, first created in 2018 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2018.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    PWUST_GET_OVERHAUL_WO_SUFFIX.sql
* 
 *  Created: 2018-03-16
* 
 *  Author:  xcs4331
* 
 *  Revision History
* 
 *  Date        Who             Description
*  ----------  ---------------  ---------------  ---------------------------------------------------
* 2018-03-16   xcs4331        Initial Release PW_MRO_eWORK_202-Order-Creation
* 2018-03-29   xcs4331        defect 386
* 2018-04-28   xcs4331        defect 632
*/

CREATE OR REPLACE PROCEDURE PWUST_GET_OVERHAUL_WO_SUFFIX(VI_PW_ORDER_No    VARCHAR,
                                                         VI_PW_ORDER_TYPE  VARCHAR, 
                                                         VO_NEXT_NUMBER    OUT VARCHAR) IS
--
BEGIN
       BEGIN
         SELECT TT.PW_ORDER_NO
         into VO_NEXT_NUMBER
         FROM (SELECT T.PW_ORDER_NO
               FROM PWUST_SFWID_ORDER_DESC T
               WHERE T.PW_ORDER_NO LIKE VI_PW_ORDER_No
               AND T.PW_ORDER_TYPE = VI_PW_ORDER_TYPE
               ORDER BY T.PW_ORDER_NO DESC ) TT
         WHERE ROWNUM =1;
       EXCEPTION
            WHEN NO_DATA_FOUND THEN
              VO_NEXT_NUMBER := null;
       END;

END;
/

grant execute on PWUST_GET_OVERHAUL_WO_SUFFIX to SF$CONNECTION_POOL;
grant execute on PWUST_GET_OVERHAUL_WO_SUFFIX to PRATT_READ_ONLY;
