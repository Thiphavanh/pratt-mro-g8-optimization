------------------------------------------------
-- Export file for user SFMFG                 --
-- Created by c079222 on 9/5/2017, 5:23:28 PM --
------------------------------------------------

--set define off
--spool pwust_ext_sys_properties.log

prompt
prompt Creating table PWUST_EXT_SYS_PROPERTIES
prompt ======================================
prompt
create table PWUST_EXT_SYS_PROPERTIES
(
  agent_name        VARCHAR2(30) not null,
  ps_ip             VARCHAR2(2000),
  ps_conmin         NUMBER,
  ps_conmax         NUMBER,
  ps_constep        NUMBER,
  ps_timeout        NUMBER,
  ps_azport         NUMBER,
  ps_auport         NUMBER,
  ps_acport         NUMBER,
  agent_secret      VARCHAR2(100),
  admin_usrname     VARCHAR2(30),
  admin_pwd         VARCHAR2(100),
  user_dir          VARCHAR2(100),
  org_root          VARCHAR2(100),
  ldap_host         VARCHAR2(100),
  ldap_port         NUMBER,
  ldap_user         VARCHAR2(100),
  ldap_pwd          VARCHAR2(100),
  ldap_chg_pwd_link VARCHAR2(100),
  xclass_host       VARCHAR2(100),
  was_instance      VARCHAR2(100),
  agent_descr       VARCHAR2(255),
  comments          VARCHAR2(255),
  updt_userid       VARCHAR2(30) default user,
  time_stamp        DATE default sysdate,
  action            VARCHAR2(20) default 'INSERTED',
  key10             VARCHAR2(255),
  key11             VARCHAR2(255),
  key12             VARCHAR2(255),
  key13             VARCHAR2(255),
  key14             VARCHAR2(255),
  key15             VARCHAR2(255)
)
;
alter table PWUST_EXT_SYS_PROPERTIES
  add constraint PWUST_EXT_SYS_PROPERTIES_PK primary key (AGENT_NAME);
grant select, insert, update, delete on PWUST_EXT_SYS_PROPERTIES to SF$CONNECTION_POOL;
grant select on PWUST_EXT_SYS_PROPERTIES to PRATT_READ_ONLY;

--spool off
