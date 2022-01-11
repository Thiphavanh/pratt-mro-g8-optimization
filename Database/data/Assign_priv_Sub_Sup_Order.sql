--Create ROLES
begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      SFCORE_ROLES_INS('SFMFG','SF$6100_Tradesperson','6100_Tradesperson','');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_Tradesperson','PWUST_SUB_ORDER_CREATE');
      sfcore_roles_priv_ins('SFMFG','SF$6100_Tradesperson','PWUST_SUB_ORDER_START_ALT');
      sfcore_roles_priv_ins('SFMFG','SF$6100_Tradesperson','PWUST_SUPPLEM_ORDER_CREATE');
      sfcore_roles_priv_ins('SFMFG','SF$6100_Tradesperson','PWUST_SUPPLEM_ORDER_ALT_ST');
      sfcore_roles_priv_ins('SFMFG','SF$6100_WSE_APPROVER','PWUST_SUB_ORDER_REL');
      sfcore_roles_priv_ins('SFMFG','SF$6100_WSE_APPROVER','PWUST_SUB_ORDER_DEL');
      sfcore_roles_priv_ins('SFMFG','SF$6100_WSE_APPROVER','PWUST_SUPPLEM_ORDER_REL');
      sfcore_roles_priv_ins('SFMFG','SF$6100_WSE_APPROVER','PWUST_SUPPLEM_ORDER_DEL');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/

