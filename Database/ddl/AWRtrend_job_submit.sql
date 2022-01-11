VARIABLE jobno NUMBER;
DECLARE
BEGIN
    DBMS_JOB.submit(job => :jobno, 
                    what => 'pwperf_awrtrend.main(SYSDATE-1);',
                    next_date => TRUNC(SYSDATE +1) + 2/24,
                    interval => 'TRUNC(SYSDATE + 1) + 2/24'
                    );
    COMMIT;
END;
/
