
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_RPTTRAVELERORDERHEADER.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_RPTTRAVELERORDERHEADER';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_RptTravelerOrderHeader';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFWID';
v_description sfcore_sql_lib.description%type  :='SMRO_WOE_310,1565, 1566;1371,1984';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT A.ORDER_ID as ORDER_ID,
       B.PW_ORDER_NO as ORDER_NO,
       (SELECT C.PART_NO FROM SFPL_PLAN_DESC C WHERE C.PLAN_ID = A.PLAN_ID AND C.PLAN_UPDT_NO = A.PLAN_UPDT_NO) AS  ITEM_NO_PROCESS_PLAN,
       B.SALES_ORDER AS SALES_ORDER,
       A.PLAN_REVISION as PLAN_REVISION,
       A.PART_NO AS PART_NO,
       A.PLAN_TITLE as ORDER_TITLE,
       B.OUTGOING_PART_NO as OUTGOING_PART_NO,
       A.ORDER_QTY AS ORDER_QTY,
       A.SECURITY_GROUP,
       A.COMMODITY_JURISDICTION,
       A.COMMODITY_CLASSIFICATION,
       (''U.S. Export Classification: '' || A.COMMODITY_JURISDICTION || '' '' || A.COMMODITY_CLASSIFICATION) as CLASSIFICATION_DESCRIPTION,
       B.SAP_LOT_CODE AS LOT_CODE
  FROM SFMFG.SFWID_ORDER_DESC A, SFMFG.PWUST_SFWID_ORDER_DESC B
 WHERE B.PW_ORDER_NO = :ORDER_NO_LIST
       AND A.ORDER_ID = B.ORDER_ID';
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

