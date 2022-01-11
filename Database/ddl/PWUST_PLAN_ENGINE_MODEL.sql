-- Create table
create table PWUST_PLAN_ENGINE_MODEL
(
  plan_id      VARCHAR2(40) not null,
  plan_updt_no NUMBER default 1 not null,
  engine_model VARCHAR2(20) not null,
  updt_userid  VARCHAR2(30) default USER not null,
  time_stamp   DATE default SYSDATE not null,
  last_action  VARCHAR2(20) default 'INSERTED'
)
tablespace SFPL_DATA
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
alter table PWUST_PLAN_ENGINE_MODEL
  add constraint PWUST_PLAN_ENGINE_MODEL_PK primary key (PLAN_ID, PLAN_UPDT_NO, ENGINE_MODEL)
  using index 
  tablespace SFPL_INDEXES
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
alter table PWUST_PLAN_ENGINE_MODEL
  add constraint PWUST_PLAN_ENGINE_MODEL_FK_1 foreign key (PLAN_ID, PLAN_UPDT_NO)
  references SFPL_PLAN_DESC (PLAN_ID, PLAN_UPDT_NO) on delete cascade;
-- Grant/Revoke object privileges 
grant select on PWUST_PLAN_ENGINE_MODEL to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUST_PLAN_ENGINE_MODEL to SF$CONNECTION_POOL;