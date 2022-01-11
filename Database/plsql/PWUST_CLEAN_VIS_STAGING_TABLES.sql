CREATE OR REPLACE PROCEDURE PWUST_CLEAN_VIS_STAGING_TABLES(VI_PART_NO             VARCHAR2,
                                                           VI_CLEAN_PLAN_TABL_FLG VARCHAR2,
                                                           VI_CLEAN_LOG_TABL_FLG  VARCHAR2,
                                                           VI_CLEAN_STD_TABLS_FLG VARCHAR2,
                                                           VI_PURGE_ALL_VIS_FLG   VARCHAR2) AS
  -- *************************************************************************************
  -- Date Created:   2018-03-27
  -- Created By:     Rusty Cannon
  -- DER:            SMRO_CV_202
  -- Description:    Clean VIS tables related to G5 to G8 plan migration
  --
  -- Modification History:
  -- DATE          AUTHOR          DEFECT   DESCRIPTION
  -- 2018-03-27    R. Cannon       --       Initial Release (NZ Check-In)
  -- 2018-08-28    R. Cannon       --       OKC Development Check-In
  -- 2019-05-22    R. Cannon       --       Include/exlude standard tables during clean
  -- *************************************************************************************

  V_PLAN_ID   VARCHAR2(40);
  V_PLAN_FLAG VARCHAR2(1);
  V_LOG_FLAG  VARCHAR2(1);
  V_STD_FLAG  VARCHAR2(1);
  V_PURGE_ALL VARCHAR2(1);

  CURSOR C1 IS
    SELECT PLAN_ID FROM VIS_PLAN T WHERE T.PART_NO = TRIM(VI_PART_NO);

