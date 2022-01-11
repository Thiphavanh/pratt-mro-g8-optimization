-- Create table
create table PWMROI_SERIAL_SCRAP
(
  order_id                          VARCHAR2(40) not null,
  oper_key                          NUMBER not null,
  serial_id                         VARCHAR2(40) not null,
  scrap_type                        VARCHAR2(40) not null,
  non_repair_type                   VARCHAR2(40) not null,
  category_1                        VARCHAR2(40) not null,
  code_1                            VARCHAR2(40) not null,
  primary_secondary_1               VARCHAR2(1) not null,
  category_2                        VARCHAR2(40),
  code_2                            VARCHAR2(40),
  primary_secondary_2               VARCHAR2(1),
  category_3                        VARCHAR2(40),
  code_3                            VARCHAR2(40),
  primary_secondary_3               VARCHAR2(1),
  category_4                        VARCHAR2(40),
  code_4                            VARCHAR2(40),
  primary_secondary_4               VARCHAR2(1),
  category_5                        VARCHAR2(40),
  code_5                            VARCHAR2(40),
  primary_secondary_5               VARCHAR2(1),
  category_6                        VARCHAR2(40),
  code_6                            VARCHAR2(40),
  primary_secondary_6               VARCHAR2(1),
  scrap_text                        VARCHAR2(255),
  zcom_sales_order                  VARCHAR2(40),
  interface_transaction_id          VARCHAR2(40) not null,
  interface_response_id             VARCHAR2(40),
  last_action                       VARCHAR2(20),
  updt_userid                       VARCHAR2(50),
  time_stamp                        DATE
)
tablespace SFUPGRADE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  )
nologging;
-- Create/Recreate primary, unique and foreign key constraints 
alter table PWMROI_SERIAL_SCRAP
  add constraint PWMROI_SRL_SCRAP_PK primary key (ORDER_ID, OPER_KEY, SERIAL_ID)
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
alter index PWMROI_SRL_SCRAP_PK nologging;
-- Grant/Revoke object privileges 
grant select, insert, update, delete on PWMROI_SERIAL_SCRAP to SF$CONNECTION_POOL;
