
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_PLG_FILEDISPLAY.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_PLG_FILEDISPLAY';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_PLG_FileDisplay';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='PLG_FileDisplay Defect 1906';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT OBJECT_REFERENCE AS FILE_NAME, C.OBJECT_TAG, C.OBJECT_TYPE,
CAST(C.OBJECT_REV AS INTEGER) AS OBJECT_REV,COALESCE(C.OBJECT_VER,''N/A'') AS OBJECT_VER, C.OBJECT_DESC, OBJECT_PARAMETERS AS ORIGINAL_FILE_LOCATION,
NVL(LENGTH(BINARY_DATA),0) AS FILE_SIZE, FORMAT AS FILE_TYPE,
C.CLASSIFICATION,
C.Security_Group,
C.OBSOLETE_RECORD_FLAG, C.PWP_ID AS PWP_ID,A.UPDT_USERID, A.TIME_STAMP,
A.OBJECT_ID as OLD_OBJECT_ID, A.OBJECT_ID as OBJECT_ID,COMMODITY_JURISDICTION,COMMODITY_CLASSIFICATION,C.STATUS
FROM SFFND_OBJECT_CLASSIFICATION A,SFCORE_MM_OBJECT C, SFCORE_OBJECT_TYPE_DEF D,  SFCORE_MM_OBJECT_SEC_GRP E
WHERE C.OBJECT_TYPE = D.OBJECT_TYPE
AND D.INTERNAL_FLAG = ''N''
AND ACQUIRE_EXPRESS = ''N''
AND A.OBJECT_ID=C.OBJECT_ID
AND A.OBJECT_ID=E.OBJECT_ID
AND C.EXTERNAL_INCOMPLETE_FLAG = ''N''
AND E.SECURITY_GROUP IN ( SELECT SECURITY_GROUP
                            FROM  SFFND_USER_SEC_GRP
                            WHERE  USERID = :@USERID
                            AND EFFECTIVE_START_DATE < SYSDATE
                            AND EFFECTIVE_END_DATE > SYSDATE )
ORDER BY CASE WHEN OBSOLETE_RECORD_FLAG=''Y'' THEN 2 ELSE 1 END';
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

