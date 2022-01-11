
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_SFOR_SFWIDORDERSUBJECT.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_SFOR_SFWIDORDERSUBJECT';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_SFOR_SfwidOrderSubject';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='SFOR;Defect426';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT T.SUBJECT_NO,
       T.INCLUDED_FLAG,
       T.STANDARD_FLAG,
       T.SUBJECT_STATUS,
       T.SUBJECT_REV,
       T.AUTHORITY,
       T.TITLE,
       NOTES,
       T.DISC_ID,
       T.DISC_LINE_NO,
       T2.SUPERIOR_NETWORK_ACT,
       T3.SUB_NETWORK_ACT,
       T1.SECURITY_GROUP
  FROM SFOR_SFWID_ORDER_SUBJECT       T,
       SFWID_ORDER_DESC               T1,
       PWUST_PL_SUBJ_NETWORK_ACTIVITY T2,
       PWUST_PL_SUBJ_SUBNET_ACTIVITY  T3
 WHERE T.ORDER_ID = T1.ORDER_ID
   AND T1.PLAN_ID = T2.PLAN_ID
   AND T1.PLAN_UPDT_NO = T2.PLAN_UPDT_NO
   AND T.SUBJECT_NO = T2.SUBJECT_NO
   AND T.SUBJECT_REV = T2.SUBJECT_REV
   AND T1.PROGRAM = T2.ENGINE_TYPE
   AND T2.PLAN_ID = T3.PLAN_ID
   AND T2.PLAN_UPDT_NO = T3.PLAN_UPDT_NO
   AND T2.SUBJECT_NO = T3.SUBJECT_NO
   AND T2.SUBJECT_REV = T3.SUBJECT_REV
   AND T2.ENGINE_TYPE = T3.ENGINE_TYPE
   AND T2.SUPERIOR_NETWORK_ACT = T3.SUPERIOR_NETWORK_ACT
   AND T.ORDER_ID = :order_id
   AND T.OBSOLETE_RECORD_FLAG = ''N''';
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

