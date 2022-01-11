-------------------------------------------------
-- Export file for user SFMFG                  --
-- Created by c079222 on 5/24/2019, 1:35:52 PM --
-------------------------------------------------


prompt
prompt Creating table PWUST_INT_SAP_SM_ORDER
prompt =====================================
prompt
create table SFMFG.PWUST_INT_SAP_SM_ORDER
(
  IDOC_NUMBER            VARCHAR2(16) not null,
  MESSAGE_TYPE           VARCHAR2(3),
  SAP_UPDT_USERID        VARCHAR2(40),
  ORDER_TYPE             VARCHAR2(4),
  PLANT                  VARCHAR2(4),
  NOTIFICATION           VARCHAR2(12),
  SALES_ORDER            VARCHAR2(10),
  SALES_DOC_ITEM         VARCHAR2(10),
  SUPERIOR_SM_ORDER      VARCHAR2(12),
  SM_ORDER               VARCHAR2(12) not null,
  CUSTOMER_NUMBER        VARCHAR2(10),
  CUSTOMER_NAME          VARCHAR2(35),
  PURCHASE_ORDER         VARCHAR2(20),
  QUOTE_REQUIRED         VARCHAR2(3),
  REQUIRED_DELIVERY_DATE VARCHAR2(8),
  ENGINE_MODEL           VARCHAR2(18),
  ENGINE_SERIAL_NUMBER   VARCHAR2(18),
  ENGINE_SECTION         VARCHAR2(18),
  ENGINE_SECTION_DESC    VARCHAR2(40),
  INCOMING_MATERIAL      VARCHAR2(18),
  OUTGOING_MATERIAL      VARCHAR2(18),
  QUANTITY               NUMBER,
  LOT_CODE               VARCHAR2(7),
  ORDER_ID               VARCHAR2(40),
  SOL_ORD_STATUS         VARCHAR2(2),
  ORDER_CREATE_DATE      DATE,
  SOL_ERROR_TEXT         VARCHAR2(4000),
  UPDT_USERID            VARCHAR2(50) default user not null,
  TIME_STAMP             DATE default sysdate not null,
  LAST_ACTION            VARCHAR2(20) default 'INSERTED' not null
)
;
comment on table SFMFG.PWUST_INT_SAP_SM_ORDER
  is 'T301 SM Order Intermediate Table.';
comment on column SFMFG.PWUST_INT_SAP_SM_ORDER.SAP_UPDT_USERID
  is 'SAP UPDT_USERID';
comment on column SFMFG.PWUST_INT_SAP_SM_ORDER.INCOMING_MATERIAL
  is 'Part Number';
comment on column SFMFG.PWUST_INT_SAP_SM_ORDER.SOL_ORD_STATUS
  is 'Order processing status: PENDING, CREATED, SYNCED, ERROR.';
comment on column SFMFG.PWUST_INT_SAP_SM_ORDER.SOL_ERROR_TEXT
  is 'If error encountered while processing imcoming message or order create, error desription.';
comment on column SFMFG.PWUST_INT_SAP_SM_ORDER.UPDT_USERID
  is 'Solumina login ID of the last user to manipulate the record.';
comment on column SFMFG.PWUST_INT_SAP_SM_ORDER.TIME_STAMP
  is 'The date and time the record was last manipulated.';
comment on column SFMFG.PWUST_INT_SAP_SM_ORDER.LAST_ACTION
  is 'The last operation (update, insert, copy, etc.) applied to the record.';
alter table SFMFG.PWUST_INT_SAP_SM_ORDER
  add constraint PWUST_INT_SAP_SM_ORDER_PK primary key (IDOC_NUMBER, SM_ORDER);
grant select on SFMFG.PWUST_INT_SAP_SM_ORDER to PRATT_READ_ONLY;
grant select, insert, update, delete on SFMFG.PWUST_INT_SAP_SM_ORDER to SF$CONNECTION_POOL;


