
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_PLG_PARTINFODRAG.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_PLG_PARTINFODRAG';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_PLG_PartInfoDrag';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFPL';
v_description sfcore_sql_lib.description%type  :='Defect 365/897/1272';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT
COMP_PART_NO,COMP_PART_CHG,PART_TITLE,SERIAL_FLAG,LOT_FLAG,EXP_FLAG,SPOOL_FLAG,UID_ITEM_FLAG,
UID_ENTRY_NAME,STOCK_UOM,ITEM_TYPE,ITEM_SUBTYPE,ITEM_SUB_TYPE,STANDARD_PART_FLAG,UTILIZATION_RULE,
TRACKABLE_FLAG,PROGRAM,WORK_LOC,SECURITY_GROUP,BOM_LINE_NO,PART_ACTION,REF_DES,REF_DES_PREF_RANK,
ITEM_QTY,FIND_NO,OPTIONAL_FLAG,OVER_CONSUMPTION_FLAG,ITEM_NOTES,EFF_FROM,EFF_THRU,EFF_FROM_DATE,
EFF_THRU_DATE,DISPLAY_LINE_NO,UNIT_TYPE,REMOVE_ACTION,PART_DAT_COL_ID,RETURN_PART_ACTION,
CALLED_FROM,REPLACEMENT_PART_NO,REPLACEMENT_PART_CHG,REPLACE_WITH_DIFF_PART_FLAG,BOM_COMP_ID,
CROSS_ORDER_FLAG,ORIENTATION_FLAG,UCF_PLAN_ITEM_VCH15
FROM
(
SELECT PART_NO AS COMP_PART_NO, PART_CHG AS COMP_PART_CHG, PART_TITLE, COMP_SERIAL_FLAG AS SERIAL_FLAG,
       COMP_LOT_FLAG AS LOT_FLAG, EXP_FLAG, SPOOL_FLAG,UID_ITEM_FLAG,UID_ENTRY_NAME,STOCK_UOM,
       ITEM_TYPE, ITEM_SUBTYPE,ITEM_SUBTYPE AS ITEM_SUB_TYPE, STANDARD_PART_FLAG, UTILIZATION_RULE,
       TRACKABLE_FLAG, PROGRAM, WORK_LOC, SECURITY_GROUP,
       NULL AS BOM_LINE_NO,''USE'' AS PART_ACTION, NULL AS REF_DES, NULL AS REF_DES_PREF_RANK,
       1 AS ITEM_QTY, NULL AS FIND_NO, ''N'' AS OPTIONAL_FLAG, ''N'' AS OVER_CONSUMPTION_FLAG,
       NULL AS ITEM_NOTES, NULL AS EFF_FROM, NULL AS EFF_THRU, NULL AS EFF_FROM_DATE,
       NULL AS EFF_THRU_DATE,
       NULL AS DISPLAY_LINE_NO,
       NULL AS UNIT_TYPE,
       NULL AS REMOVE_ACTION,
       NULL AS PART_DAT_COL_ID,
       ''USE'' AS RETURN_PART_ACTION,
       ''AUTHORING_PALETTE'' AS CALLED_FROM,
       NULL AS REPLACEMENT_PART_NO,
       NULL AS REPLACEMENT_PART_CHG,
      ''N''AS REPLACE_WITH_DIFF_PART_FLAG,
       NULL AS BOM_COMP_ID,
       (SELECT CROSS_ORDER_FLAG FROM SFPL_OPERATION_DESC D WHERE D.PLAN_ID = :PLAN_ID AND D.OPER_KEY = :OPER_KEY AND D.OPER_UPDT_NO = :OPER_UPDT_NO) AS CROSS_ORDER_FLAG,
       (SELECT CASE WHEN ORIENTATION_FLAG = ''D'' THEN ''Data Collection Centric'' ELSE ''Unit Centric'' END FROM SFPL_OPERATION_DESC D WHERE D.PLAN_ID = :PLAN_ID AND D.OPER_KEY = :OPER_KEY AND D.OPER_UPDT_NO = :OPER_UPDT_NO) AS ORIENTATION_FLAG,
       NULL AS UCF_PLAN_ITEM_VCH15
FROM  SFPL_ITEM_DESC I
WHERE OBSOLETE_RECORD_FLAG = ''N''
AND ((0 = (select count(*) from sfpl_item_desc_sec_grp where part_no = i.part_no))
 or exists
(SELECT 1 FROM SFFND_USER_SEC_GRP X, SFFND_SECURITY_GROUP_DEF Y, SFPL_ITEM_DESC_SEC_GRP Z
  WHERE USERID = :@USERID
    AND X.SECURITY_GROUP = Y.SECURITY_GROUP
    AND Y.OBSOLETE_RECORD_FLAG = ''N''
    AND SYSDATE BETWEEN X.EFFECTIVE_START_DATE AND X.EFFECTIVE_END_DATE
    AND Z.PART_NO = I.PART_NO
    AND Z.SECURITY_GROUP = X.SECURITY_GROUP))
AND ((i.work_loc <> ''ANY'') or ((i.work_loc = ''ANY'') and not exists (select 1 from SFPL_ITEM_DESC i2 where i.item_id = i2.item_id and i2.work_loc <> ''ANY'')))
AND   ITEM_TYPE = ''PART''
AND NVL(ITEM_SUBTYPE, ''X'') != ''STDOPER''
AND ITEM_ID NOT IN
       (SELECT ITEM_ID
          FROM SFPL_PLAN_V P
         WHERE P.PLAN_ID = :PLAN_ID
           AND P.PLAN_VERSION = :PLAN_VERSION
           AND P.PLAN_REVISION = :PLAN_REVISION
           AND P.PLAN_ALTERATIONS = :PLAN_ALTERATIONS)
AND
(
   ((PROGRAM=''ANY'' AND WORK_LOC = ''ANY'') and not exists (select 1 from SFPL_ITEM_DESC a where a.item_id = i.item_id and a.work_loc = :ASGND_WORK_LOC))
   OR
   (PROGRAM=''ANY'' AND WORK_LOC =
      (CASE WHEN :ASGND_WORK_LOC = ''N/A'' THEN ''ANY'' WHEN :ASGND_WORK_LOC IS NULL THEN ''ANY'' ELSE :ASGND_WORK_LOC END))
   OR
   (WORK_LOC = ''ANY'' AND PROGRAM =
      (CASE WHEN :PROGRAM = ''N/A'' THEN ''ANY'' WHEN :PROGRAM IS NULL THEN ''ANY'' ELSE :PROGRAM END) and
      not exists (select 1 from SFPL_ITEM_DESC a where a.item_id = i.item_id and a.work_loc = :ASGND_WORK_LOC))
   OR
   (PROGRAM =
     (CASE WHEN :PROGRAM = ''N/A'' THEN ''ANY'' WHEN :PROGRAM IS NULL THEN ''ANY'' ELSE :PROGRAM END)
    AND WORK_LOC =
    (CASE WHEN :ASGND_WORK_LOC = ''N/A'' THEN ''ANY'' WHEN :ASGND_WORK_LOC IS NULL THEN ''ANY'' ELSE :ASGND_WORK_LOC END))
)
) X
WHERE 1=1 /*+ @filter */';
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

