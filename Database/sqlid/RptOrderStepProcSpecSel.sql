
set define off
prompt ---------------------------------------;
prompt Executing ... RPTORDERSTEPPROCSPECSEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='FOUNDATION';
v_sql_id sfcore_sql_lib.sql_id%type :='RPTORDERSTEPPROCSPECSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='RptOrderStepProcSpecSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='';
v_sql_text sfcore_sql_lib.sql_text%type  :='select d.classification, d.PROC_SPEC_NO, d.PROC_SPEC_CHG, d.object_desc, d.object_rev, b.security_group
  from SFMFG.sffnd_htref_wid_oper_text a,
       SFMFG.sfcore_mm_object b, SFMFG.sfwid_oper_desc c,
       SFMFG.sffnd_process_spec_objects_v d
where a.object_id = b.object_id and b.object_id = d.object_id (+)
  and c.order_id = a.order_id and c.oper_key = a.oper_key and c.step_key = a.step_key
  and a.order_id =:order_id
  and a.oper_key =:oper_key
  and d.classification = ''Process Specs''
and a.step_key     = :step_key
order by d.proc_spec_no';
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

