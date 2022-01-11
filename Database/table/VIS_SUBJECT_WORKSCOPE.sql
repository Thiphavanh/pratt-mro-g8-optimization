-- Drop table
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE VIS_SUBJECT_WORKSCOPE';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN NULL;
  END;
END;
/

-- Create table
create table VIS_SUBJECT_WORKSCOPE
(
  plan_id    VARCHAR2(40),
  subject_id VARCHAR2(40) not null,
  workscope  VARCHAR2(4) not null
);

-- Create/Recreate indexes 
create index VIS_SUBJECT_WORKSCOPE_IDX1 on VIS_SUBJECT_WORKSCOPE (SUBJECT_ID);

-- Create primary and unique key constraints
alter table VIS_SUBJECT_WORKSCOPE
  add constraint VIS_SUBJ_WORKSCOPE_PK primary key (SUBJECT_ID, WORKSCOPE);

-- Grant/Revoke object privileges 
grant select, insert, update, delete on VIS_SUBJECT_WORKSCOPE to PRATT_READ_ONLY;
grant select, insert, update, delete on VIS_SUBJECT_WORKSCOPE to SF$CONNECTION_POOL;
