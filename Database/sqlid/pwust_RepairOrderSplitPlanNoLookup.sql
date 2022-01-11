
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_REPAIRORDERSPLITPLANNOLOOKUP.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='SFCORE_SQLLIBS';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_REPAIRORDERSPLITPLANNOLOOKUP';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='pwust_RepairOrderSplitPlanNoLookup';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='defect1407;Defect 1508';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT T.PLAN_NO,
       T.PLAN_TITLE,
       T.PLAN_ID,
       T.PLAN_VERSION,
       T.PLAN_REVISION,
       T.PLAN_ALTERATIONS,
       T.EFF_TYPE,
       T.EFF_FROM,
       T.EFF_THRU,
       T.EFF_FROM_DATE,
       T.EFF_THRU_DATE
FROM PWUST_REPAIR_PLAN_SEL_V T
WHERE T.SUBJECT_PART_NO = :PART_NO
AND T.NUMBER_TASK_PART >0
AND T.PLND_WORK_LOC = :ASGND_WORK_LOC
ORDER BY T.NUMBER_TASK_PART DESC';
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

