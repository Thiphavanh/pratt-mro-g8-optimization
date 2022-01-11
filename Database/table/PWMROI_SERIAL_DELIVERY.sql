-- Create table
create table PWMROI_SERIAL_DELIVERY
(
  order_id                 VARCHAR2(40) not null,
  serial_id                VARCHAR2(40) not null,
  engine_manual            VARCHAR2(40) not null,
  interface_transaction_id VARCHAR2(40) not null,
  last_action              VARCHAR2(40) default 'INSERTED' not null,
  updt_userid              VARCHAR2(40) default USER not null,
  time_stamp               DATE default SYSDATE not null,
  engine_manual_revision   VARCHAR2(40) not null,
  interface_response_id    VARCHAR2(40)
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
alter table PWMROI_SERIAL_DELIVERY
  add constraint PWMROI_SERIAL_DELIVERY_PK primary key (ORDER_ID, SERIAL_ID, ENGINE_MANUAL)
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
alter index PWMROI_SERIAL_DELIVERY_PK nologging;
