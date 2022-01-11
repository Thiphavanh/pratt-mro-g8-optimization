
set define off
prompt ---------------------------------------;
prompt Executing ... PWMROI_SFPLPLANSUBJECT_SEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWMROI_721CCDAC8C2341B8A268719CCA67FBCE';
v_sql_id sfcore_sql_lib.sql_id%type :='PWMROI_SFPLPLANSUBJECT_SEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWMROI_SfplPlanSubject_Sel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='SFOR';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT A.OBJECT_ID, A.SUBJECT_NO, A.SUBJECT_REV, A.SUBJECT_REV AS MAX_REVISION, A.SUBJECT_STATE, A.TITLE,
A.UPDT_USERID, A.TIME_STAMP, A.IS_DEFAULT, A.LAST_ACTION,A.AUTHORITY,
A.PLAN_UPDT_NO, A.PLAN_ID, B.PLAN_ID, PLAN_VERSION, PLAN_REVISION, PLAN_ALTERATIONS,
B.PLAN_UPDT_NO,A.OBSOLETE_RECORD_FLAG,
A.SUBJECT_STATUS, A.CHG_AUTH_TYPE,A.CHG_AUTH_NUM,
       CASE A.SUBJECT_STATE WHEN ''NO EDIT'' THEN ''COMPLETE''
                            WHEN ''IN EDIT'' THEN ''AUTHORING''
                            ELSE ''COMPLETE'' END
       AS STATUS, C.SECURITY_GROUP,
UCF_PLAN_SUBJECT_VCH1,
UCF_PLAN_SUBJECT_VCH2,
UCF_PLAN_SUBJECT_VCH3,
UCF_PLAN_SUBJECT_VCH4,
UCF_PLAN_SUBJECT_VCH5,
UCF_PLAN_SUBJECT_VCH6,
UCF_PLAN_SUBJECT_VCH7,
UCF_PLAN_SUBJECT_VCH8,
UCF_PLAN_SUBJECT_VCH9,
UCF_PLAN_SUBJECT_VCH10,
UCF_PLAN_SUBJECT_VCH11,
UCF_PLAN_SUBJECT_VCH12,
UCF_PLAN_SUBJECT_VCH13,
UCF_PLAN_SUBJECT_VCH14,
UCF_PLAN_SUBJECT_VCH15,
UCF_PLAN_SUBJECT_NUM1,
UCF_PLAN_SUBJECT_NUM2,
UCF_PLAN_SUBJECT_NUM3,
UCF_PLAN_SUBJECT_NUM4,
UCF_PLAN_SUBJECT_NUM5,
UCF_PLAN_SUBJECT_FLAG1,
UCF_PLAN_SUBJECT_FLAG2,
UCF_PLAN_SUBJECT_FLAG3,
UCF_PLAN_SUBJECT_FLAG4,
UCF_PLAN_SUBJECT_FLAG5,
UCF_PLAN_SUBJECT_DATE1,
UCF_PLAN_SUBJECT_DATE2,
UCF_PLAN_SUBJECT_DATE3,
UCF_PLAN_SUBJECT_DATE4,
UCF_PLAN_SUBJECT_DATE5
FROM SFOR_SFPL_PLAN_SUBJECT A, SFPL_PLAN_REV B, SFPL_PLAN_DESC C
WHERE A.PLAN_ID = B.PLAN_ID
  AND A.PLAN_UPDT_NO = B.PLAN_UPDT_NO
  AND A.PLAN_ID = C.PLAN_ID
  AND A.PLAN_UPDT_NO = C.PLAN_UPDT_NO
  AND B.PLAN_ID =:PLAN_ID
  AND B.PLAN_REVISION =:PLAN_REVISION
  AND B.PLAN_VERSION =:PLAN_VERSION
  AND B.PLAN_ALTERATIONS =:PLAN_ALTERATIONS
ORDER BY DECODE(OBSOLETE_RECORD_FLAG,''Y'',2,1),SUBJECT_NO';
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

