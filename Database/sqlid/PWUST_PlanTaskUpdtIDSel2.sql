
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_PLANTASKUPDTIDSEL2.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_PLANTASKUPDTIDSEL2';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_PlanTaskUpdtIDSel2';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFPL';
v_description sfcore_sql_lib.description%type  :='SMRO_EXPT_201';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT A.TASK_ID, B.TASK_TYPE, B.QUEUE_TYPE, B.QUEUE_TYPE AS QUEUE_DEF,
CASE :@TAG WHEN ''REJECT'' THEN ''REJECT''
ELSE
(SELECT DISTINCT TO_STATUS
      FROM SFMFG.SFFND_WORK_FLOW
      WHERE FROM_TASK_TYPE = B.TASK_TYPE
      AND FROM_QUEUE_TYPE = B.QUEUE_TYPE
      AND FROM_STATUS= B.STATUS
      AND TO_STATUS IN ( ''ACCEPT'', ''COMPLETE'' ))
END AS STATUS,
B.NOTES, B.ASSIGNED_TO_USERID, C.DOC_TYPE, C.SECURITY_GROUP
FROM SFFND_PLG_TASK A, SFFND_TASK B, SFPL_PLAN_DESC C
WHERE A.PLAN_ID =	   :PLAN_ID
  AND A.PLAN_VERSION =	   :PLAN_VERSION
  AND A.PLAN_REVISION =    :PLAN_REVISION
  AND A.PLAN_ALTERATIONS = :PLAN_ALTERATIONS
  AND A.TASK_ID 	 =  B.TASK_ID
  AND B.TASK_TYPE	 = :TASK_TYPE
  AND (B.STATUS = ''ACTIVE'' OR B.STATUS = ''IN QUEUE'')
  AND A.PLAN_ID = C.PLAN_ID
  AND A.PLAN_UPDT_NO = C.PLAN_UPDT_NO';
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

