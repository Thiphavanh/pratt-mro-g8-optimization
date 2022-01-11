-- Drop table
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE VIS_SUBJECT';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN NULL;
  END;
END;
/

-- Create table
create table VIS_SUBJECT
(
  plan_id       VARCHAR2(40),
  subject_id    VARCHAR2(40) not null,
  subject_no    NUMBER(22),
  subject_title VARCHAR2(255),
  authority     VARCHAR2(2000),
  auto_include  VARCHAR2(1) default 'N',
  mandatory     VARCHAR2(1) default 'N'
);

-- Create/Recreate indexes 
create index VIS_SUBJECT_IDX1 on VIS_SUBJECT (PLAN_ID);

-- Create primary and unique key constraints
alter table VIS_SUBJECT
  add constraint VIS_SUBJECT_PK primary key (SUBJECT_ID);

-- Grant/Revoke object privileges 
grant select, insert, update, delete on VIS_SUBJECT to PRATT_READ_ONLY;
grant select, insert, update, delete on VIS_SUBJECT to SF$CONNECTION_POOL;
