
set define off
declare
  v_msgid  sfcore_messages.msg_id%type :='PWMROI_CDB044D6B456447EB78589E79A9105AC';
  v_language_code sfcore_messages.language_code%type := 'en';
  v_stype sfcore_messages.stype%type :='SFWID';
  v_capt_trans sfcore_messages.capt_trans%type :='';
  v_msg sfcore_messages.msg_text%type :='Zcom Sales order %V1 already exist at work location %V2';
begin
update sfcore_messages
set updt_userid=user,time_stamp=sysdate,last_action='UPDATED', STYPE=V_STYPE, CAPT_TRANS=v_capt_trans,msg_text=v_msg
where msg_id=v_msgid  and language_code = v_language_code ;
if sql%rowcount=0 then
  insert into sfcore_messages(msg_id,language_code,updt_userid,time_stamp,last_action,stype, capt_trans,msg_text)
  values(v_msgid,v_language_code,user,sysdate,'INSERTED',v_stype,v_capt_trans,v_msg);
end if;
commit;
end;
/

set define on

