-- SMRO_PLG_205
-- Create table
create table PWUST_WORKSCOPE_DEF
(
  WORKSCOPE            VARCHAR2(4) not null,
  WORKSCOPE_TITLE      VARCHAR2(50) not null,
  ENGINE_TYPE          VARCHAR2(10) not null,
  WORK_LOC             VARCHAR2(4) not null,
  UPDT_USERID          VARCHAR2(30) default user not null,
  TIME_STAMP           DATE default sysdate not null,
  LAST_ACTION          VARCHAR2(20) default 'INSERTED' not null,
  OBSOLETE_RECORD_FLAG CHAR(1) default 'N'
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table PWUST_WORKSCOPE_DEF
  add constraint PWUST_WORKSCOPE_DEF_PK primary key (WORKSCOPE, ENGINE_TYPE, WORK_LOC);
alter table PWUST_WORKSCOPE_DEF
  add constraint PWUST_WORKSCOPE_ENGTYPE_FK foreign key (ENGINE_TYPE)
  references SFFND_PROGRAM_DEF (PROGRAM);
-- Grant/Revoke object privileges 
grant select on PWUST_WORKSCOPE_DEF to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUST_WORKSCOPE_DEF to SF$CONNECTION_POOL;
