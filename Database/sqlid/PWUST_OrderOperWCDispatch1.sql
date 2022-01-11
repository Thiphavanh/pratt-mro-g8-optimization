
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_ORDEROPERWCDISPATCH1.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_ORDEROPERWCDISPATCH1';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_OrderOperWCDispatch1';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFWID';
v_description sfcore_sql_lib.description%type  :='Defect 785';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT /*+ RULE */ CASE WHEN C.INSP_ORDER_ID IS NOT NULL THEN  ''InspectionBlock'' ELSE '''' END AS IMAGE,
       C.INSP_ORDER_ID AS INSP_ORDER_ID,
A.ORDER_ID, pw_order_no as ORDER_NO,A.ORDER_TYPE,A.PART_NO,A.PART_CHG,A.ITEM_TYPE,A.ITEM_SUBTYPE,A.PROGRAM,A.ORDER_STATUS,
A.ORDER_HOLD_STATUS,A.ASGND_WORK_LOC,A.ORDER_QTY,A.ORIG_ORDER_ID,SFMFG.SFWID_ORDER_CONTROL(A.ORDER_ID) AS ORDER_CONTROL_TYPE,
A.OPER_KEY,A.OPER_NO,A.OPER_STATUS,A.OPER_HOLD_STATUS,A.MOVE_PENDING_FLAG,
A.ASGND_WORK_DEPT,A.ASGND_WORK_CENTER,A.SCHED_START_DATE,A.SCHED_END_DATE,
A.REVISED_START_DATE,A.REVISED_END_DATE,A.ACTUAL_START_DATE,A.ACTUAL_END_DATE,
A.OPER_TITLE,A.OPER_OPT_FLAG,''N'' as signoff_all_non_attendance, A.SECURITY_GROUP, A.PERCENT_COMPLETE,C.SPLIT_FROM_ORDER_ID,
C.UNIT_NO AS END_UNIT_NO ,C.UNIT_TYPE AS END_UNIT_TYPE,C.OPERATION_OVERLAP_FLAG
from SFWID_ORDER_OPERS_DISPATCH A
     LEFT OUTER JOIN SFFND_WORK_LOC_DEF LOC ON LOC.WORK_LOC = A.ASGND_WORK_LOC
       LEFT OUTER JOIN SFFND_WORK_DEPT_DEF DEPT ON DEPT.LOCATION_ID = LOC.LOCATION_ID AND DEPT.WORK_DEPT = A.ASGND_WORK_DEPT
       LEFT OUTER JOIN SFFND_WORK_CENTER_DEF CEN ON CEN.LOCATION_ID = DEPT.LOCATION_ID AND CEN.DEPARTMENT_ID = DEPT.DEPARTMENT_ID
                                                 AND CEN.WORK_CENTER = A.ASGND_WORK_CENTER,
     sffnd_user_work_centers b, SFWID_ORDER_DESC C, pwust_sfwid_order_desc d
where oper_status IN (''ACTIVE'', ''IN QUEUE'')
and nvl(b.asgnd_location_id,''x'') = nvl(LOC.location_id,''x'')
and nvl(b.asgnd_department_id,''x'') = nvl(DEPT.department_id,''x'')
and nvl(b.asgnd_center_id,''x'') = nvl(CEN.center_id,''x'')
and b.userid = :@USERID
and A.ORDER_ID  = C.ORDER_ID
and a.order_id = d.order_id
order by order_no, oper_no, a.asgnd_work_center';
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

