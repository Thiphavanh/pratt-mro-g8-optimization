
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_MFI_FND_PLANTYPE.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_MFI_FND_PLANTYPE';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_MFI_FND_PLANTYPE';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Select Plan Type';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT
PLAN_TYPE,
INSTRUCTIONS_TYPE,
WORK_FLOW
FROM
(SELECT DISTINCT
        A.DOC_SUB_TYPE AS PLAN_TYPE,
        INSTRUCTIONS_TYPE,
       (SELECT B.WORK_FLOW FROM SFFND_DOC_TYPE_DEF B
        WHERE B.IS_DEFAULT=''Y''
        AND B.DOC_TYPE=''Process Plan''
        AND B.DOC_SUB_TYPE=A.DOC_SUB_TYPE
        AND ROWNUM=1) AS WORK_FLOW
FROM SFFND_DOC_TYPE_DEF A
WHERE IS_VISIBLE = ''Y''
AND OBSOLETE_RECORD_FLAG = ''N''
AND DOC_TYPE = ''Process Plan''
AND INSTRUCTIONS_TYPE LIKE ''MRO%''
)X
WHERE 1 = 1 /*+ @Filter() */
ORDER BY PLAN_TYPE';
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

