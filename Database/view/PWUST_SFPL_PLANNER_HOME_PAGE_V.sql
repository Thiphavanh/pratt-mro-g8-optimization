CREATE OR REPLACE VIEW PWUST_SFPL_PLANNER_HOME_PAGE_V AS
SELECT DOC_TYPE,
       DOCUMENT_NUMBER,
       VER,
       REV,
       DOCUMENT_ID,
       OWNER_CODE,
       QUEUE_TYPE,
       STATUS,
       DOCUMENT_STATUS,
       PRIORITY,
       SCH_START_DATE,
       SCH_END_DATE,
       ASSIGNED_TO_USERID,
       ASSIGNED_FLAG,
       NOTES,
       TASK_ID,
       TIME_STAMP,
       SECURITY_GROUP
  FROM (SELECT PD.DOC_TYPE,
               CASE
                 WHEN PD.DOC_TYPE = 'Standard Operation' THEN
                  (SELECT S.STDOPER_TAG
                     FROM SFFND_STDOPER S
                    WHERE S.STDOPER_PLAN_ID = PR.PLAN_ID
                      AND S.STDOPER_PLAN_VER = PR.PLAN_VERSION
                      AND S.STDOPER_PLAN_REV = PR.PLAN_REVISION
                      AND S.STDOPER_PLAN_ALT = PR.PLAN_ALTERATIONS)
                 ELSE
                  PD.PLAN_NO
               END AS DOCUMENT_NUMBER,
               PR.PLAN_VERSION AS VER,
               PR.PLAN_REVISION AS REV,
               PR.PLAN_ID AS DOCUMENT_ID,
               NULL AS OWNER_CODE,
               T.QUEUE_TYPE,
               T.STATUS,
               T.PRIORITY,
               T.SCH_START_DATE,
               T.SCH_END_DATE,
               T.ASSIGNED_TO_USERID,
               T.ASSIGNED_FLAG,
               T.NOTES,
               T.TASK_ID,
               T.TIME_STAMP,
               PD.SECURITY_GROUP,
               CASE
                 WHEN T.ASSIGNED_FLAG IS NULL THEN
                  'PENDING'
                 WHEN T.ASSIGNED_FLAG = 'A' THEN
                  'ASSIGNED'
                 WHEN T.ASSIGNED_FLAG = 'C' THEN
                  'CLAIMED'
               END AS DOCUMENT_STATUS
          FROM SFFND_TASK     T,
               SFFND_PLG_TASK PT,
               SFPL_PLAN_REV  PR,
               SFPL_PLAN_DESC PD
         WHERE T.TASK_ID = PT.TASK_ID
           AND T.STATUS IN ('IN QUEUE', 'ACTIVE')
           AND PT.PLAN_ID = PR.PLAN_ID
           AND PT.PLAN_VERSION = PR.PLAN_VERSION
           AND PT.PLAN_REVISION = PR.PLAN_REVISION
           AND PT.PLAN_ALTERATIONS = PR.PLAN_ALTERATIONS
           AND PR.PLAN_ID = PD.PLAN_ID
           AND PR.PLAN_UPDT_NO = PD.PLAN_UPDT_NO
        UNION ALL
        SELECT DOC_TYPE,
               PW_ORDER_NO || '/' || CASE
                 WHEN ALT_NO LIKE SFCORE_LICENSE_PREFIX() || '_%' THEN
                  'ALT' || ROW_NO
                 ELSE
                  ALT_NO
               END AS DOCUMENT_NUMBER,
               NULL AS VER,
               ALT_REV AS REV,
               ALT_ID AS DOCUMENT_ID,
               NULL AS OWNER_CODE,
               QUEUE_TYPE,
               STATUS,
               PRIORITY,
               SCH_START_DATE,
               SCH_END_DATE,
               ASSIGNED_TO_USERID,
               ASSIGNED_FLAG,
               NOTES,
               TASK_ID,
               TIME_STAMP,
               SECURITY_GROUP,
               CASE
                 WHEN ASSIGNED_FLAG IS NULL THEN
                  'PENDING'
                 WHEN ASSIGNED_FLAG = 'A' THEN
                  'ASSIGNED'
                 WHEN ASSIGNED_FLAG = 'C' THEN
                  'CLAIMED'
               END AS DOCUMENT_STATUS
          FROM (SELECT OD.DOC_TYPE,
                       O.PW_ORDER_NO,
                       AD.ALT_NO,
                       AD.ALT_ID,
                       AD.ALT_REV,
                       OD.ORDER_ID,
                       T.QUEUE_TYPE,
                       T.STATUS,
                       T.PRIORITY,
                       T.SCH_START_DATE,
                       T.SCH_END_DATE,
                       T.ASSIGNED_TO_USERID,
                       T.NOTES,
                       T.TASK_ID,
                       T.TIME_STAMP,
                       OD.SECURITY_GROUP,
                       T.ASSIGNED_FLAG,
                       ROW_NUMBER() OVER(PARTITION BY OD.ORDER_ID ORDER BY OD.ORDER_ID, T.TIME_STAMP) AS ROW_NO
                  FROM SFFND_TASK             T,
                       SFFND_ALTERATION_TASK  A,
                       SFWID_ALTERATION_DESC  AD,
                       SFWID_ORDER_DESC       OD,
                       PWUST_SFWID_ORDER_DESC O
                 WHERE T.TASK_ID = A.TASK_ID
                   AND T.STATUS IN ('IN QUEUE', 'ACTIVE')
                   AND A.ALT_ID = AD.ALT_ID
                   AND A.ORDER_ID = OD.ORDER_ID
                   AND OD.ORDER_ID = O.ORDER_ID)
        UNION ALL
        SELECT BR.BOM_TYPE,
               BR.BOM_NO AS DOCUMENT_NUMBER,
               NULL AS VER,
               NULL AS REV,
               BR.BOM_ID AS DOCUMENT_ID,
               NULL AS OWNER_CODE,
               T.QUEUE_TYPE,
               T.STATUS,
               T.PRIORITY,
               T.SCH_START_DATE,
               T.SCH_END_DATE,
               T.ASSIGNED_TO_USERID,
               T.ASSIGNED_FLAG,
               T.NOTES,
               T.TASK_ID,
               T.TIME_STAMP,
               BR.SECURITY_GROUP,
               CASE
                 WHEN T.ASSIGNED_FLAG IS NULL THEN
                  'PENDING'
                 WHEN T.ASSIGNED_FLAG = 'A' THEN
                  'ASSIGNED'
                 WHEN T.ASSIGNED_FLAG = 'C' THEN
                  'CLAIMED'
               END AS DOCUMENT_STATUS
          FROM SFFND_TASK       T,
               SFFND_BOM_TASK   BT,
               SFPL_MFG_BOM_REV BR
         WHERE T.TASK_ID = BT.TASK_ID
           AND T.STATUS IN ('IN QUEUE', 'ACTIVE')
           AND BT.BOM_ID = BR.BOM_ID
        UNION ALL
        SELECT DI.DOC_TYPE,
               DI.DISC_ID AS DOCUMENT_NUMBER,
               DI.DISC_LINE_NO AS VER,
               NULL AS REV,
               DI.DISC_ID AS DOCUMENT_ID,
               NULL AS OWNER_CODE,
               T.QUEUE_TYPE,
               T.STATUS,
               T.PRIORITY,
               T.SCH_START_DATE,
               T.SCH_END_DATE,
               T.ASSIGNED_TO_USERID,
               T.ASSIGNED_FLAG,
               T.NOTES,
               T.TASK_ID,
               T.TIME_STAMP,
               DI.SECURITY_GROUP,
               CASE
                 WHEN T.ASSIGNED_FLAG IS NULL THEN
                  'PENDING'
                 WHEN T.ASSIGNED_FLAG = 'A' THEN
                  'ASSIGNED'
                 WHEN T.ASSIGNED_FLAG = 'C' THEN
                  'CLAIMED'
               END AS DOCUMENT_STATUS
          FROM SFFND_TASK             T,
               SFFND_DISPOSITION_TASK DT,
               SFQA_DISC_ITEM         DI
         WHERE T.TASK_ID = DT.TASK_ID
           AND T.STATUS IN ('IN QUEUE', 'ACTIVE')
           AND DT.DISC_ID = DI.DISC_ID
           AND DT.DISC_LINE_NO = DI.DISC_LINE_NO
        UNION ALL
        SELECT IDR.DOC_TYPE,
               CASE
                 WHEN O.OWNER_TYPE = 'INTERNAL' THEN
                  I.PART_NO || ' / ' || I.PART_CHG
                 WHEN O.OWNER_TYPE = 'SUPPLIER' THEN
                  O.OWNER_NAME || ' / ' || I.PART_NO || ' / ' || I.PART_CHG
                 WHEN O.OWNER_TYPE = 'MANDATE' THEN
                  O.OWNER_NAME
               END AS DOCUMENT_NUMBER,
               NULL AS VER,
               IDR.INSP_DEF_REV AS REV,
               IDR.ITEM_ID AS DOCUMENT_ID,
               IDR.OWNER_CODE AS OWNER_CODE,
               T.QUEUE_TYPE,
               T.STATUS,
               T.PRIORITY,
               T.SCH_START_DATE,
               T.SCH_END_DATE,
               T.ASSIGNED_TO_USERID,
               T.ASSIGNED_FLAG,
               T.NOTES,
               T.TASK_ID,
               T.TIME_STAMP,
               IDR.SECURITY_GROUP,
               CASE
                 WHEN T.ASSIGNED_FLAG IS NULL THEN
                  'PENDING'
                 WHEN T.ASSIGNED_FLAG = 'A' THEN
                  'ASSIGNED'
                 WHEN T.ASSIGNED_FLAG = 'C' THEN
                  'CLAIMED'
               END AS DOCUMENT_STATUS
          FROM SFFND_TASK                 T,
               SFFND_INSP_DEFINITION_TASK IDT,
               SFSQA_INSP_DEFINITION_REV  IDR,
               SFPL_ITEM_DESC_MASTER_ALL  I,
               SFSQA_OWNER                O
         WHERE T.TASK_ID = IDT.TASK_ID
           AND T.STATUS IN ('IN QUEUE', 'ACTIVE')
           AND IDT.ITEM_ID = IDR.ITEM_ID
           AND IDT.OWNER_CODE = IDR.OWNER_CODE
           AND IDT.INSP_DEF_REV = IDR.INSP_DEF_REV
           AND I.ITEM_ID = IDR.ITEM_ID
           AND O.OWNER_CODE = IDR.OWNER_CODE
        UNION ALL
        SELECT IPD.DOC_TYPE,
               IPD.INSP_PLAN_NO AS DOCUMENT_NUMBER,
               NULL AS VER,
               IPD.INSP_PLAN_REV AS REV,
               IPD.INSP_PLAN_ID AS DOCUMENT_ID,
               NULL AS OWNER_CODE,
               T.QUEUE_TYPE,
               T.STATUS,
               T.PRIORITY,
               T.SCH_START_DATE,
               T.SCH_END_DATE,
               T.ASSIGNED_TO_USERID,
               T.ASSIGNED_FLAG,
               T.NOTES,
               T.TASK_ID,
               T.TIME_STAMP,
               '' AS SECURITY_GROUP,
               CASE
                 WHEN T.ASSIGNED_FLAG IS NULL THEN
                  'PENDING'
                 WHEN T.ASSIGNED_FLAG = 'A' THEN
                  'ASSIGNED'
                 WHEN T.ASSIGNED_FLAG = 'C' THEN
                  'CLAIMED'
               END AS DOCUMENT_STATUS
          FROM SFFND_TASK           T,
               SFFND_INSP_PLAN_TASK IPT,
               SFSQA_INSP_PLAN_DESC IPD
         WHERE T.TASK_ID = IPT.TASK_ID
           AND T.STATUS IN ('IN QUEUE', 'ACTIVE')
           AND IPT.INSP_PLAN_ID = IPD.INSP_PLAN_ID
           AND IPT.INSP_PLAN_REV = IPD.INSP_PLAN_REV
        UNION ALL
        SELECT CD.DOC_TYPE,
               CD.CA_ID AS DOCUMENT_NUMBER,
               NULL AS VER,
               NULL AS REV,
               CD.CA_ID AS DOCUMENT_ID,
               NULL AS OWNER_CODE,
               T.QUEUE_TYPE,
               T.STATUS,
               T.PRIORITY,
               T.SCH_START_DATE,
               T.SCH_END_DATE,
               T.ASSIGNED_TO_USERID,
               T.ASSIGNED_FLAG,
               T.NOTES,
               T.TASK_ID,
               T.TIME_STAMP,
               '' AS SECURITY_GROUP,
               CASE
                 WHEN T.ASSIGNED_FLAG IS NULL THEN
                  'PENDING'
                 WHEN T.ASSIGNED_FLAG = 'A' THEN
                  'ASSIGNED'
                 WHEN T.ASSIGNED_FLAG = 'C' THEN
                  'CLAIMED'
               END AS DOCUMENT_STATUS
          FROM SFFND_TASK    T,
               SFFND_CA_TASK CT,
               SFQA_CA_DESC  CD
         WHERE T.TASK_ID = CT.TASK_ID
           AND T.STATUS IN ('IN QUEUE', 'ACTIVE')
           AND CT.CA_ID = CD.CA_ID
        UNION ALL
        SELECT MO.DOC_TYPE,
               MO.OBJECT_TAG AS DOCUMENT_NUMBER,
               NULL AS VER,
               TO_NUMBER(MO.OBJECT_REV) AS REV,
               MO.OBJECT_ID AS DOCUMENT_ID,
               NULL AS OWNER_CODE,
               T.QUEUE_TYPE,
               T.STATUS,
               T.PRIORITY,
               T.SCH_START_DATE,
               T.SCH_END_DATE,
               T.ASSIGNED_TO_USERID,
               T.ASSIGNED_FLAG,
               T.NOTES,
               T.TASK_ID,
               T.TIME_STAMP,
               MO.SECURITY_GROUP,
               CASE
                 WHEN T.ASSIGNED_FLAG IS NULL THEN
                  'PENDING'
                 WHEN T.ASSIGNED_FLAG = 'A' THEN
                  'ASSIGNED'
                 WHEN T.ASSIGNED_FLAG = 'C' THEN
                  'CLAIMED'
               END AS DOCUMENT_STATUS
          FROM SFFND_TASK       T,
               SFFND_MMOBJ_TASK MT,
               SFCORE_MM_OBJECT MO
         WHERE T.TASK_ID = MT.TASK_ID
           AND T.STATUS IN ('IN QUEUE', 'ACTIVE')
           AND MO.ACQUIRE_EXPRESS = 'N'
           AND MT.OBJECT_ID = MO.OBJECT_ID
        UNION ALL
        SELECT CR.DOC_TYPE,
               CR.CHANGE_NO AS DOCUMENT_NUMBER,
               NULL AS VER,
               NULL AS REV,
               CR.CHANGE_REQUEST_ID AS DOCUMENT_ID,
               NULL AS OWNER_CODE,
               T.QUEUE_TYPE,
               T.STATUS,
               T.PRIORITY,
               T.SCH_START_DATE,
               T.SCH_END_DATE,
               T.ASSIGNED_TO_USERID,
               T.ASSIGNED_FLAG,
               T.NOTES,
               T.TASK_ID,
               T.TIME_STAMP,
               '' AS SECURITY_GROUP,
               CASE
                 WHEN T.ASSIGNED_FLAG IS NULL THEN
                  'PENDING'
                 WHEN T.ASSIGNED_FLAG = 'A' THEN
                  'ASSIGNED'
                 WHEN T.ASSIGNED_FLAG = 'C' THEN
                  'CLAIMED'
               END AS DOCUMENT_STATUS
          FROM SFFND_TASK                T,
               SFFND_CHANGE_REQUEST_TASK CRT,
               SFPL_CHANGE_REQUEST       CR
         WHERE T.TASK_ID = CRT.TASK_ID
           AND T.STATUS IN ('IN QUEUE', 'ACTIVE')
           AND CRT.CHANGE_REQUEST_ID = CR.CHANGE_REQUEST_ID);
