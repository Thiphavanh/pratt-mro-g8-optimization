
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_CHECKCOMPLETEEXPTCOMM.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_CHECKCOMPLETEEXPTCOMM';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_CHECKCOMPLETEEXPTCOMM';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='CHECKCOMPLETEEXPTCOMM';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT DECODE(COUNT(*), 0, ''N'', ''Y'') AS COMM_EXISTS
  FROM SFFND_COMM A, SFFND_PLG_TASK B, SFPL_PLAN_REV C
 WHERE A.TASK_ID = B.TASK_ID
   AND A.COMM_STATUS = ''COMPLETE''
   AND A.UCF_COMM_FLAG3 <> ''C''
   AND A.subject LIKE ''%EXPT%''
   AND B.PLAN_ID = :PLAN_ID
   AND B.PLAN_UPDT_NO = :PLAN_UPDT_NO
   AND C.PLAN_ID = B.PLAN_ID
   AND C.PLAN_UPDT_NO = B.PLAN_UPDT_NO
   AND C.REV_STATUS IN (''EXPT_ACCEPTANCE'',''PLAN COMPLETE'',''PLAN RELEASED'')';
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

