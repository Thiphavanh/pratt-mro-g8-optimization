
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_MFI_WID_PARTNOLOOKUPIM.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_MFI_WID_PARTNOLOOKUPIM';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_MFI_WID_PartNoLookupIM';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFWID';
v_description sfcore_sql_lib.description%type  :='Part No lookup for WID/Clone of MFI_WID_PartNoLookupIM/Defect 365';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT PART_NO AS COMP_PART_NO,
       PART_CHG AS COMP_PART_CHG,
       PART_TITLE,
       COMP_SERIAL_FLAG AS SERIAL_FLAG,
       COMP_LOT_FLAG AS LOT_FLAG,
       EXP_FLAG,
       SPOOL_FLAG,
       UID_ITEM_FLAG,
       a.UID_ENTRY_NAME,
       STOCK_UOM AS UOM,
       ITEM_TYPE,
       ITEM_SUBTYPE,
       STANDARD_PART_FLAG,
       UTILIZATION_RULE,
       TRACKABLE_FLAG,
       PROGRAM,
       a.WORK_LOC,
       a.SECURITY_GROUP
FROM  SFPL_ITEM_DESC a,
      sffnd_user_work_centers b, sffnd_work_loc_def c, utasgi_user_sec_grp_xref d 
WHERE a.OBSOLETE_RECORD_FLAG = ''N''
and b.userid = :@USERID
and c.location_id = b.asgnd_location_id
and d.hr_loc = c.work_loc
and a.security_group = d.security_group
AND   ITEM_TYPE = ''PART''
AND NVL(ITEM_SUBTYPE, ''X'') != ''STDOPER''
AND ITEM_ID<>(SELECT O.ITEM_ID FROM SFWID_ORDER_DESC O WHERE O.ORDER_ID = :ORDER_ID)
AND
(
   (PROGRAM=''ANY'' AND a.WORK_LOC = ''ANY'')
   OR
   (PROGRAM=''ANY'' AND a.WORK_LOC =
      (CASE WHEN :ASGND_WORK_LOC = ''N/A'' THEN ''ANY'' WHEN :ASGND_WORK_LOC IS NULL THEN ''ANY'' ELSE :ASGND_WORK_LOC END))
   OR
   (a.WORK_LOC = ''ANY'' AND PROGRAM =
      (CASE WHEN :PLND_PROGRAM = ''N/A'' THEN ''ANY'' WHEN :PLND_PROGRAM IS NULL THEN ''ANY'' ELSE :PLND_PROGRAM END))
   OR
   (PROGRAM =
     (CASE WHEN :PLND_PROGRAM = ''N/A'' THEN ''ANY'' WHEN :PLND_PROGRAM IS NULL THEN ''ANY'' ELSE :PLND_PROGRAM END)
    AND a.WORK_LOC =
    (CASE WHEN :ASGND_WORK_LOC = ''N/A'' THEN ''ANY'' WHEN :ASGND_WORK_LOC IS NULL THEN ''ANY'' ELSE :ASGND_WORK_LOC END))
)  /*+ @Filter */';
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

