begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$6100_WSE_APPROVER','PW_OPER_CONF_UPDATE');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/