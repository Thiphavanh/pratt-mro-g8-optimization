
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_TASKGROUPWORKSCOPESEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_TASKGROUPWORKSCOPESEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_TaskGroupWorkScopeSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFPL';
v_description sfcore_sql_lib.description%type  :='SMRO_PLG_205';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT A.PLAN_ID,
       A.PLAN_UPDT_NO,
       A.SUBJECT_NO,
       A.SUBJECT_REV,
       A.WORKSCOPE,
       B.WORKSCOPE_TITLE,
       A.UPDT_USERID,
       A.TIME_STAMP
  FROM PWUST_PL_SUBJ_WORKSCOPE      A,
       PWUST_WORKSCOPE_DEF          B,
       PWUST_SFOR_SFPL_PLAN_SUBJECT C
 WHERE A.WORKSCOPE = B.WORKSCOPE
   AND B.WORK_LOC = :WORK_LOC
   AND B.ENGINE_TYPE = :PROGRAM
   AND A.PLAN_ID = C.PLAN_ID
   AND A.PLAN_UPDT_NO = C.PLAN_UPDT_NO
   AND A.SUBJECT_NO = C.SUBJECT_NO
   AND A.SUBJECT_REV = C.SUBJECT_REV
   AND A.PLAN_ID = :PLAN_ID
   AND A.PLAN_UPDT_NO = :PLAN_UPDT_NO
   AND A.SUBJECT_NO = :SUBJECT_NO
   AND A.SUBJECT_REV = :SUBJECT_REV';
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