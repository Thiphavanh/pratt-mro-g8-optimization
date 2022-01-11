-- Drop table
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE VIS_SUBJECT_SUPERIOR_NETWORK';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN NULL;
  END;
END;
/

-- Create table
create table VIS_SUBJECT_SUPERIOR_NETWORK
(
  plan_id         VARCHAR2(40),
  subject_id      VARCHAR2(40) not null,
  activity_number VARCHAR2(4) not null
);

-- Create/Recreate indexes 
create index VIS_SUPERIOR_NETWORK_IDX1 on VIS_SUBJECT_SUPERIOR_NETWORK (SUBJECT_ID);

-- Create primary and unique key constraints
alter table VIS_SUBJECT_SUPERIOR_NETWORK
  add constraint VIS_SUPERIOR_NETWORK_PK primary key (SUBJECT_ID, ACTIVITY_NUMBER);

-- Grant/Revoke object privileges 
grant select, insert, update, delete on VIS_SUBJECT_SUPERIOR_NETWORK to PRATT_READ_ONLY;
grant select, insert, update, delete on VIS_SUBJECT_SUPERIOR_NETWORK to SF$CONNECTION_POOL;
