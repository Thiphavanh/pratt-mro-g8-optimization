
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_ORDERCERTLOOKUP.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_ORDERCERTLOOKUP';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_OrderCertLookup';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFWID';
v_description sfcore_sql_lib.description%type  :='SMRO_USR_205; Defect 697';
v_sql_text sfcore_sql_lib.sql_text%type  :='select loc.work_loc, c.cert  as Buyoff_Cert,  c.cert as dat_col_cert, decode(c.description,null,''...'',description) as Description
  from sffnd_work_loc_def loc, sffnd_company_def comp,
       SFFND_CERT_DEF c, pwue_general_lookup d
where loc.company_id = comp.company_id
   and loc.location_id = :ASGND_LOCATION_ID
   and d.data_field = ''CERT''
   and d.data_value = loc.work_loc
   and nvl(loc.obsolete_record_flag, ''N'') = ''N''
   and loc.work_loc != ''ANY''
   and loc.obsolete_record_flag =c.obsolete_record_flag
   and c.cert = d.data_value_1
union
select null as work_loc, c.cert  as Buyoff_Cert,   c.cert as dat_col_cert,  decode(c.description,null,''...'',description) as Description
  from sffnd_work_loc_def loc, sffnd_company_def comp,
       sffnd_user_work_centers usr, SFFND_CERT_DEF c
where loc.company_id = comp.company_id
   and loc.location_id = usr.asgnd_location_id
   and usr.userid = :@userid
   and nvl(loc.obsolete_record_flag, ''N'') = ''N''
   and loc.work_loc != ''ANY''
   and loc.obsolete_record_flag =c.obsolete_record_flag
   and c.cert not in (select t.data_value_1 from pwue_general_lookup t where t.data_field = ''CERT'')
order by work_loc, buyoff_cert';
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

