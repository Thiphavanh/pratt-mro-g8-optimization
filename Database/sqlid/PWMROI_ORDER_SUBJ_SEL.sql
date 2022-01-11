
set define off
prompt ---------------------------------------;
prompt Executing ... PWMROI_ORDER_SUBJ_SEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWMROI_ORDER_SUBJ_SEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWMROI_ORDER_SUBJ_SEL';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='defect 1890,1924,1974';
v_sql_text sfcore_sql_lib.sql_text%type  :='select
:ORDER_ID AS ORDER_ID,
a.subject_no,
a.subject_rev,
subject_description,
authority_type,
authority_detail,
a.manual_no,
a.manual_rev,
a.manual_provider,
a.manual_type,
c.manual_release_date,
manual_section,
sb_part_no,
authority_rev,
authority_rev_date,
a.updt_userid,
a.time_stamp,
a.last_action,
a.authority,
null as security_group,
a.authority_type||'' ''||a.manual_section||'' ''||a.authority_detail as arc_authority,
a.seqno
FROM  PWMROI_ORDER_SUBJECT_AUTHORITY A, SFOR_SFWID_ORDER_SUBJECT  B, pwmroi_manual_desc c
WHERE A.ORDER_ID = B.ORDER_ID AND
      A.SUBJECT_NO = B.SUBJECT_NO AND
      A.SUBJECT_REV = B.SUBJECT_REV AND
      B.INCLUDED_FLAG = ''INCLUDED'' and
      a.manual_no = c.manual_no and 
      a.manual_rev = c.manual_rev and
      A.ORDER_ID = :ORDER_ID';
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

