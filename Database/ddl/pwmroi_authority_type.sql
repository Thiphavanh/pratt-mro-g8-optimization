create table SFMFG.PWMROI_AUTHORITY_TYPE
(
  authority_type       VARCHAR2(20) not null,
  authority_rev_rule   VARCHAR2(5) not null,
  authority_date_rule  VARCHAR2(5) not null,
  sb_part_no_rule      VARCHAR2(5) not null,
  updt_userid          VARCHAR2(50) default USER,
  time_stamp           DATE default SYSDATE,
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
alter table SFMFG.PWMROI_AUTHORITY_TYPE
  add constraint PWMROI_AUTH_TYPE_PK primary key (AUTHORITY_TYPE)
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

