-- Drop table
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE VIS_STANDARD_OPERATION';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN NULL;
  END;
END;
/

-- Create table
create table VIS_STANDARD_OPERATION
(
  stdoper_id    NUMBER not null,
  title         VARCHAR2(80),
  stdoper_tag   VARCHAR2(40),
  stdoper_desc  VARCHAR2(255),
  original_text CLOB
);

-- Create primary and unique key constraints
alter table VIS_STANDARD_OPERATION
  add constraint VIS_STANDARD_OPERATION_PK primary key (STDOPER_ID);
alter table VIS_STANDARD_OPERATION
  add constraint VIS_STANDARD_OPERATION_UK1 unique (STDOPER_TAG);

-- Grant/Revoke object privileges 
grant select, insert, update, delete on VIS_STANDARD_OPERATION to PRATT_READ_ONLY;
grant select, insert, update, delete on VIS_STANDARD_OPERATION to SF$CONNECTION_POOL;
