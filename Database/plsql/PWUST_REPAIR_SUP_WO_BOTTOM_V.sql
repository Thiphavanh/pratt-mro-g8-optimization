-------------------------------------------------
-- Export file for user SFMFG                  --
-- Created by X006640 on 7/21/2020, 2:10:47 PM --
-------------------------------------------------

set define off

prompt
prompt Creating view PWUST_REPAIR_SUP_WO_BOTTOM_V
prompt ==========================================
prompt
CREATE OR REPLACE FORCE VIEW SFMFG.PWUST_REPAIR_SUP_WO_BOTTOM_V AS
SELECT T.ORDER_ID,
       T.ORDER_NO,
       T.PLAN_NO,
       T.PART_NO,
       T.PLAN_REVISION,
       T.PLAN_TITLE,
       T.ALT_COUNT,
       T.ORDER_STATUS,
       T.ALT_STATUS,
       T.ORDER_HOLD_STATUS,
       (SELECT T1.UPDT_USERID FROM SFWID_ALTERATION_DESC T1 WHERE T1.ALT_ID = T.ALT_ID) AS ALTERED_BY,
       t2.note_text AS REASON_FOR_CHANGE,
       T.UCF_ORDER_DATE3 AS RELEASE_DATE,
       T2.SALES_ORDER,
       T.SECURITY_GROUP,
       T2.PW_ORDER_NO,
       T2.SALES_ORDER_CREATE_DATE,
       T2.SALES_ORDER_CREATE_BY,
       T2.SALES_ORDER_RELEASE_DATE,
       T2.SALES_ORDER_RELEASED_BY,
       T2.Pw_Order_Type,
       t2.pw_sub_order_type
FROM SFWID_ORDER_DESC T, PWUST_SFWID_ORDER_DESC T2
WHERE T.ORDER_ID = T2.ORDER_ID
AND T2.PW_ORDER_NO NOT LIKE 'E%';--Defect 1739
grant select on SFMFG.PWUST_REPAIR_SUP_WO_BOTTOM_V to PRATT_READ_ONLY;
grant select, insert, update, delete on SFMFG.PWUST_REPAIR_SUP_WO_BOTTOM_V to SF$CONNECTION_POOL;

