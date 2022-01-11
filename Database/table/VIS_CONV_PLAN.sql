-- Drop table
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE VIS_CONV_PLAN';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN NULL;
  END;
END;
/

-- Create table
create table VIS_CONV_PLAN
(
  plan_name       VARCHAR2(40) not null,
  plan_id         VARCHAR2(40),
  g5_plans        VARCHAR2(2000),
  stage           VARCHAR2(1),
  subjects        NUMBER,
  target_extract  DATE,
  target_complete DATE,
  claimed_by      VARCHAR2(20),
  claimed_when    DATE,
  claimed_stage   VARCHAR2(1)
);

-- Create primary and unique key constraints 
alter table VIS_CONV_PLAN
  add constraint VIS_CONV_PLAN_PK primary key (PLAN_NAME);

-- Grant/Revoke object privileges 
grant select, insert, update, delete on VIS_CONV_PLAN to PRATT_READ_ONLY;
grant select, insert, update, delete on VIS_CONV_PLAN to SF$CONNECTION_POOL;
