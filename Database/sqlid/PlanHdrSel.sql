
set define off
prompt ---------------------------------------;
prompt Executing ... PLANHDRSEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='FOUNDATION';
v_sql_id sfcore_sql_lib.sql_id%type :='PLANHDRSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PlanHdrSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFPL';
v_description sfcore_sql_lib.description%type  :='SMRO_PLG_301';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT
PLAN_ID,
PART_NO,
ITEM_TYPE,
ITEM_SUBTYPE,
PART_CHG,
A.PROGRAM,
PLAN_VERSION,
PLAN_REVISION,
PLAN_ALTERATIONS,
PLAN_UPDT_NO,
PLAN_STATE,
REV_STATUS,
REV_LOCK_STATE,
REV_LOCKED_BY,
PLAN_TITLE,
PLAN_STATUS,
PLND_ORDER_QTY,
PLND_CUST_ID,
ORDER_UOM,
MODEL,
MFG_INDEX_NO,
A.PLG_GROUP,
PLND_WORK_LOC,
PLND_WORK_LOC AS HEADER_PLND_WORK_LOC,
ENG_PART_NO,
ENG_PART_CHG,
A.ENG_GROUP,
LOW_NAV_LVL,
INITIAL_STORES,
FINAL_STORES,
A.PLAN_TYPE,
A.UPDT_USERID,
A.TIME_STAMP,
A.LAST_ACTION,
NEXT_PLAN_ID,
UNIT_TYPE,
SEQ_FLAG,
A.MFG_BOM_CHG,
A.MFG_BOM_CHG AS MFG_BOM_REV,
A.ITEM_ID,
A.PROJECT,
PLND_MAX_ORDER_QTY,
PLND_MIN_ORDER_QTY,
LOCK_STATE,
NEEDS_REVIEW_FLAG,
SERIAL_FLAG,
LOT_FLAG,
LTA_SEND_FLAG,
A.EXTERNAL_PLM_NO,
EXTERNAL_ERP_NO,
A.OBJECT_ID,
PLAN_NO,
CONDITION,
UCF_PLAN_VCH1,
UCF_PLAN_VCH2,
UCF_PLAN_VCH3,
UCF_PLAN_VCH4,
UCF_PLAN_VCH5,
UCF_PLAN_VCH6,
UCF_PLAN_VCH7,
UCF_PLAN_VCH8,
UCF_PLAN_VCH9,
UCF_PLAN_VCH10,
UCF_PLAN_VCH11,
UCF_PLAN_VCH12,
UCF_PLAN_VCH13,
UCF_PLAN_VCH14,
UCF_PLAN_VCH15,
UCF_PLAN_VCH255_1,
UCF_PLAN_VCH255_2,
UCF_PLAN_VCH255_3,
UCF_PLAN_VCH4000_1,
UCF_PLAN_VCH4000_1 AS CHANGE_LEVEL_NOTE,
UCF_PLAN_VCH4000_2,
UCF_PLAN_NUM1,
UCF_PLAN_NUM2,
UCF_PLAN_NUM3,
UCF_PLAN_NUM4,
UCF_PLAN_NUM5,
UCF_PLAN_DATE1,
UCF_PLAN_DATE2,
UCF_PLAN_DATE3,
UCF_PLAN_DATE4,
UCF_PLAN_DATE5,
UCF_PLAN_FLAG1,
UCF_PLAN_FLAG2,
UCF_PLAN_FLAG3,
UCF_PLAN_FLAG4,
UCF_PLAN_FLAG5,
A.BOM_NO,
DISPLAY_SEQUENCE,
OPERATION_OVERLAP_FLAG,
A.SECURITY_GROUP,
C.INSP_PLAN_NO,
C.INSP_PLAN_ID,
INSTRUCTIONS_TYPE,
D.LANGUAGE_CODE,
D.LANGUAGE_NAME,
A.PLND_LOCATION_ID,
A.EXPLICIT_BOM_LINK_FLAG,
A.PPV_REQ_FLAG,
A.PPV_TYPE,
A.PPV_QTY,
A.PPV_PENDING_QTY,
A.PPV_INPROCESS_QTY,
A.PPV_COMPLETE_QTY,
A.LAST_PPV_COMPLETE_DATE,
A.COMMODITY_JURISDICTION,
A.COMMODITY_CLASSIFICATION,
CASE WHEN COALESCE(A.BOM_NO, NULL) IS NULL THEN NULL ELSE BOM.MBOM_REV_DATE END AS MBOM_REV_DATE
FROM SFPL_PLAN_V A LEFT OUTER JOIN SFSQA_INSP_PLAN_DESC C ON A.INSP_PLAN_ID = C.INSP_PLAN_ID LEFT OUTER JOIN SFPL_MFG_BOM_REV BOM ON A.BOM_ID = BOM.BOM_ID,
SFFND_DOC_TYPE_DEF B,
SFFND_LANGUAGE D
WHERE
     PLAN_ID = :PLAN_ID AND
     PLAN_VERSION = :PLAN_VERSION AND
     PLAN_REVISION = :PLAN_REVISION AND
     PLAN_ALTERATIONS = :PLAN_ALTERATIONS AND
     A.DOC_TYPE = B.DOC_TYPE AND
     A.PLAN_TYPE = B.DOC_SUB_TYPE AND
     A.WORK_FLOW = B.WORK_FLOW AND
     A.DECLARED_LANGUAGE_CODE = D.LANGUAGE_CODE';
begin
begin
  insert into sfcore_sql_lib(sql_id,sql_id_displ,updt_userid,time_stamp,read_only,datasource,stype,description,sql_text)
    values(v_sql_id,v_sql_id_displ,user,sysdate,v_read_only,v_datasource,v_stype,v_description,v_sql_text);
  commit;
exception when dup_val_on_index then
  begin
    update sfcore_sql_lib
    set sql_text=v_sql_text,updt_userid=user,time_stamp=sysdate,sql_id_displ = v_sql_id_displ,
read_only=v_read_only,datasource=v_datasource,stype=v_stype,
description=v_description
    where sql_id=v_sql_id;
    commit;
  exception when others then
  	 sffnd_show_execution_message('DML',v_sql_id_displ, SQLCODE,SQLERRM,' update sfcore_sql_lib ');
  end;
when others then
  sffnd_show_execution_message('DML',v_sql_id_displ, SQLCODE,SQLERRM,' insert into sfcore_sql_lib ');
end;
begin
 if v_folder_id is not null then
  insert into sfcore_sql_lib_folder(sql_id,folder_id,updt_userid,time_stamp)
  values(v_sql_id,v_folder_id,user,sysdate);
  commit;
 end if;
exception when others then 
 sffnd_show_execution_message('DML',v_sql_id_displ, SQLCODE,SQLERRM,' insert into sfcore_sql_lib_folder ');
end;
end;
/

set define on

