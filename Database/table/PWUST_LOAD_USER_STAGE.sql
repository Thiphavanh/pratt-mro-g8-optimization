-- Drop tables
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE PWUST_INSERT_USER_TEMP';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN
      NULL;
  END;
END;
/

BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE PWUST_LOAD_USER_STAGE';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN
      NULL;
  END;
END;
/

-- Create table
create table PWUST_LOAD_USER_STAGE
(
  userid         VARCHAR2(50) not null,
  last_name      VARCHAR2(30),
  first_name     VARCHAR2(30),
  full_name      VARCHAR2(90),
  work_loc       VARCHAR2(40) not null,
  shift          CHAR(1),
  payroll_id     VARCHAR2(40),
  email_address  VARCHAR2(250),
  user_no_labor  VARCHAR2(1) default 'N',
  roles          VARCHAR2(250) not null
);

-- Grant/Revoke object privileges 
grant select, insert, update, delete on PWUST_LOAD_USER_STAGE to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUST_LOAD_USER_STAGE to SF$CONNECTION_POOL;
