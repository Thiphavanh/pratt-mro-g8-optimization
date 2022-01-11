
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_MFI_RPTORDEROPERTASKGROUP.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_MFI_RPTORDEROPERTASKGROUP';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_MFI_RptOrderOperTaskGroup';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFWID';
v_description sfcore_sql_lib.description%type  :='Defect 904;909';
v_sql_text sfcore_sql_lib.sql_text%type  :='select b.subject_no as TaskGroupNo, b.title as TaskGroupTitle, d.oper_no,
       d.title oper_title
  from sfmfg.sfor_sfwid_oper_subject c, sfmfg.sfwid_oper_desc d,
       SFOR_sfwid_order_subject b
where b.order_id =:order_id
   and b.obsolete_record_flag = ''N''
   and b.included_flag = ''INCLUDED''
   and c.included_flag = ''INCLUDED''
   and exists (select null
          from SFFND_DOC_TYPE_DEF a
         where a.instructions_type like ''MRO%''
           and a.obsolete_record_flag = ''N''
           and a.doc_sub_type = :order_type
           and a.doc_type = ''Process Order'')
   and c.order_id = b.order_id /*+ @Filter */
   AND c.subject_no = b.subject_no
   AND d.order_id = c.order_id
   AND d.oper_key = c.oper_key
   AND d.step_key = -1
UNION ALL
SELECT NULL as TaskGroupNo, ''NO TASK GROUPS'' TaskGroupTitle, NULL AS OPER_NO,
       NULL AS oper_title
  from dual
 WHERE NOT EXISTS
 (select b.subject_no as TaskGroupNo, b.title as TaskGroupTitle,
               d.oper_no, d.title oper_title
          from sfmfg.sfor_sfwid_oper_subject c, sfmfg.sfwid_oper_desc d,
               SFOR_sfwid_order_subject b
          where b.order_id =:order_id
           and b.obsolete_record_flag = ''N''
           and b.included_flag = ''INCLUDED''
           and c.included_flag = ''INCLUDED''
           and exists (select null
                  from SFFND_DOC_TYPE_DEF a
                 where a.instructions_type like ''MRO%''
                   and a.obsolete_record_flag = ''N''
                   and a.doc_sub_type = :order_type
                   and a.doc_type = ''Process Order'')
           and c.order_id = b.order_id /*+ @Filter */
           AND c.subject_no = b.subject_no
           AND d.order_id = c.order_id
           AND d.oper_key = c.oper_key
           AND d.step_key = -1)';
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

