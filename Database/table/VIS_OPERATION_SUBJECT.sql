-- Drop table
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE VIS_OPERATION_SUBJECT';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN NULL;
  END;
END;
/

-- Create table
create table VIS_OPERATION_SUBJECT
(
  plan_id    VARCHAR2(40),
  oper_key   NUMBER not null,
  subject_id VARCHAR2(40) not null
);

-- Create/Recreate indexes 
create index VIS_OPERATION_SUBJECT_IDX1 on VIS_OPERATION_SUBJECT (OPER_KEY);
create index VIS_OPERATION_SUBJECT_IDX2 on VIS_OPERATION_SUBJECT (SUBJECT_ID);

-- Create primary and unique key constraints
alter table VIS_OPERATION_SUBJECT
  add constraint VIS_OPERATION_SUBJECT_PK primary key (SUBJECT_ID, OPER_KEY);

-- Grant/Revoke object privileges 
grant select, insert, update, delete on VIS_OPERATION_SUBJECT to PRATT_READ_ONLY;
grant select, insert, update, delete on VIS_OPERATION_SUBJECT to SF$CONNECTION_POOL;
