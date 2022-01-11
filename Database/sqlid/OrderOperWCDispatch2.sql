
set define off
prompt ---------------------------------------;
prompt Executing ... ORDEROPERWCDISPATCH2.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='FOUNDATION';
v_sql_id sfcore_sql_lib.sql_id%type :='ORDEROPERWCDISPATCH2';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='OrderOperWCDispatch2';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='BY PROGRAM, PART_NO, SERIAL_NO, LOT_NO, ORDER_NO, OPER_NO, defect 785';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT
IMAGE,
INSP_ORDER_ID,
SERIAL_HOLD_STATUS,
PROGRAM,
PART_NO,
PART_CHG,
SERIAL_NO,
LOT_NO,
SERIAL_NO_LIST,
LOT_NO_LIST,
ORDER_NO,
OPER_NO,
LOT_QTY,
SERIAL_OPER_STATUS,
ASGND_WORK_LOC,
SCHED_PRIORITY,
SCHED_START_DATE,
SCHED_END_DATE,
ACTUAL_START_DATE,
ACTUAL_END_DATE,
REVISED_START_DATE,
REVISED_END_DATE,
MOVE_PENDING_FLAG,
ORDER_TYPE,
ORDER_STATUS,
ORDER_HOLD_STATUS,
OPER_TITLE,
OPER_STATUS,
OPER_HOLD_STATUS,
OPER_OPT_FLAG,
ORDER_QTY,
ITEM_TYPE,
ITEM_SUBTYPE,
ASGND_WORK_DEPT,
ASGND_WORK_CENTER,
ORDER_ID,
ORIG_ORDER_ID,
OPER_KEY,
SERIAL_ID,
LOT_ID,
SIGNOFF_ALL_NON_ATTENDANCE,
ORDER_CONTROL_TYPE,
REMOVE_FLAG,
SECURITY_GROUP,
END_UNIT_NO,
END_UNIT_TYPE,
SERIAL_FLAG,
OPERATION_OVERLAP_FLAG
FROM(
SELECT /*+ RULE */
 CASE WHEN F.INSP_ORDER_ID IS NOT NULL THEN  ''InspectionBlock'' ELSE '''' END AS IMAGE,
 F.INSP_ORDER_ID,
 C.SERIAL_HOLD_STATUS,
 A.PROGRAM,
 A.PART_NO,
 A.PART_CHG,
 C.SERIAL_NO,
 D.LOT_NO,
 C.SERIAL_NO               AS SERIAL_NO_LIST,
 D.LOT_NO                  AS LOT_NO_LIST,
 g.pw_ORDER_NO as order_no,
 A.OPER_NO,
 D.LOT_QTY,
 B.SERIAL_OPER_STATUS,
 A.ASGND_WORK_LOC,
 F.SCHED_PRIORITY,
 A.SCHED_START_DATE,
 A.SCHED_END_DATE,
 A.ACTUAL_START_DATE,
 A.ACTUAL_END_DATE,
 A.REVISED_START_DATE,
 A.REVISED_END_DATE,
 B.MOVE_PENDING_FLAG,
 A.ORDER_TYPE,
 A.ORDER_STATUS,
 A.ORDER_HOLD_STATUS,
 A.OPER_TITLE,
 A.OPER_STATUS,
 A.OPER_HOLD_STATUS,
 A.OPER_OPT_FLAG,
 A.ORDER_QTY,
 A.ITEM_TYPE,
 A.ITEM_SUBTYPE,
 A.ASGND_WORK_DEPT,
 A.ASGND_WORK_CENTER,
 A.ORDER_ID,
 A.ORIG_ORDER_ID,
 A.OPER_KEY,
 B.SERIAL_ID,
 B.LOT_ID,
 ''N''                       AS SIGNOFF_ALL_NON_ATTENDANCE,
 SFMFG.SFWID_ORDER_CONTROL(A.ORDER_ID) AS ORDER_CONTROL_TYPE,
 (CASE WHEN (SELECT COUNT(*) FROM SFWID_AS_WORKED_BOM G
                    WHERE G.PART_NO = A.PART_NO
                      AND G.PART_CHG = A.PART_CHG
                      AND G.LOT_ID = B.LOT_ID
                      AND G.SERIAL_ID = B.SERIAL_ID
                      AND G.PART_ACTION = ''REMOVE''
                      AND G.REMOVE_FLAG = ''Y'') = 0
             THEN ''N'' ELSE ''Y'' END ) AS REMOVE_FLAG,
   F.SECURITY_GROUP,
F.UNIT_NO  AS END_UNIT_NO, F.UNIT_TYPE AS END_UNIT_TYPE,F.SERIAL_FLAG,F.OPERATION_OVERLAP_FLAG
  FROM SFMFG.SFWID_ORDER_OPERS_DISPATCH A,
       SFMFG.SFWID_SERIAL_OPER          B,
       SFMFG.SFWID_SERIAL_DESC          C,
       SFMFG.SFWID_LOT_DESC             D,
       SFMFG.SFFND_USER_WORK_CENTERS    E
              LEFT OUTER JOIN SFFND_WORK_LOC_DEF LOC ON LOC.LOCATION_ID = E.ASGND_LOCATION_ID
              LEFT OUTER JOIN SFFND_WORK_DEPT_DEF DEPT ON DEPT.LOCATION_ID = E.ASGND_LOCATION_ID AND DEPT.DEPARTMENT_ID = E.ASGND_DEPARTMENT_ID
              LEFT OUTER JOIN SFFND_WORK_CENTER_DEF CEN ON CEN.LOCATION_ID = E.ASGND_LOCATION_ID AND CEN.DEPARTMENT_ID = E.ASGND_DEPARTMENT_ID
                                                 AND CEN.CENTER_ID = E.ASGND_CENTER_ID,
       SFMFG.SFWID_ORDER_DESC           F,
       sfmfg.pwust_sfwid_order_desc g

 WHERE C.ORDER_ID = D.ORDER_ID
   and a.order_id = g.order_id
   AND C.LOT_ID = D.LOT_ID
   AND B.ORDER_ID = C.ORDER_ID
   AND A.ORDER_ID = B.ORDER_ID
   AND F.ORDER_ID = C.ORDER_ID
   AND A.OPER_KEY = B.OPER_KEY
   AND B.LOT_ID = C.LOT_ID
   AND B.SERIAL_ID = C.SERIAL_ID
   AND B.STEP_KEY = -1
   AND A.OPER_STATUS IN(''ACTIVE'', ''IN QUEUE'')
   AND NVL(A.ASGND_WORK_LOC,''X'') = NVL(WORK_LOC,''X'')
   AND NVL(A.ASGND_WORK_DEPT,''X'') = NVL(WORK_DEPT,''X'')
   AND NVL(A.ASGND_WORK_CENTER,''X'') = NVL(WORK_CENTER,''X'')
   AND E.USERID = :@USERID
 ORDER BY PROGRAM, PART_NO, SERIAL_NO, LOT_NO, ORDER_NO, OPER_NO
 ) WHERE 1 = 1 /*+ @Filter */';
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

