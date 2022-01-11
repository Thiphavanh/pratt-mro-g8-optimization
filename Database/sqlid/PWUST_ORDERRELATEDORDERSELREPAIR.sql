
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_ORDERRELATEDORDERSELREPAIR.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_ORDERRELATEDORDERSELREPAIR';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_OrderRelatedOrderSelRepair';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='SMRO_WOE_304, defect 1509, defect 1392, 1144';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT T.ORDER_ID,
       T.ORDER_TYPE,
       T.PLAN_ID,
       T.PART_NO,
       T.ITEM_TYPE,
       T.ITEM_SUBTYPE,
       T.PART_CHG,
       T.PROGRAM,
       T.UPDT_USERID,
       T.TIME_STAMP,
       T.LAST_ACTION,
       T.ORDER_STATUS,
       T.ORDER_HOLD_STATUS,
       (SELECT WORK_LOC FROM SFFND_WORK_LOC_DEF  LOC WHERE LOC.LOCATION_ID = T.ASGND_LOCATION_ID) AS ASASGND_WORK_LOC,
       T1.PW_ORDER_NO,
       T.ORDER_CUST_ID,
       T.ORDER_QTY,
       T.SECURITY_GROUP,
       T1.PW_SUB_ORDER_TYPE AS SUB_ORDER_TYPE,
       case when (t.split_from_order_id != t.order_id) then t1.note_text
         else
       (select t2.note_text from sfwid_order_notes t2 where (t2.order_id = t1.order_id) and (t2.oper_key is null) and (T1.pw_order_type not in (''OVERHAUL'',''REPAIR''))) end as NOTE_TEXT
FROM SFWID_ORDER_DESC T, PWUST_SFWID_ORDER_DESC T1
WHERE T.ORDER_ID = T1.ORDER_ID
AND T.ORDER_ID !=:ORDER_ID
AND ((T.UCF_ORDER_VCH15 IS NULL AND NVL('''',NULL) IS NULL) OR
      T.UCF_ORDER_VCH15 = NVL(:REPAIR_MULTI_ORDER_KEY,NULL))
AND T.UCF_ORDER_VCH12 = :UCF_ORDER_VCH12
AND T.PART_NO = :PART_NO
ORDER BY ORDER_NO';
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

