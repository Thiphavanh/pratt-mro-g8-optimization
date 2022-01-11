
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_REPAIRSELDELIVEREDSNLOOKUP.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_REPAIRSELDELIVEREDSNLOOKUP';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_RepairSelDeliveredSNLookup';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFFND';
v_description sfcore_sql_lib.description%type  :='Defect1862&1868';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT S.SERIAL_NO,
T1.SECURITY_GROUP    
FROM SFWID_SERIAL_DESC S,
PWMROI_SERIAL_DELIVERY D, 
PW_S_T314_DISPOSITION_LOG L, 
PW_S_T314_DISPOSITION_RPLY R, 
PWMROI_SERIAL_SCRAP SC,          
SFWID_ORDER_DESC T1    
WHERE S.ORDER_ID = D.ORDER_ID(+)     
AND S.SERIAL_ID = D.SERIAL_ID(+)      
AND S.ORDER_ID = SC.ORDER_ID(+)      
AND S.SERIAL_ID = SC.SERIAL_ID(+)      
AND nvl(sc.INTERFACE_TRANSACTION_ID, d.INTERFACE_TRANSACTION_ID) = L.MESSAGE_ID(+) 
AND L.MESSAGE_ID = R.MESSAGE_ID(+)     
AND S.ORDER_ID = :ORDER_ID     
AND S.ORDER_ID = T1.ORDER_ID      
AND S.SERIAL_NO NOT LIKE ''%-SPLIT%''      
AND S.SERIAL_STATUS IN (''COMPLETE'', ''SCRAP'')';
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

