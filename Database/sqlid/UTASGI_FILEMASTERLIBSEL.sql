set define off
declare
v_folder_id varchar2(40) :='FOUNDATION';
v_sql_id varchar2(85) :='UTASGI_FILEMASTERLIBSEL';
v_sql_id_displ varchar2(85) :='UTASGI_FileMasterLibSel';
v_read_only number :='';
v_datasource varchar2(32) :='';
v_stype varchar2(32) :='';
v_site varchar2(10) :='HAMSI';
v_description varchar2(200) :='Selects all the files';
v_sql_text varchar2(4000) :='SELECT A.FILE_NAME,
       A.OBJECT_ID as selected_object_id,
       A.OBJECT_TAG,
       A.OBJECT_REV,
       A.OBJECT_DESC,
       A.GENERATING_SYSTEM,       
       A.ORIGINAL_FILE_LOCATION,
       A.FILE_SIZE,
       A.FILE_TYPE,
       CASE WHEN EXTERNALLY_REFERENCED = ''Y'' THEN ''N'' ELSE ''Y'' END AS STORE_IN_DATABASE,
       A.OBSOLETE_RECORD_FLAG,
       A.UPDT_USERID,
       A.TIME_STAMP,
       B.SECURITY_GROUP,
       FCF_OBJCLASS_VCH1,
       FCF_OBJCLASS_VCH2,
       FCF_OBJCLASS_VCH3,
       FCF_OBJCLASS_VCH4,
       FCF_OBJCLASS_VCH5,
       FCF_OBJCLASS_VCH6,
       FCF_OBJCLASS_VCH7,
       FCF_OBJCLASS_VCH8,
       UCF_VCH1,
       UCF_VCH2,
       FCF_OBJCLASS_DATE1,
       FCF_OBJCLASS_DATE2
  FROM UTASGI_SFFND_FILE_MGMT_V A, SFCORE_MM_OBJECT B
 WHERE A.OBJECT_ID = B.OBJECT_ID 
 AND NVL(OBSOLETE_RECORD_FLAG,''N'') = ''N''';
begin
begin
insert into sfcore_sql_lib(sql_id,sql_id_displ,updt_userid,time_stamp,read_only,datasource,stype, description,sql_text)
    values(v_sql_id,v_sql_id_displ,user,sysdate,v_read_only,v_datasource,v_stype, v_description,replace(v_sql_text,chr(10),chr(13)||chr(10)));
commit;
exception when dup_val_on_index then
  update sfcore_sql_lib
  set sql_text=replace(v_sql_text,chr(10),chr(13)||chr(10)),updt_userid=user,time_stamp=sysdate,
read_only=v_read_only,datasource=v_datasource,stype=v_stype,  
description=v_description
  where sql_id=v_sql_id;
  commit;
end;
begin
if v_folder_id is not null then
insert into sfcore_sql_lib_folder(sql_id,folder_id,updt_userid,time_stamp)
values(v_sql_id,v_folder_id,user,sysdate);
commit;
end if;
exception when others then null;
end;
end;
/

set define on

