-- Drop table
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE PWUST_LOAD_USER_LOG';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN
      NULL;
  END;
END;
/

-- Create table
create table PWUST_LOAD_USER_LOG
(
  userid        VARCHAR2(50) not null,
  user_name     VARCHAR2(90),
  error_type    VARCHAR2(30) not null,
  err_src_table VARCHAR2(30),
  time_stamp    DATE default SYSDATE not null,
  error_text    VARCHAR2(2000)
);

-- Grant/Revoke object privileges 
grant select, insert, update, delete on PWUST_LOAD_USER_LOG to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUST_LOAD_USER_LOG to SF$CONNECTION_POOL;
