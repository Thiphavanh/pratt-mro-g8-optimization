
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_MFI_PLG_ITEM_DISPATCH.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_MFI_PLG_ITEM_DISPATCH';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_MFI_PLG_ITEM_DISPATCH';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFPL';
v_description sfcore_sql_lib.description%type  :='Defect 365;Defect 876;Defect 933;Defect 964/1034;Defect 1159;Defect 1633, 1751';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT
A.PART_NO,
A.PART_NO AS ITEM_NO,
A.PART_NO AS COMPONENT_PART_NO,
A.PART_NO AS ITEM_NO_NEW,
A.COMP_SERIAL_FLAG AS SERIAL_FLAG,
A.COMP_LOT_FLAG AS LOT_FLAG,
A.PART_CHG,
A.PART_CHG AS ITEM_REV,
A.ITEM_TYPE,
A.ITEM_SUBTYPE,
A.PROGRAM,A.WORK_LOC,A.PART_TITLE,A.STOCK_UOM,A.WO_SERIAL_FLAG,
A.WO_LOT_FLAG,A.COMP_SERIAL_FLAG, A.COMP_LOT_FLAG,A.EXP_FLAG,A.SPOOL_FLAG,A.ITEM_ID,
A.UPDT_USERID,A.TIME_STAMP,A.LAST_ACTION,A.UCF_ITEM_VCH1,A.UCF_ITEM_VCH2,
A.UCF_ITEM_NUM1,A.UCF_ITEM_FLAG1,
A.UCF_ITEM_VCH3,A.UCF_ITEM_VCH4,A.UCF_ITEM_VCH5,A.UCF_ITEM_VCH6,A.UCF_ITEM_VCH7,A.UCF_ITEM_VCH8,
A.UCF_ITEM_VCH9,A.UCF_ITEM_VCH10,A.UCF_ITEM_VCH11,A.UCF_ITEM_VCH12,A.UCF_ITEM_VCH13,
A.UCF_ITEM_VCH14,A.UCF_ITEM_VCH15,A.UCF_ITEM_NUM2,A.UCF_ITEM_NUM3,
A.UCF_ITEM_NUM4,A.UCF_ITEM_NUM5,A.UCF_ITEM_DATE1,A.UCF_ITEM_DATE2,A.UCF_ITEM_DATE3,
A.UCF_ITEM_DATE4,A.UCF_ITEM_DATE5,A.UCF_ITEM_FLAG2,A.UCF_ITEM_FLAG3,
A.UCF_ITEM_FLAG4,A.UCF_ITEM_FLAG5,A.UCF_ITEM_VCH255_1,A.UCF_ITEM_VCH255_2,A.UCF_ITEM_VCH255_3,A.UCF_ITEM_VCH4000_1,A.UCF_ITEM_VCH4000_2,
A.PARENT_ENG_PART_NO, A.PARENT_ENG_PART_CHG,
A.OPT_DC1_FLAG, A.OPT_DC2_FLAG, A.OPT_DC3_FLAG, A.OPT_DC4_FLAG,A.OBSOLETE_RECORD_FLAG,A.STANDARD_PART_FLAG,
A.PART_NO AS FROM_PART_NO, A.PART_CHG AS FROM_PART_CHG, NULL AS TO_PART_NO, NULL AS TO_PART_CHG,
''N'' AS INCL_PGM_LOC, ''N'' AS INCL_ALT_PARTS, A.EXTERNAL_PLM_NO,A.EXTERNAL_ERP_NO,
FLIGHT_SAFETY_FLAG, FLIGHT_ESSENTIAL_FLAG,
A.BUY_FLAG,
A.MAKE_FLAG,
A.AVG_PURCHASE_PRICE_PER_UNIT,
A.AVG_MATERIAL_COST_PER_UNIT,
A.AVG_LABOR_COST_PER_UNIT,
A.PRICE_COST_UNIT,
A.AVG_ORDER_LEAD_TIME_DAYS,
CASE WHEN A.SOURCE_INSP_PLAN_ID IS NOT NULL THEN SFSQA_LATEST_INSP_PLAN_NO_GET(A.SOURCE_INSP_PLAN_ID)
     ELSE '''' END AS SOURCE_INSP_PLAN_NO,
A.SOURCE_INSP_PLAN_ID,
CASE WHEN A.DEST_INSP_PLAN_ID IS NOT NULL THEN SFSQA_LATEST_INSP_PLAN_NO_GET(A.DEST_INSP_PLAN_ID)
     ELSE '''' END AS DEST_INSP_PLAN_NO,
A.DEST_INSP_PLAN_ID,
CASE WHEN A.MFG_INSP_PLAN_ID IS NOT NULL THEN SFSQA_LATEST_INSP_PLAN_NO_GET(A.MFG_INSP_PLAN_ID)
     ELSE '''' END AS MFG_INSP_PLAN_NO,
A.MFG_INSP_PLAN_ID,
A.UTILIZATION_RULE, A.TRACKABLE_FLAG,
a.UID_ENTRY_NAME,UID_ITEM_FLAG,UID_ACQUISITION_CODE,
A.SECURITY_GROUP, A.STD_COST,
ITEM_STATUS,NVL(A.PHANTOM_KIT_FLAG,''N'')AS PHANTOM_KIT_FLAG,A.SERIAL_NUM_GEN,
A.COMMODITY_JURISDICTION,
A.COMMODITY_CLASSIFICATION,
A.DATE_CREATED
FROM SFPL_ITEM_DESC_ALL A
WHERE NVL(A.ITEM_SUBTYPE,''X'') != ''STDOPER''
and
((0 = (select count(*) from sfpl_item_desc_sec_grp where part_no = a.part_no))
    or exists
(SELECT 1 FROM SFFND_USER_SEC_GRP A, SFFND_SECURITY_GROUP_DEF B, SFPL_ITEM_DESC_SEC_GRP C
  WHERE USERID = :@USERID
    AND A.SECURITY_GROUP = B.SECURITY_GROUP
    AND B.OBSOLETE_RECORD_FLAG = ''N''
    AND SYSDATE BETWEEN A.EFFECTIVE_START_DATE AND A.EFFECTIVE_END_DATE
    AND C.PART_NO = A.PART_NO
    AND C.SECURITY_GROUP = A.SECURITY_GROUP))
AND A.ITEM_ID != ''N/A''
and (a.part_no, a.work_loc) in (select i.part_no, i.work_loc from SFPL_ITEM_DESC_ALL i where a.part_no = i.part_no and rownum = 1)
AND A.ITEM_TYPE = :@SqlSourceName';
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

