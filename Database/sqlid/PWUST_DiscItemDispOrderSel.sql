
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_DISCITEMDISPORDERSEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_DISCITEMDISPORDERSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_DiscItemDispOrderSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='SMRO_eWORK_202 Defect 1125';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT p.pw_order_no as disp_order_no, O.SECURITY_GROUP
  FROM sfwid_order_desc o, sfqa_disc_item d, pwust_sfwid_order_desc p
 WHERE ( o.order_type <> ''Manufacturing'' AND o.order_type <> ''MFG'' )
   AND UPPER(o.order_type) <> UPPER(''Salvage'')
   AND o.order_status IN (''PENDING'', ''IN QUEUE'')
   AND o.part_no = d.part_no
   AND o.order_id != NVL(d.order_id, ''~'')
   AND d.disc_id = :disc_id
   AND d.disc_line_no = :disc_line_no
   AND UPPER(:disp_instr_type) like ''%EXISTING%''
   AND p.order_id = o.order_id
   AND p.pw_order_no = o.order_no
 ORDER BY p.pw_order_no';
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

