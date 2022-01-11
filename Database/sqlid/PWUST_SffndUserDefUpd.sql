
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_SFFNDUSERDEFUPD.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_SFFNDUSERDEFUPD';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_SffndUserDefUpd';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFFND';
v_description sfcore_sql_lib.description%type  :='Clone of MFI_SFFND_User_Def_Upd; SMRO_USR_201';
v_sql_text sfcore_sql_lib.sql_text%type  :='EXEC com.pw.solumina.sffnd.application.UserPW.updateUser(
  :USERID,
  :USER_DEFINITION,
  :LAST_NAME,
  :FIRST_NAME,
  :MIDDLE_NAME,
  :FULL_NAME,
  :NAME_SUFFIX,
  :LTA_SEND_FLAG,
  :WORK_LOC,
  :WORK_DEPT,
  :WORK_CENTER,
  :SHIFT,
  :PAYROLL_ID,
  :USER_CLASS,
  :EMAIL_ADDRESS,
  :USER_TYPE,
  :INCLUDE_ALL_SUPPLIERS,
  :LABOR_TYPE,
  :LAST_EYE_EXAM_DATE,
  :OBSOLETE_RECORD_FLAG,
  :MI_USER_FLAG
  :UCF_USER_VCH1,
  :UCF_USER_VCH2,
  :UCF_USER_VCH3,
  :UCF_USER_VCH4,
  :UCF_USER_VCH5,
  :UCF_USER_VCH6,
  :UCF_USER_VCH7,
  :UCF_USER_VCH8,
  :UCF_USER_VCH9,
  :UCF_USER_VCH10,
  :UCF_USER_VCH11,
  :UCF_USER_VCH12,
  :UCF_USER_VCH13,
  :UCF_USER_VCH14,
  :UCF_USER_VCH15,
  :UCF_USER_NUM1,
  :UCF_USER_NUM2,
  :UCF_USER_NUM3,
  :UCF_USER_NUM4,
  :UCF_USER_NUM5,
  :UCF_USER_DATE1,
  :UCF_USER_DATE2,
  :UCF_USER_DATE3,
  :UCF_USER_DATE4,
  :UCF_USER_DATE5,
  :UCF_USER_FLAG1,
  :UCF_USER_FLAG2,
  :UCF_USER_FLAG3,
  :UCF_USER_FLAG4,
  :UCF_USER_FLAG5,
  :UCF_USER_VCH255_1,
  :UCF_USER_VCH255_2,
  :UCF_USER_VCH255_3,
  :UCF_USER_VCH4000_1,
  :UCF_USER_VCH4000_2)';
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

