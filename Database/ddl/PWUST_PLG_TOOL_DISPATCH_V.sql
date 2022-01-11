--------------------------------------------------
-- Export file for user SFMFG                   --
-- Created by X006134 on 7/30/2021, 10:28:45 AM --
--------------------------------------------------

set define off

prompt
prompt Creating view PWUST_PLG_TOOL_DISPATCH_V
prompt =======================================
prompt
CREATE OR REPLACE FORCE VIEW PWUST_PLG_TOOL_DISPATCH_V AS
(SELECT
A.PART_NO,
A.PART_NO AS ITEM_NO,
A.PART_NO AS ITEM_NO_NEW,
A.PART_CHG,
A.PART_CHG AS ITEM_REV,
A.ITEM_TYPE,
A.ITEM_SUBTYPE,
A.COMMODITY_JURISDICTION,
A.COMMODITY_CLASSIFICATION,
A.PROGRAM,A.WORK_LOC,A.PART_TITLE,A.STOCK_UOM,A.WO_SERIAL_FLAG,
A.WO_LOT_FLAG,A.COMP_SERIAL_FLAG, A.COMP_LOT_FLAG,A.EXP_FLAG,A.SPOOL_FLAG,A.ITEM_ID,
A.UPDT_USERID,A.TIME_STAMP,A.LAST_ACTION,A.UCF_ITEM_VCH1,A.UCF_ITEM_VCH2,
A.UCF_ITEM_NUM1,A.UCF_ITEM_FLAG1,
A.UCF_ITEM_VCH3,A.UCF_ITEM_VCH4,A.UCF_ITEM_VCH5,A.UCF_ITEM_VCH6,A.UCF_ITEM_VCH7,A.UCF_ITEM_VCH8,
A.UCF_ITEM_VCH9,A.UCF_ITEM_VCH10,A.UCF_ITEM_VCH11,A.UCF_ITEM_VCH12,A.UCF_ITEM_VCH13,
A.UCF_ITEM_VCH14,A.UCF_ITEM_VCH15,A.UCF_ITEM_NUM2,A.UCF_ITEM_NUM3,
A.UCF_ITEM_NUM4,A.UCF_ITEM_NUM5,A.UCF_ITEM_DATE1,A.UCF_ITEM_DATE2,A.UCF_ITEM_DATE3,
A.UCF_ITEM_DATE4,A.UCF_ITEM_DATE5,A.UCF_ITEM_FLAG2,A.UCF_ITEM_FLAG3,
A.UCF_ITEM_FLAG4,A.UCF_ITEM_FLAG5,A.UCF_ITEM_VCH255_1,A.UCF_ITEM_VCH255_2,A.UCF_ITEM_VCH255_3,A.UCF_ITEM_VCH4000_1,A.UCF_ITEM_VCH4000_2,
A.PARENT_ENG_PART_NO, A.PARENT_ENG_PART_CHG,
A.OPT_DC1_FLAG, A.OPT_DC2_FLAG, A.OPT_DC3_FLAG, A.OPT_DC4_FLAG,A.OBSOLETE_RECORD_FLAG,A.STANDARD_PART_FLAG,
A.PART_NO AS FROM_PART_NO, A.PART_CHG AS FROM_PART_CHG, NULL AS TO_PART_NO, NULL AS TO_PART_CHG,
'N' AS INCL_PGM_LOC, 'N' AS INCL_ALT_PARTS, A.EXTERNAL_PLM_NO,A.EXTERNAL_ERP_NO,
FLIGHT_SAFETY_FLAG, FLIGHT_ESSENTIAL_FLAG,
B.TOOL_CALIBRATE_DAYS,B.TOOL_CALIBRATE_HOURS,B.TOOL_CALIBRATE_USES,
B.TOOL_CALIBRATION_DAYS_FREQ,B.TOOL_CALIBRATION_HOURS_FREQ,B.TOOL_CALIBRATION_USES_FREQ,A.DATE_CREATED,
CASE WHEN A.SOURCE_INSP_PLAN_ID IS NOT NULL THEN SFSQA_LATEST_INSP_PLAN_NO_GET(A.SOURCE_INSP_PLAN_ID)
     ELSE '' END AS SOURCE_INSP_PLAN_NO,
(SELECT MAX(INSP_PLAN_REV) FROM SFSQA_INSP_PLAN_DESC AA WHERE AA.INSP_PLAN_ID = A.SOURCE_INSP_PLAN_ID) AS SOURCE_INSP_PLAN_REV,
A.SOURCE_INSP_PLAN_ID,
CASE WHEN A.DEST_INSP_PLAN_ID IS NOT NULL THEN SFSQA_LATEST_INSP_PLAN_NO_GET(A.DEST_INSP_PLAN_ID)
     ELSE '' END AS DEST_INSP_PLAN_NO,
(SELECT MAX(INSP_PLAN_REV) FROM SFSQA_INSP_PLAN_DESC BB WHERE BB.INSP_PLAN_ID = A.DEST_INSP_PLAN_ID) AS DEST_INSP_PLAN_REV,
A.DEST_INSP_PLAN_ID, A.UTILIZATION_RULE, A.TRACKABLE_FLAG,
a.UID_ENTRY_NAME,UID_ITEM_FLAG,UID_ACQUISITION_CODE,
A.SECURITY_GROUP,
CASE WHEN 'TOOL' = 'TOOL' THEN 'Tool' WHEN 'TOOL' = 'MACHINE' THEN 'Machine' ELSE '' END AS CAPTION,
CASE WHEN 'TOOL' = 'TOOL' THEN 'Serial' WHEN 'TOOL' = 'MACHINE' THEN 'ID' ELSE '' END AS SERIAL_OR_ID,
ITEM_STATUS
FROM SFPL_ITEM_DESC_ALL A, SFFND_TOOL B
WHERE case when A.ITEM_SUBTYPE is null then 'X'  else  A.ITEM_SUBTYPE end  != 'STDOPER'
and
((0 = (select count(*) from sfpl_item_desc_sec_grp where part_no = a.part_no))
    or exists
(SELECT 1 FROM SFFND_USER_SEC_GRP A, SFFND_SECURITY_GROUP_DEF B, SFPL_ITEM_DESC_SEC_GRP C
  WHERE A.SECURITY_GROUP = B.SECURITY_GROUP
    AND B.OBSOLETE_RECORD_FLAG = 'N'
    AND SYSDATE BETWEEN A.EFFECTIVE_START_DATE AND A.EFFECTIVE_END_DATE
    AND C.PART_NO = A.PART_NO
    AND C.SECURITY_GROUP = A.SECURITY_GROUP))
AND A.ITEM_ID != 'N/A'
AND A.PROGRAM = 'ANY'
AND A.ITEM_TYPE = 'TOOL'
AND A.PART_NO = B.TOOL_NO
and (a.part_no, a.work_loc) in (select i.part_no, i.work_loc from SFPL_ITEM_DESC_ALL i where a.part_no = i.part_no and rownum = 1)
AND A.PART_CHG = B.TOOL_CHG);
grant select, insert, update, delete on PWUST_PLG_TOOL_DISPATCH_V to SF$CONNECTION_POOL;


set define on