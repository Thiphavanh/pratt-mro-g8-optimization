
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_WO_CENTER_DISPATCH1.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_WO_CENTER_DISPATCH1';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_WO_Center_Dispatch1';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Defect 916';
v_sql_text sfcore_sql_lib.sql_text%type  :='select
IMAGE,HOLD_LED,INSP_ORDER_ID,ORDER_ID,PW_ORDER_NO AS ORDER_NO,PART_NO,ITEM_TYPE,ITEM_SUBTYPE,PART_CHG,PROGRAM,ORDER_STATUS,ORDER_HOLD_STATUS,
ASGND_WORK_LOC,ORDER_QTY,OPER_NO,OPER_STATUS,OPER_HOLD_STATUS,ASGND_WORK_DEPT,
ASGND_WORK_CENTER,SCHED_START_DATE,SCHED_END_DATE,
REVISED_START_DATE,REVISED_END_DATE,
ACTUAL_START_DATE,ACTUAL_END_DATE,OPER_TITLE,DISCREPANCY,ORIG_ORDER_ID,PARENT_ORDER_ID,
SUPERCEDED_ORDER_ID,OPER_KEY,USERID,ORDER_TYPE,OPER_OPT_FLAG,SECURITY_GROUP,SPLIT_FROM_ORDER_ID,
END_UNIT_NO,END_UNIT_TYPE,OPERATION_OVERLAP_FLAG
from(
select CASE WHEN INSP_ORDER_ID IS NOT NULL THEN  ''InspectionBlock'' ELSE '''' END AS IMAGE,
(SELECT DISTINCT ''STATUS_BEING_WORKED''
    from Sfwid_Holds H
    WHERE B.ORDER_ID = H.ORDER_ID
      AND H.HOLD_STATUS IN (''OPEN'',''IN WORK'')
      AND ((H.STOP_TYPE IN (''OPER HOLD'',''OPER COMPLETION'',''OPER STOP'') AND H.OPER_KEY = A.OPER_KEY) OR
           (H.STOP_TYPE IN (''ORDER STOP'',''ORDER COMPLETION'',''HOLD'')))) AS HOLD_LED,
B.INSP_ORDER_ID AS INSP_ORDER_ID,
A.ORDER_ID,C.PW_ORDER_NO,A.PART_NO,A.ITEM_TYPE,A.ITEM_SUBTYPE,A. PART_CHG,A.PROGRAM,A.ORDER_STATUS,A.ORDER_HOLD_STATUS,
A.ASGND_WORK_LOC,A.ORDER_QTY,A.OPER_NO,A.OPER_STATUS,A.OPER_HOLD_STATUS,A.ASGND_WORK_DEPT,
A.ASGND_WORK_CENTER,A.SCHED_START_DATE,A.SCHED_END_DATE,
A.REVISED_START_DATE,A. REVISED_END_DATE,
A.ACTUAL_START_DATE,A.ACTUAL_END_DATE,A.OPER_TITLE,A.DISCREPANCY,A.ORIG_ORDER_ID,A.PARENT_ORDER_ID,
A.SUPERCEDED_ORDER_ID,A.OPER_KEY,A.USERID,A.ORDER_TYPE,A.OPER_OPT_FLAG,A.SECURITY_GROUP,B.SPLIT_FROM_ORDER_ID,
B.UNIT_NO AS END_UNIT_NO, B.UNIT_TYPE AS END_UNIT_TYPE,B.OPERATION_OVERLAP_FLAG
FROM SFWID_WORKCENTER_INFO A, SFWID_ORDER_DESC B, PWUST_SFWID_ORDER_DESC C
where userid=:@USERID
and   A.ORDER_ID = B.ORDER_ID
AND A.OPER_STATUS IN (''IN QUEUE'',''ACTIVE'')) OP
where 1=1 /*+ @filter */';
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

