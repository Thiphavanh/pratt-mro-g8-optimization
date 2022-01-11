-- Create table
create table PWUST_EXPORT_AUDIT_LOG
(
  time_stamp     DATE default SYSDATE not null,
  userid         VARCHAR2(40) default USER not null,
  application    VARCHAR2(20) not null,
  business_unit  VARCHAR2(4) not null,
  jurisdiction   VARCHAR2(50) not null,
  classification VARCHAR2(50) not null,
  sme            VARCHAR2(1) not null,
  auth_license   VARCHAR2(20),
  documentid     VARCHAR2(40) not null
);
-- Create/Recreate primary, unique and foreign key constraints
alter table PWUST_EXPORT_AUDIT_LOG
  add constraint PWUST_EXPORT_AUDIT_LOG_PK primary key (TIME_STAMP, USERID);
  
-- Create/Recreate indexes
create index PWUST_EXPORT_AUDIT_LOG_IDX1 on PWUST_EXPORT_AUDIT_LOG (USERID, DOCUMENTID, TIME_STAMP);

-- Grant/Revoke object privileges
grant select on PWUST_EXPORT_AUDIT_LOG to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUST_EXPORT_AUDIT_LOG to SF$CONNECTION_POOL;
grant select on PWUST_EXPORT_AUDIT_LOG to SOLPERFREPORTER_READONLY;

