
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_DC_HISTORY.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_DC_HISTORY';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_DC_HISTORY';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Data Collection History - SMRO_WOE_206';
v_sql_text sfcore_sql_lib.sql_text%type  :='SELECT B.DCVALUE, B.DCVALUE AS DCVALUE_DATE, B.DCVALUE AS DCVALUE_TEXT, 
       CASE WHEN B.MACHINE_ID IS NOT NULL THEN 
       SFFND_EVENT.UDV_LINK(''MFI_87C61735E6D64D16BFBD9C888C646619'',''Insert'',B.MACHINE_ID,''Hypertext'',''paleyellow'',''black'',''MACHINE_ID=''||B.MACHINE_ID||'',MACHINE_NO=''||B.MACHINE_NO) 
       ELSE NULL END AS MACHINE_ID, 
       B.MACHINE_NO, 
       CASE B.LAST_ACTION WHEN ''INSERTED'' THEN NULL
       ELSE B.TIME_STAMP END AS TIME_STAMP,
       CASE B.LAST_ACTION WHEN ''INSERTED'' THEN NULL ELSE B.UPDT_USERID END AS UPDT_USERID,
       B.COMMENTS,
       SFMFG.SFFND_FORMAT_VALUE_TO_CLIENT(SFMFG.SFDB_NUMBER_TO_VARCHAR(B.XBAR_CPU_VALUE,''999999990.99999''), :FORMAT, :@UserConnectionId) AS XBAR_CPU_VALUE,
       SFMFG.SFFND_FORMAT_VALUE_TO_CLIENT(SFMFG.SFDB_NUMBER_TO_VARCHAR(B.XBAR_CPL_VALUE,''999999990.99999''), :FORMAT, :@UserConnectionId) AS XBAR_CPL_VALUE,
       SFMFG.SFFND_FORMAT_VALUE_TO_CLIENT(SFMFG.SFDB_NUMBER_TO_VARCHAR(D.UCL,''999999990.99999''), :FORMAT, :@UserConnectionId) AS UCL, 
       SFMFG.SFFND_FORMAT_VALUE_TO_CLIENT(SFMFG.SFDB_NUMBER_TO_VARCHAR(D.LCL,''999999990.99999''), :FORMAT, :@UserConnectionId) AS LCL,  
       A.UPPER_LIMIT, 
       A.LOWER_LIMIT, 
       A.TARGET_VALUE, 
       CASE SFMFG.SFWID_IS_DC_OUTSIDE_LIMITS(A.LOWER_LIMIT,A.UPPER_LIMIT,B.DCVALUE,A.STD_DATCOL_ID,A.DAT_COL_UOM) 
       WHEN ''YES'' THEN ''YES'' 
       ELSE NULL 
       END AS OUTSIDE, 
       CASE SFMFG.SFWID_IS_DC_OUTSIDE_LIMITS(A.LOWER_LIMIT,A.UPPER_LIMIT,B.DCVALUE,A.STD_DATCOL_ID,A.DAT_COL_UOM) 
       WHEN ''YES'' THEN ''ATTENTION'' 
       ELSE NULL 
       END AS DC_STATUS, 
       B.LAST_ACTION, 
       B.UCF_SRL_OPER_DC_VCH1, 
       B.UCF_SRL_OPER_DC_VCH2, 
       B.UCF_SRL_OPER_DC_VCH3, 
       B.UCF_SRL_OPER_DC_VCH4, 
       B.UCF_SRL_OPER_DC_VCH5, 
       B.UCF_SRL_OPER_DC_VCH6, 
       B.UCF_SRL_OPER_DC_VCH7, 
       B.UCF_SRL_OPER_DC_VCH8, 
       B.UCF_SRL_OPER_DC_VCH9, 
       B.UCF_SRL_OPER_DC_VCH10, 
       B.UCF_SRL_OPER_DC_VCH11, 
       B.UCF_SRL_OPER_DC_VCH12, 
       B.UCF_SRL_OPER_DC_VCH13, 
       B.UCF_SRL_OPER_DC_VCH14, 
       B.UCF_SRL_OPER_DC_VCH15, 
       B.UCF_SRL_OPER_DC_NUM1, 
       B.UCF_SRL_OPER_DC_NUM2, 
       B.UCF_SRL_OPER_DC_NUM3, 
       B.UCF_SRL_OPER_DC_NUM4, 
       B.UCF_SRL_OPER_DC_NUM5, 
       B.UCF_SRL_OPER_DC_FLAG1, 
       B.UCF_SRL_OPER_DC_FLAG2, 
       B.UCF_SRL_OPER_DC_FLAG3, 
       B.UCF_SRL_OPER_DC_FLAG4, 
       B.UCF_SRL_OPER_DC_FLAG5, 
       B.UCF_SRL_OPER_DC_DATE1, 
       B.UCF_SRL_OPER_DC_DATE2, 
       B.UCF_SRL_OPER_DC_DATE3, 
       B.UCF_SRL_OPER_DC_DATE4, 
       B.UCF_SRL_OPER_DC_DATE5, 
       B.UCF_SRL_OPER_DC_VCH255_1, 
       B.UCF_SRL_OPER_DC_VCH255_2, 
       B.UCF_SRL_OPER_DC_VCH255_3, 
       B.UCF_SRL_OPER_DC_VCH4000_1, 
       B.UCF_SRL_OPER_DC_VCH4000_2, 
       :FORMAT AS FORMAT, 
       CASE B.DCVALUE WHEN ''NOT_APPLICABLE'' THEN B.DCVALUE ELSE 
       SFMFG.SFDB_SUBSTR_VARCHAR(C.OBJECT_REFERENCE, SFMFG.SFDB_INSTR_VARCHAR(C.OBJECT_REFERENCE, ''\\'', -1, 1)+1, SFMFG.SFDB_LENGTH_VARCHAR(C.OBJECT_REFERENCE)) 
       END AS FILE_NAME,  
       NULL AS SECURITY_GROUP, 
       B.COMPLETE_QTY, 
       B.DCVALUE AS ACCEPT_REJECT
  FROM SFWID_OPER_DAT_COL A LEFT OUTER JOIN SFFND_STD_DATCOL_TYPE_DEF D ON (A.STD_DATCOL_ID = D.STD_DATCOL_ID),
       SFWID_SERIAL_OPER_DAT_COL_HIST B LEFT OUTER JOIN SFCORE_MM_OBJECT C ON (B.DCVALUE = C.OBJECT_ID)
 WHERE A.ORDER_ID = B.ORDER_ID 
   AND A.OPER_KEY = B.OPER_KEY 
   AND A.STEP_KEY = B.STEP_KEY 
   AND A.DAT_COL_ID = B.DAT_COL_ID 
   AND B.SERIAL_ID = :SERIAL_ID 
   AND B.LOT_ID = :LOT_ID 
   AND B.ORDER_ID = :ORDER_ID 
   AND A.OPER_KEY = :OPER_KEY 
   AND A.STEP_KEY = :STEP_KEY 
   AND B.DAT_COL_ID = :DAT_COL_ID';
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

