
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_SFADMCERTSSELL_WORK_LOC.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_SFADMCERTSSELL_WORK_LOC';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_SfadmCertsSelL_Work_Loc';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='SMRO_USR_205; Defect 1840';
v_sql_text sfcore_sql_lib.sql_text%type  :='select  cert,
        data_value as work_location,
        (select z.loc_title from SFFND_WORK_LOC_DEF z where b.data_value = z.work_loc) as LOC_DESC,
        cert_type,
        description,
        b.updt_userid,
        b.time_stamp,
        cert_group,
        obsolete_record_flag
from SFFND_CERT_DEF A, PWUE_GENERAL_LOOKUP B
WHERE A.CERT = B.DATA_VALUE_1
AND B.DATA_FIELD = ''CERT''
and :CERT = cert
AND data_value in (SELECT LOC.WORK_LOC
          FROM SFFND_WORK_LOC_DEF LOC, SFFND_USER_WORK_CENTERS U
            WHERE U.ASGND_LOCATION_ID = LOC.LOCATION_ID
         AND U.USERID = :@USERID)
order by decode(obsolete_record_flag,''Y'',2,1),data_value,upper(cert),upper(cert_type),description,updt_userid';
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

