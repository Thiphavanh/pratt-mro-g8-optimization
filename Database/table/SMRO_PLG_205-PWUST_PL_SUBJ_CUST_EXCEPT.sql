-- SMRO_PLG_205
-- Create table
create table PWUST_PL_SUBJ_CUST_EXCEPT
(
  PLAN_ID           VARCHAR2(40) not null,
  PLAN_UPDT_NO      NUMBER default 1 not null,
  SUBJECT_NO        NUMBER not null,
  SUBJECT_REV       NUMBER default 1 not null,
  CUST_ID           VARCHAR2(40) not null,
  CUST_ID_EXCEPTION VARCHAR2(40) not null,
  UPDT_USERID       VARCHAR2(30) default user not null,
  TIME_STAMP        DATE default sysdate not null,
  LAST_ACTION       VARCHAR2(20) default 'INSERTED' not null
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table PWUST_PL_SUBJ_CUST_EXCEPT
  add constraint PWUST_PL_SUBJ_CUST_EXCEPT_PK primary key (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, SUBJECT_REV, CUST_ID, CUST_ID_EXCEPTION);

alter table PWUST_PL_SUBJ_CUST_EXCEPT
  add constraint PWUST_PL_SUBJ_CUST_EXCEPT_FK foreign key (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, SUBJECT_REV, CUST_ID)
  references PWUST_PL_SUBJ_CUSTOMER (PLAN_ID, PLAN_UPDT_NO, SUBJECT_NO, SUBJECT_REV, CUST_ID) on delete cascade;

-- Grant/Revoke object privileges 
grant select on PWUST_PL_SUBJ_CUST_EXCEPT to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUST_PL_SUBJ_CUST_EXCEPT to SF$CONNECTION_POOL;