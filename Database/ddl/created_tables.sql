create sequence PWUST_COCKPIT_HIST_SEQ 
minvalue 1 
maxvalue 999999999999999999999999999 
start with 1 
increment by 1 
cache 500 
order; 


-- Create table 

create table PWUST_DELIVERY_COCKPIT_HIST 
( 
  HIST_ID               NUMBER not null, 
  HIST_USERID           VARCHAR2(50) default user not null, 
  HIST_TIME_STAMP       DATE default sysdate not null, 
  HIST_ACTION           VARCHAR2(20) default 'INSERTED' not null, 
  ORDER_ID              VARCHAR2(40) not null, 
  SERIAL_ID             VARCHAR2(40) not null, 
  SERIAL_NO             VARCHAR2(40) not null, 
  SERIAL_NO_STATUS      VARCHAR2(20) not null, 
  DELIVER_SERIAL_STATUS VARCHAR2(40), 
  ENGINE_MANUAL         VARCHAR2(40), 
  INTDT                 DATE, 
  INT_RESPDT            DATE, 
  INT_RESPONSE          VARCHAR2(1000), 
  UPDT_USERID           VARCHAR2(40) 
);
-- Create/Recreate primary, unique and foreign key constraints  

alter table PWUST_DELIVERY_COCKPIT_HIST 
add constraint PWUST_DELIVERY_COCKPIT_HIST primary key (HIST_ID) 
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
