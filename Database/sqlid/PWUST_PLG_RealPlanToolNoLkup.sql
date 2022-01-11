
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_PLG_REALPLANTOOLNOLKUP.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_PLG_REALPLANTOOLNOLKUP';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_PLG_RealPlanToolNoLkup';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFWID';
v_description sfcore_sql_lib.description%type  :='Defect 933, defect 964/1034, defect 1136.';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT DISTINCT T.TOOL_NO, T.TOOL_CHG AS TOOL_REV, T.TOOL_TITLE, T.ITEM_TYPE, T.ITEM_SUBTYPE,
       I.COMP_SERIAL_FLAG AS SERIAL_FLAG, I.EXP_FLAG AS EXP_FLAG, '''' as SECURITY_GROUP
  FROM SFFND_TOOL T,
       SFPL_ITEM_DESC_ALL I
 WHERE T.TOOL_NO = I.PART_NO
   AND T.TOOL_CHG = I.PART_CHG
and
    ((0 = (select count(*) from sfpl_item_desc_sec_grp where part_no = i.part_no))
    or exists
        (select 1 from sffnd_user_work_centers b, sffnd_work_loc_def c, utasgi_user_sec_grp_xref d, sfpl_item_desc_sec_grp e
        where b.userid = :@USERID
        and c.location_id = b.asgnd_location_id
        and d.hr_loc = c.work_loc
        and e.part_no = i.part_no
        and e.security_group = d.security_group))
   AND NVL(T.OBSOLETE_RECORD_FLAG,''N'') = ''N''
   AND ITEM_ID <> (SELECT ITEM_ID
                     FROM SFPL_PLAN_V P
                    WHERE P.PLAN_ID = :PLAN_ID
                      AND P.PLAN_VERSION = :PLAN_VERSION
                      AND P.PLAN_REVISION = :PLAN_REVISION
                      AND P.PLAN_ALTERATIONS = :PLAN_ALTERATIONS)
   AND ((I.PROGRAM = ''ANY''
        AND I.WORK_LOC = (CASE WHEN :PLND_WORK_LOC = ''N/A'' THEN ''ANY'' WHEN :PLND_WORK_LOC IS NULL THEN ''ANY'' ELSE :PLND_WORK_LOC END))
   OR  ((I.PROGRAM = (CASE WHEN :PROGRAM = ''N/A'' THEN ''ANY'' WHEN :PROGRAM IS NULL THEN ''ANY'' ELSE :PROGRAM END)
        AND I.WORK_LOC = ''ANY'') and not exists (select 1 from SFPL_ITEM_DESC a where a.item_id = i.item_id and a.work_loc = :PLND_WORK_LOC))
   OR  (I.PROGRAM = (CASE WHEN ''N/A'' = ''N/A'' THEN ''ANY'' WHEN ''N/A'' IS NULL THEN ''ANY'' ELSE ''N/A'' END)
        AND I.WORK_LOC = (CASE WHEN :PLND_WORK_LOC = ''N/A'' THEN ''ANY'' WHEN :PLND_WORK_LOC IS NULL THEN ''ANY'' ELSE :PLND_WORK_LOC END))
   OR  ((I.PROGRAM = ''ANY'' AND I.work_loc = ''ANY'')
         and not exists (select 1 from SFPL_ITEM_DESC a where a.item_id = i.item_id and a.work_loc = :PLND_WORK_LOC)))
  AND T.ITEM_TYPE = ''TOOL''
  AND T.TOOL_NO != ''N/A''
  AND 1=1  /*+ @Filter */';
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


set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_WID_SELECTPARTNO.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_WID_SELECTPARTNO';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_WID_SelectPartNo';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFWID';
v_description sfcore_sql_lib.description%type  :='Defect 365/897/933, defect 964/1034.';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT a.PART_NO AS COMP_PART_NO,
       PART_CHG AS COMP_PART_CHG,
       PART_TITLE,
       COMP_SERIAL_FLAG AS SERIAL_FLAG,
       COMP_LOT_FLAG AS LOT_FLAG,
       EXP_FLAG,
       SPOOL_FLAG,
       UID_ITEM_FLAG,
       a.UID_ENTRY_NAME,
       STOCK_UOM AS UOM,
       ITEM_TYPE,
       ITEM_SUBTYPE,
       STANDARD_PART_FLAG,
       UTILIZATION_RULE,
       TRACKABLE_FLAG,
       PROGRAM,
       a.WORK_LOC,
       a.SECURITY_GROUP,
       OPT_DC1_FLAG,
       OPT_DC2_FLAG,
       OPT_DC3_FLAG,
       OPT_DC4_FLAG,
       ''USE'' AS PART_ACTION,
      NULL AS BOM_LINE_NO,
       NULL AS REF_DES,
       NULL AS REF_DES_PREF_RANK,
       1 AS PLND_ITEM_QTY,
       NULL AS FIND_NO,
       NULL AS OPTIONAL_FLAG,
       NULL AS OVER_CONSUMPTION_FLAG,
       NULL AS ITEM_NOTES,
       NULL AS EFF_FROM,
       NULL AS EFF_THRU,
       NULL AS EFF_FROM_DATE,
       NULL AS EFF_THRU_DATE,
       NULL AS DISPLAY_LINE_NO,
       NULL AS REMOVE_ACTION,
       NULL AS ucf_plan_item_vch2,
       NULL AS ucf_plan_item_vch3,
       NULL AS REPLACEMENT_PART_NO,
       NULL AS REPLACEMENT_PART_CHG,
       NULL AS UCF_PLAN_ITEM_VCH15,
       ''N'' AS REPLACE_WITH_DIFF_PART_FLAG,
       (SELECT decode(orientation_flag,''U'',''Unit Centric'',''D'',''Data Collection Centric'')  FROM SFWID_OPER_DESC D WHERE D.ORDER_ID = :ORDER_ID
        AND D.OPER_KEY= :OPER_KEY AND D.STEP_KEY=-1) AS ORIENTATION_FLAG,
       (SELECT CROSS_ORDER_FLAG FROM SFWID_OPER_DESC D WHERE D.ORDER_ID=:ORDER_ID AND D.OPER_KEY=:OPER_KEY AND D.STEP_KEY=-1) AS CROSS_ORDER_FLAG,
       ''USE'' AS RETURN_PART_ACTION,
       ''AUTHORING_PALETTE'' AS CALLED_FROM,
       ''Item List'' AS PART_FROM,
       NULL AS PART_DAT_COL_ID
FROM  SFPL_ITEM_DESC A
WHERE a.OBSOLETE_RECORD_FLAG = ''N''
and
    ((0 = (select count(*) from sfpl_item_desc_sec_grp where part_no = a.part_no))
    or exists
     (select 1 from utasgi_user_sec_grp_xref b, sfpl_item_desc_sec_grp d
      where b.hr_loc = :ASGND_WORK_LOC
      and d.part_no = a.part_no
      and d.security_group = b.security_group))
AND   ITEM_TYPE = ''PART''
AND NVL(ITEM_SUBTYPE, ''X'') != ''STDOPER''
AND ITEM_ID<>(SELECT O.ITEM_ID FROM SFWID_ORDER_DESC O WHERE O.ORDER_ID = :ORDER_ID)
AND
(
   ((PROGRAM=''ANY'' AND a.WORK_LOC = ''ANY'') and not exists (select 1 from SFPL_ITEM_DESC i where a.item_id = i.item_id and i.work_loc = :ASGND_WORK_LOC))

   OR
   (PROGRAM=''ANY'' AND a.WORK_LOC =
      (CASE WHEN :ASGND_WORK_LOC = ''N/A'' THEN ''ANY'' WHEN :ASGND_WORK_LOC IS NULL THEN ''ANY'' ELSE :ASGND_WORK_LOC END))
   OR
   ((a.WORK_LOC = ''ANY'' AND PROGRAM =
      (CASE WHEN :PROGRAM = ''N/A'' THEN ''ANY'' WHEN :PROGRAM IS NULL THEN ''ANY'' ELSE :PROGRAM END))
       and not exists (select 1 from SFPL_ITEM_DESC i where a.item_id = i.item_id and i.work_loc = :ASGND_WORK_LOC))
   OR
   (PROGRAM =
     (CASE WHEN :PROGRAM = ''N/A'' THEN ''ANY'' WHEN :PROGRAM IS NULL THEN ''ANY'' ELSE :PROGRAM END)
    AND a.WORK_LOC =
    (CASE WHEN :ASGND_WORK_LOC = ''N/A'' THEN ''ANY'' WHEN :ASGND_WORK_LOC IS NULL THEN ''ANY'' ELSE :ASGND_WORK_LOC END))
)
AND 1=1 /*+ @filter */';
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

