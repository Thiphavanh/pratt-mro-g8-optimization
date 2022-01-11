create table PWUST_SFWID_ORDER_DESC
(
  ORDER_ID     VARCHAR2(40) not null,
  PW_ORDER_NO  VARCHAR2(80) not null,
  SALES_ORDER  VARCHAR2(20),
  ENGINE_MODEL VARCHAR2(18),
  WORK_SCOPE   VARCHAR2(2000),
  SUPERIORNET  VARCHAR2(2000),
  SUBNET       VARCHAR2(2000),
  CUSTOMER     VARCHAR2(2000),
  UPDT_USERID  VARCHAR2(50) default USER not null,
  TIME_STAMP   DATE default SYSDATE not null
)tablespace SFUPGRADE;


alter table PWUST_SFWID_ORDER_DESC   add constraint PWUST_SFWID_ORDER_DESC_K primary key (ORDER_ID)  using index   tablespace SFUPGRADE;

alter table PWUST_SFWID_ORDER_DESC  add constraint PWUST_SFWID_ORDER_DESC_UNIQUE unique (PW_ORDER_NO)  using index   tablespace SFUPGRADE;

alter table PWUST_SFWID_ORDER_DESC   add constraint PWUST_SFWID_ORDER_DESC_FK foreign key (ORDER_ID)  references SFWID_ORDER_DESC (ORDER_ID) on delete cascade;

grant select on PWUST_SFWID_ORDER_DESC to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUST_SFWID_ORDER_DESC to SF$CONNECTION_POOL;
