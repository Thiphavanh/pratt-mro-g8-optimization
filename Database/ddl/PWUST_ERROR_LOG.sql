-- Create table
create table PWUST_ERROR_LOG
(
  error_id    VARCHAR2(40) not null,
  updt_userid VARCHAR2(30) default user not null,
  time_stamp  DATE default sysdate not null,
  last_action VARCHAR2(20) default 'INSERTED',
  load_id     VARCHAR2(40),
  trans_type  VARCHAR2(30),
  return_code VARCHAR2(30),
  msg_id      VARCHAR2(40),
  key1        VARCHAR2(100),
  key2        VARCHAR2(100),
  key3        VARCHAR2(100),
  msg_text    VARCHAR2(2000),
  key4        VARCHAR2(100),
  key5        VARCHAR2(100),
  key6        VARCHAR2(100),
  key7        VARCHAR2(100),
  key8        VARCHAR2(100),
  key9        VARCHAR2(100),
  key10       VARCHAR2(100),
  key11       VARCHAR2(100),
  key12       VARCHAR2(100),
  key13       VARCHAR2(100),
  key14       VARCHAR2(100),
  key15       VARCHAR2(100),
  key16       VARCHAR2(100),
  key17       VARCHAR2(100),
  key18       VARCHAR2(100),
  key19       VARCHAR2(100),
  key20       VARCHAR2(100)
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
-- Add comments to the table 
comment on table PWUST_ERROR_LOG
  is 'PWUST-routine errors.';
-- Add comments to the columns 
comment on column PWUST_ERROR_LOG.error_id
  is 'System-generated identifier of the error.';
comment on column PWUST_ERROR_LOG.updt_userid
  is 'Solumina login ID of the last user to manipulate the record.';
comment on column PWUST_ERROR_LOG.time_stamp
  is 'The date and time the record was last manipulated.';
comment on column PWUST_ERROR_LOG.last_action
  is 'The last operation (update, insert, copy, etc.) applied to the record.';
comment on column PWUST_ERROR_LOG.load_id
  is 'Unique, system-generated identifier of the loading instance associated with the given error.';
comment on column PWUST_ERROR_LOG.trans_type
  is 'The transaction type producing the given error.';
comment on column PWUST_ERROR_LOG.return_code
  is 'The return code of the error.';
comment on column PWUST_ERROR_LOG.msg_id
  is 'Unique, system-generated identifier of the error message.';
comment on column PWUST_ERROR_LOG.key1
  is 'Primary key column of the object associated with the given error.';
comment on column PWUST_ERROR_LOG.key2
  is 'Primary key column of the object associated with the given error.';
comment on column PWUST_ERROR_LOG.key3
  is 'Primary key column of the object associated with the given error.';
comment on column PWUST_ERROR_LOG.msg_text
  is 'Text of the error message.';
-- Create/Recreate indexes 
create index PWUST_ERROR_LOG_FK001 on PWUST_ERROR_LOG (MSG_ID)
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
create index PWUST_ERROR_LOG_FK002 on PWUST_ERROR_LOG (LOAD_ID)
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
-- Create/Recreate primary, unique and foreign key constraints 
alter table PWUST_ERROR_LOG
  add constraint PWUST_ERROR_LOG_PK primary key (ERROR_ID)
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
grant select, insert, update, delete on PWUST_ERROR_LOG to SF$CONNECTION_POOL;
