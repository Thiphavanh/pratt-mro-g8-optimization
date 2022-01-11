
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_PLG_RELEASEPACKAGEPLANSEL2.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_PLG_RELEASEPACKAGEPLANSEL2';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_PLG_ReleasePackagePlanSel2';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFPL';
v_description sfcore_sql_lib.description%type  :='Defect319';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT
  TO_PWP_ID,
  PWP_ID,
  PLAN_NO,
  PLAN_ID,
  PLAN_TYPE,
  ITEM_NO,
  ITEM_REV,
  ITEM_TYPE,
  ITEM_SUBTYPE,
  PROGRAM,
  PLAN_VERSION,
  PLAN_REVISION,
  PLAN_ALTERATIONS,
  PLAN_TITLE,
  UPDT_USERID,
  TIME_STAMP,
  PWP_STATUS,
  REV_STATUS,
  SECURITY_GROUP,
  MFG_BOM_CHG,
  PLAN_LOCATION_ID,
  CHANGE_NO,
  CHANGE_TYPE,
  PLAN_WORK_LOC,
  PLAN_WORK_LOC AS ASGND_WORK_LOC,
  MRO_FLAG,
  OBSOLETE_FLAG
FROM (
  SELECT
    '''' AS TO_PWP_ID,
    E.PWP_ID,
    A.PLAN_NO,
    A.PLAN_ID,
    A.PLAN_TYPE,
    A.PART_NO AS ITEM_NO,
    A.PART_CHG AS ITEM_REV,
    A.PART_NO,
    A.PART_CHG,
    A.ITEM_TYPE,
    A.ITEM_SUBTYPE,
    A.PROGRAM,
    B.PLAN_VERSION,
    B.PLAN_REVISION,
    B.PLAN_ALTERATIONS,
    PLAN_TITLE,
    B.UPDT_USERID,
    B.TIME_STAMP,
    E.PWP_STATUS,
    B.REV_STATUS,
    B.OBSOLETE_FLAG,
    A.SECURITY_GROUP,
    A.MFG_BOM_CHG,
    A.PLND_LOCATION_ID AS PLAN_LOCATION_ID,
    F.CHANGE_NO,
    F.CHANGE_TYPE,
    CASE
       WHEN COALESCE(A.PLND_LOCATION_ID, ''X'') = ''X''  THEN
          NULL
       ELSE
          (SELECT WORK_LOC FROM SFFND_WORK_LOC_DEF WHERE LOCATION_ID = A.PLND_LOCATION_ID)
    END AS PLAN_WORK_LOC
    , (SELECT CASE
             WHEN M.INSTRUCTIONS_TYPE LIKE ''MRO%'' THEN
    	     ''Y''
    	     ELSE
    	     ''N''
    	     END
      FROM SFFND_DOC_TYPE_DEF M WHERE A.DOC_TYPE = M.DOC_TYPE AND A.PLAN_TYPE = M.DOC_SUB_TYPE AND A.WORK_FLOW = M.WORK_FLOW) AS MRO_FLAG
  FROM
    SFPL_PLAN_DESC A,
    SFPL_PLAN_REV B
           LEFT OUTER JOIN SFPL_PLANNED_ACTIONS C ON
                      B.PLAN_ID = C.PLAN_ID AND
                      B.PLAN_VERSION = C.PLAN_VERSION AND
                      B.PLAN_REVISION = C.PLAN_REVISION AND
                      B.PLAN_ALTERATIONS = C.PLAN_ALTERATIONS
           LEFT OUTER JOIN SFPL_CHANGE_REQUEST F ON
                      C.CHANGE_REQUEST_ID = F.CHANGE_REQUEST_ID,
    SFPL_PWP_DESC E,
    SFPL_PLAN_PWP_XREF D
  WHERE
    A.PLAN_ID = B.PLAN_ID AND
    A.PLAN_UPDT_NO = B.PLAN_UPDT_NO AND
    A.DOC_TYPE = ''Process Plan'' AND
    COALESCE(A.ITEM_SUBTYPE, ''X'') != ''REWORK'' AND
    CASE WHEN C.PLANNED_ACTION_ID IS NULL THEN E.PWP_ID ELSE C.PWP_ID END  = :PWP_ID AND
    D.PWP_ID = E.PWP_ID AND
    D.PLAN_ID = B.PLAN_ID AND
    D.PLAN_VERSION = B.PLAN_VERSION AND
    D.PLAN_REVISION = B.PLAN_REVISION AND
    D.PLAN_ALTERATIONS = B.PLAN_ALTERATIONS  AND
    B.PLAN_REVISION = (SELECT MAX(B2.PLAN_REVISION) FROM SFPL_PLAN_REV B2 WHERE
        B.PLAN_ID = B2.PLAN_ID AND
        B.PLAN_VERSION = B2.PLAN_VERSION AND
        B.PLAN_ALTERATIONS = B2.PLAN_ALTERATIONS)
) X
WHERE 1 = 1 /*+ @Filter() */';
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

