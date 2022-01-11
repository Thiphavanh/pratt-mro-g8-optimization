
set define off
prompt ---------------------------------------;
prompt Executing ... PWUSD_PART_STATUS_FIND.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUSD_PART_STATUS_FIND';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUSD_Part_status_Find';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Defect 1399';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT DOC_SUB_TYPE,
       WORK_LOC,
       V.PART_NO,
       PART_CHG,
       EBOM,
       MBOM,
       PROCESS_PLAN,
       INSP_DEF,
       LOCATION_ID,
       V.SECURITY_GROUP,
       PART_TITLE,
       ITEM_STATUS,
       ITEM_TYPE,
       ITEM_SUBTYPE,
       ITEM_ID,
       MBOM_ID,
       EBOM_ID,
       PLAN_ID,
       PLAN_REV,
       MAX_INSP_DEF_REV,
       INSP_DEF_STATUS
  FROM SFPL_PART_STATUS_V V
  LEFT JOIN SFPL_ITEM_DESC_SEC_GRP A
    ON V.PART_NO = A.PART_NO
 WHERE 1 = 1 /*+ @Filter */
   AND (V.SECURITY_GROUP in
       (SELECT SECURITY_GROUP
           FROM SFFND_USER_SEC_GRP
          WHERE USERID = :@USERID) OR
       (V.SECURITY_GROUP IS NULL AND
       A.SECURITY_GROUP in
       (SELECT SECURITY_GROUP
            FROM SFFND_USER_SEC_GRP
           WHERE USERID = :@USERID)))';
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

