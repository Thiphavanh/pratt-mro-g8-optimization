--Create PRIVS
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_privs_ins('SFMFG','PW_SECURITY_MENU','see the Users Security Groups','');
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
      sfcore_roles_priv_ins('SFMFG','SF$SUPERUSER','PW_SECURITY_MENU');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
