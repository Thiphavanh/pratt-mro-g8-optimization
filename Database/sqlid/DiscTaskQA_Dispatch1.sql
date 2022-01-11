
set define off
prompt ---------------------------------------;
prompt Executing ... DISCTASKQA_DISPATCH1.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='FOUNDATION';
v_sql_id sfcore_sql_lib.sql_id%type :='DISCTASKQA_DISPATCH1';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='DiscTaskQA_Dispatch1';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='by Order No, Oper, Step, 1765';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT Z.ORDER_NO,
       Z.OPER_NO,
       Z.DISC_ID,
       Z.DISC_LINE_NO,
       Z.DISC_LINE_TITLE,
       Z.DISC_LINE_STATUS,
       Z.ITEM_ID,
       Z.AFFECTED_QTY,
       Z.ROUTE_TYPE,
       Z.DISP_TYPE,
       Z.CUST_NOTIF_FLAG,
       Z.DISP_INSTR_TYPE,
       Z.DISP_ORDER_ID,
       Z.DISP_DOC_TYPE,
       Z.DISP_DOC_NO,
       Z.REJECT_WORK_LOC,
       Z.REJECT_WORK_DEPT,
       Z.REJECT_WORK_CENTER,
       Z.NOTES,
       Z.PO_LINE_ITEM,
       Z.REJECT_PART_NO,
       Z.CA_FLAG,
       Z.CA_ID,
       Z.CA_REQUEST_ID,
       Z.MERGED_DISP_DOC_TYPE,
       Z.MERGED_DISP_DOC_NO,
       Z.QUEUE_TYPE,
       Z.TASK_STATUS,
       Z.TASK_ID,
       Z.UPDT_USERID,
       Z.TIME_STAMP,
       Z.ITEM_TYPE,
       Z.ITEM_SUBTYPE,
       Z.SECURITY_GROUP,
       Z.PRIORITY,
       Z.ASSIGNED_TO_USERID,
       Z.END_UNIT_NO,
       Z.END_UNIT_TYPE,
       Z.DISP_ORDER_NO,
       Z.PW_ORDER_NO FROM (
select A.ORDER_NO,
       A.OPER_NO,
       A.DISC_ID,
       A.DISC_LINE_NO,
       A.DISC_LINE_TITLE,
       A.DISC_LINE_STATUS,
       A.ITEM_ID,
       A.AFFECTED_QTY,
       A.ROUTE_TYPE,
       A.DISP_TYPE || CASE WHEN EXISTS
                  (SELECT 1 FROM SFWID_HOLDS SH
                        WHERE SH.HOLD_TYPE = ''VERIFY REWORK HOLD''
                          AND SH.HOLD_REF2 = A.DISC_ID
                          AND SH.HOLD_REF3 = TO_CHAR(A.DISC_LINE_NO)
                          AND SH.HOLD_REF4 = ''Re-Disposition Units'') THEN '';'' || NVL(A.FINAL_DISP_TYPE, ''N/A'') END AS DISP_TYPE,
       A.CUST_NOTIF_FLAG,
       A.DISP_INSTR_TYPE,
       A.DISP_ORDER_ID,
       A.DISP_DOC_TYPE,
       A.DISP_DOC_NO,
       A.REJECT_WORK_LOC,
       A.REJECT_WORK_DEPT,
       A.REJECT_WORK_CENTER,
       A.NOTES,
       A.PO_LINE_ITEM,
       A.REJECT_PART_NO,
       A.CA_FLAG,
       A.CA_ID,
       A.CA_REQUEST_ID,
       A.MERGED_DISP_DOC_TYPE,
       A.MERGED_DISP_DOC_NO,
       A.QUEUE_TYPE,
       A.TASK_STATUS,
       A.TASK_ID,
       A.UPDT_USERID,
       A.TIME_STAMP,
       A.ITEM_TYPE,
       A.ITEM_SUBTYPE,
       A.SECURITY_GROUP,
       A.PRIORITY,
       A.ASSIGNED_TO_USERID,
       A.UNIT_NO              AS END_UNIT_NO,
       A.UNIT_TYPE            AS END_UNIT_TYPE,
       A.DISP_ORDER_NO AS DISP_ORDER_NO,
       A.PW_ORDER_NO
  from SFQA_DISC_TASK_DISPATCH_V A, SFFND_USER UR
 where A.TASK_STATUS in (''IN QUEUE'', ''ACTIVE'')
   AND A.DISC_ID NOT IN
       (SELECT DISC_ID
          FROM SFFND_DOC_TYPE_DEF C, SFQA_DISC_ITEM
         WHERE C.DOC_TYPE = ''Discrepancy''
           AND DISC_TYPE = C.DOC_SUB_TYPE
           AND C.INSTRUCTIONS_TYPE = ''USER'')
   AND UR.USERID = :@USERID
   AND 1 = (CASE
         WHEN UR.USER_DEFINITION = ''Supplier'' THEN
          (CASE
            WHEN EXISTS (SELECT ''X''
                    FROM SFSQA_USER_SUPPLIERS S
                   WHERE S.USERID = UR.USERID
                     AND A.RESP_LOCATION_ID = S.SUPPLIER_CODE) THEN
             1
            ELSE
             2
          END)
         ELSE
          1
       END)) Z WHERE 1 = 1 /*+ @Filter */
 order by 1, 2';
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

