
set define off
prompt ---------------------------------------;
prompt Executing ... PWMROI_ORDERSUB_MANUAL_LKUP.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWMROI_ORDERSUB_MANUAL_LKUP';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWMROI_ORDERSUB_MANUAL_LKUP';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='';
v_sql_text sfcore_sql_lib.sql_text%type  :='select manual_no, manual_rev,manual_provider,manual_type from pwmroi_manual_desc A
where not exists (select manual_no from PWMROI_ORDER_SUBJECT_AUTHORITY B WHERE B.ORDER_ID = :ORDER_ID AND 
                                        B.SUBJECT_NO = :SUBJECT_NO AND B.SUBJECT_REV = :SUBJECT_REV AND A.MANUAL_NO = B.MANUAL_NO AND
                                        A.MANUAL_REV = B.MANUAL_REV AND B.AUTHORITY_TYPE = :AUTHORITY_TYPE AND
                                        B.AUTHORITY_DETAIL = :AUTHORITY_DETAIL)';
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

