--Create Priv
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_PRIVS_INS('SFMFG','PLAN_MIGRATE', 'Allows a user to press the Migrate Plans button', 'SFPL');
   exception 
      when duplicate_entry then null;                                         
   end;
end;
/
commit;

--Assign Priv to Superuser
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
     SFCORE_ROLES_PRIV_INS('SFMFG', 'SF$SUPERUSER', 'PLAN_MIGRATE');
   exception 
      when duplicate_entry then null;                                         
   end;
end;
/
commit;
