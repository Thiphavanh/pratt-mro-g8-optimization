
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_PLG_TOOL_DISPATCH.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_PLG_TOOL_DISPATCH';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_PLG_TOOL_DISPATCH';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFPL';
v_description sfcore_sql_lib.description%type  :='Defect 365, defect 964/1034;Defect 1159;SMRO_MD_M309, Defect 1951';
v_sql_text sfcore_sql_lib.sql_text%type  :='select PART_NO,
       ITEM_NO,
       ITEM_NO_NEW,
       PART_CHG,
       ITEM_REV,
       ITEM_TYPE,
       ITEM_SUBTYPE,
       COMMODITY_JURISDICTION,
       COMMODITY_CLASSIFICATION,
       PROGRAM,
       WORK_LOC,
       PART_TITLE,
       STOCK_UOM,
       WO_SERIAL_FLAG,
       WO_LOT_FLAG,
       COMP_SERIAL_FLAG,
       COMP_LOT_FLAG,
       EXP_FLAG,
       SPOOL_FLAG,
       ITEM_ID,
       UPDT_USERID,
       TIME_STAMP,
       LAST_ACTION,
       UCF_ITEM_VCH1,
       UCF_ITEM_VCH2,
       UCF_ITEM_NUM1,
       UCF_ITEM_FLAG1,
       UCF_ITEM_VCH3,
       UCF_ITEM_VCH4,
       UCF_ITEM_VCH5,
       UCF_ITEM_VCH6,
       UCF_ITEM_VCH7,
       UCF_ITEM_VCH8,
       UCF_ITEM_VCH9,
       UCF_ITEM_VCH10,
       UCF_ITEM_VCH11,
       UCF_ITEM_VCH12,
       UCF_ITEM_VCH13,
       UCF_ITEM_VCH14,
       UCF_ITEM_VCH15,
       UCF_ITEM_NUM2,
       UCF_ITEM_NUM3,
       UCF_ITEM_NUM4,
       UCF_ITEM_NUM5,
       UCF_ITEM_DATE1,
       UCF_ITEM_DATE2,
       UCF_ITEM_DATE3,
       UCF_ITEM_DATE4,
       UCF_ITEM_DATE5,
       UCF_ITEM_FLAG2,
       UCF_ITEM_FLAG3,
       UCF_ITEM_FLAG4,
       UCF_ITEM_FLAG5,
       UCF_ITEM_VCH255_1,
       UCF_ITEM_VCH255_2,
       UCF_ITEM_VCH255_3,
       UCF_ITEM_VCH4000_1,
       UCF_ITEM_VCH4000_2,
       PARENT_ENG_PART_NO,
       PARENT_ENG_PART_CHG,
       OPT_DC1_FLAG,
       OPT_DC2_FLAG,
       OPT_DC3_FLAG,
       OPT_DC4_FLAG,
       OBSOLETE_RECORD_FLAG,
       STANDARD_PART_FLAG,
       FROM_PART_NO,
       FROM_PART_CHG,
       TO_PART_NO,
       TO_PART_CHG,
       INCL_PGM_LOC,
       INCL_ALT_PARTS,
       EXTERNAL_PLM_NO,
       EXTERNAL_ERP_NO,
       FLIGHT_SAFETY_FLAG,
       FLIGHT_ESSENTIAL_FLAG,
       TOOL_CALIBRATE_DAYS,
       TOOL_CALIBRATE_HOURS,
       TOOL_CALIBRATE_USES,
       TOOL_CALIBRATION_DAYS_FREQ,
       TOOL_CALIBRATION_HOURS_FREQ,
       TOOL_CALIBRATION_USES_FREQ,
       DATE_CREATED,
       SOURCE_INSP_PLAN_NO,
       SOURCE_INSP_PLAN_REV,
       SOURCE_INSP_PLAN_ID,
       DEST_INSP_PLAN_NO,
       DEST_INSP_PLAN_REV,
       DEST_INSP_PLAN_ID,
       UTILIZATION_RULE,
       TRACKABLE_FLAG,
       UID_ENTRY_NAME,
       UID_ITEM_FLAG,
       UID_ACQUISITION_CODE,
       SECURITY_GROUP,
       CAPTION,
       SERIAL_OR_ID,
       ITEM_STATUS
  from PWUST_PLG_TOOL_DISPATCH_V';
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

