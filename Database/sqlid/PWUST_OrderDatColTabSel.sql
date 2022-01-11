
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_ORDERDATCOLTABSEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_ORDERDATCOLTABSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_OrderDatColTabSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFWID';
v_description sfcore_sql_lib.description%type  :='defect 44; Defect 662; Defect 1370; Defect 1103; defect 1400;Defect1732;Defect1571';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT DISTINCT
X.ORDER_ID,
X.OPER_NO,
X.DAT_COL_ID,
X.STEP_NO,
X.OPER_KEY,
X.STEP_KEY,
X.DAT_COL_TYPE,
X.STD_DATCOL_ID,
X.DAT_COL_CERT,
X.UPDT_USERID,
X.TIME_STAMP,
X.DAT_COL_UOM,
X.DAT_COL_TITLE,
X.UPPER_LIMIT,
X.LOWER_LIMIT,
X.TARGET_VALUE,
X.ORIENTATION_FLAG,
X.CROSS_ORDER_FLAG,
X.OPTIONAL_FLAG,
X.DISPLAY_LINE_NO,
X.IMAGE,
X.SLIDE_ID,
X.SLIDE_EMBEDDED_REF_ID,
X.AUDIT_FLAG,
X.RESULT_ID,
X.VALID_RESULT_TYPE,
X.FILE_NAME,
X.SECURITY_GROUP,
X.TEMPLATE_FILE_ID,
(DECODE(sfmfg.SFWID_IS_DC_OUTSIDE_LIMITS(X.LOWER_LIMIT, X.UPPER_LIMIT, X.DCVALUE, X.STD_DATCOL_ID, X.DAT_COL_UOM),''Y'',''Y'','''')) AS IS_OUTSIDE_LIMIT,
X.DCVALUE,
X.COMMENTS,
X.serial_NO
FROM
 (SELECT
  C.ORDER_ID,
  C.OPER_NO,
  C.DAT_COL_ID,
  C.STEP_NO,
  C.OPER_KEY,
  C.STEP_KEY,
  D.DAT_COL_TYPE,
  C.STD_DATCOL_ID,
  DAT_COL_CERT,
  Z.UPDT_USERID,
  Z.TIME_STAMP,
  DAT_COL_UOM,
  DAT_COL_TITLE,
  SFMFG.SFFND_FORMAT_VALUE_TO_CLIENT( C.UPPER_LIMIT , U.FORMAT , :@USERCONNECTIONID) AS UPPER_LIMIT,
  SFMFG.SFFND_FORMAT_VALUE_TO_CLIENT( C.LOWER_LIMIT , U.FORMAT , :@USERCONNECTIONID) AS LOWER_LIMIT,
  SFMFG.SFFND_FORMAT_VALUE_TO_CLIENT( C.TARGET_VALUE , U.FORMAT , :@USERCONNECTIONID) AS TARGET_VALUE,
  CASE C.ORIENTATION_FLAG
   WHEN ''U'' THEN ''UNIT CENTRIC''
   WHEN ''D'' THEN ''DATA COLLECTION CENTRIC''
   END AS ORIENTATION_FLAG,
  C.CROSS_ORDER_FLAG,
  OPTIONAL_FLAG,
  DISPLAY_LINE_NO,
  CASE
   WHEN SLIDE_EMBEDDED_REF_ID IS NULL THEN NULL
   WHEN SFMFG.SFFND_MM_SECURITY_GROUP_OK(SLIDE_ID, :@USERID) <> ''Y'' THEN NULL
   ELSE ''IMAGE'' END AS IMAGE,
  SLIDE_ID,
  SLIDE_EMBEDDED_REF_ID,
  AUDIT_FLAG,
  C.RESULT_ID,
  V.VALID_RESULT_TYPE,
  U.FORMAT,
  CASE
   WHEN O.OBJECT_REFERENCE IS NULL THEN NULL
   ELSE ''LinkInvoke(SideNotes(@SubConfig=Slide,OBJECT_ID='' || C.TEMPLATE_FILE_ID || '',@Caption="'' ||
   SFMFG.SFDB_SUBSTR_VARCHAR(O.OBJECT_REFERENCE, SFMFG.SFDB_INSTR_VARCHAR(O.OBJECT_REFERENCE,''\'',-1,1) + 1,
   SFMFG.SFDB_LENGTH_VARCHAR(O.OBJECT_REFERENCE)) || ''",@ButtonStyle=Hypertext,@BGColor=paleyellow,@FontColor=black)''
   END AS FILE_NAME,
  O.SECURITY_GROUP,
  C.TEMPLATE_FILE_ID,
  Z.DCVALUE,
  Z.COMMENTS,
  S.Serial_No
  FROM
   SFWID_OPER_DAT_COL C
   LEFT OUTER JOIN SFSQA_VALID_RESULT_TYPE_DEF V ON (C.RESULT_ID = V.RESULT_ID)
   LEFT JOIN SFCORE_MM_OBJECT O ON (C.TEMPLATE_FILE_ID = O.OBJECT_ID)
   JOIN SFFND_STD_DATCOL_TYPE_DEF D on (D.STD_DATCOL_ID=C.STD_DATCOL_ID)
   JOIN SFFND_UOM_DEF U on (U.UOM=C.DAT_COL_UOM)
   LEFT JOIN SFWID_SERIAL_OPER_DAT_COL Z on (C.ORDER_ID = Z.ORDER_ID AND C.STEP_KEY = Z.STEP_KEY and c.dat_col_id = z.dat_col_id and c.oper_key=z.oper_key)
   LEFT JOIN SFWID_SERIAL_DESC S on (z.serial_id = s.serial_id and z.lot_id = s.lot_id and z.order_id = s.order_id)
   JOIN SFWID_OPER_DESC P on (C.ORDER_ID = P.ORDER_ID and C.OPER_KEY = P.OPER_KEY AND P.STEP_KEY = -1 AND COALESCE(P.INCLUDED,''INCLUDED'') = ''INCLUDED'')
  WHERE
   C.ORDER_ID = :ORDER_ID
   AND (C.LAST_ACTION <> ''SUSPECTEDI'' AND C.LAST_ACTION <> ''SUSPECTEDU'')
   AND EXISTS (SELECT 1 FROM sfor_sfwid_oper_subject TT WHERE TT.ORDER_ID = C.ORDER_ID)
) X
WHERE 1 = 1
/*+ @FILTER() */
ORDER BY X.OPER_NO, X.STEP_NO, X.DAT_COL_TYPE, X.serial_NO';
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

