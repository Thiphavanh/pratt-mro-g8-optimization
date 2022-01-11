
create table PWUST_SYNC_INT_REPLY_DATA
(
  SERVICE_NAME  VARCHAR2(50) not null,
  OUTBOUND_DATA VARCHAR2(50) not null,
  DATA_RETURNED VARCHAR2(50),
  UPDT_USERID   VARCHAR2(50) default user not null,
  TIME_STAMP    DATE default sysdate not null,
  LAST_ACTION   VARCHAR2(20) default 'INSERTED' not null
)tablespace SFUPGRADE;

alter table PWUST_SYNC_INT_REPLY_DATA
  add constraint PWUST_SYNC_INT_REPLY_DATA_PK primary key (SERVICE_NAME, OUTBOUND_DATA)
  using index tablespace SFUPGRADE;
  
ALTER TABLE PWUST_SYNC_INT_REPLY_DATA ADD ERROR_MSG VARCHAR2(4000);  

grant select on PWUST_SYNC_INT_REPLY_DATA to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUST_SYNC_INT_REPLY_DATA to SF$CONNECTION_POOL;