
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_ORDEROPERHIDEPARAMS.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_ORDEROPERHIDEPARAMS';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_OrderOperHideParams';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Order Oper Hide Params; Defect 924';
v_sql_text sfcore_sql_lib.sql_text%type  :='select a.order_Id,a.oper_key,
       decode(sfwid_order_control(a.order_id),''LOT1'',''SERIAL_NO'',
                                              ''NONE'',''SERIAL_NO'',
                                              ''SERIAL1'',''SERIAL_NO'',
                                              ''SERIAL-LOT1'',''SERIAL_NO'',
                                              ''NOTHING'') as HIDE,
       decode(sfwid_order_control(a.order_id),''LOT1'',''ORDER_ID=''||a.order_id||
                                                   '',OPER_KEY=''||a.oper_key||
                                                   '',ORDER_NO=''||sffnd_value_wrapper(b.order_no)||
                                                   '',PW_ORDER_NO=''||sffnd_value_wrapper(c.pw_order_no)||
                                                   '',OPER_NO=''||sffnd_value_wrapper(a.oper_no)||
                                                   '',SERIAL_NO=N/A'',
                                            ''NONE'',''ORDER_ID=''||a.order_id||
                                                   '',OPER_KEY=''||a.oper_key||
                                                   '',ORDER_NO=''||sffnd_value_wrapper(b.order_no)||
                                                   '',PW_ORDER_NO=''||sffnd_value_wrapper(c.pw_order_no)||
                                                   '',OPER_NO=''||sffnd_value_wrapper(a.oper_no)||
                                                   '',SERIAL_NO=N/A'',
                                            ''ORDER_ID=''||a.order_id||
                                            '',OPER_KEY=''||a.oper_key||
                                            '',ORDER_NO=''||sffnd_value_wrapper(b.order_no)||
                                            '',PW_ORDER_NO=''||sffnd_value_wrapper(c.pw_order_no)||
                                            '',OPER_NO=''||sffnd_value_wrapper(a.oper_no)) as PARAMS,
      b.security_group
from sfwid_oper_desc a, sfwid_order_desc b, pwust_sfwid_order_desc c
where a.order_id = :ORDER_ID
  and a.oper_key = :oper_key
  and a.step_key = -1
  and a.order_id = b.order_id
  and b.order_id = c.order_id';
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

