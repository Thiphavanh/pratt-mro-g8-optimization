
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_MFI_SELECTDATACOLLECTION2.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_MFI_SELECTDATACOLLECTION2';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_MFI_SelectDataCollection2';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFPL';
v_description sfcore_sql_lib.description%type  :='Select Data Collection; SMRO_WOE_306';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT
DAT_COL_TYPE
,null AS DAT_COL_TITLE
,A.UOM AS DAT_COL_UOM
,NULL AS UCF_OPER_DC_FLAG1
,NULL AS UCF_OPER_DC_VCH255_1
,NULL AS UCF_OPER_DC_FLAG2
,SPC_FLAG
,CASE WHEN SPC_FLAG = ''Y'' THEN  SFMFG.SFFND_FORMAT_VALUE_TO_CLIENT(LOWER_LIMIT,B.FORMAT,:@UserConnectionId) ELSE NULL END AS LOWER_LIMIT
,NULL AS TARGET_VALUE
,CASE WHEN SPC_FLAG = ''Y'' THEN  SFMFG.SFFND_FORMAT_VALUE_TO_CLIENT(UPPER_LIMIT,B.FORMAT,:@UserConnectionId) ELSE NULL END AS UPPER_LIMIT
,CERT AS DAT_COL_CERT
,NULL AS DAT_COL_ID
,NULL AS OPTIONAL_FLAG
,NULL AS CALC_FLAG
,NULL AS VARIABLE_NAME
,NULL AS VISIBILITY
,NULL AS DISPLAY_LINE_NO
,NULL AS AUDIT_FLAG
,DAT_COL_TYPE_DESC
,UCF_DAT_COL_VCH1
,UCF_DAT_COL_VCH2
,UCF_DAT_COL_FLAG1
,SELECTABLE_DC_FLAG
,UCF_DAT_COL_VCH3
,UCF_DAT_COL_VCH4
,UCF_DAT_COL_VCH5
,UCF_DAT_COL_VCH6
,UCF_DAT_COL_VCH7
,UCF_DAT_COL_VCH8
,UCF_DAT_COL_VCH9
,UCF_DAT_COL_VCH10
,UCF_DAT_COL_VCH11
,UCF_DAT_COL_VCH12
,UCF_DAT_COL_VCH13
,UCF_DAT_COL_VCH14
,UCF_DAT_COL_VCH15
,UCF_DAT_COL_NUM1
,UCF_DAT_COL_NUM2
,UCF_DAT_COL_NUM3
,UCF_DAT_COL_NUM4
,UCF_DAT_COL_NUM5
,UCF_DAT_COL_DATE1
,UCF_DAT_COL_DATE2
,UCF_DAT_COL_DATE3
,UCF_DAT_COL_DATE4
,UCF_DAT_COL_DATE5
,UCF_DAT_COL_FLAG2
,UCF_DAT_COL_FLAG3
,UCF_DAT_COL_FLAG4
,UCF_DAT_COL_FLAG5
,UCF_DAT_COL_VCH255_1
,UCF_DAT_COL_VCH255_2
,UCF_DAT_COL_VCH255_3
,UCF_DAT_COL_VCH4000_1
,UCF_DAT_COL_VCH4000_2
,STD_DATCOL_ID
,''AUTHORING_PALETTE'' AS CALLED_FROM
,A.UPDT_USERID
,A.TIME_STAMP
,A.LAST_ACTION
,A.OBSOLETE_RECORD_FLAG
,B.FORMAT
,DECIMAL_PLACES AS NUM_DECIMAL_DIGITS
,A.RESULT_ID
,V.VALID_RESULT_TYPE
,NULL AS FILE1
,SFMFG.SFDB_SUBSTR_VARCHAR(O.OBJECT_REFERENCE,
                               SFMFG.SFDB_INSTR_VARCHAR(O.OBJECT_REFERENCE, ''\'', -1, 1)+1, SFMFG.SFDB_LENGTH_VARCHAR(O.OBJECT_REFERENCE)) AS FILE_DISP
,O.SECURITY_GROUP
FROM
SFFND_STD_DATCOL_TYPE_DEF A LEFT OUTER JOIN SFSQA_VALID_RESULT_TYPE_DEF V ON ( A.RESULT_ID = V.RESULT_ID)
                            LEFT OUTER JOIN SFCORE_MM_OBJECT O ON A.TEMPLATE_FILE_ID = O.OBJECT_ID,
SFFND_UOM_DEF B
WHERE NVL(A.OBSOLETE_RECORD_FLAG, ''N'') = ''N''
AND A.UOM = B.UOM
AND (SELECTABLE_DC_FLAG = ''N'' OR (SELECTABLE_DC_FLAG = ''Y''
                                 AND EXISTS(SELECT ''X'' FROM SFFND_DCTYPE_SELECT_DEF B
                                            WHERE A.STD_DATCOL_ID = B.STD_DATCOL_ID)))
ORDER BY UPPER(DAT_COL_TYPE)';
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

