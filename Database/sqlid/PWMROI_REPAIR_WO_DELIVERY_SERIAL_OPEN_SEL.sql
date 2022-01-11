
set define off
prompt ---------------------------------------;
prompt Executing ... PWMROI_REPAIR_WO_DELIVERY_SERIAL_OPEN_SEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWMROI_REPAIR_WO_DELIVERY_SERIAL_OPEN_SEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWMROI_REPAIR_WO_DELIVERY_SERIAL_OPEN_SEL';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFWID';
v_description sfcore_sql_lib.description%type  :='select open serials of selected workorder;Defect1611';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT s.serial_no,
       s.serial_status,
	   s.ucf_serial_vch10,
	   s.serial_id || '','' || s.serial_no AS serial,
	   d.engine_manual,
	   null AS security_group,
	   '''' AS intdt,
	   '''' AS int_respdt,
	   '''' AS int_response
  FROM sfwid_serial_desc s left join pwmroi_serial_delivery d
    ON s.order_id = d.order_id and s.serial_id = d.serial_id
 WHERE ucf_serial_vch10 IS NULL
   AND s.order_id = :ORDER_ID
   AND s.serial_status NOT IN (''SCRAP'')
   and s.serial_no not like ''%-SPLIT%'' 
   AND NOT EXISTS (SELECT 1
                     FROM sfwid_order_desc o, sfwid_lot_desc l, sfwid_as_worked_item w
                    WHERE s.order_id = o.order_id
                      AND o.order_id = l.order_id
                      AND s.lot_id = l.lot_id
                      AND o.part_no = w.part_no
                      AND l.lot_no = w.lot_no
                      AND s.serial_no = w.serial_no
                      AND w.scrap_flag = ''Y'')
ORDER BY S.SERIAL_NO';
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

