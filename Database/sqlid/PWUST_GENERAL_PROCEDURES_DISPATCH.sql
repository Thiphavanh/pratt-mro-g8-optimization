
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_GENERAL_PROCEDURES_DISPATCH.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_GENERAL_PROCEDURES_DISPATCH';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_GENERAL_PROCEDURES_DISPATCH';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='smro_woe_303 defect 1899';
v_sql_text sfcore_sql_lib.sql_text%type  :='select x.pwp_id as release_pkg,
t.plan_id,
plan_no,part_no,
t.part_chg,
t.plan_revision,
t.plan_version,
t.plan_alterations,
plan_type,
plan_title,
rev_status,
program,
item_type,
item_subtype,
model,
plnd_work_loc,
plnd_cust_id,
t.security_group
from SFPL_PLAN_V t, sfpl_plan_pwp_xref x
where t.plan_id = x.plan_id
and t.plan_revision = x.plan_revision
and t.plan_version = x.plan_version
and t.plan_type = ''GENERAL PROCEDURES''
and t.plan_revision = (select max(z.plan_revision) from sfpl_plan_v z where z.plan_id = t.plan_id and rev_status in (''PLAN COMPLETE'',''PLAN RELEASED'') )
and rev_status in (''PLAN COMPLETE'',''PLAN RELEASED'')';
begin
begin
  insert into sfcore_sql_lib(sql_id,sql_id_displ,updt_userid,time_stamp,read_only,datasource,stype,description,sql_text)
    values(v_sql_id,v_sql_id_displ,user,sysdate,v_read_only,v_datasource,v_stype,v_description,v_sql_text);
  commit;
exception when dup_val_on_index then
  begin
    update sfcore_sql_lib
    set sql_text=v_sql_text,updt_userid=user,time_stamp=sysdate,sql_id_displ = v_sql_id_displ,
read_only=v_read_only,datasource=v_datasource,stype=v_stype,
description=v_description
    where sql_id=v_sql_id;
    commit;
  exception when others then
  	 sffnd_show_execution_message('DML',v_sql_id_displ, SQLCODE,SQLERRM,' update sfcore_sql_lib ');
  end;
when others then
  sffnd_show_execution_message('DML',v_sql_id_displ, SQLCODE,SQLERRM,' insert into sfcore_sql_lib ');
end;
begin
 if v_folder_id is not null then
  insert into sfcore_sql_lib_folder(sql_id,folder_id,updt_userid,time_stamp)
  values(v_sql_id,v_folder_id,user,sysdate);
  commit;
 end if;
exception when others then 
 sffnd_show_execution_message('DML',v_sql_id_displ, SQLCODE,SQLERRM,' insert into sfcore_sql_lib_folder ');
end;
end;
/

set define on

