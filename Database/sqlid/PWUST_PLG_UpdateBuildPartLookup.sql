
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_PLG_UPDATEBUILDPARTLOOKUP.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_PLG_UPDATEBUILDPARTLOOKUP';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_PLG_UpdateBuildPartLookup';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFPL';
v_description sfcore_sql_lib.description%type  :='Defect 812, defect 964/1034.';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT
NEW_ITEM_NO,
NEW_ITEM_REV,
NEW_ITEM_TITLE,
NEW_ITEM_TYPE,
NEW_ITEM_SUBTYPE,
ENG_PART_NO,
ENG_PART_CHG,
INSP_PLAN_ID,
INSP_PLAN_NO,
ORDER_UOM,
SECURITY_GROUP
FROM
(select /*+ index(sfpl_item_desc_master_all SFPL_ITEM_DESC_INDX002) */ DISTINCT part_no as New_Item_No,part_chg as New_Item_Rev, part_title as New_Item_Title, ITEM_TYPE AS NEW_ITEM_TYPE, ITEM_SUBTYPE AS NEW_ITEM_SUBTYPE, PARENT_ENG_PART_NO as ENG_PART_NO ,PARENT_ENG_PART_CHG as ENG_PART_CHG,
        A.MFG_INSP_PLAN_ID AS INSP_PLAN_ID, B.INSP_PLAN_NO AS INSP_PLAN_NO,  C.STOCK_UOM as ORDER_UOM, SECURITY_GROUP
from  sfpl_item_desc_master_all A LEFT OUTER JOIN  SFSQA_INSP_PLAN_DESC B ON A.MFG_INSP_PLAN_ID = B.INSP_PLAN_ID,
      sfpl_item_program_details C
WHERE  obsolete_record_flag = ''N''
AND    a.item_type = ''PART''
AND    COALESCE(A.ITEM_SUBTYPE,''X'') != ''STDOPER''
AND    A.ITEM_ID = C.ITEM_ID
AND A.ITEM_ID NOT IN(SELECT V.ITEM_ID FROM SFPL_PART_V V WHERE V.PLAN_ID = :PLAN_ID
                                         AND V.PLAN_VERSION = :PLAN_VERSION
                                         AND V.PLAN_REVISION = :PLAN_REVISION
                                         AND V.PLAN_ALTERATIONS = :PLAN_ALTERATIONS)
AND A.ITEM_ID NOT IN (SELECT I.ITEM_ID FROM SFPL_TOOL_V V, SFPL_ITEM_DESC_ALL I
                       WHERE V.PLAN_ID = :PLAN_ID
                         AND V.PLAN_VERSION = :PLAN_VERSION
                         AND V.PLAN_REVISION = :PLAN_REVISION
                         AND V.PLAN_ALTERATIONS = :PLAN_ALTERATIONS
                         AND V.TOOL_NO = I.PART_NO
                         AND V.TOOL_CHG = I.PART_CHG)
AND    C.PROGRAM = ''ANY''
and
    ((0 = (select count(*) from sfpl_item_desc_sec_grp where part_no = a.part_no))
    or exists
     (select 1 from utasgi_user_sec_grp_xref b, sfpl_item_desc_sec_grp d
      where b.hr_loc = :PLND_WORK_LOC
      and d.part_no = a.part_no
      and d.security_group = b.security_group))
) X
WHERE 1=1 /*+ @filter */
ORDER BY NEW_ITEM_NO';
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

