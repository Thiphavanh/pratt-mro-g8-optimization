
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_PLG_LATEST_FILEANDILLUSTRATIONSTDOPERDISPLAY.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_PLG_LATEST_FILEANDILLUSTRATIONSTDOPERDISPLAY';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_PLG_Latest_FileAndIllustrationStdOperDisplay';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Display Latest File and Illustration Std Oper DEFECT 1906';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT DISTINCT
  FILE_NAME,
  OBJECT_TAG,
  OBJECT_TYPE,
  OBJECT_REV,
  REV,
  REV_FILTER,
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
  OBJECT_ID,
  STATUS,
  TYPE,
  ''Y'' AS LATEST_REV_FLAG
FROM
  (
     SELECT
          ''Latest'' AS OBJECT_REV,
           C.OBJECT_REV AS REV,
          ''Latest'' AS REV_FILTER,
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
     FROM SFFND_OBJECT_CLASSIFICATION A,SFCORE_MM_OBJECT C,SFCORE_MM_OBJECT_SEC_GRP XX, SFFND_USER_SEC_GRP ZZ,
     SFCORE_OBJECT_TYPE_DEF D,
    (SELECT
        B.OBJECT_TAG,
        MAX(CAST(B.OBJECT_REV AS INTEGER)) AS OBJECT_REV ,
        CASE WHEN E.INTERNAL_FLAG=''N'' THEN ''File'' ELSE (CASE WHEN B.OBJECT_TYPE=''SLIDE'' THEN ''Illustration'' END ) END AS DERIVED_DOC_TYPE
      FROM SFCORE_MM_OBJECT B,SFCORE_OBJECT_TYPE_DEF E
      WHERE B.STATUS = ''COMPLETE''
        AND B.OBSOLETE_RECORD_FLAG = ''N''
        AND B.OBJECT_TYPE = E.OBJECT_TYPE
        AND ( E.INTERNAL_FLAG = ''N'' OR B.OBJECT_TYPE = ''SLIDE'')
      GROUP BY B.OBJECT_TAG, CASE WHEN E.INTERNAL_FLAG=''N'' THEN ''File'' ELSE (CASE WHEN B.OBJECT_TYPE=''SLIDE'' THEN ''Illustration'' END ) END ) X
    WHERE  C.OBJECT_TYPE = D.OBJECT_TYPE     AND ( D.INTERNAL_FLAG = ''N'' OR C.OBJECT_TYPE = ''SLIDE'')
    AND XX.SECURITY_GROUP = C.SECURITY_GROUP
    AND XX.SECURITY_GROUP = ZZ.SECURITY_GROUP
    AND ZZ.USERID = :@USERID
    AND XX.SECURITY_GROUP IN (select SECURITY_GROUP from sfmfg.SFCORE_MM_OBJECT_SEC_GRP Z where z.OBJECT_TAG = :STDOPER_TAG)
    AND ACQUIRE_EXPRESS = ''N''
    AND A.OBJECT_ID=C.OBJECT_ID
    AND C.OBSOLETE_RECORD_FLAG = ''N''
    AND C.OBJECT_TAG = X.OBJECT_TAG
    AND C.OBJECT_TYPE = (SELECT OBJECT_TYPE FROM
                                (SELECT ROWNUM AS RNUM,OBJECT_TYPE
                                        FROM SFCORE_MM_OBJECT W
                                        WHERE W.OBJECT_TAG=X.OBJECT_TAG
                                              AND W.DOC_TYPE=X.DERIVED_DOC_TYPE
                                              AND W.OBJECT_REV= X.OBJECT_REV) R
                         WHERE R.RNUM < 2)
    AND CAST(C.OBJECT_REV AS INTEGER) = X.OBJECT_REV
  )  A
WHERE 1 = 1 /*+ @Filter(OBJECT_REV=REV_FILTER,*) */';
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

