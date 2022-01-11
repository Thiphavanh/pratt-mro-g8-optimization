
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_DP_GETWORKORDERS.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_DP_GETWORKORDERS';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_DP_GetWorkOrders';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='SMRO_RPT_201; Defect 773; Defect 783,Defect854,defect 1763';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT DISTINCT
       E.PW_ORDER_NO AS ORDER_NO,
       E.SALES_ORDER,
       A.PART_NO,
       B.SERIAL_NO,
       C.LOT_NO,
       D.HOLD_STATUS,
       A.SECURITY_GROUP
  FROM SFWID_ORDER_DESC       A,
       SFWID_SERIAL_DESC      B,
       SFWID_LOT_DESC         C,
       SFWID_HOLDS            D,
       PWUST_SFWID_ORDER_DESC E
 WHERE e.ORDER_ID       = A.ORDER_ID
   AND D.HOLD_STATUS(+) = ''OPEN''
   AND D.ORDER_ID(+)    = C.ORDER_ID
   AND C.ORDER_ID(+)    = B.ORDER_ID
   AND B.ORDER_ID       = A.ORDER_ID
   AND E.PW_ORDER_TYPE  IN (''SUB ORDER'',''SUPPLEMENTAL'',''OVERHAUL'',''REPAIR'')
   AND E.SALES_ORDER    = :SALES_ORDER
 ORDER BY ORDER_NO,SALES_ORDER';
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
