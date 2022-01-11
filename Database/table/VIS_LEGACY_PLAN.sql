-- Drop table
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE VIS_LEGACY_PLAN';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN NULL;
  END;
END;
/

-- Create table
create table VIS_LEGACY_PLAN
(
  id            NUMBER not null,
  plan_id       VARCHAR2(40) not null,
  from_location VARCHAR2(200)
);

-- Create/Recreate indexes 
create index VIS_LEGACY_PLAN_IDX1 on VIS_LEGACY_PLAN (PLAN_ID);

-- Create primary and unique key constraints
alter table VIS_LEGACY_PLAN
  add constraint VIS_LEGACY_PLAN_PK primary key (ID);

-- Grant/Revoke object privileges 
grant select, insert, update, delete on VIS_LEGACY_PLAN to PRATT_READ_ONLY;
grant select, insert, update, delete on VIS_LEGACY_PLAN to SF$CONNECTION_POOL;
