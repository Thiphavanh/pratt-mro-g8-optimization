
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_PLG_OPERSEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_PLG_OPERSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_PLG_OPERSEL';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Defect 1055 Select operation in plan';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT
    OBJECT_ID,
    OPER_KEY,
    OPER_NO,
    SUBJECT_NO_DISP,
    SUBJECT_NO,
    OPER_TITLE,
    OPER_UPDT_NO,
    MAX_REVISION,
    UPDT_USERID,
    TIME_STAMP,
    PLAN_ID,
    STDOPER_FLAG,
    PLAN_UPDT_NO,
    PLND_WORK_CENTER,
    UNIT_PROCESSING,
    STATUS,
    REV_STATUS ,
    OPER_CHANGE_LEVEL,
    OPER_KEY_INPUT_PARAM
FROM (SELECT
        C.OBJECT_ID,
        C.OPER_KEY,
        C.OPER_NO,
        CASE WHEN T.SUBJECT_NO IS NULL THEN ''NONE'' ELSE TO_CHAR(T.SUBJECT_NO) END AS SUBJECT_NO_DISP,
        T.SUBJECT_NO,
        C.OPER_TITLE,
        C.OPER_UPDT_NO,
        C.OPER_UPDT_NO AS MAX_REVISION,
        C.UPDT_USERID,
        C.TIME_STAMP,
        C.PLAN_ID,
        A.STDOPER_FLAG,
        A.PLAN_UPDT_NO,
        CASE
            WHEN C.PLND_CENTER_ID IS NULL THEN NULL
            ELSE (SELECT
                WORK_CENTER
            FROM
                SFFND_WORK_CENTER_DEF CEN
            WHERE
                CEN.LOCATION_ID = C.PLND_LOCATION_ID
                AND CEN.DEPARTMENT_ID = C.PLND_DEPARTMENT_ID
                AND CEN.CENTER_ID = C.PLND_CENTER_ID)
        END AS PLND_WORK_CENTER,
        C.UNIT_PROCESSING,
        SFMFG.SFPL_OPERATION_STATE_GET(A.PLAN_ID,
        A.PLAN_VERSION,
        A.PLAN_REVISION ,
        A.PLAN_ALTERATIONS,
        A.OPER_KEY) AS STATUS,
        R.REV_STATUS ,
        C.OPER_CHANGE_LEVEL,
        C.OPER_KEY AS OPER_KEY_INPUT_PARAM
    FROM
        SFPL_OPERATION_REV A,
        SFPL_OPERATION_DESC C,
        SFPL_PLAN_REV R,
        SFOR_SFPL_PLAN_SUBJECT_OPER T
    WHERE
        A.PLAN_ID =:PLAN_ID
        AND A.PLAN_VERSION =:PLAN_VERSION
        AND A.PLAN_REVISION =:PLAN_REVISION
        AND A.PLAN_ALTERATIONS =:PLAN_ALTERATIONS
        AND A.PLAN_ID = R.PLAN_ID
        AND A.PLAN_VERSION = R.PLAN_VERSION
        AND A.PLAN_REVISION = R.PLAN_REVISION
        AND A.PLAN_ALTERATIONS = R.PLAN_ALTERATIONS
        AND A.PLAN_ID = C.PLAN_ID
        AND A.OPER_UPDT_NO = C.OPER_UPDT_NO
        AND A.OPER_KEY = C.OPER_KEY
        AND T.OPER_KEY(+) = A.OPER_KEY
        AND T.PLAN_ID(+) = A.PLAN_ID
        AND T.PLAN_UPDT_NO(+) = A.PLAN_UPDT_NO
   ) X WHERE 1=1 /*+ @Filter */
   ORDER BY LPAD(X.OPER_NO,4,0)';
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

