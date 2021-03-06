CREATE OR REPLACE VIEW PWUST_INT_SAP_SM_ORDER_BOTT_V AS
SELECT  T.IDOC_NUMBER,
        T.NOTIFICATION, 
        T.SALES_ORDER,
        T.SALES_DOC_ITEM,
        T.SUPERIOR_SM_ORDER,
        T.SM_ORDER,
        T.CUSTOMER_NUMBER,
        T.CUSTOMER_NAME,
        T.PURCHASE_ORDER,
        T.QUOTE_REQUIRED,
        T.REQUIRED_DELIVERY_DATE,
        T.ENGINE_MODEL,
        T.ENGINE_SERIAL_NUMBER,
        T.ENGINE_SECTION,
        T.ENGINE_SECTION_DESC,
        T.INCOMING_MATERIAL,
        T.OUTGOING_MATERIAL,
        T.QUANTITY,
        T.LOT_CODE,
        T.ORDER_ID,
        T.SOL_ORD_STATUS,
        T.ORDER_CREATE_DATE,
        T.SOL_ERROR_TEXT,
        T.UPDT_USERID,
        T.TIME_STAMP,
        T.LAST_ACTION, 
       -- PWUST_APPEND_FIELD('SELECT TT.SERIAL_NUMBER FROM PWUI_INT_REPAIR_WO_SERIALS TT WHERE TT.PK_VALUE = ' ||  T.PK_VALUE ||'AND TT.SM_ORDER = ' || CHR(39) || T.SM_ORDER || CHR(39), ';') AS SERIAL_NUMBERS,
        (SELECT COUNT(*) FROM PWUST_INT_SAP_SM_SERIALS  T1 WHERE T1.IDOC_NUMBER  = T.IDOC_NUMBER AND T1.SM_ORDER = T.SM_ORDER) AS SERIAL_CNT
FROM PWUST_INT_SAP_SM_ORDER T;
/
GRANT SELECT ON PWUST_INT_SAP_SM_ORDER_BOTT_V TO PRATT_READ_ONLY ;
GRANT SELECT ON PWUST_INT_SAP_SM_ORDER_BOTT_V TO SF$CONNECTION_POOL;