
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_STDOPERSCOPE.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_STDOPERSCOPE';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_StdOperScope';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFPL';
v_description sfcore_sql_lib.description%type  :='SMRO_WOE_202 Defect 866, 897.';
v_sql_text sfcore_sql_lib.sql_text%type  :='select
  PART_NO
  ,OPER_NO
  ,STEP_NO
  ,LOW_NAV_LVL
  ,PLAN_ID
  ,PLAN_VERSION
  ,PLAN_REVISION
  ,PLAN_ALTERATIONS
  ,SCOPE_PLAN_ID, SCOPE_PLAN_VERSION, SCOPE_PLAN_REVISION, SCOPE_PLAN_ALTERATIONS, SCOPE_OPER_NO, SCOPE_OPER_KEY, SCOPE_OPER_UPDT_NO, SCOPE_STEP_NO
  ,PART_CHG
  ,ITEM_TYPE
  ,ITEM_SUBTYPE
  ,PROGRAM
  ,PLND_PROGRAM
  ,OPER_KEY
  ,STEP_KEY
  ,PLAN_UPDT_NO
  ,OPER_UPDT_NO
  ,STEP_UPDT_NO
  ,MFG_BOM_CHG
  ,CA_ID
  ,NODE_ID
  ,CA_RESOLN_ID
  ,CA_REQUEST_ID
  ,ORDER_ID
  ,DISC_ID
  ,DISC_LINE_NO
  ,stdoper_tag
  ,stdoper_desc
  ,stdoper_rev
  ,stdoper_object_id
  ,rev_status
  ,task_type
  ,step_exe_order
  ,SECURITY_GROUP
  ,asgnd_security_group
  ,STD_OPER_TYPE
  ,WORK_FLOW
  ,DISPLAY_SEQUENCE
  ,OPERATION_OVERLAP_FLAG
  ,SERIAL_FLAG
  ,PLND_WORK_LOC
  ,RELEASE_PACKAGE,COMMODITY_JURISDICTION,COMMODITY_CLASSIFICATION,
   ASGND_WORK_LOC
FROM
(
select distinct
  PART_NO
  ,OPER_NO
  ,(CASE STEP_NO WHEN ''...'' THEN (CASE WHEN TEXT_TYPE=''PLANNING'' THEN ''FOOTER'' ELSE ''HEADER'' END) ELSE STEP_NO END) AS STEP_NO
  ,LOW_NAV_LVL
  ,a.PLAN_ID
  ,PLAN_VERSION
  ,PLAN_REVISION
  ,PLAN_ALTERATIONS
  ,a.PLAN_ID AS SCOPE_PLAN_ID, PLAN_VERSION AS SCOPE_PLAN_VERSION, PLAN_REVISION AS SCOPE_PLAN_REVISION, PLAN_ALTERATIONS AS SCOPE_PLAN_ALTERATIONS, OPER_NO AS SCOPE_OPER_NO, a.OPER_KEY AS SCOPE_OPER_KEY, a.OPER_UPDT_NO AS SCOPE_OPER_UPDT_NO, (CASE STEP_NO WHEN ''...'' THEN (CASE WHEN TEXT_TYPE=''PLANNING'' THEN ''FOOTER'' ELSE ''HEADER'' END) ELSE STEP_NO END) AS SCOPE_STEP_NO
  ,PART_CHG
  ,ITEM_TYPE
  ,ITEM_SUBTYPE
  ,PROGRAM
  ,PROGRAM AS PLND_PROGRAM

  ,a.OPER_KEY
  ,a.STEP_KEY
  ,a.PLAN_UPDT_NO
  ,a.OPER_UPDT_NO
  ,STEP_UPDT_NO
  ,MFG_BOM_CHG
 ,CA_ID
 ,NODE_ID
 ,CA_RESOLN_ID
 ,CA_REQUEST_ID

 ,ORDER_ID
 ,DISC_ID
 ,DISC_LINE_NO
 ,stdoper_tag
 ,stdoper_desc
 ,stdoper_rev
 ,b.stdoper_object_id
 ,rev_status,

 task_type,
 case seq_steps_flag when ''Y'' then exe_order end as step_exe_order,
 d.SECURITY_GROUP,
 d.security_group as asgnd_security_group,
 A.PLAN_TYPE as STD_OPER_TYPE,A.WORK_FLOW, A.DISPLAY_SEQUENCE, A.OPERATION_OVERLAP_FLAG, A.SERIAL_FLAG,
 CASE WHEN A.OPER_PLND_WORK_LOC IS NOT NULL THEN A.OPER_PLND_WORK_LOC ELSE A.PLND_WORK_LOC END AS PLND_WORK_LOC,
 D.PWP_ID AS RELEASE_PACKAGE,D.COMMODITY_JURISDICTION,D.COMMODITY_CLASSIFICATION,
 d.ucf_mmobj_vch1 as ASGND_WORK_LOC
 from sfpl_scope_v a, sffnd_stdoper b, sffnd_doc_type_def c, SFPL_OPERATION_TEXT T, SFCORE_MM_OBJECT D
 where
   a.PLAN_ID=:PLAN_ID and
   a.PLAN_VERSION=:PLAN_VERSION and
   a.PLAN_REVISION=:PLAN_REVISION and
   a.PLAN_ALTERATIONS=:PLAN_ALTERATIONS
   AND a.PLAN_ID = T.PLAN_ID
   AND a.OPER_KEY = T.OPER_KEY
   AND a.OPER_UPDT_NO = T.OPER_UPDT_NO
   AND T.TEXT_TYPE IN (''HEADER_PLANNING'',''PLANNING'')
   AND (STEP_NO = ''...'' OR (STEP_NO != ''...'' AND T.TEXT_TYPE = ''PLANNING''))

   and a.plan_id = b.stdoper_plan_id
   and a.plan_version = b.stdoper_plan_ver
   and a.plan_revision = b.stdoper_plan_rev
   and a.plan_alterations = b.stdoper_plan_alt
   and a.doc_type = c.doc_type
   and a.plan_type = c.doc_sub_type
   AND A.WORK_FLOW = C.WORK_FLOW
   AND b.STDOPER_OBJECT_ID = D.OBJECT_ID
order by a.plan_id, plan_version, plan_revision,
   plan_alterations, a.oper_no, CASE WHEN STEP_NO != ''FOOTER'' THEN STEP_EXE_ORDER END,
   CASE STEP_NO WHEN ''HEADER'' THEN '' '' WHEN ''FOOTER'' THEN CHR(''254'') ELSE SFMFG.SFDB_LPAD_VARCHAR(STEP_NO, 10, ''0'') END
)
WHERE 1 = 1';
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

