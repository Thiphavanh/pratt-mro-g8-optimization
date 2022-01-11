create table SFMFG.PWMROI_MANUAL_TYPE
(
  manual_type          VARCHAR2(40) not null,
  updt_userid          VARCHAR2(50) default USER,
  time_stamp           DATE default sysdate,
  last_action          VARCHAR2(20) default 'INSERTED',
  obsolete_record_flag CHAR(1) default 'N'
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
alter table SFMFG.PWMROI_MANUAL_TYPE
  add constraint PWUMROI_ENGINE_MAN_TYPE_PK primary key (MANUAL_TYPE)
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

