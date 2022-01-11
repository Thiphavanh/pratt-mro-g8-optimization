set define off
declare
v_folder_id varchar2(40) :='HAMSI_0A78A149DC3B5F87E053D20F0A0A4855';
v_sql_id varchar2(85) :='HAMSI_WID_SECURITYGROUPPROFILEIMSECURITYGROUPLOOKUP';
v_sql_id_displ varchar2(85) :='HAMSI_WID_SecurityGroupProfileIMSecurityGroupLookup';
v_read_only number :='';
v_datasource varchar2(32) :='';
v_stype varchar2(32) :='SFWID';
v_site varchar2(10) :='HAMSI';
v_description varchar2(200) :='Security Group Lookup in Security Group Profile Input Matrix';
v_sql_text varchar2(4000) :='SELECT SECURITY_GROUP, SECURITY_GROUP_DESC, EXPIRATION_DATE AS PMN_EFF_THRU_DATE
  FROM SFFND_SECURITY_GROUP_DEF
 WHERE NVL(OBSOLETE_RECORD_FLAG, ''N'') = ''N''
   AND NVL(UCF_SEC_GRP_FLAG1, ''N'') != ''R''
   AND NVL(EXPIRATION_DATE, SYSDATE + 1) > SYSDATE
   AND NOT EXISTS
 (SELECT ''X''
          FROM UTASGI_WID_PAAR_PMN_SGHT
         WHERE PMN_TYPE_ENTITY = SECURITY_GROUP
           AND PMN_TYPE = ''SECURITY GROUP''
           AND PMN_ACTION != ''DELETED''
           AND REQUEST_ID = :REQUEST_ID)
 ORDER BY SECURITY_GROUP';
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

