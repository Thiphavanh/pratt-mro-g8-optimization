create table SFMFG.PWMROI_MANUAL_DESC_HIST
(
  hist_id                   VARCHAR2(4000),
  hist_action               CHAR(8),
  hist_updt_userid          VARCHAR2(50),
  hist_time_stamp           DATE,
  manual_no                 VARCHAR2(15),
  manual_rev                VARCHAR2(6),
  old_manual_transmittal_no VARCHAR2(6),
  new_manual_transmittal_no VARCHAR2(6),
  old_manual_remarks        VARCHAR2(50),
  new_manual_remarks        VARCHAR2(50),
  old_manual_provider       VARCHAR2(50),
  new_manual_provider       VARCHAR2(50),
  old_manual_title          VARCHAR2(50),
  new_manual_title          VARCHAR2(50),
  old_manual_release_date   DATE,
  new_manual_release_date   DATE,
  old_manual_type           VARCHAR2(50),
  new_manual_type           VARCHAR2(50),
  old_engine_series         VARCHAR2(50),
  new_engine_series         VARCHAR2(50),
  old_obsolete_record_flag  CHAR(1),
  new_obsolete_record_flag  CHAR(1)
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

