set define off
declare
v_folder_id varchar2(40) :='HAMSI_0A78A149DC3B5F87E053D20F0A0A4855';
v_sql_id varchar2(85) :='HAMSI_WID_PAARREQHDRBYIDQRY';
v_sql_id_displ varchar2(85) :='HAMSI_WID_PaarReqHdrByIdQry';
v_read_only number :='';
v_datasource varchar2(32) :='';
v_stype varchar2(32) :='SFWID';
v_site varchar2(10) :='HAMSI';
v_description varchar2(200) :='PAAR Requests by Request Id';
v_sql_text varchar2(4000) :='select a.request_id,
       a.request_status,
       a.request_userid,
       a.request_creation_date,
       a.order_id,
       b.order_no,
       b.plan_title,
       b.order_status,
       b.item_id,
       c.part_no,
       c.part_chg,
       c.part_title,
       c.item_type,
       c.item_subtype,
       a.request_reason,
       a.reply_required_date,
       a.prior_request_id,
       b.asgnd_work_loc,
       d.loc_title,
       a.updt_userid,
       a.time_stamp,
       a.last_action,
       b.security_group
  from sfpl_item_desc_master_all c, sfwid_order_v b, utasgi_wid_paar_desc a, sffnd_work_loc_def d
 where b.order_id = a.order_id
   and c.item_id = b.item_id
   and a.request_id = :request_id
   and b.asgnd_work_loc = d.work_loc';
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

