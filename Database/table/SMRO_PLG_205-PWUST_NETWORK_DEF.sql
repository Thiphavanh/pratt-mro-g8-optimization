-- SMRO_PLG_205
-- Create table
create table PWUST_NETWORK_DEF
(
  engine_type               VARCHAR2(30) not null,
  engine_type_desc          VARCHAR2(40),
  std_network_no            VARCHAR2(8),
  superior_network_act      VARCHAR2(12) not null,
  superior_network_act_desc VARCHAR2(40),
  module_network_no         VARCHAR2(12),
  sub_network_act           VARCHAR2(8) not null,
  sub_network_act_desc      VARCHAR2(40),
  module_cat_code           VARCHAR2(4),
  module_cat_desc           VARCHAR2(40),
  work_center               VARCHAR2(8),
  control_key               VARCHAR2(4),
  gate                      VARCHAR2(13),
  duration                  VARCHAR2(5),
  duration_unit             VARCHAR2(10),
  work                      VARCHAR2(7),
  work_unit                 VARCHAR2(3),
  eworkscoping_flag         VARCHAR2(1),
  lid_no                    VARCHAR2(12),
  updt_userid               VARCHAR2(30) default user not null,
  time_stamp                DATE default sysdate not null,
  action                    VARCHAR2(20) default 'INSERTED' not null,
  obsolete_record_flag      VARCHAR2(1) default 'N' not null
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table PWUST_NETWORK_DEF   add constraint PWUST_NETWORK_DEF_FK foreign key (ENGINE_TYPE)  references SFFND_PROGRAM_DEF (PROGRAM);
-- Create/Recreate primary, unique and foreign key constraints 
alter table PWUST_NETWORK_DEF  add constraint PWUST_NETWORK_DEF_PK primary key  (STD_NETWORK_NO, STD_NETWORK_NODE_NO, MODULE_NETWORK_NO, MODULE_NETWORK_NODE_NO)  using index tablespace SFUPGRADE;
-- Grant/Revoke object privileges 
grant select on PWUST_NETWORK_DEF to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUST_NETWORK_DEF to SF$CONNECTION_POOL;