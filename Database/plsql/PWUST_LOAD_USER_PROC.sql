-------------------------------------------------
-- Export file for user SFMFG                  --
-- Created by c079222 on 2/11/2020, 5:52:23 PM --
-------------------------------------------------

spool PWUST_LOAD_USER_PROC.log

prompt
prompt Creating procedure PWUST_LOAD_USER_PROC
prompt =======================================
prompt
CREATE OR REPLACE PROCEDURE PWUST_LOAD_USER_PROC AS
  -- ********************************************************************************
  -- Date Created:   2018-08-07
  -- Created By:     Nazaire Gnassounou
  -- DER:            SMRO_USR_206
  -- Description:    Load new users, assign them a security group and role(s)
  --                 See Java code for how in-application works: [PWMRO-Interfaces]
  --                 com.pw.solumina.sffnd.application.impl.UserImplPW.insertUser()
  --
  -- Modification History:
  -- DATE          AUTHOR          DEFECT   DESCRIPTION
  -- 2018-08-07    N. Gnassounou   --       Initial Release
  -- 2019-01-18    R. Cannon       --       Insert statements modified to match Java
  -- 2019-02-04    R. Cannon       --       Error logging
  -- 2020-02-11    D. Miron        --       Added BADGE_ID to load and changed code for loading roles
  -- *********************************************************************************

  V_LOCATION_ID    SFFND_WORK_LOC_DEF.LOCATION_ID%TYPE;
  V_ID_CNT         NUMBER(1);
  V_USER_NO_LABOR  SFFND_USER.UCF_USER_FLAG2%TYPE;
  V_USER_SUCCESS   BOOLEAN;
  V_SECURITY_GROUP UTASGI_USER_SEC_GRP_XREF.SECURITY_GROUP%TYPE;
  V_ERROR_MSG      PWUST_LOAD_USER_LOG.ERROR_TEXT%TYPE;

  CURSOR C_USERS IS
    SELECT UPPER(USERID) AS USERID, LAST_NAME, FIRST_NAME, FULL_NAME, WORK_LOC, SHIFT,
           PAYROLL_ID, EMAIL_ADDRESS, UPPER(NVL(USER_NO_LABOR, 'N')) AS USER_NO_LABOR,
           BADGE_ID
      FROM PWUST_LOAD_USER_STAGE;

  --Delimit role column by commas (ignore white space)
  CURSOR C_ROLES IS
     SELECT t.userid, t.last_name, t.first_name, t.full_name, role
       FROM pwust_load_user_stage t
      OUTER APPLY (SELECT TRIM(role) AS role
                     FROM JSON_TABLE(REPLACE(JSON_ARRAY(t.roles), ',', '","'),
                                     '$[*]'
                                     COLUMNS(role VARCHAR2(4000) PATH '$'))) s;
