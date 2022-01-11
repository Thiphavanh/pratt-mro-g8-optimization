--insert into sfcore_privs(priv, priv_desc)
--values ('PWU_ENGINE_MANUAL','ENGINE MANUAL MAINTENANCE');

--insert into sfcore_app_role_privs(role,priv)
--values('SF$SUPERUSER','PWU_ENGINE_MANUAL');

--insert into sfcore_privs(priv, priv_desc)
--values ('PWU_ENGINE_AUTHORITY','ENGINE AUTHORITY MAINTENANCE');

--insert into sfcore_app_role_privs(role,priv)
--values('SF$SUPERUSER','PWU_ENGINE_AUTHORITY');

--Create PRIVS
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_privs_ins('SFMFG','PWU_ENGINE_MANUAL','ENGINE MANUAL MAINTENANCE','');

   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_privs_ins('SFMFG','PWU_ENGINE_AUTHORITY','ENGINE AUTHORITY MAINTENANCE','');

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
      sfcore_roles_priv_ins('SFMFG','SF$SUPERUSER','PWU_ENGINE_MANUAL');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$SUPERUSER','PWU_ENGINE_AUTHORITY');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
