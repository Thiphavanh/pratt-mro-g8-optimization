set define off
declare
v_folder_id varchar2(40) :='HAMSI_0A78A149DC3B5F87E053D20F0A0A4855';
v_sql_id varchar2(85) :='HAMSI_LOCATIONPROFILEIMLOCATIONLOOKUP';
v_sql_id_displ varchar2(85) :='HAMSI_LocationProfileIMLocationLookup';
v_read_only number :='';
v_datasource varchar2(32) :='';
v_stype varchar2(32) :='SFPL';
v_site varchar2(10) :='HAMSI';
v_description varchar2(200) :='Location Lookup in Location Profile Input Matrix';
v_sql_text varchar2(4000) :='SELECT DISTINCT WORK_LOC, LOC_TITLE
  FROM SFFND_WORK_LOC_DEF A, UTASGI_USER_SEC_GRP_XREF B
 WHERE A.WORK_LOC = B.HR_LOC
   AND NVL(OBSOLETE_RECORD_FLAG, ''N'') = ''N''
   AND NOT EXISTS
 (SELECT ''X''
          FROM UTASGI_PLG_PAAR_PMN_SGHT
         WHERE PMN_TYPE_ENTITY = WORK_LOC
           AND PMN_TYPE = ''LOCATION''
           AND PMN_ACTION != ''DELETED''
           AND REQUEST_ID = :REQUEST_ID )
 ORDER BY WORK_LOC';
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

