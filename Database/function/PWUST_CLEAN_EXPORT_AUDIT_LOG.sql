CREATE OR REPLACE PROCEDURE PWUST_CLEAN_EXPORT_AUDIT_LOG AS
  -- ****************************************************************************
  -- Date Created:   2017-08-31
  -- Created By:     Rusty Cannon
  -- Description:    Clean old records from PWUST_EXPORT_AUDIT_LOG
  --
  -- Modification History:
  -- DATE          AUTHOR          DEFECT   DESCRIPTION
  -- 2017-08-31    R. Cannon       --       Initial Release
  -- ****************************************************************************

  LATEST_RETRIEVAL DATE;

BEGIN

  SELECT MAX(T.ARCH_DATE) INTO LATEST_RETRIEVAL FROM PWUST_EXPORT_AUDIT_ARCH_DATE T;

  DELETE FROM PWUST_EXPORT_AUDIT_LOG A WHERE A.TIME_STAMP < LATEST_RETRIEVAL;

  COMMIT;

EXCEPTION
  WHEN NO_DATA_FOUND THEN
    NULL;
  
END;
/
