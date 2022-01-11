-- SMRO_PLG_205
-- Create table
create table PWUST_SFOR_SFPL_PLAN_SUBJECT
(
  PLAN_ID              VARCHAR2(40) not null,
  Plan_updt_no         NUMBER default 1 NOT NULL,
  subject_no           NUMBER NOT NULL,
  subject_rev          NUMBER default 1 NOT NULL,
  mandatory            varchar2(1) default 'N',
  UPDT_USERID          VARCHAR2(30) default user not null,
  TIME_STAMP           DATE default sysdate not null,
  LAST_ACTION          VARCHAR2(20) default 'INSERTED' not null,
  OBSOLETE_RECORD_FLAG CHAR(1) default 'N'
);

-- Create/Recreate primary, unique and foreign key constraints 
alter table PWUST_SFOR_SFPL_PLAN_SUBJECT
  add constraint PWUST_SFOR_SFPL_PLAN_SUBJ_PK primary key (PLAN_ID, Plan_updt_no, subject_no, subject_rev);

alter table PWUST_SFOR_SFPL_PLAN_SUBJECT
  ADD constraint PWUST_SFOR_SFPL_PLAN_SUBJ_FK foreign key (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO)
  references SFOR_SFPL_PLAN_SUBJECT (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO) on delete cascade;

-- Grant/Revoke object privileges 
grant select on PWUST_SFOR_SFPL_PLAN_SUBJECT to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUST_SFOR_SFPL_PLAN_SUBJECT to SF$CONNECTION_POOL;

