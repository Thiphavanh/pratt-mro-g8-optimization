/**************************************************************************************************
	Job runs PWUST_CLEAN_EXPORT_AUDIT_LOG procedure to clean the PWUST_EXPORT_AUDIT_LOG table 
***************************************************************************************************/

VARIABLE JOBNO NUMBER;

DECLARE
  JOB_COUNT NUMBER(4);
  OLD_JOB   NUMBER;
BEGIN

  --Count pre-existing SESSION_THREAD jobs
  SELECT COUNT(*)
    INTO JOB_COUNT
    FROM DBA_JOBS
   WHERE UPPER(WHAT) LIKE '%PWUST_CLEAN_EXPORT_AUDIT_LOG%';

  --Remove any existing SESSION_THREAD jobs
  FOR I IN 1 .. JOB_COUNT
  LOOP
  
    SELECT JOB
      INTO OLD_JOB
      FROM DBA_JOBS
     WHERE UPPER(WHAT) LIKE '%PWUST_CLEAN_EXPORT_AUDIT_LOG%'
       AND ROWNUM = 1;
  
    DBMS_JOB.REMOVE(JOB => OLD_JOB);
  
  END LOOP;

  --Insert new job
  DBMS_JOB.SUBMIT(JOB => :JOBNO,
                  WHAT => 'PWUST_CLEAN_EXPORT_AUDIT_LOG;',
                  NEXT_DATE => to_date('15-01-2018 11:30:00 PM', 'DD-MM-YYYY HH:MI:SS AM'),
                  INTERVAL => 'TRUNC(SYSDATE + 90, ''MM'') + 14 + 23.5/24');
  COMMIT;
END;
/
