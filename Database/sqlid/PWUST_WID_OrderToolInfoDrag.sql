
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_WID_ORDERTOOLINFODRAG.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_WID_ORDERTOOLINFODRAG';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_WID_OrderToolInfoDrag';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFWID';
v_description sfcore_sql_lib.description%type  :='Defect 365, defect 964/1034, defect 1136.';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT
TOOL_NO, TOOL_REV, TOOL_TITLE, ITEM_TYPE, ITEM_SUBTYPE, SERIAL_FLAG, EXP_FLAG, SECURITY_GROUP,
 NULL AS OPTIONAL_FLAG,
       ''N'' AS OVERUSE_FLAG,
       NULL AS TOOL_NOTES,
       NULL AS DISPLAY_LINE_NO,
       1 AS QTY,
       ''AUTHORING_PALETTE'' AS CALLED_FROM,
       (SELECT decode(orientation_flag,''U'',''Unit Centric'',''D'',''Data Collection Centric'')  FROM SFWID_OPER_DESC D WHERE D.ORDER_ID = :ORDER_ID
AND   D.OPER_KEY= :OPER_KEY AND D.STEP_KEY=-1) AS ORIENTATION_FLAG,
(SELECT CROSS_ORDER_FLAG FROM SFWID_OPER_DESC D WHERE D.ORDER_ID=:ORDER_ID AND D.OPER_KEY=:OPER_KEY AND D.STEP_KEY=-1) AS CROSS_ORDER_FLAG
FROM (
SELECT T.TOOL_NO, T.TOOL_CHG AS TOOL_REV, T.TOOL_TITLE, T.ITEM_TYPE, T.ITEM_SUBTYPE,
       I.COMP_SERIAL_FLAG AS SERIAL_FLAG, I.EXP_FLAG AS EXP_FLAG, I.SECURITY_GROUP
  FROM SFFND_TOOL T,
       SFPL_ITEM_DESC_ALL I
 WHERE
    ((0 = (select count(*) from sfpl_item_desc_sec_grp where part_no = i.part_no))
    or exists
        (select 1 from sffnd_user_work_centers b, sffnd_work_loc_def c, utasgi_user_sec_grp_xref d, sfpl_item_desc_sec_grp e
        where b.userid = :@USERID
        and c.location_id = b.asgnd_location_id
        and d.hr_loc = c.work_loc
        and e.part_no = i.part_no
        and e.security_group = d.security_group))
   and T.TOOL_NO = I.PART_NO
   AND I.ITEM_ID <> (SELECT O.ITEM_ID FROM SFWID_ORDER_DESC O WHERE O.ORDER_ID = :ORDER_ID )
   AND I.PROGRAM = ''ANY''
   AND NVL(T.OBSOLETE_RECORD_FLAG,''N'') = ''N''
UNION
SELECT T.TOOL_NO, T.TOOL_CHG AS TOOL_REV, T.TOOL_TITLE, T.ITEM_TYPE, T.ITEM_SUBTYPE,
       I.COMP_SERIAL_FLAG AS SERIAL_FLAG, I.EXP_FLAG AS EXP_FLAG, I.SECURITY_GROUP
  FROM SFFND_TOOL T,
       SFPL_ITEM_DESC_ALL I,
       SFWID_ORDER_DESC B
 WHERE
    ((0 = (select count(*) from sfpl_item_desc_sec_grp where part_no = i.part_no))
    or exists
        (select 1 from sffnd_user_work_centers b, sffnd_work_loc_def c, utasgi_user_sec_grp_xref d, sfpl_item_desc_sec_grp e
        where b.userid = :@USERID
        and c.location_id = b.asgnd_location_id
        and d.hr_loc = c.work_loc
        and e.part_no = i.part_no
        and e.security_group = d.security_group))
 AND B.ORDER_ID = :ORDER_ID
  AND T.TOOL_NO = I.PART_NO
  AND NVL(T.OBSOLETE_RECORD_FLAG,''N'') = ''N''
  AND I.ITEM_ID <> (SELECT O.ITEM_ID FROM SFWID_ORDER_DESC O WHERE O.ORDER_ID = :ORDER_ID )
  AND (B.PROGRAM != ''ANY'' OR B.ASGND_LOCATION_ID != ''ANY'')
  AND (I.PROGRAM != ''ANY'' OR I.WORK_LOC != ''ANY'')
  AND ((I.PROGRAM = ''ANY''
        AND I.LOCATION_ID = (CASE WHEN B.ASGND_LOCATION_ID = ''N/A'' THEN ''ANY'' WHEN B.ASGND_LOCATION_ID IS NULL THEN ''ANY'' ELSE B.ASGND_LOCATION_ID END))
  OR  (I.PROGRAM = (CASE WHEN B.PROGRAM = ''N/A'' THEN ''ANY'' WHEN B.PROGRAM IS NULL THEN ''ANY'' ELSE B.PROGRAM END)
       AND I.WORK_LOC = ''ANY'')
  OR  (I.PROGRAM = (CASE WHEN B.PROGRAM = ''N/A'' THEN ''ANY'' WHEN B.PROGRAM IS NULL THEN ''ANY'' ELSE B.PROGRAM END)
       AND I.LOCATION_ID = (CASE WHEN B.ASGND_LOCATION_ID = ''N/A'' THEN ''ANY'' WHEN B.ASGND_LOCATION_ID IS NULL THEN ''ANY'' ELSE B.ASGND_LOCATION_ID END)))
) X
WHERE 1=1  /*+ @Filter */
 AND ITEM_TYPE = ''TOOL''
 AND TOOL_NO != ''N/A''
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

