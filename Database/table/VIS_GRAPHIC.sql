-- Drop table
BEGIN
  DECLARE
    DOES_NOT_EXIST EXCEPTION;
    PRAGMA EXCEPTION_INIT(DOES_NOT_EXIST, -942);
  BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE VIS_GRAPHIC';
  EXCEPTION
    WHEN DOES_NOT_EXIST THEN NULL;
  END;
END;
/

-- Create table
create table VIS_GRAPHIC
(
  graphic_id   NUMBER not null,
  g5_object_id VARCHAR2(40),
  object_tag   VARCHAR2(40),
  object_type  VARCHAR2(40),
  object_desc  VARCHAR2(255),
  binary_data  BLOB,
  thumbnail    BLOB,
  image        BLOB,
  time_stamp   DATE default sysdate
);

-- Create/Recreate indexes 
create index VIS_GRAPHIC_IDX1 on VIS_GRAPHIC (G5_OBJECT_ID);
create index VIS_GRAPHIC_IDX2 on VIS_GRAPHIC (OBJECT_TAG);

-- Create primary and unique key constraints
alter table VIS_GRAPHIC
  add constraint VIS_GRAPHIC_PK primary key (GRAPHIC_ID);

-- Grant/Revoke object privileges 
grant select on VIS_GRAPHIC to PRATT_READ_ONLY;
grant select, insert, update, delete on VIS_GRAPHIC to SF$CONNECTION_POOL;
