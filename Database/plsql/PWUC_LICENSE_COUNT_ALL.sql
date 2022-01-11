CREATE OR REPLACE PROCEDURE PWUC_LICENSE_COUNT_ALL(P_DATE IN DATE DEFAULT SYSDATE)
  AUTHID CURRENT_USER AS
  --**************************************************************************
  -- Copyright 1995-2006 CSC, Inc.
  -- Unpublished-rights reserved under the Copyright Laws of the United States.
  -- US Government Procurements:
  --**************************************************************************
  -- Date Created: 07/26/2016
  -- Created By:   Nazaire Gnassounou
  -- Description:
  --  Create Solumina License usage:Copy license information from G5 to G7OEM-Defect 2418
  --  Defect 2433 -Update License count - 06/30/2017
  --  R. Thorpe 20190212- copied from SOPPWUS for G8 Adapted for G8
  --
  --******************************************************************************
  -- transaction specific parameters
  V_TIME_STAMP        PWUC_LICENSE_TRACKING.TIME_STAMP%TYPE := NULL;
  V_TIMEZONE          PWUC_LICENSE_TRACKING.TIMEZONE%TYPE := NULL;
  V_ASSIGNED_LICENSES PWUC_LICENSE_TRACKING.CONNECTIONS%TYPE;
  V_TOTAL_LICENSES    PWUC_LICENSE_TRACKING.LICENSES%TYPE;
  V_LICENSE_TYPE      VARCHAR2(20);
  V_DB_NAME           PWUC_LICENSE_TRACKING.DB_NAME%TYPE;
  V_REMAINING         PWUC_LICENSE_TRACKING.CONNECTIONS%TYPE;
  P_VALUE VARCHAR2(100) := 'PWUC_LICENSE_TRACKING errored for input date of: ' ||
                           TRUNC(P_DATE);

BEGIN
  SELECT INSTANCE 
    INTO V_DB_NAME 
    FROM V$THREAD;
    
  SELECT TZ_OFFSET(DBTIMEZONE) 
    INTO V_TIMEZONE 
    FROM DUAL;

  SELECT license, currentcount, total, (total - currentcount) AS remaining
    INTO V_LICENSE_TYPE, V_ASSIGNED_LICENSES, V_TOTAL_LICENSES, V_REMAINING
    FROM sfmfg.sfcore_license_info_v
   WHERE license = 'FULL USER';
 
  INSERT INTO PWUC_LICENSE_TRACKING
    (TIME_STAMP, TIMEZONE, CONNECTIONS, LICENSES, DB_NAME)
  VALUES
    (TRUNC(P_DATE), V_TIMEZONE, V_ASSIGNED_LICENSES, V_TOTAL_LICENSES, V_DB_NAME);

  COMMIT;

EXCEPTION
  WHEN NO_DATA_FOUND THEN
   PWPERF_ERRPKG.REPORT_AND_STOP(NULL, NULL, P_VALUE);
  WHEN OTHERS THEN
    PWPERF_ERRPKG.REPORT_AND_STOP(NULL, NULL, P_VALUE);
  
END PWUC_LICENSE_COUNT_ALL;
/
grant execute on  pwuc_license_count_all to SF$CONNECTION_POOL;
grant execute on  pwuc_license_count_all to XDBD144;
grant execute on  pwuc_license_count_all to X003106;
