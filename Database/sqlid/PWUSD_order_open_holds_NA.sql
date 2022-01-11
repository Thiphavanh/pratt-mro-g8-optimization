
set define off
prompt ---------------------------------------;
prompt Executing ... PWUSD_ORDER_OPEN_HOLDS_NA.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUSD_ORDER_OPEN_HOLDS_NA';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUSD_order_open_holds_NA';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFWID';
v_description sfcore_sql_lib.description%type  :='defect1714;1708,1775';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT OPEN_HOLDS, SECURITY_GROUP
FROM
(SELECT 1 AS OPEN_HOLDS,H.SECURITY_GROUP
FROM SFWID_SERIAL_HOLDS T, SFWID_HOLDS H, SFWID_SERIAL_DESC S
WHERE T.ORDER_ID = :ORDER_ID
AND T.HOLD_ID = H.HOLD_ID
AND T.ORDER_ID = H.ORDER_ID
AND H.HOLD_STATUS = ''OPEN''
AND S.ORDER_ID = T.ORDER_ID
AND T.SERIAL_ID = S.SERIAL_ID
AND T.LOT_ID = S.LOT_ID
AND S.SERIAL_NO =''N/A''
AND (H.OPER_NO = :OPER_NO or H.STOP_TYPE = ''ORDER STOP'')
UNION
SELECT 1 AS OPEN_HOLDS, SECURITY_GROUP
FROM SFWID_HOLDS t
WHERE T.ORDER_ID = :ORDER_ID
AND STOP_TYPE = ''ORDER STOP''
AND HOLD_STATUS = ''OPEN''
AND (t.OPER_NO = :OPER_NO or t.STOP_TYPE = ''ORDER STOP''))
WHERE ROWNUM = 1';
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

