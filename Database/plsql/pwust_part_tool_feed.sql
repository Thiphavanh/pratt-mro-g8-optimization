------------------------------------------------
-- Export file for user SFMFG                 --
-- Created by c079222 on 6/3/2020, 1:10:42 PM --
------------------------------------------------

prompt
prompt Creating procedure PWUST_PART_TOOL_FEED
prompt =======================================
prompt
CREATE OR REPLACE PROCEDURE PWUST_PART_TOOL_FEED AS
  -- ****************************************************************************
  -- Copyright 2012-2020 DXC
  -- Unpublished-rights reserved under the Copyright Laws of the United States.
  -- US Government Procurements:
  --         Commercial Software licensed with Restricted Rights.
  -- Use, reproduction, or disclosure is subject to restrictions
  -- set forth in license agreement and purchase contract.
  -- ****************************************************************************
  -- Date Created and Created By:
  -- 2012-06-26   N. St.Jarre
  -- Description:
  -- 2017-10-30  R. Thorpe
  -- Copied from G7 OEM - Modified for MRO G8
  -- 2020-03-06  B. Corson
  -- Copied from PWUST_TOOL_FEED and modified for specific needs.
  -- 2020-04-03  D.Miron, S.Edgerly - revised to handle parts and tools
  -- 2020-05-15  D.Miron - Added insert for SFPL_ITEM_PROGRAM_DETAILS 'ANY' record
  -- 2020-06-02  D.Miron - Defect 1671: Insert PART_NO||PART_CHG in SFPL_ITEM_DESC_MASTER_ALL.EXTERNAL_PLM_NO for parts
  -- ****************************************************************************

  --DECLARE
  V_CON VARCHAR2(40);

  V_NEXTID SFPL_ITEM_DESC_MASTER_ALL.ITEM_ID%TYPE;
  V_EXTERNAL_PLM_NO SFPL_ITEM_DESC_MASTER_ALL.EXTERNAL_PLM_NO%TYPE;

  V_ERROR_MSG VARCHAR2(2000);

  PARENT_NOT_FOUND EXCEPTION;
  PRAGMA EXCEPTION_INIT(PARENT_NOT_FOUND, -2291);

BEGIN

  --create batch login
  SFCORE_LOGIN_USER(USER, '', 'FAT', '127.0.0.1', '', '', 'N', V_CON);
  SFCORE_SET_FLAG('CLIENT_SESSION');

  BEGIN

    --- UPDATE TOOLS
    FOR VITEMS IN (SELECT DISTINCT T1.PART_NO,
                                   T1.PART_CHG,
                                   T1.PART_TITLE,
                                   T1.PARENT_ENG_PART_NO,
                                   T1.PARENT_ENG_PART_CHG,
                                   T1.WORK_LOC,
                                   T1.UCF_ITEM_VCH1,
                                   T1.UCF_ITEM_VCH2,
                                   T1.UCF_ITEM_NUM1,
                                   T1.UCF_ITEM_FLAG1,
                                   T1.OBSOLETE_RECORD_FLAG,
                                   T1.PART_FLAG,
                                   T1.STANDARD_PART_FLAG,
                                   T1.PROGRAM,
                                   T1.STOCK_UOM,
                                   T1.BATCH_SIZE,
                                   T1.COMP_SERIAL_FLAG,
                                   T1.COMP_LOT_FLAG,
                                   T1.WO_SERIAL_FLAG,
                                   T1.WO_LOT_FLAG,
                                   T1.EXP_FLAG,
                                   T1.SPOOL_FLAG,
                                   T1.OPT_DC1_FLAG,
                                   T1.OPT_DC2_FLAG,
                                   T1.OPT_DC3_FLAG,
                                   T1.OPT_DC4_FLAG,
                                   T1.ITEM_TYPE,
                                   T1.ITEM_SUBTYPE
                     FROM PWUST_PART_TOOL_LOAD T1,
                          SFPL_ITEM_DESC_MASTER_ALL T2
                    WHERE T2.ITEM_TYPE = T1.ITEM_TYPE
                      AND T2.ITEM_SUBTYPE = T1.ITEM_SUBTYPE
                      AND T1.PART_NO = T2.PART_NO
                      AND (T1.PART_TITLE <> T2.PART_TITLE /*OR
                          T2.ITEM_SUBTYPE <> T1.ITEM_SUBTYPE*/)
                  ) LOOP
      BEGIN

        UPDATE SFPL_ITEM_DESC_MASTER_ALL
           SET PART_TITLE           = VITEMS.PART_TITLE,
               ITEM_SUBTYPE         = VITEMS.ITEM_SUBTYPE,
               PART_CHG             = VITEMS.PART_CHG,
               PARENT_ENG_PART_NO   = VITEMS.PARENT_ENG_PART_NO,
               PARENT_ENG_PART_CHG  = VITEMS.PARENT_ENG_PART_CHG,
               UCF_ITEM_VCH1        = VITEMS.UCF_ITEM_VCH1,
               UCF_ITEM_VCH2        = VITEMS.UCF_ITEM_VCH2,
               UCF_ITEM_NUM1        = VITEMS.UCF_ITEM_NUM1,
               UCF_ITEM_FLAG1       = VITEMS.UCF_ITEM_FLAG1,
               ITEM_TYPE            = VITEMS.ITEM_TYPE,
