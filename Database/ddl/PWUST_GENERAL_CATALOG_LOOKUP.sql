-------------------------------------------------
-- Export file for user SFMFG                  --
-- Created by xcs2643 on 8/2/2019, 1:14:52 PM  --
-------------------------------------------------


prompt
prompt Creating table PWUST_GENERAL_CATALOG_LOOKUP
prompt ===========================================
prompt
create table SFMFG.PWUST_GENERAL_CATALOG_LOOKUP
(
  WORK_LOC               VARCHAR2(4) not null,
  CATALOG                VARCHAR2(3) not null,
  CATEGORY               VARCHAR2(8) not null,
  CATEGORY_DESC          VARCHAR2(40),
  CODE                   VARCHAR2(4) not null,
  CODE_DESC              VARCHAR2(40),
  OBSOLETE_RECORD_FLAG   VARCHAR2(1) not null,
  UPDT_USERID            VARCHAR2(50) default user not null,
  TIME_STAMP             DATE default sysdate not null
)
;
comment on table SFMFG.PWUST_GENERAL_CATALOG_LOOKUP
  is 'M316 Scrap Codes and Hold Types Groups Table';
comment on column SFMFG.PWUST_GENERAL_CATALOG_LOOKUP.WORK_LOC
  is 'Work Location';
comment on column SFMFG.PWUST_GENERAL_CATALOG_LOOKUP.CATALOG
  is 'Catalog';
comment on column SFMFG.PWUST_GENERAL_CATALOG_LOOKUP.CATEGORY
  is 'Category';
comment on column SFMFG.PWUST_GENERAL_CATALOG_LOOKUP.CATEGORY_DESC
  is 'Category Description';
comment on column SFMFG.PWUST_GENERAL_CATALOG_LOOKUP.CODE
  is 'Code';
comment on column SFMFG.PWUST_GENERAL_CATALOG_LOOKUP.CODE_DESC
  is 'Code Description';
comment on column SFMFG.PWUST_GENERAL_CATALOG_LOOKUP.UPDT_USERID
  is 'Solumina login ID of the last user to manipulate the record.';
comment on column SFMFG.PWUST_GENERAL_CATALOG_LOOKUP.TIME_STAMP
  is 'The date and time the record was last manipulated.';
alter table SFMFG.PWUST_GENERAL_CATALOG_LOOKUP
  add constraint PWUST_GENERAL_CATALOG_LU_PK primary key (WORK_LOC, CATALOG, CATEGORY, CODE);
grant select on SFMFG.PWUST_GENERAL_CATALOG_LOOKUP to PRATT_READ_ONLY;
grant select, insert, update, delete on SFMFG.PWUST_GENERAL_CATALOG_LOOKUP to SF$CONNECTION_POOL;


