create or replace trigger pwue_swimlane_protector
  for update of serial_oper_status on sfwid_serial_oper  
compound trigger
type details is record(order_id varchar2(50), lot_id varchar2(50), serial_id varchar2(50),oper_key integer, sl integer);
type rows is table of details index by pls_integer;
crows rows;
csl integer;
etype varchar2(40);
nc integer;
oc integer;
i integer;
r integer:=1;
cid varchar2(50);
after each row is
begin
if :old.serial_oper_status='PENDING' and :new.serial_oper_status='IN QUEUE' then
  select node_column-1,display_sequence into csl, etype
  from sfwid_order_node a, sfwid_order_desc b
  where a.order_id=:new.order_id
  and oper_key=:new.oper_key
  and step_key=:new.step_key
  and a.order_id=b.order_id;
--dbms_output.put_line('after each '||csl);
  if etype='Execution Order' and csl >= 2 then -- 0 would mean auto-parallel and 1 would mean no prior swimlanes to check
    crows(r).order_id:=:new.order_id; 
    crows(r).lot_id:=:new.lot_id;
    crows(r).serial_id:=:new.serial_id;
    crows(r).oper_key:=:new.oper_key;
    crows(r).sl:=csl;
    r:=r+1;
  end if;
end if;

end after each row;
after statement is
begin
 for i in 1..crows.count loop
   select count(*),sum(case when serial_oper_status in ('PENDING','IN QUEUE','ACTIVE') then 1 else 0 end),
   sfcore_get_user_connection_id
   into nc,oc,cid
   from sfwid_serial_oper
   where (order_id,oper_key,step_key) in (select order_id,oper_key,step_key from sfwid_order_node
   where order_id=crows(i).order_id
   and node_column<crows(i).sl+1);
--dbms_output.put_line('after '||i||' SL:'||crows(i).sl||' '||nc||' '||oc);
   if oc>1 then
      raise_application_error(-20999,'Protector Trigger 1 ['||crows(i).order_id||'],['||
                              crows(i).oper_key||'],['||crows(i).serial_id||'],'||chr(10)||'['||
                              crows(i).sl||'[,['||to_char(sysdate,'dd-mon-yyyy hh24:mi:ss')||'],['||cid||']'
                              );
   end if;
 end loop;
end after statement;
end pwue_swimlane_protector;
/
