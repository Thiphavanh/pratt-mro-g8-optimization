
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_ORDERHEADERTABSEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_ORDERHEADERTABSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_OrderHeaderTabSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='defect1406/Defect 386/defect 703/defect 726/defect 748/ defect 1392';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT (CASE WHEN T.ALT_NO = T.ALT_ID THEN ''BASELINE'' ELSE T.ALT_NO END) AS ALT_NO,
       ORDER_ID, ORDER_NO, SALES_ORDER, ENGINE_MODEL, WORK_SCOPE, SUPERIORNET, SUBNET,
       CUSTOMER, CUSTOMER_DESC, SAP_SERIAL_NO,
       PLAN_ID,
       PLAN_NO,
       PLAN_VERSION,
       PLAN_REVISION,
       PLAN_ALTERATIONS,
       PLAN_UPDT_NO,
       UPDT_USERID,
       TIME_STAMP,
       LAST_ACTION,
       ORDER_STATUS,
       ORDER_TYPE,
       ORDER_HOLD_STATUS,
       ASGND_WORK_LOC,
       ORDER_CUST_ID,
       CUST_DESC,
       PARENT_ORDER_ID,
       SCHED_PRIORITY,
       INITIAL_STORES,
       FINAL_STORES,
       CONTRACT_NO,
       ORIG_ORDER_ID,
       SUPERCEDED_ORDER_ID,
       SPLIT_FLAG,
       SCHED_START_DATE,
       decode(extract(year from sched_end_date), 2999, '''', sched_end_date) as sched_end_date,
       REVISED_START_DATE,
       REVISED_END_DATE,
       ACTUAL_START_DATE,
       ACTUAL_END_DATE,
       UNIT_NO,
       CUSTOMER_ORDER_NO,
       PLAN_TYPE,
       ALTER_TYPE,
       SUPERCEDES_ORDER,
       STATUS_CHG_NOTES,
       SERIAL_FLAG,
       LOT_FLAG,
       ITEM_ID,
       PART_NO,
       PART_CHG,
       ITEM_TYPE,
       ITEM_SUBTYPE,
       PROGRAM,
       MFG_BOM_CHG,
       MODEL,
       PLAN_TITLE,
       ORDER_UOM,
       UNIT_TYPE,
       PROJECT,
       ALT_ID,
       ALT_COUNT,
       ALT_STATUS,
       ENG_PART_NO,
       ENG_PART_CHG,
       ENG_GROUP,
       MFG_INDEX_NO,
       PLG_GROUP,
       LTA_SEND_FLAG,
       NEEDS_REVIEW_FLAG,
       STATUS_CHG_REASON,
       ORDER_QTY,
       ORDER_SCRAP_QTY,
       ORDER_COMPLETE_QTY,
       ORDER_STOP_QTY,
       ORDER_INPROCESS_QTY,
       EXTERNAL_PLM_NO,
       EXTERNAL_ERP_NO,
       CONDITION,
       UCF_PLAN_VCH1, UCF_PLAN_VCH2, UCF_PLAN_VCH3, UCF_PLAN_VCH4, UCF_PLAN_VCH5, UCF_PLAN_VCH6,
       UCF_PLAN_VCH7, UCF_PLAN_VCH8, UCF_PLAN_VCH9, UCF_PLAN_VCH10, UCF_PLAN_VCH11, UCF_PLAN_VCH12,
       UCF_PLAN_VCH13, UCF_PLAN_VCH14, UCF_PLAN_VCH15, UCF_PLAN_VCH255_1, UCF_PLAN_VCH255_2,
       UCF_PLAN_VCH255_3, UCF_PLAN_VCH4000_1, UCF_PLAN_VCH4000_2, UCF_PLAN_FLAG1, UCF_PLAN_FLAG2,
       UCF_PLAN_FLAG3, UCF_PLAN_FLAG4, UCF_PLAN_FLAG5, UCF_PLAN_NUM1, UCF_PLAN_NUM2, UCF_PLAN_NUM3,
       UCF_PLAN_NUM4, UCF_PLAN_NUM5, UCF_PLAN_DATE1,
       UCF_PLAN_DATE2,
       UCF_PLAN_DATE3,
       UCF_PLAN_DATE4,
       UCF_PLAN_DATE5,
       UCF_ORDER_VCH1,
       UCF_ORDER_VCH2,
       UCF_ORDER_VCH3,
       UCF_ORDER_VCH4,
       UCF_ORDER_VCH5,
       UCF_ORDER_VCH6,
       UCF_ORDER_VCH7,
       UCF_ORDER_VCH8,
       UCF_ORDER_VCH9,
       UCF_ORDER_VCH10,
       UCF_ORDER_VCH11,
       UCF_ORDER_VCH12,
       UCF_ORDER_VCH13,
       UCF_ORDER_VCH14,
       UCF_ORDER_VCH15,
       UCF_ORDER_VCH255_1,
       UCF_ORDER_VCH255_2,
       UCF_ORDER_VCH255_3,
       UCF_ORDER_VCH4000_1,
       UCF_ORDER_VCH4000_2,
       UCF_ORDER_FLAG1,
       UCF_ORDER_FLAG2,
       UCF_ORDER_FLAG3,
       UCF_ORDER_FLAG4,
       UCF_ORDER_FLAG5,
       UCF_ORDER_NUM1,
       UCF_ORDER_NUM2,
       UCF_ORDER_NUM3,
       UCF_ORDER_NUM4,
       UCF_ORDER_NUM5,
       UCF_ORDER_DATE1,
       UCF_ORDER_DATE2,
       UCF_ORDER_DATE3,
       UCF_ORDER_DATE4,
       UCF_ORDER_DATE5,
       BOM_NO,
       MFG_BOM_CHG,
       SECURITY_GROUP,
       DISPLAY_SEQUENCE,
       ACCOUNT_LABOR,
       OPERATION_OVERLAP_FLAG,
       ALIAS_PART_NO,
       ALIAS_PART_CHG,
       LANGUAGE_CODE,
       LANGUAGE_NAME,
       t.ASGND_WORK_LOC AS LOCATION_ID,
       EXPLICIT_BOM_LINK_FLAG,
       STOCK_UOM,
       CONFIG_NAME,
       COMMODITY_JURISDICTION,
       COMMODITY_CLASSIFICATION,
       (select order_no from PWUST_ORDERHEADERTABSEL_V tt where tt.order_id = t.parent_order_id) as parent_order_no,
       OUTGOING_PART_NO,
       T.PW_SUB_ORDER_TYPE
FROM PWUST_ORDERHEADERTABSEL_V T
  WHERE T.ORDER_ID = :order_id';
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

