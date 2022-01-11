
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_DISCITEMSSELECT.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_DISCITEMSSELECT';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_DiscItemsSelect';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Defect 742;1030';
v_sql_text sfcore_sql_lib.sql_text%type  :='select a.INSP_ORDER_ID, a.insp_step_id, a.DISC_ID,a.DISC_LINE_NO,
SFMFG.SFFND_EVENT.UDV_LINK(''MFI_7E243C3F271702CEE04400144FA7B7D2'', ''Update'', a.DISC_ID||'',''||a.DISC_LINE_NO,
''HYPERTEXT'', ''PALEYELLOW'',''BLACK'', ''DISC_ID='' || A.DISC_ID || '',DISC_LINE_NO='' || A.DISC_LINE_NO|| '',CALLED_FROM=GRID'') AS DISC_LINE_NO_DISPLAY,
A.DISC_LINE_TITLE AS WO_DISC_LINE_TITLE,A.DISC_LINE_TITLE AS SQA_DISC_LINE_TITLE,A.DISC_LINE_TITLE,
CASE WHEN S.INSP_ITEM_NAME_ID IS NULL THEN S.CHECKLIST_ITEM_NAME ELSE N.INSP_ITEM_NAME END AS VERIF_ITEM_NAME,
a.DISC_LINE_STATUS, a.DISC_LINE_STATUS AS CURRENT_STATUS,
a.ITEM_ID,a.PART_NO,a.PART_CHG,a.UOM,a.REF_DES,a.FIND_NO,a.PO_LINE_ITEM,a.ORDER_ID,
e.pw_order_no as order_no,a.OPER_KEY,a.STEP_KEY,a.REJECT_WORK_LOC,a.REJECT_WORK_DEPT,a.REJECT_WORK_CENTER,
a.DISC_TYPE,a.DISC_CATEGORY,a.ROUTE_TYPE,CASE WHEN a.DISP_TYPE = ''AUDIT'' THEN null ELSE a.DISP_TYPE END AS DISP_TYPE,a.DISP_ORDER_ID,a.DISP_DOC_TYPE,
a.DISP_DOC_NO,a.DISP_INSTR_TYPE,a.DISP_STOP_TYPE,a.REJECT_COMPONENT_FLAG,a.AFFECTED_QTY,
a.INSPECTED_QTY,a.ORDER_MAKE_ITEM_FLAG,a.CUST_ID,a.CUST_NOTIF_FLAG,a.NOTES,a.ALT_ID,
a.CA_FLAG,C.CA_ID,a.CA_CAUSE_OVERRIDE_FLAG,a.CA_NOTES,a.UPDT_USERID,a.TIME_STAMP,a.LAST_ACTION,
a.UCF_DISC_ITEM_VCH1,a.UCF_DISC_ITEM_VCH2,a.UCF_DISC_ITEM_VCH3,a.UCF_DISC_ITEM_VCH4,
a.UCF_DISC_ITEM_VCH5, a.UCF_DISC_ITEM_VCH6,a.UCF_DISC_ITEM_VCH7,a.UCF_DISC_ITEM_VCH8,
a.UCF_DISC_ITEM_VCH9,a.UCF_DISC_ITEM_VCH10, a.UCF_DISC_ITEM_VCH11,a.UCF_DISC_ITEM_VCH12,
a.UCF_DISC_ITEM_VCH13,a.UCF_DISC_ITEM_VCH14, a.UCF_DISC_ITEM_VCH15,
a.UCF_DISC_ITEM_FLAG1,a.UCF_DISC_ITEM_FLAG2,a.UCF_DISC_ITEM_FLAG3,a.UCF_DISC_ITEM_FLAG4,a.UCF_DISC_ITEM_FLAG5,
a.UCF_DISC_ITEM_NUM1,a.UCF_DISC_ITEM_NUM2,a.UCF_DISC_ITEM_NUM3,a.UCF_DISC_ITEM_NUM4,a.UCF_DISC_ITEM_NUM5,
a.UCF_DISC_ITEM_DATE1,a.UCF_DISC_ITEM_DATE2,a.UCF_DISC_ITEM_DATE3,a.UCF_DISC_ITEM_DATE4,a.UCF_DISC_ITEM_DATE5,
a.UCF_DISC_ITEM_VCH255_1,a.UCF_DISC_ITEM_VCH255_2,a.UCF_DISC_ITEM_VCH255_3,
a.UCF_DISC_ITEM_VCH4000_1,a.UCF_DISC_ITEM_VCH4000_2,
a.reject_flag,a.scrap_flag,a.DISP_INSTR_FLAG,
a.oper_no,a.merged_disp_doc_no,substr(sfwid_order_no_get(disp_order_id),1,40) as disp_order_no,
a.serial_flag,a.lot_flag,decode(a.program,''ANY'',null,a.program) as program,
A.ITEM_TYPE, A.ITEM_SUBTYPE,C.CA_TYPE, A.SECURITY_GROUP,
a.PART_NO as Audit_Process,a.PART_CHG as Process_Rev,A.ITEM_SUBTYPE as Process_SubType,
(select INSP_ORDER_NO from sfsqa_insp_order_desc od where od.insp_order_id=a.insp_order_id) as insp_order_no,
(select insp_step_name from sfsqa_insp_step_def where insp_step_id = a.insp_step_id) as insp_step_no,
case when (B.DOC_TYPE, ORDER_TYPE) in (select DOC_TYPE, DOC_SUB_TYPE from SFFND_DOC_TYPE_DEF where INSTRUCTIONS_TYPE like ''MRO%'') then ''Y'' else ''N'' end as MRO_FLAG,
D.INSTRUCTIONS_TYPE AS DISC_INSTRUCTIONS_TYPE,
(select ITEM_LOT_SERIAL_ALRT from table(SFMFG.SFQA_DISC_ALERT_INFO_TAB_GET(a.DISC_ID,a.DISC_LINE_NO,''DISC_RECORD''))) as ITEM_LOT_SERIAL_ALRT
from sfqa_disc_item_v a, SFWID_ORDER_DESC b, SFQA_CA_DESC C, SFFND_DOC_TYPE_DEF D ,SFSQA_INSP_ORDER_IS_INSP_ITEMS S , SFSQA_INSP_ITEM_NAME_DEF N, pwust_sfwid_order_desc e
where a.disc_id = :disc_id
and a.order_id = b.order_id(+)
and A.DOC_TYPE = D.DOC_TYPE
AND A.DISC_TYPE = D.DOC_SUB_TYPE
AND A.ROUTE_TYPE = D.WORK_FLOW
AND A.CA_ID = C.CA_ID(+)
AND a.INSP_ORDER_ID=S.INSP_ORDER_ID(+)
AND a.INSP_STEP_ID=S.INSP_STEP_ID(+)
AND a.INSP_ITEM_ID=S.INSP_ITEM_ID(+)
AND a.ITEM_ID=S.ITEM_ID(+)
AND a.INSP_ITEM_COUNT=S.INSP_ITEM_COUNT(+)
AND S.INSP_ITEM_NAME_ID = N.INSP_ITEM_NAME_ID(+)
AND 1 = (CASE WHEN D.INSTRUCTIONS_TYPE = ''AUDIT'' THEN 1 ELSE (CASE WHEN (SELECT USER_DEFINITION FROM SFFND_USER WHERE USERID = :@USERID) = ''Supplier''
THEN (CASE WHEN RESP_LOCATION_ID IN (SELECT SUPPLIER_CODE FROM SFSQA_USER_SUPPLIERS WHERE USERID = :@USERID) THEN 1 ELSE 2 END) ELSE 1 END) END)
and a.order_id = e.order_id
/*+ @Filter */
order by a.disc_line_no';
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

