set define off
declare
v_folder_id varchar2(40) :='';
v_sql_id varchar2(85) :='HAMSI_PLG_PAARACTIVEREQUESTEXISTSQRY';
v_sql_id_displ varchar2(85) :='HAMSI_PLG_PaarActiveRequestExistsQry';
v_read_only number :='';
v_datasource varchar2(32) :='';
v_stype varchar2(32) :='SFPL';
v_site varchar2(10) :='HAMSI';
v_description varchar2(200) :='PAAR Requests - Active Request Exists';
v_sql_text varchar2(4000) :='select decode(count(*),0,''N'',''Y'') as active_request_exists
  from utasgi_plg_paar_desc
 where plan_id = :plan_id
   and request_status in (''OPEN'',''IN REVIEW'')';

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

