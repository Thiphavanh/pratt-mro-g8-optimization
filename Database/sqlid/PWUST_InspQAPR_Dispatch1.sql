
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_INSPQAPR_DISPATCH1.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_INSPQAPR_DISPATCH1';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_InspQAPR_Dispatch1';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Defect 1398';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT CASE WHEN ORD.INSP_ORDER_ID IS NOT NULL THEN  ''InspectionBlock'' ELSE '''' END AS IMAGE,
       ORD.INSP_ORDER_ID AS INSP_ORDER_ID,
  A.ORDER_ID,
  A.OPER_KEY,
  A.STEP_KEY,
  A.OPER_NO,
  A.STEP_NO,
  BUYOFF_TYPE,
  BUYOFF_CERT,
  BUYOFF_TITLE,
  X.PW_ORDER_NO AS ORDER_NO,
  PROGRAM,
  PART_NO,
  ITEM_TYPE,
  ITEM_SUBTYPE,
  ORDER_STATUS,
  ORDER_HOLD_STATUS,
  ORDER_CUST_ID,
  SCHED_PRIORITY,
  OPER_STATUS,
  OPER_HOLD_STATUS,
  ORD.UNIT_NO AS END_UNIT_NO,
  ORD.UNIT_TYPE AS END_UNIT_TYPE,
  CASE WHEN OP.ASGND_LOCATION_ID IS NULL THEN NULL
       ELSE (SELECT WORK_LOC FROM SFFND_WORK_LOC_DEF LOC WHERE LOC.LOCATION_ID = OP.ASGND_LOCATION_ID) END as ASGND_WORK_LOC,
  CASE WHEN ASGND_DEPARTMENT_ID IS NULL THEN NULL
       ELSE (SELECT WORK_DEPT FROM SFFND_WORK_DEPT_DEF DEPT WHERE DEPT.LOCATION_ID = OP.ASGND_LOCATION_ID
                  AND DEPT.DEPARTMENT_ID = OP.ASGND_DEPARTMENT_ID) END as ASGND_WORK_DEPT,
  CASE WHEN ASGND_CENTER_ID IS NULL THEN NULL
       ELSE (SELECT WORK_CENTER FROM SFFND_WORK_CENTER_DEF CEN WHERE CEN.LOCATION_ID = OP.ASGND_LOCATION_ID
                                                               AND CEN.DEPARTMENT_ID = OP.ASGND_DEPARTMENT_ID
                                                               AND CEN.CENTER_ID = OP.ASGND_CENTER_ID) END as ASGND_WORK_CENTER,
  OP.SCHED_START_DATE,
  OP.SCHED_END_DATE,
  OP.REVISED_START_DATE,
  OP.REVISED_END_DATE,
  OP.ACTUAL_START_DATE,
  OP.ACTUAL_END_DATE,
  SECURITY_GROUP
  FROM SFWID_OPER_BUYOFF A, SFWID_ORDER_DESC ORD, SFWID_OPER_DESC OP, pwust_sfwid_order_desc X
WHERE ORD.ORDER_ID = OP.ORDER_ID
AND OP.ORDER_ID = A.ORDER_ID
AND A.ORDER_ID = X.ORDER_ID
AND OP.OPER_KEY = A.OPER_KEY
AND OP.STEP_KEY = -1
AND OP.OPER_STATUS IN (''IN QUEUE'',''ACTIVE'')
AND A.BUYOFF_TYPE IN (''QA'',''INSP'')
AND A.READY_TO_COLLECT_FLAG = ''Y''
/*+ @FILTER() */';
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

