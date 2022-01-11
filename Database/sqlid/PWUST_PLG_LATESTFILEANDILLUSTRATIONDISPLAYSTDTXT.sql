
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_PLG_LATESTFILEANDILLUSTRATIONDISPLAYSTDTXT.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_PLG_LATESTFILEANDILLUSTRATIONDISPLAYSTDTXT';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_PLG_LatestFileAndIllustrationDisplayStdTxt';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Display Latest File and Illustration in Standard Text Defect 1906';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT
  FILE_NAME,
  OBJECT_TAG,
  OBJECT_REV,
  REV,
  OBJECT_TYPE,
  OBJECT_DESC,
  ORIGINAL_FILE_LOCATION,
  FILE_SIZE,
  FILE_TYPE,
  CLASSIFICATION,
  SECURITY_GROUP,
  OBSOLETE_RECORD_FLAG,
  UPDT_USERID,
  TIME_STAMP,
  OLD_OBJECT_ID,
  OBJECT_ID AS MM_OBJECT_ID,
  STATUS,
  TYPE,
  ''Y'' AS LATEST_REV_FLAG
FROM
  (
     SELECT
          ''Latest'' AS OBJECT_REV,
          OBJECT_REV AS REV,
      OBJECT_REFERENCE AS FILE_NAME,
      C.OBJECT_TAG,
      C.OBJECT_TYPE,
      C.OBJECT_DESC,
      OBJECT_PARAMETERS AS ORIGINAL_FILE_LOCATION,
      NVL(LENGTH(BINARY_DATA),0) AS FILE_SIZE,
      FORMAT AS FILE_TYPE,
      C.CLASSIFICATION,
      C.SECURITY_GROUP,
      C.OBSOLETE_RECORD_FLAG,
      A.UPDT_USERID,
      A.TIME_STAMP,
      A.OBJECT_ID as OLD_OBJECT_ID,
      A.OBJECT_ID as OBJECT_ID,
          C.STATUS,
          CASE WHEN D.INTERNAL_FLAG=''N'' THEN ''FILE'' ELSE (CASE WHEN C.OBJECT_TYPE=''SLIDE'' THEN ''ILLUSTRATION'' END ) END AS TYPE
     FROM SFFND_OBJECT_CLASSIFICATION A,
     SFCORE_MM_OBJECT C,
     SFCORE_OBJECT_TYPE_DEF D
     WHERE  C.OBJECT_TYPE = D.OBJECT_TYPE AND (D.INTERNAL_FLAG = ''N'' OR C.OBJECT_TYPE = ''SLIDE'')
     AND C.SECURITY_GROUP = :SECURITY_GROUP
     AND CAST(C.object_rev AS INTEGER) = (select max(CAST(object_rev AS INTEGER))
                         from SFCORE_MM_OBJECT b
                         where C.object_tag = b.object_tag
                          AND C.OBJECT_TYPE = B.OBJECT_TYPE
                          AND b.OBSOLETE_RECORD_FLAG <> ''Y''
                          AND b.Status=''COMPLETE'')
    AND ACQUIRE_EXPRESS = ''N''
    AND A.OBJECT_ID=C.OBJECT_ID
    AND C.OBSOLETE_RECORD_FLAG <> ''Y''
  )  A
WHERE 1 = 1 /*+ @Filter() */';
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

