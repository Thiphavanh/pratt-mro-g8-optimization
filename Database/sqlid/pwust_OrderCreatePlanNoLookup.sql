
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_ORDERCREATEPLANNOLOOKUP.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_ORDERCREATEPLANNOLOOKUP';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='pwust_OrderCreatePlanNoLookup';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='SMRO_WOE_310;defect1407 / defect 1564;defect 1414/1564,Defect1880';
v_sql_text sfcore_sql_lib.sql_text%type  :='select PLAN_NO,
PLAN_PART_NO,
PLAN_TITLE,
PLAN_ID,
PLAN_VERSION,
PLAN_REVISION,
PLAN_ALTERATIONS,
EFF_TYPE,
EFF_FROM,
EFF_THRU,
EFF_FROM_DATE,
EFF_THRU_DATE,
NUMBER_TASK_PART
from
(select T.PLAN_NO,
T.PLAN_PART_NO,
T.PLAN_TITLE,
T.PLAN_ID,
T.PLAN_VERSION,
T.PLAN_REVISION,
T.PLAN_ALTERATIONS,
T.EFF_TYPE,
T.EFF_FROM,
T.EFF_THRU,
T.EFF_FROM_DATE,
T.EFF_THRU_DATE,
T.NUMBER_TASK_PART
FROM PWUST_REPAIR_PLAN_SEL_V T,
     SFFND_USER_SEC_GRP S,
     SFPL_PLAN_DESC_SEC_GRP SG
WHERE T.SUBJECT_PART_NO = :ITEM_NO
AND T.SUBJECT_PART_CHG = :ITEM_REV
AND T.NUMBER_TASK_PART >0
AND T.PLND_WORK_LOC IN (SELECT LOC.WORK_LOC AS ASGND_WORK_LOC FROM SFFND_USER_WORK_CENTERS LEFT OUTER JOIN SFFND_WORK_LOC_DEF LOC ON LOC.LOCATION_ID = ASGND_LOCATION_ID WHERE USERID = :@USERID)
AND :WORK_LOC = T.PLND_WORK_LOC
AND S.SECURITY_GROUP = SG.SECURITY_GROUP
AND SG.PLAN_ID = T.PLAN_ID
AND SG.PLAN_UPDT_NO = T.PLAN_VERSION
AND  S.USERID = :@USERID
UNION
SELECT ''No Plan'' AS PLAN_NO, '''' AS PLAN_PART_NO, '''' AS PLAN_TITLE, '''' AS PLAN_ID, 
       null AS PLAN_VERSION, null AS PLAN_REVISION, null AS PLAN_ALTERATIONS,
       '''' AS EFF_TYPE, '''' AS EFF_FROM, '''' AS EFF_THRU, NULL AS EFF_FROM_DATE, NULL AS EFF_THRU_DATE, 0 AS NUMBER_TASK_PART FROM DUAL
) T WHERE 1=1    
ORDER BY NUMBER_TASK_PART DESC';
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

