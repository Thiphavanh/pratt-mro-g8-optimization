
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_RPTTRAVELERORDEROPERATIONS.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_RPTTRAVELERORDEROPERATIONS';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_RptTravelerOrderOperations';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFWID';
v_description sfcore_sql_lib.description%type  :='SMRO_WOE_310; 1566; 1575; 1574; 1371';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT distinct OP_DESC.order_id,
       OP_DESC.oper_no as OperNo,
       OP_DESC.title as OperTitle,
       CENTER.WORK_CENTER as OperWorkCenter,
       CENTER.center_title as OperDescription,
       ORDER_DESC.PW_ORDER_NO as ORDER_NO,
       (ORDER_DESC.PW_ORDER_NO || '','' || OP_DESC.oper_no) as barcode,
       '''' as security_group 
 from SFMFG.SFWID_OPER_DESC OP_DESC
 JOIN SFMFG.SFOR_SFWID_OPER_SUBJECT OP_SUBJECT
      ON OP_DESC.ORDER_ID = OP_SUBJECT.ORDER_ID
      AND OP_DESC.OPER_KEY = OP_SUBJECT.OPER_KEY
 LEFT OUTER JOIN SFMFG.SFFND_WORK_CENTER_DEF CENTER
      ON OP_DESC.ASGND_CENTER_ID = CENTER.CENTER_ID
      AND OP_DESC.ASGND_LOCATION_ID = CENTER.LOCATION_ID
      AND OP_DESC.ASGND_DEPARTMENT_ID = CENTER.DEPARTMENT_ID
 JOIN SFMFG.PWUST_SFWID_ORDER_DESC ORDER_DESC
      ON OP_DESC.ORDER_ID = ORDER_DESC.ORDER_ID
 WHERE OP_DESC.STEP_KEY = -1
           AND OP_DESC.INCLUDED = ''INCLUDED''
           AND OP_DESC.ORDER_ID = :Order_Id
UNION
SELECT distinct OP_DESC.order_id,
       OP_DESC.oper_no as OperNo,
       OP_DESC.title as OperTitle,
       CENTER.WORK_CENTER as OperWorkCenter,
       CENTER.center_title as OperDescription,
       ORDER_DESC.PW_ORDER_NO as ORDER_NO,
       (ORDER_DESC.PW_ORDER_NO || '','' || OP_DESC.oper_no) as barcode,
       ord_desc.security_group
 from SFMFG.SFWID_OPER_DESC OP_DESC
 left outer join SFWID_ORDER_DESC ord_desc
      on ord_Desc.order_id = op_Desc.order_id
 left outer JOIN SFMFG.SFOR_SFWID_OPER_SUBJECT OP_SUBJECT
      ON OP_DESC.ORDER_ID = OP_SUBJECT.ORDER_ID
      AND OP_DESC.OPER_KEY = OP_SUBJECT.OPER_KEY
 LEFT OUTER JOIN SFMFG.SFFND_WORK_CENTER_DEF CENTER
      ON OP_DESC.ASGND_CENTER_ID = CENTER.CENTER_ID
      AND OP_DESC.ASGND_LOCATION_ID = CENTER.LOCATION_ID
      AND OP_DESC.ASGND_DEPARTMENT_ID = CENTER.DEPARTMENT_ID
left outer JOIN SFMFG.PWUST_SFWID_ORDER_DESC ORDER_DESC
      ON OP_DESC.ORDER_ID = ORDER_DESC.ORDER_ID
 WHERE OP_DESC.STEP_KEY = -1
      AND OP_DESC.ORDER_ID = :Order_Id
      and ord_Desc.Order_Type = ''Supplemental''
 order by operNo asc';
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

