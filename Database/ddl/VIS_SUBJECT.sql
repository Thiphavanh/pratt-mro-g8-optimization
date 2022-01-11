-------------------------------------------------
-- Export file for user SFMFG                  --
-- Created by X006640 on 3/23/2021, 2:36:08 PM --
-------------------------------------------------

set define off

prompt
prompt Creating table VIS_SUBJECT
prompt ==========================
prompt
create table SFMFG.VIS_SUBJECT
(
  plan_id              VARCHAR2(40),
  subject_id           VARCHAR2(40) not null,
  subject_no           NUMBER(22),
  subject_title        VARCHAR2(255),
  authority            VARCHAR2(2000),
  auto_include         VARCHAR2(1) default 'N',
  mandatory            VARCHAR2(1) default 'N',
  subject_status       VARCHAR2(30),
  obsolete_record_flag VARCHAR2(1) default 'N'
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
create index SFMFG.VIS_SUBJECT_IDX1 on SFMFG.VIS_SUBJECT (PLAN_ID)
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
alter table SFMFG.VIS_SUBJECT
  add constraint VIS_SUBJECT_PK primary key (SUBJECT_ID)
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
grant select, insert, update, delete on SFMFG.VIS_SUBJECT to PRATT_READ_ONLY;
grant select, insert, update, delete on SFMFG.VIS_SUBJECT to PRATT_WRITE_ONLY;
grant select, insert, update, delete on SFMFG.VIS_SUBJECT to SF$CONNECTION_POOL;


