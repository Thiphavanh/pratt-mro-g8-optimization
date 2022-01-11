
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_ORDERRELATEDORDERSEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='SFCORE_SQLLIBS';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_ORDERRELATEDORDERSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_OrderRelatedOrderSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Defect 661,defect 632, defect 788.,1144';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT t.ORDER_ID,t1.PW_ORDER_TYPE AS ORDER_TYPE, t.PLAN_ID,t.PART_NO,t.ITEM_TYPE,t.ITEM_SUBTYPE,t.PART_CHG,t.PROGRAM,t.UPDT_USERID,t.TIME_STAMP,t.LAST_ACTION,t.ORDER_STATUS,t.ORDER_HOLD_STATUS,
       (select WORK_LOC from SFFND_WORK_LOC_DEF  loc where loc.location_id = t.asgnd_location_id) as asASGND_WORK_LOC,
       (SELECT AO.PW_ORDER_NO FROM PWUST_SFWID_ORDER_DESC AO WHERE AO.ORDER_ID = T.PARENT_ORDER_ID) AS PARENT_ORDER_NO,
       (SELECT AO.PW_ORDER_NO FROM PWUST_SFWID_ORDER_DESC AO WHERE AO.ORDER_ID = T.ORIG_ORDER_ID) AS ORIG_ORDER_NO,
       (SELECT AO.PW_ORDER_NO FROM PWUST_SFWID_ORDER_DESC AO WHERE AO.ORDER_ID = T.ORDER_ID )AS ORDER_NO,
       t.ORDER_CUST_ID,
       t.ORDER_QTY,
       t.SECURITY_GROUP,
       ''Y'' AS RECORD_FOUND,
       ''Y'' AS IS_WORK_ORD,
       NULL AS IS_INSP_ORD,
       case when t.order_type = ''Supplemental'' then nvl(t.ucf_order_vch13, '''') else '''' end as oper_no,
       case when (t.split_from_order_id != t.order_id) then t1.note_text
         else
       (select t2.note_text from sfwid_order_notes t2 where (t2.order_id = t1.order_id) and (t2.oper_key is null) and (T1.pw_order_type not in (''OVERHAUL'',''REPAIR''))) end as NOTE_TEXT
from sfwid_order_desc t, pwust_sfwid_order_desc t1
where t.order_id = t1.order_id
and t1.sales_order =:SALES_ORDER
and t.part_no = :PART_NO
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

