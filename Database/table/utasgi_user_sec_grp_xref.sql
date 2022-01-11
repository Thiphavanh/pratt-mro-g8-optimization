-- Create table
create table UTASGI_USER_SEC_GRP_XREF
(
  hr_loc         VARCHAR2(40) not null,
  security_group VARCHAR2(50) not null,
  updt_userid    VARCHAR2(30) default user not null,
  time_stamp     DATE default sysdate not null,
  last_action    VARCHAR2(20) default 'INSERTED'
)
tablespace SFLOOKUP_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
  );
/
-- Create/Recreate primary, unique and foreign key constraints 
alter table UTASGI_USER_SEC_GRP_XREF
  add constraint UTASGI_USER_SEC_GRP_XREF_PK primary key (HR_LOC, SECURITY_GROUP)
  using index 
  tablespace SFLOOKUP_INDEXES
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
  );
/
alter index UTASGI_USER_SEC_GRP_XREF_PK nologging;
/
alter table UTASGI_USER_SEC_GRP_XREF
  add constraint UTASGI_USER_SEC_GRP_XREF_FK foreign key (SECURITY_GROUP)
  references SFFND_SECURITY_GROUP_DEF (SECURITY_GROUP);
/
