DECLARE
     p_date DATE := SYSDATE - 3;   
BEGIN
     
     FOR i IN 1 .. 3  LOOP
       DBMS_OUTPUT.put_line (TO_CHAR(p_date, 'dd-mon-yyyy'));

       pwperf_awrtrend.main(p_date);
       p_date := p_date + 1;

    END LOOP;
END;
/
