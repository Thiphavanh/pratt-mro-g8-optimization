-- Drop tables
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE PWUST_INSERT_USER_TEMP_TABLE';
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
    EXECUTE IMMEDIATE 'DROP TABLE PWUST_INSERT_USER_TEMP';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN
      NULL;
  END;
END;
/

-- Create table
create table PWUST_INSERT_USER_TEMP
(
  userid         VARCHAR2(50) not null,
  last_name      VARCHAR2(30),
  first_name     VARCHAR2(30),
  full_name      VARCHAR2(90),
  email_address  VARCHAR2(250),
  work_loc       VARCHAR2(40) not null,
  roles          VARCHAR2(250)
);

-- Grant/Revoke object privileges 
grant select, insert, update, delete on PWUST_INSERT_USER_TEMP to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUST_INSERT_USER_TEMP to SF$CONNECTION_POOL;
