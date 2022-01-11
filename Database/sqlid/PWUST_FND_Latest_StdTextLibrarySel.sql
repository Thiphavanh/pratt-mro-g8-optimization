
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_FND_LATEST_STDTEXTLIBRARYSEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_FND_LATEST_STDTEXTLIBRARYSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_FND_Latest_StdTextLibrarySel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFFND';
v_description sfcore_sql_lib.description%type  :='Clone of MFI_FND_Latest_StdTextLibrarySel, defect 50/954';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT OBJECT_TAG,
       OBJECT_ID,
       OBJECT_REV,
       REV,
       LATEST_REV_FLAG,
       OBJECT_DESC,
       BLOCKTYPE,
       STATUS,
       SECURITY_GROUP 
FROM ( SELECT DISTINCT A.OBJECT_TAG,
                       A.OBJECT_ID,
                       ''Latest'' AS OBJECT_REV,
                       A.OBJECT_REV AS REV,
                       ''Y'' AS LATEST_REV_FLAG,
                        CASE WHEN A.OBJECT_DESC IS NULL THEN  A.OBJECT_TAG  ELSE  OBJECT_DESC  END AS OBJECT_DESC,
                        A.BLOCKTYPE,
                        A.STATUS,
                       A.SECURITY_GROUP
        FROM SFCORE_MM_OBJECT A, (SELECT C.OBJECT_TAG, 
                                         C.OBJECT_TYPE,
                                         MAX(sfmfg.sfdb_lpad_varchar(C.OBJECT_REV,22,0)) AS OBJECT_REV
                                  FROM SFCORE_MM_OBJECT C
                                  WHERE C.OBJECT_TYPE = ''STDTEXT''
                                  AND C.STATUS = ''COMPLETE''
                                  AND (UPPER(C.BLOCKTYPE) = UPPER(:@BLOCKID) OR UPPER(C.BLOCKTYPE) = UPPER(''@ANYBLOCK''))
                                  GROUP BY C.OBJECT_TAG, C.OBJECT_TYPE) X,
                                  SFFND_USER_WORK_CENTERS C,
                                  SFFND_WORK_LOC_DEF D, 
                                  UTASGI_USER_SEC_GRP_XREF E
         WHERE A.OBJECT_TAG = X.OBJECT_TAG
         AND A.OBJECT_TYPE = X.OBJECT_TYPE
AND c.userid = :@USERID
AND c.asgnd_location_id = d.location_id
AND d.work_loc = e.hr_loc
AND e.security_group = A.security_group
         AND CAST(A.OBJECT_REV AS INTEGER) = X.OBJECT_REV) B
WHERE 1=1 /*+ @filter */
ORDER BY B.OBJECT_TAG';
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

