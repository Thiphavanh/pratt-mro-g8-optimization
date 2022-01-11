
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_MFI_WID_BUYOFFPECENTCOMPLETESEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_MFI_WID_BUYOFFPECENTCOMPLETESEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_MFI_WID_BuyoffPecentCompleteSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFWID';
v_description sfcore_sql_lib.description%type  :='SMRO_WOE_202;defect 729;Defect 947;SMRO_WOE_302;Defect1696;1965';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT (CAST(NVL(A.PERCENT_COMPLETE, 0) AS FLOAT) / 100) AS PERCENT_COMPLETE,
       d1.pw_order_no,
       D.SECURITY_GROUP
  FROM SFWID_SERIAL_OPER_BUYOFF A,
       SFWID_ORDER_DESC         D,
       pwust_sfwid_order_desc   D1
 WHERE A.ORDER_ID = D.ORDER_ID
 and   d.order_id = d1.order_id
   AND A.ORDER_ID = :ORDER_ID
   AND A.OPER_KEY = :OPER_KEY
   AND A.STEP_KEY = :STEP_KEY
   AND A.BUYOFF_ID = :BUYOFF_ID
   AND D.OPERATION_OVERLAP_FLAG = ''N''';
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

