--------------------------------------------------
-- Export file for user SFMFG                   --
-- Created by X006640 on 9/23/2020, 10:13:27 AM --
--------------------------------------------------

set define off

prompt
prompt Creating view SFQA_DISC_TASK_DISPATCH_V
prompt =======================================
prompt
create or replace force view sfmfg.sfqa_disc_task_dispatch_v
(disc_id, disc_line_no, disc_line_title, disc_line_status, item_id, affected_qty, route_type, disp_type, disp_instr_type, disp_stop_type, disp_order_id, disp_doc_type, disp_doc_no, reject_location_id, reject_work_loc, reject_department_id, reject_work_dept, reject_center_id, reject_work_center, notes, po_line_item, disc_category, ca_flag, ca_id, ca_request_id, cust_notif_flag, ca_notes, order_id, oper_key, reject_part_no, order_no, oper_no, merged_disp_doc_type, merged_disp_doc_no, disp_order_no, task_id, queue_type, task_status, updt_userid, time_stamp, security_group, priority, assigned_to_userid, item_type, item_subtype, unit_no, unit_type, resp_location_id, final_disp_type, pw_order_no)
as
select  a.DISC_ID,  a.DISC_LINE_NO,  a.DISC_LINE_TITLE,  a.DISC_LINE_STATUS,
  a.ITEM_ID,  a.AFFECTED_QTY,  a.ROUTE_TYPE, a.DISP_TYPE,
  a.DISP_INSTR_TYPE,  A.disp_stop_type,  a.DISP_ORDER_ID,  a.DISP_DOC_TYPE,
  a.DISP_DOC_NO,  A.REJECT_LOCATION_ID, A.REJECT_WORK_LOC, A.REJECT_DEPARTMENT_ID, A.REJECT_WORK_DEPT, A.REJECT_CENTER_ID, A.REJECT_WORK_CENTER,
  a.NOTES,  a.PO_LINE_ITEM,
  a.disc_category,  A.ca_flag,  A.ca_id, a.ca_request_id,  a.CUST_NOTIF_FLAG,  A.ca_notes,
  A.ORDER_ID,  A.OPER_KEY,  A.REJECT_PART_NO,  A.ORDER_NO,  A.OPER_NO,
  A.MERGED_DISP_DOC_TYPE,  A.MERGED_DISP_DOC_NO,  A.DISP_ORDER_NO,
  F.TASK_ID,  F.QUEUE_TYPE,  F.STATUS,  F.UPDT_USERID,
  F.TIME_STAMP, A.security_group,f.priority,f.assigned_to_userid, A.ITEM_TYPE, A.ITEM_SUBTYPE, A.UNIT_NO, A.UNIT_TYPE, A.RESP_LOCATION_ID, A.FINAL_DISP_TYPE, substr( PWUST_SFWID_PW_ORDER_NO_GET(A.ORDER_ID),1,80)
from sfqa_disc_item_dispatch_v A ,SFFND_DISPOSITION_TASK E,SFFND_TASK F
where A.DISC_ID      = E.DISC_ID
and A.DISC_LINE_NO = E.DISC_LINE_NO
and E.TASK_ID      = F.TASK_ID;
grant select on SFMFG.SFQA_DISC_TASK_DISPATCH_V to PRATT_READ_ONLY;
grant select, insert, update, delete on SFMFG.SFQA_DISC_TASK_DISPATCH_V to SF$CONNECTION_POOL;


