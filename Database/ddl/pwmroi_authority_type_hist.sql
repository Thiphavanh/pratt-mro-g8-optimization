create table SFMFG.PWMROI_AUTHORITY_TYPE_HIST
(
  hist_id                  VARCHAR2(4000) not null,
  hist_action              CHAR(8),
  hist_updt_userid         VARCHAR2(50),
  hist_time_stamp          DATE,
  authority_type           VARCHAR2(20),
  old_authority_rev_rule   VARCHAR2(5),
  new_authority_rev_rule   VARCHAR2(5),
  old_authority_date_rule  VARCHAR2(5),
  new_authority_date_rule  VARCHAR2(5),
  old_sb_part_no_rule      VARCHAR2(5),
  new_sb_part_no_rule      VARCHAR2(5),
  old_obsolete_record_flag CHAR(1),
  new_obsolete_record_flag CHAR(1)
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
