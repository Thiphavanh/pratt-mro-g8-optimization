--- Add Priv "ORDER_CREATE" and "SERIAL_LOT_CHG" to Roles 
--     SF$1180_ENGINEER,
--     SF$1350_ENGINEER,
--     SF$1700_ENGINEER,
--     SF$4100_ENGINEER,
--     SF$6100_ENGINEER,
--     SF$6150_ENGINEER,
--     SF$APU1_ENGINEER,
--     SF$CEC2_ENGINEER,
--     SF$ESA1_ENGINEER
declare
  duplicate_entry exception;
  pragma exception_init(duplicate_entry, -20101);
begin
  for i in(select role from sfcore_constrain_roles_4_privs where ROLE_DESC LIKE 'SF$ENGINEER%')
  loop
      begin
        SFCORE_ROLES_PRIV_INS('SFMFG',i.role,'ORDER_CREATE');
      exception 
            when duplicate_entry then null; 
      end;
  end loop;
end;
/
commit;
/
declare
  duplicate_entry exception;
  pragma exception_init(duplicate_entry, -20101);
begin
  for i in(select role from sfcore_constrain_roles_4_privs where ROLE_DESC LIKE 'SF$ENGINEER%')
  loop
      begin
        SFCORE_ROLES_PRIV_INS('SFMFG',i.role,'SERIAL_LOT_CHG');
      exception 
            when duplicate_entry then null; 
      end;
  end loop;
end;
/
commit;
/