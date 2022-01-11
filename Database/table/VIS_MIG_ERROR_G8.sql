-- Drop table
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE VIS_MIG_ERROR_G8';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN NULL;
  END;
END;
/

-- Create table
create table VIS_MIG_ERROR_G8
(
  type         VARCHAR2(150) not null,
  id           VARCHAR2(250),
  plan_id      VARCHAR2(40),
  plan_updt_no NUMBER,
  oper_no      VARCHAR2(10),
  step_no      VARCHAR2(10),
  error_code   NUMBER,
  error_text   VARCHAR2(3000) not null,
  tool_no      VARCHAR2(25),
  updt_userid  VARCHAR2(30) default USER,
  time_stamp   DATE default SYSDATE not null
);

-- Grant/Revoke object privileges 
grant select, insert, update, delete on VIS_MIG_ERROR_G8 to PRATT_READ_ONLY;
grant select, insert, update, delete on VIS_MIG_ERROR_G8 to SF$CONNECTION_POOL;
