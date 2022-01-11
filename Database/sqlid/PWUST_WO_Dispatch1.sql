
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_WO_DISPATCH1.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_WO_DISPATCH1';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_WO_Dispatch1';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Defect 1126.';
v_sql_text sfcore_sql_lib.sql_text%type  :='select
       CASE WHEN B.INSP_ORDER_ID IS NOT NULL THEN  ''InspectionBlock'' ELSE '''' END AS IMAGE,
       B.INSP_ORDER_ID AS INSP_ORDER_ID,
       A.ORDER_ID,
       pw_ORDER_NO as order_no,
       A.ORDER_TYPE,
       A.PART_NO,
       A.ITEM_TYPE,
       A.ITEM_SUBTYPE ,
       A.PART_CHG,
       A.PROGRAM,
       A.ORDER_STATUS,
       A.ORDER_HOLD_STATUS,
       A.ORDER_QTY,
       A.SCHED_PRIORITY,
       A.ORDER_CUST_ID,
       LOC.WORK_LOC AS ASGND_WORK_LOC,
       A.ORIG_ORDER_ID,
       A.PARENT_ORDER_ID,
       A.SUPERCEDED_ORDER_ID,
       A.SUPERCEDES_ORDER,
       A.SPLIT_FLAG,
       A.LTA_SEND_FLAG,
       A.SECURITY_GROUP,
      ''ViewModes.VIEW'' as "@ToolState",
       B.SPLIT_FROM_ORDER_ID,
       B.UNIT_NO AS END_UNIT_NO,
       B.UNIT_TYPE AS END_UNIT_TYPE,
       A.serial_flag,
       B.OPERATION_OVERLAP_FLAG
  from pwust_sfwid_order_desc c, SFWID_ACTIV_NQ_ORDERS A left outer join SFFND_WORK_LOC_DEF LOC on A.ASGND_LOCATION_ID = LOC.LOCATION_ID, SFWID_ORDER_DESC B
  where A.ORDER_ID = B.ORDER_ID
  and c.order_id = a.order_id
  and   1=1 /*+ @filter */
 order by a.ORDER_NO';
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

