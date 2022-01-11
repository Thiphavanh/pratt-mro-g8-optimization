-- Create table
create table PWMROI_ZCOM_SALES_ORDERS
(
  work_location        VARCHAR2(40) not null,
  zcom_sales_order     VARCHAR2(40) not null,
  description          VARCHAR2(255) not null,
  sold_to              VARCHAR2(40) not null,
  obsolete_record_flag VARCHAR2(1) default 'N',
  last_action          VARCHAR2(20) default 'INSERTED' not null,
  updt_userid          VARCHAR2(50) default USER not null,
  time_stamp           DATE default SYSDATE not null
)
tablespace SFWID_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table PWMROI_ZCOM_SALES_ORDERS
  add constraint ZCOM_SALES_ORDERS_PK primary key (WORK_LOCATION, ZCOM_SALES_ORDER)
  using index 
  tablespace SFUPGRADE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter index ZCOM_SALES_ORDERS_PK nologging;
-- Grant/Revoke object privileges 
grant select, insert, update, delete, alter on PWMROI_ZCOM_SALES_ORDERS to SF$CONNECTION_POOL;
