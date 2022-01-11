-------------------------------------------------
-- Export file for user SFMFG                  --
-- Created by X006640 on 6/25/2020, 5:04:39 PM --
-------------------------------------------------

set define off

prompt
prompt Creating view PWUST_OVERHAUL_WO_DISPATCH_V
prompt ==========================================
prompt
CREATE OR REPLACE FORCE VIEW SFMFG.PWUST_OVERHAUL_WO_DISPATCH_V AS
SELECT distinct
       T2.SALES_ORDER AS SALES_ORDER,
       T.Program AS ENGINE_TYPE,
       T2.ENGINE_MODEL AS ENGINE_MODEL,
       (select tt.work_loc from sffnd_work_loc_def tt where tt.location_id = t.asgnd_location_id) as work_loc,
       (select cc.CUSTOMER from pwust_sales_order cc where cc.sales_order = t2.sales_order and rownum = 1) AS CUSTOMER_NO,
       (select cc1.customer_desc from pwust_sales_order cc1 where cc1.sales_order = t2.sales_order and rownum = 1) CUSTOMER_DESC,
       (select cc2.SERIAL_NO from pwust_sales_order cc2 where cc2.sales_order = t2.sales_order and rownum = 1) SERIAL_NO,
       '' AS NEW_SALES_ORDER,
       '' AS NEW_ENGINE_TYPE,
       '' AS NEW_ENGINE_MODEL,
       '' AS NEW_SAP_WORK_LOC,
       '' AS NEW_WORK_SCOPE,
       '' AS NEW_Sup_Net_Act,
       '' AS NEW_Sub_Net_Act,
       '' AS NEW_CUSTOMER_NO,
       '' AS NEW_PLANED_ORDER,
       T.SECURITY_GROUP AS SECURITY_GROUP,
       t.order_no
FROM SFWID_ORDER_DESC T, PWUST_SFWID_ORDER_DESC T2
WHERE T.ORDER_ID = T2.ORDER_ID
AND T.ORDER_TYPE IN ('OVERHAUL','Supplemental','SUB ORDER')
AND T2.SALES_ORDER IS NOT NULL
AND T2.SALES_ORDER NOT IN (SELECT DISTINCT SALES_ORDER FROM PWUST_SFWID_ORDER_DESC WHERE PW_ORDER_TYPE = 'REPAIR')--Defect 1704
AND T.Program IS NOT NULL
AND T.asgnd_location_id IS NOT NULL
AND T2.ENGINE_MODEL  IS NOT NULL
union
select distinct
       t1.sales_order,
       T1.ENGINE_TYPE,
       t1.engine_model,
       t1.sapworkloc,
       (select cc.CUSTOMER from pwust_sales_order cc where cc.sales_order = t1.sales_order and rownum = 1) AS CUSTOMER_NO,
       (select cc1.customer_desc from pwust_sales_order cc1 where cc1.sales_order = t1.sales_order and rownum = 1) CUSTOMER_DESC,
       (select cc2.SERIAL_NO from pwust_sales_order cc2 where cc2.sales_order = t1.sales_order and rownum = 1) SERIAL_NO,
       '' AS NEW_SALES_ORDER,
       '' AS NEW_ENGINE_TYPE,
       '' AS NEW_ENGINE_MODEL,
       '' AS NEW_SAP_WORK_LOC,
       '' AS NEW_WORK_SCOPE,
       '' AS NEW_Sup_Net_Act,
       '' AS NEW_Sub_Net_Act,
       '' AS NEW_CUSTOMER_NO,
       'Y' AS NEW_PLANED_ORDER,
       '' as SECURITY_GROUP ,
       '' as order_no
from PWUST_PLANNED_ORDERS t1;
grant select on SFMFG.PWUST_OVERHAUL_WO_DISPATCH_V to PRATT_READ_ONLY;
grant select, insert, update, delete on SFMFG.PWUST_OVERHAUL_WO_DISPATCH_V to SF$CONNECTION_POOL;



