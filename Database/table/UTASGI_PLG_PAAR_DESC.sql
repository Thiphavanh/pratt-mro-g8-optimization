-- Create table
create table UTASGI_PLG_PAAR_DESC
(
  request_id             VARCHAR2(44) not null,
  request_status         VARCHAR2(20) not null,
  request_userid         VARCHAR2(30) default user not null,
  request_creation_date  DATE default sysdate not null,
  request_reason         VARCHAR2(255),
  plan_id                VARCHAR2(40) not null,
  plan_version           NUMBER not null,
  plan_revision          NUMBER not null,
  plan_alterations       NUMBER not null,
  reply_required_date    DATE,
  prior_request_id       VARCHAR2(40),
  updt_userid            VARCHAR2(30) default user not null,
  time_stamp             DATE default sysdate not null,
  last_action            VARCHAR2(20) default 'INSERTED' not null,
  approving_user_id      VARCHAR2(44),
  request_security_group VARCHAR2(44)
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
create index UTASGI_PLG_PAAR_DESC_IX1 on UTASGI_PLG_PAAR_DESC (PLAN_ID, PLAN_VERSION, PLAN_REVISION, PLAN_ALTERATIONS)
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
alter table UTASGI_PLG_PAAR_DESC
  add constraint UTASGI_PLG_PAAR_DESC_PK primary key (REQUEST_ID)
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
alter index UTASGI_PLG_PAAR_DESC_PK nologging;
/
alter table UTASGI_PLG_PAAR_DESC
  add constraint UTASGI_PLG_PAAR_DESC_FK1 foreign key (PLAN_ID, PLAN_VERSION, PLAN_REVISION, PLAN_ALTERATIONS)
  references SFPL_PLAN_REV (PLAN_ID, PLAN_VERSION, PLAN_REVISION, PLAN_ALTERATIONS) on delete cascade;
/
