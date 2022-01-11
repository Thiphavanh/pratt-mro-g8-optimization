CREATE OR REPLACE VIEW PWUST_SFWID_SCOPE_V AS
SELECT   /*+USE_MERGE(A,B) */
          a.ORDER_ID,
          a.ucf_order_vch12,
          b.OPER_NO,
          b.STEP_NO,
          'STEP' as low_nav_lvl,
          (select tt.pw_order_no from pwust_sfwid_order_desc tt where tt.order_id = t.order_id) as ORDER_NO,
          a.PART_NO,
          a.PART_CHG,
          a.ITEM_TYPE,
          a.ITEM_SUBTYPE,
          a.PROGRAM,
          (SELECT TT.SALES_ORDER FROM PWUST_SFWID_ORDER_DESC TT WHERE TT.ORDER_ID = T.ORDER_ID) AS UCF_ORDER_VCH2,
          (SELECT TT.ENGINE_MODEL FROM PWUST_SFWID_ORDER_DESC TT WHERE TT.ORDER_ID = T.ORDER_ID) AS ENGINE_MODEL,
          b.OPER_KEY,
          b.STEP_KEY,
          a.ORDER_STATUS,
          a.ORDER_HOLD_STATUS,
          o.oper_status,
          b.OPER_HOLD_STATUS,
          a.parent_order_id,
          a.orig_order_id,
          a.superceded_order_id,
          a.supercedes_order,
          A.ALT_STATUS,
          A.ALT_ID,
          a.plan_id,
          TO_NUMBER (NULL) as oper_updt_no,
          TO_NUMBER (NULL) as step_updt_no,
          CASE WHEN E.DISC_ID IS NULL THEN D.disc_id ELSE E.DISC_ID END AS DISC_ID,
          CASE WHEN E.DISC_LINE_NO IS NULL THEN D.disc_line_no ELSE E.DISC_LINE_NO END AS DISC_LINE_NO,
          D.ca_id,
          NULL as node_id,
          NULL as ca_resoln_id,
          NULL as ca_request_id,
          A.item_id,
          A.lot_flag,
          A.serial_flag,
          TO_NUMBER (NULL) as plan_version,
          TO_NUMBER (NULL) as plan_revision,
          TO_NUMBER (NULL) as plan_alterations,
          A.EXTERNAL_PLM_NO AS ORDER_EXTERNAL_PLM_NO,
          A.EXTERNAL_ERP_NO AS ORDER_EXTERNAL_ERP_NO,
          B.EXTERNAL_PLM_NO AS OPERSTEP_EXTERNAL_PLM_NO,
          B.EXTERNAL_ERP_NO AS OPERSTEP_EXTERNAL_ERP_NO,
          order_qty,
          b.SEQ_STEPS_FLAG,
          b.EXE_ORDER,
          a.doc_type,
          a.order_type,
          a.security_group,
          a.bom_no,
          a.mfg_bom_chg,
          b.included,
          a.DISPLAY_SEQUENCE,
          t.TEXT_TYPE,
          a.INVENTORY_STOCK_LOC,
          a.ACCOUNT_LABOR,
          a.ACCOUNT_MATERIAL,
          A.ASGND_LOCATION_ID AS ORDER_ASGND_LOCATION_ID,
          L1.WORK_LOC AS ORDER_ASGND_WORK_LOC,
          b.ASGND_LOCATION_ID AS OPER_ASGND_LOCATION_ID,
          L2.WORK_LOC AS OPER_ASGND_WORK_LOC,
          A.INSTRUCTIONS_TYPE,
          A.OPERATION_OVERLAP_FLAG,
          A.SPLIT_FROM_ORDER_ID,
          A.BOM_ID,
          A.INSP_ORDER_ID,
          A.DECLARED_LANGUAGE_CODE,
          A.EXPLICIT_BOM_LINK_FLAG,
          A.COMMODITY_JURISDICTION,
          A.COMMODITY_CLASSIFICATION,
          A.ALIAS_PART_NO,
          A.ALIAS_PART_CHG,
          A.ALTER_LOCKED,
          A.UCF_ORDER_VCH15 AS REPAIR_MULTI_ORDER_KEY
FROM sfwid_order_desc a LEFT OUTER JOIN SFPL_MFG_BOM_REV  C
ON A.BOM_NO = C.BOM_NO
AND  A.MFG_BOM_CHG = C.MFG_BOM_CHG
LEFT JOIN SFQA_DISC_ITEM D
ON A.ORDER_ID = D.DISP_ORDER_ID
AND A.ALT_ID = D.ALT_ID
 LEFT OUTER JOIN SFFND_WORK_LOC_DEF L1 ON a.ASGND_LOCATION_ID = L1.LOCATION_ID,
    sfwid_oper_desc b LEFT OUTER JOIN SFFND_WORK_LOC_DEF L2 ON b.ASGND_LOCATION_ID = L2.LOCATION_ID,
    sfwid_oper_desc o
LEFT JOIN SFQA_DISC_ITEM E
ON O.ORDER_ID = E.DISP_ORDER_ID
AND O.ALT_ID = E.ALT_ID
AND E.Disc_Line_Status NOT IN('DISPOSITIONED','IMPLEMENTED','CANCEL'),
          sfwid_oper_text t
WHERE b.order_id = a.order_id
and b.order_id = o.order_id
and b.oper_key = o.oper_key
and o.step_key = -1
and b.order_id = t.order_id
and b.oper_key = t.oper_key
and b.step_key = t.step_key
and t.text_type in ('HEADER_PLANNING','PLANNING')
and (b.step_no is null or (b.step_no is not null and t.text_type = 'PLANNING'));
/

GRANT SELECT ON PWUST_SFWID_SCOPE_V TO pratt_read_only ;
GRANT SELECT ON PWUST_SFWID_SCOPE_V TO sf$connection_pool ;
