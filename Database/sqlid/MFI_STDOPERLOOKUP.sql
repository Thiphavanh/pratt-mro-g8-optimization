
set define off
prompt ---------------------------------------;
prompt Executing ... MFI_STDOPERLOOKUP.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='FOUNDATION';
v_sql_id sfcore_sql_lib.sql_id%type :='MFI_STDOPERLOOKUP';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='MFI_StdOperLookup';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFPL';
v_description sfcore_sql_lib.description%type  :='Complete Standard Operation Lookup';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT DISTINCT A.STDOPER_TAG, A.STDOPER_DESC, E.SECURITY_GROUP
  FROM SFFND_STDOPER A,
       SFCORE_MM_OBJECT E,
       (SELECT AA.STDOPER_TAG, MAX(AA.STDOPER_REV) AS STDOPER_REV
          FROM SFFND_STDOPER AA
         GROUP BY AA.STDOPER_TAG) C
 WHERE A.STDOPER_OBJECT_ID = E.OBJECT_ID
   AND A.STDOPER_TAG = C.STDOPER_TAG
   AND A.STDOPER_REV = C.STDOPER_REV
   AND A.STATUS = ''COMPLETE''
   /*+ @filter */
 ORDER BY A.STDOPER_TAG';
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

