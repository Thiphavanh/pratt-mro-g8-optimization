-------------------------------------------------
-- Export file for user SFMFG                  --
-- Created by X006640 on 3/2/2021, 11:26:20 AM --
-------------------------------------------------

set define off

prompt
prompt Creating table VIS_TOOL_NO_SUBSTITUTION
prompt =======================================
prompt
create table SFMFG.VIS_TOOL_NO_SUBSTITUTION
(
  g5_tool_no VARCHAR2(50),
  g8_tool_no VARCHAR2(50),
  pwp_id     VARCHAR2(40)
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
grant select, insert, update, delete on SFMFG.VIS_TOOL_NO_SUBSTITUTION to PRATT_READ_ONLY;
grant select, insert, update, delete on SFMFG.VIS_TOOL_NO_SUBSTITUTION to PRATT_WRITE_ONLY;
