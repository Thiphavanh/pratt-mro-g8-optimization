-- SMRO_PLG_205
-- Create table
create table PWUST_PL_SUBJ_CUSTOMER
(
  PLAN_ID      VARCHAR2(40) not null,
  PLAN_UPDT_NO NUMBER default 1 not null,
  SUBJECT_NO   NUMBER not null,
  SUBJECT_REV  NUMBER default 1 not null,
  CUST_ID      VARCHAR2(40) not null,
  UPDT_USERID  VARCHAR2(30) default user not null,
  TIME_STAMP   DATE default sysdate not null,
  LAST_ACTION  VARCHAR2(20) default 'INSERTED' not null
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table PWUST_PL_SUBJ_CUSTOMER
  add constraint PWUST_DTL_PL_CUSTOMER_PK primary key (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, SUBJECT_REV, CUST_ID);

alter table PWUST_PL_SUBJ_CUSTOMER
  add constraint PWUST_PL_SUBJ_CUSTOMER_FK foreign key (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO)
  references SFOR_SFPL_PLAN_SUBJECT (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO) on delete cascade;

-- Grant/Revoke object privileges 
grant select on PWUST_PL_SUBJ_CUSTOMER to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUST_PL_SUBJ_CUSTOMER to SF$CONNECTION_POOL;
