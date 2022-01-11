
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_ROM_REPORT_DETAILS.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_ROM_REPORT_DETAILS';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_ROM_REPORT_DETAILS';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFRPT';
v_description sfcore_sql_lib.description%type  :='SMRO_RPT_306';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT DISTINCT T.DAT_COL_ID AS DC_ID,
                ''ROM'' AS DAT_COL_TYPE,
                T.DAT_COL_TITLE AS DAT_COL_TITLE,
                X.DCVALUE AS MEASUREMENT,
                X.COMMENTS AS MEASUREMENT_REMARK,
                T.UCF_STEP_DC_VCH255_1 AS ENGINEERING_REMARKS
          FROM PWUST_SFPL_DAT_COL_V      T,
               SFPL_STEP_DAT_COL         S,
               SFWID_SERIAL_OPER_DAT_COL X
         WHERE T.PLAN_ID = S.PLAN_ID
           AND T.PLAN_REVISION = :PLAN_REV
           AND T.PLAN_ID = :PLAN_ID
           AND T.STEP_UPDT_NO = S.STEP_UPDT_NO
           AND T.OPER_KEY = S.OPER_KEY
           AND X.OPER_KEY = S.OPER_KEY
           AND T.STEP_KEY = S.STEP_KEY
           AND T.DAT_COL_ID = S.DAT_COL_ID
           AND X.DAT_COL_ID = S.DAT_COL_ID
           AND X.ORDER_ID = :ORDER_ID
           AND X.SERIAL_ID =:SERIAL_ID
	   AND T.UCF_STEP_DC_FLAG1 = ''Y''';
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

