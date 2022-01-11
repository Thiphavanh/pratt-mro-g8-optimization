
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_MFI_FND_COMPAREORDERDIFFINFO.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_MFI_FND_COMPAREORDERDIFFINFO';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_MFI_FND_CompareOrderDiffInfo';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Defect 880';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT
  null as part_no,
  null as part_chg,
  null as item_type,
  null as item_subtype,
  null as plan_title,
  null as plan_no,
  null as plan_status,
  null as plan_type,
  null as TO_ITEM_NO,
  null as TO_ITEM_REV,
  null as TO_ITEM_TYPE,
  null as TO_ITEM_SUBTYPE,
  null as TO_PLAN_TITLE,
  null as TO_PLAN_NO,
  null as TO_PLAN_STATUS,
  null as TO_PLAN_TYPE,
  O1.PW_ORDER_NO AS ORDER_NO,
  O2.PW_ORDER_NO AS TARGET_ORDER_NO,
  O3.security_group as security_group
FROM
  PWUST_SFWID_ORDER_DESC O1, PWUST_SFWID_ORDER_DESC O2, SFWID_ORDER_DESC O3
WHERE
  O1.ORDER_ID = :ORDER_ID AND
  O2.ORDER_ID = :TARGET_ORDER_ID AND
  O1.ORDER_ID = O3.ORDER_ID';
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

