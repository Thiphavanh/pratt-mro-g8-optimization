
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_DISCITEMDISPINFOSEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_DISCITEMDISPINFOSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_DiscItemDispInfoSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFQA';
v_description sfcore_sql_lib.description%type  :='SMRO_eWORK_202 Defect 1125';
v_sql_text sfcore_sql_lib.sql_text%type  :='select a.disc_id, p.pw_order_no AS ORDER_NO, c.oper_no as append_to_oper_no, c.oper_key as oper_oper_key, c.step_key as step_step_key,
   APPEND_TO_STEP_NO as step_no,
    
       A.SECURITY_GROUP, B.order_status, a.disp_order_id, a.disp_instr_type
  from sfqa_disc_item a, sfwid_order_desc b, sfwid_oper_desc c ,sfqa_disc_item_text d, pwust_sfwid_order_desc p
 where a.disc_id = :disc_id
   and a.disc_line_no = :disc_line_no
   and a.order_id = b.order_id
   and a.order_id = c.order_id
   and nvl(a.append_to_oper_key,c.oper_key) = c.oper_key
   and nvl(a.append_to_step_key,-1) = c.step_key and a.disc_id=d.disc_id
   and a.disc_line_no=d.disc_line_no and text_type != ''QA''
   and a.disp_instr_type = ''Append to Operation''
   and p.order_id = a.disp_order_id
union
select a.disc_id, p.pw_order_no AS ORDER_NO, null as append_to_oper_no, null as oper_oper_key, null as step_step_key,
   null as step_no,
       A.SECURITY_GROUP, B.order_status, a.disp_order_id, a.disp_instr_type
  from sfqa_disc_item a, sfwid_order_desc b, pwust_sfwid_order_desc p
 where a.disc_id = :disc_id
   and a.disc_line_no = :disc_line_no
   and a.disp_order_id = b.order_id
   and a.disp_instr_type != ''Append to Operation''
   AND p.order_id = a.disp_order_id
union
select a.disc_id, null as order_no, null as append_to_oper_no, null as oper_oper_key, null as step_step_key,
   null as step_no,
       A.SECURITY_GROUP, null as order_status, null as disp_order_id, A.disp_instr_type
  from sfqa_disc_item a
 where a.disc_id = :disc_id
   and a.disc_line_no = :disc_line_no
   and a.disp_order_id is null or a.disp_order_id = ''''';
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

