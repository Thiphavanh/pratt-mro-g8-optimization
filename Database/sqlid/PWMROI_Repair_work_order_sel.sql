
set define off
prompt ---------------------------------------;
prompt Executing ... PWMROI_REPAIR_WORK_ORDER_SEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWMROI_REPAIR_WORK_ORDER_SEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWMROI_Repair_work_order_sel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFWID';
v_description sfcore_sql_lib.description%type  :='select repair work orders, defect 1874;Defect 1882';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT A.ORDER_ID,
       ORDER_TYPE,
       ORDER_NO,
       ORDER_QTY,
       ORDER_STATUS,
       ALT_COUNT,
       PART_NO,
       PART_CHG,
       WORK_LOC,
       SFMFG.PWUST_GET_CUSTOMER_NUMBER(A.ORDER_ID) as ORDER_CUST_ID,
       SECURITY_GROUP
  FROM SFMFG.SFWID_ORDER_DESC A
  LEFT OUTER JOIN SFMFG.SFFND_WORK_LOC_DEF LOC
    ON A.ASGND_LOCATION_ID = LOC.LOCATION_ID
 WHERE ORDER_STATUS IN (''IN QUEUE'', ''ACTIVE'', ''CLOSE'')
   AND A.ORDER_ID NOT IN
       (SELECT DISTINCT ORDER_ID
          FROM SFMFG.SFWID_HOLDS H
         WHERE UPPER(H.STOP_TYPE) IN (''ORDER STOP'', ''OPER STOP'')
           AND UPPER(H.HOLD_STATUS) = ''OPEN'')
   AND UPPER(A.ORDER_TYPE) = ''REPAIR''
 ORDER BY ORDER_NO, ORDER_STATUS';
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

