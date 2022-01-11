-- Drop table
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE VIS_CONV_WORKCENTER_MAP';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN NULL;
  END;
END;
/

-- Create table
create table VIS_CONV_WORKCENTER_MAP
(
  id          NUMBER not null,
  pattern     VARCHAR2(50),
  work_center VARCHAR2(10)
);

-- Create primary and unique key constraints
alter table VIS_CONV_WORKCENTER_MAP
  add constraint VIS_CONV_WORKCENTER_MAP_PK primary key (ID);

-- Grant/Revoke object privileges 
grant select, insert, update, delete on VIS_CONV_WORKCENTER_MAP to PRATT_READ_ONLY;
grant select, insert, update, delete on VIS_CONV_WORKCENTER_MAP to SF$CONNECTION_POOL;
