begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_privs_ins('SFMFG','PW_OPER_CONF_UPDATE','Update order operation confirmation number','');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/