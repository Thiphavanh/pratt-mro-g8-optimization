
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_PLG_PLANTOOLINFODRAG.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_PLG_PLANTOOLINFODRAG';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_PLG_PlanToolInfoDrag';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Defect 365/897/1241';
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
       I.COMP_SERIAL_FLAG AS SERIAL_FLAG, I.EXP_FLAG AS EXP_FLAG, I.SECURITY_GROUP,
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
   and (i.security_group is null or (i.security_group = :ASGND_SECURITY_GROUP))
   AND NVL(T.OBSOLETE_RECORD_FLAG,''N'') = ''N''
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

