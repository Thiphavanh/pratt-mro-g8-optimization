
set define off
prompt ---------------------------------------;
prompt Executing ... PWMROI_MANUAL_PLAN_UPD_SEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWMROI_MANUAL_PLAN_UPD_SEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWMROI_MANUAL_PLAN_UPD_SEL';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='defect 1995';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT DISTINCT ''N'' as update_plan_flag,
                b.plan_no,
                a.plan_revision,
                b.part_no,
                a.manual_rev as old_manual_rev,
                b.rev_status,
                a.subject_no,
                c.title as subject_title,
                a.subject_rev,
                b.plan_id,
                null as security_group,
                a.seqno
  FROM PWMROI_PLAN_SUBJECT_AUTHORITY a,
       SFPL_PLAN_V                   B,
       pwmroi_manual_latest_rev_v    e,
       sfor_sfpl_plan_subject c
 WHERE a.manual_no = e.manual_no
   and a.manual_rev != e.manual_rev
   and a.manual_no = :MANUAL_NO
   and A.PLAN_ID = B.PLAN_ID
   AND A.PLAN_REVISION = B.PLAN_REVISION
   and c.plan_id = a.plan_id
                    and c.subject_no = a.subject_no
                    and c.subject_rev = a.subject_rev
                    and c.plan_updt_no =
                        (select max(plan_updt_no)
                           from sfor_sfpl_plan_subject d
                          where c.plan_id = d.plan_id
                            and c.subject_no = d.subject_no
                            and c.subject_rev = d.subject_rev)';
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

