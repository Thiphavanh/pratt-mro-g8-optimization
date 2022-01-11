
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_PL_CREATE_PLAN.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_PL_CREATE_PLAN';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_PL_Create_Plan';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='';
v_sql_text sfcore_sql_lib.sql_text%type  :='EXEC com.ibaset.solumina.sfpl.application.IPlan.insertPlan(:New_Item_No,
    :NEW_ITEM_REV,
    :PLAN_TYPE,
    :ITEM_TYPE,
    :ITEM_SUBTYPE,
    :PROGRAM,
    :PROJECT,
    :ASGND_WORK_LOC,
     1,
     1,
     0,
    :Title,
    :PLND_ORDER_QTY,
    :PLND_CUST_ID,
    :MODEL,
    :ENG_PART_NO,
    :ENG_PART_CHG,
    :ENG_GROUP,
    :TASK_ID,
    :LOW_NAV_LVL,
    :CHANGE_DOC_TYPE,
    :PWP_ID,
    :FROM_ITEM_NO,
    :FROM_ITEM_REV,
    :FROM_PLAN_NO,
    :FROM_ITEM_TYPE,
    :FROM_ITEM_SUBTYPE,
    :from_program,
    :from_ver,
    :from_rev,
    :from_alt,
    :Copy_components_flag,
    ''UDV_CREATE'',
    :UCF_PLAN_VCH1,
    :UCF_PLAN_VCH2,
    :UCF_PLAN_VCH3,
    :UCF_PLAN_VCH4,
    :UCF_PLAN_VCH5,
    :UCF_PLAN_VCH6,
    :UCF_PLAN_VCH7,
    :UCF_PLAN_VCH8,
    :UCF_PLAN_VCH9,
    :UCF_PLAN_VCH10,
    :UCF_PLAN_VCH11,
    :UCF_PLAN_VCH12,
    :UCF_PLAN_VCH13,
    :UCF_PLAN_VCH14,
    :UCF_PLAN_VCH15,
    :UCF_PLAN_NUM1,
    :UCF_PLAN_NUM2,
    :UCF_PLAN_NUM3,
    :UCF_PLAN_NUM4,
    :UCF_PLAN_NUM5,
    :UCF_PLAN_FLAG1,
    :UCF_PLAN_FLAG2,
    :UCF_PLAN_FLAG3,
    :UCF_PLAN_FLAG4,
    :UCF_PLAN_FLAG5,
    :UCF_PLAN_DATE1,
    :UCF_PLAN_DATE2,
    :UCF_PLAN_DATE3,
    :UCF_PLAN_DATE4,
    :UCF_PLAN_DATE5,
    :UCF_PLAN_VCH255_1,
    :UCF_PLAN_VCH255_2,
    :UCF_PLAN_VCH255_3,
    :UCF_PLAN_VCH4000_1,
    :UCF_PLAN_VCH4000_2,
    :FROM_PLAN_TYPE,
    ''Auto Parallel'',
    ''Process Plan'',
    :MFG_BOM_REV,
    :BOM_NO,
    :WORK_FLOW,
    :FINAL_STORES,
    :INSP_PLAN_ID,
    :BOM_ID,
    :CHANGE_REQUEST_ID,
    :PLANNED_ACTION_ID,
    null,
    :PPV_REQ_FLAG,
    :PPV_TYPE,
    :PPV_QTY,
    :ASSOCIATE_CHANGE,
    :ORDER_UOM  )';
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

