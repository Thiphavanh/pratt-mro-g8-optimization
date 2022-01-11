- Create table
create table PWUC_LICENSE_TRACKING
(
  TIME_STAMP  DATE not null,
  TIMEZONE    VARCHAR2(32) not null,
  CONNECTIONS NUMBER not null,
  LICENSES    NUMBER not null,
  DB_NAME     VARCHAR2(32) not null
)
tablespace SFUPGRADE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table PWUC_LICENSE_TRACKING
  add constraint PWUC_LICENSE_TRACKING primary key (TIME_STAMP, DB_NAME)
  using index 
  tablespace SFUPGRADE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Grant/Revoke object privileges 
grant select on PWUC_LICENSE_TRACKING to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUC_LICENSE_TRACKING to SF$CONNECTION_POOL;
grant delete on PWUC_LICENSE_TRACKING to SOLPERFREPORTER;
grant select, delete on PWUC_LICENSE_TRACKING to SOLPERFREPORTER_READONLY;
