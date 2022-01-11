
create table PWUST_ORDER_NUM
(
  sale_order_no VARCHAR2(20) not null,
  suffix        NUMBER not null,
  updt_userid   VARCHAR2(30) default user not null,
  time_stamp    DATE default sysdate not null
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
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table PWUST_ORDER_NUM
  add constraint PWUST_ORDER_NUM_PK primary key (SALE_ORDER_NO, SUFFIX)
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
-- Grant/Revoke object privileges 
grant select on PWUST_ORDER_NUM to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUST_ORDER_NUM to SF$CONNECTION_POOL;