
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_MFI_WID_MROORDERLOOKUP.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_MFI_WID_MROORDERLOOKUP';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='pwust_MFI_WID_MroOrderLookup';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='defect 632,980,1851';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT T1.PW_ORDER_NO AS FROM_ORDER_NO,
       T.ORDER_ID AS FROM_ORDER_ID,
       T.PART_NO AS FROM_ITEM_NO,
       T.PART_CHG AS FROM_ITEM_REV,
       T.ITEM_TYPE AS FROM_ITEM_TYPE,
       T.ITEM_SUBTYPE AS FROM_ITEM_SUBTYPE,
       T.ORDER_STATUS,
       T1.PW_ORDER_TYPE,
       T.SECURITY_GROUP
FROM SFWID_ORDER_DESC T, PWUST_SFWID_ORDER_DESC T1
WHERE T.ORDER_ID = T1.ORDER_ID
AND T.PART_NO != ''BLANK''
AND T.INSTRUCTIONS_TYPE LIKE ''MRO%''
AND T.ITEM_TYPE = :ITEM_TYPE
AND T1.PW_ORDER_TYPE IN (''OVERHAUL'',''SUB ORDER'',''REPAIR'')
AND T1.PW_ORDER_TYPE = :PLAN_TYPE
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