/*               ITEM_STATUS          = VITEMS.ITEM_STATUS, */
               OBSOLETE_RECORD_FLAG = VITEMS.OBSOLETE_RECORD_FLAG,
               PART_FLAG            = VITEMS.PART_FLAG,
               STANDARD_PART_FLAG   = VITEMS.STANDARD_PART_FLAG,
               UCF_ITEM_VCH5        = VITEMS.WORK_LOC,
               UPDT_USERID          = USER,
               TIME_STAMP           = SYSDATE,
               LAST_ACTION          = 'UPDATE'
         WHERE PART_NO = VITEMS.PART_NO
           AND ITEM_TYPE = VITEMS.ITEM_TYPE
           AND ITEM_SUBTYPE = VITEMS.ITEM_SUBTYPE;

      EXCEPTION
        WHEN OTHERS THEN
          V_ERROR_MSG := ('PWUST_PART_TOOL_FEED-SFPL_ITEM_DESC_MASTER_ALL: could not update part no ' ||
                         VITEMS.PART_NO || '. SQLERRM=' || SQLERRM);

          PWUST_ERROR_LOG_INS(NULL /*LOAD_ID*/,
                              'LOAD ' || VITEMS.ITEM_SUBTYPE /*TRANS_TYPE*/,
                              NULL /*RETURN_CODE*/,
                              NULL /*MSG_ID*/,
                              NULL /*KEY1*/,
                              NULL /* KEY2*/,
                              NULL /*KEY3*/,
                              V_ERROR_MSG /*MSG_TEXT*/,
                              NULL /*KEY4*/,
                              NULL /*KEY5*/,
                              NULL /*KEY6*/,
                              NULL /*KEY7*/,
                              NULL /*KEY8*/,
                              NULL /*KEY9*/,
                              NULL /*KEY10*/,
                              NULL /*KEY11*/,
                              NULL /*KEY12*/,
                              NULL /*KEY13*/,
                              NULL /*KEY14*/,
                              NULL /*KEY15*/,
                              NULL /*KEY16*/,
                              NULL /*KEY17*/,
                              NULL /*KEY18*/,
                              NULL /*KEY19*/,
                              NULL /*KEY20*/);
      END;

      BEGIN

          UPDATE SFPL_ITEM_PROGRAM_DETAILS
             SET PROGRAM          = VITEMS.PROGRAM,
              /*   LOCATION_ID      = (SELECT LOCATION_ID FROM SFFND_WORK_LOC_DEF WHERE WORK_LOC = VITEMS.WORK_LOC), */
                 STOCK_UOM        = VITEMS.STOCK_UOM,           /* VITEMS.STOCK_UOM, */
                 BATCH_SIZE       = VITEMS.BATCH_SIZE,
                 UPDT_USERID      = USER,
                 TIME_STAMP       = SYSDATE,
                 LAST_ACTION      = 'UPDATE',
                 COMP_SERIAL_FLAG = VITEMS.COMP_SERIAL_FLAG,
                 comp_lot_flag    = VITEMS.COMP_LOT_FLAG,
                 wo_serial_flag   = VITEMS.WO_SERIAL_FLAG,
                 wo_lot_flag      = VITEMS.WO_LOT_FLAG,
                 exp_flag         = VITEMS.EXP_FLAG,
                 spool_flag       = VITEMS.SPOOL_FLAG,
                 opt_dc1_flag     = VITEMS.OPT_DC1_FLAG,
                 opt_dc2_flag     = VITEMS.OPT_DC2_FLAG,
                 opt_dc3_flag     = VITEMS.OPT_DC3_FLAG,
                 opt_dc4_flag     = VITEMS.OPT_DC4_FLAG
           WHERE ITEM_ID = (SELECT ITEM_ID FROM SFPL_ITEM_DESC_MASTER_ALL
                             WHERE PART_NO = VITEMS.PART_NO
                               AND ITEM_TYPE = VITEMS.ITEM_TYPE
                               AND ITEM_SUBTYPE = VITEMS.ITEM_SUBTYPE)
               AND LOCATION_ID = (SELECT LOCATION_ID FROM SFFND_WORK_LOC_DEF WHERE WORK_LOC = VITEMS.WORK_LOC);

        EXCEPTION
          WHEN OTHERS THEN
            V_ERROR_MSG := ('PWUST_PART_TOOL_FEED-SFPL_ITEM_PROGRAM_DETAILS: could not update part no ' ||
                           VITEMS.PART_NO || '. SQLERRM=' || SQLERRM);

            PWUST_ERROR_LOG_INS(NULL /*LOAD_ID*/,
                                'LOAD ' || VITEMS.ITEM_SUBTYPE /*TRANS_TYPE*/,
                                NULL /*RETURN_CODE*/,
                                NULL /*MSG_ID*/,
                                NULL /*KEY1*/,
                                NULL /* KEY2*/,
                                NULL /*KEY3*/,
                                V_ERROR_MSG /*MSG_TEXT*/,
                                NULL /*KEY4*/,
                                NULL /*KEY5*/,
                                NULL /*KEY6*/,
                                NULL /*KEY7*/,
                                NULL /*KEY8*/,
                                NULL /*KEY9*/,
                                NULL /*KEY10*/,
                                NULL /*KEY11*/,
                                NULL /*KEY12*/,
                                NULL /*KEY13*/,
                                NULL /*KEY14*/,
                                NULL /*KEY15*/,
                                NULL /*KEY16*/,
                                NULL /*KEY17*/,
                                NULL /*KEY18*/,
                                NULL /*KEY19*/,
                                NULL /*KEY20*/);

      END;

    END LOOP;



            FOR VITEMS IN (SELECT DISTINCT T1.PART_NO,
                                     T1.PART_CHG,
                                     T1.PART_TITLE,
                                     T1.PARENT_ENG_PART_NO,
                                     T1.PARENT_ENG_PART_CHG,
                                     T1.WORK_LOC,
                                     T1.UCF_ITEM_VCH1,
                                     T1.UCF_ITEM_VCH2,
                                     T1.UCF_ITEM_NUM1,
                                     T1.UCF_ITEM_FLAG1,
                                     T1.OBSOLETE_RECORD_FLAG,
                                     T1.PART_FLAG,
                                     T1.STANDARD_PART_FLAG,
                                     T1.PROGRAM,
                                     T1.STOCK_UOM,
                                     T1.BATCH_SIZE,
                                     T1.COMP_SERIAL_FLAG,
                                     T1.COMP_LOT_FLAG,
                                     T1.WO_SERIAL_FLAG,
                                     T1.WO_LOT_FLAG,
                                     T1.EXP_FLAG,
                                     T1.SPOOL_FLAG,
                                     T1.OPT_DC1_FLAG,
                                     T1.OPT_DC2_FLAG,
                                     T1.OPT_DC3_FLAG,
                                     T1.OPT_DC4_FLAG,
                                     T1.ITEM_TYPE,
                                     T1.ITEM_SUBTYPE
                       FROM PWUST_PART_TOOL_LOAD T1,
                            SFFND_TOOL T2
                      WHERE T1.PART_NO = T2.TOOL_NO
                        AND T1.PART_CHG = T2.TOOL_CHG
                        AND (T1.PART_TITLE <> T2.TOOL_TITLE OR
                            T1.ITEM_SUBTYPE <> T2.ITEM_SUBTYPE)
                    ) LOOP

           IF VITEMS.ITEM_TYPE = 'TOOL' THEN

        BEGIN

          UPDATE SFFND_TOOL
             SET TOOL_TITLE   = VITEMS.PART_TITLE,
                 ITEM_SUBTYPE = VITEMS.ITEM_SUBTYPE,
                 UPDT_USERID  = USER,
                 TIME_STAMP   = SYSDATE,
                 LAST_ACTION  = 'UPDATE'
           WHERE TOOL_NO = VITEMS.PART_NO
             AND ITEM_TYPE = VITEMS.ITEM_TYPE
             AND ITEM_SUBTYPE = VITEMS.ITEM_SUBTYPE;

        EXCEPTION
          WHEN OTHERS THEN
            V_ERROR_MSG := ('PWUST_PART_TOOL_FEED-SFFND_TOOL: could not update tool no ' ||
                           VITEMS.PART_NO || '. SQLCODE=' || SQLCODE ||
                           'SQLERRM=' || SQLERRM);

            PWUST_ERROR_LOG_INS(NULL /*LOAD_ID*/,
                                'LOAD ' || VITEMS.ITEM_SUBTYPE /*TRANS_TYPE*/,
                                NULL /*RETURN_CODE*/,
                                NULL /*MSG_ID*/,
                                NULL /*KEY1*/,
                                NULL /* KEY2*/,
                                NULL /*KEY3*/,
                                V_ERROR_MSG /*MSG_TEXT*/,
                                NULL /*KEY4*/,
                                NULL /*KEY5*/,
                                NULL /*KEY6*/,
                                NULL /*KEY7*/,
                                NULL /*KEY8*/,
                                NULL /*KEY9*/,
                                NULL /*KEY10*/,
                                NULL /*KEY11*/,
                                NULL /*KEY12*/,
                                NULL /*KEY13*/,
                                NULL /*KEY14*/,
                                NULL /*KEY15*/,
                                NULL /*KEY16*/,
                                NULL /*KEY17*/,
                                NULL /*KEY18*/,
                                NULL /*KEY19*/,
                                NULL /*KEY20*/);

        END;

        END IF;

      END LOOP;

    V_NEXTID := ' ';

    -- INSERT TOOLS
    FOR VITEMS IN (SELECT DISTINCT T1.PART_NO,
                                   T1.PART_CHG,
                                   T1.PART_TITLE,
                                   T1.PARENT_ENG_PART_NO,
                                   T1.PARENT_ENG_PART_CHG,
                                   T1.WORK_LOC,
                                   T1.UCF_ITEM_VCH1,
                                   T1.UCF_ITEM_VCH2,
                                   T1.UCF_ITEM_NUM1,
                                   T1.UCF_ITEM_FLAG1,
                                   T1.OBSOLETE_RECORD_FLAG,
                                   T1.PART_FLAG,
                                   T1.STANDARD_PART_FLAG,
                                   T1.PROGRAM,
                                   T1.STOCK_UOM,
                                   T1.BATCH_SIZE,
                                   T1.COMP_SERIAL_FLAG,
                                   T1.COMP_LOT_FLAG,
                                   T1.WO_SERIAL_FLAG,
                                   T1.WO_LOT_FLAG,
                                   T1.EXP_FLAG,
                                   T1.SPOOL_FLAG,
                                   T1.OPT_DC1_FLAG,
                                   T1.OPT_DC2_FLAG,
                                   T1.OPT_DC3_FLAG,
                                   T1.OPT_DC4_FLAG,
                                   T1.ITEM_TYPE,
                                   T1.ITEM_SUBTYPE
                     FROM PWUST_PART_TOOL_LOAD T1
                    WHERE NOT EXISTS (SELECT NULL
                             FROM SFPL_ITEM_DESC_MASTER_ALL T2
                            WHERE T2.PART_NO = T1.PART_NO
                              AND T2.ITEM_TYPE = T1.ITEM_TYPE
                              AND T2.ITEM_SUBTYPE = T1.ITEM_SUBTYPE)) LOOP
      BEGIN

        IF V_NEXTID = ' ' THEN
          SELECT SFDB_GUID INTO V_NEXTID FROM DUAL;
        END IF;
        
        -- Defect 1671
        IF VITEMS.ITEM_TYPE = 'PART' AND VITEMS.ITEM_SUBTYPE = 'REPAIR' THEN
           V_EXTERNAL_PLM_NO := VITEMS.PART_NO || VITEMS.PART_CHG;
        ELSE
           V_EXTERNAL_PLM_NO := NULL;
        END IF;

        BEGIN

          INSERT INTO SFPL_ITEM_DESC_MASTER_ALL
            (ITEM_ID,
             PART_NO,
             PART_CHG,
             PART_TITLE,
             PARENT_ENG_PART_NO,
             PARENT_ENG_PART_CHG,
             UCF_ITEM_VCH1,
             UCF_ITEM_VCH2,
             UCF_ITEM_NUM1,
             UCF_ITEM_FLAG1,
             ITEM_TYPE,
             ITEM_SUBTYPE,
             ITEM_STATUS,
             sECURITY_GROUP,
             OBSOLETE_RECORD_FLAG,
             PART_FLAG,
             STANDARD_PART_FLAG,
             UCF_ITEM_VCH5,
             PHANTOM_KIT_FLAG,
             EXTERNAL_PLM_NO)
          VALUES
            (V_NEXTID,
             VITEMS.PART_NO,
             VITEMS.PART_CHG,
             VITEMS.PART_TITLE,
             VITEMS.PARENT_ENG_PART_NO,
             VITEMS.PARENT_ENG_PART_CHG,
             VITEMS.UCF_ITEM_VCH1,
             VITEMS.UCF_ITEM_VCH2,
             VITEMS.UCF_ITEM_NUM1,
             VITEMS.UCF_ITEM_FLAG1,
             VITEMS.ITEM_TYPE,
             VITEMS.ITEM_SUBTYPE,
             'COMPLETE',
             (SELECT SECURITY_GROUP FROM UTASGI_USER_SEC_GRP_XREF T5 WHERE T5.HR_LOC = VITEMS.WORK_LOC),
             VITEMS.OBSOLETE_RECORD_FLAG,
             VITEMS.PART_FLAG,
             VITEMS.STANDARD_PART_FLAG,
             VITEMS.WORK_LOC,
             'N',
             V_EXTERNAL_PLM_NO);

         EXCEPTION

          WHEN OTHERS THEN
            V_ERROR_MSG := ('PWUST_PART_TOOL_FEED-SFPL_ITEM_DESC_MASTER_ALL: could not insert part no ' ||
                           VITEMS.PART_NO || '. SQLERRM=' || SQLERRM);

            PWUST_ERROR_LOG_INS(NULL /*LOAD_ID*/,
                                'LOAD ' || VITEMS.ITEM_SUBTYPE /*TRANS_TYPE*/,
                                NULL /*RETURN_CODE*/,
                                NULL /*MSG_ID*/,
                                NULL /*KEY1*/,
                                NULL /* KEY2*/,
                                NULL /*KEY3*/,
                                V_ERROR_MSG /*MSG_TEXT*/,
                                NULL /*KEY4*/,
                                NULL /*KEY5*/,
                                NULL /*KEY6*/,
                                NULL /*KEY7*/,
                                NULL /*KEY8*/,
                                NULL /*KEY9*/,
                                NULL /*KEY10*/,
                                NULL /*KEY11*/,
                                NULL /*KEY12*/,
                                NULL /*KEY13*/,
                                NULL /*KEY14*/,
                                NULL /*KEY15*/,
                                NULL /*KEY16*/,
                                NULL /*KEY17*/,
                                NULL /*KEY18*/,
                                NULL /*KEY19*/,
                                NULL /*KEY20*/);

        END;

        BEGIN

          INSERT INTO SFPL_ITEM_PROGRAM_DETAILS
            (ITEM_ID,
             PROGRAM,
             LOCATION_ID, -- ???? plant vs location_id    need to get the location ID from plant for insert  yes or no???????
             STOCK_UOM,
             BATCH_SIZE,
             UPDT_USERID,
             TIME_STAMP,
             LAST_ACTION,
             UTILIZATION_RULE,
             COMP_SERIAL_FLAG,
             comp_lot_flag,
              wo_serial_flag,
              wo_lot_flag,
              exp_flag,
              spool_flag,
              opt_dc1_flag,
              opt_dc2_flag,
              opt_dc3_flag,
              opt_dc4_flag)
          VALUES
            (V_NEXTID,
             VITEMS.PROGRAM,   /* 'ANY', */
             (SELECT LOCATION_ID FROM SFFND_WORK_LOC_DEF WHERE WORK_LOC = VITEMS.WORK_LOC),
             VITEMS.STOCK_UOM,       /* VITEMS.STOCK_UOM, */
             VITEMS.BATCH_SIZE,
             USER,
             SYSDATE,
             'INSERT',
             'ANY',
             VITEMS.COMP_SERIAL_FLAG,
             VITEMS.COMP_LOT_FLAG,
             VITEMS.WO_SERIAL_FLAG,
             VITEMS.WO_LOT_FLAG,
             VITEMS.EXP_FLAG,
             VITEMS.SPOOL_FLAG,
             VITEMS.OPT_DC1_FLAG,
             VITEMS.OPT_DC2_FLAG,
             VITEMS.OPT_DC3_FLAG,
             VITEMS.OPT_DC4_FLAG);

          V_NEXTID := ' ';

        EXCEPTION
          WHEN PARENT_NOT_FOUND THEN
            CONTINUE;

          WHEN OTHERS THEN
            V_ERROR_MSG := ('PWUST_PART_TOOL_FEED-SFPL_ITEM_PROGRAM_DETAILS: could not insert part no ' ||
                           VITEMS.PART_NO || '. SQLERRM=' || SQLERRM);

            PWUST_ERROR_LOG_INS(NULL /*LOAD_ID*/,
                                'LOAD ' || VITEMS.ITEM_SUBTYPE /*TRANS_TYPE*/,
                                NULL /*RETURN_CODE*/,
                                NULL /*MSG_ID*/,
                                NULL /*KEY1*/,
                                NULL /* KEY2*/,
                                NULL /*KEY3*/,
                                V_ERROR_MSG /*MSG_TEXT*/,
                                NULL /*KEY4*/,
                                NULL /*KEY5*/,
                                NULL /*KEY6*/,
                                NULL /*KEY7*/,
                                NULL /*KEY8*/,
                                NULL /*KEY9*/,
                                NULL /*KEY10*/,
                                NULL /*KEY11*/,
                                NULL /*KEY12*/,
                                NULL /*KEY13*/,
                                NULL /*KEY14*/,
                                NULL /*KEY15*/,
                                NULL /*KEY16*/,
                                NULL /*KEY17*/,
                                NULL /*KEY18*/,
                                NULL /*KEY19*/,
                                NULL /*KEY20*/);

        END;

      END;

    END LOOP;

        FOR VITEMS IN (SELECT DISTINCT T1.PART_NO,
                                   T1.PART_CHG,
                                   T1.PART_TITLE,
                                   T1.PARENT_ENG_PART_NO,
                                   T1.PARENT_ENG_PART_CHG,
                                   T1.WORK_LOC,
                                   T1.UCF_ITEM_VCH1,
                                   T1.UCF_ITEM_VCH2,
                                   T1.UCF_ITEM_NUM1,
                                   T1.UCF_ITEM_FLAG1,
                                   T1.OBSOLETE_RECORD_FLAG,
                                   T1.PART_FLAG,
                                   T1.STANDARD_PART_FLAG,
                                   T1.PROGRAM,
                                   T1.STOCK_UOM,
                                   T1.BATCH_SIZE,
                                   T1.COMP_SERIAL_FLAG,
                                   T1.COMP_LOT_FLAG,
                                   T1.WO_SERIAL_FLAG,
                                   T1.WO_LOT_FLAG,
                                   T1.EXP_FLAG,
                                   T1.SPOOL_FLAG,
                                   T1.OPT_DC1_FLAG,
                                   T1.OPT_DC2_FLAG,
                                   T1.OPT_DC3_FLAG,
                                   T1.OPT_DC4_FLAG,
                                   T1.ITEM_TYPE,
                                   T1.ITEM_SUBTYPE
                     FROM PWUST_PART_TOOL_LOAD T1,
                          SFPL_ITEM_DESC_MASTER_ALL T2
                    WHERE T2.ITEM_TYPE = T1.ITEM_TYPE
                      AND T2.ITEM_SUBTYPE = T1.ITEM_SUBTYPE
                      AND T1.PART_NO = T2.PART_NO
                  ) LOOP

      BEGIN

          INSERT INTO SFPL_ITEM_PROGRAM_DETAILS
            (ITEM_ID,
             PROGRAM,
             LOCATION_ID,
             STOCK_UOM,
             BATCH_SIZE,
             UPDT_USERID,
             TIME_STAMP,
             LAST_ACTION,
             UTILIZATION_RULE,
             COMP_SERIAL_FLAG,
             comp_lot_flag,
              wo_serial_flag,
              wo_lot_flag,
              exp_flag,
              spool_flag,
              opt_dc1_flag,
              opt_dc2_flag,
              opt_dc3_flag,
              opt_dc4_flag)
          VALUES
            ((SELECT ITEM_ID FROM SFPL_ITEM_DESC_MASTER_ALL
                             WHERE PART_NO = VITEMS.PART_NO
                               AND ITEM_TYPE = VITEMS.ITEM_TYPE),
             'ANY',
             'ANY',
             VITEMS.STOCK_UOM,
             VITEMS.BATCH_SIZE,
             USER,
             SYSDATE,
             'INSERT',
             'ANY',
             VITEMS.COMP_SERIAL_FLAG,
             VITEMS.COMP_LOT_FLAG,
             VITEMS.WO_SERIAL_FLAG,
             VITEMS.WO_LOT_FLAG,
             VITEMS.EXP_FLAG,
             VITEMS.SPOOL_FLAG,
             VITEMS.OPT_DC1_FLAG,
             VITEMS.OPT_DC2_FLAG,
             VITEMS.OPT_DC3_FLAG,
             VITEMS.OPT_DC4_FLAG);

        EXCEPTION
          WHEN PARENT_NOT_FOUND THEN
            CONTINUE;

          WHEN OTHERS THEN
            V_ERROR_MSG := ('PWUST_PART_TOOL_FEED-SFPL_ITEM_PROGRAM_DETAILS: could not insert part no ANY record ' ||
                           VITEMS.PART_NO || '. SQLERRM=' || SQLERRM);

            PWUST_ERROR_LOG_INS(NULL /*LOAD_ID*/,
                                'LOAD ' || VITEMS.ITEM_SUBTYPE /*TRANS_TYPE*/,
                                NULL /*RETURN_CODE*/,
                                NULL /*MSG_ID*/,
                                NULL /*KEY1*/,
                                NULL /* KEY2*/,
                                NULL /*KEY3*/,
                                V_ERROR_MSG /*MSG_TEXT*/,
                                NULL /*KEY4*/,
                                NULL /*KEY5*/,
                                NULL /*KEY6*/,
                                NULL /*KEY7*/,
                                NULL /*KEY8*/,
                                NULL /*KEY9*/,
                                NULL /*KEY10*/,
                                NULL /*KEY11*/,
                                NULL /*KEY12*/,
                                NULL /*KEY13*/,
                                NULL /*KEY14*/,
                                NULL /*KEY15*/,
                                NULL /*KEY16*/,
                                NULL /*KEY17*/,
                                NULL /*KEY18*/,
                                NULL /*KEY19*/,
                                NULL /*KEY20*/);

        END;

      END LOOP;

            FOR VITEMS IN (SELECT DISTINCT T1.PART_NO,
                                     T1.PART_CHG,
                                     T1.PART_TITLE,
                                     T1.PARENT_ENG_PART_NO,
                                     T1.PARENT_ENG_PART_CHG,
                                     T1.WORK_LOC,
                                     T1.UCF_ITEM_VCH1,
                                     T1.UCF_ITEM_VCH2,
                                     T1.UCF_ITEM_NUM1,
                                     T1.UCF_ITEM_FLAG1,
                                     T1.OBSOLETE_RECORD_FLAG,
                                     T1.PART_FLAG,
                                     T1.STANDARD_PART_FLAG,
                                     T1.PROGRAM,
                                     T1.STOCK_UOM,
                                     T1.BATCH_SIZE,
                                     T1.COMP_SERIAL_FLAG,
                                     T1.COMP_LOT_FLAG,
                                     T1.WO_SERIAL_FLAG,
                                     T1.WO_LOT_FLAG,
                                     T1.EXP_FLAG,
                                     T1.SPOOL_FLAG,
                                     T1.OPT_DC1_FLAG,
                                     T1.OPT_DC2_FLAG,
                                     T1.OPT_DC3_FLAG,
                                     T1.OPT_DC4_FLAG,
                                     T1.ITEM_TYPE,
                                     T1.ITEM_SUBTYPE
                       FROM PWUST_PART_TOOL_LOAD T1,
                            SFFND_TOOL T2
                      WHERE NOT EXISTS (SELECT NULL
                               FROM SFFND_TOOL T2
                              WHERE T2.TOOL_NO = T1.PART_NO
                                AND T2.ITEM_TYPE = T1.ITEM_TYPE
                                AND T2.ITEM_SUBTYPE = T1.ITEM_SUBTYPE)) LOOP

        IF VITEMS.ITEM_TYPE = 'TOOL' THEN

        BEGIN

          INSERT INTO SFFND_TOOL
            (TOOL_NO,
             TOOL_CHG,
             TOOL_TITLE,
             TOOL_CALIBRATE_DAYS,
             TOOL_CALIBRATION_DAYS_FREQ,
             ITEM_TYPE,
             ITEM_SUBTYPE)
          VALUES
            (VITEMS.PART_NO,
             'N/A',
             VITEMS.PART_TITLE,
             'Y',
             36499,
             VITEMS.ITEM_TYPE,
             VITEMS.ITEM_SUBTYPE);

        EXCEPTION
          WHEN DUP_VAL_ON_INDEX THEN
            CONTINUE;

          WHEN OTHERS THEN
            V_ERROR_MSG := ('PWUST_PART_TOOL_FEED-SFFND_TOOL: could not insert tool no ' ||
                           VITEMS.PART_NO || '. SQLERRM=' || SQLERRM);

            PWUST_ERROR_LOG_INS(NULL /*LOAD_ID*/,
                                'LOAD ' || VITEMS.ITEM_SUBTYPE /*TRANS_TYPE*/,
                                NULL /*RETURN_CODE*/,
                                NULL /*MSG_ID*/,
                                NULL /*KEY1*/,
                                NULL /* KEY2*/,
                                NULL /*KEY3*/,
                                V_ERROR_MSG /*MSG_TEXT*/,
                                NULL /*KEY4*/,
                                NULL /*KEY5*/,
                                NULL /*KEY6*/,
                                NULL /*KEY7*/,
                                NULL /*KEY8*/,
                                NULL /*KEY9*/,
                                NULL /*KEY10*/,
                                NULL /*KEY11*/,
                                NULL /*KEY12*/,
                                NULL /*KEY13*/,
                                NULL /*KEY14*/,
                                NULL /*KEY15*/,
                                NULL /*KEY16*/,
                                NULL /*KEY17*/,
                                NULL /*KEY18*/,
                                NULL /*KEY19*/,
                                NULL /*KEY20*/);

        END;

        END IF;

      END LOOP;


      FOR VITEMS IN (SELECT DISTINCT T1.PART_NO,(SELECT SECURITY_GROUP FROM UTASGI_USER_SEC_GRP_XREF T5 WHERE T5.HR_LOC = T1.WORK_LOC) AS SECURITY_GROUP,
                     T1.ITEM_SUBTYPE
                       FROM PWUST_PART_TOOL_LOAD T1
                      WHERE NOT EXISTS (SELECT NULL
                               FROM sfpl_item_desc_sec_grp T4, UTASGI_USER_SEC_GRP_XREF T3
                              WHERE T4.PART_NO = T1.PART_NO
                              AND T4.SECURITY_GROUP = T3.SECURITY_GROUP
                              AND T3.HR_LOC = T1.WORK_LOC)) LOOP
      BEGIN

        INSERT INTO sfpl_item_desc_sec_grp
          (PART_NO,
           SECURITY_GROUP)
        VALUES
          (VITEMS.PART_NO,
           VITEMS.SECURITY_GROUP);

      EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
          CONTINUE;

        WHEN OTHERS THEN
          V_ERROR_MSG := ('PWUST_PART_TOOL_FEED-SFFND_TOOL: could not insert security group for part no ' ||
                         VITEMS.PART_NO || ' Security Group ' ||  VITEMS.SECURITY_GROUP || '. SQLERRM=' || SQLERRM);

          PWUST_ERROR_LOG_INS(NULL /*LOAD_ID*/,
                              'LOAD ' || VITEMS.ITEM_SUBTYPE /*TRANS_TYPE*/,
                              NULL /*RETURN_CODE*/,
                              NULL /*MSG_ID*/,
                              NULL /*KEY1*/,
                              NULL /* KEY2*/,
                              NULL /*KEY3*/,
                              V_ERROR_MSG /*MSG_TEXT*/,
                              NULL /*KEY4*/,
                              NULL /*KEY5*/,
                              NULL /*KEY6*/,
                              NULL /*KEY7*/,
                              NULL /*KEY8*/,
                              NULL /*KEY9*/,
                              NULL /*KEY10*/,
                              NULL /*KEY11*/,
                              NULL /*KEY12*/,
                              NULL /*KEY13*/,
                              NULL /*KEY14*/,
                              NULL /*KEY15*/,
                              NULL /*KEY16*/,
                              NULL /*KEY17*/,
                              NULL /*KEY18*/,
                              NULL /*KEY19*/,
                              NULL /*KEY20*/);

      END;
    END LOOP;

  END;

  COMMIT;

END PWUST_PART_TOOL_FEED;
/
grant execute on PWUST_PART_TOOL_FEED to SF$CONNECTION_POOL;


