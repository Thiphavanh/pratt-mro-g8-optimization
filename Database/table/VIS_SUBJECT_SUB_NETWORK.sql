-- Drop table
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE VIS_SUBJECT_SUB_NETWORK';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN NULL;
  END;
END;
/

-- Create table
create table VIS_SUBJECT_SUB_NETWORK
(
  plan_id             VARCHAR2(40),
  subject_id          VARCHAR2(40),
  sub_net_id          NUMBER not null,
  activity_number     VARCHAR2(4),
  sub_activity_number VARCHAR2(8)
);

-- Create/Recreate indexes 
create index VIS_SUB_NETWORK_IDX1 on VIS_SUBJECT_SUB_NETWORK (SUBJECT_ID);

-- Create primary and unique key constraints
alter table VIS_SUBJECT_SUB_NETWORK
  add constraint VIS_SUB_NETWORK_PK primary key (SUB_NET_ID);

alter table VIS_SUBJECT_SUB_NETWORK
  add constraint VIS_SUB_NETWORK_UK1 unique (SUBJECT_ID, ACTIVITY_NUMBER, SUB_ACTIVITY_NUMBER);

-- Grant/Revoke object privileges 
grant select, insert, update, delete on VIS_SUBJECT_SUB_NETWORK to PRATT_READ_ONLY;
grant select, insert, update, delete on VIS_SUBJECT_SUB_NETWORK to SF$CONNECTION_POOL;
