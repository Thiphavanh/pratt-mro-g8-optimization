-- Drop table
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE VIS_OPERATION';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN NULL;
  END;
END;
/

-- Create table
create table VIS_OPERATION
(
  plan_id          VARCHAR2(40),
  oper_key         NUMBER not null,
  oper_no          VARCHAR2(10),
  oper_type        VARCHAR2(1),
  oper_title       VARCHAR2(255),
  stdoper_tag      VARCHAR2(40),
  plnd_work_dept   VARCHAR2(30),
  plnd_work_center VARCHAR2(30),
  exe_order        NUMBER default 0 not null,
  preclean_status  VARCHAR2(1) default 'O',
  content_sig      VARCHAR2(18)
);

-- Create/Recreate indexes 
create index VIS_OPERATION_IDX1 on VIS_OPERATION (PLAN_ID);

-- Create primary and unique key constraints
alter table VIS_OPERATION
  add constraint VIS_OPERATION_PK primary key (OPER_KEY);

-- Grant/Revoke object privileges 
grant select, insert, update, delete on VIS_OPERATION to PRATT_READ_ONLY;
grant select, insert, update, delete on VIS_OPERATION to SF$CONNECTION_POOL;
