
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_PLG_REALPLANTOOLINFODRAG.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_PLG_REALPLANTOOLINFODRAG';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_PLG_RealPlanToolInfoDrag';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFPL';
v_description sfcore_sql_lib.description%type  :='Defect 936/933, defect 964/1034, defect 1136., defect 1691, defect 1541';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT DISTINCT
       TOOL_NO,
       TOOL_REV,
       TOOL_TITLE,
       ITEM_TYPE,
       ITEM_SUBTYPE,
       SERIAL_FLAG,
       EXP_FLAG,
       SECURITY_GROUP,
       OPTIONAL_FLAG,
       OVERUSE_FLAG,
       TOOL_NOTES,
       DISPLAY_LINE_NO,
       QTY,
       CALLED_FROM,
       CROSS_ORDER_FLAG,
       ORIENTATION_FLAG
FROM (
SELECT T.TOOL_NO, T.TOOL_CHG AS TOOL_REV, T.TOOL_TITLE, T.ITEM_TYPE, T.ITEM_SUBTYPE,
       I.COMP_SERIAL_FLAG AS SERIAL_FLAG, I.EXP_FLAG AS EXP_FLAG, '''' as SECURITY_GROUP,
       ''N'' AS OPTIONAL_FLAG,
       ''N'' AS OVERUSE_FLAG,
       NULL AS TOOL_NOTES,
       NULL AS DISPLAY_LINE_NO,
       1 AS QTY,
       ''AUTHORING_PALETTE'' AS CALLED_FROM,
       (SELECT CROSS_ORDER_FLAG FROM SFPL_OPERATION_DESC D WHERE D.PLAN_ID = :PLAN_ID AND D.OPER_KEY = :OPER_KEY AND D.OPER_UPDT_NO = :OPER_UPDT_NO) AS CROSS_ORDER_FLAG,
       (SELECT CASE WHEN ORIENTATION_FLAG = ''D'' THEN ''Data Collection Centric'' ELSE ''Unit Centric'' END FROM SFPL_OPERATION_DESC D WHERE D.PLAN_ID = :PLAN_ID AND D.OPER_KEY = :OPER_KEY AND D.OPER_UPDT_NO = :OPER_UPDT_NO) AS ORIENTATION_FLAG
 FROM SFFND_TOOL T,
       SFPL_ITEM_DESC_ALL I
 WHERE T.TOOL_NO = I.PART_NO
   AND T.TOOL_CHG = I.PART_CHG
   AND I.PROGRAM = ''ANY''
   AND (T.Security_Group in (SELECT Security_Group FROM SFFND_USER_SEC_GRP WHERE USERID = :@USERID) OR T.Security_Group IS NULL)
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
   AND (i.part_no, i.work_loc) in (select a.part_no, a.work_loc from SFPL_ITEM_DESC_ALL a where a.part_no = i.part_no and rownum = 1)
   AND ITEM_ID <> (SELECT ITEM_ID
                         FROM SFPL_PLAN_V P
                        WHERE P.PLAN_ID = :PLAN_ID
                          AND P.PLAN_VERSION = :PLAN_VERSION
                          AND P.PLAN_REVISION = :PLAN_REVISION
                          AND P.PLAN_ALTERATIONS = :PLAN_ALTERATIONS)) X
WHERE 1=1  /*+ @Filter */
   AND X.ITEM_TYPE = ''TOOL''
   AND X.TOOL_NO != ''N/A''
ORDER BY TOOL_NO';
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

