
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_MFI_PLG_PLANPARTLOOKUP.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_MFI_PLG_PLANPARTLOOKUP';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_MFI_PLG_PlanPartLookup';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Defect 365, defect 964/1034.';
v_sql_text sfcore_sql_lib.sql_text%type  :='select /*+ index(sfpl_item_desc_master_all SFPL_ITEM_DESC_INDX002) */ DISTINCT part_no as New_Item_No,part_chg as New_Item_Rev, part_title as TITLE, ITEM_TYPE, SECURITY_GROUP, ITEM_SUBTYPE, PARENT_ENG_PART_NO as ENG_PART_NO ,PARENT_ENG_PART_CHG as ENG_PART_CHG,
        A.MFG_INSP_PLAN_ID AS INSP_PLAN_ID, B.INSP_PLAN_NO,  C.STOCK_UOM as ORDER_UOM
from  sfpl_item_desc_master_all A LEFT OUTER JOIN  SFSQA_INSP_PLAN_DESC B ON A.MFG_INSP_PLAN_ID = B.INSP_PLAN_ID,
      sfpl_item_program_details C
WHERE  obsolete_record_flag = ''N''
AND    part_flag = ''Y''
AND    COALESCE(A.ITEM_SUBTYPE,''X'') != ''STDOPER''
AND    A.ITEM_ID = C.ITEM_ID
AND    C.PROGRAM = ''ANY''
AND    C.LOCATION_ID is not null
AND    A.ITEM_SUBTYPE  = :PLAN_TYPE
and
    ((0 = (select count(*) from sfpl_item_desc_sec_grp h where h.part_no = a.part_no))
    or exists
        (select 1 from sffnd_user_work_centers f, sffnd_work_loc_def g, utasgi_user_sec_grp_xref d, sfpl_item_desc_sec_grp e
        where f.userid = :@USERID 
        and g.location_id = f.asgnd_location_id
        and d.hr_loc = g.work_loc
        and e.part_no = a.part_no 
        and e.security_group = d.security_group))
ORDER BY part_no';
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

