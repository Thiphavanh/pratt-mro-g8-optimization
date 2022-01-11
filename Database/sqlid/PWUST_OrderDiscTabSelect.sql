
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_ORDERDISCTABSELECT.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_ORDERDISCTABSELECT';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_OrderDiscTabSelect';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='defect 2013';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT Z.DISC_ID, Z.DISC_LINE_NO, Z.DISC_LINE_STATUS, Z.PART_NO, Z.PART_CHG, Z.ITEM_ID, Z.ITEM_TYPE, Z.ITEM_SUBTYPE,
Z.ROUTE_TYPE, Z.DISP_TYPE, Z.DISP_INSTR_TYPE, Z.AFFECTED_QTY, Z.REJECT_COMPONENT_FLAG, Z.DISC_CATEGORY, Z.CA_FLAG,
Z.CA_ID, Z.REJECT_WORK_LOC, Z.REJECT_WORK_DEPT, Z.REJECT_WORK_CENTER, Z.ORDER_ID, Z.DISP_ORDER_ID, Z.OPER_KEY,
Z.UPDT_USERID, Z.TIME_STAMP, Z.OPER_NO, Z.SECURITY_GROUP, z.disc_line_title
FROM(
select
 a.DISC_ID
,a.DISC_LINE_NO
,a.DISC_LINE_STATUS
,a.PART_NO
,a.PART_CHG
,a.ITEM_ID
,A.ITEM_TYPE
,A.ITEM_SUBTYPE
,a.ROUTE_TYPE
,a.DISP_TYPE
,a.DISP_INSTR_TYPE
,a.AFFECTED_QTY
,a.REJECT_COMPONENT_FLAG
,a.DISC_CATEGORY
,a.CA_FLAG
,a.CA_ID
,CASE WHEN a.REJECT_LOCATION_ID IS NULL THEN NULL ELSE (SELECT WORK_LOC FROM SFFND_WORK_LOC_DEF WHERE LOCATION_ID=A.REJECT_LOCATION_ID) END AS REJECT_WORK_LOC
,CASE WHEN REJECT_DEPARTMENT_ID IS NULL THEN NULL
     ELSE (SELECT WORK_DEPT FROM SFFND_WORK_DEPT_DEF DEPT WHERE DEPT.LOCATION_ID = REJECT_LOCATION_ID
                 AND DEPT.DEPARTMENT_ID = REJECT_DEPARTMENT_ID) END AS REJECT_WORK_DEPT
,CASE WHEN REJECT_CENTER_ID IS NULL THEN NULL
     ELSE (SELECT WORK_CENTER FROM SFFND_WORK_CENTER_DEF CEN WHERE CEN.LOCATION_ID = REJECT_LOCATION_ID
               AND CEN.DEPARTMENT_ID = REJECT_DEPARTMENT_ID
               AND CEN.CENTER_ID = REJECT_CENTER_ID) END AS REJECT_WORK_CENTER
,a.ORDER_ID
,a.DISP_ORDER_ID
,a.OPER_KEY
,a.UPDT_USERID
,a.TIME_STAMP
,b.OPER_NO
,A.SECURITY_GROUP
,a.disc_line_title
from SFQA_DISC_ITEM a, SFWID_OPER_DESC b
where a.ORDER_ID = :ORDER_ID
  and  a.ORDER_ID = b.ORDER_ID (+)
  and  a.OPER_KEY  = b.OPER_KEY (+)
  and -1 = b.step_key (+)
  AND A.DISC_ID NOT IN (SELECT A.DISC_ID FROM SFFND_DOC_TYPE_DEF C
                WHERE C.DOC_TYPE = ''Discrepancy''
                AND A.DISC_TYPE = C.DOC_SUB_TYPE
                AND C.INSTRUCTIONS_TYPE = ''USER'')
) Z
WHERE 1=1 /*+ @FILTER */
order by
  Z.OPER_NO
  ,decode(Z.DISC_LINE_STATUS,''IN PROCESS'',1,2)
  ,Z.DISC_ID,Z.DISC_LINE_NO';
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

