-- Drop table
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE VIS_LIB_STANDARD_TEXT';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN NULL;
  END;
END;
/

-- Create table
create table VIS_LIB_STANDARD_TEXT
(
  std_text_id  VARCHAR2(40) not null,
  text         CLOB,
  std_text_tag VARCHAR2(40)
);

-- Add comments to the columns 
comment on column VIS_LIB_STANDARD_TEXT.text
  is 'xml object containing step instructions and objects';

-- Create primary and unique key constraints 
alter table VIS_LIB_STANDARD_TEXT
  add constraint VIS_LIB_STANDARD_TEXT_PK primary key (STD_TEXT_ID);

-- Grant/Revoke object privileges 
grant select, insert, update, delete on VIS_LIB_STANDARD_TEXT to PRATT_READ_ONLY;
grant select, insert, update, delete on VIS_LIB_STANDARD_TEXT to SF$CONNECTION_POOL;
