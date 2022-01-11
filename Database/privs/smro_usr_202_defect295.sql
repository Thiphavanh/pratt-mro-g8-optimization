-----Assign priv to Roles SF$6100_DATA_CONTROL_ENGINEER
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$6100_DATA_CONTROL_ENGINEER','STDOPER_AUTHORING');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/