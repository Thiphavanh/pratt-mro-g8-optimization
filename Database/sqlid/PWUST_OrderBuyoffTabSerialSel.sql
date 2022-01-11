
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_ORDERBUYOFFTABSERIALSEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_ORDERBUYOFFTABSERIALSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_OrderBuyoffTabSerialSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFWID';
v_description sfcore_sql_lib.description%type  :='defect 44;Defect 695;Defect 422;Defect497; Defect 763;Defect 712;Defect879;1683;1796';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT BUYOFF_ID,
       OPER_NO,
       STEP_NO,
       BUYOFF_TYPE,
       BUYOFF_CERT,
       OPTIONAL_FLAG,
       CROSS_ORDER_FLAG,
       UPDT_USERID,
       TIME_STAMP,
       USER_NAME,
       IMAGE,
       SLIDE_ID,
       SLIDE_EMBEDDED_REF_ID,
       BUYOFF_TITLE,
       BUYOFF_STATUS,
       OPER_KEY,
       STEP_KEY,
       COMMENTS
FROM EXECQUERY com.pw.solumina.sfwid.application.BuyoffPW.getSerialBuyoffTabList(:ORDER_ID,:ORDER_TYPE,:@WhereClause)';
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

