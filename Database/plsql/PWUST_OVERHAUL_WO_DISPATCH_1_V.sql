-------------------------------------------------
-- Export file for user SFMFG                  --
-- Created by X006640 on 7/10/2020, 1:40:43 PM --
-------------------------------------------------



prompt
prompt Creating view PWUST_OVERHAUL_WO_DISPATCH_1_V
prompt ============================================
prompt
CREATE OR REPLACE FORCE VIEW SFMFG.PWUST_OVERHAUL_WO_DISPATCH_1_V AS
SELECT distinct
       T1.SALES_ORDER AS SALES_ORDER,
       T.Program AS ENGINE_TYPE,
       T1.ENGINE_MODEL AS ENGINE_MODEL,
       (select ttt1.CUSTOMER from pwust_sales_order ttt1 where ttt1.sales_order = t1.sales_order and rownum = 1) AS CUSTOMER_NO,
       (select ttt2.cust_desc from  SFFND_CUST_ID_DEF  ttt2 where ttt2.cust_id = T.UCF_ORDER_VCH10) as CUSTOMER_DESC,
       (select ttt3.SERIAL_NO from pwust_sales_order ttt3 where ttt3.sales_order = t1.sales_order and rownum = 1) SERIAL_NO,
       T.SECURITY_GROUP,
       (select tt.work_loc from sffnd_work_loc_def tt where tt.location_id = t.asgnd_location_id) AS work_loc,
       t.order_no
FROM SFWID_ORDER_DESC T, PWUST_SFWID_ORDER_DESC T1
WHERE T.ORDER_ID = T1.ORDER_ID
AND T.ORDER_STATUS in ('IN QUEUE','ACTIVE','PENDING')
AND ((T.ORDER_TYPE IN ('OVERHAUL'))
	OR ((T.ORDER_TYPE IN ('Supplemental','SUB ORDER'))
		AND (T1.SALES_ORDER IN (SELECT DISTINCT SALES_ORDER FROM PWUST_SFWID_ORDER_DESC WHERE PW_ORDER_TYPE = 'OVERHAUL'))))--Defect 1704
AND T1.SALES_ORDER IS NOT NULL
AND T.Program IS NOT NULL
AND T.asgnd_location_id IS NOT NULL
AND T1.ENGINE_MODEL  IS NOT NULL;
grant select on SFMFG.PWUST_OVERHAUL_WO_DISPATCH_1_V to PRATT_READ_ONLY;
grant select, insert, update, delete on SFMFG.PWUST_OVERHAUL_WO_DISPATCH_1_V to SF$CONNECTION_POOL;


