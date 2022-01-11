DECLARE
    p_job_no  NUMBER(20);
BEGIN
    SELECT job INTO p_job_no
    FROM dba_jobs
    WHERE what = 'pwperf_awrtrend.main;';

    DBMS_OUTPUT.put_line('Job no is '||p_job_no);

    DBMS_JOB.change(job => p_job_no, 
                    what => 'pwperf_awrtrend.main(SYSDATE-1);',
                    next_date => TRUNC(SYSDATE +1),
                    interval => 'TRUNC(SYSDATE +1)'
                    );
   
    COMMIT;
END;
/
