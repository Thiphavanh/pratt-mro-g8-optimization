set define off
declare
  v_priv varchar2(32) :='CAN_VIEW_ALL_PAAR_REQUESTS';
  v_stype varchar2(32) :='';
  v_desc varchar2(32767) :='Privilege to view all PAAR Requests';
begin
update sfcore_privs
set updt_userid=user,time_stamp=sysdate,stype=v_stype,priv_desc=v_desc
where priv=v_priv;
if sql%rowcount=0 then
  insert into sfcore_privs(priv,stype,updt_userid,time_stamp,priv_desc)
  values(v_priv,v_stype,user,sysdate,v_desc);
  insert into sfcore_app_role_privs(role,priv) values ('SF$SUPERUSER',v_priv);
end if;
commit;
end;
/

set define on

