-- Drop table
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE VIS_LEGACY_OPERATION';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN NULL;
  END;
END;
/

-- Create table
create table VIS_LEGACY_OPERATION
(
  id            NUMBER not null,
  oper_key      NUMBER not null,
  from_location VARCHAR2(200)
);

-- Create/Recreate indexes 
create index VIS_LEGACY_OPERATION_IDX1 on VIS_LEGACY_OPERATION (OPER_KEY);

-- Create primary and unique key constraints
alter table VIS_LEGACY_OPERATION
  add constraint VIS_LEGACY_OPERATION_PK primary key (ID);

-- Grant/Revoke object privileges 
grant select, insert, update, delete on VIS_LEGACY_OPERATION to PRATT_READ_ONLY;
grant select, insert, update, delete on VIS_LEGACY_OPERATION to SF$CONNECTION_POOL;
