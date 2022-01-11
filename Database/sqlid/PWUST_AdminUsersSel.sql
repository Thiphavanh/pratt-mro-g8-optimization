
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_ADMINUSERSSEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_ADMINUSERSSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_AdminUsersSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Clone of AdminUsersSel; SMRO_USR_201';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT USERID,
       LAST_NAME,
       FIRST_NAME,
       MIDDLE_NAME,
       FULL_NAME,
       NAME_SUFFIX,
       PAYROLL_ID,
       WORK_LOC,
       LOCATION_ID,
       LOCATION_ID AS OLD_LOCATION_ID,
       WORK_DEPT,
       WORK_CENTER,
       SHIFT,
       USER_CLASS,
       MI_USER_FLAG,
       OBSOLETE_RECORD_FLAG,
       IN_DATABASE_FLAG,
       UCF_USER_VCH1,
       UCF_USER_VCH2,
       UCF_USER_VCH3,
       UCF_USER_VCH4,
       ACCOUNT_TYPE,
       EMAIL_ADDRESS,
       USER_TYPE,
       LABOR_TYPE,
       ACCOUNT_TYPE,
       ACCOUNT_STATUS,
       USER_DEFINITION,
       UCF_USER_VCH5,
       UCF_USER_VCH6,
       UCF_USER_VCH7,
       UCF_USER_VCH8,
       UCF_USER_VCH9,
       UCF_USER_VCH10,
       UCF_USER_VCH11,
       UCF_USER_VCH12,
       UCF_USER_VCH13,
       UCF_USER_VCH14,
       UCF_USER_VCH15,
       UCF_USER_NUM1,
       UCF_USER_NUM2,
       UCF_USER_NUM3,
       UCF_USER_NUM4,
       UCF_USER_NUM5,
       UCF_USER_DATE1,
       UCF_USER_DATE2,
       UCF_USER_DATE3,
       UCF_USER_DATE4,
       UCF_USER_DATE5,
       UCF_USER_FLAG1,
       UCF_USER_FLAG2,
       UCF_USER_FLAG3,
       UCF_USER_FLAG4,
       UCF_USER_FLAG5,
       UCF_USER_VCH255_1,
       UCF_USER_VCH255_2,
       UCF_USER_VCH255_3,
       UCF_USER_VCH4000_1,
       UCF_USER_VCH4000_2,
       USER_LOGIN_ATTEMPT,
       CASE
         WHEN USER_LOGIN_ATTEMPT >=
              (SELECT PARAMETER_VALUE
                 FROM SFFND_GLOBAL_CONFIGURATION
                WHERE CONFIG_MODULE_NAME = ''FOUNDATION''
                  AND PARAMETER_NAME = ''USER_LOGIN_FAILED_LIMIT'') THEN
          ''Y''
         ELSE
          ''N''
       END IS_USER_LOCKED,
       INCLUDE_ALL_SUPPLIERS,
       LAST_EYE_EXAM_DATE,
       TIME_STAMP,
       UPDT_USERID
  FROM PWUST_SFFND_USER_USER_V
 WHERE USERID NOT IN (''SFMFG'', ''ANONYMOUS'') /*+ @filter */
 ORDER BY DECODE(OBSOLETE_RECORD_FLAG, ''Y'', 2, 1), UPPER(USERID)';
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

