set define off
declare
v_folder_id varchar2(40) :='';
v_sql_id varchar2(85) :='HAMSI_WID_PAARAUTHORISEDPERMISIONSEL';
v_sql_id_displ varchar2(85) :='HAMSI_WID_PAARAuthorisedPermisionSel';
v_read_only number :='';
v_datasource varchar2(32) :='';
v_stype varchar2(32) :='SFWID';
v_site varchar2(10) :='HAMSI';
v_description varchar2(200) :='Select WID PAAR Authorised Permission Selection';
v_sql_text varchar2(4000) :='select a.request_id,
       a.sght_id,
       a.pmn_action,
       a.pmn_type,
       a.pmn_type_entity,
       a.pmn_eff_from_date,
       a.pmn_eff_thru_date,
       a.auth_id,
       case 
         when a.sght_id is null
           then
             case a.pmn_action
               when ''PERMIT''
                 then ''A new permission has added ''||initcap(a.pmn_type)||'': ''||a.pmn_type_entity
                 else ''A new permission has removed ''||initcap(a.pmn_type)||'': ''||a.pmn_type_entity
             end
         else 
           case a.pmn_action
             when ''DENY''
               then
                 case b.pmn_action
                   when ''PERSISTED'' then ''The request to keep ''||initcap(a.pmn_type)||'': ''||a.pmn_type_entity||'' has been denied.''
                   when ''ADDED'' then ''The request to add ''||initcap(a.pmn_type)||'': ''||a.pmn_type_entity||'' has been denied.''
                   When ''DELETED'' then ''The request to remove ''||initcap(a.pmn_type)||'': ''||a.pmn_type_entity||'' has been approved.''
                 end
             when ''PERMIT''
               then
                 case b.pmn_action
                   when ''PERSISTED'' then ''The request to keep ''||initcap(a.pmn_type)||'': ''||a.pmn_type_entity||'' has been approved.''
                   when ''ADDED'' then ''The request to add ''||initcap(a.pmn_type)||'': ''||a.pmn_type_entity||'' has been approved.''
                   When ''DELETED'' then ''The request to remove ''||initcap(a.pmn_type)||'': ''||a.pmn_type_entity||'' has been denied.''
                 end
           end 
       end as paar_result,
       a.updt_userid,
       a.time_stamp,
       a.last_action,
       '''' as security_group
  from utasgi_wid_paar_pmn_sght b,
       utasgi_wid_paar_pmn_auth a
 where b.request_id(+) = a.request_id
   and b.sght_id(+) = a.sght_id 
   and a.request_id = :request_id
 order by decode(a.sght_id,null,2,1), a.pmn_type, a.pmn_type_entity';
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

