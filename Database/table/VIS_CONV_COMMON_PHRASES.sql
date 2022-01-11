-- Drop table
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE VIS_CONV_COMMON_PHRASES';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN NULL;
  END;
END;
/

-- Create table
create table VIS_CONV_COMMON_PHRASES
(
  id           NUMBER not null,
  text_find    VARCHAR2(200),
  text_replace VARCHAR2(200)
);

-- Create primary and unique key constraints 
alter table VIS_CONV_COMMON_PHRASES
  add constraint VIS_CONV_COMMON_PHRASES_PK primary key (ID);

-- Grant/Revoke object privileges 
grant select, insert, update, delete on VIS_CONV_COMMON_PHRASES to PRATT_READ_ONLY;
grant select, insert, update, delete on VIS_CONV_COMMON_PHRASES to SF$CONNECTION_POOL;
