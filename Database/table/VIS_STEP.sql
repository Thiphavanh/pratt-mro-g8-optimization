-- Drop table
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE VIS_STEP';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN NULL;
  END;
END;
/

-- Create table
create table VIS_STEP
(
  plan_id           VARCHAR2(40),
  oper_key          NUMBER,
  step_key          NUMBER not null,
  stdoper_tag       VARCHAR2(40),
  step_type         VARCHAR2(1) default 'S',
  step_no           VARCHAR2(10),
  step_title        VARCHAR2(255),
  text              CLOB,
  original_text     CLOB,
  original_location VARCHAR2(40),
  signature         VARCHAR2(32)
);

-- Add comments to the columns 
comment on column VIS_STEP.text
  is 'xml object containing step instructions and objects';

-- Create/Recreate indexes 
create index VIS_STEP_IDX1 on VIS_STEP (PLAN_ID);
create index VIS_STEP_IDX2 on VIS_STEP (OPER_KEY);

-- Create primary and unique key constraints
alter table VIS_STEP
  add constraint VIS_STEP_PK primary key (STEP_KEY);

-- Grant/Revoke object privileges 
grant select, insert, update, delete on VIS_STEP to PRATT_READ_ONLY;
grant select, insert, update, delete on VIS_STEP to SF$CONNECTION_POOL;
