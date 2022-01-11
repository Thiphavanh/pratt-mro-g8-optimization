-- Assign Priv to Role
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$USER_ADMINISTRATION','PW_SECURITY_MENU');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
