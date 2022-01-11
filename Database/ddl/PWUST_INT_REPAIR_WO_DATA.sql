create table PWUST_INT_REPAIR_WO_DATA
(
  PK_VALUE          VARCHAR2(40) not null,
  UPDT_USERID       VARCHAR2(30) default user not null,
  TIME_STAMP        DATE default sysdate not null,
  ACTION            VARCHAR2(20) default 'INSERTED' not null,
  PROCESS_FLAG      VARCHAR2(2),
  ORDER_CREATE_DATE DATE,
  SAP_USER_NAME     VARCHAR2(40),
  SM_ORDER          VARCHAR2(40) not null,
  MATERIAL          VARCHAR2(40),
  MATERIAL_DESC     VARCHAR2(40),
  QUANTITY          NUMBER,
  SALES_ORDER       VARCHAR2(40),
  CUSTOMER_NUMBER   VARCHAR2(40),
  CUSTOMER_NAME     VARCHAR2(40),
  LOT_CODE          VARCHAR2(40),
  ERROR_DESC        VARCHAR2(4000)
)tablespace SFUPGRADE;


alter table PWUST_INT_REPAIR_WO_DATA  add constraint PWUST_INT_REPAIR_WO_DATA_PK primary key (PK_VALUE, SM_ORDER)  using index  tablespace SFUPGRADE;

grant select on PWUST_INT_REPAIR_WO_DATA to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUST_INT_REPAIR_WO_DATA to SF$CONNECTION_POOL;
