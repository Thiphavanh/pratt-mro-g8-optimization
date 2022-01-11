create table PWUI_INT_REPAIR_WO_SERIALS
(
  PK_VALUE      VARCHAR2(40) not null,
  SM_ORDER      VARCHAR2(40) not null,
  UPDT_USERID   VARCHAR2(30) default user not null,
  TIME_STAMP    DATE default sysdate not null,
  ACTION        VARCHAR2(20) default 'INSERTED' not null,
  SERIAL_NUMBER VARCHAR2(50) not null
)tablespace SFUPGRADE;

alter table PWUI_INT_REPAIR_WO_SERIALS  add constraint PWUI_INT_REPAIR_WO_SERIALS_PK primary key (PK_VALUE, SM_ORDER, SERIAL_NUMBER)   using index   tablespace SFUPGRADE;
alter table PWUI_INT_REPAIR_WO_SERIALS  add constraint PWUI_INT_REPAIR_WO_SERIALS_FK foreign key (PK_VALUE, SM_ORDER)  references PWUST_INT_REPAIR_WO_DATA (PK_VALUE, SM_ORDER) on delete cascade;

grant select on PWUI_INT_REPAIR_WO_SERIALS to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUI_INT_REPAIR_WO_SERIALS to SF$CONNECTION_POOL;

