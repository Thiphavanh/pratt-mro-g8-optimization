-- Create table
create table PWUST_EXPORT_AUDIT_ARCH_DATE
(
  arch_date DATE not null,
  userid    VARCHAR2(40) default USER not null
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table PWUST_EXPORT_AUDIT_ARCH_DATE
  add constraint PWUST_EXPORT_AUDIT_ARC_DATE_PK primary key (ARCH_DATE, USERID);

-- Grant/Revoke object privileges 
grant select on PWUST_EXPORT_AUDIT_ARCH_DATE to PRATT_READ_ONLY;
grant select, insert, update, delete on PWUST_EXPORT_AUDIT_ARCH_DATE to SF$CONNECTION_POOL;
grant select, insert, update on PWUST_EXPORT_AUDIT_ARCH_DATE to SOLPERFREPORTER_READONLY;
