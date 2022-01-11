CREATE OR REPLACE VIEW PWUST_ORDERHEADERTABSEL_V AS
SELECT ALT.ALT_NO,
       TT.ORDER_ID,
       TT.PW_ORDER_NO AS ORDER_NO,
       TT.SALES_ORDER,
       TT.ENGINE_MODEL,
       TT.WORK_SCOPE,
       TT.SUPERIORNET,
       TT.SUBNET,
      -- defect 1874 start
      (SELECT CC.CUSTOMER_NUMBER FROM SFMFG.PWUST_INT_SAP_SM_ORDER CC WHERE CC.SALES_ORDER =  TT.SALES_ORDER AND ROWNUM = 1) AS CUSTOMER,
      (SELECT CC1.CUSTOMER_NAME FROM SFMFG.PWUST_INT_SAP_SM_ORDER CC1 WHERE CC1.SALES_ORDER = TT.SALES_ORDER AND ROWNUM = 1) CUSTOMER_DESC,
      -- defect 1874 start
      -- defect 726 start
      (SELECT CC2.SERIAL_NO FROM PWUST_SALES_ORDER CC2 WHERE CC2.SALES_ORDER = TT.SALES_ORDER AND ROWNUM = 1) SAP_SERIAL_NO,
       -- defect 726 end
       A.PLAN_ID,
       A.PLAN_NO,
       A.PLAN_VERSION,
       A.PLAN_REVISION,
       A.PLAN_ALTERATIONS,
       A.PLAN_UPDT_NO,
       A.UPDT_USERID,
       A.TIME_STAMP,
       A.LAST_ACTION,
       A.ORDER_STATUS,
       A.ORDER_TYPE,
       A.ORDER_HOLD_STATUS,
       LOC.WORK_LOC AS ASGND_WORK_LOC,
       A.ORDER_CUST_ID,
       CUST.CUST_DESC AS CUST_DESC,
       A.PARENT_ORDER_ID,
       A.SCHED_PRIORITY,
       A.INITIAL_STORES,
       A.FINAL_STORES,
       A.CONTRACT_NO,
       A.ORIG_ORDER_ID,
       A.SUPERCEDED_ORDER_ID,
       NVL(A.SPLIT_FLAG, 'N') AS SPLIT_FLAG,
       A.SCHED_START_DATE,
       A.SCHED_END_DATE,
       A.REVISED_START_DATE,
       A.REVISED_END_DATE,
       A.ACTUAL_START_DATE,
       A.ACTUAL_END_DATE,
       A.UNIT_NO,
       A.CUSTOMER_ORDER_NO,
       A.PLAN_TYPE,
       A.ALTER_TYPE,
       A.SUPERCEDES_ORDER,
       A.STATUS_CHG_NOTES,
       NVL(A.SERIAL_FLAG, 'N') AS SERIAL_FLAG,
       NVL(A.LOT_FLAG, 'N') AS LOT_FLAG,
       A.ITEM_ID,
       A.PART_NO,
       A.PART_CHG,
       A.ITEM_TYPE,
       A.ITEM_SUBTYPE,
       A.PROGRAM,
       A.MFG_BOM_CHG,
       A.MODEL,
       A.PLAN_TITLE,
       A.ORDER_UOM,
       A.UNIT_TYPE,
       A.PROJECT,
       A.ALT_ID,
       ALT_COUNT,
       A.ALT_STATUS,
       A.ENG_PART_NO,
       A.ENG_PART_CHG,
       A.ENG_GROUP,
       A.MFG_INDEX_NO,
       A.PLG_GROUP,
       A.LTA_SEND_FLAG,
       A.NEEDS_REVIEW_FLAG,
       A.STATUS_CHG_REASON,
       A.ORDER_QTY,
       NVL(A.ORDER_SCRAP_QTY, 0) AS ORDER_SCRAP_QTY,
       NVL(A.ORDER_COMPLETE_QTY, 0) AS ORDER_COMPLETE_QTY,
       NVL(A.ORDER_STOP_QTY, 0) AS ORDER_STOP_QTY,
       CASE  WHEN (A.ORDER_QTY -(NVL(A.ORDER_SCRAP_QTY, 0) + NVL(A.ORDER_COMPLETE_QTY, 0) + NVL(A.ORDER_STOP_QTY, 0))) <= 0 THEN 0 ELSE A.ORDER_QTY - (NVL(A.ORDER_SCRAP_QTY, 0) + NVL(A.ORDER_COMPLETE_QTY, 0) +  NVL(A.ORDER_STOP_QTY, 0)) END AS ORDER_INPROCESS_QTY,
       A.EXTERNAL_PLM_NO,
       A.EXTERNAL_ERP_NO,
       A.CONDITION,
       A.UCF_PLAN_VCH1,
       A.UCF_PLAN_VCH2,
       A.UCF_PLAN_VCH3,
       A.UCF_PLAN_VCH4,
       A.UCF_PLAN_VCH5,
       A.UCF_PLAN_VCH6,
       A.UCF_PLAN_VCH7,
       A.UCF_PLAN_VCH8,
       A.UCF_PLAN_VCH9,
       A.UCF_PLAN_VCH10,
       A.UCF_PLAN_VCH11,
       A.UCF_PLAN_VCH12,
       A.UCF_PLAN_VCH13,
       A.UCF_PLAN_VCH14,
       A.UCF_PLAN_VCH15,
       A.UCF_PLAN_VCH255_1,
       A.UCF_PLAN_VCH255_2,
       A.UCF_PLAN_VCH255_3,
       A.UCF_PLAN_VCH4000_1,
       A.UCF_PLAN_VCH4000_2,
       A.UCF_PLAN_FLAG1,
       A.UCF_PLAN_FLAG2,
       A.UCF_PLAN_FLAG3,
       A.UCF_PLAN_FLAG4,
       A.UCF_PLAN_FLAG5,
       A.UCF_PLAN_NUM1,
       A.UCF_PLAN_NUM2,
       A.UCF_PLAN_NUM3,
       A.UCF_PLAN_NUM4,
       A.UCF_PLAN_NUM5,
       A.UCF_PLAN_DATE1,
       A.UCF_PLAN_DATE2,
       A.UCF_PLAN_DATE3,
       A.UCF_PLAN_DATE4,
       A.UCF_PLAN_DATE5,
       A.UCF_ORDER_VCH1,
       TT.SALES_ORDER AS UCF_ORDER_VCH2,
       A.UCF_ORDER_VCH3,
       A.UCF_ORDER_VCH4,
       A.UCF_ORDER_VCH5,
       A.UCF_ORDER_VCH6,
       A.UCF_ORDER_VCH7,
       TT.ENGINE_MODEL AS UCF_ORDER_VCH8,
       A.UCF_ORDER_VCH9,
       A.UCF_ORDER_VCH10,
       A.UCF_ORDER_VCH11,
       A.UCF_ORDER_VCH12,
       A.UCF_ORDER_VCH13,
       A.UCF_ORDER_VCH14,
       A.UCF_ORDER_VCH15,
       A.UCF_ORDER_VCH255_1,
       A.UCF_ORDER_VCH255_2,
       A.UCF_ORDER_VCH255_3,
       --Defect 1416
       TT.Note_Text AS UCF_ORDER_VCH4000_1,
       A.UCF_ORDER_VCH4000_2,
       A.UCF_ORDER_FLAG1,
       A.UCF_ORDER_FLAG2,
       A.UCF_ORDER_FLAG3,
       A.UCF_ORDER_FLAG4,
       A.UCF_ORDER_FLAG5,
       A.UCF_ORDER_NUM1,
       A.UCF_ORDER_NUM2,
       A.UCF_ORDER_NUM3,
       A.UCF_ORDER_NUM4,
       A.UCF_ORDER_NUM5,
       A.UCF_ORDER_DATE1,
       A.UCF_ORDER_DATE2,
       A.UCF_ORDER_DATE3,
       A.UCF_ORDER_DATE4,
       A.UCF_ORDER_DATE5,
       A.BOM_NO,
       A.SECURITY_GROUP,
       A.DISPLAY_SEQUENCE,
       A.ACCOUNT_LABOR,
       A.OPERATION_OVERLAP_FLAG,
       A.ALIAS_PART_NO,
       A.ALIAS_PART_CHG,
       LANG.LANGUAGE_CODE,
       LANG.LANGUAGE_NAME,
       COALESCE(A.ASGND_LOCATION_ID, '''''') AS LOCATION_ID,
       A.EXPLICIT_BOM_LINK_FLAG,
       (SELECT LISTAGG(D.CONFIG_NAME, ',') WITHIN  GROUP( ORDER BY D.CONFIG_NAME) FROM SFWID_ORDER_CONFIG C, SFFND_CONFIG_DEF D WHERE A.ORDER_ID = C.ORDER_ID AND D.CONFIG_ID = C.CONFIG_ID) AS CONFIG_NAME,
       A.COMMODITY_JURISDICTION,
       A.COMMODITY_CLASSIFICATION,
       -- defect 1026 start
       (SELECT I.STOCK_UOM FROM SFPL_ITEM_PROGRAM_DETAILS I  WHERE I.ITEM_ID = A.ITEM_ID AND I.PROGRAM = 'ANY' AND I.LOCATION_ID = 'ANY') AS  STOCK_UOM,
       -- defect 1026 end
       --defect 1406 start
       TT.OUTGOING_PART_NO,
       --defect 1406 end
       --defect 1392 begin
       TT.PW_SUB_ORDER_TYPE,
       --defect 1392 end
       --begin defect 1984
       TT.Sap_Lot_Code
       --end defect 1984
FROM SFWID_ORDER_DESC A
LEFT OUTER JOIN SFFND_WORK_LOC_DEF      LOC     ON A.ASGND_LOCATION_ID = LOC.LOCATION_ID
LEFT OUTER JOIN SFFND_CUST_ID_DEF       CUST    ON A.ORDER_CUST_ID = CUST.CUST_ID
LEFT OUTER JOIN SFWID_ALTERATION_DESC   ALT     ON A.ALT_ID = ALT.ALT_ID
LEFT OUTER JOIN PWUST_SFWID_ORDER_DESC  TT      ON A.ORDER_ID = TT.ORDER_ID,
SFFND_LANGUAGE LANG
WHERE A.DECLARED_LANGUAGE_CODE = LANG.LANGUAGE_CODE;