
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_FND_STDTEXTDISPATCHSEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_FND_STDTEXTDISPATCHSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_FND_StdTextDispatchSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFFND';
v_description sfcore_sql_lib.description%type  :='Defect 50';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT
       OBJECT_ID,
       OBJECT_ID AS STDTEXTID,
       OBJECT_TAG,
       OBJECT_DESC,
       OBJECT_REV,
       BLOCKTYPE,
       BLOCKTYPE AS BLOCK,
       PWP_ID,
       NULL AS TO_PWP_ID,
       PWP_ID AS RELEASE_PACKAGE,
       STATUS,
       UPDT_USERID,
       TIME_STAMP,
       DOC_TYPE,
       STD_TEXT_TYPE,
       WORK_FLOW,
       OBJECT_TYPE,
       ''DISPATCH'' AS CALLED_FROM,
       SECURITY_GROUP,COMMODITY_JURISDICTION,COMMODITY_CLASSIFICATION
FROM
(
  SELECT OBJECT_ID,
         OBJECT_TAG,
         COALESCE(OBJECT_DESC, OBJECT_TAG) AS OBJECT_DESC,
         OBJECT_REV,
         BLOCKTYPE,
         PWP_ID,
         a.STATUS,
         a.UPDT_USERID,
         a.TIME_STAMP,
         DOC_TYPE,
         DOC_SUB_TYPE AS STD_TEXT_TYPE,
         WORK_FLOW,
         OBJECT_TYPE,
         a.SECURITY_GROUP,COMMODITY_JURISDICTION,COMMODITY_CLASSIFICATION
    FROM SFCORE_MM_OBJECT A,
         utasgi_user_sec_grp_xref b,
         sffnd_user_work_centers c,
         sffnd_work_loc_def d
   WHERE OBJECT_TYPE = ''STDTEXT''
   and c.userid = :@USERID 
   and c.asgnd_location_id = d.location_id
   and d.work_loc = b.hr_loc
   and b.security_group = a.security_group
         /*+ @Filter() */
) X';
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

