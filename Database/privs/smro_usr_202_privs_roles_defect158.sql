
--Assign priv DISPATCH_PRPLG to role 

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$1700_JR_PLANNER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_JR_PLANNER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_JR_PLANNER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_JR_PLANNER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_JR_PLANNER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_JR_PLANNER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_JR_PLANNER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_JR_PLANNER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_JR_PLANNER','DISPATCH_PRPLG');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/
---------------------------

begin
   declare
      duplicate_entry exception;
      pragma exception_init(duplicate_entry, -20101);
   begin
      sfcore_roles_priv_ins('SFMFG','SF$1700_SR_PLANNER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$6100_SR_PLANNER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$6150_SR_PLANNER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$4100_SR_PLANNER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1180_SR_PLANNER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$1350_SR_PLANNER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$APU1_SR_PLANNER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$CEC2_SR_PLANNER','DISPATCH_PRPLG');
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
      sfcore_roles_priv_ins('SFMFG','SF$ESA1_SR_PLANNER','DISPATCH_PRPLG');
   exception 
      when duplicate_entry then null;                                                                                                    
   end;
end;
/
commit;
/