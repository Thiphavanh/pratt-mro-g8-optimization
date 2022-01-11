
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_RPTORDEROPERINFOSEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_RPTORDEROPERINFOSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_RptOrderOperInfoSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='SMRO_RPT_201; Defect 784;Defect 907;1113, defect 1832, defect 1852';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT
  a.OPER_NO,
  a.OPER_KEY,
  a.STEP_KEY,
  a.OPER_UPDT_NO,
  a.OPER_TYPE,
  a.AUTO_START_FLAG,
  a.AUTO_COMPLETE_FLAG,
  a.OCCUR_RATE,
  a.PLND_MACHINE_NO,
  a.OPER_OPT_FLAG,
  CASE WHEN a.SUPPLIER_CODE IS NULL THEN NULL
       ELSE (SELECT LOC2.WORK_LOC FROM SFFND_WORK_LOC_DEF LOC2 WHERE LOC2.LOCATION_ID = a.SUPPLIER_CODE) END AS SUPPLIER_CODE,
  a.OSP_DAYS,
  a.OSP_COST_PER_UNIT,
  TITLE,
  DEPT.WORK_DEPT AS ASGND_WORK_DEPT,
  CENTER.WORK_CENTER AS ASGND_WORK_CENTER,
  LOC.WORK_LOC AS ASGND_WORK_LOC,
  a.TEST_TYPE,
  CASE WHEN A.STDOPER_OBJECT_ID IS NOT NULL THEN ''Y'' ELSE ''N'' END AS STD_OPER_FLAG,
  a.ORDER_ID
FROM SFWID_OPER_DESC A  LEFT OUTER JOIN SFFND_WORK_LOC_DEF LOC ON A.ASGND_LOCATION_ID = LOC.LOCATION_ID
                        LEFT OUTER JOIN SFFND_WORK_DEPT_DEF DEPT ON A.ASGND_DEPARTMENT_ID = DEPT.DEPARTMENT_ID AND A.ASGND_LOCATION_ID =  DEPT.LOCATION_ID
                        LEFT OUTER JOIN SFFND_WORK_CENTER_DEF CENTER ON A.ASGND_CENTER_ID= CENTER.CENTER_ID AND A.ASGND_LOCATION_ID = CENTER.LOCATION_ID AND A.ASGND_DEPARTMENT_ID = CENTER.DEPARTMENT_ID
WHERE
a.ORDER_ID =:ORDER_ID AND
STEP_KEY = ''-1''
AND nvl(INCLUDED,''INCLUDED'') = ''INCLUDED''
ORDER BY LPAD(OPER_NO, 10, ''0'')';
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

