
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_SALES_ORDER_SEL_UDV_1.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_SALES_ORDER_SEL_UDV_1';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_SALES_ORDER_SEL_UDV_1';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Defect 326/Defect 692/Defect 779/Defect 809/Defect 955/Defect 704';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT  t.SALES_ORDER,
        ORDER_NO,
        PLAN_TITLE,
        ALT_NO,
        ORDER_STATUS,
        t.WORK_SCOPE,
        WORK_SCOPE_DESC,
        SUPERIOR_NETWORK_ACTIVITY,
        SUP_NET_DESC,
        SUB_NETWORK_ACTIVITY,
        SUB_NET_DESC,
        ALT_STATUS ,
        ORDER_HOLD_STATUS,
        ASGND_WORK_LOC,
        CREATE_DATE,
        RELEASE_DATE,
        CREATE_BY,
        ORDER_TYPE,
        PLAN_NO,
        PART_NO,
        PARENT_ORDER_ID,
        ALTERED_BY,
        REASON_FOR_CHANGE,
        SECURITY_GROUP,
        RELEASED_BY,
        t.ORDER_ID,
        UCF_PLAN_VCH3,
        ucf_order_vch11,
        ucf_order_vch12
FROM PWUST_OVERHAUL_WO_DISP_TAB_V T
WHERE NVL(T.SALES_ORDER,''NULL'') = :SALES_ORDER
and order_status in (''IN QUEUE'', ''ACTIVE'')
and exists
(select 1
 from SFWID_ORDER_DESC_SEC_GRP t3
 WHERE T3.ORDER_NO = T.OOB_ORDER_NO
 AND T3.SECURITY_GROUP IN (select TT.SECURITY_GROUP from sffnd_user_sec_grp TT where tt.userid = :@USERID ))
 ORDER  BY T.SALES_ORDER';
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

