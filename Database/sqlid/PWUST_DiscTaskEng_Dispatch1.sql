
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_DISCTASKENG_DISPATCH1.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_DISCTASKENG_DISPATCH1';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_DiscTaskEng_Dispatch1';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Defect 940, Defect 1827';
v_sql_text sfcore_sql_lib.sql_text%type  :='select order_no,
       OPER_NO,
       DISC_ID,
       DISC_LINE_NO,
       DISC_LINE_TITLE,
       DISC_LINE_STATUS,
       ITEM_ID,
       AFFECTED_QTY,
       ROUTE_TYPE,
       DISP_TYPE,
       CUST_NOTIF_FLAG,
       DISP_INSTR_TYPE,
       DISP_ORDER_ID,
       DISP_DOC_TYPE,
       DISP_DOC_NO,
       REJECT_WORK_LOC,
       REJECT_WORK_DEPT,
       REJECT_WORK_CENTER,
       NOTES,
       PO_LINE_ITEM,
       REJECT_PART_NO,
       CA_FLAG,
       CA_ID,
       MERGED_DISP_DOC_TYPE,
       MERGED_DISP_DOC_NO,
       QUEUE_TYPE,
       TASK_STATUS,
       TASK_ID,
       UPDT_USERID,
       TIME_STAMP,
       SECURITY_GROUP,
       UNIT_NO              AS END_UNIT_NO,
       UNIT_TYPE            AS END_UNIT_TYPE
  from SFMFG.PWUE_SFQA_DISC_TASK_DISPATCH_V v
 where (QUEUE_TYPE like ''ENG%'' or QUEUE_TYPE = ''ME_APPROVAL'')
   and TASK_STATUS in (''IN QUEUE'', ''ACTIVE'')';
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

