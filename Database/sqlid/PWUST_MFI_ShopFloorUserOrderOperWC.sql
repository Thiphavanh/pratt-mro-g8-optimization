
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_MFI_SHOPFLOORUSERORDEROPERWC.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_MFI_SHOPFLOORUSERORDEROPERWC';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_MFI_ShopFloorUserOrderOperWC';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Shop floor User Order Oper WC/Defect 923';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT
 ORDER_ID,
 ORDER_NO,
 ORDER_TYPE,
 ORDER_STATUS,
 ORDER_HOLD_STATUS,
 ASGND_WORK_LOC,
 ASSIGNED_TO_USERID,
 ASSIGNMENT_STATUS,
 OPER_KEY,
 OPER_NO,
 OPER_STATUS,
 OPER_HOLD_STATUS,
 ASGND_WORK_DEPT,
 ASGND_WORK_CENTER,
 SCHED_START_DATE,
 SCHED_END_DATE,
 REVISED_START_DATE,
 REVISED_END_DATE,
 ACTUAL_START_DATE,
 ACTUAL_END_DATE,
 SECURITY_GROUP
FROM (
SELECT
 A.ORDER_ID,
 E.PW_ORDER_NO AS ORDER_NO,
 A.ORDER_TYPE,
 A.ORDER_STATUS,
 A.ORDER_HOLD_STATUS,
 A.ASGND_WORK_LOC,
 D.ASSIGNED_TO_USERID,
 CASE
   WHEN D.ASSIGNED_FLAG = ''A'' THEN ''ASSIGNED''
   WHEN D.ASSIGNED_FLAG = ''C'' THEN ''CLAIMED''
   END AS ASSIGNMENT_STATUS,
 A.OPER_KEY,
 A.OPER_NO,
 A.OPER_STATUS,
 A.OPER_HOLD_STATUS,
 A.ASGND_WORK_DEPT,
 A.ASGND_WORK_CENTER,
 A.SCHED_START_DATE,
 A.SCHED_END_DATE,
 A.REVISED_START_DATE,
 A.REVISED_END_DATE,
 A.ACTUAL_START_DATE,
 A.ACTUAL_END_DATE,
 A.SECURITY_GROUP
  FROM SFWID_ORDER_OPERS_DISPATCH A,
       SFWID_OPER_ASSIGNMENT D, PWUST_SFWID_ORDER_DESC E
 WHERE OPER_STATUS IN (''ACTIVE'', ''IN QUEUE'', ''PENDING'')
   AND D.ORDER_ID = A.ORDER_ID
   AND D.ORDER_ID = E.ORDER_ID
   AND D.OPER_KEY = A.OPER_KEY
   AND D.ASSIGNED_TO_USERID = :@USERID
   )
WHERE 1 = 1 /*+ @Filter */';
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

