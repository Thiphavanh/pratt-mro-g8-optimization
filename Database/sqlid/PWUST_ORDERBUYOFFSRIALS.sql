
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_ORDERBUYOFFSRIALS.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_ORDERBUYOFFSRIALS';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_OrderBuyoffSrials';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Order Buyoff Serials For Side Panel; Defect 706';
v_sql_text sfcore_sql_lib.sql_text%type  :='select serial_no,
       serial_id,
       Lot_id,
       lot_no,
       S.order_id,
       X.pw_order_no as order_no,
       O.security_group,
       cast(:oper_key as number) oper_key,
       cast(:oper_no as varchar2(10)) oper_no
  from sfwid_serial_Lot_v S, sfwid_order_desc O, pwust_sfwid_order_desc X
 where S.order_id = :order_id
   and S.order_id = O.order_id
   and O.order_id = X.order_id
   and :group_job_no is null

union

select serial_no,
       a.serial_id,
       a.lot_id,
       lot_no,
       a.order_id,
       x.pw_order_no as order_no,
       c.security_group,
       p.oper_key,
       p.oper_no
  from sfwid_order_desc       c,
       sfwid_serial_lot_v     a,
       sfwid_oper_desc        p,
       sfwid_group_job_serial g,
       pwust_sfwid_order_desc x
 where c.order_id = a.order_id
   and c.order_id = p.order_id
   and p.order_id = g.order_id
   and g.order_id = x.order_id
   and p.oper_key = g.oper_key
   and p.step_key = g.step_key
   and a.SERIAL_ID = g.serial_id
   and a.LOT_ID = g.lot_id
   and g.group_job_no = :group_job_no

 order by serial_no ASC';
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

