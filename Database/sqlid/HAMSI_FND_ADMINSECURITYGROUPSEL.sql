set define off
declare
v_folder_id varchar2(40) :='HAMSI_0A78A149DC3B5F87E053D20F0A0A4855';
v_sql_id varchar2(85) :='HAMSI_FND_ADMINSECURITYGROUPSEL';
v_sql_id_displ varchar2(85) :='HAMSI_FND_AdminSecurityGroupSel';
v_read_only number :='';
v_datasource varchar2(32) :='';
v_stype varchar2(32) :='';
v_site varchar2(10) :='HAMSI';
v_description varchar2(200) :='Clonned from MFI_FND_AdminSecurityGroupSel';
v_sql_text varchar2(4000) :='SELECT
SECURITY_GROUP,EXPIRATION_DATE,ISSUING_AGENCY,
UPDT_USERID,TIME_STAMP,LAST_ACTION,OBSOLETE_RECORD_FLAG,
SECURITY_GROUP_TYPE,SECURITY_GROUP_DESC,
UCF_SEC_GRP_VCH1,UCF_SEC_GRP_VCH2,UCF_SEC_GRP_VCH3,UCF_SEC_GRP_VCH4,UCF_SEC_GRP_VCH5,
UCF_SEC_GRP_VCH6,UCF_SEC_GRP_VCH7,UCF_SEC_GRP_VCH8,UCF_SEC_GRP_VCH9,UCF_SEC_GRP_VCH10,
UCF_SEC_GRP_VCH11,UCF_SEC_GRP_VCH12,UCF_SEC_GRP_VCH13,UCF_SEC_GRP_VCH14,UCF_SEC_GRP_VCH15,
UCF_SEC_GRP_NUM1,UCF_SEC_GRP_NUM2,UCF_SEC_GRP_NUM3,UCF_SEC_GRP_NUM4,UCF_SEC_GRP_NUM5,
UCF_SEC_GRP_DATE1,UCF_SEC_GRP_DATE2,UCF_SEC_GRP_DATE3,UCF_SEC_GRP_DATE4,UCF_SEC_GRP_DATE5,
UCF_SEC_GRP_FLAG1,UCF_SEC_GRP_FLAG2,UCF_SEC_GRP_FLAG3,UCF_SEC_GRP_FLAG4,UCF_SEC_GRP_FLAG5
FROM SFFND_SECURITY_GROUP_DEF
WHERE NVL(UCF_SEC_GRP_FLAG1,'' '')<>''R''
ORDER BY DECODE(OBSOLETE_RECORD_FLAG, ''Y'', 2, 1),
         SECURITY_GROUP';
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

