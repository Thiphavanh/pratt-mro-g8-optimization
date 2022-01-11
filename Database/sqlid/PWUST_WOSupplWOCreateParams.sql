
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_WOSUPPLWOCREATEPARAMS.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='SFCORE_SQLLIBS';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_WOSUPPLWOCREATEPARAMS';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_WOSupplWOCreateParams';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='SMRO_WOE_2014';
v_sql_text sfcore_sql_lib.sql_text%type  :='select ''LOT_NO=''           || COALESCE(sffnd_value_wrapper(LOT_NO), '''') ||
       '',WORK_LOC=''        || sffnd_value_wrapper(work_loc) ||
       '',PROGRAM=''         || sffnd_value_wrapper(program) || 
       '',ITEM_TYPE=''       || sffnd_value_wrapper(item_type) || 
       '',ITEM_SUBTYPE=''    || sffnd_value_wrapper(item_subtype) || 
       '',PART_NO=''         ||sffnd_value_wrapper(part_no) || 
       '',PART_CHG=''        ||sffnd_value_wrapper(part_chg) || 
       '',SERIAL_FLAG=''     || serial_flag ||
       '',LOT_FLAG=''        || lot_flag ||
       '',PARENT_ORDER_NO='' ||sffnd_value_wrapper(order_no) ||
       '',SUPPL_OPER_NO=''  ||      
       '',ORDER_TYPE=''      ||sffnd_value_wrapper(case
                             when exists (select doc_sub_type
                                     from SFFND_DOC_TYPE_DEF a
                                    where a.doc_sub_type = ''Supplemental''
                                      and a.doc_type = ''Process Order'') 
                             then ''Supplemental''
                             else ''SUPPLEMENTAL''                             
                          END) as PARAMS,
       decode(lot_flag, ''Y'', ''N/A'', ''LOT_NO'') as HIDE, LOT_NO,
       a.SECURITY_GROUP
  from sfwid_order_desc a
  left outer join sfwid_lot_desc b on a.order_id = b.order_id   
  left outer join sffnd_work_loc_def loc on a.asgnd_location_id = loc.location_id  
 where a.order_id = :order_id';
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

