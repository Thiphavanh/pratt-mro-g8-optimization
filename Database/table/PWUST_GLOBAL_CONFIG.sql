-- Create table
create table PWUST_GLOBAL_CONFIG
(
  parameter_name  VARCHAR2(100) not null,
  parameter_value VARCHAR2(255),
  parameter_desc  VARCHAR2(255),
  updt_userid     VARCHAR2(30) default user,
  time_stamp      DATE default sysdate,
  action          VARCHAR2(20) default 'INSERTED'
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
alter table PWUST_GLOBAL_CONFIG
  add constraint PWUST_GLOBAL_CONFIG_PK primary key (PARAMETER_NAME)
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
grant select on PWUST_GLOBAL_CONFIG to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUST_GLOBAL_CONFIG to SF$CONNECTION_POOL;
