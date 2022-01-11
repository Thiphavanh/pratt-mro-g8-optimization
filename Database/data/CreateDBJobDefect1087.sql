VARIABLE JOBNO NUMBER;

DECLARE
  JOB_COUNT NUMBER(4);
  OLD_JOB   NUMBER;
BEGIN

  --Count pre-existing SESSION_THREAD jobs
  SELECT COUNT(*)
    INTO JOB_COUNT
    FROM DBA_JOBS
   WHERE UPPER(WHAT) LIKE '%SFMFG.pwuc_license_count_all(SYSDATE-1)%';

  --Remove any existing SESSION_THREAD jobs
  FOR I IN 1 .. JOB_COUNT
  LOOP
  
    SELECT JOB
      INTO OLD_JOB
      FROM DBA_JOBS
     WHERE UPPER(WHAT) LIKE '%SFMFG.pwuc_license_count_all(SYSDATE-1)%'
       AND ROWNUM = 1;
  
    DBMS_JOB.REMOVE(JOB => OLD_JOB);
  
  END LOOP;


  --Insert new job
  DBMS_JOB.SUBMIT(JOB => :JOBNO,
                  WHAT => 'SFMFG.pwuc_license_count_all(SYSDATE-1);',
                  INTERVAL => 'TRUNC(SYSDATE +1) + 1/24');
  COMMIT;
END;
/