
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_OVERHAUL_WORK_ORDERS_DISPATCH.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_OVERHAUL_WORK_ORDERS_DISPATCH';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_OVERHAUL_WORK_ORDERS_DISPATCH';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Defect 326, 704';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT DISTINCT 
       SALES_ORDER,
       ENGINE_TYPE,
       ENGINE_MODEL,
       WORK_LOC,
       CUSTOMER_NO,
       CUSTOMER_DESC,
       SERIAL_NO,
       NEW_SALES_ORDER,
       NEW_ENGINE_TYPE,
       NEW_ENGINE_MODEL,
       NEW_SAP_WORK_LOC,
       NEW_WORK_SCOPE,
       NEW_SUP_NET_ACT,
       NEW_SUB_NET_ACT,
       NEW_CUSTOMER_NO,
       NEW_PLANED_ORDER
FROM PWUST_OVERHAUL_WO_DISPATCH_V t
WHERE exists (select 1
from SFWID_ORDER_DESC_SEC_GRP t3
WHERE T3.ORDER_NO = T.ORDER_NO
AND T3.SECURITY_GROUP IN (select TT.SECURITY_GROUP from sffnd_user_sec_grp TT where tt.userid = :@USERID ))
ORDER  BY SALES_ORDER';
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

