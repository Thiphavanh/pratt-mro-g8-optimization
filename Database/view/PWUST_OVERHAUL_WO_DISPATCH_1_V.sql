-- defect 766
CREATE OR REPLACE VIEW PWUST_OVERHAUL_WO_DISPATCH_1_V AS
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
AND T.ORDER_TYPE IN ('OVERHAUL','Supplemental','SUB ORDER')
AND T.ORDER_STATUS in ('IN QUEUE','ACTIVE','PENDING')
AND T1.SALES_ORDER IS NOT NULL
AND T.Program IS NOT NULL
AND T.asgnd_location_id IS NOT NULL
AND T1.ENGINE_MODEL  IS NOT NULL;
/

GRANT SELECT ON PWUST_OVERHAUL_WO_DISPATCH_1_V TO pratt_read_only ;
GRANT SELECT ON PWUST_OVERHAUL_WO_DISPATCH_1_V TO sf$connection_pool ;
