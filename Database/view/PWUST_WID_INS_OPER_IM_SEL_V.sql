CREATE VIEW PWUST_WID_INS_OPER_IM_SEL AS
SELECT O.ORDER_ID,
       O.OPER_NO,
       O.STEP_KEY,
       O.TITLE AS OPER_TITLE,
       O.PLND_MACHINE_NO,
       DEPT.WORK_DEPT AS ASGND_WORK_DEPT,
       CENTER.WORK_CENTER AS ASGND_WORK_CENTER,
       LOC.WORK_LOC AS ASGND_WORK_LOC,
       O.OPER_TYPE,
       (SELECT OP.OPER_TYPE_ROLE FROM SFFND_OPER_TYPE_DEF OP  WHERE OP.OPER_TYPE = O.OPER_TYPE) AS OPER_TYPE_ROLE,
       NVL(O.OPER_OPT_FLAG, 'N') AS OPER_OPT_FLAG,
       NVL(O.OCCUR_RATE, 1) AS OCCUR_RATE,
       NVL(O.OSP_FLAG, 'N') AS OSP_FLAG,
       CASE WHEN O.SUPPLIER_CODE IS NULL THEN NULL ELSE (SELECT WORK_LOC FROM SFFND_WORK_LOC_DEF LOC WHERE LOC.LOCATION_ID = O.SUPPLIER_CODE) END AS SUPPLIER_CODE,
       O.OSP_DAYS,
       O.OSP_COST_PER_UNIT,
       O.TEST_TYPE,
       NVL(O.AUTO_START_FLAG, 'N') AS AUTO_START_FLAG,
       NVL(O.AUTO_COMPLETE_FLAG, 'N') AS AUTO_COMPLETE_FLAG,
       O.REWORK_FLAG,
       O.UCF_ORDER_OPER_FLAG1,
       O.UCF_ORDER_OPER_DATE1,
       O.UCF_ORDER_OPER_VCH1,
       O.UCF_ORDER_OPER_VCH2,
       O.UCF_ORDER_OPER_NUM1,
       O.UCF_ORDER_OPER_NUM2,
       O.UCF_ORDER_OPER_VCH3,
       O.UCF_ORDER_OPER_VCH4,
       O.UCF_ORDER_OPER_VCH5,
       O.UCF_ORDER_OPER_VCH6,
       O.UCF_ORDER_OPER_VCH7,
       O.UCF_ORDER_OPER_VCH8,
       O.UCF_ORDER_OPER_VCH9,
       O.UCF_ORDER_OPER_VCH10,
       O.UCF_ORDER_OPER_VCH11,
       O.UCF_ORDER_OPER_VCH12,
       O.UCF_ORDER_OPER_VCH13,
       O.UCF_ORDER_OPER_VCH14,
       O.UCF_ORDER_OPER_VCH15,
       O.UCF_ORDER_OPER_NUM3,
       O.UCF_ORDER_OPER_NUM4,
       O.UCF_ORDER_OPER_NUM5,
       O.UCF_ORDER_OPER_DATE2,
       O.UCF_ORDER_OPER_DATE3,
       O.UCF_ORDER_OPER_DATE4,
       O.UCF_ORDER_OPER_DATE5,
       O.UCF_ORDER_OPER_FLAG2,
       O.UCF_ORDER_OPER_FLAG3,
       O.UCF_ORDER_OPER_FLAG4,
       O.UCF_ORDER_OPER_FLAG5,
       O.UCF_ORDER_OPER_VCH255_1,
       O.UCF_ORDER_OPER_VCH255_2,
       O.UCF_ORDER_OPER_VCH255_3,
       O.UCF_ORDER_OPER_VCH4000_1,
       O.UCF_ORDER_OPER_VCH4000_2,
       O.UCF_PLAN_OPER_VCH1,
       O.UCF_PLAN_OPER_VCH2,
       O.UCF_PLAN_OPER_VCH3,
       O.UCF_PLAN_OPER_VCH4,
       O.UCF_PLAN_OPER_VCH5,
       O.UCF_PLAN_OPER_NUM1,
       O.UCF_PLAN_OPER_NUM2,
       O.UCF_PLAN_OPER_FLAG1,
       O.UCF_PLAN_OPER_FLAG2,
       O.UCF_PLAN_OPER_VCH6,
       O.UCF_PLAN_OPER_VCH7,
       O.UCF_PLAN_OPER_VCH8,
       O.UCF_PLAN_OPER_VCH9,
       O.UCF_PLAN_OPER_VCH10,
       O.UCF_PLAN_OPER_VCH11,
       O.UCF_PLAN_OPER_VCH12,
       O.UCF_PLAN_OPER_VCH13,
       O.UCF_PLAN_OPER_VCH14,
       O.UCF_PLAN_OPER_VCH15,
       O.UCF_PLAN_OPER_NUM3,
       O.UCF_PLAN_OPER_NUM4,
       O.UCF_PLAN_OPER_NUM5,
       O.UCF_PLAN_OPER_DATE1,
       O.UCF_PLAN_OPER_DATE2,
       O.UCF_PLAN_OPER_DATE3,
       O.UCF_PLAN_OPER_DATE4,
       O.UCF_PLAN_OPER_DATE5,
       O.UCF_PLAN_OPER_FLAG3,
       O.UCF_PLAN_OPER_FLAG4,
       O.UCF_PLAN_OPER_FLAG5,
       O.UCF_PLAN_OPER_VCH255_1,
       O.UCF_PLAN_OPER_VCH255_2,
       O.UCF_PLAN_OPER_VCH255_3,
       O.UCF_PLAN_OPER_VCH4000_1,
       O.UCF_PLAN_OPER_VCH4000_2,
       O.SEQ_STEPS_FLAG,
       O.OPER_CHANGE_LEVEL,
       O.UNIT_PROCESSING,
       CASE WHEN ORIENTATION_FLAG = 'U' THEN 'Unit Centric' WHEN ORIENTATION_FLAG = 'D' THEN 'Data Collection Centric'  ELSE ORIENTATION_FLAG END AS ORIENTATION_FLAG,
       CROSS_ORDER_FLAG,
       MUST_ISSUE_PARTS_FLAG,
       O.EXE_ORDER,
       O.UNITS_PER_CYCLE,
       O.AUTO_CYCLE_FLAG,
       O.PRINT_LABEL,
       O.NUMBER_OF_LABELS,
       O.RECONCILE_SCRAP,
       O.UNITS_PER_CYCLE_ACTUAL,
       O.OPER_KEY,
       O.REPORT_ID,
       M.OBJECT_TAG AS REPORT_ID_DISP,
       NULL AS SECURITY_GROUP
  FROM SFWID_OPER_DESC O
  LEFT OUTER JOIN SFCORE_MM_OBJECT M ON O.REPORT_ID = M.OBJECT_ID
  LEFT OUTER JOIN SFFND_WORK_LOC_DEF LOC ON O.ASGND_LOCATION_ID = LOC.LOCATION_ID
  LEFT OUTER JOIN SFFND_WORK_DEPT_DEF DEPT ON O.ASGND_DEPARTMENT_ID = DEPT.DEPARTMENT_ID AND O.ASGND_LOCATION_ID = DEPT.LOCATION_ID
  LEFT OUTER JOIN SFFND_WORK_CENTER_DEF CENTER ON O.ASGND_CENTER_ID = CENTER.CENTER_ID AND O.ASGND_LOCATION_ID = CENTER.LOCATION_ID  AND O.ASGND_DEPARTMENT_ID = CENTER.DEPARTMENT_ID;
/


grant select on PWUST_WID_INS_OPER_IM_SEL to pratt_read_only ;
grant select on PWUST_WID_INS_OPER_IM_SEL to sf$connection_pool ;
