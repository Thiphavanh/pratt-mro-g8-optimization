
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_SELECTADDITIONALPLANINFORMATION.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_SELECTADDITIONALPLANINFORMATION';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_SelectAdditionalPlanInformation';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFFND';
v_description sfcore_sql_lib.description%type  :='SMRO_EXPT_201';
v_sql_text sfcore_sql_lib.sql_text%type  :='select a.plan_id,
       program,
       plan_no,
       part_no,
       ucf_plan_flag2,
       ucf_plan_vch1,
       ucf_plan_vch2,
       ucf_plan_vch11,
       ucf_plan_vch5,
       ucf_plan_vch6,
       ucf_plan_vch7,
       plan_type,
       ucf_plan_vch4000_1,
       mfg_bom_chg,
       c.pwp_id,
       b.plan_revision,
       plan_title,
       security_group
  from sfpl_plan_desc a, sfpl_plan_rev b, sfpl_plan_pwp_xref c
 where a.plan_id = b.plan_id
   AND a.plan_updt_no = b.plan_updt_no
   AND C.PLAN_ID = B.PLAN_ID
   AND C.PLAN_VERSION = B.PLAN_VERSION
   AND C.PLAN_REVISION = B.PLAN_REVISION
   AND C.PLAN_ALTERATIONS = B.PLAN_ALTERATIONS
   AND b.plan_id = :plan_id
   AND b.plan_version = :plan_version
   AND b.plan_revision = :plan_revision
   AND b.plan_alterations = :plan_alterations';
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

