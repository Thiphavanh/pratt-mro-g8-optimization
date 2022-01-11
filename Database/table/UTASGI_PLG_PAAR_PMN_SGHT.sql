-- Create table
create table UTASGI_PLG_PAAR_PMN_SGHT
(
  request_id        VARCHAR2(40) not null,
  sght_id           VARCHAR2(40) not null,
  pmn_action        VARCHAR2(20) not null,
  pmn_type          VARCHAR2(20) not null,
  pmn_type_entity   VARCHAR2(50) not null,
  pmn_eff_from_date DATE,
  pmn_eff_thru_date DATE,
  auth_id           VARCHAR2(40),
  updt_userid       VARCHAR2(30) default user not null,
  time_stamp        DATE default sysdate not null,
  last_action       VARCHAR2(20) default 'INSERTED' not null
)
tablespace SFPL_DATA
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
/
-- Create/Recreate indexes 
create index UTASGI_PLG_PAAR_PMN_SGHT_IDX1 on UTASGI_PLG_PAAR_PMN_SGHT (REQUEST_ID, PMN_TYPE)
  tablespace SFPL_INDEXES
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  )
  nologging;
/
-- Create/Recreate primary, unique and foreign key constraints 
alter table UTASGI_PLG_PAAR_PMN_SGHT
  add constraint UTASGI_PLG_PAAR_PMN_SGHT_PK primary key (REQUEST_ID, SGHT_ID)
  using index 
  tablespace SFPL_INDEXES
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
/
alter index UTASGI_PLG_PAAR_PMN_SGHT_PK nologging;
/
alter table UTASGI_PLG_PAAR_PMN_SGHT
  add constraint UTASGI_PLG_PAAR_PMN_SGHT_IDX2 unique (REQUEST_ID, PMN_TYPE, PMN_TYPE_ENTITY)
  using index 
  tablespace SFPL_INDEXES
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
/
alter index UTASGI_PLG_PAAR_PMN_SGHT_IDX2 nologging;
/
alter table UTASGI_PLG_PAAR_PMN_SGHT
  add constraint UTASGI_PLG_PAAR_PMN_SGHT_FK1 foreign key (REQUEST_ID)
  references UTASGI_PLG_PAAR_DESC (REQUEST_ID) on delete 
/