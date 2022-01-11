
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_DISCITEM_DISPATCH1.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_DISCITEM_DISPATCH1';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_DiscItem_Dispatch1';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Defect 1394';
v_sql_text sfcore_sql_lib.sql_text%type  :='select Z.ORDER_NO,Z.OPER_NO,Z.DISC_ID,Z.DISC_LINE_NO,Z.DISC_LINE_TITLE,Z.DISC_LINE_STATUS,Z.ITEM_ID,Z.AFFECTED_QTY,Z.ROUTE_TYPE,Z.DISP_TYPE,Z.CUST_NOTIF_FLAG,Z.DISP_INSTR_TYPE,Z.DISP_ORDER_ID,Z.DISP_DOC_TYPE,Z.DISP_DOC_NO,Z.NOTES,Z.PO_LINE_ITEM,Z.UPDT_USERID,Z.TIME_STAMP,Z.LAST_ACTION,Z.REJECT_PART_NO,Z.CA_FLAG,Z.CA_ID,Z.CA_REQUEST_ID,Z.SECURITY_GROUP,Z.END_UNIT_NO,Z.END_UNIT_TYPE FROM (
select
  X.PW_ORDER_NO AS ORDER_NO,
  V.OPER_NO,
  V.DISC_ID,
  V.DISC_LINE_NO,
  V.DISC_LINE_TITLE,
  V.DISC_LINE_STATUS,
  V.ITEM_ID,
  V.AFFECTED_QTY,
  V.ROUTE_TYPE,
  V.DISP_TYPE || CASE WHEN EXISTS
                  (SELECT 1 FROM SFWID_HOLDS SH
                        WHERE SH.HOLD_TYPE = ''VERIFY REWORK HOLD''
                          AND SH.HOLD_REF2 = V.DISC_ID
                          AND SH.HOLD_REF3 = TO_CHAR(V.DISC_LINE_NO)
                          AND SH.HOLD_REF4 = ''Re-Disposition Units'') THEN '';'' || NVL(V.FINAL_DISP_TYPE, ''N/A'') END AS DISP_TYPE,
  V.CUST_NOTIF_FLAG,
  V.DISP_INSTR_TYPE,
  V.DISP_ORDER_ID,
  V.DISP_DOC_TYPE,
  V.DISP_DOC_NO,
  V.NOTES,
  V.PO_LINE_ITEM,
  V.UPDT_USERID,
  V.TIME_STAMP,
  V.LAST_ACTION,
  V.REJECT_PART_NO,
  V.CA_FLAG,
  V.CA_ID,
  V.CA_REQUEST_ID,
  V.SECURITY_GROUP,
  UNIT_NO AS END_UNIT_NO,
  UNIT_TYPE AS END_UNIT_TYPE
from SFQA_DISC_ITEM_DISPATCH_V V, SFFND_USER UR, pwust_sfwid_order_desc X
WHERE V.ORDER_ID = X.ORDER_ID
AND
  V.DISC_ID NOT IN (SELECT DISC_ID FROM SFFND_DOC_TYPE_DEF C,SFQA_DISC_ITEM
                WHERE C.DOC_TYPE = ''Discrepancy''
                AND DISC_TYPE = C.DOC_SUB_TYPE
                AND C.INSTRUCTIONS_TYPE = ''USER'')
 AND UR.USERID = :@USERID
AND 1 = (CASE WHEN UR.USER_DEFINITION = ''Supplier'' AND UR.INCLUDE_ALL_SUPPLIERS = ''N'' THEN
              (CASE WHEN  EXISTS( SELECT ''X''
                                  FROM SFSQA_USER_SUPPLIERS S
                                 WHERE S.USERID = UR.USERID
                                   AND V.RESP_LOCATION_ID = S.SUPPLIER_CODE
                                   AND S.SUPPLIER_CODE <> ''N/A'' ) THEN 1
              ELSE 2 END)
        ELSE 1 END)) Z WHERE 1 = 1 /*+ @Filter */';
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

