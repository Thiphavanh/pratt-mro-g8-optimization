set define off
declare
v_folder_id varchar2(40) :='';
v_sql_id varchar2(85) :='HAMSI_PLG_PAARREQHDRBYPLANQRY';
v_sql_id_displ varchar2(85) :='HAMSI_PLG_PaarReqHdrByPlanQry';
v_read_only number :='';
v_datasource varchar2(32) :='';
v_stype varchar2(32) :='SFPL';
v_site varchar2(10) :='HAMSI';
v_description varchar2(200) :='PAAR Requests for plan';
v_sql_text varchar2(4000) :='select a.request_id,
       a.request_status,
       a.plan_version as plan_ver,
       a.plan_revision as plan_rev,
       a.plan_alterations as plan_alt,
       a.request_userid,
       a.request_creation_date,
       a.request_reason,
       a.reply_required_date,
       a.prior_request_id,
       a.updt_userid,
       a.time_stamp,
       a.last_action
  from utasgi_plg_paar_desc a
 where a.plan_id = :plan_id
 order by case a.request_status when ''OPEN'' then 1 when ''IN REVIEW'' then 2 else 3 end, a.time_stamp desc';

begin
begin
insert into sfcore_sql_lib(sql_id,sql_id_displ,updt_userid,time_stamp,read_only,datasource,stype, description,sql_text)
    values(v_sql_id,v_sql_id_displ,user,sysdate,v_read_only,v_datasource,v_stype, v_description,replace(v_sql_text,chr(10),chr(13)||chr(10)));
commit;
exception when dup_val_on_index then
  update sfcore_sql_lib
  set sql_text=replace(v_sql_text,chr(10),chr(13)||chr(10)),updt_userid=user,time_stamp=sysdate,
read_only=v_read_only,datasource=v_datasource,stype=v_stype,  
description=v_description
  where sql_id=v_sql_id;
  commit;
end;
begin
if v_folder_id is not null then
insert into sfcore_sql_lib_folder(sql_id,folder_id,updt_userid,time_stamp)
values(v_sql_id,v_folder_id,user,sysdate);
commit;
end if;
exception when others then null;
end;
end;
/

set define on

