set define off
declare
v_folder_id varchar2(40) :='';
v_sql_id varchar2(85) :='HAMSI_REQUESTSFORAPPROVALDIS';
v_sql_id_displ varchar2(85) :='HAMSI_RequestsforApprovalDis';
v_read_only number :='';
v_datasource varchar2(32) :='';
v_stype varchar2(32) :='SFPL';
v_site varchar2(10) :='HAMSI';
v_description varchar2(200) :='Requests for Plan Approval Dispatch';
v_sql_text varchar2(4000) :='select a.request_id,
       a.request_status,
       a.request_userid,
       a.request_creation_date,
       a.request_reason,
       a.plan_no,
       a.plan_version,
       a.plan_revision,
       a.plan_alterations,
       a.plan_type,
       a.plan_title,
       a.bom_no,
       a.bom_rev,
       a.facility_id,
       a.facility_desc,
       a.item_no,
       a.item_rev,
       a.item_title,
       a.item_type,
       a.item_subtype,
       a.reply_required_date,
       a.prior_request_id,
       a.approving_user_id,
       a.time_stamp,
       a.last_action,
       b.plan_id,
       b.security_group
  from sfpl_plan_v b, utasge_plg_paar_desc a
 where b.plan_no = a.plan_no
   and b.plan_version = a.plan_version
   and b.plan_revision = a.plan_revision
   and b.plan_alterations = a.plan_alterations
 order by case a.request_status when ''OPEN'' then 1 else 2 end, a.time_stamp desc';
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

