
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_ORDERTOOLTABSERIALSEL.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_ORDERTOOLTABSERIALSEL';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='pwust_OrderToolTabSerialSel';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='';
v_description sfcore_sql_lib.description%type  :='Defect 44';
v_sql_text sfcore_sql_lib.sql_text%type  :='select order_id, oper_no, oper_key, step_key, step_no,
      a.tool_id, a.tool_no, a.tool_chg, a.tool_no||'',''||a.tool_chg as tool_no_chg,
      qty, serial_flag, exp_flag, a.updt_userid, a.time_stamp,
      a.last_action, a.tool_title, a.item_type, a.item_subtype, tool_model, tool_notes,
      a.tool_no||'',''||a.tool_chg||'',''||manufacturer as tool_chg_mfg,manufacturer,
      decode(orientation_flag,''U'',''Unit Centric'',''D'',''Data Collection Centric'') as orientation_flag,
      cross_order_flag,optional_flag, EXTERNAL_PLM_NO, EXTERNAL_ERP_NO,
      CASE WHEN SLIDE_EMBEDDED_REF_ID IS NULL THEN NULL  WHEN SFFND_MM_SECURITY_GROUP_OK(SLIDE_ID,:@USERID) <> ''Y'' THEN NULL ELSE ''Image'' END AS IMAGE,
      SLIDE_ID, SLIDE_EMBEDDED_REF_ID, IS_TOOL_KITTED, SERIAL_KITTED, a.security_group, a.OVERUSE_FLAG,
      CASE WHEN A.BOM_COMP_TOOL_ID IS NULL  THEN ''I'' ELSE ''B'' END AUTHOR_FROM
FROM sfwid_oper_tool a, sffnd_tool b
WHERE order_id = :order_id
AND SUSPECT_FLAG = ''N''
AND a.tool_no = b.tool_no
AND a.tool_chg = b.tool_chg
AND EXISTS (SELECT 1 FROM sfor_sfwid_oper_subject TT WHERE TT.ORDER_ID = a.ORDER_ID AND TT.OPER_KEY = a.OPER_KEY AND TT.INCLUDED_FLAG =''INCLUDED'')
ORDER BY oper_no, nvl(step_no,''...''), a.display_line_no, a.tool_no, a.tool_chg';
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

