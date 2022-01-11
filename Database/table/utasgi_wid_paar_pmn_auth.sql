create table utasgi_wid_paar_pmn_auth
(
  request_id            varchar2(40) not null,
  auth_id               varchar2(40) not null,
  pmn_action            varchar2(20) not null,
  pmn_type              varchar2(20) not null,
  pmn_type_entity       varchar2(50) not null,
  pmn_eff_from_date     date,
  pmn_eff_thru_date     date,
  sght_id               varchar2(40),
  updt_userid           varchar2(30) default user not null,
  time_stamp            date default sysdate not null,
  last_action           varchar2(20) default 'INSERTED' not null  
)
tablespace sfwid_data;
/

alter table utasgi_wid_paar_pmn_auth
  add constraint utasgi_wid_paar_pmn_auth_pk primary key (request_id, auth_id)
  using index tablespace sfwid_indexes;
/

alter table utasgi_wid_paar_pmn_auth
  add constraint utasgi_wid_paar_pmn_auth_idx1 unique (request_id, pmn_type, pmn_type_entity)
  using index tablespace sfwid_indexes;
/

alter table utasgi_wid_paar_pmn_auth
  add constraint utasgi_wid_paar_pmn_auth_fk1 foreign key (request_id)
  references utasgi_wid_paar_desc (request_id) on delete cascade;
/