BEGIN

  /*************
    Validations
   *************/
  --Purge ALL records from VIS tables flag
  BEGIN
    SELECT UPPER(SUBSTR(VI_PURGE_ALL_VIS_FLG, 1, 1)) INTO V_PURGE_ALL FROM DUAL;

    IF V_PURGE_ALL IS NULL THEN
      SFCORE_RAISE_ERROR('MFI_20', 'PURGE ALL flag cannot be empty. Please use Y/N');
    END IF;

    IF V_PURGE_ALL NOT IN ('Y', 'N') THEN
      SFCORE_RAISE_ERROR('MFI_20', 'Invalid PURGE ALL flag. Please use Y/N');
    END IF;
  END;

  --Clean VIS_PLAN flag
  BEGIN
    SELECT UPPER(SUBSTR(VI_CLEAN_PLAN_TABL_FLG, 1, 1)) INTO V_PLAN_FLAG FROM DUAL;

    IF V_PLAN_FLAG IS NULL THEN
      SFCORE_RAISE_ERROR('MFI_20', 'PLAN flag cannot be empty. Please use Y/N');
    END IF;

    IF V_PLAN_FLAG NOT IN ('Y', 'N') THEN
      SFCORE_RAISE_ERROR('MFI_20', 'Invalid PLAN flag. Please use Y/N');
    END IF;
  END;

  --Clean VIS_MIG_ERROR_G8 flag
  BEGIN
    SELECT UPPER(SUBSTR(VI_CLEAN_LOG_TABL_FLG, 1, 1)) INTO V_LOG_FLAG FROM DUAL;

    IF V_LOG_FLAG IS NULL THEN
      SFCORE_RAISE_ERROR('MFI_20', 'LOG flag cannot be empty. Please use Y/N');
    END IF;

    IF V_LOG_FLAG NOT IN ('Y', 'N') THEN
      SFCORE_RAISE_ERROR('MFI_20', 'Invalid LOG flag. Please use Y/N');
    END IF;
  END;

  --Clean Standard Txt/Op tables flag
  BEGIN
    SELECT UPPER(SUBSTR(VI_CLEAN_STD_TABLS_FLG, 1, 1)) INTO V_STD_FLAG FROM DUAL;

    IF V_STD_FLAG IS NULL THEN
      SFCORE_RAISE_ERROR('MFI_20', 'STD flag cannot be empty. Please use Y/N');
    END IF;

    IF V_STD_FLAG NOT IN ('Y', 'N') THEN
      SFCORE_RAISE_ERROR('MFI_20', 'Invalid STD flag. Please use Y/N');
    END IF;
  END;

  /**************************************************************************
    Purge ALL records from VIS tables excluding tables marked for exclusion
  ***************************************************************************/
  IF V_PURGE_ALL IN ('Y') THEN

    IF VI_PART_NO IS NOT NULL THEN
      SFCORE_RAISE_ERROR('MFI_20',
                         'Part number must be blank to purge ALL records from VIS tables');
    END IF;

    BEGIN
      IF V_LOG_FLAG IN ('Y') THEN
        DELETE FROM VIS_MIG_ERROR_G8;
      END IF;

      IF V_STD_FLAG IN ('Y') THEN
        DELETE FROM VIS_LIB_STANDARD_TEXT;
        DELETE FROM VIS_STANDARD_OPERATION;
      END IF;

      DELETE FROM VIS_LEGACY_OPERATION;
      DELETE FROM VIS_LEGACY_PLAN;
      DELETE FROM VIS_LEGACY_SUBJECT;
      DELETE FROM VIS_GRAPHIC;
      DELETE FROM VIS_PLAN_MODEL;
      DELETE FROM VIS_STEP;
      DELETE FROM VIS_SUBJECT_CUSTOMER_EXCLUSION;
      DELETE FROM VIS_SUBJECT_CUSTOMER_INCLUSION;
      DELETE FROM VIS_SUBJECT_SUB_NETWORK;
      DELETE FROM VIS_SUBJECT_SUPERIOR_NETWORK;
      DELETE FROM VIS_SUBJECT_WORKSCOPE;
      DELETE FROM VIS_OPERATION_SUBJECT;
      DELETE FROM VIS_SUBJECT;
      DELETE FROM VIS_OPERATION;

      IF V_PLAN_FLAG IN ('Y') THEN
        DELETE FROM VIS_PLAN;
      END IF;

      COMMIT;

    EXCEPTION
      WHEN OTHERS THEN
        SFCORE_RAISE_ERROR('MFI_20', 'Error purging all VIS tables');
    END;

  /**************************************************************************
    Clean all records for a particular part number from VIS tables excluding
    tables marked for exclusion
   **************************************************************************/
  ELSE

   --Part Number Validation
    BEGIN
      SELECT PLAN_ID INTO V_PLAN_ID FROM VIS_PLAN T WHERE T.PART_NO = TRIM(VI_PART_NO);
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        SFCORE_RAISE_ERROR('MFI_20', 'part_no=' || VI_PART_NO || ' not found in VIS_PLAN');
      WHEN TOO_MANY_ROWS THEN
        NULL; --Multiple planids found for given part number. Ignore.
      WHEN VALUE_ERROR THEN
        SFCORE_RAISE_ERROR('MFI_20', 'Invalid part_no');
    END;

   --Standard Text/Operation Validation
    IF V_STD_FLAG IN ('Y') THEN
      SFCORE_RAISE_ERROR('MFI_20',
                         'Cannot clear standard tables when clearing single part number');
    END IF;

    --Do the clean (ALL planids associated with requested part number included)
    BEGIN

      FOR C1REC IN C1
      LOOP

       IF V_LOG_FLAG IN ('Y') THEN
          DELETE FROM VIS_MIG_ERROR_G8 T WHERE T.PLAN_ID = C1REC.PLAN_ID;
        END IF;

        DELETE FROM VIS_LEGACY_OPERATION T
         WHERE T.OPER_KEY IN
               (SELECT O.OPER_KEY FROM VIS_OPERATION O WHERE O.PLAN_ID = C1REC.PLAN_ID);
        DELETE FROM VIS_LEGACY_PLAN T WHERE T.PLAN_ID = C1REC.PLAN_ID;
        DELETE FROM VIS_LEGACY_SUBJECT T
         WHERE T.SUBJECT_ID IN
               (SELECT S.SUBJECT_ID FROM VIS_SUBJECT S WHERE S.PLAN_ID = C1REC.PLAN_ID);
        --VIS_GRAPHIC -- Library item (other plans might be using)
        --VIS_LIB_STANDARD_TEXT -- Library item (other plans might be using)
        --VIS_STANDARD_OPERATION -- Library item (other plans might be using)
        DELETE FROM VIS_PLAN_MODEL T WHERE T.PLAN_ID = C1REC.PLAN_ID;
        DELETE FROM VIS_STEP T WHERE T.PLAN_ID = C1REC.PLAN_ID;
        DELETE FROM VIS_SUBJECT_CUSTOMER_EXCLUSION T WHERE T.PLAN_ID = C1REC.PLAN_ID;
        DELETE FROM VIS_SUBJECT_CUSTOMER_INCLUSION T WHERE T.PLAN_ID = C1REC.PLAN_ID;
        DELETE FROM VIS_SUBJECT_SUB_NETWORK T WHERE T.PLAN_ID = C1REC.PLAN_ID;
        DELETE FROM VIS_SUBJECT_SUPERIOR_NETWORK T WHERE T.PLAN_ID = C1REC.PLAN_ID;
        DELETE FROM VIS_SUBJECT_WORKSCOPE T WHERE T.PLAN_ID = C1REC.PLAN_ID;
        DELETE FROM VIS_OPERATION_SUBJECT T WHERE T.PLAN_ID = C1REC.PLAN_ID;
        DELETE FROM VIS_SUBJECT T WHERE T.PLAN_ID = C1REC.PLAN_ID;
        DELETE FROM VIS_OPERATION T WHERE T.PLAN_ID = C1REC.PLAN_ID;

        IF V_PLAN_FLAG IN ('Y') THEN
          DELETE FROM VIS_PLAN T WHERE T.PLAN_ID = C1REC.PLAN_ID;
        END IF;
        COMMIT;

      END LOOP;

    EXCEPTION
      WHEN OTHERS THEN
        SFCORE_RAISE_ERROR('MFI_20', 'Error on VIS table delete for PART');
    END;

  END IF;

END;
/