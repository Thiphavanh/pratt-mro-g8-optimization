-------------------------------------------------
-- Export file for user SFMFG                  --
-- Created by c079222 on 5/24/2019, 1:36:19 PM --
-------------------------------------------------


prompt
prompt Creating table PWUST_INT_SAP_SM_SERIALS
prompt =======================================
prompt
create table SFMFG.PWUST_INT_SAP_SM_SERIALS
(
  IDOC_NUMBER   VARCHAR2(16) not null,
  SM_ORDER      VARCHAR2(12) not null,
  SERIAL_NUMBER VARCHAR2(30) not null,
  UPDT_USERID   VARCHAR2(50) default user not null,
  TIME_STAMP    DATE default sysdate not null,
  LAST_ACTION   VARCHAR2(20) default 'INSERTED' not null
)
;
alter table SFMFG.PWUST_INT_SAP_SM_SERIALS
  add constraint PWUST_INT_SAP_SM_SERIALS_PK primary key (IDOC_NUMBER, SM_ORDER, SERIAL_NUMBER);
alter table SFMFG.PWUST_INT_SAP_SM_SERIALS
  add constraint PWUST_INT_SAP_SM_SERIALS_FK foreign key (IDOC_NUMBER, SM_ORDER)
  references SFMFG.PWUST_INT_SAP_SM_ORDER (IDOC_NUMBER, SM_ORDER) on delete cascade;
grant select on SFMFG.PWUST_INT_SAP_SM_SERIALS to PRATT_READ_ONLY;
grant select, insert, update, delete on SFMFG.PWUST_INT_SAP_SM_SERIALS to SF$CONNECTION_POOL;

