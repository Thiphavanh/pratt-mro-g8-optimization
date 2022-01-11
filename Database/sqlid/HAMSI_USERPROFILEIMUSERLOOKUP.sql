
set define off
prompt ---------------------------------------;
prompt Executing ... HAMSI_USERPROFILEIMUSERLOOKUP.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='FOUNDATION';
v_sql_id sfcore_sql_lib.sql_id%type :='HAMSI_USERPROFILEIMUSERLOOKUP';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='HAMSI_UserProfileIMUserLookup';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFPL';
v_description sfcore_sql_lib.description%type  :='User Lookup in User Profile Input Matrix';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT USERID, FIRST_NAME, LAST_NAME,
       FULL_NAME, WORK_LOC, WORK_DEPT, WORK_CENTER
  FROM SFFND_USER_USER_V
 WHERE USERID NOT IN (''SFMFG'', ''ANONYMOUS'', ''SUPERUSER'')
   AND NVL(OBSOLETE_RECORD_FLAG, ''N'') = ''N''
   AND NOT EXISTS
 (SELECT ''X''
          FROM UTASGI_PLG_PAAR_PMN_SGHT
         WHERE PMN_TYPE_ENTITY = USERID
           AND PMN_TYPE = ''USER''
           AND PMN_ACTION != ''DELETED''
           AND REQUEST_ID = :REQUEST_ID )
 ORDER BY USERID';
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

