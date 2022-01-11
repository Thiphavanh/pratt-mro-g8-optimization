
set define off
prompt ---------------------------------------;
prompt Executing ... PWMROI_UNITWORKHISTBUYOFFSEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWMROI_AA51B426106747B8AC0E80FA198A320F';
v_sql_id sfcore_sql_lib.sql_id%type :='PWMROI_UNITWORKHISTBUYOFFSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWMROI_UnitWorkHistBuyoffSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFWID';
v_description sfcore_sql_lib.description%type  :='Unit Work History - Select Buyoff history';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT buyoff_type, buyoff_title, status, buyoff_cert, percent_or_qty_complete, comments, user_name
FROM
(
SELECT b.hist_id,
       a.buyoff_type AS buyoff_type,
       a.buyoff_title AS buyoff_title,
       b.buyoff_status AS status,
       a.buyoff_cert,
       (SFMFG.SFFND_FORMAT_VALUE_TO_CLIENT(CASE WHEN percent_complete IS NOT NULL THEN (CAST(percent_complete AS FLOAT)/100)
            ELSE complete_qty END,''NUMERIC'',SFCORE_GET_USER_CONNECTION_ID()) || (CASE WHEN percent_complete IS NOT NULL THEN ''%'' ELSE '''' END)) AS percent_or_qty_complete,
       SUBSTR(b.comments,1,500) AS comments,
       SUBSTR(SFFND_USER_NAME_GET(b.updt_userid),1,90) AS user_name,
       b.hist_time_stamp AS history_update_time
  FROM sfwid_oper_buyoff a, sfwid_serial_oper_buyoff_hist b
 WHERE a.order_id = :ORDER_ID
   AND a.oper_key = :OPER_KEY
   AND a.step_key = :STEP_KEY 
   AND a.ref_id = :REF_ID
   AND b.serial_id = :R_SERIAL_ID
   AND b.lot_id = :R_LOT_ID
   AND a.order_id = b.order_id
   AND a.oper_key = b.oper_key
   AND a.step_key = b.step_key
   AND a.buyoff_id = b.buyoff_id
UNION
SELECT 0 AS hist_id,
       a.buyoff_type AS buyoff_type,
       a.buyoff_title AS buyoff_title,
       b.buyoff_status AS status,
       a.buyoff_cert, 
       (SFMFG.SFFND_FORMAT_VALUE_TO_CLIENT(CASE WHEN percent_complete IS NOT NULL THEN (CAST(percent_complete AS FLOAT)/100)
            ELSE complete_qty END,''NUMERIC'',SFCORE_GET_USER_CONNECTION_ID()) || (CASE WHEN percent_complete IS NOT NULL THEN ''%'' ELSE '''' END)) AS percent_or_qty_complete,
       SUBSTR(b.comments,1,100) AS comments,
       SUBSTR(SFFND_USER_NAME_GET(b.updt_userid),1,90) AS user_name,
       b.time_stamp AS history_update_time
  FROM sfwid_oper_buyoff a, sfwid_serial_oper_buyoff b
 WHERE a.order_id = :ORDER_ID
   AND a.oper_key = :OPER_KEY
   AND a.step_key = :STEP_KEY
   AND a.ref_id = :REF_ID
   AND b.serial_id = :R_SERIAL_ID
   AND b.lot_id = :R_LOT_ID
   AND a.order_id = b.order_id
   AND a.oper_key = b.oper_key
   AND a.step_key = b.step_key
   AND a.buyoff_id = b.buyoff_id
   AND NVL(percent_complete,0) != 0
)
WHERE NVL(percent_or_qty_complete, ''~'') != ''~''
ORDER BY history_update_time, CASE WHEN hist_id = 0 THEN 2 ELSE 1 END ASC';
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

