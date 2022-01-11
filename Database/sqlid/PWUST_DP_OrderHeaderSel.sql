
set define off
prompt -----------------------------------------;
prompt Executing ... PWUST_DP_ORDERHEADERSEL.sql
prompt -----------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_DP_ORDERHEADERSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_DP_OrderHeaderSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='SMRO_RPT_201';
v_sql_text sfcore_sql_lib.sql_text%type  :='select ucf_order_vch12,
       plan_no,
       order_id,
       order_no,
       plan_id,
       plan_revision,
       plan_updt_no,
       order_status,
       asgnd_work_loc,
       order_cust_id,
       sched_priority,
       initial_stores,
       final_stores,
       contract_no,
       split_flag,
       unit_no,
       customer_order_no,
       plan_type,
       serial_flag,
       lot_flag,
       part_no,
       part_chg,
       item_type,
       item_subtype,
       program,
       mfg_bom_chg,
       model,
       plan_title,
       order_uom,
       unit_type,
       project,
       order_qty,
       order_cust_id,
       lta_send_flag,
       order_scrap_qty,
       order_complete_qty,
       order_stop_qty,
       order_inprocess_qty,
       order_type,
       operation_overlap_flag,
       cs_line_1,
       cs_line_2,
       cs_line_3
  from EXECQUERY com.pw.solumina.sfwid.dao.BuyoffDaoPW.getHeader(:ORDER_NO_LIST, :COVER_SHEET)';
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

