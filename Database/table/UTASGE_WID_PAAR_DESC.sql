-- Create table
create table UTASGE_WID_PAAR_DESC
(
  request_id            VARCHAR2(40) not null,
  request_status        VARCHAR2(20) not null,
  request_userid        VARCHAR2(30) default user not null,
  request_creation_date DATE default sysdate not null,
  request_reason        VARCHAR2(255),
  order_no              VARCHAR2(40) not null,
  order_status          VARCHAR2(20),
  order_title           VARCHAR2(255),
  bom_no                VARCHAR2(60),
  bom_rev               VARCHAR2(4),
  facility_id           VARCHAR2(30),
  facility_desc         VARCHAR2(255),
  item_no               VARCHAR2(50),
  item_rev              VARCHAR2(10),
  item_title            VARCHAR2(255),
  item_type             VARCHAR2(30),
  item_subtype          VARCHAR2(80),
  actual_start_date     DATE,
  actual_end_date       DATE,
  sched_start_date      DATE,
  sched_end_date        DATE,
  reply_required_date   DATE,
  prior_request_id      VARCHAR2(40),
  updt_userid           VARCHAR2(30) default user not null,
  time_stamp            DATE default sysdate not null,
  last_action           VARCHAR2(20) default 'INSERTED' not null,
  approving_user_id     VARCHAR2(30)
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
/
-- Create/Recreate indexes 
create index UTASGE_WID_PAAR_DESC_IX1 on UTASGE_WID_PAAR_DESC (ORDER_NO)
  tablespace SFWID_INDEXES
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  )
  nologging;
/
-- Create/Recreate primary, unique and foreign key constraints 
alter table UTASGE_WID_PAAR_DESC
  add constraint UTASGE_WID_PAAR_DESC_PK primary key (REQUEST_ID)
  using index 
  tablespace SFWID_INDEXES
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
/
alter index UTASGE_WID_PAAR_DESC_PK nologging;
/
