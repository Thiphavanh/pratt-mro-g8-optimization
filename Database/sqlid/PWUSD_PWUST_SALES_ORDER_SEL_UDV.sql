
set define off
prompt ---------------------------------------;
prompt Executing ... PWUSD_PWUST_SALES_ORDER_SEL_UDV.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUSD_PWUST_SALES_ORDER_SEL_UDV';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUSD_PWUST_SALES_ORDER_SEL_UDV';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='defect 427/692/809/779/704/953/1725/1798,1950';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT  T.ORDER_NO,
        T.PLAN_TITLE,
        t.ALT_NO,
        T.ORDER_STATUS,
        T.WORK_SCOPE,
        WORK_SCOPE_DESC,
        SUPERIOR_NETWORK_ACTIVITY,
        SUP_NET_DESC,
        SUB_NETWORK_ACTIVITY,
        SUB_NET_DESC,
        T.ALT_STATUS ,
        T.ORDER_HOLD_STATUS,
        ASGND_WORK_LOC,
        T.CREATE_DATE AS CREATE_DATE,
        RELEASE_DATE,
        T.CREATE_BY AS CREATE_BY,
        T.ORDER_TYPE,
        T.PLAN_NO,
        T.PART_NO,
        T.PARENT_ORDER_ID,
        PARENT_PW_ORDER_NO,
        ALTERED_BY,
        REASON_FOR_CHANGE,
        T.DATE_COMPLETED,
        T.SECURITY_GROUP,
        T.RELEASED_BY,
        NEW_SALES_ORDER,
        NEW_ENGINE_TYPE,
        NEW_ENGINE_MODEL,
        NEW_SAP_WORK_LOC,
        NEW_WORK_SCOPE,
        T.ORDER_ID,
        T.PLAN_REVISION,
        T.UCF_PLAN_VCH3,
        T.ucf_order_vch11,
        T.ucf_order_vch12
FROM SFMFG.PWUST_OVERHAUL_WO_DISP_TAB_V T
LEFT OUTER JOIN SFMFG.PWUST_SFWID_ORDER_DESC X ON T.PARENT_ORDER_ID = X.ORDER_ID
LEFT OUTER JOIN SFMFG.SFWID_ORDER_DESC A ON T.ORDER_ID = A.ORDER_ID
inner join SFMFG.SFWID_ORDER_ALTS_V T2 on t2.ALT_ID = a.alt_id and t2.order_id = A.order_id
WHERE NVL(T.SALES_ORDER,''NULL'') = :SALES_ORDER
and (X.PW_ORDER_TYPE = ''OVERHAUL'' or t.ORDER_TYPE = ''OVERHAUL'' or t.ORDER_TYPE = ''SUB ORDER'' OR t.ORDER_TYPE = ''SUPPLEMENTAL'')
and exists (select 1
from sfmfg.SFWID_ORDER_DESC_SEC_GRP t3
WHERE T3.ORDER_NO = T.OOB_ORDER_NO
AND T3.SECURITY_GROUP IN (select TT.SECURITY_GROUP from sfmfg.sffnd_user_sec_grp TT where tt.userid = :@USERID ))
ORDER  BY T.ORDER_NO';
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

