CREATE OR REPLACE VIEW PWUST_PLANNED_ORDERS_V AS
SELECT T.SALES_ORDER ||'-'||T.PART_NO || '-XX' AS ORDER_NO,
       (SELECT A.PLAN_NO FROM SFPL_PLAN_DESC A WHERE A.PLAN_ID = T.PLAN_ID AND A.PLAN_UPDT_NO = T.PLAN_UPDT_NO ) AS PLAN_NO,
       (SELECT A.PLAN_TITLE FROM SFPL_PLAN_DESC A WHERE A.PLAN_ID = T.PLAN_ID AND A.PLAN_UPDT_NO = T.PLAN_UPDT_NO ) AS PLAN_TITLE,
       T.PRIMERY_KEY,
       T.SALES_ORDER,
       T.ENGINE_TYPE,
       T.ENGINE_MODEL,
       T.SAPWORKLOC,
       T.WORK_SCOPE,
       (SELECT T3.WORKSCOPE_TITLE FROM PWUST_WORKSCOPE_DEF  T3
        WHERE T3.WORKSCOPE = T.WORK_SCOPE AND T3.ENGINE_TYPE = T.ENGINE_TYPE AND T3.WORK_LOC = (SELECT T2.WORK_LOC FROM SFFND_WORK_LOC_DEF T2 WHERE T2.LOCATION_ID = T.SAPWORKLOC)) AS WORK_SCOPE_DESC,  -- PRIMARY KEY (WORKSCOPE, ENGINE_TYPE, WORK_LOC)
       T.ITEM_NO_SEL,
       T.SUPERIORNET,
        (SELECT T4.SUPERIOR_NETWORK_ACT_DESC FROM  PWUST_NETWORK_DEF T4 WHERE T4.ENGINE_TYPE = T.ENGINE_TYPE AND T4.SUPERIOR_NETWORK_ACT = T.SUPERIORNET AND T4.SUB_NETWORK_ACT = T.SUBNET) AS SUPERIORNET_DESC,
       T.SUBNET,
       (SELECT T4.SUB_NETWORK_ACT_DESC FROM  PWUST_NETWORK_DEF T4 WHERE T4.ENGINE_TYPE = T.ENGINE_TYPE AND T4.SUPERIOR_NETWORK_ACT = T.SUPERIORNET AND T4.SUB_NETWORK_ACT = T.SUBNET) AS SUBNET_DESC,
       T.CUSTOMER,
       T.PLAN_ID,
       T.PLAN_VERSION,
       T.PLAN_REVISION,
       T.PLAN_ALTERATIONS,
       T.PLAN_UPDT_NO,
       T.PART_NO,
       T.UPDT_USERID,
       T.TIME_STAMP,
       T.LAST_ACTION,
       '' AS  SECURITY_GROUP
FROM PWUST_PLANNED_ORDERS T
ORDER BY T.SALES_ORDER;
/


GRANT SELECT ON PWUST_PLANNED_ORDERS_V TO pratt_read_only ;
GRANT SELECT ON PWUST_PLANNED_ORDERS_V TO sf$connection_pool ;