BEGIN

  FOR U IN C_USERS
  LOOP

    V_USER_SUCCESS := TRUE;

    --Validate user home work location
    BEGIN
      SELECT LOCATION_ID
        INTO V_LOCATION_ID
        FROM SFFND_WORK_LOC_DEF T
       WHERE T.WORK_LOC = U.WORK_LOC;

    EXCEPTION
      WHEN NO_DATA_FOUND THEN

        V_LOCATION_ID := 'INVALID_LOC';

        INSERT INTO PWUST_LOAD_USER_LOG
          (USERID, USER_NAME, ERROR_TYPE, ERR_SRC_TABLE, TIME_STAMP, ERROR_TEXT)
        VALUES
          (U.USERID, NVL(U.FULL_NAME, U.FIRST_NAME || ' ' || U.LAST_NAME),
           'INVALID_WORK_LOC', 'SFFND_WORK_LOC_DEF', SYSDATE,
           'Work location ' || U.WORK_LOC ||
            ' not found on definition table. Further processing of user terminated.');
    END;

    --Stop processing user if requested location is invalid
    IF V_LOCATION_ID <> 'INVALID_LOC' THEN

      --Check if user already exists
      SELECT COUNT(*) INTO V_ID_CNT FROM SFCORE_USER T WHERE T.USERID = U.USERID;

      IF V_ID_CNT > 0 THEN

        INSERT INTO PWUST_LOAD_USER_LOG
          (USERID, USER_NAME, ERROR_TYPE, ERR_SRC_TABLE, TIME_STAMP, ERROR_TEXT)
        VALUES
          (U.USERID, NVL(U.FULL_NAME, U.FIRST_NAME || ' ' || U.LAST_NAME),
           'DUPLICATE_USER', 'SFCORE_USER', SYSDATE, 'User already exists');
           
    -- Update badge id 
    BEGIN
      UPDATE SFFND_USER
         SET UCF_USER_VCH1 = U.BADGE_ID
       WHERE USERID = U.USERID;
    EXCEPTION
      WHEN others THEN
        INSERT INTO PWUST_LOAD_USER_LOG
          (USERID,
           USER_NAME,
           ERROR_TYPE,
           ERR_SRC_TABLE,
           TIME_STAMP,
           ERROR_TEXT)
        VALUES
          (U.USERID,
           NVL(U.FULL_NAME, U.FIRST_NAME || ' ' || U.LAST_NAME),
           'UPDATE ERROR',
           'SFFND_USER.UCF_USER_VCH1',
           SYSDATE,
           'Badge Id not updated: ' || U.BADGE_ID);
    END;

        /********************
             INSERT USER
        *********************/
      ELSE
        BEGIN
          --Write to SFCORE_USER
          SFCORE_CREATE_USER('SFMFG', U.USERID, NULL, NULL, 'NORMAL');

          --Validate USER_NO_LABOR flag
          IF U.USER_NO_LABOR IN ('N', 'Y') THEN
            V_USER_NO_LABOR := U.USER_NO_LABOR;
          ELSE
            V_USER_NO_LABOR := 'N';

            INSERT INTO PWUST_LOAD_USER_LOG
              (USERID, USER_NAME, ERROR_TYPE, ERR_SRC_TABLE, TIME_STAMP, ERROR_TEXT)
            VALUES
              (U.USERID, NVL(U.FULL_NAME, U.FIRST_NAME || ' ' || U.LAST_NAME),
               'LABOR_FLAG_WARNING', 'SFFND_USER', SYSDATE,
               'Requested "User No Labor" flag is a non-(Y/N) value. Defaulted to "N"');
          END IF;

          INSERT INTO SFFND_USER
            (USERID, UPDT_USERID, TIME_STAMP, LAST_ACTION, LAST_NAME, FIRST_NAME,
             MIDDLE_NAME, FULL_NAME, NAME_SUFFIX, LOCATION_ID, DEPARTMENT_ID, CENTER_ID,
             SHIFT, PAYROLL_ID, OBSOLETE_RECORD_FLAG, UCF_USER_VCH1, UCF_USER_VCH2,
             UCF_USER_VCH3, UCF_USER_VCH4, USER_CLASS, EMAIL_ADDRESS, USER_TYPE,
             LABOR_TYPE, UCF_USER_VCH5, UCF_USER_VCH6, UCF_USER_VCH7, UCF_USER_VCH8,
             UCF_USER_VCH9, UCF_USER_VCH10, UCF_USER_VCH11, UCF_USER_VCH12, UCF_USER_VCH13,
             UCF_USER_VCH14, UCF_USER_VCH15, UCF_USER_NUM1, UCF_USER_NUM2, UCF_USER_NUM3,
             UCF_USER_NUM4, UCF_USER_NUM5, UCF_USER_DATE1, UCF_USER_DATE2, UCF_USER_DATE3,
             UCF_USER_DATE4, UCF_USER_DATE5, UCF_USER_FLAG1, UCF_USER_FLAG2,
             UCF_USER_FLAG3, UCF_USER_FLAG4, UCF_USER_FLAG5, UCF_USER_VCH255_1,
             UCF_USER_VCH255_2, UCF_USER_VCH255_3, UCF_USER_VCH4000_1, UCF_USER_VCH4000_2,
             INCLUDE_ALL_SUPPLIERS, USER_DEFINITION, LAST_EYE_EXAM_DATE, MI_USER_FLAG)
          VALUES
            (U.USERID, 'SFMFG', SYSDATE, 'INSERTED', U.LAST_NAME, U.FIRST_NAME,
            NULL /*MIDDLE_NAME*/, U.FULL_NAME, NULL /*NAME_SUFFIX*/, V_LOCATION_ID,
            NULL /*DEPARTMENT_ID*/, NULL /*CENTER_ID*/, U.SHIFT, U.PAYROLL_ID,
            'N' /*OBSOLETE_RECORD_FLAG*/, U.BADGE_ID /*UCF_USER_VCH1*/, NULL /*UCF_USER_VCH2*/,
             NULL /*UCF_USER_VCH3*/, NULL /*UCF_USER_VCH4*/, NULL /*USER_CLASS*/,
             U.EMAIL_ADDRESS, '@IBASET' /*USER_TYPE*/, NULL /*LABOR_TYPE*/, NULL/*UCF_USER_VCH5*/,
             NULL /*UCF_USER_VCH6*/, NULL /*UCF_USER_VCH7*/, NULL /*UCF_USER_VCH8*/,
             NULL /*UCF_USER_VCH9*/, NULL /*UCF_USER_VCH10*/, NULL /*UCF_USER_VCH11*/,
             NULL /*UCF_USER_VCH12*/, NULL /*UCF_USER_VCH13*/, NULL /*UCF_USER_VCH14*/,
             NULL /*UCF_USER_VCH15*/, NULL /*UCF_USER_NUM1*/, NULL /*UCF_USER_NUM2*/,
             NULL /*UCF_USER_NUM3*/, NULL /*UCF_USER_NUM4*/, NULL /*UCF_USER_NUM5*/,
             NULL /*UCF_USER_DATE1 (Password Last Changed)*/, NULL /*UCF_USER_DATE2*/,
             NULL /*UCF_USER_DATE3*/, NULL /*UCF_USER_DATE4*/, NULL /*UCF_USER_DATE5*/,
             NULL /*UCF_USER_FLAG1*/, V_USER_NO_LABOR /*UCF_USER_FLAG2*/, NULL /*UCF_USER_FLAG3*/,
             NULL /*UCF_USER_FLAG4*/, NULL /*UCF_USER_FLAG5*/, NULL /*UCF_USER_VCH255_1*/,
             NULL /*UCF_USER_VCH255_2*/, NULL /*UCF_USER_VCH255_3*/, NULL /*UCF_USER_VCH4000_1*/,
             NULL /*UCF_USER_VCH4000_2*/, 'N' /*INCLUDE_ALL_SUPPLIERS*/,
             'User' /*USER_DEFINITION*/, NULL /*LAST_EYE_EXAM_DATE*/, 'N' /*MI_USER_FLAG*/);

          INSERT INTO SFFND_USER_WORK_CENTERS
            (USERID, ASGND_LOCATION_ID, ASGND_DEPARTMENT_ID, ASGND_CENTER_ID, SHIFT,
             UPDT_USERID, TIME_STAMP, LAST_ACTION)
          VALUES
            (U.USERID, V_LOCATION_ID, NULL, NULL, U.SHIFT, 'SFMFG', SYSDATE, 'INSERTED');

          INSERT INTO SFSQA_USER_SUPPLIERS
            (USERID, SUPPLIER_CODE, UPDT_USERID, TIME_STAMP, LAST_ACTION,
             UCF_USER_SUPPLIER_VCH1, UCF_USER_SUPPLIER_VCH2, UCF_USER_SUPPLIER_VCH3,
             UCF_USER_SUPPLIER_VCH4, UCF_USER_SUPPLIER_VCH5, UCF_USER_SUPPLIER_VCH6,
             UCF_USER_SUPPLIER_VCH7, UCF_USER_SUPPLIER_VCH8, UCF_USER_SUPPLIER_VCH9,
             UCF_USER_SUPPLIER_VCH10, UCF_USER_SUPPLIER_VCH11, UCF_USER_SUPPLIER_VCH12,
             UCF_USER_SUPPLIER_VCH13, UCF_USER_SUPPLIER_VCH14, UCF_USER_SUPPLIER_VCH15,
             UCF_USER_SUPPLIER_NUM1, UCF_USER_SUPPLIER_NUM2, UCF_USER_SUPPLIER_NUM3,
             UCF_USER_SUPPLIER_NUM4, UCF_USER_SUPPLIER_NUM5, UCF_USER_SUPPLIER_DATE1,
             UCF_USER_SUPPLIER_DATE2, UCF_USER_SUPPLIER_DATE3, UCF_USER_SUPPLIER_DATE4,
             UCF_USER_SUPPLIER_DATE5, UCF_USER_SUPPLIER_FLAG1, UCF_USER_SUPPLIER_FLAG2,
             UCF_USER_SUPPLIER_FLAG3, UCF_USER_SUPPLIER_FLAG4, UCF_USER_SUPPLIER_FLAG5,
             UCF_USER_SUPPLIER_VCH255_1, UCF_USER_SUPPLIER_VCH255_2,
             UCF_USER_SUPPLIER_VCH255_3, UCF_USER_SUPPLIER_VCH4000_1,
             UCF_USER_SUPPLIER_VCH4000_2)
          VALUES
            (U.USERID, 'N/A', 'SFMFG', SYSDATE, 'INSERTED', NULL, NULL, NULL, NULL, NULL,
             NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
             NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
             NULL, NULL, NULL, NULL);

        EXCEPTION
          WHEN OTHERS THEN

            V_USER_SUCCESS := FALSE;
            V_ERROR_MSG    := 'Id failed to write to one or more of four user tables:' || CHR(10) ||
                              '(1)SFCORE_USER, (2)SFFND_USER, (3)SFFND_USER_WORK_CENTERS, (4)SFSQA_USER_SUPPLIERS' ||
                              CHR(10) || 'Error Msg: ' || SUBSTR(SQLERRM, 1, 1800);

            INSERT INTO PWUST_LOAD_USER_LOG
              (USERID, USER_NAME, ERROR_TYPE, ERR_SRC_TABLE, TIME_STAMP, ERROR_TEXT)
            VALUES
              (U.USERID, NVL(U.FULL_NAME, U.FIRST_NAME || ' ' || U.LAST_NAME),
               'USER_INSERT_FAIL', 'UNKNOWN', SYSDATE, V_ERROR_MSG);

        END; --Individual user insert
      END IF; --Duplicate user or not

      IF V_USER_SUCCESS = TRUE THEN

        /**********************************
             INSERT USER SECURITY GROUP
        ***********************************/
        BEGIN

          --Derive security group from home work location
          SELECT SECURITY_GROUP
            INTO V_SECURITY_GROUP
            FROM UTASGI_USER_SEC_GRP_XREF
           WHERE HR_LOC = U.WORK_LOC;

          INSERT INTO SFFND_USER_SEC_GRP
            (USERID, SECURITY_GROUP, UPDT_USERID, TIME_STAMP, LAST_ACTION,
             EFFECTIVE_START_DATE, EFFECTIVE_END_DATE)
          VALUES
            (U.USERID, V_SECURITY_GROUP, 'SFMFG', SYSDATE, 'INSERTED', TRUNC(SYSDATE),
             TO_DATE('01/01/9999', 'MM/DD/YYYY'));

        EXCEPTION
          WHEN NO_DATA_FOUND THEN

            INSERT INTO PWUST_LOAD_USER_LOG
              (USERID, USER_NAME, ERROR_TYPE, ERR_SRC_TABLE, TIME_STAMP, ERROR_TEXT)
            VALUES
              (U.USERID, NVL(U.FULL_NAME, U.FIRST_NAME || ' ' || U.LAST_NAME),
               'UNMAPPED_SEC_GRP_LOC', 'UTASGI_USER_SEC_GRP_XREF', SYSDATE,
               'Work location ' || U.WORK_LOC ||
                ' not mapped to a security group. No security group assigned.');

          WHEN OTHERS THEN

            INSERT INTO PWUST_LOAD_USER_LOG
              (USERID, USER_NAME, ERROR_TYPE, ERR_SRC_TABLE, TIME_STAMP, ERROR_TEXT)
            VALUES
              (U.USERID, NVL(U.FULL_NAME, U.FIRST_NAME || ' ' || U.LAST_NAME),
               'SEC_GRP_INSERT_FAIL', 'SFFND_USER_SEC_GRP', SYSDATE,
               'User already assigned security group ' || V_SECURITY_GROUP ||
                ' (an attempted assignment was made based on requested work location ' ||
                U.WORK_LOC || ').');
        END;

        /****************************
             INSERT USER LICENSE
        *****************************/
        /*2019-01-18 Confirmed to be valid when using application.
        Functionality disabled per Pratt's request*/
        /*BEGIN
          INSERT INTO SFFND_LICENSE_USERS
            (LICENSE_NAME, USERID, UCF_SFFND_LICENSE_USER_VCH1,
             UCF_SFFND_LICENSE_USER_VCH2, UCF_SFFND_LICENSE_USER_VCH3,
             UCF_SFFND_LICENSE_USER_VCH4, UCF_SFFND_LICENSE_USER_VCH5,
             UCF_SFFND_LICENSE_USER_VCH6, UCF_SFFND_LICENSE_USER_VCH7,
             UCF_SFFND_LICENSE_USER_VCH8, UCF_SFFND_LICENSE_USER_VCH9,
             UCF_SFFND_LICENSE_USER_VCH10, UCF_SFFND_LICENSE_USER_VCH11,
             UCF_SFFND_LICENSE_USER_VCH12, UCF_SFFND_LICENSE_USER_VCH13,
             UCF_SFFND_LICENSE_USER_VCH14, UCF_SFFND_LICENSE_USER_VCH15,
             UCF_SFFND_LICENSE_USER_NUM1, UCF_SFFND_LICENSE_USER_NUM2,
             UCF_SFFND_LICENSE_USER_NUM3, UCF_SFFND_LICENSE_USER_NUM4,
             UCF_SFFND_LICENSE_USER_NUM5, UCF_SFFND_LICENSE_USER_DATE1,
             UCF_SFFND_LICENSE_USER_DATE2, UCF_SFFND_LICENSE_USER_DATE3,
             UCF_SFFND_LICENSE_USER_DATE4, UCF_SFFND_LICENSE_USER_DATE5,
             UCF_SFFND_LICENSE_USER_FLAG1, UCF_SFFND_LICENSE_USER_FLAG2,
             UCF_SFFND_LICENSE_USER_FLAG3, UCF_SFFND_LICENSE_USER_FLAG4,
             UCF_SFFND_LICENSE_USER_FLAG5, UPDT_USERID, TIME_STAMP, LAST_ACTION)
          VALUES
            ('FULL USER', U.USERID, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
             NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
             NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SFMFG', SYSDATE, 'INSERTED');

        EXCEPTION
          WHEN OTHERS THEN

            INSERT INTO PWUST_LOAD_USER_LOG
              (USERID, USER_NAME, ERROR_TYPE, ERR_SRC_TABLE, TIME_STAMP, ERROR_TEXT)
            VALUES
              (U.USERID, NVL(U.FULL_NAME, U.FIRST_NAME || ' ' || U.LAST_NAME),
               'LICENSE_INSERT_FAIL', 'SFFND_LICENSE_USERS', SYSDATE,
               'Failed to assign "FULL USER" license');
        END;*/

      END IF; --User insert successful
    END IF; --User work location is valid
  END LOOP; --Requested "user(s) insert"
  
  /****************************
       INSERT USER ROLE(S)
  *****************************/
        
  FOR R IN C_ROLES
        LOOP
          IF R.ROLE IS NULL THEN
            SFCORE_RAISE_ERROR('Role field empty for user ' || R.USERID ||
                               '. Must have at least one valid role to assign.');
          ELSE
            BEGIN
              --Write to SFCORE_USER_ROLES
              --FYI: This method ignores duplicate role assignments. If attempted, the code skips on w/o feedback.
              SFCORE_USER_ASSIGNROLE('SFMFG', R.USERID, R.ROLE, 'GRANT', 'Y', 'N');

            EXCEPTION
              WHEN OTHERS THEN

                V_ERROR_MSG := 'Unable to add role ' || R.ROLE || ' to user.' || CHR(10) ||
                               'Error Msg: ' || SUBSTR(SQLERRM, 1, 1800);

                INSERT INTO PWUST_LOAD_USER_LOG
                  (USERID, USER_NAME, ERROR_TYPE, ERR_SRC_TABLE, TIME_STAMP, ERROR_TEXT)
                VALUES
                  (R.USERID, NVL(R.FULL_NAME, R.FIRST_NAME || ' ' || R.LAST_NAME),
                   'ROLE_INSERT_FAIL', 'SFCORE_USER_ROLES', SYSDATE, V_ERROR_MSG);
            END;
          END IF; --User role is null or not
        END LOOP;

  COMMIT;

END PWUST_LOAD_USER_PROC;
/
grant execute on PWUST_LOAD_USER_PROC to SF$CONNECTION_POOL;



spool off
