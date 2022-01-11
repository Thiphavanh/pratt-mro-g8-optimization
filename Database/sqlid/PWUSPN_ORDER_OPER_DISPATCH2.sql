
set define off
prompt ---------------------------------------;
prompt Executing ... PWUSPN_ORDER_OPER_DISPATCH2.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUSPN_ORDER_OPER_DISPATCH2';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUSPN_ORDER_OPER_DISPATCH2';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Defect 1127';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT
IMAGE,
INSP_ORDER_ID,
A.ORDER_ID,
PW_ORDER_NO AS ORDER_NO,
ORDER_TYPE,
PART_NO,
ITEM_TYPE,
ITEM_SUBTYPE,
PART_CHG,
PROGRAM,
ORDER_STATUS,
ORDER_HOLD_STATUS,
ASGND_WORK_LOC,
ORDER_QTY,
ORIG_ORDER_ID,
ORDER_CONTROL_TYPE,
OPER_KEY,
OPER_NO,
OPER_STATUS,
OPER_HOLD_STATUS,
MOVE_PENDING_FLAG,
ASGND_WORK_DEPT,
ASGND_WORK_CENTER,
SCHED_START_DATE,
SCHED_END_DATE,
REVISED_START_DATE,
REVISED_END_DATE,
ACTUAL_START_DATE,
ACTUAL_END_DATE,
OPER_TITLE,
OPER_OPT_FLAG,
signoff_all_non_attendance,
SECURITY_GROUP,
PERCENT_COMPLETE,
SPLIT_FROM_ORDER_ID,
END_UNIT_NO,
END_UNIT_TYPE,
OPERATION_OVERLAP_FLAG
FROM
(
SELECT
   CASE WHEN B.INSP_ORDER_ID IS NOT NULL THEN  ''InspectionBlock'' ELSE '''' END AS IMAGE,
   B.INSP_ORDER_ID AS INSP_ORDER_ID,
   d.ORDER_ID,d.ORDER_NO,d.ORDER_TYPE,d.PART_NO,d.ITEM_TYPE,d.ITEM_SUBTYPE,d.PART_CHG,d.PROGRAM,d.ORDER_STATUS,
   d.ORDER_HOLD_STATUS,d.ASGND_WORK_LOC,d.ORDER_QTY,d.ORIG_ORDER_ID,
   ORDER_CONTROL_TYPE,
   OPER_KEY,OPER_NO,OPER_STATUS,OPER_HOLD_STATUS,MOVE_PENDING_FLAG,
   ASGND_WORK_DEPT,ASGND_WORK_CENTER,d.SCHED_START_DATE,d.SCHED_END_DATE,
   d.REVISED_START_DATE,d.REVISED_END_DATE,d.ACTUAL_START_DATE,d.ACTUAL_END_DATE,
   OPER_TITLE,OPER_OPT_FLAG,''N'' as signoff_all_non_attendance,d.SECURITY_GROUP,PERCENT_COMPLETE,B.SPLIT_FROM_ORDER_ID,
   END_UNIT_NO, END_UNIT_TYPE,B.OPERATION_OVERLAP_FLAG
from   SFMFG.sfwid_order_opers_disp_data d
   left join
   (
      SELECT
       o.Order_ID,o.INSP_ORDER_ID,SPLIT_FROM_ORDER_ID,
       CASE WHEN o.SERIAL_FLAG  = ''Y'' and o.LOT_FLAG = ''Y''             THEN   case when o.ORDER_QTY = 1 then ''SERIAL-LOT1''    else ''SERIAL-LOT''    end
        WHEN o.SERIAL_FLAG  = ''Y''                                  THEN   case when o.ORDER_QTY = 1 then ''SERIAL1''    else ''SERIAL''    end
        WHEN NVL(o.SERIAL_FLAG,''N'') != ''Y'' and o.LOT_FLAG = ''Y'' THEN case when count(LD.Order_id) <= 1        then ''LOT1'' else ''LOT'' end
        ELSE ''NONE''
       END AS ORDER_CONTROL_TYPE,
       o.UNIT_NO AS END_UNIT_NO, o.UNIT_TYPE AS END_UNIT_TYPE,o.OPERATION_OVERLAP_FLAG
      from         SFMFG.SFWID_ORDER_DESC o
         left join SFMFG.SFWID_LOT_DESC LD on LD.ORDER_ID = o.ORDER_ID
      group by o.Order_ID,o.SERIAL_FLAG,o.LOT_FLAG,o.ORDER_QTY,o.INSP_ORDER_ID,SPLIT_FROM_ORDER_ID, o.UNIT_NO, o.UNIT_TYPE,o.OPERATION_OVERLAP_FLAG
   ) B on d.ORDER_ID = B.ORDER_ID
) A, PWUST_SFWID_ORDER_DESC x where x.ORDER_ID = A.ORDER_ID
and 1=1 /*+ @Filter */';
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

