create table PWPERF_ERRLOG
(
  errcode    INTEGER,
  errmsg     VARCHAR2(200),
  errval     VARCHAR2(200),
  created_on DATE,
  created_by VARCHAR2(100),
  machine    VARCHAR2(100),
  program    VARCHAR2(100)
)
tablespace SFUPGRADE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 80K
    next 1M
    minextents 1
    maxextents unlimited
  );

-- Grant/Revoke object privileges 
grant select, insert, update, delete on PWPERF_ERRLOG to AWRTREND_ROLE;
grant select on PWPERF_ERRLOG to PRATT_READ_ONLY;
grant select, insert, update, delete on PWPERF_ERRLOG to SF$CONNECTION_POOL;
