
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_COMMREPLYCMDPRE.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_sql_lib_folder.folder_id%type  :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_sql_id sfcore_sql_lib.sql_id%type :='PWUST_COMMREPLYCMDPRE';
v_sql_id_displ sfcore_sql_lib.sql_id_displ%type  :='PWUST_CommReplyCmdPre';
v_read_only sfcore_sql_lib.read_only%type  :='';
v_datasource sfcore_sql_lib.datasource%type  :='';
v_stype sfcore_sql_lib.stype%type  :='SFFND';
v_description sfcore_sql_lib.description%type  :='SMRO_EXPT_201';
v_sql_text sfcore_sql_lib.sql_text%type  :='EXEC com.pw.solumina.sffnd.application.ICommunicationPW.replyCommunicationPre(
:comm_id,
:ucf_comm_vch1,
:ucf_comm_vch2,
:ucf_comm_vch3,
:ucf_comm_vch4,
:ucf_comm_vch5,
:ucf_comm_vch6,
:ucf_comm_vch7,
:ucf_comm_vch8,
:ucf_comm_vch9,
:ucf_comm_vch10,
:ucf_comm_vch11,
:ucf_comm_vch12,
:ucf_comm_vch13,
:ucf_comm_vch14,
:ucf_comm_vch15,
:ucf_comm_num1,
:ucf_comm_num2,
:ucf_comm_num3,
:ucf_comm_num4,
:ucf_comm_num5,
:ucf_comm_flag1,
:ucf_comm_flag2,
:ucf_comm_flag3,
:ucf_comm_flag4,
:ucf_comm_flag5,
:ucf_comm_date1,
:ucf_comm_date2,
:ucf_comm_date3,
:ucf_comm_date4,
:ucf_comm_date5,
:ucf_comm_vch255_1,
:ucf_comm_vch255_2,
:ucf_comm_vch255_3,
:ucf_comm_vch4000_1,
:ucf_comm_vch4000_2,
''REJECT'')';
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

