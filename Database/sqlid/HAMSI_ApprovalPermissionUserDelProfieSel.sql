
set define off
prompt ---------------------------------------;
prompt Executing ... HAMSI_APPROVALPERMISSIONUSERDELPROFILESEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='FOUNDATION';
v_sql_id sfcore_sql_lib.sql_id%type :='HAMSI_APPROVALPERMISSIONUSERDELPROFILESEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='HAMSI_ApprovalPermissionUserDelProfileSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFPL';
v_description sfcore_sql_lib.description%type  :='Select User Profile in Approval Permission User Grid';
v_sql_text sfcore_sql_lib.sql_text%type  :='select a.sght_id, a.auth_id, a.pmn_action, a.pmn_status, a.pmn_type_entity, b.first_name, b.last_name, b.full_name,
c.work_loc, c.work_dept, c.work_center, a.pmn_type, a.pmn_eff_from_date, a.pmn_eff_thru_date, a.updt_userid, a.time_stamp, a.last_action
from utasge_plg_paar_pmn_auth a, sffnd_user b, sffnd_work_loc_v c
where request_id  = :request_id
     and pmn_type = ''USER''
     and pmn_action = ''DELETED''
     and pmn_type_entity = userid     
     and b.location_id = c.location_id
     and b.department_id = c.department_id
     and b.center_id = c.center_id
 order by pmn_type_entity';
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

