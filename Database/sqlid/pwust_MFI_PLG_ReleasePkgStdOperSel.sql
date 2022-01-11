
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_MFI_PLG_RELEASEPKGSTDOPERSEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_MFI_PLG_RELEASEPKGSTDOPERSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='pwust_MFI_PLG_ReleasePkgStdOperSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='select all std oper in Release Package, defect 1355';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT  OBJECT_TYPE,
        OBJECT_ID ,
        STDOPER_OBJECT_ID,
        OBJECT_TAG,
        STDOPER_TAG,
        STDOPER_DESC,
        OBJECT_REV,
        STATUS,
        PWP_ID,
        CHANGE_NO,
        CHANGE_TYPE,
        UPDT_USERID,
        TIME_STAMP,
        STDOPER_PLAN_ID, 
        STDOPER_PLAN_VER, 
        STDOPER_PLAN_REV, 
        STDOPER_PLAN_ALT, 
        STDOPER_OPER_KEY,
        SECURITY_GROUP,
        STD_OPER_TYPE,
        WORK_FLOW,
        COMMODITY_JURISDICTION,
        COMMODITY_CLASSIFICATION,
        OBSOLETE_FLAG
FROM (SELECT DISTINCT
            M.OBJECT_TYPE,
            D.STDOPER_OBJECT_ID AS OBJECT_ID ,
            D.STDOPER_OBJECT_ID,
            D.STDOPER_TAG AS OBJECT_TAG,
            D.STDOPER_TAG,
            D.STDOPER_DESC,
            M.OBJECT_REV AS OBJECT_REV,
            D.STATUS,
            M.PWP_ID,
            C.CHANGE_NO,
            C.CHANGE_TYPE,
            D.UPDT_USERID,
            D.TIME_STAMP,
            D.STDOPER_PLAN_ID, D.STDOPER_PLAN_VER, D.STDOPER_PLAN_REV, D.STDOPER_PLAN_ALT, D.STDOPER_OPER_KEY,
            M.SECURITY_GROUP,
            M.DOC_SUB_TYPE As STD_OPER_TYPE,
            M.WORK_FLOW,
            M.COMMODITY_JURISDICTION,
            M.COMMODITY_CLASSIFICATION,
            CASE D.STATUS WHEN ''OBSOLETE'' THEN ''Y'' ELSE ''N'' END AS OBSOLETE_FLAG
            FROM  SFCORE_MM_OBJECT M  LEFT OUTER JOIN  SFPL_PLANNED_ACTIONS P  ON  M.OBJECT_ID  = P.OBJECT_ID
                                      LEFT OUTER JOIN  SFPL_CHANGE_REQUEST  C  ON  P.CHANGE_REQUEST_ID  = C.CHANGE_REQUEST_ID, SFFND_STDOPER D
            WHERE  M.OBJECT_ID  = D.STDOPER_OBJECT_ID 
            AND CASE WHEN P.PLANNED_ACTION_ID  IS NULL THEN M.PWP_ID  ELSE P.PWP_ID END  =:PWP_ID
            and exists (SELECT 1 
                        FROM SFFND_USER_SEC_GRP X, SFFND_SECURITY_GROUP_DEF Y
                        WHERE X.SECURITY_GROUP = Y.SECURITY_GROUP
                        AND x.security_group = m.security_group
                        and USERID = :@USERID
                        AND Y.OBSOLETE_RECORD_FLAG = ''N''
                        AND SYSDATE BETWEEN X.EFFECTIVE_START_DATE AND X.EFFECTIVE_END_DATE)            
AND M.ACQUIRE_EXPRESS  = ''N'') X
where 1=1
ORDER BY OBSOLETE_FLAG';
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

