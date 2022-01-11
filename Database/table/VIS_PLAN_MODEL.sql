-- Drop table
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE VIS_PLAN_MODEL';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN NULL;
  END;
END;
/

-- Create table
create table VIS_PLAN_MODEL
(
  plan_id VARCHAR2(40) not null,
  model   VARCHAR2(20) not null
);

-- Create primary and unique key constraints
alter table VIS_PLAN_MODEL
  add constraint VIS_PLAN_MODEL_PK primary key (PLAN_ID, MODEL);

-- Grant/Revoke object privileges 
grant select, insert, update, delete on VIS_PLAN_MODEL to PRATT_READ_ONLY;
grant select, insert, update, delete on VIS_PLAN_MODEL to SF$CONNECTION_POOL;
