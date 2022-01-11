
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_ORDER_OPEN_HOLDS.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_ORDER_OPEN_HOLDS';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_order_open_holds';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='defect1714;1708,1775,1834';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT OPEN_HOLDS, SECURITY_GROUP
FROM
(SELECT 1 AS OPEN_HOLDS,H.SECURITY_GROUP
FROM SFWID_SERIAL_HOLDS T, SFWID_HOLDS H, SFWID_SERIAL_DESC S
WHERE H.ORDER_ID = :ORDER_ID
AND ''OPEN'' NOT IN (SELECT HOLD_STATUS FROM SFWID_HOLDS WHERE HOLD_ID IN (SELECT HOLD_ID from SFWID_SERIAL_HOLDS t where order_id = H.ORDER_ID))
AND H.HOLD_STATUS = ''OPEN''
AND S.ORDER_ID = H.ORDER_ID
AND H.STOP_TYPE != ''ORDER COMPLETION''
AND ((H.OPER_NO = :OPER_NO AND H.STOP_TYPE != ''OPER COMPLETION'') or H.STOP_TYPE = ''ORDER STOP'')
AND S.SERIAL_NO IN  (select regexp_substr(:SERIAL_NO,''[^;]+'', 1, level) from dual
   connect by regexp_substr(:SERIAL_NO, ''[^;]+'', 1, level) is not null)
UNION
SELECT 1 AS OPEN_HOLDS,H.SECURITY_GROUP
FROM SFWID_SERIAL_HOLDS T, SFWID_HOLDS H, SFWID_SERIAL_DESC S
WHERE T.ORDER_ID = :ORDER_ID
AND T.HOLD_ID = H.HOLD_ID
AND T.ORDER_ID = H.ORDER_ID
AND H.HOLD_STATUS = ''OPEN''
AND S.ORDER_ID = T.ORDER_ID
AND T.SERIAL_ID = S.SERIAL_ID
AND T.LOT_ID = S.LOT_ID
AND H.STOP_TYPE != ''ORDER COMPLETION''
AND ((H.OPER_NO = :OPER_NO AND H.STOP_TYPE != ''OPER COMPLETION'') or H.STOP_TYPE = ''ORDER STOP'')
AND S.SERIAL_NO IN  (select regexp_substr(:SERIAL_NO,''[^;]+'', 1, level) from dual
   connect by regexp_substr(:SERIAL_NO, ''[^;]+'', 1, level) is not null)
UNION
SELECT 1 AS OPEN_HOLDS,H.SECURITY_GROUP
FROM SFWID_HOLDS H, SFWID_SERIAL_DESC S
WHERE H.ORDER_ID = S.ORDER_ID
AND H.ORDER_ID = :ORDER_ID
AND H.HOLD_STATUS = ''OPEN''
AND S.SERIAL_NO = ''N/A''
AND H.STOP_TYPE != ''ORDER COMPLETION''
AND ((H.OPER_NO = :OPER_NO AND H.STOP_TYPE != ''OPER COMPLETION'') or H.STOP_TYPE = ''ORDER STOP''))
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

