
set define off
prompt ---------------------------------------;
prompt Executing ... PWMROI_PLAN_SUBJECT_AUTHORITY_SEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWMROI_PLAN_SUBJECT_AUTHORITY_SEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWMROI_PLAN_SUBJECT_AUTHORITY_SEL';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Defect 1890,1924';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT
a.subject_no,
a.subject_rev,
a.subject_description,
a.authority_type,
a.authority_detail,
b.authority as authority_notes,
a.manual_no,
a.manual_rev,
a.manual_provider,
a.manual_type,
d.manual_release_date,
a.manual_section,
a.sb_part_no,
a.authority_rev,
a.authority_rev_date,
a.authority_type||'' ''||a.manual_section||'' ''||a.authority_detail as arc_auth,
a.updt_userid,
a.time_stamp,
a.last_action,
null as security_group,
a.SEQNO
from PWMROI_PLAN_SUBJECT_AUTHORITY a,SFOR_SFPL_PLAN_SUBJECT b ,SFPL_PLAN_V C,pwmroi_manual_desc d
WHERE A.PLAN_ID = :PLAN_ID AND A.PLAN_REVISION = :PLAN_REVISION AND
A.PLAN_ID = B.PLAN_ID AND
A.PLAN_ID = C.PLAN_ID  AND
A.PLAN_REVISION = C.PLAN_REVISION AND
 B.PLAN_ID = C.PLAN_ID AND
 B.PLAN_UPDT_NO = C.PLAN_UPDT_NO AND
A.SUBJECT_NO = B.SUBJECT_NO and
a.manual_no = d.manual_no and 
a.manual_rev = d.manual_rev';
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

