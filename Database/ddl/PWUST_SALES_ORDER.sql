-- Create table
create table PWUST_SALES_ORDER
(
  SALES_ORDER            VARCHAR2(20) not null,
  ENGINE_TYPE            VARCHAR2(8),
  ENGINE_MODEL           VARCHAR2(18),
  MODULE_CODE_CODE       VARCHAR2(4),
  ACT_NO                 VARCHAR2(4),
  SUB_ACT_NO             VARCHAR2(8),
  CUSTOMER               VARCHAR2(10),
  CUSTOMER_DESC          VARCHAR2(40),
  WORK_SCOPE             VARCHAR2(10),
  SERIAL_NO              VARCHAR2(20),
  WORK_LOC               VARCHAR2(4),
  CONFIRM_NO             VARCHAR2(10),
  SUB_ACT_NO_START_DATE  DATE,
  SUB_ACT_NO_FINISH_DATE DATE,
  UPDT_USERID            VARCHAR2(30) default user not null,
  TIME_STAMP             DATE default sysdate not null,
  LAST_ACTION            VARCHAR2(20) default 'INSERTED' not null
);
-- Create/Recreate primary, unique and foreign key constraints 
create index PWUST_SALES_ORDER_PK_INDX on PWUST_SALES_ORDER (SALES_ORDER);
alter table PWUST_SALES_ORDER
  add constraint PWUST_SALES_ORDER_FK foreign key (ENGINE_TYPE)
  references SFFND_PROGRAM_DEF (PROGRAM);
-- Grant/Revoke object privileges 
grant select on PWUST_SALES_ORDER to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUST_SALES_ORDER to SF$CONNECTION_POOL;