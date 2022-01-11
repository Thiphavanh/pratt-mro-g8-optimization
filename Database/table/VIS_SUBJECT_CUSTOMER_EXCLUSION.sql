-- Drop table
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE VIS_SUBJECT_CUSTOMER_EXCLUSION';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN NULL;
  END;
END;
/

-- Create table
create table VIS_SUBJECT_CUSTOMER_EXCLUSION
(
  plan_id    VARCHAR2(40),
  subject_id VARCHAR2(40) not null,
  customer   VARCHAR2(40) not null
);

-- Create/Recreate indexes 
create index VIS_SUBJ_CUST_EXCL_IDX1 on VIS_SUBJECT_CUSTOMER_EXCLUSION (SUBJECT_ID);

-- Create primary and unique key constraints
alter table VIS_SUBJECT_CUSTOMER_EXCLUSION
  add constraint VIS_SUBJ_CUST_EXCL_PK primary key (SUBJECT_ID, CUSTOMER);

-- Grant/Revoke object privileges 
grant select, insert, update, delete on VIS_SUBJECT_CUSTOMER_EXCLUSION to PRATT_READ_ONLY;
grant select, insert, update, delete on VIS_SUBJECT_CUSTOMER_EXCLUSION to SF$CONNECTION_POOL;
