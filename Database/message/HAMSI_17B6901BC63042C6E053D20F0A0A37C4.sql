set define off
declare
  v_msgid varchar2(40) :='HAMSI_17B6901BC63042C6E053D20F0A0A37C4';
  v_stype varchar2(32) :='SFPL';
  v_site varchar2(40) :='HAMSI';
  v_msg varchar2(32767) :='All PAAR requests must be in COMPLETE or CANCELLED status to complete this workflow task';
begin
update sfcore_messages
set updt_userid=user,time_stamp=sysdate,last_action='UPDATED', STYPE=V_STYPE,msg_text=v_msg
where msg_id=v_msgid;
if sql%rowcount=0 then
  insert into sfcore_messages(msg_id,updt_userid,time_stamp,last_action,stype, msg_text)
  values(v_msgid,user,sysdate,'INSERTED',v_stype, v_msg);
end if;
commit;
end;
/

set define on

