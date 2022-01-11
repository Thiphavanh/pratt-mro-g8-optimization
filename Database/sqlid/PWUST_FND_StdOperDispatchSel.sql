
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_FND_STDOPERDISPATCHSEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_FND_STDOPERDISPATCHSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_FND_StdOperDispatchSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFFND';
v_description sfcore_sql_lib.description%type  :='Defect 50/897;G8R2SP4; defect 1272.';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT
       A.STDOPER_OBJECT_ID,
       A.STDOPER_OBJECT_ID AS OBJECT_ID,
       A.STDOPER_TAG,
       A.STDOPER_DESC,
       A.STDOPER_REV,
       A.STDOPER_PLAN_ID,
       A.STDOPER_PLAN_VER,
       A.STDOPER_PLAN_REV,
       A.STDOPER_PLAN_ALT,
       A.STDOPER_PLAN_ID AS PLAN_ID,
       A.STDOPER_PLAN_VER AS PLAN_VERSION,
       A.STDOPER_PLAN_REV AS PLAN_REVISION,
       A.STDOPER_PLAN_ALT AS PLAN_ALTERATIONS,
       A.STATUS,
       A.UPDT_USERID,
       A.TIME_STAMP,
       C.PROGRAM,
       D.ITEM_ID,
       D.PART_NO,
       D.PART_CHG,
       D.ITEM_TYPE,
       D.ITEM_SUBTYPE,
       E.OBJECT_TYPE AS OBJECT_TYPE,
       E.PWP_ID AS RELEASE_PACKAGE,
       E.PWP_ID,
       NULL AS TO_PWP_ID,
       E.SECURITY_GROUP,
       C.PLAN_TYPE AS STD_OPER_TYPE,
       C.WORK_FLOW,E.COMMODITY_JURISDICTION,E.COMMODITY_CLASSIFICATION, CASE WHEN B.EXTERNAL_PLAN_REV IS NULL THEN ''N/A'' ELSE B.EXTERNAL_PLAN_REV END AS EXTERNAL_PLAN_REV,
       B.OBSOLETE_FLAG,
       e.ucf_mmobj_vch1 as ASGND_WORK_LOC
FROM
       SFFND_STDOPER A,
       SFPL_PLAN_REV B,
       SFPL_PLAN_DESC C,
       SFPL_ITEM_DESC_MASTER_ALL D,
       SFCORE_MM_OBJECT E,
       SFFND_DOC_TYPE_DEF F
WHERE A.STDOPER_PLAN_ID = B.PLAN_ID AND
      A.STDOPER_PLAN_VER = B.PLAN_VERSION AND
      A.STDOPER_PLAN_REV = B.PLAN_REVISION AND
      A.STDOPER_PLAN_ALT = B.PLAN_ALTERATIONS AND
      B.PLAN_ID = C.PLAN_ID AND
      B.PLAN_UPDT_NO = C.PLAN_UPDT_NO AND
      C.ITEM_ID = D.ITEM_ID AND
      A.STDOPER_OBJECT_ID = E.OBJECT_ID
      AND C.DOC_TYPE = F.DOC_TYPE
      AND C.PLAN_TYPE = F.DOC_SUB_TYPE
      AND C.WORK_FLOW = F.WORK_FLOW
      and e.object_type = ''STDOPER''
      AND ((EXISTS (SELECT 1 FROM SFFND_USER_SEC_GRP X, SFFND_SECURITY_GROUP_DEF Y, SFCORE_MM_OBJECT_SEC_GRP Z
                 WHERE USERID = :@USERID
                 AND X.SECURITY_GROUP = Y.SECURITY_GROUP
                 AND Y.OBSOLETE_RECORD_FLAG = ''N''
                 AND SYSDATE BETWEEN X.EFFECTIVE_START_DATE AND X.EFFECTIVE_END_DATE
                 AND Z.SECURITY_GROUP = X.SECURITY_GROUP
                 AND Z.object_type = ''STDOPER''
                 AND Z.OBJECT_ID = E.OBJECT_ID))
           OR (0 = (select count(*) from SFCORE_MM_OBJECT_SEC_GRP Z2 where Z2.object_type = ''STDOPER'' AND Z2.OBJECT_ID = E.OBJECT_ID)))';
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

