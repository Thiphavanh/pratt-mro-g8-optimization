
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_WORKSCOPELKUP.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_WORKSCOPELKUP';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_WorkScopeLkup';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFPL';
v_description sfcore_sql_lib.description%type  :='SMRO_PLG_205;Defect228';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT W.WORKSCOPE, W.WORKSCOPE_TITLE, T.SECURITY_GROUP
  FROM PWUST_WORKSCOPE_DEF W, SFPL_PLAN_DESC T
 WHERE W.WORK_LOC = :WORK_LOC
   AND W.OBSOLETE_RECORD_FLAG = ''N''
   AND T.PROGRAM = W.ENGINE_TYPE
   AND T.PLAN_ID = :PLAN_ID
   AND T.PLAN_UPDT_NO = :PLAN_UPDT_NO
   AND W.WORKSCOPE <>''TEST''
   AND W.WORKSCOPE NOT IN
       (SELECT WORKSCOPE
          FROM PWUST_PL_SUBJ_WORKSCOPE A
         WHERE A.PLAN_ID = T.PLAN_ID
           AND A.PLAN_UPDT_NO = T.PLAN_UPDT_NO
           AND A.SUBJECT_NO = :SUBJECT_NO
           AND A.SUBJECT_REV = :SUBJECT_REV)
ORDER BY W.WORKSCOPE';
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

