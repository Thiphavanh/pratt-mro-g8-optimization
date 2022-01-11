drop table PWUST_SFCORE_MM_OBJECT cascade constraints;
-- Create table defect 1906
-- Create table
create table PWUST_SFCORE_MM_OBJECT
(
  PW_OBJECT_ID      VARCHAR2(40) not null,
  PW_OBJECT_TAG     VARCHAR2(40) not null,
  PW_OBJECT_REV     VARCHAR2(40) not null,
  PW_OBJECT_TYPE    VARCHAR2(40),
  SECURITY_GROUP VARCHAR2(4000),
  UPDT_USERID       VARCHAR2(50) default USER not null,
  TIME_STAMP        DATE default SYSDATE not null
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
alter table PWUST_SFCORE_MM_OBJECT
  add constraint PWUST_SFCORE_MM_OBJECT_PK primary key (PW_OBJECT_TYPE, PW_OBJECT_TAG,PW_OBJECT_REV,SECURITY_GROUP)
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
alter table PWUST_SFCORE_MM_OBJECT
  add constraint PWUST_SFCORE_MM_OBJECT_FK1 foreign key (PW_OBJECT_ID)
  references SFCORE_MM_OBJECT (OBJECT_ID);
alter table PWUST_SFCORE_MM_OBJECT
  add constraint PWUST_SFCORE_MM_OBJECT_FK2 foreign key (SECURITY_GROUP)
  references SFFND_SECURITY_GROUP_DEF (SECURITY_GROUP);
-- Grant/Revoke object privileges 
grant select on PWUST_SFCORE_MM_OBJECT to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUST_SFCORE_MM_OBJECT to PRATT_WRITE_ONLY;
grant select, insert, update, delete on PWUST_SFCORE_MM_OBJECT to SF$CONNECTION_POOL;
