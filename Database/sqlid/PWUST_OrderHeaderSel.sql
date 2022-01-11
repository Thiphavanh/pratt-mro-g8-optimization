
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_ORDERHEADERSEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_ORDERHEADERSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_OrderHeaderSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='SMRO_RPT_201; Defect 773;Defect 650;869;1035;1832,1984';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT PLAN_NO,
       ORDER_ID,
       PART_NO,
       PW_ORDER_NO,
       CUSTOMER,
       CUSTOMER_DESC,
       ENGINE_MODEL,
       SAP_SERIAL_NO,
       SALES_ORDER,
       PLAN_ID,
       PLAN_REVISION,
       PLAN_UPDT_NO,
       ORDER_STATUS,
       ASGND_WORK_LOC,
       UNIT_NO,
       CUSTOMER_ORDER_NO,
       PLAN_TYPE,
       SERIAL_FLAG,
       LOT_FLAG,
       PART_NO,
       PART_CHG,
       ITEM_TYPE,
       ITEM_SUBTYPE,
       PROGRAM,
       COMMODITY_JURISDICTION,
       COMMODITY_CLASSIFICATION,
       PLAN_TITLE,
       ORDER_UOM,
       UNIT_TYPE,
       PROJECT,
       ORDER_QTY,
       LTA_SEND_FLAG,
       ORDER_TYPE,
       ORDER_CONTROL_TYPE
       OPERATION_OVERLAP_FLAG,
       CS_LINE_1,
       CS_LINE_2,
       CS_LINE_3,
       SECURITY_GROUP,
       LOT_CODE
  from EXECQUERY com.pw.solumina.sfwid.dao.WorkOrderDaoPW.getHeader(:ORDER_NO_LIST, :SALES_ORDER, :COVER_SHEET, :TAG)';
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

