ALTER TRIGGER SFFND_USER_HIST_TRIG DISABLE;

/*********************************************************************************
	Update all preexisting SFFND_USER.UCF_USER_FLAG2 (user no labor) fields
**********************************************************************************/
--Setup data fix within procedure
CREATE OR REPLACE PROCEDURE PWUE_MANUAL_INS_UPDT_DEL AS

  V_CON VARCHAR2(40);

BEGIN

  SFCORE_LOGIN_USER(USER, '', 'FAT', '127.0.0.1', '', '', 'N', V_CON);
  SFCORE_SET_FLAG('CLIENT_SESSION');

  UPDATE SFFND_USER T
     SET T.UCF_USER_FLAG2 = 'N'
   WHERE T.UCF_USER_FLAG2 <> 'Y'
      OR T.UCF_USER_FLAG2 IS NULL;

  COMMIT;

END PWUE_MANUAL_INS_UPDT_DEL;
/

--Call the procedure
BEGIN
  PWUE_MANUAL_INS_UPDT_DEL;
END;
/
  
/******************************************
	Insert records into global config
*******************************************/
INSERT INTO PWUST_GLOBAL_CONFIG T
  (PARAMETER_NAME, PARAMETER_VALUE, PARAMETER_DESC, UPDT_USERID, TIME_STAMP, ACTION)
VALUES
  ('SITEMINDER_ENABLED',
   'N',
   'Used to determine whether Siteminder connectivity is enabled for use in LoginImplPW',
   'SFMFG',
   SYSDATE,
   'INSERTED');

INSERT INTO PWUST_GLOBAL_CONFIG T
  (PARAMETER_NAME, PARAMETER_VALUE, PARAMETER_DESC, UPDT_USERID, TIME_STAMP, ACTION)
VALUES
  ('CERTIFICATION_CHECK',
   'N',
   'Used to determine whether to check Eye Exam and FOD Certifications',
   'SFMFG',
   SYSDATE,
   'INSERTED'); 