-- Drop table
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE VIS_CONV_USERS';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN NULL;
  END;
END;
/

-- Create table
create table VIS_CONV_USERS
(
  usercode   VARCHAR2(20) not null,
  domain     VARCHAR2(20),
  first_name VARCHAR2(20),
  last_name  VARCHAR2(20)
);

-- Create primary and unique key constraints 
alter table VIS_CONV_USERS
  add constraint VIS_CONV_USERS_PK primary key (USERCODE);

-- Grant/Revoke object privileges 
grant select, insert, update, delete on VIS_CONV_USERS to PRATT_READ_ONLY;
grant select, insert, update, delete on VIS_CONV_USERS to SF$CONNECTION_POOL;
