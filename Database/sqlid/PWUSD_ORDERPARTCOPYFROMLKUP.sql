
set define off
prompt ---------------------------------------;
prompt Executing ... PWUSD_ORDERPARTCOPYFROMLKUP.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='FOUNDATION';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUSD_ORDERPARTCOPYFROMLKUP';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUSD_OrderPartCopyFromLkup';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Order/Part to Copy From defect 1828';
v_sql_text sfcore_sql_lib.sql_text%type  :='select b.pw_order_no as from_order_no,
       a.part_no as from_item_no,
       a.part_chg as from_item_rev,
       a.item_type as from_item_type,
       a.item_subtype as from_item_subtype,
       a.security_group
from sfwid_order_desc a,
pwust_sfwid_order_desc b
where part_no != ''BLANK''
and a.order_id = b.order_id
and not exists(select 1 from sffnd_archive_document d where d.status = ''PURGED'' and d.arch_document_id = a.order_id and d.arch_document_type = ''WORKORDER'')
order by order_no';
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

