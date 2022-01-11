
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_WID_DATCOLEXEPRE.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_WID_DATCOLEXEPRE';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_WID_DatColExePre';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFWID';
v_description sfcore_sql_lib.description%type  :='Defect 401/795';
v_sql_text sfcore_sql_lib.sql_text%type  :='EXEC com.pw.solumina.sfor.application.SfwidUndoPW.preDataCollectionExecution(:ORDER_ID,
                                :oper_key,
                                :step_key,
                                :dat_col_id,
                                :LOT_NO,
                                :SERIAL_NO,
                                :ORDER_NO,
                                :DCVALUE,
                                :comments,
                                :dat_col_cert,
                                :UCF_SRL_OPER_DC_VCH1,
                                :ucf_srl_oper_dc_vch2,
                                :ucf_srl_oper_dc_vch3,
                                :ucf_srl_oper_dc_vch4,
                                :ucf_srl_oper_dc_vch5,
                                :ucf_srl_oper_dc_vch6,
                                :ucf_srl_oper_dc_vch7,
                                :ucf_srl_oper_dc_vch8,
                                :ucf_srl_oper_dc_vch9,
                                :ucf_srl_oper_dc_vch10,
                                :ucf_srl_oper_dc_vch11,
                                :ucf_srl_oper_dc_vch12,
                                :ucf_srl_oper_dc_vch13,
                                :ucf_srl_oper_dc_vch14,
                                :ucf_srl_oper_dc_vch15,
                                :ucf_srl_oper_dc_num1,
                                :ucf_srl_oper_dc_num2,
                                :ucf_srl_oper_dc_num3,
                                :ucf_srl_oper_dc_num4,
                                :ucf_srl_oper_dc_num5,
                                :ucf_srl_oper_dc_flag1,
                                :ucf_srl_oper_dc_flag2,
                                :ucf_srl_oper_dc_flag3,
                                :ucf_srl_oper_dc_flag4,
                                :ucf_srl_oper_dc_flag5,
                                :ucf_srl_oper_dc_date1,
                                :ucf_srl_oper_dc_date2,
                                :ucf_srl_oper_dc_date3,
                                :ucf_srl_oper_dc_date4,
                                :ucf_srl_oper_dc_date5,
                                ''COLLECT DATA'',
                                :ucf_srl_oper_dc_vch255_1,
                                :ucf_srl_oper_dc_vch255_2,
                                :ucf_srl_oper_dc_vch255_3,
                                :ucf_srl_oper_dc_vch4000_1,
                                :ucf_srl_oper_dc_vch4000_2,
                                :file1,
                                :file1_data,
                                :OLD_USERID,
                                :@USERID,
                                :SKIPPED,
                                :SERIAL_FLAG,
                                :ORDER_QTY,
                                :PROGRAM,
                                :ORDER_CONTROL_TYPE,
                                :CROSS_ORDER_FLAG,
                                :ORIENTATION_FLAG,
                                :OPER_STATUS,
                                :AVAILABLE_QTY,
                                :COMPLETE_QTY,
                                :GROUP_JOB_NO,
                                :GROUP_JOB_INFO,
                                :ACCEPT_REJECT);';
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

