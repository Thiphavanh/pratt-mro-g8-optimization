
set define off
prompt ---------------------------------------;
prompt Executing ... PWMROI_BUYOFFDATACOLLECTIONHISTORY.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWMROI_AA51B426106747B8AC0E80FA198A320F';
v_sql_id sfcore_sql_lib.sql_id%type :='PWMROI_BUYOFFDATACOLLECTIONHISTORY';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWMROI_BuyoffDataCollectionHistory';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT OPER_NO, OPER_EXECUTION_COUNT, STEP_NO, BUYOFF_TYPE, BUYOFF_TITLE, STATUS,
       SERIAL_NO, LOT_NO, PERCENT_OR_QTY_COMPLETE, COMMENTS, LAST_ACTION, USER_ID,
       USER_NAME, UPDATE_TIME, HISTORY_ACTION, HISTORY_USER_ID, HISTORY_USER_NAME, HISTORY_UPDATE_TIME
FROM
(
SELECT A.OPER_NO AS OPER_NO,
       B.OPER_EXE_COUNT AS OPER_EXECUTION_COUNT,
       A.STEP_NO AS STEP_NO,
       A.BUYOFF_TYPE AS BUYOFF_TYPE,
       A.BUYOFF_TITLE AS BUYOFF_TITLE,
       B.BUYOFF_STATUS AS STATUS,
       SUBSTR(C.SERIAL_NO,1,20) AS SERIAL_NO,
       SUBSTR(D.LOT_NO,1,20) AS LOT_NO,
      (SFMFG.SFFND_FORMAT_VALUE_TO_CLIENT(CASE WHEN PERCENT_COMPLETE IS NOT NULL THEN (CAST(PERCENT_COMPLETE AS FLOAT)/100)
            ELSE COMPLETE_QTY END,''NUMERIC'',:@USERCONNECTIONID) || (CASE WHEN PERCENT_COMPLETE IS NOT NULL THEN ''%'' ELSE '''' END)) AS PERCENT_OR_QTY_COMPLETE,
       SUBSTR(B.COMMENTS,1,500) AS COMMENTS,
       B.LAST_ACTION AS LAST_ACTION,
       B.UPDT_USERID AS USER_ID,
       SUBSTR(SFFND_USER_NAME_GET(B.UPDT_USERID),1,90) AS USER_NAME,
       B.TIME_STAMP AS UPDATE_TIME,
       B.HIST_ACTION AS HISTORY_ACTION,
       B.HIST_USERID HISTORY_USER_ID,
       SUBSTR(SFFND_USER_NAME_GET(B.HIST_USERID),1,90) AS HISTORY_USER_NAME,
       B.HIST_TIME_STAMP AS HISTORY_UPDATE_TIME
FROM  SFWID_OPER_BUYOFF A, SFWID_SERIAL_OPER_BUYOFF_HIST B,
      SFWID_SERIAL_DESC C, SFWID_LOT_DESC D
WHERE A.ORDER_ID = :ORDER_ID
  AND A.BUYOFF_ID = :BUYOFF_ID
  AND A.ORDER_ID = B.ORDER_ID
  AND A.OPER_KEY = B.OPER_KEY
  AND A.STEP_KEY = B.STEP_KEY
  AND A.BUYOFF_ID = B.BUYOFF_ID
  AND B.ORDER_ID = C.ORDER_ID
  AND B.SERIAL_ID = C.SERIAL_ID
  AND B.ORDER_ID = D.ORDER_ID
  AND B.LOT_ID = D.LOT_ID

UNION
SELECT A.OPER_NO AS OPER_NO,
       B.OPER_EXE_COUNT AS OPER_EXECUTION_COUNT,
       A.STEP_NO AS STEP_NO,
       A.BUYOFF_TYPE AS BUYOFF_TYPE,
       A.BUYOFF_TITLE AS BUYOFF_TITLE,
       B.BUYOFF_STATUS AS STATUS,
       SUBSTR(C.SERIAL_NO,1,20) AS SERIAL_NO,
       SUBSTR(D.LOT_NO,1,20) AS LOT_NO,
      (SFMFG.SFFND_FORMAT_VALUE_TO_CLIENT(CASE WHEN PERCENT_COMPLETE IS NOT NULL THEN (CAST(PERCENT_COMPLETE AS FLOAT)/100)
            ELSE COMPLETE_QTY END,''NUMERIC'',:@USERCONNECTIONID) || (CASE WHEN PERCENT_COMPLETE IS NOT NULL THEN ''%'' ELSE '''' END)) AS PERCENT_OR_QTY_COMPLETE,
       SUBSTR(B.COMMENTS,1,500) AS COMMENTS,
       B.LAST_ACTION AS LAST_ACTION,
       B.UPDT_USERID AS USER_ID,
       SUBSTR(SFFND_USER_NAME_GET(B.UPDT_USERID),1,90) AS USER_NAME,
       B.TIME_STAMP AS UPDATE_TIME,
       B.LAST_ACTION AS HISTORY_ACTION,
       B.UPDT_USERID HISTORY_USER_ID,
       SUBSTR(SFFND_USER_NAME_GET(B.UPDT_USERID),1,90) AS HISTORY_USER_NAME,
       B.TIME_STAMP AS HISTORY_UPDATE_TIME
FROM  SFWID_OPER_BUYOFF A, SFWID_SERIAL_OPER_BUYOFF B,
      SFWID_SERIAL_DESC C, SFWID_LOT_DESC D
WHERE A.ORDER_ID = :ORDER_ID
  AND A.BUYOFF_ID = :BUYOFF_ID
  AND A.ORDER_ID = B.ORDER_ID
  AND A.OPER_KEY = B.OPER_KEY
  AND A.STEP_KEY = B.STEP_KEY
  AND A.BUYOFF_ID = B.BUYOFF_ID
  AND B.ORDER_ID = C.ORDER_ID
  AND B.SERIAL_ID = C.SERIAL_ID
  AND B.ORDER_ID = D.ORDER_ID
  AND B.LOT_ID = D.LOT_ID
  AND (NVL(PERCENT_COMPLETE,0) != 0) OR BUYOFF_STATUS = ''REOPEN''
where nvl(PERCENT_OR_QTY_COMPLETE, ''~'') != ''~''
ORDER BY HISTORY_UPDATE_TIME  DESC';
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

