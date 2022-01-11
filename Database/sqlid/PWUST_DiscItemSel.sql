
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_DISCITEMSEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_DISCITEMSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_DiscItemSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFQA';
v_description sfcore_sql_lib.description%type  :='Defect 742;1030';
v_sql_text sfcore_sql_lib.sql_text%type  :='select INSP_ORDER_ID, a.DISC_ID, a.DISC_LINE_NO, a.DISC_LINE_TITLE, a.DISC_LINE_STATUS, a.DISC_LINE_STATUS AS CURRENT_STATUS, a.ITEM_ID, a.PART_NO, a.PART_CHG, a.ITEM_TYPE,
a.ITEM_SUBTYPE, a.UOM,d.INSTRUCTIONS_TYPE, a.REF_DES, a.FIND_NO, a.PO_LINE_ITEM, a.ORDER_ID, e.pw_order_no as order_no, a.OPER_KEY, a.STEP_KEY, a.REJECT_WORK_LOC, a.REJECT_WORK_DEPT,
a.REJECT_WORK_CENTER, a.DISC_TYPE, a.DISC_CATEGORY, a.ROUTE_TYPE, a.DISP_TYPE, a.DISP_ORDER_ID, a.DISP_DOC_TYPE, a.DISP_DOC_NO, a.DISP_INSTR_TYPE,a.DISP_INSTR_TYPE as old_disp_instr_type,
a.DISP_STOP_TYPE, a.REJECT_COMPONENT_FLAG, a.AFFECTED_QTY, a.INSPECTED_QTY, a.CUST_ID, a.CUST_NOTIF_FLAG, a.NOTES, a.ALT_ID, a.CA_FLAG, a.CA_ID,
a.CA_CAUSE_OVERRIDE_FLAG, a.CA_NOTES, a.UPDT_USERID, a.TIME_STAMP, a.LAST_ACTION, a.UCF_DISC_ITEM_VCH1, a.UCF_DISC_ITEM_VCH2, a.UCF_DISC_ITEM_VCH3,
a.UCF_DISC_ITEM_VCH4, a.UCF_DISC_ITEM_VCH5, a.UCF_DISC_ITEM_VCH6, a.UCF_DISC_ITEM_VCH7, a.UCF_DISC_ITEM_VCH8, a.UCF_DISC_ITEM_VCH9, a.UCF_DISC_ITEM_VCH10,
a.UCF_DISC_ITEM_VCH11, a.UCF_DISC_ITEM_VCH12, a.UCF_DISC_ITEM_VCH13, a.UCF_DISC_ITEM_VCH14, a.UCF_DISC_ITEM_VCH15, a.UCF_DISC_ITEM_FLAG1,
a.UCF_DISC_ITEM_FLAG2, a.UCF_DISC_ITEM_FLAG3, a.UCF_DISC_ITEM_FLAG4, a.UCF_DISC_ITEM_FLAG5, a.UCF_DISC_ITEM_NUM1, a.UCF_DISC_ITEM_NUM2, a.UCF_DISC_ITEM_NUM3,
a.UCF_DISC_ITEM_NUM4, a.UCF_DISC_ITEM_NUM5, a.UCF_DISC_ITEM_DATE1, a.UCF_DISC_ITEM_DATE2, a.UCF_DISC_ITEM_DATE3, a.UCF_DISC_ITEM_DATE4, a.UCF_DISC_ITEM_DATE5,
a.UCF_DISC_ITEM_VCH255_1, a.UCF_DISC_ITEM_VCH255_2, a.UCF_DISC_ITEM_VCH255_3, a.UCF_DISC_ITEM_VCH4000_1, a.UCF_DISC_ITEM_VCH4000_2, a.reject_flag,
a.scrap_flag, CASE WHEN a.DISP_INSTR_FLAG IS NULL THEN ''N'' ELSE a.DISP_INSTR_FLAG END AS DISP_INSTR_FLAG , a.oper_no, a.merged_disp_doc_no, a.RELATED_INSP_ORDER_ID as RELATED_INSP_ORDER_ID, a.serial_flag, a.lot_flag, a.zone, a.disp_doc_rev,
decode(a.program,''ANY'',null,''N/A'',null,a.program) as program, sffnd_get_ini_type(:@AppIniId) as INI_TYPE,
case a.auto_update_to_implemented when ''Y'' then ''Automatically'' else ''Manually'' end as auto_update_to_implemented, A.SECURITY_GROUP,
a.PART_NO AS AUDIT_PROCESS, a.PART_CHG AS PROCESS_REV, a.ITEM_SUBTYPE AS PROCESS_SUBTYPE,
A.RESP_LOC,A.RESP_DEPT,A.RESP_WORK_CENTER,A.RESP_LOCATION_ID,A.RESP_DEPARTMENT_ID,A.RESP_CENTER_ID,
CASE WHEN a.RELATED_INSP_ORDER_ID IS NOT NULL THEN ''SQA''
WHEN DISP_ORDER_ID IS NOT NULL THEN ''WO''
ELSE NULL END AS DISP_INSTR_ORDER_TYPE,
(SELECT INSP_ORDER_CATEGORY FROM SFSQA_INSP_ORDER_DESC B, SFSQA_INSP_ORDER_TYPE_DEF C WHERE   B.INSP_ORDER_ID = a.INSP_ORDER_ID AND B.INSP_ORDER_TYPE=C.INSP_ORDER_TYPE) AS INSP_ORDER_CATEGORY,
PO_NO, PO_RELEASE,A.RESP_LOC AS RESP_SUPPLIER_CODE, A.CHARGE_CODE as resp_charge_code, A.INSP_ITEM_NO, RETURN_TO_VENDOR, RETURN_TO_INVENTORY_FLAG,
A.COMMODITY_JURISDICTION, A.COMMODITY_CLASSIFICATION,APPEND_TO_OPER_NO,APPEND_TO_STEP_NO,
LIEN_DISC_STOP_ORDERS, LIEN_DISC_INH_STOP_ORDERS, LIEN_DISC_STOP_ORDERS AS OLD_LIEN_DISC_STOP_ORDERS, LIEN_DISC_INH_STOP_ORDERS AS OLD_LIEN_DISC_INH_STOP_ORDERS,
CASE WHEN A.AFFECTED_QTY != 0 THEN CASE WHEN A.AFFECTED_QTY = trunc(A.AFFECTED_QTY) THEN ''N'' ELSE ''Y'' END ELSE ''N'' END AS DISP_REWORK_DECIMAL_FLAG
from SFQA_DISC_ITEM_V a,  SFQA_DISC_DESC c,sffnd_doc_type_def d, SFQA_DISC_TYPE_DEF dt, pwust_sfwid_order_desc e
where a.disc_id = :disc_id
and a.disc_line_no = :disc_line_no
and a.disc_id=c.disc_id
and d.doc_type = ''Discrepancy''
and d.doc_sub_type = a.disc_type
and d.work_flow = a.route_type
and a.disc_type = dt.disc_type
and a.order_id = e.order_id';
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

