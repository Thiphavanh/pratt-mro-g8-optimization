create table SFMFG.PWMROI_MANUAL_DESC
(
  manual_no             VARCHAR2(15) not null,
  manual_rev            VARCHAR2(6) not null,
  manual_transmittal_no VARCHAR2(6),
  manual_remarks        VARCHAR2(50),
  manual_provider       VARCHAR2(50),
  manual_title          VARCHAR2(50),
  manual_release_date   DATE,
  manual_type           VARCHAR2(50),
  engine_series         VARCHAR2(50),
  updt_userid           VARCHAR2(50) default USER,
  time_stamp            DATE default sysdate,
  last_action           VARCHAR2(20) default 'INSERTED',
  obsolete_record_flag  CHAR(1) default 'N',
  revision_create_date  DATE default sysdate
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
  )
nologging;
/
alter table SFMFG.PWMROI_MANUAL_DESC
  add constraint PWUMROI_ENGINE_MANUAL_PK primary key (MANUAL_NO, MANUAL_REV)
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
/

