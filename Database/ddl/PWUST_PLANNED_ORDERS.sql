-- Create table
create table PWUST_PLANNED_ORDERS
(
  primery_key      VARCHAR2(40) not null,
  sales_order      VARCHAR2(20),
  engine_type      VARCHAR2(9),
  engine_model     VARCHAR2(18),
  sapworkloc       VARCHAR2(4),
  work_scope       VARCHAR2(10),
  item_no_sel      VARCHAR2(50),
  superiornet      VARCHAR2(4),
  subnet           VARCHAR2(8),
  customer         VARCHAR2(10),
  plan_id          VARCHAR2(40),
  plan_version     NUMBER,
  plan_revision    NUMBER,
  plan_alterations NUMBER,
  plan_updt_no     NUMBER,
  part_no          VARCHAR2(50),
  updt_userid      VARCHAR2(50) default USER not null,
  time_stamp       DATE default SYSDATE not null,
  last_action      VARCHAR2(20) default 'INSERTED' not null
)
tablespace SFUPGRADE
  pctfree 10
  initrans 1
  maxtrans 255;
-- Create/Recreate primary, unique and foreign key constraints 
alter table PWUST_PLANNED_ORDERS
  add constraint PWUST_PLANNED_ORDERS_PK primary key (PRIMERY_KEY)
  using index 
  tablespace SFUPGRADE
  pctfree 10
  initrans 2
  maxtrans 255;
alter table PWUST_PLANNED_ORDERS
  add constraint PWUST_PLANNED_ORDERS_FK foreign key (PLAN_ID, PLAN_VERSION, PLAN_REVISION, PLAN_ALTERATIONS)
  references SFPL_PLAN_REV (PLAN_ID, PLAN_VERSION, PLAN_REVISION, PLAN_ALTERATIONS);
-- Grant/Revoke object privileges 
grant select on PWUST_PLANNED_ORDERS to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUST_PLANNED_ORDERS to SF$CONNECTION_POOL;