
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_WIDOPERALTINS.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_WIDOPERALTINS';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='pwust_WidOperAltIns';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='02/03/2005 WD changed plan_asgnd_work_loc to plnd_asgnd_work_loc, and added MRO parameters;defect 760';
v_sql_text sfcore_sql_lib.sql_text%type  :='EXEC com.ibaset.solumina.sfwid.application.IOperation.insertAlteration(:ORDER_ID,
      :OPER_NO,
      :OPER_TYPE,
      :OPER_OPT_FLAG,
      :OPER_TITLE,
      :PLND_MACHINE_NO,
      :REWORK_FLAG,
      :OSP_FLAG,
      :SUPPLIER_CODE,
      :OSP_DAYS,
      :OSP_COST_PER_UNIT,
      :auto_start_flag,
      :AUTO_COMPLETE_FLAG,
      :ASGND_WORK_LOC,
      :asgnd_work_dept,
      :asgnd_work_center,
      :plnd_asgnd_work_loc,
      :occur_rate,
      :TEST_TYPE,
      :UCF_ORDER_OPER_VCH1,
      :UCF_ORDER_OPER_VCH2,
      :UCF_ORDER_OPER_NUM1,
      ''A'',
      :UCF_ORDER_OPER_DATE1,
      :UCF_ORDER_OPER_NUM2,
      ''Y'',
      :UCF_ORDER_OPER_VCH3,
      :UCF_ORDER_OPER_VCH4,
      :UCF_ORDER_OPER_VCH5,
      :UCF_ORDER_OPER_VCH6,
      :UCF_ORDER_OPER_VCH7,
      :UCF_ORDER_OPER_VCH8,
      :UCF_ORDER_OPER_VCH9,
      :UCF_ORDER_OPER_VCH10,
      :UCF_ORDER_OPER_VCH11,
      :UCF_ORDER_OPER_VCH12,
      :UCF_ORDER_OPER_VCH13,
      :UCF_ORDER_OPER_VCH14,
      :UCF_ORDER_OPER_VCH15,
      :UCF_ORDER_OPER_NUM3,
      :UCF_ORDER_OPER_NUM4,
      :UCF_ORDER_OPER_NUM5,
      :UCF_ORDER_OPER_DATE2,
      :UCF_ORDER_OPER_DATE3,
      :UCF_ORDER_OPER_DATE4,
      :UCF_ORDER_OPER_DATE5,
      :UCF_ORDER_OPER_FLAG2,
      :UCF_ORDER_OPER_FLAG3,
      :UCF_ORDER_OPER_FLAG4,
      :UCF_ORDER_OPER_FLAG5,
      :UCF_ORDER_OPER_VCH255_1,
      :UCF_ORDER_OPER_VCH255_2,
      :UCF_ORDER_OPER_VCH255_3,
      :UCF_ORDER_OPER_VCH4000_1,
      :UCF_ORDER_OPER_VCH4000_2,
      :SEQ_STEPS_FLAG,
      :OPER_CHANGE_LEVEL,
      :UNIT_PROCESSING,
      :EXE_ORDER,
      :ORIENTATION_FLAG,
      :CROSS_ORDER_FLAG,
      :MUST_ISSUE_PARTS_FLAG,
      ''Add'',
      :UNITS_PER_CYCLE,
      :AUTO_CYCLE_FLAG,
      :PRINT_LABEL,
      :NUMBER_OF_LABELS,
      :REPORT_ID,
      :RECONCILE_SCRAP,
      :UNITS_PER_CYCLE_ACTUAL,
      ''N'')';
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

