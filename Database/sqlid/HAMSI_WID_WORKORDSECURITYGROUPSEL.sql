set define off
declare
v_folder_id varchar2(40) :='HAMSI_A36AF7567F7D0B56E0400A0AD20F237D';
v_sql_id varchar2(85) :='HAMSI_WID_WORKORDSECURITYGROUPSEL';
v_sql_id_displ varchar2(85) :='HAMSI_WID_WorkOrdSecurityGroupSel';
v_read_only number :='';
v_datasource varchar2(32) :='';
v_stype varchar2(32) :='';
v_site varchar2(10) :='HAMSI';
v_description varchar2(200) :='Cloned from MFI_WID_WorkOrdSecurityGroupSel';
v_sql_text varchar2(4000) :='SELECT
    A.SECURITY_GROUP,
    B.SECURITY_GROUP_TYPE,
    B.ISSUING_AGENCY,
    A.OBSOLETE_RECORD_FLAG,
    A.UPDT_USERID,
    A.TIME_STAMP,
    B.SECURITY_GROUP_DESC,
   B.EXPIRATION_DATE
FROM
    SFWID_ORDER_DESC_SEC_GRP A, SFFND_SECURITY_GROUP_DEF B
WHERE
    A.SECURITY_GROUP = B.SECURITY_GROUP AND
    ORDER_NO = :ORDER_NO AND
    NVL(B.UCF_SEC_GRP_FLAG1,'' '') <> ''R''
ORDER BY
    A.SECURITY_GROUP';
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

