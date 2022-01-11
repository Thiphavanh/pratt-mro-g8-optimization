
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_MFI_WID_WORKORDSECURITYGROUPSEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_MFI_WID_WORKORDSECURITYGROUPSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='pwust_MFI_WID_WorkOrdSecurityGroupSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='SMRO_EXPT_203 Defect 690';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT  A.SECURITY_GROUP,
        B.SECURITY_GROUP_TYPE,
        B.ISSUING_AGENCY,
        A.OBSOLETE_RECORD_FLAG,
        A.UPDT_USERID,
        A.TIME_STAMP,
        B.SECURITY_GROUP_DESC,
        B.EXPIRATION_DATE
FROM   SFWID_ORDER_DESC_SEC_GRP A, SFFND_SECURITY_GROUP_DEF B, SFWID_ORDER_DESC A1, PWUST_SFWID_ORDER_DESC A2
WHERE  A1.ORDER_ID = A2.ORDER_ID
AND A.ORDER_NO = A1.ORDER_NO
AND A.SECURITY_GROUP = B.SECURITY_GROUP 
AND  A2.PW_ORDER_NO = :ORDER_NO
ORDER BY A.SECURITY_GROUP';
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

