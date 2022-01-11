--Create ROLES
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$EXPORT_BYPASS','Bypasses xClass for Plans in Bypass Release Packages','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

--Create PRIVS
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_privs_ins('SFMFG','DEV_EX','Bypasses xClass for Plans in Bypass Release Packages','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

-- Assign Created Priv to Role
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$EXPORT_BYPASS'','DEV_EX');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

