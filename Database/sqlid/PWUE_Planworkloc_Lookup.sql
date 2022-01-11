set define off
declare
v_folder_id varchar2(40) :='OPCERT';
v_sql_id varchar2(85) :='PWUE_PLANWORKLOC_LOOKUP';
v_sql_id_displ varchar2(85) :='PWUE_Planworkloc_Lookup';
v_read_only number :='';
v_datasource varchar2(32) :='';
v_stype varchar2(32) :='SFPL';
v_site varchar2(10) :='PWUE';
v_description varchar2(200) :='SAEP_OP_08';
v_sql_text varchar2(4000) :='select c.work_loc as plnd_work_loc, c.loc_title 
  from sffnd_work_loc_def c
 where exists (select ''Y''
                 from utasgi_user_sec_grp_xref b, sffnd_user_sec_grp a
                where b.security_group = a.security_group
                  and a.userid = :@USERID
                  and b.hr_loc = c.work_loc)
   and nvl(c.obsolete_record_flag,''N'') =''N''
   and c.work_loc != ''ANY''
   and c.work_loc not like ''%,%''
  order by c.work_loc';

begin
begin
insert into sfcore_sql_lib(sql_id,sql_id_displ,updt_userid,time_stamp,read_only,datasource,stype, description,sql_text)
    values(v_sql_id,v_sql_id_displ,user,sysdate,v_read_only,v_datasource,v_stype, v_description,replace(v_sql_text,chr(10),chr(13)||chr(10)));
commit;
exception when dup_val_on_index then
  update sfcore_sql_lib
  set sql_text=replace(v_sql_text,chr(10),chr(13)||chr(10)),updt_userid=user,time_stamp=sysdate,
read_only=v_read_only,datasource=v_datasource,stype=v_stype,  
description=v_description
  where sql_id=v_sql_id;
  commit;
end;
begin
if v_folder_id is not null then
insert into sfcore_sql_lib_folder(sql_id,folder_id,updt_userid,time_stamp)
values(v_sql_id,v_folder_id,user,sysdate);
commit;
end if;
exception when others then null;
end;
end;
/

set define on

