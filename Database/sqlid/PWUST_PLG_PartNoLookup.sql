
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_PLG_PARTNOLOOKUP.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_PLG_PARTNOLOOKUP';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_PLG_PartNoLookup';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFPL';
v_description sfcore_sql_lib.description%type  :='Defect 964/1034.';
v_sql_text sfcore_sql_lib.sql_text%type  :='select /*+ index(sfpl_item_desc_master_all SFPL_ITEM_DESC_INDX003) */
 DISTINCT part_no as ITEM_NO_NEW,
          part_title as "Title",
          SECURITY_GROUP
FROM sfpl_item_desc_master_all a
WHERE obsolete_record_flag = ''N''
AND   COALESCE(ITEM_SUBTYPE,''X'') != ''STDOPER''
AND   ITEM_TYPE = :ITEM_TYPE
and
    ((0 = (select count(*) from sfpl_item_desc_sec_grp where part_no = a.part_no))
    or exists
        (select 1 from sffnd_user_work_centers b, sffnd_work_loc_def c, utasgi_user_sec_grp_xref d, sfpl_item_desc_sec_grp e
        where b.userid = :@USERID
        and c.location_id = b.asgnd_location_id
        and d.hr_loc = c.work_loc
        and e.part_no = a.part_no 
        and e.security_group = d.security_group))';
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

