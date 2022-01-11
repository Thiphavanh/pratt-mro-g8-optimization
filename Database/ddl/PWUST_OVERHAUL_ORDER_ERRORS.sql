create table PWUST_OVERHAUL_ORDER_ERRORS
(
  UPDT_USERID          VARCHAR2(50) default USER not null,
  TIME_STAMP           DATE default SYSDATE not null,
  ERROR_MSG            VARCHAR2(2000),
  ORDER_NO             VARCHAR2(40),
  PART_NO              VARCHAR2(50),
  PLAN_ID              VARCHAR2(40),
  PLAN_VER             NUMBER,
  PLAN_REV             NUMBER,
  PLAN_ALT             NUMBER,
  PLAN_UPDT_NO         NUMBER,
  SALES_ORDER          VARCHAR2(20),
  ENGINE_TYPE          VARCHAR2(30),
  ENGINE_MODEL         VARCHAR2(20),
  WORK_LOC             VARCHAR2(30),
  WORK_SCOPE           VARCHAR2(10),
  SUPERIOR_NETWORK_ACT VARCHAR2(12),
  SUB_NETWORK_ACT      VARCHAR2(8),
  CUSTOMER             VARCHAR2(40),
  CALLED_FROM          VARCHAR2(20)
) tablespace SFWID_DATA;


-- Grant/Revoke object privileges 
grant select on PWUST_OVERHAUL_ORDER_ERRORS to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUST_OVERHAUL_ORDER_ERRORS to SF$CONNECTION_POOL;
