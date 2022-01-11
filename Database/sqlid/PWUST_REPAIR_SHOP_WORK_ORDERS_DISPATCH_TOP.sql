
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_REPAIR_SHOP_WORK_ORDERS_DISPATCH_TOP.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_REPAIR_SHOP_WORK_ORDERS_DISPATCH_TOP';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_REPAIR_SHOP_WORK_ORDERS_DISPATCH_TOP';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='SMRO_WOE_310;Defect1589';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT DISTINCT
       T.SALES_ORDER,
       T.PART_NO,
       T.WORK_LOC,
       T.CUSTOMER_NO,
       T.CUSTOMER_DESC,
       T.SERIAL_NO,
       T2.Outgoing_Material,
       T2.Required_Delivery_Date
FROM PWUST_REPAIR_WO_DISP_TOP_V T, sfmfg.PWUST_INT_SAP_SM_ORDER T2
WHERE T.ORDER_STATUS IN (''IN QUEUE'',''ACTIVE'')
and exists
(select 1
from SFWID_ORDER_DESC_SEC_GRP t1
WHERE T1.ORDER_NO = T.ORDER_NO
AND T1.SECURITY_GROUP IN (select TT.SECURITY_GROUP from sffnd_user_sec_grp TT where tt.userid = :@USERID))
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

