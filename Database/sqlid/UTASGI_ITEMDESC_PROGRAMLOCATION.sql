set define off
declare
v_folder_id varchar2(40) :='FOUNDATION';
v_sql_id varchar2(85) :='UTASGI_ITEMDESC_PROGRAMLOCATION';
v_sql_id_displ varchar2(85) :='UTASGI_ItemDesc_ProgramLocation';
v_read_only number :='';
v_datasource varchar2(32) :='';
v_stype varchar2(32) :='';
v_site varchar2(10) :='HAMSI';
v_description varchar2(200) :='';
v_sql_text varchar2(4000) :='SELECT PART_NO,PART_CHG,
  DECODE(PROGRAM,''ANY'',NULL,PROGRAM) AS PROGRAM,DECODE(WORK_LOC,''ANY'',NULL,WORK_LOC) AS WORK_LOC,
  PART_TITLE,STOCK_UOM,WO_SERIAL_FLAG,
  WO_LOT_FLAG,COMP_SERIAL_FLAG, COMP_LOT_FLAG,EXP_FLAG,SPOOL_FLAG,B.ITEM_ID,B.UPDT_USERID,B.TIME_STAMP,B.LAST_ACTION,UCF_ITEM_VCH1,UCF_ITEM_VCH2,
  UCF_ITEM_NUM1,UCF_ITEM_FLAG1,
  UCF_ITEM_VCH3,UCF_ITEM_VCH4,UCF_ITEM_VCH5,UCF_ITEM_VCH6,UCF_ITEM_VCH7,UCF_ITEM_VCH8,
  UCF_ITEM_VCH9,UCF_ITEM_VCH10,UCF_ITEM_VCH11,UCF_ITEM_VCH12,UCF_ITEM_VCH13,
  UCF_ITEM_VCH14,UCF_ITEM_VCH15,UCF_ITEM_NUM2,UCF_ITEM_NUM3,
  UCF_ITEM_NUM4,UCF_ITEM_NUM5,UCF_ITEM_DATE1,UCF_ITEM_DATE2,UCF_ITEM_DATE3,
  UCF_ITEM_DATE4,UCF_ITEM_DATE5,UCF_ITEM_FLAG2,UCF_ITEM_FLAG3,
  UCF_ITEM_FLAG4,UCF_ITEM_FLAG5,
  PARENT_ENG_PART_NO, PARENT_ENG_PART_CHG,
  OPT_DC1_FLAG, OPT_DC2_FLAG, OPT_DC3_FLAG, OPT_DC4_FLAG, ''N'' AS STANDARD_PART_FLAG,
  B.UTILIZATION_RULE, B.TRACKABLE_FLAG,
  UID_ITEM_FLAG,UID_ACQUISITION_CODE,UID_ENTRY_NAME,
  B.FLIGHT_HOURS,B.FLIGHT_DAYS,B.FLIGHT_CYCLES, A.SECURITY_GROUP,
  b.UCF_ITEM_PROG_DETAIL_VCH2, b.UCF_ITEM_PROG_DETAIL_VCH1, b.UCF_ITEM_PROG_DETAIL_FLAG1,
  sfmfg.utasgi_fnd_has_work_loc_secgrp(:@USERID, b.work_loc) as can_create_order_fl
FROM SFPL_ITEM_DESC_MASTER_ALL A, SFPL_ITEM_PROGRAM_DETAILS B
WHERE
  OBSOLETE_RECORD_FLAG LIKE ''%''
  AND PART_FLAG = ''Y''
  AND STANDARD_PART_FLAG LIKE ''%''
  AND A.ITEM_ID = B.ITEM_ID
  AND PART_NO = :PART_NO
  AND PART_CHG = :PART_CHG
  AND (PROGRAM != ''ANY'' OR WORK_LOC != ''ANY'')
ORDER BY
      PROGRAM, WORK_LOC';
begin
begin
insert into sfcore_sql_lib(sql_id,sql_id_displ,updt_userid,time_stamp,read_only,datasource,stype, description,sql_text)
    values(v_sql_id,v_sql_id_displ,user,sysdate,v_read_only,v_datasource,v_stype, v_description,replace(v_sql_text,chr(10),chr(13)||chr(10)));
commit;
exception when dup_val_on_index then
  update sfcore_sql_lib
  set sql_text=replace(v_sql_text,chr(10),chr(13)||chr(10)),updt_userid=user,time_stamp=sysdate,
read_only=v_read_only,datasource=v_datasource,stype=v_stype,  
description=v_description
  where sql_id=v_sql_id;
  commit;
end;
begin
if v_folder_id is not null then
insert into sfcore_sql_lib_folder(sql_id,folder_id,updt_userid,time_stamp)
values(v_sql_id,v_folder_id,user,sysdate);
commit;
end if;
exception when others then null;
end;
end;
/

set define on

