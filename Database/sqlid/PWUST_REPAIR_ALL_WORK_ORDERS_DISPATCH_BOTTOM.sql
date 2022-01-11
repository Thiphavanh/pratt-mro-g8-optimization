
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_REPAIR_ALL_WORK_ORDERS_DISPATCH_BOTTOM.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_REPAIR_ALL_WORK_ORDERS_DISPATCH_BOTTOM';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_REPAIR_ALL_WORK_ORDERS_DISPATCH_BOTTOM';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='SMRO_WOE_310;defect 1602,defect 1392,1940';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT DISTINCT T.ORDER_ID,
        T.PW_ORDER_NO,
        T2.ORDER_TYPE,
        T.PLAN_NO,
        T.PART_NO,
        T.PLAN_REVISION,
        T.PLAN_TITLE,
        T.ALT_COUNT,
        T.ORDER_STATUS,
        T.ALT_STATUS,
        T.ORDER_HOLD_STATUS,
        T.ALTERED_BY,
        T.REASON_FOR_CHANGE,
        T.RELEASE_DATE,
        T.SALES_ORDER,
        T2.OUTGOING_MATERIAL,
        T.SALES_ORDER_RELEASE_DATE,
        T.SALES_ORDER_RELEASED_BY,
        T.ORDER_NO,
        T.PW_SUB_ORDER_TYPE
   FROM sfmfg.PWUST_REPAIR_SUP_WO_BOTTOM_V T,
        sfmfg.PWUST_INT_SAP_SM_ORDER        T2
  WHERE NVL(T.SALES_ORDER, ''NULL'') = :SALES_ORDER
    and T2.SALES_ORDER = :SALES_ORDER
    and exists (select 1
           from sfmfg.SFWID_ORDER_DESC_SEC_GRP t1
          WHERE T1.ORDER_NO = T.ORDER_NO
            AND T1.SECURITY_GROUP IN
                (select TT.SECURITY_GROUP
                   from sfmfg.sffnd_user_sec_grp TT
                  where tt.userid = :@USERID))
  ORDER BY PW_ORDER_NO';
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

