create table SFMFG.PWMROI_SERIAL_SCRAP_HIST 
( 
  HIST_ID                  NUMBER not null, 
  HIST_USERID              VARCHAR2(50) default user not null, 
  HIST_TIME_STAMP          DATE default sysdate not null, 
  HIST_ACTION              VARCHAR2(20) default 'INSERTED' not null, 
  ORDER_ID                 VARCHAR2(40) not null, 
  OPER_KEY                 NUMBER not null, 
  SERIAL_ID                VARCHAR2(40) not null, 
  SCRAP_TYPE               VARCHAR2(40) not null, 
  NON_REPAIR_TYPE          VARCHAR2(40) not null, 
  CATEGORY_1               VARCHAR2(40) not null, 
  CODE_1                   VARCHAR2(40) not null, 
  PRIMARY_SECONDARY_1      VARCHAR2(1) not null, 
  CATEGORY_2               VARCHAR2(40), 
  CODE_2                   VARCHAR2(40), 
  PRIMARY_SECONDARY_2      VARCHAR2(1), 
  CATEGORY_3               VARCHAR2(40), 
  CODE_3                   VARCHAR2(40), 
  PRIMARY_SECONDARY_3      VARCHAR2(1), 
  CATEGORY_4               VARCHAR2(40), 
  CODE_4                   VARCHAR2(40), 
  PRIMARY_SECONDARY_4      VARCHAR2(1), 
  CATEGORY_5               VARCHAR2(40), 
  CODE_5                   VARCHAR2(40), 
  PRIMARY_SECONDARY_5      VARCHAR2(1), 
  CATEGORY_6               VARCHAR2(40), 
  CODE_6                   VARCHAR2(40), 
  PRIMARY_SECONDARY_6      VARCHAR2(1), 
  SCRAP_TEXT               VARCHAR2(255), 
  ZCOM_SALES_ORDER         VARCHAR2(40), 
  INTERFACE_TRANSACTION_ID VARCHAR2(40) not null, 
  INTERFACE_RESPONSE_ID    VARCHAR2(40), 
  LAST_ACTION              VARCHAR2(20), 
  UPDT_USERID              VARCHAR2(50), 
  TIME_STAMP               DATE, 
  ERROR_MESSAGE            VARCHAR2(1000) 
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
alter table PWMROI_SERIAL_SCRAP_HIST 
add constraint PWMROI_SRL_SCRAP_HIST_PK primary key (HIST_ID)   
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
grant select on SFMFG.PWMROI_SERIAL_SCRAP_HIST to PRATT_READ_ONLY; 
grant select, insert, update, delete on SFMFG.PWMROI_SERIAL_SCRAP_HIST to PRATT_WRITE_ONLY; 
grant select, insert, update, delete on SFMFG.PWMROI_SERIAL_SCRAP_HIST to SF$CONNECTION_POOL;
grant select on PWUST_SERIAL_SCRAP_HIST_SEQ to SF$CONNECTION_POOL;
-- Create Sequence – Use to create PWUST_SERIAL_SCRAP_HIST.HIST_ID 
create sequence PWUST_SERIAL_SCRAP_HIST_SEQ 
minvalue 1 
maxvalue 999999999999999999999999999 
start with 1 
increment by 1 
cache 500 
order; 


