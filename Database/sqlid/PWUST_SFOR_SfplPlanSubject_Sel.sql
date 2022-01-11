
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_SFOR_SFPLPLANSUBJECT_SEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_SFOR_SFPLPLANSUBJECT_SEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_SFOR_SfplPlanSubject_Sel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFPL';
v_description sfcore_sql_lib.description%type  :='SMRO_PLG_205';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT DISTINCT A.OBJECT_ID,  a.authority,
                A.SUBJECT_NO,
                A.SUBJECT_REV,
                A.SUBJECT_REV AS MAX_REVISION,
                A.SUBJECT_STATE,
                A.TITLE,
                A.UPDT_USERID,
                A.TIME_STAMP,
                A.IS_DEFAULT,
                S.MANDATORY,
                C.PROGRAM,
                C.PLND_LOCATION_ID, 
                L.WORK_LOC,
                A.LAST_ACTION,
                A.PLAN_UPDT_NO,
                A.PLAN_ID,
                B.PLAN_ID,
                PLAN_VERSION,
                PLAN_REVISION,
                PLAN_ALTERATIONS,
                B.PLAN_UPDT_NO,
                A.OBSOLETE_RECORD_FLAG,
                A.SUBJECT_STATUS,
                A.CHG_AUTH_TYPE,
                A.CHG_AUTH_NUM,
                CASE A.SUBJECT_STATE
                  WHEN ''NO EDIT'' THEN
                   ''COMPLETE''
                  WHEN ''IN EDIT'' THEN
                   ''AUTHORING''
                  ELSE
                   ''COMPLETE''
                END AS STATUS,
                C.SECURITY_GROUP
  FROM SFOR_SFPL_PLAN_SUBJECT       A,
       SFPL_PLAN_REV                B,
       SFPL_PLAN_DESC C LEFT OUTER JOIN SFFND_WORK_LOC_DEF L ON C.PLND_LOCATION_ID = L.LOCATION_ID,
       PWUST_SFOR_SFPL_PLAN_SUBJECT S
 WHERE A.PLAN_ID = B.PLAN_ID
   AND A.PLAN_UPDT_NO = B.PLAN_UPDT_NO
   AND A.PLAN_ID = C.PLAN_ID
   AND A.PLAN_UPDT_NO = C.PLAN_UPDT_NO
   AND A.PLAN_ID = S.PLAN_ID
   AND A.PLAN_UPDT_NO = S.PLAN_UPDT_NO
   AND A.SUBJECT_NO = S.SUBJECT_NO
   AND A.SUBJECT_REV = S.SUBJECT_REV
   AND B.PLAN_ID = :PLAN_ID
   AND B.PLAN_REVISION = :PLAN_REVISION
   AND B.PLAN_VERSION = :PLAN_VERSION
   AND B.PLAN_ALTERATIONS = :PLAN_ALTERATIONS
 ORDER BY DECODE(OBSOLETE_RECORD_FLAG, ''Y'', 2, 1), SUBJECT_NO';
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

