set define off
declare
v_folder_id varchar2(40) :='HAMSI_0A78A149DC3B5F87E053D20F0A0A4855';
v_sql_id varchar2(85) :='HAMSI_SUGGESTEDPERMISSIONLATESTREQUESTINFOSEL';
v_sql_id_displ varchar2(85) :='HAMSI_SuggestedPermissionLatestRequestInfoSel';
v_read_only number :='';
v_datasource varchar2(32) :='';
v_stype varchar2(32) :='SFPL';
v_site varchar2(10) :='HAMSI';
v_description varchar2(200) :='Select Latest Request for Plan';
v_sql_text varchar2(4000) :='SELECT REQUEST_ID, 
       REQUEST_CREATION_DATE, 
       REQUEST_REASON,  
       REPLY_REQUIRED_DATE,
       REQUEST_STATUS,
       PART_TITLE,
       PLAN_NO,
       PLAN_VERSION,
       PLAN_REVISION,
       PART_NO,
       PART_CHG,
       PLAN_TITLE,
       ITEM_SUBTYPE,
       SECURITY_GROUP
FROM
(
SELECT REQUEST_ID, 
       REQUEST_CREATION_DATE, 
       REQUEST_REASON,  
       REPLY_REQUIRED_DATE,
       REQUEST_STATUS,
       C.PART_TITLE,
       B.PLAN_NO,
       B.PLAN_VERSION,
       B.PLAN_REVISION,
       B.PART_NO,
       B.PART_CHG,
       B.PLAN_TITLE,
       C.ITEM_SUBTYPE,
       B.SECURITY_GROUP
FROM
       UTASGI_PLG_PAAR_DESC A, SFPL_PLAN_V B, SFPL_ITEM_DESC_MASTER_ALL C
WHERE A.PLAN_ID = :PLAN_ID
AND   A.PLAN_VERSION = :PLAN_VERSION
AND   A.PLAN_REVISION = :PLAN_REVISION
AND   A.PLAN_ALTERATIONS = :PLAN_ALTERATIONS
AND   A.PLAN_ID = B.PLAN_ID
AND   A.PLAN_VERSION = B.PLAN_VERSION
AND   A.PLAN_REVISION = B.PLAN_REVISION
AND   A.PLAN_ALTERATIONS = B.PLAN_ALTERATIONS
AND   B.ITEM_ID = C.ITEM_ID
ORDER BY REQUEST_CREATION_DATE DESC
)
WHERE ROWNUM = 1';
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

