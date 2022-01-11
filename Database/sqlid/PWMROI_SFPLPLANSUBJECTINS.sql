
set define off
prompt ---------------------------------------;
prompt Executing ... PWMROI_SFPLPLANSUBJECTINS.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWMROI_721CCDAC8C2341B8A268719CCA67FBCE';
v_sql_id sfcore_sql_lib.sql_id%type :='PWMROI_SFPLPLANSUBJECTINS';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWMROI_SfplPlanSubjectIns';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='SFOR';
v_sql_text sfcore_sql_lib.sql_text%type  :='EXEC com.pw.solumina.authority.application.impl.AuthorityImpl.insertSubjectTaskGroup (:PLAN_ID,:PLAN_VERSION,:PLAN_REVISION,:PLAN_ALTERATIONS ,:NEW_SUBJECT_NO,:TITLE,
:IS_DEFAULT,
:UCF_PLAN_SUBJECT_VCH1,
:UCF_PLAN_SUBJECT_VCH2,
:UCF_PLAN_SUBJECT_VCH3,
:UCF_PLAN_SUBJECT_VCH4,
:UCF_PLAN_SUBJECT_VCH5,
:UCF_PLAN_SUBJECT_VCH6,
:UCF_PLAN_SUBJECT_VCH7,
:UCF_PLAN_SUBJECT_VCH8,
:UCF_PLAN_SUBJECT_VCH9,
:UCF_PLAN_SUBJECT_VCH10,
:UCF_PLAN_SUBJECT_VCH11,
:UCF_PLAN_SUBJECT_VCH12,
:UCF_PLAN_SUBJECT_VCH13,
:UCF_PLAN_SUBJECT_VCH14,
:UCF_PLAN_SUBJECT_VCH15,
:UCF_PLAN_SUBJECT_NUM1,
:UCF_PLAN_SUBJECT_NUM2,
:UCF_PLAN_SUBJECT_NUM3,
:UCF_PLAN_SUBJECT_NUM4,
:UCF_PLAN_SUBJECT_NUM5,
:UCF_PLAN_SUBJECT_FLAG1,
:UCF_PLAN_SUBJECT_FLAG2,
:UCF_PLAN_SUBJECT_FLAG3,
:UCF_PLAN_SUBJECT_FLAG4,
:UCF_PLAN_SUBJECT_FLAG5,
:UCF_PLAN_SUBJECT_DATE1,
:UCF_PLAN_SUBJECT_DATE2,
:UCF_PLAN_SUBJECT_DATE3,
:UCF_PLAN_SUBJECT_DATE4,
:UCF_PLAN_SUBJECT_DATE5,
:NEW_AUTHORITY,
:PLAN_UPDT_NO)';
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

