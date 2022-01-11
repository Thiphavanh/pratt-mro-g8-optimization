set define off
declare
v_folder_id varchar2(40) :='HAMSI_A36AF7567F7D0B56E0400A0AD20F237D';
v_sql_id varchar2(85) :='HAMSI_WID_PAARREQHDRFORAPPROVALBYIDQRY';
v_sql_id_displ varchar2(85) :='HAMSI_WID_PaarReqHdrForApprovalByIdQry';
v_read_only number :='';
v_datasource varchar2(32) :='';
v_stype varchar2(32) :='SFWID';
v_site varchar2(10) :='HAMSI';
v_description varchar2(200) :='PAAR Order Sec Grp Requests Header For Approval by Request Id';
v_sql_text varchar2(4000) :='SELECT A.REQUEST_ID,
       A.REQUEST_STATUS,
       A.REQUEST_USERID,
       A.REQUEST_CREATION_DATE,
       A.REQUEST_REASON,
       A.ORDER_NO,
       A.ORDER_STATUS,
       A.ORDER_TITLE,
       A.BOM_NO,
       A.BOM_REV,
       A.FACILITY_ID,
       A.FACILITY_DESC,
       A.ITEM_NO,
       A.ITEM_REV,
       A.ITEM_TITLE,
       A.ITEM_TYPE,
       A.ITEM_SUBTYPE,
       A.ACTUAL_START_DATE,
       A.ACTUAL_END_DATE,
       A.SCHED_START_DATE,
       A.SCHED_END_DATE,
       A.REPLY_REQUIRED_DATE,
       A.PRIOR_REQUEST_ID,
       A.UPDT_USERID,
       A.TIME_STAMP,
       A.LAST_ACTION
  FROM UTASGE_WID_PAAR_DESC A
 WHERE A.REQUEST_ID = :REQUEST_ID';
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

