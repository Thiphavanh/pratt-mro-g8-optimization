-- Drop table
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE VIS_CONV_PLAN_PROGRESS';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN NULL;
  END;
END;
/

-- Create table
create table VIS_CONV_PLAN_PROGRESS
(
  id        NUMBER not null,
  plan_name VARCHAR2(40),
  who       VARCHAR2(20),
  stage     VARCHAR2(1),
  when_date DATE
);

-- Create primary and unique key constraints 
alter table VIS_CONV_PLAN_PROGRESS
  add constraint VIS_CONV_PLAN_PROGRESS_PK primary key (ID);

-- Grant/Revoke object privileges 
grant select, insert, update, delete on VIS_CONV_PLAN_PROGRESS to PRATT_READ_ONLY;
grant select, insert, update, delete on VIS_CONV_PLAN_PROGRESS to SF$CONNECTION_POOL;
