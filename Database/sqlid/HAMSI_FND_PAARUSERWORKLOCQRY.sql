set define off
declare
v_folder_id varchar2(40) :='HAMSI_A36AF7567F7D0B56E0400A0AD20F237D';
v_sql_id varchar2(85) :='HAMSI_FND_PAARUSERWORKLOCQRY';
v_sql_id_displ varchar2(85) :='HAMSI_FND_PaarUserWorkLocQry';
v_read_only number :='';
v_datasource varchar2(32) :='';
v_stype varchar2(32) :='SFWID';
v_site varchar2(10) :='HAMSI';
v_description varchar2(200) :='Paar User Work Location Look up';
v_sql_text varchar2(4000) :='SELECT WORK_LOC, LOC_TITLE 
  FROM SFFND_WORK_LOC_DEF C
 WHERE EXISTS (SELECT ''Y''
                 FROM UTASGI_USER_SEC_GRP_XREF B, SFFND_USER_SEC_GRP A
                WHERE B.SECURITY_GROUP = A.SECURITY_GROUP
                  AND A.USERID = :@USERID
                  AND B.HR_LOC = C.WORK_LOC)
   AND NVL(C.OBSOLETE_RECORD_FLAG,''N'') =''N''
   AND C.WORK_LOC != ''ANY''
  ORDER BY C.WORK_LOC';
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

