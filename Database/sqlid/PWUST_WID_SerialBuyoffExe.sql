
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_WID_SERIALBUYOFFEXE.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_WID_SERIALBUYOFFEXE';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_WID_SerialBuyoffExe';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFWID';
v_description sfcore_sql_lib.description%type  :='SMRO_WOE_202';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT DECODE( BUYOFF_STATUS, ''SKIP'', BUYOFF_STATUS, ''REJECT'', BUYOFF_STATUS,
DECODE( SFWID_BUYOFF_STATUS_OK(:ORDER_ID, :OPER_KEY, :STEP_KEY, :BUYOFF_ID, NULL, :SERIAL_ID, :LOT_ID, ''Y'', :@USERID, NULL, NULL),
                      ''Y'', BUYOFF_STATUS, NULL)) BUYOFF_STATUS,
       COMMENTS,
       SUBSTR(SFFND_USER_NAME_GET(A.UPDT_USERID),1,90) AS "USERNAME",
       A.UPDT_USERID,
       A.TIME_STAMP,
       A.BUYOFF_ID,
       A.ORDER_ID,
       (SFMFG.SFFND_FORMAT_VALUE_TO_CLIENT(CASE WHEN PERCENT_COMPLETE IS NOT NULL THEN (CAST(PERCENT_COMPLETE AS FLOAT)/100)
            ELSE COMPLETE_QTY END,''NUMERIC'',:@USERCONNECTIONID) || (CASE WHEN PERCENT_COMPLETE IS NOT NULL THEN ''%'' ELSE '''' END)) AS "PERCENT_QTY_COMPLETE",
  UCF_SRLOPBUYOFF_DATE1,  UCF_SRLOPBUYOFF_DATE2,  UCF_SRLOPBUYOFF_DATE3,  UCF_SRLOPBUYOFF_DATE4,
  UCF_SRLOPBUYOFF_DATE5,  UCF_SRLOPBUYOFF_FLAG1,  UCF_SRLOPBUYOFF_FLAG2,  UCF_SRLOPBUYOFF_FLAG3,
  UCF_SRLOPBUYOFF_FLAG4,  UCF_SRLOPBUYOFF_FLAG5,  UCF_SRLOPBUYOFF_NUM1 ,  UCF_SRLOPBUYOFF_NUM2 ,
  UCF_SRLOPBUYOFF_NUM3 ,  UCF_SRLOPBUYOFF_NUM4 ,  UCF_SRLOPBUYOFF_NUM5 ,  UCF_SRLOPBUYOFF_VCH1 ,
  UCF_SRLOPBUYOFF_VCH2 ,  UCF_SRLOPBUYOFF_VCH3 ,  UCF_SRLOPBUYOFF_VCH4 ,  UCF_SRLOPBUYOFF_VCH5 ,
  UCF_SRLOPBUYOFF_VCH6 ,  UCF_SRLOPBUYOFF_VCH7 ,  UCF_SRLOPBUYOFF_VCH8 ,  UCF_SRLOPBUYOFF_VCH9 ,
  UCF_SRLOPBUYOFF_VCH10,  UCF_SRLOPBUYOFF_VCH11,  UCF_SRLOPBUYOFF_VCH12,  UCF_SRLOPBUYOFF_VCH13,
  UCF_SRLOPBUYOFF_VCH14,  UCF_SRLOPBUYOFF_VCH15,  UCF_SRLOPBUYOFF_VCH255_1,  UCF_SRLOPBUYOFF_VCH255_2,
  UCF_SRLOPBUYOFF_VCH255_3,  UCF_SRLOPBUYOFF_VCH4000_1,  UCF_SRLOPBUYOFF_VCH4000_2
FROM SFWID_SERIAL_OPER_BUYOFF A
WHERE A.BUYOFF_ID = :BUYOFF_ID
  AND A.ORDER_ID = :ORDER_ID
  AND A.SERIAL_ID = :SERIAL_ID
  AND A.LOT_ID =    :LOT_ID
  AND A.OPER_KEY = :OPER_KEY
  AND A.STEP_KEY = :STEP_KEY
UNION
SELECT DECODE( BUYOFF_STATUS, ''SKIP'', BUYOFF_STATUS, ''REJECT'', BUYOFF_STATUS,
DECODE( SFWID_BUYOFF_STATUS_OK(:ORDER_ID, :OPER_KEY, :STEP_KEY, :BUYOFF_ID, NULL, :SERIAL_ID, :LOT_ID, ''Y'', :@USERID, NULL, NULL),
                      ''Y'', BUYOFF_STATUS, NULL)) BUYOFF_STATUS,
       COMMENTS,
       SUBSTR(SFFND_USER_NAME_GET(A.UPDT_USERID),1,90) AS "USERNAME",
       A.UPDT_USERID,
       A.TIME_STAMP,
       A.BUYOFF_ID,
       A.ORDER_ID,
       (SFMFG.SFFND_FORMAT_VALUE_TO_CLIENT(CASE WHEN PERCENT_COMPLETE IS NOT NULL THEN (CAST(PERCENT_COMPLETE AS FLOAT)/100)
            ELSE COMPLETE_QTY END,''NUMERIC'',:@USERCONNECTIONID) || (CASE WHEN PERCENT_COMPLETE IS NOT NULL THEN ''%'' ELSE '''' END)) AS "PERCENT_QTY_COMPLETE",
  UCF_SRLOPBUYOFF_DATE1,  UCF_SRLOPBUYOFF_DATE2,  UCF_SRLOPBUYOFF_DATE3,  UCF_SRLOPBUYOFF_DATE4,  UCF_SRLOPBUYOFF_DATE5,
  UCF_SRLOPBUYOFF_FLAG1,  UCF_SRLOPBUYOFF_FLAG2,  UCF_SRLOPBUYOFF_FLAG3,  UCF_SRLOPBUYOFF_FLAG4,  UCF_SRLOPBUYOFF_FLAG5,
  UCF_SRLOPBUYOFF_NUM1 ,  UCF_SRLOPBUYOFF_NUM2 ,  UCF_SRLOPBUYOFF_NUM3 ,  UCF_SRLOPBUYOFF_NUM4 ,  UCF_SRLOPBUYOFF_NUM5 ,
  UCF_SRLOPBUYOFF_VCH1 ,  UCF_SRLOPBUYOFF_VCH2 ,  UCF_SRLOPBUYOFF_VCH3 ,  UCF_SRLOPBUYOFF_VCH4 ,  UCF_SRLOPBUYOFF_VCH5 ,
  UCF_SRLOPBUYOFF_VCH6 ,  UCF_SRLOPBUYOFF_VCH7 ,  UCF_SRLOPBUYOFF_VCH8 ,  UCF_SRLOPBUYOFF_VCH9 ,  UCF_SRLOPBUYOFF_VCH10,
  UCF_SRLOPBUYOFF_VCH11,  UCF_SRLOPBUYOFF_VCH12,  UCF_SRLOPBUYOFF_VCH13,  UCF_SRLOPBUYOFF_VCH14,  UCF_SRLOPBUYOFF_VCH15,
  UCF_SRLOPBUYOFF_VCH255_1,  UCF_SRLOPBUYOFF_VCH255_2,  UCF_SRLOPBUYOFF_VCH255_3,  UCF_SRLOPBUYOFF_VCH4000_1,
  UCF_SRLOPBUYOFF_VCH4000_2
FROM SFWID_SERIAL_OPER_BUYOFF_HIST A
WHERE A.BUYOFF_ID = :BUYOFF_ID
  AND A.ORDER_ID = :ORDER_ID
  AND A.SERIAL_ID = :SERIAL_ID
  AND A.LOT_ID =    :LOT_ID
  AND A.OPER_KEY = :OPER_KEY
  AND A.STEP_KEY = :STEP_KEY
ORDER BY TIME_STAMP DESC';
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

