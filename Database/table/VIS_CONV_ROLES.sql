-- Drop table
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE VIS_CONV_ROLES';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN NULL;
  END;
END;
/

-- Create table
create table VIS_CONV_ROLES
(
  usercode      VARCHAR2(20) not null,
  business_role VARCHAR2(20) not null
);

-- Create primary and unique key constraints 
alter table VIS_CONV_ROLES
  add constraint VIS_CONV_ROLES_PK primary key (USERCODE, BUSINESS_ROLE);

-- Grant/Revoke object privileges 
grant select, insert, update, delete on VIS_CONV_ROLES to PRATT_READ_ONLY;
grant select, insert, update, delete on VIS_CONV_ROLES to SF$CONNECTION_POOL;
