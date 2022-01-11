
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_WID_ORDERUNITINFOSELECT.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_WID_ORDERUNITINFOSELECT';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_WID_OrderUnitInfoSelect';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='SMRO_RPT_201;SMRO_WOE_304;1711';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT SERIAL_NO,
       LOT_NO,
       UCF_ORDER_VCH12,
       C.SECURITY_GROUP,
       ORDER_TYPE,
       ORDER_STATUS
  FROM SFWID_SERIAL_DESC A,
       SFWID_LOT_DESC B,
       SFWID_ORDER_DESC C
 WHERE A.ORDER_ID = B.ORDER_ID
   AND A.ORDER_ID = C.ORDER_ID
   AND A.LOT_ID = B.LOT_ID
   AND A.ORDER_ID = :ORDER_ID
   AND A.SERIAL_STATUS !=''STOP''
   AND A.SERIAL_NO = CASE WHEN (C.SERIAL_FLAG = ''N'' OR :FIRST_AVAILABLE_UNIT IS NULL) THEN A.SERIAL_NO ELSE :FIRST_AVAILABLE_UNIT END';
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

