
set define off
prompt ---------------------------------------;
prompt Executing ... PWUSD_PLANOPERFLAGSEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUSD_PLANOPERFLAGSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUSD_PlanOperFlagSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Select Plan Operation Flag / defect 1040';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT
    CASE WHEN A.ORIENTATION_FLAG = ''D'' THEN ''Data Collection Centric'' ELSE ''Unit Centric'' END AS ORIENTATION_FLAG,
    A.CROSS_ORDER_FLAG AS CROSS_ORDER_FLAG, '''' AS SECURITY_GROUP, (SELECT WORK_LOC AS PLND_WORK_LOC FROM SFFND_WORK_LOC_DEF  WHERE LOCATION_ID = A.PLND_LOCATION_ID) AS PLND_WORK_LOC 
FROM
    SFPL_OPERATION_DESC A, SFPL_OPERATION_REV B
WHERE
    B.PLAN_ID = :PLAN_ID AND
    B.PLAN_VERSION = :PLAN_VERSION AND
    B.PLAN_REVISION = :PLAN_REVISION AND
    B.PLAN_ALTERATIONS = :PLAN_ALTERATIONS AND
    B.OPER_NO = :OPER_NO AND
    A.PLAN_ID = B.PLAN_ID AND
    A.OPER_KEY =B.OPER_KEY AND
    A.OPER_UPDT_NO = B.OPER_UPDT_NO';
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

