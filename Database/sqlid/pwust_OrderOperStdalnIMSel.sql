
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_ORDEROPERSTDALNIMSEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_ORDEROPERSTDALNIMSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='pwust_OrderOperStdalnIMSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='WID Tool Oper Stand Alone Input Matrix Sql Source;defect 760';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT O.OPER_NO,OPER_TITLE, O.PLND_MACHINE_NO, O.ASGND_WORK_DEPT, O.ASGND_WORK_CENTER, O.ASGND_WORK_LOC,
    O.OPER_TYPE,O.OPER_TYPE_ROLE,O.OPER_OPT_FLAG,O.OCCUR_RATE, O.OSP_FLAG, 
    O.SUPPLIER_CODE,O.OSP_DAYS, O.OSP_COST_PER_UNIT, O.TEST_TYPE, O.AUTO_START_FLAG, O.AUTO_COMPLETE_FLAG, O.REWORK_FLAG,O.UCF_ORDER_OPER_FLAG1, O.UCF_ORDER_OPER_DATE1, O.UCF_ORDER_OPER_VCH1,
    O.UCF_ORDER_OPER_VCH2, 
    O.UCF_ORDER_OPER_NUM1, O.UCF_ORDER_OPER_NUM2,
    O.UCF_ORDER_OPER_VCH3, 
    O.UCF_ORDER_OPER_VCH4,O.UCF_ORDER_OPER_VCH5, O.UCF_ORDER_OPER_VCH6, O.UCF_ORDER_OPER_VCH7, O.UCF_ORDER_OPER_VCH8, O.UCF_ORDER_OPER_VCH9,
    O.UCF_ORDER_OPER_VCH10, O.UCF_ORDER_OPER_VCH11, O.UCF_ORDER_OPER_VCH12,O.UCF_ORDER_OPER_VCH13, O.UCF_ORDER_OPER_VCH14,O.UCF_ORDER_OPER_VCH15,O.UCF_ORDER_OPER_NUM3,O.UCF_ORDER_OPER_NUM4, 
    O.UCF_ORDER_OPER_NUM5, O.UCF_ORDER_OPER_DATE2, O.UCF_ORDER_OPER_DATE3, O.UCF_ORDER_OPER_DATE4, O.UCF_ORDER_OPER_DATE5, O.UCF_ORDER_OPER_FLAG2, O.UCF_ORDER_OPER_FLAG3, O.UCF_ORDER_OPER_FLAG4, 
    O.UCF_ORDER_OPER_FLAG5, O.UCF_ORDER_OPER_VCH255_1,  O.UCF_ORDER_OPER_VCH255_2, O.UCF_ORDER_OPER_VCH255_3, O.UCF_ORDER_OPER_VCH4000_1, O.UCF_ORDER_OPER_VCH4000_2, O.UCF_PLAN_OPER_VCH1, O.UCF_PLAN_OPER_VCH2, 
    O.UCF_PLAN_OPER_VCH3, O.UCF_PLAN_OPER_VCH4, O.UCF_PLAN_OPER_VCH5, O.UCF_PLAN_OPER_NUM1, O.UCF_PLAN_OPER_NUM2, O.UCF_PLAN_OPER_FLAG1, O.UCF_PLAN_OPER_FLAG2, O.UCF_PLAN_OPER_VCH6, O.UCF_PLAN_OPER_VCH7, 
    O.UCF_PLAN_OPER_VCH8, O.UCF_PLAN_OPER_VCH9,O.UCF_PLAN_OPER_VCH10, O.UCF_PLAN_OPER_VCH11, O.UCF_PLAN_OPER_VCH12,O.UCF_PLAN_OPER_VCH13,O.UCF_PLAN_OPER_VCH14, O.UCF_PLAN_OPER_VCH15, 
    O.UCF_PLAN_OPER_NUM3,O.UCF_PLAN_OPER_NUM4,O.UCF_PLAN_OPER_NUM5,O.UCF_PLAN_OPER_DATE1, O.UCF_PLAN_OPER_DATE2,O.UCF_PLAN_OPER_DATE3,O.UCF_PLAN_OPER_DATE4,O.UCF_PLAN_OPER_DATE5,
    O.UCF_PLAN_OPER_FLAG3,O.UCF_PLAN_OPER_FLAG4,O.UCF_PLAN_OPER_FLAG5,O.UCF_PLAN_OPER_VCH255_1,O.UCF_PLAN_OPER_VCH255_2,
    O.UCF_PLAN_OPER_VCH255_3, O.UCF_PLAN_OPER_VCH4000_1, O.UCF_PLAN_OPER_VCH4000_2, O.SEQ_STEPS_FLAG,O.OPER_CHANGE_LEVEL,O.UNIT_PROCESSING,
    O.ORIENTATION_FLAG,O.CROSS_ORDER_FLAG,O.MUST_ISSUE_PARTS_FLAG,O.EXE_ORDER,O.UNITS_PER_CYCLE, O.AUTO_CYCLE_FLAG,O.PRINT_LABEL,O.NUMBER_OF_LABELS,O.RECONCILE_SCRAP,O.UNITS_PER_CYCLE_ACTUAL,
    O.OPER_KEY, O.REPORT_ID,O.REPORT_ID_DISP, O.SECURITY_GROUP
FROM PWUST_WID_INS_OPER_IM_SEL O
WHERE O.ORDER_ID = :ORDER_ID
AND ((:@TAG IS NULL AND O.OPER_KEY = :OPER_KEY) OR :@TAG IS NOT NULL)
AND O.STEP_KEY = -1
ORDER BY LPAD(O.OPER_NO,10,''0'')';
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

