-- SMRO_PLG_205
-- Create table
create table PWUST_PL_SUBJ_NETWORK_ACTIVITY
(
  PLAN_ID              VARCHAR2(40) not null,
  PLAN_UPDT_NO         NUMBER default 1 not null,
  SUBJECT_NO           NUMBER not null,
  SUBJECT_REV          NUMBER default 1 not null,
  ENGINE_TYPE          VARCHAR2(50) not null,
  SUPERIOR_NETWORK_ACT VARCHAR2(4) not null,
  UPDT_USERID          VARCHAR2(30) default user not null,
  TIME_STAMP           DATE default sysdate not null,
  LAST_ACTION  VARCHAR2(20) default 'INSERTED' not null
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table PWUST_PL_SUBJ_NETWORK_ACTIVITY
  add constraint PWUST_PL_SUBJ_NETWORKACT_PK primary key (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, ENGINE_TYPE, SUPERIOR_NETWORK_ACT);

alter table PWUST_PL_SUBJ_NETWORK_ACTIVITY
  add constraint PWUST_PL_SUBJ_NETWORKACT_FK foreign key (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO)
  references SFOR_SFPL_PLAN_SUBJECT (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO) on delete cascade;

-- Grant/Revoke object privileges 
grant select on PWUST_PL_SUBJ_NETWORK_ACTIVITY to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUST_PL_SUBJ_NETWORK_ACTIVITY to SF$CONNECTION_POOL;
