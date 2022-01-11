--------------------------------------------------
-- Export file for user SFMFG@SOQMROQ3          --
-- Created by c079222 on 5/19/2020, 10:12:13 AM --
--------------------------------------------------


prompt
prompt Creating table PWUST_PART_TOOL_LOAD
prompt ===================================
prompt
create table PWUST_PART_TOOL_LOAD
(
  item_id              VARCHAR2(40),
  part_no              VARCHAR2(50) not null,
  part_chg             VARCHAR2(10) not null,
  part_title           VARCHAR2(255),
  parent_eng_part_no   VARCHAR2(50),
  parent_eng_part_chg  VARCHAR2(10),
  program              VARCHAR2(30) default 'ANY',
  work_loc             VARCHAR2(40),
  part_type            VARCHAR2(80),
  stock_uom            VARCHAR2(10),
  batch_size           NUMBER,
  comp_serial_flag     VARCHAR2(1),
  comp_lot_flag        VARCHAR2(1),
  wo_serial_flag       VARCHAR2(1),
  wo_lot_flag          VARCHAR2(1),
  exp_flag             VARCHAR2(1),
  spool_flag           VARCHAR2(1),
  opt_dc1_flag         CHAR(1),
  opt_dc2_flag         CHAR(1),
  opt_dc3_flag         CHAR(1),
  opt_dc4_flag         CHAR(1),
  ucf_item_vch1        VARCHAR2(50),
  ucf_item_vch2        VARCHAR2(50),
  ucf_item_num1        NUMBER,
  ucf_item_flag1       CHAR(1),
  updt_userid          VARCHAR2(50) default user not null,
  time_stamp           DATE default sysdate not null,
  last_action          VARCHAR2(20) default 'INSERTED' not null,
  obsolete_record_flag CHAR(1) default 'N',
  part_flag            CHAR(1) default 'Y' not null,
  standard_part_flag   CHAR(1) default 'N' not null,
  item_subtype         VARCHAR2(80) not null,
  item_type            VARCHAR2(80) not null
)
;
grant select on PWUST_PART_TOOL_LOAD to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUST_PART_TOOL_LOAD to SF$CONNECTION_POOL;


