
set define off
prompt ---------------------------------------;
prompt Executing ... MFI_WID_RPTORDEROPERHEADERQATEXTSEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='FOUNDATION';
v_sql_id sfcore_sql_lib.sql_id%type :='MFI_WID_RPTORDEROPERHEADERQATEXTSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='MFI_WID_RptOrderOperHeaderQATextSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='';
v_sql_text sfcore_sql_lib.sql_text%type  :='select
     TEXT,
     OPER_KEY,
     block_id
from
EXECQUERY com.ibaset.solumina.sfrpt.application.IReport.selectReportOrderOperationaAndStepText(:order_id, :oper_key, ''-1'', ''HEADER_QA'')';
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


set define off
prompt ---------------------------------------;
prompt Executing ... MFI_WID_OPERATIONPRECEDENCEOPERSELECT.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='FOUNDATION';
v_sql_id sfcore_sql_lib.sql_id%type :='MFI_WID_OPERATIONPRECEDENCEOPERSELECT';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='MFI_WID_OperationPrecedenceOperSelect';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFWID';
v_description sfcore_sql_lib.description%type  :='Selects Operation Precedence Info for Order';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT DISTINCT TARGET_ORDER_ID,
       B.OPER_NO AS TARGET_OPER_NO,
       TARGET_OPER_KEY,
       TARGET_STEP_KEY,
       OPER_ACTION AS ACTION,
       C.PART_NO,
       PRECEDENT_PLAN_ID,
       PRECEDENT_PLAN_VERSION,
       C.OPER_NO AS PRECEDENT_OPER_NO,
       A.PRECEDENT_OPER_KEY,
       A.CONDITION,
       A.ALT_ID,
       A.ALT_COUNT,
       (OPER_ACTION || '' '' || B.OPER_NO || '' : '' || B.TITLE || '' until '' || C.PART_NO || '' : '' ||
        C.OPER_NO || '' '' || CASE A.CONDITION WHEN ''Oper has Started'' THEN ''Operation is Started'' ELSE ''Operation is Completed'' END) AS OPER_CONSTRAINT,
       C.SECURITY_GROUP
FROM SFWID_OPER_PRECEDENCE A, SFWID_OPER_DESC B, SFPL_OPER_V C
WHERE TARGET_ORDER_ID = :ORDER_ID
  AND TARGET_OPER_KEY = :OPER_KEY
  AND A.TARGET_ORDER_ID = B.ORDER_ID
  AND A.TARGET_OPER_KEY = B.OPER_KEY
  AND A.TARGET_STEP_KEY = B.STEP_KEY
  AND A.PRECEDENT_PLAN_ID = C.PLAN_ID (+)
  AND A.PRECEDENT_PLAN_VERSION = C.PLAN_VERSION (+)
  AND A.PRECEDENT_OPER_KEY = C.OPER_KEY (+)
  AND (C.OPER_UPDT_NO IS NULL OR
       C.OPER_UPDT_NO = (SELECT MAX(O.OPER_UPDT_NO)
                            FROM SFPL_OPER_V O
                           WHERE O.PLAN_ID = C.PLAN_ID
                             AND O.PLAN_VERSION = C.PLAN_VERSION
                             AND O.OPER_KEY = C.OPER_KEY))';
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

